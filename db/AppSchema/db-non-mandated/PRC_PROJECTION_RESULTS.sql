IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PROJECTION_RESULTS'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PROJECTION_RESULTS]
  END

GO

CREATE PROCEDURE [dbo].[PRC_PROJECTION_RESULTS](@PROJECTION_SID         VARCHAR(500),
                                                @PROJ_FREQUENCY         VARCHAR(20),
                                                @DISCOUNT_ID            NVARCHAR(MAX),
                                                @SCREEN_NAME            VARCHAR(50),
                                                @SESSION_ID             VARCHAR(50),
                                                @USER_ID                INT,
                                                @VIEW                   VARCHAR(20) = NULL,
                                                @CCP                    NVARCHAR(MAX) = NULL,
                                                @UOM_CODE               VARCHAR(50),
                                                @SALES_INCLUSION        BIT,
                                                @DEDUCTION_INCLUSION    BIT,
                                                @INDICATOR              CHAR(1),
                                                @PROGRAM                VARCHAR(100),
                                                @CUSTOM_VIEW_MASTER_SID INT=NULL,
                                                @CUSTOM_LEVEL_NO        INT=NULL)
AS
/*********** PULLING TOTAL LEVE VLUES OF CONTRACT SALES @WAC,TOTAL DISCOUTN,TOTAL RPU,TOTAL DISCOUNT % OF PROJECTIONS ATTACHED TO THE PARTICUALR PROJECTION SHOWING AS OUTPUT FOR BOTH PROJECTION_RESULTS SCRREN AND PROJECTION_VARICNACE SCRREN  ************/
/******     IN PROJECTION RESULT SCREEN WILL DISPLAY THE BOTH ACTUAL AND PROJECTION OF CONTRACT SALES,DISCOUNT&PPA .BUT VARIANCE SCREE WILL DISPLSY BOTH ACTUAL VALUES & PROJECTION ONLY FOR CONTRACT SALES REMAING WILL DISPLAY ONLY PROJECTION VALUE  *****/
/******     IN PROJECTION RESULT WILL PASS ONLY ONE PROJECTION AS INPUIT PARAMETER BUT VARIANCE MULTIPLE PROJECTION WILL PASS AS INPUT PARAMETER FROM THAT PROJECTION'S FIRST WHAT WE ARE PASSING THAT IT WILL SHOWN IN RESULTS AND REMAINING PROJECTION WILL BE SHOWN IN VARIANC SCRREN   *****/
/**********************************************************************************************************
** FILE NAME		:	PRC_PROJECTION_RESULTS.SQL
** PROCEDURE NAME	:	PRC_PROJECTION_RESULTS
** DESCRIPTION		:	  aggregation of projections attached to the particualr projection showing as output for both projection_result screen and projection_varicnace screen
** INPUT PARAMETERS	:	@PROJECTION_SID	- passing input as PROJECTION_MASTER_SID
						@PROJ_FREQUENCY - based on frequency it will display the data monthly,quarterly semi annualy and annulaly
						@DISCOUNT       - Passing inpuit as(RS_CONTRACT_SID) either REBATE_PROGRAM_TYPE OR RS_NAME based on Rebate level we will pull the discount projection value in discount projection tables
                        @SCREEN_NAME    - based on screen name :if @screen_name =''Assumption'' We will pass only one one projection as input parameter based on that we will calculate the total level sales projection values and discount projection value based rebate or RS_CONTRACT_SID & PPA for both actual & projection
						                  ,Based on Sales,Discount & PPA calculation we will calculate Netsales,Netprofit values.
										  If we pass input as @screen_name =''  Variance'' we will pass mulptiple projection as input and will display total level aggregation value of both Actual sales & projection values for only contract Sales WAC remaining will display only projection  projections attached to the first PROJECTIONS
						             	  screen_name<> assumptions it will display rest of projections display aggregation of projections attached to each projecton as pivot view
                        @VIEW           - if we pass view as pivot means result will be order by year and period other than pivot it will order by year and period
                        @SESSION_ID     - For each projection and session 
                        @CCP            - For the selected ccps only values actuals and projections  will aggregate

** OUTPUT PARAMETERS:	na
** AUTHOR NAME		:	@lakshmi Gomathi
** CREATION DATE	:	03/02/2016 - 
** CALLED BY		:   from application side they click on projection results screen the inside that proecure will call dispaly information 
**********************************************************************************************************
** CHANGE HISTORY
**********************************************************************************************************
** VER   DATE      LOCAL ALMTICKET NO      SUBTICKET NO   ONLINE ALM TICKET      AUTHOR                          DESCRIPTION 
** ---   --------  ---------             -------------        -------------    ----------------                  ------------------
** 1    03/10/2016  GAL-3565                GAL-4339           GALUAT-321       @LAKSHMI GOMATHI/@SANDEEP     Removed return related part in PR an PV procedure and i made changes in PPA related code in Projection result & Projection Variance.
                                            GAL-4343
								            GAL-4699                               
** 2    12/13/2016 GAL-8916/GAL-1163        GAL-8918            GAL-1163        @LAKSHMI GOMATHI/@BHRAMA REDDY Added RS_CONTRACT_SID column instead of RS_MODEL_SID .   
*********************************************************************************************************/
  -------------declaring variables--------------------------
  BEGIN
      SET NOCOUNT ON

      BEGIN TRY
          DECLARE @STARTFROM             DATE,
                  @PROJECTION_DATE       DATETIME,
                  @SP                    INT,
                  @SP_PROJ_SID           INT,
                  @TEMP_PROJ_SID         VARCHAR(500),
                  @START_PERIOD_SID      INT,
                  @END_PERIOD_SID        INT,
                  @ITEM_UOM              VARCHAR(50) = 'EACH',
                  @START_DATE            DATETIME,
                  @PROJ_START_PERIOD_SID INT,
                  @CURRENT_DATE          INT
          --------------GAL-808 IMPACT(Ibcluded business unit & GL-comp concept)
          ---------------------------------------------------implmented temp table changes for each session dynamicalyy----------------------------------------
          DECLARE @SALES_MASTER_TABLE           VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),----Actuals Sales values
                  @SALES_ACTUAL_TABLE           VARCHAR(200) = Concat ('ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),----Actuals Sales values
                  @SALES_PROJECTION_TABLE       VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),------Sales projection vales
                  @DISC_PROJECTION_MASTER_TABLE VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),------discoutn projection values
                  @DISC_PROJECTION_TABLE        VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),------discoutn projection values
                  @DISC_ACTUAL_TABLE            VARCHAR(200) = Concat ('ST_NM_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),----Actual discount values
                  @PPA_MASTER_TABLE             VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),-----PPA projection values
                  @PPA_PROJECTION_TABLE         VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),-----PPA projection values
                  @PPA_ACTUAL_TABLE             VARCHAR(200) = Concat ('ST_NM_ACTUAL_PPA_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),-------Actual PPA values
                  @CCP_HIERARCHY                VARCHAR(200) = Concat ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),---- ccp deatils information
                  @PRODUCT_FILE                 VARCHAR(100) = Concat ('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),-----lastest file of that product based on projection
                  @ITEM_UOM_TABLE               VARCHAR(100) = Concat ('ST_ITEM_UOM_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @CUSTOMER_TABLE_SALES VARCHAR(200) = Concat('ST_CUSTOMER_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @PRODUCT_TABLE_SALES VARCHAR(200) = Concat ('ST_PRODUCT_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @CUSTOM_TABLE_SALES  VARCHAR(200) = Concat ('ST_CUSTOM_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @CUSTOMER_TABLE_DISCOUNT VARCHAR(200) = Concat('ST_CUSTOMER_PV_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          DECLARE @PRODUCT_TABLE_DISCOUNT VARCHAR(200) = Concat ('ST_PRODUCT_PV_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @STATUS_TABLE           VARCHAR(200) = Concat ('ST_STATUS_TABLE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @CUSTOM_TABLE_DISCOUNT  VARCHAR(200) = Concat ('ST_CUSTOM_PV_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @sales_table            VARCHAR(200),
                  @discount_table         VARCHAR(200),
				  @CUSTOM_CCP_SALES VARCHAR(200) = CONCAT ('CUSTOM_CCP_SALES_',@CUSTOM_VIEW_MASTER_SID),
				  @CCP_PV_FILTERS VARCHAR(200) = Concat('ST_CCP_PV_FILTERS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))

          IF Object_id('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL -------IT CONTAIN CCP DETAIL'S 
            DROP TABLE #CCP_DETAILS_TEMP

          CREATE TABLE #CCP_DETAILS_TEMP
            (
               CCP_DETAILS_SID     INT,
               COMPANY_MASTER_SID  INT,
               ITEM_MASTER_SID     INT,
               CONTRACT_MASTER_SID INT,
               hierarchy_no        VARCHAR(8000),
               rs_contract_sid     INT
            )
		  set @CUSTOM_LEVEL_NO =(select max(level_no) from cust_view_details where CUSTOM_VIEW_MASTER_SID=@CUSTOM_VIEW_MASTER_SID)
          DECLARE @SQL NVARCHAR(MAX)

          IF @indicator = 'c'
            BEGIN
                SET @sales_table=@CUSTOMER_TABLE_SALES
                SET @discount_table=@CUSTOMER_TABLE_DISCOUNT
            END
          ELSE IF @indicator = 'P'
            BEGIN
                SET @sales_table=@PRODUCT_TABLE_SALES
                SET @discount_table=@PRODUCT_TABLE_DISCOUNT
            END
          ELSE IF @indicator NOT IN ( 'c', 'p' )
            BEGIN
                SET @sales_table=@CUSTOM_TABLE_SALES
                SET @discount_table=@CUSTOM_TABLE_DISCOUNT
            END

          IF @indicator IN ( 'c', 'p' )
            BEGIN
                SET @SQL = Concat('INSERT INTO #CCP_DETAILS_TEMP (
	CCP_DETAILS_SID
	,COMPANY_MASTER_SID
	,ITEM_MASTER_SID
	,CONTRACT_MASTER_SID,HIERARCHY_NO
	)
SELECT CH.CCP_DETAILS_SID
	,COMPANY_MASTER_SID
	,ITEM_MASTER_SID
	,CONTRACT_MASTER_SID ', CASE @indicator
                                                      WHEN 'C' THEN ', ch.CUST_HIERARCHY_NO'
                                                      WHEN 'P' THEN ',ch.PROD_HIERARCHY_NO'
                                                    END, '
FROM ', @CCP_HIERARCHY, ' CH
JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
	AND (
		EXISTS (
			SELECT 1
			FROM UDF_SPLITSTRING(@CCP, '','') U
			WHERE ', CASE @indicator
                                         WHEN 'C' THEN 'ch.CUST_HIERARCHY_NO'
                                         WHEN 'P' THEN 'ch.PROD_HIERARCHY_NO'
                                       END, ' like U.TOKEN +''%''
			)
		OR @CCP IS NULL
		)
			AND EXISTS (SELECT 1 FROM ', @CCP_PV_FILTERS, '  DMAS WHERE DMAS.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
			 AND DMAS.PV_FILTERS=1)
 ')

                EXEC Sp_executesql
                  @SQL,
                  N'@CCP VARCHAR(MAX)',
                  @CCP = @CCP
            END

          IF @indicator NOT IN ( 'c', 'p' )
            BEGIN
                SET @SQL = Concat('INSERT INTO #CCP_DETAILS_TEMP (
	CCP_DETAILS_SID
	,COMPANY_MASTER_SID
	,ITEM_MASTER_SID
	,CONTRACT_MASTER_SID,HIERARCHY_NO--,row_id 
	,rs_contract_sid
	)
SELECT distinct CH.CCP_DETAILS_SID
	,COMPANY_MASTER_SID
	,ITEM_MASTER_SID
	,CONTRACT_MASTER_SID --,ch.HIERARCHY_NO
	,rowid ,rs_contract_sid
FROM ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' ch
                     on  ch.CCP_DETAILS_SID = c.CCP_DETAILS_SID
JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
	AND (
		EXISTS (
			SELECT 1
			FROM UDF_SPLITSTRING(@CCP, '','') U
			WHERE HIERARCHY_NO  like U.TOKEN +''%''
			--WHERE HIERARCHY_NO  =U.TOKEN
			)
		OR @CCP IS NULL
		)
		 WHERE  C.CUST_VIEW_MASTER_SID= ', @CUSTOM_VIEW_MASTER_SID, '  
                        AND C.LEVEL_NO= ', @CUSTOM_LEVEL_NO, '
			AND EXISTS (SELECT 1 FROM ', @CCP_PV_FILTERS, '  DMAS WHERE DMAS.CCP_DETAILS_SID = C.CCP_DETAILS_SID
			AND (DMAS.RS_CONTRACT_SID = C.RS_CONTRACT_SID or C.RS_CONTRACT_SID =0) AND DMAS.PV_FILTERS=1
			)-- and nullif(C.RS_CONTRACT_SID,0) is not null
 ')

                EXEC Sp_executesql
                  @SQL,
                  N'@CCP VARCHAR(MAX)',
                  @CCP = @CCP
            END

          -------IT CONTAIN CCP DETAIL'S 
          IF Object_id('TEMPDB..#ITEM_UOM_DETAILS') IS NOT NULL
            DROP TABLE #ITEM_UOM_DETAILS

          CREATE TABLE #ITEM_UOM_DETAILS
            (
               ITEM_MASTER_SID INT,
               UOM_VALUE       NUMERIC(22, 6),
               UOM_CODE        VARCHAR(50)
            )

          SET @SQL = 'INSERT INTO #ITEM_UOM_DETAILS (
	ITEM_MASTER_SID
	,UOM_VALUE
	,UOM_CODE
	)
SELECT IU.ITEM_MASTER_SID
	,UOM_VALUE
	,UOM_CODE
FROM ' + @ITEM_UOM_TABLE + ' IU
WHERE IU.UOM_CODE = @UOM_CODE
	AND EXISTS (
		SELECT 1
		FROM #CCP_DETAILS_TEMP CD
		WHERE CD.ITEM_MASTER_SID = IU.ITEM_MASTER_SID
		)
 '

          EXEC Sp_executesql
            @SQL,
            N'@UOM_CODE VARCHAR(50)',
            @UOM_CODE = @UOM_CODE

          -----------------PASSING MULTIPLE PROJECTIONS AS INPUT CONVERTING INTO COMMA SEPERATED VALUES --------------------------
          IF Object_id('TEMPDB..#PROJECTION_MASTER') IS NOT NULL
            DROP TABLE #PROJECTION_MASTER

          CREATE TABLE #PROJECTION_MASTER
            (
               ID                    INT IDENTITY(1, 1),
               PROJECTION_MASTER_SID INT
            )

          ----------------------------------------------------------STORING THE OUTPUT RESULT OF PROJECTION VARIANCE TABLE--------------------------------------
          IF Object_id('TEMPDB..#PIVOT_RESULT') IS NOT NULL
            DROP TABLE #PIVOT_RESULT

          CREATE TABLE #PIVOT_RESULT
            (
               PROJECTION_ID                                      INT,
               [PERIOD]                                           SMALLINT,
               [YEAR]                                             INT,
               EX_FACTORY_SALES_ACCRUALS                          NUMERIC(33, 8),
               EX_FACTORY_SALES_ACTUALS                           NUMERIC(33, 8),
               EX_FACTORY_SALES_PROJECTED                         NUMERIC(33, 8),
               DEMAND_SALES_ACCRUALS                              NUMERIC(33, 8),
               DEMAND_SALES_ACTUALS                               NUMERIC(33, 8),
               DEMAND_SALES_PROJECTED                             NUMERIC(33, 8),
               INVENTORY_WITHDRAWAL_SALES_ACCRUALS                NUMERIC(33, 8),
               INVENTORY_WITHDRAWAL_SALES_ACTUALS                 NUMERIC(33, 8),
               INVENTORY_WITHDRAWAL_SALES_PROJECTED               NUMERIC(33, 8),
               EX_FACTORY_SALES_ACCRUAL_PERCENT                   NUMERIC(33, 8),
               EX_FACTORY_SALES_ACTUALS_PERCENT                   NUMERIC(33, 8),
               EX_FACTORY_SALES_PROJECTED_PERCENT                 NUMERIC(33, 8),
               DEMAND_SALES_ACCRUAL_PERCENT                       NUMERIC(33, 8),
               DEMAND_SALES_ACTUALS_PERCENT                       NUMERIC(33, 8),
               DEMAND_SALES_PROJECTED_PERCENT                     NUMERIC(33, 8),
               INVENTORY_WITHDRAWAL_SALES_ACCRUAL_PERCENT         NUMERIC(33, 8),
               INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT          NUMERIC(33, 8),
               INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT       NUMERIC(33, 8),
               CONTRACT_SALES_ACCRUAL                             NUMERIC(33, 8),
               CONTRACT_SALES_ACTUALS                             NUMERIC(33, 8),
               CONTRACT_SALES_PROJECTED                           NUMERIC(33, 8),
               CONTRACT_UNITS_ACCRUAL                             NUMERIC(33, 8),
               CONTRACT_UNITS_ACTUALS                             NUMERIC(33, 8),
               CONTRACT_UNITS_PROJECTED                           NUMERIC(33, 8),
               TOTAL_DISCOUNT_ACCRUAL                             NUMERIC(33, 8),
               TOTAL_DISCOUNT                                     NUMERIC(33, 8),
               TOTAL_DISCOUNT_PROJECTED                           NUMERIC(33, 8),
               TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE                  NUMERIC(33, 8),
               TOTAL_DISCOUNT_PERCENTAGE                          NUMERIC(33, 8),
               TOTAL_DISCOUNT_PROJECTED_PERCENTAGE                NUMERIC(33, 8),
               PPA_DISCOUNT_ACCRUAL                               NUMERIC(33, 8),
               PPA_DISCOUNT_ACTUALS                               NUMERIC(33, 8),
               PPA_DISCOUNT_PROJECTED                             NUMERIC(33, 8),
               PPA_DISCOUNT_ACCRUAL_PERCENTAGE                    NUMERIC(33, 8),
               PPA_DISCOUNT_ACTUALS_PERCENTAGE                    NUMERIC(33, 8),
               PPA_DISCOUNT_PROJECTED_PERCENTAGE                  NUMERIC(33, 8),
               TOTAL_DISCOUNT_RPU_ACCRUAL                         NUMERIC(33, 8),
               TOTAL_DISCOUNT_RPU_ACTUALS                         NUMERIC(33, 8),
               TOTAL_DISCOUNT_RPU_PROJECTED                       NUMERIC(33, 8),
               TOTAL_PPA_RPU_ACCRUAL                              NUMERIC(33, 8),
               TOTAL_PPA_RPU_ACTUALS                              NUMERIC(33, 8),
               TOTAL_PPA_RPU_PROJECTED                            NUMERIC(33, 8),
               TOTAL_RPU_ACCRUAL                                  NUMERIC(33, 8),
               TOTAL_RPU_ACTUAL                                   NUMERIC(33, 8),
               TOTAL_RPU_PROJECTED                                NUMERIC(33, 8),
               NET_SALES_ACCRUAL                                  NUMERIC(33, 8),
               NET_SALES                                          NUMERIC(33, 8),
               NET_SALES_PROJECTED                                NUMERIC(33, 8),
               COGS_ACCRUAL                                       NUMERIC(33, 8),
               COGS_ACTUALS                                       NUMERIC(33, 8),
               COGS_PROJECTED                                     NUMERIC(33, 8),
               NET_PROFIT_ACCRUAL                                 NUMERIC(33, 8),
               NET_PROFIT_ACTUAL                                  NUMERIC(33, 8),
               NET_PROFIT_PROJECTED                               NUMERIC(33, 8),
               TOTAL_RETURNS_RPU_ACCRUAL                          NUMERIC(33, 8),
               TOTAL_RETURNS_RPU_ACTUALS                          NUMERIC(33, 8),
               TOTAL_RETURNS_RPU_PROJECTED                        NUMERIC(33, 8),
               RETURNS_ACCRUAL_AMOUNT                             NUMERIC(33, 8),
               RETURNS_ACTUALS_AMOUNT                             NUMERIC(33, 8),
               RETURNS_PROJECTED_AMOUNT                           NUMERIC(33, 8),
               RETURNS_ACCRUAL_PERCENTAGE                         NUMERIC(33, 8),
               RETURNS_ACTUALS_PERCENTAGE                         NUMERIC(33, 8),
               RETURNS_PROJECTED_PERCENTAGE                       NUMERIC(33, 8),
               NET_SALES_OF_EX_FACTORY_ACCRUAL                    NUMERIC(33, 8),
               NET_SALES_OF_EX_FACTORY_ACTUALS                    NUMERIC(33, 8),
               NET_SALES_OF_EX_FACTORY_PROJECTED                  NUMERIC(33, 8),
               DISCOUNT_OF_EX_FACTORY_ACCRUAL                     NUMERIC(33, 8),
               DISCOUNT_OF_EX_FACTORY_ACTUALS                     NUMERIC(33, 8),
               DISCOUNT_OF_EX_FACTORY_PROJECTED                   NUMERIC(33, 8),
               NET_EX_FACTORY_SALES_ACTUALS                       NUMERIC(33, 8),
               NET_EX_FACTORY_SALES_PROJECTED                     NUMERIC(33, 8),
               NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS   NUMERIC(33, 8),
               NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED NUMERIC(33, 8),
               NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACCRUAL   NUMERIC(33, 8),
               NET_EX_FACTORY_SALES_ACCRUAL                       NUMERIC(33, 8)
            )

          SET @TEMP_PROJ_SID = Replace(@PROJECTION_SID + ',', ',,', ',')

          INSERT INTO #PROJECTION_MASTER
                      (PROJECTION_MASTER_SID)
          SELECT *
          FROM   Udf_splitstring(@PROJECTION_SID, ',')

          DECLARE @FIRST_PROJ_SID INT

          SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID
          FROM   #PROJECTION_MASTER PM
          WHERE  ID = 1

          -------------------------------GALUAT_29 CHANGES----------------------------
          ------PULLING ACTUAL START_DATE , ACTUAL_END_DATE BASED ON CURRENT -3 CONCEPT & PROJ_START_PERIOD_DATE,PROJ_END_PERIOD_DATE BASED ON PROJECTION FROM PROJECTION MASTER TABLE -----------
          SELECT TOP 1 @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0),
                       @START_DATE = Dateadd(dd, 1, Eomonth(FROM_DATE, -1)),
                       @PROJECTION_DATE = Dateadd(dd, 1, Eomonth(TO_DATE, -1))
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
          ORDER  BY PROJECTION_MASTER_SID

          SELECT @START_PERIOD_SID = Max(CASE
                                           WHEN PERIOD_DATE = @STARTFROM THEN PERIOD_SID
                                         END),
                 @END_PERIOD_SID = Max(CASE
                                         WHEN PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0) THEN PERIOD_SID
                                       END),
                 @PROJ_START_PERIOD_SID = Max(CASE
                                                WHEN PERIOD_DATE = @START_DATE THEN PERIOD_SID
                                              END)
          FROM   PERIOD
          WHERE  PERIOD_DATE IN ( @STARTFROM, @PROJECTION_DATE, @START_DATE )

          -----------------PERIOD CONVERISON--------------------
          IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
            DROP TABLE #PERIOD;

          SELECT PERIOD_SID,
                 PERIOD_DATE,
                 MONTH,
                 QUARTER,
                 SEMI_ANNUAL,
                 YEAR,
                 CASE
                   WHEN LEFT(@PROJ_FREQUENCY, 1) = 'M' THEN MONTH
                   WHEN LEFT(@PROJ_FREQUENCY, 1) = 'Q' THEN QUARTER
                   WHEN LEFT(@PROJ_FREQUENCY, 1) = 'S' THEN SEMI_ANNUAL
                   ELSE 1
                 END AS PERIOD
          INTO   #PERIOD
          FROM   PERIOD
          WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID

          SELECT @CURRENT_DATE = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(DD, 1, Eomonth(Getdate(), -1))

          -------------------------pulling ccp inforamtion for particular projection based on input projection---------------------------------
          -------------------------------------------------------------------------------------
          DECLARE @ITEMID [DBO].[UDT_ITEM]

          INSERT INTO @ITEMID
          SELECT DISTINCT ITEM_MASTER_SID
          FROM   #CCP_DETAILS_TEMP

          IF Object_id('TEMPDB..#ITEM_PRICING') IS NOT NULL
            DROP TABLE #ITEM_PRICING

          SELECT ITEM_MASTER_SID,
                 PERIOD_SID,
                 PRICING_QUALIFIER,
                 ITEM_PRICE
          INTO   #ITEM_PRICING
          FROM   [DBO].[Udf_item_pricing](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, @ITEM_UOM)

          IF Object_id('TEMPDB..#DATA_TABLE') IS NOT NULL
            DROP TABLE #DATA_TABLE

          CREATE TABLE #DATA_TABLE
            (
               HIERARCHY_NO VARCHAR(8000),
               Period       INT,
               year         INT,
               PERIOD_SID   INT,
               PRIMARY KEY (year, Period, HIERARCHY_NO)
            )

          INSERT INTO #DATA_TABLE
                      (HIERARCHY_NO,
                       YEAR,
                       PERIOD,
                       PERIOD_SID)
          SELECT HIERARCHY_NO,
                 YEAR,
                 PERIOD,
                 Min(PERIOD_SID) PERIOD_SID
          --INTO #DATA_TABLE
          FROM   (SELECT DISTINCT HIERARCHY_NO
                  FROM   #CCP_DETAILS_TEMP) A
                 CROSS JOIN #PERIOD P
          GROUP  BY HIERARCHY_NO,
                    YEAR,
                    PERIOD

          DECLARE @SQL_ACC NVARCHAR(max)

          IF Object_id('TEMPDB..#RS_DATA') IS NOT NULL
            DROP TABLE #RS_DATA

          CREATE TABLE #RS_DATA
            (
               RS_CONTRACT_SID INT
            )

          SET @SQL_ACC = 'INSERT INTO #RS_DATA (RS_CONTRACT_SID)
	SELECT DISTINCT RS_CONTRACT_SID  FROM  '
                         + @DISC_PROJECTION_MASTER_TABLE
                         + ' RS WHERE EXISTS( SELECT 1 FROM '+@CCP_PV_FILTERS+' CPV WHERE CPV.PV_FILTERS = 1  AND CPV.CCp_DETAILS_SID=RS.CCp_DETAILS_SID AND CPV.RS_CONTRACT_sID=RS.RS_CONTRACT_sID)
	AND (EXISTS (
			SELECT 1
			FROM [DBO].[UDF_SPLITSTRING](@DISCOUNT_ID, '','') A
			WHERE A.TOKEN = CONVERT(VARCHAR(100),RS.RS_CONTRACT_SID)
			) OR @DISCOUNT_ID IS NULL)'

          --EXEC Sp_executesql
          --  @SQL_ACC,
          --  N'@DISCOUNT_ID NVARCHAR(MAX)',
          --  @DISCOUNT_ID = @DISCOUNT_ID

          IF Object_id('TEMPDB..#RS_INFO') IS NOT NULL
            DROP TABLE #RS_INFO

          CREATE TABLE #RS_INFO
            (
               -- CCP_DETAILS_SID INT,
               RS_CONTRACT_SID INT,
               SELECTED_LEVEL  INT
            )

           IF @indicator IN ( 'c', 'p' )
            BEGIN
                SET @sql=Concat('SELECT --CCP_DETAILS_SID,
		R.RS_CONTRACT_SID,
            --CASE @PROGRAM
            --  WHEN ''SCHEDULE ID'' THEN R.RS_CONTRACT_SID
            --  WHEN ''PROGRAM'' THEN R.RS_CONTRACT_SID
            --  --WHEN ''SCHEDULE TYPE'' THEN R_TYPE.DESCRIPTION
            --  --WHEN ''PROGRAM CATEGORY'' THEN RPT.DESCRIPTION
            --  --WHEN ''PROGRAM TYPE'' THEN RPT.DESCRIPTION
            --  --WHEN ''SCHEDULE CATEGORY'' THEN RC.DESCRIPTION			  
            --  WHEN ''SCHEDULE TYPE'' THEN RS_TYPE
            --  WHEN ''PROGRAM CATEGORY'' THEN REBATE_PROGRAM_TYPE
            --  WHEN ''PROGRAM TYPE'' THEN REBATE_PROGRAM_TYPE
            --  WHEN ''SCHEDULE CATEGORY'' THEN RS_CATEGORY
            --  WHEN ''UDC1'' THEN UD.UDC1
            --  WHEN ''UDC2'' THEN UD.UDC2
            --  WHEN ''UDC3'' THEN UD.UDC3
            --  WHEN ''UDC4'' THEN UD.UDC4
            --  WHEN ''UDC5'' THEN UD.UDC5
            --  WHEN ''UDC6'' THEN UD.UDC6
            --END 
			',CASE @PROGRAM
              WHEN 'SCHEDULE ID' THEN' R.RS_CONTRACT_SID'
              WHEN 'PROGRAM' THEN' R.RS_CONTRACT_SID	'
			  --WHEN ''SCHEDULE TYPE'' THEN R_TYPE.DESCRIPTION
              --WHEN ''PROGRAM CATEGORY'' THEN RPT.DESCRIPTION
              --WHEN ''PROGRAM TYPE'' THEN RPT.DESCRIPTION
              --WHEN ''SCHEDULE CATEGORY'' THEN RC.DESCRIPTION			  
              WHEN 'SCHEDULE TYPE' THEN' RS_TYPE'
              WHEN 'PROGRAM CATEGORY' THEN' REBATE_PROGRAM_TYPE'
              WHEN 'PROGRAM TYPE' THEN' REBATE_PROGRAM_TYPE'
              WHEN 'SCHEDULE CATEGORY' THEN' RS_CATEGORY'
              WHEN 'UDC1' THEN ' UD.UDC1	'
              WHEN 'UDC2' THEN ' UD.UDC2	'
              WHEN 'UDC3' THEN ' UD.UDC3	'
              WHEN 'UDC4' THEN ' UD.UDC4	'
              WHEN 'UDC5' THEN ' UD.UDC5	'
              WHEN 'UDC6' THEN ' UD.UDC6	'
            END ,' SELECTED_LEVEL
     FROM   RS_CONTRACT R
            --LEFT JOIN HELPER_TABLE R_TYPE
            --       ON R_TYPE.HELPER_TABLE_SID = R.RS_TYPE
            --LEFT JOIN HELPER_TABLE RPT
            --       ON RPT.HELPER_TABLE_SID = R.REBATE_PROGRAM_TYPE
            --LEFT JOIN HELPER_TABLE RC
            --       ON RC.HELPER_TABLE_SID = R.RS_CATEGORY
            --LEFT JOIN (SELECT U.MASTER_SID,
            --                  U1.DESCRIPTION AS UDC1,
            --                  U2.DESCRIPTION AS UDC2,
            --                  U3.DESCRIPTION AS UDC3,
            --                  U4.DESCRIPTION AS UDC4,
            --                  U5.DESCRIPTION AS UDC5,
            --                  U6.DESCRIPTION UDC6
            --           FROM   UDCS U
            --                  LEFT JOIN HELPER_TABLE U1
            --                         ON U1.HELPER_TABLE_SID = U.UDC1
            --                  LEFT JOIN HELPER_TABLE U2
            --                         ON U2.HELPER_TABLE_SID = U.UDC2
            --                  LEFT JOIN HELPER_TABLE U3
            --                         ON U3.HELPER_TABLE_SID = U.UDC3
            --                  LEFT JOIN HELPER_TABLE U4
            --                         ON U4.HELPER_TABLE_SID = U.UDC4
            --                  LEFT JOIN HELPER_TABLE U5
            --                         ON U5.HELPER_TABLE_SID = U.UDC5
            --                  LEFT JOIN HELPER_TABLE U6
            --                         ON U6.HELPER_TABLE_SID = U.UDC6
            --           WHERE  MASTER_TYPE = ''RS_CONTRACT'') UD
			INNER JOIN ', @DISC_PROJECTION_MASTER_TABLE, ' D
                    on  R.RS_CONTRACT_SID = D.RS_CONTRACT_SID --AND D.PV_FILTERS = 1
			INNER JOIN ', @CCP_PV_FILTERS, ' D1
                    on  D1.RS_CONTRACT_SID = D.RS_CONTRACT_SID --AND D.PV_FILTERS = 1
					and  D1.CCP_DETAILS_SID=d.CCP_DETAILS_SID AND D1.PV_FILTERS = 1
			LEFT JOIN UDCS UD
                   ON UD.MASTER_SID = R.RS_CONTRACT_SID
				   and MASTER_TYPE = ''RS_CONTRACT''    
			WHERE exists (select 1 from [UDF_SPLITSTRING](@DISCOUNT_ID, '','') hd where hd.token= ',CASE @PROGRAM
              WHEN 'SCHEDULE ID' THEN' R.RS_CONTRACT_SID'
              WHEN 'PROGRAM' THEN' R.RS_CONTRACT_SID	'	  
              WHEN 'SCHEDULE TYPE' THEN' RS_TYPE'
              WHEN 'PROGRAM CATEGORY' THEN' REBATE_PROGRAM_TYPE'
              WHEN 'PROGRAM TYPE' THEN' REBATE_PROGRAM_TYPE'
              WHEN 'SCHEDULE CATEGORY' THEN' RS_CATEGORY'
              WHEN 'UDC1' THEN ' UD.UDC1	'
              WHEN 'UDC2' THEN ' UD.UDC2	'
              WHEN 'UDC3' THEN ' UD.UDC3	'
              WHEN 'UDC4' THEN ' UD.UDC4	'
              WHEN 'UDC5' THEN ' UD.UDC5	'
              WHEN 'UDC6' THEN ' UD.UDC6	'
            END ,'
			) or @DISCOUNT_ID is null')

                INSERT INTO #RS_INFO
                EXEC Sp_executesql
                  @sql,
                  N'@DISCOUNT_ID varchar(max),@PROGRAM varcHar(100)',
                  @DISCOUNT_ID=@DISCOUNT_ID,
                  @PROGRAM=@PROGRAM 
				  
				 
            END

          IF @indicator NOT IN ( 'c', 'p' )
            BEGIN
                SET @sql=Concat('INSERT INTO #RS_INFO (RS_CONTRACT_SID)
	  SELECT --CCP_DETAILS_SID,
		RS_CONTRACT_SID
		from  ', @DISC_PROJECTION_MASTER_TABLE, ' D
		where exists (select 1 from #CCP_DETAILS_TEMP cdt where cdt.ccp_details_sid= D.ccp_details_sid
		and (cdt.RS_CONTRACT_SID= D.RS_CONTRACT_SID or cdt.rs_contract_sid is null) )
		and exists (select 1 from ',@CCP_PV_FILTERS,' cdt where cdt.ccp_details_sid= D.ccp_details_sid
		and (cdt.RS_CONTRACT_SID= D.RS_CONTRACT_SID ) and cdt.PV_FILTERS = 1)
		')
		
                EXEC Sp_executesql
                  @sql
            END

          IF Object_id('TEMPDB..#ACCRUAL_DISCOUNT') IS NOT NULL
            DROP TABLE #ACCRUAL_DISCOUNT;

          CREATE TABLE #ACCRUAL_DISCOUNT
            (
               hierarchy_no    VARCHAR(8000),
               period          SMALLINT,
               year            INT,
               DISCOUNT_AMOUNT NUMERIC(33, 8)
            )

          SET @SQL_ACC= ' ;
WITH CTE
AS (
	SELECT DISTINCT COMPANY_MASTER_SID
		,CONTRACT_MASTER_SID
		,ITEM_MASTER_SID		
		,A.CCP_DETAILS_SID
		,PERIOD_SID
		,PERIOD_DATE
		,YEAR
		,RS.RS_CONTRACT_SID
		,RS.RS_MODEL_SID,a.hierarchy_no,period
	FROM #CCP_DETAILS_TEMP A
	JOIN ' + @DISC_PROJECTION_MASTER_TABLE
                        + ' RS ON RS.CCP_DETAILS_SID = A.CCP_DETAILS_SID
	AND PV_FILTERS = 1
	JOIN #PERIOD P on 1=1
	--JOIN PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
	--		AND @END_PERIOD_SID
	WHERE EXISTS (
			SELECT 1
			--FROM #RS_DATA Aa
			FROM #RS_INFO Aa
			WHERE Aa.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
			) 
	)
INSERT INTO #ACCRUAL_DISCOUNT (
	HIERARCHY_NO,PERIOD,YEAR
	,DISCOUNT_AMOUNT
	)
select hierarchy_no,period,year,SUM(DISCOUNT_AMOUNT) from (SELECT A2.CCP_DETAILS_SID
	,PERIOD_SID
	,A2.RS_CONTRACT_SID
	,SUM(DEDUCTION_AMOUNT) / (DATEDIFF(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE) + 1) DISCOUNT_AMOUNT,hierarchy_no,period,year
FROM ACCRUAL_MASTER A1
JOIN CTE A2 ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, 0))))
		AND CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_END_DATE, 0))))
	AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID
	AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID
	AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID
	AND A2.RS_MODEL_SID = A1.RS_MODEL_SID
	AND DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, - 1)) >= @STARTFROM
	AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJECTION_DATE
