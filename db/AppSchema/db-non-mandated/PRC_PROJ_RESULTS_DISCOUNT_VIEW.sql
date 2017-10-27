IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PROJ_RESULTS_DISCOUNT_VIEW'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PROJ_RESULTS_DISCOUNT_VIEW]
  END

GO
CREATE PROCEDURE [dbo].[PRC_PROJ_RESULTS_DISCOUNT_VIEW] (
	@PROJECTION_SID VARCHAR(500)
	,@PROJ_FREQUENCY VARCHAR(20)
	,@DISCOUNT_ID VARCHAR(8000)
	,@SCREEN_NAME VARCHAR(50)
	,@ORDER_FLAG BIT
	,@PROGRAM_TYPE VARCHAR(50) = 'Program'
	)
AS
/**********************************************************************************************************
** FILE NAME		:	PRC_PROJ_RESULTS_DISCOUNT_VIEW.SQL
** PROCEDURE NAME	:	PRC_PROJ_RESULTS_DISCOUNT_VIEW
** DESCRIPTION		:	  aggregation of projections attached to the particualr projection showing as output for both projection_result screen and projection_varicnace screen
** INPUT PARAMETERS	:	@PROJECTION_SID	- passing input as PROJECTION_MASTER_SID
						@PROJ_FREQUENCY - based on frequency it will display the data monthly,quarterly semi annualy and annulaly
						@DISCOUNT       - Passing inpuit as(RS_CONTRACT_SID) either REBATE_PROGRAM_TYPE OR RS_NAME based on Rebate level we will pull the discount projection value in discount projection tables
                        @SCREEN_NAME    - based on screen name :if @screen_name =''Assumption'' We will pass only one one projection as input parameter based on that we will calculate the total level sales projection values and discount projection value based rebate or RS_CONTRACT_SID & PPA for both actual & projection
						                  ,Based on Sales,Discount & PPA calculation we will calculate Netsales,Netprofit values.
										  If we pass input as @screen_name =''  Variance'' we will pass mulptiple projection as input and will display total level aggregation value of both Actual sales & projection values for only contract Sales WAC remaining will display only projection  projections attached to the first PROJECTIONS
						             	  screen_name<> assumptions it will display rest of projections display aggregation of projections attached to each projecton as pivot view
                        @PROGRAM_TYPE   - If this was program then results will have rebate program type as name's other wise ot will be rebate name
						@ORDER_FLAG     - this is optional which will order the result

** OUTPUT PARAMETERS:	na
** AUTHOR NAME		:	@SANDEEP
** CREATION DATE	:	 - 
** CALLED BY		:   In view mode,from application side they click on projection results screen the inside that proecure will call dispaly information 
**********************************************************************************************************
** CHANGE HISTORY
**********************************************************************************************************
** VER   DATE      LOCAL ALMTICKET NO      SUBTICKET NO   ONLINE ALM TICKET      AUTHOR                          DESCRIPTION 
** ---   --------  ---------             -------------        -------------    ----------------                  ------------------
** 1    17/10/2016  GAL-7965                GAL-7965         		           @SANDEEP     				Removed the session tables and used the main tables for calculation
*********************************************************************************************************/
BEGIN
	SET NOCOUNT ON

	BEGIN TRY
      -------------declaring variables--------------------------
		DECLARE @SP INT
			,@SP_PROJ_SID INT
			,@TEMP_PROJ_SID VARCHAR(500)
			,@START_PERIOD_SID INT
			,@END_PERIOD_SID INT,
			 @CURRENT_DATE                  DATE

		DECLARE @SALES_ACTUAL_TABLE VARCHAR(200) = 'NM_ACTUAL_SALES'
			,@SALES_PROJECTION_TABLE VARCHAR(200) = 'NM_SALES_PROJECTION'
			,@DISC_PROJECTION_TABLE VARCHAR(200) = 'NM_DISCOUNT_PROJECTION'
			,@DISC_ACTUAL_TABLE VARCHAR(200) = 'NM_ACTUAL_DISCOUNT'
			,@DISC_MASTER_TABLE VARCHAR(200) = 'NM_DISCOUNT_PROJ_MASTER'
			,@PPA_PROJECTION_TABLE VARCHAR(200) = 'NM_PPA_PROJECTION'
			,@PPA_ACTUAL_TABLE VARCHAR(200) = 'NM_ACTUAL_PPA'
			,@PPA_MASTER_TABLE VARCHAR(200) = 'NM_PPA_PROJECTION_MASTER'
 -----------------PASSING MULTIPLE PROJECTIONS AS INPUT CONVERTING INTO COMMA SEPERATED VALUES ------------------------
			IF Object_id('TEMPDB..#PROJECTION_MASTER') IS NOT NULL
		TRUNCATE TABLE #PROJECTION_MASTER
	ELSE
		CREATE TABLE #PROJECTION_MASTER (
			ID INT IDENTITY(1, 1)
			,PROJECTION_MASTER_SID INT
			)

----------------------------------------------------------STORING THE OUTPUT RESULT OF PROJECTION VARIANCE TABLE--------------------------------------
  	IF Object_id('TEMPDB..#PIVOT_RESULT') IS NOT NULL
		TRUNCATE TABLE #PIVOT_RESULT
	ELSE
		CREATE TABLE #PIVOT_RESULT (
			PROJECTION_ID INT
			,[PERIOD] SMALLINT
			,[YEAR] INT
			,RS_NAME VARCHAR(100)
			,CONTRACT_DISCOUNT_ACTUAL NUMERIC(38, 15)
			,CONTRACT_DISCOUNT_PROJECTED NUMERIC(38, 15)
			,TOTAL_DISCOUNT_ACTUAL_PERCENTAGE NUMERIC(38, 15)
			,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE NUMERIC(38, 15)
			,TOTAL_RPU_ACTUAL NUMERIC(22, 6)
			,TOTAL_RPU_PROJECTED NUMERIC(22, 6)
			,RS_CONTRACT_SID INT
			,DISCOUNT_OF_EX_FACTORY_ACTUALS NUMERIC(22,6)
			,DISCOUNT_OF_EX_FACTORY_PROJECTED NUMERIC(22,6)
			)

	SET @TEMP_PROJ_SID = Replace(@PROJECTION_SID + ',', ',,', ',')

	WHILE Patindex('%,%', @TEMP_PROJ_SID) <> 0
	BEGIN
		SELECT @SP = Patindex('%,%', @TEMP_PROJ_SID)

		SELECT @SP_PROJ_SID = LEFT(@TEMP_PROJ_SID, @SP - 1)

		SELECT @TEMP_PROJ_SID = Stuff(@TEMP_PROJ_SID, 1, @SP, '')

		INSERT INTO #PROJECTION_MASTER (PROJECTION_MASTER_SID)
		SELECT @SP_PROJ_SID
	END

-------------------taking the first projection which will be the current projection------------
  	DECLARE @FIRST_PROJ_SID INT

	SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID
	FROM #PROJECTION_MASTER PM
	WHERE ID = 1

	IF Object_id('TEMPDB.DBO.#PROJECTION_DETAILS', 'U') IS NOT NULL
		DROP TABLE #PROJECTION_DETAILS;

	SELECT PD.PROJECTION_DETAILS_SID
		,PM.PROJECTION_MASTER_SID
	INTO #PROJECTION_DETAILS
	FROM PROJECTION_DETAILS PD
	INNER JOIN #PROJECTION_MASTER PM ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
	WHERE ID = 1

	DECLARE @SQL NVARCHAR(4000) = ''

	SET @SQL = CONCAT (
			'SELECT     @START_PERIOD_SID = Min(ST.PERIOD_SID),
								@END_PERIOD_SID = Max(ST.PERIOD_SID)
					FROM       '
			,@SALES_PROJECTION_TABLE
			,' ST
					INNER JOIN #PROJECTION_DETAILS PD ON ST.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
					INNER JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID'
			)

	EXEC Sp_executesql @sql
		,N'@START_PERIOD_SID int OUTPUT,  @END_PERIOD_SID int OUTPUT'
		,@START_PERIOD_SID = @START_PERIOD_SID OUTPUT
		,@END_PERIOD_SID = @END_PERIOD_SID OUTPUT

	IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
		DROP TABLE #PERIOD;

	SELECT PERIOD_SID
		,PERIOD_DATE
		,MONTH
		,QUARTER
		,SEMI_ANNUAL
		,YEAR
		,  CASE 
			WHEN LEFT(@PROJ_FREQUENCY, 1) = 'M'
				THEN MONTH
			WHEN LEFT(@PROJ_FREQUENCY, 1) = 'Q'
				THEN QUARTER
			WHEN LEFT(@PROJ_FREQUENCY, 1) = 'S'
				THEN SEMI_ANNUAL
			ELSE 01
			END as PERIOD
	INTO #PERIOD
	FROM PERIOD

SET @CURRENT_DATE = CASE
                      WHEN LEFT(@PROJ_FREQUENCY, 1) = 'M' THEN Dateadd(DD, 1, Eomonth(Getdate(), -1))
                      WHEN LEFT(@PROJ_FREQUENCY, 1) = 'Q' THEN Datefromparts(Year(Getdate()), Datepart(QUARTER, Getdate()), 01)
                      WHEN LEFT(@PROJ_FREQUENCY, 1) = 'S' THEN Datefromparts(Year(Getdate()), ( ( ( ( ( Month(Getdate()) ) - 1 ) / 6 ) * 6 ) + 1 ), 01)
                      ELSE Datefromparts(Year(Getdate()), 01, 01)
                    END


	IF Object_id('TEMPDB..#SPLIT_TABLE') IS NOT NULL
		DROP TABLE #SPLIT_TABLE

	SELECT TOKEN
	INTO #SPLIT_TABLE
	FROM [DBO].[UDF_SPLITSTRING](@DISCOUNT_ID, ',')
-------------------identifing the prjection and rebate combination----------------
	IF Object_id('TEMPDB..#BASE_TABLE') IS NOT NULL
		DROP TABLE #BASE_TABLE

	SELECT PM.PROJECTION_MASTER_SID
		,A.TOKEN
		,RS_NAME
		,YEAR
		,PERIOD
	INTO #BASE_TABLE
	FROM #PROJECTION_MASTER PM
	CROSS JOIN (
		SELECT TOKEN
		FROM [DBO].[UDF_SPLITSTRING](@DISCOUNT_ID, ',')
		) A
	INNER JOIN RS_MODEL RS ON A.TOKEN = RS.RS_MODEL_SID
	CROSS JOIN (
		SELECT YEAR
			,PERIOD
		FROM #PERIOD
		WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
				AND @END_PERIOD_SID
		) ST
	WHERE ID <> 1
---------------pulling rs infor form rs tables------------------------------
	IF Object_id('TEMPDB.DBO.#RS_DATA', 'U') IS NOT NULL
		DROP TABLE #RS_DATA;

	CREATE TABLE #RS_DATA (
		RS_CONTRACT_SID INT
		,RS_NAME VARCHAR(100)
		)

	SET @sql = ''
	SET @sql = CONCAT (
			'INSERT INTO #RS_DATA
                      (RS_CONTRACT_SID,
                       RS_NAME)
          SELECT     DISTINCT NMDP.RS_CONTRACT_SID,
                              CASE
                                WHEN @PROGRAM_TYPE = ''PROGRAM''
                                      OR @PROGRAM_TYPE IS NULL THEN RS_NAME
                               ELSE PRICE_GROUP_TYPE
                              END AS DISCOUNT
          FROM       '
			,@DISC_MASTER_TABLE
			,' NMDP
          --INNER JOIN RS_MODEL RM ON RM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID 
		  INNER JOIN RS_CONTRACT RM ON RM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID 								--GAL-1163
          WHERE  EXISTS (SELECT 1
                                 FROM   #SPLIT_TABLE A
								-- WHERE  A.TOKEN = NMDP.RS_CONTRACT_SID
                                 WHERE  A.TOKEN = NMDP.RS_CONTRACT_SID)											--GAL-1163
          UNION ALL
          SELECT     DISTINCT RM.RS_CONTRACT_SID AS PPA_RS_CONTRACT_SID,
                              CASE
                                WHEN @PROGRAM_TYPE = ''PROGRAM''
                                      OR @PROGRAM_TYPE IS NULL THEN RS_NAME
                                ELSE ''Price Protection''
                              END             AS PPA_DISCOUNT
          FROM       '
			,@PPA_MASTER_TABLE
			,' PPA
          --INNER JOIN RS_MODEL RM ON PPA.RS_CONTRACT_SID = RM.RS_CONTRACT_SID
		    INNER JOIN RS_CONTRACT RM ON PPA.RS_CONTRACT_SID = RM.RS_CONTRACT_SID' --GAL-1163
			)

	EXEC Sp_executesql @sql
		,N'@PROGRAM_TYPE VARCHAR(50), @SCREEN_NAME VARCHAR(50),@FIRST_PROJ_SID int,@DISCOUNT_ID VARCHAR(8000)'
		,@PROGRAM_TYPE = @PROGRAM_TYPE
		,@SCREEN_NAME = @SCREEN_NAME
		,@FIRST_PROJ_SID = @FIRST_PROJ_SID
		,@DISCOUNT_ID = @DISCOUNT_ID


	
