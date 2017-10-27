IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_BALANCE_REPORT_DEMAND' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].[PRC_BALANCE_REPORT_DEMAND] 
  END 

GO 

CREATE PROCEDURE [dbo].[PRC_BALANCE_REPORT_DEMAND](@PROJECTION_MASTER_SID          INT, 
                                             @CALCULATION_PROFILE_MASTER_SID INT,
											 @USER_ID INT,
											 @SESSION_ID INT) 
AS 

/**********************************************************************************************************
** File Name		:	PRC_BALANCE_REPORT_DEMAND.sql
** Procedure Name	:	PRC_BALANCE_REPORT_DEMAND
** Description		:	To generate Balance Report Demand Screen
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Balance Report
	                    @CALCULATION_PROFILE_MASTER_SID - User Selected Clauclation Profile in Data Selection 
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
											
** Output Parameters:	NA
** Author Name		:	@Ajay
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application Balance Report Pipeline,
                        if new Transaction created with Pipeline Inventory as base Transaction then application will call this procedure. 
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------

*********************************************************************************************************/

   BEGIN 
   BEGIN TRY 

-- Variables Initialization starts here
      SET NOCOUNT ON 

        DECLARE @START_PERIOD_SID INT, 
              @BU_COMPANY       INT, 
              @GL_COMPANY       INT, 
              @TO_PERIOD_SID    INT, 
              @TO_DATE          DATETIME, 
              @FROM_DATE        DATETIME

      DECLARE @SQL NVARCHAR(MAX) 
		DECLARE @BLN_DEMAND_PERIOD_TABLE    VARCHAR(200) = CONCAT('ST_BALANCE_REPORT_DEMAND_PERIOD_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		        @BLN_DEMAND_CUM_TABLE               VARCHAR(200)= CONCAT('ST_BALANCE_REPORT_DEMAND_CUM_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))

-- Variables Initialization Ends here

 --taking price related period selected in data selection and assigning GL and BU to variable starts here 

	  SELECT @START_PERIOD_SID = PERIOD_SID 
      FROM   PERIOD P 
             JOIN PROJECTION_MASTER PM 
               ON P.PERIOD_DATE = CONVERT(DATETIME, DATEADD(MM, -1, DATEADD(DD, 1, EOMONTH(PM.FROM_DATE, 0))))
      WHERE  PM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

      SELECT @TO_DATE = DATEADD(DD, 1, EOMONTH(TO_DATE, -1)), 
             @FROM_DATE = DATEADD(DD, 1, EOMONTH(FROM_DATE, -1)), 
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

      IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_MASTER') IS NOT NULL 
        DROP TABLE #TEMP_ARM_PROJ_MASTER 

      CREATE TABLE #TEMP_ARM_PROJ_MASTER 
        ( 
           ARM_ADJUSTMENT_DETAILS_SID INT, 
		   PERIOD_SID INT,
		   PERIOD_DATE DATETIME,
           CCP_DETAILS_SID            INT, 
           RS_MODEL_SID               INT, 
           CONTRACT_MASTER_SID        INT, 
           COMPANY_MASTER_SID         INT, 
           ITEM_MASTER_SID            INT, 
           RS_ID                      VARCHAR(50) 
           PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID) 
        ) 

      INSERT INTO #TEMP_ARM_PROJ_MASTER 
                  (ARM_ADJUSTMENT_DETAILS_SID, 
				    PERIOD_SID,
					PERIOD_DATE,
                   CCP_DETAILS_SID, 
                   RS_MODEL_SID, 
                   CONTRACT_MASTER_SID, 
                   COMPANY_MASTER_SID, 
                   ITEM_MASTER_SID, 
                   RS_ID) 
      SELECT A.ARM_ADJUSTMENT_DETAILS_SID, 
	         P.PERIOD_SID,
			 P.PERIOD_DATE,
             A.CCP_DETAILS_SID, 
             A.RS_MODEL_SID, 
             B.CONTRACT_MASTER_SID, 
             B.COMPANY_MASTER_SID, 
             B.ITEM_MASTER_SID, 
             RS.RS_ID 
      FROM   ARM_ADJUSTMENT_DETAILS A 
             JOIN CCP_DETAILS B 
               ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID 
             JOIN RS_CONTRACT RS 
               ON RS.RS_MODEL_SID = A.RS_MODEL_SID 
			   and rs.CONTRACT_MASTER_SID=b.CONTRACT_MASTER_SID
             JOIN ARM_ADJUSTMENT_MASTER AM 
               ON AM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
			    CROSS JOIN PERIOD P 
          WHERE  A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 
                 AND P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @TO_PERIOD_SID 

 --PULLING CCP+D COMBINATION FOR CURRENT PROJECTION(BALNCE REPORT) Ends here
    

