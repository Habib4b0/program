IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_ARM_BSR_SINGLE_LIABILITY' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].PRC_ARM_BSR_SINGLE_LIABILITY 
  END 

GO 

CREATE PROCEDURE [DBO].PRC_ARM_BSR_SINGLE_LIABILITY (@PROJECTION_MASTER_SID INT, 
                                                     @USER_ID               INT, 
                                                     @SESSION_ID            INT,
													 @FREQUENCY CHAR(1),
													 @FROM_PERIOD VARCHAR(10),
													 @TO_PERIOD VARCHAR(10)) 
AS 

/**********************************************************************************************************
** File Name  	:	PRC_ARM_BSR_SINGLE_LIABILITY.sql
** Procedure Name	:	PRC_ARM_BSR_SINGLE_LIABILITY
** Description		:	To generate BSR Single Liability Adjustment Summary tab.
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for BSR Single Liability report
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
											
** Output Parameters:	NA
** Author Name		:	@AjayNaidu
** Creation Date	:	23/10/2017 - MM/DD/YYYY
** Called By		:   Called by Application for BSR Single Liability report 
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date       Ticket No                      Author           Description 
** ---  --------    ---------                     -------------     -----------------------------
** 01   01/12/2017  GAL-12832                      @AJAY            Calculation mismatch for 2 accounts
** 02   28-12-2017  GAL-12267                      @AjayNaidu       BP014 - [NOT] NULL option
** 03   02-01-2018  GAL-12271                      @AjayNaidu       PE003 Select into option removal
** 04   08-01-2018  GAL-12270                      @AjayNaidu       EI025 PE001 PE010 ST008 MI005 MI002 Error codes
** 05   01-02-2018  GAL-13117                      @AjayNaidu       BSR - Actuals Logic change
** 06   12-02-2018  GAL-13128                      @AjayNaidu       BSR - History Logic Change
** 07   07-05-2018  GAL-13426                      @AjayNaidu       BSR - Single Liability Logic Change
*********************************************************************************************************/

  BEGIN 
      SET NOCOUNT ON 

      BEGIN TRY 
          -- VARIABLES INITIALIZATION STARTS HERE 
          DECLARE @START_PERIOD_SID INT, 
                  @BU_COMPANY       INT, 
                  @GL_COMPANY       INT, 
                  @TO_PERIOD_SID    INT, 
                  @SUMMARY_TYPE     INT 
          DECLARE @SQL NVARCHAR(MAX) 
          DECLARE @ARM_BSR_SINGLE_LIABILITY     VARCHAR(200) = CONCAT ('ST_ARM_BSR_SINGLE_LIABILITY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                  @ARM_BSR_CUM_SINGLE_LIABILITY VARCHAR(200) = CONCAT ('ST_ARM_BSR_CUM_SINGLE_LIABILITY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))

        -- VARIABLES INITIALIZATION ENDS HERE 
----------------Creating all necessray temp tables(all ddl statements) for the logic Starts here (PE010 CodeGuarderror)
      IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_MASTER') IS NOT NULL 
        DROP TABLE #TEMP_ARM_PROJ_MASTER 

      CREATE TABLE #TEMP_ARM_PROJ_MASTER 
        ( 
           ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL, 
           CCP_DETAILS_SID            INT NOT NULL, 
           RS_MODEL_SID               INT NOT NULL, 
           CONTRACT_MASTER_SID        INT NOT NULL, 
           COMPANY_MASTER_SID         INT NOT NULL, 
           ITEM_MASTER_SID            INT NOT NULL,
		   RS_ID                      VARCHAR(50) NOT NULL,
           PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID) 
        )
		
	 IF OBJECT_ID('TEMPDB..#ARM_BSR_CCP') IS NOT NULL 
            DROP TABLE #ARM_BSR_CCP 

          CREATE TABLE #ARM_BSR_CCP 
            ( 
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL, 
               ACCOUNT                    VARCHAR(100) NOT NULL, 
               FEES_ACCRUAL               INT NULL, 
               INFLATION_ADJ              INT NULL, 
               CREDIT_CARD_FEES           INT NULL, 
               OTHER_FIXED_DOLLAR_FEES    INT NULL, 
               INVENTORY_VALULATION       INT NULL, 
               DEMAND_PAYMENT_TRUE_UP     INT NULL, 
               CCP_DETAILS_SID            INT NULL, 
               RS_MODEL_SID               INT NULL, 
            )  

          IF OBJECT_ID('TEMPDB..#TEMP_ARM_APPRVD_PROJ_START') IS NOT NULL 
            DROP TABLE #TEMP_ARM_APPRVD_PROJ_START 

			 CREATE TABLE #TEMP_ARM_APPRVD_PROJ_START
	  (
	  ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
	  PROJECTION_MASTER_SID INT NOT NULL,
	  CCP_DETAILS_SID INT NOT NULL,
	  RS_MODEL_SID INT NOT NULL,
	  ADJUSTMENT_TYPE INT  NULL,
	  GL_PERIOD_SID INT NULL
	  )

	  IF OBJECT_ID('TEMPDB..#CTE') IS NOT NULL 
  DROP TABLE #CTE 

CREATE TABLE #CTE 
  ( 
     ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL, 
     CCP_DETAILS_SID            INT NOT NULL, 
     RS_MODEL_SID               INT NOT NULL, 
     CONTRACT_MASTER_SID        INT NOT NULL, 
     COMPANY_MASTER_SID         INT NOT NULL, 
     ITEM_MASTER_SID            INT NOT NULL, 
     RS_ID                      VARCHAR(50) NOT NULL, 
     FEES_ACCRUAL               INT NULL, 
     INFLATION_ADJ              INT NULL, 
     CREDIT_CARD_FEES           INT NULL, 
     OTHER_FIXED_DOLLAR_FEES    INT NULL, 
     INVENTORY_VALULATION       INT NULL, 
     DEMAND_PAYMENT_TRUE_UP     INT NULL, 
     ACCOUNT                    VARCHAR(100) NOT NULL 
  ) 

  IF OBJECT_ID('TEMPDB..#TEMP_ACTIVE_CUM_BALANCE') IS NOT NULL 
  DROP TABLE #TEMP_ACTIVE_CUM_BALANCE 

CREATE TABLE #TEMP_ACTIVE_CUM_BALANCE 
  ( 
     CCP_DETAILS_SID         INT NOT NULL, 
     RS_MODEL_SID            INT NOT NULL, 
     FEES_ACCRUAL            NUMERIC(22, 6) NULL, 
     INFLATION_ADJ           NUMERIC(22, 6) NULL, 
     CREDIT_CARD_FEES        NUMERIC(22, 6) NULL, 
     OTHER_FIXED_DOLLAR_FEES NUMERIC(22, 6) NULL, 
     INVENTORY_VALULATION    NUMERIC(22, 6) NULL, 
     DEMAND_PAYMENT_TRUE_UP  NUMERIC(22, 6) NULL, 
     ACTUALS_PAYMENTS        NUMERIC(22, 6) NULL
     PRIMARY KEY(CCP_DETAILS_SID, RS_MODEL_SID) 
  ) 

 IF OBJECT_ID('TEMPDB..#TEMP_HIST_CUM_BALANCE') IS NOT NULL 
            DROP TABLE #TEMP_HIST_CUM_BALANCE 

          CREATE TABLE #TEMP_HIST_CUM_BALANCE 
            ( 
               CCP_DETAILS_SID         INT NOT NULL, 
               RS_MODEL_SID            INT NOT NULL, 
               FEES_ACCRUAL            NUMERIC(22, 6) NULL, 
               INFLATION_ADJ           NUMERIC(22, 6) NULL, 
               CREDIT_CARD_FEES        NUMERIC(22, 6) NULL, 
               OTHER_FIXED_DOLLAR_FEES NUMERIC(22, 6) NULL, 
               INVENTORY_VALULATION    NUMERIC(22, 6) NULL, 
               DEMAND_PAYMENT_TRUE_UP  NUMERIC(22, 6) NULL 
               PRIMARY KEY(CCP_DETAILS_SID, RS_MODEL_SID) 
            ) 

	IF OBJECT_ID('TEMPDB..#TEMP_ACTIVE_PERIOD_BALANCE') IS NOT NULL 
  DROP TABLE #TEMP_ACTIVE_PERIOD_BALANCE 

