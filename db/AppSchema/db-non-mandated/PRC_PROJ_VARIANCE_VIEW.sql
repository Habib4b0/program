IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PROJ_VARIANCE_VIEW'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PROJ_VARIANCE_VIEW]
  END

GO

CREATE PROCEDURE [dbo].[PRC_PROJ_VARIANCE_VIEW] (@PROJECTION_SID         VARCHAR(500),
                                                 @DISCOUNT_LEVEL         VARCHAR(50),
                                                 @FREQUENCY              CHAR(1),
                                                 @CP_INDICATOR           CHAR(1),
                                                 @GROUP_FILTER           VARCHAR(50),
                                                 @GROUP_FILTER_VALUE     VARCHAR(50),
                                                 @HIERARCHY_NO           VARCHAR(500) = NULL,
                                                 @LEVEL_NO               INT = NULL,
                                                 @VIEW                   VARCHAR(50),
                                                 @CUSTOM_VIEW_MASTER_SID INT = NULL,
                                                 @VIEW_TAB               VARCHAR(50))
                                                
AS
                                             

  BEGIN
  SET NOCOUNT ON
   BEGIN TRY
  
/**********************************************************************************************************
** FILE NAME		:	PRC_PROJ_VARIANCE_VIEW.SQL
** PROCEDURE NAME	:	PRC_PROJ_VARIANCE_VIEW
** DESCRIPTION		:	we will pass mulptiple projection as input ,For first projection we will display the projection value of sales & discount,PPA for current projection table remainning projection will get values of sales & discount,PPA from main table & we will aggregate the discount level for both discount & PPA  
                        aggregation of projections attached to the particualr projection showing as output Hierarchy level for both 'TOTAL level of projection ' and 'DISCOUNT level'result based on user perceptions
** INPUT PARAMETERS	:	@PROJECTION_SID	         - passing input as PROJECTION_MASTER_SID
						@FREQUENCY               - based on frequency it will display the data monthly,quarterly semi annualy and annulaly
						@CP_INDICATOR            - passing input as either  'C' customer or 'P' product or custom based on we will display the result like hierarchy level
						@GROUP_FILTER            - passing input as either 'Sales' or 'Discount' or 'PPA'
						@GROUP_FILTER_VALUE      - passing input as either 'All Sales Group' or 'All Discount Group' or 'All PPA Group'
						@HIERARCHY_NO            - passing input as based on user perception 'Hierarchy_no'
						@LEVEL_NO                - passing input as based on user perception  'level_no'
						@VIEW                    - passing input as either 'period'  or 'pivot' view
						@CUSTOM_VIEW_MASTER_SID  - passing input as 'CUSTOM_VIEW_MASTER_SID'
						@DISCOUNT_VIEW           - Passing inpuit as either 'DETAIL_TOTAL_DISCOUNT' result or 'DETAIL_DISCOUNT'result


** OUTPUT PARAMETERS:	na
** AUTHOR NAME		:	@SANDEEP
** CREATION DATE	:	17/10/2016 - 
** CALLED BY		:   from application side they click on PRC_PROJECTION_VARIANCE screen the inside that proecure will call dispaly information 
**********************************************************************************************************
** CHANGE HISTORY
**********************************************************************************************************
** VER   DATE      LOCAL ALMTICKET NO      SUBTICKET NO   ONLINE ALM TICKET      AUTHOR                          DESCRIPTION 
** ---   --------  ---------             -------------        -------------    ----------------                  ------------------
** 1    17/10/2016  GAL-7965                GAL-7965         		           @SANDEEP     				Removed the session tables and used the main tables for calculation   
*********************************************************************************************************/
  -------------declaring variables--------------------------
    DECLARE 
              @STARTFROM                     DATE,
              @PROJECTION_DATE               DATE,
              @SP                            INT,
              @SP_PROJ_SID                   INT,
              @TEMP_PROJ_SID                 VARCHAR(500),
              @START_PERIOD_SID              INT,
              @END_PERIOD_SID                INT,
              @ITEM_UDT                      UDT_ITEM,
              @FORECAST_NAME                 VARCHAR(50),
              @FORECAST_VERSION              VARCHAR(15),
              @FORECAST_NAME_INV             VARCHAR(50),
              @FORECAST_VERSION_INV          VARCHAR(15),
              @FORECAST_NAME_DM              VARCHAR(50),
              @FORECAST_VERSION_DM           VARCHAR(15),
              @CUST_RELATIONSHIP_BUILDER_SID INT,
              @BUSINESS_UNIT                 INT -----------GAL-808
              ,@COMPANY                       INT-----GAL-808
     	  DECLARE @D_MASTER_TABLE     VARCHAR(200) = 'NM_DISCOUNT_PROJ_MASTER',
              @D_ACTUAL_TABLE     VARCHAR(200) = 'NM_ACTUAL_DISCOUNT',
              @D_PROJECTION_TABLE VARCHAR(200) = 'NM_DISCOUNT_PROJECTION',
			  @S_MASTER_TABLE     VARCHAR(200) = 'NM_SALES_PROJECTION_MASTER',
              @S_ACTUAL_TABLE     VARCHAR(200) = 'NM_ACTUAL_SALES',
              @S_PROJECTION_TABLE VARCHAR(200) = 'NM_SALES_PROJECTION',
			  @P_PROJECTION_TABLE VARCHAR(200) = 'NM_PPA_PROJECTION',
			  @P_MASTER_TABLE     VARCHAR(200) = 'NM_PPA_PROJECTION_MASTER',
			  @P_ACTUAL_TABLE     VARCHAR(200) = 'NM_ACTUAL_PPA'

     
      IF Object_id('TEMPDB..#PROJECTION_MASTER') IS NOT NULL
        TRUNCATE TABLE #PROJECTION_MASTER -------------------------IT CONTAINS PROJECTION VARIANCE EXCEL RESULT BASED ON PROJECTIONS
      ELSE
        CREATE TABLE #PROJECTION_MASTER
          (
             ID                    INT IDENTITY(1, 1),
             PROJECTION_MASTER_SID INT
          )


      IF Object_id('TEMPDB..#PIVOT_RESULT') IS NOT NULL
        TRUNCATE TABLE #PIVOT_RESULT
      ELSE
        CREATE TABLE #PIVOT_RESULT
          (
             PROJECTION_ID                                INT,
             HIERARCHY_NO                                 VARCHAR(100),
             LEVEL_NAME                                   VARCHAR(100),
             [PERIOD]                                     SMALLINT,
             [YEAR]                                       INT,
             CP_INDICATOR                                 CHAR(1),
			 EX_FACTORY_SALES_ACTUALS                  NUMERIC(22, 6),
             EX_FACTORY_SALES_PROJECTED                   NUMERIC(22, 6),
			 DEMAND_SALES_ACTUALS                      NUMERIC(22, 6),
             DEMAND_SALES_PROJECTED                       NUMERIC(22, 6),
			 INVENTORY_WITHDRAWAL_SALES_ACTUALS         NUMERIC(22, 6),
             INVENTORY_WITHDRAWAL_SALES_PROJECTED         NUMERIC(22, 6),
			 EX_FACTORY_SALES_ACTUALS_PERCENT           NUMERIC(38, 15),
             EX_FACTORY_SALES_PROJECTED_PERCENT           NUMERIC(38, 15),
			 DEMAND_SALES_ACTUALS_PERCENT               NUMERIC(38, 15),
             DEMAND_SALES_PROJECTED_PERCENT               NUMERIC(38, 15),
			 INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT NUMERIC(38, 15),
             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT NUMERIC(38, 15),
			 CONTRACT_SALES_ACTUALS                    NUMERIC(38, 15),
             CONTRACT_SALES_PROJECTED                     NUMERIC(38, 15),
			 CONTRACT_UNITS_ACTUALS                     NUMERIC(38, 15),
             CONTRACT_UNITS_PROJECTED                     NUMERIC(38, 15),
			 TOTAL_DISCOUNT_ACTUALS                     NUMERIC(38, 15),
             TOTAL_DISCOUNT_PROJECTED                     NUMERIC(38, 15),
			 TOTAL_DISCOUNT_ACTUALS_PERCENTAGE          NUMERIC(38, 15),
             TOTAL_DISCOUNT_PROJECTED_PERCENTAGE          NUMERIC(38, 15),
			 TOTAL_RPU_ACTUALS                          NUMERIC(22, 6),
             TOTAL_RPU_PROJECTED                          NUMERIC(22, 6),
			 NET_SALES_ACTUALS                          NUMERIC(38, 15),
             NET_SALES_PROJECTED                          NUMERIC(38, 15),
			 COGS_ACTUALS                              NUMERIC(22, 6),
             COGS_PROJECTED                               NUMERIC(22, 6),
			 NET_PROFIT_ACTUALS                        NUMERIC(22, 6),
             NET_PROFIT_PROJECTED                         NUMERIC(22, 6),
			  NET_SALES_OF_EX_FACTORY_ACTUALS                         NUMERIC(38, 15),
             NET_SALES_OF_EX_FACTORY_PROJECTED                          NUMERIC(38, 15)
			  ,DISCOUNT_OF_EX_FACTORY_ACTUALS NUMERIC(22, 6)
			,DISCOUNT_OF_EX_FACTORY_PROJECTED NUMERIC(22,6)
          )

      IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
        DROP TABLE #PERIOD;

      SELECT PERIOD_SID,
             PERIOD_DATE,
             MONTH,
             QUARTER,
             SEMI_ANNUAL,
             YEAR,
              CASE
                        WHEN LEFT(@FREQUENCY, 1) = 'M' THEN MONTH
                        WHEN LEFT(@FREQUENCY, 1) = 'Q' THEN QUARTER
                        WHEN LEFT(@FREQUENCY, 1) = 'S' THEN SEMI_ANNUAL
                        ELSE 01
                      END as PERIOD
      INTO   #PERIOD
      FROM   PERIOD

      SET @TEMP_PROJ_SID = Replace(@PROJECTION_SID + ',', ',,', ',')

      WHILE Patindex('%,%', @TEMP_PROJ_SID) <> 0
        BEGIN
            SELECT @SP = Patindex('%,%', @TEMP_PROJ_SID)

            SELECT @SP_PROJ_SID = LEFT(@TEMP_PROJ_SID, @SP - 1)

            SELECT @TEMP_PROJ_SID = Stuff(@TEMP_PROJ_SID, 1, @SP, '')

            INSERT INTO #PROJECTION_MASTER
                        (PROJECTION_MASTER_SID)
            SELECT @SP_PROJ_SID
        END

      DECLARE @FIRST_PROJ_SID                INT,
             
              @START_DATE                    DATETIME,
              @PROD_RELATIONSHIP_BUILDER_SID INT,
              @MIN_LEVEL                     INT,
              @CURRENT_DATE                  DATE
