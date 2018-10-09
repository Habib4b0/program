IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_CFF_DISCOUNT_EXCEL_EXPORT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_CFF_DISCOUNT_EXCEL_EXPORT]
  END

GO

CREATE PROCEDURE [dbo].[PRC_CFF_DISCOUNT_EXCEL_EXPORT]       ( @CFF_MASTER_SID VARCHAR(500),
                                                               @PROJ_FREQUENCY VARCHAR(20),
															   @PROGRAM VARCHAR(50),
															   @SALES_INCLUSION BIT,
															   @DEDUCTION_INCLUSION BIT,
															   @DISCOUNT NVARCHAR(MAX),
															   @CUSTOM_VIEW_MASTER_SID INT,
															   @SESSION_ID VARCHAR(50),
															   @USER_ID INT,
															   @INDICATOR CHAR(1),
															   @LEVEL_NO SMALLINT)

AS	                                                        
BEGIN 
SET NOCOUNT ON 

BEGIN TRY 

 
  DECLARE @CFF_PRODUCT           VARCHAR(200) = Concat ('ST_CFF_PRODUCT_LEVEL_FILES_DATA_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),----Product Level data
        @CFF_COMP_PROD         VARCHAR(200) = Concat ('ST_CFF_COMP_PRD_LEVEL_FILES_DATA_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),  ---- Comp+Prod data
		@CCP_HIERARCHY         VARCHAR(200) = Concat ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')), ---- ccp deatils information
		@CUSTOMER_CFF_SALES VARCHAR(200) = Concat('ST_CUSTOMER_CFF_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
		@PRODUCT_CFF_SALES VARCHAR(200) = Concat('ST_PRODUCT_CFF_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
		@CUSTOM_CFF_SALES VARCHAR(200) = Concat('ST_CUSTOM_CFF_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
		@CUSTOMER_CFF_DISCOUNT VARCHAR(200) = Concat('ST_CUSTOMER_CFF_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
		@PRODUCT_CFF_DISCOUNT VARCHAR(200) = Concat('ST_PRODUCT_CFF_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
		@CUSTOM_CFF_DISCOUNT VARCHAR(200) = Concat('ST_CUSTOM_CFF_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
		@CUSTOM_CCP_SALES VARCHAR(200) = CONCAT ('CUSTOM_CCP_SALES_',@CUSTOM_VIEW_MASTER_SID),
		@CUSTOMER_DETAILS_TEMP varchar(200),
		@CUSTOMER_CFF_HIERARCHY_DETAILS VARCHAR(200) = CONCAT('ST_CUSTOMER_CFF_HIERARCHY_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@PRODUCT_CFF_HIERARCHY_DETAILS VARCHAR(200) = CONCAT('ST_PRODUCT_CFF_HIERARCHY_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@CUSTOM_CFF_HIERARCHY_DETAILS VARCHAR(200) = CONCAT('ST_CUSTOM_CFF_HIERARCHY_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@sales_table            VARCHAR(MAX),
        @discount_table         VARCHAR(200),
		@ROW_ID INT   
DECLARE @DEDUCTION_TABLE  VARCHAR(200)= CONCAT('ST_CCP_DEDUCTION_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))

 DECLARE @ACTUAL_START_DATE DATETIME = CAST(YEAR(GETDATE()) - 3 AS VARCHAR(4)) + '-01-01',
         @PROJ_END_PERIOD_DATE DATETIME	

SELECT TOP 1 @PROJ_END_PERIOD_DATE = DATEADD(DD, 1, EOMONTH(TO_DATE, - 1))
FROM FORECAST_CONFIG FA
LEFT JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FA.BUSINESS_PROCESS_TYPE
WHERE [DESCRIPTION] = 'CONSOLIDATED FINANCIAL FORECAST'
ORDER BY VERSION_NO DESC

DECLARE @FROM_PERIOD_SID INT,@END_PERIOD_SID INT

 set @FROM_PERIOD_SID  = (
		SELECT PERIOD_SID
		FROM PERIOD
		WHERE PERIOD_DATE = @ACTUAL_START_DATE
		)
		
set @END_PERIOD_SID  = (
		SELECT PERIOD_SID
		FROM PERIOD
		WHERE PERIOD_DATE = @PROJ_END_PERIOD_DATE
		)	
		-----------------PERIOD CONVERISON--------------------
IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
	DROP TABLE #PERIOD;

SELECT PERIOD_SID,
	YEAR,
	CASE 
		WHEN LEFT(@PROJ_FREQUENCY, 1) = 'M'
			THEN MONTH
		WHEN LEFT(@PROJ_FREQUENCY, 1) = 'Q'
			THEN QUARTER
		WHEN LEFT(@PROJ_FREQUENCY, 1) = 'S'
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
	
declare @END_SALES_SID int = (
		SELECT PERIOD_SID
		FROM PERIOD
		WHERE PERIOD_DATE = DATEADD(MM, - 3, DATEADD(QQ, DATEDIFF(QQ, 0, GETDATE()), 0))
		)	
--------------------TAKING TABLES BASED ON VIEW STARTS HERE
 IF @indicator = 'c'
            BEGIN
                SET @sales_table=@CUSTOMER_CFF_SALES
                SET @discount_table=@CUSTOMER_CFF_DISCOUNT
				set @CUSTOMER_DETAILS_TEMP=@CUSTOMER_CFF_HIERARCHY_DETAILS
            END
          ELSE IF @indicator = 'P'
            BEGIN
                SET @sales_table=@PRODUCT_CFF_SALES
                SET @discount_table=@PRODUCT_CFF_DISCOUNT
				set @CUSTOMER_DETAILS_TEMP=@PRODUCT_CFF_HIERARCHY_DETAILS
            END
          ELSE IF @indicator NOT IN ( 'c', 'p' )
            BEGIN
                SET @sales_table=@CUSTOM_CFF_SALES
                SET @discount_table=@CUSTOM_CFF_DISCOUNT
				set @CUSTOMER_DETAILS_TEMP=@CUSTOM_CFF_HIERARCHY_DETAILS
            END

             
         DECLARE @SQL NVARCHAR(MAX)			 
			
		    IF Object_id('TEMPDB..#RS_INFO') IS NOT NULL
            DROP TABLE #RS_INFO

          CREATE TABLE #RS_INFO
            (
		   [DESCRIPTION] varchar(200),
           SELECTED_LEVEL  INT
            )

          SET @SQL=Concat('SELECT distinct  ', CASE @PROGRAM
              WHEN 'SCHEDULE ID' THEN 'R.RS_ID '
              WHEN 'PROGRAM' THEN ' R.RS_ID '
              WHEN 'SCHEDULE TYPE' THEN ' R_TYPE.DESCRIPTION '
              WHEN 'PROGRAM CATEGORY' THEN  ' RPT.DESCRIPTION '
              WHEN 'PROGRAM TYPE' THEN ' RPT.DESCRIPTION '
              WHEN 'SCHEDULE CATEGORY' THEN ' RC.DESCRIPTION  '
              WHEN 'UDC1' THEN ' UD1.UDC1 '
              WHEN 'UDC2' THEN ' UD1.UDC2 '
              WHEN 'UDC3' THEN ' UD1.UDC3 '
              WHEN 'UDC4' THEN ' UD1.UDC4 '
              WHEN 'UDC5' THEN ' UD1.UDC5 '
              WHEN 'UDC6' THEN ' UD1.UDC6 '
            END,' as [DESCRIPTION],
            ',CASE @PROGRAM
              WHEN 'SCHEDULE ID' THEN ' R.RS_CONTRACT_SID '
              WHEN 'PROGRAM' THEN ' R.RS_CONTRACT_SID '                              
              WHEN 'SCHEDULE TYPE' THEN ' RS_TYPE '
              WHEN 'PROGRAM CATEGORY' THEN ' REBATE_PROGRAM_TYPE '
              WHEN 'PROGRAM TYPE' THEN ' REBATE_PROGRAM_TYPE '
              WHEN 'SCHEDULE CATEGORY' THEN ' RS_CATEGORY '
              WHEN 'UDC1' THEN ' UD.UDC1 '
              WHEN 'UDC2' THEN ' UD.UDC2 '
              WHEN 'UDC3' THEN ' UD.UDC3 '
              WHEN 'UDC4' THEN ' UD.UDC4 '
              WHEN 'UDC5' THEN ' UD.UDC5 '
              WHEN 'UDC6' THEN ' UD.UDC6 '
            END,' SELECTED_LEVEL
     FROM   RS_CONTRACT R
            LEFT JOIN HELPER_TABLE R_TYPE
                   ON R_TYPE.HELPER_TABLE_SID = R.RS_TYPE
            LEFT JOIN HELPER_TABLE RPT
                   ON RPT.HELPER_TABLE_SID = R.REBATE_PROGRAM_TYPE
            LEFT JOIN HELPER_TABLE RC
                   ON RC.HELPER_TABLE_SID = R.RS_CATEGORY
            LEFT JOIN (SELECT U.MASTER_SID,
                              U1.DESCRIPTION AS UDC1,
                              U2.DESCRIPTION AS UDC2,
                              U3.DESCRIPTION AS UDC3,
                              U4.DESCRIPTION AS UDC4,
                              U5.DESCRIPTION AS UDC5,
                              U6.DESCRIPTION UDC6
                       FROM   UDCS U
                              LEFT JOIN HELPER_TABLE U1
                                     ON U1.HELPER_TABLE_SID = U.UDC1
                              LEFT JOIN HELPER_TABLE U2
                                    ON U2.HELPER_TABLE_SID = U.UDC2
                              LEFT JOIN HELPER_TABLE U3
                                     ON U3.HELPER_TABLE_SID = U.UDC3
                              LEFT JOIN HELPER_TABLE U4
                                     ON U4.HELPER_TABLE_SID = U.UDC4
                              LEFT JOIN HELPER_TABLE U5
                                     ON U5.HELPER_TABLE_SID = U.UDC5
                              LEFT JOIN HELPER_TABLE U6
                                     ON U6.HELPER_TABLE_SID = U.UDC6
                       WHERE  MASTER_TYPE = ''RS_CONTRACT'') UD1
					   ON UD1.MASTER_SID=R.RS_CONTRACT_SID

                                                INNER JOIN ', @DEDUCTION_TABLE, ' D
                    ON  R.RS_CONTRACT_SID = D.RS_CONTRACT_SID 
                                                LEFT JOIN UDCS UD
                   ON UD.MASTER_SID = R.RS_CONTRACT_SID
                                                                   and MASTER_TYPE = ''RS_CONTRACT''    
                                                WHERE exists (select 1 from [UDF_SPLITSTRING](@DISCOUNT, '','') hd where hd.token=R.RS_CONTRACT_SID
                                                ) or nullif(@DISCOUNT,'''') is null')
												

   
	  INSERT INTO #RS_INFO
          EXEC Sp_executesql
            @sql,
            N'@DISCOUNT varchar(max),@PROGRAM varcHar(100)',
            @DISCOUNT=@DISCOUNT,
            @PROGRAM=@PROGRAM 

 
IF OBJECT_ID('TEMPDB..#HIERARCHY_NO','U') IS NOT NULL
    DROP TABLE #HIERARCHY_NO
CREATE TABLE #HIERARCHY_NO
(
HIERARCHY_NO VARCHAR(1000),row_id int
)

SET @sql=CONCAT('','
INSERT INTO #HIERARCHY_NO

SELECT distinct HIERARCHY_NO,row_id FROM ',@CUSTOMER_DETAILS_TEMP,'')


EXEC(@sql)
 
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
INDICATOR BIT
)
SET @sql=CONCAT('
INSERT INTO #PRODUCT_FILE_DATA (CFF_MASTER_SID,ROW_ID,YEAR,PERIOD,EX_FACTORY_SALES
,INDICATOR)
SELECT CF.CFF_MASTER_SID
	,PRO.ROW_ID
	,YEAR
	,PERIOD
	,SUM(EX_FACTORY_SALES_PROJ) AS EX_FACTORY_SALES
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
  EXEC Sp_executesql @sql
SET @sql=CONCAT('
INSERT INTO #PRODUCT_FILE_DATA (CFF_MASTER_SID,ROW_ID,YEAR,PERIOD,EX_FACTORY_SALES
,INDICATOR)
SELECT CF.CFF_MASTER_SID
	,PRO.ROW_ID
	,YEAR
	,PERIOD
	,SUM(EX_FACTORY_SALES_ACTUALS) AS EX_FACTORY_SALES
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

	EXEC Sp_executesql @sql

	
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
DISCOUNT_TYPE tinyint,rs_contract_Sid int, sales numeric(22,6),units numeric(22,6)
)		
	set @sql=concat('insert into #disco
	SELECT ND.CFF_MASTER_SID,hr.row_id,	
		ND.YEAR,ND.PERIOD,		
		SUM(ND.ACCRUALS) AS ACCRUALS,
		SUM(ND.DISCOUNT) AS DISCOUNT,INDICATOR,DISCOUNT_TYPE,nd.rs_contract_Sid,sum(SALES) SALES,sum(UNITS) UNITS
	FROM #HIERARCHY_NO HR
	INNER JOIN ',@DISCOUNT_TABLE,' ND ON ',case when  @INDICATOR IN ('C','P') then ' ND.HIERARCHY_NO LIKE HR.HIERARCHY_NO + ''%''' else 'ND.HIERARCHY_NO =hr.row_id '  end,'
		AND ND.FILTER_CCP = 1	
		',case when @DEDUCTION_INCLUSION IS not NULL then concat('	AND ND.DEDUCTION_INCLUSION = ',@DEDUCTION_INCLUSION, '   ' ) end,'
			where ND.DISCOUNT is not null and  ND.DISCOUNT<>0
	GROUP BY ND.CFF_MASTER_SID,hr.row_id,ND.PERIOD,
		ND.YEAR,INDICATOR,DISCOUNT_TYPE,nd.rs_contract_Sid')

		EXEC Sp_executesql @sql
declare @ACTUALS_END_SID int = (
		SELECT top 1 PERIOD
		FROM #PERIOD
		WHERE PERIOD_SID = @END_SALES_SID
		)	
		if OBJECT_ID('tempdb..#trail') is not null
	drop table #trail
	select CFF_MASTER_SID,HIERARCHY_NO HIERARCHY_NO,period,year,row_id ,SELECTED_LEVEL,DESCRIPTION
	into #trail
	from #HIERARCHY_NO h cross join #period p cross join #CFF_PROJECTION_MASTER cpm
	cross join #RS_INFO
	group by CFF_MASTER_SID,HIERARCHY_NO,period,year,row_id ,SELECTED_LEVEL,DESCRIPTION
	---412160----424994
	select t.CFF_MASTER_SID,
	t.HIERARCHY_NO,
	t.YEAR,
	t.PERIOD,
	(ND.DISCOUNT) + isnull((MD.DISCOUNT),0) + isnull((SPD.DISCOUNT),0) AS TOTAL_DISCOUNT,
	(((ND.DISCOUNT) + isnull((MD.DISCOUNT),0) + isnull((SPD.DISCOUNT),0)) / NULLIF((nd.SALES), 0)) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
	(MD.DISCOUNT) AS MANDATED_DISCOUNT,
	(SPD.DISCOUNT) AS SUPPLEMENTAL_DISCOUNT,
	((MD.DISCOUNT) / NULLIF((nd.SALES), 0)) * 100 AS MANDATED_DISCOUNT_PERCENTAGE,
	((SPD.DISCOUNT) / NULLIF((nd.SALES), 0)) * 100 AS SUPPLEMENTAL_DISCOUNT_PERCENTAGE,
	(ND.DISCOUNT) / NULLIF((nd.UNITS), 0) AS TOTAL_DISCOUNT_RPU,
	((ND.DISCOUNT) + isnull((MD.DISCOUNT),0) + isnull((SPD.DISCOUNT),0)) / NULLIF((nd.UNITS), 0) AS TOTAL_RPU,
	(((ND.DISCOUNT) + isnull((MD.DISCOUNT),0) + isnull((SPD.DISCOUNT),0)) / NULLIF((p.EX_FACTORY_SALES), 0)) * 100 AS DISCOUNT_OF_EX_FACTORY,
	(ND.ACCRUALS) TOTAL_DISCOUNT_ACCRUAL,
	0 AS INDICATOR,
	t.DESCRIPTION
	 from #trail t left join #PRODUCT_FILE_DATA p on p.CFF_MASTER_SID=t.CFF_MASTER_SID and p.ROW_ID=t.row_id
	and p.PERIOD=t.PERIOD
	and p.YEAR=t.YEAR
	and p.INDICATOR=0
	left join #disco nd on nd.CFF_MASTER_SID=t.CFF_MASTER_SID and nd.ROW_ID=t.row_id
	and nd.PERIOD=t.PERIOD
	and nd.YEAR=t.YEAR
	and nd.INDICATOR=0
	and nd.rs_contract_Sid=t.SELECTED_LEVEL
	and nd.DISCOUNT_TYPE=0
	left join #disco md on md.CFF_MASTER_SID=t.CFF_MASTER_SID and md.ROW_ID=t.row_id
	and md.PERIOD=t.PERIOD
	and md.YEAR=t.YEAR
	and md.INDICATOR=0
	and md.rs_contract_Sid=t.SELECTED_LEVEL
	and md.DISCOUNT_TYPE=1
	left join #disco spd on spd.CFF_MASTER_SID=t.CFF_MASTER_SID and spd.ROW_ID=t.row_id
	and spd.PERIOD=t.PERIOD
	and spd.YEAR=t.YEAR
	and spd.INDICATOR=0
	and spd.rs_contract_Sid=t.SELECTED_LEVEL
	and spd.DISCOUNT_TYPE=2
	where t.CFF_MASTER_SID=@FIRST_PROJ_SID and t.PERIOD<@ACTUALS_END_SID and t.YEAR<=year(getdate())
	union all
	select t.CFF_MASTER_SID,
	t.HIERARCHY_NO,
	t.YEAR,
	t.PERIOD,
	(ND.DISCOUNT) + isnull((MD.DISCOUNT),0) + isnull((SPD.DISCOUNT),0) AS TOTAL_DISCOUNT,
	(((ND.DISCOUNT) + isnull((MD.DISCOUNT),0) + isnull((SPD.DISCOUNT),0)) / NULLIF((nd.SALES), 0)) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
	(MD.DISCOUNT) AS MANDATED_DISCOUNT,
	(SPD.DISCOUNT) AS SUPPLEMENTAL_DISCOUNT,
	((MD.DISCOUNT) / NULLIF((nd.SALES), 0)) * 100 AS MANDATED_DISCOUNT_PERCENTAGE,
	((SPD.DISCOUNT) / NULLIF((nd.SALES), 0)) * 100 AS SUPPLEMENTAL_DISCOUNT_PERCENTAGE,
	(ND.DISCOUNT) / NULLIF((nd.UNITS), 0) AS TOTAL_DISCOUNT_RPU,
	((ND.DISCOUNT) + isnull((MD.DISCOUNT),0) + isnull((SPD.DISCOUNT),0)) / NULLIF((nd.UNITS), 0) AS TOTAL_RPU,
	(((ND.DISCOUNT) + isnull((MD.DISCOUNT),0) + isnull((SPD.DISCOUNT),0)) / NULLIF((p.EX_FACTORY_SALES), 0)) * 100 AS DISCOUNT_OF_EX_FACTORY,
	(ND.ACCRUALS) TOTAL_DISCOUNT_ACCRUAL,
	1 AS INDICATOR,
	t.DESCRIPTION
	 from #trail t left join #PRODUCT_FILE_DATA p on p.CFF_MASTER_SID=t.CFF_MASTER_SID and p.ROW_ID=t.row_id
	and p.PERIOD=t.PERIOD
	and p.YEAR=t.YEAR
	and p.INDICATOR=1
	left join #disco nd on nd.CFF_MASTER_SID=t.CFF_MASTER_SID and nd.ROW_ID=t.row_id
	and nd.PERIOD=t.PERIOD
	and nd.YEAR=t.YEAR
	and nd.INDICATOR=1
	and nd.rs_contract_Sid=t.SELECTED_LEVEL
	and nd.DISCOUNT_TYPE=0
	left join #disco md on md.CFF_MASTER_SID=t.CFF_MASTER_SID and md.ROW_ID=t.row_id
	and md.PERIOD=t.PERIOD
	and md.YEAR=t.YEAR
	and md.INDICATOR=1
	and md.rs_contract_Sid=t.SELECTED_LEVEL
	and md.DISCOUNT_TYPE=1
	left join #disco spd on spd.CFF_MASTER_SID=t.CFF_MASTER_SID and spd.ROW_ID=t.row_id
	and spd.PERIOD=t.PERIOD
	and spd.YEAR=t.YEAR
	and spd.INDICATOR=1
	and spd.rs_contract_Sid=t.SELECTED_LEVEL
	and spd.DISCOUNT_TYPE=2

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

	      
	