CREATE TABLE #TEMP_ACTIVE_PERIOD_BALANCE 
  ( 
     CCP_DETAILS_SID         INT NOT NULL, 
     RS_MODEL_SID            INT NOT NULL, 
     PERIOD_SID              INT NOT NULL, 
     FEES_ACCRUAL            NUMERIC(22, 6) NULL, 
     INFLATION_ADJ           NUMERIC(22, 6) NULL, 
     CREDIT_CARD_FEES        NUMERIC(22, 6) NULL, 
     OTHER_FIXED_DOLLAR_FEES NUMERIC(22, 6) NULL, 
     INVENTORY_VALULATION    NUMERIC(22, 6) NULL, 
     DEMAND_PAYMENT_TRUE_UP  NUMERIC(22, 6) NULL, 
     ACTUALS_PAYMENTS        NUMERIC(22, 6) NULL
     PRIMARY KEY(CCP_DETAILS_SID, RS_MODEL_SID, PERIOD_SID) 
  )

     IF OBJECT_ID('TEMPDB..#TEMP_HIST_PERIOD_BALANCE') IS NOT NULL 
            DROP TABLE #TEMP_HIST_PERIOD_BALANCE 

          CREATE TABLE #TEMP_HIST_PERIOD_BALANCE 
            ( 
               CCP_DETAILS_SID         INT NOT NULL, 
               RS_MODEL_SID            INT NOT NULL, 
               PERIOD_SID              INT NOT NULL, 
               FEES_ACCRUAL            NUMERIC(22, 6) NULL, 
               INFLATION_ADJ           NUMERIC(22, 6) NULL, 
               CREDIT_CARD_FEES        NUMERIC(22, 6) NULL, 
               OTHER_FIXED_DOLLAR_FEES NUMERIC(22, 6) NULL, 
               INVENTORY_VALULATION    NUMERIC(22, 6) NULL, 
               DEMAND_PAYMENT_TRUE_UP  NUMERIC(22, 6) NULL 
               PRIMARY KEY(CCP_DETAILS_SID, RS_MODEL_SID, PERIOD_SID) 
            ) 

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
----------------Creating all necessray temp tables(all ddl statements) for the logic Ends here (PE010 CodeGuarderror)
          --TAKING PRICE RELATED PERIOD SELECTED IN DATA SELECTION AND ASSIGNING GL AND BU TO VARIABLE STARTS HERE 
				SELECT @GL_COMPANY = COMPANY_MASTER_SID
		FROM PROJECTION_MASTER
		WHERE PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

			   INSERT INTO #TEMP_PERIOD
                      (PERIOD_SID,
                       PERIODS,
                       PERIODS_MONTH,
                       PERIOD_DATE,
                       MONTH,
                       YEAR)
          SELECT PERIOD_SID,
                 CASE
                   WHEN @FREQUENCY = 'M' THEN Concat (LEFT(Datename(MM, PERIOD_DATE), 3), ' ', YEAR)
                   WHEN @FREQUENCY = 'Q' THEN Concat ('Q', QUARTER, ' ', YEAR)
                   WHEN @FREQUENCY = 'S' THEN Concat ('S', SEMI_ANNUAL, ' ', YEAR)
                   ELSE Cast(YEAR AS CHAR(4))
                 END                                                    AS PERIODS,
                 Concat (LEFT(Datename(MM, PERIOD_DATE), 3), ' ', YEAR) AS PERIODS_MONTH,
                 PERIOD_DATE,
                 MONTH,
                 YEAR
          FROM   PERIOD

		SET @START_PERIOD_SID = (
				SELECT MIN(PERIOD_SID)
				FROM #TEMP_PERIOD
				WHERE PERIODS = @FROM_PERIOD
				)
		SET @TO_PERIOD_SID = (
				SELECT MAX(PERIOD_SID)
				FROM #TEMP_PERIOD
				WHERE PERIODS = @TO_PERIOD
				)

          SELECT @BU_COMPANY = BU_COMPANY_MASTER_SID, 
                 @SUMMARY_TYPE = TRANSACTION_TYPE 
          FROM   ARM_ADJUSTMENT_MASTER 
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

          --TAKING PRICE RELATED PERIOD SELECTED IN DATA SELECTION AND ASSIGNING GL AND BU TO VARIABLE ENDS HERE 
          --PULLING CCP+D COMBINATION FOR CURRENT PROJECTION(BALNCE REPORT) STARTS HERE 
      INSERT INTO #TEMP_ARM_PROJ_MASTER 
                  (ARM_ADJUSTMENT_DETAILS_SID, 
                   CCP_DETAILS_SID, 
                   RS_MODEL_SID, 
                   CONTRACT_MASTER_SID, 
                   COMPANY_MASTER_SID, 
                   ITEM_MASTER_SID,
				   RS_ID) 
      SELECT A.ARM_ADJUSTMENT_DETAILS_SID,
             A.CCP_DETAILS_SID, 
             A.RS_MODEL_SID, 
             B.CONTRACT_MASTER_SID, 
             B.COMPANY_MASTER_SID, 
             B.ITEM_MASTER_SID,
			 RS.RS_ID
      FROM  ARM_ADJUSTMENT_DETAILS A
      JOIN CCP_DETAILS B
        ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
      JOIN RS_MODEL RS
        ON RS.RS_MODEL_SID = A.RS_MODEL_SID
      JOIN ARM_ADJUSTMENT_MASTER AM
        ON AM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
          WHERE  A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

          --PULLING CCP+D COMBINATION FOR CURRENT PROJECTION(BALNCE REPORT) ENDS HERE 
          --IDENTIFYING BALANCE SUMMARY CONFIGURATION ACCOUNTS AND ADJUSTMENT TYPES W.R.T TO CCP+D  
          INSERT INTO #ARM_BSR_CCP 
                      (ARM_ADJUSTMENT_DETAILS_SID, 
                       ACCOUNT, 
                       FEES_ACCRUAL, 
                       INFLATION_ADJ, 
                       CREDIT_CARD_FEES, 
                       OTHER_FIXED_DOLLAR_FEES, 
                       INVENTORY_VALULATION, 
                       DEMAND_PAYMENT_TRUE_UP, 
                       CCP_DETAILS_SID, 
                       RS_MODEL_SID) 
          SELECT AAS.ARM_ADJUSTMENT_DETAILS_SID, 
                 AAS.ACCOUNT, 
                 AAS.FEES_ACCRUAL, 
                 AAS.INFLATION_ADJ, 
                 AAS.CREDIT_CARD_FEES, 
                 AAS.OTHER_FIXED_DOLLAR_FEES, 
                 AAS.INVENTORY_VALULATION, 
                 AAS.DEMAND_PAYMENT_TRUE_UP, 
                 TA.CCP_DETAILS_SID, 
                 TA.RS_MODEL_SID 
          FROM   ARM_BSR_CCP AAS 
                 JOIN #TEMP_ARM_PROJ_MASTER TA 
                   ON AAS.ARM_ADJUSTMENT_DETAILS_SID = TA.ARM_ADJUSTMENT_DETAILS_SID 
                      AND AAS.SUMMARY_TYPE = @SUMMARY_TYPE 

          -----------APPROVED CCP+D+ADJUSTMENT_TYPE+ACCOUNT'S FOR THE PARTICULAR ADJUSTMENT TYPE STARTS HERE  
		  INSERT INTO #TEMP_ARM_APPRVD_PROJ_START(ARM_ADJUSTMENT_DETAILS_SID,PROJECTION_MASTER_SID,CCP_DETAILS_SID,RS_MODEL_SID,
	  ADJUSTMENT_TYPE,GL_PERIOD_SID)
		SELECT DISTINCT AD.ARM_ADJUSTMENT_DETAILS_SID
			,AAM.PROJECTION_MASTER_SID
			,TAM.CCP_DETAILS_SID
			,TAM.RS_MODEL_SID
			,AAM.TRANSACTION_TYPE AS ADJUSTMENT_TYPE
			,P.PERIOD_SID AS GL_PERIOD_SID
		FROM  ARM_ADJUSTMENT_DETAILS AD 
		INNER JOIN #TEMP_ARM_PROJ_MASTER TAM ON TAM.CCP_DETAILS_SID = AD.CCP_DETAILS_SID
			AND TAM.RS_MODEL_SID = AD.RS_MODEL_SID
		INNER JOIN ARM_ADJUSTMENT_MASTER AAM ON AAM.PROJECTION_MASTER_SID = AD.PROJECTION_MASTER_SID
			AND AAM.BU_COMPANY_MASTER_SID = @BU_COMPANY
	    INNER JOIN PERIOD P ON P.PERIOD_DATE=CONVERT(datetime, DATEADD(MM, -1, DATEADD(DD, 1, EOMONTH(AAM.GL_IMPACT_DATE, 0))))
		AND P.PERIOD_SID<=@TO_PERIOD_SID
		INNER JOIN PROJECTION_MASTER PM ON AAM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
			AND PM.COMPANY_MASTER_SID = @GL_COMPANY
		INNER JOIN WORKFLOW_MASTER WM ON WM.PROJECTION_MASTER_SID = AAM.PROJECTION_MASTER_SID
			AND WM.WORKFLOW_ID LIKE 'ARM%'
			AND EXISTS (
				SELECT H1.HELPER_TABLE_SID
				FROM HELPER_TABLE H1
				WHERE H1.LIST_NAME = 'WORKFLOWSTATUS'
					AND H1.DESCRIPTION = 'APPROVED'
					AND H1.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID
				);

