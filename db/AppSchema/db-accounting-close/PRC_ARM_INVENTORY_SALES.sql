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
** 3    26/10/2017  GAL-12644       @AjayNaidu        PUlling exfactory latest price changes
** 4    19/12/2017  GAL-12886       @AjayNaidu        Units on hand c.r changes
** 5    28-12-2017  GAL-12267       @AjayNaidu        BP014 - [NOT] NULL option
** 6    29-12-2017  GAL-12271       @AjayNaidu        PE003 - SELECT INTO logic removed
** 7    04-01-2018  GAL-12268       @AjayNaidu        BP012 - CASE without ELSE
** 8    08-01-2018  GAL-12270       @AjayNaidu        EI025 PE001 PE010 ST008 MI005 MI002 Error codes
** 9    06-02-2018  GAL-12931       @AjayNaidu        Return Reserve Calculation Change
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
					@PRICE_MONTH                INT, 
                    @PRICE_YEAR                 INT, 
                    @SQL NVARCHAR(MAX) 
-- Variables Initialization for this no override block ends here 
----------------Creating all necessray temp tables(all ddl statements) for the logic Starts here (PE010 CodeGuarderror)
             IF OBJECT_ID('TEMPDB..#PERIOD') IS NOT NULL 
              DROP TABLE #PERIOD 

			   CREATE TABLE #PERIOD
            (
               PERIOD_SID    INT NOT NULL,
			   YEAR          INT NOT NULL,
			   QUARTER       INT NOT NULL,
			   MONTH         INT NOT NULL,
			   SEMI_ANNUAL   INT NOT NULL,
               PERIOD_DATE   DATETIME NOT NULL,
			   PERIODS        VARCHAR(50) NOT NULL
            )

			 IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_DETAILS') IS NOT NULL 
              DROP TABLE #TEMP_ARM_PROJ_DETAILS 

            CREATE TABLE #TEMP_ARM_PROJ_DETAILS 
              ( 
                 ARM_ADJUSTMENT_DETAILS_SID INT NOT NULL, 
                 PROJECTION_MASTER_SID      INT NOT NULL, 
                 CCP_DETAILS_SID            INT NOT NULL, 
                 RS_MODEL_SID               INT NOT NULL 
              ) 

			 IF OBJECT_ID('TEMPDB..#TEMP_ARM_PROJ_ITEM') IS NOT NULL 
              DROP TABLE #TEMP_ARM_PROJ_ITEM 

            CREATE TABLE #TEMP_ARM_PROJ_ITEM 
              ( 
                 PROJECTION_MASTER_SID INT NOT NULL, 
                 ITEM_MASTER_SID       INT NOT NULL, 
                 ITEM_ID               VARCHAR(50) NOT NULL
                 PRIMARY KEY ( ITEM_ID, ITEM_MASTER_SID, PROJECTION_MASTER_SID ) 
              ) 

             IF OBJECT_ID('TEMPDB..#DEMAND') IS NOT NULL 
              DROP TABLE #DEMAND 
			  CREATE TABLE #DEMAND
			  (
			  ITEM_MASTER_SID INT NOT NULL,
			  ACT_GROSS_UNITS NUMERIC(22,6) NULL,
			  ACT_GROSS_AMOUNT NUMERIC(22,6) NULL,
			  PERIOD_SID INT NOT NULL
			  )

			 IF OBJECT_ID('TEMPDB..#TEMP_PRICE') IS NOT NULL 
              DROP TABLE #TEMP_PRICE 

            CREATE TABLE #TEMP_PRICE 
              ( 
                 ITEM_MASTER_SID INT NOT NULL, 
                 PRICE           NUMERIC(22, 6) NULL
				 PRIMARY KEY(ITEM_MASTER_SID)
              ) 

			  IF OBJECT_ID('TEMPDB..#TEMP_INVENTORY') IS NOT NULL 
              DROP TABLE #TEMP_INVENTORY 

            CREATE TABLE #TEMP_INVENTORY 
              ( 
                 ITEM_MASTER_SID       INT NOT NULL, 
                 ITEM_ID               VARCHAR(50) NOT NULL, 
                 PROJECTION_MASTER_SID INT NOT NULL, 
                 COMP_CUST_MASTER_SID  INT NULL, 
                 UNITS_ON_HAND       NUMERIC(22, 6) NULL, 
                 INDICATOR             BIT NULL, 
                 COMPANY_GROUP_NAME    VARCHAR(50) NULL 
              ) 

			IF OBJECT_ID('TEMPDB..#RETURN_RESERVE_CURRENT') IS NOT NULL 
             DROP TABLE #RETURN_RESERVE_CURRENT

             CREATE TABLE #RETURN_RESERVE_CURRENT
             (
              ITEM_MASTER_SID INT NOT NULL,
              RETURN_RESERVE NUMERIC(22,6) NULL
              PRIMARY KEY(ITEM_MASTER_SID)
             )

			 IF OBJECT_ID('TEMPDB..#RETURN_RESERVE_HIST') IS NOT NULL 
             DROP TABLE #RETURN_RESERVE_HIST

             CREATE TABLE #RETURN_RESERVE_HIST
             (
              ITEM_MASTER_SID INT NOT NULL,
              RETURN_RESERVE NUMERIC(22,6) NULL
              PRIMARY KEY(ITEM_MASTER_SID)
             )

			  IF OBJECT_ID('TEMPDB..#RETURN_RESERVE') IS NOT NULL 
             DROP TABLE #RETURN_RESERVE

             CREATE TABLE #RETURN_RESERVE
             (
              ITEM_MASTER_SID INT NOT NULL,
              RETURN_RESERVE NUMERIC(22,6) NULL
              PRIMARY KEY(ITEM_MASTER_SID)
             )


