IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_RETURNS_REFRESH'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_RETURNS_REFRESH]
  END

GO

CREATE PROCEDURE [dbo].[PRC_RETURNS_REFRESH] (@PROJECTION_MASTER_SID INT,
                                             @SELECTED_ITEMS        VARCHAR(4000),
                                             @REFRESHED_PERIODS     VARCHAR(4000),
                                             @MAS_FLAG              BIT = 0,
                                             @USER_ID               INT,
                                             @SESSION_ID            varchar(100))
AS

/**********************************************************************************************************
** File Name		:	PRC_RETURNS_REFRESH.sql
** Procedure Name	:	PRC_RETURNS_REFRESH
** Description		:	To Calculate Remaining fields based on User Overroided Value in Returns Projection(Refresh functionality).
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Returns
                        @SELECTED_ITEMS         - Items Selected for Returns Refresh 
						@REFRESHED_PERIODS      - Period Selected for Refresh
				        @USER_ID                - it is the identification of the User Performing particular projection
						@MAS_FLAG               - it represents Overided in screen or not
						@SESSION_ID             - it is the identification of session for particular projection
											
** Output Parameters:	NA
** Author Name		:	@Ajay
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application for Returns Refresh Functionality for Calculate.
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------

*********************************************************************************************************/

  BEGIN
      BEGIN TRY
	      SET NOCOUNT ON
		   ----DECLARING VARIABLES STARTS HERE 
          DECLARE @ITEM_UDT            UDT_ITEM,
                  @PROJECTION_END_DATE DATETIME,
                  @START_PERIOD_SID    INT,
                  @END_PERIOD_SID      INT,
                  @FORECAST_NAME       VARCHAR(50),
                  @FORECAST_VERSION    VARCHAR(15),
                  @STRING              VARCHAR(2),
                  @CNT                 INT=0,
                  @I                   INT=1,
                  @VALUE               INT,
                  @SELECTED_PERIOD     VARCHAR(10),
                  @FLAG                CHAR(1)
				  ,@BUSINESS_UNIT INT ----------------GAL-808-
				  ,@COMPANY_ID INT ------------------GAL-808
  ----------------------------------
  		            ----DECLARING VARIABLES ENDS HERE 
 --------------------PULLING @BUSINESS_UNIT,@COMPANY_ID,@FROM_DATE,@TO_DATE STARTS HERE----------------------
		 DECLARE @PROJECTION_TABLE VARCHAR(200) = Concat('ST_RETURNS_PROJ_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
		  ------------------------------
          SELECT @PROJECTION_END_DATE = TO_DATE,
		         @BUSINESS_UNIT=BUSINESS_UNIT 
				,@COMPANY_ID=COMPANY_MASTER_SID----------------GAL-808-
          FROM   PROJECTION_MASTER 
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          SELECT @START_PERIOD_SID = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(YY, -3, Dateadd(YY, Datediff(YY, 0, Getdate()), 0))

          SELECT @END_PERIOD_SID = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_END_DATE), 0)
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
				 AND FT.BUSINESS_UNIT=@BUSINESS_UNIT---GAL-808
				 AND FT.COMPANY=@COMPANY_ID
          ORDER  BY FT.FROM_PERIOD DESC

          INSERT INTO @ITEM_UDT
                      (ITEM_MASTER_SID)
          SELECT ITEM_MASTER_SID
          FROM   RETURNS_DETAILS
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

 --------------PULLING @FORECAST_NAME,@FORECAST_VERSION ENDS HERE------------------
      ---------PULLING EXFACTORY SALES AND UNITS STARTS HERE-------------------------
          IF Object_id('TEMPDB..#TEMP_EXFACTORY') IS NOT NULL
            DROP TABLE #TEMP_EXFACTORY

          CREATE TABLE #TEMP_EXFACTORY
            (
               RETURNS_DETAILS_SID INT,
               ITEM_MASTER_SID     INT,
               PERIOD_SID          INT,
               ACTUAL_GTS_SALES    NUMERIC(22, 6),
               ACTUAL_GTS_UNITS    NUMERIC(22, 6),
               FORECAST_GTS_SALES  NUMERIC(22, 6),
               FORECAST_GTS_UNITS  NUMERIC(22, 6),
               ACTUAL_PRICE        NUMERIC(22, 6),
               FORECAST_PRICE      NUMERIC(22, 6)
            )

          INSERT INTO #TEMP_EXFACTORY
                      (RETURNS_DETAILS_SID,
                       ITEM_MASTER_SID,
                       PERIOD_SID,
                       ACTUAL_GTS_SALES,
                       ACTUAL_GTS_UNITS,
                       FORECAST_GTS_SALES,
                       FORECAST_GTS_UNITS,
                       ACTUAL_PRICE,
                       FORECAST_PRICE)
          SELECT RD.RETURNS_DETAILS_SID,
                 RD.ITEM_MASTER_SID,
                 PERIOD_SID,
                 ACTUAL_GTS_SALES,
                 ACTUAL_GTS_UNITS,
                 FORECAST_GTS_SALES,
                 FORECAST_GTS_UNITS,
                 ACTUAL_PRICE,
                 FORECAST_PRICE
          FROM   Udf_gts_wac(@ITEM_UDT,@START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME, @FORECAST_VERSION) U
                 JOIN RETURNS_DETAILS RD
                   ON RD.ITEM_MASTER_SID = U.ITEM_MASTER_SID
          WHERE  RD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
      ---------PULLING EXFACTORY SALES AND UNITS ENDS HERE-------------------------
