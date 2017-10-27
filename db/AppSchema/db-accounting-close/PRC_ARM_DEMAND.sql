IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_ARM_DEMAND' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].[PRC_ARM_DEMAND] 
  END 

GO 

CREATE PROCEDURE [dbo].[PRC_ARM_DEMAND] (@PROJECTION_MASTER_SID INT, 
                                         @MODULE                VARCHAR(50), 
                                         @USER_ID               INT, 
                                         @SESSION_ID            INT) 
AS 

/**********************************************************************************************************
** File Name		:	PRC_ARM_DEMAND.sql
** Procedure Name	:	PRC_ARM_DEMAND
** Description		:	To generate Demand Accrual Adjustment Summary tab.
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Tr-2 & Tr-4 & Tr-5
						@MODULE                 - It indicates the base module which refers
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
											
** Output Parameters:	NA
** Author Name		:	@AjayNaidu
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application for Demand Accrual Transaction(as base).
                        if new Transaction created with base methodolgy as Demand Accrual / Demand Payment Reconcilation / Demand Reforecast
						as base Transaction then also application will call this procedure. 
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------
** 1    11/08/2016  GAL-8283        @Paul             Temp table Changes.
*********************************************************************************************************/

  BEGIN 
      SET NOCOUNT ON 

      BEGIN TRY 

-- Variables Initialization and assigning variables starts here

        DECLARE @START_PERIOD_SID  INT, 
                  @END_PERIOD_SID    INT, 
                  @START_PERIOD_DATE DATETIME, 
                  @END_PERIOD_DATE   DATETIME,
				  @ADJUSTMENT_TYPE   INT,
				  @SQL NVARCHAR(MAX)=''

          SELECT @START_PERIOD_SID = PERIOD_SID, 
                 @START_PERIOD_DATE = P.PERIOD_DATE 
          FROM   PERIOD P 
                 JOIN PROJECTION_MASTER PM 
                   ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, -1, DATEADD(DD, 1, EOMONTH(PM.FROM_DATE, 0))))
          WHERE  PM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

          SELECT @END_PERIOD_SID = PERIOD_SID, 
                 @END_PERIOD_DATE = P.PERIOD_DATE 
          FROM   PERIOD P 
                 JOIN PROJECTION_MASTER PM 
                   ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, -1, DATEADD(DD, 1, EOMONTH(PM.TO_DATE, 0))))
          WHERE  PM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
		  
          DECLARE @ARM_DEMAND_ADJ_SUMMARY_TABLE        VARCHAR(200) = CONCAT('ST_ARM_DEMAND_ADJ_SUMMARY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                  @ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE VARCHAR(200) = CONCAT('ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                  @ARM_DEMAND_RECON_SUMMARY_TABLE      VARCHAR(200) = CONCAT('ST_ARM_DEMAND_RECON_SUMMARY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))

-- Variables Initialization and assigning variables Ends here

--Taking base Transaction for Particular Projection	starts here
          SELECT @MODULE = C.DESCRIPTION,
		         @ADJUSTMENT_TYPE = TRANSACTION_TYPE
          FROM   ARM_ADJUSTMENT_MASTER A 
                 JOIN ARM_ADJUSTMENT_CONFIG B 
                   ON A.TRANSACTION_TYPE = B.ARM_ADJUSTMENT_CONFIG_SID 
                 JOIN HELPER_TABLE C 
                   ON C.HELPER_TABLE_SID = B.METHODOLGY 
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
--Taking base Transaction for Particular Projection	ends here

--Taking Base Methodologies and Updating Current Transaction type and refering other 2 base methodolgy logic starts here
		   IF OBJECT_ID('TEMPDB..#TEMP_METHODOLOGY') IS NOT NULL 
            DROP TABLE #TEMP_METHODOLOGY 

          CREATE TABLE #TEMP_METHODOLOGY 
            ( 
               ID                        INT IDENTITY(1, 1), 
               METHODOLOGY               VARCHAR(50), 
               ARM_ADJUSTMENT_CONFIG_SID INT 
            ) 

          INSERT INTO #TEMP_METHODOLOGY 
                      (METHODOLOGY, 
                       ARM_ADJUSTMENT_CONFIG_SID) 
          SELECT B.DESCRIPTION, 
                 A.ARM_ADJUSTMENT_CONFIG_SID 
          FROM   ARM_ADJUSTMENT_CONFIG A 
                 JOIN HELPER_TABLE B 
                   ON B.HELPER_TABLE_SID = A.METHODOLGY 
          WHERE  A.TRANSACTION_NAME IN ( 'DEMAND ACCRUAL', 'DEMAND PAYMENT RECONCILIATION TRUE-UP', 'DEMAND REFORECAST TRUE-UP' ) 

          UPDATE T 
          SET    T.ARM_ADJUSTMENT_CONFIG_SID = A.TRANSACTION_TYPE 
          FROM   #TEMP_METHODOLOGY T 
                 JOIN (SELECT TRANSACTION_TYPE, 
                              METHODOLGY, 
                              C.DESCRIPTION 
                       FROM   ARM_ADJUSTMENT_MASTER A 
                              JOIN ARM_ADJUSTMENT_CONFIG B 
                                ON A.TRANSACTION_TYPE = B.ARM_ADJUSTMENT_CONFIG_SID 
                              JOIN HELPER_TABLE C 
                                ON C.HELPER_TABLE_SID = B.METHODOLGY 
                       WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID) A 
                   ON T.METHODOLOGY = A.DESCRIPTION 
          WHERE  TRANSACTION_TYPE NOT IN ( 2, 4, 5 ) 

--Taking Base Methodologies and Updating Current Transaction type and refering other 2 base methodolgy logic Ends here

--Pulling CCP+D Combination for Cuurent Projection with Peridos Starts here
          IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_MASTER') IS NOT NULL 
            DROP TABLE #TEMP_ARM_PROJ_MASTER 

          CREATE TABLE #TEMP_ARM_PROJ_MASTER 
            ( 
               ARM_ADJUSTMENT_DETAILS_SID INT, 
               CCP_DETAILS_SID            INT, 
               RS_MODEL_SID               INT, 
               PERIOD_SID                 INT, 
               PERIOD_DATE                DATETIME, 
               CONTRACT_MASTER_SID        INT, 
               COMPANY_MASTER_SID         INT, 
               ITEM_MASTER_SID            INT, 
               RS_ID                      VARCHAR(50), 
               METHODOLGY                 VARCHAR(20), 
               ARM_ADJUSTMENT_CONFIG_SID  INT, 
               PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID) 
            ) 

          INSERT INTO #TEMP_ARM_PROJ_MASTER 
                      (ARM_ADJUSTMENT_DETAILS_SID, 
                       CCP_DETAILS_SID, 
                       RS_MODEL_SID, 
                       PERIOD_SID, 
                       PERIOD_DATE, 
                       CONTRACT_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       ITEM_MASTER_SID, 
                       RS_ID, 
                       METHODOLGY, 
                       ARM_ADJUSTMENT_CONFIG_SID) 
          SELECT A.ARM_ADJUSTMENT_DETAILS_SID, 
                 A.CCP_DETAILS_SID, 
                 A.RS_MODEL_SID, 
                 P.PERIOD_SID, 
                 P.PERIOD_DATE, 
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
                 CROSS JOIN PERIOD P 
          WHERE  A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
                 AND P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID 

--Pulling CCP+D Combination for Cuurent Projection with Peridos Ends here

--Pulling Discounts from Latest Approved Projection for that CCP+D Combination for Tr-2 and Tr-5 Starts here       
          IF ( @MODULE = 'TRANSACTION 2' ) 
              OR ( @MODULE = 'TRANSACTION 5' ) 
            BEGIN 
--pulling latest approved Projection starts here 
                 IF OBJECT_ID('TEMPDB..#TEMP_PROJ_DETAILS') IS NOT NULL 
                  DROP TABLE #TEMP_PROJ_DETAILS 

                CREATE TABLE #TEMP_PROJ_DETAILS 
                  ( 
                     ARM_ADJUSTMENT_DETAILS_SID INT, 
                     PROJECTION_MASTER_SID      INT, 
                     PROJECTION_DETAILS_SID     INT, 
                     CCP_DETAILS_SID            INT, 
                     FORECASTING_TYPE           VARCHAR(50), 
                     RS_MODEL_SID               INT, 
                     PRIMARY KEY (PROJECTION_DETAILS_SID, RS_MODEL_SID) 
                  ) 

                INSERT INTO #TEMP_PROJ_DETAILS 
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
                               ROW_NUMBER() 
                                 OVER ( 
                                   PARTITION BY ARM_ADJUSTMENT_DETAILS_SID, OUT_P.CCP_DETAILS_SID
                                   ORDER BY WM.MODIFIED_DATE DESC ) RNO 
                        FROM   WORKFLOW_MASTER WM 
                               INNER JOIN (SELECT TMP.ARM_ADJUSTMENT_DETAILS_SID, 
                                                  PM.PROJECTION_MASTER_SID, 
                                                  PD.PROJECTION_DETAILS_SID, 
                                                  PD.CCP_DETAILS_SID, 
                                                  TMP.RS_MODEL_SID, 
                                                  PM.FORECASTING_TYPE 
                                           FROM   ARM_ADJUSTMENT_DETAILS TMP 
                                                  INNER JOIN PROJECTION_DETAILS PD 
                                                          ON PD.CCP_DETAILS_SID = TMP.CCP_DETAILS_SID
                                                  INNER JOIN PROJECTION_MASTER PM 
                                                          ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                                  LEFT JOIN NM_DISCOUNT_PROJ_MASTER NM 
                                                         ON PD.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                                            AND NM.RS_MODEL_SID = TMP.RS_MODEL_SID
                                           WHERE  PM.SAVE_FLAG = 1 
                                                  AND TMP.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID) OUT_P
                                       ON WM.PROJECTION_MASTER_SID = OUT_P.PROJECTION_MASTER_SID
									   WHERE EXISTS (SELECT HELPER_TABLE_SID
                                                                              FROM   HELPER_TABLE H1
                                                                              WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                                                                     AND DESCRIPTION = 'APPROVED'
																					 AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID))A
                        --WHERE  WM.WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID 
                        --                                FROM   HELPER_TABLE 
                        --                                WHERE  LIST_NAME = 'WORKFLOWSTATUS' 
                        --                                       AND DESCRIPTION = 'APPROVED')) A
                WHERE  RNO = 1 
                ORDER  BY ARM_ADJUSTMENT_DETAILS_SID 