--PULLING ADJUSTMENT_TYPES IN  CALCULATION PROFILE VIEW FOR CURRENT PROJECTION(BALNCE REPORT) Starts here

      IF OBJECT_ID('TEMPDB..#TEMP_CAL_PROFILE_MASTER') IS NOT NULL 
        DROP TABLE #TEMP_CAL_PROFILE_MASTER 

      SELECT PROFILE_NAME, 
             PROFILE_DESCRIPTION, 
             SAVE_FLAG, 
             ADJUSTMENT_TYPE, 
             ACCOUNT_TYPE, 
             INCLUDE, 
             INDICATOR, 
             PRIORITY, 
             HT.DESCRIPTION  AS BASE, 
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
             LEFT JOIN ARM_ADJUSTMENT_CONFIG AC 
                    ON AC.ARM_ADJUSTMENT_CONFIG_SID = CD.ADJUSTMENT_TYPE 
             LEFT JOIN HELPER_TABLE HT 
                    ON HT.HELPER_TABLE_SID = AC.METHODOLGY 
                       AND LIST_NAME LIKE '%ARM%' 
             LEFT JOIN HELPER_TABLE HT1 
                    ON HT1.HELPER_TABLE_SID = CD.ACCOUNT_TYPE 
      WHERE  CF.CALCULATION_PROFILE_MASTER_SID = @CALCULATION_PROFILE_MASTER_SID 

--PULLING ADJUSTMENT_TYPES IN  CALCULATION PROFILE VIEW FOR CURRENT PROJECTION(BALNCE REPORT) Starts here

	IF Object_id('TEMPDB..#TEMP') IS NOT NULL
  DROP TABLE #TEMP;	
  	
		SELECT A.ARM_ADJUSTMENT_DETAILS_SID,A.CCP_DETAILS_SID,PERIOD_DATE,
		CONTRACT_MASTER_SID , COMPANY_MASTER_SID 
                            , ITEM_MASTER_SID 
                            ,RS_ID,  A.RS_MODEL_SID,A.PERIOD_SID,B.ADJUSTMENT_TYPE,INDICATOR  INTO #TEMP FROM #TEMP_ARM_PROJ_MASTER A   
						  CROSS JOIN #TEMP_CAL_PROFILE_MASTER B  

--PULLING APPROVED PROJECTIONS  BASED ON   CURRENT PROJECTION CCP+D LEVEL (BALNCE REPORT) Starts here
	 
	   IF OBJECT_ID('TEMPDB..#TEMP_ARM_APPRVD_PROJ') IS NOT NULL
                            DROP TABLE #TEMP_ARM_APPRVD_PROJ

                          CREATE TABLE #TEMP_ARM_APPRVD_PROJ
                            (
                               ARM_ADJUSTMENT_DETAILS_SID        INT,
                               APPRVD_PROJECTION_MASTER_SID      INT,
                               APPRVD_ARM_ADJUSTMENT_DETAILS_SID INT,
                               CCP_DETAILS_SID                   INT,
                               RS_MODEL_SID                      INT,
                               ADJUSTMENT_TYPE                   VARCHAR(50),
                               --PERIOD_SID                        INT,
                              
                               WORKFLOW_ID                       VARCHAR(50),
                              
                             
                               APPRVD_ARM_ADJUSTMENT_CONFIG_SID  INT,
                               APPRVD_TRANSACTION_NAME           VARCHAR(50),
                               APPRVD_METHODOLOGY                VARCHAR(50),
							   BASE VARCHAR(50),
							   ACCOUT_TYPE_DES VARCHAR(50),
							   INDICATOR BIT
                               PRIMARY KEY ( APPRVD_ARM_ADJUSTMENT_DETAILS_SID, CCP_DETAILS_SID, RS_MODEL_SID )
                            )

                          INSERT INTO #TEMP_ARM_APPRVD_PROJ
                                      (ARM_ADJUSTMENT_DETAILS_SID,
                                       APPRVD_PROJECTION_MASTER_SID,
                                       APPRVD_ARM_ADJUSTMENT_DETAILS_SID,
                                       CCP_DETAILS_SID,
                                       RS_MODEL_SID,
                                       ADJUSTMENT_TYPE,
                                       --PERIOD_SID,
                                      
                                       WORKFLOW_ID,
                                    
                                    
                                       APPRVD_ARM_ADJUSTMENT_CONFIG_SID,
                                       APPRVD_TRANSACTION_NAME,
                                       APPRVD_METHODOLOGY,
									   BASE,
									   ACCOUT_TYPE_DES,
									   INDICATOR)
                         
                         SELECT C.ARM_ADJUSTMENT_DETAILS_SID AS ARM_ADJUSTMENT_DETAILS_SID,
						        PM.PROJECTION_MASTER_SID AS APPRVD_PROJECTION_MASTER_SID,
                                         B.ARM_ADJUSTMENT_DETAILS_SID AS APPRVD_ARM_ADJUSTMENT_DETAILS_SID,
                                         B.CCP_DETAILS_SID,
                                         B.RS_MODEL_SID,
                                     
                                         PM.FORECASTING_TYPE          AS ADJUSTMENT_TYPE,
                                         ---P.PERIOD_SID,
                                    
                                         A.WORKFLOW_ID,
                                         ARM_ADJUSTMENT_CONFIG_SID,
                                         TRANSACTION_NAME,
                                         HT.DESCRIPTION               AS METHODOLOGY,
										 TCF.BASE,
										 ACCOUT_TYPE_DES,
										 INDICATOR
                                  FROM   WORKFLOW_MASTER A
                                         JOIN ARM_ADJUSTMENT_DETAILS B
                                           ON A.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID
                                         JOIN ARM_ADJUSTMENT_MASTER AM
                                           ON AM.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID
										  AND  AM.BU_COMPANY_MASTER_SID=@BU_COMPANY
                                         JOIN ARM_ADJUSTMENT_CONFIG AC
                                           ON AM.TRANSACTION_TYPE = AC.ARM_ADJUSTMENT_CONFIG_SID
										    JOIN #TEMP_CAL_PROFILE_MASTER TCF
				                           ON AC.ARM_ADJUSTMENT_CONFIG_SID=TCF.ADJUSTMENT_TYPE
                                         JOIN HELPER_TABLE HT
                                           ON HT.HELPER_TABLE_SID = AC.METHODOLGY
                                         JOIN PROJECTION_MASTER PM
                                           ON PM.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID
										      AND PM.COMPANY_MASTER_SID=@GL_COMPANY
                                              AND PM.FROM_DATE >= @FROM_DATE
                                              AND PM.TO_DATE <= eomonth(@TO_DATE)
                                         JOIN (SELECT DISTINCT ARM_ADJUSTMENT_DETAILS_SID,CCP_DETAILS_SID,RS_MODEL_SID FROM  #TEMP_ARM_PROJ_MASTER)  C
                                           ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                              AND C.RS_MODEL_SID = B.RS_MODEL_SID
                                         ---CROSS JOIN #TEMP_PERIOD P
                                         WHERE WORKFLOW_ID LIKE 'ARM%'
										 AND EXISTS (SELECT HELPER_TABLE_SID
                                            FROM   HELPER_TABLE H1
                                            WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                                    AND DESCRIPTION = 'APPROVED'
													AND H1.HELPER_TABLE_SID=A.WORKFLOW_STATUS_ID)
                                        --AND WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID
                                        --    FROM   HELPER_TABLE
                                        --    WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                        --           AND DESCRIPTION = 'APPROVED')
