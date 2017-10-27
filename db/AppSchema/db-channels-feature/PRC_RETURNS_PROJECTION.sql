IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_RETURNS_PROJECTION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_RETURNS_PROJECTION]
  END

GO

CREATE PROCEDURE [dbo].[PRC_RETURNS_PROJECTION](@PROJECTION_MASTER_SID INT,
                                                @USER_ID               INT,
                                                @SESSION_ID            VARCHAR(100),
                                                @FREQUENCY1            CHAR(1))
AS

/**********************************************************************************************************
** File Name		:	PRC_RETURNS_PROJECTION.sql
** Procedure Name	:	PRC_RETURNS_PROJECTION
** Description		:	To generate Returns Projections based on Methodolgy Seleted in Returns UI.
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Returns
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
						@FREQUENCY1             - User Selected Frequency in Returns Projection Screen
											
** Output Parameters:	NA
** Author Name		:	@Tharun
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application to generate Returns Projections
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------

*********************************************************************************************************/

  BEGIN
      SET NOCOUNT ON
	  ---------------DECLARING VARIABLES  STARTS HERE----------------------
      BEGIN TRY
          DECLARE @METHODOLOGY            VARCHAR(50),
                  @ITEM_UDT               UDT_ITEM,
                  @PROJECTION_END_DATE    DATETIME,
                  @START_PERIOD_SID       INT,
                  @END_PERIOD_SID         INT,
                  @FORECAST_NAME          VARCHAR(50),
                  @FORECAST_VERSION       VARCHAR(15),
                  @CALCULATION_PERIODS    VARCHAR(4000),
                  @METHODOLOGY_START_SID  INT,
                  @METHODOLOGY_END_SID    INT,
                  @BASE_LINE_VALUE        NUMERIC(22, 6),
                  @BUSINESS_UNIT          INT,
                  @COMPANY_ID             INT

	  ---------------DECLARING VARIABLES  ENDS HERE----------------------
	   --------------------PULLING @BUSINESS_UNIT,@COMPANY_ID,@FROM_DATE,@TO_DATE STARTS HERE----------------------
          ------------------------------------GAL-808-------------------------------
          SELECT @PROJECTION_END_DATE = TO_DATE,
                 @BUSINESS_UNIT = BUSINESS_UNIT,
                 @COMPANY_ID = PM.COMPANY_MASTER_SID
          FROM   PROJECTION_MASTER PM
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          -----------------------------------------------------------------------
          DECLARE @MASTER_TABLE         VARCHAR(200) = Concat('ST_RETURNS_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ACTUAL_TABLE         VARCHAR(200) = Concat('ST_RETURNS_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @PROJECTION_TABLE     VARCHAR(200) = Concat('ST_RETURNS_PROJ_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @RETURNS_GROWTH_TABLE VARCHAR(200) = Concat('ST_RETURNS_GROWTH_FACTOR_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

          ----------------------------------------------------------------------
          SELECT @START_PERIOD_SID = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(YY, -3, Dateadd(YY, Datediff(YY, 0, Getdate()), 0))

          SELECT @END_PERIOD_SID = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_END_DATE), 0)
		   DECLARE @SQL1 NVARCHAR(MAX)=' '
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
                 AND FT.BUSINESS_UNIT = @BUSINESS_UNIT--------GAL-808
                 AND FT.COMPANY = @COMPANY_ID
          ORDER  BY FT.FROM_PERIOD DESC
		     --------------PULLING @FORECAST_NAME,@FORECAST_VERSION ENDS HERE------------------
      --------------INSERTING ITEMS STARTS  HERE------------------
          INSERT INTO @ITEM_UDT
                      (ITEM_MASTER_SID)
          SELECT ITEM_MASTER_SID
          FROM   RETURNS_DETAILS
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
		         --------------INSERTING ITEMS ENDS  HERE------------------
			----------	 PULLING GROWTH VALUE STARTS HERE------------
		  SET @SQL1= CONCAT('
            IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''',@RETURNS_GROWTH_TABLE,''' 
                      AND TABLE_SCHEMA = ''DBO'')
					
           CREATE TABLE ',@RETURNS_GROWTH_TABLE,'
                   (
				   RETURNS_DETAILS_SID     INT,
				   PERIOD_SID          INT,
				   GROWTH              NUMERIC(22,15)

				   )'

)


