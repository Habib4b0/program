IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NA_WAC_DATA'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NA_WAC_DATA]
  END

GO


CREATE PROCEDURE [dbo].[PRC_NA_WAC_DATA](@NA_PROJ_MASTER_SID INT,
                                         @USER_ID            INT,
                                         @SESSION_ID         VARCHAR(50))
AS
  BEGIN

  SET NOCOUNT ON

      DECLARE @ACTUAL_START_DATE     DATETIME,
              @PROJECTION_START_DATE DATETIME,
              @PROJECTION_END_DATE   DATETIME,
              @ACTUAL_END_DATE       DATETIME,
              @BUSINESS_PROCESS_TYPE VARCHAR(50),
              @PROJ_PERIOD_END_SID   INT,
              @ACT_PERIOD_START_SID  INT,
              @BUSINESS_UNIT         INT,
              @COMPANY_ID            INT,--------------------GAL-808
              @NA_NDC9_WAC           VARCHAR(100),
              @NA_NDC11_WAC          VARCHAR(100)

      SET @BUSINESS_PROCESS_TYPE = 'NATIONAL ASSUMPTIONS'
      SET @NA_NDC9_WAC =Concat('ST_NA_NDC9_WAC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
      SET @NA_NDC11_WAC =Concat('ST_NA_NDC11_WAC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

      SELECT @ACTUAL_START_DATE = ACTUAL_START_DATE,
             @ACTUAL_END_DATE = ACTUAL_END_DATE,
             @PROJECTION_START_DATE = PROJECTION_START_DATE,
             @PROJECTION_END_DATE = PROJECTION_END_DATE
      FROM   [DBO].[Udf_na_proj_dates](@BUSINESS_PROCESS_TYPE)

      ------------------GL COMPANY ID---------------
           IF OBJECT_ID('TEMPDB..#PERIOD') IS NOT NULL
              DROP TABLE #PERIOD

       CREATE TABLE #PERIOD (
              PERIOD_SID INT PRIMARY KEY
              ,PERIOD_YEAR INT
              ,PERIOD_QUARTER INT
              ,SEMI_ANNUAL INT
              ,PERIOD_MONTH INT
              )

       INSERT INTO #PERIOD (
              PERIOD_SID
              ,PERIOD_YEAR
              ,PERIOD_QUARTER
              ,SEMI_ANNUAL
              ,PERIOD_MONTH
              )
       SELECT PERIOD_SID
              ,[YEAR]
              ,[QUARTER]
              ,SEMI_ANNUAL
              ,MONTH
       FROM PERIOD
       WHERE PERIOD_DATE BETWEEN DATEADD(QQ, DATEDIFF(QQ, 0, @ACTUAL_START_DATE), 0)
                     AND @PROJECTION_END_DATE
                     
      IF Object_id('TEMPDB..#PERIOD_QUARTER') IS NOT NULL
        DROP TABLE #PERIOD_QUARTER

      CREATE TABLE #PERIOD_QUARTER
        (
           PERIOD_SID     INT PRIMARY KEY,
           PERIOD_YEAR    INT,
           PERIOD_QUARTER INT,
           SEMI_ANNUAL    INT,
           PERIOD_DATE    DATETIME
        )

      INSERT INTO #PERIOD_QUARTER
                  (PERIOD_SID,
                   PERIOD_YEAR,
                   PERIOD_QUARTER,
                   SEMI_ANNUAL,
                   PERIOD_DATE)
      SELECT Min(PERIOD_SID),
             [YEAR],
             [QUARTER],
             SEMI_ANNUAL,
             Min(PERIOD_DATE)
      FROM   PERIOD
      WHERE  PERIOD_DATE BETWEEN Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_START_DATE), 0) AND @PROJECTION_END_DATE
      GROUP  BY [YEAR],
                [QUARTER],
                SEMI_ANNUAL

      SELECT @ACT_PERIOD_START_SID = Max(CASE
                                           WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_START_DATE), 0) THEN PERIOD_SID
                                         END),
             @PROJ_PERIOD_END_SID = Max(CASE
                                          WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_END_DATE), 0) THEN PERIOD_SID
                                        END)
      FROM   (SELECT PERIOD_SID,
                    PERIOD_DATE
              FROM   #PERIOD_QUARTER
              WHERE  PERIOD_DATE IN ( Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_START_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_END_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_START_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_END_DATE), 0) )) A

      ------------------------------------------  ITEM DETAILS TABLE BASED ON PROJECTION 
      IF Object_id('TEMPDB..#PROJECTION_DETAILS') IS NOT NULL
        DROP TABLE #PROJECTION_DETAILS

      CREATE TABLE #PROJECTION_DETAILS
        (
           NA_PROJ_DETAILS_SID INT PRIMARY KEY,
           ITEM_MASTER_SID     INT UNIQUE,
           NA_PROJ_MASTER_SID  INT,
           NDC9                VARCHAR(20),
           ITEM_ID             VARCHAR(50),
           UPPS                NUMERIC(22, 6)
        )

              DECLARE @SQL  NVARCHAR(MAX)= ''
              SET @SQL = CONCAT('SELECT NA_PROJ_DETAILS_SID,
             NPD.ITEM_MASTER_SID,
             NA_PROJ_MASTER_SID,
             ITEM_ID,
             IM.NDC9,
             IM.UPPS
      FROM   NA_PROJ_DETAILS NPD
             INNER JOIN ITEM_MASTER IM
                     ON IM.ITEM_MASTER_SID = NPD.ITEM_MASTER_SID
      WHERE  NA_PROJ_MASTER_SID = ',@NA_PROJ_MASTER_SID,'
         --AND NOT EXISTS (SELECT 1 FROM ',@NA_NDC11_WAC,' NW WHERE NW.ITEM_MASTER_SID =  IM.ITEM_MASTER_SID )
		 ')

      INSERT INTO #PROJECTION_DETAILS
                  (NA_PROJ_DETAILS_SID,
                   ITEM_MASTER_SID,
                   NA_PROJ_MASTER_SID,
                   ITEM_ID,
                   NDC9,
                   UPPS)
   EXEC  sp_executesql @SQL
      --------------------------------------------------GALUAT-808-BUSINESS_UNIT-----------------------------------------
      SELECT @BUSINESS_UNIT = BUSINESS_UNIT,
             @COMPANY_ID = NA.COMPANY_MASTER_SID
      FROM   NA_PROJ_MASTER NA
      WHERE  NA_PROJ_MASTER_SID = @NA_PROJ_MASTER_SID

      -------------------------------------------------------------------------------------------- WAC CALCULATION STARTS HERE
      DECLARE @ITEMID   [DBO].[UDT_ITEM],
              @ITEM_UOM VARCHAR(50) = 'UN'

      INSERT INTO @ITEMID
      SELECT ITEM_MASTER_SID
      FROM   #PROJECTION_DETAILS

      DECLARE @FORECAST_NAME    VARCHAR(50),
              @FORECAST_VERSION VARCHAR(15)

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
             AND HT.DESCRIPTION = 'EX-FACTORY SALES'
             AND FT.BUSINESS_UNIT = @BUSINESS_UNIT --------------------GAL-808
             AND FT.COMPANY = @COMPANY_ID --------------------GAL-808
      ORDER  BY FT.FROM_PERIOD DESC

      DECLARE @CURR_QUART_PERIOD_SID INT,@PREV_MONTH_SID int

    --  IF Object_id('TEMPDB..#I_PRICING') IS NOT NULL
     --   DROP TABLE #I_PRICING

      SELECT @CURR_QUART_PERIOD_SID = PERIOD_SID
      FROM   PERIOD
      WHERE  PERIOD_DATE = ( Dateadd(qq, Datediff(qq, 0, Getdate()) , 0) )

      SELECT @PREV_MONTH_SID = PERIOD_SID
      FROM   PERIOD
      WHERE  PERIOD_DATE = dateadd(mm,-1, Dateadd(DD, 1, Eomonth(Getdate(), -1)))


   /*   IF Object_id('tempdb..#I_PRICING') IS NOT NULL
        DROP TABLE #I_PRICING

      SELECT NDC9 AS NDC,
             PQ.PERIOD_SID,
             ITEM_PRICE AS ACTUAL_PRICE,
             PERIOD_YEAR,
             PERIOD_QUARTER,
             UPPS,
             Day(Eomonth(pq.PERIOD_DATE)) AS NO_OF_DAYS,
             PRICING_QUALIFIER AS price_type, 
             pd.ITEM_MASTER_SID
      INTO   #I_PRICING
      FROM   Udf_item_pricing(@ITEMID, 'EQWAC,BQWAC,MQWAC,AVGQWAC,WAC', @ACT_PERIOD_START_SID, @CURR_QUART_PERIOD_SID - 1, @ITEM_UOM) IP
             INNER JOIN #PERIOD_QUARTER PQ
                     ON IP.PERIOD_SID = PQ.PERIOD_SID
             INNER JOIN #PROJECTION_DETAILS PD
                     ON PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
*/


              IF OBJECT_ID('TEMPDB..#UDF_ITEM_PRICING') IS NOT NULL
              DROP TABLE #UDF_ITEM_PRICING

              CREATE TABLE #UDF_ITEM_PRICING (
                           ITEM_MASTER_SID INT
                           ,PERIOD_SID INT
                           ,ITEM_PRICE NUMERIC(22, 6)
                           ,PRICING_QUALIFIER VARCHAR(50)
                           )

                           INSERT INTO #UDF_ITEM_PRICING (
                                  ITEM_MASTER_SID
                                  ,PERIOD_SID
                                  ,ITEM_PRICE
                                  ,PRICING_QUALIFIER
                                  )
                           SELECT ITEM_MASTER_SID
                                  ,PERIOD_SID
                                  ,ITEM_PRICE
                                  ,PRICING_QUALIFIER
                           --INTO #UDF_ITEM_PRICING
                           FROM UDF_ITEM_PRICING(@ITEMID, 'BQWAC,MQWAC,WAC,AVGQWAC,EQWAC', @ACT_PERIOD_START_SID, @PREV_MONTH_SID, @ITEM_UOM)
              IF OBJECT_ID('TEMPDB..#I_PRICING') IS NOT NULL
              DROP TABLE #I_PRICING

       CREATE TABLE #I_PRICING (NDC VARCHAR(200)
                                                ,PERIOD_SID INT
                                                ,ACTUAL_PRICE NUMERIC(22, 6)
                                                ,PERIOD_YEAR INT
                                                ,PERIOD_QUARTER INT
                                                ,UPPS NUMERIC(22, 6)
                                                ,NO_OF_DAYS INT
                                                ,PRICE_TYPE VARCHAR(50)
                                                ,ITEM_MASTER_SID INT
                                                )
                     
                     /*INSERT INTO #I_PRICING (
                           NDC
                           ,PERIOD_SID
                           ,ACTUAL_PRICE
                           ,PERIOD_YEAR
                           ,PERIOD_QUARTER
                           ,UPPS
                           ,NO_OF_DAYS
                           ,PRICE_TYPE
                           ,ITEM_MASTER_SID
                           )
                     SELECT NDC9 AS NDC
                           ,PQ.PERIOD_SID
                           ,ITEM_PRICE AS ACTUAL_PRICE
                           ,PERIOD_YEAR
                           ,PERIOD_QUARTER
                           ,UPPS
                           ,DAY(EOMONTH(PQ.PERIOD_DATE)) AS NO_OF_DAYS
                           ,PRICING_QUALIFIER AS PRICE_TYPE
                           ,PD.ITEM_MASTER_SID
                     --- INTO   #I_PRICING
                     FROM #UDF_ITEM_PRICING IP
                     INNER JOIN #PERIOD_QUARTER PQ ON IP.PERIOD_SID = PQ.PERIOD_SID
                     INNER JOIN #PROJECTION_DETAILS PD ON PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                     WHERE IP.PRICING_QUALIFIER IN ('BQWAC','AVGQWAC','WAC')
                     
                     INSERT INTO #I_PRICING (
                           NDC
                           ,PERIOD_SID
                           ,ACTUAL_PRICE
                           ,PERIOD_YEAR
                           ,PERIOD_QUARTER
                           ,UPPS
                           ,NO_OF_DAYS
                           ,PRICE_TYPE
                           ,ITEM_MASTER_SID
                           )
                     SELECT NDC9 AS NDC
                           ,PQ.PERIOD_SID - 1
                           ,ITEM_PRICE AS ACTUAL_PRICE
                           ,YEAR PERIOD_YEAR
                           ,QUARTER PERIOD_QUARTER
                           ,UPPS
                           ,DAY(EOMONTH(PQ.PERIOD_DATE)) AS NO_OF_DAYS
                           ,PRICING_QUALIFIER AS PRICE_TYPE
                           ,PD.ITEM_MASTER_SID
                     --INTO   #I_PRICING
                     FROM #UDF_ITEM_PRICING IP
                     INNER JOIN PERIOD PQ ON IP.PERIOD_SID = PQ.PERIOD_SID
                     INNER JOIN #PROJECTION_DETAILS PD ON PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                     WHERE IP.PRICING_QUALIFIER IN ('MQWAC')
                           AND PQ.MONTH IN (2,5,8,11)
                     
                     INSERT INTO #I_PRICING (
                           NDC
                           ,PERIOD_SID
                           ,ACTUAL_PRICE
                           ,PERIOD_YEAR
                           ,PERIOD_QUARTER
                           ,UPPS
                           ,NO_OF_DAYS
                           ,PRICE_TYPE
                           ,ITEM_MASTER_SID
                           )
                     SELECT NDC9 AS NDC
                           ,PQ.PERIOD_SID - 2
                           ,ITEM_PRICE AS ACTUAL_PRICE
                           ,YEAR PERIOD_YEAR
                           ,QUARTER PERIOD_QUARTER
                           ,UPPS
                           ,DAY(EOMONTH(PQ.PERIOD_DATE)) AS NO_OF_DAYS
                           ,PRICING_QUALIFIER AS PRICE_TYPE
                           ,PD.ITEM_MASTER_SID
                     FROM #UDF_ITEM_PRICING IP
                     INNER JOIN PERIOD PQ ON IP.PERIOD_SID = PQ.PERIOD_SID
                     INNER JOIN #PROJECTION_DETAILS PD ON PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                     WHERE IP.PRICING_QUALIFIER IN ('EQWAC')
                           AND PQ.MONTH IN (3,6,9,12)
                           */
