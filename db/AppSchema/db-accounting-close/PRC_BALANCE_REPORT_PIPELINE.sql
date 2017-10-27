IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_BALANCE_REPORT_PIPELINE' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].[PRC_BALANCE_REPORT_PIPELINE] 
  END 

GO 

CREATE PROCEDURE [dbo].[PRC_BALANCE_REPORT_PIPELINE](@PROJECTION_MASTER_SID          INT, 
                                             @CALCULATION_PROFILE_MASTER_SID INT,
											 @USER_ID INT,
											 @SESSION_ID INT) 
AS 

/**********************************************************************************************************
** File Name		:	PRC_BALANCE_REPORT_PIPELINE.sql
** Procedure Name	:	PRC_BALANCE_REPORT_PIPELINE
** Description		:	To generate Balance Report Pipeline Screen
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Balance Report
	                    @CALCULATION_PROFILE_MASTER_SID - User Selected Clauclation Profile in Data Selection 
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
											
** Output Parameters:	NA
** Author Name		:	@Reddy,@Venkatesh
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application Balance Report Pipeline. 
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------

*********************************************************************************************************/

  BEGIN 
      SET NOCOUNT ON 
	  BEGIN TRY
-- Variables Initialization starts here
	 DECLARE 
        @START_PERIOD_SID               INT,
        @BU_COMPANY                     INT,
        @GL_COMPANY                     INT,
        @TO_PERIOD_SID                  INT,
        @TO_DATE                        DATETIME,
        @FROM_DATE                      DATETIME
      DECLARE @SQL NVARCHAR(MAX) 
		DECLARE @BLN_PIPELINE_PERIOD_TABLE    VARCHAR(200) = CONCAT('ST_BALANCE_REPORT_PIPELINE_PERIOD_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		        @BLN_PIPELINE_CUM_TABLE               VARCHAR(200)= CONCAT('ST_BALANCE_REPORT_PIPELINE_CUM_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
-- Variables Initialization Ends here

 --taking price related period selected in data selection and assigning GL and BU to variable starts here 
				 
SELECT @START_PERIOD_SID = PERIOD_SID
FROM   PERIOD P
       JOIN PROJECTION_MASTER PM
         ON P.PERIOD_DATE = CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(PM.FROM_DATE, 0))))
WHERE  PM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
 
SELECT @TO_DATE = Dateadd(DD, 1, Eomonth(TO_DATE, -1)),
       @FROM_DATE = Dateadd(DD, 1, Eomonth(FROM_DATE, -1)),
       @GL_COMPANY = COMPANY_MASTER_SID
FROM   PROJECTION_MASTER
WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
 
SET @START_PERIOD_SID= (SELECT PERIOD_SID
                        FROM   PERIOD
                        WHERE  PERIOD_DATE = @FROM_DATE)
SET @TO_PERIOD_SID= (SELECT PERIOD_SID
                     FROM   PERIOD
                     WHERE  PERIOD_DATE = @TO_DATE)
 
SELECT @BU_COMPANY = BU_COMPANY_MASTER_SID
FROM   ARM_ADJUSTMENT_MASTER
WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

 --taking price related period selected in data selection and assigning GL and BU to variable Ends here 
 
--PULLING CCP+D COMBINATION FOR CURRENT PROJECTION(BALNCE REPORT) Starts here
IF Object_id('TEMPDB..#TEMP_ARM_PROJ_MASTER') IS NOT NULL
  DROP TABLE #TEMP_ARM_PROJ_MASTER
 
CREATE TABLE #TEMP_ARM_PROJ_MASTER
  (
     ARM_ADJUSTMENT_DETAILS_SID INT,
     CCP_DETAILS_SID            INT,
     RS_MODEL_SID               INT,
     CONTRACT_MASTER_SID        INT,
     COMPANY_MASTER_SID         INT,
     ITEM_MASTER_SID            INT,
     RS_ID                      VARCHAR(50)
     PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID)
  )
 
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
FROM   ARM_ADJUSTMENT_DETAILS A
       JOIN CCP_DETAILS B
         ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
       JOIN RS_MODEL RS
         ON RS.RS_MODEL_SID = A.RS_MODEL_SID
       JOIN ARM_ADJUSTMENT_MASTER AM
         ON AM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
WHERE  A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

--PULLING CCP+D COMBINATION FOR CURRENT PROJECTION(BALNCE REPORT) Ends here
 
-- PULLING APPROVED PROJECTIONS  BASED ON   CURRENT PROJECTION CCP+D LEVEL (BALNCE REPORT) Starts here
IF Object_id('TEMPDB..#TEMP_ARM_APPRVD_PROJ') IS NOT NULL
  DROP TABLE #TEMP_ARM_APPRVD_PROJ
 
SELECT DISTINCT AD.ARM_ADJUSTMENT_DETAILS_SID,
                AAM.PROJECTION_MASTER_SID,
                TAM.CCP_DETAILS_SID,
                TAM.RS_MODEL_SID,
                ARC.ACCOUNT,
                AAM.TRANSACTION_TYPE AS ADJUSTMENT_TYPE
INTO   #TEMP_ARM_APPRVD_PROJ
FROM   (SELECT DISTINCT AAC.ACCOUNT
        FROM   ARM_ADJ_RES_CCP AAC
               JOIN HELPER_TABLE HT
                 ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
                    AND HT.DESCRIPTION = 'LIABILITY'
               JOIN ARM_ADJUSTMENT_CONFIG AC
                 ON AAC.ADJUSTMENT_TYPE = AC.ARM_ADJUSTMENT_CONFIG_SID
                    AND AC.TRANSACTION_NAME = 'PIPELINE ACCRUAL'
               JOIN ARM_ADJUSTMENT_DETAILS AD
                 ON AAC.ARM_ADJUSTMENT_DETAILS_SID = AD.ARM_ADJUSTMENT_DETAILS_SID
               JOIN #TEMP_ARM_PROJ_MASTER TAM
                 ON TAM.CCP_DETAILS_SID = AD.CCP_DETAILS_SID
                    AND TAM.RS_MODEL_SID = AD.RS_MODEL_SID)A
       JOIN ARM_ADJ_RES_CCP ARC
         ON A.ACCOUNT = ARC.ACCOUNT
       JOIN ARM_ADJUSTMENT_DETAILS AD
         ON ARC.ARM_ADJUSTMENT_DETAILS_SID = AD.ARM_ADJUSTMENT_DETAILS_SID
       JOIN #TEMP_ARM_PROJ_MASTER TAM
         ON TAM.CCP_DETAILS_SID = AD.CCP_DETAILS_SID
            AND TAM.RS_MODEL_SID = AD.RS_MODEL_SID
       JOIN ARM_ADJUSTMENT_MASTER AAM
         ON AAM.PROJECTION_MASTER_SID = AD.PROJECTION_MASTER_SID
		 AND AAM.BU_COMPANY_MASTER_SID=@BU_COMPANY
		 JOIN PROJECTION_MASTER PM ON  AAM.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
		 AND PM.COMPANY_MASTER_SID=@GL_COMPANY
       JOIN WORKFLOW_MASTER WM
         ON WM.PROJECTION_MASTER_SID = AAM.PROJECTION_MASTER_SID
            AND WORKFLOW_ID LIKE 'ARM%'
			AND EXISTS (SELECT HELPER_TABLE_SID
                        FROM   HELPER_TABLE H1
                        WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                AND DESCRIPTION = 'APPROVED'
								AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID);
            --AND WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID
            --                           FROM   HELPER_TABLE
            --                           WHERE  LIST_NAME = 'WORKFLOWSTATUS'
            --                                  AND DESCRIPTION = 'APPROVED');

