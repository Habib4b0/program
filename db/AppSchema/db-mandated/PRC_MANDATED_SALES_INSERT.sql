IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_MANDATED_SALES_INSERT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_MANDATED_SALES_INSERT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_MANDATED_SALES_INSERT]   (@PROJECTION INT,
                                                      @USER_ID    INT,
                                                      @SESSION_ID INT)
AS
  BEGIN
      SET NOCOUNT ON

      DECLARE @FROM_DATE DATETIME
      DECLARE @START_DATE DATETIME
      DECLARE @PROJECTION_DATE DATETIME
      DECLARE @START_FROM DATETIME

      BEGIN TRY
          SET @FROM_DATE= (SELECT DATEADD(YY, DATEDIFF(YY, 0, GETDATE()) - 3, 0))
          SET @START_DATE = (SELECT DATEFROMPARTS (YEAR(FROM_DATE),1,1)
                             FROM   PROJECTION_MASTER
                             WHERE  PROJECTION_MASTER_SID = @PROJECTION)
          SET @PROJECTION_DATE = (SELECT DATEFROMPARTS (YEAR(TO_DATE),12,1)
                                  FROM   PROJECTION_MASTER
                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION)
          SET @START_FROM=(SELECT DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0))

          CREATE TABLE #ST_M_SALES_PROJECTION_MASTER
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT
            )

          INSERT INTO #ST_M_SALES_PROJECTION_MASTER
                      (PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID)
          SELECT PROJECTION_DETAILS_SID,
                 CCP_DETAILS_SID
          FROM   PROJECTION_DETAILS
          WHERE  PROJECTION_MASTER_SID = @PROJECTION

          ----------------- EDIT PROCESS_MODE PLUGIN STARTS HERE --------------
          IF EXISTS(SELECT 1
                    FROM   ST_M_SALES_PROJECTION_MASTER M
                           JOIN PROJECTION_DETAILS P
                             ON P.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID
                    WHERE  PROJECTION_MASTER_SID = @PROJECTION)
            BEGIN
                DELETE T ---- TO FIND NEWLY ADDED CCP ALONE
                FROM   #ST_M_SALES_PROJECTION_MASTER T
                       JOIN ST_M_SALES_PROJECTION_MASTER M
                         ON M.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID
                            AND M.USER_ID = @USER_ID
                            AND M.SESSION_ID = @SESSION_ID
            END

          ----------------EDIT PROCESS_MODE PLUGIN ENDS HERE ------------
          ---------------- TO FIND THE APPROVED CCP DETAILS START---------------------
          IF OBJECT_ID('TEMPDB..#APPROVED_CCP_DETAILS') IS NOT NULL
            TRUNCATE TABLE #APPROVED_CCP_DETAILS
          ELSE
            CREATE TABLE #APPROVED_CCP_DETAILS
              (
                 PROJECTION_MASTER_SID  INT,
                 PROJECTION_DETAILS_SID INT,
                 CCP_DETAILS_SID        INT
              )

          INSERT INTO #APPROVED_CCP_DETAILS
                      (PROJECTION_MASTER_SID,
                       PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID)
          SELECT PROJECTION_MASTER_SID,
                 PROJECTION_DETAILS_SID,
                 CCP_DETAILS_SID
          FROM   DBO.UDF_APPROVED_CCP_DETAILS(@PROJECTION, 'MANDATED')
	

          ---------------- TO FIND THE APPROVED CCP DETAILS END---------------------
          INSERT INTO ST_M_SALES_PROJECTION_MASTER
                      (PROJECTION_DETAILS_SID,
                       CHECK_RECORD,
                       METHODOLOGY,
                       CALCULATION_PERIODS,
                       CALCULATION_BASED,
                       [USER_ID],
                       SESSION_ID)
          SELECT PROJECTION_DETAILS_SID,
                 COALESCE(A.CHECK_RECORD, 0),
                 A.METHODOLOGY,
                 A.CALCULATION_PERIODS,
                 A.CALCULATION_BASED,
                 @USER_ID,
                 @SESSION_ID
          FROM   #ST_M_SALES_PROJECTION_MASTER SPM
                 LEFT JOIN (SELECT SPM.METHODOLOGY,
                                   SPM.CALCULATION_PERIODS,
                                   SPM.CALCULATION_BASED,
                                   SPM.CHECK_RECORD,
                                   ACD.CCP_DETAILS_SID
                            FROM   M_SALES_PROJECTION_MASTER SPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID) A -- BRINGS THE DATA FROM MAIN TABLE FOR THE APPROVED PROJECTION
                        ON SPM.CCP_DETAILS_SID = A.CCP_DETAILS_SID

          DECLARE @ACTUALS_CCP UDT_CCP_DETAILS

          INSERT INTO @ACTUALS_CCP
                      (CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID,
                       PROJECTION_DETAILS_SID)
          SELECT CONTRACT_MASTER_SID,
                 COMPANY_MASTER_SID,
                 ITEM_MASTER_SID,
                 D.PROJECTION_DETAILS_SID
          FROM   PROJECTION_DETAILS A
                 JOIN CCP_DETAILS C
                   ON A.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                 JOIN ST_M_SALES_PROJECTION_MASTER D
                   ON D.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
          WHERE  PROJECTION_MASTER_SID = @PROJECTION
                 AND D.USER_ID = @USER_ID
                 AND D.SESSION_ID = @SESSION_ID

          CREATE TABLE #ST_M_ACTUAL_SALES
            (
               PROJECTION_DETAILS_SID INT,
               PERIOD_SID             INT,
               ACTUAL_SALES           NUMERIC(22, 6),
               ACTUAL_UNITS           NUMERIC(22, 6)
            )

          INSERT INTO #ST_M_ACTUAL_SALES
                      (PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS)
          SELECT PROJECTION_DETAILS_SID,
                 PERIOD_SID,
                 MAX(SALES)    ACTUAL_SALES,
                 MAX(QUANTITY) ACTUAL_UNITS
          FROM   [DBO].[UDF_ACTUALS_DETAILS](@ACTUALS_CCP, @START_FROM, @FROM_DATE)
          WHERE  QUANTITY_INCLUSION = 'Y'
          GROUP  BY PROJECTION_DETAILS_SID,
                    PERIOD_SID

          DELETE S
          FROM   ST_M_ACTUAL_SALES S
                 JOIN ST_M_SALES_PROJECTION_MASTER M
                   ON M.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                 JOIN PROJECTION_DETAILS P
                   ON M.PROJECTION_DETAILS_SID = P.PROJECTION_DETAILS_SID
                      AND P.PROJECTION_MASTER_SID = @PROJECTION
                      AND S.USER_ID = @USER_ID
                      AND S.SESSION_ID = @SESSION_ID

          INSERT INTO ST_M_ACTUAL_SALES
                      (PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS,
                       [USER_ID],
                       [SESSION_ID])
          SELECT PROJECTION_DETAILS_SID,
                 PERIOD_SID,
                 ACTUAL_SALES,
                 ACTUAL_UNITS,
                 @USER_ID,
                 @SESSION_ID
          FROM   #ST_M_ACTUAL_SALES

          CREATE TABLE #ST_M_SALES_PROJECTION
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               PERIOD_SID             INT
            )

          SELECT @START_DATE = DATEADD(MONTH, DATEDIFF(MONTH, 0, @START_DATE), 0),
                 @PROJECTION_DATE = DATEADD(MONTH, DATEDIFF(MONTH, 0, @PROJECTION_DATE), 0)

          INSERT INTO #ST_M_SALES_PROJECTION
                      (PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       PERIOD_SID)
          SELECT PROJECTION_DETAILS_SID,
                 CCP_DETAILS_SID,
                 PERIOD_SID
          FROM   PERIOD
                 CROSS JOIN (SELECT AC.PROJECTION_DETAILS_SID,
                                    CCP_DETAILS_SID
                             FROM   @ACTUALS_CCP AC
                                    INNER JOIN #ST_M_SALES_PROJECTION_MASTER NSPM
                                            ON AC.PROJECTION_DETAILS_SID = NSPM.PROJECTION_DETAILS_SID) A
          WHERE  PERIOD_DATE BETWEEN @START_DATE AND @PROJECTION_DATE

          DELETE T
          FROM   #ST_M_SALES_PROJECTION T
                 JOIN ST_M_SALES_PROJECTION M
                   ON M.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID
                      AND T.PERIOD_SID = M.PERIOD_SID
                      AND M.USER_ID = @USER_ID
                      AND M.SESSION_ID = @SESSION_ID

          INSERT INTO ST_M_SALES_PROJECTION
                      (PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       [USER_ID],
                       SESSION_ID)
          SELECT PROJECTION_DETAILS_SID,
                 MSP.PERIOD_SID,
                 COALESCE(A.ACCOUNT_GROWTH, 0),
                 COALESCE(A.PRODUCT_GROWTH, 0),
                 PROJECTION_SALES,
                 PROJECTION_UNITS,
                 @USER_ID,
                 @SESSION_ID
          FROM   #ST_M_SALES_PROJECTION MSP
                 LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                                   SPM.PRODUCT_GROWTH,
                                   SPM.PROJECTION_SALES,
                                   SPM.PROJECTION_UNITS,
                                   SPM.PERIOD_SID,
                                   ACD.CCP_DETAILS_SID
                            FROM   M_SALES_PROJECTION SPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID) A
                        ON MSP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND MSP.PERIOD_SID = A.PERIOD_SID
      END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC USPERRORCOLLECTOR

          SELECT @ERRORMESSAGE = ERROR_MESSAGE(),
                 @ERRORSEVERITY = ERROR_SEVERITY(),
                 @ERRORSTATE = ERROR_STATE(),
                 @ERRORPROCEDURE = ERROR_PROCEDURE(),
                 @ERRORLINE = ERROR_LINE(),
                 @ERRORNUMBER = ERROR_NUMBER()

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.
                     @ERRORSEVERITY,-- SEVERITY.
                     @ERRORSTATE,-- STATE.
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.
                     @ERRORNUMBER,-- ERRORNUMBER
                     @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END 
GO
