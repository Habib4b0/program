IF EXISTS (
		SELECT 'X'
		FROM INFORMATION_SCHEMA.ROUTINES
		WHERE ROUTINE_NAME = 'PRC_DISCOUNT_MANUAL_ADJUSTMENT' AND ROUTINE_SCHEMA = 'DBO'
		)
BEGIN
	DROP PROCEDURE [DBO].[PRC_DISCOUNT_MANUAL_ADJUSTMENT]
END
GO

CREATE PROCEDURE [dbo].[PRC_DISCOUNT_MANUAL_ADJUSTMENT] (
	@PROJECTION INT,
	@FREQUENCY VARCHAR(50),
	@USER_ID INT,
	@SESSION_ID VARCHAR(50),
	@ADJUSTMENT_TYPE VARCHAR(20),
	@ADJUSTMENT_BASIS_temp VARCHAR(20),
	@ADJUSTMENT_METHODOLOGY VARCHAR(50),
	@ADJUSTMENT_VALUES NUMERIC(22, 6),
	@BASELINE_PERIODS VARCHAR(4000),
	@SELECTED_PERIODS VARCHAR(4000),
	@DEDUCTION_INCLUSION BIT = NULL
	)
AS
BEGIN
	SET NOCOUNT ON

	DECLARE @METHODOLOGY_COUNT INT,
		@VARIABLE_COUNT INT,
		@OUTER_LOOP INT = 1,
		@INNER_LOOP1 INT = 1,
		@METHODOLOGY VARCHAR(50) = '',
		@ADJUSTMENTTYPE VARCHAR(20) = '',
		@COUNT INT = 0
	DECLARE @MASTER_TABLE VARCHAR(200) = CONCAT (
			'ST_NM_DISCOUNT_PROJ_MASTER_',
			@USER_ID,
			'_',
			@SESSION_ID,
			'_',
			replace(convert(VARCHAR(50), getdate(), 2), '.', '')
			),
		@ACTUAL_TABLE VARCHAR(200) = CONCAT (
			'ST_NM_ACTUAL_DISCOUNT_',
			@USER_ID,
			'_',
			@SESSION_ID,
			'_',
			replace(convert(VARCHAR(50), getdate(), 2), '.', '')
			),
		@PROJECTION_TABLE VARCHAR(200) = CONCAT (
			'ST_NM_DISCOUNT_PROJECTION_',
			@USER_ID,
			'_',
			@SESSION_ID,
			'_',
			replace(convert(VARCHAR(50), getdate(), 2), '.', '')
			),
		@S_PROJECTION_TABLE VARCHAR(200) = CONCAT (
			'ST_NM_SALES_PROJECTION_',
			@user_id,
			'_',
			@session_id,
			'_',
			replace(convert(VARCHAR(50), getdate(), 2), '.', '')
			),
		@SM_TABLE VARCHAR(200) = CONCAT (
			'ST_NM_SALES_PROJECTION_MASTER_',
			@user_id,
			'_',
			@session_id,
			'_',
			replace(convert(VARCHAR(50), getdate(), 2), '.', '')
			),
		@ACTUAL_SALESTABLE VARCHAR(200) = CONCAT (
			'ST_NM_ACTUAL_SALES_',
			@USER_ID,
			'_',
			@SESSION_ID,
			'_',
			replace(convert(VARCHAR(50), getdate(), 2), '.', '')
			),
		@D_SQL NVARCHAR(MAX)

	------------------ ADJUSTMENT CCP DETAILS -------------------------------------------------
	IF OBJECT_ID('TEMPDB.DBO.#DISCOUNT_MASTER', 'U') IS NOT NULL
		DROP TABLE #DISCOUNT_MASTER;

	CREATE TABLE #DISCOUNT_MASTER (
		NM_DISCOUNT_ID INT,
		ITEM_SID INT,
		RS_MODEL_SID INT,
		BASELINE_PERIODS VARCHAR(500),
		SELECTED_PERIODS VARCHAR(500)
		)

	SET @D_SQL = CONCAT (
			';

          WITH CTE
               AS (SELECT STNMS.CCP_DETAILS_SID AS NM_DISCOUNT_ID,
                          CCP.ITEM_MASTER_SID          AS ITEM_SID,
                          STNMS.RS_MODEL_SID,
                         
                          ''',
			@BASELINE_PERIODS,
			''' AS BASELINE_PERIODS,
                          ''',
			@SELECTED_PERIODS,
			'''  AS SELECTED_PERIODS
                   FROM   ',
			@MASTER_TABLE,
			' STNMS
                         
                          JOIN CCP_DETAILS CCP
                            ON CCP.CCP_DETAILS_SID = STNMS.CCP_DETAILS_SID
                 

                          WHERE STNMS.CHECK_RECORD = 1   and  FILTER_CCP=1  ',
			CASE WHEN @DEDUCTION_INCLUSION IS NOT NULL THEN CONCAT (
							'ANd DEDUCTION_INCLUSION =',
							@DEDUCTION_INCLUSION
							) END,
			' 

                          )
          INSERT INTO #DISCOUNT_MASTER
                      (NM_DISCOUNT_ID,
                       ITEM_SID,
                       RS_MODEL_SID,
                       
                       BASELINE_PERIODS,
                       SELECTED_PERIODS)
          SELECT NM_DISCOUNT_ID,
                 ITEM_SID,
                 RS_MODEL_SID,
               
                 BASELINE_PERIODS,
                 SELECTED_PERIODS
          FROM   CTE
		  '
			)

	EXEC sp_executesql @D_SQL

	IF OBJECT_ID('TEMPDB.DBO.#TEMP_PERIOD', 'U') IS NOT NULL
		DROP TABLE #TEMP_PERIOD;

	CREATE TABLE #TEMP_PERIOD (
		PERIOD_SID INT,
		PERIODS VARCHAR(50)
		)

	DECLARE @STRING CHAR(1)

	SELECT @STRING = SUBSTRING(@FREQUENCY, 1, 1)

	IF (@STRING = 'Q')
	BEGIN
		INSERT INTO #TEMP_PERIOD (
			PERIOD_SID,
			PERIODS
			)
		SELECT PERIOD_SID,
			'Q' + CAST(QUARTER AS VARCHAR(2)) + ' ' + CAST(YEAR AS VARCHAR(4)) PERIODS
		FROM PERIOD
	END
	ELSE IF (@STRING = 'S')
	BEGIN
		INSERT INTO #TEMP_PERIOD (
			PERIOD_SID,
			PERIODS
			)
		SELECT PERIOD_SID,
			'S' + CAST(SEMI_ANNUAL AS VARCHAR(2)) + ' ' + CAST(YEAR AS VARCHAR(4)) PERIODS
		FROM PERIOD
	END
	ELSE IF (@STRING = 'M')
	BEGIN
		INSERT INTO #TEMP_PERIOD (
			PERIOD_SID,
			PERIODS
			)
		SELECT PERIOD_SID,
			'M' + CAST(MONTH AS VARCHAR(2)) + ' ' + CAST(YEAR AS VARCHAR(4)) PERIODS
		FROM PERIOD
	END
	ELSE
	BEGIN
		INSERT INTO #TEMP_PERIOD (
			PERIOD_SID,
			PERIODS
			)
		SELECT PERIOD_SID,
			CAST(YEAR AS VARCHAR(4)) PERIODS
		FROM PERIOD
	END

	IF OBJECT_ID('TEMPDB.DBO.#TEMP_BASELINE_PERCENT', 'U') IS NOT NULL
		DROP TABLE #TEMP_BASELINE_PERCENT;

	CREATE TABLE #TEMP_BASELINE_PERCENT (
		NM_DISCOUNT_ID INT,
		RS_MODEL_SID INT,
		ITEM_SID INT,
		BASELINE_SALES_PERCENT NUMERIC(22, 6),
		)

	IF OBJECT_ID('TEMPDB.DBO.#TEMP_PERIOD_CONT', 'U') IS NOT NULL
		DROP TABLE #TEMP_PERIOD_CONT;

	CREATE TABLE #TEMP_PERIOD_CONT (
		PERIOD_SID INT,
		PERIOD_SALES_PERCENT NUMERIC(22, 6),
		)

	IF OBJECT_ID('TEMPDB.DBO.#TEMP_FINAL_DISCOUNT', 'U') IS NOT NULL
		DROP TABLE #TEMP_FINAL_DISCOUNT;

	CREATE TABLE #TEMP_FINAL_DISCOUNT (
		NM_DISCOUNT_ID INT,
		RS_MODEL_SID INT,
		PERIOD_SID INT,
		PROJ_SALES NUMERIC(22, 6),
		PROJ_RATE NUMERIC(22, 6),
		PROJ_RPU NUMERIC(22, 6),
		)

	IF OBJECT_ID('TEMPDB.DBO.#TEMP_PROJECTION_DISCOUNT', 'U') IS NOT NULL
		DROP TABLE #TEMP_PROJECTION_DISCOUNT;

	CREATE TABLE #TEMP_PROJECTION_DISCOUNT (
		CCP_DETAILS_SID INT,
		RS_MODEL_SID INT,
		PROJECTION_SALES NUMERIC(22, 6),
		PROJECTION_RATE NUMERIC(22, 6),
		PROJECTION_RPU NUMERIC(22, 6),
		PERIOD_SID INT,
		ADJUSTMENT_TYPE VARCHAR(20),
		ADJUSTMENT_BASIS VARCHAR(20),
		ADJUSTMENT_VALUE NUMERIC(22, 6),
		ADJUSTMENT_METHODOLOGY VARCHAR(50),
		)

	IF OBJECT_ID('TEMPDB.DBO.#TEMP_METHODOLOGY', 'U') IS NOT NULL
		DROP TABLE #TEMP_METHODOLOGY;

	CREATE TABLE #TEMP_METHODOLOGY (METHODOLOGY VARCHAR(50))

	SET @D_SQL = CONCAT (
			' INSERT INTO #TEMP_PROJECTION_DISCOUNT
                      (CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PROJECTION_SALES,
                       PROJECTION_RATE,
                       PROJECTION_RPU,
                       PERIOD_SID,
                       ADJUSTMENT_TYPE,
                       ADJUSTMENT_BASIS,
                       ADJUSTMENT_VALUE,
                       ADJUSTMENT_METHODOLOGY)
          SELECT STNMP.CCP_DETAILS_SID,
                 RS_MODEL_SID,
                 STNMP.PROJECTION_SALES,
                 STNMP.PROJECTION_RATE,
                 STNMP.PROJECTION_RPU,
                 STNMP.PERIOD_SID,
                 ''',
			@ADJUSTMENT_TYPE,
			''',
                 ''',
			@ADJUSTMENT_BASIS_temp,
			''',
                     ',
			ISNULL(@ADJUSTMENT_VALUES, 0),
			',
				                  ''',
			@ADJUSTMENT_METHODOLOGY,
			'''

          FROM   ',
			@PROJECTION_TABLE,
			' STNMP
          WHERE  EXISTS (SELECT 1
                         FROM   #DISCOUNT_MASTER SM
                         WHERE  STNMP.CCP_DETAILS_SID = SM.NM_DISCOUNT_ID
                                AND STNMP.RS_MODEL_SID = SM.RS_MODEL_SID
                                )'
			)

	EXEC sp_executesql @D_SQL

	DECLARE @ADJUSTMENT_BASIS NUMERIC(22, 6)
	DECLARE @ADJUSTMENT_VALUE NUMERIC(22, 6)
	DECLARE @PRIOR_FLAG BIT
	DECLARE @CONDITION BIT = 0

	SET @D_SQL = ' SELECT @PRIOR_FLAG = 1
          WHERE  EXISTS( SELECT 1
                        FROM   ' + @PROJECTION_TABLE + ' A
                               
                               JOIN ' + @MASTER_TABLE + ' C
                                 ON C.RS_MODEL_SID = A.RS_MODEL_SID
                                    AND C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                   and  FILTER_CCP=1  ' + CASE WHEN @DEDUCTION_INCLUSION IS NOT NULL THEN CONCAT (
						'ANd DEDUCTION_INCLUSION =',
						@DEDUCTION_INCLUSION
						) END + '
                                    AND C.CHECK_RECORD = 1
                        WHERE  PROJECTION_SALES IS NULL)'

	EXEC SP_EXECUTESQL @D_SQL,
		N'@PRIOR_FLAG INT OUTPUT',
		@PRIOR_FLAG OUTPUT

	SET @D_SQL = '  SELECT @CONDITION = 1
          WHERE  NOT EXISTS(SELECT SM.RS_MODEL_SID,
                                   SM.ITEM_SID,
                                   
                                   SUM(STNMP.PROJECTION_SALES) AS PROJECTION_SALES
                            FROM   ' + @PROJECTION_TABLE + 
		' STNMP
                                   JOIN #DISCOUNT_MASTER SM
                                     ON STNMP.CCP_DETAILS_SID = SM.NM_DISCOUNT_ID
                                        AND STNMP.RS_MODEL_SID = SM.RS_MODEL_SID
                                        
                                   JOIN (SELECT PERIOD_SID,
                                                PERIODS
                                         FROM   #TEMP_PERIOD) SUB
                                     ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                            WHERE  EXISTS (SELECT 1
                                           FROM   UDF_SPLITSTRING(SM.SELECTED_PERIODS, '','') A
                                           WHERE  A.TOKEN = SUB.PERIODS)
                                   AND STNMP.PROJECTION_SALES IS NULL
                            GROUP  BY SM.RS_MODEL_SID,
                                      SM.ITEM_SID
                                      )'

	EXEC SP_EXECUTESQL @D_SQL,
		N'@CONDITION INT OUTPUT',
		@CONDITION OUTPUT

	SET @D_SQL = '  SELECT  @ADJUSTMENT_BASIS =(SELECT SUM(A.PROJECTION_SALES) ADJUSTMENT_RESULT
                                  FROM   (SELECT SM.RS_MODEL_SID,
                                                 SM.ITEM_SID,
                                                 SUM(STNMP.PROJECTION_SALES) AS PROJECTION_SALES
                                          FROM   ' + @PROJECTION_TABLE + 
		' STNMP
                                                 JOIN #DISCOUNT_MASTER SM
                                                   ON STNMP.CCP_DETAILS_SID = SM.NM_DISCOUNT_ID
                                                      AND STNMP.RS_MODEL_SID = SM.RS_MODEL_SID
                                                    
                                                 JOIN (SELECT PERIOD_SID,
                                                              PERIODS
                                                       FROM   #TEMP_PERIOD) SUB
                                                   ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                                          WHERE  EXISTS (SELECT 1
                                                         FROM   UDF_SPLITSTRING(SM.SELECTED_PERIODS, '','') A
                                                         WHERE  A.TOKEN = SUB.PERIODS)
                                          GROUP  BY SM.RS_MODEL_SID,SM.ITEM_SID) A)'

	EXEC SP_EXECUTESQL @D_SQL,
		N'@ADJUSTMENT_BASIS NUMERIC(22,6) OUTPUT',
		@ADJUSTMENT_BASIS OUTPUT

	IF @CONDITION = 1
		SET @ADJUSTMENT_VALUE = 0
	ELSE
	BEGIN
		SET @D_SQL = '   SELECT  @ADJUSTMENT_VALUE =(SELECT SUM(A.PROJECTION_SALES) ADJUSTMENT_RESULT
                                    FROM   (SELECT SM.RS_MODEL_SID,
                                                   SM.ITEM_SID,
                                                  
                                                   SUM(STNMP.PROJECTION_SALES) AS PROJECTION_SALES
                                            FROM   ' + @PROJECTION_TABLE + 
			' STNMP
                                                   JOIN #DISCOUNT_MASTER SM
                                                     ON STNMP.CCP_DETAILS_SID = SM.NM_DISCOUNT_ID
                                                        AND STNMP.RS_MODEL_SID = SM.RS_MODEL_SID
                                                       
                                                   JOIN (SELECT PERIOD_SID,
                                                                PERIODS
                                                         FROM   #TEMP_PERIOD) SUB
                                                     ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   UDF_SPLITSTRING(SM.SELECTED_PERIODS, '','') A
                                                           WHERE  A.TOKEN = SUB.PERIODS)
                                                   AND STNMP.PROJECTION_SALES IS NULL
                                            GROUP  BY SM.RS_MODEL_SID,
                                                      SM.ITEM_SID
                                                      ) A)
													  '

		EXEC SP_EXECUTESQL @D_SQL,
			N'@ADJUSTMENT_VALUE NUMERIC(22,6) OUTPUT',
			@ADJUSTMENT_VALUE OUTPUT
	END

	--------------ADJUSTMENT SALES AND PERIOD DETAILS FOR SELECTED PERIODS --------------------------
	DECLARE @PERIOD_CONT TABLE (
		PERIOD_SID INT,
		PROJ_SALES NUMERIC(22, 6),
		BASELINE_PERIOD VARCHAR(500),
		SELECTED_PERIOD VARCHAR(500)
		)

	SET @D_SQL = CONCAT (
			' SELECT STNMP.PERIOD_SID,
                 ''',
			@BASELINE_PERIODS,
			''',
                 ''',
			@SELECTED_PERIODS,
			''',
                 SUM(STNMP.PROJECTION_SALES) AS PROJ_SALES
          FROM   ' + @PROJECTION_TABLE + ' STNMP
                 JOIN #DISCOUNT_MASTER SM
                   ON SM.NM_DISCOUNT_ID = STNMP.CCP_DETAILS_SID
                      AND STNMP.RS_MODEL_SID = SM.RS_MODEL_SID
                          AND ',
			isnull(@ADJUSTMENT_VALUES, 0),
			' IS NOT NULL
          GROUP  BY STNMP.PERIOD_SID'
			)

	INSERT INTO @PERIOD_CONT (
		PERIOD_SID,
		BASELINE_PERIOD,
		SELECTED_PERIOD,
		PROJ_SALES
		)
	EXEC sp_executesql @D_SQL

	---------------- CONTRIBUTION FOR MONTH IN SELECTED PERIODS --------------------------
	IF (@ADJUSTMENT_VALUE IS NULL) OR (@PRIOR_FLAG = 1 AND @ADJUSTMENT_BASIS = 0)
	BEGIN
		SET @D_SQL = 'SELECT @COUNT = COUNT(DISTINCT SUB.PERIOD_SID)
                FROM   ' + @PROJECTION_TABLE + ' STNMP
                       JOIN #DISCOUNT_MASTER SM
                         ON STNMP.CCP_DETAILS_SID = SM.NM_DISCOUNT_ID
                            AND STNMP.RS_MODEL_SID = SM.RS_MODEL_SID
                            
                       JOIN PERIOD SUB
                         ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                       JOIN #TEMP_PERIOD TP
                         ON TP.PERIOD_SID = SUB.PERIOD_SID
                WHERE  EXISTS (SELECT 1
                               FROM   UDF_SPLITSTRING(SM.SELECTED_PERIODS, '','') A
                               WHERE  A.TOKEN = TP.PERIODS)'

		EXEC sp_executesql @D_SQL,
			N'@COUNT INT OUTPUT',
			@COUNT OUTPUT

		INSERT INTO #TEMP_PERIOD_CONT (
			PERIOD_SID,
			PERIOD_SALES_PERCENT
			)
		SELECT PERIOD_SID,
			COALESCE(1.0 / NULLIF(@COUNT, 0), 0) AS SALES_PERIOD_PERCENT
		FROM (
			SELECT PC.PERIOD_SID,
				PC.PROJ_SALES
			FROM @PERIOD_CONT PC
			JOIN (
				SELECT PERIOD_SID,
					PERIODS
				FROM #TEMP_PERIOD
				) SUB
				ON PC.PERIOD_SID = SUB.PERIOD_SID
			WHERE EXISTS (
					SELECT 1
					FROM UDF_SPLITSTRING(PC.SELECTED_PERIOD, ',') A
					WHERE A.TOKEN = SUB.PERIODS
					)
			) A
	END
	ELSE
	BEGIN
		INSERT INTO #TEMP_PERIOD_CONT (
			PERIOD_SID,
			PERIOD_SALES_PERCENT
			)
		SELECT PERIOD_SID,
			COALESCE(PROJ_SALES / NULLIF(SUM(PROJ_SALES) OVER (), 0), 0) AS SALES_PERIOD_PERCENT
		FROM (
			SELECT PC.PERIOD_SID,
				PC.PROJ_SALES
			FROM @PERIOD_CONT PC
			JOIN (
				SELECT PERIOD_SID,
					PERIODS
				FROM #TEMP_PERIOD
				) SUB
				ON PC.PERIOD_SID = SUB.PERIOD_SID
			WHERE EXISTS (
					SELECT 1
					FROM UDF_SPLITSTRING(PC.SELECTED_PERIOD, ',') A
					WHERE A.TOKEN = SUB.PERIODS
					)
			) A
	END

	SELECT @METHODOLOGY_COUNT = COUNT(DISTINCT ADJUSTMENT_METHODOLOGY)
	FROM #TEMP_PROJECTION_DISCOUNT

	WHILE (@OUTER_LOOP <= @METHODOLOGY_COUNT)
	BEGIN
		SELECT DISTINCT TOP 1 @METHODOLOGY = ADJUSTMENT_METHODOLOGY
		FROM #TEMP_PROJECTION_DISCOUNT
		WHERE @METHODOLOGY <> ADJUSTMENT_METHODOLOGY AND NOT EXISTS (
				SELECT METHODOLOGY
				FROM #TEMP_METHODOLOGY T
				WHERE T.METHODOLOGY = #TEMP_PROJECTION_DISCOUNT.ADJUSTMENT_METHODOLOGY
				)
		ORDER BY ADJUSTMENT_METHODOLOGY

		/*
				SELECT DISTINCT TOP 1 @METHODOLOGY = ADJUSTMENT_METHODOLOGY
                FROM   #TEMP_PROJECTION_DISCOUNT
                WHERE  @METHODOLOGY <> ADJUSTMENT_METHODOLOGY
                       AND NOT EXISTS (SELECT METHODOLOGY
                FROM   #TEMP_METHODOLOGY T WHERE T.METHODOLOGY=#TEMP_PROJECTION_DISCOUNT.ADJUSTMENT_METHODOLOGY)
				ORDER BY CCP_DETAILS_SID*/
		INSERT INTO #TEMP_METHODOLOGY (METHODOLOGY)
		VALUES (@METHODOLOGY)

		--------------CONTRIBUTION % FOR EACH LINE IN BASELINE PERIODS ------------------------
		DECLARE @METHODOLOGY_HISTORICAL_SALES VARCHAR(100) = (
				SELECT FORMULA
				FROM FORECASTING_FORMULA
				WHERE FORMULA_NAME = 'HISTORICAL % OF BUSINESS'
				), --MONTHLY_ACTUAL/BASELINE_PERIODS_ACTUAL
			@METHODOLOGY_FORECAST_SALES VARCHAR(100) = (
				SELECT FORMULA
				FROM FORECASTING_FORMULA
				WHERE FORMULA_NAME = 'FORECAST % OF BUSINESS'
				), --MONTHLY_PROJECTION/BASELINE_PERIODS_PROJECTION
			@INSERT_METHODOLOGY NVARCHAR(MAX)

		SET @METHODOLOGY_HISTORICAL_SALES = (
				SELECT REPLACE(REPLACE(@METHODOLOGY_HISTORICAL_SALES, 'MONTHLY_ACTUAL', 'ACTUAL_BASE_SALES'), 'BASELINE_PERIODS_ACTUAL', 'NULLIF(SUM(ACTUAL_BASE_SALES)')
				)
		SET @METHODOLOGY_FORECAST_SALES = (
				SELECT REPLACE(REPLACE(@METHODOLOGY_FORECAST_SALES, 'MONTHLY_PROJECTION', 'PROJECTION_SALES'), 'BASELINE_PERIODS_PROJECTION', 'NULLIF(SUM(PROJECTION_SALES)')
				)

		IF @METHODOLOGY = 'HISTORICAL % OF BUSINESS'
		BEGIN
			SET @INSERT_METHODOLOGY = 'INSERT INTO #TEMP_BASELINE_PERCENT
                                  (NM_DISCOUNT_ID,
                                   RS_MODEL_SID,
                                   ITEM_SID,
                                   BASELINE_SALES_PERCENT)
                      SELECT NM_DISCOUNT_ID,
                             RS_MODEL_SID,
                             ITEM_SID,
                            
                            COALESCE(' + @METHODOLOGY_HISTORICAL_SALES + 'OVER(),0),0) AS BASELINE_SALES_PERCENTAGE
                      FROM   (SELECT SM.NM_DISCOUNT_ID,
                                     SM.RS_MODEL_SID,
                                     SM.ITEM_SID,
                                     
                                     SUM(STNMA.ACTUAL_SALES)AS ACTUAL_BASE_SALES
                              FROM   ' + @ACTUAL_TABLE + 
				' STNMA
                              JOIN   #DISCOUNT_MASTER SM ON STNMA.CCP_DETAILS_SID = SM.NM_DISCOUNT_ID
                                                        AND STNMA.RS_MODEL_SID = SM.RS_MODEL_SID
                                                       
                              JOIN   (SELECT PERIOD_SID,
                                           PERIODS
                                    FROM   #TEMP_PERIOD) SUB ON STNMA.PERIOD_SID = SUB.PERIOD_SID
                              WHERE  EXISTS (SELECT 1
                                             FROM   UDF_SPLITSTRING(SM.BASELINE_PERIODS,''' + ',' + ''') A
                                             WHERE  A.TOKEN = SUB.PERIODS)
                              GROUP  BY SM.NM_DISCOUNT_ID,
                                        SM.RS_MODEL_SID,
                                        SM.ITEM_SID
                                       ) A'
		END
		ELSE
		BEGIN
			IF (@ADJUSTMENT_VALUE IS NULL) OR (@PRIOR_FLAG = 1 AND @ADJUSTMENT_BASIS = 0)
			BEGIN
				SELECT @COUNT = COUNT(1)
				FROM (
					SELECT DISTINCT NM_DISCOUNT_ID,
						RS_MODEL_SID
					FROM #DISCOUNT_MASTER
					) A

				SET @D_SQL = CONCAT (
						'   INSERT INTO #TEMP_BASELINE_PERCENT
                                        (NM_DISCOUNT_ID,
                                         RS_MODEL_SID,
                                         ITEM_SID,
                                         BASELINE_SALES_PERCENT)
                            SELECT DISTINCT NM_DISCOUNT_ID,
                                            RS_MODEL_SID,
                                            ITEM_SID,
                                            
                                            COALESCE(1.0 / NULLIF(',
						@COUNT,
						', 0), 0) AS BASELINE_SALES_PERCENTAGE
                            FROM   (SELECT SM.NM_DISCOUNT_ID,
                                           SM.RS_MODEL_SID,
                                           SM.ITEM_SID,
                                           
                                           STNMP.PROJECTION_SALES AS PROJECTION_SALES
                                    FROM   ',
						@PROJECTION_TABLE,
						' STNMP
                                           JOIN #DISCOUNT_MASTER SM
                                             ON STNMP.CCP_DETAILS_SID = SM.NM_DISCOUNT_ID
                                                AND STNMP.RS_MODEL_SID = SM.RS_MODEL_SID
                                                
                                           JOIN (SELECT PERIOD_SID,
                                                        PERIODS
                                                 FROM   #TEMP_PERIOD) SUB
                                             ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                                    WHERE  EXISTS (SELECT 1
                                                   FROM   UDF_SPLITSTRING(SM.BASELINE_PERIODS,'','') A
                                                   WHERE  A.TOKEN = SUB.PERIODS))A'
						)

				EXEC sp_executesql @D_SQL
			END
			ELSE
			BEGIN
				SET @INSERT_METHODOLOGY = 'INSERT INTO #TEMP_BASELINE_PERCENT
                                  (NM_DISCOUNT_ID,
                                   RS_MODEL_SID,
                                   ITEM_SID,
                                   
                                   BASELINE_SALES_PERCENT
                                   )
                      SELECT NM_DISCOUNT_ID,
                             RS_MODEL_SID,
                             ITEM_SID,
                             
                               COALESCE(' + @METHODOLOGY_FORECAST_SALES + 'OVER(),0),0) AS BASELINE_SALES_PERCENTAGE
                      FROM   (SELECT SM.NM_DISCOUNT_ID,
                                     SM.RS_MODEL_SID,
                                     SM.ITEM_SID,
                                     SUM(STNMP.PROJECTION_SALES) AS PROJECTION_SALES
                              FROM    ' + @PROJECTION_TABLE + 
					' STNMP
                              JOIN   #DISCOUNT_MASTER SM ON STNMP.CCP_DETAILS_SID = SM.NM_DISCOUNT_ID
                                                        AND STNMP.RS_MODEL_SID = SM.RS_MODEL_SID
                                                        
                              JOIN   (SELECT PERIOD_SID,
                                           PERIODS
                                    FROM   #TEMP_PERIOD) SUB ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                              WHERE  EXISTS (SELECT 1
                                             FROM   UDF_SPLITSTRING(SM.BASELINE_PERIODS, ''' + ',' + ''') A
                                             WHERE  A.TOKEN = SUB.PERIODS)
                              GROUP  BY SM.NM_DISCOUNT_ID,
                                        SM.RS_MODEL_SID,
                                        SM.ITEM_SID
                                        ) A'
			END
		END

		EXEC SP_EXECUTESQL @INSERT_METHODOLOGY

		SELECT @VARIABLE_COUNT = COUNT(*)
		FROM (
			SELECT DISTINCT ADJUSTMENT_TYPE
			FROM #TEMP_PROJECTION_DISCOUNT
			WHERE ADJUSTMENT_METHODOLOGY = @METHODOLOGY
			) A

		WHILE (@INNER_LOOP1 <= @VARIABLE_COUNT)
		BEGIN
			SELECT DISTINCT TOP 1 @ADJUSTMENTTYPE = ADJUSTMENT_TYPE
			FROM #TEMP_PROJECTION_DISCOUNT
			WHERE (ADJUSTMENT_TYPE <> @ADJUSTMENTTYPE) AND ADJUSTMENT_METHODOLOGY = @METHODOLOGY
			ORDER BY ADJUSTMENT_TYPE

			/*
					  SELECT DISTINCT TOP 1 @ADJUSTMENTTYPE = ADJUSTMENT_TYPE
                      FROM   #TEMP_PROJECTION_DISCOUNT
                      WHERE  ( ADJUSTMENT_TYPE <> @ADJUSTMENTTYPE )
					  ORDER BY CCP_DETAILS_SID*/
			IF @ADJUSTMENTTYPE = 'OVERRIDE'
			BEGIN
				SET @D_SQL = CONCAT (
						'  INSERT INTO #TEMP_FINAL_DISCOUNT
                                        (NM_DISCOUNT_ID,
                                         RS_MODEL_SID,
                                         PERIOD_SID,
                                        
                                         PROJ_SALES,
                                         PROJ_RATE,
                                         PROJ_RPU)
                            SELECT A.CCP_DETAILS_SID,
                                   A.RS_MODEL_SID,
                                   A.PERIOD_SID,
                                  
                                   ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUE ) * A.BASELINE_SALES_PERCENT )                                                        AS SALES,
                                   ( COALESCE(( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUE ) * A.BASELINE_SALES_PERCENT * 100 ) / NULLIF(A.PROJECTION_SALES, 0), 0) ) AS RATE,
                                   ( COALESCE(( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUE ) * A.BASELINE_SALES_PERCENT ) / NULLIF(A.PROJECTION_UNITS, 0), 0) ) AS RPU
                            FROM   (SELECT TPS.CCP_DETAILS_SID,
                                           TPS.RS_MODEL_SID,
                                           TPS.PERIOD_SID,
                                          
                                           TPC.PERIOD_SALES_PERCENT,
                                           CASE
                                             WHEN TPS.ADJUSTMENT_BASIS = ''AMOUNT'' THEN TPS.ADJUSTMENT_VALUE
                                             ELSE (( TPS.ADJUSTMENT_VALUE ) /100) * '
						,
						isnull(@ADJUSTMENT_BASIS, 0),
						'
                                           END                        ADJUSTMENT_VALUE,
                                           CASE
                                             WHEN TPS.ADJUSTMENT_BASIS = ''AMOUNT'' THEN TPS.ADJUSTMENT_VALUE
                                             ELSE ( ( TBP.BASELINE_SALES_PERCENT ) / 100 )* TPS.ADJUSTMENT_VALUE
                                           END                        AS NEW_METHODOLOGY,
                                           TBP.BASELINE_SALES_PERCENT,
                                           IP.PROJECTION_SALES,
                                           IP.PROJECTION_UNITS,
                                           TPS.ADJUSTMENT_METHODOLOGY AS METHODOLOGY
                                    FROM   #TEMP_PROJECTION_DISCOUNT TPS
                                           JOIN #TEMP_PERIOD_CONT TPC
                                             ON TPC.PERIOD_SID = TPS.PERIOD_SID
                                           JOIN #TEMP_BASELINE_PERCENT TBP
                                             ON TBP.NM_DISCOUNT_ID = TPS.CCP_DETAILS_SID
                                                AND TBP.RS_MODEL_SID = TPS.RS_MODEL_SID
                                           JOIN '
						,
						@S_PROJECTION_TABLE,
						' IP
                                             ON IP.CCP_DETAILS_SID = TPS.CCP_DETAILS_SID
                                                AND IP.PERIOD_SID = TPS.PERIOD_SID
                                                
                                    WHERE  TPS.ADJUSTMENT_METHODOLOGY = ''',
						@METHODOLOGY,
						'''
                                           AND TPS.ADJUSTMENT_TYPE =''',
						@ADJUSTMENTTYPE,
						'''
                                           ) A'
						)

				EXEC sp_executesql @D_SQL
			END
			ELSE
			BEGIN
				SET @D_SQL = CONCAT (
						' INSERT INTO #TEMP_FINAL_DISCOUNT
                                        (NM_DISCOUNT_ID,
                                         RS_MODEL_SID,
                                         PERIOD_SID,
                                         
                                         PROJ_SALES,
                                         PROJ_RATE,
                                         PROJ_RPU)
                            SELECT A.CCP_DETAILS_SID,
                                   A.RS_MODEL_SID,
                                   A.PERIOD_SID,
                                   
                                   ( ISNULL(A.SALES_DISCOUNT, 0) + ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUE ) * A.BASELINE_SALES_PERCENT ) )                                         AS SALES,
                                   COALESCE(( ISNULL(A.SALES_DISCOUNT, 0) + ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUE ) * A.BASELINE_SALES_PERCENT ) ) * 100 / NULLIF(A.SALES, 0), 0) AS RATE,---RATE=(NEW_DISCOUNT_AMOUNT*100)/SALES
                                   COALESCE(( ISNULL(A.SALES_DISCOUNT, 0) + ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUE ) * A.BASELINE_SALES_PERCENT ) ) / NULLIF(A.UNITS, 0), 0) AS RPU ---RATE=(NEW_DISCOUNT_AMOUNT*100)/UNITS
                            FROM   (SELECT TPS.CCP_DETAILS_SID,
                                           TPS.RS_MODEL_SID,
                                           TPS.PERIOD_SID,
                                           
                                           TPC.PERIOD_SALES_PERCENT,
                                           CASE
                                             WHEN TPS.ADJUSTMENT_BASIS = ''AMOUNT'' THEN TPS.ADJUSTMENT_VALUE
                                             ELSE (( TPS.ADJUSTMENT_VALUE ) / 100) *'
						,
						isnull(@ADJUSTMENT_BASIS, 0),
						'
                                           END                        ADJUSTMENT_VALUE,
                                           CASE
                                             WHEN TPS.ADJUSTMENT_BASIS = ''AMOUNT'' THEN TPS.ADJUSTMENT_VALUE
                                             ELSE (( TBP.BASELINE_SALES_PERCENT ) / 100) * TPS.ADJUSTMENT_VALUE
                                           END                        AS NEW_METHODOLOGY,
                                           TBP.BASELINE_SALES_PERCENT,
                                           TPS.PROJECTION_SALES       AS SALES_DISCOUNT,
                                           IP.PROJECTION_SALES        AS SALES,
                                           IP.PROJECTION_UNITS        AS UNITS,
                                           TPS.ADJUSTMENT_METHODOLOGY AS METHODOLOGY
                                    FROM   #TEMP_PROJECTION_DISCOUNT TPS
                                           JOIN #TEMP_PERIOD_CONT TPC
                                             ON TPC.PERIOD_SID = TPS.PERIOD_SID
                                           JOIN #TEMP_BASELINE_PERCENT TBP
                                             ON TBP.NM_DISCOUNT_ID = TPS.CCP_DETAILS_SID
                                                AND TBP.RS_MODEL_SID = TPS.RS_MODEL_SID
                                           JOIN '
						,
						@S_PROJECTION_TABLE,
						' IP
                                             ON IP.CCP_DETAILS_SID = TPS.CCP_DETAILS_SID
                                                AND IP.PERIOD_SID = TPS.PERIOD_SID
                                               
                                    WHERE  TPS.ADJUSTMENT_METHODOLOGY =''',
						@METHODOLOGY,
						'''
                                           AND TPS.ADJUSTMENT_TYPE =''',
						@ADJUSTMENTTYPE,
						'''
                                           ) A'
						)

				EXEC sp_executesql @D_SQL
			END

			SET @INNER_LOOP1 = @INNER_LOOP1 + 1
		END

		SET @INSERT_METHODOLOGY = ''
		SET @INNER_LOOP1 = 1
		SET @COUNT = 0

		DELETE
		FROM #TEMP_BASELINE_PERCENT

		SET @OUTER_LOOP = @OUTER_LOOP + 1
	END

	IF (@ADJUSTMENT_VALUES = 0)
	BEGIN
		IF OBJECT_ID('TEMPDB.DBO.#PERIOD_SID_FIND', 'U') IS NOT NULL
			DROP TABLE #PERIOD_SID_FIND

		CREATE TABLE #PERIOD_SID_FIND (
			PERIOD_SID INT,
			PERIODS VARCHAR(50)
			)

		DECLARE @STRING1 CHAR(1)

		SELECT @STRING1 = SUBSTRING(@FREQUENCY, 1, 1)

		IF (@STRING1 = 'Q')
		BEGIN
			INSERT INTO #PERIOD_SID_FIND (
				PERIOD_SID,
				PERIODS
				)
			SELECT PERIOD_SID,
				'Q' + CAST(QUARTER AS VARCHAR(2)) + ' ' + CAST(YEAR AS VARCHAR(4)) PERIODS
			FROM PERIOD
		END
		ELSE IF (@STRING1 = 'S')
		BEGIN
			INSERT INTO #PERIOD_SID_FIND (
				PERIOD_SID,
				PERIODS
				)
			SELECT PERIOD_SID,
				'S' + CAST(SEMI_ANNUAL AS VARCHAR(2)) + ' ' + CAST(YEAR AS VARCHAR(4)) PERIODS
			FROM PERIOD
		END
		ELSE IF (@STRING1 = 'M')
		BEGIN
			INSERT INTO #PERIOD_SID_FIND (
				PERIOD_SID,
				PERIODS
				)
			SELECT PERIOD_SID,
				'M' + CAST(MONTH AS VARCHAR(2)) + ' ' + CAST(YEAR AS VARCHAR(4)) PERIODS
			FROM PERIOD
		END
		ELSE
		BEGIN
			INSERT INTO #PERIOD_SID_FIND (
				PERIOD_SID,
				PERIODS
				)
			SELECT PERIOD_SID,
				CAST(YEAR AS VARCHAR(4)) PERIODS
			FROM PERIOD
		END

		DECLARE @DP_SQL nVARCHAR(MAX)

		SET @DP_SQL = CONCAT (
				'UPDATE P
SET P.PROJECTION_SALES = A.ACTUAL_SALES
	,P.PROJECTION_RATE = ((A.ACTUAL_SALES / NULLIF(SP.PROJECTION_SALES, 0)) * 100)
	,P.PROJECTION_RPU = ((A.ACTUAL_SALES / NULLIF(SP.PROJECTION_UNITS, 0)))
FROM ',
				@MASTER_TABLE,
				' M
JOIN ',
				@PROJECTION_TABLE,
				' P ON M.CCP_DETAILS_SID = P.CCP_DETAILS_SID
	AND FILTER_CCP = 1  AND (DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL)
JOIN ',
				@ACTUAL_TABLE,
				' A ON A.CCP_DETAILS_SID = P.CCP_DETAILS_SID
	AND A.PERIOD_SID = P.PERIOD_SID
	AND A.RS_CONTRACT_SID = P.RS_CONTRACT_SID
JOIN #PERIOD_SID_FIND PP ON PP.PERIOD_SID = P.PERIOD_SID
JOIN ',
				@SM_TABLE,
				' SPM ON SPM.CCP_DETAILS_SID = M.CCP_DETAILS_SID
	AND SPM.ADJUSTED_CCP = 1
JOIN ',
				@S_PROJECTION_TABLE,
				' SP ON SP.CCP_DETAILS_SID = M.CCP_DETAILS_SID
	AND SP.PERIOD_SID = P.PERIOD_SID
 '
				)

		EXEC sp_executesql @DP_SQL,
			N'@DEDUCTION_INCLUSION BIT',
			@DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
	END

	-----------------------------------
	SET @D_SQL = '  UPDATE STNMP
          SET    STNMP.PROJECTION_SALES = TFS.PROJ_SALES,
                 STNMP.PROJECTION_RATE = TFS.PROJ_RATE,
                 STNMP.PROJECTION_RPU = TFS.PROJ_RPU
          FROM   ' + @PROJECTION_TABLE + ' STNMP
                 JOIN #TEMP_FINAL_DISCOUNT TFS
                   ON STNMP.CCP_DETAILS_SID = TFS.NM_DISCOUNT_ID
                      AND STNMP.RS_MODEL_SID = TFS.RS_MODEL_SID
                      AND STNMP.PERIOD_SID = TFS.PERIOD_SID

         '

	EXEC sp_executesql @D_SQL
END