--taking price related period selected in data selection and assigning GL Comp and BU(Business Unit) to variable starts here
             
			INSERT INTO #PERIOD(PERIOD_SID,YEAR,QUARTER,MONTH,SEMI_ANNUAL,PERIOD_DATE,PERIODS)
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
                             ELSE 'DEC'
                           END, ' ', YEAR) AS PERIOD 
            FROM   PERIOD 

            SELECT @RETURN_RESERVE_SID = PERIOD_SID 
            FROM   #PERIOD 
            WHERE  PERIODS = @RETURN_RESERVE 

            SELECT @INVENTORY_DETAILS_SID = PERIOD_SID 
            FROM   #PERIOD 
            WHERE  PERIODS = @INVENTORY_DETAILS 

            SELECT @PRICE_PERIOD_SID = PERIOD_SID,@PRICE_MONTH=MONTH,@PRICE_YEAR=YEAR
            FROM   #PERIOD 
            WHERE  PERIODS = @PRICE_PERIOD 

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
			  INSERT INTO #DEMAND(ITEM_MASTER_SID,ACT_GROSS_UNITS,ACT_GROSS_AMOUNT,PERIOD_SID)
            SELECT TAR.ITEM_MASTER_SID, 
                   DA.TOTAL_DEMAND_UNITS  ACT_GROSS_UNITS, 
                   DA.TOTAL_DEMAND_AMOUNT ACT_GROSS_AMOUNT, 
                   P.PERIOD_SID 
            FROM   DEMAND_ACTUAL DA 
                   JOIN #TEMP_ARM_PROJ_ITEM TAR 
                     ON TAR.ITEM_MASTER_SID = DA.ITEM_MASTER_SID 
                   JOIN PERIOD P 
                     ON P.MONTH = DA.FORECAST_MONTH 
                        AND P.[YEAR] = DA.FORECAST_YEAR 

--pulling amount from Demand Actual for Item + Period Combination Ends here

--Pulling Exfactory/ITEM Pricing  Price based on item  strats here(Note: Item and Price for current Ptojection stores in below temp table) starts here

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
                 SELECT A.ITEM_MASTER_SID,A.PRICE FROM (
                      SELECT B.ITEM_MASTER_SID, 
                             A.PRICE,
							 ROW_NUMBER() OVER(PARTITION BY  FORECAST_YEAR,FORECAST_MONTH,NDC ORDER BY CREATED_DATE DESC) AS RN
                      FROM   FORECASTING_MASTER A 
                             JOIN #TEMP_ARM_PROJ_ITEM B 
                               ON A.NDC = B.ITEM_ID 
                      WHERE  A.FORECAST_MONTH = @PRICE_MONTH 
                             AND A.FORECAST_YEAR = @PRICE_YEAR 
                             AND A.FORECAST_NAME = @FORECAST_NAME_EXFACTORY 
                             AND A.FORECAST_VER IN ( @FORECAST_VERSION_EXFACTORY, FLOOR(@FORECAST_VERSION_EXFACTORY) )
							  ) A WHERE A.RN=1
              END 