INSERT INTO #CTE 
            (ARM_ADJUSTMENT_DETAILS_SID, 
             CCP_DETAILS_SID, 
             RS_MODEL_SID, 
             CONTRACT_MASTER_SID, 
             COMPANY_MASTER_SID, 
             ITEM_MASTER_SID, 
             RS_ID, 
             FEES_ACCRUAL, 
             INFLATION_ADJ, 
             CREDIT_CARD_FEES, 
             OTHER_FIXED_DOLLAR_FEES, 
             INVENTORY_VALULATION, 
             DEMAND_PAYMENT_TRUE_UP, 
             ACCOUNT) 
SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
       TAM.CCP_DETAILS_SID, 
       TAM.RS_MODEL_SID, 
       TAM.CONTRACT_MASTER_SID, 
       TAM.COMPANY_MASTER_SID, 
       TAM.ITEM_MASTER_SID, 
       TAM.RS_ID, 
       BSC.FEES_ACCRUAL, 
       BSC.INFLATION_ADJ, 
       BSC.CREDIT_CARD_FEES, 
       BSC.OTHER_FIXED_DOLLAR_FEES, 
       BSC.INVENTORY_VALULATION, 
       BSC.DEMAND_PAYMENT_TRUE_UP, 
       BSC.ACCOUNT 
FROM   #TEMP_ARM_PROJ_MASTER TAM 
       INNER JOIN #ARM_BSR_CCP BSC 
               ON TAM.ARM_ADJUSTMENT_DETAILS_SID = BSC.ARM_ADJUSTMENT_DETAILS_SID 

--------------BEGINING BALANCE  (BASED ON BALANCE SUMMARY CONFIGURATION) CALUCLTRAION STARTS HERE   

INSERT INTO #TEMP_ACTIVE_CUM_BALANCE 
            (CCP_DETAILS_SID, 
             RS_MODEL_SID, 
             FEES_ACCRUAL, 
             INFLATION_ADJ, 
             CREDIT_CARD_FEES, 
             OTHER_FIXED_DOLLAR_FEES, 
             INVENTORY_VALULATION, 
             DEMAND_PAYMENT_TRUE_UP, 
             ACTUALS_PAYMENTS) 
SELECT A.CCP_DETAILS_SID, 
       A.RS_MODEL_SID, 
       B.FEES_ACCRUAL, 
       C.INFLATION_ADJ, 
       D.CREDIT_CARD_FEES, 
       E.OTHER_FIXED_DOLLAR_FEES, 
       F.INVENTORY_VALULATION, 
       G.DEMAND_PAYMENT_TRUE_UP, 
       H.ACTUALS_PAYMENTS 
