IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_NM_PPA_PROJECTION'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_NM_PPA_PROJECTION]
  END

GO

CREATE PROCEDURE [dbo].[PRC_NM_PPA_PROJECTION] (@PROJECTION_MASTER_SID INT,
                                                     @USER_ID               INT,
                                                     @SESSION_ID            VARCHAR(100))
AS
  BEGIN

  SET NOCOUNT ON 
/**********************************************************************************************************
** FILE NAME		:	PRC_NM_PPA_PROJECTION.SQL
** PROCEDURE NAME	:	PRC_NM_PPA_PROJECTION
** DESCRIPTION		:	this procedure will calculate the ppa discount for the ccp's having netting logic ONLY after performing the nep and wac calcuation in the ppa init procedure 
** INPUT PARAMETERS	:	@PROJECTION_SID	  - passing input as PROJECTION_MASTER_SID
						@USER_ID          - user id for the particular projection 
						@SESSION_ID       - session id for the particular projection 					
                        
** OUTPUT PARAMETERS:	na
** AUTHOR NAME		:	@SandeepKumar.A
** CREATION DATE	:	 
** CALLED BY		:   This procedure will be called from UI in the ppa projection results screen.
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
** 6                                                                                   @pradeepthanga and @SandeepKumar.A  modified the procedure to improve the performance
** 7                                                                                   @pradeepthanga 					   modified the procedure to improve the performance
*****************************************************************************************************/
--------------------------- VARIABLES DECLARATION SATRTS HERE-------------------
      DECLARE @D_PROJECTION_TABLE VARCHAR(200) = CONCAT ('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @D_SQL              NVARCHAR(MAX),
              @PROJECTION_TABLE   VARCHAR(200) = CONCAT ('ST_NM_PPA_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @MASTER_TABLE       VARCHAR(200) = CONCAT ('ST_NM_PPA_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @ACTUAL_TABLE       VARCHAR(200) = CONCAT ('ST_NM_ACTUAL_PPA_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @S_MASTER_TABLE     VARCHAR(200) = CONCAT ('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @S_PROJECTION_TABLE VARCHAR(200) = CONCAT ('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @CCP_HIERARCHY      VARCHAR(200) = CONCAT ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @NETTING_LOGIC_CCPS VARCHAR(200) = CONCAT ('ST_NETTING_LOGIC_CCPS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
              @ST_NM_PPA_SETUP    VARCHAR(200) = CONCAT ('ST_NM_PPA_SETUP_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
      DECLARE @SQL               NVARCHAR(MAX),
              @FROM_DATE         DATE,
              @TO_DATE           DATE,
              @ACTUAL_START_DATE DATE,
              @CURRENT_DATE      DATE
			    declare @rowcnt int
---------------------checking whether any ccp's having netting logic if netting logic was avaialble then only the netting calcuation will be performed to the respective ccps--------
	set @D_SQL  ='select top 1 @rowcnt = 1 from '+@NETTING_LOGIC_CCPS

	EXEC Sp_executesql
	  @D_SQL,
	  N'@rowcnt int output',
	  @rowcnt output 

      BEGIN
          SELECT @FROM_DATE = FROM_DATE,
                 @TO_DATE = TO_DATE
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
      END

  if @rowcnt = 1
  begin 

------------------------taking the ccp and projection information from respective tables--------------------------
       IF OBJECT_ID('TEMPDB.DBO.#PROJECTION_DATES', 'U') IS NOT NULL
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

      BEGIN
          SET @SQL = CONCAT ('', '  INSERT INTO #PROJECTION_DATES
		  (
		  
		  CCP_DETAILS_SID        ,
		  CONTRACT_MASTER_SID    ,
		  COMPANY_MASTER_SID     ,
		  ITEM_MASTER_SID        ,
		  PROJ_START_DATE        ,
		  PROJ_END_DATE          
		  )

          SELECT DISTINCT 
                 CD.CCP_DETAILS_SID, 
                 CD.CONTRACT_MASTER_SID, 
                 CD.COMPANY_MASTER_SID, 
                 CD.ITEM_MASTER_SID, 
                 CASE 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''M'' 
                         OR CALCULATION_PERIODS IS NULL THEN DATEADD(MM, DATEDIFF(MM, 0, @FROM_DATE), 0) 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''Q'' THEN DATEADD(QQ, DATEDIFF(QQ, 0, @FROM_DATE), 0) 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''S'' THEN 
                     CASE 
                       WHEN DATEPART(QQ, @FROM_DATE) IN ( 2, 4 ) THEN DATEADD(QQ, DATEDIFF(QQ, 0, @FROM_DATE) - 1, 0) 
                       ELSE DATEADD(QQ, DATEDIFF(QQ, 0, @FROM_DATE), 0) 
                     END 
                   ELSE DATEADD(YYYY, DATEDIFF(YYYY, 0, @FROM_DATE), 0) 
                 END PROJ_START_DATE, 
                 CASE 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''M'' THEN DATEADD(MM, DATEDIFF(MM, 0, @TO_DATE) + 1, 0) - 1 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''Q'' THEN DATEADD(QQ, DATEDIFF(QQ, 0, @TO_DATE) + 1, 0) - 1 
                   WHEN LEFT(CALCULATION_PERIODS, 1) = ''S'' THEN 
                     CASE 
                       WHEN DATEPART(QQ, @TO_DATE) IN ( 2, 4 ) THEN DATEADD(QQ, DATEDIFF(QQ, 0, @TO_DATE) + 1, 0) - 1 
                       ELSE DATEADD(QQ, DATEDIFF(QQ, 0, @TO_DATE) + 2, 0) - 1 
                     END 
                   ELSE DATEADD(YYYY, DATEDIFF(YYYY, 0, @TO_DATE) + 1, 0) - 1 
                 END  PROJ_END_DATE 
        
          FROM  CCP_DETAILS CD JOIN ', @S_MASTER_TABLE, ' S ON S.CCP_DETAILS_SID=CD.CCP_DETAILS_SID
                     WHERE EXISTS (
				   SELECT 1 FROM ', @MASTER_TABLE, ' S WHERE CD.CCP_DETAILS_SID = S.CCP_DETAILS_SID)
				
				   ', 'AND EXISTS (
				  SELECT 1 FROM ', @PROJECTION_TABLE, ' S
				  LEFT JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID=S.NET_BASE_PRICE
				  LEFT JOIN HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID=S.NET_SUBSEQUENT_PERIOD_PRICE
				  LEFT JOIN HELPER_TABLE HT2 ON HT2.HELPER_TABLE_SID=S.NET_RESET_PRICE_TYPE
				  LEFT JOIN HELPER_TABLE HT3 ON HT3.HELPER_TABLE_SID=S.NET_PRICE_TYPE
				  LEFT JOIN HELPER_TABLE HT4 ON HT4.HELPER_TABLE_SID=S.RESET_ELIGIBLE 
				  LEFT JOIN HELPER_TABLE HT5 ON HT5.HELPER_TABLE_SID=S.RESET_TYPE
				   WHERE CD.CCP_DETAILS_SID = S.CCP_DETAILS_SID )')
      END

      EXEC SP_EXECUTESQL
        @SQL,
        N'@FROM_DATE DATETIME,@TO_DATE DATETIME',
        @FROM_DATE = @FROM_DATE,
        @TO_DATE = @TO_DATE
-----------------------------calcuating the rpu for the ccp+d period wise  based on the neeting logic attached to the vaibales------------------

      IF OBJECT_ID('TEMPDB..#TEMP_REBATE_RPU') IS NOT NULL
        DROP TABLE #TEMP_REBATE_RPU

      CREATE TABLE #TEMP_REBATE_RPU
        (
           --PROJECTION_DETAILS_SID INT
           CCP_DETAILS_SID    INT,
           REBATE_TYPE        INT,
           PERIOD_SID         INT,
           DEDUCTION_PER_UNIT NUMERIC(22, 6)
        )

      BEGIN
          SET @SQL = CONCAT ('INSERT INTO #TEMP_REBATE_RPU (
			--PROJECTION_DETAILS_SID
			CCP_DETAILS_SID
			,REBATE_TYPE
			,PERIOD_SID
			,DEDUCTION_PER_UNIT
			)
		SELECT SP.CCP_DETAILS_SID--PROJECTION_DETAILS_SID
			,RM.REBATE_PROGRAM_TYPE
			,DP.PERIOD_SID
			,COALESCE(SUM(DP.PROJECTION_SALES / NULLIF(SP.PROJECTION_UNITS, 0)), 0) DEDUCTION_PER_UNIT
		FROM ', @D_PROJECTION_TABLE, ' DP
		JOIN RS_CONTRACT RM ON RM.RS_CONTRACT_SID = DP.RS_CONTRACT_SID
		JOIN ', @S_PROJECTION_TABLE, ' SP ON SP.CCP_DETAILS_SID = DP.CCP_DETAILS_SID
			AND DP.PERIOD_SID = SP.PERIOD_SID
		JOIN #PROJECTION_DATES P ON P.CCP_DETAILS_SID = SP.CCP_DETAILS_SID
		GROUP BY SP.CCP_DETAILS_SID
			,RM.REBATE_PROGRAM_TYPE
			,DP.PERIOD_SID ');

          EXEC (@SQL);
      END
-------------taking the netting logic ccp information for the ccp's------------- 
  IF Object_id('TEMPDB..#NETTING_LOGIC_CCPS') IS NOT NULL
    DROP TABLE #NETTING_LOGIC_CCPS
  
  CREATE TABLE #NETTING_LOGIC_CCPS
    (
       REBATE_FREQUENCY1                   INT,
       TTE                                 INT,
       RESET_PERIODS_RN                    INT,
       PT_SEQ                              INT,
       RESET_SEQ                           INT,
       CCP_DETAILS_SID                     INT,
       PERIOD_SID                          INT,
       RS_CONTRACT_SID                     INT,
       HARD_RESET_PERIODS                  SMALLINT,
       BASE_PRICE_TYPE                     NUMERIC(22, 6),
       PRICE_TOLERANCE_FREQUENCY           VARCHAR(50),
       PRICE_TOLERANCE_INTERVAL            INT,
       ITEM_PRICING_QUALIFIER_SID          VARCHAR(50),
       NET_BASE_PRICE                      VARCHAR(100),
       NET_SUBSEQUENT_PERIOD_PRICE         VARCHAR(100),
       NET_PRICE_TYPE                      VARCHAR(100),
       NET_RESET_PRICE_TYPE                VARCHAR(100),
       RESET_ELIGIBLE                      VARCHAR(100),
       RESET_TYPE                          VARCHAR(100),
       RESET_PRICE_TYPE                    VARCHAR(100),
       SUBSEQUENT_PERIOD_PRICE_TYPE        VARCHAR(100),
       PRICE_TOLERANCE                     NUMERIC(22, 6),
       PRICE_TOLERANCE_TYPE                VARCHAR(100),
       SUBSEQUENT_PERIOD_PRICE             NUMERIC(22, 6),
       WAC_START_SID                       INT,
       WAC_END_SID                         INT,
       RESET_DATE                          DATETIME,
       REBATE_FREQUENCY                    VARCHAR(100),
       RESET_PRICE_VALUE                   NUMERIC(22, 6),
       WAC_PRICE_VALUE                     NUMERIC(22, 6),
       RESET_FREQUENCY                     VARCHAR(100),
       RESET_INTERVAL                      INT,
       NEP_RESULT                          NUMERIC(22, 6),
       WAC_RESULT                          NUMERIC(22, 6),
       RESET_GRP                           INT,
       NET_BASE_PRICE_FORMULA              VARCHAR(100),
       NET_PRICE_TYPE_FORMULA              VARCHAR(100),
       NET_SUBSEQUENT_PERIOD_PRICE_FORMULA VARCHAR(100),
       NET_RESET_PRICE_FORMULA             VARCHAR(100),
       NEW_WAC_PRICE_VALUE                 NUMERIC(22, 6),
       INITIAL_NEP                         NUMERIC(22, 6)----cel-1465
       ,
       WAC_WITHOUT_NETTING                 NUMERIC(22, 6)----cel-1465
       ,
       PRICE_CHANGE                        NUMERIC(22, 6)----cel-1465
    ) 
  

      EXEC('INSERT INTO #NETTING_LOGIC_CCPS(REBATE_FREQUENCY1,TTE,
RESET_PERIODS_RN                   
,PT_SEQ                             
,RESET_SEQ                          
,CCP_DETAILS_SID                    
,PERIOD_SID                         
,RS_CONTRACT_SID           
,HARD_RESET_PERIODS         
,BASE_PRICE_TYPE                    
,PRICE_TOLERANCE_FREQUENCY          
,PRICE_TOLERANCE_INTERVAL           
,ITEM_PRICING_QUALIFIER_SID         
,NET_BASE_PRICE                     
,NET_SUBSEQUENT_PERIOD_PRICE        
,NET_PRICE_TYPE                     
,NET_RESET_PRICE_TYPE               
,RESET_ELIGIBLE                     
,RESET_TYPE                         
,RESET_PRICE_TYPE                   
,SUBSEQUENT_PERIOD_PRICE_TYPE       
,PRICE_TOLERANCE                    
,PRICE_TOLERANCE_TYPE               
,SUBSEQUENT_PERIOD_PRICE                                         
,WAC_START_SID                      
,WAC_END_SID                        
,RESET_DATE                         
,REBATE_FREQUENCY                   
,RESET_PRICE_VALUE                  
,WAC_PRICE_VALUE                    
,RESET_FREQUENCY                    
,RESET_INTERVAL                     
,NEP_RESULT                         
,WAC_RESULT                         
,RESET_GRP                          
,NET_BASE_PRICE_FORMULA             
,NET_PRICE_TYPE_FORMULA             
,NET_SUBSEQUENT_PERIOD_PRICE_FORMULA
,NET_RESET_PRICE_FORMULA ,NEW_WAC_PRICE_VALUE   ,INITIAL_NEP         )
SELECT REBATE_FREQUENCY1,TTE,RESET_PERIODS_RN                   
,PT_SEQ                             
,RESET_SEQ                          
,CCP_DETAILS_SID                    
,PERIOD_SID                         
,RS_CONTRACT_SID         
,HARD_RESET_PERIODS           
,BASE_PRICE_TYPE                    
,PRICE_TOLERANCE_FREQUENCY          
,PRICE_TOLERANCE_INTERVAL           
,ITEM_PRICING_QUALIFIER_SID         
,NET_BASE_PRICE                     
,NET_SUBSEQUENT_PERIOD_PRICE        
,NET_PRICE_TYPE                     
,NET_RESET_PRICE_TYPE               
,RESET_ELIGIBLE                     
,RESET_TYPE                         
,RESET_PRICE_TYPE                   
,SUBSEQUENT_PERIOD_PRICE_TYPE       
,PRICE_TOLERANCE                    
,PRICE_TOLERANCE_TYPE               
,SUBSEQUENT_PERIOD_PRICE                                          
,WAC_START_SID                      
,WAC_END_SID                        
,RESET_DATE                         
,REBATE_FREQUENCY                   
,RESET_PRICE_VALUE                  
,WAC_PRICE_VALUE                    
,RESET_FREQUENCY                    
,RESET_INTERVAL                     
,NEP_RESULT                         
,WAC_RESULT                         
,RESET_GRP                          
,NET_BASE_PRICE_FORMULA             
,NET_PRICE_TYPE_FORMULA             
,NET_SUBSEQUENT_PERIOD_PRICE_FORMULA
,NET_RESET_PRICE_FORMULA,NEW_WAC_PRICE_VALUE
,INITIAL_NEP    FROM '+@NETTING_LOGIC_CCPS)

-----------------------first we need to check the net sales, for that net sales we need to check the DEDUCTION_SUB_TYPE, there may be achange of more than one types hence for those type we will look in to indicatio column for that net sales-----
-----------------------then formula might be net sales = DEDUCTION_SUB_TYPE1 , indicator(+ or -) DEDUCTION_SUB_TYPE2 ....
-----------------------for those DEDUCTION_SUB_TYPE we need to refer the rebate program type in the RSeabtes attached to the projection and calculate the rpu for ccp +d based on the set up, this will act as netting logic ccp -----
      IF OBJECT_ID('TEMPDB..#TEMP_REBATES') IS NOT NULL
        DROP TABLE #TEMP_REBATES

      CREATE TABLE #TEMP_REBATES
        (
           CCP_DETAILS_SID             INT,
           RS_CONTRACT_SID             INT,
           PERIOD_SID                  INT,
           NET_BASE_PRICE              NUMERIC(22, 6),
           NET_SUBSEQUENT_PERIOD_PRICE NUMERIC(22, 6),
           NET_RESET_PRICE             NUMERIC(22, 6),
           NET_PRICE                   NUMERIC(22, 6)
        );

      WITH NET_BASE_PRICE
           AS (SELECT DISTINCT TN.CCP_DETAILS_SID,
                               TN.RS_CONTRACT_SID,
                               TN.PERIOD_SID,
                               ISNULL(TRR.DEDUCTION_PER_UNIT * J.INDICATOR, 0) PRODUCT,
                               TRR.REBATE_TYPE,
                               J.INDICATOR
               FROM   #NETTING_LOGIC_CCPS TN
                      LEFT JOIN (SELECT DEDUCTION_SUB_TYPE,
                                        NS.NET_SALES_FORMULA_MASTER_SID,
                                        CASE
                                          WHEN INDICATOR = '+' THEN ( -1 )
                                          WHEN INDICATOR = '-' THEN 1
                                        END INDICATOR
                                 FROM   DEDUCTION_DETAILS DD
                                        JOIN NET_SALES_FORMULA_MASTER NS
                                          ON NS.NET_SALES_FORMULA_MASTER_SID = DD.NET_SALES_FORMULA_MASTER_SID
                                 WHERE  DD.INBOUND_STATUS <> 'D'
                                        AND NS.INBOUND_STATUS <> 'D') J
                             ON J.NET_SALES_FORMULA_MASTER_SID = TN.NET_BASE_PRICE_FORMULA
                      LEFT JOIN #TEMP_REBATE_RPU TRR
                             ON TRR.PERIOD_SID = TN.PERIOD_SID
                                AND J.DEDUCTION_SUB_TYPE = TRR.REBATE_TYPE
                                AND TRR.CCP_DETAILS_SID = TN.CCP_DETAILS_SID)
      INSERT INTO #TEMP_REBATES
                  (CCP_DETAILS_SID,
                   RS_CONTRACT_SID,
                   PERIOD_SID,
                   NET_BASE_PRICE)
      SELECT CCP_DETAILS_SID,
             RS_CONTRACT_SID,
             PERIOD_SID,
             SUM(PRODUCT)
      FROM   NET_BASE_PRICE
      GROUP  BY CCP_DETAILS_SID,
                RS_CONTRACT_SID,
                PERIOD_SID;

      WITH NET_SUBSEQUENT_PERIOD_PRICE
           AS (SELECT DISTINCT TN.CCP_DETAILS_SID,
                               TN.RS_CONTRACT_SID,
                               TN.PERIOD_SID,
                               ISNULL(TRR.DEDUCTION_PER_UNIT * J.INDICATOR, 0) PRODUCT,
                               TRR.REBATE_TYPE,
                               J.INDICATOR
               FROM   #NETTING_LOGIC_CCPS TN
                      LEFT JOIN (SELECT DEDUCTION_SUB_TYPE,
                                        NS.NET_SALES_FORMULA_MASTER_SID,
                                        CASE
                                          WHEN INDICATOR = '+' THEN ( -1 )
                                          WHEN INDICATOR = '-' THEN 1
                                        END INDICATOR
                                 FROM   DEDUCTION_DETAILS DD
                                        JOIN NET_SALES_FORMULA_MASTER NS
                                          ON NS.NET_SALES_FORMULA_MASTER_SID = DD.NET_SALES_FORMULA_MASTER_SID
                                 WHERE  DD.INBOUND_STATUS <> 'D'
                                        AND NS.INBOUND_STATUS <> 'D') J
                             ON J.NET_SALES_FORMULA_MASTER_SID = NET_SUBSEQUENT_PERIOD_PRICE_FORMULA
                      LEFT JOIN #TEMP_REBATE_RPU TRR
                             ON TRR.PERIOD_SID = TN.PERIOD_SID
                                AND J.DEDUCTION_SUB_TYPE = TRR.REBATE_TYPE
                                AND TRR.CCP_DETAILS_SID = TN.CCP_DETAILS_SID)
      UPDATE T
      SET    T.NET_SUBSEQUENT_PERIOD_PRICE = A.NET_SUBSEQUENT_PERIOD_PRICE
      FROM   (SELECT CCP_DETAILS_SID,
                     RS_CONTRACT_SID,
                     PERIOD_SID,
                     SUM(PRODUCT) NET_SUBSEQUENT_PERIOD_PRICE
              FROM   NET_SUBSEQUENT_PERIOD_PRICE
              GROUP  BY CCP_DETAILS_SID,
                        RS_CONTRACT_SID,
                        PERIOD_SID) A
             JOIN #TEMP_REBATES T
               ON T.PERIOD_SID = A.PERIOD_SID
                  AND T.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                  AND T.RS_CONTRACT_SID = A.RS_CONTRACT_SID;

      WITH NET_RESET_PRICE
           AS (SELECT DISTINCT TN.CCP_DETAILS_SID,
                               TN.RS_CONTRACT_SID,
                               TN.PERIOD_SID,
                               ISNULL(TRR.DEDUCTION_PER_UNIT * J.INDICATOR, 0) PRODUCT,
                               TRR.REBATE_TYPE,
                               J.INDICATOR
               FROM   #NETTING_LOGIC_CCPS TN
                      LEFT JOIN (SELECT DEDUCTION_SUB_TYPE,
                                        NS.NET_SALES_FORMULA_MASTER_SID,
                                        CASE
                                          WHEN INDICATOR = '+' THEN ( -1 )
                                          WHEN INDICATOR = '-' THEN 1
                                        END INDICATOR
                                 FROM   DEDUCTION_DETAILS DD
                                        JOIN NET_SALES_FORMULA_MASTER NS
                                          ON NS.NET_SALES_FORMULA_MASTER_SID = DD.NET_SALES_FORMULA_MASTER_SID
                                 WHERE  DD.INBOUND_STATUS <> 'D'
                                        AND NS.INBOUND_STATUS <> 'D') J
                             ON J.NET_SALES_FORMULA_MASTER_SID = NET_RESET_PRICE_FORMULA
                      LEFT JOIN #TEMP_REBATE_RPU TRR
                             ON TRR.PERIOD_SID = TN.PERIOD_SID
                                AND J.DEDUCTION_SUB_TYPE = TRR.REBATE_TYPE
                                AND TRR.CCP_DETAILS_SID = TN.CCP_DETAILS_SID)
      UPDATE T
      SET    T.NET_RESET_PRICE = A.NET_RESET_PRICE
      FROM   (SELECT CCP_DETAILS_SID,
                     RS_CONTRACT_SID,
                     PERIOD_SID,
                     SUM(PRODUCT) NET_RESET_PRICE
              FROM   NET_RESET_PRICE
              GROUP  BY CCP_DETAILS_SID,
                        RS_CONTRACT_SID,
                        PERIOD_SID) A
             JOIN #TEMP_REBATES T
               ON T.PERIOD_SID = A.PERIOD_SID
                  AND T.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                  AND T.RS_CONTRACT_SID = A.RS_CONTRACT_SID;

      WITH NET_PRICE_TYPE
           AS (SELECT DISTINCT TN.CCP_DETAILS_SID,
                               TN.RS_CONTRACT_SID,
                               TN.PERIOD_SID,
                               ISNULL(TRR.DEDUCTION_PER_UNIT * J.INDICATOR, 0) PRODUCT,
                               TRR.REBATE_TYPE,
                               J.INDICATOR
               FROM   #NETTING_LOGIC_CCPS TN
                      LEFT JOIN (SELECT DEDUCTION_SUB_TYPE,
                                        NS.NET_SALES_FORMULA_MASTER_SID,
                                        CASE
                                          WHEN INDICATOR = '+' THEN ( -1 )
                                          WHEN INDICATOR = '-' THEN 1
                                        END INDICATOR
                                 FROM   DEDUCTION_DETAILS DD
                                        JOIN NET_SALES_FORMULA_MASTER NS
                                          ON NS.NET_SALES_FORMULA_MASTER_SID = DD.NET_SALES_FORMULA_MASTER_SID
                                 WHERE  DD.INBOUND_STATUS <> 'D'
                                        AND NS.INBOUND_STATUS <> 'D') J
                             ON J.NET_SALES_FORMULA_MASTER_SID = NET_PRICE_TYPE_FORMULA
                      LEFT JOIN #TEMP_REBATE_RPU TRR
                             ON TRR.PERIOD_SID = TN.PERIOD_SID
                                AND J.DEDUCTION_SUB_TYPE = TRR.REBATE_TYPE
                                AND TRR.CCP_DETAILS_SID = TN.CCP_DETAILS_SID)
      UPDATE T
      SET    T.NET_PRICE = A.NET_PRICE_TYPE
      FROM   (SELECT CCP_DETAILS_SID,
                     RS_CONTRACT_SID,
                     PERIOD_SID,
                     SUM(PRODUCT) NET_PRICE_TYPE
              FROM   NET_PRICE_TYPE
              GROUP  BY CCP_DETAILS_SID,
                        RS_CONTRACT_SID,
                        PERIOD_SID) A
             JOIN #TEMP_REBATES T
               ON T.PERIOD_SID = A.PERIOD_SID
                  AND A.CCP_DETAILS_SID = T.CCP_DETAILS_SID
                  AND T.RS_CONTRACT_SID = A.RS_CONTRACT_SID;
-----------------results will be update here---------------------------
      IF OBJECT_ID('TEMPDB..#TEMP_MAP_DATA') IS NOT NULL
        DROP TABLE #TEMP_MAP_DATA

      CREATE TABLE #TEMP_MAP_DATA
        (
           ID               INT IDENTITY(1, 1),
           CCP_DETAILS_SID  INT NOT NULL,
           RS_CONTRACT_SID  INT NOT NULL,
           PERIOD_SID       INT NOT NULL,
           WAC              NUMERIC(38, 15),
           NET_WAC          NUMERIC(38, 15),
           NET_MAP          NUMERIC(22, 6),
           PPA              NUMERIC(22, 6),
           PRICE_CHANGE     NUMERIC(22, 6),
           RESET_GRP        INT,
           REBATE_FREQUENCY INT
           PRIMARY KEY ( CCP_DETAILS_SID, RS_CONTRACT_SID, PERIOD_SID )
        )

      INSERT INTO #TEMP_MAP_DATA
                  (CCP_DETAILS_SID,
                   RS_CONTRACT_SID,
                   PERIOD_SID,
                   REBATE_FREQUENCY)
      SELECT CCP_DETAILS_SID,
             RS_CONTRACT_SID,
             PERIOD_SID,
             REBATE_FREQUENCY1
      FROM   #NETTING_LOGIC_CCPS
------------------------calcualting the ppa discount for the netting ccp's having reset type other than violation date------------------------
      IF EXISTS (SELECT 1
                 FROM   #NETTING_LOGIC_CCPS s
                 WHERE  LEFT(PRICE_TOLERANCE_TYPE, 1) = 'P'
                        AND not exists(select 1 from  #NETTING_LOGIC_CCPS d where d.CCP_DETAILS_SID=s.CCP_DETAILS_SID
						and d.RS_CONTRACT_SID=s.RS_CONTRACT_SID and d.PERIOD_SID=s.PERIOD_SID and d.RESET_ELIGIBLE='yes' and d.RESET_TYPE='VIOLATION DATE'))
						-------------directly taking the netting value from the #temp_rebates then deduct the netting amount from nep and wac the re-calcuate the nep and wac value
        BEGIN ;
            WITH CTE
                 AS (SELECT NC.REBATE_FREQUENCY1,
                            NC.TTE,
                            NC.RESET_PERIODS_RN,
                            NC.CCP_DETAILS_SID,
                            NC.PERIOD_SID,
                            NC.RS_CONTRACT_SID,
                            NC.BASE_PRICE_TYPE,
                            NC.PRICE_TOLERANCE_FREQUENCY,
                            NC.PRICE_TOLERANCE_INTERVAL,
                            NC.ITEM_PRICING_QUALIFIER_SID,
                            NC.NET_BASE_PRICE,
                            NC.NET_SUBSEQUENT_PERIOD_PRICE,
                            NC.NET_PRICE_TYPE,
                            NC.NET_RESET_PRICE_TYPE,
                            NC.RESET_ELIGIBLE,
                            NC.RESET_TYPE,
                            NC.RESET_PRICE_TYPE,
                            NC.SUBSEQUENT_PERIOD_PRICE_TYPE,
                            NC.PRICE_TOLERANCE,
                            NC.PRICE_TOLERANCE_TYPE,
                            NC.SUBSEQUENT_PERIOD_PRICE,
                            NC.WAC_START_SID,
                            NC.WAC_END_SID,
                            NC.RESET_DATE,
                            NC.REBATE_FREQUENCY,
                            NC.RESET_PRICE_VALUE,
                            NC.WAC_PRICE_VALUE,
                            NC.RESET_FREQUENCY,
                            NC.RESET_INTERVAL,
                            NC.NEP_RESULT,
                            NC.WAC_RESULT,
                            NC.RESET_GRP,
                           /* CASE 
							  WHEN ISNULL(INITIAL_NEP,0)=ISNULL(NEP_RESULT,0) AND RESET_GRP=1 AND NULLIF(INITIAL_NEP,0) IS NOT NULL THEN
							  0 ELSE 
							TR.NET_BASE_PRICE   END          NET_BASE_PRICE_VALUE,------cel-1465
							--TR.NET_BASE_PRICE              NET_BASE_PRICE_VALUE,
                            TR.NET_PRICE,
                            TR.NET_RESET_PRICE,
                            TR.NET_SUBSEQUENT_PERIOD_PRICE NET_SUBSEQUENT_PERIOD_PRICE_VALUE*/
							 CASE 
							  WHEN ISNULL(INITIAL_NEP,0)=ISNULL(NEP_RESULT,0) AND nc.RESET_GRP=1 AND NULLIF(INITIAL_NEP,0) IS NOT NULL THEN
							  0 ELSE 
							TR1.NET_BASE_PRICE   END          NET_BASE_PRICE_VALUE,------cel-1465
							--TR.NET_BASE_PRICE              NET_BASE_PRICE_VALUE,
                            TR.NET_PRICE,
                            TR1.NET_RESET_PRICE,
                            TR1.NET_SUBSEQUENT_PERIOD_PRICE NET_SUBSEQUENT_PERIOD_PRICE_VALUE
							,NC.INITIAL_NEP ------CEL-1465
							, CASE 
							  WHEN ISNULL(INITIAL_NEP,0)=ISNULL(NEP_RESULT,0) AND nc.RESET_GRP=1 AND NULLIF(INITIAL_NEP,0) IS NOT NULL THEN
							  1 ELSE 
							  0 END          NETTING_EXCLUSION------CEL-1465
                     FROM   #NETTING_LOGIC_CCPS NC
                            JOIN #TEMP_REBATES TR
                              ON TR.CCP_DETAILS_SID = NC.CCP_DETAILS_SID
                                 AND TR.RS_CONTRACT_SID = NC.RS_CONTRACT_SID
                                 AND TR.PERIOD_SID = NC.PERIOD_SID
							JOIN 	 ( SELECT * FROM (SELECT TR.CCP_DETAILS_SID,TR.RS_CONTRACT_SID,TR.PERIOD_SID ,ROW_NUMBER() OVER( PARTITION BY TR.CCP_DETAILS_SID,TR.RS_CONTRACT_SID ORDER BY TR.PERIOD_SID,RESET_GRP) RN,RESET_GRP,TR.NET_BASE_PRICE,TR.NET_RESET_PRICE,TR.NET_SUBSEQUENT_PERIOD_PRICE FROM  #NETTING_LOGIC_CCPS NC
                            JOIN #TEMP_REBATES TR
                              ON TR.CCP_DETAILS_SID = NC.CCP_DETAILS_SID
                                 AND TR.RS_CONTRACT_SID = NC.RS_CONTRACT_SID
                                 AND TR.PERIOD_SID = NC.PERIOD_SID WHERE RESET_PERIODS_RN = 1)A WHERE RN=1
								 ) TR1-------------AGN-395
                              ON TR1.CCP_DETAILS_SID = NC.CCP_DETAILS_SID
                                 AND TR1.RS_CONTRACT_SID = NC.RS_CONTRACT_SID
                                 AND TR1.RESET_GRP = NC.RESET_GRP
                     WHERE  RESET_PERIODS_RN = 1
                            AND LEFT(NC.PRICE_TOLERANCE_TYPE, 1) = 'P'
                             AND not exists(select 1 from  #NETTING_LOGIC_CCPS d where d.CCP_DETAILS_SID=NC.CCP_DETAILS_SID
						and d.RS_CONTRACT_SID=NC.RS_CONTRACT_SID and d.PERIOD_SID=nc.PERIOD_SID and d.RESET_ELIGIBLE='yes' and d.RESET_TYPE='VIOLATION DATE')),
					CTE2
                 AS (
						SELECT A.CCP_DETAILS_SID,
                            A.RS_CONTRACT_SID,
                            A.PERIOD_SID,
                            CASE
							  WHEN ISNULL(INITIAL_NEP,0)=ISNULL(NEP_RESULT,0) AND RESET_GRP=1 AND NULLIF(INITIAL_NEP,0) IS NOT NULL THEN NEP_RESULT	------CEL-1465
                              WHEN ( NEP_RESULT - RES ) > 0 THEN ( NEP_RESULT - RES )
                              ELSE 0
                            END AS RESULT,
							NEP_RESULT,cs.RES ---,cs.NEW_RN,cs.NET_BASE_PRICE_VALUE,cs.FIRST_RESET_VAL,cs.NET_SUBSEQUENT_PERIOD_PRICE_VALUE,cs.MX_NEW_RN
                            ,RESET_GRP
                     FROM   CTE A
                            CROSS APPLY (SELECT max(( B.NET_BASE_PRICE_VALUE * POWER(( 1 + B.PRICE_TOLERANCE / 100.00 ), NEW_RN-NETTING_EXCLUSION) ) 							
							+ CASE
                                                                                                                                       WHEN MX_NEW_RN = NEW_RN THEN ( B.FIRST_RESET_VAL * POWER(( 1 + B.PRICE_TOLERANCE / 100.00 ), NEW_RN-NETTING_EXCLUSION) )
                                                                                                                                       ELSE 0
                                                                                                                                     END + ( IIF(B.NET_SUBSEQUENT_PERIOD_PRICE = 'YES', B.NET_SUBSEQUENT_PERIOD_PRICE_VALUE, 0) * POWER(( 1 + B.PRICE_TOLERANCE / 100.00 ), NEW_RN-NETTING_EXCLUSION) ))-------------AGN-395
										 AS RES
                                         -- ,NEW_RN,B.NET_BASE_PRICE_VALUE,B.FIRST_RESET_VAL,B.NET_SUBSEQUENT_PERIOD_PRICE_VALUE,MX_NEW_RN
                                         FROM   (SELECT MAX(NEW_RN)
                                                          OVER() AS MX_NEW_RN,
                                                        *
                                                 FROM   (SELECT ROW_NUMBER()
                                                                  OVER(
                                                                    PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                    ORDER BY PERIOD_SID DESC) NEW_RN,
                                                                FIRST_VALUE(NET_RESET_PRICE)
                                                                  OVER(
                                                                    PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                    ORDER BY PERIOD_SID )     AS FIRST_RESET_VAL,
                                                                *
                                                         FROM   CTE B
                                                         WHERE  A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                                                AND A.RS_CONTRACT_SID = B.RS_CONTRACT_SID
                                                                AND A.RESET_GRP = B.RESET_GRP
                                                                AND A.PERIOD_SID >= B.PERIOD_SID)C)B																
                                                        ) CS)
                 /*CTE2
                 AS (SELECT A.CCP_DETAILS_SID,
                            A.RS_CONTRACT_SID,
                            A.PERIOD_SID,
                            CASE
							  WHEN ISNULL(INITIAL_NEP,0)=ISNULL(NEP_RESULT,0) AND RESET_GRP=1 AND NULLIF(INITIAL_NEP,0) IS NOT NULL THEN NEP_RESULT	------CEL-1465
                              WHEN ( NEP_RESULT - RES ) > 0 THEN ( NEP_RESULT - RES )
                              ELSE 0
                            END AS RESULT,
                            RESET_GRP
                     FROM   CTE A
                            CROSS APPLY (SELECT SUM(( B.NET_BASE_PRICE_VALUE * POWER(( 1 + B.PRICE_TOLERANCE / 100.00 ), NEW_RN-NETTING_EXCLUSION) ) + CASE
                                                                                                                                       WHEN MX_NEW_RN = NEW_RN THEN ( B.FIRST_RESET_VAL * POWER(( 1 + B.PRICE_TOLERANCE / 100.00 ), NEW_RN-NETTING_EXCLUSION) )
                                                                                                                                       ELSE 0
                                                                                                                                     END + ( IIF(B.NET_SUBSEQUENT_PERIOD_PRICE = 'YES', B.NET_SUBSEQUENT_PERIOD_PRICE_VALUE, 0) * POWER(( 1 + B.PRICE_TOLERANCE / 100.00 ), NEW_RN-NETTING_EXCLUSION) )) AS RES
                                         -- ,NEW_RN,B.NET_BASE,B.FIRST_RESET_VAL,B.NET_SUBSEQUENT,B.RNO,MX_NEW_RN
                                         FROM   (SELECT MAX(NEW_RN)
                                                          OVER() AS MX_NEW_RN,
                                                        *
                                                 FROM   (SELECT ROW_NUMBER()
                                                                  OVER(
                                                                    PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                    ORDER BY PERIOD_SID DESC) NEW_RN,
                                                                FIRST_VALUE(NET_RESET_PRICE)
                                                                  OVER(
                                                                    PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                    ORDER BY PERIOD_SID )     AS FIRST_RESET_VAL,
                                                                *
                                                         FROM   CTE B
                                                         WHERE  A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                                                AND A.RS_CONTRACT_SID = B.RS_CONTRACT_SID
                                                                AND A.RESET_GRP = B.RESET_GRP
                                                                AND A.PERIOD_SID >= B.PERIOD_SID)C)B) CS)*/
            UPDATE TM
            SET    NET_MAP = C2.RESULT,
                   RESET_GRP = C2.RESET_GRP
            FROM   #TEMP_MAP_DATA TM
                   JOIN CTE2 C2
                     ON C2.CCP_DETAILS_SID = TM.CCP_DETAILS_SID
                        AND TM.PERIOD_SID = C2.PERIOD_SID
                        AND TM.RS_CONTRACT_SID = C2.RS_CONTRACT_SID
        --ORDER  BY A.CCP_DETAILS_SID,
        --          A.RS_CONTRACT_SID,
        --          A.PERIOD_SID;
        END
------------------------calcualting the ppa discount for the netting ccp's having only reset type violation date------------------------
      IF EXISTS (SELECT 1
                 FROM   #NETTING_LOGIC_CCPS S
                 WHERE  LEFT(PRICE_TOLERANCE_TYPE, 1) = 'D'
                        --AND RESET_TYPE <> 'VIOLATION DATE'
						 AND not exists(select 1 from  #NETTING_LOGIC_CCPS d where d.CCP_DETAILS_SID=s.CCP_DETAILS_SID
						and d.RS_CONTRACT_SID=s.RS_CONTRACT_SID and d.PERIOD_SID=s.PERIOD_SID and d.RESET_ELIGIBLE='yes' and d.RESET_TYPE='VIOLATION DATE')
	
						)
						----------------for the violation ccp's we need to follow the violation date logic as we need to compare the wac and nep values period wise----------------
						----------------this violation date logic will be applicable from projection start date to projection end date only----------------
						----------------for the first period we will compare the nep(after netting) and net wac(after netting of wac), if price violation happens then reset of next period again need to do the same logic for the entire periods----------------
        BEGIN ;
            WITH CTE
                 AS (SELECT NC.REBATE_FREQUENCY1,
                            NC.TTE,
                            NC.RESET_PERIODS_RN,
                            NC.CCP_DETAILS_SID,
                            NC.PERIOD_SID,
                            NC.RS_CONTRACT_SID,
                            NC.BASE_PRICE_TYPE,
                            NC.PRICE_TOLERANCE_FREQUENCY,
                            NC.PRICE_TOLERANCE_INTERVAL,
                            NC.ITEM_PRICING_QUALIFIER_SID,
                            NC.NET_BASE_PRICE,
                            NC.NET_SUBSEQUENT_PERIOD_PRICE,
                            NC.NET_PRICE_TYPE,
                            NC.NET_RESET_PRICE_TYPE,
                            NC.RESET_ELIGIBLE,
                            NC.RESET_TYPE,
                            NC.RESET_PRICE_TYPE,
                            NC.SUBSEQUENT_PERIOD_PRICE_TYPE,
                            NC.PRICE_TOLERANCE,
                            NC.PRICE_TOLERANCE_TYPE,
                            NC.SUBSEQUENT_PERIOD_PRICE,
                            NC.WAC_START_SID,
                            NC.WAC_END_SID,
                            NC.RESET_DATE,
                            NC.REBATE_FREQUENCY,
                            NC.RESET_PRICE_VALUE,
                            NC.WAC_PRICE_VALUE,
                            NC.RESET_FREQUENCY,
                            NC.RESET_INTERVAL,
                            NC.NEP_RESULT,
                            NC.WAC_RESULT,
                            NC.RESET_GRP,
							/*CASE 
							  WHEN ISNULL(INITIAL_NEP,0)=ISNULL(NEP_RESULT,0) AND RESET_GRP=1 THEN
							  0 ELSE 
							TR.NET_BASE_PRICE   END          NET_BASE_PRICE_VALUE,------cel-1465
                            TR.NET_PRICE,
                            TR.NET_RESET_PRICE,
                            TR.NET_SUBSEQUENT_PERIOD_PRICE NET_SUBSEQUENT_PERIOD_PRICE_VALUE*/
							CASE 
							  WHEN ISNULL(INITIAL_NEP,0)=ISNULL(NEP_RESULT,0) AND nc.RESET_GRP=1 THEN
							  0 ELSE 
							TR1.NET_BASE_PRICE   END          NET_BASE_PRICE_VALUE,------cel-1465
                            TR.NET_PRICE,
                            TR1.NET_RESET_PRICE,
                            TR1.NET_SUBSEQUENT_PERIOD_PRICE NET_SUBSEQUENT_PERIOD_PRICE_VALUE
							,NC.INITIAL_NEP------cel-1465
                     FROM   #NETTING_LOGIC_CCPS NC
                            JOIN #TEMP_REBATES TR
                              ON TR.CCP_DETAILS_SID = NC.CCP_DETAILS_SID
                                 AND TR.RS_CONTRACT_SID = NC.RS_CONTRACT_SID
                                 AND TR.PERIOD_SID = NC.PERIOD_SID
							JOIN 	 ( SELECT * FROM (SELECT TR.CCP_DETAILS_SID,TR.RS_CONTRACT_SID,TR.PERIOD_SID ,ROW_NUMBER() OVER( PARTITION BY TR.CCP_DETAILS_SID,TR.RS_CONTRACT_SID ORDER BY TR.PERIOD_SID,RESET_GRP) RN,RESET_GRP,TR.NET_BASE_PRICE,TR.NET_RESET_PRICE,TR.NET_SUBSEQUENT_PERIOD_PRICE FROM  #NETTING_LOGIC_CCPS NC
                            JOIN #TEMP_REBATES TR
                              ON TR.CCP_DETAILS_SID = NC.CCP_DETAILS_SID
                                 AND TR.RS_CONTRACT_SID = NC.RS_CONTRACT_SID
                                 AND TR.PERIOD_SID = NC.PERIOD_SID WHERE RESET_PERIODS_RN = 1)A WHERE RN=1
								 ) TR1-------------AGN-395
                              ON TR1.CCP_DETAILS_SID = NC.CCP_DETAILS_SID
                                 AND TR1.RS_CONTRACT_SID = NC.RS_CONTRACT_SID
                                 AND TR1.RESET_GRP = NC.RESET_GRP
                     WHERE  RESET_PERIODS_RN = 1
                            AND LEFT(NC.PRICE_TOLERANCE_TYPE, 1) = 'D'
                               AND not exists(select 1 from  #NETTING_LOGIC_CCPS d where d.CCP_DETAILS_SID=NC.CCP_DETAILS_SID
						and d.RS_CONTRACT_SID=NC.RS_CONTRACT_SID and d.PERIOD_SID=nc.PERIOD_SID and d.RESET_ELIGIBLE='yes' and d.RESET_TYPE='VIOLATION DATE')),
						CTE2 AS(SELECT A.CCP_DETAILS_SID,
                            A.RS_CONTRACT_SID,
                            A.PERIOD_SID,
                            CASE
							  WHEN ISNULL(INITIAL_NEP,0)=ISNULL(NEP_RESULT,0) AND RESET_GRP=1 THEN NEP_RESULT
                              WHEN ( NEP_RESULT - RES ) > 0 THEN ( NEP_RESULT - RES )
                              ELSE 0
                            END AS RESULT,RES,
                            RESET_GRP
                     FROM   CTE A
                            CROSS APPLY (SELECT MAX(( B.NET_BASE_PRICE_VALUE ) + CASE
                                                                                   WHEN MX_NEW_RN = NEW_RN THEN ( B.FIRST_RESET_VAL )
                                                                                   ELSE 0
                                                                                 END + ( IIF(B.NET_SUBSEQUENT_PERIOD_PRICE = 'YES', B.NET_SUBSEQUENT_PERIOD_PRICE_VALUE, 0) )) AS RES-------------AGN-395
                                         -- ,NEW_RN,B.NET_BASE,B.FIRST_RESET_VAL,B.NET_SUBSEQUENT,B.RNO,MX_NEW_RN
                                         FROM   (SELECT MAX(NEW_RN)
                                                          OVER() AS MX_NEW_RN,
                                                        *
                                                 FROM   (SELECT ROW_NUMBER()
                                                                  OVER(
                                                                    PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                    ORDER BY PERIOD_SID DESC) NEW_RN,
                                                                FIRST_VALUE(NET_RESET_PRICE)
                                                                  OVER(
                                                                    PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                    ORDER BY PERIOD_SID )     AS FIRST_RESET_VAL,
                                                                *
                                                         FROM   CTE B
                                                         WHERE  A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                                                AND A.RS_CONTRACT_SID = B.RS_CONTRACT_SID
                                                                AND A.RESET_GRP = B.RESET_GRP
                                                                AND A.PERIOD_SID >= B.PERIOD_SID)C)B) CS)
    
                /* CTE2
                 AS (SELECT A.CCP_DETAILS_SID,
                            A.RS_CONTRACT_SID,
                            A.PERIOD_SID,
                            CASE
							  WHEN ISNULL(INITIAL_NEP,0)=ISNULL(NEP_RESULT,0) AND RESET_GRP=1 THEN NEP_RESULT
                              WHEN ( NEP_RESULT - RES ) > 0 THEN ( NEP_RESULT - RES )
                              ELSE 0
                            END AS RESULT,
                            RESET_GRP
                     FROM   CTE A
                            CROSS APPLY (SELECT SUM(( B.NET_BASE_PRICE_VALUE ) + CASE
                                                                                   WHEN MX_NEW_RN = NEW_RN THEN ( B.FIRST_RESET_VAL )
                                                                                   ELSE 0
                                                                                 END + ( IIF(B.NET_SUBSEQUENT_PERIOD_PRICE = 'YES', B.NET_SUBSEQUENT_PERIOD_PRICE_VALUE, 0) )) AS RES
                                         -- ,NEW_RN,B.NET_BASE,B.FIRST_RESET_VAL,B.NET_SUBSEQUENT,B.RNO,MX_NEW_RN
                                         FROM   (SELECT MAX(NEW_RN)
                                                          OVER() AS MX_NEW_RN,
                                                        *
                                                 FROM   (SELECT ROW_NUMBER()
                                                                  OVER(
                                                                    PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                    ORDER BY PERIOD_SID DESC) NEW_RN,
                                                                FIRST_VALUE(NET_RESET_PRICE)
                                                                  OVER(
                                                                    PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                    ORDER BY PERIOD_SID )     AS FIRST_RESET_VAL,
                                                                *
                                                         FROM   CTE B
                                                         WHERE  A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                                                AND A.RS_CONTRACT_SID = B.RS_CONTRACT_SID
                                                                AND A.RESET_GRP = B.RESET_GRP
                                                                AND A.PERIOD_SID >= B.PERIOD_SID)C)B) CS)*/
            UPDATE TM
            SET    NET_MAP = C2.RESULT,
                   RESET_GRP = C2.RESET_GRP
            FROM   #TEMP_MAP_DATA TM
                   JOIN CTE2 C2
                     ON C2.CCP_DETAILS_SID = TM.CCP_DETAILS_SID
                        AND TM.PERIOD_SID = C2.PERIOD_SID
                        AND TM.RS_CONTRACT_SID = C2.RS_CONTRACT_SID
        END
--------------------taking the wac information for the ccp having reset type violation date only---------------------
      IF EXISTS (SELECT 1
                 FROM   #NETTING_LOGIC_CCPS
                 WHERE  RESET_TYPE = 'VIOLATION DATE'
                        AND RESET_ELIGIBLE = 'YES')
        BEGIN
            IF OBJECT_ID('TEMPDB..#BEFORE_WAC') IS NOT NULL
              DROP TABLE #BEFORE_WAC

            CREATE TABLE #BEFORE_WAC
              (
                 CCP_DETAILS_SID INT,
                 RS_CONTRACT_SID INT,
                 PERIOD_SID      INT
              )

            EXEC('
INSERT INTO #BEFORE_WAC(CCP_DETAILS_SID,RS_CONTRACT_SID,PERIOD_SID)
		      SELECT CCP_DETAILS_SID,
             RS_CONTRACT_SID,
             MAX(PERIOD_SID) AS PERIOD_SID
      FROM   (SELECT C.CCP_DETAILS_SID,
                     RS_CONTRACT_SID,
                     P.PERIOD_SID,
                     ( PERIOD_SID - PRICE_PROTECTION_START_DATE ) / ( CASE LEFT(C.PRICE_TOL_FRQ, 1)
                                                                        WHEN ''M'' THEN 1
                                                                        WHEN ''Q'' THEN 3
                                                                        WHEN ''S'' THEN 6
                                                                        ELSE 12
                                                                      END * C.PRICE_TOL_INT ) + 1 AS GRP,
                     MAX(CASE
                           WHEN PD.PROJ_START_DATE = PERIOD_SID THEN ( PERIOD_SID - PRICE_PROTECTION_START_DATE ) / ( CASE LEFT(C.PRICE_TOL_FRQ, 1)
                                                                        WHEN ''M'' THEN 1
                                                                        WHEN ''Q'' THEN 3
                                                                        WHEN ''S'' THEN 6
                                                                        ELSE 12
                                                                      END * C.PRICE_TOL_INT ) + 1
                         END)
                       OVER(
                         PARTITION BY C.CCP_DETAILS_SID, RS_CONTRACT_SID)                         AS PROJ_STAT_GRP
              FROM   PERIOD P
                     JOIN '+@ST_NM_PPA_SETUP+' C
                          JOIN #PROJECTION_DATES PD
                            ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                       ON P.PERIOD_SID >= C.PRICE_PROTECTION_START_DATE
                          AND P.PERIOD_SID <= C.PRICE_PROTECTION_END_DATE
              WHERE  C.RESET_TYPE = ''VIOLATION_DATE''
                     AND C.RESET_ELIGIBLE = ''YES'') A
      WHERE  GRP <= PROJ_STAT_GRP
      GROUP  BY CCP_DETAILS_SID,
                RS_CONTRACT_SID');
						----------------this violation date logic will be applicable from projection start date to projection end date only----------------
						----------------for the first period we will compare the nep(after netting) and net wac(after netting of wac), if price violation happens then reset of next period again need to do the same logic for the entire periods----------------
            -----CEL-1465
			IF Object_id('tempdb..#VIOLATION_NEP_RESULT') IS NOT NULL
            DROP TABLE #VIOLATION_NEP_RESULT;
            ;WITH CTE
                 AS (SELECT *,
                            ROW_NUMBER()
                              OVER (
                                PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                ORDER BY PERIOD_SID ) FIRST_PERIOD
                     FROM   #NETTING_LOGIC_CCPS
                     WHERE  RESET_TYPE = 'VIOLATION DATE'
                            AND RESET_ELIGIBLE = 'YES'
                            AND RESET_PERIODS_RN = 1)
            SELECT C.*,
                   CASE
                     WHEN LEFT(c.PRICE_TOLERANCE_TYPE, 1) = 'D' THEN
                       CASE 
                         WHEN c.SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN c.SUBSEQUENT_PERIOD_PRICE
                         ELSE IIF(c.RESET_GRP = 1, Iif(NULLIF(c.INITIAL_NEP, 0) IS NOT NULL AND c.RESET_GRP = 1, c.INITIAL_NEP, c.BASE_PRICE_TYPE), FIRST_VALUE(c.RESET_PRICE_VALUE) OVER ( PARTITION BY c.CCP_DETAILS_SID, c.RS_CONTRACT_SID, c.RESET_GRP ORDER BY c.PERIOD_SID ))
                              + SUM(c.PRICE_TOLERANCE) OVER ( PARTITION BY c.CCP_DETAILS_SID, c.RS_CONTRACT_SID, c.RESET_GRP ORDER BY c.PERIOD_SID )- Iif(NULLIF(c.INITIAL_NEP, 0) IS NOT NULL AND c.RESET_GRP = 1, c.PRICE_TOLERANCE, 0)--------CEL-1465
                       END
                     WHEN LEFT(c.PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
                       CASE 
                         WHEN c.SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN c.SUBSEQUENT_PERIOD_PRICE
                         ELSE IIF(c.RESET_GRP = 1, Iif(NULLIF(c.INITIAL_NEP, 0) IS NOT NULL AND c.RESET_GRP = 1, c.INITIAL_NEP, c.BASE_PRICE_TYPE), FIRST_VALUE(c.RESET_PRICE_VALUE)
                                                                    OVER (
                                                                      PARTITION BY c.CCP_DETAILS_SID, c.RS_CONTRACT_SID, c.RESET_GRP
                                                                      ORDER BY c.PERIOD_SID )) * POWER(( 1 + c.PRICE_TOLERANCE / 100.0 ), FIRST_PERIOD- Iif(NULLIF(c.INITIAL_NEP, 0) IS NOT NULL AND c.RESET_GRP = 1, 1, 0))--------CEL-1465
                       END
                   END AS NEP_WITHOUT_NETTING
            INTO   #VIOLATION_NEP_RESULT
            FROM   CTE C
                   LEFT JOIN #BEFORE_WAC B
                          ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                             AND C.RS_CONTRACT_SID = B.RS_CONTRACT_SID
                             AND C.PERIOD_SID <= B.PERIOD_SID
            WHERE  C.RESET_TYPE = 'VIOLATION DATE'
                   AND C.RESET_ELIGIBLE = 'YES'
				   AND EXISTS(SELECT 1
                                  FROM   #NETTING_LOGIC_CCPS T
                                  WHERE  ( ( T.NET_BASE_PRICE = 'YES'
                                              )
                                            OR ( T.NET_PRICE_TYPE = 'YES'
                                                  )
                                            OR ( T.NET_SUBSEQUENT_PERIOD_PRICE = 'YES'
                                                  )
                                            OR ( T.NET_RESET_PRICE_TYPE = 'YES'
                                                  ) )
                                         AND T.CCP_DETAILS_SID = C.CCP_DETAILS_SID
                                         AND T.RS_CONTRACT_SID = C.RS_CONTRACT_SID
                                         AND T.PERIOD_SID = C.PERIOD_SID)
/*
            ;WITH CTE
                 AS (SELECT *,
                            ROW_NUMBER()
                              OVER (
                                PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                ORDER BY PERIOD_SID ) FIRST_PERIOD
                     FROM   #NETTING_LOGIC_CCPS
                     WHERE  RESET_TYPE = 'VIOLATION DATE'
                            AND RESET_ELIGIBLE = 'YES'
                            AND RESET_PERIODS_RN = 1)
            SELECT C.*,
                   CASE
                     WHEN LEFT(PRICE_TOLERANCE_TYPE, 1) = 'D' THEN
                       CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                         WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                         ELSE IIF(RESET_GRP = 1, BASE_PRICE_TYPE, FIRST_VALUE(RESET_PRICE_VALUE) OVER ( PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP ORDER BY PERIOD_SID ))
                              + SUM(PRICE_TOLERANCE) OVER ( PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP ORDER BY PERIOD_SID )
                       END
                     WHEN LEFT(PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
                       CASE --WHEN SUBSEQUENT_PERIOD_PRICE_TYPE ='BQWAC' THEN
                         WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE
                         ELSE IIF(RESET_GRP = 1, BASE_PRICE_TYPE, FIRST_VALUE(RESET_PRICE_VALUE)
                                                                    OVER (
                                                                      PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID, RESET_GRP
                                                                      ORDER BY PERIOD_SID )) * POWER(( 1 + PRICE_TOLERANCE / 100.0 ), FIRST_PERIOD)
                       END
                   END AS NEP_WITHOUT_NETTING
            INTO   #VIOLATION_NEP_RESULT
            FROM   CTE C
                   LEFT JOIN #BEFORE_WAC B
                          ON C.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                             AND C.RS_CONTRACT_SID = B.RS_CONTRACT_SID
                             AND C.PERIOD_SID <= B.PERIOD_SID
            WHERE  C.RESET_TYPE = 'VIOLATION DATE'
                   AND C.RESET_ELIGIBLE = 'YES'
				   

            SELECT *
            INTO   #TEST
            FROM   (SELECT A.*,
                           ROW_NUMBER()
                             OVER(
                               PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID
                               ORDER BY PERIOD_SID DESC) LAST_PERIOD,
                           SUM(PRICE_TOLERANCE)
                             OVER (
                               PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID
                               ORDER BY PERIOD_SID )     AS NEP
                    FROM   (SELECT *,
                                   ( ROW_NUMBER()
                                       OVER(
                                         PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID
                                         ORDER BY PERIOD_SID) - 1 ) % PT_SEQ + 1 GRP
                            FROM   #VIOLATION_NEP_RESULT) A
                           JOIN #PROJECTION_DATES PD
                             ON PD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           JOIN PERIOD P
                             ON P.PERIOD_DATE = PD.PROJ_START_DATE
                    WHERE  GRP = 1
                           AND A.PERIOD_SID <= P.PERIOD_SID) A
            WHERE  LAST_PERIOD = 1;
			*/
			IF Object_id('tempdb..#TEST') IS NOT NULL
            DROP TABLE #TEST;
            SELECT *
            INTO   #TEST
            FROM   (SELECT A.*,
                           ROW_NUMBER()
                             OVER(
                               PARTITION BY a.CCP_DETAILS_SID, a.RS_CONTRACT_SID
                               ORDER BY a.PERIOD_SID DESC) LAST_PERIOD,
                           /*SUM(PRICE_TOLERANCE)
                             OVER (
                               PARTITION BY a.CCP_DETAILS_SID, a.RS_CONTRACT_SID
                               ORDER BY a.PERIOD_SID )     AS NEP*/
							   NEP_WITHOUT_NETTING             AS NEP
                    FROM   (SELECT *,
                                   ( ROW_NUMBER()
                                       OVER(
                                         PARTITION BY CCP_DETAILS_SID,RS_CONTRACT_SID
                                         ORDER BY PERIOD_SID) - 1 ) % PT_SEQ + 1 GRP
                            FROM   #VIOLATION_NEP_RESULT) A
                           JOIN #PROJECTION_DATES PD
                             ON PD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                           JOIN PERIOD P
                             ON P.PERIOD_DATE = PD.PROJ_START_DATE
                    WHERE  GRP = 1
                           AND A.PERIOD_SID >= P.PERIOD_SID) A
            WHERE  LAST_PERIOD = 1;
						----------------this violation date logic will be applicable from projection start date to projection end date only----------------
						----------------for the first period we will compare the nep(after netting) and net wac(after netting of wac), if price violation happens then reset of next period again need to do the same logic for the entire periods----------------

           ; WITH REC_CTE
                 AS (SELECT RP.CCP_DETAILS_SID,
                            RP.RS_CONTRACT_SID,
                            RP.BASE_PRICE_TYPE,
                            IIF(NULLIF(RP.INITIAL_NEP, 0) IS NOT NULL AND RP.RESET_GRP = 1 AND T.NEP=RP.NEP_RESULT,T.NEP,T.NEP-TR.NET_BASE_PRICE) AS NEP,-----CEL-1465
							--NEP-TR.NET_BASE_PRICE  NEP,
                            RP.NEW_WAC_PRICE_VALUE,
                            IIF(NULLIF(RP.INITIAL_NEP, 0) IS NOT NULL AND RP.RESET_GRP = 1 AND T.NEP=RP.NEP_RESULT,T.NEP,T.NEP-TR.NET_BASE_PRICE)  AS RUNNING_TOTAL,-----CEL-1465
							---NEP-TR.NET_BASE_PRICE                                  AS RUNNING_TOTAL,
                            IIF (IIF(NULLIF(RP.INITIAL_NEP, 0) IS NOT NULL AND RP.RESET_GRP = 1 AND T.NEP=RP.NEP_RESULT,T.NEP,T.NEP-TR.NET_BASE_PRICE)   > T.NEW_WAC_PRICE_VALUE - TR.NET_PRICE , 1, 0) AS FLAG,-----CEL-1465
                            1                                       AS RESET_PERIOD,
                            1                                       AS PTF_RESET,
                            T.NEW_WAC_PRICE_VALUE   - TR.NET_PRICE     AS PREV_WAC,
                            T.PERIOD_SID
							,RP.NEW_WAC_PRICE_VALUE WAC_WITHOUT_NETTING
							,PRICE_CHANGE=CAST(0 AS NUMERIC(22,6))
							--,IIF(NULLIF(RP.INITIAL_NEP, 0) IS NOT NULL AND RP.RESET_GRP = 1 AND T.NEP=RP.NEP_RESULT,T.NEP,T.NEP-TR.NET_BASE_PRICE)
                     FROM   #TEST T
                            JOIN #NETTING_LOGIC_CCPS RP
                              ON T.CCP_DETAILS_SID = RP.CCP_DETAILS_SID
                                 AND T.RS_CONTRACT_SID = RP.RS_CONTRACT_SID
                                 AND T.PERIOD_SID = RP.PERIOD_SID
							 JOIN #TEMP_REBATES TR ON TR.CCP_DETAILS_SID = RP.CCP_DETAILS_SID
							 AND TR.RS_CONTRACT_SID = RP.RS_CONTRACT_SID 
							 AND TR.PERIOD_SID = RP.PERIOD_SID
                     UNION ALL
                     SELECT R.CCP_DETAILS_SID,
                            R.RS_CONTRACT_SID,
                            R.BASE_PRICE_TYPE,
                            C.NEP,
                            R.RESET_PRICE_VALUE,
                            IIF(C.FLAG = 1, CASE  WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'D' THEN 
											CASE 
                                              WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE - TR.NET_SUBSEQUENT_PERIOD_PRICE
                                             ELSE R.RESET_PRICE_VALUE - TR.NET_SUBSEQUENT_PERIOD_PRICE - TR.NET_RESET_PRICE + R.PRICE_TOLERANCE
                                            END
											 WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
											CASE 
                                              WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE - TR.NET_SUBSEQUENT_PERIOD_PRICE
                                             ELSE (R.RESET_PRICE_VALUE - TR.NET_SUBSEQUENT_PERIOD_PRICE - TR.NET_RESET_PRICE) * (1 + R.PRICE_TOLERANCE/100)
                                            END
											END,  CASE  WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'D' THEN 
											CASE
                                                   WHEN PTF_RESET <> PT_SEQ THEN C.RUNNING_TOTAL
                                                   ELSE
                                                     CASE 
                                                       WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE  - TR.NET_SUBSEQUENT_PERIOD_PRICE
                                                       ELSE C.RUNNING_TOTAL - TR.NET_SUBSEQUENT_PERIOD_PRICE + R.PRICE_TOLERANCE
                                                     END
                                                 END
												 WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
												 CASE
                                                   WHEN PTF_RESET <> PT_SEQ THEN C.RUNNING_TOTAL
                                                   ELSE
                                                     CASE 
                                                       WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE  - TR.NET_SUBSEQUENT_PERIOD_PRICE
                                                       ELSE (C.RUNNING_TOTAL - TR.NET_SUBSEQUENT_PERIOD_PRICE) * (1+ R.PRICE_TOLERANCE/100)
                                                     END
                                                 END
												 END ),
                            IIF(IIF(C.FLAG = 1, CASE  WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'D' THEN 
											CASE 
                                              WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE - TR.NET_SUBSEQUENT_PERIOD_PRICE
                                             ELSE R.RESET_PRICE_VALUE - TR.NET_SUBSEQUENT_PERIOD_PRICE - TR.NET_RESET_PRICE + R.PRICE_TOLERANCE
                                            END
											 WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
											CASE 
                                              WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE - TR.NET_SUBSEQUENT_PERIOD_PRICE
                                             ELSE (R.RESET_PRICE_VALUE - TR.NET_SUBSEQUENT_PERIOD_PRICE - TR.NET_RESET_PRICE) * (1 + R.PRICE_TOLERANCE/100)
                                            END
											END,  CASE  WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'D' THEN 
											CASE
                                                   WHEN PTF_RESET <> PT_SEQ THEN C.RUNNING_TOTAL
                                                   ELSE
                                                     CASE 
                                                       WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE  - TR.NET_SUBSEQUENT_PERIOD_PRICE
                                                       ELSE C.RUNNING_TOTAL - TR.NET_SUBSEQUENT_PERIOD_PRICE + R.PRICE_TOLERANCE
                                                     END
                                                 END
												 WHEN LEFT(R.PRICE_TOLERANCE_TYPE, 1) = 'P' THEN
												 CASE
                                                   WHEN PTF_RESET <> PT_SEQ THEN C.RUNNING_TOTAL
                                                   ELSE
                                                     CASE 
                                                       WHEN SUBSEQUENT_PERIOD_PRICE_TYPE IN ( 'BQWAC', 'EQWAC', 'AVGQWAC', 'MQWAC', 'WAC' ) THEN SUBSEQUENT_PERIOD_PRICE  - TR.NET_SUBSEQUENT_PERIOD_PRICE
                                                       ELSE (C.RUNNING_TOTAL - TR.NET_SUBSEQUENT_PERIOD_PRICE) * (1+ R.PRICE_TOLERANCE/100)
                                                     END
                                                 END
												 END) > IIF(C.FLAG = 1
                                            OR RESET_PERIOD = RESET_SEQ, R.NEW_WAC_PRICE_VALUE-TR.NET_PRICE, C.PREV_WAC  ), 1, 0),
                            RESET_PERIOD = ( IIF(C.FLAG = 0, RESET_PERIOD + 1, 1) - 1 ) % RESET_SEQ + 1,
                            PTF_RESET = ( IIF(C.FLAG = 0, PTF_RESET + 1, 1) - 1 ) % PT_SEQ + 1,
                            PREV_WAC = IIF(C.FLAG = 1
                                            OR RESET_PERIOD = RESET_SEQ, R.NEW_WAC_PRICE_VALUE-TR.NET_PRICE, C.PREV_WAC  ),
                            R.PERIOD_SID
							,WAC_WITHOUT_NETTING = IIF(C.FLAG = 1
                                            OR RESET_PERIOD = RESET_SEQ, R.NEW_WAC_PRICE_VALUE, C.WAC_WITHOUT_NETTING  )
							,PRICE_CHANGE=CAST(IIF(C.FLAG = 1
                                            OR RESET_PERIOD = RESET_SEQ, 0,ISNULL(R.NEW_WAC_PRICE_VALUE/NULLIF(C.WAC_WITHOUT_NETTING,0),0)*100) AS NUMERIC(22,6))
                     FROM   REC_CTE C
                            JOIN #NETTING_LOGIC_CCPS R
                              ON R.PERIOD_SID = C.PERIOD_SID + 1
                                 AND C.CCP_DETAILS_SID = R.CCP_DETAILS_SID
                                 AND C.RS_CONTRACT_SID = R.RS_CONTRACT_SID
							JOIN #TEMP_REBATES TR ON TR.CCP_DETAILS_SID = R.CCP_DETAILS_SID
							 AND TR.RS_CONTRACT_SID = R.RS_CONTRACT_SID 
							 AND TR.PERIOD_SID = R.PERIOD_SID)
            UPDATE RP
            SET    RP.NEP_RESULT = RUNNING_TOTAL,
                   RP.WAC_RESULT = PREV_WAC,
				   RP.HARD_RESET_PERIODS = FLAG,
				   WAC_WITHOUT_NETTING=RC.WAC_WITHOUT_NETTING
				   ,PRICE_CHANGE=RC.PRICE_CHANGE
            FROM   #NETTING_LOGIC_CCPS RP
                   JOIN REC_CTE RC
                     ON RP.CCP_DETAILS_SID = RC.CCP_DETAILS_SID
                        AND RP.RS_CONTRACT_SID = RC.RS_CONTRACT_SID
                        AND RP.PERIOD_SID = RC.PERIOD_SID

			UPDATE TM
            SET    NET_MAP = C2.NEP_RESULT,
                   RESET_GRP = C2.RESET_GRP
				   ,NET_WAC=C2.WAC_RESULT
				   ,WAC=C2.WAC_WITHOUT_NETTING
				   ,PRICE_CHANGE=C2.PRICE_CHANGE
				   ,PPA=CASE
                     WHEN C2.WAC_RESULT > C2.NEP_RESULT THEN C2.WAC_RESULT - C2.NEP_RESULT
                     ELSE 0
                   END
            FROM   #TEMP_MAP_DATA TM
                   JOIN #NETTING_LOGIC_CCPS C2
                     ON C2.CCP_DETAILS_SID = TM.CCP_DETAILS_SID
                        AND TM.PERIOD_SID = C2.PERIOD_SID
                        AND TM.RS_CONTRACT_SID = C2.RS_CONTRACT_SID
        END
------------------update the net wac value for the ccp's--------------------
      UPDATE TM
      SET    WAC = A.WAC_RESULT,
             NET_WAC = CASE
                         WHEN ( A.WAC_RESULT - B.NET_PRICE ) > 0 THEN ( A.WAC_RESULT - B.NET_PRICE )
                         ELSE 0
                       END
      FROM   #NETTING_LOGIC_CCPS A
             JOIN #TEMP_REBATES B
               ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                  AND A.PERIOD_SID = B.PERIOD_SID
                  AND A.RS_CONTRACT_SID = B.RS_CONTRACT_SID
             JOIN #TEMP_MAP_DATA TM
               ON TM.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                  AND TM.PERIOD_SID = A.PERIOD_SID
                  AND TM.RS_CONTRACT_SID = A.RS_CONTRACT_SID
      WHERE  REBATE_FREQUENCY1 = 1
	  and not exists (select 1 from #NETTING_LOGIC_CCPS n where n.CCP_DETAILS_SID = a.CCP_DETAILS_SID and n.RS_CONTRACT_SID = a.RS_CONTRACT_SID and n.RESET_ELIGIBLE = 'yes' and n.RESET_TYPE = 'violation date')

  declare @ST_PPA_RESET_PERIODS  VARCHAR(200) = Concat ('ST_PPA_RESET_PERIODS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')) 
  --------------updating the reset information for the netting ccp's used for the price change calcualtion--------------------
  SET @SQL= concat('insert into ',@ST_PPA_RESET_PERIODS,'(CCP_DETAILS_SID,RS_CONTRACT_SID,PERIOD_SID,REBATE_FREQUENCY)
SELECT  CCP_DETAILS_SID,
                RS_CONTRACT_SID,
				R.PERIOD_SID,
                LEFT(REBATE_FREQUENCY, 1) AS REBATE_FREQUENCY
FROM   #NETTING_LOGIC_CCPS R 
WHERE  hard_reset_periods = 1 ')

exec sp_executesql @sql
  
  -----------------------calcualted the price change for the netting ccp's---------------------------
  ;
      WITH TEMP_MAP_DATA
           AS (SELECT *
               FROM   #TEMP_MAP_DATA a
               WHERE  REBATE_FREQUENCY = 1
			    and not exists (select 1 from #NETTING_LOGIC_CCPS n where n.CCP_DETAILS_SID = a.CCP_DETAILS_SID and n.RS_CONTRACT_SID = a.RS_CONTRACT_SID and n.RESET_ELIGIBLE = 'yes' and n.RESET_TYPE = 'violation date')---------cel-1465
),
           CTE
           AS (SELECT *,
                      CASE
                        WHEN RESET_GRP = LAG(RESET_GRP)
                                           OVER(
                                             PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID
                                             ORDER BY PERIOD_SID) THEN 100.00 * COALESCE(( ( NULLIF(WAC, 0) - LAG(WAC)
                                                                                                                OVER(
                                                                                                                  PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID
                                                                                                                  ORDER BY PERIOD_SID) ) / NULLIF(LAG(WAC)
                                                                                                                                                    OVER(
                                                                                                                                                      PARTITION BY CCP_DETAILS_SID, RS_CONTRACT_SID
                                                                                                                                                      ORDER BY PERIOD_SID), 0) ), 0)
                        ELSE 0
                      END CHANGE
               FROM   TEMP_MAP_DATA)
      UPDATE TM
      SET    PRICE_CHANGE = CHANGE,
             PPA = CASE
                     WHEN TM.NET_WAC > TM.NET_MAP THEN TM.NET_WAC - TM.NET_MAP
                     ELSE 0
                   END
      FROM   #TEMP_MAP_DATA TM
             JOIN CTE C
               ON C.CCP_DETAILS_SID = TM.CCP_DETAILS_SID
                  AND TM.PERIOD_SID = C.PERIOD_SID
                  AND TM.RS_CONTRACT_SID = C.RS_CONTRACT_SID

---------------------update the required information for the result-------------------------
      /*IF OBJECT_ID('TEMPDB..#TEMP_FINAL_MAP_DATA') IS NOT NULL
        DROP TABLE #TEMP_FINAL_MAP_DATA

      CREATE TABLE #TEMP_FINAL_MAP_DATA
        (
           ID                            INT IDENTITY(1, 1),
           CCP_DETAILS_SID               INT NOT NULL,
           RS_CONTRACT_SID               INT NOT NULL,
           PERIOD_SID                    INT NOT NULL,
           WAC                           NUMERIC(38, 15),
           NET_WAC                       NUMERIC(38, 15),
           NET_MAP                       NUMERIC(22, 6),
           PPA                           NUMERIC(22, 6),
           PRICE_CHANGE                  NUMERIC(22, 6),
           TOTAL_PRICE_PROTECTION_DOLLAR NUMERIC(22, 6),
           PROJECTION_UNITS              NUMERIC(22, 6),
           PPA_RATE                      NUMERIC(22, 6)
           PRIMARY KEY ( CCP_DETAILS_SID, RS_CONTRACT_SID, PERIOD_SID )
        );*/

      UPDATE RP
      SET    WAC = OA.WAC,
             NET_WAC = OA.NET_WAC,
             PPA = OA.PPA,
             PRICE_CHANGE = OA.PRICE_CHANGE
      FROM   #TEMP_MAP_DATA RP
             OUTER APPLY (SELECT TOP 1 WAC,
                                       NET_WAC,
                                       PPA,
                                       PRICE_CHANGE,
                                       PERIOD_SID
                          FROM   #TEMP_MAP_DATA NR
                          WHERE  RP.CCP_DETAILS_SID = NR.CCP_DETAILS_SID
                                 AND RP.RS_CONTRACT_SID = NR.RS_CONTRACT_SID
                                 AND RP.PERIOD_SID >= NR.PERIOD_SID
                                 AND WAC IS NOT NULL
                          ORDER  BY PERIOD_SID DESC) OA
	WHERE NOT EXISTS (SELECT 1 FROM #NETTING_LOGIC_CCPS N WHERE N.CCP_DETAILS_SID = RP.CCP_DETAILS_SID AND N.RS_CONTRACT_SID = RP.RS_CONTRACT_SID AND N.RESET_ELIGIBLE = 'YES' AND N.RESET_TYPE = 'VIOLATION DATE')



      UPDATE RP
      SET    NET_MAP = OA.NET_MAP
      FROM   #TEMP_MAP_DATA RP
             OUTER APPLY (SELECT TOP 1 NET_MAP,
                                       PERIOD_SID
                          FROM   #TEMP_MAP_DATA NR
                          WHERE  RP.CCP_DETAILS_SID = NR.CCP_DETAILS_SID
                                 AND RP.RS_CONTRACT_SID = NR.RS_CONTRACT_SID
                                 AND RP.PERIOD_SID >= NR.PERIOD_SID
                                 AND NET_MAP IS NOT NULL
                          ORDER  BY PERIOD_SID DESC) OA
	WHERE NOT EXISTS (SELECT 1 FROM #NETTING_LOGIC_CCPS N WHERE N.CCP_DETAILS_SID = RP.CCP_DETAILS_SID AND N.RS_CONTRACT_SID = RP.RS_CONTRACT_SID AND N.RESET_ELIGIBLE = 'YES' AND N.RESET_TYPE = 'VIOLATION DATE')


---------final update in the main table----------------------

   EXEC('
UPDATE PP
SET    PP.NETMAP = NET_MAP,
       PP.PRICE = WAC,
       PP.PROJECTION_SALES = IIF(NET_WAC - NET_MAP > 0, NET_WAC - NET_MAP, 0),
          PP.PROJECTION_DISCOUNT_UNITS = SP.PROJECTION_UNITS ,
          PP.PROJECTION_RATE = (SP.PROJECTION_UNITS * IIF(NET_WAC - NET_MAP > 0, NET_WAC - NET_MAP, 0)) / NULLIF(SP.PROJECTION_SALES,0) * 100,
          PP.PROJECTION_DISCOUNT_DOLLAR = (SP.PROJECTION_UNITS * IIF(NET_WAC - NET_MAP > 0, NET_WAC - NET_MAP, 0))
          ,TOTALDEDUCTIONS                             = (SP.PROJECTION_UNITS * IIF(NET_WAC - NET_MAP > 0, NET_WAC - NET_MAP, 0))
          ,NETPRICE                                    = NET_WAC      
          ,PRICEPROTECTIONAMOUNTPERUNIT  = IIF(NET_WAC - NET_MAP > 0, NET_WAC - NET_MAP, 0)
          ,PRICEPROTECTIONPERCENTAGE            = (SP.PROJECTION_UNITS * IIF(NET_WAC - NET_MAP > 0, NET_WAC - NET_MAP, 0)) / NULLIF(SP.PROJECTION_SALES,0) * 100
          ,TOTALPRICEPROTECTIONDEDUCTION = (SP.PROJECTION_UNITS * IIF(NET_WAC - NET_MAP > 0, NET_WAC - NET_MAP, 0))
          ,DEDUCTION_PER_UNIT                          = IIF(NET_WAC - NET_MAP > 0, NET_WAC - NET_MAP, 0)
FROM   '+@PROJECTION_TABLE+' PP
       JOIN #TEMP_MAP_DATA RP
         ON PP.CCP_DETAILS_SID = RP.CCP_DETAILS_SID
            AND PP.RS_CONTRACT_SID = RP.RS_CONTRACT_SID
            AND PP.PERIOD_SID = RP.PERIOD_SID
       JOIN '+@S_PROJECTION_TABLE+' SP
         ON SP.CCP_DETAILS_SID = PP.CCP_DETAILS_SID
            AND SP.PERIOD_SID = PP.PERIOD_SID
WHERE   EXISTS (SELECT 1
                   FROM   '+@NETTING_LOGIC_CCPS+' NLC
                   WHERE  NLC.CCP_DETAILS_SID = RP.CCP_DETAILS_SID
                          AND NLC.RS_CONTRACT_SID = RP.RS_CONTRACT_SID) ')


						  END
  END 

GO