GROUP BY A2.CCP_DETAILS_SID
	,PERIOD_SID
	,A2.RS_CONTRACT_SID
	,ACCRUAL_PERIOD_START_DATE
	,ACCRUAL_PERIOD_END_DATE,hierarchy_no,period,year)a group by hierarchy_no,period,year'

          EXEC Sp_executesql
            @SQL_ACC,
            N'@START_PERIOD_SID INT,@END_PERIOD_SID INT,@STARTFROM DATETIME,@PROJECTION_DATE DATETIME, @DISCOUNT_ID NVARCHAR(MAX)',
            @START_PERIOD_SID = @START_PERIOD_SID,
            @END_PERIOD_SID = @END_PERIOD_SID,
            @STARTFROM = @STARTFROM,
            @PROJECTION_DATE = @PROJECTION_DATE,
            @DISCOUNT_ID = @DISCOUNT_ID

          ------------------------------------IN SCREEN NAME ='ASSUMPTION' WILL DISPLAY BOTH ACTUAL & PROJECTION VALUE FOR TOTAL LEVEL OF CONTRACT SALE,DISCOUNT AMOUNT,DISOCUNT PROJECTION & PPA.
          IF @SCREEN_NAME = 'ASSUMPTIONS'
            BEGIN
                IF @PROJ_FREQUENCY = 'MONTHLY' ------------------------------CALCULATING OR AGGREGATING THE MONTH WISE 
                  BEGIN
                      DECLARE @SQL_M NVARCHAR(MAX) = ''

                      SET @SQL_M = 'SELECT @PROJECTION_SID                    PROJECTION_ID,
                             EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                             EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                             GTS_UNITS = Isnull(GTS.UNITS, 0),
                             P.[MONTH],
                             P.[YEAR],
                             CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                             CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                             CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                             CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                             TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) +
                             --                              + COALESCE(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(PPA.PPA_ACTUAL_SALES, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) +
                             --                                        + COALESCE(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(PPA.PPA_PROJECTION_SALES, 0), 0) ) * 100,
                             ----DISC.CONTRACT_DISCOUNT_ACTUALS,  SALES.CONTRACT_SALES_ACTUALS,    PPA.PPA_DISCOUNT_ACTUALS,  PPA.PPA_ACTUAL_SALES,   
                             EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                             PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                             PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
                             NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
                             NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
                             DEMAND_SALES_ACTUAL = Isnull(GTS.DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = Isnull(GTS.DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = Isnull(GTS.ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = Isnull(GTS.FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             --TOTAL_RPU_ACTUAL = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0)
                             --                     + COALESCE(PPA.PPA_RPU_ACTUALS / NULLIF(PPA.PPA_ACTUAL_UNITS, 0), 0) ),
                             --TOTAL_RPU_PROJECTED = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0)
                             --                        + COALESCE(PPA.PPA_RPU_PROJECTED / NULLIF(PPA.PPA_PROJECTION_UNITS, 0), 0) ),
                             COGS_ACTUALS = Isnull(SALES.COGS_ACTUALS, 0),
                             COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUALs, 0) ) ),
                             NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
							 
							 
                             RETURNS_ACTUAL_AMOUNT = 000000.0,
                             RETURNS_ACTUAL_PERCENTAGE = 000000.0,
                             RETURNS_PROJECTED_AMOUNT = 000000.0,
                             RETURNS_PROJECTED_PERCENTAGE = 000000.0,
                             TOTAL_RETURNS_RPU_ACTAULS = 000000.0,
                             TOTAL_RETURNS_RPU_PROJECTED = 000000.0,
							 NET_SALES_OF_EX_FACTORY_ACTUALS = COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
                             NET_SALES_OF_EX_FACTORY_PROJECTED = COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
							,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							 DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
							 
                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = Sum(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												p.PERIOD_SID
                              FROM   '
                                   + @PRODUCT_FILE
                                   + ' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                              GROUP  BY p.PERIOD_SID
							  ) GTS
                             LEFT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                               PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID),
                                               CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                               CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                               COGS_ACTUALS = Sum(COGS_ACTUAL),
                                               COGS_PROJECTED = Sum(COGS_PROJECTED)
                                        --, ITEM_MASTER_SID = COALESCE(ACT.ITEM_MASTER_SID, PROJ.ITEM_MASTER_SID)
                                        FROM   (SELECT CCP.CCP_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                FROM   '
                                   + @SALES_ACTUAL_TABLE
                                   + ' NAS
                                                       INNER JOIN #CCP_DETAILS_TEMP CCP
                                                               ON CCP.CCP_DETAILS_SID = NAS.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT CCP.CCP_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                          FROM   '
                                   + @SALES_PROJECTION_TABLE
                                   + ' NPS
                                                                 INNER JOIN #CCP_DETAILS_TEMP CCP
                                                                         ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SALES
                                    ON SALES.PERIOD_SID = GTS.PERIOD_SID
                             INNER JOIN PERIOD P
                                     ON P.PERIOD_SID = COALESCE(SALES.PERIOD_SID, GTS.PERIOD_SID)
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                               PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        FROM   (SELECT CCP_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_CONTRACT_SID
                                                FROM   '
                                   + @DISC_ACTUAL_TABLE
                                   + ' NAD
                                                WHERE   EXISTS (SELECT 1
                                                                   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                   WHERE  A.TOKEN = NAD.RS_CONTRACT_SID)
                                               --AND NAD.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT CCP_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_CONTRACT_SID
                                                          FROM   '
                                   + @DISC_PROJECTION_TABLE
                                   + ' NDP
                                                          WHERE   EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_CONTRACT_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) DISC
                                    ON DISC.PERIOD_SID = P.PERIOD_SID
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        FROM   (SELECT NAP.CCP_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_CONTRACT_SID,
                                                       ACTUAL_SALES = NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS = NS.ACTUAL_UNITS
                                                FROM   '
                                   + @PPA_ACTUAL_TABLE
                                   + ' NAP
                                                       INNER JOIN '
                                   + @SALES_ACTUAL_TABLE
                                   + ' NS
                                                               ON NS.CCP_DETAILS_SID = NAP.CCP_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                 --AND NAP.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT NPP.CCP_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_CONTRACT_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   '
                                   + @PPA_PROJECTION_TABLE
                                   + ' NPP
                                                                 INNER JOIN '
                                   + @SALES_PROJECTION_TABLE
                                   + ' NS
                                                                         ON NS.CCP_DETAILS_SID = NPP.CCP_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE    NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) PPA
                                    ON PPA.PERIOD_SID = SALES.PERIOD_SID
                      ORDER  BY SALES.PERIOD_SID'

                      EXEC Sp_executesql
                        @SQL_M,
                        N'@PROJECTION_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID NVARCHAR(MAX),@START_PERIOD_SID INT',
                        @PROJECTION_SID = @PROJECTION_SID,
                        @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
                        @END_PERIOD_SID = @END_PERIOD_SID,
                        @START_PERIOD_SID = @START_PERIOD_SID,
                        @DISCOUNT_ID = @DISCOUNT_ID
                  END
                ELSE IF @PROJ_FREQUENCY = 'QUARTERLY' ----------------------------------QUARTERLY WISE AGGREGATING AND GETTIMG THE FILE TYPES ,SALES ,DISOCUN &PPA
                  BEGIN
                      DECLARE @SQL_Q NVARCHAR(MAX) = ''

                      SET @SQL_Q = Concat ('SELECT @PROJECTION_SID                    PROJECTION_ID,
                             EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                             EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                             GTS_UNITS = Isnull(GTS.UNITS, 0),
                             COALESCE(SALES.[QUARTER], GTS.QUARTERLY) AS [QUARTER],
                             COALESCE(SALES.[YEAR], GTS.YEARLY)       AS [YEAR],
                             CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                             CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                             CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                             CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                             TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) +
                             --                              + COALESCE(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(PPA.PPA_ACTUAL_SALES, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) +
                             --                                        + COALESCE(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(PPA.PPA_PROJECTION_SALES, 0), 0) ) * 100,
                             ----DISC.CONTRACT_DISCOUNT_ACTUALS,  SALES.CONTRACT_SALES_ACTUALS,    PPA.PPA_DISCOUNT_ACTUALS,  PPA.PPA_ACTUAL_SALES,   
                             EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                             PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                             PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
                             NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
                             NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
                             DEMAND_SALES_ACTUAL = Isnull(GTS.DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = Isnull(GTS.DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = Isnull(GTS.ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = Isnull(GTS.FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             --TOTAL_RPU_ACTUAL = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0)
                             --                     + COALESCE(PPA.PPA_RPU_ACTUALS / NULLIF(PPA.PPA_ACTUAL_UNITS, 0), 0) ),
                             --TOTAL_RPU_PROJECTED = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0)
                             --                        + COALESCE(PPA.PPA_RPU_PROJECTED / NULLIF(PPA.PPA_PROJECTION_UNITS, 0), 0) ),
                             COGS_ACTUALS = Isnull(SALES.COGS_ACTUAL, 0),
                             COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUAL, 0) ) ),
                             NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
																									  
						
                             RETURNS_ACTUAL_AMOUNT = 000000.0,
                             RETURNS_ACTUAL_PERCENTAGE = 000000.0,
                             RETURNS_PROJECTED_AMOUNT = 000000.0,
                             RETURNS_PROJECTED_PERCENTAGE = 000000.0,
                             TOTAL_RETURNS_RPU_ACTAULS = 000000.0,
                             TOTAL_RETURNS_RPU_PROJECTED = 000000.0,
							 NET_SALES_OF_EX_FACTORY_ACTUALS = COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
                             NET_SALES_OF_EX_FACTORY_PROJECTED = COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
							,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							 DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
					
                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),
									 DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES),
                                                ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS),
                                                DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                                FOR_GROSS_UNITS = Sum(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),
												GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												QUARTER QUARTERLY,
                                     YEAR YEARLY 
                              FROM   '
                                           + @PRODUCT_FILE
                                           + ' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                              GROUP  BY QUARTER,YEAR 
							  ) GTS
                             LEFT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                               [QUARTER],
                                               [YEAR],
                                               CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                               CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                               COGS_ACTUAL = Sum(COGS_ACTUAL),
                                               COGS_PROJECTED = Sum(COGS_PROJECTED)
                                        FROM   (SELECT CCP.CCP_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                FROM   ', @SALES_ACTUAL_TABLE, ' NAS
                                                       INNER JOIN #CCP_DETAILS_TEMP CCP
                                                               ON NAS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT CCP.CCP_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                          FROM   ', @SALES_PROJECTION_TABLE, ' NPS
                                                                 INNER JOIN #CCP_DETAILS_TEMP CCP
                                                                         ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) SALES
                                    ON SALES.QUARTER = GTS.QUARTERLY
                                       AND SALES.YEAR = GTS.YEARLY
                         
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = ( ACTUAL_SALES ),
                                               CONTRACT_DISCOUNT_PROJECTED = ( PROJECTION_SALES ),
                                               COALESCE(ACT.[QUARTER], PROJ.QUARTER) QUARTER,
                                               COALESCE(ACT.[YEAR], PROJ.YEAR)       YEAR
                                        FROM   (SELECT P.QUARTER,
                                               P.YEAR,
                                               Sum(ACTUAL_SALES) ACTUAL_SALES
                                                FROM   ', @DISC_ACTUAL_TABLE, ' NAD
                                                       INNER JOIN PERIOD P
                                                               ON P.PERIOD_SID = NAD.PERIOD_SID
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                               WHERE  A.TOKEN = NAD.RS_CONTRACT_SID)
                                                GROUP  BY P.QUARTER,
                                                          P.YEAR) ACT
                                               FULL JOIN (SELECT P.QUARTER,
                                                         P.YEAR,
                                                         Sum(PROJECTION_SALES) PROJECTION_SALES
                                                          FROM   ', @DISC_PROJECTION_TABLE, ' NDP
                                                                 INNER JOIN PERIOD P
                                                                         ON P.PERIOD_SID = NDP.PERIOD_SID
                                                          WHERE   EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_CONTRACT_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID
                                                          GROUP  BY P.QUARTER,
                                                                    P.YEAR) PROJ
                                                      ON 
                                               ACT.YEAR = PROJ.YEAR
                                               AND ACT.QUARTER = PROJ.QUARTER
                                       ) DISC
                                    ON DISC.[QUARTER] = SALES.[QUARTER]
                                       AND DISC.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               [QUARTER],
                                               [YEAR]
                                        FROM   (SELECT NAP.CCP_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_CONTRACT_SID,
                                                       ACTUAL_SALES =NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS =NS.ACTUAL_UNITS
                                                FROM   ', @PPA_ACTUAL_TABLE, ' NAP
                                                       INNER JOIN ', @SALES_ACTUAL_TABLE, ' NS
                                                               ON NS.CCP_DETAILS_SID = NAP.CCP_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT NPP.CCP_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_CONTRACT_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   ', @PPA_PROJECTION_TABLE, ' NPP
                                                                 INNER JOIN ', @SALES_PROJECTION_TABLE, ' NS
                                                                         ON NS.CCP_DETAILS_SID = NPP.CCP_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE  NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) PPA
                                    ON PPA.[QUARTER] = SALES.[QUARTER]
                                       AND PPA.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY),
                                COALESCE(SALES.[QUARTER], GTS.QUARTERLY)')

                      EXEC Sp_executesql
                        @SQL_Q,
                        N'@PROJECTION_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID NVARCHAR(MAX),@START_PERIOD_SID INT',
                        @PROJECTION_SID = @PROJECTION_SID,
                        @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
                        @END_PERIOD_SID = @END_PERIOD_SID,
                        @START_PERIOD_SID = @START_PERIOD_SID,
                        @DISCOUNT_ID = @DISCOUNT_ID
                  END
                ELSE IF @PROJ_FREQUENCY = 'SEMI-ANNUAL' ------------------SEMI-ANNUAL WISE AGGREGATING AND GETTIMG THE FILE TYPES ,SALES ,DISOCUN &PPA
                  BEGIN
                      DECLARE @SQL_S NVARCHAR(MAX) = ''

                      SET @SQL_S = Concat ('SELECT @PROJECTION_SID                    PROJECTION_ID,
                             EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                             EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                             GTS_UNITS = Isnull(GTS.UNITS, 0),
                             COALESCE(SALES.SEMI_ANNUAL, GTS.SEMI_ANNUAL)SEMI_ANNUAL,
                             COALESCE(SALES.[YEAR], GTS.YEARLY)          AS YEAR,
                             CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                             CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                             CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                             CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                             TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) +
                             --                              + COALESCE(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(PPA.PPA_ACTUAL_SALES, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) +
                             --                                        + COALESCE(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(PPA.PPA_PROJECTION_SALES, 0), 0) ) * 100,
                             ----DISC.CONTRACT_DISCOUNT_ACTUALS,  SALES.CONTRACT_SALES_ACTUALS,    PPA.PPA_DISCOUNT_ACTUALS,  PPA.PPA_ACTUAL_SALES,   
                             EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                             PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                             PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
                             NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
                             NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
                             DEMAND_SALES_ACTUAL = Isnull(GTS.DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = Isnull(GTS.DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = Isnull(GTS.ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = Isnull(GTS.FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             --TOTAL_RPU_ACTUAL = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0)
                             --                     + COALESCE(PPA.PPA_RPU_ACTUALS / NULLIF(PPA.PPA_ACTUAL_UNITS, 0), 0) ),
                             --TOTAL_RPU_PROJECTED = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0)
                             --                        + COALESCE(PPA.PPA_RPU_PROJECTED / NULLIF(PPA.PPA_PROJECTION_UNITS, 0), 0) ),
                             COGS_ACTUALS = Isnull(SALES.COGS_ACTUAL, 0),
                             COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUAL, 0) ) ),
                             NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
						
                             RETURNS_ACTUAL_AMOUNT = 000000.0,
                             RETURNS_ACTUAL_PERCENTAGE = 000000.0,
                             RETURNS_PROJECTED_AMOUNT = 000000.0,
                             RETURNS_PROJECTED_PERCENTAGE = 000000.0,
                             TOTAL_RETURNS_RPU_ACTAULS = 000000.0,
                             TOTAL_RETURNS_RPU_PROJECTED = 000000.0,
							 NET_SALES_OF_EX_FACTORY_ACTUALS = COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
                             NET_SALES_OF_EX_FACTORY_PROJECTED = COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
							,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							 DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),
									 DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES),
                                                ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS),
                                                DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                                FOR_GROSS_UNITS = Sum(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),
												GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												SEMI_ANNUAL,
                                     YEAR YEARLY 
                              FROM   '
                                           + @PRODUCT_FILE
                                           + ' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                              GROUP  BY SEMI_ANNUAL,YEAR 
							  ) GTS
                             RIGHT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                                CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                                SEMI_ANNUAL,
                                                [YEAR],
                                                CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                                CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                                COGS_ACTUAL = Sum(COGS_ACTUAL),
                                                COGS_PROJECTED = Sum(COGS_PROJECTED)
                                         FROM   (SELECT CCP.CCP_DETAILS_SID,
                                                        NAS.PERIOD_SID,
                                                        ACTUAL_SALES,
                                                        ACTUAL_UNITS,
                                                        CCP.ITEM_MASTER_SID,
                                                        COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                 FROM   ', @SALES_ACTUAL_TABLE, ' NAS
                                                        INNER JOIN #CCP_DETAILS_TEMP CCP
                                                                ON NAS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                        INNER JOIN #ITEM_PRICING U
                                                                ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                   AND NAS.PERIOD_SID = U.PERIOD_SID
                                                --AND NAS.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                                ) ACT
                                                FULL JOIN (SELECT CCP.CCP_DETAILS_SID,
                                                                  NPS.PERIOD_SID,
                                                                  PROJECTION_SALES,
                                                                  PROJECTION_UNITS,
                                                                  CCP.ITEM_MASTER_SID,
                                                                  COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                           FROM   ', @SALES_PROJECTION_TABLE, ' NPS
                                                                  INNER JOIN #CCP_DETAILS_TEMP CCP
                                                                          ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                  INNER JOIN #ITEM_PRICING U
                                                                          ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                             AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                             AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                       ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                          AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                INNER JOIN PERIOD P
                                                        ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                         GROUP  BY SEMI_ANNUAL,
                                                   [YEAR]) SALES
                                     ON SALES.SEMI_ANNUAL = GTS.SEMI_ANNUAL
                                        AND SALES.YEAR = GTS.YEARLY
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                               SEMI_ANNUAL,
                                               [YEAR]
                                        FROM   (SELECT CCP_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_CONTRACT_SID
                                                FROM   ', @DISC_ACTUAL_TABLE, ' NAD
                                                WHERE   EXISTS (SELECT 1
                                                                   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                   WHERE  A.TOKEN = NAD.RS_CONTRACT_SID)
                                               -- AND NAD.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT CCP_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_CONTRACT_SID
                                                          FROM   ', @DISC_PROJECTION_TABLE, ' NDP
                                                          WHERE   EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_CONTRACT_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY SEMI_ANNUAL,
                                                  [YEAR]) DISC
                                    ON DISC.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                       AND DISC.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               SEMI_ANNUAL,
                                               [YEAR]
                                        FROM   (SELECT NAP.CCP_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_CONTRACT_SID,
                                                       ACTUAL_SALES = NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS = NS.ACTUAL_UNITS
                                                FROM   ', @PPA_ACTUAL_TABLE, ' NAP
                                                       INNER JOIN ', @SALES_ACTUAL_TABLE, ' NS
                                                               ON NS.CCP_DETAILS_SID = NAP.CCP_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                               
                                               -- AND NAP.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT NPP.CCP_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_CONTRACT_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   ', @PPA_PROJECTION_TABLE, ' NPP
                                                                 INNER JOIN ', @SALES_PROJECTION_TABLE, ' NS
                                                                         ON NS.CCP_DETAILS_SID = NPP.CCP_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE   NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY SEMI_ANNUAL,
                                                  [YEAR]) PPA
                                    ON PPA.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                       AND PPA.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY),
                                COALESCE(SALES.SEMI_ANNUAL, GTS.SEMI_ANNUAL)')

                      EXEC Sp_executesql
                        @SQL_S,
                        N'@PROJECTION_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID NVARCHAR(MAX),@START_PERIOD_SID INT',
                        @PROJECTION_SID = @PROJECTION_SID,
                        @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
                        @END_PERIOD_SID = @END_PERIOD_SID,
                        @START_PERIOD_SID = @START_PERIOD_SID,
                        @DISCOUNT_ID = @DISCOUNT_ID
                  END
                ELSE
                  BEGIN
                      ---------------------YEARLY WISE AGGREGATING AND GETTIMG THE FILE TYPES ,SALES ,DISOCUN &PPA
                      DECLARE @SQL_A NVARCHAR(MAX) = ''

                      SET @SQL_A = Concat ('SELECT @PROJECTION_SID                    PROJECTION_ID,
                             EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                             EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                             GTS_UNITS = Isnull(GTS.UNITS, 0),
                             COALESCE(SALES.[YEAR], gts.yearly) AS YEAR,
                             CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                             CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                             CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                             CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                             TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) +
                             --                              + COALESCE(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(PPA.PPA_ACTUAL_SALES, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) +
                             --                                        + COALESCE(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(PPA.PPA_PROJECTION_SALES, 0), 0) ) * 100,
                             ----DISC.CONTRACT_DISCOUNT_ACTUALS,  SALES.CONTRACT_SALES_ACTUALS,    PPA.PPA_DISCOUNT_ACTUALS,  PPA.PPA_ACTUAL_SALES,   
                             EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                             PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                             PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
                             NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
                             NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
                             DEMAND_SALES_ACTUAL = Isnull(GTS.DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = Isnull(GTS.DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = Isnull(GTS.ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = Isnull(GTS.FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             --TOTAL_RPU_ACTUAL = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0)
                             --                     + COALESCE(PPA.PPA_RPU_ACTUALS / NULLIF(PPA.PPA_ACTUAL_UNITS, 0), 0) ),
                             --TOTAL_RPU_PROJECTED = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0)
                             --                        + COALESCE(PPA.PPA_RPU_PROJECTED / NULLIF(PPA.PPA_PROJECTION_UNITS, 0), 0) ),
                             COGS_ACTUALS = Isnull(SALES.COGS_ACTUAL, 0),
                             COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUAL, 0) ) ),
                             NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
																									  
					
                             RETURNS_ACTUAL_AMOUNT = 000000.0,
                             RETURNS_ACTUAL_PERCENTAGE = 000000.0,
                             RETURNS_PROJECTED_AMOUNT = 000000.0,
                             RETURNS_PROJECTED_PERCENTAGE = 000000.0,
                             TOTAL_RETURNS_RPU_ACTAULS = 000000.0,
                             TOTAL_RETURNS_RPU_PROJECTED = 000000.0,
							 NET_SALES_OF_EX_FACTORY_ACTUALS = COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
                             NET_SALES_OF_EX_FACTORY_PROJECTED = COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
							,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							 DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),
									 DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES),
                                                ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS),
                                                DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                                FOR_GROSS_UNITS = Sum(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),
												GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),

                                     YEAR YEARLY 
                              FROM   '
                                           + @PRODUCT_FILE
                                           + ' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                              GROUP  BY YEAR 
							  ) GTS
                             RIGHT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                                CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                                [YEAR],
                                                CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                                CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                                COGS_ACTUAL = Sum(COGS_ACTUAL),
                                                COGS_PROJECTED = Sum(COGS_PROJECTED)
                                         FROM   (SELECT CCP.CCP_DETAILS_SID,
                                                        NAS.PERIOD_SID,
                                                        ACTUAL_SALES,
                                                        ACTUAL_UNITS,
                                                        CCP.ITEM_MASTER_SID,
                                                        COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                 FROM   ', @SALES_ACTUAL_TABLE, ' NAS
                                                        INNER JOIN #CCP_DETAILS_TEMP CCP
                                                                ON NAS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                        INNER JOIN #ITEM_PRICING U
                                                                ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                   AND NAS.PERIOD_SID = U.PERIOD_SID
                                                --AND NAS.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                                ) ACT
                                                FULL JOIN (SELECT CCP.CCP_DETAILS_SID,
                                                                  NPS.PERIOD_SID,
                                                                  PROJECTION_SALES,
                                                                  PROJECTION_UNITS,
                                                                  CCP.ITEM_MASTER_SID,
                                                                  COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                           FROM   ', @SALES_PROJECTION_TABLE, ' NPS
                                                                  INNER JOIN #CCP_DETAILS_TEMP CCP
                                                                          ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                  INNER JOIN #ITEM_PRICING U
                                                                          ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                             AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                             AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                       ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                          AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                INNER JOIN PERIOD P
                                                        ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                         GROUP  BY [YEAR]) SALES
                                     ON SALES.YEAR = GTS.YEARLY
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                               [YEAR]
                                        FROM   (SELECT CCP_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_CONTRACT_SID
                                                FROM   ', @DISC_ACTUAL_TABLE, ' NAD
                                                WHERE  EXISTS (SELECT 1
                                                                   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                   WHERE  A.TOKEN = NAD.RS_CONTRACT_SID)
                                               --  AND NAD.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT CCP_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_CONTRACT_SID
                                                          FROM   ', @DISC_PROJECTION_TABLE, ' NDP
                                                          WHERE  EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_CONTRACT_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [YEAR]) DISC
                                    ON DISC.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               [YEAR]
                                        FROM   (SELECT NAP.CCP_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_CONTRACT_SID,
                                                       ACTUAL_SALES = NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS = NS.ACTUAL_UNITS
                                                FROM   ', @PPA_ACTUAL_TABLE, ' NAP
                                                       INNER JOIN ', @SALES_ACTUAL_TABLE, ' NS
                                                               ON NS.CCP_DETAILS_SID = NAP.CCP_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT NPP.CCP_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_CONTRACT_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   ', @PPA_PROJECTION_TABLE, ' NPP
                                                                 INNER JOIN ', @SALES_PROJECTION_TABLE, ' NS
                                                                         ON NS.CCP_DETAILS_SID = NPP.CCP_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE   NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY YEAR) PPA
                                    ON PPA.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], gts.yearly)')

                      EXEC Sp_executesql
                        @SQL_A,
                        N'@PROJECTION_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID NVARCHAR(MAX),@START_PERIOD_SID INT',
                        @PROJECTION_SID = @PROJECTION_SID,
                        @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
                        @END_PERIOD_SID = @END_PERIOD_SID,
                        @START_PERIOD_SID = @START_PERIOD_SID,
                        @DISCOUNT_ID = @DISCOUNT_ID
                  END
            END
          ELSE -- SCREEN NAME = VARIANCE-----------------------WILL DISPLSY BOTH ACTUAL VALUES & PROJECTION ONLY FOR CONTRACT SALES REMAING WILL DISPLAY ONLY PROJECTION VALUE AND WILL PASS INPUT AS MULTIPLE PROJECTION
            BEGIN
                IF @VIEW = 'PIVOT'
                  BEGIN
                      SET @SQL_ACC=Concat('WITH GTS
AS (
	SELECT ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES)
		,ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS)
		,FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES))
		,FOR_UNITS_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_UNITS, INVENTORY_ACTUAL_UNITS))
		,DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES)
		,ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS)
		,DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES))
		,FOR_GROSS_UNITS = Sum(COALESCE(DEMAND_FORECAST_UNITS, DEMAND_ACTUAL_UNITS))
		,GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES)
		,GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES))
		,UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS))		
		,PERIOD
		,YEAR
	FROM #period DT---(SELECT DISTINCT ITEM_MASTER_SID FROM #CCP_DETAILS_TEMP ) DT
	JOIN ', @PRODUCT_FILE, ' PF ON --DT.ITEM_MASTER_SID = PF.ITEM_MASTER_SID
		--AND 
		DT.PERIOD_SID = PF.PERIOD_SID 
		where exists (SELECT 1 FROM #CCP_DETAILS_TEMP ct where cT.ITEM_MASTER_SID = PF.ITEM_MASTER_SID )
	GROUP BY YEAR
	 	,PERIOD
	)
	,SALES