FROM   #TEMP_ARM_PROJ_MASTER A 
       LEFT JOIN(SELECT TAM.CCP_DETAILS_SID, 
                        TAM.RS_MODEL_SID, 
                        ISNULL(SUM(AA_FA.CREDIT), 0) - ISNULL(SUM(AA_FA.DEBIT), 0) AS FEES_ACCRUAL
                 FROM   #CTE TAM 
                        INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                   AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                        INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_FA 
                                ON AAC_FA.ARM_ADJUSTMENT_CONFIG_SID = TAM.FEES_ACCRUAL 
                                   AND AAC_FA.REDEMPTION_PERIOD = 1 
                        INNER JOIN ARM_ADJUSTMENTS AA_FA 
                                ON AA_FA.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                   AND AA_FA.ADJUSTMENT_TYPE = TAM.FEES_ACCRUAL 
                                   AND TAM.FEES_ACCRUAL = TAP_D.ADJUSTMENT_TYPE 
                                   AND AA_FA.ACCOUNT = TAM.ACCOUNT 
                                   AND AA_FA.PERIOD_SID < @START_PERIOD_SID 
                 GROUP  BY TAM.CCP_DETAILS_SID, 
                           TAM.RS_MODEL_SID) B 
              ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = B.RS_MODEL_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(AA_IA.CREDIT), 0) - ISNULL(SUM(AA_IA.DEBIT), 0) AS INFLATION_ADJ
                  FROM   #CTE TAM 
                         INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                 ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                    AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                         INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_IA 
                                 ON AAC_IA.ARM_ADJUSTMENT_CONFIG_SID = TAM.INFLATION_ADJ 
                                    AND AAC_IA.REDEMPTION_PERIOD = 1 
                         INNER JOIN ARM_ADJUSTMENTS AA_IA 
                                 ON AA_IA.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                    AND AA_IA.ADJUSTMENT_TYPE = TAM.INFLATION_ADJ 
                                    AND TAM.INFLATION_ADJ = TAP_D.ADJUSTMENT_TYPE 
                                    AND AA_IA.ACCOUNT = TAM.ACCOUNT 
                                    AND AA_IA.PERIOD_SID < @START_PERIOD_SID 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID) C 
              ON A.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = C.RS_MODEL_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(AA_CC.CREDIT), 0) - ISNULL(SUM(AA_CC.DEBIT), 0) AS CREDIT_CARD_FEES
                  FROM   #CTE TAM 
                         INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                 ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                    AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                         INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_CC 
                                 ON AAC_CC.ARM_ADJUSTMENT_CONFIG_SID = TAM.CREDIT_CARD_FEES 
                                    AND AAC_CC.REDEMPTION_PERIOD = 1 
                         INNER JOIN ARM_ADJUSTMENTS AA_CC 
                                 ON AA_CC.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                    AND AA_CC.ADJUSTMENT_TYPE = TAM.CREDIT_CARD_FEES 
                                    AND TAM.CREDIT_CARD_FEES = TAP_D.ADJUSTMENT_TYPE 
                                    AND AA_CC.ACCOUNT = TAM.ACCOUNT 
                                    AND AA_CC.PERIOD_SID < @START_PERIOD_SID 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID) D 
              ON A.CCP_DETAILS_SID = D.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = D.RS_MODEL_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(AA_OF.CREDIT), 0) - ISNULL(SUM(AA_OF.DEBIT), 0) AS OTHER_FIXED_DOLLAR_FEES
                  FROM   #CTE TAM 
                         INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                 ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                    AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                         INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_OF 
                                 ON AAC_OF.ARM_ADJUSTMENT_CONFIG_SID = TAM.OTHER_FIXED_DOLLAR_FEES
                                    AND AAC_OF.REDEMPTION_PERIOD = 1 
                         INNER JOIN ARM_ADJUSTMENTS AA_OF 
                                 ON AA_OF.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                    AND AA_OF.ADJUSTMENT_TYPE = TAM.OTHER_FIXED_DOLLAR_FEES 
                                    AND TAM.OTHER_FIXED_DOLLAR_FEES = TAP_D.ADJUSTMENT_TYPE 
                                    AND AA_OF.ACCOUNT = TAM.ACCOUNT 
                                    AND AA_OF.PERIOD_SID < @START_PERIOD_SID 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID) E 
              ON A.CCP_DETAILS_SID = E.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = E.RS_MODEL_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(AA_IV.CREDIT), 0) - ISNULL(SUM(AA_IV.DEBIT), 0) AS INVENTORY_VALULATION
                  FROM   #CTE TAM 
                         INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                 ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                    AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                         INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_IV 
                                 ON AAC_IV.ARM_ADJUSTMENT_CONFIG_SID = TAM.INVENTORY_VALULATION
                                    AND AAC_IV.REDEMPTION_PERIOD = 1 
                         INNER JOIN ARM_ADJUSTMENTS AA_IV 
                                 ON AA_IV.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                    AND AA_IV.ADJUSTMENT_TYPE = TAM.INVENTORY_VALULATION 
                                    AND TAM.INVENTORY_VALULATION = TAP_D.ADJUSTMENT_TYPE 
                                    AND AA_IV.ACCOUNT = TAM.ACCOUNT 
                                    AND AA_IV.PERIOD_SID < @START_PERIOD_SID 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID)F 
              ON A.CCP_DETAILS_SID = F.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = F.RS_MODEL_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(AA_DPR.CREDIT), 0) - ISNULL(SUM(AA_DPR.DEBIT), 0) AS DEMAND_PAYMENT_TRUE_UP
                  FROM   #CTE TAM 
                         INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                 ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                    AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                         INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_DPR 
                                 ON AAC_DPR.ARM_ADJUSTMENT_CONFIG_SID = TAM.DEMAND_PAYMENT_TRUE_UP
                                    AND AAC_DPR.REDEMPTION_PERIOD = 1 
                         INNER JOIN ARM_ADJUSTMENTS AA_DPR 
                                 ON AA_DPR.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                    AND AA_DPR.ADJUSTMENT_TYPE = TAM.DEMAND_PAYMENT_TRUE_UP 
                                    AND TAM.DEMAND_PAYMENT_TRUE_UP = TAP_D.ADJUSTMENT_TYPE 
                                    AND AA_DPR.ACCOUNT = TAM.ACCOUNT 
                                    AND AA_DPR.PERIOD_SID < @START_PERIOD_SID 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID) G 
              ON G.CCP_DETAILS_SID = A.CCP_DETAILS_SID 
                 AND G.RS_MODEL_SID = A.RS_MODEL_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(ACT.DISCOUNT), 0) AS ACTUALS_PAYMENTS 
                  FROM   #TEMP_ARM_PROJ_MASTER TAM 
                         INNER JOIN ACTUALS_DETAILS ACT 
                                ON ACT.CCP_DETAILS_SID=TAM.CCP_DETAILS_SID 
                                    AND TAM.RS_MODEL_SID=ACT.RS_MODEL_SID
                                    AND ACT.CASH_PAID_PERIOD<=@TO_PERIOD_SID
                                    AND ACT.PERIOD_SID<@START_PERIOD_SID
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID)H 
              ON H.CCP_DETAILS_SID = A.CCP_DETAILS_SID 
                 AND H.RS_MODEL_SID = A.RS_MODEL_SID 
          --------------------TAKING BEGING BALANCE CALCULATION HISTORY RECORDS FOR PERIOD BALANCE STARTS HERE 
        	;WITH CTE 
     AS (SELECT TAM.CCP_DETAILS_SID, 
                TAM.RS_MODEL_SID, 
	            AHM.ARM_HIST_ADJ_RES_CON_MASTER_SID,
				BSC.FEES_ACCRUAL,
				BSC.INFLATION_ADJ,
				BSC.CREDIT_CARD_FEES,
				BSC.OTHER_FIXED_DOLLAR_FEES,
				BSC.INVENTORY_VALULATION,
				BSC.DEMAND_PAYMENT_TRUE_UP,
				BSC.ACCOUNT
         FROM   #TEMP_ARM_PROJ_MASTER TAM 
                 INNER JOIN #ARM_BSR_CCP BSC 
                         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = BSC.ARM_ADJUSTMENT_DETAILS_SID 
                 INNER JOIN RS_CONTRACT R 
                         ON R.RS_MODEL_SID = TAM.RS_MODEL_SID 
                            AND R.CONTRACT_MASTER_SID = TAM.CONTRACT_MASTER_SID 
                 INNER JOIN ARM_DEDUCTION_SELECTION ADS 
                         ON ADS.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
                            AND ADS.RS_CONTRACT_SID = R.RS_CONTRACT_SID 
                 INNER JOIN ARM_HIST_ADJ_RES_CON_MASTER AHM 
                         ON AHM.RS_CATEGORY = R.RS_CATEGORY 
                            AND AHM.RS_TYPE = R.RS_TYPE 
                            AND AHM.REBATE_PROGRAM_TYPE = R.REBATE_PROGRAM_TYPE 
                            AND AHM.GL_COMPANY_MASTER_SID = @GL_COMPANY 
                            AND AHM.BU_COMPANY_MASTER_SID = @BU_COMPANY 
                            AND CONFIGURATION_TYPE = 0 ) 

INSERT INTO #TEMP_HIST_CUM_BALANCE 
            (CCP_DETAILS_SID, 
             RS_MODEL_SID, 
             FEES_ACCRUAL, 
             INFLATION_ADJ, 
             CREDIT_CARD_FEES, 
             OTHER_FIXED_DOLLAR_FEES, 
             INVENTORY_VALULATION, 
             DEMAND_PAYMENT_TRUE_UP) 
SELECT A.CCP_DETAILS_SID, 
       A.RS_MODEL_SID, 
       B.FEES_ACCRUAL, 
       C.INFLATION_ADJ, 
       D.CREDIT_CARD_FEES, 
       E.OTHER_FIXED_DOLLAR_FEES, 
       F.INVENTORY_VALULATION, 
       G.DEMAND_PAYMENT_TRUE_UP 
