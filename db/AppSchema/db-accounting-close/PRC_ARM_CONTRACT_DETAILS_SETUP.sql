IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_ARM_CONTRACT_DETAILS_SETUP'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_ARM_CONTRACT_DETAILS_SETUP]
  END

GO

CREATE PROCEDURE [dbo].[PRC_ARM_CONTRACT_DETAILS_SETUP] (@PROJECTION_MASTER_SID INT,
                                                         @USER_ID               INT,
                                                         @SESSION_ID            INT)
AS
  /**********************************************************************************************************
  ** File Name			:	PRC_ARM_CONTRACT_DETAILS_SETUP.sql
  ** Procedure Name		:	PRC_ARM_CONTRACT_DETAILS_SETUP
  ** Description		:	Contract Details information setup for the Tx-3 AND Tx-7
  ** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Tr-3 as base
  							@USER_ID                - it is the identification of the User Performing particular projection
  							@SESSION_ID             - it is the identification of session for particular projection
  											
  ** Output Parameters	:	NA
  ** Author Name		:	@SandeepKumar
  ** Creation Date		:	07/26/2017 - MM/DD/YYYY
  ** Called By			:   Called by procedure in Tx-3( for contract terms as rate basis) and Tx-7                        
  **********************************************************************************************************
  ** Change History
  **********************************************************************************************************
  ** VER   Date      Ticket No         Author          Description 
  ** ---   --------  ---------        -------------    -----------------------------
  ** 1    07/26/2017  GAL-12236      @SandeepKumar     Contract Details information setup for the Tx-3 AND Tx-7.
  ** 2    10/16/2017  GAL-12531      @SandeepKumar     Contract Details information setup for the Tx-3.
  ** 3    13/12,2017  GAL-12903      @AjayNaidu        NetCalculated sales pulling logic change
  ** 4    04-01-2018  GAL-12268      @AjayNaidu        BP012 - CASE without ELSE
  ** 5    08-01-2018  GAL-12270      @AjayNaidu        EI025 PE001 PE010 ST008 MI005 MI002 Error codes
  ** 6    26-02-2018  GAL-13196      @AjayNaidu        Inventory Sales Pulling Logic Changed in Contract Details Methodology
  *********************************************************************************************************/
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          -- Variables Initialization and assigning starts here
          DECLARE @PROJECTION_START_PERIOD_SID INT,
                  @ACTUAL_START_DATE           DATE,
                  @PROJECTION_END_PERIOD_DATE  DATE,
                  @PROJECTION_START_DATE       DATE,
                  @SQL                         NVARCHAR(MAX),
                  @FROM_PERIOD_DATE            DATETIME,
                  @MODULE                      VARCHAR(100)
          DECLARE @ARM_DISTRIBUTION_FEES_SALES_TABLE VARCHAR(200) = Concat ('ST_ARM_DISTRIBUTION_FEES_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @INVENTORY_TABLE                   VARCHAR(200) = Concat ('ST_ARM_INVENTORY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

          -- Variables Initialization Ends here
----------------Creating all necessray temp tables(all ddl statements) for the logic Starts here (PE010 CodeGuarderror)
   IF Object_id('TEMPDB..#TEMP_EFFECTIVE1') IS NOT NULL
            BEGIN
                DROP TABLE #TEMP_EFFECTIVE1
            END

          CREATE TABLE #TEMP_EFFECTIVE1
            (
               PROJECTION_DETAILS_SID INT NOT NULL,
               CCP_DETAILS_SID        INT NOT NULL,
               RS_MODEL_SID           INT NOT NULL,
               RS_CATEGORY            VARCHAR(50) NULL,
               START_DATE             DATETIME NULL,
               END_DATE               DATETIME NULL,
               PRIMARY KEY (PROJECTION_DETAILS_SID)
            )

   IF Object_id('TEMPDB..#TEMP_RATE_IDENTIFICATION') IS NOT NULL
            BEGIN
                DROP TABLE #TEMP_RATE_IDENTIFICATION
            END

          CREATE TABLE #TEMP_RATE_IDENTIFICATION
            (
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               PROJECTION_MASTER_SID      INT NOT NULL,
               CCP_DETAILS_SID            INT NOT NULL,
               RS_MODEL_SID               INT NOT NULL,
               NET_CALCULATED_SALES       NUMERIC(22, 6) NULL,
               NET_UNITS                  NUMERIC(22, 6) NULL
               PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID)
            )

	         IF Object_id('TEMPDB..#ARM_MASTER_MASTER1') IS NOT NULL
            BEGIN
                DROP TABLE #ARM_MASTER_MASTER1
            END

          CREATE TABLE #ARM_MASTER_MASTER1
            (
               PROJECTION_MASTER_SID      INT NOT NULL,
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               CCP_DETAILS_SID            INT NOT NULL,
               CONTRACT_MASTER_SID        INT NOT NULL,
               COMPANY_MASTER_SID         INT NOT NULL,
               ITEM_MASTER_SID            INT NOT NULL,
               RS_MODEL_SID               INT NOT NULL,
               ACTUAL_START_DATE          DATETIME NULL,
               PROJ_START_DATE            DATETIME NULL,
               PROJECTION_END_PERIOD_DATE DATETIME NULL,
               GL_COMPANY_MASTER_SID      INT NULL
               PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID)
            )

		   IF Object_id('TEMPDB..#ARM_MASTER1') IS NOT NULL
            BEGIN
                DROP TABLE #ARM_MASTER1
            END

          CREATE TABLE #ARM_MASTER1
            (
               [PROJECTION_MASTER_SID]      INT NOT NULL,
               [ARM_ADJUSTMENT_DETAILS_SID] INT NOT NULL,
               [CCP_DETAILS_SID]            INT NOT NULL,
               [CONTRACT_MASTER_SID]        INT NOT NULL,
               [COMPANY_MASTER_SID]         INT NOT NULL,
               [ITEM_MASTER_SID]            INT NOT NULL,
               [RS_MODEL_SID]               INT NOT NULL,
               [ACTUAL_START_DATE]          DATETIME NULL,
               [PROJ_START_DATE]            DATETIME NULL,
               [PROJECTION_END_PERIOD_DATE] DATETIME NULL,
               [GL_COMPANY_MASTER_SID]      INT NULL,
               PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID)
            );

		   IF Object_id('TEMPDB..#REBATE_INFO1') IS NOT NULL
            BEGIN
                DROP TABLE #REBATE_INFO1
            END

          CREATE TABLE #REBATE_INFO1
            (
               PROJECTION_MASTER_SID      INT NOT NULL,
               ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
               CONTRACT_MASTER_SID        INT NOT NULL,
               COMPANY_MASTER_SID         INT NOT NULL,
               ITEM_MASTER_SID            INT NOT NULL,
               RS_MODEL_SID               INT NOT NULL,
               BUNDLE_NO                  INT NULL,
               REBATE_PLAN_MASTER_SID     INT NULL,
               TIER_OPERATOR              CHAR(1) NULL,
               REBATE_STRUCTURE           VARCHAR(50) NULL,
               REBATE_BASED_ON            VARCHAR(50) NULL,
               REBATE_RANGE_BASED_ON      VARCHAR(50) NULL,
               CALCULATION_TYPE           VARCHAR(50) NULL,
               CALCULATION_LEVEL          VARCHAR(50) NULL
               PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID)
            )

		     IF Object_id('TEMPDB..#CONTRACT_DETAILS1') IS NOT NULL
                  DROP TABLE #CONTRACT_DETAILS1

                CREATE TABLE #CONTRACT_DETAILS1
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
                     CALCULATION_TYPE           VARCHAR(50) NULL,
					 SUM_PROJECTION             NUMERIC(22, 6) NULL----GAL-12531
                     PRIMARY KEY (ARM_ADJUSTMENT_DETAILS_SID)
                  )

			IF Object_id('TEMPDB..#TIER_INFO1') IS NOT NULL
                        BEGIN
                            DROP TABLE #TIER_INFO1
                        END

                      CREATE TABLE #TIER_INFO1
                        (
                           ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL,
                           RS_MODEL_SID               INT NOT NULL,
                           TIER_FROM                  NUMERIC(22, 6) NULL,
                           TIER_TO                    NUMERIC(22, 6) NULL,
                           DISCOUNT_RATE              NUMERIC(22, 6) NULL,
                           REBATE_PER_UNIT            NUMERIC(22, 6) NULL,
                           FLAT_DISCOUNT              NUMERIC(22, 6) NULL
                        )
