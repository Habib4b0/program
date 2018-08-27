IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_ARM_PIPELINE_RATE' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].[PRC_ARM_PIPELINE_RATE] 
  END 

GO 

CREATE PROCEDURE [dbo].[PRC_ARM_PIPELINE_RATE] (@PROJECTION_MASTER_SID INT, 
                                               @RATE_BASIS            VARCHAR(50), 
                                               @RATE_PERIOD           VARCHAR(20), 
                                               @RATE_FREQUENCY        VARCHAR(20), 
                                               @MODULE                VARCHAR(50), 
											   @INVENTORY_DETAILS     VARCHAR(50),
                                               @USER_ID               INT, 
                                               @SESSION_ID            INT) 
AS
  /**********************************************************************************************************
  ** File Name		:	PRC_ARM_PIPELINE_RATE.sql
  ** Procedure Name	:	PRC_ARM_PIPELINE_RATE
  ** Description		:	To generate Pipeline Accrual(Tr-1) Rates tab
  ** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Tr-1
  						@RATE_BASIS             - It is used to pulling rate based on which files we need to refer
  						@RATE_PERIOD            - It indicates the period for pulling Rate
  						@RATE_FREQUENCY         - It indicates the frequency selected in Rates tab
  						@MODULE                 - It indicates the base module which refers(but currently we are overriding it after CR in below procedure)
  						@INVENTORY_DETAILS      -It indicates the Period for pulling Inventory values
  				        @USER_ID                - it is the identification of the User Performing particular projection
  						@SESSION_ID             - it is the identification of session for particular projection
  											
  ** Output Parameters:	NA
  ** Author Name		:	@Paul,@AjayNaidu
  ** Creation Date	:	09/11/2016 - MM/DD/YYYY
  ** Called By		:   Called by Application for Pipeline Accrual Transaction(as base) and in Rates Tab for Both Tr-1 and Tr-3,
                          if new Transaction created with Pipeline accrual as base Transaction then also application will call this procedure. 
  **********************************************************************************************************
  ** Change History
  **********************************************************************************************************
  ** VER   Date      Ticket No         Author          Description 
  ** ---   --------  ---------        -------------    -----------------------------
  ** 1    11/08/2016  GAL-8283        @Paul             Temp table Changes.
  ** 2	  08/07/2017  GAL-12264       @Kishore Kumar	MI003 - Unqualified column name
  ** 3    15/12/2017  GAL-12908       @Ajay             Contract Terms methodology null values handling
  ** 4    28-12-2017  GAL-12267       @AjayNaidu        BP014 - [NOT] NULL option
  ** 5    08-01-2018  GAL-12270       @AjayNaidu        EI025 PE001 PE010 ST008 MI005 MI002 Error codes
  ** 6    02-02-2018  GAL-13121       @AjayNaidu        rate basis Accrual rate projection aggregation change
  ** 7    21-08-2018  ALG-5513        @Vishal           Projection Sales Column is not present in NM_PPA_PROJECTION table and 
                                                        "NM_PPA_PROJECTION" table is not getting used in Allergan. So commented that lines
                                                        which are related to "NM_PPA_PROJECTION".
  *********************************************************************************************************/
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          -- Variables Initialization starts here
          DECLARE @PERIOD_SID                     INT,
                  @FORECAST_NAME_GTS_CUST_PROD    VARCHAR(50),
                  -- ACTIVE FILE NAME IN FILE MANAGEMENT      
                  @FORECAST_VERSION_GTS_CUST_PROD VARCHAR(50),
                  @FORECAST_NAME_DEMAND           VARCHAR(50),
                  @FORECAST_VERSION_DEMAND        VARCHAR(50),
                  @FORECAST_NAME_ADJ_DEMAND       VARCHAR(50),
                  @FORECAST_VERSION_ADJ_DEMAND    VARCHAR(50),
                  @FORECAST_NAME_EXFACTORY        VARCHAR(50),
                  @FORECAST_VERSION_EXFACTORY     VARCHAR(50),
                  @FORECAST_NAME_INVENTORY        VARCHAR(50),
                  @FORECAST_VERSION_INVENTORY     VARCHAR(50),
                  @STRING                         CHAR(1),
                  @BUISNESS_UNIT                  INT,
                  @GL_COMP_COMPANY                INT,
                  @ADJUSTMENT_TYPE                INT,
                 -- @INVENTORY_DETAILS_SID          DATE,
                  @SQL                            NVARCHAR(MAX)

          -- Variables Initialization ends here