-- PULLING APPROVED PROJECTIONS  BASED ON   CURRENT PROJECTION CCP+D LEVEL (BALNCE REPORT) Starts here
 
--PULLING ADJUSTMENT_TYPES IN  CALCULATION PROFILE VIEW FOR CURRENT PROJECTION(BALNCE REPORT) Starts here
IF Object_id('TEMPDB..#TEMP_CAL_PROFILE_MASTER') IS NOT NULL
  DROP TABLE #TEMP_CAL_PROFILE_MASTER
 
SELECT PROFILE_NAME,
       PROFILE_DESCRIPTION,
       SAVE_FLAG,
       ADJUSTMENT_TYPE,
       ACCOUNT_TYPE,
       INCLUDE,
       INDICATOR,
       PRIORITY,
       --  HT.DESCRIPTION  AS BASE,  
       HT1.DESCRIPTION AS ACCOUT_TYPE_DES,
       CASE HT.DESCRIPTION
         WHEN 'TRANSACTION 1' THEN 'ARM_PIPELINE_RATE'
         WHEN 'TRANSACTION 2' THEN 'ARM_DEMAND_ADJ_SUMMARY'
         WHEN 'TRANSACTION 3' THEN 'ARM_INVENTORY_RATE'
         WHEN 'TRANSACTION 4' THEN 'ARM_DEMAND_RECON_SUMMARY'
         WHEN 'TRANSACTION 5' THEN 'ARM_DEMAND_RF_TRUE_UP_SUMMARY'
         WHEN 'TRANSACTION 6' THEN 'ARM_INFLATION_INVENTORY_ADJ'
         WHEN 'TRANSACTION 7' THEN 'ARM_DISTRIBUTION_FEES_RATE'
         ELSE 'ACTUALS_MASTER'
       END             AS SOURCE
INTO   #TEMP_CAL_PROFILE_MASTER
FROM   CALCULATION_PROFILE_MASTER CF
       INNER JOIN CALCULATION_PROFILE_DETAILS CD
               ON CD.CALCULATION_PROFILE_MASTER_SID = CF.CALCULATION_PROFILE_MASTER_SID
                  AND CF.CALCULATION_PROFILE_MASTER_SID = @CALCULATION_PROFILE_MASTER_SID
       LEFT JOIN ARM_ADJUSTMENT_CONFIG AC
              ON AC.ARM_ADJUSTMENT_CONFIG_SID = CD.ADJUSTMENT_TYPE
       LEFT JOIN HELPER_TABLE HT
              ON HT.HELPER_TABLE_SID = AC.METHODOLGY
                 AND LIST_NAME LIKE '%ARM%'
       LEFT JOIN HELPER_TABLE HT1
              ON HT1.HELPER_TABLE_SID = CD.ACCOUNT_TYPE

--PULLING ADJUSTMENT_TYPES IN  CALCULATION PROFILE VIEW FOR CURRENT PROJECTION(BALNCE REPORT) Ends here
 
--UPDATE ARM_ACTUAL_ADJ_MASTER  
--SET ADJUSTMENT_TYPE=1  

--Caluclating Debit and Credit Starts here
IF Object_id('TEMPDB..#TEMP_ADJ_MASTER') IS NOT NULL
  DROP TABLE #TEMP_ADJ_MASTER
 
CREATE TABLE #TEMP_ADJ_MASTER
  (
     ARM_ADJUSTMENT_DETAILS_SID INT,
     CCP_DETAILS_SID            INT,
     ACCOUNT                    VARCHAR(100),
     RS_MODEL_SID               INT,
     PERIOD_SID                 INT,
     ADJUSTMENT_TYPE            INT,
     AMOUNT                     NUMERIC(22, 6)
  )
 
INSERT INTO #TEMP_ADJ_MASTER
            (ARM_ADJUSTMENT_DETAILS_SID,
             ACCOUNT,
             CCP_DETAILS_SID,
             RS_MODEL_SID,
             PERIOD_SID,
             ADJUSTMENT_TYPE,
             AMOUNT)
SELECT A.ARM_ADJUSTMENT_DETAILS_SID,
       A.ACCOUNT,
       A.CCP_DETAILS_SID,
       A.RS_MODEL_SID,
       A.PERIOD_SID,
       ADJUSTMENT_TYPE,
       Isnull(Sum(CREDIT), 0) - Isnull(Sum(DEBIT), 0) AS AMOUNT
FROM   (SELECT DISTINCT TAM.ARM_ADJUSTMENT_DETAILS_SID,
                        TAM.CCP_DETAILS_SID,
                        TAM.RS_MODEL_SID,
                        REDEMPTION_DATE AS PERIOD_DATE,
                        P.PERIOD_SID,
                        DEBIT_INDICATOR,
                        CREDIT_INDICATOR,
                        AMOUNT,
                        AM.ACCOUNT,
                        CASE
                                  WHEN AMOUNT < 0
                                       AND DEBIT_INDICATOR = 1 THEN Abs(AMOUNT)
                                  WHEN AMOUNT > 0
                                       AND DEBIT_INDICATOR = 0 THEN Abs(AMOUNT)
                                END AS DEBIT,
                        CASE
                                 WHEN AMOUNT < 0
                                      AND CREDIT_INDICATOR = 1 THEN Abs(AMOUNT)
                                 WHEN AMOUNT > 0
                                      AND CREDIT_INDICATOR = 0 THEN Abs(AMOUNT)
                               END AS CREDIT,
                        AM.ADJUSTMENT_TYPE
        FROM   ARM_ACTUAL_ADJ_MASTER AM
               JOIN CONTRACT_MASTER CON
                 ON AM.CONTRACT_ID = CON.CONTRACT_ID
               JOIN COMPANY_MASTER CM
                 ON AM.COMPANY_ID = CM.COMPANY_ID
               JOIN ITEM_MASTER IM
                 ON AM.ITEM_ID = IM.ITEM_ID
               JOIN #TEMP_ARM_PROJ_MASTER TAM
                 ON TAM.CONTRACT_MASTER_SID = CON.CONTRACT_MASTER_SID
                    AND TAM.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID
                    AND TAM.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
               JOIN #TEMP_ARM_APPRVD_PROJ TA
                 ON TA.CCP_DETAILS_SID = TAM.CCP_DETAILS_SID
                    AND TA.RS_MODEL_SID = TAM.RS_MODEL_SID
                    AND TA.ACCOUNT = AM.ACCOUNT
               JOIN RS_MODEL RS
                 ON RS.RS_CATEGORY = AM.RS_CATEGORY
                    AND RS.RS_TYPE = AM.RS_TYPE
                    AND RS.REBATE_PROGRAM_TYPE = AM.REBATE_PROGRAM_TYPE
                    AND RS.RS_MODEL_SID = TAM.RS_MODEL_SID
               JOIN PERIOD P
                 ON P.PERIOD_DATE = Cast(AM.REDEMPTION_DATE AS DATE))A
