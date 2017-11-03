IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_ARM_INFLATION_ADJUSTMENT' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].[PRC_ARM_INFLATION_ADJUSTMENT] 
  END 

GO 

CREATE PROCEDURE [dbo].[PRC_ARM_INFLATION_ADJUSTMENT](@PROJECTION_MASTER_SID   INT, 
                                                      @INVENTORY_DETAILS       VARCHAR(100), 
                                                      @BASEPRICE_PERIOD        VARCHAR(100), 
                                                      @ADJUSTMENT_PRICE_PERIOD VARCHAR(100), 
                                                      @USER_ID                 INT, 
                                                      @SESSION_ID              INT) 
AS 

/**********************************************************************************************************
** File Name		:	PRC_ARM_INFLATION_ADJUSTMENT.sql
** Procedure Name	:	PRC_ARM_INFLATION_ADJUSTMENT
** Description		:	To generate Inflation Adjustment  (Tr-6) Adjustment Summary tab
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Tr-6 as base
						@INVENTORY_DETAILS      - It indicates the Period for Pulling Inventory Units
						@CUSTOMERS_SELECTION    - It indicates the whther user selected Customer or Customer Group in Inventory Screen
						@RETURN_RESERVE         - It indicates the Period to pull the Return Reserve Values from Return Reserve Interface
						@PRICE_PERIOD			- It Indicates the Period to Pull the Price values
						@OVERRIDE               - It is used to find out user gave Price override in UI or not. if no override it will be zero,otherwise 1
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
											
** Output Parameters:	NA
** Author Name		:	@Paul,@Reddy
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application for Pipeline Inventory Transaction(as base),
                        if new Transaction created with Pipeline Inventory as base Transaction then application will call this procedure. 
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------
** 1    11/08/2016  GAL-8283        @Ajay             Temp table Changes.
** 2	08/07/2017  GAL-12264       @Kishore Kumar	  MI003 - Unqualified column name
*********************************************************************************************************/

  BEGIN 
      SET NOCOUNT ON 
       BEGIN TRY 
-- Variables Initialization starts here
         DECLARE @CURRENT_DATE_SID            INT, 
                  @BASEPRICE_PERIOD_SID        INT, 
                  @BASEPRICE_MONTH             INT, 
                  @BASEPRICE_YEAR              INT, 
                  @INVENTORY_DETAILS_SID       INT, 
                  @ADJUSTMENT_PRICE_PERIOD_SID INT, 
                  @ADJUSTMENTPRICE_MONTH       INT, 
                  @ADJUSTMENTPRICE_YEAR        INT, 
                  @FROM_PEIOD_SID              INT, 
                  @ITEM_UDT                    UDT_ITEM, 
                  @ITEM_UDT1                   UDT_ITEM, 
                  @FORECAST_NAME_EXFACTORY     VARCHAR(50), 
                  @FORECAST_VERSION_EXFACTORY  VARCHAR(50), 
                  @BUISNESS_UNIT               INT, 
                  @GL_COMP_COMPANY             INT, 
                  @ITEM_UOM                    VARCHAR(50) ='EACH', 
                  @FROM_PERIOD_DATE            DATETIME ,
				  @SQL						NVARCHAR(MAX),
				  @ADJUSTMENT_TYPE INT


 DECLARE @INFLATION_INVENTORY_TABLE          VARCHAR(200) = CONCAT('ST_ARM_INFLATION_INVENTORY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		 @INFLATION_INVENTORY_ADJ_TABLE          VARCHAR(200) = CONCAT('ST_ARM_INFLATION_INVENTORY_ADJ_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		 @ARM_CURRENT_BALANCE  VARCHAR(200) = CONCAT('ST_ARM_CURRENT_BALANCE_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		 @ARM_ADJUSTMENTS  VARCHAR(200) = CONCAT('ST_ARM_ADJUSTMENTS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
 -- Variables Initialization ends here     


  SELECT  @ADJUSTMENT_TYPE = A.TRANSACTION_TYPE
          FROM   ARM_ADJUSTMENT_MASTER A 
                 JOIN ARM_ADJUSTMENT_CONFIG B 
                   ON A.TRANSACTION_TYPE = B.ARM_ADJUSTMENT_CONFIG_SID 
          WHERE  A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
 
 --taking price related(base price and adjusted price period) selected in data selection and assigning GL and BU to variable starts here  
		  IF OBJECT_ID('TEMPDB..#PERIOD') IS NOT NULL 
            DROP TABLE #PERIOD 

          SELECT PERIOD_SID,YEAR,QUARTER,MONTH,SEMI_ANNUAL,PERIOD_DATE, 
                 CONCAT(CASE 
                          WHEN MONTH = 1 THEN 'JAN' 
                          WHEN MONTH = 2 THEN 'FEB' 
                          WHEN MONTH = 3 THEN 'MAR' 
                          WHEN MONTH = 4 THEN 'APR' 
                          WHEN MONTH = 5 THEN 'MAY' 
                          WHEN MONTH = 6 THEN 'JUN' 
                          WHEN MONTH = 7 THEN 'JUL' 
                          WHEN MONTH = 8 THEN 'AUG' 
                          WHEN MONTH = 9 THEN 'SEP' 
                          WHEN MONTH = 10 THEN 'OCT' 
                          WHEN MONTH = 11 THEN 'NOV' 
                          WHEN MONTH = 12 THEN 'DEC' 
                        END, ' ', YEAR) AS PERIOD 
          INTO   #PERIOD 
          FROM   PERIOD 

          SELECT @INVENTORY_DETAILS_SID = PERIOD_SID 
          FROM   #PERIOD 
          WHERE  PERIOD = @INVENTORY_DETAILS 

          SELECT @BASEPRICE_PERIOD_SID = PERIOD_SID, 
                 @BASEPRICE_MONTH = MONTH, 
                 @BASEPRICE_YEAR = YEAR 
          FROM   #PERIOD 
          WHERE  PERIOD = @BASEPRICE_PERIOD 

          SELECT @ADJUSTMENT_PRICE_PERIOD_SID = PERIOD_SID, 
                 @ADJUSTMENTPRICE_MONTH = MONTH, 
                 @ADJUSTMENTPRICE_YEAR = YEAR 
          FROM   #PERIOD 
          WHERE  PERIOD = @ADJUSTMENT_PRICE_PERIOD 

          SELECT @FROM_PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, -1, DATEADD(DD, 1, EOMONTH(FROM_DATE, 0))))
          FROM   PROJECTION_MASTER 
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

          SELECT @FROM_PEIOD_SID = PERIOD_SID 
          FROM   #PERIOD 
          WHERE  PERIOD_DATE = @FROM_PERIOD_DATE 

          SET @CURRENT_DATE_SID = (SELECT PERIOD_SID 
                                   FROM   PERIOD 
                                   WHERE  PERIOD_DATE = DATEADD(DD, 1, EOMONTH(GETDATE(), -1)))

          SELECT @BUISNESS_UNIT = BU_COMPANY_MASTER_SID 
          FROM   ARM_ADJUSTMENT_MASTER 
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

          SELECT @GL_COMP_COMPANY = COMPANY_MASTER_SID 
          FROM   PROJECTION_MASTER 
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

 --taking price related(base price and adjusted price period) selected in data selection and assigning GL and BU to variable Ends here  

 -- Pulling CCP+D Combination for Current projection starts here

          IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_MASTER') IS NOT NULL 
            DROP TABLE #TEMP_ARM_PROJ_MASTER 

          CREATE TABLE #TEMP_ARM_PROJ_MASTER 
            ( 
               ARM_ADJUSTMENT_DETAILS_SID INT, 
               CCP_DETAILS_SID            INT, 
               RS_MODEL_SID               INT, 
               PERIOD_SID                 INT, 
               CONTRACT_MASTER_SID        INT, 
               COMPANY_MASTER_SID         INT, 
               ITEM_MASTER_SID            INT, 
               RS_ID                      VARCHAR(50), 
               METHODOLGY                 VARCHAR(50), 
               ARM_ADJUSTMENT_CONFIG_SID  INT 
            ) 

          INSERT INTO #TEMP_ARM_PROJ_MASTER 
                      (ARM_ADJUSTMENT_DETAILS_SID, 
                       CCP_DETAILS_SID, 
                       RS_MODEL_SID, 
                       PERIOD_SID, 
                       CONTRACT_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       ITEM_MASTER_SID, 
                       RS_ID, 
                       METHODOLGY, 
                       ARM_ADJUSTMENT_CONFIG_SID) 
          SELECT A.ARM_ADJUSTMENT_DETAILS_SID, 
                 A.CCP_DETAILS_SID, 
                 A.RS_MODEL_SID, 
                 @FROM_PEIOD_SID AS PERIOD_SID, 
                 B.CONTRACT_MASTER_SID, 
                 B.COMPANY_MASTER_SID, 
                 B.ITEM_MASTER_SID, 
                 RS.RS_ID, 
                 HT.DESCRIPTION, 
                 AM.TRANSACTION_TYPE 
          FROM   ARM_ADJUSTMENT_DETAILS A 
                 JOIN CCP_DETAILS B 
                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID 
                 JOIN RS_MODEL RS 
                   ON RS.RS_MODEL_SID = A.RS_MODEL_SID 
                 JOIN ARM_ADJUSTMENT_MASTER AM 
                   ON AM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
                 JOIN ARM_ADJUSTMENT_CONFIG AC 
                   ON AC.ARM_ADJUSTMENT_CONFIG_SID = AM.TRANSACTION_TYPE 
                 JOIN HELPER_TABLE HT 
                   ON HT.HELPER_TABLE_SID = AC.METHODOLGY 
          WHERE  A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

-- Pulling CCP+D Combination for Current projection ends here

--Pulling product related information for current projection starts here

          IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_ITEM') IS NOT NULL 
            DROP TABLE #TEMP_ARM_PROJ_ITEM 

          CREATE TABLE #TEMP_ARM_PROJ_ITEM 
            ( 
               PROJECTION_MASTER_SID INT, 
               ITEM_MASTER_SID       INT, 
               ITEM_ID               VARCHAR(50) 
               PRIMARY KEY ( ITEM_ID, ITEM_MASTER_SID, PROJECTION_MASTER_SID ) 
            ) 

          INSERT INTO #TEMP_ARM_PROJ_ITEM 
                      (ITEM_ID, 
                       ITEM_MASTER_SID, 
                       PROJECTION_MASTER_SID) 
          SELECT DISTINCT IM.ITEM_ID, 
                          B.ITEM_MASTER_SID, 
                          @PROJECTION_MASTER_SID 
          FROM   #TEMP_ARM_PROJ_MASTER A 
                 JOIN CCP_DETAILS B 
                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID 
                 JOIN ITEM_MASTER IM 
                   ON IM.ITEM_MASTER_SID = B.ITEM_MASTER_SID 

--Pulling product related information for current projection ends here

--Taking Customer and item for particular projection starts here

          IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_CUST_ITEM') IS NOT NULL 
            DROP TABLE #TEMP_ARM_PROJ_CUST_ITEM 

          CREATE TABLE #TEMP_ARM_PROJ_CUST_ITEM 
            ( 
               PROJECTION_MASTER_SID INT, 
               ITEM_MASTER_SID       INT, 
               ITEM_ID               VARCHAR(50), 
               COMPANY_MASTER_SID    INT, 
               COMPANY_ID            VARCHAR(50), 
               PRIMARY KEY ( ITEM_ID, ITEM_MASTER_SID, COMPANY_ID, COMPANY_MASTER_SID, PROJECTION_MASTER_SID )
            ) 

          INSERT INTO #TEMP_ARM_PROJ_CUST_ITEM 
                      (ITEM_ID, 
                       ITEM_MASTER_SID, 
                       COMPANY_ID, 
                       COMPANY_MASTER_SID, 
                       PROJECTION_MASTER_SID) 
          SELECT DISTINCT T.ITEM_ID, 
                          T.ITEM_MASTER_SID, 
                          CM.COMPANY_ID, 
                          CM.COMPANY_MASTER_SID, 
                          @PROJECTION_MASTER_SID 
          FROM   #TEMP_ARM_PROJ_MASTER A 
                 JOIN CCP_DETAILS B 
                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID 
                 JOIN #TEMP_ARM_PROJ_ITEM T 
                   ON T.ITEM_MASTER_SID = B.ITEM_MASTER_SID 
                 JOIN COMPANY_MASTER CM 
                   ON CM.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID 

