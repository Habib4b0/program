IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_ARM_DISTRIBUTION_FEES_RATE' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].[PRC_ARM_DISTRIBUTION_FEES_RATE] 
  END 

GO 

CREATE PROCEDURE [dbo].[PRC_ARM_DISTRIBUTION_FEES_RATE](@PROJECTION_MASTER_SID INT, 
                                                        @FREQUENCY             VARCHAR(20), 
                                                        @USER_ID               INT, 
                                                        @SESSION_ID            INT) 
AS 

/**********************************************************************************************************
** File Name		:	PRC_ARM_DISTRIBUTION_FEES_RATE.sql
** Procedure Name	:	PRC_ARM_DISTRIBUTION_FEES_RATE
** Description		:	To generate Distribution Fee Rates tab  (Tr-7) and it will calculAdjustment Summary tab
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Tr-7 as base
	                    @FREQUENCY - User Selected Frequency in Rates tab.
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
											
** Output Parameters:	NA
** Author Name		:	@Rashmi,@Ajay(Debit and Credit Logic)
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application for Pipeline Inventory Transaction(as base),
                        if new Transaction created with Pipeline Inventory as base Transaction then application will call this procedure. 
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------
** 1    11/08/2016  GAL-8283        @Paul             Temp table Changes.
*********************************************************************************************************/
  BEGIN 
      SET NOCOUNT ON 
-- Variables Initialization starts here
      BEGIN TRY 
        DECLARE @ACTUAL_START_PERIOD_SID     INT,
                  @PROJECTION_START_PERIOD_SID INT,
                  @PROJECTION_END_PERIOD_SID   INT,
                  @ACTUAL_START_DATE           DATE,
                  @PROJECTION_END_PERIOD_DATE  DATE,
                  @PROJECTION_START_DATE       DATE,
                  @FORECAST_NAME               VARCHAR(50),
                  @FORECAST_VERSION            VARCHAR(15),
                  @ITEM_UOM                    VARCHAR(50) ='EACH',-- 'UN' TO 'EACH' GALUAT-46    
                  @SQL                         NVARCHAR(MAX),
                  @BUSINESS_UNIT               INT,--------------------GAL-808
                  @GL_COMPANY_ID               INT,--------------------GAL-808
                  @FROM_PERIOD_DATE            DATETIME,
                  @TYPE                        INT,
				  @ADJUSTMENT_TYPE			   INT
          DECLARE @ARM_DISTRIBUTION_FEES_SALES_TABLE VARCHAR(200) = Concat('ST_ARM_DISTRIBUTION_FEES_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ARM_DISTRIBUTION_FEES_RATE_TABLE  VARCHAR(200) = Concat('ST_ARM_DISTRIBUTION_FEES_RATE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

-- Variables Initialization Ends here
 
-- Select base methodolgy starts here

          SELECT @TYPE = TRANSACTION_TYPE
          FROM   ARM_ADJUSTMENT_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

-- Select base methodolgy Ends here

 --Effective dating and elimination of CCP'S Based on effective dating Concept Starts here
 
          IF Object_id('TEMPDB..#TEMP_EFFECTIVE') IS NOT NULL
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
 
          SELECT @FROM_PERIOD_DATE = CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(FROM_DATE, 0))))
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
		  SELECT @ADJUSTMENT_TYPE=(SELECT TRANSACTION_TYPE FROM ARM_ADJUSTMENT_MASTER WHERE PROJECTION_MASTER_SID= @PROJECTION_MASTER_SID)
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
          FROM   Udf_date_finder (@PROJECTION_MASTER_SID, 'D')
          WHERE  START_DATE = @FROM_PERIOD_DATE
 
          SELECT @PROJECTION_START_PERIOD_SID = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = @FROM_PERIOD_DATE

 --Effective dating and elimination of CCP'S Based on effective dating Concept Ends here

  -- Pulling CCP+D Combination for Current projection starts here
 
          IF Object_id('TEMPDB..#ARM_MASTER_MASTER') IS NOT NULL
            TRUNCATE TABLE #ARM_MASTER_MASTER
          ELSE
            CREATE TABLE #ARM_MASTER_MASTER
              (
                 PROJECTION_MASTER_SID      INT,
                 ARM_ADJUSTMENT_DETAILS_SID INT,
                 CCP_DETAILS_SID            INT,
                 CONTRACT_MASTER_SID        INT,
                 COMPANY_MASTER_SID         INT,
                 ITEM_MASTER_SID            INT,
                 RS_MODEL_SID               INT,
                 ACTUAL_START_DATE          DATETIME,
                 PROJ_START_DATE            DATETIME,
                 PROJECTION_END_PERIOD_DATE DATETIME,
                 GL_COMPANY_MASTER_SID      INT
                 PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID)
              )
 
          INSERT INTO #ARM_MASTER_MASTER
                      (PROJECTION_MASTER_SID,
                       ARM_ADJUSTMENT_DETAILS_SID,
                       CCP_DETAILS_SID,
                       CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID,
                       RS_MODEL_SID,
                       ACTUAL_START_DATE,
                       PROJ_START_DATE,
                       PROJECTION_END_PERIOD_DATE,
                       GL_COMPANY_MASTER_SID)
          SELECT PM.PROJECTION_MASTER_SID,
                 ARM_ADJUSTMENT_DETAILS_SID,
                 CD.CCP_DETAILS_SID,
                 CONTRACT_MASTER_SID,
                 CD.COMPANY_MASTER_SID,
                 ITEM_MASTER_SID,
                 RS_MODEL_SID,
                 Datefromparts(Year(Getdate()) - 3, 01, 01) AS ACTUAL_START_DATE,
                 Datefromparts(Year(FROM_DATE), Month(FROM_DATE), 01) AS PROJ_START_DATE,
                 Datefromparts(Year(TO_DATE), Month(TO_DATE), 01) AS PROJECTION_END_PERIOD_DATE,
                 PM.COMPANY_MASTER_SID AS GL_COMPANY_MASTER_SID
          FROM   ARM_ADJUSTMENT_DETAILS AD
                 INNER JOIN PROJECTION_MASTER PM
                         ON AD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
                 INNER JOIN CCP_DETAILS CD
                         ON CD.CCP_DETAILS_SID = AD.CCP_DETAILS_SID
          WHERE  PM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                 AND FORECASTING_TYPE = 'ARM'


  -- Pulling CCP+D Combination for Current projection Ends here

  --Effective Dating Locic applies here
 
          IF Object_id('TEMPDB..#ARM_MASTER') IS NOT NULL
            DROP TABLE #ARM_MASTER
 
          SELECT A.PROJECTION_MASTER_SID,
                 A.ARM_ADJUSTMENT_DETAILS_SID,
                 A.CCP_DETAILS_SID,
                 A.CONTRACT_MASTER_SID,
                 A.COMPANY_MASTER_SID,
                 A.ITEM_MASTER_SID,
                 A.RS_MODEL_SID,
                 A.ACTUAL_START_DATE,
                 A.PROJ_START_DATE,
                 A.PROJECTION_END_PERIOD_DATE,
                 A.GL_COMPANY_MASTER_SID
          INTO   #ARM_MASTER
          FROM   #ARM_MASTER_MASTER A
                 JOIN #TEMP_EFFECTIVE B
                   ON A.ARM_ADJUSTMENT_DETAILS_SID = B.PROJECTION_DETAILS_SID
          WHERE  START_DATE = @FROM_PERIOD_DATE

--Taking Liability Accounts and calculating Debit and Credit Logic Starts here
 
          IF Object_id('TEMPDB..#REBATE_INFO') IS NOT NULL
            DROP TABLE #REBATE_INFO
 
          CREATE TABLE #REBATE_INFO
            (
               PROJECTION_MASTER_SID           INT,
               ARM_ADJUSTMENT_DETAILS_SID      INT,
               CONTRACT_MASTER_SID             INT,
               COMPANY_MASTER_SID              INT,
               ITEM_MASTER_SID                 INT,
               RS_MODEL_SID                    INT,
               BUNDLE_NO                       INT,
               REBATE_PLAN_MASTER_SID          INT,
               TIER_OPERATOR                   CHAR(1),
               REBATE_STRUCTURE                VARCHAR(50),
               REBATE_BASED_ON                 VARCHAR(50),
               REBATE_RANGE_BASED_ON           VARCHAR(50),
               CALCULATION_TYPE                VARCHAR(50),
               CALCULATION_LEVEL               VARCHAR(50),
               CALCULATION_RULE                VARCHAR(50),
               EVALUATION_RULE                 VARCHAR(50),
               RS_NET_SALES_FORMULA_MASTER_SID INT,
               RP_NET_SALES_FORMULA_MASTER_SID INT,
               ITEM_PRICING_QUALIFIER_SID      INT,
               RETURN_RATE_SID                 INT,
               [USER_ID]                       INT,
               SESSION_ID                      INT
               PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID)
            )
 
          IF Object_id('TEMPDB..#APPROVE_CCP') IS NOT NULL
            DROP TABLE #APPROVE_CCP
 
          CREATE TABLE #APPROVE_CCP
            (
               ARM_ADJUSTMENT_DETAILS_SID INT,
               CONTRACT_MASTER_SID        INT,
               COMPANY_MASTER_SID         INT,
               ITEM_MASTER_SID            INT,
               CCP_DETAILS_SID            INT,
               RS_MODEL_SID               INT,
               CURRENT_BALANCE            NUMERIC(22, 6),
               PERIOD_SID                 INT
               PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID)
            );
 
          WITH APPROVE_CTE
               AS (SELECT WM.PROJECTION_MASTER_SID,
                          HT.DESCRIPTION AS WORKFLOW_STATUS
                   FROM   WORKFLOW_MASTER WM
                          INNER JOIN HELPER_TABLE HT
                                  ON WM.WORKFLOW_STATUS_ID = HT.HELPER_TABLE_SID
                                     AND HT.DESCRIPTION = 'APPROVED'
                                     AND WM.WORKFLOW_ID LIKE 'ARM%'
                          INNER JOIN ARM_ADJUSTMENT_MASTER AM
                                  ON AM.PROJECTION_MASTER_SID = WM.PROJECTION_MASTER_SID
                                     AND AM.TRANSACTION_TYPE = @TYPE)
          INSERT INTO #APPROVE_CCP
                      (ARM_ADJUSTMENT_DETAILS_SID,
                       CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID,
                       CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       CURRENT_BALANCE,
                       PERIOD_SID)
          SELECT ARM_ADJUSTMENT_DETAILS_SID,
                 CONTRACT_MASTER_SID,
                 COMPANY_MASTER_SID,
                 ITEM_MASTER_SID,
                 CCP_DETAILS_SID,
                 RS_MODEL_SID,
                 Isnull(( Abs(Isnull(Sum(CREDIT_BALANCE), 0)) - Abs(Isnull(Sum(DEBIT_BALANCE), 0)) ), 0) AS CURRENT_BALANCE,
                 PERIOD_SID
          FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                         CONTRACT_MASTER_SID,
                         COMPANY_MASTER_SID,
                         ITEM_MASTER_SID,
                         CCP_DETAILS_SID,
                         RS_MODEL_SID,
                         CASE
                                          WHEN B.INDICATOR = CREDIT THEN Isnull(CURRENT_BALANCE, 0)
                                        END AS CREDIT_BALANCE,
                         CASE
                                         WHEN B.INDICATOR = DEBIT THEN Isnull(CURRENT_BALANCE, 0)
                                       END DEBIT_BALANCE,
                         PERIOD_SID
                  FROM   (SELECT A.ARM_ADJUSTMENT_DETAILS_SID,
                                 CONTRACT_MASTER_SID,
                                 COMPANY_MASTER_SID,
                                 ITEM_MASTER_SID,
                                 CCP_DETAILS_SID,
                                 RS_MODEL_SID,
                                 CASE
                                             WHEN CURRENT_BALANCE > 0 THEN 0
                                             WHEN CURRENT_BALANCE < 0 THEN 1
                                           END AS INDICATOR,
                                 CREDIT,
                                 DEBIT,
                                 CURRENT_BALANCE,
                                 PERIOD_SID
                          FROM   (SELECT ADFR.ARM_ADJUSTMENT_DETAILS_SID,
                                         B.CONTRACT_MASTER_SID,
                                         B.COMPANY_MASTER_SID,
                                         B.ITEM_MASTER_SID,
                                         B.CCP_DETAILS_SID,
                                         B.RS_MODEL_SID,
                                         COALESCE(Sum(OVERRIDE)
                                                                    OVER (
                                                                      PARTITION BY ADFR.ARM_ADJUSTMENT_DETAILS_SID), Sum(VARIANCE)
                                                                                                                       OVER (
                                                                                                                         PARTITION BY ADFR.ARM_ADJUSTMENT_DETAILS_SID)) AS CURRENT_BALANCE,
                                         PERIOD_SID
                                  FROM   (SELECT C.PROJECTION_MASTER_SID,
                                                 CD.CONTRACT_MASTER_SID,
                                                 CD.COMPANY_MASTER_SID,
                                                 CD.ITEM_MASTER_SID,
                                                 CD.CCP_DETAILS_SID,
                                                 PD.RS_MODEL_SID,
                                                 WORKFLOW_STATUS,
                                                 PD.ARM_ADJUSTMENT_DETAILS_SID
                                          FROM   APPROVE_CTE C
                                                 INNER JOIN ARM_ADJUSTMENT_DETAILS PD
                                                         ON PD.PROJECTION_MASTER_SID = C.PROJECTION_MASTER_SID
                                                 INNER JOIN CCP_DETAILS CD
                                                         ON CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID)B
                                         INNER JOIN #ARM_MASTER_MASTER AM
                                                 ON B.CONTRACT_MASTER_SID = AM.CONTRACT_MASTER_SID
                                                    AND B.COMPANY_MASTER_SID = AM.COMPANY_MASTER_SID
                                                    AND B.ITEM_MASTER_SID = AM.ITEM_MASTER_SID
                                                    AND B.RS_MODEL_SID = AM.RS_MODEL_SID
                                         INNER JOIN ARM_DISTRIBUTION_FEES_RATE ADFR
                                                 ON ADFR.ARM_ADJUSTMENT_DETAILS_SID = B.ARM_ADJUSTMENT_DETAILS_SID
                                                    AND ADFR.PERIOD_SID = @PROJECTION_START_PERIOD_SID)A
                                 INNER JOIN ARM_ADJ_RES_CCP AAC
                                         ON A.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
                                            AND AAC.ADJUSTMENT_TYPE = @TYPE
                                 INNER JOIN HELPER_TABLE HT
                                         ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
                                            AND HT.DESCRIPTION = 'LIABILITY')B)C
          GROUP  BY ARM_ADJUSTMENT_DETAILS_SID,
                    CONTRACT_MASTER_SID,
                    COMPANY_MASTER_SID,
                    ITEM_MASTER_SID,
                    CCP_DETAILS_SID,
                    RS_MODEL_SID,
                    PERIOD_SID

--Taking Liability Accounts and calculating Debit and Credit Logic Ends here

--Creating Temp Tables for Credit and Debit Starts here 
 
          IF Object_id('TEMPDB..#TEMP_OUTPUT') IS NOT NULL
            DROP TABLE #TEMP_OUTPUT
 
          CREATE TABLE #TEMP_OUTPUT
            (
               ARM_ADJUSTMENT_DETAILS_SID INT ,
               PERIOD_SID                 INT,
               CURRENT_BALANCE            NUMERIC(22, 6),
               CALCULATED_ADJUSTMENT      NUMERIC(22, 6),
               VARIANCE                   NUMERIC(22, 6)
            )
 
          IF Object_id('TEMPDB..#TEMP_AMOUNT') IS NOT NULL
            DROP TABLE #TEMP_AMOUNT
 
          CREATE TABLE #TEMP_AMOUNT
            (
               ARM_ADJUSTMENT_DETAILS_SID INT,
               PERIOD_SID                 INT,
               CURRENT_BALANCE             NUMERIC(22, 6),
               INDICATOR                  BIT
            )
 
          IF Object_id('TEMPDB..#CURRENT_LIABILITY') IS NOT NULL
            DROP TABLE #CURRENT_LIABILITY
 
          CREATE TABLE #CURRENT_LIABILITY
            (
               ARM_ADJUSTMENT_DETAILS_SID INT,
               PERIOD_SID                 INT,
               CREDIT_AMOUNT              NUMERIC(22, 6),
               DEBIT_AMOUNT               NUMERIC(22, 6),
               CURRENT_AMOUNT             NUMERIC(22, 6)
            )
 
          IF Object_id('TEMPDB..#CURRENT_EXPENSE') IS NOT NULL
            DROP TABLE #CURRENT_EXPENSE
 
          CREATE TABLE #CURRENT_EXPENSE
            (
               ARM_ADJUSTMENT_DETAILS_SID INT,
               PERIOD_SID                 INT,
               CREDIT_AMOUNT              NUMERIC(22, 6),
               DEBIT_AMOUNT               NUMERIC(22, 6),
               CURRENT_AMOUNT             NUMERIC(22, 6)
            )

