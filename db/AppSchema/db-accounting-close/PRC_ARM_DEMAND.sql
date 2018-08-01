IF EXISTS (SELECT
    'X'
  FROM INFORMATION_SCHEMA.ROUTINES
  WHERE ROUTINE_NAME = 'PRC_ARM_DEMAND'
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
  ** File Name  	:	PRC_ARM_DEMAND.sql
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
  ** VER   Date       Ticket No                        Author           Description 
  ** ---  --------    ---------                     -------------     -----------------------------
  ** 1    11/08/2016  GAL-8283                        @Paul              Temp table Changes.
  ** 2	08/07/2017  GAL-12264                       @Kishore Kumar	   MI003 - Unqualified column name
  ** 3    03/10/2017  GAL-12490,GAL-12491,GAL-12497   @AjayNaidu         Current balance Logic change multi period
  ** 4    12/10/2017  GAL-12571                       @AjayNaidu         Unit testing issue for adjustments
  ** 5    28-12-2017  GAL-12267                       @AjayNaidu         BP014 - [NOT] NULL option
  ** 6    08-01-2018  GAL-12270                       @AjayNaidu         EI025 PE001 PE010 ST008 MI005 MI002 Error codes
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
                  @SQL               NVARCHAR(max) = '',
                  @CONSTRAINT_NAME   VARCHAR(1000)

          ----------------Creating all necessray temp tables(all ddl statements) for the logic Starts here (PE010 CodeGuarderror)
          IF Object_id('TEMPDB..#TEMP_ARM_PROJ_MASTER') IS NOT NULL
            DROP TABLE #TEMP_ARM_PROJ_MASTER

          CREATE TABLE #TEMP_ARM_PROJ_MASTER
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               CCP_DETAILS_SID            INT NOT NULL,
               RS_MODEL_SID               INT NOT NULL,
               --PERIOD_SID                 INT NOT NULL,
               --PERIOD_DATE DATETIME NOT NULL,
               --CONTRACT_MASTER_SID INT NOT NULL,
               --COMPANY_MASTER_SID INT NOT NULL,
               --ITEM_MASTER_SID INT NOT NULL,
               --RS_ID VARCHAR(50)  NOT NULL,
               --METHODOLGY VARCHAR(50) NULL,
               ARM_ADJUSTMENT_CONFIG_SID  INT NULL,
            --PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID)
            )

          IF Object_id('TEMPDB..#TEMP_PROJ_DETAILS') IS NOT NULL
            DROP TABLE #TEMP_PROJ_DETAILS

          CREATE TABLE #TEMP_PROJ_DETAILS
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               --PROJECTION_MASTER_SID      INT NOT NULL,
               PROJECTION_DETAILS_SID     INT NOT NULL,
               --CCP_DETAILS_SID            INT NOT NULL,
               FORECASTING_TYPE           VARCHAR(50) NULL,
               RS_MODEL_SID               INT NOT NULL,
               RS_CONTRACT_SID            INT NOT NULL,
            -- PRIMARY KEY (PROJECTION_DETAILS_SID, RS_MODEL_SID, RS_CONTRACT_SID)
            )

          IF Object_id('TEMPDB..#TEMP_ARM_PROJ_DISCOUNTS') IS NOT NULL
            DROP TABLE #TEMP_ARM_PROJ_DISCOUNTS

          CREATE TABLE #TEMP_ARM_PROJ_DISCOUNTS
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               PERIOD_SID                 INT NOT NULL,
               PROJ_TOTAL_DEMAND_ACCRUAL  NUMERIC(22, 6) NULL,
               PROJECTION_RATE            NUMERIC(22, 6) NULL,
            -- PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID)
            )

          IF Object_id('TEMPDB..#TEMP_ACTUAL_DISCOUNTS') IS NOT NULL
            DROP TABLE #TEMP_ACTUAL_DISCOUNTS

          CREATE TABLE #TEMP_ACTUAL_DISCOUNTS
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               PERIOD_SID                 INT NOT NULL,
               ACTUAL_PAYMENTS            NUMERIC(22, 6) NULL,
               ACTUAL_RATE                NUMERIC(22, 6) NULL,
            --PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID)
            )

          /*
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
            */
          IF Object_id('TEMPDB..#ARM_DEMAND_REFORECAST_SUMMARY') IS NOT NULL
            DROP TABLE #ARM_DEMAND_REFORECAST_SUMMARY

          CREATE TABLE #ARM_DEMAND_REFORECAST_SUMMARY
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               PERIOD_SID                 INT NOT NULL,
               DEMAND_ACCRUAL             NUMERIC(22, 6) NULL,
               DEMAND_ACCRUAL_REFORECAST  NUMERIC(22, 6) NULL,
               TOTAL_DEMAND_ACCRUAL       NUMERIC(22, 6) NULL,
               PROJ_TOTAL_DEMAND_ACCRUAL  NUMERIC(22, 6) NULL,
               DEMAND_ACCRUAL_RATIO       NUMERIC(22, 6) NULL,
               VARIANCE                   NUMERIC(22, 6) NULL,
               PROJECTED_RATE             NUMERIC(22, 6) NULL
            --PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID)
            )

          IF Object_id('TEMPDB..#ARM_DEMAND_ADJ_SUMMARY') IS NOT NULL
            DROP TABLE #ARM_DEMAND_ADJ_SUMMARY

          CREATE TABLE #ARM_DEMAND_ADJ_SUMMARY
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               PERIOD_SID                 INT NOT NULL,
               DEMAND_ACCRUAL             NUMERIC(22, 6) NULL,
               DEMAND_ACCRUAL_REFORECAST  NUMERIC(22, 6) NULL,
               TOTAL_DEMAND_ACCRUAL       NUMERIC(22, 6) NULL,
               PROJ_TOTAL_DEMAND_ACCRUAL  NUMERIC(22, 6) NULL,
               DEMAND_ACCRUAL_RATIO       NUMERIC(22, 6) NULL,
               VARIANCE                   NUMERIC(22, 6) NULL,
               PROJECTED_RATE             NUMERIC(22, 6) NULL
            -- PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID)
            )

          IF Object_id('TEMPDB..#ARM_DEMAND_RECON_SUMMARY') IS NOT NULL
            DROP TABLE #ARM_DEMAND_RECON_SUMMARY

          CREATE TABLE #ARM_DEMAND_RECON_SUMMARY
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               PERIOD_SID                 INT NOT NULL,
               DEMAND_ACCRUAL             NUMERIC(22, 6) NULL,
               DEMAND_ACCRUAL_REFORECAST  NUMERIC(22, 6) NULL,
               DEMAND_PAYMENT_RECON       NUMERIC(22, 6) NULL,
               TOTAL_DEMAND_ACCRUAL       NUMERIC(22, 6) NULL,
               ACTUAL_PAYMENTS            NUMERIC(22, 6) NULL,
               PAYMENT_RATIO              NUMERIC(22, 6) NULL,
               VARIANCE                   NUMERIC(22, 6) NULL,
               PROJECTED_RATE             NUMERIC(22, 6) NULL
            -- PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID)
            )

          ----------------Creating all necessray temp tables(all ddl statements) for the logic Ends here (PE010 CodeGuarderror)
          SELECT @START_PERIOD_SID = P.PERIOD_SID,
                 @START_PERIOD_DATE = P.PERIOD_DATE
          FROM   PERIOD P
                 JOIN PROJECTION_MASTER PM
                   ON P.PERIOD_DATE = CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(PM.FROM_DATE, 0))))
          WHERE  PM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          SELECT @END_PERIOD_SID = P.PERIOD_SID,
                 @END_PERIOD_DATE = P.PERIOD_DATE
          FROM   PERIOD P
                 JOIN PROJECTION_MASTER PM
                   ON P.PERIOD_DATE = CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(PM.TO_DATE, 0))))
          WHERE  PM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          SELECT @END_PERIOD_DATE = Eomonth(@END_PERIOD_DATE)

          IF Object_id('TEMPDB..#PERIOD') IS NOT NULL
            DROP TABLE #PERIOD

          SELECT PERIOD_SID
          INTO   #PERIOD
          FROM   PERIOD
          WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID

          DECLARE @ARM_DEMAND_ADJ_SUMMARY_TABLE        VARCHAR(200) = Concat('ST_ARM_DEMAND_ADJ_SUMMARY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE VARCHAR(200) = Concat('ST_ARM_DEMAND_RF_TRUE_UP_SUMMARY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ARM_DEMAND_RECON_SUMMARY_TABLE      VARCHAR(200) = Concat('ST_ARM_DEMAND_RECON_SUMMARY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ARM_CURRENT_BALANCE                 VARCHAR(200) = Concat('ST_ARM_CURRENT_BALANCE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ARM_ADJUSTMENTS                     VARCHAR(200) = Concat('ST_ARM_ADJUSTMENTS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @summary_table                       VARCHAR(200)

          -- Variables Initialization and assigning variables Ends here
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
          --Pulling CCP+D Combination for Cuurent Projection with Peridos Starts here
          INSERT INTO #TEMP_ARM_PROJ_MASTER WITH (tablock)
                      (ARM_ADJUSTMENT_DETAILS_SID,
                       CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       --PERIOD_SID,
                       --PERIOD_DATE,
                       --CONTRACT_MASTER_SID,
                       --COMPANY_MASTER_SID,
                       --ITEM_MASTER_SID,
                       --RS_ID,
                       --METHODOLGY,
                       ARM_ADJUSTMENT_CONFIG_SID)
          SELECT A.ARM_ADJUSTMENT_DETAILS_SID,
                 A.CCP_DETAILS_SID,
                 A.RS_MODEL_SID,
                 --P.PERIOD_SID,
                 --P.PERIOD_DATE,
                 --B.CONTRACT_MASTER_SID,
                 --B.COMPANY_MASTER_SID,
                 --B.ITEM_MASTER_SID,
                 --RS.RS_ID,
                 --HT.DESCRIPTION,
                 AM.TRANSACTION_TYPE
          FROM   ARM_ADJUSTMENT_DETAILS A
                 --JOIN CCP_DETAILS B
                 --  ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                 --JOIN RS_MODEL RS
                 --  ON RS.RS_MODEL_SID = A.RS_MODEL_SID
                 JOIN ARM_ADJUSTMENT_MASTER AM
                   ON AM.PROJECTION_MASTER_SID = a.PROJECTION_MASTER_SID
                      AND AM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
          --JOIN ARM_ADJUSTMENT_CONFIG AC
          --  ON AC.ARM_ADJUSTMENT_CONFIG_SID = AM.TRANSACTION_TYPE
          -- JOIN HELPER_TABLE HT
          -- ON HT.HELPER_TABLE_SID = AC.METHODOLGY
          --CROSS JOIN #PERIOD P
          WHERE  A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          --AND P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
          -- AND EXISTS (SELECT 1
          --             FROM   ARM_DEDUCTION_SELECTION ADS
          --                    JOIN RS_CONTRACT R
          --                      ON R.RS_CONTRACT_SID = ADS.RS_CONTRACT_SID
          --                         AND R.CONTRACT_MASTER_SID = B.CONTRACT_MASTER_SID
          --                         AND A.PROJECTION_MASTER_SID = ADS.PROJECTION_MASTER_SID
          --                         AND A.RS_MODEL_SID = R.RS_MODEL_SID)
          --
          --Pulling CCP+D Combination for Cuurent Projection with Peridos Ends here
          --   IF Object_id('TEMPDB..#ARM_ADJ_RES_CCP') IS NOT NULL
          --     DROP TABLE #ARM_ADJ_RES_CCP
          --
          --   SELECT ARM_ADJUSTMENT_DETAILS_SID,
          --          ACCOUNT,
          --          CREDIT,
          --          DEBIT
          --   INTO   #ARM_ADJ_RES_CCP
          --   FROM   ARM_ADJ_RES_CCP AC
          --   WHERE  CREDIT IS NOT NULL
          --          AND DEBIT IS NOT NULL
          --          AND ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
          --          AND EXISTS(SELECT 1
          --                     FROM   #TEMP_ARM_PROJ_MASTER AR
          --                     WHERE  AR.ARM_ADJUSTMENT_DETAILS_SID = AC.ARM_ADJUSTMENT_DETAILS_SID)
          --Pulling Discounts from Latest Approved Projection for that CCP+D Combination for Tr-2 and Tr-5 Starts here       
          IF ( @MODULE = 'Transaction 2 - Demand Accrual' )
              OR ( @MODULE = 'Transaction 5 - Demand Reforecast True-up' )
            BEGIN
                --pulling latest approved Projection starts here 
                INSERT INTO #TEMP_PROJ_DETAILS WITH (tablock)
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             --PROJECTION_MASTER_SID,
                             PROJECTION_DETAILS_SID,
                             -- CCP_DETAILS_SID,
                             FORECASTING_TYPE,
                             RS_MODEL_SID,
                             RS_CONTRACT_SID)
                SELECT A.ARM_ADJUSTMENT_DETAILS_SID,
                       --A.PROJECTION_MASTER_SID,
                       A.PROJECTION_DETAILS_SID,
                       -- A.CCP_DETAILS_SID,
                       A.FORECASTING_TYPE,
                       A.RS_MODEL_SID,
                       a.RS_CONTRACT_SID
                FROM   (SELECT OUT_P.ARM_ADJUSTMENT_DETAILS_SID,
                               -- WM.PROJECTION_MASTER_SID,
                               OUT_P.PROJECTION_DETAILS_SID,
                               --OUT_P.CCP_DETAILS_SID,
                               OUT_P.RS_MODEL_SID,
                               OUT_P.FORECASTING_TYPE,
                               OUT_P.RS_CONTRACT_SID,
                               Row_number()
                                 OVER (
                                   PARTITION BY OUT_P.ARM_ADJUSTMENT_DETAILS_SID, OUT_P.CCP_DETAILS_SID, OUT_P.RS_CONTRACT_SID
                                   ORDER BY WM.MODIFIED_DATE DESC) RNO
                        FROM   WORKFLOW_MASTER WM
                               INNER JOIN (SELECT TMP.ARM_ADJUSTMENT_DETAILS_SID,
                                                  PM.PROJECTION_MASTER_SID,
                                                  PD.PROJECTION_DETAILS_SID,
                                                  PD.CCP_DETAILS_SID,
                                                  TMP.RS_MODEL_SID,
                                                  PM.FORECASTING_TYPE,
                                                  ads.RS_CONTRACT_SID
                                           FROM   ARM_ADJUSTMENT_DETAILS TMP
                                                  INNER JOIN PROJECTION_DETAILS PD
                                                          ON PD.CCP_DETAILS_SID = TMP.CCP_DETAILS_SID
                                                  INNER JOIN PROJECTION_MASTER PM
                                                          ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                                  JOIN CCP_DETAILS CD
                                                    ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                                                  JOIN RS_CONTRACT R
                                                    ON R.RS_MODEL_SID = TMP.RS_MODEL_SID
                                                       AND R.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
                                                  INNER JOIN ARM_DEDUCTION_SELECTION ADS
                                                          ON ads.PROJECTION_MASTER_SID = tmp.PROJECTION_MASTER_SID
                                                             AND R.RS_CONTRACT_SID = ADS.RS_CONTRACT_SID
                                           WHERE  PM.SAVE_FLAG = 1
                                                  AND TMP.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID) OUT_P
                                       ON WM.PROJECTION_MASTER_SID = OUT_P.PROJECTION_MASTER_SID
                        WHERE  EXISTS (SELECT H1.HELPER_TABLE_SID
                                       FROM   HELPER_TABLE H1
                                       WHERE  H1.LIST_NAME = 'WORKFLOWSTATUS'
                                              AND H1.DESCRIPTION = 'APPROVED'
                                              AND H1.HELPER_TABLE_SID = WM.WORKFLOW_STATUS_ID)) A
                --WHERE  WM.WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID 
                --                                FROM   HELPER_TABLE 
                --                                WHERE  LIST_NAME = 'WORKFLOWSTATUS' 
                --                                       AND DESCRIPTION = 'APPROVED')) A
                WHERE  RNO = 1

                --pulling latest approved Projection Ends here 
                --Pulling Discount amounts for latest approved Projection starts here
                INSERT INTO #TEMP_ARM_PROJ_DISCOUNTS WITH (tablock)
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             PERIOD_SID,
                             PROJ_TOTAL_DEMAND_ACCRUAL,
                             PROJECTION_RATE)
                SELECT ARM_ADJUSTMENT_DETAILS_SID,
                       PERIOD_SID,
                       Sum(PROJ_TOTAL_DEMAND_ACCRUAL),
                       Sum(PROJECTION_RATE)
                FROM   (SELECT T.ARM_ADJUSTMENT_DETAILS_SID,
                               NM.PERIOD_SID,
                               ( NM.PROJECTION_SALES ) AS PROJ_TOTAL_DEMAND_ACCRUAL,
                               NM.PROJECTION_RATE      AS PROJECTION_RATE
                        FROM   [DBO].[NM_DISCOUNT_PROJECTION] NM
                               INNER JOIN #TEMP_PROJ_DETAILS T
                                       ON T.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                          AND NM.RS_MODEL_SID = T.RS_MODEL_SID
                                          AND NM.RS_CONTRACT_SID = T.RS_CONTRACT_SID
                        WHERE  T.FORECASTING_TYPE = 'NON MANDATED'
                               AND EXISTS (SELECT 1
                                           FROM   #PERIOD P
                                           WHERE  P.PERIOD_SID = NM.PERIOD_SID)
                        UNION ALL
                        SELECT T.ARM_ADJUSTMENT_DETAILS_SID,
                               NM.PERIOD_SID,
                               ( NM.PROJECTION_DISCOUNT_DOLLAR ) AS PROJ_TOTAL_DEMAND_ACCRUAL,
                               NM.PROJECTION_RATE                AS PROJECTION_RATE
                        FROM   [DBO].[NM_PPA_PROJECTION] NM
                               INNER JOIN #TEMP_PROJ_DETAILS T
                                       ON T.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                          AND NM.RS_MODEL_SID = T.RS_MODEL_SID
                                          AND NM.RS_CONTRACT_SID = T.RS_CONTRACT_SID
                        WHERE  T.FORECASTING_TYPE = 'NON MANDATED'
                               AND EXISTS (SELECT 1
                                           FROM   #PERIOD P
                                           WHERE  P.PERIOD_SID = NM.PERIOD_SID)
                        UNION ALL
                        SELECT T.ARM_ADJUSTMENT_DETAILS_SID,
                               M.PERIOD_SID,
                               ( Isnull(M.PROJECTION_SALES, 0)
                                 + Isnull(MS.PROJECTION_SALES, 0) ) AS PROJ_TOTAL_DEMAND_ACCRUAL,
                               ( Isnull(M.PROJECTION_RATE, 0)
                                 + Isnull(MS.PROJECTION_RATE, 0) )  AS PROJECTION_RATE
                        FROM   #TEMP_PROJ_DETAILS T
                               INNER JOIN [DBO].[M_DISCOUNT_PROJECTION] M
                                       ON T.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID
                                          AND M.SAVE_FLAG = 1
                               JOIN #PERIOD P
                                 ON P.PERIOD_SID = M.PERIOD_SID
                               LEFT JOIN M_SUPPLEMENTAL_DISC_PROJ MS
                                      ON MS.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID
                                         AND MS.PERIOD_SID = M.PERIOD_SID
                                         AND P.PERIOD_SID = MS.PERIOD_SID
                        --AND P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                        WHERE  T.FORECASTING_TYPE = 'MANDATED') A
                GROUP  BY ARM_ADJUSTMENT_DETAILS_SID,
                          PERIOD_SID
            END
          --Pulling Discount amounts for latest approved Projection Ends here
          --Pulling Discounts from Latest Approved Projection for that CCP+D Combination for Tr-2 and Tr-5 Ends here  
          --pulling Actual Discounts for CCP+D Combination Actual Discounts(Tr-4) Starts here 
          ELSE IF @MODULE = 'Transaction 4 - Demand Payment Recon'
            BEGIN
                INSERT INTO #TEMP_ACTUAL_DISCOUNTS WITH (tablock)
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             PERIOD_SID,
                             ACTUAL_PAYMENTS,
                             ACTUAL_RATE)
                SELECT A.ARM_ADJUSTMENT_DETAILS_SID,
                       p.PERIOD_SID,
                       Sum(ad.DISCOUNT),
                       Isnull(Sum(ad.DISCOUNT / NULLIF(SALES, 0)), 0)
                FROM   #TEMP_ARM_PROJ_MASTER a
                       JOIN #period p
                         ON 1 = 1
                       JOIN ACTUALS_DETAILS ad
                         ON ad.CCP_DETAILS_SID = a.CCP_DETAILS_SID
                            AND a.RS_MODEL_SID = ad.RS_MODEL_SID
                            AND p.[PERIOD_SID] = ad.PERIOD_SID
                GROUP  BY A.ARM_ADJUSTMENT_DETAILS_SID,
                          p.PERIOD_SID
            /*INSERT INTO #TEMP_ACTUAL_DISCOUNTS (ARM_ADJUSTMENT_DETAILS_SID,
            PERIOD_SID,
            ACTUAL_PAYMENTS,
            ACTUAL_RATE)
              SELECT
                A2.ARM_ADJUSTMENT_DETAILS_SID,
                A2.PERIOD_SID,
                SUM(AMOUNT) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                                     + 1 ) AS ACTUAL_PAYMENTS,
                COALESCE((SUM(A1.AMOUNT) / NULLIF(SUM(A1.SALES_AMOUNT), 0)), 0) RATE
              FROM ACTUALS_MASTER A1
              JOIN #TEMP_ARM_PROJ_MASTER A2
                ON A2.PERIOD_DATE BETWEEN A1.ACCRUAL_ACTUAL_START_DATE AND A1.ACCRUAL_ACTUAL_END_DATE
                AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID
                AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID
                AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID
                AND A1.PROVISION_ID = A2.RS_ID
                AND DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_ACTUAL_START_DATE, -1)) >= @START_PERIOD_DATE
                AND A1.ACCRUAL_ACTUAL_END_DATE <= @END_PERIOD_DATE
              GROUP BY A2.ARM_ADJUSTMENT_DETAILS_SID,
                       A2.PERIOD_SID,
                       A1.CONTRACT_ID,
                       A1.ACCOUNT_ID,
                       A1.ITEM_ID,
                       A1.PROVISION_ID,
                       A1.ACCRUAL_ACTUAL_START_DATE,
                       A1.ACCRUAL_ACTUAL_END_DATE,
                       A1.QUANTITY_INCLUSION,
                       A1.COMPANY_MASTER_SID,
                       A1.CONTRACT_MASTER_SID,
                       A1.ITEM_MASTER_SID*/
            END

          --pulling Actual Discounts for CCP+D Combination Actual Discounts(Tr-4) Ends here 
          SET @SQL = ''

          -------------------------------INSERTION OF MAIN TABLE HAPPENING HERE------------------------          
          IF @MODULE = 'Transaction 2 - Demand Accrual'
            BEGIN
                --Insertion of Adjustment Summary fileds in one temp table starts here
                /*SET @SQL = concat('INSERT INTO #ARM_DEMAND_ADJ_SUMMARY
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
                                 ISNULL(SUM(ADJ.DEMAND_ACCRUAL), 0)                                                                              AS DEMAND_ACCRUAL,
                                 ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST), 0)                                                                   AS DEMAND_ACCRUAL_REFORECAST,
                                 ISNULL(SUM(ADJ.DEMAND_ACCRUAL), 0) 
                                 + ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST), 0)                                                                 AS TOTAL_DEMAND_ACCRUAL,
                                 ISNULL(SUM(TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0)                                                                         AS PROJ_TOTAL_DEMAND_ACCRUAL,
                                 ( ISNULL(SUM(ADJ.DEMAND_ACCRUAL), 0) 
                                   + ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST), 0) ) / NULLIF(ISNULL(SUM(TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0), 0) AS DEMAND_ACCRUAL_RATIO,
                                 ISNULL(( ISNULL(SUM(ADJ.DEMAND_ACCRUAL), 0) 
                                          + ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST), 0) ) - ISNULL(SUM(TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0), 0) AS VARIANCE,
                                 ISNULL(SUM(TAPD.PROJECTION_RATE), 0)                                                                                   AS PROJECTED_RATE
                          FROM   #TEMP_ARM_PROJ_MASTER TAM 
                                 LEFT JOIN ', @ARM_CURRENT_BALANCE, ' ADJ 
                                        ON TAM.CCP_DETAILS_SID = ADJ.CCP_DETAILS_SID 
                                           AND TAM.RS_MODEL_SID = ADJ.RS_MODEL_SID 
                                           AND TAM.PERIOD_SID = ADJ.PERIOD_SID 
                                 LEFT JOIN #TEMP_ARM_PROJ_DISCOUNTS TAPD 
                                        ON TAM.ARM_ADJUSTMENT_DETAILS_SID = TAPD.ARM_ADJUSTMENT_DETAILS_SID
                                           AND TAM.PERIOD_SID = TAPD.PERIOD_SID 
                          GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                                    TAM.PERIOD_SID', '')*/
                SET @SQL =Concat('SELECT @CONSTRAINT_NAME=CONSTRAINT_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_NAME =''', @ARM_DEMAND_ADJ_SUMMARY_TABLE, ''' AND CONSTRAINT_TYPE=''PRIMARY KEY''')

                EXEC Sp_executesql
                  @SQL,
                  N'@CONSTRAINT_NAME VARCHAR(1000) OUTPUT ',
                  @CONSTRAINT_NAME=@CONSTRAINT_NAME OUTPUT

                IF EXISTS (SELECT CASE
                                    WHEN @CONSTRAINT_NAME IS NOT NULL THEN 1
                                    ELSE NULL
                                  END)
                  BEGIN
                      SET @SQL =Concat('ALTER TABLE ', @ARM_DEMAND_ADJ_SUMMARY_TABLE, ' DROP CONSTRAINT ', @CONSTRAINT_NAME, ' ')

                      EXEC Sp_executesql
                        @SQL
                  END

                SET @SQL = Concat('IF EXISTS (SELECT 1 FROM   ', @ARM_DEMAND_ADJ_SUMMARY_TABLE, ')
                  BEGIN 
                      TRUNCATE TABLE ', @ARM_DEMAND_ADJ_SUMMARY_TABLE, '   
                  END 

                INSERT INTO ', @ARM_DEMAND_ADJ_SUMMARY_TABLE, '  with (tablock)
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
                       p.PERIOD_SID, 
                       ISNULL((ADJ.DEMAND_ACCRUAL), 0)                                                                              AS DEMAND_ACCRUAL,
                       ISNULL((ADJ.DEMAND_ACCRUAL_REFORECAST), 0)                                                                   AS DEMAND_ACCRUAL_REFORECAST,
                       ISNULL((ADJ.DEMAND_ACCRUAL), 0) 
                       + ISNULL((ADJ.DEMAND_ACCRUAL_REFORECAST), 0)                                                                 AS TOTAL_DEMAND_ACCRUAL,
                       ISNULL((TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0)                                                                         AS PROJ_TOTAL_DEMAND_ACCRUAL,
                       ( ISNULL((ADJ.DEMAND_ACCRUAL), 0) 
                         + ISNULL((ADJ.DEMAND_ACCRUAL_REFORECAST), 0) ) / NULLIF(ISNULL((TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0), 0) AS DEMAND_ACCRUAL_RATIO,
                       ISNULL(( ISNULL((ADJ.DEMAND_ACCRUAL), 0) 
                                + ISNULL((ADJ.DEMAND_ACCRUAL_REFORECAST), 0) ) - ISNULL((TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0), 0) AS VARIANCE,
                       ISNULL((TAPD.PROJECTION_RATE), 0)                                                                                   AS PROJECTED_RATE
                FROM   #TEMP_ARM_PROJ_MASTER TAM 
                       join #period p on 1=1
					   LEFT JOIN ', @ARM_CURRENT_BALANCE, ' ADJ 
                              ON TAM.CCP_DETAILS_SID = ADJ.CCP_DETAILS_SID 
                                 AND TAM.RS_MODEL_SID = ADJ.RS_MODEL_SID 
                                 AND p.PERIOD_SID = ADJ.PERIOD_SID 
                       LEFT JOIN #TEMP_ARM_PROJ_DISCOUNTS TAPD 
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = TAPD.ARM_ADJUSTMENT_DETAILS_SID
                                 AND p.PERIOD_SID = TAPD.PERIOD_SID 
                --GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                        --  p.PERIOD_SID
						  ', '')

                EXEC Sp_executesql
                  @SQL

                SET @SUMMARY_TABLE=@ARM_DEMAND_ADJ_SUMMARY_TABLE
            --Insertion of Adjustment Summary fileds in one temp table Ends here
            -- 
            --       --Balance suammary report calculation starts here for separtely for both Liability and Expense amount for Tr-2
            --       INSERT INTO #TEMP_AMOUNT (ARM_ADJUSTMENT_DETAILS_SID,
            --       PERIOD_SID,
            --       ACCRUAL_AMOUNT,
            --       INDICATOR)
            --         SELECT
            --           ARM_ADJUSTMENT_DETAILS_SID,
            --           PERIOD_SID,
            --           SUM(VARIANCE) AS ACCRUAL_AMOUNT,
            --           CASE
            --             WHEN SUM(VARIANCE) > 0 THEN 0
            --             WHEN SUM(VARIANCE) < 0 THEN 1
            --             ELSE NULL
            --           END AS INDICATOR
            --         FROM #ARM_DEMAND_ADJ_SUMMARY
            --         GROUP BY ARM_ADJUSTMENT_DETAILS_SID,
            --                  PERIOD_SID
            -- 	-----GALUAT-948 CREATED INDEX FOR FASTER RETRIVING OF DATA
            --       CREATE NONCLUSTERED INDEX IDX_TEMP_AMOUNT
            --         ON #TEMP_AMOUNT ( ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID,INDICATOR )
            --       
            -- 	  --GALUAT-948
            --       	  ;WITH CTE AS (SELECT * FROM #TEMP_AMOUNT)	 
            --       INSERT INTO #CURRENT_LIABILITY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --         SELECT AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --           ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) PERIOD_SID,
            --           SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --           SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --           ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT		 
            -- 		   FROM ARM_ADJ_RES_CCP AAC        
            --         INNER JOIN HELPER_TABLE HT
            --           ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --           AND HT.DESCRIPTION = 'LIABILITY'
            --           AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            -- 		  LEFT JOIN CTE A1
            --           ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --           AND AAC.CREDIT = A1.INDICATOR
            -- 		  AND A1.INDICATOR IS NOT NULL
            -- 		  LEFT JOIN CTE A2
            --           ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --           AND AAC.DEBIT = A2.INDICATOR
            -- 		    AND A2.INDICATOR IS NOT NULL
            -- 		WHERE AAC.CREDIT IS NOT NULL
            --         AND AAC.DEBIT IS NOT NULL AND 
            -- 		EXISTS (SELECT 1 FROM CTE C WHERE C.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID AND C.PERIOD_SID=ISNULL(A1.PERIOD_SID, A2.PERIOD_SID))
            -- 		 GROUP BY AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --                  ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)*/
            -- /* prior to GALUAT-948
            -- INSERT INTO #CURRENT_LIABILITY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --   SELECT
            --     TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --     ISNULL(A1.PERIOD_SID, A2.PERIOD_SID),
            --     SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --     SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --     ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
            --   FROM #TEMP_AMOUNT TAM
            --   JOIN ARM_ADJ_RES_CCP AAC
            --     ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
            --   INNER JOIN HELPER_TABLE HT
            --     ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --     AND HT.DESCRIPTION = 'LIABILITY'
            --     AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            --   LEFT JOIN #TEMP_AMOUNT A1
            --     ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --     AND AAC.CREDIT = A1.INDICATOR
            --   LEFT JOIN #TEMP_AMOUNT A2
            --     ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --     AND AAC.DEBIT = A2.INDICATOR
            --   JOIN PERIOD P
            --     ON P.PERIOD_SID = ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
            --     AND P.PERIOD_SID = TAM.PERIOD_SID
            --   WHERE AAC.CREDIT IS NOT NULL
            --   AND AAC.DEBIT IS NOT NULL
            --   GROUP BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --            ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)*/
            --     --GALUAT-948
            -- /* ;WITH CTE AS (SELECT * FROM #TEMP_AMOUNT)	
            -- INSERT INTO #CURRENT_EXPENSE (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --    SELECT AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --      ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) PERIOD_SID,
            --      SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --      SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --      ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT		 
            -- FROM ARM_ADJ_RES_CCP AAC        
            --    INNER JOIN HELPER_TABLE HT
            --      ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --      AND HT.DESCRIPTION = 'EXPENSE'
            --      AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            -- LEFT JOIN CTE A1
            --      ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --      AND AAC.CREDIT = A1.INDICATOR
            -- AND A1.INDICATOR IS NOT NULL
            -- LEFT JOIN CTE A2
            --      ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --      AND AAC.DEBIT = A2.INDICATOR
            --  AND A2.INDICATOR IS NOT NULL
            -- WHERE AAC.CREDIT IS NOT NULL
            --    AND AAC.DEBIT IS NOT NULL AND 
            -- EXISTS (SELECT 1 FROM CTE C WHERE C.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID AND C.PERIOD_SID=ISNULL(A1.PERIOD_SID, A2.PERIOD_SID))
            -- GROUP BY AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --             ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)*/
            -- /* PRIOR TO GALUAT-948
            -- INSERT INTO #CURRENT_EXPENSE (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --   SELECT
            --     TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --     ISNULL(A1.PERIOD_SID, A2.PERIOD_SID),
            --     SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --     SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --     ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
            --   FROM #TEMP_AMOUNT TAM
            --   JOIN ARM_ADJ_RES_CCP AAC
            --     ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
            --   INNER JOIN HELPER_TABLE HT
            --     ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --     AND HT.DESCRIPTION = 'EXPENSE'
            --     AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            --   LEFT JOIN #TEMP_AMOUNT A1
            --     ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --     AND AAC.CREDIT = A1.INDICATOR
            --   LEFT JOIN #TEMP_AMOUNT A2
            --     ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --     AND AAC.DEBIT = A2.INDICATOR
            --   JOIN PERIOD P
            --     ON P.PERIOD_SID = ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
            --     AND P.PERIOD_SID = TAM.PERIOD_SID
            --   WHERE AAC.CREDIT IS NOT NULL
            --   AND AAC.DEBIT IS NOT NULL
            --   GROUP BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --            ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)*/
            --     --GALUAT-948
            -- /* SET @SQL = CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_ADJUSTMENTS, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
            --                                         ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
            -- 			                   BEGIN 
            -- 										TRUNCATE TABLE ', @ARM_ADJUSTMENTS, '  
            -- 							   END
            -- 					;WITH CTE AS (SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            -- 			   TAM.PERIOD_SID AS PERIOD_SID,
            -- 			   AAC.ACCOUNT,
            -- 			   SUM(ABS(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT,
            -- 			   SUM(ABS(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT
            -- 		FROM   #TEMP_AMOUNT TAM
            -- 			   INNER JOIN ARM_ADJ_RES_CCP AAC 
            -- 				 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
            -- 						  AND AAC.ADJUSTMENT_TYPE = ', @ADJUSTMENT_TYPE, ' 
            -- 			   LEFT JOIN #TEMP_AMOUNT A1 
            -- 					  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
            -- 						 AND AAC.CREDIT = A1.INDICATOR
            -- 						 AND TAM.PERIOD_SID=A1.PERIOD_SID
            -- 						 AND A1.INDICATOR IS NOT NULL
            -- 			   LEFT JOIN #TEMP_AMOUNT A2 
            -- 					  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
            -- 						 AND AAC.DEBIT = A2.INDICATOR
            -- 						  AND TAM.PERIOD_SID=A2.PERIOD_SID 
            -- 						  AND A2.INDICATOR IS NOT NULL
            -- 				WHERE AAC.CREDIT IS NOT NULL AND AAC.DEBIT IS NOT NULL
            -- 		GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            -- 				  TAM.PERIOD_SID,
            -- 				  AAC.ACCOUNT)
            -- 		INSERT INTO ', @ARM_ADJUSTMENTS,
            --     '(ARM_ADJUSTMENT_DETAILS_SID, 
            --                            PERIOD_SID, 
            --                            ADJUSTMENT_TYPE,
            -- 					 ACCOUNT,
            --                            CREDIT,
            -- 					 DEBIT
            -- 					 )
            --               SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
            -- 				  TAM.PERIOD_SID,
            -- 				  ', @ADJUSTMENT_TYPE, '  AS ADJUSTMENT_TYPE,
            -- 				   AAC.ACCOUNT,
            -- 				   AAC.CREDIT,
            -- 				   AAC.DEBIT 
            -- 				 FROM  #TEMP_AMOUNT TAM
            -- 			   INNER JOIN CTE AAC 
            -- 				 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID AND TAM.PERIOD_SID=AAC.PERIOD_SID')*/
            -- /* pRIOR TO GALUAT-948
            -- SET @SQL = CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_ADJUSTMENTS, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
            --                                     ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
            --                   BEGIN 
            -- 						TRUNCATE TABLE ', @ARM_ADJUSTMENTS, '  
            -- 			   END
            -- INSERT INTO ', @ARM_ADJUSTMENTS,
            -- '(ARM_ADJUSTMENT_DETAILS_SID, 
            --                        PERIOD_SID, 
            --                        ADJUSTMENT_TYPE,
            -- 	 ACCOUNT,
            --                        CREDIT,
            -- 	 DEBIT
            -- 	 )
            --           SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --   TAM.PERIOD_SID AS PERIOD_SID,
            --   ', @ADJUSTMENT_TYPE, ' AS ADJUSTMENT_TYPE,
            --   ACCOUNT,
            --   SUM(ABS(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT,
            --   SUM(ABS(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT
            -- FROM    #TEMP_AMOUNT TAM 
            -- JOIN PERIOD P ON P.PERIOD_SID=TAM.PERIOD_SID 
            --  LEFT JOIN ARM_ADJ_RES_CCP AAC 
            --  ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
            -- 		  AND AAC.ADJUSTMENT_TYPE = ', @ADJUSTMENT_TYPE, ' 
            --   LEFT JOIN #TEMP_AMOUNT A1 
            -- 	  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
            -- 		 AND AAC.CREDIT = A1.INDICATOR
            -- 		 AND P.PERIOD_SID=A1.PERIOD_SID 
            --   LEFT JOIN #TEMP_AMOUNT A2 
            -- 	  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
            -- 		 AND AAC.DEBIT = A2.INDICATOR
            -- 		 AND P.PERIOD_SID=A2.PERIOD_SID
            -- WHERE CREDIT IS NOT NULL AND DEBIT IS NOT NULL AND P.PERIOD_DATE IS NOT NULL
            -- GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --   TAM.PERIOD_SID,
            --   AAC.ACCOUNT')*/
            --     --Balance suammary report calculation Ends here for separtely for both Liability and Expense amount for Tr-2 
            --     SET @SQL = ''
            -- --Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-2) for CCP+D+Period Combination Starts here
            -- /*
            --       SET @SQL = CONCAT('IF EXISTS (SELECT 1 FROM   ' , @ARM_DEMAND_ADJ_SUMMARY_TABLE , ')
            --                   BEGIN 
            --                       TRUNCATE TABLE ', @ARM_DEMAND_ADJ_SUMMARY_TABLE, '   
            --                   END 
            -- 
            --                 INSERT INTO ', @ARM_DEMAND_ADJ_SUMMARY_TABLE, ' 
            --                             (ARM_ADJUSTMENT_DETAILS_SID, 
            --                              PERIOD_SID, 
            --                              DEMAND_ACCRUAL, 
            --                              DEMAND_ACCRUAL_REFORECAST, 
            --                              TOTAL_DEMAND_ACCRUAL, 
            --                              PROJ_TOTAL_DEMAND_ACCRUAL, 
            --                              DEMAND_ACCRUAL_RATIO, 
            --                              VARIANCE, 
            --                              PROJECTED_RATE,
            -- 							 LIABILITY_AMOUNT,
            -- 							 EXPENSE_AMOUNT) 
            --                 SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --                        TAM.PERIOD_SID, 
            --                        TAM.DEMAND_ACCRUAL AS DEMAND_ACCRUAL,                                                                        
            --                        TAM.DEMAND_ACCRUAL_REFORECAST AS DEMAND_ACCRUAL_REFORECAST,                       
            --                        TAM.TOTAL_DEMAND_ACCRUAL AS TOTAL_DEMAND_ACCRUAL,
            --                        TAM.PROJ_TOTAL_DEMAND_ACCRUAL AS PROJ_TOTAL_DEMAND_ACCRUAL,
            --                        TAM.DEMAND_ACCRUAL_RATIO AS DEMAND_ACCRUAL_RATIO,
            --                        TAM.VARIANCE AS VARIANCE,
            --                        TAM.PROJECTED_RATE AS PROJECTED_RATE,
            -- 					   CL.CURRENT_AMOUNT AS LIABILITY_AMOUNT,
            -- 					   CE.CURRENT_AMOUNT AS EXPENSE_AMOUNT
            --                 FROM   #ARM_DEMAND_ADJ_SUMMARY TAM
            -- 					  LEFT JOIN #CURRENT_LIABILITY CL
            --                               ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CL.ARM_ADJUSTMENT_DETAILS_SID
            --                                  AND TAM.PERIOD_SID = CL.PERIOD_SID 
            -- 					  LEFT JOIN #CURRENT_EXPENSE CE
            --                               ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CE.ARM_ADJUSTMENT_DETAILS_SID
            --                                  AND TAM.PERIOD_SID = CE.PERIOD_SID ')
            -- 
            --       EXEC sp_executesql @SQL*/
            END
          --Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-2) for CCP+D+Period Combination Ends here	
          ELSE IF @MODULE = 'Transaction 5 - Demand Reforecast True-up'
            BEGIN
                SET @SQL =Concat('SELECT @CONSTRAINT_NAME=CONSTRAINT_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_NAME =''', @ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE, ''' AND CONSTRAINT_TYPE=''PRIMARY KEY''')

                EXEC Sp_executesql
                  @SQL,
                  N'@CONSTRAINT_NAME VARCHAR(1000) OUTPUT ',
                  @CONSTRAINT_NAME=@CONSTRAINT_NAME OUTPUT

                IF EXISTS (SELECT CASE
                                    WHEN @CONSTRAINT_NAME IS NOT NULL THEN 1
                                    ELSE NULL
                                  END)
                  BEGIN
                      SET @SQL =Concat('ALTER TABLE ', @ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE, ' DROP CONSTRAINT ', @CONSTRAINT_NAME, ' ')

                      EXEC Sp_executesql
                        @SQL
                  END

                SET @SQL = Concat('IF EXISTS (SELECT 1 FROM ', @ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE, ') 
                  BEGIN 
                      TRUNCATE TABLE ', @ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE, '   
                  END 
  
                INSERT INTO ', @ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE, '  with (tablock)
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
                       p.PERIOD_SID, 
                       ISNULL((ADJ.DEMAND_ACCRUAL), 0)                                                                              AS DEMAND_ACCRUAL,
                       ISNULL((ADJ.DEMAND_ACCRUAL_REFORECAST), 0)                                                                   AS DEMAND_ACCRUAL_REFORECAST,
                       ISNULL((ADJ.DEMAND_ACCRUAL), 0) 
                       + ISNULL((ADJ.DEMAND_ACCRUAL_REFORECAST), 0)                                                                 AS TOTAL_DEMAND_ACCRUAL,
                       ISNULL((TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0)                                                                         AS PROJ_TOTAL_DEMAND_ACCRUAL,
                       ( ISNULL((ADJ.DEMAND_ACCRUAL), 0) 
                         + ISNULL((ADJ.DEMAND_ACCRUAL_REFORECAST), 0) ) / NULLIF(ISNULL((TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0), 0) AS DEMAND_ACCRUAL_RATIO,
                       ISNULL(( ISNULL((ADJ.DEMAND_ACCRUAL), 0) 
                                + ISNULL((ADJ.DEMAND_ACCRUAL_REFORECAST), 0) ) - ISNULL((TAPD.PROJ_TOTAL_DEMAND_ACCRUAL), 0), 0) AS VARIANCE,
                       ISNULL((TAPD.PROJECTION_RATE), 0)                                                                                   AS PROJECTED_RATE
                FROM   #TEMP_ARM_PROJ_MASTER TAM 
                       join #period p on 1=1
					   LEFT JOIN ', @ARM_CURRENT_BALANCE, ' ADJ 
                              ON TAM.CCP_DETAILS_SID = ADJ.CCP_DETAILS_SID 
                                 AND TAM.RS_MODEL_SID = ADJ.RS_MODEL_SID 
                                 AND p.PERIOD_SID = ADJ.PERIOD_SID 
                       LEFT JOIN #TEMP_ARM_PROJ_DISCOUNTS TAPD 
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = TAPD.ARM_ADJUSTMENT_DETAILS_SID
                                 AND p.PERIOD_SID = TAPD.PERIOD_SID 
              --  GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
               --           p.PERIOD_SID
						  ', '')

                EXEC Sp_executesql
                  @SQL

                SET @SUMMARY_TABLE=@ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE
            --Balance suammary report calculation starts here for separtely for both Liability and Expense amount for Tr-5
            --     INSERT INTO #TEMP_AMOUNT (ARM_ADJUSTMENT_DETAILS_SID,
            --     PERIOD_SID,
            --     ACCRUAL_AMOUNT,
            --     INDICATOR)
            --       SELECT
            --         ARM_ADJUSTMENT_DETAILS_SID,
            --         PERIOD_SID,
            --         SUM(VARIANCE) AS ACCRUAL_AMOUNT,
            --         CASE
            --           WHEN SUM(VARIANCE) > 0 THEN 0
            --           WHEN SUM(VARIANCE) < 0 THEN 1
            --           ELSE NULL
            --         END AS INDICATOR
            --       FROM #ARM_DEMAND_REFORECAST_SUMMARY
            --       GROUP BY ARM_ADJUSTMENT_DETAILS_SID,
            --                PERIOD_SID
            --	-----GALUAT-948 CREATED INDEX FOR FASTER RETRIVING OF DATA
            --     CREATE NONCLUSTERED INDEX IDX_TEMP_AMOUNT
            --       ON #TEMP_AMOUNT ( ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID,INDICATOR )
            --
            --     SET @SQL = ''
            --
            --     --GALUAT-948
            --	  SET @SQL = CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_ADJUSTMENTS, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
            --                                         ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
            --					                   BEGIN 
            --												TRUNCATE TABLE ', @ARM_ADJUSTMENTS, '  
            --									   END
            --							;WITH CTE AS (SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --					   TAM.PERIOD_SID AS PERIOD_SID,
            --					   AAC.ACCOUNT,
            --					   SUM(ABS(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT,
            --					   SUM(ABS(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT
            --				FROM   #TEMP_AMOUNT TAM
            --					   INNER JOIN ARM_ADJ_RES_CCP AAC 
            --						 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
            --								  AND AAC.ADJUSTMENT_TYPE = ', @ADJUSTMENT_TYPE, ' 
            --					   LEFT JOIN #TEMP_AMOUNT A1 
            --							  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
            --								 AND AAC.CREDIT = A1.INDICATOR
            --								 AND TAM.PERIOD_SID=A1.PERIOD_SID
            --								 AND A1.INDICATOR IS NOT NULL
            --					   LEFT JOIN #TEMP_AMOUNT A2 
            --							  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
            --								 AND AAC.DEBIT = A2.INDICATOR
            --								  AND TAM.PERIOD_SID=A2.PERIOD_SID 
            --								  AND A2.INDICATOR IS NOT NULL
            --						WHERE AAC.CREDIT IS NOT NULL AND AAC.DEBIT IS NOT NULL
            --				GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --						  TAM.PERIOD_SID,
            --						  AAC.ACCOUNT)
            --				INSERT INTO ', @ARM_ADJUSTMENTS,
            --     '(ARM_ADJUSTMENT_DETAILS_SID, 
            --                            PERIOD_SID, 
            --                            ADJUSTMENT_TYPE,
            --							 ACCOUNT,
            --                            CREDIT,
            --							 DEBIT
            --							 )
            --               SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --						  TAM.PERIOD_SID,
            --						  ', @ADJUSTMENT_TYPE, '  AS ADJUSTMENT_TYPE,
            --						   AAC.ACCOUNT,
            --						   AAC.CREDIT,
            --						   AAC.DEBIT 
            --						 FROM  #TEMP_AMOUNT TAM
            --					   INNER JOIN CTE AAC 
            --						 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID AND TAM.PERIOD_SID=AAC.PERIOD_SID')
            --    /*SET @SQL = CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_ADJUSTMENTS, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
            --                                         ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
            --					                   BEGIN 
            --												TRUNCATE TABLE ', @ARM_ADJUSTMENTS, '  
            --									   END
            --				INSERT INTO ', @ARM_ADJUSTMENTS,
            --     '(ARM_ADJUSTMENT_DETAILS_SID, 
            --                            PERIOD_SID, 
            --                            ADJUSTMENT_TYPE,
            --							 ACCOUNT,
            --                            CREDIT,
            --							 DEBIT
            --							 )
            --               SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --					   TAM.PERIOD_SID AS PERIOD_SID,
            --					   ', @ADJUSTMENT_TYPE, ' AS ADJUSTMENT_TYPE,
            --					   AAC.ACCOUNT,
            --					   SUM(ABS(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT,
            --					   SUM(ABS(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT
            --				FROM  #TEMP_AMOUNT TAM 
            --				JOIN PERIOD P ON P.PERIOD_SID=TAM.PERIOD_SID
            --					  LEFT JOIN ARM_ADJ_RES_CCP AAC 
            --						 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
            --								  AND AAC.ADJUSTMENT_TYPE = ', @ADJUSTMENT_TYPE, ' 
            --					   LEFT JOIN #TEMP_AMOUNT A1 
            --							  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
            --								 AND AAC.CREDIT = A1.INDICATOR
            --								 AND P.PERIOD_SID=A1.PERIOD_SID 
            --					   LEFT JOIN #TEMP_AMOUNT A2 
            --							  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
            --								 AND AAC.DEBIT = A2.INDICATOR
            --								 AND P.PERIOD_SID=A2.PERIOD_SID  
            --						WHERE AAC.CREDIT IS NOT NULL AND AAC.DEBIT IS NOT NULL AND P.PERIOD_DATE IS NOT NULL
            --				GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --						  TAM.PERIOD_SID,
            --						  AAC.ACCOUNT')*/
            --
            --     EXEC sp_executesql @SQL
            --
            --     --GALUAT-948
            --     	  ;WITH CTE AS (SELECT * FROM #TEMP_AMOUNT)	 
            --     INSERT INTO #CURRENT_LIABILITY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --       SELECT AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --         ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) PERIOD_SID,
            --         SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --         SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --         ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT		 
            --		   FROM ARM_ADJ_RES_CCP AAC        
            --       INNER JOIN HELPER_TABLE HT
            --         ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --         AND HT.DESCRIPTION = 'LIABILITY'
            --         AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            --		  LEFT JOIN CTE A1
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.CREDIT = A1.INDICATOR
            --		  AND A1.INDICATOR IS NOT NULL
            --		  LEFT JOIN CTE A2
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.DEBIT = A2.INDICATOR
            --		    AND A2.INDICATOR IS NOT NULL
            --		WHERE AAC.CREDIT IS NOT NULL
            --       AND AAC.DEBIT IS NOT NULL AND 
            --		EXISTS (SELECT 1 FROM CTE C WHERE C.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID AND C.PERIOD_SID=ISNULL(A1.PERIOD_SID, A2.PERIOD_SID))
            --		 GROUP BY AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --                ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
            --	
            --     /* PRIOR TO GALUAT-948
            --	  INSERT INTO #CURRENT_LIABILITY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --       SELECT
            --         TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --         ISNULL(A1.PERIOD_SID, A2.PERIOD_SID),
            --         SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --         SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --         ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
            --       FROM #TEMP_AMOUNT TAM
            --       JOIN ARM_ADJ_RES_CCP AAC
            --         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
            --       INNER JOIN HELPER_TABLE HT
            --         ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --         AND HT.DESCRIPTION = 'LIABILITY'
            --         AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            --       LEFT JOIN #TEMP_AMOUNT A1
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.CREDIT = A1.INDICATOR
            --       LEFT JOIN #TEMP_AMOUNT A2
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.DEBIT = A2.INDICATOR
            --       JOIN PERIOD P
            --         ON P.PERIOD_SID = ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
            --         AND P.PERIOD_SID = TAM.PERIOD_SID
            --       WHERE AAC.CREDIT IS NOT NULL
            --       AND AAC.DEBIT IS NOT NULL
            --       GROUP BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --                ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)*/
            --
            --     --GALUAT-948
            --     ;WITH CTE AS (SELECT * FROM #TEMP_AMOUNT)	
            --		INSERT INTO #CURRENT_EXPENSE (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --       SELECT AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --         ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) PERIOD_SID,
            --         SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --         SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --         ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT		 
            --		   FROM ARM_ADJ_RES_CCP AAC        
            --       INNER JOIN HELPER_TABLE HT
            --         ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --         AND HT.DESCRIPTION = 'EXPENSE'
            --         AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            --		  LEFT JOIN CTE A1
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.CREDIT = A1.INDICATOR
            --		  AND A1.INDICATOR IS NOT NULL
            --		  LEFT JOIN CTE A2
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.DEBIT = A2.INDICATOR
            --		    AND A2.INDICATOR IS NOT NULL
            --		WHERE AAC.CREDIT IS NOT NULL
            --       AND AAC.DEBIT IS NOT NULL AND 
            --		EXISTS (SELECT 1 FROM CTE C WHERE C.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID AND C.PERIOD_SID=ISNULL(A1.PERIOD_SID, A2.PERIOD_SID))
            --		 GROUP BY AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --                ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
            --     /*----PRIOR TO GALUAT-948
            --	  INSERT INTO #CURRENT_EXPENSE (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --       SELECT
            --         TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --         ISNULL(A1.PERIOD_SID, A2.PERIOD_SID),
            --         SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --         SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --         ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
            --       FROM #TEMP_AMOUNT TAM
            --       JOIN ARM_ADJ_RES_CCP AAC
            --         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
            --       INNER JOIN HELPER_TABLE HT
            --         ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --         AND HT.DESCRIPTION = 'EXPENSE'
            --         AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            --       LEFT JOIN #TEMP_AMOUNT A1
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.CREDIT = A1.INDICATOR
            --       LEFT JOIN #TEMP_AMOUNT A2
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.DEBIT = A2.INDICATOR
            --       JOIN PERIOD P
            --         ON P.PERIOD_SID = ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
            --         AND P.PERIOD_SID = TAM.PERIOD_SID
            --       WHERE AAC.CREDIT IS NOT NULL
            --       AND AAC.DEBIT IS NOT NULL
            --       GROUP BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --                ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)*/
            --
            --     --Balance suammary report calculation Ends here for separtely for both Liability and Expense amount for Tr-5
            --
            --     SET @SQL = ''
            --
            --     --Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-5) for CCP+D+Period Combination Starts here	
            --
            --     SET @SQL = CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE, ') 
            --                 BEGIN 
            --                     TRUNCATE TABLE ', @ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE, '   
            --                 END 
            --
            --               INSERT INTO ', @ARM_DEMAND_RF_TRUE_UP_SUMMARY_TABLE, ' 
            --                           (ARM_ADJUSTMENT_DETAILS_SID, 
            --                            PERIOD_SID, 
            --                            DEMAND_ACCRUAL, 
            --                            DEMAND_ACCRUAL_REFORECAST, 
            --                            TOTAL_DEMAND_ACCRUAL, 
            --                            PROJ_TOTAL_DEMAND_ACCRUAL, 
            --                            DEMAND_ACCRUAL_RATIO, 
            --                            VARIANCE, 
            --                            PROJECTED_RATE,
            --							 LIABILITY_AMOUNT,
            --							 EXPENSE_AMOUNT) 
            --               SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --                      TAM.PERIOD_SID, 
            --                      TAM.DEMAND_ACCRUAL AS DEMAND_ACCRUAL,
            --                      TAM.DEMAND_ACCRUAL_REFORECAST AS DEMAND_ACCRUAL_REFORECAST,
            --                       TAM.TOTAL_DEMAND_ACCRUAL,
            --                      TAM.PROJ_TOTAL_DEMAND_ACCRUAL,
            --                      TAM.DEMAND_ACCRUAL_RATIO,
            --                      TAM.VARIANCE,
            --                      TAM.PROJECTED_RATE,
            --					   CL.CURRENT_AMOUNT AS LIABILITY_AMOUNT,
            --					   CE.CURRENT_AMOUNT AS EXPENSE_AMOUNT                                                                                  
            --               FROM   #ARM_DEMAND_REFORECAST_SUMMARY TAM
            --					  LEFT JOIN #CURRENT_LIABILITY CL
            --                             ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CL.ARM_ADJUSTMENT_DETAILS_SID
            --                                AND TAM.PERIOD_SID = CL.PERIOD_SID 
            --					  LEFT JOIN #CURRENT_EXPENSE CE
            --                             ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CE.ARM_ADJUSTMENT_DETAILS_SID
            --                                AND TAM.PERIOD_SID = CE.PERIOD_SID ')
            --     EXEC sp_executesql @SQL
            --
            END
          --Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-5) for CCP+D+Period Combination Ends here
          ELSE IF @MODULE = 'Transaction 4 - Demand Payment Recon'
            BEGIN
                /*   SET @SQL = Concat('IF EXISTS (SELECT 1 FROM ', @ARM_DEMAND_RECON_SUMMARY_TABLE, ') 
                               BEGIN 
                                   TRUNCATE TABLE ', @ARM_DEMAND_RECON_SUMMARY_TABLE, '   
                               END 
                
                             INSERT INTO ', @ARM_DEMAND_RECON_SUMMARY_TABLE, ' with (tablock)
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
                                    p.PERIOD_SID, 
                                    ISNULL(SUM(ADJ.DEMAND_ACCRUAL), 0)                                                                  AS DEMAND_ACCRUAL,
                                    ISNULL(SUM(ADJ.DEMAND_ACCRUAL_REFORECAST), 0)                                                       AS DEMAND_ACCRUAL_REFORECAST,
                                    ISNULL(SUM(ADJ.DEMAND_PAYMENT_RECON), 0)                                                            AS DEMAND_PAYMENT_RECON,
                                    ISNULL(SUM(ADJ.DEMAND_ACCRUAL) 
                                           + SUM(ADJ.DEMAND_ACCRUAL_REFORECAST) 
                                           + SUM(ADJ.DEMAND_PAYMENT_RECON), 0)                                                          AS TOTAL_DEMAND_ACCRUAL,
                                    ISNULL(SUM(TAPD.ACTUAL_PAYMENTS), 0)                                                                       AS ACTUAL_PAYMENTS,
                                    COALESCE (( ISNULL(SUM(ADJ.DEMAND_ACCRUAL) 
                                                       + SUM(ADJ.DEMAND_ACCRUAL_REFORECAST) 
                                                       + SUM(ADJ.DEMAND_PAYMENT_RECON), 0) ) / NULLIF(SUM(TAPD.ACTUAL_PAYMENTS), 0), 0) AS PAYMENT_RATIO,
                                    COALESCE(( ISNULL(SUM(ADJ.DEMAND_ACCRUAL) 
                                                      + SUM(ADJ.DEMAND_ACCRUAL_REFORECAST) 
                                                      + SUM(ADJ.DEMAND_PAYMENT_RECON), 0) ) - ISNULL(SUM(TAPD.ACTUAL_PAYMENTS), 0), 0)  AS VARIANCE,
                                    ISNULL(SUM(TAPD.ACTUAL_RATE), 0)                                                                           AS PROJECTED_RATE
                             FROM   #TEMP_ARM_PROJ_MASTER TAM 
                                    join #period p on 1=1
                		   LEFT JOIN ', @ARM_CURRENT_BALANCE, ' ADJ 
                                           ON TAM.CCP_DETAILS_SID = ADJ.CCP_DETAILS_SID 
                                              AND TAM.RS_MODEL_SID = ADJ.RS_MODEL_SID 
                                              AND p.PERIOD_SID = ADJ.PERIOD_SID 
                                    LEFT JOIN #TEMP_ACTUAL_DISCOUNTS TAPD 
                                           ON TAM.ARM_ADJUSTMENT_DETAILS_SID = TAPD.ARM_ADJUSTMENT_DETAILS_SID
                                              AND p.PERIOD_SID = TAPD.PERIOD_SID 
                             --GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                                 --      p.PERIOD_SID
                			  ', '')*/
                SET @SQL =Concat('SELECT @CONSTRAINT_NAME=CONSTRAINT_NAME FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS WHERE TABLE_NAME =''', @ARM_DEMAND_RECON_SUMMARY_TABLE, ''' AND CONSTRAINT_TYPE=''PRIMARY KEY''')

                EXEC Sp_executesql
                  @SQL,
                  N'@CONSTRAINT_NAME VARCHAR(1000) OUTPUT ',
                  @CONSTRAINT_NAME=@CONSTRAINT_NAME OUTPUT

                IF EXISTS (SELECT CASE
                                    WHEN @CONSTRAINT_NAME IS NOT NULL THEN 1
                                    ELSE NULL
                                  END)
                  BEGIN
                      SET @SQL =Concat('ALTER TABLE ', @ARM_DEMAND_RECON_SUMMARY_TABLE, ' DROP CONSTRAINT ', @CONSTRAINT_NAME, ' ')

                      EXEC Sp_executesql
                        @SQL
                  END

                SET @SQL = Concat('IF EXISTS (SELECT 1 FROM ', @ARM_DEMAND_RECON_SUMMARY_TABLE, ') 
                  BEGIN 
                      TRUNCATE TABLE ', @ARM_DEMAND_RECON_SUMMARY_TABLE, '   
                  END 

                INSERT INTO ', @ARM_DEMAND_RECON_SUMMARY_TABLE, ' with (tablock)
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
                       p.PERIOD_SID, 
                       ISNULL((ADJ.DEMAND_ACCRUAL), 0)                                                                  AS DEMAND_ACCRUAL,
                       ISNULL((ADJ.DEMAND_ACCRUAL_REFORECAST), 0)                                                       AS DEMAND_ACCRUAL_REFORECAST,
                       ISNULL((ADJ.DEMAND_PAYMENT_RECON), 0)                                                            AS DEMAND_PAYMENT_RECON,
                       ISNULL((ADJ.DEMAND_ACCRUAL) 
                              + (ADJ.DEMAND_ACCRUAL_REFORECAST) 
                              + (ADJ.DEMAND_PAYMENT_RECON), 0)                                                          AS TOTAL_DEMAND_ACCRUAL,
                       ISNULL((TAPD.ACTUAL_PAYMENTS), 0)                                                                       AS ACTUAL_PAYMENTS,
                       COALESCE (( ISNULL((ADJ.DEMAND_ACCRUAL) 
                                          + (ADJ.DEMAND_ACCRUAL_REFORECAST) 
                                          + (ADJ.DEMAND_PAYMENT_RECON), 0) ) / NULLIF((TAPD.ACTUAL_PAYMENTS), 0), 0) AS PAYMENT_RATIO,
                       COALESCE(( ISNULL((ADJ.DEMAND_ACCRUAL) 
                                         + (ADJ.DEMAND_ACCRUAL_REFORECAST) 
                                         + (ADJ.DEMAND_PAYMENT_RECON), 0) ) - ISNULL((TAPD.ACTUAL_PAYMENTS), 0), 0)  AS VARIANCE,
                       ISNULL((TAPD.ACTUAL_RATE), 0)                                                                           AS PROJECTED_RATE
                FROM   #TEMP_ARM_PROJ_MASTER TAM 
                       join #period p on 1=1
					   LEFT JOIN ', @ARM_CURRENT_BALANCE, ' ADJ 
                              ON TAM.CCP_DETAILS_SID = ADJ.CCP_DETAILS_SID 
                                 AND TAM.RS_MODEL_SID = ADJ.RS_MODEL_SID 
                                 AND p.PERIOD_SID = ADJ.PERIOD_SID 
                       LEFT JOIN #TEMP_ACTUAL_DISCOUNTS TAPD 
                              ON TAM.ARM_ADJUSTMENT_DETAILS_SID = TAPD.ARM_ADJUSTMENT_DETAILS_SID
                                 AND p.PERIOD_SID = TAPD.PERIOD_SID 
                --GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
                    --      p.PERIOD_SID
						  ', '')

                -- SELECT @SQL
                EXEC Sp_executesql
                  @SQL

                SET @SUMMARY_TABLE=@ARM_DEMAND_RECON_SUMMARY_TABLE
            --     --Balance suammary report calculation starts here for separtely for both Liability and Expense amount for Tr-4
            --     INSERT INTO #TEMP_AMOUNT (ARM_ADJUSTMENT_DETAILS_SID,
            --     PERIOD_SID,
            --     ACCRUAL_AMOUNT,
            --     INDICATOR)
            --       SELECT
            --         ARM_ADJUSTMENT_DETAILS_SID,
            --         PERIOD_SID,
            --         SUM(VARIANCE) AS ACCRUAL_AMOUNT,
            --         CASE
            --           WHEN SUM(VARIANCE) > 0 THEN 0
            --           WHEN SUM(VARIANCE) < 0 THEN 1
            --           ELSE NULL
            --         END AS INDICATOR
            --       FROM #ARM_DEMAND_RECON_SUMMARY
            --       GROUP BY ARM_ADJUSTMENT_DETAILS_SID,
            --                PERIOD_SID
            --	-----GALUAT-948 CREATED INDEX FOR FASTER RETRIVING OF DATA
            --     CREATE NONCLUSTERED INDEX IDX_TEMP_AMOUNT
            --       ON #TEMP_AMOUNT ( ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID,INDICATOR )
            --
            --     SET @SQL = ''
            --
            --     --GALUAT-948
            --	  SET @SQL = CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_ADJUSTMENTS, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
            --                                         ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
            --					                   BEGIN 
            --												TRUNCATE TABLE ', @ARM_ADJUSTMENTS, '  
            --									   END
            --							;WITH CTE AS (SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --					   TAM.PERIOD_SID AS PERIOD_SID,
            --					   AAC.ACCOUNT,
            --					   SUM(ABS(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT,
            --					   SUM(ABS(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT
            --				FROM   #TEMP_AMOUNT TAM
            --					   INNER JOIN ARM_ADJ_RES_CCP AAC 
            --						 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
            --								  AND AAC.ADJUSTMENT_TYPE = ', @ADJUSTMENT_TYPE, ' 
            --					   LEFT JOIN #TEMP_AMOUNT A1 
            --							  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
            --								 AND AAC.CREDIT = A1.INDICATOR
            --								 AND TAM.PERIOD_SID=A1.PERIOD_SID
            --								 AND A1.INDICATOR IS NOT NULL
            --					   LEFT JOIN #TEMP_AMOUNT A2 
            --							  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
            --								 AND AAC.DEBIT = A2.INDICATOR
            --								  AND TAM.PERIOD_SID=A2.PERIOD_SID 
            --								  AND A2.INDICATOR IS NOT NULL
            --						WHERE AAC.CREDIT IS NOT NULL AND AAC.DEBIT IS NOT NULL
            --				GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --						  TAM.PERIOD_SID,
            --						  AAC.ACCOUNT)
            --				INSERT INTO ', @ARM_ADJUSTMENTS,
            --     '(ARM_ADJUSTMENT_DETAILS_SID, 
            --                            PERIOD_SID, 
            --                            ADJUSTMENT_TYPE,
            --							 ACCOUNT,
            --                            CREDIT,
            --							 DEBIT
            --							 )
            --               SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --						  TAM.PERIOD_SID,
            --						  ', @ADJUSTMENT_TYPE, '  AS ADJUSTMENT_TYPE,
            --						   AAC.ACCOUNT,
            --						   AAC.CREDIT,
            --						   AAC.DEBIT 
            --						 FROM  #TEMP_AMOUNT TAM
            --					   INNER JOIN CTE AAC 
            --						 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID AND TAM.PERIOD_SID=AAC.PERIOD_SID')
            --
            --     /* pRIOR TO GALUAT-948
            --	  SET @SQL = CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_ADJUSTMENTS, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
            --                                         ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
            --					                   BEGIN 
            --												TRUNCATE TABLE ', @ARM_ADJUSTMENTS, '  
            --									   END
            --				INSERT INTO ', @ARM_ADJUSTMENTS,
            --     '(ARM_ADJUSTMENT_DETAILS_SID, 
            --                            PERIOD_SID, 
            --                            ADJUSTMENT_TYPE,
            --							 ACCOUNT,
            --                            CREDIT,
            --							 DEBIT
            --							 )
            --               SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --					   TAM.PERIOD_SID AS PERIOD_SID,
            --					   ', @ADJUSTMENT_TYPE, '  AS ADJUSTMENT_TYPE,
            --					   AAC.ACCOUNT,
            --					   SUM(ABS(A1.ACCRUAL_AMOUNT))                                                     AS CREDIT,
            --					   SUM(ABS(A2.ACCRUAL_AMOUNT))                                                     AS DEBIT
            --				FROM   #TEMP_AMOUNT TAM 
            --				JOIN PERIOD P ON P.PERIOD_SID=TAM.PERIOD_SID
            --					   LEFT JOIN ARM_ADJ_RES_CCP AAC 
            --						 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID 
            --								  AND AAC.ADJUSTMENT_TYPE = ', @ADJUSTMENT_TYPE, ' 
            --					   LEFT JOIN #TEMP_AMOUNT A1 
            --							  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID 
            --								 AND AAC.CREDIT = A1.INDICATOR
            --								 AND P.PERIOD_SID=A1.PERIOD_SID
            --					   LEFT JOIN #TEMP_AMOUNT A2 
            --							  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID 
            --								 AND AAC.DEBIT = A2.INDICATOR
            --								 AND P.PERIOD_SID=A2.PERIOD_SID 
            --						WHERE AAC.CREDIT IS NOT NULL AND AAC.DEBIT IS NOT NULL AND P.PERIOD_DATE IS NOT NULL
            --				GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --						  TAM.PERIOD_SID,
            --						  AAC.ACCOUNT')*/
            --
            --     EXEC sp_executesql @SQL
            --
            --     --GALUAT-948
            --     	  ;WITH CTE AS (SELECT * FROM #TEMP_AMOUNT)	 
            --     INSERT INTO #CURRENT_LIABILITY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --       SELECT AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --         ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) PERIOD_SID,
            --         SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --         SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --         ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT		 
            --		   FROM ARM_ADJ_RES_CCP AAC        
            --       INNER JOIN HELPER_TABLE HT
            --         ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --         AND HT.DESCRIPTION = 'LIABILITY'
            --         AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            --		  LEFT JOIN CTE A1
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.CREDIT = A1.INDICATOR
            --		  AND A1.INDICATOR IS NOT NULL
            --		  LEFT JOIN CTE A2
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.DEBIT = A2.INDICATOR
            --		    AND A2.INDICATOR IS NOT NULL
            --		WHERE AAC.CREDIT IS NOT NULL
            --       AND AAC.DEBIT IS NOT NULL AND 
            --		EXISTS (SELECT 1 FROM CTE C WHERE C.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID AND C.PERIOD_SID=ISNULL(A1.PERIOD_SID, A2.PERIOD_SID))
            --		 GROUP BY AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --                ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
            --
            --     /* PRIOR TO GALUAT-948
            --	  INSERT INTO #CURRENT_LIABILITY (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --       SELECT
            --         TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --         ISNULL(A1.PERIOD_SID, A2.PERIOD_SID),
            --         SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --         SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --         ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
            --       FROM #TEMP_AMOUNT TAM
            --       JOIN ARM_ADJ_RES_CCP AAC
            --         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
            --       INNER JOIN HELPER_TABLE HT
            --         ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --         AND HT.DESCRIPTION = 'LIABILITY'
            --         AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            --       LEFT JOIN #TEMP_AMOUNT A1
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.CREDIT = A1.INDICATOR
            --       LEFT JOIN #TEMP_AMOUNT A2
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.DEBIT = A2.INDICATOR
            --       JOIN PERIOD P
            --         ON P.PERIOD_SID = ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
            --         AND P.PERIOD_SID = TAM.PERIOD_SID
            --       WHERE AAC.CREDIT IS NOT NULL
            --       AND AAC.DEBIT IS NOT NULL
            --       GROUP BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --                ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)*/
            --     --GALUAT-948
            --     ;WITH CTE AS (SELECT * FROM #TEMP_AMOUNT)	
            --		INSERT INTO #CURRENT_EXPENSE (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --       SELECT AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --         ISNULL(A1.PERIOD_SID, A2.PERIOD_SID) PERIOD_SID,
            --         SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --         SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --         ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT		 
            --		   FROM ARM_ADJ_RES_CCP AAC        
            --       INNER JOIN HELPER_TABLE HT
            --         ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --         AND HT.DESCRIPTION = 'EXPENSE'
            --         AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            --		  LEFT JOIN CTE A1
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.CREDIT = A1.INDICATOR
            --		  AND A1.INDICATOR IS NOT NULL
            --		  LEFT JOIN CTE A2
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.DEBIT = A2.INDICATOR
            --		    AND A2.INDICATOR IS NOT NULL
            --		WHERE AAC.CREDIT IS NOT NULL
            --       AND AAC.DEBIT IS NOT NULL AND 
            --		EXISTS (SELECT 1 FROM CTE C WHERE C.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID AND C.PERIOD_SID=ISNULL(A1.PERIOD_SID, A2.PERIOD_SID))
            --		 GROUP BY AAC.ARM_ADJUSTMENT_DETAILS_SID,
            --                ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
            --
            --     /*Prior to GALUAT-948
            --	  INSERT INTO #CURRENT_EXPENSE (ARM_ADJUSTMENT_DETAILS_SID, PERIOD_SID, CREDIT_AMOUNT, DEBIT_AMOUNT, CURRENT_AMOUNT)
            --       SELECT
            --         TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --         ISNULL(A1.PERIOD_SID, A2.PERIOD_SID),
            --         SUM(ABS(A1.ACCRUAL_AMOUNT)) AS CREDIT_AMOUNT,
            --         SUM(ABS(A2.ACCRUAL_AMOUNT)) AS DEBIT_AMOUNT,
            --         ISNULL(SUM(ABS(A1.ACCRUAL_AMOUNT)), 0) - ISNULL(SUM(ABS(A2.ACCRUAL_AMOUNT)), 0) AS CURRENT_AMOUNT
            --       FROM #TEMP_AMOUNT TAM
            --       JOIN ARM_ADJ_RES_CCP AAC
            --         ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
            --       INNER JOIN HELPER_TABLE HT
            --         ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
            --         AND HT.DESCRIPTION = 'EXPENSE'
            --         AND AAC.ADJUSTMENT_TYPE = @ADJUSTMENT_TYPE
            --       LEFT JOIN #TEMP_AMOUNT A1
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.CREDIT = A1.INDICATOR
            --       LEFT JOIN #TEMP_AMOUNT A2
            --         ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
            --         AND AAC.DEBIT = A2.INDICATOR
            --       JOIN PERIOD P
            --         ON P.PERIOD_SID = ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
            --         AND P.PERIOD_SID = TAM.PERIOD_SID
            --       WHERE AAC.CREDIT IS NOT NULL
            --       AND AAC.DEBIT IS NOT NULL
            --       GROUP BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
            --                ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)*/
            --     --Balance suammary report calculation Ends here for separtely for both Liability and Expense amount for Tr-4
            --
            --     SET @SQL = ''
            --
            --     --Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-4) for CCP+D+Period Combination Starts here	
            --
            --
            --     SET @SQL = CONCAT('IF EXISTS (SELECT 1 FROM ', @ARM_DEMAND_RECON_SUMMARY_TABLE, ') 
            --                 BEGIN 
            --                     TRUNCATE TABLE ', @ARM_DEMAND_RECON_SUMMARY_TABLE, '   
            --                 END 
            --
            --               INSERT INTO ', @ARM_DEMAND_RECON_SUMMARY_TABLE, ' 
            --                           (ARM_ADJUSTMENT_DETAILS_SID, 
            --                            PERIOD_SID, 
            --                            DEMAND_ACCRUAL, 
            --                            DEMAND_ACCRUAL_REFORECAST, 
            --                            DEMAND_PAYMENT_RECON, 
            --                            TOTAL_DEMAND_ACCRUAL, 
            --                            ACTUAL_PAYMENTS, 
            --                            PAYMENT_RATIO, 
            --                            VARIANCE, 
            --                            PROJECTED_RATE,
            --							 LIABILITY_AMOUNT,
            --							 EXPENSE_AMOUNT) 
            --               SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
            --                      TAM.PERIOD_SID, 
            --                      TAM.DEMAND_ACCRUAL,
            --                       TAM.DEMAND_ACCRUAL_REFORECAST,
            --                       TAM.DEMAND_PAYMENT_RECON,
            --                       TAM.TOTAL_DEMAND_ACCRUAL,
            --                      TAM.ACTUAL_PAYMENTS,
            --                   TAM.PAYMENT_RATIO,
            --                      TAM.VARIANCE,
            --                      TAM.PROJECTED_RATE,
            --					   CL.CURRENT_AMOUNT AS LIABILITY_AMOUNT,
            --					   CE.CURRENT_AMOUNT AS EXPENSE_AMOUNT 
            --               FROM   #ARM_DEMAND_RECON_SUMMARY TAM 
            --                      LEFT JOIN #CURRENT_LIABILITY CL
            --                             ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CL.ARM_ADJUSTMENT_DETAILS_SID
            --                                AND TAM.PERIOD_SID = CL.PERIOD_SID 
            --					  LEFT JOIN #CURRENT_EXPENSE CE
            --                             ON TAM.ARM_ADJUSTMENT_DETAILS_SID = CE.ARM_ADJUSTMENT_DETAILS_SID
            --                                AND TAM.PERIOD_SID = CE.PERIOD_SID ')
            --
            --     EXEC sp_executesql @SQL
            --Deletion of Existing and Re insertion of Records in main table for Particular Projection(Tr-4) for CCP+D+Period Combination Ends here	
            END

          EXEC PRC_ARM_ADJUSTMENTS_INSERTION
            @PROJECTION_MASTER_SID,
            @ADJUSTMENT_TYPE,
            @SUMMARY_TABLE,
            @ARM_ADJUSTMENTS

          IF Object_id('TEMPDB..#TEMP_ARM_PROJ_MASTER') IS NOT NULL
            DROP TABLE #TEMP_ARM_PROJ_MASTER

          IF Object_id('TEMPDB..#TEMP_ARM_PROJ_DISCOUNTS') IS NOT NULL
            DROP TABLE #TEMP_ARM_PROJ_DISCOUNTS

          IF Object_id('TEMPDB..#TEMP_ACTUAL_DISCOUNTS') IS NOT NULL
            DROP TABLE #TEMP_ACTUAL_DISCOUNTS

          IF Object_id('TEMPDB..#TEMP_PROJ_MASTER_STATUS') IS NOT NULL
            DROP TABLE #TEMP_PROJ_MASTER_STATUS

          IF Object_id('TEMPDB..#TEMP_PROJ_DETAILS') IS NOT NULL
            DROP TABLE #TEMP_PROJ_DETAILS

          IF Object_id('TEMPDB..#ARM_DEMAND_REFORECAST_SUMMARY') IS NOT NULL
            DROP TABLE #ARM_DEMAND_REFORECAST_SUMMARY

          IF Object_id('TEMPDB..#ARM_DEMAND_ADJ_SUMMARY') IS NOT NULL
            DROP TABLE #ARM_DEMAND_ADJ_SUMMARY

          IF Object_id('TEMPDB..#ARM_DEMAND_RECON_SUMMARY') IS NOT NULL
            DROP TABLE #ARM_DEMAND_RECON_SUMMARY

          IF Object_id('TEMPDB..#PERIOD') IS NOT NULL
            DROP TABLE #PERIOD
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

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.                     
                     @ERRORSEVERITY,-- SEVERITY.                     
                     @ERRORSTATE,-- STATE.                     
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.                     
                     @ERRORNUMBER,-- ERRORNUMBER                     
                     @ERRORLINE -- ERRORLINE                     
          );
      END CATCH
  END


GO