FROM   #TEMP_ARM_PROJ_MASTER A 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(AH_FA.CREDIT), 0) - ISNULL(SUM(AH_FA.DEBIT), 0) AS FEES_ACCRUAL
                  FROM   CTE TAM 
                         LEFT JOIN ARM_HIST_ADJUSTMENTS AH_FA 
                                ON AH_FA.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                                   AND AH_FA.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                   AND AH_FA.RS_MODEL_SID = TAM.RS_MODEL_SID 
                                   AND AH_FA.CALCULATION_PERIOD_SID < @START_PERIOD_SID
								    AND AH_FA.GL_PERIOD_SID<=@TO_PERIOD_SID
                                   AND AH_FA.ADJUSTMENT_TYPE = TAM.FEES_ACCRUAL 
                                   AND AH_FA.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID) B 
              ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = B.RS_MODEL_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(AH_IA.CREDIT), 0) - ISNULL(SUM(AH_IA.DEBIT), 0) AS INFLATION_ADJ
                  FROM   CTE TAM 
                         JOIN ARM_HIST_ADJUSTMENTS AH_IA 
                           ON AH_IA.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                              AND AH_IA.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                              AND AH_IA.RS_MODEL_SID = TAM.RS_MODEL_SID 
                              AND AH_IA.CALCULATION_PERIOD_SID < @START_PERIOD_SID
							  AND AH_IA.GL_PERIOD_SID<=@TO_PERIOD_SID 
                              AND AH_IA.ADJUSTMENT_TYPE = TAM.INFLATION_ADJ 
                              AND AH_IA.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID) C 
              ON A.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = C.RS_MODEL_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(AH_CC.CREDIT), 0) - ISNULL(SUM(AH_CC.DEBIT), 0) AS CREDIT_CARD_FEES
                  FROM   CTE TAM 
                         JOIN ARM_HIST_ADJUSTMENTS AH_CC 
                           ON AH_CC.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                              AND AH_CC.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                              AND AH_CC.RS_MODEL_SID = TAM.RS_MODEL_SID 
                              AND AH_CC.CALCULATION_PERIOD_SID < @START_PERIOD_SID
							  AND AH_CC.GL_PERIOD_SID<=@TO_PERIOD_SID 
                              AND AH_CC.ADJUSTMENT_TYPE = TAM.CREDIT_CARD_FEES 
                              AND AH_CC.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID)D 
              ON A.CCP_DETAILS_SID = D.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = D.RS_MODEL_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(AH_OF.CREDIT), 0) - ISNULL(SUM(AH_OF.DEBIT), 0) AS OTHER_FIXED_DOLLAR_FEES
                  FROM   CTE TAM 
                         JOIN ARM_HIST_ADJUSTMENTS AH_OF 
                           ON AH_OF.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                              AND AH_OF.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                              AND AH_OF.RS_MODEL_SID = TAM.RS_MODEL_SID 
                              AND AH_OF.CALCULATION_PERIOD_SID < @START_PERIOD_SID 
							  AND AH_OF.GL_PERIOD_SID<=@TO_PERIOD_SID 
                              AND AH_OF.ADJUSTMENT_TYPE = TAM.OTHER_FIXED_DOLLAR_FEES 
                              AND AH_OF.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID) E 
              ON A.CCP_DETAILS_SID = E.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = E.RS_MODEL_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(AH_IV.CREDIT), 0) - ISNULL(SUM(AH_IV.DEBIT), 0) AS INVENTORY_VALULATION
                  FROM   CTE TAM 
                         JOIN ARM_HIST_ADJUSTMENTS AH_IV 
                           ON AH_IV.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                              AND AH_IV.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                              AND AH_IV.RS_MODEL_SID = TAM.RS_MODEL_SID 
                              AND AH_IV.CALCULATION_PERIOD_SID < @START_PERIOD_SID 
							  AND AH_IV.GL_PERIOD_SID<=@TO_PERIOD_SID 
                              AND AH_IV.ADJUSTMENT_TYPE = TAM.INVENTORY_VALULATION 
                              AND AH_IV.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID) F 
              ON A.CCP_DETAILS_SID = F.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = F.RS_MODEL_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         ISNULL(SUM(AH_DPR.CREDIT), 0) - ISNULL(SUM(AH_DPR.DEBIT), 0) AS DEMAND_PAYMENT_TRUE_UP
                  FROM   CTE TAM 
                         JOIN ARM_HIST_ADJUSTMENTS AH_DPR 
                           ON AH_DPR.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                              AND AH_DPR.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                              AND AH_DPR.RS_MODEL_SID = TAM.RS_MODEL_SID 
                              AND AH_DPR.CALCULATION_PERIOD_SID < @START_PERIOD_SID
							  AND AH_DPR.GL_PERIOD_SID<=@TO_PERIOD_SID  
                              AND AH_DPR.ADJUSTMENT_TYPE = TAM.DEMAND_PAYMENT_TRUE_UP 
                              AND AH_DPR.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID)G 
              ON A.CCP_DETAILS_SID = G.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = G.RS_MODEL_SID 

          ---------------------BEGINING BALANCE CALCULATION INCLUDING HISTORY DEBITS AND CREDITS CALCULATION STARTS HERE  
          SET @SQL=CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_BSR_CUM_SINGLE_LIABILITY, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A    
		                                          ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID )          
												   BEGIN  
												   TRUNCATE TABLE ', @ARM_BSR_CUM_SINGLE_LIABILITY, '    
												  	  END 
				INSERT INTO ', @ARM_BSR_CUM_SINGLE_LIABILITY, '  (ARM_ADJUSTMENT_DETAILS_SID,     
				               FEES_ACCRUAL, 
							   INFLATION_ADJ, 
							   CREDIT_CARD_FEES, 
							   OTHER_FIXED_DOLLAR_FEES, 
							   INVENTORY_VALULATION,
							   DEMAND_PAYMENT_TRUE_UP,
							   ACTUALS_PAYMENTS,
							   PERIOD_BALANCE)  
			  SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
			  ISNULL(TAP.FEES_ACCRUAL,0) + ISNULL(THB.FEES_ACCRUAL,0)  AS FEES_ACCRUAL,
			  ISNULL(TAP.INFLATION_ADJ,0) + ISNULL(THB.INFLATION_ADJ,0)   AS INFLATION_ADJ,  
			  ISNULL(TAP.CREDIT_CARD_FEES,0) + ISNULL(THB.CREDIT_CARD_FEES,0) AS CREDIT_CARD_FEES, 
			  ISNULL(TAP.OTHER_FIXED_DOLLAR_FEES,0) + ISNULL(THB.OTHER_FIXED_DOLLAR_FEES,0) AS OTHER_FIXED_DOLLAR_FEES,
			  ISNULL(TAP.INVENTORY_VALULATION,0) + ISNULL(THB.INVENTORY_VALULATION,0) AS INVENTORY_VALULATION, 
			  ISNULL(TAP.DEMAND_PAYMENT_TRUE_UP,0) + ISNULL(THB.DEMAND_PAYMENT_TRUE_UP,0) AS DEMAND_PAYMENT_TRUE_UP,
			  ISNULL(TAP.ACTUALS_PAYMENTS,0), (ISNULL(TAP.FEES_ACCRUAL,0) + ISNULL(THB.FEES_ACCRUAL,0) + ISNULL(TAP.INFLATION_ADJ,0) + ISNULL(THB.INFLATION_ADJ,0) + ISNULL(TAP.CREDIT_CARD_FEES,0) + ISNULL(THB.CREDIT_CARD_FEES,0) + ISNULL(TAP.OTHER_FIXED_DOLLAR_FEES,0) + ISNULL(THB.OTHER_FIXED_DOLLAR_FEES,0) + ISNULL(TAP.INVENTORY_VALULATION,0) + ISNULL(THB.INVENTORY_VALULATION,0) + ISNULL(TAP.DEMAND_PAYMENT_TRUE_UP,0) + ISNULL(THB.DEMAND_PAYMENT_TRUE_UP,0)) - ISNULL(TAP.ACTUALS_PAYMENTS,0) AS PERIOD_BALANCE 
			  FROM   #TEMP_ARM_PROJ_MASTER TAM 
			         JOIN ARM_ADJUSTMENT_DETAILS AD  
					 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AD.ARM_ADJUSTMENT_DETAILS_SID 
					 LEFT JOIN #TEMP_ACTIVE_CUM_BALANCE TAP  
					 ON TAM.CCP_DETAILS_SID = TAP.CCP_DETAILS_SID  
					  AND TAM.RS_MODEL_SID = TAP.RS_MODEL_SID
					   LEFT JOIN #TEMP_HIST_CUM_BALANCE THB  
					   ON TAM.CCP_DETAILS_SID = THB.CCP_DETAILS_SID  
					   AND TAM.RS_MODEL_SID = THB.RS_MODEL_SID') 

          EXEC SP_EXECUTESQL 
            @SQL 

       --------------ACTIVE PERIOD BALANCES(BASED ON BALANCE SUMMARY CONFIGURATION) CALUCLTRAION STARTS HERE  
  ; 