--Creating Temp Tables for Credit and Debit Ends here 
 
          IF EXISTS(SELECT 1
                    FROM   #TEMP_EFFECTIVE)
            BEGIN
                SELECT TOP 1 @ACTUAL_START_DATE = ACTUAL_START_DATE,-- FIRST DAY OF -3 YEARS    
                             @PROJECTION_START_DATE = PROJ_START_DATE,--FIRST DAY OF THE QUARTER    
                             @PROJECTION_END_PERIOD_DATE = PROJECTION_END_PERIOD_DATE,--LAST DAY OF QUARTER
                             @GL_COMPANY_ID = GL_COMPANY_MASTER_SID--------------------GAL-808
                FROM   #ARM_MASTER ORDER BY GL_COMPANY_MASTER_SID
 
                SELECT @ACTUAL_START_PERIOD_SID = Max(CASE
                                                        WHEN @ACTUAL_START_DATE = PERIOD_DATE THEN PERIOD_SID
                                                      END),
                       @PROJECTION_START_PERIOD_SID = Max(CASE
                                                            WHEN @PROJECTION_START_DATE = PERIOD_DATE THEN PERIOD_SID
                                                          END),
                       @PROJECTION_END_PERIOD_SID = Max(CASE
                                                          WHEN @PROJECTION_END_PERIOD_DATE = PERIOD_DATE THEN PERIOD_SID
                                                        END)
                FROM   PERIOD
                WHERE  PERIOD_DATE IN ( @ACTUAL_START_DATE, @PROJECTION_START_DATE, @PROJECTION_END_PERIOD_DATE )
 
                SELECT @BUSINESS_UNIT = BU_COMPANY_MASTER_SID
                FROM   ARM_ADJUSTMENT_MASTER
                WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

-- CONTRACT DETAILS METHODOLOGY STARTS HERE  
 
                IF Object_id('TEMPDB..#CONTRACT_DETAILS') IS NOT NULL
                  TRUNCATE TABLE #CONTRACT_DETAILS
                ELSE
                  CREATE TABLE #CONTRACT_DETAILS
                    (
                       ARM_ADJUSTMENT_DETAILS_SID INT,
                       RS_MODEL_SID               INT,
                       PRICE_GROUP_TYPE           VARCHAR(50),
                       PERIOD_SID                 INT,
                       PROJECTION_AMOUNT          NUMERIC(22, 6),
                       PROJECTION_RATE            NUMERIC(22, 6),
                       PROJECTION_REBATE_PER_UNIT NUMERIC(22, 6),
                       NET_CALCULATED_SALES       NUMERIC(22, 6),
                       NET_UNITS                  NUMERIC(22, 6),
                       SALES_PROJECTED_VALUE      NUMERIC(22, 6),
                       CALCULATION_TYPE           VARCHAR(50),
                       CALCULATION_RULE           INT,
                       EVALUATION_RULE            INT
                       PRIMARY KEY(ARM_ADJUSTMENT_DETAILS_SID)
                    )  
                ;
 
                WITH CTE
                     AS (SELECT AM.PROJECTION_MASTER_SID,
                                AM.ARM_ADJUSTMENT_DETAILS_SID,
                                AM.CONTRACT_MASTER_SID,
                                AM.COMPANY_MASTER_SID,
                                AM.ITEM_MASTER_SID,
                                AM.RS_MODEL_SID,
                                RS.BUNDLE_NO,
                                HT.[DESCRIPTION]                AS CALCULATION_TYPE,
                                HL.[DESCRIPTION]                AS CALCULATION_LEVEL,
                                RS.EVALUATION_RULE,
                                RS.CALCULATION_RULE,
                                @USER_ID AS [USER_ID],
                                @SESSION_ID AS SESSION_ID, 
                                RS.RS_CONTRACT_DETAILS_SID,
                                RS.NET_SALES_FORMULA_MASTER_SID AS RS_NET_SALES_FORMULA_MASTER_SID
                         FROM   #ARM_MASTER AM
                                INNER JOIN RS_CONTRACT RC
                                        ON RC.RS_MODEL_SID = AM.RS_MODEL_SID
                                           AND RC.CONTRACT_MASTER_SID = AM.CONTRACT_MASTER_SID
                                           AND RC.INBOUND_STATUS <> 'D'
                                INNER JOIN RS_CONTRACT_DETAILS RS
                                        ON RS.RS_CONTRACT_SID = RC.RS_CONTRACT_SID
                                           AND RS.ITEM_MASTER_SID = AM.ITEM_MASTER_SID
                                           AND RS.INBOUND_STATUS <> 'D'
                                LEFT JOIN HELPER_TABLE HT
                                       ON HT.HELPER_TABLE_SID = RC.CALCULATION_TYPE
                                          AND HT.LIST_NAME = 'CALCULATION_TYPE'
                                LEFT JOIN HELPER_TABLE HL
                                       ON HL.HELPER_TABLE_SID = RC.CALCULATION_LEVEL
                                          AND HL.LIST_NAME = 'CALCULATION_LEVEL'
                         WHERE  EXISTS (SELECT 1
                                        FROM   RS_MODEL RM
                                               INNER JOIN RS_DETAILS RD
                                                       ON RM.RS_MODEL_SID = RD.RS_MODEL_SID
                                                          AND RD.ITEM_MASTER_SID = AM.ITEM_MASTER_SID
                                        WHERE  AM.RS_MODEL_SID = RM.RS_MODEL_SID)
                                AND EXISTS (SELECT 1
                                            FROM   CFP_CONTRACT CC
                                                   INNER JOIN IFP_CONTRACT IC
                                                           ON CC.CFP_CONTRACT_SID = IC.CFP_CONTRACT_SID
                                                              AND CC.CONTRACT_MASTER_SID = IC.CONTRACT_MASTER_SID
                                                   INNER JOIN PS_CONTRACT PC
                                                           ON PC.CFP_CONTRACT_SID = IC.CFP_CONTRACT_SID
                                                              AND PC.IFP_CONTRACT_SID = IC.IFP_CONTRACT_SID
                                                              AND PC.CONTRACT_MASTER_SID = IC.CONTRACT_MASTER_SID
                                            WHERE  CC.CFP_CONTRACT_SID = RC.CFP_CONTRACT_SID
                                                   AND IC.IFP_CONTRACT_SID = RC.IFP_CONTRACT_SID
                                                   AND PC.PS_CONTRACT_SID = RC.PS_CONTRACT_SID
                                                   AND CC.CONTRACT_MASTER_SID = RC.CONTRACT_MASTER_SID
                                                   AND IC.CONTRACT_MASTER_SID = RC.CONTRACT_MASTER_SID
                                                   AND PC.CONTRACT_MASTER_SID = RC.CONTRACT_MASTER_SID
                                                   AND CC.INBOUND_STATUS <> 'D'
                                                   AND IC.INBOUND_STATUS <> 'D'
                                                   AND PC.INBOUND_STATUS <> 'D')),
                     REBATE_INFO
                     AS (
-- IF STRUCTURE - REBATE PLAN  Starts here   
                        SELECT PROJECTION_MASTER_SID,
                               ARM_ADJUSTMENT_DETAILS_SID,
                               CONTRACT_MASTER_SID,
                               COMPANY_MASTER_SID,
                               ITEM_MASTER_SID,
                               RS_MODEL_SID,
                               BUNDLE_NO,
                               REBATE_PLAN_MASTER_SID,
                               TIER_OPERATOR,
                               REBATE_STRUCTURE,
                               REBATE_BASED_ON,
                               REBATE_RANGE_BASED_ON,
                               CALCULATION_TYPE,
                               CALCULATION_LEVEL,
                               ITEM_PRICING_QUALIFIER_SID,
                               RETURN_RATE_SID,
                               EVALUATION_RULE,
                               CALCULATION_RULE,
                               RS_NET_SALES_FORMULA_MASTER_SID,
                               RP_NET_SALES_FORMULA_MASTER_SID,
                               [USER_ID],
                               SESSION_ID
                         FROM   (SELECT Row_number()
                                          OVER (
                                            PARTITION BY T.ARM_ADJUSTMENT_DETAILS_SID
                                            ORDER BY T.ARM_ADJUSTMENT_DETAILS_SID)RN,
                                        T.PROJECTION_MASTER_SID,
                                        T.ARM_ADJUSTMENT_DETAILS_SID,
                                        T.CONTRACT_MASTER_SID,
                                        T.COMPANY_MASTER_SID,
                                        T.ITEM_MASTER_SID,
                                        T.RS_MODEL_SID,
                                        T.BUNDLE_NO,
                                        RC.REBATE_PLAN_MASTER_SID,
                                        HTO.[DESCRIPTION]                         AS TIER_OPERATOR,
                                        HRS.[DESCRIPTION]                         AS REBATE_STRUCTURE,
                                        HRB.[DESCRIPTION]                         AS REBATE_BASED_ON,
                                        HRR.[DESCRIPTION]                         AS REBATE_RANGE_BASED_ON,
                                        T.CALCULATION_TYPE,
                                        T.CALCULATION_LEVEL,
                                        RPT.ITEM_PRICING_QUALIFIER_SID,
                                        RPT.RETURN_RATE_SID,
                                        RC.EVALUATION_RULE,
                                        RC.CALCULATION_RULE,
                                        RS_NET_SALES_FORMULA_MASTER_SID,
                                        RPM.NET_SALES_FORMULA_MASTER_SID          AS RP_NET_SALES_FORMULA_MASTER_SID,
                                        [USER_ID],
                                        SESSION_ID
                                 FROM   CTE T
                                        JOIN RS_CONTRACT_DETAILS RC
                                          ON RC.RS_CONTRACT_DETAILS_SID = T.RS_CONTRACT_DETAILS_SID
                                        LEFT JOIN REBATE_PLAN_MASTER RPM
                                               ON RC.REBATE_PLAN_MASTER_SID = RPM.REBATE_PLAN_MASTER_SID
                                        LEFT JOIN REBATE_PLAN_TIER RPT
                                               ON RPM.REBATE_PLAN_MASTER_SID = RPT.REBATE_PLAN_MASTER_SID
                                        LEFT JOIN HELPER_TABLE HRS
                                               ON HRS.HELPER_TABLE_SID = RPM.REBATE_STRUCTURE
                                                  AND HRS.LIST_NAME = 'REBATE_STRUCTURE'
                                        LEFT JOIN HELPER_TABLE HTO
                                               ON HTO.HELPER_TABLE_SID = RPT.TIER_OPERATOR
                                                  AND HTO.LIST_NAME = 'TIER_OPERATOR'
                                        LEFT JOIN HELPER_TABLE HRB
                                               ON HRB.HELPER_TABLE_SID = RPM.REBATE_BASED_ON
                                                  AND HRB.LIST_NAME = 'REBATE_BASED_ON'
                                        LEFT JOIN HELPER_TABLE HRR
                                               ON HRR.HELPER_TABLE_SID = RPM.REBATE_RANGE_BASED_ON
                                                  AND HRR.LIST_NAME = 'REBATE_RANGE_BASED_ON')A
                         WHERE  RN = 1)
                INSERT INTO #REBATE_INFO
                            (PROJECTION_MASTER_SID,
                             ARM_ADJUSTMENT_DETAILS_SID,
                             CONTRACT_MASTER_SID,
                             COMPANY_MASTER_SID,
                             ITEM_MASTER_SID,
                             RS_MODEL_SID,
                             BUNDLE_NO,
                             REBATE_PLAN_MASTER_SID,
                             TIER_OPERATOR,
                             REBATE_STRUCTURE,
                             REBATE_BASED_ON,
                             REBATE_RANGE_BASED_ON,
                             CALCULATION_TYPE,
                             CALCULATION_LEVEL,
                             ITEM_PRICING_QUALIFIER_SID,
                             RETURN_RATE_SID,
                             EVALUATION_RULE,
                             CALCULATION_RULE,
                             RS_NET_SALES_FORMULA_MASTER_SID,
                             RP_NET_SALES_FORMULA_MASTER_SID,
                             [USER_ID],
                             SESSION_ID)
                SELECT PROJECTION_MASTER_SID,
                       ARM_ADJUSTMENT_DETAILS_SID,
                       CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID,
                       RS_MODEL_SID,
                       BUNDLE_NO,
                       REBATE_PLAN_MASTER_SID,
                       TIER_OPERATOR,
                       REBATE_STRUCTURE,
                       REBATE_BASED_ON,
                       REBATE_RANGE_BASED_ON,
                       CALCULATION_TYPE,
                       CALCULATION_LEVEL,
                       ITEM_PRICING_QUALIFIER_SID,
                       RETURN_RATE_SID,
                       EVALUATION_RULE,
                       CALCULATION_RULE,
                       RS_NET_SALES_FORMULA_MASTER_SID,
                       RP_NET_SALES_FORMULA_MASTER_SID,
                       [USER_ID],
                       SESSION_ID
                FROM   REBATE_INFO
 
                IF EXISTS (SELECT 1
                           FROM   #REBATE_INFO
                           WHERE  CALCULATION_TYPE = 'REBATE PLAN')
                  BEGIN
                      IF Object_id('TEMPDB..#TIER_INFO') IS NOT NULL
                        TRUNCATE TABLE #TIER_INFO
                      ELSE
                        CREATE TABLE #TIER_INFO
                          (
                             ARM_ADJUSTMENT_DETAILS_SID INT,
                             RS_MODEL_SID               INT,
                             TIER_FROM                  NUMERIC(22, 6),
                             TIER_TO                    NUMERIC(22, 6),
                             DISCOUNT_RATE              NUMERIC(22, 6),
                             REBATE_PER_UNIT            NUMERIC(22, 6),
                             FLAT_DISCOUNT              NUMERIC(22, 6),
                             ITEM_PRICING_QUALIFIER_SID INT,
                             RETURN_RATE_SID            INT
                          )
 
                      INSERT INTO #TIER_INFO
                                  (ARM_ADJUSTMENT_DETAILS_SID,
                                   RS_MODEL_SID,
                                   TIER_FROM,
                                   TIER_TO,
                                   DISCOUNT_RATE,
                                   REBATE_PER_UNIT,
                                   FLAT_DISCOUNT,
                                   ITEM_PRICING_QUALIFIER_SID,
                                   RETURN_RATE_SID)
                      SELECT DISTINCT ARM_ADJUSTMENT_DETAILS_SID,
                                      RS_MODEL_SID,
                                      TIER_FROM,
                                      TIER_TO,
                                      Isnull(CASE
                                               WHEN RS.TIER_OPERATOR = '%' THEN RPT.TIER_VALUE
                                             END, 0) AS DISCOUNT_RATE,
                                      Isnull(CASE
                                               WHEN RS.TIER_OPERATOR = '$' THEN
                                                 CASE
                                                   WHEN RS.REBATE_STRUCTURE = 'TIER'
                                                         OR RS.REBATE_STRUCTURE = 'LEVEL' THEN RPT.TIER_VALUE
                                                 END
                                             END, 0) AS REBATE_PER_UNIT,
                                      Isnull(CASE
                                               WHEN RS.TIER_OPERATOR = '$' THEN
                                                 CASE
                                                   WHEN RS.REBATE_STRUCTURE = 'FLAT' THEN RPT.TIER_VALUE
                                                 END
                                             END, 0) AS FLAT_DISCOUNT,
                                      RS.ITEM_PRICING_QUALIFIER_SID,
                                      RS.RETURN_RATE_SID
                      FROM   #REBATE_INFO RS
                             JOIN REBATE_PLAN_MASTER RPM
                               ON RS.REBATE_PLAN_MASTER_SID = RPM.REBATE_PLAN_MASTER_SID
                             JOIN REBATE_PLAN_TIER RPT
                               ON RPM.REBATE_PLAN_MASTER_SID = RPT.REBATE_PLAN_MASTER_SID
 
                      IF Object_id('TEMPDB..#TEMP_ITEM_PRICING') IS NOT NULL
                        DROP TABLE #TEMP_ITEM_PRICING
 
                      CREATE TABLE #TEMP_ITEM_PRICING
                        (
                           ARM_ADJUSTMENT_DETAILS_SID INT,
                           RS_MODEL_SID               INT,
                           ITEM_MASTER_SID            INT,
                           ITEM_PRICING_QUALIFIER     VARCHAR(500),
                           PERIOD_SID                 INT,
                           ITEM_PRICE                 NUMERIC(22, 6)
                        )
 
                      DECLARE @ITEM_UDT UDT_ITEM
 
                      INSERT INTO @ITEM_UDT
                                  (ITEM_MASTER_SID)
                      SELECT DISTINCT ITEM_MASTER_SID
                      FROM   #REBATE_INFO
 
                      IF EXISTS (SELECT 1
                                 FROM   #REBATE_INFO
                                 WHERE  TIER_OPERATOR = '$'
                                        AND ITEM_PRICING_QUALIFIER_SID IS NOT NULL)
                        BEGIN
--TIER_OPERATOR = $ IMPLEMENTATION STARTS here       
                            IF Object_id('TEMPDB..#ITEM_QUALIFIER') IS NOT NULL
                              TRUNCATE TABLE #ITEM_QUALIFIER
                            ELSE
                              CREATE TABLE #ITEM_QUALIFIER
                                (
                                   ARM_ADJUSTMENT_DETAILS_SID INT,
                                   RS_MODEL_SID               INT,
                                   ITEM_MASTER_SID            INT,
                                   ITEM_PRICING_QUALIFIER_SID INT,
                                   PRICING_QUALIFIER          VARCHAR(500)
                                )
 
                            INSERT INTO #ITEM_QUALIFIER
                                        (ARM_ADJUSTMENT_DETAILS_SID,
                                         RS_MODEL_SID,
                                         ITEM_MASTER_SID,
                                         ITEM_PRICING_QUALIFIER_SID,
                                         PRICING_QUALIFIER)
                            SELECT RS.ARM_ADJUSTMENT_DETAILS_SID,
                                   RS.RS_MODEL_SID,
                                   RS.ITEM_MASTER_SID,
                                   IQ.ITEM_PRICING_QUALIFIER_SID,
                                   IQ.PRICING_QUALIFIER
                            FROM   #REBATE_INFO RS
                                   INNER JOIN (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                      RS_MODEL_SID,
                                                      ITEM_PRICING_QUALIFIER_SID
                                               FROM   (SELECT Row_number()
                                                                OVER (
                                                                  PARTITION BY ARM_ADJUSTMENT_DETAILS_SID, RS_MODEL_SID
                                                                  ORDER BY ARM_ADJUSTMENT_DETAILS_SID, RS_MODEL_SID) RN,
                                                              ARM_ADJUSTMENT_DETAILS_SID,
                                                              RS_MODEL_SID,
                                                              ITEM_PRICING_QUALIFIER_SID
                                                       FROM   #TIER_INFO)A
                                               WHERE  RN = 1) TI
                                           ON RS.ITEM_PRICING_QUALIFIER_SID = TI.ITEM_PRICING_QUALIFIER_SID
                                              AND RS.ARM_ADJUSTMENT_DETAILS_SID = TI.ARM_ADJUSTMENT_DETAILS_SID
                                              AND RS.RS_MODEL_SID = TI.RS_MODEL_SID
                                   INNER JOIN ITEM_PRICING_QUALIFIER IQ
                                           ON IQ.ITEM_PRICING_QUALIFIER_SID = TI.ITEM_PRICING_QUALIFIER_SID
 
                            DECLARE @PRICING_QUALIFIER VARCHAR(8000)
 
                            SET @PRICING_QUALIFIER= 'BQWAC,BPWAC,EPWAC,EQWAC,WAC,AMP,ANON-FAMP,BP,FCP,FSS,PHS,QNON-FAMP,URA,AFSS,QFSS,TRI-CARE,ALTURA,BURA,CPIURA,MQWAC,PRIOR PERIOD NEP,PRIOR PERIOD WAC,PRIOR PERIOD NET WAC'
 
 -- FOR FINDING LATEST NA_PROJECTIONS FOR EACH ITEM_MASTER_SID Starts here
                            IF Object_id('TEMPDB..#ITEM_NA_PROJ') IS NOT NULL
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
                                           Row_number()
                                             OVER(
                                               PARTITION BY IT.ITEM_MASTER_SID
                                               ORDER BY COALESCE(NA.MODIFIED_DATE, NA.CREATED_DATE) DESC ) AS RN
                                    FROM   NA_PROJ_DETAILS PD
                                           JOIN NA_PROJ_MASTER NA
                                             ON PD.NA_PROJ_MASTER_SID = NA.NA_PROJ_MASTER_SID
                                           JOIN @ITEM_UDT IT
                                             ON IT.ITEM_MASTER_SID = PD.ITEM_MASTER_SID
                                    WHERE  SAVE_FLAG = 1)A
                            WHERE  RN = 1
 
-- FOR FINDING LATEST NA_PROJECTIONS FOR EACH ITEM_MASTER_SID ENDS HERE                 
                            INSERT INTO #TEMP_ITEM_PRICING
                                        (ARM_ADJUSTMENT_DETAILS_SID,
                                         RS_MODEL_SID,
                                         ITEM_MASTER_SID,
                                         PERIOD_SID,
                                         ITEM_PRICE,
                                         ITEM_PRICING_QUALIFIER)
                            SELECT R.ARM_ADJUSTMENT_DETAILS_SID,
                                   R.RS_MODEL_SID,
                                   R.ITEM_MASTER_SID,
                                   @PROJECTION_START_PERIOD_SID AS PERIOD_SID,
                                   0,
                                   PRICING_QUALIFIER AS ITEM_PRICING_QUALIFIER
                            FROM   #REBATE_INFO R
                                   INNER JOIN #ITEM_QUALIFIER TQ
                                           ON R.ITEM_MASTER_SID = TQ.ITEM_MASTER_SID
                                              AND R.ARM_ADJUSTMENT_DETAILS_SID = TQ.ARM_ADJUSTMENT_DETAILS_SID
                                              AND R.RS_MODEL_SID = TQ.RS_MODEL_SID
                                              AND R.ITEM_PRICING_QUALIFIER_SID = TQ.ITEM_PRICING_QUALIFIER_SID;
 
                            WITH PERIOD_NA
                                 AS (SELECT PERIOD_SID,
                                            [QUARTER],
                                            [YEAR]
                                     FROM   PERIOD
                                     WHERE  [QUARTER] = Datepart(Q, @PROJECTION_START_DATE)
                                            AND [YEAR] = Year(@PROJECTION_START_DATE)),
                                 ITEM_PRICE
                                 AS (SELECT IQU.ARM_ADJUSTMENT_DETAILS_SID,
                                            IQU.RS_MODEL_SID,
                                            IQU.ITEM_MASTER_SID,
                                            IQU.ITEM_PRICING_QUALIFIER_SID,
                                            IQU.PRICING_QUALIFIER,
                                            UDF.PERIOD_SID,
                                            Iif(ITEM_PRICE = 0, NULL, ITEM_PRICE) AS ITEM_PRICE,--- GAL-1008
                                            P.[QUARTER],
                                            P.[YEAR]
                                     FROM   Udf_item_pricing(@ITEM_UDT, @PRICING_QUALIFIER, @ACTUAL_START_PERIOD_SID, @PROJECTION_END_PERIOD_SID, @ITEM_UOM) UDF -----  @ITEM_UOM 'UN' TO 'EACH'  GALUAT-46    
                                            INNER JOIN #ITEM_QUALIFIER IQU
                                                    ON IQU.ITEM_MASTER_SID = UDF.ITEM_MASTER_SID
                                                       AND IQU.PRICING_QUALIFIER = UDF.PRICING_QUALIFIER
                                            INNER JOIN PERIOD_NA P
                                                    ON UDF.PERIOD_SID = P.PERIOD_SID
                                                       AND P.PERIOD_SID = @PROJECTION_START_PERIOD_SID),
                                 WORKSHEETS_RESULT
                                 AS (SELECT CASE
                                              WHEN IP.PRICING_QUALIFIER IN ( 'BP', 'CPIURA', 'AMP', 'ALTURA',
                                                                             'URA', 'BURA' ) THEN COALESCE(IP.ITEM_PRICE, NULLIF(M.PROJECTION_PRICE, 0), 0)
                                              WHEN IP.PRICING_QUALIFIER IN ( 'ANON-FAMP', 'AFSS', 'FCP', 'TRI-CARE',
                                                                             'QNON-FAMP', 'QFSS' ) THEN COALESCE(IP.ITEM_PRICE, NULLIF(F.PROJECTION_PRICE, 0), 0)
                                              WHEN IP.PRICING_QUALIFIER = 'PHS' THEN COALESCE(IP.ITEM_PRICE, NULLIF(P.PROJECTION_PRICE, 0), 0)
                                              ELSE IP.ITEM_PRICE
                                            END ITEM_PRICE,
                                            IP.PRICING_QUALIFIER,
                                            IP.PERIOD_SID,
                                            IQ.ARM_ADJUSTMENT_DETAILS_SID,
                                            IQ.RS_MODEL_SID,
                                            IQ.ITEM_MASTER_SID,
                                            IT_NA.NA_PROJ_MASTER_SID,
                                            IP.[QUARTER],
                                            IP.[YEAR]
                                     FROM   ITEM_PRICE IP
                                            LEFT JOIN #ITEM_QUALIFIER IQ
                                                   ON IP.ARM_ADJUSTMENT_DETAILS_SID = IQ.ARM_ADJUSTMENT_DETAILS_SID
                                                      AND IP.RS_MODEL_SID = IQ.RS_MODEL_SID
                                                      AND IP.ITEM_MASTER_SID = IQ.ITEM_MASTER_SID
                                                      AND IP.PRICING_QUALIFIER = IQ.PRICING_QUALIFIER
                                            INNER JOIN #ITEM_NA_PROJ IT_NA
                                                    ON IT_NA.ITEM_MASTER_SID = IQ.ITEM_MASTER_SID
                                            ------------------------------------------- FOR LATEST NA PROJECTIONS    
                                            LEFT JOIN (SELECT ( MP.PROJECTION_PRICE ) AS PROJECTION_PRICE,
                                                              PN.[QUARTER],
                                                              PN.[YEAR],
                                                              MP.PRICE_TYPE,
                                                              MP.NA_PROJ_DETAILS_SID
                                                       FROM   MEDICAID_URA_PROJ MP
                                                              LEFT JOIN (SELECT Min(PERIOD_SID) AS PERIOD_SID,
                                                                                [QUARTER],
                                                                                [YEAR]
                                                                         FROM   PERIOD_NA
                                                                         GROUP  BY [QUARTER],
                                                                                   [YEAR]) PN
                                                                     ON PN.PERIOD_SID = MP.PERIOD_SID) M
                                                   ON IT_NA.NA_PROJ_DETAILS_SID = M.NA_PROJ_DETAILS_SID
                                                      AND IP.[QUARTER] = M.[QUARTER]
                                                      AND IP.[YEAR] = M.[YEAR]
                                                      AND M.PRICE_TYPE = CASE
                                                                           WHEN IP.PRICING_QUALIFIER = 'BP' THEN 'BEST PRICE'
                                                                           WHEN IP.PRICING_QUALIFIER = 'ALTURA' THEN 'ADJUSTMENT CPI (ALT)'
                                                                           WHEN IP.PRICING_QUALIFIER = 'URA' THEN 'TOTAL URA'
                                                                           WHEN IP.PRICING_QUALIFIER = 'BURA' THEN 'BASIC URA'
                                                                           ELSE IP.PRICING_QUALIFIER
                                                                         END
                                            LEFT JOIN (SELECT ( MP.PROJECTION_PRICE ) AS PROJECTION_PRICE,
                                                              PN.[QUARTER],
                                                              PN.[YEAR],
                                                              MP.PRICE_TYPE,
                                                              MP.NA_PROJ_DETAILS_SID
                                                       FROM   FCP_PROJ MP
                                                              LEFT JOIN (SELECT Min(PERIOD_SID) AS PERIOD_SID,
                                                                                [QUARTER],
                                                                                [YEAR]
                                                                         FROM   PERIOD_NA
                                                                         GROUP  BY [QUARTER],
                                                                                   [YEAR]) PN
                                                                     ON PN.PERIOD_SID = MP.PERIOD_SID) F
                                                   ON IT_NA.NA_PROJ_DETAILS_SID = F.NA_PROJ_DETAILS_SID
                                                      AND CASE
                                                            WHEN F.PRICE_TYPE IN ( 'QFSS', 'QNON-FAMP' ) THEN IP.[QUARTER]
                                                            ELSE 1
                                                          END = CASE
                                                                  WHEN F.PRICE_TYPE IN ( 'QFSS', 'QNON-FAMP' ) THEN F.[QUARTER]
                                                                  ELSE 1
                                                                END
                                                      AND IP.[YEAR] = F.[YEAR]
                                                      AND IP.PRICING_QUALIFIER = F.PRICE_TYPE
                                            LEFT JOIN (SELECT ( MP.PROJECTION_PRICE ) AS PROJECTION_PRICE,
                                                              PN.[QUARTER],
                                                              PN.[YEAR],
                                                              MP.PRICE_TYPE,
                                                              MP.NA_PROJ_DETAILS_SID
                                                       FROM   PHS_PROJ MP
                                                              LEFT JOIN (SELECT Min(PERIOD_SID) AS PERIOD_SID,
                                                                                [QUARTER],
                                                                                [YEAR]
                                                                         FROM   PERIOD_NA
                                                                         GROUP  BY [QUARTER],
                                                                                   [YEAR]) PN
                                                                     ON PN.PERIOD_SID = MP.PERIOD_SID) P
                                                   ON IT_NA.NA_PROJ_DETAILS_SID = P.NA_PROJ_DETAILS_SID
                                                      AND IP.[QUARTER] = P.[QUARTER]
                                                      AND IP.[YEAR] = P.[YEAR]
                                                      AND IP.PRICING_QUALIFIER = P.PRICE_TYPE)
                            UPDATE T
                            SET    T.ITEM_PRICE = R.ITEM_PRICE
                            FROM   #TEMP_ITEM_PRICING T
                                   INNER JOIN WORKSHEETS_RESULT R
                                           ON T.ARM_ADJUSTMENT_DETAILS_SID = R.ARM_ADJUSTMENT_DETAILS_SID
                                              AND T.RS_MODEL_SID = R.RS_MODEL_SID
                                              AND T.ITEM_MASTER_SID = R.ITEM_MASTER_SID
                                              AND T.PERIOD_SID = R.PERIOD_SID
                        END
 
                      IF NOT EXISTS (SELECT 1
                                     FROM   #TEMP_ITEM_PRICING)
                        BEGIN
                            INSERT INTO #TEMP_ITEM_PRICING
                                        (ARM_ADJUSTMENT_DETAILS_SID,
                                         RS_MODEL_SID,
                                         ITEM_MASTER_SID,
                                         PERIOD_SID,
                                         ITEM_PRICE)
                            SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                   RS_MODEL_SID,
                                   ITEM_MASTER_SID,
                                   PERIOD_SID,
                                   0
                            FROM   #REBATE_INFO R
                                   CROSS APPLY PERIOD P
                            WHERE  P.PERIOD_SID = @PROJECTION_START_PERIOD_SID
                        END
 
-- TIER_OPERATOR = $ IMPLEMENTATION ENDS here
--EXFACTORY CALCULATION Starts here
                      IF Object_id('TEMPDB..#RETURNS_PERCENT') IS NOT NULL
                        DROP TABLE #RETURNS_PERCENT
 
                      CREATE TABLE #RETURNS_PERCENT
                        (
                           ARM_ADJUSTMENT_DETAILS_SID INT,
                           RS_MODEL_SID               INT,
                           ITEM_MASTER_SID            INT,
                           PERIOD_SID                 INT,
                           RETURN_RATE                NUMERIC(22, 6)
                        )
 
 --TIER OPERATOR =% IMPLEMENTATION STARTS HERE --GALUAT-360    
                      IF EXISTS (SELECT 1
                                 FROM   #REBATE_INFO
                                 WHERE  TIER_OPERATOR = '%'
                                        AND RETURN_RATE_SID IS NOT NULL)
                        BEGIN
                            INSERT INTO #RETURNS_PERCENT
                                        (ARM_ADJUSTMENT_DETAILS_SID,
                                         RS_MODEL_SID,
                                         ITEM_MASTER_SID,
                                         PERIOD_SID,
                                         RETURN_RATE)
                            SELECT A.ARM_ADJUSTMENT_DETAILS_SID,
                                   A.RS_MODEL_SID,
                                   A.ITEM_MASTER_SID,
                                   A.PERIOD_SID,
                                   B.RETURN_RATE
                            FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                           RS_MODEL_SID,
                                           ITEM_MASTER_SID,
                                           @PROJECTION_START_PERIOD_SID AS PERIOD_SID
                                    FROM   #REBATE_INFO)A
                                   LEFT JOIN (SELECT ITEM_MASTER_SID,
                                                     PERIOD_SID,
                                                     PROJECTED_RETURN_PERCENT AS RETURN_RATE
                                              FROM   RETURNS_PROJ_DETAILS RPD
                                                     INNER JOIN(SELECT RD.RETURNS_DETAILS_SID,
                                                                       RD.ITEM_MASTER_SID,
                                                                       RD.PROJECTION_MASTER_SID
                                                                FROM   RETURNS_DETAILS RD
                                                                       INNER JOIN(SELECT PROJECTION_MASTER_SID,
                                                                                         ITEM_MASTER_SID
                                                                                  FROM   (SELECT PROJECTION_MASTER_SID,
                                                                                                 ITEM_MASTER_SID,
                                                                                                 Row_number()
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
                                                                                                  WHERE  FORECASTING_TYPE = 'RETURNS'
                                                                                                         AND SAVE_FLAG = 1)B)A
                                                                                  WHERE  RN = 1) J
                                                                               ON J.PROJECTION_MASTER_SID = RD.PROJECTION_MASTER_SID
                                                                                  AND J.ITEM_MASTER_SID = RD.ITEM_MASTER_SID)D
                                                             ON RPD.RETURNS_DETAILS_SID = D.RETURNS_DETAILS_SID
                                                                AND RPD.PERIOD_SID = @PROJECTION_START_PERIOD_SID)B
                                          ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                             AND A.PERIOD_SID = B.PERIOD_SID
                        END
 
                      IF NOT EXISTS (SELECT 1
                                     FROM   #RETURNS_PERCENT)
                        BEGIN
                            INSERT INTO #RETURNS_PERCENT
                                        (ARM_ADJUSTMENT_DETAILS_SID,
                                         RS_MODEL_SID,
                                         ITEM_MASTER_SID,
                                         PERIOD_SID,
                                         RETURN_RATE)
                            SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                   RS_MODEL_SID,
                                   ITEM_MASTER_SID,
                                   @PROJECTION_START_PERIOD_SID AS PERIOD_SID,
                                   0
                            FROM   #REBATE_INFO
                        END

--TIER OPERATOR =% IMPLEMENTATION ENDS HERE    
                      SET @SQL=Concat(';WITH CONTRACT_DETAILS_INFO
                     AS (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                RS_MODEL_SID,
                                PERIOD_SID,
                                SALES_PROJECTED_VALUE=CASE
                                                        WHEN CALCULATION_LEVEL LIKE ''%SINGLE%'' THEN SUM(SALES_PROJECTED_VALUE)
                                                                                                      OVER (
                                                                                                        PARTITION BY RS_MODEL_SID, PERIOD_SID, COMPANY_MASTER_SID)
                                                        WHEN CALCULATION_LEVEL LIKE ''%LINE%'' THEN SUM(SALES_PROJECTED_VALUE)
                                                                                                    OVER (
                                                                                                      PARTITION BY RS_MODEL_SID, CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID, PERIOD_SID)
                                                        WHEN CALCULATION_LEVEL LIKE ''%BUNDLE%'' THEN SUM(SALES_PROJECTED_VALUE)
                                                                                                      OVER (
                                                                                                        PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, PERIOD_SID)
                                                      END,
                                SALES_NET_UNITS=CASE
                                                  WHEN CALCULATION_LEVEL LIKE ''%SINGLE%'' THEN SUM(NET_UNITS)
                                                                                                OVER (
                                                                                                  PARTITION BY RS_MODEL_SID, PERIOD_SID, COMPANY_MASTER_SID)
                                                  WHEN CALCULATION_LEVEL LIKE ''%LINE%'' THEN SUM(NET_UNITS)
                                                                                              OVER (
                                                                                                PARTITION BY RS_MODEL_SID, CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID, PERIOD_SID)
                                                  WHEN CALCULATION_LEVEL LIKE ''%BUNDLE%'' THEN SUM(NET_UNITS)
                                                                                                OVER (
                                                                                                  PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, PERIOD_SID)
                                                END,
                                CALCULATION_LEVEL,
                                REBATE_STRUCTURE,
                                TIER_OPERATOR,
                                NET_CALCULATED_SALES,
                                NET_UNITS,
                                REBATE_RANGE_BASED_ON,
                                                       CALCULATION_RULE,
                                                       EVALUATION_RULE 
                         FROM   (SELECT DISTINCT R.ARM_ADJUSTMENT_DETAILS_SID,
                                                 CONTRACT_MASTER_SID,
                                                 R.COMPANY_MASTER_SID,
                                                 R.ITEM_MASTER_SID,
                                                 RS_MODEL_SID,
                                                 CALCULATION_LEVEL,
                                                 CASE
                                                   WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT SALES'', ''DOLLARS'' ) THEN NET_CALCULATED_SALES
                                                   WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT UNITS'', ''UNITS'' ) THEN NET_UNITS
                                                 END AS SALES_PROJECTED_VALUE,
                                                 PERIOD_SID=', @PROJECTION_START_PERIOD_SID, ',
                                                 BUNDLE_NO,
                                                 REBATE_STRUCTURE,
                                                 TIER_OPERATOR,
                                                 NET_CALCULATED_SALES,
                                                 NET_UNITS,
                                                 REBATE_RANGE_BASED_ON,
                                                                                  CALCULATION_RULE,
                                                                                  EVALUATION_RULE 
                                 FROM   #REBATE_INFO R
                                        LEFT JOIN ', @ARM_DISTRIBUTION_FEES_SALES_TABLE, ' SNSP
                                               ON R.PROJECTION_MASTER_SID = SNSP.PROJECTION_MASTER_SID
                                                  AND R.ITEM_MASTER_SID = SNSP.ITEM_MASTER_SID
                                                  AND R.COMPANY_MASTER_SID = SNSP.COMPANY_MASTER_SID)A),
                     TIER_INFO
                     AS (SELECT C.ARM_ADJUSTMENT_DETAILS_SID,
                                C.RS_MODEL_SID,
                                C.PERIOD_SID,
                                NET_CALCULATED_SALES,
                                NET_UNITS,
                                SALES_PROJECTED_VALUE,
                                REBATE_STRUCTURE,
                                CALCULATION_LEVEL,
                                TIER_FROM,
                                TIER_TO,
                                DISCOUNT_RATE,
                                REBATE_PER_UNIT,
                                FLAT_DISCOUNT,
                                TIER_OPERATOR,
                                TP.ITEM_PRICE,
                                VALUE=CASE
                                        WHEN REBATE_STRUCTURE = ''TIER'' THEN
                                          CASE
                                            WHEN SALES_PROJECTED_VALUE >= TIER_FROM
                                                 AND SALES_PROJECTED_VALUE <= TIER_TO THEN
                                              CASE
                                                WHEN TIER_OPERATOR = ''%'' THEN ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 )
                                                WHEN TIER_OPERATOR = ''$'' THEN COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0)
                                              END
                                          END
                                        WHEN REBATE_STRUCTURE = ''LEVEL'' THEN
                                          CASE
                                            WHEN TIER_TO < SALES_PROJECTED_VALUE THEN
                                              CASE
                                                WHEN TIER_OPERATOR = ''%'' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 ) )
                                                WHEN TIER_OPERATOR = ''$'' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0) ) )
                                              END
                                            WHEN TIER_TO >= SALES_PROJECTED_VALUE THEN
                                              CASE
                                                WHEN SALES_PROJECTED_VALUE BETWEEN TIER_FROM AND TIER_TO THEN
                                                  CASE
                                                    WHEN TIER_OPERATOR = ''%'' THEN ( ( IIF(SALES_PROJECTED_VALUE < TIER_TO, SALES_PROJECTED_VALUE, TIER_TO) - TIER_FROM ) * ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 ) )
                                                    WHEN TIER_OPERATOR = ''$'' THEN ( ( IIF(SALES_PROJECTED_VALUE < TIER_TO, SALES_PROJECTED_VALUE, TIER_TO) - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0) ) )
                                                  END
                                              END
                                          END
                                        WHEN REBATE_STRUCTURE = ''FLAT'' THEN FLAT_DISCOUNT
                                      END,
                                REBATE_RANGE_BASED_ON,
                                                       CALCULATION_RULE,
                                                       EVALUATION_RULE 
                         FROM   CONTRACT_DETAILS_INFO C
                                JOIN #TEMP_ITEM_PRICING TP
                                  ON C.ARM_ADJUSTMENT_DETAILS_SID = TP.ARM_ADJUSTMENT_DETAILS_SID
                                     AND C.RS_MODEL_SID = TP.RS_MODEL_SID
                                     AND C.PERIOD_SID = TP.PERIOD_SID
                                JOIN #RETURNS_PERCENT RP
                                  ON RP.ARM_ADJUSTMENT_DETAILS_SID = TP.ARM_ADJUSTMENT_DETAILS_SID
                                     AND RP.RS_MODEL_SID = TP.RS_MODEL_SID
                                     AND RP.PERIOD_SID = TP.PERIOD_SID
                                OUTER APPLY (SELECT DISCOUNT_RATE,
                                                    REBATE_PER_UNIT,
                                                    FLAT_DISCOUNT,
                                                    TIER_FROM,
                                                    TIER_TO=CASE
                                                              WHEN T.TIER_TO IS NULL THEN SALES_PROJECTED_VALUE
                                                              ELSE T.TIER_TO
                                                            END
                                             FROM   #TIER_INFO T
                                             WHERE  C.ARM_ADJUSTMENT_DETAILS_SID = T.ARM_ADJUSTMENT_DETAILS_SID
                                                    AND C.RS_MODEL_SID = T.RS_MODEL_SID) OU),
                     AGGREGATION
                     AS (SELECT ROW_NUMBER ()
                                  OVER (
                                    PARTITION BY ARM_ADJUSTMENT_DETAILS_SID, RS_MODEL_SID, PERIOD_SID
                                    ORDER BY ARM_ADJUSTMENT_DETAILS_SID)RN,
                                ARM_ADJUSTMENT_DETAILS_SID,
                                RS_MODEL_SID,
                                PERIOD_SID,
                                SUM_PROJECTION= SUM(VALUE)
                                                  OVER (
                                                    PARTITION BY ARM_ADJUSTMENT_DETAILS_SID, RS_MODEL_SID, PERIOD_SID),
                                -----FINAL SUM (FOR FLAT AND TIER VALUE = SUM_PROJECTION) BUT FOR LEVEL VALUE FOR SINGLE CCP AND  SUM_PROJECTION=SUM OF SINGLE CCP FOR ALL TIER 
                                NET_CALCULATED_SALES,
                                NET_UNITS,
                                RATIO=( ( CASE
                                            WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT SALES'', ''DOLLARS'' ) THEN NET_CALCULATED_SALES
                                            WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT UNITS'', ''UNITS'' ) THEN NET_UNITS
                                          END ) / NULLIF(SALES_PROJECTED_VALUE, 0) ),
                                ------EITHER NET_CALCULATED_SALES OR NET_UNITS CONFIRMATION BASED ON REBATE RANGE BASED ON    
                                SALES_PROJECTED_VALUE,
                                REBATE_STRUCTURE,
                                CALCULATION_LEVEL,
                                TIER_OPERATOR,
                                VALUE,
                                                       CALCULATION_RULE,
                                                       EVALUATION_RULE 
                         FROM   TIER_INFO),
                     CONTRACT_DETAILS
                     AS (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                RS_MODEL_SID,
                                PERIOD_SID,
                                PROJECTION_AMOUNT= AMOUNT,
                                PROJECTION_RATE= ( AMOUNT / NULLIF(NET_CALCULATED_SALES, 0) ) * 100,
                                PROJECTION_REBATE_PER_UNIT= ( AMOUNT / NULLIF(NET_UNITS, 0) ),
                                NET_CALCULATED_SALES,
                                NET_UNITS,
                                SALES_PROJECTED_VALUE,
                                                       CALCULATION_RULE,
                                                       EVALUATION_RULE 
                         FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                        RS_MODEL_SID,
                                        PERIOD_SID,
                                        AMOUNT,
                                        NET_CALCULATED_SALES,
                                        NET_UNITS,
                                        SALES_PROJECTED_VALUE,
                                                                     CALCULATION_RULE,
                                                                     EVALUATION_RULE  
                                 FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                RS_MODEL_SID,
                                                PERIOD_SID,
                                                AMOUNT= CASE
                                                          WHEN REBATE_STRUCTURE = ''TIER'' THEN SUM_PROJECTION * CASE
                                                                                                                 WHEN TIER_OPERATOR = ''%'' THEN NET_CALCULATED_SALES
                                                                                                                 WHEN TIER_OPERATOR = ''$'' THEN NET_UNITS
                                                                                                               END
                                                          WHEN REBATE_STRUCTURE = ''LEVEL'' THEN
                                                            CASE
                                                              WHEN CALCULATION_LEVEL LIKE ''%LINE%'' THEN SUM_PROJECTION
                                                              ELSE SUM_PROJECTION * RATIO
                                                            END
                                                          WHEN REBATE_STRUCTURE = ''FLAT'' THEN
                                                            CASE
                                                              WHEN CALCULATION_LEVEL LIKE ''%LINE%'' THEN SUM_PROJECTION
                                                              ELSE SUM_PROJECTION * RATIO
                                                            END
                                                        END,
                                                NET_CALCULATED_SALES,
                                                NET_UNITS,
                                                SALES_PROJECTED_VALUE,
                                                REBATE_STRUCTURE,
                                                                                  CALCULATION_RULE,
                                                                                  EVALUATION_RULE 
                                         FROM   AGGREGATION
                                         WHERE  RN = 1)B)A)
                INSERT INTO #CONTRACT_DETAILS
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             PROJECTION_AMOUNT,
                             PROJECTION_RATE,
                             PROJECTION_REBATE_PER_UNIT,
                             NET_CALCULATED_SALES,
                             NET_UNITS,
                             SALES_PROJECTED_VALUE,
                             CALCULATION_TYPE,
                                                CALCULATION_RULE,
                                                EVALUATION_RULE
                                                 )
                SELECT ARM_ADJUSTMENT_DETAILS_SID,
                       RS_MODEL_SID,
                       ISNULL(PERIOD_SID, ', @PROJECTION_START_PERIOD_SID, '),
                       ISNULL(PROJECTION_AMOUNT, 0),
                       ISNULL(PROJECTION_RATE, 0),
                       ISNULL(PROJECTION_REBATE_PER_UNIT, 0),
                       ISNULL(NET_CALCULATED_SALES, 0),
                       ISNULL(NET_UNITS, 0),
                       ISNULL(SALES_PROJECTED_VALUE, 0),
                       ''REBATE PLAN'',
                                     CALCULATION_RULE,
                                     EVALUATION_RULE 
                FROM   CONTRACT_DETAILS
 
                           TRUNCATE TABLE ', @ARM_DISTRIBUTION_FEES_RATE_TABLE)
 
                     EXEC sp_executesql @SQL