--Taking Customer and item for particular projection Ends here
--taking Helper table entries for Rebate list name starts here

          IF OBJECT_ID('TEMPDB..#TEMP_HELPER') IS NOT NULL 
            DROP TABLE #TEMP_HELPER 

          CREATE TABLE #TEMP_HELPER 
            ( 
               HELPER_TABLE_SID INT, 
               DESCRIPTION      VARCHAR(50), 
               LIST_NAME        VARCHAR(50), 
               PRIMARY KEY (HELPER_TABLE_SID, DESCRIPTION, LIST_NAME) 
            ) 

          INSERT INTO #TEMP_HELPER 
                      (HELPER_TABLE_SID, 
                       DESCRIPTION, 
                       LIST_NAME) 
          SELECT HELPER_TABLE_SID, 
                 DESCRIPTION, 
                 LIST_NAME 
          FROM   HELPER_TABLE 
          WHERE  LIST_NAME IN ( 'REBATE_STRUCTURE', 'TIER_OPERATOR', 'REBATE_BASED_ON', 'REBATE_RANGE_BASED_ON',
                                'CALCULATION_LEVEL', 'CALCULATION_TYPE' ) 

--taking Helper table entries for Rebate list name ends here

--Pulling Base and Adjusted Price starts here

          IF OBJECT_ID('TEMPDB..#TEMP_PRICE') IS NOT NULL 
            DROP TABLE #TEMP_PRICE 

          CREATE TABLE #TEMP_PRICE 
            ( 
               ITEM_MASTER_SID INT PRIMARY KEY, 
               BASE_PRICE      NUMERIC(22, 6) 
            ) 

          INSERT INTO @ITEM_UDT 
                      (ITEM_MASTER_SID) 
          SELECT ITEM_MASTER_SID 
          FROM   #TEMP_ARM_PROJ_ITEM 

          IF OBJECT_ID('TEMPDB..#ADJ_PRICE') IS NOT NULL 
            DROP TABLE #ADJ_PRICE 

          CREATE TABLE #ADJ_PRICE 
            ( 
               ITEM_MASTER_SID INT PRIMARY KEY, 
               ADJ_PRICE       NUMERIC(22, 6) 
            ) 

          INSERT INTO @ITEM_UDT1 
                      (ITEM_MASTER_SID) 
          SELECT ITEM_MASTER_SID 
          FROM   #TEMP_ARM_PROJ_ITEM 
--Taking Latest Forcast name and Forecast Version Starts here
          SELECT @FORECAST_NAME_EXFACTORY = FORECAST_NAME, 
                 @FORECAST_VERSION_EXFACTORY = VERSION 
          FROM   (SELECT FT.FORECAST_NAME, 
                         FT.[VERSION], 
                         FT.FILE_MANAGEMENT_SID, 
                         HT.[DESCRIPTION]                        AS FILE_TYPE, 
                         ROW_NUMBER() 
                           OVER ( 
                             PARTITION BY FT.FILE_TYPE 
                             ORDER BY FT.FILE_MANAGEMENT_SID DESC ) AS RN 
                  FROM   FILE_MANAGEMENT FT 
                         INNER JOIN HELPER_TABLE HT 
                                 ON HT.HELPER_TABLE_SID = FT.FILE_TYPE 
                  WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE()) 
                           AND FT.FROM_PERIOD IS NOT NULL ) 
                         AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE()) 
                                OR FT.TO_PERIOD IS NULL ) 
                         AND HT.LIST_NAME = 'FILE_TYPE' 
                         AND HT.[DESCRIPTION] IN ( 'EX-FACTORY SALES' ) 
                         AND FT.BUSINESS_UNIT = @BUISNESS_UNIT 
                         AND FT.COMPANY = @GL_COMP_COMPANY) A 
          WHERE  RN = 1 
--Taking Latest Forcast name and Forecast Version Starts here

--If Base Price Period is less than Current System Date then we shoud pull Price from Item Pricing Starts here

          IF ( @BASEPRICE_PERIOD_SID < @CURRENT_DATE_SID ) 
            BEGIN 
                INSERT INTO #TEMP_PRICE 
                            (ITEM_MASTER_SID, 
                             BASE_PRICE) 
                SELECT ITEM_MASTER_SID, 
                       ITEM_PRICE 
                FROM   [DBO].[UDF_ITEM_PRICING](@ITEM_UDT, 'WAC', @BASEPRICE_PERIOD_SID, @BASEPRICE_PERIOD_SID, 'EACH') 
                WHERE  PERIOD_SID = @BASEPRICE_PERIOD_SID 
--If Base Price Period is less than Current System Date then we shoud pull Price from Item Pricing Starts here
            END 
          ELSE 

--If Base Price Period is Greater than Current System Date then we shoud pull Price from Foreacsting master Starts here
            BEGIN 
                INSERT INTO #TEMP_PRICE 
                            (ITEM_MASTER_SID, 
                             BASE_PRICE) 
                SELECT B.ITEM_MASTER_SID, 
                       A.PRICE 
                FROM   FORECASTING_MASTER A 
                       JOIN #TEMP_ARM_PROJ_ITEM B 
                         ON A.NDC = B.ITEM_ID 
                WHERE  A.FORECAST_MONTH = @BASEPRICE_MONTH 
                       AND A.FORECAST_YEAR = @BASEPRICE_YEAR 
                       AND A.FORECAST_NAME = @FORECAST_NAME_EXFACTORY 
                       AND A.FORECAST_VER = COALESCE(@FORECAST_VERSION_EXFACTORY, FLOOR(@FORECAST_VERSION_EXFACTORY))
--If Base Price Period is Greater than Current System Date then we shoud pull Price from Foreacsting master ends here
            END 

          IF ( @ADJUSTMENT_PRICE_PERIOD_SID < @CURRENT_DATE_SID ) 
            BEGIN 
--If Adjusted Price Period is less than Current System Date then we shoud pull Price from Item Pricing Starts here
                INSERT INTO #ADJ_PRICE 
                            (ITEM_MASTER_SID, 
                             ADJ_PRICE) 
                SELECT ITEM_MASTER_SID, 
                       ITEM_PRICE 
                FROM   [DBO].[UDF_ITEM_PRICING](@ITEM_UDT1, 'WAC', @ADJUSTMENT_PRICE_PERIOD_SID, @ADJUSTMENT_PRICE_PERIOD_SID, 'EACH') 
                WHERE  PERIOD_SID = @ADJUSTMENT_PRICE_PERIOD_SID 
--If Adjusted Price Period is less than Current System Date then we shoud pull Price from Item Pricing Ends here
            END 
          ELSE 
--If Adjusted Price Period is Greater than Current System Date then we shoud pull Price from Foreacsting master Starts here
            BEGIN 
                INSERT INTO #ADJ_PRICE 
                            (ITEM_MASTER_SID, 
                             ADJ_PRICE) 
                SELECT B.ITEM_MASTER_SID, 
                       A.PRICE 
                FROM   FORECASTING_MASTER A 
                       JOIN #TEMP_ARM_PROJ_ITEM B 
                         ON A.NDC = B.ITEM_ID 
                WHERE  A.FORECAST_MONTH = @ADJUSTMENTPRICE_MONTH 
                       AND A.FORECAST_YEAR = @ADJUSTMENTPRICE_YEAR 
                       AND A.FORECAST_NAME = @FORECAST_NAME_EXFACTORY 
                       AND A.FORECAST_VER = COALESCE(@FORECAST_VERSION_EXFACTORY, FLOOR(@FORECAST_VERSION_EXFACTORY))
--If Adjusted Price Period is Greater than Current System Date then we shoud pull Price from Foreacsting master Starts here
            END 
--If Base Price Period is less than Current System Date then we shoud pull Price from Item Pricing Ends here

--taking approved CCP+D Combination for that particular adjustment type(if new transactions created by adjustment Configuration also) starts here

          IF OBJECT_ID('TEMPDB..#TEMP_PROJ_MASTER_STATUS') IS NOT NULL 
            DROP TABLE #TEMP_PROJ_MASTER_STATUS 

          CREATE TABLE #TEMP_PROJ_MASTER_STATUS 
            ( 
               PROJECTION_MASTER_SID     INT, 
               CCP_DETAILS_SID           INT, 
               RS_MODEL_SID              INT, 
               PERIOD_SID                INT, 
               CURRENT_DETAILS_SID       INT, 
               OLD_DETAILS_SID           INT, 
               ARM_ADJUSTMENT_CONFIG_SID INT 
            ) 

          INSERT INTO #TEMP_PROJ_MASTER_STATUS 
                      (PROJECTION_MASTER_SID, 
                       CCP_DETAILS_SID, 
                       RS_MODEL_SID, 
                       PERIOD_SID, 
                       CURRENT_DETAILS_SID, 
                       OLD_DETAILS_SID, 
                       ARM_ADJUSTMENT_CONFIG_SID) 
          SELECT ARD.PROJECTION_MASTER_SID, 
                 ARD.CCP_DETAILS_SID, 
                 ARD.RS_MODEL_SID, 
                 TA.PERIOD_SID, 
                 TA.ARM_ADJUSTMENT_DETAILS_SID  AS CURRENT_DETAILS_SID, 
                 ARD.ARM_ADJUSTMENT_DETAILS_SID AS OLD_DETAILS_SID, 
                 TA.ARM_ADJUSTMENT_CONFIG_SID 
          FROM   PROJECTION_MASTER PM 
                 JOIN WORKFLOW_MASTER WM 
                   ON PM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID 
                 JOIN ARM_ADJUSTMENT_DETAILS ARD 
                   ON ARD.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID 
                 JOIN ARM_ADJUSTMENT_MASTER ADM 
                   ON ADM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID 
                 JOIN #TEMP_ARM_PROJ_MASTER TA 
                   ON TA.CCP_DETAILS_SID = ARD.CCP_DETAILS_SID 
                      AND TA.RS_MODEL_SID = ARD.RS_MODEL_SID 
                      AND TA.ARM_ADJUSTMENT_CONFIG_SID = ADM.TRANSACTION_TYPE 
					  AND EXISTS (SELECT H1.HELPER_TABLE_SID
                                    FROM   HELPER_TABLE H1
                                    WHERE  H1.LIST_NAME = 'WORKFLOWSTATUS'
                                            AND H1.DESCRIPTION = 'APPROVED'
											AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID)
                      --AND WM.WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID 
                      --                              FROM   HELPER_TABLE 
                      --                              WHERE  LIST_NAME = 'WORKFLOWSTATUS' 
                      --                                     AND DESCRIPTION = 'APPROVED') 
--taking approved CCP+D Combination for that particular adjustment type(if new transactions created by adjustment Configuration also) ends here

