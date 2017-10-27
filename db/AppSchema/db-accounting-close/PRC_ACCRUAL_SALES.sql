IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_ACCRUAL_SALES'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_ACCRUAL_SALES]
  END

GO

CREATE PROCEDURE [dbo].[PRC_ACCRUAL_SALES](@PROJECTION   INT,
                                           @USER_ID      INT,
                                           @SESSION_ID   VARCHAR(100),
                                           @PERIOD_BASIS VARCHAR(100))
AS

/**********************************************************************************************************
** File Name		:	PRC_ACCRUAL_SALES.sql
** Procedure Name	:	PRC_ACCRUAL_SALES
** Description		:	To generate Accrual Sales(ARP Sales) tab
** Input Parameters	:	@PROJECTION             - Respective Projection ID Creted for Accrual Rate Projection
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
						@PERIOD_BASIS           - it is the identification of Period basis selected in data selection.
						                          based on this we will pull values from files and we will perform calculation.
											
** Output Parameters:	NA
** Author Name		:	@Paul
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application in Accrual Sales tab to generate sales
                        
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------
**    
*********************************************************************************************************/

  BEGIN
      SET NOCOUNT ON
      BEGIN TRY
-- Variables Initialization and taking from date as 12 months history and 36 months future Starts here
        DECLARE @CURRENT_MONTH DATETIME = Dateadd(mm, Datediff(mm, 0, Getdate()), 0)
          DECLARE @FROM DATETIME = Dateadd(mm, Datediff(mm, 0, Getdate()) - 12, 0),--(CURRENT - 12 Months)
                  @TO   DATETIME = Dateadd(mm, Datediff(mm, 0, Getdate()) + 36, 0) - 1-- (+36 Months-- )
          DECLARE @CUST_ITEM                      UDT_CUST_ITEM,
                  @FORECAST_NAME_GTS_CUST_PROD    VARCHAR(50),-- ACTIVE FILE NAME IN FILE MANAGEMENT
                  @FORECAST_VERSION_GTS_CUST_PROD VARCHAR(15),
                  @START_PERIOD                   INT,
                  @END_PERIOD                     INT,
                  @BUSINESS_UNIT INT--------------------GAL-808
				  ,@COMPANY_ID INT