-- CONTRACT DETAILS METHODOLOGY STARTS HERE 
 
                      SET @SQL=''
                      SET @SQL=Concat('INSERT INTO ', @ARM_DISTRIBUTION_FEES_RATE_TABLE, '
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             PERIOD_SID,
                             CALCULATED_ADJUSTMENT,
                             RATE,
                             DEDUCTION_AMOUNT)
                 SELECT A.ARM_ADJUSTMENT_DETAILS_SID,', @PROJECTION_START_PERIOD_SID, ',
                       ISNULL((( PROJECTION_RATE/100.0) * NET_CALCULATED_SALES ),0) AS CALCULATED_ADJUSTMENT,
                       ISNULL(PROJECTION_RATE,0),
                       ISNULL(PROJECTION_AMOUNT,0)
                FROM   #ARM_MASTER_MASTER A
                       LEFT JOIN #CONTRACT_DETAILS B
                              ON A.ARM_ADJUSTMENT_DETAILS_SID = B.ARM_ADJUSTMENT_DETAILS_SID ')
 
                      EXEC sp_executesql @SQL
 
                      SET @SQL=''
                      SET @SQL=Concat('
              INSERT INTO #TEMP_OUTPUT
                     (ARM_ADJUSTMENT_DETAILS_SID ,
                           CURRENT_BALANCE   ,
                           CALCULATED_ADJUSTMENT ,
                           PERIOD_SID                 ,
                           VARIANCE     )
                           SELECT SADFR.ARM_ADJUSTMENT_DETAILS_SID,
                          ISNULL(AC.CURRENT_BALANCE,0),
                           SADFR.CALCULATED_ADJUSTMENT
                           ,SADFR.PERIOD_SID
                           ,ISNULL(AC.CURRENT_BALANCE,0)  - ISNULL(SADFR.CALCULATED_ADJUSTMENT,0)
                           FROM   ', @ARM_DISTRIBUTION_FEES_RATE_TABLE, ' SADFR
                                  LEFT JOIN #ARM_MASTER ARM
                                     ON ARM.ARM_ADJUSTMENT_DETAILS_SID=SADFR.ARM_ADJUSTMENT_DETAILS_SID
                       LEFT JOIN #APPROVE_CCP AC
                               ON AC.CCP_DETAILS_SID = ARM.CCP_DETAILS_SID
                                                   AND AC.RS_MODEL_SID = ARM.RS_MODEL_SID
                                                   AND SADFR.PERIOD_SID=AC.PERIOD_SID
 
                                  INSERT INTO #TEMP_AMOUNT
                                  (ARM_ADJUSTMENT_DETAILS_SID,
                                   PERIOD_SID,
                                   CURRENT_BALANCE,
                                   INDICATOR)
                                  SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                            PERIOD_SID,
                                            SUM(VARIANCE) AS CURRENT_BALANCE,
                                            CASE
                                                 WHEN SUM(VARIANCE) > 0 THEN 0
                                                 WHEN SUM(VARIANCE) < 0 THEN 1
                                                 ELSE NULL
                                            END                                 AS INDICATOR
                                  FROM   #TEMP_OUTPUT
                                  GROUP BY ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID
 
 
                                  INSERT INTO #CURRENT_LIABILITY
                           SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
                                     ISNULL(A1.PERIOD_SID, A2.PERIOD_SID),
                                     SUM(ABS(A1.CURRENT_BALANCE))                                                     AS CREDIT_AMOUNT,
                                     SUM(ABS(A2.CURRENT_BALANCE))                                                     AS DEBIT_AMOUNT,
                                     ISNULL(SUM(ABS(A1.CURRENT_BALANCE)), 0) - ISNULL(SUM(ABS(A2.CURRENT_BALANCE)), 0) AS CURRENT_AMOUNT
                           FROM   #TEMP_AMOUNT TAM
                                     JOIN ARM_ADJ_RES_CCP AAC
                                          ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
                                     INNER JOIN HELPER_TABLE HT
                                                   ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
                                                         AND HT.DESCRIPTION = ''LIABILITY''
                                                         AND AAC.ADJUSTMENT_TYPE = ',@ADJUSTMENT_TYPE,'
                                     LEFT JOIN #TEMP_AMOUNT A1
                                                  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
                                                        AND AAC.CREDIT = A1.INDICATOR
                                     LEFT JOIN #TEMP_AMOUNT A2
                                                  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
                                                        AND AAC.DEBIT = A2.INDICATOR
                           GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
                                           ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
 
                                            INSERT INTO #CURRENT_EXPENSE
                     SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
                              ISNULL(A1.PERIOD_SID, A2.PERIOD_SID),
                              SUM(ABS(A1.CURRENT_BALANCE))                                                     AS CREDIT_AMOUNT,
                              SUM(ABS(A2.CURRENT_BALANCE))                                                     AS DEBIT_AMOUNT,
                              ISNULL(SUM(ABS(A1.CURRENT_BALANCE)), 0) - ISNULL(SUM(ABS(A2.CURRENT_BALANCE)), 0) AS CURRENT_AMOUNT
                     FROM   #TEMP_AMOUNT TAM
                              JOIN ARM_ADJ_RES_CCP AAC
                                   ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
                              INNER JOIN HELPER_TABLE HT
                                            ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
                                                  AND HT.DESCRIPTION = ''EXPENSE''
                                                  AND AAC.ADJUSTMENT_TYPE = ',@ADJUSTMENT_TYPE,'
                              LEFT JOIN #TEMP_AMOUNT A1
                                           ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
                                                 AND AAC.CREDIT = A1.INDICATOR
                              LEFT JOIN #TEMP_AMOUNT A2
                                           ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
                                                 AND AAC.DEBIT = A2.INDICATOR
                     GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
                                    ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
 
                                     UPDATE SA
                                   SET SA.CURRENT_BALANCE = ISNULL(A.CURRENT_BALANCE,0),
                                          SA.VARIANCE = (ISNULL(A.CURRENT_BALANCE,0)  - ISNULL(A.CALCULATED_ADJUSTMENT,0) ),
                         SA.ADJUSTMENT_RATIO = ISNULL(( A.CALCULATED_ADJUSTMENT / NULLIF(A.CURRENT_BALANCE, 0) ),0),
                                           LIABILITY_AMOUNT=LIA.CURRENT_AMOUNT  ,
                                       EXPENSE_AMOUNT=CEX.CURRENT_AMOUNT   FROM
                                          ', @ARM_DISTRIBUTION_FEES_RATE_TABLE, ' SA JOIN
                                  (SELECT ARM_ADJUSTMENT_DETAILS_SID
                                  ,SUM(CURRENT_BALANCE) AS CURRENT_BALANCE
                                  ,SUM(CALCULATED_ADJUSTMENT) AS CALCULATED_ADJUSTMENT FROM 
                                   #TEMP_OUTPUT GROUP BY ARM_ADJUSTMENT_DETAILS_SID)A ON  A.ARM_ADJUSTMENT_DETAILS_SID=SA.ARM_ADJUSTMENT_DETAILS_SID
                                     LEFT JOIN #CURRENT_LIABILITY LIA
                                                  ON A.ARM_ADJUSTMENT_DETAILS_SID = LIA.ARM_ADJUSTMENT_DETAILS_SID
                                     LEFT JOIN #CURRENT_EXPENSE CEX
                                                  ON A.ARM_ADJUSTMENT_DETAILS_SID = CEX.ARM_ADJUSTMENT_DETAILS_SID'
                               --    UPDATE SA
                               --SET SA.CURRENT_BALANCE = ISNULL(A.CURRENT_BALANCE,0),
                               -- SA.VARIANCE = (ISNULL(A.CURRENT_BALANCE,0)  - ISNULL(A.CALCULATED_ADJUSTMENT,0) ),
                               --                   SA.ADJUSTMENT_RATIO = ISNULL(( A.CALCULATED_ADJUSTMENT / NULLIF(A.CURRENT_BALANCE, 0) ),0) FROM
                               -- ',@ARM_DISTRIBUTION_FEES_RATE_TABLE,' SA JOIN
                               --          (SELECT SADFR.ARM_ADJUSTMENT_DETAILS_SID,SUM(AC.CURRENT_BALANCE) AS CURRENT_BALANCE,SUM(SADFR.CALCULATED_ADJUSTMENT) AS CALCULATED_ADJUSTMENT FROM   ',@ARM_DISTRIBUTION_FEES_RATE_TABLE,' SADFR
                               --     LEFT JOIN #ARM_MASTER ARM ON ARM.ARM_ADJUSTMENT_DETAILS_SID=SADFR.ARM_ADJUSTMENT_DETAILS_SID
                               --                 LEFT JOIN #APPROVE_CCP AC
                               --                         ON AC.CCP_DETAILS_SID = ARM.CCP_DETAILS_SID
                               --    AND AC.RS_MODEL_SID = ARM.RS_MODEL_SID
                               --    AND SADFR.PERIOD_SID=AC.PERIOD_SID
                               --          GROUP BY SADFR.ARM_ADJUSTMENT_DETAILS_SID)A ON A.ARM_ADJUSTMENT_DETAILS_SID=SA.ARM_ADJUSTMENT_DETAILS_SID'
                               )
 
                      EXEC sp_executesql @SQL
 
		
 -- RULES IMPLEMENTATION STARTs here
 -- EXFACTORY CALCULATION Starts here
                      IF EXISTS (SELECT 1
                                 FROM   #REBATE_INFO
                                 WHERE  EVALUATION_RULE IS NOT NULL
                                         OR CALCULATION_RULE IS NOT NULL
                                         OR RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                                         OR RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL)
                        BEGIN
                            SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME,
                                         @FORECAST_VERSION = FT.[VERSION]
                            FROM   FILE_MANAGEMENT FT
                                   INNER JOIN HELPER_TABLE HT
                                           ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
                            WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                                     AND FT.FROM_PERIOD IS NOT NULL )
                                   AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                                          OR FT.TO_PERIOD IS NULL )
                                   AND HT.LIST_NAME = 'FILE_TYPE'
                                   AND HT.DESCRIPTION = 'EX-FACTORY SALES'
                                   AND FT.BUSINESS_UNIT = @BUSINESS_UNIT
                                   AND FT.COMPANY = @GL_COMPANY_ID
                            ORDER  BY FT.FROM_PERIOD DESC
 
                            IF Object_id('TEMPDB.DBO.#GTS_WAC', 'U') IS NOT NULL
                              DROP TABLE #GTS_WAC;
 
                            CREATE TABLE #GTS_WAC
                              (
                                 ITEM_MASTER_SID    INT,
                                 PERIOD_SID         INT,
                                 ACTUAL_GTS_SALES   NUMERIC(38, 15),
                                 ACTUAL_GTS_UNITS   NUMERIC(38, 15),
                                 FORECAST_GTS_SALES NUMERIC(38, 15),
                                 FORECAST_GTS_UNITS NUMERIC(38, 15),
                                 PRIMARY KEY(ITEM_MASTER_SID, PERIOD_SID)
                              )
 
                            INSERT INTO #GTS_WAC
                                        (ITEM_MASTER_SID,
                                         PERIOD_SID,
                                         ACTUAL_GTS_SALES,
                                         ACTUAL_GTS_UNITS,
                                         FORECAST_GTS_SALES,
                                         FORECAST_GTS_UNITS)
                            SELECT GTS.ITEM_MASTER_SID,
                                   GTS.PERIOD_SID,
                                   ACTUAL_GTS_SALES,
                                   ACTUAL_GTS_UNITS,
                                   FORECAST_GTS_SALES,
                                   FORECAST_GTS_UNITS
                            FROM   DBO.Udf_gts_wac(@ITEM_UDT, @ACTUAL_START_PERIOD_SID - 3, @PROJECTION_END_PERIOD_SID, @FORECAST_NAME, @FORECAST_VERSION) GTS
 
                            --------------------------------------------------------------------------------------------DEMAND PRICE CALCULATION
                            IF Object_id('TEMPDB.DBO.#DEMAND_WAC', 'U') IS NOT NULL
                              DROP TABLE #DEMAND_WAC
 
                            SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME,
                                         @FORECAST_VERSION = FT.[VERSION]
                            FROM   FILE_MANAGEMENT FT
                                   JOIN HELPER_TABLE HT
                                     ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
                            WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                                     AND FT.FROM_PERIOD IS NOT NULL )
                                   AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                                          OR FT.TO_PERIOD IS NULL )
                                   AND HT.DESCRIPTION = 'DEMAND'
                                   AND FT.BUSINESS_UNIT = @BUSINESS_UNIT
                                   AND FT.COMPANY = @GL_COMPANY_ID
                            ORDER  BY FT.FROM_PERIOD DESC
 
                            IF Object_id('TEMPDB.DBO.#DEMAND_WAC', 'U') IS NOT NULL
                              DROP TABLE #DEMAND_WAC;
 
                            CREATE TABLE #DEMAND_WAC
                              (
                                 ITEM_MASTER_SID  INT,
                                 PERIOD_SID       INT,
                                 ACT_GROSS_AMOUNT NUMERIC(38, 15),
                                 ACT_GROSS_UNITS  NUMERIC(38, 15),
                                 FOR_GROSS_AMOUNT NUMERIC(38, 15),
                                 FOR_GROSS_UNITS  NUMERIC(38, 15)
                                 PRIMARY KEY(ITEM_MASTER_SID, PERIOD_SID)
                              )
 
                            INSERT INTO #DEMAND_WAC
                                        (ITEM_MASTER_SID,
                                         PERIOD_SID,
                                         ACT_GROSS_AMOUNT,
                                        ACT_GROSS_UNITS,
                                         FOR_GROSS_AMOUNT,
                                         FOR_GROSS_UNITS)
                            SELECT D.ITEM_MASTER_SID,
                                   D.PERIOD_SID,
                                   ACT_GROSS_AMOUNT,
                                   ACT_GROSS_UNITS,
                                   FOR_GROSS_AMOUNT,
                                   FOR_GROSS_UNITS
                            FROM   [DBO].[Udf_demand_wac](@ITEM_UDT, @ACTUAL_START_PERIOD_SID - 3, @PROJECTION_END_PERIOD_SID, @FORECAST_NAME, @FORECAST_VERSION) D
 
                            ---------------------------------------------------------------------------------------------------- INVENTORY PRICE CALCULATION  
                            SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME,
                                         @FORECAST_VERSION = FT.[VERSION]
                            FROM   FILE_MANAGEMENT FT
                                   JOIN HELPER_TABLE HT
                                     ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
                            WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                                     AND FT.FROM_PERIOD IS NOT NULL )
                                   AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                                          OR FT.TO_PERIOD IS NULL )
                                   AND LIST_NAME = 'FILE_TYPE'
                                   AND HT.DESCRIPTION = 'INVENTORY WITHDRAWAL - FORECAST SUMMARY'
                                   AND FT.BUSINESS_UNIT = @BUSINESS_UNIT
                                   AND FT.COMPANY = @GL_COMPANY_ID
                            ORDER  BY FT.FROM_PERIOD DESC
 
                            IF Object_id('TEMPDB.DBO.#INVENTORY_WAC', 'U') IS NOT NULL
                              DROP TABLE #INVENTORY_WAC
 
                            CREATE TABLE #INVENTORY_WAC
                              (
                                 ITEM_MASTER_SID      INT,
                                 PERIOD_SID           INT,
                                 ACT_AMOUNT_WITHDRAWN NUMERIC(38, 15),
                                 ACT_UNITS_WITHDRAWN  NUMERIC(38, 15),
                                 FOR_AMOUNT_WITHDRAWN NUMERIC(38, 15),
                                 FOR_UNITS_WITHDRAWN  NUMERIC(38, 15)
                                 PRIMARY KEY(ITEM_MASTER_SID, PERIOD_SID)
                              )
 
                            INSERT INTO #INVENTORY_WAC
                                        (ITEM_MASTER_SID,
                                         PERIOD_SID,
                                         ACT_AMOUNT_WITHDRAWN,
                                         ACT_UNITS_WITHDRAWN,
                                         FOR_AMOUNT_WITHDRAWN,
                                         FOR_UNITS_WITHDRAWN)
                            SELECT D.ITEM_MASTER_SID,
                                   D.PERIOD_SID,
                                   ACT_AMOUNT_WITHDRAWN,
                                   ACT_UNITS_WITHDRAWN,
                                   FOR_AMOUNT_WITHDRAWN,
                                   FOR_UNITS_WITHDRAWN
                            FROM   [DBO].[Udf_inventory_wac](@ITEM_UDT, @ACTUAL_START_PERIOD_SID - 3, @PROJECTION_END_PERIOD_SID, @FORECAST_NAME, @FORECAST_VERSION) D
                        END
 
                      ---------------------------------------------------------------------------------------------------------------------------------------- EVALUATION RULE STARTED
                      IF EXISTS (SELECT 1
                                 FROM   #REBATE_INFO
                                 WHERE  EVALUATION_RULE IS NOT NULL
                                        AND EVALUATION_RULE <> 0)
                        BEGIN
                            IF Object_id('TEMPDB..#EVALUATION_RULE') IS NOT NULL
                              TRUNCATE TABLE #EVALUATION_RULE
                            ELSE
                              CREATE TABLE #EVALUATION_RULE
                                (
                                   ARM_ADJUSTMENT_DETAILS_SID INT,
                                   RS_MODEL_SID               INT,
                                   PERIOD_SID                 INT,
                                   CDR_MODEL_SID              INT,
                                   EVALUATION_RULE            INT,
                                   PROJECTION_AMOUNT          NUMERIC(22, 6),
                                   NET_CALCULATED_SALES       NUMERIC(22, 6),
                                   NET_UNITS                  NUMERIC(22, 6),
                                  REBATE_STRUCTURE           VARCHAR(20),
                                   PRICE_GROUP_TYPE           VARCHAR(50),
                                   KEYWORD                    VARCHAR(50),
                                   OPERATOR                   VARCHAR(50),
                                   COMPARISON                 VARCHAR(50),
                                   LOGICAL_OPERATOR           VARCHAR(50),
                                   ITEM_GROUP_MS_ASSOCIATION  VARCHAR(50),
                                   LINE_TYPE                  VARCHAR(50),
                                   VALUE                      NUMERIC(22, 6),
                                   RULE_RESULT                VARCHAR(10)
                                )
 
                            INSERT INTO #EVALUATION_RULE
                                        (ARM_ADJUSTMENT_DETAILS_SID,
                                         RS_MODEL_SID,
                                         PERIOD_SID,
                                         CDR_MODEL_SID,
                                         EVALUATION_RULE,
                                         PROJECTION_AMOUNT,
                                         NET_CALCULATED_SALES,
                                         NET_UNITS,
                                         REBATE_STRUCTURE,
                                         PRICE_GROUP_TYPE,
                                         KEYWORD,
                                         OPERATOR,
                                         COMPARISON,
                                         LOGICAL_OPERATOR,
                                         ITEM_GROUP_MS_ASSOCIATION,
                                         LINE_TYPE,
                                         VALUE)
                            SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                   RS_MODEL_SID,
                                   PERIOD_SID,
                                   CDR_MODEL_SID,
                                   EVALUATION_RULE,
                                   PROJECTION_AMOUNT,
                                   NET_CALCULATED_SALES,
                                   NET_UNITS,
                                   REBATE_STRUCTURE,
                                   PRICE_GROUP_TYPE,
                                   KEYWORD,
                                   OPERATOR,
                                   COMPARISON,
                                   LOGICAL_OPERATOR,
                                   ITEM_GROUP_MS_ASSOCIATION,
                                   LINE_TYPE,
                                   COALESCE(CASE COMPARISON
                                                    WHEN 'GROSS TRADE SALES' THEN Isnull(FORECAST_GTS_SALES, 0)
                                                    WHEN 'DEMAND' THEN Isnull(FOR_GROSS_AMOUNT, 0)
                                                    WHEN 'INVENTORY WITHDRAWALS' THEN Isnull(FOR_AMOUNT_WITHDRAWN, 0)
                                                    WHEN 'CONTRACT SALES' THEN Isnull(NET_CALCULATED_SALES, 0)
                                                  END * ( VALUE / 100 ), VALUE) AS VALUE
                            FROM   (SELECT Row_number()
                                             OVER (
                                               PARTITION BY CD.ARM_ADJUSTMENT_DETAILS_SID, CD.RS_MODEL_SID, CD.PERIOD_SID, D.CDR_MODEL_SID, CD.EVALUATION_RULE
                                               ORDER BY CD.PERIOD_SID)RN,
                                           CD.ARM_ADJUSTMENT_DETAILS_SID,
                                           CD.RS_MODEL_SID,
                                           CD.PERIOD_SID,
                                           D.CDR_MODEL_SID,
                                           CD.EVALUATION_RULE,
                                           PROJECTION_AMOUNT,
                                           NET_CALCULATED_SALES,
                                           NET_UNITS,
                                           R.REBATE_STRUCTURE,
                                           CD.PRICE_GROUP_TYPE,
                                           H.DESCRIPTION              KEYWORD,
                                           H1.DESCRIPTION             OPERATOR,
                                           H2.DESCRIPTION             COMPARISON,
                                           H3.DESCRIPTION             LOGICAL_OPERATOR,
                                           H4.DESCRIPTION             ITEM_GROUP_MS_ASSOCIATION,
                                           H5.DESCRIPTION             LINE_TYPE,
                                           GW.FORECAST_GTS_SALES,
                                           DW.FOR_GROSS_AMOUNT,
                                           IW.FOR_AMOUNT_WITHDRAWN,
                                           VALUE
                                    FROM   #CONTRACT_DETAILS CD
                                           INNER JOIN CDR_DETAILS D
                                                   ON CD.EVALUATION_RULE = D.CDR_MODEL_SID
                                           INNER JOIN #REBATE_INFO R
                                                   ON R.ARM_ADJUSTMENT_DETAILS_SID = CD.ARM_ADJUSTMENT_DETAILS_SID
                                                      AND R.RS_MODEL_SID = CD.RS_MODEL_SID
                                                      AND R.EVALUATION_RULE = CD.EVALUATION_RULE
                                           INNER JOIN #GTS_WAC GW
                                                   ON GW.ITEM_MASTER_SID = R.ITEM_MASTER_SID
                                                      AND GW.PERIOD_SID = CD.PERIOD_SID
                                           INNER JOIN #DEMAND_WAC DW
                                                   ON DW.ITEM_MASTER_SID = R.ITEM_MASTER_SID
                                                      AND DW.PERIOD_SID = CD.PERIOD_SID
                                           INNER JOIN #INVENTORY_WAC IW
                                                   ON IW.ITEM_MASTER_SID = R.ITEM_MASTER_SID
                                                      AND IW.PERIOD_SID = CD.PERIOD_SID
                                           LEFT JOIN HELPER_TABLE H
                                                  ON D.KEYWORD = H.HELPER_TABLE_SID
                                                     AND H.LIST_NAME = 'KEYWORD'
                                           LEFT JOIN HELPER_TABLE H1
                                                  ON D.OPERATOR = H1.HELPER_TABLE_SID
                                                     AND H1.LIST_NAME = 'OPERATOR'
                                           LEFT JOIN HELPER_TABLE H2
                                                  ON D.COMPARISON = H2.HELPER_TABLE_SID
                                                     AND H2.LIST_NAME = 'COMPARISON'
                                           LEFT JOIN HELPER_TABLE H3
                                                  ON D.LOGICAL_OPERATOR = H3.HELPER_TABLE_SID
                                                     AND H3.LIST_NAME = 'LOGICAL_OPERATOR'
                                           LEFT JOIN HELPER_TABLE H4
                                                  ON D.ITEM_GROUP_MS_ASSOCIATION = H4.HELPER_TABLE_SID
                                                     AND H4.LIST_NAME = 'ITEM_GROUP_MS_ASSOCIATION'
                                           LEFT JOIN HELPER_TABLE H5
                                                  ON D.LINE_TYPE = H5.HELPER_TABLE_SID
                                                     AND H5.LIST_NAME = 'LINE_TYPE'
                                    WHERE  H.DESCRIPTION = 'CONTRACT SALES')A
                            WHERE  RN = 1
 
                            ---------------------------------------------------------------------------- 
                            UPDATE RS
                            SET    RULE_RESULT = CASE
                                                   WHEN NET_CALCULATED_SALES = RS_PROJECTION_AMOUNT THEN 'PASS'
                                                   WHEN VALUE = RS_PROJECTION_AMOUNT THEN 'FAIL'
                                                 END
                            FROM   #EVALUATION_RULE RS
                                   CROSS APPLY ( VALUES ('<',
                                               Iif(Isnull(NET_CALCULATED_SALES, 0) < VALUE, NET_CALCULATED_SALES, VALUE)),
                                                        ('<=',
                                               Iif(Isnull(NET_CALCULATED_SALES, 0) <= VALUE, NET_CALCULATED_SALES, VALUE) ),
                                                        ('=',
                                               Iif(Isnull(NET_CALCULATED_SALES, 0) = VALUE, NET_CALCULATED_SALES, VALUE)),
                                                        ('>',
                                               Iif(Isnull(NET_CALCULATED_SALES, 0) > VALUE, NET_CALCULATED_SALES, VALUE)),
                                                        ('>=',
                                               Iif(Isnull(NET_CALCULATED_SALES, 0) >= VALUE, NET_CALCULATED_SALES, VALUE)) ) AS CS(OPERATOR, RS_PROJECTION_AMOUNT)
                            WHERE  RS.OPERATOR = CS.OPERATOR
 
                            UPDATE SNDP
                            SET    SNDP.PROJECTION_AMOUNT = CD.PROJECTION_AMOUNT,
                                   SNDP.PROJECTION_RATE = CD.PROJECTION_RATE
                            FROM   #CONTRACT_DETAILS SNDP
                                   INNER JOIN (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                      RS_MODEL_SID,
                                                      PERIOD_SID,
                                                      AMOUNT AS PROJECTION_AMOUNT,
                                                      ( AMOUNT / NULLIF(NET_CALCULATED_SALES, 0) ) * 100 AS PROJECTION_RATE
                                               FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                              RS_MODEL_SID,
                                                              PERIOD_SID,
                                                              CASE
                                                                        WHEN RULE_RESULT = 'PASS' THEN PROJECTION_AMOUNT
                                                                        WHEN RULE_RESULT = 'FAIL' THEN 0
                                                                      END AS AMOUNT,
                                                              REBATE_STRUCTURE,
                                                              NET_CALCULATED_SALES
                                                       FROM   #EVALUATION_RULE)A) CD
                                           ON SNDP.ARM_ADJUSTMENT_DETAILS_SID = CD.ARM_ADJUSTMENT_DETAILS_SID
                                              AND SNDP.RS_MODEL_SID = CD.RS_MODEL_SID
                                              AND SNDP.PERIOD_SID = CD.PERIOD_SID
                        END
 
                      ----------------------------------------------------------------------------------------------------------------------------------------------  EVALUATION RULE ENDS
                      ----------------------------------------------------------------------------------------------------------------------------------------- CALCULATION RULE STARTED
                      IF EXISTS (SELECT 1
                                 FROM   #REBATE_INFO
                                 WHERE  CALCULATION_RULE IS NOT NULL
                                        AND CALCULATION_RULE <> 0)
                        BEGIN
                            IF Object_id('TEMPDB..#CALCULATION_RULE') IS NOT NULL
                              TRUNCATE TABLE #CALCULATION_RULE
                            ELSE
                              CREATE TABLE #CALCULATION_RULE
                                (
                                   ARM_ADJUSTMENT_DETAILS_SID INT,
                                   RS_MODEL_SID               INT,
                                   PERIOD_SID                 INT,
                                   CDR_MODEL_SID              INT,
                                   CALCULATION_RULE           INT,
                                   PROJECTION_AMOUNT          NUMERIC(22, 6),
                                   NET_CALCULATED_SALES       NUMERIC(22, 6),
                                   NET_UNITS                  NUMERIC(22, 6),
                                   PRICE_GROUP_TYPE           VARCHAR(50),
                                   KEYWORD                    VARCHAR(50),
                                   OPERATOR                   VARCHAR(50),
                                   COMPARISON                 VARCHAR(50),
                                   LOGICAL_OPERATOR           VARCHAR(50),
                                   ITEM_GROUP_MS_ASSOCIATION  VARCHAR(50),
                                   LINE_TYPE                  VARCHAR(50),
                                   VALUE                      NUMERIC(22, 6),
                                   REBATE_STRUCTURE           VARCHAR(20)
                                )
 
                            INSERT INTO #CALCULATION_RULE
                                        (ARM_ADJUSTMENT_DETAILS_SID,
                                         RS_MODEL_SID,
                                         PERIOD_SID,
                                         CDR_MODEL_SID,
                                         CALCULATION_RULE,
                                         PROJECTION_AMOUNT,
                                         NET_CALCULATED_SALES,
                                         NET_UNITS,
                                         PRICE_GROUP_TYPE,
                                         KEYWORD,
                                         OPERATOR,
                                         COMPARISON,
                                         LOGICAL_OPERATOR,
                                         ITEM_GROUP_MS_ASSOCIATION,
                                         LINE_TYPE,
                                         VALUE,
                                         REBATE_STRUCTURE)
                            SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                   RS_MODEL_SID,
                                   PERIOD_SID,
                                   CDR_MODEL_SID,
                                   CALCULATION_RULE,
                                   PROJECTION_AMOUNT,
                                   NET_CALCULATED_SALES,
                                   NET_UNITS,
                                   PRICE_GROUP_TYPE,
                                   KEYWORD,
                                   OPERATOR,
                                   COMPARISON,
                                   LOGICAL_OPERATOR,
                                   ITEM_GROUP_MS_ASSOCIATION,
                                   LINE_TYPE,
                                   COALESCE(CASE COMPARISON
                                                    WHEN 'GROSS TRADE SALES' THEN Isnull(FORECAST_GTS_SALES, 0)
                                                    WHEN 'DEMAND' THEN Isnull(FOR_GROSS_AMOUNT, 0)
                                                    WHEN 'INVENTORY WITHDRAWALS' THEN Isnull(FOR_AMOUNT_WITHDRAWN, 0)
                                                    WHEN 'CONTRACT SALES' THEN Isnull(NET_CALCULATED_SALES, 0)
                                                  END * ( VALUE / 100 ), VALUE) AS VALUE,
                                   REBATE_STRUCTURE
                            FROM   (SELECT Row_number()
                                             OVER (
                                               PARTITION BY CD.ARM_ADJUSTMENT_DETAILS_SID, CD.RS_MODEL_SID, CD.PERIOD_SID, D.CDR_MODEL_SID, CD.CALCULATION_RULE
                                               ORDER BY CD.PERIOD_SID)RN,
                                           CD.ARM_ADJUSTMENT_DETAILS_SID,
                                           CD.RS_MODEL_SID,
                                           CD.PERIOD_SID,
                                           D.CDR_MODEL_SID,
                                           CD.CALCULATION_RULE,
                                           PROJECTION_AMOUNT,
                                           NET_CALCULATED_SALES,
                                           NET_UNITS,
                                           CD.PRICE_GROUP_TYPE,
                                           H.DESCRIPTION              KEYWORD,
                                           H1.DESCRIPTION             OPERATOR,
                                           H2.DESCRIPTION             COMPARISON,
                                           H3.DESCRIPTION             LOGICAL_OPERATOR,
                                           H4.DESCRIPTION             ITEM_GROUP_MS_ASSOCIATION,
                                           H5.DESCRIPTION             LINE_TYPE,
                                           GW.FORECAST_GTS_SALES,
                                           DW.FOR_GROSS_AMOUNT,
                                           IW.FOR_AMOUNT_WITHDRAWN,
                                           VALUE,
                                           R.REBATE_STRUCTURE
                                    FROM   #CONTRACT_DETAILS CD
                                           INNER JOIN CDR_DETAILS D
                                                   ON CD.CALCULATION_RULE = D.CDR_MODEL_SID
                                           INNER JOIN #REBATE_INFO R
                                                   ON R.ARM_ADJUSTMENT_DETAILS_SID = CD.ARM_ADJUSTMENT_DETAILS_SID
                                                      AND R.RS_MODEL_SID = CD.RS_MODEL_SID
                                                      AND R.CALCULATION_RULE = CD.CALCULATION_RULE
                                           INNER JOIN #GTS_WAC GW
                                                   ON GW.ITEM_MASTER_SID = R.ITEM_MASTER_SID
                                                      AND GW.PERIOD_SID = CD.PERIOD_SID
                                           INNER JOIN #DEMAND_WAC DW
                                                   ON DW.ITEM_MASTER_SID = R.ITEM_MASTER_SID
                                                      AND DW.PERIOD_SID = CD.PERIOD_SID
                                           INNER JOIN #INVENTORY_WAC IW
                                                   ON IW.ITEM_MASTER_SID = R.ITEM_MASTER_SID
                                                      AND IW.PERIOD_SID = CD.PERIOD_SID
                                           LEFT JOIN HELPER_TABLE H
                                                  ON D.KEYWORD = H.HELPER_TABLE_SID
                                                     AND H.LIST_NAME = 'KEYWORD'
                                           LEFT JOIN HELPER_TABLE H1
                                                  ON D.OPERATOR = H1.HELPER_TABLE_SID
                                                     AND H1.LIST_NAME = 'OPERATOR'
                                           LEFT JOIN HELPER_TABLE H2
                                                  ON D.COMPARISON = H2.HELPER_TABLE_SID
                                                     AND H2.LIST_NAME = 'COMPARISON'
                                           LEFT JOIN HELPER_TABLE H3
                                                  ON D.LOGICAL_OPERATOR = H3.HELPER_TABLE_SID
                                                     AND H3.LIST_NAME = 'LOGICAL_OPERATOR'
                                           LEFT JOIN HELPER_TABLE H4
                                                  ON D.ITEM_GROUP_MS_ASSOCIATION = H4.HELPER_TABLE_SID
                                                    AND H4.LIST_NAME = 'ITEM_GROUP_MS_ASSOCIATION'
                                           LEFT JOIN HELPER_TABLE H5
                                                  ON D.LINE_TYPE = H5.HELPER_TABLE_SID
                                                    AND H5.LIST_NAME = 'LINE_TYPE'
                                    WHERE  H.DESCRIPTION = 'CONTRACT DEDUCTIONS')A
                            WHERE  RN = 1

                            UPDATE RS
                            SET    PROJECTION_AMOUNT = RS_PROJECTION_AMOUNT
                            FROM   #CALCULATION_RULE RS
                                   CROSS APPLY ( VALUES ('<',
                                               Iif(Isnull(PROJECTION_AMOUNT, 0) < VALUE, PROJECTION_AMOUNT, VALUE)),
                                                        ('<=',
                                               Iif(Isnull(PROJECTION_AMOUNT, 0) <= VALUE, PROJECTION_AMOUNT, VALUE) ),
                                                        ('=',
                                               Iif(Isnull(PROJECTION_AMOUNT, 0) = VALUE, PROJECTION_AMOUNT, VALUE)),
                                                        ('>',
                                               Iif(Isnull(PROJECTION_AMOUNT, 0) > VALUE, PROJECTION_AMOUNT, VALUE)),
                                                        ('>=',
                                               Iif(Isnull(PROJECTION_AMOUNT, 0) >= VALUE, PROJECTION_AMOUNT, VALUE)) ) AS CS(OPERATOR, RS_PROJECTION_AMOUNT)
                            WHERE  RS.OPERATOR = CS.OPERATOR
 
                            IF EXISTS (SELECT 1
                                       FROM   #REBATE_INFO
                                       WHERE  CALCULATION_TYPE = 'REBATE PLAN')
                              BEGIN
                                  UPDATE SNDP
                                  SET    SNDP.PROJECTION_AMOUNT = CD.PROJECTION_AMOUNT,
                                         SNDP.PROJECTION_RATE = CD.PROJECTION_RATE
                                  FROM   #CONTRACT_DETAILS SNDP
                                         INNER JOIN (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                            RS_MODEL_SID,
                                                            PERIOD_SID,
                                                            PROJECTION_AMOUNT,
                                                            ( PROJECTION_AMOUNT / NULLIF(NET_CALCULATED_SALES, 0) ) * 100 AS PROJECTION_RATE
                                                     FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                                    RS_MODEL_SID,
                                                                    PERIOD_SID,
                                                                    PROJECTION_AMOUNT,
                                                                    NET_CALCULATED_SALES
                                                             FROM   #CALCULATION_RULE)A) CD
                                                 ON SNDP.ARM_ADJUSTMENT_DETAILS_SID = CD.ARM_ADJUSTMENT_DETAILS_SID
                                                    AND SNDP.RS_MODEL_SID = CD.RS_MODEL_SID
                                                    AND SNDP.PERIOD_SID = CD.PERIOD_SID
                              END
                        END
 
 -- CALCULATION RULE ENDS here
 -- NET SALES RULE Starts here
                      IF EXISTS (SELECT 1
                                 FROM   #REBATE_INFO
                                 WHERE  ( RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                                          AND RS_NET_SALES_FORMULA_MASTER_SID <> 0 )
                                        AND ( RP_NET_SALES_FORMULA_MASTER_SID IS NULL
                                              AND RP_NET_SALES_FORMULA_MASTER_SID = 0 ))
                        BEGIN
                            IF Object_id('TEMPDB..#RS_NET_SALES') IS NOT NULL
                              DROP TABLE #RS_NET_SALES
 
                            SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                   RS_MODEL_SID,
                                   PERIOD_SID,
                                   ( (PROJECTION_RATE/100.0) * NETTED_SALES ) AS CALCULATED_ADJUSTMENT,
                                   PROJECTION_RATE,
                                   PROJECTION_AMOUNT
                            INTO   #RS_NET_SALES
                            FROM   (SELECT T.ARM_ADJUSTMENT_DETAILS_SID,
                                           T.RS_MODEL_SID,
                                           T.PERIOD_SID,
                                           AMOUNT AS PROJECTION_AMOUNT, 
                                           Isnull(( AMOUNT / NULLIF(NET_CALCULATED_SALES, 0) ) * 100, 0) AS PROJECTION_RATE,
                                           Isnull(( AMOUNT / NULLIF(NET_UNITS, 0) ), 0) AS PROJECTION_REBATE_PER_UNIT,
                                           Isnull(( SALES_PROJECTED_VALUE - CURRENT_BALANCE ), 0) AS NETTED_SALES
                                    FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                   RS_MODEL_SID,
                                                   PERIOD_SID,
                                                   Isnull(( CASE
                                                                      WHEN REBATE_STRUCTURE = 'TIER' THEN SUM_PROJECTION * CASE
                                                                                                                             WHEN TIER_OPERATOR = '%' THEN NET_CALCULATED_SALES
                                                                                                                             WHEN TIER_OPERATOR = '$' THEN NET_UNITS
                                                                                                                           END
                                                                      WHEN REBATE_STRUCTURE = 'LEVEL' THEN
                                                                        CASE
                                                                          WHEN CALCULATION_LEVEL LIKE '%LINE%' THEN SUM_PROJECTION
                                                                          ELSE SUM_PROJECTION * RATIO
                                                                        END
                                                                      WHEN REBATE_STRUCTURE = 'FLAT' THEN
                                                                        CASE
                                                                          WHEN CALCULATION_LEVEL LIKE '%LINE%' THEN SUM_PROJECTION
                                                                          ELSE SUM_PROJECTION * RATIO
                                                                        END
                                                                    END ), 0) AS AMOUNT,
                                                   NET_CALCULATED_SALES,
                                                   NET_UNITS,
                                                   REBATE_RANGE_BASED_ON,
                                                   SALES_PROJECTED_VALUE
                                            FROM   (SELECT Row_number ()
                                                             OVER (
                                                               PARTITION BY ARM_ADJUSTMENT_DETAILS_SID, RS_MODEL_SID, PERIOD_SID
                                                               ORDER BY ARM_ADJUSTMENT_DETAILS_SID)RN,
                                                           ARM_ADJUSTMENT_DETAILS_SID,
                                                           RS_MODEL_SID,
                                                           PERIOD_SID,
                                                           Sum(VALUE)
                                                                            OVER (
                                                                              PARTITION BY ARM_ADJUSTMENT_DETAILS_SID, RS_MODEL_SID, PERIOD_SID) AS SUM_PROJECTION,
                                                           ( ( CASE
                                                                       WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT SALES', 'DOLLARS' ) THEN NET_CALCULATED_SALES
                                                                       WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT UNITS', 'UNITS' ) THEN NET_UNITS
                                                                     END ) / NULLIF(RS_NET_SALES, 0) ) AS RATIO,
                                                           NET_CALCULATED_SALES,
                                                           NET_UNITS,
                                                           REBATE_STRUCTURE,
                                                           TIER_OPERATOR,
                                                           CALCULATION_LEVEL,
                                                           REBATE_RANGE_BASED_ON,
                                                           SALES_PROJECTED_VALUE
                                                    FROM   (SELECT C.ARM_ADJUSTMENT_DETAILS_SID,
                                                                   C.RS_MODEL_SID,
                                                                   C.PERIOD_SID,
                                                                   NET_CALCULATED_SALES,
                                                                   NET_UNITS,
                                                                   REBATE_RANGE_BASED_ON,
                                                                   REBATE_STRUCTURE,
                                                                   CASE
                                                                           WHEN REBATE_STRUCTURE = 'TIER' THEN
                                                                             CASE
                                                                               WHEN RS_NET_SALES >= TIER_FROM
                                                                                    AND RS_NET_SALES <= TIER_TO THEN
                                                                                 CASE
                                                                                   WHEN TIER_OPERATOR = '%' THEN ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 )
                                                                                   WHEN TIER_OPERATOR = '$' THEN COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0)
                                                                                 END
                                                                             END
                                                                           WHEN REBATE_STRUCTURE = 'LEVEL' THEN
                                                                             CASE
                                                                               WHEN TIER_TO < RS_NET_SALES THEN
                                                                                 CASE
                                                                                   WHEN TIER_OPERATOR = '%' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 ) )
                                                                                   WHEN TIER_OPERATOR = '$' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0) ) )
                                                                                 END
                                                                               WHEN TIER_TO >= RS_NET_SALES THEN
                                                                                 CASE
                                                                                   WHEN RS_NET_SALES BETWEEN TIER_FROM AND TIER_TO THEN
                                                                                     CASE
                                                                                       WHEN TIER_OPERATOR = '%' THEN ( ( Iif(RS_NET_SALES < TIER_TO, RS_NET_SALES, TIER_TO) - TIER_FROM ) * ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 ) )
                                                                                       WHEN TIER_OPERATOR = '$' THEN ( ( Iif(RS_NET_SALES < TIER_TO, RS_NET_SALES, TIER_TO) - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0) ) )
                                                                                     END
                                                                                 END
                                                                             END
                                                                           WHEN REBATE_STRUCTURE = 'FLAT' THEN FLAT_DISCOUNT
                                                                         END AS VALUE,
                                                                   RS_NET_SALES,
                                                                   TIER_OPERATOR,
                                                                   CALCULATION_LEVEL,
                                                                   SALES_PROJECTED_VALUE
                                                            FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                                           RS_MODEL_SID,
                                                                           PERIOD_SID,
                                                                           Isnull(CASE
                                                                                                 WHEN CALCULATION_LEVEL LIKE '%SINGLE%' THEN Sum(SALES_PROJECTED_VALUE)
                                                                                                                                               OVER (
                                                                                                                                                 PARTITION BY RS_MODEL_SID, @PROJECTION_START_PERIOD_SID, COMPANY_MASTER_SID)
                                                                                                 WHEN CALCULATION_LEVEL LIKE '%LINE%' THEN Sum(SALES_PROJECTED_VALUE)
                                                                                                                                             OVER (
                                                                                                                                               PARTITION BY RS_MODEL_SID, CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID, @PROJECTION_START_PERIOD_SID)
                                                                                                 WHEN CALCULATION_LEVEL LIKE '%BUNDLE%' THEN Sum(SALES_PROJECTED_VALUE)
                                                                                                                                               OVER (
                                                                                                                                                 PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, @PROJECTION_START_PERIOD_SID)
                                                                                               END, 0) AS RS_NET_SALES,
                                                                           CALCULATION_LEVEL,
                                                                           BUNDLE_NO,
                                                                           REBATE_STRUCTURE,
                                                                           TIER_OPERATOR,
                                                                           NET_CALCULATED_SALES,
                                                                           NET_UNITS,
                                                                           REBATE_RANGE_BASED_ON,
                                                                           SALES_PROJECTED_VALUE
                                                                    FROM   (SELECT RI.ARM_ADJUSTMENT_DETAILS_SID,
                                                                                   RI.RS_MODEL_SID,
                                                                                   CD.PERIOD_SID,
                                                                                   CALCULATION_LEVEL,
                                                                                   BUNDLE_NO,
                                                                                   REBATE_STRUCTURE,
                                                                                   TIER_OPERATOR,
                                                                                   NET_CALCULATED_SALES,
                                                                                   NET_UNITS,
                                                                                   REBATE_RANGE_BASED_ON,
                                                                                   CASE
                                                                                                           WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT SALES', 'DOLLARS' ) THEN NET_CALCULATED_SALES
                                                                                                           WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT UNITS', 'UNITS' ) THEN NET_UNITS
                                                                                                         END AS SALES_PROJECTED_VALUE,
                                                                                   COMPANY_MASTER_SID,
                                                                                   CONTRACT_MASTER_SID,
                                                                                   ITEM_MASTER_SID
                                                                            FROM   #REBATE_INFO RI
                                                                                   LEFT JOIN #CONTRACT_DETAILS CD
                                                                                          ON RI.ARM_ADJUSTMENT_DETAILS_SID = CD.ARM_ADJUSTMENT_DETAILS_SID
                                                                                             AND RI.RS_MODEL_SID = CD.RS_MODEL_SID)D)C
                                                                   JOIN #TEMP_ITEM_PRICING TP
                                                                     ON C.ARM_ADJUSTMENT_DETAILS_SID = TP.ARM_ADJUSTMENT_DETAILS_SID
                                                                        AND C.RS_MODEL_SID = TP.RS_MODEL_SID
                                                                        AND C.PERIOD_SID = TP.PERIOD_SID
                                                                   JOIN #RETURNS_PERCENT RP
                                                                     ON RP.ARM_ADJUSTMENT_DETAILS_SID = TP.ARM_ADJUSTMENT_DETAILS_SID
                                                                        AND RP.RS_MODEL_SID = TP.RS_MODEL_SID
                                                                        AND RP.PERIOD_SID = TP.PERIOD_SID
                                                                   OUTER APPLY (SELECT DISCOUNT_RATE,
                                                                                       REBATE_PER_UNIT,
                                                                                       FLAT_DISCOUNT,
                                                                                       TIER_FROM,
                                                                                       CASE
                                                                                                 WHEN T.TIER_TO IS NULL THEN RS_NET_SALES
                                                                                                 ELSE T.TIER_TO
                                                                                               END AS TIER_TO
                                                                                FROM   #TIER_INFO T
                                                                                WHERE  C.ARM_ADJUSTMENT_DETAILS_SID = T.ARM_ADJUSTMENT_DETAILS_SID
                                                                                       AND C.RS_MODEL_SID = T.RS_MODEL_SID) OU)B)A
                                            WHERE  RN = 1)T
                                           LEFT JOIN #APPROVE_CCP AC
                                                  ON T.ARM_ADJUSTMENT_DETAILS_SID = AC.ARM_ADJUSTMENT_DETAILS_SID
                                                     AND T.RS_MODEL_SID = AC.RS_MODEL_SID
                                                     AND COALESCE(T.PERIOD_SID, AC.PERIOD_SID) = @PROJECTION_START_PERIOD_SID)R
  --Updating Main table Starts here 
                            SET @SQL=''
                            SET @SQL=Concat('UPDATE SADFR
                      SET    SADFR.CALCULATED_ADJUSTMENT = ISNULL(AC.CALCULATED_ADJUSTMENT, 0),
                             SADFR.RATE = ISNULL(AC.PROJECTION_RATE, 0),
                             SADFR.DEDUCTION_AMOUNT = ISNULL(AC.PROJECTION_AMOUNT, 0)
                      FROM   ', @ARM_DISTRIBUTION_FEES_RATE_TABLE, ' SADFR
                             INNER JOIN #RS_NET_SALES AC
                                     ON SADFR.ARM_ADJUSTMENT_DETAILS_SID = AC.ARM_ADJUSTMENT_DETAILS_SID
                                        AND SADFR.PERIOD_SID = AC.PERIOD_SID ')
 
                            EXEC sp_executesql @SQL
                        END
 --Updating Main table Ends here 
                      IF EXISTS (SELECT 1
                                 FROM   #REBATE_INFO
                                 WHERE  ( RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                                          AND RP_NET_SALES_FORMULA_MASTER_SID <> 0 )
                                        AND ( RS_NET_SALES_FORMULA_MASTER_SID IS NULL
                                              AND RS_NET_SALES_FORMULA_MASTER_SID = 0 ))
                        BEGIN
                            IF Object_id('TEMPDB..#RP_NET_SALES') IS NOT NULL
                              DROP TABLE #RP_NET_SALES
 
                            SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                   RS_MODEL_SID,
                                   PERIOD_SID,
                                   ( (PROJECTION_RATE/100.0) * CASE
                                                                               WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT SALES', 'DOLLARS' ) THEN NET_CALCULATED_SALES
                                                                               WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT UNITS', 'UNITS' ) THEN NET_UNITS
                                                                             END ) AS CALCULATED_ADJUSTMENT,
                                   PROJECTION_RATE,
                                   PROJECTION_AMOUNT
                            INTO   #RP_NET_SALES
                            FROM   (SELECT P.ARM_ADJUSTMENT_DETAILS_SID,
                                           P.RS_MODEL_SID,
                                           P.PERIOD_SID,
                                           AMOUNT AS PROJECTION_AMOUNT,
                                           Isnull(( AMOUNT / NULLIF(NET_CALCULATED_SALES, 0) ) * 100, 0) AS PROJECTION_RATE, 
                                           Isnull(( AMOUNT / NULLIF(NET_UNITS, 0) ), 0) AS PROJECTION_REBATE_PER_UNIT, 
                                           REBATE_RANGE_BASED_ON,
                                           NET_CALCULATED_SALES,
                                           NET_UNITS
                                    FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                   RS_MODEL_SID,
                                                   PERIOD_SID,
                                                   Isnull(( CASE
                                                                      WHEN REBATE_STRUCTURE = 'TIER' THEN SUM_PROJECTION * CASE
                                                                                                                             WHEN TIER_OPERATOR = '%' THEN NET_CALCULATED_SALES
                                                                                                                             WHEN TIER_OPERATOR = '$' THEN NET_UNITS
                                                                                                                           END
                                                                      WHEN REBATE_STRUCTURE = 'LEVEL' THEN
                                                                        CASE
                                                                          WHEN CALCULATION_LEVEL LIKE '%LINE%' THEN SUM_PROJECTION
                                                                          ELSE SUM_PROJECTION * RATIO
                                                                        END
                                                                      WHEN REBATE_STRUCTURE = 'FLAT' THEN
                                                                        CASE
                                                                          WHEN CALCULATION_LEVEL LIKE '%LINE%' THEN SUM_PROJECTION
                                                                          ELSE SUM_PROJECTION * RATIO
                                                                        END
                                                                    END ), 0) AS AMOUNT,
                                                   NET_CALCULATED_SALES,
                                                   NET_UNITS,
                                                   REBATE_RANGE_BASED_ON
                                            FROM   (SELECT Row_number ()
                                                             OVER (
                                                               PARTITION BY ARM_ADJUSTMENT_DETAILS_SID, RS_MODEL_SID, PERIOD_SID
                                                               ORDER BY ARM_ADJUSTMENT_DETAILS_SID)RN,
                                                           ARM_ADJUSTMENT_DETAILS_SID,
                                                           RS_MODEL_SID,
                                                           PERIOD_SID,
                                                           Sum(VALUE)
                                                                            OVER (
                                                                              PARTITION BY ARM_ADJUSTMENT_DETAILS_SID, RS_MODEL_SID, PERIOD_SID) AS SUM_PROJECTION,
                                                           ( ( CASE
                                                                       WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT SALES', 'DOLLARS' ) THEN NET_CALCULATED_SALES
                                                                       WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT UNITS', 'UNITS' ) THEN NET_UNITS
                                                                     END ) / NULLIF(RP_NET_SALES, 0) ) AS  RATIO,
                                                           NET_CALCULATED_SALES,
                                                           NET_UNITS,
                                                           REBATE_STRUCTURE,
                                                           TIER_OPERATOR,
                                                           CALCULATION_LEVEL,
                                                           REBATE_RANGE_BASED_ON
                                                    FROM   (SELECT C.ARM_ADJUSTMENT_DETAILS_SID,
                                                                   C.RS_MODEL_SID,
                                                                   C.PERIOD_SID,
                                                                   NET_CALCULATED_SALES,
                                                                   NET_UNITS,
                                                                   REBATE_RANGE_BASED_ON,
                                                                   REBATE_STRUCTURE,
                                                                   CASE
                                                                           WHEN REBATE_STRUCTURE = 'TIER' THEN
                                                                             CASE
                                                                               WHEN RP_NET_SALES >= TIER_FROM
                                                                                    AND RP_NET_SALES <= TIER_TO THEN
                                                                                 CASE
                                                                                   WHEN TIER_OPERATOR = '%' THEN ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 )
                                                                                   WHEN TIER_OPERATOR = '$' THEN COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0)
                                                                                 END
                                                                             END
                                                                           WHEN REBATE_STRUCTURE = 'LEVEL' THEN
                                                                             CASE
                                                                               WHEN TIER_TO < RP_NET_SALES THEN
                                                                                 CASE
                                                                                   WHEN TIER_OPERATOR = '%' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 ) )
                                                                                   WHEN TIER_OPERATOR = '$' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0) ) )
                                                                                 END
                                                                               WHEN TIER_TO >= RP_NET_SALES THEN
                                                                                 CASE
                                                                                   WHEN RP_NET_SALES BETWEEN TIER_FROM AND TIER_TO THEN
                                                                                     CASE
                                                                                       WHEN TIER_OPERATOR = '%' THEN ( ( Iif(RP_NET_SALES < TIER_TO, RP_NET_SALES, TIER_TO) - TIER_FROM ) * ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 ) )
                                                                                       WHEN TIER_OPERATOR = '$' THEN ( ( Iif(RP_NET_SALES < TIER_TO, RP_NET_SALES, TIER_TO) - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0) ) )
                                                                                     END
                                                                                 END
                                                                             END
                                                                           WHEN REBATE_STRUCTURE = 'FLAT' THEN FLAT_DISCOUNT
                                                                         END AS VALUE,
                                                                   RP_NET_SALES,
                                                                   TIER_OPERATOR,
                                                                   CALCULATION_LEVEL
                                                            FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                                           RS_MODEL_SID,
                                                                           PERIOD_SID,
                                                                           Isnull(CASE
                                                                                                 WHEN CALCULATION_LEVEL LIKE '%SINGLE%' THEN Sum(NETTED_SALES)
                                                                                                                                               OVER (
                                                                                                                                                 PARTITION BY RS_MODEL_SID, @PROJECTION_START_PERIOD_SID, COMPANY_MASTER_SID)
                                                                                                 WHEN CALCULATION_LEVEL LIKE '%LINE%' THEN Sum(NETTED_SALES)
                                                                                                                                             OVER (
                                                                                                                                               PARTITION BY RS_MODEL_SID, CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID, @PROJECTION_START_PERIOD_SID)
                                                                                                 WHEN CALCULATION_LEVEL LIKE '%BUNDLE%' THEN Sum(NETTED_SALES)
                                                                                                                                               OVER (
                                                                                                                                                 PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, @PROJECTION_START_PERIOD_SID)
                                                                                               END, 0) AS RP_NET_SALES,
                                                                           CALCULATION_LEVEL,
                                                                           BUNDLE_NO,
                                                                           REBATE_STRUCTURE,
                                                                           TIER_OPERATOR,
                                                                           NET_CALCULATED_SALES,
                                                                           NET_UNITS,
                                                                           REBATE_RANGE_BASED_ON
                                                                    FROM   (SELECT RI.ARM_ADJUSTMENT_DETAILS_SID,
                                                                                   RI.RS_MODEL_SID,
                                                                                   CD.PERIOD_SID,
                                                                                   CALCULATION_LEVEL,
                                                                                   BUNDLE_NO,
                                                                                   REBATE_STRUCTURE,
                                                                                   TIER_OPERATOR,
                                                                                   NET_CALCULATED_SALES,
                                                                                   NET_UNITS,
                                                                                   REBATE_RANGE_BASED_ON,
                                                                                   Isnull(( ( CASE
                                                                                                             WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT SALES', 'DOLLARS' ) THEN NET_CALCULATED_SALES
                                                                                                             WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT UNITS', 'UNITS' ) THEN NET_UNITS
                                                                                                           END ) - CURRENT_BALANCE ), 0) AS NETTED_SALES,
                                                                                   RI.COMPANY_MASTER_SID,
                                                                                   RI.CONTRACT_MASTER_SID,
                                                                                   RI.ITEM_MASTER_SID
                                                                            FROM   #REBATE_INFO RI
                                                                                   LEFT JOIN #CONTRACT_DETAILS CD
                                                                                          ON RI.ARM_ADJUSTMENT_DETAILS_SID = CD.ARM_ADJUSTMENT_DETAILS_SID
                                                                                             AND RI.RS_MODEL_SID = CD.RS_MODEL_SID
                                                                                   LEFT JOIN #APPROVE_CCP AC
                                                                                          ON RI.ARM_ADJUSTMENT_DETAILS_SID = AC.ARM_ADJUSTMENT_DETAILS_SID
                                                                                             AND RI.RS_MODEL_SID = AC.RS_MODEL_SID
                                                                                             AND COALESCE(CD.PERIOD_SID, AC.PERIOD_SID) = @PROJECTION_START_PERIOD_SID)A)C
                                                                   JOIN #TEMP_ITEM_PRICING TP
                                                                     ON C.ARM_ADJUSTMENT_DETAILS_SID = TP.ARM_ADJUSTMENT_DETAILS_SID
                                                                        AND C.RS_MODEL_SID = TP.RS_MODEL_SID
                                                                        AND C.PERIOD_SID = TP.PERIOD_SID
                                                                   JOIN #RETURNS_PERCENT RP
                                                                     ON RP.ARM_ADJUSTMENT_DETAILS_SID = TP.ARM_ADJUSTMENT_DETAILS_SID
                                                                        AND RP.RS_MODEL_SID = TP.RS_MODEL_SID
                                                                        AND RP.PERIOD_SID = TP.PERIOD_SID
                                                                   OUTER APPLY (SELECT DISCOUNT_RATE,
                                                                                       REBATE_PER_UNIT,
                                                                                       FLAT_DISCOUNT,
                                                                                       TIER_FROM,
                                                                                       CASE
                                                                                                 WHEN T.TIER_TO IS NULL THEN RP_NET_SALES
                                                                                                 ELSE T.TIER_TO
                                                                                               END AS TIER_TO
                                                                                FROM   #TIER_INFO T
                                                                                WHERE  C.ARM_ADJUSTMENT_DETAILS_SID = T.ARM_ADJUSTMENT_DETAILS_SID
                                                                                       AND C.RS_MODEL_SID = T.RS_MODEL_SID) OU)B)F
                                            WHERE  RN = 1)P)R
   --Updating Main table Starts here 
                            SET @SQL=''
                            SET @SQL=Concat('UPDATE SADFR
                      SET    SADFR.CALCULATED_ADJUSTMENT = ISNULL(AC.CALCULATED_ADJUSTMENT, 0),
                             SADFR.RATE = ISNULL(AC.PROJECTION_RATE, 0),
                             SADFR.DEDUCTION_AMOUNT = ISNULL(AC.PROJECTION_AMOUNT, 0)
                      FROM   ', @ARM_DISTRIBUTION_FEES_RATE_TABLE, ' SADFR
                             INNER JOIN #RP_NET_SALES AC
                                     ON SADFR.ARM_ADJUSTMENT_DETAILS_SID = AC.ARM_ADJUSTMENT_DETAILS_SID
                                        AND SADFR.PERIOD_SID = AC.PERIOD_SID ')
 
                            EXEC sp_executesql @SQL
	  --Updating Main table Ends here 
                        END

 
                     IF EXISTS (SELECT 1
                                 FROM   #REBATE_INFO
                                 WHERE  ( RP_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                                          AND RP_NET_SALES_FORMULA_MASTER_SID <> 0 )
                                        AND ( RS_NET_SALES_FORMULA_MASTER_SID IS NOT NULL
                                              AND RS_NET_SALES_FORMULA_MASTER_SID <> 0 ))
                        BEGIN
                            IF Object_id('TEMPDB..#NET_SALES') IS NOT NULL
                              DROP TABLE #NET_SALES
 
                            SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                   RS_MODEL_SID,
                                   PERIOD_SID,
                                   (( PROJECTION_RATE/100.0) * NETTED_SALES ) AS CALCULATED_ADJUSTMENT,
                                   PROJECTION_RATE,
                                   PROJECTION_AMOUNT
                            INTO   #NET_SALES
                            FROM   (SELECT T.ARM_ADJUSTMENT_DETAILS_SID,
                                           T.RS_MODEL_SID,
                                           T.PERIOD_SID,
                                           AMOUNT AS PROJECTION_AMOUNT,
                                           Isnull(( AMOUNT / NULLIF(NET_CALCULATED_SALES, 0) ) * 100, 0) AS PROJECTION_RATE, 
                                           Isnull(( AMOUNT / NULLIF(NET_UNITS, 0) ), 0) AS PROJECTION_REBATE_PER_UNIT,
                                           Isnull(( SALES_PROJECTED_VALUE - CURRENT_BALANCE ), 0) AS NETTED_SALES
                                    FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                   RS_MODEL_SID,
                                                  PERIOD_SID,
                                                   Isnull(( CASE
                                                                      WHEN REBATE_STRUCTURE = 'TIER' THEN SUM_PROJECTION * CASE
                                                                                                                             WHEN TIER_OPERATOR = '%' THEN NET_CALCULATED_SALES
                                                                                                                             WHEN TIER_OPERATOR = '$' THEN NET_UNITS
                                                                                                                           END
                                                                      WHEN REBATE_STRUCTURE = 'LEVEL' THEN
                                                                        CASE
                                                                          WHEN CALCULATION_LEVEL LIKE '%LINE%' THEN SUM_PROJECTION
                                                                          ELSE SUM_PROJECTION * RATIO
                                                                        END
                                                                      WHEN REBATE_STRUCTURE = 'FLAT' THEN
                                                                        CASE
                                                                          WHEN CALCULATION_LEVEL LIKE '%LINE%' THEN SUM_PROJECTION
                                                                          ELSE SUM_PROJECTION * RATIO
                                                                        END
                                                                    END ), 0) AS AMOUNT,
                                                   NET_CALCULATED_SALES,
                                                   NET_UNITS,
                                                   REBATE_RANGE_BASED_ON,
                                                   SALES_PROJECTED_VALUE
                                            FROM   (SELECT Row_number ()
                                                             OVER (
                                                               PARTITION BY ARM_ADJUSTMENT_DETAILS_SID, RS_MODEL_SID, PERIOD_SID
                                                               ORDER BY ARM_ADJUSTMENT_DETAILS_SID)RN,
                                                           ARM_ADJUSTMENT_DETAILS_SID,
                                                           RS_MODEL_SID,
                                                           PERIOD_SID,
                                                           Sum(VALUE)
                                                                            OVER (
                                                                              PARTITION BY ARM_ADJUSTMENT_DETAILS_SID, RS_MODEL_SID, PERIOD_SID) AS SUM_PROJECTION,
                                                           ( ( CASE
                                                                       WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT SALES', 'DOLLARS' ) THEN NET_CALCULATED_SALES
                                                                       WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT UNITS', 'UNITS' ) THEN NET_UNITS
                                                                     END ) / NULLIF(NET_SALES, 0) ) AS RATIO,
                                                           NET_CALCULATED_SALES,
                                                           NET_UNITS,
                                                           REBATE_STRUCTURE,
                                                           TIER_OPERATOR,
                                                           CALCULATION_LEVEL,
                                                           REBATE_RANGE_BASED_ON,
                                                           SALES_PROJECTED_VALUE
                                                    FROM   (SELECT C.ARM_ADJUSTMENT_DETAILS_SID,
                                                                   C.RS_MODEL_SID,
                                                                   C.PERIOD_SID,
                                                                   NET_CALCULATED_SALES,
                                                                   NET_UNITS,
                                                                   REBATE_RANGE_BASED_ON,
                                                                   REBATE_STRUCTURE,
                                                                   CASE
                                                                           WHEN REBATE_STRUCTURE = 'TIER' THEN
                                                                             CASE
                                                                               WHEN NET_SALES >= TIER_FROM
                                                                                    AND NET_SALES <= TIER_TO THEN
                                                                                 CASE
                                                                                   WHEN TIER_OPERATOR = '%' THEN ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 )
                                                                                   WHEN TIER_OPERATOR = '$' THEN COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0)
                                                                                 END
                                                                             END
                                                                           WHEN REBATE_STRUCTURE = 'LEVEL' THEN
                                                                             CASE
                                                                               WHEN TIER_TO < NET_SALES THEN
                                                                                 CASE
                                                                                   WHEN TIER_OPERATOR = '%' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 ) )
                                                                                   WHEN TIER_OPERATOR = '$' THEN ( ( TIER_TO - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0) ) )
                                                                                 END
                                                                               WHEN TIER_TO >= NET_SALES THEN
                                                                                 CASE
                                                                                   WHEN NET_SALES BETWEEN TIER_FROM AND TIER_TO THEN
                                                                                     CASE
                                                                                       WHEN TIER_OPERATOR = '%' THEN ( ( Iif(NET_SALES < TIER_TO, NET_SALES, TIER_TO) - TIER_FROM ) * ( COALESCE(NULLIF(DISCOUNT_RATE, 0), NULLIF(RP.RETURN_RATE, 0)) / 100.0 ) )
                                                                                       WHEN TIER_OPERATOR = '$' THEN ( ( Iif(NET_SALES < TIER_TO, NET_SALES, TIER_TO) - TIER_FROM ) * ( COALESCE(NULLIF(REBATE_PER_UNIT, 0), NULLIF(TP.ITEM_PRICE, 0), 0) ) )
                                                                                     END
                                                                                 END
                                                                             END
                                                                          WHEN REBATE_STRUCTURE = 'FLAT' THEN FLAT_DISCOUNT
                                                                         END AS VALUE,
                                                                   NET_SALES,
                                                                   TIER_OPERATOR,
                                                                   CALCULATION_LEVEL,
                                                                   SALES_PROJECTED_VALUE
                                                            FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                                           RS_MODEL_SID,
                                                                           PERIOD_SID,
                                                                           Isnull(CASE
                                                                                              WHEN CALCULATION_LEVEL LIKE '%SINGLE%' THEN Sum(SALES_PROJECTED_VALUE)
                                                                                                                                            OVER (
                                                                                                                                              PARTITION BY RS_MODEL_SID, @PROJECTION_START_PERIOD_SID, COMPANY_MASTER_SID)
                                                                                              WHEN CALCULATION_LEVEL LIKE '%LINE%' THEN Sum(SALES_PROJECTED_VALUE)
                                                                                                                                          OVER (
                                                                                                                                            PARTITION BY RS_MODEL_SID, CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID, @PROJECTION_START_PERIOD_SID)
                                                                                              WHEN CALCULATION_LEVEL LIKE '%BUNDLE%' THEN Sum(SALES_PROJECTED_VALUE)
                                                                                                                                            OVER (
                                                                                                                                              PARTITION BY COMPANY_MASTER_SID, RS_MODEL_SID, BUNDLE_NO, @PROJECTION_START_PERIOD_SID)
                                                                                            END, 0) AS NET_SALES,
                                                                           CALCULATION_LEVEL,
                                                                           BUNDLE_NO,
                                                                           REBATE_STRUCTURE,
                                                                           TIER_OPERATOR,
                                                                           NET_CALCULATED_SALES,
                                                                           NET_UNITS,
                                                                           REBATE_RANGE_BASED_ON,
                                                                           SALES_PROJECTED_VALUE
                                                                    FROM   (SELECT RI.ARM_ADJUSTMENT_DETAILS_SID,
                                                                                   RI.RS_MODEL_SID,
                                                                                   CD.PERIOD_SID,
                                                                                   CALCULATION_LEVEL,
                                                                                   BUNDLE_NO,
                                                                                   REBATE_STRUCTURE,
                                                                                   TIER_OPERATOR,
                                                                                   NET_CALCULATED_SALES,
                                                                                   NET_UNITS,
                                                                                   REBATE_RANGE_BASED_ON,
                                                                                   CASE
                                                                                                           WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT SALES', 'DOLLARS' ) THEN NET_CALCULATED_SALES
                                                                                                           WHEN REBATE_RANGE_BASED_ON IN ( 'CONTRACT UNITS', 'UNITS' ) THEN NET_UNITS
                                                                                                         END AS SALES_PROJECTED_VALUE,
                                                                                   COMPANY_MASTER_SID,
                                                                                   CONTRACT_MASTER_SID,
                                                                                   ITEM_MASTER_SID
                                                                            FROM   #REBATE_INFO RI
                                                                                   LEFT JOIN #CONTRACT_DETAILS CD
                                                                                          ON RI.ARM_ADJUSTMENT_DETAILS_SID = CD.ARM_ADJUSTMENT_DETAILS_SID
                                                                                             AND RI.RS_MODEL_SID = CD.RS_MODEL_SID)D)C
                                                                   JOIN #TEMP_ITEM_PRICING TP
                                                                     ON C.ARM_ADJUSTMENT_DETAILS_SID = TP.ARM_ADJUSTMENT_DETAILS_SID
                                                                        AND C.RS_MODEL_SID = TP.RS_MODEL_SID
                                                                        AND C.PERIOD_SID = TP.PERIOD_SID
                                                                   JOIN #RETURNS_PERCENT RP
                                                                     ON RP.ARM_ADJUSTMENT_DETAILS_SID = TP.ARM_ADJUSTMENT_DETAILS_SID
                                                                        AND RP.RS_MODEL_SID = TP.RS_MODEL_SID
                                                                        AND RP.PERIOD_SID = TP.PERIOD_SID
                                                                   OUTER APPLY (SELECT DISCOUNT_RATE,
                                                                                       REBATE_PER_UNIT,
                                                                                       FLAT_DISCOUNT,
                                                                                       TIER_FROM,
                                                                                       CASE
                                                                                                 WHEN T.TIER_TO IS NULL THEN NET_SALES
                                                                                                 ELSE T.TIER_TO
                                                                                               END AS TIER_TO
                                                                                FROM   #TIER_INFO T
                                                                                WHERE  C.ARM_ADJUSTMENT_DETAILS_SID = T.ARM_ADJUSTMENT_DETAILS_SID
                                                                                       AND C.RS_MODEL_SID = T.RS_MODEL_SID) OU)B)A
                                            WHERE  RN = 1)T
                                           LEFT JOIN #APPROVE_CCP AC
                                                  ON T.ARM_ADJUSTMENT_DETAILS_SID = AC.ARM_ADJUSTMENT_DETAILS_SID
                                                     AND T.RS_MODEL_SID = AC.RS_MODEL_SID
                                                     AND COALESCE(T.PERIOD_SID, AC.PERIOD_SID) = @PROJECTION_START_PERIOD_SID)R
    --Updating Main table Starts here 
                            SET @SQL=''
                            SET @SQL=Concat('UPDATE SADFR
                      SET    SADFR.CALCULATED_ADJUSTMENT = ISNULL(AC.CALCULATED_ADJUSTMENT, 0),
                             SADFR.RATE = ISNULL(AC.PROJECTION_RATE, 0),
                             SADFR.DEDUCTION_AMOUNT = ISNULL(AC.PROJECTION_AMOUNT, 0)
                      FROM   ', @ARM_DISTRIBUTION_FEES_RATE_TABLE, ' SADFR
                             INNER JOIN #NET_SALES AC
                                     ON SADFR.ARM_ADJUSTMENT_DETAILS_SID = AC.ARM_ADJUSTMENT_DETAILS_SID
                                        AND SADFR.PERIOD_SID = AC.PERIOD_SID ')
 
                            EXEC sp_executesql @SQL
                        END
	   --Updating Main table Ends here 
 
                      --------------------------------------------------------------------------------------------------------------------------------------  NET SALES RULE ENDS
                      SET @SQL=''
                      SET @SQL=Concat('UPDATE SA
                                   SET SA.RATE = ISNULL(CD.PROJECTION_RATE,0),
                                          SA.DEDUCTION_AMOUNT = ISNULL(CD.PROJECTION_AMOUNT,0)
                                         FROM  ', @ARM_DISTRIBUTION_FEES_RATE_TABLE, ' SA
                                          JOIN #CONTRACT_DETAILS CD
                                         ON SA.ARM_ADJUSTMENT_DETAILS_SID=CD.ARM_ADJUSTMENT_DETAILS_SID
                                         AND SA.PERIOD_SID = CD.PERIOD_SID')
 
                      EXEC sp_executesql @SQL
                  END
            END

--Deleting and Reinserting into main table Starts here
 
          IF EXISTS (SELECT 1
                     FROM   #REBATE_INFO
                     WHERE  CALCULATION_TYPE <> 'REBATE PLAN'
                             OR CALCULATION_TYPE IS NULL)
              OR NOT EXISTS (SELECT 1
                             FROM   #TEMP_EFFECTIVE)
            BEGIN
                SET @SQL=''
                SET @SQL=Concat('INSERT INTO ', @ARM_DISTRIBUTION_FEES_RATE_TABLE, '
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             PERIOD_SID,
                             CALCULATED_ADJUSTMENT,
                             RATE,
                             DEDUCTION_AMOUNT,
                             CURRENT_BALANCE,
                             VARIANCE,
                             ADJUSTMENT_RATIO)
                SELECT A.ARM_ADJUSTMENT_DETAILS_SID,
                       PERIOD_SID=', @PROJECTION_START_PERIOD_SID, ',
                       CALCULATED_ADJUSTMENT=0,
                       PROJECTION_RATE=0,
                       PROJECTION_AMOUNT=0,
                       CURRENT_BALANCE=0,
                       VARIANCE=0,
                       ADJUSTMENT_RATIO=0
                FROM   #ARM_MASTER_MASTER A
                       LEFT JOIN #REBATE_INFO R
                              ON A.ARM_ADJUSTMENT_DETAILS_SID = R.ARM_ADJUSTMENT_DETAILS_SID
                WHERE  NOT EXISTS (SELECT 1
                                   FROM   ', @ARM_DISTRIBUTION_FEES_RATE_TABLE, ' D
                                   WHERE  R.ARM_ADJUSTMENT_DETAILS_SID = D.ARM_ADJUSTMENT_DETAILS_SID) ')
 
                EXEC sp_executesql @SQL

--Deleting and Reinserting into main table Ends here

--BSR Report Chnages Starts here and Inserting into Main table Starts here
 
                SET @SQL=''
                SET @SQL=Concat('INSERT INTO #TEMP_OUTPUT
                     (ARM_ADJUSTMENT_DETAILS_SID ,
                           CURRENT_BALANCE   ,
                           CALCULATED_ADJUSTMENT ,
                           PERIOD_SID                 ,
                           VARIANCE     )
                            SELECT SADFR.ARM_ADJUSTMENT_DETAILS_SID,
                           ISNULL(AC.CURRENT_BALANCE,0),
                           SADFR.CALCULATED_ADJUSTMENT
                           ,SADFR.PERIOD_SID
                           ,ISNULL(AC.CURRENT_BALANCE,0)  - ISNULL(SADFR.CALCULATED_ADJUSTMENT,0)
                           FROM   ', @ARM_DISTRIBUTION_FEES_RATE_TABLE, ' SADFR
                                  LEFT JOIN #ARM_MASTER ARM
                                     ON ARM.ARM_ADJUSTMENT_DETAILS_SID=SADFR.ARM_ADJUSTMENT_DETAILS_SID
                       LEFT JOIN #APPROVE_CCP AC
                               ON AC.CCP_DETAILS_SID = ARM.CCP_DETAILS_SID
                                                   AND AC.RS_MODEL_SID = ARM.RS_MODEL_SID
                                                   AND SADFR.PERIOD_SID=AC.PERIOD_SID
 
                                  INSERT INTO #TEMP_AMOUNT
                                  (ARM_ADJUSTMENT_DETAILS_SID,
                                   PERIOD_SID,
                                   CURRENT_BALANCE,
                                   INDICATOR)
                                  SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                            PERIOD_SID,
                                            SUM(VARIANCE) AS CURRENT_BALANCE,
                                            CASE
                                                 WHEN SUM(VARIANCE) > 0 THEN 0
                                                 WHEN SUM(VARIANCE) < 0 THEN 1
                                                 ELSE NULL
                                            END                                 AS INDICATOR
                                  FROM   #TEMP_OUTPUT
                                  GROUP BY ARM_ADJUSTMENT_DETAILS_SID,PERIOD_SID
 
 
                                  INSERT INTO #CURRENT_LIABILITY
                           SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
                                     ISNULL(A1.PERIOD_SID, A2.PERIOD_SID),
                                     SUM(ABS(A1.CURRENT_BALANCE))                                                     AS CREDIT_AMOUNT,
                                     SUM(ABS(A2.CURRENT_BALANCE))                                                     AS DEBIT_AMOUNT,
                                     ISNULL(SUM(ABS(A1.CURRENT_BALANCE)), 0) - ISNULL(SUM(ABS(A2.CURRENT_BALANCE)), 0) AS CURRENT_AMOUNT
                           FROM   #TEMP_AMOUNT TAM
                                     JOIN ARM_ADJ_RES_CCP AAC
                                          ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
                                     INNER JOIN HELPER_TABLE HT
                                                   ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
                                                         AND HT.DESCRIPTION = ''LIABILITY''
                                                         AND AAC.ADJUSTMENT_TYPE = ',@ADJUSTMENT_TYPE,'
                                     LEFT JOIN #TEMP_AMOUNT A1
                                                  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
                                                        AND AAC.CREDIT = A1.INDICATOR
                                     LEFT JOIN #TEMP_AMOUNT A2
                                                  ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
                                                        AND AAC.DEBIT = A2.INDICATOR
							WHERE CREDIT IS NOT NULL AND DEBIT IS NOT NULL
                           GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
                                           ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
 
                                            INSERT INTO #CURRENT_EXPENSE
                     SELECT TAM.ARM_ADJUSTMENT_DETAILS_SID,
                              ISNULL(A1.PERIOD_SID, A2.PERIOD_SID),
                              SUM(ABS(A1.CURRENT_BALANCE))                                                     AS CREDIT_AMOUNT,
                              SUM(ABS(A2.CURRENT_BALANCE))                                                     AS DEBIT_AMOUNT,
                              ISNULL(SUM(ABS(A1.CURRENT_BALANCE)), 0) - ISNULL(SUM(ABS(A2.CURRENT_BALANCE)), 0) AS CURRENT_AMOUNT
                     FROM   #TEMP_AMOUNT TAM
                              JOIN ARM_ADJ_RES_CCP AAC
                                   ON TAM.ARM_ADJUSTMENT_DETAILS_SID = AAC.ARM_ADJUSTMENT_DETAILS_SID
                              INNER JOIN HELPER_TABLE HT
                                            ON AAC.ACCOUNT_TYPE = HT.HELPER_TABLE_SID
                                                  AND HT.DESCRIPTION = ''EXPENSE''
                                                  AND AAC.ADJUSTMENT_TYPE = ',@ADJUSTMENT_TYPE,'
                              LEFT JOIN #TEMP_AMOUNT A1
                                           ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A1.ARM_ADJUSTMENT_DETAILS_SID
                                                 AND AAC.CREDIT = A1.INDICATOR
                              LEFT JOIN #TEMP_AMOUNT A2
                                           ON AAC.ARM_ADJUSTMENT_DETAILS_SID = A2.ARM_ADJUSTMENT_DETAILS_SID
                                                 AND AAC.DEBIT = A2.INDICATOR
						WHERE CREDIT IS NOT NULL AND DEBIT IS NOT NULL
                     GROUP  BY TAM.ARM_ADJUSTMENT_DETAILS_SID,
                                    ISNULL(A1.PERIOD_SID, A2.PERIOD_SID)
 
                                     UPDATE SA
                                   SET SA.CURRENT_BALANCE = ISNULL(A.CURRENT_BALANCE,0),
                                          SA.VARIANCE = (ISNULL(A.CURRENT_BALANCE,0)  - ISNULL(A.CALCULATED_ADJUSTMENT,0) ),
                         SA.ADJUSTMENT_RATIO = ISNULL(( A.CALCULATED_ADJUSTMENT / NULLIF(A.CURRENT_BALANCE, 0) ),0),
                                           LIABILITY_AMOUNT=LIA.CURRENT_AMOUNT  ,
                                       EXPENSE_AMOUNT=CEX.CURRENT_AMOUNT   FROM
                                          ', @ARM_DISTRIBUTION_FEES_RATE_TABLE, ' SA JOIN
                                  (SELECT ARM_ADJUSTMENT_DETAILS_SID
                                  ,SUM(CURRENT_BALANCE) AS CURRENT_BALANCE
                                  ,SUM(CALCULATED_ADJUSTMENT) AS CALCULATED_ADJUSTMENT  FROM
                                   #TEMP_OUTPUT GROUP BY ARM_ADJUSTMENT_DETAILS_SID)A ON  A.ARM_ADJUSTMENT_DETAILS_SID=SA.ARM_ADJUSTMENT_DETAILS_SID
                                     LEFT JOIN #CURRENT_LIABILITY LIA
                                                  ON A.ARM_ADJUSTMENT_DETAILS_SID = LIA.ARM_ADJUSTMENT_DETAILS_SID
                                     LEFT JOIN #CURRENT_EXPENSE CEX
                                                  ON A.ARM_ADJUSTMENT_DETAILS_SID = CEX.ARM_ADJUSTMENT_DETAILS_SID')
 
  --BSR Report Chnages Starts here and Inserting into Main table Ends here    
                EXEC sp_executesql @SQL
            END
    
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

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.    
                     @ERRORSEVERITY,-- SEVERITY.    
                     @ERRORSTATE,-- STATE.    
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.    
                     @ERRORNUMBER,-- ERRORNUMBER    
                     @ERRORLINE -- ERRORLINE    
          ); 
      END CATCH 
  END 