WITH CTE 
     AS (SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                TAM.CCP_DETAILS_SID, 
                TAM.RS_MODEL_SID, 
                TAM.CONTRACT_MASTER_SID, 
                TAM.COMPANY_MASTER_SID, 
                TAM.ITEM_MASTER_SID, 
                TAM.RS_ID, 
                TAM.FEES_ACCRUAL, 
                TAM.INFLATION_ADJ, 
                TAM.CREDIT_CARD_FEES, 
                TAM.OTHER_FIXED_DOLLAR_FEES, 
                TAM.INVENTORY_VALULATION, 
                TAM.DEMAND_PAYMENT_TRUE_UP, 
                TAM.ACCOUNT, 
                P.PERIOD_SID 
         FROM   #CTE TAM 
                INNER JOIN PERIOD P 
                        ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @TO_PERIOD_SID) 
INSERT INTO #TEMP_ACTIVE_PERIOD_BALANCE 
            (CCP_DETAILS_SID, 
             RS_MODEL_SID, 
             PERIOD_SID, 
             FEES_ACCRUAL, 
             INFLATION_ADJ, 
             CREDIT_CARD_FEES, 
             OTHER_FIXED_DOLLAR_FEES, 
             INVENTORY_VALULATION, 
             DEMAND_PAYMENT_TRUE_UP, 
             ACTUALS_PAYMENTS) 
SELECT A.CCP_DETAILS_SID, 
       A.RS_MODEL_SID,
	   P.PERIOD_SID,
       B.FEES_ACCRUAL, 
       C.INFLATION_ADJ, 
       D.CREDIT_CARD_FEES, 
       E.OTHER_FIXED_DOLLAR_FEES, 
       F.INVENTORY_VALULATION, 
       G.DEMAND_PAYMENT_TRUE_UP, 
       H.ACTUALS_PAYMENTS 
FROM   #TEMP_ARM_PROJ_MASTER A 
 INNER JOIN PERIOD P  ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @TO_PERIOD_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AA_FA.CREDIT), 0) - ISNULL(SUM(AA_FA.DEBIT), 0) AS FEES_ACCRUAL
                  FROM   CTE TAM 
                         INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                 ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                    AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                         INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_FA 
                                 ON AAC_FA.ARM_ADJUSTMENT_CONFIG_SID = TAM.FEES_ACCRUAL 
                                    AND AAC_FA.REDEMPTION_PERIOD = 1 
                         INNER JOIN ARM_ADJUSTMENTS AA_FA 
                                 ON AA_FA.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                    AND AA_FA.ADJUSTMENT_TYPE = TAM.FEES_ACCRUAL 
                                    AND TAM.FEES_ACCRUAL = TAP_D.ADJUSTMENT_TYPE 
                                    AND AA_FA.ACCOUNT = TAM.ACCOUNT 
                                    AND AA_FA.PERIOD_SID = TAM.PERIOD_SID 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID) B 
              ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = B.RS_MODEL_SID 
				 AND P.PERIOD_SID=B.PERIOD_SID
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AA_IA.CREDIT), 0) - ISNULL(SUM(AA_IA.DEBIT), 0) AS INFLATION_ADJ
                  FROM   CTE TAM 
                         INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                 ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                    AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                         INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_IA 
                                 ON AAC_IA.ARM_ADJUSTMENT_CONFIG_SID = TAM.INFLATION_ADJ 
                                    AND AAC_IA.REDEMPTION_PERIOD = 1 
                         INNER JOIN ARM_ADJUSTMENTS AA_IA 
                                 ON AA_IA.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                    AND AA_IA.ADJUSTMENT_TYPE = TAM.INFLATION_ADJ 
                                    AND TAM.INFLATION_ADJ = TAP_D.ADJUSTMENT_TYPE 
                                    AND AA_IA.ACCOUNT = TAM.ACCOUNT 
                                    AND AA_IA.PERIOD_SID = TAM.PERIOD_SID 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID) C 
              ON A.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = C.RS_MODEL_SID
				 AND P.PERIOD_SID=C.PERIOD_SID
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AA_CC.CREDIT), 0) - ISNULL(SUM(AA_CC.DEBIT), 0) AS CREDIT_CARD_FEES
                  FROM   CTE TAM 
                         INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                 ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                    AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                         INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_CC 
                                 ON AAC_CC.ARM_ADJUSTMENT_CONFIG_SID = TAM.CREDIT_CARD_FEES 
                                    AND AAC_CC.REDEMPTION_PERIOD = 1 
                         INNER JOIN ARM_ADJUSTMENTS AA_CC 
                                 ON AA_CC.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                    AND AA_CC.ADJUSTMENT_TYPE = TAM.CREDIT_CARD_FEES 
                                    AND TAM.CREDIT_CARD_FEES = TAP_D.ADJUSTMENT_TYPE 
                                    AND AA_CC.ACCOUNT = TAM.ACCOUNT 
                                    AND AA_CC.PERIOD_SID = TAM.PERIOD_SID 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID) D 
              ON A.CCP_DETAILS_SID = D.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = D.RS_MODEL_SID
				 AND P.PERIOD_SID=D.PERIOD_SID
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AA_OF.CREDIT), 0) - ISNULL(SUM(AA_OF.DEBIT), 0) AS OTHER_FIXED_DOLLAR_FEES
                  FROM   CTE TAM 
                         INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                 ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                    AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                         INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_OF 
                                 ON AAC_OF.ARM_ADJUSTMENT_CONFIG_SID = TAM.OTHER_FIXED_DOLLAR_FEES
                                    AND AAC_OF.REDEMPTION_PERIOD = 1 
                         INNER JOIN ARM_ADJUSTMENTS AA_OF 
                                 ON AA_OF.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                    AND AA_OF.ADJUSTMENT_TYPE = TAM.OTHER_FIXED_DOLLAR_FEES 
                                    AND TAM.OTHER_FIXED_DOLLAR_FEES = TAP_D.ADJUSTMENT_TYPE 
                                    AND AA_OF.ACCOUNT = TAM.ACCOUNT 
                                    AND AA_OF.PERIOD_SID = TAM.PERIOD_SID 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID) E 
              ON A.CCP_DETAILS_SID = E.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = E.RS_MODEL_SID
				 AND P.PERIOD_SID=E.PERIOD_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AA_IV.CREDIT), 0) - ISNULL(SUM(AA_IV.DEBIT), 0) AS INVENTORY_VALULATION
                  FROM   CTE TAM 
                         INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                 ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                    AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                         INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_IV 
                                 ON AAC_IV.ARM_ADJUSTMENT_CONFIG_SID = TAM.INVENTORY_VALULATION
                                    AND AAC_IV.REDEMPTION_PERIOD = 1 
                         LEFT JOIN ARM_ADJUSTMENTS AA_IV 
                                ON AA_IV.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                   AND AA_IV.ADJUSTMENT_TYPE = TAM.INVENTORY_VALULATION 
                                   AND TAM.INVENTORY_VALULATION = TAP_D.ADJUSTMENT_TYPE 
                                   AND AA_IV.ACCOUNT = TAM.ACCOUNT 
                                   AND AA_IV.PERIOD_SID = TAM.PERIOD_SID 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID) F 
              ON A.CCP_DETAILS_SID = F.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = F.RS_MODEL_SID
				 AND P.PERIOD_SID=F.PERIOD_SID
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AA_DPR.CREDIT), 0) - ISNULL(SUM(AA_DPR.DEBIT), 0) AS DEMAND_PAYMENT_TRUE_UP
                  FROM   CTE TAM 
                         INNER JOIN #TEMP_ARM_APPRVD_PROJ_START TAP_D 
                                 ON TAP_D.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                                    AND TAP_D.RS_MODEL_SID = TAM.RS_MODEL_SID 
                         INNER JOIN ARM_ADJUSTMENT_CONFIG AAC_DPR 
                                 ON AAC_DPR.ARM_ADJUSTMENT_CONFIG_SID = TAM.DEMAND_PAYMENT_TRUE_UP
                                    AND AAC_DPR.REDEMPTION_PERIOD = 1 
                         LEFT JOIN ARM_ADJUSTMENTS AA_DPR 
                                ON AA_DPR.ARM_ADJUSTMENT_DETAILS_SID = TAP_D.ARM_ADJUSTMENT_DETAILS_SID
                                   AND AA_DPR.ADJUSTMENT_TYPE = TAM.DEMAND_PAYMENT_TRUE_UP 
                                   AND TAM.DEMAND_PAYMENT_TRUE_UP = TAP_D.ADJUSTMENT_TYPE 
                                   AND AA_DPR.ACCOUNT = TAM.ACCOUNT 
                                   AND AA_DPR.PERIOD_SID = TAM.PERIOD_SID 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID) G 
              ON A.CCP_DETAILS_SID = G.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = G.RS_MODEL_SID
				 AND P.PERIOD_SID=G.PERIOD_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                                  TAM.RS_MODEL_SID, 
                                  P.PERIOD_SID,
                                  SUM(DISCOUNT) AS ACTUALS_PAYMENTS
								 FROM 
									#TEMP_ARM_PROJ_MASTER TAM 
				 INNER JOIN PERIOD P  ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @TO_PERIOD_SID 
				 INNER JOIN ACTUALS_DETAILS ACT 
                                        ON TAM.CCP_DETAILS_SID=ACT.CCP_DETAILS_SID 
                                            AND TAM.RS_MODEL_SID=ACT.RS_MODEL_SID
                                            AND ACT.CASH_PAID_PERIOD<=@TO_PERIOD_SID
                                            AND P.PERIOD_SID=ACT.PERIOD_SID
