IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_CFF_EXCEL_EXPORT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_CFF_EXCEL_EXPORT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_CFF_EXCEL_EXPORT] (@CFF_MASTER_SID VARCHAR(100),
                                                       @FREQUENCY VARCHAR(20),
													   @USER_ID INT,
													   @SESSION_ID VARCHAR(50),
													   @INDICATOR CHAR(1),
													   @CUSTOM_VIEW_MASTER_SID INT,
													   @SALES_INCLUSION BIT,
													   @DEDUCTION_INCLUSION BIT,
													   @level_no SMALLINT
													   )
  

AS	                                                        
BEGIN 
SET NOCOUNT ON 

BEGIN TRY	
DECLARE @CFF_PRODUCT           VARCHAR(200) = CONCAT ('ST_CFF_PRODUCT_LEVEL_FILES_DATA_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),----PRODUCT LEVEL DATA
        @CFF_COMP_PROD         VARCHAR(200) = CONCAT ('ST_CFF_COMP_PRD_LEVEL_FILES_DATA_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),  ---- COMP+PROD DATA
		@CCP_HIERARCHY         VARCHAR(200) = CONCAT ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')), ---- CCP DEATILS INFORMATION
		@CUSTOMER_CFF_SALES VARCHAR(200) = CONCAT('ST_CUSTOMER_CFF_SALES_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@PRODUCT_CFF_SALES VARCHAR(200) = CONCAT('ST_PRODUCT_CFF_SALES_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@CUSTOM_CFF_SALES VARCHAR(200) = CONCAT('ST_CUSTOM_CFF_SALES_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@CUSTOMER_CFF_DISCOUNT VARCHAR(200) = CONCAT('ST_CUSTOMER_CFF_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@PRODUCT_CFF_DISCOUNT VARCHAR(200) = CONCAT('ST_PRODUCT_CFF_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@CUSTOM_CFF_DISCOUNT VARCHAR(200) = CONCAT('ST_CUSTOM_CFF_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@CUSTOM_CCP_SALES VARCHAR(200) = CONCAT ('CUSTOM_CCP_SALES_',@CUSTOM_VIEW_MASTER_SID),
		@CUSTOMER_DETAILS_TEMP varchar(200),
		@CUSTOMER_CFF_HIERARCHY_DETAILS VARCHAR(200) = CONCAT('ST_CUSTOMER_CFF_HIERARCHY_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@PRODUCT_CFF_HIERARCHY_DETAILS VARCHAR(200) = CONCAT('ST_PRODUCT_CFF_HIERARCHY_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@CUSTOM_CFF_HIERARCHY_DETAILS VARCHAR(200) = CONCAT('ST_CUSTOM_CFF_HIERARCHY_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@SALES_TABLE            VARCHAR(MAX),
        @DISCOUNT_TABLE         VARCHAR(200),
		@ROW_ID INT,
		@ROW_OP INT
	
DECLARE @ACTUAL_START_DATE DATETIME = CAST(YEAR(GETDATE()) - 3 AS VARCHAR(4)) + '-01-01',
         @PROJ_END_PERIOD_DATE DATETIME	

SELECT TOP 1 @PROJ_END_PERIOD_DATE = DATEADD(DD, 1, EOMONTH(TO_DATE, - 1)),@ACTUAL_START_DATE= DATEADD(DD, 1, EOMONTH(FROM_DATE, - 1))
FROM FORECAST_CONFIG FA
LEFT JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FA.BUSINESS_PROCESS_TYPE
WHERE [DESCRIPTION] = 'CONSOLIDATED FINANCIAL FORECAST'
ORDER BY VERSION_NO DESC

DECLARE @FROM_PERIOD_SID INT,@END_PERIOD_SID INT

 SET @FROM_PERIOD_SID  = (
		SELECT PERIOD_SID
		FROM PERIOD
		WHERE PERIOD_DATE = @ACTUAL_START_DATE
		)
		
SET @END_PERIOD_SID  = (
		SELECT PERIOD_SID
		FROM PERIOD
		WHERE PERIOD_DATE = @PROJ_END_PERIOD_DATE
		)					 
-----------------PERIOD CONVERISON--------------------
IF OBJECT_ID('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
	DROP TABLE #PERIOD;

SELECT PERIOD_SID,
	YEAR,
	CASE 
		WHEN LEFT(@FREQUENCY, 1) = 'M'
			THEN MONTH
		WHEN LEFT(@FREQUENCY, 1) = 'Q'
			THEN QUARTER
		WHEN LEFT(@FREQUENCY, 1) = 'S'
			THEN SEMI_ANNUAL
		ELSE 1
		END AS PERIOD
INTO #PERIOD
FROM PERIOD
WHERE PERIOD_SID BETWEEN @FROM_PERIOD_SID
		AND @END_PERIOD_SID

DECLARE @SP INT
	,@SP_PROJ_SID INT
	,@TEMP_PROJ_SID VARCHAR(500)
	,@FIRST_PROJ_SID INT

 IF OBJECT_ID('TEMPDB..#CFF_PROJECTION_MASTER') IS NOT NULL 
            DROP TABLE #CFF_PROJECTION_MASTER 


	CREATE TABLE #CFF_PROJECTION_MASTER (
		ID INT IDENTITY(1, 1),
		CFF_MASTER_SID INT
		)

SET @TEMP_PROJ_SID = REPLACE(@CFF_MASTER_SID + ',', ',,', ',')

WHILE PATINDEX('%,%', @TEMP_PROJ_SID) <> 0
BEGIN
	SELECT @SP = PATINDEX('%,%', @TEMP_PROJ_SID)

	SELECT @SP_PROJ_SID = LEFT(@TEMP_PROJ_SID, @SP - 1)

	SELECT @TEMP_PROJ_SID = STUFF(@TEMP_PROJ_SID, 1, @SP, '')

	INSERT INTO #CFF_PROJECTION_MASTER (CFF_MASTER_SID)
	SELECT @SP_PROJ_SID
END

SELECT @FIRST_PROJ_SID = PM.CFF_MASTER_SID
FROM #CFF_PROJECTION_MASTER PM
WHERE ID = 1
--------------------TAKING TABLES BASED ON VIEW STARTS HERE
 IF @INDICATOR = 'C'
            BEGIN
                SET @SALES_TABLE=@CUSTOMER_CFF_SALES
                SET @DISCOUNT_TABLE=@CUSTOMER_CFF_DISCOUNT
				set @CUSTOMER_DETAILS_TEMP=@CUSTOMER_CFF_HIERARCHY_DETAILS
            END
          ELSE IF @INDICATOR = 'P'
            BEGIN
                SET @SALES_TABLE=@PRODUCT_CFF_SALES
                SET @DISCOUNT_TABLE=@PRODUCT_CFF_DISCOUNT
				set @CUSTOMER_DETAILS_TEMP=@PRODUCT_CFF_HIERARCHY_DETAILS
            END
          ELSE IF @INDICATOR NOT IN ( 'C', 'P' )
            BEGIN
                SET @SALES_TABLE=@CUSTOM_CFF_SALES
                SET @DISCOUNT_TABLE=@CUSTOM_CFF_DISCOUNT
				set @CUSTOMER_DETAILS_TEMP=@CUSTOM_CFF_HIERARCHY_DETAILS
            END

			DECLARE @RELATIONSHIP_SID INT, @VERSION INT

			SELECT @RELATIONSHIP_SID=IIF( @INDICATOR='C',CUST_RELATIONSHIP_BUILDER_SID,PROD_RELATIONSHIP_BUILDER_SID),
			@VERSION=IIF( @INDICATOR='C',PROJECTION_CUST_VERSION,PROJECTION_PROD_VERSION )
			
			FROM CFF_MASTER WHERE CFF_MASTER_SID=@FIRST_PROJ_SID

 DECLARE @SQL_C NVARCHAR(MAX) , @SQL_PRO NVARCHAR(MAX)	

IF OBJECT_ID('TEMPDB..#HIERARCHY_NO','U') IS NOT NULL
    DROP TABLE #HIERARCHY_NO
CREATE TABLE #HIERARCHY_NO
(
HIERARCHY_NO VARCHAR(1000),row_id int
)

SET @SQL_PRO=CONCAT('','
INSERT INTO #HIERARCHY_NO

SELECT distinct HIERARCHY_NO,row_id FROM ',@CUSTOMER_DETAILS_TEMP,'')


EXEC(@SQL_PRO)
 declare @END_SALES_SID int = (
		SELECT PERIOD_SID
		FROM PERIOD
		WHERE PERIOD_DATE = DATEADD(MM, - 3, DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0))
		)	
-----------------Taking file Values including Prior CFF'S

IF OBJECT_ID('TEMPDB..#PRODUCT_FILE_DATA') IS NOT NULL
DROP TABLE #PRODUCT_FILE_DATA
CREATE TABLE #PRODUCT_FILE_DATA
(
CFF_MASTER_SID INT,
ROW_ID INT,
YEAR INT,
PERIOD SMALLINT,
EX_FACTORY_SALES NUMERIC(22,6),
DEMAND_SALES NUMERIC(22,6),
ADJUSTED_DEMAND  NUMERIC(22,6),
IW_MASTER NUMERIC(22,6),
INDICATOR BIT
)
SET @SQL_PRO=CONCAT('
INSERT INTO #PRODUCT_FILE_DATA (CFF_MASTER_SID,ROW_ID,YEAR,PERIOD,EX_FACTORY_SALES--,DEMAND_SALES,
--ADJUSTED_DEMAND,IW_MASTER
,INDICATOR)
SELECT CF.CFF_MASTER_SID
	,PRO.ROW_ID
	,YEAR
	,PERIOD
	,SUM(EX_FACTORY_SALES_PROJ) AS EX_FACTORY_SALES
	--,SUM(DEMAND_SALES_PROJ) AS DEMAND_SALES
	--,SUM(ADJUSTED_DEMAND_PROJ) AS ADJUSTED_DEMAND
	--,SUM(IW_MASTER_PROJ) AS IW_MASTER
	,1 AS INDICATOR
		FROM (select * from ', @CFF_PRODUCT ,' where EX_FACTORY_SALES_PROJ is not null) CF
JOIN #PERIOD p  on P.PERIOD_SID = CF.PERIOD_SID
JOIN (
	SELECT DISTINCT ITEM_MASTER_SID
		,ROW_ID
	FROM ',@CUSTOMER_DETAILS_TEMP,'
	) PRO ON CF.ITEM_MASTER_SID = PRO.ITEM_MASTER_SID
GROUP BY cf.CFF_MASTER_SID
	,PRO.ROW_ID
	,YEAR
	,PERIOD
	')
--	select @SQL_PRO
  EXEC Sp_executesql @SQL_PRO
SET @SQL_PRO=CONCAT('
INSERT INTO #PRODUCT_FILE_DATA (CFF_MASTER_SID,ROW_ID,YEAR,PERIOD,EX_FACTORY_SALES--,DEMAND_SALES,
--ADJUSTED_DEMAND,IW_MASTER
,INDICATOR)
SELECT CF.CFF_MASTER_SID
	,PRO.ROW_ID
	,YEAR
	,PERIOD
	,SUM(EX_FACTORY_SALES_ACTUALS) AS EX_FACTORY_SALES
	--,SUM(DEMAND_SALES_ACTUALS) AS DEMAND_SALES
	--,SUM(ADJUSTED_DEMAND_ACTUALS) AS ADJUSTED_DEMAND
	--,SUM(IW_MASTER_ACTUALS) AS IW_MASTER
	,0 AS INDICATOR
FROM (select * from ', @CFF_PRODUCT ,' where EX_FACTORY_SALES_ACTUALS is not null)  CF
JOIN #PERIOD p ON CF.CFF_MASTER_SID =  ', @FIRST_PROJ_SID ,'
	AND P.PERIOD_SID = CF.PERIOD_SID
	
JOIN (
	SELECT DISTINCT ITEM_MASTER_SID
		,ROW_ID
	FROM ',@CUSTOMER_DETAILS_TEMP,'
	) PRO ON CF.ITEM_MASTER_SID = PRO.ITEM_MASTER_SID
	where p.period_sid<',@END_SALES_SID,'
GROUP BY cf.CFF_MASTER_SID
	,PRO.ROW_ID
	,YEAR
	,PERIOD ')
	--select @SQL_PRO
	EXEC Sp_executesql @SQL_PRO



			-----------------15416768

DECLARE @SQL_COMP NVARCHAR(MAX)

IF OBJECT_ID('TEMPDB..#COMP_PRODUCT_FILE_DATA') IS NOT NULL
DROP TABLE #COMP_PRODUCT_FILE_DATA
CREATE TABLE #COMP_PRODUCT_FILE_DATA
(
CFF_MASTER_SID INT,
ROW_ID INT,
YEAR INT,
PERIOD SMALLINT,
CUST_EX_FACTORY_SALES NUMERIC(22,6),
IW_DETAILS NUMERIC(22,6),
INDICATOR BIT
)
SET @SQL_COMP= CONCAT('
INSERT INTO #COMP_PRODUCT_FILE_DATA (CFF_MASTER_SID,ROW_ID,YEAR,PERIOD,CUST_EX_FACTORY_SALES,
IW_DETAILS,INDICATOR)
SELECT      CF.CFF_MASTER_SID
			,COMP.ROW_ID
            ,YEAR
			,PERIOD
			,SUM(CUST_EX_FACTORY_SALES_ACTUALS) AS CUST_EX_FACTORY_SALES
			,SUM(IW_DETAILS_ACTUALS) AS IW_DETAILS
			,0 AS INDICATOR
		FROM ', @CFF_COMP_PROD ,' CF
		INNER JOIN #PERIOD P ON P.PERIOD_SID = CF.PERIOD_SID 
		INNER JOIN #CFF_PROJECTION_MASTER CFM ON CF.CFF_MASTER_SID=CFM.CFF_MASTER_SID AND CF.CFF_MASTER_SID = ', @FIRST_PROJ_SID ,'
		INNER JOIN ',@CUSTOMER_DETAILS_TEMP,' COMP ON CF.COMPANY_MASTER_SID=COMP.COMPANY_MASTER_SID
		AND CF.ITEM_MASTER_SID=COMP.ITEM_MASTER_SID
		where p.period_sid<',@END_SALES_SID,'
		GROUP BY YEAR
			,PERIOD
			,CF.CFF_MASTER_SID
			,COMP.ROW_ID
UNION ALL
SELECT      CF.CFF_MASTER_SID
			,COMP.ROW_ID
            ,YEAR
			,PERIOD
			,SUM(CUST_EX_FACTORY_SALES_PROJ) AS CUST_EX_FACTORY_SALES
			,SUM(IW_DETAILS_PROJ) AS IW_DETAILS
			, 1 AS INDICATOR
		FROM ', @CFF_COMP_PROD ,' CF
		INNER JOIN #PERIOD P ON P.PERIOD_SID = CF.PERIOD_SID
		INNER JOIN #CFF_PROJECTION_MASTER CFM ON CF.CFF_MASTER_SID=CFM.CFF_MASTER_SID
		INNER JOIN ',@CUSTOMER_DETAILS_TEMP,' COMP ON CF.COMPANY_MASTER_SID=COMP.COMPANY_MASTER_SID
		AND CF.ITEM_MASTER_SID=COMP.ITEM_MASTER_SID
		GROUP BY YEAR
			,PERIOD
			,CF.CFF_MASTER_SID
			,COMP.ROW_ID')

		
			EXEC Sp_executesql @SQL_COMP

if OBJECT_ID('tempdb..#disco') is not null
	drop table #disco
CREATE TABLE #disco---6,969,338--60420
(
CFF_MASTER_SID INT,
ROW_ID INT,
YEAR INT,
PERIOD SMALLINT,
ACCRUALS NUMERIC(22,6),
DISCOUNT NUMERIC(22,6),
INDICATOR BIT,
DISCOUNT_TYPE tinyint
)		
	set @SQL_C=concat('insert into #disco
	SELECT ND.CFF_MASTER_SID,hr.row_id,	
		ND.YEAR,ND.PERIOD,		
		SUM(ND.ACCRUALS) AS ACCRUALS,
		SUM(ND.DISCOUNT) AS DISCOUNT,INDICATOR,DISCOUNT_TYPE
	FROM #HIERARCHY_NO HR
	INNER JOIN ',@DISCOUNT_TABLE,' ND ON ',case when  @INDICATOR IN ('C','P') then ' ND.HIERARCHY_NO LIKE HR.HIERARCHY_NO + ''%''' else 'ND.HIERARCHY_NO =hr.row_id '  end,'
		AND ND.FILTER_CCP = 1	
		',case when @DEDUCTION_INCLUSION IS not NULL then concat('	AND ND.DEDUCTION_INCLUSION = ',@DEDUCTION_INCLUSION, '   ' ) end,'
			where ND.DISCOUNT is not null and  ND.DISCOUNT<>0
	GROUP BY ND.CFF_MASTER_SID,hr.row_id,ND.PERIOD,
		ND.YEAR,INDICATOR,DISCOUNT_TYPE')

		EXEC Sp_executesql @SQL_C
	
	if OBJECT_ID('tempdb..#sales') is not null
	drop table #sales---11,080,802
	---12,314,854
	---15,687,172
CREATE TABLE #sales
(
CFF_MASTER_SID INT,
--HIERARCHY_NO varchar(8000),
ROW_ID INT,
YEAR INT,
PERIOD SMALLINT,
SALES NUMERIC(22,6),
UNITS NUMERIC(22,6),COGS_VALUE NUMERIC(22,6),
INDICATOR BIT
)	
		set @SQL_C=concat(' insert into #sales
			SELECT A.CFF_MASTER_SID,--HR.HIERARCHY_NO,
			hr.row_id,		
		A.YEAR,A.PERIOD,
		sum(A.SALES) SALES,
		sum(A.UNITS) UNITS,
		
		SUM(A.COGS_VALUE) COGS_VALUE,INDICATOR

	FROM #HIERARCHY_NO HR
	INNER JOIN ',@SALES_TABLE,' A ON ',case when  @INDICATOR IN ('C','P') then ' a.HIERARCHY_NO LIKE hr.HIERARCHY_NO + ''%'' ' else 'a.HIERARCHY_NO =hr.row_id '  end,'
		AND A.FILTER_CCP = 1
		', case when  @SALES_INCLUSION IS not NULL then concat('AND SALES_INCLUSION = ',@SALES_INCLUSION,'  ') end ,'
		where nullif(A.SALES,0) is not null or nullif(A.UNITS,0) is not null 
			
	GROUP BY A.CFF_MASTER_SID,HR.HIERARCHY_NO,
			hr.row_id,
			A.PERIOD,
			A.YEAR	,INDICATOR')

	EXEC Sp_executesql @SQL_C
		if OBJECT_ID('tempdb..#trail') is not null
	drop table #trail
	select CFF_MASTER_SID,HIERARCHY_NO HIERARCHY_NO,period,year,row_id 
	into #trail
	from #HIERARCHY_NO h cross join #period p cross join #CFF_PROJECTION_MASTER cpm
	group by CFF_MASTER_SID,HIERARCHY_NO,period,year,row_id 
--declare @END_SALES_SID int = (
--		SELECT top 1 PERIOD
--		FROM #period
--		WHERE PERIOD_DATE = DATEADD(MM, - 3, DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0))
--		order by period_sid asc
--		)
--IF @INDICATOR IN ('C','P')
declare @ACTUALS_END_SID int = (
		SELECT top 1 PERIOD
		FROM #PERIOD
		WHERE PERIOD_SID = @END_SALES_SID
		)	
BEGIN
----11,080,802
 SELECT t.CFF_MASTER_SID,
	t.HIERARCHY_NO,
	ISNULL((PFD.EX_FACTORY_SALES), 0) AS EX_FACTORY_SALES,
	t.YEAR,
	t.PERIOD,
	ISNULL(S.SALES, 0) AS CONTRACT_SALES,
	ISNULL(S.UNITS, 0) AS CONTRACT_UNITS,
	ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0) AS TOTAL_DISCOUNT,
	ISNULL((ISNULL((ND.DISCOUNT), 0) + ISNULL((MD.DISCOUNT), 0) + ISNULL((SPD.DISCOUNT), 0)) / NULLIF((S.SALES), 0), 0) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
	(ISNULL(S.SALES / NULLIF(PFD.EX_FACTORY_SALES, 0), 0)) * 100 AS EX_FACTORY_SALES_PERCENT,
	0 AS PPA_DISCOUNT,
	0 AS PPA_DISCOUNT_PERCENTAGE,
	0 AS PPA_DISCOUNT_PROJECTED_PERCENTAGE,
	ISNULL(MD.DISCOUNT, 0) AS MANDATED_DISCOUNT,
	ISNULL(SPD.DISCOUNT, 0) AS SUPPLEMENTAL_DISCOUNT,
	COALESCE(MD.DISCOUNT / NULLIF((S.SALES), 0), 0) * 100 AS MANDATED_DISCOUNT_PERCENTAGE,
	COALESCE(SPD.DISCOUNT / NULLIF((S.SALES), 0), 0) * 100 AS SUPPLEMENTAL_DISCOUNT_PERCENTAGE,
	ISNULL(S.SALES, 0)  - (ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0)) AS NET_SALES,
	ISNULL(PFD.DEMAND_SALES, 0) AS DEMAND_SALES,
	ISNULL(PFD.IW_MASTER, 0) AS INVENTORY_WITHDRAWAL,
	(ISNULL(S.SALES / NULLIF((PFD.DEMAND_SALES), 0), 0)) * 100 AS DEMAND_SALES_PERCENT,
	(ISNULL(S.SALES / NULLIF((PFD.IW_MASTER), 0), 0)) * 100 AS INVENTORY_WITHDRAWAL_SALES_PERCENT,
	(ISNULL(ND.DISCOUNT / NULLIF((S.UNITS), 0), 0)) AS TOTAL_DISCOUNT_RPU,
	0 AS TOTAL_PPA_RPU,
	ISNULL((ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0)) / NULLIF(S.UNITS, 0), 0) AS TOTAL_RPU,
	ISNULL((S.COGS_VALUE), 0) AS COGS_VALUE,
	ISNULL(S.SALES, 0)  - (ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0))-ISNULL(S.COGS_VALUE, 0) AS NET_PROFIT,
	ISNULL(PFD.ADJUSTED_DEMAND, 0) AS ADJUSTED_DEMAND,
	ISNULL(CPFD.CUST_EX_FACTORY_SALES, 0) AS CUST_EX_FACTORY_SALES,
	ISNULL(CPFD.IW_DETAILS, 0) AS IW_DETAILS,
	ISNULL(S.SALES / NULLIF((PFD.ADJUSTED_DEMAND), 0), 0) * 100 AS ADJUSTED_DEMAND_PERCENTAGE,
	ISNULL(S.SALES / NULLIF((CPFD.CUST_EX_FACTORY_SALES), 0), 0) * 100 AS CUST_EX_FACTORY_SALES_PERCENTAGE,
	ISNULL(S.SALES / NULLIF((CPFD.IW_DETAILS), 0), 0) * 100 AS IW_DETAILS_PERCENTAGE,
	coalesce((ISNULL(S.SALES, 0)  - (ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0))) / NULLIF(pfd.EX_FACTORY_SALES, 0) , 0)* 100 AS NET_SALES_OF_EX_FACTORY,
	isnull((ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0)) / NULLIF(pfd.EX_FACTORY_SALES, 0) , 0)* 100 AS DISCOUNT_OF_EX_FACTORY,
	ISNULL((PFD.EX_FACTORY_SALES), 0) - (ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0)) AS NET_EXFACTORY_SALES,
	ISNULL(((ISNULL((PFD.EX_FACTORY_SALES), 0) - ISNULL((isnull((ND.DISCOUNT), 0) + ISNULL((SPD.DISCOUNT), 0) + ISNULL((MD.DISCOUNT), 0)), 0)) / NULLIF((PFD.EX_FACTORY_SALES), 0)), 0) * 100 AS NET_EXFACTORY_SALES_PERCENT,
	ISNULL((ND.ACCRUALS), 0) TOTAL_DISCOUNT_ACCRUAL,
	1 AS INDICATOR
FROM #trail t 
INNER JOIN #CFF_PROJECTION_MASTER CFM ON t.CFF_MASTER_SID = CFM.CFF_MASTER_SID
	left join #sales S  on S.PERIOD = t.PERIOD
	AND t.YEAR = S.YEAR
	AND t.CFF_MASTER_SID = S.CFF_MASTER_SID
	AND t.row_id = S.row_id
	AND S.INDICATOR = 0
LEFT JOIN #PRODUCT_FILE_DATA PFD ON PFD.PERIOD = t.PERIOD
	AND t.YEAR = PFD.YEAR
	AND t.CFF_MASTER_SID = PFD.CFF_MASTER_SID
	AND t.row_id = PFD.row_id
	AND PFD.INDICATOR = 0
LEFT JOIN #COMP_PRODUCT_FILE_DATA CPFD ON CPFD.PERIOD = t.PERIOD
	AND t.YEAR = CPFD.YEAR
	AND t.CFF_MASTER_SID = CPFD.CFF_MASTER_SID
	AND t.row_id = CPFD.row_id
	AND CPFD.INDICATOR = 0
LEFT JOIN #Disco ND ON ND.PERIOD = t.PERIOD
	AND t.YEAR = ND.YEAR
	AND t.CFF_MASTER_SID = ND.CFF_MASTER_SID
	AND t.row_id = ND.row_id
	and nd.INDICATOR=0 and nd.DISCOUNT_TYPE=0
LEFT JOIN #Disco MD ON MD.PERIOD = t.PERIOD
	AND t.YEAR = MD.YEAR
	AND t.CFF_MASTER_SID = MD.CFF_MASTER_SID
	AND t.row_id = MD.row_id
	and MD.INDICATOR=0 and MD.DISCOUNT_TYPE=1
LEFT JOIN #Disco SPD ON SPD.PERIOD = t.PERIOD
	AND t.YEAR = SPD.YEAR
	AND t.CFF_MASTER_SID = SPD.CFF_MASTER_SID
	AND t.row_id = SPD.row_id
	and SPD.INDICATOR=0 and SPD.DISCOUNT_TYPE=2
 where  t.CFF_MASTER_SID = @FIRST_PROJ_SID and t.PERIOD<@ACTUALS_END_SID and t.YEAR<=year(getdate())
UNION ALL

SELECT t.CFF_MASTER_SID,
	t.HIERARCHY_NO,
	ISNULL((PFD.EX_FACTORY_SALES), 0) AS EX_FACTORY_SALES,
	t.YEAR,
	t.PERIOD,
	ISNULL(S.SALES, 0) AS CONTRACT_SALES,
	ISNULL(S.UNITS, 0) AS CONTRACT_UNITS,
	ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0) AS TOTAL_DISCOUNT,
	ISNULL((ISNULL((ND.DISCOUNT), 0) + ISNULL((MD.DISCOUNT), 0) + ISNULL((SPD.DISCOUNT), 0)) / NULLIF((S.SALES), 0), 0) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
	(ISNULL(S.SALES / NULLIF(PFD.EX_FACTORY_SALES, 0), 0)) * 100 AS EX_FACTORY_SALES_PERCENT,
	0 AS PPA_DISCOUNT,
	0 AS PPA_DISCOUNT_PERCENTAGE,
	0 AS PPA_DISCOUNT_PROJECTED_PERCENTAGE,
	ISNULL(MD.DISCOUNT, 0) AS MANDATED_DISCOUNT,
	ISNULL(SPD.DISCOUNT, 0) AS SUPPLEMENTAL_DISCOUNT,
	COALESCE(MD.DISCOUNT / NULLIF((S.SALES), 0), 0) * 100 AS MANDATED_DISCOUNT_PERCENTAGE,
	COALESCE(SPD.DISCOUNT / NULLIF((S.SALES), 0), 0) * 100 AS SUPPLEMENTAL_DISCOUNT_PERCENTAGE,
	ISNULL(S.SALES, 0)  - (ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0)) AS NET_SALES,
	ISNULL(PFD.DEMAND_SALES, 0) AS DEMAND_SALES,
	ISNULL(PFD.IW_MASTER, 0) AS INVENTORY_WITHDRAWAL,
	(ISNULL(S.SALES / NULLIF((PFD.DEMAND_SALES), 0), 0)) * 100 AS DEMAND_SALES_PERCENT,
	(ISNULL(S.SALES / NULLIF((PFD.IW_MASTER), 0), 0)) * 100 AS INVENTORY_WITHDRAWAL_SALES_PERCENT,
	(ISNULL(ND.DISCOUNT / NULLIF((S.UNITS), 0), 0)) AS TOTAL_DISCOUNT_RPU,
	0 AS TOTAL_PPA_RPU,
	ISNULL((ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0)) / NULLIF(S.UNITS, 0), 0) AS TOTAL_RPU,
	ISNULL((S.COGS_VALUE), 0) AS COGS_VALUE,
	ISNULL(S.SALES, 0)  - (ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0))-ISNULL(S.COGS_VALUE, 0) AS NET_PROFIT,
	ISNULL(PFD.ADJUSTED_DEMAND, 0) AS ADJUSTED_DEMAND,
	ISNULL(CPFD.CUST_EX_FACTORY_SALES, 0) AS CUST_EX_FACTORY_SALES,
	ISNULL(CPFD.IW_DETAILS, 0) AS IW_DETAILS,
	ISNULL(S.SALES / NULLIF((PFD.ADJUSTED_DEMAND), 0), 0) * 100 AS ADJUSTED_DEMAND_PERCENTAGE,
	ISNULL(S.SALES / NULLIF((CPFD.CUST_EX_FACTORY_SALES), 0), 0) * 100 AS CUST_EX_FACTORY_SALES_PERCENTAGE,
	ISNULL(S.SALES / NULLIF((CPFD.IW_DETAILS), 0), 0) * 100 AS IW_DETAILS_PERCENTAGE,
	coalesce((ISNULL(S.SALES, 0)  - (ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0))) / NULLIF(pfd.EX_FACTORY_SALES, 0) , 0)* 100 AS NET_SALES_OF_EX_FACTORY,
	isnull((ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0)) / NULLIF(pfd.EX_FACTORY_SALES, 0) , 0)* 100 AS DISCOUNT_OF_EX_FACTORY,
	ISNULL((PFD.EX_FACTORY_SALES), 0) - (ISNULL(ND.DISCOUNT, 0) + ISNULL(MD.DISCOUNT, 0) + ISNULL(SPD.DISCOUNT, 0)) AS NET_EXFACTORY_SALES,
	ISNULL(((ISNULL((PFD.EX_FACTORY_SALES), 0) - ISNULL((isnull((ND.DISCOUNT), 0) + ISNULL((SPD.DISCOUNT), 0) + ISNULL((MD.DISCOUNT), 0)), 0)) / NULLIF((PFD.EX_FACTORY_SALES), 0)), 0) * 100 AS NET_EXFACTORY_SALES_PERCENT,
	ISNULL((ND.ACCRUALS), 0) TOTAL_DISCOUNT_ACCRUAL,
	0 AS INDICATOR
FROM  #trail t 
INNER JOIN #CFF_PROJECTION_MASTER CFM ON t.CFF_MASTER_SID = CFM.CFF_MASTER_SID	
	left join #sales S  on S.PERIOD = t.PERIOD
	AND t.YEAR = S.YEAR
	AND t.CFF_MASTER_SID = S.CFF_MASTER_SID
	AND t.row_id = S.row_id
	AND S.INDICATOR = 1
LEFT JOIN #PRODUCT_FILE_DATA PFD ON PFD.PERIOD = t.PERIOD
	AND t.YEAR = PFD.YEAR
	AND t.CFF_MASTER_SID = PFD.CFF_MASTER_SID
	AND t.row_id = PFD.row_id
	AND PFD.INDICATOR = 1
LEFT JOIN #COMP_PRODUCT_FILE_DATA CPFD ON CPFD.PERIOD = t.PERIOD
	AND t.YEAR = CPFD.YEAR
	AND t.CFF_MASTER_SID = CPFD.CFF_MASTER_SID
	AND t.row_id = CPFD.row_id
	AND CPFD.INDICATOR = 1
LEFT JOIN #Disco ND ON ND.PERIOD = t.PERIOD
	AND t.YEAR = ND.YEAR
	AND t.CFF_MASTER_SID = ND.CFF_MASTER_SID
	AND t.row_id = ND.row_id
	and nd.INDICATOR=1 and nd.DISCOUNT_TYPE=0
LEFT JOIN #Disco MD ON MD.PERIOD = t.PERIOD
	AND t.YEAR = MD.YEAR
	AND t.CFF_MASTER_SID = MD.CFF_MASTER_SID
	AND t.row_id = MD.row_id
	and MD.INDICATOR=1 and MD.DISCOUNT_TYPE=1
LEFT JOIN #Disco SPD ON SPD.PERIOD = t.PERIOD
	AND t.YEAR = SPD.YEAR
	AND t.CFF_MASTER_SID = SPD.CFF_MASTER_SID
	AND t.row_id = SPD.row_id
	and SPD.INDICATOR=1 and SPD.DISCOUNT_TYPE=2
		--order  BY S.CFF_MASTER_SID,
  --           s.HIERARCHY_NO,
	 --        S.YEAR,
		--	 S.PERIOD
	end 


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
          ) 
      END CATCH 
  END 
GO