GROUP  BY A.ARM_ADJUSTMENT_DETAILS_SID,
          CCP_DETAILS_SID,
          ADJUSTMENT_TYPE,
          A.ACCOUNT,
          RS_MODEL_SID,
          PERIOD_SID;
--Caluclating Debit and Credit Ends here

--taking Liability and Expense amount for all Transactions Starts here
 
IF Object_id('TEMPDB..#TEMP_TRANSACTION_TABLE') IS NOT NULL
  DROP TABLE #TEMP_TRANSACTION_TABLE;
 
SELECT TA.ARM_ADJUSTMENT_DETAILS_SID,
       TA.CCP_DETAILS_SID,
       TA.RS_MODEL_SID,
       COALESCE(AR.PERIOD_SID, ADJ.PERIOD_SID, AIR.PERIOD_SID, ADRS.PERIOD_SID, ADRF.PERIOD_SID, AIA.PERIOD_SID, ADR.PERIOD_SID) AS PERIOD_SID,
       TA.ADJUSTMENT_TYPE,
       COALESCE(AR.VARIANCE, AR.OVERRIDE, 0)                                                                                     AR_AMOUNT,
       COALESCE(ADJ.VARIANCE, ADJ.OVERRIDE, 0)                                                                                   ADJ_AMOUNT,
       COALESCE(AIR.VARIANCE, AIR.OVERRIDE, 0)                                                                                   AIR_AMOUNT,
       COALESCE(ADRS.VARIANCE, ADRS.OVERRIDE, 0)                                                                                 ADRS_AMOUNT,
       COALESCE(ADRF.VARIANCE, ADRF.OVERRIDE, 0)                                                                                 ADRF_AMOUNT,
       COALESCE(AIA.VARIANCE, AIA.OVERRIDE, 0)                                                                                   AIA_AMOUNT,
       COALESCE(ADR.VARIANCE, ADR.OVERRIDE, 0)                                                                                   ADR_AMOUNT,
       Isnull(AR.LIABILITY_AMOUNT, 0)                                                                                            AR_LIABILITY_AMOUNT,
       Isnull(AR.EXPENSE_AMOUNT, 0)                                                                                              AR_EXPENSE_AMOUNT,
       Isnull(ADJ.LIABILITY_AMOUNT, 0)                                                                                           ADJ_LIABILITY_AMOUNT,
       Isnull(ADJ.EXPENSE_AMOUNT, 0)                                                                                             ADJ_EXPENSE_AMOUNT,
       Isnull(AIR.LIABILITY_AMOUNT, 0)                                                                                           AIR_LIABILITY_AMOUNT,
       Isnull(AIR.EXPENSE_AMOUNT, 0)                                                                                             AIR_EXPENSE_AMOUNT,
       Isnull(ADRS.LIABILITY_AMOUNT, 0)                                                                                          ADRS_LIABILITY_AMOUNT,
       Isnull(ADRS.EXPENSE_AMOUNT, 0)                                                                                            ADRS_EXPENSE_AMOUNT,
       Isnull(ADRF.LIABILITY_AMOUNT, 0)                                                                                          ADRF_LIABILITY_AMOUNT,
       Isnull(ADRF.EXPENSE_AMOUNT, 0)                                                                                            ADRF_EXPENSE_AMOUNT,
       Isnull(AIA.LIABILITY_AMOUNT, 0)                                                                                           AIA_LIABILITY_AMOUNT,
       Isnull(AIA.EXPENSE_AMOUNT, 0)                                                                                             AIA_EXPENSE_AMOUNT,
       Isnull(ADR.LIABILITY_AMOUNT, 0)                                                                                           ADR_LIABILITY_AMOUNT,
       Isnull(ADR.EXPENSE_AMOUNT, 0)                                                                                             ADR_EXPENSE_AMOUNT