--PULLING APPROVED PROJECTIONS  BASED ON   CURRENT PROJECTION CCP+D LEVEL (BALNCE REPORT) Ends here

-- ACTUAL ARM MASTER DEBIT AND CREDIT LOGIC Starts here

  IF OBJECT_ID('TEMPDB..#TEMP_ADJ_MASTER') IS NOT NULL 
            DROP TABLE #TEMP_ADJ_MASTER 

          CREATE TABLE #TEMP_ADJ_MASTER 
            ( 
               CCP_DETAILS_SID INT, 
               RS_MODEL_SID    INT, 
               PERIOD_SID      INT, 
               AMOUNT          NUMERIC(22, 6),
			   ADJUSTMENT_TYPE INT
            ) 

          INSERT INTO #TEMP_ADJ_MASTER 
                      (CCP_DETAILS_SID, 
                       RS_MODEL_SID, 
                       PERIOD_SID,
					   ADJUSTMENT_TYPE,
                       AMOUNT) 
          SELECT A.CCP_DETAILS_SID, 
                 A.RS_MODEL_SID, 
                 A.PERIOD_SID,
				 A.ADJUSTMENT_TYPE,
                 ISNULL(SUM(CREDIT), 0) - ISNULL(SUM(DEBIT), 0) AS AMOUNT 
          FROM   (SELECT DISTINCT TAM.CCP_DETAILS_SID, 
                                  TAM.RS_MODEL_SID, 
                                  REDEMPTION_DATE AS PERIOD_DATE, 
                                  P.PERIOD_SID, 
                                  DEBIT_INDICATOR, 
                                  CREDIT_INDICATOR, 
								  AM.ADJUSTMENT_TYPE AS ADJUSTMENT_TYPE,
                                  AMOUNT, 
                                  CASE 
                                            WHEN AMOUNT < 0 
                                                 AND DEBIT_INDICATOR = 1 THEN ABS(AMOUNT) 
                                            WHEN AMOUNT > 0 
                                                 AND DEBIT_INDICATOR = 0 THEN ABS(AMOUNT) 
                                          END AS DEBIT, 
                                  CASE 
                                           WHEN AMOUNT < 0 
                                                AND CREDIT_INDICATOR = 1 THEN ABS(AMOUNT) 
                                           WHEN AMOUNT > 0 
                                                AND CREDIT_INDICATOR = 0 THEN ABS(AMOUNT) 
                                         END AS CREDIT
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
                         JOIN RS_CONTRACT RS 
                           ON RS.RS_CATEGORY = AM.RS_CATEGORY 
                              AND RS.RS_TYPE = AM.RS_TYPE 
                              AND RS.REBATE_PROGRAM_TYPE = AM.REBATE_PROGRAM_TYPE 
                              AND RS.RS_MODEL_SID = TAM.RS_MODEL_SID 
							  and rs.CONTRACT_MASTER_SID=con.CONTRACT_MASTER_SID
                         JOIN PERIOD P 
                           ON P.PERIOD_DATE = CAST(AM.REDEMPTION_DATE AS DATE))A 
          GROUP  BY CCP_DETAILS_SID, 
                    RS_MODEL_SID, 
                    PERIOD_SID,
					ADJUSTMENT_TYPE 

