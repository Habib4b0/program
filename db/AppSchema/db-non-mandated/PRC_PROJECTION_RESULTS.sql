IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PROJECTION_RESULTS'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PROJECTION_RESULTS]
  END

GO

CREATE PROCEDURE [dbo].[PRC_PROJECTION_RESULTS](@PROJECTION_SID VARCHAR(500),
                                                @PROJ_FREQUENCY VARCHAR(20),
                                                @DISCOUNT_ID    NVARCHAR(MAX),
                                                @SCREEN_NAME    VARCHAR(50),
                                                @SESSION_ID     VARCHAR(50),
                                                @USER_ID        INT,
                                                @VIEW           VARCHAR(20) = NULL,
												@CCP NVARCHAR(MAX) = NULL,
												@UOM_CODE VARCHAR(50),
												@SALES_INCLUSION BIT,
												@DEDUCTION_INCLUSION BIT)
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
          DECLARE 
                  @SALES_MASTER_TABLE     VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),----Actuals Sales values
                  @SALES_ACTUAL_TABLE     VARCHAR(200) = Concat ('ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),----Actuals Sales values
                  @SALES_PROJECTION_TABLE VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),------Sales projection vales
                  @DISC_PROJECTION_MASTER_TABLE  VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),------discoutn projection values
                  @DISC_PROJECTION_TABLE  VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),------discoutn projection values
                  @DISC_ACTUAL_TABLE      VARCHAR(200) = Concat ('ST_NM_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),----Actual discount values
                  @PPA_MASTER_TABLE   VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),-----PPA projection values
                  @PPA_PROJECTION_TABLE   VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),-----PPA projection values
                  @PPA_ACTUAL_TABLE       VARCHAR(200) = Concat ('ST_NM_ACTUAL_PPA_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),-------Actual PPA values
                  @CCP_HIERARCHY          VARCHAR(200) = Concat ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),---- ccp deatils information
                  @PRODUCT_FILE           VARCHAR(100) = Concat ('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')), -----lastest file of that product based on projection
				  @ITEM_UOM_TABLE		  VARCHAR(100) = Concat ('ST_ITEM_UOM_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
          
		
IF OBJECT_ID('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL -------IT CONTAIN CCP DETAIL'S 
	DROP TABLE #CCP_DETAILS_TEMP

CREATE TABLE #CCP_DETAILS_TEMP (
	CCP_DETAILS_SID INT
	,COMPANY_MASTER_SID INT
	,ITEM_MASTER_SID INT
	,CONTRACT_MASTER_SID INT
	,NEW BIT DEFAULT(0)
	)

DECLARE @SQL NVARCHAR(MAX)

SET @SQL = 'INSERT INTO #CCP_DETAILS_TEMP (
	CCP_DETAILS_SID
	,COMPANY_MASTER_SID
	,ITEM_MASTER_SID
	,CONTRACT_MASTER_SID
	)
SELECT CH.CCP_DETAILS_SID
	,COMPANY_MASTER_SID
	,ITEM_MASTER_SID
	,CONTRACT_MASTER_SID
FROM ' + @CCP_HIERARCHY + ' CH
JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
	AND (
		EXISTS (
			SELECT 1
			FROM UDF_SPLITSTRING(@CCP, '','') U
			WHERE U.TOKEN = CONVERT(VARCHAR(100),CH.CCP_DETAILS_SID)
			)
		OR @CCP IS NULL
		)
 '

EXEC sp_executesql @SQL
	,N'@CCP VARCHAR(MAX)'
	,@CCP = @CCP

-------IT CONTAIN CCP DETAIL'S 
IF OBJECT_ID('TEMPDB..#ITEM_UOM_DETAILS') IS NOT NULL
	DROP TABLE #ITEM_UOM_DETAILS

CREATE TABLE #ITEM_UOM_DETAILS (
	ITEM_MASTER_SID INT
	,UOM_VALUE NUMERIC(22, 6)
	,UOM_CODE VARCHAR(50)
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

EXEC sp_executesql @SQL
	,N'@UOM_CODE VARCHAR(50)'
	,@UOM_CODE = @UOM_CODE


-----------------PASSING MULTIPLE PROJECTIONS AS INPUT CONVERTING INTO COMMA SEPERATED VALUES --------------------------
IF Object_id('TEMPDB..#PROJECTION_MASTER') IS NOT NULL
	DROP TABLE #PROJECTION_MASTER

CREATE TABLE #PROJECTION_MASTER (
	ID INT IDENTITY(1, 1)
	,PROJECTION_MASTER_SID INT
	)


          ----------------------------------------------------------STORING THE OUTPUT RESULT OF PROJECTION VARIANCE TABLE--------------------------------------
IF Object_id('TEMPDB..#PIVOT_RESULT') IS NOT NULL
	DROP TABLE #PIVOT_RESULT

CREATE TABLE #PIVOT_RESULT (
	PROJECTION_ID INT
	,[PERIOD] SMALLINT
	,[YEAR] INT
	,EX_FACTORY_SALES_ACCRUALS NUMERIC(33, 8)
	,EX_FACTORY_SALES_ACTUALS NUMERIC(33, 8)
	,EX_FACTORY_SALES_PROJECTED NUMERIC(33, 8)
	,DEMAND_SALES_ACCRUALS NUMERIC(33, 8)
	,DEMAND_SALES_ACTUALS NUMERIC(33, 8)
	,DEMAND_SALES_PROJECTED NUMERIC(33, 8)
	,INVENTORY_WITHDRAWAL_SALES_ACCRUALS NUMERIC(33, 8)
	,INVENTORY_WITHDRAWAL_SALES_ACTUALS NUMERIC(33, 8)
	,INVENTORY_WITHDRAWAL_SALES_PROJECTED NUMERIC(33, 8)
	,EX_FACTORY_SALES_ACCRUAL_PERCENT NUMERIC(33, 8)
	,EX_FACTORY_SALES_ACTUALS_PERCENT NUMERIC(33, 8)
	,EX_FACTORY_SALES_PROJECTED_PERCENT NUMERIC(33, 8)
	,DEMAND_SALES_ACCRUAL_PERCENT NUMERIC(33, 8)
	,DEMAND_SALES_ACTUALS_PERCENT NUMERIC(33, 8)
	,DEMAND_SALES_PROJECTED_PERCENT NUMERIC(33, 8)
	,INVENTORY_WITHDRAWAL_SALES_ACCRUAL_PERCENT NUMERIC(33, 8)
	,INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT NUMERIC(33, 8)
	,INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT NUMERIC(33, 8)
	,CONTRACT_SALES_ACCRUAL NUMERIC(33, 8)
	,CONTRACT_SALES_ACTUALS NUMERIC(33, 8)
	,CONTRACT_SALES_PROJECTED NUMERIC(33, 8)
	,CONTRACT_UNITS_ACCRUAL NUMERIC(33, 8)
	,CONTRACT_UNITS_ACTUALS NUMERIC(33, 8)
	,CONTRACT_UNITS_PROJECTED NUMERIC(33, 8)
	,TOTAL_DISCOUNT_ACCRUAL NUMERIC(33, 8)
	,TOTAL_DISCOUNT NUMERIC(33, 8)
	,TOTAL_DISCOUNT_PROJECTED NUMERIC(33, 8)
	,TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE NUMERIC(33, 8)
	,TOTAL_DISCOUNT_PERCENTAGE NUMERIC(33, 8)
	,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE NUMERIC(33, 8)
	,PPA_DISCOUNT_ACCRUAL NUMERIC(33, 8)
	,PPA_DISCOUNT_ACTUALS NUMERIC(33, 8)
	,PPA_DISCOUNT_PROJECTED NUMERIC(33, 8)
	,PPA_DISCOUNT_ACCRUAL_PERCENTAGE NUMERIC(33, 8)
	,PPA_DISCOUNT_ACTUALS_PERCENTAGE NUMERIC(33, 8)
	,PPA_DISCOUNT_PROJECTED_PERCENTAGE NUMERIC(33, 8)
	,TOTAL_DISCOUNT_RPU_ACCRUAL NUMERIC(33, 8)
	,TOTAL_DISCOUNT_RPU_ACTUALS NUMERIC(33, 8)
	,TOTAL_DISCOUNT_RPU_PROJECTED NUMERIC(33, 8)
	,TOTAL_PPA_RPU_ACCRUAL NUMERIC(33, 8)
	,TOTAL_PPA_RPU_ACTUALS NUMERIC(33, 8)
	,TOTAL_PPA_RPU_PROJECTED NUMERIC(33, 8)
	,TOTAL_RPU_ACCRUAL NUMERIC(33, 8)
	,TOTAL_RPU_ACTUAL NUMERIC(33, 8)
	,TOTAL_RPU_PROJECTED NUMERIC(33, 8)
	,NET_SALES_ACCRUAL NUMERIC(33, 8)
	,NET_SALES NUMERIC(33, 8)
	,NET_SALES_PROJECTED NUMERIC(33, 8)
	,COGS_ACCRUAL NUMERIC(33, 8)
	,COGS_ACTUALS NUMERIC(33, 8)
	,COGS_PROJECTED NUMERIC(33, 8)
	,NET_PROFIT_ACCRUAL NUMERIC(33, 8)
	,NET_PROFIT_ACTUAL NUMERIC(33, 8)
	,NET_PROFIT_PROJECTED NUMERIC(33, 8)
	,TOTAL_RETURNS_RPU_ACCRUAL NUMERIC(33, 8)
	,TOTAL_RETURNS_RPU_ACTUALS NUMERIC(33, 8)
	,TOTAL_RETURNS_RPU_PROJECTED NUMERIC(33, 8)
	,RETURNS_ACCRUAL_AMOUNT NUMERIC(33, 8)
	,RETURNS_ACTUALS_AMOUNT NUMERIC(33, 8)
	,RETURNS_PROJECTED_AMOUNT NUMERIC(33, 8)
	,RETURNS_ACCRUAL_PERCENTAGE NUMERIC(33, 8)
	,RETURNS_ACTUALS_PERCENTAGE NUMERIC(33, 8)
	,RETURNS_PROJECTED_PERCENTAGE NUMERIC(33, 8)
	,NET_SALES_OF_EX_FACTORY_ACCRUAL NUMERIC(33, 8)
	,NET_SALES_OF_EX_FACTORY_ACTUALS NUMERIC(33, 8)
	,NET_SALES_OF_EX_FACTORY_PROJECTED NUMERIC(33, 8)
	,DISCOUNT_OF_EX_FACTORY_ACCRUAL NUMERIC(33, 8)
	,DISCOUNT_OF_EX_FACTORY_ACTUALS NUMERIC(33, 8)
	,DISCOUNT_OF_EX_FACTORY_PROJECTED NUMERIC(33, 8)
	,NET_EX_FACTORY_SALES_ACTUALS NUMERIC(33, 8) -----------CEL-386 
	,NET_EX_FACTORY_SALES_PROJECTED NUMERIC(33, 8) -----------CEL-386
	,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS NUMERIC(33, 8) -----------CEL-386					 
	,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED NUMERIC(33, 8) -----------CEL-386
	,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACCRUAL NUMERIC(33, 8)
	,NET_EX_FACTORY_SALES_ACCRUAL NUMERIC(33, 8)
	)
SET @TEMP_PROJ_SID = Replace(@PROJECTION_SID + ',', ',,', ',')
/*
WHILE Patindex('%,%', @TEMP_PROJ_SID) <> 0
BEGIN
	SELECT @SP = Patindex('%,%', @TEMP_PROJ_SID)

	SELECT @SP_PROJ_SID = LEFT(@TEMP_PROJ_SID, @SP - 1)

	SELECT @TEMP_PROJ_SID = Stuff(@TEMP_PROJ_SID, 1, @SP, '')

	INSERT INTO #PROJECTION_MASTER (PROJECTION_MASTER_SID)
	SELECT @SP_PROJ_SID
END
*/
INSERT INTO #PROJECTION_MASTER (PROJECTION_MASTER_SID)
SELECT * FROM UDF_SPLITSTRING(@PROJECTION_SID,',')
DECLARE @FIRST_PROJ_SID INT

SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID
FROM #PROJECTION_MASTER PM
WHERE ID = 1

-------------------------------GALUAT_29 CHANGES----------------------------
------PULLING ACTUAL START_DATE , ACTUAL_END_DATE BASED ON CURRENT -3 CONCEPT & PROJ_START_PERIOD_DATE,PROJ_END_PERIOD_DATE BASED ON PROJECTION FROM PROJECTION MASTER TABLE -----------
SELECT TOP 1 @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, - 3, Getdate())), 0)
	,@START_DATE = Dateadd(dd, 1, Eomonth(FROM_DATE, - 1))
	,@PROJECTION_DATE = Dateadd(dd, 1, Eomonth(TO_DATE, - 1))
FROM PROJECTION_MASTER
WHERE PROJECTION_MASTER_SID = @FIRST_PROJ_SID
ORDER BY PROJECTION_MASTER_SID

SELECT @START_PERIOD_SID = Max(CASE 
			WHEN PERIOD_DATE = @STARTFROM
				THEN PERIOD_SID
			END)
	,@END_PERIOD_SID = Max(CASE 
			WHEN PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0)
				THEN PERIOD_SID
			END)
	,@PROJ_START_PERIOD_SID = Max(CASE 
			WHEN PERIOD_DATE = @START_DATE
				THEN PERIOD_SID
			END)
FROM PERIOD
WHERE PERIOD_DATE IN (
		@STARTFROM
		,@PROJECTION_DATE
		,@START_DATE
		)

-----------------PERIOD CONVERISON--------------------
IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
	DROP TABLE #PERIOD;

SELECT PERIOD_SID
	,PERIOD_DATE
	,MONTH
	,QUARTER
	,SEMI_ANNUAL
	,YEAR
	,CASE 
		WHEN LEFT(@PROJ_FREQUENCY, 1) = 'M'
			THEN MONTH
		WHEN LEFT(@PROJ_FREQUENCY, 1) = 'Q'
			THEN QUARTER
		WHEN LEFT(@PROJ_FREQUENCY, 1) = 'S'
			THEN SEMI_ANNUAL
		ELSE 1
		END AS PERIOD
INTO #PERIOD
FROM PERIOD WHERE PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID

SELECT @CURRENT_DATE = PERIOD_SID FROM PERIOD WHERE PERIOD_DATE=DATEADD(DD, 1, EOMONTH(GETDATE(), - 1))

 -------------------------pulling ccp inforamtion for particular projection based on input projection---------------------------------
/*IF OBJECT_ID('TEMPDB..#TEMP_CCP') IS NOT NULL
	DROP TABLE #TEMP_CCP

CREATE TABLE #TEMP_CCP (
	COMPANY_MASTER_SID INT
	,CONTRACT_MASTER_SID INT
	,ITEM_MASTER_SID INT
	,CCP_DETAILS_SID INT PRIMARY KEY
	,PROJECTION_MASTER_SID INT
	,BUSINESS_UNIT INT
	)

INSERT INTO #TEMP_CCP (
	COMPANY_MASTER_SID
	,CONTRACT_MASTER_SID
	,ITEM_MASTER_SID
	,CCP_DETAILS_SID
	,PROJECTION_MASTER_SID
	,BUSINESS_UNIT
	)
SELECT CCP.COMPANY_MASTER_SID
	,CCP.CONTRACT_MASTER_SID
	,CCP.ITEM_MASTER_SID
	,CCP.CCP_DETAILS_SID
	,PM.PROJECTION_MASTER_SID
	,PM.BUSINESS_UNIT
FROM #CCP_DETAILS_TEMP CCP
CROSS JOIN (
	SELECT PM.PROJECTION_MASTER_SID
		,PM.BUSINESS_UNIT
	FROM PROJECTION_MASTER PM
	WHERE PM.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
	) PM
	*/
          -------------------------------------------------------------------------------------
DECLARE @ITEMID [DBO].[UDT_ITEM]

INSERT INTO @ITEMID
select distinct ITEM_MASTER_SID from #CCP_DETAILS_TEMP
/*SELECT IM.ITEM_MASTER_SID
FROM ITEM_MASTER IM
WHERE EXISTS (
		SELECT 1
		FROM #TEMP_CCP A
		WHERE PROJECTION_MASTER_SID = @FIRST_PROJ_SID
			AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID
		)*/

----------------------------------PULLING ITEM PRICING BASED ON ITEM ---------------------------------------------
IF Object_id('TEMPDB..#ITEM_PRICING') IS NOT NULL
	DROP TABLE #ITEM_PRICING

SELECT ITEM_MASTER_SID
	,PERIOD_SID
	,PRICING_QUALIFIER
	,ITEM_PRICE
INTO #ITEM_PRICING
FROM [DBO].[Udf_item_pricing](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, @ITEM_UOM)


IF Object_id('TEMPDB..#DATA_TABLE') IS NOT NULL
	DROP TABLE #DATA_TABLE

SELECT /*COMPANY_MASTER_SID
	,CONTRACT_MASTER_SID
	,*/ITEM_MASTER_SID
	,CCP_DETAILS_SID
	--,PROJECTION_MASTER_SID
	,YEAR
	,PERIOD
	,PERIOD_SID
	INTO #DATA_TABLE
FROM #CCP_DETAILS_TEMP A
CROSS JOIN #PERIOD P

DECLARE @SQL_ACC NVARCHAR(max)

IF Object_id('TEMPDB..#RS_DATA') IS NOT NULL
	DROP TABLE #RS_DATA

CREATE TABLE #RS_DATA (RS_CONTRACT_SID INT)

SET @SQL_ACC = 'INSERT INTO #RS_DATA (RS_CONTRACT_SID)
	SELECT DISTINCT RS_CONTRACT_SID  FROM  ' + @DISC_PROJECTION_MASTER_TABLE + ' RS WHERE  PV_FILTERS = 1
	AND (EXISTS (
			SELECT 1
			FROM [DBO].[UDF_SPLITSTRING](@DISCOUNT_ID, '','') A
			WHERE A.TOKEN = CONVERT(VARCHAR(100),RS.RS_CONTRACT_SID)
			) OR @DISCOUNT_ID IS NULL)'

