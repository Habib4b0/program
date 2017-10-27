IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NEW_NDC'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NEW_NDC]
  END

GO

CREATE PROCEDURE [dbo].[PRC_NEW_NDC] (@NA_PROJ_MASTER_SID INT,
                                      @USER_ID            INT,
                                      @SESSION_ID         varchar(100))
AS
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
            DECLARE @ACTUAL_START_DATE     DATETIME,
                  @ACTUAL_END_DATE       DATETIME,
                  @PROJECTION_START_DATE DATETIME,
                  @PROJECTION_END_DATE   DATETIME,
                  @BASELINE_METHODOLOGY  VARCHAR(30),
                  @BASELINE_PERIOD       VARCHAR(500),
                  @FORECAST_METHODOLOGY  VARCHAR(30),
                  @ROLLING_PERIOD        VARCHAR(500),
                  @GROWTH_RATE           NUMERIC(22, 6),
                  @GROWTH_FREQUENCY      VARCHAR(25),
                  @START_PERIOD          VARCHAR(10),
                  @END_PERIOD            VARCHAR(10),
                  @PROJ_PERIOD_START_SID INT,
                  @PROJ_PERIOD_END_SID   INT,
                  @ACT_PERIOD_START_SID  INT,
                  @ACT_PERIOD_END_SID    INT,
                  @PRICE_TYPE            VARCHAR(25),
                  @PRICE_BASIS           VARCHAR(30),
                  @ITEM_UOM              VARCHAR(50)='UN',
                  @BUSINESS_PROCESS_TYPE VARCHAR(50) ='NATIONAL ASSUMPTIONS',
				  @BUSINESS_UNIT INT , 
				  @COMPANY_ID INT 
     

          --TO INSERT ITEMS THAT ARE ALREADY SETUP IN NEW NDC WHICH IS INCLUEDED IN CURRENT PROJECTION START
DECLARE 