--Actual Payment Calculation Ends here

--Pulling Actual Discounts Starts here

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

--Pulling Actual Discounts Ends here

--Cummulative Discounts starts here

		 IF OBJECT_ID('TEMPDB..#TEMP_ACTUAL_DISCOUNTS_CUMM') IS NOT NULL 
                  DROP TABLE #TEMP_ACTUAL_DISCOUNTS_CUMM 

                CREATE TABLE #TEMP_ACTUAL_DISCOUNTS_CUMM 
                  ( 
                     ARM_ADJUSTMENT_DETAILS_SID INT, 
                     ACTUAL_PAYMENTS            NUMERIC(22, 6), 
                     ACTUAL_RATE                NUMERIC(22, 6), 
                     PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID) 
                  ) 

                INSERT INTO #TEMP_ACTUAL_DISCOUNTS_CUMM 
                            (ARM_ADJUSTMENT_DETAILS_SID, 
                            
                             ACTUAL_PAYMENTS, 
                             ACTUAL_RATE) 
                SELECT A2.ARM_ADJUSTMENT_DETAILS_SID, 
                     
                       SUM(SALES_AMOUNT) AS ACTUAL_PAYMENTS,
                                            
                       COALESCE(( SUM(AMOUNT) / NULLIF(SUM(SALES_AMOUNT), 0) ), 0) RATE 
                FROM   ACTUALS_MASTER A1 
                       JOIN #TEMP_ARM_PROJ_MASTER A2 
                        
                            ON A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID 
                            AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID 
                            AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID 
                            AND A1.PROVISION_ID = A2.RS_ID 
							
                            
                           
                GROUP  BY ARM_ADJUSTMENT_DETAILS_SID, 
                        
                          CONTRACT_ID, 
                          ACCOUNT_ID, 
                          ITEM_ID, 
                          PROVISION_ID, 
                          A1.COMPANY_MASTER_SID, 
                          A1.CONTRACT_MASTER_SID, 
                          A1.ITEM_MASTER_SID 

--Cummulative Discounts Ends here

--Liability and Expense amounts for each period starts here

						  IF OBJECT_ID('TEMPDB..#TEMP_DEMAND') IS NOT NULL 
                  DROP TABLE #TEMP_DEMAND 		