EXEC Sp_executesql @SQL_ACC
	,N'@DISCOUNT_ID NVARCHAR(MAX)'
	,@DISCOUNT_ID = @DISCOUNT_ID
														 
IF OBJECT_ID('TEMPDB..#ACCRUAL_DISCOUNT') IS NOT NULL
	DROP TABLE #ACCRUAL_DISCOUNT;

CREATE TABLE #ACCRUAL_DISCOUNT (
	PROJECTION_MASTER_SID INT
	,CCP_DETAILS_SID INT
	,PERIOD_SID INT
	,RS_CONTRACT_SID INT
	,DISCOUNT_AMOUNT NUMERIC(33, 8)
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
		,RS.RS_MODEL_SID
	FROM #CCP_DETAILS_TEMP A
	JOIN '+ @DISC_PROJECTION_MASTER_TABLE +' RS ON RS.CCP_DETAILS_SID = A.CCP_DETAILS_SID
	AND PV_FILTERS = 1
	JOIN #PERIOD P on 1=1
	--JOIN PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
	--		AND @END_PERIOD_SID
	WHERE EXISTS (
			SELECT 1
			FROM #RS_DATA Aa
			WHERE Aa.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
			) 
	)
INSERT INTO #ACCRUAL_DISCOUNT (
	CCP_DETAILS_SID
	,PERIOD_SID
	,RS_CONTRACT_SID
	,DISCOUNT_AMOUNT
	)
SELECT A2.CCP_DETAILS_SID
	,PERIOD_SID
	,A2.RS_CONTRACT_SID
	,SUM(DEDUCTION_AMOUNT) / (DATEDIFF(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE) + 1) DISCOUNT_AMOUNT
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
	,ACCRUAL_PERIOD_END_DATE'
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
                   SET @SQL_ACC='WITH GTS
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
	FROM (SELECT DISTINCT YEAR,ITEM_MASTER_SID,PERIOD_SID
	 	,PERIOD FROM #DATA_TABLE) DT
	JOIN ' + @PRODUCT_FILE + ' PF ON DT.ITEM_MASTER_SID = PF.ITEM_MASTER_SID
		AND DT.PERIOD_SID = PF.PERIOD_SID
	GROUP BY YEAR
	 	,PERIOD
	)
	,SALES
AS (
	SELECT PERIOD
	    ,YEAR
		,CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES)
		,CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES)
		,CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS)
		,CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS)
		,COGS_ACTUALS = Sum(COGS_ACTUAL)
		,COGS_PROJECTED = Sum(COGS_PROJECTED)
	FROM (
		SELECT DT.CCP_DETAILS_SID
			,PERIOD
	        ,YEAR
			,DT.PERIOD_SID
			,IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(ACTUAL_SALES,0), NULL) ACTUAL_SALES
			,IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(ACTUAL_UNITS,0), NULL) *  COALESCE(NULLIF(UOM_VALUE, 0),1)  ACTUAL_UNITS
			,IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(PROJECTION_SALES,0), NULL) PROJECTION_SALES
			,IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(PROJECTION_UNITS,0), NULL) *  COALESCE(NULLIF(UOM_VALUE, 0),1)  PROJECTION_UNITS
			,DT.ITEM_MASTER_SID
			,COGS_ACTUAL = (ISNULL(NAS.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1) 
			,COGS_PROJECTED = (ISNULL(NPS.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1) 
		FROM #DATA_TABLE DT
		LEFT JOIN ' +@SALES_MASTER_TABLE +' SPM ON SPM.CCP_DETAILS_SID = DT.CCP_DETAILS_SID
		LEFT JOIN ' + @SALES_ACTUAL_TABLE  + ' NAS ON DT.CCP_DETAILS_SID = NAS.CCP_DETAILS_SID
			AND DT.PERIOD_SID = NAS.PERIOD_SID
		LEFT JOIN '  + @SALES_PROJECTION_TABLE  + ' NPS ON NPS.CCP_DETAILS_SID = DT.CCP_DETAILS_SID
			AND DT.PERIOD_SID = NPS.PERIOD_SID
		LEFT JOIN #ITEM_PRICING U ON DT.ITEM_MASTER_SID = U.ITEM_MASTER_SID
			AND DT.PERIOD_SID = U.PERIOD_SID
		LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID = DT.ITEM_MASTER_SID
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
		,ACCRUAL_DISCOUNT=SUM(ACCRUAL_DISCOUNT)
	FROM (
		SELECT DT.CCP_DETAILS_SID
			,PERIOD
	        ,YEAR
			,DT.PERIOD_SID
			,IIF(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL ), ISNULL(ACTUAL_SALES,0), NULL) ACTUAL_SALES
			,IIF(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL ), ISNULL(PROJECTION_SALES,0), NULL)  PROJECTION_SALES
			,DISCOUNT_AMOUNT  ACCRUAL_DISCOUNT
		FROM #DATA_TABLE DT
		JOIN ' + @DISC_PROJECTION_MASTER_TABLE +' DPM ON DPM.CCP_DETAILS_SID = DT.CCP_DETAILS_SID
			AND  EXISTS (
			SELECT 1
			FROM #RS_DATA A
			WHERE A.RS_CONTRACT_SID = DPM.RS_CONTRACT_SID
			) 
		LEFT JOIN ' + @DISC_ACTUAL_TABLE  + ' NAD ON DPM.CCP_DETAILS_SID = NAD.CCP_DETAILS_SID
			AND NAD.RS_CONTRACT_SID = DPM.RS_CONTRACT_SID
			AND DT.PERIOD_SID = NAD.PERIOD_SID
		LEFT JOIN ' + @DISC_PROJECTION_TABLE  + ' NDP ON NDP.CCP_DETAILS_SID = DPM.CCP_DETAILS_SID
			AND NDP.RS_CONTRACT_SID = DPM.RS_CONTRACT_SID
			AND DT.PERIOD_SID = NDP.PERIOD_SID
		LEFT JOIN #ACCRUAL_DISCOUNT AD ON AD.CCP_DETAILS_SID = DPM.CCP_DETAILS_SID
			AND AD.RS_CONTRACT_SID = DPM.RS_CONTRACT_SID
			AND AD.PERIOD_SID = DT.PERIOD_SID
		) A
	GROUP BY PERIOD
	    ,YEAR
	)
	,PPA
AS (
	SELECT PERIOD
	    ,YEAR
		,PPA_DISCOUNT_ACTUALS = SUM(ACTUAL_PPA_SALES)
		,PPA_RPU_ACTUALS = SUM(ACTUAL_PPA_RPU)
		,PPA_DISCOUNT_PROJECTED = SUM(PROJECTION_PPA_SALES)
		,PPA_RPU_PROJECTED = SUM(PPA_RPU)
		,PPA_ACTUAL_SALES = SUM(ACTUAL_SALES)
		,PPA_ACTUAL_UNITS = SUM(ACTUAL_UNITS)
		,PPA_PROJECTION_SALES = SUM(PROJECTION_SALES)
		,PPA_PROJECTION_UNITS = SUM(PROJECTION_UNITS)
	FROM (
		SELECT DT.CCP_DETAILS_SID
			,PERIOD
	        ,YEAR
			,DT.PERIOD_SID
			,ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR
			,ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR
            ,PPA_RPU = PROJECTION_DISCOUNT_DOLLAR
            ,PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR
			,NPM.RS_CONTRACT_SID
		,ACTUAL_SALES = IIF((SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), NS.ACTUAL_SALES, NULL)
		,ACTUAL_UNITS = IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), NS.ACTUAL_UNITS, NULL) *  COALESCE(NULLIF(UOM_VALUE, 0),1) 
		,PROJECTION_SALES = IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), NP.PROJECTION_SALES, NULL)
		,PROJECTION_UNITS = IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), NP.PROJECTION_UNITS, NULL) *  COALESCE(NULLIF(UOM_VALUE, 0),1) 
		FROM #DATA_TABLE DT
		INNER JOIN ' + @PPA_MASTER_TABLE +' NPM ON NPM.CCP_DETAILS_SID = DT.CCP_DETAILS_SID
			AND (EXISTS (
				SELECT 1
				FROM [DBO].[UDF_SPLITSTRING](@DISCOUNT_ID, '','') A
				WHERE A.TOKEN = NPM.RS_CONTRACT_SID
				) OR @DISCOUNT_ID IS NULL)
		INNER JOIN ' + @SALES_MASTER_TABLE + ' SPM ON SPM.CCP_DETAILS_SID = NPM.CCP_DETAILS_SID
		LEFT JOIN ' + @PPA_ACTUAL_TABLE  + ' NAP ON NPM.CCP_DETAILS_SID = NAP.CCP_DETAILS_SID
			AND NPM.RS_CONTRACT_SID = NAP.RS_CONTRACT_SID
			AND NAP.PERIOD_SID = DT.PERIOD_SID
		LEFT JOIN ' + @PPA_PROJECTION_TABLE  + ' NPP ON NPM.CCP_DETAILS_SID = NPP.CCP_DETAILS_SID
			AND NPP.RS_CONTRACT_SID = NPM.RS_CONTRACT_SID
			AND NPP.PERIOD_SID = DT.PERIOD_SID
		LEFT JOIN ' + @SALES_ACTUAL_TABLE + ' NS ON NS.CCP_DETAILS_SID = DT.CCP_DETAILS_SID
			AND NS.PERIOD_SID = DT.PERIOD_SID
		LEFT JOIN ' + @SALES_PROJECTION_TABLE + ' NP ON NP.CCP_DETAILS_SID = DT.CCP_DETAILS_SID
			AND NP.PERIOD_SID = DT.PERIOD_SID
		LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID = DT.ITEM_MASTER_SID
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
	,DEMAND_SALES_PROJECTED
	,DEMAND_SALES_ACTUALS
	,INVENTORY_WITHDRAWAL_SALES_ACTUALS
	,INVENTORY_WITHDRAWAL_SALES_PROJECTED
	,EX_FACTORY_SALES_ACTUALS_PERCENT
	,EX_FACTORY_SALES_PROJECTED_PERCENT
	,DEMAND_SALES_ACTUALS_PERCENT
	,DEMAND_SALES_PROJECTED_PERCENT
	,INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT
	,INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT
	,CONTRACT_SALES_ACTUALS
	,CONTRACT_SALES_PROJECTED
	,CONTRACT_UNITS_ACTUALS
	,CONTRACT_UNITS_PROJECTED
	,TOTAL_DISCOUNT
	,TOTAL_DISCOUNT_PROJECTED
	,TOTAL_DISCOUNT_PERCENTAGE
	,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE
	,PPA_DISCOUNT_ACTUALS
	,PPA_DISCOUNT_PROJECTED
	,PPA_DISCOUNT_ACTUALS_PERCENTAGE
	,PPA_DISCOUNT_PROJECTED_PERCENTAGE
	,TOTAL_DISCOUNT_RPU_ACTUALS
	,TOTAL_DISCOUNT_RPU_PROJECTED
	,TOTAL_PPA_RPU_ACTUALS
	,TOTAL_PPA_RPU_PROJECTED
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
	,DEMAND_SALES_ACTUALS = CASE  WHEN @CURRENT_DATE > PERIOD_SID THEN Isnull(GTS.DEMAND_SALES_ACTUAL, 0) ELSE NULL END
	,DEMAND_SALES_PROJECTED = Isnull(GTS.DEMAND_SALES_PROJECTED, 0)
	,INVENTORY_WITHDRAWAL_SALES_ACTUALS = CASE  WHEN @CURRENT_DATE > PERIOD_SID THEN Isnull(GTS.ACT_AMOUNT_WITHDRAWN, 0) ELSE NULL END
	,INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(GTS.FOR_AMOUNT_WITHDRAWN, 0)
	,EX_FACTORY_SALES_ACTUALS_PERCENT = CASE  WHEN @CURRENT_DATE > PERIOD_SID THEN (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0)) * 100 ELSE NULL END
	,EX_FACTORY_SALES_PROJECTED_PERCENT = (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0)) * 100
	,DEMAND_SALES_ACTUAL_PERCENT = CASE  WHEN @CURRENT_DATE > PERIOD_SID THEN (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0)) * 100 ELSE NULL END
	,DEMAND_SALES_PROJECTED_PERCENT = (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0)) * 100
	,INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = CASE  WHEN @CURRENT_DATE > PERIOD_SID THEN (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0)) * 100
		ELSE NULL
		END
	,INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0)) * 100
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
			THEN Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
		ELSE NULL
		END
	,TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0), 0)
	,TOTAL_DISCOUNT_PERCENTAGE = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN (Isnull((DISC.CONTRACT_DISCOUNT_ACTUALS + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0)) * 100
		ELSE NULL
		END
	,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = (Isnull((DISC.CONTRACT_DISCOUNT_PROJECTED + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0)) * 100
	,PPA_DISCOUNT_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
		ELSE NULL
		END
	,PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
	,PPA_DISCOUNT_ACTUALS_PERCENTAGE = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100
		ELSE NULL
		END
	,PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100
	,TOTAL_DISCOUNT_RPU_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN (Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0))
		ELSE NULL
		END
	,TOTAL_DISCOUNT_RPU_PROJECTED = (Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0))
	,TOTAL_PPA_RPU_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN (Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0))
		ELSE NULL
		END
	,TOTAL_PPA_RPU_PROJECTED = (Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0))
	,TOTAL_RPU_ACTUAL = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN (Isnull((DISC.CONTRACT_DISCOUNT_ACTUALS + Isnull(PPA.PPA_RPU_ACTUALS, 0)) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0))
		ELSE NULL
		END
	,TOTAL_RPU_PROJECTED = (Isnull((DISC.CONTRACT_DISCOUNT_PROJECTED + Isnull(PPA.PPA_RPU_PROJECTED, 0)) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0))
	,NET_SALES = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN (SALES.CONTRACT_SALES_ACTUALS - (DISC.CONTRACT_DISCOUNT_ACTUALS + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))
		ELSE NULL
		END
	,NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED - (DISC.CONTRACT_DISCOUNT_PROJECTED + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)), 0)
	,COGS_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN Isnull(SALES.COGS_ACTUALS, 0)
		ELSE NULL
		END
	,COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0)
	,NET_PROFIT_ACTUAL = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN isnull(((SALES.CONTRACT_SALES_ACTUALS - (DISC.CONTRACT_DISCOUNT_ACTUALS + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0))) - (SALES.COGS_ACTUALS)),0)
		ELSE NULL
		END
	,NET_PROFIT_PROJECTED = isnull(((SALES.CONTRACT_SALES_PROJECTED - (DISC.CONTRACT_DISCOUNT_PROJECTED + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0))) - (SALES.COGS_PROJECTED)),0)
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
			THEN (COALESCE(((SALES.CONTRACT_SALES_ACTUALS - (DISC.CONTRACT_DISCOUNT_ACTUALS + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))) / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) * 100)
		ELSE NULL
		END
	,NET_SALES_OF_EX_FACTORY_PROJECTED = (COALESCE(((SALES.CONTRACT_SALES_PROJECTED- (DISC.CONTRACT_DISCOUNT_PROJECTED + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100)
	,DISCOUNT_OF_EX_FACTORY_ACTUALS = CASE 
		WHEN @CURRENT_DATE > PERIOD_SID
			THEN COALESCE(((DISC.CONTRACT_DISCOUNT_ACTUALS + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0))) / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) * 100
		ELSE NULL
		END
	,DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((DISC.CONTRACT_DISCOUNT_PROJECTED + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0))) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100
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
					THEN ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS + ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0), 0)
				ELSE NULL
				END
			)
		) -----------cel-386
	,NET_EX_FACTORY_SALES_PROJECTED = isnull(ISNULL(GTS.GTS_SALES_PROJECTED, 0) - (DISC.CONTRACT_DISCOUNT_PROJECTED + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0)),0) -----------cel-386
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
						THEN ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS + ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0), 0)
					ELSE NULL
					END
				)
			) / nullif(CASE 
				WHEN @CURRENT_DATE > PERIOD_SID
					THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)
				ELSE NULL
				END, 0), 0) * 100 -----------cel-386
	,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED = ISNULL((ISNULL(GTS.GTS_SALES_PROJECTED, 0) - (DISC.CONTRACT_DISCOUNT_PROJECTED + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0))) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100
	,-----------cel-386
	ACCRUAL_DISCOUNT AS TOTAL_DISCOUNT_ACCRUAL
FROM (
	SELECT MIN(PERIOD_SID) PERIOD_SID
		,YEAR
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
LEFT JOIN PPA PPA ON DT.YEAR = PPA.YEAR
	AND DT.PERIOD = PPA.PERIOD
	'


                    
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


IF OBJECT_ID('TEMPDB..#PRIOR_TEMP_CCP') IS NOT NULL
	DROP TABLE #PRIOR_TEMP_CCP

CREATE TABLE #PRIOR_TEMP_CCP (
	COMPANY_MASTER_SID INT
	,CONTRACT_MASTER_SID INT
	,ITEM_MASTER_SID INT
	,CCP_DETAILS_SID INT 
	,PROJECTION_MASTER_SID INT
	,PROJECTION_DETAILS_SID int
	--,BUSINESS_UNIT INT
	PRIMARY KEY (PROJECTION_MASTER_SID,CCP_DETAILS_SID)
	)

INSERT INTO #PRIOR_TEMP_CCP (
	COMPANY_MASTER_SID
	,CONTRACT_MASTER_SID
	,ITEM_MASTER_SID
	,CCP_DETAILS_SID
	,PROJECTION_MASTER_SID
	,PROJECTION_DETAILS_SID
	)
SELECT CD.COMPANY_MASTER_SID
	,CD.CONTRACT_MASTER_SID
	,CD.ITEM_MASTER_SID
	,CD.CCP_DETAILS_SID
	,A.PROJECTION_MASTER_SID
	,pd.PROJECTION_DETAILS_SID