-----------taking the active file inforamtion from projection file details which will store the active file information ------------------
		---------------------------------------gts_file_data---------------------------------------
		declare @item_udt UDT_ITEM,@company_id int,@business_unit int,@FORECAST_NAME varchar(200),@FORECAST_VERSION varchar(50),@PROJECTION_DATE datetime

	INSERT INTO @ITEM_UDT (ITEM_MASTER_SID)
	SELECT DISTINCT IM.ITEM_MASTER_SID
	FROM ITEM_MASTER IM
	WHERE EXISTS (
			SELECT 1
			FROM CCP_DETAILS CCP
			INNER JOIN PROJECTION_DETAILS PD ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
			WHERE CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
				AND EXISTS (select PROJECTION_MASTER_SID from #PROJECTION_MASTER PM where ID=1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
			)

	SELECT @COMPANY_ID = COMPANY_MASTER_SID
			,@business_unit=pm.BUSINESS_UNIT
			,@PROJECTION_DATE = pm.TO_DATE
	FROM PROJECTION_MASTER PM
	WHERE EXISTS (select PROJECTION_MASTER_SID from #PROJECTION_MASTER P where ID=1 AND P.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)

-----------taking the active file inforamtion from projection file details which will store the active file information ------------------
	SELECT @FORECAST_NAME=FORECAST_NAME
		,@FORECAST_VERSION=VERSION
	FROM (
		SELECT FT.FORECAST_NAME
			,FT.[VERSION]
			,FILE_MANAGEMENT_SID
			,HT.[DESCRIPTION] AS FILE_TYPE
			,Row_number() OVER (
				PARTITION BY FILE_TYPE ORDER BY FILE_MANAGEMENT_SID DESC
				) AS RN
		FROM FILE_MANAGEMENT FT
		INNER JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
		WHERE (
				CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
				AND FT.FROM_PERIOD IS NOT NULL
				)
			AND (
				CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
				OR FT.TO_PERIOD IS NULL
				)
			AND HT.LIST_NAME = 'FILE_TYPE'
			AND HT.[DESCRIPTION] =
				'EX-FACTORY SALES'
			AND FT.BUSINESS_UNIT = @BUSINESS_UNIT
			AND FT.COMPANY = @COMPANY_ID
		) A
	WHERE RN = 1

	SELECT @START_PERIOD_SID = Max(CASE 
				WHEN PERIOD_DATE = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, - 3, Getdate())), 0)
					THEN PERIOD_SID
				END)
		,@END_PERIOD_SID = Max(CASE 
				WHEN PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0)
					THEN PERIOD_SID
				END)
	FROM PERIOD
	WHERE PERIOD_DATE IN (
			Dateadd(YY, Datediff(YY, 0, Dateadd(YY, - 3, Getdate())), 0)
			,Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0)			
			)


		IF Object_id('TEMPDB..#TEMP_GTS_DETAILS') IS NOT NULL
		DROP TABLE #TEMP_GTS_DETAILS

	CREATE TABLE #TEMP_GTS_DETAILS (
		ITEM_MASTER_SID int
		,GTS_SALES_PROJECTED NUMERIC(22, 6)
		,GTS_SALES_ACTUALS NUMERIC(22, 6)
		,UNITS NUMERIC(22, 6)
		,PERIOD INT
		,YEARLY INT
		)

	INSERT INTO #TEMP_GTS_DETAILS (
		ITEM_MASTER_SID
		,GTS_SALES_PROJECTED
		,GTS_SALES_ACTUALS
		,YEARLY
		,PERIOD
		
		)
	SELECT DISTINCT ITEM_MASTER_SID
		, COALESCE(A.FORECAST_GTS_SALES, A.ACTUAL_GTS_SALES)as FORECAST_GTS_SALES 
		,A.ACTUAL_GTS_SALES
		,P.YEAR
		,p.PERIOD
	FROM Udf_gts_wac(@ITEM_UDT, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME, @FORECAST_VERSION) A
	INNER JOIN #PERIOD P ON P.PERIOD_SID = A.PERIOD_SID
	------------------------------------------------------------------------------------------------
-------------------taking the file information for the prior projections---------------------