----------------Creating all necessray temp tables(all ddl statements) for the logic Ends here (PE010 CodeGuarderror)
          -- Select base methodolgy Ends here
          --Effective dating and elimination of CCP'S Based on effective dating Concept Starts here
          SELECT @FROM_PERIOD_DATE = CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(FROM_DATE, 0))))
          FROM   DBO.PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          INSERT INTO #TEMP_EFFECTIVE1
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
          FROM   Udf_date_finder(@PROJECTION_MASTER_SID, 'D')
          WHERE  START_DATE = @FROM_PERIOD_DATE
		  
		 /* INSERT INTO #TEMP_EFFECTIVE1
                      (PROJECTION_DETAILS_SID,
                       CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       RS_CATEGORY,
                       START_DATE,
                       END_DATE)
          SELECT distinct PROJECTION_DETAILS_SID,
                 CCP_DETAILS_SID,
                 RS_MODEL_SID,
                 RS_CATEGORY,
                 @FROM_PERIOD_DATE,
                 @FROM_PERIOD_DATE
          FROM   Udf_date_finder(@PROJECTION_MASTER_SID, 'D')
          ---WHERE  START_DATE = @FROM_PERIOD_DATE
		  */
          SELECT @PROJECTION_START_PERIOD_SID = PERIOD_SID
          FROM   DBO.PERIOD
          WHERE  PERIOD_DATE = @FROM_PERIOD_DATE

          SELECT @MODULE = C.DESCRIPTION
          FROM   dbo.ARM_ADJUSTMENT_MASTER A
                 JOIN dbo.ARM_ADJUSTMENT_CONFIG B
                   ON A.TRANSACTION_TYPE = B.ARM_ADJUSTMENT_CONFIG_SID
                 JOIN dbo.HELPER_TABLE C
                   ON C.HELPER_TABLE_SID = B.METHODOLGY
          WHERE  a.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          --Effective dating and elimination of CCP'S Based on effective dating Concept Ends here
          DECLARE @rate_ident NVARCHAR(max)