INTO   #TEMP_TRANSACTION_TABLE
FROM   (SELECT DISTINCT ARM_ADJUSTMENT_DETAILS_SID,
                        CCP_DETAILS_SID,
                        RS_MODEL_SID,
                        ADJUSTMENT_TYPE
        FROM   #TEMP_ARM_APPRVD_PROJ) TA
       LEFT JOIN ARM_PIPELINE_RATE AR
              ON TA.ARM_ADJUSTMENT_DETAILS_SID = AR.ARM_ADJUSTMENT_DETAILS_SID
                 AND AR.PERIOD_SID <= @TO_PERIOD_SID
       LEFT JOIN ARM_DEMAND_ADJ_SUMMARY ADJ
              ON TA.ARM_ADJUSTMENT_DETAILS_SID = ADJ.ARM_ADJUSTMENT_DETAILS_SID
                 AND ADJ.PERIOD_SID <= @TO_PERIOD_SID
       LEFT JOIN ARM_INVENTORY_RATE AIR
              ON TA.ARM_ADJUSTMENT_DETAILS_SID = AIR.ARM_ADJUSTMENT_DETAILS_SID
                 AND AIR.PERIOD_SID <= @TO_PERIOD_SID
       LEFT JOIN ARM_DEMAND_RECON_SUMMARY ADRS
              ON TA.ARM_ADJUSTMENT_DETAILS_SID = ADRS.ARM_ADJUSTMENT_DETAILS_SID
                 AND ADRS.PERIOD_SID <= @TO_PERIOD_SID
       LEFT JOIN ARM_DEMAND_RF_TRUE_UP_SUMMARY ADRF
              ON TA.ARM_ADJUSTMENT_DETAILS_SID = ADRF.ARM_ADJUSTMENT_DETAILS_SID
                 AND ADRF.PERIOD_SID <= @TO_PERIOD_SID
       LEFT JOIN ARM_INFLATION_INVENTORY_ADJ AIA
              ON TA.ARM_ADJUSTMENT_DETAILS_SID = AIA.ARM_ADJUSTMENT_DETAILS_SID
                 AND AIA.PERIOD_SID <= @TO_PERIOD_SID
       LEFT JOIN ARM_DISTRIBUTION_FEES_RATE ADR
              ON TA.ARM_ADJUSTMENT_DETAILS_SID = ADR.ARM_ADJUSTMENT_DETAILS_SID
                 AND ADR.PERIOD_SID <= @TO_PERIOD_SID;
 
 --taking Liability and Expense amount for all Transactions Starts here

 --Calculating Starting Balnce Starts here

IF Object_id('TEMPDB..#ARM_STARTING_BALANCE') IS NOT NULL
  DROP TABLE #ARM_STARTING_BALANCE;
 
;
WITH CTE
     AS (SELECT TA.CCP_DETAILS_SID,
                TA.RS_MODEL_SID,
                ARC.ACCOUNT,
                PERIOD_SID,
                TAJ.ADJUSTMENT_TYPE,
                CASE
                  WHEN AR_AMOUNT < 0
                       AND ARC.DEBIT = 1 THEN Abs(AR_AMOUNT)
                  WHEN AR_AMOUNT > 0
                       AND ARC.DEBIT = 0 THEN Abs(AR_AMOUNT)
                END  AR_AMOUNT_DEBIT,
                CASE
                  WHEN AR_AMOUNT < 0
                       AND ARC.CREDIT = 1 THEN Abs(AR_AMOUNT)
                  WHEN AR_AMOUNT > 0
                       AND ARC.CREDIT = 0 THEN Abs(AR_AMOUNT)
                END  AR_AMOUNT_CREDIT,
                CASE
                  WHEN ADJ_AMOUNT < 0
                       AND ARC.DEBIT = 1 THEN Abs(ADJ_AMOUNT)
                  WHEN ADJ_AMOUNT > 0
                       AND ARC.DEBIT = 0 THEN Abs(ADJ_AMOUNT)
                END  ADJ_AMOUNT_DEBIT,
                CASE
                  WHEN ADJ_AMOUNT < 0
                       AND ARC.CREDIT = 1 THEN Abs(ADJ_AMOUNT)
                  WHEN ADJ_AMOUNT > 0
                       AND ARC.CREDIT = 0 THEN ADJ_AMOUNT
                END  ADJ_AMOUNT_CREDIT,
                CASE
                  WHEN AIR_AMOUNT < 0
                       AND ARC.DEBIT = 1 THEN Abs(AIR_AMOUNT)
                  WHEN AIR_AMOUNT > 0
                       AND ARC.DEBIT = 0 THEN Abs(AIR_AMOUNT)
                END  AIR_AMOUNT_DEBIT,
                CASE
                  WHEN AIR_AMOUNT < 0
                       AND ARC.CREDIT = 1 THEN Abs(AIR_AMOUNT)
                  WHEN AIR_AMOUNT > 0
                       AND ARC.CREDIT = 0 THEN AIR_AMOUNT
                END  AIR_AMOUNT_CREDIT,
                CASE
                  WHEN ADRS_AMOUNT < 0
                       AND ARC.DEBIT = 1 THEN Abs(ADRS_AMOUNT)
                  WHEN ADRS_AMOUNT > 0
                       AND ARC.DEBIT = 0 THEN Abs(ADRS_AMOUNT)
                END  ADRS_AMOUNT_DEBIT,
                CASE
                  WHEN ADRS_AMOUNT < 0
                       AND ARC.CREDIT = 1 THEN Abs(ADRS_AMOUNT)
                  WHEN ADRS_AMOUNT > 0
                       AND ARC.CREDIT = 0 THEN ADRS_AMOUNT
                END  ADRS_AMOUNT_CREDIT,
                CASE
                  WHEN ADRF_AMOUNT < 0
                       AND ARC.DEBIT = 1 THEN Abs(ADRF_AMOUNT)
                  WHEN ADRF_AMOUNT > 0
                       AND ARC.DEBIT = 0 THEN Abs(ADRF_AMOUNT)
                END  ADRF_AMOUNT_DEBIT,
                CASE
                  WHEN ADRF_AMOUNT < 0
                       AND ARC.CREDIT = 1 THEN Abs(ADRF_AMOUNT)
                  WHEN ADRF_AMOUNT > 0
                       AND ARC.CREDIT = 0 THEN ADRF_AMOUNT
                END  ADRF_AMOUNT_CREDIT,
                CASE
                  WHEN ADR_AMOUNT < 0
                       AND ARC.DEBIT = 1 THEN Abs(ADR_AMOUNT)
                  WHEN ADR_AMOUNT > 0
                       AND ARC.DEBIT = 0 THEN Abs(ADR_AMOUNT)
                END  ADR_AMOUNT_DEBIT,
                CASE
                  WHEN ADR_AMOUNT < 0
                       AND ARC.CREDIT = 1 THEN Abs(ADR_AMOUNT)
                  WHEN ADR_AMOUNT > 0
                       AND ARC.CREDIT = 0 THEN ADR_AMOUNT
                END  ADR_AMOUNT_CREDIT,
                CASE
                  WHEN AIA_AMOUNT < 0
                       AND ARC.DEBIT = 1 THEN Abs(AIA_AMOUNT)
                  WHEN AIA_AMOUNT > 0
                       AND ARC.DEBIT = 0 THEN Abs(AIA_AMOUNT)
                END  AIA_AMOUNT_DEBIT,
                CASE
                  WHEN AIA_AMOUNT < 0
                       AND ARC.CREDIT = 1 THEN Abs(AIA_AMOUNT)
                  WHEN AIA_AMOUNT > 0
                       AND ARC.CREDIT = 0 THEN AIA_AMOUNT
                END  AIA_AMOUNT_CREDIT,
                NULL AMOUNT
         FROM   #TEMP_ARM_APPRVD_PROJ TAJ
                JOIN ARM_ADJ_RES_CCP ARC
                  ON ARC.ARM_ADJUSTMENT_DETAILS_SID = TAJ.ARM_ADJUSTMENT_DETAILS_SID
                     AND ARC.ADJUSTMENT_TYPE = TAJ.ADJUSTMENT_TYPE
                     AND ARC.ACCOUNT = TAJ.ACCOUNT
                LEFT JOIN #TEMP_TRANSACTION_TABLE TA
                       ON ARC.ARM_ADJUSTMENT_DETAILS_SID = TA.ARM_ADJUSTMENT_DETAILS_SID
                          AND ARC.ADJUSTMENT_TYPE = TA.ADJUSTMENT_TYPE
                          AND TA.PERIOD_SID < @START_PERIOD_SID
         WHERE  COALESCE(ARC.CREDIT, ARC.DEBIT) IS NOT NULL
         UNION ALL
         SELECT TAJ.CCP_DETAILS_SID,
                TAJ.RS_MODEL_SID,
                ARC.ACCOUNT,
                TB.PERIOD_SID,
                TAJ.ADJUSTMENT_TYPE,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                TB.AMOUNT
         FROM   #TEMP_ARM_APPRVD_PROJ TAJ
                JOIN ARM_ADJ_RES_CCP ARC
                  ON ARC.ARM_ADJUSTMENT_DETAILS_SID = TAJ.ARM_ADJUSTMENT_DETAILS_SID
                     AND ARC.ADJUSTMENT_TYPE = TAJ.ADJUSTMENT_TYPE
                     AND ARC.ACCOUNT = TAJ.ACCOUNT
                JOIN #TEMP_ADJ_MASTER TB
                  ON TAJ.CCP_DETAILS_SID = TB.CCP_DETAILS_SID
                     AND TAJ.RS_MODEL_SID = TB.RS_MODEL_SID
                     AND ARC.ACCOUNT = TB.ACCOUNT
                     AND ARC.ADJUSTMENT_TYPE = TB.ADJUSTMENT_TYPE
                     AND TB.PERIOD_SID < @START_PERIOD_SID),
     CTE2
     AS (SELECT CCP_DETAILS_SID,
                RS_MODEL_SID,
                PERIOD_SID,
                ADJUSTMENT_TYPE,
                Isnull(Max(AMOUNT), 0)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               AMOUNT,
                Isnull(Max(AMOUNT), 0) + ( Abs(COALESCE(Sum(AR_AMOUNT_CREDIT), 0) - COALESCE(Sum(AR_AMOUNT_DEBIT), 0)) ) + ( Abs(COALESCE(Sum(ADJ_AMOUNT_CREDIT), 0) - COALESCE(Sum(ADJ_AMOUNT_DEBIT), 0)) ) + ( Abs(COALESCE(Sum(AIR_AMOUNT_CREDIT), 0) - COALESCE(Sum(AIR_AMOUNT_DEBIT), 0)) ) + ( Abs(COALESCE(Sum(ADRS_AMOUNT_CREDIT), 0) - COALESCE(Sum(ADRS_AMOUNT_DEBIT), 0)) ) + ( Abs(COALESCE(Sum(ADRF_AMOUNT_CREDIT), 0) - COALESCE(Sum(ADRF_AMOUNT_DEBIT), 0)) ) + ( Abs(COALESCE(Sum(AIA_AMOUNT_CREDIT), 0) - COALESCE(Sum(AIA_AMOUNT_DEBIT), 0)) ) + ( Abs(COALESCE(Sum(ADR_AMOUNT_CREDIT), 0) - COALESCE(Sum(ADR_AMOUNT_DEBIT), 0)) ) AS ADJUSTED_BALANCE,
                Abs(COALESCE(Sum(AR_AMOUNT_CREDIT), 0) - COALESCE(Sum(AR_AMOUNT_DEBIT), 0))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          AR_AMOUNT,
                Abs(COALESCE(Sum(ADJ_AMOUNT_CREDIT), 0) - COALESCE(Sum(ADJ_AMOUNT_DEBIT), 0))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ADJ_AMOUNT,
                Abs(COALESCE(Sum(AIR_AMOUNT_CREDIT), 0) - COALESCE(Sum(AIR_AMOUNT_DEBIT), 0))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        AIR_AMOUNT,
                Abs(COALESCE(Sum(ADRS_AMOUNT_CREDIT), 0) - COALESCE(Sum(ADRS_AMOUNT_DEBIT), 0))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ADRS_AMOUNT,
                Abs(COALESCE(Sum(ADRF_AMOUNT_CREDIT), 0) - COALESCE(Sum(ADRF_AMOUNT_DEBIT), 0))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      ADRF_AMOUNT,
                Abs(COALESCE(Sum(AIA_AMOUNT_CREDIT), 0) - COALESCE(Sum(AIA_AMOUNT_DEBIT), 0))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        AIA_AMOUNT,
                Abs(COALESCE(Sum(ADR_AMOUNT_CREDIT), 0) - COALESCE(Sum(ADR_AMOUNT_DEBIT), 0))                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        ADR_AMOUNT,
                Dense_rank()
                  OVER (
                    PARTITION BY CCP_DETAILS_SID, RS_MODEL_SID
                    ORDER BY PERIOD_SID)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             RN
         FROM   CTE
         GROUP  BY CCP_DETAILS_SID,
                   RS_MODEL_SID,
                   ADJUSTMENT_TYPE,
                   PERIOD_SID),
     CTE3
     AS (SELECT CCP_DETAILS_SID,
                RS_MODEL_SID,
                ADJUSTMENT_TYPE,
                PERIOD_SID,
                ADJUSTED_BALANCE AS ST_BALANCE
         FROM   CTE2
         WHERE  RN = 1)
SELECT DISTINCT A.CCP_DETAILS_SID,
                A.RS_MODEL_SID,
                A.ADJUSTMENT_TYPE,
                @START_PERIOD_SID                                AS PERIOD_SID,
                Sum(A.ADJUSTED_BALANCE)
                  OVER (
                    ORDER BY A.CCP_DETAILS_SID, A.RS_MODEL_SID)  STARTING_BALANCE,
                Sum(ST_BALANCE)
                  OVER(
                    ORDER BY A.CCP_DETAILS_SID, A.RS_MODEL_SID ) CUM_STARTING_BALANCE
INTO   #ARM_STARTING_BALANCE
FROM   CTE2 A
       LEFT JOIN CTE3 B
              ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                 AND A.RS_MODEL_SID = B.RS_MODEL_SID
                 AND A.PERIOD_SID = B.PERIOD_SID;

 --Calculating Starting Balnce Ends here

--Calculating Laibility amount and Expense amount for Cummulative starts here

IF Object_id('TEMPDB..#TEMP_CALCULATION') IS NOT NULL
  DROP TABLE #TEMP_CALCULATION;
 
WITH CTE
     AS (SELECT TAJ.CCP_DETAILS_SID,
                TAJ.RS_MODEL_SID,
                TAJ.ADJUSTMENT_TYPE,
                PERIOD_SID,
                NULL AS AMOUNT,
                AR_LIABILITY_AMOUNT,
                AR_EXPENSE_AMOUNT,
                ADJ_LIABILITY_AMOUNT,
                ADJ_EXPENSE_AMOUNT,
                AIR_LIABILITY_AMOUNT,
                AIR_EXPENSE_AMOUNT,
                ADRS_LIABILITY_AMOUNT,
                ADRS_EXPENSE_AMOUNT,
                ADRF_LIABILITY_AMOUNT,
                ADRF_EXPENSE_AMOUNT,
                AIA_LIABILITY_AMOUNT,
                AIA_EXPENSE_AMOUNT,
                ADR_LIABILITY_AMOUNT,
                ADR_EXPENSE_AMOUNT
         FROM   #TEMP_ARM_APPRVD_PROJ TAJ
                LEFT JOIN #TEMP_TRANSACTION_TABLE TA
                       ON TAJ.ARM_ADJUSTMENT_DETAILS_SID = TA.ARM_ADJUSTMENT_DETAILS_SID
                          AND TAJ.CCP_DETAILS_SID = TA.CCP_DETAILS_SID
                          AND TAJ.RS_MODEL_SID = TA.RS_MODEL_SID
                          AND TAJ.ADJUSTMENT_TYPE = TA.ADJUSTMENT_TYPE
                          AND TA.PERIOD_SID <= @TO_PERIOD_SID
         UNION ALL
         SELECT CCP_DETAILS_SID,
                RS_MODEL_SID,
                ADJUSTMENT_TYPE,
                PERIOD_SID,
                MAX(AMOUNT) AMOUNT,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL,
                NULL
         FROM   #TEMP_ADJ_MASTER 
                 WHERE PERIOD_SID <= @TO_PERIOD_SID
				 GROUP BY CCP_DETAILS_SID,
                RS_MODEL_SID,
                ADJUSTMENT_TYPE, PERIOD_SID)
				SELECT CCP_DETAILS_SID,
                RS_MODEL_SID,
                ADJUSTMENT_TYPE,
                PERIOD_SID,
				Isnull(AMOUNT, 0)AMOUNT,
                Isnull(AMOUNT, 0)
                + Isnull(AR_LIABILITY_AMOUNT, 0)
                + Isnull(AR_EXPENSE_AMOUNT, 0)
                + Isnull(ADJ_LIABILITY_AMOUNT, 0)
                + Isnull(ADJ_EXPENSE_AMOUNT, 0)
                + Isnull(AIR_LIABILITY_AMOUNT, 0)
                + Isnull(AIR_EXPENSE_AMOUNT, 0)
                + Isnull(ADRS_LIABILITY_AMOUNT, 0)
                + Isnull(ADRS_EXPENSE_AMOUNT, 0)
                + Isnull(ADRF_LIABILITY_AMOUNT, 0)
                + Isnull(ADRF_EXPENSE_AMOUNT, 0)
                + Isnull(AIA_LIABILITY_AMOUNT, 0)
                + Isnull(AIA_EXPENSE_AMOUNT, 0)
                + Isnull(ADR_LIABILITY_AMOUNT, 0)
                + Isnull(ADR_EXPENSE_AMOUNT, 0)                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
				AS ADJUSTED_BALANCE ,
				 ISNULL(AR_LIABILITY_AMOUNT, 0) AR_LIABILITY_AMOUNT
                 , ISNULL(AR_EXPENSE_AMOUNT, 0) AR_EXPENSE_AMOUNT
                 , ISNULL(ADJ_LIABILITY_AMOUNT, 0) ADJ_LIABILITY_AMOUNT
                 , ISNULL(ADJ_EXPENSE_AMOUNT, 0) ADJ_EXPENSE_AMOUNT
                 , ISNULL(AIR_LIABILITY_AMOUNT, 0) AIR_LIABILITY_AMOUNT
                 , ISNULL(AIR_EXPENSE_AMOUNT, 0) AIR_EXPENSE_AMOUNT
                 , ISNULL(ADRS_LIABILITY_AMOUNT, 0) ADRS_LIABILITY_AMOUNT
                 , ISNULL(ADRS_EXPENSE_AMOUNT, 0) ADRS_EXPENSE_AMOUNT
                 , ISNULL(ADRF_LIABILITY_AMOUNT, 0) ADRF_LIABILITY_AMOUNT
                 , ISNULL(ADRF_EXPENSE_AMOUNT, 0) ADRF_EXPENSE_AMOUNT
                 , ISNULL(AIA_LIABILITY_AMOUNT, 0) AIA_LIABILITY_AMOUNT
                 , ISNULL(AIA_EXPENSE_AMOUNT, 0) AIA_EXPENSE_AMOUNT
                 , ISNULL(ADR_LIABILITY_AMOUNT, 0) ADR_LIABILITY_AMOUNT
                 , ISNULL(ADR_EXPENSE_AMOUNT, 0) ADR_EXPENSE_AMOUNT,
                Dense_rank()
                  OVER (
                    PARTITION BY CCP_DETAILS_SID,RS_MODEL_SID
                    ORDER BY PERIOD_SID) 
					                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
					 RN INTO #TEMP_CALCULATION  
         FROM   CTE

--Calculating Laibility amount and Expense amount for Cummulative Ends here

	IF Object_id('TEMPDB..#TEMP') IS NOT NULL
  DROP TABLE #TEMP;	
  	
		SELECT A.ARM_ADJUSTMENT_DETAILS_SID,A.CCP_DETAILS_SID,PRIORITY,PERIOD_DATE,SOURCE,
		CONTRACT_MASTER_SID , COMPANY_MASTER_SID 
                            , ITEM_MASTER_SID 
                            ,RS_ID,  A.RS_MODEL_SID,P.PERIOD_SID,B.ADJUSTMENT_TYPE,INDICATOR  INTO #TEMP FROM #TEMP_ARM_PROJ_MASTER A   
						  JOIN PERIOD P ON P.PERIOD_SID BETWEEN  @START_PERIOD_SID AND @TO_PERIOD_SID 
						  CROSS JOIN #TEMP_CAL_PROFILE_MASTER B  
	IF Object_id('TEMPDB..#TOTAL_PERIOD_ADJUSTMENT') IS NOT NULL
  DROP TABLE #TOTAL_PERIOD_ADJUSTMENT;	
  	
WITH CTE 
     
     AS (	
				SELECT DISTINCT C.CCP_DETAILS_SID,
                         C.RS_MODEL_SID,
                         C.PERIOD_SID                                                        AS PERIOD_SID,
                         C.ADJUSTMENT_TYPE,
						 C.INDICATOR,
						 C.PRIORITY,
                        ISNULL( Isnull(AMOUNT, 0) + CASE WHEN SOURCE = 'ARM_PIPELINE_RATE' THEN Isnull( AR_LIABILITY_AMOUNT, 0) 
				+ Isnull(AR_EXPENSE_AMOUNT, 0) ELSE 0 END + CASE WHEN SOURCE = 'ARM_DEMAND_ADJ_SUMMARY' THEN Isnull( ADJ_LIABILITY_AMOUNT, 0 ) 
				+ Isnull( ADJ_EXPENSE_AMOUNT, 0) ELSE 0 END + CASE WHEN SOURCE = 'ARM_INVENTORY_RATE' THEN Isnull(AIR_LIABILITY_AMOUNT, 0) 
				+ Isnull( AIR_EXPENSE_AMOUNT, 0) ELSE 0 END + CASE WHEN SOURCE = 'ARM_DEMAND_RECON_SUMMARY' THEN Isnull( ADRS_LIABILITY_AMOUNT, 0 ) 
				+ Isnull( ADRS_EXPENSE_AMOUNT, 0) ELSE 0 END + CASE WHEN SOURCE = 'ARM_DEMAND_RF_TRUE_UP_SUMMARY' THEN Isnull( ADRF_LIABILITY_AMOUNT, 0 ) 
				+ Isnull( ADRF_EXPENSE_AMOUNT, 0) ELSE 0 END + CASE WHEN SOURCE = 'ARM_INFLATION_INVENTORY_ADJ' THEN Isnull(AIA_LIABILITY_AMOUNT, 0) 
				+ Isnull( AIA_EXPENSE_AMOUNT, 0) ELSE 0 END + CASE WHEN SOURCE = 'ARM_DISTRIBUTION_FEES_RATE' THEN Isnull(ADR_LIABILITY_AMOUNT, 0 ) 
				+ Isnull( ADR_EXPENSE_AMOUNT, 0)ELSE 0 END,0) AS PERIOD_BALANCE  
				
         FROM  #TEMP C LEFT JOIN (SELECT DISTINCT ARM_ADJUSTMENT_DETAILS_SID,PROJECTION_MASTER_SID,CCP_DETAILS_SID,RS_MODEL_SID
		 ,ADJUSTMENT_TYPE FROM #TEMP_ARM_APPRVD_PROJ) A  
		 
				 ON A.ADJUSTMENT_TYPE=C.ADJUSTMENT_TYPE 
				LEFT JOIN  #TEMP_CALCULATION B ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID
		 AND A.RS_MODEL_SID=B.RS_MODEL_SID
		 AND C.ADJUSTMENT_TYPE=B.ADJUSTMENT_TYPE
		 AND B.PERIOD_SID   BETWEEN @START_PERIOD_SID AND  @TO_PERIOD_SID
         )
		 SELECT CCP_DETAILS_SID,
                RS_MODEL_SID,
				PERIOD_SID,
                 ADJUSTMENT_TYPE,
				ISNULL(PERIOD_BALANCE,0) PERIOD_BALANCE,
                INDICATOR,
		  ISNULL(Sum(CASE
                      WHEN INDICATOR = 1 THEN PERIOD_BALANCE
                      ELSE -PERIOD_BALANCE
                    END)
                  OVER (
                    PARTITION BY CCP_DETAILS_SID, RS_MODEL_SID,PERIOD_SID
                    ORDER BY PERIOD_SID),0) TOTAL_PERIOD_ADJUSTMENT 
					
					INTO #TOTAL_PERIOD_ADJUSTMENT FROM  CTE
					;

--Adjustment Balnce Calculation Starts here 
		IF Object_id('TEMPDB..#TOTAL_BALANCE') IS NOT NULL
  DROP TABLE #TOTAL_BALANCE;	
	WITH CTE AS
      (SELECT DISTINCT A.CCP_DETAILS_SID,
                         A.RS_MODEL_SID,
                         @START_PERIOD_SID                                                        AS PERIOD_SID,
						 A.INDICATOR,
                         A.ADJUSTMENT_TYPE,ADJUSTED_BALANCE,
                         Sum(ADJUSTED_BALANCE)
                           OVER (
                             ORDER BY A.CCP_DETAILS_SID, A.RS_MODEL_SID, B.PERIOD_SID) AS ADJUSTE_TYPE_BALANCE
         FROM   #TEMP A  LEFT JOIN  #TEMP_CALCULATION B ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID
		 AND A.RS_MODEL_SID=B.RS_MODEL_SID
		 AND A.PERIOD_SID=B.PERIOD_SID
		 AND A.PERIOD_SID<=@START_PERIOD_SID
         WHERE  RN > 1)
		 SELECT CCP_DETAILS_SID,
                RS_MODEL_SID,
				PERIOD_SID,
                ADJUSTMENT_TYPE,
				ISNULL(ADJUSTE_TYPE_BALANCE,0) ADJUSTE_TYPE_BALANCE,
		  ISNULL(Sum(CASE
                      WHEN INDICATOR = 1 THEN ISNULL(ADJUSTE_TYPE_BALANCE,0)
                      ELSE -ISNULL(ADJUSTE_TYPE_BALANCE,0)
                    END)
                  OVER (
                    PARTITION BY CCP_DETAILS_SID, RS_MODEL_SID
                    ORDER BY CCP_DETAILS_SID, RS_MODEL_SID),0) TOTAL_BALANCE
					INTO #TOTAL_BALANCE
					FROM   CTE
--Adjustment Balnce Calculation Ends here  

IF Object_id('TEMPDB..#TEMP_PERIOD_PILPELINE') IS NOT NULL
  DROP TABLE #TEMP_PERIOD_PILPELINE	

		SELECT DISTINCT A.ARM_ADJUSTMENT_DETAILS_SID,A.CCP_DETAILS_SID
		,A.RS_MODEL_SID,A.PERIOD_SID,A.ADJUSTMENT_TYPE,C.PERIOD_BALANCE,B.STARTING_BALANCE,C.TOTAL_PERIOD_ADJUSTMENT 
		INTO #TEMP_PERIOD_PILPELINE FROM #TEMP  A 
		LEFT JOIN (SELECT DISTINCT CCP_DETAILS_SID,RS_MODEL_SID,PERIOD_SID,STARTING_BALANCE
		FROM  #ARM_STARTING_BALANCE)B
		ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID AND A.RS_MODEL_SID =B.RS_MODEL_SID
		   JOIN #TOTAL_PERIOD_ADJUSTMENT C ON B.CCP_DETAILS_SID=C.CCP_DETAILS_SID AND C.RS_MODEL_SID =B.RS_MODEL_SID
		AND B.PERIOD_SID=C.PERIOD_SID
		AND C.ADJUSTMENT_TYPE=A.ADJUSTMENT_TYPE


		IF Object_id('TEMPDB..#TEMP_CUM_PILPELINE') IS NOT NULL
  DROP TABLE #TEMP_CUM_PILPELINE	

		
		SELECT DISTINCT A.ARM_ADJUSTMENT_DETAILS_SID,A.CCP_DETAILS_SID
		,A.RS_MODEL_SID,A.ADJUSTMENT_TYPE,ISNULL(C.ADJUSTE_TYPE_BALANCE,0) ADJUSTE_TYPE_BALANCE
		,ISNULL(B.CUM_STARTING_BALANCE,0) CUM_STARTING_BALANCE,C.TOTAL_BALANCE 
		INTO #TEMP_CUM_PILPELINE 
		FROM #TEMP  A 
		LEFT JOIN (SELECT DISTINCT CCP_DETAILS_SID,RS_MODEL_SID,PERIOD_SID,CUM_STARTING_BALANCE
		FROM  #ARM_STARTING_BALANCE)B
		ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID AND A.RS_MODEL_SID =B.RS_MODEL_SID
		  LEFT JOIN #TOTAL_BALANCE C ON B.CCP_DETAILS_SID=C.CCP_DETAILS_SID AND C.RS_MODEL_SID =B.RS_MODEL_SID
		AND B.PERIOD_SID=C.PERIOD_SID
		AND C.ADJUSTMENT_TYPE=A.ADJUSTMENT_TYPE;

--taking Actual Discount Amounts Starts here

		 IF OBJECT_ID('TEMPDB..#TEMP_ACTUAL_DISCOUNTS') IS NOT NULL 
                  DROP TABLE #TEMP_ACTUAL_DISCOUNTS 

                CREATE TABLE #TEMP_ACTUAL_DISCOUNTS 
                  ( 
                     ARM_ADJUSTMENT_DETAILS_SID INT, 
                     PERIOD_SID                 INT, 
                     ACTUAL_PAYMENTS            NUMERIC(22, 6), 
                     ACTUAL_RATE                NUMERIC(22, 6), 
                     PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID) 
                  ) 

                INSERT INTO #TEMP_ACTUAL_DISCOUNTS 
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             ACTUAL_PAYMENTS, 
                             ACTUAL_RATE) 
                SELECT A2.ARM_ADJUSTMENT_DETAILS_SID, 
                       A2.PERIOD_SID, 
                       SUM(SALES_AMOUNT) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                             + 1 )                                 SALES, 
                       COALESCE(( SUM(AMOUNT) / NULLIF(SUM(SALES_AMOUNT), 0) ), 0) RATE 
                FROM   ACTUALS_MASTER A1 
                       JOIN #TEMP A2 
                         ON A2.PERIOD_DATE BETWEEN A1.ACCRUAL_ACTUAL_START_DATE AND A1.ACCRUAL_ACTUAL_END_DATE
                            AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID 
                            AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID 
                            AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID 
                            AND A1.PROVISION_ID = A2.RS_ID 
                            AND DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_ACTUAL_START_DATE, -1)) >= @FROM_DATE
                            AND A1.ACCRUAL_ACTUAL_END_DATE <= @TO_DATE 
                GROUP  BY ARM_ADJUSTMENT_DETAILS_SID, 
                          PERIOD_SID, 
                          CONTRACT_ID, 
                          ACCOUNT_ID, 
                          ITEM_ID, 
                          PROVISION_ID, 
                          ACCRUAL_ACTUAL_START_DATE, 
                          ACCRUAL_ACTUAL_END_DATE, 
                          QUANTITY_INCLUSION, 
                          A1.COMPANY_MASTER_SID, 
                          A1.CONTRACT_MASTER_SID, 
                          A1.ITEM_MASTER_SID 

