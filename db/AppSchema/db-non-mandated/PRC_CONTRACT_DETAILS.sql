IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_CONTRACT_DETAILS'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_CONTRACT_DETAILS]
  END

GO

CREATE PROCEDURE [dbo].[PRC_CONTRACT_DETAILS] (@PROJECTION_MASTER_SID INT,
                                              @FREQUENCY             VARCHAR(20),
                                              @USER_ID               INT,
                                              @SESSION_ID            VARCHAR(50),
											  @FORECAST_START_PERIOD_SID  INT,
											  @FORECAST_END_PERIOD_SID     INT,
											  @DEDUCTION_INCLUSION INT 
											  )
AS
BEGIN
	BEGIN TRY
		DECLARE @PROJECTION_END_PERIOD_SID INT
			,@FORECASTING_TYPE VARCHAR(50)
			,@SQL NVARCHAR(MAX)
			
		DECLARE @MASTER_TABLE VARCHAR(200) = CONCAT (
				'ST_NM_DISCOUNT_PROJ_MASTER_'
				,@USER_ID
				,'_'
				,@SESSION_ID
				,'_'
				,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
				)
			,@PROJECTION_TABLE VARCHAR(200) = CONCAT (
				'ST_NM_DISCOUNT_PROJECTION_'
				,@USER_ID
				,'_'
				,@SESSION_ID
				,'_'
				,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
				)
			,@S_PROJECTION_TABLE VARCHAR(200) = CONCAT (
				'ST_NM_SALES_PROJECTION_'
				,@user_id
				,'_'
				,@session_id
				,'_'
				,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
				)
			,@D_SQL NVARCHAR(MAX)
			,@D_SQL1 NVARCHAR(MAX)
			,@PRODUCT_TABLE VARCHAR(200) = CONCAT (
				'ST_PRODUCT_FILE_'
				,@USER_ID
				,'_'
				,@SESSION_ID
				,'_'
				,Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')
				)
			,@REBATE_INFO VARCHAR(200) = CONCAT (
				'ST_NM_DISC_REBATE_INFO_'
				,@USER_ID
				,'_'
				,@SESSION_ID
				,'_'
				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
				)
			,@TIER_INFO VARCHAR(MAX) = CONCAT (
				'ST_NM_DISC_TIER_INFO_'
				,@USER_ID
				,'_'
				,@SESSION_ID
				,'_'
				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
				)
			,@RETURNS_INFO VARCHAR(MAX) = CONCAT (
				'ST_RETURNS_CONTRACT_INFO_'
				,@USER_ID
				,'_'
				,@SESSION_ID
				,'_'
				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
				)
			,@NATIONAL_INFO VARCHAR(MAX) = CONCAT (
				'ST_NATIONAL_CONTRACT_INFO_'
				,@USER_ID
				,'_'
				,@SESSION_ID
				,'_'
				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
				)
-----------------CEL-102
		SET @SQL = CONCAT (
				'
            UPDATE A
            SET    FREQ_CAL_START_PERIOD_SID = CASE WHEN  ',@FORECAST_START_PERIOD_SID,' > EFFECTIVE_START_PERIOD_SID THEN  ',@FORECAST_START_PERIOD_SID,' ELSE EFFECTIVE_START_PERIOD_SID END ,
                   FREQ_CAL_END_PERIOD_SID = CASE WHEN  ',@FORECAST_END_PERIOD_SID,'  < EFFECTIVE_END_PERIOD_SID THEN  ',@FORECAST_END_PERIOD_SID,' ELSE EFFECTIVE_END_PERIOD_SID END 
           FROM '
				,@MASTER_TABLE
				,' A '
				)

		EXEC sp_executesql @SQL
-----------------CEL-102
		SET @FREQUENCY = LEFT(@FREQUENCY, 1)
		

		SELECT @FORECASTING_TYPE = FORECASTING_TYPE
		FROM PROJECTION_MASTER
		WHERE PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

		SELECT @PROJECTION_END_PERIOD_SID = PROJECTION_END_SID
		FROM [DBO].[Udf_projection_dates](@PROJECTION_MASTER_SID, @FREQUENCY)

		IF Object_id('TEMPDB..#CONTRACT_DETAILS') IS NOT NULL
			TRUNCATE TABLE #CONTRACT_DETAILS
		ELSE
			CREATE TABLE #CONTRACT_DETAILS (
				CCP_DETAILS_SID INT
				,RS_MODEL_SID INT
				,RS_CONTRACT_SID INT
				,PRICE_GROUP_TYPE VARCHAR(50)
				,PERIOD_SID INT
				,PROJECTION_AMOUNT NUMERIC(22, 6)
				,PROJECTION_RATE NUMERIC(22, 6)
				,PROJECTION_REBATE_PER_UNIT NUMERIC(22, 6)
				,PROJECTION_SALES NUMERIC(22, 6)
				,PROJECTION_UNITS NUMERIC(22, 6)
				,RP_NET_SALES_FORMULA_MASTER_SID INT
				,RS_NET_SALES_FORMULA_MASTER_SID INT
				,SALES_PROJECTED_VALUE NUMERIC(22, 6)
				,CALCULATION_TYPE VARCHAR(50)
				,CALCULATION_RULE INT
				,EVALUATION_RULE INT PRIMARY KEY (
					CCP_DETAILS_SID
					,RS_MODEL_SID
					,PERIOD_SID
					)
				)

		------------------------------------------------------------- CONTRACT DETAILS METHODOLOGY STARTS HERE
		---------------------------------------------------- MONTHLY PERIOD 
		IF Object_id('TEMPDB.DBO.#PERIOD_CD', 'U') IS NOT NULL
			DROP TABLE #PERIOD_CD;

		CREATE TABLE #PERIOD_CD (
			PERIOD_SID INT
			,PERIOD_DATE DATE
			,PERIOD_MONTH INT
			,PERIOD_QUARTER INT
			,PERIOD_SEMI INT
			,PERIOD_YEAR INT
			,PERIOD VARCHAR(10)
			,PERIOD_QUAR VARCHAR(10)
			,PERIOD_SEMIF VARCHAR(10)
			,PERIOD_YEARF VARCHAR(10) PRIMARY KEY (
				PERIOD_SID
				,PERIOD_DATE
				,PERIOD_MONTH
				,PERIOD_QUARTER
				,PERIOD_SEMI
				,PERIOD_YEAR
				)
			)

		INSERT INTO #PERIOD_CD (
			PERIOD_SID
			,PERIOD_DATE
			,PERIOD_MONTH
			,PERIOD_QUARTER
			,PERIOD_SEMI
			,PERIOD_YEAR
			,PERIOD
			,PERIOD_QUAR
			,PERIOD_SEMIF
			,PERIOD_YEARF
			)
		SELECT PERIOD_SID
			,PERIOD_DATE
			,MONTH AS PERIOD_MONTH
			,QUARTER AS PERIOD_QUARTER
			,SEMI_ANNUAL AS PERIOD_SEMI
			,YEAR AS PERIOD_YEAR
			, CASE 
				WHEN @FREQUENCY = 'M'
					THEN CONCAT (
							'M'
							,MONTH
							,' '
							,YEAR
							)
				WHEN @FREQUENCY = 'Q'
					THEN CONCAT (
							'Q'
							,QUARTER
							,' '
							,YEAR
							)
				WHEN @FREQUENCY = 'S'
					THEN CONCAT (
							'S'
							,SEMI_ANNUAL
							,' '
							,YEAR
							)
				ELSE Cast(YEAR AS CHAR(4))
				END AS PERIOD
			,CONCAT (
				'Q'
				,QUARTER
				,' '
				,YEAR
				) AS PERIOD_QUAR
			,CONCAT (
				'S'
				,QUARTER
				,' '
				,YEAR
				) as PERIOD_SEMIF
			, Cast(YEAR AS CHAR(4)) as PERIOD_YEARF
		FROM PERIOD


		IF OBJECT_ID('TEMPDB..#TABLEA') IS NOT NULL
			DROP TABLE #TABLEA

		CREATE TABLE #TABLEA (
			CCP_DETAILS_SID INT
			,RS_MODEL_SID INT
			,PRICE_GROUP_TYPE VARCHAR(50)
			,PERIOD_SID INT
			,SALES_PROJECTED_VALUE NUMERIC(22, 6)
			,SALES_PROJECTION_UNITS NUMERIC(22, 6)
			,CALCULATION_LEVEL VARCHAR(50)
			,REBATE_STRUCTURE VARCHAR(50)
			,TIER_OPERATOR VARCHAR(50)
			,ITEM_MASTER_SID INT
			,[STATUS] INT
			,PROJECTION_SALES NUMERIC(22, 6)
			,PROJECTION_UNITS NUMERIC(22, 6)
			,RP_NET_SALES_FORMULA_MASTER_SID INT
			,RS_NET_SALES_FORMULA_MASTER_SID INT
			,CALCULATION_RULE VARCHAR(50)
			,EVALUATION_RULE VARCHAR(50)
			,REBATE_FREQUENCY VARCHAR(50)
			,REBATE_RANGE_BASED_ON VARCHAR(50)
			,UPPS VARCHAR(50)
			,REBATE_BASED_ON  VARCHAR(50)---------------CEL-345,CEL-346
			)

		SET @d_sql = CONCAT (
				'
				INSERT INTO #TABLEA
                           (
                           CCP_DETAILS_SID,
                           RS_MODEL_SID,
                           PRICE_GROUP_TYPE,
                           PERIOD_SID,
                           SALES_PROJECTED_VALUE,
						   SALES_PROJECTION_UNITS,
                           CALCULATION_LEVEL,
                           REBATE_STRUCTURE,
                           TIER_OPERATOR,
                           ITEM_MASTER_SID,
						   STATUS,
                           PROJECTION_SALES,
                           PROJECTION_UNITS,
                           RP_NET_SALES_FORMULA_MASTER_SID,
                           RS_NET_SALES_FORMULA_MASTER_SID,
                           CALCULATION_RULE,
                           EVALUATION_RULE,
                           REBATE_FREQUENCY,
                           REBATE_RANGE_BASED_ON,
                           UPPS
						   ,REBATE_BASED_ON---------------CEL-345,CEL-346
                           )

					SELECT CCP_DETAILS_SID,
                           RS_MODEL_SID,
                           PRICE_GROUP_TYPE,
                           PERIOD_SID,
                           SALES_PROJECTED_VALUE=SUM(SALES_PROJECTED_VALUE)
                                                         OVER (
                                                           PARTITION BY REBATE_GROUP, PERIOD_REBATE),
                           SALES_PROJECTION_UNITS=SUM(PROJECTION_UNITS)
                                                            OVER (
                                                              PARTITION BY REBATE_GROUP, PERIOD_REBATE),
                                CALCULATION_LEVEL,
                                REBATE_STRUCTURE,
                                TIER_OPERATOR,
                                ITEM_MASTER_SID,
								STATUS,
                                PROJECTION_SALES,
                                PROJECTION_UNITS,
                                RP_NET_SALES_FORMULA_MASTER_SID,
                                RS_NET_SALES_FORMULA_MASTER_SID,
                                CALCULATION_RULE,
                                EVALUATION_RULE,
                                REBATE_FREQUENCY,
                                REBATE_RANGE_BASED_ON,
                                UPPS
								,REBATE_BASED_ON---------------CEL-345,CEL-346
                         FROM   (SELECT R.CCP_DETAILS_SID,
                                        CCP.CONTRACT_MASTER_SID,
                                        CCP.COMPANY_MASTER_SID,
                                        CCP.ITEM_MASTER_SID,
										IM.ITEM_STATUS AS STATUS,
                                        R.RS_MODEL_SID,
                                        PRICE_GROUP_TYPE,
                                        CALCULATION_LEVEL,
                                        CASE
                                          WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT SALES'', ''DOLLARS'' ) THEN PROJECTION_SALES
                                          WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT UNITS'', ''UNITS'' ) THEN PROJECTION_UNITS
                                        END AS SALES_PROJECTED_VALUE,
                                        SNSP.PERIOD_SID,
                                        BUNDLE_NO,
                                        REBATE_FREQUENCY,
                                        REBATE_STRUCTURE,
                                        TIER_OPERATOR,
                                     CASE WHEN 
                                                              LEFT(REBATE_FREQUENCY,1)=''Q'' THEN 
                                        P.PERIOD_QUAR
                                                                     WHEN 
                                                              LEFT(REBATE_FREQUENCY,1)=''A'' THEN 
                                        P.PERIOD_YEARF
                                                                     WHEN 
                                                              LEFT(REBATE_FREQUENCY,1)=''S'' THEN 
                                        P.PERIOD_SEMIF
                                                              WHEN 
                                                              LEFT(REBATE_FREQUENCY,1)=''M'' THEN 
                                       CONVERT(VARCHAR(4), P.PERIOD_SID) END PERIOD_REBATE,
                                        PROJECTION_SALES,
                                        PROJECTION_UNITS,
                                        RP_NET_SALES_FORMULA_MASTER_SID,
                                        RS_NET_SALES_FORMULA_MASTER_SID,
                                        CALCULATION_RULE,
                                        EVALUATION_RULE,
										REBATE_BASED_ON,---------------CEL-345,CEL-346
                                        REBATE_RANGE_BASED_ON,
                                                                     
                                                                     REBATE_GROUP,
                                                                     UPPS = (
                                                CASE 
                                                       WHEN REBATE_BASED_ON = ''Contract CMS Units''
                                                              THEN UPPS
                                                       ELSE 1
                                                       END)
                                 FROM  '
				,@REBATE_INFO
				,' R
                                        INNER JOIN '
				,@S_PROJECTION_TABLE
				,' SNSP
                                                ON R.CCP_DETAILS_SID = SNSP.CCP_DETAILS_SID AND R.REBATE_PLAN_MASTER_SID IS NOT NULL
                                                                     INNER JOIN '
				,@MASTER_TABLE
				,
				' SM  
                                                                               ON R.CCP_DETAILS_SID = SM.CCP_DETAILS_SID
                                                                                      AND R.RS_MODEL_SID=SM.RS_MODEL_SID
                                                                                         AND SM.METHODOLOGY=''CONTRACT DETAILS'' 
                                                                     INNER JOIN CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID=SM.CCP_DETAILS_SID
                                                                     INNER JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
                                        INNER JOIN #PERIOD_CD P
                                                ON SNSP.PERIOD_SID = P.PERIOD_SID AND SM.CHECK_RECORD=1 and  FILTER_CCP=1  ',case when @DEDUCTION_INCLUSION is not null then concat('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END,'
                                                  AND SNSP.PERIOD_SID BETWEEN SM.FREQ_CAL_START_PERIOD_SID AND SM.FREQ_CAL_END_PERIOD_SID  
                                   )A'
				)

		EXEC sp_executesql @d_sql
		


IF OBJECT_ID('TEMPDB..#NATIONAL_INFO ') IS NOT NULL
			DROP TABLE #NATIONAL_INFO 

CREATE TABLE #NATIONAL_INFO (
	CCP_DETAILS_SID			INT
	,RS_MODEL_SID				INT
	,PERIOD_SID					INT
	,SALES_PROJECTED_VALUE		NUMERIC(22,6)
	,ITEM_PRICING_QUALIFIER     VARCHAR(1000)
	,RETURN_RATE				NUMERIC(22,6)
	,ITEM_MASTER_SID			INT
	,[CPI]						NUMERIC(22,6)
	,TP_UPPS					BIT
	,FORMULA_TYPE				VARCHAR(10)
	,RN							INT
	,COMPLEX_VALUE				NUMERIC(22,6)
	)
IF OBJECT_ID('TEMPDB..#TEMP_NATIONAL_INFO ') IS NOT NULL
	DROP TABLE #TEMP_NATIONAL_INFO

CREATE TABLE #TEMP_NATIONAL_INFO (COLUMN_NAME VARCHAR(100),ITEM_MASTER_SID INT)

SET @sql = CONCAT (
		'INSERT INTO #TEMP_NATIONAL_INFO
	SELECT DISTINCT QUOTENAME(CASE WHEN ST.ITEM_PRICING_QUALIFIER=''CPI ALT URA'' THEN ''CPI (ALT) URA'' ELSE ST.ITEM_PRICING_QUALIFIER END) AS COLUMN_NAME,ITEM_MASTER_SID FROM ',
		@NATIONAL_INFO,
		'
		  ST JOIN ITEM_PRICING_QUALIFIER IP ON IP.ITEM_PRICING_QUALIFIER_name=CASE WHEN ST.ITEM_PRICING_QUALIFIER=''CPI ALT URA'' THEN ''CPI (ALT) URA'' ELSE ST.ITEM_PRICING_QUALIFIER END '
		)

EXEC Sp_executesql @sql

DECLARE @SCRIPT_PREPARE  NVARCHAR(MAX);

 SET @SCRIPT_PREPARE = (SELECT DISTINCT 'ALTER TABLE #NATIONAL_INFO ADD  '+ COLUMN_NAME+ ' NUMERIC(22,6) ; '
                                 FROM    #TEMP_NATIONAL_INFO 
                                 FOR XML PATH (''))


 EXEC sp_executesql @SCRIPT_PREPARE
 

DECLARE @COLUMNS NVARCHAR(MAX)
SELECT @COLUMNS =STUFF((SELECT distinct ',' + COLUMN_NAME 
                     FROM    #TEMP_NATIONAL_INFO 
                                 FOR XML PATH (''), TYPE
            ).value('.', 'NVARCHAR(MAX)') 
        ,1,1,'')


SET @D_SQL = N'
INSERT INTO #NATIONAL_INFO
SELECT
    * 
FROM
(
	SELECT 
	C.CCP_DETAILS_SID			
	,C.RS_MODEL_SID			
	,C.PERIOD_SID				
	,C.SALES_PROJECTED_VALUE
	,TP.ITEM_PRICING_QUALIFIER 	 ITEM_PRICING_QUALIFIER1
	,TP.ITEM_PRICING_QUALIFIER 
	,RP.RETURN_RATE			
	,TP.ITEM_MASTER_SID		
	,INDEX_VALUE AS CPI					
	,0 AS TP_UPPS				
	,FORMULA_TYPE			
	,DENSE_RANK() OVER (
		ORDER BY TP.ITEM_PRICING_QUALIFIER
		) RN					
	,NULL as COMPLEX_VALUE
	,ITEM_PRICE	
	FROM  #TABLEA C
JOIN #PERIOD_CD P ON P.PERIOD_SID = C.PERIOD_SID
JOIN ' + @NATIONAL_INFO + ' TP ON C.ITEM_MASTER_SID = TP.ITEM_MASTER_SID
	AND C.PERIOD_SID = TP.PERIOD_SID
LEFT JOIN '+ @RETURNS_INFO +' RP ON C.ITEM_MASTER_SID = RP.ITEM_MASTER_SID
	AND C.PERIOD_SID = RP.PERIOD_SID 
OUTER APPLY (SELECT TOP 1 INDEX_VALUE FROM CPI_INDEX_MASTER CIM  WHERE CIM.STATUS = C.STATUS ORDER BY EFFECTIVE_DATE DESC )OA
	WHERE  TP.FORMULA_TYPE <> ''SIMPLE''      
) AS P
PIVOT
(
    SUM(ITEM_PRICE) FOR ITEM_PRICING_QUALIFIER IN (' + @COLUMNS + ') 
) AS P1

UNION ALL

SELECT
    *
FROM
(
	SELECT 
	C.CCP_DETAILS_SID			
	,C.RS_MODEL_SID			
	,C.PERIOD_SID				
	,C.SALES_PROJECTED_VALUE	
	,TP.ITEM_PRICING_QUALIFIER 	 ITEM_PRICING_QUALIFIER1
	,TP.ITEM_PRICING_QUALIFIER 
	,RP.RETURN_RATE			
	,TP.ITEM_MASTER_SID		
	,INDEX_VALUE AS CPI					
	,1 AS TP_UPPS				
	,FORMULA_TYPE			
	,DENSE_RANK() OVER (
		ORDER BY TP.ITEM_PRICING_QUALIFIER
		) RN				
	,NULL as COMPLEX_VALUE
	,ISNULL(ITEM_PRICE,0) AS ITEM_PRICE	
	FROM  #TABLEA C
JOIN #PERIOD_CD P ON P.PERIOD_SID = C.PERIOD_SID
JOIN ' + @NATIONAL_INFO + ' TP ON C.ITEM_MASTER_SID = TP.ITEM_MASTER_SID
	AND C.PERIOD_SID = TP.PERIOD_SID
LEFT JOIN '+ @RETURNS_INFO +' RP ON C.ITEM_MASTER_SID = RP.ITEM_MASTER_SID
	AND C.PERIOD_SID = RP.PERIOD_SID 
	OUTER APPLY (SELECT TOP 1 INDEX_VALUE FROM CPI_INDEX_MASTER CIM  WHERE CIM.STATUS = C.STATUS ORDER BY EFFECTIVE_DATE DESC )OA
	WHERE  TP.FORMULA_TYPE <> ''SIMPLE''      
) AS P
PIVOT
(
    SUM(ITEM_PRICE) FOR ITEM_PRICING_QUALIFIER IN (' + @COLUMNS + ') 
) AS P1
'

EXEC (@D_SQL)

-----------------------------------------TIER OPERATOR =% IMPLEMENTATION ENDS HERE

