IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_CFF_PROJECTION_RESULTS_DISCOUNT'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_CFF_PROJECTION_RESULTS_DISCOUNT]
  END

GO


CREATE PROCEDURE [dbo].[PRC_CFF_PROJECTION_RESULTS_DISCOUNT] @CFF_MASTER_SID VARCHAR(500),
	                                                         @PROJ_FREQUENCY VARCHAR(20),
	                                                         @FROM_PERIOD_SID INT,
	                                                         @END_PERIOD_SID INT,
	                                                         @PROGRAM VARCHAR(50) =NULL,
	                                                         @SALES_INCLUSION BIT =NULL ,
	                                                         @DEDUCTION_INCLUSION BIT = NULL,
	                                                         @DISCOUNT NVARCHAR(MAX) = NULL,
	                                                         @CUSTOM_VIEW_MASTER_SID INT = NULL,
	                                                         --@CUSTOM_LEVEL_NO INT = NULL,
	                                                         @SESSION_ID VARCHAR(50),
	                                                         @USER_ID INT,
	                                                         @INDICATOR CHAR(1),
	                                                         @HIERARCHY_NO VARCHAR(100)
WITH EXEC AS CALLER
AS

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
		@sales_table            VARCHAR(200),
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

--------------------TAKING TABLES BASED ON VIEW STARTS HERE
 IF @indicator = 'c'
            BEGIN
                SET @sales_table=@CUSTOMER_CFF_SALES
                SET @discount_table=@CUSTOMER_CFF_DISCOUNT
            END
          ELSE IF @indicator = 'P'
            BEGIN
                SET @sales_table=@PRODUCT_CFF_SALES
                SET @discount_table=@PRODUCT_CFF_DISCOUNT
            END
          ELSE IF @indicator NOT IN ( 'c', 'p' )
            BEGIN
                SET @sales_table=@CUSTOM_CFF_SALES
                SET @discount_table=@CUSTOM_CFF_DISCOUNT
            END

             
         DECLARE @SQL NVARCHAR(MAX)			 
			
		    IF Object_id('TEMPDB..#RS_INFO') IS NOT NULL
            DROP TABLE #RS_INFO

          CREATE TABLE #RS_INFO
            (
		   [DESCRIPTION] varchar(200),
           SELECTED_LEVEL  INT 
            )

          SET @SQL=Concat('SELECT distinct  CASE @PROGRAM
              WHEN ''SCHEDULE ID'' THEN R.RS_ID
              WHEN ''PROGRAM'' THEN R.RS_ID
              WHEN ''SCHEDULE TYPE'' THEN R_TYPE.DESCRIPTION
              WHEN ''PROGRAM CATEGORY'' THEN RPT.DESCRIPTION
              WHEN ''PROGRAM TYPE'' THEN RPT.DESCRIPTION
              WHEN ''SCHEDULE CATEGORY'' THEN RC.DESCRIPTION 
              WHEN ''UDC1'' THEN UD1.UDC1
              WHEN ''UDC2'' THEN UD1.UDC2
              WHEN ''UDC3'' THEN UD1.UDC3
              WHEN ''UDC4'' THEN UD1.UDC4
              WHEN ''UDC5'' THEN UD1.UDC5
              WHEN ''UDC6'' THEN UD1.UDC6
            END as [DESCRIPTION],
            CASE @PROGRAM
              WHEN ''SCHEDULE ID'' THEN R.RS_CONTRACT_SID
              WHEN ''PROGRAM'' THEN R.RS_CONTRACT_SID                               
              WHEN ''SCHEDULE TYPE'' THEN RS_TYPE
              WHEN ''PROGRAM CATEGORY'' THEN REBATE_PROGRAM_TYPE
              WHEN ''PROGRAM TYPE'' THEN REBATE_PROGRAM_TYPE
              WHEN ''SCHEDULE CATEGORY'' THEN RS_CATEGORY
              WHEN ''UDC1'' THEN UD.UDC1
              WHEN ''UDC2'' THEN UD.UDC2
              WHEN ''UDC3'' THEN UD.UDC3
              WHEN ''UDC4'' THEN UD.UDC4
              WHEN ''UDC5'' THEN UD.UDC5
              WHEN ''UDC6'' THEN UD.UDC6
            END SELECTED_LEVEL
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
		 
IF Object_id('TEMPDB..#CUSTOMER_DETAILS_TEMP') IS NOT NULL 
 DROP TABLE #CUSTOMER_DETAILS_TEMP

CREATE TABLE #CUSTOMER_DETAILS_TEMP
( 
ITEM_MASTER_SID     INT,
HIERARCHY_NO        VARCHAR(8000)
,ROW_ID int
)


 DECLARE @SQL_C NVARCHAR(MAX)

IF @indicator IN ( 'c', 'p' )
            BEGIN
SET @SQL_C = CONCAT('INSERT INTO #CUSTOMER_DETAILS_TEMP (
	ITEM_MASTER_SID
	 ,HIERARCHY_NO
	)
SELECT DISTINCT 
	 ITEM_MASTER_SID,
	@HIERARCHY_NO  AS HIERARCHY_NO
FROM ', @CCP_HIERARCHY, ' CH
JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID
 WHERE ', CASE @indicator
                                         WHEN 'C' THEN 'ch.CUST_HIERARCHY_NO'
                                         WHEN 'P' THEN 'ch.PROD_HIERARCHY_NO'
                                       END, ' like @HIERARCHY_NO +''%''')

		EXEC Sp_executesql @SQL_C,
		N'@HIERARCHY_NO VARCHAR(200)',
		@HIERARCHY_NO=@HIERARCHY_NO

	END


 IF @indicator NOT IN ( 'c', 'p' )
            BEGIN
                SET @SQL_C = Concat('INSERT INTO #CUSTOMER_DETAILS_TEMP (
	ITEM_MASTER_SID,
     HIERARCHY_NO,row_id
	)