--------------------fetching the first projection------------------------
      SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID
      FROM   #PROJECTION_MASTER PM
      WHERE  ID = 1

      SELECT TOP 1 @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0),
                   @START_DATE = Dateadd(dd, 1, Eomonth(FROM_DATE, -1)),
                   @PROJECTION_DATE = Dateadd(dd, 1, Eomonth(TO_DATE, -1)),
                   @CUST_RELATIONSHIP_BUILDER_SID = CUST_RELATIONSHIP_BUILDER_SID,
                   @PROD_RELATIONSHIP_BUILDER_SID = PROD_RELATIONSHIP_BUILDER_SID,
                   @MIN_LEVEL = CASE @CP_INDICATOR
                                  WHEN 'C' THEN CUSTOMER_HIERARCHY_LEVEL
                                  WHEN 'P' THEN PRODUCT_HIERARCHY_LEVEL
                                END,
				   @BUSINESS_UNIT = BUSINESS_UNIT, -----GAL-808
				   @COMPANY = COMPANY_MASTER_SID -----GAL-808
      FROM   PROJECTION_MASTER
      WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
	  order by PROJECTION_MASTER_SID
	  

      SELECT @START_PERIOD_SID = Max(CASE
                                       WHEN PERIOD_DATE = @STARTFROM THEN PERIOD_SID
                                     END),
             @END_PERIOD_SID = Max(CASE
                                     WHEN PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0) THEN PERIOD_SID
                                   END)
      FROM   PERIOD
      WHERE  PERIOD_DATE IN ( @STARTFROM, @PROJECTION_DATE, @START_DATE )
     
      IF OBJECT_ID('TEMPDB..#CCP') IS NOT NULL
        DROP TABLE #CCP----------------ccp details based on HIERARCHY_NO
      create table  #CCP 
        (
           CCP_DETAILS_SID INT,
           HIERARCHY_NO    VARCHAR(50),
           LEVEL_NAME      VARCHAR(100)
        )

      IF @CP_INDICATOR IN ( 'C', 'P' )---------------------------------------based on @CP_INDICATOR we will display either customer & product 
        BEGIN
	
            DECLARE @SQL1 NVARCHAR(MAX)=N''

            SET @SQL1 = CONCAT ('SELECT DISTINCT LCCP.CCP_DETAILS_SID,
                LCCP.HIERARCHY_NO,
                LEVEL_NAME
FROM   (SELECT CCPMAP.CCP_DETAILS_SID,
               HLD.HIERARCHY_NO,
               HLD.RELATIONSHIP_LEVEL_SID,
               LEVEL_NAME
        FROM   (SELECT RLD.RELATIONSHIP_LEVEL_VALUES,
                       RLD.HIERARCHY_NO,
                       CCP.CCP_DETAILS_SID
                FROM   RELATIONSHIP_LEVEL_DEFINITION RLD
                       JOIN CCP_MAP CCP
                         ON RLD.RELATIONSHIP_LEVEL_SID = CCP.RELATIONSHIP_LEVEL_SID
                            AND RLD.RELATIONSHIP_BUILDER_SID = ', CASE @CP_INDICATOR
                                                                                            WHEN 'C' THEN @CUST_RELATIONSHIP_BUILDER_SID
                                                                                            WHEN 'P' THEN @PROD_RELATIONSHIP_BUILDER_SID
                                                                                          END, '
                       JOIN PROJECTION_DETAILS PD
                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                            AND PD.PROJECTION_MASTER_SID = ', @FIRST_PROJ_SID, '
                        ', CASE @GROUP_FILTER
                                                     WHEN 'SALES' THEN ' 
						JOIN '+@S_MASTER_TABLE+' S ON S.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID'
                                                     WHEN 'DISCOUNT' THEN ' 
						JOIN '+@D_MASTER_TABLE+' S ON S.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID'
                                                     WHEN 'PPA' THEN ' 
						JOIN '+@P_MASTER_TABLE+' S ON S.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID'
                                                   END, ' 
                         
                WHERE  (S.USER_GROUP = ''', @GROUP_FILTER_VALUE, ''' OR ''ALL'' = ''', @GROUP_FILTER_VALUE, ''')
                      ) CCPMAP,
               (SELECT RLD1.HIERARCHY_NO,
                       RLD1.RELATIONSHIP_LEVEL_SID,
                       LEVEL_NAME
                FROM   RELATIONSHIP_LEVEL_DEFINITION RLD1
                       JOIN ', CASE @CP_INDICATOR
                                                         WHEN 'C' THEN 'PROJECTION_CUST_HIERARCHY'
                                                         WHEN 'P' THEN 'PROJECTION_PROD_HIERARCHY'
                                                       END, ' PCH
                         ON PCH.RELATIONSHIP_LEVEL_SID = RLD1.RELATIONSHIP_LEVEL_SID
                            AND PCH.PROJECTION_MASTER_SID = ', @FIRST_PROJ_SID, '
							', CASE
                                    WHEN @LEVEL_NO <> 0 THEN 'WHERE  RLD1.HIERARCHY_NO LIKE '
                                                             + @HIERARCHY_NO + '%'
                                    ELSE ''
                                  END, '
               ) HLD
        WHERE  CCPMAP.HIERARCHY_NO LIKE HLD.HIERARCHY_NO + ''%'') LCCP
WHERE  LCCP.HIERARCHY_NO IN (SELECT RLD2.HIERARCHY_NO
                             FROM   RELATIONSHIP_LEVEL_DEFINITION RLD2
                                    JOIN '
                                       , CASE @CP_INDICATOR WHEN 'C' THEN 'PROJECTION_CUST_HIERARCHY' WHEN 'P' THEN 'PROJECTION_PROD_HIERARCHY' END
                                       , ' PCH2
                                      ON PCH2.RELATIONSHIP_LEVEL_SID = RLD2.RELATIONSHIP_LEVEL_SID
                                         AND PCH2.PROJECTION_MASTER_SID = '
                                       , CAST(@FIRST_PROJ_SID AS VARCHAR(20))
                                       , '
                             WHERE  RLD2.LEVEL_NO  '
                                      , CASE WHEN @LEVEL_NO = 0 THEN ' >= ' + CAST(@MIN_LEVEL AS VARCHAR(50)) ELSE '= ' +CAST(@LEVEL_NO AS VARCHAR(50)) END , ')')


	
		    INSERT INTO #CCP
                        (CCP_DETAILS_SID,
                         HIERARCHY_NO,
                         LEVEL_NAME)
            EXEC sp_executesql @SQL1	

		end
			
      ELSE
         BEGIN
            INSERT INTO #CCP
                        (CCP_DETAILS_SID,
                         HIERARCHY_NO,
                         LEVEL_NAME)
            SELECT DISTINCT CCM.CCP_DETAILS_SID,
                            CRB.CUSTOM_HIERARCHY_NO,
                            RLD.LEVEL_NAME
            FROM   CUSTOM_RELATIONSHIP_BUILDER CRB
                   INNER JOIN CUSTOM_CCP_MAP CCM
                           ON CCM.CUSTOM_VIEW_DETAILS_SID = CRB.CUSTOM_VIEW_DETAILS_SID
                              AND CCM.CUSTOM_HIERARCHY_NO = CRB.CUSTOM_HIERARCHY_NO
                   INNER JOIN CUSTOM_VIEW_DETAILS CVD
                           ON CVD.CUSTOM_VIEW_DETAILS_SID = CRB.CUSTOM_VIEW_DETAILS_SID
                   INNER JOIN CUSTOM_VIEW_MASTER CVM
                           ON CVM.CUSTOM_VIEW_MASTER_SID = CVD.CUSTOM_VIEW_MASTER_SID
                   INNER JOIN RELATIONSHIP_LEVEL_DEFINITION RLD
                           ON RLD.HIERARCHY_LEVEL_DEFINITION_SID = CVD.HIERARCHY_ID
            WHERE  CVM.PROJECTION_MASTER_SID = @PROJECTION_SID
                   AND CVD.CUSTOM_VIEW_MASTER_SID = @CUSTOM_VIEW_MASTER_SID
                   AND ( CVD.LEVEL_NO = @LEVEL_NO
                          OR @LEVEL_NO IS NULL
                          OR @LEVEL_NO = 0 )
        END
   IF Object_id('TEMPDB..#DATA_TABLE') IS NOT NULL
        DROP TABLE #DATA_TABLE-------------------contains the data based on the hierarchy and period wise 

      SELECT HIERARCHY_NO,
             LEVEL_NAME,
             PERIOD,
             YEAR
      INTO   #DATA_TABLE
      FROM   (SELECT DISTINCT HIERARCHY_NO,
                              LEVEL_NAME
              FROM   #CCP) C
             CROSS JOIN (SELECT DISTINCT PERIOD,
                                         YEAR
                         FROM   #PERIOD
                         WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID) P

      DECLARE @LEVEL_DISC   VARCHAR(100),
              @FIELD_VALUES VARCHAR(500)

      SELECT @LEVEL_DISC = Max(CASE
                                 WHEN FIELD_NAME = 'LEVEL' THEN FIELD_VALUES
                               END),
             @FIELD_VALUES = Max(CASE
                                   WHEN FIELD_NAME = 'SELECTED DISCOUNTS' THEN FIELD_VALUES
                                 END)
      FROM   NM_PROJECTION_SELECTION
      WHERE  SCREEN_NAME = 'DISCOUNT PROJECTION'
             AND PROJECTION_MASTER_SID = @FIRST_PROJ_SID
             AND FIELD_NAME IN ( 'LEVEL', 'SELECTED DISCOUNTS' )

      DECLARE @D_SQL NVARCHAR(MAX)=''
-------------------------------based on the inputs discouts were to be divide--------------
      IF Object_id('TEMPDB..#DISCOUNT_INFO') IS NOT NULL
        DROP TABLE #DISCOUNT_INFO

      CREATE TABLE #DISCOUNT_INFO
        (
           TOKEN    VARCHAR(100),
           DISCOUNT VARCHAR(100)
        )

      SET @D_SQL= Concat(' INSERT INTO #DISCOUNT_INFO
                  (TOKEN,
                   DISCOUNT)
 SELECT DISTINCT CASE
                        WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN CAST(R.RS_CONTRACT_SID AS VARCHAR(50))
                        ELSE PRICE_GROUP_TYPE
                      END AS TOKEN,
                      CASE
                        WHEN''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN RS_NAME
                        ELSE PRICE_GROUP_TYPE
                      END AS DISCOUNT
      FROM   ', @D_MASTER_TABLE, ' S
             INNER JOIN RS_CONTRACT R
                     ON R.RS_CONTRACT_SID = S.RS_CONTRACT_SID
      WHERE   ( (''', @LEVEL_DISC, ''' = ''PROGRAM CATEGORY''
                     AND PRICE_GROUP_TYPE IN (SELECT TOKEN
                                              FROM   UDF_SPLITSTRING(''', @FIELD_VALUES, ''','','')) )
                    OR (''', @LEVEL_DISC, ''' = ''PROGRAM''
                         AND RS_NAME IN (SELECT TOKEN
                                         FROM   UDF_SPLITSTRING(''', @FIELD_VALUES, ''','','')) ) )
            
      UNION ALL
      SELECT DISTINCT CASE
                        WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN CAST(R.RS_CONTRACT_SID AS VARCHAR(50))
                        ELSE ''PRICE PROTECTION''
                      END AS TOKEN,
                      CASE
                        WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN RS_NAME
                        ELSE ''PRICE PROTECTION''
                      END AS DISCOUNT
      FROM   ', @P_MASTER_TABLE, ' S
             INNER JOIN RS_CONTRACT R
                     ON R.RS_CONTRACT_SID = S.RS_CONTRACT_SID')

		EXEC sp_executesql @D_SQL		
		


  IF Object_id('TEMPDB..#TEMP_CCP') IS NOT NULL
	DROP TABLE #TEMP_CCP------------------------ccp level information based on the hierarchy---------------

CREATE TABLE #TEMP_CCP (
	COMPANY_MASTER_SID INT
	, CONTRACT_MASTER_SID INT
	, ITEM_MASTER_SID INT
	, PROJECTION_DETAILS_SID INT
	, PROJECTION_MASTER_SID INT
	,BUSINESS_UNIT INT
	,COMPANY INT
	, HIERARCHY_NO VARCHAR(100)
	, LEVEL_NAME VARCHAR(100)
	)

INSERT INTO #TEMP_CCP (
	COMPANY_MASTER_SID
	, CONTRACT_MASTER_SID
	, ITEM_MASTER_SID
	, PROJECTION_DETAILS_SID
	, PROJECTION_MASTER_SID
	,BUSINESS_UNIT
	,COMPANY
	, HIERARCHY_NO
	, LEVEL_NAME
	)
SELECT CCP.COMPANY_MASTER_SID
	, CCP.CONTRACT_MASTER_SID
	, CCP.ITEM_MASTER_SID
	, PD.PROJECTION_DETAILS_SID
	, PM.PROJECTION_MASTER_SID
	,PM.BUSINESS_UNIT
	,PM.COMPANY_MASTER_SID AS COMPANY
	, HIERARCHY_NO
	, LEVEL_NAME
FROM CCP_DETAILS CCP
INNER JOIN PROJECTION_DETAILS PD
	ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
INNER JOIN PROJECTION_MASTER PM
	ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
INNER JOIN #CCP C
	ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
WHERE PM.PROJECTION_MASTER_SID = @FIRST_PROJ_SID

------------------------------------GAL-808-------------------------------------
SELECT @BUSINESS_UNIT=BUSINESS_UNIT,
@COMPANY=COMPANY
FROM #TEMP_CCP
-------------------------------------------------------------------------------------


      DECLARE @ITEMID [DBO].[UDT_ITEM]

      INSERT INTO @ITEMID
      SELECT IM.ITEM_MASTER_SID
      FROM   ITEM_MASTER IM
      WHERE  EXISTS (SELECT 1
                     FROM   #TEMP_CCP A
                     WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                            AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID)

SET @CURRENT_DATE = CASE
                      WHEN LEFT(@FREQUENCY, 1) = 'M' THEN Dateadd(DD, 1, Eomonth(Getdate(), -1))
                      WHEN LEFT(@FREQUENCY, 1) = 'Q' THEN Datefromparts(Year(Getdate()), Datepart(QUARTER, Getdate()), 01)
                      WHEN LEFT(@FREQUENCY, 1) = 'S' THEN Datefromparts(Year(Getdate()), ( ( ( ( ( Month(Getdate()) ) - 1 ) / 6 ) * 6 ) + 1 ), 01)
                      ELSE Datefromparts(Year(Getdate()), 01, 01)
                    END
-------------------------for the prior projection we will take result form procedure------------------

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
    
      INSERT INTO #PRODUCT_FILE_TEMP(PROJECTION_MASTER_SID,
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
   DECLARE @ITEM_INFO TABLE
              (
                 ITEM_ID         VARCHAR(50),
                 NDC8            VARCHAR(20),
                 ITEM_MASTER_SID INT
              )

            INSERT INTO @ITEM_INFO
                        (ITEM_ID,
                         NDC8,
                         ITEM_MASTER_SID)
            SELECT DISTINCT IM.ITEM_ID,
                            IM.NDC8,
                            IM.ITEM_MASTER_SID
            FROM   ITEM_MASTER IM
                   INNER JOIN CCP_DETAILS CCP
                           ON CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                   INNER JOIN PROJECTION_DETAILS PD
                           ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
            WHERE  PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                   AND EXISTS (SELECT 1
                               FROM   #CCP C
                               WHERE  C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID)

            INSERT INTO @ITEM_UDT
                        (ITEM_MASTER_SID)
            SELECT DISTINCT ITEM_MASTER_SID
            FROM   @ITEM_INFO
---------------------------active fiel inforamtion-----------------
            DECLARE @FILE_VER AS TABLE
              (
                 FILE_TYPE     VARCHAR(100),
                 FORECAST_NAME VARCHAR(100),
                 VERSION       VARCHAR(100)
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
                           HT.[DESCRIPTION]                        AS FILE_TYPE,
                           ROW_NUMBER()
                             OVER (
                               PARTITION BY FILE_TYPE
                               ORDER BY FILE_MANAGEMENT_SID DESC ) AS RN
                    FROM   FILE_MANAGEMENT FT
                           INNER JOIN HELPER_TABLE HT
                                   ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
                    WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE())
                             AND FT.FROM_PERIOD IS NOT NULL )
                           AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE())
                                  OR FT.TO_PERIOD IS NULL )
                           AND HT.LIST_NAME = 'FILE_TYPE'
                           AND HT.[DESCRIPTION] IN ( 'EX-FACTORY SALES', 'DEMAND',  'Inventory Withdrawal - Forecast Summary' )
						   AND FT.BUSINESS_UNIT=@BUSINESS_UNIT
						   AND FT.COMPANY=@COMPANY) A
            WHERE  RN = 1

            SELECT @FORECAST_NAME = FORECAST_NAME,
                   @FORECAST_VERSION = [VERSION]
            FROM   @FILE_VER
            WHERE  FILE_TYPE = 'EX-FACTORY SALES'

            SELECT @FORECAST_NAME_INV = FORECAST_NAME,
                   @FORECAST_VERSION_INV = [VERSION]
            FROM   @FILE_VER
            WHERE  FILE_TYPE =  'Inventory Withdrawal - Forecast Summary'

            SELECT @FORECAST_NAME_DM = FORECAST_NAME,
                   @FORECAST_VERSION_DM = [VERSION]
            FROM   @FILE_VER
            WHERE  FILE_TYPE = 'DEMAND'
--------------------exfactory information--------------
            IF OBJECT_ID('TEMPDB..#TEMP_GTS_DETAILS') IS NOT NULL
              DROP TABLE #TEMP_GTS_DETAILS

            CREATE TABLE #TEMP_GTS_DETAILS
              (
                 GTS_SALES_PROJECTED NUMERIC(22, 6),
                 GTS_SALES_ACTUALS   NUMERIC(22, 6),
                 UNITS               NUMERIC(22, 6),
                 PERIOD_SID          INT,
				 ITEM_MASTER_sID     INT
              )

            INSERT INTO #TEMP_GTS_DETAILS
                        (GTS_SALES_PROJECTED,
                         GTS_SALES_ACTUALS,
                         UNITS,
                         PERIOD_SID,ITEM_MASTER_sID)
            SELECT COALESCE(A.FORECAST_GTS_SALES, A.ACTUAL_GTS_SALES) AS GTS_SALES_PROJECTED,
                   A.ACTUAL_GTS_SALES,
                   COALESCE(A.FORECAST_GTS_UNITS, A.ACTUAL_GTS_UNITS) AS UNITS,
                   PERIOD_SID,ITEM_MASTER_sID
            FROM   UDF_GTS_WAC(@ITEM_UDT, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME, @FORECAST_VERSION) A
			
            --------------------------------------------DEMANT SALES-----------------------------------------------------------------------
            -----------------------------------------------------INVENTORY-------------------------------------------------------------------------------------------------
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
                   FOR_AMOUNT_WITHDRAWN,
                   FOR_UNITS_WITHDRAWN,
                   P.YEAR,
                   P.MONTH,
                   P.QUARTER,
                   SEMI_ANNUAL
            FROM   [DBO].[UDF_INVENTORY_WAC](@ITEMID, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME_INV, @FORECAST_VERSION_INV) I
                   INNER JOIN PERIOD P
                           ON P.PERIOD_SID = I.PERIOD_SID
                   INNER JOIN @ITEMID IM
                           ON I.ITEM_MASTER_SID = IM.ITEM_MASTER_SID

            -----------------------------------------------------DEMAND-------------------------------------------------------------------------------------------------
            IF OBJECT_ID('TEMPDB..#DEMAND') IS NOT NULL
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
                         PERIOD_SID,
                         ACT_GROSS_AMOUNT,
                         ACT_GROSS_UNITS,
                         FOR_GROSS_AMOUNT,
                         FOR_GROSS_UNITS,
                         MONTHLY,
                         QUARTERLY,
                         YEARLY,
                         SEMI_ANNUAL)
            SELECT D.ITEM_MASTER_SID,
                   P.PERIOD_SID,
                   ACT_GROSS_AMOUNT,
                   ACT_GROSS_UNITS,
                   COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT),
                   COALESCE(FOR_GROSS_UNITS, ACT_GROSS_UNITS),
                   P.MONTH,
                   P.[QUARTER],
                   P.[YEAR],
                   P.SEMI_ANNUAL
            FROM   [DBO].[UDF_DEMAND_WAC](@ITEMID, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME_DM, @FORECAST_VERSION_DM) D
                   INNER JOIN PERIOD P
                           ON P.PERIOD_SID = D.PERIOD_SID
                   INNER JOIN @ITEMID IM
                           ON D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID

            IF OBJECT_ID('TEMPDB..#ITEM_PRICING') IS NOT NULL
              DROP TABLE #ITEM_PRICING

            SELECT ITEM_MASTER_sID,PERIOD_sID,PRICING_QUALIFIER,ITEM_PRICE
            INTO   #ITEM_PRICING
            FROM   [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH')
			--declare @D_SQL1 nvarchar(max)=''

			IF @VIEW = 'DETAIL_TOTAL_DISCOUNT' 
        BEGIN-------------------all the table information used to achoeve result------------------
           

  SET @D_SQL= N''+'INSERT INTO #PIVOT_RESULT
                        (PROJECTION_ID,
                         LEVEL_NAME,
                         HIERARCHY_NO,
                         CP_INDICATOR,
                         PERIOD,
                         YEAR,
						 EX_FACTORY_SALES_ACTUALS,
                         EX_FACTORY_SALES_PROJECTED,
						 DEMAND_SALES_ACTUALS,
                         DEMAND_SALES_PROJECTED,
						 INVENTORY_WITHDRAWAL_SALES_ACTUALS,
                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
						 EX_FACTORY_SALES_ACTUALS_PERCENT,
                         EX_FACTORY_SALES_PROJECTED_PERCENT,
						 DEMAND_SALES_ACTUALS_PERCENT,
                         DEMAND_SALES_PROJECTED_PERCENT,
						 INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT,
                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
						 CONTRACT_SALES_ACTUALS,
                         CONTRACT_SALES_PROJECTED,
						 CONTRACT_UNITS_ACTUALS,
                         CONTRACT_UNITS_PROJECTED,
						 TOTAL_DISCOUNT_ACTUALS,
                         TOTAL_DISCOUNT_PROJECTED,
						 TOTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
						 TOTAL_RPU_ACTUALS,
                         TOTAL_RPU_PROJECTED,
						 NET_SALES_ACTUALS,
                         NET_SALES_PROJECTED,
						 COGS_ACTUALS,
                         COGS_PROJECTED,
						 NET_PROFIT_ACTUALS,
                         NET_PROFIT_PROJECTED
						 , NET_SALES_OF_EX_FACTORY_ACTUALS ,
                         NET_SALES_OF_EX_FACTORY_PROJECTED
						 ,DISCOUNT_OF_EX_FACTORY_ACTUALS
						 ,DISCOUNT_OF_EX_FACTORY_PROJECTED)
						 '
			SET @D_SQL= @D_SQL+N'SELECT '+ CAST(@FIRST_PROJ_SID as varchar(100)) +N' PROJECTION_ID,
                   DT.LEVEL_NAME,
                   DT.HIERARCHY_NO,
                   '''+@CP_INDICATOR+N''',
                   DT.PERIOD,
                   DT.YEAR,
				   EX_FACTORY_SALES_ACTUALS = CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0) ELSE NULL END,
                   EX_FACTORY_SALES_PROJECTED = ISNULL(GTS.GTS_SALES_PROJECTED, 0),
                   DEMAND_SALES_ACTUALS = CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(DEMAND.DEMAND_SALES_ACTUAL, 0) ELSE NULL END,
                   DEMAND_SALES_PROJECTED = ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0),
                   INVENTORY_WITHDRAWAL_SALES_ACTUALS =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0) ELSE NULL END,
                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0),
                   EX_FACTORY_SALES_ACTUALS_PERCENT =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100 ELSE NULL END,
                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                   DEMAND_SALES_ACTUALS_PERCENT =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 ELSE NULL END,
                   DEMAND_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                   INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 ELSE NULL END,
                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                   CONTRACT_SALES_ACTUALS =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) ELSE NULL END,
                   CONTRACT_SALES_PROJECTED = ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0),
                   CONTRACT_UNITS_ACTUALS =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(CONTRACT_UNITS_ACTUALS, 0) ELSE NULL END,
                   CONTRACT_UNITS_PROJECTED = ISNULL(CONTRACT_UNITS_PROJECTED, 0),
				   TOTAL_DISCOUNT_ACTUALS =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                              + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ELSE NULL END,'