--pulling latest approved Projection Ends here 
--Pulling Discount amounts for latest approved Projection starts here
                IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_DISCOUNTS') IS NOT NULL 
                  DROP TABLE #TEMP_ARM_PROJ_DISCOUNTS 

                CREATE TABLE #TEMP_ARM_PROJ_DISCOUNTS 
                  ( 
                     ARM_ADJUSTMENT_DETAILS_SID INT, 
                     PERIOD_SID                 INT, 
                     PROJ_TOTAL_DEMAND_ACCRUAL  NUMERIC(22, 6), 
                     PROJECTION_RATE            NUMERIC(22, 6), 
                     PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID) 
                  ) 

                INSERT INTO #TEMP_ARM_PROJ_DISCOUNTS 
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             PROJ_TOTAL_DEMAND_ACCRUAL, 
                             PROJECTION_RATE) 
                SELECT ARM_ADJUSTMENT_DETAILS_SID, 
                       PERIOD_SID, 
                       PROJ_TOTAL_DEMAND_ACCRUAL, 
                       PROJECTION_RATE 
                FROM   (SELECT T.ARM_ADJUSTMENT_DETAILS_SID, 
                               NM.PERIOD_SID, 
                               ( NM.PROJECTION_SALES ) AS PROJ_TOTAL_DEMAND_ACCRUAL, 
                               NM.PROJECTION_RATE      AS PROJECTION_RATE 
                        FROM   [DBO].[NM_DISCOUNT_PROJECTION] NM 
                               INNER JOIN #TEMP_PROJ_DETAILS T 
                                       ON T.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                          AND NM.RS_MODEL_SID = T.RS_MODEL_SID 
                               JOIN PERIOD P 
                                 ON P.PERIOD_SID = NM.PERIOD_SID 
                                    AND P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                        WHERE  T.FORECASTING_TYPE = 'NON MANDATED' 
                        UNION ALL 
                        SELECT T.ARM_ADJUSTMENT_DETAILS_SID, 
                               NM.PERIOD_SID, 
                               ( NM.PROJECTION_SALES ) AS PROJ_TOTAL_DEMAND_ACCRUAL, 
                               NM.PROJECTION_RATE      AS PROJECTION_RATE 
                        FROM   [DBO].[NM_PPA_PROJECTION] NM 
                               INNER JOIN #TEMP_PROJ_DETAILS T 
                                       ON T.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                          AND NM.RS_MODEL_SID = T.RS_MODEL_SID 
                               JOIN PERIOD P 
                                 ON P.PERIOD_SID = NM.PERIOD_SID 
                                       AND P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                        WHERE  T.FORECASTING_TYPE = 'NON MANDATED' 
                        UNION ALL 
                        SELECT T.ARM_ADJUSTMENT_DETAILS_SID, 
                               M.PERIOD_SID, 
                               ( ISNULL(M.PROJECTION_SALES, 0) 
                                 + ISNULL(MS.PROJECTION_SALES, 0) ) AS PROJ_TOTAL_DEMAND_ACCRUAL,
                               ( ISNULL(M.PROJECTION_RATE, 0) 
                                 + ISNULL(MS.PROJECTION_RATE, 0) )  AS PROJECTION_RATE 
                        FROM   #TEMP_PROJ_DETAILS T 
                               INNER JOIN [DBO].[M_DISCOUNT_PROJECTION] M 
                                       ON T.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID
                                          AND M.SAVE_FLAG = 1 
                               JOIN PERIOD P 
                                 ON P.PERIOD_SID = M.PERIOD_SID 
                               LEFT JOIN M_SUPPLEMENTAL_DISC_PROJ MS 
                                      ON MS.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID
                                         AND MS.PERIOD_SID = M.PERIOD_SID 
                                         AND P.PERIOD_SID = MS.PERIOD_SID 
                                            AND P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                        WHERE  T.FORECASTING_TYPE = 'MANDATED')A 
            END 
