IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_ACCRUAL_DETAILS'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_ACCRUAL_DETAILS]
  END

GO

CREATE PROCEDURE [dbo].[PRC_ACCRUAL_DETAILS]    (@PROJECTION_MASTER_SID INT,
                                                @PERIOD_BASIS          VARCHAR(100),
                                                @USER_ID               INT,
                                                @SESSION_ID            VARCHAR(100))
AS

/**********************************************************************************************************
** File Name		:	PRC_ACCRUAL_DETAILS.sql
** Procedure Name	:	PRC_ACCRUAL_DETAILS
** Description		:	To generate ARP(Accrual Rate Projection) Details tab
** Input Parameters	:	@PROJECTION             - Respective Projection ID Creted for Accrual Rate Projection
                        @PERIOD_BASIS           - it is the identification of Period basis selected in data selection.
						                          based on this we will pull values from files and we will perform calculation
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection										
** Output Parameters:	NA
** Author Name		:	@Tharun,@Ajay
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application in Accrual Details tab to Generate ARP Details.
                        
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

          DECLARE @ACTUAL_START_DATE    DATETIME = Dateadd(MM, Datediff(MM, 0, Dateadd(MM, -12, Getdate())), 0),--CURRENT - 6 COMPLETE MONTHS
                  @PROJ_END_DATE        DATETIME = Dateadd(MM, Datediff(MM, 0, Dateadd(MM, 35, Getdate())), 0),--CURRENT + 35=36 COMPLETE MONTHS
                  @START_PERIOD_SID     INT,
                  @END_PERIOD_SID       INT,
                  @START_PERIOD         INT,
                  @END_PERIOD           INT,
                  @CURRENT_PERIOD_SID   INT,
                  @COMPANY_MASTER_SID   INT

-- Variables Initialization and taking from date as 12 months history and 36 months future Ends here
--assigning temp tables to varaiable Starts here
		 DECLARE  @ACTUAL_TABLE     VARCHAR(200) = Concat('ST_ACCRUAL_DETAILS_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
                  @PROJECTION_TABLE VARCHAR(200) = Concat('ST_ACCRUAL_DETAILS_INFO_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				  @PRODUCT_FILE     VARCHAR(200)= Concat('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				  @ARP_PROJ_TABLE   VARCHAR(200)= Concat('ST_ACCRUAL_PROJ_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
--assigning temp tables to varaiable Ends here

--Taking Period Sid's based on Projection Start date and End Date Starts here
		 DECLARE @SQL1 NVARCHAR(MAX)

          SELECT @START_PERIOD_SID = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = @ACTUAL_START_DATE

          SELECT @START_PERIOD = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @ACTUAL_START_DATE), 0)

          SELECT @END_PERIOD_SID = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = @PROJ_END_DATE

          SELECT @END_PERIOD = ( PERIOD_SID ) - 1
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, @PROJ_END_DATE) + 2, 0)

          SELECT @CURRENT_PERIOD_SID = PERIOD_SID
          FROM   PERIOD
          WHERE  PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, Dateadd(MM, 0, Getdate())), 0)

--Taking Period Sid's based on Projection Start date and End Date Ends here
--Pulling CCP+D Combination for Current projection starts here
          IF Object_id('TEMPDB..#TEMP_MASTER') IS NOT NULL
            DROP TABLE #TEMP_MASTER

          CREATE TABLE #TEMP_MASTER
            (
             
               CCP_DETAILS_SID          INT,
               CONTRACT_MASTER_SID      INT,
               COMPANY_MASTER_SID       INT,
               ITEM_MASTER_SID          INT,
               RS_MODEL_SID             INT,
			   RS_CONTRACT_SID          INT,
               RS_ID                    VARCHAR(50)
	
            )



      SET @SQL1=CONCAT('    INSERT INTO #TEMP_MASTER
                      (
                       CCP_DETAILS_SID,
                       CONTRACT_MASTER_SID,
                       COMPANY_MASTER_SID,
                       ITEM_MASTER_SID,
                       RS_MODEL_SID,
					   RS_CONTRACT_SID,
                       RS_ID
					   
					  )
          SELECT 
                 CCP.CCP_DETAILS_SID,
                 CCP.CONTRACT_MASTER_SID,
                 CCP.COMPANY_MASTER_SID,
                 CCP.ITEM_MASTER_SID,
                 AC.RS_MODEL_SID,
				 AC.RS_CONTRACT_SID,
                 RS.RS_ID
				
				 
          FROM  
                  ',@ARP_PROJ_TABLE,' AC
                 
                 JOIN CCP_DETAILS CCP
                   ON CCP.CCP_DETAILS_SID = AC.CCP_DETAILS_SID
                 JOIN COMPANY_MASTER CM
                   ON CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID
                 JOIN ITEM_MASTER IM
                   ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID
                 LEFT JOIN RS_CONTRACT RS
                        ON RS.RS_MODEL_SID = AC.RS_MODEL_SID
						AND RS.CONTRACT_MASTER_SID=CCP.CONTRACT_MASTER_SID
						----------------------------------CHANGED/REMOVED FOR GAL-1163 
						   AND RS.RS_CONTRACT_SID=AC.RS_CONTRACT_SID 
						   --AND RS.INBOUND_STATUS <> ''D''	')
						   --------------------------------------------------------------					
EXEC SP_EXECUTESQL  @SQL1

--Pulling CCP+D Combination for Current projection Ends here


 SET @COMPANY_MASTER_SID=(  SELECT TOP 1 COMPANY_MASTER_SID FROM #TEMP_MASTER ORDER BY COMPANY_MASTER_SID)

 --Applying Period Basis Concept Logic tro take flag based on selected period basis Starts here

          DECLARE @TEMP_PERIOD TABLE
            (
               COMPANY_MASTER_SID INT,
               ITEM_MASTER_SID    INT,
               PERIOD_SID         INT,
               FLAG               BIT,
               PERIOD_MONTH       INT,
               QUARTER            INT,
               PERIOD_YEAR        INT
            )

          INSERT INTO @TEMP_PERIOD
                      (ITEM_MASTER_SID,
                       PERIOD_SID,
                       FLAG,
                       PERIOD_MONTH,
                       QUARTER,
                       PERIOD_YEAR)
          SELECT DISTINCT TM.ITEM_MASTER_SID,
                          P.PERIOD_SID,
                          CASE
                            WHEN @PERIOD_BASIS = 'MONTH 2-3 USE FORWARD QUARTER RATE'
                                 AND P.PERIOD_SID%3 IN ( 0, 2 ) THEN 1
                            WHEN @PERIOD_BASIS = 'MONTH 3 USE FORWARD QUARTER RATE'
                                 AND P.PERIOD_SID%3 = 0 THEN 1
                            WHEN @PERIOD_BASIS = 'MONTH 1-3 USE FORWARD QUARTER RATE' THEN 1
                            ELSE 0
                          END FLAG,
                          MONTH,
                          QUARTER,
                          YEAR
          FROM   #TEMP_MASTER TM
                 CROSS JOIN PERIOD P
          WHERE  PERIOD_SID BETWEEN @START_PERIOD AND @END_PERIOD

 --Applying Period Basis Concept Logic tro take flag based on selected period basis Ends here

       --   DECLARE @FILE_VER AS TABLE
       --     (
       --        FILE_TYPE     VARCHAR(100),
       --        FORECAST_NAME VARCHAR(100),
       --        VERSION       VARCHAR(15)
       --     )

       --   INSERT INTO @FILE_VER
       --               (FILE_TYPE,
       --                FORECAST_NAME,
       --                VERSION)
       --   SELECT FILE_TYPE,
       --          FORECAST_NAME,
       --          VERSION
       --   FROM   (SELECT FT.FORECAST_NAME,
       --                  FT.[VERSION],
       --                  FILE_MANAGEMENT_SID,
       --                  HT.[DESCRIPTION]                       AS FILE_TYPE,
       --                  ROW_NUMBER()
       --                    OVER(
       --                      PARTITION BY FILE_TYPE
       --                      ORDER BY FILE_MANAGEMENT_SID DESC) AS RN
       --           FROM   FILE_MANAGEMENT FT
       --                  INNER JOIN HELPER_TABLE HT
       --                          ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
       --           WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
       --                    AND FT.FROM_PERIOD IS NOT NULL )
       --                  AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
       --                         OR FT.TO_PERIOD IS NULL )
       --                  AND HT.LIST_NAME = 'FILE_TYPE'
       --                  AND HT.[DESCRIPTION] IN ( 'Inventory Withdrawal - Forecast Detail', 'Customer Sales', 'Ex-Factory Sales' )
						 --AND FT.BUSINESS_UNIT=@BUSINESS_UNIT ----gal-808
						 --AND FT.COMPANY=@COMPANY_ID ----gal-808
						 --)A
       --   WHERE  RN = 1

       --   SELECT @FORECAST_NAME = FORECAST_NAME,
       --          @FORECAST_VERSION = [VERSION]
       --   FROM   @FILE_VER
       --   WHERE  FILE_TYPE = 'CUSTOMER SALES'

       --   SELECT @FORECAST_NAME_INV = FORECAST_NAME,
       --          @FORECAST_VERSION_INV = [VERSION]
       --   FROM   @FILE_VER
       --   WHERE  FILE_TYPE = 'Inventory Withdrawal - Forecast Detail'

       --   SELECT @FORECAST_NAME_EX = FORECAST_NAME,
       --          @FORECAST_VERSION_EX = [VERSION]
       --   FROM   @FILE_VER
       --   WHERE  FILE_TYPE = 'Ex-Factory Sales'

       --   INSERT INTO @CUST_ITEM
       --               (COMPANY_MASTER_SID,
       --                ITEM_MASTER_SID)
         
       --   SELECT  DISTINCT NULL,ITEM_MASTER_SID
       --                      FROM   #TEMP_MASTER
       --   INSERT INTO @ITEM_UDT
       --               (ITEM_MASTER_SID)
       --   SELECT DISTINCT ITEM_MASTER_SID
       --   FROM   @CUST_ITEM

--Creating table for storing Forecast and Actual Values based Period basis Concept Starts here	
 
          IF Object_id('TEMPDB..#TEMP_DETAILS') IS NOT NULL
            DROP TABLE #TEMP_DETAILS

          CREATE TABLE #TEMP_DETAILS
            (
               ITEM_MASTER_SID            INT,
               PERIOD_SID                 INT,
               FLAG                       BIT,
               CUST_ACT_GTS_SALES         NUMERIC(22, 6),
               CUST_FORE_GTS_SALES        NUMERIC(22, 6),
               PRESENT_ACT_INV            NUMERIC(22, 6),
               NEXT_QUARTER_ACT_INV       NUMERIC(22, 6),
               PRESENT_FORE_INV           NUMERIC(22, 6),
               NEXT_QUARTER_FORE_INV      NUMERIC(22, 6),
               ACTUAL_PRES_QTR_PRICE_CHNG NUMERIC(22, 6),
               FORE_PRES_QTR_PRICE_CHNG   NUMERIC(22, 6),
               ACTUAL_NEXT_QTR_PRICE_CHNG NUMERIC(22, 6),
               FORE_NEXT_QTR_PRICE_CHNG   NUMERIC(22, 6)
            )

--Creating table for storing Forecast and Actual Values based Period basis Concept Ends here
	
--Pulling Values from Product file Starts here

	IF OBJECT_ID('TEMPDB..#T1') IS NOT NULL
	  DROP TABLE #T1
		CREATE TABLE #T1
		  (
		  ITEM_MASTER_SID      INT,
		  PERIOD_SID           INT,
		  CUST_ACT_GTS_SALES   NUMERIC(22,6),
		  CUST_FORE_GTS_SALES   NUMERIC(22,6)
		  )
		

		SET @SQL1=CONCAT('INSERT INTO #T1
		 (
		  ITEM_MASTER_SID  ,  
		  PERIOD_SID ,        
		  CUST_ACT_GTS_SALES, 
		  CUST_FORE_GTS_SALES 
		 )
          SELECT A.ITEM_MASTER_SID,
                 A.PERIOD_SID,
                 (A.EXFACTORY_ACTUAL_SALES)  AS CUST_ACT_GTS_SALES,
                 (A.EXFACTORY_FORECAST_SALES) AS CUST_FORE_GTS_SALES
    
          FROM  ',@PRODUCT_FILE,' A')
   
   EXEC SP_EXECUTESQL @SQL1

--Pulling Values from Product file Ends here

 /*Pulling Forecast and Actual Values based Period basis Concept 
   i.e 1-3 use forward quarter : we need to pull last quarter value for Current Quarter all Months
       2-3 use forward quarter : we need to pull last quarter value for current quarter 2nd and 3rd month
	   3   use forward quarter : we need to pull last quarter value for current quarter 3rd month only Logic Starts here */

		IF Object_id('TEMPDB..#T2') IS NOT NULL
		  DROP TABLE #T2

		CREATE TABLE #T2
		  (
			 ITEM_MASTER_SID            INT,
			 PERIOD_SID                 INT,
			 ACTUAL_PRES_QTR_PRICE_CHNG NUMERIC(22, 6),
			 FORE_PRES_QTR_PRICE_CHNG   NUMERIC(22, 6),
			 ACTUAL_NEXT_QTR_PRICE_CHNG NUMERIC(22, 6),
			 FORE_NEXT_QTR_PRICE_CHNG   NUMERIC(22, 6)
		  ) 

       SET @SQL1=CONCAT('   INSERT INTO #T2
		  (
		  ITEM_MASTER_SID ,          
		  PERIOD_SID ,               
		  ACTUAL_PRES_QTR_PRICE_CHNG,
		  FORE_PRES_QTR_PRICE_CHNG , 
		  ACTUAL_NEXT_QTR_PRICE_CHNG,
		  FORE_NEXT_QTR_PRICE_CHNG  
		  )
          SELECT ITEM_MASTER_SID,
                 PERIOD_SID,
                 ( ISNULL(ACTUAL_PRICE, 0) - ISNULL(PREV_QTR_ACTUAL_PRICE, 0) ) / NULLIF(PREV_QTR_ACTUAL_PRICE, 0)     AS ACTUAL_PRES_QTR_PRICE_CHNG,
                 ( ISNULL(FORECAST_PRICE, 0) - ISNULL(PREV_QTR_FORE_PRICE, 0) ) / NULLIF(PREV_QTR_FORE_PRICE, 0) AS FORE_PRES_QTR_PRICE_CHNG,
                 (ISNULL(NEXT_QTR_ACT_PRICE, 0) - ISNULL(ACTUAL_PRICE, 0) )/ NULLIF(ACTUAL_PRICE, 0)            AS ACTUAL_NEXT_QTR_PRICE_CHNG,
                 ( ISNULL(NEXT_QTR_FORE_PRICE, 0) - ISNULL(FORECAST_PRICE, 0) ) / NULLIF(FORECAST_PRICE, 0)   AS   FORE_NEXT_QTR_PRICE_CHNG
  
          FROM   (SELECT ITEM_MASTER_SID,
                         PERIOD_SID,
                         LAG(EXFACTORY_ACTUAL_SALES, Replace(PERIOD_SID%3, 0, 3))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID)/NULLIF(LAG(EXFACTORY_ACTUAL_UNITS, Replace(PERIOD_SID%3, 0, 3))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID),0) AS PREV_QTR_ACTUAL_PRICE,
                         LEAD(EXFACTORY_ACTUAL_SALES, IIF(PERIOD_SID%3 = 1, 2, IIF(PERIOD_SID%3 = 0, 0, 1)))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID)/NULLIF(LEAD(EXFACTORY_ACTUAL_UNITS, IIF(PERIOD_SID%3 = 1, 2, IIF(PERIOD_SID%3 = 0, 0, 1)))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID),0) AS ACTUAL_PRICE,
                         LEAD(EXFACTORY_ACTUAL_SALES, IIF(PERIOD_SID%3 = 1, 5, IIF(PERIOD_SID%3 = 0, 3, 4)))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID)/NULLIF( LEAD(EXFACTORY_ACTUAL_UNITS, IIF(PERIOD_SID%3 = 1, 5, IIF(PERIOD_SID%3 = 0, 3, 4)))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID),0) AS NEXT_QTR_ACT_PRICE,
                         LAG(EXFACTORY_FORECAST_SALES, Replace(PERIOD_SID%3, 0, 3))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID)/NULLIF(LAG(EXFACTORY_FORECAST_UNITS, Replace(PERIOD_SID%3, 0, 3))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID),0) AS PREV_QTR_FORE_PRICE,
                         LEAD(EXFACTORY_FORECAST_SALES, IIF(PERIOD_SID%3 = 1, 2, IIF(PERIOD_SID%3 = 0, 0, 1)))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID)/NULLIF(LEAD(EXFACTORY_FORECAST_UNITS, IIF(PERIOD_SID%3 = 1, 2, IIF(PERIOD_SID%3 = 0, 0, 1)))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID),0) AS FORECAST_PRICE,
                         LEAD(EXFACTORY_FORECAST_SALES, IIF(PERIOD_SID%3 = 1, 5, IIF(PERIOD_SID%3 = 0, 3, 4)))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID)/NULLIF(  LEAD(EXFACTORY_FORECAST_UNITS, IIF(PERIOD_SID%3 = 1, 5, IIF(PERIOD_SID%3 = 0, 3, 4)))
                           OVER(
                             PARTITION BY ITEM_MASTER_SID
                             ORDER BY ITEM_MASTER_SID, PERIOD_SID),0) AS NEXT_QTR_FORE_PRICE
                  FROM   ',@PRODUCT_FILE,' V)A')
			

				  EXEC SP_EXECUTESQL  @SQL1

	 /*Pulling Forecast and Actual Values based Period basis Concept 
   i.e 1-3 use forward quarter : we need to pull last quarter value for Current Quarter all Months
       2-3 use forward quarter : we need to pull last quarter value for current quarter 2nd and 3rd month
	   3   use forward quarter : we need to pull last quarter value for current quarter 3rd month only Logic Ends here */