IF Object_id('tempdb..#PRODUCT_FILE_TEMP') IS NOT NULL
        DROP TABLE #PRODUCT_FILE_TEMP

      CREATE TABLE #PRODUCT_FILE_TEMP
        (
           PROJECTION_MASTER_SID          INT,
           ITEM_MASTER_SID                INT,
           PERIOD_SID                     INT,
           EXFACTORY_ACTUAL_SALES         NUMERIC(22, 6),
           EXFACTORY_ACTUAL_UNITS         NUMERIC(22, 6),
           EXFACTORY_FORECAST_SALES       NUMERIC(22, 6),
           EXFACTORY_FORECAST_UNITS       NUMERIC(22, 6),
           DEMAND_ACTUAL_SALES            NUMERIC(22, 6),
           DEMAND_ACTUAL_UNITS            NUMERIC(22, 6),
           DEMAND_FORECAST_SALES          NUMERIC(22, 6),
           DEMAND_FORECAST_UNITS          NUMERIC(22, 6),
           ADJUSTED_DEMAND_ACTUAL_SALES   NUMERIC(22, 6),
           ADJUSTED_DEMAND_ACTUAL_UNITS   NUMERIC(22, 6),
           ADJUSTED_DEMAND_FORECAST_SALES NUMERIC(22, 6),
           ADJUSTED_DEMAND_FORECAST_UNITS NUMERIC(22, 6),
           INVENTORY_ACTUAL_SALES         NUMERIC(22, 6),
           INVENTORY_ACTUAL_UNITS         NUMERIC(22, 6),
           INVENTORY_FORECAST_SALES       NUMERIC(22, 6),
           INVENTORY_FORECAST_UNITS       NUMERIC(22, 6),
           ITEM_PRICE                     NUMERIC(22, 6),
           EXFACTORY_CUST_ACTUAL_SALES    NUMERIC(22, 6),
           EXFACTORY_CUST_ACTUAL_UNITS    NUMERIC(22, 6),
           EXFACTORY_CUST_FORECAST_SALES  NUMERIC(22, 6),
           EXFACTORY_CUST_FORECAST_UNITS  NUMERIC(22, 6),
           INVENTORY_CUST_ACTUAL_SALES    NUMERIC(22, 6),
           INVENTORY_CUST_ACTUAL_UNITS    NUMERIC(22, 6),
           INVENTORY_CUST_FORECAST_SALES  NUMERIC(22, 6),
           INVENTORY_CUST_FORECAST_UNITS  NUMERIC(22, 6)
        )


		IF( (SELECT Count(ID)
     FROM   #PROJECTION_MASTER) > 1 )
  BEGIN
      
      INSERT INTO #PRODUCT_FILE_TEMP (PROJECTION_MASTER_SID,
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
  END

  ------------------------------------IN SCREEN NAME ='ASSUMPTION' WILL DISPLAY BOTH ACTUAL & PROJECTION VALUE FOR TOTAL LEVEL OF CONTRACT SALE,DISCOUNT AMOUNT,DISOCUNT PROJECTION & PPA.
      

	IF @SCREEN_NAME = 'ASSUMPTIONS'
	BEGIN
	
	
	 -------------------------------------PULLING THE discount  PROJECTION FROM discount  PROJECTION TABLE BASED ON PROJECTION----------------------------
       
		IF Object_id('TEMPDB.DBO.#ST_NM_DISCOUNT_PROJECTION', 'U') IS NOT NULL
			DROP TABLE #ST_NM_DISCOUNT_PROJECTION;

		CREATE TABLE #ST_NM_DISCOUNT_PROJECTION (
			PROJECTION_SALES NUMERIC(22, 6)
			,PERIOD INT
			,YEAR INT
			,RS_NAME VARCHAR(100)
			,RS_CONTARCT_SID INT
			)

		SET @SQL = ''
		SET @SQL = CONCAT (
				'INSERT INTO #ST_NM_DISCOUNT_PROJECTION
							(PROJECTION_SALES,
							 PERIOD,
							 YEAR,
							 RS_NAME,RS_CONTARCT_SID)
				SELECT Sum(NSP.PROJECTION_SALES) AS PROJECTION_SALES,
					   PERIOD,
					   YEAR,
					   RS_NAME
					   ,rm.RS_CONTRACT_SID
				--TO       #ST_NM_DISCOUNT_PROJECTION
				FROM   '
				,@DISC_PROJECTION_TABLE
				,' NSP
					   INNER JOIN #RS_DATA RM
							   ON RM.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
					   INNER JOIN #PERIOD P
							   ON P.PERIOD_SID = NSP.PERIOD_SID
				WHERE  EXISTS (SELECT 1
							   FROM   PROJECTION_DETAILS PD
									  INNER JOIN #PROJECTION_MASTER PM
											  ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
							   WHERE  PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID)
					   AND EXISTS (SELECT 1
								   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
								   WHERE  A.TOKEN = NSP.RS_CONTRACT_SID)
				GROUP  BY PERIOD,
						  YEAR,
						  RS_NAME
						  ,rm.RS_CONTRACT_SID'
				)

		EXEC Sp_executesql @sql
			,N'@DISCOUNT_ID VARCHAR(8000)'
			,@DISCOUNT_ID = @DISCOUNT_ID
 -------------------------------------PULLING THE discount actuals  FROM discount actual TABLE BASED ON PROJECTION----------------------------
    
		IF Object_id('TEMPDB.DBO.#ST_NM_ACTUAL_DISCOUNT', 'U') IS NOT NULL
			DROP TABLE #ST_NM_ACTUAL_DISCOUNT;

		CREATE TABLE #ST_NM_ACTUAL_DISCOUNT (
			ACTUAL_SALES NUMERIC(22, 6)
			,PERIOD INT
			,YEAR INT
			,RS_NAME VARCHAR(100)
			,RS_CONTRACT_SID INT
			)

		SET @SQL = ''
		SET @SQL = CONCAT (
				'INSERT INTO #ST_NM_ACTUAL_DISCOUNT(ACTUAL_SALES,
							 PERIOD,
							 YEAR,
							 RS_NAME,RS_CONTRACT_SID)
                SELECT     Sum(NAD.ACTUAL_SALES) AS ACTUAL_SALES,
                           P.PERIOD,
                           P.YEAR,
                           RS_NAME
						   ,RM.RS_CONTRACT_SID
                FROM       '
				,@DISC_ACTUAL_TABLE
				,' NAD
               INNER JOIN #RS_DATA RM
							   ON RM.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID
                INNER JOIN #PERIOD P ON P.PERIOD_SID = NAD.PERIOD_SID
                WHERE      EXISTS (SELECT     1
                                   FROM       PROJECTION_DETAILS PD
                                   INNER JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                   WHERE      PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID)
                           AND EXISTS (SELECT 1
                                       FROM   [DBO].[UDF_SPLITSTRING](@DISCOUNT_ID, '','') A
                                       WHERE  A.TOKEN = NAD.RS_CONTRACT_SID)
                GROUP      BY P.PERIOD,
                              P.YEAR,
                              RS_NAME
							  ,RM.RS_CONTRACT_SID'
				)

		EXEC Sp_executesql @sql
			,N'@DISCOUNT_ID VARCHAR(8000)'
			,@DISCOUNT_ID = @DISCOUNT_ID
 -------------------------------------PULLING THE ppa  information   FROM ppa  PROJECTION TABLE BASED ON PROJECTION----------------------------

		IF Object_id('TEMPDB.DBO.#ST_NM_PPA_PROJECTION', 'U') IS NOT NULL
			DROP TABLE #ST_NM_PPA_PROJECTION;

		CREATE TABLE #ST_NM_PPA_PROJECTION (
			PROJECTION_DISCOUNT_DOLLAR NUMERIC(22, 6)
			,RS_CONTRACT_SID INT
			,PERIOD INT
			,YEAR INT
			,RS_NAME VARCHAR(100)
			,RATE NUMERIC(22, 6)
			,RPU NUMERIC(22, 6)
			)

		DECLARE @PPA_SQL NVARCHAR(MAX) = ''

		SET @PPA_SQL = CONCAT (
				'INSERT INTO #ST_NM_PPA_PROJECTION (PERIOD,
                       YEAR,
                       RS_NAME,
					   RS_CONTRACT_SID,
					   PROJECTION_DISCOUNT_DOLLAR,
					   rate,
					   RPU)
                SELECT PERIOD,
                       YEAR,
                       RS_NAME,
					   RS_CONTRACT_SID,
                       Sum(PROJECTION_DISCOUNT_DOLLAR)                               PROJECTION_DISCOUNT_DOLLAR,
                       Sum(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(Sum(sales), 0) * 100 AS rate,
                       Sum(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(Sum(units), 0)       RPU
               -- INTO   #ST_NM_PPA_PROJECTION
                FROM   (SELECT PROJECTION_DETAILS_SID,
                               PERIOD,
                               YEAR,
                               RS_NAME,
							   RS_CONTRACT_SID,
                               Sum(PROJECTION_DISCOUNT_DOLLAR) PROJECTION_DISCOUNT_DOLLAR,
                               Sum(PROJECTION_SALES)           sales,
                               Sum(PROJECTION_UNITS)           units
                        FROM   (SELECT     S.PROJECTION_DETAILS_SID,
                                           Sum(PROJECTION_DISCOUNT_DOLLAR) PROJECTION_DISCOUNT_DOLLAR,
                                           Sum(SP.PROJECTION_SALES)        AS PROJECTION_SALES,
                                           Sum(SP.PROJECTION_UNITS)        AS PROJECTION_UNITS,
                                           RM.RS_NAME,
                                           P.PERIOD,
                                           P.YEAR,
										   rm.RS_CONTRACT_SID
                                FROM       '
				,@PPA_PROJECTION_TABLE
				,' S
                                INNER JOIN '
				,@SALES_PROJECTION_TABLE
				,' SP ON SP.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                                                    AND SP.PERIOD_SID = S.PERIOD_SID
                                INNER JOIN #PERIOD P ON P.PERIOD_SID = S.PERIOD_SID
                                INNER JOIN '
				,@PPA_MASTER_TABLE
				,
				' C ON C.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                                                        AND C.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                                	   INNER JOIN #RS_DATA RM
							   ON RM.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                                WHERE      EXISTS (SELECT     1
                                                   FROM       PROJECTION_DETAILS PD
                                                   INNER JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                                   WHERE      PM.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                GROUP      BY S.PROJECTION_DETAILS_SID,
                                              RM.RS_NAME,
                                              P.PERIOD,
                                              P.YEAR
											  ,rm.RS_CONTRACT_SID) G
                        GROUP  BY PROJECTION_DETAILS_SID,
                                  PERIOD,
                                  YEAR,
                                  G.RS_NAME
								  ,RS_CONTRACT_SID) Y
                GROUP  BY PERIOD,
                          YEAR,
                          RS_NAME,RS_CONTRACT_SID'
				)

		EXEC Sp_executesql @PPA_SQL
			,N'@FIRST_PROJ_SID INT'
			,@FIRST_PROJ_SID = @FIRST_PROJ_SID
 -------------------------------------PULLING THE ppa actual information   FROM ppa  actual TABLE BASED ON PROJECTION----------------------------

		IF Object_id('TEMPDB.DBO.#ST_NM_ACTUAL_PPA', 'U') IS NOT NULL
			DROP TABLE #ST_NM_ACTUAL_PPA;

		CREATE TABLE #ST_NM_ACTUAL_PPA (
			DOLLAR NUMERIC(22, 6)
			,PERIOD INT
			,YEAR INT
			,RS_NAME VARCHAR(100)
			,RATE NUMERIC(22, 6)
			,RPU NUMERIC(22, 6)
			,Actuals NUMERIC(22, 6)
			,UNIT NUMERIC(22, 6)
			)

		SET @PPA_SQL = ''
		SET @PPA_SQL = CONCAT (
				'INSERT INTO #ST_NM_ACTUAL_PPA(PERIOD,
                       YEAR,
                       RS_NAME, DOLLAR,rate, UNIT, RPU,Actuals)
                SELECT PERIOD                                                    AS PERIOD,
                       YEAR,
                       RS_NAME,
                       Sum(ACTUAL_DISCOUNT_DOLLAR)                               DOLLAR,
                       Sum(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(Sum(sales), 0) * 100 AS rate,
                       Sum(ACTUAL_DISCOUNT_UNITS1)                               UNIT,
                       Sum(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(Sum(units), 0)       RPU,
                       0                                                         Actuals
                --INTO   #ST_NM_ACTUAL_PPA
                FROM   (SELECT PROJECTION_DETAILS_SID,
                               PERIOD,
                               YEAR,
                               RS_NAME,
                               Sum(ACTUAL_DISCOUNT_DOLLAR) ACTUAL_DISCOUNT_DOLLAR,
                               Min(NULLIF(UNIT, 0))        ACTUAL_DISCOUNT_UNITS1,
                               Sum(sales)                  sales,
                               Sum(unit)                   units
                        FROM   (SELECT     S.PROJECTION_DETAILS_SID,
                                           Sum(ACTUAL_DISCOUNT_DOLLAR) ACTUAL_DISCOUNT_DOLLAR,
                                           Sum(SP.ACTUAL_UNITS)        UNIT,
                                           Sum(SP.ACTUAL_SALES)        sales,
                                           RM.RS_NAME,
                                           P.PERIOD,
                                           P.YEAR
                                FROM       '
				,@PPA_ACTUAL_TABLE
				,' S
                                INNER JOIN '
				,@SALES_ACTUAL_TABLE
				,' SP ON SP.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                                                AND SP.PERIOD_SID = S.PERIOD_SID
                                INNER JOIN #PERIOD P ON P.PERIOD_SID = S.PERIOD_SID
                                INNER JOIN '
				,@PPA_MASTER_TABLE
				,
				' C ON C.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                                                        AND C.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                                INNER JOIN #RS_DATA RM ON RM.RS_CONTRACT_SID = C.RS_CONTRACT_SID
                                WHERE      EXISTS (SELECT     1
                                                   FROM       PROJECTION_DETAILS PD
                                                   INNER JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                                   WHERE      PM.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                GROUP      BY S.PROJECTION_DETAILS_SID,
                                              RM.RS_NAME,
                                              P.PERIOD,
                                              P.YEAR) G
                        GROUP  BY PROJECTION_DETAILS_SID,
                                  PERIOD,
                                  YEAR,
                                  G.RS_NAME) Y
                GROUP  BY PERIOD,
                          YEAR,
                          RS_NAME'
				)

		EXEC Sp_executesql @PPA_SQL
			,N'@FIRST_PROJ_SID INT'
			,@FIRST_PROJ_SID = @FIRST_PROJ_SID
-------------------------------------PULLING THE sales information   FROM sales TABLE BASED ON PROJECTION----------------------------
		IF Object_id('TEMPDB.DBO.#ST_NM_SALES_PROJECTION', 'U') IS NOT NULL
			DROP TABLE #ST_NM_SALES_PROJECTION;

		CREATE TABLE #ST_NM_SALES_PROJECTION (
			PROJECTION_SALES NUMERIC(22, 6)
			,PROJECTION_UNITS NUMERIC(22, 6)
			,PERIOD INT
			,YEAR INT
			)

		SET @SQL = ''
		SET @SQL = CONCAT (
				'insert into #ST_NM_SALES_PROJECTION(PROJECTION_SALES,PROJECTION_UNITS,PERIOD,YEAR)
                SELECT     Sum(PROJECTION_SALES) PROJECTION_SALES,
                           Sum(PROJECTION_UNITS) PROJECTION_UNITS,
                           P.PERIOD,
                           P.YEAR
                --INTO       #ST_NM_SALES_PROJECTION
                FROM       '
				,@SALES_PROJECTION_TABLE
				,' NSP
                INNER JOIN #PERIOD P ON P.PERIOD_SID = NSP.PERIOD_SID
                WHERE      EXISTS (SELECT     1
                                   FROM       PROJECTION_DETAILS PD
                                   INNER JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                   WHERE      PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                                              AND pd.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                GROUP      BY PERIOD,
                              YEAR'
				)

		EXEC Sp_executesql @SQL
			,N'@FIRST_PROJ_SID INT'
			,@FIRST_PROJ_SID = @FIRST_PROJ_SID

-------------------------------------PULLING THE sales information   FROM sales TABLE BASED ON PROJECTION----------------------------
		IF Object_id('TEMPDB.DBO.#ST_NM_ACTUAL_SALES', 'U') IS NOT NULL
			DROP TABLE #ST_NM_ACTUAL_SALES;

		CREATE TABLE #ST_NM_ACTUAL_SALES (
			ACTUAL_SALES NUMERIC(22, 6)
			,ACTUAL_UNITS NUMERIC(22, 6)
			,PERIOD INT
			,YEAR INT
			)

		SET @sql = ''
		SET @sql = CONCAT (
				'INSERT #ST_NM_ACTUAL_SALES
					   (ACTUAL_SALES,
						ACTUAL_UNITS,
						PERIOD,
						YEAR)
				SELECT Sum(ACTUAL_SALES) ACTUAL_SALES,
					   Sum(ACTUAL_UNITS) ACTUAL_UNITS,
					   PERIOD,
					   YEAR
				--INTO       #ST_NM_ACTUAL_SALES
				FROM   '
				,@SALES_ACTUAL_TABLE
				,' NAD
					   INNER JOIN #PERIOD P
							   ON P.PERIOD_SID = NAD.PERIOD_SID
				WHERE  EXISTS (SELECT 1
							   FROM   PROJECTION_DETAILS PD
									  INNER JOIN #PROJECTION_MASTER PM
											  ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
							   WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID)
				GROUP  BY PERIOD,
						  YEAR '
				)

		EXEC Sp_executesql @SQL
------------combining sales actual and projection information -----------------------
		IF Object_id('TEMPDB.DBO.#SALES', 'U') IS NOT NULL
			DROP TABLE #SALES;

		SELECT FD.YEAR
			,FD.PERIOD
			,COALESCE((NAS.ACTUAL_SALES), 0) as ACTUAL_SALES
			,COALESCE((NAS.ACTUAL_UNITS), 0) as ACTUAL_UNITS
			,COALESCE((NAS.ACTUAL_SALES), 0) as NM_ACTUAL_SALES
			,COALESCE((NSP.PROJECTION_SALES), 0) as NM_PROJECTED_SALES
			,COALESCE((NAS.ACTUAL_UNITS), 0) as NM_ACTUAL_UNITS
			,COALESCE((NSP.PROJECTION_UNITS), 0) as NM_PROJECTED_UNITS
		INTO #SALES
		FROM (
			SELECT DISTINCT PERIOD
				,YEAR
			FROM #PERIOD
			WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
			) FD
		LEFT JOIN #ST_NM_SALES_PROJECTION NSP ON NSP.YEAR = FD.YEAR
			AND NSP.PERIOD = FD.PERIOD
		LEFT JOIN #ST_NM_ACTUAL_SALES NAS ON NAS.YEAR = FD.YEAR
			AND NAS.PERIOD = FD.PERIOD
------------combining discount actual and projection information -----------------------
		IF OBJECT_ID('TEMPDB.DBO.#DISCOUNT', 'U') IS NOT NULL
			DROP TABLE #DISCOUNT;

		SELECT COALESCE(NAD.YEAR, NSP.YEAR) YEAR
			,COALESCE(NAD.PERIOD, NSP.PERIOD) PERIOD
			,COALESCE((NSP.PROJECTION_SALES), 0) as PROJECTION_SALES
			, COALESCE((NAD.ACTUAL_SALES), 0) as ACTUAL_SALES
			,COALESCE(NAD.RS_NAME, NSP.RS_NAME) RS_NAME
		INTO #DISCOUNT
		FROM #ST_NM_DISCOUNT_PROJECTION NSP
		LEFT JOIN #ST_NM_ACTUAL_DISCOUNT NAD ON NAD.YEAR = NSP.YEAR
			AND NAD.PERIOD = NSP.PERIOD
			AND NAD.RS_NAME = NSP.RS_NAME
------------combining ppa actual and projection information -----------------------

		IF OBJECT_ID('TEMPDB.DBO.#PPA', 'U') IS NOT NULL
			DROP TABLE #PPA;

		SELECT COALESCE(NAS.YEAR, NSP.YEAR) YEAR
			,COALESCE(NAS.PERIOD, NSP.PERIOD) PERIOD
			,COALESCE((NSP.PROJECTION_DISCOUNT_DOLLAR), 0) AS PPA_DISCOUNT_PROJECTED
			,COALESCE((NAS.DOLLAR), 0) AS PPA_ACTUAL_SALES
			,COALESCE((NAS.RATE), 0) AS PPA_RATE_ACTUAL
			,COALESCE((NSP.RATE), 0) AS PPA_RATE_PROJECTION
			,COALESCE((NSP.RPU), 0) AS PPA_RPU_PROJECTION
			,COALESCE((NAS.RPU), 0) AS PPA_RPU_ACTUAL
			,COALESCE(NAS.RS_NAME, NSP.RS_NAME) RS_NAME
		INTO #PPA
		FROM #ST_NM_ACTUAL_PPA NAS
		FULL JOIN #ST_NM_PPA_PROJECTION NSP ON NAS.YEAR = NSP.YEAR
			AND NAS.PERIOD = NSP.PERIOD
			AND NAS.RS_NAME = NSP.RS_NAME

	
	------------final results -----------------------

		IF Object_id('TEMPDB.DBO.#RESULT_DETAILS', 'U') IS NOT NULL
			DROP TABLE #RESULT_DETAILS;

		CREATE TABLE #RESULT_DETAILS (
			PROJECTION_MASTER_SID VARCHAR(500)
			,YEAR INT
			,PERIOD INT
			,RS_NAME VARCHAR(100)
			,TOTAL_DISCOUNT NUMERIC(22, 6)
			,TOTAL_DISCOUNT_PROJECTED NUMERIC(22, 6)
			,TOTAL_DISCOUNT_PERCENTAGE NUMERIC(22, 6)
			,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE NUMERIC(22, 6)
			,TOTAL_RPU NUMERIC(22, 6)
			,TOTAL_PROJECTED_RPU NUMERIC(22, 6)			
			)

		INSERT INTO #RESULT_DETAILS (
			PROJECTION_MASTER_SID
			,YEAR
			,PERIOD
			,RS_NAME
			,TOTAL_DISCOUNT
			,TOTAL_DISCOUNT_PROJECTED
			,TOTAL_DISCOUNT_PERCENTAGE
			,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE
			,TOTAL_RPU
			,TOTAL_PROJECTED_RPU
			)
		SELECT @PROJECTION_SID
			,ND.YEAR
			,ND.PERIOD
			,RS_NAME
			,ND.ACTUAL_SALES
			,ND.PROJECTION_SALES
			,COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_SALES, 0), 0) * 100
			,COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100
			,COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_UNITS, 0), 0)
			,COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
		FROM #DISCOUNT ND
		INNER JOIN #SALES RD ON RD.YEAR = ND.YEAR
			AND RD.PERIOD = ND.PERIOD
		
		UNION ALL
		
		SELECT @PROJECTION_SID
			,PD.YEAR
			,PD.PERIOD
			,RS_NAME
			,PD.PPA_ACTUAL_SALES
			,PD.PPA_DISCOUNT_PROJECTED
			,PD.PPA_RATE_ACTUAL
			,PD.PPA_RATE_PROJECTION
			,PD.PPA_RPU_ACTUAL
			,PD.PPA_RPU_PROJECTION
		FROM #PPA PD

		SELECT DISTINCT PROJECTION_MASTER_SID
			,nd.YEAR
			,nd.PERIOD
			,RS_NAME
			,ISNULL(TOTAL_DISCOUNT,0)
			,ISNULL(TOTAL_DISCOUNT_PROJECTED,0)
			,ISNULL(TOTAL_DISCOUNT_PERCENTAGE,0)
			,ISNULL(TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,0)
			,ISNULL(TOTAL_RPU,0)
			,ISNULL(TOTAL_PROJECTED_RPU,0)
			,ISNULL(cast(COALESCE(nd.TOTAL_DISCOUNT / NULLIF(fd.GTS_SALES_ACTUALS, 0), 0) * 100 as numeric(22,6)),0) as DISCOUNT_OF_EX_FACTORY_ACTUALS
			,ISNULL(cast(COALESCE(nD.TOTAL_DISCOUNT_PROJECTED/ NULLIF(fd.GTS_SALES_PROJECTED, 0), 0) * 100 as numeric(22,6)),0) as DISCOUNT_OF_EX_FACTORY_PROJECTED
		FROM #RESULT_DETAILS nd inner join (select YEARLY,period,sum(GTS_SALES_ACTUALS) as GTS_SALES_ACTUALS,sum(GTS_SALES_PROJECTED) as GTS_SALES_PROJECTED from #TEMP_GTS_DETAILS group by YEARLY,period) fd
		on fd.PERIOD=nd.PERIOD and fd.YEARLY=nd.YEAR
		ORDER BY RS_NAME
			,nd.YEAR
			,nd.PERIOD
	END
		IF @SCREEN_NAME = 'VARIANCE'-----------------------WILL DISPLSY BOTH ACTUAL VALUES & PROJECTION ONLY FOR CONTRACT SALES REMAING WILL DISPLAY ONLY PROJECTION VALUE AND WILL PASS INPUT AS MULTIPLE PROJECTION
		BEGIN
		-------------------------------------PULLING THE discount  PROJECTION FROM discount  PROJECTION TABLE BASED ON PROJECTION----------------------------
     
			IF Object_id('TEMPDB.DBO.#CURRENT_NM_ACTUAL_DISCOUNT', 'U') IS NOT NULL
				DROP TABLE #CURRENT_NM_ACTUAL_DISCOUNT;

			CREATE TABLE #CURRENT_NM_ACTUAL_DISCOUNT (
				ACTUAL_SALES NUMERIC(22, 6)
				,PERIOD INT
				,YEAR INT
				,RS_NAME VARCHAR(100)
				,RS_CONTRACT_SID INT
				)

			DECLARE @DISC_Asql NVARCHAR(4000) = ''

			SET @DISC_Asql = CONCAT (
					'insert into #CURRENT_NM_ACTUAL_DISCOUNT(ACTUAL_SALES,PERIOD,YEAR,RS_NAME
			,RS_CONTRACT_SID)
		        SELECT     Sum(NSP.ACTUAL_SALES) AS ACTUAL_SALES,
                           P.PERIOD,
                           P.YEAR,
                           rm.RS_NAME
						   ,RM.RS_CONTRACT_SID
                FROM       '
					,@DISC_ACTUAL_TABLE
					,' nsp
                INNER JOIN #RS_DATA RM ON RM.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID				--GAL-1163
                INNER JOIN #PERIOD P ON P.PERIOD_SID = NSP.PERIOD_SID
                WHERE     EXISTS (SELECT 1
                                       FROM   #SPLIT_TABLE A
                                       WHERE  A.TOKEN = NSP.RS_CONTRACT_SID)					--GAL-1163
                GROUP      BY P.PERIOD,
                              P.YEAR,
                              rm.RS_NAME
							  ,RM.RS_CONTRACT_SID'
					)

			EXEC Sp_executesql @DISC_Asql
				,N'@DISCOUNT_ID VARCHAR(8000)'
				,@DISCOUNT_ID = @DISCOUNT_ID

 -------------------------------------PULLING THE discount actuals  FROM discount actual TABLE BASED ON PROJECTION----------------------------
			IF Object_id('TEMPDB.DBO.#CURRENT_NM_DISCOUNT_PROJECTION', 'U') IS NOT NULL
				DROP TABLE #CURRENT_NM_DISCOUNT_PROJECTION;

			CREATE TABLE #CURRENT_NM_DISCOUNT_PROJECTION (
				PROJECTION_SALES NUMERIC(22, 6)
				,PERIOD INT
				,YEAR INT
				,RS_NAME VARCHAR(100)
				,rs_contract_sid INT
				)

			DECLARE @psql NVARCHAR(4000) = ''

			SET @psql = CONCAT (
					'insert into #CURRENT_NM_DISCOUNT_PROJECTION (PROJECTION_SALES,PERIOD,YEAR,RS_NAME,rs_contract_sid)
		        SELECT     Sum(NSP.PROJECTION_SALES) AS PROJECTION_SALES,
                           P.PERIOD,
                           P.YEAR,
                           rm.RS_NAME
						   ,rm.RS_CONTRACT_SID
                --INTO       #CURRENT_ST_NM_DISCOUNT_PROJECTION
                FROM       '
					,@DISC_PROJECTION_TABLE
					,' nsp
                INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = nsp.PROJECTION_DETAILS_SID
                INNER JOIN #RS_DATA RM ON RM.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
                INNER JOIN #PERIOD P ON P.PERIOD_SID = NSP.PERIOD_SID
                WHERE      EXISTS (SELECT 1
                                   FROM   PROJECTION_DETAILS PD
                                   WHERE  PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                                          AND PD.PROJECTION_MASTER_SID = '
					,@FIRST_PROJ_SID
					,')
                           AND EXISTS (SELECT 1
                                       FROM   [DBO].[UDF_SPLITSTRING](@DISCOUNT_ID, '','') A
                                       WHERE  A.TOKEN = NSP.RS_CONTRACT_SID)
                GROUP      BY P.PERIOD,
                              P.YEAR,
                              rm.RS_NAME,
							  rm.RS_CONTRACT_SID'
					)

			EXEC Sp_executesql @psql
				,N'@DISCOUNT_ID VARCHAR(8000)'
				,@DISCOUNT_ID = @DISCOUNT_ID
 -------------------------------------PULLING THE ppa actual information   FROM ppa  actual TABLE BASED ON PROJECTION----------------------------

			IF Object_id('TEMPDB.DBO.#CURRENT_NM_PPA_ACTUAL', 'U') IS NOT NULL
				DROP TABLE #CURRENT_NM_PPA_ACTUAL;

			CREATE TABLE #CURRENT_NM_PPA_ACTUAL (
				ACTUAL_DISCOUNT_DOLLAR NUMERIC(22, 6)
				,PERIOD INT
				,YEAR INT
				,RS_NAME VARCHAR(100)
				,RATE NUMERIC(22, 6)
				,RPU NUMERIC(22, 6)
				,UNIT NUMERIC(22, 6)
				,RS_CONTRACT_SID INT
				)

			DECLARE @PPA_ASQL NVARCHAR(MAX) = ''

			SET @PPA_ASQL = CONCAT (
					'INSERT INTO #CURRENT_NM_PPA_ACTUAL (PERIOD,
                       YEAR,
                       RS_NAME,
					   ACTUAL_DISCOUNT_DOLLAR,
					   rate,
					   unit,
					   RPU
					   ,RS_CONTRACT_SID)
	            SELECT PERIOD,
                       YEAR,
                       RS_NAME,
                       Sum(ACTUAL_DISCOUNT_DOLLAR)                               ACTUAL_DISCOUNT_DOLLAR,
                       Sum(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(Sum(sales), 0) * 100 AS rate,
                       Sum(ACTUAL_DISCOUNT_DOLLAR)                               UNIT,
                       Sum(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(Sum(units), 0)       RPU
					   ,RS_CONTRACT_SID
               -- INTO   #CURRENT_ST_NM_PPA_PROJECTION
                FROM   (SELECT projection_details_sid,
                               PERIOD,
                               YEAR,
                               RS_NAME,
                               Sum(ACTUAL_DISCOUNT_DOLLAR)  ACTUAL_DISCOUNT_DOLLAR,
                               Min(NULLIF(ACTUAL_UNITS, 0)) ACTUAL_DISCOUNT_UNITS1,
                               Sum(ACTUAL_SALES)            sales,
                               Sum(ACTUAL_UNITS)            units
							  ,RS_CONTRACT_SID
                        FROM   (SELECT     S.projection_details_sid,
                                           Sum(ACTUAL_DISCOUNT_DOLLAR) ACTUAL_DISCOUNT_DOLLAR,
                                           Sum(SP.ACTUAL_SALES)        ACTUAL_SALES,
                                           Sum(SP.ACTUAL_UNITS)        ACTUAL_UNITS,
                                           RM.RS_NAME,
                                           P.PERIOD,
                                           P.YEAR
										   ,RM.RS_CONTRACT_SID
                                FROM       '
					,@PPA_ACTUAL_TABLE
					,' S
                                INNER JOIN '
					,@SALES_ACTUAL_TABLE
					,' SP ON SP.projection_details_sid = S.projection_details_sid
                                                                    AND SP.PERIOD_SID = S.PERIOD_SID
                                INNER JOIN #PERIOD P ON P.PERIOD_SID = S.PERIOD_SID
                                INNER JOIN '
					,@PPA_MASTER_TABLE
					,' C ON C.projection_details_sid = S.projection_details_sid
                                                                        AND C.RS_CONTRACT_SID = S.RS_CONTRACT_SID					--GAL-1163
                                INNER JOIN #RS_DATA RM ON RM.RS_CONTRACT_SID = C.RS_CONTRACT_SID									--GAL-1163
                                GROUP      BY S.projection_details_sid,	
                                              RM.RS_NAME,
                                              P.PERIOD,
                                              P.YEAR,
											  RM.RS_CONTRACT_SID) G
                        GROUP  BY projection_details_sid,
                                  PERIOD,
                                  YEAR,
                                  G.RS_NAME,
								  RS_CONTRACT_SID) Y
                GROUP  BY PERIOD,
                          YEAR,
                          RS_NAME
						  ,RS_CONTRACT_SID'
					)

			EXEC Sp_executesql @PPA_ASQL
				,N'@FIRST_PROJ_SID INT'
				,@FIRST_PROJ_SID = @FIRST_PROJ_SID

 -------------------------------------PULLING THE ppa  information   FROM ppa  PROJECTION TABLE BASED ON PROJECTION----------------------------

			IF Object_id('TEMPDB.DBO.#CURRENT_NM_PPA_PROJECTION', 'U') IS NOT NULL
				DROP TABLE #CURRENT_NM_PPA_PROJECTION;

			CREATE TABLE #CURRENT_NM_PPA_PROJECTION (
				PROJECTION_DISCOUNT_DOLLAR NUMERIC(22, 6)
				,PERIOD INT
				,YEAR INT
				,RS_NAME VARCHAR(100)
				,RS_CONTRACT_SID INT
				,RATE NUMERIC(22, 6)
				,RPU NUMERIC(22, 6)
				,UNIT NUMERIC(22, 6)
				)

			DECLARE @PPA_pSQL NVARCHAR(MAX) = ''

			SET @PPA_pSQL = CONCAT (
					'INSERT INTO #CURRENT_NM_PPA_PROJECTION (PERIOD,
                       YEAR,
                       RS_NAME,
					   rs_contract_sid,
					   PROJECTION_DISCOUNT_DOLLAR,
					   rate,
					   unit,
					   RPU)
	            SELECT PERIOD,
                       YEAR,
                       RS_NAME,
					   rs_contract_sid,
                       Sum(PROJECTION_DISCOUNT_DOLLAR)                               PROJECTION_DISCOUNT_DOLLAR,
                       Sum(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(Sum(sales), 0) * 100 AS rate,
                       Sum(PROJECTION_DISCOUNT_UNITS1)                               UNIT,
                       Sum(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(Sum(units), 0)       RPU
               -- INTO   #CURRENT_ST_NM_PPA_PROJECTION
                FROM   (SELECT PROJECTION_DETAILS_SID,
                               PERIOD,
                               YEAR,
                               RS_NAME,
							   rs_contract_sid,
                               Sum(PROJECTION_DISCOUNT_DOLLAR)  PROJECTION_DISCOUNT_DOLLAR,
                               Min(NULLIF(PROJECTION_UNITS, 0)) PROJECTION_DISCOUNT_UNITS1,
                               Sum(PROJECTION_SALES)            sales,
                              Sum(PROJECTION_UNITS)            units
                        FROM   (SELECT     S.PROJECTION_DETAILS_SID,
                                           Sum(PROJECTION_DISCOUNT_DOLLAR) PROJECTION_DISCOUNT_DOLLAR,
                                           Sum(SP.PROJECTION_SALES)        PROJECTION_SALES,
                                           Sum(SP.PROJECTION_UNITS)        PROJECTION_UNITS,
                                           RM.RS_NAME,
										    rm.rs_contract_sid,
                                           P.PERIOD,
                                           P.YEAR
                                FROM       '
					,@PPA_PROJECTION_TABLE
					,' S
                                INNER JOIN '
					,@SALES_PROJECTION_TABLE
					,' SP ON SP.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                                                    AND SP.PERIOD_SID = S.PERIOD_SID
                                INNER JOIN #PERIOD P ON P.PERIOD_SID = S.PERIOD_SID
                                INNER JOIN '
					,@PPA_MASTER_TABLE
					,
					' C ON C.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                                                        AND C.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                                INNER JOIN #RS_DATA RM ON RM.RS_CONTRACT_SID = C.RS_CONTRACT_SID
                                WHERE      EXISTS (SELECT     1
                                                   FROM       PROJECTION_DETAILS PD
                                                   INNER JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                                   WHERE      PM.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                GROUP      BY S.PROJECTION_DETAILS_SID,
                                              RM.RS_NAME,
                                              P.PERIOD,
                                              P.YEAR,
											rm.rs_contract_sid) G
                        GROUP  BY PROJECTION_DETAILS_SID,
                                  PERIOD,
                                  YEAR,
                                  G.RS_NAME
								  ,rs_contract_sid) Y
                GROUP  BY PERIOD,
                          YEAR,
                          RS_NAME,
						 rs_contract_sid'
					)

			EXEC Sp_executesql @PPA_pSQL
				,N'@FIRST_PROJ_SID INT'
				,@FIRST_PROJ_SID = @FIRST_PROJ_SID

-------------------------------------PULLING THE sales information   FROM sales TABLE BASED ON PROJECTION----------------------------
			IF Object_id('TEMPDB.DBO.#CURRENT_NM_ACTUAL_SALES', 'U') IS NOT NULL
				DROP TABLE #CURRENT_NM_ACTUAL_SALES;

			CREATE TABLE #CURRENT_NM_ACTUAL_SALES (
				ACTUAL_SALES NUMERIC(22, 6)
				,ACTUAL_UNITS NUMERIC(22, 6)
				,PERIOD INT
				,YEAR INT
				)

			DECLARE @ASQL NVARCHAR(MAX)

			SET @ASQL = ''
			SET @ASQL = CONCAT (
					'insert into #CURRENT_NM_ACTUAL_SALES(ACTUAL_SALES,ACTUAL_UNITS,PERIOD,YEAR)
                SELECT     Sum(ACTUAL_SALES) ACTUAL_SALES,
                           Sum(ACTUAL_UNITS) ACTUAL_UNITS,
                           P.PERIOD,
                           P.YEAR
                FROM       '
					,@SALES_ACTUAL_TABLE
					,' NSP
                INNER JOIN #PERIOD P ON P.PERIOD_SID = NSP.PERIOD_SID
                GROUP      BY PERIOD,
                              YEAR'
					)

			EXEC Sp_executesql @ASQL
				,N'@FIRST_PROJ_SID INT'
				,@FIRST_PROJ_SID = @FIRST_PROJ_SID

-------------------------------------PULLING THE sales information   FROM sales TABLE BASED ON PROJECTION----------------------------
			IF Object_id('TEMPDB.DBO.#CURRENT_NM_SALES_PROJECTION', 'U') IS NOT NULL
				DROP TABLE #CURRENT_NM_SALES_PROJECTION;

			CREATE TABLE #CURRENT_NM_SALES_PROJECTION (
				PROJECTION_SALES NUMERIC(22, 6)
				,PROJECTION_UNITS NUMERIC(22, 6)
				,PERIOD INT
				,YEAR INT
				)

			SET @psql = ''
			SET @psql = CONCAT (
					'insert into #CURRENT_NM_SALES_PROJECTION(PROJECTION_SALES,PROJECTION_UNITS,PERIOD,YEAR)
                SELECT     Sum(PROJECTION_SALES) PROJECTION_SALES,
                           Sum(PROJECTION_UNITS) PROJECTION_UNITS,
                           P.PERIOD,
                           P.YEAR
               -- INTO       #CURRENT_ST_NM_SALES_PROJECTION
                FROM       '
					,@SALES_PROJECTION_TABLE
					,' NSP
                INNER JOIN #PERIOD P ON P.PERIOD_SID = NSP.PERIOD_SID
                WHERE      EXISTS (SELECT 1
                                   FROM   PROJECTION_DETAILS PD
                                   WHERE  PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
                                          AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                GROUP      BY PERIOD,
                              YEAR'
					)

			EXEC Sp_executesql @psql
				,N'@FIRST_PROJ_SID INT'
				,@FIRST_PROJ_SID = @FIRST_PROJ_SID


------------combining sales actual and projection information -----------------------
			IF Object_id('TEMPDB.DBO.#CURRENT_SALES', 'U') IS NOT NULL
				DROP TABLE #CURRENT_SALES;

			SELECT FD.YEAR
				,FD.PERIOD
				, COALESCE((NAS.ACTUAL_SALES), 0) as NM_ACTUAL_SALES
				, COALESCE((NAS.ACTUAL_UNITS), 0) AS NM_ACTUAL_UNITS
				, COALESCE((NSP.PROJECTION_SALES), 0) AS NM_PROJECTED_SALES
				, COALESCE((NSP.PROJECTION_UNITS), 0) AS NM_PROJECTED_UNITS
			INTO #CURRENT_SALES
			FROM (
				SELECT DISTINCT PERIOD
					,YEAR
				FROM #PERIOD
				WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
						AND @END_PERIOD_SID
				) FD
			LEFT JOIN #CURRENT_NM_ACTUAL_SALES NAS ON FD.YEAR = NAS.YEAR
				AND FD.YEAR = NAS.PERIOD
			LEFT JOIN #CURRENT_NM_SALES_PROJECTION NSP ON NSP.YEAR = FD.YEAR
				AND NSP.PERIOD = FD.PERIOD
------------combining discount actual and projection information -----------------------

			IF OBJECT_ID('TEMPDB.DBO.#CURRENT_DISCOUNT', 'U') IS NOT NULL
	DROP TABLE #CURRENT_DISCOUNT;

SELECT FD.YEAR
	,FD.PERIOD
	,FD.RS_NAME
	,COALESCE((A.ACTUAL_SALES), 0) as ACTUAL_SALES 
	, COALESCE((A.PROJECTION_SALES), 0) as PROJECTION_SALES 
	,FD.RS_CONTRACT_SID
INTO #CURRENT_DISCOUNT
FROM (
	SELECT DISTINCT PERIOD
		,YEAR
		,RS_NAME
		,RS_CONTRACT_SID
	FROM #PERIOD
	CROSS JOIN #RS_DATA
	WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
	) FD
JOIN (
	SELECT COALESCE(NAD.PERIOD, NSP.PERIOD) PERIOD
		,COALESCE(NAD.YEAR, NSP.YEAR) YEAR
		,COALESCE(NAD.RS_NAME, NSP.RS_NAME) RS_NAME
		,COALESCE(NAD.RS_CONTRACT_SID, NSP.RS_CONTRACT_SID) RS_CONTRACT_SID
		,NAD.ACTUAL_SALES
		,NSP.PROJECTION_SALES
	FROM (
		SELECT ACTUAL_SALES
			,PERIOD
			,YEAR
			,RS_NAME
			,RS_CONTRACT_SID
		FROM #CURRENT_NM_ACTUAL_DISCOUNT
		) NAD
	FULL JOIN (
		SELECT PROJECTION_SALES
			,PERIOD
			,YEAR
			,RS_NAME
			,RS_CONTRACT_SID
		FROM #CURRENT_NM_DISCOUNT_PROJECTION
		) NSP ON NAD.PERIOD = NSP.PERIOD
		AND NAD.YEAR = NSP.YEAR
		AND NAD.RS_NAME = NSP.RS_NAME
		AND NAD.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
	) A ON FD.PERIOD = A.PERIOD
	AND FD.YEAR = A.YEAR
	AND FD.RS_NAME = A.RS_NAME
	AND FD.RS_CONTRACT_SID = A.RS_CONTRACT_SID
ORDER BY FD.PERIOD
	,FD.YEAR
	,FD.RS_NAME
	,FD.RS_CONTRACT_SID

			
------------combining ppa actual and projection information -----------------------
IF OBJECT_ID('TEMPDB.DBO.#CURRENT_PPA', 'U') IS NOT NULL
	DROP TABLE #CURRENT_PPA;

SELECT FD.YEAR
	,FD.PERIOD
	,COALESCE((A.PROJECTION_DISCOUNT_DOLLAR), 0) as PPA_DISCOUNT_PROJECTED 
	, COALESCE((A.ACTUAL_DISCOUNT_DOLLAR), 0) as PPA_ACTUAL_SALES 
	, COALESCE((A.ACTUAL_RATE), 0) as PPA_RATE_ACTUAL
	, COALESCE((A.RATE), 0) as PPA_RATE_PROJECTION 
	,COALESCE((A.RPU), 0) as PPA_RPU_PROJECTION 
	,COALESCE((A.ACTUAL_RPU), 0) as PPA_RPU_ACTUAL 
	,FD.RS_NAME
	,FD.RS_CONTRACT_SID
INTO #CURRENT_PPA
FROM (
	SELECT DISTINCT PERIOD
		,YEAR
		,RS_NAME
		,RS_CONTRACT_SID
	FROM #PERIOD
	CROSS JOIN #RS_DATA
	WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
	) FD
JOIN (
	SELECT COALESCE(NAD.PERIOD, NSP.PERIOD) PERIOD
		,COALESCE(NAD.YEAR, NSP.YEAR) YEAR
		,COALESCE(NAD.RS_NAME, NSP.RS_NAME) RS_NAME
		,COALESCE(NAD.RS_CONTRACT_SID, NSP.RS_CONTRACT_SID) RS_CONTRACT_SID
		,NAD.ACTUAL_DISCOUNT_DOLLAR
		,NSP.PROJECTION_DISCOUNT_DOLLAR
		,NAD.RATE AS ACTUAL_RATE
		,NAD.RPU AS ACTUAL_RPU
		,NSP.RATE
		,NSP.RPU
	FROM (
		SELECT ACTUAL_DISCOUNT_DOLLAR
			,RATE
			,RPU
			,UNIT
			,PERIOD
			,YEAR
			,RS_NAME
			,RS_CONTRACT_SID
		FROM #CURRENT_NM_PPA_ACTUAL
		) NAD
	FULL JOIN (
		SELECT PROJECTION_DISCOUNT_DOLLAR
			,RATE
			,RPU
			,UNIT
			,PERIOD
			,YEAR
			,RS_NAME
			,RS_CONTRACT_SID
		FROM #CURRENT_NM_PPA_PROJECTION
		) NSP ON NAD.PERIOD = NSP.PERIOD
		AND NAD.YEAR = NSP.YEAR
		AND NAD.RS_NAME = NSP.RS_NAME
		AND NAD.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
	) A ON FD.PERIOD = A.PERIOD
	AND FD.YEAR = A.YEAR
	AND FD.RS_NAME = A.RS_NAME
	AND FD.RS_CONTRACT_SID = A.RS_CONTRACT_SID
ORDER BY FD.PERIOD
	,FD.YEAR
	,FD.RS_NAME
	,FD.RS_CONTRACT_SID
------------final results  for current projection-----------------------
			IF Object_id('TEMPDB.DBO.#RESULT_DETAILS_INFO', 'U') IS NOT NULL
				DROP TABLE #RESULT_DETAILS_INFO;

			CREATE TABLE #RESULT_DETAILS_INFO (
				PROJECTION_MASTER_SID INT
				,YEAR INT
				,PERIOD INT
				,RS_NAME VARCHAR(100)
				,TOTAL_DISCOUNT NUMERIC(22, 6)
				,TOTAL_DISCOUNT_PROJECTED NUMERIC(22, 6)
				,TOTAL_DISCOUNT_ACTUAL_PERCENTAGE NUMERIC(22, 6)
				,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE NUMERIC(22, 6)
				,TOTAL_PROJECTED_RPU NUMERIC(22, 6)
				,TOTAL_ACTUAL_RPU NUMERIC(22, 6)
				,RS_CONTRACT_SID INT
				)

			INSERT INTO #RESULT_DETAILS_INFO (
				PROJECTION_MASTER_SID
				,YEAR
				,PERIOD
				,RS_NAME
				,TOTAL_DISCOUNT
				,TOTAL_DISCOUNT_PROJECTED
				,TOTAL_DISCOUNT_ACTUAL_PERCENTAGE
				,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE
				,TOTAL_ACTUAL_RPU
				,TOTAL_PROJECTED_RPU
				,RS_CONTRACT_SID
				)
			SELECT @FIRST_PROJ_SID
				,ND.YEAR
				,ND.PERIOD
				,RS_NAME
				,ND.ACTUAL_SALES
				,ND.PROJECTION_SALES
				,COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_SALES, 0), 0) * 100
				,COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100
				,COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_UNITS, 0), 0)
				,COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
				,ND.RS_CONTRACT_SID
			FROM #CURRENT_DISCOUNT ND
			INNER JOIN #CURRENT_SALES RD ON RD.YEAR = ND.YEAR
				AND RD.PERIOD = ND.PERIOD
			
			UNION 
			
			SELECT @FIRST_PROJ_SID
				,PD.YEAR
				,PD.PERIOD
				,RS_NAME
				,PD.PPA_ACTUAL_SALES
				,PD.PPA_DISCOUNT_PROJECTED
				,PD.PPA_RATE_ACTUAL
				,PPA_RATE_PROJECTION
				,PPA_RPU_ACTUAL
				,PPA_RPU_PROJECTION
				,RS_CONTRACT_SID
			FROM #CURRENT_PPA PD

			INSERT INTO #PIVOT_RESULT (
				PROJECTION_ID
				,[PERIOD]
				,[YEAR]
				,RS_NAME
				,CONTRACT_DISCOUNT_ACTUAL
				,CONTRACT_DISCOUNT_PROJECTED
				,TOTAL_DISCOUNT_ACTUAL_PERCENTAGE
				,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE
				,TOTAL_RPU_ACTUAL
				,TOTAL_RPU_PROJECTED
				,RS_CONTRACT_SID
				,DISCOUNT_OF_EX_FACTORY_ACTUALS
				,DISCOUNT_OF_EX_FACTORY_PROJECTED
				)
			SELECT DISTINCT PROJECTION_MASTER_SID
				,ND.PERIOD
				,ND.YEAR
				,RS_NAME
				,ISNULL(TOTAL_DISCOUNT,0)
				,ISNULL(TOTAL_DISCOUNT_PROJECTED,0)
				,ISNULL(TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,0)
				,ISNULL(TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,0)
				,ISNULL(TOTAL_ACTUAL_RPU,0)
				,ISNULL(TOTAL_PROJECTED_RPU,0)
				,RS_CONTRACT_SID
				,ISNULL(CAST(COALESCE(ND.TOTAL_DISCOUNT / NULLIF(FD.GTS_SALES_ACTUALS, 0), 0) * 100 AS NUMERIC(22,6)),0) AS DISCOUNT_OF_EX_FACTORY_ACTUALS
			    ,ISNULL(CAST(COALESCE(ND.TOTAL_DISCOUNT_PROJECTED/ NULLIF(FD.GTS_SALES_PROJECTED, 0), 0) * 100 AS NUMERIC(22,6)),0) AS DISCOUNT_OF_EX_FACTORY_PROJECTED
			FROM #RESULT_DETAILS_INFO ND 
			INNER JOIN (SELECT YEARLY,PERIOD,SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED FROM #TEMP_GTS_DETAILS GROUP BY YEARLY,PERIOD) FD
		ON FD.PERIOD=ND.PERIOD AND FD.YEARLY=ND.YEAR
		ORDER BY RS_NAME
			,ND.YEAR
			,ND.PERIOD
				
	-----------------prior ccp information projection wise----------------------
			IF object_id('tempdb..#CURRENT_CPP_COMP_PRIOR_CPP') IS NOT NULL
				DROP TABLE #CURRENT_CPP_COMP_PRIOR_CPP

			SELECT PM.PROJECTION_MASTER_SID
				,CC.CCP_DETAILS_SID
			INTO #CURRENT_CPP_COMP_PRIOR_CPP
			FROM #PROJECTION_MASTER PM
			INNER JOIN PROJECTION_DETAILS PD ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
			INNER JOIN CCP_DETAILS CC ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
			WHERE ID = 1
---------------------prior projection nrs information rs wise-------------------
			IF Object_id('TEMPDB.DBO.#PRIOR_RS_DATA', 'U') IS NOT NULL
				DROP TABLE #PRIOR_RS_DATA;

			CREATE TABLE #PRIOR_RS_DATA --GAL-1163
				(
				RS_CONTRACT_SID INT
				,RS_NAME VARCHAR(100)
				)

			INSERT INTO #PRIOR_RS_DATA (
				RS_CONTRACT_SID
				,RS_NAME
				)
			SELECT DISTINCT NMDP.RS_CONTRACT_SID
				,CASE 
					WHEN @PROGRAM_TYPE = 'PROGRAM'
						OR @PROGRAM_TYPE IS NULL
						THEN RS_NAME
					ELSE PRICE_GROUP_TYPE
					END AS DISCOUNT
			FROM NM_DISCOUNT_PROJ_MASTER NMDP
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = NMDP.PROJECTION_DETAILS_SID
				AND EXISTS (
					SELECT PM.PROJECTION_MASTER_SID
					FROM #PROJECTION_MASTER PM
					WHERE ID <> 1
					AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
					)
			INNER JOIN RS_CONTRACT RM ON RM.RS_CONTRACT_SID = NMDP.RS_CONTRACT_SID --GAL-1163
			WHERE EXISTS (
					SELECT 1
					FROM #SPLIT_TABLE A
					WHERE A.TOKEN = NMDP.RS_CONTRACT_SID
					) --GAL-1163
			
			UNION ALL
			
			SELECT DISTINCT RM.RS_CONTRACT_SID AS PPA_RS_CONTRACT_SID
				,CASE 
					WHEN @PROGRAM_TYPE = 'PROGRAM'
						OR @PROGRAM_TYPE IS NULL
						THEN RS_NAME
					ELSE 'Price Protection'
					END AS PPA_DISCOUNT
			FROM NM_PPA_PROJECTION_MASTER PPA
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = PPA.PROJECTION_DETAILS_SID
				AND EXISTS (
					SELECT PM.PROJECTION_MASTER_SID
					FROM #PROJECTION_MASTER PM
					WHERE ID <> 1
					AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
					)
			INNER JOIN RS_CONTRACT RM ON PPA.RS_CONTRACT_SID = RM.RS_CONTRACT_SID --GAL-1163
				AND EXISTS (
					SELECT PROJECTION_MASTER_SID
					FROM #PROJECTION_MASTER PRM
					WHERE PRM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
					)
     
 -------------------------------------PULLING THE discount actuals  FROM discount actual TABLE BASED ON PROJECTION----------------------------
 
			IF Object_id('TEMPDB.DBO.#NM_ACTUAL_DISCOUNT', 'U') IS NOT NULL
				DROP TABLE #NM_ACTUAL_DISCOUNT;

			SELECT PD.PROJECTION_MASTER_SID
				,Sum(NAD.ACTUAL_SALES) AS ACTUAL_SALES
				,P.PERIOD
				,P.YEAR
				,RS_NAME
				,RM.RS_CONTRACT_SID
			INTO #NM_ACTUAL_DISCOUNT
			FROM NM_ACTUAL_DISCOUNT NAD
			INNER JOIN #PRIOR_RS_DATA RM ON RM.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID --GAL-1163
			INNER JOIN #PERIOD P ON P.PERIOD_SID = NAD.PERIOD_SID
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
			INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
				AND EXISTS (
					SELECT PM.PROJECTION_MASTER_SID
					FROM #PROJECTION_MASTER PM
					WHERE ID <> 1
					AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
					)
			GROUP BY PD.PROJECTION_MASTER_SID
				,P.PERIOD
				,P.YEAR
				,RS_NAME
				,RM.RS_CONTRACT_SID