-- Variables Initialization and taking from date as 12 months history and 36 months future Ends here
--temp table chnages and table assigning starts here
				  DECLARE @SQL NVARCHAR(MAX)='',
				  @ARP_PROJ_DETAILS     VARCHAR(200) = CONCAT('ST_ACCRUAL_PROJ_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
				  @PRODUCT_FILE     VARCHAR(200) = CONCAT('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
--temp table chnages and table assigning ends here

-- Pulling CCP+D Combination for Current projection starts here
          IF Object_id('tempdb..#TEMP_ACCR_PROJ_MASTER') IS NOT NULL
            DROP TABLE #TEMP_ACCR_PROJ_MASTER

          CREATE TABLE #TEMP_ACCR_PROJ_MASTER
            (
               ID                  INT IDENTITY(1, 1),
               CCP_DETAILS_SID     INT,
               RS_MODEL_SID        INT,
               CONTRACT_MASTER_SID INT,
               COMPANY_MASTER_SID  INT,
               ITEM_MASTER_SID     INT,
			   BUSINESS_UNIT INT,
			   COMPANY_ID INT
            )

         SET @SQL=CONCAT(' INSERT INTO #TEMP_ACCR_PROJ_MASTER
                      (CCP_DETAILS_SID,
                       RS_MODEL_SID,
                       CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID,
					   BUSINESS_UNIT
					   ,COMPANY_ID)
          SELECT A.CCP_DETAILS_SID,
                 A.RS_MODEL_SID,
                 B.CONTRACT_MASTER_SID,
                 B.COMPANY_MASTER_SID,
                 B.ITEM_MASTER_SID
				 ,PM.BUSINESS_UNIT
				 ,PM.COMPANY_MASTER_SID AS COMPANY_ID
          FROM   ',@ARP_PROJ_DETAILS,' A
                 JOIN CCP_DETAILS B
                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
				 INNER JOIN PROJECTION_MASTER PM
				 ON A.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID
				 INNER JOIN ITEM_MASTER IM
				 ON IM.ORGANIZATION_KEY=PM.BUSINESS_UNIT
         ')

	EXEC SP_EXECUTESQL @SQL

-- Pulling CCP+D Combination for Current projection Ends here

--assigning Business Unit and GlComp company starts here(GAL-808)
		  SELECT @BUSINESS_UNIT=BUSINESS_UNIT,
		  @COMPANY_ID=COMPANY_ID FROM
		  #TEMP_ACCR_PROJ_MASTER
--assigning Business Unit and GlComp company ends here

--taking latest file for Businees Unit and Gl Comp company Starts here
		        DECLARE @FILE_VER AS TABLE
            (
               FILE_TYPE     VARCHAR(100),
               FORECAST_NAME VARCHAR(100),
               VERSION       VARCHAR(15)
            )

          INSERT INTO @FILE_VER
                      (FILE_TYPE,
                       FORECAST_NAME,
                       VERSION)
          SELECT FILE_TYPE,
                 FORECAST_NAME,
                 VERSION
          FROM   (SELECT FT.FORECAST_NAME,
                         FT.[VERSION],
                         FILE_MANAGEMENT_SID,
                         HT.[DESCRIPTION]                       AS FILE_TYPE,
                         ROW_NUMBER()
                           OVER(
                             PARTITION BY FILE_TYPE
                             ORDER BY FILE_MANAGEMENT_SID DESC) AS RN
                  FROM   FILE_MANAGEMENT FT
                         INNER JOIN HELPER_TABLE HT
                                 ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
                  WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                           AND FT.FROM_PERIOD IS NOT NULL )
                         AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                                OR FT.TO_PERIOD IS NULL )
                         AND HT.LIST_NAME = 'FILE_TYPE'
                         AND HT.[DESCRIPTION] IN (  'Customer Sales')
						 AND FT.BUSINESS_UNIT=@BUSINESS_UNIT-----------------GAL-808
						 AND FT.COMPANY=@COMPANY_ID-----------------GAL-808
						 )A
          WHERE  RN = 1

          SELECT @FORECAST_NAME_GTS_CUST_PROD = FORECAST_NAME,
                 @FORECAST_VERSION_GTS_CUST_PROD = [VERSION]
          FROM   @FILE_VER
          WHERE  FILE_TYPE = 'CUSTOMER SALES'
--taking latest file for Businees Unit and Gl Comp company Ends here

--assigning temp tables to varaiable Starts here
	DECLARE 
        @ARP_SALES_ACTUAL_TABLE     VARCHAR(200) = CONCAT('ST_ACCRUAL_SALES_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
        @ARP_SALES_PROJECTION_TABLE VARCHAR(200) = CONCAT('ST_ACCRUAL_SALES_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		@ARP_EXCLUSION_DETAILS VARCHAR(200) = CONCAT('ST_EXCLUSION_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
--assigning temp tables to varaiable Ends here
/*Creating tables for Actuals values(taking actuals periods upto Current month-1) and Projection values
(taking projections upto Current+36) Starts here */
          IF Object_id('tempdb..#TEMP_ACCR_PROJ_ITEM') IS NOT NULL
            DROP TABLE #TEMP_ACCR_PROJ_ITEM

          CREATE TABLE #TEMP_ACCR_PROJ_ITEM
            (
               ID               INT IDENTITY(1, 1),
               ITEM_MASTER_SID  INT,
               PERIOD_SID       INT,
               TOTAL_UNITS      NUMERIC(22, 6),
               EXCLUDED_UNITS   NUMERIC(22, 6),
               EXCLUDED_DOLLARS NUMERIC(22, 6),
               NET_UNITS        NUMERIC(22, 6),
               SALES            NUMERIC(22, 6)
            )
		
          IF Object_id('tempdb..#TEMP_ACCR_ACTU_ITEM') IS NOT NULL
            DROP TABLE #TEMP_ACCR_ACTU_ITEM

          CREATE TABLE #TEMP_ACCR_ACTU_ITEM
            (
               ID               INT IDENTITY(1, 1),
               ITEM_MASTER_SID  INT,
               PERIOD_SID       INT,
               TOTAL_UNITS      NUMERIC(22, 6),
               EXCLUDED_UNITS   NUMERIC(22, 6),
               EXCLUDED_DOLLARS NUMERIC(22, 6),
               NET_UNITS        NUMERIC(22, 6),
               SALES            NUMERIC(22, 6)
            )

          ------ FOR Actuals Insertion
          INSERT INTO #TEMP_ACCR_ACTU_ITEM
                      (ITEM_MASTER_SID,
                       PERIOD_SID)
          SELECT DISTINCT ITEM_MASTER_SID,
                          PERIOD_SID
          FROM   #TEMP_ACCR_PROJ_MASTER A
                 CROSS JOIN (SELECT PERIOD_SID
                             FROM   PERIOD
                             WHERE  PERIOD_DATE BETWEEN @FROM AND @CURRENT_MONTH - 1) B

          ------ FOR Projection Insertion
          INSERT INTO #TEMP_ACCR_PROJ_ITEM
                      (ITEM_MASTER_SID,
                       PERIOD_SID)
          SELECT DISTINCT ITEM_MASTER_SID,
                          PERIOD_SID
          FROM   #TEMP_ACCR_PROJ_MASTER A
                 CROSS JOIN (SELECT PERIOD_SID
                             FROM   PERIOD
                             WHERE  PERIOD_DATE BETWEEN @CURRENT_MONTH AND @TO) B

 /*Creating tables for Actuals values(taking actuals periods upto Current month-1) and Projection values
(taking projections upto Current+36) Ends here */    

--taking file values based on file selected in data selection and selected item for that projection Starts here

          SELECT @START_PERIOD = Min(PERIOD_SID)
          FROM   #TEMP_ACCR_ACTU_ITEM

          SELECT @END_PERIOD = Max(PERIOD_SID)
          FROM   #TEMP_ACCR_PROJ_ITEM

          IF Object_id('tempdb..#TEMP_GTS_PROD') IS NOT NULL
            DROP TABLE #TEMP_GTS_PROD

          CREATE TABLE #TEMP_GTS_PROD
            (
               ID                 INT IDENTITY(1, 1),
               ITEM_MASTER_SID    INT,
               PERIOD_SID         INT,
               QUARTER            INT,
               YEAR               INT,
               ACTUAL_GTS_SALES   NUMERIC(22, 6),
               ACTUAL_GTS_UNITS   NUMERIC(22, 6),
               FORECAST_GTS_SALES NUMERIC(22, 6),
               FORECAST_GTS_UNITS NUMERIC(22, 6),
            )

         SET @SQL=concat(' INSERT INTO #TEMP_GTS_PROD
                      (ITEM_MASTER_SID,
                       PERIOD_SID,
                       QUARTER,
                       YEAR,
                       ACTUAL_GTS_SALES,
                       ACTUAL_GTS_UNITS,
                       FORECAST_GTS_SALES,
                       FORECAST_GTS_UNITS)
          SELECT ITEM_MASTER_SID,
                 A.PERIOD_SID,
                 P.QUARTER,
                 P.YEAR,
                 Sum(EXFACTORY_CUST_ACTUAL_SALES)  AS ACTUAL_GTS_SALES,
                 Sum(EXFACTORY_CUST_ACTUAL_UNITS)  AS ACTUAL_GTS_UNITS,
                 Sum(EXFACTORY_CUST_FORECAST_SALES) AS FORECAST_GTS_SALES,
                 Sum(EXFACTORY_CUST_FORECAST_UNITS) AS FORECAST_GTS_UNITS
          FROM   ',@PRODUCT_FILE,' A
                 JOIN PERIOD P
                   ON P.PERIOD_SID = A.PERIOD_SID
          GROUP  BY A.PERIOD_SID,
                    P.QUARTER,
                    P.YEAR,
                    ITEM_MASTER_SID')
		EXEC SP_EXECUTESQL @SQL

--taking file values based on file selected in data selection and selected item for that projection Ends here	
--Pulling actuals and forecast values for each period Starts here

          IF Object_id('tempdb..#TEMP_GTS_PROD_EQWAC') IS NOT NULL
            DROP TABLE #TEMP_GTS_PROD_EQWAC

          CREATE TABLE #TEMP_GTS_PROD_EQWAC
            (
               ID                 INT IDENTITY(1, 1),
               ITEM_MASTER_SID    INT,
               PERIOD_SID         INT,
               QUARTER            INT,
               YEAR               INT,
               ACTUAL_GTS_SALES   NUMERIC(22, 6),
               ACTUAL_GTS_UNITS   NUMERIC(22, 6),
               FORECAST_GTS_SALES NUMERIC(22, 6),
               FORECAST_GTS_UNITS NUMERIC(22, 6),
               RN                 INT
            )

          INSERT INTO #TEMP_GTS_PROD_EQWAC
                      (A.ITEM_MASTER_SID,
                       A.PERIOD_SID,
                       A.QUARTER,
                       A.YEAR,
                       A.ACTUAL_GTS_SALES,
                       A.ACTUAL_GTS_UNITS,
                       FORECAST_GTS_SALES,
                       FORECAST_GTS_UNITS,
                       RN)
          SELECT A.ITEM_MASTER_SID,
                 A.PERIOD_SID,
                 A.QUARTER,
                 A.YEAR,
                 A.ACTUAL_GTS_SALES,
                 A.ACTUAL_GTS_UNITS,
                 A.FORECAST_GTS_SALES,
                 A.FORECAST_GTS_UNITS,
                 ROW_NUMBER()
                   OVER(
                     PARTITION BY A.ITEM_MASTER_SID, A.YEAR, A.QUARTER
                     ORDER BY A.YEAR, A.QUARTER, A.PERIOD_SID DESC) AS RN
          FROM   #TEMP_GTS_PROD A
                 JOIN (SELECT ITEM_MASTER_SID,
                              PERIOD_SID,
                              QUARTER,
                              YEAR,
                              ACTUAL_GTS_SALES,
                              ACTUAL_GTS_UNITS,
                              FORECAST_GTS_SALES,
                              FORECAST_GTS_UNITS
                       FROM   (SELECT TP.ITEM_MASTER_SID,
                                      TP.PERIOD_SID,
                                      TP.ACTUAL_GTS_SALES,
                                      TP.ACTUAL_GTS_UNITS,
                                      TP.FORECAST_GTS_SALES,
                                      TP.FORECAST_GTS_UNITS,
                                      TP.QUARTER,
                                      YEAR,
                                      ROW_NUMBER()
                                        OVER(
                                          PARTITION BY ITEM_MASTER_SID, YEAR, QUARTER
                                          ORDER BY YEAR, QUARTER, PERIOD_SID DESC) AS RN
                               FROM   #TEMP_GTS_PROD TP)A
                       WHERE  RN = 1)B
                   ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                      AND A.QUARTER = B.QUARTER
                      AND A.YEAR = B.YEAR
--Pulling actuals and forecast values for each period Ends here
--taking  Exclusion customers which were Excluded in sales tab(by Excusion look up) Starts here
					    declare @sql1 nvarchar(max)=''
          IF Object_id('tempdb..#TEMP_GTS_CUS_PROD') IS NOT NULL
            DROP TABLE #TEMP_GTS_CUS_PROD

          CREATE TABLE #TEMP_GTS_CUS_PROD
            (
               ID                 INT IDENTITY(1, 1),
               ITEM_MASTER_SID    INT,
               PERIOD_SID         INT,
               ACTUAL_GTS_SALES   NUMERIC(22, 6),
               ACTUAL_GTS_UNITS   NUMERIC(22, 6),
               FORECAST_GTS_SALES NUMERIC(22, 6),
               FORECAST_GTS_UNITS NUMERIC(22, 6)
            )

			     IF Object_id('tempdb..#cust_item') IS NOT NULL
            DROP TABLE #cust_item
			create table #cust_item
			(
			ITEM_MASTER_SID int,
			COMPANY_MASTER_SID int
			)
			


          SET @SQL='IF EXISTS(SELECT 1 FROM '+@ARP_EXCLUSION_DETAILS+')
            BEGIN
                -------------------- EXCLUSION
               INSERT INTO #CUST_ITEM
					(ITEM_MASTER_SID,
                             COMPANY_MASTER_SID)
                SELECT DISTINCT A.ITEM_MASTER_SID,
                                B.COMPANY_MASTER_SID
                FROM   #TEMP_ACCR_PROJ_MASTER A
                       CROSS JOIN (SELECT COMPANY_MASTER_SID
                                   FROM '+@ARP_EXCLUSION_DETAILS+'
                                   ) B 
			  END'


EXEC SP_EXECUTESQL @SQL

--taking Exclusion customers which were Excluded in sales tab(by Excusion look up) Ends here

INSERT INTO @CUST_ITEM(COMPANY_MASTER_SID,
                       ITEM_MASTER_SID)
					  SELECT COMPANY_MASTER_SID,
                      ITEM_MASTER_SID FROM #CUST_ITEM

--exec sp_executesql 
--    N'select  * from @CUST_ITEM',
--    N'@CUST_ITEM UDT_CUST_ITEM readonly', @CUST_ITEM  


--Pulling Customer Gts values both actuals and Forecast Starts here
	set @sql1=' 
                INSERT INTO #TEMP_GTS_CUS_PROD
                            (ITEM_MASTER_SID,
                             PERIOD_SID,
                             ACTUAL_GTS_SALES,
                             ACTUAL_GTS_UNITS,
                             FORECAST_GTS_SALES,
                             FORECAST_GTS_UNITS)
							 
							 
							 SELECT ITEM_MASTER_SID,
                       PERIOD_SID,
                       Sum(CUST_ACT_GTS_SALES)  AS ACTUAL_GTS_SALES,
                       Sum(CUST_ACT_GTS_UNITS)  AS ACTUAL_GTS_UNITS,
                       Sum(CUST_FORE_GTS_SALES) AS FORECAST_GTS_SALES,
                       Sum(CUST_FORE_GTS_UNITS) AS FORECAST_GTS_UNITS
                FROM   [UDF_CUST_GTS_WAC](@CUST_ITEM, @START_PERIOD, @END_PERIOD, @FORECAST_NAME_GTS_CUST_PROD, @FORECAST_VERSION_GTS_CUST_PROD)
                GROUP  BY PERIOD_SID,
                          ITEM_MASTER_SID'
						
          
			
		  	
         EXEC SP_EXECUTESQL @SQL1,N'@CUST_ITEM UDT_CUST_ITEM READONLY,@START_PERIOD INT,@END_PERIOD INT,@FORECAST_NAME_GTS_CUST_PROD VARCHAR(50),@FORECAST_VERSION_GTS_CUST_PROD VARCHAR(50)',
          @CUST_ITEM=@CUST_ITEM,
		  @START_PERIOD=@START_PERIOD,
		  @END_PERIOD=@END_PERIOD,
		  @FORECAST_NAME_GTS_CUST_PROD=@FORECAST_NAME_GTS_CUST_PROD,
		  @FORECAST_VERSION_GTS_CUST_PROD=@FORECAST_VERSION_GTS_CUST_PROD

--Pulling Customer Gts values both actuals and Forecast Ends here

		  --INSERT INTO @CUST_ITEM
    --                        (ITEM_MASTER_SID,
    --                         COMPANY_MASTER_SID)
				--			 select  ITEM_MASTER_SID,
    --        COMPANY_MASTER_SID from #CUST_ITEM
		  --SELECT ITEM_MASTER_SID,
    --                   PERIOD_SID,
    --                   Sum(CUST_ACT_GTS_SALES)  AS ACTUAL_GTS_SALES,
    --                   Sum(CUST_ACT_GTS_UNITS)  AS ACTUAL_GTS_UNITS,
    --                   Sum(CUST_FORE_GTS_SALES) AS FORECAST_GTS_SALES,
    --                   Sum(CUST_FORE_GTS_UNITS) AS FORECAST_GTS_UNITS
    --            FROM   [UDF_CUST_GTS_WAC](@CUST_ITEM, 616, 657, 'RFTEST3 2016', '7')
    --            GROUP  BY PERIOD_SID,
    --                      ITEM_MASTER_SID
			
--Updation of Actuals Calculated fields starts here
          UPDATE T
          SET    T.TOTAL_UNITS = ISNULL(S.ACTUAL_GTS_UNITS, 0),
                 T.EXCLUDED_UNITS = ISNULL(U.ACTUAL_GTS_UNITS, 0),
                 T.EXCLUDED_DOLLARS = ISNULL(U.ACTUAL_GTS_SALES, 0),
                 T.NET_UNITS = ISNULL(S.ACTUAL_GTS_UNITS, 0) - ISNULL(U.ACTUAL_GTS_UNITS, 0)
          FROM   #TEMP_ACCR_ACTU_ITEM T
                 JOIN #TEMP_GTS_PROD_EQWAC S
                   ON S.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                      AND S.PERIOD_SID = T.PERIOD_SID
                 LEFT JOIN #TEMP_GTS_CUS_PROD U
                        ON U.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                           AND U.PERIOD_SID = T.PERIOD_SID
--Updation of Actuals Calculated fields Ends here

--Updation of Projection Calculated fields Starts here

          UPDATE T
          SET    T.TOTAL_UNITS = ISNULL(S.FORECAST_GTS_UNITS, 0),
                 T.EXCLUDED_UNITS = ISNULL(U.FORECAST_GTS_UNITS, 0),
                 T.EXCLUDED_DOLLARS = ISNULL(U.FORECAST_GTS_SALES, 0),
                 T.NET_UNITS = ISNULL(S.FORECAST_GTS_UNITS, 0) - ISNULL(U.FORECAST_GTS_UNITS, 0)
          FROM   #TEMP_ACCR_PROJ_ITEM T
                 JOIN #TEMP_GTS_PROD_EQWAC S
                   ON S.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                      AND S.PERIOD_SID = T.PERIOD_SID
                 LEFT JOIN #TEMP_GTS_CUS_PROD U
                        ON U.ITEM_MASTER_SID = T.ITEM_MASTER_SID
                           AND U.PERIOD_SID = T.PERIOD_SID
--Updation of Projection Calculated fields Ends here
--Pulling and calculated the demand and adjusted demand prices based on Peirod basis selected on data selection Starts here
          SELECT @START_PERIOD = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @FROM), 0)

          SELECT @END_PERIOD = PERIOD_SID - 1
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @TO) + 2, 0)

          IF Object_id('TEMPDB..#TEMP_RATES') IS NOT NULL
            DROP TABLE #TEMP_RATES

          CREATE TABLE #TEMP_RATES
            (
               ITEM_MASTER_SID        INT,
               PERIOD_SID             INT,
               PRESENT_ACT_DEM        NUMERIC(22, 6),
               NEXT_QUARTER_ACT_DEM   NUMERIC(22, 6),
               PRESENT_FORE_DEM       NUMERIC(22, 6),
               NEXT_QUARTER_FORE_DEM  NUMERIC(22, 6),
               PRESENT_ACT_ADEM       NUMERIC(22, 6),
               NEXT_QUARTER_ACT_ADEM  NUMERIC(22, 6),
               PRESENT_FORE_ADEM      NUMERIC(22, 6),
               NEXT_QUARTER_FORE_ADEM NUMERIC(22, 6),
               PRS_QTR_ACT_PRICE      NUMERIC(22, 6),
               PRS_QTR_FOR_PRICE      NUMERIC(22, 6),
               NEXT_QTR_ACT_PRICE     NUMERIC(22, 6),
               NEXT_QTR_FOR_PRICE     NUMERIC(22, 6)
            )

         SET @SQL=CONCAT( 'INSERT INTO #TEMP_RATES
                      (ITEM_MASTER_SID,
                       PERIOD_SID,
                       PRESENT_ACT_DEM,
                       NEXT_QUARTER_ACT_DEM,
                       PRESENT_FORE_DEM,
                       NEXT_QUARTER_FORE_DEM,
                       PRESENT_ACT_ADEM,
                       NEXT_QUARTER_ACT_ADEM,
                       PRESENT_FORE_ADEM,
                       NEXT_QUARTER_FORE_ADEM,
                       PRS_QTR_ACT_PRICE,
                       PRS_QTR_FOR_PRICE,
                       NEXT_QTR_ACT_PRICE,
                       NEXT_QTR_FOR_PRICE)
          SELECT D.ITEM_MASTER_SID,
                 D.PERIOD_SID,
                 D.PRESENT_ACT_DEM,
                 D.NEXT_QUARTER_ACT_DEM,
                 D.PRESENT_FORE_DEM,
                 D.NEXT_QUARTER_FORE_DEM,
                 D.PRESENT_ACT_ADEM,
                 D.NEXT_QUARTER_ACT_ADEM,
                 D.PRESENT_FORE_ADEM,
                 D.NEXT_QUARTER_FORE_ADEM,
                 D. PRS_QTR_ACT_PRICE,
                 D. PRS_QTR_FOR_PRICE,
                 D. NEXT_QTR_ACT_PRICE,
                 D. NEXT_QTR_FOR_PRICE
          FROM   (SELECT B.ITEM_MASTER_SID,
                         B.PERIOD_SID,
                         B.PRESENT_ACT_DEM,
                         Sum(B.MONTH_ACT_DEM)
                           OVER(
                             PARTITION BY B.ITEM_MASTER_SID, B.QUARTER, B.YEAR) AS NEXT_QUARTER_ACT_DEM,
                         B.PRESENT_FORE_DEM,
                         Sum(B.MONTH_FORE_DEM)
                           OVER(
                             PARTITION BY B.ITEM_MASTER_SID, B.QUARTER, B.YEAR) AS NEXT_QUARTER_FORE_DEM,
							 B.PRESENT_ACT_ADEM,
						Sum(B.MONTH_ACT_ADEM)
                                     OVER(
                                       PARTITION BY B.ITEM_MASTER_SID, B.QUARTER, B.YEAR) AS NEXT_QUARTER_ACT_ADEM,
                                   B.PRESENT_FORE_ADEM,
                                   Sum(B.MONTH_FORE_ADEM)
                                     OVER(
                                       PARTITION BY B.ITEM_MASTER_SID, B.QUARTER, B.YEAR) AS NEXT_QUARTER_FORE_ADEM,
					 Max(PRS_QTR_ACT_PRICE)
                                     OVER(
                                       PARTITION BY ITEM_MASTER_SID, QUARTER, YEAR) AS PRS_QTR_ACT_PRICE,
                                   Max(PRS_QTR_FOR_PRICE)
                                     OVER(
                                       PARTITION BY ITEM_MASTER_SID, QUARTER, YEAR) AS PRS_QTR_FOR_PRICE,
                                   Max(NEXT_QTR_ACT_PRICE)
                                     OVER(
                                       PARTITION BY ITEM_MASTER_SID, QUARTER, YEAR) AS NEXT_QTR_ACT_PRICE,
                                   Max(NEXT_QTR_FOR_PRICE)
                                     OVER(
                                       PARTITION BY ITEM_MASTER_SID, QUARTER, YEAR) AS NEXT_QTR_FOR_PRICE
						 
                  FROM   (SELECT A.ITEM_MASTER_SID,
                                 A.PERIOD_SID,
                                 Sum(A.DEMAND_ACTUAL_SALES)
                                   OVER(
                                     PARTITION BY A.ITEM_MASTER_SID, P.QUARTER, P.YEAR) AS PRESENT_ACT_DEM,
                                 ISNULL(LEAD(A.DEMAND_ACTUAL_SALES)
                                          OVER(
                                            PARTITION BY A.ITEM_MASTER_SID, A.PERIOD_SID%3
                                            ORDER BY A.PERIOD_SID), 0)                  AS MONTH_ACT_DEM,
                                 Sum(A.DEMAND_FORECAST_SALES)
                                   OVER(
                                     PARTITION BY A.ITEM_MASTER_SID, P.QUARTER, P.YEAR) AS PRESENT_FORE_DEM,
                                 ISNULL(LEAD(A.DEMAND_FORECAST_SALES)
                                          OVER(
                                            PARTITION BY A.ITEM_MASTER_SID, A.PERIOD_SID%3
                                            ORDER BY A.PERIOD_SID), 0)                  AS MONTH_FORE_DEM,
								 Sum(A.ADJUSTED_DEMAND_ACTUAL_SALES)
                                             OVER(
                                               PARTITION BY A.ITEM_MASTER_SID, P.QUARTER, P.YEAR) AS PRESENT_ACT_ADEM,
                                           LEAD(A.ADJUSTED_DEMAND_ACTUAL_SALES)
                                             OVER(
                                               PARTITION BY A.ITEM_MASTER_SID, A.PERIOD_SID%3
                                               ORDER BY A.PERIOD_SID)                             AS MONTH_ACT_ADEM,
                                           Sum(A.ADJUSTED_DEMAND_FORECAST_SALES)
                                             OVER(
                                               PARTITION BY A.ITEM_MASTER_SID, P.QUARTER, P.YEAR) AS PRESENT_FORE_ADEM,
                                           LEAD(A.ADJUSTED_DEMAND_FORECAST_SALES)
                                             OVER(
                                               PARTITION BY A.ITEM_MASTER_SID, A.PERIOD_SID%3
                                               ORDER BY A.PERIOD_SID)                             AS MONTH_FORE_ADEM,
									CASE
                                             WHEN P.PERIOD_SID%3 = 0 THEN EXFACTORY_ACTUAL_SALES/NULLIF(EXFACTORY_ACTUAL_UNITS,0)
                                           END AS PRS_QTR_ACT_PRICE,
                                           CASE
                                             WHEN P.PERIOD_SID%3 = 0 THEN EXFACTORY_FORECAST_SALES/NULLIF(EXFACTORY_FORECAST_UNITS,0)
                                           END AS PRS_QTR_FOR_PRICE,
                                           CASE
                                             WHEN P.PERIOD_SID%3 = 0 THEN LEAD(EXFACTORY_ACTUAL_SALES)
                                                                            OVER(
                                                                              PARTITION BY ITEM_MASTER_SID, P.PERIOD_SID%3
                                                                              ORDER BY ITEM_MASTER_SID, P.PERIOD_SID)/
																			  NULLIF(LEAD(EXFACTORY_ACTUAL_UNITS)
                                                                            OVER(
                                                                              PARTITION BY ITEM_MASTER_SID, P.PERIOD_SID%3
                                                                              ORDER BY ITEM_MASTER_SID, P.PERIOD_SID),0)
                                           END AS NEXT_QTR_ACT_PRICE,
                                           CASE
                                             WHEN P.PERIOD_SID%3 = 0 THEN LEAD(EXFACTORY_FORECAST_SALES)
                                                                            OVER(
                                                                              PARTITION BY ITEM_MASTER_SID, P.PERIOD_SID%3
                                                                              ORDER BY ITEM_MASTER_SID, P.PERIOD_SID)/
																			  NULLIF(LEAD(EXFACTORY_FORECAST_UNITS)
                                                                            OVER(
                                                                              PARTITION BY ITEM_MASTER_SID, P.PERIOD_SID%3
                                                                              ORDER BY ITEM_MASTER_SID, P.PERIOD_SID),0)
                                           END AS NEXT_QTR_FOR_PRICE, P.QUARTER,
                                 P.YEAR
                          FROM  ',@PRODUCT_FILE,' A
                                 JOIN PERIOD P
                                   ON P.PERIOD_SID = A.PERIOD_SID) B)D
              ')


		
			  EXEC SP_EXECUTESQL @SQL
		
--Pulling and calculated the demand and adjusted demand prices based on Peirod basis selected on data selection Ends here

/*Deleting and Reinsertion for Sales actuals and Projections for Particular Projection based on Peiord basis Price change
 i.e 1-3 use forward quarter : we need to pull last quarter value for Current Quarter all Months
       2-3 use forward quarter : we need to pull last quarter value for current quarter 2nd and 3rd month
	   3   use forward quarter : we need to pull last quarter value for current quarter 3rd month Logic Starts here*/

	
          DECLARE @SQL2 NVARCHAR(MAX) = ''
	      SET @SQL2=CONCAT('IF EXISTS(SELECT 1
                    FROM   ',@ARP_SALES_ACTUAL_TABLE,' A
                    WHERE  A.PROJECTION_MASTER_SID = ',@PROJECTION,')
           BEGIN
                TRUNCATE TABLE ',@ARP_SALES_ACTUAL_TABLE,'
            END

          IF EXISTS(SELECT 1
                    FROM   ',@ARP_SALES_PROJECTION_TABLE,' P
                    WHERE P.PROJECTION_MASTER_SID = ',@PROJECTION,')
            BEGIN
                TRUNCATE TABLE ',@ARP_SALES_PROJECTION_TABLE,'
            END')

			EXEC SP_EXECUTESQL @SQL2

			
          DECLARE @SQL3 nVARCHAR(MAX)= ''
     SET @SQL3 =  CONCAT('INSERT INTO ',@ARP_SALES_ACTUAL_TABLE,'
                      (PROJECTION_MASTER_SID,
                       ITEM_MASTER_SID,
                       PERIOD_SID,
                       TOTAL_UNITS,
                       EXCLUDED_UNITS,
                       NET_UNITS,
                       PRICE,
                       SALES,
                       EXCLUDED_DOLLARS,
                       DEMAND,
                       ADJUSTED_DEMAND)

					
       
          SELECT ',@PROJECTION,' AS PROJECTION_MASTER_SID,
                 TA.ITEM_MASTER_SID,
                 TA.PERIOD_SID,
                 ISNULL(TOTAL_UNITS, 0),
                 ISNULL(EXCLUDED_UNITS, 0),
                 ISNULL(NET_UNITS, 0),
                 CASE
                   WHEN @PERIOD_BASIS = ''Month 2-3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 IN( 0, 2 ) THEN ISNULL(NEXT_QTR_ACT_PRICE, 0)
                   WHEN @PERIOD_BASIS = ''Month 1-3 use forward quarter rate'' THEN ISNULL(NEXT_QTR_ACT_PRICE, 0)
                   WHEN @PERIOD_BASIS = ''Month 3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 = 0 THEN ISNULL(NEXT_QTR_ACT_PRICE, 0)
                   ELSE ISNULL(PRS_QTR_ACT_PRICE, 0)
                 END PRICE,
                 ( CASE
                     WHEN @PERIOD_BASIS = ''Month 2-3 use forward quarter rate''
                          AND TR.PERIOD_SID%3 IN( 0, 2 ) THEN ISNULL(NEXT_QTR_ACT_PRICE, 0)
                     WHEN @PERIOD_BASIS = ''Month 1-3 use forward quarter rate'' THEN ISNULL(NEXT_QTR_ACT_PRICE, 0)
                     WHEN @PERIOD_BASIS = ''Month 3 use forward quarter rate''
                          AND TR.PERIOD_SID%3 = 0 THEN ISNULL(NEXT_QTR_ACT_PRICE, 0)
                     ELSE ISNULL(PRS_QTR_ACT_PRICE, 0)
                   END ) * ISNULL(NET_UNITS, 0),
                 ( EXCLUDED_DOLLARS * -1 ),
                 CASE
                   WHEN @PERIOD_BASIS = ''Month 2-3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 IN( 0, 2 ) THEN NEXT_QUARTER_ACT_DEM
                   WHEN @PERIOD_BASIS = ''Month 1-3 use forward quarter rate'' THEN NEXT_QUARTER_ACT_DEM
                   WHEN @PERIOD_BASIS = ''Month 3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 = 0 THEN NEXT_QUARTER_ACT_DEM
                   ELSE PRESENT_ACT_DEM
                 END DEMAND,
                 CASE
                   WHEN @PERIOD_BASIS = ''Month 2-3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 IN( 0, 2 ) THEN NEXT_QUARTER_ACT_ADEM
                   WHEN @PERIOD_BASIS = ''Month 1-3 use forward quarter rate'' THEN NEXT_QUARTER_ACT_ADEM
                   WHEN @PERIOD_BASIS = ''Month 3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 = 0 THEN NEXT_QUARTER_ACT_ADEM
                   ELSE PRESENT_ACT_ADEM
                 END ADJUSTED_DEMAND
          FROM   #TEMP_ACCR_ACTU_ITEM TA
                 LEFT JOIN #TEMP_RATES TR
                        ON TA.ITEM_MASTER_SID = TR.ITEM_MASTER_SID
                           AND TA.PERIOD_SID = TR.PERIOD_SID

          INSERT INTO ',@ARP_SALES_PROJECTION_TABLE,'
                      (PROJECTION_MASTER_SID,
                       ITEM_MASTER_SID,
                       PERIOD_SID,
                       TOTAL_UNITS,
                       EXCLUDED_UNITS,
                       NET_UNITS,
                       PRICE,
                       SALES,
                       EXCLUDED_DOLLARS,
                       DEMAND,
                       ADJUSTED_DEMAND)
          SELECT ',@PROJECTION,' AS PROJECTION_MASTER_SID ,
                 TA.ITEM_MASTER_SID,
                 TA.PERIOD_SID,
                 ISNULL(TOTAL_UNITS, 0),
                 ISNULL(EXCLUDED_UNITS, 0),
                 ISNULL(NET_UNITS, 0),
                 CASE
                   WHEN @PERIOD_BASIS = ''Month 2-3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 IN( 0, 2 ) THEN ISNULL(NEXT_QTR_FOR_PRICE, 0)
                   WHEN @PERIOD_BASIS = ''Month 1-3 use forward quarter rate'' THEN ISNULL(NEXT_QTR_FOR_PRICE, 0)
                   WHEN @PERIOD_BASIS = ''Month 3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 = 0 THEN ISNULL(NEXT_QTR_FOR_PRICE, 0)
                   ELSE ISNULL(PRS_QTR_FOR_PRICE, 0)
                 END PRICE,
                 ( CASE
                     WHEN @PERIOD_BASIS = ''Month 2-3 use forward quarter rate''
                          AND TR.PERIOD_SID%3 IN( 0, 2 ) THEN ISNULL(NEXT_QTR_FOR_PRICE, 0)
                     WHEN @PERIOD_BASIS = ''Month 1-3 use forward quarter rate'' THEN ISNULL(NEXT_QTR_FOR_PRICE, 0)
                     WHEN @PERIOD_BASIS = ''Month 3 use forward quarter rate''
                          AND TR.PERIOD_SID%3 = 0 THEN ISNULL(NEXT_QTR_FOR_PRICE, 0)
                     ELSE ISNULL(PRS_QTR_FOR_PRICE, 0)
                   END ) * ISNULL(NET_UNITS, 0),
                 ( EXCLUDED_DOLLARS * -1 ),
                 CASE
                   WHEN @PERIOD_BASIS = ''Month 2-3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 IN( 0, 2 ) THEN NEXT_QUARTER_FORE_DEM
                   WHEN @PERIOD_BASIS = ''Month 1-3 use forward quarter rate'' THEN NEXT_QUARTER_FORE_DEM
                   WHEN @PERIOD_BASIS = ''Month 3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 = 0 THEN NEXT_QUARTER_FORE_DEM
                   ELSE PRESENT_FORE_DEM
                 END DEMAND,
                 CASE
                   WHEN @PERIOD_BASIS = ''Month 2-3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 IN( 0, 2 ) THEN NEXT_QUARTER_FORE_ADEM
                   WHEN @PERIOD_BASIS = ''Month 1-3 use forward quarter rate'' THEN NEXT_QUARTER_FORE_ADEM
                   WHEN @PERIOD_BASIS = ''Month 3 use forward quarter rate''
                        AND TR.PERIOD_SID%3 = 0 THEN NEXT_QUARTER_FORE_ADEM
                   ELSE PRESENT_FORE_ADEM
                 END ADJUSTED_DEMAND
          FROM   #TEMP_ACCR_PROJ_ITEM TA
                 LEFT JOIN #TEMP_RATES TR
                        ON TA.ITEM_MASTER_SID = TR.ITEM_MASTER_SID
                           AND TA.PERIOD_SID = TR.PERIOD_SID')
			

	
EXEC SP_EXECUTESQL @SQL3,N'@PERIOD_BASIS VARCHAR(100)',@PERIOD_BASIS  = @PERIOD_BASIS 

/*Deleting and Reinsertion for Sales actuals and Projections for Particular Projection based on Peiord basis Price change
 i.e 1-3 use forward quarter : we need to pull last quarter value for Current Quarter all Months
       2-3 use forward quarter : we need to pull last quarter value for current quarter 2nd and 3rd month
	   3   use forward quarter : we need to pull last quarter value for current quarter 3rd month Logic Starts here*/

      END TRY

      BEGIN CATCH
          DECLARE @ErrorMessage NVARCHAR(4000);
          DECLARE @ErrorSeverity INT;
          DECLARE @ErrorState INT;
          DECLARE @ErrorNumber INT;
          DECLARE @ErrorProcedure VARCHAR(200);
          DECLARE @Errorline INT;

          EXEC USPERRORCOLLECTOR

          SELECT @ErrorMessage = ERROR_MESSAGE(),
                 @ErrorSeverity = ERROR_SEVERITY(),
                 @ErrorState = ERROR_STATE(),
                 @ErrorProcedure = ERROR_PROCEDURE(),
                 @Errorline = ERROR_LINE(),
                 @ErrorNumber = ERROR_NUMBER()

          RAISERROR (@ErrorMessage,-- Message text.
                     @ErrorSeverity,-- Severity.
                     @ErrorState,-- State.
                     @ErrorProcedure,-- Procedure_Name.
                     @ErrorNumber,-- ErrorNumber
                     @Errorline -- ErrorLine
          );
      END CATCH
  END


GO