SELECT DISTINCT 
	ITEM_MASTER_SID
	 ,c.HIERARCHY_NO
	,rowid 
FROM ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' ch
                     on  ch.CCP_DETAILS_SID = c.CCP_DETAILS_SID
JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID

			--WHERE HIERARCHY_NO  LIKE @HIERARCHY_NO +''%''
			WHERE HIERARCHY_NO  = @HIERARCHY_NO 
AND  C.CUST_VIEW_MASTER_SID= @CUSTOM_VIEW_MASTER_SID  
                       
 ')

EXEC Sp_executesql @SQL_C,
		N'@HIERARCHY_NO VARCHAR(200),@CUSTOM_VIEW_MASTER_SID INT',
		@HIERARCHY_NO=@HIERARCHY_NO,
		@CUSTOM_VIEW_MASTER_SID=@CUSTOM_VIEW_MASTER_SID
	

END

 
          
 
-----------------Taking file Values including Prior CFF'S

DECLARE @SQL_PRO NVARCHAR(MAX)
IF OBJECT_ID('TEMPDB..#PRODUCT_FILE_DATA') IS NOT NULL
DROP TABLE #PRODUCT_FILE_DATA
CREATE TABLE #PRODUCT_FILE_DATA
(
CFF_MASTER_SID INT,
HIERARCHY_NO VARCHAR(8000),
ROW_ID INT,
YEAR INT,
PERIOD SMALLINT,
EX_FACTORY_SALES NUMERIC(22,6),
--DEMAND_SALES NUMERIC(22,6),
--ADJUSTED_DEMAND  NUMERIC(22,6),
--IW_MASTER NUMERIC(22,6),
INDICATOR BIT
)