--------------------PULLING FLAG LEVEL INFORMATION STARTS HERE---------------------------------------------
          IF Object_id('TEMPDB..#TEMP_LOOP') IS NOT NULL
            DROP TABLE #TEMP_LOOP

          CREATE TABLE #TEMP_LOOP
            (
               ID     INT IDENTITY(1, 1) NOT NULL,
               PERIOD VARCHAR(10),
               FLAG   CHAR(1)
            )

          INSERT INTO #TEMP_LOOP
                      (PERIOD,
                       FLAG)
          SELECT TOKEN,
                 'B'
          FROM   Udf_splitstring(@REFRESHED_PERIODS, ',')
--------------------PULLING FLAG LEVEL INFORMATION ENDS HERE---------------------------------------------
-------------------------FREQUENCY WISE SEPERATION AND DOING SELECTED PERIOD PENDING STARTS HERE------------------------
          IF Object_id('TEMPDB..#TEMP_PERIOD') IS NOT NULL
            DROP TABLE #TEMP_PERIOD

          CREATE TABLE #TEMP_PERIOD
            (
               ID          INT IDENTITY(1, 1),
               PERIOD_SID  INT,
               PERIOD_DATE DATETIME,
               [QUARTER]   INT,
               [YEAR]      INT,
               SEMI_ANNUAL INT,
               [MONTH]     INT,
               FLAG        CHAR(1)
            )

          SELECT @CNT = Count(*)
          FROM   #TEMP_LOOP

          WHILE ( @I <= @CNT )
            BEGIN
                SELECT @SELECTED_PERIOD = PERIOD
                FROM   #TEMP_LOOP
                WHERE  ID = @I

                SELECT @FLAG = FLAG
                FROM   #TEMP_LOOP
                WHERE  ID = @I

                SET @STRING=(SELECT LEFT(@SELECTED_PERIOD, 1))

                SELECT @STRING = CASE
                                   WHEN TRY_CONVERT(INT,@STRING) IS NOT NULL THEN 'A'
                                   WHEN @STRING = 'Q' THEN 'Q'
                                   WHEN @STRING = 'S' THEN
                                     CASE
                                       WHEN TRY_CONVERT(INT,Substring(@SELECTED_PERIOD, 2, 1)) IS NOT NULL THEN 'S'
                                       ELSE 'M'
                                     END
                                   ELSE 'M'
                                 END

                SELECT @VALUE = CASE
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'JAN' THEN 1
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'FEB' THEN 2
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'MAR' THEN 3
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'APR' THEN 4
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'MAY' THEN 5
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'JUN' THEN 6
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'JUL' THEN 7
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'AUG' THEN 8
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'SEP' THEN 9
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'OCT' THEN 10
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'NOV' THEN 11
                                  WHEN LEFT (@SELECTED_PERIOD, 3) = 'DEC' THEN 12
                                END

                IF ( @STRING = 'M' )
                  BEGIN
                      SELECT @SELECTED_PERIOD = Stuff(@SELECTED_PERIOD, 1, 3, 'M' + CONVERT(VARCHAR(2), @VALUE))
                  END
                ELSE IF ( @STRING = 'A' )
                  BEGIN
                      SELECT @SELECTED_PERIOD = 'A0 ' + @SELECTED_PERIOD
                  END

                INSERT INTO #TEMP_PERIOD
                            (PERIOD_SID,
                             QUARTER,
                             YEAR,
                             SEMI_ANNUAL,
                             MONTH,
                             PERIOD_DATE,
                             FLAG)
                SELECT PERIOD_SID,
                       QUARTER,
                       YEAR,
                       SEMI_ANNUAL,
                       MONTH,
                       PERIOD_DATE,
                       @FLAG
                FROM   PERIOD
                WHERE  @STRING + CONVERT(VARCHAR(2), CASE WHEN @STRING='Q' THEN QUARTER WHEN @STRING='S' THEN SEMI_ANNUAL WHEN @STRING ='M' THEN MONTH WHEN @STRING='A' THEN 0 END ) + ' ' + CONVERT(VARCHAR(4), YEAR) = @SELECTED_PERIOD

                SET @I+=1
            END
