IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_MANDATED_SALES_PROJECTION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_MANDATED_SALES_PROJECTION]
  END

GO

CREATE PROCEDURE [dbo].[PRC_MANDATED_SALES_PROJECTION](@PROJECTION INT,
                                                      @USER_ID    INT,
                                                      @SESSION_ID INT)
AS
  BEGIN
      SET NOCOUNT ON

      DECLARE @FROM_DATE         DATETIME,
              @PROJECTION_DATE   DATETIME

      BEGIN TRY
          SET @FROM_DATE= (SELECT Dateadd(yy, Datediff(yy, 0, Getdate()) - 3, 0))

          SELECT @PROJECTION_DATE = Dateadd(day, -1, Dateadd(qq, Datediff(qq, 0, TO_DATE) + 1, 0)) --last day of quarter
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION

          ------- CCP COMBINATION INSERTION-----------------------------
          SELECT CCP.COMPANY_MASTER_SID,
                 CCP.CONTRACT_MASTER_SID,
                 CCP.ITEM_MASTER_SID,
                 PD.PROJECTION_DETAILS_SID,
                 PM.PROJECTION_MASTER_SID,
                 NSPM.METHODOLOGY,
                 NSPM.CALCULATION_BASED,
                 NSPM.CALCULATION_PERIODS,
                 NSPM.[USER_ID],
                 NSPM.[SESSION_ID]
          INTO   #TEMP_CCP
          FROM   CCP_DETAILS CCP
                 JOIN PROJECTION_DETAILS PD
                   ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                 JOIN ST_M_SALES_PROJECTION_MASTER NSPM
                   ON PD.PROJECTION_DETAILS_SID = NSPM.PROJECTION_DETAILS_SID
                 JOIN PROJECTION_MASTER PM
                   ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
          WHERE  PM.PROJECTION_MASTER_SID = @PROJECTION
                 AND NSPM.[USER_ID] = @USER_ID
                 AND NSPM.[SESSION_ID] = @SESSION_ID
                 AND NSPM.CHECK_RECORD = 1

          -------------------------------------------------------------- QUARTER PERIOD
          DECLARE @PERIOD_QUARTER TABLE
            (
               PERIOD_SID     INT PRIMARY KEY,
               PERIOD_YEAR    INT,
               PERIOD_QUARTER INT,
               SEMI_ANNUAL    INT,
               PERIOD_DATE    DATETIME
            )

          INSERT INTO @PERIOD_QUARTER
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
          GROUP  BY [YEAR],
                    [QUARTER],
                    SEMI_ANNUAL

          -----------------------------------------NEXT BASELINE OF DATE-----------------------
          IF EXISTS(SELECT 1
                    FROM   #TEMP_CCP CCP
                    WHERE  METHODOLOGY IN( 'SINGLE PERIOD', 'AVERAGE' ))
            BEGIN
                SELECT T_CCP.PROJECTION_DETAILS_SID,
                       Dateadd(mm, 1, Max(PERIOD_DATE)) AS [Start_date]---Dateadd(mm,1,max(PERIOD_DATE))--min(Dateadd(mm, 3, PERIOD_DATE))
                INTO   #NEXTQUARTER
                FROM   #TEMP_CCP T_CCP
                       CROSS APPLY (SELECT FN.TOKEN,
                                           P.PERIOD_SID,
                                           P.QUARTER,
                                           P.YEAR,
                                           P.PERIOD_DATE,
                                           T_CCP.PROJECTION_DETAILS_SID
                                    FROM   Udf_splitstring(T_CCP.CALCULATION_PERIODS, ',') FN
                                           JOIN PERIOD P
                                             ON 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
                                                + CONVERT(VARCHAR(4), P.YEAR) = FN.TOKEN) CS
                GROUP  BY T_CCP.PROJECTION_DETAILS_SID
            END

          --------------------------------------------- WAC PRICE CALCULATION  ------------------
          DECLARE @ITEMID UDT_ITEM_PRICING

          INSERT INTO @ITEMID
          SELECT DISTINCT IM.ITEM_ID
          FROM   ITEM_MASTER IM
          WHERE  EXISTS (SELECT 1
                         FROM   #TEMP_CCP A
                         WHERE  PROJECTION_MASTER_SID = @PROJECTION
                                AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID)

          DECLARE @FROM_DATE_AVG       DATETIME=Dateadd(MM, Datediff(MM, 0, @FROM_DATE) - 3, 0),
                  @PROJECTION_DATE_AVG DATETIME=Dateadd(MONTH, Datediff(MONTH, 0, @PROJECTION_DATE), 0)

          SELECT DISTINCT [YEAR],
                          'Q' + CONVERT(VARCHAR(8), P.[QUARTER]) + ' '
                          + CONVERT(VARCHAR(8), P.[YEAR]) PERIOD,
                          P.MONTH,
                          C.ITEM_ID,
                          P.PERIOD_DATE
          INTO   #QUARTER_PERIOD
          FROM   PERIOD P
                 CROSS JOIN (SELECT DISTINCT ITEMID AS ITEM_ID
                             FROM   @ITEMID) C
          WHERE  P.PERIOD_DATE BETWEEN @FROM_DATE_AVG AND @PROJECTION_DATE_AVG

          CREATE TABLE #WAC
            (
               ITEMID          VARCHAR(50),
               ITEM_MASTER_SID INT,
               QUARTER_WAC     NUMERIC(22, 6),
               PERIOD          VARCHAR(20),
               WAC_INCREASE    NUMERIC(22, 6),
               PERIOD_DATE     DATE
            );

          WITH QUARTER_WAC
               AS (SELECT Q.ITEM_ID,
                          Isnull(( Sum(ITEM_PRICE * DAYS_DIFFERENCE) / NULLIF(Sum(DAYS_DIFFERENCE), 0) ), 0) AS QUARTER_WAC,
                          Q.PERIOD,
                          0                                                                                  AS [SOURCE]
                   FROM   Udf_month_wac(@ITEMID, @FROM_DATE, @PROJECTION_DATE) F
                          RIGHT JOIN #QUARTER_PERIOD Q
                                  ON F.START_QUARTER = Q.PERIOD
                                     AND Q.ITEM_ID = F.ITEM_ID
                   GROUP  BY Q.ITEM_ID,
                             Q.PERIOD,
                             Q.PERIOD_DATE
                   UNION ALL
                   SELECT ITEM_ID,
                          PRICE                                        AS QUARTER_WAC,
                          'Q' + CONVERT(VARCHAR(8), [FORECAST_QUART])
                          + ' ' + CONVERT(VARCHAR(8), [FORECAST_YEAR]) AS PERIOD,
                          1                                            AS [SOURCE]
                   FROM   (SELECT ITEM_ID,
                                  CASE
                                    WHEN FORECAST_MONTH IN ( 1, 2, 3 ) THEN 1
                                    WHEN FORECAST_MONTH IN ( 4, 5, 6 ) THEN 2
                                    WHEN FORECAST_MONTH IN ( 7, 8, 9 ) THEN 3
                                    WHEN FORECAST_MONTH IN ( 10, 11, 12 ) THEN 4
                                  END AS FORECAST_QUART,
                                  FORECAST_YEAR,
                                  Sum(DOLLARS) / NULLIF(Sum(UNITS), 0) AS PRICE
                           FROM   FORECASTING_MASTER FM
                                  INNER JOIN ITEM_MASTER IM
                                          ON IM.ITEM_ID = FM.NDC
                                  INNER JOIN (SELECT TOP 1 FT.FORECAST_NAME,
                                                           FT.[VERSION]
                                              FROM   FILE_MANAGEMENT FT
                                              WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                                                       AND FT.FROM_PERIOD IS NOT NULL )
                                                     AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                                                            OR FT.TO_PERIOD IS NULL )
                                              ORDER  BY FT.FROM_PERIOD DESC) F
                                          ON FM.FORECAST_NAME = F.FORECAST_NAME
                                             AND FM.FORECAST_VER IN ( F.[VERSION], Floor(F.[VERSION]) )
                           WHERE  EXISTS (SELECT 1
                                          FROM   @ITEMID B
                                          WHERE  FM.NDC = B.ITEMID)
                                  AND ( Dateadd(MM, 0, Cast(Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'
                                                            + Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' AS DATETIME)) >= @FROM_DATE
                                        AND ( Dateadd(MM, 0, Cast(Cast(FORECAST_YEAR AS VARCHAR(4)) + '-'
                                                                  + Cast(FORECAST_MONTH AS VARCHAR(2)) + '-01' AS DATETIME)) <= @PROJECTION_DATE ) )
                           GROUP  BY ITEM_ID,
                                     CASE
                                       WHEN FORECAST_MONTH IN ( 1, 2, 3 ) THEN 1
                                       WHEN FORECAST_MONTH IN ( 4, 5, 6 ) THEN 2
                                       WHEN FORECAST_MONTH IN ( 7, 8, 9 ) THEN 3
                                       WHEN FORECAST_MONTH IN ( 10, 11, 12 ) THEN 4
                                     END,
                                     FORECAST_YEAR)A),
               WAC
               AS (SELECT ITEM_ID,
                          Max(CASE
                                                     WHEN [SOURCE] = 1 THEN QUARTER_WAC
                                                   END) AS FORECAST_QUARTER_WAC,
                          Max(CASE
                                                   WHEN [SOURCE] = 0 THEN QUARTER_WAC
                                                 END) AS ACTUAL_QUARTER_WAC,
                          PERIOD
                   FROM   QUARTER_WAC
                   GROUP  BY ITEM_ID,
                             PERIOD)
          INSERT INTO #WAC
                      (ITEMID,
                       ITEM_MASTER_SID,
                       QUARTER_WAC,
                       PERIOD,
                       PERIOD_DATE)
          SELECT DISTINCT C.ITEM_ID AS ITEMID,
                          IM.ITEM_MASTER_SID,
                          COALESCE(FORECAST_QUARTER_WAC, ACTUAL_QUARTER_WAC, 0) AS QUARTER_WAC,
                          C.PERIOD,
                          PERIOD_DATE
          FROM   WAC C
                 INNER JOIN ITEM_MASTER IM
                         ON C.ITEM_ID = IM.ITEM_ID
                 INNER JOIN @PERIOD_QUARTER Q
                         ON 'Q' + CONVERT(VARCHAR(8), Q.PERIOD_QUARTER)
                            + ' ' + CONVERT(VARCHAR(8), Q.PERIOD_YEAR) = C.PERIOD
          ORDER  BY PERIOD_DATE ASC;

          WITH WAC_INCREASE_UPDATE_CTE
               AS (SELECT Row_number()
                            OVER(
                              PARTITION BY ITEM_MASTER_SID
                              ORDER BY PERIOD_DATE ASC) RN,
                          ITEMID,
                          ITEM_MASTER_SID,
                          PERIOD,
                          PERIOD_DATE,
                          QUARTER_WAC,
                          WAC_INCREASE
                   FROM   #WAC)
          UPDATE C
          SET    C.WAC_INCREASE = Isnull(D.WAC_INCREASE, 0)
          FROM   WAC_INCREASE_UPDATE_CTE C
                 INNER JOIN (SELECT A.ITEMID,
                                    A.ITEM_MASTER_SID,
                                    A.PERIOD,
                                    ( ( A.QUARTER_WAC - B.QUARTER_WAC ) / NULLIF(B.QUARTER_WAC, 0) ) AS WAC_INCREASE
                             FROM   WAC_INCREASE_UPDATE_CTE A
                                    LEFT OUTER JOIN WAC_INCREASE_UPDATE_CTE B
                                                 ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                                    AND A.RN = B.RN + 1) D
                         ON C.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                            AND C.PERIOD = D.PERIOD

          CREATE TABLE #TEMP_WAC
            (
               ITEMID                 VARCHAR(50),
               WAC_PRICE              NUMERIC(22, 6),
               WAC_INCREASE           NUMERIC(22, 6),
               PERIOD_SID             INT,
               PROJECTION_DETAILS_SID INT
            )

          INSERT INTO #TEMP_WAC
                      (ITEMID,
                       WAC_PRICE,
                       WAC_INCREASE,
                       PROJECTION_DETAILS_SID,
                       PERIOD_SID)
          SELECT C.ITEMID,
                 F.ITEM_PRICE AS WAC_PRICE,
                 WAC_INCREASE,
                 PROJECTION_DETAILS_SID,
                 P.PERIOD_SID
          FROM   #WAC C
                 INNER JOIN PERIOD P
                         ON Datepart(QUARTER, C.PERIOD_DATE) = P.QUARTER
                            AND Year(C.PERIOD_DATE) = P.YEAR
                 INNER JOIN #TEMP_CCP CCP
                         ON C.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID
                 INNER JOIN Udf_month_wac(@ITEMID, @FROM_DATE, @PROJECTION_DATE) F
                         ON F.PERIOD_SID = P.PERIOD_SID
                            AND F.ITEM_ID = C.ITEMID
          WHERE  P.PERIOD_DATE BETWEEN @FROM_DATE AND @PROJECTION_DATE
          ORDER  BY C.ITEMID,
                    P.PERIOD_SID,
                    PROJECTION_DETAILS_SID

          ---------------- WAC PRICE CALCULATION ENDS ------------------
          -----------------ACTUAL_BASELINE CALCULATION START (BASED ON SINGLE OR AVERAGE METHODOLOGY)-------------------- 
          CREATE TABLE #ACTUAL_BASELINE
            (
               ACTUAL_BASELINE           NUMERIC(38, 15),
               PROJECTION_DETAILS_SID    INT,
               CALCULATION_BASED         VARCHAR(6),
               NUMBER_OF_SELECTED_PERIOD INT
            )

          DECLARE @METHDOLGY   VARCHAR(50) = (SELECT FORMULA
                     FROM   FORECASTING_FORMULA
                     WHERE  FORMULA_NAME = 'SINGLE_AVERAGE_METHODOLOGY'),--SELECTED_PERIOD_ACTUALS/NUMBER_OF_SELECTED_PERIOD
                  @METHODOLOGY NVARCHAR(MAX)

          IF EXISTS(SELECT 1
                    FROM   #TEMP_CCP CCP
                    WHERE  METHODOLOGY IN( 'SINGLE PERIOD', 'AVERAGE' ))
            BEGIN
                SET @METHODOLOGY='INSERT INTO #ACTUAL_BASELINE
                                              (ACTUAL_BASELINE,
                                               PROJECTION_DETAILS_SID,
                                               CALCULATION_BASED,
                                                               NUMBER_OF_SELECTED_PERIOD)
                                 
                  SELECT ' + @METHDOLGY
                                 + ' AS ACTUAL_BASELINE,
                         PROJECTION_DETAILS_SID,
                         CALCULATION_BASED,
                                         NUMBER_OF_SELECTED_PERIOD
                  FROM   (SELECT SUM(CASE
                                       WHEN CALCULATION_BASED = ''SALES'' THEN ACTUAL_SALES
                                       ELSE ACTUAL_UNITS
                                     END) SELECTED_PERIOD_ACTUALS,
                                 PROJECTION_DETAILS_SID,
                                 CALCULATION_BASED,
                                 NUMBER_OF_SELECTED_PERIOD
                          FROM   (SELECT T_CCP.PROJECTION_DETAILS_SID,
                                         CALCULATION_BASED,
                                         SUM(ACTUAL_SALES)                                                         ACTUAL_SALES,
                                         SUM(ACTUAL_UNITS)                                                         ACTUAL_UNITS,
                                         LEN(CALCULATION_PERIODS) - LEN(REPLACE(CALCULATION_PERIODS, '''
                                 + ',' + ''', ''' + ''
                                 + ''')) + 1 AS NUMBER_OF_SELECTED_PERIOD
                                  FROM   #TEMP_CCP T_CCP
                                         CROSS APPLY (SELECT FN.TOKEN,
                                                             PERIOD_SID,
                                                             [QUARTER],
                                                             [YEAR]
                                                      FROM   UDF_SPLITSTRING(T_CCP.CALCULATION_PERIODS, '''
                                 + ','
                                 + ''') FN
                                                             JOIN PERIOD P
                                                               ON ''Q'' + CONVERT(VARCHAR(1), P.QUARTER) + '''
                                 + ' '
                                 + '''
                                                                  + CONVERT(VARCHAR(4), P.YEAR) = FN.TOKEN) CS
                                         INNER JOIN ST_M_ACTUAL_SALES NAS
                                                 ON CS.PERIOD_SID = NAS.PERIOD_SID
                                                    AND T_CCP.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                    AND NAS.[USER_ID] = '
                                 + CONVERT(VARCHAR(50), @USER_ID)
                                 + '
                                                     AND NAS.[SESSION_ID] = '
                                 + CONVERT(VARCHAR(50), @SESSION_ID)
                                 + '
                                  WHERE  T_CCP.PROJECTION_MASTER_SID = '
                                 + CONVERT(VARCHAR(50), @PROJECTION)
                                 + '
                                         AND T_CCP.METHODOLOGY IN( ''SINGLE PERIOD'', ''AVERAGE'' )
                                  GROUP  BY T_CCP.[PROJECTION_DETAILS_SID],
                                            [QUARTER],
                                            [YEAR],
                                            CALCULATION_BASED,
                                            CALCULATION_PERIODS) A
                          GROUP  BY PROJECTION_DETAILS_SID,
                                    CALCULATION_BASED,
                                    NUMBER_OF_SELECTED_PERIOD)L
                  '

                EXEC Sp_executesql
                  @METHODOLOGY

                -------------------- VARIABLE USED FOR CALCULATING PROJECTED SALES AND UNITS----------------------------
                DECLARE @SALES_BASED_SALES VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = 'SALES_BASED_SALES'),--PROJECTED_SALES * (1 + WAC_INCREASE)
                        @SALES_BASED_UNITS VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = 'SALES_BASED_UNITS'),--PROJECTED_SALES/WAC_PRICE
                        @UNITS_BASED_SALES VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = 'UNITS_BASED_SALES')--PROJECTED_UNITS*WAC_PRICE
                ---------------------------------------------ALL CALCULATION DONE HERE FOR 1ST 3 MONTHS OF 1ST YEAR WITH GTS % OF BUISNESS ----------------
                SELECT IM.ITEM_ID,
                       T.ITEM_MASTER_SID,
                       T.PROJECTION_DETAILS_SID
                INTO   #GTS_TEMP
                FROM   ITEM_MASTER IM
                       JOIN #TEMP_CCP T
                         ON IM.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                WHERE  T.PROJECTION_MASTER_SID = @PROJECTION

                DECLARE @ACTUAL_GTS UDT_ACTUAL_GTS_ITEM

                INSERT INTO @ACTUAL_GTS
                            (ITEMID,
                             NDC8,
                             COMPANY_COST_CENTER)
                SELECT DISTINCT G.ITEM_ID,
                                IM.NDC8,
                                GL.COMPANY_COST_CENTER
                FROM   ITEM_MASTER IM
                       JOIN #TEMP_CCP T
                         ON IM.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                       JOIN GL_COST_CENTER_MASTER GL
                         ON GL.NDC8 = IM.NDC8
                       JOIN COMPANY_MASTER CM
                         ON GL.COMPANY_CODE = CM.COMPANY_ID
                            AND EXISTS(SELECT 1
                                       FROM   PROJECTION_MASTER PM
                                       WHERE  PM.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID
									   AND    PM.PROJECTION_MASTER_SID=@PROJECTION)
                       RIGHT JOIN #GTS_TEMP G
                               ON IM.ITEM_ID = G.ITEM_ID;

                WITH GTS
                     AS (SELECT T.PROJECTION_DETAILS_SID,
                                T.ITEM_MASTER_SID,
                                'Q' + CONVERT(VARCHAR(8), T.QUARTER) + ' '
                                + CONVERT(VARCHAR(8), T.YEAR) AS START_QUARTER,
                                T.PERIOD_SID,
                                Isnull(SALES, 0) AS MONTHLY_SALES,
                                Isnull(UNITS, 0) AS MONTHLY_UNITS,
                                Sum(Isnull(SALES, 0))
                                                      OVER(
                                                        PARTITION BY FM.ITEM_ID, FORECAST_YEAR, FORECAST_QUARTER, T.PROJECTION_DETAILS_SID, SOURCE) AS QUARTERLY_GTS_SALES,
                                Sum(Isnull(UNITS, 0))
                                                       OVER(
                                                         PARTITION BY FM.ITEM_ID, FORECAST_YEAR, FORECAST_QUARTER, T.PROJECTION_DETAILS_SID, SOURCE) AS QUARTERLY_GTS_units,
                                T.QUARTER                     AS FORECAST_QUARTER,
                                T.MONTH,
                                T.YEAR,
                                T.ITEM_ID,
                                [SOURCE]
                         FROM   [dbo].[Udf_gts_details] (@ACTUAL_GTS, @FROM_DATE, @PROJECTION_DATE) FM
                                RIGHT JOIN (SELECT PERIOD_SID,
                                                   G.ITEM_ID,
                                                   G.ITEM_MASTER_SID,
                                                   G.PROJECTION_DETAILS_SID,
                                                   MONTH,
                                                   YEAR,
                                                   QUARTER,
                                                   PERIOD_DATE
                                            FROM   PERIOD
                                                   CROSS JOIN #GTS_TEMP G)T
                                        ON T.ITEM_ID = FM.ITEM_ID
                                           AND FM.START_PERIOD = T.PERIOD_DATE
                         WHERE  T.PERIOD_DATE >= @FROM_DATE
                                AND T.PERIOD_DATE <= @PROJECTION_DATE),
                     GTS_PIVOT
                     AS (SELECT ITEM_ID,
                                PROJECTION_DETAILS_SID,
                                ITEM_MASTER_SID,
                                START_QUARTER,
                                PERIOD_SID,
                                MONTH,
                                [FORECAST_QUARTER],
                                Max(CASE
                                                           WHEN [SOURCE] = 1 THEN MONTHLY_SALES
                                                         END) AS MONTHLY_FORECAST_GTS,
                                Max(CASE
                                                         WHEN [SOURCE] = 0 THEN MONTHLY_SALES
                                                       END) AS MONTHLY_ACTUAL_GTS,
                                Max(CASE
                                                   WHEN [SOURCE] = 1 THEN QUARTERLY_GTS_SALES
                                                 END) AS FORECAST_GTS,
                                Max(CASE
                                                 WHEN [SOURCE] = 0 THEN QUARTERLY_GTS_SALES
                                               END) AS ACTUAL_GTS,
                                YEAR
                         FROM   GTS
                         GROUP  BY ITEM_ID,
                                   PROJECTION_DETAILS_SID,
                                   ITEM_MASTER_SID,
                                   START_QUARTER,
                                   PERIOD_SID,
                                   MONTH,
                                   [FORECAST_QUARTER],
                                   YEAR)
                SELECT ITEM_ID                                               AS ITEMID,
                       ITEM_MASTER_SID,
                       PROJECTION_DETAILS_SID,
                       YEAR                                                  AS FORECAST_YEAR,
                       FORECAST_QUARTER,
                       MONTH                                                 AS FORECAST_MONTH,
                       START_QUARTER,
                       PERIOD_SID,
                       COALESCE(FORECAST_GTS, ACTUAL_GTS, 0)                 AS QUARTERLY_GTS_SALES,
                       COALESCE(MONTHLY_FORECAST_GTS, MONTHLY_ACTUAL_GTS, 0) AS MONTHLY_SALES
                INTO   #UDF_GTS
                FROM   GTS_PIVOT

                DECLARE @GTS_PERCENT VARCHAR(100)=(SELECT FORMULA
                  FROM   FORECASTING_FORMULA
                  WHERE  FORMULA_NAME = 'GTS % OF BUSINESS')
                DECLARE @RES_VALUE          VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = 'SALES_PROJECTION'),
                        @MAIN_CAL           NVARCHAR(MAX),
                        @UPDATE_SALES_UNITS NVARCHAR(MAX)

                SET @GTS_PERCENT=(SELECT Replace(@GTS_PERCENT, 'MONTHLY_GTS', 'MONTHLY_SALES'))
                SET @GTS_PERCENT=(SELECT Replace(@GTS_PERCENT, 'QUARTERLY_GTS', 'NULLIF(QUARTERLY_GTS_SALES,0)'))
                SET @SALES_BASED_SALES=(SELECT Replace(@SALES_BASED_SALES, 'PROJECTED_SALES', 'RES_VALUE'))
                SET @SALES_BASED_UNITS=(SELECT Replace(@SALES_BASED_UNITS, 'PROJECTED_SALES', 'RES_VALUE * ( 1 + ROUND(WAC_INCREASE,4))'))
                SET @SALES_BASED_UNITS=(SELECT Replace(@SALES_BASED_UNITS, 'WAC_PRICE', 'NULLIF(WAC_PRICE,0)'))
                SET @UNITS_BASED_SALES=(SELECT Replace(@UNITS_BASED_SALES, 'PROJECTED_UNITS', 'RES_VALUE'))

                CREATE TABLE #GTS
                  (
                     ITEM_ID                VARCHAR(50),
                     ITEM_MASTER_SID        INT,
                     PROJECTION_DETAILS_SID INT,
                     PERIOD_SID             INT,
                     PERIOD_DATE            DATETIME,
                     FORECAST_YEAR          INT,
                     FORECAST_MONTH         INT,
                     FORECAST_QUARTER       INT,
                     DOLLARS                NUMERIC(38, 15),
                     GTS_PERCENT            NUMERIC(38, 15),
                     ACTUAL_BASELINE        NUMERIC(38, 15),
                     BASELINE               NUMERIC(38, 15),
                     RES_VALUE              NUMERIC(38, 15),
                     ACCOUNT_GROWTH         NUMERIC(22, 6),
                     PRODUCT_GROWTH         NUMERIC(22, 6),
                     CALCULATION_BASED      VARCHAR(6),
                     PROJECTED_SALES        NUMERIC(38, 15),
                     PROJECTED_UNITS        NUMERIC(38, 15),
                     RN                     INT,
                     WAC_INCREASE           NUMERIC(22, 6),
                     WAC_PRICE              NUMERIC(22, 6),
                     START_DATE             DATETIME
                  )

                SET @MAIN_CAL='INSERT INTO #GTS
                            (ITEM_ID,
                             ITEM_MASTER_SID,
                             PROJECTION_DETAILS_SID,
                             PERIOD_SID,
							 PERIOD_DATE,
                             FORECAST_YEAR,
                             FORECAST_MONTH,
                             FORECAST_QUARTER,
                             DOLLARS,
                             GTS_PERCENT,
                             ACTUAL_BASELINE,
                             BASELINE,
                             RES_VALUE,
                             ACCOUNT_GROWTH,
                             PRODUCT_GROWTH,
                             CALCULATION_BASED,
                             WAC_INCREASE,
                             WAC_PRICE,
							 START_DATE,
                             RN)
                SELECT ITEMID,
                       ITEM_MASTER_SID,
                       PROJECTION_DETAILS_SID,
                       PERIOD_SID,
					   PERIOD_DATE,
                       FORECAST_YEAR,
                       FORECAST_MONTH,
                       FORECAST_QUARTER,
                       DOLLARS,
                       GTS_PERCENT,
                       ACTUAL_BASELINE,
                       BASELINE,
					   (' + @RES_VALUE + ') AS RES_VALUE,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                       CALCULATION_BASED,
                       WAC_INCREASE,
                       WAC_PRICE,
					   START_DATE,
                       RN

				FROM(SELECT ITEMID,
                       ITEM_MASTER_SID,
                       PROJECTION_DETAILS_SID,
                       PERIOD_SID,
					   PERIOD_DATE,
                       FORECAST_YEAR,
                       FORECAST_MONTH,
                       FORECAST_QUARTER,
                       DOLLARS,
                       GTS_PERCENT,
                       ACTUAL_BASELINE,
                       ( ISNULL(GTS_PERCENT, 0) * ISNULL(ACTUAL_BASELINE, 0) ) AS BASELINE,
                       ACCOUNT_GROWTH,
                       PRODUCT_GROWTH,
                       CALCULATION_BASED,
                       WAC_INCREASE,
                       WAC_PRICE,
					   START_DATE,
                       RN
                
									  FROM  (SELECT ITEMID,
                              ITEM_MASTER_SID,
                              PROJECTION_DETAILS_SID,
                              PERIOD_SID,
							  PERIOD_DATE,
                              FORECAST_YEAR,
                              FORECAST_MONTH,
                              FORECAST_QUARTER,
                              DOLLARS,
                              GTS_PERCENT/100 AS GTS_PERCENT,
                              ACTUAL_BASELINE,
                              ACCOUNT_GROWTH,
                              PRODUCT_GROWTH,
                              CALCULATION_BASED,
                              WAC_INCREASE,
                              WAC_PRICE,
							  START_DATE,
                              DENSE_RANK()
                                OVER(
                                  ORDER BY FORECAST_YEAR, FORECAST_QUARTER)    AS RN
                       FROM   (SELECT ITEMID,
                                      ITEM_MASTER_SID,
                                      PROJECTION_DETAILS_SID,
                                      PERIOD_SID,
									  PERIOD_DATE,
                                      FORECAST_YEAR,
                                      FORECAST_MONTH,
                                      FORECAST_QUARTER,
                                      MONTHLY_SALES AS DOLLARS,
									  '
                              + @GTS_PERCENT
                              + ' AS GTS_PERCENT,
                                      ACTUAL_BASELINE,
                                      ACCOUNT_GROWTH,
                                      PRODUCT_GROWTH,
                                      CALCULATION_BASED,
                                      WAC_INCREASE,
                                      WAC_PRICE ,
									  START_DATE
									  FROM   (SELECT DISTINCT FM.ITEMID,
                                                       ITEM_MASTER_SID,
                                                       FM.PROJECTION_DETAILS_SID,
                                                       P.PERIOD_SID,
													   P.PERIOD_DATE,
                                                       FORECAST_YEAR,
                                                       FORECAST_MONTH,
                                                       FORECAST_QUARTER,
                                                       MONTHLY_SALES,
													   QUARTERLY_GTS_SALES,
                                                       ACTUAL_BASELINE,
                                                       ISNULL(TC.ACCOUNT_GROWTH,0) ACCOUNT_GROWTH,
                                                       ISNULL(TC.PRODUCT_GROWTH,0) PRODUCT_GROWTH,
                                                       B.CALCULATION_BASED,
                                                       WAC_INCREASE,
                                                       WAC_PRICE,
													   N.START_DATE
													  
											  FROM #UDF_GTS FM
											  JOIN #NEXTQUARTER N
											  ON FM.PROJECTION_DETAILS_SID=N.PROJECTION_DETAILS_SID
												JOIN PERIOD P
												ON P.PERIOD_DATE >=N.START_DATE 
													AND ( CAST(FM.FORECAST_YEAR AS VARCHAR(4)) + '''
                              + '-' + '''
															+ CAST(FM.FORECAST_MONTH AS VARCHAR(2))
															+ ''' + '-' + ''' + CAST('
                              + '01' + ' AS VARCHAR(2)) ) = P.PERIOD_DATE
													AND FM.FORECAST_YEAR >= YEAR(N.START_DATE)
                              
												JOIN #ACTUAL_BASELINE B
												ON FM.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
												JOIN #TEMP_WAC WC
												ON FM.PROJECTION_DETAILS_SID = WC.PROJECTION_DETAILS_SID
													AND P.PERIOD_SID = WC.PERIOD_SID
													AND N.PROJECTION_DETAILS_SID = WC.PROJECTION_DETAILS_SID
												JOIN ST_M_SALES_PROJECTION TC
												ON N.PROJECTION_DETAILS_SID = TC.PROJECTION_DETAILS_SID
												AND FM.PROJECTION_DETAILS_SID = TC.PROJECTION_DETAILS_SID
												AND TC.PERIOD_SID=P.PERIOD_SID
												
												
													AND TC.[USER_ID] = '
                              + CONVERT(VARCHAR(50), @USER_ID)
                              + 'AND TC.[SESSION_ID] = '
                              + CONVERT(VARCHAR(50), @SESSION_ID)
                              + ') A)B)C)D

             ------------------------ PROJECTED_SALES AND UNITS FOR 1ST THREE MONTH OF 1ST YEAR


              UPDATE GTS
              SET    PROJECTED_SALES = CASE
                                         WHEN GTS.CALCULATION_BASED = ''SALES'' THEN '
                              + @SALES_BASED_SALES
                              + '
                                           ELSE '
                              + @UNITS_BASED_SALES
                              + '
                                         END,
                       PROJECTED_UNITS = CASE
                                           WHEN GTS.CALCULATION_BASED = ''SALES'' THEN '
                              + @SALES_BASED_UNITS
                              + '
                                           ELSE RES_VALUE
                                         END
                FROM   #GTS GTS
                       JOIN #ACTUAL_BASELINE BL
                         ON GTS.PROJECTION_DETAILS_SID = BL.PROJECTION_DETAILS_SID
               WHERE  GTS.FORECAST_QUARTER = DATEPART(QQ,(CONVERT(VARCHAR(38), GTS.START_DATE)))

                         AND GTS.FORECAST_YEAR =YEAR( CONVERT(VARCHAR(38), GTS.START_DATE) )'

                EXECUTE Sp_executesql
                  @MAIN_CAL

                -------------------------- PROJECTED_SALES AND UNITS FOR REMAINING MONTHS
                

                CREATE TABLE #SUM_QTR
                  (
                     PROJECTION_DETAILS_SID INT,
                     PROJECTED_SALES        NUMERIC(38, 15),
                     PROJECTED_UNITS        NUMERIC(38, 15),
                     PERIOD_SID             INT
                  )

                DECLARE @J INT=1
                DECLARE @START_PROJ DATETIME
                DECLARE @START_DATE1 INT
                DECLARE @T INT
                DECLARE @START_DATETAB TABLE
                  (
                     ID                     INT IDENTITY(1, 1) NOT NULL,
                     PROJECTION_DETAILS_SID INT,
                     START_DATE             DATETIME
                  )

                INSERT INTO @START_DATETAB
                            (PROJECTION_DETAILS_SID,
                             START_DATE)
                SELECT PROJECTION_DETAILS_SID,
                       START_DATE
                FROM   #NEXTQUARTER

                SELECT @T = 1

                SELECT @START_DATE1 = Count(PROJECTION_DETAILS_SID)
                FROM   @START_DATETAB

                WHILE ( @T <= @START_DATE1 )
                  BEGIN
                      SELECT @START_PROJ = START_DATE
                      FROM   @START_DATETAB
                      WHERE  ID = @T

                      WHILE( Dateadd(QQ, @J, @START_PROJ) <= @PROJECTION_DATE )
                        BEGIN
                            INSERT INTO #SUM_QTR(PROJECTION_DETAILS_SID,PROJECTED_SALES,PROJECTED_UNITS,PERIOD_SID)
                            SELECT PROJECTION_DETAILS_SID,
                                   Sum(PROJECTED_SALES)
                                     OVER(
                                       PARTITION BY FORECAST_YEAR, FORECAST_QUARTER, PROJECTION_DETAILS_SID) PROJECTED_SALES,
                                   Sum(PROJECTED_UNITS)
                                     OVER(
                                       PARTITION BY FORECAST_YEAR, FORECAST_QUARTER, PROJECTION_DETAILS_SID) PROJECTED_UNITS,
                                   PERIOD_SID
                            FROM   #GTS
                            WHERE  FORECAST_QUARTER = Datepart(QUARTER, ( Dateadd(QQ, @J - 1, @START_PROJ) ))
                                   AND FORECAST_YEAR = Year(Dateadd(QQ, @J - 1, @START_PROJ))
                            GROUP  BY PROJECTED_SALES,
                                      PROJECTED_UNITS,
                                      FORECAST_YEAR,
                                      FORECAST_QUARTER,
                                      PROJECTION_DETAILS_SID,
                                      PERIOD_SID

                            SET @UPDATE_SALES_UNITS='UPDATE GTS  SET BASELINE = CASE
                                              WHEN GTS.CALCULATION_BASED = ''SALES'' THEN GTS_PERCENT * CONVERT(VARCHAR(100), Q.PROJECTED_SALES)
                                               
                                              ELSE GTS_PERCENT * CONVERT(VARCHAR(100), Q.PROJECTED_UNITS)
                                              
                                            END
                      FROM   #GTS GTS 
                                     JOIN   #SUM_QTR Q
                                    ON GTS.PROJECTION_DETAILS_SID=Q.PROJECTION_DETAILS_SID

                                    
                      WHERE  GTS.FORECAST_QUARTER = DATEPART(QUARTER, DATEADD(QQ, '
                                                    + CONVERT(VARCHAR(10), +@J) + ', '''
                                                    + CONVERT(VARCHAR(38), @START_PROJ)
                                                    + '''))
                             AND GTS.FORECAST_YEAR = YEAR(DATEADD(QQ, '
                                                    + CONVERT(VARCHAR(10), +@J) + ', '''
                                                    + CONVERT(VARCHAR(38), @START_PROJ)
                                                    + '''))

                UPDATE GTS
                      SET    RES_VALUE = ( '
                                                    + @RES_VALUE
                                                    + ' )
                      FROM   #GTS GTS
                                    
                      WHERE  GTS.FORECAST_QUARTER = DATEPART(QUARTER, DATEADD(QQ, '
                                                    + CONVERT(VARCHAR(10), +@J) + ', '''
                                                    + CONVERT(VARCHAR(38), @START_PROJ)
                                                    + '''))
                             AND GTS.FORECAST_YEAR = YEAR(DATEADD(QQ, '
                                                    + CONVERT(VARCHAR(10), +@J) + ', '''
                                                    + CONVERT(VARCHAR(38), @START_PROJ)
                                                    + '''))
											  
             

                UPDATE GTS
                      SET    PROJECTED_SALES = CASE
                                                 WHEN GTS.CALCULATION_BASED = ''SALES'' THEN '
                                                    + @SALES_BASED_SALES
                                                    + '
                                                 ELSE '
                                                    + @UNITS_BASED_SALES
                                                    + '
                                               END,
                             PROJECTED_UNITS = CASE
                                                 WHEN GTS.CALCULATION_BASED = ''SALES'' THEN '
                                                    + @SALES_BASED_UNITS
                                                    + '
                                                 ELSE RES_VALUE
                                               END
                     FROM   #GTS GTS
                      WHERE  GTS.FORECAST_QUARTER = DATEPART(QUARTER, DATEADD(QQ, '
                                                    + CONVERT(VARCHAR(10), +@J) + ', '''
                                                    + CONVERT(VARCHAR(38), @START_PROJ)
                                                    + '''))
                             AND GTS.FORECAST_YEAR = YEAR(DATEADD(QQ, '
                                                    + CONVERT(VARCHAR(10), +@J) + ', '''
                                                    + CONVERT(VARCHAR(38), @START_PROJ) + '''))'

                            EXECUTE Sp_executesql
                              @UPDATE_SALES_UNITS

                            TRUNCATE TABLE #SUM_QTR

                            SET @J=@J + 1
                        END

                      SELECT @T = @T + 1
                  END

                UPDATE A
                SET    A.PROJECTION_SALES = Isnull(CONVERT(NUMERIC(22, 6), B.PROJECTED_SALES), 0),
                       A.PROJECTION_UNITS = Isnull(CONVERT(NUMERIC(22, 6), B.PROJECTED_UNITS), 0)
                FROM   #GTS B
                       JOIN ST_M_SALES_PROJECTION A
                         ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                            AND A.PERIOD_SID = B.PERIOD_SID
                            AND A.[USER_ID] = @USER_ID
                            AND A.[SESSION_ID] = @SESSION_ID

                UPDATE A
                SET    A.PROJECTION_SALES = 0,
                       A.PROJECTION_UNITS = 0
                FROM   ST_M_SALES_PROJECTION A
                       JOIN ST_M_SALES_PROJECTION_MASTER S
                         ON A.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                            AND S.[USER_ID] = @USER_ID
                            AND S.[SESSION_ID] = @SESSION_ID
                            AND S.CHECK_RECORD = 1
                WHERE  A.PROJECTION_SALES IS NULL
                       AND A.PROJECTION_UNITS IS NULL
            END

          ------------------------ BASED ON METHODOLOGY = '% OF BUSINESS'
          IF EXISTS(SELECT 1
                    FROM   ST_M_SALES_PROJECTION_MASTER NSPM
                           JOIN PROJECTION_DETAILS PD
                             ON PD.PROJECTION_DETAILS_SID = NSPM.PROJECTION_DETAILS_SID
                           JOIN PROJECTION_MASTER PM
                             ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                AND PM.PROJECTION_MASTER_SID = @PROJECTION
                                AND METHODOLOGY = '% OF BUSINESS')
            BEGIN
                DECLARE @PERCENT_OF_BUSINESS_UNITS VARCHAR(100)
                DECLARE @PERCENT_OF_BUSINESS_SALES VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = '% OF BUSINESS'),
                        @CTE                       NVARCHAR(MAX)
                DECLARE @SALES_BASED_SALES_PERCENT VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = 'SALES_BASED_SALES'),--PROJECTED_SALES * (1 + WAC_INCREASE)
                        @SALES_BASED_UNITS_PERCENT VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = 'SALES_BASED_UNITS'),--PROJECTED_SALES/WAC_PRICE
                        @UNITS_BASED_SALES_PERCENT VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = 'UNITS_BASED_SALES')
                SET @PERCENT_OF_BUSINESS_SALES=(SELECT Replace(@PERCENT_OF_BUSINESS_SALES, 'LAST_ACTUALS', 'ACTUAL_SALES'))
                SET @PERCENT_OF_BUSINESS_SALES=(SELECT Replace(@PERCENT_OF_BUSINESS_SALES, 'QUARTERLY_GTS', 'NULLIF(QUAT_SALES,0)'))
                SET @PERCENT_OF_BUSINESS_UNITS=(SELECT Replace(@PERCENT_OF_BUSINESS_SALES, 'ACTUAL_SALES', 'ACTUAL_UNITS'))
                SET @PERCENT_OF_BUSINESS_UNITS=(SELECT Replace(@PERCENT_OF_BUSINESS_UNITS, 'NULLIF(QUAT_SALES,0)', 'NULLIF(QUAT_UNITS,0)'))
                SET @SALES_BASED_SALES_PERCENT=(SELECT Replace(@SALES_BASED_SALES_PERCENT, 'PROJECTED_SALES', 'BASELINE_PROJECTED_SALES'))
                SET @SALES_BASED_UNITS_PERCENT=(SELECT Replace(@SALES_BASED_UNITS_PERCENT, 'PROJECTED_SALES', 'BASELINE_PROJECTED_SALES * ( 1 + WAC_INCREASE )'))
                SET @SALES_BASED_UNITS_PERCENT=(SELECT Replace(@SALES_BASED_UNITS_PERCENT, 'WAC_PRICE', 'NULLIF(WAC_PRICE,0)'))
                SET @UNITS_BASED_SALES_PERCENT=(SELECT Replace(@UNITS_BASED_SALES_PERCENT, 'PROJECTED_UNITS', 'BASELINE_PROJECTED_UNITS'))

                CREATE TABLE #LAST_ACTUALS--------------------------------IN THIS TABLE WE WILL GET LAST AVAILABLE GTS ACTUALS
                  (
                     ITEMID                 VARCHAR(50),
                     NDC8                   VARCHAR(50),
                     QUARTER_VAL            VARCHAR(10),
                     COMPANY_COST_CENTER    VARCHAR(50),
                     PROJECTION_DETAILS_SID INT,
                     ACTUAL_SALES           NUMERIC(38, 15),
                     ACTUAL_UNITS           NUMERIC(38, 15),
                     CALCULATION_BASED      VARCHAR(50),
                     PROJECTION_MASTER_SID  INT,
                     PERIOD_SID             INT
                  )

                INSERT INTO #LAST_ACTUALS(ITEMID,NDC8,QUARTER_VAL,COMPANY_COST_CENTER,PROJECTION_DETAILS_SID,ACTUAL_SALES,ACTUAL_UNITS,
				CALCULATION_BASED,PROJECTION_MASTER_SID,PERIOD_SID)
                SELECT ITEM_ID,
                       NDC8,
                       QUARTER_VAL,
                       COMPANY_COST_CENTER,
                       PROJECTION_DETAILS_SID,
                       ACTUAL_SALES,
                       ACTUAL_UNITS,
                       CALCULATION_BASED,
                       PROJECTION_MASTER_SID,
                       PERIOD_SID
                FROM   (SELECT Row_number()
                                 OVER (
                                   PARTITION BY AC.PROJECTION_DETAILS_SID
                                   ORDER BY AC.PERIOD_SID DESC) RN,
                               T.PROJECTION_DETAILS_SID,
                               IM.ITEM_ID,
                               GL.NDC8,
                               'Q' + CONVERT(VARCHAR(8), P.QUARTER) + ' '
                               + CONVERT(VARCHAR(8), P.YEAR)    QUARTER_VAL,
                               GL.COMPANY_COST_CENTER,
                               AC.ACTUAL_SALES,
                               AC.ACTUAL_UNITS,
                               T.CALCULATION_BASED,
                               T.PROJECTION_MASTER_SID,
                               AC.PERIOD_SID
                        FROM   #TEMP_CCP T
                               JOIN ST_M_ACTUAL_SALES AC
                                 ON T.PROJECTION_DETAILS_SID = AC.PROJECTION_DETAILS_SID
                                    AND T.PROJECTION_MASTER_SID = @PROJECTION
                                    AND T.METHODOLOGY = '% OF BUSINESS'
                                    AND AC.[USER_ID] = @USER_ID
                                    AND AC.SESSION_ID = @SESSION_ID
                               JOIN ITEM_MASTER IM
                                 ON T.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                               JOIN PERIOD P
                                 ON AC.PERIOD_SID = P.PERIOD_SID
                               JOIN GL_COST_CENTER_MASTER GL
                                 ON GL.NDC8 = IM.NDC8
                               JOIN COMPANY_MASTER CM
                                 ON GL.COMPANY_CODE = CM.COMPANY_ID
                                    AND EXISTS(SELECT 1
                                               FROM   PROJECTION_MASTER PM
                                               WHERE  PM.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID
											   AND    PM.PROJECTION_MASTER_SID=@PROJECTION)
                        WHERE  ACTUAL_SALES <> 0
                                OR ACTUAL_UNITS <> 0) Al
                WHERE  RN = 1

                SELECT IM.ITEM_ID,
                       T.ITEM_MASTER_SID,
                       T.PROJECTION_DETAILS_SID
                INTO   #GTS_TEMPTABE
                FROM   ITEM_MASTER IM
                       JOIN #TEMP_CCP T
                         ON IM.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                WHERE  T.PROJECTION_MASTER_SID = @PROJECTION

                DECLARE @GTS_DETAILS UDT_ACTUAL_GTS_ITEM -----UDT TABLE CONTAIN ONLY LAST AVAILABLE ACTUALS
                INSERT INTO @GTS_DETAILS
                            (ITEMID,
                             NDC8,
                             QUARTER_VAL,
                             COMPANY_COST_CENTER)
                SELECT DISTINCT ITEMID,
                                NDC8,
                                QUARTER_VAL,
                                COMPANY_COST_CENTER
                FROM   #LAST_ACTUALS

                CREATE TABLE #TEMP_GTS ----------- IN THIS TABLE HAVING BOTH LAST ACTUALS AND FORECAST GTS OR PROJECTED GTS DATA
                  (
                     ITEMID                 VARCHAR(50),
                     --START_PERIOD           DATETIME,
                     START_QUARTER          VARCHAR(10),
                     SALES                  NUMERIC(22, 6),
                     UNITS                  NUMERIC(22, 6),
                     QUAT_SALES             NUMERIC(22, 6),
                     QUAT_UNITS             NUMERIC(22, 6),
                     PERIOD_SID             INT,
                     PROJECTION_DETAILS_SID INT
                  );

                WITH GTS_VALUES
                     AS (SELECT T.PROJECTION_DETAILS_SID,
                                T.ITEM_MASTER_SID,
                                'Q' + CONVERT(VARCHAR(8), T.QUARTER) + ' '
                                + CONVERT(VARCHAR(8), T.YEAR) AS START_QUARTER,
                                T.PERIOD_SID,
                                Isnull(SALES, 0) AS MONTHLY_SALES,
                                Isnull(UNITS, 0) AS MONTHLY_UNITS,
                                Sum(Isnull(SALES, 0))
                                                      OVER(
                                                        PARTITION BY FM.ITEM_ID, FORECAST_YEAR, FORECAST_QUARTER, T.PROJECTION_DETAILS_SID, SOURCE) AS QUARTERLY_GTS_SALES,
                                Sum(Isnull(UNITS, 0))
                                                       OVER(
                                                         PARTITION BY FM.ITEM_ID, FORECAST_YEAR, FORECAST_QUARTER, T.PROJECTION_DETAILS_SID, SOURCE) AS QUARTERLY_GTS_units,
                                T.QUARTER                     AS FORECAST_QUARTER,
                                T.MONTH,
                                T.YEAR,
                                T.ITEM_ID,
                                [SOURCE]
                         FROM   [dbo].[Udf_gts_details] (@GTS_DETAILS, @FROM_DATE, @PROJECTION_DATE) FM
                                RIGHT JOIN (SELECT PERIOD_SID,
                                                   G.ITEM_ID,
                                                   G.ITEM_MASTER_SID,
                                                   G.PROJECTION_DETAILS_SID,
                                                   MONTH,
                                                   YEAR,
                                                   QUARTER,
                                                   PERIOD_DATE
                                            FROM   PERIOD
                                                   CROSS JOIN #GTS_TEMPTABE G)T
                                        ON T.ITEM_ID = FM.ITEM_ID
                                           AND FM.START_PERIOD = T.PERIOD_DATE
                         WHERE  T.PERIOD_DATE >= @FROM_DATE
                                AND T.PERIOD_DATE <= @PROJECTION_DATE),
                     GTS_PIVOT_VALUES
                     AS (SELECT ITEM_ID,
                                PROJECTION_DETAILS_SID,
                                ITEM_MASTER_SID,
                                START_QUARTER,
                                PERIOD_SID,
                                MONTH,
                                [FORECAST_QUARTER],
                                Max(CASE
                                                           WHEN [SOURCE] = 1 THEN MONTHLY_SALES
                                                         END) AS MONTHLY_FORECAST_GTS,
                                Max(CASE
                                                         WHEN [SOURCE] = 0 THEN MONTHLY_SALES
                                                       END) AS MONTHLY_ACTUAL_GTS,
                                Max(CASE
                                                             WHEN [SOURCE] = 1 THEN MONTHLY_units
                                                           END) AS MONTHLY_FORECAST_units,
                                Max(CASE
                                                           WHEN [SOURCE] = 0 THEN MONTHLY_units
                                                         END) AS MONTHLY_ACTUAL_units,
                                Max(CASE
                                                   WHEN [SOURCE] = 1 THEN QUARTERLY_GTS_SALES
                                                 END) AS FORECAST_GTS,
                                Max(CASE
                                                 WHEN [SOURCE] = 0 THEN QUARTERLY_GTS_SALES
                                               END) AS ACTUAL_GTS,
                                Max(CASE
                                                     WHEN [SOURCE] = 1 THEN QUARTERLY_GTS_UNITS
                                                   END) AS FORECAST_units,
                                Max(CASE
                                                   WHEN [SOURCE] = 0 THEN QUARTERLY_GTS_units
                                                 END) AS ACTUAL_units,
                                YEAR
                         FROM   GTS_VALUES
                         GROUP  BY ITEM_ID,
                                   PROJECTION_DETAILS_SID,
                                   ITEM_MASTER_SID,
                                   START_QUARTER,
                                   PERIOD_SID,
                                   MONTH,
                                   [FORECAST_QUARTER],
                                   YEAR)
                INSERT INTO #TEMP_GTS
                            (ITEMID,
                             PROJECTION_DETAILS_SID,
                             START_QUARTER,
                             PERIOD_SID,
                             QUAT_SALES,
                             QUAT_UNITS,
                             SALES,
                             units)
                SELECT ITEM_ID                                                  AS ITEMID,
                       PROJECTION_DETAILS_SID,
                       START_QUARTER,
                       PERIOD_SID,
                       COALESCE(FORECAST_GTS, ACTUAL_GTS, 0)                    AS QUAT_SALES,
                       COALESCE(FORECAST_units, ACTUAL_units, 0)                AS QUAT_UNITS,
                       COALESCE(MONTHLY_FORECAST_GTS, MONTHLY_ACTUAL_GTS, 0)    AS SALES,
                       COALESCE(MONTHLY_FORECAST_units, MONTHLY_ACTUAL_units, 0)AS units
                FROM   GTS_PIVOT_VALUES

                CREATE TABLE #SALES_PROJECTION
                  (
                     PROJECTION_MASTER_SID    INT,
                     PROJECTION_DETAILS_SID   INT,
                     ITEM_ID                  VARCHAR(50),
                     PERIOD_SID               INT,
                     BASELINE_PROJECTED_SALES NUMERIC(38, 15),
                     BASELINE_PROJECTED_UNITS NUMERIC(38, 15),
                     PROJECTED_SALES          NUMERIC(38, 15),
                     PROJECTED_UNITS          NUMERIC(38, 15)
                  )

                SET @CTE='WITH CTE
                     AS (SELECT  F_TFG.PROJECTION_DETAILS_SID,
                                SALES * SALES_PERCENT AS BASELINE_PROJECTED_SALES,
                                UNITS * UNITS_PERCENT AS BASELINE_PROJECTED_UNITS,
								F_TFG.ITEMID,
                                F_TFG.PERIOD_SID,
                                CALCULATION_BASED,
                                PROJECTION_MASTER_SID
                         FROM   #TEMP_GTS F_TFG
                                CROSS APPLY (SELECT B.PROJECTION_DETAILS_SID,
                                                    '
                         + @PERCENT_OF_BUSINESS_SALES
                         + ' AS SALES_PERCENT,
                                                    '
                         + @PERCENT_OF_BUSINESS_UNITS
                         + ' AS UNITS_PERCENT,
                                                    TFG.ITEMID,
                                                    B.PERIOD_SID,
                                                    CALCULATION_BASED,
                                                    PROJECTION_MASTER_SID
                                             FROM   #TEMP_GTS TFG
                                                    JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 ACTUAL_SALES,
                                                                 ACTUAL_UNITS,
																 ITEMID,
                                                                 PERIOD_SID,
                                                                 CALCULATION_BASED,
                                                                 PROJECTION_MASTER_SID
                                                          FROM   #LAST_ACTUALS) B
                                                      ON TFG.PERIOD_SID = B.PERIOD_SID
													  AND F_TFG.PROJECTION_DETAILS_SID=B.PROJECTION_DETAILS_SID
													  AND B.PROJECTION_DETAILS_SID=TFG.PROJECTION_DETAILS_SID
													  AND TFG.ITEMID=B.ITEMID
                                             WHERE  TFG.ITEMID = F_TFG.ITEMID) CS),
                                                                           
                     CTE1
                     AS (SELECT PROJECTION_MASTER_SID,
                                PROJECTION_DETAILS_SID,
								A.ITEMID,
                                A.PERIOD_SID,
                                BASELINE_PROJECTED_SALES,
								BASELINE_PROJECTED_UNITS,
                                PROJECTED_SALES,
                                CASE
                                  WHEN CALCULATION_BASED = ''SALES'' THEN '
                         + @SALES_BASED_UNITS_PERCENT
                         + '
                                  ELSE BASELINE_PROJECTED_UNITS
                                END PROJECTED_UNITS
                         FROM   (SELECT PROJECTION_MASTER_SID,
                                        T.PROJECTION_DETAILS_SID,
										T.ITEMID,
                                        T.PERIOD_SID,
                                        BASELINE_PROJECTED_SALES,
										BASELINE_PROJECTED_UNITS,
                                        CASE
                                          WHEN CALCULATION_BASED = ''SALES'' THEN '
                         + @SALES_BASED_SALES_PERCENT
                         + '
                                          ELSE '
                         + @UNITS_BASED_SALES_PERCENT
                         + '
                                        END PROJECTED_SALES,
                                        CALCULATION_BASED,
                                        WAC_INCREASE,
                                        WAC_PRICE
                                 FROM   CTE T
                                        JOIN #TEMP_WAC WC
                                          ON T.PERIOD_SID = WC.PERIOD_SID
										  AND T.PROJECTION_DETAILS_SID=WC.PROJECTION_DETAILS_SID
										  AND T.ITEMID=WC.ITEMID
										 )A)
                                                                       
                                                                      
                INSERT INTO   #SALES_PROJECTION
                           SELECT *
                FROM   CTE1 order by period_sid'

                EXEC Sp_executesql
                  @CTE

                UPDATE A
                SET    A.PROJECTION_SALES = Isnull(CONVERT(NUMERIC(22, 6), B.PROJECTED_SALES), 0),
                       A.PROJECTION_UNITS = Isnull(CONVERT(NUMERIC(22, 6), B.PROJECTED_UNITS), 0)
                FROM   #SALES_PROJECTION B
                       JOIN ST_M_SALES_PROJECTION A
                         ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                            AND A.PERIOD_SID = B.PERIOD_SID
                            AND A.USER_ID = @USER_ID
                            AND A.SESSION_ID = @SESSION_ID

                UPDATE A
                SET    A.PROJECTION_SALES = 0,
                       A.PROJECTION_UNITS = 0
                FROM   ST_M_SALES_PROJECTION A
                       JOIN ST_M_SALES_PROJECTION_MASTER S
                         ON A.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                            AND S.[USER_ID] = @USER_ID
                            AND S.[SESSION_ID] = @SESSION_ID
                            AND S.CHECK_RECORD = 1
                WHERE  A.PROJECTION_SALES IS NULL
                       AND A.PROJECTION_UNITS IS NULL
            END
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