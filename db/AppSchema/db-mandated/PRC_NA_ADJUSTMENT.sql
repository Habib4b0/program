IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NA_ADJUSTMENT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NA_ADJUSTMENT]
  END

GO
CREATE PROCEDURE [dbo].[PRC_NA_ADJUSTMENT](@ITEM_MASTER_SID INT,
                                           @PRICE_TYPE      VARCHAR(50),
                                           @WORKSHEET       VARCHAR(50),
                                           @USER_ID         INT,
                                           @SESSION_ID      VARCHAR(100),
                                           @NDC9            VARCHAR(20)= NULL)
AS
    
SET NOCOUNT ON




    DECLARE @BUSINESS_UNIT INT
    DECLARE @GL_COMPANY_ID INT
    DECLARE @FLAG INT
    DECLARE @SQL1 NVARCHAR(MAX)= ''
       DECLARE @ADJUSTMENT_TYPE VARCHAR(100)

       SET @ADJUSTMENT_TYPE=(SELECT DESCRIPTION FROM HELPER_TABLE WHERE LIST_NAME='PRICE_TYPE_FORECAST_ADJUSTMENT')
    --------------------------------------------------------TEMP TABLES DECLARATION-------------------------------------------------------------------------------------------------------
    DECLARE @MEDICAID_URA_ACTUALS         VARCHAR(200) = Concat('ST_MEDICAID_URA_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
            @MEDICAID_URA_PROJ            VARCHAR(200) = Concat('ST_MEDICAID_URA_PROJ_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
            @fcp_actuals                  VARCHAR(200) = Concat('ST_FCP_actuals_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
            @PHS_PROJ                     VARCHAR(200) = Concat('ST_PHS_PROJ_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
            @FCP_PROJ                     VARCHAR(200) = Concat('ST_FCP_PROJ_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
            @NATIONAL_ASSUMPTIONS_ACTUALS VARCHAR(200) = Concat('ST_NATIONAL_ASSUMPTIONS_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
            @NATIONAL_ASSUMPTIONS_PROJ    VARCHAR(200) = Concat('ST_NATIONAL_ASSUMPTIONS_PROJ_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
            @NA_NDC9_WAC                  VARCHAR(100) =Concat('ST_NA_NDC9_WAC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
            @NA_NDC11_WAC                 VARCHAR(100) =Concat('ST_NA_NDC11_WAC_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
            @NATIONAL_ASSUMPTIONS         VARCHAR(200) = Concat('ST_NATIONAL_ASSUMPTIONS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

    --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    IF Object_id('TEMPDB..#ITEM_PRICING') IS NOT NULL -- TO STORE PROJECTION PRICE AND ADJUSTMENT DETAIL OF SELECTED ITEM
      TRUNCATE TABLE #ITEM_PRICING
    ELSE
      CREATE TABLE #ITEM_PRICING
        (
           NA_PROJ_DETAILS_SID INT,
           PRICE_TYPE          VARCHAR(50),
           PERIOD_SID          INT,
           PROJECTION_PRICE    NUMERIC(22, 6),
           ADJUSTMENT          NUMERIC(22, 6),
           INCREASE_PERCENTAGE NUMERIC(22, 6)
           PRIMARY KEY (NA_PROJ_DETAILS_SID, PRICE_TYPE, PERIOD_SID)
        );

    IF Object_id('TEMPDB..#ADJUST_PERIOD') IS NOT NULL -- TO STORE PROJECTION PRICE AND ADJUSTMENT DETAILS OF SELECTED ITEM FROM THE FIRST ADJUSTED PERIOD FOR EACH PRICE TYPE
      TRUNCATE TABLE #ADJUST_PERIOD
    ELSE
      CREATE TABLE #ADJUST_PERIOD
        (
           NA_PROJ_DETAILS_SID INT,
           PERIOD_SID          INT,
           PRICE_TYPE          VARCHAR(50),
           PROJECTION_PRICE    NUMERIC(22, 6),
           ADJUSTMENT          NUMERIC(22, 6),
           INCREASE_PERCENTAGE NUMERIC(22, 6)
           PRIMARY KEY (NA_PROJ_DETAILS_SID, PRICE_TYPE, PERIOD_SID)
        )

    IF Object_id('TEMPDB..#PERIOD_QUARTER') IS NOT NULL -- TO STORE THE PERIOD INFORMATION IN QUARTER BASIS
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

    INSERT INTO #PERIOD_QUARTER
                (PERIOD_SID,
                 PERIOD_YEAR,
                 PERIOD_QUARTER,
                 SEMI_ANNUAL,
                 Q_PERIOD,
                 PERIOD_DATE)
    SELECT Min(PERIOD_SID)  AS PERIOD_SID,
           [YEAR],
           [QUARTER],
           SEMI_ANNUAL,
           'Q' + CONVERT(VARCHAR(2), [QUARTER]) + ' '
           + CONVERT(VARCHAR(5), [YEAR]),
           Min(PERIOD_DATE) AS PERIOD_DATE
    FROM   PERIOD
    GROUP  BY [YEAR],
              [QUARTER],
              SEMI_ANNUAL

    -------------------------------------------------------------------GLA-808---------------------------------------------------------------------------
    SELECT @BUSINESS_UNIT = BUSINESS_UNIT,
           @GL_COMPANY_ID = NA.COMPANY_MASTER_SID
    FROM   NA_PROJ_MASTER NA
           JOIN NA_PROJ_DETAILS D
             ON NA.NA_PROJ_MASTER_SID = D.NA_PROJ_MASTER_SID
    WHERE  D.ITEM_MASTER_SID = @ITEM_MASTER_SID

    --------------------------------------------------------------------END------------------------------------------------------------------------------
    -- ADJUSTMENT CALCULATION START
    IF Object_id ('tempdb..#ST_NATIONAL_ASSUMPTIONS') IS NOT NULL
      DROP TABLE #ST_NATIONAL_ASSUMPTIONS

    CREATE TABLE #ST_NATIONAL_ASSUMPTIONS
      (
         [NA_PROJ_MASTER_SID]   [INT] NOT NULL,
         [PRICE_TYPE]           [VARCHAR](50) NOT NULL,
         [BASELINE_METHODOLOGY] [VARCHAR](50) NOT NULL,
         [FORECAST_METHODOLOGY] [VARCHAR](50) NOT NULL,
         [PRICE_BASIS]          [VARCHAR](30) NULL,
         [BASELINE_PERIOD]      [VARCHAR](8000) NOT NULL,
         [ROLLING_PERIOD]       [VARCHAR](8000) NULL,
         [START_PERIOD]         [VARCHAR](8) NOT NULL,
         [END_PERIOD]           [VARCHAR](8) NOT NULL,
         [GROWTH_RATE]          [NUMERIC](22, 6) NULL,
         [FREQUENCY]            [VARCHAR](50) NULL,
         [CPI_COMPOUNDING]      [VARCHAR](20) NULL,
         [LAST_MODIFIED_DATE]   [DATETIME] NULL,
         PRIMARY KEY( [NA_PROJ_MASTER_SID] ASC, [PRICE_TYPE] ASC, [START_PERIOD] ASC, [END_PERIOD] ASC)
      )

    SET @SQL1 ='INSERT INTO #ST_NATIONAL_ASSUMPTIONS (NA_PROJ_MASTER_SID
,PRICE_TYPE
,BASELINE_METHODOLOGY
,FORECAST_METHODOLOGY
,PRICE_BASIS
,BASELINE_PERIOD
,ROLLING_PERIOD
,START_PERIOD
,END_PERIOD
,GROWTH_RATE
,FREQUENCY
,CPI_COMPOUNDING
,LAST_MODIFIED_DATE)
SELECT NA_PROJ_MASTER_SID
,case PRICE_TYPE when  ''FSS(OGA)'' then ''QFSS'' when ''Non-FAMP'' then ''QNON-FAMP'' else PRICE_TYPE end
,BASELINE_METHODOLOGY
,FORECAST_METHODOLOGY
,PRICE_BASIS
,BASELINE_PERIOD
,ROLLING_PERIOD
,START_PERIOD
,END_PERIOD
,GROWTH_RATE
,FREQUENCY
,CPI_COMPOUNDING
,LAST_MODIFIED_DATE
FROM  ' + @NATIONAL_ASSUMPTIONS  +'  WHERE PRICE_TYPE NOT IN (''CPI-U'') '

    EXEC Sp_executesql
      @SQL1
       
         declare  @END_PERIOD_SID1 int
         select @END_PERIOD_SID1 =period_sid from period p join #ST_NATIONAL_ASSUMPTIONS st on datefromparts(st.end_period,01,01)=p.period_date   WHERE PRICE_TYPE='AFSS'


       
          
    IF @WORKSHEET = 'MEDICAID URA' -- ADJUSTMENT CALCULATION FOR MEDICAID URA WORKSHEET START
      BEGIN

          IF EXISTS (SELECT 1
                           FROM   #ST_NATIONAL_ASSUMPTIONS
                           WHERE  FORECAST_METHODOLOGY = '% of WAC')
                                            BEGIN

                                            SET @SQL1 = 'UPDATE M SET ADJUSTMENT_PRICE=ADJUSTMENT FROM '+@MEDICAID_URA_PROJ+ ' M JOIN #ST_NATIONAL_ASSUMPTIONS ST ON M.PRICE_TYPE=ST.PRICE_TYPE AND ST.FORECAST_METHODOLOGY=''% of WAC'' WHERE ADJUSTMENT IS NOT NULL'
                                            
                                            EXEC(@SQL1) 
                                            END
          DECLARE @BASELINE_AMP    NUMERIC(22, 6),
                  @BASE_CPI        NUMERIC(22, 6),
                  @ST_BASELINE_AMP NUMERIC(22, 6),
                  @ST_BASE_CPI     NUMERIC(22, 6)

          SELECT TOP 1 @BASELINE_AMP = BASELINE_AMP,
                       @BASE_CPI = BASE_CPI
          FROM   ITEM_MASTER
          WHERE  NDC9 = @NDC9
          ORDER  BY ITEM_MASTER_SID

          DECLARE @MEDICAID1 NVARCHAR(MAX) =''

          SET @MEDICAID1 = N'
                       SELECT TOP 1 @ST_BASELINE_AMP = MAX(CASE
                                                WHEN PRICE_TYPE = ''AMP'' THEN ALT_BASE_YEAR
                                                END),
                           @ST_BASE_CPI     = MAX(CASE
                                                WHEN PRICE_TYPE = ''CPI-U'' THEN ALT_BASE_YEAR
                                                END)
             FROM   '
                           + @MEDICAID_URA_ACTUALS
                           + ' MUA
             WHERE  EXISTS (SELECT 1
                           FROM   NA_PROJ_DETAILS NPD
                                  INNER JOIN ITEM_MASTER IM
                                          ON IM.ITEM_MASTER_SID = NPD.ITEM_MASTER_SID
                           WHERE  MUA.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
                                  AND IM.NDC9 = @NDC9)
                   AND PRICE_TYPE IN ( ''AMP'', ''CPI-U'' )'

          EXEC Sp_executesql
            @MEDICAID1,
            N'@NDC9 VARCHAR(20), @ST_BASELINE_AMP NUMERIC(22,6) OUTPUT, @ST_BASE_CPI NUMERIC(22,6) OUTPUT',
            @NDC9 = @NDC9,
            @ST_BASELINE_AMP = @ST_BASELINE_AMP OUTPUT,
            @ST_BASE_CPI = @ST_BASE_CPI OUTPUT

          IF Object_id('TEMPDB..#MEDICAID_URA_PROJ') IS NOT NULL
            DROP TABLE #MEDICAID_URA_PROJ

          CREATE TABLE #MEDICAID_URA_PROJ
            (
               NA_PROJ_DETAILS_SID INT,
               PERIOD_SID          INT,
               PRICE_TYPE          VARCHAR(50),
               PROJECTION_PRICE    NUMERIC(22, 6),
               ADJUSTMENT          NUMERIC(22, 6),
               NOTES               VARCHAR(MAX),
               ADJUSTMENT_PRICE    NUMERIC(22, 6)
            )

          DECLARE @MEDICAID2 NVARCHAR(MAX)

          SET @MEDICAID2 = 'INSERT INTO #MEDICAID_URA_PROJ 
                     SELECT NA_PROJ_DETAILS_SID , PERIOD_SID, PRICE_TYPE, PROJECTION_PRICE , ADJUSTMENT , NOTES, ADJUSTMENT_PRICE FROM '
                           + @MEDICAID_URA_PROJ + ''

          EXEC Sp_executesql
            @MEDICAID2

          SELECT @FLAG = 1
          FROM   #MEDICAID_URA_PROJ MUP
                 JOIN NA_PROJ_DETAILS ND
                   ON MUP.NA_PROJ_DETAILS_SID = ND.NA_PROJ_DETAILS_SID
          WHERE  EXISTS (SELECT 1
                         FROM   NA_PROJ_DETAILS NPD
                                INNER JOIN ITEM_MASTER IM
                                        ON IM.ITEM_MASTER_SID = NPD.ITEM_MASTER_SID
                         WHERE  MUP.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
                                AND IM.NDC9 = @NDC9)
                 AND PRICE_TYPE IN ( 'AMP', 'BP', 'CPI-U' )
                 AND ADJUSTMENT IS NOT NULL


          IF @FLAG = 1
              OR ( @ST_BASELINE_AMP <> @BASELINE_AMP )
              OR ( @ST_BASE_CPI <> @BASE_CPI )
            BEGIN 
                     
                     ;
              
                WITH PROJECTION_DETAILS
                     AS (SELECT NA_PROJ_DETAILS_SID,
                                COALESCE(ADJUSTMENT_PRICE, PROJECTION_PRICE) AS PROJECTION_PRICE,
                                ADJUSTMENT,
                                PERIOD_SID,
                                PRICE_TYPE
                         FROM   #MEDICAID_URA_PROJ MUP
                         WHERE  EXISTS (SELECT 1
                                        FROM   NA_PROJ_DETAILS NPD
                                               INNER JOIN ITEM_MASTER IM
                                                       ON IM.ITEM_MASTER_SID = NPD.ITEM_MASTER_SID
                                        WHERE  MUP.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
                                               AND IM.NDC9 =@NDC9)
                                AND EXISTS (SELECT 1
                                            FROM   DBO.Udf_splitstring(@PRICE_TYPE, ',') SS
                                            WHERE  MUP.PRICE_TYPE = SS.TOKEN))
                INSERT INTO #ITEM_PRICING
                            (NA_PROJ_DETAILS_SID,
                             PERIOD_SID,
                             PRICE_TYPE,
                             ADJUSTMENT,
                             PROJECTION_PRICE,
                             INCREASE_PERCENTAGE)
                SELECT A.NA_PROJ_DETAILS_SID,
                       A.PERIOD_SID,
                       A.PRICE_TYPE,
                       A.ADJUSTMENT,
                       A.PROJECTION_PRICE,
                       Round(( ( A.PROJECTION_PRICE - B.PROJECTION_PRICE ) / NULLIF(B.PROJECTION_PRICE, 0) ) * 100, 5) AS INCREASE_PERCENTAGE
                FROM   PROJECTION_DETAILS A
                       LEFT OUTER JOIN PROJECTION_DETAILS B
                                    ON A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID
                                       AND A.PRICE_TYPE = B.PRICE_TYPE
                                       AND A.PERIOD_SID = B.PERIOD_SID + 3;

                WITH ADJUST_START_PERIOD
                     AS (SELECT Min(PERIOD_SID) AS MIN_ADJUST_PERIOD_SID,
                                PRICE_TYPE
                         FROM   #ITEM_PRICING IP
                         WHERE  EXISTS (SELECT 1
                                        FROM   DBO.Udf_splitstring(@PRICE_TYPE, ',') SS
                                        WHERE  IP.PRICE_TYPE = SS.TOKEN)
                                AND ADJUSTMENT IS NOT NULL
                         GROUP  BY PRICE_TYPE),
                     ADJUST_PERIOD
                     AS (SELECT *
                         FROM   #ITEM_PRICING IP
                         WHERE  EXISTS (SELECT 1
                                        FROM   ADJUST_START_PERIOD ASP
                                        WHERE  IP.PRICE_TYPE = ASP.PRICE_TYPE
                                               AND IP.PERIOD_SID >= ASP.MIN_ADJUST_PERIOD_SID))
                INSERT INTO #ADJUST_PERIOD
                            (NA_PROJ_DETAILS_SID,
                             PERIOD_SID,
                             PRICE_TYPE,
                             PROJECTION_PRICE,
                             ADJUSTMENT,
                             INCREASE_PERCENTAGE)
                SELECT NA_PROJ_DETAILS_SID,
                       PERIOD_SID,
                       PRICE_TYPE,
                       PROJECTION_PRICE,
                       ADJUSTMENT,
                       INCREASE_PERCENTAGE
                FROM   ADJUST_PERIOD

                     
                     
                IF EXISTS (SELECT 1
                           FROM   #ST_NATIONAL_ASSUMPTIONS
                           WHERE  FORECAST_METHODOLOGY = 'Price Trending') -- Price Trending FORECASTING METHODOLOGY START
                  BEGIN
                      IF Object_id ('tempdb..#ndc9_wac_increase') IS NOT NULL
                        DROP TABLE #ndc9_wac_increase

                      CREATE TABLE #ndc9_wac_increase
                        (
                           ndc                 VARCHAR(50),
                           PERIOD_SID          INT,
                           BQWAC               NUMERIC(22, 6),
                           EQWAC               NUMERIC(22, 6),
                           MQWAC               NUMERIC(22, 6),
                           AVGQWAC             NUMERIC(22, 6),
                           DAY_WEIGHTED_WAC    NUMERIC(22, 6),
                           SALES_WEIGHTED_WAC  NUMERIC(22, 6),
                           item_master_sid     INT,
                           NA_PROJ_DETAILS_SID INT
                        )

                      SET @SQL1 = 'insert into #ndc9_wac_increase
              SELECT n.NDC9 as ndc,
       PERIOD_SID,
       BQWAC =  isnull(((BQWAC - lag(BQWAC) over(partition by n.NDC9 order by PERIOD_SID))/nullif(lag(BQWAC) over(partition by n.NDC9 order by PERIOD_SID),0)),0) ,
       EQWAC = isnull( ((EQWAC - lag(EQWAC) over(partition by n.NDC9 order by PERIOD_SID))/nullif(lag(EQWAC) over(partition by n.NDC9 order by PERIOD_SID),0)),0) ,
       MQWAC =  isnull(((MQWAC - lag(MQWAC) over(partition by n.NDC9 order by PERIOD_SID))/nullif(lag(MQWAC) over(partition by n.NDC9 order by PERIOD_SID),0)),0) ,
       AVGQWAC = isnull(((AVGQWAC - lag(AVGQWAC) over(partition by n.NDC9 order by PERIOD_SID))/nullif(lag(AVGQWAC) over(partition by n.NDC9 order by PERIOD_SID),0)),0),
       DAY_WEIGHTED_WAC = isnull(((DAY_WEIGHTED_WAC - lag(DAY_WEIGHTED_WAC) over(partition by n.NDC9 order by PERIOD_SID))/nullif(lag(DAY_WEIGHTED_WAC) over(partition by n.NDC9 order by PERIOD_SID),0)) ,0),
       SALES_WEIGHTED_WAC = isnull(((SALES_WEIGHTED_WAC - lag(SALES_WEIGHTED_WAC) over(partition by n.NDC9 order by PERIOD_SID))/nullif(lag(SALES_WEIGHTED_WAC) over(partition by n.NDC9 order by PERIOD_SID),0)) ,0)
          ,im.item_master_sid,
          npd.NA_PROJ_DETAILS_SID
FROM   ' + @NA_NDC9_WAC
                                  + ' n join ITEM_MASTER im on n.ndc9 =im.ndc9 join NA_PROJ_DETAILS npd on npd.ITEM_MASTER_SID = im.ITEM_MASTER_SID
join (select distinct NA_PROJ_DETAILS_SID from '
                                  + @MEDICAID_URA_ACTUALS
                                  + ' ) m on m.NA_PROJ_DETAILS_sid = npd.NA_PROJ_DETAILS_sid  
where im.NDC9 = @ndc9 '

                      EXEC Sp_executesql
                        @SQL1,
                        N'@ndc9 VARCHAR(50)',
                        @ndc9 = @ndc9;

                      WITH ORDER_CTE
                           AS (SELECT ap.NA_PROJ_DETAILS_SID,
                                      ap.PERIOD_SID,
                                      ap.PRICE_TYPE,
                                      Isnull(ADJUSTMENT, 0)       AS ADJUSTMENT,
                                      PROJECTION_PRICE,
                                      CASE s.PRICE_BASIS
                                        WHEN 'EQWAC' THEN EQWAC
                                        WHEN 'BQWAC' THEN BQWAC
                                        WHEN 'MQWAC' THEN MQWAC
                                        WHEN 'AVGQWAC' THEN AVGQWAC
                                        WHEN 'DAY WEIGHTED WAC' THEN DAY_WEIGHTED_WAC
                                        WHEN 'SALES WEIGHTED WAC' THEN SALES_WEIGHTED_WAC
                                      END                         INCREASE_PERCENTAGE,
                                      Row_number()
                                        OVER (
                                          PARTITION BY ap.NA_PROJ_DETAILS_SID, AP.PRICE_TYPE
                                          ORDER BY n.PERIOD_SID ) AS RN
                               FROM   #ADJUST_PERIOD AP
                                      JOIN #ndc9_wac_increase n
                                        ON n.NA_PROJ_DETAILS_SID = ap.NA_PROJ_DETAILS_SID
                                           AND ap.PERIOD_SID = n.PERIOD_SID
                                      JOIN #ST_NATIONAL_ASSUMPTIONS s
                                        ON s.PRICE_TYPE = ap.PRICE_TYPE
                               WHERE  s.FORECAST_METHODOLOGY = 'Price Trending'),
                           RECURSIVE_CTE
                           AS (SELECT OC.NA_PROJ_DETAILS_SID,
                                      OC.PERIOD_SID,
                                      OC.INCREASE_PERCENTAGE,
                                      OC.RN,
                                      OC.PRICE_TYPE,
                                                                CASE WHEN @ADJUSTMENT_TYPE='INCREMENTAL' THEN 
                                      Cast((  ADJUSTMENT ) AS NUMERIC(22, 6))
                                                                ELSE 
                                                                 Cast(( OC.PROJECTION_PRICE + ADJUSTMENT ) AS NUMERIC(22, 6)) END AS PROJECTION_PRICE
                               FROM   ORDER_CTE OC
                               WHERE  OC.RN = 1
                               UNION ALL
                               SELECT OC.NA_PROJ_DETAILS_SID,
                                      OC.PERIOD_SID,
                                      OC.INCREASE_PERCENTAGE,
                                      OC.RN,
                                      OC.PRICE_TYPE,
                                      Cast(( RC.PROJECTION_PRICE * ( 1 + ( Isnull(OC.INCREASE_PERCENTAGE, 0) / 100 ) ) ) + ADJUSTMENT AS NUMERIC(22, 6)) AS PROJECTION_PRICE
                               FROM   ORDER_CTE OC
                                      JOIN RECURSIVE_CTE RC
                                        ON OC.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                           AND OC.PRICE_TYPE = RC.PRICE_TYPE
                                           AND OC.RN = RC.RN + 1)
                      UPDATE MUP
                      SET    MUP.ADJUSTMENT_PRICE = Isnull(RC.PROJECTION_PRICE, 0)
                                  
                      FROM   #MEDICAID_URA_PROJ MUP
                             INNER JOIN RECURSIVE_CTE RC
                                     ON MUP.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                        AND MUP.PERIOD_SID = RC.PERIOD_SID
                                        AND MUP.PRICE_TYPE = RC.PRICE_TYPE
                      OPTION (MAXRECURSION 0)
                  END -- Price Trending FORECASTING METHODOLOGY END
                IF EXISTS (SELECT 1
                           FROM   #ST_NATIONAL_ASSUMPTIONS
                           WHERE  FORECAST_METHODOLOGY = 'GROWTH')-- GROWTH FORECSTING METHODOLOGY START. THIS METHODOLOGY HAS CPI COMPOUNDING. CPI COMP
                  BEGIN
                      IF EXISTS (SELECT 1
                                 FROM   #ST_NATIONAL_ASSUMPTIONS
                                 WHERE  CPI_COMPOUNDING <> 'QUARTERLY'
                                        AND FORECAST_METHODOLOGY = 'GROWTH')
                        --AND @PRICE_TYPE = 'CPI-U' --GALUAT-48 CHANGE START (ROLLING AVERAGE FOR CPI-U)
                        BEGIN ;
                            WITH ORDER_CTE
                                 AS (SELECT ap.NA_PROJ_DETAILS_SID,
                                            Min(ap.PERIOD_SID)               PERIOD_SID,
                                            ap.PRICE_TYPE,
                                            Isnull(Min(ADJUSTMENT), 0)       AS ADJUSTMENT,
                                            Min(PROJECTION_PRICE)            PROJECTION_PRICE,
                                            CASE n.FREQUENCY
                                              WHEN 'ANNUAL' THEN ( n.GROWTH_RATE / 100.0 ) / 4.0
                                              WHEN 'SEMI-ANNUAL' THEN ( n.GROWTH_RATE / 100.0 ) / 2.0
                                              ELSE ( n.GROWTH_RATE / 100.0 ) -- CPI Compounding Annual or Growth Frequency quarterly 
                                            END                              INCREASE_PERCENTAGE,
                                            Row_number()
                                              OVER (
                                                PARTITION BY ap.NA_PROJ_DETAILS_SID, AP.PRICE_TYPE
                                                ORDER BY Min(p.PERIOD_SID) ) AS RN,
                                            year
                                     FROM   #ADJUST_PERIOD AP
                                            JOIN #ST_NATIONAL_ASSUMPTIONS n
                                              ON n.price_type = ap.price_type
                                            JOIN PERIOD p
                                              ON p.PERIOD_SID = ap.PERIOD_SID
                                     WHERE  n.FORECAST_METHODOLOGY = 'GROWTH'
                                            AND n.CPI_COMPOUNDING <> 'QUARTERLY'
                                     GROUP  BY ap.NA_PROJ_DETAILS_SID,
                                               ap.PRICE_TYPE,
                                               CASE n.FREQUENCY
                                                 WHEN 'ANNUAL' THEN ( n.GROWTH_RATE / 100.0 ) / 4.0
                                                 WHEN 'SEMI-ANNUAL' THEN ( n.GROWTH_RATE / 100.0 ) / 2.0
                                                 ELSE ( n.GROWTH_RATE / 100.0 ) -- CPI Compounding Annual or Growth Frequency quarterly 
                                               END,
                                               p.YEAR),
                                 RECURSIVE_CTE
                                 AS (SELECT OC.NA_PROJ_DETAILS_SID,
                                            OC.PERIOD_SID,
                                            OC.INCREASE_PERCENTAGE,
                                            OC.RN,
                                            OC.PRICE_TYPE,
                                            CASE WHEN @ADJUSTMENT_TYPE='INCREMENTAL' THEN 
                                      Cast((  ADJUSTMENT ) AS NUMERIC(22, 6))
                                                                ELSE 
                                                                 Cast(( OC.PROJECTION_PRICE + ADJUSTMENT ) AS NUMERIC(22, 6)) END AS PROJECTION_PRICE,
                                            YEAR
                                     FROM   ORDER_CTE OC
                                     WHERE  OC.RN = 1
                                     UNION ALL
                                     SELECT OC.NA_PROJ_DETAILS_SID,
                                            OC.PERIOD_SID,
                                            OC.INCREASE_PERCENTAGE,
                                            OC.RN,
                                            OC.PRICE_TYPE,
                                            Cast(( RC.PROJECTION_PRICE * ( 1 + ( Isnull(OC.INCREASE_PERCENTAGE, 0) / 100 ) ) ) + ADJUSTMENT AS NUMERIC(22, 6)) AS PROJECTION_PRICE,
                                            oc.YEAR
                                     FROM   ORDER_CTE OC
                                            JOIN RECURSIVE_CTE RC
                                              ON OC.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                                 AND OC.PRICE_TYPE = RC.PRICE_TYPE
                                                 AND OC.RN = RC.RN + 1)
                            UPDATE MUP
                            SET    MUP.ADJUSTMENT_PRICE = Isnull(RC.PROJECTION_PRICE, 0)
                            FROM   #MEDICAID_URA_PROJ MUP
                                   JOIN PERIOD p
                                     ON p.PERIOD_SID = mup.PERIOD_SID
                                   INNER JOIN RECURSIVE_CTE RC
                                           ON MUP.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                              AND p.YEAR = RC.year
                                              AND MUP.PRICE_TYPE = RC.PRICE_TYPE
                            OPTION (MAXRECURSION 0)
                        END
                       IF EXISTS (SELECT 1
                                 FROM   #ST_NATIONAL_ASSUMPTIONS
                                 WHERE  CPI_COMPOUNDING = 'QUARTERLY'
                                        AND FORECAST_METHODOLOGY = 'GROWTH') -- ANNUAL CPI COMPOUNDING
                        BEGIN ;
                            WITH ORDER_CTE
                                 AS (SELECT ap.NA_PROJ_DETAILS_SID,
                                            ap.PERIOD_SID,
                                            ap.PRICE_TYPE,
                                            Isnull(ADJUSTMENT, 0)       AS ADJUSTMENT,
                                            PROJECTION_PRICE            PROJECTION_PRICE,
                                            CASE n.FREQUENCY
                                              WHEN 'ANNUAL' THEN ( n.GROWTH_RATE / 100.0 ) / 4.0
                                              WHEN 'SEMI-ANNUAL' THEN ( n.GROWTH_RATE / 100.0 ) / 2.0
                                              ELSE ( n.GROWTH_RATE / 100.0 ) -- CPI Compounding Annual or Growth Frequency quarterly 
                                            END                         INCREASE_PERCENTAGE,
                                            Row_number()
                                              OVER (
                                                PARTITION BY ap.NA_PROJ_DETAILS_SID, AP.PRICE_TYPE
                                                ORDER BY p.PERIOD_SID ) AS RN
                                     FROM   #ADJUST_PERIOD AP
                                            JOIN #ST_NATIONAL_ASSUMPTIONS n
                                              ON n.price_type = ap.price_type
                                            JOIN PERIOD p
                                              ON p.PERIOD_SID = ap.PERIOD_SID
                                     WHERE  n.FORECAST_METHODOLOGY = 'GROWTH'
                                            AND n.CPI_COMPOUNDING = 'QUARTERLY'),
                                 RECURSIVE_CTE
                                 AS (SELECT OC.NA_PROJ_DETAILS_SID,
                                            OC.PERIOD_SID,
                                            OC.INCREASE_PERCENTAGE,
                                            OC.RN,
                                            OC.PRICE_TYPE,
                                            CASE WHEN @ADJUSTMENT_TYPE='INCREMENTAL' THEN 
                                      Cast((  ADJUSTMENT ) AS NUMERIC(22, 6))
                                                                ELSE 
                                                                 Cast(( OC.PROJECTION_PRICE + ADJUSTMENT ) AS NUMERIC(22, 6)) END AS PROJECTION_PRICE
                                     FROM   ORDER_CTE OC
                                     WHERE  OC.RN = 1
                                     UNION ALL
                                     SELECT OC.NA_PROJ_DETAILS_SID,
                                            OC.PERIOD_SID,
                                            OC.INCREASE_PERCENTAGE,
                                            OC.RN,
                                            OC.PRICE_TYPE,
                                            Cast(( RC.PROJECTION_PRICE * ( 1 + ( Isnull(OC.INCREASE_PERCENTAGE, 0) / 100 ) ) ) + ADJUSTMENT AS NUMERIC(22, 6)) AS PROJECTION_PRICE
                                     FROM   ORDER_CTE OC
                                            JOIN RECURSIVE_CTE RC
                                              ON OC.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                                 AND OC.PRICE_TYPE = RC.PRICE_TYPE
                                                 AND OC.RN = RC.RN + 1)
                                                                           ---    select * from RECURSIVE_CTE
                            UPDATE MUP
                            SET    MUP.ADJUSTMENT_PRICE = Isnull(RC.PROJECTION_PRICE, 0)
                            FROM   #MEDICAID_URA_PROJ MUP
                                   INNER JOIN RECURSIVE_CTE RC
                                           ON MUP.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                              AND MUP.PERIOD_SID = RC.PERIOD_SID
                                              AND MUP.PRICE_TYPE = RC.PRICE_TYPE
                            OPTION (MAXRECURSION 0)
                        END
                  END -- GROWTH FORECSTING METHODOLOGY END

                               

                IF EXISTS (SELECT 1
                           FROM   #ST_NATIONAL_ASSUMPTIONS
                           WHERE  FORECAST_METHODOLOGY = 'ROLLING AVERAGE') -- ROLLING AVERAGE FORECSTING METHODOLOGY START
                  BEGIN
                      ------------------------------------ADDED ON 4th AUGUST 2016--------------------------------------------         
                      IF Object_id('TEMPDB.DBO.#ROLLING_AVERAGE', 'U') IS NOT NULL
                        DROP TABLE #ROLLING_AVERAGE;

                      --------------------------------------------------------------------------------------------------------         
                      IF Object_id('TEMPDB.DBO.#na_actuals_med', 'U') IS NOT NULL
                        DROP TABLE #na_actuals_med;

                      CREATE TABLE #na_actuals_med
                        (
                           NA_PROJ_DETAILS_SID INT,
                           PERIOD_SID          SMALLINT,
                           PRICE_TYPE          VARCHAR(100),
                           ACTUAL_PRICE        NUMERIC(22, 6)
                        )

                      SET @SQL1 = ''
                      SET @SQL1 = '    insert into #na_actuals_med (NA_PROJ_DETAILS_SID,PERIOD_SID,PRICE_TYPE,ACTUAL_PRICE)
                     select NA_PROJ_DETAILS_SID,PERIOD_SID,PRICE_TYPE,ACTUAL_PRICE from '
                                  + @MEDICAID_URA_ACTUALS

                     EXEC Sp_executesql
                        @SQL1;

                      WITH ITEM_PRICING
                           AS (SELECT IP.NA_PROJ_DETAILS_SID,
                                      IP.ACTUAL_PRICE,
                                      IP.PERIOD_SID,
                                      Row_number()
                                        OVER(
                                          PARTITION BY IP.NA_PROJ_DETAILS_SID
                                          ORDER BY IP.PERIOD_SID ASC) AS RN,
                                      s.price_type
                               FROM   #na_actuals_med IP
                                      INNER JOIN #PERIOD_QUARTER PQ
                                              ON PQ.PERIOD_SID = IP.PERIOD_SID
                                      JOIN #ST_NATIONAL_ASSUMPTIONS s
                                        ON s.PRICE_TYPE = ip.PRICE_TYPE
                                      CROSS apply DBO.Udf_splitstring(s.ROLLING_PERIOD, ',') ud
                               WHERE  ud.token = pq.Q_PERIOD
                                      AND FORECAST_METHODOLOGY = 'ROLLING AVERAGE'
                                      AND s.price_type IN (SELECT token
                                                           FROM   Udf_splitstring(@PRICE_TYPE, ',')))
                      SELECT A.NA_PROJ_DETAILS_SID,
                             a.PRICE_TYPE,
                             Avg(( ( A.ACTUAL_PRICE - B.ACTUAL_PRICE ) / NULLIF(B.ACTUAL_PRICE, 0) ) * 100) AS ROLLING_AVERGARE
                      INTO   #ROLLING_AVERAGE
                      FROM   ITEM_PRICING A
                             INNER JOIN ITEM_PRICING B -- USED INNER JOIN SINCE WE DON'T NEED THE UNMATCHED DATA 
                                     ON A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID
                                        AND a.PRICE_TYPE = b.PRICE_TYPE
                                        AND A.RN = B.RN + 1
                      GROUP  BY A.NA_PROJ_DETAILS_SID,
                                a.PRICE_TYPE

                      IF EXISTS (SELECT 1
                                 FROM   #ST_NATIONAL_ASSUMPTIONS
                                 WHERE  CPI_COMPOUNDING <> 'QUARTERLY'
                                        AND PRICE_TYPE = 'CPI-U'
                                        AND FORECAST_METHODOLOGY = 'ROLLING AVERAGE')
                        BEGIN ;
                            WITH ORDER_CTE
                                 AS (SELECT ap.NA_PROJ_DETAILS_SID,
                                            Min(ap.PERIOD_SID)               PERIOD_SID,
                                            ap.PRICE_TYPE,
                                            Isnull(Min(ADJUSTMENT), 0)       AS ADJUSTMENT,
                                            Min(PROJECTION_PRICE)            PROJECTION_PRICE,
                                            ROLLING_AVERGARE                 INCREASE_PERCENTAGE,
                                            Row_number()
                                              OVER (
                                                PARTITION BY ap.NA_PROJ_DETAILS_SID, AP.PRICE_TYPE
                                                ORDER BY Min(p.PERIOD_SID) ) AS RN,
                                            year
                                     FROM   #ADJUST_PERIOD AP
                                            JOIN #ST_NATIONAL_ASSUMPTIONS n
                                              ON n.price_type = ap.price_type
                                            JOIN PERIOD p
                                              ON p.PERIOD_SID = ap.PERIOD_SID
                                            JOIN #ROLLING_AVERAGE r
                                              ON r.NA_PROJ_DETAILS_SID = ap.NA_PROJ_DETAILS_SID
                                                 AND r.PRICE_TYPE = ap.PRICE_TYPE
                                     WHERE  n.PRICE_TYPE = 'CPI-U'
                                            AND FORECAST_METHODOLOGY = 'ROLLING AVERAGE'
                                            AND n.CPI_COMPOUNDING <> 'QUARTERLY'
                                     GROUP  BY ap.NA_PROJ_DETAILS_SID,
                                               ap.PRICE_TYPE,
                                               ROLLING_AVERGARE,
                                               p.YEAR),
                                 RECURSIVE_CTE
                                 AS (SELECT OC.NA_PROJ_DETAILS_SID,
                                            OC.PERIOD_SID,
                                            OC.INCREASE_PERCENTAGE,
                                            OC.RN,
                                            OC.PRICE_TYPE,
                                            CASE WHEN @ADJUSTMENT_TYPE='INCREMENTAL' THEN 
                                      Cast((  ADJUSTMENT ) AS NUMERIC(22, 6))
                                                                ELSE 
                                                                 Cast(( OC.PROJECTION_PRICE + ADJUSTMENT ) AS NUMERIC(22, 6)) END AS PROJECTION_PRICE,
                                            YEAR
                                     FROM   ORDER_CTE OC
                                     WHERE  OC.RN = 1
                                     UNION ALL
                                     SELECT OC.NA_PROJ_DETAILS_SID,
                                            OC.PERIOD_SID,
                                            OC.INCREASE_PERCENTAGE,
                                            OC.RN,
                                            OC.PRICE_TYPE,
                                            Cast(( RC.PROJECTION_PRICE * ( 1 + ( Isnull(OC.INCREASE_PERCENTAGE, 0) / 100 ) ) ) + ADJUSTMENT AS NUMERIC(22, 6)) AS PROJECTION_PRICE,
                                            oc.YEAR
                                     FROM   ORDER_CTE OC
                                            JOIN RECURSIVE_CTE RC
                                              ON OC.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                                 AND OC.PRICE_TYPE = RC.PRICE_TYPE
                                                 AND OC.RN = RC.RN + 1)
                            UPDATE MUP
                            SET    MUP.ADJUSTMENT_PRICE = Isnull(RC.PROJECTION_PRICE, 0)
                            FROM   #MEDICAID_URA_PROJ MUP
                                   JOIN PERIOD p
                                     ON p.PERIOD_SID = mup.PERIOD_SID
                                   INNER JOIN RECURSIVE_CTE RC
                                           ON MUP.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                              AND p.YEAR = RC.year
                                              AND MUP.PRICE_TYPE = RC.PRICE_TYPE
                            OPTION (MAXRECURSION 0)
                        END ---- ROLLING AVERAGE FORECSTING METHODOLOGY END
                       IF EXISTS (SELECT 1
                                 FROM   #ST_NATIONAL_ASSUMPTIONS
                                 WHERE  CPI_COMPOUNDING ='QUARTERLY'                                        
                                        AND FORECAST_METHODOLOGY = 'ROLLING AVERAGE')
                        BEGIN ;
                            WITH ORDER_CTE
                                 AS (SELECT ap.NA_PROJ_DETAILS_SID,
                                            ap.PERIOD_SID,
                                            ap.PRICE_TYPE,
                                            Isnull(ADJUSTMENT, 0)       AS ADJUSTMENT,
                                            PROJECTION_PRICE            PROJECTION_PRICE,
                                            ROLLING_AVERGARE            INCREASE_PERCENTAGE,
                                            Row_number()
                                              OVER (
                                                PARTITION BY ap.NA_PROJ_DETAILS_SID, AP.PRICE_TYPE
                                                ORDER BY p.PERIOD_SID ) AS RN
                                     FROM   #ADJUST_PERIOD AP
                                            JOIN #ST_NATIONAL_ASSUMPTIONS n
                                              ON n.price_type = ap.price_type
                                            JOIN PERIOD p
                                              ON p.PERIOD_SID = ap.PERIOD_SID
                                            JOIN #ROLLING_AVERAGE r
                                              ON r.NA_PROJ_DETAILS_SID = ap.NA_PROJ_DETAILS_SID
                                                 AND r.PRICE_TYPE = ap.PRICE_TYPE
                                     WHERE  FORECAST_METHODOLOGY = 'ROLLING AVERAGE'
                                            AND n.CPI_COMPOUNDING = 'QUARTERLY'
                                                                           ),
                                 RECURSIVE_CTE
                                 AS (SELECT OC.NA_PROJ_DETAILS_SID,
                                            OC.PERIOD_SID,
                                            OC.INCREASE_PERCENTAGE,
                                            OC.RN,
                                            OC.PRICE_TYPE,
                                           CASE WHEN @ADJUSTMENT_TYPE='INCREMENTAL' THEN 
                                      Cast((  ADJUSTMENT ) AS NUMERIC(22, 6))
                                                                ELSE 
                                                                 Cast(( OC.PROJECTION_PRICE + ADJUSTMENT ) AS NUMERIC(22, 6)) END AS PROJECTION_PRICE
                                     FROM   ORDER_CTE OC
                                     WHERE  OC.RN = 1
                                     UNION ALL
                                     SELECT OC.NA_PROJ_DETAILS_SID,
                                            OC.PERIOD_SID,
                                            OC.INCREASE_PERCENTAGE,
                                            OC.RN,
                                            OC.PRICE_TYPE,
                                            Cast(( RC.PROJECTION_PRICE * ( 1 + ( Isnull(OC.INCREASE_PERCENTAGE, 0) / 100 ) ) ) + ADJUSTMENT AS NUMERIC(22, 6)) AS PROJECTION_PRICE
                                     FROM   ORDER_CTE OC
                                            JOIN RECURSIVE_CTE RC
                                              ON OC.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                                 AND OC.PRICE_TYPE = RC.PRICE_TYPE
                                                 AND OC.RN = RC.RN + 1)
                            UPDATE MUP
                            SET    MUP.ADJUSTMENT_PRICE = Isnull(RC.PROJECTION_PRICE, 0)
                            FROM   #MEDICAID_URA_PROJ MUP
                                   INNER JOIN RECURSIVE_CTE RC
                                           ON MUP.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                              AND MUP.PERIOD_SID = RC.PERIOD_SID
                                              AND MUP.PRICE_TYPE = RC.PRICE_TYPE
                            OPTION (MAXRECURSION 0)
                        END
                  END;

                           

                                           ---------------CEL-978
DECLARE @MED NVARCHAR(MAX)
SET @MED=''
SET @MED=CONCAT(@MED,';  WITH URA
                     AS (SELECT A.NA_PROJ_DETAILS_SID,
                                PERIOD_SID,
                                CASE
                                  WHEN Isnull(AMP, 0) - Isnull(BP, 0) > Isnull(AMP, 0) * 0.231 THEN Isnull(AMP, 0) - Isnull(BP, 0)
                                  ELSE Isnull(AMP, 0) * 0.231
                                END BASIC_URA,
                                CASE
                                  WHEN ( AMP - (( CPI / NULLIF(BASE_CPI, 0) * BASELINE_AMP )) ) < 1 THEN 0
                                  ELSE ( AMP - (( CPI / NULLIF(BASE_CPI, 0) * BASELINE_AMP )) )
                                END CPI_URA,
                                AMP
                         FROM   (SELECT MUP.NA_PROJ_DETAILS_SID,
                                        PERIOD_SID,
                                        Max(CASE
                                              WHEN PRICE_TYPE = ''AMP'' THEN COALESCE(ADJUSTMENT_PRICE, PROJECTION_PRICE)
                                            END) AS AMP,
                                        Max(CASE
                                              WHEN PRICE_TYPE = ''BEST PRICE'' THEN COALESCE(ADJUSTMENT_PRICE, PROJECTION_PRICE)
                                            END) AS BP,
                                        Max(CASE
                                              WHEN PRICE_TYPE = ''CPI-U'' THEN COALESCE(ADJUSTMENT_PRICE, PROJECTION_PRICE)
                                            END) AS CPI,
                                        BASELINE_AMP,
                                        BASE_CPI
                                 FROM   #MEDICAID_URA_PROJ MUP
                                        INNER JOIN (SELECT NA_PROJ_DETAILS_SID,
                                                           Max(CASE
                                                                 WHEN PRICE_TYPE = ''AMP'' THEN ALT_BASE_YEAR
                                                               END) AS BASELINE_AMP,
                                                           Max(CASE
                                                                 WHEN PRICE_TYPE = ''CPI-U'' THEN ALT_BASE_YEAR
                                                               END) AS BASE_CPI
                                                    FROM   ',@MEDICAID_URA_ACTUALS,' MUA
                                                    WHERE  PRICE_TYPE IN( ''AMP'', ''CPI-U'' )
                                                           AND EXISTS (SELECT 1
                                                                       FROM   #ITEM_PRICING IP
                                                                       WHERE  IP.NA_PROJ_DETAILS_SID = MUA.NA_PROJ_DETAILS_SID)
                                                    GROUP  BY NA_PROJ_DETAILS_SID) ACT
                                                ON ACT.NA_PROJ_DETAILS_SID = MUP.NA_PROJ_DETAILS_SID
                                 WHERE  PRICE_TYPE IN( ''AMP'', ''BEST PRICE'', ''CPI-U'' )
                                        AND EXISTS (SELECT 1
                                                    FROM   #ITEM_PRICING IP
                                                    WHERE  IP.NA_PROJ_DETAILS_SID = MUP.NA_PROJ_DETAILS_SID)
                                 GROUP  BY MUP.NA_PROJ_DETAILS_SID,
                                           PERIOD_SID,
                                           BASELINE_AMP,
                                           BASE_CPI) A)
                UPDATE MUP
                SET    MUP.ADJUSTMENT_PRICE = ITEM_PRICE
                FROM   #MEDICAID_URA_PROJ MUP
                       INNER JOIN (SELECT NA_PROJ_DETAILS_SID,
                                          PERIOD_SID,
                                          ITEM_PRICE,
                                          PRICE_TYPE
                                   FROM   URA
                                          CROSS APPLY (VALUES (BASIC_URA,
                                                      ''BASIC URA''),
                                                              (CPI_URA,
                                                      ''CPI URA''),
                                                              (CASE
                                                        WHEN ( Isnull(BASIC_URA, 0) + Isnull(CPI_URA, 0) ) > AMP THEN AMP
                                                        ELSE ( Isnull(BASIC_URA, 0) + Isnull(CPI_URA, 0) )
                                                      END,
                                                      ''TOTAL URA'')) CS (ITEM_PRICE, PRICE_TYPE)) A
                               ON MUP.NA_PROJ_DETAILS_SID = A.NA_PROJ_DETAILS_SID
                                  AND MUP.PRICE_TYPE = A.PRICE_TYPE
                                  AND MUP.PERIOD_SID = A.PERIOD_SID')
                                                       
                  EXEC Sp_executesql  @MED
                             ---------------cel-978 end

                SET @MED = N' UPDATE MP
                  SET    MP.ADJUSTMENT_PRICE = SMP.ADJUSTMENT_PRICE
                  FROM   '
                           + @MEDICAID_URA_PROJ
                           + ' MP
                         INNER JOIN #MEDICAID_URA_PROJ SMP
                                 ON MP.NA_PROJ_DETAILS_SID = SMP.NA_PROJ_DETAILS_SID
                                    AND MP.PRICE_TYPE = SMP.PRICE_TYPE
                                    AND MP.PERIOD_SID = SMP.PERIOD_SID'

                EXEC Sp_executesql  @MED
            END
          ELSE -- WHEN USER REMOVES THE ADJUSTMENT THE ORIGINAL VALUE SHOULD POPULATED TO ADJUSTMENT COLUMN 
            BEGIN
                DECLARE @MEDICAID6 NVARCHAR(MAX)

                SET @MEDICAID6 = N'UPDATE MUP
                  SET    ADJUSTMENT_PRICE = PROJECTION_PRICE
                  FROM   '
                                 + @MEDICAID_URA_PROJ
                                 + ' MUP
                         JOIN NA_PROJ_DETAILS ND
                           ON MUP.NA_PROJ_DETAILS_SID = ND.NA_PROJ_DETAILS_SID
                  WHERE  EXISTS (SELECT 1
                                 FROM   NA_PROJ_DETAILS NPD
                                        INNER JOIN ITEM_MASTER IM
                                                ON NPD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                 WHERE  MUP.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
                                        AND NDC9 = @NDC9)
                         AND PRICE_TYPE IN ( ''AMP'', ''BP'', ''CPI-U'', ''BASIC URA'',
                                             ''CPI URA'', ''TOTAL URA'' )'

                EXEC Sp_executesql
                  @MEDICAID6,
                  N'@NDC9 VARCHAR(20)',
                  @NDC9 = @NDC9
            END
      END -- ADJUSTMENT CALCULATION FOR MEDICAID URA WORKSHEET END
    --------------------------------------------------------------PHS STARTS----------------------------------------------------------------------------------
    ELSE IF @WORKSHEET = 'PHS WORKSHEET' -- ADJUSTMENT CALCULATION FOR PHS WORKSHEET START
      BEGIN
          DECLARE @PHS1 NVARCHAR(MAX)

          SET @PHS1 = '(SELECT @FLAG = 1
                              FROM   '
                      + @PHS_PROJ
                      + ' PP
                                     JOIN NA_PROJ_DETAILS ND
                                       ON PP.NA_PROJ_DETAILS_SID = ND.NA_PROJ_DETAILS_SID
                              WHERE  EXISTS (SELECT 1
                                             FROM   NA_PROJ_DETAILS NPD
                                             WHERE  PP.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
                                                    AND NPD.ITEM_MASTER_SID = @ITEM_MASTER_SID)
                                     AND PRICE_TYPE = ''PHS''
                                     AND ADJUSTMENT IS NOT NULL)'

          EXEC Sp_executesql
            @PHS1,
            N'@ITEM_MASTER_SID INT, @FLAG INT OUTPUT',
            @ITEM_MASTER_SID = @ITEM_MASTER_SID,
            @FLAG = @FLAG OUTPUT

          IF @FLAG = 1
            BEGIN
                DECLARE @PHS2 NVARCHAR(MAX)

                SET @PHS2 = N'UPDATE PP
                  SET    PP.ADJUSTMENT_PRICE = COALESCE(ADJUSTMENT_PRICE,0)
                                               + ISNULL(PP.ADJUSTMENT, 0)
                  FROM   ' + @PHS_PROJ
                            + ' PP
                  WHERE  EXISTS (SELECT 1
                                 FROM   NA_PROJ_DETAILS NPD
                                 WHERE  PP.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
                                        AND NPD.ITEM_MASTER_SID = @ITEM_MASTER_SID)
                         AND PRICE_TYPE = ''PHS'' AND ADJUSTMENT IS NOT NULL'

                EXEC Sp_executesql
                  @PHS2,
                  N'@ITEM_MASTER_SID INT',
                  @ITEM_MASTER_SID = @ITEM_MASTER_SID
            END
          ELSE
            BEGIN
                DECLARE @PHS3 NVARCHAR(MAX)

                SET @PHS3 = N'UPDATE PP
                  SET    ADJUSTMENT_PRICE = PROJECTION_PRICE
                  FROM   ' + @PHS_PROJ
                            + ' PP
                         JOIN NA_PROJ_DETAILS ND
                           ON PP.NA_PROJ_DETAILS_SID = ND.NA_PROJ_DETAILS_SID
                  WHERE  EXISTS (SELECT 1
                                 FROM   NA_PROJ_DETAILS NPD
                                 WHERE  PP.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
                                        AND NPD.ITEM_MASTER_SID = @ITEM_MASTER_SID)
                         AND PRICE_TYPE = ''PHS'' '

                EXEC Sp_executesql
                  @PHS3,
                  N'@ITEM_MASTER_SID  INT',
                  @ITEM_MASTER_SID = @ITEM_MASTER_SID
            END
      END -- ADJUSTMENT CALCULATION FOR PHS WORKSHEET END
    ----------------------------------------------------------------------------------------------------------------------------------------------------------
    ELSE IF @WORKSHEET = 'FCP WORKSHEET'
      BEGIN
          DECLARE @FCP1 NVARCHAR(MAX)

		   IF EXISTS (SELECT 1
                           FROM   #ST_NATIONAL_ASSUMPTIONS
                           WHERE  FORECAST_METHODOLOGY = '% of WAC')
                                            BEGIN

                                            SET @SQL1 = 'UPDATE M SET ADJUSTMENT_PRICE=ADJUSTMENT FROM '+@FCP_PROJ+ ' M JOIN #ST_NATIONAL_ASSUMPTIONS ST ON M.PRICE_TYPE=ST.PRICE_TYPE AND ST.FORECAST_METHODOLOGY=''% of WAC'' WHERE ADJUSTMENT IS NOT NULL'
                                            
                                            EXEC(@SQL1) 
                                            END


          SET @FCP1 = N'(SELECT @FLAG = 1
                      FROM   ' + @FCP_PROJ
                      + ' FP
                             JOIN NA_PROJ_DETAILS ND
                               ON FP.NA_PROJ_DETAILS_SID = ND.NA_PROJ_DETAILS_SID
                      WHERE  EXISTS (SELECT 1
                                     FROM   NA_PROJ_DETAILS NPD
                                     WHERE  FP.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
                                            AND NPD.ITEM_MASTER_SID = @ITEM_MASTER_SID)
                             AND PRICE_TYPE IN ( ''QNON-FAMP'',''QFSS'' )
                             AND ADJUSTMENT IS NOT NULL)'

          EXEC Sp_executesql
            @FCP1,
            N'@ITEM_MASTER_SID INT, @FLAG INT OUTPUT',
            @ITEM_MASTER_SID = @ITEM_MASTER_SID,
            @FLAG = @FLAG OUTPUT


       

--SELECT FP.NA_PROJ_DETAILS_SID
--            ,FP.PERIOD_SID + 3 PERIOD_SID
--            ,'AFSS' PRICE_TYPE
--            ,FP.PROJECTION_PRICE
--            ,FP.ADJUSTMENT
--            ,NOTES
--            ,ADJUSTMENT_PRICE
--            ,ND.NA_PROJ_MASTER_SID
--            ,ND.ITEM_MASTER_SID
--            ,COALESCE(LEAD(PERIOD_SID, 1) OVER (
--                         PARTITION BY FP.NA_PROJ_DETAILS_SID ORDER BY PERIOD_SID
--                         ), @END_PERIOD_SID) PERIOD_SID1
--            ,ROW_NUMBER() OVER (
--                   PARTITION BY FP.NA_PROJ_DETAILS_SID ORDER BY FP.PERIOD_SID
--                   ) RN
--     FROM ST_FCP_PROJ_10734_105330673_170603 FP
--     JOIN NA_PROJ_DETAILS ND ON FP.NA_PROJ_DETAILS_SID = ND.NA_PROJ_DETAILS_SID
--     WHERE EXISTS (
--                   SELECT 1
--                   FROM NA_PROJ_DETAILS NPD
--                   WHERE FP.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
--                         AND NPD.ITEM_MASTER_SID = 1
--                   )
--            AND PRICE_TYPE IN ('QFSS')
--            AND ADJUSTMENT IS NOT NULL
--end
IF Object_id ('TEMPDB..#ADJUSTMENT_PERIOD_PRICE') IS NOT NULL
  DROP TABLE #ADJUSTMENT_PERIOD_PRICE;

CREATE TABLE #ADJUSTMENT_PERIOD_PRICE
  (
     NA_PROJ_DETAILS_SID INT,
     PRICE_TYPE          VARCHAR(50),
     PERIOD_SID          INT,
       PERIOD_SID1         INT,
       ADJUSTMENT     NUMERIC(22,6),
     RN                  INT,
     RN1                 INT
  ) 

         SET @FCP1=CONCAT('
;WITH CTE
AS (
       SELECT FP.NA_PROJ_DETAILS_SID
              ,FP.PERIOD_SID + 3 PERIOD_SID
              ,''AFSS'' PRICE_TYPE
              ,FP.PROJECTION_PRICE
              ,ISNULL(FP.ADJUSTMENT,0) AS ADJUSTMENT
              ,NOTES
              ,ADJUSTMENT_PRICE
              ,ND.NA_PROJ_MASTER_SID
              ,ND.ITEM_MASTER_SID
              ,COALESCE(LEAD(PERIOD_SID, 1) OVER (
                           PARTITION BY FP.NA_PROJ_DETAILS_SID ORDER BY PERIOD_SID
                           ), ',@END_PERIOD_SID1,') PERIOD_SID1
              ,ROW_NUMBER() OVER (
                     PARTITION BY FP.NA_PROJ_DETAILS_SID ORDER BY FP.PERIOD_SID
                     ) RN
       FROM '+@FCP_PROJ+' FP
       JOIN NA_PROJ_DETAILS ND ON FP.NA_PROJ_DETAILS_SID = ND.NA_PROJ_DETAILS_SID
       WHERE EXISTS (
                     SELECT 1
                     FROM NA_PROJ_DETAILS NPD
                     WHERE FP.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
                           AND NPD.ITEM_MASTER_SID = ',@ITEM_MASTER_SID,'
                     )
              AND PRICE_TYPE IN (''QFSS'')
              AND ADJUSTMENT IS NOT NULL)
       
         INSERT INTO #ADJUSTMENT_PERIOD_PRICE
         (
          NA_PROJ_DETAILS_SID,
          PRICE_TYPE  ,       
          PERIOD_SID, 
          PERIOD_SID1,  
          ADJUSTMENT,      
          RN ,                
          RN1                
         )
              SELECT B.NA_PROJ_DETAILS_SID,B.PRICE_TYPE,B.PERIOD_SID,B.PERIOD_SID+12 PERIOD_SID2 ,A.ADJUSTMENT,RN,
              ROW_NUMBER() OVER (PARTITION BY B.NA_PROJ_DETAILS_SID,RN  ORDER BY RN) RN1 
               FROM CTE A JOIN '+@FCP_PROJ+' B
              ON A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID  AND B.PRICE_TYPE=''AFSS''
              AND B.PERIOD_SID  BETWEEN A.PERIOD_SID AND A.PERIOD_SID1')

              
              EXEC Sp_executesql
            @FCP1
              
                           
              

          IF @FLAG = 1
            BEGIN
                DECLARE @ACTUAL_START_DATE     DATETIME,
                        @PROJECTION_START_DATE DATETIME,
                        @PROJECTION_END_DATE   DATETIME,
                        @ACTUAL_END_DATE       DATETIME,
                        @BUSINESS_PROCESS_TYPE VARCHAR(50),
                        @PROJ_PERIOD_START_SID INT,
                        @PROJ_PERIOD_END_SID   INT,
                        @ACT_PERIOD_START_SID  INT,
                        @ACT_PERIOD_END_SID    INT

                SET @BUSINESS_PROCESS_TYPE = 'NATIONAL ASSUMPTIONS'

                SELECT @ACTUAL_START_DATE = ACTUAL_START_DATE,
                       @ACTUAL_END_DATE = ACTUAL_END_DATE,
                       @PROJECTION_START_DATE = PROJECTION_START_DATE,
                       @PROJECTION_END_DATE = PROJECTION_END_DATE
                FROM   [DBO].[Udf_na_proj_dates](@BUSINESS_PROCESS_TYPE)
                           --select * from  [DBO].[Udf_na_proj_dates]('national assumptions')

                SELECT @ACT_PERIOD_START_SID = Max(CASE
                                                     WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_START_DATE), 0) THEN PERIOD_SID
                                                   END),
                       @ACT_PERIOD_END_SID = Max(CASE
                                                   WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_END_DATE), 0) THEN PERIOD_SID
                                                 END),
                       @PROJ_PERIOD_START_SID = Max(CASE
                                                      WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_START_DATE), 0) THEN PERIOD_SID
                                                    END),
                       @PROJ_PERIOD_END_SID = Max(CASE
                                                    WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_END_DATE), 0) THEN PERIOD_SID
                                                  END)
                FROM   (SELECT PERIOD_SID,
                               PERIOD_DATE
                        FROM   #PERIOD_QUARTER
                        WHERE  PERIOD_DATE IN ( Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_START_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_END_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_START_DATE), 0), Dateadd(QQ, Datediff(QQ, 0, @PROJECTION_END_DATE), 0) )) A

                ------------------------------------------ SELECTED ITEM BASED ON PROJECTION_DETAILS_SID 
                IF Object_id('tempdb..#PROJECTION_DETAILS') IS NOT NULL
                  DROP TABLE #PROJECTION_DETAILS

                CREATE TABLE #PROJECTION_DETAILS
                  (
                     NA_PROJ_DETAILS_SID INT,
                     ITEM_MASTER_SID     INT
                  )

                DECLARE @FCP2 NVARCHAR(MAX)

                SET @FCP2 = N'
                                     INSERT INTO #PROJECTION_DETAILS
                       SELECT PD.NA_PROJ_DETAILS_SID,
                             ITEM_MASTER_SID
                       FROM   NA_PROJ_DETAILS PD
                             INNER JOIN (SELECT DISTINCT NA_PROJ_DETAILS_SID
                                         FROM   '
                            + @FCP_PROJ
                            + '
                                         WHERE  NA_PROJ_DETAILS_SID IN (SELECT NA_PROJ_DETAILS_SID
                                                                        FROM   NA_PROJ_DETAILS
                                                                        WHERE  ITEM_MASTER_SID = @ITEM_MASTER_SID)) A
                                     ON PD.NA_PROJ_DETAILS_SID = A.NA_PROJ_DETAILS_SID
                  ---------------------------------------------------------------------- QUARTER WISE NON-FAMP AND FSS ADJUSTMENT START HERE'

                EXECUTE Sp_executesql
                  @FCP2,
                  N'@ITEM_MASTER_SID INT',
                  @ITEM_MASTER_SID = @ITEM_MASTER_SID

                DECLARE @FCP3 NVARCHAR(MAX)

                SET @FCP3 = N'
                             ;WITH PROJECTION_DETAILS
                       AS (SELECT NA_PROJ_DETAILS_SID,
                                  PROJECTION_PRICE=COALESCE(ADJUSTMENT_PRICE, PROJECTION_PRICE),
                                  ADJUSTMENT,
                                  PERIOD_SID,
                                  PRICE_TYPE
                           FROM   '
                            + @FCP_PROJ
                            + ' FP
                           WHERE  EXISTS (SELECT 1
                                          FROM   NA_PROJ_DETAILS NPD
                                          WHERE  FP.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
                                                 AND NPD.ITEM_MASTER_SID = @ITEM_MASTER_SID)
                                  AND EXISTS (SELECT 1
                                              FROM   DBO.UDF_SPLITSTRING(@PRICE_TYPE, '','') SS
                                              WHERE  FP.PRICE_TYPE = SS.TOKEN))
                  INSERT INTO #ITEM_PRICING
                              (NA_PROJ_DETAILS_SID,
                               PERIOD_SID,
                               PRICE_TYPE,
                               ADJUSTMENT,
                               PROJECTION_PRICE,
                               INCREASE_PERCENTAGE)
                  SELECT A.NA_PROJ_DETAILS_SID,
                         A.PERIOD_SID,
                         A.PRICE_TYPE,
                         A.ADJUSTMENT,
                         A.PROJECTION_PRICE,
                         INCREASE_PERCENTAGE= ROUND(( ( A.PROJECTION_PRICE - B.PROJECTION_PRICE ) / NULLIF(B.PROJECTION_PRICE, 0) ) * 100, 5)
                  FROM   PROJECTION_DETAILS A
                         LEFT OUTER JOIN PROJECTION_DETAILS B
                                      ON A.PRICE_TYPE = B.PRICE_TYPE
                                         AND A.PERIOD_SID = B.PERIOD_SID + 3'

                EXECUTE Sp_executesql
                  @FCP3,
                  N'@ITEM_MASTER_SID int , @PRICE_TYPE VARCHAR(100)',
                  @ITEM_MASTER_SID = @ITEM_MASTER_SID,
                  @PRICE_TYPE = @PRICE_TYPE;

                WITH ADJUST_START_PERIOD
                     AS (SELECT Min(PERIOD_SID) AS MIN_ADJUST_PERIOD_SID,
                                PRICE_TYPE
                         FROM   #ITEM_PRICING IP
                         WHERE  EXISTS (SELECT 1
                                        FROM   DBO.Udf_splitstring(@PRICE_TYPE, ',') SS
                                        WHERE  IP.PRICE_TYPE = SS.TOKEN)
                                AND ADJUSTMENT IS NOT NULL
                         GROUP  BY PRICE_TYPE),
                     ADJUST_PERIOD
                     AS (SELECT *
                         FROM   #ITEM_PRICING IP
                         WHERE  EXISTS (SELECT 1
                                        FROM   ADJUST_START_PERIOD ASP
                                        WHERE  IP.PRICE_TYPE = ASP.PRICE_TYPE
                                               AND IP.PERIOD_SID >= ASP.MIN_ADJUST_PERIOD_SID))
                INSERT INTO #ADJUST_PERIOD
                            (NA_PROJ_DETAILS_SID,
                             PERIOD_SID,
                             PRICE_TYPE,
                             PROJECTION_PRICE,
                             ADJUSTMENT,
                             INCREASE_PERCENTAGE)
                SELECT NA_PROJ_DETAILS_SID,
                       PERIOD_SID,
                       PRICE_TYPE,
                       PROJECTION_PRICE,
                       ADJUSTMENT,
                       INCREASE_PERCENTAGE
                FROM   ADJUST_PERIOD

                IF EXISTS (SELECT 1
                           FROM   #ST_NATIONAL_ASSUMPTIONS
                           WHERE  FORECAST_METHODOLOGY = 'Price Trending') -- Price Trending FORECASTING METHODOLOGY START
                  BEGIN
                      IF Object_id ('tempdb..#ndc11_wac_increase') IS NOT NULL
                        DROP TABLE #ndc11_wac_increase

                      CREATE TABLE #ndc11_wac_increase
                        (
                           ndc                 VARCHAR(50),
                           PERIOD_SID          INT,
                           BQWAC               NUMERIC(22, 6),
                           EQWAC               NUMERIC(22, 6),
                           MQWAC               NUMERIC(22, 6),
                           AVGQWAC             NUMERIC(22, 6),
                           DAY_WEIGHTED_WAC    NUMERIC(22, 6),
                           SALES_WEIGHTED_WAC  NUMERIC(22, 6),
                           item_master_sid     INT,
                           NA_PROJ_DETAILS_SID INT
                        )

                      SET @SQL1 =''
                      SET @SQL1 = '
  INSERT INTO #ndc11_wac_increase
  SELECT n.ITEM_MASTER_sid ndc,
       n.PERIOD_SID,