EXEC sp_executesql @SQL1
			----------	 PULLING GROWTH VALUE ENDS HERE------------
			------------PULLING  EXFACTORY SALES AND UNITS STARTS HERE---------------------------
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
          SELECT DISTINCT RD.RETURNS_DETAILS_SID,
                          RD.ITEM_MASTER_SID,
                          PERIOD_SID,
                          ACTUAL_GTS_SALES,
                          ACTUAL_GTS_UNITS,
                          FORECAST_GTS_SALES,
                          FORECAST_GTS_UNITS,
                          ACTUAL_PRICE,
                          FORECAST_PRICE
          FROM   Udf_gts_wac(@ITEM_UDT, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME, @FORECAST_VERSION) U
                 JOIN RETURNS_DETAILS RD
                   ON RD.ITEM_MASTER_SID = U.ITEM_MASTER_SID
          WHERE  RD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
		------------PULLING  EXFACTORY SALES AND UNITS ENDS HERE---------------------------
  ---------------------------BASED ON FREQUENCY CALUCLATIONS SHOLUD HAPPEN- STARTS HERE--------------------------
          IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
            DROP TABLE #PERIOD;

          SELECT PERIOD_SID,
                 PERIOD_DATE,
                 MONTH AS PERIOD_MONTH,
                 QUARTER AS PERIOD_QUARTER,
                 SEMI_ANNUAL AS PERIOD_SEMI,
                 YEAR AS PERIOD_YEAR, 
                 CASE
                            WHEN @FREQUENCY1 = 'M' THEN Concat ('M', MONTH, ' ', YEAR)
                            WHEN @FREQUENCY1 = 'Q' THEN Concat ('Q', QUARTER, ' ', YEAR)
                            WHEN @FREQUENCY1 = 'S' THEN Concat ('S', SEMI_ANNUAL, ' ', YEAR)
                            ELSE Cast(YEAR AS CHAR(4))
                          END AS PERIOD,
                 MONTH AS ROLL_PERIOD
          INTO   #PERIOD
          FROM   PERIOD
  ---------------------------BASED ON CHOOSING  METHODOLOGY  STARTS HERE---------------------------
          IF Object_id('TEMPDB..#TEMP_MASTER') IS NOT NULL
            DROP TABLE #TEMP_MASTER

          CREATE TABLE #TEMP_MASTER
            (
               ID                     INT IDENTITY(1, 1),
               RETURNS_DETAILS_SID    INT,
               ITEM_MASTER_SID        INT,
               METHODOLOGY            VARCHAR(50),
               METHODOLOGY_START_DATE VARCHAR(50),
               METHODOLOGY_END_DATE   VARCHAR(50),
               CALCULATION_PERIODS    VARCHAR(4000)
            )

         

          SET @SQL1 = Concat('INSERT INTO #TEMP_MASTER
                      (RETURNS_DETAILS_SID,
                       ITEM_MASTER_SID,
                       METHODOLOGY,
                       METHODOLOGY_START_DATE,
                       METHODOLOGY_END_DATE,
                       CALCULATION_PERIODS)
		   
		   
          SELECT SRM.RETURNS_DETAILS_SID,
                 RD.ITEM_MASTER_SID,
                 SRM.METHODOLOGY,
                 SRM.METHODOLOGY_START_DATE,
                 SRM.METHODOLOGY_END_DATE,
                 SRM.CALCULATION_PERIODS
				
          FROM   ', @MASTER_TABLE, ' SRM
                 JOIN RETURNS_DETAILS RD
                   ON RD.RETURNS_DETAILS_SID = SRM.RETURNS_DETAILS_SID
          WHERE  RD.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ' AND SRM.CHECK_RECORD = 1
                 ')

          EXEC sp_executesql @SQL1
  ---------------------------BASED ON CHOOSING  METHODOLOGY  ENDS HERE---------------------------
  ------------------------------CHOOSING METHODOLOGY STARTPERIOD AND END PERIOD STARTS HERE-----------

          SELECT TOP 1 @METHODOLOGY = METHODOLOGY
          FROM   #TEMP_MASTER
		  ORDER BY METHODOLOGY
          SELECT @METHODOLOGY_START_SID = Min(P.PERIOD_SID),
                 @METHODOLOGY_END_SID = Max(P1.PERIOD_SID)
          FROM   #TEMP_MASTER TM
                 JOIN #PERIOD P
                   ON P.PERIOD = METHODOLOGY_START_DATE
                 LEFT JOIN #PERIOD P1
                        ON P1.PERIOD = METHODOLOGY_END_DATE

          SELECT TOP 1 @CALCULATION_PERIODS = CALCULATION_PERIODS
          FROM   #TEMP_MASTER
		  ORDER BY CALCULATION_PERIODS
          SET @METHODOLOGY_END_SID=Iif(@METHODOLOGY_END_SID < @END_PERIOD_SID, @METHODOLOGY_END_SID, @END_PERIOD_SID)

          --IF OBJECT_ID('TEMPDB..#TEMP_LOOP') IS NOT NULL
          --  DROP TABLE #TEMP_LOOP
          --CREATE TABLE #TEMP_LOOP
          --  (
          --     ID     INT IDENTITY(1, 1) NOT NULL,
          --     PERIOD VARCHAR(10),
          --     FLAG   CHAR(1)
          --  )
          --INSERT INTO #TEMP_LOOP
          --            (PERIOD,
          --             FLAG)
          --SELECT TOKEN,
          --       'B'
          --FROM   UDF_SPLITSTRING(@CALCULATION_PERIODS, ',')
          --UNION ALL
          --SELECT @METHODOLOGY_START_DATE,
          --       'F'
          --UNION ALL
          --SELECT @METHODOLOGY_END_DATE,
          --       'E'
          --SELECT @FREQUENCY = CASE
          --                      WHEN LEFT(@CALCULATION_PERIODS, 1) = 'Q' THEN 3
          --                      WHEN LEFT(@CALCULATION_PERIODS, 1) = 'S' THEN 6
          --                      WHEN ISNUMERIC(LEFT(@CALCULATION_PERIODS, 1)) = 1 THEN 12
          --                      ELSE 1
          --                    END
		    ------------------------------CHOOSING METHODOLOGY STARTPERIOD AND END PERIOD ENDS HERE-----------
					    ------------------------------COMMASEPERATED VALUES INTO ROWS STARTS HERE-----------
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
               FLAG        CHAR(1),
               FREQ        INT
            )

          --SELECT @CNT = COUNT(*)
          --FROM   #TEMP_LOOP
          --WHILE ( @I <= @CNT )
          --  BEGIN
          --      SELECT @SELECTED_PERIOD = PERIOD
          --      FROM   #TEMP_LOOP
          --      WHERE  ID = @I
          --      SELECT @FLAG = FLAG
          --      FROM   #TEMP_LOOP
          --      WHERE  ID = @I
          --      SELECT @FLAG_FRE = ID
          --      FROM   #TEMP_LOOP
          --      WHERE  ID = @I
          --      SET @STRING=(SELECT LEFT(@SELECTED_PERIOD, 1))
          --      SELECT @STRING = CASE
          --                         WHEN ISNUMERIC(@STRING) = 1 THEN 'A'
          --                         WHEN @STRING = 'Q' THEN 'Q'
          --                         WHEN @STRING = 'S' THEN
          --                           CASE
          --                             WHEN ISNUMERIC(SUBSTRING(@SELECTED_PERIOD, 2, 1)) = 1 THEN 'S'
          --                             ELSE 'M'
          --                           END
          --                         ELSE 'M'
          --                       END
          --      SELECT @VALUE = CASE
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'JAN' THEN 1
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'FEB' THEN 2
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'MAR' THEN 3
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'APR' THEN 4
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'MAY' THEN 5
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'JUN' THEN 6
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'JUL' THEN 7
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'AUG' THEN 8
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'SEP' THEN 9
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'OCT' THEN 10
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'NOV' THEN 11
          --                        WHEN LEFT (@SELECTED_PERIOD, 3) = 'DEC' THEN 12
          --                      END
          --      IF ( @STRING = 'M' )
          --        BEGIN
          --            SELECT @SELECTED_PERIOD = STUFF(@SELECTED_PERIOD, 1, 3, 'M' + CONVERT(VARCHAR(2), @VALUE))
          --        END
          --      ELSE IF ( @STRING = 'A' )
          --        BEGIN
          --            SELECT @SELECTED_PERIOD = 'A0 ' + @SELECTED_PERIOD
          --        END
          --      SET @I+=1
          --  END


          INSERT INTO #TEMP_PERIOD
                      (PERIOD_SID)
          SELECT PERIOD_SID
          FROM   #PERIOD P
                 JOIN (SELECT TOKEN
                       FROM   Udf_splitstring(@CALCULATION_PERIODS, ',')) B
                   ON P.PERIOD = B.TOKEN
		    ------------------------------COMMASEPERATED VALUES INTO ROWS ENDS HERE-----------

          IF Object_id('TEMPDB..#TEMP_DETAILS') IS NOT NULL
            DROP TABLE #TEMP_DETAILS

          CREATE TABLE #TEMP_DETAILS
            (
               RETURNS_DETAILS_SID      INT,
               PERIOD_SID               INT,
               PROJECTED_RETURN_PERCENT NUMERIC(22, 6),
               PROJECTED_RPU            NUMERIC(22, 6),
               PROJECTED_RETURN_UNITS   NUMERIC(22, 6),
               PROJECTED_RETURN_AMOUNT  NUMERIC(22, 6)
            )

          --      IF OBJECT_ID('TEMPDB..#TEMP_UDT') IS NOT NULL
          --        DROP TABLE #TEMP_UDT
          --      CREATE TABLE #TEMP_UDT
          --        (
          --           RETURNS_DETAILS_SID INT,
          --           PERIOD_SID          INT,
          --           GROWTH_RATE         NUMERIC(22, 6)
          --        )
          --      SET @SQL1 = CONCAT('INSERT INTO #TEMP_UDT
          --                  (RETURNS_DETAILS_SID,
          --                   PERIOD_SID,
          --                   GROWTH_RATE)
          --     SELECT RPD.RETURNS_DETAILS_SID,
          --             PERIOD_SID,
          --             GROWTH_RATE
          --      FROM   ', @PROJECTION_TABLE, ' RPD
          --             JOIN RETURNS_DETAILS RD
          --               ON RPD.RETURNS_DETAILS_SID = RD.RETURNS_DETAILS_SID
          --             JOIN ', @MASTER_TABLE, ' ST
          --               ON ST.RETURNS_DETAILS_SID = RPD.RETURNS_DETAILS_SID
          --      WHERE  RD.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, '
          --             ')
          --      EXEC (@SQL1)
          --select 1
		---------------  METHODOLOGY FOR  ( 'SINGLE PERIOD', 'AVERAGE' ) STARTS HERE------------------------
          IF @METHODOLOGY IN( 'SINGLE PERIOD', 'AVERAGE' )
            BEGIN
                ----------BASE LINE CALCULATION STRATS HERE-----------------------------------
                SET @SQL1 = Concat('INSERT INTO #TEMP_DETAILS
                            (RETURNS_DETAILS_SID,
                             PERIOD_SID,
                             PROJECTED_RETURN_PERCENT,
                             PROJECTED_RPU,
                             PROJECTED_RETURN_UNITS,
                             PROJECTED_RETURN_AMOUNT)
                SELECT TE.RETURNS_DETAILS_SID,
                       TE.PERIOD_SID,
                       BASE_LINE_VALUE * isnull( RGT.GROWTH,1 )                                                                                                                                       PROJECTED_RATE_PERCENT,
                       ( ( BASE_LINE_VALUE / 100.00 ) * isnull( RGT.GROWTH,1 )  * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES)) / NULLIF(ISNULL(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS), 0) AS PROJECTED_RPU,
                       ( ( BASE_LINE_VALUE / 100.00 ) * isnull( RGT.GROWTH,1 )  * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES)) / NULLIF(ISNULL(TE.FORECAST_PRICE, TE.ACTUAL_PRICE), 0)         AS PROJECTED_RETURN_UNITS,
                       ( ( BASE_LINE_VALUE / 100.00 ) * isnull( RGT.GROWTH,1 )  * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) )                                                                AS PROJECTED_RETURN_AMOUNT
                FROM   (SELECT B.RETURNS_DETAILS_SID,
                               AVG(BASE_LINE_VALUE) AS BASE_LINE_VALUE
                        FROM   (SELECT CASE
                                         WHEN ''', @METHODOLOGY, ''' = ''SINGLE PERIOD'' THEN ( SUM(STA.CUM_RETURN_UNITS) / SUM(STA.ORIG_SALE_UNITS) ) * 100
                                         ELSE ( SUM(STA.CUM_RETURN_UNITS) / SUM(STA.ORIG_SALE_UNITS) ) * 100
                                       END AS BASE_LINE_VALUE,
                                       TM.RETURNS_DETAILS_SID
                                FROM   ', @ACTUAL_TABLE, '  STA
                                       JOIN #TEMP_MASTER TM
                                         ON STA.RETURNS_DETAILS_SID = TM.RETURNS_DETAILS_SID
                                       JOIN #TEMP_PERIOD TP
                                         ON TP.PERIOD_SID = STA.PERIOD_SID
                             
                                GROUP  BY 
                                          
                                          TM.RETURNS_DETAILS_SID)B
                        GROUP  BY RETURNS_DETAILS_SID)A
                       JOIN #TEMP_EXFACTORY TE
                         ON TE.RETURNS_DETAILS_SID = A.RETURNS_DETAILS_SID
					   LEFT JOIN ', @RETURNS_GROWTH_TABLE, ' RGT ON RGT.RETURNS_DETAILS_SID=TE.RETURNS_DETAILS_SID AND TE.PERIOD_SID=RGT.PERIOD_SID
                     JOIN #PERIOD P ON P.PERIOD_SID=TE.PERIOD_SID AND P.PERIOD_SID BETWEEN ', @METHODOLOGY_START_SID, ' AND ', @METHODOLOGY_END_SID, '
                     ')

                EXEC sp_executesql @SQL1
            END
			      ----------BASE LINE CALCULATION ENDS HERE-----------------------------------
				---------------  METHODOLOGY FOR  ( 'SINGLE PERIOD', 'AVERAGE' ) STARTS HERE------------------------
				---------------METHODOLOGY FOR '% OF EX-FACTORY' STARTS HERE---------------------------------
          ELSE IF @METHODOLOGY = '% OF EX-FACTORY'
            BEGIN
                SET @SQL1 = Concat('INSERT INTO #TEMP_DETAILS
                            (RETURNS_DETAILS_SID,
                             PERIOD_SID,
                             PROJECTED_RETURN_PERCENT,
                             PROJECTED_RPU,
                             PROJECTED_RETURN_UNITS,
                             PROJECTED_RETURN_AMOUNT)
                SELECT TE.RETURNS_DETAILS_SID,
                       TE.PERIOD_SID,
                       ( ( ( ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) * ISNULL(RETURN_AMOUNT_RATIO, 0) ) / NULLIF(ISNULL(TE.FORECAST_PRICE, TE.ACTUAL_PRICE), 0) ) / NULLIF(ISNULL(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS), 0) ) * 100 AS PROJECTED_RETURN_PERCENT,
                       COALESCE(ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) / NULLIF(ISNULL(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS), 0), 0)                                                                                               AS PROJECTED_RPU,
                       ( ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) * ISNULL(RETURN_AMOUNT_RATIO, 0) ) / NULLIF(ISNULL(TE.FORECAST_PRICE, TE.ACTUAL_PRICE), 0)                                                                               AS PROJECTED_RETURN_UNITS,
                       ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) * ISNULL(RETURN_AMOUNT_RATIO, 0)                                                                                                                                           AS PROJECTED_RETURN_AMOUNT
                FROM   (SELECT ISNULL(SUM(ST.ACTUAL_RETURN_AMOUNT) / NULLIF(SUM(ST.ORIG_SALE_DOLLARS), 0), 0) AS RETURN_AMOUNT_RATIO,
                               TM.ITEM_MASTER_SID,
                               TM.RETURNS_DETAILS_SID
                        FROM   #TEMP_MASTER TM
                               JOIN ', @ACTUAL_TABLE, ' ST
                                 ON ST.RETURNS_DETAILS_SID = TM.RETURNS_DETAILS_SID
                        WHERE  ST.PERIOD_SID IN (SELECT TP.PERIOD_SID
                                                 FROM   #TEMP_PERIOD TP
                                                 )
                         
                        GROUP  BY TM.ITEM_MASTER_SID,
                                  TM.RETURNS_DETAILS_SID) A
                       JOIN #TEMP_EXFACTORY TE
                         ON TE.RETURNS_DETAILS_SID = A.RETURNS_DETAILS_SID
					JOIN #PERIOD P 
					ON P.PERIOD_SID=TE.PERIOD_SID
					WHERE P.PERIOD_SID BETWEEN ', @METHODOLOGY_START_SID, ' AND ', @METHODOLOGY_END_SID)

                EXEC sp_executesql @SQL1
            END

				---------------METHODOLOGY FOR '% OF EX-FACTORY' ENDS HERE---------------------------------