IF Object_id('tempdb..#SIMPLE_FORMULA_CALCULATION') IS NOT NULL
	DROP TABLE #SIMPLE_FORMULA_CALCULATION

	CREATE TABLE #SIMPLE_FORMULA_CALCULATION
	(ITEM_MASTER_SID  INT
		,PERIOD_SID  INT
		,ITEM_PRICING_QUALIFIER VARCHAR(1000)
		,ITEM_PRICE NUMERIC(22,6)
		,ITEM_PRICE_UPPS NUMERIC(22,6)
		,FORMULA_TYPE VARCHAR(10)
	)
	SET @d_sql = CONCAT (
				'INSERT INTO #SIMPLE_FORMULA_CALCULATION
				(ITEM_MASTER_SID
		,PERIOD_SID
		,ITEM_PRICING_QUALIFIER
		,ITEM_PRICE
		,ITEM_PRICE_UPPS
		,FORMULA_TYPE)
	SELECT ITEM_MASTER_SID
		,PERIOD_SID
		,ITEM_PRICING_QUALIFIER
		,ITEM_PRICE
		,ITEM_PRICE_UPPS
		,FORMULA_TYPE
	FROM ' ,@NATIONAL_INFO, ' WHERE FORMULA_TYPE = ''SIMPLE''')
		EXEC sp_executesql @d_sql

			
DECLARE @COUNT INT
	,@ROW INT = 1
	,@FORMULA VARCHAR(2000);

SELECT @COUNT = COUNT(DISTINCT ITEM_PRICING_QUALIFIER)
FROM #NATIONAL_INFO

WHILE (@ROW <= @COUNT)
BEGIN
	SET @FORMULA = REPLACE((
			SELECT DISTINCT ITEM_PRICING_QUALIFIER
			FROM #NATIONAL_INFO
			WHERE RN = @ROW
			),'Returns Rate','RETURN_RATE')
	SET @D_SQL = '
UPDATE #NATIONAL_INFO
SET COMPLEX_VALUE=' + @FORMULA + ' 
WHERE   RN=''' + CONVERT(NVARCHAR, @ROW) + ''''

	EXECUTE (@D_SQL)

	SET @ROW = @ROW + 1
END


		SET @D_SQL = CONCAT (
				@D_SQL
				,
				'  ;WITH 
                     TIER_INFO
                     AS (SELECT C.CCP_DETAILS_SID,
                                C.RS_MODEL_SID,
                                PRICE_GROUP_TYPE,
                                C.PERIOD_SID,
                                PROJECTION_SALES,
                                PROJECTION_UNITS,
                                SALES_PROJECTED_VALUE,
                                REBATE_STRUCTURE,
                                CALCULATION_LEVEL,
                                RP_NET_SALES_FORMULA_MASTER_SID,
                                RS_NET_SALES_FORMULA_MASTER_SID,
                                TIER_FROM,
                                TIER_TO,
                                DISCOUNT_RATE,
                                REBATE_PER_UNIT,
                                FLAT_DISCOUNT,
                                TIER_OPERATOR,
                                CASE WHEN TP.ITEM_PRICING_QUALIFIER= OU.ITEM_PRICING_QUALIFIER_NAME THEN TP.ITEM_PRICE END ITEM_PRICE,---CEL-368
								CASE WHEN REBATE_STRUCTURE in ( ''TIER'',''LEVEL'') and TIER_OPERATOR = ''$'' and TP.ITEM_PRICING_QUALIFIER= OU.ITEM_PRICING_QUALIFIER_NAME AND (SALES_PROJECTED_VALUE >= TIER_FROM AND SALES_PROJECTED_VALUE <= ISNULL(TIER_TO,SALES_PROJECTED_VALUE) )   THEN   TP.ITEM_PRICING_QUALIFIER  ELSE  ''1'' END PRICE_VALUES,-------- CEL-345,CEL-346,cel-368,cel-1204, CEL-1811/CEL-466 
                                VALUE=CASE
                                        WHEN REBATE_STRUCTURE = ''TIER'' THEN  
                                          CASE
                                            WHEN SALES_PROJECTED_VALUE >= TIER_FROM
                                                 AND SALES_PROJECTED_VALUE <= ISNULL(TIER_TO,SALES_PROJECTED_VALUE) THEN
												 CASE WHEN TP1.FORMULA_TYPE <> ''SIMPLE'' THEN ISNULL(CFC.COMPLEX_VALUE,0)
										  
										    ELSE 
                                              CASE
                                                WHEN TIER_OPERATOR = ''%'' THEN ( COALESCE((NULLIF(DISCOUNT_RATE, 0)/100), RETURN_RATE/100)  )
                                                WHEN TIER_OPERATOR = ''$'' THEN COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(CASE WHEN TP.ITEM_PRICING_QUALIFIER= OU.ITEM_PRICING_QUALIFIER_NAME AND (SALES_PROJECTED_VALUE >= TIER_FROM AND SALES_PROJECTED_VALUE <= ISNULL(TIER_TO,SALES_PROJECTED_VALUE) )  THEN CASE WHEN REBATE_BASED_ON = ''CONTRACT CMS UNITS'' THEN TP.ITEM_PRICE_UPPS ELSE TP.ITEM_PRICE END END, 0), 0)---------------CEL-345,CEL-346,CEL-368,CEL-1204
                                              END
                                           END
										  END
										  
                                        WHEN REBATE_STRUCTURE = ''LEVEL'' THEN
                                          CASE
                                            WHEN TIER_TO < SALES_PROJECTED_VALUE THEN
                                              CASE
                                                WHEN TIER_OPERATOR = ''%'' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE((NULLIF(DISCOUNT_RATE, 0)/100), RETURN_RATE/100) ))
                                                WHEN TIER_OPERATOR = ''$'' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(CASE WHEN TP.ITEM_PRICING_QUALIFIER= OU.ITEM_PRICING_QUALIFIER_NAME AND (SALES_PROJECTED_VALUE >= TIER_FROM AND SALES_PROJECTED_VALUE <= ISNULL(TIER_TO,SALES_PROJECTED_VALUE) )  THEN CASE WHEN REBATE_BASED_ON = ''CONTRACT CMS UNITS'' THEN TP.ITEM_PRICE_UPPS ELSE TP.ITEM_PRICE END END, 0), 0) ) )---------------CEL-345,CEL-346,CEL-368,cel-1204
                                              END
                                            WHEN TIER_TO >= SALES_PROJECTED_VALUE THEN
                                              CASE
                                                WHEN SALES_PROJECTED_VALUE BETWEEN TIER_FROM AND TIER_TO THEN
                                                  CASE
                                                    WHEN TIER_OPERATOR = ''%'' THEN ( ( IIF(SALES_PROJECTED_VALUE < TIER_TO, SALES_PROJECTED_VALUE, TIER_TO) - TIER_FROM ) * ( COALESCE((NULLIF(DISCOUNT_RATE, 0)/100), RETURN_RATE/100)  ) )
                                                    WHEN TIER_OPERATOR = ''$'' THEN ( ( IIF(SALES_PROJECTED_VALUE < TIER_TO, SALES_PROJECTED_VALUE, TIER_TO) - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(CASE WHEN TP.ITEM_PRICING_QUALIFIER= OU.ITEM_PRICING_QUALIFIER_NAME AND (SALES_PROJECTED_VALUE >= TIER_FROM AND SALES_PROJECTED_VALUE <= ISNULL(TIER_TO,SALES_PROJECTED_VALUE) )  THEN CASE WHEN REBATE_BASED_ON = ''CONTRACT CMS UNITS'' THEN TP.ITEM_PRICE_UPPS ELSE TP.ITEM_PRICE END END, 0), 0) ) )---------------CEL-345,CEL-346,CEL-368,cel-1204                               
													END
                                              END
                                          END
                                        WHEN REBATE_STRUCTURE = ''FLAT'' THEN FLAT_DISCOUNT
                                      END,
                                REBATE_FREQUENCY,
                                CALCULATION_RULE,
                                EVALUATION_RULE,
                                REBATE_RANGE_BASED_ON,
                                                       UPPS
                         FROM   #TABLEA C
                                            JOIN #PERIOD_CD P ON P.PERIOD_SID=C.PERIOD_SID
                                LEFT JOIN (SELECT DISTINCT ITEM_MASTER_SID
		,PERIOD_SID
		,FORMULA_TYPE
	FROM ' ,@NATIONAL_INFO,') TP1 ON  C.ITEM_MASTER_SID = TP1.ITEM_MASTER_SID
                                           AND C.PERIOD_SID = TP1.PERIOD_SID 
							LEFT JOIN #SIMPLE_FORMULA_CALCULATION TP
                                        ON C.ITEM_MASTER_SID = TP.ITEM_MASTER_SID
                                           AND C.PERIOD_SID = TP.PERIOD_SID
										   AND TP1.FORMULA_TYPE=TP.FORMULA_TYPE
							LEFT JOIN (SELECT CCP_DETAILS_SID,RS_MODEL_SID,PERIOD_SID,ITEM_MASTER_SID,FORMULA_TYPE,COMPLEX_VALUE,TP_UPPS
							FROM  #NATIONAL_INFO) CFC ON CFC.CCP_DETAILS_SID=C.CCP_DETAILS_SID
										   AND CFC.RS_MODEL_SID=C.RS_MODEL_SID
										   AND CFC.PERIOD_SID=C.PERIOD_SID
										   AND CFC.ITEM_MASTER_SID=C.ITEM_MASTER_SID
										   AND TP1.FORMULA_TYPE=CFC.FORMULA_TYPE
										   AND TP_UPPS=CASE WHEN REBATE_BASED_ON = ''CONTRACT CMS UNITS'' THEN 1 ELSE 0 END 
                              LEFT JOIN '
				,@RETURNS_INFO
				,' RP
                                        ON C.ITEM_MASTER_SID = RP.ITEM_MASTER_SID
                                           AND C.PERIOD_SID = RP.PERIOD_SID
                                OUTER APPLY (SELECT DISCOUNT_RATE,
                                                    REBATE_PER_UNIT,
                                                    FLAT_DISCOUNT,
                                                    TIER_FROM,
                                                    TIER_TO=CASE
                                                              WHEN T.TIER_TO IS NULL THEN SALES_PROJECTED_VALUE
                                                              ELSE T.TIER_TO
                                                            END
													,T.ITEM_PRICING_QUALIFIER_NAME----,cel-1204
                                             FROM   '
				,@TIER_INFO
				,
				' T
                                             WHERE  C.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                                                    AND C.RS_MODEL_SID = T.RS_MODEL_SID) OU),----- CEL-1811/CEL-466
                     AGGREGATION1
                     AS (SELECT ROW_NUMBER ()
                                  OVER (
                                    PARTITION BY CCP_DETAILS_SID, RS_MODEL_SID, PERIOD_SID
                                    ORDER BY CCP_DETAILS_SID)RN,
                                CCP_DETAILS_SID,
                                RS_MODEL_SID,
                                PRICE_GROUP_TYPE,
                                PERIOD_SID,
								SUM_PROJECTION_DOLLAR=SUM(VALUE)
                                                  OVER (
                                                    PARTITION BY CCP_DETAILS_SID, RS_MODEL_SID, PERIOD_SID),
                                SUM_PROJECTION_PERCENT= VALUE,--FINAL SUM (FOR FLAT AND TIER VALUE = SUM_PROJECTION) BUT FOR LEVEL VALUE FOR SINGLE CCP AND  SUM_PROJECTION=SUM OF SINGLE CCP FOR ALL TIER
                                PROJECTION_SALES,
                                PROJECTION_UNITS,
                                RATIO=( ( CASE
                                            WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT SALES'', ''DOLLARS'' ) THEN PROJECTION_SALES
                                            WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT UNITS'', ''UNITS'' ) THEN PROJECTION_UNITS
                                          END ) / NULLIF(SALES_PROJECTED_VALUE, 0) ),--EITHER PROJECTION_SALES OR PROJECTION_UNITS CONFIRMATION BASED ON REBATE RANGE BASED ON
                                SALES_PROJECTED_VALUE,
                                REBATE_STRUCTURE,
                                CALCULATION_LEVEL,
                                RP_NET_SALES_FORMULA_MASTER_SID,
                                RS_NET_SALES_FORMULA_MASTER_SID,
                                TIER_OPERATOR,
                                VALUE,
                                CALCULATION_RULE,
                                EVALUATION_RULE,
                                REBATE_FREQUENCY,
                                                       UPPS
                         FROM   TIER_INFO WHERE PRICE_VALUES IS NOT NULL
						 )
						,----- CEL-1811/CEL-466
                     AGGREGATION12
                     AS (SELECT RN, CCP_DETAILS_SID,
                                RS_MODEL_SID,
                                PRICE_GROUP_TYPE,
                                PERIOD_SID,
								SUM_PROJECTION_DOLLAR,
								SUM_PROJECTION_PERCENT=SUM(VALUE)
                                                  OVER (
                                                    PARTITION BY CCP_DETAILS_SID, RS_MODEL_SID, PERIOD_SID),
								PROJECTION_SALES,
                                PROJECTION_UNITS,
                                RATIO,
								SALES_PROJECTED_VALUE,
                                REBATE_STRUCTURE,
                                CALCULATION_LEVEL,
                                RP_NET_SALES_FORMULA_MASTER_SID,
                                RS_NET_SALES_FORMULA_MASTER_SID,
                                TIER_OPERATOR,
                                VALUE,
                                CALCULATION_RULE,
                                EVALUATION_RULE,
                                REBATE_FREQUENCY,
                                                       UPPS FROM AGGREGATION1 WHERE RN=1)
						,----- CEL-1811/CEL-466
                     AGGREGATION
                     AS (SELECT RN, CCP_DETAILS_SID,
                                RS_MODEL_SID,
                                PRICE_GROUP_TYPE,
                                PERIOD_SID,
								 CASE 
								 WHEN TIER_OPERATOR = ''%'' THEN SUM_PROJECTION_PERCENT
								 WHEN TIER_OPERATOR = ''$'' THEN SUM_PROJECTION_DOLLAR
								 END AS SUM_PROJECTION,
								PROJECTION_SALES,
                                PROJECTION_UNITS,
                                RATIO,
								SALES_PROJECTED_VALUE,
                                REBATE_STRUCTURE,
                                CALCULATION_LEVEL,
                                RP_NET_SALES_FORMULA_MASTER_SID,
                                RS_NET_SALES_FORMULA_MASTER_SID,
                                TIER_OPERATOR,
                                VALUE,
                                CALCULATION_RULE,
                                EVALUATION_RULE,
                                REBATE_FREQUENCY,
                                                       UPPS FROM AGGREGATION1 WHERE RN=1),
                     CONTRACT_DETAILS
                     AS (SELECT CCP_DETAILS_SID,
                                RS_MODEL_SID,
                                PRICE_GROUP_TYPE,
                                PERIOD_SID,
                                PROJECTION_AMOUNT= AMOUNT,
                                PROJECTION_RATE= ( AMOUNT / NULLIF(PROJECTION_SALES, 0) ) * 100,
                                PROJECTION_REBATE_PER_UNIT = (AMOUNT / NULLIF((PROJECTION_UNITS), 0)),---------------CEL-345,CEL-346
                                PROJECTION_SALES,
                                PROJECTION_UNITS,
                                RP_NET_SALES_FORMULA_MASTER_SID,
                                RS_NET_SALES_FORMULA_MASTER_SID,
                                SALES_PROJECTED_VALUE,
                                CALCULATION_RULE,
                                EVALUATION_RULE
                         FROM   (SELECT CCP_DETAILS_SID,
                                        RS_MODEL_SID,
                                        PRICE_GROUP_TYPE,
                                        PERIOD_SID,
                                        AMOUNT,
                                        PROJECTION_SALES,
                                        PROJECTION_UNITS,
                                        RP_NET_SALES_FORMULA_MASTER_SID,
                                        RS_NET_SALES_FORMULA_MASTER_SID,
                                        SALES_PROJECTED_VALUE,
                                        CALCULATION_RULE,
                                        EVALUATION_RULE,
                                                                     UPPS
                                 FROM   (SELECT CCP_DETAILS_SID,
                                                RS_MODEL_SID,
                                                PRICE_GROUP_TYPE,
                                                PERIOD_SID,
                                                AMOUNT= CASE
                                                          WHEN REBATE_STRUCTURE = ''TIER'' THEN SUM_PROJECTION * CASE
                                                                                                                 WHEN TIER_OPERATOR = ''%'' THEN PROJECTION_SALES
                                                                                                                WHEN TIER_OPERATOR = ''$'' THEN PROJECTION_UNITS
                                                                                                               END
                                                          WHEN REBATE_STRUCTURE = ''LEVEL'' THEN
                                                            CASE
                                                              WHEN CALCULATION_LEVEL LIKE ''%LINE%'' THEN
                                                                CASE
                                                                  WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN SUM_PROJECTION / 3
                                                                  WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN SUM_PROJECTION / 6
                                                                  WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN SUM_PROJECTION / 12
                                                                  WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN SUM_PROJECTION
                                                                END
                                                              ELSE SUM_PROJECTION * RATIO
                                                            END
                                                          WHEN REBATE_STRUCTURE = ''FLAT'' THEN
                                                            CASE
                                                              WHEN CALCULATION_LEVEL LIKE ''%LINE%'' THEN
                                                                CASE
                                                                  WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN SUM_PROJECTION / 3
                                                                  WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN SUM_PROJECTION / 6
                                                                  WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN SUM_PROJECTION / 12
                                                                  WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN SUM_PROJECTION
                                                                END
                                                              ELSE SUM_PROJECTION * RATIO
                                                            END
                                                        END,
                                                PROJECTION_SALES,
                                                PROJECTION_UNITS,
                                                RP_NET_SALES_FORMULA_MASTER_SID,
                                                RS_NET_SALES_FORMULA_MASTER_SID,
                                                SALES_PROJECTED_VALUE,
                                                CALCULATION_RULE,
                                                EVALUATION_RULE,
                                                REBATE_FREQUENCY,
                                                REBATE_STRUCTURE,
                                                                                  UPPS
                                         FROM   AGGREGATION
                                         WHERE  RN = 1)B)A)
                INSERT INTO #CONTRACT_DETAILS
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PRICE_GROUP_TYPE,
                             PERIOD_SID,
                             PROJECTION_AMOUNT,
                             PROJECTION_RATE,
                             PROJECTION_REBATE_PER_UNIT,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             RP_NET_SALES_FORMULA_MASTER_SID,
                             RS_NET_SALES_FORMULA_MASTER_SID,
                             SALES_PROJECTED_VALUE,
                             CALCULATION_TYPE,
                             CALCULATION_RULE,
                             EVALUATION_RULE)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PRICE_GROUP_TYPE,
                       PERIOD_SID,
                       PROJECTION_AMOUNT,
                       PROJECTION_RATE,
                       PROJECTION_REBATE_PER_UNIT,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       RP_NET_SALES_FORMULA_MASTER_SID,
                       RS_NET_SALES_FORMULA_MASTER_SID,
                       SALES_PROJECTED_VALUE,
                       ''REBATE PLAN'',
                       CALCULATION_RULE,
                       EVALUATION_RULE
                FROM   CONTRACT_DETAILS'
				)

		EXEC sp_executesql @D_SQL


		SET @D_SQL = CONCAT (
				'    UPDATE SNDP
                SET    SNDP.PROJECTION_SALES = CD.PROJECTION_AMOUNT,
                       SNDP.PROJECTION_RPU = CD.PROJECTION_REBATE_PER_UNIT,
                       SNDP.PROJECTION_RATE = CD.PROJECTION_RATE
                FROM   '
				,@PROJECTION_TABLE
				,' SNDP
                       INNER JOIN #CONTRACT_DETAILS CD
                               ON SNDP.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                  AND SNDP.RS_MODEL_SID = CD.RS_MODEL_SID
                                  AND SNDP.PERIOD_SID = CD.PERIOD_SID '
				)

		EXEC sp_executesql @D_SQL

		DECLARE @DEDUCTION BIT

		SET @D_SQL1 = CONCAT (
				'SELECT @DEDUCTION=1 FROM '
				,@REBATE_INFO
				,' WHERE  CALCULATION_TYPE = ''DEDUCTION CALENDAR'''
				)

		EXEC SP_EXECUTESQL @D_SQL1
			,N'@DEDUCTION INT OUTPUT'
			,@DEDUCTION = @DEDUCTION OUTPUT --------cel-141

		IF @DEDUCTION = 1
		BEGIN
			SET @D_SQL = CONCAT (
					'  ;WITH PERIODS
                     AS (SELECT RI.DEDUCTION_CALENDAR_MASTER_SID,
                                RI.CCP_DETAILS_SID,
                                CD.COMPANY_MASTER_SID,
                                RI.ITEM_MASTER_SID,
                                RI.RS_MODEL_SID,
                                P.PERIOD_SID,
                                P.PERIOD_YEAR,
                                P.PERIOD_MONTH
                         FROM   '
					,@REBATE_INFO
					,' RI
                                                 JOIN '
					,@MASTER_TABLE
					,
					' SM
                                                       ON SM.CCP_DETAILS_SID=RI.CCP_DETAILS_SID
                                                         AND SM.RS_MODEL_SID=RI.RS_MODEL_SID AND CALCULATION_TYPE = ''DEDUCTION CALENDAR''
												JOIN CCP_DETAILS CD ON SM.CCP_DETAILS_SID=CD.CCP_DETAILS_SID
                                CROSS JOIN #PERIOD_CD P
                         WHERE  P.PERIOD_SID BETWEEN FREQ_CAL_START_PERIOD_SID AND FREQ_CAL_END_PERIOD_SID AND SM.CHECK_RECORD=1 and  FILTER_CCP=1  ',case when @DEDUCTION_INCLUSION is not null then concat('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END,'),
                     CONTRACT_DETAILS_DEDUCTION
                     AS (SELECT D.CCP_DETAILS_SID,
                                RS_MODEL_SID,
                                D.PERIOD_SID,
                                PROJECTION_AMOUNT=DISCOUNT_AMOUNT,
                                PROJECTION_RATE=( ISNULL(DISCOUNT_AMOUNT, 0) / NULLIF(PROJECTION_SALES, 0) ) * 100,
                                PROJECTION_REBATE_PER_UNIT=( ISNULL(DISCOUNT_AMOUNT, 0) / NULLIF(PROJECTION_UNITS, 0) ),
                                PROJECTION_SALES,
                                PROJECTION_UNITS
                         FROM   (SELECT RI.DEDUCTION_CALENDAR_MASTER_SID,
                                        RI.CCP_DETAILS_SID,
                                        RI.COMPANY_MASTER_SID,
                                        RI.ITEM_MASTER_SID,
                                        CD.CP_DETAILS_SID,
                                        RI.PERIOD_SID,
                                        RI.RS_MODEL_SID,
                                        ISNULL(DCD.AMOUNT, 0) AS DISCOUNT_AMOUNT
                                 FROM   DEDUCTION_CALENDAR_COMPANY DCC
                                        RIGHT JOIN PERIODS RI
                                                ON RI.DEDUCTION_CALENDAR_MASTER_SID = DCC.DEDUCTION_CALENDAR_MASTER_SID
                                                   AND RI.COMPANY_MASTER_SID = DCC.COMPANY_MASTER_SID
                                                   AND DCC.INBOUND_STATUS = ''A''
                                        RIGHT JOIN DEDUCTION_CALENDAR_ITEM DCI
                                                ON RI.DEDUCTION_CALENDAR_MASTER_SID = DCI.DEDUCTION_CALENDAR_MASTER_SID
                                                   AND RI.ITEM_MASTER_SID = DCI.ITEM_MASTER_SID
                                                   AND DCI.INBOUND_STATUS = ''A''
                                        RIGHT JOIN CP_DETAILS CD
                                                ON CD.COMPANY_MASTER_SID = DCC.COMPANY_MASTER_SID
                                                   AND CD.ITEM_MASTER_SID = DCI.ITEM_MASTER_SID
                                        INNER JOIN (SELECT CP_DETAILS_SID,
                                                           [YEAR],
                                                           DEDUCTION_CALENDAR_MASTER_SID,
                                                           AMOUNT,
                                                           CS.[MONTH]
                                                    FROM   (SELECT CP_DETAILS_SID,
                                                                   [YEAR],
                                                                   JAN_DISCOUNT_AMOUNT,
                                                                   FEB_DISCOUNT_AMOUNT,
                                                                   MAR_DISCOUNT_AMOUNT,
                                                                   APR_DISCOUNT_AMOUNT,
                                                                   MAY_DISCOUNT_AMOUNT,
                                                                   JUN_DISCOUNT_AMOUNT,
                                                                   JUL_DISCOUNT_AMOUNT,
                                                                   AUG_DISCOUNT_AMOUNT,
                                                                   SEP_DISCOUNT_AMOUNT,
                                                                   OCT_DISCOUNT_AMOUNT,
                                                                   NOV_DISCOUNT_AMOUNT,
                                                                   DEC_DISCOUNT_AMOUNT,
                                                                   CHECK_RECORD,
                                                                   DEDUCTION_CALENDAR_MASTER_SID
                                                            FROM   DEDUCTION_CALENDAR_DETAILS)A
                                                           CROSS APPLY (VALUES(JAN_DISCOUNT_AMOUNT,
                                                                       1),
                                                                              (FEB_DISCOUNT_AMOUNT,
                                                                       2),
                                                                              (MAR_DISCOUNT_AMOUNT,
                                                                       3),
                                                                              (APR_DISCOUNT_AMOUNT,
                                                                       4),
                                                                              (MAY_DISCOUNT_AMOUNT,
                                                                       5),
                                                                              (JUN_DISCOUNT_AMOUNT,
                                                                       6),
                                                                              (JUL_DISCOUNT_AMOUNT,
                                                                       7),
                                                                              (AUG_DISCOUNT_AMOUNT,
                                                                       8),
                                                                              (SEP_DISCOUNT_AMOUNT,
                                                                       9),
                                                                              (OCT_DISCOUNT_AMOUNT,
                                                                       10),
                                                                              (NOV_DISCOUNT_AMOUNT,
                                                                       11),
                                                                              (DEC_DISCOUNT_AMOUNT,
                                                                       12))CS (AMOUNT, [MONTH])
																	   WHERE  EXISTS (SELECT 1
                                                FROM   PERIODS D
                                                WHERE  D.DEDUCTION_CALENDAR_MASTER_SID = A.DEDUCTION_CALENDAR_MASTER_SID)
																	   )DCD
                                                ON DCD.CP_DETAILS_SID = CD.CP_DETAILS_SID
                                                   AND DCD.[YEAR] = RI.PERIOD_YEAR
                                                   AND DCD.[MONTH] = RI.PERIOD_MONTH
                                )D
                                LEFT JOIN ')
						SET @D_SQL=CONCAT(@D_SQL, ' ',@S_PROJECTION_TABLE,' SNSP
                                       ON D.CCP_DETAILS_SID = SNSP.CCP_DETAILS_SID
                                          AND D.PERIOD_SID = SNSP.PERIOD_SID)
										  
                
		INSERT INTO #CONTRACT_DETAILS
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             PROJECTION_AMOUNT,
                             PROJECTION_RATE,
                             PROJECTION_REBATE_PER_UNIT,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             CALCULATION_TYPE)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PERIOD_SID,
                       PROJECTION_AMOUNT,
                       PROJECTION_RATE,
                       PROJECTION_REBATE_PER_UNIT,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       ''DEDUCTION CALENDAR''
                FROM   CONTRACT_DETAILS_DEDUCTION'
					)

			EXEC sp_executesql @D_SQL

			SET @D_SQL = CONCAT (
					'   UPDATE SNDP
                SET    SNDP.PROJECTION_SALES = CD.PROJECTION_AMOUNT,
                       SNDP.PROJECTION_RPU = CD.PROJECTION_REBATE_PER_UNIT,
                       SNDP.PROJECTION_RATE = CD.PROJECTION_RATE
                FROM   '
					,@PROJECTION_TABLE
					,' SNDP
                       INNER JOIN #CONTRACT_DETAILS CD
                               ON SNDP.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                  AND SNDP.RS_MODEL_SID = CD.RS_MODEL_SID
                                  AND SNDP.PERIOD_SID = CD.PERIOD_SID'
					)

			EXEC sp_executesql @D_SQL
		END

		--------------------------------------------------------------------------------------------------------------------------------------- CONTRACT DETAILS METHODOLOGY ENDS HERE
		--------------------------------------------------------------------------------------------DEMAND PRICE CALCULATION
		------------------------------------------------------------------------------------------------------------------------------------------ EVALUATION RULE STARTED
		SET @D_SQL1 = CONCAT (
				'SELECT @DEDUCTION=1 FROM '
				,@REBATE_INFO
				,'    WHERE  EVALUATION_RULE IS NOT NULL
                             OR EVALUATION_RULE <> 0'
				)

		EXEC SP_EXECUTESQL @D_SQL1
			,N'@DEDUCTION INT OUTPUT'
			,@DEDUCTION = @DEDUCTION

		IF @DEDUCTION = 1
		BEGIN
			IF Object_id('TEMPDB..#EVALUATION_RULE') IS NOT NULL
				TRUNCATE TABLE #EVALUATION_RULE
			ELSE
				CREATE TABLE #EVALUATION_RULE (
					CCP_DETAILS_SID INT
					,RS_MODEL_SID INT
					,PERIOD_SID INT
					,CDR_MODEL_SID INT
					,EVALUATION_RULE INT
					,PROJECTION_AMOUNT NUMERIC(22, 6)
					,RS_PROJECTION_SALES NUMERIC(22, 6)
					,PROJECTION_SALES NUMERIC(22, 6)
					,PROJECTION_UNITS NUMERIC(22, 6)
					,REBATE_STRUCTURE VARCHAR(20)
					,PRICE_GROUP_TYPE VARCHAR(50)
					,KEYWORD VARCHAR(50)
					,OPERATOR VARCHAR(50)
					,COMPARISON VARCHAR(50)
					,LOGICAL_OPERATOR VARCHAR(50)
					,ITEM_GROUP_MS_ASSOCIATION VARCHAR(50)
					,LINE_TYPE VARCHAR(50)
					,VALUE NUMERIC(22, 6)
					,REBATE_FREQUENCY VARCHAR(20)
					,RULE_RESULT VARCHAR(10)
					)

			SET @D_SQL1 = CONCAT (
					' INSERT INTO #EVALUATION_RULE
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             CDR_MODEL_SID,
                             EVALUATION_RULE,
                             PROJECTION_AMOUNT,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             REBATE_STRUCTURE,
                             PRICE_GROUP_TYPE,
                             KEYWORD,
                             OPERATOR,
                             COMPARISON,
                             LOGICAL_OPERATOR,
                             ITEM_GROUP_MS_ASSOCIATION,
                             LINE_TYPE,
                             VALUE,
                             REBATE_FREQUENCY)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PERIOD_SID,
                       CDR_MODEL_SID,
                       EVALUATION_RULE,
                       PROJECTION_AMOUNT,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       REBATE_STRUCTURE,
                       PRICE_GROUP_TYPE,
                       KEYWORD,
                       OPERATOR,
                       COMPARISON,
                       LOGICAL_OPERATOR,
                       ITEM_GROUP_MS_ASSOCIATION,
                       LINE_TYPE,
                       VALUE=COALESCE(CASE COMPARISON
                                        WHEN ''GROSS TRADE SALES'' THEN Isnull(EXFACTORY_FORECAST_SALES, 0)  --PRODUCT_FILE
                                        WHEN ''DEMAND'' THEN Isnull(DEMAND_FORECAST_SALES, 0)
                                        WHEN ''INVENTORY WITHDRAWALS'' THEN Isnull(INVENTORY_FORECAST_SALES, 0)
                                        WHEN ''CONTRACT SALES'' THEN Isnull(PROJECTION_SALES, 0)
                                      END * ( VALUE / 100 ), VALUE),
                       REBATE_FREQUENCY
                FROM   (SELECT Row_number()
                                 OVER (
                                   PARTITION BY CD.CCP_DETAILS_SID, CD.RS_MODEL_SID, CD.PERIOD_SID, D.CDR_MODEL_SID, CD.EVALUATION_RULE
                                   ORDER BY CD.PERIOD_SID)RN,
                               CD.CCP_DETAILS_SID,
                               CD.RS_MODEL_SID,
                               CD.PERIOD_SID,
                               D.CDR_MODEL_SID,
                               CD.EVALUATION_RULE,
                               PROJECTION_AMOUNT,
                               PROJECTION_SALES,
                               PROJECTION_UNITS,
                               R.REBATE_STRUCTURE,
                               CD.PRICE_GROUP_TYPE,
                               H.DESCRIPTION              KEYWORD,
                              H1.DESCRIPTION             OPERATOR,
                               H2.DESCRIPTION             COMPARISON,
                               H3.DESCRIPTION             LOGICAL_OPERATOR,
                               H4.DESCRIPTION             ITEM_GROUP_MS_ASSOCIATION,
                               H5.DESCRIPTION             LINE_TYPE,
                               EXFACTORY_FORECAST_SALES,
                               DEMAND_FORECAST_SALES,
                               INVENTORY_FORECAST_SALES,
                               VALUE,
                               R.REBATE_FREQUENCY
                        FROM   #CONTRACT_DETAILS CD
                               INNER JOIN CDR_DETAILS D
                                       ON CD.EVALUATION_RULE = D.CDR_MODEL_SID
                               INNER JOIN '
					,@REBATE_INFO
					,' R
                                       ON R.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = CD.RS_MODEL_SID
                                          AND R.EVALUATION_RULE = CD.EVALUATION_RULE
                                                                       AND  R.EVALUATION_RULE IS NOT NULL  OR R.EVALUATION_RULE <> 0
                                                   INNER JOIN '
					,@MASTER_TABLE
					,'  SM
                                                   ON R.CCP_DETAILS_SID = SM.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = SM.RS_MODEL_SID
                                                                       AND SM.CHECK_RECORD=1 and  FILTER_CCP=1  ',case when @DEDUCTION_INCLUSION is not null then concat('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END,'
                               INNER JOIN CCP_DETAILS CCP
                                                    ON CCP.CCP_DETAILS_SID=SM.CCP_DETAILS_SID
                                                       INNER JOIN '
					,@PRODUCT_TABLE
					,
					' I
                                                       ON I.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
                                                         AND CD.PERIOD_SID=I.PERIOD_SID
                               LEFT JOIN HELPER_TABLE H
                                      ON D.KEYWORD = H.HELPER_TABLE_SID
                                         AND H.LIST_NAME = ''KEYWORD''
                               LEFT JOIN HELPER_TABLE H1
                                      ON D.OPERATOR = H1.HELPER_TABLE_SID
                                         AND H1.LIST_NAME = ''OPERATOR''
                               LEFT JOIN HELPER_TABLE H2
                                      ON D.COMPARISON = H2.HELPER_TABLE_SID
                                         AND H2.LIST_NAME = ''COMPARISON''
                               LEFT JOIN HELPER_TABLE H3
                                      ON D.LOGICAL_OPERATOR = H3.HELPER_TABLE_SID
                                         AND H3.LIST_NAME = ''LOGICAL_OPERATOR''
                               LEFT JOIN HELPER_TABLE H4
                                      ON D.ITEM_GROUP_MS_ASSOCIATION = H4.HELPER_TABLE_SID
                                         AND H4.LIST_NAME = ''ITEM_GROUP_MS_ASSOCIATION''
                               LEFT JOIN HELPER_TABLE H5
                                      ON D.LINE_TYPE = H5.HELPER_TABLE_SID
                                         AND H5.LIST_NAME = ''LINE_TYPE''
                        WHERE  H.DESCRIPTION = ''CONTRACT SALES'')A   WHERE  RN = 1'
					)

			---------------------------------------------------------------------------- 
			UPDATE RS
			SET RS_PROJECTION_SALES = RS_PROJECTION_AMOUNT
				,RULE_RESULT = CASE 
					WHEN PROJECTION_SALES = RS_PROJECTION_AMOUNT
						THEN 'PASS'
					WHEN VALUE = RS_PROJECTION_AMOUNT
						THEN 'FAIL'
					END
			FROM #EVALUATION_RULE RS
			CROSS APPLY (
				VALUES (
					'<'
					,Iif(Isnull(PROJECTION_SALES, 0) < VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'<='
					,Iif(Isnull(PROJECTION_SALES, 0) <= VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'='
					,Iif(Isnull(PROJECTION_SALES, 0) = VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'>'
					,Iif(Isnull(PROJECTION_SALES, 0) > VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'>='
					,Iif(Isnull(PROJECTION_SALES, 0) >= VALUE, PROJECTION_SALES, VALUE)
					)
				) AS CS(OPERATOR, RS_PROJECTION_AMOUNT)
			WHERE RS.OPERATOR = CS.OPERATOR

			SET @D_SQL1 = CONCAT (
					'SELECT @DEDUCTION=1 FROM '
					,@REBATE_INFO
					,'     WHERE  CALCULATION_TYPE = ''REBATE PLAN'''
					)

			EXEC SP_EXECUTESQL @D_SQL1
				,N'@DEDUCTION INT OUTPUT'
				,@DEDUCTION OUTPUT

			IF @DEDUCTION = 1
			BEGIN
				SET @D_SQL = CONCAT (
						' UPDATE SNDP
                      SET    SNDP.PROJECTION_SALES = CD.PROJECTION_AMOUNT,
                             SNDP.PROJECTION_RPU = CD.PROJECTION_RPU,
                             SNDP.PROJECTION_RATE = CD.PROJECTION_RATE
                      FROM   '
						,@PROJECTION_TABLE
						,
						' SNDP
                             INNER JOIN (SELECT CCP_DETAILS_SID,
                                                RS_MODEL_SID,
                                                PERIOD_SID,
                                                PROJECTION_AMOUNT=AMOUNT,
                                                PROJECTION_RATE=( AMOUNT / NULLIF(PROJECTION_SALES, 0) ) * 100,
                                                PROJECTION_RPU=( AMOUNT / NULLIF(PROJECTION_UNITS, 0) )
                                         FROM   (SELECT CCP_DETAILS_SID,
                                                        RS_MODEL_SID,
                                                        PERIOD_SID,
                                                        AMOUNT,
                                                        --=CASE
                                                        --                                                  WHEN REBATE_STRUCTURE = ''LEVEL'' THEN
                                                        --                                                     CASE
                                                        --                                                       WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN AMOUNT / 3
                                                        --                                                       WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN AMOUNT / 6
                                                        --                                                       WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN AMOUNT / 12
                                                        --                                                       WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN AMOUNT
                                                        --                                                    END
                                                        --                                                   WHEN REBATE_STRUCTURE IN( ''TIER'', ''FLAT'' ) THEN AMOUNT
                                                        --                                                 END,
                                                        PROJECTION_SALES,
                                                        PROJECTION_UNITS
                                                 FROM   (SELECT CCP_DETAILS_SID,
                                                                RS_MODEL_SID,
                                                                PERIOD_SID,
                                                                AMOUNT= CASE
                                                                          WHEN RULE_RESULT = ''PASS'' THEN PROJECTION_AMOUNT
                                                                          WHEN RULE_RESULT = ''FAIL'' THEN 0
                                                                        END,
                                                                REBATE_FREQUENCY,
                                                                REBATE_STRUCTURE,
                                                                PROJECTION_SALES,
                                                                PROJECTION_UNITS
                                                         FROM   #EVALUATION_RULE)A)B) CD
                                     ON SNDP.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                        AND SNDP.RS_MODEL_SID = CD.RS_MODEL_SID
                                        AND SNDP.PERIOD_SID = CD.PERIOD_SID'
						)

				EXEC sp_executesql @D_SQL
			END

			SET @D_SQL1 = CONCAT (
					'SELECT @DEDUCTION=1 FROM '
					,@REBATE_INFO
					,'     WHERE  CALCULATION_TYPE = ''DEDUCTION CALENDAR'''
					)

			EXEC SP_EXECUTESQL @D_SQL1
				,N'@DEDUCTION INT OUTPUT'
				,@DEDUCTION OUTPUT

			IF @DEDUCTION = 1
			BEGIN
				SET @D_SQL = CONCAT (
						'UPDATE SNDP
                      SET    SNDP.PROJECTION_SALES = CD.PROJECTION_AMOUNT,
                             SNDP.PROJECTION_RPU = CD.PROJECTION_RPU,
                             SNDP.PROJECTION_RATE = CD.PROJECTION_RATE
                      FROM   '
						,@PROJECTION_TABLE
						,
						' SNDP
                             INNER JOIN (SELECT CCP_DETAILS_SID,
                                                RS_MODEL_SID,
                                                PERIOD_SID,
                                                PROJECTION_AMOUNT,
                                                PROJECTION_RATE=( PROJECTION_AMOUNT / NULLIF(PROJECTION_SALES, 0) ) * 100,
                                                PROJECTION_RPU=( PROJECTION_AMOUNT / NULLIF(PROJECTION_UNITS, 0) )
                                         FROM   (SELECT CCP_DETAILS_SID,
                                                        RS_MODEL_SID,
                                                        PERIOD_SID,
                                                        PROJECTION_AMOUNT= CASE
                                                                             WHEN RULE_RESULT = ''PASS'' THEN RS_PROJECTION_SALES
                                                                             WHEN RULE_RESULT = ''FAIL'' THEN 0
                                                                           END,
                                                        PROJECTION_SALES,
                                                        PROJECTION_UNITS
                                                 FROM   #EVALUATION_RULE)A) CD
                                     ON SNDP.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                        AND SNDP.RS_MODEL_SID = CD.RS_MODEL_SID
                                        AND SNDP.PERIOD_SID = CD.PERIOD_SID
                                        '
						)

				EXEC sp_executesql @D_SQL
			END
		END

		------------------------------------------------------------------------------------------------------------------------------------  EVALUATION RULE ENDS
		----------------------------------------------------------------------------------------------------------------------------------------- CALCULATION RULE STARTED
		SET @D_SQL1 = CONCAT (
				'SELECT @DEDUCTION=1 FROM '
				,@REBATE_INFO
				,'    WHERE  CALCULATION_RULE IS NOT NULL
                             OR EVALUATION_RULE <> 0'
				)

		EXEC SP_EXECUTESQL @D_SQL1
			,N'@DEDUCTION INT OUTPUT'
			,@DEDUCTION OUTPUT

		IF @DEDUCTION = 1
		BEGIN
			IF Object_id('TEMPDB..#CALCULATION_RULE') IS NOT NULL
				TRUNCATE TABLE #CALCULATION_RULE
			ELSE
				CREATE TABLE #CALCULATION_RULE (
					CCP_DETAILS_SID INT
					,RS_MODEL_SID INT
					,PERIOD_SID INT
					,CDR_MODEL_SID INT
					,CALCULATION_RULE INT
					,PROJECTION_AMOUNT NUMERIC(22, 6)
					,PROJECTION_SALES NUMERIC(22, 6)
					,PROJECTION_UNITS NUMERIC(22, 6)
					,PRICE_GROUP_TYPE VARCHAR(50)
					,KEYWORD VARCHAR(50)
					,OPERATOR VARCHAR(50)
					,COMPARISON VARCHAR(50)
					,LOGICAL_OPERATOR VARCHAR(50)
					,ITEM_GROUP_MS_ASSOCIATION VARCHAR(50)
					,LINE_TYPE VARCHAR(50)
					,VALUE NUMERIC(22, 6)
					,REBATE_FREQUENCY VARCHAR(20)
					,REBATE_STRUCTURE VARCHAR(20)
					)

			SET @D_SQL1 = CONCAT (
					'   INSERT INTO #CALCULATION_RULE
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             CDR_MODEL_SID,
                             CALCULATION_RULE,
                             PROJECTION_AMOUNT,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PRICE_GROUP_TYPE,
                             KEYWORD,
                             OPERATOR,
                             COMPARISON,
                             LOGICAL_OPERATOR,
                             ITEM_GROUP_MS_ASSOCIATION,
                             LINE_TYPE,
                             VALUE,
                             REBATE_FREQUENCY,
                             REBATE_STRUCTURE)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PERIOD_SID,
                       CDR_MODEL_SID,
                       CALCULATION_RULE,
                       PROJECTION_AMOUNT,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       PRICE_GROUP_TYPE,
                       KEYWORD,
                       OPERATOR,
                       COMPARISON,
                       LOGICAL_OPERATOR,
                       ITEM_GROUP_MS_ASSOCIATION,
                       LINE_TYPE,
                       VALUE=COALESCE(CASE COMPARISON
                                        WHEN ''GROSS TRADE SALES'' THEN Isnull(EXFACTORY_FORECAST_SALES, 0) 
                                        WHEN ''DEMAND'' THEN Isnull(DEMAND_FORECAST_SALES, 0)
                                        WHEN ''INVENTORY WITHDRAWALS'' THEN Isnull(INVENTORY_FORECAST_SALES, 0)
                                        WHEN ''CONTRACT SALES'' THEN Isnull(PROJECTION_SALES, 0)
                                      END * ( VALUE / 100 ), VALUE),
                       REBATE_FREQUENCY,
                       REBATE_STRUCTURE
                FROM   (SELECT Row_number()
                                 OVER (
                                   PARTITION BY CD.CCP_DETAILS_SID, CD.RS_MODEL_SID, CD.PERIOD_SID, D.CDR_MODEL_SID, CD.CALCULATION_RULE
                                   ORDER BY CD.PERIOD_SID)RN,
                               CD.CCP_DETAILS_SID,
                               CD.RS_MODEL_SID,
                               CD.PERIOD_SID,
                               D.CDR_MODEL_SID,
                               CD.CALCULATION_RULE,
                               PROJECTION_AMOUNT,
                               PROJECTION_SALES,
                               PROJECTION_UNITS,
                               CD.PRICE_GROUP_TYPE,
                               H.DESCRIPTION              KEYWORD,
                               H1.DESCRIPTION             OPERATOR,
                               H2.DESCRIPTION             COMPARISON,
                               H3.DESCRIPTION             LOGICAL_OPERATOR,
                               H4.DESCRIPTION             ITEM_GROUP_MS_ASSOCIATION,
                               H5.DESCRIPTION             LINE_TYPE,
                               EXFACTORY_FORECAST_SALES,
                               DEMAND_FORECAST_SALES,
                               INVENTORY_FORECAST_SALES,
                               VALUE,
                               R.REBATE_FREQUENCY,
                               R.REBATE_STRUCTURE
                        FROM   #CONTRACT_DETAILS CD
                               INNER JOIN CDR_DETAILS D
                                       ON CD.CALCULATION_RULE = D.CDR_MODEL_SID
                               INNER JOIN '
					,@REBATE_INFO
					,' R
                                       ON R.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = CD.RS_MODEL_SID
                                          AND R.EVALUATION_RULE = CD.EVALUATION_RULE
                                                                       AND (  R.CALCULATION_RULE IS NOT NULL
                             OR R.EVALUATION_RULE <> 0)
                                                   INNER JOIN '
					,@MASTER_TABLE
					,'  SM
                                                   ON R.CCP_DETAILS_SID = SM.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = SM.RS_MODEL_SID
                                                                       AND SM.CHECK_RECORD=1 and  FILTER_CCP=1  ',case when @DEDUCTION_INCLUSION is not null then concat('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END,'
                               INNER JOIN CCP_DETAILS CCP
                                                    ON CCP.CCP_DETAILS_SID=SM.CCP_DETAILS_SID
                                                       INNER JOIN '
					,@PRODUCT_TABLE
					,
					' I
                                                       ON I.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
                                                         AND CD.PERIOD_SID=I.PERIOD_SID
                               LEFT JOIN HELPER_TABLE H
                                      ON D.KEYWORD = H.HELPER_TABLE_SID
                                         AND H.LIST_NAME = ''KEYWORD''
                               LEFT JOIN HELPER_TABLE H1
                                      ON D.OPERATOR = H1.HELPER_TABLE_SID
                                         AND H1.LIST_NAME = ''OPERATOR''
                               LEFT JOIN HELPER_TABLE H2
                                      ON D.COMPARISON = H2.HELPER_TABLE_SID
                                         AND H2.LIST_NAME = ''COMPARISON''
                               LEFT JOIN HELPER_TABLE H3
                                      ON D.LOGICAL_OPERATOR = H3.HELPER_TABLE_SID
                                         AND H3.LIST_NAME = ''LOGICAL_OPERATOR''
                               LEFT JOIN HELPER_TABLE H4
                                      ON D.ITEM_GROUP_MS_ASSOCIATION = H4.HELPER_TABLE_SID
                                         AND H4.LIST_NAME = ''ITEM_GROUP_MS_ASSOCIATION''
                               LEFT JOIN HELPER_TABLE H5
                                      ON D.LINE_TYPE = H5.HELPER_TABLE_SID
                                         AND H5.LIST_NAME = ''LINE_TYPE''
                        WHERE  H.DESCRIPTION = ''CONTRACT DEDUCTIONS'')A
                WHERE  RN = 1'
					)

			EXEC sp_executesql @D_SQL1

			------------------------------------------------------------------------------ 
			UPDATE RS
			SET PROJECTION_AMOUNT = RS_PROJECTION_AMOUNT
			FROM #CALCULATION_RULE RS
			CROSS APPLY (
				VALUES (
					'<'
					,Iif(Isnull(PROJECTION_AMOUNT, 0) < VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'<='
					,Iif(Isnull(PROJECTION_AMOUNT, 0) <= VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'='
					,Iif(Isnull(PROJECTION_AMOUNT, 0) = VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'>'
					,Iif(Isnull(PROJECTION_AMOUNT, 0) > VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'>='
					,Iif(Isnull(PROJECTION_AMOUNT, 0) >= VALUE, PROJECTION_AMOUNT, VALUE)
					)
				) AS CS(OPERATOR, RS_PROJECTION_AMOUNT)
			WHERE RS.OPERATOR = CS.OPERATOR

			SET @D_SQL1 = CONCAT (
					'SELECT @DEDUCTION=1 FROM '
					,@REBATE_INFO
					,'    WHERE  CALCULATION_TYPE = ''REBATE PLAN'''
					)

			EXEC SP_EXECUTESQL @D_SQL1
				,N'@DEDUCTION INT OUTPUT'
				,@DEDUCTION OUTPUT

			IF @DEDUCTION = 1
			BEGIN
				SET @D_SQL = CONCAT (
						'    UPDATE SNDP
                      SET    SNDP.PROJECTION_SALES = CD.PROJECTION_AMOUNT,
                             SNDP.PROJECTION_RPU = CD.PROJECTION_RPU,
                             SNDP.PROJECTION_RATE = CD.PROJECTION_RATE
                      FROM   '
						,@PROJECTION_TABLE
						,
						' SNDP
                             INNER JOIN (SELECT CCP_DETAILS_SID,
                                                RS_MODEL_SID,
                                                PERIOD_SID,
                                                PROJECTION_AMOUNT,
                                                PROJECTION_RATE=( PROJECTION_AMOUNT / NULLIF(PROJECTION_SALES, 0) ) * 100,
                                                PROJECTION_RPU=( PROJECTION_AMOUNT / NULLIF(PROJECTION_UNITS, 0) )
                                         FROM   (SELECT CCP_DETAILS_SID,
                                                        RS_MODEL_SID,
                                                        PERIOD_SID,
                                                        PROJECTION_AMOUNT,
                                                       
                                                        PROJECTION_SALES,
                                                        PROJECTION_UNITS
                                                 FROM   #CALCULATION_RULE)A) CD
                                     ON SNDP.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                        AND SNDP.RS_MODEL_SID = CD.RS_MODEL_SID
                                        AND SNDP.PERIOD_SID = CD.PERIOD_SID
                                        '
						)

				EXEC sp_executesql @D_SQL
			END

			SET @D_SQL1 = CONCAT (
					'SELECT @DEDUCTION=1 FROM '
					,@REBATE_INFO
					,'    WHERE  CALCULATION_TYPE = ''DEDUCTION CALENDAR'''
					)

			EXEC SP_EXECUTESQL @D_SQL1
				,N'@DEDUCTION INT OUTPUT'
				,@DEDUCTION OUTPUT

			IF @DEDUCTION = 1
			BEGIN
				SET @D_SQL = CONCAT (
						'   UPDATE SNDP
                      SET    SNDP.PROJECTION_SALES = CD.PROJECTION_AMOUNT,
                             SNDP.PROJECTION_RPU = CD.PROJECTION_RPU,
                             SNDP.PROJECTION_RATE = CD.PROJECTION_RATE
                      FROM   '
						,@PROJECTION_TABLE
						,' SNDP
                             INNER JOIN (SELECT CCP_DETAILS_SID,
                                                RS_MODEL_SID,
                                                PERIOD_SID,
                                                PROJECTION_AMOUNT,
                                                PROJECTION_RATE=( PROJECTION_AMOUNT / NULLIF(PROJECTION_SALES, 0) ) * 100,
                                                PROJECTION_RPU=( PROJECTION_AMOUNT / NULLIF(PROJECTION_UNITS, 0) )
                                         FROM   #CALCULATION_RULE) CD
                                     ON SNDP.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                        AND SNDP.RS_MODEL_SID = CD.RS_MODEL_SID
                                        AND SNDP.PERIOD_SID = CD.PERIOD_SID
                                      '
						)

				EXEC sp_executesql @D_SQL
			END
		END

		--------------------------------------------------------------------------------------------------------------------------------------  CALCULATION RULE ENDS
		---------------------------------------------------------------------------------------------------------------------------------------  NET SALES STARTED
		SET @D_SQL1 = CONCAT (
				'SELECT @DEDUCTION=1 FROM '
				,@REBATE_INFO
				,'  WHERE  ( RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                               OR RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL )
                             OR ( RP_NET_SALES_FORMULA_MASTER_SID <> 0
                                   OR RS_NET_SALES_FORMULA_MASTER_SID <> 0 )'
				)

		EXEC SP_EXECUTESQL @D_SQL1
			,N'@DEDUCTION INT OUTPUT'
			,@DEDUCTION OUTPUT

		IF @DEDUCTION = 1
		BEGIN
			IF Object_id('TEMPDB..#RP_NET_SALES_INFO') IS NOT NULL
				TRUNCATE TABLE #RP_NET_SALES_INFO
			ELSE
				CREATE TABLE #RP_NET_SALES_INFO (
					CCP_DETAILS_SID INT
					,RS_MODEL_SID INT
					,PERIOD_SID INT
					,CDR_MODEL_SID INT
					,RP_NET_SALES_FORMULA_MASTER_SID INT
					,CONTRACT_SELECTION VARCHAR(30)
					,NET_SALES_FORMULA_TYPE VARCHAR(50)
					,PROJECTION_AMOUNT NUMERIC(22, 6)
					,PROJECTION_SALES NUMERIC(22, 6)
					,PROJECTION_UNITS NUMERIC(22, 6)
					,RP_NET_SALES NUMERIC(22, 6)
					,PRICE_GROUP_TYPE VARCHAR(50)
					,KEYWORD VARCHAR(50)
					,OPERATOR VARCHAR(50)
					,COMPARISON VARCHAR(50)
					,LOGICAL_OPERATOR VARCHAR(50)
					,ITEM_GROUP_MS_ASSOCIATION VARCHAR(50)
					,LINE_TYPE VARCHAR(50)
					,VALUE NUMERIC(22, 6)
					)

			IF Object_id('TEMPDB..#RS_NET_SALES_INFO') IS NOT NULL
				TRUNCATE TABLE #RS_NET_SALES_INFO
			ELSE
				CREATE TABLE #RS_NET_SALES_INFO (
					CCP_DETAILS_SID INT
					,RS_MODEL_SID INT
					,PERIOD_SID INT
					,CDR_MODEL_SID INT
					,RS_NET_SALES_FORMULA_MASTER_SID INT
					,CONTRACT_SELECTION VARCHAR(30)
					,NET_SALES_FORMULA_TYPE VARCHAR(50)
					,PROJECTION_AMOUNT NUMERIC(22, 6)
					,PROJECTION_SALES NUMERIC(22, 6)
					,PROJECTION_UNITS NUMERIC(22, 6)
					,RS_NET_SALES NUMERIC(22, 6)
					,PRICE_GROUP_TYPE VARCHAR(50)
					,KEYWORD VARCHAR(50)
					,OPERATOR VARCHAR(50)
					,COMPARISON VARCHAR(50)
					,LOGICAL_OPERATOR VARCHAR(50)
					,ITEM_GROUP_MS_ASSOCIATION VARCHAR(50)
					,LINE_TYPE VARCHAR(50)
					,VALUE NUMERIC(22, 6)
					)

			IF Object_id('TEMPDB..#RP_NET_SALES_INFO_DEDUCTION') IS NOT NULL
				TRUNCATE TABLE #RP_NET_SALES_INFO_DEDUCTION
			ELSE
				CREATE TABLE #RP_NET_SALES_INFO_DEDUCTION (
					CCP_DETAILS_SID INT
					,RS_MODEL_SID INT
					,PERIOD_SID INT
					,CDR_MODEL_SID INT
					,RP_NET_SALES_FORMULA_MASTER_SID INT
					,CONTRACT_SELECTION VARCHAR(30)
					,NET_SALES_FORMULA_TYPE VARCHAR(50)
					,PROJECTION_AMOUNT NUMERIC(22, 6)
					,PROJECTION_SALES NUMERIC(22, 6)
					,PROJECTION_UNITS NUMERIC(22, 6)
					,RP_NET_SALES NUMERIC(22, 6)
					,PRICE_GROUP_TYPE VARCHAR(50)
					,KEYWORD VARCHAR(50)
					,OPERATOR VARCHAR(50)
					,COMPARISON VARCHAR(50)
					,LOGICAL_OPERATOR VARCHAR(50)
					,ITEM_GROUP_MS_ASSOCIATION VARCHAR(50)
					,LINE_TYPE VARCHAR(50)
					,INDICATOR VARCHAR(2)
					,VALUE NUMERIC(22, 6)
					)

			IF Object_id('TEMPDB..#RS_NET_SALES_INFO_DEDUCTION') IS NOT NULL
				TRUNCATE TABLE #RS_NET_SALES_INFO_DEDUCTION
			ELSE
				CREATE TABLE #RS_NET_SALES_INFO_DEDUCTION (
					CCP_DETAILS_SID INT
					,RS_MODEL_SID INT
					,PERIOD_SID INT
					,CDR_MODEL_SID INT
					,RS_NET_SALES_FORMULA_MASTER_SID INT
					,CONTRACT_SELECTION VARCHAR(30)
					,NET_SALES_FORMULA_TYPE VARCHAR(50)
					,PROJECTION_AMOUNT NUMERIC(22, 6)
					,PROJECTION_SALES NUMERIC(22, 6)
					,PROJECTION_UNITS NUMERIC(22, 6)
					,RS_NET_SALES NUMERIC(22, 6)
					,PRICE_GROUP_TYPE VARCHAR(50)
					,KEYWORD VARCHAR(50)
					,OPERATOR VARCHAR(50)
					,COMPARISON VARCHAR(50)
					,LOGICAL_OPERATOR VARCHAR(50)
					,ITEM_GROUP_MS_ASSOCIATION VARCHAR(50)
					,LINE_TYPE VARCHAR(50)
					,INDICATOR VARCHAR(2)
					,VALUE NUMERIC(22, 6)
					)

			------------------------------------------------------------------ SALES BASIS TAB --> NET SALES FORMULA (EXISTING CONTRACT) IN REBATE PLAN 
			SET @D_SQL1 = CONCAT (
					'  INSERT INTO #RP_NET_SALES_INFO
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             CDR_MODEL_SID,
                             RP_NET_SALES_FORMULA_MASTER_SID,
                             CONTRACT_SELECTION,
                             NET_SALES_FORMULA_TYPE,
                             PROJECTION_AMOUNT,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PRICE_GROUP_TYPE,
                             KEYWORD,
                             OPERATOR,
                             COMPARISON,
                             LOGICAL_OPERATOR,
                             ITEM_GROUP_MS_ASSOCIATION,
                             LINE_TYPE,
                             VALUE)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PERIOD_SID,
                       CDR_MODEL_SID,
                       RP_NET_SALES_FORMULA_MASTER_SID,
                       CONTRACT_SELECTION,
                       NET_SALES_FORMULA_TYPE,
                       PROJECTION_AMOUNT,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       PRICE_GROUP_TYPE,
                       KEYWORD,
                       OPERATOR,
                       COMPARISON,
                       LOGICAL_OPERATOR,
                       ITEM_GROUP_MS_ASSOCIATION,
                       LINE_TYPE,
                       VALUE=COALESCE(CASE COMPARISON
                                         WHEN ''GROSS TRADE SALES'' THEN Isnull(EXFACTORY_FORECAST_SALES, 0) 
                                        WHEN ''DEMAND'' THEN Isnull(DEMAND_FORECAST_SALES, 0)
                                        WHEN ''INVENTORY WITHDRAWALS'' THEN Isnull(INVENTORY_FORECAST_SALES, 0)
                                        WHEN ''CONTRACT SALES'' THEN Isnull(PROJECTION_SALES, 0)
                                      END * ( VALUE / 100 ), VALUE)
                FROM   (SELECT Row_number()
                                 OVER (
                                   PARTITION BY CD.CCP_DETAILS_SID, CD.RS_MODEL_SID, CD.PERIOD_SID, D.CDR_MODEL_SID, CD.RP_NET_SALES_FORMULA_MASTER_SID
                                   ORDER BY CD.PERIOD_SID)RN,
                               CD.CCP_DETAILS_SID,
                               CD.RS_MODEL_SID,
                               CD.PERIOD_SID,
                               D.CDR_MODEL_SID,
                               CD.RP_NET_SALES_FORMULA_MASTER_SID,
                               NSFM.CONTRACT_SELECTION,
                               H6.DESCRIPTION             NET_SALES_FORMULA_TYPE,
                               PROJECTION_AMOUNT,
                               PROJECTION_SALES,
                               PROJECTION_UNITS,
                               CD.PRICE_GROUP_TYPE,
                               H.DESCRIPTION              KEYWORD,
                               H1.DESCRIPTION             OPERATOR,
                               H2.DESCRIPTION             COMPARISON,
                               H3.DESCRIPTION             LOGICAL_OPERATOR,
                               H4.DESCRIPTION             ITEM_GROUP_MS_ASSOCIATION,
                               H5.DESCRIPTION             LINE_TYPE,
                              EXFACTORY_FORECAST_SALES,
                               DEMAND_FORECAST_SALES,
                               INVENTORY_FORECAST_SALES,
                               VALUE
                        FROM   #CONTRACT_DETAILS CD
                               LEFT JOIN NET_SALES_FORMULA_MASTER NSFM
                                      ON CD.RP_NET_SALES_FORMULA_MASTER_SID = NSFM.NET_SALES_FORMULA_MASTER_SID
                               INNER JOIN CDR_DETAILS D
                                       ON D.CDR_MODEL_SID = NSFM.CDR_MODEL_SID
                               
                               INNER JOIN '
					,@REBATE_INFO
					,' R
                                       ON R.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = CD.RS_MODEL_SID
                                          AND R.EVALUATION_RULE = CD.EVALUATION_RULE
                                                   INNER JOIN '
					,@MASTER_TABLE
					,'  SM
                                                   ON R.CCP_DETAILS_SID = SM.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = SM.RS_MODEL_SID
                                                                       AND SM.CHECK_RECORD=1 and  FILTER_CCP=1  ',case when @DEDUCTION_INCLUSION is not null then concat('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END,'
                               INNER JOIN CCP_DETAILS CCP
                                                    ON CCP.CCP_DETAILS_SID=SM.CCP_DETAILS_SID
                                                       INNER JOIN '
					,@PRODUCT_TABLE
					,
					' I
                                                       ON I.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
                                                         AND CD.PERIOD_SID=I.PERIOD_SID
                               LEFT JOIN HELPER_TABLE H
                                      ON D.KEYWORD = H.HELPER_TABLE_SID AND  H.DESCRIPTION = ''CONTRACT SALES''
                                         AND H.LIST_NAME = ''KEYWORD''
                               LEFT JOIN HELPER_TABLE H1
                                      ON D.OPERATOR = H1.HELPER_TABLE_SID
                                         AND H1.LIST_NAME = ''OPERATOR''
                               LEFT JOIN HELPER_TABLE H2
                                      ON D.COMPARISON = H2.HELPER_TABLE_SID
                                         AND H2.LIST_NAME = ''COMPARISON''
                               LEFT JOIN HELPER_TABLE H3
                                      ON D.LOGICAL_OPERATOR = H3.HELPER_TABLE_SID
                                         AND H3.LIST_NAME = ''LOGICAL_OPERATOR''
                               LEFT JOIN HELPER_TABLE H4
                                      ON D.ITEM_GROUP_MS_ASSOCIATION = H4.HELPER_TABLE_SID
                                         AND H4.LIST_NAME = ''ITEM_GROUP_MS_ASSOCIATION''
                               LEFT JOIN HELPER_TABLE H5
                                      ON D.LINE_TYPE = H5.HELPER_TABLE_SID
                                         AND H5.LIST_NAME = ''LINE_TYPE''
                               LEFT JOIN HELPER_TABLE H6
                                      ON NSFM.NET_SALES_FORMULA_TYPE = H6.HELPER_TABLE_SID
                                         AND H6.LIST_NAME = ''NS_FORMULA_TYPE''
                        WHERE 

                                           ( R.RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                               OR R.RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL )
                             OR ( R.RP_NET_SALES_FORMULA_MASTER_SID <> 0
                                   OR R.RS_NET_SALES_FORMULA_MASTER_SID <> 0 )
                                         
                                         
                                         )A
                WHERE  RN = 1'
					)

			EXEC sp_executesql @D_SQL1

			-----------------------------------------------------------------------------------------SALES BASIS TAB -->  NET SALES RULE (EXISTING CONTRACT) IN REBATE PLAN
			UPDATE RP
			SET PROJECTION_SALES = RP_CONTRACT_SALES
			FROM #RP_NET_SALES_INFO RP
			CROSS APPLY (
				VALUES (
					'<'
					,Iif(Isnull(PROJECTION_SALES, 0) < VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'<='
					,Iif(Isnull(PROJECTION_SALES, 0) <= VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'='
					,Iif(Isnull(PROJECTION_SALES, 0) = VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'>'
					,Iif(Isnull(PROJECTION_SALES, 0) > VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'>='
					,Iif(Isnull(PROJECTION_SALES, 0) >= VALUE, PROJECTION_SALES, VALUE)
					)
				) AS CS(OPERATOR, RP_CONTRACT_SALES)
			WHERE RP.OPERATOR = CS.OPERATOR

			--------------------------------------------------------------------------------------- SALES BASIS TAB --> NET SALES FORMULA (EXISTING CONTRACT) IN REBATE SCHEDULE 
			SET @D_SQL1 = CONCAT (
					'   INSERT INTO #RS_NET_SALES_INFO
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             CDR_MODEL_SID,
                             RS_NET_SALES_FORMULA_MASTER_SID,
                             CONTRACT_SELECTION,
                             NET_SALES_FORMULA_TYPE,
                             PROJECTION_AMOUNT,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PRICE_GROUP_TYPE,
                             KEYWORD,
                             OPERATOR,
                             COMPARISON,
                             LOGICAL_OPERATOR,
                             ITEM_GROUP_MS_ASSOCIATION,
                             LINE_TYPE,
                             VALUE)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PERIOD_SID,
                       CDR_MODEL_SID,
                       RS_NET_SALES_FORMULA_MASTER_SID,
                       CONTRACT_SELECTION,
                       NET_SALES_FORMULA_TYPE,
                       PROJECTION_AMOUNT,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       PRICE_GROUP_TYPE,
                       KEYWORD,
                       OPERATOR,
                       COMPARISON,
                       LOGICAL_OPERATOR,
                       ITEM_GROUP_MS_ASSOCIATION,
                       LINE_TYPE,
                       VALUE=COALESCE(CASE COMPARISON
                                        WHEN ''GROSS TRADE SALES'' THEN Isnull(EXFACTORY_FORECAST_SALES, 0)
                                        WHEN ''DEMAND'' THEN Isnull(DEMAND_FORECAST_SALES, 0)
                                        WHEN ''INVENTORY WITHDRAWALS'' THEN Isnull(INVENTORY_FORECAST_SALES, 0)
                                        WHEN ''CONTRACT SALES'' THEN Isnull(PROJECTION_SALES, 0)
                                      END * ( VALUE / 100 ), VALUE)
                FROM   (SELECT Row_number()
                                 OVER (
                                   PARTITION BY CD.CCP_DETAILS_SID, CD.RS_MODEL_SID, CD.PERIOD_SID, D.CDR_MODEL_SID, CD.RS_NET_SALES_FORMULA_MASTER_SID
                                   ORDER BY CD.PERIOD_SID)RN,
                               CD.CCP_DETAILS_SID,
                               CD.RS_MODEL_SID,
                               CD.PERIOD_SID,
                               D.CDR_MODEL_SID,
                              CD.RS_NET_SALES_FORMULA_MASTER_SID,
                               NSFM.CONTRACT_SELECTION,
                               H6.DESCRIPTION             NET_SALES_FORMULA_TYPE,
                               PROJECTION_AMOUNT,
                               PROJECTION_SALES,
                               PROJECTION_UNITS,
                               CD.PRICE_GROUP_TYPE,
                               H.DESCRIPTION              KEYWORD,
                               H1.DESCRIPTION             OPERATOR,
                               H2.DESCRIPTION             COMPARISON,
                               H3.DESCRIPTION             LOGICAL_OPERATOR,
                               H4.DESCRIPTION             ITEM_GROUP_MS_ASSOCIATION,
                               H5.DESCRIPTION             LINE_TYPE,
                              EXFACTORY_FORECAST_SALES,
                               DEMAND_FORECAST_SALES,
                               INVENTORY_FORECAST_SALES,
                               VALUE
                        FROM   #CONTRACT_DETAILS CD
                               LEFT JOIN NET_SALES_FORMULA_MASTER NSFM
                                      ON CD.RS_NET_SALES_FORMULA_MASTER_SID = NSFM.NET_SALES_FORMULA_MASTER_SID
                               INNER JOIN CDR_DETAILS D
                                       ON D.CDR_MODEL_SID = NSFM.CDR_MODEL_SID
                               INNER JOIN '
					,@REBATE_INFO
					,' R
                                       ON R.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = CD.RS_MODEL_SID
                                          AND R.EVALUATION_RULE = CD.EVALUATION_RULE
                                                                     
                                                   INNER JOIN '
					,@MASTER_TABLE
					,'  SM
                                                   ON R.CCP_DETAILS_SID = SM.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = SM.RS_MODEL_SID
                                                                       AND SM.CHECK_RECORD=1 and  FILTER_CCP=1  ',case when @DEDUCTION_INCLUSION is not null then concat('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END,'
                               INNER JOIN CCP_DETAILS CCP
                                                    ON CCP.CCP_DETAILS_SID=SM.CCP_DETAILS_SID
                                                       INNER JOIN '
					,@PRODUCT_TABLE
					,
					' I
                                                       ON I.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
                                                         AND CD.PERIOD_SID=I.PERIOD_SID
                               LEFT JOIN HELPER_TABLE H
                                      ON D.KEYWORD = H.HELPER_TABLE_SID AND  H.DESCRIPTION = ''CONTRACT SALES''
                                         
                                         
                                         AND H.LIST_NAME = ''KEYWORD''
                               LEFT JOIN HELPER_TABLE H1
                                      ON D.OPERATOR = H1.HELPER_TABLE_SID
                                         AND H1.LIST_NAME = ''OPERATOR''
                               LEFT JOIN HELPER_TABLE H2
                                      ON D.COMPARISON = H2.HELPER_TABLE_SID
                                         AND H2.LIST_NAME = ''COMPARISON''
                               LEFT JOIN HELPER_TABLE H3
                                      ON D.LOGICAL_OPERATOR = H3.HELPER_TABLE_SID
                                         AND H3.LIST_NAME = ''LOGICAL_OPERATOR''
                               LEFT JOIN HELPER_TABLE H4
                                      ON D.ITEM_GROUP_MS_ASSOCIATION = H4.HELPER_TABLE_SID
                                         AND H4.LIST_NAME = ''ITEM_GROUP_MS_ASSOCIATION''
                               LEFT JOIN HELPER_TABLE H5
                                      ON D.LINE_TYPE = H5.HELPER_TABLE_SID
                                         AND H5.LIST_NAME = ''LINE_TYPE''
                               LEFT JOIN HELPER_TABLE H6
                                      ON NSFM.NET_SALES_FORMULA_TYPE = H6.HELPER_TABLE_SID
                                         AND H6.LIST_NAME = ''NS_FORMULA_TYPE''
                        WHERE  ( R.RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                               OR R.RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL )
                             OR ( R.RP_NET_SALES_FORMULA_MASTER_SID <> 0
                                   OR R.RS_NET_SALES_FORMULA_MASTER_SID <> 0 )
                                         
                                         )A
                WHERE  RN = 1'
					)

			EXEC sp_executesql @D_SQL1

			-----------------------------------------------------------------------------------------SALES BASIS TAB -->  NET SALES RULE (EXISTING CONTRACT) IN REBATE SCHEDULE
			UPDATE RS
			SET PROJECTION_SALES = RS_CONTRACT_SALES
			FROM #RS_NET_SALES_INFO RS
			CROSS APPLY (
				VALUES (
					'<'
					,Iif(Isnull(PROJECTION_SALES, 0) < VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'<='
					,Iif(Isnull(PROJECTION_SALES, 0) <= VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'='
					,Iif(Isnull(PROJECTION_SALES, 0) = VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'>'
					,Iif(Isnull(PROJECTION_SALES, 0) > VALUE, PROJECTION_SALES, VALUE)
					)
					,(
					'>='
					,Iif(Isnull(PROJECTION_SALES, 0) >= VALUE, PROJECTION_SALES, VALUE)
					)
				) AS CS(OPERATOR, RS_CONTRACT_SALES)
			WHERE RS.OPERATOR = CS.OPERATOR

			---------------------------------------------------------------------------------------------------------------------------------------------SALES BASIS TAB --> NET SALES (SELECTED CONTRACT) MAIN BLOCK
			IF Object_id('TEMPDB..#SALES_BASIS') IS NOT NULL
				DROP TABLE #SALES_BASIS

			IF Object_id('TEMPDB..#CCP') IS NOT NULL
				TRUNCATE TABLE #CCP
			ELSE
				CREATE TABLE #CCP (
					CCP_DETAILS_SID INT
					,CONTRACT_MASTER_SID INT
					,COMPANY_MASTER_SID INT
					,ITEM_MASTER_SID INT
					,RS_MODEL_SID INT
					,REBATE_PROGRAM_TYPE VARCHAR(30)
					,RS_NSF INT
					,RP_NSF INT
					,REBATE_PLAN_MASTER_SID INT
					)

			SET @D_SQL = CONCAT (
					';

                WITH CCP
                     AS (SELECT DISTINCT CD. CCP_DETAILS_SID,
                                       
                                         CC. CONTRACT_MASTER_SID,
                                         CCD.COMPANY_MASTER_SID,
                                         ICD.ITEM_MASTER_SID,
                                         RC. RS_MODEL_SID,
                                         REBATE_PROGRAM_TYPE= CASE
                                                                WHEN HT.DESCRIPTION = ''REIMB'' THEN ''REIMBURSEMENT''
                                                                WHEN HT.DESCRIPTION = ''OI'' THEN ''OFF INVOICE''
                                                                ELSE HT.DESCRIPTION
                                                              END,
                                         RCD.NET_SALES_FORMULA_MASTER_SID AS RS_NSF,
                                         RPM.NET_SALES_FORMULA_MASTER_SID AS RP_NSF,
                                         RCD.REBATE_PLAN_MASTER_SID
                         FROM   CFP_CONTRACT CC
                                INNER JOIN CFP_CONTRACT_DETAILS CCD
                                        ON CC.CFP_CONTRACT_SID = CCD.CFP_CONTRACT_SID
                                INNER JOIN IFP_CONTRACT IC
                                        ON IC.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
                                           AND IC.CONTRACT_MASTER_SID = CC.CONTRACT_MASTER_SID
                                INNER JOIN IFP_CONTRACT_DETAILS ICD
                                        ON ICD.IFP_CONTRACT_SID = IC.IFP_CONTRACT_SID
                                INNER JOIN RS_CONTRACT RC
                                        ON RC.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
                                           AND RC.IFP_CONTRACT_SID = RC.IFP_CONTRACT_SID
                                           AND RC.CONTRACT_MASTER_SID = CC.CONTRACT_MASTER_SID
                                INNER JOIN RS_CONTRACT_DETAILS RCD
                                        ON RCD.RS_CONTRACT_SID = RC.RS_CONTRACT_SID
                                           AND RCD.ITEM_MASTER_SID = ICD.ITEM_MASTER_SID
                                INNER JOIN HELPER_TABLE CT
                                        ON RC.CALCULATION_TYPE = CT.HELPER_TABLE_SID
                                INNER JOIN CCP_DETAILS CD
                                        ON CCD.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID
                                           AND CD.CONTRACT_MASTER_SID = CC.CONTRACT_MASTER_SID
                                           AND CD.ITEM_MASTER_SID = ICD.ITEM_MASTER_SID
                                
                                LEFT OUTER JOIN HELPER_TABLE HT
                                             ON HT.HELPER_TABLE_SID = RC.REBATE_PROGRAM_TYPE
                                                --AND HT.LIST_NAME = REBATE_PROGRAM_TYPE
                                LEFT OUTER JOIN REBATE_PLAN_MASTER RPM
                                             ON RPM.REBATE_PLAN_MASTER_SID = RCD.REBATE_PLAN_MASTER_SID
                         WHERE  EXISTS (SELECT 1
                                        FROM   '
					,@MASTER_TABLE
					,
					' NDPM
                                        WHERE   METHODOLOGY = ''CONTRACT DETAILS''
                                               AND CHECK_RECORD = 1 and  FILTER_CCP=1  ',case when @DEDUCTION_INCLUSION is not null then concat('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END,'
                                             )
                                AND RC.CALCULATION_TYPE IS NOT NULL)
                INSERT INTO #CCP
                            (CCP_DETAILS_SID,
                           
                             CONTRACT_MASTER_SID,
                             COMPANY_MASTER_SID,
                             ITEM_MASTER_SID,
                             RS_MODEL_SID,
                             REBATE_PROGRAM_TYPE,
                             RS_NSF,
                             RP_NSF,
                             REBATE_PLAN_MASTER_SID)
                SELECT CCP_DETAILS_SID,
                       CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID,
                       RS_MODEL_SID,
                       REBATE_PROGRAM_TYPE,
                       RS_NSF,
                       RP_NSF,
                       REBATE_PLAN_MASTER_SID
                FROM   CCP'
					)

			EXEC sp_executesql @D_SQL;

			WITH SELECT_CONTRACT
			AS (
				SELECT DISTINCT CC.CONTRACT_MASTER_SID
					,CCD.COMPANY_MASTER_SID
					,ICD.ITEM_MASTER_SID
					,SSD.CDR_MODEL_SID
					,NSF.NET_SALES_FORMULA_MASTER_SID
					,NSF.CONTRACT_SELECTION
				FROM NET_SALES_FORMULA_MASTER NSF
				INNER JOIN SALES_BASIS_DETAILS SSD ON NSF.NET_SALES_FORMULA_MASTER_SID = SSD.NET_SALES_FORMULA_MASTER_SID
				INNER JOIN CFP_CONTRACT CC ON CC.CONTRACT_MASTER_SID = SSD.CONTRACT_MASTER_SID
				INNER JOIN CFP_CONTRACT_DETAILS CCD ON SSD.CFP_CONTRACT_DETAILS_SID = CCD.CFP_CONTRACT_DETAILS_SID
					AND CC.CFP_CONTRACT_SID = CCD.CFP_CONTRACT_SID
				INNER JOIN IFP_CONTRACT IC ON IC.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
					AND IC.CONTRACT_MASTER_SID = CC.CONTRACT_MASTER_SID
				INNER JOIN IFP_CONTRACT_DETAILS ICD ON ICD.IFP_CONTRACT_SID = IC.IFP_CONTRACT_SID
				WHERE NSF.CONTRACT_SELECTION LIKE 'SELECT CONTRACT%'
				)
			SELECT c.CCP_DETAILS_SID
				,C.CONTRACT_MASTER_SID
				,C.COMPANY_MASTER_SID
				,C.ITEM_MASTER_SID
				,RS_MODEL_SID
				,REBATE_PROGRAM_TYPE
				,RS.CONTRACT_MASTER_SID AS RS_CONTRACT_MASTER_SID
				,RS.ITEM_MASTER_SID AS RS_ITEM_MASTER_SID
				,RS.COMPANY_MASTER_SID RS_COMPANY_MASTER_SID
				,RS.CDR_MODEL_SID AS RS_CDR_MODEL_SID
				,RP.CONTRACT_MASTER_SID AS RP_CONTRACT_MASTER_SID
				,RP.ITEM_MASTER_SID AS RP_ITEM_MASTER_SID
				,RP.COMPANY_MASTER_SID RP_COMPANY_MASTER_SID
				,RP.CDR_MODEL_SID AS RP_CDR_MODEL_SID
				,RS.CONTRACT_SELECTION AS RS_CONTRACT_SELECTION
				,RP.CONTRACT_SELECTION AS RP_CONTRACT_SELECTION
			INTO #SALES_BASIS -- NOTE : THIS MAY HAVE DUPLICATE SINCE ITEM CAN BE PRESENT IN MORE THAN ONE CONTRACT/CUSTOMER
			FROM #CCP C
			LEFT OUTER JOIN SELECT_CONTRACT RS ON C.ITEM_MASTER_SID = RS.ITEM_MASTER_SID
				AND C.RS_NSF = RS.NET_SALES_FORMULA_MASTER_SID
			LEFT OUTER JOIN SELECT_CONTRACT RP ON C.ITEM_MASTER_SID = RP.ITEM_MASTER_SID
				AND C.RP_NSF = RP.NET_SALES_FORMULA_MASTER_SID

			-----------------------------------------------------------------------------REBATE PLAN (NSF) PULLING SALES FOR SELECT CONTRACT IN SALES BASIS TAB
			IF Object_id('TEMPDB..#SELECT_CONTRACT_SALES') IS NOT NULL
				TRUNCATE TABLE #SELECT_CONTRACT_SALES;
			ELSE
				CREATE TABLE #SELECT_CONTRACT_SALES (
					CCP_DETAILS_SID INT
					,PERIOD_SID INT
					,RP_PROJECTION_SALES NUMERIC(22, 6)
					,RS_PROJECTION_SALES NUMERIC(22, 6)
					)

			SET @D_SQL = CONCAT (
					'  INSERT INTO #SELECT_CONTRACT_SALES
                            (CCP_DETAILS_SID,
                             PERIOD_SID)
                SELECT CCP_DETAILS_SID,
                       PERIOD_SID
                FROM   '
					,@MASTER_TABLE
					,' PD
                       CROSS JOIN #PERIOD_CD P
                WHERE   METHODOLOGY = ''CONTRACT DETAILS''
                                      AND P.PERIOD_SID BETWEEN ',@FORECAST_START_PERIOD_SID,' AND COALESCE(',@FORECAST_END_PERIOD_SID,','
					,@PROJECTION_END_PERIOD_SID
					,')
                       '
					)

			EXEC sp_executesql @D_SQL;

			WITH CTE
			AS (
				SELECT DISTINCT CCP_DETAILS_SID
					,RP_CONTRACT_MASTER_SID
					,RP_COMPANY_MASTER_SID
					,RP_ITEM_MASTER_SID
				FROM #SALES_BASIS
				)
				,APPROVED_PROJ_DETAILS --TO FIND APPROVED PROJECTION FOR MATCHED ITEM'S 
			AS (
				SELECT RP_CONTRACT_MASTER_SID
					,RP_COMPANY_MASTER_SID
					,RP_ITEM_MASTER_SID
					,RP_PROJECTION_DETAILS_SID
					,C.CCP_DETAILS_SID
				FROM CTE C
				CROSS APPLY (
					SELECT TOP 1 PD.PROJECTION_DETAILS_SID AS RP_PROJECTION_DETAILS_SID
					FROM WORKFLOW_MASTER WM
					INNER JOIN HELPER_TABLE HT ON WM.WORKFLOW_STATUS_ID = HT.HELPER_TABLE_SID
					INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
					INNER JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
					WHERE HT.DESCRIPTION = 'APPROVED'
						AND EXISTS (
							SELECT 1
							FROM PROJECTION_MASTER PM
							WHERE PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
								AND FORECASTING_TYPE = @FORECASTING_TYPE
							)
						AND CD.COMPANY_MASTER_SID = C.RP_COMPANY_MASTER_SID
						AND CD.CONTRACT_MASTER_SID = C.RP_CONTRACT_MASTER_SID
						AND CD.ITEM_MASTER_SID = C.RP_ITEM_MASTER_SID
					ORDER BY WM.MODIFIED_DATE DESC
					) CS
				)
			UPDATE SCS
			SET SCS.RP_PROJECTION_SALES = A.PROJECTION_SALES
			FROM #SELECT_CONTRACT_SALES SCS
			INNER JOIN (
				SELECT APD.CCP_DETAILS_SID
					,Sum(PROJECTION_SALES) AS PROJECTION_SALES
					,PERIOD_SID
				FROM NM_SALES_PROJECTION NSP
				INNER JOIN APPROVED_PROJ_DETAILS APD ON APD.RP_PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
				GROUP BY APD.CCP_DETAILS_SID
					,PERIOD_SID
				) A ON A.CCP_DETAILS_SID = SCS.CCP_DETAILS_SID
				AND A.PERIOD_SID = SCS.PERIOD_SID
				----------------------------------------------------------------------------------------------REBATE SCHEDULE (NSF) PULLING SALES FOR SELECT CONTRACT IN SALES BASIS TAB
				;

			WITH CTE
			AS (
				SELECT DISTINCT CCP_DETAILS_SID
					,RS_CONTRACT_MASTER_SID
					,RS_COMPANY_MASTER_SID
					,RS_ITEM_MASTER_SID
				FROM #SALES_BASIS
				)
				,APPROVED_PROJ_DETAILS --TO FIND APPROVED PROJECTION FOR MATCHED ITEM'S
			AS (
				SELECT RS_CONTRACT_MASTER_SID
					,RS_COMPANY_MASTER_SID
					,RS_ITEM_MASTER_SID
					,RP_PROJECTION_DETAILS_SID
					,C.CCP_DETAILS_SID
				FROM CTE C
				CROSS APPLY (
					SELECT TOP 1 PD.PROJECTION_DETAILS_SID AS RP_PROJECTION_DETAILS_SID
					FROM WORKFLOW_MASTER WM
					INNER JOIN HELPER_TABLE HT ON WM.WORKFLOW_STATUS_ID = HT.HELPER_TABLE_SID
					INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
					INNER JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
					WHERE HT.DESCRIPTION = 'APPROVED'
						AND EXISTS (
							SELECT 1
							FROM PROJECTION_MASTER PM
							WHERE PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
								AND FORECASTING_TYPE = @FORECASTING_TYPE
							)
						AND CD.COMPANY_MASTER_SID = C.RS_COMPANY_MASTER_SID
						AND CD.CONTRACT_MASTER_SID = C.RS_CONTRACT_MASTER_SID
						AND CD.ITEM_MASTER_SID = C.RS_ITEM_MASTER_SID
					ORDER BY WM.MODIFIED_DATE DESC
					) CS
				)
			UPDATE SCS
			SET SCS.RS_PROJECTION_SALES = A.PROJECTION_SALES
			FROM #SELECT_CONTRACT_SALES SCS
			INNER JOIN (
				SELECT APD.CCP_DETAILS_SID
					,Sum(PROJECTION_SALES) AS PROJECTION_SALES
					,-- SUM USED TO SUM THE ITEM'S IS PRESENT IN MORE THAN ONE CONTRACT/CUSTOMER
					PERIOD_SID
				FROM NM_SALES_PROJECTION NSP
				INNER JOIN APPROVED_PROJ_DETAILS APD ON APD.RP_PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
				GROUP BY APD.CCP_DETAILS_SID
					,PERIOD_SID
				) A ON A.CCP_DETAILS_SID = SCS.CCP_DETAILS_SID
				AND A.PERIOD_SID = SCS.PERIOD_SID

			----------------------------------------------------------------------------------------- SALES BASIS TAB --> NET SALES RULE (SELECTED CONTRACT) IN REBATE PLAN
			IF Object_id('TEMPDB..#RP_SALES_BASIS') IS NOT NULL
				TRUNCATE TABLE #RP_SALES_BASIS
			ELSE
				CREATE TABLE #RP_SALES_BASIS (
					CCP_DETAILS_SID INT
					,RS_MODEL_SID INT
					,PERIOD_SID INT
					,RP_CONTRACT_SELECTION VARCHAR(20)
					,RP_CONTRACT_SALES NUMERIC(22, 6)
					)

			SET @D_SQL1 = CONCAT (
					'       ;

                WITH CTE
                     AS (SELECT SB.CCP_DETAILS_SID,
                                SB.RS_MODEL_SID,
                                SCS.PERIOD_SID,
                                SB.REBATE_PROGRAM_TYPE,
                                RP_PROJECTION_SALES,
                                EXFACTORY_FORECAST_SALES                  AS GROSS_TRADE_SALES,
                                DEMAND_FORECAST_SALES                    AS DEMAND_SALES,
                                INVENTORY_FORECAST_SALES                AS INVENTORY_SALES,
                                KEYWORD,
                                OPERATOR,
                                VALUE,
                                COMPARISON,
                                COALESCE(CASE COMPARISON
                                           WHEN ''GROSS TRADE SALES'' THEN Isnull(EXFACTORY_FORECAST_SALES, 0) --PRODUCT_FILE
                                           WHEN ''DEMAND'' THEN Isnull(DEMAND_FORECAST_SALES, 0)
                                           WHEN ''INVENTORY WITHDRAWALS'' THEN Isnull(INVENTORY_FORECAST_SALES, 0)
                                           WHEN ''CONTRACT SALES'' THEN Isnull(PROJECTION_SALES, 0)
                                         END * ( VALUE / 100 ), VALUE) CHECK_VALUE,
                                LOGICAL_OPERTAOR,
                                RP_CONTRACT_SELECTION
                         FROM   #SALES_BASIS SB
                                INNER JOIN #SELECT_CONTRACT_SALES SCS
                                        ON SCS.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
                                 
                               INNER JOIN CCP_DETAILS CCP
                                                    ON CCP.CCP_DETAILS_SID=SB.CCP_DETAILS_SID
                                                       
                                INNER JOIN #CONTRACT_DETAILS CDPS
                                        ON CDPS.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
                                           AND CDPS.RS_MODEL_SID = SB.RS_MODEL_SID
                                           AND CDPS.PERIOD_SID = SCS.PERIOD_SID
                                                                     INNER JOIN '
					,@PRODUCT_TABLE
					,
					' I
                                                       ON I.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
                                                         AND CDPS.PERIOD_SID=SCS.PERIOD_SID
                                CROSS APPLY (SELECT TOP 1 CDR_DETAILS_SID,
                                                          CDR_MODEL_SID,
                                                          KE.DESCRIPTION AS KEYWORD,
                                                          OP.DESCRIPTION AS OPERATOR,
                                                          VALUE,
                                                          CO.DESCRIPTION AS COMPARISON,
                                                          LO.DESCRIPTION AS LOGICAL_OPERTAOR
                                             FROM   CDR_DETAILS CD
                                                    INNER JOIN HELPER_TABLE KE
                                                            ON CD.KEYWORD = KE.HELPER_TABLE_SID
                                                    INNER JOIN HELPER_TABLE OP
                                                            ON CD.OPERATOR = OP.HELPER_TABLE_SID
                                                    LEFT JOIN HELPER_TABLE CO
                                                           ON CD.COMPARISON = CO.HELPER_TABLE_SID
                                                    LEFT JOIN HELPER_TABLE LO
                                                           ON LO.HELPER_TABLE_SID = CD.LOGICAL_OPERATOR
                                             WHERE  KE.DESCRIPTION = ''CONTRACT SALES''
                                                    AND SB.RP_CDR_MODEL_SID = CD.CDR_MODEL_SID --RP
                                             ORDER  BY CD.CDR_DETAILS_SID ASC) CD),
                     FINAL_SALES
                     AS (SELECT CCP_DETAILS_SID,
                                RS_MODEL_SID,
                                PERIOD_SID,
                                RP_CONTRACT_SELECTION,
                                RP_CONTRACT_SALES
                         FROM   CTE C
                                CROSS APPLY ( VALUES (''<'',
                                            Iif(Isnull(RP_PROJECTION_SALES, 0) < CHECK_VALUE, RP_PROJECTION_SALES, CHECK_VALUE)),
                                                     (''<='',
                                            Iif(Isnull(RP_PROJECTION_SALES, 0) <= CHECK_VALUE, RP_PROJECTION_SALES, CHECK_VALUE) ),
                                                     (''='',
                                            Iif(Isnull(RP_PROJECTION_SALES, 0) = CHECK_VALUE, RP_PROJECTION_SALES, CHECK_VALUE)),
                                                     (''>'',
                                            Iif(Isnull(RP_PROJECTION_SALES, 0) > CHECK_VALUE, RP_PROJECTION_SALES, CHECK_VALUE)),
                                                     (''>='',
                                            Iif(Isnull(RP_PROJECTION_SALES, 0) >= CHECK_VALUE, RP_PROJECTION_SALES, CHECK_VALUE)) ) AS CS(OPERATOR, RP_CONTRACT_SALES)
                         WHERE  C.OPERATOR = CS.OPERATOR)
                INSERT INTO #RP_SALES_BASIS
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             RP_CONTRACT_SELECTION,
                             RP_CONTRACT_SALES)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PERIOD_SID,
                       RP_CONTRACT_SELECTION,
                       RP_CONTRACT_SALES
                FROM   FINAL_SALES'
					)

			EXEC sp_executesql @D_SQL1

			------------------------------------------------------------------------------------------  SALES BASIS TAB --> SELECTED AND EXISTING CONTRACT IN REBATE PLAN
			UPDATE RP
			SET PROJECTION_SALES = RP_CONTRACT_SALES
			FROM #RP_NET_SALES_INFO RP
			INNER JOIN #RP_SALES_BASIS SB ON RP.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
				AND RP.PERIOD_SID = SB.PERIOD_SID
				AND RP.CONTRACT_SELECTION = SB.RP_CONTRACT_SELECTION

			----------------------------------------------------------------------------------------- SALES BASIS TAB --> NET SALES RULE (SELECTED CONTRACT) IN REBATE SCHEDULE
			IF Object_id('TEMPDB..#RS_SALES_BASIS') IS NOT NULL
				TRUNCATE TABLE #RS_SALES_BASIS
			ELSE
				CREATE TABLE #RS_SALES_BASIS (
					CCP_DETAILS_SID INT
					,RS_MODEL_SID INT
					,PERIOD_SID INT
					,RS_CONTRACT_SELECTION VARCHAR(20)
					,RS_CONTRACT_SALES NUMERIC(22, 6)
					)

			SET @D_SQL1 = CONCAT (
					'              ;

                WITH CTE
                     AS (SELECT SB.CCP_DETAILS_SID,
                                SB.RS_MODEL_SID,
                                SCS.PERIOD_SID,
                                SB.REBATE_PROGRAM_TYPE,
                                RP_PROJECTION_SALES,
                                EXFACTORY_FORECAST_SALES                  AS GROSS_TRADE_SALES,
                                DEMAND_FORECAST_SALES                    AS DEMAND_SALES,
                                INVENTORY_FORECAST_SALES                AS INVENTORY_SALES,
                                KEYWORD,
                                OPERATOR,
                                VALUE,
                                COMPARISON,
                                COALESCE(CASE COMPARISON
                                           WHEN ''GROSS TRADE SALES'' THEN Isnull(EXFACTORY_FORECAST_SALES, 0) --PRODUCT_FILE
                                           WHEN ''DEMAND'' THEN Isnull(DEMAND_FORECAST_SALES, 0)
                                           WHEN ''INVENTORY WITHDRAWALS'' THEN Isnull(INVENTORY_FORECAST_SALES, 0)
                                           WHEN ''CONTRACT SALES'' THEN Isnull(PROJECTION_SALES, 0)
                                         END * ( VALUE / 100 ), VALUE) CHECK_VALUE,
                                LOGICAL_OPERTAOR,
                                RS_CONTRACT_SELECTION
                         FROM   #SALES_BASIS SB
                                INNER JOIN #SELECT_CONTRACT_SALES SCS
                                        ON SCS.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
                                INNER JOIN CCP_DETAILS CCP
                                                    ON CCP.CCP_DETAILS_SID=sb.CCP_DETAILS_SID
                                                       
                                INNER JOIN #CONTRACT_DETAILS CDPS
                                        ON CDPS.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
                                           AND CDPS.RS_MODEL_SID = SB.RS_MODEL_SID
                                           AND CDPS.PERIOD_SID = SCS.PERIOD_SID
                                                       INNER JOIN '
					,@PRODUCT_TABLE
					,
					' I
                                                       ON I.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
                                                         AND CDPS.PERIOD_SID=SCS.PERIOD_SID
                                CROSS APPLY (SELECT TOP 1 CDR_DETAILS_SID,
                                                          CDR_MODEL_SID,
                                                          KE.DESCRIPTION AS KEYWORD,
                                                          OP.DESCRIPTION AS OPERATOR,
                                                          VALUE,
                                                          CO.DESCRIPTION AS COMPARISON,
                                                          LO.DESCRIPTION AS LOGICAL_OPERTAOR
                                             FROM   CDR_DETAILS CD
                                                    INNER JOIN HELPER_TABLE KE
                                                            ON CD.KEYWORD = KE.HELPER_TABLE_SID
                                                    INNER JOIN HELPER_TABLE OP
                                                            ON CD.OPERATOR = OP.HELPER_TABLE_SID
                                                   LEFT JOIN HELPER_TABLE CO
                                                           ON CD.COMPARISON = CO.HELPER_TABLE_SID
                                                    LEFT JOIN HELPER_TABLE LO
                                                           ON LO.HELPER_TABLE_SID = CD.LOGICAL_OPERATOR
                                             WHERE  KE.DESCRIPTION = ''CONTRACT SALES''
                                                    AND SB.RS_CDR_MODEL_SID = CD.CDR_MODEL_SID --RS
                                             ORDER  BY CD.CDR_DETAILS_SID ASC) CD),
                     FINAL_SALES
                     AS (SELECT CCP_DETAILS_SID,
                                RS_MODEL_SID,
                                PERIOD_SID,
                                RS_CONTRACT_SELECTION,
                                RS_CONTRACT_SALES
                         FROM   CTE C
                                CROSS APPLY ( VALUES (''<'',
                                            Iif(Isnull(RP_PROJECTION_SALES, 0) < CHECK_VALUE, RP_PROJECTION_SALES, CHECK_VALUE)),
                                                     (''<='',
                                            Iif(Isnull(RP_PROJECTION_SALES, 0) <= CHECK_VALUE, RP_PROJECTION_SALES, CHECK_VALUE) ),
                                                     (''='',
                                            Iif(Isnull(RP_PROJECTION_SALES, 0) = CHECK_VALUE, RP_PROJECTION_SALES, CHECK_VALUE)),
                                                     (''>'',
                                            Iif(Isnull(RP_PROJECTION_SALES, 0) > CHECK_VALUE, RP_PROJECTION_SALES, CHECK_VALUE)),
                                                     (''>='',
                                            Iif(Isnull(RP_PROJECTION_SALES, 0) >= CHECK_VALUE, RP_PROJECTION_SALES, CHECK_VALUE)) ) AS CS(OPERATOR, RS_CONTRACT_SALES)
                         WHERE  C.OPERATOR = CS.OPERATOR)
                INSERT INTO #RS_SALES_BASIS
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             RS_CONTRACT_SELECTION,
                             RS_CONTRACT_SALES)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PERIOD_SID,
                       RS_CONTRACT_SELECTION,
                       RS_CONTRACT_SALES
                FROM   FINAL_SALES'
					)

			EXEC sp_executesql @D_SQL1

			------------------------------------------------------------------------------------------  SALES BASIS TAB --> SELECTED AND EXISTING CONTRACT IN REBATE SCHEDULE
			UPDATE RS
			SET PROJECTION_SALES = RS_CONTRACT_SALES
			FROM #RS_NET_SALES_INFO RS
			INNER JOIN #RS_SALES_BASIS SB ON RS.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
				AND RS.PERIOD_SID = SB.PERIOD_SID
				AND RS.CONTRACT_SELECTION = SB.RS_CONTRACT_SELECTION

			-------------------------------------------------------------------------------------------- DEDUCTION TAB --> NET SALES FORMULA IN REBATE PLAN 
			SET @D_SQL1 = CONCAT (
					'      INSERT INTO #RP_NET_SALES_INFO_DEDUCTION
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             CDR_MODEL_SID,
                             RP_NET_SALES_FORMULA_MASTER_SID,
                             CONTRACT_SELECTION,
                             NET_SALES_FORMULA_TYPE,
                             PROJECTION_AMOUNT,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PRICE_GROUP_TYPE,
                             KEYWORD,
                             OPERATOR,
                             COMPARISON,
                             LOGICAL_OPERATOR,
                             ITEM_GROUP_MS_ASSOCIATION,
                             LINE_TYPE,
                             INDICATOR,
                             VALUE)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                      PERIOD_SID,
                       CDR_MODEL_SID,
                       RP_NET_SALES_FORMULA_MASTER_SID,
                       CONTRACT_SELECTION,
                       NET_SALES_FORMULA_TYPE,
                       PROJECTION_AMOUNT,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       PRICE_GROUP_TYPE,
                       KEYWORD,
                       OPERATOR,
                       COMPARISON,
                       LOGICAL_OPERATOR,
                       ITEM_GROUP_MS_ASSOCIATION,
                       LINE_TYPE,
                       INDICATOR,
                       VALUE=COALESCE(CASE COMPARISON
                                        WHEN ''GROSS TRADE SALES'' THEN Isnull(EXFACTORY_FORECAST_SALES, 0) --PRODUCT_FILE
                                           WHEN ''DEMAND'' THEN Isnull(DEMAND_FORECAST_SALES, 0)
                                           WHEN ''INVENTORY WITHDRAWALS'' THEN Isnull(INVENTORY_FORECAST_SALES, 0)
                                        WHEN ''CONTRACT SALES'' THEN Isnull(PROJECTION_SALES, 0)
                                      END * ( VALUE / 100 ), VALUE)
                FROM   (SELECT Row_number()
                                 OVER (
                                   PARTITION BY CD.ccp_DETAILS_SID, CD.RS_MODEL_SID, CD.PERIOD_SID, D.CDR_MODEL_SID, CD.RP_NET_SALES_FORMULA_MASTER_SID
                                   ORDER BY CD.PERIOD_SID)RN,
                               CD.CCP_DETAILS_SID,
                               CD.RS_MODEL_SID,
                               CD.PERIOD_SID,
                               D.CDR_MODEL_SID,
                               CD.RP_NET_SALES_FORMULA_MASTER_SID,
                               NSFM.CONTRACT_SELECTION,
                               H6.DESCRIPTION             NET_SALES_FORMULA_TYPE,
                               PROJECTION_AMOUNT,
                               PROJECTION_SALES,
                               PROJECTION_UNITS,
                               CD.PRICE_GROUP_TYPE,
                               H.DESCRIPTION              KEYWORD,
                               H1.DESCRIPTION             OPERATOR,
                               H2.DESCRIPTION             COMPARISON,
                               H3.DESCRIPTION             LOGICAL_OPERATOR,
                               H4.DESCRIPTION             ITEM_GROUP_MS_ASSOCIATION,
                               H5.DESCRIPTION             LINE_TYPE,
                               DD.INDICATOR,
                               EXFACTORY_FORECAST_SALES  ,                
                                DEMAND_FORECAST_SALES ,                  
                                INVENTORY_FORECAST_SALES    ,            
                               VALUE
                        FROM   #CONTRACT_DETAILS CD
                               LEFT JOIN DEDUCTION_DETAILS DD
                                      ON CD.RP_NET_SALES_FORMULA_MASTER_SID = DD.NET_SALES_FORMULA_MASTER_SID
                               INNER JOIN NET_SALES_FORMULA_MASTER NSFM
                                       ON NSFM.NET_SALES_FORMULA_MASTER_SID = DD.NET_SALES_FORMULA_MASTER_SID
                               INNER JOIN CDR_DETAILS D
                                       ON D.CDR_MODEL_SID = DD.CDR_MODEL_SID
                              INNER JOIN '
					,@REBATE_INFO
					,' R
                                       ON R.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = CD.RS_MODEL_SID
                                          AND R.EVALUATION_RULE = CD.EVALUATION_RULE
                                                   INNER JOIN '
					,@MASTER_TABLE
					,'  SM
                                                   ON R.CCP_DETAILS_SID = SM.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = SM.RS_MODEL_SID
                                                                       AND SM.CHECK_RECORD=1 and  FILTER_CCP=1  ',case when @DEDUCTION_INCLUSION is not null then concat('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END,'
                               INNER JOIN CCP_DETAILS CCP
                                                    ON CCP.CCP_DETAILS_SID=SM.CCP_DETAILS_SID
                                                       INNER JOIN '
					,@PRODUCT_TABLE
					,
					' I
                                                       ON I.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
                                                         AND CD.PERIOD_SID=I.PERIOD_SID
                               LEFT JOIN HELPER_TABLE H
                                      ON D.KEYWORD = H.HELPER_TABLE_SID AND  H.DESCRIPTION = ''CONTRACT DEDUCTIONS''
                                         AND H.LIST_NAME = ''KEYWORD''
                               LEFT JOIN HELPER_TABLE H1
                                      ON D.OPERATOR = H1.HELPER_TABLE_SID
                                         AND H1.LIST_NAME = ''OPERATOR''
                               LEFT JOIN HELPER_TABLE H2
                                      ON D.COMPARISON = H2.HELPER_TABLE_SID
                                         AND H2.LIST_NAME = ''COMPARISON''
                               LEFT JOIN HELPER_TABLE H3
                                      ON D.LOGICAL_OPERATOR = H3.HELPER_TABLE_SID
                                         AND H3.LIST_NAME = ''LOGICAL_OPERATOR''
                               LEFT JOIN HELPER_TABLE H4
                                      ON D.ITEM_GROUP_MS_ASSOCIATION = H4.HELPER_TABLE_SID
                                         AND H4.LIST_NAME = ''ITEM_GROUP_MS_ASSOCIATION''
                               LEFT JOIN HELPER_TABLE H5
                                      ON D.LINE_TYPE = H5.HELPER_TABLE_SID
                                         AND H5.LIST_NAME = ''LINE_TYPE''
                               LEFT JOIN HELPER_TABLE H6
                                      ON NSFM.NET_SALES_FORMULA_TYPE = H6.HELPER_TABLE_SID
                                         AND H6.LIST_NAME = ''NS_FORMULA_TYPE''   AND H6.DESCRIPTION <> ''CONTRACT''
                        WHERE 
                              
                                                   
                                          ( R.RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                               OR R.RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL )
                             OR ( R.RP_NET_SALES_FORMULA_MASTER_SID <> 0
                                   OR R.RS_NET_SALES_FORMULA_MASTER_SID <> 0 )       
                                                   
                                                 )A
                WHERE  RN = 1'
					)

			EXEC sp_executesql @D_SQL1

			-----------------------------------------------------------------------------------------DEDUCTION TAB -->  NET SALES RULE IN REBATE PLAN
			UPDATE RP
			SET PROJECTION_AMOUNT = RP_PROJECTION_AMOUNT
			FROM #RP_NET_SALES_INFO_DEDUCTION RP
			CROSS APPLY (
				VALUES (
					'<'
					,Iif(Isnull(PROJECTION_AMOUNT, 0) < VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'<='
					,Iif(Isnull(PROJECTION_AMOUNT, 0) <= VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'='
					,Iif(Isnull(PROJECTION_AMOUNT, 0) = VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'>'
					,Iif(Isnull(PROJECTION_AMOUNT, 0) > VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'>='
					,Iif(Isnull(PROJECTION_AMOUNT, 0) >= VALUE, PROJECTION_AMOUNT, VALUE)
					)
				) AS CS(OPERATOR, RP_PROJECTION_AMOUNT)
			WHERE RP.OPERATOR = CS.OPERATOR

			-------------------------------------------------------------------------------------------- DEDUCTION TAB --> NET SALES FORMULA IN REBATE SCHEDULE 
			SET @D_SQL1 = CONCAT (
					'  INSERT INTO #RS_NET_SALES_INFO_DEDUCTION
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             CDR_MODEL_SID,
                             RS_NET_SALES_FORMULA_MASTER_SID,
                             CONTRACT_SELECTION,
                             NET_SALES_FORMULA_TYPE,
                             PROJECTION_AMOUNT,
                             PROJECTION_SALES,
                             PROJECTION_UNITS,
                             PRICE_GROUP_TYPE,
                             KEYWORD,
                             OPERATOR,
                             COMPARISON,
                             LOGICAL_OPERATOR,
                             ITEM_GROUP_MS_ASSOCIATION,
                             LINE_TYPE,
                             INDICATOR,
                             VALUE)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PERIOD_SID,
                       CDR_MODEL_SID,
                       RS_NET_SALES_FORMULA_MASTER_SID,
                       CONTRACT_SELECTION,
                       NET_SALES_FORMULA_TYPE,
                       PROJECTION_AMOUNT,
                       PROJECTION_SALES,
                       PROJECTION_UNITS,
                       PRICE_GROUP_TYPE,
                       KEYWORD,
                       OPERATOR,
                       COMPARISON,
                       LOGICAL_OPERATOR,
                       ITEM_GROUP_MS_ASSOCIATION,
                       LINE_TYPE,
                       INDICATOR,
                       VALUE=COALESCE(CASE COMPARISON
                                        WHEN ''GROSS TRADE SALES'' THEN Isnull(EXFACTORY_FORECAST_SALES, 0) --PRODUCT_FILE
                                           WHEN ''DEMAND'' THEN Isnull(DEMAND_FORECAST_SALES, 0)
                                           WHEN ''INVENTORY WITHDRAWALS'' THEN Isnull(INVENTORY_FORECAST_SALES, 0)
                                        WHEN ''CONTRACT SALES'' THEN Isnull(PROJECTION_SALES, 0)
                                      END * ( VALUE / 100 ), VALUE)
                FROM   (SELECT Row_number()
                                 OVER (
                                   PARTITION BY CD.CCP_DETAILS_SID, CD.RS_MODEL_SID, CD.PERIOD_SID, D.CDR_MODEL_SID, CD.RS_NET_SALES_FORMULA_MASTER_SID
                                   ORDER BY CD.PERIOD_SID)RN,
                               CD.CCP_DETAILS_SID,
                               CD.RS_MODEL_SID,
                               CD.PERIOD_SID,
                               D.CDR_MODEL_SID,
                               CD.RS_NET_SALES_FORMULA_MASTER_SID,
                               NSFM.CONTRACT_SELECTION,
                               H6.DESCRIPTION             NET_SALES_FORMULA_TYPE,
                               PROJECTION_AMOUNT,
                               PROJECTION_SALES,
                               PROJECTION_UNITS,
                               CD.PRICE_GROUP_TYPE,
                               H.DESCRIPTION              KEYWORD,
                               H1.DESCRIPTION             OPERATOR,
                               H2.DESCRIPTION             COMPARISON,
                               H3.DESCRIPTION             LOGICAL_OPERATOR,
                               H4.DESCRIPTION             ITEM_GROUP_MS_ASSOCIATION,
                               H5.DESCRIPTION             LINE_TYPE,
                               DD.INDICATOR,
                               EXFACTORY_FORECAST_SALES,
                               DEMAND_FORECAST_SALES,
                               INVENTORY_FORECAST_SALES,
                               VALUE
                        FROM   #CONTRACT_DETAILS CD
                               LEFT JOIN DEDUCTION_DETAILS DD
                                      ON CD.RS_NET_SALES_FORMULA_MASTER_SID = DD.NET_SALES_FORMULA_MASTER_SID
                               INNER JOIN NET_SALES_FORMULA_MASTER NSFM
                                       ON NSFM.NET_SALES_FORMULA_MASTER_SID = DD.NET_SALES_FORMULA_MASTER_SID
                               INNER JOIN CDR_DETAILS D
                                       ON D.CDR_MODEL_SID = DD.CDR_MODEL_SID
                               INNER JOIN '
					,@REBATE_INFO
					,' R
                                       ON R.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = CD.RS_MODEL_SID
                                          AND R.EVALUATION_RULE = CD.EVALUATION_RULE
                                                   INNER JOIN '
					,@MASTER_TABLE
					,'  SM
                                                   ON R.CCP_DETAILS_SID = SM.CCP_DETAILS_SID
                                          AND R.RS_MODEL_SID = SM.RS_MODEL_SID
                                                                       AND SM.CHECK_RECORD=1 and  FILTER_CCP=1  ',case when @DEDUCTION_INCLUSION is not null then concat('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END,'
                               INNER JOIN CCP_DETAILS CCP
                                                    ON CCP.CCP_DETAILS_SID=SM.CCP_DETAILS_SID
                                                       INNER JOIN '
					,@PRODUCT_TABLE
					,
					' I
                                                       ON I.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
                                                         AND CD.PERIOD_SID=I.PERIOD_SID
                               LEFT JOIN HELPER_TABLE H
                                      ON D.KEYWORD = H.HELPER_TABLE_SID
                                         AND H.LIST_NAME = ''KEYWORD'' AND H.DESCRIPTION = ''CONTRACT DEDUCTIONS''
                               LEFT JOIN HELPER_TABLE H1
                                      ON D.OPERATOR = H1.HELPER_TABLE_SID
                                         AND H1.LIST_NAME = ''OPERATOR''
                               LEFT JOIN HELPER_TABLE H2
                                      ON D.COMPARISON = H2.HELPER_TABLE_SID
                                         AND H2.LIST_NAME = ''COMPARISON''
                               LEFT JOIN HELPER_TABLE H3
                                      ON D.LOGICAL_OPERATOR = H3.HELPER_TABLE_SID
                                         AND H3.LIST_NAME = ''LOGICAL_OPERATOR''
                               LEFT JOIN HELPER_TABLE H4
                                      ON D.ITEM_GROUP_MS_ASSOCIATION = H4.HELPER_TABLE_SID
                                         AND H4.LIST_NAME = ''ITEM_GROUP_MS_ASSOCIATION''
                               LEFT JOIN HELPER_TABLE H5
                                      ON D.LINE_TYPE = H5.HELPER_TABLE_SID
                                         AND H5.LIST_NAME = ''LINE_TYPE''
                               LEFT JOIN HELPER_TABLE H6
                                      ON NSFM.NET_SALES_FORMULA_TYPE = H6.HELPER_TABLE_SID
                                         AND H6.LIST_NAME = ''NS_FORMULA_TYPE'' AND   H6.DESCRIPTION <> ''CONTRACT''
                          
                              
                                                   
                                     WHERE 
                              
                                                   
                                          ( R.RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                               OR R.RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL )
                             OR ( R.RP_NET_SALES_FORMULA_MASTER_SID <> 0
                                   OR R.RS_NET_SALES_FORMULA_MASTER_SID <> 0 )                    
                                                   
                                                   
                                                   
                                                   )A
                WHERE  RN = 1'
					)

			EXEC sp_executesql @D_SQL1

			-----------------------------------------------------------------------------------------DEDUCTION TAB -->  NET SALES RULE IN REBATE SCHEDULE
			UPDATE RS
			SET PROJECTION_AMOUNT = RS_PROJECTION_AMOUNT
			FROM #RS_NET_SALES_INFO_DEDUCTION RS
			CROSS APPLY (
				VALUES (
					'<'
					,Iif(Isnull(PROJECTION_AMOUNT, 0) < VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'<='
					,Iif(Isnull(PROJECTION_AMOUNT, 0) <= VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'='
					,Iif(Isnull(PROJECTION_AMOUNT, 0) = VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'>'
					,Iif(Isnull(PROJECTION_AMOUNT, 0) > VALUE, PROJECTION_AMOUNT, VALUE)
					)
					,(
					'>='
					,Iif(Isnull(PROJECTION_AMOUNT, 0) >= VALUE, PROJECTION_AMOUNT, VALUE)
					)
				) AS CS(OPERATOR, RS_PROJECTION_AMOUNT)
			WHERE RS.OPERATOR = CS.OPERATOR

			----------------------------------------------------------------------------------------  DEDUCTION TAB --> FORMULA TYPE 'CONTRACT' MAIN BLOCK
			IF Object_id('TEMPDB..#DEDUCTION_CONTRACT') IS NOT NULL
				DROP TABLE #DEDUCTION_CONTRACT;;

			WITH FORMULA_CONTRACT
			AS (
				SELECT DISTINCT CC.CONTRACT_MASTER_SID
					,CCD.COMPANY_MASTER_SID
					,ICD.ITEM_MASTER_SID
					,NSF.NET_SALES_FORMULA_MASTER_SID
					, CASE 
						WHEN HT.DESCRIPTION = 'REIMB'
							THEN 'REIMBURSEMENT'
						WHEN HT.DESCRIPTION = 'OI'
							THEN 'OFF INVOICE'
						ELSE HT.DESCRIPTION
						END AS REBATE_PROGRAM_TYPE
					,HT.DESCRIPTION AS NET_SALES_FORMULA_TYPE
					,DD.CDR_MODEL_SID
				FROM NET_SALES_FORMULA_MASTER NSF
				INNER JOIN DEDUCTION_DETAILS DD ON NSF.NET_SALES_FORMULA_MASTER_SID = DD.NET_SALES_FORMULA_MASTER_SID
				INNER JOIN CFP_CONTRACT CC ON DD.CONTRACT_MASTER_SID = CC.CONTRACT_MASTER_SID
				INNER JOIN CFP_CONTRACT_DETAILS CCD ON CC.CFP_CONTRACT_SID = CCD.CFP_CONTRACT_SID
				INNER JOIN IFP_CONTRACT IC ON IC.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
					AND IC.CONTRACT_MASTER_SID = CC.CONTRACT_MASTER_SID
				INNER JOIN IFP_CONTRACT_DETAILS ICD ON ICD.IFP_CONTRACT_SID = IC.IFP_CONTRACT_SID
				INNER JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = NSF.NET_SALES_FORMULA_TYPE
					AND LIST_NAME = 'NS_FORMULA_TYPE'
				INNER JOIN RS_CONTRACT RC ON RC.RS_CONTRACT_SID = DD.RS_CONTRACT_SID
					AND RC.CONTRACT_MASTER_SID = DD.CONTRACT_MASTER_SID
					AND RC.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
					AND RC.IFP_CONTRACT_SID = IC.IFP_CONTRACT_SID
				INNER JOIN RS_CONTRACT_DETAILS RCD ON RCD.RS_CONTRACT_SID = RC.RS_CONTRACT_SID
					AND RCD.ITEM_MASTER_SID = ICD.ITEM_MASTER_SID
				LEFT OUTER JOIN HELPER_TABLE RPT ON RPT.HELPER_TABLE_SID = RC.REBATE_PROGRAM_TYPE
					AND RPT.LIST_NAME = 'REBATE_PROGRAM_TYPE'
				WHERE HT.DESCRIPTION = 'CONTRACT'
				)
			SELECT CCP_DETAILS_SID
				,C.CONTRACT_MASTER_SID
				,C.COMPANY_MASTER_SID
				,C.ITEM_MASTER_SID
				,RS_MODEL_SID
				,C.REBATE_PROGRAM_TYPE
				,RS.NET_SALES_FORMULA_TYPE AS RS_NET_SALES_FORMULA_TYPE
				,RS.CONTRACT_MASTER_SID AS RS_CONTRACT_MASTER_SID
				,RS.ITEM_MASTER_SID AS RS_ITEM_MASTER_SID
				,RS.COMPANY_MASTER_SID RS_COMPANY_MASTER_SID
				,RS.CDR_MODEL_SID AS RS_CDR_MODEL_SID
				,RP.NET_SALES_FORMULA_TYPE AS RP_NET_SALES_FORMULA_TYPE
				,RP.CONTRACT_MASTER_SID AS RP_CONTRACT_MASTER_SID
				,RP.ITEM_MASTER_SID AS RP_ITEM_MASTER_SID
				,RP.COMPANY_MASTER_SID RP_COMPANY_MASTER_SID
				,RP.CDR_MODEL_SID AS RP_CDR_MODEL_SID
			INTO #DEDUCTION_CONTRACT -- NOTE : THIS MAY HAVE DUPLICATE SINCE CUST/ITEM CAN BE PRESENT IN MORE THAN ONE CONTRACT
			FROM #CCP C
			LEFT OUTER JOIN FORMULA_CONTRACT RS ON C.COMPANY_MASTER_SID = RS.COMPANY_MASTER_SID
				AND C.ITEM_MASTER_SID = RS.ITEM_MASTER_SID
				AND C.REBATE_PROGRAM_TYPE = RS.REBATE_PROGRAM_TYPE
				AND C.RS_NSF = RS.NET_SALES_FORMULA_MASTER_SID
			LEFT OUTER JOIN FORMULA_CONTRACT RP ON C.COMPANY_MASTER_SID = RP.COMPANY_MASTER_SID
				AND C.ITEM_MASTER_SID = RP.ITEM_MASTER_SID
				AND C.REBATE_PROGRAM_TYPE = RP.REBATE_PROGRAM_TYPE
				AND C.RP_NSF = RP.NET_SALES_FORMULA_MASTER_SID

			--------------------------------------------------------------------------REBATE PLAN (NSF) PULLING SALES FOR SELECT CONTRACT IN SALES BASIS TAB
			IF Object_id('TEMPDB..#CONTRACT_PERIOD') IS NOT NULL
				TRUNCATE TABLE #CONTRACT_PERIOD;
			ELSE
				CREATE TABLE #CONTRACT_PERIOD (
					CCP_DETAILS_SID INT
					,PERIOD_SID INT
					,RP_PROJECTION_AMOUNT NUMERIC(22, 6)
					,RS_PROJECTION_AMOUNT NUMERIC(22, 6)
					)

			SET @D_SQL = CONCAT (
					' INSERT INTO #CONTRACT_PERIOD
                            (CCP_DETAILS_SID,
                             PERIOD_SID)
                SELECT CCP_DETAILS_SID,
                       PERIOD_SID
                FROM   '
					,@MASTER_TABLE
					,' PD
                       CROSS JOIN #PERIOD_CD P
                WHERE   METHODOLOGY = ''CONTRACT DETAILS''
                                      AND P.PERIOD_SID BETWEEN ',@FORECAST_START_PERIOD_SID,' AND COALESCE(',@FORECAST_END_PERIOD_SID,','
					,@PROJECTION_END_PERIOD_SID
					,')
                       '
					)

			EXEC sp_executesql @D_SQL;

			WITH CTE
			AS (
				SELECT DISTINCT CCP_DETAILS_SID
					,RS_MODEL_SID
					,RP_CONTRACT_MASTER_SID
					,RP_COMPANY_MASTER_SID
					,RP_ITEM_MASTER_SID
				FROM #DEDUCTION_CONTRACT
				)
				,APPROVED_PROJ_DETAILS --TO FIND APPROVED PROJECTION FOR MATCHED CUSTOMER/ITEM COMBINATION 
			AS (
				SELECT RP_CONTRACT_MASTER_SID
					,RP_COMPANY_MASTER_SID
					,RP_ITEM_MASTER_SID
					,RP_PROJECTION_DETAILS_SID
					,C.CCP_DETAILS_SID
					,C.RS_MODEL_SID
				FROM CTE C
				CROSS APPLY (
					SELECT TOP 1 PD.PROJECTION_DETAILS_SID AS RP_PROJECTION_DETAILS_SID
					FROM WORKFLOW_MASTER WM
					INNER JOIN HELPER_TABLE HT ON WM.WORKFLOW_STATUS_ID = HT.HELPER_TABLE_SID
					INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
					INNER JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
					WHERE HT.DESCRIPTION = 'APPROVED'
						AND EXISTS (
							SELECT 1
							FROM PROJECTION_MASTER PM
							WHERE PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
								AND FORECASTING_TYPE = @FORECASTING_TYPE
							)
						AND CD.COMPANY_MASTER_SID = C.RP_COMPANY_MASTER_SID
						AND CD.CONTRACT_MASTER_SID = C.RP_CONTRACT_MASTER_SID
						AND CD.ITEM_MASTER_SID = C.RP_ITEM_MASTER_SID
					ORDER BY WM.MODIFIED_DATE DESC
					) CS
				)
			UPDATE SCS
			SET SCS.RP_PROJECTION_AMOUNT = A.PROJECTION_SALES
			FROM #CONTRACT_PERIOD SCS
			INNER JOIN (
				SELECT APD.CCP_DETAILS_SID
					,Sum(PROJECTION_SALES) AS PROJECTION_SALES
					,PERIOD_SID
				FROM NM_DISCOUNT_PROJECTION NDP
				INNER JOIN APPROVED_PROJ_DETAILS APD ON APD.RP_PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
					AND NDP.RS_MODEL_SID = APD.RS_MODEL_SID
				GROUP BY APD.CCP_DETAILS_SID
					,APD.RS_MODEL_SID
					,PERIOD_SID
				) A ON A.CCP_DETAILS_SID = SCS.CCP_DETAILS_SID
				AND A.PERIOD_SID = SCS.PERIOD_SID
				--------------------------------------------------------------------------------------REBATE SCHEDULE (NSF) PULLING SALES FOR SELECT CONTRACT IN SALES BASIS TAB
				;

			WITH CTE
			AS (
				SELECT DISTINCT CCP_DETAILS_SID
					,RS_CONTRACT_MASTER_SID
					,RS_COMPANY_MASTER_SID
					,RS_ITEM_MASTER_SID
				FROM #DEDUCTION_CONTRACT
				)
				,APPROVED_PROJ_DETAILS --TO FIND APPROVED PROJECTION FOR MATCHED CUSTOMER/ITEM COMBINATION 
			AS (
				SELECT RS_CONTRACT_MASTER_SID
					,RS_COMPANY_MASTER_SID
					,RS_ITEM_MASTER_SID
					,RP_PROJECTION_DETAILS_SID
					,C.CCP_DETAILS_SID
				FROM CTE C
				CROSS APPLY (
					SELECT TOP 1 PD.PROJECTION_DETAILS_SID AS RP_PROJECTION_DETAILS_SID
					FROM WORKFLOW_MASTER WM
					INNER JOIN HELPER_TABLE HT ON WM.WORKFLOW_STATUS_ID = HT.HELPER_TABLE_SID
					INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
					INNER JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
					WHERE HT.DESCRIPTION = 'APPROVED'
						AND EXISTS (
							SELECT 1
							FROM PROJECTION_MASTER PM
							WHERE PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
								AND FORECASTING_TYPE = @FORECASTING_TYPE
							)
						AND CD.COMPANY_MASTER_SID = C.RS_COMPANY_MASTER_SID
						AND CD.CONTRACT_MASTER_SID = C.RS_CONTRACT_MASTER_SID
						AND CD.ITEM_MASTER_SID = C.RS_ITEM_MASTER_SID
					ORDER BY WM.MODIFIED_DATE DESC
					) CS
				)
			UPDATE SCS
			SET SCS.RS_PROJECTION_AMOUNT = A.PROJECTION_SALES
			FROM #CONTRACT_PERIOD SCS
			INNER JOIN (
				SELECT APD.CCP_DETAILS_SID
					,Sum(PROJECTION_SALES) AS PROJECTION_SALES
					,-- SUM USED TO SUM THE CUSTOMER/ITEM COMBINATION IS PRESENT IN MORE THAN ONE CONTRACT
					PERIOD_SID
				FROM NM_SALES_PROJECTION NSP
				INNER JOIN APPROVED_PROJ_DETAILS APD ON APD.RP_PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
				GROUP BY APD.CCP_DETAILS_SID
					,PERIOD_SID
				) A ON A.CCP_DETAILS_SID = SCS.CCP_DETAILS_SID
				AND A.PERIOD_SID = SCS.PERIOD_SID

			----------------------------------------------------------------------------------------  DEDUCTION TAB --> FORMULA TYPE 'CONTRACT' IMPLEMENTATION IN REBATE PLAN
			IF Object_id('TEMPDB..#RP_DEDUCTION_CONTRACT') IS NOT NULL
				TRUNCATE TABLE #RP_DEDUCTION_CONTRACT
			ELSE
				CREATE TABLE #RP_DEDUCTION_CONTRACT (
					CCP_DETAILS_SID INT
					,RS_MODEL_SID INT
					,PERIOD_SID INT
					,RP_NET_SALES_FORMULA_TYPE VARCHAR(30)
					,REBATE_PROGRAM_TYPE VARCHAR(30)
					,RP_CONTRACT_SALES NUMERIC(22, 6)
					)

			SET @D_SQL1 = CONCAT (
					';

                WITH CTE
                     AS (SELECT SB.CCP_DETAILS_SID,
                                SB.RS_MODEL_SID,
                                SCS.PERIOD_SID,
                                SB.REBATE_PROGRAM_TYPE,
                                SB.RP_NET_SALES_FORMULA_TYPE,
                                RP_PROJECTION_AMOUNT,
                                EXFACTORY_FORECAST_SALES                  AS GROSS_TRADE_SALES,
                                DEMAND_FORECAST_SALES                    AS DEMAND_SALES,
                                INVENTORY_FORECAST_SALES                AS INVENTORY_SALES,
                                KEYWORD,
                                OPERATOR,
                                VALUE,
                                COMPARISON,
                                COALESCE(CASE COMPARISON
                                          WHEN ''GROSS TRADE SALES'' THEN Isnull(EXFACTORY_FORECAST_SALES, 0) --PRODUCT_FILE
                                           WHEN ''DEMAND'' THEN Isnull(DEMAND_FORECAST_SALES, 0)
                                           WHEN ''INVENTORY WITHDRAWALS'' THEN Isnull(INVENTORY_FORECAST_SALES, 0)
                                           WHEN ''CONTRACT SALES'' THEN Isnull(PROJECTION_SALES, 0)
                                         END * ( VALUE / 100 ), VALUE) CHECK_VALUE,
                                LOGICAL_OPERTAOR
                         FROM   #DEDUCTION_CONTRACT SB
                                INNER JOIN #CONTRACT_PERIOD SCS
                                        ON SCS.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
                                 INNER JOIN CCP_DETAILS CCP
                                                    ON CCP.CCP_DETAILS_SID=SCS.CCP_DETAILS_SID
                                                       INNER JOIN '
					,@PRODUCT_TABLE
					,
					' I
                                                       ON I.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
                                                         AND SCS.PERIOD_SID=I.PERIOD_SID
                                INNER JOIN #CONTRACT_DETAILS CDPS
                                        ON CDPS.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
                                           AND CDPS.RS_MODEL_SID = SB.RS_MODEL_SID
                                           AND CDPS.PERIOD_SID = SCS.PERIOD_SID
                                CROSS APPLY (SELECT TOP 1 CDR_DETAILS_SID,
                                                          CDR_MODEL_SID,
                                                          KE.DESCRIPTION AS KEYWORD,
                                                          OP.DESCRIPTION AS OPERATOR,
                                                          VALUE,
                                                          CO.DESCRIPTION AS COMPARISON,
                                                          LO.DESCRIPTION AS LOGICAL_OPERTAOR
                                             FROM   CDR_DETAILS CD
                                                    INNER JOIN HELPER_TABLE KE
                                                            ON CD.KEYWORD = KE.HELPER_TABLE_SID
                                                    INNER JOIN HELPER_TABLE OP
                                                            ON CD.OPERATOR = OP.HELPER_TABLE_SID
                                                    LEFT JOIN HELPER_TABLE CO
                                                           ON CD.COMPARISON = CO.HELPER_TABLE_SID
                                                    LEFT JOIN HELPER_TABLE LO
                                                           ON LO.HELPER_TABLE_SID = CD.LOGICAL_OPERATOR
                                             WHERE  KE.DESCRIPTION = ''CONTRACT DEDUCTIONS''
                                                    AND SB.RP_CDR_MODEL_SID = CD.CDR_MODEL_SID --RP
                                             ORDER  BY CD.CDR_DETAILS_SID ASC) CD),
                     FINAL_SALES
                     AS (SELECT CCP_DETAILS_SID,
                                RS_MODEL_SID,
                                PERIOD_SID,
                                RP_NET_SALES_FORMULA_TYPE,
                                REBATE_PROGRAM_TYPE,
                                RP_CONTRACT_SALES
                         FROM   CTE C
                                CROSS APPLY ( VALUES (''<'',
                                            Iif(Isnull(RP_PROJECTION_AMOUNT, 0) < CHECK_VALUE, RP_PROJECTION_AMOUNT, CHECK_VALUE)),
                                                     (''<='',
                                            Iif(Isnull(RP_PROJECTION_AMOUNT, 0) <= CHECK_VALUE, RP_PROJECTION_AMOUNT, CHECK_VALUE) ),
                                                     (''='',
                                            Iif(Isnull(RP_PROJECTION_AMOUNT, 0) = CHECK_VALUE, RP_PROJECTION_AMOUNT, CHECK_VALUE)),
                                                     (''>'',
                                            Iif(Isnull(RP_PROJECTION_AMOUNT, 0) > CHECK_VALUE, RP_PROJECTION_AMOUNT, CHECK_VALUE)),
                                                     (''>='',
                                            Iif(Isnull(RP_PROJECTION_AMOUNT, 0) >= CHECK_VALUE, RP_PROJECTION_AMOUNT, CHECK_VALUE)) ) AS CS(OPERATOR, RP_CONTRACT_SALES)
                         WHERE  C.OPERATOR = CS.OPERATOR)
                INSERT INTO #RP_DEDUCTION_CONTRACT
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             RP_NET_SALES_FORMULA_TYPE,
                             REBATE_PROGRAM_TYPE,
                             RP_CONTRACT_SALES)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PERIOD_SID,
                       RP_NET_SALES_FORMULA_TYPE,
                       REBATE_PROGRAM_TYPE,
                       RP_CONTRACT_SALES
                FROM   FINAL_SALES'
					)

			EXEC sp_executesql @D_SQL1

			------------------------------------------------------------------------------------------  DEDUCTION TAB --> FORMULA TYPE 'CONTRACT' MERGED WITH OTHER FORMULA TYPE IN REBATE PLAN
			UPDATE RP
			SET PROJECTION_AMOUNT = RP_CONTRACT_SALES
			FROM #RP_NET_SALES_INFO_DEDUCTION RP
			INNER JOIN #RP_DEDUCTION_CONTRACT SB ON RP.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
				AND RP.PERIOD_SID = SB.PERIOD_SID
				AND RP.NET_SALES_FORMULA_TYPE = SB.RP_NET_SALES_FORMULA_TYPE

			UPDATE RP
			SET PROJECTION_AMOUNT = SB.PROJECTION_AMOUNT
			FROM #RP_NET_SALES_INFO RP
			INNER JOIN #RP_NET_SALES_INFO_DEDUCTION SB ON RP.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
				AND RP.PERIOD_SID = SB.PERIOD_SID
				AND RP.CONTRACT_SELECTION = SB.CONTRACT_SELECTION
				AND RP.NET_SALES_FORMULA_TYPE = SB.NET_SALES_FORMULA_TYPE

			----------------------------------------------------------------------------------------  DEDUCTION TAB --> FORMULA TYPE 'CONTRACT' IMPLEMENTATION IN REBATE SCHEDULE
			IF Object_id('TEMPDB..#RS_DEDUCTION_CONTRACT') IS NOT NULL
				TRUNCATE TABLE #RS_DEDUCTION_CONTRACT
			ELSE
				CREATE TABLE #RS_DEDUCTION_CONTRACT (
					CCP_DETAILS_SID INT
					,RS_MODEL_SID INT
					,PERIOD_SID INT
					,RS_NET_SALES_FORMULA_TYPE VARCHAR(30)
					,REBATE_PROGRAM_TYPE VARCHAR(30)
					,RS_CONTRACT_SALES NUMERIC(22, 6)
					)

			SET @D_SQL1 = CONCAT (
					'              ;

                WITH CTE
                     AS (SELECT SB.CCP_DETAILS_SID,
                                SB.RS_MODEL_SID,
                                SCS.PERIOD_SID,
                                SB.REBATE_PROGRAM_TYPE,
                                SB.RS_NET_SALES_FORMULA_TYPE,
                                RS_PROJECTION_AMOUNT,
                                EXFACTORY_FORECAST_SALES                  AS GROSS_TRADE_SALES,
                                DEMAND_FORECAST_SALES                    AS DEMAND_SALES,
                                INVENTORY_FORECAST_SALES                AS INVENTORY_SALES,
                                KEYWORD,
                                OPERATOR,
                                VALUE,
                                COMPARISON,
                                COALESCE(CASE COMPARISON
                                            WHEN ''GROSS TRADE SALES'' THEN Isnull(EXFACTORY_FORECAST_SALES, 0) --PRODUCT_FILE
                                           WHEN ''DEMAND'' THEN Isnull(DEMAND_FORECAST_SALES, 0)
                                           WHEN ''INVENTORY WITHDRAWALS'' THEN Isnull(INVENTORY_FORECAST_SALES, 0)
                                           WHEN ''CONTRACT SALES'' THEN Isnull(PROJECTION_SALES, 0)
                                         END * ( VALUE / 100 ), VALUE) CHECK_VALUE,
                                LOGICAL_OPERTAOR
                         FROM   #DEDUCTION_CONTRACT SB
                                INNER JOIN #CONTRACT_PERIOD SCS
                                        ON SCS.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
                              INNER JOIN CCP_DETAILS CCP
                                                    ON CCP.CCP_DETAILS_SID=SCS.CCP_DETAILS_SID
                                                       INNER JOIN '
					,@PRODUCT_TABLE
					,
					' I
                                                       ON I.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
                                                         AND SCS.PERIOD_SID=I.PERIOD_SID
                                INNER JOIN #CONTRACT_DETAILS CDPS
                                        ON CDPS.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
                                           AND CDPS.RS_MODEL_SID = SB.RS_MODEL_SID
                                           AND CDPS.PERIOD_SID = SCS.PERIOD_SID
                                CROSS APPLY (SELECT TOP 1 CDR_DETAILS_SID,
                                                          CDR_MODEL_SID,
                                                          KE.DESCRIPTION AS KEYWORD,
                                                          OP.DESCRIPTION AS OPERATOR,
                                                          VALUE,
                                                          CO.DESCRIPTION AS COMPARISON,
                                                          LO.DESCRIPTION AS LOGICAL_OPERTAOR
                                             FROM   CDR_DETAILS CD
                                                    INNER JOIN HELPER_TABLE KE
                                                            ON CD.KEYWORD = KE.HELPER_TABLE_SID
                                                    INNER JOIN HELPER_TABLE OP
                                                            ON CD.OPERATOR = OP.HELPER_TABLE_SID
                                                    LEFT JOIN HELPER_TABLE CO
                                                           ON CD.COMPARISON = CO.HELPER_TABLE_SID
                                                    LEFT JOIN HELPER_TABLE LO
                                                           ON LO.HELPER_TABLE_SID = CD.LOGICAL_OPERATOR
                                             WHERE  KE.DESCRIPTION = ''CONTRACT DEDUCTIONS''
                                                    AND SB.RS_CDR_MODEL_SID = CD.CDR_MODEL_SID --RP
                                             ORDER  BY CD.CDR_DETAILS_SID ASC) CD),
                     FINAL_SALES
                     AS (SELECT CCP_DETAILS_SID,
                                RS_MODEL_SID,
                                PERIOD_SID,
                                RS_NET_SALES_FORMULA_TYPE,
                                REBATE_PROGRAM_TYPE,
                                RS_CONTRACT_SALES
                         FROM   CTE C
                                CROSS APPLY ( VALUES (''<'',
                                            Iif(Isnull(RS_PROJECTION_AMOUNT, 0) < CHECK_VALUE, RS_PROJECTION_AMOUNT, CHECK_VALUE)),
                                                     (''<='',
                                            Iif(Isnull(RS_PROJECTION_AMOUNT, 0) <= CHECK_VALUE, RS_PROJECTION_AMOUNT, CHECK_VALUE) ),
                                                     (''='',
                                            Iif(Isnull(RS_PROJECTION_AMOUNT, 0) = CHECK_VALUE, RS_PROJECTION_AMOUNT, CHECK_VALUE)),
                                                     (''>'',
                                            Iif(Isnull(RS_PROJECTION_AMOUNT, 0) > CHECK_VALUE, RS_PROJECTION_AMOUNT, CHECK_VALUE)),
                                                     (''>='',
                                            Iif(Isnull(RS_PROJECTION_AMOUNT, 0) >= CHECK_VALUE, RS_PROJECTION_AMOUNT, CHECK_VALUE)) ) AS CS(OPERATOR, RS_CONTRACT_SALES)
                         WHERE  C.OPERATOR = CS.OPERATOR)
                INSERT INTO #RS_DEDUCTION_CONTRACT
                            (CCP_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             RS_NET_SALES_FORMULA_TYPE,
                             REBATE_PROGRAM_TYPE,
                             RS_CONTRACT_SALES)
                SELECT CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       PERIOD_SID,
                       RS_NET_SALES_FORMULA_TYPE,
                       REBATE_PROGRAM_TYPE,
                       RS_CONTRACT_SALES
                FROM   FINAL_SALES'
					)

			EXEC sp_executesql @D_SQL1

			------------------------------------------------------------------------------------------  DEDUCTION TAB --> FORMULA TYPE 'CONTRACT' MERGED WITH OTHER FORMULA TYPE IN REBATE SCHEDULE
			UPDATE RS
			SET PROJECTION_AMOUNT = RS_CONTRACT_SALES
			FROM #RS_NET_SALES_INFO_DEDUCTION RS
			INNER JOIN #RS_DEDUCTION_CONTRACT SB ON RS.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
				AND RS.PERIOD_SID = SB.PERIOD_SID
				AND RS.NET_SALES_FORMULA_TYPE = SB.RS_NET_SALES_FORMULA_TYPE

			UPDATE RS
			SET PROJECTION_AMOUNT = SB.PROJECTION_AMOUNT
			FROM #RS_NET_SALES_INFO RS
			INNER JOIN #RS_NET_SALES_INFO_DEDUCTION SB ON RS.CCP_DETAILS_SID = SB.CCP_DETAILS_SID
				AND RS.PERIOD_SID = SB.PERIOD_SID
				AND RS.CONTRACT_SELECTION = SB.CONTRACT_SELECTION
				AND RS.NET_SALES_FORMULA_TYPE = SB.NET_SALES_FORMULA_TYPE

			-----------------------------------------------------------------------------------------DEDUCTION TAB -->  AGGREGATION BASED ON FORMULA TYPE IN REBATE PLAN
			IF Object_id('TEMPDB..#RESULT_NET_SALES') IS NOT NULL
				TRUNCATE TABLE #RESULT_NET_SALES
			ELSE
				CREATE TABLE #RESULT_NET_SALES (
					CCP_DETAILS_SID INT
					,RS_MODEL_SID INT
					,PERIOD_SID INT
					,SALES_PROJECTED_VALUE NUMERIC(22, 6)
					,PROJECTION_SALES NUMERIC(22, 6)
					,PROJECTION_UNITS NUMERIC(22, 6)
					,RP_NET_SALES NUMERIC(22, 6)
					,RS_NET_SALES NUMERIC(22, 6)
					)

			INSERT INTO #RESULT_NET_SALES (
				CCP_DETAILS_SID
				,RS_MODEL_SID
				,PERIOD_SID
				,SALES_PROJECTED_VALUE
				,PROJECTION_SALES
				,PROJECTION_UNITS
				)
			SELECT CCP_DETAILS_SID
				,RS_MODEL_SID
				,PERIOD_SID
				,SALES_PROJECTED_VALUE
				,PROJECTION_SALES
				,PROJECTION_UNITS
			FROM #CONTRACT_DETAILS
			WHERE RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
				OR RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL;

			WITH RP_DISCOUNT_AMOUNT
			AS (
				SELECT CCP_DETAILS_SID
					,RS_MODEL_SID
					,PERIOD_SID
					,PRICE_GROUP_TYPE
					,INDICATOR
					,PROJECTION_SALES
					, CASE 
						WHEN NET_SALES_FORMULA_TYPE = 'DEDUCTION TYPE'
							THEN Sum(PROJECTION_AMOUNT) OVER (
									PARTITION BY PRICE_GROUP_TYPE
									,PERIOD_SID
									) --REBATE PROGRAM TYPE
						ELSE PROJECTION_AMOUNT --PER CCP,
						END AS DISCOUNT_AMOUNT
					,NET_SALES_FORMULA_TYPE
				FROM #RP_NET_SALES_INFO_DEDUCTION
				)
				,RP_PROJECTION_SALES
			AS (
				SELECT CCP_DETAILS_SID
					,RS_MODEL_SID
					,PERIOD_SID
					,PRICE_GROUP_TYPE
					,PROJECTION_SALES
					,PROJECTION_AMOUNT AS DISCOUNT_AMOUNT
					,NET_SALES_FORMULA_TYPE
				FROM #RP_NET_SALES_INFO
				)
				,NSF_RP
			AS (
				SELECT CD.CCP_DETAILS_SID
					,CD.RS_MODEL_SID
					,CD.PERIOD_SID
					, CASE 
						WHEN INDICATOR = '+'
							THEN COALESCE(S.PROJECTION_SALES, D.PROJECTION_SALES, CD.PROJECTION_SALES) + COALESCE(D.DISCOUNT_AMOUNT, S.DISCOUNT_AMOUNT, CD.PROJECTION_AMOUNT)
						WHEN INDICATOR = '-'
							THEN COALESCE(S.PROJECTION_SALES, D.PROJECTION_SALES, CD.PROJECTION_SALES) - COALESCE(D.DISCOUNT_AMOUNT, S.DISCOUNT_AMOUNT, CD.PROJECTION_AMOUNT)
						END aS RP_NET_SALES
				FROM RP_DISCOUNT_AMOUNT D
				INNER JOIN RP_PROJECTION_SALES S ON D.CCP_DETAILS_SID = S.CCP_DETAILS_SID
					AND D.RS_MODEL_SID = S.RS_MODEL_SID
					AND D.PERIOD_SID = S.PERIOD_SID
				INNER JOIN #CONTRACT_DETAILS CD ON CD.CCP_DETAILS_SID = COALESCE(S.CCP_DETAILS_SID, D.CCP_DETAILS_SID)
					AND CD.RS_MODEL_SID = COALESCE(S.RS_MODEL_SID, D.RS_MODEL_SID)
					AND CD.PERIOD_SID = COALESCE(S.PERIOD_SID, D.PERIOD_SID)
				)
				-----------------------------------------------------------------------------------------DEDUCTION TAB -->  AGGREGATION BASED ON FORMULA TYPE IN REBATE SCHEDULE
				,RS_DISCOUNT_AMOUNT
			AS (
				SELECT CCP_DETAILS_SID
					,RS_MODEL_SID
					,PERIOD_SID
					,PRICE_GROUP_TYPE
					,INDICATOR
					,PROJECTION_SALES
					, CASE 
						WHEN NET_SALES_FORMULA_TYPE = 'DEDUCTION TYPE'
							THEN Sum(PROJECTION_AMOUNT) OVER (
									PARTITION BY PRICE_GROUP_TYPE
									,PERIOD_SID
									) --REBATE PROGRAM TYPE
						ELSE PROJECTION_AMOUNT --PER CCP,
						END As DISCOUNT_AMOUNT
					,NET_SALES_FORMULA_TYPE
				FROM #RS_NET_SALES_INFO_DEDUCTION
				)
				,RS_PROJECTION_SALES
			AS (
				SELECT CCP_DETAILS_SID
					,RS_MODEL_SID
					,PERIOD_SID
					,PRICE_GROUP_TYPE
					,PROJECTION_SALES
					,PROJECTION_AMOUNT AS DISCOUNT_AMOUNT
					,NET_SALES_FORMULA_TYPE
				FROM #RS_NET_SALES_INFO
				)
				,NSF_RS
			AS (
				SELECT CD.CCP_DETAILS_SID
					,CD.RS_MODEL_SID
					,CD.PERIOD_SID
					, CASE 
						WHEN INDICATOR = '+'
							THEN COALESCE(S.PROJECTION_SALES, D.PROJECTION_SALES, CD.PROJECTION_SALES) + COALESCE(D.DISCOUNT_AMOUNT, S.DISCOUNT_AMOUNT, CD.PROJECTION_AMOUNT)
						WHEN INDICATOR = '-'
							THEN COALESCE(S.PROJECTION_SALES, D.PROJECTION_SALES, CD.PROJECTION_SALES) - COALESCE(D.DISCOUNT_AMOUNT, S.DISCOUNT_AMOUNT, CD.PROJECTION_AMOUNT)
						END aS RS_NET_SALES
				FROM RS_DISCOUNT_AMOUNT D
				INNER JOIN RS_PROJECTION_SALES S ON D.CCP_DETAILS_SID = S.CCP_DETAILS_SID
					AND D.RS_MODEL_SID = S.RS_MODEL_SID
					AND D.PERIOD_SID = S.PERIOD_SID
				INNER JOIN #CONTRACT_DETAILS CD ON CD.CCP_DETAILS_SID = COALESCE(S.CCP_DETAILS_SID, D.CCP_DETAILS_SID)
					AND CD.RS_MODEL_SID = COALESCE(S.RS_MODEL_SID, D.RS_MODEL_SID)
					AND CD.PERIOD_SID = COALESCE(S.PERIOD_SID, D.PERIOD_SID)
				)
			UPDATE TCNS
			SET TCNS.RP_NET_SALES = PN.RP_NET_SALES
				,TCNS.RS_NET_SALES = SN.RS_NET_SALES
			FROM #RESULT_NET_SALES TCNS
			LEFT OUTER JOIN NSF_RP PN ON TCNS.CCP_DETAILS_SID = PN.CCP_DETAILS_SID
				AND TCNS.RS_MODEL_SID = PN.RS_MODEL_SID
				AND TCNS.PERIOD_SID = PN.PERIOD_SID
			LEFT OUTER JOIN NSF_RS SN ON TCNS.CCP_DETAILS_SID = PN.CCP_DETAILS_SID
				AND TCNS.RS_MODEL_SID = PN.RS_MODEL_SID
				AND TCNS.PERIOD_SID = PN.PERIOD_SID

			SET @D_SQL1 = CONCAT (
					'SELECT @DEDUCTION=1 FROM '
					,@REBATE_INFO
					,'   WHERE  CALCULATION_TYPE = ''REBATE PLAN''
                                  AND ( RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                                         OR RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL )'
					)

			EXEC SP_EXECUTESQL @D_SQL1
				,N'@DEDUCTION INT OUTPUT'
				,@DEDUCTION OUTPUT

			IF @DEDUCTION = 1
			BEGIN
				SET @D_SQL = CONCAT (
						';
                      WITH CONTRACT_DETAILS
                           AS (SELECT CCP_DETAILS_SID,
                                      RS_MODEL_SID,
                                      PERIOD_SID,
                                      SALES_PROJECTED_VALUE=CASE
                                                              WHEN RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL THEN
                                                                CASE
                                                                  WHEN CALCULATION_LEVEL LIKE ''%SINGLE%'' THEN
                                                                    CASE
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY RS_MODEL_SID, PERIOD_YEAR, COMPANY_MASTER_SID)
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY RS_MODEL_SID, PERIOD_YEAR, PERIOD_SEMI, COMPANY_MASTER_SID)
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY RS_MODEL_SID, PERIOD_YEAR, PERIOD_QUARTER, COMPANY_MASTER_SID)
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY RS_MODEL_SID, PERIOD_SID, COMPANY_MASTER_SID)
                                                                    END
                                                                  WHEN CALCULATION_LEVEL LIKE ''%LINE%'' THEN
                                                                    CASE
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY RS_MODEL_SID, CCP_DETAILS_SID, PERIOD_YEAR)
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY RS_MODEL_SID, CCP_DETAILS_SID, PERIOD_YEAR, PERIOD_SEMI)
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY RS_MODEL_SID, CCP_DETAILS_SID, PERIOD_YEAR, PERIOD_QUARTER)
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY RS_MODEL_SID, CCP_DETAILS_SID, PERIOD_SID)
                                                                    END
                                                                  WHEN CALCULATION_LEVEL LIKE ''%BUNDLE%'' THEN
                                                                    CASE
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, PERIOD_YEAR)
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, PERIOD_YEAR, PERIOD_SEMI)
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, PERIOD_YEAR, PERIOD_QUARTER)
                                                                      WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN SUM(RP_NET_SALES)
                                                                                                                  OVER (
                                                                                                                    PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, PERIOD_SID)
                                                                    END
                                                               END
                                                              ELSE RP_NSF_NULL_VALUE
                                                            END,
                                      SALES_PROJECTION_UNITS=CASE
                                                               WHEN CALCULATION_LEVEL LIKE ''%SINGLE%'' THEN
                                                                 CASE
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY RS_MODEL_SID, PERIOD_YEAR, COMPANY_MASTER_SID)
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY RS_MODEL_SID, PERIOD_YEAR, PERIOD_SEMI, COMPANY_MASTER_SID)
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY RS_MODEL_SID, PERIOD_YEAR, PERIOD_QUARTER, COMPANY_MASTER_SID)
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY RS_MODEL_SID, PERIOD_SID, COMPANY_MASTER_SID)
                                                                 END
                                                               WHEN CALCULATION_LEVEL LIKE ''%LINE%'' THEN
                                                                 CASE
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY RS_MODEL_SID, CCP_DETAILS_SID, PERIOD_YEAR)
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY RS_MODEL_SID, CCP_DETAILS_SID, PERIOD_YEAR, PERIOD_SEMI)
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY RS_MODEL_SID, CCP_DETAILS_SID, PERIOD_YEAR, PERIOD_QUARTER)
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY RS_MODEL_SID, CCP_DETAILS_SID, PERIOD_SID)
                                                                 END
                                                               WHEN CALCULATION_LEVEL LIKE ''%BUNDLE%'' THEN
                                                                 CASE
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, PERIOD_YEAR)
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, PERIOD_YEAR, PERIOD_SEMI)
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, PERIOD_YEAR, PERIOD_QUARTER)
                                                                   WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN SUM(PROJECTION_UNITS)
                                                                                                               OVER (
                                                                                                                 PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, PERIOD_SID)
                                                                 END
                                                             END,
                                      CALCULATION_LEVEL,
                                      REBATE_STRUCTURE,
                                      REBATE_FREQUENCY,
                                      TIER_OPERATOR,
                                      PROJECTION_SALES,
                                      PROJECTION_UNITS,
                                      RP_NET_SALES_FORMULA_MASTER_SID,
                                      RS_NET_SALES_FORMULA_MASTER_SID,
                                      RS_NET_SALES,
                                      REBATE_RANGE_BASED_ON,
                                                                item_master_sid
																,REBATE_BASED_ON---------------CEL-345,CEL-346
                               FROM   (SELECT RN.CCP_DETAILS_SID,
                                              RN.RS_MODEL_SID,
                                              RN.PERIOD_SID,
                                              RI.RP_NET_SALES_FORMULA_MASTER_SID,
                                              RI.RS_NET_SALES_FORMULA_MASTER_SID,
                                              RN.RS_NET_SALES,
                                              RN.RP_NET_SALES,
                                              REBATE_FREQUENCY,
                                              REBATE_STRUCTURE,
                                              TIER_OPERATOR,
                                              CALCULATION_LEVEL,
                                              BUNDLE_NO,
                                              P.PERIOD_QUARTER,
                                              P.PERIOD_YEAR,
                                              P.PERIOD_SEMI,
                                              P.PERIOD_MONTH,
                                              RN.PROJECTION_SALES,
                                              RN.PROJECTION_UNITS,
                                              RN.SALES_PROJECTED_VALUE AS RP_NSF_NULL_VALUE,
                                              COMPANY_MASTER_SID,
                                                                             ccp.item_master_sid,
                                              REBATE_RANGE_BASED_ON
											  ,ri.REBATE_BASED_ON---------------CEL-345,CEL-346
                                       FROM   '
						,@REBATE_INFO
						,' RI
                                                                   INNER JOIN '
						,@MASTER_TABLE
						,
						'   SM
                                                                        ON SM.CCP_DETAILS_SID=RI.CCP_DETAILS_SID
                                                                           and SM.RS_MODEL_SID=RI.RS_MODEL_SID AND SM.CHECK_RECORD=1 and  FILTER_CCP=1  ',case when @DEDUCTION_INCLUSION is not null then concat('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END,'
                                                                             AND    CALCULATION_TYPE = ''REBATE PLAN''
                                  AND ( RI.RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                                         OR RI.RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL )

                                                                   INNER JOIN CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID=SM.CCP_DETAILS_SID
                                              INNER JOIN #RESULT_NET_SALES RN
                                                      ON RI.CCP_DETAILS_SID = RN.CCP_DETAILS_SID
                                                         AND RI.RS_MODEL_SID = RN.RS_MODEL_SID
                                              INNER JOIN #PERIOD_CD P
                                                      ON P.PERIOD_SID = RN.PERIOD_SID)A),
                           TIER_INFO
                           AS (SELECT C.CCP_DETAILS_SID,
                                      C.RS_MODEL_SID,
                                      C.PERIOD_SID,
									  CASE WHEN REBATE_STRUCTURE in ( ''TIER'',''LEVEL'') and TIER_OPERATOR = ''$'' and TP.ITEM_PRICING_QUALIFIER= OU.ITEM_PRICING_QUALIFIER_NAME AND (SALES_PROJECTED_VALUE >= TIER_FROM AND SALES_PROJECTED_VALUE <= ISNULL(TIER_TO,SALES_PROJECTED_VALUE) )   THEN   TP.ITEM_PRICING_QUALIFIER  ELSE  ''1'' END PRICE_VALUES,
                                      VALUE=CASE
                                              WHEN REBATE_STRUCTURE = ''TIER'' THEN 
                                                CASE
                                                  WHEN SALES_PROJECTED_VALUE >= TIER_FROM
                                                       AND SALES_PROJECTED_VALUE <= ISNULL(TIER_TO,SALES_PROJECTED_VALUE) THEN
													  CASE WHEN  TP1.FORMULA_TYPE  <> ''SIMPLE'' THEN 
                                                    CASE
                                                      WHEN TIER_OPERATOR = ''%'' THEN ( COALESCE(NULLIF(DISCOUNT_RATE, 0),RETURN_RATE) )
                                                      WHEN TIER_OPERATOR = ''$'' THEN COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(CASE WHEN TP.ITEM_PRICING_QUALIFIER= OU.ITEM_PRICING_QUALIFIER_NAME AND (SALES_PROJECTED_VALUE >= TIER_FROM AND SALES_PROJECTED_VALUE <= ISNULL(TIER_TO,SALES_PROJECTED_VALUE) )  THEN CASE WHEN REBATE_BASED_ON = ''CONTRACT CMS UNITS'' THEN TP.ITEM_PRICE_UPPS ELSE TP.ITEM_PRICE END END, 0), 0)---------------CEL-345,CEL-346,cel-368,cel-1204
                                                    END
												ELSE 
												ISNULL(CFC.COMPLEX_VALUE,0) 
												END
												END
												
                                              WHEN REBATE_STRUCTURE = ''LEVEL'' THEN
                                                CASE
                                                  WHEN TIER_TO < SALES_PROJECTED_VALUE THEN
                                                    CASE
                                                      WHEN TIER_OPERATOR = ''%'' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE(NULLIF(DISCOUNT_RATE, 0), RETURN_RATE )))
                                                      WHEN TIER_OPERATOR = ''$'' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(CASE WHEN TP.ITEM_PRICING_QUALIFIER= OU.ITEM_PRICING_QUALIFIER_NAME AND (SALES_PROJECTED_VALUE >= TIER_FROM AND SALES_PROJECTED_VALUE <= ISNULL(TIER_TO,SALES_PROJECTED_VALUE) )  THEN CASE WHEN REBATE_BASED_ON = ''CONTRACT CMS UNITS'' THEN TP.ITEM_PRICE_UPPS ELSE TP.ITEM_PRICE END END, 0), 0) ) )---------------CEL-345,CEL-346,CEL-368,cel-1204
                                                    END
                                                  WHEN TIER_TO >= SALES_PROJECTED_VALUE THEN
                                                    CASE
                                                      WHEN SALES_PROJECTED_VALUE BETWEEN TIER_FROM AND TIER_TO THEN
                                                        CASE
                                                          WHEN TIER_OPERATOR = ''%'' THEN ( ( IIF(SALES_PROJECTED_VALUE < TIER_TO, SALES_PROJECTED_VALUE, TIER_TO) - TIER_FROM ) * ( COALESCE(NULLIF(DISCOUNT_RATE, 0), RETURN_RATE) ))
                                                          WHEN TIER_OPERATOR = ''$'' THEN ( ( IIF(SALES_PROJECTED_VALUE < TIER_TO, SALES_PROJECTED_VALUE, TIER_TO) - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(CASE WHEN TP.ITEM_PRICING_QUALIFIER= OU.ITEM_PRICING_QUALIFIER_NAME AND (SALES_PROJECTED_VALUE >= TIER_FROM AND SALES_PROJECTED_VALUE <= ISNULL(TIER_TO,SALES_PROJECTED_VALUE) )  THEN CASE WHEN REBATE_BASED_ON = ''CONTRACT CMS UNITS'' THEN TP.ITEM_PRICE_UPPS ELSE TP.ITEM_PRICE END END, 0), 0) ) )---------------CEL-345,CEL-346,CEL-368,cel-1204                                                  END
                                                        END
                                                    END
                                                END
                                              WHEN REBATE_STRUCTURE = ''FLAT'' THEN FLAT_DISCOUNT
                                            END,
                                      PROJECTION_SALES,
                                      PROJECTION_UNITS,
                                      SALES_PROJECTED_VALUE,
                                      REBATE_STRUCTURE,
                                      CALCULATION_LEVEL,
                                      RP_NET_SALES_FORMULA_MASTER_SID,
                                      RS_NET_SALES_FORMULA_MASTER_SID,
                                      RS_NET_SALES,
                                      TIER_OPERATOR,
                                      REBATE_FREQUENCY,
                                      REBATE_RANGE_BASED_ON
                               FROM   CONTRACT_DETAILS C
                                                    JOIN #PERIOD_CD P ON P.PERIOD_SID=C.PERIOD_SID
                                        LEFT JOIN (SELECT ITEM_MASTER_SID
		,PERIOD_SID
		,FORMULA_TYPE
	FROM ' ,@NATIONAL_INFO,') TP1 ON  C.ITEM_MASTER_SID = TP1.ITEM_MASTER_SID
                                           AND C.PERIOD_SID = TP1.PERIOD_SID 
							LEFT JOIN #SIMPLE_FORMULA_CALCULATION TP
                                        ON C.ITEM_MASTER_SID = TP.ITEM_MASTER_SID
                                           AND C.PERIOD_SID = TP.PERIOD_SID
										   AND TP1.FORMULA_TYPE=TP.FORMULA_TYPE
							LEFT JOIN (SELECT CCP_DETAILS_SID,RS_MODEL_SID,PERIOD_SID,ITEM_MASTER_SID,FORMULA_TYPE,COMPLEX_VALUE,TP_UPPS
							FROM  #NATIONAL_INFO) CFC ON CFC.CCP_DETAILS_SID=C.CCP_DETAILS_SID
										   AND CFC.RS_MODEL_SID=C.RS_MODEL_SID
										   AND CFC.PERIOD_SID=C.PERIOD_SID
										   AND CFC.ITEM_MASTER_SID=C.ITEM_MASTER_SID
										   AND TP1.FORMULA_TYPE=CFC.FORMULA_TYPE
										   AND TP_UPPS=CASE WHEN REBATE_BASED_ON = ''CONTRACT CMS UNITS'' THEN 1 ELSE 0 END 
                                LEFT JOIN '
						,@RETURNS_INFO
						,' RP
                                        ON RP.ITEM_MASTER_SID = TP.ITEM_MASTER_SID
                                           AND RP.PERIOD_SID = TP.PERIOD_SID
                                      OUTER APPLY (SELECT DISCOUNT_RATE,
                                                          REBATE_PER_UNIT,
                                                          FLAT_DISCOUNT,
                                                          TIER_FROM,
                                                          TIER_TO=CASE
                                                                    WHEN T.TIER_TO IS NULL THEN SALES_PROJECTED_VALUE
                                                                    ELSE T.TIER_TO
                                                                  END
														  ,T.ITEM_PRICING_QUALIFIER_NAME--------,cel-1204
                                                   FROM   '
						,@TIER_INFO
						,
						' T
                                                   WHERE  C.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                                                           AND C.RS_MODEL_SID = T.RS_MODEL_SID) OU),                           
                     AGGREGATION1
                     AS (SELECT ROW_NUMBER ()
                                  OVER (
                                    PARTITION BY CCP_DETAILS_SID, RS_MODEL_SID, PERIOD_SID
                                    ORDER BY CCP_DETAILS_SID)RN,
                                CCP_DETAILS_SID,
                                RS_MODEL_SID,                               
                                PERIOD_SID,
								VALUE,
								SUM_PROJECTION_DOLLAR=SUM(VALUE)
                                                  OVER (
                                                    PARTITION BY CCP_DETAILS_SID, RS_MODEL_SID, PERIOD_SID),
                                SUM_PROJECTION_PERCENT= VALUE,--FINAL SUM (FOR FLAT AND TIER VALUE = SUM_PROJECTION) BUT FOR LEVEL VALUE FOR SINGLE CCP AND  SUM_PROJECTION=SUM OF SINGLE CCP FOR ALL TIER                                
                                RATIO=( ( CASE
                                            WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT SALES'', ''DOLLARS'' ) THEN PROJECTION_SALES
                                            WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT UNITS'', ''UNITS'' ) THEN PROJECTION_UNITS
                                          END ) / NULLIF(SALES_PROJECTED_VALUE, 0) ),--EITHER PROJECTION_SALES OR PROJECTION_UNITS CONFIRMATION BASED ON REBATE RANGE BASED ON
                                 PROJECTION_SALES,
                                      PROJECTION_UNITS,
                                      SALES_PROJECTED_VALUE,
                                      REBATE_STRUCTURE,
                                      CALCULATION_LEVEL,
                                      RP_NET_SALES_FORMULA_MASTER_SID,
                                      RS_NET_SALES_FORMULA_MASTER_SID,
                                      RS_NET_SALES,
                                      TIER_OPERATOR,
                                      REBATE_FREQUENCY,
                                      REBATE_RANGE_BASED_ON
                         FROM   TIER_INFO WHERE PRICE_VALUES IS NOT NULL
						 )
						,
                     AGGREGATION12
                     AS (SELECT RN, CCP_DETAILS_SID,
                                RS_MODEL_SID,                                
                                PERIOD_SID,
								SUM_PROJECTION_DOLLAR,
								SUM_PROJECTION_PERCENT=SUM(VALUE)
                                                  OVER (
                                                    PARTITION BY CCP_DETAILS_SID, RS_MODEL_SID, PERIOD_SID),
								
                                RATIO,
								 PROJECTION_SALES,
                                      PROJECTION_UNITS,
                                      SALES_PROJECTED_VALUE,
                                      REBATE_STRUCTURE,
                                      CALCULATION_LEVEL,
                                      RP_NET_SALES_FORMULA_MASTER_SID,
                                      RS_NET_SALES_FORMULA_MASTER_SID,
                                      RS_NET_SALES,
                                      TIER_OPERATOR,
                                      REBATE_FREQUENCY,
                                      REBATE_RANGE_BASED_ON FROM AGGREGATION1 WHERE RN=1)
						,
                     AGGREGATION
                     AS (SELECT RN, CCP_DETAILS_SID,
                                RS_MODEL_SID,
                                PERIOD_SID,
								 CASE 
								 WHEN TIER_OPERATOR = ''%'' THEN SUM_PROJECTION_PERCENT
								 WHEN TIER_OPERATOR = ''$'' THEN SUM_PROJECTION_DOLLAR
								 END AS SUM_PROJECTION,
								RATIO,
								 PROJECTION_SALES,
                                      PROJECTION_UNITS,
                                      SALES_PROJECTED_VALUE,
                                      REBATE_STRUCTURE,
                                      CALCULATION_LEVEL,
                                      RP_NET_SALES_FORMULA_MASTER_SID,
                                      RS_NET_SALES_FORMULA_MASTER_SID,
                                      RS_NET_SALES,
                                      TIER_OPERATOR,
                                      REBATE_FREQUENCY,
                                      REBATE_RANGE_BASED_ON FROM AGGREGATION1 WHERE RN=1),
                           DISCOUNT_WITH_NET_SALES
                           AS (SELECT CCP_DETAILS_SID,
                                      RS_MODEL_SID,
                                      PERIOD_SID,
                                      PROJECTION_AMOUNT= AMOUNT,
                                      PROJECTION_RATE= ( AMOUNT / NULLIF(PROJECTION_SALES, 0) ) * 100,
                                      PROJECTION_REBATE_PER_UNIT= ( AMOUNT / NULLIF(PROJECTION_UNITS, 0) )
                               FROM   (SELECT CCP_DETAILS_SID,
                                              RS_MODEL_SID,
                                              PERIOD_SID,
                                              AMOUNT,
                                              PROJECTION_SALES,
                                              PROJECTION_UNITS
                                       FROM   (SELECT CCP_DETAILS_SID,
                                                      RS_MODEL_SID,
                                                      PERIOD_SID,
                                                      AMOUNT= CASE
                                                                WHEN REBATE_STRUCTURE = ''TIER'' THEN SUM_PROJECTION * CASE
                                                                                                                       WHEN TIER_OPERATOR = ''%'' THEN PROJECTION_SALES
                                                                                                                       WHEN TIER_OPERATOR = ''$'' THEN PROJECTION_UNITS
                                                                                                                     END
                                                                WHEN REBATE_STRUCTURE = ''LEVEL'' THEN
                                                                  CASE
                                                                    WHEN CALCULATION_LEVEL LIKE ''%LINE%'' THEN
                                                                      CASE
                                                                        WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN SUM_PROJECTION / 3
                                                                        WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN SUM_PROJECTION / 6
                                                                        WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN SUM_PROJECTION / 12
                                                                        WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN SUM_PROJECTION
                                                                      END
                                                                    ELSE SUM_PROJECTION * RATIO
                                                                  END
                                                                WHEN REBATE_STRUCTURE = ''FLAT'' THEN
                                                                  CASE
                                                                    WHEN CALCULATION_LEVEL LIKE ''%LINE%'' THEN
                                                                      CASE
                                                                        WHEN LEFT(REBATE_FREQUENCY, 1) = ''Q'' THEN SUM_PROJECTION / 3
                                                                        WHEN LEFT(REBATE_FREQUENCY, 1) = ''S'' THEN SUM_PROJECTION / 6
                                                                        WHEN LEFT(REBATE_FREQUENCY, 1) = ''A'' THEN SUM_PROJECTION / 12
                                                                        WHEN LEFT(REBATE_FREQUENCY, 1) = ''M'' THEN SUM_PROJECTION
                                                                      END
                                                                    ELSE SUM_PROJECTION * RATIO
                                                                  END
                                                              END,
                                                      PROJECTION_SALES,
                                                      PROJECTION_UNITS,
                                                      REBATE_STRUCTURE,
                                                      REBATE_FREQUENCY
                                               FROM   AGGREGATION
                                               WHERE  RN = 1)B)A)
                      UPDATE SNDP
                      SET    SNDP.PROJECTION_SALES = CD.PROJECTION_AMOUNT,
                             SNDP.PROJECTION_RPU = CD.PROJECTION_REBATE_PER_UNIT,
                             SNDP.PROJECTION_RATE = CD.PROJECTION_RATE
                      FROM   '
						,@PROJECTION_TABLE
						,' SNDP
                             INNER JOIN DISCOUNT_WITH_NET_SALES CD
                                     ON SNDP.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                                        AND SNDP.RS_MODEL_SID = CD.RS_MODEL_SID
                                        AND SNDP.PERIOD_SID = CD.PERIOD_SID
                                        '
						)

				EXEC sp_executesql @D_SQL
			END

			SET @D_SQL1 = CONCAT (
					'SELECT @DEDUCTION=1 FROM '
					,@REBATE_INFO
					,'     WHERE  CALCULATION_TYPE = ''DEDUCTION CALENDAR''
                                  AND ( RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                                         OR RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL )'
					)

			EXEC SP_EXECUTESQL @D_SQL1
				,N'@DEDUCTION INT OUTPUT'
				,@DEDUCTION OUTPUT

			IF @DEDUCTION = 1
			BEGIN
				SET @D_SQL = CONCAT (
						'UPDATE SNDP
                      SET    SNDP.PROJECTION_SALES = RNS.RS_NET_SALES,
                             SNDP.PROJECTION_RPU = ( ISNULL(RNS.RS_NET_SALES, 0) / NULLIF(SNSP.PROJECTION_UNITS, 0) ),
                             SNDP.PROJECTION_RATE = ( ISNULL(RNS.RS_NET_SALES, 0) / NULLIF(SNSP.PROJECTION_SALES, 0) ) * 100
                      FROM   '
						,@PROJECTION_TABLE
						,' SNDP
                             INNER JOIN #RESULT_NET_SALES RNS
                                     ON SNDP.CCP_DETAILS_SID = RNS.CCP_DETAILS_SID
                                        AND SNDP.RS_MODEL_SID = RNS.RS_MODEL_SID
                                        AND SNDP.PERIOD_SID = RNS.PERIOD_SID
                                      
                             INNER JOIN '
						,@S_PROJECTION_TABLE
						,' SNSP
                                     ON SNDP.CCP_DETAILS_SID = SNSP.CCP_DETAILS_SID
                                        AND SNDP.PERIOD_SID = SNSP.PERIOD_SID
                                       '
						)

				EXEC sp_executesql @D_SQL
			END
		END

		---------------------------------------------------------------------------------------------------------------------------------  NET SALES ENDS
		SET @D_SQL = 'UPDATE SNDP
          SET    SNDP.PROJECTION_SALES = 0,
                 SNDP.PROJECTION_RPU = 0,
                 SNDP.PROJECTION_RATE = 0
          FROM   ' + @PROJECTION_TABLE + ' SNDP
                 JOIN ' + @MASTER_TABLE + ' SNDPM
                   ON SNDP.CCP_DETAILS_SID = SNDPM.CCP_DETAILS_SID
                      AND SNDP.RS_MODEL_SID = SNDPM.RS_MODEL_SID
                     
                      AND SNDPM.CHECK_RECORD = 1 AND  FILTER_CCP=1  '+CASE WHEN @DEDUCTION_INCLUSION IS NOT NULL THEN CONCAT('AND DEDUCTION_INCLUSION =',@DEDUCTION_INCLUSION) END+'
          WHERE  SNDP.PROJECTION_SALES IS NULL
                 AND SNDP.PROJECTION_RPU IS NULL
                 AND SNDP.PROJECTION_RATE IS NULL'

		EXEC sp_executesql @D_SQL
	
	  DECLARE @ROWCNT INT
	 declare @PPA_PROJECTION_TABLE   VARCHAR(200) = CONCAT ('ST_NM_PPA_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @PPA_MASTER_TABLE       VARCHAR(200) = CONCAT ('ST_NM_PPA_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @PPA_ACTUAL_TABLE       VARCHAR(200) = CONCAT ('ST_NM_ACTUAL_PPA_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))


SET @D_SQL = CONCAT(@D_SQL,'SELECT  @ROWCNT =  convert(varchar(4000),count(*))
FROM '+@MASTER_TABLE+' NMD
JOIN RS_CONTRACT RS ON RS.RS_CONTRACT_SID = NMD.RS_CONTRACT_SID
       AND NMD.CHECK_RECORD = 1
       AND FILTER_CCP = 1 AND METHODOLOGY = ''CONTRACT DETAILS''
JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = CALCULATION_TYPE
       AND RS.INBOUND_STATUS <> ''D''
WHERE HT.DESCRIPTION = ''PRICE PROTECTION''
', case when @DEDUCTION_INCLUSION is null then  +'
AND (
              DEDUCTION_INCLUSION = '+convert(varchar(1),@DEDUCTION_INCLUSION)+'
              OR '+convert(varchar(1),@DEDUCTION_INCLUSION)+' IS NULL
              )' end)
			
	EXEC SP_EXECUTESQL @D_SQL
		,N'@ROWCNT INT OUTPUT'
		,@ROWCNT=@ROWCNT OUTPUT



IF @ROWCNT >= 1
BEGIN
       EXEC PRC_NM_PPA_INSERT @PROJECTION_MASTER_SID
              ,@USER_ID
              ,@SESSION_ID

              
SET @D_SQL = 'SELECT TOP 1 @ROWCNT = 1 FROM ' + @PPA_PROJECTION_TABLE

EXEC SP_EXECUTESQL @D_SQL
	,N'@ROWCNT INT OUTPUT'
	,@ROWCNT OUTPUT

IF @ROWCNT = 1
BEGIN

	EXEC [PRC_NM_PPA_PROJ_INIT] @PROJECTION_MASTER_SID
		,@USER_ID
		,@SESSION_ID

	EXEC [PRC_NM_PPA_PROJECTION] @PROJECTION_MASTER_SID
		,@USER_ID
		,@SESSION_ID

	SET @D_SQL = ' UPDATE SNDP
          SET    SNDP.PROJECTION_SALES = PPT.PROJECTION_SALES,
                 SNDP.PROJECTION_RPU = PPT.PROJECTION_DISCOUNT_DOLLAR,
                 SNDP.PROJECTION_RATE = PPT.PROJECTION_RATE
          FROM   ' + @PROJECTION_TABLE + ' SNDP
                 JOIN ' + @MASTER_TABLE + ' SNDPM
                   ON SNDP.CCP_DETAILS_SID = SNDPM.CCP_DETAILS_SID
                      AND SNDP.RS_MODEL_SID = SNDPM.RS_MODEL_SID
					  AND SNDPM.CHECK_RECORD = 1 AND  FILTER_CCP=1  AND  (DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL)
				JOIN ' + @PPA_PROJECTION_TABLE + ' PPT ON PPT.CCP_DETAILS_SID=SNDPM.CCP_DETAILS_SID
				AND PPT.RS_CONTRACT_SID=SNDPM.RS_CONTRACT_SID
				AND PPT.PERIOD_SID=SNDP.PERIOD_SID'
			
	EXEC sp_executesql @D_SQL
		,N'@DEDUCTION_INCLUSION BIT'
		,@DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
END
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

		SELECT @ERRORMESSAGE = Error_message()
			,@ERRORSEVERITY = Error_severity()
			,@ERRORSTATE = Error_state()
			,@ERRORPROCEDURE = Error_procedure()
			,@ERRORLINE = Error_line()
			,@ERRORNUMBER = Error_number()

		RAISERROR (
				@ERRORMESSAGE
				,-- MESSAGE TEXT.
				@ERRORSEVERITY
				,-- SEVERITY.
				@ERRORSTATE
				,-- STATE.
				@ERRORPROCEDURE
				,-- PROCEDURE_NAME.
				@ERRORNUMBER
				,-- ERRORNUMBER
				@ERRORLINE -- ERRORLINE
				);
	END CATCH
END

GO