----------------Creating all necessray temp tables(all ddl statements) for the logic Starts here (PE010 CodeGuarderror)
          IF Object_id('TEMPDB..#TEMP_PERIOD') IS NOT NULL
            DROP TABLE #TEMP_PERIOD

          CREATE TABLE #TEMP_PERIOD
            (
               PERIOD_SID    INT NOT NULL,
               PERIODS       VARCHAR(50) NOT NULL,
               PERIODS_MONTH VARCHAR(50) NOT NULL,
               PERIOD_DATE   DATETIME NOT NULL,
               MONTH         INT NOT NULL,
               YEAR          INT NOT NULL
            )

            IF Object_id('TEMPDB..#TEMP_ARM_PROJ_DETAILS') IS NOT NULL
            DROP TABLE #TEMP_ARM_PROJ_DETAILS

          CREATE TABLE #TEMP_ARM_PROJ_DETAILS
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               PROJECTION_MASTER_SID      INT NOT NULL,
               CCP_DETAILS_SID            INT NOT NULL,
               RS_MODEL_SID               INT NOT NULL,
               CONTRACT_MASTER_SID        INT NOT NULL,
               COMPANY_MASTER_SID         INT NOT NULL,
               ITEM_MASTER_SID            INT NOT NULL,
               PERIOD_SID                 INT NOT NULL,
               METHODOLGY                 VARCHAR(50) NOT NULL
            )

		    IF Object_id('TEMPDB..#TEMP_ARM_PROJ_ITEM') IS NOT NULL
            DROP TABLE #TEMP_ARM_PROJ_ITEM

          CREATE TABLE #TEMP_ARM_PROJ_ITEM
            (
               PROJECTION_MASTER_SID INT NOT NULL,
               ITEM_MASTER_SID       INT NOT NULL,
               ITEM_ID               VARCHAR(50) NOT NULL
               PRIMARY KEY ( ITEM_ID, ITEM_MASTER_SID, PROJECTION_MASTER_SID )
            )

		  IF Object_id('TEMPDB..#TEMP_ARM_APPRVD_PROJ') IS NOT NULL
            DROP TABLE #TEMP_ARM_APPRVD_PROJ

          CREATE TABLE #TEMP_ARM_APPRVD_PROJ
            (
               ARM_ADJUSTMENT_DETAILS_SID   INT NOT NULL,
               APPRVD_PROJECTION_MASTER_SID INT NOT NULL,
               APPRVD_ARP_PROJ_DETAILS_SID  INT NOT NULL,
               CCP_DETAILS_SID              INT NOT NULL,
               RS_CONTRACT_SID              INT NOT NULL,
			   RS_MODEL_SID                 INT NOT NULL,
               PRIMARY KEY ( APPRVD_ARP_PROJ_DETAILS_SID, CCP_DETAILS_SID,RS_CONTRACT_SID, RS_MODEL_SID )
            )

		          IF Object_id('TEMPDB..#TEMP_ARP_RATE') IS NOT NULL
            DROP TABLE #TEMP_ARP_RATE

          CREATE TABLE #TEMP_ARP_RATE
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               CCP_DETAILS_SID            INT NOT NULL,
               RS_MODEL_SID               INT NOT NULL,
			   RS_CONTRACT_SID            INT NOT NULL,
               RATE                       NUMERIC(22, 6) NULL,
               PRIMARY KEY ( ARM_ADJUSTMENT_DETAILS_SID, CCP_DETAILS_SID,RS_CONTRACT_SID, RS_MODEL_SID )
            )

          IF Object_id('TEMPDB..#TEMP_FORECAST') IS NOT NULL
            DROP TABLE #TEMP_FORECAST

          CREATE TABLE #TEMP_FORECAST
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               AMOUNT                     NUMERIC(22, 6) NULL
            )

          IF Object_id('TEMPDB..#TEMP_FILE_SALES') IS NOT NULL
            DROP TABLE #TEMP_FILE_SALES

          CREATE TABLE #TEMP_FILE_SALES
            (
               ITEM_MASTER_SID INT NOT NULL,
               AMOUNT          NUMERIC(22, 6)  NULL
            )

		 IF Object_id('TEMPDB..#CT_RESULT') IS NOT NULL
                  DROP TABLE #CT_RESULT

                CREATE TABLE #CT_RESULT
                  (
                     ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
                     RS_MODEL_SID               INT NOT NULL,
                     PRICE_GROUP_TYPE           VARCHAR(50) NULL,
                     PERIOD_SID                 INT NOT NULL,
                     PROJECTION_AMOUNT          NUMERIC(22, 6) NULL,
                     PROJECTION_RATE            NUMERIC(22, 6) NULL,
                     PROJECTION_REBATE_PER_UNIT NUMERIC(22, 6) NULL,
                     NET_CALCULATED_SALES       NUMERIC(22, 6) NULL,
                     NET_UNITS                  NUMERIC(22, 6) NULL,
                     SALES_PROJECTED_VALUE      NUMERIC(22, 6) NULL,
                     CALCULATION_TYPE           VARCHAR(50) NULL
                     PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID)
                  )
            
			          IF Object_id('TEMPDB..#TEMP_OUTPUT') IS NOT NULL
            DROP TABLE #TEMP_OUTPUT

          CREATE TABLE #TEMP_OUTPUT
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               PERIOD_SID                 INT NOT NULL,
               RATE                       NUMERIC(22, 6) NULL,
               SALES_AMOUNT               NUMERIC(22, 6) NULL,
               DISCOUNT_AMOUNT            NUMERIC(22, 6) NULL,
               CURRENT_PIPELINE_ACCRUAL   NUMERIC(22, 6) NULL,
               PROJECTED_PIPELINE_ACCRUAL NUMERIC(22, 6) NULL,
               PIPELINE_RATIO             NUMERIC(22, 6) NULL,
               VARIANCE                   NUMERIC(22, 6) NULL
			   PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID)
            )

          IF Object_id('TEMPDB..#TEMP_AMOUNT') IS NOT NULL
            DROP TABLE #TEMP_AMOUNT

          CREATE TABLE #TEMP_AMOUNT
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               PERIOD_SID                 INT NOT NULL,
               ACCRUAL_AMOUNT             NUMERIC(22, 6) NULL,
               INDICATOR                  BIT NULL
            )

          IF Object_id('TEMPDB..#CURRENT_LIABILITY') IS NOT NULL
            DROP TABLE #CURRENT_LIABILITY

          CREATE TABLE #CURRENT_LIABILITY
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               PERIOD_SID                 INT NOT NULL, 
               CREDIT_AMOUNT              NUMERIC(22, 6) NULL,
               DEBIT_AMOUNT               NUMERIC(22, 6) NULL,
               CURRENT_AMOUNT             NUMERIC(22, 6) NULL
            )

          IF Object_id('TEMPDB..#CURRENT_EXPENSE') IS NOT NULL
            DROP TABLE #CURRENT_EXPENSE

          CREATE TABLE #CURRENT_EXPENSE
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               PERIOD_SID                 INT NOT NULL,
               CREDIT_AMOUNT              NUMERIC(22, 6) NULL,
               DEBIT_AMOUNT               NUMERIC(22, 6) NULL,
               CURRENT_AMOUNT             NUMERIC(22, 6) NULL
            )


          --Taking base Transaction for Particular Projection	starts here
          SELECT @MODULE = C.DESCRIPTION,
                 @ADJUSTMENT_TYPE = A.TRANSACTION_TYPE
          FROM   ARM_ADJUSTMENT_MASTER A
                 JOIN ARM_ADJUSTMENT_CONFIG B
                   ON A.TRANSACTION_TYPE = B.ARM_ADJUSTMENT_CONFIG_SID
                 JOIN HELPER_TABLE C
                   ON C.HELPER_TABLE_SID = B.METHODOLGY
          WHERE  A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          --Taking base Transaction for Particular Projection	ends here
          DECLARE @PIPELINE_SALES_TABLE VARCHAR(200) = Concat('ST_ARM_PIPELINE_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @PIPELINE_RATE_TABLE  VARCHAR(200) = Concat('ST_ARM_PIPELINE_RATE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @INVENTORY_RATE_TABLE VARCHAR(200) = Concat('ST_ARM_INVENTORY_RATE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @INVENTORY_TABLE      VARCHAR(200) = Concat('ST_ARM_INVENTORY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ARM_CURRENT_BALANCE  VARCHAR(200) = Concat('ST_ARM_CURRENT_BALANCE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ARM_ADJUSTMENTS      VARCHAR(200) = Concat('ST_ARM_ADJUSTMENTS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

          --taking price related period selected in data selection and assigning GL and BU to variable starts here 
          SELECT @PERIOD_SID = P.PERIOD_SID
          FROM   PROJECTION_MASTER A
                 JOIN PERIOD P
                   ON CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A.FROM_DATE, 0)))) = P.PERIOD_DATE
          WHERE A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          SET @STRING=LEFT(@RATE_FREQUENCY, 1)

          SELECT @BUISNESS_UNIT = BU_COMPANY_MASTER_SID
          FROM   ARM_ADJUSTMENT_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          SELECT @GL_COMP_COMPANY = COMPANY_MASTER_SID
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          INSERT INTO #TEMP_PERIOD
                      (PERIOD_SID,
                       PERIODS,
                       PERIODS_MONTH,
                       PERIOD_DATE,
                       MONTH,
                       YEAR)
          SELECT PERIOD_SID,
                 CASE
                   WHEN @STRING = 'M' THEN Concat (LEFT(Datename(MM, PERIOD_DATE), 3), ' ', YEAR)
                   WHEN @STRING = 'Q' THEN Concat ('Q', QUARTER, ' ', YEAR)
                   WHEN @STRING = 'S' THEN Concat ('S', SEMI_ANNUAL, ' ', YEAR)
                   ELSE Cast(YEAR AS CHAR(4))
                 END                                                    AS PERIODS,
                 Concat (LEFT(Datename(MM, PERIOD_DATE), 3), ' ', YEAR) AS PERIODS_MONTH,
                 PERIOD_DATE,
                 MONTH,
                 YEAR
          FROM   PERIOD

          --SELECT @INVENTORY_DETAILS_SID = Eomonth(PERIOD_DATE)
          --FROM   #TEMP_PERIOD
          --WHERE  PERIODS_MONTH = @INVENTORY_DETAILS

          --taking price related period selected in data selection and assigning GL and BU to variable Ends here
          --taking active file information starts here
          DECLARE @FILE_VER AS TABLE
            (
               FILE_TYPE     VARCHAR(100) NOT NULL,
               FORECAST_NAME VARCHAR(100) NULL,
               VERSION       VARCHAR(15) NULL,
			   PRIMARY KEY(FILE_TYPE)
            )

          INSERT INTO @FILE_VER
                      (FILE_TYPE,
                       FORECAST_NAME,
                       VERSION)
          SELECT FILE_TYPE,
                 FORECAST_NAME,
                 VERSION
          FROM   (SELECT FT.FORECAST_NAME,
                         FT.[VERSION],
                         FT.FILE_MANAGEMENT_SID,
                         HT.[DESCRIPTION]                        AS FILE_TYPE,
                         Row_number()
                           OVER (
                             PARTITION BY FT.FILE_TYPE
                             ORDER BY FT.FILE_MANAGEMENT_SID DESC ) AS RN
                  FROM   FILE_MANAGEMENT FT
                         INNER JOIN HELPER_TABLE HT
                                 ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
                  WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                           AND FT.FROM_PERIOD IS NOT NULL )
                         AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                                OR FT.TO_PERIOD IS NULL )
                         AND HT.LIST_NAME = 'FILE_TYPE'
                         AND HT.[DESCRIPTION] IN ( 'DEMAND', 'CUSTOMER SALES', 'ADJUSTED DEMAND', 'EX-FACTORY SALES', 'INVENTORY WITHDRAWAL - FORECAST SUMMARY' )
                         AND FT.BUSINESS_UNIT = @BUISNESS_UNIT
                         AND FT.COMPANY = @GL_COMP_COMPANY) A
          WHERE  RN = 1

          SELECT @FORECAST_NAME_GTS_CUST_PROD = FORECAST_NAME,
                 @FORECAST_VERSION_GTS_CUST_PROD = [VERSION]
          FROM   @FILE_VER
          WHERE  FILE_TYPE = 'CUSTOMER SALES'

          SELECT @FORECAST_NAME_DEMAND = FORECAST_NAME,
                 @FORECAST_VERSION_DEMAND = [VERSION]
          FROM   @FILE_VER
          WHERE  FILE_TYPE = 'DEMAND'

          SELECT @FORECAST_NAME_ADJ_DEMAND = FORECAST_NAME,
                 @FORECAST_VERSION_ADJ_DEMAND = [VERSION]
          FROM   @FILE_VER
          WHERE  FILE_TYPE = 'ADJUSTED DEMAND'

          SELECT @FORECAST_NAME_EXFACTORY = FORECAST_NAME,
                 @FORECAST_VERSION_EXFACTORY = [VERSION]
          FROM   @FILE_VER
          WHERE  FILE_TYPE = 'EX-FACTORY SALES'

          SELECT @FORECAST_NAME_INVENTORY = FORECAST_NAME,
                 @FORECAST_VERSION_INVENTORY = [VERSION]
          FROM   @FILE_VER
          WHERE  FILE_TYPE = 'INVENTORY WITHDRAWAL - FORECAST SUMMARY'

          --taking active file information ends here
          -- Pulling CCP+D Combination for Current projection starts here
          INSERT INTO #TEMP_ARM_PROJ_DETAILS
                      (ARM_ADJUSTMENT_DETAILS_SID,
                       PROJECTION_MASTER_SID,
                       CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID,
                       PERIOD_SID,
                       METHODOLGY)
          SELECT AD.ARM_ADJUSTMENT_DETAILS_SID,
                 AD.PROJECTION_MASTER_SID,
                 AD.CCP_DETAILS_SID,
                 AD.RS_MODEL_SID,
                 CD.CONTRACT_MASTER_SID,
                 CD.COMPANY_MASTER_SID,
                 CD.ITEM_MASTER_SID,
                 @PERIOD_SID AS PERIOD_SID,
                 HT.DESCRIPTION
          FROM   ARM_ADJUSTMENT_DETAILS AD
                 JOIN CCP_DETAILS CD
                   ON AD.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                 JOIN ARM_ADJUSTMENT_MASTER AM
                   ON AM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                 JOIN ARM_ADJUSTMENT_CONFIG AC
                   ON AC.ARM_ADJUSTMENT_CONFIG_SID = AM.TRANSACTION_TYPE
                 JOIN HELPER_TABLE HT
                   ON HT.HELPER_TABLE_SID = AC.METHODOLGY
          WHERE  AD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          -- Pulling CCP+D Combination for Current projection ends here
          --Pulling product related information for current projection starts here
          INSERT INTO #TEMP_ARM_PROJ_ITEM
                      (ITEM_ID,
                       ITEM_MASTER_SID,
                       PROJECTION_MASTER_SID)
          SELECT DISTINCT IM.ITEM_ID,
                          B.ITEM_MASTER_SID,
                          @PROJECTION_MASTER_SID
          FROM   #TEMP_ARM_PROJ_DETAILS A
                 JOIN CCP_DETAILS B
                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                 JOIN ITEM_MASTER IM
                   ON IM.ITEM_MASTER_SID = B.ITEM_MASTER_SID

          --Pulling product related information for current projection ends here
          --Pulling Rates based on Rate basis selected in Data Selection starts here
          IF ( @RATE_BASIS = 'ACCRUAL RATE PROJECTION' )
            BEGIN
                --if Selected Rate basis= 'ACCRUAL RATE PROJECTION' we are taking Latest Approved CCP+D Combination and pulling rate starts here
                INSERT INTO #TEMP_ARM_APPRVD_PROJ
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             APPRVD_PROJECTION_MASTER_SID,
                             APPRVD_ARP_PROJ_DETAILS_SID,
                             CCP_DETAILS_SID,
                             RS_MODEL_SID
							 ,RS_CONTRACT_SID)
                SELECT ARM_ADJUSTMENT_DETAILS_SID,
                       PROJECTION_MASTER_SID,
                       ACCRUAL_PROJ_DETAILS_SID,
                       CCP_DETAILS_SID,
                       RS_MODEL_SID,
					   RS_CONTRACT_SID
                FROM   (SELECT Row_number()
                                 OVER (
                                   PARTITION BY C.CCP_DETAILS_SID, C.RS_MODEL_SID,ADS.RS_CONTRACT_SID
                                   ORDER BY A.APPROVED_DATE DESC ) AS RN,
                               C.ARM_ADJUSTMENT_DETAILS_SID,
                               B.ACCRUAL_PROJ_DETAILS_SID,
                               B.CCP_DETAILS_SID,
                               B.RS_MODEL_SID,
                               B.PROJECTION_MASTER_SID
							   ,ADS.RS_CONTRACT_SID
                        FROM   WORKFLOW_MASTER A
                               JOIN ACCRUAL_PROJ_DETAILS B
                                 ON A.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID
                               JOIN #TEMP_ARM_PROJ_DETAILS C
                                 ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                    AND C.RS_MODEL_SID = B.RS_MODEL_SID
								JOIN RS_CONTRACT R 
                                    ON R.RS_MODEL_SID = C.RS_MODEL_SID 
                                    AND R.CONTRACT_MASTER_SID = C.CONTRACT_MASTER_SID 
									AND R.RS_CONTRACT_SID=B.RS_CONTRACT_SID
								JOIN ARM_DEDUCTION_SELECTION ADS 
                                    ON ADS.RS_CONTRACT_SID=R.RS_CONTRACT_SID
									AND ADS.PROJECTION_MASTER_SID=@PROJECTION_MASTER_SID
                        WHERE  A.WORKFLOW_ID LIKE 'AR%'
                               AND EXISTS (SELECT H1.HELPER_TABLE_SID
                                           FROM   HELPER_TABLE H1
                                           WHERE  H1.LIST_NAME = 'WORKFLOWSTATUS'
                                                  AND H1.DESCRIPTION = 'APPROVED'
                                                  AND H1.HELPER_TABLE_SID = A.WORKFLOW_STATUS_ID))A
                --AND WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID 
                --                           FROM   HELPER_TABLE 
                --                           WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                --                                  AND DESCRIPTION = 'APPROVED')) A
                WHERE  RN = 1

                INSERT INTO #TEMP_ARP_RATE
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             CCP_DETAILS_SID,
							 RS_CONTRACT_SID,
                             RS_MODEL_SID,
                             RATE)
                SELECT A.ARM_ADJUSTMENT_DETAILS_SID,
                       A.CCP_DETAILS_SID,
					   A.RS_CONTRACT_SID,
                       A.RS_MODEL_SID,
                       AVG(B.ACCRUAL_RATE) AS RATE
                FROM   #TEMP_ARM_APPRVD_PROJ A
                       JOIN ACCRUAL_RATE_DETAILS B
                         ON A.APPRVD_ARP_PROJ_DETAILS_SID = B.ACCRUAL_PROJ_DETAILS_SID
                       JOIN #TEMP_PERIOD P
                         ON P.PERIOD_SID = B.PERIOD_SID
                            AND P.PERIODS = @RATE_PERIOD
                GROUP  BY A.ARM_ADJUSTMENT_DETAILS_SID,
                          A.CCP_DETAILS_SID,
						  A.RS_CONTRACT_SID,
                          A.RS_MODEL_SID
            END
          --if Selected Rate basis= 'ACCRUAL RATE PROJECTION' we are taking Latest Approved CCP+D Combination and pulling rate ends here
          --if Selected Rate basis= 'Contract Terms' we are taking Latest Approved CCP+D Combination and pulling rate from ciontract setup
          ELSE IF ( @RATE_BASIS = 'Contract Terms' )
            BEGIN
                INSERT INTO #CT_RESULT
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             PROJECTION_AMOUNT,
                             PROJECTION_RATE,
                             PROJECTION_REBATE_PER_UNIT,
                             NET_CALCULATED_SALES,
                             NET_UNITS,
                             SALES_PROJECTED_VALUE,
                             CALCULATION_TYPE)
                EXEC [dbo].Prc_arm_contract_details_setup
                  @PROJECTION_MASTER_SID,
                  @USER_ID,
                  @SESSION_ID
            END
          ELSE
            --if selected Rate basis is other than ACCRUAL RATE PROJECTION we are taking latest approved CCP+D and taking amount starts here
            BEGIN
                DECLARE @TEMP_PROJ_DETAILS TABLE
                  (
                     ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
                     PROJECTION_MASTER_SID      INT NOT NULL,
                     PROJECTION_DETAILS_SID     INT NOT NULL,
                     CCP_DETAILS_SID            INT NOT NULL,
                     FORECASTING_TYPE           VARCHAR(50) NOT NULL,
                     RS_MODEL_SID               INT NOT NULL
                  )

                INSERT INTO @TEMP_PROJ_DETAILS
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             PROJECTION_MASTER_SID,
                             PROJECTION_DETAILS_SID,
                             CCP_DETAILS_SID,
                             FORECASTING_TYPE,
                             RS_MODEL_SID)
                SELECT ARM_ADJUSTMENT_DETAILS_SID,
                       PROJECTION_MASTER_SID,
                       PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       FORECASTING_TYPE,
                       RS_MODEL_SID
                FROM   (SELECT OUT_P.ARM_ADJUSTMENT_DETAILS_SID,
                               WM.PROJECTION_MASTER_SID,
                               OUT_P.PROJECTION_DETAILS_SID,
                               OUT_P.CCP_DETAILS_SID,
                               OUT_P.RS_MODEL_SID,
                               OUT_P.FORECASTING_TYPE,
                               Row_number()
                                 OVER (
                                   PARTITION BY OUT_P.ARM_ADJUSTMENT_DETAILS_SID
                                   ORDER BY WM.APPROVED_DATE DESC ) RNO
                        FROM   WORKFLOW_MASTER WM
                               INNER JOIN (SELECT TMP.ARM_ADJUSTMENT_DETAILS_SID,
                                                  PM.PROJECTION_MASTER_SID,
                                                  PD.PROJECTION_DETAILS_SID,
                                                  PD.CCP_DETAILS_SID,
                                                  TMP.RS_MODEL_SID,
                                                  PM.FORECASTING_TYPE
                                           FROM   #TEMP_ARM_PROJ_DETAILS TMP
                                                  INNER JOIN PROJECTION_DETAILS PD
                                                          ON PD.CCP_DETAILS_SID = TMP.CCP_DETAILS_SID
                                                  INNER JOIN PROJECTION_MASTER PM
                                                          ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                                  LEFT JOIN NM_DISCOUNT_PROJ_MASTER NM
                                                         ON PD.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                                            AND NM.RS_MODEL_SID = TMP.RS_MODEL_SID) OUT_P
                                       ON WM.PROJECTION_MASTER_SID = OUT_P.PROJECTION_MASTER_SID
                        WHERE  EXISTS (SELECT H1.HELPER_TABLE_SID
                                       FROM   HELPER_TABLE H1
                                       WHERE  H1.LIST_NAME = 'WORKFLOWSTATUS'
                                              AND H1.DESCRIPTION = 'APPROVED'
                                              AND H1.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID))A
                --WHERE  WM.WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID 
                --                                FROM   HELPER_TABLE 
                --                                WHERE  LIST_NAME = 'WORKFLOWSTATUS' 
                --                                       AND DESCRIPTION = 'APPROVED')) A
                WHERE  RNO = 1
                ORDER  BY ARM_ADJUSTMENT_DETAILS_SID

                INSERT INTO #TEMP_FORECAST
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             AMOUNT)
                SELECT ARM_ADJUSTMENT_DETAILS_SID,
                       Sum(AMOUNT) AS AMOUNT
                FROM   (SELECT T.ARM_ADJUSTMENT_DETAILS_SID,
                               NM.PERIOD_SID,
                               ( NM.PROJECTION_SALES ) AS AMOUNT
                        FROM   [DBO].[NM_DISCOUNT_PROJECTION] NM
                               INNER JOIN @TEMP_PROJ_DETAILS T
                                       ON T.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                          AND NM.RS_MODEL_SID = T.RS_MODEL_SID
                               JOIN #TEMP_PERIOD P
                                 ON P.PERIOD_SID = NM.PERIOD_SID
                                    AND P.PERIODS = @RATE_PERIOD
                        WHERE  T.FORECASTING_TYPE = 'NON MANDATED'
--                         UNION ALL
--                         SELECT T.ARM_ADJUSTMENT_DETAILS_SID,
--                                NM.PERIOD_SID,
--                                ( NM.PROJECTION_SALES ) AS AMOUNT
--                         FROM   [DBO].[NM_PPA_PROJECTION] NM
--                                INNER JOIN @TEMP_PROJ_DETAILS T
--                                        ON T.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
--                                           AND NM.RS_MODEL_SID = T.RS_MODEL_SID
--                                JOIN #TEMP_PERIOD P
--                                  ON P.PERIOD_SID = NM.PERIOD_SID
--                                     AND P.PERIODS = @RATE_PERIOD
--                         WHERE  T.FORECASTING_TYPE = 'NON MANDATED'
                        UNION ALL
                        SELECT T.ARM_ADJUSTMENT_DETAILS_SID,
                               M.PERIOD_SID,
                               ( Isnull(M.PROJECTION_SALES, 0)
                                 + Isnull(MS.PROJECTION_SALES, 0) ) AS AMOUNT
                        FROM   @TEMP_PROJ_DETAILS T
                               INNER JOIN [DBO].[M_DISCOUNT_PROJECTION] M
                                       ON T.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID
                                          AND M.SAVE_FLAG = 1
                               JOIN #TEMP_PERIOD P
                                 ON P.PERIOD_SID = M.PERIOD_SID
                                    AND P.PERIODS = @RATE_PERIOD
                               LEFT JOIN M_SUPPLEMENTAL_DISC_PROJ MS
                                      ON MS.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID
                                         AND MS.PERIOD_SID = M.PERIOD_SID
                        WHERE  T.FORECASTING_TYPE = 'MANDATED') A
                GROUP  BY ARM_ADJUSTMENT_DETAILS_SID

                --if Rate basis is ADJUSTED DEMAND taking amount from active file (based on items selected in Data Selection) starts here
                IF ( @RATE_BASIS = 'ADJUSTED DEMAND' )
                  BEGIN
                      INSERT INTO #TEMP_FILE_SALES
                                  (ITEM_MASTER_SID,
                                   AMOUNT)
                      SELECT A.ITEM_MASTER_SID,
                             Sum(A.TOTAL_DEMAND_AMOUNT) AS SALES
                      FROM   ADJUSTED_DEMAND_FORECAST A
                             JOIN #TEMP_ARM_PROJ_ITEM B
                               ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                  AND A.FORECAST_NAME = @FORECAST_NAME_ADJ_DEMAND
                                  AND A.FORECAST_VER = @FORECAST_VERSION_ADJ_DEMAND
                             JOIN #TEMP_PERIOD P
                               ON P.PERIODS = @RATE_PERIOD
                                  AND P.MONTH = A.MONTH
                                  AND P.YEAR = A.YEAR
                      GROUP  BY A.ITEM_MASTER_SID
                  END
                --if Rate basis is ADJUSTED DEMAND taking amount from active file (based on items selected in Data Selection) ends here
                --if Rate basis is  DEMAND taking amount from active file (based on items selected in Data Selection) starts here
                ELSE IF ( @RATE_BASIS = 'DEMAND' )
                  BEGIN
                      INSERT INTO #TEMP_FILE_SALES
                                  (ITEM_MASTER_SID,
                                   AMOUNT)
                      SELECT A.ITEM_MASTER_SID,
                             Sum(A.TOTAL_DEMAND_AMOUNT) AS SALES
                      FROM   DEMAND_FORECAST A
                             JOIN #TEMP_ARM_PROJ_ITEM B
                               ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                  AND A.FORECAST_NAME = @FORECAST_NAME_DEMAND
                                  AND A.FORECAST_VER = @FORECAST_VERSION_DEMAND
                             JOIN #TEMP_PERIOD P
                               ON P.PERIODS = @RATE_PERIOD
                                  AND P.MONTH = A.FORECAST_MONTH
                                  AND P.YEAR = A.FORECAST_YEAR
                      GROUP  BY A.ITEM_MASTER_SID
                  END
                --if Rate basis is  DEMAND taking amount from active file (based on items selected in Data Selection) ends here
                --if Rate basis is  CUSTOMER GROSS TRADE SALES taking amount from active file (based on items selected in Data Selection) starts here
                ELSE IF ( @RATE_BASIS = 'CUSTOMER GROSS TRADE SALES' )
                  BEGIN
                      INSERT INTO #TEMP_FILE_SALES
                                  (ITEM_MASTER_SID,
                                   AMOUNT)
                      SELECT A.ITEM_MASTER_SID,
                             Sum(A.SALES_AMOUNT) AS SALES
                      FROM   CUSTOMER_GTS_FORECAST A
                             JOIN #TEMP_ARM_PROJ_ITEM B
                               ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                  AND A.FORECAST_NAME = @FORECAST_NAME_GTS_CUST_PROD
                                  AND A.FORECAST_VER = @FORECAST_VERSION_GTS_CUST_PROD
                             JOIN #TEMP_PERIOD P
                               ON P.PERIODS = @RATE_PERIOD
                                  AND P.MONTH = A.FORECAST_MONTH
                                  AND P.YEAR = A.FORECAST_YEAR
                      GROUP  BY A.ITEM_MASTER_SID
                  END
                --if Rate basis is  CUSTOMER GROSS TRADE SALES taking amount from active file (based on items selected in Data Selection) starts here
                --if Rate basis is  PRODUCT GROSS TRADE SALES taking amount from active file (based on items selected in Data Selection) starts here
                ELSE IF ( @RATE_BASIS = 'PRODUCT GROSS TRADE SALES' )
                  BEGIN
                      INSERT INTO #TEMP_FILE_SALES
                                  (ITEM_MASTER_SID,
                                   AMOUNT)
                      SELECT B.ITEM_MASTER_SID,
                             Sum(A.DOLLARS) AS SALES
                      FROM   FORECASTING_MASTER A
                             JOIN #TEMP_ARM_PROJ_ITEM B
                               ON A.NDC = B.ITEM_ID
                                  AND A.FORECAST_NAME = @FORECAST_NAME_EXFACTORY
                                  AND A.FORECAST_VER = @FORECAST_VERSION_EXFACTORY
                             JOIN #TEMP_PERIOD P
                               ON P.PERIODS = @RATE_PERIOD
                                  AND P.MONTH = A.FORECAST_MONTH
                                  AND P.YEAR = A.FORECAST_YEAR
                      GROUP  BY B.ITEM_MASTER_SID
                  END
                --if Rate basis is  PRODUCT GROSS TRADE SALES taking amount from active file (based on items selected in Data Selection) ends here
                ELSE
                  BEGIN
                      --if Rate basis is  INVENTORY Withdrawl taking amount from active file (based on items selected in Data Selection) starts here
                      INSERT INTO #TEMP_FILE_SALES
                                  (ITEM_MASTER_SID,
                                   AMOUNT)
                      SELECT A.ITEM_MASTER_SID,
                             Sum(A.AMOUNT_WITHDRAWN) AS SALES
                      FROM   INVENTORY_WD_PROJ_MAS A
                             JOIN #TEMP_ARM_PROJ_ITEM B
                               ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                  AND A.FORECAST_NAME = @FORECAST_NAME_INVENTORY
                                  AND A.FORECAST_VER = @FORECAST_VERSION_INVENTORY
                             JOIN #TEMP_PERIOD P
                               ON P.PERIODS = @RATE_PERIOD
                                  AND P.MONTH = A.MONTH
                                  AND P.YEAR = A.YEAR
                      GROUP  BY A.ITEM_MASTER_SID
                  END
            --if Rate basis is  INVENTORY Withdrawl taking amount from active file (based on items selected in Data Selection) ends here
            END

          --if selected Rate basis is other than ACCRUAL RATE PROJECTION we are taking latest approved CCP+D and taking amount ends here
          --for BSR(Balnce Summary Report)	Calculation We Calculated the Liability and Expense Amount Separately for each Account Starts here


          SET @SQL=''

          IF( @MODULE = 'Transaction 1 - Pipeline Accrual' )
            BEGIN
                --Insertion into main table starts here for both Rate and Adjustmemnt Summary Screens without BSR Chnages
                SET @SQL=Concat('INSERT INTO  #TEMP_OUTPUT
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             RATE, 
                             SALES_AMOUNT, 
                             DISCOUNT_AMOUNT, 
                             CURRENT_PIPELINE_ACCRUAL, 
                             PROJECTED_PIPELINE_ACCRUAL, 
                             VARIANCE)
					   SELECT A.ARM_ADJUSTMENT_DETAILS_SID, 
                       A.PERIOD_SID, 
                       RATE = CASE 
                                WHEN @RATE_BASIS = ''ACCRUAL RATE PROJECTION'' THEN ISNULL(ARP.RATE, 0) * 100
                                ELSE COALESCE(ISNULL(TF.AMOUNT, 0) / NULLIF(TFS.AMOUNT, 0), 0) * 100
                              END, 
                       TFS.AMOUNT AS SALES_AMOUNT, 
                       TF.AMOUNT  AS DISCOUNT_AMOUNT, 
                       CURRENT_PIPELINE_ACCRUAL = ISNULL(B.CURRENT_BALANCE, 0), 
                       PROJECTED_PIPELINE_ACCRUAL = ( CASE 
                                                        WHEN @RATE_BASIS = ''ACCRUAL RATE PROJECTION'' THEN ISNULL(ARP.RATE, 0)
                                                        ELSE COALESCE(ISNULL(TF.AMOUNT, 0) / NULLIF(TFS.AMOUNT, 0), 0)
                                                      END ) * ISNULL(APS.NET_CALCULATED_SALES, 0),
                       VARIANCE = (( ISNULL(B.CURRENT_BALANCE, 0) - ( ( CASE 
                                                                         WHEN @RATE_BASIS = ''ACCRUAL RATE PROJECTION'' THEN ISNULL(ARP.RATE, 0)
                                                                         ELSE COALESCE(ISNULL(TF.AMOUNT, 0) / NULLIF(TFS.AMOUNT, 0), 0)
                                                                       END ) * ISNULL(APS.NET_CALCULATED_SALES, 0) ) ))
                FROM   #TEMP_ARM_PROJ_DETAILS A 
                       LEFT JOIN ', @ARM_CURRENT_BALANCE, ' B 
                               ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
					             AND A.RS_MODEL_SID = B.RS_MODEL_SID
                                 AND A.PERIOD_SID = B.PERIOD_SID 
                       LEFT JOIN #TEMP_ARP_RATE ARP 
                              ON ARP.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID
                       LEFT JOIN #TEMP_FORECAST TF 
                              ON A.ARM_ADJUSTMENT_DETAILS_SID = TF.ARM_ADJUSTMENT_DETAILS_SID
                       LEFT JOIN #TEMP_FILE_SALES TFS 
                              ON TFS.ITEM_MASTER_SID = A.ITEM_MASTER_SID 
                       LEFT JOIN ', @PIPELINE_SALES_TABLE, ' APS 
                              ON APS.ITEM_MASTER_SID = A.ITEM_MASTER_SID 
                                 AND APS.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID)

                EXEC Sp_executesql
                  @SQL,
                  N'@RATE_BASIS VARCHAR(50)',
                  @RATE_BASIS=@RATE_BASIS

                --Insertion into main table starts here for both Rate and Adjustmemnt Summary Screens without BSR Chnages      
                SET @SQL=''

                INSERT INTO #TEMP_AMOUNT
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             PERIOD_SID,
                             ACCRUAL_AMOUNT,
                             INDICATOR)
                SELECT ARM_ADJUSTMENT_DETAILS_SID,
                       PERIOD_SID,
                       Sum(VARIANCE) AS ACCRUAL_AMOUNT,
                       CASE
                         WHEN Sum(VARIANCE) > 0 THEN 0
                         WHEN Sum(VARIANCE) < 0 THEN 1
                         ELSE NULL
                       END           AS INDICATOR
                FROM   #TEMP_OUTPUT
                GROUP  BY ARM_ADJUSTMENT_DETAILS_SID,
                          PERIOD_SID

                SET @SQL=Concat('IF EXISTS (SELECT 1 FROM ', @ARM_ADJUSTMENTS, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
                                          ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
					                   BEGIN 
												TRUNCATE TABLE ', @ARM_ADJUSTMENTS, '  
									   END
				INSERT INTO ', @ARM_ADJUSTMENTS, '(ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             ADJUSTMENT_TYPE,
							 ACCOUNT, 
							  DEBIT,
                             CREDIT
							 )
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
					   TAM.PERIOD_SID AS PERIOD_SID,
					   ', @ADJUSTMENT_TYPE, ' AS ADJUSTMENT_TYPE,
					   AAC.ACCOUNT,
					   SUM(ABS(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT,
					   SUM(ABS(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT
				FROM   #TEMP_AMOUNT TAM 
					   LEFT JOIN  ARM_ADJ_RES_CCP AAC 
						 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
								  AND AAC.ADJUSTMENT_TYPE = ', @ADJUSTMENT_TYPE, ' 
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
						  ACCOUNT')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''

                INSERT INTO #CURRENT_LIABILITY
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             PERIOD_SID,
                             CREDIT_AMOUNT,
                             DEBIT_AMOUNT,
                             CURRENT_AMOUNT)
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
                       Isnull(A1.PERIOD_SID, A2.PERIOD_SID),
                       Sum(Abs(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT_AMOUNT,
                       Sum(Abs(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT_AMOUNT,
                       Isnull(Sum(Abs(A1.ACCRUAL_AMOUNT)), 0) - Isnull(Sum(Abs(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
                FROM   #TEMP_AMOUNT TAM
                       JOIN ARM_ADJ_RES_CCP AAC
                         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
                       INNER JOIN HELPER_TABLE HT
                               ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
                                  AND HT.DESCRIPTION = 'LIABILITY'
                                  AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
                       LEFT JOIN #TEMP_AMOUNT A1
                              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
                                 AND AAC.CREDIT = A1.INDICATOR
                       LEFT JOIN #TEMP_AMOUNT A2
                              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
                                 AND AAC.DEBIT = A2.INDICATOR
                       JOIN PERIOD P
                         ON P.PERIOD_SID = Isnull(A1.PERIOD_SID, A2.PERIOD_SID)
                            AND P.PERIOD_SID = TAM.PERIOD_SID
                WHERE  AAC.CREDIT IS NOT NULL
                       AND AAC.DEBIT IS NOT NULL
                GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
                          Isnull(A1.PERIOD_SID, A2.PERIOD_SID)

                INSERT INTO #CURRENT_EXPENSE
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             PERIOD_SID,
                             CREDIT_AMOUNT,
                             DEBIT_AMOUNT,
                             CURRENT_AMOUNT)
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
                       Isnull(A1.PERIOD_SID, A2.PERIOD_SID),
                       Sum(Abs(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT_AMOUNT,
                       Sum(Abs(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT_AMOUNT,
                       Isnull(Sum(Abs(A1.ACCRUAL_AMOUNT)), 0) - Isnull(Sum(Abs(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
                FROM   #TEMP_AMOUNT TAM
                       JOIN ARM_ADJ_RES_CCP AAC
                         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
                       INNER JOIN HELPER_TABLE HT
                               ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
                                  AND HT.DESCRIPTION = 'EXPENSE'
                                  AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
                       LEFT JOIN #TEMP_AMOUNT A1
                              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
                                 AND AAC.CREDIT = A1.INDICATOR
                       LEFT JOIN #TEMP_AMOUNT A2
                              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
                                 AND AAC.DEBIT = A2.INDICATOR
                       JOIN PERIOD P
                         ON P.PERIOD_SID = Isnull(A1.PERIOD_SID, A2.PERIOD_SID)
                            AND P.PERIOD_SID = TAM.PERIOD_SID
                WHERE  AAC.CREDIT IS NOT NULL
                       AND AAC.DEBIT IS NOT NULL
                GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
                          Isnull(A1.PERIOD_SID, A2.PERIOD_SID)

                --for BSR(Balnce Summary Report)	Calculation We Calculated the Liability and Expense Amount Separately for each Account ends here
                --Insertion into main table starts here for both Rate and Adjustmemnt Summary Screens with BSR Chnages     
                SET @SQL=Concat('IF EXISTS (SELECT 1 FROM ', @PIPELINE_RATE_TABLE, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
                                          ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
					                   BEGIN 
												TRUNCATE TABLE ', @PIPELINE_RATE_TABLE, '  
									   END
				INSERT INTO ', @PIPELINE_RATE_TABLE, '(ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             RATE, 
                             SALES_AMOUNT, 
                             DISCOUNT_AMOUNT, 
                             CURRENT_PIPELINE_ACCRUAL, 
                             PROJECTED_PIPELINE_ACCRUAL, 
                             VARIANCE,
							 LIABILITY_AMOUNT,
							 EXPENSE_AMOUNT
							 )
                SELECT A.ARM_ADJUSTMENT_DETAILS_SID, 
					   A.PERIOD_SID, 
					   A.RATE, 
					   a.SALES_AMOUNT, 
					   a.DISCOUNT_AMOUNT, 
					   A.CURRENT_PIPELINE_ACCRUAL, 
					   A.PROJECTED_PIPELINE_ACCRUAL, 
					   A.VARIANCE,
					   LIA.CURRENT_AMOUNT AS LIABILITY_AMOUNT, 
					   CEX.CURRENT_AMOUNT AS EXPENSE_AMOUNT 
				FROM   #TEMP_OUTPUT A 
					   LEFT JOIN #CURRENT_LIABILITY LIA 
							  ON A.ARM_ADJUSTMENT_DETAILS_SID = LIA.ARM_ADJUSTMENT_DETAILS_SID 
							  AND A.PERIOD_SID=LIA.PERIOD_SID
					   LEFT JOIN #CURRENT_EXPENSE CEX 
							  ON A.ARM_ADJUSTMENT_DETAILS_SID = CEX.ARM_ADJUSTMENT_DETAILS_SID
							  AND A.PERIOD_SID=CEX.PERIOD_SID')

                EXEC Sp_executesql
                  @SQL
            --Insertion into main table ends here for both Rate and Adjustmemnt Summary Screens with BSR Chnages  
            END
          ELSE
            BEGIN
                --for BSR(Balnce Summary Report) Tr-3	Calculation We Calculated the Liability and Expense Amount Separately for each Account Starts here
                IF ( @RATE_BASIS <> 'Contract Terms' )
                  BEGIN
                      SET @SQL=Concat('INSERT INTO  #TEMP_OUTPUT
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             RATE, 
                             SALES_AMOUNT, 
                             DISCOUNT_AMOUNT, 
                             CURRENT_PIPELINE_ACCRUAL, 
                             PROJECTED_PIPELINE_ACCRUAL, 
							 PIPELINE_RATIO,
                             VARIANCE)
					SELECT ARM_ADJUSTMENT_DETAILS_SID, 
                       PERIOD_SID, 
                       RATE, 
                       SALES_AMOUNT, 
                       DISCOUNT_AMOUNT, 
                       CURRENT_PIPELINE_ACCRUAL, 
                       PROJECTED_PIPELINE_ACCRUAL, 
                       COALESCE(CURRENT_PIPELINE_ACCRUAL / NULLIF(PROJECTED_PIPELINE_ACCRUAL, 0), 0) AS PIPELINE_RATIO,
                       VARIANCE
                FROM   (SELECT A.ARM_ADJUSTMENT_DETAILS_SID, 
                               A.PERIOD_SID, 
                               RATE = CASE 
                                        WHEN @RATE_BASIS = ''ACCRUAL RATE PROJECTION'' THEN ISNULL(ARP.RATE, 0) * 100
                                        ELSE COALESCE(ISNULL(TF.AMOUNT, 0) / NULLIF(TFS.AMOUNT, 0), 0) * 100
                                      END, 
                               TFS.AMOUNT AS SALES_AMOUNT, 
                               TF.AMOUNT  AS DISCOUNT_AMOUNT, 
                               CURRENT_PIPELINE_ACCRUAL = ISNULL(B.CURRENT_BALANCE, 0), 
                               PROJECTED_PIPELINE_ACCRUAL = ( CASE 
                                                                WHEN @RATE_BASIS = ''ACCRUAL RATE PROJECTION'' THEN ISNULL(ARP.RATE, 0)
                                                                ELSE COALESCE(ISNULL(TF.AMOUNT, 0) / NULLIF(TFS.AMOUNT, 0), 0)
                                                              END ) * ISNULL(APS.NET_PIPELINE_VALUE, 0),
                               VARIANCE = ((ISNULL(B.CURRENT_BALANCE, 0) - ( ( CASE 
                                                                                 WHEN @RATE_BASIS = ''ACCRUAL RATE PROJECTION'' THEN ISNULL(ARP.RATE, 0)
                                                                                 ELSE COALESCE(ISNULL(TF.AMOUNT, 0) / NULLIF(TFS.AMOUNT, 0), 0)
                                                                               END ) * ISNULL(APS.NET_PIPELINE_VALUE, 0) ) ))
                        FROM   #TEMP_ARM_PROJ_DETAILS A 
                               LEFT JOIN ', @ARM_CURRENT_BALANCE, ' B 
                                      ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
					                     AND A.RS_MODEL_SID = B.RS_MODEL_SID
                                         AND A.PERIOD_SID = B.PERIOD_SID 
                               LEFT JOIN #TEMP_ARP_RATE ARP 
                                      ON ARP.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID
                               LEFT JOIN #TEMP_FORECAST TF 
                                      ON A.ARM_ADJUSTMENT_DETAILS_SID = TF.ARM_ADJUSTMENT_DETAILS_SID
                               LEFT JOIN #TEMP_FILE_SALES TFS 
                                      ON TFS.ITEM_MASTER_SID = A.ITEM_MASTER_SID 
                               LEFT JOIN (SELECT ITEM_MASTER_SID, 
                                                 MAX(NET_PIPELINE_VALUE) NET_PIPELINE_VALUE 
                                          FROM   ', @INVENTORY_TABLE, ' WHERE  PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ' GROUP  BY ITEM_MASTER_SID) APS 
                                      ON APS.ITEM_MASTER_SID = A.ITEM_MASTER_SID)A ')

                      EXEC Sp_executesql
                        @SQL,
                        N'@RATE_BASIS VARCHAR(50)',
                        @RATE_BASIS=@RATE_BASIS
                  END
                ELSE IF ( @RATE_BASIS = 'Contract Terms' )
                  BEGIN
                      SET @SQL=Concat('INSERT INTO  #TEMP_OUTPUT
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             RATE,                              
                             CURRENT_PIPELINE_ACCRUAL, 
                             PROJECTED_PIPELINE_ACCRUAL, 
							 PIPELINE_RATIO,
                             VARIANCE)
					SELECT ARM_ADJUSTMENT_DETAILS_SID, 
                       PERIOD_SID, 
                       RATE,                        
                       CURRENT_PIPELINE_ACCRUAL, 
                       PROJECTED_PIPELINE_ACCRUAL, 
                       COALESCE(CURRENT_PIPELINE_ACCRUAL / NULLIF(PROJECTED_PIPELINE_ACCRUAL, 0), 0) AS PIPELINE_RATIO,
                       VARIANCE
                FROM   (SELECT A.ARM_ADJUSTMENT_DETAILS_SID, 
                               A.PERIOD_SID, 
                               RATE = ISNULL(CTR.PROJECTION_RATE,0),
                               CURRENT_PIPELINE_ACCRUAL = ISNULL(B.CURRENT_BALANCE, 0), 
                               PROJECTED_PIPELINE_ACCRUAL = ISNULL(CTR.PROJECTION_RATE,0) * ISNULL(CTR.NET_CALCULATED_SALES, 0),
                               VARIANCE = ((ISNULL(B.CURRENT_BALANCE, 0) - ( ISNULL(CTR.PROJECTION_RATE,0) * ISNULL(CTR.NET_CALCULATED_SALES, 0) ) ))
                        FROM   #TEMP_ARM_PROJ_DETAILS A 
                               LEFT JOIN ', @ARM_CURRENT_BALANCE, ' B 
                                      ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
					                     AND A.RS_MODEL_SID = B.RS_MODEL_SID
                                         AND A.PERIOD_SID = B.PERIOD_SID                           
                               LEFT JOIN #CT_RESULT CTR
                                      ON  CTR.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID
									  AND A.RS_MODEL_SID = CTR.RS_MODEL_SID
                                         AND A.PERIOD_SID = CTR.PERIOD_SID)A ')

                      EXEC Sp_executesql
                        @SQL
                  END

                SET @SQL=''

                INSERT INTO #TEMP_AMOUNT
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             PERIOD_SID,
                             ACCRUAL_AMOUNT,
                             INDICATOR)
                SELECT ARM_ADJUSTMENT_DETAILS_SID,
                       PERIOD_SID,
                       Sum(VARIANCE) AS ACCRUAL_AMOUNT,
                       CASE
                         WHEN Sum(VARIANCE) > 0 THEN 0
                         WHEN Sum(VARIANCE) < 0 THEN 1
                         ELSE NULL
                       END           AS INDICATOR
                FROM   #TEMP_OUTPUT
                GROUP  BY ARM_ADJUSTMENT_DETAILS_SID,
                          PERIOD_SID

                SET @SQL=Concat('IF EXISTS (SELECT 1 FROM ', @ARM_ADJUSTMENTS, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
                                          ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
					                   BEGIN 
												TRUNCATE TABLE ', @ARM_ADJUSTMENTS, '  
									   END
				INSERT INTO ', @ARM_ADJUSTMENTS, '(ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             ADJUSTMENT_TYPE,
							 ACCOUNT, 
                             CREDIT,
							 DEBIT
							 )
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
					   TAM.PERIOD_SID AS PERIOD_SID,
					   ', @ADJUSTMENT_TYPE, ' AS ADJUSTMENT_TYPE,
					   AAC.ACCOUNT,
					   SUM(ABS(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT,
					   SUM(ABS(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT
				FROM   #TEMP_AMOUNT TAM 
					  LEFT JOIN ARM_ADJ_RES_CCP AAC 
						 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
								  AND AAC.ADJUSTMENT_TYPE = ', @ADJUSTMENT_TYPE, ' 
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
						  ACCOUNT')

                EXEC Sp_executesql
                  @SQL

                SET @SQL=''

                INSERT INTO #CURRENT_LIABILITY
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             PERIOD_SID,
                             CREDIT_AMOUNT,
                             DEBIT_AMOUNT,
                             CURRENT_AMOUNT)
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
                       Isnull(A1.PERIOD_SID, A2.PERIOD_SID),
                       Sum(Abs(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT_AMOUNT,
                       Sum(Abs(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT_AMOUNT,
                       Isnull(Sum(Abs(A1.ACCRUAL_AMOUNT)), 0) - Isnull(Sum(Abs(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
                FROM   #TEMP_AMOUNT TAM
                       JOIN ARM_ADJ_RES_CCP AAC
                         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
                       INNER JOIN HELPER_TABLE HT
                               ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
                                  AND HT.DESCRIPTION = 'LIABILITY'
                                  AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
                       LEFT JOIN #TEMP_AMOUNT A1
                              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
                                 AND AAC.CREDIT = A1.INDICATOR
                       LEFT JOIN #TEMP_AMOUNT A2
                              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
                                 AND AAC.DEBIT = A2.INDICATOR
                       JOIN PERIOD P
                         ON P.PERIOD_SID = Isnull(A1.PERIOD_SID, A2.PERIOD_SID)
                            AND P.PERIOD_SID = TAM.PERIOD_SID
                WHERE  AAC.CREDIT IS NOT NULL
                       AND AAC.DEBIT IS NOT NULL
                GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
                          Isnull(A1.PERIOD_SID, A2.PERIOD_SID)

                INSERT INTO #CURRENT_EXPENSE
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             PERIOD_SID,
                             CREDIT_AMOUNT,
                             DEBIT_AMOUNT,
                             CURRENT_AMOUNT)
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
                       Isnull(A1.PERIOD_SID, A2.PERIOD_SID),
                       Sum(Abs(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT_AMOUNT,
                       Sum(Abs(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT_AMOUNT,
                       Isnull(Sum(Abs(A1.ACCRUAL_AMOUNT)), 0) - Isnull(Sum(Abs(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
                FROM   #TEMP_AMOUNT TAM
                       JOIN ARM_ADJ_RES_CCP AAC
                         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
                       INNER JOIN HELPER_TABLE HT
                               ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
                                  AND HT.DESCRIPTION = 'EXPENSE'
                                  AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
                       LEFT JOIN #TEMP_AMOUNT A1
                              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
                                 AND AAC.CREDIT = A1.INDICATOR
                       LEFT JOIN #TEMP_AMOUNT A2
                              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
                                 AND AAC.DEBIT = A2.INDICATOR
                       JOIN PERIOD P
                         ON P.PERIOD_SID = Isnull(A1.PERIOD_SID, A2.PERIOD_SID)
                            AND P.PERIOD_SID = TAM.PERIOD_SID
                WHERE  AAC.CREDIT IS NOT NULL
                       AND AAC.DEBIT IS NOT NULL
                GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
                          Isnull(A1.PERIOD_SID, A2.PERIOD_SID)

                --for BSR(Balnce Summary Report) Tr-3	Calculation We Calculated the Liability and Expense Amount Separately for each Account Ends here
                --Insertion into main table starts here for Tr-3 both Rate and Adjustmemnt Summary Screens with BSR Chnages    
                SET @SQL=Concat('IF EXISTS (SELECT 1 FROM ', @INVENTORY_RATE_TABLE, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
                                          ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
					                   BEGIN 
												TRUNCATE TABLE ', @INVENTORY_RATE_TABLE, '  
									   END
				INSERT INTO ', @INVENTORY_RATE_TABLE, '(ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             RATE, 
                             SALES_AMOUNT, 
                             DISCOUNT_AMOUNT, 
                             CURRENT_PIPELINE_INVENTORY, 
                             PROJECTED_PIPELINE_INVENTORY, 
                             PIPELINE_RATIO, 
                             VARIANCE,
							 LIABILITY_AMOUNT,
							 EXPENSE_AMOUNT) 
                SELECT A.ARM_ADJUSTMENT_DETAILS_SID, 
                       A.PERIOD_SID, 
                       A.RATE, 
                       A.SALES_AMOUNT, 
                       A.DISCOUNT_AMOUNT, 
                       A.CURRENT_PIPELINE_ACCRUAL, 
                       A.PROJECTED_PIPELINE_ACCRUAL, 
                       A.PIPELINE_RATIO,
                       A.VARIANCE,
					   LIA.CURRENT_AMOUNT,
					   CEX.CURRENT_AMOUNT
                FROM   #TEMP_OUTPUT A 
				LEFT JOIN #CURRENT_LIABILITY LIA 
						ON A.ARM_ADJUSTMENT_DETAILS_SID = LIA.ARM_ADJUSTMENT_DETAILS_SID 
				LEFT JOIN #CURRENT_EXPENSE CEX 
					    ON A.ARM_ADJUSTMENT_DETAILS_SID = CEX.ARM_ADJUSTMENT_DETAILS_SID  ')

                EXEC Sp_executesql
                  @SQL
            --Insertion into main table Ends here for Tr-3 both Rate and Adjustmemnt Summary Screens with BSR Chnages            
            END
      END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC [dbo].Usperrorcollector

          SELECT @ERRORMESSAGE = Error_message(),
                 @ERRORSEVERITY = Error_severity(),
                 @ERRORSTATE = Error_state(),
                 @ERRORPROCEDURE = Error_procedure(),
                 @ERRORLINE = Error_line(),
                 @ERRORNUMBER = Error_number()

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