--Pulling Discount amounts for latest approved Projection Ends here
--Pulling Discounts from Latest Approved Projection for that CCP+D Combination for Tr-2 and Tr-5 Ends here  

--pulling Actual Discounts for CCP+D Combination Actual Discounts(Tr-4) Starts here 
          ELSE IF @MODULE = 'TRANSACTION 4' 
            BEGIN 
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
                       JOIN #TEMP_ARM_PROJ_MASTER A2 
                         ON A2.PERIOD_DATE BETWEEN A1.ACCRUAL_ACTUAL_START_DATE AND A1.ACCRUAL_ACTUAL_END_DATE
                            AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID 
                            AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID 
                            AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID 
                            AND A1.PROVISION_ID = A2.RS_ID 
                            AND DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_ACTUAL_START_DATE, -1)) >= @START_PERIOD_DATE
                            AND A1.ACCRUAL_ACTUAL_END_DATE <= @END_PERIOD_DATE 
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
            END 

--pulling Actual Discounts for CCP+D Combination Actual Discounts(Tr-4) Ends here 

/*Approved CCP'S Pulling for Current Transaction and other 2 base Transactions i.e for e.g Current Transaction base is Tr-2 then
 we are pulling values for remaining Demand Transactions i.e Demand Payment Recon and Demand Reforecast Transactions Starts here*/

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
                 T.ARM_ADJUSTMENT_CONFIG_SID 
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
                 JOIN #TEMP_METHODOLOGY T 
                   ON T.ARM_ADJUSTMENT_CONFIG_SID = ADM.TRANSACTION_TYPE 
				   AND EXISTS (SELECT HELPER_TABLE_SID
                                                                              FROM   HELPER_TABLE H1
                                                                              WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                                                                     AND DESCRIPTION = 'APPROVED'
																					 AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID)
                      --AND WM.WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID 
                      --                              FROM   HELPER_TABLE 
                      --                              WHERE  LIST_NAME = 'WORKFLOWSTATUS' 
                      --                                     AND DESCRIPTION = 'APPROVED') 

/*Approved CCP'S Pulling for Current Transaction and other 2 base Transactions i.e for e.g Current Transaction base is Tr-2 then
 we are pulling values for remaining Demand Transactions i.e Demand Payment Recon and Demand Reforecast Transactions Ends here*/

--Debit and Credit Logic for Pulling Current Balnce Starts here  

--Pulling Current balance for Tr-2(Deamand Accrual) based on Credit and Debit Indicator and adjustment amount Starts here
		  
		  IF OBJECT_ID('TEMPDB..#ARM_ADJ_SUMMARY_1') IS NOT NULL 
            DROP TABLE #ARM_ADJ_SUMMARY_1 

          CREATE TABLE #ARM_ADJ_SUMMARY_1 
            ( 
               CCP_DETAILS_SID           INT, 
               RS_MODEL_SID              INT, 
               PERIOD_SID                INT, 
               DEMAND_ACCRUAL            NUMERIC(22, 6), 
               INDICATOR_1               BIT, 
               ARM_ADJUSTMENT_CONFIG_SID INT, 
               OLD_DETAILS_SID           INT 
            --PRIMARY KEY(CCP_DETAILS_SID, RS_MODEL_SID, PERIOD_SID)   
            ) 

          INSERT INTO #ARM_ADJ_SUMMARY_1 
                      (CCP_DETAILS_SID, 
                       RS_MODEL_SID, 
                       PERIOD_SID, 
                       DEMAND_ACCRUAL, 
                       INDICATOR_1, 
                       ARM_ADJUSTMENT_CONFIG_SID, 
                       OLD_DETAILS_SID) 
          SELECT A.CCP_DETAILS_SID, 
                 A.RS_MODEL_SID, 
                 A.PERIOD_SID, 
                 ISNULL(SUM(ISNULL(C.OVERRIDE, C.VARIANCE)), 0) AS DEMAND_ACCRUAL, 
                 CASE 
                   WHEN SUM(ISNULL(C.OVERRIDE, C.VARIANCE)) > 0 THEN 0 
                   WHEN SUM(ISNULL(C.OVERRIDE, C.VARIANCE)) < 0 THEN 1 
                   ELSE NULL 
                 END                                            AS INDICATOR_1, 
                 ARM_ADJUSTMENT_CONFIG_SID, 
                 A.OLD_DETAILS_SID 
          FROM   #TEMP_PROJ_MASTER_STATUS A 
                 LEFT JOIN ARM_DEMAND_ADJ_SUMMARY C 
                        ON A.OLD_DETAILS_SID = C.ARM_ADJUSTMENT_DETAILS_SID 
                           AND A.PERIOD_SID = C.PERIOD_SID 
          GROUP  BY A.CCP_DETAILS_SID, 
                    A.RS_MODEL_SID, 
                    A.PERIOD_SID, 
                    ARM_ADJUSTMENT_CONFIG_SID, 
                    A.OLD_DETAILS_SID 

--Pulling Current balance for Tr-2(Deamand Accrual) based on Credit and Debit Indicator and adjustment amount Ends here

--Pulling Current balance for Tr-5(Deamand Refoecast True Up) based on Credit and Debit Indicator and adjustment amount Starts here
          IF OBJECT_ID('TEMPDB..#ARM_ADJ_SUMMARY_2') IS NOT NULL 
            DROP TABLE #ARM_ADJ_SUMMARY_2 

          CREATE TABLE #ARM_ADJ_SUMMARY_2 
            ( 
               CCP_DETAILS_SID           INT, 
               RS_MODEL_SID              INT, 
               PERIOD_SID                INT, 
               DEMAND_ACCRUAL_REFORECAST NUMERIC(22, 6), 
               INDICATOR_2               BIT, 
               ARM_ADJUSTMENT_CONFIG_SID INT, 
               OLD_DETAILS_SID           INT 
            --PRIMARY KEY(CCP_DETAILS_SID, RS_MODEL_SID, PERIOD_SID)   
            ) 

          INSERT INTO #ARM_ADJ_SUMMARY_2 
                      (CCP_DETAILS_SID, 
                       RS_MODEL_SID, 
                       PERIOD_SID, 
                       DEMAND_ACCRUAL_REFORECAST, 
                       INDICATOR_2, 
                       ARM_ADJUSTMENT_CONFIG_SID, 
                       OLD_DETAILS_SID) 
          SELECT A.CCP_DETAILS_SID, 
                 A.RS_MODEL_SID, 
                 A.PERIOD_SID, 
                 ISNULL(SUM(ISNULL(C.OVERRIDE, C.VARIANCE)), 0) AS DEMAND_ACCRUAL_REFORECAST,
                 CASE 
                   WHEN SUM(ISNULL(C.OVERRIDE, C.VARIANCE)) > 0 THEN 0 
                   WHEN SUM(ISNULL(C.OVERRIDE, C.VARIANCE)) < 0 THEN 1 
                   ELSE NULL 
                 END                                            AS INDICATOR_2, 
                 ARM_ADJUSTMENT_CONFIG_SID, 
                 OLD_DETAILS_SID 
          FROM   #TEMP_PROJ_MASTER_STATUS A 
                 LEFT JOIN ARM_DEMAND_RF_TRUE_UP_SUMMARY C 
                        ON A.OLD_DETAILS_SID = C.ARM_ADJUSTMENT_DETAILS_SID 
                           AND A.PERIOD_SID = C.PERIOD_SID 
          GROUP  BY A.CCP_DETAILS_SID, 
                    A.RS_MODEL_SID, 
                    A.PERIOD_SID, 
                    ARM_ADJUSTMENT_CONFIG_SID, 
                    A.OLD_DETAILS_SID 