BQWAC = isnull(((BQWAC - lag(BQWAC) over(partition by n.item_master_sid order by PERIOD_SID))/nullif(lag(BQWAC) over(partition by n.item_master_sid order by PERIOD_SID),0)),0) ,
       EQWAC = isnull( ((EQWAC - lag(EQWAC) over(partition by n.item_master_sid order by PERIOD_SID))/nullif(lag(EQWAC) over(partition by n.item_master_sid order by PERIOD_SID),0)),0) ,
       MQWAC =    isnull(((MQWAC - lag(MQWAC) over(partition by n.item_master_sid order by PERIOD_SID))/nullif(lag(MQWAC) over(partition by n.item_master_sid order by PERIOD_SID),0)),0) ,
       AVGQWAC =  isnull(((AVGQWAC - lag(AVGQWAC) over(partition by n.item_master_sid order by PERIOD_SID))/nullif(lag(AVGQWAC) over(partition by n.item_master_sid order by PERIOD_SID),0)),0),
       DAY_WEIGHTED_WAC =  isnull(((DAY_WEIGHTED_WAC - lag(DAY_WEIGHTED_WAC) over(partition by n.item_master_sid order by PERIOD_SID))/nullif(lag(DAY_WEIGHTED_WAC) over(partition by n.item_master_sid order by PERIOD_SID),0)) ,0),
       SALES_WEIGHTED_WAC =  isnull(((SALES_WEIGHTED_WAC - lag(SALES_WEIGHTED_WAC) over(partition by n.item_master_sid order by PERIOD_SID))/nullif(lag(SALES_WEIGHTED_WAC) over(partition by n.item_master_sid order by PERIOD_SID),0)) ,0)
         , n.item_master_sid,
          npd.NA_PROJ_DETAILS_SID
         
