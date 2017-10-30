IF OBJECT_ID('PRC_RETURNS_INSERT', 'P') IS NOT NULL
  DROP PROCEDURE PRC_RETURNS_INSERT

GO

CREATE PROCEDURE [dbo].[PRC_RETURNS_INSERT](@PROJECTION INT,
                                            @USER_ID    int,
                                            @SESSION_ID varchar(100))
AS

/**********************************************************************************************************
** File Name		:	PRC_RETURNS_INSERT.sql
** Procedure Name	:	PRC_RETURNS_INSERT
** Description		:	To generate Returns Actuals and Calculated Projection Related Information
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Returns
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
											
** Output Parameters:	NA
** Author Name		:	@Paul
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application to generate Returns Actuals 
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------

*********************************************************************************************************/
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          ----DECLARING VARIABLES STARTS HERE 
        DECLARE @FROM_DATE DATETIME,
                  @TO_DATE   DATETIME
          ---- For  Projection Period Date Variables
 
                ----------------------------------
              DECLARE @MASTER_TABLE     VARCHAR(200) = Concat('ST_RETURNS_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
               @ACTUAL_TABLE     VARCHAR(200) = Concat('ST_RETURNS_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
               @PROJECTION_TABLE VARCHAR(200) = Concat('ST_RETURNS_PROJ_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                ------------------------------
          DECLARE @START_ACTUAL     DATETIME=(SELECT Dateadd(yy, Datediff(yy, 0, Getdate()) - 3, 0)),
                  @END_ACTUAL       DATETIME=(SELECT Dateadd(mm, Datediff(mm, 0, Getdate()), 0) - 1),
                  @START_PERIOD_SID INT,
                  @END_PERIOD_SID   INT,
                  @FORECAST_NAME    VARCHAR(50),
                  @FORECAST_VERSION VARCHAR(15),
                  @ITEM_UDT         UDT_ITEM,
                             @COMPANY_ID INT
              DECLARE @BUSINESS_UNIT INT-----GAL-808
			            ----DECLARING VARIABLES ENDS HERE 
 --------------------PULLING @BUSINESS_UNIT,@COMPANY_ID,@FROM_DATE,@TO_DATE STARTS HERE----------------------
           SELECT @FROM_DATE = DATEADD(dd, 1, EOMONTH(FROM_DATE, -1)),
                 @TO_DATE = DATEADD(dd, 1, EOMONTH(TO_DATE, -1))
                           ,@BUSINESS_UNIT=BUSINESS_UNIT -----GAL-808
                           ,@COMPANY_ID=PM.COMPANY_MASTER_SID------GAL-808
          FROM   PROJECTION_MASTER PM
          WHERE  PROJECTION_MASTER_SID = @PROJECTION
 
          SELECT @START_PERIOD_SID = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = @START_ACTUAL
 
          SELECT @END_PERIOD_SID = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @TO_DATE), 0)
 
  --------------------PULLING @BUSINESS_UNIT,@COMPANY_ID,@FROM_DATE,@TO_DATE ENDS HERE----------------------
  --------------PULLING @FORECAST_NAME,@FORECAST_VERSION STARTS HERE------------------
          SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME,
                       @FORECAST_VERSION = FT.[VERSION]
          FROM   FILE_MANAGEMENT FT
                 INNER JOIN HELPER_TABLE HT
                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
          WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                   AND FT.FROM_PERIOD IS NOT NULL )
                 AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                        OR FT.TO_PERIOD IS NULL )
                 AND HT.LIST_NAME = 'FILE_TYPE'
                 AND HT.[DESCRIPTION] IN ( 'EX-FACTORY SALES' )
                           AND FT.BUSINESS_UNIT=@BUSINESS_UNIT----------------------------GAL-808
                           AND FT.COMPANY=@COMPANY_ID---------------------GAL-808
          ORDER  BY FT.FROM_PERIOD DESC
   --------------PULLING @FORECAST_NAME,@FORECAST_VERSION ENDS HERE------------------
      --------------INSERTING ITEMS STARTS  HERE------------------
          INSERT INTO @ITEM_UDT
                      (ITEM_MASTER_SID)
          SELECT ITEM_MASTER_SID
          FROM   RETURNS_DETAILS
          WHERE  PROJECTION_MASTER_SID = @PROJECTION
       --------------INSERTING ITEMS ENDS  HERE------------------
	   ----------------PULLING INFORMATION FOR RETURNS_MASTER_SID- STARTS HERE-----------------
          IF Object_id('tempdb..#TEMP_RETURNS') IS NOT NULL
            DROP TABLE #TEMP_RETURNS
 
          CREATE TABLE #TEMP_RETURNS
            (
               RETURNS_MASTER_SID            INT,
               RETURNS_DETAILS_SID           INT,
               ITEM_MASTER_SID               INT,
               BRAND_MASTER_SID              INT,
               ORIG_SALE_MONTH               DATETIME,
               CUM_RETURN_UNITS              NUMERIC(22, 6),--P
               ORIG_SALE_UNITS               NUMERIC(22, 6),--M
               ORIG_SALE_DOLLARS             NUMERIC(22, 6),--N
               ASP                           NUMERIC(22, 6),--O
               EXPECTED_RETURN_RATE          NUMERIC(22, 6),
               MAX_EXPIRED_MONTH             DATETIME,
               MAX_EXPIRED_MONS_PLUSCUTOFF   DATETIME,
               AUTHORIZED_GENERIC_START_DATE DATETIME,
               AUTHORIZED_GENERIC_END_DATE   DATETIME
            )
 
          INSERT INTO #TEMP_RETURNS
                      (RETURNS_MASTER_SID,
                       RETURNS_DETAILS_SID,
                       ITEM_MASTER_SID,
                       BRAND_MASTER_SID,
                       ORIG_SALE_MONTH,
                       CUM_RETURN_UNITS,
                       ORIG_SALE_UNITS,
                       ORIG_SALE_DOLLARS,
                       ASP,
                       EXPECTED_RETURN_RATE,
                       MAX_EXPIRED_MONTH,
                       MAX_EXPIRED_MONS_PLUSCUTOFF,
                       AUTHORIZED_GENERIC_START_DATE,
                       AUTHORIZED_GENERIC_END_DATE)
          SELECT A.RETURNS_MASTER_SID,
                 A.RETURNS_DETAILS_SID,
                 A.ITEM_MASTER_SID,
                 A.BRAND_MASTER_SID,
                 A.ORIG_SALE_MONTH,
                 A.CUM_RETURN_UNITS,
                 A.ORIG_SALE_UNITS,
                 A.ORIG_SALE_DOLLARS,
                 A.ASP,
                 A.EXPECTED_RETURN_RATE,
                 A.MAX_EXPIRED_MONTH,
                 A.MAX_EXPIRED_MONS_PLUSCUTOFF,
                 AUTHORIZED_GENERIC_START_DATE,
                 AUTHORIZED_GENERIC_END_DATE
          FROM   (SELECT RM.RETURNS_MASTER_SID,
                         RD.RETURNS_DETAILS_SID,
                         RM.ITEM_MASTER_SID,
                         RM.BRAND_MASTER_SID,
                         RM.ORIG_SALE_MONTH,
                         RM.CUM_RETURN_UNITS,
                         RM.ORIG_SALE_UNITS,
                         RM.ORIG_SALE_DOLLARS,
                         RM.ASP,
                         RM.EXPECTED_RETURN_RATE,
                         RM.MAX_EXPIRED_MONTH,
                         RM.MAX_EXPIRED_MONS_PLUSCUTOFF,
                         IM.AUTHORIZED_GENERIC_START_DATE,
                         IM.AUTHORIZED_GENERIC_END_DATE,
                         ROW_NUMBER()
                           OVER(
                             PARTITION BY RD.ITEM_MASTER_SID, RM.ORIG_SALE_MONTH
                             ORDER BY RM.ITEM_MASTER_SID, RM.ORIG_SALE_MONTH ASC) AS RN
                  FROM   RETURNS_DETAILS RD
                         LEFT JOIN RETURNS_MASTER RM
                                ON RM.ITEM_MASTER_SID = RD.ITEM_MASTER_SID
                         --AND RM.IS_OUTLINER = 0
                         LEFT JOIN ITEM_MASTER IM
                                ON IM.ITEM_MASTER_SID = RD.ITEM_MASTER_SID
                  WHERE  PROJECTION_MASTER_SID = @PROJECTION) A
          WHERE  A.RN = 1
                 AND A.ORIG_SALE_MONTH BETWEEN @START_ACTUAL AND @END_ACTUAL
 ----------------PULLING INFORMATION FOR RETURNS_MASTER_SID- ENDS HERE-----------------
                                 ----------------- EDIT PROCESS_MODE PLUGIN STARTS HERE --------------
    --      DECLARE @SQL VARCHAR(8000)=''
 
    --      SET @SQL = Concat('IF EXISTS (SELECT 1
    --                 FROM   ', @MASTER_TABLE, ' NM
    --                        INNER JOIN PROJECTION_DETAILS P
    --                                ON P.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
    --                 WHERE  PROJECTION_MASTER_SID = ', @PROJECTION, ')
    --        BEGIN
    --            DELETE T ---- TO FIND NEWLY ADDED CCP ALONE
    --            FROM   ##TEMP_RETURNS_MASTER T
    --                   INNER JOIN ', @MASTER_TABLE, ' N
    --                           ON N.PROJECTION_DETAILS_SID = T.PROJECTION_DETAILS_SID
    --        END')
 
    --      EXEC (@sql)
 
                --select @sql
 
 ------------------------PULLING METHODOLOGY STARTS HERE -----------
          IF Object_id('tempdb..#TEMP_RETURNS_MASTER') IS NOT NULL
            DROP TABLE #TEMP_RETURNS_MASTER
 
          CREATE TABLE #TEMP_RETURNS_MASTER
            (
               ID                     INT IDENTITY (1, 1) NOT NULL,
               RETURNS_DETAILS_SID    INT,
               METHODOLOGY            VARCHAR(50),
               METHODOLOGY_START_DATE VARCHAR(50),
               METHODOLOGY_END_DATE   VARCHAR(50),
               CALCULATION_PERIODS    VARCHAR(4000),
               LAG                    INT,
               CLOSED_DATE            DATETIME,
               LOE_DATE               DATETIME
            )
 
          INSERT INTO #TEMP_RETURNS_MASTER
                      (RETURNS_DETAILS_SID,
                       LAG,
                       CLOSED_DATE,
                       LOE_DATE
                      )
          SELECT RD.RETURNS_DETAILS_SID,
                 A.LAG,
                 A.CLOSED_DATE,
                 A.LOE_DATE
            
          FROM   RETURNS_DETAILS RD
                 LEFT JOIN (SELECT A.RETURNS_DETAILS_SID,
                                   Round(( Datediff(DD, A.MAX_EXPIRED_MONTH, A.MAX_EXPIRED_MONS_PLUSCUTOFF) / 30.45 ), 1) AS LAG,
                                   A.MAX_EXPIRED_MONS_PLUSCUTOFF                                                          AS CLOSED_DATE,
                                   A.AUTHORIZED_GENERIC_END_DATE                                                          AS LOE_DATE
                            FROM   #TEMP_RETURNS A
                                   JOIN (SELECT A.RETURNS_DETAILS_SID,
                                                Max(ORIG_SALE_MONTH) AS LATEST_MONTH
                                         FROM   #TEMP_RETURNS A
                                         GROUP  BY A.RETURNS_DETAILS_SID) B
                                     ON A.RETURNS_DETAILS_SID = B.RETURNS_DETAILS_SID
                                        AND A.ORIG_SALE_MONTH = B.LATEST_MONTH) A
                        ON A.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID
          WHERE  RD.PROJECTION_MASTER_SID = @PROJECTION
 
                  DECLARE @SQL1 nVARCHAR(max)=' '
 
        set @SQL1=  concat('INSERT INTO ', @MASTER_TABLE ,'
                      (RETURNS_DETAILS_SID,
                       LAG,
                       CLOSED_DATE,
                       LOE_DATE
                       )
          SELECT RETURNS_DETAILS_SID,
                 LAG,
                 CLOSED_DATE,
                 LOE_DATE
                
          FROM   #TEMP_RETURNS_MASTER TRM
          WHERE  NOT EXISTS(SELECT 1
                            FROM   ', @MASTER_TABLE ,' STRMM
                            WHERE  STRMM.RETURNS_DETAILS_SID = TRM.RETURNS_DETAILS_SID)')   ---- FOR NEWLY ADDED ITEM ALONE  (EDIT MODE)
 
 
          EXEC sp_executesql @SQL1
  ------------------------PULLING METHODOLOGY ENDS HERE -----------
  -----------------------PULLING GTS FILE INFORMATION STARTS HERE--------------------------------
          IF Object_id('tempdb..#TEMP_RETURNS_GTS') IS NOT NULL
            DROP TABLE #TEMP_RETURNS_GTS
 
          CREATE TABLE #TEMP_RETURNS_GTS
            (
               RETURNS_DETAILS_SID INT,
               PERIOD_SID          INT,
               ITEM_MASTER_SID     INT,
               FORECAST_GTS_SALES  NUMERIC(22, 6),
               FORECAST_GTS_UNITS  NUMERIC(22, 6),
               ACTUAL_GTS_SALES    NUMERIC(22, 6),
               ACTUAL_GTS_UNITS    NUMERIC(22, 6)
            )
 
          INSERT INTO #TEMP_RETURNS_GTS(RETURNS_DETAILS_SID,PERIOD_SID,ITEM_MASTER_SID,FORECAST_GTS_SALES,FORECAST_GTS_UNITS,ACTUAL_GTS_SALES,ACTUAL_GTS_UNITS)
          SELECT distinct RD.RETURNS_DETAILS_SID,
                 U.PERIOD_SID,
                 U.ITEM_MASTER_SID,
                 U.FORECAST_GTS_SALES,
                 U.FORECAST_GTS_UNITS,
                 U.ACTUAL_GTS_SALES,
                 U.ACTUAL_GTS_UNITS
          FROM   UDF_GTS_WAC(@ITEM_UDT, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME, @FORECAST_VERSION) U
                 JOIN RETURNS_DETAILS RD
                   ON RD.ITEM_MASTER_SID = U.ITEM_MASTER_SID
          WHERE  RD.PROJECTION_MASTER_SID = @PROJECTION
   -----------------------PULLING GTS FILE INFORMATION ENDS HERE--------------------------------
   -----------------------PULLING PROJECTED_RETURN_PERCENT AND PROJECTED_RPU AND  PROJECTED_RETURN_AMOUNT STARTS HERE---------------------
          IF Object_id('tempdb..#TEMP_RETURNS_PROJECTION_DETAILS') IS NOT NULL
            DROP TABLE #TEMP_RETURNS_PROJECTION_DETAILS
 
          CREATE TABLE #TEMP_RETURNS_PROJECTION_DETAILS
            (
               RETURNS_DETAILS_SID           INT,
               PERIOD_SID                    INT,
               GROWTH_RATE                   NUMERIC(22, 6),
               PROJECTED_RETURN_PERCENT      NUMERIC(22, 6),
               PROJECTED_RPU                 NUMERIC(22, 6),
               PROJECTED_RETURN_AMOUNT       NUMERIC(22, 6),
               PROJECTED_RETURN_UNITS        NUMERIC(22, 6),
               ACTIVE_EXFACTORY_SALES_AMOUNT NUMERIC(22, 6),
               ACTIVE_EXFACTORY_SALES_UNITS  NUMERIC(22, 6)
           
            )
 
          INSERT INTO #TEMP_RETURNS_PROJECTION_DETAILS
                      (RETURNS_DETAILS_SID,
                       PERIOD_SID,
                       ACTIVE_EXFACTORY_SALES_AMOUNT,
                       ACTIVE_EXFACTORY_SALES_UNITS
                       )
          SELECT A.RETURNS_DETAILS_SID,
                 P.PERIOD_SID,
                 ISNULL(B.FORECAST_GTS_SALES, B.ACTUAL_GTS_SALES),
                 ISNULL(B.FORECAST_GTS_UNITS, B.ACTUAL_GTS_UNITS) 
          FROM   #TEMP_RETURNS_MASTER A
                 CROSS JOIN PERIOD P
                 JOIN #TEMP_RETURNS_GTS B
                   ON A.RETURNS_DETAILS_SID = B.RETURNS_DETAILS_SID
                      AND P.PERIOD_SID = B.PERIOD_SID
          WHERE  P.PERIOD_DATE BETWEEN @FROM_DATE AND @TO_DATE


 
                  DECLARE @SQL2 nVARCHAR(max)=''
 
         set @SQL2=concat(' INSERT INTO ', @PROJECTION_TABLE,'
                      (RETURNS_DETAILS_SID,
                       PERIOD_SID,
                       ACTIVE_EXFACTORY_SALES_AMOUNT,
                       ACTIVE_EXFACTORY_SALES_UNITS
                       )
          SELECT TRPD.RETURNS_DETAILS_SID,
                 PERIOD_SID,
                 ACTIVE_EXFACTORY_SALES_AMOUNT,
                 ACTIVE_EXFACTORY_SALES_UNITS
                
          FROM   #TEMP_RETURNS_PROJECTION_DETAILS TRPD
          WHERE  NOT EXISTS(SELECT 1
                            FROM   ',@PROJECTION_TABLE,' STRD
                            WHERE  STRD.RETURNS_DETAILS_SID = TRPD.RETURNS_DETAILS_SID
                                   AND STRD.PERIOD_SID = TRPD.PERIOD_SID)') ---- FOR NEWLY ADDED ITEM ALONE  (EDIT MODE)
EXEC sp_executesql @SQL2
-----------------------PULLING PROJECTED_RETURN_PERCENT AND PROJECTED_RPU AND  PROJECTED_RETURN_AMOUNT ENDS HERE--------------------
----------------------PULLING  ACTUALS FOR THAT PARTICULAR ITEM STARTS HERE----------------------------
          IF Object_id('tempdb..#TEMP_RETURNS_ACTUALS') IS NOT NULL
            DROP TABLE #TEMP_RETURNS_ACTUALS
 
          CREATE TABLE #TEMP_RETURNS_ACTUALS
            (
               RETURNS_DETAILS_SID   INT,
               PERIOD_SID            INT,
               ACTUAL_RETURN_PERCENT NUMERIC(22, 6),
               ACTUAL_RPU            NUMERIC(22, 6),
               ACTUAL_RETURN_AMOUNT  NUMERIC(22, 6),
               ORIG_SALE_UNITS       NUMERIC(22, 6),
               ORIG_SALE_DOLLARS     NUMERIC(22, 6),
               CUM_RETURN_UNITS      NUMERIC(22, 6),
               ASP                   NUMERIC(22, 6),
               EXPECTED_RETURN_RATE  NUMERIC(22, 6)
            )
 
          INSERT INTO #TEMP_RETURNS_ACTUALS
                      (RETURNS_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_RETURN_PERCENT,
                       ACTUAL_RPU,
                       ACTUAL_RETURN_AMOUNT,
                       ORIG_SALE_UNITS,
                       ORIG_SALE_DOLLARS,
                       CUM_RETURN_UNITS,
                       ASP,
                       EXPECTED_RETURN_RATE
                       )
          SELECT B.RETURNS_DETAILS_SID,
                 B.PERIOD_SID,
                 COALESCE(A.CUM_RETURN_UNITS / NULLIF(A.ORIG_SALE_UNITS, 0), 0) * 100                                                                                AS ACTUAL_RETURN_PERCENT,
                 COALESCE(( ( COALESCE(A.CUM_RETURN_UNITS / NULLIF(A.ORIG_SALE_UNITS, 0), 0) ) * ISNULL(A.ORIG_SALE_DOLLARS, 0) ) / NULLIF(A.ORIG_SALE_UNITS, 0), 0) AS ACTUAL_RPU,
                 ISNULL(A.CUM_RETURN_UNITS, 0) * ISNULL(A.ASP, 0)                                                                                                    AS ACTUAL_RETURN_AMOUNT,
                 A.ORIG_SALE_UNITS,
                 A.ORIG_SALE_DOLLARS,
                 A.CUM_RETURN_UNITS,
                 A.ASP,
                 A.EXPECTED_RETURN_RATE
               
          FROM   #TEMP_RETURNS A
                 RIGHT JOIN (SELECT TRM.RETURNS_DETAILS_SID,
                                    P.PERIOD_SID,
                                    P.PERIOD_DATE
                             FROM   #TEMP_RETURNS_MASTER TRM
                                    CROSS JOIN PERIOD P
                             WHERE  P.PERIOD_DATE BETWEEN @START_ACTUAL AND @END_ACTUAL)B
                         ON A.RETURNS_DETAILS_SID = B.RETURNS_DETAILS_SID
                            AND A.ORIG_SALE_MONTH = B.PERIOD_DATE
    DECLARE @SQL3 nVARCHAR(max)=''
 
 ----------------------PULLING  ACTUALS FOR THAT PARTICULAR ITEM ENDS HERE----------------------------
 -----------------------------INSERTING INTO MAIN TABLE STARTS HERE-------------------------------
              set @sql3=concat('INSERT INTO ', @ACTUAL_TABLE ,'
                      (RETURNS_DETAILS_SID,
                       PERIOD_SID,
                       ACTUAL_RETURN_PERCENT,
                       ACTUAL_RPU,
                       ACTUAL_RETURN_AMOUNT,
                       ORIG_SALE_UNITS,
                       ORIG_SALE_DOLLARS,
                       CUM_RETURN_UNITS,
                       ASP,
                       EXPECTED_RETURN_RATE
                       )
          SELECT RETURNS_DETAILS_SID,
                 PERIOD_SID,
                 ACTUAL_RETURN_PERCENT,
                 ACTUAL_RPU,
                 ACTUAL_RETURN_AMOUNT,
                 ORIG_SALE_UNITS,
                 ORIG_SALE_DOLLARS,
                 CUM_RETURN_UNITS,
                 ASP,
                 EXPECTED_RETURN_RATE
          FROM   #TEMP_RETURNS_ACTUALS TRA
          WHERE  NOT EXISTS(SELECT 1
                            FROM   ',@ACTUAL_TABLE,' STRD
                            WHERE  STRD.RETURNS_DETAILS_SID = TRA.RETURNS_DETAILS_SID
                                   AND STRD.PERIOD_SID = TRA.PERIOD_SID)'
                                   ) ---- FOR NEWLY ADDED ITEM ALONE  (EDIT MODE)
EXEC sp_executesql @sql3
 -----------------------------INSERTING INTO MAIN TABLE ENDS HERE-------------------------------
       END TRY

      BEGIN CATCH
          DECLARE @ErrorMessage NVARCHAR(4000);
          DECLARE @ErrorSeverity INT;
          DECLARE @ErrorState INT;
          DECLARE @ErrorNumber INT;
          DECLARE @ErrorProcedure VARCHAR(200);
          DECLARE @Errorline INT;

          EXEC USPERRORCOLLECTOR

          SELECT @ErrorMessage = ERROR_MESSAGE(),
                 @ErrorSeverity = ERROR_SEVERITY(),
                 @ErrorState = ERROR_STATE(),
                 @ErrorProcedure = ERROR_PROCEDURE(),
                 @Errorline = ERROR_LINE(),
                 @ErrorNumber = ERROR_NUMBER()

          RAISERROR (@ErrorMessage,-- Message text.
                     @ErrorSeverity,-- Severity.
                     @ErrorState,-- State.
                     @ErrorProcedure,-- Procedure_Name.
                     @ErrorNumber,-- ErrorNumber
                     @Errorline -- ErrorLine
          );
      END CATCH
  END