--Pulling Inventory file values from product file Starts here

		IF Object_id('TEMPDB..#T3') IS NOT NULL
		  DROP TABLE #T3
    
		CREATE TABLE #T3
		  (
			 ITEM_MASTER_SID       INT,
			 PERIOD_SID            INT,
			 PRESENT_ACT_INV       NUMERIC(22, 6),
			 NEXT_QUARTER_ACT_INV  NUMERIC(22, 6),
			 PRESENT_FORE_INV      NUMERIC(22, 6),
			 NEXT_QUARTER_FORE_INV NUMERIC(22, 6),
		  ) 
    

		SET @SQL1=CONCAT(  'INSERT INTO #T3
		  (
		   ITEM_MASTER_SID,       
		   PERIOD_SID ,           
		   PRESENT_ACT_INV,       
		   NEXT_QUARTER_ACT_INV  ,
		   PRESENT_FORE_INV ,     
		   NEXT_QUARTER_FORE_INV 
		  )


          SELECT B.ITEM_MASTER_SID,
                 B.PERIOD_SID,
                 B.PRESENT_ACT_INV,
                 LEAD(B.PRESENT_ACT_INV, 3)
                   OVER(
                     PARTITION BY B.ITEM_MASTER_SID
                     ORDER BY B.ITEM_MASTER_SID, PERIOD_SID) AS NEXT_QUARTER_ACT_INV,
                 B.PRESENT_FORE_INV,
                 LEAD(B.PRESENT_FORE_INV,3)
                   OVER(
                     PARTITION BY B.ITEM_MASTER_SID
                     ORDER BY B.ITEM_MASTER_SID, PERIOD_SID) AS NEXT_QUARTER_FORE_INV
       
          FROM   (SELECT ITEM_MASTER_SID,
                         PERIOD_SID,
                         Sum(PRESENT_ACT_INV)  AS PRESENT_ACT_INV,
                         Sum(PRESENT_FORE_INV) AS PRESENT_FORE_INV
                  FROM   (SELECT A.ITEM_MASTER_SID,
                                 A.PERIOD_SID,
                                 Sum(A.INVENTORY_CUST_ACTUAL_SALES)
                                   OVER(
                                     PARTITION BY A.ITEM_MASTER_SID, QUARTER, YEAR ) AS PRESENT_ACT_INV,
                                 Sum(A.INVENTORY_CUST_FORECAST_SALES)
                                   OVER(
                                     PARTITION BY A.ITEM_MASTER_SID, QUARTER, YEAR ) AS PRESENT_FORE_INV
                          FROM  ',@PRODUCT_FILE,' A
                                 JOIN PERIOD P
                                   ON A.PERIOD_SID = P.PERIOD_SID)C
                  GROUP  BY ITEM_MASTER_SID,
                            PERIOD_SID) B')

				
			EXEC SP_EXECUTESQL @SQL1	

--Pulling Inventory file values from product file Starts here

--Pulling and Inserting all file values into temp table Starts here

          INSERT INTO #TEMP_DETAILS
                      (ITEM_MASTER_SID,
                       PERIOD_SID,
                       FLAG,
                       CUST_ACT_GTS_SALES,
                       CUST_FORE_GTS_SALES,
                       PRESENT_ACT_INV,
                       NEXT_QUARTER_ACT_INV,
                       PRESENT_FORE_INV,
                       NEXT_QUARTER_FORE_INV,
                       ACTUAL_PRES_QTR_PRICE_CHNG,
                       FORE_PRES_QTR_PRICE_CHNG,
                       ACTUAL_NEXT_QTR_PRICE_CHNG,
                       FORE_NEXT_QTR_PRICE_CHNG)
          SELECT A.ITEM_MASTER_SID,
                 A.PERIOD_SID,
                 A.FLAG,
                 B.CUST_ACT_GTS_SALES,
                 B.CUST_FORE_GTS_SALES,
                 C.PRESENT_ACT_INV,
                 C.NEXT_QUARTER_ACT_INV,
                 C.PRESENT_FORE_INV,
                 C.NEXT_QUARTER_FORE_INV,
                 D.ACTUAL_PRES_QTR_PRICE_CHNG,
                 D.FORE_PRES_QTR_PRICE_CHNG,
                 D.ACTUAL_NEXT_QTR_PRICE_CHNG,
                 D.FORE_NEXT_QTR_PRICE_CHNG
          FROM   (SELECT TP.ITEM_MASTER_SID,
                         TP.PERIOD_SID,
                         TP.FLAG
                  FROM   @TEMP_PERIOD TP) A
                 LEFT JOIN (SELECT ITEM_MASTER_SID,
                                   PERIOD_SID,
                                   ( CUST_ACT_GTS_SALES )  AS CUST_ACT_GTS_SALES,
                                   ( CUST_FORE_GTS_SALES ) AS CUST_FORE_GTS_SALES
                            FROM   #T1) B
                        ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                           AND A.PERIOD_SID = B.PERIOD_SID
                 LEFT JOIN (SELECT ITEM_MASTER_SID,
                                   PERIOD_SID,
                                   PRESENT_ACT_INV,
                                   NEXT_QUARTER_ACT_INV,
                                   PRESENT_FORE_INV,
                                   NEXT_QUARTER_FORE_INV
                            FROM   #T3) C
                        ON A.ITEM_MASTER_SID = C.ITEM_MASTER_SID
                           AND A.PERIOD_SID = C.PERIOD_SID
                 LEFT JOIN (SELECT ITEM_MASTER_SID,
                                   PERIOD_SID,
                                   ACTUAL_PRES_QTR_PRICE_CHNG,
                                   FORE_PRES_QTR_PRICE_CHNG,
                                   ACTUAL_NEXT_QTR_PRICE_CHNG,
                                   FORE_NEXT_QTR_PRICE_CHNG
                            FROM   #T2)D
                        ON A.ITEM_MASTER_SID = D.ITEM_MASTER_SID
                           AND A. PERIOD_SID = D.PERIOD_SID

--Pulling and Inserting all file values into temp table Ends here

--Deletion and Reinsewrting into Actual and Projection Starts here
		  
          SET @SQL1=CONCAT('IF EXISTS(SELECT 1
                    FROM   ',@PROJECTION_TABLE,'
                    WHERE  PROJECTION_MASTER_SID = ',@PROJECTION_MASTER_SID,')
            BEGIN
                TRUNCATE TABLE ',@PROJECTION_TABLE,'
            END

          IF EXISTS(SELECT 1
                    FROM  ',@ACTUAL_TABLE,'
                    WHERE   PROJECTION_MASTER_SID = ',@PROJECTION_MASTER_SID,')
            BEGIN
                TRUNCATE TABLE ',@ACTUAL_TABLE,'
            END
			')
			EXEC SP_EXECUTESQL @SQL1
			DECLARE @SQL2 NVARCHAR(MAX)=''
			SET @SQL2=CONCAT('INSERT INTO ',@ACTUAL_TABLE,'
                      (PROJECTION_MASTER_SID,
                       ITEM_MASTER_SID,
                       PERIOD_SID,
                       COMPANY_MASTER_SID,
                       GROSS_TRADE_SALES,
                       INVENTORY_WITHDRAWALS,
                       ELIGIBLE_GROSS_TRADE_SALES,
                       PERIOD_BASIS_PRICE_CHANGE,
                       GTS_ACCRUAL_BASIS)
          SELECT ',@PROJECTION_MASTER_SID,' AS PROJECTION_MASTER_SID,
                 ITEM_MASTER_SID,
                 PERIOD_SID,
                 ',@COMPANY_MASTER_SID,',
                 CUST_ACT_GTS_SALES,
                 CASE
                   WHEN FLAG = 1 THEN NEXT_QUARTER_ACT_INV
                   ELSE PRESENT_ACT_INV
                 END                    INVENTORY,
                 ISNULL(CUST_ACT_GTS_SALES, 0),
                 CASE
                   WHEN FLAG = 1 THEN ACTUAL_NEXT_QTR_PRICE_CHNG
                   ELSE ACTUAL_PRES_QTR_PRICE_CHNG
                 END                    PERIOD_BASIS_PRICE_CHANGE,
                 CUST_ACT_GTS_SALES * ( 1 + ISNULL(( CASE WHEN FLAG = 1 THEN ACTUAL_NEXT_QTR_PRICE_CHNG ELSE ACTUAL_PRES_QTR_PRICE_CHNG END), 0) )
          FROM   #TEMP_DETAILS
          WHERE  PERIOD_SID BETWEEN ',@START_PERIOD_SID,' AND ',@CURRENT_PERIOD_SID,' - 1

          INSERT INTO  ',@PROJECTION_TABLE,'
                      (PROJECTION_MASTER_SID,
                       ITEM_MASTER_SID,
                       PERIOD_SID,
                       COMPANY_MASTER_SID,
                       GROSS_TRADE_SALES,
                       INVENTORY_WITHDRAWALS,
                       ELIGIBLE_GROSS_TRADE_SALES,
                       PERIOD_BASIS_PRICE_CHANGE,
                       GTS_ACCRUAL_BASIS)
          SELECT ',@PROJECTION_MASTER_SID,' AS PROJECTION_MASTER_SID,
                 ITEM_MASTER_SID,
                 PERIOD_SID,
                 ',@COMPANY_MASTER_SID,',
                 CUST_FORE_GTS_SALES,
                 CASE
                   WHEN FLAG = 1 THEN NEXT_QUARTER_FORE_INV
                   ELSE PRESENT_FORE_INV
                 END                    INVENTORY,
                 ISNULL(CUST_FORE_GTS_SALES, 0),
                 CASE
                   WHEN FLAG = 1 THEN FORE_NEXT_QTR_PRICE_CHNG
                   ELSE FORE_PRES_QTR_PRICE_CHNG
                 END                    PERIOD_BASIS_PRICE_CHANGE,
                 CUST_FORE_GTS_SALES * ( 1 + ISNULL(( CASE WHEN FLAG = 1 THEN FORE_NEXT_QTR_PRICE_CHNG ELSE FORE_PRES_QTR_PRICE_CHNG END), 0) )
          FROM   #TEMP_DETAILS
          WHERE  PERIOD_SID BETWEEN ',@CURRENT_PERIOD_SID,' AND ',@END_PERIOD_SID,'')

	
EXEC SP_EXECUTESQL @SQL2,N'@PERIOD_BASIS VARCHAR(100)',@PERIOD_BASIS  = @PERIOD_BASIS 

--Deletion and Reinsewrting into Actual and Projection Ends here

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
          );
      END CATCH
  END 