;

       WITH CTE
       AS (
              SELECT NDC9 AS NDC
                     ,ITEM_PRICE AS ACTUAL_PRICE
                     ,LEAD(ITEM_PRICE) OVER (
                                  PARTITION BY IP.ITEM_MASTER_SID
                                  ,IP.PRICING_QUALIFIER
                                  ,P.PERIOD_QUARTER
                                  ,PERIOD_YEAR ORDER BY IP.PERIOD_SID
                                  ) AS LEAD_ITEM_PRICE
                     ,LEAD(ITEM_PRICE, 2) OVER (
                                  PARTITION BY IP.ITEM_MASTER_SID
                                  ,IP.PRICING_QUALIFIER
                                  ,P.PERIOD_QUARTER
                                  ,PERIOD_YEAR ORDER BY IP.PERIOD_SID
                                  ) AS LEAD_ITEM_PRICE_OFFSET
                     ,IP.PERIOD_SID
                     ,PERIOD_YEAR
                     ,PERIOD_QUARTER
                     ,P.PERIOD_MONTH
                     ,PRICING_QUALIFIER AS PRICE_TYPE
                     ,IP.ITEM_MASTER_SID
                     ,IM.UPPS
              FROM #UDF_ITEM_PRICING IP
              JOIN #PERIOD P ON P.PERIOD_SID = IP.PERIOD_SID
              JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
              where ip.PRICING_QUALIFIER in ('BQWAC','MQWAC','AVGQWAC','EQWAC')
              )

              INSERT INTO #I_PRICING (
                           NDC
                           ,PERIOD_SID
                           ,ACTUAL_PRICE
                           ,PERIOD_YEAR
                           ,PERIOD_QUARTER
                           ,UPPS
                           ,NO_OF_DAYS
                           ,PRICE_TYPE
                           ,ITEM_MASTER_SID
                           )

       SELECT C.NDC
              ,P.PERIOD_SID
              ,COALESCE(NULLIF(ACTUAL_PRICE,0), NULLIF(LEAD_ITEM_PRICE,0), NULLIF(LEAD_ITEM_PRICE_OFFSET,0), 0) ACTUAL_PRICE
              ,P.PERIOD_YEAR
              ,P.PERIOD_QUARTER
              ,UPPS
              ,DAY(EOMONTH(P.PERIOD_DATE)) AS NO_OF_DAYS
              ,C.PRICE_TYPE
              ,ITEM_MASTER_SID
       FROM CTE C
       JOIN #PERIOD_QUARTER P ON P.PERIOD_SID = C.PERIOD_SID
              ORDER BY NDC
              ,PRICE_TYPE
              ,PERIOD_SID


      IF Object_id('tempdb..#gts') IS NOT NULL
        DROP TABLE #gts

      SELECT ITEM_MASTER_SID,
             FORECAST_GTS_SALES AS GTS_SALES,
             FORECAST_GTS_UNITS aS GTS_UNITS,
             actual_gts_sales,
             actual_gts_units,
             P.QUARTER,
             P.MONTH,
             P.YEAR,
             Day(Eomonth(P.PERIOD_DATE)) aS NO_OF_DAYS,
             FORECAST_PRICE aS WAC_PRICE,
             PERIOD_DATE,
             GTS.PERIOD_SID
      INTO   #gts
      FROM   [DBO].[Udf_gts_wac] (@ITEMID, @ACT_PERIOD_START_SID, @PROJ_PERIOD_END_SID + 2, @FORECAST_NAME, @FORECAST_VERSION) GTS
             INNER JOIN PERIOD P
                     ON GTS.PERIOD_SID = P.PERIOD_SID