CREATE TABLE #TEMP_DEMAND
(
 CCP_DETAILS_SID INT,
 RS_MODEL_SID INT,
 PERIOD_SID INT,
 ADJUSTMENT_TYPE INT,
 ACTUAL_PAYMENTS NUMERIC(22,6),
 PERIOD_BALANCE NUMERIC(22,6),
 ADJUSTMENT_TYPE_BALANCE NUMERIC(22,6)
 )

 INSERT INTO #TEMP_DEMAND(CCP_DETAILS_SID,RS_MODEL_SID,PERIOD_SID,ADJUSTMENT_TYPE,ACTUAL_PAYMENTS,PERIOD_BALANCE,ADJUSTMENT_TYPE_BALANCE)
    

		 SELECT  TAM.CCP_DETAILS_SID,
		         TAM.RS_MODEL_SID,
				 TAM.PERIOD_SID,
				 TAC.APPRVD_ARM_ADJUSTMENT_CONFIG_SID AS ADJUSTMENT_TYPE,
				 ISNULL(MAX(TAD.ACTUAL_PAYMENTS),0) AS ACTUAL_PAYMENTS,
                
                 ISNULL(MAX(TB.AMOUNT), 0)  + ISNULL(SUM(CASE  WHEN BASE = 'TRANSACTION 1' AND ACCOUT_TYPE_DES='Expense' THEN 
				 CASE WHEN INDICATOR=0 THEN AR.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN AR.EXPENSE_AMOUNT * -1
				    ELSE 0 END END
					+ 
			   CASE  WHEN BASE = 'TRANSACTION 1' AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN AR.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN AR.LIABILITY_AMOUNT * -1
				    ELSE 0 END END+ 
			      CASE  WHEN BASE = 'TRANSACTION 2' AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN ADJ.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN ADJ.EXPENSE_AMOUNT * -1
				    ELSE 0 END END+
					CASE  WHEN BASE = 'TRANSACTION 2' AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN ADJ.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN ADJ.LIABILITY_AMOUNT * -1
				    ELSE 0 END END+ 
					CASE  WHEN BASE = 'TRANSACTION 3' AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN AIR.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN AIR.EXPENSE_AMOUNT * -1
				    ELSE 0 END END+  
					CASE  WHEN BASE = 'TRANSACTION 3' AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN AIR.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN AIR.LIABILITY_AMOUNT * -1
				    ELSE 0 END END
					+  
					CASE  WHEN BASE = 'TRANSACTION 4'  AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN ADRS.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN ADRS.EXPENSE_AMOUNT * -1
				    ELSE 0 END END+ 
					CASE  WHEN BASE = 'TRANSACTION 4'  AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN ADRS.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN ADRS.LIABILITY_AMOUNT * -1
				    ELSE 0 END END
					+ 
					 CASE  WHEN BASE = 'TRANSACTION 5'  AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN ADRF.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN ADRF.EXPENSE_AMOUNT * -1
				    ELSE 0 END END+ 
					CASE  WHEN BASE = 'TRANSACTION 5'  AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN ADRF.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN ADRF.LIABILITY_AMOUNT * -1
				    ELSE 0 END  END
					+  
					CASE  WHEN BASE = 'TRANSACTION 6'  AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN AIA.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN AIA.EXPENSE_AMOUNT * -1
				    ELSE 0 END END+ 
					CASE  WHEN BASE = 'TRANSACTION 6'  AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN AIA.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN AIA.LIABILITY_AMOUNT * -1
				    ELSE 0 END END+  
					CASE  WHEN BASE = 'TRANSACTION 7'  AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN ADR.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN ADR.EXPENSE_AMOUNT * -1
				    ELSE 0 END END + 
					CASE  WHEN BASE = 'TRANSACTION 7'  AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN ADR.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN ADR.LIABILITY_AMOUNT * -1
				    ELSE 0 END END),0)
				 
					AS PERIOD_BALANCE,

					 ISNULL(MAX(TB.AMOUNT), 0)  + ISNULL(SUM(CASE  WHEN BASE = 'TRANSACTION 1' AND ACCOUT_TYPE_DES='Expense' THEN 
				 AR.EXPENSE_AMOUNT  WHEN BASE = 'TRANSACTION 1' AND ACCOUT_TYPE_DES='Liability'  THEN AR.LIABILITY_AMOUNT 
				    ELSE 0 END 
					+ 
			  
			      CASE  WHEN BASE = 'TRANSACTION 2' AND ACCOUT_TYPE_DES='Expense' THEN 
				 ADJ.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 2' AND ACCOUT_TYPE_DES='Liability' THEN  ADJ.LIABILITY_AMOUNT
				    ELSE 0 END +
					
					CASE  WHEN BASE = 'TRANSACTION 3' AND ACCOUT_TYPE_DES='Expense' THEN 
				 AIR.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 3' AND ACCOUT_TYPE_DES='Liability' THEN AIR.LIABILITY_AMOUNT
				    ELSE 0 END +  
					
					CASE  WHEN BASE = 'TRANSACTION 4'  AND ACCOUT_TYPE_DES='Expense' THEN 
				 ADRS.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 4'  AND ACCOUT_TYPE_DES='Liability' THEN ADRS.LIABILITY_AMOUNT
				    ELSE 0 END
					+ 
					 CASE  WHEN BASE = 'TRANSACTION 5'  AND ACCOUT_TYPE_DES='Expense' THEN 
				 ADRF.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 5'  AND ACCOUT_TYPE_DES='Liability' THEN ADRF.LIABILITY_AMOUNT
				    ELSE 0 END 
					
					+  
					CASE  WHEN BASE = 'TRANSACTION 6'  AND ACCOUT_TYPE_DES='Expense' THEN 
				 AIA.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 6'  AND ACCOUT_TYPE_DES='Liability' THEN AIA.LIABILITY_AMOUNT
				    ELSE 0 END + 
					
					CASE  WHEN BASE = 'TRANSACTION 7'  AND ACCOUT_TYPE_DES='Expense' THEN 
				ADR.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 7'  AND ACCOUT_TYPE_DES='Liability' THEN ADR.LIABILITY_AMOUNT
				    ELSE 0 END ),0)
				 
					AS ADJUSTMENT_TYPE_BALANCE
			     FROM   #TEMP_ARM_PROJ_MASTER TAM 
                  JOIN #TEMP_ARM_APPRVD_PROJ TAC 
                        ON TAM.CCP_DETAILS_SID = TAC.CCP_DETAILS_SID 
                           AND TAM.RS_MODEL_SID = TAC.RS_MODEL_SID 
                 LEFT JOIN #TEMP_ADJ_MASTER TB 
                        ON TAC.CCP_DETAILS_SID = TB.CCP_DETAILS_SID 
                           AND TAC.RS_MODEL_SID = TB.RS_MODEL_SID 
                           AND TB.PERIOD_SID=TAM.PERIOD_SID
				 LEFT JOIN #TEMP_ACTUAL_DISCOUNTS TAD
				 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = TAD.ARM_ADJUSTMENT_DETAILS_SID 
                     AND TAM.PERIOD_SID=TAD.PERIOD_SID
                 LEFT JOIN ARM_PIPELINE_RATE AR 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = AR.ARM_ADJUSTMENT_DETAILS_SID 
                           AND AR.PERIOD_SID=TAM.PERIOD_SID
                 LEFT JOIN ARM_DEMAND_ADJ_SUMMARY ADJ 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = ADJ.ARM_ADJUSTMENT_DETAILS_SID 
                        AND ADJ.PERIOD_SID= TAM.PERIOD_SID
                 LEFT JOIN ARM_INVENTORY_RATE AIR 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = AIR.ARM_ADJUSTMENT_DETAILS_SID 
                           AND AIR.PERIOD_SID = TAM.PERIOD_SID
                 LEFT JOIN ARM_DEMAND_RECON_SUMMARY ADRS 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = ADRS.ARM_ADJUSTMENT_DETAILS_SID 
                           AND ADRS.PERIOD_SID = TAM.PERIOD_SID
                 LEFT JOIN ARM_DEMAND_RF_TRUE_UP_SUMMARY ADRF 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = ADRF.ARM_ADJUSTMENT_DETAILS_SID 
                           AND ADRF.PERIOD_SID = TAM.PERIOD_SID
                 LEFT JOIN ARM_INFLATION_INVENTORY_ADJ AIA 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = AIA.ARM_ADJUSTMENT_DETAILS_SID 
                           AND AIA.PERIOD_SID = TAM.PERIOD_SID
                 LEFT JOIN ARM_DISTRIBUTION_FEES_RATE ADR 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = ADR.ARM_ADJUSTMENT_DETAILS_SID 
                           AND ADR.PERIOD_SID = TAM.PERIOD_SID
           GROUP  BY TAM.CCP_DETAILS_SID,
		            TAM.RS_MODEL_SID,
					TAM.PERIOD_SID,
					TAC.APPRVD_ARM_ADJUSTMENT_CONFIG_SID

