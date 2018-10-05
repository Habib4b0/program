IF EXISTS (SELECT 'X' 
           FROM   INFORMATION_SCHEMA.ROUTINES 
           WHERE  ROUTINE_NAME = 'PRC_CFF_FILES_DATA_INSERT' 
                  AND ROUTINE_SCHEMA = 'DBO') 
  BEGIN 
      DROP PROCEDURE [DBO].[PRC_CFF_FILES_DATA_INSERT] 
  END 

GO 

CREATE PROCEDURE [DBO].[PRC_CFF_FILES_DATA_INSERT] (@CFF_MASTER_SID VARCHAR(100),
                                                    @USER_ID INT,
													@SESSION_ID VARCHAR(50),
													@INDICATOR BIT = 0
                                                     ) 
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

	 DECLARE @CFF_PRODUCT           VARCHAR(200) = Concat ('ST_CFF_PRODUCT_LEVEL_FILES_DATA_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),----Product Level data
             @CFF_COMP_PROD         VARCHAR(200) = Concat ('ST_CFF_COMP_PRD_LEVEL_FILES_DATA_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),  ---- Comp+Prod data
			 @STATUS_TABLE       VARCHAR(200) = Concat ('ST_STATUS_TABLE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
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


-----------------PERIOD CONVERISON--------------------
IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
	DROP TABLE #PERIOD;

SELECT PERIOD_SID,
	PERIOD_DATE,
	MONTH,
	QUARTER,
	SEMI_ANNUAL,
	YEAR
INTO #PERIOD
FROM PERIOD
WHERE PERIOD_SID BETWEEN @ACT_START_PERIOD_SID
		AND @PROJ_END_PERIOD_SID

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

------------------------------------------------------Pulling files information starts here-----------------------------------
IF OBJECT_ID('TEMPDB..#FILE_INFO') IS NOT NULL
			DROP TABLE #FILE_INFO

			CREATE TABLE #FILE_INFO
			(
			CFF_MASTER_SID INT,
			FILE_TYPE     VARCHAR(100) NOT NULL,
            FORECAST_NAME VARCHAR(100) NULL,
            VERSION       VARCHAR(15) NULL
			)

			INSERT INTO #FILE_INFO
                      (CFF_MASTER_SID,
					   FILE_TYPE,
                       FORECAST_NAME,
                       VERSION )

			SELECT FI.CFF_MASTER_SID,
			       HT.[DESCRIPTION]  AS FILE_TYPE,
			       FT.FILE_NAME,
				   FT.[VERSION] 
			 FROM   CFF_FILE_SELECTION FT 
                 INNER JOIN HELPER_TABLE HT 
                         ON HT.HELPER_TABLE_SID = FT.FILE_TYPE 
				  INNER JOIN #CFF_PROJECTION_MASTER FI ON FI.CFF_MASTER_SID=FT.CFF_MASTER_SID
          WHERE  HT.[DESCRIPTION] IN ( 'EX-FACTORY SALES' , 'INVENTORY WITHDRAWAL - FORECAST SUMMARY','DEMAND','ADJUSTED DEMAND','INVENTORY WITHDRAWAL - FORECAST DETAIL','CUSTOMER SALES'  ) 

declare @cff_max int =(select count(*) from #CFF_PROJECTION_MASTER), @incr int=1




IF(@INDICATOR=0)
BEGIN

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
                                AND CFF_MASTER_SID = @FIRST_PROJ_SID) 

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
          --------------------------------------------PULLING ACTUALS AND PROJECTION VALES FROM EX-FACTORY SALES FILE------------- 

          IF OBJECT_ID('TEMPDB..#GTS_DETAILS') IS NOT NULL 
            DROP TABLE #GTS_DETAILS 

          CREATE TABLE #GTS_DETAILS 
            ( 
               CFF_MASTER_SID INT,
			   ITEMID              VARCHAR(50), 
               ITEM_MASTER_SID     INT, 
               GTS_SALES_PROJECTED NUMERIC(22, 6), 
               GTS_SALES_ACTUALS   NUMERIC(22, 6), 
               UNITS               NUMERIC(22, 6),
               PERIOD_SID          INT 
            ) 
			 set @incr=1
			while (@incr<=@cff_max)
			begin 
			--select @incr,@cff_max
          INSERT INTO #GTS_DETAILS 
                      (CFF_MASTER_SID,
					   ITEM_MASTER_SID, 
                       GTS_SALES_PROJECTED, 
                       GTS_SALES_ACTUALS, 
                       UNITS, 
                       PERIOD_SID) 
   --       SELECT F.CFF_MASTER_SID,
		 --        ITEM_MASTER_SID, 
   --              A.FORECAST_GTS_SALES, 
   --              A.ACTUAL_GTS_SALES, 
   --              A.UNITS, 
   --              A.MONTH, 
   --              A.QUARTER, 
   --              A.YEAR, 
   --              A.PERIOD_SID 
   --       FROM  #FILE_INFO F CROSS APPLY
		 --( SELECT ITEM_MASTER_SID, 
   --              COALESCE(B.FORECAST_GTS_SALES, B.ACTUAL_GTS_SALES) AS FORECAST_GTS_SALES, 
   --              B.ACTUAL_GTS_SALES, 
   --              COALESCE(B.FORECAST_GTS_UNITS, B.ACTUAL_GTS_UNITS) AS UNITS, 
   --              P.MONTH, 
   --              P.QUARTER, 
   --              P.YEAR, 
   --              P.PERIOD_SID FROM
		 --  UDF_GTS_WAC(@ITEMID, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, FORECAST_NAME, VERSION) B
   --              INNER JOIN PERIOD P 
   --                      ON P.PERIOD_SID = B.PERIOD_SID ) A
		 --WHERE  FILE_TYPE = 'EX-FACTORY SALES'
 SELECT          F.CFF_MASTER_SID,
                 ITEM_MASTER_SID, 
                 COALESCE(B.FORECAST_GTS_SALES, B.ACTUAL_GTS_SALES) AS FORECAST_GTS_SALES, 
                 B.ACTUAL_GTS_SALES, 
                 COALESCE(B.FORECAST_GTS_UNITS, B.ACTUAL_GTS_UNITS) AS UNITS,
                 PERIOD_SID FROM #FILE_INFO F CROSS APPLY
		   UDF_GTS_WAC(@ITEMID, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, FORECAST_NAME, VERSION) B
		 WHERE  F.FILE_TYPE = 'EX-FACTORY SALES'
		 and exists(select 1 from #CFF_PROJECTION_MASTER cfm where cfm.CFF_MASTER_SID=f.CFF_MASTER_SID and cfm.ID=@incr)
		 set @incr=@incr+1
		 end 
		 
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
          IF OBJECT_ID('TEMPDB..#INVENTORY') IS NOT NULL 
            DROP TABLE #INVENTORY 

          CREATE TABLE #INVENTORY 
            ( 
			   CFF_MASTER_SID INT,
               ITEM_MASTER_SID      INT, 
               PERIOD_SID           INT NOT NULL, 
               ACT_AMOUNT_WITHDRAWN NUMERIC(22, 6), 
               ACT_UNITS_WITHDRAWN  NUMERIC(22, 6), 
               FOR_AMOUNT_WITHDRAWN NUMERIC(22, 6), 
               FOR_UNITS_WITHDRAWN  NUMERIC(22, 6)
            ) 
			 set @incr=1
			while (@incr<=@cff_max)
			begin 

          INSERT INTO #INVENTORY 
                      (CFF_MASTER_SID,
					   ITEM_MASTER_SID, 
                       PERIOD_SID, 
                       ACT_AMOUNT_WITHDRAWN, 
                       ACT_UNITS_WITHDRAWN, 
                       FOR_AMOUNT_WITHDRAWN, 
                       FOR_UNITS_WITHDRAWN
                      ) 
SELECT           F.CFF_MASTER_SID,
                 I.ITEM_MASTER_SID, 
                 PERIOD_SID, 
                 ACT_AMOUNT_WITHDRAWN, 
                 ACT_UNITS_WITHDRAWN, 
                 COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN) AS FOR_AMOUNT_WITHDRAWN,
                 COALESCE(FOR_UNITS_WITHDRAWN, ACT_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN
		  FROM #FILE_INFO F CROSS APPLY
		  [DBO].[UDF_INVENTORY_WAC](@ITEMID, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, FORECAST_NAME, VERSION) I 
						 WHERE FILE_TYPE = 'INVENTORY WITHDRAWAL - FORECAST SUMMARY'
						  and exists(select 1 from #CFF_PROJECTION_MASTER cfm where cfm.CFF_MASTER_SID=f.CFF_MASTER_SID and cfm.ID=@incr)
		 set @incr=@incr+1
		 end 
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
          IF OBJECT_ID('TEMPDB.DBO.#DEMAND', 'U') IS NOT NULL 
            DROP TABLE #DEMAND 

          CREATE TABLE #DEMAND 
            ( 
			   CFF_MASTER_SID INT,
               ITEM_MASTER_SID  INT, 
               PERIOD_SID       INT NOT NULL, 
               ACT_GROSS_AMOUNT NUMERIC(22, 6), 
               ACT_GROSS_UNITS  NUMERIC(22, 6), 
               FOR_GROSS_AMOUNT NUMERIC(22, 6), 
               FOR_GROSS_UNITS  NUMERIC(22, 6)
            ) 
			set @incr=1
			while (@incr<=@cff_max)
			begin 

          INSERT INTO #DEMAND 
                      (CFF_MASTER_SID,
					   ITEM_MASTER_SID, 
                       ACT_GROSS_AMOUNT, 
                       ACT_GROSS_UNITS, 
                       FOR_GROSS_AMOUNT, 
                       FOR_GROSS_UNITS, 
                       PERIOD_SID) 
          SELECT F.CFF_MASTER_SID,
		         ITEM_MASTER_SID, 
                 ACT_GROSS_AMOUNT, 
                 ACT_GROSS_UNITS, 
                 COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT) AS FOR_GROSS_AMOUNT, 
                 COALESCE(FOR_GROSS_UNITS, ACT_GROSS_UNITS) AS FOR_GROSS_UNITS, 
                PERIOD_SID  FROM #FILE_INFO F CROSS APPLY
               [DBO].[UDF_DEMAND_WAC](@ITEMID, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, FORECAST_NAME, VERSION) I 
			 WHERE  FILE_TYPE = 'DEMAND'
			  and exists(select 1 from #CFF_PROJECTION_MASTER cfm where cfm.CFF_MASTER_SID=f.CFF_MASTER_SID and cfm.ID=@incr)
		 set @incr=@incr+1
		 end 
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
          IF OBJECT_ID('TEMPDB.DBO.#ADJUSTED_DEMAND', 'U') IS NOT NULL 
            DROP TABLE #ADJUSTED_DEMAND 

          CREATE TABLE #ADJUSTED_DEMAND 
            ( 
			   CFF_MASTER_SID      INT,
               ITEM_MASTER_SID     INT, 
               PERIOD_SID          INT NOT NULL, 
               AD_ACT_GROSS_AMOUNT NUMERIC(22, 6), 
               AD_FOR_GROSS_AMOUNT NUMERIC(22, 6)
            ) 
			set @incr=1
			while (@incr<=@cff_max)
			begin 
          INSERT INTO #ADJUSTED_DEMAND 
                      (CFF_MASTER_SID,
					   ITEM_MASTER_SID, 
                       AD_ACT_GROSS_AMOUNT, 
                       AD_FOR_GROSS_AMOUNT,
                       PERIOD_SID)
          SELECT F.CFF_MASTER_SID,
		         ITEM_MASTER_SID, 
                 ACT_GROSS_AMOUNT, 
                 COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT) AS FOR_GROSS_AMOUNT,  
                 PERIOD_SID 
          FROM  #FILE_INFO F CROSS APPLY
		   [DBO].[UDF_ADJUSTED_DEMAND_WAC](@ITEMID, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, FORECAST_NAME, VERSION) I 
				   WHERE  FILE_TYPE = 'ADJUSTED DEMAND' 
				   and exists(select 1 from #CFF_PROJECTION_MASTER cfm where cfm.CFF_MASTER_SID=f.CFF_MASTER_SID and cfm.ID=@incr)
		 set @incr=@incr+1
		 end 
          --------------------INSERTING PRODUCT+ITEM LEVEL ACTUALS AND PROJECTED VALUES INTO  CFF_PRODUCT_LEVEL_FILES_DATA ------------- 
        
		DECLARE @SQL NVARCHAR(MAX)
		SET @SQL = CONCAT('TRUNCATE TABLE ', @CFF_PRODUCT, '  
                                 INSERT INTO ', @CFF_PRODUCT , ' (CFF_MASTER_SID,
		                                                    ITEM_MASTER_SID, 
		                                                    PERIOD_SID,EX_FACTORY_SALES_ACTUALS,
															EX_FACTORY_SALES_PROJ,
                                                            DEMAND_SALES_ACTUALS,
                                                            DEMAND_SALES_PROJ,
                                                            ADJUSTED_DEMAND_ACTUALS,
                                                            ADJUSTED_DEMAND_PROJ,
                                                            IW_MASTER_ACTUALS,
                                                            IW_MASTER_PROJ)
							SELECT CF.CFF_MASTER_SID,
							       GTS.ITEM_MASTER_SID,
								   P.PERIOD_SID,
								   GTS.GTS_SALES_ACTUALS AS EX_FACTORY_SALES_ACTUALS,
								   GTS.GTS_SALES_PROJECTED AS EX_FACTORY_SALES_PROJ,
								   DEMAND.ACT_GROSS_AMOUNT AS DEMAND_SALES_ACTUAL,
								   DEMAND.FOR_GROSS_AMOUNT AS DEMAND_SALES_PROJECTED,
								   AD.AD_ACT_GROSS_AMOUNT AS ADJUSTED_DEMAND_ACTUALS,
								   AD.AD_FOR_GROSS_AMOUNT AS ADJUSTED_DEMAND_PROJ,
								   INVENTORY.ACT_AMOUNT_WITHDRAWN AS IW_MASTER_ACTUALS,
								   INVENTORY.FOR_AMOUNT_WITHDRAWN AS IW_MASTER_PROJ
								     FROM  #CFF_PROJECTION_MASTER CF CROSS JOIN #PERIOD  P
						 INNER JOIN #GTS_DETAILS GTS 
                          ON GTS.CFF_MASTER_SID=CF.CFF_MASTER_SID AND GTS.PERIOD_SID=P.PERIOD_SID
						  LEFT JOIN	 #INVENTORY INVENTORY 
						  ON INVENTORY.CFF_MASTER_SID=CF.CFF_MASTER_SID AND INVENTORY.PERIOD_SID=P.PERIOD_SID
						  AND GTS.ITEM_MASTER_SID=INVENTORY.ITEM_MASTER_SID
                          LEFT JOIN #DEMAND DEMAND 
                          ON DEMAND.CFF_MASTER_SID=CF.CFF_MASTER_SID AND DEMAND.PERIOD_SID=P.PERIOD_SID
						  AND GTS.ITEM_MASTER_SID = DEMAND.ITEM_MASTER_SID
                          LEFT JOIN #ADJUSTED_DEMAND AD 
                           ON AD.CFF_MASTER_SID=CF.CFF_MASTER_SID AND AD.PERIOD_SID=P.PERIOD_SID
						   AND GTS.ITEM_MASTER_SID = AD.ITEM_MASTER_SID')

					
							EXEC Sp_executesql @SQL

  DECLARE @SQL_STATUS NVARCHAR(MAX)
SET @SQL_STATUS=CONCAT('UPDATE ',@STATUS_TABLE,' SET FLAG=''C''',' WHERE SCREEN_NAME=''FILES_INSERT'' AND VIEW_NAME=''PRODUCT''')
EXEC Sp_executesql @SQL_STATUS


		 END

ELSE
BEGIN
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
                                AND CFF_MASTER_SID = @FIRST_PROJ_SID) 

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
          IF OBJECT_ID('TEMPDB..#INVENTORY_WAC', 'U') IS NOT NULL 
            DROP TABLE #INVENTORY_WAC 
		CREATE TABLE #INVENTORY_WAC
		(
		 CFF_MASTER_SID INT,
		 ITEM_MASTER_SID INT,
		 COMPANY_MASTER_SID INT ,
		 PERIOD_SID INT,
		 FOR_AMOUNT_WITHDRAWN NUMERIC(22,6),
		 ACT_AMOUNT_WITHDRAWN NUMERIC(22,6)
		 )
		 set @incr=1
			while (@incr<=@cff_max)
			begin 
		 INSERT INTO #INVENTORY_WAC(CFF_MASTER_SID,ITEM_MASTER_SID,COMPANY_MASTER_SID,PERIOD_SID,FOR_AMOUNT_WITHDRAWN,ACT_AMOUNT_WITHDRAWN)

		  SELECT F.CFF_MASTER_SID,
		         ITEM_MASTER_SID, 
                 COMPANY_MASTER_SID, 
                 PERIOD_SID, 
                 COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN) AS FOR_AMOUNT_WITHDRAWN, 
                 ACT_AMOUNT_WITHDRAWN 
          FROM     #FILE_INFO F CROSS APPLY
		   [DBO].[UDF_INVENTORY_WAC_DETAILS](@CUST_ITEM_DETAILS, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, FORECAST_NAME, VERSION) I
		WHERE F.FILE_TYPE = 'INVENTORY WITHDRAWAL - FORECAST DETAIL'
		and exists(select 1 from #CFF_PROJECTION_MASTER cfm where cfm.CFF_MASTER_SID=f.CFF_MASTER_SID and cfm.ID=@incr)
		 set @incr=@incr+1
		 end 
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
          IF OBJECT_ID('TEMPDB..#CUSTOMER_GTS', 'U') IS NOT NULL 
            DROP TABLE #CUSTOMER_GTS 
           
		   CREATE TABLE #CUSTOMER_GTS
		   (
		    CFF_MASTER_SID INT,
		    ITEM_MASTER_SID INT,
			COMPANY_MASTER_SID INT,
			PERIOD_SID INT,
			CUST_ACT_GTS_SALES NUMERIC(22,6),
			CUST_FORE_GTS_SALES NUMERIC(22,6)
		   )
			set @incr=1
			while (@incr<=@cff_max)
			begin 
		   INSERT INTO #CUSTOMER_GTS
		   (CFF_MASTER_SID,ITEM_MASTER_SID,COMPANY_MASTER_SID,PERIOD_SID,CUST_ACT_GTS_SALES,CUST_FORE_GTS_SALES)

          SELECT         F.CFF_MASTER_SID,
		                 ITEM_MASTER_SID, 
                          COMPANY_MASTER_SID, 
                          PERIOD_SID, 
                          CUST_ACT_GTS_SALES, 
                          COALESCE(CUST_FORE_GTS_SALES, CUST_ACT_GTS_SALES) AS CUST_FORE_GTS_SALES 
          FROM    #FILE_INFO F CROSS APPLY 
		  [DBO].[UDF_CUST_GTS_WAC](@CUST_ITEM_DETAILS, @ACT_START_PERIOD_SID, @PROJ_END_PERIOD_SID, FORECAST_NAME, VERSION) I
		  WHERE F.FILE_TYPE = 'CUSTOMER SALES'
		  and exists(select 1 from #CFF_PROJECTION_MASTER cfm where cfm.CFF_MASTER_SID=f.CFF_MASTER_SID and cfm.ID=@incr)
		 set @incr=@incr+1
		 end 
		 
          --------------------INSERTING PRODUCT+ITEM LEVEL ACTUALS AND PROJECTED VALUES INTO  CFF_PRODUCT_LEVEL_FILES_DATA ------------- 
		  DECLARE @SQL_COMP NVARCHAR(MAX)

		  SET @SQL_COMP = Concat( 'TRUNCATE TABLE ', @CFF_COMP_PROD, '  
                                 INSERT INTO ', @CFF_COMP_PROD , ' (CFF_MASTER_SID,
		                                                    ITEM_MASTER_SID, 
															COMPANY_MASTER_SID,
		                                                    PERIOD_SID,
															CUST_EX_FACTORY_SALES_ACTUALS,
															CUST_EX_FACTORY_SALES_PROJ,
                                                            IW_DETAILS_ACTUALS,
                                                            IW_DETAILS_PROJ)

                                 SELECT CF.CFF_MASTER_SID,
								 CT.ITEM_MASTER_SID, 
                          CT.COMPANY_MASTER_SID, 
                          CT.PERIOD_SID, 
                          CT.CUST_ACT_GTS_SALES AS CUST_EX_FACTORY_SALES_ACTUALS, 
                          CT.CUST_FORE_GTS_SALES AS CUST_EX_FACTORY_SALES_PROJ, 
						  IW.ACT_AMOUNT_WITHDRAWN AS IW_DETAILS_ACTUALS,
                          IW.FOR_AMOUNT_WITHDRAWN AS IW_DETAILS_PROJ
                   FROM   #CFF_PROJECTION_MASTER CF CROSS JOIN #PERIOD  P
				 JOIN  #CUSTOMER_GTS CT ON CT.CFF_MASTER_SID=CF.CFF_MASTER_SID AND CT.PERIOD_SID=P.PERIOD_SID
                 LEFT JOIN #INVENTORY_WAC IW ON
                            IW.CFF_MASTER_SID=CF.CFF_MASTER_SID AND IW.PERIOD_SID=P.PERIOD_SID
							AND CT.COMPANY_MASTER_SID=IW.COMPANY_MASTER_SID AND CT.ITEM_MASTER_SID=IW.ITEM_MASTER_SID' )
						  EXEC Sp_executesql @SQL_COMP

		 DECLARE @SQL_STATUS_C NVARCHAR(MAX)
SET @SQL_STATUS_C=CONCAT('UPDATE ',@STATUS_TABLE,' SET FLAG=''C''',' WHERE SCREEN_NAME=''FILES_INSERT'' AND VIEW_NAME=''CUSTOMER''')
EXEC Sp_executesql @SQL_STATUS_C

                   

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