--taking Actual Discount Amounts Ends here

--Insertion and Deletion into main table(Cummulative) Starts here

	SET @SQL= CONCAT('IF EXISTS (SELECT 1 FROM ', @BLN_PIPELINE_CUM_TABLE, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
                                          ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
					                   BEGIN 
												TRUNCATE TABLE ', @BLN_PIPELINE_CUM_TABLE, '  
									   END

				IF EXISTS (SELECT 1 FROM ', @BLN_PIPELINE_PERIOD_TABLE, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
                                          ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
					                   BEGIN 
												TRUNCATE TABLE ', @BLN_PIPELINE_PERIOD_TABLE, '  
									   END')

    EXEC sp_executesql @SQL

	SET @SQL=''

    SET @SQL=CONCAT('
	 ;WITH CTE AS 
		  ( SELECT DISTINCT  ARM_ADJUSTMENT_DETAILS_SID,CCP_DETAILS_SID,
                                           RS_MODEL_SID,
                                           PERIOD_SID,STARTING_BALANCE,
                                           TOTAL_PERIOD_ADJUSTMENT,
										    ISNULL(Sum(TOTAL_PERIOD_ADJUSTMENT) 
											OVER (partition by CCP_DETAILS_SID, RS_MODEL_SID 
											ORDER BY  PERIOD_SID ),0)+ ISNULL(Sum(STARTING_BALANCE)
											OVER (partition by CCP_DETAILS_SID, RS_MODEL_SID 
											ORDER BY  PERIOD_SID ),0) AS ENDING_BALANCE
											 FROM (SELECT DISTINCT  ARM_ADJUSTMENT_DETAILS_SID,CCP_DETAILS_SID,
                                           RS_MODEL_SID,
                                           PERIOD_SID,STARTING_BALANCE,
                                           TOTAL_PERIOD_ADJUSTMENT FROM #TEMP_PERIOD_PILPELINE)A),CTE2 AS(
SELECT DISTINCT  ARM_ADJUSTMENT_DETAILS_SID,CCP_DETAILS_SID,
                                           RS_MODEL_SID,
                                           PERIOD_SID,
                                           TOTAL_PERIOD_ADJUSTMENT,
                          COALESCE(Lag(ENDING_BALANCE, 1)
                                     OVER( PARTITION BY CCP_DETAILS_SID, RS_MODEL_SID
                                       ORDER BY CCP_DETAILS_SID, RS_MODEL_SID), STARTING_BALANCE) AS STARTING_BALANCE,
                          ENDING_BALANCE
						  FROM CTE)
						  INSERT INTO ',@BLN_PIPELINE_PERIOD_TABLE,' 
                  (ARM_ADJUSTMENT_DETAILS_SID, 
                   STARTING_BALANCE, 
                   PERIOD_SID, 
                   ADJUSTMENT_TYPE, 
                   PERIOD_ADJUSTMENT_VALUE, 
                   ACTUAL_PAYMENT, 
                   TOTAL_PERIOD_ADJ, 
                   ENDING_BALANCE) 
			SELECT DISTINCT  A.ARM_ADJUSTMENT_DETAILS_SID,	
		    B.STARTING_BALANCE,	
		   A.PERIOD_SID,	
		   A.ADJUSTMENT_TYPE,
		   A.PERIOD_BALANCE	,
		    ISNULL(C.ACTUAL_PAYMENTS,0) ACTUAL_PAYMENT,
		   A.TOTAL_PERIOD_ADJUSTMENT,
		   B.ENDING_BALANCE FROM #TEMP_PERIOD_PILPELINE A  LEFT JOIN CTE2 B ON  A.ARM_ADJUSTMENT_DETAILS_SID=B.ARM_ADJUSTMENT_DETAILS_SID AND
		    A.CCP_DETAILS_SID=B.CCP_DETAILS_SID	 AND
		   A.RS_MODEL_SID=B.RS_MODEL_SID AND A.PERIOD_SID=B.PERIOD_SID
		   LEFT JOIN #TEMP_ACTUAL_DISCOUNTS C 
		   ON  A.ARM_ADJUSTMENT_DETAILS_SID=C.ARM_ADJUSTMENT_DETAILS_SID 
		   AND A.PERIOD_SID=C.PERIOD_SID ')

--Insertion and Deletion into main table (Cummulative) Ends here

 EXEC sp_executesql @SQL
 SET @SQL=''
	SET @SQL =CONCAT('
	    ;WITH CTE AS 
		  ( SELECT  ARM_ADJUSTMENT_DETAILS_SID,CCP_DETAILS_SID,
                                           RS_MODEL_SID,
                                           CUM_STARTING_BALANCE,
                                           TOTAL_BALANCE,
										    ISNULL(Sum(TOTAL_BALANCE) OVER (ORDER BY CCP_DETAILS_SID, RS_MODEL_SID),0)+ISNULL(CUM_STARTING_BALANCE,0) AS ENDING_BALANCE
											 FROM (SELECT DISTINCT  ARM_ADJUSTMENT_DETAILS_SID,CCP_DETAILS_SID,
                                           RS_MODEL_SID,
                                           CUM_STARTING_BALANCE,
                                           TOTAL_BALANCE from
											 #TEMP_CUM_PILPELINE)a),CTE2 AS(
           SELECT  DISTINCT ARM_ADJUSTMENT_DETAILS_SID,CCP_DETAILS_SID,
                                           RS_MODEL_SID,
                                           TOTAL_BALANCE,
                          COALESCE(Lag(ENDING_BALANCE, 1)
                                     OVER(PARTITION BY CCP_DETAILS_SID, RS_MODEL_SID
                                       ORDER BY CCP_DETAILS_SID, RS_MODEL_SID), CUM_STARTING_BALANCE) AS CUM_STARTING_BALANCE,
                          ENDING_BALANCE
						  FROM CTE)
			INSERT INTO ',@BLN_PIPELINE_CUM_TABLE,' 
                  (ARM_ADJUSTMENT_DETAILS_SID, 
                   STARTING_BALANCE, 
                   ADJUSTMENT_TYPE, 
                   CUM_ADJUSTMENT_VALUE, 
                   TOTAL_CUM_ADJ, 
                   ENDING_BALANCE) 	   			
		   SELECT A.ARM_ADJUSTMENT_DETAILS_SID,	
		    B.CUM_STARTING_BALANCE,		
		   A.ADJUSTMENT_TYPE,
		   A.ADJUSTE_TYPE_BALANCE	,
		   A.TOTAL_BALANCE,
		   B.ENDING_BALANCE FROM #TEMP_CUM_PILPELINE A LEFT JOIN CTE2 B ON  A.ARM_ADJUSTMENT_DETAILS_SID=B.ARM_ADJUSTMENT_DETAILS_SID AND
		   A.CCP_DETAILS_SID=B.CCP_DETAILS_SID	 AND
		   A.RS_MODEL_SID=B.RS_MODEL_SID ')
		   EXEC sp_executesql @SQL
----------------------------------------------Actual Payment Calculation starts here--------------------------------------------

	
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

          RAISERROR ( @ERRORMESSAGE,-- MESSAGE TEXT.
                      @ERRORSEVERITY,-- SEVERITY.
                      @ERRORSTATE,-- STATE.
                      @ERRORPROCEDURE,-- PROCEDURE_NAME.
                      @ERRORNUMBER,-- ERRORNUMBER
                      @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END 