FROM   ' + @NA_NDC11_WAC
                                  + ' n join NA_PROJ_DETAILS npd on npd.ITEM_MASTER_SID = N.ITEM_MASTER_SID
join (select distinct NA_PROJ_DETAILS_SID from '
                                  + @fcp_actuals
                                  + ' ) m on m.NA_PROJ_DETAILS_sid = npd.NA_PROJ_DETAILS_sid  
where npd.ITEM_MASTER_SID = @ITEM_MASTER_SID '

                      EXEC Sp_executesql
                        @SQL1,
                        N'@ITEM_MASTER_SID INT',
                        @ITEM_MASTER_SID = @ITEM_MASTER_SID

                      SET @SQL1 = ''
                      SET @SQL1 = ';WITH ORDER_CTE
                       AS (SELECT ap.NA_PROJ_DETAILS_SID,
                                  ap.PERIOD_SID,
                                  ap.PRICE_TYPE,
                                  ISNULL(ADJUSTMENT, 0) AS ADJUSTMENT,
                                  PROJECTION_PRICE,
                                  CASE s.PRICE_BASIS WHEN ''EQWAC'' THEN EQWAC
                                                                                             WHEN ''BQWAC'' THEN BQWAC
                                                                                                                                           WHEN ''MQWAC'' THEN MQWAC
                                                                                                                                           WHEN ''AVGQWAC'' THEN AVGQWAC
                                                                                                                                           WHEN ''DAY WEIGHTED WAC'' THEN DAY_WEIGHTED_WAC
                                                                                                                                           WHEN ''SALES WEIGHTED WAC'' THEN SALES_WEIGHTED_WAC END INCREASE_PERCENTAGE,
                                  ROW_NUMBER()
                                    OVER (
                                      PARTITION BY ap.NA_PROJ_DETAILS_SID, AP.PRICE_TYPE
                                      ORDER BY n.PERIOD_SID ) AS RN
                           FROM   #ADJUST_PERIOD AP join #ndc11_wac_increase n on n.NA_PROJ_DETAILS_SID = ap.NA_PROJ_DETAILS_SID and ap.PERIOD_SID = n.PERIOD_SID
                                            join #ST_NATIONAL_ASSUMPTIONS s on s.PRICE_TYPE = ap.PRICE_TYPE
                                            where s.FORECAST_METHODOLOGY = ''Price Trending''
                                            ),
                       RECURSIVE_CTE
                       AS (SELECT OC.NA_PROJ_DETAILS_SID,
                                  OC.PERIOD_SID,
                                  OC.INCREASE_PERCENTAGE,
                                  OC.RN,
                                  OC.PRICE_TYPE,
                                   CASE WHEN '''+@ADJUSTMENT_TYPE+'''=''INCREMENTAL'' THEN 
                                      Cast((  ADJUSTMENT ) AS NUMERIC(22, 6))
                                                                ELSE 
                                                                 Cast(( OC.PROJECTION_PRICE + ADJUSTMENT ) AS NUMERIC(22, 6)) END AS PROJECTION_PRICE
                           FROM   ORDER_CTE OC
                           WHERE  OC.RN = 1
                           UNION ALL
                           SELECT OC.NA_PROJ_DETAILS_SID,
                                  OC.PERIOD_SID,
                                  OC.INCREASE_PERCENTAGE,
                                  OC.RN,
                                  OC.PRICE_TYPE,
                                  CAST(( RC.PROJECTION_PRICE * ( 1 + ( ISNULL(OC.INCREASE_PERCENTAGE, 0) ) ) ) + ADJUSTMENT AS NUMERIC(22, 6)) AS PROJECTION_PRICE
                           FROM   ORDER_CTE OC
                                  JOIN RECURSIVE_CTE RC
                                    ON OC.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                       AND OC.PRICE_TYPE = RC.PRICE_TYPE
                                       AND OC.RN = RC.RN + 1)

                  UPDATE MUP
                  SET    MUP.ADJUSTMENT_PRICE = ISNULL(RC.PROJECTION_PRICE, 0)
                  FROM   ' + @FCP_PROJ
                                  + ' MUP
                         INNER JOIN RECURSIVE_CTE RC
                                 ON MUP.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                    AND MUP.PERIOD_SID = RC.PERIOD_SID
                                    AND MUP.PRICE_TYPE = RC.PRICE_TYPE
                  OPTION (MAXRECURSION 0)'

                      EXEC Sp_executesql
                        @SQL1
                  END -- Price Trending FORECASTING METHODOLOGY END
                IF EXISTS (SELECT 1
                           FROM   #ST_NATIONAL_ASSUMPTIONS
                           WHERE  FORECAST_METHODOLOGY = 'GROWTH')-- GROWTH FORECSTING METHODOLOGY START. THIS METHODOLOGY HAS CPI COMPOUNDING. CPI COMP
                  BEGIN
                      IF EXISTS (SELECT 1
                                 FROM   #ST_NATIONAL_ASSUMPTIONS
                                 WHERE  CPI_COMPOUNDING <> 'QUARTERLY'
                                        AND FORECAST_METHODOLOGY = 'GROWTH')
                        --AND @PRICE_TYPE = 'CPI-U' --GALUAT-48 CHANGE START (ROLLING AVERAGE FOR CPI-U)
                        BEGIN
                            SET @SQL1 = ''
                            SET @SQL1 = ';WITH ORDER_CTE
                       AS (SELECT ap.NA_PROJ_DETAILS_SID,
                                  min(ap.PERIOD_SID) PERIOD_SID,
                                  ap.PRICE_TYPE,
                                  ISNULL(min(ADJUSTMENT), 0) AS ADJUSTMENT,
                                  min(PROJECTION_PRICE) PROJECTION_PRICE,
                                  CASE n.FREQUENCY
                                    WHEN ''ANNUAL'' THEN ( n.GROWTH_RATE / 100.0 ) / 4.0
                                    WHEN ''SEMI-ANNUAL'' THEN ( n.GROWTH_RATE / 100.0 ) / 2.0
                                    ELSE ( n.GROWTH_RATE / 100.0 ) -- CPI Compounding Annual or Growth Frequency quarterly 
                                  END INCREASE_PERCENTAGE,
                                  ROW_NUMBER()
                                    OVER (
                                      PARTITION BY ap.NA_PROJ_DETAILS_SID, AP.PRICE_TYPE
                                      ORDER BY Min(p.PERIOD_SID) ) AS RN,
                                                                year
                           FROM   #ADJUST_PERIOD AP join #ST_NATIONAL_ASSUMPTIONS n on n.price_type = ap.price_type 
                                            join PERIOD p on p.PERIOD_SID = ap.PERIOD_SID 
                                            where n.FORECAST_METHODOLOGY = ''GROWTH'' and n.CPI_COMPOUNDING <> ''QUARTERLY''
                                            group by ap.NA_PROJ_DETAILS_SID,ap.PRICE_TYPE, CASE n.FREQUENCY
                                    WHEN ''ANNUAL'' THEN ( n.GROWTH_RATE / 100.0 ) / 4.0
                                    WHEN ''SEMI-ANNUAL'' THEN ( n.GROWTH_RATE / 100.0 ) / 2.0
                                    ELSE ( n.GROWTH_RATE / 100.0 ) -- CPI Compounding Annual or Growth Frequency quarterly 
                                  END,p.YEAR
                                            ),
                       RECURSIVE_CTE
                       AS (SELECT OC.NA_PROJ_DETAILS_SID,
                                  OC.PERIOD_SID,
                                  OC.INCREASE_PERCENTAGE,
                                  OC.RN,
                                  OC.PRICE_TYPE,
                                  CASE WHEN '''+@ADJUSTMENT_TYPE+'''=''INCREMENTAL'' THEN 
                                      Cast((  ADJUSTMENT ) AS NUMERIC(22, 6))
                                                                ELSE 
                                                                 Cast(( OC.PROJECTION_PRICE + ADJUSTMENT ) AS NUMERIC(22, 6)) END AS PROJECTION_PRICE,
                                                         YEAR
                           FROM   ORDER_CTE OC
                           WHERE  OC.RN = 1
                           UNION ALL
                           SELECT OC.NA_PROJ_DETAILS_SID,
                                  OC.PERIOD_SID,
                                  OC.INCREASE_PERCENTAGE,
                                  OC.RN,
                                  OC.PRICE_TYPE,
                                  CAST(( RC.PROJECTION_PRICE * ( 1 + ( ISNULL(OC.INCREASE_PERCENTAGE, 0) / 100 ) ) ) + ADJUSTMENT AS NUMERIC(22, 6)) AS PROJECTION_PRICE,
                                                         oc.YEAR
                           FROM   ORDER_CTE OC
                                  JOIN RECURSIVE_CTE RC
                                    ON OC.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                       AND OC.PRICE_TYPE = RC.PRICE_TYPE
                                       AND OC.RN = RC.RN + 1)

                  UPDATE MUP
                  SET    MUP.ADJUSTMENT_PRICE = ISNULL(RC.PROJECTION_PRICE, 0)
                  FROM   ' + @FCP_PROJ
                                        + ' MUP
                             join PERIOD p on p.PERIOD_SID = mup.PERIOD_SID
                         INNER JOIN RECURSIVE_CTE RC
                                 ON MUP.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                    AND p.YEAR = RC.year
                                    AND MUP.PRICE_TYPE = RC.PRICE_TYPE
                  OPTION (MAXRECURSION 0)'

                            EXEC Sp_executesql
                              @SQL1
                        END
                      IF EXISTS (SELECT 1
                                 FROM   #ST_NATIONAL_ASSUMPTIONS
                                 WHERE  CPI_COMPOUNDING = 'QUARTERLY'
                                        AND FORECAST_METHODOLOGY = 'GROWTH') -- ANNUAL CPI COMPOUNDING
                        BEGIN
                            SET @SQL1 = ''
                            SET @SQL1 = @SQL1 + ';WITH ORDER_CTE
                       AS (SELECT ap.NA_PROJ_DETAILS_SID,
                                  ap.PERIOD_SID,
                                  ap.PRICE_TYPE,
                                  ISNULL(ADJUSTMENT, 0) AS ADJUSTMENT,
                                  PROJECTION_PRICE PROJECTION_PRICE,
                                  CASE n.FREQUENCY
                                    WHEN ''ANNUAL'' THEN ( n.GROWTH_RATE / 100.0 ) / 4.0
                                    WHEN ''SEMI-ANNUAL'' THEN ( n.GROWTH_RATE / 100.0 ) / 2.0
                                    ELSE ( n.GROWTH_RATE / 100.0 ) -- CPI Compounding Annual or Growth Frequency quarterly 
                                  END INCREASE_PERCENTAGE,
                                  ROW_NUMBER()
                                    OVER (
                                      PARTITION BY ap.NA_PROJ_DETAILS_SID, AP.PRICE_TYPE
                                      ORDER BY p.PERIOD_SID ) AS RN
                           FROM   #ADJUST_PERIOD AP join #ST_NATIONAL_ASSUMPTIONS n on n.price_type = ap.price_type 
                                            join PERIOD p on p.PERIOD_SID = ap.PERIOD_SID 
                                            where n.FORECAST_METHODOLOGY = ''GROWTH'' and n.CPI_COMPOUNDING = ''QUARTERLY'' 
                                            ),
                       RECURSIVE_CTE
                       AS (SELECT OC.NA_PROJ_DETAILS_SID,
                                  OC.PERIOD_SID,
                                  OC.INCREASE_PERCENTAGE,
                                  OC.RN,
                                  OC.PRICE_TYPE,
                                  CASE WHEN '''+@ADJUSTMENT_TYPE+'''=''INCREMENTAL'' THEN 
                                      Cast((  ADJUSTMENT ) AS NUMERIC(22, 6))
                                                                ELSE 
                                                                 Cast(( OC.PROJECTION_PRICE + ADJUSTMENT ) AS NUMERIC(22, 6)) END AS PROJECTION_PRICE
                           FROM   ORDER_CTE OC
                           WHERE  OC.RN = 1
                           UNION ALL
                           SELECT OC.NA_PROJ_DETAILS_SID,
                                  OC.PERIOD_SID,
                                  OC.INCREASE_PERCENTAGE,
                                  OC.RN,
                                  OC.PRICE_TYPE,
                                  CAST(( RC.PROJECTION_PRICE * ( 1 + ( ISNULL(OC.INCREASE_PERCENTAGE, 0) / 100 ) ) ) + ADJUSTMENT AS NUMERIC(22, 6)) AS PROJECTION_PRICE
                           FROM   ORDER_CTE OC
                                  JOIN RECURSIVE_CTE RC
                                    ON OC.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                       AND OC.PRICE_TYPE = RC.PRICE_TYPE
                                       AND OC.RN = RC.RN + 1)

                  UPDATE MUP
                  SET    MUP.ADJUSTMENT_PRICE = ISNULL(RC.PROJECTION_PRICE, 0)
                  FROM    '
                                        + @FCP_PROJ
                                        + ' MUP
                         INNER JOIN RECURSIVE_CTE RC
                                 ON MUP.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                   AND MUP.PERIOD_SID = RC.PERIOD_SID
                                    AND MUP.PRICE_TYPE = RC.PRICE_TYPE
                  OPTION (MAXRECURSION 0)'

                            EXEC Sp_executesql
                              @SQL1
                        END
                  END -- GROWTH FORECSTING METHODOLOGY END
                IF EXISTS (SELECT 1
                           FROM   #ST_NATIONAL_ASSUMPTIONS
                           WHERE  FORECAST_METHODOLOGY = 'ROLLING AVERAGE') -- ROLLING AVERAGE FORECSTING METHODOLOGY START
                  BEGIN
                      ------------------------------------ADDED ON 4th AUGUST 2016--------------------------------------------         
                      IF Object_id('TEMPDB.DBO.#ROLLING_AVERAGE_FCP', 'U') IS NOT NULL
                        DROP TABLE #ROLLING_AVERAGE_FCP;

                      --------------------------------------------------------------------------------------------------------         
                      IF Object_id('TEMPDB.DBO.#na_actuals', 'U') IS NOT NULL
                        DROP TABLE #na_actuals;

                      CREATE TABLE #na_actuals
                        (
                           NA_PROJ_DETAILS_SID INT,
                           PERIOD_SID          SMALLINT,
                           PRICE_TYPE          VARCHAR(100),
                           ACTUAL_PRICE        NUMERIC(22, 6)
                        )

                      SET @SQL1 = ''
                      SET @SQL1 = 'insert into #na_actuals (NA_PROJ_DETAILS_SID,PERIOD_SID,PRICE_TYPE,ACTUAL_PRICE)
                     select NA_PROJ_DETAILS_SID,PERIOD_SID,PRICE_TYPE,ACTUAL_PRICE from '
                                  + @fcp_actuals

                      EXEC Sp_executesql
                        @SQL1;

                      WITH ITEM_PRICING
                           AS (SELECT IP.NA_PROJ_DETAILS_SID,
                                      IP.ACTUAL_PRICE,
                                      IP.PERIOD_SID,
                                      Row_number()
                                        OVER(
                                          PARTITION BY IP.NA_PROJ_DETAILS_SID
                                          ORDER BY IP.PERIOD_SID ASC) AS RN,
                                      s.price_type
                               FROM   #na_actuals IP
                                      INNER JOIN #PERIOD_QUARTER PQ
                                              ON PQ.PERIOD_SID = IP.PERIOD_SID
                                      JOIN #ST_NATIONAL_ASSUMPTIONS s
                                        ON s.PRICE_TYPE = ip.PRICE_TYPE
                                      CROSS apply DBO.Udf_splitstring(s.ROLLING_PERIOD, ',') ud
                               WHERE  ud.token = pq.Q_PERIOD
                                      AND FORECAST_METHODOLOGY = 'ROLLING AVERAGE'
                                      AND s.price_type IN (SELECT token
                                                           FROM   Udf_splitstring(@PRICE_TYPE, ',')))
                      SELECT A.NA_PROJ_DETAILS_SID,
                             a.PRICE_TYPE,
                             Avg(( ( A.ACTUAL_PRICE - B.ACTUAL_PRICE ) / NULLIF(B.ACTUAL_PRICE, 0) ) * 100) AS ROLLING_AVERGARE
                      INTO   #ROLLING_AVERAGE_FCP
                      FROM   ITEM_PRICING A
                             INNER JOIN ITEM_PRICING B -- USED INNER JOIN SINCE WE DON'T NEED THE UNMATCHED DATA 
                                     ON A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID
                                        AND a.PRICE_TYPE = b.PRICE_TYPE
                                        AND A.RN = B.RN + 1
                      GROUP  BY A.NA_PROJ_DETAILS_SID,
                                a.PRICE_TYPE

                      IF EXISTS (SELECT 1
                                 FROM   #ST_NATIONAL_ASSUMPTIONS
                                 WHERE  CPI_COMPOUNDING <> 'QUARTERLY'
                                        AND PRICE_TYPE = 'CPI-U'
                                        AND FORECAST_METHODOLOGY = 'ROLLING AVERAGE')
                        BEGIN
                            SET @SQL1 = ''
                            SET @SQL1 = ';WITH ORDER_CTE
                       AS (SELECT ap.NA_PROJ_DETAILS_SID,
                                  min(ap.PERIOD_SID) PERIOD_SID,
                                  ap.PRICE_TYPE,
                                  ISNULL(min(ADJUSTMENT), 0) AS ADJUSTMENT,
                                  min(PROJECTION_PRICE) PROJECTION_PRICE,
                                  ROLLING_AVERGARE INCREASE_PERCENTAGE,
                                  ROW_NUMBER()
                                    OVER (
                                      PARTITION BY ap.NA_PROJ_DETAILS_SID, AP.PRICE_TYPE
                                      ORDER BY Min(p.PERIOD_SID) ) AS RN,
                                                                year
                           FROM   #ADJUST_PERIOD AP join #ST_NATIONAL_ASSUMPTIONS n on n.price_type = ap.price_type 
                                            join PERIOD p on p.PERIOD_SID = ap.PERIOD_SID 
                                            join #ROLLING_AVERAGE_FCP r on r.NA_PROJ_DETAILS_SID = ap.NA_PROJ_DETAILS_SID and r.PRICE_TYPE = ap.PRICE_TYPE
                                            where n.PRICE_TYPE = ''CPI-U'' and FORECAST_METHODOLOGY = ''ROLLING AVERAGE'' and n.CPI_COMPOUNDING <> ''QUARTERLY''
                                            group by ap.NA_PROJ_DETAILS_SID,ap.PRICE_TYPE, ROLLING_AVERGARE,p.YEAR
                                            ),
                       RECURSIVE_CTE
                       AS (SELECT OC.NA_PROJ_DETAILS_SID,
                                  OC.PERIOD_SID,
                                  OC.INCREASE_PERCENTAGE,
                                  OC.RN,
                                  OC.PRICE_TYPE,
                                   CASE WHEN '''+@ADJUSTMENT_TYPE+'''=''INCREMENTAL'' THEN 
                                      Cast((  ADJUSTMENT ) AS NUMERIC(22, 6))
                                                                ELSE 
                                                                 Cast(( OC.PROJECTION_PRICE + ADJUSTMENT ) AS NUMERIC(22, 6)) END AS PROJECTION_PRICE,
                                                         YEAR
                           FROM   ORDER_CTE OC
                           WHERE  OC.RN = 1
                           UNION ALL
                           SELECT OC.NA_PROJ_DETAILS_SID,
                                  OC.PERIOD_SID,
                                  OC.INCREASE_PERCENTAGE,
                                  OC.RN,
                                  OC.PRICE_TYPE,
                                  CAST(( RC.PROJECTION_PRICE * ( 1 + ( ISNULL(OC.INCREASE_PERCENTAGE, 0) / 100 ) ) ) + ADJUSTMENT AS NUMERIC(22, 6)) AS PROJECTION_PRICE,
                                                         oc.YEAR
                           FROM   ORDER_CTE OC
                                  JOIN RECURSIVE_CTE RC
                                    ON OC.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                       AND OC.PRICE_TYPE = RC.PRICE_TYPE
                                       AND OC.RN = RC.RN + 1)

                  UPDATE MUP
                  SET    MUP.ADJUSTMENT_PRICE = ISNULL(RC.PROJECTION_PRICE, 0)
                  FROM   ' + @FCP_PROJ
                                        + ' MUP
                             join PERIOD p on p.PERIOD_SID = mup.PERIOD_SID
                         INNER JOIN RECURSIVE_CTE RC
                                 ON MUP.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                    AND p.YEAR = RC.year
                                    AND MUP.PRICE_TYPE = RC.PRICE_TYPE
                  OPTION (MAXRECURSION 0)'

                            EXEC Sp_executesql
                              @SQL1
                        END ---- ROLLING AVERAGE FORECSTING METHODOLOGY END
                      IF EXISTS (SELECT 1
                                 FROM   #ST_NATIONAL_ASSUMPTIONS
                                 WHERE  CPI_COMPOUNDING = 'QUARTERLY'
                                      
                                        AND FORECAST_METHODOLOGY = 'ROLLING AVERAGE')
                        BEGIN
                            SET @SQL1 = ''
                            SET @SQL1 = ';WITH ORDER_CTE
                       AS (SELECT ap.NA_PROJ_DETAILS_SID,
                                  ap.PERIOD_SID,
                                  ap.PRICE_TYPE,
                                  ISNULL(ADJUSTMENT, 0) AS ADJUSTMENT,
                                  PROJECTION_PRICE PROJECTION_PRICE,
                                  ROLLING_AVERGARE INCREASE_PERCENTAGE,
                                  ROW_NUMBER()
                                    OVER (
                                      PARTITION BY ap.NA_PROJ_DETAILS_SID, AP.PRICE_TYPE
                                      ORDER BY p.PERIOD_SID ) AS RN
                           FROM #ADJUST_PERIOD AP join #ST_NATIONAL_ASSUMPTIONS n on n.price_type = ap.price_type 
                                            join PERIOD p on p.PERIOD_SID = ap.PERIOD_SID 
                                            join #ROLLING_AVERAGE_FCP r on r.NA_PROJ_DETAILS_SID = ap.NA_PROJ_DETAILS_SID and r.PRICE_TYPE = ap.PRICE_TYPE
                                            where FORECAST_METHODOLOGY = ''ROLLING AVERAGE'' and n.CPI_COMPOUNDING = ''QUARTERLY''
                                            ),
                       RECURSIVE_CTE
                       AS (SELECT OC.NA_PROJ_DETAILS_SID,
                                  OC.PERIOD_SID,
                                  OC.INCREASE_PERCENTAGE,
                                  OC.RN,
                                  OC.PRICE_TYPE,
                                 CASE WHEN '''+@ADJUSTMENT_TYPE+'''=''INCREMENTAL'' THEN 
                                      Cast((  ADJUSTMENT ) AS NUMERIC(22, 6))
                                                                ELSE 
                                                                 Cast(( OC.PROJECTION_PRICE + ADJUSTMENT ) AS NUMERIC(22, 6)) END AS PROJECTION_PRICE
                           FROM   ORDER_CTE OC
                           WHERE  OC.RN = 1
                           UNION ALL
                           SELECT OC.NA_PROJ_DETAILS_SID,
                                  OC.PERIOD_SID,
                                  OC.INCREASE_PERCENTAGE,
                                  OC.RN,
                                  OC.PRICE_TYPE,
                                  CAST(( RC.PROJECTION_PRICE * ( 1 + ( ISNULL(OC.INCREASE_PERCENTAGE, 0) / 100 ) ) ) + ADJUSTMENT AS NUMERIC(22, 6)) AS PROJECTION_PRICE
                           FROM   ORDER_CTE OC
                                  JOIN RECURSIVE_CTE RC
                                    ON OC.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                       AND OC.PRICE_TYPE = RC.PRICE_TYPE
                                       AND OC.RN = RC.RN + 1)

                  UPDATE MUP
                 SET    MUP.ADJUSTMENT_PRICE = ISNULL(RC.PROJECTION_PRICE, 0)
                  FROM   ' + @FCP_PROJ
                                        + ' MUP
                         INNER JOIN RECURSIVE_CTE RC
                                 ON MUP.NA_PROJ_DETAILS_SID = RC.NA_PROJ_DETAILS_SID
                                    AND MUP.PERIOD_SID = RC.PERIOD_SID
                                    AND MUP.PRICE_TYPE = RC.PRICE_TYPE
                  OPTION (MAXRECURSION 0)'

                            EXEC Sp_executesql
                              @SQL1
                        END
                  END

                ---------------------------------------------------------------------- QUARTER WISE NON-FAMP AND FSS ADJUSTMENT END HERE  
                ----------------------------------------------------- CPI-U PERCENTAGE INCREASE ONLY FOR THIRD QUARTER
                           
                IF Object_id('TEMPDB..#THIRD_QUARTER_PRICE') IS NOT NULL
                  TRUNCATE TABLE #THIRD_QUARTER_PRICE
                ELSE
                  CREATE TABLE #THIRD_QUARTER_PRICE
                    (
                       NA_PROJ_DETAILS_SID          INT,
                       PERIOD_SID                   INT,
                       CPI_U_THIRD_QUARTER_PRICE    NUMERIC(22, 6),
                       CPI_INCREASE_PERCENT         NUMERIC(22, 6),
                       NON_FAMP_THIRD_QUARTER_PRICE NUMERIC(22, 6),
                       ADDITIONAL_DISCOUNT          NUMERIC(22, 6),
                       PERIOD_DATE                  DATETIME
                    )

                INSERT INTO #THIRD_QUARTER_PRICE
                            (NA_PROJ_DETAILS_SID,
                             PERIOD_SID,
                             PERIOD_DATE,
                             CPI_U_THIRD_QUARTER_PRICE)
                SELECT NA_PROJ_DETAILS_SID,
                       PERIOD_SID,
                       PQ.PERIOD_DATE,
                       0
                FROM   #PERIOD_QUARTER PQ
                       CROSS JOIN #PROJECTION_DETAILS PD
                WHERE  PERIOD_SID BETWEEN @ACT_PERIOD_START_SID - 6 AND @PROJ_PERIOD_END_SID
                       AND PERIOD_QUARTER = 3

                DECLARE @FCP5 NVARCHAR(MAX)

                SET @FCP5 =Concat(';WITH CTE
                       AS (SELECT ROW_NUMBER()
                                    OVER (
                                      PARTITION BY NA_PROJ_DETAILS_SID, PERIOD_SID
                                      ORDER BY NUM DESC) RN,
                                  NA_PROJ_DETAILS_SID,
                                  PERIOD_SID,
                                  THIRD_QUARTER_PRICE
                           FROM   (SELECT PD.NA_PROJ_DETAILS_SID,
                                          NAA.PERIOD_SID,
                                          NAA.ACTUAL_PRICE AS THIRD_QUARTER_PRICE,
                                          1                AS NUM
                                   FROM   '
                                  + @NATIONAL_ASSUMPTIONS_ACTUALS
                                  + ' NAA
                                          INNER JOIN #PERIOD_QUARTER PQ
                                                  ON NAA.PERIOD_SID = PQ.PERIOD_SID
                                                     AND PQ.[PERIOD_QUARTER] = 3
                                          INNER JOIN #PROJECTION_DETAILS PD
                                                  ON PD.ITEM_MASTER_SID = NAA.ITEM_MASTER_SID
                                   WHERE  NAA.PRICE_TYPE = ''CPI-U''
                                          AND NAA.PERIOD_SID BETWEEN @ACT_PERIOD_START_SID AND @ACT_PERIOD_END_SID
                                   UNION ALL
                                   SELECT NA_PROJ_DETAILS_SID,
                                  PERIOD_SID,
                                  ISNULL(INDEX_VALUE,0),
                                  NUM
                           FROM   (SELECT TOP 1 NA_PROJ_DETAILS_SID,
                                                PERIOD_SID,
                                                INDEX_VALUE,
                                                1 AS NUM
                                   FROM   CPI_INDEX_MASTER CIM
                                          RIGHT JOIN #PERIOD_QUARTER PQ
                                                  ON PQ.PERIOD_QUARTER = DATEPART(QUARTER, CIM.EFFECTIVE_DATE)
                                                     AND PQ.PERIOD_YEAR = DATEPART(YEAR, CIM.EFFECTIVE_DATE)
                                                     AND INDEX_TYPE = ''CPI''
                                          CROSS JOIN #PROJECTION_DETAILS PD
                                   WHERE  PQ.PERIOD_SID BETWEEN ', @ACT_PERIOD_START_SID, ' - 12 AND ', @ACT_PERIOD_START_SID, '
                                          AND PQ.[PERIOD_QUARTER] = 3
                                   ORDER  BY PERIOD_SID DESC) A
                                   UNION ALL
                                   SELECT PD.NA_PROJ_DETAILS_SID,
                                          NAP.PERIOD_SID,
                                          NAP.PROJECTION_PRICE AS THIRD_QUARTER_PRICE,
                                          2                    AS NUM
                                   FROM   '
                                                                                                                                                          + @NATIONAL_ASSUMPTIONS_PROJ
                                                                                                                                                          + ' NAP
                                          INNER JOIN #PERIOD_QUARTER PQ
                                                  ON NAP.PERIOD_SID = PQ.PERIOD_SID
                                                     AND PQ.[PERIOD_QUARTER] = 3
                                          INNER JOIN #PROJECTION_DETAILS PD
                                                  ON PD.ITEM_MASTER_SID = NAP.ITEM_MASTER_SID
                                   WHERE  NAP.PRICE_TYPE = ''CPI-U''
                                          AND NAP.PERIOD_SID BETWEEN @PROJ_PERIOD_START_SID AND @PROJ_PERIOD_END_SID)OU)
                  
                             UPDATE QP
                  SET    QP.CPI_U_THIRD_QUARTER_PRICE = C.THIRD_QUARTER_PRICE
                  FROM   #THIRD_QUARTER_PRICE QP
                         INNER JOIN CTE C
                                 ON QP.NA_PROJ_DETAILS_SID = C.NA_PROJ_DETAILS_SID
                                    AND QP.PERIOD_SID = C.PERIOD_SID
                  WHERE  RN = 1')

                EXECUTE Sp_executesql
                  @FCP5,
                  N'@ACT_PERIOD_END_SID INT , @ACT_PERIOD_START_SID INT ,@PROJ_PERIOD_START_SID INT, @PROJ_PERIOD_END_SID INT',
                  @ACT_PERIOD_END_SID = @ACT_PERIOD_END_SID,
                  @ACT_PERIOD_START_SID = @ACT_PERIOD_START_SID,
                  @PROJ_PERIOD_START_SID = @PROJ_PERIOD_START_SID,
                  @PROJ_PERIOD_END_SID = @PROJ_PERIOD_END_SID;

               WITH CPI_U_INCREASE
                     AS (SELECT Row_number()
                                  OVER(
                                    PARTITION BY NA_PROJ_DETAILS_SID
                                    ORDER BY PERIOD_SID ASC) RN,
                                NA_PROJ_DETAILS_SID,
                                PERIOD_SID,
                                CPI_U_THIRD_QUARTER_PRICE,
                                CPI_INCREASE_PERCENT
                         FROM   #THIRD_QUARTER_PRICE)
                UPDATE CUI
                SET    CUI.CPI_INCREASE_PERCENT = CASE
                                                    WHEN D.CPI_INCREASE_PERCENT < 0 THEN 0
                                                    ELSE D.CPI_INCREASE_PERCENT
                                                  END
                FROM   CPI_U_INCREASE CUI
                       INNER JOIN (SELECT A.NA_PROJ_DETAILS_SID,
                                          A.PERIOD_SID,
                                          A.CPI_U_THIRD_QUARTER_PRICE,
                                          ( ( A.CPI_U_THIRD_QUARTER_PRICE - B.CPI_U_THIRD_QUARTER_PRICE ) / NULLIF(B.CPI_U_THIRD_QUARTER_PRICE, 0) ) * 100 AS CPI_INCREASE_PERCENT
                                   FROM   CPI_U_INCREASE A
                                          LEFT OUTER JOIN CPI_U_INCREASE B
                                                       ON A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID
                                                          AND A.RN = B.RN + 1) D
                               ON CUI.NA_PROJ_DETAILS_SID = D.NA_PROJ_DETAILS_SID
                                  AND CUI.PERIOD_SID = D.PERIOD_SID

                ---------------------------------------------------------------- ADDITIONAL DISCOUNT ONLY FOR THIRD QUARTER
                DECLARE @FCP6 NVARCHAR(MAX) =''

                SET @FCP6 = @FCP6
                            + N';WITH CTE
                       AS (SELECT ROW_NUMBER()
                                    OVER (
                                      PARTITION BY NA_PROJ_DETAILS_SID, PERIOD_SID
                                      ORDER BY NUM DESC) RN,
                                  ITEM_PRICE,
                                  NA_PROJ_DETAILS_SID,
                                  PERIOD_SID
                           FROM   (SELECT PD.NA_PROJ_DETAILS_SID,
                                          NAA.ACTUAL_PRICE AS ITEM_PRICE,
                                          NAA.PERIOD_SID,
                                          1                AS NUM
                                   FROM   '
                            + @NATIONAL_ASSUMPTIONS_ACTUALS
                            + ' NAA
                                          INNER JOIN #PERIOD_QUARTER PQ
                                                  ON NAA.PERIOD_SID = PQ.PERIOD_SID
                                                     AND PQ.[PERIOD_QUARTER] = 3
                                          INNER JOIN #PROJECTION_DETAILS PD
                                                  ON PD.ITEM_MASTER_SID = NAA.ITEM_MASTER_SID
                                   WHERE  NAA.PRICE_TYPE = ''NON-FAMP''
                                          AND NAA.PERIOD_SID BETWEEN @ACT_PERIOD_START_SID AND @ACT_PERIOD_END_SID
                                   UNION ALL
                                   SELECT PD.NA_PROJ_DETAILS_SID,
                                          AVG(ITEM_PRICE),
                                          PQ.PERIOD_SID,
                                          1 AS NUM
                                   FROM   ITEM_MASTER IM
                                          INNER JOIN DBO.ITEM_PRICING IP
                                                  ON IP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                          INNER JOIN #PROJECTION_DETAILS PD
                                                  ON PD.ITEM_MASTER_SID = IP.ITEM_MASTER_SID
                                          INNER JOIN DBO.ITEM_PRICING_QUALIFIER IPQ
                                                  ON IP.ITEM_PRICING_QUALIFIER_SID = IPQ.ITEM_PRICING_QUALIFIER_SID
                                                     AND IPQ.PRICING_QUALIFIER = ''QNON-FAMP''
                                          INNER JOIN #PERIOD_QUARTER PQ
                                                  ON PQ.PERIOD_QUARTER = DATEPART(QUARTER, [START_DATE])
                                                     AND PQ.PERIOD_YEAR = DATEPART(YEAR, [START_DATE])
                                                     AND PQ.PERIOD_SID BETWEEN @ACT_PERIOD_START_SID - 15 AND @ACT_PERIOD_START_SID - 3
                                                    AND PQ.PERIOD_QUARTER = 3
                                   GROUP  BY PD.NA_PROJ_DETAILS_SID,
                                             PQ.PERIOD_SID
                                   UNION ALL
                                   SELECT PD.NA_PROJ_DETAILS_SID,
                                          NAP.ADJUSTMENT_PRICE AS ITEM_PRICE,
                                          NAP.PERIOD_SID,
                                          2                    AS NUM
                                   FROM   '
                            + @FCP_PROJ
                            + ' NAP
                                          INNER JOIN #PERIOD_QUARTER PQ
                                                  ON NAP.PERIOD_SID = PQ.PERIOD_SID
                                                     AND PQ.[PERIOD_QUARTER] = 3
                                          INNER JOIN #PROJECTION_DETAILS PD
                                                  ON PD.NA_PROJ_DETAILS_SID = NAP.NA_PROJ_DETAILS_SID
                                   WHERE  NAP.PRICE_TYPE = ''QNON-FAMP''
                                          AND NAP.PERIOD_SID BETWEEN @PROJ_PERIOD_START_SID AND @PROJ_PERIOD_END_SID)A)
                                                                       
                          UPDATE PPT
                          SET NON_FAMP_THIRD_QUARTER_PRICE = P.ITEM_PRICE
                          FROM #THIRD_QUARTER_PRICE PPT
                          INNER JOIN CTE P ON PPT.NA_PROJ_DETAILS_SID = P.NA_PROJ_DETAILS_SID
                          AND PPT.PERIOD_SID = P.PERIOD_SID
                          WHERE RN = 1'

                EXECUTE Sp_executesql
                  @FCP6,
                  N'@ACT_PERIOD_END_SID INT , @ACT_PERIOD_START_SID INT ,@PROJ_PERIOD_START_SID INT, @PROJ_PERIOD_END_SID INT',
                  @ACT_PERIOD_END_SID = @ACT_PERIOD_END_SID,
                  @ACT_PERIOD_START_SID = @ACT_PERIOD_START_SID,
                  @PROJ_PERIOD_START_SID = @PROJ_PERIOD_START_SID,
                  @PROJ_PERIOD_END_SID = @PROJ_PERIOD_END_SID;

                WITH ADDITIONAL_DISCOUNT
                     AS (SELECT Row_number()
                                  OVER(
                                    PARTITION BY NA_PROJ_DETAILS_SID
                                    ORDER BY PERIOD_SID ASC) RN,
                                NA_PROJ_DETAILS_SID,
                                PERIOD_SID,
                                CPI_INCREASE_PERCENT,
                                NON_FAMP_THIRD_QUARTER_PRICE,
                                ADDITIONAL_DISCOUNT
                         FROM   #THIRD_QUARTER_PRICE)
                UPDATE AD
                SET    AD.ADDITIONAL_DISCOUNT = CASE
                                                  WHEN D.ADDITIONAL_DISCOUNT < 0 THEN 0
                                                  ELSE D.ADDITIONAL_DISCOUNT
                                                END
                FROM   ADDITIONAL_DISCOUNT AD
                       INNER JOIN (SELECT A.NA_PROJ_DETAILS_SID,
                                          A.PERIOD_SID,
                                          A.NON_FAMP_THIRD_QUARTER_PRICE,
                                          A.CPI_INCREASE_PERCENT,
                                          (( A.NON_FAMP_THIRD_QUARTER_PRICE - ( B.NON_FAMP_THIRD_QUARTER_PRICE * ( 1 + A.CPI_INCREASE_PERCENT ) ) )) AS ADDITIONAL_DISCOUNT
                                   FROM   ADDITIONAL_DISCOUNT A
                                          LEFT OUTER JOIN ADDITIONAL_DISCOUNT B
                                                       ON A.NA_PROJ_DETAILS_SID = B.NA_PROJ_DETAILS_SID
                                                          AND A.RN = B.RN + 1) D
                               ON AD.NA_PROJ_DETAILS_SID = D.NA_PROJ_DETAILS_SID
                                  AND AD.PERIOD_SID = D.PERIOD_SID

                IF Object_id('TEMPDB..#QUARTER_PRICE') IS NOT NULL
                  TRUNCATE TABLE #QUARTER_PRICE
                ELSE
                  CREATE TABLE #QUARTER_PRICE
                    (
                       GROSS_TRADE_SALES       NUMERIC(22, 6),
                       GROSS_TRADE_UNITS       NUMERIC(22, 6),
                       PERIOD_SID              INT,
                       ITEM_MASTER_SID         INT,
                       NA_PROJ_DETAILS_SID     INT,
                       PERIOD_DATE             DATETIME,
                       PERCENT_OF_BUSINESS     NUMERIC(22, 6),
                       SALES_WEIGHTED_NON_FAMP NUMERIC(22, 6)
                    )

                ------------- GROSS TRADE SALES FOR PROJECTION = FORECASTING MASTER SUM OF 3 MONTHS AND FOR HISTORICAL = FUNCTION BASED ON GL_BALANCE_MASTER
                DECLARE @ITEMID [DBO].[UDT_ITEM]
                DECLARE @FROM_DATE DATETIME
                DECLARE @TO_DATE DATETIME

                --INSERT INTO @ITEM_ID
                --            (ITEMID)
                --SELECT ITEM_ID
                --FROM   ITEM_MASTER IM
                --WHERE  EXISTS (SELECT 1
                --               FROM   #PROJECTION_DETAILS PD
                --               WHERE  PD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID)
                SET @TO_DATE=@PROJECTION_END_DATE

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
                       AND FT.BUSINESS_UNIT = @BUSINESS_UNIT
                       AND FT.COMPANY = @GL_COMPANY_ID
                ORDER  BY FT.FROM_PERIOD DESC

                INSERT INTO @ITEMID
                SELECT item_master_sid
                FROM   ITEM_MASTER IM
                WHERE  EXISTS (SELECT 1
                              FROM   #PROJECTION_DETAILS PD
                               WHERE  PD.ITEM_MASTER_SID = IM.ITEM_MASTER_SID)

                SELECT @FROM_DATE = CASE
                                      WHEN Datepart(QQ, @PROJECTION_START_DATE) IN ( 1, 2, 3 ) THEN CONVERT(DATE, CONVERT(VARCHAR(30), Year(@PROJECTION_START_DATE) - 2)
                                                                                                                  + '-10-01')
                                      ELSE @PROJECTION_START_DATE
                                    END

                DECLARE @START_PERIOD_SID INT = (SELECT PERIOD_SID
                   FROM   PERIOD
                   WHERE  MONTH = Month(@FROM_DATE)
                          AND YEAR = Year(@FROM_DATE))
                DECLARE @END_PERIOD_SID INT = (SELECT PERIOD_SID
                   FROM   PERIOD
                   WHERE  MONTH = Month(@TO_DATE)
                          AND YEAR = Year(@TO_DATE));

                WITH GTS
                     AS (SELECT Sum(FORECAST_GTS_SALES) AS FORECAST_GTS,
                                Sum(FORECAST_GTS_UNITS) AS FORECAST_UNITS,
                                Sum(ACTUAL_GTS_SALES)   AS ACTUAL_GTS,
                                Sum(ACTUAL_GTS_UNITS)   AS ACTUAL_UNITS,
                                [QUARTER],
                                [YEAR],
                                ITEM_MASTER_SID
                         FROM   [DBO].[Udf_gts_wac] (@ITEMID, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME, @FORECAST_VERSION) G
                                JOIN PERIOD P
                                  ON P.PERIOD_SID = G.PERIOD_SID
                         GROUP  BY ITEM_MASTER_SID,
                                   [QUARTER],
                                   [YEAR])
                INSERT INTO #QUARTER_PRICE
                            (ITEM_MASTER_SID,
                             NA_PROJ_DETAILS_SID,
                             PERIOD_SID,
                             GROSS_TRADE_SALES,
                             GROSS_TRADE_UNITS,
                             PERIOD_DATE)
                SELECT C.ITEM_MASTER_SID,
                       C.NA_PROJ_DETAILS_SID,
                       C.PERIOD_SID,
                       COALESCE(FORECAST_GTS, ACTUAL_GTS, 0) AS GROSS_TRADE_SALES,
                       COALESCE(FORECAST_UNITS, ACTUAL_UNITS, 0),
                       C.PERIOD_DATE
                FROM   GTS GP
                       RIGHT JOIN (SELECT ITEM_MASTER_SID,
                                          NA_PROJ_DETAILS_SID,
                                          PERIOD_QUARTER,
                                          PERIOD_YEAR,
                                          PERIOD_DATE,
                                          PERIOD_SID
                                   FROM   #PROJECTION_DETAILS
                                          CROSS JOIN #PERIOD_QUARTER
                                   WHERE  PERIOD_DATE BETWEEN @FROM_DATE AND @TO_DATE) C
                               ON GP.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                                  AND GP.[QUARTER] = C.PERIOD_QUARTER
                                  AND GP.[YEAR] = C.PERIOD_YEAR

                ------------------------------------- % OF BUSINESS FOR Q1 2015 = GROSS TRADE SALES OF Q1 2015/SUM(GROSS TRADE SALES OF Q4 2014,Q1 2015,Q2 2015,Q3 2015)
                DECLARE @GTS_START_PERIOD_DATE DATETIME

                SELECT @GTS_START_PERIOD_DATE = CASE
                                                  WHEN Datepart(QQ, @PROJECTION_START_DATE) IN ( 1, 2, 3 ) THEN CONVERT(DATE, CONVERT(VARCHAR(30), Year(@PROJECTION_START_DATE) - 2)
                                                                                                                              + '-10-01')
                                                  ELSE @PROJECTION_START_DATE
                                                END;

                WITH PERCENT_BUSINESS
                     AS (SELECT ( ( Row_number()
                                      OVER (
                                        PARTITION BY PD.NA_PROJ_DETAILS_SID
                                        ORDER BY GTS.PERIOD_SID) - 1 ) / 4 ) + 1 RN,
                                PD.NA_PROJ_DETAILS_SID,
                                GTS.PERIOD_SID,
                                GTS.GROSS_TRADE_SALES,
                                GTS.PERIOD_DATE
                         FROM   #QUARTER_PRICE GTS
                                INNER JOIN #PROJECTION_DETAILS PD
                                        ON PD.ITEM_MASTER_SID = GTS.ITEM_MASTER_SID
                         WHERE  GTS.PERIOD_DATE BETWEEN @GTS_START_PERIOD_DATE AND @PROJECTION_END_DATE),
                     PER_BUSINESS
                     AS (SELECT NA_PROJ_DETAILS_SID,
                                ( GROSS_TRADE_SALES / NULLIF(Sum(GROSS_TRADE_SALES)
                                                               OVER(
                                                                 PARTITION BY RN), 0) ) * 100 AS PERCENT_OF_BUSINESS,
                                PERIOD_SID
                         FROM   PERCENT_BUSINESS)
                UPDATE GTS
                SET    GTS.PERCENT_OF_BUSINESS = T.PERCENT_OF_BUSINESS
                FROM   #QUARTER_PRICE GTS
                       JOIN PER_BUSINESS T
                         ON GTS.PERIOD_SID = T.PERIOD_SID

                ------------------------- SALES WEIGHTED NON-FAMP FOR EACH QUARTER = NON-FAMP OF EACH QUARTER * % OF BUSINESS--------------------------------
                                  DECLARE @SQL_1 NVARCHAR(MAX)
              SET @SQL_1 =  'UPDATE GTS
                SET    GTS.SALES_WEIGHTED_NON_FAMP = IP.ADJUSTMENT_PRICE * ( GTS.PERCENT_OF_BUSINESS / 100.0 )
                FROM   #QUARTER_PRICE GTS
                       INNER JOIN  ' + @FCP_PROJ 
                            + ' IP
                               ON GTS.PERIOD_SID = IP.PERIOD_SID
                                  AND GTS.NA_PROJ_DETAILS_SID = IP.NA_PROJ_DETAILS_SID
                WHERE  IP.PRICE_TYPE = ''QNON-FAMP'''
                           
                     
                           EXEC SP_EXECUTESQL @SQL_1

                     
                           SET @SQL_1 =  'UPDATE GTS
                SET    GTS.SALES_WEIGHTED_NON_FAMP = IP.ACTUAL_PRICE * ( GTS.PERCENT_OF_BUSINESS / 100.0 )
                FROM   #QUARTER_PRICE GTS
                       INNER JOIN  ' + @FCP_ACTUALS 
                            + ' IP
                               ON GTS.PERIOD_SID = IP.PERIOD_SID
                                  AND GTS.NA_PROJ_DETAILS_SID = IP.NA_PROJ_DETAILS_SID
                WHERE  IP.PRICE_TYPE = ''QNON-FAMP''
                           AND GTS.PERIOD_SID <(SELECT MIN(PERIOD_SID) FROM ' + @FCP_PROJ 
                            + '  WHERE PRICE_TYPE = ''QNON-FAMP'' GROUP BY NA_PROJ_DETAILS_SID) '
                                                EXEC SP_EXECUTESQL @SQL_1

                     
                ---------------------------------------------------  YEAR WISE NON-FAMP FOR 2014 = (SUM OF SALES WEIGHTED NON-FAMP OF Q4 2012,Q1 2013,Q2 2013,Q3 2013)
                DECLARE @NFAMP_START_PERIOD_DATE DATETIME

                SELECT @NFAMP_START_PERIOD_DATE = CASE
                                                    WHEN Datepart(QQ, @PROJECTION_START_DATE) IN ( 1, 2, 3 ) THEN CONVERT(DATE, CONVERT(VARCHAR(30), Year(@PROJECTION_START_DATE) - 2)
                                                                                                                                + '-10-01')
                                                    ELSE @PROJECTION_START_DATE
                                                  END;

                IF Object_id('TEMPDB..#YEAR_PRICE') IS NOT NULL
                  TRUNCATE TABLE #YEAR_PRICE
                ELSE
                  CREATE TABLE #YEAR_PRICE
                    (
                       NON_FAMP_PRICE      NUMERIC(22, 6),
                       PERIOD_SID          INT,
                       NA_PROJ_DETAILS_SID INT,
                       PERIOD_DATE         DATETIME,
                       CALCULATED_CEILING  NUMERIC(22, 6),
                       FSS_YEAR_PRICE      NUMERIC(22, 6),
                       MAX_FSS_PRICE       NUMERIC(22, 6),
                       FORECAST_CPI_U      NUMERIC(22, 6),
                       FORECAST_FCP        NUMERIC(22, 6),
                       MANDATED_DISCOUNT   NUMERIC(22, 6),
                       WAC_YEAR_PRICE      NUMERIC(22, 6),
                       CPI_URA_YEAR_PRICE  NUMERIC(22, 6)
                    )
                ------------------------------------------------------------------------------- NON-FAMP YEARLY CALCULATION
                ;

                WITH NON_FAMP
                     AS (SELECT ( ( Row_number()
                                      OVER (
                                        PARTITION BY PD.NA_PROJ_DETAILS_SID
                                        ORDER BY GTS.PERIOD_SID) - 1 ) / 4 ) + 1 RN,
                                PD.NA_PROJ_DETAILS_SID,
                                GTS.PERIOD_SID,
                                GTS.SALES_WEIGHTED_NON_FAMP                      AS NF_PRICE,
                                GTS.PERIOD_DATE
                         FROM   #QUARTER_PRICE GTS
                                INNER JOIN #PROJECTION_DETAILS PD
                                        ON PD.ITEM_MASTER_SID = GTS.ITEM_MASTER_SID
                         WHERE  GTS.PERIOD_DATE BETWEEN @NFAMP_START_PERIOD_DATE AND @PROJECTION_END_DATE),
                     N_FAMP
                     AS (SELECT NA_PROJ_DETAILS_SID,
                                PRICE,
                                CONVERT(VARCHAR(4), PERIOD_DATE) + '-01-01' AS PERIOD_DATE,
                                PERIOD_SID
                         FROM   (SELECT DISTINCT NA_PROJ_DETAILS_SID,
                                                 Sum(NF_PRICE)
                                                   OVER(
                                                     PARTITION BY RN)     AS PRICE,
                                                 Max(Year(PERIOD_DATE))
                                                   OVER(
                                                     PARTITION BY RN)     AS [PERIOD_DATE],
                                                 Max(PERIOD_SID)
                                                   OVER(
                                                     PARTITION BY RN) - 6 AS PERIOD_SID
                                 FROM   NON_FAMP) A)
                INSERT INTO #YEAR_PRICE
                            (NON_FAMP_PRICE,
                             PERIOD_SID,
                             NA_PROJ_DETAILS_SID,
                             PERIOD_DATE)
                SELECT PRICE,
                       PERIOD_SID,
                      NA_PROJ_DETAILS_SID,
                       PERIOD_DATE
                FROM   N_FAMP

                ------------------------------------------------------------------------------- WAC YEARLY CALCULATION
                UPDATE YP
                SET    YP.WAC_YEAR_PRICE = Isnull(A.WAC_YEAR_PRICE, 0)
                FROM   #YEAR_PRICE YP
                       INNER JOIN (SELECT (Sum(GROSS_TRADE_SALES) / NULLIF(Sum(GROSS_TRADE_UNITS), 0))/NULLIF(IM.UPPS,0) AS WAC_YEAR_PRICE,
                                          PD.NA_PROJ_DETAILS_SID,
                                          Min(PERIOD_SID)                                            AS PERIOD_SID
                                   FROM   #QUARTER_PRICE GTS
                                          INNER JOIN #PROJECTION_DETAILS PD
                                                  ON GTS.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
												  INNER JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID=PD.ITEM_MASTER_SID
                                   GROUP  BY PD.NA_PROJ_DETAILS_SID,
								   IM.UPPS,
                                             Year(PERIOD_DATE)) A
                               ON A.NA_PROJ_DETAILS_SID = YP.NA_PROJ_DETAILS_SID
                                  AND A.PERIOD_SID = YP.PERIOD_SID

                ------------------------------------------------------------------------------- CPI-URA YEARLY CALCULATION
                UPDATE YP
                SET    YP.CPI_URA_YEAR_PRICE = Isnull(PT.ADDITIONAL_DISCOUNT, 0)
                FROM   #YEAR_PRICE YP
                       LEFT OUTER JOIN #THIRD_QUARTER_PRICE PT
                                    ON Year(YP.PERIOD_DATE) = Year(PT.PERIOD_DATE) + 1
                                       AND YP.NA_PROJ_DETAILS_SID = PT.NA_PROJ_DETAILS_SID
                -------------------------------------------------------------- CALCULATED CEILING
                --UPDATE YP
                --SET    YP.CALCULATED_CEILING = ( YP.NON_FAMP_PRICE * 0.76 ) - PT.ADDITIONAL_DISCOUNT
                --FROM   #YEAR_PRICE YP
                --       LEFT OUTER JOIN #THIRD_QUARTER_PRICE PT
                --                    ON YEAR(YP.PERIOD_DATE) = YEAR(PT.PERIOD_DATE) + 1
                --                       AND YP.NA_PROJ_DETAILS_SID = PT.NA_PROJ_DETAILS_SID
                ----- CEL-338 change request (Previous year NON-FAMP*0.76)-PREVIOUS YEAR THIRD QUARTER ADDITIONAL DISCOUNT
                ;

                WITH cte
                     AS (SELECT YP.NA_PROJ_DETAILS_SID,
                                YP.period_sid,
                                COALESCE(( Lag(YP.NON_FAMP_PRICE)
                                             OVER(
                                               partition BY pt.NA_PROJ_DETAILS_SID
                                               ORDER BY Year(YP.PERIOD_DATE) ) * 0.76 ), 0) - Isnull(Lag(PT.ADDITIONAL_DISCOUNT)
                                                                                                       OVER(
                                                                                                         partition BY pt.NA_PROJ_DETAILS_SID
                                                                                                         ORDER BY Year(PT.PERIOD_DATE) ), 0) AS CALCULATED_CEILING
                         FROM   #YEAR_PRICE YP
                                LEFT OUTER JOIN #THIRD_QUARTER_PRICE PT
                                             ON Year(YP.PERIOD_DATE) = Year(PT.PERIOD_DATE)
                                                AND YP.NA_PROJ_DETAILS_SID = PT.NA_PROJ_DETAILS_SID)
                UPDATE yp
                SET    yp.CALCULATED_CEILING = c.CALCULATED_CEILING
                FROM   #YEAR_PRICE yp
                       JOIN cte c
                         ON yp.NA_PROJ_DETAILS_SID = c.NA_PROJ_DETAILS_SID
                            AND yp.PERIOD_SID = c.PERIOD_SID

----------------------------------------------------------- FSS = AVERAGE(Q4 OF BEFORE PREVIOUS YEAR,E9:G9) FOR EACH YEAR     
--                DECLARE @FCP7 NVARCHAR(MAX)

--                SET @FCP7 = N'
--                            ;WITH FSS_YEAR_PRICE
--                       AS (SELECT PD.NA_PROJ_DETAILS_SID,
--                                  IP.PERIOD_SID,
--                                  IP.ADJUSTMENT_PRICE                             AS PROJECTION_YEAR_PRICE,
--                                  PQ.PERIOD_DATE,

--FSS_YEAR_PRICE = Lag(ip.ADJUSTMENT_PRICE)
--                                OVER(
--                                       partition BY pd.NA_PROJ_DETAILS_SID
--                                       ORDER BY pq.period_year) * ( 1
--                                                                                       + Lag(qp.CPI_INCREASE_PERCENT/100.0)OVER(partition BY pd.NA_PROJ_DETAILS_SID ORDER BY pq.period_year) )

--                           FROM   '
--                            + @FCP_PROJ
--                            + ' IP
--                                  INNER JOIN #PERIOD_QUARTER PQ
--                                          ON IP.PERIOD_SID = PQ.PERIOD_SID
--                                  INNER JOIN #PROJECTION_DETAILS PD
--                                          ON PD.NA_PROJ_DETAILS_SID = IP.NA_PROJ_DETAILS_SID
--                                                                          JOIN #THIRD_QUARTER_PRICE qp
--                                              ON qp.NA_PROJ_DETAILS_SID = pd.NA_PROJ_DETAILS_SID
--                                                     AND Year(qp.PERIOD_DATE) = pq.PERIOD_YEAR

--                           WHERE  IP.PRICE_TYPE = ''QFSS''
--                                  AND PQ.PERIOD_DATE BETWEEN @NFAMP_START_PERIOD_DATE AND @PROJECTION_END_DATE
--                                                       AND PQ.PERIOD_QUARTER = 4
--                                                       )
--            UPDATE YP
--            SET    YP.FSS_YEAR_PRICE = Isnull(T.FSS_YEAR_PRICE, 0)
--            FROM   #YEAR_PRICE YP
--                         JOIN FSS_YEAR_PRICE T
--                                ON Year(YP.PERIOD_DATE) = Year(T.PERIOD_DATE) 
--                                AND YP.NA_PROJ_DETAILS_SID = T.NA_PROJ_DETAILS_SID


                  -------------------------------------------------------- MAXIMUM FSS PRICE (OGA) FOR EACH YEAR = FSS(PREVIOUS YEAR)*(1 + CPI-U PERCENTAGE INCREASE(THIRD QUARTER OF PREVIOUS YEAR))'

                --EXECUTE Sp_executesql
                --  @FCP7,
                --  N' @NFAMP_START_PERIOD_DATE DATETIME ,  @PROJECTION_END_DATE DATETIME',
                --  @NFAMP_START_PERIOD_DATE = @NFAMP_START_PERIOD_DATE,
                --  @PROJECTION_END_DATE = @PROJECTION_END_DATE
                           
;WITH FSS_YEAR_PRICE
                           AS (SELECT PD.NA_PROJ_DETAILS_SID,
                                         PQ.PERIOD_DATE,
                                         PQ.PERIOD_YEAR,
                                         rn1 as id,
                                         (FS.ADJUSTMENT)
                                                                           * ( 1
                                                                                                                                  +isnull( lag(QP.CPI_INCREASE_PERCENT/100.0) OVER(PARTITION BY qp.NA_PROJ_DETAILS_SID ORDER BY year(QP.PERIOD_DATE)),0) ) 
                                                                                                                                  AS FSS_YEAR_PRICE ,
                                                                                                                                  QP.CPI_INCREASE_PERCENT
                                                                                                                                  
                                  FROM  #PROJECTION_DETAILS PD
                                         JOIN #ADJUSTMENT_PERIOD_PRICE FS ON FS.NA_PROJ_DETAILS_SID=PD.NA_PROJ_DETAILS_SID and FS.rn1=1
                                         JOIN #PERIOD_QUARTER PQ
                                                       ON PQ.PERIOD_SID=FS.PERIOD_SID
                                         RIGHT JOIN #THIRD_QUARTER_PRICE QP
                                                ON QP.NA_PROJ_DETAILS_SID = PD.NA_PROJ_DETAILS_SID
                                                       AND YEAR(QP.PERIOD_DATE) = PQ.PERIOD_YEAR
                                                       
                                                        UNION ALL

                                         SELECT PD.NA_PROJ_DETAILS_SID,
                                         PQ.PERIOD_DATE,
                                         PQ.PERIOD_YEAR,
                                         FS.rn1,
                                         PD.FSS_YEAR_PRICE* ( 1+ isnull((PD.CPI_INCREASE_PERCENT/100.0),0)),
                                         QP.CPI_INCREASE_PERCENT
                                                                                                                           
                                  FROM  FSS_YEAR_PRICE PD
                                         JOIN #ADJUSTMENT_PERIOD_PRICE FS ON FS.NA_PROJ_DETAILS_SID=PD.NA_PROJ_DETAILS_SID
                                         JOIN #PERIOD_QUARTER PQ
                                                       ON PQ.PERIOD_SID=FS.PERIOD_SID
                                         JOIN #THIRD_QUARTER_PRICE qp
                                                ON qp.NA_PROJ_DETAILS_SID = pd.NA_PROJ_DETAILS_SID
                                                       AND Year(qp.PERIOD_DATE) = pq.PERIOD_YEAR
                                                       WHERE PD.ID+1=FS.rn1  AND PD.ID<>0 AND PD.PERIOD_YEAR+1=PQ.PERIOD_YEAR

                                         )

       UPDATE YP
              SET    YP.FSS_YEAR_PRICE = Isnull(T.FSS_YEAR_PRICE, 0)
              FROM   #YEAR_PRICE YP
                           JOIN FSS_YEAR_PRICE T
                                  ON Year(YP.PERIOD_DATE) = Year(T.PERIOD_DATE) 
                                  AND YP.NA_PROJ_DETAILS_SID = T.NA_PROJ_DETAILS_SID


                UPDATE YP
                SET    YP.MAX_FSS_PRICE = A.MAX_FSS_PRICE
                FROM   (SELECT YP.FSS_YEAR_PRICE * ( 1 + PT.CPI_INCREASE_PERCENT )AS MAX_FSS_PRICE,
                               Year(YP.PERIOD_DATE)                               AS [YEAR]
                        FROM   #YEAR_PRICE YP
                               LEFT OUTER JOIN #THIRD_QUARTER_PRICE PT
                                            ON Year(YP.PERIOD_DATE) = Year(PT.PERIOD_DATE)
                                               AND YP.NA_PROJ_DETAILS_SID = PT.NA_PROJ_DETAILS_SID) A
                       LEFT OUTER JOIN #YEAR_PRICE YP
                                    ON Year(YP.PERIOD_DATE) = A.[YEAR] + 1



                --UPDATE YP
                --SET    YP.MAX_FSS_PRICE = A.MAX_FSS_PRICE
                --FROM   (SELECT YP.FSS_YEAR_PRICE * ( 1 + PT.CPI_INCREASE_PERCENT )AS MAX_FSS_PRICE,
                --               Year(YP.PERIOD_DATE)                               AS [YEAR]
                --        FROM   #YEAR_PRICE YP
                --               LEFT OUTER JOIN #THIRD_QUARTER_PRICE PT
                --                            ON Year(YP.PERIOD_DATE) = Year(PT.PERIOD_DATE)
                --                               AND YP.NA_PROJ_DETAILS_SID = PT.NA_PROJ_DETAILS_SID) A
                --       LEFT OUTER JOIN #YEAR_PRICE YP
                --                    ON Year(YP.PERIOD_DATE) = A.[YEAR] + 1

                                  UPDATE YP
SET YP.MAX_FSS_PRICE = FSS_YEAR_PRICE
FROM  #YEAR_PRICE YP 

                    
                --------------------------------------------------------- FORECAST FCP UPDATED IN FORECAST TABLE
                --MIN(MAX FSS,CALCULATED CEILING)
                UPDATE YP
                SET    YP.FORECAST_FCP = CASE
                                           WHEN YP.MAX_FSS_PRICE < YP.CALCULATED_CEILING THEN YP.MAX_FSS_PRICE
                                           ELSE YP.CALCULATED_CEILING
                                         END
                FROM   #YEAR_PRICE YP

                ---------------------------------------------------------------------FORECAST CPI-U YEAR WISE (NEDD TO CLARIFY)--------------------------------------------------------------------
                UPDATE YP
                SET    YP.FORECAST_CPI_U = PT.ADDITIONAL_DISCOUNT
                FROM   #YEAR_PRICE YP
                       LEFT OUTER JOIN #THIRD_QUARTER_PRICE PT
                                    ON Year(YP.PERIOD_DATE) = Year(PT.PERIOD_DATE) + 1
                                       AND YP.NA_PROJ_DETAILS_SID = PT.NA_PROJ_DETAILS_SID

                ----------------------------------------------------------------------------------- MANDATED DISCOUNT----------------------------------------------------------------------------
                UPDATE YP
                SET    YP.MANDATED_DISCOUNT = YP.NON_FAMP_PRICE - YP.FORECAST_FCP
                FROM   #YEAR_PRICE YP

                               UPDATE YP
          SET    YP.FORECAST_FCP =  CASE
                                     WHEN YP.MAX_FSS_PRICE < ISNULL(YP.CALCULATED_CEILING,0) THEN YP.MAX_FSS_PRICE
                                     ELSE YP.CALCULATED_CEILING
                                   END
          FROM 
                 #YEAR_PRICE YP
              

                --------------------------------------------------------------------- UPDATE YEAR WISE DATA IN PROJECTION TABLE------------------------------------------------------------------
                DECLARE @FCP8 NVARCHAR(MAX)

                SET @FCP8 = N' UPDATE FP
                  SET    FP.ADJUSTMENT_PRICE = FORECAST_YEAR_PRICE
                  FROM   ' + @FCP_PROJ
                            + ' FP
                         INNER JOIN (SELECT YP.NA_PROJ_DETAILS_SID,
                                            PERIOD_SID,
                                            FORECAST_YEAR_PRICE,
                                            PRICE_TYPE
                                     FROM   #YEAR_PRICE YP
                                            CROSS APPLY (VALUES (NON_FAMP_PRICE,
                                                        ''ANON-FAMP''),
                                                                (FORECAST_CPI_U,
                                                        ''CPI-U''),
                                                                (FORECAST_FCP,
                                                        ''FCP'' ),
                                                                (MANDATED_DISCOUNT,
                                                       ''TRI-CARE''),
                                                                (WAC_YEAR_PRICE,
                                                        ''AVGY''),
                                                                (CPI_URA_YEAR_PRICE,
                                                        ''CPI-URA''),
                                                                (FSS_YEAR_PRICE,
                                                        ''AFSS''),
                                                        (MAX_FSS_PRICE,
                                                ''MAX FSS'' ) ) CS (FORECAST_YEAR_PRICE, PRICE_TYPE)) A
                                 ON FP.NA_PROJ_DETAILS_SID = A.NA_PROJ_DETAILS_SID
                                    AND FP.PRICE_TYPE = A.PRICE_TYPE
                                    AND FP.PERIOD_SID = A.PERIOD_SID'

                EXEC Sp_executesql
                  @FCP8
                             SET @FCP8 = 'UPDATE B SET ADJUSTMENT_PRICE=C.ADJUSTMENT_PRICE
FROM 

'+@FCP_PROJ+' B
JOIN (
SELECT NA_PROJ_DETAILS_SID,ADJUSTMENT_PRICE,YEAR FROM '+@FCP_PROJ+' F JOIN PERIOD P ON P.PERIOD_SID=F.PERIOD_SID WHERE PRICE_TYPE=''AFSS'' 
)C
ON 
C.NA_PROJ_DETAILS_SID=B.NA_PROJ_DETAILS_SID
JOIN PERIOD P
ON P.PERIOD_SID=B.PERIOD_SID   AND P.YEAR=C.YEAR
WHERE B.PRICE_TYPE=''QFSS'' AND B.ADJUSTMENT IS NULL'
EXEC Sp_executesql
                  @FCP8
            END
          ELSE
            BEGIN
                DECLARE @FCP9 NVARCHAR(MAX)

                SET @FCP9 = N' DECLARE @ITEM_MASTER_SID INT = '
                            + Cast (@ITEM_MASTER_SID AS NVARCHAR(100))
                            + '
                             UPDATE MUP
                  SET    ADJUSTMENT_PRICE = PROJECTION_PRICE
                  FROM   ' + @FCP_PROJ
                            + ' MUP
                         JOIN NA_PROJ_DETAILS ND
                           ON MUP.NA_PROJ_DETAILS_SID = ND.NA_PROJ_DETAILS_SID
                  WHERE  EXISTS (SELECT 1
                                 FROM   NA_PROJ_DETAILS NPD
                                 WHERE  MUP.NA_PROJ_DETAILS_SID = NPD.NA_PROJ_DETAILS_SID
                                        AND NPD.ITEM_MASTER_SID = @ITEM_MASTER_SID)
                         AND PRICE_TYPE IN ( ''QNON-FAMP'', ''QFSS'' ) '

                EXEC Sp_executesql
                  @FCP9
            END
                     -----------------------CEL-405
                     DECLARE @FINAL_UPDATE NVARCHAR(MAX)=''
                     SET @FINAL_UPDATE = '
                     UPDATE PROJ SET ADJUSTMENT_PRICE= ADJ.ADJUSTMENT_PRICE
                     FROM 
                     ' + @FCP_PROJ + ' PROJ
                     JOIN 
                     (SELECT A.NA_PROJ_DETAILS_SID,B.PERIOD_SID,CASE WHEN A.PRICE_TYPE=''AFSS'' THEN ''WAC-FSS'' ELSE ''WAC-FCP'' END PRICE_TYPE, CASE WHEN A.ADJUSTMENT_PRICE IS NOT NULL THEN ISNULL(B.PROJECTION_PRICE,0)-ISNULL(A.ADJUSTMENT_PRICE,0) END ADJUSTMENT_PRICE FROM 
                     (SELECT PROJ.NA_PROJ_DETAILS_SID
                     ,PQ.PERIOD_YEAR
                     ,PROJ.PRICE_TYPE
                     ,PROJ.ADJUSTMENT_PRICE FROM ' + @FCP_PROJ + ' PROJ 
                     JOIN #PERIOD_QUARTER PQ ON PQ.PERIOD_SID=PROJ.PERIOD_SID
                     WHERE  PROJ.PRICE_TYPE IN  (''AFSS'' , ''FCP'')) A
                     JOIN 
                     (SELECT PROJ.NA_PROJ_DETAILS_SID
                     ,PQ.PERIOD_YEAR
                     ,PQ.PERIOD_SID
                     ,PROJ.PROJECTION_PRICE FROM ' + @FCP_PROJ + 
                     ' PROJ 
                     JOIN #PERIOD_QUARTER PQ ON PQ.PERIOD_SID=PROJ.PERIOD_SID
                     WHERE  PROJ.PRICE_TYPE = ''WAC'')B 
                     ON A.NA_PROJ_DETAILS_SID=B.NA_PROJ_DETAILS_SID
                     AND A.PERIOD_YEAR=B.PERIOD_YEAR
                     ) ADJ ON ADJ.PERIOD_SID=PROJ.PERIOD_SID
                     AND ADJ.NA_PROJ_DETAILS_SID=PROJ.NA_PROJ_DETAILS_SID
                     AND ADJ.PRICE_TYPE=PROJ.PRICE_TYPE'


                     EXEC SP_EXECUTESQL @FINAL_UPDATE
                     
                     -----------------------CEL-405
      END
         