-------------------------FREQUENCY WISE SEPERATION AND DOING SELECTED PERIOD PENDING STARTS HERE------------------------
-------------------------PULLING PROJECTED_RETURN_PERCENT,PROJECTED_RETURN_UNITS,PROJECTED_RETURN_AMOUNT STARTS HERE------------------------
          IF Object_id('TEMPDB..#TEMP_MASTER') IS NOT NULL
            DROP TABLE #TEMP_MASTER

          CREATE TABLE #TEMP_MASTER
            (
               RETURNS_DETAILS_SID      INT,
               PERIOD_SID               INT,
               PROJECTED_RETURN_PERCENT NUMERIC(22, 6),
               PROJECTED_RPU            NUMERIC(22, 6),
               PROJECTED_RETURN_UNITS   NUMERIC(22, 6),
               PROJECTED_RETURN_AMOUNT  NUMERIC(22, 6),
               REFRESHED_NAME           VARCHAR(50),
            )
DECLARE @SQL1 NVARCHAR(MAX)=' '
          IF @MAS_FLAG = 0
            BEGIN
                SET @SQL1=CONCAT('INSERT INTO #TEMP_MASTER
                            (RETURNS_DETAILS_SID,
                             PERIOD_SID,
                             PROJECTED_RETURN_PERCENT,
                             PROJECTED_RPU,
                             PROJECTED_RETURN_UNITS,
                             PROJECTED_RETURN_AMOUNT,
                             REFRESHED_NAME)
                SELECT ST.RETURNS_DETAILS_SID,
                       ST.PERIOD_SID,
                       PROJECTED_RETURN_PERCENT,
                       PROJECTED_RPU,
                       PROJECTED_RETURN_UNITS,
                       PROJECTED_RETURN_AMOUNT,
                       REFRESHED_NAME
                FROM   ',@PROJECTION_TABLE,' ST
                       JOIN #TEMP_PERIOD TP
                         ON ST.PERIOD_SID = TP.PERIOD_SID
                       JOIN RETURNS_DETAILS RD
                         ON RD.RETURNS_DETAILS_SID = ST.RETURNS_DETAILS_SID
                       JOIN (SELECT CONVERT(INT, TOKEN) AS RETURN_DETAILS_SID
                             FROM   Udf_splitstring(''',@SELECTED_ITEMS,''', '','')) U
                         ON U.RETURN_DETAILS_SID = ST.RETURNS_DETAILS_SID
                WHERE  RD.PROJECTION_MASTER_SID = ',@PROJECTION_MASTER_SID)
	
            EXEC sp_executesql @SQL1
            END
	-------------------------PULLING PROJECTED_RETURN_PERCENT,PROJECTED_RETURN_UNITS,PROJECTED_RETURN_AMOUNT ENDS HERE------------------------	
	-------------------------PULLING PROJECTED_RETURN_PERCENT,PROJECTED_RETURN_UNITS,PROJECTED_RETURN_AMOUNT STARTS HERE------------------------	
          ELSE IF @MAS_FLAG = 1
            BEGIN
                DECLARE @START_PERIOD INT,
                        @END_PERIOD   INT

                SELECT @START_PERIOD = Min(PERIOD_SID)
                FROM   #TEMP_PERIOD

                SELECT @END_PERIOD = Max(PERIOD_SID)
                FROM   #TEMP_PERIOD

                SET @SQL1=CONCAT('INSERT INTO #TEMP_MASTER
                            (RETURNS_DETAILS_SID,
                             PERIOD_SID,
                             PROJECTED_RETURN_PERCENT,
                             PROJECTED_RPU,
                             PROJECTED_RETURN_UNITS,
                             PROJECTED_RETURN_AMOUNT,
                             REFRESHED_NAME)
                SELECT ST.RETURNS_DETAILS_SID,
                       ST.PERIOD_SID,
                       PROJECTED_RETURN_PERCENT,
                       PROJECTED_RPU,
                       PROJECTED_RETURN_UNITS,
                       PROJECTED_RETURN_AMOUNT,
                       REFRESHED_NAME
                FROM   ',@PROJECTION_TABLE,' ST
                       JOIN RETURNS_DETAILS RD
                         ON RD.RETURNS_DETAILS_SID = ST.RETURNS_DETAILS_SID
                       JOIN (SELECT CONVERT(INT, TOKEN) AS RETURN_DETAILS_SID
                             FROM   Udf_splitstring(''',@SELECTED_ITEMS,''', '','')) U
                         ON U.RETURN_DETAILS_SID = ST.RETURNS_DETAILS_SID
                WHERE  RD.PROJECTION_MASTER_SID = ',@PROJECTION_MASTER_SID,'
                       AND ST.PERIOD_SID BETWEEN ',@START_PERIOD,' AND ',@END_PERIOD)

				
                EXEC sp_executesql @SQL1
            END
	-------------------------PULLING PROJECTED_RETURN_PERCENT,PROJECTED_RETURN_UNITS,PROJECTED_RETURN_AMOUNT ENDS HERE------------------------	
		-------------------------BASED ON REFRESHED_NAME PROJECTED_RETURN_AMOUNT ,PROJECTED_RPU,PROJECTED_RETURN_AMOUNT STARTS HERE---------------------	
          IF Object_id('TEMPDB..#TEMP_DETAILS') IS NOT NULL
            DROP TABLE #TEMP_DETAILS

          CREATE TABLE #TEMP_DETAILS
            (
               RETURNS_DETAILS_SID      INT,
               PERIOD_SID               INT,
               PROJECTED_RETURN_PERCENT NUMERIC(22, 6),
               PROJECTED_RPU            NUMERIC(22, 6),
               PROJECTED_RETURN_UNITS   NUMERIC(22, 6),
               PROJECTED_RETURN_AMOUNT  NUMERIC(22, 6),
               REFRESHED_NAME           VARCHAR(50)
            )

          INSERT INTO #TEMP_DETAILS
                      (RETURNS_DETAILS_SID,
                       PERIOD_SID,
                       PROJECTED_RETURN_PERCENT,
                       PROJECTED_RPU,
                       PROJECTED_RETURN_UNITS,
                       PROJECTED_RETURN_AMOUNT,
                       REFRESHED_NAME)
          SELECT TM.RETURNS_DETAILS_SID,
                 TM.PERIOD_SID,
                 CASE
                                            WHEN REFRESHED_NAME = 'PROJECTED_RETURN_AMOUNT' THEN ( TM.PROJECTED_RETURN_AMOUNT / NULLIF(Isnull(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES), 0) ) 
                                            WHEN REFRESHED_NAME = 'PROJECTED_RPU' THEN ( TM.PROJECTED_RPU * Isnull(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS) )/NULLIF(Isnull(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES), 0)
                                            ELSE TM.PROJECTED_RETURN_PERCENT
                                          END AS PROJECTED_RETURN_PERCENT,
                 CASE
                                  WHEN REFRESHED_NAME = 'PROJECTED_RETURN_AMOUNT' THEN TM.PROJECTED_RETURN_AMOUNT / NULLIF(Isnull(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS), 0)
                                  WHEN REFRESHED_NAME = 'PROJECTED_RETURN_PERCENT' THEN ( COALESCE(TM.PROJECTED_RETURN_PERCENT/100.00,0) * Isnull(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) ) / NULLIF(Isnull(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS), 0)
                                  ELSE TM.PROJECTED_RPU
                                END AS PROJECTED_RPU,
								 CASE
                                           WHEN REFRESHED_NAME = 'PROJECTED_RETURN_AMOUNT' THEN TM.PROJECTED_RETURN_AMOUNT / NULLIF(Isnull(TE.FORECAST_PRICE, TE.ACTUAL_PRICE), 0)
                                           WHEN REFRESHED_NAME = 'PROJECTED_RETURN_PERCENT' THEN ( COALESCE(TM.PROJECTED_RETURN_PERCENT/100.00,0) * Isnull(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) ) / NULLIF(Isnull(TE.FORECAST_PRICE, TE.ACTUAL_PRICE), 0)
                                           WHEN REFRESHED_NAME = 'PROJECTED_RPU' THEN ( TM.PROJECTED_RPU * Isnull(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS) ) / NULLIF(Isnull(TE.FORECAST_PRICE, TE.ACTUAL_PRICE), 0)
                                           ELSE TM.PROJECTED_RETURN_UNITS
                                         END AS PROJECTED_RETURN_UNITS,
                 CASE
                                            WHEN REFRESHED_NAME = 'PROJECTED_RETURN_PERCENT' THEN COALESCE(TM.PROJECTED_RETURN_PERCENT/100.00,0) * Isnull(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES)
                                            WHEN REFRESHED_NAME = 'PROJECTED_RPU' THEN TM.PROJECTED_RPU * Isnull(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS)
                                            ELSE TM.PROJECTED_RETURN_AMOUNT
                                          END AS PROJECTED_RETURN_AMOUNT,
                
                 TM.REFRESHED_NAME
          FROM   #TEMP_MASTER TM
                 JOIN #TEMP_EXFACTORY TE
                   ON TE.RETURNS_DETAILS_SID = TM.RETURNS_DETAILS_SID
                      AND TE.PERIOD_SID = TM.PERIOD_SID

          SET @SQL1='UPDATE A
          SET    A.PROJECTED_RETURN_PERCENT = TD.PROJECTED_RETURN_PERCENT,
                 A.PROJECTED_RPU = TD.PROJECTED_RPU,
                 A.PROJECTED_RETURN_AMOUNT = TD.PROJECTED_RETURN_AMOUNT,
                 A.PROJECTED_RETURN_UNITS = TD.PROJECTED_RETURN_UNITS
          FROM   ' +@PROJECTION_TABLE+ ' A
                 JOIN #TEMP_DETAILS TD
                   ON TD.RETURNS_DETAILS_SID = A.RETURNS_DETAILS_SID
                      AND TD.PERIOD_SID = A.PERIOD_SID
                      AND TD.REFRESHED_NAME = A.REFRESHED_NAME'
               EXEC sp_executesql @SQL1
              
    		-------------------------BASED ON REFRESHED_NAME PROJECTED_RETURN_AMOUNT ,PROJECTED_RPU,PROJECTED_RETURN_AMOUNT ENDS HERE---------------------
          END TRY

      BEGIN CATCH
          DECLARE @ErrorMessage NVARCHAR(4000);
          DECLARE @ErrorSeverity INT;
          DECLARE @ErrorState INT;
          DECLARE @ErrorNumber INT;
          DECLARE @ErrorProcedure VARCHAR(200);
          DECLARE @Errorline INT;

          EXEC Usperrorcollector

          SELECT @ErrorMessage = Error_message(),
                 @ErrorSeverity = Error_severity(),
                 @ErrorState = Error_state(),
                 @ErrorProcedure = Error_procedure(),
                 @Errorline = Error_line(),
                 @ErrorNumber = Error_number()

          RAISERROR (@ErrorMessage,-- Message text.
                     @ErrorSeverity,-- Severity.
                     @ErrorState,-- State.
                     @ErrorProcedure,-- Procedure_Name.
                     @ErrorNumber,-- ErrorNumber
                     @Errorline -- ErrorLine
          );
      END CATCH
  END