SET @D_SQL=@D_SQL+N'
                   TOTAL_DISCOUNT_PROJECTED = ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                              + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0),
                   TOTAL_DISCOUNT_ACTUALS_PERCENTAGE =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                                                    + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 ELSE NULL END,
                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                   TOTAL_RPU_ACTUALS =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                                    + ISNULL(PPA.PPA_ACTUAL_RPU, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) ELSE NULL END,
                   TOTAL_RPU_PROJECTED = ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                    + ISNULL(PPA.PPA_PROJECTED_RPU, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                   NET_SALES_ACTUALS =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                                                                       + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ) ELSE NULL END,
                   NET_SALES_PROJECTED = ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                       + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
                   COGS_ACTUALS =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(SALES.COGS_ACTUALS, 0) ELSE NULL END,
                   COGS_PROJECTED = ISNULL(SALES.COGS_PROJECTED, 0),
                   NET_PROFIT_ACTUALS =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ( ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                                                                            + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ) ) - ( ISNULL(SALES.COGS_ACTUALS, 0) ) ) ELSE NULL END,
                   NET_PROFIT_PROJECTED = ( ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                            + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ),
			 NET_SALES_OF_EX_FACTORY_ACTUALS =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  (COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUAL, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUAL, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100) ELSE NULL END,
             NET_SALES_OF_EX_FACTORY_PROJECTED = (COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100)'