-------------------------------------PULLING THE discount  PROJECTION FROM discount  PROJECTION TABLE BASED ON PROJECTION----------------------------

			IF Object_id('TEMPDB.DBO.#NM_DISCOUNT_PROJECTION', 'U') IS NOT NULL
				DROP TABLE #NM_DISCOUNT_PROJECTION;

			SELECT PD.PROJECTION_MASTER_SID
				,Sum(NSP.PROJECTION_SALES) AS PROJECTION_SALES
				,P.PERIOD
				,P.YEAR
				,RS_NAME
				,RM.RS_CONTRACT_SID
			INTO #NM_DISCOUNT_PROJECTION
			FROM NM_DISCOUNT_PROJECTION NSP
			INNER JOIN #PRIOR_RS_DATA RM ON RM.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID --GAL-1163
			INNER JOIN #PERIOD P ON P.PERIOD_SID = NSP.PERIOD_SID
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
			INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
				AND EXISTS (
					SELECT PM.PROJECTION_MASTER_SID
					FROM #PROJECTION_MASTER PM
					WHERE ID <> 1
					AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
					)
			GROUP BY PD.PROJECTION_MASTER_SID
				,P.PERIOD
				,P.YEAR
				,RS_NAME
				,RM.RS_CONTRACT_SID