GROUP  BY TAM.CCP_DETAILS_SID, 
          TAM.RS_MODEL_SID,
          P.PERIOD_SID) H 
              ON A.CCP_DETAILS_SID = H.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = H.RS_MODEL_SID
				 AND P.PERIOD_SID=H.PERIOD_SID 

          --------------------TAKING  HISTORY RECORDS FOR PERIOD BALANCE STARTS HERE 
      	;WITH CTE 
     AS (SELECT TAM.CCP_DETAILS_SID, 
                TAM.RS_MODEL_SID,
				P.PERIOD_SID, 
	            AHM.ARM_HIST_ADJ_RES_CON_MASTER_SID,
				BSC.FEES_ACCRUAL,
				BSC.INFLATION_ADJ,
				BSC.CREDIT_CARD_FEES,
				BSC.OTHER_FIXED_DOLLAR_FEES,
				BSC.INVENTORY_VALULATION,
				BSC.DEMAND_PAYMENT_TRUE_UP,
				BSC.ACCOUNT
         FROM   #TEMP_ARM_PROJ_MASTER TAM 
                 INNER JOIN #ARM_BSR_CCP BSC 
                         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = BSC.ARM_ADJUSTMENT_DETAILS_SID 
                 INNER JOIN PERIOD P 
                         ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @TO_PERIOD_SID 
                 INNER JOIN RS_CONTRACT R 
                         ON R.RS_MODEL_SID = TAM.RS_MODEL_SID 
                            AND R.CONTRACT_MASTER_SID = TAM.CONTRACT_MASTER_SID 
                 INNER JOIN ARM_DEDUCTION_SELECTION ADS 
                         ON ADS.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
                            AND ADS.RS_CONTRACT_SID = R.RS_CONTRACT_SID 
                 INNER JOIN ARM_HIST_ADJ_RES_CON_MASTER AHM 
                         ON AHM.RS_CATEGORY = R.RS_CATEGORY 
                            AND AHM.RS_TYPE = R.RS_TYPE 
                            AND AHM.REBATE_PROGRAM_TYPE = R.REBATE_PROGRAM_TYPE 
                            AND AHM.GL_COMPANY_MASTER_SID = @GL_COMPANY 
                            AND AHM.BU_COMPANY_MASTER_SID = @BU_COMPANY 
                            AND CONFIGURATION_TYPE = 0  ) 
      INSERT INTO #TEMP_HIST_PERIOD_BALANCE 
            (CCP_DETAILS_SID, 
             RS_MODEL_SID, 
             PERIOD_SID, 
             FEES_ACCRUAL, 
             INFLATION_ADJ, 
             CREDIT_CARD_FEES, 
             OTHER_FIXED_DOLLAR_FEES, 
             INVENTORY_VALULATION, 
             DEMAND_PAYMENT_TRUE_UP) 
SELECT A.CCP_DETAILS_SID, 
       A.RS_MODEL_SID, 
       P.PERIOD_SID, 
       B.FEES_ACCRUAL, 
       C.INFLATION_ADJ, 
       D.CREDIT_CARD_FEES, 
       E.OTHER_FIXED_DOLLAR_FEES, 
       F.INVENTORY_VALULATION, 
       G.DEMAND_PAYMENT_TRUE_UP 