SET @D_SQL=@D_SQL+N'
			,DISCOUNT_OF_EX_FACTORY_ACTUALS =CASE WHEN '''+CAST(@CURRENT_DATE AS NVARCHAR(20))+N''' > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUAL, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUAL, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100 ELSE NULL END,
				DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
            FROM   #DATA_TABLE DT
                    LEFT JOIN (SELECT ACT_AMOUNT_WITHDRAWN = SUM(ACT_AMOUNT_WITHDRAWN),
                                     ACT_UNITS_WITHDRAWN = SUM(ACT_UNITS_WITHDRAWN),
                                     FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(FOR_AMOUNT_WITHDRAWN,ACT_AMOUNT_WITHDRAWN)),
                                     FOR_UNITS_WITHDRAWN = SUM(FOR_UNITS_WITHDRAWN),
                                     PERIOD,
                                     YEAR YEARLY,HIERARCHY_NO,LEVEL_NAME
                              FROM   #INVENTORY I
                                     INNER JOIN #PERIOD P
                                             ON I.PERIOD_SID = P.PERIOD_SID
									 INNER JOIN (SELECT DISTINCT ITEM_MASTER_SID,HIERARCHY_NO,LEVEL_NAME FROM #TEMP_CCP ) X
									 ON X.ITEM_MASTER_SID=I.ITEM_MASTER_SID
                              GROUP  BY PERIOD,
                                        YEAR ,HIERARCHY_NO,LEVEL_NAME) INVENTORY
                          ON DT.PERIOD = INVENTORY.PERIOD
                             AND DT.YEAR = INVENTORY.YEARLY
							AND DT.HIERARCHY_NO = INVENTORY.HIERARCHY_NO
                             AND DT.LEVEL_NAME = INVENTORY.LEVEL_NAME
                   LEFT JOIN (SELECT DEMAND_SALES_ACTUAL = SUM(ACT_GROSS_AMOUNT),
                                     ACT_GROSS_UNITS = SUM(ACT_GROSS_UNITS),
                                     DEMAND_SALES_PROJECTED = SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)),
                                     FOR_GROSS_UNITS = SUM(FOR_GROSS_UNITS),
                                     PERIOD,
                                    YEAR  YEARLY,HIERARCHY_NO,LEVEL_NAME
                              FROM   #DEMAND D
                                     INNER JOIN #PERIOD P
                                             ON D.PERIOD_SID = P.PERIOD_SID
									 INNER JOIN (SELECT DISTINCT ITEM_MASTER_SID,HIERARCHY_NO,LEVEL_NAME FROM #TEMP_CCP ) X
									 ON X.ITEM_MASTER_SID=d.ITEM_MASTER_SID
                              GROUP  BY PERIOD,
                                        YEAR ,HIERARCHY_NO,LEVEL_NAME) DEMAND
                          ON DEMAND.YEARLY = DT.YEAR
                             AND DEMAND.PERIOD = DT.PERIOD
							AND  DT.HIERARCHY_NO = DEMAND.HIERARCHY_NO
                             AND DT.LEVEL_NAME = DEMAND.LEVEL_NAME
                   LEFT JOIN (SELECT GTS_SALES_ACTUALS = SUM(GTS_SALES_ACTUALS),
                                     GTS_SALES_PROJECTED = SUM(GTS_SALES_PROJECTED),
                                     UNITS = SUM(UNITS),
                                     PERIOD,
                                     YEAR YEARLY,HIERARCHY_NO,LEVEL_NAME
                              FROM   #TEMP_GTS_DETAILS G
                                     INNER JOIN #PERIOD P
                                             ON G.PERIOD_SID = P.PERIOD_SID
									 INNER JOIN (SELECT DISTINCT ITEM_MASTER_SID,HIERARCHY_NO,LEVEL_NAME FROM #TEMP_CCP ) X
									 ON X.ITEM_MASTER_SID=G.ITEM_MASTER_SID
                              GROUP  BY PERIOD,
                                        YEAR,HIERARCHY_NO,LEVEL_NAME) GTS
                          ON GTS.PERIOD = DT.PERIOD
                             AND GTS.YEARLY = DT.YEAR
							 AND  DT.HIERARCHY_NO = GTS.HIERARCHY_NO
                             AND DT.LEVEL_NAME = GTS.LEVEL_NAME
                   LEFT JOIN (SELECT CONTRACT_SALES_ACTUALS = ACTUAL_SALES,
                                     CONTRACT_UNITS_ACTUALS = ACTUAL_UNITS,
									 CONTRACT_SALES_PROJECTED = PROJECTION_SALES,
                                     CONTRACT_UNITS_PROJECTED = PROJECTION_UNITS,
                                     COALESCE(ACT.PERIOD,PROJ.PERIOD)PERIOD,
                                     COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
									 COGS_ACTUALS = COGS_ACTUAL,
                                     COGS_PROJECTED =COGS_PROJECTED,
                                     COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO)HIERARCHY_NO,
                                     COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME
							FROM(SELECT ACTUAL_SALES=SUM(ACTUAL_SALES),
									 ACTUAL_UNITS=SUM(ACTUAL_UNITS),
									 COGS_ACTUAL = SUM( Isnull(NPS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ),
									 PERIOD,
                                     [YEAR],
									 HIERARCHY_NO,
                                     LEVEL_NAME
                              FROM   '+@S_ACTUAL_TABLE+N' NPS
                                     INNER JOIN PROJECTION_DETAILS CCP
                                             ON NPS.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                                     INNER JOIN #CCP C
                                             ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
									  INNER JOIN CCP_DETAILS CC
											 ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                      INNER JOIN #ITEM_PRICING U
                                             ON CC.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                AND NPS.PERIOD_SID = U.PERIOD_SID
                                                AND CCP.PROJECTION_MASTER_SID =' +cast(@FIRST_PROJ_SID as varchar(100))+N'
                                               
                                     INNER JOIN #PERIOD P
                                             ON P.PERIOD_SID = NPS.PERIOD_SID
											 GROUP BY  PERIOD,
                                     [YEAR],
									 HIERARCHY_NO,
                                     LEVEL_NAME)ACT
											 FULL JOIN(SELECT 
									 PROJECTION_SALES=SUM(PROJECTION_SALES),
									 PROJECTION_UNITS=SUM(PROJECTION_UNITS),
									  COGS_PROJECTED =SUM( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ),
									 PERIOD,
                                     [YEAR],
									 HIERARCHY_NO,
                                     LEVEL_NAME
                              FROM   '+@S_PROJECTION_TABLE+N' NPS
                                     INNER JOIN PROJECTION_DETAILS CCP
                                             ON NPS.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                                     INNER JOIN #CCP C
                                             ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID	 
											 INNER JOIN CCP_DETAILS CC
											 ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                      INNER JOIN #ITEM_PRICING U
                                             ON CC.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                AND NPS.PERIOD_SID = U.PERIOD_SID
                                                AND CCP.PROJECTION_MASTER_SID =' +cast(@FIRST_PROJ_SID as varchar(100))+N'
                                               
                                     INNER JOIN #PERIOD P
                                             ON P.PERIOD_SID = NPS.PERIOD_SID
											 GROUP BY  PERIOD,
                                     [YEAR],
									 HIERARCHY_NO,
                                     LEVEL_NAME)PROJ
                       ON ACT.PERIOD=PROJ.PERIOD
					   AND ACT.YEAR=PROJ.YEAR
					   AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
					   AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME
                            ) SALES
                          ON SALES.PERIOD = DT.PERIOD
                             AND SALES.[YEAR] = DT.YEAR
                             AND SALES.HIERARCHY_NO = DT.HIERARCHY_NO
                             AND SALES.LEVEL_NAME = DT.LEVEL_NAME
                   LEFT JOIN (SELECT CONTRACT_DISCOUNT_PROJECTED = PROJECTION_SALES,
				   CONTRACT_DISCOUNT_ACTUAL = ACTUAL_SALES,
                                    COALESCE(ACT.PERIOD,PROJ.PERIOD) PERIOD,
                                        COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
                                        COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO) HIERARCHY_NO,
                                        COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME
									 FROM (SELECT ACTUAL_SALES=SUM(ACTUAL_SALES),
									    PERIOD,
                                     [YEAR],
                                     HIERARCHY_NO,
                                     LEVEL_NAME
                              FROM   '+@D_ACTUAL_TABLE+N' NAD
                                     INNER JOIN '+@D_MASTER_TABLE+N' NDPM
                                             ON NDPM.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                AND NDPM.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID
                                     INNER JOIN PROJECTION_DETAILS CCP
                                             ON NDPM.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                                     INNER JOIN #CCP C
                                             ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                     INNER JOIN #PERIOD P
                                             ON P.PERIOD_SID = NAD.PERIOD_SID
                            WHERE  EXISTS (SELECT 1
                                             FROM   PROJECTION_DETAILS PD
                                             WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                    AND PD.PROJECTION_MASTER_SID ='+ cast(@FIRST_PROJ_SID as Nvarchar(100))+N')
                                     AND EXISTS (SELECT 1
                                                 FROM   #DISCOUNT_INFO A
                                                 WHERE  CASE
                                                          WHEN '''+@DISCOUNT_LEVEL+N''' = ''PROGRAM'' THEN CAST(NAD.RS_CONTRACT_SID AS VARCHAR(20))
                                                          ELSE NDPM.PRICE_GROUP_TYPE
                                                        END = A.TOKEN)
														GROUP BY    PERIOD,
                                     [YEAR],
                                     HIERARCHY_NO,
                                     LEVEL_NAME)ACT FULL JOIN(SELECT PROJECTION_SALES=SUM(PROJECTION_SALES),
									    PERIOD,
                                     [YEAR],
                                     HIERARCHY_NO,
                                     LEVEL_NAME
                              FROM   '+@D_PROJECTION_TABLE+N' NDP
                                     INNER JOIN '+@D_MASTER_TABLE+N' NDPM
                                             ON NDPM.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                                     INNER JOIN PROJECTION_DETAILS CCP
                                             ON NDPM.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                                     INNER JOIN #CCP C
                                             ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                     INNER JOIN #PERIOD P
                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                      WHERE  EXISTS (SELECT 1
                                             FROM   PROJECTION_DETAILS PD
                                             WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                    AND PD.PROJECTION_MASTER_SID ='+ cast(@FIRST_PROJ_SID as varchar(100))+N')
                                     AND EXISTS (SELECT 1
                                                 FROM   #DISCOUNT_INFO A
                                                 WHERE  CASE
                                                          WHEN '''+@DISCOUNT_LEVEL+N''' = ''PROGRAM'' THEN CAST(NDP.RS_CONTRACT_SID AS VARCHAR(20))
                                                          ELSE NDPM.PRICE_GROUP_TYPE
                                                        END = A.TOKEN)
														GROUP BY PERIOD,
                                     [YEAR],
                                     HIERARCHY_NO,
                                     LEVEL_NAME)PROJ
					   ON ACT.PERIOD=PROJ.PERIOD
					    AND ACT.YEAR=PROJ.YEAR
					   AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
					   AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME
                                     
                             ) DISC
                          ON DISC.PERIOD = DT.PERIOD
                             AND DISC.[YEAR] = DT.[YEAR]
                             AND DISC.HIERARCHY_NO = DT.HIERARCHY_NO
                             AND DISC.LEVEL_NAME = DT.LEVEL_NAME
                   LEFT JOIN (SELECT PPA_DISCOUNT_PROJECTED = PROJECTION_PPA_SALES,
                                     PPA_PROJECTED_RPU = PROJ.PPA_RPU,
                                     PROJECTION_SALES AS PPA_PROJECTION_SALES,
                                     PROJECTION_UNITS AS PPA_PROJECTION_UNITS,
									 PPA_DISCOUNT_ACTUAL =ACTUAL_PPA_SALES,
                                     PPA_ACTUAL_RPU = ACT.PPA_RPU,
                                     ACTUAL_SALES AS PPA_ACTUAL_SALES,
                                     ACTUAL_UNITS AS PPA_ACTUAL_UNITS,
                                     COALESCE(ACT.PERIOD,PROJ.PERIOD)PERIOD,
                                        COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
                                        COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO)HIERARCHY_NO,
                                        COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME

                              FROM  (SELECT 
                                             PPA_RPU = SUM(ACTUAL_DISCOUNT_DOLLAR),
                                             ACTUAL_PPA_SALES = SUM(ACTUAL_DISCOUNT_DOLLAR),
                                              PERIOD,
                                             [YEAR],
                                             HIERARCHY_NO,
                                             C.LEVEL_NAME,                                            
                                             ACTUAL_SALES = SUM(NS.ACTUAL_SALES),
                                             ACTUAL_UNITS = SUM(NS.ACTUAL_UNITS)
                                      FROM   '+@P_ACTUAL_TABLE+' NPP
                                             INNER JOIN '+@S_ACTUAL_TABLE+N' NS
                                                     ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                        AND NS.PERIOD_SID = NPP.PERIOD_SID
                                             INNER JOIN PROJECTION_DETAILS CCP
                                                     ON NPP.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                                             INNER JOIN #CCP C
                                                     ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                             INNER JOIN #PERIOD P
                                                     ON P.PERIOD_SID = NPP.PERIOD_SID
                                        WHERE  EXISTS (SELECT 1
                                                     FROM   PROJECTION_DETAILS PD
                                                     WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                            AND PD.PROJECTION_MASTER_SID = '+cast(@FIRST_PROJ_SID as nvarchar(100))+N') 
															GROUP BY PERIOD,
                                             [YEAR],
                                             HIERARCHY_NO,
                                             C.LEVEL_NAME ) ACT FULL JOIN  (SELECT 
                                             PPA_RPU = SUM(PROJECTION_DISCOUNT_DOLLAR),
                                             PROJECTION_PPA_SALES = SUM(PROJECTION_DISCOUNT_DOLLAR),
                                              PERIOD,
                                             [YEAR],
                                             HIERARCHY_NO,
                                             C.LEVEL_NAME,
                                             PROJECTION_SALES = SUM(NS.PROJECTION_SALES),
                                             PROJECTION_UNITS = SUM(NS.PROJECTION_UNITS)
                                      FROM   '+@P_PROJECTION_TABLE+' NPP
                                             INNER JOIN '+@S_PROJECTION_TABLE+N' NS
                                                     ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                        AND NS.PERIOD_SID = NPP.PERIOD_SID
                                             INNER JOIN PROJECTION_DETAILS CCP
                                                     ON NPP.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                                             INNER JOIN #CCP C
                                                     ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                             INNER JOIN #PERIOD P
                                                     ON P.PERIOD_SID = NPP.PERIOD_SID
                                         WHERE  EXISTS (SELECT 1
                                                     FROM   PROJECTION_DETAILS PD
                                                     WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                            AND PD.PROJECTION_MASTER_SID = '+cast(@FIRST_PROJ_SID as nvarchar(100))+')
															GROUP BY
															PERIOD,
                                             [YEAR],
                                             HIERARCHY_NO,
                                             C.LEVEL_NAME) PROJ

						ON ACT.PERIOD=PROJ.PERIOD
					    AND ACT.YEAR=PROJ.YEAR
					   AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
					   AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME
                           ) PPA
                          ON PPA.PERIOD = DT.PERIOD
                             AND PPA.[YEAR] = DT.[YEAR]
                             AND PPA.HIERARCHY_NO = DT.HIERARCHY_NO
                             AND PPA.LEVEL_NAME = DT.LEVEL_NAME
            ORDER  BY DT.PERIOD,
                      DT.[YEAR],
                      DT.HIERARCHY_NO'
					EXEC sp_executesql @D_SQL

					 IF EXISTS (SELECT 1
                       FROM   #PROJECTION_MASTER
                       WHERE  ID > 1)
              BEGIN
                  IF Object_id('TEMPDB..#PRIOR_PROJECTIONS') IS NOT NULL
                    DROP TABLE #PRIOR_PROJECTIONS--------------------prior projection inforamtion-----------------

                  SELECT PD.PROJECTION_DETAILS_SID,
                         PM.PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NAME
                  INTO   #PRIOR_PROJECTIONS
                  FROM   #PROJECTION_MASTER PM
                         INNER JOIN PROJECTION_DETAILS PD
                                 ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                         INNER JOIN #CCP C
                                 ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                  WHERE  PM.ID <> 1

                  IF Object_id('TEMPDB..#PRIOR_DATA_TABLE') IS NOT NULL
                    DROP TABLE #PRIOR_DATA_TABLE-------------------prior projection hierarchy inforamtion-----------------

                  SELECT PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NAME,
                         PERIOD,
                         YEAR
                  INTO   #PRIOR_DATA_TABLE
                  FROM   (SELECT DISTINCT HIERARCHY_NO,
                                          LEVEL_NAME
                          FROM   #CCP) C
                         CROSS JOIN (SELECT DISTINCT PERIOD,
                                                     YEAR
                                     FROM   #PERIOD
                                     WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID) P
                         CROSS JOIN #PROJECTION_MASTER PM
                  WHERE  PM.ID <> 1

				  if object_id('tempdb..#CURRENT_CPP_COMP_PRIOR_CPP') is not null
drop table #CURRENT_CPP_COMP_PRIOR_CPP----------------------ccp related information

		  SELECT  PM.PROJECTION_MASTER_SID,
		  CC.CCP_DETAILS_SID
         INTO #CURRENT_CPP_COMP_PRIOR_CPP FROM   #PROJECTION_MASTER PM
		 INNER JOIN PROJECTION_DETAILS PD
		 ON PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
		 INNER JOIN CCP_DETAILS CC
		 ON PD.CCP_DETAILS_SID=CC.CCP_DETAILS_SID
          WHERE  ID = 1
		  -------------take the data from main tables and insert in the temp table-----------------------
		      INSERT INTO #PIVOT_RESULT
                        (PROJECTION_ID,
                         LEVEL_NAME,
                         HIERARCHY_NO,
                         CP_INDICATOR,
                         PERIOD,
                         YEAR,
						 EX_FACTORY_SALES_ACTUALS,
                         EX_FACTORY_SALES_PROJECTED,
						 DEMAND_SALES_ACTUALS,
                         DEMAND_SALES_PROJECTED,
						 INVENTORY_WITHDRAWAL_SALES_ACTUALS,
                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
						 EX_FACTORY_SALES_ACTUALS_PERCENT,
                         EX_FACTORY_SALES_PROJECTED_PERCENT,
						 DEMAND_SALES_ACTUALS_PERCENT,
                         DEMAND_SALES_PROJECTED_PERCENT,
						 INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT,
                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
						 CONTRACT_SALES_ACTUALS,
                         CONTRACT_SALES_PROJECTED,
						 CONTRACT_UNITS_ACTUALS,
                         CONTRACT_UNITS_PROJECTED,
						 TOTAL_DISCOUNT_ACTUALS,
                         TOTAL_DISCOUNT_PROJECTED,
						 TOTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
						 TOTAL_RPU_ACTUALS,
                         TOTAL_RPU_PROJECTED,
						 NET_SALES_ACTUALS,
                         NET_SALES_PROJECTED,
						 COGS_ACTUALS,
                         COGS_PROJECTED,
						 NET_PROFIT_ACTUALS,
                         NET_PROFIT_PROJECTED
						 , NET_SALES_OF_EX_FACTORY_ACTUALS ,
                         NET_SALES_OF_EX_FACTORY_PROJECTED
						 ,DISCOUNT_OF_EX_FACTORY_ACTUALS
						 ,DISCOUNT_OF_EX_FACTORY_PROJECTED)
       
                  SELECT PDT.PROJECTION_MASTER_SID PROJECTION_ID,
                         PDT.LEVEL_NAME,
                         PDT.HIERARCHY_NO,
                         @CP_INDICATOR,
                         PDT.PERIOD,
                         PDT.YEAR,
                         ISNULL(GTS.GTS_SALES_ACTUALS, 0) AS EX_FACTORY_SALES_ACTUALS,
						 ISNULL(GTS.GTS_SALES_PROJECTED, 0) as EX_FACTORY_SALES_PROJECTED ,
                         ISNULL(GTS.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS ,
						 ISNULL(GTS.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED ,
                         ISNULL(GTS.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_ACTUALS,
						 ISNULL(GTS.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED ,
                         (ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT ,
						 (ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
						 (ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUALS_PERCENT,
                         (ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT ,
						  ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT ,
                        ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS  INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT  ,
                          ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS ,
						 ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED ,
						  ISNULL(CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS , 
                          ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED ,
						  Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS TOTAL_DISCOUNT ,
                         ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                    + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED ,
						 ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS  TOTAL_DISCOUNT_PERCENTAGE ,
                         ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                          + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS  TOTAL_DISCOUNT_PROJECTED_PERCENTAGE ,
						  ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_ACTUALS_RPU, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUAL ,
                         ( ISNULL(( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                          + ISNULL(PPA.PPA_PROJECTED_RPU, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED ,
						 ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_ACTUALS_SALES, 0) ) ) AS  NET_SALES ,
                          ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                             + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ) AS NET_SALES_PROJECTED ,
						  Isnull(SALES.COGS_ACTUALS, 0) AS COGS_ACTUALS ,
                         ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED ,
						   ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                       + Isnull(PPA.PPA_ACTUALS_SALES, 0) ) ) - ( Isnull(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL ,
                          ( ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                  + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED ,
                           (COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100) AS NET_SALES_OF_EX_FACTORY_ACTUALS ,
                             (COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100) AS  NET_SALES_OF_EX_FACTORY_PROJECTED 
					, COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100 AS DISCOUNT_OF_EX_FACTORY_ACTUALS ,
							 COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100 AS  DISCOUNT_OF_EX_FACTORY_PROJECTED
                  FROM   #PRIOR_DATA_TABLE PDT
                         LEFT JOIN (SELECT PF.PROJECTION_MASTER_SID,SUM(INVENTORY_ACTUAL_SALES) as ACT_AMOUNT_WITHDRAWN,
                                       SUM(INVENTORY_ACTUAL_UNITS) AS ACT_UNITS_WITHDRAWN,
                                      SUM(COALESCE(INVENTORY_FORECAST_SALES,INVENTORY_ACTUAL_SALES)) AS FOR_AMOUNT_WITHDRAWN ,
                                      SUM(INVENTORY_FORECAST_UNITS) AS FOR_UNITS_WITHDRAWN ,
									 SUM(DEMAND_ACTUAL_SALES) AS DEMAND_SALES_ACTUAL,
                                     SUM(DEMAND_ACTUAL_UNITS) AS ACT_GROSS_UNITS ,
                                     SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)) AS  DEMAND_SALES_PROJECTED ,
                                      SUM(DEMAND_FORECAST_UNITS) AS FOR_GROSS_UNITS ,
									 SUM(EXFACTORY_ACTUAL_SALES) AS  GTS_SALES_ACTUALS ,
                                      SUM(COALESCE(EXFACTORY_FORECAST_SALES,EXFACTORY_ACTUAL_SALES)) AS GTS_SALES_PROJECTED ,
                                      SUM(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)) AS UNITS ,
                                     PERIOD,
                                     YEAR AS YEARLY,HIERARCHY_NO,LEVEL_NAME
                              FROM   #PRODUCT_FILE_TEMP PF JOIN #PERIOD P ON PF.PERIOD_SID=P.PERIOD_SID
							  INNER JOIN (SELECT DISTINCT ITEM_MASTER_SID,HIERARCHY_NO,LEVEL_NAME FROM #TEMP_CCP ) X
									 ON X.ITEM_MASTER_SID=PF.ITEM_MASTER_SID
							  GROUP BY PF.PROJECTION_MASTER_SID,PERIOD,YEAR,HIERARCHY_NO,LEVEL_NAME) GTS
                                 ON PDT.PROJECTION_MASTER_SID=GTS.PROJECTION_MASTER_SID AND PDT.[YEAR] = GTS.YEARLY
                                   AND PDT.PERIOD = GTS.PERIOD
								   AND PDT.HIERARCHY_NO = GTS.HIERARCHY_NO
                                   AND PDT.LEVEL_NAME = GTS.LEVEL_NAME
                         LEFT JOIN (SELECT  PROJECTION_SALES AS CONTRACT_SALES_PROJECTED ,
                                           PROJECTION_UNITS AS CONTRACT_UNITS_PROJECTED ,
										    ACTUAL_SALES AS CONTRACT_SALES_ACTUALS ,
                                            ACTUAL_UNITS AS CONTRACT_UNITS_ACTUALS ,
										   COGS_ACTUALS AS COGS_ACTUALS ,
                                           COGS_PROJECTED AS COGS_PROJECTED ,
										   COALESCE(ACT.PERIOD,PROJ.PERIOD)PERIOD,
                                           COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
                                           COALESCE(ACT.PROJECTION_MASTER_SID,PROJ.PROJECTION_MASTER_SID)PROJECTION_MASTER_SID,
                                           COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO)HIERARCHY_NO,
                                           COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME
                                    FROM  (SELECT sum(ACTUAL_SALES) AS ACTUAL_SALES,
									sum(ACTUAL_UNITS) AS ACTUAL_UNITS,
									sum( Isnull(NPS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ) AS COGS_ACTUALS
									,PERIOD
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAME
									FROM NM_ACTUAL_SALES NPS
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                           INNER JOIN CCP_DETAILS CCP
                                                   ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
										   INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
										   ON CCP.CCP_DETAILS_SID=CC.CCP_DETAILS_SID
                                           INNER JOIN #ITEM_PRICING U
                                                   ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                      AND NPS.PERIOD_SID = U.PERIOD_SID
                                           INNER JOIN #PRIOR_PROJECTIONS PP
                                                   ON PP.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
												    AND PP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NPS.PERIOD_SID
												   group by PERIOD
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAME)ACT 
												   FULL JOIN (SELECT sum(PROJECTION_SALES) AS PROJECTION_SALES,
									sum(PROJECTION_UNITS) AS PROJECTION_UNITS,
									sum( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ) AS COGS_PROJECTED
									,PERIOD
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAME
									FROM NM_SALES_PROJECTION NPS
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                           INNER JOIN CCP_DETAILS CCP
                                                   ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
											INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
										   ON CCP.CCP_DETAILS_SID=CC.CCP_DETAILS_SID
                                           INNER JOIN #ITEM_PRICING U
                                                   ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                      AND NPS.PERIOD_SID = U.PERIOD_SID
                                           INNER JOIN #PRIOR_PROJECTIONS PP
                                                    ON PP.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
												    AND PP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NPS.PERIOD_SID
												   group by PERIOD
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAME)PROJ
												   ON ACT.PROJECTION_MASTER_SID=PROJ.PROJECTION_MASTER_SID
												   AND ACT.PERIOD=PROJ.PERIOD
												   AND ACT.YEAR=PROJ.YEAR
												   AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
												   AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME
                                    ) SALES
                                ON SALES.PERIOD = PDT.PERIOD
                                   AND SALES.[YEAR] = PDT.YEAR
                                   AND SALES.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND SALES.LEVEL_NAME = PDT.LEVEL_NAME
                                   AND SALES.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID
                         LEFT JOIN (SELECT PROJECTION_SALES AS CONTRACT_DISCOUNT_PROJECTED,
						                    ACTUAL_SALES AS CONTRACT_DISCOUNT_ACTUALS ,
                                            COALESCE(ACT.PERIOD,PROJ.PERIOD)PERIOD,
                                           COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
                                           COALESCE(ACT.PROJECTION_MASTER_SID,PROJ.PROJECTION_MASTER_SID)PROJECTION_MASTER_SID,
                                           COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO)HIERARCHY_NO,
                                           COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME
										   FROM(SELECT sum(ACTUAL_SALES) AS ACTUAL_SALES,PERIOD
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAME
                                    FROM   NM_ACTUAL_DISCOUNT NDP
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
											INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
										   ON PD.CCP_DETAILS_SID=CC.CCP_DETAILS_SID
                                           INNER JOIN #PRIOR_PROJECTIONS PP
                                                   ON PP.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
												    AND PP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NDP.PERIOD_SID
												   group by PERIOD
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAME)ACT FULL JOIN(SELECT sum(PROJECTION_SALES) AS PROJECTION_SALES,PERIOD
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAME
                                    FROM   NM_DISCOUNT_PROJECTION NDP
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
												   INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
										   ON PD.CCP_DETAILS_SID=CC.CCP_DETAILS_SID
                                           INNER JOIN #PRIOR_PROJECTIONS PP
                                                    ON PP.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
												    AND PP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NDP.PERIOD_SID
												   group by PERIOD
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAME)PROJ
												     ON ACT.PROJECTION_MASTER_SID=PROJ.PROJECTION_MASTER_SID
												   AND ACT.PERIOD=PROJ.PERIOD
												   AND ACT.YEAR=PROJ.YEAR
												   AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
												   AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME
                                  ) DISC
                                ON DISC.PERIOD = PDT.PERIOD
                                   AND DISC.[YEAR] = PDT.YEAR
                                   AND DISC.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND DISC.LEVEL_NAME = PDT.LEVEL_NAME
                                   AND DISC.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID
                         LEFT JOIN (SELECT  PROJECTION_DISCOUNT_DOLLAR AS PPA_DISCOUNT_PROJECTED ,
                                           PROJECTION_DISCOUNT_DOLLAR AS PPA_PROJECTED_RPU ,
                                            PROJECTION_SALES AS PPA_PROJECTION_SALES ,
                                          PROJECTION_UNITS AS  PPA_PROJECTION_UNITS ,
										   ACTUAL_DISCOUNT_DOLLAR AS  PPA_DISCOUNT_ACTUALS ,
                                           ACTUAL_DISCOUNT_DOLLAR AS PPA_ACTUALS_RPU ,
                                           ACTUAL_SALES AS PPA_ACTUALS_SALES ,
                                            ACTUAL_UNITS AS PPA_ACTUALS_UNITS ,
                                            COALESCE(ACT.PERIOD,PROJ.PERIOD)PERIOD,
                                           COALESCE(ACT.[YEAR],PROJ.YEAR)YEAR,
                                           COALESCE(ACT.PROJECTION_MASTER_SID,PROJ.PROJECTION_MASTER_SID)PROJECTION_MASTER_SID,
                                           COALESCE(ACT.HIERARCHY_NO,PROJ.HIERARCHY_NO)HIERARCHY_NO,
                                           COALESCE(ACT.LEVEL_NAME,PROJ.LEVEL_NAME)LEVEL_NAME
										   FROM(SELECT sum(ACTUAL_DISCOUNT_DOLLAR) AS ACTUAL_DISCOUNT_DOLLAR,sum(NS.ACTUAL_SALES) AS ACTUAL_SALES, PERIOD,sum(NS.ACTUAL_UNITS) AS ACTUAL_UNITS
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAME
										   FROM NM_ACTUAL_PPA NPP
                                           INNER JOIN NM_ACTUAL_SALES NS
                                                   ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                      AND NS.PERIOD_SID = NPP.PERIOD_SID
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
												   INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
										   ON PD.CCP_DETAILS_SID=CC.CCP_DETAILS_SID
                                           INNER JOIN #PRIOR_PROJECTIONS PP
                                                   ON PP.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
												    AND PP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NPP.PERIOD_SID
                                                      AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                       FROM   #PROJECTION_MASTER PM
                                                                                       WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
																					    group by PERIOD
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAMe )ACT
																					   FULL JOIN
																					   
                              (   SELECT sum(PROJECTION_DISCOUNT_DOLLAR) AS PROJECTION_DISCOUNT_DOLLAR,PERIOD,sum(NS.PROJECTION_SALES) AS PROJECTION_SALES,sum(NS.PROJECTION_UNITS) AS PROJECTION_UNITS
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAME  FROM   NM_PPA_PROJECTION NPP
                                           INNER JOIN NM_SALES_PROJECTION NS
                                                   ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                      AND NS.PERIOD_SID = NPP.PERIOD_SID
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
												   INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
										   ON PD.CCP_DETAILS_SID=CC.CCP_DETAILS_SID
                                           INNER JOIN #PRIOR_PROJECTIONS PP
                                                    ON PP.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
												    AND PP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = NPP.PERIOD_SID
                                                      AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                       FROM   #PROJECTION_MASTER PM
                                                                                       WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
																					     group by PERIOD
									,YEAR,
									PP.PROJECTION_MASTER_SID,
									HIERARCHY_NO,
									LEVEL_NAMe )PROJ
																					    ON ACT.PROJECTION_MASTER_SID=PROJ.PROJECTION_MASTER_SID
												   AND ACT.PERIOD=PROJ.PERIOD
												   AND ACT.YEAR=PROJ.YEAR
												   AND ACT.HIERARCHY_NO=PROJ.HIERARCHY_NO
												   AND ACT.LEVEL_NAME=PROJ.LEVEL_NAME
                                   ) PPA
                                ON PPA.PERIOD = PDT.PERIOD
                                   AND PPA.[YEAR] = PDT.YEAR
                                   AND PPA.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND PPA.LEVEL_NAME = PDT.LEVEL_NAME
                                   AND PPA.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID
              END
       DECLARE @SQL       NVARCHAR(MAX),
                    @LOOP_CNTR INT,
                    @MAX_CCP   INT

            SET @SQL = N'
SELECT pr.LEVEL_NAME,
                   pr.HIERARCHY_NO,
                   CP_INDICATOR,
                   PERIOD,
                   YEAR, '
            SET @LOOP_CNTR = 1
            SET @MAX_CCP = (SELECT Max(ID)
                            FROM   #PROJECTION_MASTER)

            WHILE @LOOP_CNTR <= @MAX_CCP
              BEGIN
                   SET @SQL += N' EX_FACTORY_SALES_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN EX_FACTORY_SALES_ACTUALS END),EX_FACTORY_SALES_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN EX_FACTORY_SALES_PROJECTED END),0),
                    DEMAND_SALES_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + N' THEN DEMAND_SALES_ACTUALS END),
                    DEMAND_SALES_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN DEMAND_SALES_PROJECTED END),0),
					INVENTORY_WITHDRAWAL_SALES_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUALS END),
					INVENTORY_WITHDRAWAL_SALES_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED END),0),
                    EX_FACTORY_SALES_ACTUALS_PERCENT_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN EX_FACTORY_SALES_ACTUALS_PERCENT END),
                    EX_FACTORY_SALES_PROJECTED_PERCENT_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN EX_FACTORY_SALES_PROJECTED_PERCENT END),0),
                    DEMAND_SALES_ACTUALS_PERCENT_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN DEMAND_SALES_ACTUALS_PERCENT END),
                    DEMAND_SALES_PROJECTED_PERCENT_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN DEMAND_SALES_PROJECTED_PERCENT END),0),
                    INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT END),
                    INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT END),0),
                    CONTRACT_SALES_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN CONTRACT_SALES_ACTUALS END),
                    CONTRACT_SALES_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN CONTRACT_SALES_PROJECTED END),0),
                    CONTRACT_UNITS_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN CONTRACT_UNITS_ACTUALS END),
                    CONTRACT_UNITS_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN CONTRACT_UNITS_PROJECTED END),0),
                    TOTAL_DISCOUNT_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN TOTAL_DISCOUNT_ACTUALS END),
                    TOTAL_DISCOUNT_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN TOTAL_DISCOUNT_PROJECTED END),0),
                    TOTAL_DISCOUNT_ACTUALS_PERCENTAGE_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN TOTAL_DISCOUNT_ACTUALS_PERCENTAGE END),
                    TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                    TOTAL_RPU_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN TOTAL_RPU_ACTUALS END),
                    TOTAL_RPU_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN TOTAL_RPU_PROJECTED END),0),
                    NET_SALES_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN NET_SALES_ACTUALS END),
                    NET_SALES_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN NET_SALES_PROJECTED END),0),
                    COGS_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN COGS_ACTUALS END),
                    COGS_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN COGS_PROJECTED END),0),
                    NET_PROFIT_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN NET_PROFIT_ACTUALS END),
                    NET_PROFIT_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN NET_PROFIT_PROJECTED END),0),
                    NET_SALES_OF_EX_FACTORY_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN NET_SALES_OF_EX_FACTORY_ACTUALS END),
                    NET_SALES_OF_EX_FACTORY_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                        + ' THEN  NET_SALES_OF_EX_FACTORY_PROJECTED END),0),
                    DISCOUNT_OF_EX_FACTORY_ACTUALS_'
                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                        + ' = MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                        + ' THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),
                     DISCOUNT_OF_EX_FACTORY_PROJECTED_'
                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                        + ' = ISNULL(MAX(CASE WHEN ID = '
                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                        + ' THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0), '
                  SET @LOOP_CNTR += 1
              END

            SET @SQL = LEFT(@SQL, Len(@SQL) - 1)
            SET @SQL += N'  FROM   #PIVOT_RESULT PR JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID  = PR.PROJECTION_ID
			  join #CCP c on pr.HIERARCHY_NO = c.HIERARCHY_NO

                GROUP  BY    PR.LEVEL_NAME,PR.HIERARCHY_NO,CP_INDICATOR,PERIOD,YEAR		 				
                ORDER  BY PR.HIERARCHY_NO,[YEAR],PERIOD	'

		
            EXEC Sp_executesql
              @SQL