-- Price Period select in UI greater than getdate() pulling price from exfactory ends here 
--Pulling Exfactory/ITEM Pricing  Price based on item (insertion into #TEMP_PRICE also ends here)

--Return Reserve for each item selected on Data Selection Calculates here from Tr-8 Approved Starts here
INSERT INTO #RETURN_RESERVE_CURRENT 
            (ITEM_MASTER_SID, 
             RETURN_RESERVE) 
SELECT TAPI.ITEM_MASTER_SID, 
       ISNULL(SUM(AA.CREDIT), 0) - ISNULL(SUM(AA.DEBIT), 0) AS RETURN_RESERVE 
FROM   ARM_ADJUSTMENTS AA 
       JOIN ARM_ADJ_RES_CCP ARC 
         ON AA.ARM_ADJUSTMENT_DETAILS_SID = ARC.ARM_ADJUSTMENT_DETAILS_SID 
            AND ARC.REPORT_INDICATOR = 0 
            AND AA.ADJUSTMENT_TYPE = ARC.ADJUSTMENT_TYPE 
            AND AA.ACCOUNT = ARC.ACCOUNT 
       JOIN ARM_ADJ_RES_CONFIG_MASTER ARM 
         ON ARC.ARM_ADJ_RES_CONFIG_MASTER_SID = ARM.ARM_ADJ_RES_CONFIG_MASTER_SID 
            AND ARM.GL_COMPANY_MASTER_SID = @GL_COMP_COMPANY 
            AND ARM.BU_COMPANY_MASTER_SID = @BUISNESS_UNIT 
       JOIN ARM_ADJUSTMENT_CONFIG AAC 
         ON AAC.ARM_ADJUSTMENT_CONFIG_SID = ARC.ADJUSTMENT_TYPE 
       JOIN HELPER_TABLE HT_AC 
         ON AAC.METHODOLGY = HT_AC.HELPER_TABLE_SID 
            AND HT_AC.DESCRIPTION = 'TRANSACTION 8 - RETURN RESERVE' 
            AND HT_AC.LIST_NAME = 'ARM_TRX_METHDOLOGY' 
       JOIN ARM_ADJUSTMENT_DETAILS AD 
         ON AA.ARM_ADJUSTMENT_DETAILS_SID = AD.ARM_ADJUSTMENT_DETAILS_SID 
            AND AA.PERIOD_SID <= @RETURN_RESERVE_SID 
       JOIN CCP_DETAILS CD 
         ON AD.CCP_DETAILS_SID = CD.CCP_DETAILS_SID 
       JOIN #TEMP_ARM_PROJ_ITEM TAPI 
         ON TAPI.ITEM_MASTER_SID = CD.ITEM_MASTER_SID 
       JOIN WORKFLOW_MASTER WM 
         ON WM.PROJECTION_MASTER_SID = AD.PROJECTION_MASTER_SID 
       JOIN HELPER_TABLE HT_WM 
         ON WM.WORKFLOW_STATUS_ID = HT_WM.HELPER_TABLE_SID 
            AND HT_WM.DESCRIPTION = 'APPROVED' 
GROUP  BY TAPI.ITEM_MASTER_SID

INSERT INTO #RETURN_RESERVE_HIST 
            (ITEM_MASTER_SID, 
             RETURN_RESERVE)
SELECT TAPI.ITEM_MASTER_SID, 
       ISNULL(SUM(AH.CREDIT), 0) - ISNULL(SUM(AH.DEBIT), 0) AS RETURN_RESERVE 
FROM   ARM_HIST_ADJUSTMENTS AH 
       JOIN ARM_HIST_ADJ_RES_CON_MASTER AHM 
         ON AH.ARM_HIST_ADJ_RES_CON_MASTER_SID = AHM.ARM_HIST_ADJ_RES_CON_MASTER_SID 
            AND AHM.GL_COMPANY_MASTER_SID = @GL_COMP_COMPANY 
            AND AHM.BU_COMPANY_MASTER_SID = @BUISNESS_UNIT 
            AND AHM.CONFIGURATION_TYPE = 0 
            AND AH.CALCULATION_PERIOD_SID <= @RETURN_RESERVE_SID 
       JOIN HELPER_TABLE HT_AC 
         ON AH.METHODOLOGY = HT_AC.HELPER_TABLE_SID 
            AND HT_AC.DESCRIPTION = 'TRANSACTION 8 - RETURN RESERVE' 
            AND HT_AC.LIST_NAME = 'ARM_TRX_METHDOLOGY' 
       JOIN CCP_DETAILS CD 
         ON AH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID 
       JOIN #TEMP_ARM_PROJ_ITEM TAPI 
         ON TAPI.ITEM_MASTER_SID = CD.ITEM_MASTER_SID 
       JOIN ARM_HIST_ADJ_RES_CON_DETAILS AHD 
         ON AHD.ARM_HIST_ADJ_RES_CON_MASTER_SID = AH.ARM_HIST_ADJ_RES_CON_MASTER_SID 
            AND AHD.CCP_DETAILS_SID = AH.CCP_DETAILS_SID 
			AND AHD.RS_MODEL_SID=AH.RS_MODEL_SID
            AND AHD.ADJUSTMENT_TYPE = AH.ADJUSTMENT_TYPE 
            AND AH.ACCOUNT = AHD.ACCOUNT 
            AND AHD.METHODOLOGY = AH.METHODOLOGY 
            AND REPORT_INDICATOR = 0 
			AND AHD.CALCULATION_PERIOD_SID <= @RETURN_RESERVE_SID 
			AND AH.CALCULATION_PERIOD_SID=AHD.CALCULATION_PERIOD_SID		
GROUP  BY TAPI.ITEM_MASTER_SID 

INSERT INTO #RETURN_RESERVE 
            (ITEM_MASTER_SID, 
             RETURN_RESERVE) 
SELECT TAI.ITEM_MASTER_SID, 
       ISNULL(RR.RETURN_RESERVE, 0) 
       + ISNULL(RR_H.RETURN_RESERVE, 0) AS RETURN_RESERVE
FROM   #TEMP_ARM_PROJ_ITEM TAI 
       LEFT JOIN #RETURN_RESERVE_CURRENT RR 
              ON TAI.ITEM_MASTER_SID = RR.ITEM_MASTER_SID 
       LEFT JOIN #RETURN_RESERVE_HIST RR_H 
              ON TAI.ITEM_MASTER_SID = RR_H.ITEM_MASTER_SID 

--Return Reserve for each item selected on Data Selection Calculates here from Tr-8 Approved Ends here

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
                               UNITS_ON_HAND, 
                               INDICATOR) 
                  SELECT TAPI.ITEM_MASTER_SID, 
                         TAPI.ITEM_ID, 
                         TAPI.PROJECTION_MASTER_SID, 
                         ATID.COMPANY_MASTER_SID, 
                         SUM(IWAD.UNITS_ON_HAND) AS UNITS_ON_HAND,
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
                               UNITS_ON_HAND, 
                               INDICATOR, 
                               COMPANY_GROUP_NAME) 
                  SELECT TAPI.ITEM_MASTER_SID, 
                         TAPI.ITEM_ID, 
                         TAPI.PROJECTION_MASTER_SID, 
                         ATID.COMPANY_GROUP_SID,
                         SUM(IWAD.UNITS_ON_HAND) AS UNITS_ON_HAND, 
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
                   ISNULL(TI.UNITS_ON_HAND, 0)                    UNITS_ON_HAND, 
                   TOTAL_INVENTORY = ISNULL(SUM(CASE 
                                                  WHEN TI.INDICATOR = 1 THEN TI.UNITS_ON_HAND
                                                  ELSE -TI.UNITS_ON_HAND 
                                                END) 
                                              OVER ( 
                                                PARTITION BY TI.ITEM_MASTER_SID), 0), 
                   CASE 
                     WHEN COMPANY_GROUP_NAME = ''WHA INVENTORY'' THEN ( TI.UNITS_ON_HAND/D.ACT_GROSS_UNITS  )
                     ELSE 0 
                   END                                              WEEKS_ON_HAND, 
                   ( SUM(CASE 
                           WHEN TI.COMPANY_GROUP_NAME = ''KAISER & ESI'' THEN -TI.UNITS_ON_HAND
                           ELSE 
                             CASE 
                               WHEN TI.COMPANY_GROUP_NAME = ''DS INVENTORY'' THEN TI.UNITS_ON_HAND 
                             END 
                         END) 
                       OVER ( 
                         PARTITION BY TI.ITEM_MASTER_SID) ) / 45000 AS UNITS_PER_RETAIL, 
                   TP.PRICE, 
                   RR.RETURN_RESERVE                                        AS RETURN_RESERVE, 
                   NET_PIPELINE_VALUE = ISNULL(( PRICE * SUM(CASE 
                                                               WHEN TI.INDICATOR = 1 THEN TI.UNITS_ON_HAND
                                                               ELSE -TI.UNITS_ON_HAND 
                                                             END) 
                                                           OVER ( 
                                                             PARTITION BY TI.ITEM_MASTER_SID) ), 0) - RR.RETURN_RESERVE,
                D.ACT_GROSS_UNITS
            FROM   #TEMP_ARM_PROJ_ITEM TAPI 
                   INNER JOIN #TEMP_PRICE TP 
                           ON TAPI.ITEM_MASTER_SID = TP.ITEM_MASTER_SID 
                   INNER JOIN #TEMP_INVENTORY TI 
                           ON TI.PROJECTION_MASTER_SID = TAPI.PROJECTION_MASTER_SID 
                              AND TAPI.ITEM_MASTER_SID = TI.ITEM_MASTER_SID 
                   LEFT JOIN #RETURN_RESERVE RR
                           ON RR.ITEM_MASTER_SID = TI.ITEM_MASTER_SID
                   LEFT JOIN #DEMAND D 
                          ON D.ITEM_MASTER_SID = TAPI.ITEM_MASTER_SID 
                             AND D.PERIOD_SID = ',@FROM_PEIOD_SID )
                       EXEC sp_executesql @SQL
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
  GO