@NA_ACTUAL_TABLE      VARCHAR(200) = CONCAT('ST_NATIONAL_ASSUMPTIONS_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
@NA_PROJECTION_TABLE  VARCHAR(200) = CONCAT('ST_NATIONAL_ASSUMPTIONS_PROJ_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),



@MEDICATED_TABLE VARCHAR(200) = CONCAT('ST_MEDICAID_NEW_NDC_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
@FEDERAL_TABLE VARCHAR(200) = CONCAT('ST_FEDERAL_NEW_NDC_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
@NATIONAL_TABLE VARCHAR(200) = CONCAT('ST_NATIONAL_ASSUMPTIONS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
DECLARE @SQL1 NVARCHAR(MAX)=' '

          -- FOR MEDIACID 
          SET @SQL1 =CONCAT('INSERT INTO ',@MEDICATED_TABLE,'
                      (NDC9,
                       WAC_PRICE,
                       BASE_YEAR_AMP,
                       BASE_YEAR_CPI,
                       FORECAST_AMP,
                       FORECAST_BESTPRICE
                       )
          SELECT NDC9,
                 WAC_PRICE,
                 BASE_YEAR_AMP,
                 BASE_YEAR_CPI,
                 FORECAST_AMP,
                 FORECAST_BESTPRICE
          FROM   MEDICAID_NEW_NDC MNN
          WHERE  EXISTS (SELECT 1
                         FROM   NA_PROJ_DETAILS NPD
                                INNER JOIN ITEM_MASTER IM
                                        ON NPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                LEFT OUTER JOIN ITEM_PRICING IP
                                             ON IM.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                                LEFT OUTER JOIN ITEM_PRICING_QUALIFIER IPQ
                                             ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                                                AND IPQ.PRICING_QUALIFIER IN ( ''AMP'', ''BP'' )
                         WHERE  MNN.NDC9 = IM.NDC9
                                AND NPD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
                         GROUP  BY NPD.ITEM_MASTER_SID
                         HAVING COUNT(DISTINCT IPQ.PRICING_QUALIFIER) <> 2)
                 AND NOT EXISTS (SELECT 1
                                 FROM   ',@MEDICATED_TABLE,' SMNN
                                 WHERE  MNN.NDC9 = SMNN.NDC9)')
                                        
										EXEC sp_executesql @SQL1




          --FOR FEDERAL
            SET @SQL1 =CONCAT('INSERT INTO ',@FEDERAL_TABLE,'
                      (ITEM_MASTER_SID,
                       WAC_PRICE,
                       NON_FAMP,
                       FSS
                     )
          SELECT ITEM_MASTER_SID,
                 WAC_PRICE,
                 NON_FAMP,
                 FSS
          FROM   FEDERAL_NEW_NDC FNN
          WHERE  EXISTS (SELECT 1
                         FROM   NA_PROJ_DETAILS NPD
                                INNER JOIN ITEM_MASTER IM
                                        ON NPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                LEFT OUTER JOIN ITEM_PRICING IP
                                             ON IM.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                                LEFT OUTER JOIN ITEM_PRICING_QUALIFIER IPQ
                                             ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                                                AND IPQ.PRICING_QUALIFIER IN ( ''QNON-FAMP'', ''QFSS'' )
                         WHERE  FNN.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                AND NPD.NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
                         GROUP  BY NPD.ITEM_MASTER_SID
                         HAVING COUNT(DISTINCT IPQ.PRICING_QUALIFIER) <> 2)
                 AND NOT EXISTS (SELECT 1
                                 FROM   ',@FEDERAL_TABLE,' SFNN
                                 WHERE  FNN.ITEM_MASTER_SID = SFNN.ITEM_MASTER_SID
                                     ) ')

									 EXEC sp_executesql @SQL1




         IF Object_id('TEMPDB..#NATIONAL_ASSUMPTIONS') IS NOT NULL
        DROP TABLE #NATIONAL_ASSUMPTIONS
		 
		    CREATE TABLE #NATIONAL_ASSUMPTIONS
                    (
                       BASELINE_METHODOLOGY         VARCHAR(50),
                       FORECAST_METHODOLOGY         VARCHAR(50),
                       BASELINE_PERIOD              VARCHAR(8000),
					   ROLLING_PERIOD               VARCHAR(8000),
					   START_PERIOD                 VARCHAR(8),
					   END_PERIOD                   VARCHAR(8),
                       GROWTH_RATE                  NUMERIC(22, 6),
                       PRICE_TYPE                   VARCHAR(50),
					   FREQUENCY                    VARCHAR(50),
					   PRICE_BASIS                  VARCHAR(30),
					   LAST_MODIFIED_DATE           DATETIME,
					   NA_PROJ_MASTER_SID           INT
                    )



            SET @SQL1=CONCAT('
			       INSERT INTO #NATIONAL_ASSUMPTIONS  (NA_PROJ_MASTER_SID,PRICE_TYPE,
                   BASELINE_METHODOLOGY,
                   FORECAST_METHODOLOGY,
                   BASELINE_PERIOD,
                   ROLLING_PERIOD,
                   START_PERIOD,
                   END_PERIOD,
                   GROWTH_RATE,
                   FREQUENCY,
				   LAST_MODIFIED_DATE,
                   PRICE_BASIS)


			SELECT NA_PROJ_MASTER_SID,
                   PRICE_TYPE,
                   BASELINE_METHODOLOGY,
                   FORECAST_METHODOLOGY,
                   BASELINE_PERIOD,
                   ROLLING_PERIOD,
                   START_PERIOD,
                   END_PERIOD,
                   GROWTH_RATE,
                   FREQUENCY,
				   LAST_MODIFIED_DATE,
                   PRICE_BASIS
            FROM   ',@NATIONAL_TABLE,' NA
            WHERE  NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
                   AND FORECAST_METHODOLOGY <> ''ROLLING AVERAGE''
                   AND ( EXISTS (SELECT 1
                                 FROM   ',@MEDICATED_TABLE,' MNN
                                 WHERE  
                                         NA.PRICE_TYPE IN ( ''AMP'', ''BEST PRICE'' )) -- TO FIND THE MEDICAID PRICE TYPES THAT ARE APPLICABLE FOR NEW NDC 
                          OR EXISTS (SELECT 1
                                     FROM   ',@FEDERAL_TABLE,' FNN
                                     WHERE  
                                             NA.PRICE_TYPE IN ( ''NON-FAMP'', ''FSS(OGA)'')) ) -- TO FIND THE FEDERAL PRICE TYPES THAT ARE APPLICABLE FOR NEW NDC 
            ORDER  BY LAST_MODIFIED_DATE ASC')
			EXEC sp_executesql @SQL1





 -- TO AVOID THE ITEMS WHICH IS ALREADY INSERTED BY JAVA (EDIT MODE)
          --TO INSERT ITEMS THAT ARE ALREADY SETUP IN NEW NDC WHICH IS INCLUEDED IN CURRENT PROJECTION END
          -- NA_CUR START CURSOR TO PARSE EACH PRICE TYPE THAT HAS BEEN SETUP IN NATIONAL ASSUMPTIONS TAB.
          DECLARE @ITEMID [DBO].[UDT_ITEM]

          INSERT INTO @ITEMID
          SELECT ITEM_MASTER_SID
          FROM   NA_PROJ_DETAILS
          WHERE  NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID

          DECLARE NA_CUR CURSOR LOCAL FAST_FORWARD FOR

           SELECT NA_PROJ_MASTER_SID,
                   PRICE_TYPE,
                   BASELINE_METHODOLOGY,
                   FORECAST_METHODOLOGY,
                   BASELINE_PERIOD,
                   ROLLING_PERIOD,
                   START_PERIOD,
                   END_PERIOD,
                   GROWTH_RATE,
                   FREQUENCY,
                   PRICE_BASIS
				   FROM #NATIONAL_ASSUMPTIONS
				            ORDER  BY LAST_MODIFIED_DATE ASC
		

			
			
          OPEN NA_CUR

          FETCH NA_CUR INTO @NA_PROJ_MASTER_SID, @PRICE_TYPE, @BASELINE_METHODOLOGY, @FORECAST_METHODOLOGY, @BASELINE_PERIOD, @ROLLING_PERIOD, @START_PERIOD, @END_PERIOD, @GROWTH_RATE, @GROWTH_FREQUENCY, @PRICE_BASIS
		
          WHILE @@FETCH_STATUS = 0
            BEGIN
                --CREATION OF TEMP TABLES START 
                IF OBJECT_ID('TEMPDB..#ITEM_DETAILS') IS NOT NULL --  TO STORE THE ITEM DETAILS THAT WAS SELECTED IN GIVEN PROJECTION
                  TRUNCATE TABLE #ITEM_DETAILS
                ELSE
                  CREATE TABLE #ITEM_DETAILS
                    (
                       ITEM_NO         VARCHAR(50),
                       ITEM_MASTER_SID INT PRIMARY KEY,
                       NDC9            VARCHAR(50),
                       UPPS            NUMERIC(22, 6),
                       ITEM_ID         VARCHAR(50)
                    )

                IF OBJECT_ID('TEMPDB..#PERIOD_QUARTER') IS NOT NULL -- TO STORE THE PERIOD INFORMATION IN QUARTER BASIS
                  TRUNCATE TABLE #PERIOD_QUARTER
                ELSE
                  CREATE TABLE #PERIOD_QUARTER
                    (
                       PERIOD_SID     INT PRIMARY KEY,
                       PERIOD_YEAR    INT,
                       PERIOD_QUARTER INT,
                       SEMI_ANNUAL    INT,
                       Q_PERIOD       VARCHAR(10),
                       PERIOD_DATE    DATETIME
                    )

                IF OBJECT_ID('TEMPDB..#ITEM_PRICING') IS NOT NULL -- TO STORE ITEM PRICING DETAILS OF SELECTED ITEMS 
                  TRUNCATE TABLE #ITEM_PRICING
                ELSE
                  CREATE TABLE #ITEM_PRICING
                    (
                       ITEM_MASTER_SID  INT,
                       PRICE_TYPE       VARCHAR(50),
                       PERIOD_SID       INT,
                       ACTUAL_PRICE     NUMERIC(22, 6),
                       PROJECTION_PRICE NUMERIC(22, 6),
                       WAC_INCREASE     NUMERIC(22, 6),
                       NDC9             VARCHAR(20),
                       PERIOD_INDICATOR BIT, -- ACTUAL SYSTEM DATA(0) AND FORECASTED DATA(1)
                       PRIMARY KEY (ITEM_MASTER_SID, PRICE_TYPE, PERIOD_SID)
                    )

                IF OBJECT_ID('TEMPDB..#BASELINE') IS NOT NULL -- TO INSERT THE BASELINE VALUES FOR EACH ITEM'S SELECTED IN 
                  TRUNCATE TABLE #BASELINE
                ELSE
                  CREATE TABLE #BASELINE
                    (
                       ITEM_MASTER_SID INT,
                       SALES           NUMERIC(22, 6),
                       BASELINE_PRICE  NUMERIC(22, 6),
                       PERIOD_SID      INT,
                       [YEAR]          INT,
                       [QUARTER]       INT
                    )

                --CREATION OF TEMP TABLES END 
                -- INSERTION OF ITEM DETAILS THAT WAS SELECTED IN GIVEN PROJECTION
                SET @SQL1=CONCAT('INSERT INTO #ITEM_DETAILS
                            (ITEM_NO,
                             ITEM_MASTER_SID,
                             NDC9,
                             UPPS,
                             ITEM_ID)
                SELECT IM.ITEM_NO,
                       IM.ITEM_MASTER_SID,
                       IM.NDC9,
                       IM.UPPS,
                       IM.ITEM_ID
                FROM   ',@MEDICATED_TABLE,' MNN
                       INNER JOIN ITEM_MASTER IM
                               ON MNN.NDC9 = IM.NDC9
              
                UNION
                SELECT IM.ITEM_NO,
                       IM.ITEM_MASTER_SID,
                       IM.NDC9,
                       IM.UPPS,
                       IM.ITEM_ID
                FROM   ',@FEDERAL_TABLE,' FNN
                       INNER JOIN ITEM_MASTER IM
                               ON FNN.ITEM_MASTER_SID = IM.ITEM_MASTER_SID')
               EXEC sp_executesql @SQL1

					--------------------------------------GAL-808 IMPACT-----------------------------

			SELECT @BUSINESS_UNIT=BUSINESS_UNIT,
			@COMPANY_ID=NA.COMPANY_MASTER_SID
			FROM NA_PROJ_MASTER NA
			WHERE NA_PROJ_MASTER_SID=@NA_PROJ_MASTER_SID
			-----------------------------------------------------------------------
                -- INSERTION OF PERIOD INFORMATION IN QUARTER BASIS
                INSERT INTO #PERIOD_QUARTER
                            (PERIOD_SID,
                             PERIOD_YEAR,
                             PERIOD_QUARTER,
                             SEMI_ANNUAL,
                             Q_PERIOD,
                             PERIOD_DATE)
                SELECT MIN(PERIOD_SID) AS PERIOD_SID,
                       [YEAR],
                       [QUARTER],
                       SEMI_ANNUAL,
                       'Q' + CONVERT(VARCHAR(2), [QUARTER]) + ' '
                       + CONVERT(VARCHAR(5), [YEAR]),
                       MIN(PERIOD_DATE) AS PERIOD_DATE
                FROM   PERIOD
                GROUP  BY [YEAR],
                          [QUARTER],
                          SEMI_ANNUAL

                -- PULLING THE DATE RANGE FOR HISTORICAL START TO PROJECTION END
                SELECT @ACTUAL_START_DATE = ACTUAL_START_DATE,
                       @ACTUAL_END_DATE = ACTUAL_END_DATE,
                       @PROJECTION_START_DATE = PROJECTION_START_DATE,
                       @PROJECTION_END_DATE = PROJECTION_END_DATE
                FROM   [DBO].[UDF_NA_PROJ_DATES](@BUSINESS_PROCESS_TYPE)

                SELECT @ACT_PERIOD_START_SID = MAX(CASE
                                                     WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_START_DATE), 0) THEN PERIOD_SID
                                                   END),
                       @ACT_PERIOD_END_SID = MAX(CASE
                                                   WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_END_DATE), 0) THEN PERIOD_SID
                                                 END),
                       @PROJ_PERIOD_START_SID = MAX(CASE
                                                      WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_START_DATE), 0) THEN PERIOD_SID
                                                    END),
                       @PROJ_PERIOD_END_SID = MAX(CASE
                                                    WHEN PERIOD_DATE = DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_END_DATE), 0) THEN PERIOD_SID
                                                  END)
                FROM   (SELECT PERIOD_SID,
                               PERIOD_DATE
                        FROM   #PERIOD_QUARTER
                        WHERE  PERIOD_DATE IN ( DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_START_DATE), 0), DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_END_DATE), 0), DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_START_DATE), 0), DATEADD(QQ, DATEDIFF(QQ, 0, @PROJECTION_END_DATE), 0) )) A

                --INSERTION OF PERIODS WITHOUT ITEM_PRICE DATA FROM ACTUAL START TO PROJECTION END FOR GIVEN PRICE TYPE AND WAC FOR ALL THE ITEMS
                INSERT INTO #ITEM_PRICING
                            (ITEM_MASTER_SID,
                             PRICE_TYPE,
                             PERIOD_SID,
                             NDC9)
                SELECT ID.ITEM_MASTER_SID,
                       PRICE_TYPE,
                       PQ.PERIOD_SID,
                       NDC9
                FROM   #PERIOD_QUARTER PQ
                       CROSS JOIN #ITEM_DETAILS ID
                       CROSS JOIN (SELECT PRICE_TYPE
                                   FROM   (VALUES(@PRICE_TYPE),
                                                 (@PRICE_BASIS))V(PRICE_TYPE)) PT
                WHERE  PQ.PERIOD_SID BETWEEN @PROJ_PERIOD_START_SID AND @PROJ_PERIOD_END_SID

                DECLARE @FORECAST_NAME    VARCHAR(50),
                        @FORECAST_VERSION VARCHAR(15)

                SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME,
                             @FORECAST_VERSION = FT.[VERSION]
                FROM   FILE_MANAGEMENT FT
                       INNER JOIN HELPER_TABLE HT
                               ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
                WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE())
                         AND FT.FROM_PERIOD IS NOT NULL )
                       AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE())
                              OR FT.TO_PERIOD IS NULL )
                       AND HT.LIST_NAME = 'FILE_TYPE'
                       AND HT.DESCRIPTION = 'EX-FACTORY SALES'
					   AND FT.BUSINESS_UNIT=@BUSINESS_UNIT-----GAL-808
					   AND FT.COMPANY=@COMPANY_ID----GAL-808
                ORDER  BY FT.FROM_PERIOD DESC

                DECLARE @CURR_QUART_PERIOD_SID INT

                SELECT @CURR_QUART_PERIOD_SID = PERIOD_SID
                FROM   PERIOD
                WHERE  PERIOD_DATE IN ( DATEADD(MONTH, DATEDIFF(MONTH, 0, GETDATE()) - 1, 0) )

                IF @PRICE_BASIS IN( 'EQWAC', 'BQWAC', 'MQWAC', 'AVGQWAC' )
                  BEGIN ;
                      WITH WAC
                           AS (SELECT ITEM_MASTER_SID,
                                       FORECAST_GTS_SALES AS GTS_SALES,
                                       FORECAST_GTS_UNITS AS GTS_UNITS, 
                                      P.QUARTER,
                                      P.MONTH,
                                      P.YEAR,
                                      FORECAST_PRICE AS WAC_PRICE, 
                                      GTS.PERIOD_SID
                               FROM   [DBO].[UDF_GTS_WAC] (@ITEMID, @CURR_QUART_PERIOD_SID, @PROJ_PERIOD_END_SID, @FORECAST_NAME, @FORECAST_VERSION) GTS
                                      INNER JOIN PERIOD P
                                              ON GTS.PERIOD_SID = P.PERIOD_SID)
                      UPDATE IP
                      SET    IP.PROJECTION_PRICE = WAC
                      FROM   #ITEM_PRICING IP
                             INNER JOIN (SELECT CASE
                                                         WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN NDC9
                                                         ELSE CAST(ID.ITEM_MASTER_SID AS VARCHAR(15))
                                                       END AS ITEM,
                                                @PRICE_BASIS AS PRICE_TYPE,
                                                 CASE @PRICE_BASIS
                                                       WHEN 'BQWAC' THEN MAX(CASE
                                                                               WHEN MONTH IN( 1, 4, 7, 10 ) THEN GTS_SALES
                                                                             END) / MAX(CASE
                                                                                          WHEN MONTH IN( 1, 4, 7, 10 ) THEN GTS_UNITS
                                                                                        END)
                                                       WHEN 'EQWAC' THEN MAX(CASE
                                                                               WHEN MONTH IN( 3, 6, 9, 12 ) THEN GTS_SALES
                                                                             END) / MAX(CASE
                                                                                          WHEN MONTH IN( 3, 6, 9, 12 ) THEN GTS_UNITS
                                                                                        END)
                                                       WHEN 'MQWAC' THEN MAX(CASE
                                                                               WHEN MONTH IN( 2, 5, 8, 11 ) THEN GTS_SALES
                                                                             END) / MAX(CASE
                                                                                          WHEN MONTH IN( 2, 5, 8, 11 ) THEN GTS_UNITS
                                                                                        END)
                                                       WHEN 'AVGQWAC' THEN AVG(WAC_PRICE)
                                                     END AS WAC,
                                                 MIN(PERIOD_SID) AS PERIOD_SID
                                         FROM   WAC C
                                                INNER JOIN #ITEM_DETAILS ID
                                                        ON C.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                                         GROUP  BY CASE
                                                     WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN NDC9
                                                     ELSE CAST(ID.ITEM_MASTER_SID AS VARCHAR(15))
                                                   END,
                                                   YEAR,
                                                   QUARTER
                                         UNION ALL
                                         SELECT CASE
                                                  WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN NDC9
                                                  ELSE CAST(IP.ITEM_MASTER_SID AS VARCHAR(15))
                                                END,
                                                 @PRICE_BASIS AS PRICE_TYPE, 
                                                AVG(ACTUAL_PRICE),
                                                MIN(IP.PERIOD_SID)
                                         FROM   #ITEM_PRICING IP
                                                INNER JOIN #PERIOD_QUARTER PQ
                                                        ON PQ.PERIOD_SID = IP.PERIOD_SID
                                         WHERE  PRICE_TYPE = @PRICE_BASIS
                                                AND PQ.PERIOD_SID BETWEEN @ACT_PERIOD_START_SID AND @CURR_QUART_PERIOD_SID
                                         GROUP  BY CASE
                                                     WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN NDC9
                                                     ELSE CAST(IP.ITEM_MASTER_SID AS VARCHAR(15))
                                                   END,
                                                   PERIOD_YEAR,
                                                   PERIOD_QUARTER) A
                                     ON CASE
                                          WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN IP.NDC9
                                          ELSE CAST(ITEM_MASTER_SID AS VARCHAR(15))
                                        END = A.ITEM
                                        AND IP.PERIOD_SID = A.PERIOD_SID
                                        AND IP.PRICE_TYPE = A.PRICE_TYPE
                  END

                IF @PRICE_BASIS = 'DAY WEIGHTED WAC'
                  BEGIN ;
                      WITH CTE
                           AS (SELECT FORECAST_PRICE AS FORECAST_WAC_PRICE,
                                      0 AS ACTUAL_WAC_PRICE,
                                      ITEM_MASTER_SID,
                                      P.PERIOD_DATE,
                                      [QUARTER],
                                      [YEAR],
                                      P.PERIOD_SID,
                                      DATEDIFF(DAY, P.PERIOD_DATE, DATEADD(MONTH, 1, P.PERIOD_DATE)) AS NO_OF_DAYS
                               FROM   [DBO].[UDF_GTS_WAC] (@ITEMID, @PROJ_PERIOD_START_SID, @PROJ_PERIOD_END_SID + 2, @FORECAST_NAME, @FORECAST_VERSION) GTS
                                      INNER JOIN PERIOD P
                                              ON GTS.PERIOD_SID = P.PERIOD_SID
                               WHERE  P.PERIOD_DATE BETWEEN DATEADD(DD, 1, EOMONTH(GETDATE(), -1)) AND @PROJECTION_END_DATE
                               UNION ALL
                               SELECT 0 AS FORECAST_WAC_PRICE,
                                      ITEM_PRICE AS ACTUAL_WAC_PRICE, 
                                      IP.ITEM_MASTER_SID,
                                      P.PERIOD_DATE,
                                      [QUARTER],
                                      [YEAR],
                                      P.PERIOD_SID,
                                      DATEDIFF(DAY, P.PERIOD_DATE, DATEADD(MONTH, 1, P.PERIOD_DATE)) AS NO_OF_DAYS
                               FROM   UDF_ITEM_PRICING(@ITEMID, 'WAC', @ACT_PERIOD_START_SID, @CURR_QUART_PERIOD_SID, @ITEM_UOM) IP
                                      INNER JOIN PERIOD P
                                              ON IP.PERIOD_SID = P.PERIOD_SID
                               WHERE  P.PERIOD_DATE BETWEEN @ACTUAL_START_DATE AND DATEADD(MONTH, DATEDIFF(MONTH, -1, GETDATE()) - 1, -1)),
                           CTE1
                           AS (SELECT  CASE
                                               WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN NDC9
                                               ELSE CAST(ID.ITEM_MASTER_SID AS VARCHAR(15))
                                             END AS ITEM,
                                      QUARTER,
                                      [YEAR],
                                       MIN(PERIOD_SID) AS PERIOD_SID,
                                       @PRICE_BASIS AS PRICE_TYPE,
                                       SUM(CASE
                                                              WHEN PERIOD_DATE < CAST(DATEADD(DD, -( DAY(GETDATE()) - 1 ), GETDATE()) AS DATE) THEN ( ACTUAL_WAC_PRICE * NO_OF_DAYS )
                                                              ELSE ( FORECAST_WAC_PRICE * NO_OF_DAYS )
                                                            END) / SUM(CASE
                                                                         WHEN PERIOD_DATE < CAST(DATEADD(DD, -( DAY(GETDATE()) - 1 ), GETDATE()) AS DATE) THEN NULLIF(NO_OF_DAYS, 0)
                                                                         ELSE NULLIF(NO_OF_DAYS, 0)
                                                                       END) AS DAY_WEIGHTED_WAC
                               FROM   CTE C
                                      INNER JOIN #ITEM_DETAILS ID
                                              ON C.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                               GROUP  BY CASE
                                           WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN NDC9
                                           ELSE CAST(ID.ITEM_MASTER_SID AS VARCHAR(15))
                                         END,
                                         QUARTER,
                                         [YEAR])
                      UPDATE IP
                      SET    IP.PROJECTION_PRICE = DAY_WEIGHTED_WAC
                      FROM   #ITEM_PRICING IP
                             INNER JOIN CTE1 C
                                     ON CASE
                                          WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN NDC9
                                          ELSE CAST(IP.ITEM_MASTER_SID AS VARCHAR(15))
                                        END = C.ITEM
                                        AND IP.PERIOD_SID = C.PERIOD_SID
                                        AND IP.PRICE_TYPE = C.PRICE_TYPE
                  END
                ELSE IF @PRICE_BASIS = 'SALES WEIGHTED WAC'
                  BEGIN ;
                      WITH CTE
                           AS (SELECT FORECAST_GTS_SALES,
                                      ACTUAL_GTS_SALES,
                                      FORECAST_GTS_UNITS,
                                      ACTUAL_GTS_UNITS,
                                      ITEM_MASTER_SID,
                                      P.QUARTER,
                                      P.[YEAR],
                                      P.PERIOD_SID,
                                      P.PERIOD_DATE
                               FROM   [DBO].[UDF_GTS_WAC] (@ITEMID, @ACT_PERIOD_START_SID, @PROJ_PERIOD_END_SID, @FORECAST_NAME, @FORECAST_VERSION) GTS
                                      INNER JOIN PERIOD P
                                              ON GTS.PERIOD_SID = P.PERIOD_SID),
                           CTE1
                           AS (SELECT  CASE
                                               WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN ID.NDC9
                                               ELSE CAST(ID.ITEM_MASTER_SID AS VARCHAR(15))
                                             END AS ITEM,
                                      QUARTER,
                                      [YEAR],
                                       MIN(PERIOD_SID) AS PERIOD_SID,
                                       @PRICE_BASIS AS PRICE_TYPE, 
                                      SUM(CASE
                                                               WHEN PERIOD_DATE < CAST(DATEADD(DD, -( DAY(GETDATE()) - 1 ), GETDATE()) AS DATE) THEN ACTUAL_GTS_SALES
                                                               ELSE FORECAST_GTS_SALES
                                                             END) / SUM(CASE
                                                                          WHEN PERIOD_DATE < CAST(DATEADD(DD, -( DAY(GETDATE()) - 1 ), GETDATE()) AS DATE) THEN NULLIF(ACTUAL_GTS_UNITS, 0)
                                                                          ELSE NULLIF(FORECAST_GTS_UNITS, 0)
                                                                        END) AS SALES_WEIGHTED_WAC
                               FROM   CTE C
                                      INNER JOIN #ITEM_DETAILS ID
                                              ON C.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                               GROUP  BY CASE
                                           WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN ID.NDC9
                                           ELSE CAST(ID.ITEM_MASTER_SID AS VARCHAR(15))
                                         END,
                                         QUARTER,
                                         [YEAR])
                      UPDATE IP
                      SET    IP.PROJECTION_PRICE = SALES_WEIGHTED_WAC
                      FROM   #ITEM_PRICING IP
                             INNER JOIN CTE1 C
                                     ON CASE
                                          WHEN @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' ) THEN NDC9
                                          ELSE CAST(IP.ITEM_MASTER_SID AS VARCHAR(15))
                                        END = C.ITEM
                                        AND IP.PERIOD_SID = C.PERIOD_SID
                                        AND IP.PRICE_TYPE = C.PRICE_TYPE
                  END;

                WITH WAC_INCREASE_UPDATE_CTE
                     AS (SELECT ROW_NUMBER()
                                  OVER(
                                    PARTITION BY ITEM_MASTER_SID
                                    ORDER BY PERIOD_SID ASC) RN,
                                ITEM_MASTER_SID,
                                PRICE_TYPE,
                                PERIOD_SID,
                                PROJECTION_PRICE AS ITEM_PRICE,
                                WAC_INCREASE
                         FROM   #ITEM_PRICING
                         WHERE  PRICE_TYPE = @PRICE_BASIS)
                UPDATE C
                SET    C.WAC_INCREASE = D.WAC_INCREASE
                FROM   WAC_INCREASE_UPDATE_CTE C
                       INNER JOIN (SELECT A.ITEM_MASTER_SID,
                                          A.PRICE_TYPE,
                                          A.PERIOD_SID,
                                          A.ITEM_PRICE,
                                          ( ( A.ITEM_PRICE - B.ITEM_PRICE ) / NULLIF(B.ITEM_PRICE, 0) ) * 100 AS WAC_INCREASE
                                   FROM   WAC_INCREASE_UPDATE_CTE A
                                          LEFT OUTER JOIN WAC_INCREASE_UPDATE_CTE B
                                                       ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                                          AND A.RN = B.RN + 1) D
                               ON C.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                                  AND C.PERIOD_SID = D.PERIOD_SID

                ---DELETE ALL THE ACTUAL DATA OF ALL THE ITEMS FOR THE GIVEN @NA_PROJ_MASTER_SID BECAUSE NEW PRICES MAY BE ADDED IN ITEM_PRICING TABLE. 
                --SO ALWAYS STORE THE LATEST DATA FROM ITEM_PRICING FOR EVERY ITEM AND PRICE TYPE
               SET @SQL1=CONCAT(' DELETE NAA
                FROM   ',@NA_ACTUAL_TABLE,' NAA
                WHERE  EXISTS (SELECT 1
                               FROM   #ITEM_DETAILS ID
                               WHERE  ID.ITEM_MASTER_SID = NAA.ITEM_MASTER_SID)
                       AND NAA.PRICE_TYPE = ',@PRICE_TYPE,'
                   

                -- INSERTING HISTORICAL DATA FOR ALL THE ITEMS INTO ST_NATIONAL_ASSUMPTIONS_ACTUALS TABLE FOR GIVEN PRICE TYPE 
                INSERT INTO ',@NA_ACTUAL_TABLE,'
                            (ITEM_MASTER_SID,
                             PRICE_TYPE,
                             PERIOD_SID,
                             ACTUAL_PRICE
                            
                SELECT ID.ITEM_MASTER_SID,
                       ',@PRICE_TYPE,',
                       PQ.PERIOD_SID,
                       IP.ACTUAL_PRICE
                FROM   #PERIOD_QUARTER PQ
                       CROSS JOIN #ITEM_DETAILS ID
                       LEFT JOIN #ITEM_PRICING IP
                              ON IP.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                                 AND IP.PERIOD_SID = PQ.PERIOD_SID
                                 AND IP.PRICE_TYPE = ',@PRICE_TYPE,'
                --    AND PERIOD_INDICATOR = 0
                WHERE  PQ.PERIOD_SID BETWEEN ',@ACT_PERIOD_START_SID,' AND ',@ACT_PERIOD_END_SID,'')
				EXEC sp_executesql @SQL1
				
                ----- FORECASTING PERIOD INSERT FOR NEW ITEM PLUS NEW PERIOD IN NATIONAL_ASSUMPTIONS_PROJ TABLE
                --INSERT INTO ST_NATIONAL_ASSUMPTIONS_PROJ
                --            (ITEM_MASTER_SID,PRICE_TYPE,PERIOD_SID,[USER_ID],SESSION_ID)
                --SELECT ID.ITEM_MASTER_SID,@PRICE_TYPE,PQ.PERIOD_SID,@USER_ID,@SESSION_ID
                --FROM   #PERIOD_QUARTER PQ
                --       CROSS JOIN #ITEM_DETAILS ID
                --WHERE  PQ.PERIOD_SID BETWEEN @PROJ_PERIOD_START_SID AND @PROJ_PERIOD_END_SID
                --       AND NOT EXISTS (SELECT 1
                --                       FROM   ST_NATIONAL_ASSUMPTIONS_PROJ NAP
                --                       WHERE  NAP.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                --                              AND NAP.PERIOD_SID = PQ.PERIOD_SID
                --                              AND NAP.PRICE_TYPE = @PRICE_TYPE
                --                              )
                -------- BASLINE CALCULATION STARTS


                IF @PRICE_TYPE IN ( 'AMP', 'BEST PRICE' )
                  SET @SQL1=CONCAT('INSERT INTO #BASELINE
                              (ITEM_MASTER_SID,
                               BASELINE_PRICE)
                  SELECT IM.ITEM_MASTER_SID,
                         CASE @PRICE_TYPE
                           WHEN ''AMP'' THEN FORECAST_AMP
                           WHEN ''BEST PRICE'' THEN FORECAST_BESTPRICE
                           ELSE BASE_YEAR_CPI
                         END
                  FROM   ',@MEDICATED_TABLE,' SNN
                         INNER JOIN ITEM_MASTER IM
                                 ON IM.NDC9 = SNN.NDC9
                  
                ELSE
                  INSERT INTO #BASELINE
                              (ITEM_MASTER_SID,
                               BASELINE_PRICE)
                  SELECT IM.ITEM_MASTER_SID,
                         CASE @PRICE_TYPE
                           WHEN ''FSS(OGA)'' THEN FSS
                           ELSE NON_FAMP
                         END
                  FROM   ',@MEDICATED_TABLE,' SNN
                         INNER JOIN ITEM_MASTER IM
                                 ON IM.ITEM_MASTER_SID = SNN.ITEM_MASTER_SID')
								 EXEC sp_executesql @SQL1
                

                --- FORECASTING PERIOD INSERT FOR NEW ITEM PLUS NEW PERIOD IN NATIONAL_ASSUMPTIONS_PROJ TABLE
                SET @SQL1 =CONCAT('INSERT INTO ',@NA_PROJECTION_TABLE,'
                            (ITEM_MASTER_SID,
                             PRICE_TYPE,
                             PERIOD_SID
                             )
                SELECT ID.ITEM_MASTER_SID,
                       @PRICE_TYPE,
                       PQ.PERIOD_SID
                FROM   #PERIOD_QUARTER PQ
                       CROSS JOIN #ITEM_DETAILS ID
                WHERE  PQ.PERIOD_SID BETWEEN ',@PROJ_PERIOD_START_SID,' AND ',@PROJ_PERIOD_END_SID,'
                       AND NOT EXISTS (SELECT 1
                                       FROM   ',@NA_PROJECTION_TABLE,' NAP
                                       WHERE  NAP.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                                              AND NAP.PERIOD_SID = PQ.PERIOD_SID
                                              AND NAP.PRICE_TYPE = ',@PRICE_TYPE,')')
											  EXEC sp_executesql @SQL1

                                            

                -------FORECASTING CALCULATION STARTS 
                IF @FORECAST_METHODOLOGY = 'WAC % FLEX' -- WAC % FLEX FORECASTING METHODOLOGY START
                  BEGIN 
                      SET @SQL1=CONCAT(';WITH ORDER_CTE
                           AS (SELECT IP.*,
                                      ID.UPPS,
                                      ROW_NUMBER()
                                        OVER (
                                          PARTITION BY ID.ITEM_MASTER_SID
                                          ORDER BY PERIOD_SID ) AS RN
                               FROM   #ITEM_PRICING IP
                                      INNER JOIN #ITEM_DETAILS ID
                                              ON IP.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                                                 AND PRICE_TYPE = ',@PRICE_BASIS,'),
                           RECURSIVE_CTE
                           AS (SELECT OC.ITEM_MASTER_SID,
                                      OC.PERIOD_SID,
                                      OC.WAC_INCREASE,
                                      OC.RN,
                                      PROJECTION_PRICE= ( B.BASELINE_PRICE * ( 1 + ( ISNULL(OC.WAC_INCREASE, 0) / 100 ) ) )
                               FROM   ORDER_CTE OC
                                      JOIN #BASELINE B
                                        ON B.ITEM_MASTER_SID = OC.ITEM_MASTER_SID
                               WHERE  OC.RN = 1
                               UNION ALL
                               SELECT OC.ITEM_MASTER_SID,
                                      OC.PERIOD_SID,
                                      OC.WAC_INCREASE,
                                      OC.RN,
                                      PROJECTION_PRICE=( RC.PROJECTION_PRICE * ( 1 + ( ISNULL(OC.WAC_INCREASE, 0) / 100 ) ) )
                               FROM   ORDER_CTE OC
                                      JOIN RECURSIVE_CTE RC
                                        ON OC.ITEM_MASTER_SID = RC.ITEM_MASTER_SID
                                           AND OC.RN = RC.RN + 1)
                    UPDATE NAP
                      SET    NAP.PROJECTION_PRICE = ISNULL(RC.PROJECTION_PRICE, 0)
                      FROM   ',@NA_PROJECTION_TABLE,' NAP
                             INNER JOIN RECURSIVE_CTE RC
                                     ON NAP.ITEM_MASTER_SID = RC.ITEM_MASTER_SID
                                        AND NAP.PERIOD_SID = RC.PERIOD_SID
                      WHERE  PRICE_TYPE = ',@PRICE_TYPE,'
                           
                      OPTION (MAXRECURSION 0);
                   ')
				   END
				  -- WAC % FLEX FORECASTING METHODOLOGY END 
                ELSE IF @FORECAST_METHODOLOGY = 'GROWTH' -- GROWTH FORECSTING METHODOLOGY START
                  BEGIN
                      SELECT @GROWTH_RATE = CASE @GROWTH_FREQUENCY
                                              WHEN 'ANNUALLY' THEN ( @GROWTH_RATE / 100.0 ) / 4.0
                                              WHEN 'SEMI-ANNUALLY' THEN ( @GROWTH_RATE / 100.0 ) / 2.0
                                              ELSE ( @GROWTH_RATE / 100.0 )
                                            END;

                   SET @SQL1=CONCAT(' ;
                      WITH ORDER_CTE
                           AS (SELECT IP.ITEM_MASTER_SID,
                                      PERIOD_SID,
                                      ROW_NUMBER()
                                        OVER (
                                          PARTITION BY IP.ITEM_MASTER_SID
                                          ORDER BY PERIOD_SID ) AS RN
                               FROM   #ITEM_PRICING IP
                               WHERE  PRICE_TYPE = @PRICE_BASIS), -- HAVE SAME PERIOD_SID FOR DIFFERENT PRICE TYPES SO I HAVE USED ONE PRICE TYPE(WAC) IN WHERE CLAUSE
                           RECURSIVE_CTE
                           AS (SELECT OC.ITEM_MASTER_SID,
                                      OC.PERIOD_SID,
                                      OC.RN,
                                      PROJECTION_PRICE= B.BASELINE_PRICE * ( 1 + ( @GROWTH_RATE ) )
                               FROM   ORDER_CTE OC
                                      JOIN #BASELINE B
                                        ON B.ITEM_MASTER_SID = OC.ITEM_MASTER_SID
                               WHERE  OC.RN = 1
                               UNION ALL
                               SELECT OC.ITEM_MASTER_SID,
                                      OC.PERIOD_SID,
                                      OC.RN,
                                      PROJECTION_PRICE=RC.PROJECTION_PRICE * ( 1 + ( @GROWTH_RATE ) )
                               FROM   ORDER_CTE OC
                                      JOIN RECURSIVE_CTE RC
                                        ON OC.ITEM_MASTER_SID = RC.ITEM_MASTER_SID
                                           AND OC.RN = RC.RN + 1)
                  UPDATE NAP
                      SET    NAP.PROJECTION_PRICE = ISNULL(RC.PROJECTION_PRICE, 0)
                      FROM   ',@NATIONAL_TABLE,' NAP
                             INNER JOIN RECURSIVE_CTE RC
                                     ON NAP.ITEM_MASTER_SID = RC.ITEM_MASTER_SID
                                        AND NAP.PERIOD_SID = RC.PERIOD_SID
                      WHERE  PRICE_TYPE = ',@PRICE_TYPE,'OPTION (MAXRECURSION 0);')
			

                        
      
                  END -- GROWTH FORECSTING METHODOLOGY END
				    EXEC sp_executesql @SQL1
                FETCH NA_CUR INTO @NA_PROJ_MASTER_SID, @PRICE_TYPE, @BASELINE_METHODOLOGY, @FORECAST_METHODOLOGY, @BASELINE_PERIOD, @ROLLING_PERIOD, @START_PERIOD, @END_PERIOD, @GROWTH_RATE, @GROWTH_FREQUENCY, @PRICE_BASIS
            END
				

          CLOSE NA_CUR;

          DEALLOCATE NA_CUR;
      -- NA_CUR END CURSOR TO PARSE EACH PRICE TYPE THAT HAS BEEN SETUP IN NATIONAL ASSUMPTIONS TAB.
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