enD

 ELSE
        BEGIN	

			           IF Object_id('TEMPDB..#FILE_DATA') IS NOT NULL
                     DROP TABLE #FILE_DATA--------------taking active gts file inforamtion-----------------
             

					 SELECT P.PERIOD,P.YEAR, SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS ,
                                  SUM(COALESCE(GTS_SALES_PROJECTED, GTS_SALES_ACTUALS)) AS GTS_SALES_PROJECTED ,HIERARCHY_NO,LEVEL_NAME
                                 
                            into #FILE_DATA  FROM   
			#TEMP_GTS_DETAILS
			 PF JOIN #PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 		
			INNER JOIN (SELECT DISTINCT ITEM_MASTER_SID,HIERARCHY_NO,LEVEL_NAME FROM #TEMP_CCP ) X
									 ON X.ITEM_MASTER_SID=PF.ITEM_MASTER_SID	 
                              GROUP  BY P.PERIOD,P.YEAR,HIERARCHY_NO,LEVEL_NAME
 
            IF Object_id('TEMPDB..#DISCOUNT_DATA_TABLE') IS NOT NULL
              DROP TABLE #DISCOUNT_DATA_TABLE----------taking discount related information-----------------

            SELECT HIERARCHY_NO,
                   LEVEL_NAME,
                   PERIOD,
                   YEAR,
                   DISCOUNT
            INTO   #DISCOUNT_DATA_TABLE
            FROM   (SELECT DISTINCT HIERARCHY_NO,
                                    LEVEL_NAME
                    FROM   #CCP) C
                   CROSS JOIN (SELECT DISTINCT PERIOD,
                                               YEAR
                               FROM   #PERIOD
                               WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID) P
                   CROSS JOIN (SELECT DISCOUNT
                               FROM   #DISCOUNT_INFO) D


            IF Object_id('TEMPDB..#SALES_ACTUAL_DATA') IS NOT NULL
              DROP TABLE #SALES_ACTUAL_DATA------------taking sales related information--------------

            CREATE TABLE #SALES_ACTUAL_DATA
              (
                 CONTRACT_SALES_ACTUALS NUMERIC(22, 6),
                 CONTRACT_UNITS_ACTUALS NUMERIC(22, 6),
                 PERIOD                   VARCHAR(50),
                 [YEAR]                   INT,
                 HIERARCHY_NO             VARCHAR(100),
                 LEVEL_NAME               VARCHAR(100)
              )

            SET @D_SQL=Concat('INSERT INTO #SALES_ACTUAL_DATA(
			CONTRACT_SALES_ACTUALS ,
			CONTRACT_UNITS_ACTUALS, 
			PERIOD  ,                 
			[YEAR],                   
			HIERARCHY_NO,			
			LEVEL_NAME               
			)
            SELECT CONTRACT_SALES_ACTUALS = SUM(ACTUAL_SALES),
                   CONTRACT_UNITS_ACTUALS = SUM(ACTUAL_UNITS),
                   PERIOD,
                   [YEAR],
                   HIERARCHY_NO,
                   LEVEL_NAME
           
            FROM   ', @S_ACTUAL_TABLE, ' NPS
                   INNER JOIN PROJECTION_DETAILS CCP
                           ON NPS.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                   INNER JOIN #CCP C
                           ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = NPS.PERIOD_SID
						   WHERE CCP.PROJECTION_MASTER_SID=@FIRST_PROJ_SID
            GROUP  BY PERIOD,
                      [YEAR],
                      HIERARCHY_NO,
                      LEVEL_NAME')
				
           	EXEC Sp_executesql
			@D_SQL,
			N'@FIRST_PROJ_SID INT',
			@FIRST_PROJ_SID = @FIRST_PROJ_SID


			IF Object_id('TEMPDB..#SALES_DATA') IS NOT NULL
              DROP TABLE #SALES_DATA--------------taking sales related information--------------

            CREATE TABLE #SALES_DATA
              (
                 CONTRACT_SALES_PROJECTED NUMERIC(22, 6),
                 CONTRACT_UNITS_PROJECTED NUMERIC(22, 6),
                 PERIOD                   VARCHAR(50),
                 [YEAR]                   INT,
                 HIERARCHY_NO             VARCHAR(100),
                 LEVEL_NAME               VARCHAR(100)
              )

            SET @D_SQL=Concat('INSERT INTO #SALES_DATA(
			CONTRACT_SALES_PROJECTED ,
			CONTRACT_UNITS_PROJECTED, 
			PERIOD  ,                 
			[YEAR],                   
			HIERARCHY_NO,			
			LEVEL_NAME               
			)
            SELECT CONTRACT_SALES_PROJECTED = SUM(PROJECTION_SALES),
                   CONTRACT_UNITS_PROJECTED = SUM(PROJECTION_UNITS),
                   PERIOD,
                   [YEAR],
                   HIERARCHY_NO,
                   LEVEL_NAME
           
            FROM   ', @S_PROJECTION_TABLE, ' NPS
                   INNER JOIN PROJECTION_DETAILS CCP
                           ON NPS.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                   INNER JOIN #CCP C
                           ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = NPS.PERIOD_SID
						   WHERE CCP.PROJECTION_MASTER_SID=@FIRST_PROJ_SID
            GROUP  BY PERIOD,
                      [YEAR],
                      HIERARCHY_NO,
                      LEVEL_NAME')
				
           	EXEC Sp_executesql
			@D_SQL,
			N'@FIRST_PROJ_SID INT',
			@FIRST_PROJ_SID = @FIRST_PROJ_SID

		 IF Object_id('TEMPDB..#DISCOUNT_ACTUAL_DATA') IS NOT NULL
              DROP TABLE #DISCOUNT_ACTUAL_DATA
---------------taking discount related information-----------------
            CREATE TABLE #DISCOUNT_ACTUAL_DATA
              (
                 CONTRACT_SALES_ACTUALS NUMERIC(22, 6),
                 DISCOUNT                 VARCHAR(50),
                 HIERARCHY_NO             VARCHAR(100),
                 LEVEL_NAME               VARCHAR(100),
                 PERIOD                   VARCHAR(50),
                 [YEAR]                   INT
              )

            SET @D_SQL=Concat('	INSERT INTO #DISCOUNT_ACTUAL_DATA
			(
			CONTRACT_SALES_ACTUALS ,
			DISCOUNT                 ,
			HIERARCHY_NO			 ,
			LEVEL_NAME               ,
			PERIOD                   ,
			[YEAR]                   
			)
            SELECT CONTRACT_SALES_ACTUALS = SUM(ACTUAL_SALES),
                   CASE
                     WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN RS_NAME
                     ELSE NDPM.PRICE_GROUP_TYPE
                   END AS DISCOUNT,
                   C.HIERARCHY_NO,
                   C.LEVEL_NAME,
                   P.PERIOD,
                   P.YEAR
          
            FROM   ', @D_ACTUAL_TABLE, ' NDP
                   INNER JOIN ', @D_MASTER_TABLE, ' NDPM
                           ON NDPM.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
						   AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                   INNER JOIN RS_CONTRACT R
                           ON R.RS_CONTRACT_SID = NDPM.RS_CONTRACT_SID   
                   INNER JOIN PROJECTION_DETAILS  CCP
                           ON NDPM.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                   INNER JOIN #CCP C
                           ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = NDP.PERIOD_SID
            WHERE  EXISTS (SELECT 1
                               FROM   #DISCOUNT_INFO A
                               WHERE  CASE
                                        WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN CAST(NDP.RS_CONTRACT_SID AS VARCHAR(20))
                                        ELSE NDPM.PRICE_GROUP_TYPE
                                      END = A.TOKEN)
									  AND CCP.PROJECTION_MASTER_SID=@FIRST_PROJ_SID
                   
            GROUP  BY CASE
                        WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN RS_NAME
                        ELSE NDPM.PRICE_GROUP_TYPE
                      END,
                      C.HIERARCHY_NO,
                      C.LEVEL_NAME,
                      P.PERIOD,
                      P.YEAR')

          	EXEC Sp_executesql
			@D_SQL,
			N'@FIRST_PROJ_SID INT',
			@FIRST_PROJ_SID = @FIRST_PROJ_SID


---------------taking discount related information-----------------
            IF Object_id('TEMPDB..#DISCOUNT_DATA') IS NOT NULL
              DROP TABLE #DISCOUNT_DATA

            CREATE TABLE #DISCOUNT_DATA
              (
                 CONTRACT_SALES_PROJECTED NUMERIC(22, 6),
                 DISCOUNT                 VARCHAR(50),
                 HIERARCHY_NO             VARCHAR(100),
                 LEVEL_NAME               VARCHAR(100),
                 PERIOD                   VARCHAR(50),
                 [YEAR]                   INT
              )

            SET @D_SQL=Concat('	INSERT INTO #DISCOUNT_DATA
			(
			CONTRACT_SALES_PROJECTED ,
			DISCOUNT                 ,
			HIERARCHY_NO			 ,
			LEVEL_NAME               ,
			PERIOD                   ,
			[YEAR]                   
			)
            SELECT CONTRACT_SALES_PROJECTED = SUM(PROJECTION_SALES),
                   CASE
                     WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN RS_NAME
                     ELSE NDPM.PRICE_GROUP_TYPE
                   END AS DISCOUNT,
                   C.HIERARCHY_NO,
                   C.LEVEL_NAME,
                   P.PERIOD,
                   P.YEAR
          
            FROM   ', @D_PROJECTION_TABLE, ' NDP
                   INNER JOIN ', @D_MASTER_TABLE, ' NDPM
                           ON NDPM.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
						   AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                   INNER JOIN RS_CONTRACT R
                           ON R.RS_CONTRACT_SID = NDPM.RS_CONTRACT_SID   
                   INNER JOIN PROJECTION_DETAILS  CCP
                           ON NDPM.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                   INNER JOIN #CCP C
                           ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID = NDP.PERIOD_SID
            WHERE  EXISTS (SELECT 1
                               FROM   #DISCOUNT_INFO A
                               WHERE  CASE
                                        WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN CAST(NDP.RS_CONTRACT_SID AS VARCHAR(20))
                                        ELSE NDPM.PRICE_GROUP_TYPE
                                      END = A.TOKEN)
									  AND CCP.PROJECTION_MASTER_SID=@FIRST_PROJ_SID
                   
            GROUP  BY CASE
                        WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN RS_NAME
                        ELSE NDPM.PRICE_GROUP_TYPE
                      END,
                      C.HIERARCHY_NO,
                      C.LEVEL_NAME,
                      P.PERIOD,
                      P.YEAR')

			
      	EXEC Sp_executesql
			@D_SQL,
			N'@FIRST_PROJ_SID INT',
			@FIRST_PROJ_SID = @FIRST_PROJ_SID

	
                 
---------------taking ppa discount related information-----------------            
                         IF Object_id('TEMPDB..#PPA_ACTUAL_DATA') IS NOT NULL
              DROP TABLE #PPA_ACTUAL_DATA

            CREATE TABLE #PPA_ACTUAL_DATA
              (
                 CONTRACT_SALES_ACTUALS NUMERIC(22, 6),
                 PPA_ACTUALS_RATE       NUMERIC(22, 6),
                 PPA_ACTUALS_RPU        NUMERIC(22, 6),
                 DISCOUNT                 VARCHAR(50),
                 HIERARCHY_NO             VARCHAR(100),
                 LEVEL_NAME               VARCHAR(100),
                 PERIOD                   VARCHAR(50),
                 [YEAR]                   INT
              )

            SET @D_SQL=Concat('INSERT INTO #PPA_ACTUAL_DATA
			(
			CONTRACT_SALES_ACTUALS,
			PPA_ACTUALS_RATE      ,
			PPA_ACTUALS_RPU       ,
			DISCOUNT                ,
			HIERARCHY_NO			,
			LEVEL_NAME              ,
			PERIOD                  ,
			[YEAR]                  
			)
            SELECT CONTRACT_SALES_ACTUALS = SUM(ACTUAL_DISCOUNT_DOLLAR),
                   PPA_ACTUALS_RATE = SUM(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(SUM(ACTUAL_SALES), 0) * 100,
                   PPA_ACTUALS_RPU = SUM(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(SUM(ACTUAL_UNITS), 0),
                   DISCOUNT,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PERIOD,
                   YEAR
       
            FROM   (SELECT NPP.PROJECTION_DETAILS_SID,
                           SUM(ACTUAL_DISCOUNT_DOLLAR) ACTUAL_DISCOUNT_DOLLAR,
                           SUM(NM.ACTUAL_SALES)        ACTUAL_SALES,
                           SUM(NM.ACTUAL_UNITS)        ACTUAL_UNITS,
                           CASE
                             WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN RS_NAME
                             ELSE ''PRICE PROTECTION''
                           END                             AS DISCOUNT,
                           C.HIERARCHY_NO,
                           C.LEVEL_NAME,
                           P.PERIOD,
                           P.YEAR
                    FROM   ', @P_ACTUAL_TABLE, ' NPP
                           INNER JOIN ', @S_ACTUAL_TABLE, ' NM
                                   ON NM.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                      AND NM.PERIOD_SID = NPP.PERIOD_SID
                           INNER JOIN ', @P_MASTER_TABLE, ' NDPM
                                   ON NPP.PROJECTION_DETAILS_SID = NDPM.PROJECTION_DETAILS_SID
                           INNER JOIN RS_CONTRACT R
                                   ON R.RS_CONTRACT_SID = NPP.RS_CONTRACT_SID          
                           INNER JOIN PROJECTION_DETAILS CCP
                                   ON NM.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                           INNER JOIN #CCP C
                                   ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                           INNER JOIN #PERIOD P
                                   ON P.PERIOD_SID = NPP.PERIOD_SID
                    WHERE  EXISTS (SELECT 1
                                       FROM   #DISCOUNT_INFO A
                                       WHERE  CASE
                                                WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN CAST(NPP.RS_CONTRACT_SID AS VARCHAR(20))
                                                ELSE ''PRICE PROTECTION''
                                              END = A.TOKEN)
											  AND CCP.PROJECTION_MASTER_SID=@FIRST_PROJ_SID
                           
                    GROUP  BY NPP.PROJECTION_DETAILS_SID,
                              CASE
                                WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN RS_NAME
                                ELSE ''PRICE PROTECTION''
                              END,
                              C.HIERARCHY_NO,
                              C.LEVEL_NAME,
                              P.PERIOD,
                              P.YEAR) A
            GROUP  BY DISCOUNT,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PERIOD,
                      YEAR')

          	EXEC Sp_executesql
			@D_SQL,
			N'@FIRST_PROJ_SID INT',
			@FIRST_PROJ_SID = @FIRST_PROJ_SID
---------------taking ppa discount related information-----------------            
            IF Object_id('TEMPDB..#PPA_DATA') IS NOT NULL
              DROP TABLE #PPA_DATA

            CREATE TABLE #PPA_DATA
              (
                 CONTRACT_SALES_PROJECTED NUMERIC(22, 6),
                 PPA_PROJECTED_RATE       NUMERIC(22, 6),
                 PPA_PROJECTED_RPU        NUMERIC(22, 6),
                 DISCOUNT                 VARCHAR(50),
                 HIERARCHY_NO             VARCHAR(100),
                 LEVEL_NAME               VARCHAR(100),
                 PERIOD                   VARCHAR(50),
                 [YEAR]                   INT
              )

            SET @D_SQL=Concat('INSERT INTO #PPA_DATA
			(
			CONTRACT_SALES_PROJECTED,
			PPA_PROJECTED_RATE      ,
			PPA_PROJECTED_RPU       ,
			DISCOUNT                ,
			HIERARCHY_NO			,
			LEVEL_NAME              ,
			PERIOD                  ,
			[YEAR]                  
			)
            SELECT CONTRACT_SALES_PROJECTED = SUM(PROJECTION_DISCOUNT_DOLLAR),
                   PPA_PROJECTED_RATE = SUM(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(SUM(PROJECTION_SALES), 0) * 100,
                   PPA_PROJECTED_RPU = SUM(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(SUM(PROJECTION_UNITS), 0),
                   DISCOUNT,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PERIOD,
                   YEAR
       
            FROM   (SELECT NPP.PROJECTION_DETAILS_SID,
                           SUM(PROJECTION_DISCOUNT_DOLLAR) PROJECTION_DISCOUNT_DOLLAR,
                           SUM(NM.PROJECTION_SALES)        PROJECTION_SALES,
                           SUM(NM.PROJECTION_UNITS)        PROJECTION_UNITS,
                           CASE
                             WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN RS_NAME
                             ELSE ''PRICE PROTECTION''
                           END                             AS DISCOUNT,
                           C.HIERARCHY_NO,
                           C.LEVEL_NAME,
                           P.PERIOD,
                           P.YEAR
                    FROM   ', @P_PROJECTION_TABLE, ' NPP
                           INNER JOIN ', @S_PROJECTION_TABLE, ' NM
                                   ON NM.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                      
                                      AND NM.PERIOD_SID = NPP.PERIOD_SID
                           INNER JOIN ', @P_MASTER_TABLE, ' NDPM
                                   ON NPP.PROJECTION_DETAILS_SID = NDPM.PROJECTION_DETAILS_SID
                           INNER JOIN RS_CONTRACT R
                                   ON R.RS_CONTRACT_SID = NPP.RS_CONTRACT_SID          
                           INNER JOIN PROJECTION_DETAILS CCP
                                   ON NM.PROJECTION_DETAILS_SID = CCP.PROJECTION_DETAILS_SID
                           INNER JOIN #CCP C
                                   ON C.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                           INNER JOIN #PERIOD P
                                   ON P.PERIOD_SID = NPP.PERIOD_SID
                    WHERE  EXISTS (SELECT 1
                                       FROM   #DISCOUNT_INFO A
                                       WHERE  CASE
                                                WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN CAST(NPP.RS_CONTRACT_SID AS VARCHAR(20))
                                                ELSE ''PRICE PROTECTION''
                                              END = A.TOKEN)
											  AND CCP.PROJECTION_MASTER_SID=@FIRST_PROJ_SID
                           
                    GROUP  BY NPP.PROJECTION_DETAILS_SID,
                              CASE
                                WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM'' THEN RS_NAME
                                ELSE ''PRICE PROTECTION''
                              END,
                              C.HIERARCHY_NO,
                              C.LEVEL_NAME,
                              P.PERIOD,
                              P.YEAR) A
            GROUP  BY DISCOUNT,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PERIOD,
                      YEAR')

          
			EXEC Sp_executesql
			@D_SQL,
			N'@FIRST_PROJ_SID INT',
			@FIRST_PROJ_SID = @FIRST_PROJ_SID

			---------------combining sales related information-------------------------------            
                IF Object_id('TEMPDB.DBO.#CURRENT_SALES', 'U') IS NOT NULL
                  DROP TABLE #CURRENT_SALES;

                      SELECT  FD.PERIOD ,               
			            FD.YEAR ,                  
			            FD.HIERARCHY_NO,			
			            FD.LEVEL_NAME,
                           COALESCE(( NAS.CONTRACT_SALES_ACTUALS ), 0) AS NM_ACTUAL_SALES ,
                          COALESCE(( NAS.CONTRACT_UNITS_ACTUALS ), 0) AS NM_ACTUAL_UNITS ,
						   COALESCE(( NSP.CONTRACT_SALES_PROJECTED ), 0) AS NM_PROJECTED_SALES ,
                           COALESCE(( NSP.CONTRACT_UNITS_PROJECTED ), 0) AS NM_PROJECTED_UNITS 
                INTO      #CURRENT_SALES
                FROM   (SELECT DISTINCT PERIOD,
                                           YEAR,
										 
										   HIERARCHY_NO
										   ,LEVEL_NAME
                           FROM   #DISCOUNT_DATA_TABLE) FD
				LEFT JOIN #SALES_ACTUAL_DATA NAS 
				ON  FD.YEAR = NAS.YEAR
                                                             AND FD.PERIOD = NAS.PERIOD
															 AND FD.HIERARCHY_NO=NAS.HIERARCHY_NO
															 AND FD.LEVEL_NAME=NAS.LEVEL_NAME
                LEFT JOIN #SALES_DATA NSP ON NSP.YEAR = FD.YEAR
                                                             AND NSP.PERIOD = FD.PERIOD
															 AND NSP.HIERARCHY_NO=FD.HIERARCHY_NO
															 AND NSP.LEVEL_NAME=FD.LEVEL_NAME
---------------combining DISCOUNT related information-------------------------------            
                IF Object_id('TEMPDB.DBO.#CURRENT_DISCOUNT', 'U') IS NOT NULL
                  DROP TABLE #CURRENT_DISCOUNT;

           SELECT FD.YEAR,
                       FD.PERIOD,
					   FD.DISCOUNT,
					  COALESCE(( NAD.CONTRACT_SALES_ACTUALS ), 0) AS   ACTUAL_SALES ,
                        COALESCE(( NSP.CONTRACT_SALES_PROJECTED ), 0) AS PROJECTION_SALES ,
			           FD.HIERARCHY_NO	,
			           FD.LEVEL_NAME         
                INTO   #CURRENT_DISCOUNT
                FROM  (SELECT DISTINCT PERIOD,
                                           YEAR,
										   DISCOUNT,
										   HIERARCHY_NO
										   ,LEVEL_NAME
                           FROM   #DISCOUNT_DATA_TABLE where DISCOUNT<>'PRICE PROTECTION') FD
						LEFT JOIN  #DISCOUNT_DATA NSP
						ON FD.YEAR = NSP.YEAR
                                                    AND FD.PERIOD = NSP.PERIOD
                                                    AND FD.DISCOUNT = NSP.DISCOUNT
													AND FD.HIERARCHY_NO=NSP.HIERARCHY_NO
													AND FD.LEVEL_NAME=NSP.LEVEL_NAME
				LEFT JOIN #DISCOUNT_ACTUAL_DATA NAD ON NAD.YEAR = FD.YEAR
                                                    AND NAD.PERIOD = FD.PERIOD
                                                    AND NAD.DISCOUNT = FD.DISCOUNT
													AND NAD.HIERARCHY_NO=FD.HIERARCHY_NO
													and NAD.LEVEL_NAME=FD.LEVEL_NAME
    IF Object_id('TEMPDB.DBO.#CURRENT_PPA', 'U') IS NOT NULL
                  DROP TABLE #CURRENT_PPA;---------------combining PPA' related information-------------------------------            
  SELECT    FD.YEAR,
                          FD.PERIOD,
                           COALESCE(( NSP.CONTRACT_SALES_PROJECTED ), 0) AS PPA_DISCOUNT_PROJECTED ,
                          COALESCE(( NAS.CONTRACT_SALES_ACTUALS ), 0) AS PPA_ACTUAL_SALES ,
                          COALESCE(( NAS.PPA_ACTUALS_RATE ), 0) AS PPA_RATE_ACTUAL ,
                           COALESCE(( NSP.PPA_PROJECTED_RATE ), 0) AS PPA_RATE_PROJECTION ,
                           COALESCE(( NSP.PPA_PROJECTED_RPU ), 0) AS PPA_RPU_PROJECTION ,
                          COALESCE(( NAS.PPA_ACTUALS_RPU ), 0) AS PPA_RPU_ACTUAL ,
                          FD.DISCOUNT,
			          FD.HIERARCHY_NO	,
			           FD.LEVEL_NAME         
         INTO      #CURRENT_PPA
                FROM    (SELECT DISTINCT PERIOD,
                                           YEAR,
										   DISCOUNT,
										   HIERARCHY_NO
										   ,LEVEL_NAME
                           FROM   #DISCOUNT_DATA_TABLE  where DISCOUNT='PRICE PROTECTION')FD
						LEFT JOIN  #PPA_ACTUAL_DATA NAS
						 ON NAS.YEAR = FD.YEAR
                                                   AND NAS.PERIOD = FD.PERIOD
                                                   AND NAS.DISCOUNT = FD.DISCOUNT
												   AND NAS.HIERARCHY_NO=FD.HIERARCHY_NO
												   AND NAS.LEVEL_NAME=FD.LEVEL_NAME
                LEFT JOIN #PPA_DATA NSP ON FD.YEAR = NSP.YEAR
                                                   AND FD.PERIOD = NSP.PERIOD
                                                   AND FD.DISCOUNT = NSP.DISCOUNT
												   AND NAS.HIERARCHY_NO=NSP.HIERARCHY_NO
												   AND NAS.LEVEL_NAME=NSP.LEVEL_NAME
												   ---------------------combining the results in the temp table------------------------
 IF Object_id('TEMPDB..#DPIVOT_TABLE') IS NOT NULL
              DROP TABLE #DPIVOT_TABLE

            CREATE TABLE #DPIVOT_TABLE
              (
                 PROJECTION_MASTER_SID INT,
                 LEVEL_NAME            VARCHAR(100),
                 HIERARCHY_NO          VARCHAR(100),
                 CP_INDICATOR          CHAR(1),
                 PERIOD                INT,
                 YEAR                  INT,
                 DISCOUNT_AMOUNT       NUMERIC(22, 6),
				 DISCOUNT_AMOUNT_PROJECTED       NUMERIC(22, 6),
                 DISCOUNT_RATE         NUMERIC(22, 6),
				 DISCOUNT_RATE_PROJECTED         NUMERIC(22, 6),
                 DISCOUNT_RPU          NUMERIC(22, 6),
				 DISCOUNT_RPU_PROJECTED         NUMERIC(22, 6),
				 DISCOUNT_OF_EX_FACTORY_ACTUALS          NUMERIC(22, 6),
				 DISCOUNT_OF_EX_FACTORY_PROJECTED         NUMERIC(22, 6),
                 DISCOUNT              VARCHAR(100)
              )


            INSERT INTO #DPIVOT_TABLE
                        (PROJECTION_MASTER_SID,
                         DISCOUNT,
                         CP_INDICATOR,
                         DISCOUNT_AMOUNT,
						 DISCOUNT_AMOUNT_PROJECTED,
                         DISCOUNT_RATE,
						 DISCOUNT_RATE_PROJECTED,
                         DISCOUNT_RPU,
						 DISCOUNT_RPU_PROJECTED,
						 DISCOUNT_OF_EX_FACTORY_ACTUALS      ,
				 DISCOUNT_OF_EX_FACTORY_PROJECTED       ,
                         PERIOD,
                         YEAR,
                         HIERARCHY_NO,
                         LEVEL_NAME)
						 
            SELECT @FIRST_PROJ_SID AS PROJECTION_MASTER_SID,
             DISCOUNT,
             @CP_INDICATOR   CP_INDICATOR,
             CASE
                                 WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN DT.ACTUAL_SALES
                                 ELSE NULL
                               END AS DISCOUNT_AMOUNT ,
             DT.PROJECTION_SALES as DISCOUNT_AMOUNT_PROJECTED,
              CASE
                               WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_SALES, 0), 0) * 100
                               ELSE NULL
                             END AS  DISCOUNT_RATE ,
             Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_SALES, 0), 0) * 100 AS  DISCOUNT_RATE_PROJECTED ,
             CASE
                             WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_UNITS, 0), 0)
                             ELSE NULL
                           END AS DISCOUNT_RPU ,
              Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_UNITS, 0), 0) AS DISCOUNT_RPU_PROJECTED ,
             CASE
                                              WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN Isnull(DT.ACTUAL_SALES / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100
                                              ELSE NULL
                                            END AS DISCOUNT_OF_EX_FACTORY_ACTUALS,
             Isnull(DT.PROJECTION_SALES / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100 AS DISCOUNT_OF_EX_FACTORY_PROJECTED,
             DT.PERIOD,
             DT.[YEAR],
             DT.HIERARCHY_NO,
             DT.LEVEL_NAME
            FROM   #CURRENT_DISCOUNT DT
                   INNER JOIN #CURRENT_SALES S
                           ON S.HIERARCHY_NO = DT.HIERARCHY_NO
                              AND S.LEVEL_NAME = DT.LEVEL_NAME
                              AND S.YEAR = DT.YEAR
                              AND S.PERIOD = DT.PERIOD
				   LEFT JOIN #FILE_DATA F
					ON F.YEAR=S.YEAR
					AND F.PERIOD=S.PERIOD
					 and S.HIERARCHY_NO = F.HIERARCHY_NO
                     AND S.LEVEL_NAME = F.LEVEL_NAME
            UNION ALL
             SELECT @FIRST_PROJ_SID AS PROJECTION_MASTER_SID,
             DISCOUNT,
             @CP_INDICATOR   CP_INDICATOR,
              CASE
                                 WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01) THEN P.PPA_ACTUAL_SALES
                                 ELSE NULL
                               END AS DISCOUNT_AMOUNT ,
             PPA_DISCOUNT_PROJECTED AS DISCOUNT_AMOUNT_PROJECTED,
             CASE
               WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01) THEN PPA_RATE_ACTUAL
               ELSE NULL
             END,
             PPA_RATE_PROJECTION,
             CASE
               WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01) THEN PPA_RPU_ACTUAL
               ELSE NULL
             END,
             PPA_RPU_PROJECTION,
             CASE
               WHEN @CURRENT_DATE > Datefromparts(P.[YEAR], P.PERIOD, 01) THEN Isnull(P.PPA_ACTUAL_SALES / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100
               ELSE NULL
             END,
             Isnull(P.PPA_DISCOUNT_PROJECTED / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100,
             P.PERIOD,
             P.[YEAR],
             P.HIERARCHY_NO,
             P.LEVEL_NAME
            FROM   #CURRENT_PPA P
			  LEFT JOIN #FILE_DATA F
					ON F.YEAR=P.YEAR
					AND F.PERIOD=P.PERIOD
					 and p.HIERARCHY_NO = F.HIERARCHY_NO
                     AND p.LEVEL_NAME = F.LEVEL_NAME
---------------------------------------taking the prior projeciton data-----------------
					  IF EXISTS (SELECT 1
                       FROM   #PROJECTION_MASTER
                       WHERE  ID > 1)
              BEGIN
                  IF Object_id('TEMPDB..#DPRIOR_PROJECTIONS') IS NOT NULL
                    DROP TABLE #DPRIOR_PROJECTIONS----------------------prior projection data  hierarchy wise----------------------

                  SELECT PD.PROJECTION_DETAILS_SID,
                         PM.PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NAME
                  INTO   #DPRIOR_PROJECTIONS
                  FROM   #PROJECTION_MASTER PM
                         INNER JOIN PROJECTION_DETAILS PD
                                 ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                         INNER JOIN #CCP C
                                 ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                  WHERE  PM.ID <> 1

                  IF Object_id('TEMPDB..#DPRIOR_DATA_TABLE') IS NOT NULL
                    DROP TABLE #DPRIOR_DATA_TABLE----------------------prior projection data hierarchy period wise----------------------

                  SELECT PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NAME,
                         PERIOD,
                         YEAR,
                         DISCOUNT
                  INTO   #DPRIOR_DATA_TABLE
                  FROM   (SELECT DISTINCT HIERARCHY_NO,
                                          LEVEL_NAME
                          FROM   #CCP) C
                         CROSS JOIN (SELECT DISTINCT PERIOD,
                                                     YEAR
                                     FROM   #PERIOD
                                     WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID) P
                         CROSS JOIN #PROJECTION_MASTER PM
                         CROSS JOIN (SELECT DISCOUNT
                                     FROM   #DISCOUNT_INFO) D
                  WHERE  PM.ID <> 1
              IF Object_id('TEMPDB..#DPRIOR_ACTUAL_SALES_DATA') IS NOT NULL
              DROP TABLE #DPRIOR_ACTUAL_SALES_DATA---------taking actual data from sales-----------------

            SELECT  Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS ,
                    Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS ,
                   P.PERIOD,
                   P.[YEAR],
                   PP.PROJECTION_MASTER_SID,
                   PP.HIERARCHY_NO,
                   PP.LEVEL_NAME
            INTO   #DPRIOR_ACTUAL_SALES_DATA
            FROM  #DPRIOR_PROJECTIONS PP INNER JOIN PROJECTION_DETAILS PD
                           ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
						   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID  BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
					LEFT JOIN NM_ACTUAL_SALES NPS
                    ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
					 AND   P.PERIOD_SID = NPS.PERIOD_SID
            GROUP  BY PP.PROJECTION_MASTER_SID,
                      PERIOD,
                      [YEAR],
                      HIERARCHY_NO,
                      LEVEL_NAME

                  IF Object_id('TEMPDB..#DPRIOR_SALES_DATA') IS NOT NULL
              DROP TABLE #DPRIOR_SALES_DATA---------------taking projection data from sales-----------------

          SELECT  isnull(Sum(PROJECTION_SALES),0) AS CONTRACT_SALES_PROJECTED ,
                   isnull(Sum(PROJECTION_UNITS),0) AS  CONTRACT_UNITS_PROJECTED ,
                   P.PERIOD,
                   P.[YEAR],
                   PP.PROJECTION_MASTER_SID,
                   PP.HIERARCHY_NO,
                   PP.LEVEL_NAME
            INTO   #DPRIOR_SALES_DATA
            FROM    #DPRIOR_PROJECTIONS PP INNER JOIN PROJECTION_DETAILS PD
                           ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
						   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID  BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
					LEFT JOIN NM_SALES_PROJECTION NPS
                    ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
					 AND   P.PERIOD_SID = NPS.PERIOD_SID
            GROUP  BY PP.PROJECTION_MASTER_SID,
                      PERIOD,
                      [YEAR],
                      HIERARCHY_NO,
                      LEVEL_NAME
 IF Object_id('TEMPDB..#PRIOR_ACTUAL_DISCOUNT_DATA') IS NOT NULL
        DROP TABLE #PRIOR_ACTUAL_DISCOUNT_DATA----------------------taking discount data from discount table------------------------

SELECT  isnull(Sum(ACTUAL_SALES),0) AS CONTRACT_SALES_ACTUALS ,
                   CASE
                     WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN RS_NAME
                     ELSE D.PRICE_GROUP_TYPE
                   END AS DISCOUNT,
                   PP.HIERARCHY_NO,
                   PP.LEVEL_NAME,
                   P.PERIOD,
                   P.YEAR,
                   PP.PROJECTION_MASTER_SID
            INTO   #PRIOR_ACTUAL_DISCOUNT_DATA
            FROM   #DPRIOR_PROJECTIONS PP INNER JOIN PROJECTION_DETAILS PD
                           ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
						   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID  BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
						  LEFT JOIN ( SELECT  NDPM.PROJECTION_DETAILS_SID,NDPM.PRICE_GROUP_TYPE ,R.RS_NAME,NDP.RS_CONTRACT_SID,ACTUAL_SALES
						  ,PERIOD_SID  FROM NM_ACTUAL_DISCOUNT NDP
                   INNER JOIN NM_DISCOUNT_PROJ_MASTER NDPM
                           ON NDPM.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                              AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                   INNER JOIN RS_CONTRACT R
                           ON R.RS_CONTRACT_SID = NDPM.RS_CONTRACT_SID
						   ) D on D.PROJECTION_DETAILS_SID=PP.PROJECTION_DETAILS_SID
               AND   P.PERIOD_SID = D.PERIOD_SID
                  WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                FROM   #PROJECTION_MASTER PM
                                                WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PP.PROJECTION_MASTER_SID)
                   AND EXISTS (SELECT 1
                               FROM   #DISCOUNT_INFO A
                               WHERE  CASE
                                        WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN Cast(D.RS_CONTRACT_SID AS VARCHAR(20))
                                        ELSE D.PRICE_GROUP_TYPE
                                      END = A.TOKEN)
            GROUP  BY CASE
                        WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN RS_NAME
                        ELSE D.PRICE_GROUP_TYPE
                      END,
                      PP.HIERARCHY_NO,
                      PP.LEVEL_NAME,
                      P.PERIOD,
                      P.YEAR,
                      PP.PROJECTION_MASTER_SID

            IF Object_id('TEMPDB..#PRIOR_DISCOUNT_DATA') IS NOT NULL
              DROP TABLE #PRIOR_DISCOUNT_DATA----------------------taking discount data from discount table------------------------

 
			  SELECT isnull(Sum(PROJECTION_SALES),0) AS CONTRACT_SALES_PROJECTED ,
                   CASE
                     WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN RS_NAME
                     ELSE D.PRICE_GROUP_TYPE
                   END AS DISCOUNT,
                   PP.HIERARCHY_NO,
                   PP.LEVEL_NAME,
                   P.PERIOD,
                   P.YEAR,
                   PP.PROJECTION_MASTER_SID
            INTO   #PRIOR_DISCOUNT_DATA
            FROM    #DPRIOR_PROJECTIONS PP INNER JOIN PROJECTION_DETAILS PD
                           ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
						   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID  BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
						  LEFT JOIN ( SELECT  NDPM.PROJECTION_DETAILS_SID,NDPM.PRICE_GROUP_TYPE ,R.RS_NAME,NDP.RS_CONTRACT_SID,PROJECTION_SALES
						  ,PERIOD_SID  FROM NM_DISCOUNT_PROJECTION NDP
                   INNER JOIN NM_DISCOUNT_PROJ_MASTER NDPM
                           ON NDPM.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                              AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                   INNER JOIN RS_CONTRACT R
                           ON R.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
						   ) D on D.PROJECTION_DETAILS_SID=PP.PROJECTION_DETAILS_SID
                      AND   P.PERIOD_SID = D.PERIOD_SID
                  WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                FROM   #PROJECTION_MASTER PM
                                                WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                   AND EXISTS (SELECT 1
                               FROM   #DISCOUNT_INFO A
                               WHERE  CASE
                                        WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN Cast(D.RS_CONTRACT_SID AS VARCHAR(20))
                                        ELSE D.PRICE_GROUP_TYPE
                                      END = A.TOKEN)
            GROUP  BY CASE
                        WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN RS_NAME
                        ELSE D.PRICE_GROUP_TYPE
                      END,
                      PP.HIERARCHY_NO,
                      PP.LEVEL_NAME,
                      P.PERIOD,
                      P.YEAR,
                      PP.PROJECTION_MASTER_SID

					    

						  
            IF Object_id('TEMPDB..#PRIOR_ACTUAL_PPA_DATA') IS NOT NULL
              DROP TABLE #PRIOR_ACTUAL_PPA_DATA----------------------taking ppa data from ppa table------------------------

           SELECT PROJECTION_MASTER_SID,
                    Sum(ACTUAL_DISCOUNT_DOLLAR) AS CONTRACT_SALES_ACTUALS ,
                    Sum(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(Sum(ACTUAL_SALES), 0) * 100 AS PPA_ACTUAL_RATE ,
                    Sum(ACTUAL_DISCOUNT_DOLLAR) / NULLIF(Sum(ACTUAL_UNITS), 0) AS  PPA_ACTUAL_RPU ,
                   DISCOUNT,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PERIOD,
                   YEAR
            INTO   #PRIOR_ACTUAL_PPA_DATA
             FROM   (SELECT PP.PROJECTION_MASTER_SID,
                           PP.PROJECTION_DETAILS_SID,
                           Sum(ACTUAL_DISCOUNT_DOLLAR) ACTUAL_DISCOUNT_DOLLAR,
                           Sum(D.ACTUAL_SALES)        ACTUAL_SALES,
                           Sum(D.ACTUAL_UNITS)        ACTUAL_UNITS,
                           CASE
                             WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN RS_NAME
                             ELSE 'PRICE PROTECTION'
                           END                         AS DISCOUNT,
                           PP.HIERARCHY_NO,
                           PP.LEVEL_NAME,
                           P.PERIOD,
                           P.YEAR
                    FROM    #DPRIOR_PROJECTIONS PP INNER JOIN PROJECTION_DETAILS PD
                           ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
						   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID  BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
						  LEFT JOIN
                        (SELECT  NDPM.PROJECTION_DETAILS_SID,R.RS_NAME,NPP.RS_CONTRACT_SID,ACTUAL_DISCOUNT_DOLLAR,NM.ACTUAL_SALES,NM.ACTUAL_UNITS
						  ,NPP.PERIOD_SID  FROM  NM_ACTUAL_PPA NPP
                           INNER JOIN NM_ACTUAL_SALES NM
                                   ON NM.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                      AND NM.PERIOD_SID = NPP.PERIOD_SID
                           INNER JOIN NM_PPA_PROJECTION_MASTER NDPM
                                   ON NPP.PROJECTION_DETAILS_SID = NDPM.PROJECTION_DETAILS_SID
                          INNER JOIN RS_CONTRACT R
                                   ON R.RS_CONTRACT_SID = NPP.RS_CONTRACT_SID
                                      AND NDPM.RS_CONTRACT_SID = NPP.RS_CONTRACT_SID)
									  D on D.PROJECTION_DETAILS_SID=PP.PROJECTION_DETAILS_SID
                      AND   P.PERIOD_SID = D.PERIOD_SID
                    WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                        FROM   #PROJECTION_MASTER PM
                                                        WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                           AND EXISTS (SELECT 1
                                       FROM   #DISCOUNT_INFO A
                                       WHERE  CASE
                                                WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN Cast(D.RS_CONTRACT_SID AS VARCHAR(20))
                                                ELSE 'PRICE PROTECTION'
                                              END = A.TOKEN)
                    GROUP  BY PP.PROJECTION_MASTER_SID,
                              PP.PROJECTION_DETAILS_SID,
                              CASE
                                WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN RS_NAME
                                ELSE 'PRICE PROTECTION'
                              END,
                              PP.HIERARCHY_NO,
                              PP.LEVEL_NAME,
                              P.PERIOD,
                              P.YEAR) A
            GROUP  BY PROJECTION_MASTER_SID,
                      DISCOUNT,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PERIOD,
                      YEAR

 IF Object_id('TEMPDB..#PRIOR_PPA_DATA') IS NOT NULL
              DROP TABLE #PRIOR_PPA_DATA----------------------taking ppa data from ppa table------------------------

            SELECT PROJECTION_MASTER_SID,
                    Sum(PROJECTION_DISCOUNT_DOLLAR) AS CONTRACT_SALES_PROJECTED ,
                    Sum(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(Sum(PROJECTION_SALES), 0) * 100 AS PPA_PROJECTED_RATE ,
                    Sum(PROJECTION_DISCOUNT_DOLLAR) / NULLIF(Sum(PROJECTION_UNITS), 0) AS PPA_PROJECTED_RPU ,
                   DISCOUNT,
                   HIERARCHY_NO,
                   LEVEL_NAME,
                   PERIOD,
                   YEAR
            INTO   #PRIOR_PPA_DATA
            FROM    (SELECT PP.PROJECTION_MASTER_SID,
                           PP.PROJECTION_DETAILS_SID,
                            Sum(PROJECTION_DISCOUNT_DOLLAR) PROJECTION_DISCOUNT_DOLLAR,
                           Sum(D.PROJECTION_SALES)        PROJECTION_SALES,
                           Sum(D.PROJECTION_UNITS)        PROJECTION_UNITS,
                           CASE
                             WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN RS_NAME
                             ELSE 'PRICE PROTECTION'
                           END                         AS DISCOUNT,
                           PP.HIERARCHY_NO,
                           PP.LEVEL_NAME,
                           P.PERIOD,
                           P.YEAR
                    FROM    #DPRIOR_PROJECTIONS PP INNER JOIN PROJECTION_DETAILS PD
                           ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
						   INNER JOIN #PERIOD P
                           ON P.PERIOD_SID  BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
						  LEFT JOIN
                        (SELECT  NDPM.PROJECTION_DETAILS_SID,R.RS_NAME,NPP.RS_CONTRACT_SID,PROJECTION_DISCOUNT_DOLLAR,NM.PROJECTION_SALES,NM.PROJECTION_UNITS
						  ,NPP.PERIOD_SID  FROM  NM_PPA_PROJECTION NPP
                           INNER JOIN NM_SALES_PROJECTION NM
                                   ON NM.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                      AND NM.PERIOD_SID = NPP.PERIOD_SID
                           INNER JOIN NM_PPA_PROJECTION_MASTER NDPM
                                   ON NPP.PROJECTION_DETAILS_SID = NDPM.PROJECTION_DETAILS_SID
                          INNER JOIN RS_CONTRACT R
                                   ON R.RS_CONTRACT_SID = NPP.RS_CONTRACT_SID
                                      AND NDPM.RS_CONTRACT_SID = NPP.RS_CONTRACT_SID)
									  D on D.PROJECTION_DETAILS_SID=PP.PROJECTION_DETAILS_SID
                      AND   P.PERIOD_SID = D.PERIOD_SID
                    WHERE  EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                        FROM   #PROJECTION_MASTER PM
                                                        WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                           AND EXISTS (SELECT 1
                                       FROM   #DISCOUNT_INFO A
                                       WHERE  CASE
                                                WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN Cast(D.RS_CONTRACT_SID AS VARCHAR(20))
                                                ELSE 'PRICE PROTECTION'
                                              END = A.TOKEN)
                    GROUP  BY PP.PROJECTION_MASTER_SID,
                              PP.PROJECTION_DETAILS_SID,
                              CASE
                                WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN RS_NAME
                                ELSE 'PRICE PROTECTION'
                              END,
                              PP.HIERARCHY_NO,
                              PP.LEVEL_NAME,
                              P.PERIOD,
                              P.YEAR) A
            GROUP  BY PROJECTION_MASTER_SID,
                      DISCOUNT,
                      HIERARCHY_NO,
                      LEVEL_NAME,
                      PERIOD,
                      YEAR
					  

			  IF Object_id('TEMPDB.DBO.#PRIOR_SALES', 'U') IS NOT NULL
              DROP TABLE #PRIOR_SALES;-------------combining sales information-------------------------------

            SELECT FD.PROJECTION_MASTER_SID,
                   FD.YEAR,
                   FD.PERIOD,
                   FD.HIERARCHY_NO,
                   FD.LEVEL_NAME,
                   COALESCE(( NAS.CONTRACT_SALES_ACTUALS ), 0) AS  NM_ACTUAL_SALES ,
                   COALESCE(( NAS.CONTRACT_UNITS_ACTUALS ), 0) AS NM_ACTUAL_UNITS ,
                   COALESCE(( NSP.CONTRACT_SALES_PROJECTED ), 0) AS NM_PROJECTED_SALES ,
                   COALESCE(( NSP.CONTRACT_UNITS_PROJECTED ), 0) AS NM_PROJECTED_UNITS 
            INTO   #PRIOR_SALES
            FROM   (SELECT distinct YEAR,
                           PERIOD,
                           HIERARCHY_NO,
                           LEVEL_NAME,                         
                           PROJECTION_MASTER_SID
                    FROM   #DPRIOR_DATA_TABLE)FD
                   LEFT JOIN #DPRIOR_SALES_DATA NSP
                          ON FD.PROJECTION_MASTER_SID=NSP.PROJECTION_MASTER_SID AND
						  FD.YEAR = NSP.YEAR
                             AND FD.PERIOD = NSP.PERIOD
                             AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
                             AND FD.LEVEL_NAME = NSP.LEVEL_NAME
                   LEFT JOIN #DPRIOR_ACTUAL_SALES_DATA NAS
                          ON FD.PROJECTION_MASTER_SID=NAS.PROJECTION_MASTER_SID AND FD.YEAR = NAS.YEAR
                             AND FD.PERIOD = NAS.PERIOD
                             AND FD.HIERARCHY_NO = NAS.HIERARCHY_NO
                             AND FD.LEVEL_NAME = NAS.LEVEL_NAME
     IF Object_id('TEMPDB.DBO.#PRIOR_DISCOUNT', 'U') IS NOT NULL
              DROP TABLE #PRIOR_DISCOUNT;-------------combining discount information-------------------------------

            SELECT FD.PROJECTION_MASTER_SID,
                   FD.YEAR,
                   FD.PERIOD,
                   COALESCE(( NAD.CONTRACT_SALES_ACTUALS ), 0) AS ACTUAL_SALES ,
                   COALESCE(( NSP.CONTRACT_SALES_PROJECTED ), 0) AS PROJECTION_SALES ,
                   FD.DISCOUNT,
                   FD.HIERARCHY_NO,
                   FD.LEVEL_NAME
            INTO   #PRIOR_DISCOUNT
            FROM   (SELECT YEAR,
                           PERIOD,
                           HIERARCHY_NO,
                           LEVEL_NAME,
                           DISCOUNT,
                           PROJECTION_MASTER_SID
                    FROM   #DPRIOR_DATA_TABLE where DISCOUNT<>'PRICE PROTECTION')FD
                   LEFT JOIN #PRIOR_DISCOUNT_DATA NSP
                          ON FD.PROJECTION_MASTER_SID=NSP.PROJECTION_MASTER_SID AND FD.YEAR = NSP.YEAR
                             AND FD.PERIOD = NSP.PERIOD
                             AND FD.DISCOUNT = NSP.DISCOUNT
                             AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
                             AND FD.LEVEL_NAME = NSP.LEVEL_NAME
                   LEFT JOIN #PRIOR_ACTUAL_DISCOUNT_DATA NAD
                          ON FD.PROJECTION_MASTER_SID=NAD.PROJECTION_MASTER_SID AND FD.YEAR = NAD.YEAR
                             AND FD.PERIOD = NAD.PERIOD
                             AND FD.DISCOUNT = NAD.DISCOUNT
                             AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
                             AND FD.LEVEL_NAME = NSP.LEVEL_NAME
		 IF Object_id('TEMPDB.DBO.#PRIOR_PPA', 'U') IS NOT NULL
              DROP TABLE #PRIOR_PPA;-------------combining ppa information-------------------------------

            SELECT FD.PROJECTION_MASTER_SID,
                   FD.YEAR,
                   FD.PERIOD,
                   COALESCE(( NSP.CONTRACT_SALES_ACTUALS ), 0) AS PPA_ACTUAL ,
                   COALESCE(( NSP.PPA_ACTUAL_RATE ), 0) AS PPA_RATE,
                   COALESCE(( NSP.PPA_ACTUAL_RPU ), 0) AS  PPA_RPU,
                   COALESCE(( NAP.CONTRACT_SALES_PROJECTED ), 0) AS PPA_DISCOUNT_PROJECTED ,
                   COALESCE(( NAP.PPA_PROJECTED_RATE ), 0) AS PPA_RATE_PROJECTION ,
                   COALESCE(( NAP.PPA_PROJECTED_RPU ), 0) AS PPA_RPU_PROJECTION ,
                   FD.DISCOUNT,
                   FD.HIERARCHY_NO,
                   FD.LEVEL_NAME
            INTO   #PRIOR_PPA
            FROM   (SELECT YEAR,
                           PERIOD,
                           HIERARCHY_NO,
                           LEVEL_NAME,
                           DISCOUNT,
                           PROJECTION_MASTER_SID
                    FROM   #DPRIOR_DATA_TABLE where DISCOUNT='PRICE PROTECTION')FD
                   LEFT JOIN #PRIOR_ACTUAL_PPA_DATA NSP
                          ON FD.PROJECTION_MASTER_SID=NSP.PROJECTION_MASTER_SID AND FD.YEAR = NSP.YEAR
                             AND FD.PERIOD = NSP.PERIOD
                             AND FD.DISCOUNT = NSP.DISCOUNT
                             AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
                             AND FD.LEVEL_NAME = NSP.LEVEL_NAME
                   LEFT JOIN #PRIOR_PPA_DATA NAP
                          ON FD.PROJECTION_MASTER_SID=NAP.PROJECTION_MASTER_SID AND FD.YEAR = NAP.YEAR
                             AND FD.PERIOD = NAP.PERIOD
                             AND FD.DISCOUNT = NAP.DISCOUNT
                             AND FD.HIERARCHY_NO = NAP.HIERARCHY_NO
                             AND FD.LEVEL_NAME = NAP.LEVEL_NAME
-----------------result will insert into temp table

                  INSERT INTO #DPIVOT_TABLE
                              (PROJECTION_MASTER_SID,
                               DISCOUNT,
                               CP_INDICATOR,
                               DISCOUNT_AMOUNT,
							   DISCOUNT_AMOUNT_PROJECTED,
                               DISCOUNT_RATE,
							   DISCOUNT_RATE_PROJECTED,
                               DISCOUNT_RPU,
							   DISCOUNT_RPU_PROJECTED,
							   DISCOUNT_OF_EX_FACTORY_ACTUALS,
							   DISCOUNT_OF_EX_FACTORY_PROJECTED,
                               PERIOD,
                               YEAR,
                               HIERARCHY_NO,
                               LEVEL_NAME)
                  SELECT distinct D.PROJECTION_MASTER_SID,
                         DT.DISCOUNT,
                         @CP_INDICATOR CP_INDICATOR,
                         DT.ACTUAL_SALES AS  DISCOUNT_AMOUNT ,
						 DT.PROJECTION_SALES AS DISCOUNT_AMOUNT_PROJECTED ,
                         Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_SALES, 0), 0) * 100 AS DISCOUNT_RATE , 
						 Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_SALES, 0), 0) * 100 AS  DISCOUNT_RATE_PROJECTED ,
						 Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_UNITS, 0), 0) AS DISCOUNT_RPU ,
                         Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_UNITS, 0), 0) AS DISCOUNT_RPU ,
						  Isnull(DT.ACTUAL_SALES / NULLIF(F.GTS_SALES_ACTUALS, 0), 0)*100,
                        Isnull(DT.PROJECTION_SALES / NULLIF(F.GTS_SALES_PROJECTED, 0), 0)*100,
                         D.PERIOD,
                         D.[YEAR],
                         D.HIERARCHY_NO,
                         D.LEVEL_NAME
                  FROM  #DPRIOR_DATA_TABLE D
                    LEFT JOIN #PRIOR_DISCOUNT DT
                          ON D.PROJECTION_MASTER_SID=DT.PROJECTION_MASTER_SID AND DT.HIERARCHY_NO = D.HIERARCHY_NO
                             AND DT.LEVEL_NAME = D.LEVEL_NAME
                             AND DT.PERIOD = D.PERIOD
                             AND DT.[YEAR] = D.[YEAR]
                             AND DT.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                   LEFT JOIN #PRIOR_SALES S
                           ON D.PROJECTION_MASTER_SID=S.PROJECTION_MASTER_SID AND S.HIERARCHY_NO = D.HIERARCHY_NO
                              AND D.LEVEL_NAME = S.LEVEL_NAME
                              AND D.YEAR = S.YEAR
                              AND D.PERIOD = S.PERIOD
                   LEFT JOIN (SELECT PF.PROJECTION_MASTER_SID
                                  ,P.PERIOD
                                  ,P.YEAR
                                  ,dp.HIERARCHY_NO
                                  ,dp.LEVEL_NAME
                                  ,Sum(EXFACTORY_ACTUAL_SALES) as GTS_SALES_ACTUALS
                                  ,Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) as GTS_SALES_PROJECTED
                           FROM #PRODUCT_FILE_TEMP PF
                           JOIN #PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID
                           JOIN (SELECT DISTINCT DP.PROJECTION_MASTER_SID,HIERARCHY_NO,LEVEL_NAME,ITEM_MASTER_SID FROM
                     #DPRIOR_PROJECTIONS DP JOIN PROJECTION_DETAILS PD ON PD.PROJECTION_MASTER_SID=DP.PROJECTION_MASTER_SID AND  PD.PROJECTION_DETAILS_SID=DP.PROJECTION_DETAILS_SID
                     JOIN CCP_DETAILS CD ON CD.CCP_DETAILS_SID=PD.CCP_DETAILS_SID )       DP ON DP.PROJECTION_MASTER_SID=PF.PROJECTION_MASTER_SID
                     AND DP.ITEM_MASTER_SID=PF.ITEM_MASTER_SID
                           GROUP BY PF.PROJECTION_MASTER_SID
                           ,dp.HIERARCHY_NO
                                  ,dp.LEVEL_NAME
                                  ,P.PERIOD
                                  ,P.YEAR) F
                          ON F.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                             AND F.YEAR = D.YEAR
                             AND F.PERIOD = D.PERIOD
							   AND F.HIERARCHY_NO = D.HIERARCHY_NO
                           AND F.LEVEL_NAME = D.LEVEL_NAME
                  UNION ALL
                  SELECT distinct D.PROJECTION_MASTER_SID,
                         P.DISCOUNT,
                         @CP_INDICATOR CP_INDICATOR,
						 PPA_ACTUAL,
                         P.PPA_DISCOUNT_PROJECTED as DISCOUNT_AMOUNT_PROJECTED,
						 PPA_RATE,
                         PPA_RATE_PROJECTION,
						 PPA_RPU,
                         PPA_RPU_PROJECTION,
						 	 Isnull(P.PPA_ACTUAL / NULLIF(F.GTS_SALES_ACTUALS, 0), 0)*100,
                        Isnull(P.PPA_DISCOUNT_PROJECTED / NULLIF(F.GTS_SALES_PROJECTED, 0), 0)*100,
                         D.PERIOD,
                         D.[YEAR],
                         D.HIERARCHY_NO,
                         D.LEVEL_NAME
                  FROM    #DPRIOR_DATA_TABLE D
                   LEFT JOIN #PRIOR_PPA P
                          ON   D.PROJECTION_MASTER_SID=P.PROJECTION_MASTER_SID AND P.HIERARCHY_NO = D.HIERARCHY_NO
                             AND D.LEVEL_NAME = P.LEVEL_NAME
                             AND D.PERIOD = P.PERIOD
                             AND D.[YEAR] = P.[YEAR]
                             AND D.PROJECTION_MASTER_SID = P.PROJECTION_MASTER_SID
                    LEFT JOIN #PRIOR_SALES S
                           ON D.PROJECTION_MASTER_SID=S.PROJECTION_MASTER_SID AND S.HIERARCHY_NO = D.HIERARCHY_NO
                              AND D.LEVEL_NAME = S.LEVEL_NAME
                              AND D.YEAR = S.YEAR
                              AND D.PERIOD = S.PERIOD
                   LEFT JOIN (
                                  SELECT PF.PROJECTION_MASTER_SID
                                  ,P.PERIOD
                                  ,P.YEAR
                                  ,dp.HIERARCHY_NO
                                  ,dp.LEVEL_NAME
                                  ,Sum(EXFACTORY_ACTUAL_SALES) as GTS_SALES_ACTUALS
                                  ,Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) as GTS_SALES_PROJECTED
                           FROM #PRODUCT_FILE_TEMP PF
                           JOIN #PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID
                           join (select distinct dp.PROJECTION_MASTER_SID,HIERARCHY_NO,level_name,item_master_Sid from
                     #DPRIOR_PROJECTIONS dp join PROJECTION_DETAILS pd on pd.PROJECTION_MASTER_SID=dp.PROJECTION_MASTER_SID and  pd.projection_details_Sid=dp.projection_details_Sid
                     join CCP_DETAILS cd on cd.CCP_DETAILS_SID=pd.CCP_DETAILS_SID )       dp on dp.PROJECTION_MASTER_SID=pf.PROJECTION_MASTER_SID
                     and dp.ITEM_MASTER_SID=pf.ITEM_MASTER_SID
                           GROUP BY PF.PROJECTION_MASTER_SID
                           ,dp.HIERARCHY_NO
                                  ,dp.LEVEL_NAME
                                  ,P.PERIOD
                                  ,P.YEAR) F
                          ON F.PROJECTION_MASTER_SID = D.PROJECTION_MASTER_SID
                             AND F.YEAR = D.YEAR
                             AND F.PERIOD = D.PERIOD
							 AND F.HIERARCHY_NO = P.HIERARCHY_NO
                           AND F.LEVEL_NAME = P.LEVEL_NAME
end 
 DECLARE @SQL2       NVARCHAR(MAX),
                    @LOOP_CNTR1 INT,
                    @MAX_CCP1   INT

            SET @SQL2 = '
SELECT DISCOUNT,pr.HIERARCHY_NO,
                   PERIOD,
                   YEAR, '
            SET @LOOP_CNTR1 = 1
            SET @MAX_CCP1 = (SELECT Max(ID)
                             FROM   #PROJECTION_MASTER)

            WHILE @LOOP_CNTR1 <= @MAX_CCP1
              BEGIN
                  SET @SQL2 += ' DISCOUNT_AMOUNT_'
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' = MAX(CASE WHEN ID = '
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' THEN DISCOUNT_AMOUNT END),
							   DISCOUNT_AMOUNT_PROJECTED_'
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' = ISNULL(MAX(CASE WHEN ID = '
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' THEN DISCOUNT_AMOUNT_PROJECTED END),0),
                     DISCOUNT_RATE_'
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' = MAX(CASE WHEN ID = '
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' THEN DISCOUNT_RATE END),
                     DISCOUNT_RATE_PROJECTED_'
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' = ISNULL(MAX(CASE WHEN ID = '
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' THEN DISCOUNT_RATE_PROJECTED END),0),
					 DISCOUNT_RPU_'
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' = MAX(CASE WHEN ID = '
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' THEN DISCOUNT_RPU END),
					 DISCOUNT_RPU_PROJECTED_'
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' = ISNULL(MAX(CASE WHEN ID = '
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' THEN DISCOUNT_RPU_PROJECTED END),0),
					DISCOUNT_OF_EX_FACTORY_ACTUALS_'
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' = MAX(CASE WHEN ID = '
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),
					DISCOUNT_OF_EX_FACTORY_PROJECTED_'
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' = ISNULL(MAX(CASE WHEN ID = '
                         + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                         + ' THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0),'
                  SET @LOOP_CNTR1 += 1
              END

            SET @SQL2 = LEFT(@SQL2, Len(@SQL2) - 1)
            SET @SQL2 += ' FROM   #DPIVOT_TABLE PR JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID  = PR.PROJECTION_MASTER_SID
			join #CCP c on pr.HIERARCHY_NO = c.HIERARCHY_NO
                GROUP  BY DISCOUNT,pr.LEVEL_NAME,pr.HIERARCHY_NO,CP_INDICATOR,PERIOD,YEAR				
                ORDER BY pr.HIERARCHY_NO	,'
                         + CASE WHEN( @VIEW_TAB ='PIVOT' AND @VIEW='DETAIL_DISCOUNT') THEN 'YEAR,PERIOD' ELSE 'DISCOUNT,YEAR,PERIOD' END


            EXEC Sp_executesql
              @SQL2
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

          RAISERROR (@ERRORMESSAGE,-- MESSAGE TEXT.
                     @ERRORSEVERITY,-- SEVERITY.
                     @ERRORSTATE,-- STATE.
                     @ERRORPROCEDURE,-- PROCEDURE_NAME.
                     @ERRORNUMBER,-- ERRORNUMBER
                     @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END 
