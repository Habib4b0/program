IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_SALES_ADJUSTMENT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_SALES_ADJUSTMENT]
  END

GO
CREATE PROCEDURE [dbo].[PRC_SALES_ADJUSTMENT] (@BASLINE_PERIODS        VARCHAR(1000)=NULL,
                                                   @SELECTED_PERIODS       VARCHAR(1000),
                                                   @PROJECTION_SID         INT,
                                                   @FREQUENCY              VARCHAR(50),
                                                   @USER_ID                INT,
                                                   @SESSION_ID             VARCHAR(50),
                                                   @ADJUSTMENT_TYPE        VARCHAR(20),
                                                   @ADJUSTMENT_BASIS_TEMP       VARCHAR(20),
                                                   @ADJUSTMENT_VARIABLE    BIT,
                                                   @ADJUSTMENT_METHODOLOGY VARCHAR(50),
                                                   @ADJUSTMENT_VALUES      NUMERIC(22, 6))
AS
  BEGIN
      SET NOCOUNT ON

      DECLARE @METHODOLOGY_COUNT INT,
              @VARIABLE_COUNT    INT,
              @OUTER_LOOP        INT=1,
              @INNER_LOOP1       INT=1,
              @METHODOLOGY       VARCHAR(50)='',
              @VARIABLE          INT=2,
              @PROJECTION_DATE   DATETIME,
              @ADJUSTMENTTYPE    VARCHAR(20)='',
              @FROM_DATE         DATE,
              @FORECAST_TYPE     VARCHAR(20),
              @COUNT             INT=0,
              @START_SID         INT,
              @END_SID           INT,
              @ITEM_UOM          VARCHAR(20) = 'EACH'

      BEGIN TRY
          SET @FROM_DATE= (SELECT Dateadd(YY, Datediff(YY, 0, Getdate()) - 3, 0))
          SET @PROJECTION_DATE = (SELECT Dateadd(DAY, -1, Dateadd(QQ, Datediff(QQ, 0, TO_DATE) + 1, 0))
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

          DECLARE @MASTER_TABLE     VARCHAR(200) = Concat('ST_', Iif(@FORECAST_TYPE <> 'MANDATED', 'NM', 'M'), '_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ACTUAL_TABLE     VARCHAR(200) = Concat('ST_', Iif(@FORECAST_TYPE <> 'MANDATED', 'NM', 'M'), '_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @PROJECTION_TABLE VARCHAR(200) = Concat('ST_', Iif(@FORECAST_TYPE <> 'MANDATED', 'NM', 'M'), '_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @CCP_HIERARCHY    VARCHAR(200) = Concat('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

          IF ( Object_id('TEMPDB..#SALES_PROJECTION_MASTER') ) IS NOT NULL
            DROP TABLE #SALES_PROJECTION_MASTER

          CREATE TABLE #SALES_PROJECTION_MASTER
            (
               CCP_DETAILS_SID     INT,
               METHODOLOGY         VARCHAR(50),
             
               CALCULATION_PERIODS VARCHAR(50),
               CALCULATION_BASED   VARCHAR(50),
               CHECK_RECORD        BIT
            )

          IF ( Object_id('TEMPDB..#SALES_PROJECTION') ) IS NOT NULL
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
               ADJUSTMENT_VARIABLE    BIT,
               ADJUSTMENT_METHODOLOGY VARCHAR(50),
               ADJUSTMENT_VALUES      NUMERIC(22, 6)
            )

          IF ( Object_id('TEMPDB..#ACTUAL_SALES') ) IS NOT NULL
            DROP TABLE #ACTUAL_SALES

          CREATE TABLE #ACTUAL_SALES
            (
               CCP_DETAILS_SID          INT,
               PERIOD_SID               INT,
               ACTUAL_SALES             NUMERIC(22, 6),
               ACTUAL_UNITS             NUMERIC(22, 6),
               HISTORY_PROJECTION_SALES NUMERIC(22, 6),
               HISTORY_PROJECTION_UNITS NUMERIC(22, 6)
            )

          IF Object_id('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL
            DROP TABLE #CCP_DETAILS_TEMP

          CREATE TABLE #CCP_DETAILS_TEMP
            (
               CCP_DETAILS_SID     INT,
               COMPANY_MASTER_SID  INT,
               ITEM_MASTER_SID     INT,
               CONTRACT_MASTER_SID INT,
               NEW                 BIT DEFAULT (0)
            )

          DECLARE @SQL NVARCHAR(max)

          SET @SQL='INSERT INTO #CCP_DETAILS_TEMP(CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID)
								  SELECT CH.CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID FROM '
                   + @CCP_HIERARCHY
                   + ' CH JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID '

          EXEC Sp_executesql
            @SQL

          SET @SQL=''
          SET @SQL =Concat('INSERT INTO #SALES_PROJECTION_MASTER
						(CCP_DETAILS_SID,
						 METHODOLOGY,
						
						 CALCULATION_PERIODS,
						 CALCULATION_BASED,
						 CHECK_RECORD)
			SELECT CCP_DETAILS_SID,
				   METHODOLOGY,
				  
				   CALCULATION_PERIODS,
				   CALCULATION_BASED,
				   CHECK_RECORD
			FROM   ', @MASTER_TABLE, '

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
				,',@ADJUSTMENT_VARIABLE ,'   
				,''',@ADJUSTMENT_METHODOLOGY ,'''
				,',@ADJUSTMENT_VALUES ,' 
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

          EXEC Sp_executesql
            @SQL

          ------------------ ADJUSTMENT CCP DETAILS -------------------------------------------------
          IF Object_id('TEMPDB.DBO.#SALES_MASTER', 'U') IS NOT NULL
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
                          WHERE  PERIOD_DATE = Dateadd(MONTH, Datediff(MONTH, - 1, @PROJECTION_DATE) - 1, - 1)
                                               + 1)

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

          DECLARE @ITEM_PRICE TABLE
            (
               SALES_ID   INT,
               ITEM_SID   INT,
               PERIOD_SID INT,
               ITEM_PRICE NUMERIC(22, 6)
            )

          INSERT INTO @ITEM_PRICE
                      (SALES_ID,
                       ITEM_SID,
                       PERIOD_SID,
                       ITEM_PRICE)
          SELECT SM.SALES_ID,
                 SM.ITEM_SID,
                 UDF.PERIOD_SID,
                 UDF.ITEM_PRICE
          FROM   #SALES_MASTER SM
                 JOIN @ITEM_INFO ITM
                   ON ITM.ITEM_SID = SM.ITEM_SID
                 JOIN Udf_item_pricing(@ITEMID, 'WAC', @START_SID, @END_SID, @ITEM_UOM) UDF
                   ON UDF.ITEM_MASTER_SID = ITM.ITEM_SID

          -------------------WAC PRICE FOR THE ITEM ENDS HERE -----------------------------------------
          IF Object_id('TEMPDB.DBO.#TEMP_BASELINE_PERCENT', 'U') IS NOT NULL
            DROP TABLE #TEMP_BASELINE_PERCENT;

          CREATE TABLE #TEMP_BASELINE_PERCENT
            (
               SALES_ID               INT,
               ITEM_SID               INT,
               BASELINE_SALES_PERCENT NUMERIC(22, 6),
               BASELINE_UNITS_PERCENT NUMERIC(22, 6)
            )

          IF Object_id('TEMPDB.DBO.#TEMP_PERIOD_CONT', 'U') IS NOT NULL
            DROP TABLE #TEMP_PERIOD_CONT;

          CREATE TABLE #TEMP_PERIOD_CONT
            (
               PERIOD_SID           INT,
               PERIOD_SALES_PERCENT NUMERIC(22, 6),
               PERIOD_UNITS_PERCENT NUMERIC(22, 6)
            )

          IF Object_id('TEMPDB.DBO.#TEMP_FINAL_SALES', 'U') IS NOT NULL
            DROP TABLE #TEMP_FINAL_SALES;

          CREATE TABLE #TEMP_FINAL_SALES
            (
               SALES_ID   INT,
               PERIOD_SID INT,
               PROJ_SALES NUMERIC(22, 6),
               PROJ_UNITS NUMERIC(22, 6)
            )

          IF Object_id('TEMPDB.DBO.#TEMP_PROJECTION_SALES', 'U') IS NOT NULL
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
                                   Sum(STNMP.PROJECTION_SALES) AS PROJECTION_SALES,
                                   Sum(STNMP.PROJECTION_UNITS) AS PROJECTION_UNITS,
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
                                           FROM   Udf_splitstring(@SELECTED_PERIODS, ',') A
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
                                           WHEN Max(Cast(A.ADJUSTMENT_VARIABLE AS INT)) = 1 THEN Isnull(Sum(A.PROJECTION_SALES), 0)
                                           ELSE Isnull(Sum(A.PROJECTION_UNITS), 0)
                                         END ADJUSTMENT_RESULT
                                  FROM   (SELECT SM.SALES_ID,
                                                 SM.ITEM_SID,
                                                 Sum(STNMP.PROJECTION_SALES) AS PROJECTION_SALES,
                                                 Sum(STNMP.PROJECTION_UNITS) AS PROJECTION_UNITS,
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
                                                         FROM   Udf_splitstring(@SELECTED_PERIODS, ',') A
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
                                             WHEN Max(Cast(A.ADJUSTMENT_VARIABLE AS INT)) = 1 THEN Sum(A.PROJECTION_SALES)
                                             ELSE Sum(A.PROJECTION_UNITS)
                                           END ADJUSTMENT_RESULT
                                    FROM   (SELECT SM.SALES_ID,
                                                   SM.ITEM_SID,
                                                   Sum(STNMP.PROJECTION_SALES) AS PROJECTION_SALES,
                                                   Sum(STNMP.PROJECTION_UNITS) AS PROJECTION_UNITS,
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
                                                           FROM   Udf_splitstring(@SELECTED_PERIODS, ',') A
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
                 Sum(STNMP.PROJECTION_SALES) AS PROJ_SALES,
                 Sum(STNMP.PROJECTION_UNITS) AS PROJ_UNITS
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
                SELECT @COUNT = Count(1)
                FROM   PERIOD SUB
                WHERE  EXISTS(SELECT 1
                              FROM   Udf_splitstring(@SELECTED_PERIODS, ',') A
                              WHERE  A.TOKEN = CASE
                                                 WHEN @FREQUENCY = 'M' THEN 'M' + Cast( SUB.MONTH AS VARCHAR(2)) + ' '
                                                                            + Cast(SUB.YEAR AS VARCHAR(4))
                                                 WHEN @FREQUENCY = 'Q' THEN 'Q' + Cast( SUB.QUARTER AS VARCHAR(2)) + ' '
                                                                            + Cast(SUB.YEAR AS VARCHAR(4))
                                                 WHEN @FREQUENCY = 'S' THEN 'S' + Cast( SUB.SEMI_ANNUAL AS VARCHAR(2))
                                                                            + ' ' + Cast(SUB.YEAR AS VARCHAR(4))
                                                 ELSE Cast(SUB.YEAR AS VARCHAR(4))
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
                                       FROM   Udf_splitstring(@SELECTED_PERIODS, ',') A
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
                       COALESCE(PROJ_SALES / NULLIF(Sum(PROJ_SALES)
                                                      OVER(), 0), 0)  AS SALES_PERIOD_PERCENT,
                       COALESCE (PROJ_UNITS / NULLIF(Sum(PROJ_UNITS)
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
                                       FROM   Udf_splitstring(@SELECTED_PERIODS, ',') A
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
          SELECT @METHODOLOGY_COUNT = Count(DISTINCT ADJUSTMENT_METHODOLOGY)
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
                SET @METHODOLOGY_HISTORICAL_SALES=(SELECT Replace(Replace(@METHODOLOGY_HISTORICAL_SALES, 'MONTHLY_ACTUAL', 'ACTUAL_SALES'), 'BASELINE_PERIODS_ACTUAL', 'NULLIF(BASELINE_SALES,0)'))
                SET @METHODOLOGY_HISTORICAL_UNITS=(SELECT Replace(Replace(@METHODOLOGY_HISTORICAL_SALES, 'ACTUAL_SALES', 'ACTUAL_UNITS'), 'NULLIF(BASELINE_SALES,0)', 'NULLIF(BASELINE_UNITS,0)'))
                SET @METHODOLOGY_FORECAST_SALES=(SELECT Replace(Replace(@METHODOLOGY_HISTORICAL_SALES, 'ACTUAL_SALES', 'PROJECTION_SALES'), 'BASELINE_PERIODS_PROJECTION', 'NULLIF(BASELINE_SALES,0)'))
                SET @METHODOLOGY_FORECAST_UNITS=(SELECT Replace(Replace(@METHODOLOGY_FORECAST_SALES, 'PROJECTION_SALES', 'PROJECTION_UNITS'), 'NULLIF(BASELINE_SALES,0)', 'NULLIF(BASELINE_UNITS,0)'))

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
                            SELECT @COUNT = Count(SALES_ID)
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
                                                   FROM   Udf_splitstring(@BASLINE_PERIODS, ',') A
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
                EXECUTE Sp_executesql
                  @INSERT_METHODOLOGY

                --------------------------------------------------------------------------------------------------------------------------------
                --------------------------------------------------------------------------------------------------------------------------------
                SELECT @VARIABLE_COUNT = Count(*)
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

                      IF @VARIABLE = 1
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
                                         ( Isnull(A.PROJECTION_SALES, 0) + ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_SALES_PERCENT ) )                               AS SALES,
                                         ( ( Isnull(A.PROJECTION_SALES, 0) + ( ( A.PERIOD_SALES_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_SALES_PERCENT ) ) / NULLIF(A.ITEM_PRICE, 0) ) AS UNITS
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
                                         ( ( Isnull(A.PROJECTION_UNITS, 0) + ( ( A.PERIOD_UNITS_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_UNITS_PERCENT ) ) * A.ITEM_PRICE ) AS SALES,
                                         ( Isnull(A.PROJECTION_UNITS, 0) + ( ( A.PERIOD_UNITS_PERCENT * A.ADJUSTMENT_VALUES ) * A.BASELINE_UNITS_PERCENT ) )
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

          DECLARE @SQL1 NVARCHAR(max)=''

          SET @SQL1 = Concat('UPDATE STNMP
                SET    STNMP.PROJECTION_SALES = ISNULL(TFS.PROJ_SALES, 0),
                       STNMP.PROJECTION_UNITS = ISNULL(TFS.PROJ_UNITS, 0)
                FROM   ', @PROJECTION_TABLE, ' STNMP
                       JOIN #TEMP_FINAL_SALES TFS
                         ON STNMP.CCP_DETAILS_SID = TFS.SALES_ID
                            AND STNMP.PERIOD_SID = TFS.PERIOD_SID')

          EXEC Sp_executesql
            @SQL1
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