--Pulling Current balance for Tr-2(Deamand Reforecast True Up) based on Credit and Debit Indicator and adjustment amount Ends here

--Pulling Current balance for Tr-4(Deamand Payment Recon) based on Credit and Debit Indicator and adjustment amount Starts here

          IF OBJECT_ID('TEMPDB..#ARM_ADJ_SUMMARY_3') IS NOT NULL 
            DROP TABLE #ARM_ADJ_SUMMARY_3 

          CREATE TABLE #ARM_ADJ_SUMMARY_3 
            ( 
               CCP_DETAILS_SID           INT, 
               RS_MODEL_SID              INT, 
               PERIOD_SID                INT, 
               DEMAND_PAYMENT_RECON      NUMERIC(22, 6), 
               INDICATOR_3               BIT, 
               ARM_ADJUSTMENT_CONFIG_SID INT, 
               OLD_DETAILS_SID           INT 
            -- PRIMARY KEY(CCP_DETAILS_SID, RS_MODEL_SID, PERIOD_SID)   
            ) 

          INSERT INTO #ARM_ADJ_SUMMARY_3 
                      (CCP_DETAILS_SID, 
                       RS_MODEL_SID, 
                       PERIOD_SID, 
                       DEMAND_PAYMENT_RECON, 
                       INDICATOR_3, 
                       ARM_ADJUSTMENT_CONFIG_SID, 
                       OLD_DETAILS_SID) 
          SELECT A.CCP_DETAILS_SID, 
                 A.RS_MODEL_SID, 
                 A.PERIOD_SID, 
                 ISNULL(SUM(ISNULL(C.OVERRIDE, C.VARIANCE)), 0) AS DEMAND_PAYMENT_RECON, 
                 CASE 
                   WHEN SUM(ISNULL(C.OVERRIDE, C.VARIANCE)) > 0 THEN 0 
                   WHEN SUM(ISNULL(C.OVERRIDE, C.VARIANCE)) < 0 THEN 1 
                   ELSE NULL 
                 END                                            AS INDICATOR_3, 
                 ARM_ADJUSTMENT_CONFIG_SID, 
                 OLD_DETAILS_SID 
          FROM   #TEMP_PROJ_MASTER_STATUS A 
                 LEFT JOIN ARM_DEMAND_RECON_SUMMARY C 
                        ON A.OLD_DETAILS_SID = C.ARM_ADJUSTMENT_DETAILS_SID 
                           AND A.PERIOD_SID = C.PERIOD_SID 
          GROUP  BY A.CCP_DETAILS_SID, 
                    A.RS_MODEL_SID, 
                    A.PERIOD_SID, 
                    ARM_ADJUSTMENT_CONFIG_SID, 
                    A.OLD_DETAILS_SID 

--Pulling Current balance for Tr-4(Deamand Payment Recon) based on Credit and Debit Indicator and adjustment amount ends here