AS (
	')
                        SET @SQL_ACC=Concat(@SQL_ACC, 'SELECT PERIOD
	    ,YEAR
		,CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES)
		,CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES)
		,CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS)
		,CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS)
		,COGS_ACTUALS = max(COGS_ACTUAL)
		,COGS_PROJECTED = max(COGS_PROJECTED)
	FROM (
		SELECT distinct --dt.HIERARCHY_NO,
			dt.PERIOD
	        ,dt.YEAR
			,ISNULL(ACTUAL_SALES,0) ACTUAL_SALES
			,ISNULL(ACTUAL_UNITS,0) ACTUAL_UNITS
			,ISNULL(PROJECTION_SALES,0) PROJECTION_SALES
			,ISNULL(PROJECTION_UNITS,0) PROJECTION_UNITS
			--,DT.ITEM_MASTER_SID
			,COGS_ACTUAL = COGS_ACTUAL---(ISNULL(NAS.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1) 
			,COGS_PROJECTED = COGS_ACTUAL---(ISNULL(NPS.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1) 
		FROM #DATA_TABLE DT		
		left join (select --dt.HIERARCHY_NO,
		p.year,p.period,COGS_ACTUAL = sum((ISNULL(NAS.QUANTITY, 0) * ISNULL(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1)),
		COGS_PROJECTED = sum((ISNULL(NPS.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1) )
		,PROJECTION_UNITS=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ) , ISNULL(PROJECTION_UNITS,0), NULL))
		,ACTUAL_UNITS=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ) , ISNULL(NAS.QUANTITY,0), NULL))
		,PROJECTION_SALES=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ) , ISNULL(PROJECTION_SALES,0), NULL))
		,ACTUAL_SALES=sum(IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ) , ISNULL(SALES,0), NULL))
		  from  #CCP_DETAILS_TEMP dt
		cross join #period p 
		LEFT JOIN ', @SALES_MASTER_TABLE, ' SPM ON SPM.CCP_DETAILS_SID = DT.CCP_DETAILS_SID
		LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,                               
                               SUM(QUANTITY) QUANTITY,sum(SALES) SALES
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) NAS ON DT.CCP_DETAILS_SID = NAS.CCP_DETAILS_SID
			AND p.PERIOD_SID = NAS.PERIOD_SID
		LEFT JOIN ', @SALES_PROJECTION_TABLE, ' NPS ON NPS.CCP_DETAILS_SID = DT.CCP_DETAILS_SID
			AND p.PERIOD_SID = NPS.PERIOD_SID
		LEFT JOIN #ITEM_PRICING U ON DT.ITEM_MASTER_SID = U.ITEM_MASTER_SID
			AND p.PERIOD_SID = U.PERIOD_SID
		LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID = dt.ITEM_MASTER_SID
		group by --dt.HIERARCHY_NO,
		p.year,p.period)  h on --h.HIERARCHY_NO=dt.HIERARCHY_NO and
		 h.period=dt.period
		and h.year=dt.year
		')
                 

                      SET @SQL_ACC=Concat(@SQL_ACC, '
		) A
	GROUP BY PERIOD
	    ,YEAR
	)
	,DISC