--Liability and Expense amounts for each period Ends here

--CummulativE AND Adjustment Cummalative Logic Starts here

	 IF OBJECT_ID('TEMPDB..#TEMP_DEMAND_CUMM') IS NOT NULL 
                  DROP TABLE #TEMP_DEMAND_CUMM 		
CREATE TABLE #TEMP_DEMAND_CUMM
(
 CCP_DETAILS_SID INT,
 RS_MODEL_SID INT,
 ADJUSTMENT_TYPE INT,
 ACTUAL_PAYMENTS NUMERIC(22,6),
 CUMMULATIVE_BALANCE NUMERIC(22,6),
 ADJ_TYPE_CUMM_BALANCE NUMERIC(22,6)
 )

 INSERT INTO #TEMP_DEMAND_CUMM(CCP_DETAILS_SID,RS_MODEL_SID,ADJUSTMENT_TYPE,ACTUAL_PAYMENTS,CUMMULATIVE_BALANCE,ADJ_TYPE_CUMM_BALANCE)
    

		 SELECT  TAM.CCP_DETAILS_SID,
		         TAM.RS_MODEL_SID,
				 TAC.APPRVD_ARM_ADJUSTMENT_CONFIG_SID AS ADJUSTMENT_TYPE,
				 ISNULL(SUM(TAD.ACTUAL_PAYMENTS),0) AS ACTUAL_PAYMENTS,
                
                 ISNULL(MAX(TB.AMOUNT), 0)  + ISNULL(SUM(CASE  WHEN BASE = 'TRANSACTION 1' AND ACCOUT_TYPE_DES='Expense' THEN 
				 CASE WHEN INDICATOR=0 THEN AR.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN AR.EXPENSE_AMOUNT * -1
				    ELSE 0 END END
					+ 
			   CASE  WHEN BASE = 'TRANSACTION 1' AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN AR.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN AR.LIABILITY_AMOUNT * -1
				    ELSE 0 END END+ 
			      CASE  WHEN BASE = 'TRANSACTION 2' AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN ADJ.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN ADJ.EXPENSE_AMOUNT * -1
				    ELSE 0 END END+
					CASE  WHEN BASE = 'TRANSACTION 2' AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN ADJ.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN ADJ.LIABILITY_AMOUNT * -1
				    ELSE 0 END END+ 
					CASE  WHEN BASE = 'TRANSACTION 3' AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN AIR.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN AIR.EXPENSE_AMOUNT * -1
				    ELSE 0 END END+  
					CASE  WHEN BASE = 'TRANSACTION 3' AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN AIR.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN AIR.LIABILITY_AMOUNT * -1
				    ELSE 0 END END
					+  
					CASE  WHEN BASE = 'TRANSACTION 4'  AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN ADRS.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN ADRS.EXPENSE_AMOUNT * -1
				    ELSE 0 END END+ 
					CASE  WHEN BASE = 'TRANSACTION 4'  AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN ADRS.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN ADRS.LIABILITY_AMOUNT * -1
				    ELSE 0 END END
					+ 
					 CASE  WHEN BASE = 'TRANSACTION 5'  AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN ADRF.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN ADRF.EXPENSE_AMOUNT * -1
				    ELSE 0 END END+ 
					CASE  WHEN BASE = 'TRANSACTION 5'  AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN ADRF.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN ADRF.LIABILITY_AMOUNT * -1
				    ELSE 0 END  END
					+  
					CASE  WHEN BASE = 'TRANSACTION 6'  AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN AIA.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN AIA.EXPENSE_AMOUNT * -1
				    ELSE 0 END END+ 
					CASE  WHEN BASE = 'TRANSACTION 6'  AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN AIA.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN AIA.LIABILITY_AMOUNT * -1
				    ELSE 0 END END+  
					CASE  WHEN BASE = 'TRANSACTION 7'  AND ACCOUT_TYPE_DES='Expense' THEN 
				CASE WHEN INDICATOR=0 THEN ADR.EXPENSE_AMOUNT  WHEN INDICATOR= 1 THEN ADR.EXPENSE_AMOUNT * -1
				    ELSE 0 END END + 
					CASE  WHEN BASE = 'TRANSACTION 7'  AND ACCOUT_TYPE_DES='Liability' THEN 
				CASE WHEN INDICATOR=0 THEN ADR.LIABILITY_AMOUNT  WHEN INDICATOR= 1 THEN ADR.LIABILITY_AMOUNT * -1
				    ELSE 0 END END),0)
				 
					AS CUMMULATIVE_BALANCE,

					 ISNULL(MAX(TB.AMOUNT), 0)  + ISNULL(SUM(CASE  WHEN BASE = 'TRANSACTION 1' AND ACCOUT_TYPE_DES='Expense' THEN 
				 AR.EXPENSE_AMOUNT  WHEN BASE = 'TRANSACTION 1' AND ACCOUT_TYPE_DES='Liability'  THEN AR.LIABILITY_AMOUNT 
				    ELSE 0 END 
					+ 
			  
			      CASE  WHEN BASE = 'TRANSACTION 2' AND ACCOUT_TYPE_DES='Expense' THEN 
				 ADJ.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 2' AND ACCOUT_TYPE_DES='Liability' THEN  ADJ.LIABILITY_AMOUNT
				    ELSE 0 END +
					
					CASE  WHEN BASE = 'TRANSACTION 3' AND ACCOUT_TYPE_DES='Expense' THEN 
				 AIR.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 3' AND ACCOUT_TYPE_DES='Liability' THEN AIR.LIABILITY_AMOUNT
				    ELSE 0 END +  
					
					CASE  WHEN BASE = 'TRANSACTION 4'  AND ACCOUT_TYPE_DES='Expense' THEN 
				 ADRS.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 4'  AND ACCOUT_TYPE_DES='Liability' THEN ADRS.LIABILITY_AMOUNT
				    ELSE 0 END
					+ 
					 CASE  WHEN BASE = 'TRANSACTION 5'  AND ACCOUT_TYPE_DES='Expense' THEN 
				 ADRF.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 5'  AND ACCOUT_TYPE_DES='Liability' THEN ADRF.LIABILITY_AMOUNT
				    ELSE 0 END 
					
					+  
					CASE  WHEN BASE = 'TRANSACTION 6'  AND ACCOUT_TYPE_DES='Expense' THEN 
				 AIA.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 6'  AND ACCOUT_TYPE_DES='Liability' THEN AIA.LIABILITY_AMOUNT
				    ELSE 0 END + 
					
					CASE  WHEN BASE = 'TRANSACTION 7'  AND ACCOUT_TYPE_DES='Expense' THEN 
				ADR.EXPENSE_AMOUNT WHEN BASE = 'TRANSACTION 7'  AND ACCOUT_TYPE_DES='Liability' THEN ADR.LIABILITY_AMOUNT
				    ELSE 0 END ),0)
				 
					AS ADJ_TYPE_CUMM_BALANCE
			     FROM   #TEMP_ARM_PROJ_MASTER TAM 
                 LEFT JOIN #TEMP_ARM_APPRVD_PROJ TAC 
                        ON TAM.CCP_DETAILS_SID = TAC.CCP_DETAILS_SID 
                           AND TAM.RS_MODEL_SID = TAC.RS_MODEL_SID
                 LEFT JOIN #TEMP_ADJ_MASTER TB 
                        ON TAC.CCP_DETAILS_SID = TB.CCP_DETAILS_SID 
                           AND TAC.RS_MODEL_SID = TB.RS_MODEL_SID 
                          
				 LEFT JOIN #TEMP_ACTUAL_DISCOUNTS_CUMM TAD
				 ON TAM.ARM_ADJUSTMENT_DETAILS_SID = TAD.ARM_ADJUSTMENT_DETAILS_SID 
                 LEFT JOIN ARM_PIPELINE_RATE AR 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = AR.ARM_ADJUSTMENT_DETAILS_SID      
                 LEFT JOIN ARM_DEMAND_ADJ_SUMMARY ADJ 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = ADJ.ARM_ADJUSTMENT_DETAILS_SID 
                 LEFT JOIN ARM_INVENTORY_RATE AIR 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = AIR.ARM_ADJUSTMENT_DETAILS_SID  
                 LEFT JOIN ARM_DEMAND_RECON_SUMMARY ADRS 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = ADRS.ARM_ADJUSTMENT_DETAILS_SID  
                 LEFT JOIN ARM_DEMAND_RF_TRUE_UP_SUMMARY ADRF 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = ADRF.ARM_ADJUSTMENT_DETAILS_SID 
                 LEFT JOIN ARM_INFLATION_INVENTORY_ADJ AIA 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = AIA.ARM_ADJUSTMENT_DETAILS_SID 
                 LEFT JOIN ARM_DISTRIBUTION_FEES_RATE ADR 
                        ON TAC.APPRVD_ARM_ADJUSTMENT_DETAILS_SID = ADR.ARM_ADJUSTMENT_DETAILS_SID 
                          
          GROUP  BY TAM.CCP_DETAILS_SID,
		            TAM.RS_MODEL_SID,
					TAC.APPRVD_ARM_ADJUSTMENT_CONFIG_SID