--calculation of Current Balance (Credit - Debit) for Liability Accounts Stats here  

          IF OBJECT_ID('TEMPDB..#CURRENT_BALANCE') IS NOT NULL 
            DROP TABLE #CURRENT_BALANCE 

          CREATE TABLE #CURRENT_BALANCE 
            ( 
               CCP_DETAILS_SID                  INT, 
               RS_MODEL_SID                     INT, 
               PERIOD_SID                       INT, 
               DEMAND_ACCRUAL_CREDIT            NUMERIC(22, 6), 
               DEMAND_ACCRUAL_DEBIT             NUMERIC(22, 6), 
               DEMAND_CURRENT_AMOUNT            NUMERIC(22, 6), 
               DEMAND_ACCRUAL_REFORECAST_CREDIT NUMERIC(22, 6), 
               DEMAND_ACCRUAL_REFORECAST_DEBIT  NUMERIC(22, 6), 
               DEMAND_ACCRUAL_REFORECAST_AMOUNT NUMERIC(22, 6), 
               DEMAND_PAYMENT_RECON_CREDIT      NUMERIC(22, 6), 
               DEMAND_PAYMENT_RECON_DEBIT       NUMERIC(22, 6), 
               DEMAND_PAYMENT_RECON_AMOUNT      NUMERIC(22, 6) 
            ) 

          INSERT INTO #CURRENT_BALANCE (CCP_DETAILS_SID,RS_MODEL_SID,PERIOD_SID,DEMAND_ACCRUAL_CREDIT,DEMAND_ACCRUAL_DEBIT,DEMAND_CURRENT_AMOUNT,
		  DEMAND_ACCRUAL_REFORECAST_CREDIT,DEMAND_ACCRUAL_REFORECAST_DEBIT,DEMAND_ACCRUAL_REFORECAST_AMOUNT,DEMAND_PAYMENT_RECON_CREDIT,
		  DEMAND_PAYMENT_RECON_DEBIT,DEMAND_PAYMENT_RECON_AMOUNT)
          SELECT TAM.CCP_DETAILS_SID, 
                 TAM.RS_MODEL_SID, 
                 COALESCE(A1.PERIOD_SID, A2.PERIOD_SID, A3.PERIOD_SID, A4.PERIOD_SID, A5.PERIOD_SID, A6.PERIOD_SID),
                 SUM(ABS(A1.DEMAND_ACCRUAL))                                                                           AS DEMAND_ACCRUAL_CREDIT,
                 SUM(ABS(A2.DEMAND_ACCRUAL))                                                                           AS DEMAND_ACCRUAL_DEBIT,
                 ISNULL(SUM(ABS(A1.DEMAND_ACCRUAL)), 0) - ISNULL(SUM(ABS(A2.DEMAND_ACCRUAL)), 0)                       AS DEMAND_CURRENT_AMOUNT,
                 SUM(ABS(A3.DEMAND_ACCRUAL_REFORECAST))                                                                AS DEMAND_ACCRUAL_REFORECAST_CREDIT,
                 SUM(ABS(A4.DEMAND_ACCRUAL_REFORECAST))                                                                AS DEMAND_ACCRUAL_REFORECAST_DEBIT,
                 ISNULL(SUM(ABS(A3.DEMAND_ACCRUAL_REFORECAST)), 0) - ISNULL(SUM(ABS(A4.DEMAND_ACCRUAL_REFORECAST)), 0) AS DEMAND_ACCRUAL_REFORECAST_AMOUNT,
                 SUM(ABS(A5.DEMAND_PAYMENT_RECON))                                                                     AS DEMAND_PAYMENT_RECON_CREDIT,
                 SUM(ABS(A6.DEMAND_PAYMENT_RECON))                                                                     AS DEMAND_PAYMENT_RECON_DEBIT,
                 ISNULL(SUM(ABS(A5.DEMAND_PAYMENT_RECON)), 0) - ISNULL(SUM(ABS(A6.DEMAND_PAYMENT_RECON)), 0)           AS DEMAND_PAYMENT_RECON_AMOUNT
          FROM   #TEMP_PROJ_MASTER_STATUS TAM 
                 JOIN ARM_ADJ_RES_CCP AAC 
                   ON TAM.OLD_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
                      AND TAM.ARM_ADJUSTMENT_CONFIG_SID = AAC.ADJUSTMENT_TYPE 
                 INNER JOIN HELPER_TABLE HT 
                         ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID 
                            AND HT.DESCRIPTION = 'LIABILITY' 
                 LEFT JOIN #ARM_ADJ_SUMMARY_1 A1 
                        ON TAM.CCP_DETAILS_SID = A1.CCP_DETAILS_SID 
                           AND TAM.RS_MODEL_SID = A1.RS_MODEL_SID 
                           AND TAM.PERIOD_SID = A1.PERIOD_SID 
                           AND AAC.CREDIT = A1.INDICATOR_1 
                           AND AAC.ADJUSTMENT_TYPE = A1.ARM_ADJUSTMENT_CONFIG_SID 
                           AND TAM.OLD_DETAILS_SID = A1.OLD_DETAILS_SID 
                 LEFT JOIN #ARM_ADJ_SUMMARY_1 A2 
                        ON TAM.CCP_DETAILS_SID = A2.CCP_DETAILS_SID 
                           AND TAM.RS_MODEL_SID = A2.RS_MODEL_SID 
                           AND TAM.PERIOD_SID = A2.PERIOD_SID 
                           AND AAC.DEBIT = A2.INDICATOR_1 
                           AND AAC.ADJUSTMENT_TYPE = A2.ARM_ADJUSTMENT_CONFIG_SID 
                           AND TAM.OLD_DETAILS_SID = A2.OLD_DETAILS_SID 
                 LEFT JOIN #ARM_ADJ_SUMMARY_2 A3 
                        ON TAM.CCP_DETAILS_SID = A3.CCP_DETAILS_SID 
                           AND TAM.RS_MODEL_SID = A3.RS_MODEL_SID 
                           AND TAM.PERIOD_SID = A3.PERIOD_SID 
                           AND AAC.CREDIT = A3.INDICATOR_2 
                           AND AAC.ADJUSTMENT_TYPE = A3.ARM_ADJUSTMENT_CONFIG_SID 
                           AND TAM.OLD_DETAILS_SID = A3.OLD_DETAILS_SID 
                 LEFT JOIN #ARM_ADJ_SUMMARY_2 A4 
                        ON TAM.CCP_DETAILS_SID = A4.CCP_DETAILS_SID 
                           AND TAM.RS_MODEL_SID = A4.RS_MODEL_SID 
                           AND TAM.PERIOD_SID = A4.PERIOD_SID 
                           AND AAC.DEBIT = A4.INDICATOR_2 
                           AND AAC.ADJUSTMENT_TYPE = A4.ARM_ADJUSTMENT_CONFIG_SID 
                           AND TAM.OLD_DETAILS_SID = A4.OLD_DETAILS_SID 
                 LEFT JOIN #ARM_ADJ_SUMMARY_3 A5 
                        ON TAM.CCP_DETAILS_SID = A5.CCP_DETAILS_SID 
                           AND TAM.RS_MODEL_SID = A5.RS_MODEL_SID 
                           AND TAM.PERIOD_SID = A5.PERIOD_SID 
                           AND AAC.CREDIT = A5.INDICATOR_3 
                           AND AAC.ADJUSTMENT_TYPE = A5.ARM_ADJUSTMENT_CONFIG_SID 
                           AND TAM.OLD_DETAILS_SID = A5.OLD_DETAILS_SID 
                 LEFT JOIN #ARM_ADJ_SUMMARY_3 A6 
                        ON TAM.CCP_DETAILS_SID = A6.CCP_DETAILS_SID 
                           AND TAM.RS_MODEL_SID = A6.RS_MODEL_SID 
                           AND TAM.PERIOD_SID = A6.PERIOD_SID 
                           AND AAC.DEBIT = A6.INDICATOR_3 
                           AND AAC.ADJUSTMENT_TYPE = A6.ARM_ADJUSTMENT_CONFIG_SID 
                           AND TAM.OLD_DETAILS_SID = A6.OLD_DETAILS_SID 
          GROUP  BY TAM.CCP_DETAILS_SID, 
                    TAM.RS_MODEL_SID, 
                    COALESCE(A1.PERIOD_SID, A2.PERIOD_SID, A3.PERIOD_SID, A4.PERIOD_SID, A5.PERIOD_SID, A6.PERIOD_SID)

--calculation of Current Balance (Credit - Debit) for Liability Accounts Ends here  

--Debit and Credit Logic for Pulling Current Balnce Ends here  

-- BSR(Balance Summary Report) Liability and Expense amount calculation starts here 

IF OBJECT_ID('TEMPDB..#TEMP_AMOUNT') IS NOT NULL 
  DROP TABLE #TEMP_AMOUNT 

CREATE TABLE #TEMP_AMOUNT 
  ( 
     ARM_ADJUSTMENT_DETAILS_SID INT, 
     PERIOD_SID                 INT, 
     ACCRUAL_AMOUNT             NUMERIC(22, 6), 
     INDICATOR                  BIT 
  ) 
  
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


          SET @SQL=''
          -------------------------------INSERTION OF MAIN TABLE HAPPENING HERE------------------------          
          IF @MODULE = 'TRANSACTION 2' 
            BEGIN 

