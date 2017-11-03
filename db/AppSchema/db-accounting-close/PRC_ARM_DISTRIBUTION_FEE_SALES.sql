IF EXISTS (
		SELECT 'X'
		FROM INFORMATION_SCHEMA.ROUTINES
		WHERE ROUTINE_NAME = 'PRC_ARM_DISTRIBUTION_FEE_SALES'
			AND ROUTINE_SCHEMA = 'DBO'
		)
BEGIN
	DROP PROCEDURE [DBO].[PRC_ARM_DISTRIBUTION_FEE_SALES]
END
GO

CREATE PROCEDURE [dbo].[PRC_ARM_DISTRIBUTION_FEE_SALES] (
	@PROJECTION_MASTER_SID INT
	,@DATE_TYPE VARCHAR(20)
	,@PRICE VARCHAR(20)
	,@OVERRIDE BIT = 0
	,@USER_ID INT
	,@SESSION_ID INT
	)
AS
/**********************************************************************************************************
** File Name		:	PRC_ARM_DISTRIBUTION_FEE_SALES.sql
** Procedure Name	:	PRC_ARM_DISTRIBUTION_FEE_SALES
** Description		:	To generate Distribution Fee (Tr-7) Sales tab
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Tr-7
						@DATE_TYPE              - It is used to differntiate Pulling Customer Gts actual amount based on which data type
						@PRICE                  - It indicates the period for pulling Price
						@OVERRIDE               - It is used to find out user gave Price override in UI or not. if no override it will be zero,otherwise 1
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
											
** Output Parameters:	NA
** Author Name		:	@Paul,@Venkatesh,@Reddy
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application for Distribution Fee Sales Transaction(as base),
                        if new Transaction created with Distrinution fee as base Transaction then application will call this procedure. 
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------
** 1    11/08/2016  GAL-8283        @Ajay             Temp table Changes.
** 2	08/07/2017  GAL-12264       @Kishore Kumar	  MI003 - Unqualified column name
*********************************************************************************************************/
BEGIN
	SET NOCOUNT ON

	BEGIN TRY
		-- Variables Initialization starts here
		DECLARE @SQL NVARCHAR(MAX)
		DECLARE @DISTRIBUTION_FEES_SALES_TABLE VARCHAR(200) = CONCAT (
				'ST_ARM_DISTRIBUTION_FEES_SALES_'
				,@USER_ID
				,'_'
				,@SESSION_ID
				,'_'
				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
				)
			,@PIPELINE_EXCL_DETAILS_TABLE VARCHAR(200) = CONCAT (
				'ST_ARM_PIPELINE_EXCLUSION_DETAILS_'
				,@USER_ID
				,'_'
				,@SESSION_ID
				,'_'
				,REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')
				)

		-- Variables Initialization ends here
		IF (@OVERRIDE = 0)
		BEGIN
			-- Variables Initialization for this no override block starts here
			DECLARE @PERIOD_SID INT
				,@MONTH INT
				,@YEAR INT
				,@FORECAST_NAME_EXFACTORY VARCHAR(50)
				,@FORECAST_VERSION_EXFACTORY VARCHAR(50)
				,@ITEM_UDT UDT_ITEM
				,@PRICE_PERIOD_SID INT
				,@PRICE_PERIOD_DATE DATETIME
				,@PRICE_MONTH INT
				,@PRICE_YEAR INT
				,@BUISNESS_UNIT INT
				,@GL_COMP_COMPANY INT

			-- Variables Initialization for this no override block ends here
			--taking price related period selected in data selection and assigning GL and BU to variable starts here 
			SELECT @PRICE = REPLACE(@PRICE, ' ', ',')

			SELECT @PRICE_MONTH = CASE 
					WHEN TOKEN = 'JAN'
						THEN 1
					WHEN TOKEN = 'FEB'
						THEN 2
					WHEN TOKEN = 'MAR'
						THEN 3
					WHEN TOKEN = 'APR'
						THEN 4
					WHEN TOKEN = 'MAY'
						THEN 5
					WHEN TOKEN = 'JUN'
						THEN 6
					WHEN TOKEN = 'JUL'
						THEN 7
					WHEN TOKEN = 'AUG'
						THEN 8
					WHEN TOKEN = 'SEP'
						THEN 9
					WHEN TOKEN = 'OCT'
						THEN 10
					WHEN TOKEN = 'NOV'
						THEN 11
					WHEN TOKEN = 'DEC'
						THEN 12
					END
			FROM UDF_SPLITSTRING(@PRICE, ',')
			WHERE TRY_CONVERT(INT, TOKEN) IS NULL

			SELECT @PRICE_YEAR = TOKEN
			FROM UDF_SPLITSTRING(@PRICE, ',')
			WHERE TRY_CONVERT(INT, TOKEN) IS NOT NULL

			SELECT @PRICE_PERIOD_SID = PERIOD_SID
				,@PRICE_PERIOD_DATE = PERIOD_DATE
			FROM PERIOD
			WHERE MONTH = @PRICE_MONTH
				AND YEAR = @PRICE_YEAR

			SELECT @PERIOD_SID = P.PERIOD_SID
				,@MONTH = P.MONTH
				,@YEAR = P.YEAR
			FROM PROJECTION_MASTER A
			INNER JOIN PERIOD P
				--ON A.FROM_DATE = P.PERIOD_DATE 
				ON CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A.FROM_DATE, 0)))) = P.PERIOD_DATE
			WHERE A.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

			SELECT @BUISNESS_UNIT = BU_COMPANY_MASTER_SID
			FROM ARM_ADJUSTMENT_MASTER
			WHERE PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

			SELECT @GL_COMP_COMPANY = COMPANY_MASTER_SID
			FROM PROJECTION_MASTER
			WHERE PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

			--taking price related period selected in data selection and assigning GL and BU to variable Ends here 
			-- Pulling CCP+D Combination for Current projection starts here
			IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_DETAILS') IS NOT NULL
				DROP TABLE #TEMP_ARM_PROJ_DETAILS

			CREATE TABLE #TEMP_ARM_PROJ_DETAILS (
				PROJECTION_MASTER_SID INT
				,COMPANY_MASTER_SID INT
				,ITEM_MASTER_SID INT
				)

			INSERT INTO #TEMP_ARM_PROJ_DETAILS (
				PROJECTION_MASTER_SID
				,COMPANY_MASTER_SID
				,ITEM_MASTER_SID
				)
			SELECT DISTINCT AD.PROJECTION_MASTER_SID
				,CCP.COMPANY_MASTER_SID
				,CCP.ITEM_MASTER_SID
			FROM ARM_ADJUSTMENT_DETAILS AD
			INNER JOIN CCP_DETAILS CCP ON AD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
			WHERE AD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID

			-- Pulling CCP+D Combination for Current projection ends here
			--Pulling product related information for current projection starts here
			IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_ITEM') IS NOT NULL
				DROP TABLE #TEMP_ARM_PROJ_ITEM

			CREATE TABLE #TEMP_ARM_PROJ_ITEM (
				PROJECTION_MASTER_SID INT
				,ITEM_MASTER_SID INT
				,ITEM_ID VARCHAR(50)
				,PERIOD_SID INT
				,PRIMARY KEY (
					ITEM_ID
					,ITEM_MASTER_SID
					,PROJECTION_MASTER_SID
					)
				)

			INSERT INTO #TEMP_ARM_PROJ_ITEM (
				ITEM_ID
				,ITEM_MASTER_SID
				,PROJECTION_MASTER_SID
				,PERIOD_SID
				)
			SELECT DISTINCT IM.ITEM_ID
				,A.ITEM_MASTER_SID
				,@PROJECTION_MASTER_SID
				,@PERIOD_SID
			FROM #TEMP_ARM_PROJ_DETAILS A
			INNER JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID

			--Pulling product related information for current projection ends here
			--Pulling Actual Amount from Cutsomer Gts actual for C+I Combination starts here
			IF OBJECT_ID('TEMPDB..#TEMP_CUST_GTS') IS NOT NULL
				DROP TABLE #TEMP_CUST_GTS

			CREATE TABLE #TEMP_CUST_GTS (
				ITEM_MASTER_SID INT
				,ITEM_ID VARCHAR(50)
				,COMPANY_MASTER_SID INT
				,ACCOUNT_ID VARCHAR(50)
				,AMOUNT NUMERIC(22, 6)
				,QUANTITY NUMERIC(22, 6)
				,PERIOD_SID INT
				)

			INSERT INTO #TEMP_CUST_GTS (
				ITEM_MASTER_SID
				,ITEM_ID
				,COMPANY_MASTER_SID
				,ACCOUNT_ID
				,AMOUNT
				,QUANTITY
				,PERIOD_SID
				)
			SELECT B.ITEM_MASTER_SID
				,A.ITEM_ID
				,C.COMPANY_MASTER_SID
				,A.ACCOUNT_ID
				,A.AMOUNT
				,A.QUANTITY
				,@PERIOD_SID
			FROM [DBO].[CUSTOMER_GTS_ACTUAL] A
			INNER JOIN #TEMP_ARM_PROJ_ITEM B ON A.ITEM_ID = B.ITEM_ID
			INNER JOIN COMPANY_MASTER C ON C.COMPANY_ID = A.ACCOUNT_ID
			WHERE MONTH(CASE 
						WHEN @DATE_TYPE = 'INVOICE_DATE'
							THEN A.INVOICE_DATE
						WHEN @DATE_TYPE = 'ORDER_RECEIVED_DATE'
							THEN A.ORDER_RECEIVED_DATE
						WHEN @DATE_TYPE = 'CREATED_DATE'
							THEN A.CREATED_DATE
						WHEN @DATE_TYPE = 'MODIFIED_DATE'
							THEN A.INVOICE_DATE
						END) = @MONTH
				AND YEAR(CASE 
						WHEN @DATE_TYPE = 'INVOICE_DATE'
							THEN A.INVOICE_DATE
						WHEN @DATE_TYPE = 'ORDER_RECEIVED_DATE'
							THEN A.ORDER_RECEIVED_DATE
						WHEN @DATE_TYPE = 'CREATED_DATE'
							THEN A.CREATED_DATE
						WHEN @DATE_TYPE = 'MODIFIED_DATE'
							THEN A.INVOICE_DATE
						END) = @YEAR

			--Pulling Actual Amount from Cutsomer Gts actual for C+I Combination ends here
			-- Find out the Excluded Customers for the current Projection (Note: GAL-8283 Chnages also included here) starts here
			IF OBJECT_ID('TEMPDB..#TEMP_EXCLUDE_CUST') IS NOT NULL
				DROP TABLE #TEMP_EXCLUDE_CUST

			CREATE TABLE #TEMP_EXCLUDE_CUST (COMPANY_MASTER_SID INT PRIMARY KEY)

			SET @SQL = CONCAT (
					'INSERT INTO #TEMP_EXCLUDE_CUST (COMPANY_MASTER_SID) SELECT COMPANY_MASTER_SID FROM '
					,@PIPELINE_EXCL_DETAILS_TABLE
					,' WHERE  PROJECTION_MASTER_SID = '
					,@PROJECTION_MASTER_SID
					)

			EXEC sp_executesql @SQL

			-- Find out the Excluded Customers for the current Projection (Note: GAL-8283 Chnages also included here) ends here
			--Pulling Exfactory/ITEM Pricing  Price based on item  strats here(Note: Item and Price for current Ptojection stores in below temp table) starts here
			IF OBJECT_ID('TEMPDB..#TEMP_PRICE') IS NOT NULL
				DROP TABLE #TEMP_PRICE

			CREATE TABLE #TEMP_PRICE (
				ITEM_MASTER_SID INT PRIMARY KEY
				,PRICE NUMERIC(22, 6)
				)

			INSERT INTO @ITEM_UDT (ITEM_MASTER_SID)
			SELECT ITEM_MASTER_SID
			FROM #TEMP_ARM_PROJ_ITEM

			--Price Period select in UI less than getdate() we need to take price from ITEM Pricing starts here
			IF (@PRICE_PERIOD_DATE < DATEADD(MM, DATEDIFF(MM, 0, GETDATE()), 0))
			BEGIN
				INSERT INTO #TEMP_PRICE (
					ITEM_MASTER_SID
					,PRICE
					)
				SELECT ITEM_MASTER_SID
					,ITEM_PRICE
				FROM [DBO].[UDF_ITEM_PRICING](@ITEM_UDT, 'WAC', @PRICE_PERIOD_SID, @PRICE_PERIOD_SID, 'EACH')
			END
					-- Price Period select in UI less than getdate() we need to take price from ITEM Pricing ends here
			ELSE
				-- Price Period select in UI greater than getdate() pulling price from exfactory starts here 
			BEGIN
				SELECT @FORECAST_NAME_EXFACTORY = FORECAST_NAME
					,@FORECAST_VERSION_EXFACTORY = VERSION
				FROM (
					SELECT FT.FORECAST_NAME
						,FT.[VERSION]
						,FT.FILE_MANAGEMENT_SID
						,HT.[DESCRIPTION] AS FILE_TYPE
						,ROW_NUMBER() OVER (
							PARTITION BY FT.FILE_TYPE ORDER BY FT.FILE_MANAGEMENT_SID DESC
							) AS RN
					FROM FILE_MANAGEMENT FT
					INNER JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
					WHERE (
							CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE())
							AND FT.FROM_PERIOD IS NOT NULL
							)
						AND (
							CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE())
							OR FT.TO_PERIOD IS NULL
							)
						AND HT.LIST_NAME = 'FILE_TYPE'
						AND HT.[DESCRIPTION] IN ('EX-FACTORY SALES')
						AND FT.BUSINESS_UNIT = @BUISNESS_UNIT
						AND FT.COMPANY = @GL_COMP_COMPANY
					) A
				WHERE RN = 1

				INSERT INTO #TEMP_PRICE (
					ITEM_MASTER_SID
					,PRICE
					)
				SELECT B.ITEM_MASTER_SID
					,A.PRICE
				FROM FORECASTING_MASTER A
				INNER JOIN #TEMP_ARM_PROJ_ITEM B ON A.NDC = B.ITEM_ID
				WHERE A.FORECAST_MONTH = @PRICE_MONTH
					AND A.FORECAST_YEAR = @PRICE_YEAR
					AND A.FORECAST_NAME = @FORECAST_NAME_EXFACTORY
					AND A.FORECAST_VER IN (
						@FORECAST_VERSION_EXFACTORY
						,FLOOR(@FORECAST_VERSION_EXFACTORY)
						)
					-- Price Period select in UI greater than getdate() pulling price from exfactory ends here 
			END

			--Pulling Exfactory/ITEM Pricing  Price based on item (insertion into #TEMP_PRICE also ends here)
			--Inserting into main table starts here for both  Tr-7(GAL-8283 Chnages Included here) starts here          
			SET @SQL = CONCAT (
					'TRUNCATE TABLE '
					,@DISTRIBUTION_FEES_SALES_TABLE
					,'  

					  INSERT INTO '
					,@DISTRIBUTION_FEES_SALES_TABLE
					,'
                                  (PROJECTION_MASTER_SID, 
                                   ITEM_MASTER_SID,
                                   COMPANY_MASTER_SID, 
                                   PERIOD_SID, 
                                   TOTAL_SALES, 
                                   TOTAL_UNITS, 
                                   EXCLUDED_SALES, 
                                   EXCLUDED_UNITS, 
                                   NET_SALES, 
                                   NET_UNITS, 
                                   PRICE, 
                                   NET_CALCULATED_SALES, 
                                   SALES_VARIANCE, 
                                   SALES_VARIANCE_PERCENT) 
                      SELECT '
					,@PROJECTION_MASTER_SID
					,', 
                             AD.ITEM_MASTER_SID,
                             AD.COMPANY_MASTER_SID,'
					,@PERIOD_SID
					,
					', 
                             ISNULL(A.TOTAL_SALES, 0), 
                             ISNULL(A.TOTAL_UNITS, 0), 
                             ISNULL(B.EXCLUDED_SALES, 0), 
                             ISNULL(B.EXCLUDED_UNITS, 0), 
                             ISNULL(( ISNULL(A.TOTAL_SALES, 0) - ISNULL(B.EXCLUDED_SALES, 0) ), 0)                                                                                                              AS NET_SALES,
                             ISNULL(( ISNULL(A.TOTAL_UNITS, 0) - ISNULL(B.EXCLUDED_UNITS, 0) ), 0)                                                                                                              AS NET_UNITS,
                             ISNULL(C.PRICE, 0), 
                             ISNULL(( ISNULL(A.TOTAL_UNITS, 0) - ISNULL(B.EXCLUDED_UNITS, 0) ) * ISNULL(C.PRICE, 0), 0)                                                                                         AS NET_CALCULATED_SALES,
                             ISNULL(( ( ISNULL(A.TOTAL_UNITS, 0) - ISNULL(B.EXCLUDED_UNITS, 0) ) * ISNULL(C.PRICE, 0) ) - ( ISNULL(A.TOTAL_SALES, 0) - ISNULL(B.EXCLUDED_SALES, 0) ), 0)                        AS SALES_VARIANCE,
                             COALESCE(( ( ( ISNULL(A.TOTAL_UNITS, 0) - ISNULL(B.EXCLUDED_UNITS, 0) ) * ISNULL(C.PRICE, 0) ) / NULLIF(( ISNULL(A.TOTAL_SALES, 0) - ISNULL(B.EXCLUDED_SALES, 0) ), 0) ), 0) * 100 AS SALES_VARIANCE_PERCENT
                      FROM   #TEMP_ARM_PROJ_DETAILS AD
					  LEFT JOIN (SELECT PROJECTION_MASTER_SID, 
                                     ITEM_MASTER_SID, 
                                     PERIOD_SID 
                              FROM   #TEMP_ARM_PROJ_ITEM) X 
							  ON AD.ITEM_MASTER_SID=X.ITEM_MASTER_SID
                             LEFT JOIN (SELECT ITEM_MASTER_SID,COMPANY_MASTER_SID, 
                                               SUM(AMOUNT)   AS TOTAL_SALES, 
                                               SUM(QUANTITY) AS TOTAL_UNITS 
                                        FROM   #TEMP_CUST_GTS 
                                        GROUP  BY COMPANY_MASTER_SID,ITEM_MASTER_SID) A 
                                    ON COALESCE(AD.ITEM_MASTER_SID,X.ITEM_MASTER_SID) = A.ITEM_MASTER_SID 
									AND AD.COMPANY_MASTER_SID=A.COMPANY_MASTER_SID 
                             LEFT JOIN (SELECT ITEM_MASTER_SID,A.COMPANY_MASTER_SID, 
                                               SUM(A.AMOUNT)   AS EXCLUDED_SALES, 
                                               SUM(A.QUANTITY) AS EXCLUDED_UNITS 
                                        FROM   #TEMP_CUST_GTS A 
                                               INNER JOIN #TEMP_EXCLUDE_CUST B 
                                                       ON A.COMPANY_MASTER_SID = B.COMPANY_MASTER_SID
                                        GROUP  BY A.COMPANY_MASTER_SID,ITEM_MASTER_SID) B 
                                    ON COALESCE(AD.ITEM_MASTER_SID,A.ITEM_MASTER_SID) = B.ITEM_MASTER_SID
                                  AND COALESCE(AD.COMPANY_MASTER_SID,A.COMPANY_MASTER_SID)=B.COMPANY_MASTER_SID 
                             LEFT JOIN #TEMP_PRICE C 
                                    ON C.ITEM_MASTER_SID = COALESCE(AD.ITEM_MASTER_SID ,B.ITEM_MASTER_SID)'
					)

			EXEC sp_executesql @SQL
		END
				--Inserting into main table starts here for both  Tr-7(GAL-8283 Chnages Included here) starts here   
		ELSE
			--updating Tr-7 table based on override calculation starts here
		BEGIN
			SET @SQL = CONCAT (
					'UPDATE '
					,@DISTRIBUTION_FEES_SALES_TABLE
					,' 
                      SET    NET_CALCULATED_SALES = ( NET_UNITS * ISNULL(PRICE_OVERRIDE, 0) ),
                             SALES_VARIANCE = ( NET_UNITS * ISNULL(PRICE_OVERRIDE, 0) ) - NET_SALES,
                             SALES_VARIANCE_PERCENT = ( ( ( NET_UNITS * ISNULL(PRICE_OVERRIDE, 0) ) - NET_SALES ) / NULLIF(NET_SALES, 0) ) * 100
                      WHERE  PROJECTION_MASTER_SID = '
					,@PROJECTION_MASTER_SID
					,' 
                             AND PRICE_OVERRIDE IS NOT NULL '
					)

			EXEC sp_executesql @SQL
		END
				--updating Tr-7 table based on override calculation Ends here
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
				,-- MESSAGE TEXT.        
				@ERRORSEVERITY
				,-- SEVERITY.        
				@ERRORSTATE
				,-- STATE.        
				@ERRORPROCEDURE
				,-- PROCEDURE_NAME.        
				@ERRORNUMBER
				,-- ERRORNUMBER        
				@ERRORLINE -- ERRORLINE        
				);
	END CATCH
END
GO