FROM   #TEMP_ARM_PROJ_MASTER A 
       INNER JOIN PERIOD P 
               ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @TO_PERIOD_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AH_FA.CREDIT), 0) - ISNULL(SUM(AH_FA.DEBIT), 0) AS FEES_ACCRUAL
                  FROM   CTE TAM 
                         JOIN ARM_HIST_ADJUSTMENTS AH_FA 
                           ON AH_FA.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                              AND AH_FA.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                              AND AH_FA.RS_MODEL_SID = TAM.RS_MODEL_SID
							  AND AH_FA.GL_PERIOD_SID<=@TO_PERIOD_SID 
                              AND AH_FA.CALCULATION_PERIOD_SID = TAM.PERIOD_SID 
                              AND AH_FA.ADJUSTMENT_TYPE = TAM.FEES_ACCRUAL 
                              AND AH_FA.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID) B 
              ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = B.RS_MODEL_SID 
                 AND P.PERIOD_SID = B.PERIOD_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AH_IA.CREDIT), 0) - ISNULL(SUM(AH_IA.DEBIT), 0) AS INFLATION_ADJ
                  FROM   CTE TAM 
                         JOIN ARM_HIST_ADJUSTMENTS AH_IA 
                           ON AH_IA.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                              AND AH_IA.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                              AND AH_IA.RS_MODEL_SID = TAM.RS_MODEL_SID 
							  AND AH_IA.GL_PERIOD_SID<=@TO_PERIOD_SID 
                              AND AH_IA.CALCULATION_PERIOD_SID = TAM.PERIOD_SID 
                              AND AH_IA.ADJUSTMENT_TYPE = TAM.INFLATION_ADJ 
                              AND AH_IA.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID) C 
              ON A.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = C.RS_MODEL_SID 
                 AND P.PERIOD_SID = C.PERIOD_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AH_CC.CREDIT), 0) - ISNULL(SUM(AH_CC.DEBIT), 0) AS CREDIT_CARD_FEES
                  FROM   CTE TAM 
                         JOIN ARM_HIST_ADJUSTMENTS AH_CC 
                           ON AH_CC.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                              AND AH_CC.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                              AND AH_CC.RS_MODEL_SID = TAM.RS_MODEL_SID 
							  AND AH_CC.GL_PERIOD_SID<=@TO_PERIOD_SID 
                              AND AH_CC.CALCULATION_PERIOD_SID = TAM.PERIOD_SID 
                              AND AH_CC.ADJUSTMENT_TYPE = TAM.CREDIT_CARD_FEES 
                              AND AH_CC.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID)D 
              ON A.CCP_DETAILS_SID = D.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = D.RS_MODEL_SID 
                 AND P.PERIOD_SID = D.PERIOD_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AH_OF.CREDIT), 0) - ISNULL(SUM(AH_OF.DEBIT), 0) AS OTHER_FIXED_DOLLAR_FEES
                  FROM   CTE TAM 
                         JOIN ARM_HIST_ADJUSTMENTS AH_OF 
                           ON AH_OF.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                              AND AH_OF.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                              AND AH_OF.RS_MODEL_SID = TAM.RS_MODEL_SID 
							  AND AH_OF.GL_PERIOD_SID<=@TO_PERIOD_SID 
                              AND AH_OF.CALCULATION_PERIOD_SID = TAM.PERIOD_SID 
                              AND AH_OF.ADJUSTMENT_TYPE = TAM.OTHER_FIXED_DOLLAR_FEES 
                              AND AH_OF.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID) E 
              ON A.CCP_DETAILS_SID = E.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = E.RS_MODEL_SID 
                 AND P.PERIOD_SID = E.PERIOD_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AH_IV.CREDIT), 0) - ISNULL(SUM(AH_IV.DEBIT), 0) AS INVENTORY_VALULATION
                  FROM   CTE TAM 
                         JOIN ARM_HIST_ADJUSTMENTS AH_IV 
                           ON AH_IV.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                              AND AH_IV.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                              AND AH_IV.RS_MODEL_SID = TAM.RS_MODEL_SID 
							  AND AH_IV.GL_PERIOD_SID<=@TO_PERIOD_SID
                              AND AH_IV.CALCULATION_PERIOD_SID = TAM.PERIOD_SID 
                              AND AH_IV.ADJUSTMENT_TYPE = TAM.INVENTORY_VALULATION 
                              AND AH_IV.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID) F 
              ON A.CCP_DETAILS_SID = F.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = F.RS_MODEL_SID 
                 AND P.PERIOD_SID = F.PERIOD_SID 
       LEFT JOIN (SELECT TAM.CCP_DETAILS_SID, 
                         TAM.RS_MODEL_SID, 
                         TAM.PERIOD_SID, 
                         ISNULL(SUM(AH_DPR.CREDIT), 0) - ISNULL(SUM(AH_DPR.DEBIT), 0) AS DEMAND_PAYMENT_TRUE_UP
                  FROM   CTE TAM 
                         JOIN ARM_HIST_ADJUSTMENTS AH_DPR 
                           ON AH_DPR.ARM_HIST_ADJ_RES_CON_MASTER_SID = TAM.ARM_HIST_ADJ_RES_CON_MASTER_SID
                              AND AH_DPR.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID 
                              AND AH_DPR.RS_MODEL_SID = TAM.RS_MODEL_SID 
							  AND AH_DPR.GL_PERIOD_SID<=@TO_PERIOD_SID
                              AND AH_DPR.CALCULATION_PERIOD_SID = TAM.PERIOD_SID 
                              AND AH_DPR.ADJUSTMENT_TYPE = TAM.DEMAND_PAYMENT_TRUE_UP 
                              AND AH_DPR.ACCOUNT = TAM.ACCOUNT 
                  GROUP  BY TAM.CCP_DETAILS_SID, 
                            TAM.RS_MODEL_SID, 
                            TAM.PERIOD_SID)G 
              ON A.CCP_DETAILS_SID = G.CCP_DETAILS_SID 
                 AND A.RS_MODEL_SID = G.RS_MODEL_SID 
                 AND P.PERIOD_SID = G.PERIOD_SID 

          ---------------------PERIOD BALANCE CALCULATION INCLUDING HISTORY DEBITS AND CREDITS CALCULATION STARTS HERE  
          SET @SQL='' 
          SET @SQL=CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_BSR_SINGLE_LIABILITY, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A   
		                                           ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID )
												      BEGIN  
													              TRUNCATE TABLE ', @ARM_BSR_SINGLE_LIABILITY, '  
													   END 
		 INSERT INTO ', @ARM_BSR_SINGLE_LIABILITY, '  
													     (ARM_ADJUSTMENT_DETAILS_SID,
														  PERIOD_SID, 
														  FEES_ACCRUAL, 
														  INFLATION_ADJ,
														  CREDIT_CARD_FEES,
														  OTHER_FIXED_DOLLAR_FEES, 
														  INVENTORY_VALULATION,
														  DEMAND_PAYMENT_TRUE_UP,
														  ACTUALS_PAYMENTS,
														  PERIOD_BALANCE)  
		 SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
       P.PERIOD_SID, 
       ISNULL(TAP.FEES_ACCRUAL, 0) 
       + ISNULL(THB.FEES_ACCRUAL, 0)                                                 AS FEES_ACCRUAL,
       ISNULL(TAP.INFLATION_ADJ, 0) 
       + ISNULL(THB.INFLATION_ADJ, 0)                                                AS INFLATION_ADJ,
       ISNULL(TAP.CREDIT_CARD_FEES, 0) 
       + ISNULL(THB.CREDIT_CARD_FEES, 0)                                             AS CREDIT_CARD_FEES,
       ISNULL(TAP.OTHER_FIXED_DOLLAR_FEES, 0) 
       + ISNULL(THB.OTHER_FIXED_DOLLAR_FEES, 0)                                      AS OTHER_FIXED_DOLLAR_FEES,
       ISNULL(TAP.INVENTORY_VALULATION, 0) 
       + ISNULL(THB.INVENTORY_VALULATION, 0)                                         AS INVENTORY_VALULATION,
       ISNULL(TAP.DEMAND_PAYMENT_TRUE_UP, 0) 
       + ISNULL(THB.DEMAND_PAYMENT_TRUE_UP, 0)                                       AS DEMAND_PAYMENT_TRUE_UP,
       ISNULL(TAP.ACTUALS_PAYMENTS, 0), 
       (ISNULL(TAP.FEES_ACCRUAL, 0)  + ISNULL(THB.FEES_ACCRUAL, 0)   + ISNULL(TAP.INFLATION_ADJ, 0)   + ISNULL(THB.INFLATION_ADJ, 0)  + ISNULL(TAP.CREDIT_CARD_FEES, 0)  + ISNULL(THB.CREDIT_CARD_FEES, 0)   + ISNULL(TAP.OTHER_FIXED_DOLLAR_FEES, 0)  + ISNULL(THB.OTHER_FIXED_DOLLAR_FEES, 0)  + ISNULL(TAP.INVENTORY_VALULATION, 0)  + ISNULL(THB.INVENTORY_VALULATION, 0)  + ISNULL(TAP.DEMAND_PAYMENT_TRUE_UP, 0)  + ISNULL(THB.DEMAND_PAYMENT_TRUE_UP, 0) ) - ISNULL(TAP.ACTUALS_PAYMENTS, 0) AS PERIOD_BALANCE
FROM   #TEMP_ARM_PROJ_MASTER TAM 
       JOIN ARM_ADJUSTMENT_DETAILS AD 
         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AD.ARM_ADJUSTMENT_DETAILS_SID 
       INNER JOIN PERIOD P 
               ON P.PERIOD_SID BETWEEN ', @START_PERIOD_SID, ' AND ', @TO_PERIOD_SID, ' 
       LEFT JOIN #TEMP_ACTIVE_PERIOD_BALANCE TAP 
              ON TAM.CCP_DETAILS_SID = TAP.CCP_DETAILS_SID 
                 AND TAM.RS_MODEL_SID = TAP.RS_MODEL_SID 
                 AND P.PERIOD_SID = TAP.PERIOD_SID 
       LEFT JOIN #TEMP_HIST_PERIOD_BALANCE THB 
              ON TAM.CCP_DETAILS_SID = THB.CCP_DETAILS_SID 
                 AND TAM.RS_MODEL_SID = THB.RS_MODEL_SID 
                 AND P.PERIOD_SID = THB.PERIOD_SID ') 

          EXEC SP_EXECUTESQL 
            @SQL 
      END TRY 

      BEGIN CATCH 
          DECLARE @ERRORMESSAGE NVARCHAR(4000); 
          DECLARE @ERRORSEVERITY INT; 
          DECLARE @ERRORSTATE INT; 
          DECLARE @ERRORNUMBER INT; 
          DECLARE @ERRORPROCEDURE VARCHAR(200); 
          DECLARE @ERRORLINE INT; 

          EXEC [dbo].USPERRORCOLLECTOR 

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