--Taking Adjustment value for previousliy approved CCP+D Combination starts here

          IF OBJECT_ID('TEMPDB..#ARM_ADJ_SUMMARY') IS NOT NULL 
            DROP TABLE #ARM_ADJ_SUMMARY 

          CREATE TABLE #ARM_ADJ_SUMMARY 
            ( 
               CCP_DETAILS_SID INT, 
               RS_MODEL_SID    INT, 
               PERIOD_SID      INT, 
               CURRENT_BALANCE NUMERIC(22, 6), 
               INDICATOR       BIT 
            ) 

          INSERT INTO #ARM_ADJ_SUMMARY 
                      (CCP_DETAILS_SID, 
                       RS_MODEL_SID, 
                       PERIOD_SID, 
                       CURRENT_BALANCE, 
                       INDICATOR) 
          SELECT A.CCP_DETAILS_SID, 
                 A.RS_MODEL_SID, 
                 A.PERIOD_SID, 
                 ISNULL(SUM(ISNULL(C.VARIANCE, C.OVERRIDE)), 0) AS CURRENT_BALANCE, 
                 CASE 
                   WHEN SUM(ISNULL(C.VARIANCE, C.OVERRIDE)) > 0 THEN 0 
                   WHEN SUM(ISNULL(C.VARIANCE, C.OVERRIDE)) < 0 THEN 1 
                   ELSE NULL 
                 END                                            AS INDICATOR 
          FROM   #TEMP_PROJ_MASTER_STATUS A 
                 LEFT JOIN ARM_INFLATION_INVENTORY_ADJ C 
                        ON A.OLD_DETAILS_SID = C.ARM_ADJUSTMENT_DETAILS_SID 
                           AND A.PERIOD_SID = C.PERIOD_SID 
          GROUP  BY A.CCP_DETAILS_SID, 
                    A.RS_MODEL_SID, 
                    A.PERIOD_SID 
--Taking Adjustment value for previousliy approved CCP+D Combination ends here 

 --taking debits and credits for  liability accounts(Debit and Credit Logic) for that CCP+D Combination starts here

          --IF OBJECT_ID('TEMPDB..#CURRENT_BALANCE') IS NOT NULL 
          --  DROP TABLE #CURRENT_BALANCE 
          --CREATE TABLE #CURRENT_BALANCE 
          --  ( 
          --     CCP_DETAILS_SID INT, 
          --     RS_MODEL_SID    INT, 
          --     PERIOD_SID      INT, 
          --     DEBIT_AMOUNT    NUMERIC(22, 6), 
          --     CREDIT_AMOUNT   NUMERIC(22, 6), 
          --     CURRENT_AMOUNT  NUMERIC(22, 6) 
          --  ) 

          --INSERT INTO #CURRENT_BALANCE 
          --            (CCP_DETAILS_SID, 
          --             RS_MODEL_SID, 
          --             PERIOD_SID, 
          --             CREDIT_AMOUNT, 
          --             DEBIT_AMOUNT, 
          --             CURRENT_AMOUNT) 
          --SELECT TAM.CCP_DETAILS_SID, 
          --       TAM.RS_MODEL_SID, 
          --       ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)                                              AS PERIOD_SID,
          --       SUM(ABS(A1.CURRENT_BALANCE))                                                      AS CREDIT_AMOUNT,
          --       SUM(ABS(A2.CURRENT_BALANCE))                                                      AS DEBIT_AMOUNT,
          --       ISNULL(SUM(ABS(A1.CURRENT_BALANCE)), 0) - ISNULL(SUM(ABS(A2.CURRENT_BALANCE)), 0) AS CURRENT_AMOUNT
          --FROM   #TEMP_ARM_PROJ_MASTER TAM 
          --       JOIN ARM_ADJ_RES_CCP AAC 
          --         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
          --       INNER JOIN HELPER_TABLE HT 
          --               ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID 
          --                  AND HT.DESCRIPTION = 'LIABILITY' 
          --       LEFT JOIN #ARM_ADJ_SUMMARY A1 
          --              ON TAM.CCP_DETAILS_SID = A1.CCP_DETAILS_SID 
          --                 AND TAM.RS_MODEL_SID = A1.RS_MODEL_SID 
          --                 AND TAM.PERIOD_SID = A1.PERIOD_SID 
          --                 AND AAC.CREDIT = A1.INDICATOR 
          --       LEFT JOIN #ARM_ADJ_SUMMARY A2 
          --              ON TAM.CCP_DETAILS_SID = A2.CCP_DETAILS_SID 
          --                 AND TAM.RS_MODEL_SID = A2.RS_MODEL_SID 
          --                 AND TAM.PERIOD_SID = A2.PERIOD_SID 
          --                 AND AAC.DEBIT = A2.INDICATOR 
          --GROUP  BY TAM.CCP_DETAILS_SID, 
          --          TAM.RS_MODEL_SID, 
          --          ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) 
 --taking debits and credits for  liability accounts(Debit and Credit Logic) for that CCP+D Combination Ends here

 --Pulling values for Inventory Units for Customer and Item Combination starts here
          IF OBJECT_ID('TEMPDB..#TEMP_INVENTORY') IS NOT NULL 
            DROP TABLE #TEMP_INVENTORY 

          CREATE TABLE #TEMP_INVENTORY 
            ( 
               ITEM_MASTER_SID       INT, 
               ITEM_ID               VARCHAR(50), 
               COMPANY_MASTER_SID    INT, 
               COMPANY_ID            VARCHAR(50), 
               PROJECTION_MASTER_SID INT, 
               UNITS_ON_HAND         NUMERIC(22, 6) 
            ) 

          INSERT INTO #TEMP_INVENTORY 
                      (ITEM_MASTER_SID, 
                       ITEM_ID, 
                       COMPANY_MASTER_SID, 
                       COMPANY_ID, 
                       PROJECTION_MASTER_SID, 
                       UNITS_ON_HAND) 
          SELECT ITEM_MASTER_SID, 
                 ITEM_ID, 
                 COMPANY_MASTER_SID, 
                 COMPANY_ID, 
                 PROJECTION_MASTER_SID, 
                 SUM(UNITS_ON_HAND) AS UNITS_ON_HAND 
          FROM  (SELECT TAPI.ITEM_MASTER_SID, 
                        TAPI.ITEM_ID, 
                        TAPI.COMPANY_MASTER_SID, 
                        TAPI.COMPANY_ID, 
                        TAPI.PROJECTION_MASTER_SID, 
                        IWAD.UNITS_ON_HAND, 
                        P.PERIOD_SID 
                 FROM   #TEMP_ARM_PROJ_CUST_ITEM TAPI 
                        LEFT JOIN INVENTORY_WD_ACTUAL_DT IWAD 
                               ON TAPI.ITEM_MASTER_SID = IWAD.ITEM_MASTER_SID 
                                  AND TAPI.COMPANY_MASTER_SID = IWAD.COMPANY_MASTER_SID 
                        JOIN #PERIOD P 
                          ON P.PERIOD_DATE = DATEFROMPARTS(IWAD.YEAR, IWAD.MONTH, 01) 
                             AND P.PERIOD_SID = @INVENTORY_DETAILS_SID)A 
          GROUP  BY COMPANY_MASTER_SID, 
                    ITEM_ID, 
                    COMPANY_ID, 
                    PROJECTION_MASTER_SID, 
                    ITEM_MASTER_SID, 
                    PERIOD_SID 
 --Pulling values for Inventory Units for Customer and Item Combination Ends here

 --Inserting Base and Adjusted Price Calculated values for each Customer and Item and Period Combination starts here

          IF OBJECT_ID('TEMPDB..#TEMP_INFLATION_INVENTORY ') IS NOT NULL 
            DROP TABLE #TEMP_INFLATION_INVENTORY 

          CREATE TABLE #TEMP_INFLATION_INVENTORY 
            ( 
               PROJECTION_MASTER_SID      INT NOT NULL, 
               ITEM_MASTER_SID            INT NOT NULL, 
               COMPANY_MASTER_SID         INT NOT NULL, 
               PERIOD_SID                 INT NOT NULL, 
               TOTAL_INVENTORY            NUMERIC(22, 6) NULL, 
               BASELINE_PRICE             NUMERIC(22, 6) NULL, 
               BASELINE_PRICE_OVERRIDE    NUMERIC(22, 6) NULL, 
               ADJUSTED_PRICE             NUMERIC(22, 6) NULL, 
               ADJUSTED_PRICE_OVERRIDE    NUMERIC(22, 6) NULL, 
               PRICE_CHANGE               NUMERIC(22, 6) NULL, 
               PRICE_CHANGE_PERCENT       NUMERIC(22, 6) NULL, 
               BASELINE_CALCULATED_AMOUNT NUMERIC(22, 6) NULL, 
               ADJUSTED_CALCULATED_AMOUNT NUMERIC(22, 6) NULL, 
               NET_CALCULATED_AMOUNT      NUMERIC(22, 6) NULL, 
               USER_ID                    INT NOT NULL, 
               SESSION_ID                 INT NOT NULL, 
               PRIMARY KEY (PROJECTION_MASTER_SID, ITEM_MASTER_SID, COMPANY_MASTER_SID, PERIOD_SID, USER_ID, SESSION_ID)
            ) 

          INSERT INTO #TEMP_INFLATION_INVENTORY 
                      (PROJECTION_MASTER_SID, 
                       ITEM_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       PERIOD_SID, 
                       TOTAL_INVENTORY, 
                       BASELINE_PRICE, 
                       ADJUSTED_PRICE, 
                       PRICE_CHANGE, 
                       PRICE_CHANGE_PERCENT, 
                       BASELINE_CALCULATED_AMOUNT, 
                       ADJUSTED_CALCULATED_AMOUNT, 
                       NET_CALCULATED_AMOUNT, 
                       USER_ID, 
                       SESSION_ID) 
          SELECT TAPD.PROJECTION_MASTER_SID, 
                 TAPD.ITEM_MASTER_SID, 
                 TAPD.COMPANY_MASTER_SID                                                                                                         AS COMPANY_MASTER_SID,
                 @FROM_PEIOD_SID                                                                                                                 AS PERIOD_SID,
                 TI.UNITS_ON_HAND, 
                 TP.BASE_PRICE, 
                 TP1.ADJ_PRICE, 
                 ( ISNULL(TP1.ADJ_PRICE, 0) - ISNULL(TP.BASE_PRICE, 0) )                                                                                AS PRICE_CHANGE,
                 COALESCE(( ISNULL(TP1.ADJ_PRICE, 0) - ISNULL(TP.BASE_PRICE, 0) ) / NULLIF(TP.BASE_PRICE, 0), 0) * 100                                     AS PRICE_CHANGE_PERCENT,
                 ( ISNULL(TI.UNITS_ON_HAND, 0) ) * ISNULL(( TP.BASE_PRICE ), 0)                                                                  AS BASELINE_CALCULATED_AMOUNT,
                 ISNULL(( TI.UNITS_ON_HAND ), 0) * ISNULL(( TP1.ADJ_PRICE ), 0)                                                                  AS ADJUSTED_CALCULATED_AMOUNT,
                 ISNULL(( TI.UNITS_ON_HAND ), 0) * ISNULL(( TP1.ADJ_PRICE ), 0) - ( ISNULL(TI.UNITS_ON_HAND, 0) ) * ISNULL(( TP.BASE_PRICE ), 0) AS NET_CALCULATED_AMOUNT,
                 @USER_ID, 
                 @SESSION_ID 
          FROM   #TEMP_ARM_PROJ_CUST_ITEM TAPD 
                 JOIN ARM_ADJUSTMENT_MASTER A 
                   ON TAPD.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID 
                 JOIN #TEMP_PRICE TP 
                   ON TAPD.ITEM_MASTER_SID = TP.ITEM_MASTER_SID 
                 JOIN #ADJ_PRICE TP1 
                   ON TAPD.ITEM_MASTER_SID = TP1.ITEM_MASTER_SID 
                 LEFT JOIN #TEMP_INVENTORY TI 
                        ON TI.PROJECTION_MASTER_SID = TAPD.PROJECTION_MASTER_SID 
                           AND TAPD.ITEM_MASTER_SID = TI.ITEM_MASTER_SID 
                           AND TAPD.COMPANY_MASTER_SID = TI.COMPANY_MASTER_SID 

 --Inserting Base and Adjusted Price Calculated values for each Customer and Item and Period Combination ends here

 --Effective dating and elimination of CCP'S Based on effective dating Concept Starts here

          IF OBJECT_ID('TEMPDB..#TEMP_EFFECTIVE') IS NOT NULL 
            DROP TABLE #TEMP_EFFECTIVE 

          CREATE TABLE #TEMP_EFFECTIVE 
            ( 
               PROJECTION_DETAILS_SID INT, 
               CCP_DETAILS_SID        INT, 
               RS_MODEL_SID           INT, 
               RS_CATEGORY            VARCHAR(50), 
               START_DATE             DATETIME, 
               END_DATE               DATETIME, 
               PRIMARY KEY (PROJECTION_DETAILS_SID) 
            ) 

          INSERT INTO #TEMP_EFFECTIVE 
                      (PROJECTION_DETAILS_SID, 
                       CCP_DETAILS_SID, 
                       RS_MODEL_SID, 
                       RS_CATEGORY, 
                       START_DATE, 
                       END_DATE) 
          SELECT PROJECTION_DETAILS_SID, 
                 CCP_DETAILS_SID, 
                 RS_MODEL_SID, 
                 RS_CATEGORY, 
                 START_DATE, 
                 END_DATE 
          FROM   UDF_DATE_FINDER (@PROJECTION_MASTER_SID, 'D') 
          WHERE  START_DATE = @FROM_PERIOD_DATE 

