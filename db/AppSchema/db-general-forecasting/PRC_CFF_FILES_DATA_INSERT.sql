IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_CFF_FILES_DATA_INSERT' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].[PRC_CFF_FILES_DATA_INSERT] 
  END 

GO 

CREATE PROCEDURE [DBO].[PRC_CFF_FILES_DATA_INSERT] (@CFF_MASTER_SID INT) 
AS 
  /**********************************************************************************************************
  ** FILE NAME    :  PRC_CFF_FILES_DATA_INSERT.SQL 
  ** PROCEDURE NAME  :  PRC_CFF_FILES_DATA_INSERT 
  ** DESCRIPTION    :  TO GENERATE  ACTUALS AND  FORECASTING VALUES OF BOTH CUSTOMER+PRODUCT AND PRODUCT FILES INFORMATION 
  ** INPUT PARAMETERS  :  @CFF_MASTER_SID 
                * @CFF_MASTER_SID - PASSING INPUT AS CFF_MASTER_SID.  
                       
  ** OUTPUT PARAMETERS:  NA 
  ** AUTHOR NAME    :   @LEELA VENKATESH 
  ** CREATION DATE  :  25/03/2016 - MM/DD/YYYY 
  ** CALLED BY    :  APPLICATION SIDE TO HOW VALUES ON SCREEN 
  **********************************************************************************************************/
  BEGIN 
      SET NOCOUNT ON 

      BEGIN TRY 
           -------------------------------GALUAT_29 CHANGES---------------------------- 
          ------PULLING ACTUAL START_DATE , ACTUAL_END_DATE,ROJ_START_PERIOD_DATE,PROJ_END_PERIOD_DATE BASED ON DESCRIPTION FROM HELPER_TABLE ----------- 
          DECLARE @ACTUAL_START_DATE    DATETIME = CAST(YEAR(GETDATE()) - 3 AS VARCHAR(4)) 
                    + '-01-01', 
                  @PROJ_END_PERIOD_DATE DATETIME 

          SELECT TOP 1 @PROJ_END_PERIOD_DATE = DATEADD(DD, 1, EOMONTH(TO_DATE, -1)) 
          FROM   FORECAST_CONFIG FA 
                 LEFT JOIN HELPER_TABLE HT 
                        ON HT.HELPER_TABLE_SID = FA.BUSINESS_PROCESS_TYPE 
          WHERE  [DESCRIPTION] = 'CONSOLIDATED FINANCIAL FORECAST' 
          ORDER  BY VERSION_NO DESC 

          DECLARE @ACT_START_PERIOD_SID INT = (SELECT PERIOD_SID 
             FROM   PERIOD 
             WHERE  PERIOD_DATE = @ACTUAL_START_DATE) 
          DECLARE @PROJ_END_PERIOD_SID INT = (SELECT PERIOD_SID 
             FROM   PERIOD 
             WHERE  PERIOD_DATE = @PROJ_END_PERIOD_DATE) 
          DECLARE @FORECAST_NAME_EX    VARCHAR(50), 
                  @FORECAST_VERSION_EX VARCHAR(15),
				  @FORECAST_NAME_IVD   VARCHAR(50),
				  @FORECAST_VERSION_IVD VARCHAR(15),
				  @FORECAST_NAME_DEM VARCHAR(50),
                  @FORECAST_VERSION_DEM VARCHAR(15) ,
				  @FORECAST_NAME_AD VARCHAR(50) ,
                  @FORECAST_VERSION_AD  VARCHAR(15),
				  @FORECAST_NAME_IW VARCHAR(50),
                  @FORECAST_VERSION_IW VARCHAR(15),
				  @FORECAST_NAME_CS VARCHAR(50), 
                  @FORECAST_VERSION_CS VARCHAR(15)

          DECLARE @ITEMID [DBO].[UDT_ITEM] 

          --------------------------------------------PULLING ITEMS ATTACHED  TO CFF------------- 
          INSERT INTO @ITEMID 
          SELECT DISTINCT ITEM_MASTER_SID 
          FROM   CCP_DETAILS CCP 
          WHERE  EXISTS (SELECT 1 
                         FROM   CFF_DETAILS CD 
                                JOIN PROJECTION_DETAILS PD 
                                  ON CD.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID 
                                     AND CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID 
                         WHERE  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID 
                                AND CFF_MASTER_SID = @CFF_MASTER_SID) 

          ---------------------EXFACTORY--------------- 
      /* SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME, 
                       @FORECAST_VERSION = FT.[VERSION] 
          FROM   FILE_MANAGEMENT FT 
                 INNER JOIN HELPER_TABLE HT 
                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE 
          WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE()) 
                   AND FT.FROM_PERIOD IS NOT NULL ) 
                 AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE()) 
                        OR FT.TO_PERIOD IS NULL ) 
                 AND HT.LIST_NAME = 'FILE_TYPE' 
                 AND HT.[DESCRIPTION] IN ( 'EX-FACTORY SALES' ) 
          ORDER  BY FT.FROM_PERIOD DESC*/ 
          ---------------------CHANGED ONE--------------- 