--------------cel-360

      SET @sql =''

       SET @sql = Concat(@sql, '  ;WITH PERIOD_WAC
     AS (SELECT NDC9,
                BQWAC=AVG( BQWAC  ),
                EQWAC=AVG( EQWAC ),
                MQWAC=AVG( MQWAC  ),
                AVGQWAC=AVG( AVGQWAC ),
                MIN(PERIOD_SID) AS  PERIOD_SID
                               FROM (SELECT NDC9,ITEM_MASTER_SID,
                BQWAC=( BQWAC / NULLIF(UPPS, 0) ),
                EQWAC=( EQWAC / NULLIF(UPPS, 0) ),
                MQWAC=( MQWAC / NULLIF(UPPS, 0) ),
                AVGQWAC=( AVGQWAC / NULLIF(UPPS, 0) ),
                PERIOD_SID
                               , YEAR,
                           QUARTER
         FROM   (SELECT NDC9,
                                              C.ITEM_MASTER_SID,
                                              YEAR,
                        QUARTER,
                        BQWAC= Max(CASE
                                     WHEN MONTH IN( 1, 4, 7, 10 ) THEN GTS_SALES
                                   END) / NULLIF(Max(CASE
                                                       WHEN MONTH IN( 1, 4, 7, 10 ) THEN GTS_UNITS
                                                     END), 0),
                        EQWAC = Max(CASE
                                      WHEN MONTH IN( 3, 6, 9, 12 ) THEN GTS_SALES
                                    END) / NULLIF(Max(CASE
                                                        WHEN MONTH IN( 3, 6, 9, 12 ) THEN GTS_UNITS
                                                      END), 0),
                        MQWAC= Max(CASE
                                     WHEN MONTH IN( 2, 5, 8, 11 ) THEN GTS_SALES
                                   END) / NULLIF(Max(CASE
                                                       WHEN MONTH IN( 2, 5, 8, 11 ) THEN GTS_UNITS
                                                     END), 0),
                        AVGQWAC = Avg(WAC_PRICE),
                        PERIOD_SID= Min(PERIOD_SID),
                        UPPS=Max(UPPS)
                 FROM   #GTS C
                        INNER JOIN #PROJECTION_DETAILS ID
                                ON C.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                 WHERE  C.PERIOD_SID BETWEEN ', @CURR_QUART_PERIOD_SID, ' AND ', @PROJ_PERIOD_END_SID, ' + 2
                  GROUP  BY NDC9,
                                                C.ITEM_MASTER_SID,
                           YEAR,
                           QUARTER)A                                         
                           ) B
                                              GROUP  BY    NDC9,                                          
                           YEAR,
                           QUARTER
         UNION ALL
         SELECT NDC9=NDC,
                BQWAC= Avg(CASE
                             WHEN PRICE_TYPE = ''BQWAC'' THEN ACTUAL_PRICE
                           END),
                EQWAC= Avg(CASE
                             WHEN PRICE_TYPE = ''EQWAC'' THEN ACTUAL_PRICE
                           END),
                MQWAC= Avg(CASE
                             WHEN PRICE_TYPE = ''MQWAC'' THEN ACTUAL_PRICE
                           END),
                AVGQWAC= Avg(CASE
                               WHEN PRICE_TYPE = ''AVGQWAC'' THEN ACTUAL_PRICE
                             END),
                Min(PERIOD_SID)
         FROM   #I_PRICING
         WHERE  PRICE_TYPE IN ( ''BQWAC'', ''EQWAC'', ''MQWAC'', ''AVGQWAC'' )
              and PERIOD_SID < ', @CURR_QUART_PERIOD_SID,'
         GROUP  BY NDC,
                   PERIOD_YEAR,
                   PERIOD_QUARTER),
     DAY_WEIGHTED_WAC_DATA
     AS (SELECT FORECAST_WAC_PRICE= (( GTS_SALES / NULLIF(GTS_UNITS, 0) )) / NULLIF(PD.UPPS, 0),
                ACTUAL_WAC_PRICE=0,
                PD.ITEM_MASTER_SID,
                GTS.PERIOD_DATE,
                [QUARTER],
                [YEAR],
                PERIOD_SID,
                NO_OF_DAYS=Datediff(DAY, GTS.PERIOD_DATE, Dateadd(MONTH, 1, GTS.PERIOD_DATE))
         FROM   #GTS GTS
                JOIN #PROJECTION_DETAILS PD
                  ON PD.ITEM_MASTER_SID = GTS.ITEM_MASTER_SID
         WHERE  GTS.PERIOD_DATE BETWEEN Dateadd(DD, 1, Eomonth(Getdate(), -1)) AND ''', @PROJECTION_END_DATE, '''
         UNION ALL
         SELECT FORECAST_WAC_PRICE=0,
                 -- ACTUAL_PRICE,
                         ITEM_PRICE ACTUAL_PRICE,
                IP.ITEM_MASTER_SID,
                P.PERIOD_DATE,
                [QUARTER],
                [YEAR],
                P.PERIOD_SID,
                NO_OF_DAYS=Datediff(DAY, P.PERIOD_DATE, Dateadd(MONTH, 1, P.PERIOD_DATE))
         FROM   #UDF_ITEM_PRICING IP
              --FROM   #I_PRICING IP
                JOIN PERIOD P
                  ON IP.PERIOD_SID = P.PERIOD_SID
         WHERE  P.PERIOD_SID BETWEEN ', @ACT_PERIOD_START_SID, ' AND ', @PREV_MONTH_SID, '
                 --AND price_type = ''WAC''
                           AND PRICING_QUALIFIER = ''WAC''),

     DAY_WEIGHTED_WAC_ITEM_WISE
     AS (SELECT NDC9,C.ITEM_MASTER_SID,
                QUARTER,
                [YEAR],
                PERIOD_SID = Min(PERIOD_SID),
                DAY_WEIGHTED_WAC= Sum(CASE
                                        WHEN PERIOD_DATE < Cast(Dateadd(DD, -( Day(Getdate()) - 1 ), Getdate()) AS DATE) THEN ( ACTUAL_WAC_PRICE * NO_OF_DAYS )
                                        ELSE ( FORECAST_WAC_PRICE * NO_OF_DAYS )
                                      END) / NULLIF(Sum(CASE
                                                          WHEN PERIOD_DATE < Cast(Dateadd(DD, -( Day(Getdate()) - 1 ), Getdate()) AS DATE) THEN NULLIF(NO_OF_DAYS, 0)
                                                          ELSE NULLIF(NO_OF_DAYS, 0)
                                                        END), 0),
                UPPS=Max(UPPS)
         FROM   DAY_WEIGHTED_WAC_DATA C
                INNER JOIN #PROJECTION_DETAILS ID
                       ON C.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
         GROUP  BY NDC9,C.ITEM_MASTER_SID,
                   QUARTER,
                   [YEAR]),

     DAY_WEIGHTED_WAC
     AS (SELECT NDC9,
                QUARTER,
                [YEAR],
                PERIOD_SID = Min(PERIOD_SID),
                DAY_WEIGHTED_WAC= avg(DAY_WEIGHTED_WAC),
                UPPS=Max(UPPS)
         FROM   DAY_WEIGHTED_WAC_ITEM_WISE C                
         GROUP  BY NDC9,
                   QUARTER,
                   [YEAR]),')
     SET @sql = Concat(@sql, '
	 SALES_WEIGHTED_WAC
     AS (SELECT NDC9,PERIOD_SID=MIN(PERIOD_SID),SALES_WEIGHTED_WAC=AVG(SALES_WEIGHTED_WAC) FROM (SELECT NDC9,ITEM_MASTER_SID,
                QUARTER,
                [YEAR],
                PERIOD_SID,SALES_WEIGHTED_WAC=COALESCE(SALES_WEIGHTED_WAC/NULLIF(UPPS,0),0) FROM (SELECT ID.NDC9,
                               C.ITEM_MASTER_SID,
                QUARTER,
                [YEAR],
                PERIOD_SID = MIN(PERIOD_SID),
                SALES_WEIGHTED_WAC=SUM(CASE
                                         WHEN PERIOD_DATE < CAST(DATEADD(DD, -( DAY(GETDATE()) - 1 ), GETDATE()) AS DATE) THEN ACTUAL_GTS_SALES
                                         ELSE GTS_SALES
                                       END) / NULLIF(SUM(CASE
                                                           WHEN PERIOD_DATE < CAST(DATEADD(DD, -( DAY(GETDATE()) - 1 ), GETDATE()) AS DATE) THEN NULLIF(ACTUAL_GTS_UNITS, 0)
                                                           ELSE NULLIF(GTS_UNITS, 0)
                                                         END), 0),
                UPPS=MAX(UPPS)
         FROM   #GTS C
                INNER JOIN #PROJECTION_DETAILS ID
                        ON C.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
        GROUP  BY ID.NDC9,C.ITEM_MASTER_SID,
                   QUARTER,
                   [YEAR]) A) B GROUP BY NDC9,
                QUARTER,
                [YEAR])
INSERT INTO ', @NA_NDC9_WAC, '
            (NDC9,
             PERIOD_SID,
             BQWAC,
             EQWAC,
             MQWAC,
             AVGQWAC,
             DAY_WEIGHTED_WAC,
             SALES_WEIGHTED_WAC)
SELECT PW.NDC9,
       PW.PERIOD_SID,
       PW.BQWAC,
       PW.EQWAC,
       PW.MQWAC,
       PW.AVGQWAC,
       DAY_WEIGHTED_WAC,
       SALES_WEIGHTED_WAC SALES_WEIGHTED_WAC
FROM   PERIOD_WAC PW
       JOIN DAY_WEIGHTED_WAC DW
         ON PW.NDC9 = DW.NDC9
            AND PW.PERIOD_SID = DW.PERIOD_SID
       JOIN SALES_WEIGHTED_WAC SW
         ON SW.NDC9 = PW.NDC9
            AND PW.PERIOD_SID = SW.PERIOD_SID;')

      EXEC sp_executesql @sql

      SET @sql = ''
      SET @sql = Concat(@sql, ';WITH PERIOD_WAC
     AS (SELECT ITEM_MASTER_SID,
                BQWAC=( BQWAC / NULLIF(UPPS, 0) ),
                EQWAC=( EQWAC / NULLIF(UPPS, 0) ),
                MQWAC=( MQWAC / NULLIF(UPPS, 0) ),
                AVGQWAC=( AVGQWAC / NULLIF(UPPS, 0) ),
                PERIOD_SID
         FROM   (SELECT c.ITEM_MASTER_SID,
                        BQWAC= Max(CASE
                                     WHEN MONTH IN( 1, 4, 7, 10 ) THEN GTS_SALES
                                   END) / NULLIF(Max(CASE
                                                       WHEN MONTH IN( 1, 4, 7, 10 ) THEN GTS_UNITS
                                                     END), 0),
                        EQWAC = Max(CASE
                                      WHEN MONTH IN( 3, 6, 9, 12 ) THEN GTS_SALES
                                    END) / NULLIF(Max(CASE
                                                        WHEN MONTH IN( 3, 6, 9, 12 ) THEN GTS_UNITS
                                                      END), 0),
                        MQWAC= Max(CASE
                                     WHEN MONTH IN( 2, 5, 8, 11 ) THEN GTS_SALES
                                   END) / NULLIF(Max(CASE
                                                       WHEN MONTH IN( 2, 5, 8, 11 ) THEN GTS_UNITS
                                                     END), 0),
                        AVGQWAC = Avg(WAC_PRICE),
                        PERIOD_SID= Min(PERIOD_SID),
                        UPPS=Max(UPPS)
                 FROM   #GTS C
                        INNER JOIN #PROJECTION_DETAILS ID
                                ON C.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
                 WHERE  C.PERIOD_SID BETWEEN ', @CURR_QUART_PERIOD_SID, ' AND ', @PROJ_PERIOD_END_SID, ' + 2
                 GROUP  BY C.ITEM_MASTER_SID,
                           YEAR,
                           QUARTER)A
         UNION ALL
         SELECT ITEM_MASTER_SID,
                BQWAC= Avg(CASE
                             WHEN PRICE_TYPE = ''BQWAC'' THEN ACTUAL_PRICE
                           END),
                EQWAC= Avg(CASE
                             WHEN PRICE_TYPE = ''EQWAC'' THEN ACTUAL_PRICE
                           END),
                MQWAC= Avg(CASE
                             WHEN PRICE_TYPE = ''MQWAC'' THEN ACTUAL_PRICE
                           END),
                AVGQWAC= Avg(CASE
                               WHEN PRICE_TYPE = ''AVGQWAC'' THEN ACTUAL_PRICE
                             END),
                Min(PERIOD_SID)
         FROM   #I_PRICING
         WHERE  PRICE_TYPE IN ( ''BQWAC'', ''EQWAC'', ''MQWAC'', ''AVGQWAC'' )
              and PERIOD_SID < ', @CURR_QUART_PERIOD_SID,'
         GROUP  BY ITEM_MASTER_SID,
                   PERIOD_YEAR,
                   PERIOD_QUARTER),
     DAY_WEIGHTED_WAC_DATA
     AS (SELECT FORECAST_WAC_PRICE= (( GTS_SALES / NULLIF(GTS_UNITS, 0) )) / NULLIF(PD.UPPS, 0),
                ACTUAL_WAC_PRICE=0,
                PD.ITEM_MASTER_SID,
                GTS.PERIOD_DATE,
                [QUARTER],
                [YEAR],
                PERIOD_SID,
                NO_OF_DAYS=Datediff(DAY, GTS.PERIOD_DATE, Dateadd(MONTH, 1, GTS.PERIOD_DATE))
         FROM   #GTS GTS
                JOIN #PROJECTION_DETAILS PD
                  ON PD.ITEM_MASTER_SID = GTS.ITEM_MASTER_SID
         WHERE  GTS.PERIOD_DATE BETWEEN Dateadd(DD, 1, Eomonth(Getdate(), -1)) AND ''', @PROJECTION_END_DATE, '''
         UNION ALL
         SELECT FORECAST_WAC_PRICE=0,
                 --ACTUAL_PRICE,
                           ITEM_PRICE ACTUAL_PRICE,
                IP.ITEM_MASTER_SID,
                P.PERIOD_DATE,
                [QUARTER],
                [YEAR],
                P.PERIOD_SID,
                NO_OF_DAYS=Datediff(DAY, P.PERIOD_DATE, Dateadd(MONTH, 1, P.PERIOD_DATE))
           --FROM   #I_PRICING IP
              FROM   #UDF_ITEM_PRICING IP
                JOIN PERIOD P
                  ON IP.PERIOD_SID = P.PERIOD_SID
         WHERE  P.PERIOD_SID BETWEEN ', @ACT_PERIOD_START_SID, ' AND ', @PREV_MONTH_SID, '
                 --AND price_type = ''WAC''
                           AND PRICING_QUALIFIER = ''WAC''),
     DAY_WEIGHTED_WAC
     AS (SELECT ID.ITEM_MASTER_SID,
                QUARTER,
                [YEAR],
                PERIOD_SID = Min(PERIOD_SID),
                DAY_WEIGHTED_WAC= Sum(CASE
                                        WHEN PERIOD_DATE < Cast(Dateadd(DD, -( Day(Getdate()) - 1 ), Getdate()) AS DATE) THEN ( ACTUAL_WAC_PRICE * NO_OF_DAYS )
                                        ELSE ( FORECAST_WAC_PRICE * NO_OF_DAYS )
                                      END) / NULLIF(Sum(CASE
                                                          WHEN PERIOD_DATE < Cast(Dateadd(DD, -( Day(Getdate()) - 1 ), Getdate()) AS DATE) THEN NULLIF(NO_OF_DAYS, 0)
                                                          ELSE NULLIF(NO_OF_DAYS, 0)
                                                        END), 0),
                UPPS=Max(UPPS)
         FROM   DAY_WEIGHTED_WAC_DATA C
                INNER JOIN #PROJECTION_DETAILS ID
                        ON C.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
         GROUP  BY ID.ITEM_MASTER_SID,
                   QUARTER,
                   [YEAR]),
     SALES_WEIGHTED_WAC
     AS (SELECT ID.ITEM_MASTER_SID,
                QUARTER,
                [YEAR],
                PERIOD_SID = Min(PERIOD_SID),
                SALES_WEIGHTED_WAC=Sum(CASE
                                         WHEN PERIOD_DATE < Cast(Dateadd(DD, -( Day(Getdate()) - 1 ), Getdate()) AS DATE) THEN ACTUAL_GTS_SALES
                                         ELSE GTS_SALES
                                       END) / NULLIF(Sum(CASE
                                                           WHEN PERIOD_DATE < Cast(Dateadd(DD, -( Day(Getdate()) - 1 ), Getdate()) AS DATE) THEN NULLIF(ACTUAL_GTS_UNITS, 0)
                                                           ELSE NULLIF(GTS_UNITS, 0)
                                                         END), 0),
                UPPS=Max(UPPS)
         FROM   #GTS C
                INNER JOIN #PROJECTION_DETAILS ID
                        ON C.ITEM_MASTER_SID = ID.ITEM_MASTER_SID
         GROUP  BY ID.ITEM_MASTER_SID,
                   QUARTER,
                   [YEAR])
INSERT INTO ', @NA_NDC11_WAC, '
            (ITEM_MASTER_SID,
             PERIOD_SID,
             BQWAC,
             EQWAC,
             MQWAC,
             AVGQWAC,
             DAY_WEIGHTED_WAC,
             SALES_WEIGHTED_WAC)
SELECT PW.ITEM_MASTER_SID,
       PW.PERIOD_SID,
       PW.BQWAC,
       PW.EQWAC,
       PW.MQWAC,
       PW.AVGQWAC,
       DAY_WEIGHTED_WAC,
       (SALES_WEIGHTED_WAC/nullif(sw.UPPS,0)) SALES_WEIGHTED_WAC
FROM   PERIOD_WAC PW
       JOIN DAY_WEIGHTED_WAC DW
         ON PW.ITEM_MASTER_SID = DW.ITEM_MASTER_SID
            AND PW.PERIOD_SID = DW.PERIOD_SID
       JOIN SALES_WEIGHTED_WAC SW
         ON SW.ITEM_MASTER_SID = PW.ITEM_MASTER_SID
            AND PW.PERIOD_SID = SW.PERIOD_SID ')

      EXEC sp_executesql @sql
  END 

GO