AS (
	SELECT PERIOD
	    ,YEAR
		,CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES)
		,CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES)
		,ACCRUAL_DISCOUNT=max(ACCRUAL_DISCOUNT)
	FROM (
		SELECT --dt.HIERARCHY_NO,
			dt.PERIOD
	        ,dt.YEAR
			--,DT.PERIOD_SID
			,IIF(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL ) and cts.indicator=0, ISNULL(DISCOUNT,0), NULL) ACTUAL_SALES
			,IIF(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL ) and cts.indicator=1, ISNULL(DISCOUNT,0), NULL)  PROJECTION_SALES
			,DISCOUNT_AMOUNT  ACCRUAL_DISCOUNT
			,Discount as CONTRACT_DISCOUNT_ACTUALS
		FROM #DATA_TABLE DT
		left JOIN  ')

                      IF @INDICATOR IN ( 'c', 'p' )
                        BEGIN
                            SET @SQL_ACC=Concat(@SQL_ACC, '(select ctd.* from ', @discount_table, ' ctd where exists (select 1 from #rs_info ri where ri.SELECTED_LEVEL=ctd.RS_CONTRACT_SID) )cts on cts.HIERARCHY_NO=dt.HIERARCHY_NO ')
                        END

                      IF @INDICATOR NOT IN ( 'c', 'p' )
                        BEGIN
                            SET @SQL_ACC=Concat(@SQL_ACC, '(select ctd.* from ', @discount_table, ' ctd)cts on cts.HIERARCHY_NO=dt.HIERARCHY_NO ')
                        END

                      SET @SQL_ACC=Concat(@SQL_ACC, '
		and cts.period=dt.period
		and cts.year=dt.year
		LEFT JOIN #ACCRUAL_DISCOUNT AD ON cts.HIERARCHY_NO=dt.HIERARCHY_NO
		and cts.period=dt.period
		and cts.year=dt.year
		--left join (select dt.HIERARCHY_NO,p.year,p.period,sum(Discount) as CONTRACT_DISCOUNT_ACTUALS
		--  from  #CCP_DETAILS_TEMP dt
		--cross join #period p 
		--JOIN ', @DISC_PROJECTION_MASTER_TABLE, ' SPM ON SPM.CCP_DETAILS_SID = DT.CCP_DETAILS_SID
		--LEFT JOIN (select CCP_DETAILS_SID,
  --                                PERIOD_SID,rs_model_sid,max(Discount) Discount 
		--						  from (SELECT AD.CCP_DETAILS_SID,
  --                             PERIOD_SID,     rs_model_sid,                          
  --                             SUM(Discount) Discount
  --                      FROM   [ACTUALS_DETAILS] AD
  --                      --WHERE  QUANTITY_INCLUSION = ''Y''
  --                      GROUP  BY AD.CCP_DETAILS_SID,
  --                                PERIOD_SID,rs_model_sid,QUANTITY_INCLUSION)a 
		--						  group by CCP_DETAILS_SID,
  --                                PERIOD_SID,rs_model_sid) NAS ON DT.CCP_DETAILS_SID = NAS.CCP_DETAILS_SID
		--	AND p.PERIOD_SID = NAS.PERIOD_SID
		--	AND spm.rs_model_sid = NAS.rs_model_sid
		--	where exists (select 1 from #rs_info rty where rty.rs_contract_sid=spm.rs_contract_sid)
		--group by dt.HIERARCHY_NO,p.year,p.period)  h on h.HIERARCHY_NO=dt.HIERARCHY_NO
		--and h.period=dt.period
		--and h.year=dt.year
		) A
	GROUP BY PERIOD
	    ,YEAR
	)
	INSERT INTO #PIVOT_RESULT (
	PROJECTION_ID
	,[PERIOD]
	,[YEAR]
	,EX_FACTORY_SALES_ACTUALS
	,EX_FACTORY_SALES_PROJECTED
	,EX_FACTORY_SALES_ACTUALS_PERCENT
	,EX_FACTORY_SALES_PROJECTED_PERCENT
	,CONTRACT_SALES_ACTUALS
	,CONTRACT_SALES_PROJECTED
	,CONTRACT_UNITS_ACTUALS
	,CONTRACT_UNITS_PROJECTED
	,TOTAL_DISCOUNT
	,TOTAL_DISCOUNT_PROJECTED
	,TOTAL_DISCOUNT_PERCENTAGE
	,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE
	,TOTAL_DISCOUNT_RPU_ACTUALS
	,TOTAL_DISCOUNT_RPU_PROJECTED
	,TOTAL_RPU_ACTUAL
	,TOTAL_RPU_PROJECTED
	,NET_SALES
	,NET_SALES_PROJECTED
	,COGS_ACTUALS
	,COGS_PROJECTED
	,NET_PROFIT_ACTUAL
	,NET_PROFIT_PROJECTED
	,TOTAL_RETURNS_RPU_ACTUALS
	,TOTAL_RETURNS_RPU_PROJECTED
	,RETURNS_ACTUALS_AMOUNT
	,RETURNS_PROJECTED_AMOUNT
	,RETURNS_ACTUALS_PERCENTAGE
	,RETURNS_PROJECTED_PERCENTAGE
	,NET_SALES_OF_EX_FACTORY_ACTUALS
	,NET_SALES_OF_EX_FACTORY_PROJECTED
	,DISCOUNT_OF_EX_FACTORY_ACTUALS
	,DISCOUNT_OF_EX_FACTORY_PROJECTED
	,NET_EX_FACTORY_SALES_ACTUALS ---------------CEL-386
	,NET_EX_FACTORY_SALES_PROJECTED ---------------CEL-386
	,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS ---------------CEL-386				  
	,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED ,---------------CEL-386
	TOTAL_DISCOUNT_ACCRUAL
	)
