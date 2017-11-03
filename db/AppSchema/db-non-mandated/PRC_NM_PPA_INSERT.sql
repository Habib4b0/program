IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_PPA_INSERT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NM_PPA_INSERT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_NM_PPA_INSERT] (@PROJECTION_MASTER_SID INT,
                                            @USER_ID               INT,
                                            @SESSION_ID            VARCHAR(200))
AS
  /**********************************************************************************************************
  ** FILE NAME		:	PRC_NM_PPA_INSERT.SQL
  ** PROCEDURE NAME	:	PRC_NM_PPA_INSERT
  ** DESCRIPTION		:	The functionality of the procedure is, in a projection the items which were eligible for PPA will be inserted in to the respective session tables.
  ** INPUT PARAMETERS	:	@PROJECTION_SID	- passing input as PROJECTION_MASTER_SID
  						@USER_ID        - user id for the particular projection 
  						@SESSION_ID     - session id for the particular projection 
                          
  ** OUTPUT PARAMETERS:	na
  ** AUTHOR NAME		:	@SandeepKumar.A
  ** CREATION DATE	:	21/10/2015 - 
  ** CALLED BY		:   This procedure will be called from UI in the data selection screen.
  **********************************************************************************************************
  ** CHANGE HISTORY
  **********************************************************************************************************
  ** VER   DATE       LOCAL ALMTICKET NO      SUBTICKET NO        ONLINE ALM TICKET      AUTHOR                              DESCRIPTION 
  ** ---   --------      ---------             -------------        -------------     ----------------                       ------------------
  ** 1                                                                                   @SandeepKumar.A		               Implemented the logic as the request that is based on price tolerance type and table consists of ccp wise only.
  ** 2                                                                                   @SandeepKumar.A		               Implemented the netting logic
  ** 3                                                                  GALUAT-321       @SandeepKumar.A		               Implemented the logic in which if there were multiple rebates attached to the PS of Price protection rebate type then the tables should contains the ccp+rebate combinbation.
  ** 4                                                                  GAL-1163         @SandeepKumar.A                     Added RS_CONTRACT_SID column instead of RS_MODEL_SID .   
  ** 5                                                                  GALUAT-295       @SandeepKumar.A                     Actuals should be taken from actuals details so this logic was added in the insert procedure.
  ** 6                                                                  GALUAT-295       @pradeepthanga and @SandeepKumar.A  modified the procedure to improve the performance
  *****************************************************************************************************/
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          ---------------------------------------declare variable---------------
          DECLARE @ACTUAL_START_DATE DATETIME,
                  @CURRENT_QUARTER   DATETIME,
                  @FROM_DATE         DATETIME,
                  @TO_DATE           DATETIME,
                  @temp_table        VARCHAR(200)
          DECLARE @SQL              NVARCHAR(MAX),
                  @PROJECTION_TABLE VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @MASTER_TABLE     VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @ACTUAL_TABLE     VARCHAR(200) = Concat ('ST_NM_ACTUAL_PPA_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @CCP_HIERARCHY    VARCHAR(200) = Concat ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				  @D_MASTER_TABLE   VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

          SELECT @FROM_DATE = FROM_DATE,
                 @TO_DATE = TO_DATE
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

          SELECT @CURRENT_QUARTER = Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0) --CURRENT QUARTER FOR ACTULAS 
                 ,
                 @ACTUAL_START_DATE = Dateadd(YY, Datediff(YY, 0, Getdate()) - 3, 0)

          --taking the company contract and item information from the projection
          IF Object_id('TEMPDB.DBO.#PROJECTION_DATES', 'U') IS NOT NULL
            DROP TABLE #PROJECTION_DATES;

          CREATE TABLE #PROJECTION_DATES
            (
               CCP_DETAILS_SID     INT,
               CONTRACT_MASTER_SID INT,
               COMPANY_MASTER_SID  INT,
               ITEM_MASTER_SID     INT,
               PROJ_START_DATE     DATETIME,
               PROJ_END_DATE       DATETIME
            )

          --select * from ST_CCP_HIERARCHY_767497_024544441_161223 sc join CCP_DETAILS c on c.CCP_DETAILS_SID=sc.CCP_DETAILS_SID
          SET @SQL = Concat ('  INSERT INTO #PROJECTION_DATES
		  (
		  
		  CCP_DETAILS_SID        ,
		  CONTRACT_MASTER_SID    ,
		  COMPANY_MASTER_SID     ,
		  ITEM_MASTER_SID        ,
		  PROJ_START_DATE        ,
		  PROJ_END_DATE          
		  )

          SELECT 
                 CD.CCP_DETAILS_SID, 
                 CD.CONTRACT_MASTER_SID, 
                 CD.COMPANY_MASTER_SID, 
                 CD.ITEM_MASTER_SID,                 
                DATEADD(MM, DATEDIFF(MM, 0, @FROM_DATE), 0)  PROJ_START_DATE, 
                DATEADD(YYYY, DATEDIFF(YYYY, 0, @TO_DATE) + 1, 0) - 1  PROJ_END_DATE 
        
          FROM   ', @CCP_HIERARCHY, ' S 
                
                 JOIN CCP_DETAILS CD 
                   ON CD.CCP_DETAILS_SID = S.CCP_DETAILS_SID ')

          EXEC Sp_executesql
            @SQL,
            N'@FROM_DATE DATETIME,@TO_DATE DATETIME',
            @FROM_DATE = @FROM_DATE,
            @TO_DATE = @TO_DATE


----------------------------------checking the item information which were eligible for the PPA discount and taking the appropriate info from price schedule and reabte schedule, then storing in one temp table-------------------------------
IF Object_id('TEMPDB.DBO.#MASTR', 'U') IS NOT NULL
	DROP TABLE #MASTR;

CREATE TABLE #MASTR (
	CCP_DETAILS_SID INT
	,RS_CONTRACT_SID INT
	,RS_MODEL_SID INT
	,ITEM_PRICING_QUALIFIER_SID INT
	,NEP NUMERIC(22, 6)
	,NEP_FORMULA INT
	,MAX_INCREMENTAL_CHANGE NUMERIC(22, 6)
	,RESET_ELIGIBLE INT
	,RESET_TYPE INT
	,RESET_DATE DATETIME
	,RESET_INTERVAL INT
	,RESET_FREQUENCY INT
	,NET_PRICE_TYPE INT
	,NET_PRICE_TYPE_FORMULA INT
	,PRICE_TOLERANCE_INTERVAL INT
	,PRICE_TOLERANCE_FREQUENCY INT
	,PRICE_TOLERANCE_TYPE INT
	,PRICE_CAP NUMERIC(22, 6)
	,BASE_PRICE_TYPE INT
	,BASE_PRICE_MANUAL NUMERIC(22, 6)
	,BASE_PRICE_DATE DATETIME
	,BASE_PRICE_PRICE_TYPE INT
	,NET_BASE_PRICE INT
	,SUBSEQUENT_PERIOD_PRICE_TYPE INT
	,NET_SUBSEQUENT_PERIOD_PRICE INT
	,NET_SUBSEQUENT_PERIOD_PRICE_FORMULA INT
	,RESET_PRICE_TYPE INT
	,NET_RESET_PRICE_TYPE INT
	,NET_RESET_PRICE_FORMULA INT
	,ATTACHED_DATE DATETIME
	,NET_BASE_PRICE_FORMULA INT
	,PRICE_TOLERANCE NUMERIC(22, 6)
	,COMPANY_MASTER_SID INT
	,CONTRACT_MASTER_SID INT
	,ITEM_MASTER_SID INT
	,PRICE_PROTECTION_END_DATE DATETIME
	,PRICE_PROTECTION_STATUS INT
	,REBATE_FREQUENCY INT
	,RS_ID VARCHAR(100)
	,PRICE_PROTECTION_START_DATE DATETIME
	,PPA_INDEX_NO INT
	)
	
           SET @SQL =' INSERT INTO #MASTR (
	             CCP_DETAILS_SID,
                 PRICE_PROTECTION_STATUS,
                 PRICE_PROTECTION_START_DATE,
                 PRICE_PROTECTION_END_DATE,
                 ITEM_PRICING_QUALIFIER_SID,
                 NEP,
                 NEP_FORMULA,
                 BASE_PRICE_TYPE,
                 BASE_PRICE_MANUAL,
                 BASE_PRICE_DATE,
                 BASE_PRICE_PRICE_TYPE,
                 NET_BASE_PRICE,
                 NET_BASE_PRICE_FORMULA,
                 SUBSEQUENT_PERIOD_PRICE_TYPE,
                 NET_SUBSEQUENT_PERIOD_PRICE,
                 NET_SUBSEQUENT_PERIOD_PRICE_FORMULA,
                 PRICE_TOLERANCE_INTERVAL,
                 PRICE_TOLERANCE_FREQUENCY,
                 PRICE_TOLERANCE_TYPE,
                 PRICE_TOLERANCE,
                 MAX_INCREMENTAL_CHANGE,
                 RESET_ELIGIBLE,
                 RESET_TYPE,
                 RESET_DATE,
                 RESET_INTERVAL,
                 RESET_FREQUENCY,
                 RESET_PRICE_TYPE,
                 NET_RESET_PRICE_TYPE,
                 NET_RESET_PRICE_FORMULA,
                 NET_PRICE_TYPE,
                 NET_PRICE_TYPE_FORMULA,
                 ATTACHED_DATE,
                 RS_CONTRACT_SID,
                 RS_MODEL_SID,
                 RS_ID,
                 REBATE_FREQUENCY,
                 CONTRACT_MASTER_SID,
                 COMPANY_MASTER_SID,
                 ITEM_MASTER_SID,
				 PPA_INDEX_NO
				 ) 
				 SELECT CCP_DETAILS_SID,
                 PRICE_PROTECTION_STATUS,
                 Dateadd(MM, Datediff(MM, 0, PRICE_PROTECTION_START_DATE), 0) PRICE_PROTECTION_START_DATE,
                 COALESCE(DATEADD(MM, DATEDIFF(MM, 0, PRICE_PROTECTION_END_DATE) + 1, 0) - 1, LEAD(DATEADD(MM, DATEDIFF(MM, 0, PRICE_PROTECTION_START_DATE) - 1, 0)) OVER (
			PARTITION BY CCP_DETAILS_SID
			,RS_CONTRACT_SID ORDER BY PRICE_PROTECTION_START_DATE
			), DATEADD(MM, DATEDIFF(MM, 0, @TO_DATE) , 0) )   PRICE_PROTECTION_END_DATE,
                 NULLIF(ITEM_PRICING_QUALIFIER_SID, 0)                        ITEM_PRICING_QUALIFIER_SID,
                 NULLIF(NEP, 0)                                               NEP,
                 NULLIF(NEP_FORMULA, 0)                                       NEP_FORMULA,
                 BASE_PRICE_TYPE,
                 Isnull(BASE_PRICE_MANUAL, 0)                                 BASE_PRICE_MANUAL,
                 BASE_PRICE_DATE,
                 NULLIF(BASE_PRICE_PRICE_TYPE, 0)                             BASE_PRICE_PRICE_TYPE,
                 NET_BASE_PRICE,
                 NULLIF(NET_BASE_PRICE_FORMULA, 0)                            NET_BASE_PRICE_FORMULA,
                 NULLIF(SUBSEQUENT_PERIOD_PRICE_TYPE, 0)                      SUBSEQUENT_PERIOD_PRICE_TYPE,
                 NET_SUBSEQUENT_PERIOD_PRICE,
                 NULLIF(NET_SUBSEQUENT_PERIOD_PRICE_FORMULA, 0)               NET_SUBSEQUENT_PERIOD_PRICE_FORMULA,
                 NULLIF(PRICE_TOLERANCE_INTERVAL, 0)                          PRICE_TOLERANCE_INTERVAL,
                 NULLIF(PRICE_TOLERANCE_FREQUENCY, 0)                         PRICE_TOLERANCE_FREQUENCY,
                 NULLIF(PRICE_TOLERANCE_TYPE, 0)                              PRICE_TOLERANCE_TYPE,
                 Isnull(PRICE_TOLERANCE, 0)                                   PRICE_TOLERANCE,
                 MAX_INCREMENTAL_CHANGE,
                 NULLIF(RESET_ELIGIBLE, 0)                                    RESET_ELIGIBLE,
                 NULLIF(RESET_TYPE, 0)                                        RESET_TYPE,
                 NULLIF(RESET_DATE, 0)                                        RESET_DATE,
                 NULLIF(RESET_INTERVAL, 0)                                    RESET_INTERVAL,
                 NULLIF(RESET_FREQUENCY, 0)                                   RESET_FREQUENCY,
                 NULLIF(RESET_PRICE_TYPE, 0)                                  RESET_PRICE_TYPE,
                 NET_RESET_PRICE_TYPE,
                 NULLIF(NET_RESET_PRICE_FORMULA, 0)                           NET_RESET_PRICE_FORMULA,
                 NULLIF(NET_PRICE_TYPE, 0)                                    NET_PRICE_TYPE,
                 NULLIF(NET_PRICE_TYPE_FORMULA, 0)                            NET_PRICE_TYPE_FORMULA,
                 ATTACHED_DATE,
                 RS_CONTRACT_SID,
                 RS_MODEL_SID,
                 RS_ID,
                 REBATE_FREQUENCY,
                 CONTRACT_MASTER_SID,
                 COMPANY_MASTER_SID,
                 ITEM_MASTER_SID,PPA_INDEX_NO
          FROM (SELECT CCP.CCP_DETAILS_SID,
                         PSCD.PRICE_PROTECTION_START_DATE                                                                                                                                     PRICE_PROTECTION_START_DATE,
                         PSCD.PRICE_PROTECTION_END_DATE,
                         PSCD.PRICE_PROTECTION_STATUS,
                         PSCD.PRICE_PROTECTION_PRICE_TYPE                                                                                                                                     ITEM_PRICING_QUALIFIER_SID,
                         PSCD.NEP,
                         PSCD.NEP_FORMULA                                                                                                                                                     NEP_FORMULA,
                         PSCD.BASE_PRICE_TYPE,
                         PSCD.BASE_PRICE_ENTRY                                                                                                                                                BASE_PRICE_MANUAL,
                         PSCD.BASE_PRICE_DATE,
                         PSCD.BASE_PRICE_DDLB                                                                                                                                                 BASE_PRICE_PRICE_TYPE,
                         PSCD.NET_BASE_PRICE,
                         PSCD.NET_BASE_PRICE_FORMULA_ID                                                                                                                                       NET_BASE_PRICE_FORMULA,
                         PSCD.SUBSEQUENT_PERIOD_PRICE_TYPE                                                                                                                                    SUBSEQUENT_PERIOD_PRICE_TYPE,
                         PSCD.NET_SUBSEQUENT_PERIOD_PRICE                                                                                                                                     NET_SUBSEQUENT_PERIOD_PRICE,
                         PSCD.NET_SUBSEQUENT_PRICE_FORMULA_ID                                                                                                                                 NET_SUBSEQUENT_PERIOD_PRICE_FORMULA,
                         PSCD.PRICE_TOLERANCE_INTERVAL                                                                                                                                        PRICE_TOLERANCE_INTERVAL,
                         PSCD.PRICE_TOLERANCE_FREQUENCY                                                                                                                                       PRICE_TOLERANCE_FREQUENCY,
                         PSCD.PRICE_TOLERANCE_TYPE,
                         PSCD.PRICE_TOLERANCE,
                         PSCD.MAX_INCREMENTAL_CHANGE,
                         PSCD.RESET_ELIGIBLE                                                                                                                                                  RESET_ELIGIBLE,
                         PSCD.RESET_TYPE                                                                                                                                                      RESET_TYPE,
                         PSCD.RESET_DATE,
                         PSCD.RESET_INTERVAL                                                                                                                                                  RESET_INTERVAL,
                         PSCD.RESET_FREQUENCY                                                                                                                                                 RESET_FREQUENCY,
                         PSCD.RESET_PRICE_TYPE                                                                                                                                                RESET_PRICE_TYPE,
                         PSCD.NET_RESET_PRICE_TYPE                                                                                                                                            NET_RESET_PRICE_TYPE,
                         PSCD.NET_RESET_PRICE_FORMULA_ID                                                                                                                                      NET_RESET_PRICE_FORMULA,
                         PSCD.NET_PRICE_TYPE,
                         PSCD.NET_PRICE_TYPE_FORMULA                                                                                                                                          NET_PRICE_TYPE_FORMULA,
                         PSCD.PS_CONTRACT_ATTACHED_DATE                                                                                                                                       ATTACHED_DATE,
                         RC.RS_CONTRACT_SID,
                         RC.RS_MODEL_SID,
                         RC.RS_ID,
                         RC.REBATE_FREQUENCY,
                         CCP.CONTRACT_MASTER_SID,
                         CCP.COMPANY_MASTER_SID,
                         CCP.ITEM_MASTER_SID,
                         Row_number()
                           OVER (
                             PARTITION BY CCP.CCP_DETAILS_SID, CCP.CONTRACT_MASTER_SID, PSCD.PS_CONTRACT_SID, RC.RS_CONTRACT_SID,PPA_INDEX_NO
                             ORDER BY CCP.CCP_DETAILS_SID, CCP.CONTRACT_MASTER_SID, PSCD.PS_CONTRACT_SID, RC.RS_CONTRACT_SID, PSCD.CREATED_DATE DESC,PPA_INDEX_NO ) RN,
							PPA_INDEX_NO
                  FROM   #PROJECTION_DATES CCP
                         JOIN '+ @D_MASTER_TABLE+ ' DM ON DM.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID 
						 JOIN RS_CONTRACT RC
                           ON RC.RS_CONTRACT_SID = DM.RS_CONTRACT_SID
                              AND RC.INBOUND_STATUS <> ''D''
						 JOIN HELPER_TABLE HT
                                ON HT.HELPER_TABLE_SID = RC.CALCULATION_TYPE
								AND HT.DESCRIPTION = ''PRICE PROTECTION''
                         JOIN PS_CONTRACT_DETAILS PSCD
                           ON PSCD.PS_CONTRACT_SID = RC.PS_CONTRACT_SID
                              AND PSCD.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID
                              AND PRICE_PROTECTION_START_DATE IS NOT NULL
                              AND PSCD.INBOUND_STATUS <> ''D''
                         JOIN HELPER_TABLE HT1
                                ON HT1.HELPER_TABLE_SID = PSCD.PRICE_PROTECTION_STATUS
								AND HT1.DESCRIPTION = ''ACTIVE''
						 JOIN HELPER_TABLE HT2
                                ON HT2.HELPER_TABLE_SID = RC.REBATE_FREQUENCY)A
          WHERE  RN = 1'
		  EXEC SP_EXECUTESQL  @SQL ,N'@TO_DATE DATETIME',@TO_DATE = @TO_DATE
		
		 --select ST_NM_PPA_PROJECTION_10948_042446519_171011
          --------------------------------based on the ccp's eligibility the ccps and rebates were stored in ppa master table-------------------------------------------------------
          SET @SQL = Concat ('TRUNCATE TABLE ', @MASTER_TABLE)

          EXEC Sp_executesql
            @SQL

          SET @SQL = CONCAT (' INSERT INTO ', @MASTER_TABLE, ' 
                      (CCP_DETAILS_SID, 
                       PRICE_PROTECTION_STATUS, 
                       PRICE_PROTECTION_START_DATE, 
                       PRICE_PROTECTION_END_DATE, 
                       ACTUAL_PRICE_CAP, 
                       PRICE_BASIS, 
                       CHECK_RECORD, 
                       USER_GROUP, 
                       RS_MODEL_SID, 
                       REBATE_FREQUENCY,
					   RS_CONTRACT_SID,
					   PPA_INDEX_NO) 
          SELECT NPPM.CCP_DETAILS_SID, 
                 NPPM.PRICE_PROTECTION_STATUS, 
                 NPPM.PRICE_PROTECTION_START_DATE,
				  PRICE_PROTECTION_END_DATE,
                 NPPM.PRICE_TOLERANCE  ACTUAL_PRICE_CAP, 
                 IPQ.PRICING_QUALIFIER PRICE_BASIS, 
                 ''0''                   CHECK_RECORD, 
                 0                     AS USER_GROUP, 
                 NPPM.RS_MODEL_SID, 
                 NPPM.REBATE_FREQUENCY,
				 NPPM.RS_CONTRACT_SID,
				 ROW_NUMBER () OVER (PARTITION BY NPPM.CCP_DETAILS_SID,NPPM.RS_CONTRACT_SID ORDER BY NPPM.PRICE_PROTECTION_START_DATE) PPA_INDEX_NO
          FROM   #MASTR NPPM 
                 LEFT JOIN ITEM_PRICING_QUALIFIER IPQ 
                        ON IPQ.ITEM_PRICING_QUALIFIER_SID = NPPM.ITEM_PRICING_QUALIFIER_SID ')

          EXEC Sp_executesql  @SQL
		   
 
     --     DECLARE @ACTUALS_CCPD UDT_CCP_DETAILS

     --     -------------------------------------------INSERT INTO NM_ACTUAL_PPA STARTS HERE-------------------------------------------------------------- 
     --     SET @SQL = Concat ('SELECT C.CONTRACT_MASTER_SID, 
     --            C.COMPANY_MASTER_SID, 
     --            C.ITEM_MASTER_SID, 
     --            D.CCP_DETAILS_SID, 
     --            RM.RS_ID 
     --     FROM   CCP_DETAILS C 
                   
     --            JOIN ', @MASTER_TABLE, ' D 
     --              ON D.CCP_DETAILS_SID = C.CCP_DETAILS_SID 
     --            JOIN RS_CONTRACT RM 
     --              ON RM.RS_CONTRACT_SID = D.RS_CONTRACT_SID 
     --     WHERE   RM.INBOUND_STATUS <> ''D'' ')

     --     INSERT INTO @ACTUALS_CCPD
     --                 (CONTRACT_MASTER_SID,
     --                  COMPANY_MASTER_SID,
     --                  ITEM_MASTER_SID,
     --                  PROJECTION_DETAILS_SID,
     --                  DISCOUNT_ID)
     --     EXEC Sp_executesql
     --       @SQL

     --     ------------------------the actuals for the eligible ccp will be taken from udf actual details table------------------------------------------------
     --     SET @SQL = Concat ('TRUNCATE TABLE ', @ACTUAL_TABLE)

     --     EXEC Sp_executesql
     --       @SQL

     --     SET @SQL = Concat ('  INSERT INTO ', @ACTUAL_TABLE, '
     --                 (CCP_DETAILS_SID, 
     --                  PERIOD_SID, 
     --                 -- RS_MODEL_SID, 
     --                  ACTUAL_RATE, 
     --                  ACTUAL_SALES, 
     --                  ACTUAL_DISCOUNT_DOLLAR, 
     --                  ACTUAL_DISCOUNT_UNITS,
					--   RS_CONTRACT_SID
     --                 ) 
         
     --     SELECT NDPM.CCP_DETAILS_SID, 
     --            PERIOD_SID, 
     --            --NDPM.RS_MODEL_SID, 
     --            ISNULL(( MAX(DISCOUNT) ) / NULLIF(MAX(SALES), 0), 0) * 100 ACTUAL_RATE, 
     --            ISNULL(( MAX(DISCOUNT) ) / NULLIF(MAX(QUANTITY), 0), 0)    ACTUAL_RPU, 
     --            ISNULL(MAX(DISCOUNT), 0)                                   ACTUAL_AMOUNT, 
     --            ISNULL(MAX(QUANTITY), 0)                                   ACTUAL_UNITS,
				 --NDPM.RS_CONTRACT_SID
                  
     --     FROM   [DBO].[UDF_ACTUALS_DETAILS](@ACTUALS_CCPD, ''', @CURRENT_QUARTER, ''',', '''', @ACTUAL_START_DATE, ''') A 
     --            --  JOIN #NM_PPA_PROJECTION_MASTER NDPM ON NDPM.CONTRACT_MASTER_SID = A.CONTRACT_MASTER_SID 
     --            JOIN #MASTR NDPM 
     --              ON NDPM.CONTRACT_MASTER_SID = A.CONTRACT_MASTER_SID 
     --                 AND NDPM.COMPANY_MASTER_SID = A.COMPANY_MASTER_SID 
     --                 AND NDPM.ITEM_MASTER_SID = A.ITEM_MASTER_SID 
     --                 AND NDPM.RS_ID = A.DISCOUNT_ID 
     --     GROUP  BY NDPM.CCP_DETAILS_SID, 
     --               PERIOD_SID, 
     --               NDPM.RS_CONTRACT_SID ')

     --     EXEC Sp_executesql
     --       @SQL,
     --       N'@ACTUALS_CCPD UDT_CCP_DETAILS READONLY',
     --       @ACTUALS_CCPD = @ACTUALS_CCPD

     --     SET @SQL = Concat ('  DELETE N 
     --     FROM   ', @PROJECTION_TABLE, ' N 
     --     WHERE  EXISTS (SELECT 1 
     --                        FROM   ', @MASTER_TABLE, ' T 
     --                        WHERE N.CCP_DETAILS_SID = T.CCP_DETAILS_SID )
                              
     --                                ')

     --     EXEC Sp_executesql
     --       @SQL

          SET @SQL = Concat ('TRUNCATE TABLE ', @PROJECTION_TABLE)

          EXEC Sp_executesql
            @SQL

          ----------------------------------ccp , rs_contract and period wise data will be store in the projection table-------------------------------------------------------------
          --------------EDIT MODE PLUGIN ENDS HERE ------------ 
          SET @SQL = Concat ('  INSERT INTO ', @PROJECTION_TABLE, ' 
                      (CCP_DETAILS_SID, 
                       PERIOD_SID, 
					   RS_CONTRACT_SID,
                       RS_MODEL_SID, 
                       ITEM_PRICING_QUALIFIER_SID, 
                       NEP, 
                       NEP_FORMULA, 
                       MAX_INCREMENTAL_CHANGE, 
                       RESET_ELIGIBLE, 
                       RESET_TYPE, 
                       RESET_DATE, 
                       RESET_INTERVAL, 
                       RESET_FREQUENCY, 
                       NET_PRICE_TYPE, 
                       NET_PRICE_TYPE_FORMULA, 
                       PRICE_TOLERANCE_INTERVAL, 
                       PRICE_TOLERANCE_FREQUENCY, 
                       PRICE_TOLERANCE_TYPE, 
                       PRICE_CAP, 
                       BASE_PRICE_TYPE, 
                       BASE_PRICE_MANUAL, 
                       BASE_PRICE_DATE, 
                       BASE_PRICE_PRICE_TYPE, 
                       NET_BASE_PRICE, 
                       SUBSEQUENT_PERIOD_PRICE_TYPE, 
                       NET_SUBSEQUENT_PERIOD_PRICE, 
                       NET_SUBSEQUENT_PERIOD_PRICE_FORMULA, 
                       RESET_PRICE_TYPE, 
                       NET_RESET_PRICE_TYPE, 
                       NET_RESET_PRICE_FORMULA, 
                       ATTACHED_DATE, 
                       NET_BASE_PRICE_FORMULA, 
                       PRICE_TOLERANCE 

                       ) 
          SELECT NPP.CCP_DETAILS_SID, 
                 A.PERIOD_SID, 
				 NPP.RS_CONTRACT_SID, 
                 NPP.RS_MODEL_SID, 
                 NPP.ITEM_PRICING_QUALIFIER_SID          ITEM_PRICING_QUALIFIER_SID, 
                 NPP.NEP                                 NEP, 
                 NPP.NEP_FORMULA                         NEP_FORMULA, 
                 NPP.MAX_INCREMENTAL_CHANGE, 
                 NPP.RESET_ELIGIBLE                      RESET_ELIGIBLE, 
                 NPP.RESET_TYPE                          RESET_TYPE, 
                 NPP.RESET_DATE                          RESET_DATE, 
                 NPP.RESET_INTERVAL                      RESET_INTERVAL, 
                 NPP.RESET_FREQUENCY                     RESET_FREQUENCY, 
                 NPP.NET_PRICE_TYPE                      NET_PRICE_TYPE, 
                 NPP.NET_PRICE_TYPE_FORMULA              NET_PRICE_TYPE_FORMULA, 
                 NPP.PRICE_TOLERANCE_INTERVAL            PRICE_TOLERANCE_INTERVAL, 
                 NPP.PRICE_TOLERANCE_FREQUENCY           PRICE_TOLERANCE_FREQUENCY, 
                 NPP.PRICE_TOLERANCE_TYPE                PRICE_TOLERANCE_TYPE , 
                 ISNULL(NPP.PRICE_TOLERANCE, 0)          PRICE_CAP, 
                 NPP.BASE_PRICE_TYPE, 
                 ISNULL(NPP.BASE_PRICE_MANUAL, 0)        BASE_PRICE_MANUAL, 
                 NPP.BASE_PRICE_DATE, 
                 NPP.BASE_PRICE_PRICE_TYPE               BASE_PRICE_PRICE_TYPE, 
                 NPP.NET_BASE_PRICE, 
                 NPP.SUBSEQUENT_PERIOD_PRICE_TYPE        SUBSEQUENT_PERIOD_PRICE_TYPE, 
                 NPP.NET_SUBSEQUENT_PERIOD_PRICE         NET_SUBSEQUENT_PERIOD_PRICE, 
                 NPP.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA NET_SUBSEQUENT_PERIOD_PRICE_FORMULA, 
                 NPP.RESET_PRICE_TYPE                    RESET_PRICE_TYPE, 
                 NPP.NET_RESET_PRICE_TYPE                NET_RESET_PRICE_TYPE, 
                 NPP.NET_RESET_PRICE_FORMULA             NET_RESET_PRICE_FORMULA, 
                 NPP.ATTACHED_DATE, 
                 NPP.NET_BASE_PRICE_FORMULA              NET_BASE_PRICE_FORMULA, 
                 ISNULL(NPP.PRICE_TOLERANCE, 0)          PRICE_TOLERANCE
          FROM   #MASTR NPP 
                 CROSS JOIN PERIOD A 
          WHERE  PERIOD_DATE BETWEEN PRICE_PROTECTION_START_DATE AND PRICE_PROTECTION_END_DATE')

		 
          EXEC Sp_executesql
            @SQL

          DECLARE @CONTRACT_SETUP VARCHAR(200) = Concat ('ST_CONTRACT_SETUP_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

          SET @SQL= Concat('
            IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME =''', @CONTRACT_SETUP, '''
                      AND TABLE_SCHEMA = ''DBO'')
					  BEGIN
			   DROP TABLE ', @CONTRACT_SETUP, ' 
       END
           CREATE TABLE ', @CONTRACT_SETUP, '
                   (CCP_DETAILS_SID int, 
					   RS_CONTRACT_SID int,
                       RS_MODEL_SID int,  
                       ITEM_PRICING_QUALIFIER_SID int, 
                       NEP numeric(22,6), 
                       NEP_FORMULA int, 
                       MAX_INCREMENTAL_CHANGE numeric(22,6), 
                       RESET_ELIGIBLE int, 
                       RESET_TYPE int, 
                       RESET_DATE datetime, 
                       RESET_INTERVAL int, 
                       RESET_FREQUENCY int, 
                       NET_PRICE_TYPE int, 
                       NET_PRICE_TYPE_FORMULA int, 
                       PRICE_TOLERANCE_INTERVAL int, 
                       PRICE_TOLERANCE_FREQUENCY int, 
                       PRICE_TOLERANCE_TYPE int, 
                       PRICE_CAP numeric(22,6), 
                       BASE_PRICE_TYPE int, 
                       BASE_PRICE_MANUAL numeric(22,6), 
                       BASE_PRICE_DATE datetime, 
                       BASE_PRICE_PRICE_TYPE int, 
                       NET_BASE_PRICE int, 
                       SUBSEQUENT_PERIOD_PRICE_TYPE int, 
                       NET_SUBSEQUENT_PERIOD_PRICE int, 
                       NET_SUBSEQUENT_PERIOD_PRICE_FORMULA int, 
                       RESET_PRICE_TYPE int, 
                       NET_RESET_PRICE_TYPE int, 
                       NET_RESET_PRICE_FORMULA int, 
                       ATTACHED_DATE datetime, 
                       NET_BASE_PRICE_FORMULA int, 
                       PRICE_TOLERANCE numeric(22,6)
					   ,PPA_INDEX_NO INT)')

					   exec sp_executesql @sql

          SET @SQL = Concat ('  INSERT INTO ', @CONTRACT_SETUP, ' 
                      (CCP_DETAILS_SID, 
					   RS_CONTRACT_SID,
                       RS_MODEL_SID, 
                       ITEM_PRICING_QUALIFIER_SID, 
                       NEP, 
                       NEP_FORMULA, 
                       MAX_INCREMENTAL_CHANGE, 
                       RESET_ELIGIBLE, 
                       RESET_TYPE, 
                       RESET_DATE, 
                       RESET_INTERVAL, 
                       RESET_FREQUENCY, 
                       NET_PRICE_TYPE, 
                       NET_PRICE_TYPE_FORMULA, 
                       PRICE_TOLERANCE_INTERVAL, 
                       PRICE_TOLERANCE_FREQUENCY, 
                       PRICE_TOLERANCE_TYPE, 
                       PRICE_CAP, 
                       BASE_PRICE_TYPE, 
                       BASE_PRICE_MANUAL, 
                       BASE_PRICE_DATE, 
                       BASE_PRICE_PRICE_TYPE, 
                       NET_BASE_PRICE, 
                       SUBSEQUENT_PERIOD_PRICE_TYPE, 
                       NET_SUBSEQUENT_PERIOD_PRICE, 
                       NET_SUBSEQUENT_PERIOD_PRICE_FORMULA, 
                       RESET_PRICE_TYPE, 
                       NET_RESET_PRICE_TYPE, 
                       NET_RESET_PRICE_FORMULA, 
                       ATTACHED_DATE, 
                       NET_BASE_PRICE_FORMULA, 
                       PRICE_TOLERANCE,PPA_INDEX_NO 
                       ) 
          SELECT NPP.CCP_DETAILS_SID, 
				 NPP.RS_CONTRACT_SID, 
                 NPP.RS_MODEL_SID, 
                 NPP.ITEM_PRICING_QUALIFIER_SID          ITEM_PRICING_QUALIFIER_SID, 
                 NPP.NEP                                 NEP, 
                 NPP.NEP_FORMULA                         NEP_FORMULA, 
                 NPP.MAX_INCREMENTAL_CHANGE, 
                 NPP.RESET_ELIGIBLE                      RESET_ELIGIBLE, 
                 NPP.RESET_TYPE                          RESET_TYPE, 
                 NPP.RESET_DATE                          RESET_DATE, 
                 NPP.RESET_INTERVAL                      RESET_INTERVAL, 
                 NPP.RESET_FREQUENCY                     RESET_FREQUENCY, 
                 NPP.NET_PRICE_TYPE                      NET_PRICE_TYPE, 
                 NPP.NET_PRICE_TYPE_FORMULA              NET_PRICE_TYPE_FORMULA, 
                 NPP.PRICE_TOLERANCE_INTERVAL            PRICE_TOLERANCE_INTERVAL, 
                 NPP.PRICE_TOLERANCE_FREQUENCY           PRICE_TOLERANCE_FREQUENCY, 
                 NPP.PRICE_TOLERANCE_TYPE                PRICE_TOLERANCE_TYPE , 
                 ISNULL(NPP.PRICE_TOLERANCE, 0)          PRICE_CAP, 
                 NPP.BASE_PRICE_TYPE, 
                 ISNULL(NPP.BASE_PRICE_MANUAL, 0)        BASE_PRICE_MANUAL, 
                 NPP.BASE_PRICE_DATE, 
                 NPP.BASE_PRICE_PRICE_TYPE               BASE_PRICE_PRICE_TYPE, 
                 NPP.NET_BASE_PRICE, 
                 NPP.SUBSEQUENT_PERIOD_PRICE_TYPE        SUBSEQUENT_PERIOD_PRICE_TYPE, 
                 NPP.NET_SUBSEQUENT_PERIOD_PRICE         NET_SUBSEQUENT_PERIOD_PRICE, 
                 NPP.NET_SUBSEQUENT_PERIOD_PRICE_FORMULA NET_SUBSEQUENT_PERIOD_PRICE_FORMULA, 
                 NPP.RESET_PRICE_TYPE                    RESET_PRICE_TYPE, 
                 NPP.NET_RESET_PRICE_TYPE                NET_RESET_PRICE_TYPE, 
                 NPP.NET_RESET_PRICE_FORMULA             NET_RESET_PRICE_FORMULA, 
                 NPP.ATTACHED_DATE, 
                 NPP.NET_BASE_PRICE_FORMULA              NET_BASE_PRICE_FORMULA, 
                 ISNULL(NPP.PRICE_TOLERANCE, 0)          PRICE_TOLERANCE,
				 PPA_INDEX_NO
          FROM   #MASTR NPP')

          EXEC Sp_executesql
            @SQL
     
      -----------------------------due to the performance activity the following temp tables were created-------------------------------------------------------
      --		SET @TEMP_TABLE = CONCAT (
      --				'ST_PPA_MAP_WO_NETTING_VD_Q'
      --				,'_'
      --				,@USER_ID
      --				,'_'
      --				,@SESSION_ID
      --				,'_'
      --				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
      --				)
      --		SET @sql = CONCAT (
      --				'CREATE TABLE '
      --				,@TEMP_TABLE
      --				,' (
      --	CCP_DETAILS_SID INT
      --	,RS_CONTRACT_SID INT
      --	,PERIOD_SID INT
      --	,WAC NUMERIC(22, 6)
      --	,NET_WAC NUMERIC(22, 6)
      --	,NET_MAP NUMERIC(22, 6)
      --	,PPA NUMERIC(22, 6)
      --	,PRICE_CHANGE NUMERIC(22, 6)
      --	,RESET BIT
      --	,WAC_RESET BIT
      --	,PRIMARY KEY (
      --		CCP_DETAILS_SID
      --		,RS_CONTRACT_SID
      --		,PERIOD_SID
      --		)
      --	) 
      -- '
      --				);
      --		EXEC sp_executesql @SQL 
      --		--SELECT @sql
      --		SET @TEMP_TABLE = CONCAT (
      --				'ST_PPA_MAP_WO_NETTING_VD_M'
      --				,'_'
      --				,@USER_ID
      --				,'_'
      --				,@SESSION_ID
      --				,'_'
      --				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
      --				)
      --		SET @sql = CONCAT (
      --				'CREATE TABLE '
      --				,@TEMP_TABLE
      --				,' (
      --	CCP_DETAILS_SID INT
      --	,RS_CONTRACT_SID INT
      --	,PERIOD_SID INT
      --	,WAC NUMERIC(22, 6)
      --	,NET_WAC NUMERIC(22, 6)
      --	,NET_MAP NUMERIC(22, 6)
      --	,PPA NUMERIC(22, 6)
      --	,PRICE_CHANGE NUMERIC(22, 6)
      --	,RESET BIT
      --	,WAC_RESET BIT
      --	,PRIMARY KEY (
      --		CCP_DETAILS_SID
      --		,RS_CONTRACT_SID
      --		,PERIOD_SID
      --		)
      --	) 
      -- '
      --				);
      --		EXEC sp_executesql @SQL
      --		--SELECT @sql
      --		SET @TEMP_TABLE = CONCAT (
      --				'ST_PPA_MAP_WO_NETTING_VD_S'
      --				,'_'
      --				,@USER_ID
      --				,'_'
      --				,@SESSION_ID
      --				,'_'
      --				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
      --				)
      --		SET @sql = CONCAT (
      --				'CREATE TABLE '
      --				,@TEMP_TABLE
      --				,' (
      --	CCP_DETAILS_SID INT
      --	,RS_CONTRACT_SID INT
      --	,PERIOD_SID INT
      --	,WAC NUMERIC(22, 6)
      --	,NET_WAC NUMERIC(22, 6)
      --	,NET_MAP NUMERIC(22, 6)
      --	,PPA NUMERIC(22, 6)
      --	,PRICE_CHANGE NUMERIC(22, 6)
      --	,RESET BIT
      --	,WAC_RESET BIT
      --	,PRIMARY KEY (
      --		CCP_DETAILS_SID
      --		,RS_CONTRACT_SID
      --		,PERIOD_SID
      --		)
      --	) 
      -- '
      --				);
      --		EXEC sp_executesql @SQL
      --		--SELECT @sql
      --		SET @TEMP_TABLE = CONCAT (
      --				'ST_PPA_MAP_WO_NETTING_VD_A'
      --				,'_'
      --				,@USER_ID
      --				,'_'
      --				,@SESSION_ID
      --				,'_'
      --				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
      --				)
      --		SET @sql = CONCAT (
      --				'CREATE TABLE '
      --				,@TEMP_TABLE
      --				,' (
      --	CCP_DETAILS_SID INT
      --	,RS_CONTRACT_SID INT
      --	,PERIOD_SID INT
      --	,WAC NUMERIC(22, 6)
      --	,NET_WAC NUMERIC(22, 6)
      --	,NET_MAP NUMERIC(22, 6)
      --	,PPA NUMERIC(22, 6)
      --	,PRICE_CHANGE NUMERIC(22, 6)
      --	,RESET BIT
      --	,WAC_RESET BIT
      --	,PRIMARY KEY (
      --		CCP_DETAILS_SID
      --		,RS_CONTRACT_SID
      --		,PERIOD_SID
      --		)
      --	) 
      -- '
      --				);
      --		EXEC sp_executesql @SQL
      --		--SELECT @sql
      --		SET @TEMP_TABLE = CONCAT (
      --				'ST_PPA_MAP_WO_NETTING_Q'
      --				,'_'
      --				,@USER_ID
      --				,'_'
      --				,@SESSION_ID
      --				,'_'
      --				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
      --				)
      --		SET @sql = CONCAT (
      --				'CREATE TABLE '
      --				,@TEMP_TABLE
      --				,' (
      --	CCP_DETAILS_SID INT
      --	,RS_CONTRACT_SID INT
      --	,PERIOD_SID INT
      --	,WAC NUMERIC(22, 6)
      --	,NET_WAC NUMERIC(22, 6)
      --	,NET_MAP NUMERIC(22, 6)
      --	,PPA NUMERIC(22, 6)
      --	,PRICE_CHANGE NUMERIC(22, 6)
      --	,RESET BIT
      --	,WAC_RESET BIT
      --	,PRIMARY KEY (
      --		CCP_DETAILS_SID
      --		,RS_CONTRACT_SID
      --		,PERIOD_SID
      --		)
      --	) 
      -- '
      --				);
      --		EXEC sp_executesql @SQL
      --		--SELECT @sql
      --		SET @TEMP_TABLE = CONCAT (
      --				'ST_PPA_MAP_WO_NETTING_M'
      --				,'_'
      --				,@USER_ID
      --				,'_'
      --				,@SESSION_ID
      --				,'_'
      --				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
      --				)
      --		SET @sql = CONCAT (
      --				'CREATE TABLE '
      --				,@TEMP_TABLE
      --				,' (
      --	CCP_DETAILS_SID INT
      --	,RS_CONTRACT_SID INT
      --	,PERIOD_SID INT
      --	,WAC NUMERIC(22, 6)
      --	,NET_WAC NUMERIC(22, 6)
      --	,NET_MAP NUMERIC(22, 6)
      --	,PPA NUMERIC(22, 6)
      --	,PRICE_CHANGE NUMERIC(22, 6)
      --	,RESET BIT
      --	,WAC_RESET BIT
      --	,PRIMARY KEY (
      --		CCP_DETAILS_SID
      --		,RS_CONTRACT_SID
      --		,PERIOD_SID
      --		)
      --	) 
      -- '
      --				);
      --		EXEC sp_executesql @SQL
      --		--------select @sql
      --		SET @TEMP_TABLE = CONCAT (
      --				'ST_PPA_MAP_WO_NETTING_S'
      --				,'_'
      --				,@USER_ID
      --				,'_'
      --				,@SESSION_ID
      --				,'_'
      --				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
      --				)
      --		SET @sql = CONCAT (
      --				'CREATE TABLE '
      --				,@TEMP_TABLE
      --				,' (
      --	CCP_DETAILS_SID INT
      --	,RS_CONTRACT_SID INT
      --	,PERIOD_SID INT
      --	,WAC NUMERIC(22, 6)
      --	,NET_WAC NUMERIC(22, 6)
      --	,NET_MAP NUMERIC(22, 6)
      --	,PPA NUMERIC(22, 6)
      --	,PRICE_CHANGE NUMERIC(22, 6)
      --	,RESET BIT
      --	,WAC_RESET BIT
      --	,PRIMARY KEY (
      --		CCP_DETAILS_SID
      --		,RS_CONTRACT_SID
      --		,PERIOD_SID
      --		)
      --	) 
      -- '
      --				);
      --		EXEC sp_executesql @SQL
      --		--------select @sql
      --		SET @TEMP_TABLE = CONCAT (
      --				'ST_PPA_MAP_WO_NETTING_A'
      --				,'_'
      --				,@USER_ID
      --				,'_'
      --				,@SESSION_ID
      --				,'_'
      --				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
      --				)
      --		SET @sql = CONCAT (
      --				'CREATE TABLE '
      --				,@TEMP_TABLE
      --				,' (
      --	CCP_DETAILS_SID INT
      --	,RS_CONTRACT_SID INT
      --	,PERIOD_SID INT
      --	,WAC NUMERIC(22, 6)
      --	,NET_WAC NUMERIC(22, 6)
      --	,NET_MAP NUMERIC(22, 6)
      --	,PPA NUMERIC(22, 6)
      --	,PRICE_CHANGE NUMERIC(22, 6)
      --	,RESET BIT
      --	,WAC_RESET BIT
      --	,PRIMARY KEY (
      --		CCP_DETAILS_SID
      --		,RS_CONTRACT_SID
      --		,PERIOD_SID
      --		)
      --	) 
      -- '
      --				);
      --		EXEC sp_executesql @SQL
      --		--------select @sql
      --		SET @TEMP_TABLE = CONCAT (
      --				'ST_PPA_MAP_NETTING'
      --				,'_'
      --				,@USER_ID
      --				,'_'
      --				,@SESSION_ID
      --				,'_'
      --				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
      --				)
      --		SET @sql = CONCAT (
      --				'CREATE TABLE '
      --				,@TEMP_TABLE
      --				,' (
      --	CCP_DETAILS_SID INT
      --	,RS_CONTRACT_SID INT
      --	,PERIOD_SID INT
      --	,PROJECTION_RATE NUMERIC(22, 6)
      --	,PROJECTION_SALES NUMERIC(22, 6)
      --	,PROJECTION_DISCOUNT_DOLLAR NUMERIC(22, 6)
      --	,PROJECTION_DISCOUNT_UNITS NUMERIC(22, 6)
      --	,PROJECTION_MAP NUMERIC(22, 6)
      --	,PRICE NUMERIC(22, 6)
      --	,TOTALDEDUCTIONS NUMERIC(22, 6)
      --	,NETPRICE NUMERIC(22, 6)
      --	,NETMAP NUMERIC(22, 6)
      --	,PRICEPROTECTIONAMOUNTPERUNIT NUMERIC(22, 6)
      --	,PRICEPROTECTIONPERCENTAGE NUMERIC(22, 6)
      --	,TOTALPRICEPROTECTIONDEDUCTION NUMERIC(22, 6)
      --	,DEDUCTION_PER_UNIT NUMERIC(22, 6)
      --	,RESET BIT
      --	,PRICECHANGE NUMERIC(22, 6)
      --	,PRIMARY KEY (
      --		CCP_DETAILS_SID
      --		,RS_CONTRACT_SID
      --		,PERIOD_SID
      --		)
      --	) 
      -- '
      --				);
      --		EXEC sp_executesql @SQL
      --		--------select @sql
      --		SET @TEMP_TABLE = CONCAT (
      --				'ST_PPA_MAP_NETTING_VIOLATION'
      --				,'_'
      --				,@USER_ID
      --				,'_'
      --				,@SESSION_ID
      --				,'_'
      --				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
      --				)
      --		SET @sql = CONCAT (
      --				'CREATE TABLE '
      --				,@TEMP_TABLE
      --				,' (
      --	CCP_DETAILS_SID INT
      --	,RS_CONTRACT_SID INT
      --	,PERIOD_SID INT
      --	,PROJECTION_RATE NUMERIC(22, 6)
      --	,PROJECTION_SALES NUMERIC(22, 6)
      --	,PROJECTION_DISCOUNT_DOLLAR NUMERIC(22, 6)
      --	,PROJECTION_DISCOUNT_UNITS NUMERIC(22, 6)
      --	,PROJECTION_MAP NUMERIC(22, 6)
      --	,PRICE NUMERIC(22, 6)
      --	,TOTALDEDUCTIONS NUMERIC(22, 6)
      --	,NETPRICE NUMERIC(22, 6)
      --	,NETMAP NUMERIC(22, 6)
      --	,PRICEPROTECTIONAMOUNTPERUNIT NUMERIC(22, 6)
      --	,PRICEPROTECTIONPERCENTAGE NUMERIC(22, 6)
      --	,TOTALPRICEPROTECTIONDEDUCTION NUMERIC(22, 6)
      --	,DEDUCTION_PER_UNIT NUMERIC(22, 6)
      --	,RESET BIT
      --	,PRICECHANGE NUMERIC(22, 6)
      --	,PRIMARY KEY (
      --		CCP_DETAILS_SID
      --		,RS_CONTRACT_SID
      --		,PERIOD_SID
      --		)
      --	) 
      -- '
      --				);
      --		EXEC sp_executesql @SQL
      --------select @sql
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