----GAL-12531 ---REMOVED TH CCP DETAILS CONDITION
          IF EXISTS (SELECT *
                     FROM   DBO.HELPER_TABLE
                     WHERE  DESCRIPTION = 'TRANSACTION 3 - PIPELINE INVENTORY TRUE-UP'
                            AND LIST_NAME = 'ARM_TRX_METHDOLOGY'
                            AND @MODULE = DESCRIPTION)
            BEGIN
                 SET @RATE_IDENT = Concat (' INSERT INTO #TEMP_RATE_IDENTIFICATION
                                               (ARM_ADJUSTMENT_DETAILS_SID,
                                                PROJECTION_MASTER_SID,
                                                NET_CALCULATED_SALES,
                                                CCP_DETAILS_SID,
                                                RS_MODEL_SID)
                                  SELECT AD.ARM_ADJUSTMENT_DETAILS_SID,
                                          AD.PROJECTION_MASTER_SID,
                                          APS.NET_PIPELINE_VALUE AS  NET_PIPELINE_VALUE,
                                          AD.CCP_DETAILS_SID,
                                          AD.RS_MODEL_SID 
                                           FROM ARM_ADJUSTMENT_DETAILS AD
                                  JOIN CCP_DETAILS CD
                   ON AD.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                 JOIN ARM_ADJUSTMENT_MASTER AM
                   ON AM.PROJECTION_MASTER_SID = ',@PROJECTION_MASTER_SID,'
				   JOIN (SELECT ITEM_MASTER_SID,
				                MAX(NET_PIPELINE_VALUE) NET_PIPELINE_VALUE  
				   FROM  ',  @INVENTORY_TABLE ,' INVEN WHERE PROJECTION_MASTER_SID=',@PROJECTION_MASTER_SID,' GROUP BY ITEM_MASTER_SID)APS
				   ON CD.ITEM_MASTER_SID=APS.ITEM_MASTER_SID
					WHERE  AD.PROJECTION_MASTER_SID = ',@PROJECTION_MASTER_SID,'')
            END

          IF EXISTS (SELECT *
                     FROM   DBO.HELPER_TABLE
                     WHERE  LIST_NAME = 'ARM_TRX_METHDOLOGY'
                            AND DESCRIPTION = 'TRANSACTION 7 - DISTRIBUTION FEES'
                            AND @MODULE = DESCRIPTION)
            BEGIN
                SET @RATE_IDENT = Concat (' INSERT INTO #TEMP_RATE_IDENTIFICATION
                                               (ARM_ADJUSTMENT_DETAILS_SID,
                                                PROJECTION_MASTER_SID,
                                                NET_CALCULATED_SALES,
                                                CCP_DETAILS_SID,
                                                RS_MODEL_SID,
                                                NET_UNITS)
                                   SELECT ARM_DET.ARM_ADJUSTMENT_DETAILS_SID,
                                          INVEN.PROJECTION_MASTER_SID,
                                          INVEN.NET_CALCULATED_SALES,
                                          ARM_DET.CCP_DETAILS_SID,
                                          ARM_DET.RS_MODEL_SID,
                                          INVEN.NET_UNITS 
                                   
								  FROM  ', @ARM_DISTRIBUTION_FEES_SALES_TABLE, ' INVEN
                                  JOIN ARM_ADJUSTMENT_DETAILS ARM_DET ON ARM_DET.PROJECTION_MASTER_SID = INVEN.PROJECTION_MASTER_SID
                                  JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID = ARM_DET.CCP_DETAILS_SID
                                  AND INVEN.ITEM_MASTER_SID = CD.ITEM_MASTER_SID
								  AND INVEN.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID
								  WHERE  ARM_DET.PROJECTION_MASTER_SID = ',@PROJECTION_MASTER_SID,'')
            END

          EXEC Sp_executesql
            @RATE_IDENT

          -- Pulling CCP+D Combination for Current projection starts here

          INSERT INTO #ARM_MASTER_MASTER1
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
                 ad.ARM_ADJUSTMENT_DETAILS_SID,
                 CD.CCP_DETAILS_SID,
                 CD.CONTRACT_MASTER_SID,
                 CD.COMPANY_MASTER_SID,
                 CD.ITEM_MASTER_SID,
                 AD.RS_MODEL_SID,
                 Datefromparts(Year(Getdate()) - 3, 01, 01)                 AS ACTUAL_START_DATE,
                 Datefromparts(Year(PM.FROM_DATE), Month(PM.FROM_DATE), 01) AS PROJ_START_DATE,
                 Datefromparts(Year(PM.TO_DATE), Month(PM.TO_DATE), 01)     AS PROJECTION_END_PERIOD_DATE,
                 PM.COMPANY_MASTER_SID                                      AS GL_COMPANY_MASTER_SID
          FROM   dbo.ARM_ADJUSTMENT_DETAILS AD
                 INNER JOIN dbo.PROJECTION_MASTER PM
                         ON AD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
                 INNER JOIN dbo.CCP_DETAILS CD
                         ON CD.CCP_DETAILS_SID = AD.CCP_DETAILS_SID
          WHERE  PM.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                 AND pm.FORECASTING_TYPE = 'ARM'

          -- Pulling CCP+D Combination for Current projection Ends here
          --Effective Dating Locic applies here
          INSERT INTO #ARM_MASTER1
                      ([PROJECTION_MASTER_SID],
                       [ARM_ADJUSTMENT_DETAILS_SID],
                       [CCP_DETAILS_SID],
                       [CONTRACT_MASTER_SID],
                       [COMPANY_MASTER_SID],
                       [ITEM_MASTER_SID],
                       [RS_MODEL_SID],
                       [ACTUAL_START_DATE],
                       [PROJ_START_DATE],
                       [PROJECTION_END_PERIOD_DATE],
                       [GL_COMPANY_MASTER_SID])
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
          FROM   #ARM_MASTER_MASTER1 A
                 JOIN #TEMP_EFFECTIVE1 B
                   ON A.ARM_ADJUSTMENT_DETAILS_SID = B.PROJECTION_DETAILS_SID
          WHERE  b.START_DATE = @FROM_PERIOD_DATE

          --Taking Liability Accounts and calculating Debit and Credit Logic Starts here

          --Taking Liability Accounts and calculating Debit and Credit Logic Ends here
          IF EXISTS (SELECT 1
                     FROM   #TEMP_EFFECTIVE1)
            BEGIN
                SELECT TOP 1 @ACTUAL_START_DATE = ACTUAL_START_DATE,-- FIRST DAY OF -3 YEARS    
                             @PROJECTION_START_DATE = PROJ_START_DATE,--FIRST DAY OF THE QUARTER    
                             @PROJECTION_END_PERIOD_DATE = PROJECTION_END_PERIOD_DATE --LAST DAY OF QUARTER
                -- ,@GL_COMPANY_ID = GL_COMPANY_MASTER_SID--------------------GAL-808
                FROM   #ARM_MASTER1
                ORDER  BY GL_COMPANY_MASTER_SID

                SELECT @PROJECTION_START_PERIOD_SID = Max(CASE
                                                            WHEN @PROJECTION_START_DATE = PERIOD_DATE THEN PERIOD_SID
															ELSE NULL
                                                          END)
                FROM   dbo.PERIOD
                WHERE  PERIOD_DATE IN ( @ACTUAL_START_DATE, @PROJECTION_START_DATE, @PROJECTION_END_PERIOD_DATE )

                -- CONTRACT DETAILS METHODOLOGY STARTS HERE  
				  ; WITH CTE
                     AS (SELECT AM.PROJECTION_MASTER_SID,
                                AM.ARM_ADJUSTMENT_DETAILS_SID,
                                AM.CONTRACT_MASTER_SID,
                                AM.COMPANY_MASTER_SID,
                                AM.ITEM_MASTER_SID,
                                AM.RS_MODEL_SID,
                                RS.BUNDLE_NO,
                                HT.[DESCRIPTION] AS CALCULATION_TYPE,
                                HL.[DESCRIPTION] AS CALCULATION_LEVEL,
                                RS.RS_CONTRACT_DETAILS_SID
                         FROM   #ARM_MASTER1 AM
                                INNER JOIN dbo.RS_CONTRACT RC
                                        ON RC.RS_MODEL_SID = AM.RS_MODEL_SID
                                           AND RC.CONTRACT_MASTER_SID = AM.CONTRACT_MASTER_SID
                                           AND RC.INBOUND_STATUS <> 'D'
                                INNER JOIN dbo.RS_CONTRACT_DETAILS RS
                                        ON RS.RS_CONTRACT_SID = RC.RS_CONTRACT_SID
                                           AND RS.ITEM_MASTER_SID = AM.ITEM_MASTER_SID
                                           AND RS.INBOUND_STATUS <> 'D'
                                LEFT JOIN dbo.HELPER_TABLE HT
                                       ON HT.HELPER_TABLE_SID = RC.CALCULATION_TYPE
                                          AND HT.LIST_NAME = 'CALCULATION_TYPE'
                                LEFT JOIN dbo.HELPER_TABLE HL
                                       ON HL.HELPER_TABLE_SID = RC.CALCULATION_LEVEL
                                          AND HL.LIST_NAME = 'CALCULATION_LEVEL'
                         WHERE  EXISTS (SELECT 1
                                        FROM   dbo.RS_MODEL RM
                                               INNER JOIN dbo.RS_DETAILS RD
                                                       ON RM.RS_MODEL_SID = RD.RS_MODEL_SID
                                                          AND RD.ITEM_MASTER_SID = AM.ITEM_MASTER_SID
                                        WHERE  AM.RS_MODEL_SID = RM.RS_MODEL_SID)
                                AND EXISTS (SELECT 1
                                            FROM   dbo.CFP_CONTRACT CC
                                                   INNER JOIN dbo.IFP_CONTRACT IC
                                                           ON CC.CFP_CONTRACT_SID = IC.CFP_CONTRACT_SID
                                                              AND CC.CONTRACT_MASTER_SID = IC.CONTRACT_MASTER_SID
                                                   INNER JOIN dbo.PS_CONTRACT PC
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
                     AS (SELECT PROJECTION_MASTER_SID,
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
                                RP_NET_SALES_FORMULA_MASTER_SID
                         FROM   (SELECT Row_number()
                                          OVER (
                                            PARTITION BY T.ARM_ADJUSTMENT_DETAILS_SID
                                            ORDER BY T.ARM_ADJUSTMENT_DETAILS_SID ) RN,
                                        T.PROJECTION_MASTER_SID,
                                        T.ARM_ADJUSTMENT_DETAILS_SID,
                                        T.CONTRACT_MASTER_SID,
                                        T.COMPANY_MASTER_SID,
                                        T.ITEM_MASTER_SID,
                                        T.RS_MODEL_SID,
                                        T.BUNDLE_NO,
                                        RC.REBATE_PLAN_MASTER_SID,
                                        HTO.[DESCRIPTION]                           AS TIER_OPERATOR,
                                        HRS.[DESCRIPTION]                           AS REBATE_STRUCTURE,
                                        HRB.[DESCRIPTION]                           AS REBATE_BASED_ON,
                                        HRR.[DESCRIPTION]                           AS REBATE_RANGE_BASED_ON,
                                        T.CALCULATION_TYPE,
                                        T.CALCULATION_LEVEL,
                                        RPT.ITEM_PRICING_QUALIFIER_SID,
                                        RPT.RETURN_RATE_SID,
                                        RPM.NET_SALES_FORMULA_MASTER_SID            AS RP_NET_SALES_FORMULA_MASTER_SID
                                 FROM   CTE T
                                        JOIN dbo.RS_CONTRACT_DETAILS RC
                                          ON RC.RS_CONTRACT_DETAILS_SID = T.RS_CONTRACT_DETAILS_SID
                                        LEFT JOIN dbo.REBATE_PLAN_MASTER RPM
                                               ON RC.REBATE_PLAN_MASTER_SID = RPM.REBATE_PLAN_MASTER_SID
                                        LEFT JOIN dbo.REBATE_PLAN_TIER RPT
                                               ON RPM.REBATE_PLAN_MASTER_SID = RPT.REBATE_PLAN_MASTER_SID
                                        LEFT JOIN dbo.HELPER_TABLE HRS
                                               ON HRS.HELPER_TABLE_SID = RPM.REBATE_STRUCTURE
                                                  AND HRS.LIST_NAME = 'REBATE_STRUCTURE'
                                        LEFT JOIN dbo.HELPER_TABLE HTO
                                               ON HTO.HELPER_TABLE_SID = RPT.TIER_OPERATOR
                                                  AND HTO.LIST_NAME = 'TIER_OPERATOR'
                                        LEFT JOIN dbo.HELPER_TABLE HRB
                                               ON HRB.HELPER_TABLE_SID = RPM.REBATE_BASED_ON
                                                  AND HRB.LIST_NAME = 'REBATE_BASED_ON'
                                        LEFT JOIN dbo.HELPER_TABLE HRR
                                               ON HRR.HELPER_TABLE_SID = RPM.REBATE_RANGE_BASED_ON
                                                  AND HRR.LIST_NAME = 'REBATE_RANGE_BASED_ON') A
                         WHERE  RN = 1)
                INSERT INTO #REBATE_INFO1
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
                             CALCULATION_LEVEL)
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
                       CALCULATION_LEVEL
                FROM   REBATE_INFO

                IF EXISTS (SELECT 1
                           FROM   #REBATE_INFO1
                           WHERE  CALCULATION_TYPE = 'REBATE PLAN')
                  BEGIN
                      INSERT INTO #TIER_INFO1
                                  (ARM_ADJUSTMENT_DETAILS_SID,
                                   RS_MODEL_SID,
                                   TIER_FROM,
                                   TIER_TO,
                                   DISCOUNT_RATE)
                      SELECT DISTINCT rs.ARM_ADJUSTMENT_DETAILS_SID,
                                      rs.RS_MODEL_SID,
                                      RPT.TIER_FROM,
                                      RPT.TIER_TO,
                                      Isnull(CASE
                                               WHEN RS.TIER_OPERATOR = '%' THEN RPT.TIER_VALUE
											   ELSE NULL
                                             END, 0) AS DISCOUNT_RATE
                      FROM   #REBATE_INFO1 RS
                             JOIN dbo.REBATE_PLAN_MASTER RPM
                               ON RS.REBATE_PLAN_MASTER_SID = RPM.REBATE_PLAN_MASTER_SID
                             JOIN dbo.REBATE_PLAN_TIER RPT
                               ON RPM.REBATE_PLAN_MASTER_SID = RPT.REBATE_PLAN_MASTER_SID

                      --TIER OPERATOR =% IMPLEMENTATION ENDS HERE    
                      SET @SQL = Concat ('', ';WITH CONTRACT_DETAILS_INFO
                     AS (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                RS_MODEL_SID,
                                PERIOD_SID,
                                SALES_PROJECTED_VALUE=SUM(SALES_PROJECTED_VALUE)
                                                                                                    OVER (
                                                                                                      PARTITION BY  ARM_ADJUSTMENT_DETAILS_SID,RS_MODEL_SID, PERIOD_SID),
                                CALCULATION_LEVEL,
                                REBATE_STRUCTURE,
                                TIER_OPERATOR,
                                NET_CALCULATED_SALES,
								 NET_UNITS,
                                REBATE_RANGE_BASED_ON,
								CALCULATION_TYPE
                         FROM   (SELECT DISTINCT R.ARM_ADJUSTMENT_DETAILS_SID,
                                                 R.RS_MODEL_SID,
                                                 CALCULATION_LEVEL,
                                                 CASE
                                                   WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT SALES'', ''DOLLARS'' ) THEN NET_CALCULATED_SALES                                                   
                                                 END AS SALES_PROJECTED_VALUE,
                                                 PERIOD_SID=', @PROJECTION_START_PERIOD_SID, ',
                                                 BUNDLE_NO,
                                                 REBATE_STRUCTURE,
                                                 TIER_OPERATOR,
                                                 NET_CALCULATED_SALES,
												 NET_UNITS,
                                                 REBATE_RANGE_BASED_ON,
												 CALCULATION_TYPE
                                 FROM   #REBATE_INFO1 R
                                        LEFT JOIN #TEMP_RATE_IDENTIFICATION SNSP
                                               ON R.ARM_ADJUSTMENT_DETAILS_SID = SNSP.ARM_ADJUSTMENT_DETAILS_SID
                                                )A),
                     TIER_INFO
                     AS (SELECT C.ARM_ADJUSTMENT_DETAILS_SID,
                                C.RS_MODEL_SID,
                                C.PERIOD_SID,
                                NET_CALCULATED_SALES,
                                SALES_PROJECTED_VALUE,
                                REBATE_STRUCTURE,
                                CALCULATION_LEVEL,
                                TIER_FROM,
                                TIER_TO,
                                DISCOUNT_RATE,
                                TIER_OPERATOR,  
								CASE
                                     WHEN REBATE_STRUCTURE = ''TIER'' THEN
                                        CASE
                                           WHEN SALES_PROJECTED_VALUE >= TIER_FROM
                                                AND SALES_PROJECTED_VALUE <= TIER_TO THEN
                                              1 END
                                      END AS TIER_SELECT,                                
                                VALUE=CASE
                                        WHEN REBATE_STRUCTURE = ''TIER'' THEN
                                          CASE
                                            WHEN SALES_PROJECTED_VALUE >= TIER_FROM
                                                 AND SALES_PROJECTED_VALUE <= TIER_TO THEN
                                              CASE
                                                WHEN TIER_OPERATOR = ''%'' THEN COALESCE(NULLIF(DISCOUNT_RATE, 0)/ 100.0  ,0)                                          
                                              END
                                          END
                                      END,
                                REBATE_RANGE_BASED_ON,
								c.NET_UNITS,
								C.CALCULATION_TYPE
                         FROM   CONTRACT_DETAILS_INFO C
                                OUTER APPLY (SELECT DISCOUNT_RATE,
                                                    REBATE_PER_UNIT,
                                                    FLAT_DISCOUNT,
                                                    TIER_FROM,
                                                    TIER_TO=CASE
                                                              WHEN T.TIER_TO IS NULL THEN SALES_PROJECTED_VALUE
                                                              ELSE T.TIER_TO
                                                            END
                                             FROM   #TIER_INFO1 T
                                             WHERE  C.ARM_ADJUSTMENT_DETAILS_SID = T.ARM_ADJUSTMENT_DETAILS_SID
                                                    AND C.RS_MODEL_SID = T.RS_MODEL_SID) OU),
													')
                      SET @SQL = Concat (@SQL, ' 
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
                                RATIO=( ( CASE
                                            WHEN REBATE_RANGE_BASED_ON IN ( ''CONTRACT SALES'', ''DOLLARS'' ) THEN NET_CALCULATED_SALES
                                          END ) / NULLIF(SALES_PROJECTED_VALUE, 0) ),
                                ------EITHER NET_CALCULATED_SALES OR NET_UNITS CONFIRMATION BASED ON REBATE RANGE BASED ON    
                                SALES_PROJECTED_VALUE,
                                REBATE_STRUCTURE,
                                CALCULATION_LEVEL,
                                TIER_OPERATOR,
                                VALUE,
								NET_UNITS,
								CALCULATION_TYPE
                         FROM   TIER_INFO WHERE TIER_SELECT IS NOT NULL),
                     CONTRACT_DETAILS
                     AS (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                RS_MODEL_SID,
                                PERIOD_SID,
                                PROJECTION_AMOUNT= AMOUNT,
                                PROJECTION_RATE= ( AMOUNT / NULLIF(NET_CALCULATED_SALES, 0) ) * 100,
                                PROJECTION_REBATE_PER_UNIT= ( AMOUNT / NULLIF(NET_UNITS, 0) ),
                                NET_CALCULATED_SALES,
								NET_UNITS,
                                SALES_PROJECTED_VALUE,SUM_PROJECTION,CALCULATION_TYPE----GAL-12531
                         FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                        RS_MODEL_SID,
                                        PERIOD_SID,
                                        AMOUNT,
                                        NET_CALCULATED_SALES,
										NET_UNITS,
                                        SALES_PROJECTED_VALUE,SUM_PROJECTION,CALCULATION_TYPE--------GAL-12531
                                 FROM   (SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                                RS_MODEL_SID,
                                                PERIOD_SID,
                                                AMOUNT= CASE
                                                          WHEN REBATE_STRUCTURE = ''TIER'' THEN SUM_PROJECTION * CASE
                                                                                                                 WHEN TIER_OPERATOR = ''%'' THEN NET_CALCULATED_SALES
                                                                                                               END                                                         
                                                        END,
                                                NET_CALCULATED_SALES,
												NET_UNITS,
                                                SALES_PROJECTED_VALUE,
                                                REBATE_STRUCTURE,SUM_PROJECTION,CALCULATION_TYPE----GAL-12531
                                         FROM   AGGREGATION
                                         WHERE  RN = 1)B)A)
                INSERT INTO #CONTRACT_DETAILS1
                            (ARM_ADJUSTMENT_DETAILS_SID,
                             RS_MODEL_SID,
                             PERIOD_SID,
                             PROJECTION_AMOUNT,
                             PROJECTION_RATE,
                             PROJECTION_REBATE_PER_UNIT,
                             NET_CALCULATED_SALES,
                             NET_UNITS,
                             SALES_PROJECTED_VALUE,
                             CALCULATION_TYPE,SUM_PROJECTION)--------GAL-12531
                SELECT ARM_ADJUSTMENT_DETAILS_SID,
                       RS_MODEL_SID,
                       ISNULL(PERIOD_SID, ', @PROJECTION_START_PERIOD_SID, '),
                       ISNULL(PROJECTION_AMOUNT, 0),
                       ISNULL(PROJECTION_RATE, 0),
                       ISNULL(PROJECTION_REBATE_PER_UNIT, 0),
                       ISNULL(NET_CALCULATED_SALES, 0),
                       ISNULL(NET_UNITS, 0),
                       ISNULL(SALES_PROJECTED_VALUE, 0),
                       CALCULATION_TYPE,SUM_PROJECTION--------GAL-12531
                FROM   CONTRACT_DETAILS')

                      EXEC Sp_executesql
                        @SQL
						----GAL-12531
						IF EXISTS (SELECT *
                                   FROM   DBO.HELPER_TABLE
                                   WHERE  DESCRIPTION = 'TRANSACTION 3 - PIPELINE INVENTORY TRUE-UP'
                                          AND LIST_NAME = 'ARM_TRX_METHDOLOGY'
                                          AND @MODULE = DESCRIPTION)
                          BEGIN
                              SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                     RS_MODEL_SID,
                                     PERIOD_SID,
                                     PROJECTION_AMOUNT,
                                     SUM_PROJECTION*100 PROJECTION_RATE,
                                     PROJECTION_REBATE_PER_UNIT,
                                     NET_CALCULATED_SALES,
                                     NET_UNITS,
                                     SALES_PROJECTED_VALUE,
                                     CALCULATION_TYPE
                              FROM   #CONTRACT_DETAILS1
                          END 
                        IF EXISTS (SELECT *
                                   FROM   DBO.HELPER_TABLE
                                   WHERE  LIST_NAME = 'ARM_TRX_METHDOLOGY'
                                          AND DESCRIPTION = 'TRANSACTION 7 - DISTRIBUTION FEES'
                                          AND @MODULE = DESCRIPTION)
                          BEGIN
                              SELECT ARM_ADJUSTMENT_DETAILS_SID,
                                     RS_MODEL_SID,
                                     PERIOD_SID,
                                     PROJECTION_AMOUNT,
									 CASE WHEN CALCULATION_TYPE IS NULL THEN 0
                                           ELSE  SUM_PROJECTION*100 END PROJECTION_RATE,--------galuat-939
                                     ------PROJECTION_RATE,--------galuat-939
                                     PROJECTION_REBATE_PER_UNIT,
                                     NET_CALCULATED_SALES,
                                     NET_UNITS,
                                     SALES_PROJECTED_VALUE,
                                     CALCULATION_TYPE
                              FROM   #CONTRACT_DETAILS1
                          END 
                        
                  END
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