SET @SQL_PRO=CONCAT('
INSERT INTO #PRODUCT_FILE_DATA (CFF_MASTER_SID,HIERARCHY_NO,ROW_ID,YEAR,PERIOD,EX_FACTORY_SALES,INDICATOR)
SELECT      
            CF.CFF_MASTER_SID
			,PRO.HIERARCHY_NO
			,PRO.ROW_ID
            ,YEAR
			,PERIOD
			,SUM(EX_FACTORY_SALES_ACTUALS) AS EX_FACTORY_SALES
			,0 AS INDICATOR
		FROM  #CFF_PROJECTION_MASTER CFM 
	    CROSS JOIN  #CUSTOMER_DETAILS_TEMP PRO
		CROSS JOIN #PERIOD P 
		LEFT JOIN ', @CFF_PRODUCT ,' CF ON CF.CFF_MASTER_SID=CFM.CFF_MASTER_SID AND CF.CFF_MASTER_SID = @FIRST_PROJ_SID
		AND P.PERIOD_SID = CF.PERIOD_SID AND CF.ITEM_MASTER_SID=PRO.ITEM_MASTER_SID
		GROUP BY YEAR
			,PERIOD
			,cf.CFF_MASTER_SID
			,PRO.HIERARCHY_NO
			,PRO.ROW_ID
  UNION ALL

  SELECT    CF.CFF_MASTER_SID
            ,PRO.HIERARCHY_NO
			,PRO.ROW_ID
            ,YEAR
			,PERIOD
			,SUM(EX_FACTORY_SALES_PROJ) AS EX_FACTORY_SALES
			, 1 AS INDICATOR
		FROM #CFF_PROJECTION_MASTER CFM 
	    CROSS JOIN (SELECT DISTINCT ITEM_MASTER_SID,HIERARCHY_NO,ROW_ID FROM #CUSTOMER_DETAILS_TEMP) PRO
		CROSS JOIN #PERIOD P 
		LEFT JOIN ', @CFF_PRODUCT ,' CF ON CF.CFF_MASTER_SID=CFM.CFF_MASTER_SID AND P.PERIOD_SID = CF.PERIOD_SID 
		AND CF.ITEM_MASTER_SID=PRO.ITEM_MASTER_SID
		GROUP BY YEAR
			,PERIOD
			,CF.CFF_MASTER_SID
			,PRO.HIERARCHY_NO
			,PRO.ROW_ID')

			EXEC Sp_executesql @SQL_PRO,
			N'@FIRST_PROJ_SID int',
			@FIRST_PROJ_SID=@FIRST_PROJ_SID

IF @INDICATOR IN ('C','P')
BEGIN
DECLARE @SQL_OUT NVARCHAR(MAX)
--product_cff_discount
SET @SQL_OUT=CONCAT('
	SELECT S.CFF_MASTER_SID,
	S.YEAR,
	S.PERIOD,
	SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0) AS TOTAL_DISCOUNT,
	((SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(S.SALES), 0)) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
	SUM(MD.DISCOUNT) AS MANDATED_DISCOUNT,
	SUM(SPD.DISCOUNT) AS SUPPLEMENTAL_DISCOUNT,
	(SUM(MD.DISCOUNT) / NULLIF(SUM(S.SALES), 0)) * 100 AS MANDATED_DISCOUNT_PERCENTAGE,
	(SUM(SPD.DISCOUNT) / NULLIF(SUM(S.SALES), 0)) * 100 AS SUPPLEMENTAL_DISCOUNT_PERCENTAGE,
	SUM(ND.DISCOUNT) / NULLIF(SUM(S.UNITS), 0) AS TOTAL_DISCOUNT_RPU,
	(SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(S.UNITS), 0) AS TOTAL_RPU,
	((SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(pfd.EX_FACTORY_SALES), 0)) * 100 AS DISCOUNT_OF_EX_FACTORY,
	SUM(ND.ACCRUALS) TOTAL_DISCOUNT_ACCRUAL,ND.DESCRIPTION,
	0 AS INDICATOR


	FROM (select  @HIERARCHY_NO HIERARCHY_NO,A.PERIOD,A.YEAR,sum(A.SALES) SALES ,sum(A.UNITS) UNITS,
	A.CFF_MASTER_SID from ', @sales_table ,' A
	where a.HIERARCHY_NO like @HIERARCHY_NO +''%''
	AND A.INDICATOR=0
	AND (SALES_INCLUSION = @SALES_INCLUSION  OR @SALES_INCLUSION IS NULL) AND A.FILTER_CCP = 1
	group by A.PERIOD,A.YEAR,A.CFF_MASTER_SID
	) S

	INNER JOIN #CFF_PROJECTION_MASTER CFM ON S.CFF_MASTER_SID=CFM.CFF_MASTER_SID AND S.CFF_MASTER_SID = @FIRST_PROJ_SID 
	LEFT JOIN #PRODUCT_FILE_DATA PFD 
	ON PFD.PERIOD = S.PERIOD AND S.YEAR = PFD.YEAR AND S.CFF_MASTER_SID = PFD.CFF_MASTER_SID 
	AND S.HIERARCHY_NO = PFD.HIERARCHY_NO  AND PFD.INDICATOR=0

	LEFT JOIN ( SELECT @HIERARCHY_NO HIERARCHY_NO,ND.PERIOD,ND.YEAR,SUM(ND.SALES) SALES,SUM(ND.UNITS) AS UNITS,DESCRIPTION,
	ND.CFF_MASTER_SID,SUM(ND.ACCRUALS) AS ACCRUALS,SUM(ND.DISCOUNT) DISCOUNT FROM  ' , @discount_table ,' ND LEFT join #RS_INFO B ON ND.RS_CONTRACT_SID=B.SELECTED_LEVEL
	WHERE ND.HIERARCHY_NO like @HIERARCHY_NO+''%'' AND ND.INDICATOR=0 AND ND.DISCOUNT_TYPE=0
	 AND (ND.DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION  OR @DEDUCTION_INCLUSION IS NULL)  AND ND.FILTER_CCP = 1
	 GROUP BY ND.PERIOD,ND.YEAR,ND.CFF_MASTER_SID,DESCRIPTION ) ND

	ON ND.PERIOD = S.PERIOD AND S.YEAR = ND.YEAR AND S.CFF_MASTER_SID = ND.CFF_MASTER_SID AND S.HIERARCHY_NO=ND.HIERARCHY_NO 
	LEFT JOIN (SELECT @HIERARCHY_NO HIERARCHY_NO,MD.PERIOD,MD.YEAR,SUM(MD.SALES) SALES,SUM(MD.UNITS) AS UNITS,DESCRIPTION,
	MD.CFF_MASTER_SID,SUM(MD.ACCRUALS) AS ACCRUALS,SUM(MD.DISCOUNT) DISCOUNT FROM  ' , @discount_table ,' MD LEFT join #RS_INFO B ON md.RS_CONTRACT_SID=B.SELECTED_LEVEL
	WHERE MD.HIERARCHY_NO like @HIERARCHY_NO+''%'' AND MD.INDICATOR=0 AND MD.DISCOUNT_TYPE=1
	 AND (MD.DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION  OR @DEDUCTION_INCLUSION IS NULL) AND MD.FILTER_CCP = 1
	 GROUP BY MD.PERIOD,MD.YEAR,MD.CFF_MASTER_SID,DESCRIPTION ) MD

	ON MD.PERIOD = S.PERIOD AND S.YEAR = MD.YEAR AND S.CFF_MASTER_SID = MD.CFF_MASTER_SID AND S.HIERARCHY_NO = MD.HIERARCHY_NO 
	LEFT JOIN (SELECT @HIERARCHY_NO HIERARCHY_NO,SPD.PERIOD,SPD.YEAR,SUM(SPD.SALES) SALES,SUM(SPD.UNITS) AS UNITS,DESCRIPTION,
	SPD.CFF_MASTER_SID,SUM(SPD.ACCRUALS) AS ACCRUALS,SUM(SPD.DISCOUNT) DISCOUNT FROM  ' , @discount_table ,' SPD LEFT join #RS_INFO B ON spd.RS_CONTRACT_SID=B.SELECTED_LEVEL
	WHERE SPD.HIERARCHY_NO like @HIERARCHY_NO+''%'' AND SPD.INDICATOR=0 AND SPD.DISCOUNT_TYPE=2
	 AND (SPD.DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION  OR @DEDUCTION_INCLUSION IS NULL) AND SPD.FILTER_CCP = 1
	 GROUP BY SPD.PERIOD,SPD.YEAR,SPD.CFF_MASTER_SID,DESCRIPTION ) SPD

	ON SPD.PERIOD = S.PERIOD AND S.YEAR = SPD.YEAR AND S.CFF_MASTER_SID = SPD.CFF_MASTER_SID AND S.HIERARCHY_NO = SPD.HIERARCHY_NO

	GROUP BY S.CFF_MASTER_SID,
	         S.YEAR,
			 S.PERIOD,ND.DESCRIPTION

	UNION ALL
	
	SELECT S.CFF_MASTER_SID,
	S.YEAR,
	S.PERIOD,
	SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0) AS TOTAL_DISCOUNT,
	((SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(S.SALES), 0)) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
	SUM(MD.DISCOUNT) AS MANDATED_DISCOUNT,
	SUM(SPD.DISCOUNT) AS SUPPLEMENTAL_DISCOUNT,
	(SUM(MD.DISCOUNT) / NULLIF(SUM(S.SALES), 0)) * 100 AS MANDATED_DISCOUNT_PERCENTAGE,
	(SUM(SPD.DISCOUNT) / NULLIF(SUM(S.SALES), 0)) * 100 AS SUPPLEMENTAL_DISCOUNT_PERCENTAGE,
	SUM(ND.DISCOUNT) / NULLIF(SUM(S.UNITS), 0) AS TOTAL_DISCOUNT_RPU,
	(SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(S.UNITS), 0) AS TOTAL_RPU,
	((SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(pfd.EX_FACTORY_SALES), 0)) * 100 AS DISCOUNT_OF_EX_FACTORY,
	SUM(ND.ACCRUALS) TOTAL_DISCOUNT_ACCRUAL,ND.DESCRIPTION,
	1 AS INDICATOR
	FROM (select  @HIERARCHY_NO HIERARCHY_NO,A.PERIOD,A.YEAR,sum(A.SALES) SALES ,sum(A.UNITS) UNITS,
	A.CFF_MASTER_SID from ', @sales_table ,' A
	where a.HIERARCHY_NO like @HIERARCHY_NO+''%''
	AND A.INDICATOR=1
	AND (SALES_INCLUSION = @SALES_INCLUSION  OR @SALES_INCLUSION IS NULL) AND A.FILTER_CCP = 1
	group by A.PERIOD,A.YEAR,A.CFF_MASTER_SID
	) S

	INNER JOIN #CFF_PROJECTION_MASTER CFM ON S.CFF_MASTER_SID=CFM.CFF_MASTER_SID 
	LEFT JOIN #PRODUCT_FILE_DATA PFD 
	ON PFD.PERIOD = S.PERIOD AND S.YEAR = PFD.YEAR AND S.CFF_MASTER_SID = PFD.CFF_MASTER_SID 
	AND S.HIERARCHY_NO = PFD.HIERARCHY_NO  AND PFD.INDICATOR=1

	LEFT JOIN ( SELECT @HIERARCHY_NO HIERARCHY_NO,ND.PERIOD,ND.YEAR,SUM(ND.SALES) SALES,SUM(ND.UNITS) AS UNITS,DESCRIPTION,
	ND.CFF_MASTER_SID,SUM(ND.ACCRUALS) AS ACCRUALS,SUM(ND.DISCOUNT) DISCOUNT FROM  ' , @discount_table ,' ND LEFT join #RS_INFO B ON ND.RS_CONTRACT_SID=B.SELECTED_LEVEL
	WHERE ND.HIERARCHY_NO like @HIERARCHY_NO+''%'' AND ND.INDICATOR=1 AND ND.DISCOUNT_TYPE=0
	 AND (ND.DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION  OR @DEDUCTION_INCLUSION IS NULL) AND ND.FILTER_CCP = 1
	 GROUP BY ND.PERIOD,ND.YEAR,ND.CFF_MASTER_SID,DESCRIPTION ) ND

	ON ND.PERIOD = S.PERIOD AND S.YEAR = ND.YEAR AND S.CFF_MASTER_SID = ND.CFF_MASTER_SID AND S.HIERARCHY_NO=ND.HIERARCHY_NO 
	LEFT JOIN (SELECT @HIERARCHY_NO HIERARCHY_NO,MD.PERIOD,MD.YEAR,SUM(MD.SALES) SALES,SUM(MD.UNITS) AS UNITS,DESCRIPTION,
	MD.CFF_MASTER_SID,SUM(MD.ACCRUALS) AS ACCRUALS,SUM(MD.DISCOUNT) DISCOUNT FROM  ' , @discount_table ,' MD LEFT join #RS_INFO B ON md.RS_CONTRACT_SID=B.SELECTED_LEVEL
	WHERE MD.HIERARCHY_NO like @HIERARCHY_NO+''%'' AND MD.INDICATOR=1 AND MD.DISCOUNT_TYPE=1
	 AND (MD.DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION  OR @DEDUCTION_INCLUSION IS NULL) AND MD.FILTER_CCP = 1
	 GROUP BY MD.PERIOD,MD.YEAR,MD.CFF_MASTER_SID,DESCRIPTION ) MD

	ON MD.PERIOD = S.PERIOD AND S.YEAR = MD.YEAR AND S.CFF_MASTER_SID = MD.CFF_MASTER_SID AND S.HIERARCHY_NO = MD.HIERARCHY_NO 
	LEFT JOIN (SELECT @HIERARCHY_NO HIERARCHY_NO,SPD.PERIOD,SPD.YEAR,SUM(SPD.SALES) SALES,SUM(SPD.UNITS) AS UNITS,DESCRIPTION,
	SPD.CFF_MASTER_SID,SUM(SPD.ACCRUALS) AS ACCRUALS,SUM(SPD.DISCOUNT) DISCOUNT FROM  ' , @discount_table ,' SPD LEFT join #RS_INFO B ON spd.RS_CONTRACT_SID=B.SELECTED_LEVEL
	WHERE SPD.HIERARCHY_NO like @HIERARCHY_NO+''%'' AND SPD.INDICATOR=1 AND SPD.DISCOUNT_TYPE=2
	 AND (SPD.DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION  OR @DEDUCTION_INCLUSION IS NULL) AND SPD.FILTER_CCP = 1
	 GROUP BY SPD.PERIOD,SPD.YEAR,SPD.CFF_MASTER_SID,DESCRIPTION ) SPD

	ON SPD.PERIOD = S.PERIOD AND S.YEAR = SPD.YEAR AND S.CFF_MASTER_SID = SPD.CFF_MASTER_SID AND S.HIERARCHY_NO = SPD.HIERARCHY_NO

	GROUP BY S.CFF_MASTER_SID,
	         S.YEAR,
			 S.PERIOD
			,ND.DESCRIPTION
')

	EXEC Sp_executesql @SQL_OUT,
	N'@SALES_INCLUSION int,@FIRST_PROJ_SID int,@HIERARCHY_NO varchar(200),@DEDUCTION_INCLUSION int'
	,@SALES_INCLUSION=@SALES_INCLUSION,
	@FIRST_PROJ_SID =@FIRST_PROJ_SID ,
	@HIERARCHY_NO=@HIERARCHY_NO,
	@DEDUCTION_INCLUSION=@DEDUCTION_INCLUSION

	END
	

ELSE
BEGIN
DECLARE @SQL_OUT_C NVARCHAR(MAX)
--product_cff_discount
SET @SQL_OUT_C=CONCAT('

	      
	    SELECT S.CFF_MASTER_SID,
	S.YEAR,
	S.PERIOD,
	SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0) AS TOTAL_DISCOUNT,
	((SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(S.SALES), 0)) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
	SUM(MD.DISCOUNT) AS MANDATED_DISCOUNT,
	SUM(SPD.DISCOUNT) AS SUPPLEMENTAL_DISCOUNT,
	(SUM(MD.DISCOUNT) / NULLIF(SUM(S.SALES), 0)) * 100 AS MANDATED_DISCOUNT_PERCENTAGE,
	(SUM(SPD.DISCOUNT) / NULLIF(SUM(S.SALES), 0)) * 100 AS SUPPLEMENTAL_DISCOUNT_PERCENTAGE,
	SUM(ND.DISCOUNT) / NULLIF(SUM(S.UNITS), 0) AS TOTAL_DISCOUNT_RPU,
	(SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(S.UNITS), 0) AS TOTAL_RPU,
	((SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(pfd.EX_FACTORY_SALES), 0)) * 100 AS DISCOUNT_OF_EX_FACTORY,
	SUM(ND.ACCRUALS) TOTAL_DISCOUNT_ACCRUAL,ND.DESCRIPTION,
	0 AS INDICATOR

	FROM  ',@sales_table,' S  
	INNER JOIN #CFF_PROJECTION_MASTER CFM ON S.CFF_MASTER_SID=CFM.CFF_MASTER_SID AND S.INDICATOR=1
	AND (SALES_INCLUSION = ',@SALES_INCLUSION,'  OR ',@SALES_INCLUSION ,'IS NULL) AND S.FILTER_CCP = 1
	LEFT JOIN #PRODUCT_FILE_DATA PFD 
	ON PFD.PERIOD = S.PERIOD AND S.YEAR = PFD.YEAR AND S.CFF_MASTER_SID = PFD.CFF_MASTER_SID AND S.HIERARCHY_NO = PFD.ROW_ID 
	AND PFD.INDICATOR=0
	 JOIN (SELECT ND.*,DESCRIPTION FROM ' , @DISCOUNT_TABLE ,' ND LEFT JOIN #RS_INFO B ON ND.RS_CONTRACT_SID=B.SELECTED_LEVEL)ND
	ON ND.PERIOD = S.PERIOD AND S.YEAR = ND.YEAR AND S.CFF_MASTER_SID = ND.CFF_MASTER_SID AND S.HIERARCHY_NO = ND.HIERARCHY_NO 
	AND ND.INDICATOR=0 AND (ND.DEDUCTION_INCLUSION = ',@DEDUCTION_INCLUSION,'  OR ',@DEDUCTION_INCLUSION,' IS NULL) AND ND.DISCOUNT_TYPE=0 AND ND.FILTER_CCP = 1
	LEFT JOIN (SELECT ND.*,DESCRIPTION FROM ' , @DISCOUNT_TABLE ,' ND LEFT JOIN #RS_INFO B ON ND.RS_CONTRACT_SID=B.SELECTED_LEVEL) MD 
	ON MD.PERIOD = S.PERIOD AND S.YEAR = MD.YEAR AND S.CFF_MASTER_SID = MD.CFF_MASTER_SID AND S.HIERARCHY_NO = MD.HIERARCHY_NO 
	AND MD.INDICATOR=0 AND (MD.DEDUCTION_INCLUSION = ',@DEDUCTION_INCLUSION,'  OR ',@DEDUCTION_INCLUSION,' IS NULL) AND MD.DISCOUNT_TYPE=1 AND MD.FILTER_CCP = 1
    LEFT JOIN (select nd.*,DESCRIPTION from ' , @discount_table ,' ND LEFT join #RS_INFO B ON ND.RS_CONTRACT_SID=B.SELECTED_LEVEL) SPD
	ON SPD.PERIOD = S.PERIOD AND S.YEAR = SPD.YEAR AND S.CFF_MASTER_SID = SPD.CFF_MASTER_SID AND S.HIERARCHY_NO = SPD.HIERARCHY_NO 
	AND SPD.INDICATOR=0 AND (SPD.DEDUCTION_INCLUSION = ',@DEDUCTION_INCLUSION,'  OR ',@DEDUCTION_INCLUSION,' IS NULL) AND SPD.DISCOUNT_TYPE=2 AND SPD.FILTER_CCP = 1
	WHERE (S.HIERARCHY_NO =  @ROW_ID  or @ROW_ID is null)
	GROUP BY S.CFF_MASTER_SID,
	         S.YEAR,
			 S.PERIOD,ND.DESCRIPTION

	
	UNION ALL
	
	SELECT S.CFF_MASTER_SID,
	S.YEAR,
	S.PERIOD,
	SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0) AS TOTAL_DISCOUNT,
	((SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(S.SALES), 0)) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
	SUM(MD.DISCOUNT) AS MANDATED_DISCOUNT,
	SUM(SPD.DISCOUNT) AS SUPPLEMENTAL_DISCOUNT,
	(SUM(MD.DISCOUNT) / NULLIF(SUM(S.SALES), 0)) * 100 AS MANDATED_DISCOUNT_PERCENTAGE,
	(SUM(SPD.DISCOUNT) / NULLIF(SUM(S.SALES), 0)) * 100 AS SUPPLEMENTAL_DISCOUNT_PERCENTAGE,
	SUM(ND.DISCOUNT) / NULLIF(SUM(S.UNITS), 0) AS TOTAL_DISCOUNT_RPU,
	(SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(S.UNITS), 0) AS TOTAL_RPU,
	((SUM(ND.DISCOUNT) + isnull(SUM(MD.DISCOUNT),0) + isnull(SUM(SPD.DISCOUNT),0)) / NULLIF(SUM(pfd.EX_FACTORY_SALES), 0)) * 100 AS DISCOUNT_OF_EX_FACTORY,
	SUM(ND.ACCRUALS) TOTAL_DISCOUNT_ACCRUAL,ND.DESCRIPTION,
	1 AS INDICATOR

	FROM  ',@sales_table,' S  
	INNER JOIN #CFF_PROJECTION_MASTER CFM ON S.CFF_MASTER_SID=CFM.CFF_MASTER_SID AND S.INDICATOR=1
	AND (SALES_INCLUSION = ',@SALES_INCLUSION,'  OR ',@SALES_INCLUSION ,'IS NULL) AND S.FILTER_CCP = 1
	LEFT JOIN #PRODUCT_FILE_DATA PFD 
	ON PFD.PERIOD = S.PERIOD AND S.YEAR = PFD.YEAR AND S.CFF_MASTER_SID = PFD.CFF_MASTER_SID AND S.HIERARCHY_NO = PFD.ROW_ID 
	AND PFD.INDICATOR=1
	LEFT JOIN (SELECT ND.*,DESCRIPTION FROM ' , @DISCOUNT_TABLE ,' ND LEFT JOIN #RS_INFO B ON ND.RS_CONTRACT_SID=B.SELECTED_LEVEL)ND
	ON ND.PERIOD = S.PERIOD AND S.YEAR = ND.YEAR AND S.CFF_MASTER_SID = ND.CFF_MASTER_SID AND S.HIERARCHY_NO = ND.HIERARCHY_NO 
	AND ND.INDICATOR=1 AND (ND.DEDUCTION_INCLUSION = ',@DEDUCTION_INCLUSION,'  OR ',@DEDUCTION_INCLUSION,' IS NULL) AND ND.DISCOUNT_TYPE=0 AND ND.FILTER_CCP = 1
	LEFT JOIN (SELECT ND.*,DESCRIPTION FROM ' , @DISCOUNT_TABLE ,' ND LEFT JOIN #RS_INFO B ON ND.RS_CONTRACT_SID=B.SELECTED_LEVEL) MD 
	ON MD.PERIOD = S.PERIOD AND S.YEAR = MD.YEAR AND S.CFF_MASTER_SID = MD.CFF_MASTER_SID AND S.HIERARCHY_NO = MD.HIERARCHY_NO 
	AND MD.INDICATOR=1 AND (MD.DEDUCTION_INCLUSION = ',@DEDUCTION_INCLUSION,'  OR ',@DEDUCTION_INCLUSION,' IS NULL) AND MD.DISCOUNT_TYPE=1 AND MD.FILTER_CCP = 1
    LEFT JOIN (select nd.*,DESCRIPTION from ' , @discount_table ,' ND LEFT join #RS_INFO B ON ND.RS_CONTRACT_SID=B.SELECTED_LEVEL) SPD
	ON SPD.PERIOD = S.PERIOD AND S.YEAR = SPD.YEAR AND S.CFF_MASTER_SID = SPD.CFF_MASTER_SID AND S.HIERARCHY_NO = SPD.HIERARCHY_NO 
	AND SPD.INDICATOR=1 AND (SPD.DEDUCTION_INCLUSION = ',@DEDUCTION_INCLUSION,'  OR ',@DEDUCTION_INCLUSION,' IS NULL) AND SPD.DISCOUNT_TYPE=2 AND SPD.FILTER_CCP = 1
	WHERE (S.HIERARCHY_NO =  @ROW_ID  or @ROW_ID is null)
	GROUP BY S.CFF_MASTER_SID,
	         S.YEAR,
			 S.PERIOD,ND.DESCRIPTION')

	EXEC Sp_executesql @SQL_OUT_C,
	N'@FIRST_PROJ_SID int,@ROW_ID int',
	@FIRST_PROJ_SID=@FIRST_PROJ_SID,
	@ROW_ID=@ROW_ID
	
END
GO