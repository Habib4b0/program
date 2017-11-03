IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_ARM_INVENTORY_SALES' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].[PRC_ARM_INVENTORY_SALES] 
  END 

GO 

CREATE PROCEDURE [dbo].[PRC_ARM_INVENTORY_SALES] (@PROJECTION_MASTER_SID INT, 
                                                 @INVENTORY_DETAILS     VARCHAR(100), 
                                                 @CUSTOMERS_SELECTION   VARCHAR(100), 
                                                 @RETURN_RESERVE        VARCHAR(100), 
                                                 @PRICE_PERIOD          VARCHAR(100), 
                                                 @OVERRIDE              BIT = 0, 
                                                 @USER_ID               INT, 
                                                 @SESSION_ID            INT) 
AS 

/**********************************************************************************************************
** File Name		:	PRC_ARM_INVENTORY_SALES.sql
** Procedure Name	:	PRC_ARM_INVENTORY_SALES
** Description		:	To generate Pipeline Inventory (Tr-3) Inventory tab
** Input Parameters	:	@PROJECTION_MASTER_SID  - Respective Projection ID Creted for Tr-3 as base
						@INVENTORY_DETAILS      - It indicates the Period for Pulling Inventory Units
						@CUSTOMERS_SELECTION    - It indicates the whther user selected Customer or Customer Group in Inventory Screen
						@RETURN_RESERVE         - It indicates the Period to pull the Return Reserve Values from Return Reserve Interface
						@PRICE_PERIOD			- It Indicates the Period to Pull the Price values
						@OVERRIDE               - It is used to find out user gave Price override in UI or not. if no override it will be zero,otherwise 1
				        @USER_ID                - it is the identification of the User Performing particular projection
						@SESSION_ID             - it is the identification of session for particular projection
											
** Output Parameters:	NA
** Author Name		:	@Paul,@Reddy
** Creation Date	:	09/11/2016 - MM/DD/YYYY
** Called By		:   Called by Application for Pipeline Inventory Transaction(as base),
                        if new Transaction created with Pipeline Inventory as base Transaction then application will call this procedure. 
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
-- Variables Initialization and assigning starts here
	  DECLARE @TR_INVENTORY_DETAILS_TABLE          VARCHAR(200) = CONCAT('ST_ARM_TR_INVENTORY_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', '')),
		            @INVENTORY_TABLE          VARCHAR(200) = CONCAT('ST_ARM_INVENTORY_', @USER_ID, '_', @SESSION_ID, '_', REPLACE(CONVERT(VARCHAR(50), GETDATE(), 2), '.', ''))
-- Variables Initialization and assigning Ends here

-- if user didn't give override for price below block will work
      IF ( @OVERRIDE = 0 ) 
        BEGIN 
-- Variables Initialization for this no override block starts here
            DECLARE @CURRENT_DATE_SID           INT, 
                    @RETURN_RESERVE_SID         INT, 
                    @INVENTORY_DETAILS_SID      INT, 
                    @PRICE_PERIOD_SID           INT, 
                    @FROM_PEIOD_SID             INT, 
                    @ITEM_UDT                   UDT_ITEM, 
                    @FORECAST_NAME_EXFACTORY    VARCHAR(50), 
                    @FORECAST_VERSION_EXFACTORY VARCHAR(50), 
                    @BUISNESS_UNIT              INT, 
                    @GL_COMP_COMPANY            INT,
					@SQL NVARCHAR(MAX) 
-- Variables Initialization for this no override block ends here 

--taking price related period selected in data selection and assigning GL Comp and BU(Business Unit) to variable starts here             
			IF OBJECT_ID('TEMPDB..#PERIOD') IS NOT NULL 
              DROP TABLE #PERIOD 

            SELECT PERIOD_SID,YEAR,QUARTER,MONTH,SEMI_ANNUAL,PERIOD_DATE,  
                   CONCAT (CASE 
                             WHEN MONTH = 1 THEN 'JAN' 
                             WHEN MONTH = 2 THEN 'FEB' 
                             WHEN MONTH = 3 THEN 'MAR' 
                             WHEN MONTH = 4 THEN 'APR' 
                             WHEN MONTH = 5 THEN 'MAY' 
                             WHEN MONTH = 6 THEN 'JUN' 
                             WHEN MONTH = 7 THEN 'JUL' 
                             WHEN MONTH = 8 THEN 'AUG' 
                             WHEN MONTH = 9 THEN 'SEP' 
                             WHEN MONTH = 10 THEN 'OCT' 
                             WHEN MONTH = 11 THEN 'NOV' 
                             WHEN MONTH = 12 THEN 'DEC' 
                           END, ' ', YEAR) AS PERIOD 
            INTO   #PERIOD 
            FROM   PERIOD 

            SELECT @RETURN_RESERVE_SID = PERIOD_SID 
            FROM   #PERIOD 
            WHERE  PERIOD = @RETURN_RESERVE 

            SELECT @INVENTORY_DETAILS_SID = PERIOD_SID 
            FROM   #PERIOD 
            WHERE  PERIOD = @INVENTORY_DETAILS 

            SELECT @PRICE_PERIOD_SID = PERIOD_SID 
            FROM   #PERIOD 
            WHERE  PERIOD = @PRICE_PERIOD 

            SELECT @FROM_PEIOD_SID = PERIOD_SID 
            FROM   #PERIOD P
			WHERE  EXISTS (SELECT CONVERT(DATETIME, DATEADD(MM, -1, DATEADD(DD, 1, EOMONTH(P1.FROM_DATE, 0))))
                                  FROM   PROJECTION_MASTER P1
                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
								  AND P.PERIOD_DATE=CONVERT(DATETIME, DATEADD(MM, -1, DATEADD(DD, 1, EOMONTH(FROM_DATE, 0))))) 
            --WHERE  PERIOD_DATE IN (SELECT CONVERT(DATETIME, DATEADD(MM, -1, DATEADD(DD, 1, EOMONTH(FROM_DATE, 0))))
            --                      FROM   PROJECTION_MASTER 
            --                      WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID) 

            SET @CURRENT_DATE_SID = (SELECT PERIOD_SID 
                                     FROM   PERIOD 
                                     WHERE  PERIOD_DATE = DATEADD(DD, 1, EOMONTH(GETDATE(), -1)))

            SELECT @BUISNESS_UNIT = BU_COMPANY_MASTER_SID 
            FROM   ARM_ADJUSTMENT_MASTER 
            WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

            SELECT @GL_COMP_COMPANY = COMPANY_MASTER_SID 
            FROM   PROJECTION_MASTER 
            WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

--taking price related period selected in data selection and assigning GL Comp Company and BU (Business Unit) to variable Ends here 

-- Pulling CCP+D Combination for Current projection Starts here  

            IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_DETAILS') IS NOT NULL 
              DROP TABLE #TEMP_ARM_PROJ_DETAILS 

            CREATE TABLE #TEMP_ARM_PROJ_DETAILS 
              ( 
                 ARM_ADJUSTMENT_DETAILS_SID INT, 
                 PROJECTION_MASTER_SID      INT, 
                 CCP_DETAILS_SID            INT, 
                 RS_MODEL_SID               INT 
              ) 

            INSERT INTO #TEMP_ARM_PROJ_DETAILS 
                        (ARM_ADJUSTMENT_DETAILS_SID, 
                         PROJECTION_MASTER_SID, 
                         CCP_DETAILS_SID, 
                         RS_MODEL_SID) 
            SELECT ARM_ADJUSTMENT_DETAILS_SID, 
                   PROJECTION_MASTER_SID, 
                   CCP_DETAILS_SID, 
                   RS_MODEL_SID 
            FROM   ARM_ADJUSTMENT_DETAILS 
            WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID 

-- Pulling CCP+D Combination for Current projection Ends here

--Pulling product related information for current projection starts here
            IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_ITEM') IS NOT NULL 
              DROP TABLE #TEMP_ARM_PROJ_ITEM 

            CREATE TABLE #TEMP_ARM_PROJ_ITEM 
              ( 
                 PROJECTION_MASTER_SID INT, 
                 ITEM_MASTER_SID       INT, 
                 ITEM_ID               VARCHAR(50) 
                 PRIMARY KEY ( ITEM_ID, ITEM_MASTER_SID, PROJECTION_MASTER_SID ) 
              ) 

            INSERT INTO #TEMP_ARM_PROJ_ITEM 
                        (ITEM_ID, 
                         ITEM_MASTER_SID, 
                         PROJECTION_MASTER_SID) 
            SELECT DISTINCT IM.ITEM_ID, 
                            B.ITEM_MASTER_SID, 
                            @PROJECTION_MASTER_SID 
            FROM   #TEMP_ARM_PROJ_DETAILS A 
                   JOIN CCP_DETAILS B 
                     ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID 
                   JOIN ITEM_MASTER IM 
                     ON IM.ITEM_MASTER_SID = B.ITEM_MASTER_SID 
--Pulling product related information for current projection ends here

--pulling amount from Demand Actual for Item + Period Combination Starts here

            IF OBJECT_ID('TEMPDB..#DEMAND') IS NOT NULL 
              DROP TABLE #DEMAND 

            SELECT TAR.ITEM_MASTER_SID, 
                   DA.TOTAL_DEMAND_UNITS  ACT_GROSS_UNITS, 
                   DA.TOTAL_DEMAND_AMOUNT ACT_GROSS_AMOUNT, 
                   P.PERIOD_SID 
            INTO   #DEMAND 
            FROM   DEMAND_ACTUAL DA 
                   JOIN #TEMP_ARM_PROJ_ITEM TAR 
                     ON TAR.ITEM_MASTER_SID = DA.ITEM_MASTER_SID 
                   JOIN PERIOD P 
                     ON P.MONTH = DA.FORECAST_MONTH 
                        AND P.[YEAR] = DA.FORECAST_YEAR 

--pulling amount from Demand Actual for Item + Period Combination Ends here

--Pulling Exfactory/ITEM Pricing  Price based on item  strats here(Note: Item and Price for current Ptojection stores in below temp table) starts here
            IF OBJECT_ID('TEMPDB..#TEMP_PRICE') IS NOT NULL 
              DROP TABLE #TEMP_PRICE 

            CREATE TABLE #TEMP_PRICE 
              ( 
                 ITEM_MASTER_SID INT PRIMARY KEY, 
                 PRICE           NUMERIC(22, 6) 
              ) 

            INSERT INTO @ITEM_UDT 
                        (ITEM_MASTER_SID) 
            SELECT ITEM_MASTER_SID 
            FROM   #TEMP_ARM_PROJ_ITEM 

--Price Period select in UI less than getdate() we need to take price from ITEM Pricing Starts here

            IF ( @PRICE_PERIOD_SID < @CURRENT_DATE_SID ) 
              BEGIN 
                  INSERT INTO #TEMP_PRICE 
                              (ITEM_MASTER_SID, 
                               PRICE) 
                  SELECT ITEM_MASTER_SID, 
                         ITEM_PRICE 
                  FROM   [DBO].[UDF_ITEM_PRICING](@ITEM_UDT, 'WAC', @PRICE_PERIOD_SID, @PRICE_PERIOD_SID, 'EACH') 
                  WHERE  PERIOD_SID = @PRICE_PERIOD_SID 
              END 
 -- Price Period select in UI less than getdate() we need to take price from ITEM Pricing Ends here
            ELSE 
-- Price Period select in UI greater than getdate() pulling price from exfactory starts here 
              BEGIN 
                  SELECT @FORECAST_NAME_EXFACTORY = FORECAST_NAME, 
                         @FORECAST_VERSION_EXFACTORY = VERSION 
                  FROM   (SELECT FT.FORECAST_NAME, 
                                 FT.[VERSION], 
                                 FT.FILE_MANAGEMENT_SID, 
                                 HT.[DESCRIPTION]                        AS FILE_TYPE, 
                                 ROW_NUMBER() 
                                   OVER ( 
                                     PARTITION BY FT.FILE_TYPE 
                                     ORDER BY FT.FILE_MANAGEMENT_SID DESC ) AS RN 
                          FROM   FILE_MANAGEMENT FT 
                                 INNER JOIN HELPER_TABLE HT 
                                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE 
                          WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE()) 
                                   AND FT.FROM_PERIOD IS NOT NULL ) 
                                 AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE())
                                        OR FT.TO_PERIOD IS NULL ) 
                                 AND HT.LIST_NAME = 'FILE_TYPE' 
                                 AND HT.[DESCRIPTION] IN ( 'EX-FACTORY SALES' ) 
                                 AND FT.BUSINESS_UNIT = @BUISNESS_UNIT 
                                 AND FT.COMPANY = @GL_COMP_COMPANY) A 
                  WHERE  RN = 1 

                  INSERT INTO #TEMP_PRICE 
                              (ITEM_MASTER_SID, 
                               PRICE) 
                  SELECT B.ITEM_MASTER_SID, 
                         A.FORECAST_PRICE 
                  FROM   #TEMP_ARM_PROJ_ITEM B 
                         INNER JOIN [DBO].[UDF_GTS_WAC](@ITEM_UDT, @PRICE_PERIOD_SID, @PRICE_PERIOD_SID, @FORECAST_NAME_EXFACTORY, COALESCE(@FORECAST_VERSION_EXFACTORY, FLOOR(@FORECAST_VERSION_EXFACTORY))) A
                                 ON A.ITEM_MASTER_SID = B.ITEM_MASTER_SID 
                  WHERE  A.PERIOD_SID = @PRICE_PERIOD_SID 
              END 
-- Price Period select in UI greater than getdate() pulling price from exfactory ends here 
--Pulling Exfactory/ITEM Pricing  Price based on item (insertion into #TEMP_PRICE also ends here)

            IF OBJECT_ID('TEMPDB..#TEMP_INVENTORY') IS NOT NULL 
              DROP TABLE #TEMP_INVENTORY 

            CREATE TABLE #TEMP_INVENTORY 
              ( 
                 ITEM_MASTER_SID       INT, 
                 ITEM_ID               VARCHAR(50), 
                 PROJECTION_MASTER_SID INT, 
                 COMP_CUST_MASTER_SID  INT, 
                 UNITS_WITHDRAWN       NUMERIC(22, 6), 
                 INDICATOR             BIT, 
                 COMPANY_GROUP_NAME    VARCHAR(50) 
              ) 

/*if User Selection in Data Selection is Customer below block will give Inventory Units for Particular 
  Period Selected in Inventory DDLB Starts here */

            SET @SQL=''
            IF ( @CUSTOMERS_SELECTION = 'CUSTOMER' ) 
              BEGIN 
                  SET @SQL=CONCAT('INSERT INTO #TEMP_INVENTORY 
                              (ITEM_MASTER_SID, 
                               ITEM_ID, 
                               PROJECTION_MASTER_SID, 
                               COMP_CUST_MASTER_SID, 
                               UNITS_WITHDRAWN, 
                               INDICATOR) 
                  SELECT TAPI.ITEM_MASTER_SID, 
                         TAPI.ITEM_ID, 
                         TAPI.PROJECTION_MASTER_SID, 
                         ATID.COMPANY_MASTER_SID, 
                         SUM(IWAD.UNITS_WITHDRAWN) AS UNITS_WITHDRAWN,
                         ATID.INDICATOR 
                  FROM   #TEMP_ARM_PROJ_ITEM TAPI 
                         INNER JOIN ',@TR_INVENTORY_DETAILS_TABLE,' ATID 
                                 ON TAPI.PROJECTION_MASTER_SID = ATID.PROJECTION_MASTER_SID 
                                    AND ATID.CHECK_RECORD = 1 
                         LEFT JOIN INVENTORY_WD_ACTUAL_DT IWAD 
                                ON ATID.COMPANY_MASTER_SID = IWAD.COMPANY_MASTER_SID 
                                   AND TAPI.ITEM_MASTER_SID = IWAD.ITEM_MASTER_SID 
                                   AND @INVENTORY_DETAILS_SID = (SELECT PERIOD_SID 
                                                                 FROM   #PERIOD 
                                                                 WHERE  PERIOD_DATE = DATEFROMPARTS(IWAD.YEAR, IWAD.MONTH, 01))
				  GROUP BY TAPI.ITEM_MASTER_SID, 
                           TAPI.ITEM_ID, 
                           TAPI.PROJECTION_MASTER_SID, 
                           ATID.COMPANY_MASTER_SID,
						   ATID.INDICATOR')
                  EXEC sp_executesql @SQL,N'@INVENTORY_DETAILS_SID INT',@INVENTORY_DETAILS_SID=@INVENTORY_DETAILS_SID
/*if User Selection in Data Selection is Customer this block will give Inventory Units for Particular 
  Period Selected in Inventory DDLB Ends here */
              END 
            ELSE 
/*if User Selection in Data Selection is Customer Group below block will give Inventory Units for Particular 
  Period Selected in Inventory DDLB Starts here */
              BEGIN 
                  SET @SQL=CONCAT('INSERT INTO #TEMP_INVENTORY 
                              (ITEM_MASTER_SID, 
                               ITEM_ID, 
                               PROJECTION_MASTER_SID, 
                               COMP_CUST_MASTER_SID, 
                               UNITS_WITHDRAWN, 
                               INDICATOR, 
                               COMPANY_GROUP_NAME) 
                  SELECT TAPI.ITEM_MASTER_SID, 
                         TAPI.ITEM_ID, 
                         TAPI.PROJECTION_MASTER_SID, 
                         ATID.COMPANY_GROUP_SID,
                         SUM(IWAD.UNITS_WITHDRAWN)UNITS_WITHDRAWN, 
                         ATID.INDICATOR, 
                         CG.COMPANY_GROUP_NAME 
                  FROM   #TEMP_ARM_PROJ_ITEM TAPI 
                         INNER JOIN ',@TR_INVENTORY_DETAILS_TABLE,' ATID 
                                 ON TAPI.PROJECTION_MASTER_SID = ATID.PROJECTION_MASTER_SID 
                         LEFT JOIN COMPANY_GROUP_DETAILS CGD 
                                ON CGD.COMPANY_GROUP_SID = ATID.COMPANY_GROUP_SID 
                         LEFT JOIN INVENTORY_WD_ACTUAL_DT IWAD 
                                ON COALESCE(CGD.COMPANY_MASTER_SID, ATID.COMPANY_MASTER_SID) = IWAD.COMPANY_MASTER_SID
                                   AND TAPI.ITEM_MASTER_SID = IWAD.ITEM_MASTER_SID 
                                   AND @INVENTORY_DETAILS_SID = (SELECT PERIOD_SID 
                                                                 FROM   #PERIOD 
                                                                 WHERE  PERIOD_DATE = DATEFROMPARTS(IWAD.YEAR, IWAD.MONTH, 01))
                         INNER JOIN COMPANY_GROUP CG 
                                 ON CG.COMPANY_GROUP_SID = ATID.COMPANY_GROUP_SID 
                  GROUP  BY TAPI.ITEM_MASTER_SID, 
                            TAPI.ITEM_ID, 
                            TAPI.PROJECTION_MASTER_SID, 
                            ATID.COMPANY_GROUP_SID, 
                            ATID.INDICATOR, 
                            CG.COMPANY_GROUP_NAME' )						
				EXEC sp_executesql @SQL,N'@INVENTORY_DETAILS_SID INT',@INVENTORY_DETAILS_SID=@INVENTORY_DETAILS_SID
              END 
/*if User Selection in Data Selection is Customer Group below block will give Inventory Units for Particular 
  Period Selected in Inventory DDLB Ends here */

  --Inserting into Inventory (main table) Starts here for Customer + Item + Period Combination Starts here

			SET @SQL=''
			SET @SQL=CONCAT('IF EXISTS (SELECT 1 FROM ', @INVENTORY_TABLE, ' WHERE PROJECTION_MASTER_SID = ', @PROJECTION_MASTER_SID, ')  
					                   BEGIN 
												TRUNCATE TABLE ', @INVENTORY_TABLE, '  
									   END
            INSERT INTO ',@INVENTORY_TABLE, 
                        '(PROJECTION_MASTER_SID, 
                         ITEM_MASTER_SID, 
                         COMP_CUST_MASTER_SID, 
                         PERIOD_SID, 
                         INVENTORY_UNITS, 
                         TOTAL_INVENTORY, 
                         WEEKS_ON_HAND, 
                         UNITS_PER_RETAIL, 
                         PRICE, 
                         RETURN_RESERVE, 
                         NET_PIPELINE_VALUE,
						 DEMAND_UNITS) 
            SELECT TAPI.PROJECTION_MASTER_SID, 
                   TP.ITEM_MASTER_SID, 
                   TI.COMP_CUST_MASTER_SID, '
                   ,@FROM_PEIOD_SID,' AS PERIOD_SID, 
                   ISNULL(TI.UNITS_WITHDRAWN, 0)                    UNITS_WITHDRAWN, 
                   TOTAL_INVENTORY = ISNULL(SUM(CASE 
                                                  WHEN TI.INDICATOR = 1 THEN TI.UNITS_WITHDRAWN
                                                  ELSE -TI.UNITS_WITHDRAWN 
                                                END) 
                                              OVER ( 
                                                PARTITION BY TI.ITEM_MASTER_SID), 0), 
                   CASE 
                     WHEN COMPANY_GROUP_NAME = ''WHA INVENTORY'' THEN ( TI.UNITS_WITHDRAWN/D.ACT_GROSS_UNITS  )
                     ELSE 0 
                   END                                              WEEKS_ON_HAND, 
                   ( SUM(CASE 
                           WHEN TI.COMPANY_GROUP_NAME = ''KAISER & ESI'' THEN -TI.UNITS_WITHDRAWN
                           ELSE 
                             CASE 
                               WHEN TI.COMPANY_GROUP_NAME = ''DS INVENTORY'' THEN TI.UNITS_WITHDRAWN 
                             END 
                         END) 
                       OVER ( 
                         PARTITION BY TI.ITEM_MASTER_SID) ) / 45000 AS UNITS_PER_RETAIL, 
                   TP.PRICE, 
                   RR.AMOUNT                                        AS RETURN_RESERVE, 
                   NET_PIPELINE_VALUE = ISNULL(( PRICE * SUM(CASE 
                                                               WHEN TI.INDICATOR = 1 THEN TI.UNITS_WITHDRAWN
                                                               ELSE -TI.UNITS_WITHDRAWN 
                                                             END) 
                                                           OVER ( 
                                                             PARTITION BY TI.ITEM_MASTER_SID) ), 0) - RR.AMOUNT,
                D.ACT_GROSS_UNITS
            FROM   #TEMP_ARM_PROJ_ITEM TAPI 
                   INNER JOIN #TEMP_PRICE TP 
                           ON TAPI.ITEM_MASTER_SID = TP.ITEM_MASTER_SID 
                   INNER JOIN #TEMP_INVENTORY TI 
                           ON TI.PROJECTION_MASTER_SID = TAPI.PROJECTION_MASTER_SID 
                              AND TAPI.ITEM_MASTER_SID = TI.ITEM_MASTER_SID 
                   INNER JOIN (SELECT ISNULL(RR.AMOUNT, 0) AMOUNT, 
                                      TAPI.ITEM_MASTER_SID, 
                                      TAPI.ITEM_ID 
                               FROM   #TEMP_ARM_PROJ_ITEM TAPI 
                                      LEFT JOIN RETURN_RESERVE RR 
                                             ON TAPI.ITEM_MASTER_SID = RR.ITEM_MASTER_SID 
                                                AND @RETURN_RESERVE_SID = (SELECT PERIOD_SID 
                                                                           FROM   #PERIOD 
                                                                           WHERE  PERIOD_DATE = DATEFROMPARTS(RR.YEAR, RR.MONTH, 01))) RR
                           ON RR.ITEM_MASTER_SID = TI.ITEM_MASTER_SID 
                   LEFT JOIN #DEMAND D 
                          ON D.ITEM_MASTER_SID = TAPI.ITEM_MASTER_SID 
                             AND D.PERIOD_SID = ',@FROM_PEIOD_SID )
			EXEC sp_executesql @SQL,N'@RETURN_RESERVE_SID INT',@RETURN_RESERVE_SID=@RETURN_RESERVE_SID
--Inserting into Inventory (main table) Starts here for Customer + Item + Period Combination Ends here
        END 
      ELSE 
--updating Tr-3 main table(Inventory) based on override calculation Starts here
        BEGIN 
		    SET @SQL=''
            SET @SQL=CONCAT('UPDATE ',@INVENTORY_TABLE,'  
            SET    NET_PIPELINE_VALUE = ISNULL(( TOTAL_INVENTORY * ISNULL(PRICE_OVERRIDE, 0) ), 0) - RETURN_RESERVE
            WHERE  PROJECTION_MASTER_SID = ',@PROJECTION_MASTER_SID ,' AND PRICE_OVERRIDE IS NOT NULL ')
			EXEC sp_executesql @SQL
--updating Tr-3 main table(Inventory) based on override calculation Ends here
        END 
  END 