------------------------------------------------------Pulling files information starts here-----------------------------------
		 DECLARE @FILE_VER AS TABLE
            (
               FILE_TYPE     VARCHAR(100) NOT NULL,
               FORECAST_NAME VARCHAR(100) NULL,
               VERSION       VARCHAR(15) NULL,
			   PRIMARY KEY(FILE_TYPE)
            )

          INSERT INTO @FILE_VER
                      (FILE_TYPE,
                       FORECAST_NAME,
                       VERSION)

			SELECT HT.[DESCRIPTION]  AS FILE_TYPE,
			       FT.FILE_NAME,
				   FT.[VERSION] 
			 FROM   CFF_FILE_SELECTION FT 
                 INNER JOIN HELPER_TABLE HT 
                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE 
          WHERE  CFF_MASTER_SID = @CFF_MASTER_SID 
                 AND HT.[DESCRIPTION] IN ( 'EX-FACTORY SALES' , 'INVENTORY WITHDRAWAL - FORECAST SUMMARY','DEMAND','ADJUSTED DEMAND','INVENTORY WITHDRAWAL - FORECAST DETAIL','CUSTOMER SALES'  ) 
          --------------------------------------------PULLING ACTUALS AND PROJECTION VALES FROM EX-FACTORY SALES FILE------------- 

          SELECT @FORECAST_NAME_EX = FORECAST_NAME,
                 @FORECAST_VERSION_EX = [VERSION]
          FROM  @FILE_VER
                 WHERE  FILE_TYPE = 'EX-FACTORY SALES'

          IF OBJECT_ID('TEMPDB..#GTS_DETAILS') IS NOT NULL 
            DROP TABLE #GTS_DETAILS 

          CREATE TABLE #GTS_DETAILS 
            ( 
               ITEMID              VARCHAR(50), 
               ITEM_MASTER_SID     INT, 
               GTS_SALES_PROJECTED NUMERIC(22, 6), 
               GTS_SALES_ACTUALS   NUMERIC(22, 6), 
               UNITS               NUMERIC(22, 6), 
               MONTHLY             INT, 
               QUARTERLY           INT, 
               YEARLY              INT, 
               PERIOD_SID          INT 
            ) 

          INSERT INTO #GTS_DETAILS 
                      (ITEM_MASTER_SID, 
                       GTS_SALES_PROJECTED, 
                       GTS_SALES_ACTUALS, 
                       UNITS, 
                       MONTHLY, 
                       QUARTERLY, 
                       YEARLY, 
                       PERIOD_SID) 
          SELECT ITEM_MASTER_SID, 
                 COALESCE(A.FORECAST_GTS_SALES, A.ACTUAL_GTS_SALES) AS FORECAST_GTS_SALES, 
                 A.ACTUAL_GTS_SALES, 
                 COALESCE(A.FORECAST_GTS_UNITS, A.ACTUAL_GTS_UNITS) AS UNITS, 
                 P.MONTH, 
                 P.QUARTER, 
                 P.YEAR, 
                 P.PERIOD_SID 
          FROM   UDF_GTS_WAC(@ITEMID, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, @FORECAST_NAME_EX, @FORECAST_VERSION_EX) A
                 INNER JOIN PERIOD P 
                         ON P.PERIOD_SID = A.PERIOD_SID 

          --------------------------------------------PULLING ACTUALS AND PROJECTION VALES FROM INVENTORY WITHDRAWAL - FORECAST SUMMARY  FILE------------- 
          ---------------------------INVENTORY---------------- 
      /* SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME, 
                       @FORECAST_VERSION = FT.[VERSION] 
          FROM   FILE_MANAGEMENT FT 
                 INNER JOIN HELPER_TABLE HT 
                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE 
          WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE()) 
                   AND FT.FROM_PERIOD IS NOT NULL ) 
                 AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE()) 
                        OR FT.TO_PERIOD IS NULL ) 
                 AND HT.LIST_NAME = 'FILE_TYPE' 
                 AND HT.[DESCRIPTION] IN ( 'INVENTORY WITHDRAWAL - FORECAST SUMMARY' ) 
          ORDER  BY FT.FROM_PERIOD DESC 
       */ 
          ---------------------CHANGED ONE--------------- 
		  SELECT @FORECAST_NAME_IVD = FORECAST_NAME,
                 @FORECAST_VERSION_IVD = [VERSION]
          FROM  @FILE_VER
                 WHERE  FILE_TYPE = 'INVENTORY WITHDRAWAL - FORECAST SUMMARY'

          IF OBJECT_ID('TEMPDB..#INVENTORY') IS NOT NULL 
            DROP TABLE #INVENTORY 

          CREATE TABLE #INVENTORY 
            ( 
               ITEM_MASTER_SID      INT, 
               PERIOD_SID           INT NOT NULL, 
               ACT_AMOUNT_WITHDRAWN NUMERIC(22, 6), 
               ACT_UNITS_WITHDRAWN  NUMERIC(22, 6), 
               FOR_AMOUNT_WITHDRAWN NUMERIC(22, 6), 
               FOR_UNITS_WITHDRAWN  NUMERIC(22, 6), 
               YEARLY               INT, 
               MONTHLY              INT, 
               QUARTERLY            INT, 
               SEMI_ANNUAL          INT 
            ) 

          INSERT INTO #INVENTORY 
                      (ITEM_MASTER_SID, 
                       PERIOD_SID, 
                       ACT_AMOUNT_WITHDRAWN, 
                       ACT_UNITS_WITHDRAWN, 
                       FOR_AMOUNT_WITHDRAWN, 
                       FOR_UNITS_WITHDRAWN, 
                       YEARLY, 
                       MONTHLY, 
                       QUARTERLY, 
                       SEMI_ANNUAL) 
          SELECT I.ITEM_MASTER_SID, 
                 P.PERIOD_SID, 
                 ACT_AMOUNT_WITHDRAWN, 
                 ACT_UNITS_WITHDRAWN, 
                 COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN) AS FOR_AMOUNT_WITHDRAWN,
                 COALESCE(FOR_UNITS_WITHDRAWN, ACT_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN, 
                 P.YEAR, 
                 P.MONTH, 
                 P.QUARTER, 
                 SEMI_ANNUAL 
          FROM   [DBO].[UDF_INVENTORY_WAC](@ITEMID, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, @FORECAST_NAME_IVD, @FORECAST_VERSION_IVD) I 
                 INNER JOIN PERIOD P 
                         ON P.PERIOD_SID = I.PERIOD_SID 

          --------------------------------------------PULLING ACTUALS AND PROJECTION VALES FROM DEMAND  FILE------------- 
          ------------------DEMAND-------------- 
      /*   SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME, 
                       @FORECAST_VERSION = FT.[VERSION] 
          FROM   FILE_MANAGEMENT FT 
                 INNER JOIN HELPER_TABLE HT 
                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE 
          WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE()) 
                   AND FT.FROM_PERIOD IS NOT NULL ) 
                 AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE()) 
                        OR FT.TO_PERIOD IS NULL ) 
                 AND HT.LIST_NAME = 'FILE_TYPE' 
                 AND HT.[DESCRIPTION] IN ( 'DEMAND' ) 
          ORDER  BY FT.FROM_PERIOD DESC*/ 
          ---------------------CHANGED ONE--------------- 
		   SELECT @FORECAST_NAME_DEM = FORECAST_NAME,
                 @FORECAST_VERSION_DEM = [VERSION]
          FROM  @FILE_VER
                WHERE  FILE_TYPE = 'DEMAND'

          IF OBJECT_ID('TEMPDB.DBO.#DEMAND', 'U') IS NOT NULL 
            DROP TABLE #DEMAND 

          CREATE TABLE #DEMAND 
            ( 
               ITEM_MASTER_SID  INT, 
               PERIOD_SID       INT NOT NULL, 
               ACT_GROSS_AMOUNT NUMERIC(22, 6), 
               ACT_GROSS_UNITS  NUMERIC(22, 6), 
               FOR_GROSS_AMOUNT NUMERIC(22, 6), 
               FOR_GROSS_UNITS  NUMERIC(22, 6), 
               YEARLY           INT, 
               MONTHLY          INT, 
               QUARTERLY        INT, 
               SEMI_ANNUAL      INT 
            ) 

          INSERT INTO #DEMAND 
                      (ITEM_MASTER_SID, 
                       ACT_GROSS_AMOUNT, 
                       ACT_GROSS_UNITS, 
                       FOR_GROSS_AMOUNT, 
                       FOR_GROSS_UNITS, 
                       MONTHLY, 
                       YEARLY, 
                       PERIOD_SID) 
          SELECT I.ITEM_MASTER_SID, 
                 ACT_GROSS_AMOUNT, 
                 ACT_GROSS_UNITS, 
                 COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT) AS FOR_GROSS_AMOUNT, 
                 COALESCE(FOR_GROSS_UNITS, ACT_GROSS_UNITS) AS FOR_GROSS_UNITS, 
                 P.MONTH, 
                 P.YEAR, 
                 P.PERIOD_SID 
          FROM   [DBO].[UDF_DEMAND_WAC](@ITEMID, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, @FORECAST_NAME_DEM, @FORECAST_VERSION_DEM) I 
                 INNER JOIN PERIOD P 
                         ON P.PERIOD_SID = I.PERIOD_SID 

      /*  SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME, 
                       @FORECAST_VERSION = FT.[VERSION] 
          FROM   FILE_MANAGEMENT FT 
                 INNER JOIN HELPER_TABLE HT 
                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE 
          WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE()) 
                   AND FT.FROM_PERIOD IS NOT NULL ) 
                 AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE()) 
                        OR FT.TO_PERIOD IS NULL ) 
                 AND HT.LIST_NAME = 'FILE_TYPE' 
                 AND HT.[DESCRIPTION] IN ( 'ADJUSTED DEMAND' ) 
          ORDER  BY FT.FROM_PERIOD DESC*/ 
          ---------------------CHANGED ONE--------------- 
          --------------------------------------------PULLING ACTUALS AND PROJECTION VALES FROM ADJUSTED DEMAND  FILE------------- 
          SELECT @FORECAST_NAME_AD = FORECAST_NAME, 
                 @FORECAST_VERSION_AD = [VERSION]
          FROM  @FILE_VER
                WHERE  FILE_TYPE = 'ADJUSTED DEMAND'
               

          IF OBJECT_ID('TEMPDB.DBO.#ADJUSTED_DEMAND', 'U') IS NOT NULL 
            DROP TABLE #ADJUSTED_DEMAND 

          CREATE TABLE #ADJUSTED_DEMAND 
            ( 
               ITEM_MASTER_SID     INT, 
               PERIOD_SID          INT NOT NULL, 
               AD_ACT_GROSS_AMOUNT NUMERIC(22, 6), 
               AD_FOR_GROSS_AMOUNT NUMERIC(22, 6), 
               YEARLY              INT, 
               MONTHLY             INT, 
               QUARTERLY           INT, 
               SEMI_ANNUAL         INT 
            ) 

          INSERT INTO #ADJUSTED_DEMAND 
                      (ITEM_MASTER_SID, 
                       AD_ACT_GROSS_AMOUNT, 
                       AD_FOR_GROSS_AMOUNT, 
                       MONTHLY, 
                       YEARLY, 
                       PERIOD_SID) 
          SELECT ITEM_MASTER_SID, 
                 ACT_GROSS_AMOUNT, 
                 COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT) AS FOR_GROSS_AMOUNT, 
                 MONTH, 
                 YEAR, 
                 D.PERIOD_SID 
          FROM   [DBO].[UDF_ADJUSTED_DEMAND_WAC](@ITEMID, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, @FORECAST_NAME_AD, @FORECAST_VERSION_AD) I 
                 JOIN PERIOD D 
                   ON D.PERIOD_SID = I.PERIOD_SID 
          --------------------INSERTING PRODUCT+ITEM LEVEL ACTUALS AND PROJECTED VALUES INTO  CFF_PRODUCT_LEVEL_FILES_DATA ------------- 
          ; 

          WITH CTE 
               AS (SELECT INVENTORY.ITEM_MASTER_SID, 
                          ACT_AMOUNT_WITHDRAWN, 
                          FOR_AMOUNT_WITHDRAWN, 
                          ACT_GROSS_AMOUNT AS DEMAND_SALES_ACTUAL, 
                          FOR_GROSS_AMOUNT AS DEMAND_SALES_PROJECTED,  
                          GTS_SALES_ACTUALS, 
                          GTS_SALES_PROJECTED, 
                          AD.AD_ACT_GROSS_AMOUNT, 
                          AD.AD_FOR_GROSS_AMOUNT, 
                          GTS.PERIOD_SID 
                   FROM   #INVENTORY INVENTORY 
                          INNER JOIN #DEMAND DEMAND 
                                  ON INVENTORY.ITEM_MASTER_SID = DEMAND.ITEM_MASTER_SID 
                                     AND INVENTORY.PERIOD_SID = DEMAND.PERIOD_SID 
                          INNER JOIN #GTS_DETAILS GTS 
                                  ON GTS.ITEM_MASTER_SID = DEMAND.ITEM_MASTER_SID 
                                     AND GTS.PERIOD_SID = DEMAND.PERIOD_SID 
                          INNER JOIN #ADJUSTED_DEMAND AD 
                                  ON AD.ITEM_MASTER_SID = GTS.ITEM_MASTER_SID 
                                     AND AD.PERIOD_SID = GTS.PERIOD_SID) 
          MERGE CFF_PRODUCT_LEVEL_FILES_DATA AS TARGET 
          USING CTE AS SOURCE 
          ON TARGET.ITEM_MASTER_SID = SOURCE.ITEM_MASTER_SID 
             AND TARGET.PERIOD_SID = SOURCE.PERIOD_SID 
             AND TARGET.CFF_MASTER_SID = @CFF_MASTER_SID 
          WHEN NOT MATCHED THEN 
            INSERT ( CFF_MASTER_SID, 
                     ITEM_MASTER_SID, 
                     PERIOD_SID, 
                     EX_FACTORY_SALES_ACTUALS, 
                     EX_FACTORY_SALES_PROJ, 
                     DEMAND_SALES_ACTUALS, 
                     DEMAND_SALES_PROJ, 
                     IW_MASTER_ACTUALS, 
                     IW_MASTER_PROJ, 
                     ADJUSTED_DEMAND_ACTUALS, 
                     ADJUSTED_DEMAND_PROJ ) 
            VALUES ( @CFF_MASTER_SID, 
                     SOURCE.ITEM_MASTER_SID, 
                     SOURCE.PERIOD_SID, 
                     SOURCE.GTS_SALES_ACTUALS, 
                     SOURCE.GTS_SALES_PROJECTED, 
                     SOURCE.DEMAND_SALES_ACTUAL, 
                     SOURCE.DEMAND_SALES_PROJECTED, 
                     SOURCE.ACT_AMOUNT_WITHDRAWN, 
                     SOURCE.FOR_AMOUNT_WITHDRAWN, 
                     SOURCE.AD_ACT_GROSS_AMOUNT, 
                     SOURCE.AD_FOR_GROSS_AMOUNT ) 
          WHEN MATCHED THEN 
            UPDATE SET TARGET.EX_FACTORY_SALES_ACTUALS = SOURCE.GTS_SALES_ACTUALS, 
                       TARGET.EX_FACTORY_SALES_PROJ = SOURCE.GTS_SALES_PROJECTED, 
                       TARGET.DEMAND_SALES_ACTUALS = SOURCE.DEMAND_SALES_ACTUAL, 
                       TARGET.DEMAND_SALES_PROJ = SOURCE.DEMAND_SALES_PROJECTED, 
                       TARGET.IW_MASTER_ACTUALS = SOURCE.ACT_AMOUNT_WITHDRAWN, 
                       TARGET.IW_MASTER_PROJ = SOURCE.FOR_AMOUNT_WITHDRAWN, 
                       TARGET.ADJUSTED_DEMAND_ACTUALS = SOURCE.AD_ACT_GROSS_AMOUNT, 
                       TARGET.ADJUSTED_DEMAND_PROJ = SOURCE.AD_FOR_GROSS_AMOUNT; 

          ------------------------------------------------------------------------------------------2 
          -----------------------PULLING COMPANY +ITEM  LEVEL INFROMATION FOR  CFF---------------- 
          DECLARE @CUST_ITEM_DETAILS [UDT_CUST_ITEM] 

          INSERT INTO @CUST_ITEM_DETAILS 
                      (ITEM_MASTER_SID, 
                       COMPANY_MASTER_SID) 
          SELECT DISTINCT ITEM_MASTER_SID, 
                          COMPANY_MASTER_SID 
          FROM   CCP_DETAILS CCP 
          WHERE  EXISTS (SELECT 1 
                         FROM   CFF_DETAILS CD 
                                JOIN PROJECTION_DETAILS PD 
                                  ON CD.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID 
                                     AND CD.CCP_DETAILS_SID = PD.CCP_DETAILS_SID 
                         WHERE  CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID 
                                AND CFF_MASTER_SID = @CFF_MASTER_SID) 

          ---------------------INVENTORY_WAC--------------- 
      /* SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME, 
                       @FORECAST_VERSION = FT.[VERSION] 
          FROM   FILE_MANAGEMENT FT 
                 INNER JOIN HELPER_TABLE HT 
                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE 
          WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE()) 
                   AND FT.FROM_PERIOD IS NOT NULL ) 
                 AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE()) 
                        OR FT.TO_PERIOD IS NULL ) 
                 AND HT.LIST_NAME = 'FILE_TYPE' 
                 AND HT.[DESCRIPTION] IN ( 'INVENTORY WITHDRAWAL - FORECAST DETAIL' ) 
          ORDER  BY FT.FROM_PERIOD DESC*/ 
          ---------------------CHANGED ONE--------------- 
          --------------------------------------------PULLING ACTUALS AND PROJECTION VALES FROM INVENTORY WITHDRAWAL - FORECAST DETAIL   FILE------------- 
		       SELECT @FORECAST_NAME_IW = FORECAST_NAME, 
                      @FORECAST_VERSION_IW = [VERSION]
          FROM  @FILE_VER
                WHERE  FILE_TYPE = 'INVENTORY WITHDRAWAL - FORECAST DETAIL'

          IF OBJECT_ID('TEMPDB..#INVENTORY_WAC', 'U') IS NOT NULL 
            DROP TABLE #INVENTORY_WAC 
		CREATE TABLE #INVENTORY_WAC
		(
		 ITEM_MASTER_SID INT,
		 COMPANY_MASTER_SID INT ,
		 PERIOD_SID INT,
		 FOR_AMOUNT_WITHDRAWN NUMERIC(22,6),
		 ACT_AMOUNT_WITHDRAWN NUMERIC(22,6)
		 )

		 INSERT INTO #INVENTORY_WAC(ITEM_MASTER_SID,COMPANY_MASTER_SID,PERIOD_SID,FOR_AMOUNT_WITHDRAWN,ACT_AMOUNT_WITHDRAWN)
          SELECT ITEM_MASTER_SID, 
                 COMPANY_MASTER_SID, 
                 PERIOD_SID, 
                 COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN) AS FOR_AMOUNT_WITHDRAWN, 
                 ACT_AMOUNT_WITHDRAWN 
          FROM   [DBO].[UDF_INVENTORY_WAC_DETAILS](@CUST_ITEM_DETAILS, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, @FORECAST_NAME_IW, @FORECAST_VERSION_IW) I
		

          ---------------------CUSTOMMER_WAC-------------- 
      /*SELECT TOP 1 @FORECAST_NAME = FT.FORECAST_NAME 
        ,@FORECAST_VERSION = FT.[VERSION] 
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
        AND HT.[DESCRIPTION] IN ('CUSTOMER SALES') 
      ORDER BY FT.FROM_PERIOD DESC 
      */ 
          ---------------------CHANGED ONE--------------- 
          --------------------------------------------PULLING ACTUALS AND PROJECTION VALES FROM CUSTOMER SALES   FILE------------- 
		       SELECT @FORECAST_NAME_CS = FORECAST_NAME, 
                      @FORECAST_VERSION_CS = [VERSION]
                FROM  @FILE_VER
                WHERE  FILE_TYPE = 'CUSTOMER SALES'

          IF OBJECT_ID('TEMPDB..#CUSTOMER_GTS', 'U') IS NOT NULL 
            DROP TABLE #CUSTOMER_GTS 
           
		   CREATE TABLE #CUSTOMER_GTS
		   (
		    ITEM_MASTER_SID INT,
			COMPANY_MASTER_SID INT,
			PERIOD_SID INT,
			CUST_ACT_GTS_SALES NUMERIC(22,6),
			CUST_FORE_GTS_SALES NUMERIC(22,6)
		   )
			
		   INSERT INTO #CUSTOMER_GTS(ITEM_MASTER_SID,COMPANY_MASTER_SID,PERIOD_SID,CUST_ACT_GTS_SALES,CUST_FORE_GTS_SALES)

          SELECT  ITEM_MASTER_SID, 
                          COMPANY_MASTER_SID, 
                          PERIOD_SID, 
                          CUST_ACT_GTS_SALES, 
                          COALESCE(CUST_FORE_GTS_SALES, CUST_ACT_GTS_SALES) AS CUST_FORE_GTS_SALES 
          FROM   [DBO].[UDF_CUST_GTS_WAC](@CUST_ITEM_DETAILS, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, @FORECAST_NAME_CS, @FORECAST_VERSION_CS) I;

          --------------------INSERTING PRODUCT+ITEM LEVEL ACTUALS AND PROJECTED VALUES INTO  CFF_PRODUCT_LEVEL_FILES_DATA ------------- 
          WITH CTE 
               AS (SELECT CT.ITEM_MASTER_SID, 
                          CT.COMPANY_MASTER_SID, 
                          CT.PERIOD_SID, 
                          CT.CUST_ACT_GTS_SALES, 
                          CT.CUST_FORE_GTS_SALES, 
                          IW.FOR_AMOUNT_WITHDRAWN, 
                          IW.ACT_AMOUNT_WITHDRAWN 
                   FROM   #CUSTOMER_GTS CT 
                          JOIN #INVENTORY_WAC IW 
                            ON IW.ITEM_MASTER_SID = CT.ITEM_MASTER_SID 
                               AND IW.COMPANY_MASTER_SID = CT.COMPANY_MASTER_SID 
                               AND IW.PERIOD_SID = CT.PERIOD_SID) 
          MERGE CFF_COMP_PRD_LEVEL_FILES_DATA AS TARGET 
          USING CTE AS SOURCE 
          ON TARGET.ITEM_MASTER_SID = SOURCE.ITEM_MASTER_SID 
             AND TARGET.COMPANY_MASTER_SID = SOURCE.COMPANY_MASTER_SID 
             AND TARGET.PERIOD_SID = SOURCE.PERIOD_SID 
             AND TARGET.CFF_MASTER_SID = @CFF_MASTER_SID 
          WHEN NOT MATCHED THEN 
            INSERT ( CFF_MASTER_SID, 
                     ITEM_MASTER_SID, 
                     COMPANY_MASTER_SID, 
                     PERIOD_SID, 
                     CUST_EX_FACTORY_SALES_ACTUALS, 
                     CUST_EX_FACTORY_SALES_PROJ, 
                     IW_DETAILS_ACTUALS, 
                     IW_DETAILS_PROJ ) 
            VALUES ( @CFF_MASTER_SID, 
                     SOURCE.ITEM_MASTER_SID, 
                     SOURCE.COMPANY_MASTER_SID, 
                     SOURCE.PERIOD_SID, 
                     SOURCE.CUST_ACT_GTS_SALES, 
                     SOURCE.CUST_FORE_GTS_SALES, 
                     SOURCE.FOR_AMOUNT_WITHDRAWN, 
                     SOURCE.ACT_AMOUNT_WITHDRAWN ) 
          WHEN MATCHED THEN 
            UPDATE SET TARGET.CUST_EX_FACTORY_SALES_ACTUALS = SOURCE.CUST_ACT_GTS_SALES, 
                       TARGET.CUST_EX_FACTORY_SALES_PROJ = SOURCE.CUST_FORE_GTS_SALES, 
                       TARGET.IW_DETAILS_ACTUALS = SOURCE.ACT_AMOUNT_WITHDRAWN, 
                       TARGET.IW_DETAILS_PROJ = SOURCE.FOR_AMOUNT_WITHDRAWN; 
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
  END --END OF PROCEDURE 
  GO