--CummulativE AND Adjustment Cummalative Logic Starts here

--Deletion and Re Inserting into main table Starts here

					
		 SET @SQL =CONCAT('IF EXISTS (SELECT 1 FROM ', @BLN_DEMAND_PERIOD_TABLE, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
                                          ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
					                   BEGIN 
												TRUNCATE TABLE ', @BLN_DEMAND_PERIOD_TABLE, '  
									   END
	  INSERT INTO ',@BLN_DEMAND_PERIOD_TABLE,' 
                  (ARM_ADJUSTMENT_DETAILS_SID, 
                   PERIOD_SID, 
                   ADJUSTMENT_TYPE, 
                   ADJUSTMENT_TYPE_BALANCE, 
                   ACTUALS_PAYMENTS, 
                   PERIOD_BALANCE) 
      SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID, 
             TAM.PERIOD_SID, 
             T.ADJUSTMENT_TYPE, 
             ADJUSTMENT_TYPE_BALANCE, 
             ACTUAL_PAYMENTS,
			 PERIOD_BALANCE                             
             
      FROM   #TEMP_ARM_PROJ_MASTER TAM  LEFT JOIN #TEMP T ON  T.ARM_ADJUSTMENT_DETAILS_SID=TAM.ARM_ADJUSTMENT_DETAILS_SID
	  and t.PERIOD_SID=tam.PERIOD_SID  
	  LEFT JOIN #TEMP_DEMAND TD ON TAM.CCP_DETAILS_SID=TD.CCP_DETAILS_SID
	  AND TAM.RS_MODEL_SID=TD.RS_MODEL_SID AND TAM.PERIOD_SID=TD.PERIOD_SID
	   and td.ADJUSTMENT_TYPE=t.ADJUSTMENT_TYPE')
          
	  EXEC sp_executesql @SQL

	
	  SET @SQL=''
	  SET @SQL =CONCAT('IF EXISTS (SELECT 1 FROM ', @BLN_DEMAND_CUM_TABLE, ' R INNER JOIN ARM_ADJUSTMENT_DETAILS A 
                                          ON R.ARM_ADJUSTMENT_DETAILS_SID = A.ARM_ADJUSTMENT_DETAILS_SID WHERE  A.PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
					                   BEGIN 
												TRUNCATE TABLE ', @BLN_DEMAND_CUM_TABLE, '  
									   END
	  INSERT INTO ',@BLN_DEMAND_CUM_TABLE,'
                  (ARM_ADJUSTMENT_DETAILS_SID, 
                   ADJUSTMENT_TYPE, 
                   ADJUSTMENT_TYPE_BALANCE, 
                   ACTUALS_PAYMENTS, 
                   CUMMULATIVE_BALANCE
				  ) 
      SELECT T.ARM_ADJUSTMENT_DETAILS_SID, 
       T.ADJUSTMENT_TYPE, 
       ADJ_TYPE_CUMM_BALANCE AS ADJUSTMENT_TYPE_BALANCE, 
       ACTUAL_PAYMENTS, 
       CUMMULATIVE_BALANCE 
FROM   (SELECT DISTINCT ARM_ADJUSTMENT_DETAILS_SID, 
                        CCP_DETAILS_SID, 
                        RS_MODEL_SID,
						ADJUSTMENT_TYPE 
        FROM   #TEMP) T
       
   
       LEFT JOIN #TEMP_DEMAND_CUMM TDC 
              ON T.CCP_DETAILS_SID = TDC.CCP_DETAILS_SID 
                 AND T.RS_MODEL_SID = TDC.RS_MODEL_SID 
                 AND TDC.ADJUSTMENT_TYPE = T.ADJUSTMENT_TYPE')

	 	  EXEC sp_executesql @SQL


--Deletion and Re Inserting into main table Ends here





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