FROM #PROJECTION_MASTER A
JOIN PROJECTION_DETAILS PD ON A.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
AND ID<>1
JOIN  CCP_DETAILS CD  ON PD.CCP_DETAILS_SID=CD.CCP_DETAILS_SID
WHERE EXISTS (SELECT 1 FROM #CCP_DETAILS_TEMP TC WHERE TC.CCP_DETAILS_SID=CD.CCP_DETAILS_SID)
	
IF Object_id('TEMPDB..#PRIOR_DATA_TABLE') IS NOT NULL
	DROP TABLE #PRIOR_DATA_TABLE

SELECT COMPANY_MASTER_SID
	,CONTRACT_MASTER_SID
	,ITEM_MASTER_SID
	,CCP_DETAILS_SID
	,PROJECTION_MASTER_SID
	,PROJECTION_DETAILS_SID
	,YEAR
	,PERIOD
	,PERIOD_SID
	INTO #PRIOR_DATA_TABLE
FROM #PRIOR_TEMP_CCP A
CROSS JOIN #PERIOD 
/*
IF Object_id('TEMPDB..#PRIOR_RS_PPA') IS NOT NULL
	DROP TABLE #PRIOR_RS_PPA

CREATE TABLE #PRIOR_RS_PPA (RS_CONTRACT_SID INT)

INSERT INTO #PRIOR_RS_PPA
EXEC ('SELECT DISTINCT RS_CONTRACT_SID FROM ' + @PPA_MASTER_TABLE + ' X  WHERE EXISTS (SELECT 1 FROM #PRIOR_TEMP_CCP R WHERE R.CCP_DETAILS_SID = X.CCP_DETAILS_SID)')
*/

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
                      INSERT INTO #PRODUCT_FILE_TEMP(PROJECTION_MASTER_SID,
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
                      EXEC dbo.Prc_prior_proj_prod_file_data
					   

IF OBJECT_ID('TEMPDB..#SALES_INCLUSION') IS NOT NULL
	DROP TABLE #SALES_INCLUSION

SELECT DISTINCT  A.PROJECTION_DETAILS_SID
	,CD.CCP_DETAILS_SID
	,CASE 
		WHEN DESCRIPTION = 'YES'
			THEN 1
		ELSE 0
		END SALES_INCLUSION
INTO #SALES_INCLUSION
FROM NM_SALES_PROJECTION_MASTER A
--JOIN PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
JOIN #PRIOR_TEMP_CCP CD ON --CD.CCP_DETAILS_SID = B.CCP_DETAILS_SID AND
 CD.PROJECTION_DETAILS_SID=A.PROJECTION_DETAILS_SID
JOIN CFP_CONTRACT CC1 ON CC1.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
AND INBOUND_STATUS <> 'D'
JOIN CFP_CONTRACT_DETAILS CC ON 
  CC1.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
	AND CC.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID
JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = CC1.SALES_INCLUSION

IF OBJECT_ID('TEMPDB..#DEDUCTION_INCLUSION') IS NOT NULL
	DROP TABLE #DEDUCTION_INCLUSION

SELECT A.PROJECTION_DETAILS_SID
	,CD.CCP_DETAILS_SID
	,RS.RS_CONTRACT_SID
	,CASE 
		WHEN DESCRIPTION = 'YES'
			THEN 1
		ELSE 0
		END DEDUCTION_INCLUSION
INTO #DEDUCTION_INCLUSION
FROM NM_DISCOUNT_PROJ_MASTER A
--JOIN PROJECTION_DETAILS B ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
JOIN #PRIOR_TEMP_CCP CD ON --CD.CCP_DETAILS_SID = B.CCP_DETAILS_SID	AND
 CD.PROJECTION_DETAILS_SID=a.PROJECTION_DETAILS_SID
JOIN RS_CONTRACT RS ON A.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = RS.DEDUCTION_INCLUSION

IF OBJECT_ID('TEMPDB..#PRIOR_ACCRUAL_DISCOUNT') IS NOT NULL
	DROP TABLE #PRIOR_ACCRUAL_DISCOUNT;

CREATE TABLE #PRIOR_ACCRUAL_DISCOUNT (
	PROJECTION_MASTER_SID INT
	,PROJECTION_DETAILS_SID INT
	,PERIOD_SID INT
	,RS_CONTRACT_SID INT
	,DISCOUNT_AMOUNT NUMERIC(33, 8)
	);

WITH CTE
AS (
	SELECT DISTINCT COMPANY_MASTER_SID
		,CONTRACT_MASTER_SID
		,ITEM_MASTER_SID
		,CD.PROJECTION_MASTER_SID
		,CD.PROJECTION_DETAILS_SID
		,PERIOD_SID
		,PERIOD_DATE
		,RS.RS_CONTRACT_SID
		,RS.RS_MODEL_SID
	FROM  PROJECTION_DETAILS CD 
	JOIN #PRIOR_TEMP_CCP CDD ON CD.CCP_DETAILS_SID = CDD.CCP_DETAILS_SID
	AND CD.PROJECTION_MASTER_SID=CDD.PROJECTION_MASTER_SID
	JOIN NM_DISCOUNT_PROJ_MASTER RS ON RS.PROJECTION_DETAILS_SID = CD.PROJECTION_DETAILS_SID
	JOIN #PERIOD P on 1=1
	--JOIN PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
	--		AND @END_PERIOD_SID
	WHERE EXISTS (
			SELECT 1
			FROM [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
			WHERE A.TOKEN = convert(varchar(100),RS.RS_CONTRACT_SID)
			)
	)
INSERT INTO #PRIOR_ACCRUAL_DISCOUNT (
	PROJECTION_MASTER_SID
	,PROJECTION_DETAILS_SID
	,PERIOD_SID
	,RS_CONTRACT_SID
	,DISCOUNT_AMOUNT
	)
SELECT PROJECTION_MASTER_SID
	,A2.PROJECTION_DETAILS_SID
	,PERIOD_SID
	,A2.RS_CONTRACT_SID
	,SUM(DEDUCTION_AMOUNT) / (DATEDIFF(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE) + 1) DISCOUNT_AMOUNT
FROM ACCRUAL_MASTER A1
JOIN CTE A2 ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, 0))))
		AND CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_END_DATE, 0))))
	AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID
	AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID
	AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID
	AND A2.RS_MODEL_SID = A1.RS_MODEL_SID
	AND DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, - 1)) >= @STARTFROM
	AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJECTION_DATE
