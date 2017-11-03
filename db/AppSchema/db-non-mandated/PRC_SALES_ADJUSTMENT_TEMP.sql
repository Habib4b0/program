IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_SALES_ADJUSTMENT_TEMP'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_SALES_ADJUSTMENT_TEMP]
  END

  go
CREATE PROCEDURE [dbo].[PRC_SALES_ADJUSTMENT_TEMP]  ( @BASLINE_PERIODS        VARCHAR(1000)=null,
                                                   @SELECTED_PERIODS       VARCHAR(1000),
                                                   @PROJECTION_SID         INT ,
                                                   @FREQUENCY              VARCHAR(50),
                                                   @USER_ID                INT ,
                                                   @SESSION_ID             VARCHAR(50),
                                                   @ADJUSTMENT_TYPE        VARCHAR(20),
                                                   @ADJUSTMENT_BASIS_TEMP       VARCHAR(20),
                                                   @ADJUSTMENT_VARIABLE    CHAR(1),
                                                   @ADJUSTMENT_METHODOLOGY VARCHAR(50),
                                                   @ADJUSTMENT_VALUES      NUMERIC(22, 6),
												   @SALES_INCLUSION BIT =null,
												   @UOM VARCHAR(50)
												   
												 )
AS
  BEGIN
      SET NOCOUNT ON
 
       DECLARE @METHODOLOGY_COUNT INT,
              @VARIABLE_COUNT    INT,
              @OUTER_LOOP        INT=1,
              @INNER_LOOP1       INT=1,
              @METHODOLOGY       VARCHAR(50)='',
              @VARIABLE          VARCHAR(10)=2,
              @PROJECTION_DATE   DATETIME,
              @ADJUSTMENTTYPE    VARCHAR(20)='',
              @FROM_DATE         DATE,
              @FORECAST_TYPE     VARCHAR(20),
              @COUNT             INT=0,
              @START_SID         INT,
              @END_SID           INT,
              @ITEM_UOM          VARCHAR(20) = 'EACH'

          SET @FROM_DATE= (SELECT DATEADD(YY, DATEDIFF(YY, 0, GETDATE()) - 3, 0))
          SET @PROJECTION_DATE = (SELECT DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))
                                  FROM   PROJECTION_MASTER
                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION_SID)
          SET @FREQUENCY = LEFT(@FREQUENCY, 1)
          --SET @FREQUENCY = (SELECT CASE
          --                           WHEN SUBSTRING(@SELECTED_PERIODS, 1, 1) = 'M' THEN 'MONTH'
          --                           WHEN SUBSTRING(@SELECTED_PERIODS, 1, 1) = 'Q' THEN 'QUARTERLY'
          --                           WHEN SUBSTRING(@SELECTED_PERIODS, 1, 1) = 'S' THEN 'SEMI ANNUAL'
          --                           ELSE 'ANNUAL'
          --                         END)
          SET @FORECAST_TYPE= (SELECT FORECASTING_TYPE
                               FROM   PROJECTION_MASTER
                               WHERE  PROJECTION_MASTER_SID = @PROJECTION_SID)
 
          DECLARE @MASTER_TABLE     VARCHAR(200) = CONCAT('ST_', IIF(@FORECAST_TYPE <> 'MANDATED', 'NM', 'M'), '_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                  @ACTUAL_TABLE     VARCHAR(200) = CONCAT('ST_', IIF(@FORECAST_TYPE <> 'MANDATED', 'NM', 'M'), '_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                  @PROJECTION_TABLE VARCHAR(200) = CONCAT('ST_', IIF(@FORECAST_TYPE <> 'MANDATED', 'NM', 'M'), '_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                  @CCP_HIERARCHY    VARCHAR(200) = CONCAT('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
				  @UOM_TABLE                   VARCHAR(100)=Concat('ST_ITEM_UOM_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				  @PRODUCT_FILE_TABLE VARCHAR(200) = Concat('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
 
          IF ( OBJECT_ID('TEMPDB..#SALES_PROJECTION_MASTER') ) IS NOT NULL
            DROP TABLE #SALES_PROJECTION_MASTER
 
          CREATE TABLE #SALES_PROJECTION_MASTER
            (
               CCP_DETAILS_SID     INT,
               METHODOLOGY         VARCHAR(50),
               USER_GROUP          VARCHAR(50),
               CALCULATION_PERIODS VARCHAR(50),
            
               CHECK_RECORD        BIT
            )
 
          IF ( OBJECT_ID('TEMPDB..#SALES_PROJECTION') ) IS NOT NULL
            DROP TABLE #SALES_PROJECTION
 
          CREATE TABLE #SALES_PROJECTION
            (
               CCP_DETAILS_SID        INT,
               ACCOUNT_GROWTH         NUMERIC(22, 6),
               PRODUCT_GROWTH         NUMERIC(22, 6),
               PROJECTION_SALES       NUMERIC(22, 6),
               PROJECTION_UNITS       NUMERIC(22, 6),
               PERIOD_SID             INT,
               ADJUSTMENT_TYPE        VARCHAR(50),
               ADJUSTMENT_BASIS       VARCHAR(50),
               ADJUSTMENT_VARIABLE    CHAR(1),
               ADJUSTMENT_METHODOLOGY VARCHAR(50),
               ADJUSTMENT_VALUES      NUMERIC(22, 6)
            )
 
          IF ( OBJECT_ID('TEMPDB..#ACTUAL_SALES') ) IS NOT NULL
            DROP TABLE #ACTUAL_SALES
 
          CREATE TABLE #ACTUAL_SALES
            (
               CCP_DETAILS_SID          INT,
               PERIOD_SID               INT,
               ACTUAL_SALES             NUMERIC(22, 6),
               ACTUAL_UNITS             NUMERIC(22, 6),
             
            )
 
          IF OBJECT_ID('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL
            DROP TABLE #CCP_DETAILS_TEMP
 
          CREATE TABLE #CCP_DETAILS_TEMP
            (
               CCP_DETAILS_SID     INT,
               COMPANY_MASTER_SID  INT,
               ITEM_MASTER_SID     INT,
               CONTRACT_MASTER_SID INT,
               NEW                 BIT DEFAULT (0)
            )
 
          DECLARE @SQL NVARCHAR(MAX)
 
          SET @SQL='INSERT INTO #CCP_DETAILS_TEMP(CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID)
                                                         SELECT CH.CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID FROM '
                   + @CCP_HIERARCHY
                   + ' CH JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID '
 
          EXEC SP_EXECUTESQL
            @SQL
 
          SET @SQL=''
          SET @SQL =CONCAT('INSERT INTO #SALES_PROJECTION_MASTER
                                         (CCP_DETAILS_SID,
                                         METHODOLOGY,
                                         USER_GROUP,
                                         CALCULATION_PERIODS,
                                        
                                         CHECK_RECORD)
                     SELECT CCP_DETAILS_SID,
                              METHODOLOGY,
                              USER_GROUP,
                              CALCULATION_PERIODS,
                            
                              CHECK_RECORD
                     FROM   ', @MASTER_TABLE, '
						WHERE  FILTER_CCP=1  ',case when @SALES_INCLUSION is not null then concat('ANd SALES_INCLUSION =',@SALES_INCLUSION) END,' 
 
                INSERT INTO #SALES_PROJECTION
                           (
                           CCP_DETAILS_SID
                           ,ACCOUNT_GROWTH       
                           ,PRODUCT_GROWTH       
                           ,PROJECTION_SALES     
                           ,PROJECTION_UNITS     
                           ,PERIOD_SID           
                           ,ADJUSTMENT_TYPE      
                           ,ADJUSTMENT_BASIS     
                           ,ADJUSTMENT_VARIABLE  
                           ,ADJUSTMENT_METHODOLOGY
                           ,ADJUSTMENT_VALUES    
                           )
 
                SELECT
                           STNMSP.CCP_DETAILS_SID
                           ,STNMSP.ACCOUNT_GROWTH       
                           ,STNMSP.PRODUCT_GROWTH       
                           ,STNMSP.PROJECTION_SALES     
                           ,STNMSP.PROJECTION_UNITS     
                           ,STNMSP.PERIOD_SID           
                           ,''',@ADJUSTMENT_TYPE       ,'''
                           ,''',@ADJUSTMENT_BASIS_TEMP ,'''    
                           ,''',@ADJUSTMENT_VARIABLE ,'''  
                           ,''',@ADJUSTMENT_METHODOLOGY ,''' 
                           ,''', isnull(@ADJUSTMENT_VALUES , 0),'''
                FROM   ', @PROJECTION_TABLE, ' STNMSP

 
                INSERT INTO #ACTUAL_SALES(
                           CCP_DETAILS_SID 
                           ,PERIOD_SID             
                           ,ACTUAL_SALES           
                           ,ACTUAL_UNITS           
                          
                           )
                SELECT STNMAS.CCP_DETAILS_SID 
                                    ,STNMAS.PERIOD_SID             
                                    ,STNMAS.ACTUAL_SALES           
                                    ,STNMAS.ACTUAL_UNITS           
                                   
                FROM   ', @ACTUAL_TABLE, ' STNMAS')
 
 
 
          EXEC SP_EXECUTESQL
            @SQL
 
          ------------------ ADJUSTMENT CCP DETAILS -------------------------------------------------
          IF OBJECT_ID('TEMPDB.DBO.#SALES_MASTER', 'U') IS NOT NULL
            DROP TABLE #SALES_MASTER;
 
          CREATE TABLE #SALES_MASTER
            (
               SALES_ID INT,
               ITEM_SID INT
            );
 
          WITH CTE
               AS (SELECT STNMS.CCP_DETAILS_SID AS NM_SALES_ID,
                          CCP.ITEM_MASTER_SID   AS ITEM_SID
                   FROM   #SALES_PROJECTION_MASTER STNMS
                          JOIN #CCP_DETAILS_TEMP CCP
                            ON CCP.CCP_DETAILS_SID = STNMS.CCP_DETAILS_SID
                   WHERE  STNMS.CHECK_RECORD = 1)
          INSERT INTO #SALES_MASTER
                      (SALES_ID,
                       ITEM_SID)
          SELECT NM_SALES_ID,
                 ITEM_SID
          FROM   CTE

          --------------------WAC PRICE FOR THE ITEM STARTS HERE -------------------------------------------
          SET @START_SID = (SELECT PERIOD_SID
                            FROM   PERIOD
                            WHERE  PERIOD_DATE = @FROM_DATE)
          SET @END_SID = (SELECT PERIOD_SID
                          FROM   PERIOD
                          WHERE  PERIOD_DATE = DATEADD(MONTH, DATEDIFF(MONTH, - 1, @PROJECTION_DATE) - 1, - 1)
                                               + 1)
		DECLARE @ACTUAL_PERIOD INT
		SET @ACTUAL_PERIOD=(SELECT PERIOD_SID
                            FROM   PERIOD
                            WHERE  PERIOD_DATE = 
 CONVERT(DATETIME,DATEADD(MM,-1,DATEADD(DD,1,EOMONTH(GETDATE(), 0)))))

           IF OBJECT_ID('TEMPDB.DBO.#ITEM_INFO', 'U') IS NOT NULL
            DROP TABLE #ITEM_INFO;
 
          CREATE TABLE #ITEM_INFO
            (
               SALES_ID   INT,
               ITEM_SID   INT,
			   PERIOD_SID int,
			   WAC_PRICE  INT
            );
		  SET @SQL =CONCAT('	INSERT INTO #ITEM_INFO
			(
			SALES_ID   ,
			ITEM_SID   ,
			PERIOD_SID ,
			WAC_PRICE  )



 SELECT SM.SALES_ID
	,SM.ITEM_SID
	,ITM.PERIOD_SID
	,(EXFACTORY_FORECAST_SALES / EXFACTORY_FORECAST_UNITS) WAC_PRICE
FROM #SALES_MASTER SM
INNER JOIN ',@PRODUCT_FILE_TABLE,' ITM ON itm.ITEM_MASTER_SID = SM.ITEM_SID
JOIN period p ON p.PERIOD_SID = itm.PERIOD_SID
	AND ITM.PERIOD_SID BETWEEN ',@ACTUAL_PERIOD,'
		AND ',@END_SID,'','')

		      EXEC SP_EXECUTESQL
            @SQL

          DECLARE @ITEM_INFO TABLE
            (
               ITEM_ID  VARCHAR(50),
               ITEM_SID INT
            )
 
          INSERT INTO @ITEM_INFO
                      (ITEM_ID,
                       ITEM_SID)
          SELECT DISTINCT IM.ITEM_ID,
                          IM.ITEM_MASTER_SID
          FROM   ITEM_MASTER IM
                 JOIN #SALES_MASTER SM
                   ON IM.ITEM_MASTER_SID = ITEM_SID
 
          DECLARE @ITEMID UDT_ITEM
 
          INSERT INTO @ITEMID
                      (ITEM_MASTER_SID)
          SELECT DISTINCT ITEM_SID
          FROM   @ITEM_INFO
 
DECLARE @ITEM_PRICE TABLE (
	SALES_ID INT
	,ITEM_SID INT
	,PERIOD_SID INT
	,ITEM_PRICE NUMERIC(22, 6)
	)

  INSERT INTO @ITEM_PRICE (
	SALES_ID
	,ITEM_SID
	,PERIOD_SID
	,ITEM_PRICE
	)
SELECT SM.SALES_ID
	,SM.ITEM_SID
	,UDF.PERIOD_SID
	,UDF.ITEM_PRICE
FROM #SALES_MASTER SM
JOIN @ITEM_INFO ITM ON ITM.ITEM_SID = SM.ITEM_SID
JOIN UDF_ITEM_PRICING(@ITEMID, 'WAC', @START_SID, @ACTUAL_PERIOD-1, @ITEM_UOM) UDF ON UDF.ITEM_MASTER_SID = ITM.ITEM_SID

UNION ALL
 SELECT SALES_ID
	,ITEM_SID
	,PERIOD_SID
	, WAC_PRICE FROM #ITEM_INFO

 
          -------------------WAC PRICE FOR THE ITEM ENDS HERE -----------------------------------------
          IF OBJECT_ID('TEMPDB.DBO.#TEMP_BASELINE_PERCENT', 'U') IS NOT NULL
            DROP TABLE #TEMP_BASELINE_PERCENT;
 
          CREATE TABLE #TEMP_BASELINE_PERCENT
            (
               SALES_ID               INT,
               ITEM_SID               INT,
               BASELINE_SALES_PERCENT NUMERIC(22, 6),
               BASELINE_UNITS_PERCENT NUMERIC(22, 6)
            )
 
          IF OBJECT_ID('TEMPDB.DBO.#TEMP_PERIOD_CONT', 'U') IS NOT NULL
            DROP TABLE #TEMP_PERIOD_CONT;
 
          CREATE TABLE #TEMP_PERIOD_CONT
            (
               PERIOD_SID           INT,
               PERIOD_SALES_PERCENT NUMERIC(22, 6),
               PERIOD_UNITS_PERCENT NUMERIC(22, 6)
            )
 
          IF OBJECT_ID('TEMPDB.DBO.#TEMP_FINAL_SALES', 'U') IS NOT NULL
            DROP TABLE #TEMP_FINAL_SALES;
 
          CREATE TABLE #TEMP_FINAL_SALES
            (
               SALES_ID   INT,
               PERIOD_SID INT,
               PROJ_SALES NUMERIC(22, 6),
               PROJ_UNITS NUMERIC(22, 6)
            )
 
          IF OBJECT_ID('TEMPDB.DBO.#TEMP_PROJECTION_SALES', 'U') IS NOT NULL
            DROP TABLE #TEMP_PROJECTION_SALES;
 
          SELECT CCP_DETAILS_SID,
                 PROJECTION_SALES,
                 PROJECTION_UNITS,
                 PERIOD_SID,
                 ADJUSTMENT_TYPE,
                 ADJUSTMENT_BASIS,
                 ADJUSTMENT_METHODOLOGY,
                 ADJUSTMENT_VALUES,
                 ADJUSTMENT_VARIABLE
          INTO   #TEMP_PROJECTION_SALES
          FROM   (SELECT STNMP.CCP_DETAILS_SID,
                         STNMP.PROJECTION_SALES,
                         STNMP.PROJECTION_UNITS,
                         STNMP.PERIOD_SID,
                         STNMP.ADJUSTMENT_TYPE,
                         STNMP.ADJUSTMENT_BASIS,
                         STNMP.ADJUSTMENT_METHODOLOGY,
                         STNMP.ADJUSTMENT_VALUES,
                         STNMP.ADJUSTMENT_VARIABLE
                  FROM   #SALES_PROJECTION STNMP
                  WHERE  EXISTS (SELECT 1
                                 FROM   #SALES_MASTER SM
                                 WHERE  STNMP.CCP_DETAILS_SID = SM.SALES_ID)) A
 
          DECLARE @ADJUSTMENT_BASIS NUMERIC(22, 6)
          DECLARE @ADJUSTMENT_VALUE NUMERIC(22, 6)=0
          DECLARE @PRIOR_FLAG BIT
          DECLARE @CONDITION BIT=0
 
          SELECT @PRIOR_FLAG = 1
          WHERE  EXISTS(SELECT 1
                        FROM   #SALES_PROJECTION A
                               JOIN #SALES_PROJECTION_MASTER C
                                 ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                    AND C.CHECK_RECORD = 1
                        WHERE  PROJECTION_SALES IS NULL)
 
          SELECT @CONDITION = 1
          WHERE  NOT EXISTS(SELECT SM.SALES_ID,
                                   SM.ITEM_SID,
                                   SUM(STNMP.PROJECTION_SALES) AS PROJECTION_SALES,
                                   SUM(STNMP.PROJECTION_UNITS) AS PROJECTION_UNITS,
                                   STNMP.ADJUSTMENT_VARIABLE
                            FROM   #SALES_PROJECTION STNMP
                                   JOIN #SALES_MASTER SM
                                     ON STNMP.CCP_DETAILS_SID = SM.SALES_ID
                                   JOIN (SELECT PERIOD_SID,
                                                CASE
                                                  WHEN @FREQUENCY = 'M' THEN 'M' + CONVERT(VARCHAR(2), P.MONTH) + ' '
                                                                             + CONVERT(VARCHAR(4), P.YEAR)
                                                  WHEN @FREQUENCY = 'Q' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
                                                                             + CONVERT(VARCHAR(4), P.YEAR)
                                                  WHEN @FREQUENCY = 'S' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
                                                                             + CONVERT(VARCHAR(4), P.YEAR)
                                                  ELSE CONVERT(VARCHAR(4), P.YEAR)
                                                END PERIODS
                                         FROM   PERIOD P) SUB
                                     ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                            WHERE  EXISTS (SELECT 1
                                           FROM   UDF_SPLITSTRING(@SELECTED_PERIODS, ',') A
                                           WHERE  A.TOKEN = SUB.PERIODS)
                                   AND ( STNMP.PROJECTION_SALES IS NULL )
                                   AND ( STNMP.PROJECTION_UNITS IS NULL )
                            GROUP  BY SM.SALES_ID,
                                      SM.ITEM_SID,
                                      STNMP.ADJUSTMENT_VARIABLE)
 
          ---------------------------------------------------------------------------------------------------------------------------------------------------------------
          --CASE
          --  WHEN @FREQUENCY = 'MONTH' THEN 'M' + CONVERT(VARCHAR(2), P.MONTH) + ' '
          --                                                                                 + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  WHEN @FREQUENCY = 'QUARTER' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
          --                                                                                   + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  WHEN @FREQUENCY = 'SEMI ANNUAL' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
          --                                                                                       + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  ELSE CONVERT(VARCHAR(4), P.YEAR)
          --                                                END PERIODS
          ---------------------------------------------------------------------------------------------------------------------------------------------------------------
          SET @ADJUSTMENT_BASIS =(SELECT CASE
                                           WHEN MAX(CAST(A.ADJUSTMENT_VARIABLE AS VARCHAR(10))) = '1' THEN ISNULL(SUM(A.PROJECTION_SALES), 0)
                                           ELSE ISNULL(SUM(A.PROJECTION_UNITS), 0)
                                         END ADJUSTMENT_RESULT
                                  FROM   (SELECT SM.SALES_ID,
                                                 SM.ITEM_SID,
                                                 SUM(STNMP.PROJECTION_SALES) AS PROJECTION_SALES,
                                                 SUM(STNMP.PROJECTION_UNITS) AS PROJECTION_UNITS,
                                                 STNMP.ADJUSTMENT_VARIABLE
                                          FROM   #SALES_PROJECTION STNMP
                                                 JOIN #SALES_MASTER SM
                                                   ON STNMP.CCP_DETAILS_SID = SM.SALES_ID
                                                 JOIN (SELECT PERIOD_SID,
                                                              CASE
                                                                WHEN @FREQUENCY = 'M' THEN 'M' + CONVERT(VARCHAR(2), P.MONTH) + ' '
                                                                                           + CONVERT(VARCHAR(4), P.YEAR)
                                                                WHEN @FREQUENCY = 'Q' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
                                                                                           + CONVERT(VARCHAR(4), P.YEAR)
                                                                WHEN @FREQUENCY = 'S' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
                                                                                           + CONVERT(VARCHAR(4), P.YEAR)
                                                                ELSE
                                                                                                              CONVERT(VARCHAR(4), P.YEAR)
                                                              END
                                                                                                          PERIODS
                                                       FROM   PERIOD P) SUB
                                                   ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                                          WHERE  EXISTS (SELECT 1
                                                         FROM   UDF_SPLITSTRING (@SELECTED_PERIODS, ',') A
                                                         WHERE  A.TOKEN = SUB.PERIODS)
                                          GROUP  BY SM.SALES_ID,
                                                    SM.ITEM_SID,
                                                    STNMP.ADJUSTMENT_VARIABLE) A)
 
          ---------------------------------------------------------------------------------------------------------------------------------------------------------------
          --CASE
          --  WHEN @FREQUENCY = 'MONTH' THEN 'M' + CONVERT(VARCHAR(2), P.MONTH) + ' '
          --                                                                                 + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  WHEN @FREQUENCY = 'QUARTER' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
          --                                                                                   + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  WHEN @FREQUENCY = 'SEMI ANNUAL' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
          --                                                                                       + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  ELSE CONVERT(VARCHAR(4), P.YEAR)
          --                                                END PERIODS
          ---------------------------------------------------------------------------------------------------------------------------------------------------------------
          IF @CONDITION = 1
            SET @ADJUSTMENT_VALUE=0
          ELSE
            SET @ADJUSTMENT_VALUE =(SELECT CASE
                                             WHEN MAX(CAST(A.ADJUSTMENT_VARIABLE AS VARCHAR(10))) = '1' THEN SUM(A.PROJECTION_SALES)
                                             ELSE SUM(A.PROJECTION_UNITS)
                                           END ADJUSTMENT_RESULT
                                    FROM   (SELECT SM.SALES_ID,
                                                   SM.ITEM_SID,
                                                   SUM(STNMP.PROJECTION_SALES) AS PROJECTION_SALES,
                                                   SUM(STNMP.PROJECTION_UNITS) AS PROJECTION_UNITS,
                                                   STNMP.ADJUSTMENT_VARIABLE
                                            FROM   #SALES_PROJECTION STNMP
                                                   JOIN #SALES_MASTER SM
                                                     ON STNMP.CCP_DETAILS_SID = SM.SALES_ID
                                                   JOIN (SELECT PERIOD_SID,
                                                                CASE
                                                                  WHEN @FREQUENCY = 'M' THEN 'M' + CONVERT(VARCHAR(2), P.MONTH) + ' '
                                                                                             + CONVERT(VARCHAR(4), P.YEAR)
                                                                  WHEN @FREQUENCY = 'Q' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
                                                                                             + CONVERT(VARCHAR(4), P.YEAR)
                                                                  WHEN @FREQUENCY = 'S' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
                                                                                             + CONVERT(VARCHAR(4), P.YEAR)
                                                                  ELSE CONVERT(VARCHAR(4), P.YEAR)
                                                                END PERIODS
                                                         FROM   PERIOD P) SUB
                                                     ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                                            WHERE  EXISTS (SELECT 1
                                                           FROM   UDF_SPLITSTRING(@SELECTED_PERIODS, ',') A
                                                           WHERE  A.TOKEN = SUB.PERIODS)
                                                   AND ( STNMP.PROJECTION_SALES IS NULL )
                                                   AND ( STNMP.PROJECTION_UNITS IS NULL )
                                            GROUP  BY SM.SALES_ID,
                                                      SM.ITEM_SID,
                                                      STNMP.ADJUSTMENT_VARIABLE) A)
 
          ---------------------------------------------------------------------------------------------------------------------------------------------------------------
          --CASE
          --  WHEN @FREQUENCY = 'MONTH' THEN 'M' + CONVERT(VARCHAR(2), P.MONTH) + ' '
          --                                                                                 + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  WHEN @FREQUENCY = 'QUARTER' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
          --                                                                                   + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  WHEN @FREQUENCY = 'SEMI ANNUAL' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
          --                                                                                       + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  ELSE CONVERT(VARCHAR(4), P.YEAR)
          --                                                END PERIODS
          ---------------------------------------------------------------------------------------------------------------------------------------------------------------
          --------------ADJUSTMENT SALES AND PERIOD DETAILS FOR SELECTED PERIODS --------------------------
          DECLARE @PERIOD_CONT TABLE
            (
               PERIOD_SID INT,
               PROJ_SALES NUMERIC(22, 6),
               PROJ_UNITS NUMERIC(22, 6)
            )
 
          INSERT INTO @PERIOD_CONT
                      (PERIOD_SID,
                       PROJ_SALES,
                       PROJ_UNITS)
          SELECT STNMP.PERIOD_SID,
                 SUM(STNMP.PROJECTION_SALES) AS PROJ_SALES,
                 SUM(STNMP.PROJECTION_UNITS) AS PROJ_UNITS
          FROM   #SALES_PROJECTION STNMP
          WHERE  EXISTS (SELECT 1
                         FROM   #SALES_MASTER SM
                         WHERE  SM.SALES_ID = STNMP.CCP_DETAILS_SID)
                 AND STNMP.ADJUSTMENT_VALUES IS NOT NULL
          GROUP  BY STNMP.PERIOD_SID
 
          ---------------- CONTRIBUTION FOR MONTH IN SELECTED PERIODS --------------------------
          IF ( @ADJUSTMENT_VALUE IS NULL )
              OR ( @PRIOR_FLAG = 1
                   AND @ADJUSTMENT_BASIS = 0 )
            BEGIN
                SELECT @COUNT = COUNT(1)
                FROM   PERIOD SUB
                WHERE  EXISTS(SELECT 1
                              FROM   UDF_SPLITSTRING(@SELECTED_PERIODS, ',') A
                              WHERE  A.TOKEN = CASE
                                                 WHEN @FREQUENCY = 'M' THEN 'M' + CAST( SUB.MONTH AS VARCHAR(2)) + ' '
                                                                            + CAST(SUB.YEAR AS VARCHAR(4))
                                                 WHEN @FREQUENCY = 'Q' THEN 'Q' + CAST( SUB.QUARTER AS VARCHAR(2)) + ' '
                                                                            + CAST(SUB.YEAR AS VARCHAR(4))
                                                 WHEN @FREQUENCY = 'S' THEN 'S' + CAST( SUB.SEMI_ANNUAL AS VARCHAR(2))
                                                                            + ' ' + CAST(SUB.YEAR AS VARCHAR(4))
                                                 ELSE CAST(SUB.YEAR AS VARCHAR(4))
                                               END)
                                                                          
                ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                --CASE
                --  WHEN @FREQUENCY = 'MONTH' THEN 'M' + CONVERT(VARCHAR(2), P.MONTH) + ' '
                --                                                                                 + CONVERT(VARCHAR(4), P.YEAR)
                --                                                  WHEN @FREQUENCY = 'QUARTER' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
                --                                                                                   + CONVERT(VARCHAR(4), P.YEAR)
                --                                                  WHEN @FREQUENCY = 'SEMI ANNUAL' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
                --                                                                                       + CONVERT(VARCHAR(4), P.YEAR)
                --                                                  ELSE CONVERT(VARCHAR(4), P.YEAR)
                --                                                END PERIODS
                ---------------------------------------------------------------------------------------------------------------------------------------------------------------
                INSERT INTO #TEMP_PERIOD_CONT
                            (PERIOD_SID,
                             PERIOD_SALES_PERCENT,
                             PERIOD_UNITS_PERCENT)
                SELECT PERIOD_SID,
                       COALESCE(1.0 / NULLIF(@COUNT, 0), 0) AS PERIOD_SALES_PERCENT,
                       COALESCE(1.0 / NULLIF(@COUNT, 0), 0) AS PERIOD_UNITS_PERCENT
                FROM   (SELECT PC.PERIOD_SID,
                               PC.PROJ_SALES,
                               PC.PROJ_UNITS
                        FROM   @PERIOD_CONT PC
                               JOIN (SELECT PERIOD_SID,
                                            CASE
                                              WHEN @FREQUENCY = 'M' THEN 'M' + CONVERT(VARCHAR(3), P.MONTH) + ' '
                                                                         + CONVERT(VARCHAR(4), P.YEAR)
                                              WHEN @FREQUENCY = 'Q' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
                                                                         + CONVERT(VARCHAR(4), P.YEAR)
                                              WHEN @FREQUENCY = 'S' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
                                                                         + CONVERT(VARCHAR(4), P.YEAR)
                                              ELSE CONVERT(VARCHAR(4), P.YEAR)
                                            END PERIODS
                                     FROM   PERIOD P) SUB
                                 ON PC.PERIOD_SID = SUB.PERIOD_SID
                        WHERE  EXISTS (SELECT 1
                                       FROM   UDF_SPLITSTRING(@SELECTED_PERIODS, ',') A
                                       WHERE  A.TOKEN = SUB.PERIODS)) A
                                                                   
            ---------------------------------------------------------------------------------------------------------------------------------------------------------------
            --CASE
            --  WHEN @FREQUENCY = 'MONTH' THEN 'M' + CONVERT(VARCHAR(2), P.MONTH) + ' '
            --                                                                                 + CONVERT(VARCHAR(4), P.YEAR)
            --                                                  WHEN @FREQUENCY = 'QUARTER' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
            --                                                                                   + CONVERT(VARCHAR(4), P.YEAR)
            --                                                  WHEN @FREQUENCY = 'SEMI ANNUAL' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
            --                                                                                       + CONVERT(VARCHAR(4), P.YEAR)
            --                                                  ELSE CONVERT(VARCHAR(4), P.YEAR)
            --                                                END PERIODS
            ---------------------------------------------------------------------------------------------------------------------------------------------------------------
            END
          ELSE
            BEGIN
                INSERT INTO #TEMP_PERIOD_CONT
                            (PERIOD_SID,
                             PERIOD_SALES_PERCENT,
                             PERIOD_UNITS_PERCENT)
                SELECT PERIOD_SID,
                       COALESCE(PROJ_SALES / NULLIF(SUM(PROJ_SALES)
                                                      OVER(), 0), 0)  AS SALES_PERIOD_PERCENT,
                       COALESCE (PROJ_UNITS / NULLIF(SUM(PROJ_UNITS)
                                                       OVER(), 0), 0) AS UNITS_PERIOD_PERCENT
                FROM   (SELECT PC.PERIOD_SID,
                               PC.PROJ_SALES,
                               PC.PROJ_UNITS
                        FROM   @PERIOD_CONT PC
                               JOIN (SELECT PERIOD_SID,
                                            CASE
                                              WHEN @FREQUENCY = 'M' THEN 'M' + CONVERT(VARCHAR(3), P.MONTH) + ' '
                                                                         + CONVERT(VARCHAR(4), P.YEAR)
                                              WHEN @FREQUENCY = 'Q' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
                                                                         + CONVERT(VARCHAR(4), P.YEAR)
                                              WHEN @FREQUENCY = 'S' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
                                                                         + CONVERT(VARCHAR(4), P.YEAR)
                                              ELSE CONVERT(VARCHAR(4), P.YEAR)
                                            END PERIODS
                                     FROM   PERIOD P) SUB
                                 ON PC.PERIOD_SID = SUB.PERIOD_SID
                        WHERE  EXISTS (SELECT 1
                                       FROM   UDF_SPLITSTRING(@SELECTED_PERIODS, ',') A
                                       WHERE  A.TOKEN = SUB.PERIODS)) A
            END
 
          ---------------------------------------------------------------------------------------------------------------------------------------------------------------
          --CASE
          --  WHEN @FREQUENCY = 'MONTH' THEN 'M' + CONVERT(VARCHAR(2), P.MONTH) + ' '
          --                                                                                 + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  WHEN @FREQUENCY = 'QUARTER' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
          --                                                                                   + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  WHEN @FREQUENCY = 'SEMI ANNUAL' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
          --                                                                                       + CONVERT(VARCHAR(4), P.YEAR)
          --                                                  ELSE CONVERT(VARCHAR(4), P.YEAR)
          --                                                END PERIODS
          ---------------------------------------------------------------------------------------------------------------------------------------------------------------
          SELECT @METHODOLOGY_COUNT = COUNT(DISTINCT ADJUSTMENT_METHODOLOGY)
          FROM   #TEMP_PROJECTION_SALES
 
          WHILE ( @OUTER_LOOP <= @METHODOLOGY_COUNT )
            BEGIN
                SELECT DISTINCT TOP 1 @METHODOLOGY = ADJUSTMENT_METHODOLOGY
                FROM   #TEMP_PROJECTION_SALES
                WHERE  @METHODOLOGY <> ADJUSTMENT_METHODOLOGY
 
                DECLARE @METHODOLOGY_HISTORICAL_UNITS VARCHAR(100)
                DECLARE @METHODOLOGY_HISTORICAL_SALES VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = 'HISTORICAL % OF BUSINESS'),--MONTHLY_ACTUAL/BASELINE_PERIODS_ACTUAL
                        @METHODOLOGY_FORECAST_UNITS   VARCHAR(100),
                        @METHODOLOGY_FORECAST_SALES   VARCHAR(100)=(SELECT FORMULA
                          FROM   FORECASTING_FORMULA
                          WHERE  FORMULA_NAME = 'FORECAST % OF BUSINESS'),--MONTHLY_PROJECTION/BASELINE_PERIODS_PROJECTION
                        @INSERT_METHODOLOGY           NVARCHAR(MAX)
 
                --@PRIOR_FORECAST_PROJ VARCHAR(100)
                --SET @PRIOR_FORECAST_PROJ=
                SET @METHODOLOGY_HISTORICAL_SALES=(SELECT REPLACE(REPLACE(@METHODOLOGY_HISTORICAL_SALES, 'MONTHLY_ACTUAL', 'ACTUAL_SALES'), 'BASELINE_PERIODS_ACTUAL', 'NULLIF(BASELINE_SALES,0)'))
                SET @METHODOLOGY_HISTORICAL_UNITS=(SELECT REPLACE(REPLACE(@METHODOLOGY_HISTORICAL_SALES, 'ACTUAL_SALES', 'ACTUAL_UNITS'), 'NULLIF(BASELINE_SALES,0)', 'NULLIF(BASELINE_UNITS,0)'))
                SET @METHODOLOGY_FORECAST_SALES=(SELECT REPLACE(REPLACE(@METHODOLOGY_HISTORICAL_SALES, 'ACTUAL_SALES', 'PROJECTION_SALES'), 'BASELINE_PERIODS_PROJECTION', 'NULLIF(BASELINE_SALES,0)'))
                SET @METHODOLOGY_FORECAST_UNITS=(SELECT REPLACE(REPLACE(@METHODOLOGY_FORECAST_SALES, 'PROJECTION_SALES', 'PROJECTION_UNITS'), 'NULLIF(BASELINE_SALES,0)', 'NULLIF(BASELINE_UNITS,0)'))
 
                --------------CONTRIBUTION % FOR EACH LINE IN BASELINE PERIODS ------------------------
                IF @METHODOLOGY = 'HISTORICAL % OF BUSINESS'
                  BEGIN
                      SET @INSERT_METHODOLOGY = ' DECLARE @FREQUENCY VARCHAR(30) = '''
                                                + @FREQUENCY + '''
                                    INSERT INTO #TEMP_BASELINE_PERCENT
                                  (
                                                          SALES_ID,
                                   ITEM_SID,
                                   BASELINE_SALES_PERCENT,
                                   BASELINE_UNITS_PERCENT)
                                  SELECT SALES_ID,
                             ITEM_SID,
                             '
                                                + @METHODOLOGY_HISTORICAL_SALES
                                                + ' AS BASELINE_SALES_PERCENTAGE,
                           '
                                                + @METHODOLOGY_HISTORICAL_UNITS
                                                + ' AS BASELINE_UNITS_PERCENTAGE
                      FROM  
                                    ( SELECT A.SALES_ID,
                             A.ITEM_SID,
                              A.ACTUAL_SALES,
                                                SUM(A.ACTUAL_SALES)OVER ()AS BASELINE_SALES,
                             A.ACTUAL_UNITS,
                                                SUM(A.ACTUAL_UNITS)OVER () AS BASELINE_UNITS
                                   
                           FROM     (SELECT SM.SALES_ID,
                                     SM.ITEM_SID,
                               SUM(STNMA.ACTUAL_SALES)AS ACTUAL_SALES,
                               SUM(STNMA.ACTUAL_UNITS)AS ACTUAL_UNITS
                              FROM   #ACTUAL_SALES STNMA
                                     JOIN #SALES_MASTER SM
                                       ON STNMA.CCP_DETAILS_SID = SM.SALES_ID
                                     JOIN (SELECT PERIOD_SID, CASE WHEN @FREQUENCY = ''M'' THEN ''M'' + CAST( P.MONTH AS VARCHAR(3)) + '''
                                                + ' '
                                                + '''+ CAST(P.YEAR AS VARCHAR(4))
                                                                                             WHEN @FREQUENCY = ''Q'' THEN ''Q'' + CAST( P.QUARTER AS VARCHAR(2)) + '''
                                                + ' '
                                                + '''+ CAST(P.YEAR AS VARCHAR(4))
                                                                                                                 WHEN @FREQUENCY = ''S'' THEN ''S'' + CAST( P.SEMI_ANNUAL AS VARCHAR(2)) + '''
                                                + ' '
                                                + '''+ CAST(P.YEAR AS VARCHAR(4))
                                                                                                                 ELSE   CAST(P.YEAR AS VARCHAR(4))
                                                                                                                 END PERIODS FROM PERIOD P) SUB
                                       ON STNMA.PERIOD_SID = SUB.PERIOD_SID
                              WHERE  EXISTS (SELECT 1 FROM   UDF_SPLITSTRING('''
                                                + @BASLINE_PERIODS + ''',''' + ','
                                                + ''') A
                                             WHERE  A.TOKEN = SUB.PERIODS)
                              GROUP  BY SM.SALES_ID,
                                        SM.ITEM_SID) A)J'
                  ----------------------------------------------------------------------------------------------------------------------------
                  ----------------------------------------------------------------------------------------------------------------------------
                  END
                          
                ELSE
                  BEGIN
                      IF ( @ADJUSTMENT_VALUE IS NULL )
                          OR ( @PRIOR_FLAG = 1
                               AND @ADJUSTMENT_BASIS = 0 )
                        BEGIN
                            SELECT @COUNT = COUNT(SALES_ID)
                            FROM   #SALES_MASTER
                                        
 
                            INSERT INTO #TEMP_BASELINE_PERCENT
                                        (SALES_ID,
                                         ITEM_SID,
                                         BASELINE_SALES_PERCENT,
                                         BASELINE_UNITS_PERCENT)
                            SELECT DISTINCT SALES_ID,
                                            ITEM_SID,
                                            COALESCE(1.0 / NULLIF(@COUNT, 0), 0) AS BASELINE_SALES_PERCENTAGE,
                                            COALESCE(1.0 / NULLIF(@COUNT, 0), 0) AS BASELINE_UNITS_PERCENTAGE
                            FROM   (SELECT SM.SALES_ID,
                                           SM.ITEM_SID,
                                           STNMP.PROJECTION_SALES AS PROJECTION_SALES,
                                           STNMP.PROJECTION_UNITS AS PROJECTION_UNITS
                                    FROM   #SALES_PROJECTION STNMP
                                           JOIN #SALES_MASTER SM
                                             ON STNMP.CCP_DETAILS_SID = SM.SALES_ID
                                           JOIN (SELECT PERIOD_SID,
                                                        CASE
                                                          WHEN @FREQUENCY = 'M' THEN 'M' + CONVERT(VARCHAR(3), P.MONTH) + ' '
                                                                                     + CONVERT(VARCHAR(4), P.YEAR)
                                                          WHEN @FREQUENCY = 'Q' THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' '
                                                                                     + CONVERT(VARCHAR(4), P.YEAR)
                                                          WHEN @FREQUENCY = 'S' THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' '
                                                                                     + CONVERT(VARCHAR(4), P.YEAR)
                                                          ELSE CONVERT(VARCHAR(4), P.YEAR)
                                                        END PERIODS
                                                 FROM   PERIOD P) SUB
                                             ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                                    WHERE  EXISTS (SELECT 1
                                                   FROM   UDF_SPLITSTRING(@BASLINE_PERIODS, ',') A
                                                   WHERE  A.TOKEN = SUB.PERIODS))A
                        END
                                 
                      ELSE
                        BEGIN
                                        
                            SET @INSERT_METHODOLOGY = 'DECLARE @FREQUENCY VARCHAR(30) = '''
                                                      + @FREQUENCY + '''
                                                INSERT INTO #TEMP_BASELINE_PERCENT
                                  (SALES_ID,
                                   ITEM_SID,
                                   BASELINE_SALES_PERCENT,
                                   BASELINE_UNITS_PERCENT)
                      SELECT SALES_ID,
                             ITEM_SID,
                             '
                                                      + @METHODOLOGY_FORECAST_SALES
                                                      + ' AS BASELINE_SALES_PERCENTAGE,
                            '
                                                      + @METHODOLOGY_FORECAST_UNITS
                                                      + ' AS BASELINE_UNITS_PERCENTAGE
                      FROM  (
                                    SELECT A.SALES_ID,
                                     A.ITEM_SID,
                                    A.PROJECTION_SALES,
                                                              SUM(A.PROJECTION_SALES)OVER() AS BASELINE_SALES,
                                     A.PROJECTION_UNITS,
                                                              SUM(A.PROJECTION_UNITS)OVER() AS BASELINE_UNITS
                           FROM     (SELECT SM.SALES_ID,
                                     SM.ITEM_SID,
                                     SUM(STNMP.PROJECTION_SALES)AS PROJECTION_SALES,
                                     SUM(STNMP.PROJECTION_UNITS) AS PROJECTION_UNITS
                              FROM   #SALES_PROJECTION STNMP
                                     JOIN #SALES_MASTER SM
                                       ON STNMP.CCP_DETAILS_SID = SM.SALES_ID
                                     JOIN (SELECT PERIOD_SID,CASE  WHEN @FREQUENCY = ''M'' THEN ''M'' + CAST( P.MONTH AS VARCHAR(3)) + '''
                                                      + ' '
                                                      + '''+ CAST(P.YEAR AS VARCHAR(4))
                                                                                             WHEN @FREQUENCY = ''Q'' THEN ''Q'' + CAST( P.QUARTER AS VARCHAR(2)) + '''
                                                      + ' '
                                                      + '''+ CAST(P.YEAR AS VARCHAR(4))
                                                                                                                 WHEN @FREQUENCY = ''S'' THEN ''S'' + CAST( P.SEMI_ANNUAL AS VARCHAR(2)) + '''
                                                      + ' '
                                                      + '''+ CAST(P.YEAR AS VARCHAR(4))
                                                                                                                 ELSE   CAST(P.YEAR AS VARCHAR(4))
                                                                                                                 END PERIODS FROM PERIOD P) SUB
                                       ON STNMP.PERIOD_SID = SUB.PERIOD_SID
                              WHERE  EXISTS (SELECT 1 FROM   UDF_SPLITSTRING('''
                                                      + @BASLINE_PERIODS + ''',''' + ','
                                                      + ''') A
                                             WHERE  A.TOKEN = SUB.PERIODS)
                              GROUP  BY SM.SALES_ID,
                                        SM.ITEM_SID) A)J'
                        END
                  END
 
                --PRINT @INSERT_METHODOLOGY
                EXECUTE SP_EXECUTESQL
                  @INSERT_METHODOLOGY
 
                --------------------------------------------------------------------------------------------------------------------------------
                --------------------------------------------------------------------------------------------------------------------------------
                SELECT @VARIABLE_COUNT = COUNT(*)
                FROM   (SELECT DISTINCT ADJUSTMENT_VARIABLE,
                                        ADJUSTMENT_TYPE
                        FROM   #TEMP_PROJECTION_SALES
                        WHERE  ADJUSTMENT_METHODOLOGY = @METHODOLOGY) A
 
                WHILE ( @INNER_LOOP1 <= @VARIABLE_COUNT )
                  BEGIN
                      SELECT DISTINCT TOP 1 @VARIABLE = ADJUSTMENT_VARIABLE,
                                            @ADJUSTMENTTYPE = ADJUSTMENT_TYPE
                      FROM   #TEMP_PROJECTION_SALES
                      WHERE  ( @ADJUSTMENTTYPE <> ADJUSTMENT_TYPE
                               AND @VARIABLE <> ADJUSTMENT_VARIABLE )
 
                      IF @VARIABLE = '1'
                        BEGIN
                            IF @ADJUSTMENTTYPE = 'OVERRIDE'
                              BEGIN
 
                                                      
                                  INSERT INTO #TEMP_FINAL_SALES
                                              (SALES_ID,
                                               PERIOD_SID,
                                               PROJ_SALES,
                                               PROJ_UNITS)
                                  SELECT A.CCP_DETAILS_SID,
                                         A.PERIOD_SID,
                                         ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_SALES_PERCENT )                               AS SALES,
                                         ( ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_SALES_PERCENT ) / NULLIF(A.ITEM_PRICE, 0) ) AS UNITS
                                  FROM   (SELECT TPS.CCP_DETAILS_SID,
                                                 TPS.PERIOD_SID,
                                                 TPC.PERIOD_SALES_PERCENT,
                                                 CASE
                                                   WHEN TPS.ADJUSTMENT_BASIS = 'AMOUNT' THEN TPS.ADJUSTMENT_VALUES
                                                   ELSE ( TPS.ADJUSTMENT_VALUES / 100 ) * @ADJUSTMENT_BASIS
                                                 END ADJUSTMENT_VALUES,
                                                 IP.ITEM_PRICE,
                                                 TBP.BASELINE_SALES_PERCENT
                                         FROM   #TEMP_PROJECTION_SALES TPS
                                                 JOIN #TEMP_PERIOD_CONT TPC
                                                   ON TPC.PERIOD_SID = TPS.PERIOD_SID
                                                 JOIN #TEMP_BASELINE_PERCENT TBP
                                                   ON TBP.SALES_ID = TPS.CCP_DETAILS_SID
                                                 LEFT JOIN @ITEM_PRICE IP
                                                        ON IP.SALES_ID = TPS.CCP_DETAILS_SID
                                                           AND IP.SALES_ID = TBP.SALES_ID
                                                           AND IP.PERIOD_SID = TPS.PERIOD_SID
                                                           AND IP.PERIOD_SID = TPC.PERIOD_SID
                                          WHERE  TPS.ADJUSTMENT_METHODOLOGY = @METHODOLOGY
                                                 AND TPS.ADJUSTMENT_VARIABLE = @VARIABLE
                                                 AND TPS.ADJUSTMENT_TYPE = @ADJUSTMENTTYPE) A
                              END
                            ELSE
                              BEGIN
                                  INSERT INTO #TEMP_FINAL_SALES
                                              (SALES_ID,
                                               PERIOD_SID,
                                               PROJ_SALES,
                                               PROJ_UNITS)
                                  SELECT A.CCP_DETAILS_SID,
                                         A.PERIOD_SID,
                                         ( ISNULL(A.PROJECTION_SALES, 0) + ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_SALES_PERCENT ) )                               AS SALES,
                                         ( ( ISNULL(A.PROJECTION_SALES, 0) + ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_SALES_PERCENT ) ) / NULLIF(A.ITEM_PRICE, 0) ) AS UNITS
                                  FROM   (SELECT TPS.CCP_DETAILS_SID,
                                                 TPS.PERIOD_SID,
                                                 TPC.PERIOD_SALES_PERCENT,
                                                 CASE
                                                   WHEN TPS.ADJUSTMENT_BASIS = 'AMOUNT' THEN TPS.ADJUSTMENT_VALUES
                                                   ELSE ( TPS.ADJUSTMENT_VALUES / 100 ) * @ADJUSTMENT_BASIS
                                                 END ADJUSTMENT_VALUES,
                                                 IP.ITEM_PRICE,
                                                 TBP.BASELINE_SALES_PERCENT,
                                                 TPS.PROJECTION_SALES
                                          FROM   #TEMP_PROJECTION_SALES TPS
                                                 JOIN #TEMP_PERIOD_CONT TPC
                                                   ON TPC.PERIOD_SID = TPS.PERIOD_SID
                                                 JOIN #TEMP_BASELINE_PERCENT TBP
                                                   ON TBP.SALES_ID = TPS.CCP_DETAILS_SID
                                                 LEFT JOIN @ITEM_PRICE IP
                                                        ON IP.SALES_ID = TPS.CCP_DETAILS_SID
                                                           AND IP.SALES_ID = TBP.SALES_ID
                                                           AND IP.PERIOD_SID = TPS.PERIOD_SID
                                                           AND IP.PERIOD_SID = TPC.PERIOD_SID
                                          WHERE  TPS.ADJUSTMENT_METHODOLOGY = @METHODOLOGY
                                                 AND TPS.ADJUSTMENT_VARIABLE = @VARIABLE
                                                 AND TPS.ADJUSTMENT_TYPE = @ADJUSTMENTTYPE) A
                              END
                        END
                      ELSE
                        BEGIN
                            IF @ADJUSTMENTTYPE = 'OVERRIDE'
                              BEGIN
                                                      
                                  INSERT INTO #TEMP_FINAL_SALES
                                              (SALES_ID,
                                               PERIOD_SID,
                                               PROJ_SALES,
                                               PROJ_UNITS)
                                  SELECT A.CCP_DETAILS_SID,
                                         A.PERIOD_SID,
                                         ( ( A.PERIOD_UNITS_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_UNITS_PERCENT ) * ( A.ITEM_PRICE ) AS SALES,
                                         ( ( A.PERIOD_UNITS_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_UNITS_PERCENT )                    AS UNITS
                                  FROM   (SELECT TPS.CCP_DETAILS_SID,
                                                 TPS.PERIOD_SID,
                                                 TPC.PERIOD_UNITS_PERCENT,
                                                 CASE
                                                   WHEN TPS.ADJUSTMENT_BASIS = 'AMOUNT' THEN TPS.ADJUSTMENT_VALUES
                                                   ELSE ( TPS.ADJUSTMENT_VALUES / 100 ) * @ADJUSTMENT_BASIS
                                                 END ADJUSTMENT_VALUES,
                                                 IP.ITEM_PRICE,
                                                 TBP.BASELINE_UNITS_PERCENT
                                          FROM   #TEMP_PROJECTION_SALES TPS
                                                 JOIN #TEMP_PERIOD_CONT TPC
                                                   ON TPC.PERIOD_SID = TPS.PERIOD_SID
                                                 JOIN #TEMP_BASELINE_PERCENT TBP
                                                   ON TBP.SALES_ID = TPS.CCP_DETAILS_SID
                                                 LEFT JOIN @ITEM_PRICE IP
                                                        ON IP.SALES_ID = TPS.CCP_DETAILS_SID
                                                           AND IP.SALES_ID = TBP.SALES_ID
                                                           AND IP.PERIOD_SID = TPS.PERIOD_SID
                                                           AND IP.PERIOD_SID = TPC.PERIOD_SID
                                          WHERE  TPS.ADJUSTMENT_METHODOLOGY = @METHODOLOGY
                                                 AND TPS.ADJUSTMENT_VARIABLE = @VARIABLE
                                                 AND TPS.ADJUSTMENT_TYPE = @ADJUSTMENTTYPE) A
                              END
                           ELSE
                              BEGIN
                                 
                                  INSERT INTO #TEMP_FINAL_SALES
                                              (SALES_ID,
                                               PERIOD_SID,
                                               PROJ_SALES,
                                               PROJ_UNITS)
                                  SELECT A.CCP_DETAILS_SID,
                                         A.PERIOD_SID,
                                         ( ( ISNULL(A.PROJECTION_UNITS, 0) + ( ( A.PERIOD_UNITS_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_UNITS_PERCENT ) ) * A.ITEM_PRICE ) AS SALES,
                                         ( ISNULL(A.PROJECTION_UNITS, 0) + ( ( A.PERIOD_UNITS_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_UNITS_PERCENT ) )
                                  FROM   (SELECT TPS.CCP_DETAILS_SID,
                                                 TPS.PERIOD_SID,
                                                 TPC.PERIOD_UNITS_PERCENT,
                                                 CASE
                                                   WHEN TPS.ADJUSTMENT_BASIS = 'AMOUNT' THEN TPS.ADJUSTMENT_VALUES
                                                   ELSE ( TPS.ADJUSTMENT_VALUES / 100 ) * @ADJUSTMENT_BASIS
                                                 END ADJUSTMENT_VALUES,
                                                 IP.ITEM_PRICE,
                                                 TBP.BASELINE_UNITS_PERCENT,
                                                 TPS.PROJECTION_UNITS
                                          FROM   #TEMP_PROJECTION_SALES TPS
                                                 JOIN #TEMP_PERIOD_CONT TPC
                                                   ON TPC.PERIOD_SID = TPS.PERIOD_SID
                                                 JOIN #TEMP_BASELINE_PERCENT TBP
                                                   ON TBP.SALES_ID = TPS.CCP_DETAILS_SID
                                                 LEFT JOIN @ITEM_PRICE IP
                                                        ON IP.SALES_ID = TPS.CCP_DETAILS_SID
                                                           AND IP.SALES_ID = TBP.SALES_ID
                                                           AND IP.PERIOD_SID = TPS.PERIOD_SID
                                                           AND IP.PERIOD_SID = TPC.PERIOD_SID
                                          WHERE  TPS.ADJUSTMENT_METHODOLOGY = @METHODOLOGY
                                                 AND TPS.ADJUSTMENT_VARIABLE = @VARIABLE
                                                 AND TPS.ADJUSTMENT_TYPE = @ADJUSTMENTTYPE) A
                              END
                        END
 
                      SET @INNER_LOOP1=@INNER_LOOP1 + 1
                  END
 
                SET @INSERT_METHODOLOGY=''
                SET @INNER_LOOP1=1
                SET @COUNT=0
 
                DELETE FROM #TEMP_BASELINE_PERCENT
 
                SET @OUTER_LOOP=@OUTER_LOOP + 1
            END
       IF ( @ADJUSTMENT_TYPE = 'OVERRIDE' AND @ADJUSTMENT_BASIS_TEMP = 'AMOUNT' AND @ADJUSTMENT_VARIABLE = 'A')
BEGIN
 
  DECLARE  @S_SQL VARCHAR(MAX)
  SET @S_SQL=CONCAT('UPDATE  M SET ADJUSTED_CCP = 1' ,' FROM   ', @MASTER_TABLE ,'  M  WHERE  FILTER_CCP=1  ',case when @SALES_INCLUSION is not null then concat('ANd SALES_INCLUSION =',@SALES_INCLUSION) END,' ')
  EXEC(@S_SQL)

IF OBJECT_ID('TEMPDB..#PERIOD_SID_FIND') IS NOT NULL
DROP TABLE #PERIOD_SID_FIND
       CREATE TABLE #PERIOD_SID_FIND (
              PERIODS_SID INT
              ,PERIODS VARCHAR(100)
              )
       INSERT INTO #PERIOD_SID_FIND (
              PERIODS_SID
              ,PERIODS
              )
       SELECT PERIOD_SID
              ,TOKEN
       FROM UDF_SPLITSTRING(@SELECTED_PERIODS, ',') A
       JOIN (
              SELECT PERIOD_SID
                     ,CASE
                           WHEN @FREQUENCY = 'M'
                                  THEN 'M' + CONVERT(VARCHAR(2), P.MONTH) + ' ' + CONVERT(VARCHAR(4), P.YEAR)
                           WHEN @FREQUENCY = 'Q'
                                  THEN 'Q' + CONVERT(VARCHAR(1), P.QUARTER) + ' ' + CONVERT(VARCHAR(4), P.YEAR)
                           WHEN @FREQUENCY = 'S'
                                  THEN 'S' + CONVERT(VARCHAR(1), P.SEMI_ANNUAL) + ' ' + CONVERT(VARCHAR(4), P.YEAR)
                           ELSE CONVERT(VARCHAR(4), P.YEAR)
                           END PERIODS
              FROM PERIOD P
              ) AS P ON P.PERIODS = A.TOKEN
       DECLARE  @V_SQL VARCHAR(MAX)
     
       SET  @V_SQL=CONCAT(' UPDATE P SET P.PROJECTION_SALES=A.ACTUAL_SALES, P.PROJECTION_UNITS=A.ACTUAL_UNITS FROM ',@MASTER_TABLE ,' M JOIN ',@PROJECTION_TABLE,' P ON M.CCP_DETAILS_SID=P.CCP_DETAILS_SID  AND CHECK_RECORD=1 ',
       ' JOIN ',@ACTUAL_TABLE,' A ON A.CCP_DETAILS_SID=P.CCP_DETAILS_SID AND A.PERIOD_SID=P.PERIOD_SID JOIN #PERIOD_SID_FIND PP ON PP.PERIODS_SID=P.PERIOD_SID')
       EXEC(@V_SQL)
      
 
END
 
          DECLARE @SQL1 NVARCHAR(MAX)=''
 
          SET @SQL1 = CONCAT('UPDATE STNMP
                SET    STNMP.PROJECTION_SALES = ISNULL(TFS.PROJ_SALES, 0),
                       STNMP.PROJECTION_UNITS = ISNULL(TFS.PROJ_UNITS, 0)/NULLIF(UOM_VALUE,0)
                FROM   ', @PROJECTION_TABLE, ' STNMP
				JOIN CCP_DETAILS CCP ON CCP.CCP_DETAILS_SID=STNMP.CCP_DETAILS_SID
				LEFT JOIN ',@UOM_TABLE,' UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
				AND UOM_CODE=''',@UOM,'''
                       JOIN #TEMP_FINAL_SALES TFS
                         ON STNMP.CCP_DETAILS_SID = TFS.SALES_ID
                            AND STNMP.PERIOD_SID = TFS.PERIOD_SID')
 

 
				 
          EXEC SP_EXECUTESQL
            @SQL1

			end