--Effective dating and elimination of CCP'S Based on effective dating Concept Ends here

--Contract Details Methodolgy Calculation for Calculating Rate Starts here

--Pulling Rebate and Bundle Information for Particular CCP+D Combination selected in Data Selection Starts here

          IF OBJECT_ID('TEMPDB..#TEMP_ARM_RS_INFLATION') IS NOT NULL 
            DROP TABLE #TEMP_ARM_RS_INFLATION 

          CREATE TABLE #TEMP_ARM_RS_INFLATION 
            ( 
               PROJECTION_MASTER_SID  INT, 
               CONTRACT_MASTER_SID    INT, 
               COMPANY_MASTER_SID     INT, 
               ITEM_MASTER_SID        INT, 
               RS_MODEL_SID           INT, 
               REBATE_PLAN_MASTER_SID INT, 
               REBATE_RANGE_BASED_ON  VARCHAR(50), 
               REBATE_STRUCTURE       VARCHAR(50), 
               CALCULATION_LEVEL      VARCHAR(50), 
               BUNDLE_NO              VARCHAR(25) 
               PRIMARY KEY ( CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID, RS_MODEL_SID )
            ) 

          INSERT INTO #TEMP_ARM_RS_INFLATION 
                      (PROJECTION_MASTER_SID, 
                       CONTRACT_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       ITEM_MASTER_SID, 
                       RS_MODEL_SID, 
                       REBATE_PLAN_MASTER_SID, 
                       REBATE_RANGE_BASED_ON, 
                       REBATE_STRUCTURE, 
                       CALCULATION_LEVEL, 
                       BUNDLE_NO) 
          SELECT ADS.PROJECTION_MASTER_SID, 
                 A.CONTRACT_MASTER_SID, 
                 A.COMPANY_MASTER_SID, 
                 A.ITEM_MASTER_SID, 
                 B.RS_MODEL_SID, 
                 RCD.REBATE_PLAN_MASTER_SID, 
                 RB_ON.DESCRIPTION AS REBATE_RANGE_BASED_ON, 
                 RBS.DESCRIPTION   AS REBATE_STRUCTURE, 
                 RC.DESCRIPTION    AS CALCULATION_LEVEL, 
                 RCD.BUNDLE_NO 
          FROM   #TEMP_ARM_PROJ_MASTER A 
                 JOIN #TEMP_EFFECTIVE A1 
                   ON A.ARM_ADJUSTMENT_DETAILS_SID = A1.PROJECTION_DETAILS_SID 
                      AND A1.START_DATE = @FROM_PERIOD_DATE 
                 JOIN RS_CONTRACT B 
                   ON A.RS_MODEL_SID = B.RS_MODEL_SID 
                      AND A.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID 
                 JOIN ARM_DEDUCTION_SELECTION ADS 
                   ON ADS.RS_CONTRACT_SID = B.RS_CONTRACT_SID 
                      AND ADS.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
                 JOIN RS_CONTRACT_DETAILS RCD 
                   ON RCD.RS_CONTRACT_SID = B.RS_CONTRACT_SID 
                      AND RCD.ITEM_MASTER_SID = A.ITEM_MASTER_SID 
                 JOIN REBATE_PLAN_MASTER RPM 
                   ON RPM.REBATE_PLAN_MASTER_SID = RCD.REBATE_PLAN_MASTER_SID 
                      AND RPM.INBOUND_STATUS <> 'D' 
                 JOIN #TEMP_HELPER RB_ON 
                   ON RB_ON.HELPER_TABLE_SID = RPM.REBATE_RANGE_BASED_ON 
                 JOIN #TEMP_HELPER RBS 
                   ON RBS.HELPER_TABLE_SID = RPM.REBATE_STRUCTURE 
                 JOIN #TEMP_HELPER RC 
                   ON RC.HELPER_TABLE_SID = B.CALCULATION_LEVEL 
                 JOIN #TEMP_HELPER CT 
                   ON CT.HELPER_TABLE_SID = B.CALCULATION_TYPE 

--Pulling Rebate and Bundle Information for Particular CCP+D Combination selected in Data Selection Ends here

-- Tier level logic starts here

          IF OBJECT_ID('TEMPDB..#TIER_INFO') IS NOT NULL 
            DROP TABLE #TIER_INFO 

          CREATE TABLE #TIER_INFO 
            ( 
               PROJECTION_MASTER_SID      INT, 
               CONTRACT_MASTER_SID        INT, 
               COMPANY_MASTER_SID         INT, 
               ITEM_MASTER_SID            INT, 
               RS_MODEL_SID               INT, 
               TIER_FROM                  NUMERIC(22, 6), 
               TIER_TO                    NUMERIC(22, 6), 
               TIER_VALUE                 NUMERIC(22, 6), 
               RETURN_RATE_SID            INT, 
               ITEM_PRICING_QUALIFIER_SID INT, 
               TIER_OPERATOR              CHAR(1) 
            ) 

          INSERT INTO #TIER_INFO 
                      (PROJECTION_MASTER_SID, 
                       CONTRACT_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       ITEM_MASTER_SID, 
                       RS_MODEL_SID, 
                       TIER_FROM, 
                       TIER_TO, 
                       TIER_VALUE, 
                       RETURN_RATE_SID, 
                       ITEM_PRICING_QUALIFIER_SID, 
                       TIER_OPERATOR) 
          SELECT RS.PROJECTION_MASTER_SID, 
                 RS.CONTRACT_MASTER_SID, 
                 RS.COMPANY_MASTER_SID, 
                 RS.ITEM_MASTER_SID, 
                 RS.RS_MODEL_SID, 
                 RPT.TIER_FROM, 
                 RPT.TIER_TO, 
                 RPT.TIER_VALUE, 
                 RPT.RETURN_RATE_SID, 
                 RPT.ITEM_PRICING_QUALIFIER_SID, 
                 T_OP.DESCRIPTION AS TIER_OPERATOR 
          FROM   #TEMP_ARM_RS_INFLATION RS 
                 JOIN REBATE_PLAN_MASTER RPM 
                   ON RS.REBATE_PLAN_MASTER_SID = RPM.REBATE_PLAN_MASTER_SID 
                 JOIN REBATE_PLAN_TIER RPT 
                   ON RPM.REBATE_PLAN_MASTER_SID = RPT.REBATE_PLAN_MASTER_SID 
                 JOIN #TEMP_HELPER T_OP 
                   ON T_OP.HELPER_TABLE_SID = RPT.TIER_OPERATOR 
-- Tier level logic starts here

--ARM Logic to pull amounts Starts here

          IF OBJECT_ID('TEMPDB..#TEMP_ARM_LOGIC') IS NOT NULL 
            DROP TABLE #TEMP_ARM_LOGIC 

          CREATE TABLE #TEMP_ARM_LOGIC 
            ( 
               PROJECTION_MASTER_SID      INT, 
               CONTRACT_MASTER_SID        INT, 
               COMPANY_MASTER_SID         INT, 
               ITEM_MASTER_SID            INT, 
               RS_MODEL_SID               INT, 
               REBATE_PLAN_MASTER_SID     INT, 
               REBATE_RANGE_BASED_ON      VARCHAR(50), 
               REBATE_STRUCTURE           VARCHAR(50), 
               SALES_PROJECTED_VALUE      NUMERIC(22, 6), 
               CALCULATION_LEVEL          VARCHAR(50), 
               BUNDLE_NO                  VARCHAR(25), 
               ADJUSTED_CALCULATED_AMOUNT NUMERIC(22, 6), 
               TOTAL_INVENTORY            NUMERIC(22, 6) 
            ) 

          INSERT INTO #TEMP_ARM_LOGIC 
                      (PROJECTION_MASTER_SID, 
                       CONTRACT_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       ITEM_MASTER_SID, 
                       RS_MODEL_SID, 
                       REBATE_PLAN_MASTER_SID, 
                       REBATE_RANGE_BASED_ON, 
                       REBATE_STRUCTURE, 
                       SALES_PROJECTED_VALUE, 
                       CALCULATION_LEVEL, 
                       BUNDLE_NO, 
                       ADJUSTED_CALCULATED_AMOUNT, 
                       TOTAL_INVENTORY) 
          SELECT B.PROJECTION_MASTER_SID, 
                 B.CONTRACT_MASTER_SID, 
                 B.COMPANY_MASTER_SID, 
                 B.ITEM_MASTER_SID, 
                 B.RS_MODEL_SID, 
                 B.REBATE_PLAN_MASTER_SID, 
                 B.REBATE_RANGE_BASED_ON, 
                 B.REBATE_STRUCTURE, 
                 CASE 
                   WHEN B.REBATE_RANGE_BASED_ON IN ( 'CONTRACT SALES', 'DOLLARS' ) THEN TI.ADJUSTED_CALCULATED_AMOUNT
                   WHEN B.REBATE_RANGE_BASED_ON IN ( 'CONTRACT UNITS', 'UNITS' ) THEN TI.TOTAL_INVENTORY
                 END AS SALES_PROJECTED_VALUE, 
                 B.CALCULATION_LEVEL, 
                 B.BUNDLE_NO, 
                 TI.ADJUSTED_CALCULATED_AMOUNT, 
                 TI.TOTAL_INVENTORY 
          FROM   #TEMP_ARM_RS_INFLATION B 
                 INNER JOIN #TEMP_INFLATION_INVENTORY TI 
                         ON B.PROJECTION_MASTER_SID = TI.PROJECTION_MASTER_SID 
                            AND B.COMPANY_MASTER_SID = TI.COMPANY_MASTER_SID 
                            AND B.ITEM_MASTER_SID = TI.ITEM_MASTER_SID 

--ARM Logic to pull amounts Ends here

--Pulling Return rate and Tier Opertaor Logic Starts here
          IF OBJECT_ID('TEMPDB..#RETURNS_PERCENT') IS NOT NULL 
            DROP TABLE #RETURNS_PERCENT 

          CREATE TABLE #RETURNS_PERCENT 
            ( 
               PROJECTION_MASTER_SID INT, 
               CONTRACT_MASTER_SID   INT, 
               COMPANY_MASTER_SID    INT, 
               RS_MODEL_SID          INT, 
               ITEM_MASTER_SID       INT, 
               PERIOD_SID            INT, 
               RETURN_RATE           NUMERIC(22, 6) 
            ) 

