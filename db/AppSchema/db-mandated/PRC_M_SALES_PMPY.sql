IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'M_SALES_PMPY'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[M_SALES_PMPY]
  END

GO


CREATE PROCEDURE [dbo].[M_SALES_PMPY](@CONTRACT_MASTER_SID    INT,
                                     @COMPANY_MASTER_SID     INT,
                                     @ITEM_MASTER_SID_CM_SEP VARCHAR(MAX),
                                     @FREQUENCY              VARCHAR(10))
AS
  BEGIN
      BEGIN TRY
	      SET NOCOUNT ON
          DECLARE @UDT_CCP       UDT_CCP_DETAILS,
                  @@ROW_COUNT    INT,
                  @CT_MASTER_SID INT,
                  @CM_MASTER_SID INT,
                  @IM_MASTER_SID INT,
                  @COMPANY_ID    VARCHAR(50),
                  @INCR          INT = 1

          INSERT INTO @UDT_CCP
                      (CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID)
          SELECT @CONTRACT_MASTER_SID,
                 @COMPANY_MASTER_SID,
                 TOKEN
          FROM   UDF_SPLITSTRING(@ITEM_MASTER_SID_CM_SEP, ',')

          IF OBJECT_ID('TEMPDB..#TEMP') IS NOT NULL
            DROP TABLE #TEMP

          CREATE TABLE #TEMP
            (
               ACTUAL_SALES    NUMERIC(22, 6),
               ACTUAL_UNITS    NUMERIC(22, 6),
               PROJECTED_SALES NUMERIC(22, 6),
               PROJECTED_UNITS NUMERIC(22, 6),
               COV_LIVES       NUMERIC(22, 6),
               PERIOD_SID      INT,
               MONTH           INT,
               QUARTER         INT,
               SEMIANNUAL      INT,
               YEAR            INT
            )

          ---------------------------FETCH ACTUAL SALES,ACTUAL UNITS FOR EACH CCP FROM ACTUALS_MASTER TABLE STARTS HERE-----------------------------
          INSERT INTO #TEMP
                      (ACTUAL_SALES,
                       ACTUAL_UNITS,
                       PERIOD_SID,
                       MONTH,
                       QUARTER,
                       SEMIANNUAL,
                       YEAR)
          SELECT SUM(SALES)    AS SALES,
                 SUM(QUANTITY) AS UNITS,
                 PERIOD_SID,
                 MONTH,
                 QUARTER,
                 CASE
                                WHEN QUARTER IN ( 1, 2 ) THEN 1
                                ELSE 2
                              END AS SEMIANNUAL,
                 YEAR
          FROM   UDF_ACTUALS_DETAILS(@UDT_CCP, DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0), DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, -3, GETDATE())), 0))
          WHERE  QUANTITY_INCLUSION = 'Y'
          GROUP  BY PERIOD_SID,
                    MONTH,
                    QUARTER,
                    CASE
                      WHEN QUARTER IN ( 1, 2 ) THEN 1
                      ELSE 2
                    END,
                    YEAR

          ---------------------------FETCH ACTUAL SALES,ACTUAL UNITS FOR EACH CCP FROM ACTUALS_MASTER TABLE ENDS HERE-----------------------------
          DECLARE @TEMP_VALUE TABLE
            (
               RID                 INT IDENTITY(1, 1),
               CONTRACT_MASTER_SID INT,
               COMPANY_MASTER_SID  INT,
               ITEM_MASTER_SID     INT
            )

          INSERT INTO @TEMP_VALUE
                      (CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID)
          SELECT CONTRACT_MASTER_SID,
                 COMPANY_MASTER_SID,
                 ITEM_MASTER_SID
          FROM   @UDT_CCP

          SET @@ROW_COUNT = @@ROWCOUNT

          ---------------------------FETCH PROJECTED SALES FOR EACH CCP FROM M_SALES_PROJECTION TABLE STARTS HERE-----------------------------
          WHILE ( @INCR <= @@ROW_COUNT )
            BEGIN
                SELECT @CT_MASTER_SID = CONTRACT_MASTER_SID,
                       @CM_MASTER_SID = COMPANY_MASTER_SID,
                       @IM_MASTER_SID = ITEM_MASTER_SID
                FROM   @TEMP_VALUE
                WHERE  RID = @INCR

                UPDATE A
                SET    A.PROJECTED_SALES = COALESCE(B.PROJECTION_SALES, 0),
                       A.PROJECTED_UNITS = COALESCE(B.PROJECTION_UNITS, 0) --SUM UP EACH CCP'S PROJECTED SALES
                FROM   #TEMP A
                       JOIN (SELECT SP.PROJECTION_SALES,
                                    SP.PROJECTION_UNITS,
                                    P.PERIOD_SID
                             FROM   (SELECT TOP 1 PM.PROJECTION_MASTER_SID,
                                                  PD.PROJECTION_DETAILS_SID
                                     FROM   PROJECTION_MASTER PM
                                            JOIN WORKFLOW_MASTER WM
                                              ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
											     AND EXISTS (SELECT HELPER_TABLE_SID
                                                                              FROM   HELPER_TABLE H1
                                                                              WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                                                                     AND DESCRIPTION = 'APPROVED'
																					 AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID)
                                                 --AND WM.WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID
                                                 --                             FROM   HELPER_TABLE
                                                 --                             WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                                 --                                    AND DESCRIPTION = 'APPROVED')
                                            JOIN PROJECTION_DETAILS PD
                                              ON WM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
									 WHERE EXISTS (SELECT CCP_DETAILS_SID
                                                                   FROM   CCP_DETAILS CCP
                                                                   WHERE  CONTRACT_MASTER_SID = @CT_MASTER_SID
                                                                          AND COMPANY_MASTER_SID = @CM_MASTER_SID
                                                                          AND ITEM_MASTER_SID = @IM_MASTER_SID
																		  AND PD.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID)
                                     --WHERE  PD.CCP_DETAILS_SID IN (SELECT CCP_DETAILS_SID
                                     --                              FROM   CCP_DETAILS
                                     --                              WHERE  CONTRACT_MASTER_SID = @CT_MASTER_SID
                                     --                                     AND COMPANY_MASTER_SID = @CM_MASTER_SID
                                     --                                     AND ITEM_MASTER_SID = @IM_MASTER_SID)
                                     ORDER  BY WM.MODIFIED_DATE DESC) OP
                                    JOIN M_SALES_PROJECTION SP
                                      ON OP.PROJECTION_DETAILS_SID = SP.PROJECTION_DETAILS_SID
                                    RIGHT JOIN PERIOD P
                                            ON SP.PERIOD_SID = P.PERIOD_SID
                             WHERE  P.PERIOD_DATE BETWEEN DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, -3, GETDATE())), 0) AND DATEADD("S", -1, DATEADD(QUARTER, DATEDIFF(QUARTER, 0, GETDATE()), 0)))B
                         ON A.PERIOD_SID = B.PERIOD_SID

                SET @INCR += 1
            END

          ---------------------------FETCH PROJECTED SALES FOR EACH CCP FROM M_SALES_PROJECTION TABLE ENDS HERE-----------------------------
          ----------------------------------------LIVES CALCULATION STARTS HERE-------------------------------------------------------------
          SELECT @COMPANY_ID = COMPANY_ID
          FROM   COMPANY_MASTER
          WHERE  COMPANY_MASTER_SID = @COMPANY_MASTER_SID

          UPDATE A
          SET    A.COV_LIVES = COALESCE(B.COV_LIVES, 0)
          FROM   #TEMP A
                 JOIN (SELECT P.PERIOD_SID,
                              OP.COV_LIVES
                       FROM   PERIOD P
                              OUTER APPLY(SELECT TOP 1 MDA.COLUMN_3 AS COV_LIVES
                                          FROM   MASTER_DATA_ATTRIBUTE MDA
                                          WHERE  MDA.MASTER_ATTRIBUTE = 'COV_LIVES'
                                                 AND MDA.MASTER_TYPE = 'COMPANY_MASTER'
                                                 AND MDA.MASTER_ID = @COMPANY_ID
                                                 AND ( CAST(MDA.COLUMN_1 AS DATE) <= CAST(P.PERIOD_DATE AS DATE)
                                                       AND CAST(MDA.COLUMN_2 AS DATE) >= CAST(DATEADD(D, -1, DATEADD(M, DATEDIFF(M, 0, P.PERIOD_DATE) + 1, 0)) AS DATE) ) --LAST DAY OF THE MONTH
                                          ORDER  BY MDA.MODIFIED_DATE DESC)OP
                       WHERE  P.PERIOD_DATE BETWEEN DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, -3, GETDATE())), 0) AND DATEADD("S", -1, DATEADD(QUARTER, DATEDIFF(QUARTER, 0, GETDATE()), 0)))B
                   ON A.PERIOD_SID = B.PERIOD_SID

          ----------------------------------------LIVES CALCULATION ENDS HERE-------------------------------------------------------------
          IF @FREQUENCY = 'MONTHLY'
            SELECT SUM(ACTUAL_SALES)ACTUAL_SALES,
			       SUM(ACTUAL_UNITS)ACTUAL_UNITS,
                   SUM(PROJECTED_SALES)PROJECTED_SALES,
				   SUM(PROJECTED_UNITS)PROJECTED_UNITS,
                   SUM(COV_LIVES)COV_LIVES,
                   MONTH,
                   YEAR
            FROM   #TEMP
            GROUP  BY MONTH,
                      YEAR
          ELSE IF @FREQUENCY = 'QUARTERLY'
            SELECT SUM(ACTUAL_SALES)ACTUAL_SALES,
			       SUM(ACTUAL_UNITS)ACTUAL_UNITS,
                   SUM(PROJECTED_SALES)PROJECTED_SALES,
				   SUM(PROJECTED_UNITS)PROJECTED_UNITS,
                   SUM(COV_LIVES)COV_LIVES,
                   QUARTER,
                   YEAR
            FROM   #TEMP
            GROUP  BY QUARTER,
                      YEAR
          ELSE IF @FREQUENCY = 'YEARLY'
            SELECT SUM(ACTUAL_SALES)ACTUAL_SALES,
			       SUM(ACTUAL_UNITS)ACTUAL_UNITS,
                   SUM(PROJECTED_SALES)PROJECTED_SALES,
				   SUM(PROJECTED_UNITS)PROJECTED_UNITS,
                   SUM(COV_LIVES)COV_LIVES,
                   YEAR
            FROM   #TEMP
            GROUP  BY YEAR
          ELSE
            SELECT SUM(ACTUAL_SALES)ACTUAL_SALES,
			       SUM(ACTUAL_UNITS)ACTUAL_UNITS,
                   SUM(PROJECTED_SALES)PROJECTED_SALES,
				   SUM(PROJECTED_UNITS)PROJECTED_UNITS,
                   SUM(COV_LIVES)COV_LIVES,
                   SEMIANNUAL,
                   YEAR
            FROM   #TEMP
            GROUP  BY SEMIANNUAL,
                      YEAR
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
          )
      END CATCH
  END 