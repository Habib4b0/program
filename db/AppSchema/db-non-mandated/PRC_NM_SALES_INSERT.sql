IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_SALES_INSERT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NM_SALES_INSERT]
  END

GO
CREATE PROCEDURE [dbo].[PRC_NM_SALES_INSERT] (@PROJECTION INT,
                                                   @USER_ID    INT,
                                                   @SESSION_ID VARCHAR(50))
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY

          DECLARE @MASTER_TABLE     VARCHAR(200) = Concat('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ACTUAL_TABLE     VARCHAR(200) = Concat('ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @PROJECTION_TABLE VARCHAR(200) = Concat('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @FROM_DATE DATETIME
          DECLARE @START_DATE DATETIME
          DECLARE @PROJECTION_DATE DATETIME
          DECLARE @START_FROM DATETIME
          DECLARE @PROJECTION_SAVE_FALG BIT

          SET @FROM_DATE = (SELECT Dateadd(YY, Datediff(YY, 0, Getdate()) - 3, 0))
          SET @START_DATE = (SELECT Datefromparts (Year(FROM_DATE), 1, 1)
                             FROM   PROJECTION_MASTER
                             WHERE  PROJECTION_MASTER_SID = @PROJECTION)
          SET @PROJECTION_DATE = (SELECT Datefromparts (Year(TO_DATE), 12, 1)
                                  FROM   PROJECTION_MASTER
                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION)
          SET @START_FROM = (SELECT Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0))

          -----------------------TO GET SAVE_FLAG INFORMATION FOR THE PROJECTION----------------------------
          SELECT @PROJECTION_SAVE_FALG = SAVE_FLAG
          FROM   PROJECTION_MASTER PM
                 INNER JOIN PROJECTION_DETAILS PD
                         ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                 INNER JOIN CCP_DETAILS C
                         ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
          WHERE  PM.PROJECTION_MASTER_SID = @PROJECTION

          -----------------------TO GET SAVE_FLAG INFORMATION FOR THE PROJECTION END----------------------------
          IF Object_id('TEMPDB..#ST_NM_SALES_PROJECTION_MASTER') IS NOT NULL
            DROP TABLE #ST_NM_SALES_PROJECTION_MASTER

          CREATE TABLE #ST_NM_SALES_PROJECTION_MASTER
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT
            )

          INSERT INTO #ST_NM_SALES_PROJECTION_MASTER
                      (PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID)
          SELECT PROJECTION_DETAILS_SID,
                 CCP_DETAILS_SID
          FROM   PROJECTION_DETAILS
          WHERE  PROJECTION_MASTER_SID = @PROJECTION

	

          ----------------- EDIT PROCESS_MODE PLUGIN STARTS HERE --------------
          DECLARE @SQL NVARCHAR(MAX)=''

          SET @SQL = Concat('IF EXISTS (SELECT 1
                     FROM   ', @MASTER_TABLE, ' NM
                            INNER JOIN PROJECTION_DETAILS P
                                    ON P.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                     WHERE  PROJECTION_MASTER_SID = ', @PROJECTION, ')
            BEGIN
                DELETE T ---- TO FIND NEWLY ADDED CCP ALONE
                FROM   #ST_NM_SALES_PROJECTION_MASTER T
                       INNER JOIN ', @MASTER_TABLE, ' N
                               ON N.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID
            END')

          EXEC sp_executesql @SQL

		  

          ----------------EDIT PROCESS_MODE PLUGIN ENDS HERE ------------
          ---------------- TO FIND THE APPROVED CCP DETAILS START---------------------
          IF Object_id('TEMPDB..#APPROVED_CCP_DETAILS') IS NOT NULL
            DROP TABLE #APPROVED_CCP_DETAILS

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
          FROM   DBO.Udf_approved_ccp_details(@PROJECTION, 'NON MANDATED')

          ---------------- TO FIND THE APPROVED CCP DETAILS END---------------------
          --------------------------ST_NM_SALES_PROJECTION_MASTER INSERTION START HERE --------------------------------
          DECLARE @SQL1 NVARCHAR(MAX)=''

          SET @SQL1 = Concat('INSERT INTO ', @MASTER_TABLE, '
                      (PROJECTION_DETAILS_SID,
                       METHODOLOGY,
                       CHECK_RECORD,
                       USER_GROUP,
                       CALCULATION_PERIODS,
                       CALCULATION_BASED)
          SELECT SPM.PROJECTION_DETAILS_SID,
                 A.METHODOLOGY,
                 COALESCE(A.CHECK_RECORD, 0),
                 COALESCE(A.USER_GROUP, ''0''),
                 CALCULATION_PERIODS,
                 CALCULATION_BASED
          FROM   #ST_NM_SALES_PROJECTION_MASTER SPM
                 LEFT JOIN (SELECT SPM.METHODOLOGY,
                                   SPM.USER_GROUP,
                                   SPM.CALCULATION_PERIODS,
                                   SPM.CALCULATION_BASED,
                                   SPM.CHECK_RECORD,
                                   ACD.CCP_DETAILS_SID
                            FROM   NM_SALES_PROJECTION_MASTER SPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID) A -- BRINGS THE DATA FROM MAIN TABLE FOR THE APPROVED PROJECTION
                        ON SPM.CCP_DETAILS_SID = A.CCP_DETAILS_SID')

          EXEC sp_executesql @SQL1

          --------------------------ST_NM_SALES_PROJECTION_MASTER INSERTION END HERE --------------------------------
          ------------------------------------------TO PULL ACTUALS FOR NEWLY/EDITED CCP STARTS HERE-------------------------
          IF Object_id('TEMPDB..#ST_NM_ACTUAL_SALES') IS NOT NULL
            DROP TABLE #ST_NM_ACTUAL_SALES

          CREATE TABLE #ST_NM_ACTUAL_SALES
            (
               PROJECTION_DETAILS_SID INT,
               PERIOD_SID             INT,
               ACTUAL_SALES           NUMERIC(22, 6),
               ACTUAL_UNITS           NUMERIC(22, 6)
            )

        DECLARE @ACTUALS_CCP UDT_CCP_DETAILS

          INSERT INTO @ACTUALS_CCP
                      (CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID,
                       PROJECTION_DETAILS_SID)
          SELECT CONTRACT_MASTER_SID,
                 COMPANY_MASTER_SID,
                 ITEM_MASTER_SID,
                 a.PROJECTION_DETAILS_SID
          FROM   PROJECTION_DETAILS A
                 INNER JOIN CCP_DETAILS C
                         ON A.CCP_DETAILS_SID = C.CCP_DETAILS_SID where a.projection_master_sid = @projection 

				


          INSERT INTO #ST_NM_ACTUAL_SALES
                      (PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS)
          SELECT PROJECTION_DETAILS_SID,
                 PERIOD_SID,
                 MAX(SALES)    ACTUAL_SALES,
                 MAX(QUANTITY) ACTUAL_UNITS
          FROM   [DBO].[UDF_ACTUALS_DETAILS](@ACTUALS_CCP,  @START_FROM,  @FROM_DATE)
          WHERE  QUANTITY_INCLUSION = 'Y'
          GROUP  BY PROJECTION_DETAILS_SID,
                    PERIOD_SID
          ORDER  BY PROJECTION_DETAILS_SID,
                    PERIOD_SID



			


          ------------------------------------------TO PULL ACTUALS FOR NEWLY/EDITED CCP END HERE-------------------------
          --SELECT * FROM  DBO.UDF_APPROVED_CCP_DETAILS(@PROJECTION, 'NON MANDATED') A
          --INNER JOIN NM_ACTUAL_SALES NM
          --ON A.PROJECTION_DETAILS_SID=NM.PROJECTION_DETAILS_SID
          IF Object_id('TEMPDB..#NEWLY_CCP_DETAILS') IS NOT NULL
            DROP TABLE #NEWLY_CCP_DETAILS

          CREATE TABLE #NEWLY_CCP_DETAILS
            (
               PROJECTION_DETAILS_SID INT PRIMARY KEY
            )

          INSERT INTO #NEWLY_CCP_DETAILS
                      (PROJECTION_DETAILS_SID)
          SELECT PROJECTION_DETAILS_SID
          FROM   #ST_NM_SALES_PROJECTION_MASTER
          WHERE  NOT EXISTS (SELECT NM.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_SALES NM
                                                       INNER JOIN PROJECTION_DETAILS PD
                                                               ON NM.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                                WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION
												AND NM.PROJECTION_DETAILS_SID=#ST_NM_SALES_PROJECTION_MASTER.PROJECTION_DETAILS_SID)

          IF Object_id('TEMPDB..#ST_NM_SALES_PROJECTION') IS NOT NULL
            DROP TABLE #ST_NM_SALES_PROJECTION

          CREATE TABLE #ST_NM_SALES_PROJECTION
            (
               PROJECTION_DETAILS_SID INT,
               CCP_DETAILS_SID        INT,
               PERIOD_SID             INT
            )

          SELECT @START_DATE = Dateadd(MONTH, Datediff(MONTH, 0, @START_DATE), 0),
                 @PROJECTION_DATE = Dateadd(MONTH, Datediff(MONTH, 0, @PROJECTION_DATE), 0)

          ---------------------------------------------TO FIND CCP HAVING 0 VALUE OR NOT------------------------------------
          IF Object_id('TEMPDB..#ACTUAL_CCP_DETAILS') IS NOT NULL
            DROP TABLE #ACTUAL_CCP_DETAILS

          CREATE TABLE #ACTUAL_CCP_DETAILS
            (
               PROJECTION_DETAILS_SID INT PRIMARY KEY
            )

          INSERT INTO #ACTUAL_CCP_DETAILS
                      (PROJECTION_DETAILS_SID)
          SELECT PROJECTION_DETAILS_SID
          FROM   #ST_NM_ACTUAL_SALES
          GROUP  BY PROJECTION_DETAILS_SID
          HAVING Isnull(Sum(ACTUAL_SALES), 0) = 0
                 AND Isnull(Sum(ACTUAL_UNITS), 0) = 0

          IF ( @PROJECTION_SAVE_FALG = 0 )
            BEGIN
                UPDATE SS -----------PULL APPROVED ACTUALS FOR NEWLY CREATED PROJECTION
                SET    SS.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID,
                       SS.PERIOD_SID = NM.PERIOD_SID,
                       SS.ACTUAL_SALES = NM.ACTUAL_SALES,
                       SS.ACTUAL_UNITS = NM.ACTUAL_UNITS
                FROM   #ST_NM_ACTUAL_SALES SS
                       INNER JOIN #ACTUAL_CCP_DETAILS S
                               ON SS.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                       INNER JOIN PROJECTION_DETAILS PD
                               ON PD.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                       INNER JOIN #APPROVED_CCP_DETAILS A
                               ON PD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                       INNER JOIN NM_ACTUAL_SALES NM
                               ON A.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                  AND SS.PERIOD_SID = NM.PERIOD_SID
            END
          ELSE
            BEGIN
                UPDATE SS ------------------PULL ACTUALS ACTUALS FOR EDITED PROJECTION
                SET    SS.PROJECTION_DETAILS_SID = O.PROJECTION_DETAILS_SID,
                       SS.PERIOD_SID = O.PERIOD_SID,
                       SS.ACTUAL_SALES = O.ACTUAL_SALES,
                       SS.ACTUAL_UNITS = O.ACTUAL_UNITS
                FROM   #ST_NM_ACTUAL_SALES SS
                       INNER JOIN (SELECT S.PROJECTION_DETAILS_SID,
                                          NM.PERIOD_SID,
                                          NM.ACTUAL_SALES,
                                          NM.ACTUAL_UNITS
                                   FROM   #ACTUAL_CCP_DETAILS S
                                          INNER JOIN NM_ACTUAL_SALES NM
                                                  ON S.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                          INNER JOIN PROJECTION_DETAILS PD
                                                  ON PD.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                   WHERE  PD.PROJECTION_MASTER_SID = @PROJECTION
                                   UNION
                                   SELECT S.PROJECTION_DETAILS_SID,
                                          NM.PERIOD_SID,
                                          NM.ACTUAL_SALES,
                                          NM.ACTUAL_UNITS
                                   FROM   #NEWLY_CCP_DETAILS S
                                          INNER JOIN #ACTUAL_CCP_DETAILS AA
                                                  ON AA.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                          INNER JOIN PROJECTION_DETAILS PD
                                                  ON PD.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                          INNER JOIN #APPROVED_CCP_DETAILS A
                                                  ON PD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                          INNER JOIN NM_ACTUAL_SALES NM
                                                  ON A.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID) O
                               ON SS.PROJECTION_DETAILS_SID = O.PROJECTION_DETAILS_SID
                                  AND SS.PERIOD_SID = O.PERIOD_SID
            END

          DECLARE @SQL3 NVARCHAR(MAX)=''

          SET @SQL3 = Concat(' truncate table ' ,@ACTUAL_TABLE)


		  EXEC sp_executesql @SQL3

          DECLARE @SQL4 NVARCHAR(MAX)=''

          ------------------------------------TEMP ST_NM_ACTUAL_SALES TABLE INSERTION STARTS HERE-----------------------------------
          SET @SQL4 =CONCAT('INSERT INTO ', @ACTUAL_TABLE, '
                      (PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS)
          SELECT PROJECTION_DETAILS_SID,
                 PERIOD_SID,
                 ACTUAL_SALES,
                 ACTUAL_UNITS
          FROM   #ST_NM_ACTUAL_SALES')
	
		  exec sp_executesql @sql4

		      DECLARE @SQL6 NVARCHAR(MAX)=''

        set @sql6 =  concat( 'INSERT INTO #ST_NM_SALES_PROJECTION
                      (PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       PERIOD_SID)
          SELECT PROJECTION_DETAILS_SID,
                 CCP_DETAILS_SID,
                 PERIOD_SID
          FROM   PERIOD
                 CROSS JOIN (SELECT NSPM.PROJECTION_DETAILS_SID,
                                    CCP_DETAILS_SID
                             FROM  #ST_NM_SALES_PROJECTION_MASTER NSPM                                      
                             WHERE  NOT EXISTS (SELECT 1
                                                FROM   ', @PROJECTION_TABLE, ' N
                                                WHERE  N.PROJECTION_DETAILS_SID = NSPM.PROJECTION_DETAILS_SID)) A
          WHERE  PERIOD_DATE BETWEEN ''', @START_DATE, ''' AND ''', @PROJECTION_DATE, '''')

		  EXEC sp_executesql @SQL6

          ------------------------------------TEMP ST_NM_ACTUAL_SALES TABLE INSERTION STARTS HERE-----------------------------------
          --DELETE T
          --FROM #ST_NM_SALES_PROJECTION T
          --INNER JOIN ST_NM_SALES_PROJECTION N
          --     ON N.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID
          --            AND T.PERIOD_SID = N.PERIOD_SID
          --            AND N.USER_ID = @USER_ID
          --            AND N.SESSION_ID = @SESSION_ID
          ------------------------------------TEMP ST_NM_SALES_PROJECTION TABLE INSERTION STARTS HERE-----------------------------------

		  DECLARE @SQL5 NVARCHAR(MAX)=''

         SET @SQL5 = CONCAT('INSERT INTO ', @PROJECTION_TABLE, '
                      (PROJECTION_DETAILS_SID,
                       PERIOD_SID,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                       PROJECTION_SALES,
                       PROJECTION_UNITS)
          SELECT NSP.PROJECTION_DETAILS_SID,
                 NSP.PERIOD_SID,
                 COALESCE(ACCOUNT_GROWTH, 0),
                 COALESCE(PRODUCT_GROWTH, 0),
                 PROJECTION_SALES,
                 PROJECTION_UNITS
          FROM   #ST_NM_SALES_PROJECTION NSP
                 LEFT JOIN (SELECT SPM.ACCOUNT_GROWTH,
                                   SPM.PRODUCT_GROWTH,
                                   SPM.PROJECTION_SALES,
                                   SPM.PROJECTION_UNITS,
                                   SPM.PERIOD_SID,
                                   ACD.CCP_DETAILS_SID
                            FROM   NM_SALES_PROJECTION SPM
                                   INNER JOIN #APPROVED_CCP_DETAILS ACD
                                           ON SPM.PROJECTION_DETAILS_SID = ACD.PROJECTION_DETAILS_SID) A
                        ON NSP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           AND NSP.PERIOD_SID = A.PERIOD_SID')

          --SELECT @SQL 
          EXEC sp_executesql @SQL5
      ------------------------------------TEMP ST_NM_SALES_PROJECTION TABLE INSERTION STARTS HERE-----------------------------------
      END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC Usperrorcollector

          SELECT @ERRORMESSAGE = Error_message(),
                 @ERRORSEVERITY = Error_severity(),
                 @ERRORSTATE = Error_state(),
                 @ERRORPROCEDURE = Error_procedure(),
                 @ERRORLINE = Error_line(),
                 @ERRORNUMBER = Error_number()

          RAISERROR ( @ERRORMESSAGE,-- MESSAGE TEXT.
                      @ERRORSEVERITY,-- SEVERITY.
                      @ERRORSTATE,-- STATE.
                      @ERRORPROCEDURE,-- PROCEDURE_NAME.
                      @ERRORNUMBER,-- ERRORNUMBER
                      @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END 

  GO