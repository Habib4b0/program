IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_SALES_PROJECTION_TEMP'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_SALES_PROJECTION_TEMP]
  END

GO


CREATE  PROCEDURE [dbo].[PRC_SALES_PROJECTION_TEMP] (@PROJECTION                INT,
                                                   @USER_ID                   INT,
                                                   @SESSION_ID                VARCHAR(50),
                                                   @FREQUENCY                 CHAR(1),
                                                   @FORECAST_START_PERIOD_SID INT,
                                                   @FORECAST_END_PERIOD_SID   INT,
                                                   @CALCULATION_BASED         VARCHAR(50),
                                                   @ALLOCATION_BASIS          VARCHAR(50),
												   @SALES_INCLUSION        BIT=null)
AS
  BEGIN
      SET NOCOUNT ON

   DECLARE @ACTUAL_START_DATE   DATETIME,
              @COMPANY_MASTER_SID  INT,
              @PROJ_END_DATE       DATETIME,
              @PROJ_START_DATE     DATETIME,
              @FORECASTING_TYPE    VARCHAR(100),
              @PERIOD_COUNT        NUMERIC(38, 15),
              @CUSTOMER_FILE_NAME  VARCHAR(50),
              @CUSTOMER_VERSION    VARCHAR(15),
              @MASTER_TABLE        VARCHAR(100),
              @ACTUAL_TABLE        VARCHAR(100),
              @PROJECTION_TABLE    VARCHAR(100),
              @CCP_HIERARCHY       VARCHAR(200),
              @BUSINESS_UNIT       INT,
              @GROWTH_FACTOR_TABLE VARCHAR(100),
              @PRODUCT_FILE_TABLE  VARCHAR(100)

      IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
        DROP TABLE #PERIOD;

      SELECT PERIOD_SID,
             PERIOD_DATE,
             MONTH       AS PERIOD_MONTH,
             QUARTER     AS PERIOD_QUARTER,
             SEMI_ANNUAL AS PERIOD_SEMI,
             YEAR        AS PERIOD_YEAR,
             CASE
               WHEN @FREQUENCY = 'M' THEN Concat ('M', MONTH, ' ', YEAR)
               WHEN @FREQUENCY = 'Q' THEN Concat ('Q', QUARTER, ' ', YEAR)
               WHEN @FREQUENCY = 'S' THEN Concat ('S', SEMI_ANNUAL, ' ', YEAR)
               ELSE Cast(YEAR AS CHAR(4))
             END         AS PERIOD,
             CASE
               WHEN @FREQUENCY = 'M' THEN MONTH
               WHEN @FREQUENCY = 'Q' THEN QUARTER
               WHEN @FREQUENCY = 'S' THEN SEMI_ANNUAL
               ELSE YEAR
             END         AS ROLL_PERIOD
      INTO   #PERIOD
      FROM   PERIOD

      IF Object_id('TEMPDB.DBO.#RESULT', 'U') IS NOT NULL
        DROP TABLE #RESULT;

      CREATE TABLE #RESULT
        (
           CCP_DETAILS_SID         INT,
           PERIOD_SID              INT,
           MONTHLY_PROJECTED_SALES NUMERIC(38, 15),
           MONTHLY_PROJECTED_UNITS NUMERIC(38, 15)
        )

      SELECT @ACTUAL_START_DATE = Dateadd(YY, Datediff(YY, 0, Getdate()) - 3, 0),-- FIRST DAY OF -3 YEARS
             @PROJ_START_DATE = Dateadd(DAY, 1, Eomonth(FROM_DATE, -1)),--FIRST DAY OF THE month -- galuat-29
             @PROJ_END_DATE = Dateadd(DAY, 1, Eomonth(TO_DATE, -1)),--FIRST DAY OF month-- galuat-29
             @COMPANY_MASTER_SID = COMPANY_MASTER_SID,
             @FORECASTING_TYPE = FORECASTING_TYPE,
             @BUSINESS_UNIT = BUSINESS_UNIT
      FROM   PROJECTION_MASTER
      WHERE  PROJECTION_MASTER_SID = @PROJECTION

      DECLARE @ACTUAL_PERIOD_SID   INT,
              @PROJ_END_PERIOD_SID INT,
              @SINGLE_METHOD       VARCHAR(50) = 'SINGLE PERIOD',
              @AVERAGE_METHOD      VARCHAR(50) = 'AVERAGE',
              @XFACTORY_METHOD     VARCHAR(50) = '% OF EX-FACTORY SALES',
              @DEMAND_METHOD       VARCHAR(50)='% OF DEMAND',
              @ROLLING_ANNUAL      VARCHAR(50) = 'ROLLING ANNUAL TREND',
              @INVENTORY_METHOD    VARCHAR(50)='% OF INVENTORY WITHDRAWAL',
              @CUSTOMER_GTS_SALES  VARCHAR(50)='CUSTOMER GTS',
              @ADJUSTED_DEMAND     VARCHAR(50) = '% OF ADJUSTED DEMAND',
			   @SEASONAL_TREND     VARCHAR(50) = '% OF EX-FACTORY - SEASONAL TREND'

      SELECT @ACTUAL_PERIOD_SID = Max(CASE
                                        WHEN @ACTUAL_START_DATE = PERIOD_DATE THEN PERIOD_SID
                                      END),
             @PROJ_END_PERIOD_SID = Max(CASE
                                          WHEN @PROJ_END_DATE = PERIOD_DATE THEN PERIOD_SID
                                        END)
      FROM   PERIOD
      WHERE  PERIOD_DATE IN ( @ACTUAL_START_DATE, @PROJ_START_DATE, @PROJ_END_DATE )

      SELECT @ACTUAL_TABLE = CASE
                               WHEN @FORECASTING_TYPE IN( 'GOVERNMENT', 'MANDATED' ) THEN Concat('ST_M_ACTUAL_SALES_', @user_id, '_', @session_id, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                               WHEN @FORECASTING_TYPE IN ( 'COMMERCIAL', 'NON MANDATED' ) THEN Concat('ST_NM_ACTUAL_SALES_', @user_id, '_', @session_id, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                             END,
             @PROJECTION_TABLE = CASE
                                   WHEN @FORECASTING_TYPE IN( 'GOVERNMENT', 'MANDATED' ) THEN Concat('ST_M_SALES_PROJECTION_', @user_id, '_', @session_id, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                                   WHEN @FORECASTING_TYPE IN ( 'COMMERCIAL', 'NON MANDATED' ) THEN Concat('ST_NM_SALES_PROJECTION_', @user_id, '_', @session_id, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                                 END,
             @MASTER_TABLE = CASE
                               WHEN @FORECASTING_TYPE IN( 'GOVERNMENT', 'MANDATED' ) THEN Concat('ST_M_SALES_PROJECTION_MASTER_', @user_id, '_', @session_id, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                               WHEN @FORECASTING_TYPE IN ( 'COMMERCIAL', 'NON MANDATED' ) THEN Concat('ST_NM_SALES_PROJECTION_MASTER_', @user_id, '_', @session_id, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
                             END,
             @GROWTH_FACTOR_TABLE = Concat('ST_SALES_GROWTH_FACTOR_', @user_id, '_', @session_id, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
             @CCP_HIERARCHY = Concat('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
             @PRODUCT_FILE_TABLE = Concat('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

			  set @FORECAST_END_PERIOD_SID=isnull(@FORECAST_END_PERIOD_SID,@PROJ_END_PERIOD_SID)
			  				DECLARE @ACTUAL_PERIOD INT
		SET @ACTUAL_PERIOD=(SELECT PERIOD_SID
                            FROM   PERIOD
                            WHERE  PERIOD_DATE = 
 CONVERT(DATETIME,DATEADD(MM,-1,DATEADD(DD,1,EOMONTH(GETDATE(), 0)))))

      IF Object_id('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL
        DROP TABLE #CCP_DETAILS_TEMP

      CREATE TABLE #CCP_DETAILS_TEMP
        (
           CCP_DETAILS_SID            INT,
           COMPANY_MASTER_SID         INT,
           ITEM_MASTER_SID            INT,
           CONTRACT_MASTER_SID        INT,
           METHODOLOGY                VARCHAR(50),
           CALCULATION_BASED          VARCHAR(10),
           CALCULATION_PERIODS        VARCHAR(4000),
           FORECAST_START_PERIOD_SID  INT,
           FORECAST_END_PERIOD_SID    INT,
           ALLOCATION_BASIS           VARCHAR(100),
           CALC_START_PERIOD_SID      INT,
           CALC_END_PERIOD_SID        INT,
           FREQ_CAL_START_PERIOD_SID  INT,
           FREQ_CAL_END_PERIOD_SID    INT,
           EFFECTIVE_START_PERIOD_SID INT,
           EFFECTIVE_END_PERIOD_SID   INT
        )

      DECLARE @D_SQL NVARCHAR(MAX)

      SET @D_SQL = concat('INSERT INTO #CCP_DETAILS_TEMP(CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID,METHODOLOGY,
                   CALCULATION_BASED,
                   CALCULATION_PERIODS,FORECAST_START_PERIOD_SID,FORECAST_END_PERIOD_SID,ALLOCATION_BASIS,CALC_START_PERIOD_SID,CALC_END_PERIOD_SID,FREQ_CAL_START_PERIOD_SID,
				   FREQ_CAL_END_PERIOD_SID,EFFECTIVE_START_PERIOD_SID,EFFECTIVE_END_PERIOD_SID)
				SELECT CH.CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID,METHODOLOGY,
                   '''+@CALCULATION_BASED+''',
                   CALCULATION_PERIODS,',@FORECAST_START_PERIOD_SID,',',@FORECAST_END_PERIOD_SID,',''',@ALLOCATION_BASIS,''',

				CALC_START_PERIOD_SID = CASE
                    WHEN METHODOLOGY IN ( @XFACTORY_METHOD, @INVENTORY_METHOD, @DEMAND_METHOD, @CUSTOMER_GTS_SALES, @ADJUSTED_DEMAND,@SEASONAL_TREND ) THEN Iif(',@FORECAST_START_PERIOD_SID,' > EFFECTIVE_START_PERIOD_SID, ',@FORECAST_START_PERIOD_SID,', EFFECTIVE_START_PERIOD_SID)
                 ELSE Iif(',@FORECAST_START_PERIOD_SID,' > EFFECTIVE_START_PERIOD_SID, Iif(',@FORECAST_START_PERIOD_SID,' > CS.MAX_BASELINE_PERIOD, ',@FORECAST_START_PERIOD_SID,', CS.MAX_BASELINE_PERIOD), Iif(EFFECTIVE_START_PERIOD_SID > CS.MAX_BASELINE_PERIOD, EFFECTIVE_START_PERIOD_SID, CS.MAX_BASELINE_PERIOD))
             END,
       CALC_END_PERIOD_SID = Iif(COALESCE(',@FORECAST_END_PERIOD_SID,', @PROJ_END_PERIOD_SID) < EFFECTIVE_END_PERIOD_SID, COALESCE(',@FORECAST_END_PERIOD_SID,', @PROJ_END_PERIOD_SID), EFFECTIVE_END_PERIOD_SID)
	   ,FREQ_CAL_START_PERIOD_SID,FREQ_CAL_END_PERIOD_SID,EFFECTIVE_START_PERIOD_SID,EFFECTIVE_END_PERIOD_SID
				   
				    FROM ' + @CCP_HIERARCHY
                   + ' CH 
						  JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID 
						  JOIN ' + @MASTER_TABLE + ' NSPM
               ON CD.CCP_DETAILS_SID = NSPM.CCP_DETAILS_SID
		AND	    FILTER_CCP=1  ',case when @SALES_INCLUSION is not null then concat('ANd SALES_INCLUSION =',@SALES_INCLUSION) END,' 
			  AND NSPM.CHECK_RECORD=1 
			  --AND (EFFECTIVE_START_PERIOD_SID IS NOT NULL OR EFFECTIVE_END_PERIOD_SID IS NOT NULL)
			         OUTER APPLY (SELECT TOP 1 P.PERIOD_SID + 1 AS MAX_BASELINE_PERIOD
                    FROM   Udf_splitstring(NSPM.CALCULATION_PERIODS, '','') FN
                           JOIN #PERIOD P
                             ON PERIOD = FN.TOKEN
                    ORDER  BY PERIOD_SID DESC) CS 
						  ')

      EXEC Sp_executesql
        @D_SQL,
        N'
@XFACTORY_METHOD       VARCHAR(50)
,@SEASONAL_TREND       VARCHAR(50)
,@DEMAND_METHOD         VARCHAR(50)
,@INVENTORY_METHOD      VARCHAR(50)
,@CUSTOMER_GTS_SALES    VARCHAR(50)
,@ADJUSTED_DEMAND       VARCHAR(50)
,@PROJ_END_PERIOD_SID int
',
        @XFACTORY_METHOD = @XFACTORY_METHOD,
        @DEMAND_METHOD = @DEMAND_METHOD,
		@SEASONAL_TREND=@SEASONAL_TREND,
        @INVENTORY_METHOD = @INVENTORY_METHOD,
        @CUSTOMER_GTS_SALES = @CUSTOMER_GTS_SALES,
        @ADJUSTED_DEMAND = @ADJUSTED_DEMAND,
        @PROJ_END_PERIOD_SID = @PROJ_END_PERIOD_SID

      DECLARE @SQL NVARCHAR(MAX)

      SET @SQL= Concat('
            IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''', @GROWTH_FACTOR_TABLE, '''
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			 
      
           CREATE TABLE ', @GROWTH_FACTOR_TABLE, '
                   (
				   CCP_DETAILS_SID     INT,
				   PERIOD_SID          INT,
				   GROWTH              NUMERIC(22,15)

				   )
				   
				    END')

      EXEC Sp_executesql
        @SQL

      DECLARE @SINGLE_AVERAGE_METHODOLOGY VARCHAR(100),
              @SALES_BASED_UNITS          VARCHAR(100),
              @UNITS_BASED_SALES          VARCHAR(100),
              @MONTHLY_PROJECTED_SALES    VARCHAR(100),
              @MONTHLY_PROJECTED_UNITS    VARCHAR(100)

      SELECT @SINGLE_AVERAGE_METHODOLOGY = Max(CASE
                                                 WHEN FORMULA_NAME = 'SINGLE_AVERAGE_METHODOLOGY' THEN FORMULA
                                               END),
             @SALES_BASED_UNITS = Max(CASE
                                        WHEN FORMULA_NAME = 'SALES_BASED_UNITS' THEN Replace(FORMULA, 'PROJECTED_SALES', 'PROJECTED_SALES_UNITS')
                                      END),
             @UNITS_BASED_SALES = Max(CASE
                                        WHEN FORMULA_NAME = 'UNITS_BASED_SALES' THEN Replace(FORMULA, 'PROJECTED_UNITS', 'PROJECTED_SALES_UNITS')
                                      END),
             @MONTHLY_PROJECTED_SALES = Max(CASE
                                              WHEN FORMULA_NAME = 'MONTHLY_PROJECTED_SALES' THEN FORMULA
                                            END),
             @MONTHLY_PROJECTED_UNITS = Max(CASE
                                              WHEN FORMULA_NAME = 'MONTHLY_PROJECTED_UNITS' THEN FORMULA
                                            END)
      FROM   FORECASTING_FORMULA
      WHERE  FORMULA_NAME IN ( 'WAC_INCR', 'SINGLE_AVERAGE_METHODOLOGY', 'SALES_FACTOR', 'UNITS_FACTOR',
                               'SALES_UNITS_PROJECTION', 'SALES_BASED_UNITS', 'UNITS_BASED_SALES', 'MONTHLY_PROJECTED_SALES', 'MONTHLY_PROJECTED_UNITS' )

      SET @SINGLE_AVERAGE_METHODOLOGY = Replace(@SINGLE_AVERAGE_METHODOLOGY, 'NUMBER_OF_SELECTED_PERIOD', 'NULLIF(NUMBER_OF_SELECTED_PERIOD,0)')
      SET @SALES_BASED_UNITS = Replace(@SALES_BASED_UNITS, 'WAC_PRICE', 'NULLIF(WAC_PRICE,0)')

      -------------ACTUAL_BASELINE CALCULATION START (BASED ON SINGLE OR AVERAGE METHODOLOGY)-------------------- 
      IF Object_id('TEMPDB.DBO.#BASELINE', 'U') IS NOT NULL
        DROP TABLE #BASELINE

      CREATE TABLE #BASELINE
        (
           BASELINE                  NUMERIC(38, 15),
           CCP_DETAILS_SID           INT,
           CALCULATION_BASED         VARCHAR(6),
           NUMBER_OF_SELECTED_PERIOD INT,
           ROLL_PERIOD               INT,
           PERIOD_SID                INT
        )

      SET @PERIOD_COUNT = CASE @FREQUENCY
                            WHEN 'Q' THEN 3
                            WHEN 'S' THEN 6
                            WHEN 'A' THEN 12
                            ELSE 1
                          END

      DECLARE @D_SQL1 NVARCHAR(MAX)

      SET @D_SQL1 = '
	 
	 INSERT INTO #BASELINE
                  (BASELINE,
                   CCP_DETAILS_SID,
                   CALCULATION_BASED,
                   NUMBER_OF_SELECTED_PERIOD,
				  ROLL_PERIOD,
				  PERIOD_SID)
      SELECT '
                    + @SINGLE_AVERAGE_METHODOLOGY
                    + ' AS ACTUAL_BASELINE, 
             CCP_DETAILS_SID,
             CALCULATION_BASED,
             NUMBER_OF_SELECTED_PERIOD,
			ROLL_PERIOD,
			PERIOD_SID
      FROM   (SELECT CASE
                       WHEN CALCULATION_BASED = ''SALES'' THEN ACTUAL_SALES
                       ELSE ACTUAL_UNITS
                     END SELECTED_PERIOD_ACTUALS,
                     CCP_DETAILS_SID,
                     CALCULATION_BASED,
                     NUMBER_OF_SELECTED_PERIOD,
					ROLL_PERIOD,
					
					PERIOD_SID
              FROM   (SELECT T_CCP.CCP_DETAILS_SID,
                             CALCULATION_BASED,
                             ROUND(SUM(ACTUAL_SALES), 5) ACTUAL_SALES,
                             ROUND(SUM(ACTUAL_UNITS), 5) ACTUAL_UNITS,
							NUMBER_OF_SELECTED_PERIOD,
							ROLL_PERIOD=CASE WHEN T_CCP.METHODOLOGY = @ROLLING_ANNUAL  THEN ROLL_PERIOD ELSE 1 END,
							
							MIN(CS.PERIOD_SID) AS PERIOD_SID
                      FROM   #CCP_DETAILS_TEMP T_CCP
                             CROSS APPLY (SELECT PERIOD_SID,
												ROLL_PERIOD,
												
                                                 NUMBER_OF_SELECTED_PERIOD = CASE WHEN (T_CCP.METHODOLOGY = @ROLLING_ANNUAL  OR T_CCP.METHODOLOGY = @XFACTORY_METHOD)  THEN 1 ELSE COUNT(1)
                                                                               OVER() / NULLIF(@PERIOD_COUNT,0) END
                                          FROM   UDF_SPLITSTRING(T_CCP.CALCULATION_PERIODS, '','') FN
                                                 JOIN #PERIOD P
                                                   ON P.PERIOD = FN.TOKEN) CS
                             INNER JOIN '
                    + @ACTUAL_TABLE
                    + ' NAS
                                     ON CS.PERIOD_SID = NAS.PERIOD_SID
                                        AND T_CCP.CCP_DETAILS_SID = NAS.CCP_DETAILS_SID
                      WHERE  T_CCP.METHODOLOGY IN( @SINGLE_PERIOD, @AVERAGE_PERIOD, @ROLLING_ANNUAL,@XFACTORY_METHOD, @INVENTORY_METHOD, @DEMAND_METHOD,@ADJUSTED_DEMAND)
                      GROUP  BY T_CCP.[CCP_DETAILS_SID],
                                CASE WHEN T_CCP.METHODOLOGY = @ROLLING_ANNUAL THEN ROLL_PERIOD ELSE 1 END,
								CALCULATION_BASED,
								
                                NUMBER_OF_SELECTED_PERIOD) A)L'

      EXEC Sp_executesql
        @D_SQL1,
        N'@SINGLE_PERIOD VARCHAR(50),@AVERAGE_PERIOD VARCHAR(50), @ROLLING_ANNUAL VARCHAR(50), @PERIOD_COUNT NUMERIC(22,6)
		,@PROJECTION INT,@XFACTORY_METHOD VARCHAR(50), @INVENTORY_METHOD VARCHAR(50), @DEMAND_METHOD VARCHAR(50),@ADJUSTED_DEMAND VARCHAR(50)',
        @SINGLE_PERIOD = @SINGLE_METHOD,
        @AVERAGE_PERIOD = @AVERAGE_METHOD,
        @ROLLING_ANNUAL = @ROLLING_ANNUAL,
        @PERIOD_COUNT = @PERIOD_COUNT,
        @PROJECTION = @PROJECTION,
        @XFACTORY_METHOD = @XFACTORY_METHOD,
        @INVENTORY_METHOD = @INVENTORY_METHOD,
        @DEMAND_METHOD = @DEMAND_METHOD,
        @ADJUSTED_DEMAND=@ADJUSTED_DEMAND

      IF Object_id('TEMPDB.DBO.#SALES_UNIT_RESULT', 'U') IS NOT NULL
        DROP TABLE #SALES_UNIT_RESULT;

      CREATE TABLE #SALES_UNIT_RESULT
        (
           CCP_DETAILS_SID       INT,
           ITEM_MASTER_SID       INT,
           PERIOD_SID            INT,
           PROJECTED_SALES_UNITS NUMERIC(38, 15),
           BASED_ON              VARCHAR(10),
           PRIMARY KEY (CCP_DETAILS_SID, PERIOD_SID)
        );

				 IF EXISTS (SELECT 1
                 FROM   #CCP_DETAILS_TEMP CCP
                 WHERE  METHODOLOGY IN ( @SEASONAL_TREND ))
        BEGIN

IF Object_id('TEMPDB.DBO.#BASELINE_SEASONAL_TREND_RATIO', 'U') IS NOT NULL
	DROP TABLE #BASELINE_SEASONAL_TREND_RATIO

CREATE TABLE #BASELINE_SEASONAL_TREND_RATIO (
	RATIO NUMERIC(22, 6)
	,CCP_DETAILS_SID INT
	,ROLL_PERIOD INT
	)

DECLARE @D_SQL11 NVARCHAR(MAX)

SET @D_SQL11 = CONCAT (
		'INSERT INTO #BASELINE_SEASONAL_TREND_RATIO (RATIO,CCP_DETAILS_SID,ROLL_PERIOD)
SELECT CASE 
		WHEN '''
		,@CALCULATION_BASED
		,'''  = ''SALES''
			THEN SUM(ACTUAL_SALES) / NULLIF(ISNULL(SUM(case when ppp.period_sid<',@ACTUAL_PERIOD,' then EXFACTORY_ACTUAL_sales  else  EXFACTORY_FORECAST_SALES end ), 0), 0)
		ELSE SUM(ACTUAL_UNITS) / NULLIF(ISNULL(SUM(case when ppp.period_sid<',@ACTUAL_PERIOD,' then EXFACTORY_ACTUAL_UNITS  else  EXFACTORY_FORECAST_UNITS end), 0), 0)
		END RATIO
	,CCP.CCP_DETAILS_SID
	,b.ROLL_PERIOD
FROM ' + @ACTUAL_TABLE + ' A
JOIN #CCP_DETAILS_TEMP CCP ON CCP.CCP_DETAILS_SID = a.CCP_DETAILS_SID
JOIN  ' + @MASTER_TABLE + ' MM ON mm.CCP_DETAILS_SID = a.CCP_DETAILS_SID
and  FILTER_CCP=1  ',case when @SALES_INCLUSION is not null then concat('ANd SALES_INCLUSION =',@SALES_INCLUSION) END,' 

JOIN ' + @PRODUCT_FILE_TABLE + ' PPP ON PPP.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID
	AND A.PERIOD_SID = PPP.PERIOD_SID
CROSS APPLY (SELECT PERIOD_SID,ROLL_PERIOD FROM   UDF_SPLITSTRING(MM.CALCULATION_PERIODS, '','') FN
                                                 JOIN #PERIOD P
                                                   ON P.PERIOD = FN.TOKEN) B join #PERIOD PP on pp.PERIOD_SID=b.PERIOD_SID and a.PERIOD_SID=pp.PERIOD_SID 
GROUP BY CCP.CCP_DETAILS_SID
	,b.ROLL_PERIOD'
		,''
		)

EXEC (@D_SQL11)


DECLARE @D_SQL12 NVARCHAR(MAX)

IF OBJECT_ID('TEMPDB.DBO.#MUTLIPICATION', 'U') IS NOT NULL
	DROP TABLE #MUTLIPICATION;

CREATE TABLE #MUTLIPICATION (
	CCP_DETAILS_SID INT
	,AMOUNT NUMERIC(22, 6)
	,PERIOD_SID INT
	)

SET @D_SQL12 = CONCAT (
		'INSERT #MUTLIPICATION
	(CCP_DETAILS_SID,AMOUNT,PERIOD_SID)
SELECT CCP.CCP_DETAILS_SID
	,RATIO * CASE 
		WHEN  '''
		,@CALCULATION_BASED
		,''' = ''SALES''
			THEN EXFACTORY_FORECAST_SALES
		ELSE EXFACTORY_FORECAST_UNITS
		END AMOUNT
	,P.PERIOD_SID

FROM ' + @PRODUCT_FILE_TABLE + '  A
JOIN #CCP_DETAILS_TEMP CCP ON CCP.ITEM_MASTER_SID = A.ITEM_MASTER_SID
JOIN '
		,@CCP_HIERARCHY
		,' PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
JOIN #BASELINE_SEASONAL_TREND_RATIO RR ON RR.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
JOIN #PERIOD P ON   ', CASE WHEN @FREQUENCY<>'A' THEN 'P.ROLL_PERIOD = RR.ROLL_PERIOD  AND' ELSE ' ' END ,' a.PERIOD_SID=p.PERIOD_SID' 
		,''
		)
	
EXEC (@D_SQL12)


DECLARE @D_SQL13 NVARCHAR(MAX)


SET @D_SQL13 = CONCAT (
		'
;WITH CTE
AS (
	SELECT CDP.CCP_DETAILS_SID
		,P.PERIOD_SID
		,(EXFACTORY_FORECAST_SALES / EXFACTORY_FORECAST_UNITS) WAC_PRICE
	FROM ' + @PRODUCT_FILE_TABLE + ' a
	JOIN #CCP_DETAILS_TEMP CDP ON CDP.ITEM_MASTER_SID = A.ITEM_MASTER_SID
	JOIN #PERIOD P ON P.PERIOD_SID = A.PERIOD_SID 
	AND P.PERIOD_SID BETWEEN CDP.CALC_START_PERIOD_SID AND CDP.CALC_END_PERIOD_SID
	)
	INSERT INTO #RESULT(CCP_DETAILS_SID,PERIOD_SID,MONTHLY_PROJECTED_SALES,MONTHLY_PROJECTED_UNITS)
SELECT CDP.CCP_DETAILS_SID
	,MP.PERIOD_SID,CASE 
		WHEN '''
		,@CALCULATION_BASED
		,''' = ''SALES''
			THEN AMOUNT
		ELSE (AMOUNT * WAC_PRICE)
		END  PROJECTION_SALES,
		CASE 
		WHEN '''
		,@CALCULATION_BASED
		,''' = ''UNITS''
			THEN AMOUNT
		ELSE (AMOUNT / WAC_PRICE) 
		END  PROJECTION_UNITS
FROM CTE CDP
JOIN #MUTLIPICATION MP ON MP.CCP_DETAILS_SID = CDP.CCP_DETAILS_SID
	AND CDP.PERIOD_SID = MP.PERIOD_SID'
		,''
		)

EXEC (@D_SQL13)


END

      IF EXISTS(SELECT 1
                FROM   #CCP_DETAILS_TEMP CCP
                WHERE  METHODOLOGY IN( @SINGLE_METHOD, @AVERAGE_METHOD, @ROLLING_ANNUAL ))
        BEGIN
            ----------------INCREASE FACTORS (WAC, ACCOUNT GROWTH AND PRODUCT GROWTH)START-------------------------
            IF Object_id('TEMPDB.DBO.#SALES_UNIT_RESULT', 'U') IS NOT NULL
              TRUNCATE TABLE #SALES_UNIT_RESULT;

            ----------------INCREASE FACTORS (WAC INCREASE, ACCOUNT GROWTH AND PRODUCT GROWTH) END-------------------------
            -----------------SALES PROJECTION CALCULATION START-----------------------------
            DECLARE @D_SQL3 NVARCHAR(MAX)

            SET @D_SQL3 = '
             ;WITH data 
                 AS (SELECT NSP.CCP_DETAILS_SID,
				            NSPM.ITEM_MASTER_SID,
                            min(NSP.PERIOD_SID) as PERIOD_SID,
							NSPM.CALCULATION_BASED as BASED_ON,
							p.roll_period,
							nspm.methodology 
                     FROM   '
                          + @PROJECTION_TABLE
                          + ' NSP
                            INNER JOIN  #CCP_DETAILS_TEMP NSPM
                                    ON NSP.CCP_DETAILS_SID = NSPM.CCP_DETAILS_SID 
									and nsp.PERIOD_SID between NSPM.FREQ_CAL_START_PERIOD_SID and NSPM.FREQ_CAL_END_PERIOD_SID
									join #period p on p.period_sid = NSP.period_sid 
									WHERE  NSPM.METHODOLOGY IN( @SINGLE_METHOD, @AVERAGE_METHOD,@ROLLING_ANNUAL )
									group by NSP.CCP_DETAILS_SID,NSPM.ITEM_MASTER_SID,NSPM.CALCULATION_BASED,p.period_year,p.period,p.roll_period,nspm.methodology 
                       
                     )
            INSERT INTO #SALES_UNIT_RESULT
                        (CCP_DETAILS_SID,
                         ITEM_MASTER_SID,
                         PERIOD_SID,
                         PROJECTED_SALES_UNITS,
                         BASED_ON)
            SELECT D.CCP_DETAILS_SID,
                   D.ITEM_MASTER_SID,
                   D.PERIOD_SID, 
				   b.BASELINE * ISNULL(gf.GROWTH,1) ,
                   D.BASED_ON
            FROM   data d left join '
                          + @GROWTH_FACTOR_TABLE + ' gf on d.CCP_DETAILS_SID = gf.CCP_DETAILS_SID and d.period_sid = gf.period_sid
                   INNER JOIN #BASELINE B
                           ON D.CCP_DETAILS_SID = B.CCP_DETAILS_SID
						  AND (((d.ROLL_PERIOD = B.ROLL_PERIOD AND @FREQUENCY <> ''A'') OR  @FREQUENCY = ''A'' and d.methodology = @ROLLING_ANNUAL ) or d.methodology <> @ROLLING_ANNUAL)
						   '

            EXEC Sp_executesql
              @D_SQL3,
              N' @SINGLE_METHOD VARCHAR(50), @AVERAGE_METHOD VARCHAR(50),@ROLLING_ANNUAL VARCHAR(50),@FREQUENCY char(1) ',
              @SINGLE_METHOD = @SINGLE_METHOD,
              @AVERAGE_METHOD = @AVERAGE_METHOD,
              @ROLLING_ANNUAL = @ROLLING_ANNUAL,
              @FREQUENCY =@FREQUENCY

            -----------------SALES PROJECTION CALCULATION END-----------------------------
            --------------------------MONTHLY ALLOCATION(EX-FACTORY) START------------------------------
            DECLARE @l_sql NVARCHAR(max)

            SET @l_sql = '
            WITH MONTHLY_PER_BUSINESS
                 AS (SELECT A.ITEM_MASTER_SID,
                            A.PERIOD_SID,
                            MONTHLY_PER_BUSINESS = CASE WHEN ALLOCATION_BASIS = ''% OF EX-FACTORY'' and BASED_ON = ''SALES'' THEN EX_MONTHLY_PER_BUSINESS 
							                    WHEN ALLOCATION_BASIS = ''% OF INVENTORY WITHDRAWAL'' and BASED_ON = ''SALES'' THEN IW_MONTHLY_PER_BUSINESS 
												WHEN ALLOCATION_BASIS = ''% OF DEMAND'' and BASED_ON = ''SALES'' THEN DE_MONTHLY_PER_BUSINESS 
												WHEN ALLOCATION_BASIS = ''% OF EX-FACTORY'' and BASED_ON <> ''SALES'' THEN EX_MONTHLY_PER_BUSINESS_UNITS
							                    WHEN ALLOCATION_BASIS = ''% OF INVENTORY WITHDRAWAL'' and BASED_ON <> ''SALES'' THEN IW_MONTHLY_PER_BUSINESS_UNITS
												WHEN ALLOCATION_BASIS = ''% OF DEMAND'' and BASED_ON <> ''SALES'' THEN DE_MONTHLY_PER_BUSINESS_UNITS END,
                            WAC_PRICE,
                            TC.CCP_DETAILS_SID,
                            PROJECTED_SALES = CASE
                                                WHEN BASED_ON = ''SALES'' THEN SR.PROJECTED_SALES_UNITS
                                                ELSE '
                         + @UNITS_BASED_SALES
                         + '
                                              END,
                            PROJECTED_UNITS = CASE
                                                WHEN BASED_ON = ''SALES'' THEN  '
                         + @SALES_BASED_UNITS
                         + '
												ELSE PROJECTED_SALES_UNITS
                                              END
                     FROM   (SELECT GW.ITEM_MASTER_SID,
                                    P.PERIOD_SID,
                                    Q_PERIOD_SID = MIN(P.PERIOD_SID)
                                                     OVER(
                                                       PARTITION BY GW.ITEM_MASTER_SID, PERIOD),
                                    EX_MONTHLY_PER_BUSINESS = COALESCE(EXFACTORY_FORECAST_SALES,EXFACTORY_ACTUAL_SALES) / NULLIF(SUM(COALESCE(EXFACTORY_FORECAST_SALES,EXFACTORY_ACTUAL_SALES))
                                                                                      OVER(
                                                                                        PARTITION BY GW.ITEM_MASTER_SID, PERIOD),0),
                                    DE_MONTHLY_PER_BUSINESS = COALESCE(DEMAND_FORECAST_SALES,DEMAND_ACTUAL_SALES) / NULLIF(SUM(COALESCE(DEMAND_FORECAST_SALES,DEMAND_ACTUAL_SALES))
                                                                                      OVER(
                                                                                        PARTITION BY GW.ITEM_MASTER_SID, PERIOD),0),
                                    IW_MONTHLY_PER_BUSINESS =COALESCE(INVENTORY_FORECAST_SALES,INVENTORY_ACTUAL_SALES) / NULLIF(SUM(COALESCE(INVENTORY_FORECAST_SALES,INVENTORY_ACTUAL_SALES))
                                                                                      OVER(
                                                                                        PARTITION BY GW.ITEM_MASTER_SID, PERIOD),0),

                                    EX_MONTHLY_PER_BUSINESS_UNITS = COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS) / NULLIF(SUM(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS))
                                                                                      OVER(
                                                                                        PARTITION BY GW.ITEM_MASTER_SID, PERIOD),0),
                                    DE_MONTHLY_PER_BUSINESS_UNITS = COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS) / NULLIF(SUM(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS))
                                                                                      OVER(
                                                                                        PARTITION BY GW.ITEM_MASTER_SID, PERIOD),0),
                                    IW_MONTHLY_PER_BUSINESS_UNITS =COALESCE(INVENTORY_FORECAST_UNITS, INVENTORY_ACTUAL_UNITS) / NULLIF(SUM(COALESCE(INVENTORY_FORECAST_UNITS, INVENTORY_ACTUAL_UNITS))
                                                                                      OVER(
                                                                                        PARTITION BY GW.ITEM_MASTER_SID, PERIOD),0),
                                    COALESCE(EXFACTORY_FORECAST_SALES/NULLIF(EXFACTORY_FORECAST_UNITS,0), GW.ITEM_PRICE) WAC_PRICE
                             FROM   '
                         + @PRODUCT_FILE_TABLE
                         + ' gw 
                                    INNER JOIN #PERIOD P
                                            ON GW.PERIOD_SID = P.PERIOD_SID) A
                            INNER JOIN #SALES_UNIT_RESULT SR INNER JOIN #CCP_DETAILS_TEMP TC ON TC.CCP_DETAILS_SID = SR.CCP_DETAILS_SID
                                    ON SR.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                                       AND SR.PERIOD_SID = A.Q_PERIOD_SID)
            INSERT INTO #RESULT
                        (CCP_DETAILS_SID,
                         MONTHLY_PROJECTED_SALES,
                         MONTHLY_PROJECTED_UNITS,
                         PERIOD_SID)
            SELECT CCP_DETAILS_SID,
                   MONTHLY_PROJECTED_SALES ='
                         + CASE WHEN @FREQUENCY <> 'M' THEN @MONTHLY_PROJECTED_SALES ELSE 'PROJECTED_SALES' END
                         + ',
                   MONTHLY_PROJECTED_UNITS = '
                         + CASE WHEN @FREQUENCY <> 'M' THEN @MONTHLY_PROJECTED_UNITS ELSE 'PROJECTED_UNITS' END
                         + ',
                   MPB.PERIOD_SID
            FROM   MONTHLY_PER_BUSINESS MPB'

            --print @l_sql
            EXEC Sp_executesql
              @l_sql
        --------------------------MONTHLY ALLOCATION(EX-FACTORY) END------------------------------
        END

      IF EXISTS(SELECT 1
                FROM   #CCP_DETAILS_TEMP CCP
                WHERE  METHODOLOGY IN( @XFACTORY_METHOD, @DEMAND_METHOD, @ADJUSTED_DEMAND, @INVENTORY_METHOD ))
        BEGIN
            SET @D_SQL = Concat(' ;WITH RESULT
           AS (SELECT I1.CCP_DETAILS_SID,
                      I1.PERIOD_SID,
                      I1.ACTUALS,
                      I1.PRICE,
                      RATIO_CAL.RATIO,
                      I1.CALCULATION_BASED
               FROM   (SELECT TC.CCP_DETAILS_SID,
                              TC.CALCULATION_BASED,
                              TC.CALCULATION_PERIODS,
                              CASE
                                WHEN CALCULATION_BASED = ''SALES'' THEN case TC.METHODOLOGY  when 
								 @XFACTORY_METHOD then case when g.period_sid<',@ACTUAL_PERIOD,' then  COALESCE(G.EXFACTORY_ACTUAL_SALES, 0) else COALESCE(G.EXFACTORY_FORECAST_SALES, 0) end
								 when @DEMAND_METHOD then COALESCE(G.DEMAND_FORECAST_SALES, G.DEMAND_ACTUAL_SALES, 0)
								  when @INVENTORY_METHOD then COALESCE(G.INVENTORY_FORECAST_SALES, G.INVENTORY_ACTUAL_SALES, 0)
								  when @ADJUSTED_DEMAND then COALESCE(G.ADJUSTED_DEMAND_FORECAST_SALES, G.ADJUSTED_DEMAND_ACTUAL_SALES, 0)
								  end
                                ELSE 
								case TC.METHODOLOGY  when 
								  @XFACTORY_METHOD then case when g.period_sid<',@ACTUAL_PERIOD,' then  COALESCE(G.EXFACTORY_ACTUAL_UNITS, 0) else COALESCE(G.EXFACTORY_FORECAST_UNITS, 0) end
								 when @DEMAND_METHOD then COALESCE(G.DEMAND_FORECAST_UNITS, G.DEMAND_ACTUAL_UNITS, 0)
								  when @INVENTORY_METHOD then COALESCE(G.INVENTORY_FORECAST_UNITS, G.INVENTORY_ACTUAL_UNITS, 0)
								  when @ADJUSTED_DEMAND then COALESCE(G.ADJUSTED_DEMAND_FORECAST_UNITS, G.ADJUSTED_DEMAND_ACTUAL_UNITS, 0)
								  end
								
                              END         AS ACTUALS,
                              G.PERIOD_SID,                                
                                 case TC.METHODOLOGY  
								 when @XFACTORY_METHOD then
								 case when g.period_sid<',@ACTUAL_PERIOD,' then  COALESCE(G.EXFACTORY_ACTUAL_SALES, 0) else COALESCE(G.EXFACTORY_FORECAST_SALES, 0) end/   nullif(case when g.period_sid<',@ACTUAL_PERIOD,' then COALESCE(G.EXFACTORY_ACTUAL_UNITS, 0) else COALESCE(G.EXFACTORY_FORECAST_UNITS, 0) end,0)
								 when @DEMAND_METHOD then isnull(COALESCE(G.DEMAND_FORECAST_SALES, G.DEMAND_ACTUAL_SALES, 0)/NULLIF(COALESCE(G.DEMAND_FORECAST_UNITS, G.DEMAND_ACTUAL_UNITS, 0),0),0)
								 when @INVENTORY_METHOD then isnull(COALESCE(G.INVENTORY_FORECAST_SALES, G.INVENTORY_ACTUAL_SALES, 0)/NULLIF(COALESCE(G.INVENTORY_FORECAST_UNITS, G.INVENTORY_ACTUAL_UNITS, 0),0),0)
								 when @ADJUSTED_DEMAND then isnull(COALESCE(G.ADJUSTED_DEMAND_FORECAST_SALES, G.ADJUSTED_DEMAND_ACTUAL_SALES, 0)/NULLIF(COALESCE(G.ADJUSTED_DEMAND_FORECAST_UNITS, G.ADJUSTED_DEMAND_ACTUAL_UNITS, 0),0),0)
								  end
                                 PRICE,
                              TC.CALC_START_PERIOD_SID,
                              TC.CALC_END_PERIOD_SID
                       FROM   #CCP_DETAILS_TEMP TC
                              INNER JOIN ', @PRODUCT_FILE_TABLE, ' G
                                      ON G.ITEM_MASTER_SID = TC.ITEM_MASTER_SID
                       WHERE  TC.METHODOLOGY IN( @XFACTORY_METHOD, @DEMAND_METHOD, @ADJUSTED_DEMAND, @INVENTORY_METHOD )
                              AND G.PERIOD_SID BETWEEN TC.CALC_START_PERIOD_SID AND TC.CALC_END_PERIOD_SID) I1
                      JOIN (SELECT BL.CCP_DETAILS_SID,
                                   tc.CALCULATION_BASED,
                                   XF.ITEM_MASTER_SID,
                                   CASE
                                     WHEN tc.CALCULATION_BASED = ''SALES''  THEN 
									 case TC.METHODOLOGY  when 
								 @XFACTORY_METHOD then  
									 COALESCE((( BL.BASELINE / NULLIF(XF.EXFACTORY_ACTUAL_SALES, 0) )), 0)
									  when @DEMAND_METHOD then 
									  COALESCE((( BL.BASELINE / NULLIF(XF.DEMAND_ACTUAL_SALES, 0) )), 0)
		  when @INVENTORY_METHOD then 
									  COALESCE((( BL.BASELINE / NULLIF(XF.INVENTORY_ACTUAL_SALES, 0) )), 0)
		  when @ADJUSTED_DEMAND then 
									  COALESCE((( BL.BASELINE / NULLIF(XF.ADJUSTED_DEMAND_ACTUAL_SALES, 0) )), 0)
									  end
                                     ELSE 
									 case TC.METHODOLOGY  when 
								 @XFACTORY_METHOD then  
									 COALESCE((( BL.BASELINE / NULLIF(XF.EXFACTORY_ACTUAL_UNITS, 0) )), 0)
									  when @DEMAND_METHOD then 
									  COALESCE((( BL.BASELINE / NULLIF(XF.DEMAND_ACTUAL_UNITS, 0) )), 0)
		  when @INVENTORY_METHOD then 
									  COALESCE((( BL.BASELINE / NULLIF(XF.INVENTORY_ACTUAL_UNITS, 0) )), 0)
		  when @ADJUSTED_DEMAND then 
									  COALESCE((( BL.BASELINE / NULLIF(XF.ADJUSTED_DEMAND_ACTUAL_UNITS, 0) )), 0)
									  end
                                   END AS RATIO,
                                   TC.CALCULATION_PERIODS
                            FROM   #BASELINE BL
                                   JOIN #CCP_DETAILS_TEMP TC
                                     ON TC.CCP_DETAILS_SID = BL.CCP_DETAILS_SID
                                   JOIN (SELECT Min(CS.PERIOD_SID)                    PERIOD_SID,
                                                COALESCE(Sum(G.EXFACTORY_ACTUAL_SALES), 0) EXFACTORY_ACTUAL_SALES,
                                                COALESCE(Sum(G.EXFACTORY_ACTUAL_UNITS), 0) EXFACTORY_ACTUAL_UNITS,
                                                COALESCE(Sum(G.DEMAND_ACTUAL_SALES), 0) DEMAND_ACTUAL_SALES,
                                                COALESCE(Sum(G.DEMAND_ACTUAL_UNITS), 0) DEMAND_ACTUAL_UNITS,
                                                COALESCE(Sum(G.INVENTORY_ACTUAL_SALES), 0) INVENTORY_ACTUAL_SALES,
                                                COALESCE(Sum(G.INVENTORY_ACTUAL_UNITS), 0) INVENTORY_ACTUAL_UNITS,
                                                COALESCE(Sum(G.ADJUSTED_DEMAND_ACTUAL_SALES), 0) ADJUSTED_DEMAND_ACTUAL_SALES,
                                                COALESCE(Sum(G.ADJUSTED_DEMAND_ACTUAL_UNITS), 0) ADJUSTED_DEMAND_ACTUAL_UNITS,
                                                G.ITEM_MASTER_SID
                                         FROM   (SELECT ITEM_MASTER_SID,CALCULATION_PERIODS FROM #CCP_DETAILS_TEMP GROUP BY ITEM_MASTER_SID,CALCULATION_PERIODS) 
										 SSM  JOIN 
										 ', @PRODUCT_FILE_TABLE, ' G  ON 
                                                  G.ITEM_MASTER_SID=SSM.ITEM_MASTER_SID
												 
												  CROSS APPLY
												  (
												  SELECT TOKEN AS PERIOD,PERIOD_SID FROM UDF_SPLITSTRING(SSM.CALCULATION_PERIODS,'','') FN
												  JOIN #PERIOD P ON P.PERIOD=TOKEN AND P.PERIOD_SID=G.PERIOD_SID
												  )CS
												
                                         GROUP  BY G.ITEM_MASTER_SID) XF
                                     ON XF.ITEM_MASTER_SID = TC.ITEM_MASTER_SID
                                        AND xf.period_sid = bl.period_sid
                            WHERE  TC.METHODOLOGY IN ( @XFACTORY_METHOD, @DEMAND_METHOD, @ADJUSTED_DEMAND, @INVENTORY_METHOD )) RATIO_CAL
                        ON I1.CCP_DETAILS_SID = RATIO_CAL.CCP_DETAILS_SID)
      INSERT #RESULT
             (CCP_DETAILS_SID,
              PERIOD_SID,
              MONTHLY_PROJECTED_SALES,
              MONTHLY_PROJECTED_UNITS)
      SELECT CCP_DETAILS_SID,
             PERIOD_SID,
             CASE
               WHEN CALCULATION_BASED = ''SALES'' THEN COALESCE(( ACTUALS * RATIO ), 0)
               ELSE COALESCE(( ACTUALS * RATIO ), 0) * PRICE
             END SALES,
             CASE
               WHEN CALCULATION_BASED = ''SALES'' THEN COALESCE(( ( ACTUALS * RATIO ) / NULLIF(PRICE, 0) ), 0)
               ELSE COALESCE(( ACTUALS * RATIO ), 0)
             END UNITS
      FROM   RESULT
  ')

            EXEC Sp_executesql
              @D_SQL,
              N'@XFACTORY_METHOD VARCHAR(50), @INVENTORY_METHOD VARCHAR(50), @DEMAND_METHOD VARCHAR(50),@ADJUSTED_DEMAND VARCHAR(50)',
              @XFACTORY_METHOD = @XFACTORY_METHOD,
              @INVENTORY_METHOD = @INVENTORY_METHOD,
              @DEMAND_METHOD = @DEMAND_METHOD,
              @ADJUSTED_DEMAND=@ADJUSTED_DEMAND
        END

      --------------------------------CUSTOMER_GTS_SALES--------------------------------------
      IF EXISTS (SELECT 1
                 FROM   #CCP_DETAILS_TEMP CCP
                 WHERE  METHODOLOGY IN ( @CUSTOMER_GTS_SALES ))
        BEGIN
            SELECT TOP 1 @CUSTOMER_FILE_NAME = FT.FORECAST_NAME,
                         @CUSTOMER_VERSION = FT.[VERSION]
            FROM   FILE_MANAGEMENT FT
                   INNER JOIN HELPER_TABLE HT
                           ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
            WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                     AND FT.FROM_PERIOD IS NOT NULL )
                   AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                          OR FT.TO_PERIOD IS NULL )
                   AND HT.LIST_NAME = 'FILE_TYPE'
                   AND HT.DESCRIPTION = 'CUSTOMER SALES'
                   AND FT.BUSINESS_UNIT = @BUSINESS_UNIT
                   AND FT.COMPANY = @COMPANY_MASTER_SID
            ORDER  BY FT.FROM_PERIOD DESC

            DECLARE @CUST_ITEM_DETAILS [UDT_CUST_ITEM]

            INSERT INTO @CUST_ITEM_DETAILS
                        (ITEM_MASTER_SID,
                         COMPANY_MASTER_SID)
            SELECT DISTINCT ITEM_MASTER_SID,
                            COMPANY_MASTER_SID
            FROM   #CCP_DETAILS_TEMP

            IF Object_id ('TEMPDB..#cust_gts_wac') IS NOT NULL
              DROP TABLE #cust_gts_wac

            SELECT COMPANY_MASTER_SID,
                   ITEM_MASTER_SID,
                   PERIOD_SID,
                   CUST_ACT_GTS_SALES,
                   CUST_ACT_GTS_UNITS,
                   CUST_FORE_GTS_SALES,
                   CUST_FORE_GTS_UNITS,
                   ACTUAL_PRICE FORECAST_PRICE
            INTO   #cust_gts_wac
            FROM   [DBO].[Udf_cust_gts_wac](@CUST_ITEM_DETAILS, @ACTUAL_PERIOD_SID - 3, @PROJ_END_PERIOD_SID, @CUSTOMER_FILE_NAME, @CUSTOMER_VERSION) CGS

            SET @SQL=''
 SET @SQL=concat('WITH CTE
AS (
	SELECT CCP_DETAILS_SID
		,CGS.PERIOD_SID
		, CASE 
				WHEN IW.period_sid <  ', @ACTUAL_PERIOD,'
					THEN COALESCE(CGS.CUST_ACT_GTS_UNITS, 0)
				ELSE COALESCE(CGS.CUST_FORE_GTS_UNITS, 0)
				END  CUST_FORE_GTS_UNITS
		,
		--( CGS.CUST_FORE_GTS_UNITS * IW.ITEM_PRICE ) AS PROJECTED_SALES
		(
			CASE 
				WHEN IW.period_sid <  ', @ACTUAL_PERIOD,'
					THEN COALESCE(CGS.CUST_ACT_GTS_UNITS, 0)
				ELSE COALESCE(CGS.CUST_FORE_GTS_UNITS, 0)
				END
			) * coalesce(nullif((
					CASE 
						WHEN IW.period_sid <  ', @ACTUAL_PERIOD,'
							THEN COALESCE(CGS.CUST_ACT_GTS_SALES, 0)
						ELSE COALESCE(CGS.CUST_FORE_GTS_SALES, 0)
						END
					) / nullif((
						CASE 
							WHEN IW.period_sid <  ', @ACTUAL_PERIOD,'
								THEN COALESCE(CGS.CUST_ACT_GTS_UNITS, 0)
							ELSE COALESCE(CGS.CUST_FORE_GTS_UNITS, 0)
							END
						), 0), 0), IW.ITEM_PRICE) AS PROJECTED_SALES ----CEL-366
	FROM #CCP_DETAILS_TEMP TCCP
	INNER JOIN #cust_gts_wac CGS ON TCCP.COMPANY_MASTER_SID = CGS.COMPANY_MASTER_SID
		AND TCCP.ITEM_MASTER_SID = CGS.ITEM_MASTER_SID
	INNER JOIN ',@PRODUCT_FILE_TABLE,' IW ON CGS.ITEM_MASTER_SID = IW.ITEM_MASTER_SID
		AND CGS.PERIOD_SID = IW.PERIOD_SID
	)
INSERT #RESULT (
	CCP_DETAILS_SID
	,PERIOD_SID
	,MONTHLY_PROJECTED_SALES
	,MONTHLY_PROJECTED_UNITS
	)
SELECT CCP_DETAILS_SID
	,PERIOD_SID
	,Sum(PROJECTED_SALES) PROJECTED_SALES
	,Sum(CUST_FORE_GTS_UNITS) PROJECTED_UNITS
FROM CTE
GROUP BY CCP_DETAILS_SID
	,PERIOD_SID ','')
    EXEC Sp_executesql
              @SQL
			        END


      DECLARE @D_SQL6 NVARCHAR(MAX)

      SET @D_SQL6='UPDATE SNSP
      SET    SNSP.PROJECTION_SALES = COALESCE(RS.MONTHLY_PROJECTED_SALES,0),
             SNSP.PROJECTION_UNITS = COALESCE(RS.MONTHLY_PROJECTED_UNITS,0)
      FROM   ' + @PROJECTION_TABLE
                  + ' SNSP
				  JOIN ' + @MASTER_TABLE
                  + ' S
                         ON SNSP.CCP_DETAILS_SID = S.CCP_DETAILS_SID
and  FILTER_CCP=1  AND  (SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL) 
             JOIN #RESULT RS
               ON SNSP.CCP_DETAILS_SID = RS.CCP_DETAILS_SID
                  AND SNSP.PERIOD_SID = RS.PERIOD_SID
      WHERE  CHECK_RECORD = 1'
		    EXEC Sp_executesql
            @D_SQL6,
            N'@SALES_INCLUSION BIT',
            @SALES_INCLUSION = @SALES_INCLUSION

      --------GALUAT_229 CHANGE, PERIODS OTHER THAN EFFECTIVE RANGE SHOULD BE UPDATED TO 0
      IF Object_id('tempdb..#zeroout_period') IS NOT NULL
        DROP TABLE #zeroout_period

      CREATE TABLE #zeroout_period
        (
           CCP_DETAILS_SID   INT,
           ZEROOUT_START_SID INT,
           ZEROOUT_END_SID   INT
        )

      INSERT INTO #zeroout_period
                  (ZEROOUT_START_SID,
                   ZEROOUT_END_SID,
                   CCP_DETAILS_SID)
       SELECT Iif(FREQ_CAL_START_PERIOD_SID > TC.EFFECTIVE_START_PERIOD_SID,FREQ_CAL_START_PERIOD_SID,EFFECTIVE_START_PERIOD_SID) AS ZEROOUT_START_SID,
              Iif(FREQ_CAL_END_PERIOD_SID < EFFECTIVE_END_PERIOD_SID, FREQ_CAL_END_PERIOD_SID, EFFECTIVE_END_PERIOD_SID) AS ZEROOUT_END_SID,
             CCP_DETAILS_SID
      FROM   #CCP_DETAILS_TEMP TC

      DECLARE @D_SQL7 NVARCHAR(MAX)

      SET @D_SQL7=' UPDATE SNSP
      SET    SNSP.PROJECTION_SALES = 0,
             SNSP.PROJECTION_UNITS = 0
      FROM   ' + @PROJECTION_TABLE
                  + ' SNSP
				  JOIN ' + @MASTER_TABLE
                  + ' S
                         ON SNSP.CCP_DETAILS_SID = S.CCP_DETAILS_SID
             JOIN #ZEROOUT_PERIOD TE
               ON SNSP.CCP_DETAILS_SID = TE.CCP_DETAILS_SID
			   and  FILTER_CCP=1  AND  (SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL) 
      WHERE  (SNSP.PERIOD_SID NOT BETWEEN TE.ZEROOUT_START_SID AND TE.ZEROOUT_END_SID) AND
			CHECK_RECORD = 1'

       EXEC Sp_executesql
            @D_SQL7,
            N'@SALES_INCLUSION BIT',
            @SALES_INCLUSION = @SALES_INCLUSION

END