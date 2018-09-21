IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_ACCRUAL_RATE'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_ACCRUAL_RATE]
  END

GO

CREATE PROCEDURE [dbo].[PRC_ACCRUAL_RATE] (@PROJECTION   INT, 
                                          @USER_ID      INT, 
                                          @SESSION_ID   VARCHAR(100), 
                                          @RATE_BASIS   VARCHAR(100), 
                                          @PERIOD_BASIS VARCHAR(100)) 
AS 

/**********************************************************************************************************
** File Name		:	PRC_ACCRUAL_RATE.sql
** Procedure Name	:	PRC_ACCRUAL_RATE
** Description		:	To generate ARP(Accrual Rate Projection) Rates tab
** Input Parameters	:	@PROJECTION             - Respective Projection ID Creted for Accrual Rate Projection
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
						@RATE_BASIS             - Rate Basis selected in Rate tab.this indicates from which file 
						                          we need to consider to calculate Rate.
						@PERIOD_BASIS           - it is the identification of Period basis selected in data selection.
						                          based on this we will pull values from files and we will perform calculation
											
** Output Parameters:	NA
** Author Name		:	@Lakshmi,@Tharun
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application in Accrual Rates tab to calculate Rate based on selected Rate basis
                        and Period basis.
                        
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
	   DECLARE @ACTUAL_START_DATE  DATETIME, 
                  @PROJ_END_DATE      DATETIME, 
                  @START_PERIOD_SID   INT, 
                  @END_PERIOD_SID     INT, 
                  @CURRENT_PERIOD_SID INT
		DECLARE @SQL NVARCHAR(MAX) 
		DECLARE @ARP_PROJ_TABLE    VARCHAR(200) = CONCAT('ST_ACCRUAL_PROJ_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		        @PRODUCT_FILE               VARCHAR(200)= CONCAT('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
          SELECT @CURRENT_PERIOD_SID = PERIOD_SID 
          FROM   PERIOD 
          WHERE  PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, DATEADD(MM, 0, GETDATE())), 0) 

          SET @ACTUAL_START_DATE = (SELECT DATEADD(MM, DATEDIFF(MM, 0, GETDATE()) - 12, 0)) 
          SET @PROJ_END_DATE = (SELECT DATEADD(M, DATEDIFF(M, 0, DATEADD(DAY, -1, DATEADD(MM, DATEDIFF(MM, 0, GETDATE()) + 36, 0))), 0))

-- Variables Initialization and taking from date as 12 months history and 36 months future Ends here

          --DECLARE @DESCRIPTIONS VARCHAR(50) 

          --SET @DESCRIPTIONS = ( CASE 
          --                        WHEN @RATE_BASIS = 'FORECASTED EX-FACTORY SALES - CUSTOMER/PRODUCT' THEN 'CUSTOMER SALES' 
          --                        WHEN @RATE_BASIS = 'FORECASTED DEMAND' THEN 'DEMAND' 
          --                        WHEN @RATE_BASIS = 'FORECASTED INVENTORY WITHDRAWALS - DETAIL' THEN 'INVENTORY WITHDRAWAL - FORECAST DETAIL'
          --                        WHEN @RATE_BASIS = 'FORECASTED ADJUSTED DEMAND' THEN 'ADJUSTED DEMAND'
          --                      END ) 

          SELECT @START_PERIOD_SID = PERIOD_SID 
          FROM   PERIOD 
          WHERE  PERIOD_DATE = @ACTUAL_START_DATE       

          SELECT @END_PERIOD_SID = PERIOD_SID 
          FROM   PERIOD 
          WHERE  PERIOD_DATE = @PROJ_END_DATE 

-- Pulling CCP+D Combination for Current projection starts here
          IF OBJECT_ID('TEMPDB..#TEMP_MASTER') IS NOT NULL 
            DROP TABLE #TEMP_MASTER 

          CREATE TABLE #TEMP_MASTER 
            ( 
               ACCRUAL_PROJ_DETAILS_SID INT, 
               CCP_DETAILS_SID          INT, 
               CONTRACT_MASTER_SID      INT, 
               COMPANY_MASTER_SID       INT, 
               ITEM_MASTER_SID          INT, 
               RS_MODEL_SID             INT, 
			   RS_CONTRACT_SID          INT, 
               RS_ID                    VARCHAR(50)
            ) 

    SET @SQL =CONCAT('INSERT INTO #TEMP_MASTER 
                      (
                       CCP_DETAILS_SID, 
                       CONTRACT_MASTER_SID, 
                       COMPANY_MASTER_SID, 
                       ITEM_MASTER_SID, 
                       RS_MODEL_SID,
					   RS_CONTRACT_SID,
                       RS_ID) 
          SELECT 
                 CCP.CCP_DETAILS_SID, 
                 CCP.CONTRACT_MASTER_SID, 
                 CCP.COMPANY_MASTER_SID, 
                 CCP.ITEM_MASTER_SID, 
                 AC.RS_MODEL_SID, 
				 AC.RS_CONTRACT_SID,
                 RS.RS_ID
          FROM    ',@ARP_PROJ_TABLE,' AC 
                         
                 INNER JOIN CCP_DETAILS CCP 
                         ON CCP.CCP_DETAILS_SID = AC.CCP_DETAILS_SID 
                 INNER JOIN COMPANY_MASTER CM 
                         ON CM.COMPANY_MASTER_SID = CCP.COMPANY_MASTER_SID 
                 INNER JOIN ITEM_MASTER IM 
                         ON IM.ITEM_MASTER_SID = CCP.ITEM_MASTER_SID 
                 LEFT JOIN RS_CONTRACT RS 
                        ON RS.RS_MODEL_SID = AC.RS_MODEL_SID 
                           AND RS.CONTRACT_MASTER_SID = CCP.CONTRACT_MASTER_SID
						   ----------------------------------CHANGED/REMOVED FOR GAL-1163 
						   AND RS.RS_CONTRACT_SID=AC.RS_CONTRACT_SID 
						   --AND RS.INBOUND_STATUS <> ''D''	')


EXEC SP_EXECUTESQL @SQL

-- Pulling CCP+D Combination for Current projection Ends here   
          ------------------------------------------------------------------------ 
          --SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME, 
          --             @FORECAST_VERSION = FT.[VERSION] 
          --FROM   FILE_MANAGEMENT FT 
          --       INNER JOIN HELPER_TABLE HT 
          --               ON HT.HELPER_TABLE_SID = FT.FILE_TYPE 
          --WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE()) 
          --         AND FT.FROM_PERIOD IS NOT NULL ) 
          --       AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE()) 
          --              OR FT.TO_PERIOD IS NULL ) 
          --       AND HT.LIST_NAME = 'FILE_TYPE' 
          --       AND DESCRIPTION = @DESCRIPTIONS 
          --       AND FT.BUSINESS_UNIT = @BUSINESS_UNIT ----GAL-808 
          --       AND FT.COMPANY = @COMPANY_ID ----GAL-808 
          --ORDER  BY FT.FROM_PERIOD DESC 

          --DECLARE @CUST_ITEM_DETAILS UDT_CUST_ITEM 

          --INSERT INTO @CUST_ITEM_DETAILS 
          --            (COMPANY_MASTER_SID, 
          --             ITEM_MASTER_SID) 
          --SELECT DISTINCT NULL, 
          --                ITEM_MASTER_SID 
          --FROM   #TEMP_MASTER 

          --DECLARE @ITEM_DETAILS UDT_ITEM 

          --INSERT INTO @ITEM_DETAILS 
          --            (ITEM_MASTER_SID) 
          --SELECT DISTINCT ITEM_MASTER_SID 
          --FROM   #TEMP_MASTER 

--assigning temp tables to varaiable Starts here
          DECLARE @ARP_SALES_ACTUAL_TABLE     VARCHAR(200) = CONCAT('ST_ACCRUAL_SALES_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                  @ARP_SALES_PROJECTION_TABLE VARCHAR(200) = CONCAT('ST_ACCRUAL_SALES_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                  @ARP_RATE_ACTUAL_TABLE      VARCHAR(200) = CONCAT('ST_ACCRUAL_RATE_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
                  @ARP_RATE_PROJECTION_TABLE  VARCHAR(200) = CONCAT('ST_ACCRUAL_RATE_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
--assigning temp tables to varaiable Ends here
      
--Creating table for storing values pulled from file based on Peiord basis selected Starts here

          IF OBJECT_ID('TEMPDB..#TEMP_DETAILS') IS NOT NULL 
            DROP TABLE #TEMP_DETAILS 

          CREATE TABLE #TEMP_DETAILS 
            ( 
               ITEM_MASTER_SID          INT, 
               PERIOD_SID               INT, 
               GTS_SALES_ACTUALS        NUMERIC(22, 6), 
               GTS_SALES_PROJECTED      NUMERIC(22, 6), 
               GTS_SALES_PROJECTED_PRS  NUMERIC(22, 6), 
               GTS_SALES_PROJECTED_PRSF NUMERIC(22, 6), 
               FLAG                     BIT 
            ) 

--Creating table for storing values pulled from file based on Peiord basis selected Ends here

--Pulling GTS Calculation Starts here based on Period basis price change selected on rates tab
          IF @RATE_BASIS = 'FORECASTED EX-FACTORY SALES - CUSTOMER/PRODUCT' 
            BEGIN 
              SET @SQL=CONCAT( ' INSERT INTO #TEMP_DETAILS 
                            (ITEM_MASTER_SID, 
                             GTS_SALES_ACTUALS, 
                             GTS_SALES_PROJECTED, 
                             GTS_SALES_PROJECTED_PRS, 
                             GTS_SALES_PROJECTED_PRSF, 
                             PERIOD_SID, 
                             FLAG) 
                SELECT ITEM_MASTER_SID, 
                       GTS_SALES_ACTUALS= ACT_GROSS_AMOUNT, 
                       GTS_SALES_PROJECTED= DEMAND_PROJECTED, 
                       GTS_SALES_PROJECTED_PRS= CASE 
                                                  WHEN A.FLAG = 0 THEN ISNULL(A.GTS_SALES_PROJECTED_PRS, 0)
                                                  ELSE LEAD(GTS_SALES_PROJECTED_PRS, 3) 
                                                         OVER ( 
                                                           PARTITION BY ITEM_MASTER_SID 
                                                           ORDER BY PERIOD_SID) 
                                                END, 
                       GTS_SALES_PROJECTED_PRSF= CASE 
                                                   WHEN A.FLAG = 0 THEN ISNULL(A.GTS_SALES_PROJECTED_PRSF, 0)
                                                   ELSE LEAD(GTS_SALES_PROJECTED_PRSF, 3) 
                                                          OVER ( 
                                                            PARTITION BY ITEM_MASTER_SID 
                                                            ORDER BY PERIOD_SID) 
                                                 END, 
                       PERIOD_SID, 
                       FLAG 
                FROM   (SELECT A.ITEM_MASTER_SID, 
                               EXFACTORY_CUST_ACTUAL_SALES                                 AS ACT_GROSS_AMOUNT,
                               EXFACTORY_CUST_FORECAST_SALES                                AS DEMAND_PROJECTED,
                               SUM(EXFACTORY_CUST_ACTUAL_SALES) 
                                 OVER ( 
                                   PARTITION BY A.ITEM_MASTER_SID, QUARTER, YEAR) AS GTS_SALES_PROJECTED_PRS,
                               SUM(EXFACTORY_CUST_FORECAST_SALES) 
                                 OVER ( 
                                   PARTITION BY A.ITEM_MASTER_SID, QUARTER, YEAR) AS GTS_SALES_PROJECTED_PRSF,
                               P.PERIOD_SID, 
                               CASE 
                                 WHEN @PERIOD_BASIS = ''MONTH 2-3 USE FORWARD QUARTER RATE'' 
                                      AND P.PERIOD_SID%3 IN ( 0, 2 ) THEN 1 
                                 WHEN @PERIOD_BASIS = ''MONTH 3 USE FORWARD QUARTER RATE'' 
                                      AND P.PERIOD_SID%3 = 0 THEN 1 
                                 WHEN @PERIOD_BASIS = ''MONTH 1-3 USE FORWARD QUARTER RATE'' THEN 1 
                                 ELSE 0 
                               END                                                FLAG 
                        FROM  ',@PRODUCT_FILE,' A
                               INNER JOIN PERIOD P 
                                       ON P.PERIOD_SID = A.PERIOD_SID)A' )

			EXEC sp_executesql @SQL,N'@PERIOD_BASIS varchar(100)',@PERIOD_BASIS=@PERIOD_BASIS

--Pulling GTS Calculation Ends here based on Period basis price change selected on rates tab	

            END 

/*Pulling Inventory if rate basis is FORECASTED INVENTORY WITHDRAWALS - DETAIL based on Period basis 
price change selected on rates tab Starts here */

          ELSE IF @RATE_BASIS = 'FORECASTED INVENTORY WITHDRAWALS - DETAIL' 
            BEGIN 
                 SET @SQL=CONCAT( ' INSERT INTO #TEMP_DETAILS 
                            (ITEM_MASTER_SID, 
                             GTS_SALES_ACTUALS, 
                             GTS_SALES_PROJECTED, 
                             GTS_SALES_PROJECTED_PRS, 
                             GTS_SALES_PROJECTED_PRSF, 
                             PERIOD_SID, 
                             FLAG) 
                SELECT ITEM_MASTER_SID, 
                       GTS_SALES_ACTUALS= ACT_GROSS_AMOUNT, 
                       GTS_SALES_PROJECTED= DEMAND_PROJECTED, 
                       GTS_SALES_PROJECTED_PRS= CASE 
                                                  WHEN A.FLAG = 0 THEN ISNULL(A.GTS_SALES_PROJECTED_PRS, 0)
                                                  ELSE LEAD(GTS_SALES_PROJECTED_PRS, 3) 
                                                         OVER ( 
                                                           PARTITION BY ITEM_MASTER_SID 
                                                           ORDER BY PERIOD_SID) 
                                                END, 
                       GTS_SALES_PROJECTED_PRSF= CASE 
                                                   WHEN A.FLAG = 0 THEN ISNULL(A.GTS_SALES_PROJECTED_PRSF, 0)
                                                   ELSE LEAD(GTS_SALES_PROJECTED_PRSF, 3) 
                                                          OVER ( 
                                                            PARTITION BY ITEM_MASTER_SID 
                                                            ORDER BY PERIOD_SID) 
                                                 END, 
                       PERIOD_SID, 
                       FLAG 
                FROM   (SELECT A.ITEM_MASTER_SID, 
                               INVENTORY_CUST_ACTUAL_SALES                               AS ACT_GROSS_AMOUNT,
                               INVENTORY_CUST_FORECAST_SALES                               AS DEMAND_PROJECTED,
                               SUM(INVENTORY_CUST_ACTUAL_SALES) 
                                 OVER ( 
                                   PARTITION BY A.ITEM_MASTER_SID, QUARTER, YEAR) AS GTS_SALES_PROJECTED_PRS,
                               SUM(INVENTORY_CUST_FORECAST_SALES) 
                                 OVER ( 
                                   PARTITION BY A.ITEM_MASTER_SID, QUARTER, YEAR) AS GTS_SALES_PROJECTED_PRSF,
                               P.PERIOD_SID, 
                               CASE 
                                 WHEN @PERIOD_BASIS = ''MONTH 2-3 USE FORWARD QUARTER RATE'' 
                                      AND P.PERIOD_SID%3 IN ( 0, 2 ) THEN 1 
                                 WHEN @PERIOD_BASIS = ''MONTH 3 USE FORWARD QUARTER RATE'' 
                                      AND P.PERIOD_SID%3 = 0 THEN 1 
                                 WHEN @PERIOD_BASIS = ''MONTH 1-3 USE FORWARD QUARTER RATE'' THEN 1 
                                 ELSE 0 
                               END                                                FLAG 
                        FROM   ',@PRODUCT_FILE,' A 
                               INNER JOIN PERIOD P 
                                       ON P.PERIOD_SID = A.PERIOD_SID)A ')
					EXEC SP_EXECUTESQL @SQL,N'@PERIOD_BASIS VARCHAR(100)',@PERIOD_BASIS=@PERIOD_BASIS
            END 

/*Pulling Inventory if rate basis is FORECASTED INVENTORY WITHDRAWALS - DETAIL based on Period basis 
price change selected on rates tab Ends here */

/*Pulling Inventory if rate basis is FORECASTED DEMAND based on Period basis price change selected on rates tab Starts here */

          ELSE IF @RATE_BASIS = 'FORECASTED DEMAND' 
            BEGIN 
                SET @SQL=CONCAT( ' INSERT INTO #TEMP_DETAILS 
                            (ITEM_MASTER_SID, 
                             GTS_SALES_ACTUALS, 
                             GTS_SALES_PROJECTED, 
                             GTS_SALES_PROJECTED_PRS, 
                             GTS_SALES_PROJECTED_PRSF, 
                             PERIOD_SID, 
                             FLAG) 
                SELECT ITEM_MASTER_SID, 
                       GTS_SALES_ACTUALS= ACT_GROSS_AMOUNT, 
                       GTS_SALES_PROJECTED= DEMAND_PROJECTED, 
                       GTS_SALES_PROJECTED_PRS= CASE 
                                                  WHEN A.FLAG = 0 THEN ISNULL(A.GTS_SALES_PROJECTED_PRS, 0)
                                                  ELSE LEAD(GTS_SALES_PROJECTED_PRS, 3) 
                                                         OVER ( 
                                                           PARTITION BY ITEM_MASTER_SID 
                                                           ORDER BY PERIOD_SID) 
                                                END, 
                       GTS_SALES_PROJECTED_PRSF= CASE 
                                                   WHEN A.FLAG = 0 THEN ISNULL(A.GTS_SALES_PROJECTED_PRSF, 0)
                                                   ELSE LEAD(GTS_SALES_PROJECTED_PRSF, 3) 
                                                          OVER ( 
                                                            PARTITION BY ITEM_MASTER_SID 
                                                            ORDER BY PERIOD_SID) 
                                                 END, 
                       PERIOD_SID, 
                       FLAG 
                FROM   (SELECT A.ITEM_MASTER_SID, 
                               DEMAND_ACTUAL_SALES                                   AS ACT_GROSS_AMOUNT,
                               DEMAND_FORECAST_SALES                                   AS DEMAND_PROJECTED,
                               SUM(DEMAND_ACTUAL_SALES) 
                                 OVER ( 
                                   PARTITION BY A.ITEM_MASTER_SID, QUARTER, YEAR) AS GTS_SALES_PROJECTED_PRS,
                               SUM(DEMAND_FORECAST_SALES) 
                                 OVER ( 
                                   PARTITION BY A.ITEM_MASTER_SID, QUARTER, YEAR) AS GTS_SALES_PROJECTED_PRSF,
                               P.PERIOD_SID, 
                               CASE 
                                 WHEN @PERIOD_BASIS = ''MONTH 2-3 USE FORWARD QUARTER RATE'' 
                                      AND P.PERIOD_SID%3 IN ( 0, 2 ) THEN 1 
                                 WHEN @PERIOD_BASIS = ''MONTH 3 USE FORWARD QUARTER RATE'' 
                                      AND P.PERIOD_SID%3 = 0 THEN 1 
                                 WHEN @PERIOD_BASIS = ''MONTH 1-3 USE FORWARD QUARTER RATE'' THEN 1 
                                 ELSE 0 
                               END                                                FLAG 
                        FROM   ',@PRODUCT_FILE,' A 
                               INNER JOIN PERIOD P 
                                       ON P.PERIOD_SID = A.PERIOD_SID)A ')

					EXEC SP_EXECUTESQL @SQL,N'@PERIOD_BASIS VARCHAR(100)',@PERIOD_BASIS=@PERIOD_BASIS
            END 
/*Pulling Inventory if rate basis is FORECASTED DEMAND based on Period basis price change selected on rates tab Ends here */

/*Pulling Inventory if rate basis is FORECASTED ADJUSTED DEMAND based on Period basis price change selected on rates tab Starts here 
i.e 1-3 use forward quarter : we need to pull last quarter value for Current Quarter all Months
       2-3 use forward quarter : we need to pull last quarter value for current quarter 2nd and 3rd month
	   3   use forward quarter : we need to pull last quarter value for current quarter 3rd month*/

          ELSE IF @RATE_BASIS = 'FORECASTED ADJUSTED DEMAND' 
            BEGIN 
                 SET @SQL=CONCAT( ' INSERT INTO #TEMP_DETAILS 
                            (ITEM_MASTER_SID, 
                             GTS_SALES_ACTUALS, 
                             GTS_SALES_PROJECTED, 
                             GTS_SALES_PROJECTED_PRS, 
                             GTS_SALES_PROJECTED_PRSF, 
                             PERIOD_SID, 
                             FLAG) 
                SELECT ITEM_MASTER_SID, 
                       GTS_SALES_ACTUALS= ACT_GROSS_AMOUNT, 
                       GTS_SALES_PROJECTED= DEMAND_PROJECTED, 
                       GTS_SALES_PROJECTED_PRS= CASE 
                                                  WHEN A.FLAG = 0 THEN ISNULL(A.GTS_SALES_PROJECTED_PRS, 0)
                                                  ELSE LEAD(GTS_SALES_PROJECTED_PRS, 3) 
                                                         OVER ( 
                                                           PARTITION BY ITEM_MASTER_SID 
                                                           ORDER BY PERIOD_SID) 
                                                END, 
                       GTS_SALES_PROJECTED_PRSF= CASE 
                                                   WHEN A.FLAG = 0 THEN ISNULL(A.GTS_SALES_PROJECTED_PRSF, 0)
                                                   ELSE LEAD(GTS_SALES_PROJECTED_PRSF, 3) 
                                                          OVER ( 
                                                            PARTITION BY ITEM_MASTER_SID 
                                                            ORDER BY PERIOD_SID) 
                                                 END, 
                       PERIOD_SID, 
                       FLAG 
                FROM   (SELECT A.ITEM_MASTER_SID, 
                               ADJUSTED_DEMAND_ACTUAL_SALES                                   AS ACT_GROSS_AMOUNT,
                               ADJUSTED_DEMAND_FORECAST_SALES                                   AS DEMAND_PROJECTED,
                               SUM(ADJUSTED_DEMAND_ACTUAL_SALES) 
                                 OVER ( 
                                   PARTITION BY A.ITEM_MASTER_SID, QUARTER, YEAR) AS GTS_SALES_PROJECTED_PRS,
                               SUM(ADJUSTED_DEMAND_FORECAST_SALES) 
                                 OVER ( 
                                   PARTITION BY A.ITEM_MASTER_SID, QUARTER, YEAR) AS GTS_SALES_PROJECTED_PRSF,
                               P.PERIOD_SID, 
                               CASE 
                                 WHEN @PERIOD_BASIS = ''MONTH 2-3 USE FORWARD QUARTER RATE'' 
                                      AND P.PERIOD_SID%3 IN ( 0, 2 ) THEN 1 
                                 WHEN @PERIOD_BASIS = ''MONTH 3 USE FORWARD QUARTER RATE'' 
                                      AND P.PERIOD_SID%3 = 0 THEN 1 
                                 WHEN @PERIOD_BASIS = ''MONTH 1-3 USE FORWARD QUARTER RATE'' THEN 1 
                                 ELSE 0 
                               END                                                FLAG 
                        FROM  ',@PRODUCT_FILE,' A 
                               INNER JOIN PERIOD P 
                                       ON P.PERIOD_SID = A.PERIOD_SID)A')
							EXEC SP_EXECUTESQL @SQL,N'@PERIOD_BASIS VARCHAR(100)',@PERIOD_BASIS=@PERIOD_BASIS
            END 

/*Pulling Inventory if rate basis is FORECASTED ADJUSTED DEMAND based on Period basis price change selected on rates tab Ends here 
i.e 1-3 use forward quarter : we need to pull last quarter value for Current Quarter all Months
       2-3 use forward quarter : we need to pull last quarter value for current quarter 2nd and 3rd month
	   3   use forward quarter : we need to pull last quarter value for current quarter 3rd month*/

--Pulling Latest approved CCP+D Combination for the same ccp+d selected in Data selection for Current Projection Starts here

          IF OBJECT_ID('TEMPDB..#TEMP_PROJ_DETAILS') IS NOT NULL 
            DROP TABLE #TEMP_PROJ_DETAILS 

          CREATE TABLE #TEMP_PROJ_DETAILS 
            ( 
               CCP_DETAILS_SID_ORGI     INT, 
               PROJECTION_MASTER_SID    INT, 
               PROJECTION_DETAILS_SID   INT, 
               CCP_DETAILS_SID          INT, 
               FORECASTING_TYPE         VARCHAR(50), 
               RS_MODEL_SID             INT, 
			   RS_CONTRACT_SID             INT 
            ) 

          INSERT INTO #TEMP_PROJ_DETAILS 
                      (CCP_DETAILS_SID_ORGI, 
                       PROJECTION_MASTER_SID, 
                       PROJECTION_DETAILS_SID, 
                       CCP_DETAILS_SID, 
                       FORECASTING_TYPE, 
                       RS_MODEL_SID
					   ,RS_CONTRACT_SID) 
          SELECT CCP_DETAILS_SID_ORGI, 
                 PROJECTION_MASTER_SID, 
                 PROJECTION_DETAILS_SID, 
                 CCP_DETAILS_SID, 
                 FORECASTING_TYPE, 
                 RS_MODEL_SID
				 ,RS_CONTRACT_SID 
          FROM   (SELECT OUT_P.CCP_DETAILS_SID_ORGI, 
                         WM.PROJECTION_MASTER_SID, 
                         OUT_P.PROJECTION_DETAILS_SID, 
                         OUT_P.CCP_DETAILS_SID, 
                         OUT_P.RS_MODEL_SID, 
						 OUT_P.RS_CONTRACT_SID,
                         OUT_P.FORECASTING_TYPE, 
                         ROW_NUMBER() 
                           OVER ( 
                             PARTITION BY CCP_DETAILS_SID_ORGI, OUT_P.RS_CONTRACT_SID,RS_MODEL_SID, OUT_P.CCP_DETAILS_SID 
                             ORDER BY WM.MODIFIED_DATE DESC ) RNO 
                  FROM   WORKFLOW_MASTER WM 
                         INNER JOIN (SELECT TMP.CCP_DETAILS_SID AS CCP_DETAILS_SID_ORGI, 
                                            PM.PROJECTION_MASTER_SID, 
                                            PD.PROJECTION_DETAILS_SID, 
                                            PD.CCP_DETAILS_SID, 
                                            TMP.RS_MODEL_SID, 
											TMP.RS_CONTRACT_SID,
                                            PM.FORECASTING_TYPE 
                                     FROM   #TEMP_MASTER TMP 
                                            INNER JOIN PROJECTION_DETAILS PD 
                                                    ON PD.CCP_DETAILS_SID = TMP.CCP_DETAILS_SID
                                            INNER JOIN PROJECTION_MASTER PM 
                                                    ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                                     WHERE  PM.SAVE_FLAG = 1) OUT_P 
                                 ON WM.PROJECTION_MASTER_SID = OUT_P.PROJECTION_MASTER_SID 
								 WHERE EXISTS (SELECT HELPER_TABLE_SID
                                                                              FROM   HELPER_TABLE H1
                                                                              WHERE  LIST_NAME = 'WORKFLOWSTATUS'
                                                                                     AND DESCRIPTION = 'APPROVED'
																					 AND H1.HELPER_TABLE_SID=WM.WORKFLOW_STATUS_ID))A
                  --WHERE  WM.WORKFLOW_STATUS_ID IN (SELECT HELPER_TABLE_SID 
                  --                                FROM   HELPER_TABLE 
                  --                                WHERE  LIST_NAME = 'WORKFLOWSTATUS' 
                  --                                       AND DESCRIPTION = 'APPROVED')) A 
          WHERE  RNO = 1 
          ORDER  BY CCP_DETAILS_SID_ORGI 

--Pulling Latest approved CCP+D Combination for the same ccp+d selected in Data selection for Current Projection Ends here

          --DECLARE @ACTUAL_INFO UDT_CCP_DETAILS 

          --INSERT INTO @ACTUAL_INFO 
          --            (CONTRACT_MASTER_SID, 
          --             COMPANY_MASTER_SID, 
          --             ITEM_MASTER_SID, 
          --             DISCOUNT_ID) 
          --SELECT T.CONTRACT_MASTER_SID, 
          --       T.COMPANY_MASTER_SID, 
          --       T.ITEM_MASTER_SID, 
          --       RS_ID 
          --FROM   #TEMP_MASTER T 
          --       JOIN #TEMP_PROJ_DETAILS CCP 
          --         ON T.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID 
          --            AND T.RS_MODEL_SID = CCP.RS_MODEL_SID 

--Pulling Discount Amounts for all Forecasting types(Mandated+Non Mandated) for latest CCP+D Combination Starts here
          IF OBJECT_ID('TEMPDB..#TEMP_RATES') IS NOT NULL 
            DROP TABLE #TEMP_RATES 

          SELECT A.CCP_DETAILS_SID, 
		         A.RS_MODEL_SID,
				 A.RS_CONTRACT_SID,
                 B.PERIOD_SID, 
                 C.AMOUNT           AS ACTUAL_PAYMENTS, 
                 C.PRES_QTR_PAY     AS PRES_QTR_PAY, 
                 C.NEXT_QTR_PAY     AS NEXT_QTR_PAY, 
                 G.AMOUNT           FORECAST_PAYMENTS, 
                 G.PRES_QTR_PAY_FOR PRES_QTR_PAY_FOR, 
                 G.NEXT_QTR_PAY_FOR NEXT_QTR_PAY_FOR 
          INTO   #TEMP_RATES 
          FROM   #TEMP_MASTER A 
				--JOIN #TEMP_DETAILS B 
                 LEFT JOIN #TEMP_DETAILS B 
                   ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID 
                 JOIN #TEMP_PROJ_DETAILS M 
                   ON M.CCP_DETAILS_SID_ORGI = A.CCP_DETAILS_SID
				   AND M.RS_MODEL_SID=A.RS_MODEL_SID 
				   AND M.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                 LEFT JOIN (SELECT CCP_DETAILS_SID,
				                   RS_MODEL_SID,
								   RS_CONTRACT_SID,
                                   PERIOD_SID, 
                                   AMOUNT, 
                                   PRES_QTR_PAY, 
                                   LEAD(PRES_QTR_PAY, 3) 
                                     OVER( 
                                       PARTITION BY CCP_DETAILS_SID,RS_CONTRACT_SID,
				                    RS_MODEL_SID 
                                       ORDER BY PERIOD_SID) AS NEXT_QTR_PAY 
                            FROM   (SELECT   A1.CCP_DETAILS_SID,
							               A1.RS_MODEL_SID,
										   A1.RS_CONTRACT_SID, 
                                           A1.PERIOD_SID, 
                                           A1.DISCOUNT                                                      AS AMOUNT,
                                          SUM(DISCOUNT) 
                                             OVER ( 
                                               PARTITION BY A1.CCP_DETAILS_SID,A1.RS_CONTRACT_SID,
				                    A1.RS_MODEL_SID, A1.QUARTER, A1.YEAR) AS PRES_QTR_PAY FROM 
                                    (SELECT  A.CCP_DETAILS_SID,
							               A.RS_MODEL_SID,
										   B.RS_CONTRACT_SID, 
                                           A.PERIOD_SID,
										   SUM(DISCOUNT)  DISCOUNT,
										   P.QUARTER, P.YEAR
										   
										    FROM   ACTUALS_DETAILS A JOIN #TEMP_MASTER B ON A.CCP_DETAILS_SID=B.CCP_DETAILS_SID AND A.RS_MODEL_SID=B.RS_MODEL_SID
									JOIN PERIOD P ON 
									p.PERIOD_sID=a.PERIOD_SID AND P.PERIOD_DATE between @ACTUAL_START_DATE and DATEADD(MM, DATEDIFF(MM, 0, GETDATE()) - 1, 0)
									GROUP BY   A.CCP_DETAILS_SID,
							               A.RS_MODEL_SID, 
										   B.RS_CONTRACT_SID,
                                           A.PERIOD_SID,
										   P.QUARTER, P.YEAR
									)A1) A)C 
                        ON C.CCP_DETAILS_SID = A.CCP_DETAILS_SID 
						   AND C.RS_MODEL_SID=A.RS_MODEL_SID
						   AND C.RS_CONTRACT_SID=A.RS_CONTRACT_SID
                           AND B.PERIOD_SID = C.PERIOD_SID 
                 LEFT JOIN (SELECT CCP_DETAILS_SID_ORGI,
				                    RS_MODEL_SID, 
									RS_CONTRACT_SID,
                                   PERIOD_SID, 
                                   AMOUNT, 
                                   PRES_QTR_PAY_FOR, 
                                   LEAD(PRES_QTR_PAY_FOR, 3) 
                                     OVER ( 
                                       PARTITION BY CCP_DETAILS_SID_ORGI,RS_CONTRACT_SID,
				                    RS_MODEL_SID 
                                       ORDER BY PERIOD_SID) AS NEXT_QTR_PAY_FOR 
                            FROM   (SELECT T.CCP_DETAILS_SID_ORGI,
							               T.RS_MODEL_SID, 
										   T.RS_CONTRACT_SID,
                                           NM.PERIOD_SID, 
                                           ( NM.PROJECTION_SALES )                                         AS AMOUNT,
                                           SUM(NM.PROJECTION_SALES) 
                                             OVER ( 
                                               PARTITION BY T.CCP_DETAILS_SID_ORGI,
							               T.RS_MODEL_SID,T.RS_CONTRACT_SID, P.QUARTER, P.YEAR) AS PRES_QTR_PAY_FOR
                                    FROM   [DBO].[NM_DISCOUNT_PROJECTION] NM 
                                           INNER JOIN #TEMP_PROJ_DETAILS T 
                                                   ON T.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                                      AND NM.RS_MODEL_SID = T.RS_MODEL_SID 
													  AND NM.RS_CONTRACT_SID = T.RS_CONTRACT_SID
                                           JOIN PERIOD P 
                                             ON P.PERIOD_SID = NM.PERIOD_SID 
                                     WHERE  T.FORECASTING_TYPE = 'NON MANDATED' 

									 UNION ALL

									 SELECT  T.CCP_DETAILS_SID_ORGI,
							                 T.RS_MODEL_SID,
											 T.RS_CONTRACT_SID, 
									         NM.PERIOD_SID,
                                            ISNULL(NM.PROJECTION_DISCOUNT_DOLLAR,0) AS AMOUNT,
                                            ISNULL(SUM(NM.PROJECTION_DISCOUNT_DOLLAR) OVER ( PARTITION BY  T.CCP_DETAILS_SID_ORGI,T.RS_CONTRACT_SID,T.RS_MODEL_SID, P.QUARTER, P.YEAR),0) AS PRES_QTR_PAY_FOR
                                     FROM   [DBO].[NM_PPA_PROJECTION] NM 
                                            INNER JOIN #TEMP_PROJ_DETAILS T ON T.PROJECTION_DETAILS_SID = NM.PROJECTION_DETAILS_SID
                                                       AND NM.RS_MODEL_SID = T.RS_MODEL_SID 
													   AND NM.RS_CONTRACT_SID = T.RS_CONTRACT_SID
                                            JOIN PERIOD P 
                                              ON P.PERIOD_SID = NM.PERIOD_SID 
                                     WHERE  T.FORECASTING_TYPE = 'NON MANDATED' 

                                     UNION ALL 

                                     SELECT T.CCP_DETAILS_SID_ORGI,
									       T.RS_MODEL_SID, 
										   T.RS_CONTRACT_SID,
                                           M.PERIOD_SID, 
                                           ( ISNULL(M.PROJECTION_SALES, 0) 
                                             + ISNULL(MS.PROJECTION_SALES, 0) )                            AS AMOUNT,
                                           SUM(( ISNULL(M.PROJECTION_SALES, 0) 
                                                 + ISNULL(MS.PROJECTION_SALES, 0) )) 
                                             OVER ( 
                                               PARTITION BY T.CCP_DETAILS_SID_ORGI,
									       T.RS_MODEL_SID, P.QUARTER, P.YEAR) AS PRES_QTR_PAY_FOR
                                    FROM   #TEMP_PROJ_DETAILS T 
                                           INNER JOIN [DBO].[M_DISCOUNT_PROJECTION] M 
                                                   ON T.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID
                                                      AND M.SAVE_FLAG = 1 
                                           JOIN PERIOD P 
                                             ON P.PERIOD_SID = M.PERIOD_SID 
                                           LEFT JOIN M_SUPPLEMENTAL_DISC_PROJ MS 
                                                  ON MS.PROJECTION_DETAILS_SID = M.PROJECTION_DETAILS_SID
                                                     AND MS.PERIOD_SID = M.PERIOD_SID 
                                                     AND P.PERIOD_SID = MS.PERIOD_SID 
                                    WHERE  T.FORECASTING_TYPE = 'MANDATED') A)G 
                        ON G.CCP_DETAILS_SID_ORGI = A.CCP_DETAILS_SID
						   AND G.RS_MODEL_SID=A.RS_MODEL_SID 
						   AND G.RS_CONTRACT_SID=A.RS_CONTRACT_SID 
                           AND G.PERIOD_SID = B.PERIOD_SID 

--Pulling Discount Amounts for all Forecasting types(Mandated+Non Mandated) for latest CCP+D Combination Ends here

--Deleting and Reinserting for the Current projection for both Rate Actual and Forecast Starts here

  SET @SQL =CONCAT('TRUNCATE TABLE ',@ARP_RATE_ACTUAL_TABLE,'; TRUNCATE TABLE ',@ARP_RATE_PROJECTION_TABLE)
EXEC SP_EXECUTESQL @SQL

SET @SQL=Concat('INSERT INTO ', @ARP_RATE_ACTUAL_TABLE, '
		   (CCP_DETAILS_SID,RS_MODEL_SID,PERIOD_SID,ACCRUAL_RATE,ACCRUAL_AMOUNT,PAYMENTS,DETAILS_ACCRUAL_RATE,RS_CONTRACT_SID)
		                                        SELECT TM.CCP_DETAILS_SID,
												TM.RS_MODEL_SID,
												TM.PERIOD_SID,
												COALESCE(ISNULL(TR.ACTUAL_PAYMENTS, 0) / NULLIF(TD.GTS_SALES_ACTUALS, 0), 0) ACCRUAL_RATE,
												COALESCE(ISNULL(TR.ACTUAL_PAYMENTS, 0) / NULLIF(TD.GTS_SALES_ACTUALS, 0), 0) * ISNULL(STA.SALES, 0) ACCRUAL_AMOUNT,
												CASE 
												 WHEN TD.FLAG = 1 THEN ISNULL(NEXT_QTR_PAY, 0)
												 ELSE ISNULL(PRES_QTR_PAY, 0)
												END PAYMENTS,
												COALESCE(( CASE
												            WHEN TD.FLAG = 1 THEN ISNULL(NEXT_QTR_PAY, 0)
															ELSE ISNULL(PRES_QTR_PAY, 0)
														   END ) / NULLIF(GTS_SALES_PROJECTED_PRS, 0), 0) AS RATE,TM.RS_CONTRACT_SID FROM
											(SELECT CCP_DETAILS_SID,
											RS_MODEL_SID,
											RS_CONTRACT_SID,
											PERIOD_SID,
											ITEM_MASTER_SID 
											FROM
											 #TEMP_MASTER 
											CROSS JOIN PERIOD 
											WHERE PERIOD_SID BETWEEN ', @START_PERIOD_SID, ' AND ', @CURRENT_PERIOD_SID - 1, ') TM 
											--INNER JOIN 
                                            LEFT JOIN 
											#TEMP_DETAILS TD 
											ON TM.ITEM_MASTER_SID=TD.ITEM_MASTER_SID 
											AND TM.PERIOD_SID=TD.PERIOD_SID 
											LEFT JOIN #TEMP_RATES TR 
											ON TM.CCP_DETAILS_SID = TR.CCP_DETAILS_SID AND
											   TM.RS_MODEL_SID=TR.RS_MODEL_SID 
											   AND TM.RS_CONTRACT_SID=TR.RS_CONTRACT_SID
											AND TR.PERIOD_SID=TM.PERIOD_SID 
											LEFT JOIN ', @ARP_SALES_ACTUAL_TABLE, ' STA 
											 ON STA.ITEM_MASTER_SID = TM.ITEM_MASTER_SID  
											AND STA.PERIOD_SID = TD.PERIOD_SID  
											AND STA.PROJECTION_MASTER_SID=', @PROJECTION, ' 


											INSERT INTO  ', @ARP_RATE_PROJECTION_TABLE, '  
											(CCP_DETAILS_SID,RS_MODEL_SID,RS_CONTRACT_SID,PERIOD_SID,ACCRUAL_RATE,ACCRUAL_AMOUNT,PAYMENTS,DETAILS_ACCRUAL_RATE)
											SELECT 
											TM.CCP_DETAILS_SID,
											TM.RS_MODEL_SID,
											TM.RS_CONTRACT_SID,
											TM.PERIOD_SID,
											COALESCE(ISNULL(TR.FORECAST_PAYMENTS, 0) / NULLIF(TD.GTS_SALES_PROJECTED, 0), 0) ACCRUAL_RATE,
											COALESCE(ISNULL(TR.FORECAST_PAYMENTS, 0) / NULLIF(TD.GTS_SALES_PROJECTED, 0), 0) * ISNULL(STA.SALES, 0) ACCRUAL_AMOUNT, 
											CASE WHEN TD.FLAG = 1 THEN 
											ISNULL(NEXT_QTR_PAY_FOR, 0) ELSE ISNULL(PRES_QTR_PAY_FOR, 0) END PAYMENTS,
											COALESCE(( CASE WHEN TD.FLAG = 1 THEN 
											ISNULL(NEXT_QTR_PAY_FOR, 0) ELSE ISNULL(PRES_QTR_PAY_FOR, 0) END ) / NULLIF(GTS_SALES_PROJECTED_PRSF, 0), 0) AS RATE
											FROM 
											(SELECT CCP_DETAILS_SID,
											RS_MODEL_SID,
											RS_CONTRACT_SID,
											TM.ITEM_MASTER_SID,
											PERIOD_SID 
											FROM #TEMP_MASTER TM
											CROSS JOIN PERIOD WHERE PERIOD_SID BETWEEN ', @CURRENT_PERIOD_SID, ' AND ', @END_PERIOD_SID, ' 
											 )TM 
											--INNER JOIN #TEMP_DETAILS TD 
                                            LEFT JOIN #TEMP_DETAILS TD 
											ON TM.ITEM_MASTER_SID=TD.ITEM_MASTER_SID AND TM.PERIOD_SID=TD.PERIOD_SID
											LEFT JOIN #TEMP_RATES TR 
											ON TM.CCP_DETAILS_SID = TR.CCP_DETAILS_SID
											AND TM.RS_MODEL_SID=TR.RS_MODEL_SID
											AND TM.RS_CONTRACT_SID=TR.RS_CONTRACT_SID
											 AND 
											TR.PERIOD_SID=TM.PERIOD_SID 
											LEFT JOIN ', @ARP_SALES_PROJECTION_TABLE, ' STA 
											ON STA.ITEM_MASTER_SID = TM.ITEM_MASTER_SID 
											 AND STA.PERIOD_SID = TD.PERIOD_SID AND STA.PROJECTION_MASTER_SID=', @PROJECTION, '') 
					
			
          EXEC SP_EXECUTESQL 
            @SQL, 
            N'@PERIOD_BASIS VARCHAR(100),@RATE_BASIS VARCHAR(100)', 
            @PERIOD_BASIS = @PERIOD_BASIS, 
            @RATE_BASIS=@RATE_BASIS 

--Deleting and Reinserting for the Current projection for both Rate Actual and Forecast Ends here

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


GO