--Insertion of Adjustment Summary fileds in one temp table starts here

			  IF OBJECT_ID('TEMPDB..#ARM_DEMAND_ADJ_SUMMARY') IS NOT NULL 
            DROP TABLE #ARM_DEMAND_ADJ_SUMMARY 

			CREATE TABLE #ARM_DEMAND_ADJ_SUMMARY
			(
			  ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL, 
			  PERIOD_SID INT NOT NULL, 
			  DEMAND_ACCRUAL NUMERIC(22,6) NULL, 
			  DEMAND_ACCRUAL_REFORECAST NUMERIC(22,6) NULL, 
			  TOTAL_DEMAND_ACCRUAL NUMERIC(22,6) NULL, 
			  PROJ_TOTAL_DEMAND_ACCRUAL NUMERIC(22,6) NULL, 
			  DEMAND_ACCRUAL_RATIO NUMERIC(22,6) NULL, 
			  VARIANCE NUMERIC(22,6) NULL,
			  PROJECTED_RATE NUMERIC(22,6) NULL
			  PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID)
			  )
		INSERT INTO #ARM_DEMAND_ADJ_SUMMARY
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             DEMAND_ACCRUAL, 
                             DEMAND_ACCRUAL_REFORECAST, 
                             TOTAL_DEMAND_ACCRUAL, 
                             PROJ_TOTAL_DEMAND_ACCRUAL, 
                             DEMAND_ACCRUAL_RATIO, 
                             VARIANCE, 
                             PROJECTED_RATE) 
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                       TAM.PERIOD_SID, 
                       ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT), 0)                                                                              AS DEMAND_ACCRUAL,
                       ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT), 0)                                                                   AS DEMAND_ACCRUAL_REFORECAST,
                       ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT), 0) 
                       + ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT), 0)                                                                 AS TOTAL_DEMAND_ACCRUAL,
                       ISNULL(SUM(TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0)                                                                         AS PROJ_TOTAL_DEMAND_ACCRUAL,
                       ( ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT), 0) 
                         + ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT), 0) ) / NULLIF(ISNULL(SUM(TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0), 0) AS DEMAND_ACCRUAL_RATIO,
                       ISNULL(( ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT), 0) 
                                + ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT), 0) ) - ISNULL(SUM(TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0), 0) AS VARIANCE,
                       ISNULL(SUM(TAPD.PROJECTION_RATE), 0)                                                                                   AS PROJECTED_RATE
                FROM   #TEMP_ARM_PROJ_MASTER TAM 
                       LEFT JOIN #CURRENT_BALANCE ADJ 
                              ON TAM.CCP_DETAILS_SID = ADJ.CCP_DETAILS_SID 
                                 AND TAM.RS_MODEL_SID = ADJ.RS_MODEL_SID 
                                 AND TAM.PERIOD_SID = ADJ.PERIOD_SID 
                       LEFT JOIN #TEMP_ARM_PROJ_DISCOUNTS TAPD 
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = TAPD.ARM_ADJUSTMENT_DETAILS_SID
                                 AND TAM.PERIOD_SID = TAPD.PERIOD_SID 
                GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                          TAM.PERIOD_SID 
               
--Insertion of Adjustment Summary fileds in one temp table Ends here

--Balance suammary report calculation starts here for separtely for both Liability and Expense amount for Tr-2
INSERT INTO #TEMP_AMOUNT 
            (ARM_ADJUSTMENT_DETAILS_SID, 
             PERIOD_SID, 
             ACCRUAL_AMOUNT, 
             INDICATOR) 
SELECT ARM_ADJUSTMENT_DETAILS_SID, 
       PERIOD_SID, 
       SUM (VARIANCE) AS ACCRUAL_AMOUNT, 
       CASE 
         WHEN SUM(VARIANCE) > 0 THEN 0 
         WHEN SUM(VARIANCE) < 0 THEN 1 
         ELSE NULL 
       END                                 AS INDICATOR 
FROM   #ARM_DEMAND_ADJ_SUMMARY 
GROUP BY ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID



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
                  AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE 
       LEFT JOIN #TEMP_AMOUNT A1 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.CREDIT = A1.INDICATOR 
       LEFT JOIN #TEMP_AMOUNT A2 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.DEBIT = A2.INDICATOR 
WHERE CREDIT IS NOT NULL AND DEBIT IS NOT NULL
GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
          ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) 


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
                  AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE 
       LEFT JOIN #TEMP_AMOUNT A1 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.CREDIT = A1.INDICATOR 
       LEFT JOIN #TEMP_AMOUNT A2 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.DEBIT = A2.INDICATOR
WHERE CREDIT IS NOT NULL AND DEBIT IS NOT NULL 
GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
          ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) 

--Balance suammary report calculation Ends here for separtely for both Liability and Expense amount for Tr-2 

--Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-2) for CCP+D+Period Combination Starts here

 SET @SQL=CONCAT('IF EXISTS (SELECT 1 FROM   '+@ARM_DEMAND_ADJ_SUMMARY_TABLE+')
                  BEGIN 
                      TRUNCATE TABLE ', @ARM_DEMAND_ADJ_SUMMARY_TABLE, '   
                  END 

                INSERT INTO ',@ARM_DEMAND_ADJ_SUMMARY_TABLE,' 
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             DEMAND_ACCRUAL, 
                             DEMAND_ACCRUAL_REFORECAST, 
                             TOTAL_DEMAND_ACCRUAL, 
                             PROJ_TOTAL_DEMAND_ACCRUAL, 
                             DEMAND_ACCRUAL_RATIO, 
                             VARIANCE, 
                             PROJECTED_RATE,
							 LIABILITY_AMOUNT,
							 EXPENSE_AMOUNT) 
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                       TAM.PERIOD_SID, 
                       TAM.DEMAND_ACCRUAL AS DEMAND_ACCRUAL,                                                                        
                       TAM.DEMAND_ACCRUAL_REFORECAST AS DEMAND_ACCRUAL_REFORECAST,                       
                       TAM.TOTAL_DEMAND_ACCRUAL AS TOTAL_DEMAND_ACCRUAL,
                       TAM.PROJ_TOTAL_DEMAND_ACCRUAL AS PROJ_TOTAL_DEMAND_ACCRUAL,
                       TAM.DEMAND_ACCRUAL_RATIO AS DEMAND_ACCRUAL_RATIO,
                       TAM.VARIANCE AS VARIANCE,
                       TAM.PROJECTED_RATE AS PROJECTED_RATE,
					   CL.CURRENT_AMOUNT AS LIABILITY_AMOUNT,
					   CE.CURRENT_AMOUNT AS EXPENSE_AMOUNT
                FROM   #ARM_DEMAND_ADJ_SUMMARY TAM
					  LEFT JOIN #CURRENT_LIABILITY CL
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CL.ARM_ADJUSTMENT_DETAILS_SID
                                 AND TAM.PERIOD_SID = CL.PERIOD_SID 
					  LEFT JOIN #CURRENT_EXPENSE CE
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CE.ARM_ADJUSTMENT_DETAILS_SID
                                 AND TAM.PERIOD_SID = CE.PERIOD_SID ')
			    
				EXEC sp_executesql @SQL

            END 
--Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-2) for CCP+D+Period Combination Ends here	

          ELSE IF @MODULE = 'TRANSACTION 5' 
            BEGIN 

			 IF OBJECT_ID('TEMPDB..#ARM_DEMAND_REFORECAST_SUMMARY') IS NOT NULL 
            DROP TABLE #ARM_DEMAND_REFORECAST_SUMMARY 

			CREATE TABLE #ARM_DEMAND_REFORECAST_SUMMARY
			(
			  ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL, 
			  PERIOD_SID INT NOT NULL, 
			  DEMAND_ACCRUAL NUMERIC(22,6) NULL, 
			  DEMAND_ACCRUAL_REFORECAST NUMERIC(22,6) NULL, 
			  TOTAL_DEMAND_ACCRUAL NUMERIC(22,6) NULL, 
			  PROJ_TOTAL_DEMAND_ACCRUAL NUMERIC(22,6) NULL, 
			  DEMAND_ACCRUAL_RATIO NUMERIC(22,6) NULL, 
			  VARIANCE NUMERIC(22,6) NULL,
			  PROJECTED_RATE NUMERIC(22,6) NULL
			  PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID)
			  )

			INSERT INTO #ARM_DEMAND_REFORECAST_SUMMARY
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             DEMAND_ACCRUAL, 
                             DEMAND_ACCRUAL_REFORECAST, 
                             TOTAL_DEMAND_ACCRUAL, 
                             PROJ_TOTAL_DEMAND_ACCRUAL, 
                             DEMAND_ACCRUAL_RATIO, 
                             VARIANCE, 
                             PROJECTED_RATE) 
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                       TAM.PERIOD_SID, 
                       ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT), 0)                                                                              AS DEMAND_ACCRUAL,
                       ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT), 0)                                                                   AS DEMAND_ACCRUAL_REFORECAST,
                       ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT), 0) 
                       + ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT), 0)                                                                 AS TOTAL_DEMAND_ACCRUAL,
                       ISNULL(SUM(TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0)                                                                         AS PROJ_TOTAL_DEMAND_ACCRUAL,
                       ( ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT), 0) 
                         + ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT), 0) ) / NULLIF(ISNULL(SUM(TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0), 0) AS DEMAND_ACCRUAL_RATIO,
                       ISNULL(( ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT), 0) 
                                + ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT), 0) ) - ISNULL(SUM(TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0), 0) AS VARIANCE,
                       ISNULL(SUM(TAPD.PROJECTION_RATE), 0)                                                                                   AS PROJECTED_RATE
                FROM   #TEMP_ARM_PROJ_MASTER TAM 
                       LEFT JOIN #CURRENT_BALANCE ADJ 
                              ON TAM.CCP_DETAILS_SID = ADJ.CCP_DETAILS_SID 
                                 AND TAM.RS_MODEL_SID = ADJ.RS_MODEL_SID 
                                 AND TAM.PERIOD_SID = ADJ.PERIOD_SID 
                       LEFT JOIN #TEMP_ARM_PROJ_DISCOUNTS TAPD 
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = TAPD.ARM_ADJUSTMENT_DETAILS_SID
                                 AND TAM.PERIOD_SID = TAPD.PERIOD_SID 
                GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                          TAM.PERIOD_SID
--Balance suammary report calculation starts here for separtely for both Liability and Expense amount for Tr-5
INSERT INTO #TEMP_AMOUNT 
            (ARM_ADJUSTMENT_DETAILS_SID, 
             PERIOD_SID, 
             ACCRUAL_AMOUNT, 
             INDICATOR) 
SELECT ARM_ADJUSTMENT_DETAILS_SID, 
       PERIOD_SID, 
       SUM (VARIANCE) AS ACCRUAL_AMOUNT, 
       CASE 
         WHEN SUM(VARIANCE) > 0 THEN 0 
         WHEN SUM(VARIANCE) < 0 THEN 1 
         ELSE NULL 
       END                                 AS INDICATOR 
FROM   #ARM_DEMAND_REFORECAST_SUMMARY 
GROUP BY ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID




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
                  AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE 
       LEFT JOIN #TEMP_AMOUNT A1 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.CREDIT = A1.INDICATOR 
       LEFT JOIN #TEMP_AMOUNT A2 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.DEBIT = A2.INDICATOR 
	  WHERE CREDIT IS NOT NULL AND DEBIT IS NOT NULL
GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
          ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) 


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
                  AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE 
       LEFT JOIN #TEMP_AMOUNT A1 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.CREDIT = A1.INDICATOR 
       LEFT JOIN #TEMP_AMOUNT A2 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.DEBIT = A2.INDICATOR 
		WHERE CREDIT IS NOT NULL AND DEBIT IS NOT NULL
GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
          ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) 

--Balance suammary report calculation Ends here for separtely for both Liability and Expense amount for Tr-5
						  
--Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-5) for CCP+D+Period Combination Starts here	

                SET @SQL=CONCAT('IF EXISTS (SELECT 1 FROM ',@ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE,') 
                  BEGIN 
                      TRUNCATE TABLE ', @ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE, '   
                  END 

                INSERT INTO ',@ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE,' 
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             DEMAND_ACCRUAL, 
                             DEMAND_ACCRUAL_REFORECAST, 
                             TOTAL_DEMAND_ACCRUAL, 
                             PROJ_TOTAL_DEMAND_ACCRUAL, 
                             DEMAND_ACCRUAL_RATIO, 
                             VARIANCE, 
                             PROJECTED_RATE,
							 LIABILITY_AMOUNT,
							 EXPENSE_AMOUNT) 
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                       TAM.PERIOD_SID, 
                       TAM.DEMAND_ACCRUAL AS DEMAND_ACCRUAL,
                       TAM.DEMAND_ACCRUAL_REFORECAST AS DEMAND_ACCRUAL_REFORECAST,
                        TAM.TOTAL_DEMAND_ACCRUAL,
                       TAM.PROJ_TOTAL_DEMAND_ACCRUAL,
                       TAM.DEMAND_ACCRUAL_RATIO,
                       TAM.VARIANCE,
                       TAM.PROJECTED_RATE,
					   CL.CURRENT_AMOUNT AS LIABILITY_AMOUNT,
					   CE.CURRENT_AMOUNT AS EXPENSE_AMOUNT                                                                                  
                FROM   #ARM_DEMAND_REFORECAST_SUMMARY TAM
					  LEFT JOIN #CURRENT_LIABILITY CL
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CL.ARM_ADJUSTMENT_DETAILS_SID
                                 AND TAM.PERIOD_SID = CL.PERIOD_SID 
					  LEFT JOIN #CURRENT_EXPENSE CE
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CE.ARM_ADJUSTMENT_DETAILS_SID
                                 AND TAM.PERIOD_SID = CE.PERIOD_SID ') 
								 select @SQL
			    EXEC sp_executesql @SQL
				
            END 

--Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-5) for CCP+D+Period Combination Ends here
          ELSE IF @MODULE = 'TRANSACTION 4' 
            BEGIN 

		IF OBJECT_ID('TEMPDB..#ARM_DEMAND_RECON_SUMMARY') IS NOT NULL 
            DROP TABLE #ARM_DEMAND_RECON_SUMMARY 

			CREATE TABLE #ARM_DEMAND_RECON_SUMMARY
			(
			  ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL, 
			  PERIOD_SID INT NOT NULL, 
			  DEMAND_ACCRUAL NUMERIC(22,6) NULL, 
			  DEMAND_ACCRUAL_REFORECAST NUMERIC(22,6) NULL,
			  DEMAND_PAYMENT_RECON NUMERIC(22,6) NULL,
			  TOTAL_DEMAND_ACCRUAL NUMERIC(22,6) NULL, 
			  ACTUAL_PAYMENTS NUMERIC(22,6) NULL, 
			  PAYMENT_RATIO NUMERIC(22,6) NULL, 
			  VARIANCE NUMERIC(22,6) NULL,
			  PROJECTED_RATE NUMERIC(22,6) NULL
			  PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID)
			  )

			INSERT INTO #ARM_DEMAND_RECON_SUMMARY
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             DEMAND_ACCRUAL, 
                             DEMAND_ACCRUAL_REFORECAST, 
                             DEMAND_PAYMENT_RECON, 
                             TOTAL_DEMAND_ACCRUAL, 
                             ACTUAL_PAYMENTS, 
                             PAYMENT_RATIO, 
                             VARIANCE, 
                             PROJECTED_RATE) 
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                       TAM.PERIOD_SID, 
                       ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT), 0)                                                                  AS DEMAND_ACCRUAL,
                       ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT), 0)                                                       AS DEMAND_ACCRUAL_REFORECAST,
                       ISNULL(SUM(ADJ.DEMAND_PAYMENT_RECON_AMOUNT), 0)                                                            AS DEMAND_PAYMENT_RECON,
                       ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT) 
                              + SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT) 
                              + SUM(ADJ.DEMAND_PAYMENT_RECON_AMOUNT), 0)                                                          AS TOTAL_DEMAND_ACCRUAL,
                       ISNULL(SUM(TAPD.ACTUAL_PAYMENTS), 0)                                                                       AS ACTUAL_PAYMENTS,
                       COALESCE (( ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT) 
                                          + SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT) 
                                          + SUM(ADJ.DEMAND_PAYMENT_RECON_AMOUNT), 0) ) / NULLIF(SUM(TAPD.ACTUAL_PAYMENTS), 0), 0) AS PAYMENT_RATIO,
                       COALESCE(( ISNULL(SUM(ADJ.DEMAND_CURRENT_AMOUNT) 
                                         + SUM(ADJ.DEMAND_ACCRUAL_REFORECAST_AMOUNT) 
                                         + SUM(ADJ.DEMAND_PAYMENT_RECON_AMOUNT), 0) ) - ISNULL(SUM(TAPD.ACTUAL_PAYMENTS), 0), 0)  AS VARIANCE,
                       ISNULL(SUM(TAPD.ACTUAL_RATE), 0)                                                                           AS PROJECTED_RATE
                FROM   #TEMP_ARM_PROJ_MASTER TAM 
                       LEFT JOIN #CURRENT_BALANCE ADJ 
                              ON TAM.CCP_DETAILS_SID = ADJ.CCP_DETAILS_SID 
                                 AND TAM.RS_MODEL_SID = ADJ.RS_MODEL_SID 
                                 AND TAM.PERIOD_SID = ADJ.PERIOD_SID 
                       LEFT JOIN #TEMP_ACTUAL_DISCOUNTS TAPD 
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = TAPD.ARM_ADJUSTMENT_DETAILS_SID
                                 AND TAM.PERIOD_SID = TAPD.PERIOD_SID 
                GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                          TAM.PERIOD_SID

--Balance suammary report calculation starts here for separtely for both Liability and Expense amount for Tr-4
INSERT INTO #TEMP_AMOUNT 
            (ARM_ADJUSTMENT_DETAILS_SID, 
             PERIOD_SID, 
             ACCRUAL_AMOUNT, 
             INDICATOR) 
SELECT ARM_ADJUSTMENT_DETAILS_SID, 
       PERIOD_SID, 
       SUM (VARIANCE) AS ACCRUAL_AMOUNT, 
       CASE 
         WHEN SUM(VARIANCE) > 0 THEN 0 
         WHEN SUM(VARIANCE) < 0 THEN 1 
         ELSE NULL 
       END                                 AS INDICATOR 
FROM   #ARM_DEMAND_RECON_SUMMARY 
GROUP BY ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID

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
                  AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE 
       LEFT JOIN #TEMP_AMOUNT A1 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.CREDIT = A1.INDICATOR 
       LEFT JOIN #TEMP_AMOUNT A2 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.DEBIT = A2.INDICATOR 
	WHERE CREDIT IS NOT NULL AND DEBIT IS NOT NULL
GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
          ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) 



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
                  AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE 
       LEFT JOIN #TEMP_AMOUNT A1 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.CREDIT = A1.INDICATOR 
       LEFT JOIN #TEMP_AMOUNT A2 
              ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
                 AND AAC.DEBIT = A2.INDICATOR 
WHERE CREDIT IS NOT NULL AND DEBIT IS NOT NULL
GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
          ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) 

--Balance suammary report calculation Ends here for separtely for both Liability and Expense amount for Tr-4

--Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-4) for CCP+D+Period Combination Starts here	


                SET @SQL=CONCAT('IF EXISTS (SELECT 1 FROM ',@ARM_DEMAND_RECON_SUMMARY_TABLE,') 
                  BEGIN 
                      TRUNCATE TABLE ', @ARM_DEMAND_RECON_SUMMARY_TABLE, '   
                  END 

                INSERT INTO ',@ARM_DEMAND_RECON_SUMMARY_TABLE,' 
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                             PERIOD_SID, 
                             DEMAND_ACCRUAL, 
                             DEMAND_ACCRUAL_REFORECAST, 
                             DEMAND_PAYMENT_RECON, 
                             TOTAL_DEMAND_ACCRUAL, 
                             ACTUAL_PAYMENTS, 
                             PAYMENT_RATIO, 
                             VARIANCE, 
                             PROJECTED_RATE,
							 LIABILITY_AMOUNT,
							 EXPENSE_AMOUNT) 
                SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                       TAM.PERIOD_SID, 
                       TAM.DEMAND_ACCRUAL,
                        TAM.DEMAND_ACCRUAL_REFORECAST,
                        TAM.DEMAND_PAYMENT_RECON,
                        TAM.TOTAL_DEMAND_ACCRUAL,
                       TAM.ACTUAL_PAYMENTS,
                    TAM.PAYMENT_RATIO,
                       TAM.VARIANCE,
                       TAM.PROJECTED_RATE,
					   CL.CURRENT_AMOUNT AS LIABILITY_AMOUNT,
					   CE.CURRENT_AMOUNT AS EXPENSE_AMOUNT 
                FROM   #ARM_DEMAND_RECON_SUMMARY TAM 
                       LEFT JOIN #CURRENT_LIABILITY CL
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CL.ARM_ADJUSTMENT_DETAILS_SID
                                 AND TAM.PERIOD_SID = CL.PERIOD_SID 
					  LEFT JOIN #CURRENT_EXPENSE CE
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CE.ARM_ADJUSTMENT_DETAILS_SID
                                 AND TAM.PERIOD_SID = CE.PERIOD_SID ')
				
				EXEC sp_executesql @SQL

--Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-4) for CCP+D+Period Combination Ends here	

            END 

			
          IF OBJECT_ID('TEMPDB..#TEMP_METHODOLOGY') IS NOT NULL 
            DROP TABLE #TEMP_METHODOLOGY 

          IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_MASTER') IS NOT NULL 
            DROP TABLE #TEMP_ARM_PROJ_MASTER 

          IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_DISCOUNTS') IS NOT NULL 
            DROP TABLE #TEMP_ARM_PROJ_DISCOUNTS 

          IF OBJECT_ID('TEMPDB..#TEMP_ACTUAL_DISCOUNTS') IS NOT NULL 
            DROP TABLE #TEMP_ACTUAL_DISCOUNTS 

          IF OBJECT_ID('TEMPDB..#TEMP_PROJ_MASTER_STATUS') IS NOT NULL 
            DROP TABLE #TEMP_PROJ_MASTER_STATUS 

          IF OBJECT_ID('TEMPDB..#ARM_ADJ_SUMMARY') IS NOT NULL 
            DROP TABLE #ARM_ADJ_SUMMARY 
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