--TIER OPERATOR =% IMPLEMENTATION STARTS HERE --GALUAT-360      
          IF EXISTS (SELECT 1 
                     FROM   #TIER_INFO 
                     WHERE  RETURN_RATE_SID IS NOT NULL) 
            BEGIN 
                INSERT INTO #RETURNS_PERCENT 
                            (PROJECTION_MASTER_SID, 
                             CONTRACT_MASTER_SID, 
                             COMPANY_MASTER_SID, 
                             RS_MODEL_SID, 
                             ITEM_MASTER_SID, 
                             PERIOD_SID, 
                             RETURN_RATE) 
                SELECT A.PROJECTION_MASTER_SID, 
                       A.CONTRACT_MASTER_SID, 
                       A.COMPANY_MASTER_SID, 
                       A.RS_MODEL_SID, 
                       A.ITEM_MASTER_SID, 
                       A.PERIOD_SID, 
                       B.RETURN_RATE 
                FROM   (SELECT PROJECTION_MASTER_SID, 
                               CONTRACT_MASTER_SID, 
                               COMPANY_MASTER_SID, 
                               RS_MODEL_SID, 
                               ITEM_MASTER_SID, 
                               @FROM_PEIOD_SID AS PERIOD_SID 
                        FROM   #TEMP_ARM_LOGIC)A 
                       LEFT JOIN (SELECT D.ITEM_MASTER_SID, 
                                         RPD.PERIOD_SID, 
                                         RPD.PROJECTED_RETURN_PERCENT AS RETURN_RATE 
                                  FROM   RETURNS_PROJ_DETAILS RPD 
                                         INNER JOIN(SELECT RD.RETURNS_DETAILS_SID, 
                                                           RD.ITEM_MASTER_SID, 
                                                           RD.PROJECTION_MASTER_SID 
                                                    FROM   RETURNS_DETAILS RD 
                                                           INNER JOIN(SELECT PROJECTION_MASTER_SID,
                                                                             ITEM_MASTER_SID 
                                                                      FROM   (SELECT PROJECTION_MASTER_SID,
                                                                                     ITEM_MASTER_SID,
                                                                                     ROW_NUMBER()
                                                                                       OVER( 
                                                                                         PARTITION BY ITEM_MASTER_SID
                                                                                         ORDER BY COALESCE(MODIFIED_DATE, CREATED_DATE) DESC ) AS RN
                                                                              FROM   (SELECT DISTINCT PM.PROJECTION_MASTER_SID,
                                                                                                      D.ITEM_MASTER_SID,
                                                                                                      PM.MODIFIED_DATE,
                                                                                                      PM.CREATED_DATE
                                                                                      FROM   PROJECTION_MASTER PM
                                                                                             INNER JOIN RETURNS_DETAILS PD
                                                                                                     ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                                                                             INNER JOIN @ITEM_UDT D
                                                                                                     ON PD.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                                                                                      WHERE  PM.FORECASTING_TYPE = 'RETURNS'
                                                                                             AND PM.SAVE_FLAG = 1)B)A
                                                                      WHERE  RN = 1) J 
                                                                   ON J.PROJECTION_MASTER_SID = RD.PROJECTION_MASTER_SID
                                                                      AND J.ITEM_MASTER_SID = RD.ITEM_MASTER_SID)D
                                                 ON RPD.RETURNS_DETAILS_SID = D.RETURNS_DETAILS_SID
                                  WHERE  RPD.PERIOD_SID = @FROM_PEIOD_SID)B 
                              ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID 
                                 AND A.PERIOD_SID = B.PERIOD_SID 
            END 

          IF NOT EXISTS (SELECT 1 
                         FROM   #RETURNS_PERCENT) 
            BEGIN 
                INSERT INTO #RETURNS_PERCENT 
                            (PROJECTION_MASTER_SID, 
                             CONTRACT_MASTER_SID, 
                             COMPANY_MASTER_SID, 
                             RS_MODEL_SID, 
                             ITEM_MASTER_SID, 
                             PERIOD_SID, 
                             RETURN_RATE) 
                SELECT PROJECTION_MASTER_SID, 
                       CONTRACT_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       RS_MODEL_SID, 
                       ITEM_MASTER_SID, 
                       @FROM_PEIOD_SID AS PERIOD_SID, 
                       0 
                FROM   #TEMP_ARM_LOGIC R 
            END 
--Pulling Return rate and Tier Opertaor Logic Ends here
-- TIER_OPERATOR = % IMPLEMENTATION   (GALUAT-360) Ends here
          IF OBJECT_ID('TEMPDB..#TEMP_ITEM_PRICING') IS NOT NULL 
            DROP TABLE #TEMP_ITEM_PRICING 

          CREATE TABLE #TEMP_ITEM_PRICING 
            ( 
               PROJECTION_MASTER_SID  INT, 
               CONTRACT_MASTER_SID    INT, 
               COMPANY_MASTER_SID     INT, 
               RS_MODEL_SID           INT, 
               ITEM_MASTER_SID        INT, 
               ITEM_PRICING_QUALIFIER VARCHAR(500), 
               PERIOD_SID             INT, 
               ITEM_PRICE             NUMERIC(22, 6) 
            ) 

          DECLARE @PRICING_UDT UDT_ITEM 

          INSERT INTO @PRICING_UDT 
                      (ITEM_MASTER_SID) 
          SELECT DISTINCT ITEM_MASTER_SID 
          FROM   #TEMP_ARM_LOGIC 

          IF EXISTS (SELECT 1 
                     FROM   #TIER_INFO 
                     WHERE  ITEM_PRICING_QUALIFIER_SID IS NOT NULL) 
            BEGIN 
 -- TIER_OPERATOR = $ IMPLEMENTATION STARTS here and Pricing Qualifiers Implementations starts here
                IF OBJECT_ID('TEMPDB..#ITEM_QUALIFIER') IS NOT NULL 
                  TRUNCATE TABLE #ITEM_QUALIFIER 
                ELSE 
                  CREATE TABLE #ITEM_QUALIFIER 
                    ( 
                       CONTRACT_MASTER_SID        INT, 
                       COMPANY_MASTER_SID         INT, 
                       ITEM_MASTER_SID            INT, 
                       RS_MODEL_SID               INT, 
                       ITEM_PRICING_QUALIFIER_SID INT, 
                       PRICING_QUALIFIER          VARCHAR(500) 
                    ) 

                INSERT INTO #ITEM_QUALIFIER 
                            (CONTRACT_MASTER_SID, 
                             COMPANY_MASTER_SID, 
                             ITEM_MASTER_SID, 
                             RS_MODEL_SID, 
                             ITEM_PRICING_QUALIFIER_SID, 
                             PRICING_QUALIFIER) 
                SELECT RS.CONTRACT_MASTER_SID, 
                       RS.COMPANY_MASTER_SID, 
                       RS.ITEM_MASTER_SID, 
                       RS.RS_MODEL_SID, 
                       IQ.ITEM_PRICING_QUALIFIER_SID, 
                       IQ.PRICING_QUALIFIER 
                FROM   #TEMP_ARM_LOGIC RS 
                       INNER JOIN (SELECT CONTRACT_MASTER_SID, 
                                          COMPANY_MASTER_SID, 
                                          ITEM_MASTER_SID, 
                                          RS_MODEL_SID, 
                                          ITEM_PRICING_QUALIFIER_SID 
                                   FROM   (SELECT ROW_NUMBER() 
                                                    OVER ( 
                                                      PARTITION BY CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID, RS_MODEL_SID
                                                      ORDER BY CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID, RS_MODEL_SID)RN,
                                                  CONTRACT_MASTER_SID, 
                                                  COMPANY_MASTER_SID, 
                                                  ITEM_MASTER_SID, 
                                                  RS_MODEL_SID, 
                                                  ITEM_PRICING_QUALIFIER_SID 
                                           FROM   #TIER_INFO)A 
                                   WHERE  RN = 1) TI 
                               ON RS.CONTRACT_MASTER_SID = TI.CONTRACT_MASTER_SID 
                                  AND RS.COMPANY_MASTER_SID = TI.COMPANY_MASTER_SID 
                                  AND RS.ITEM_MASTER_SID = TI.ITEM_MASTER_SID 
                                  AND RS.RS_MODEL_SID = TI.RS_MODEL_SID 
                       INNER JOIN ITEM_PRICING_QUALIFIER IQ 
                               ON IQ.ITEM_PRICING_QUALIFIER_SID = TI.ITEM_PRICING_QUALIFIER_SID

                DECLARE @PRICING_QUALIFIER VARCHAR(8000) 

                SET @PRICING_QUALIFIER= 'BQWAC,BPWAC,EPWAC,EQWAC,WAC,AMP,ANON-FAMP,BP,FCP,FSS,PHS,QNON-FAMP,URA,AFSS,QFSS,TRI-CARE,ALTURA,BURA,CPIURA,MQWAC,PRIOR PERIOD NEP,PRIOR PERIOD WAC,PRIOR PERIOD NET WAC' --CHANGE TO TABLE MAPPING IN QA AND UAT            
 -- FOR FINDING LATEST NA_PROJECTIONS FOR EACH ITEM_MASTER_SID starts here
                IF OBJECT_ID('TEMPDB..#ITEM_NA_PROJ') IS NOT NULL 
                  TRUNCATE TABLE #ITEM_NA_PROJ 
                ELSE 
                  CREATE TABLE #ITEM_NA_PROJ 
                    ( 
                       ID                  INT IDENTITY(1, 1), 
                       ITEM_MASTER_SID     INT, 
                       NA_PROJ_MASTER_SID  INT, 
                       NA_PROJ_DETAILS_SID INT 
                    ) 

                INSERT INTO #ITEM_NA_PROJ 
                            (ITEM_MASTER_SID, 
                             NA_PROJ_MASTER_SID, 
                             NA_PROJ_DETAILS_SID) 
                SELECT ITEM_MASTER_SID, 
                       NA_PROJ_MASTER_SID, 
                       NA_PROJ_DETAILS_SID 
                FROM   (SELECT IT.ITEM_MASTER_SID, 
                               NA.NA_PROJ_MASTER_SID, 
                               PD.NA_PROJ_DETAILS_SID, 
                               ROW_NUMBER() 
                                 OVER( 
                                   PARTITION BY IT.ITEM_MASTER_SID 
                                   ORDER BY COALESCE(NA.MODIFIED_DATE, NA.CREATED_DATE) DESC ) AS RN
                        FROM   NA_PROJ_DETAILS PD 
                               JOIN NA_PROJ_MASTER NA 
                                 ON PD.NA_PROJ_MASTER_SID = NA.NA_PROJ_MASTER_SID 
                               JOIN @ITEM_UDT IT 
                                 ON IT.ITEM_MASTER_SID = PD.ITEM_MASTER_SID 
                        WHERE  NA.SAVE_FLAG = 1)A 
                WHERE  RN = 1 
           
                INSERT INTO #TEMP_ITEM_PRICING 
                            (PROJECTION_MASTER_SID, 
                             CONTRACT_MASTER_SID, 
                             COMPANY_MASTER_SID, 
                             RS_MODEL_SID, 
                             ITEM_MASTER_SID, 
                             PERIOD_SID, 
                             ITEM_PRICE, 
                             ITEM_PRICING_QUALIFIER) 
                SELECT TAL.PROJECTION_MASTER_SID, 
                       TAL.CONTRACT_MASTER_SID, 
                       TAL.COMPANY_MASTER_SID, 
                       TAL.RS_MODEL_SID, 
                       TAL.ITEM_MASTER_SID, 
                       @FROM_PEIOD_SID AS PERIOD_SID, 
                       0, 
                       TQ.PRICING_QUALIFIER AS ITEM_PRICING_QUALIFIER 
                FROM   #TEMP_ARM_LOGIC TAL 
                       INNER JOIN #ITEM_QUALIFIER TQ 
                               ON TAL.ITEM_MASTER_SID = TQ.ITEM_MASTER_SID 
                                  AND TAL.COMPANY_MASTER_SID = TQ.COMPANY_MASTER_SID 
                                  AND TAL.CONTRACT_MASTER_SID = TQ.CONTRACT_MASTER_SID 
                                  AND TAL.RS_MODEL_SID = TQ.RS_MODEL_SID; 

                WITH PERIOD_NA 
                     AS (SELECT PERIOD_SID, 
                                QUARTER AS PERIOD_QUARTER, 
                                YEAR    AS PERIOD_YEAR 
                         FROM   #PERIOD 
                         WHERE  [QUARTER] = DATEPART(Q, @FROM_PERIOD_DATE) 
                                AND [YEAR] = YEAR(@FROM_PERIOD_DATE)), 
                     ITEM_PRICE 
                     AS (SELECT IQU.CONTRACT_MASTER_SID, 
                                IQU.COMPANY_MASTER_SID, 
                                IQU.RS_MODEL_SID, 
                                IQU.ITEM_MASTER_SID, 
                                IQU.ITEM_PRICING_QUALIFIER_SID, 
                                IQU.PRICING_QUALIFIER, 
                                UDF.PERIOD_SID, 
                                IIF(UDF.ITEM_PRICE = 0, NULL, UDF.ITEM_PRICE) AS ITEM_PRICE,--- GAL-1008, 
                                P.PERIOD_QUARTER, 
                                P.PERIOD_YEAR 
                         FROM   #ITEM_QUALIFIER IQU 
                                INNER JOIN UDF_ITEM_PRICING(@PRICING_UDT, @PRICING_QUALIFIER, @FROM_PEIOD_SID, @FROM_PEIOD_SID, @ITEM_UOM) UDF -----  @ITEM_UOM 'UN' TO 'EACH'  GALUAT-46 
                                        ON IQU.ITEM_MASTER_SID = UDF.ITEM_MASTER_SID 
                                           AND IQU.PRICING_QUALIFIER = UDF.PRICING_QUALIFIER 
                                INNER JOIN PERIOD_NA P 
                                        ON UDF.PERIOD_SID = P.PERIOD_SID), 
                     WORKSHEETS_RESULT 
                     AS (SELECT CASE 
                                  WHEN IP.PRICING_QUALIFIER IN ( 'BP', 'CPIURA', 'AMP', 'ALTURA',
                                                                 'URA', 'BURA' ) THEN COALESCE(IP.ITEM_PRICE, NULLIF(M.PROJECTION_PRICE, 0), 0)
                                  WHEN IP.PRICING_QUALIFIER IN ( 'ANON-FAMP', 'AFSS', 'FCP', 'TRI-CARE', 
                                                                 'QNON-FAMP', 'QFSS' ) THEN COALESCE(IP.ITEM_PRICE, NULLIF(F.PROJECTION_PRICE, 0), 0) 
                                  WHEN IP.PRICING_QUALIFIER = 'PHS' THEN COALESCE(IP.ITEM_PRICE, NULLIF(P.PROJECTION_PRICE, 0), 0)
                                  ELSE IP.ITEM_PRICE 
                                END AS ITEM_PRICE, 
                                IP.PRICING_QUALIFIER, 
                                IP.PERIOD_SID, 
                                IQ.CONTRACT_MASTER_SID, 
                                IQ.COMPANY_MASTER_SID, 
                                IQ.RS_MODEL_SID, 
                                IQ.ITEM_MASTER_SID, 
                                IT_NA.NA_PROJ_MASTER_SID, 
                                IP.PERIOD_QUARTER, 
                                IP.PERIOD_YEAR 
                         FROM   ITEM_PRICE IP 
                                LEFT JOIN #ITEM_QUALIFIER IQ 
                                       ON IP.CONTRACT_MASTER_SID = IQ.CONTRACT_MASTER_SID 
                                          AND IP.COMPANY_MASTER_SID = IQ.COMPANY_MASTER_SID 
                                          AND IP.RS_MODEL_SID = IQ.RS_MODEL_SID 
                                          AND IP.ITEM_MASTER_SID = IQ.ITEM_MASTER_SID 
                                INNER JOIN #ITEM_NA_PROJ IT_NA 
                                        ON IT_NA.ITEM_MASTER_SID = IQ.ITEM_MASTER_SID ------------------------------------------ FOR LATEST NA PROJECTIONS 
                                LEFT JOIN (SELECT ( MP.PROJECTION_PRICE ) AS PROJECTION_PRICE,
                                                  PN.PERIOD_QUARTER, 
                                                  PN.PERIOD_YEAR, 
                                                  MP.PRICE_TYPE, 
                                                  MP.NA_PROJ_DETAILS_SID 
                                           FROM   MEDICAID_URA_PROJ MP 
                                                  LEFT JOIN (SELECT MIN(PERIOD_SID) AS PERIOD_SID, 
                                                                    PERIOD_QUARTER, 
                                                                    PERIOD_YEAR 
                                                             FROM   PERIOD_NA 
                                                             GROUP  BY PERIOD_QUARTER, 
                                                                       PERIOD_YEAR) PN 
                                                         ON PN.PERIOD_SID = MP.PERIOD_SID) M 
                                       ON IT_NA.NA_PROJ_DETAILS_SID = M.NA_PROJ_DETAILS_SID 
                                          AND IP.PERIOD_QUARTER = M.PERIOD_QUARTER 
                                          AND IP.PERIOD_YEAR = M.PERIOD_YEAR 
                                          AND M.PRICE_TYPE = CASE 
                                                               WHEN IP.PRICING_QUALIFIER = 'BP' THEN 'BEST PRICE'
                                                               WHEN IP.PRICING_QUALIFIER = 'ALTURA' THEN 'ADJUSTMENT CPI (ALT)'
                                                               WHEN IP.PRICING_QUALIFIER = 'URA' THEN 'TOTAL URA'
                                                               WHEN IP.PRICING_QUALIFIER = 'BURA' THEN 'BASIC URA'
                                                               ELSE IP.PRICING_QUALIFIER 
                                                             END 
                                LEFT JOIN (SELECT ( MP.PROJECTION_PRICE ) AS PROJECTION_PRICE,
                                                  PN.PERIOD_QUARTER, 
                                                  PN.PERIOD_YEAR, 
                                                  MP.PRICE_TYPE, 
                                                  MP.NA_PROJ_DETAILS_SID 
                                           FROM   FCP_PROJ MP 
                                                  LEFT JOIN (SELECT MIN(PERIOD_SID) AS PERIOD_SID,
                                                                    PERIOD_QUARTER, 
                                                                    PERIOD_YEAR 
                                                             FROM   PERIOD_NA 
                                                             GROUP  BY PERIOD_QUARTER, 
                                                                       PERIOD_YEAR) PN 
                                                         ON PN.PERIOD_SID = MP.PERIOD_SID) F 
                                       ON IT_NA.NA_PROJ_DETAILS_SID = F.NA_PROJ_DETAILS_SID 
                                          AND CASE 
                                                WHEN F.PRICE_TYPE IN ( 'QFSS', 'QNON-FAMP' ) THEN IP.PERIOD_QUARTER 
                                                ELSE 1 
                                              END = CASE 
                                                      WHEN F.PRICE_TYPE IN ( 'QFSS', 'QNON-FAMP' ) THEN F.PERIOD_QUARTER 
                                                      ELSE 1 
                                                    END 
                                          AND IP.PERIOD_YEAR = F.PERIOD_YEAR 
                                          AND IP.PRICING_QUALIFIER = F.PRICE_TYPE 
                                LEFT JOIN (SELECT ( MP.PROJECTION_PRICE ) AS PROJECTION_PRICE,
                                                  PN.PERIOD_QUARTER, 
                                                  PN.PERIOD_YEAR, 
                                                  MP.PRICE_TYPE, 
                                                  MP.NA_PROJ_DETAILS_SID 
                                           FROM   PHS_PROJ MP 
                                                  LEFT JOIN (SELECT MIN(PERIOD_SID) AS PERIOD_SID,
                                                                    PERIOD_QUARTER, 
                                                                    PERIOD_YEAR 
                                                             FROM   PERIOD_NA 
                                                             GROUP  BY PERIOD_QUARTER, 
                                                                       PERIOD_YEAR) PN 
                                                         ON PN.PERIOD_SID = MP.PERIOD_SID) P 
                                       ON IT_NA.NA_PROJ_DETAILS_SID = P.NA_PROJ_DETAILS_SID 
                                          AND IP.PERIOD_QUARTER = P.PERIOD_QUARTER 
                                          AND IP.PERIOD_YEAR = P.PERIOD_YEAR 
                                          AND IP.PRICING_QUALIFIER = P.PRICE_TYPE) 
                UPDATE T 
                SET    T.ITEM_PRICE = R.ITEM_PRICE 
                FROM   #TEMP_ITEM_PRICING T 
                       INNER JOIN WORKSHEETS_RESULT R 
                               ON T.CONTRACT_MASTER_SID = R.CONTRACT_MASTER_SID 
                                  AND T.COMPANY_MASTER_SID = R.COMPANY_MASTER_SID 
                                  AND T.RS_MODEL_SID = R.RS_MODEL_SID 
                                  AND T.ITEM_MASTER_SID = R.ITEM_MASTER_SID 
                                  AND T.PERIOD_SID = R.PERIOD_SID 
                                  AND T.ITEM_PRICING_QUALIFIER = R.PRICING_QUALIFIER 
            END 

          IF NOT EXISTS (SELECT 1 
                         FROM   #TEMP_ITEM_PRICING) 
            BEGIN 
                INSERT INTO #TEMP_ITEM_PRICING 
                            (PROJECTION_MASTER_SID, 
                             CONTRACT_MASTER_SID, 
                             COMPANY_MASTER_SID, 
                             RS_MODEL_SID, 
                             ITEM_MASTER_SID, 
                             PERIOD_SID, 
                             ITEM_PRICE) 
                SELECT PROJECTION_MASTER_SID, 
                       CONTRACT_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       RS_MODEL_SID, 
                       ITEM_MASTER_SID, 
                       @FROM_PEIOD_SID AS PERIOD_SID, 
                       0 
                FROM   #TEMP_ARM_LOGIC 
            END 

 -- TIER_OPERATOR = $ IMPLEMENTATION ENDS here
          IF OBJECT_ID('TEMPDB..#TEMP_CTE') IS NOT NULL 
            DROP TABLE #TEMP_CTE 

          CREATE TABLE #TEMP_CTE 
            ( 
               PROJECTION_MASTER_SID INT, 
               COMPANY_MASTER_SID    INT, 
               ITEM_MASTER_SID       INT, 
               VALUE                 NUMERIC(22, 6), 
               PRIMARY KEY (PROJECTION_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID) 
            ) 

          INSERT INTO #TEMP_CTE 
                      (PROJECTION_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       ITEM_MASTER_SID, 
                       VALUE) 
          SELECT PROJECTION_MASTER_SID, 
                 COMPANY_MASTER_SID, 
                 ITEM_MASTER_SID, 
                 SUM(CASE 
                             WHEN TIER_OPERATOR = '%' THEN ( AMOUNT / NULLIF(ADJUSTED_CALCULATED_AMOUNT, 0) ) --MULTIPLY BY 100 REMOVED DUE TO REQUEST RATE NEEDS TO BE IN NUMERIC  
                             WHEN TIER_OPERATOR = '$' THEN ( AMOUNT / NULLIF(TOTAL_INVENTORY, 0) )
                           END) AS VALUE
          FROM   (SELECT PROJECTION_MASTER_SID, 
                         COMPANY_MASTER_SID, 
                         ITEM_MASTER_SID, 
                         CASE 
                                  WHEN REBATE_STRUCTURE = 'TIER' THEN VALUE * CASE 
                                                                                WHEN TIER_OPERATOR = '%' THEN ADJUSTED_CALCULATED_AMOUNT
                                                                                WHEN TIER_OPERATOR = '$' THEN TOTAL_INVENTORY
                                                                              END 
                                  WHEN REBATE_STRUCTURE = 'LEVEL' THEN 
                                    CASE 
                                      WHEN CALCULATION_LEVEL LIKE '%LINE%' THEN VALUE 
                                      ELSE VALUE * RATIO 
                                    END 
                                  WHEN REBATE_STRUCTURE = 'FLAT' THEN 
                                    CASE 
                                      WHEN CALCULATION_LEVEL LIKE '%LINE%' THEN VALUE 
                                      ELSE VALUE * RATIO 
                                    END 
                                END AS AMOUNT, 
                         TIER_OPERATOR, 
                         ADJUSTED_CALCULATED_AMOUNT, 
                         TOTAL_INVENTORY 
                  FROM   (SELECT ROW_NUMBER () 
                                   OVER ( 
                                     PARTITION BY CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID, RS_MODEL_SID
                                     ORDER BY PROJECTION_MASTER_SID)RN, 
                                 PROJECTION_MASTER_SID, 
                                 COMPANY_MASTER_SID, 
                                 ITEM_MASTER_SID, 
                                 SUM(VALUE) 
                                         OVER ( 
                                           PARTITION BY CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID, RS_MODEL_SID) AS VALUE, 
                                 ( ( CASE 
                                             WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT SALES', 'DOLLARS' ) THEN ADJUSTED_CALCULATED_AMOUNT
                                             WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT UNITS', 'UNITS' ) THEN TOTAL_INVENTORY
                                           END ) / NULLIF(SALES_PROJECTED_VALUE, 0) ) AS RATIO, 
                                 ADJUSTED_CALCULATED_AMOUNT, 
                                 TOTAL_INVENTORY, 
                                 TIER_OPERATOR, 
                                 REBATE_STRUCTURE, 
                                 CALCULATION_LEVEL 
                          FROM   (SELECT A.PROJECTION_MASTER_SID, 
                                         A.CONTRACT_MASTER_SID, 
                                         A.COMPANY_MASTER_SID, 
                                         A.ITEM_MASTER_SID, 
                                         A.RS_MODEL_SID, 
                                         CASE 
                                                 WHEN A.REBATE_STRUCTURE = 'TIER' THEN 
                                                   CASE 
                                                     WHEN A.SALES_PROJECTED_VALUE >= OU.TIER_FROM 
                                                          AND A.SALES_PROJECTED_VALUE <= ISNULL(OU.TIER_TO, A.SALES_PROJECTED_VALUE) THEN
                                                       CASE 
                                                         WHEN OU.TIER_OPERATOR = '%' THEN ( COALESCE(NULLIF(OU.TIER_VALUE, 0), NULLIF(A.RETURN_RATE, 0)) / 100.0 )
                                                         WHEN OU.TIER_OPERATOR = '$' THEN COALESCE(NULLIF(OU.TIER_VALUE, 0), NULLIF(A.ITEM_PRICE, 0), 0)
                                                       END 
                                                   END 
                                                 WHEN A.REBATE_STRUCTURE = 'LEVEL' THEN 
                                                   CASE 
                                                     WHEN OU.TIER_TO < A.SALES_PROJECTED_VALUE THEN
                                                       CASE 
                                                         WHEN OU.TIER_OPERATOR = '%' THEN ( ( OU.TIER_TO - OU.TIER_FROM ) * ( COALESCE(NULLIF(OU.TIER_VALUE, 0), NULLIF(A.RETURN_RATE, 0)) / 100.0 ) )
                                                         WHEN OU.TIER_OPERATOR = '$' THEN ( ( OU.TIER_TO - OU.TIER_FROM ) * ( COALESCE(NULLIF(OU.TIER_VALUE, 0), NULLIF(A.ITEM_PRICE, 0), 0) ) )
                                                       END 
                                                     WHEN OU.TIER_TO >= A.SALES_PROJECTED_VALUE THEN
                                                       CASE 
                                                         WHEN A.SALES_PROJECTED_VALUE BETWEEN OU.TIER_FROM AND OU.TIER_TO THEN
                                                           CASE 
                                                             WHEN OU.TIER_OPERATOR = '%' THEN ( ( IIF(A.SALES_PROJECTED_VALUE < OU.TIER_TO, A.SALES_PROJECTED_VALUE, OU.TIER_TO) - OU.TIER_FROM ) * ( COALESCE(NULLIF(OU.TIER_VALUE, 0), NULLIF(A.RETURN_RATE, 0)) / 100.0 ) )
                                                             WHEN OU.TIER_OPERATOR = '$' THEN ( ( IIF(A.SALES_PROJECTED_VALUE < OU.TIER_TO, A.SALES_PROJECTED_VALUE, OU.TIER_TO) - OU.TIER_FROM ) * ( COALESCE(NULLIF(OU.TIER_VALUE, 0), NULLIF(A.ITEM_PRICE, 0), 0) ) )
                                                           END 
                                                       END 
                                                   END 
                                                 WHEN A.REBATE_STRUCTURE = 'FLAT' THEN OU.TIER_VALUE -- ITS APPLICALBLE ONLY FOR OPERATOR=$      
                                               END AS VALUE, 
                                         A.SALES_PROJECTED_VALUE, 
                                         A.ADJUSTED_CALCULATED_AMOUNT, 
                                         A.TOTAL_INVENTORY, 
                                         A.REBATE_RANGE_BASED_ON, 
                                         OU.TIER_OPERATOR, 
                                         A.REBATE_STRUCTURE, 
                                         A.CALCULATION_LEVEL 
                                  FROM   (SELECT TAL.PROJECTION_MASTER_SID, 
                                                 TAL.CONTRACT_MASTER_SID, 
                                                 TAL.COMPANY_MASTER_SID, 
                                                 TAL.ITEM_MASTER_SID, 
                                                 TAL.RS_MODEL_SID, 
                                                 TAL.REBATE_STRUCTURE, 
                                                 TAL.CALCULATION_LEVEL, 
                                                 CASE 
                                                                          WHEN TAL.CALCULATION_LEVEL LIKE '%SINGLE%' THEN SUM(TAL.SALES_PROJECTED_VALUE)
                                                                                                                        OVER (
                                                                                                                          PARTITION BY RP.RS_MODEL_SID, RP.PERIOD_SID, RP.COMPANY_MASTER_SID)
                                                                          WHEN TAL.CALCULATION_LEVEL LIKE '%LINE%' THEN SUM(TAL.SALES_PROJECTED_VALUE)
                                                                                                                      OVER (
                                                                                                                        PARTITION BY RP.RS_MODEL_SID, RP.CONTRACT_MASTER_SID, RP.COMPANY_MASTER_SID, RP.ITEM_MASTER_SID, RP.PERIOD_SID)
                                                                          WHEN TAL.CALCULATION_LEVEL LIKE '%BUNDLE%' THEN SUM(TAL.SALES_PROJECTED_VALUE)
                                                                                                                        OVER (
                                                                                                                          PARTITION BY RP.COMPANY_MASTER_SID, RP.RS_MODEL_SID, TAL.BUNDLE_NO, RP.PERIOD_SID)
                                                                        END AS SALES_PROJECTED_VALUE, 
                                                 RP.RETURN_RATE, 
                                                 TAL.ADJUSTED_CALCULATED_AMOUNT, 
                                                 TAL.TOTAL_INVENTORY, 
                                                 TAL.REBATE_RANGE_BASED_ON, 
                                                 TIP.ITEM_PRICE 
                                          FROM   #TEMP_ARM_LOGIC TAL 
                                                 INNER JOIN #RETURNS_PERCENT RP 
                                                         ON TAL.CONTRACT_MASTER_SID = RP.CONTRACT_MASTER_SID
                                                            AND TAL.COMPANY_MASTER_SID = RP.COMPANY_MASTER_SID
                                                            AND TAL.ITEM_MASTER_SID = RP.ITEM_MASTER_SID
                                                            AND TAL.RS_MODEL_SID = RP.RS_MODEL_SID
                                                            AND TAL.PROJECTION_MASTER_SID = RP.PROJECTION_MASTER_SID
                                                 INNER JOIN #TEMP_ITEM_PRICING TIP 
                                                         ON TAL.CONTRACT_MASTER_SID = TIP.CONTRACT_MASTER_SID
                                                            AND TAL.COMPANY_MASTER_SID = TIP.COMPANY_MASTER_SID
                                                            AND TAL.ITEM_MASTER_SID = TIP.ITEM_MASTER_SID
                                                            AND TAL.RS_MODEL_SID = TIP.RS_MODEL_SID
                                                            AND TAL.PROJECTION_MASTER_SID = TIP.PROJECTION_MASTER_SID)A
                                         OUTER APPLY (SELECT TIER_FROM, 
                                                             CASE 
                                                                       WHEN T.TIER_TO IS NULL THEN SALES_PROJECTED_VALUE
                                                                       ELSE T.TIER_TO 
                                                                     END AS TIER_TO, 
                                                             TIER_VALUE, 
                                                             TIER_OPERATOR 
                                                      FROM   #TIER_INFO T 
                                                      WHERE  A.PROJECTION_MASTER_SID = T.PROJECTION_MASTER_SID
                                                             AND A.CONTRACT_MASTER_SID = T.CONTRACT_MASTER_SID
                                                             AND A.COMPANY_MASTER_SID = T.COMPANY_MASTER_SID
                                                             AND A.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                                                             AND A.RS_MODEL_SID = T.RS_MODEL_SID) OU)C)A
                  WHERE  RN = 1)B 
          GROUP  BY PROJECTION_MASTER_SID, 
                    COMPANY_MASTER_SID, 
                    ITEM_MASTER_SID ;

--Deleting and re inserting into main table for particular Projection starts here	 

		SET @SQL=CONCAT('TRUNCATE TABLE ',@INFLATION_INVENTORY_TABLE,'  
        

          INSERT INTO ',@INFLATION_INVENTORY_TABLE,' 
                      (PROJECTION_MASTER_SID, 
                       ITEM_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       PERIOD_SID, 
                       TOTAL_INVENTORY, 
                       BASELINE_PRICE, 
                       ADJUSTED_PRICE, 
                       PRICE_CHANGE, 
                       PRICE_CHANGE_PERCENT, 
                       BASELINE_CALCULATED_AMOUNT, 
                       ADJUSTED_CALCULATED_AMOUNT, 
                       NET_CALCULATED_AMOUNT, 
                       INFLATION_FACTOR, 
                       INFLATION_ADJUSTMENT) 
          SELECT A.PROJECTION_MASTER_SID, 
                 A.ITEM_MASTER_SID, 
                 A.COMPANY_MASTER_SID, 
                 A.PERIOD_SID, 
                 A.TOTAL_INVENTORY, 
                 A.BASELINE_PRICE, 
                 A.ADJUSTED_PRICE, 
                 A.PRICE_CHANGE, 
                 A.PRICE_CHANGE_PERCENT, 
                 A.BASELINE_CALCULATED_AMOUNT, 
                 A.ADJUSTED_CALCULATED_AMOUNT, 
                 A.NET_CALCULATED_AMOUNT, 
                 ISNULL(B.VALUE, 0)                           AS INFLATION_FACTOR, 
                 A.NET_CALCULATED_AMOUNT * ISNULL(B.VALUE, 0) AS INFLATION_ADJUSTMENT
          FROM   #TEMP_INFLATION_INVENTORY A 
                 LEFT JOIN #TEMP_CTE B 
                        ON A.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID 
                           AND A.ITEM_MASTER_SID = B.ITEM_MASTER_SID 
                           AND A.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID')
						    
--Deleting and re inserting into main table for particular Projection Ends here
		
		EXEC sp_executesql @SQL

--Debit and Credt Logic Implementaion for both Liability and Expense account(for BSR-BALANCE Summary Report Chnages starts here)

		IF OBJECT_ID('TEMPDB..#ARM_INFLATION_SUMMARY') IS NOT NULL 
            DROP TABLE #ARM_INFLATION_SUMMARY 

			CREATE TABLE #ARM_INFLATION_SUMMARY
			(
			  ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL, 
			  PERIOD_SID INT NOT NULL, 
			  CURRENT_BALANCE NUMERIC(22,6) NULL, 
			  CALCULATED_ADJUSTMENT NUMERIC(22,6) NULL,
			  ADJUSTMENT_RATIO NUMERIC(22,6) NULL,
			  VARIANCE NUMERIC(22,6) NULL,
			  ARM_ADJUSTMENT_CONFIG_SID INT NOT NULL
			  PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID)
			  
			  )


			  declare @sql1 varchar(max)

			set @sql1=CONCAT('INSERT INTO #ARM_INFLATION_SUMMARY
                      (ARM_ADJUSTMENT_DETAILS_SID, 
                       PERIOD_SID, 
                       CURRENT_BALANCE, 
                       CALCULATED_ADJUSTMENT, 
                       ADJUSTMENT_RATIO, 
                       VARIANCE,
					   ARM_ADJUSTMENT_CONFIG_SID) 
          SELECT TAR.ARM_ADJUSTMENT_DETAILS_SID, 
                 TAR.PERIOD_SID, 
                 ISNULL(AD.CURRENT_BALANCE, 0) aS CURRENT_BALANCE, 
                 ISNULL(B.VALUE * TII.NET_CALCULATED_AMOUNT, 0) AS CALCULATED_ADJUSTMENT, 
                 COALESCE(ISNULL(AD.CURRENT_BALANCE, 0) / NULLIF(ISNULL(B.VALUE * TII.NET_CALCULATED_AMOUNT, 0), 0), 0) aS ADJUSTMENT_RATIO,
                 ISNULL(AD.CURRENT_BALANCE, 0) - ISNULL(B.VALUE * TII.NET_CALCULATED_AMOUNT, 0) aS VARIANCE,
				 ARM_ADJUSTMENT_CONFIG_SID

          FROM   #TEMP_ARM_PROJ_MASTER TAR 
                 LEFT JOIN #TEMP_INFLATION_INVENTORY TII 
                        ON TAR.ITEM_MASTER_SID = TII.ITEM_MASTER_SID 
                           AND TAR.COMPANY_MASTER_SID = TII.COMPANY_MASTER_SID 
                           AND TAR.PERIOD_SID = TII.PERIOD_SID 
                 LEFT JOIN ',@ARM_CURRENT_BALANCE,' AD 
                        ON TAR.CCP_DETAILS_SID = AD.CCP_DETAILS_SID 
                           AND TAR.RS_MODEL_SID = AD.RS_MODEL_SID 
                           AND TAR.PERIOD_SID = AD.PERIOD_SID 
                 LEFT JOIN #TEMP_CTE B 
                        ON TII.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID 
                           AND TII.ITEM_MASTER_SID = B.ITEM_MASTER_SID 
                           AND TII.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID','')
						   EXEC(@SQL1) 

--checking with amount for indicator changes starts here 
 IF OBJECT_ID('TEMPDB..#TEMP_AMOUNT') IS NOT NULL 
  DROP TABLE #TEMP_AMOUNT 

CREATE TABLE #TEMP_AMOUNT 
  ( 
     ARM_ADJUSTMENT_DETAILS_SID INT, 
     PERIOD_SID                 INT, 
     ACCRUAL_AMOUNT             NUMERIC(22, 6), 
     INDICATOR                  BIT,
	 ARM_ADJUSTMENT_CONFIG_SID INT
  ) 

INSERT INTO #TEMP_AMOUNT 
            (ARM_ADJUSTMENT_DETAILS_SID, 
             PERIOD_SID, 
             ACCRUAL_AMOUNT, 
             INDICATOR,
			 ARM_ADJUSTMENT_CONFIG_SID) 
SELECT ARM_ADJUSTMENT_DETAILS_SID, 
       PERIOD_SID, 
       SUM (VARIANCE) AS ACCRUAL_AMOUNT, 
       CASE 
         WHEN SUM(VARIANCE) > 0 THEN 0 
         WHEN SUM(VARIANCE) < 0 THEN 1 
         ELSE NULL 
       END                                 AS INDICATOR,
	   ARM_ADJUSTMENT_CONFIG_SID
FROM   #ARM_INFLATION_SUMMARY 
GROUP BY ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID,ARM_ADJUSTMENT_CONFIG_SID

--checking with amount for indicator changes Ends here 

--taking Liability accounts for the particular CCP+D Combination and Calculated the Current balnce for liability accounts starts here

 			SET @SQL=CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_ADJUSTMENTS, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
                                          ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
					                   BEGIN 
												TRUNCATE TABLE ', @ARM_ADJUSTMENTS, '  
									   END
				INSERT INTO ',@ARM_ADJUSTMENTS, 
                            '(ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             ADJUSTMENT_TYPE,
							 ACCOUNT,
                             CREDIT,
							 DEBIT
							 )
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
					   TAM.PERIOD_SID AS PERIOD_SID,
					   ', @ADJUSTMENT_TYPE ,' AS ADJUSTMENT_TYPE,
					   AAC.ACCOUNT,
					   SUM(ABS(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT,
					   SUM(ABS(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT
				FROM   #TEMP_AMOUNT TAM 
					   LEFT JOIN ARM_ADJ_RES_CCP AAC 
						 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
								  AND AAC.ADJUSTMENT_TYPE = ', @ADJUSTMENT_TYPE ,'
					   LEFT JOIN #TEMP_AMOUNT A1 
							  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
								 AND AAC.CREDIT = A1.INDICATOR 
					   LEFT JOIN #TEMP_AMOUNT A2 
							  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
								 AND AAC.DEBIT = A2.INDICATOR 
						LEFT JOIN PERIOD P ON P.PERIOD_SID=ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) AND P.PERIOD_SID=TAM.PERIOD_SID
						WHERE AAC.CREDIT IS NOT NULL AND AAC.DEBIT IS NOT NULL
				GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
						  TAM.PERIOD_SID,
						  AAC.ACCOUNT')


						  EXEC(@SQL)

--taking Liability accounts for the particular CCP+D Combination and Calculated the Current balnce for liability accounts Ends here

--taking Expense accounts for the particular CCP+D Combination and Calculated the Current balnce for liability accounts starts here
IF OBJECT_ID('TEMPDB..#CURRENT_LIABILITY') IS NOT NULL 
  DROP TABLE #CURRENT_LIABILITY 

CREATE TABLE #CURRENT_LIABILITY 
  ( 
     ARM_ADJUSTMENT_DETAILS_SID INT, 
     PERIOD_SID                 INT, 
     CREDIT_AMOUNT               NUMERIC(22, 6), 
     DEBIT_AMOUNT              NUMERIC(22, 6), 
     CURRENT_AMOUNT             NUMERIC(22, 6) 
  ) 

INSERT INTO #CURRENT_LIABILITY (ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID,CREDIT_AMOUNT,DEBIT_AMOUNT,CURRENT_AMOUNT)
SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
       ISNULL(A1.PERIOD_SID, A2.PERIOD_SID), 
       SUM(ABS(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT_AMOUNT,
       SUM(ABS(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT_AMOUNT,
       ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
FROM   #TEMP_AMOUNT TAM 
       JOIN ARM_ADJ_RES_CCP AAC 
         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
       INNER JOIN HELPER_TABLE HT 
               ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID 
                  AND HT.DESCRIPTION = 'LIABILITY' 
                  AND AAC.ADJUSTMENT_TYPE = TAM.ARM_ADJUSTMENT_CONFIG_SID 
       LEFT JOIN #TEMP_AMOUNT A1 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.CREDIT = A1.INDICATOR 
       LEFT JOIN #TEMP_AMOUNT A2 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.DEBIT = A2.INDICATOR 
	   JOIN PERIOD P ON P.PERIOD_SID=ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) AND P.PERIOD_SID=TAM.PERIOD_SID
WHERE AAC.CREDIT IS NOT NULL AND AAC.DEBIT IS NOT NULL
GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
          ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) 

IF OBJECT_ID('TEMPDB..#CURRENT_EXPENSE') IS NOT NULL 
  DROP TABLE #CURRENT_EXPENSE 

CREATE TABLE #CURRENT_EXPENSE 
  ( 
     ARM_ADJUSTMENT_DETAILS_SID INT, 
     PERIOD_SID                 INT, 
     CREDIT_AMOUNT               NUMERIC(22, 6), 
     DEBIT_AMOUNT              NUMERIC(22, 6), 
     CURRENT_AMOUNT             NUMERIC(22, 6) 
  ) 

INSERT INTO #CURRENT_EXPENSE (ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID,CREDIT_AMOUNT,DEBIT_AMOUNT,CURRENT_AMOUNT)
SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
       ISNULL(A1.PERIOD_SID, A2.PERIOD_SID), 
       SUM(ABS(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT_AMOUNT,
       SUM(ABS(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT_AMOUNT,
       ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
FROM   #TEMP_AMOUNT TAM 
       JOIN ARM_ADJ_RES_CCP AAC 
         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
       INNER JOIN HELPER_TABLE HT 
               ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID 
                  AND HT.DESCRIPTION = 'EXPENSE' 
                  AND AAC.ADJUSTMENT_TYPE = TAM.ARM_ADJUSTMENT_CONFIG_SID
       LEFT JOIN #TEMP_AMOUNT A1 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.CREDIT = A1.INDICATOR 
       LEFT JOIN #TEMP_AMOUNT A2 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.DEBIT = A2.INDICATOR
		JOIN PERIOD P ON P.PERIOD_SID=ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) AND P.PERIOD_SID=TAM.PERIOD_SID 
WHERE AAC.CREDIT IS NOT NULL AND AAC.DEBIT IS NOT NULL
GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
          ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) 

--taking Expense accounts for the particular CCP+D Combination and Calculated the Current balnce for liability accounts Ends here

--main table insertion starts here 

          SET @SQL=CONCAT('TRUNCATE TABLE ',@INFLATION_INVENTORY_ADJ_TABLE,'  
		    
		       INSERT INTO ',@INFLATION_INVENTORY_ADJ_TABLE,' 
                      (ARM_ADJUSTMENT_DETAILS_SID, 
                       PERIOD_SID, 
                       CURRENT_BALANCE, 
                       CALCULATED_ADJUSTMENT, 
                       ADJUSTMENT_RATIO, 
                       VARIANCE,
					   LIABILITY_AMOUNT,
					   EXPENSE_AMOUNT) 
          SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                 TAM.PERIOD_SID, 
                 TAM.CURRENT_BALANCE,
                 TAM.CALCULATED_ADJUSTMENT,
                 TAM.ADJUSTMENT_RATIO,
                 TAM.VARIANCE,
				 CL.CURRENT_AMOUNT AS LIABILITY_AMOUNT,
				 CE.CURRENT_AMOUNT AS EXPENSE_AMOUNT 
          FROM   #ARM_INFLATION_SUMMARY TAM
		   LEFT JOIN #CURRENT_LIABILITY CL
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CL.ARM_ADJUSTMENT_DETAILS_SID
                                 AND TAM.PERIOD_SID = CL.PERIOD_SID 
					  LEFT JOIN #CURRENT_EXPENSE CE
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CE.ARM_ADJUSTMENT_DETAILS_SID
                                 AND TAM.PERIOD_SID = CE.PERIOD_SID ')

--main table insertion Ends here 	
						 
		EXEC sp_executesql @SQL
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

          RAISERROR ( @ERRORMESSAGE,-- MESSAGE TEXT.                       
                      @ERRORSEVERITY,-- SEVERITY.                       
                      @ERRORSTATE,-- STATE.                       
                      @ERRORPROCEDURE,-- PROCEDURE_NAME.                       
                      @ERRORNUMBER,-- ERRORNUMBER                       
                      @ERRORLINE -- ERRORLINE                       
          ); 
      END CATCH 
  END 
 GO