GROUP BY PROJECTION_MASTER_SID
	,A2.PROJECTION_DETAILS_SID
	,PERIOD_SID
	,A2.RS_CONTRACT_SID
	,ACCRUAL_PERIOD_START_DATE
	,ACCRUAL_PERIOD_END_DATE
	
                  ---FIRST PROJECTION_DETAILS_SID (PULL FROM ST TABLE) END
                      --SECOND TO LAST PROJECTION_DETAILS_SID(PULL FROM MAIN TABLE) START
         
                            INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
                                         PERIOD,
                                         [YEAR],
                                         EX_FACTORY_SALES_ACTUALS,
                                         EX_FACTORY_SALES_PROJECTED,
                                         DEMAND_SALES_ACTUALS,
                                         DEMAND_SALES_PROJECTED,
                                         INVENTORY_WITHDRAWAL_SALES_ACTUALS,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                         EX_FACTORY_SALES_ACTUALS_PERCENT,
                                         EX_FACTORY_SALES_PROJECTED_PERCENT,
                                         DEMAND_SALES_ACTUALS_PERCENT,
                                         DEMAND_SALES_PROJECTED_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                         CONTRACT_SALES_ACTUALS,
                                         CONTRACT_SALES_PROJECTED,
                                         CONTRACT_UNITS_ACTUALS,
                                         CONTRACT_UNITS_PROJECTED,
                                         TOTAL_DISCOUNT,
                                         TOTAL_DISCOUNT_PROJECTED,
                                         TOTAL_DISCOUNT_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         PPA_DISCOUNT_ACTUALS,
                                         PPA_DISCOUNT_PROJECTED,
                                         PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                         PPA_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_DISCOUNT_RPU_ACTUALS,
                                         TOTAL_DISCOUNT_RPU_PROJECTED,
                                         TOTAL_PPA_RPU_ACTUALS,
                                         TOTAL_PPA_RPU_PROJECTED,
                                         TOTAL_RPU_ACTUAL,
                                         TOTAL_RPU_PROJECTED,
                                         NET_SALES,
                                         NET_SALES_PROJECTED,
                                         COGS_ACTUALS,
                                         COGS_PROJECTED,
                                         NET_PROFIT_ACTUAL,
                                         NET_PROFIT_PROJECTED,
                                         TOTAL_RETURNS_RPU_ACTUALS,
                                         TOTAL_RETURNS_RPU_PROJECTED,
                                         RETURNS_ACTUALS_AMOUNT,
                                         RETURNS_PROJECTED_AMOUNT,
                                         RETURNS_ACTUALS_PERCENTAGE,
                                         RETURNS_PROJECTED_PERCENTAGE,
                                         NET_SALES_OF_EX_FACTORY_ACTUALS,
                                         NET_SALES_OF_EX_FACTORY_PROJECTED,
                                         DISCOUNT_OF_EX_FACTORY_ACTUALS,
                                         DISCOUNT_OF_EX_FACTORY_PROJECTED
										,NET_EX_FACTORY_SALES_ACTUALS---------------CEL-386
										,NET_EX_FACTORY_SALES_PROJECTED---------------CEL-386
										,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS 	---------------CEL-386				  
										,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED,---------------CEL-386
										 TOTAL_DISCOUNT_ACCRUAL
										 )
                            SELECT COALESCE(a.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
                                   A.PERIOD,
                                   A.[YEAR],
                                   Isnull(A.GTS_SALES_ACTUALS, 0) as EX_FACTORY_SALES_ACTUALS ,
                                    Isnull(A.GTS_SALES_PROJECTED, 0)AS EX_FACTORY_SALES_PROJECTED ,
                                    Isnull(A.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS ,
                                    Isnull(A.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED ,
                                    Isnull(A.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_ACTUALS ,
                                    Isnull(A.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED ,
                                   (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT ,
                                   (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                                   (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUAL_PERCENT ,
                                   (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT ,
                                   (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS  INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT ,
                                   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS ,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED ,
                                   Isnull(CONTRACT_UNITS_ACTUALS, 0) AS  CONTRACT_UNITS_ACTUALS ,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS  CONTRACT_UNITS_PROJECTED ,
                                    Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) AS TOTAL_DISCOUNT ,
                                    Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED ,
                                    ( Isnull( DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PERCENTAGE ,
                                    ( Isnull( DISC.CONTRACT_DISCOUNT_PROJECTED  / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 as TOTAL_DISCOUNT_PROJECTED_PERCENTAGE ,
                                    0 AS PPA_DISCOUNT_ACTUALS ,
                                   0 AS PPA_DISCOUNT_PROJECTED ,
                                    0 AS PPA_DISCOUNT_ACTUALS_PERCENTAGE ,
                                    0  AS PPA_DISCOUNT_PROJECTED_PERCENTAGE ,
                                    ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_DISCOUNT_RPU_ACTUALS ,
                                    ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_DISCOUNT_RPU_PROJECTED ,
                                   0 AS  TOTAL_PPA_RPU_ACTUALS ,
                                    0 AS TOTAL_PPA_RPU_PROJECTED ,
                                   ( Isnull( DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUAL ,
                                    ( Isnull( DISC.CONTRACT_DISCOUNT_PROJECTED  / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED ,
                                   ( Isnull(SALES.CONTRACT_SALES_ACTUALS,0) -  Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)) AS NET_SALES ,
                                    Isnull(SALES.CONTRACT_SALES_PROJECTED,0) - isnull( DISC.CONTRACT_DISCOUNT_PROJECTED, 0) AS NET_SALES_PROJECTED , 
                                    Isnull(SALES.COGS_ACTUALS, 0) AS COGS_ACTUALS ,
                                    Isnull(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED ,
                                   (  Isnull(SALES.CONTRACT_SALES_ACTUALS,0) - Isnull( DISC.CONTRACT_DISCOUNT_ACTUALS, 0 ) - Isnull( SALES.COGS_ACTUALS , 0) )AS NET_PROFIT_ACTUAL ,
                                     Isnull(SALES.CONTRACT_SALES_PROJECTED,0) - Isnull( DISC.CONTRACT_DISCOUNT_PROJECTED, 0 ) - Isnull( SALES.COGS_PROJECTED ,0) AS NET_PROFIT_PROJECTED ,
                                    000000.0 AS TOTAL_RETURNS_RPU_ACTUALS,
                                    000000.0 AS TOTAL_RETURNS_RPU_PROJECTED,
                                    000000.0 AS RETURNS_ACTUALS_AMOUNT,
                                    000000.0 AS RETURNS_PROJECTED_AMOUNT,
                                    000000.0 AS RETURNS_ACTUALS_PERCENTAGE,
                                    000000.0 AS RETURNS_PROJECTED_PERCENTAGE,
                                   ( COALESCE(( Isnull(SALES.CONTRACT_SALES_ACTUALS,0) -  Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)) / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) * 100 ) AS NET_SALES_OF_EX_FACTORY_ACTUALS ,
                                    ( COALESCE((Isnull(SALES.CONTRACT_SALES_PROJECTED,0) - isnull( DISC.CONTRACT_DISCOUNT_PROJECTED, 0)) / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) * 100 ) AS NET_SALES_OF_EX_FACTORY_PROJECTED ,
                                    COALESCE((( DISC.CONTRACT_DISCOUNT_ACTUALS )) / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) * 100 AS DISCOUNT_OF_EX_FACTORY_ACTUALS ,
                                  COALESCE((( DISC.CONTRACT_DISCOUNT_PROJECTED )) / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) * 100 AS  DISCOUNT_OF_EX_FACTORY_PROJECTED
                            ,NET_EX_FACTORY_SALES_ACTUALS=isnull(((ISNULL(A.GTS_SALES_ACTUALS, 0))-(ISNULL( DISC.CONTRACT_DISCOUNT_ACTUALS,0))),0) -----------cel-386
							,NET_EX_FACTORY_SALES_PROJECTED =isnull(ISNULL(A.GTS_SALES_PROJECTED, 0)-(ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)),0)-----------cel-386
							,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS=ISNULL((isnull(((ISNULL(A.GTS_SALES_ACTUALS, 0))-(ISNULL( DISC.CONTRACT_DISCOUNT_ACTUALS,0))),0))/NULLIF( ISNULL(A.GTS_SALES_ACTUALS, 0),0),0)*100  -----------cel-386
							,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED=ISNULL(isnull(ISNULL(A.GTS_SALES_PROJECTED, 0)-(ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)),0)/NULLIF(A.GTS_SALES_PROJECTED, 0),0)*100, -----------cel-386
							 ACCRUAL_DISCOUNT AS TOTAL_DISCOUNT_ACCRUAL
                            FROM  (
	SELECT PM.PROJECTION_MASTER_SID,
		SUM(INVENTORY_ACTUAL_SALES) AS ACT_AMOUNT_WITHDRAWN,
		SUM(INVENTORY_ACTUAL_UNITS) AS ACT_UNITS_WITHDRAWN,
		SUM(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)) AS FOR_AMOUNT_WITHDRAWN,
		SUM(COALESCE(INVENTORY_FORECAST_UNITS, INVENTORY_ACTUAL_UNITS)) AS FOR_UNITS_WITHDRAWN,
		SUM(DEMAND_ACTUAL_SALES) AS DEMAND_SALES_ACTUAL,
		SUM(DEMAND_ACTUAL_UNITS) AS ACT_GROSS_UNITS,
		SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)) AS DEMAND_SALES_PROJECTED,
		SUM(COALESCE(DEMAND_FORECAST_UNITS, DEMAND_ACTUAL_UNITS)) AS FOR_GROSS_UNITS,
		SUM(EXFACTORY_ACTUAL_SALES) AS GTS_SALES_ACTUALS,
		SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) AS GTS_SALES_PROJECTED,
		SUM(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS)) AS UNITS,
		P.YEAR,
		P.PERIOD 
	FROM (SELECT distinct PROJECTION_MASTER_SID,ITEM_MASTER_SID FROM  #PRIOR_TEMP_CCP) PM
	join #PERIOD p on 1=1
	LEFT JOIN #PRODUCT_FILE_TEMP PF
		ON PM.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID
			AND PF.ITEM_MASTER_SID = PM.ITEM_MASTER_SID
			AND PF.period_sid = P.period_sid
	GROUP BY PM.PROJECTION_MASTER_SID,
		P.PERIOD,
		P.YEAR
	) A
INNER JOIN (
	SELECT PROJECTION_MASTER_SID,
		PERIOD,
		YEAR,
		CONTRACT_SALES_ACTUALS = SUM(ACTUAL_SALES),
		CONTRACT_SALES_PROJECTED = SUM(PROJECTION_SALES),
		CONTRACT_UNITS_ACTUALS = SUM(ACTUAL_UNITS),
		CONTRACT_UNITS_PROJECTED = SUM(PROJECTION_UNITS),
		COGS_ACTUALS = SUM(COGS_ACTUAL),
		COGS_PROJECTED = SUM(COGS_PROJECTED)
	FROM (
		SELECT PT.PROJECTION_MASTER_SID,
			PT.CCP_DETAILS_SID,
			PERIOD,
			YEAR,
			PT.PERIOD_SID
		,IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(ACTUAL_SALES,0), NULL) ACTUAL_SALES
		,IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(ACTUAL_UNITS,0), NULL) *  COALESCE(NULLIF(UOM_VALUE, 0),1)  ACTUAL_UNITS
		,IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(PROJECTION_SALES,0), NULL) PROJECTION_SALES
		,IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ISNULL(PROJECTION_UNITS,0), NULL) *  COALESCE(NULLIF(UOM_VALUE, 0),1)  PROJECTION_UNITS
		,COGS_ACTUAL = (IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), ACTUAL_UNITS, NULL) * ISNULL(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1) 
		,COGS_PROJECTED = (IIF(( SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ), PROJECTION_UNITS, NULL) * ISNULL(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1) 
		FROM #PRIOR_DATA_TABLE PT
		/*INNER JOIN PROJECTION_DETAILS PD
			ON PT.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
				AND PD.PROJECTION_MASTER_SID = PT.PROJECTION_MASTER_SID*/
		INNER JOIN #SALES_INCLUSION SI
			ON SI.PROJECTION_DETAILS_SID = PT.PROJECTION_DETAILS_SID
		LEFT JOIN NM_ACTUAL_SALES NAS
			ON NAS.PROJECTION_DETAILS_SID = PT.PROJECTION_DETAILS_SID
				AND NAS.PERIOD_SID = PT.PERIOD_SID
		LEFT JOIN NM_SALES_PROJECTION NSP
			ON NSP.PROJECTION_DETAILS_SID = PT.PROJECTION_DETAILS_SID
				AND NSP.PERIOD_SID = PT.PERIOD_SID
		LEFT JOIN #ITEM_PRICING U
			ON PT.ITEM_MASTER_SID = U.ITEM_MASTER_SID
				AND PT.PERIOD_SID = U.PERIOD_SID
		LEFT JOIN #ITEM_UOM_DETAILS UOM
			ON UOM.ITEM_MASTER_SID = PT.ITEM_MASTER_SID
		) A
	GROUP BY PROJECTION_MASTER_SID,
		YEAR,
		PERIOD
	) SALES
	ON SALES.PERIOD = A.PERIOD
		AND sales.YEAR = a.YEAR
		AND SALES.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
		LEFT JOIN (
	SELECT PROJECTION_MASTER_SID,
		PERIOD,
		YEAR,
		CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
		CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
		ACCRUAL_DISCOUNT = SUM(ACCRUAL_DISCOUNT)
	FROM (
		SELECT PT.PROJECTION_MASTER_SID,
			PT.CCP_DETAILS_SID,
			PERIOD,
			YEAR,
			PT.PERIOD_SID,
			IIF(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL ), ISNULL(ACTUAL_SALES,0), NULL) ACTUAL_SALES,
			IIF(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL ), ISNULL(PROJECTION_SALES,0), NULL)  PROJECTION_SALES,
			DISCOUNT_AMOUNT ACCRUAL_DISCOUNT
		FROM #PRIOR_DATA_TABLE PT
		/*INNER JOIN PROJECTION_DETAILS PD
			ON PT.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
				AND PD.PROJECTION_MASTER_SID = PT.PROJECTION_MASTER_SID*/
		INNER JOIN #DEDUCTION_INCLUSION DI
			ON DI.PROJECTION_DETAILS_SID = PT.PROJECTION_DETAILS_SID
		LEFT JOIN NM_ACTUAL_DISCOUNT NAD
			ON NAD.PROJECTION_DETAILS_SID = PT.PROJECTION_DETAILS_SID
				AND DI.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID
				AND NAD.PERIOD_SID = PT.PERIOD_SID
		LEFT JOIN NM_DISCOUNT_PROJECTION NDP
			ON NDP.PROJECTION_DETAILS_SID = PT.PROJECTION_DETAILS_SID
				AND DI.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
				AND NDP.PERIOD_SID = PT.PERIOD_SID
		LEFT JOIN #PRIOR_ACCRUAL_DISCOUNT AD
			ON AD.PROJECTION_MASTER_SID = PT.PROJECTION_MASTER_SID
				AND AD.PROJECTION_DETAILS_SID = PT.PROJECTION_DETAILS_SID
				AND AD.RS_CONTRACT_SID = DI.RS_CONTRACT_SID
				AND AD.PERIOD_SID = PT.PERIOD_SID
		) A
	GROUP BY PROJECTION_MASTER_SID,
		PERIOD,
		YEAR
	) DISC
	ON DISC.PERIOD = A.PERIOD
		AND disc.year = A.YEAR
		AND DISC.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
--LEFT JOIN (
--	SELECT 0 AS PPA_DISCOUNT_ACTUALS,
--	0 AS PPA_RPU_ACTUALS,
--	0 AS PPA_DISCOUNT_PROJECTED,
--	0 AS PPA_RPU_PROJECTED,
--	0 AS PPA_ACTUAL_SALES,
--	0 AS PPA_ACTUAL_UNITS,
--	0 AS PPA_PROJECTION_SALES,
--	0 AS PPA_PROJECTION_UNITS,
--	PERIOD,
--	year,
--	PROJECTION_MASTER_SID
--FROM #PRIOR_DATA_TABLE
--) PPA
--	ON PPA.PERIOD = SALES.PERIOD
--		AND ppa.year = sales.year
--		AND A.PROJECTION_MASTER_SID = PPA.PROJECTION_MASTER_SID
--ORDER BY SALES.PERIOD,
--	sales.year
                 
       --                ELSE IF @PROJ_FREQUENCY = 'QUARTERLY'----------------------------IT WILL DISPLAY THE QUARTERLY WISE DATA  -------------------------
       --                 BEGIN
       --                     INSERT INTO #PIVOT_RESULT
       --                                 (PROJECTION_ID,
       --                                  [PERIOD],
       --                                  [YEAR],
       --                                  EX_FACTORY_SALES_ACTUALS,
       --                                  EX_FACTORY_SALES_PROJECTED,
       --                                  DEMAND_SALES_ACTUALS,
       --                                  DEMAND_SALES_PROJECTED,
       --                                  INVENTORY_WITHDRAWAL_SALES_ACTUALS,
       --                                  INVENTORY_WITHDRAWAL_SALES_PROJECTED,
       --                                  EX_FACTORY_SALES_ACTUALS_PERCENT,
       --                                  EX_FACTORY_SALES_PROJECTED_PERCENT,
       --                                  DEMAND_SALES_ACTUALS_PERCENT,
       --                                  DEMAND_SALES_PROJECTED_PERCENT,
       --                                  INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
       --                                  INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
       --                                  CONTRACT_SALES_ACTUALS,
       --                                  CONTRACT_SALES_PROJECTED,
       --                                  CONTRACT_UNITS_ACTUALS,
       --                                  CONTRACT_UNITS_PROJECTED,
       --                                  TOTAL_DISCOUNT,
       --                                  TOTAL_DISCOUNT_PROJECTED,
       --                                  TOTAL_DISCOUNT_PERCENTAGE,
       --                                  TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
       --                                  PPA_DISCOUNT_ACTUALS,
       --                                  PPA_DISCOUNT_PROJECTED,
       --                                  PPA_DISCOUNT_ACTUALS_PERCENTAGE,
       --                                  PPA_DISCOUNT_PROJECTED_PERCENTAGE,
       --                                  TOTAL_DISCOUNT_RPU_ACTUALS,
       --                                  TOTAL_DISCOUNT_RPU_PROJECTED,
       --                                  TOTAL_PPA_RPU_ACTUALS,
       --                                  TOTAL_PPA_RPU_PROJECTED,
       --                                  TOTAL_RPU_ACTUAL,
       --                                  TOTAL_RPU_PROJECTED,
       --                                  NET_SALES,
       --                                  NET_SALES_PROJECTED,
       --                                  COGS_ACTUALS,
       --                                  COGS_PROJECTED,
       --                                  NET_PROFIT_ACTUAL,
       --                                  NET_PROFIT_PROJECTED,
       --                                  TOTAL_RETURNS_RPU_ACTUALS,
       --                                  TOTAL_RETURNS_RPU_PROJECTED,
       --                                  RETURNS_ACTUALS_AMOUNT,
       --                                  RETURNS_PROJECTED_AMOUNT,
       --                                  RETURNS_ACTUALS_PERCENTAGE,
       --                                  RETURNS_PROJECTED_PERCENTAGE,
       --                                  NET_SALES_OF_EX_FACTORY_ACTUALS,
       --                                  NET_SALES_OF_EX_FACTORY_PROJECTED,
       --                                  DISCOUNT_OF_EX_FACTORY_ACTUALS,
       --                                  DISCOUNT_OF_EX_FACTORY_PROJECTED
							--			,NET_EX_FACTORY_SALES_ACTUALS---------------CEL-386
							--			,NET_EX_FACTORY_SALES_PROJECTED---------------CEL-386
							--			,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS 	---------------CEL-386				  
							--			,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED,---------------CEL-386
							--			 TOTAL_DISCOUNT_ACCRUAL
							--			 )
       --                     SELECT COALESCE(a.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
       --                            COALESCE(A.QUARTERLY, SALES.[QUARTER]),
       --                            COALESCE(A.YEARLY, SALES.[YEAR]),
       --                               Isnull(A.GTS_SALES_ACTUALS, 0) as EX_FACTORY_SALES_ACTUALS ,
       --                             Isnull(A.GTS_SALES_PROJECTED, 0)AS EX_FACTORY_SALES_PROJECTED ,
       --                             Isnull(A.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS ,
       --                             Isnull(A.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED ,
       --                             Isnull(A.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_ACTUALS ,
       --                             Isnull(A.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED ,
       --                            (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUAL_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS  INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
       --                            (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT ,
       --                            Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS ,
       --                            Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED ,
       --                            Isnull(CONTRACT_UNITS_ACTUALS, 0) AS  CONTRACT_UNITS_ACTUALS ,
       --                            Isnull(CONTRACT_UNITS_PROJECTED, 0) AS  CONTRACT_UNITS_PROJECTED ,
       --                             Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                             + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS TOTAL_DISCOUNT ,
       --                             Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                       + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED ,
       --                             ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                   + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PERCENTAGE ,
       --                             ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                             + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 as TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
       --                             Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS PPA_DISCOUNT_ACTUALS ,
       --                             Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS PPA_DISCOUNT_PROJECTED ,
       --                             Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100 AS PPA_DISCOUNT_ACTUALS_PERCENTAGE ,
       --                             Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100 AS PPA_DISCOUNT_PROJECTED_PERCENTAGE ,
       --                             ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_DISCOUNT_RPU_ACTUALS ,
       --                             ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_DISCOUNT_RPU_PROJECTED ,
       --                            ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS  TOTAL_PPA_RPU_ACTUALS ,
       --                             ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_PPA_RPU_PROJECTED ,
       --                            ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                          + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUAL ,
       --                             ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                             + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED ,
       --                            ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                                      + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) AS NET_SALES ,
       --                             Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                                                + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) AS NET_SALES_PROJECTED , 
       --                             Isnull(SALES.COGS_ACTUALS, 0) AS COGS_ACTUALS ,
       --                             Isnull(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED ,
       --                            ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                                                + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL ,
       --                             ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                                                     + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED ,
       --                             000000.0 AS TOTAL_RETURNS_RPU_ACTUALS,
       --                             000000.0 AS TOTAL_RETURNS_RPU_PROJECTED,
       --                             000000.0 AS RETURNS_ACTUALS_AMOUNT,
       --                             000000.0 AS RETURNS_PROJECTED_AMOUNT,
       --                             000000.0 AS RETURNS_ACTUALS_PERCENTAGE,
       --                             000000.0 AS RETURNS_PROJECTED_PERCENTAGE,
       --                            ( COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                                                                        + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) )) / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) * 100 ) AS NET_SALES_OF_EX_FACTORY_ACTUALS ,
       --                             ( COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                                                                            + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) )) / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) * 100 ) AS NET_SALES_OF_EX_FACTORY_PROJECTED ,
       --                             COALESCE((( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                         + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) )) / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) * 100 AS DISCOUNT_OF_EX_FACTORY_ACTUALS ,
       --                           COALESCE((( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                           + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) )) / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) * 100 AS  DISCOUNT_OF_EX_FACTORY_PROJECTED
       --                     ,NET_EX_FACTORY_SALES_ACTUALS=((ISNULL(A.GTS_SALES_ACTUALS, 0))-( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)+ ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0))) -----------cel-386
							--,NET_EX_FACTORY_SALES_PROJECTED =ISNULL(A.GTS_SALES_PROJECTED, 0)-(ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0) + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0))-----------cel-386
							--,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS=ISNULL((( ISNULL(A.GTS_SALES_ACTUALS, 0) )-( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)+ ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0)  ))/NULLIF( ISNULL(A.GTS_SALES_ACTUALS, 0),0),0)*100  -----------cel-386
							--,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED=ISNULL((ISNULL(A.GTS_SALES_PROJECTED, 0)-(ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0) + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(A.GTS_SALES_PROJECTED, 0),0)*100, -----------cel-386
							--ACCRUAL_DISCOUNT AS TOTAL_DISCOUNT_ACCRUAL
       --                     FROM   (SELECT PM.PROJECTION_MASTER_SID,
       --                                    Sum(INVENTORY_ACTUAL_SALES) AS  ACT_AMOUNT_WITHDRAWN,
       --                                    Sum(INVENTORY_ACTUAL_UNITS) AS ACT_UNITS_WITHDRAWN ,
       --                                    Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)) AS  FOR_AMOUNT_WITHDRAWN ,
       --                                     Sum(COALESCE(INVENTORY_FORECAST_UNITS, INVENTORY_ACTUAL_UNITS)) AS FOR_UNITS_WITHDRAWN ,
       --                                    Sum(DEMAND_ACTUAL_SALES) AS  DEMAND_SALES_ACTUAL ,
       --                                    Sum(DEMAND_ACTUAL_UNITS) AS ACT_GROSS_UNITS ,
       --                                    Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)) AS DEMAND_SALES_PROJECTED ,
       --                                   Sum(COALESCE(DEMAND_FORECAST_UNITS, DEMAND_ACTUAL_UNITS)) AS FOR_GROSS_UNITS ,
       --                                     Sum(EXFACTORY_ACTUAL_SALES) AS GTS_SALES_ACTUALS ,
       --                                     Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) AS GTS_SALES_PROJECTED,
       --                                  Sum(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS)) AS UNITS ,
       --                                    P.[QUARTER] QUARTERLY,
       --                                    P.[YEAR]    YEARLY
       --                             FROM   #PROJECTION_MASTER PM
							--			JOIN PERIOD P
       --                                      ON P.PERIOD_SID  BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
							--				 AND PM.ID <> 1
							--			LEFT JOIN  (SELECT * FROM #PRODUCT_FILE_TEMP  PF WHERE  EXISTS (SELECT 1 FROM  #TEMP_CCP A WHERE A.ITEM_MASTER_SID=PF.ITEM_MASTER_SID))PF
							--				 ON PM.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID	 	
							--				 AND PF.PERIOD_SID=P.PERIOD_SID
							--			GROUP  BY PM.PROJECTION_MASTER_SID,
       --                                       P.[YEAR],
       --                                       P.[QUARTER]) a
       --                            LEFT JOIN (SELECT  Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS ,
       --                                              Sum(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED ,
       --                                               Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
       --                                              Sum(PROJECTION_UNITS) AS  CONTRACT_UNITS_PROJECTED,
       --                                             Sum(COGS_ACTUAL) AS  COGS_ACTUALS ,
       --                                             Sum(COGS_PROJECTED) AS  COGS_PROJECTED,
       --                                             COALESCE(act.PROJECTION_MASTER_SID, proj.PROJECTION_MASTER_SID) AS  PROJECTION_MASTER_SID,
       --                                              [QUARTER],
       --                                              [YEAR]
       --                                       --, ITEM_MASTER_SID = COALESCE(ACT.ITEM_MASTER_SID, PROJ.ITEM_MASTER_SID)
       --                                       FROM   (SELECT PD.PROJECTION_MASTER_SID,
       --                                                      NAS.PERIOD_SID,
       --                                                       IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),ACTUAL_SALES,NULL)  ACTUAL_SALES,
       --                                                       IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),ACTUAL_UNITS,NULL) * ISNULL(UOM_VALUE,0) ACTUAL_UNITS,
       --                                                      CCP.ITEM_MASTER_SID,
       --                                                     (ISNULL(IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NAS.ACTUAL_UNITS,NULL), 0) * Isnull(U.ITEM_PRICE, 0) ) * ISNULL(UOM_VALUE,0) AS  COGS_ACTUAL 
							--								,NAS.PROJECTION_DETAILS_SID
       --                                               FROM   NM_ACTUAL_SALES NAS
       --                                                      INNER JOIN PROJECTION_DETAILS PD
       --                                                              ON NAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
       --                                                      INNER JOIN CCP_DETAILS CCP
       --                                                              ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID)
							--								INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                      INNER JOIN #ITEM_PRICING U
       --                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
       --                                                                 AND NAS.PERIOD_SID = U.PERIOD_SID
							--											LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																						
       --                                               WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                   FROM   #PROJECTION_MASTER PM
       --                                                                                   WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)) ACT
       --                                              FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
       --                                                                NSP.PERIOD_SID,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),PROJECTION_SALES,NULL)  PROJECTION_SALES,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),PROJECTION_UNITS,NULL)  * ISNULL(UOM_VALUE,0) PROJECTION_UNITS,
       --                                                                CCP.ITEM_MASTER_SID,
       --                                                                (  Isnull(IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION = NULL),NSP.PROJECTION_UNITS,NULL)  , 0)  * Isnull(U.ITEM_PRICE, 0) ) * ISNULL(UOM_VALUE,0) AS COGS_PROJECTED 
							--										   ,NSP.PROJECTION_DETAILS_SID
       --                                                         FROM   NM_SALES_PROJECTION NSP
       --                                                                INNER JOIN PROJECTION_DETAILS PD
       --                                                                        ON NSP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
							--										   INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                                INNER JOIN CCP_DETAILS CCP
       --                                                                        ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
       --                                                                AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID)
       --                                                                INNER JOIN #ITEM_PRICING U
       --                                                                        ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
       --                                                                           AND NSP.PERIOD_SID = U.PERIOD_SID
							--													  LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																						
       --                                                         WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                             FROM   #PROJECTION_MASTER PM
       --                                                                                             WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
       --                                                                AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
       --                                                     ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
							--								AND ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
       --                                                        AND ACT.PERIOD_SID = PROJ.PERIOD_SID
       --                                              INNER JOIN PERIOD P
       --                                                      ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
       --                                       GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID),
       --                                                 [QUARTER],
       --                                                 [YEAR]) sales
       --                                   ON SALES.[QUARTER] = a.QUARTERLY
       --                                      AND SALES.[YEAR] = a.YEARLY
       --                                      AND sales.PROJECTION_MASTER_SID = a.PROJECTION_MASTER_SID
       --                            LEFT JOIN (SELECT  SUM(ACTUAL_SALES) AS  CONTRACT_DISCOUNT_ACTUALS,
       --                                               SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
							--						  COALESCE(SUM(ACCRUAL_DISCOUNT_PROJ),SUM(ACCRUAL_DISCOUNT_ACTUAL)) ACCRUAL_DISCOUNT,
       --                                              COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID) AS PROJECTION_MASTER_SID,
       --                                              [QUARTER],
       --                                              [YEAR]
       --                                       FROM   (SELECT PD.PROJECTION_MASTER_SID,
       --                                                      NAD.PERIOD_SID,
       --                                                      IIF((DEDUCTION_INCLUSION=@DEDUCTION_INCLUSION OR  @DEDUCTION_INCLUSION  IS NULL),ACTUAL_SALES,NULL) ACTUAL_SALES,
       --                                                      NAD.RS_CONTRACT_SID,
							--								 NAD.PROJECTION_DETAILS_SID,DISCOUNT_AMOUNT ACCRUAL_DISCOUNT_ACTUAL
       --                                               FROM   NM_ACTUAL_DISCOUNT NAD
       --                                                      INNER JOIN PROJECTION_DETAILS PD
       --                                                              ON NAD.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = PD.CCP_DETAILS_SID)
							--								INNER JOIN #DEDUCTION_INCLUSION DI ON DI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
							--								AND DI.RS_CONTRACT_SID=NAD.RS_CONTRACT_SID
							--								LEFT JOIN  #PRIOR_ACCRUAL_DISCOUNT AD ON AD.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
							--							  AND AD.PROJECTION_DETAILS_SID=NAD.PROJECTION_DETAILS_SID
							--							   AND AD.RS_CONTRACT_SID=NAD.RS_CONTRACT_SID
							--							   AND AD.PERIOD_SID=NAD.PERIOD_SID
       --                                               WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                   FROM   #PROJECTION_MASTER PM
       --                                                                                   WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
							--								 AND EXISTS(SELECT 1 FROM #RS_DATA RS WHERE RS.RS_CONTRACT_SID=NAD.RS_CONTRACT_SID )) ACT
       --                                              FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
       --                                                                NDP.PERIOD_SID,
       --                                                                IIF((DEDUCTION_INCLUSION=@DEDUCTION_INCLUSION OR  @DEDUCTION_INCLUSION IS NULL),PROJECTION_SALES,NULL) PROJECTION_SALES,
       --                                                                NDP.RS_CONTRACT_SID,
							--										   NDP.PROJECTION_DETAILS_SID,DISCOUNT_AMOUNT ACCRUAL_DISCOUNT_PROJ
       --                                                         FROM   NM_DISCOUNT_PROJECTION NDP
       --                                                                INNER JOIN PROJECTION_DETAILS PD
       --                                                                        ON NDP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
       --                                                                AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
							--								INNER JOIN #DEDUCTION_INCLUSION DI ON DI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
							--								AND DI.RS_CONTRACT_SID=NDP.RS_CONTRACT_SID
							--										 LEFT JOIN  #PRIOR_ACCRUAL_DISCOUNT AD ON AD.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
							--							  AND AD.PROJECTION_DETAILS_SID=NDP.PROJECTION_DETAILS_SID
							--							   AND AD.RS_CONTRACT_SID=NDP.RS_CONTRACT_SID
							--							   AND AD.PERIOD_SID=NDP.PERIOD_SID
       --                                                         WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                             FROM   #PROJECTION_MASTER PM
       --                                                                                             WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
							--										   AND EXISTS(SELECT 1 FROM #RS_DATA RS WHERE RS.RS_CONTRACT_SID=NDP.RS_CONTRACT_SID )
       --                                                                AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
       --                                                     ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
							--								AND ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
       --                                                        AND ACT.PERIOD_SID = PROJ.PERIOD_SID
       --                                                        AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
       --                                              INNER JOIN PERIOD P
       --                                                      ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
       --                                       GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID),
       --                                                 QUARTER,
       --                                                 YEAR) DISC
       --                                   ON COALESCE(A.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) = DISC.PROJECTION_MASTER_SID
       --                                      AND DISC.[QUARTER] = COALESCE(A.QUARTERLY, SALES.[QUARTER])
       --                                      AND DISC.[YEAR] = COALESCE(A.YEARLY, SALES.[YEAR])
       --                            LEFT JOIN (SELECT Sum(ACTUAL_PPA_SALES) AS  PPA_DISCOUNT_ACTUALS ,
       --                                               Sum (ACTUAL_PPA_RPU) AS PPA_RPU_ACTUALS,
       --                                               Sum(PROJECTION_PPA_SALES) AS PPA_DISCOUNT_PROJECTED ,
       --                                               Sum(PPA_RPU) AS PPA_RPU_PROJECTED ,
       --                                              Sum(ACTUAL_SALES) AS  PPA_ACTUAL_SALES ,
       --                                               Sum(ACTUAL_UNITS) AS PPA_ACTUAL_UNITS,
       --                                               Sum(PROJECTION_SALES) AS PPA_PROJECTION_SALES,
       --                                               Sum(PROJECTION_UNITS) AS PPA_PROJECTION_UNITS ,
       --                                               COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID) AS PROJECTION_MASTER_SID,
       --                                              [QUARTER],
       --                                              [YEAR]
       --                                       FROM   (SELECT PD.PROJECTION_MASTER_SID,
       --                                                      NAP.PERIOD_SID,
       --                                                      ACTUAL_DISCOUNT_DOLLAR AS ACTUAL_PPA_SALES ,
       --                                                      ACTUAL_DISCOUNT_DOLLAR AS ACTUAL_PPA_RPU ,
       --                                                      RS_CONTRACT_SID,
       --                                                       IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.ACTUAL_SALES,NULL)  AS ACTUAL_SALES,
       --                                                       IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.ACTUAL_UNITS,NULL)  * ISNULL(UOM_VALUE,0) AS ACTUAL_UNITS
							--								 ,NAP.PROJECTION_DETAILS_SID
       --                                               FROM   NM_ACTUAL_PPA NAP
       --                                                      INNER JOIN NM_ACTUAL_SALES NS
       --                                                              ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
       --                                                                 AND NS.PERIOD_SID = NAP.PERIOD_SID
       --                                                      INNER JOIN PROJECTION_DETAILS PD
       --                                                              ON NS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
							--										  INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
							--										  INNER JOIN CCP_DETAILS CCP
       --                                                                         ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
							--								 LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																						
       --                                               WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                   FROM   #PROJECTION_MASTER PM
       --                                                                                   WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
							--								AND EXISTS(SELECT 1 FROM #PRIOR_RS_PPA RS WHERE RS.RS_CONTRACT_SID=NAP.RS_CONTRACT_SID )) ACT
       --                                              FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
       --                                                                NPP.PERIOD_SID,
       --                                                                 PROJECTION_DISCOUNT_DOLLAR AS PPA_RPU ,
       --                                                                 PROJECTION_DISCOUNT_DOLLAR AS PROJECTION_PPA_SALES ,
       --                                                                RS_CONTRACT_SID,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.PROJECTION_SALES,NULL)   AS PROJECTION_SALES ,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.PROJECTION_UNITS,NULL)  * ISNULL(UOM_VALUE,0) AS PROJECTION_UNITS,
							--										   NPP.PROJECTION_DETAILS_SID
       --                                                         FROM   NM_PPA_PROJECTION NPP
       --                                                                INNER JOIN NM_SALES_PROJECTION NS
       --                                                                        ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
       --                                                                           AND NS.PERIOD_SID = NPP.PERIOD_SID
       --                                                                INNER JOIN PROJECTION_DETAILS PD
       --                                                                        ON NS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
							--												   INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                                AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
							--										  INNER JOIN CCP_DETAILS CCP
       --                                                                         ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
							--								 LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																							
       --                                                         WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                             FROM   #PROJECTION_MASTER PM
       --                                                                                             WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
       --                                                                AND EXISTS(SELECT 1 FROM #PRIOR_RS_PPA RS WHERE RS.RS_CONTRACT_SID=NPP.RS_CONTRACT_SID )
							--										   AND NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
       --                                                     ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
							--									AND ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
       --                                                        AND ACT.PERIOD_SID = PROJ.PERIOD_SID
       --                                                        AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
       --                                              INNER JOIN PERIOD P
       --                                                      ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
       --                                       GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID),
       --                                                 QUARTER,
       --                                                 YEAR) PPA
       --                                   ON PPA.QUARTER = SALES.QUARTER
       --                                      AND PPA.YEAR = SALES.YEAR
       --                                      AND COALESCE(A.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) = PPA.PROJECTION_MASTER_SID
       --                     ORDER  BY SALES.QUARTER,
       --                               sales.YEAR
       --                 END
       --               ELSE IF @PROJ_FREQUENCY = 'SEMI-ANNUAL'-------------------------------it will display the semi_annual wise data  -------------------------
       --                 BEGIN
       --                     INSERT INTO #PIVOT_RESULT
       --                                 (PROJECTION_ID,
       --                                  PERIOD,
       --                                  [YEAR],
       --                                  EX_FACTORY_SALES_ACTUALS,
       --                                  EX_FACTORY_SALES_PROJECTED,
       --                                  DEMAND_SALES_ACTUALS,
       --                                  DEMAND_SALES_PROJECTED,
       --                                  INVENTORY_WITHDRAWAL_SALES_ACTUALS,
       --                                  INVENTORY_WITHDRAWAL_SALES_PROJECTED,
       --                                  EX_FACTORY_SALES_ACTUALS_PERCENT,
       --                                  EX_FACTORY_SALES_PROJECTED_PERCENT,
       --                                  DEMAND_SALES_ACTUALS_PERCENT,
       --                                  DEMAND_SALES_PROJECTED_PERCENT,
       --                                  INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
       --                                  INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
       --                                  CONTRACT_SALES_ACTUALS,
       --                                  CONTRACT_SALES_PROJECTED,
       --                                  CONTRACT_UNITS_ACTUALS,
       --                                  CONTRACT_UNITS_PROJECTED,
       --                                  TOTAL_DISCOUNT,
       --                                  TOTAL_DISCOUNT_PROJECTED,
       --                                  TOTAL_DISCOUNT_PERCENTAGE,
       --                                  TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
       --                                  PPA_DISCOUNT_ACTUALS,
       --                                  PPA_DISCOUNT_PROJECTED,
       --                                  PPA_DISCOUNT_ACTUALS_PERCENTAGE,
       --                                  PPA_DISCOUNT_PROJECTED_PERCENTAGE,
       --                                  TOTAL_DISCOUNT_RPU_ACTUALS,
       --                                  TOTAL_DISCOUNT_RPU_PROJECTED,
       --                                  TOTAL_PPA_RPU_ACTUALS,
       --                                  TOTAL_PPA_RPU_PROJECTED,
       --                                  TOTAL_RPU_ACTUAL,
       --                                  TOTAL_RPU_PROJECTED,
       --                                  NET_SALES,
       --                                  NET_SALES_PROJECTED,
       --                                  COGS_ACTUALS,
       --                                  COGS_PROJECTED,
       --                                  NET_PROFIT_ACTUAL,
       --                                  NET_PROFIT_PROJECTED,
       --                                  TOTAL_RETURNS_RPU_ACTUALS,
       --                                  TOTAL_RETURNS_RPU_PROJECTED,
       --                                  RETURNS_ACTUALS_AMOUNT,
       --                                  RETURNS_PROJECTED_AMOUNT,
       --                                  RETURNS_ACTUALS_PERCENTAGE,
       --                                  RETURNS_PROJECTED_PERCENTAGE,
       --                                  NET_SALES_OF_EX_FACTORY_ACTUALS,
       --                                  NET_SALES_OF_EX_FACTORY_PROJECTED,
       --                                  DISCOUNT_OF_EX_FACTORY_ACTUALS,
       --                                  DISCOUNT_OF_EX_FACTORY_PROJECTED
							--			,NET_EX_FACTORY_SALES_ACTUALS---------------CEL-386
							--			,NET_EX_FACTORY_SALES_PROJECTED---------------CEL-386
							--			,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS 	---------------CEL-386				  
							--			,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED,---------------CEL-386
							--			 TOTAL_DISCOUNT_ACCRUAL
							--			 )
       --                     SELECT COALESCE(a.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
       --                            COALESCE(a.SEMI_ANNUAL, SALES.SEMI_ANNUAL),
       --                            COALESCE(a.YEARLY, SALES.[YEAR]),
       --                             Isnull(A.GTS_SALES_ACTUALS, 0) as EX_FACTORY_SALES_ACTUALS ,
       --                             Isnull(A.GTS_SALES_PROJECTED, 0)AS EX_FACTORY_SALES_PROJECTED ,
       --                             Isnull(A.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS ,
       --                             Isnull(A.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED ,
       --                             Isnull(A.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_ACTUALS ,
       --                             Isnull(A.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED ,
       --                            (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUAL_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS  INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
       --                            (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT ,
       --                            Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS ,
       --                            Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED ,
       --                            Isnull(CONTRACT_UNITS_ACTUALS, 0) AS  CONTRACT_UNITS_ACTUALS ,
       --                            Isnull(CONTRACT_UNITS_PROJECTED, 0) AS  CONTRACT_UNITS_PROJECTED ,
       --                             Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                             + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS TOTAL_DISCOUNT ,
       --                             Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                       + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED ,
       --                             ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                   + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PERCENTAGE ,
       --                           ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                             + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 as TOTAL_DISCOUNT_PROJECTED_PERCENTAGE ,
       --                             Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS PPA_DISCOUNT_ACTUALS ,
       --                             Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS PPA_DISCOUNT_PROJECTED ,
       --                             Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100 AS PPA_DISCOUNT_ACTUALS_PERCENTAGE ,
       --                             Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100 AS PPA_DISCOUNT_PROJECTED_PERCENTAGE ,
       --                             ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_DISCOUNT_RPU_ACTUALS ,
       --                             ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_DISCOUNT_RPU_PROJECTED ,
       --                            ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS  TOTAL_PPA_RPU_ACTUALS ,
       --                             ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_PPA_RPU_PROJECTED ,
       --                            ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                          + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUAL ,
       --                             ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                             + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED ,
       --                            ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                                      + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) AS NET_SALES ,
       --                             Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                                                + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) AS NET_SALES_PROJECTED , 
       --                             Isnull(SALES.COGS_ACTUALS, 0) AS COGS_ACTUALS ,
       --                             Isnull(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED ,
       --                            ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                                                + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL ,
       --                             ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                                                     + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED ,
       --                             000000.0 AS TOTAL_RETURNS_RPU_ACTUALS,
       --                             000000.0 AS TOTAL_RETURNS_RPU_PROJECTED,
       --                             000000.0 AS RETURNS_ACTUALS_AMOUNT,
       --                             000000.0 AS RETURNS_PROJECTED_AMOUNT,
       --                             000000.0 AS RETURNS_ACTUALS_PERCENTAGE,
       --                             000000.0 AS RETURNS_PROJECTED_PERCENTAGE,
       --                            ( COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                                                                        + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) )) / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) * 100 ) AS NET_SALES_OF_EX_FACTORY_ACTUALS ,
       --                             ( COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                                                                            + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) )) / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) * 100 ) AS NET_SALES_OF_EX_FACTORY_PROJECTED ,
       --                             COALESCE((( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                         + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) )) / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) * 100 AS DISCOUNT_OF_EX_FACTORY_ACTUALS ,
       --                           COALESCE((( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                           + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) )) / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) * 100 AS  DISCOUNT_OF_EX_FACTORY_PROJECTED
       --                     ,NET_EX_FACTORY_SALES_ACTUALS=((ISNULL(A.GTS_SALES_ACTUALS, 0))-( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)+ ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0))) -----------cel-386
							--,NET_EX_FACTORY_SALES_PROJECTED =ISNULL(A.GTS_SALES_PROJECTED, 0)-(ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0) + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0))-----------cel-386
							--,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS=ISNULL((( ISNULL(A.GTS_SALES_ACTUALS, 0) )-( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)+ ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0)  ))/NULLIF( ISNULL(A.GTS_SALES_ACTUALS, 0),0),0)*100  -----------cel-386
							--,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED=ISNULL((ISNULL(A.GTS_SALES_PROJECTED, 0)-(ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0) + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(A.GTS_SALES_PROJECTED, 0),0)*100, -----------cel-386
							-- ACCRUAL_DISCOUNT AS TOTAL_DISCOUNT_ACCRUAL
       --                     FROM   (SELECT PM.PROJECTION_MASTER_SID,
       --                                     Sum(INVENTORY_ACTUAL_SALES) AS ACT_AMOUNT_WITHDRAWN,
       --                                     Sum(INVENTORY_ACTUAL_UNITS) AS ACT_UNITS_WITHDRAWN ,
       --                                     Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)) AS FOR_AMOUNT_WITHDRAWN ,
       --                                     Sum(COALESCE(INVENTORY_FORECAST_UNITS, INVENTORY_ACTUAL_UNITS)) AS FOR_UNITS_WITHDRAWN ,
       --                                     Sum(DEMAND_ACTUAL_SALES) AS DEMAND_SALES_ACTUAL ,
       --                                     Sum(DEMAND_ACTUAL_UNITS) AS  ACT_GROSS_UNITS ,
       --                                     Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)) AS DEMAND_SALES_PROJECTED ,
       --                                     Sum(COALESCE(DEMAND_FORECAST_UNITS, DEMAND_ACTUAL_UNITS)) AS FOR_GROSS_UNITS ,
       --                                     Sum(EXFACTORY_ACTUAL_SALES) AS GTS_SALES_ACTUALS ,
       --                                     Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) AS GTS_SALES_PROJECTED ,
       --                                     Sum(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS)) AS UNITS ,
       --                                    P.SEMI_ANNUAL,
       --                                    P.[YEAR] YEARLY
       --                             FROM   #PROJECTION_MASTER PM
							--			JOIN PERIOD P
       --                                      ON P.PERIOD_SID  BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
							--				 AND PM.ID <> 1
							--			LEFT JOIN  (SELECT * FROM #PRODUCT_FILE_TEMP  PF WHERE  EXISTS (SELECT 1 FROM  #TEMP_CCP A WHERE A.ITEM_MASTER_SID=PF.ITEM_MASTER_SID)) PF
							--				 ON PM.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID		
							--				 AND PF.PERIOD_SID=P.PERIOD_SID 
							--			GROUP  BY PM.PROJECTION_MASTER_SID,
       --                                       P.[YEAR],
       --                                       P.SEMI_ANNUAL) a
       --                            LEFT JOIN (SELECT COALESCE(act.PROJECTION_MASTER_SID, proj.PROJECTION_MASTER_SID) AS PROJECTION_MASTER_SID,
       --                                               Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS ,
       --                                               Sum(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED ,
       --                                              Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS ,
       --                                              Sum(PROJECTION_UNITS) AS  CONTRACT_UNITS_PROJECTED ,
       --                                               Sum(COGS_ACTUAL) AS COGS_ACTUALS ,
       --                                               Sum(COGS_PROJECTED) AS COGS_PROJECTED ,
       --                                              SEMI_ANNUAL,
       --                                              [YEAR]
       --                                       --, ITEM_MASTER_SID = COALESCE(ACT.ITEM_MASTER_SID, PROJ.ITEM_MASTER_SID)
       --                                       FROM   (SELECT PD.PROJECTION_MASTER_SID,
       --                                                      NAS.PERIOD_SID,
       --                                                       IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),ACTUAL_SALES,NULL)  ACTUAL_SALES,
       --                                                       IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),ACTUAL_UNITS,NULL) * ISNULL(UOM_VALUE,0) ACTUAL_UNITS,
       --                                                       CCP.ITEM_MASTER_SID,
       --                                                        (ISNULL(IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NAS.ACTUAL_UNITS,NULL), 0) * ISNULL(U.ITEM_PRICE, 0) )  * ISNULL(UOM_VALUE,0) AS COGS_ACTUAL,
							--								 NAS.PROJECTION_DETAILS_SID
       --                                               FROM   NM_ACTUAL_SALES NAS
       --                                                      INNER JOIN PROJECTION_DETAILS PD
       --                                                              ON NAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
       --                                                      INNER JOIN CCP_DETAILS CCP
       --                                                              ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
							--										 INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
       --                                                      INNER JOIN #ITEM_PRICING U
       --                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
       --                                                                 AND NAS.PERIOD_SID = U.PERIOD_SID
							--											LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																						
       --                                               WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                   FROM   #PROJECTION_MASTER PM
       --                                                                                   WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)) ACT
       --                                              FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
       --                                                                NSP.PERIOD_SID,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),PROJECTION_SALES,NULL)  PROJECTION_SALES,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),PROJECTION_UNITS,NULL)  * ISNULL(UOM_VALUE,0) PROJECTION_UNITS,
       --                                                                 CCP.ITEM_MASTER_SID,
       --                                                                 ( Isnull(IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NSP.PROJECTION_UNITS,NULL)  , 0) * Isnull(U.ITEM_PRICE, 0) )  * ISNULL(UOM_VALUE,0) AS COGS_PROJECTED,
							--										  NSP.PROJECTION_DETAILS_SID
       --                                                         FROM   NM_SALES_PROJECTION NSP
       --                                                                INNER JOIN PROJECTION_DETAILS PD
       --                                                                        ON NSP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
       --                                                                INNER JOIN CCP_DETAILS CCP
       --                                                                        ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
       --                                                                AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
							--										  INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                                INNER JOIN #ITEM_PRICING U
       --                                                                        ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
       --                                                                           AND NSP.PERIOD_SID = U.PERIOD_SID
							--													  LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																							
       --                                                         WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                             FROM   #PROJECTION_MASTER PM
       --                                                                                             WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
       --                                                                AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
       --                                                     ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
							--								AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
       --                                                        AND ACT.PERIOD_SID = PROJ.PERIOD_SID
       --                                              INNER JOIN PERIOD P
       --                                                      ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
       --                                       GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID),
       --                                                 SEMI_ANNUAL,
       --                                                 [YEAR]) SALES
       --                                   ON SALES.SEMI_ANNUAL = A.SEMI_ANNUAL
       --                                      AND SALES.YEAR = A.YEARLY
       --                            LEFT JOIN (SELECT  Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS ,
       --                                               Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
							--						  COALESCE(SUM(ACCRUAL_DISCOUNT_PROJ),SUM(ACCRUAL_DISCOUNT_ACTUAL)) ACCRUAL_DISCOUNT,
       --                                              COALESCE(act.PROJECTION_MASTER_SID, proj.PROJECTION_MASTER_SID) AS PROJECTION_MASTER_SID,
       --                                              SEMI_ANNUAL,
       --                                              [YEAR]
       --                                       FROM   (SELECT PD.PROJECTION_MASTER_SID,
       --                                                      NAD.PERIOD_SID,
       --                                                      IIF((DEDUCTION_INCLUSION=@DEDUCTION_INCLUSION OR  @DEDUCTION_INCLUSION  IS NULL),ACTUAL_SALES,NULL) ACTUAL_SALES,DISCOUNT_AMOUNT ACCRUAL_DISCOUNT_ACTUAL,
       --                                                      nad.RS_CONTRACT_SID
							--								 ,NAD.PROJECTION_DETAILS_SID
       --                                               FROM   NM_ACTUAL_DISCOUNT NAD
       --                                                      INNER JOIN PROJECTION_DETAILS PD
       --                                                              ON NAD.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
							--										  INNER JOIN #DEDUCTION_INCLUSION DI ON DI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
							--										  AND DI.RS_CONTRACT_SID=NAD.RS_CONTRACT_SID
							--								LEFT JOIN  #PRIOR_ACCRUAL_DISCOUNT AD ON AD.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
							--							  AND AD.PROJECTION_DETAILS_SID=NAD.PROJECTION_DETAILS_SID
							--							   AND AD.RS_CONTRACT_SID=NAD.RS_CONTRACT_SID
							--							   AND AD.PERIOD_SID=NAD.PERIOD_SID
       --                                               WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                   FROM   #PROJECTION_MASTER PM
       --                                                                                   WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
							--								AND EXISTS(SELECT 1 FROM #RS_DATA RS WHERE RS.RS_CONTRACT_SID=NAD.RS_CONTRACT_SID )
							--															  ) ACT
       --                                              FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
       --                                                                NDP.PERIOD_SID,
       --                                                                IIF((DEDUCTION_INCLUSION=@DEDUCTION_INCLUSION OR  @DEDUCTION_INCLUSION  IS NULL),PROJECTION_SALES,NULL) PROJECTION_SALES,DISCOUNT_AMOUNT ACCRUAL_DISCOUNT_PROJ,
       --                                                                ndp.RS_CONTRACT_SID,
							--										   NDP.PROJECTION_DETAILS_SID
       --                                                         FROM   NM_DISCOUNT_PROJECTION NDP
       --                                                                INNER JOIN PROJECTION_DETAILS PD
       --                                                                        ON NDP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
       --                                                                AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
							--										  INNER JOIN #DEDUCTION_INCLUSION DI ON DI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
							--										  AND DI.RS_CONTRACT_SID=NDP.RS_CONTRACT_SID
							--										  LEFT JOIN  #PRIOR_ACCRUAL_DISCOUNT AD ON AD.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
							--							  AND AD.PROJECTION_DETAILS_SID=NDP.PROJECTION_DETAILS_SID
							--							   AND AD.RS_CONTRACT_SID=NDP.RS_CONTRACT_SID
							--							   AND AD.PERIOD_SID=NDP.PERIOD_SID
       --                                                         WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                             FROM   #PROJECTION_MASTER PM
       --                                                                                             WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
       --                                                                AND EXISTS(SELECT 1 FROM #RS_DATA RS WHERE RS.RS_CONTRACT_SID=NDP.RS_CONTRACT_SID )
							--										   AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
       --                                                     ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
							--								AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
       --                                                        AND ACT.PERIOD_SID = PROJ.PERIOD_SID
       --                                                        AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
       --                                              INNER JOIN PERIOD P
       --                                                      ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
       --                                       GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID),
       --                                                 SEMI_ANNUAL,
       --                                                 YEAR) DISC
       --                                   ON DISC.SEMI_ANNUAL = COALESCE(A.SEMI_ANNUAL, SALES.SEMI_ANNUAL)
       --                                      AND DISC.YEAR = COALESCE(A.YEARLY, SALES.YEAR)
       --                                      AND DISC.PROJECTION_MASTER_SID = COALESCE(A.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID)
       --                            LEFT JOIN (SELECT  Sum(ACTUAL_PPA_SALES) AS PPA_DISCOUNT_ACTUALS ,
       --                                               Sum(ACTUAL_PPA_RPU) AS PPA_RPU_ACTUALS ,
       --                                              Sum(PROJECTION_PPA_SALES) AS PPA_DISCOUNT_PROJECTED ,
       --                                               Sum(PPA_RPU) AS PPA_RPU_PROJECTED ,
       --                                              Sum(ACTUAL_SALES) AS  PPA_ACTUAL_SALES ,
       --                                               Sum(ACTUAL_UNITS) AS PPA_ACTUAL_UNITS ,
       --                                              Sum(PROJECTION_SALES) AS  PPA_PROJECTION_SALES ,
       --                                               Sum(PROJECTION_UNITS) AS PPA_PROJECTION_UNITS ,
       --                                               COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID) AS PROJECTION_MASTER_SID ,
       --                                              SEMI_ANNUAL,
       --                                              [YEAR]
       --                                       FROM   (SELECT PD.PROJECTION_MASTER_SID,
       --                                                      NAP.PERIOD_SID,
       --                                                       ACTUAL_DISCOUNT_DOLLAR AS ACTUAL_PPA_SALES ,
       --                                                       ACTUAL_DISCOUNT_DOLLAR AS ACTUAL_PPA_RPU ,
       --                                                      RS_CONTRACT_SID,
       --                                                       IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.ACTUAL_SALES,NULL)  AS ACTUAL_SALES,
       --                                                       IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.ACTUAL_UNITS,NULL)  * ISNULL(UOM_VALUE,0) AS ACTUAL_UNITS,NAP.PROJECTION_DETAILS_SID
       --                                               FROM   NM_ACTUAL_PPA NAP
       --                                                      INNER JOIN NM_ACTUAL_SALES NS
       --                                                              ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
       --                                                                 AND NS.PERIOD_SID = NAP.PERIOD_SID
       --                                                      INNER JOIN PROJECTION_DETAILS PD
       --                                                              ON NS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
							--										 INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
							--										  INNER JOIN CCP_DETAILS CCP
       --                                                                         ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
							--								 LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																						
       --                                               WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                   FROM   #PROJECTION_MASTER PM
       --                                                                                   WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
							--								AND EXISTS(SELECT 1 FROM #PRIOR_RS_PPA RS WHERE RS.RS_CONTRACT_SID=NAP.RS_CONTRACT_SID )) ACT
       --                                              FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
       --                                                                NPP.PERIOD_SID,
       --                                                                 PROJECTION_DISCOUNT_DOLLAR AS PPA_RPU ,
       --                                                                PROJECTION_DISCOUNT_DOLLAR AS PROJECTION_PPA_SALES,
       --                                                                RS_CONTRACT_SID,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.PROJECTION_SALES,NULL)   AS PROJECTION_SALES ,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.PROJECTION_UNITS,NULL)  * ISNULL(UOM_VALUE,0) AS PROJECTION_UNITS,
							--											NPP.PROJECTION_DETAILS_SID
       --                                                         FROM   NM_PPA_PROJECTION NPP
       --                                                                INNER JOIN NM_SALES_PROJECTION NS
       --                                                                        ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
       --                                                                           AND NS.PERIOD_SID = NPP.PERIOD_SID
       --                                                                INNER JOIN PROJECTION_DETAILS PD
       --                                                                        ON NS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
							--												   INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                                AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
							--										  INNER JOIN CCP_DETAILS CCP
       --                                                                         ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
							--								 LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																						
       --                                                         WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                             FROM   #PROJECTION_MASTER PM
       --                                                                                             WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
       --                                                                AND EXISTS(SELECT 1 FROM #PRIOR_RS_PPA RS WHERE RS.RS_CONTRACT_SID=NPP.RS_CONTRACT_SID )
							--										   AND NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
       --                                                     ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
							--								AND ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
       --                                                        AND ACT.PERIOD_SID = PROJ.PERIOD_SID
       --                                                        AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
       --                                              INNER JOIN PERIOD P
       --                                                      ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
       --                                       GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID),
       --                                                 SEMI_ANNUAL,
       --                                                 YEAR) PPA
       --                                   ON PPA.SEMI_ANNUAL = SALES.SEMI_ANNUAL
       --                                      AND PPA.YEAR = SALES.YEAR
       --                                      AND COALESCE(A.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) = PPA.PROJECTION_MASTER_SID
       --                     ORDER  BY SALES.SEMI_ANNUAL,
       --                               sales.YEAR
       --                 END
       --               ELSE--------------------------------------------it will display the yearly wise data  -------------------------
       --                 BEGIN
       --                     INSERT INTO #PIVOT_RESULT
       --                                 (PROJECTION_ID,
       --                                  [YEAR],
							--			 PERIOD,
       --                                  EX_FACTORY_SALES_ACTUALS,
       --                                  EX_FACTORY_SALES_PROJECTED,
       --                                  DEMAND_SALES_ACTUALS,
       --                                  DEMAND_SALES_PROJECTED,
       --                                  INVENTORY_WITHDRAWAL_SALES_ACTUALS,
       --                                  INVENTORY_WITHDRAWAL_SALES_PROJECTED,
       --                                  EX_FACTORY_SALES_ACTUALS_PERCENT,
       --                                  EX_FACTORY_SALES_PROJECTED_PERCENT,
       --                                  DEMAND_SALES_ACTUALS_PERCENT,
       --                                  DEMAND_SALES_PROJECTED_PERCENT,
       --                                  INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
       --                                  INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
       --                                  CONTRACT_SALES_ACTUALS,
       --                                  CONTRACT_SALES_PROJECTED,
       --                                  CONTRACT_UNITS_ACTUALS,
       --                                  CONTRACT_UNITS_PROJECTED,
       --                                  TOTAL_DISCOUNT,
       --                                  TOTAL_DISCOUNT_PROJECTED,
       --                                  TOTAL_DISCOUNT_PERCENTAGE,
       --                                  TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
       --                                  PPA_DISCOUNT_ACTUALS,
       --                                  PPA_DISCOUNT_PROJECTED,
       --                                  PPA_DISCOUNT_ACTUALS_PERCENTAGE,
       --                                  PPA_DISCOUNT_PROJECTED_PERCENTAGE,
       --                                  TOTAL_DISCOUNT_RPU_ACTUALS,
       --                                  TOTAL_DISCOUNT_RPU_PROJECTED,
       --                                  TOTAL_PPA_RPU_ACTUALS,
       --                                  TOTAL_PPA_RPU_PROJECTED,
       --                                  TOTAL_RPU_ACTUAL,
       --                                  TOTAL_RPU_PROJECTED,
       --                                  NET_SALES,
       --                                  NET_SALES_PROJECTED,
       --                                  COGS_ACTUALS,
       --                                  COGS_PROJECTED,
       --                                  NET_PROFIT_ACTUAL,
       --                                  NET_PROFIT_PROJECTED,
       --                                  TOTAL_RETURNS_RPU_ACTUALS,
       --                                  TOTAL_RETURNS_RPU_PROJECTED,
       --                                  RETURNS_ACTUALS_AMOUNT,
       --                                  RETURNS_PROJECTED_AMOUNT,
       --                                  RETURNS_ACTUALS_PERCENTAGE,
       --                                  RETURNS_PROJECTED_PERCENTAGE,
       --                                  NET_SALES_OF_EX_FACTORY_ACTUALS,
       --                                  NET_SALES_OF_EX_FACTORY_PROJECTED,
       --                                  DISCOUNT_OF_EX_FACTORY_ACTUALS,
       --                                  DISCOUNT_OF_EX_FACTORY_PROJECTED
							--			,NET_EX_FACTORY_SALES_ACTUALS---------------CEL-386
							--			,NET_EX_FACTORY_SALES_PROJECTED---------------CEL-386
							--			,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS 	---------------CEL-386				  
							--			,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED,---------------CEL-386
							--			 TOTAL_DISCOUNT_ACCRUAL
							--			 )
       --                     SELECT COALESCE(a.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
       --                            COALESCE(A.[YEARLY], SALES.[YEAR]),
							--	   1 AS PERIOD,
       --                              Isnull(A.GTS_SALES_ACTUALS, 0) as EX_FACTORY_SALES_ACTUALS ,
       --                             Isnull(A.GTS_SALES_PROJECTED, 0)AS EX_FACTORY_SALES_PROJECTED ,
       --                             Isnull(A.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS ,
       --                             Isnull(A.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED ,
       --                             Isnull(A.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_ACTUALS ,
       --                             Isnull(A.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED ,
       --                            (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUAL_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT ,
       --                            (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(A.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS  INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
       --                            (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(A.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT ,
       --                            Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS ,
       --                            Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED ,
       --                            Isnull(CONTRACT_UNITS_ACTUALS, 0) AS  CONTRACT_UNITS_ACTUALS ,
       --                            Isnull(CONTRACT_UNITS_PROJECTED, 0) AS  CONTRACT_UNITS_PROJECTED ,
       --                             Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                             + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS TOTAL_DISCOUNT ,
       --                             Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                       + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED ,
       --                             ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                   + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PERCENTAGE ,
       --                             ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                             + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 as TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
       --                             Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS PPA_DISCOUNT_ACTUALS ,
       --                             Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS PPA_DISCOUNT_PROJECTED ,
       --                             Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100 AS PPA_DISCOUNT_ACTUALS_PERCENTAGE ,
       --                             Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100 AS PPA_DISCOUNT_PROJECTED_PERCENTAGE ,
       --                             ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_DISCOUNT_RPU_ACTUALS ,
       --                             ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_DISCOUNT_RPU_PROJECTED ,
       --                            ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS  TOTAL_PPA_RPU_ACTUALS ,
       --                             ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_PPA_RPU_PROJECTED ,
       --                            ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                          + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUAL ,
       --                             ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                             + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED ,
       --                            ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                                      + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) AS NET_SALES ,
       --                             Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                                                + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) AS NET_SALES_PROJECTED , 
       --                             Isnull(SALES.COGS_ACTUALS, 0) AS COGS_ACTUALS ,
       --                             Isnull(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED ,
       --                            ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                                                + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL ,
       --                             ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                                                     + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED ,
       --                             000000.0 AS TOTAL_RETURNS_RPU_ACTUALS,
       --                             000000.0 AS TOTAL_RETURNS_RPU_PROJECTED,
       --                             000000.0 AS RETURNS_ACTUALS_AMOUNT,
       --                             000000.0 AS RETURNS_PROJECTED_AMOUNT,
       --                             000000.0 AS RETURNS_ACTUALS_PERCENTAGE,
       --                             000000.0 AS RETURNS_PROJECTED_PERCENTAGE,
       --                            ( COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                                                                        + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) )) / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) * 100 ) AS NET_SALES_OF_EX_FACTORY_ACTUALS ,
       --                             ( COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                                                                            + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) )) / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) * 100 ) AS NET_SALES_OF_EX_FACTORY_PROJECTED ,
       --                             COALESCE((( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
       --                                                                         + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) )) / NULLIF(A.GTS_SALES_ACTUALS, 0), 0) * 100 AS DISCOUNT_OF_EX_FACTORY_ACTUALS ,
       --                           COALESCE((( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
       --                                                                           + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) )) / NULLIF(A.GTS_SALES_PROJECTED, 0), 0) * 100 AS  DISCOUNT_OF_EX_FACTORY_PROJECTED
       --                     ,NET_EX_FACTORY_SALES_ACTUALS=((ISNULL(A.GTS_SALES_ACTUALS, 0))-( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)+ ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0))) -----------cel-386
							--,NET_EX_FACTORY_SALES_PROJECTED =ISNULL(A.GTS_SALES_PROJECTED, 0)-(ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0) + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0))-----------cel-386
							--,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS=ISNULL((( ISNULL(A.GTS_SALES_ACTUALS, 0) )-( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)+ ISNULL(PPA.PPA_DISCOUNT_ACTUALS, 0)  ))/NULLIF( ISNULL(A.GTS_SALES_ACTUALS, 0),0),0)*100  -----------cel-386
							--,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED=ISNULL((ISNULL(A.GTS_SALES_PROJECTED, 0)-(ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0) + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(A.GTS_SALES_PROJECTED, 0),0)*100, -----------cel-386
							-- ACCRUAL_DISCOUNT AS TOTAL_DISCOUNT_ACCRUAL
       --                     FROM   (SELECT PM.PROJECTION_MASTER_SID,
       --                                     Sum(INVENTORY_ACTUAL_SALES) AS ACT_AMOUNT_WITHDRAWN,
       --                                     Sum(INVENTORY_ACTUAL_UNITS) AS ACT_UNITS_WITHDRAWN ,
       --                                     Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)) AS FOR_AMOUNT_WITHDRAWN ,
       --                                     Sum(COALESCE(INVENTORY_FORECAST_UNITS, INVENTORY_ACTUAL_UNITS)) AS FOR_UNITS_WITHDRAWN ,
       --                                     Sum(DEMAND_ACTUAL_SALES) AS DEMAND_SALES_ACTUAL ,
       --                                     Sum(DEMAND_ACTUAL_UNITS) AS  ACT_GROSS_UNITS ,
       --                                     Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)) AS DEMAND_SALES_PROJECTED ,
       --                                     Sum(COALESCE(DEMAND_FORECAST_UNITS, DEMAND_ACTUAL_UNITS)) AS FOR_GROSS_UNITS ,
       --                                     Sum(EXFACTORY_ACTUAL_SALES) AS GTS_SALES_ACTUALS ,
       --                                     Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) AS GTS_SALES_PROJECTED ,
       --                                     Sum(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS)) AS UNITS ,
       --                                    P.[YEAR] YEARLY
       --                             FROM   #PROJECTION_MASTER PM
							--			JOIN PERIOD P
       --                                      ON P.PERIOD_SID  BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
							--				 AND PM.ID <> 1
							--			LEFT JOIN  (SELECT * FROM #PRODUCT_FILE_TEMP  PF WHERE  EXISTS (SELECT 1 FROM  #TEMP_CCP A WHERE A.ITEM_MASTER_SID=PF.ITEM_MASTER_SID))PF
							--				 ON PM.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID	
							--				 AND PF.PERIOD_SID=P.PERIOD_SID 	 
							--			GROUP  BY PM.PROJECTION_MASTER_SID,
       --                                       P.[YEAR]) a
       --                            LEFT JOIN (SELECT COALESCE(act.PROJECTION_MASTER_SID, proj.PROJECTION_MASTER_SID) AS PROJECTION_MASTER_SID,
       --                                               Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS ,
       --                                               Sum(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED ,
       --                                              Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS ,
       --                                              Sum(PROJECTION_UNITS) AS  CONTRACT_UNITS_PROJECTED ,
       --                                               Sum(COGS_ACTUAL) AS COGS_ACTUALS ,
       --                                               Sum(COGS_PROJECTED) AS COGS_PROJECTED ,
       --                                              [YEAR]
       --                                       --, ITEM_MASTER_SID = COALESCE(ACT.ITEM_MASTER_SID, PROJ.ITEM_MASTER_SID)
       --                                       FROM   (SELECT PD.PROJECTION_MASTER_SID,
       --                                                      NAS.PERIOD_SID,
       --                                                       IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),ACTUAL_SALES,NULL)  ACTUAL_SALES,
       --                                                       IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),ACTUAL_UNITS,NULL) * ISNULL(UOM_VALUE,0) ACTUAL_UNITS,
       --                                                       CCP.ITEM_MASTER_SID,
       --                                                        (ISNULL(IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NAS.ACTUAL_UNITS,NULL), 0) * ISNULL(U.ITEM_PRICE, 0) )  * ISNULL(UOM_VALUE,0) AS COGS_ACTUAL
							--								  ,NAS.PROJECTION_DETAILS_SID
       --                                               FROM   NM_ACTUAL_SALES NAS
       --                                                      INNER JOIN PROJECTION_DETAILS PD
       --                                                              ON NAS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
							--								 INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                      INNER JOIN CCP_DETAILS CCP
       --                                                              ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
       --                                                      INNER JOIN #ITEM_PRICING U
       --                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
       --                                                                 AND NAS.PERIOD_SID = U.PERIOD_SID
							--											LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																							
       --                                               WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                   FROM   #PROJECTION_MASTER PM
       --                                                                                   WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)) ACT
       --                                              FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
       --                                                                NSP.PERIOD_SID,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),PROJECTION_SALES,NULL)  PROJECTION_SALES,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),PROJECTION_UNITS,NULL)  * ISNULL(UOM_VALUE,0) PROJECTION_UNITS,
       --                                                                 CCP.ITEM_MASTER_SID,
       --                                                                 ( Isnull(IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NSP.PROJECTION_UNITS,NULL)  , 0) * Isnull(U.ITEM_PRICE, 0) )  * ISNULL(UOM_VALUE,0) AS COGS_PROJECTED 
							--										   ,NSP.PROJECTION_DETAILS_SID
       --                                                         FROM   NM_SALES_PROJECTION NSP
       --                                                                INNER JOIN PROJECTION_DETAILS PD
       --                                                                        ON NSP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
							--								 INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                                INNER JOIN CCP_DETAILS CCP
       --                                                                        ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
       --                                                                INNER JOIN #ITEM_PRICING U
       --                                                                        ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
       --                                                                           AND NSP.PERIOD_SID = U.PERIOD_SID
							--													  LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																						
       --                                                         WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                             FROM   #PROJECTION_MASTER PM
       --                                                                                             WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
       --                                                        --AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID
       --                                                        ) PROJ
       --                                                     ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
							--								AND ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
       --                                                        AND ACT.PERIOD_SID = PROJ.PERIOD_SID
       --                                              INNER JOIN PERIOD P
       --                                                      ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
       --                                       GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID),
       --                                                 [YEAR]) SALES
       --                                   ON SALES.YEAR = A.YEARLY
       --                            LEFT JOIN (SELECT  Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS ,
       --                                               Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED ,
							--						  COALESCE(SUM(ACCRUAL_DISCOUNT_PROJ),SUM(ACCRUAL_DISCOUNT_ACTUAL)) ACCRUAL_DISCOUNT,
       --                                              COALESCE(act.PROJECTION_MASTER_SID, proj.PROJECTION_MASTER_SID) AS PROJECTION_MASTER_SID,
       --                                              [YEAR]
       --                                       FROM   (SELECT PD.PROJECTION_MASTER_SID,
       --                                                      NAD.PERIOD_SID,
       --                                                      IIF((DEDUCTION_INCLUSION=@DEDUCTION_INCLUSION OR  @DEDUCTION_INCLUSION IS NULL),ACTUAL_SALES,NULL) ACTUAL_SALES,
       --                                                      NAD.RS_CONTRACT_SID,
							--								 NAD.PROJECTION_DETAILS_SID,DISCOUNT_AMOUNT ACCRUAL_DISCOUNT_ACTUAL
       --                                               FROM   NM_ACTUAL_DISCOUNT NAD
       --                                                      INNER JOIN PROJECTION_DETAILS PD
       --                                                              ON NAD.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
							--										  INNER JOIN #DEDUCTION_INCLUSION DI ON DI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
							--								AND DI.RS_CONTRACT_SID=NAD.RS_CONTRACT_SID
							--							  LEFT JOIN  #PRIOR_ACCRUAL_DISCOUNT AD ON AD.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
							--							  AND AD.PROJECTION_DETAILS_SID=NAD.PROJECTION_DETAILS_SID
							--							   AND AD.RS_CONTRACT_SID=NAD.RS_CONTRACT_SID
							--							   AND AD.PERIOD_SID=NAD.PERIOD_SID
       --                                               WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                   FROM   #PROJECTION_MASTER PM
       --                                                                                   WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
							--								AND EXISTS(SELECT 1 FROM #RS_DATA RS WHERE RS.RS_CONTRACT_SID=NAD.RS_CONTRACT_SID )) ACT
       --                                              FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
       --                                                                NDP.PERIOD_SID,
       --                                                                IIF((DEDUCTION_INCLUSION=@DEDUCTION_INCLUSION OR  @DEDUCTION_INCLUSION IS NULL),PROJECTION_SALES,NULL) PROJECTION_SALES,
       --                                                                NDP.RS_CONTRACT_SID
							--										   ,NDP.PROJECTION_DETAILS_SID,DISCOUNT_AMOUNT ACCRUAL_DISCOUNT_PROJ
       --                                                         FROM   NM_DISCOUNT_PROJECTION NDP
       --                                                                INNER JOIN PROJECTION_DETAILS PD
       --                                                                        ON NDP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
							--									INNER JOIN #DEDUCTION_INCLUSION DI ON DI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
							--								AND DI.RS_CONTRACT_SID=NDP.RS_CONTRACT_SID
							--										 LEFT JOIN  #PRIOR_ACCRUAL_DISCOUNT AD ON AD.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
							--							  AND AD.PROJECTION_DETAILS_SID=NDP.PROJECTION_DETAILS_SID
							--							   AND AD.RS_CONTRACT_SID=NDP.RS_CONTRACT_SID
							--							   AND AD.PERIOD_SID=NDP.PERIOD_SID
       --                                                         WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                             FROM   #PROJECTION_MASTER PM
       --                                                                                             WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
       --                                                        AND EXISTS(SELECT 1 FROM #RS_DATA RS WHERE RS.RS_CONTRACT_SID=NDP.RS_CONTRACT_SID )
       --                                                        ) PROJ
       --                                                     ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
							--								AND ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
       --                                                        AND ACT.PERIOD_SID = PROJ.PERIOD_SID
       --                                                        AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
       --                                              INNER JOIN PERIOD P
       --                                                      ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
       --                                       GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID),
       --                                                 YEAR) DISC
       --                                   ON DISC.YEAR = COALESCE(A.YEARLY, SALES.YEAR)
       --                                      AND DISC.PROJECTION_MASTER_SID = COALESCE(A.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID)
       --                            LEFT JOIN (SELECT Sum(ACTUAL_PPA_SALES) AS PPA_DISCOUNT_ACTUALS ,
       --                                               Sum(ACTUAL_PPA_RPU) AS PPA_RPU_ACTUALS ,
       --                                              Sum(PROJECTION_PPA_SALES) AS PPA_DISCOUNT_PROJECTED ,
       --                                               Sum(PPA_RPU) AS PPA_RPU_PROJECTED ,
       --                                              Sum(ACTUAL_SALES) AS  PPA_ACTUAL_SALES ,
       --                                               Sum(ACTUAL_UNITS) AS PPA_ACTUAL_UNITS ,
       --                                              Sum(PROJECTION_SALES) AS  PPA_PROJECTION_SALES ,
       --                                               Sum(PROJECTION_UNITS) AS PPA_PROJECTION_UNITS ,
       --                                               COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID) AS PROJECTION_MASTER_SID ,
       --                                              [YEAR]
       --                                       FROM   (SELECT PD.PROJECTION_MASTER_SID,
       --                                                      NAP.PERIOD_SID,
       --                                                       ACTUAL_DISCOUNT_DOLLAR AS ACTUAL_PPA_SALES ,
       --                                                      ACTUAL_DISCOUNT_DOLLAR AS  ACTUAL_PPA_RPU ,
       --                                                      RS_CONTRACT_SID,
       --                                                      IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.ACTUAL_SALES,NULL)  AS ACTUAL_SALES,
       --                                                      IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.ACTUAL_UNITS,NULL)  * ISNULL(UOM_VALUE,0) AS ACTUAL_UNITS
							--								  ,NAP.PROJECTION_DETAILS_SID
       --                                               FROM   NM_ACTUAL_PPA NAP
       --                                                      INNER JOIN NM_ACTUAL_SALES NS
       --                                                              ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
       --                                                                 AND NS.PERIOD_SID = NAP.PERIOD_SID
       --                                                      INNER JOIN PROJECTION_DETAILS PD
       --                                                              ON NS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
							--										 INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
							--										  INNER JOIN CCP_DETAILS CCP
       --                                                                         ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
							--								 LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																						
       --                                               WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                   FROM   #PROJECTION_MASTER PM
       --                                                                                   WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
							--								AND EXISTS(SELECT 1 FROM #PRIOR_RS_PPA RS WHERE RS.RS_CONTRACT_SID=NAP.RS_CONTRACT_SID )) ACT
       --                                              FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
       --                                                                NPP.PERIOD_SID,
       --                                                                PROJECTION_DISCOUNT_DOLLAR AS PPA_RPU ,
       --                                                                 PROJECTION_DISCOUNT_DOLLAR AS PROJECTION_PPA_SALES ,
       --                                                                RS_CONTRACT_SID,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.PROJECTION_SALES,NULL)   AS PROJECTION_SALES ,
       --                                                                 IIF((SALES_INCLUSION=@SALES_INCLUSION OR @SALES_INCLUSION IS NULL),NS.PROJECTION_UNITS,NULL)  * ISNULL(UOM_VALUE,0) AS PROJECTION_UNITS
							--											,NPP.PROJECTION_DETAILS_SID
       --                                                         FROM   NM_PPA_PROJECTION NPP
       --                                                                INNER JOIN NM_SALES_PROJECTION NS
       --                                                                        ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
       --                                                                           AND NS.PERIOD_SID = NPP.PERIOD_SID
       --                                                                INNER JOIN PROJECTION_DETAILS PD
       --                                                                        ON NS.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
							--												   INNER JOIN #SALES_INCLUSION SI ON SI.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
       --                                                      AND  EXISTS (SELECT 1 FROM #CURRENT_CPP_COMP_PRIOR_CPP CC
       --                                                               WHERE  CC.CCP_DETAILS_SID = pd.CCP_DETAILS_SID)
																	  
							--										  INNER JOIN CCP_DETAILS CCP
       --                                                                         ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
							--								 LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID=CCP.ITEM_MASTER_SID
																							
       --                                                         WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
       --                                                                                             FROM   #PROJECTION_MASTER PM
       --                                                                                             WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
       --                                                        AND EXISTS(SELECT 1 FROM #PRIOR_RS_PPA RS WHERE RS.RS_CONTRACT_SID=NPP.RS_CONTRACT_SID )
       --                                                        ) PROJ
       --                                                     ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
							--								AND ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
       --                                                        AND ACT.PERIOD_SID = PROJ.PERIOD_SID
       --                                                        AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
       --                                              INNER JOIN PERIOD P
       --                                                      ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
       --                                       GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID),
       --                                                 YEAR) PPA
       --                                   ON PPA.YEAR = SALES.YEAR
       --                                      AND COALESCE(A.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) = PPA.PROJECTION_MASTER_SID
       --                     ORDER  BY SALES.YEAR
       --                 END
                      --SECOND TO LAST PROJECTION_DETAILS_SID(PULL FROM MAIN TABLE) END
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
						
						
                  ------------PIVOTING END
        
            /*  ELSE -- PERIOD VIEW
                         BEGIN
                             IF @PROJ_FREQUENCY = 'MONTHLY'
                               BEGIN
                                   SELECT @PROJECTION_SID PROJECTION_ID,
                                          GTS_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                                          GTS_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
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
                                                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) )
                                   FROM   (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                  GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
                                                  UNITS = Sum(UNITS),
                                                  MONTHLY PERIOD,
                                                  YEARLY,
                                                  PERIOD_SID
                                           FROM   #TEMP_GTS_DETAILS G
                                                  --INNER JOIN @ITEM_INFO ACT
                                                  --        ON G.ITEMID = ACT.ITEM_ID
                                                  INNER JOIN PERIOD P
                                                          ON P.[MONTH] = G.MONTHLY
                                                             AND P.[YEAR] = G.YEARLY
                                           GROUP  BY MONTHLY,
                                                     YEARLY,
                                                     PERIOD_SID) GTS
                                          FULL JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                                            CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                                            PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID),
                                                            CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                                            CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS)
                                                     FROM   (SELECT PROJECTION_DETAILS_SID,
                                                                    PERIOD_SID,
                                                                    ACTUAL_SALES,
                                                                    ACTUAL_UNITS
                                                             FROM   NM_ACTUAL_SALES NAS
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
                                                            FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                              PERIOD_SID,
                                                                              PROJECTION_SALES,
                                                                              PROJECTION_UNITS
                                                                       FROM   NM_SALES_PROJECTION NPS
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                     GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SALES
                                                 ON SALES.PERIOD_SID = GTS.PERIOD_SID
                                          INNER JOIN PERIOD P
                                                  ON P.PERIOD_SID = SALES.PERIOD_SID
                                          LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                                            CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                                            PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                                     FROM   (SELECT PROJECTION_DETAILS_SID,
                                                                    PERIOD_SID,
                                                                    ACTUAL_SALES,
                                                                    RS_CONTRACT_SID
                                                             FROM   NM_ACTUAL_DISCOUNT NAD
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                    AND EXISTS (SELECT 1
                                                                                FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
                                                                                WHERE  A.TOKEN = NAD.RS_CONTRACT_SID)) ACT
                                                            FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                              PERIOD_SID,
                                                                              PROJECTION_SALES,
                                                                              RS_CONTRACT_SID
                                                                       FROM   NM_DISCOUNT_PROJECTION NDP
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                              AND EXISTS (SELECT 1
                                                                                          FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
                                                                                          WHERE  A.TOKEN = NDP.RS_CONTRACT_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                                      AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                                     GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) DISC
                                                 ON DISC.PERIOD_SID = P.PERIOD_SID
                                          LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                                            PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                                            PPA_PROJECTION_SALES = Sum(PPA_PROJECTION_SALES),
                                                            PPA_PROJECTION_UNITS = Sum(PPA_PROJECTION_UNITS),
                                                            PPA_ACTUAL_SALES = Sum(PPA_ACTUAL_SALES),
                                                            PPA_ACTUAL_UNITS = Sum(PPA_ACTUAL_UNITS),
                                                            PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                                     FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                                    NAP.PERIOD_SID,
                                                                    ACTUAL_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                                    PPA_ACTUAL_SALES = NS.ACTUAL_SALES,
                                                                    PPA_ACTUAL_UNITS = NS.ACTUAL_UNITS,
                                                                    RS_CONTRACT_SID
                                                             FROM   NM_ACTUAL_PPA NAP
                                                                    INNER JOIN NM_ACTUAL_SALES NS
                                                                            ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                               AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
                                                            FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                              NPP.PERIOD_SID,
                                                                              PROJECTION_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                              PPA_PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                              PPA_PROJECTION_UNITS = NS.PROJECTION_UNITS,
                                                                              RS_CONTRACT_SID
                                                                       FROM   NM_PPA_PROJECTION NPP
                                                                              INNER JOIN NM_SALES_PROJECTION NS
                                                                                      ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                         AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                                      AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                                     GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) PPA
                                                 ON PPA.PERIOD_SID = SALES.PERIOD_SID
                                   ORDER  BY P.[YEAR],
                                             P.[MONTH]
                               END
                             ELSE IF @PROJ_FREQUENCY = 'QUARTERLY'
                               BEGIN
                                   SELECT @PROJECTION_SID PROJECTION_ID,
                                          GTS_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                                          GTS_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                                          GTS_UNITS = Isnull(GTS.UNITS, 0),
                                          SALES.[QUARTER],
                                          SALES.[YEAR],
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
                                                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) )
                                   FROM   (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                  GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
                                                  UNITS = Sum(UNITS),
                                                  QUARTERLY,
                                                  YEARLY
                                           FROM   #TEMP_GTS_DETAILS G
                                           --INNER JOIN @ITEM_INFO ACT
                                           --        ON G.ITEMID = ACT.ITEM_ID
                                           GROUP  BY QUARTERLY,
                                                     YEARLY) GTS
                                          FULL JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                                            CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                                            [QUARTER],
                                                            [YEAR],
                                                            CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                                            CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS)
                                                     FROM   (SELECT PROJECTION_DETAILS_SID,
                                                                    PERIOD_SID,
                                                                    ACTUAL_SALES,
                                                                    ACTUAL_UNITS
                                                             FROM   NM_ACTUAL_SALES NAS
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
                                                            FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                              PERIOD_SID,
                                                                              PROJECTION_SALES,
                                                                              PROJECTION_UNITS
                                                                       FROM   NM_SALES_PROJECTION NPS
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                            INNER JOIN PERIOD P
                                                                    ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                                     GROUP  BY [QUARTER],
                                                               [YEAR]) SALES
                                                 ON SALES.[QUARTER] = GTS.QUARTERLY
                                                    AND SALES.[YEAR] = GTS.YEARLY
                                          LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                                            CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                                            [QUARTER],
                                                            [YEAR]
                                                     FROM   (SELECT PROJECTION_DETAILS_SID,
                                                                    PERIOD_SID,
                                                                    ACTUAL_SALES,
                                                                    RS_CONTRACT_SID
                                                             FROM   NM_ACTUAL_DISCOUNT NAD
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                    AND EXISTS (SELECT 1
                                                                                FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
                                                                                WHERE  A.TOKEN = NAD.RS_CONTRACT_SID)) ACT
                                                            FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                              PERIOD_SID,
                                                                              PROJECTION_SALES,
                                                                              RS_CONTRACT_SID
                                                                       FROM   NM_DISCOUNT_PROJECTION NDP
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                              AND EXISTS (SELECT 1
                                                                                          FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
                                                                                          WHERE  A.TOKEN = NDP.RS_CONTRACT_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                                      AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                                            INNER JOIN PERIOD P
                                                                    ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                                     GROUP  BY [QUARTER],
                                                               [YEAR]) DISC
                                                 ON DISC.[QUARTER] = SALES.[QUARTER]
                                                    AND DISC.[YEAR] = SALES.[YEAR]
                                          LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                                            PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                                            PPA_PROJECTION_SALES = Sum(PPA_PROJECTION_SALES),
                                                            PPA_PROJECTION_UNITS = Sum(PPA_PROJECTION_UNITS),
                                                            PPA_ACTUAL_SALES = Sum(PPA_ACTUAL_SALES),
                                                            PPA_ACTUAL_UNITS = Sum(PPA_ACTUAL_UNITS),
                                                            [QUARTER],
                                                            [YEAR]
                                                     FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                                    NAP.PERIOD_SID,
                                                                    ACTUAL_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                                    PPA_ACTUAL_SALES = NS.ACTUAL_SALES,
                                                                    PPA_ACTUAL_UNITS = NS.ACTUAL_UNITS,
                                                                    RS_CONTRACT_SID
                                                             FROM   NM_ACTUAL_PPA NAP
                                                                    INNER JOIN NM_ACTUAL_SALES NS
                                                                            ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                               AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
                                                            FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                              NPP.PERIOD_SID,
                                                                              PROJECTION_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                              PPA_PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                              PPA_PROJECTION_UNITS = NS.PROJECTION_UNITS,
                                                                              RS_CONTRACT_SID
                                                                       FROM   NM_PPA_PROJECTION NPP
                                                                              INNER JOIN NM_SALES_PROJECTION NS
                                                                                      ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                         AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                                      AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                                            INNER JOIN PERIOD P
                                                                    ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                                     GROUP  BY [QUARTER],
                                                               [YEAR]) PPA
                                                 ON PPA.[QUARTER] = SALES.[QUARTER]
                                                    AND PPA.[YEAR] = SALES.[YEAR]
                                   ORDER  BY SALES.[YEAR],
                                             SALES.[QUARTER]
                               END
                             ELSE IF @PROJ_FREQUENCY = 'SEMI-ANNUAL'
                               BEGIN
                                   SELECT @PROJECTION_SID PROJECTION_ID,
                                          GTS_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                                          GTS_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                                          GTS_UNITS = Isnull(GTS.UNITS, 0),
                                          SALES.[SEMI_ANNUAL],
                                          SALES.[YEAR],
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
                                                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) )
                                   FROM   (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                  GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
                                                  Sum(GTS.UNITS) UNITS,
                                                  CASE
                                                    WHEN GTS.QUARTERLY <= 2 THEN 1
                                                    ELSE 2
                                                  END            SEMI_ANNUAL,
                                                  GTS.YEARLY
                                           FROM   #TEMP_GTS_DETAILS GTS
                                           --JOIN @ITEM_INFO ACT
                                           --  ON GTS.ITEMID = ACT.ITEM_ID
                                           GROUP  BY CASE
                                                       WHEN GTS.QUARTERLY <= 2 THEN 1
                                                       ELSE 2
                                                     END,
                                                     GTS.YEARLY) GTS
                                          FULL JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                                            CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                                            SEMI_ANNUAL,
                                                            [YEAR],
                                                            CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                                            CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS)
                                                     FROM   (SELECT PROJECTION_DETAILS_SID,
                                                                    PERIOD_SID,
                                                                    ACTUAL_SALES,
                                                                    ACTUAL_UNITS
                                                             FROM   NM_ACTUAL_SALES NAS
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
                                                            FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                              PERIOD_SID,
                                                                              PROJECTION_SALES,
                                                                              PROJECTION_UNITS
                                                                       FROM   NM_SALES_PROJECTION NPS
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                            INNER JOIN PERIOD P
                                                                    ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                                     GROUP  BY SEMI_ANNUAL,
                                                               [YEAR]) SALES
                                                 ON SALES.SEMI_ANNUAL = GTS.SEMI_ANNUAL
                                                    AND SALES.[YEAR] = GTS.YEARLY
                                          LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                                            CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                                            SEMI_ANNUAL,
                                                            [YEAR]
                                                     FROM   (SELECT PROJECTION_DETAILS_SID,
                                                                    PERIOD_SID,
                                                                    ACTUAL_SALES,
                                                                    RS_CONTRACT_SID
                                                             FROM   NM_ACTUAL_DISCOUNT NAD
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                    AND EXISTS (SELECT 1
                                                                                FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
                                                                                WHERE  A.TOKEN = NAD.RS_CONTRACT_SID)) ACT
                                                            FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                              PERIOD_SID,
                                                                              PROJECTION_SALES,
                                                                              RS_CONTRACT_SID
                                                                       FROM   NM_DISCOUNT_PROJECTION NDP
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                              AND EXISTS (SELECT 1
                                                                                          FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
                                                                                          WHERE  A.TOKEN = NDP.RS_CONTRACT_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                                      AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                                            INNER JOIN PERIOD P
                                                                    ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                                     GROUP  BY SEMI_ANNUAL,
                                                               [YEAR]) DISC
                                                 ON DISC.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                                    AND DISC.[YEAR] = SALES.[YEAR]
                                          LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                                            PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                                            PPA_PROJECTION_SALES = Sum(PPA_PROJECTION_SALES),
                                                            PPA_PROJECTION_UNITS = Sum(PPA_PROJECTION_UNITS),
                                                            PPA_ACTUAL_SALES = Sum(PPA_ACTUAL_SALES),
                                                            PPA_ACTUAL_UNITS = Sum(PPA_ACTUAL_UNITS),
                                                            SEMI_ANNUAL,
                                                            [YEAR]
                                                     FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                                    NAP.PERIOD_SID,
                                                                    ACTUAL_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                                    PPA_ACTUAL_SALES = NS.ACTUAL_SALES,
                                                                    PPA_ACTUAL_UNITS = NS.ACTUAL_UNITS,
                                                                    RS_CONTRACT_SID
                                                             FROM   NM_ACTUAL_PPA NAP
                                                                    INNER JOIN NM_ACTUAL_SALES NS
                                                                            ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                               AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
                                                            FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                              NPP.PERIOD_SID,
                                                                              PROJECTION_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                              PPA_PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                              PPA_PROJECTION_UNITS = NS.PROJECTION_UNITS,
                                                                              RS_CONTRACT_SID
                                                                       FROM   NM_PPA_PROJECTION NPP
                                                                              INNER JOIN NM_SALES_PROJECTION NS
                                                                                      ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                         AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                                      AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                                            INNER JOIN PERIOD P
                                                                    ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                                     GROUP  BY SEMI_ANNUAL,
                                                               [YEAR]) PPA
                                                 ON PPA.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                                    AND PPA.[YEAR] = SALES.[YEAR]
                                   ORDER  BY SALES.[YEAR],
                                             SALES.[SEMI_ANNUAL]
                               END
                             ELSE
                               BEGIN
                                   SELECT @PROJECTION_SID PROJECTION_ID,
                                          GTS_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                                          GTS_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                                          GTS_UNITS = Isnull(GTS.UNITS, 0),
                                          SALES.[YEAR],
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
                                                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) )
                                   FROM   (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                  GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
                                                  Sum(GTS.UNITS) UNITS,
                                                  GTS.YEARLY
                                           FROM   #TEMP_GTS_DETAILS GTS
                                           --JOIN @ITEM_INFO ACT
                                           --  ON GTS.ITEMID = ACT.ITEM_ID
                                           GROUP  BY GTS.YEARLY) GTS
                                          FULL JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                                            CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                                            [YEAR],
                                                            CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                                            CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS)
                                                     FROM   (SELECT PROJECTION_DETAILS_SID,
                                                                    PERIOD_SID,
                                                                    ACTUAL_SALES,
                                                                    ACTUAL_UNITS
                                                             FROM   NM_ACTUAL_SALES NAS
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
                                                            FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                              PERIOD_SID,
                                                                              PROJECTION_SALES,
                                                                              PROJECTION_UNITS
                                                                       FROM   NM_SALES_PROJECTION NPS
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                            INNER JOIN PERIOD P
                                                                    ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                                     GROUP  BY [YEAR]) SALES
                                                 ON SALES.[YEAR] = GTS.YEARLY
                                          LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                                            CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                                            [YEAR]
                                                     FROM   (SELECT PROJECTION_DETAILS_SID,
                                                                    PERIOD_SID,
                                                                    ACTUAL_SALES,
                                                                    RS_CONTRACT_SID
                                                             FROM   NM_ACTUAL_DISCOUNT NAD
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                    AND EXISTS (SELECT 1
                                                                                FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
                                                                                WHERE  A.TOKEN = NAD.RS_CONTRACT_SID)) ACT
                                                            FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                              PERIOD_SID,
                                                                              PROJECTION_SALES,
                                                                              RS_CONTRACT_SID
                                                                       FROM   NM_DISCOUNT_PROJECTION NDP
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                              AND EXISTS (SELECT 1
                                                                                          FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
                                                                                          WHERE  A.TOKEN = NDP.RS_CONTRACT_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                                      AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                                            INNER JOIN PERIOD P
                                                                    ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                                     GROUP  BY [YEAR]) DISC
                                                 ON DISC.[YEAR] = SALES.[YEAR]
                                          LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                                            PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                                            PPA_PROJECTION_SALES = Sum(PPA_PROJECTION_SALES),
                                                            PPA_PROJECTION_UNITS = Sum(PPA_PROJECTION_UNITS),
                                                            PPA_ACTUAL_SALES = Sum(PPA_ACTUAL_SALES),
                                                            PPA_ACTUAL_UNITS = Sum(PPA_ACTUAL_UNITS),
                                                            [YEAR]
                                                     FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                                    NAP.PERIOD_SID,
                                                                    ACTUAL_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                                    PPA_ACTUAL_SALES = NS.ACTUAL_SALES,
                                                                    PPA_ACTUAL_UNITS = NS.ACTUAL_UNITS,
                                                                    RS_CONTRACT_SID
                                                             FROM   NM_ACTUAL_PPA NAP
                                                                    INNER JOIN NM_ACTUAL_SALES NS
                                                                            ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                               AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                             WHERE  EXISTS (SELECT 1
                                                                            FROM   PROJECTION_DETAILS PD
                                                                            WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
                                                            FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                              NPP.PERIOD_SID,
                                                                              PROJECTION_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                              PPA_PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                              PPA_PROJECTION_UNITS = NS.PROJECTION_UNITS,
                                                                              RS_CONTRACT_SID
                                                                       FROM   NM_PPA_PROJECTION NPP
                                                                              INNER JOIN NM_SALES_PROJECTION NS
                                                                                      ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                         AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                                       WHERE  EXISTS (SELECT 1
                                                                                      FROM   PROJECTION_DETAILS PD
                                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
                                                                   ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                      AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                                      AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                                            INNER JOIN PERIOD P
                                                                    ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                                     GROUP  BY [YEAR]) PPA
                                                 ON PPA.[YEAR] = SALES.[YEAR]
                                   ORDER  BY SALES.[YEAR]
                               END
                         END*/
            




			END
			GO