-------------------------------------PULLING THE ppa actual information   FROM ppa  actual TABLE BASED ON PROJECTION----------------------------
 
			IF Object_id('TEMPDB.DBO.#NM_ACTUAL_PPA', 'U') IS NOT NULL
				DROP TABLE #NM_ACTUAL_PPA;

			SELECT PROJECTION_MASTER_SID
				,PERIOD AS PERIOD
				,YEAR
				,RS_NAME
				,Sum(ACTUAL_DISCOUNT_DOLLAR) ACTUAL_DISCOUNT_DOLLAR
				,Sum(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(Sum(sales), 0) * 100 AS rate
				,Sum(ACTUAL_UNITS) UNIT
				,Sum(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(Sum(units), 0) RPU
				,RS_CONTRACT_SID
			INTO #NM_ACTUAL_PPA
			FROM (
				SELECT PROJECTION_MASTER_SID
					,PERIOD
					,YEAR
					,RS_NAME
					,Sum(ACTUAL_DISCOUNT_DOLLAR) ACTUAL_DISCOUNT_DOLLAR
					,Min(NULLIF(ACTUAL_UNITS, 0)) ACTUAL_UNITS
					,Sum(ACTUAL_SALES) sales
					,Sum(ACTUAL_UNITS) units
					,RS_CONTRACT_SID
				FROM (
					SELECT PD.PROJECTION_MASTER_SID
						,S.PROJECTION_DETAILS_SID
						,Sum(ACTUAL_DISCOUNT_DOLLAR) ACTUAL_DISCOUNT_DOLLAR
						,Sum(CASE 
								WHEN ACTUAL_DISCOUNT_DOLLAR <> 0
									THEN SP.ACTUAL_SALES
								END) ACTUAL_SALES
						,Sum(SP.ACTUAL_UNITS) ACTUAL_UNITS
						,RM.RS_NAME
						,P.PERIOD
						,P.YEAR
						,RM.RS_CONTRACT_SID
					FROM NM_ACTUAL_PPA S
					INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
					INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
					INNER JOIN NM_ACTUAL_SALES SP ON SP.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
						AND sp.PERIOD_SID = s.PERIOD_SID
					INNER JOIN #PERIOD P ON P.PERIOD_SID = S.PERIOD_SID
					INNER JOIN NM_PPA_PROJECTION_MASTER C ON C.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
						AND C.RS_CONTRACT_SID = S.RS_CONTRACT_SID --GAL-1163
					INNER JOIN #PRIOR_RS_DATA RM ON RM.RS_CONTRACT_SID = S.RS_CONTRACT_SID --GAL-1163
						AND EXISTS (
							SELECT PM.PROJECTION_MASTER_SID
							FROM #PROJECTION_MASTER PM
							WHERE ID <> 1
							AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
							)
					GROUP BY PD.PROJECTION_MASTER_SID
						,S.PROJECTION_DETAILS_SID
						,RM.RS_NAME
						,P.PERIOD
						,P.YEAR
						,RM.RS_CONTRACT_SID
					) G
				GROUP BY PROJECTION_MASTER_SID
					,PERIOD
					,YEAR
					,G.RS_NAME
					,RS_CONTRACT_SID
				) Y
			GROUP BY PROJECTION_MASTER_SID
				,PERIOD
				,YEAR
				,RS_NAME
				,RS_CONTRACT_SID
-------------------------------------PULLING THE ppa  information   FROM ppa  PROJECTION TABLE BASED ON PROJECTION----------------------------

			IF Object_id('TEMPDB.DBO.#NM_PPA_PROJECTION', 'U') IS NOT NULL
				DROP TABLE #NM_PPA_PROJECTION;

			SELECT PROJECTION_MASTER_SID
				,PERIOD AS PERIOD
				,YEAR
				,RS_NAME
				,Sum(PROJECTION_DISCOUNT_DOLLAR) PROJECTION_DISCOUNT_DOLLAR
				,Sum(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(Sum(sales), 0) * 100 AS rate
				,Sum(PROJECTION_DISCOUNT_UNITS1) UNIT
				,Sum(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(Sum(units), 0) RPU
				,RS_CONTRACT_SID
			INTO #NM_PPA_PROJECTION
			FROM (
				SELECT PROJECTION_MASTER_SID
					,PERIOD
					,YEAR
					,RS_NAME
					,Sum(PROJECTION_DISCOUNT_DOLLAR) PROJECTION_DISCOUNT_DOLLAR
					,Min(NULLIF(PROJECTION_UNITS, 0)) PROJECTION_DISCOUNT_UNITS1
					,Sum(PROJECTION_SALES) sales
					,Sum(PROJECTION_UNITS) units
					,RS_CONTRACT_SID
				FROM (
					SELECT PD.PROJECTION_MASTER_SID
						,S.PROJECTION_DETAILS_SID
						,Sum(PROJECTION_DISCOUNT_DOLLAR) PROJECTION_DISCOUNT_DOLLAR
						,Sum(CASE 
								WHEN PROJECTION_DISCOUNT_DOLLAR <> 0
									THEN SP.PROJECTION_SALES
								END) PROJECTION_SALES
						,Sum(SP.PROJECTION_UNITS) PROJECTION_UNITS
						,RM.RS_NAME
						,P.PERIOD
						,P.YEAR
						,RM.RS_CONTRACT_SID
					FROM NM_PPA_PROJECTION S
					INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
					INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
					INNER JOIN NM_SALES_PROJECTION SP ON SP.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
						AND sp.PERIOD_SID = s.PERIOD_SID
					INNER JOIN #PERIOD P ON P.PERIOD_SID = S.PERIOD_SID
					INNER JOIN NM_PPA_PROJECTION_MASTER C ON C.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
						AND C.RS_CONTRACT_SID = S.RS_CONTRACT_SID --GAL-1163
					INNER JOIN #PRIOR_RS_DATA RM ON RM.RS_CONTRACT_SID = S.RS_CONTRACT_SID --GAL-1163
						AND EXISTS (
							SELECT PM.PROJECTION_MASTER_SID
							FROM #PROJECTION_MASTER PM
							WHERE ID <> 1
							AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
							)
					GROUP BY PD.PROJECTION_MASTER_SID
						,S.PROJECTION_DETAILS_SID
						,RM.RS_NAME
						,P.PERIOD
						,P.YEAR
						,RM.RS_CONTRACT_SID
					) G
				GROUP BY PROJECTION_MASTER_SID
					,PERIOD
					,YEAR
					,G.RS_NAME
					,RS_CONTRACT_SID
				) Y
			GROUP BY PROJECTION_MASTER_SID
				,PERIOD
				,YEAR
				,RS_NAME
				,RS_CONTRACT_SID
-------------------------------------PULLING THE sales information   FROM sales TABLE BASED ON PROJECTION----------------------------

			IF Object_id('TEMPDB.DBO.#NM_ACTUAL_SALES', 'U') IS NOT NULL
				DROP TABLE #NM_ACTUAL_SALES;

			SELECT PD.PROJECTION_MASTER_SID
				,Sum(ACTUAL_SALES) ACTUAL_SALES
				,Sum(ACTUAL_UNITS) ACTUAL_UNITS
				,P.PERIOD
				,P.YEAR
			INTO #NM_ACTUAL_SALES
			FROM NM_ACTUAL_SALES NAS
			INNER JOIN #PERIOD P ON P.PERIOD_SID = NAS.PERIOD_SID
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
			INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
				AND EXISTS (
					SELECT PM.PROJECTION_MASTER_SID
					FROM #PROJECTION_MASTER PM
					WHERE ID <> 1
					AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
					)
			GROUP BY PD.PROJECTION_MASTER_SID
				,PERIOD
				,YEAR
-------------------------------------PULLING THE sales information   FROM sales TABLE BASED ON PROJECTION----------------------------

			IF Object_id('TEMPDB.DBO.#NM_SALES_PROJECTION', 'U') IS NOT NULL
				DROP TABLE #NM_SALES_PROJECTION;

			SELECT PD.PROJECTION_MASTER_SID
				,Sum(PROJECTION_SALES) PROJECTION_SALES
				,Sum(PROJECTION_UNITS) PROJECTION_UNITS
				,P.PERIOD
				,P.YEAR
			INTO #NM_SALES_PROJECTION
			FROM NM_SALES_PROJECTION NSP
			INNER JOIN #PERIOD P ON P.PERIOD_SID = NSP.PERIOD_SID
			INNER JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_DETAILS_SID = NSP.PROJECTION_DETAILS_SID
			INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
				AND EXISTS (
					SELECT PM.PROJECTION_MASTER_SID
					FROM #PROJECTION_MASTER PM
					WHERE ID <> 1
					AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
					)
			GROUP BY PD.PROJECTION_MASTER_SID
				,PERIOD
				,YEAR

------------combining sales actual and projection information -----------------------
			IF Object_id('TEMPDB.DBO.#PRIOR_SALES', 'U') IS NOT NULL
				DROP TABLE #PRIOR_SALES;

			SELECT PM.PROJECTION_MASTER_SID
			    ,FD.YEAR
				,FD.PERIOD
				,COALESCE((NAS.ACTUAL_SALES), 0) AS NM_ACTUAL_SALES
				,COALESCE((NAS.ACTUAL_UNITS), 0) AS NM_ACTUAL_UNITS
				,COALESCE((NSP.PROJECTION_SALES), 0) AS NM_PROJECTED_SALES
				,COALESCE((NSP.PROJECTION_UNITS), 0) AS NM_PROJECTED_UNITS
			INTO #PRIOR_SALES
			FROM #PROJECTION_MASTER PM CROSS JOIN (
			SELECT DISTINCT PERIOD
				,YEAR
			FROM #PERIOD
			WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
			) FD
			LEFT JOIN #NM_SALES_PROJECTION NSP ON 
		     PM.PROJECTION_MASTER_SID=NSP.PROJECTION_MASTER_SID AND NSP.YEAR = FD.YEAR
			AND NSP.PERIOD = FD.PERIOD
		    LEFT JOIN #NM_ACTUAL_SALES NAS ON PM.PROJECTION_MASTER_SID=NAS.PROJECTION_MASTER_SID AND NAS.YEAR = FD.YEAR
			AND NAS.PERIOD = FD.PERIOD AND NSP.PROJECTION_MASTER_SID=NAS.PROJECTION_MASTER_SID
			WHERE  PM.ID<>1
------------combining discount actual and projection information -----------------------
			IF Object_id('TEMPDB.DBO.#PRIOR_DISCOUNT', 'U') IS NOT NULL
			DROP TABLE #PRIOR_DISCOUNT;

		SELECT PM.PROJECTION_MASTER_SID 
			,FD.YEAR
			,FD.PERIOD
			,COALESCE((A.ACTUAL_SALES), 0) as ACTUAL_SALES 
			,COALESCE((A.PROJECTION_SALES), 0) as PROJECTION_SALES 
			,FD.RS_NAME
			,FD.RS_CONTRACT_SID
	INTO #PRIOR_DISCOUNT
		FROM #PROJECTION_MASTER PM CROSS JOIN (
			SELECT DISTINCT PERIOD
				,YEAR
				,RS_CONTRACT_SID
				,RS_NAME
			FROM #PERIOD
			CROSS JOIN #RS_DATA
			WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
			) FD 

		  JOIN (
	SELECT COALESCE(NAD.PERIOD, NSP.PERIOD) PERIOD
		,COALESCE(NAD.YEAR, NSP.YEAR) YEAR
		,COALESCE(NAD.RS_NAME, NSP.RS_NAME) RS_NAME
		,COALESCE(NAD.RS_CONTRACT_SID, NSP.RS_CONTRACT_SID) RS_CONTRACT_SID
		,NAD.ACTUAL_SALES
		,NSP.PROJECTION_SALES
		,COALESCE(NAD.PROJECTION_MASTER_SID,NSP.PROJECTION_MASTER_SID)PROJECTION_MASTER_SID
	FROM (
		SELECT ACTUAL_SALES
			,PERIOD
			,YEAR
			,RS_NAME
			,RS_CONTRACT_SID
			,PROJECTION_MASTER_SID
		FROM #NM_ACTUAL_DISCOUNT
		) NAD
	FULL JOIN (
		SELECT PROJECTION_SALES
			,PERIOD
			,YEAR
			,RS_NAME
			,RS_CONTRACT_SID
			,PROJECTION_MASTER_SID
		FROM #NM_DISCOUNT_PROJECTION
		) NSP ON NAD.PERIOD = NSP.PERIOD
		AND NAD.YEAR = NSP.YEAR
		AND NAD.RS_NAME = NSP.RS_NAME
		AND NAD.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
		AND NAD.PROJECTION_MASTER_SID=NSP.PROJECTION_MASTER_SID
	) A ON FD.PERIOD = A.PERIOD
	AND FD.YEAR = A.YEAR
	AND FD.RS_NAME = A.RS_NAME
	AND FD.RS_CONTRACT_SID = A.RS_CONTRACT_SID
	AND PM.PROJECTION_MASTER_SID=A.PROJECTION_MASTER_SID
				WHERE  PM.ID<>1
ORDER BY FD.PERIOD
	,FD.YEAR
	,FD.RS_NAME
	,FD.RS_CONTRACT_SID

------------combining ppa actual and projection information -----------------------
			IF Object_id('TEMPDB.DBO.#PRIOR_PPA', 'U') IS NOT NULL
			DROP TABLE #PRIOR_PPA;

		SELECT PM.PROJECTION_MASTER_SID
			,FD.YEAR
			,FD.PERIOD
			, COALESCE((A.ACTUAL_DISCOUNT_DOLLAR), 0) as PPA_ACTUAL 
			,COALESCE((A.RATE), 0) as PPA_RATE 
			,COALESCE((A.RPU), 0) as PPA_RPU 
			, COALESCE((A.PROJECTION_DISCOUNT_DOLLAR), 0) as PPA_DISCOUNT_PROJECTED
			,COALESCE((A.RATE), 0) as PPA_RATE_PROJECTION 
			, COALESCE((A.RPU), 0) as PPA_RPU_PROJECTION 
			,FD.RS_NAME
			,FD.RS_CONTRACT_SID
		INTO #PRIOR_PPA
		FROM #PROJECTION_MASTER PM CROSS JOIN (
			SELECT DISTINCT PERIOD
				,YEAR
				,RS_CONTRACT_SID
				,RS_NAME
			FROM #PERIOD
			CROSS JOIN #RS_DATA
			WHERE PERIOD_SID BETWEEN @START_PERIOD_SID
					AND @END_PERIOD_SID
			) FD
		JOIN (
	SELECT COALESCE(NAD.PERIOD, NSP.PERIOD) PERIOD
		,COALESCE(NAD.YEAR, NSP.YEAR) YEAR
		,COALESCE(NAD.RS_NAME, NSP.RS_NAME) RS_NAME
		,COALESCE(NAD.RS_CONTRACT_SID, NSP.RS_CONTRACT_SID) RS_CONTRACT_SID
		,COALESCE(NAD.PROJECTION_MASTER_SID,NSP.PROJECTION_MASTER_SID)PROJECTION_MASTER_SID
		,NAD.ACTUAL_DISCOUNT_DOLLAR
		,NSP.PROJECTION_DISCOUNT_DOLLAR
		,NAD.RATE AS ACTUAL_RATE
		,NAD.RPU AS ACTUAL_RPU
		,NSP.RATE
		,NSP.RPU
	FROM (
		SELECT ACTUAL_DISCOUNT_DOLLAR
			,RATE
			,RPU
			,UNIT
			,PERIOD
			,YEAR
			,RS_NAME
			,RS_CONTRACT_SID
			,PROJECTION_MASTER_SID
		FROM #NM_ACTUAL_PPA
		) NAD
	FULL JOIN (
		SELECT PROJECTION_DISCOUNT_DOLLAR
			,RATE
			,RPU
			,UNIT
			,PERIOD
			,YEAR
			,RS_NAME
			,RS_CONTRACT_SID
			,PROJECTION_MASTER_SID
		FROM #NM_PPA_PROJECTION
		) NSP ON NAD.PERIOD = NSP.PERIOD
		AND NAD.YEAR = NSP.YEAR
		AND NAD.RS_NAME = NSP.RS_NAME
		AND NAD.RS_CONTRACT_SID = NSP.RS_CONTRACT_SID
		AND NAD.PROJECTION_MASTER_SID=NSP.PROJECTION_MASTER_SID
	) A ON FD.PERIOD = A.PERIOD
	AND FD.YEAR = A.YEAR
	AND FD.RS_NAME = A.RS_NAME
	AND FD.RS_CONTRACT_SID = A.RS_CONTRACT_SID
	AND PM.PROJECTION_MASTER_SID=A.PROJECTION_MASTER_SID
	WHERE PM.ID<>1
ORDER BY FD.PERIOD
	,FD.YEAR
	,FD.RS_NAME
	,FD.RS_CONTRACT_SID
	
	----------------final update-------------------
			INSERT INTO #RESULT_DETAILS_INFO (
				PROJECTION_MASTER_SID
				,YEAR
				,PERIOD
				,RS_NAME
				,TOTAL_DISCOUNT
				,TOTAL_DISCOUNT_PROJECTED
				,TOTAL_DISCOUNT_ACTUAL_PERCENTAGE
				,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE
				,TOTAL_ACTUAL_RPU
				,TOTAL_PROJECTED_RPU
				,RS_CONTRACT_SID
				)
			SELECT RD.PROJECTION_MASTER_SID
				,ND.YEAR
				,ND.PERIOD
				,RS_NAME
				,ND.ACTUAL_SALES
				,ND.PROJECTION_SALES
				,COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_SALES, 0), 0) * 100
				,COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_SALES, 0), 0) * 100
				,COALESCE(ND.ACTUAL_SALES / NULLIF(RD.NM_ACTUAL_UNITS, 0), 0)
				,COALESCE(ND.PROJECTION_SALES / NULLIF(RD.NM_PROJECTED_UNITS, 0), 0)
				,RS_CONTRACT_SID
			FROM #PRIOR_DISCOUNT ND
			INNER JOIN #PRIOR_SALES RD ON  ND.PROJECTION_MASTER_SID=RD.PROJECTION_MASTER_SID AND  RD.YEAR = ND.YEAR
				AND RD.PERIOD = ND.PERIOD
			
			UNION 
			
			SELECT PROJECTION_MASTER_SID
				,PD.YEAR
				,PD.PERIOD
				,RS_NAME
				,PD.PPA_ACTUAL
				,PD.PPA_DISCOUNT_PROJECTED
				,PPA_RATE
				,PPA_RATE_PROJECTION
				,PPA_RPU
				,PPA_RPU_PROJECTION
				,RS_CONTRACT_SID
			FROM #PRIOR_PPA PD

			INSERT INTO #PIVOT_RESULT (
				PROJECTION_ID
				,[PERIOD]
				,[YEAR]
				,RS_NAME
				,CONTRACT_DISCOUNT_ACTUAL
				,CONTRACT_DISCOUNT_PROJECTED
				,TOTAL_DISCOUNT_ACTUAL_PERCENTAGE
				,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE
				,TOTAL_RPU_ACTUAL
				,TOTAL_RPU_PROJECTED
				,RS_CONTRACT_SID
				,DISCOUNT_OF_EX_FACTORY_ACTUALS
				,DISCOUNT_OF_EX_FACTORY_PROJECTED
				)
			SELECT DISTINCT RD.PROJECTION_MASTER_SID
			,RD.PERIOD
			,RD.YEAR
			,RS_NAME
			,ISNULL(TOTAL_DISCOUNT,0)
			,ISNULL(TOTAL_DISCOUNT_PROJECTED,0)
			,ISNULL(TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,0)
			,ISNULL(TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,0)
			,ISNULL(TOTAL_ACTUAL_RPU,0)
			,ISNULL(TOTAL_PROJECTED_RPU,0)
			,RS_CONTRACT_SID
			,ISNULL(cast(COALESCE(RD.TOTAL_DISCOUNT / NULLIF(FD.GTS_SALES_ACTUALS, 0), 0) * 100 AS NUMERIC(22, 6)),0) AS DISCOUNT_OF_EX_FACTORY_ACTUALS
			, ISNULL(cast(COALESCE(RD.TOTAL_DISCOUNT_PROJECTED / NULLIF(FD.GTS_SALES_PROJECTED, 0), 0) * 100 AS NUMERIC(22, 6)),0) AS DISCOUNT_OF_EX_FACTORY_PROJECTED
		FROM #RESULT_DETAILS_INFO RD
		LEFT JOIN (SELECT PROJECTION_MASTER_SID,P.PERIOD,P.YEAR,SUM(EXFACTORY_ACTUAL_SALES) AS GTS_SALES_ACTUALS,
                                  SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) AS GTS_SALES_PROJECTED
                                 FROM  #PRODUCT_FILE_TEMP PF JOIN #PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                              GROUP  BY PROJECTION_MASTER_SID,P.PERIOD,P.YEAR) FD ON FD.PROJECTION_MASTER_SID=RD.PROJECTION_MASTER_SID
							 AND  FD.YEAR = RD.YEAR
			AND FD.PERIOD = RD.PERIOD
			  WHERE NOT EXISTS(SELECT 1 FROM #PIVOT_RESULT A
			WHERE RD.PROJECTION_MASTER_SID=A.PROJECTION_ID)
		ORDER BY RS_NAME
			,RD.YEAR
			,RD.PERIOD
			,RS_CONTRACT_SID
			
			-------------------------------PIVOT START---------------------------------------------------------
			DECLARE @SQL1 NVARCHAR(MAX)
				,@LOOP_CNTR INT
				,@MAX_CCP INT

			SET @SQL1 = 'SELECT YEAR,PERIOD,RS_NAME,'
			SET @LOOP_CNTR = 1
			SET @MAX_CCP = (
					SELECT Max(ID)
					FROM #PROJECTION_MASTER
					)

			WHILE @LOOP_CNTR <= @MAX_CCP
			BEGIN
				IF @LOOP_CNTR=1
		BEGIN
			SET @SQL1 += 'CONTRACT_DISCOUNT_ACTUAL_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN CONTRACT_DISCOUNT_ACTUAL END),0) ELSE NULL END, 
								  CONTRACT_DISCOUNT_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN CONTRACT_DISCOUNT_PROJECTED END),0), 
								  TOTAL_DISCOUNT_ACTUAL_PERCENTAGE_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' =CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN TOTAL_DISCOUNT_ACTUAL_PERCENTAGE END),0) ELSE NULL END,      
                                  TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                                   TOTAL_RPU_ACTUAL_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' =CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 
				                  'THEN TOTAL_RPU_ACTUAL END),0) ELSE NULL END,
                                   TOTAL_RPU_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN TOTAL_RPU_PROJECTED END),0),
                                   DISCOUNT_OF_EX_FACTORY_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' =CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),0) ELSE NULL END,
                                   DISCOUNT_OF_EX_FACTORY_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0),'
		
			END 
			ELSE 
			BEGIN
			SET @SQL1 += 'CONTRACT_DISCOUNT_ACTUAL_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN CONTRACT_DISCOUNT_ACTUAL END),0), 
								  CONTRACT_DISCOUNT_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN CONTRACT_DISCOUNT_PROJECTED END),0), 
								  TOTAL_DISCOUNT_ACTUAL_PERCENTAGE_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN TOTAL_DISCOUNT_ACTUAL_PERCENTAGE END),0),      
                                  TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                                   TOTAL_RPU_ACTUAL_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 
					'THEN TOTAL_RPU_ACTUAL END),0),
                                   TOTAL_RPU_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN TOTAL_RPU_PROJECTED END),0),
								     DISCOUNT_OF_EX_FACTORY_ACTUALS_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' =ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),0),
                                   DISCOUNT_OF_EX_FACTORY_PROJECTED_' + Cast(@LOOP_CNTR AS VARCHAR(10)) + ' = ISNULL(MAX(CASE WHEN ID = ' + Cast(@LOOP_CNTR AS VARCHAR(10)) + 'THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0),'
				END
				
				SET @LOOP_CNTR += 1
			END

			SET @SQL1 = LEFT(@SQL1, Len(@SQL1) - 1)
			SET @SQL1 += ' ,RS_CONTRACT_SID FROM   #PIVOT_RESULT PR
                                                     INNER JOIN #PROJECTION_MASTER PM
                                                             ON PR.PROJECTION_ID = PM.PROJECTION_MASTER_SID
                                                       GROUP  BY [YEAR],PERIOD,RS_NAME,RS_CONTRACT_SID
                                                       ORDER BY RS_NAME,RS_CONTRACT_SID,[YEAR],PERIOD'

			EXEC SP_EXECUTESQL @SQL1
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

		SELECT @ERRORMESSAGE = ERROR_MESSAGE()
			,@ERRORSEVERITY = ERROR_SEVERITY()
			,@ERRORSTATE = ERROR_STATE()
			,@ERRORPROCEDURE = ERROR_PROCEDURE()
			,@ERRORLINE = ERROR_LINE()
			,@ERRORNUMBER = ERROR_NUMBER()

		RAISERROR (
				@ERRORMESSAGE
				,
				-- MESSAGE TEXT.
				@ERRORSEVERITY
				,
				-- SEVERITY.
				@ERRORSTATE
				,
				-- STATE.
				@ERRORPROCEDURE
				,
				-- PROCEDURE_NAME.
				@ERRORNUMBER
				,
				-- ERRORNUMBER
				@ERRORLINE -- ERRORLINE
				);
	END CATCH
END


GO