SELECT @FIRST_PROJ_SID PROJECTION_ID
	,DT.PERIOD
	,DT.YEAR
	,EX_FACTORY_SALES_ACTUALS = CASE  WHEN @CURRENT_DATE > PERIOD_SID THEN Isnull(GTS.GTS_SALES_ACTUALS, 0) ELSE NULL END
	,EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0)
	,EX_FACTORY_SALES_ACTUALS_PERCENT = CASE  WHEN @CURRENT_DATE > PERIOD_SID THEN (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0)) * 100 ELSE NULL END
	,EX_FACTORY_SALES_PROJECTED_PERCENT = (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0)) * 100
	,CONTRACT_SALES_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN Isnull(SALES.CONTRACT_SALES_ACTUALS, 0)
		ELSE NULL
		END
	,CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0)
	,CONTRACT_UNITS_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN Isnull(CONTRACT_UNITS_ACTUALS, 0)
		ELSE NULL
		END
	,CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0)
	,TOTAL_DISCOUNT = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) 
		ELSE NULL
		END
	,TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED ,0)
	,TOTAL_DISCOUNT_PERCENTAGE = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN (Isnull((DISC.CONTRACT_DISCOUNT_ACTUALS ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0)) * 100
		ELSE NULL
		END
	,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = (Isnull((DISC.CONTRACT_DISCOUNT_PROJECTED ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0)) * 100
	,TOTAL_DISCOUNT_RPU_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN (Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0))
		ELSE NULL
		END
	,TOTAL_DISCOUNT_RPU_PROJECTED = (Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0))
	,TOTAL_RPU_ACTUAL = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN (Isnull((DISC.CONTRACT_DISCOUNT_ACTUALS ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0))
		ELSE NULL
		END
	,TOTAL_RPU_PROJECTED = (Isnull((DISC.CONTRACT_DISCOUNT_PROJECTED ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0))
	,NET_SALES = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN (SALES.CONTRACT_SALES_ACTUALS - (DISC.CONTRACT_DISCOUNT_ACTUALS ))
		ELSE NULL
		END
	,NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED - (DISC.CONTRACT_DISCOUNT_PROJECTED ), 0)
	,COGS_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN Isnull(SALES.COGS_ACTUALS, 0)
		ELSE NULL
		END
	,COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0)
	,NET_PROFIT_ACTUAL = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN isnull(((SALES.CONTRACT_SALES_ACTUALS - (DISC.CONTRACT_DISCOUNT_ACTUALS )) - (SALES.COGS_ACTUALS)),0)
		ELSE NULL
		END
	,NET_PROFIT_PROJECTED = isnull(((SALES.CONTRACT_SALES_PROJECTED - (DISC.CONTRACT_DISCOUNT_PROJECTED )) - (SALES.COGS_PROJECTED)),0)
	,TOTAL_RETURNS_RPU_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN 000000.0
		ELSE NULL
		END
	,TOTAL_RETURNS_RPU_PROJECTED = 000000.0
	,RETURNS_ACTUALS_AMOUNT = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN 000000.0
		ELSE NULL
		END
	,RETURNS_PROJECTED_AMOUNT = 000000.0
	,RETURNS_ACTUALS_PERCENTAGE = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN 000000.0
		ELSE NULL
		END
	,RETURNS_PROJECTED_PERCENTAGE = 000000.0
	,NET_SALES_OF_EX_FACTORY_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN (COALESCE(((SALES.CONTRACT_SALES_ACTUALS - (DISC.CONTRACT_DISCOUNT_ACTUALS ))) / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) * 100)
		ELSE NULL
		END
	,NET_SALES_OF_EX_FACTORY_PROJECTED = (COALESCE(((SALES.CONTRACT_SALES_PROJECTED- (DISC.CONTRACT_DISCOUNT_PROJECTED ))) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100)
	,DISCOUNT_OF_EX_FACTORY_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN COALESCE(((DISC.CONTRACT_DISCOUNT_ACTUALS )) / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) * 100
		ELSE NULL
		END
	,DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((DISC.CONTRACT_DISCOUNT_PROJECTED )) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100
	,NET_EX_FACTORY_SALES_ACTUALS = (
		(
			CASE 
				WHEN @CURRENT_DATE > PERIOD_SID
					THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)
				ELSE NULL
				END
			) - (
			CASE 
				WHEN @CURRENT_DATE > PERIOD_SID
					THEN ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
				ELSE NULL
				END
			)
		) -----------cel-386
	,NET_EX_FACTORY_SALES_PROJECTED = isnull(ISNULL(GTS.GTS_SALES_PROJECTED, 0) - (DISC.CONTRACT_DISCOUNT_PROJECTED),0) -----------cel-386
	,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS = ISNULL((
			(
				CASE 
					WHEN @CURRENT_DATE > PERIOD_SID
						THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)
					ELSE NULL
					END
				) - (
				CASE 
					WHEN @CURRENT_DATE > PERIOD_SID
						THEN ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS , 0)
					ELSE NULL
					END
				)
			) / nullif(CASE 
				WHEN @CURRENT_DATE > PERIOD_SID
					THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)
				ELSE NULL
				END, 0), 0) * 100 -----------cel-386
	,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED = ISNULL((ISNULL(GTS.GTS_SALES_PROJECTED, 0) - (DISC.CONTRACT_DISCOUNT_PROJECTED )) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100
	,-----------cel-386
	ACCRUAL_DISCOUNT AS TOTAL_DISCOUNT_ACCRUAL
FROM (
	SELECT MIN(PERIOD_SID) PERIOD_SID,
	YEAR
		,PERIOD
	FROM #DATA_TABLE
	GROUP BY YEAR
		,PERIOD
	) DT
LEFT JOIN GTS GTS ON  DT.YEAR = GTS.YEAR
	AND DT.PERIOD = GTS.PERIOD
LEFT JOIN SALES SALES ON DT.YEAR = SALES.YEAR
	AND DT.PERIOD = SALES.PERIOD
LEFT JOIN DISC DISC ON DT.YEAR = DISC.YEAR
	AND DT.PERIOD = DISC.PERIOD
	')

                      --select @SQL_ACC          
                      EXEC Sp_executesql
                        @SQL_ACC,
                        N'@FIRST_PROJ_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID NVARCHAR(MAX),@START_PERIOD_SID INT,@CURRENT_DATE INT,@SALES_INCLUSION INT,@DEDUCTION_INCLUSION INT',
                        @FIRST_PROJ_SID = @FIRST_PROJ_SID,
                        @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
                        @END_PERIOD_SID = @END_PERIOD_SID,
                        @START_PERIOD_SID = @START_PERIOD_SID,
                        @DISCOUNT_ID = @DISCOUNT_ID,
                        @CURRENT_DATE=@CURRENT_DATE,
                        @SALES_INCLUSION=@SALES_INCLUSION,
                        @DEDUCTION_INCLUSION=@DEDUCTION_INCLUSION

                      IF Object_id('TEMPDB..#PRIOR_TEMP_CCP') IS NOT NULL
                        DROP TABLE #PRIOR_TEMP_CCP

                      CREATE TABLE #PRIOR_TEMP_CCP
                        (
                           COMPANY_MASTER_SID     INT,
                           CONTRACT_MASTER_SID    INT,
                           ITEM_MASTER_SID        INT,
                           CCP_DETAILS_SID        INT,
                           PROJECTION_MASTER_SID  INT,
                           PROJECTION_DETAILS_SID INT
                           --,BUSINESS_UNIT INT
                           PRIMARY KEY (PROJECTION_MASTER_SID, CCP_DETAILS_SID)
                        )

                      INSERT INTO #PRIOR_TEMP_CCP
                                  (COMPANY_MASTER_SID,
                                   CONTRACT_MASTER_SID,
                                   ITEM_MASTER_SID,
                                   CCP_DETAILS_SID,
                                   PROJECTION_MASTER_SID,
                                   PROJECTION_DETAILS_SID)
                      SELECT CD.COMPANY_MASTER_SID,
                             CD.CONTRACT_MASTER_SID,
                             CD.ITEM_MASTER_SID,
                             CD.CCP_DETAILS_SID,
                             A.PROJECTION_MASTER_SID,
                             pd.PROJECTION_DETAILS_SID
                      FROM   #PROJECTION_MASTER A
                             JOIN PROJECTION_DETAILS PD
                               ON A.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                  AND ID <> 1
                             JOIN CCP_DETAILS CD
                               ON PD.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
                      WHERE  EXISTS (SELECT 1
                                     FROM   #CCP_DETAILS_TEMP TC
                                     WHERE  TC.CCP_DETAILS_SID = CD.CCP_DETAILS_SID)

                      IF Object_id('TEMPDB..#PRIOR_DATA_TABLE') IS NOT NULL
                        DROP TABLE #PRIOR_DATA_TABLE

                      SELECT COMPANY_MASTER_SID,
                             CONTRACT_MASTER_SID,
                             ITEM_MASTER_SID,
                             CCP_DETAILS_SID,
                             PROJECTION_MASTER_SID,
                             PROJECTION_DETAILS_SID,
                             YEAR,
                             PERIOD,
                             PERIOD_SID
                      INTO   #PRIOR_DATA_TABLE
                      FROM   #PRIOR_TEMP_CCP A
                             CROSS JOIN #PERIOD

                      IF Object_id('tempdb..#PRODUCT_FILE_TEMP') IS NOT NULL
                        DROP TABLE #PRODUCT_FILE_TEMP ------------------------------STORING THNE FILE TYPE VALUE IN THIS TABLE-----------------------------------

                      CREATE TABLE #PRODUCT_FILE_TEMP
                        (
                           PROJECTION_MASTER_SID          INT,
                           ITEM_MASTER_SID                INT,
                           PERIOD_SID                     INT,
                           EXFACTORY_ACTUAL_SALES         NUMERIC(33, 8),
                           EXFACTORY_ACTUAL_UNITS         NUMERIC(33, 8),
                           EXFACTORY_FORECAST_SALES       NUMERIC(33, 8),
                           EXFACTORY_FORECAST_UNITS       NUMERIC(33, 8),
                           DEMAND_ACTUAL_SALES            NUMERIC(33, 8),
                           DEMAND_ACTUAL_UNITS            NUMERIC(33, 8),
                           DEMAND_FORECAST_SALES          NUMERIC(33, 8),
                           DEMAND_FORECAST_UNITS          NUMERIC(33, 8),
                           ADJUSTED_DEMAND_ACTUAL_SALES   NUMERIC(33, 8),
                           ADJUSTED_DEMAND_ACTUAL_UNITS   NUMERIC(33, 8),
                           ADJUSTED_DEMAND_FORECAST_SALES NUMERIC(33, 8),
                           ADJUSTED_DEMAND_FORECAST_UNITS NUMERIC(33, 8),
                           INVENTORY_ACTUAL_SALES         NUMERIC(33, 8),
                           INVENTORY_ACTUAL_UNITS         NUMERIC(33, 8),
                           INVENTORY_FORECAST_SALES       NUMERIC(33, 8),
                           INVENTORY_FORECAST_UNITS       NUMERIC(33, 8),
                           ITEM_PRICE                     NUMERIC(33, 8),
                           EXFACTORY_CUST_ACTUAL_SALES    NUMERIC(33, 8),
                           EXFACTORY_CUST_ACTUAL_UNITS    NUMERIC(33, 8),
                           EXFACTORY_CUST_FORECAST_SALES  NUMERIC(33, 8),
                           EXFACTORY_CUST_FORECAST_UNITS  NUMERIC(33, 8),
                           INVENTORY_CUST_ACTUAL_SALES    NUMERIC(33, 8),
                           INVENTORY_CUST_ACTUAL_UNITS    NUMERIC(33, 8),
                           INVENTORY_CUST_FORECAST_SALES  NUMERIC(33, 8),
                           INVENTORY_CUST_FORECAST_UNITS  NUMERIC(33, 8)
                        )

                      --  -------------------------------------------------------
                      INSERT INTO #PRODUCT_FILE_TEMP
                                  (PROJECTION_MASTER_SID,
                                   ITEM_MASTER_SID,
                                   PERIOD_SID,
                                   EXFACTORY_ACTUAL_SALES,
                                   EXFACTORY_ACTUAL_UNITS,
                                   EXFACTORY_FORECAST_SALES,
                                   EXFACTORY_FORECAST_UNITS,
                                   DEMAND_ACTUAL_SALES,
                                   DEMAND_ACTUAL_UNITS,
                                   DEMAND_FORECAST_SALES,
                                   DEMAND_FORECAST_UNITS,
                                   ADJUSTED_DEMAND_ACTUAL_SALES,
                                   ADJUSTED_DEMAND_ACTUAL_UNITS,
                                   ADJUSTED_DEMAND_FORECAST_SALES,
                                   ADJUSTED_DEMAND_FORECAST_UNITS,
                                   INVENTORY_ACTUAL_SALES,
                                   INVENTORY_ACTUAL_UNITS,
                                   INVENTORY_FORECAST_SALES,
                                   INVENTORY_FORECAST_UNITS,
                                   ITEM_PRICE,
                                   EXFACTORY_CUST_ACTUAL_SALES,
                                   EXFACTORY_CUST_ACTUAL_UNITS,
                                   EXFACTORY_CUST_FORECAST_SALES,
                                   EXFACTORY_CUST_FORECAST_UNITS,
                                   INVENTORY_CUST_ACTUAL_SALES,
                                   INVENTORY_CUST_ACTUAL_UNITS,
                                   INVENTORY_CUST_FORECAST_SALES,
                                   INVENTORY_CUST_FORECAST_UNITS)
                      EXEC dbo.PRC_PRIOR_PROJ_PROD_FILE_DATA--_change
                      IF Object_id('TEMPDB..#SALES_INCLUSION') IS NOT NULL
                        DROP TABLE #SALES_INCLUSION

                      SELECT DISTINCT A.PROJECTION_MASTER_SID,
                                      A.PROJECTION_DETAILS_SID,
                                      CD.CCP_DETAILS_SID,
                                      CASE
                                        WHEN DESCRIPTION = 'YES' THEN 1
                                        ELSE 0
                                      END SALES_INCLUSION
                      INTO   #SALES_INCLUSION
                      FROM   NM_SALES_PROJECTION_MASTER A
                             JOIN #PRIOR_TEMP_CCP CD
                               ON CD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                  AND CD.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
                             JOIN CFP_CONTRACT CC1
                               ON CC1.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
                                  AND INBOUND_STATUS <> 'D'
                             JOIN CFP_CONTRACT_DETAILS CC
                               ON CC1.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
                                  AND CC.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID
                             JOIN HELPER_TABLE HT
                               ON HT.HELPER_TABLE_SID = CC1.SALES_INCLUSION

                      IF Object_id('TEMPDB..#DEDUCTION_INCLUSION') IS NOT NULL
                        DROP TABLE #DEDUCTION_INCLUSION

                      SELECT a.PROJECTION_MASTER_SID,
                             A.PROJECTION_DETAILS_SID,
                             CD.CCP_DETAILS_SID,
                             RS.RS_CONTRACT_SID,
                             CASE
                               WHEN DESCRIPTION = 'YES' THEN 1
                               ELSE 0
                             END DEDUCTION_INCLUSION
                      INTO   #DEDUCTION_INCLUSION
                      FROM   NM_DISCOUNT_PROJ_MASTER A
                             JOIN #PRIOR_TEMP_CCP CD
                               ON CD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                  AND CD.PROJECTION_MASTER_SID = a.PROJECTION_MASTER_SID
                             JOIN RS_CONTRACT RS
                               ON A.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
                             JOIN HELPER_TABLE HT
                               ON HT.HELPER_TABLE_SID = RS.DEDUCTION_INCLUSION
                      WHERE  EXISTS (SELECT 1
                                     FROM   #RS_info RD
                                     WHERE  RD.RS_CONTRACT_SID = RS.RS_CONTRACT_SID)

                      INSERT INTO #PIVOT_RESULT
                                  (PROJECTION_ID,
                                   PERIOD,
                                   [YEAR],
                                   EX_FACTORY_SALES_PROJECTED,
                                   EX_FACTORY_SALES_PROJECTED_PERCENT,
                                   CONTRACT_SALES_PROJECTED,
                                   CONTRACT_UNITS_PROJECTED,
                                   TOTAL_DISCOUNT_PROJECTED,
                                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   TOTAL_DISCOUNT_RPU_PROJECTED,
                                   TOTAL_RPU_PROJECTED,
                                   NET_SALES_PROJECTED,
                                   COGS_PROJECTED,
                                   NET_PROFIT_PROJECTED,
                                   NET_SALES_OF_EX_FACTORY_PROJECTED,
                                   DISCOUNT_OF_EX_FACTORY_PROJECTED,
                                   NET_EX_FACTORY_SALES_PROJECTED,
                                   NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED)
                      SELECT COALESCE(a.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID)                                                                                        PROJECTION_ID,
                             A.PERIOD,
                             A.[YEAR],
                             Isnull(A.GTS_SALES_PROJECTED, 0)                                                                                                                      AS EX_FACTORY_SALES_PROJECTED,
                             ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) ) * 100                                                                AS EX_FACTORY_SALES_PROJECTED_PERCENT,
                             Isnull(SALES.CONTRACT_SALES_PROJECTED, 0)                                                                                                             AS CONTRACT_SALES_PROJECTED,
                             Isnull(CONTRACT_UNITS_PROJECTED, 0)                                                                                                                   AS CONTRACT_UNITS_PROJECTED,
                             Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)                                                                                                           AS TOTAL_DISCOUNT_PROJECTED,
                             ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100                                                     AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )                                                           AS TOTAL_DISCOUNT_RPU_PROJECTED,
                             ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )                                                           AS TOTAL_RPU_PROJECTED,
                             Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)                                                               AS NET_SALES_PROJECTED,
                             Isnull(SALES.COGS_PROJECTED, 0)                                                                                                                       AS COGS_PROJECTED,
                             Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0) - Isnull(SALES.COGS_PROJECTED, 0)                             AS NET_PROFIT_PROJECTED,
                             ( COALESCE(( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0) ) / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) * 100 ) AS NET_SALES_OF_EX_FACTORY_PROJECTED,
                             COALESCE((( DISC.CONTRACT_DISCOUNT_PROJECTED )) / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) * 100                                                          AS DISCOUNT_OF_EX_FACTORY_PROJECTED,
                             NET_EX_FACTORY_SALES_PROJECTED =Isnull(Isnull(A.GTS_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0) ), 0),
                             NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED=Isnull(Isnull(Isnull(A.GTS_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0) ), 0) / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) * 100 -----------cel-386
                      FROM   (SELECT PM.PROJECTION_MASTER_SID,
                                     Sum(EXFACTORY_ACTUAL_SALES)                                     AS GTS_SALES_ACTUALS,
                                     Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) AS GTS_SALES_PROJECTED,
                                     P.YEAR,
                                     P.PERIOD
                              FROM   (SELECT DISTINCT PROJECTION_MASTER_SID,
                                                      ITEM_MASTER_SID
                                      FROM   #PRIOR_TEMP_CCP) PM
                                     JOIN #PERIOD p
                                       ON 1 = 1
                                     LEFT JOIN #PRODUCT_FILE_TEMP PF
                                            ON PM.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID
                                               AND PF.ITEM_MASTER_SID = PM.ITEM_MASTER_SID
                                               AND PF.period_sid = P.period_sid
                              GROUP  BY PM.PROJECTION_MASTER_SID,
                                        P.PERIOD,
                                        P.YEAR) A
                             INNER JOIN (SELECT PROJECTION_MASTER_SID,
                                                PERIOD,
                                                YEAR,
                                                CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                                CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                                COGS_PROJECTED = Sum(COGS_PROJECTED)
                                         FROM   (SELECT PT.PROJECTION_MASTER_SID,
                                                        PT.CCP_DETAILS_SID,
                                                        PERIOD,
                                                        YEAR,
                                                        PT.PERIOD_SID,
                                                        Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                               OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL)                                     PROJECTION_SALES,
                                                        Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                               OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_UNITS, 0), NULL) * COALESCE(NULLIF(UOM_VALUE, 0), 1) PROJECTION_UNITS,
                                                        COGS_PROJECTED = ( Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                                  OR @SALES_INCLUSION IS NULL ), PROJECTION_UNITS, NULL) * Isnull(U.ITEM_PRICE, 0) ) * COALESCE(NULLIF(UOM_VALUE, 0), 1)
                                                 FROM   #PRIOR_DATA_TABLE PT
                                                        INNER JOIN #SALES_INCLUSION SI
                                                                ON SI.PROJECTION_DETAILS_SID = PT.PROJECTION_DETAILS_SID
                                                        LEFT JOIN NM_SALES_PROJECTION NSP
                                                               ON NSP.PROJECTION_MASTER_SID = PT.PROJECTION_MASTER_SID
                                                                  AND NSP.CCP_DETAILS_SID = PT.CCP_DETAILS_SID
                                                                  AND NSP.PERIOD_SID = PT.PERIOD_SID
                                                        LEFT JOIN #ITEM_PRICING U
                                                               ON PT.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND PT.PERIOD_SID = U.PERIOD_SID
                                                        LEFT JOIN #ITEM_UOM_DETAILS UOM
                                                               ON UOM.ITEM_MASTER_SID = PT.ITEM_MASTER_SID) A
                                         GROUP  BY PROJECTION_MASTER_SID,
                                                   YEAR,
                                                   PERIOD) SALES
                                     ON SALES.PERIOD = A.PERIOD
                                        AND sales.YEAR = a.YEAR
                                        AND SALES.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
                             LEFT JOIN (SELECT PROJECTION_MASTER_SID,
                                               PERIOD,
                                               YEAR,
                                               CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES)
                                        FROM   (SELECT PT.PROJECTION_MASTER_SID,
                                                       PT.CCP_DETAILS_SID,
                                                       PERIOD,
                                                       YEAR,
                                                       PT.PERIOD_SID,
                                                       Iif(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
                                                              OR @DEDUCTION_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL) PROJECTION_SALES
                                                FROM   #PRIOR_DATA_TABLE PT
                                                       INNER JOIN #DEDUCTION_INCLUSION DI
                                                               ON DI.PROJECTION_DETAILS_SID = PT.PROJECTION_DETAILS_SID
                                                       LEFT JOIN NM_DISCOUNT_PROJECTION NDP
                                                              ON NDP.PROJECTION_MASTER_SID = PT.PROJECTION_MASTER_SID
                                                                 AND DI.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID
                                                                 AND DI.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                                                                 AND NDP.PERIOD_SID = PT.PERIOD_SID) A
                                        GROUP  BY PROJECTION_MASTER_SID,
                                                  PERIOD,
                                                  YEAR) DISC
                                    ON DISC.PERIOD = A.PERIOD
                                       AND disc.year = A.YEAR
                                       AND DISC.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
                  END
            END

          ------------- PIVOTING START
          DECLARE @LOOP_CNTR INT,
                  @MAX_CCP   INT

          SET @SQL = 'SELECT YEAR,PERIOD, '
          SET @LOOP_CNTR = 1
          SET @MAX_CCP = (SELECT Max(ID)
                          FROM   #PROJECTION_MASTER)

          WHILE @LOOP_CNTR <= @MAX_CCP
            BEGIN
                SET @SQL = @SQL + 'EX_FACTORY_SALES_ACCRUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN EX_FACTORY_SALES_ACCRUALS END),
								EX_FACTORY_SALES_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN EX_FACTORY_SALES_ACTUALS END),
								 EX_FACTORY_SALES_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN EX_FACTORY_SALES_PROJECTED END),0),
								  DEMAND_SALES_ACCRUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN DEMAND_SALES_ACCRUALS END),
                                  DEMAND_SALES_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN DEMAND_SALES_ACTUALS END),
                                   DEMAND_SALES_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN DEMAND_SALES_PROJECTED END),0),
                                  INVENTORY_WITHDRAWAL_SALES_ACCRUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN INVENTORY_WITHDRAWAL_SALES_ACCRUALS END),
								  INVENTORY_WITHDRAWAL_SALES_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUALS END),
                                  INVENTORY_WITHDRAWAL_SALES_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED END),0),
                                  EX_FACTORY_SALES_ACCRUAL_PERCENT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN EX_FACTORY_SALES_ACCRUAL_PERCENT END),
								EX_FACTORY_SALES_ACTUALS_PERCENT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN EX_FACTORY_SALES_ACTUALS_PERCENT END),
                                EX_FACTORY_SALES_PROJECTED_PERCENT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN EX_FACTORY_SALES_PROJECTED_PERCENT END),0),
                                DEMAND_SALES_ACCRUAL_PERCENT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN DEMAND_SALES_ACCRUAL_PERCENT END),
							   DEMAND_SALES_ACTUALS_PERCENT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN DEMAND_SALES_ACTUALS_PERCENT END),
                               DEMAND_SALES_PROJECTED_PERCENT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN DEMAND_SALES_PROJECTED_PERCENT END),0),
                              INVENTORY_WITHDRAWAL_SALES_ACCRUAL_PERCENT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN INVENTORY_WITHDRAWAL_SALES_ACCRUAL_PERCENT END),
                              INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT END),
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT END),0),
                             CONTRACT_SALES_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN CONTRACT_SALES_ACCRUAL END),
                             CONTRACT_SALES_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN CONTRACT_SALES_ACTUALS END),
                             CONTRACT_SALES_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN CONTRACT_SALES_PROJECTED END),0),
                             CONTRACT_UNITS_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN CONTRACT_UNITS_ACCRUAL END),
                             CONTRACT_UNITS_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN CONTRACT_UNITS_ACTUALS END),
                             CONTRACT_UNITS_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN CONTRACT_UNITS_PROJECTED END),0),
                             TOTAL_DISCOUNT_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN NULLIF(TOTAL_DISCOUNT_ACCRUAL,0) END),
                             TOTAL_DISCOUNT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN IIF(TOTAL_DISCOUNT=0,NULL,TOTAL_DISCOUNT) END),
                            TOTAL_DISCOUNT_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN TOTAL_DISCOUNT_PROJECTED END),0),
                            TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE END),
                            TOTAL_DISCOUNT_PERCENTAGE_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN TOTAL_DISCOUNT_PERCENTAGE END),
                           TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                           PPA_DISCOUNT_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN PPA_DISCOUNT_ACCRUAL END),
									   
                           PPA_DISCOUNT_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN PPA_DISCOUNT_ACTUALS END),
                           PPA_DISCOUNT_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN PPA_DISCOUNT_PROJECTED END),0),
                           PPA_DISCOUNT_ACCRUAL_PERCENTAGE_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN PPA_DISCOUNT_ACCRUAL_PERCENTAGE END),
                           PPA_DISCOUNT_ACTUALS_PERCENTAGE_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN PPA_DISCOUNT_ACTUALS_PERCENTAGE END),
                            PPA_DISCOUNT_PROJECTED_PERCENTAGE_'
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(10))
                           + ' THEN PPA_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                           TOTAL_DISCOUNT_RPU_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_DISCOUNT_RPU_ACCRUAL END),
                           TOTAL_DISCOUNT_RPU_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_DISCOUNT_RPU_ACTUALS END),
                           TOTAL_DISCOUNT_RPU_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_DISCOUNT_RPU_PROJECTED END),0),
                           TOTAL_PPA_RPU_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_PPA_RPU_ACCRUAL END),
                           TOTAL_PPA_RPU_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_PPA_RPU_ACTUALS END),
                           TOTAL_PPA_RPU_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_PPA_RPU_PROJECTED END),0),
                           TOTAL_RPU_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_RPU_ACCRUAL END),
                           TOTAL_RPU_ACTUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_RPU_ACTUAL END),
                           TOTAL_RPU_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_RPU_PROJECTED END),0),
                           NET_SALES_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_SALES_ACCRUAL END),
                           NET_SALES_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_SALES END),
                           NET_SALES_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_SALES_PROJECTED END),0),
                          COGS_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN COGS_ACCRUAL END),
                          COGS_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN COGS_ACTUALS END),
                          COGS_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN COGS_PROJECTED END),0),
                          NET_PROFIT_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_PROFIT_ACCRUAL END),
                          NET_PROFIT_ACTUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_PROFIT_ACTUAL END),
                          NET_PROFIT_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_PROFIT_PROJECTED END),0),
                          TOTAL_RETURNS_RPU_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_RETURNS_RPU_ACCRUAL END),
                          TOTAL_RETURNS_RPU_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_RETURNS_RPU_ACTUALS END),
                          TOTAL_RETURNS_RPU_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN TOTAL_RETURNS_RPU_PROJECTED END),0),
                          RETURNS_ACCRUAL_AMOUNT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN RETURNS_ACCRUAL_AMOUNT END),
                          RETURNS_ACTUALS_AMOUNT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN RETURNS_ACTUALS_AMOUNT END),
                          RETURNS_PROJECTED_AMOUNT_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN RETURNS_PROJECTED_AMOUNT END),0),
                          RETURNS_ACCRUAL_PERCENTAGE_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN RETURNS_ACCRUAL_PERCENTAGE END),
                          RETURNS_ACTUALS_PERCENTAGE_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN RETURNS_ACTUALS_PERCENTAGE END),
                          RETURNS_PROJECTED_PERCENTAGE_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + 'THEN RETURNS_PROJECTED_PERCENTAGE END),0),
                          NET_SALES_OF_EX_FACTORY_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_SALES_OF_EX_FACTORY_ACCRUAL END),
                          NET_SALES_OF_EX_FACTORY_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_SALES_OF_EX_FACTORY_ACTUALS END),
                          NET_SALES_OF_EX_FACTORY_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_SALES_OF_EX_FACTORY_PROJECTED END),0),
                           DISCOUNT_OF_EX_FACTORY_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN DISCOUNT_OF_EX_FACTORY_ACCRUAL END),
                           DISCOUNT_OF_EX_FACTORY_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),
                           DISCOUNT_OF_EX_FACTORY_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0), 
                          NET_EX_FACTORY_SALES_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_EX_FACTORY_SALES_ACCRUAL END),
                          NET_EX_FACTORY_SALES_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_EX_FACTORY_SALES_ACTUALS END),
                          NET_EX_FACTORY_SALES_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_EX_FACTORY_SALES_PROJECTED END),0),
                           NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACCRUAL_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACCRUAL END),
                           NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS END),
                           NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED_'
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' = ISNULL(MAX(CASE WHEN ID = '
                           + Cast(@LOOP_CNTR AS VARCHAR(20))
                           + ' THEN NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED END),0), '
                SET @LOOP_CNTR += 1
            END

          SET @SQL = LEFT(@SQL, Len(@SQL) - 1)
          SET @SQL += ' FROM   #PIVOT_RESULT PR
               INNER JOIN #PROJECTION_MASTER PM
                       ON PR.PROJECTION_ID = PM.PROJECTION_MASTER_SID
                                  GROUP  BY [YEAR],PERIOD
                                  ORDER BY [YEAR],PERIOD'

          EXEC Sp_executesql
            @SQL
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
GO