---------------METHODOLOGY FOR  'TIER 1' STARTS HERE---------------------------------
          ELSE IF @METHODOLOGY = 'TIER 1'
            BEGIN
                SET @SQL1 = Concat('SELECT @BASE_LINE_VALUE = SUM(RM.CUM_RETURN_UNITS) / NULLIF(SUM(RM.ORIG_SALE_UNITS), 0)
                FROM   #TEMP_MASTER TM
                       JOIN ', @ACTUAL_TABLE, ' RM
                         ON TM.RETURNS_DETAILS_SID = RM.RETURNS_DETAILS_SID
                       JOIN #TEMP_PERIOD TP
                         ON TP.PERIOD_SID = RM.PERIOD_SID
                       JOIN UDCS UD
                         ON UD.MASTER_SID = TM.ITEM_MASTER_SID
                       JOIN HELPER_TABLE HT
                         ON HT.HELPER_TABLE_SID = UD.UDC1
                WHERE   UD.MASTER_TYPE = ''ITEM_MASTER''
                       AND HT.DESCRIPTION = ''TIER 1''')

                EXEC Sp_executesql
                  @SQL1,
                  N'@BASE_LINE_VALUE NUMERIC(22,6) OUTPUT',
                  @BASE_LINE_VALUE OUTPUT

                SET @SQL1 = Concat('  INSERT INTO #TEMP_DETAILS
                            (RETURNS_DETAILS_SID,
                             PERIOD_SID,
                             PROJECTED_RETURN_PERCENT,
                             PROJECTED_RPU,
                             PROJECTED_RETURN_UNITS,
                             PROJECTED_RETURN_AMOUNT)
                SELECT TE.RETURNS_DETAILS_SID,
                       TE.PERIOD_SID,
                       ( @BASE_LINE_VALUE * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) ) * 100                                                                                                                        AS PROJECTED_RETURN_PERCENT,
                       ( ( ( @BASE_LINE_VALUE ) * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) ) * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) ) / NULLIF(ISNULL(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS), 0) AS PROJECTED_RPU,
                       ( ( @BASE_LINE_VALUE * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) ) * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) ) / NULLIF(ISNULL(TE.FORECAST_PRICE, TE.ACTUAL_PRICE), 0)             AS PROJECTED_RETURN_UNITS,
                       ( @BASE_LINE_VALUE * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) ) * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES)                                                                         AS PROJECTEED_RETURN_AMOUNT
                FROM   #TEMP_EXFACTORY TE
                       JOIN ', @PROJECTION_TABLE, ' TU ON  TU.RETURNS_DETAILS_SID=TE.RETURNS_DETAILS_SID  AND TE.PERIOD_SID=TU.PERIOD_SID
                       JOIN UDCS UD
                         ON UD.MASTER_SID = TE.ITEM_MASTER_SID
                       JOIN HELPER_TABLE HT
                         ON HT.HELPER_TABLE_SID = UD.UDC1
						 JOIN #PERIOD P
                         ON 
                             P.PERIOD_SID = TE.PERIOD_SID AND P.PERIOD_SID BETWEEN ', @METHODOLOGY_START_SID, ' AND ', @METHODOLOGY_END_SID, ' 
                WHERE  UD.MASTER_TYPE = ''ITEM_MASTER''
                       AND HT.DESCRIPTION = ''TIER 1''
                       ')

                EXEC Sp_executesql
                  @SQL1,
                  N'@BASE_LINE_VALUE NUMERIC(22,6)',
                  @BASE_LINE_VALUE=@BASE_LINE_VALUE
            END
---------------METHODOLOGY FOR  'TIER 1' ENDS HERE---------------------------------
---------------METHODOLOGY FOR  'TIER 2' STARTS HERE---------------------------------
          ELSE IF @METHODOLOGY = 'TIER 2'
            BEGIN
                SET @SQL1 = Concat('SELECT @BASE_LINE_VALUE = SUM(RM.CUM_RETURN_UNITS) / NULLIF(SUM(RM.ORIG_SALE_UNITS), 0)
                FROM   #TEMP_MASTER TM
                       JOIN ', @ACTUAL_TABLE, ' RM
                         ON TM.RETURNS_DETAILS_SID = RM.RETURNS_DETAILS_SID
                       JOIN #TEMP_PERIOD TP
                         ON TP.PERIOD_SID = RM.PERIOD_SID
              ')

                EXEC Sp_executesql
                  @SQL1,
                  N'@BASE_LINE_VALUE NUMERIC(22,6) OUTPUT',
                  @BASE_LINE_VALUE OUTPUT

                SET @SQL1 = Concat(' INSERT INTO #TEMP_DETAILS
                            (RETURNS_DETAILS_SID,
                             PERIOD_SID,
                             PROJECTED_RETURN_PERCENT,
                             PROJECTED_RPU,
                             PROJECTED_RETURN_UNITS,
                             PROJECTED_RETURN_AMOUNT)
                SELECT TU.RETURNS_DETAILS_SID,
                       P.PERIOD_SID,
                       ( @BASE_LINE_VALUE * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) ) * 100                                                                                                                    AS PROJECTED_RETURN_PERCENT,
                       ( ( @BASE_LINE_VALUE * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) ) * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) ) / NULLIF(ISNULL(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS), 0) AS PROJECTED_RPU,
                       ( ( @BASE_LINE_VALUE * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) ) * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) ) / NULLIF(ISNULL(TE.FORECAST_PRICE, TE.ACTUAL_PRICE), 0)         AS PROJECTED_RETURN_UNITS,
                       ( @BASE_LINE_VALUE * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) ) * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES)                                                                     AS PROJECTEED_RETURN_AMOUNT
                FROM   #TEMP_EXFACTORY TE
                       JOIN ', @PROJECTION_TABLE, ' TU
                         ON TU.RETURNS_DETAILS_SID = TE.RETURNS_DETAILS_SID
						   AND TE.PERIOD_SID=TU.PERIOD_SID
                        JOIN #PERIOD P ON   TE.PERIOD_SID = P.PERIOD_SID AND P.PERIOD_SID BETWEEN ', @METHODOLOGY_START_SID, ' AND ', @METHODOLOGY_END_SID)

                EXEC Sp_executesql
                  @SQL1,
                  N'@BASE_LINE_VALUE NUMERIC(22,6)',
                  @BASE_LINE_VALUE=@BASE_LINE_VALUE
            END
---------------METHODOLOGY FOR  'TIER 2' ENDS HERE---------------------------------
---------------METHODOLOGY FOR  'ROLLING ANNUAL TREND' ENDS HERE---------------------------------
          ELSE IF @METHODOLOGY = 'ROLLING ANNUAL TREND'
            BEGIN
                --DECLARE @METHODOLGY_ROLL_SID INT,
                --        @CNT1                INT=1,
                --        @YEAR_DIFF           INT,
                --        @MONTHS              INT=11,
                --        @YEAR_DIFF1          INT
                --SELECT TOP 1 @METHODOLGY_ROLL_SID = TP.PERIOD_SID
                --FROM   #TEMP_PERIOD TP
                --WHERE  TP.FLAG = 'F'
                --SELECT @CNT = MIN(TP.PERIOD_SID)
                --FROM   #TEMP_PERIOD TP
                --WHERE  FLAG = 'B'
                --GROUP  BY FLAG
                --SET @CNT=@METHODOLGY_ROLL_SID - @CNT
                --SELECT TOP 1 @YEAR_DIFF = YEAR
                --FROM   #TEMP_PERIOD
                --WHERE  FLAG = 'F'
                --ORDER  BY PERIOD_SID ASC
                --SELECT @YEAR_DIFF1 = YEAR
                --FROM   #TEMP_PERIOD
                --WHERE  FLAG = 'E'
                IF Object_id('TEMPDB..#TEMP_BASE') IS NOT NULL
                  DROP TABLE #TEMP_BASE

                CREATE TABLE #TEMP_BASE
                  (
                     RETURNS_DETAILS_SID   INT,
                     ACTUAL_RETURN_PERCENT NUMERIC(22, 6),
                     ROLL_PERIOD           INT
                  )

                SET @SQL1 = Concat(' INSERT INTO #TEMP_BASE
					 (
					 RETURNS_DETAILS_SID  ,
					 ACTUAL_RETURN_PERCENT,
					 ROLL_PERIOD
					 )
					
					SELECT A.RETURNS_DETAILS_SID,ACTUAL_RETURN_PERCENT,ROLL_PERIOD
					       FROM ', @ACTUAL_TABLE, ' A JOIN #TEMP_MASTER T ON T.RETURNS_DETAILS_SID=A.RETURNS_DETAILS_SID
						   JOIN #TEMP_PERIOD TP ON TP.PERIOD_SID=A.PERIOD_SID
						   JOIN #PERIOD P ON P.PERIOD_SID=TP.PERIOD_SID
					 
					 ')

                EXEC sp_executesql @SQL1

                --SELECT @YEAR_DIFF = COALESCE(@YEAR_DIFF1, YEAR(@PROJECTION_END_DATE)) - @YEAR_DIFF
                SET @SQL1 = Concat('      INSERT INTO #TEMP_DETAILS
                            (RETURNS_DETAILS_SID,
                             PERIOD_SID,
                             PROJECTED_RETURN_PERCENT,
                             PROJECTED_RPU,
                             PROJECTED_RETURN_UNITS,
                             PROJECTED_RETURN_AMOUNT)
                SELECT STA.RETURNS_DETAILS_SID,
                       P.PERIOD_SID,
                       ( ISNULL(TU.ACTUAL_RETURN_PERCENT, 0) * ISNULL( GROWTH,1  ) )                                                                                                                              AS PROJECTED_RETURN_PERCENT,
                       ( ISNULL(TU.ACTUAL_RETURN_PERCENT / 100.00, 0) * ISNULL( GROWTH,1  )    * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) ) / NULLIF(ISNULL(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS), 0) AS PROJECTED_RPU,
                       ISNULL(TU.ACTUAL_RETURN_PERCENT / 100.00, 0) * ISNULL( GROWTH,1  )    * ISNULL(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS)                                                                     AS PROJECTED_RETURN_UNITS,
                       ISNULL(TU.ACTUAL_RETURN_PERCENT / 100.00, 0) * ISNULL( GROWTH,1  )    * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES)                                                                     AS PROJECTED_RETURN_AMOUNT
                FROM    #TEMP_MASTER STA
                       JOIN #TEMP_BASE TU
                         ON TU.RETURNS_DETAILS_SID = STA.RETURNS_DETAILS_SID
                       JOIN #TEMP_EXFACTORY TE
                         ON TU.RETURNS_DETAILS_SID = TE.RETURNS_DETAILS_SID
                       JOIN #PERIOD P ON P.PERIOD_SID= TE.PERIOD_SID AND P.ROLL_PERIOD=TU.ROLL_PERIOD
					   LEFT JOIN ', @RETURNS_GROWTH_TABLE, ' RG ON RG.RETURNS_DETAILS_SID=STA.RETURNS_DETAILS_SID AND RG.PERIOD_SID=P.PERIOD_sID
                WHERE  P.PERIOD_SID BETWEEN  ', @METHODOLOGY_START_SID, ' AND ', @METHODOLOGY_END_SID)

                EXEC sp_executesql @SQL1

                --WHILE( @I <= @YEAR_DIFF )
                --  BEGIN
                --      SELECT @METHODOLGY_ROLL_SID = MAX(PERIOD_SID) + 1
                --      FROM   #TEMP_DETAILS
                --      SET @CNT=12
                --      INSERT INTO #TEMP_DETAILS
                --                  (RETURNS_DETAILS_SID,
                --                   PERIOD_SID,
                --                   PROJECTED_RETURN_PERCENT,
                --                   PROJECTED_RPU,
                --                   PROJECTED_RETURN_UNITS,
                --                   PROJECTED_RETURN_AMOUNT)
                --      SELECT TM.RETURNS_DETAILS_SID,
                --             TU.PERIOD_SID,
                --             ( ISNULL(STA.PROJECTED_RETURN_PERCENT, 0) * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) )                                                                                                                               AS PROJECTED_RETURN_PERCENT,
                --             ( ISNULL(STA.PROJECTED_RETURN_PERCENT / 100.00, 0) * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) ) / NULLIF(ISNULL(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS), 0) AS PROJECTED_RPU,
                --             ISNULL(STA.PROJECTED_RETURN_PERCENT / 100.00, 0) * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) * ISNULL(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS)                                                                     AS PROJECTED_RETURN_UNITS,
                --             ISNULL(STA.PROJECTED_RETURN_PERCENT / 100.00, 0) * ( 1 + COALESCE(TU.GROWTH_RATE, 0) / 100 ) * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES)                                                                     AS PROJECTED_RETURN_AMOUNT
                --      FROM   #TEMP_DETAILS STA
                --             JOIN #TEMP_MASTER TM
                --               ON STA.RETURNS_DETAILS_SID = TM.RETURNS_DETAILS_SID
                --             JOIN #TEMP_UDT TU
                --               ON TU.RETURNS_DETAILS_SID = TM.RETURNS_DETAILS_SID
                --                  AND STA.PERIOD_SID = TU.PERIOD_SID - @CNT
                --             JOIN #TEMP_EXFACTORY TE
                --               ON TU.RETURNS_DETAILS_SID = TE.RETURNS_DETAILS_SID
                --                  AND TU.PERIOD_SID = TE.PERIOD_SID
                --                  AND TE.PERIOD_SID - @CNT = STA.PERIOD_SID
                --      WHERE  TU.PERIOD_SID BETWEEN @METHODOLGY_ROLL_SID AND @METHODOLGY_ROLL_SID + @MONTHS
                --             AND TU.PERIOD_SID >= @METHODOLOGY_START_SID
                --             AND ( ( TU.PERIOD_SID <= @METHODOLOGY_END_SID
                --                     AND TU.PERIOD_SID <= @END_PERIOD_SID )
                --                    OR ( TU.PERIOD_SID <= @END_PERIOD_SID ) )
                --      SET @I=@I + 1
                -- END
        
            END
			---------------METHODOLOGY FOR  'ROLLING ANNUAL TREND' ENDS HERE---------------------------------
				---------------METHODOLOGY FOR  'QUARTILE' ENDS HERE---------------------------------
          ELSE IF @METHODOLOGY = 'QUARTILE'
            BEGIN
                SET @SQL1 = Concat('INSERT INTO #TEMP_DETAILS
                            (RETURNS_DETAILS_SID,
                             PERIOD_SID,
                             PROJECTED_RETURN_PERCENT,
                             PROJECTED_RPU,
                             PROJECTED_RETURN_UNITS,
                             PROJECTED_RETURN_AMOUNT)
                SELECT TU.RETURNS_DETAILS_SID,
                       p.PERIOD_SID,
                       RETURNED_UNITS                                                                                                                          AS PROJECTED_RETURN_PERCENT,
                       ( RETURNED_UNITS * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) ) / NULLIF(ISNULL(TE.FORECAST_GTS_UNITS, TE.ACTUAL_GTS_UNITS), 0) AS PROJECTED_RPU,
                       ( RETURNED_UNITS * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES) ) / NULLIF(ISNULL(TE.FORECAST_PRICE, TE.ACTUAL_PRICE), 0)         AS PROJECTED_RETURN_UNITS,
                       RETURNED_UNITS * ISNULL(TE.FORECAST_GTS_SALES, TE.ACTUAL_GTS_SALES)                                                                     AS PROJECTED_RETURN_AMOUNT
                FROM   (SELECT ISNULL((( RM.EXPECTED_RETURN_RATE )), 0) * ( 1 + ISNULL(( TU.GROWTH_RATE ), 0) / 100 ) AS RETURNED_UNITS,
                               TU.RETURNS_DETAILS_SID,
                               TM.ITEM_MASTER_SID
                        FROM   #TEMP_MASTER TM
                               JOIN ', @ACTUAL_TABLE, ' RM
                                 ON RM.RETURNS_DETAILS_SID = TM.RETURNS_DETAILS_SID
                               JOIN ', @PROJECTION_TABLE, ' TU
                                 ON TU.RETURNS_DETAILS_SID = TM.RETURNS_DETAILS_SID
                               AND RM.PERIOD_SID IN (SELECT MAX(PERIOD_SID)
                                                     FROM   ', @ACTUAL_TABLE, '
                                                     WHERE  RETURNS_DETAILS_SID = RM.RETURNS_DETAILS_SID
                                                            AND EXPECTED_RETURN_RATE != 0
                                                     GROUP  BY RETURNS_DETAILS_SID)
                               AND TU.PERIOD_SID = ', @METHODOLOGY_START_SID, ')A
                       JOIN #TEMP_EXFACTORY TE
                         ON TE.RETURNS_DETAILS_SID = A.RETURNS_DETAILS_SID
                       JOIN #TEMP_MASTER TU
                         ON TU.RETURNS_DETAILS_SID = A.RETURNS_DETAILS_SID
                       JOIN #PERIOD P ON P.PERIOD_SID=TE.PERIOD_SID AND P.PERIOD_SID BETWEEN ', @METHODOLOGY_START_SID, ' AND ', @METHODOLOGY_END_SID)

                EXEC sp_executesql @SQL1
            END

          SET @SQL1='UPDATE A
          SET    A.PROJECTED_RETURN_PERCENT = TD.PROJECTED_RETURN_PERCENT,
                 A.PROJECTED_RPU = TD.PROJECTED_RPU,
                 A.PROJECTED_RETURN_AMOUNT = TD.PROJECTED_RETURN_AMOUNT,
                 A.PROJECTED_RETURN_UNITS = TD.PROJECTED_RETURN_UNITS
          FROM   ' + @PROJECTION_TABLE
                    + ' A
                 JOIN #TEMP_MASTER TM
                   ON TM.RETURNS_DETAILS_SID = A.RETURNS_DETAILS_SID
                 LEFT JOIN #TEMP_DETAILS TD
                        ON TD.RETURNS_DETAILS_SID = A.RETURNS_DETAILS_SID
                           AND TD.PERIOD_SID = A.PERIOD_SID'

          EXEC sp_executesql @SQL1
			---------------METHODOLOGY FOR   'QUARTILE' ENDS HERE---------------------------------	  
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

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.
                     @ERRORSEVERITY,-- SEVERITY.
                     @ERRORSTATE,-- STATE.
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.
                     @ERRORNUMBER,-- ERRORNUMBER
                     @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END 
