IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PROJ_RESULTS_VIEW'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PROJ_RESULTS_VIEW]
  END

GO

CREATE PROCEDURE [dbo].[PRC_PROJ_RESULTS_VIEW] (@PROJECTION_SID VARCHAR(500),
                                                @PROJ_FREQUENCY VARCHAR(20),
                                                @DISCOUNT_ID    VARCHAR(8000),
                                                @SCREEN_NAME    VARCHAR(50),
                                                @VIEW           VARCHAR(20) = NULL)
AS
/**********************************************************************************************************
** FILE NAME		:	PRC_PROJ_RESULTS_VIEW.SQL
** PROCEDURE NAME	:	PRC_PROJ_RESULTS_VIEW
** DESCRIPTION		:	  aggregation of projections attached to the particualr projection showing as output for both projection_result screen and projection_varicnace screen
** INPUT PARAMETERS	:	@PROJECTION_SID	- passing input as PROJECTION_MASTER_SID
						@PROJ_FREQUENCY - based on frequency it will display the data monthly,quarterly semi annualy and annulaly
						@DISCOUNT       - Passing inpuit as(RS_CONTRACT_SID) either REBATE_PROGRAM_TYPE OR RS_NAME based on Rebate level we will pull the discount projection value in discount projection tables
                        @SCREEN_NAME    - based on screen name :if @screen_name =''Assumption'' We will pass only one one projection as input parameter based on that we will calculate the total level sales projection values and discount projection value based rebate or RS_CONTRACT_SID & PPA for both actual & projection
						                  ,Based on Sales,Discount & PPA calculation we will calculate Netsales,Netprofit values.
										  If we pass input as @screen_name =''  Variance'' we will pass mulptiple projection as input and will display total level aggregation value of both Actual sales & projection values for only contract Sales WAC remaining will display only projection  projections attached to the first PROJECTIONS
						             	  screen_name<> assumptions it will display rest of projections display aggregation of projections attached to each projecton as pivot view
                        @VIEW           - if we pass view as pivot means result will be order by year and period other than pivot it will order by year and period

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
   DECLARE @COMPANY_ID            INT,
                  @STARTFROM             DATE,
                  @PROJECTION_DATE       DATETIME,
                  @SP                    INT,
                  @SP_PROJ_SID           INT,
                  @TEMP_PROJ_SID         VARCHAR(500),
                  @START_PERIOD_SID      INT,
                  @END_PERIOD_SID        INT,
                  @ITEM_UDT              UDT_ITEM,
                  @FORECAST_NAME         VARCHAR(50),
                  @FORECAST_VERSION      VARCHAR(15),
                  @FORECAST_NAME_INV     VARCHAR(50),
                  @FORECAST_VERSION_INV  VARCHAR(15),
                  @FORECAST_NAME_DM      VARCHAR(50),
                  @FORECAST_VERSION_DM   VARCHAR(15),
                  @ITEM_UOM              VARCHAR(50) = 'EACH',
                  @START_DATE            DATETIME,
                  @PROJ_START_PERIOD_SID INT,
                  @BUSINESS_UNIT         INT, --------------808 IMPACT
				  @CURRENT_DATE          DATE
  ---------------------------------------------------main table changes----------------------------------------
DECLARE @SALES_ACTUAL_TABLE     VARCHAR(200) = 'NM_ACTUAL_SALES'
        ,@SALES_PROJECTION_TABLE VARCHAR(200) = 'NM_SALES_PROJECTION'
        ,@DISC_PROJECTION_TABLE  VARCHAR(200) = 'NM_DISCOUNT_PROJECTION'
        ,@DISC_ACTUAL_TABLE      VARCHAR(200) = 'NM_ACTUAL_DISCOUNT'
        ,@PPA_PROJECTION_TABLE   VARCHAR(200) = 'NM_PPA_PROJECTION'
        ,@PPA_ACTUAL_TABLE       VARCHAR(200) = 'NM_ACTUAL_PPA'

          -----------------PASSING MULTIPLE PROJECTIONS AS INPUT CONVERTING INTO COMMA SEPERATED VALUES --------------------------
          IF Object_id('TEMPDB..#PROJECTION_MASTER') IS NOT NULL
            DROP TABLE #PROJECTION_MASTER

          CREATE TABLE #PROJECTION_MASTER
            (
               ID                    INT IDENTITY(1, 1),
               PROJECTION_MASTER_SID INT
            )
----------------------------------------------------------STORING THE OUTPUT RESULT OF PROJECTION VARIANCE TABLE--------------------------------------
         
          IF Object_id('TEMPDB..#PIVOT_RESULT') IS NOT NULL
            DROP TABLE #PIVOT_RESULT
          CREATE TABLE #PIVOT_RESULT
            (
               PROJECTION_ID                                INT,
               [PERIOD]                                     SMALLINT,
               [YEAR]                                       INT,
			  EX_FACTORY_SALES_ACTUALS    NUMERIC(22, 6),
              EX_FACTORY_SALES_PROJECTED    NUMERIC(22, 6),
			  DEMAND_SALES_ACTUALS    NUMERIC(22, 6),
              DEMAND_SALES_PROJECTED    NUMERIC(22, 6),
			  INVENTORY_WITHDRAWAL_SALES_ACTUALS    NUMERIC(22, 6),
              INVENTORY_WITHDRAWAL_SALES_PROJECTED    NUMERIC(22, 6),
			  EX_FACTORY_SALES_ACTUALS_PERCENT    NUMERIC(22, 6),
              EX_FACTORY_SALES_PROJECTED_PERCENT    NUMERIC(22, 6),
              DEMAND_SALES_ACTUALS_PERCENT    NUMERIC(22, 6),
		      DEMAND_SALES_PROJECTED_PERCENT    NUMERIC(22, 6),
			  INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT    NUMERIC(22, 6),
              INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT    NUMERIC(22, 6),
			  CONTRACT_SALES_ACTUALS    NUMERIC(22, 6),
              CONTRACT_SALES_PROJECTED    NUMERIC(22, 6),
			  CONTRACT_UNITS_ACTUALS    NUMERIC(22, 6),
              CONTRACT_UNITS_PROJECTED    NUMERIC(22, 6),
			  TOTAL_DISCOUNT    NUMERIC(22, 6),
              TOTAL_DISCOUNT_PROJECTED    NUMERIC(22, 6),
			  TOTAL_DISCOUNT_PERCENTAGE    NUMERIC(22, 6),
              TOTAL_DISCOUNT_PROJECTED_PERCENTAGE    NUMERIC(22, 6),
			  PPA_DISCOUNT_ACTUALS    NUMERIC(22, 6),
              PPA_DISCOUNT_PROJECTED    NUMERIC(22, 6),
			  PPA_DISCOUNT_ACTUALS_PERCENTAGE    NUMERIC(22, 6),
              PPA_DISCOUNT_PROJECTED_PERCENTAGE    NUMERIC(22, 6),
			  TOTAL_DISCOUNT_RPU_ACTUALS    NUMERIC(22, 6),
              TOTAL_DISCOUNT_RPU_PROJECTED    NUMERIC(22, 6),
			  TOTAL_PPA_RPU_ACTUALS    NUMERIC(22, 6),
              TOTAL_PPA_RPU_PROJECTED    NUMERIC(22, 6),
			  TOTAL_RPU_ACTUAL    NUMERIC(22, 6),
              TOTAL_RPU_PROJECTED    NUMERIC(22, 6),
			  NET_SALES    NUMERIC(22, 6),
              NET_SALES_PROJECTED    NUMERIC(22, 6),
			  COGS_ACTUALS    NUMERIC(22, 6),
              COGS_PROJECTED    NUMERIC(22, 6),
			  NET_PROFIT_ACTUAL    NUMERIC(22, 6),
              NET_PROFIT_PROJECTED    NUMERIC(22, 6),
			  TOTAL_RETURNS_RPU_ACTUALS    NUMERIC(22, 6),
              TOTAL_RETURNS_RPU_PROJECTED    NUMERIC(22, 6),
			  RETURNS_ACTUALS_AMOUNT    NUMERIC(22, 6),
              RETURNS_PROJECTED_AMOUNT    NUMERIC(22, 6),
			  RETURNS_ACTUALS_PERCENTAGE    NUMERIC(22, 6),
              RETURNS_PROJECTED_PERCENTAGE NUMERIC(22, 6),
			 NET_SALES_OF_EX_FACTORY_ACTUALS numeric(22,6),
			NET_SALES_OF_EX_FACTORY_PROJECTED numeric(22,6)
			,
			DISCOUNT_OF_EX_FACTORY_ACTUALS numeric(22,6),
			DISCOUNT_OF_EX_FACTORY_PROJECTED NUMERIC(22,6)
            )

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
-------------------taking the first projection which will be the current projection------------
          DECLARE @FIRST_PROJ_SID INT

          SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID
          FROM   #PROJECTION_MASTER PM
          WHERE  ID = 1

          SELECT TOP 1 @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0),
                       @START_DATE = Dateadd(dd, 1, Eomonth(FROM_DATE, -1)),
                       @PROJECTION_DATE = Dateadd(dd, 1, Eomonth(TO_DATE, -1))
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
		  ORDER BY PROJECTION_MASTER_SID
 -------------------------------GALUAT_29 CHANGES----------------------------
          ------PULLING ACTUAL START_DATE , ACTUAL_END_DATE BASED ON CURRENT -3 CONCEPT & PROJ_START_PERIOD_DATE,PROJ_END_PERIOD_DATE BASED ON PROJECTION FROM PROJECTION MASTER TABLE -----------
        
          SELECT @START_PERIOD_SID = Max(CASE
                                           WHEN PERIOD_DATE = @STARTFROM THEN PERIOD_SID
                                         END),
                 @END_PERIOD_SID = Max(CASE
                                         WHEN PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0) THEN PERIOD_SID
                                       END),
                 @PROJ_START_PERIOD_SID = Max(CASE
                                                WHEN PERIOD_DATE = @START_DATE THEN PERIOD_SID
                                              END)
          FROM   PERIOD
          WHERE  PERIOD_DATE IN ( @STARTFROM, @PROJECTION_DATE, @START_DATE )
		  SET @CURRENT_DATE = CASE
                      WHEN LEFT(@PROJ_FREQUENCY, 1) = 'M' THEN Dateadd(DD, 1, Eomonth(Getdate(), -1))
                      WHEN LEFT(@PROJ_FREQUENCY, 1) = 'Q' THEN Datefromparts(Year(Getdate()), Datepart(QUARTER, Getdate()), 01)
                      WHEN LEFT(@PROJ_FREQUENCY, 1) = 'S' THEN Datefromparts(Year(Getdate()), ( ( ( ( ( Month(Getdate()) ) - 1 ) / 6 ) * 6 ) + 1 ), 01)
                      ELSE Datefromparts(Year(Getdate()), 01, 01)
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
          SELECT IM.ITEM_ID,
                 IM.NDC8,
                 IM.ITEM_MASTER_SID
          FROM   ITEM_MASTER IM
          WHERE  EXISTS (SELECT 1
                         FROM   CCP_DETAILS CCP
                                INNER JOIN PROJECTION_DETAILS PD
                                        ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                         WHERE  CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)

          SELECT @COMPANY_ID = CM.COMPANY_MASTER_SID
          FROM   PROJECTION_MASTER PM
                 INNER JOIN COMPANY_MASTER CM
                         ON PM.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID
          WHERE  PM.PROJECTION_MASTER_SID = @FIRST_PROJ_SID

          INSERT INTO @ITEM_UDT
                      (ITEM_MASTER_SID)
          SELECT DISTINCT ITEM_MASTER_SID
          FROM   @ITEM_INFO
    -------------------------pulling ccp inforamtion for particular projection based on input projection---------------------------------
          IF Object_id('TEMPDB..#TEMP_CCP') IS NOT NULL
            DROP TABLE #TEMP_CCP

          CREATE TABLE #TEMP_CCP
            (
               COMPANY_MASTER_SID     INT,
               CONTRACT_MASTER_SID    INT,
               ITEM_MASTER_SID        INT,
               PROJECTION_DETAILS_SID INT PRIMARY KEY,
               PROJECTION_MASTER_SID  INT,
               BUSINESS_UNIT          INT
            )

          INSERT INTO #TEMP_CCP
                      (COMPANY_MASTER_SID,
                       CONTRACT_MASTER_SID,
                       ITEM_MASTER_SID,
                       PROJECTION_DETAILS_SID,
                       PROJECTION_MASTER_SID,
                       BUSINESS_UNIT)
          SELECT CCP.COMPANY_MASTER_SID,
                 CCP.CONTRACT_MASTER_SID,
                 CCP.ITEM_MASTER_SID,
                 PD.PROJECTION_DETAILS_SID,
                 PM.PROJECTION_MASTER_SID,
                 PM.BUSINESS_UNIT
          FROM   CCP_DETAILS CCP
                 INNER JOIN PROJECTION_DETAILS PD
                         ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                 INNER JOIN PROJECTION_MASTER PM
                         ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
          WHERE  PM.PROJECTION_MASTER_SID = @FIRST_PROJ_SID

          ------------------------------------GAL-808-------------------------------------
          SELECT @BUSINESS_UNIT = BUSINESS_UNIT
          FROM   #TEMP_CCP

          -------------------------------------------------------------------------------------
          DECLARE @ITEMID [DBO].[UDT_ITEM]

          INSERT INTO @ITEMID
          SELECT IM.ITEM_MASTER_SID
          FROM   ITEM_MASTER IM
          WHERE  EXISTS (SELECT 1
                         FROM   #TEMP_CCP A
                         WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID)
-----------taking the active file inforamtion from projection file details which will store the active file information ------------------
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
                         Row_number()
                           OVER (
                             PARTITION BY FILE_TYPE
                             ORDER BY FILE_MANAGEMENT_SID DESC ) AS RN
                  FROM   FILE_MANAGEMENT FT
                         INNER JOIN HELPER_TABLE HT
                                 ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
                  WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
                           AND FT.FROM_PERIOD IS NOT NULL )
                         AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
                                OR FT.TO_PERIOD IS NULL )
                         AND HT.LIST_NAME = 'FILE_TYPE'
                         AND HT.[DESCRIPTION] IN ( 'EX-FACTORY SALES', 'DEMAND', 'Inventory Withdrawal - Forecast Summary' )
                         AND FT.BUSINESS_UNIT = @BUSINESS_UNIT
                         AND FT.COMPANY = @COMPANY_ID) A
          WHERE  RN = 1

          SELECT @FORECAST_NAME = FORECAST_NAME,
                 @FORECAST_VERSION = [VERSION]
          FROM   @FILE_VER
          WHERE  FILE_TYPE = 'EX-FACTORY SALES'

          SELECT @FORECAST_NAME_INV = FORECAST_NAME,
                 @FORECAST_VERSION_INV = [VERSION]
          FROM   @FILE_VER
          WHERE  FILE_TYPE = 'Inventory Withdrawal - Forecast Summary'

          SELECT @FORECAST_NAME_DM = FORECAST_NAME,
                 @FORECAST_VERSION_DM = [VERSION]
          FROM   @FILE_VER
          WHERE  FILE_TYPE = 'DEMAND'
----------pulling the 'EX-FACTORY SALES' information----------------
          IF Object_id('TEMPDB..#TEMP_GTS_DETAILS') IS NOT NULL
            DROP TABLE #TEMP_GTS_DETAILS

          CREATE TABLE #TEMP_GTS_DETAILS
            (
               ITEMID              VARCHAR(50),
               GTS_SALES_PROJECTED NUMERIC(22, 6),
               GTS_SALES_ACTUALS   NUMERIC(22, 6),
               UNITS               NUMERIC(22, 6),
               MONTHLY             INT,
               QUARTERLY           INT,
               YEARLY              INT
            )

          INSERT INTO #TEMP_GTS_DETAILS
                      (ITEMID,
                       GTS_SALES_PROJECTED,
                       GTS_SALES_ACTUALS,
                       UNITS,
                       MONTHLY,
                       QUARTERLY,
                       YEARLY)
          SELECT DISTINCT II.ITEM_ID,
                           COALESCE(A.FORECAST_GTS_SALES, A.ACTUAL_GTS_SALES) as FORECAST_GTS_SALES ,
                          A.ACTUAL_GTS_SALES,
                           COALESCE(A.FORECAST_GTS_UNITS, A.ACTUAL_GTS_UNITS) as UNITS ,
                          P.MONTH,
                          P.QUARTER,
                          P.YEAR
          FROM   Udf_gts_wac(@ITEM_UDT, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME, @FORECAST_VERSION) A
                 INNER JOIN PERIOD P
                         ON P.PERIOD_SID = A.PERIOD_SID
                 INNER JOIN ITEM_MASTER II
                         ON II.ITEM_MASTER_SID = A.ITEM_MASTER_SID

          -----------------------------------------------------INVENTORY-------------------------------------------------------------------------------------------------
          IF Object_id('TEMPDB..#INVENTORY') IS NOT NULL
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
          FROM   [DBO].[Udf_inventory_wac](@ITEMID, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME_INV, @FORECAST_VERSION_INV) I
                 INNER JOIN PERIOD P
                         ON P.PERIOD_SID = I.PERIOD_SID
                 INNER JOIN @ITEMID IM
                         ON I.ITEM_MASTER_SID = IM.ITEM_MASTER_SID

          -----------------------------------------------------DEMAND-------------------------------------------------------------------------------------------------
          IF Object_id('TEMPDB..#DEMAND') IS NOT NULL
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
                       YEARLY,
                       MONTHLY,
                       QUARTERLY,
                       SEMI_ANNUAL)
          SELECT D.ITEM_MASTER_SID,
                 P.PERIOD_SID,
                 ACT_GROSS_AMOUNT,
                 ACT_GROSS_UNITS,
                 COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT) AS FOR_GROSS_AMOUNT,
                 COALESCE(FOR_GROSS_UNITS, ACT_GROSS_UNITS)   AS FOR_GROSS_UNITS,
                 P.YEAR,
                 P.MONTH,
                 P.[QUARTER],
                 P.SEMI_ANNUAL
          FROM   [DBO].[Udf_demand_wac](@ITEMID, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME_DM, @FORECAST_VERSION_DM) D
                 INNER JOIN PERIOD P
                         ON P.PERIOD_SID = D.PERIOD_SID
                 INNER JOIN @ITEMID IM
                         ON D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID

          IF Object_id('TEMPDB..#ITEM_PRICING') IS NOT NULL
            DROP TABLE #ITEM_PRICING

          SELECT ITEM_MASTER_SID,
             PERIOD_SID,
             PRICING_QUALIFIER,
             ITEM_PRICE
          INTO   #ITEM_PRICING
          FROM   [DBO].[Udf_item_pricing](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, @ITEM_UOM)
 -------------------------------------PULLING THE SALES PROJECTION FROM SALES PROJECTION TABLE BASED ON PROJECTION AND STORING IN #NM_SALES_PROJECTION TABLE----------------------------
        
          IF Object_id('TEMPDB..#NM_SALES_PROJECTION') IS NOT NULL
            DROP TABLE #NM_SALES_PROJECTION

		CREATE TABLE #NM_SALES_PROJECTION
		  (
			 [PROJECTION_DETAILS_SID] [INT] NOT NULL,
			 [ACCOUNT_GROWTH]         [NUMERIC](22, 6) NULL,
			 [PRODUCT_GROWTH]         [NUMERIC](22, 6) NULL,
			 [PROJECTION_SALES]       [NUMERIC](22, 6) NULL,
			 [PROJECTION_UNITS]       [NUMERIC](22, 6) NULL,
			 [PERIOD_SID]             [INT] NOT NULL,
			 [ADJUSTMENT_TYPE]        [VARCHAR](20) NULL,
			 [ADJUSTMENT_BASIS]       [VARCHAR](20) NULL,
			 [ADJUSTMENT_VARIABLE]    [BIT] NULL,
			 [ADJUSTMENT_METHODOLOGY] [VARCHAR](50) NULL,
			 [ADJUSTMENT_VALUES]      [NUMERIC](22, 6) NULL
		  ) 

			INSERT INTO #NM_SALES_PROJECTION
						([PROJECTION_DETAILS_SID],
						 [ACCOUNT_GROWTH],
						 [PRODUCT_GROWTH],
						 [PROJECTION_SALES],
						 [PROJECTION_UNITS],
						 [PERIOD_SID],
						 [ADJUSTMENT_TYPE],
						 [ADJUSTMENT_BASIS],
						 [ADJUSTMENT_VARIABLE],
						 [ADJUSTMENT_METHODOLOGY],
						 [ADJUSTMENT_VALUES])
			SELECT nm.[PROJECTION_DETAILS_SID],
				   [ACCOUNT_GROWTH],
				   [PRODUCT_GROWTH],
				   [PROJECTION_SALES],
				   [PROJECTION_UNITS],
				   [PERIOD_SID],
				   [ADJUSTMENT_TYPE],
				   [ADJUSTMENT_BASIS],
				   [ADJUSTMENT_VARIABLE],
				   [ADJUSTMENT_METHODOLOGY],
				   [ADJUSTMENT_VALUES]
			FROM   NM_SALES_PROJECTION nm
				   JOIN PROJECTION_DETAILS pd
					 ON pd.PROJECTION_DETAILS_SID = nm.PROJECTION_DETAILS_SID
						AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
														 FROM   #PROJECTION_MASTER PM
														 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID) 

  ------------------------------------IN SCREEN NAME ='ASSUMPTION' WILL DISPLAY BOTH ACTUAL & PROJECTION VALUE FOR TOTAL LEVEL OF CONTRACT SALE,DISCOUNT AMOUNT,DISOCUNT PROJECTION & PPA.
        
          IF @SCREEN_NAME = 'ASSUMPTIONS'
            BEGIN
                IF @PROJ_FREQUENCY = 'MONTHLY' ------------------------------CALCULATING OR AGGREGATING THE MONTH WISE 
                  BEGIN

				  DECLARE @SQL_M NVARCHAR(MAX)= ''
                      SET  @SQL_M = 'SELECT @PROJECTION_SID PROJECTION_ID,
                             EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                             EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                             GTS_UNITS = Isnull(GTS.UNITS, 0),
                             P.[MONTH],
                             P.[YEAR],
                             CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                             CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                             CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                             CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                             TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) +
                             --                              + COALESCE(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(PPA.PPA_ACTUAL_SALES, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) +
                             --                                        + COALESCE(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(PPA.PPA_PROJECTION_SALES, 0), 0) ) * 100,
                             ----DISC.CONTRACT_DISCOUNT_ACTUALS,  SALES.CONTRACT_SALES_ACTUALS,    PPA.PPA_DISCOUNT_ACTUALS,  PPA.PPA_ACTUAL_SALES,   
                             EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                             PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                             PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
                             NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
                             NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
                             DEMAND_SALES_ACTUAL = Isnull(DEMAND.DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = Isnull(DEMAND.DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = Isnull(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = Isnull(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             COGS_ACTUALS = Isnull(SALES.COGS_ACTUALS, 0),
                             COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUALS, 0) ) ),
                             NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
                             RETURNS_ACTUAL_AMOUNT = 000000.0,
                             RETURNS_ACTUAL_PERCENTAGE = 000000.0,
                             RETURNS_PROJECTED_AMOUNT = 000000.0,
                             RETURNS_PROJECTED_PERCENTAGE = 000000.0,
                             TOTAL_RETURNS_RPU_ACTAULS = 000000.0,
                             TOTAL_RETURNS_RPU_PROJECTED = 000000.0,
							 NET_SALES_OF_EX_FACTORY_ACTUALS = COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
                             NET_SALES_OF_EX_FACTORY_PROJECTED = COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
 							 ,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							 DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(ACT_AMOUNT_WITHDRAWN),
                                     ACT_UNITS_WITHDRAWN = Sum(ACT_UNITS_WITHDRAWN),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)),
                                     FOR_UNITS_WITHDRAWN = Sum(FOR_UNITS_WITHDRAWN),
                                     MONTHLY,
                                     YEARLY,
                                     PERIOD_SID
                              FROM   #INVENTORY
                              GROUP  BY MONTHLY,
                                        YEARLY,
                                        PERIOD_SID) INVENTORY
                             RIGHT JOIN (SELECT DEMAND_SALES_ACTUAL = Sum(ACT_GROSS_AMOUNT),
                                                ACT_GROSS_UNITS = Sum(ACT_GROSS_UNITS),
                                                DEMAND_SALES_PROJECTED = Sum(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)),
                                                FOR_GROSS_UNITS = Sum(FOR_GROSS_UNITS),
                                                MONTHLY,
                                                YEARLY,
                                                PERIOD_SID
                                         FROM   #DEMAND
                                         GROUP  BY MONTHLY,
                                                   YEARLY,
                                                   PERIOD_SID) DEMAND
                                     ON INVENTORY.PERIOD_SID = DEMAND.PERIOD_SID
                             RIGHT JOIN (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                GTS_SALES_PROJECTED = Sum(COALESCE(GTS_SALES_PROJECTED, GTS_SALES_ACTUALS)),
                                                UNITS = Sum(UNITS),
                                                MONTHLY PERIOD,
                                                YEARLY,
                                                PERIOD_SID
                                         FROM   #TEMP_GTS_DETAILS G
                                                INNER JOIN PERIOD P
                                                        ON P.[MONTH] = G.MONTHLY
                                                           AND P.[YEAR] = G.YEARLY
                                         GROUP  BY MONTHLY,
                                                   YEARLY,
                                                   PERIOD_SID) GTS
                                     ON GTS.PERIOD_SID = DEMAND.PERIOD_SID
                             LEFT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                               PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID),
                                               CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                               CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                               COGS_ACTUALS = Sum(COGS_ACTUAL),
                                               COGS_PROJECTED = Sum(COGS_PROJECTED)
                                        --, ITEM_MASTER_SID = COALESCE(ACT.ITEM_MASTER_SID, PROJ.ITEM_MASTER_SID)
                                        FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                FROM   '+@SALES_ACTUAL_TABLE+' NAS
                                                       INNER JOIN PROJECTION_DETAILS PD
                                                               ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                          FROM   '+@SALES_PROJECTION_TABLE+' NPS
                                                                 INNER JOIN PROJECTION_DETAILS PD
                                                                         ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID
                                                                            AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SALES
                                    ON SALES.PERIOD_SID = GTS.PERIOD_SID
                             INNER JOIN PERIOD P
                                     ON P.PERIOD_SID = COALESCE(SALES.PERIOD_SID, GTS.PERIOD_SID, DEMAND.PERIOD_SID, INVENTORY.PERIOD_SID)
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                               PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_MODEL_SID
                                                FROM   '+@DISC_ACTUAL_TABLE+' NAD
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                       AND EXISTS (SELECT 1
                                                                   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                   WHERE  A.TOKEN = NAD.RS_MODEL_SID)
                                               --AND NAD.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_MODEL_SID
                                                          FROM   '
														  +@DISC_PROJECTION_TABLE+' NDP
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                 AND EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_MODEL_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) DISC
                                    ON DISC.PERIOD_SID = P.PERIOD_SID
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_MODEL_SID,
                                                       ACTUAL_SALES = NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS = NS.ACTUAL_UNITS
                                                FROM   '+@PPA_ACTUAL_TABLE+' NAP
                                                       INNER JOIN '+@SALES_ACTUAL_TABLE+' NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                               --AND NAP.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_MODEL_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   '+@PPA_PROJECTION_TABLE+' NPP
                                                                 INNER JOIN '+@SALES_PROJECTION_TABLE+' NS
                                                                         ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                 AND NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) PPA
                                    ON PPA.PERIOD_SID = SALES.PERIOD_SID
                      ORDER  BY SALES.PERIOD_SID'

					 

			EXEC Sp_executesql
			  @SQL_M,
			  N'@PROJECTION_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID VARCHAR(8000),@START_PERIOD_SID INT',
			  @PROJECTION_SID = @PROJECTION_SID,
			  @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
			  @END_PERIOD_SID = @END_PERIOD_SID,
			  @START_PERIOD_SID = @START_PERIOD_SID,
			  @DISCOUNT_ID = @DISCOUNT_ID 

			

                  END


                ELSE IF @PROJ_FREQUENCY = 'QUARTERLY'----------------------------------QUARTERLY WISE AGGREGATING AND GETTIMG THE FILE TYPES ,SALES ,DISOCUN &PPA
                  BEGIN

				    DECLARE @SQL_Q NVARCHAR(MAX)= ''
                      SET  @SQL_Q =
                      concat('SELECT @PROJECTION_SID PROJECTION_ID,
                             EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                             EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                             GTS_UNITS = Isnull(GTS.UNITS, 0),
                             COALESCE(SALES.[QUARTER], GTS.QUARTERLY) AS [QUARTER],
                             COALESCE(SALES.[YEAR], GTS.YEARLY)       AS [YEAR],
                             CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                             CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                             CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                             CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                             TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                                      EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                             PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                             PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
                             NET_SALES = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                     + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ),
                             NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
                             DEMAND_SALES_ACTUAL = Isnull(DEMAND.DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = Isnull(DEMAND.DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = Isnull(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = Isnull(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             COGS_ACTUAL = Isnull(SALES.COGS_ACTUAL, 0),
                             COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUAL, 0) ) ),
                             NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
                             RETURNS_ACTUAL_AMOUNT = 000000.0,
                             RETURNS_ACTUAL_PERCENTAGE = 000000.0,
                             RETURNS_PROJECTED_AMOUNT = 000000.0,
                             RETURNS_PROJECTED_PERCENTAGE = 000000.0,
                             TOTAL_RETURNS_RPU_ACTAULS = 000000.0,
                             TOTAL_RETURNS_RPU_PROJECTED = 000000.0,
							 NET_SALES_OF_EX_FACTORY_ACTUALS = COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
                             NET_SALES_OF_EX_FACTORY_PROJECTED = COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
 							 ,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							 DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(ACT_AMOUNT_WITHDRAWN),
                                     ACT_UNITS_WITHDRAWN = Sum(ACT_UNITS_WITHDRAWN),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)),
                                     FOR_UNITS_WITHDRAWN = Sum(FOR_UNITS_WITHDRAWN),
                                     QUARTERLY,
                                     YEARLY
                              FROM   #INVENTORY
                              GROUP  BY QUARTERLY,
                                        YEARLY) INVENTORY
                             RIGHT JOIN (SELECT DEMAND_SALES_ACTUAL = Sum(ACT_GROSS_AMOUNT),
                                                ACT_GROSS_UNITS = Sum(ACT_GROSS_UNITS),
                                                DEMAND_SALES_PROJECTED = Sum(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)),
                                                FOR_GROSS_UNITS = Sum(FOR_GROSS_UNITS),
                                                QUARTERLY,
                                                YEARLY
                                         FROM   #DEMAND
                                         GROUP  BY QUARTERLY,
                                                   YEARLY) DEMAND
                                     ON INVENTORY.QUARTERLY = DEMAND.QUARTERLY
                                        AND INVENTORY.YEARLY = DEMAND.YEARLY
                             RIGHT JOIN (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
                                                UNITS = Sum(UNITS),
                                                QUARTERLY,
                                                YEARLY
                                         FROM   #TEMP_GTS_DETAILS G
                                         GROUP  BY QUARTERLY,
                                                   YEARLY) GTS
                                     ON GTS.QUARTERLY = DEMAND.QUARTERLY
                                        AND GTS.YEARLY = DEMAND.YEARLY
                             LEFT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                               [QUARTER],
                                               [YEAR],
                                               CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                               CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                               COGS_ACTUAL = Sum(COGS_ACTUAL),
                                               COGS_PROJECTED = Sum(COGS_PROJECTED)
                                        FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                FROM   ',@SALES_ACTUAL_TABLE,' NAS
                                                       INNER JOIN PROJECTION_DETAILS PD
                                                               ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                          FROM   ',@SALES_PROJECTION_TABLE,' NPS
                                                                 INNER JOIN PROJECTION_DETAILS PD
                                                                         ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID
                                                                            AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) SALES
                                    ON SALES.QUARTER = GTS.QUARTERLY
                                       AND SALES.YEAR = GTS.YEARLY
                         
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = ( ACTUAL_SALES ),
                                               CONTRACT_DISCOUNT_PROJECTED = ( PROJECTION_SALES ),
                                               COALESCE(ACT.[QUARTER], PROJ.QUARTER) QUARTER,
                                               COALESCE(ACT.[YEAR], PROJ.YEAR)       YEAR
                                        FROM   (SELECT P.QUARTER,
                                               P.YEAR,
                                               Sum(ACTUAL_SALES) ACTUAL_SALES
                                                FROM   ',@DISC_ACTUAL_TABLE,' NAD
                                                       INNER JOIN PERIOD P
                                                               ON P.PERIOD_SID = NAD.PERIOD_SID
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                       AND EXISTS (SELECT 1
                                                                   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                   WHERE  A.TOKEN = NAD.RS_MODEL_SID)
                                                GROUP  BY P.QUARTER,
                                                          P.YEAR) ACT
                                               FULL JOIN (SELECT P.QUARTER,
                                                         P.YEAR,
                                                         Sum(PROJECTION_SALES) PROJECTION_SALES
                                                          FROM   ',@DISC_PROJECTION_TABLE,' NDP
                                                                 INNER JOIN PERIOD P
                                                                         ON P.PERIOD_SID = NDP.PERIOD_SID
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                 AND EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_MODEL_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID
                                                          GROUP  BY P.QUARTER,
                                                                    P.YEAR) PROJ
                                                      ON 
                                               ACT.YEAR = PROJ.YEAR
                                               AND ACT.QUARTER = PROJ.QUARTER
                                       ) DISC
                                    ON DISC.[QUARTER] = SALES.[QUARTER]
                                       AND DISC.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               [QUARTER],
                                               [YEAR]
                                        FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_MODEL_SID,
                                                       ACTUAL_SALES =NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS =NS.ACTUAL_UNITS
                                                FROM   ',@PPA_ACTUAL_TABLE,' NAP
                                                       INNER JOIN ',@SALES_ACTUAL_TABLE,' NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                               ) ACT
                                               FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_MODEL_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   ',@PPA_PROJECTION_TABLE,' NPP
                                                                 INNER JOIN ',@SALES_PROJECTION_TABLE,' NS
                                                                         ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                 AND NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) PPA
                                    ON PPA.[QUARTER] = SALES.[QUARTER]
                                       AND PPA.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY),
                                COALESCE(SALES.[QUARTER], GTS.QUARTERLY)')

			EXEC Sp_executesql
			  @SQL_Q,
			  N'@PROJECTION_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID VARCHAR(8000),@START_PERIOD_SID INT',
			  @PROJECTION_SID = @PROJECTION_SID,
			  @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
			  @END_PERIOD_SID = @END_PERIOD_SID,
			  @START_PERIOD_SID = @START_PERIOD_SID,
			  @DISCOUNT_ID = @DISCOUNT_ID 

                  END

                ELSE IF @PROJ_FREQUENCY = 'SEMI-ANNUAL'------------------SEMI-ANNUAL WISE AGGREGATING AND GETTIMG THE FILE TYPES ,SALES ,DISOCUN &PPA
                  BEGIN
				      DECLARE @SQL_S NVARCHAR(MAX)= ''
                      SET  @SQL_S =
                      concat('SELECT @PROJECTION_SID   PROJECTION_ID,
                             EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                             EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                             GTS_UNITS = Isnull(GTS.UNITS, 0),
                             COALESCE(SALES.SEMI_ANNUAL, GTS.SEMI_ANNUAL)SEMI_ANNUAL,
                             COALESCE(SALES.[YEAR], GTS.YEARLY)          AS YEAR,
                             CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                             CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                             CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                             CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                             TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) +
                             --                              + COALESCE(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(PPA.PPA_ACTUAL_SALES, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) +
                             --                                        + COALESCE(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(PPA.PPA_PROJECTION_SALES, 0), 0) ) * 100,
                             ----DISC.CONTRACT_DISCOUNT_ACTUALS,  SALES.CONTRACT_SALES_ACTUALS,    PPA.PPA_DISCOUNT_ACTUALS,  PPA.PPA_ACTUAL_SALES,   
                             EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                             PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                             PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
                             NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
                             NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
                             DEMAND_SALES_ACTUAL = Isnull(DEMAND.DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = Isnull(DEMAND.DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = Isnull(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = Isnull(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             --TOTAL_RPU_ACTUAL = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0)
                             --                     + COALESCE(PPA.PPA_RPU_ACTUALS / NULLIF(PPA.PPA_ACTUAL_UNITS, 0), 0) ),
                             --TOTAL_RPU_PROJECTED = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0)
                             --                        + COALESCE(PPA.PPA_RPU_PROJECTED / NULLIF(PPA.PPA_PROJECTION_UNITS, 0), 0) ),
                             COGS_ACTUALS = Isnull(SALES.COGS_ACTUAL, 0),
                             COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUAL, 0) ) ),
                             NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
                             RETURNS_ACTUAL_AMOUNT = 000000.0,
                             RETURNS_ACTUAL_PERCENTAGE = 000000.0,
                             RETURNS_PROJECTED_AMOUNT = 000000.0,
                             RETURNS_PROJECTED_PERCENTAGE = 000000.0,
                             TOTAL_RETURNS_RPU_ACTAULS = 000000.0,
                             TOTAL_RETURNS_RPU_PROJECTED = 000000.0,
							 NET_SALES_OF_EX_FACTORY_ACTUALS = COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
                             NET_SALES_OF_EX_FACTORY_PROJECTED = COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
 							 ,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							 DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(ACT_AMOUNT_WITHDRAWN),
                                     ACT_UNITS_WITHDRAWN = Sum(ACT_UNITS_WITHDRAWN),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)),
                                     FOR_UNITS_WITHDRAWN = Sum(FOR_UNITS_WITHDRAWN),
                                     CASE
                                       WHEN QUARTERLY <= 2 THEN 1
                                       ELSE 2
                                     END SEMI_ANNUAL,
                                     YEARLY
                              FROM   #INVENTORY
                              GROUP  BY CASE
                                          WHEN QUARTERLY <= 2 THEN 1
                                          ELSE 2
                                        END,
                                        YEARLY) INVENTORY
                             RIGHT JOIN (SELECT DEMAND_SALES_ACTUAL = Sum(ACT_GROSS_AMOUNT),
                                                ACT_GROSS_UNITS = Sum(ACT_GROSS_UNITS),
                                                DEMAND_SALES_PROJECTED = Sum(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)),
                                                FOR_GROSS_UNITS = Sum(FOR_GROSS_UNITS),
                                                CASE
                                                  WHEN QUARTERLY <= 2 THEN 1
                                                  ELSE 2
                                                END SEMI_ANNUAL,
                                                YEARLY
                                         FROM   #DEMAND
                                         GROUP  BY CASE
                                                     WHEN QUARTERLY <= 2 THEN 1
                                                     ELSE 2
                                                   END,
                                                   YEARLY) DEMAND
                                     ON INVENTORY.SEMI_ANNUAL = DEMAND.SEMI_ANNUAL
                                        AND INVENTORY.YEARLY = DEMAND.YEARLY
                             RIGHT JOIN (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
                                                Sum(GTS.UNITS) UNITS,
                                                CASE
                                                  WHEN GTS.QUARTERLY <= 2 THEN 1
                                                  ELSE 2
                                                END            SEMI_ANNUAL,
                                                GTS.YEARLY
                                         FROM   #TEMP_GTS_DETAILS GTS
                                         GROUP  BY CASE
                                                     WHEN GTS.QUARTERLY <= 2 THEN 1
                                                     ELSE 2
                                                   END,
                                                   GTS.YEARLY) GTS
                                     ON DEMAND.SEMI_ANNUAL = GTS.SEMI_ANNUAL
                                        AND DEMAND.YEARLY = GTS.YEARLY
                             RIGHT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                                CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                                SEMI_ANNUAL,
                                                [YEAR],
                                                CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                                CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                                COGS_ACTUAL = Sum(COGS_ACTUAL),
                                                COGS_PROJECTED = Sum(COGS_PROJECTED)
                                         FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                        NAS.PERIOD_SID,
                                                        ACTUAL_SALES,
                                                        ACTUAL_UNITS,
                                                        CCP.ITEM_MASTER_SID,
                                                        COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                 FROM   ',@SALES_ACTUAL_TABLE,' NAS
                                                        INNER JOIN PROJECTION_DETAILS PD
                                                                ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                        INNER JOIN CCP_DETAILS CCP
                                                                ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                        INNER JOIN #ITEM_PRICING U
                                                                ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                   AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID
                                                --AND NAS.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                                ) ACT
                                                FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                  NPS.PERIOD_SID,
                                                                  PROJECTION_SALES,
                                                                  PROJECTION_UNITS,
                                                                  CCP.ITEM_MASTER_SID,
                                                                  COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                           FROM   ',@SALES_PROJECTION_TABLE,' NPS
                                                                  INNER JOIN PROJECTION_DETAILS PD
                                                                          ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                  INNER JOIN CCP_DETAILS CCP
                                                                          ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                  INNER JOIN #ITEM_PRICING U
                                                                          ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                             AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID
                                                                             AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                       ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                          AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                INNER JOIN PERIOD P
                                                        ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                         GROUP  BY SEMI_ANNUAL,
                                                   [YEAR]) SALES
                                     ON SALES.SEMI_ANNUAL = GTS.SEMI_ANNUAL
                                        AND SALES.YEAR = GTS.YEARLY
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                               SEMI_ANNUAL,
                                               [YEAR]
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_MODEL_SID
                                                FROM   ',@DISC_ACTUAL_TABLE,' NAD
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                       AND EXISTS (SELECT 1
                                                                   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                   WHERE  A.TOKEN = NAD.RS_MODEL_SID)
                                               -- AND NAD.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_MODEL_SID
                                                          FROM   ',@DISC_PROJECTION_TABLE,' NDP
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                 AND EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_MODEL_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY SEMI_ANNUAL,
                                                  [YEAR]) DISC
                                    ON DISC.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                       AND DISC.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               SEMI_ANNUAL,
                                               [YEAR]
                                        FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_MODEL_SID,
                                                       ACTUAL_SALES = NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS = NS.ACTUAL_UNITS
                                                FROM   ',@PPA_ACTUAL_TABLE,' NAP
                                                       INNER JOIN ',@SALES_ACTUAL_TABLE,' NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                               -- AND NAP.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_MODEL_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   ',@PPA_PROJECTION_TABLE,' NPP
                                                                 INNER JOIN ',@SALES_PROJECTION_TABLE,' NS
                                                                         ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                 AND NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY SEMI_ANNUAL,
                                                  [YEAR]) PPA
                                    ON PPA.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                       AND PPA.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY),
                                COALESCE(SALES.SEMI_ANNUAL, GTS.SEMI_ANNUAL)')


			EXEC Sp_executesql
			  @SQL_S,
			  N'@PROJECTION_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID VARCHAR(8000),@START_PERIOD_SID INT',
			  @PROJECTION_SID = @PROJECTION_SID,
			  @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
			  @END_PERIOD_SID = @END_PERIOD_SID,
			  @START_PERIOD_SID = @START_PERIOD_SID,
			  @DISCOUNT_ID = @DISCOUNT_ID 


                  END
                ELSE
                  BEGIN

---------------------YEARLY WISE AGGREGATING AND GETTIMG THE FILE TYPES ,SALES ,DISOCUN &PPA
				  DECLARE @SQL_A NVARCHAR(MAX) = ''
				  SET @SQL_A = 
                      CONCAT('SELECT @PROJECTION_SID                    PROJECTION_ID,
                             EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                             EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                             GTS_UNITS = Isnull(GTS.UNITS, 0),
                             COALESCE(SALES.[YEAR], gts.yearly) AS YEAR,
                             CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                             CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                             CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
                             CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                             TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) +
                             --                              + COALESCE(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(PPA.PPA_ACTUAL_SALES, 0), 0) ) * 100,
                             --TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) +
                             --                                        + COALESCE(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(PPA.PPA_PROJECTION_SALES, 0), 0) ) * 100,
                             ----DISC.CONTRACT_DISCOUNT_ACTUALS,  SALES.CONTRACT_SALES_ACTUALS,    PPA.PPA_DISCOUNT_ACTUALS,  PPA.PPA_ACTUAL_SALES,   
                             EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                             PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                             PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                             PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                             PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
                             NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
                             NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
                             DEMAND_SALES_ACTUAL = Isnull(DEMAND.DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = Isnull(DEMAND.DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = Isnull(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = Isnull(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             --TOTAL_RPU_ACTUAL = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0)
                             --                     + COALESCE(PPA.PPA_RPU_ACTUALS / NULLIF(PPA.PPA_ACTUAL_UNITS, 0), 0) ),
                             --TOTAL_RPU_PROJECTED = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0)
                             --                        + COALESCE(PPA.PPA_RPU_PROJECTED / NULLIF(PPA.PPA_PROJECTION_UNITS, 0), 0) ),
                             COGS_ACTUALS = Isnull(SALES.COGS_ACTUAL, 0),
                             COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUAL, 0) ) ),
                             NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
                             RETURNS_ACTUAL_AMOUNT = 000000.0,
                             RETURNS_ACTUAL_PERCENTAGE = 000000.0,
                             RETURNS_PROJECTED_AMOUNT = 000000.0,
                             RETURNS_PROJECTED_PERCENTAGE = 000000.0,
                             TOTAL_RETURNS_RPU_ACTAULS = 000000.0,
                             TOTAL_RETURNS_RPU_PROJECTED = 000000.0,
							 NET_SALES_OF_EX_FACTORY_ACTUALS = COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
                             NET_SALES_OF_EX_FACTORY_PROJECTED = COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
 							 ,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							 DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100

                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(ACT_AMOUNT_WITHDRAWN),
                                     ACT_UNITS_WITHDRAWN = Sum(ACT_UNITS_WITHDRAWN),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)),
                                     FOR_UNITS_WITHDRAWN = Sum(FOR_UNITS_WITHDRAWN),
                                     YEARLY
                              FROM   #INVENTORY
                              GROUP  BY YEARLY) INVENTORY
                             RIGHT JOIN (SELECT DEMAND_SALES_ACTUAL = Sum(ACT_GROSS_AMOUNT),
                                                ACT_GROSS_UNITS = Sum(ACT_GROSS_UNITS),
                                                DEMAND_SALES_PROJECTED = Sum(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)),
                                                FOR_GROSS_UNITS = Sum(FOR_GROSS_UNITS),
                                                YEARLY
                                         FROM   #DEMAND
                                         GROUP  BY YEARLY) DEMAND
                                     ON INVENTORY.YEARLY = DEMAND.YEARLY
                             RIGHT JOIN (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
                                                Sum(GTS.UNITS) UNITS,
                                                GTS.YEARLY
                                         FROM   #TEMP_GTS_DETAILS GTS
                                         --JOIN @ITEM_INFO ACT
                                         --  ON GTS.ITEMID = ACT.ITEM_ID
                                         GROUP  BY GTS.YEARLY) GTS
                                     ON DEMAND.YEARLY = GTS.YEARLY
                             RIGHT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                                CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                                [YEAR],
                                                CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                                CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                                COGS_ACTUAL = Sum(COGS_ACTUAL),
                                                COGS_PROJECTED = Sum(COGS_PROJECTED)
                                         FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                        NAS.PERIOD_SID,
                                                        ACTUAL_SALES,
                                                        ACTUAL_UNITS,
                                                        CCP.ITEM_MASTER_SID,
                                                        COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                 FROM   ',@SALES_ACTUAL_TABLE,' NAS
                                                        INNER JOIN PROJECTION_DETAILS PD
                                                                ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                        INNER JOIN CCP_DETAILS CCP
                                                                ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                        INNER JOIN #ITEM_PRICING U
                                                                ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                   AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                   AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID
                                                --AND NAS.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                                ) ACT
                                                FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                  NPS.PERIOD_SID,
                                                                  PROJECTION_SALES,
                                                                  PROJECTION_UNITS,
                                                                  CCP.ITEM_MASTER_SID,
                                                                  COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                           FROM   ',@SALES_PROJECTION_TABLE,' NPS
                                                                  INNER JOIN PROJECTION_DETAILS PD
                                                                          ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                  INNER JOIN CCP_DETAILS CCP
                                                                          ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                  INNER JOIN #ITEM_PRICING U
                                                                          ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                             AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID
                                                                             AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                       ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                          AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                INNER JOIN PERIOD P
                                                        ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                         GROUP  BY [YEAR]) SALES
                                     ON SALES.YEAR = GTS.YEARLY
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                               [YEAR]
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_MODEL_SID
                                                FROM   ',@DISC_ACTUAL_TABLE,' NAD
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                       AND EXISTS (SELECT 1
                                                                   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                   WHERE  A.TOKEN = NAD.RS_MODEL_SID)
                                               --  AND NAD.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_MODEL_SID
                                                          FROM   ',@DISC_PROJECTION_TABLE,' NDP
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                 AND EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_MODEL_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [YEAR]) DISC
                                    ON DISC.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               [YEAR]
                                        FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_MODEL_SID,
                                                       ACTUAL_SALES = NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS = NS.ACTUAL_UNITS
                                                FROM   ',@PPA_ACTUAL_TABLE,' NAP
                                                       INNER JOIN ',@SALES_ACTUAL_TABLE,' NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                               -- AND NAP.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_MODEL_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   ',@PPA_PROJECTION_TABLE,' NPP
                                                                 INNER JOIN ',@SALES_PROJECTION_TABLE,' NS
                                                                         ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
                                                                 AND NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY YEAR) PPA
                                    ON PPA.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], gts.yearly)')

								EXEC Sp_executesql
			  @SQL_A,
			  N'@PROJECTION_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID VARCHAR(8000),@START_PERIOD_SID INT',
			  @PROJECTION_SID = @PROJECTION_SID,
			  @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
			  @END_PERIOD_SID = @END_PERIOD_SID,
			  @START_PERIOD_SID = @START_PERIOD_SID,
			  @DISCOUNT_ID = @DISCOUNT_ID 

END

---SELECT 1
END
        ELSE -- SCREEN NAME = VARIANCE-----------------------WILL DISPLSY BOTH ACTUAL VALUES & PROJECTION ONLY FOR CONTRACT SALES REMAING WILL DISPLAY ONLY PROJECTION VALUE AND WILL PASS INPUT AS MULTIPLE PROJECTION
            BEGIN
                IF @VIEW = 'PIVOT'---FIRST PROJECTION_DETAILS_SID (PULL FROM ST TABLE) START
                  BEGIN
                      ---FIRST PROJECTION_DETAILS_SID (PULL FROM ST TABLE) START
                      IF @PROJ_FREQUENCY = 'MONTHLY'-----it will display the data  monthly
                        BEGIN

						DECLARE @VSQL_M NVARCHAR(MAX)=''

                    SET @VSQL_M = CONCAT (@VSQL_M,'INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
                                         [PERIOD],
                                         [YEAR],
                                         EX_FACTORY_SALES_PROJECTED,
										 EX_FACTORY_SALES_ACTUALS,
                                         DEMAND_SALES_PROJECTED,
										 DEMAND_SALES_ACTUALS,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
										 INVENTORY_WITHDRAWAL_SALES_ACTUALS,
                                         EX_FACTORY_SALES_PROJECTED_PERCENT,
										 EX_FACTORY_SALES_ACTUALS_PERCENT,
                                         DEMAND_SALES_ACTUALS_PERCENT,
										 DEMAND_SALES_PROJECTED_PERCENT,
										 INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
										 CONTRACT_SALES_ACTUALS,
                                         CONTRACT_SALES_PROJECTED,
										 CONTRACT_UNITS_ACTUALS,
                                         CONTRACT_UNITS_PROJECTED,
										 TOTAL_DISCOUNT,
                                         TOTAL_DISCOUNT_PROJECTED,
										 TOTAL_DISCOUNT_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
										 PPA_DISCOUNT_ACTUALS,
                                         PPA_DISCOUNT_PROJECTED,
										 PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                         PPA_DISCOUNT_PROJECTED_PERCENTAGE,
										 TOTAL_DISCOUNT_RPU_ACTUALS,
                                         TOTAL_DISCOUNT_RPU_PROJECTED,
										 TOTAL_PPA_RPU_ACTUALS,
                                         TOTAL_PPA_RPU_PROJECTED,
										 TOTAL_RPU_ACTUAL,
                                         TOTAL_RPU_PROJECTED,
										 NET_SALES,
                                         NET_SALES_PROJECTED,
										 COGS_ACTUALS,
                                         COGS_PROJECTED,
										 NET_PROFIT_ACTUAL,
                                         NET_PROFIT_PROJECTED,
										 TOTAL_RETURNS_RPU_ACTUALS,
                                         TOTAL_RETURNS_RPU_PROJECTED,
										 RETURNS_ACTUALS_AMOUNT,
                                         RETURNS_PROJECTED_AMOUNT,
										 RETURNS_ACTUALS_PERCENTAGE,
                                         RETURNS_PROJECTED_PERCENTAGE
										 , NET_SALES_OF_EX_FACTORY_ACTUALS,
										 NET_SALES_OF_EX_FACTORY_PROJECTED,
										 DISCOUNT_OF_EX_FACTORY_ACTUALS,
										 DISCOUNT_OF_EX_FACTORY_PROJECTED)
                            SELECT @FIRST_PROJ_SID PROJECTION_ID,
                                   P.[MONTH],
                                   P.[YEAR],
								   EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
								   DEMAND_SALES_ACTUALS = Isnull(DEMAND.DEMAND_SALES_ACTUAL, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND.DEMAND_SALES_PROJECTED, 0),
								   INVENTORY_WITHDRAWAL_SALES_ACTUALS = Isnull(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0),
								   EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100,
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
								   CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                                   CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
								    CONTRACT_UNITS_ACTUALS = Isnull(CONTRACT_UNITS_ACTUALS, 0),
                                   CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                                   TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                                   TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                                   TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                                   PPA_DISCOUNT_ACTUALS = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
								   PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
								   PPA_DISCOUNT_ACTUALS_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                                   PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
								   TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
								   NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
                                   NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
								   COGS_ACTUALS = Isnull(SALES.COGS_ACTUALS, 0),
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUALS, 0) ) ),
                                   NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
								   TOTAL_RETURNS_RPU_ACTUALS= 000000.0,
                                   000000.0 as TOTAL_RETURNS_RPU_PROJECTED,
								   000000.0 as RETURNS_ACTUALS_AMOUNT,
                                   000000.0 as RETURNS_PROJECTED_AMOUNT,
								   000000.0 as RETURNS_ACTUALS_PERCENTAGE,
                                000000.0 as RETURNS_PROJECTED_PERCENTAGE,
								 NET_SALES_OF_EX_FACTORY_ACTUALS = (COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100),
                               NET_SALES_OF_EX_FACTORY_PROJECTED = (COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100)
							 ,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							  DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
								   
                             FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(ACT_AMOUNT_WITHDRAWN),
                                     ACT_UNITS_WITHDRAWN = Sum(ACT_UNITS_WITHDRAWN),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)),
                                     FOR_UNITS_WITHDRAWN = Sum(FOR_UNITS_WITHDRAWN),
                                     MONTHLY,
                                     YEARLY,
                                     PERIOD_SID
                              FROM   #INVENTORY
                              GROUP  BY MONTHLY,
                                        YEARLY,
                                        PERIOD_SID) INVENTORY
                             RIGHT JOIN (SELECT DEMAND_SALES_ACTUAL = Sum(ACT_GROSS_AMOUNT),
                                                ACT_GROSS_UNITS = Sum(ACT_GROSS_UNITS),
                                                DEMAND_SALES_PROJECTED = Sum(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)),
                                                FOR_GROSS_UNITS = Sum(FOR_GROSS_UNITS),
                                                MONTHLY,
                                                YEARLY,
                                                PERIOD_SID
                                         FROM   #DEMAND
                                         GROUP  BY MONTHLY,
                                                   YEARLY,
                                                   PERIOD_SID) DEMAND
                                     ON INVENTORY.PERIOD_SID = DEMAND.PERIOD_SID
                             RIGHT JOIN (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                GTS_SALES_PROJECTED = Sum(COALESCE(GTS_SALES_PROJECTED, GTS_SALES_ACTUALS)),
                                                UNITS = Sum(UNITS),
                                                MONTHLY PERIOD,
                                                YEARLY,
                                                PERIOD_SID
                                         FROM   #TEMP_GTS_DETAILS G
                                                INNER JOIN PERIOD P
                                                        ON P.[MONTH] = G.MONTHLY
                                                           AND P.[YEAR] = G.YEARLY
                                         GROUP  BY MONTHLY,
                                                   YEARLY,
                                                   PERIOD_SID) GTS
                                     ON GTS.PERIOD_SID = DEMAND.PERIOD_SID
                             LEFT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                               PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID),
                                               CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                               CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                               COGS_ACTUALS = Sum(COGS_ACTUAL),
                                               COGS_PROJECTED = Sum(COGS_PROJECTED)
                                        --, ITEM_MASTER_SID = COALESCE(ACT.ITEM_MASTER_SID, PROJ.ITEM_MASTER_SID)
                                        FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                FROM   '+@SALES_ACTUAL_TABLE+' NAS
                                                       INNER JOIN PROJECTION_DETAILS PD
                                                               ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                          FROM   '+@SALES_PROJECTION_TABLE+' NPS
                                                                 INNER JOIN PROJECTION_DETAILS PD
                                                                         ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                                                            AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SALES
                                    ON SALES.PERIOD_SID = GTS.PERIOD_SID
                             INNER JOIN PERIOD P
                                     ON P.PERIOD_SID = COALESCE(SALES.PERIOD_SID, GTS.PERIOD_SID, DEMAND.PERIOD_SID, INVENTORY.PERIOD_SID)
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                               PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_MODEL_SID
                                                FROM   '+@DISC_ACTUAL_TABLE+' NAD
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                       AND EXISTS (SELECT 1
                                                                   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                   WHERE  A.TOKEN = NAD.RS_MODEL_SID)
                                               --AND NAD.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_MODEL_SID
                                                          FROM   '
														  +@DISC_PROJECTION_TABLE+' NDP
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_MODEL_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) DISC
                                    ON DISC.PERIOD_SID = P.PERIOD_SID
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_MODEL_SID,
                                                       ACTUAL_SALES = NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS = NS.ACTUAL_UNITS
                                                FROM   '+@PPA_ACTUAL_TABLE+' NAP
                                                       INNER JOIN '+@SALES_ACTUAL_TABLE+' NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                               --AND NAP.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_MODEL_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   '+@PPA_PROJECTION_TABLE+' NPP
                                                                 INNER JOIN '+@SALES_PROJECTION_TABLE+' NS
                                                                         ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID =@FIRST_PROJ_SID)
                                                                 AND NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) PPA
                                    ON PPA.PERIOD_SID = SALES.PERIOD_SID
                      ORDER  BY SALES.PERIOD_SID')


			EXEC Sp_executesql
			  @VSQL_M,
			  N'@FIRST_PROJ_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID VARCHAR(8000),@START_PERIOD_SID INT',
			  @FIRST_PROJ_SID = @FIRST_PROJ_SID,
			  @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
			  @END_PERIOD_SID = @END_PERIOD_SID,
			  @START_PERIOD_SID = @START_PERIOD_SID,
			  @DISCOUNT_ID = @DISCOUNT_ID 



                        END
                      ELSE IF @PROJ_FREQUENCY = 'QUARTERLY'-------------it will display the data  Quarterly
                        BEGIN
				

							DECLARE @VSQL_Q NVARCHAR(MAX)

                    SET @VSQL_Q =     CONCAT (@VSQL_M,'INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
										PERIOD,
                                         [YEAR],
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
										 INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
										 CONTRACT_SALES_ACTUALS,
                                         CONTRACT_SALES_PROJECTED,
										 CONTRACT_UNITS_ACTUALS,
                                         CONTRACT_UNITS_PROJECTED,
										 TOTAL_DISCOUNT,
                                         TOTAL_DISCOUNT_PROJECTED,
										 TOTAL_DISCOUNT_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
										 PPA_DISCOUNT_ACTUALS,
                                         PPA_DISCOUNT_PROJECTED,
										 PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                         PPA_DISCOUNT_PROJECTED_PERCENTAGE,
										 TOTAL_DISCOUNT_RPU_ACTUALS,
                                         TOTAL_DISCOUNT_RPU_PROJECTED,
										 TOTAL_PPA_RPU_ACTUALS,
                                         TOTAL_PPA_RPU_PROJECTED,
										 TOTAL_RPU_ACTUAL,
                                         TOTAL_RPU_PROJECTED,
										 NET_SALES,
                                         NET_SALES_PROJECTED,
										 COGS_ACTUALS,
                                         COGS_PROJECTED,
										 NET_PROFIT_ACTUAL,
                                         NET_PROFIT_PROJECTED,
										 TOTAL_RETURNS_RPU_ACTUALS,
                                         TOTAL_RETURNS_RPU_PROJECTED,
										 RETURNS_ACTUALS_AMOUNT,
                                         RETURNS_PROJECTED_AMOUNT,
										 RETURNS_ACTUALS_PERCENTAGE,
                                         RETURNS_PROJECTED_PERCENTAGE,
										 NET_SALES_OF_EX_FACTORY_ACTUALS,
										 NET_SALES_OF_EX_FACTORY_PROJECTED,
										 DISCOUNT_OF_EX_FACTORY_ACTUALS,
										 DISCOUNT_OF_EX_FACTORY_PROJECTED)
                        SELECT @FIRST_PROJ_SID PROJECTION_ID,
                                   COALESCE(GTS.QUARTERLY, SALES.[QUARTER]),
                                   COALESCE(GTS.YEARLY, SALES.[YEAR]),
                                  EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
								   DEMAND_SALES_ACTUALS = Isnull(DEMAND.DEMAND_SALES_ACTUAL, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND.DEMAND_SALES_PROJECTED, 0),
								   INVENTORY_WITHDRAWAL_SALES_ACTUALS = Isnull(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0),
								   EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100,
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
								   CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                                   CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
								    CONTRACT_UNITS_ACTUALS = Isnull(CONTRACT_UNITS_ACTUALS, 0),
                                   CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                                   TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                                   TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                                   TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                                   PPA_DISCOUNT_ACTUALS = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
								   PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
								   PPA_DISCOUNT_ACTUALS_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                                   PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
								   TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
								   NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
                                   NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
								   COGS_ACTUALS = Isnull(SALES.COGS_ACTUAL, 0),
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUAL, 0) ) ),
                                   NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
								   TOTAL_RETURNS_RPU_ACTUALS= 000000.0,
                                   000000.0 as TOTAL_RETURNS_RPU_PROJECTED,
								   000000.0 as RETURNS_ACTUALS_AMOUNT,
                                   000000.0 as RETURNS_PROJECTED_AMOUNT,
								   000000.0 as RETURNS_ACTUALS_PERCENTAGE,
                                000000.0 as RETURNS_PROJECTED_PERCENTAGE,
							 NET_SALES_OF_EX_FACTORY_ACTUALS = (COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100),
                             NET_SALES_OF_EX_FACTORY_PROJECTED = (COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100)
							,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							 DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
								   
                             FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(ACT_AMOUNT_WITHDRAWN),
                                     ACT_UNITS_WITHDRAWN = Sum(ACT_UNITS_WITHDRAWN),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)),
                                     FOR_UNITS_WITHDRAWN = Sum(FOR_UNITS_WITHDRAWN),
                                     QUARTERLY,
                                     YEARLY
                              FROM   #INVENTORY
                              GROUP  BY QUARTERLY,
                                        YEARLY) INVENTORY
                             RIGHT JOIN (SELECT DEMAND_SALES_ACTUAL = Sum(ACT_GROSS_AMOUNT),
                                                ACT_GROSS_UNITS = Sum(ACT_GROSS_UNITS),
                                                DEMAND_SALES_PROJECTED = Sum(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)),
                                                FOR_GROSS_UNITS = Sum(FOR_GROSS_UNITS),
                                                QUARTERLY,
                                                YEARLY
                                         FROM   #DEMAND
                                         GROUP  BY QUARTERLY,
                                                   YEARLY) DEMAND
                                     ON INVENTORY.QUARTERLY = DEMAND.QUARTERLY
                                        AND INVENTORY.YEARLY = DEMAND.YEARLY
                             RIGHT JOIN (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
                                                UNITS = Sum(UNITS),
                                                QUARTERLY,
                                                YEARLY
                                         FROM   #TEMP_GTS_DETAILS G
                                         GROUP  BY QUARTERLY,
                                                   YEARLY) GTS
                                     ON GTS.QUARTERLY = DEMAND.QUARTERLY
                                        AND GTS.YEARLY = DEMAND.YEARLY
                             LEFT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                               [QUARTER],
                                               [YEAR],
                                               CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                               CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                               COGS_ACTUAL = Sum(COGS_ACTUAL),
                                               COGS_PROJECTED = Sum(COGS_PROJECTED)
                                        FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                FROM   ',@SALES_ACTUAL_TABLE,' NAS
                                                       INNER JOIN PROJECTION_DETAILS PD
                                                               ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                          FROM   ',@SALES_PROJECTION_TABLE,' NPS
                                                                 INNER JOIN PROJECTION_DETAILS PD
                                                                         ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                                                            AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) SALES
                                    ON SALES.QUARTER = GTS.QUARTERLY
                                       AND SALES.YEAR = GTS.YEARLY
                         
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = ( ACTUAL_SALES ),
                                               CONTRACT_DISCOUNT_PROJECTED = ( PROJECTION_SALES ),
                                               COALESCE(ACT.[QUARTER], PROJ.QUARTER) QUARTER,
                                               COALESCE(ACT.[YEAR], PROJ.YEAR)       YEAR
                                        FROM   (SELECT P.QUARTER,
                                               P.YEAR,
                                               Sum(ACTUAL_SALES) ACTUAL_SALES
                                                FROM   ',@DISC_ACTUAL_TABLE,' NAD
                                                       INNER JOIN PERIOD P
                                                               ON P.PERIOD_SID = NAD.PERIOD_SID
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                       AND EXISTS (SELECT 1
                                                                   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                   WHERE  A.TOKEN = NAD.RS_MODEL_SID)
                                                GROUP  BY P.QUARTER,
                                                          P.YEAR) ACT
                                               FULL JOIN (SELECT P.QUARTER,
                                                         P.YEAR,
                                                         Sum(PROJECTION_SALES) PROJECTION_SALES
                                                          FROM   ',@DISC_PROJECTION_TABLE,' NDP
                                                                 INNER JOIN PERIOD P
                                                                         ON P.PERIOD_SID = NDP.PERIOD_SID
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_MODEL_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID
                                                          GROUP  BY P.QUARTER,
                                                                    P.YEAR) PROJ
                                                      ON 
                                               ACT.YEAR = PROJ.YEAR
                                               AND ACT.QUARTER = PROJ.QUARTER
                                       ) DISC
                                    ON DISC.[QUARTER] = SALES.[QUARTER]
                                       AND DISC.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               [QUARTER],
                                               [YEAR]
                                        FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_MODEL_SID,
                                                       ACTUAL_SALES =NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS =NS.ACTUAL_UNITS
                                                FROM   ',@PPA_ACTUAL_TABLE,' NAP
                                                       INNER JOIN ',@SALES_ACTUAL_TABLE,' NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                               ) ACT
                                               FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_MODEL_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   ',@PPA_PROJECTION_TABLE,' NPP
                                                                 INNER JOIN ',@SALES_PROJECTION_TABLE,' NS
                                                                         ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) PPA
                                    ON PPA.[QUARTER] = SALES.[QUARTER]
                                       AND PPA.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY),
                                COALESCE(SALES.[QUARTER], GTS.QUARTERLY)')


											EXEC Sp_executesql
			  @VSQL_q,
			  N'@FIRST_PROJ_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID VARCHAR(8000),@START_PERIOD_SID INT',
			  @FIRST_PROJ_SID = @FIRST_PROJ_SID,
			  @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
			  @END_PERIOD_SID = @END_PERIOD_SID,
			  @START_PERIOD_SID = @START_PERIOD_SID,
			  @DISCOUNT_ID = @DISCOUNT_ID 





                        END
                      ELSE IF @PROJ_FREQUENCY = 'SEMI-ANNUAL'----------it will display the semi-annual wise data  -------------------------
                        BEGIN

						DECLARE @VSQL_S NVARCHAR(MAX)

                        SET @VSQL_S =     CONCAT (@VSQL_M,'INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
										period,
                                         [YEAR],
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
										 INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
										 CONTRACT_SALES_ACTUALS,
                                         CONTRACT_SALES_PROJECTED,
										 CONTRACT_UNITS_ACTUALS,
                                         CONTRACT_UNITS_PROJECTED,
										 TOTAL_DISCOUNT,
                                         TOTAL_DISCOUNT_PROJECTED,
										 TOTAL_DISCOUNT_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
										 PPA_DISCOUNT_ACTUALS,
                                         PPA_DISCOUNT_PROJECTED,
										 PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                         PPA_DISCOUNT_PROJECTED_PERCENTAGE,
										 TOTAL_DISCOUNT_RPU_ACTUALS,
                                         TOTAL_DISCOUNT_RPU_PROJECTED,
										 TOTAL_PPA_RPU_ACTUALS,
                                         TOTAL_PPA_RPU_PROJECTED,
										 TOTAL_RPU_ACTUAL,
                                         TOTAL_RPU_PROJECTED,
										 NET_SALES,
                                         NET_SALES_PROJECTED,
										 COGS_ACTUALS,
                                         COGS_PROJECTED,
										 NET_PROFIT_ACTUAL,
                                         NET_PROFIT_PROJECTED,
										 TOTAL_RETURNS_RPU_ACTUALS,
                                         TOTAL_RETURNS_RPU_PROJECTED,
										 RETURNS_ACTUALS_AMOUNT,
                                         RETURNS_PROJECTED_AMOUNT,
										 RETURNS_ACTUALS_PERCENTAGE,
                                         RETURNS_PROJECTED_PERCENTAGE,
										 NET_SALES_OF_EX_FACTORY_ACTUALS,
										 NET_SALES_OF_EX_FACTORY_PROJECTED,
										 DISCOUNT_OF_EX_FACTORY_ACTUALS,
										 DISCOUNT_OF_EX_FACTORY_PROJECTED)
                                SELECT @FIRST_PROJ_SID PROJECTION_ID,
                                   COALESCE(GTS.SEMI_ANNUAL, SALES.SEMI_ANNUAL),
                                   COALESCE(GTS.YEARLY, SALES.[YEAR]), 
								   EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
								   DEMAND_SALES_ACTUALS = Isnull(DEMAND.DEMAND_SALES_ACTUAL, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND.DEMAND_SALES_PROJECTED, 0),
								   INVENTORY_WITHDRAWAL_SALES_ACTUALS = Isnull(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0),
								   EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100,
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
								   CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                                   CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
								    CONTRACT_UNITS_ACTUALS = Isnull(CONTRACT_UNITS_ACTUALS, 0),
                                   CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                                   TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                                   TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                                   TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                                   PPA_DISCOUNT_ACTUALS = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
								   PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
								   PPA_DISCOUNT_ACTUALS_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                                   PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
								   TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
								   NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
                                   NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
								   COGS_ACTUALS = Isnull(SALES.COGS_ACTUAL, 0),
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUAL, 0) ) ),
                                   NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
								   TOTAL_RETURNS_RPU_ACTUALS= 000000.0,
                                   000000.0 as TOTAL_RETURNS_RPU_PROJECTED,
								   000000.0 as RETURNS_ACTUALS_AMOUNT,
                                   000000.0 as RETURNS_PROJECTED_AMOUNT,
								   000000.0 as RETURNS_ACTUALS_PERCENTAGE,
                                000000.0 as RETURNS_PROJECTED_PERCENTAGE,
							 NET_SALES_OF_EX_FACTORY_ACTUALS = (COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100),
                             NET_SALES_OF_EX_FACTORY_PROJECTED = (COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100)
							,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							 DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
								   
                            FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(ACT_AMOUNT_WITHDRAWN),
                                           ACT_UNITS_WITHDRAWN = Sum(ACT_UNITS_WITHDRAWN),
                                           FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)),
                                           FOR_UNITS_WITHDRAWN = Sum(FOR_UNITS_WITHDRAWN),
                                           CASE
                                             WHEN QUARTERLY <= 2 THEN 1
                                             ELSE 2
                                           END SEMI_ANNUAL,
                                           YEARLY
                                    FROM   #INVENTORY
                                    GROUP  BY CASE
                                                WHEN QUARTERLY <= 2 THEN 1
                                                ELSE 2
                                              END,
                                              YEARLY) INVENTORY
                                   RIGHT JOIN (SELECT DEMAND_SALES_ACTUAL = Sum(ACT_GROSS_AMOUNT),
                                                      ACT_GROSS_UNITS = Sum(ACT_GROSS_UNITS),
                                                      DEMAND_SALES_PROJECTED = Sum(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)),
                                                      FOR_GROSS_UNITS = Sum(FOR_GROSS_UNITS),
                                                      CASE
                                                        WHEN QUARTERLY <= 2 THEN 1
                                                        ELSE 2
                                                      END SEMI_ANNUAL,
                                                      YEARLY
                                               FROM   #DEMAND D
                                                      INNER JOIN PERIOD P
                                                              ON P.PERIOD_SID = D.PERIOD_SID
                                               GROUP  BY CASE
                                                           WHEN QUARTERLY <= 2 THEN 1
                                                           ELSE 2
                                                         END,
                                                         YEARLY) DEMAND
                                           ON INVENTORY.SEMI_ANNUAL = DEMAND.SEMI_ANNUAL
                                              AND INVENTORY.YEARLY = DEMAND.YEARLY
                                   RIGHT JOIN (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                      GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
                                                      Sum(GTS.UNITS) UNITS,
                                                      CASE
                                                        WHEN GTS.QUARTERLY <= 2 THEN 1
                                                        ELSE 2
                                                      END            SEMI_ANNUAL,
                                                      GTS.YEARLY
                                               FROM   #TEMP_GTS_DETAILS GTS
                                               GROUP  BY CASE
                                                           WHEN GTS.QUARTERLY <= 2 THEN 1
                                                           ELSE 2
                                                         END,
                                                         GTS.YEARLY) GTS
                                           ON GTS.SEMI_ANNUAL = DEMAND.SEMI_ANNUAL
                                              AND GTS.YEARLY = DEMAND.YEARLY
                                        RIGHT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                                CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                                SEMI_ANNUAL,
                                                [YEAR],
                                                CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                                CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                                COGS_ACTUAL = Sum(COGS_ACTUAL),
                                                COGS_PROJECTED = Sum(COGS_PROJECTED)
                                         FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                        NAS.PERIOD_SID,
                                                        ACTUAL_SALES,
                                                        ACTUAL_UNITS,
                                                        CCP.ITEM_MASTER_SID,
                                                        COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                 FROM   ',@SALES_ACTUAL_TABLE,' NAS
                                                        INNER JOIN PROJECTION_DETAILS PD
                                                                ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                        INNER JOIN CCP_DETAILS CCP
                                                                ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                        INNER JOIN #ITEM_PRICING U
                                                                ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                   AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                   AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                                --AND NAS.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                                ) ACT
                                                FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                  NPS.PERIOD_SID,
                                                                  PROJECTION_SALES,
                                                                  PROJECTION_UNITS,
                                                                  CCP.ITEM_MASTER_SID,
                                                                  COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                           FROM   ',@SALES_PROJECTION_TABLE,' NPS
                                                                  INNER JOIN PROJECTION_DETAILS PD
                                                                          ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                  INNER JOIN CCP_DETAILS CCP
                                                                          ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                  INNER JOIN #ITEM_PRICING U
                                                                          ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                             AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                             AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                                                             AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                       ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                          AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                INNER JOIN PERIOD P
                                                        ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                         GROUP  BY SEMI_ANNUAL,
                                                   [YEAR]) SALES
                                     ON SALES.SEMI_ANNUAL = GTS.SEMI_ANNUAL
                                        AND SALES.YEAR = GTS.YEARLY
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                               SEMI_ANNUAL,
                                               [YEAR]
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_MODEL_SID
                                                FROM   ',@DISC_ACTUAL_TABLE,' NAD
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                       AND EXISTS (SELECT 1
                                                                   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                   WHERE  A.TOKEN = NAD.RS_MODEL_SID)
                                               -- AND NAD.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_MODEL_SID
                                                          FROM   ',@DISC_PROJECTION_TABLE,' NDP
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_MODEL_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY SEMI_ANNUAL,
                                                  [YEAR]) DISC
                                    ON DISC.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                       AND DISC.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               SEMI_ANNUAL,
                                               [YEAR]
                                        FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_MODEL_SID,
                                                       ACTUAL_SALES = NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS = NS.ACTUAL_UNITS
                                                FROM   ',@PPA_ACTUAL_TABLE,' NAP
                                                       INNER JOIN ',@SALES_ACTUAL_TABLE,' NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                               -- AND NAP.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_MODEL_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   ',@PPA_PROJECTION_TABLE,' NPP
                                                                 INNER JOIN ',@SALES_PROJECTION_TABLE,' NS
                                                                         ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY SEMI_ANNUAL,
                                                  [YEAR]) PPA
                                    ON PPA.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                       AND PPA.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY),
                                COALESCE(SALES.SEMI_ANNUAL, GTS.SEMI_ANNUAL)')

				EXEC Sp_executesql
			  @VSQL_S,
			  N'@FIRST_PROJ_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID VARCHAR(8000),@START_PERIOD_SID INT',
			  @FIRST_PROJ_SID = @FIRST_PROJ_SID,
			  @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
			  @END_PERIOD_SID = @END_PERIOD_SID,
			  @START_PERIOD_SID = @START_PERIOD_SID,
			  @DISCOUNT_ID = @DISCOUNT_ID 
                        END
                      ELSE
                        BEGIN
--------------------------------	it will display the yearly wise data  ----------------------------------

                       DECLARE @VSQL_A NVARCHAR(MAX)= ''
					   SET  @VSQL_A =  CONCAT (@VSQL_M,'INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
                                         [YEAR],
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
										 INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
										 CONTRACT_SALES_ACTUALS,
                                         CONTRACT_SALES_PROJECTED,
										 CONTRACT_UNITS_ACTUALS,
                                         CONTRACT_UNITS_PROJECTED,
										 TOTAL_DISCOUNT,
                                         TOTAL_DISCOUNT_PROJECTED,
										 TOTAL_DISCOUNT_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
										 PPA_DISCOUNT_ACTUALS,
                                         PPA_DISCOUNT_PROJECTED,
										 PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                         PPA_DISCOUNT_PROJECTED_PERCENTAGE,
										 TOTAL_DISCOUNT_RPU_ACTUALS,
                                         TOTAL_DISCOUNT_RPU_PROJECTED,
										 TOTAL_PPA_RPU_ACTUALS,
                                         TOTAL_PPA_RPU_PROJECTED,
										 TOTAL_RPU_ACTUAL,
                                         TOTAL_RPU_PROJECTED,
										 NET_SALES,
                                         NET_SALES_PROJECTED,
										 COGS_ACTUALS,
                                         COGS_PROJECTED,
										 NET_PROFIT_ACTUAL,
                                         NET_PROFIT_PROJECTED,
										 TOTAL_RETURNS_RPU_ACTUALS,
                                         TOTAL_RETURNS_RPU_PROJECTED,
										 RETURNS_ACTUALS_AMOUNT,
                                         RETURNS_PROJECTED_AMOUNT,
										 RETURNS_ACTUALS_PERCENTAGE,
                                         RETURNS_PROJECTED_PERCENTAGE
										 ,NET_SALES_OF_EX_FACTORY_ACTUALS,
										 NET_SALES_OF_EX_FACTORY_PROJECTED
										 ,DISCOUNT_OF_EX_FACTORY_ACTUALS,
										 DISCOUNT_OF_EX_FACTORY_PROJECTED)
                          SELECT @FIRST_PROJ_SID PROJECTION_ID,
                                   COALESCE(GTS.YEARLY, SALES.[YEAR]) AS YEARS, 
								   EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
								   DEMAND_SALES_ACTUALS = Isnull(DEMAND.DEMAND_SALES_ACTUAL, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND.DEMAND_SALES_PROJECTED, 0),
								   INVENTORY_WITHDRAWAL_SALES_ACTUALS = Isnull(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0),
								   EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100,
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
								   CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                                   CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
								    CONTRACT_UNITS_ACTUALS = Isnull(CONTRACT_UNITS_ACTUALS, 0),
                                   CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                                   TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
                                   TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
                                   TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                                   PPA_DISCOUNT_ACTUALS = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
								   PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
								   PPA_DISCOUNT_ACTUALS_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
                                   PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
								   TOTAL_DISCOUNT_RPU_ACTUALS = ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_DISCOUNT_RPU_PROJECTED = ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_PPA_RPU_ACTUALS = ( Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_PPA_RPU_PROJECTED = ( Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_ACTUAL = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
								   NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
                                   NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ),
								   COGS_ACTUALS = Isnull(SALES.COGS_ACTUAL, 0),
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_ACTUAL = ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUAL, 0) ) ),
                                   NET_PROFIT_PROJECTED = ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ),
								   TOTAL_RETURNS_RPU_ACTUALS= 000000.0,
                                   000000.0 as TOTAL_RETURNS_RPU_PROJECTED,
								   000000.0 as RETURNS_ACTUALS_AMOUNT,
                                   000000.0 as RETURNS_PROJECTED_AMOUNT,
								   000000.0 as RETURNS_ACTUALS_PERCENTAGE,
                                000000.0 as RETURNS_PROJECTED_PERCENTAGE,
				 NET_SALES_OF_EX_FACTORY_ACTUALS = (COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100),
                             NET_SALES_OF_EX_FACTORY_PROJECTED = (COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100)
							,DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100,
							 DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100
								   
                             FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(ACT_AMOUNT_WITHDRAWN),
                                     ACT_UNITS_WITHDRAWN = Sum(ACT_UNITS_WITHDRAWN),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)),
                                     FOR_UNITS_WITHDRAWN = Sum(FOR_UNITS_WITHDRAWN),
                                     YEARLY
                              FROM   #INVENTORY
                              GROUP  BY YEARLY) INVENTORY
                             RIGHT JOIN (SELECT DEMAND_SALES_ACTUAL = Sum(ACT_GROSS_AMOUNT),
                                                ACT_GROSS_UNITS = Sum(ACT_GROSS_UNITS),
                                                DEMAND_SALES_PROJECTED = Sum(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)),
                                                FOR_GROSS_UNITS = Sum(FOR_GROSS_UNITS),
                                                YEARLY
                                         FROM   #DEMAND
                                         GROUP  BY YEARLY) DEMAND
                                     ON INVENTORY.YEARLY = DEMAND.YEARLY
                             RIGHT JOIN (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
                                                GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
                                                Sum(GTS.UNITS) UNITS,
                                                GTS.YEARLY
                                         FROM   #TEMP_GTS_DETAILS GTS
                                         --JOIN @ITEM_INFO ACT
                                         --  ON GTS.ITEMID = ACT.ITEM_ID
                                         GROUP  BY GTS.YEARLY) GTS
                                     ON DEMAND.YEARLY = GTS.YEARLY
                             RIGHT JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
                                                CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
                                                [YEAR],
                                                CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
                                                CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS),
                                                COGS_ACTUAL = Sum(COGS_ACTUAL),
                                                COGS_PROJECTED = Sum(COGS_PROJECTED)
                                         FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                        NAS.PERIOD_SID,
                                                        ACTUAL_SALES,
                                                        ACTUAL_UNITS,
                                                        CCP.ITEM_MASTER_SID,
                                                        COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                 FROM   ',@SALES_ACTUAL_TABLE,' NAS
                                                        INNER JOIN PROJECTION_DETAILS PD
                                                                ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                        INNER JOIN CCP_DETAILS CCP
                                                                ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                        INNER JOIN #ITEM_PRICING U
                                                                ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                   AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                   AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                                --AND NAS.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                                ) ACT
                                                FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                  NPS.PERIOD_SID,
                                                                  PROJECTION_SALES,
                                                                  PROJECTION_UNITS,
                                                                  CCP.ITEM_MASTER_SID,
                                                                  COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                           FROM   ',@SALES_PROJECTION_TABLE,' NPS
                                                                  INNER JOIN PROJECTION_DETAILS PD
                                                                          ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                  INNER JOIN CCP_DETAILS CCP
                                                                          ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                  INNER JOIN #ITEM_PRICING U
                                                                          ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                             AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                             AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                                                             AND NPS.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                       ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                          AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                INNER JOIN PERIOD P
                                                        ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                         GROUP  BY [YEAR]) SALES
                                     ON SALES.YEAR = GTS.YEARLY
                             LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
                                               CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
                                               [YEAR]
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_MODEL_SID
                                                FROM   ',@DISC_ACTUAL_TABLE,' NAD
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                       AND EXISTS (SELECT 1
                                                                   FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                   WHERE  A.TOKEN = NAD.RS_MODEL_SID)
                                               --  AND NAD.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_MODEL_SID
                                                          FROM   ',@DISC_PROJECTION_TABLE,' NDP
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND EXISTS (SELECT 1
                                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, '','') A
                                                                             WHERE  A.TOKEN = NDP.RS_MODEL_SID)
                                                                 AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [YEAR]) DISC
                                    ON DISC.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_PPA_SALES),
                                               PPA_RPU_ACTUALS = Sum(ACTUAL_PPA_RPU),
                                               PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_PPA_SALES),
                                               PPA_RPU_PROJECTED = Sum(PPA_RPU),
                                               PPA_ACTUAL_SALES = Sum(ACTUAL_SALES),
                                               PPA_ACTUAL_UNITS = Sum(ACTUAL_UNITS),
                                               PPA_PROJECTION_SALES = Sum(PROJECTION_SALES),
                                               PPA_PROJECTION_UNITS = Sum(PROJECTION_UNITS),
                                               [YEAR]
                                        FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_PPA_SALES = ACTUAL_DISCOUNT_DOLLAR,
                                                       ACTUAL_PPA_RPU = ACTUAL_DISCOUNT_DOLLAR,
                                                       RS_MODEL_SID,
                                                       ACTUAL_SALES = NS.ACTUAL_SALES,
                                                       ACTUAL_UNITS = NS.ACTUAL_UNITS
                                                FROM   ',@PPA_ACTUAL_TABLE,' NAP
                                                       INNER JOIN ',@SALES_ACTUAL_TABLE,' NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                               -- AND NAP.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PPA_RPU = PROJECTION_DISCOUNT_DOLLAR,
                                                                 PROJECTION_PPA_SALES = PROJECTION_DISCOUNT_DOLLAR,
                                                                 RS_MODEL_SID,
                                                                 PROJECTION_SALES = NS.PROJECTION_SALES,
                                                                 PROJECTION_UNITS = NS.PROJECTION_UNITS
                                                          FROM   ',@PPA_PROJECTION_TABLE,' NPP
                                                                 INNER JOIN ',@SALES_PROJECTION_TABLE,' NS
                                                                         ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                            AND NS.PERIOD_SID = NPP.PERIOD_SID
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY YEAR) PPA
                                    ON PPA.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], gts.yearly)')
					  EXEC Sp_executesql
			  @VSQL_A,
			  N'@FIRST_PROJ_SID INT,@PROJ_START_PERIOD_SID INT,@END_PERIOD_SID INT, @DISCOUNT_ID VARCHAR(8000),@START_PERIOD_SID INT',
			  @FIRST_PROJ_SID = @FIRST_PROJ_SID,
			  @PROJ_START_PERIOD_SID = @PROJ_START_PERIOD_SID,
			  @END_PERIOD_SID = @END_PERIOD_SID,
			  @START_PERIOD_SID = @START_PERIOD_SID,
			  @DISCOUNT_ID = @DISCOUNT_ID 
                        END

                        END

				if object_id('tempdb..#CURRENT_CPP_COMP_PRIOR_CPP') is not null
				drop table #CURRENT_CPP_COMP_PRIOR_CPP

		  SELECT  PM.PROJECTION_MASTER_SID,
		  CC.CCP_DETAILS_SID
         INTO #CURRENT_CPP_COMP_PRIOR_CPP FROM   #PROJECTION_MASTER PM
		 INNER JOIN PROJECTION_DETAILS PD
		 ON PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID
		 INNER JOIN CCP_DETAILS CC
		 ON PD.CCP_DETAILS_SID=CC.CCP_DETAILS_SID
          WHERE  ID = 1

		     IF Object_id('tempdb..#PRODUCT_FILE_TEMP') IS NOT NULL
                        DROP TABLE #PRODUCT_FILE_TEMP ------------------------------STORING THNE FILE TYPE VALUE IN THIS TABLE-----------------------------------
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

                      --  -------------------------------------------------------
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

                      ---FIRST PROJECTION_DETAILS_SID (PULL FROM ST TABLE) END
                      --SECOND TO LAST PROJECTION_DETAILS_SID(PULL FROM MAIN TABLE) START
                      IF @PROJ_FREQUENCY = 'MONTHLY'----------------------------it will display the monthly wise data  -------------------------
                        BEGIN
                           INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
										PERIOD,
                                         [YEAR],
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
										 INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
										 CONTRACT_SALES_ACTUALS,
                                         CONTRACT_SALES_PROJECTED,
										 CONTRACT_UNITS_ACTUALS,
                                         CONTRACT_UNITS_PROJECTED,
										 TOTAL_DISCOUNT,
                                         TOTAL_DISCOUNT_PROJECTED,
										 TOTAL_DISCOUNT_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
										 PPA_DISCOUNT_ACTUALS,
                                         PPA_DISCOUNT_PROJECTED,
										 PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                         PPA_DISCOUNT_PROJECTED_PERCENTAGE,
										 TOTAL_DISCOUNT_RPU_ACTUALS,
                                         TOTAL_DISCOUNT_RPU_PROJECTED,
										 TOTAL_PPA_RPU_ACTUALS,
                                         TOTAL_PPA_RPU_PROJECTED,
										 TOTAL_RPU_ACTUAL,
                                         TOTAL_RPU_PROJECTED,
										 NET_SALES,
                                         NET_SALES_PROJECTED,
										 COGS_ACTUALS,
                                         COGS_PROJECTED,
										 NET_PROFIT_ACTUAL,
                                         NET_PROFIT_PROJECTED,
										 TOTAL_RETURNS_RPU_ACTUALS,
                                         TOTAL_RETURNS_RPU_PROJECTED,
										 RETURNS_ACTUALS_AMOUNT,
                                         RETURNS_PROJECTED_AMOUNT,
										 RETURNS_ACTUALS_PERCENTAGE,
                                         RETURNS_PROJECTED_PERCENTAGE,
										 NET_SALES_OF_EX_FACTORY_ACTUALS,
										 NET_SALES_OF_EX_FACTORY_PROJECTED
										 ,DISCOUNT_OF_EX_FACTORY_ACTUALS,
										 DISCOUNT_OF_EX_FACTORY_PROJECTED)
      SELECT COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
                                   P.[MONTH],
                                   P.[YEAR],
								   Isnull(GTS.GTS_SALES_ACTUALS, 0) AS EX_FACTORY_SALES_ACTUALS,
                                   Isnull(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
								   Isnull(GTS.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS,
                                   Isnull(GTS.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
								   Isnull(GTS.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_ACTUALS,
                                   Isnull(GTS.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
								   (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUAL_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                   ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
								   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0)AS CONTRACT_SALES_ACTUALS,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
								   Isnull(CONTRACT_UNITS_ACTUALS, 0)AS CONTRACT_UNITS_ACTUALS,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS TOTAL_DISCOUNT,
                                   Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
                                   ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS PPA_DISCOUNT_ACTUALS,
								   Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS PPA_DISCOUNT_PROJECTED,
								   Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100 AS PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                   Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100 AS PPA_DISCOUNT_PROJECTED_PERCENTAGE,
								   (Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_DISCOUNT_RPU_ACTUALS,
                                   (Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_DISCOUNT_RPU_PROJECTED,
                                   (Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_PPA_RPU_ACTUALS,
                                   (Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_PPA_RPU_PROJECTED,
                                   (Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUAL,
                                   (Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
								   ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) AS NET_SALES,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
								   Isnull(SALES.COGS_ACTUALS, 0) AS COGS_ACTUALS,
                                   Isnull(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL,
                                   ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED,
								   000000.0 AS TOTAL_RETURNS_RPU_ACTUALS,
                                   000000.0 as TOTAL_RETURNS_RPU_PROJECTED,
								   000000.0 as RETURNS_ACTUALS_AMOUNT,
                                   000000.0 as RETURNS_PROJECTED_AMOUNT,
								   000000.0 as RETURNS_ACTUALS_PERCENTAGE,
                                   000000.0 as RETURNS_PROJECTED_PERCENTAGE,
								  (COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100) AS NET_SALES_OF_EX_FACTORY_ACTUALS,
                              (COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100) AS NET_SALES_OF_EX_FACTORY_PROJECTED
							,COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100 AS DISCOUNT_OF_EX_FACTORY_ACTUALS,
							  COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100 AS DISCOUNT_OF_EX_FACTORY_PROJECTED
								   
								   
                            FROM   (SELECT PF.PROJECTION_MASTER_SID,
                                            Sum(INVENTORY_ACTUAL_SALES) AS ACT_AMOUNT_WITHDRAWN,
                                            Sum(INVENTORY_ACTUAL_UNITS) as ACT_UNITS_WITHDRAWN,
                                            Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)) as FOR_AMOUNT_WITHDRAWN,
                                            Sum(COALESCE(INVENTORY_FORECAST_UNITS, INVENTORY_ACTUAL_UNITS)) as FOR_UNITS_WITHDRAWN,
                                            Sum(DEMAND_ACTUAL_SALES) as DEMAND_SALES_ACTUAL,
                                            Sum(DEMAND_ACTUAL_UNITS) as ACT_GROSS_UNITS,
                                            Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)) as DEMAND_SALES_PROJECTED,
                                            Sum(COALESCE(DEMAND_FORECAST_UNITS, DEMAND_ACTUAL_UNITS)) as FOR_GROSS_UNITS,
                                            Sum(EXFACTORY_ACTUAL_SALES) as GTS_SALES_ACTUALS,
                                            Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) as GTS_SALES_PROJECTED,
                                            Sum(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS)) as UNITS,
                                           P.PERIOD_SID
                                    FROM   #PRODUCT_FILE_TEMP PF
                                           JOIN PERIOD P
                                             ON P.PERIOD_SID = PF.PERIOD_SID
                                           JOIN #PROJECTION_MASTER PM
                                             ON PM.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID
                                                AND PM.ID <> 1
                                    GROUP  BY PF.PROJECTION_MASTER_SID,
                                              P.PERIOD_SID ) GTS  
                                  RIGHT JOIN (SELECT   Sum(ACTUAL_SALES) as CONTRACT_SALES_ACTUALS,
                                                Sum(PROJECTION_SALES) as CONTRACT_SALES_PROJECTED,
                                                COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) as PERIOD_SID,
                                                Sum(ACTUAL_UNITS) as CONTRACT_UNITS_ACTUALS,
                                                Sum(PROJECTION_UNITS) as CONTRACT_UNITS_PROJECTED,
                                                Sum(COGS_ACTUAL) as COGS_ACTUALS,
                                               Sum(COGS_PROJECTED) as COGS_PROJECTED
											   ,coalesce(act.PROJECTION_MASTER_SID,proj.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID
                                        --, ITEM_MASTER_SID = COALESCE(ACT.ITEM_MASTER_SID, PROJ.ITEM_MASTER_SID)
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                         ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ) as COGS_ACTUAL
														 ,NAS.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_SALES NAS
												INNER JOIN PROJECTION_DETAILS PD
												ON NAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
														INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
																   where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
								                       
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NSP.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                   ( Isnull(NSP.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ) as COGS_PROJECTED,
																   NSP.PROJECTION_DETAILS_SID
                                                          FROM   #NM_SALES_PROJECTION NSP
                                                                	INNER JOIN PROJECTION_DETAILS PD
												ON NSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
															   INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NSP.PERIOD_SID = U.PERIOD_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                                                            AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
										,COALESCE(ACT.PROJECTION_MASTER_SID,PROJ.PROJECTION_MASTER_SID)) SALES
										ON SALES.PERIOD_SID=GTS.PERIOD_SID
										   and SALES.PROJECTION_MASTER_SID = GTS.PROJECTION_MASTER_SID
										        INNER JOIN PERIOD P
                                           ON P.PERIOD_SID = COALESCE(SALES.PERIOD_SID, GTS.PERIOD_SID)
                                              AND P.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID
                             LEFT JOIN (SELECT   Sum(ACTUAL_SALES) as CONTRACT_DISCOUNT_ACTUALS,
                                                 Sum(PROJECTION_SALES) as CONTRACT_DISCOUNT_PROJECTED,
                                                COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) as PERIOD_SID,
											   coalesce(act.PROJECTION_MASTER_SID,proj.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                       NAD.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_CONTRACT_SID,
													   NAD.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_DISCOUNT NAD
                                               INNER JOIN PROJECTION_DETAILS PD
												ON NAD.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
												INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NDP.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_CONTRACT_SID,
																 NDP.PROJECTION_DETAILS_SID
                                                          FROM   NM_DISCOUNT_PROJECTION NDP
                                                                        	INNER JOIN PROJECTION_DETAILS PD
												ON NDP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
													INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                                                            AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID),
										 COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID)) DISC
                                     ON DISC.PERIOD_SID = P.PERIOD_SID
									 AND DISC.PROJECTION_MASTER_SID = COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID)
                             LEFT JOIN (SELECT  Sum(ACTUAL_PPA_SALES) as PPA_DISCOUNT_ACTUALS,
                                               Sum(ACTUAL_PPA_RPU) as PPA_RPU_ACTUALS,
                                               Sum(PROJECTION_PPA_SALES) as PPA_DISCOUNT_PROJECTED,
                                                Sum(PPA_RPU) as PPA_RPU_PROJECTED,
                                                Sum(ACTUAL_SALES) as PPA_ACTUAL_SALES,
                                                Sum(ACTUAL_UNITS) as PPA_ACTUAL_UNITS,
                                                Sum(PROJECTION_SALES) as PPA_PROJECTION_SALES,
                                                Sum(PROJECTION_UNITS) as PPA_PROJECTION_UNITS,
                                                COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) as PERIOD_SID,
											    COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                        NAP.PERIOD_SID,
                                                        ACTUAL_DISCOUNT_DOLLAR as ACTUAL_PPA_SALES,
                                                        ACTUAL_DISCOUNT_DOLLAR as ACTUAL_PPA_RPU,
                                                        RS_CONTRACT_SID,
                                                        NS.ACTUAL_SALES as ACTUAL_SALES,
                                                        NS.ACTUAL_UNITS as ACTUAL_UNITS,
														NAP.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_PPA NAP
                                                       INNER JOIN NM_ACTUAL_SALES NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
																  INNER JOIN PROJECTION_DETAILS PD
																  ON NS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
																  	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                                 --AND NAP.PERIOD_SID BETWEEN @START_PERIOD_SID AND @ACT_END_PERIOD_SID
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PROJECTION_DISCOUNT_DOLLAR as PPA_RPU,
                                                                 PROJECTION_DISCOUNT_DOLLAR as PROJECTION_PPA_SALES,
                                                                 RS_CONTRACT_SID,
                                                                 NS.PROJECTION_SALES as PROJECTION_SALES,
                                                                 NS.PROJECTION_UNITS as PROJECTION_UNITS
																 ,NPP.PROJECTION_DETAILS_SID
                                                          FROM   NM_PPA_PROJECTION NPP
                                                                 INNER JOIN #NM_SALES_PROJECTION NS
                                                                          ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NPP.PERIOD_SID
																  INNER JOIN PROJECTION_DETAILS PD
																  ON NS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID   
																  	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																  where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)

                                                          AND    NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID),
										COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID)) PPA
                                    ON PPA.PERIOD_SID = SALES.PERIOD_SID     
									   AND COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) = PPA.PROJECTION_MASTER_SID               
									  ORDER  BY SALES.PERIOD_SID
                        END
                      ELSE IF @PROJ_FREQUENCY = 'QUARTERLY'----------------------------IT WILL DISPLAY THE QUARTERLY WISE DATA  -------------------------
                        BEGIN
                                  INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
										PERIOD,
                                         [YEAR],
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
										 INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
										 CONTRACT_SALES_ACTUALS,
                                         CONTRACT_SALES_PROJECTED,
										 CONTRACT_UNITS_ACTUALS,
                                         CONTRACT_UNITS_PROJECTED,
										 TOTAL_DISCOUNT,
                                         TOTAL_DISCOUNT_PROJECTED,
										 TOTAL_DISCOUNT_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
										 PPA_DISCOUNT_ACTUALS,
                                         PPA_DISCOUNT_PROJECTED,
										 PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                         PPA_DISCOUNT_PROJECTED_PERCENTAGE,
										 TOTAL_DISCOUNT_RPU_ACTUALS,
                                         TOTAL_DISCOUNT_RPU_PROJECTED,
										 TOTAL_PPA_RPU_ACTUALS,
                                         TOTAL_PPA_RPU_PROJECTED,
										 TOTAL_RPU_ACTUAL,
                                         TOTAL_RPU_PROJECTED,
										 NET_SALES,
                                         NET_SALES_PROJECTED,
										 COGS_ACTUALS,
                                         COGS_PROJECTED,
										 NET_PROFIT_ACTUAL,
                                         NET_PROFIT_PROJECTED,
										 TOTAL_RETURNS_RPU_ACTUALS,
                                         TOTAL_RETURNS_RPU_PROJECTED,
										 RETURNS_ACTUALS_AMOUNT,
                                         RETURNS_PROJECTED_AMOUNT,
										 RETURNS_ACTUALS_PERCENTAGE,
                                         RETURNS_PROJECTED_PERCENTAGE
										 ,NET_SALES_OF_EX_FACTORY_ACTUALS,
										 NET_SALES_OF_EX_FACTORY_PROJECTED
										 ,DISCOUNT_OF_EX_FACTORY_ACTUALS,
										 DISCOUNT_OF_EX_FACTORY_PROJECTED)
                            SELECT COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
                                   COALESCE(GTS.QUARTERLY, SALES.[QUARTER]),
                                   COALESCE(GTS.YEARLY, SALES.[YEAR]),
                                  Isnull(GTS.GTS_SALES_ACTUALS, 0) AS EX_FACTORY_SALES_ACTUALS,
                                   Isnull(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
								   Isnull(GTS.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS,
                                   Isnull(GTS.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
								   Isnull(GTS.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_ACTUALS,
                                   Isnull(GTS.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
								   (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUAL_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                   ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
								   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0)AS CONTRACT_SALES_ACTUALS,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
								   Isnull(CONTRACT_UNITS_ACTUALS, 0)AS CONTRACT_UNITS_ACTUALS,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS TOTAL_DISCOUNT,
                                   Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
                                   ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS PPA_DISCOUNT_ACTUALS,
								   Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS PPA_DISCOUNT_PROJECTED,
								   Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100 AS PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                   Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100 AS PPA_DISCOUNT_PROJECTED_PERCENTAGE,
								   (Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_DISCOUNT_RPU_ACTUALS,
                                   (Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_DISCOUNT_RPU_PROJECTED,
                                   (Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_PPA_RPU_ACTUALS,
                                   (Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_PPA_RPU_PROJECTED,
                                   (Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUAL,
                                   (Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
								   ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) AS NET_SALES,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
								   Isnull(SALES.COGS_ACTUALS, 0) AS COGS_ACTUALS,
                                   Isnull(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL,
                                   ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED,
								   000000.0 AS TOTAL_RETURNS_RPU_ACTUALS,
                                   000000.0 as TOTAL_RETURNS_RPU_PROJECTED,
								   000000.0 as RETURNS_ACTUALS_AMOUNT,
                                   000000.0 as RETURNS_PROJECTED_AMOUNT,
								   000000.0 as RETURNS_ACTUALS_PERCENTAGE,
                                   000000.0 as RETURNS_PROJECTED_PERCENTAGE,
								  (COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100) AS NET_SALES_OF_EX_FACTORY_ACTUALS,
                              (COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100) AS NET_SALES_OF_EX_FACTORY_PROJECTED
							,COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100 AS DISCOUNT_OF_EX_FACTORY_ACTUALS,
							  COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100 AS DISCOUNT_OF_EX_FACTORY_PROJECTED
								   
                            FROM   (SELECT PF.PROJECTION_MASTER_SID,
                                            Sum(INVENTORY_ACTUAL_SALES) AS ACT_AMOUNT_WITHDRAWN,
                                           Sum(INVENTORY_ACTUAL_UNITS) as ACT_UNITS_WITHDRAWN,
                                           Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)) as FOR_AMOUNT_WITHDRAWN,
                                           Sum(COALESCE(INVENTORY_FORECAST_UNITS, INVENTORY_ACTUAL_UNITS)) as FOR_UNITS_WITHDRAWN,
                                           Sum(DEMAND_ACTUAL_SALES) as DEMAND_SALES_ACTUAL,
                                           Sum(DEMAND_ACTUAL_UNITS) as ACT_GROSS_UNITS,
                                           Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)) as DEMAND_SALES_PROJECTED,
                                           Sum(COALESCE(DEMAND_FORECAST_UNITS, DEMAND_ACTUAL_UNITS)) as FOR_GROSS_UNITS,
                                           Sum(EXFACTORY_ACTUAL_SALES) as GTS_SALES_ACTUALS,
                                           Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) as GTS_SALES_PROJECTED,
                                           Sum(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS)) as UNITS,
                                           P.[QUARTER] QUARTERLY,
                                           P.[YEAR]    YEARLY
                                    FROM   #PRODUCT_FILE_TEMP PF
                                           JOIN PERIOD P
                                             ON P.PERIOD_SID = PF.PERIOD_SID
                                           JOIN #PROJECTION_MASTER PM
                                             ON PM.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID
                                                AND PM.ID <> 1
                                    GROUP  BY PF.PROJECTION_MASTER_SID,
                                              P.[YEAR],
                                              P.[QUARTER]) GTS
                                    LEFT JOIN (SELECT Sum(ACTUAL_SALES) as CONTRACT_SALES_ACTUALS,
                                               Sum(PROJECTION_SALES) as CONTRACT_SALES_PROJECTED,
                                               Sum(ACTUAL_UNITS) as CONTRACT_UNITS_ACTUALS,
                                               Sum(PROJECTION_UNITS) as CONTRACT_UNITS_PROJECTED,
                                               Sum(COGS_ACTUAL)as COGS_ACTUALS,
                                               Sum(COGS_PROJECTED) as COGS_PROJECTED,
											   coalesce(act.PROJECTION_MASTER_SID,proj.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID,
											    [QUARTER],
                                                [YEAR]
                                        --, ITEM_MASTER_SID = COALESCE(ACT.ITEM_MASTER_SID, PROJ.ITEM_MASTER_SID)
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       (Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ) as COGS_ACTUAL
													   ,NAS.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_SALES NAS
												INNER JOIN PROJECTION_DETAILS PD
												ON NAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
															   	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
																   where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NSP.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                               ( Isnull(NSP.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ) as COGS_PROJECTED
															   ,NSP.PROJECTION_DETAILS_SID
                                                          FROM   #NM_SALES_PROJECTION NSP
                                                                	INNER JOIN PROJECTION_DETAILS PD
												ON NSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
															   	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NSP.PERIOD_SID = U.PERIOD_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                                                            AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
														 INNER JOIN PERIOD P ON P.PERIOD_SID=COALESCE(ACT.PERIOD_SID,PROJ.PERIOD_SID)
                                        GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID,PROJ.PROJECTION_MASTER_SID)
										,   [QUARTER],
                                        [YEAR]) sales
                                           ON SALES.[QUARTER] = GTS.QUARTERLY
                                              AND SALES.[YEAR] = GTS.YEARLY
											  and sales.PROJECTION_MASTER_SID = GTS.PROJECTION_MASTER_SID
                             LEFT JOIN (SELECT   Sum(ACTUAL_SALES) as CONTRACT_DISCOUNT_ACTUALS,
                                                Sum(PROJECTION_SALES) as CONTRACT_DISCOUNT_PROJECTED,
											   coalesce(act.PROJECTION_MASTER_SID,proj.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID,
											  [QUARTER],
                                                [YEAR]
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                       NAD.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_CONTRACT_SID,
													   NAD.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_DISCOUNT NAD
                                               INNER JOIN PROJECTION_DETAILS PD
												ON NAD.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
													INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NDP.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_CONTRACT_SID,
																 NDP.PROJECTION_DETAILS_SID
                                                          FROM   NM_DISCOUNT_PROJECTION NDP
                                                                        	INNER JOIN PROJECTION_DETAILS PD
												ON NDP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
													INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                                                            AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
														 INNER JOIN PERIOD P ON P.PERIOD_SID=COALESCE(ACT.PERIOD_SID,PROJ.PERIOD_SID)
                                        GROUP  BY 
										 COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID)
										 ,QUARTER,YEAR) DISC
                                 ON COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) = DISC.PROJECTION_MASTER_SID
                                             AND DISC.[QUARTER] = COALESCE(GTS.QUARTERLY, SALES.[QUARTER])
                                             AND DISC.[YEAR] = COALESCE(GTS.YEARLY, SALES.[YEAR])
                             LEFT JOIN (SELECT  Sum(ACTUAL_PPA_SALES) as PPA_DISCOUNT_ACTUALS,
                                                Sum(ACTUAL_PPA_RPU) as PPA_RPU_ACTUALS,
                                                Sum(PROJECTION_PPA_SALES) as PPA_DISCOUNT_PROJECTED,
                                                Sum(PPA_RPU) as PPA_RPU_PROJECTED,
                                                Sum(ACTUAL_SALES) as PPA_ACTUAL_SALES,
                                                Sum(ACTUAL_UNITS) as PPA_ACTUAL_UNITS,
                                                Sum(PROJECTION_SALES) as PPA_PROJECTION_SALES,
                                                Sum(PROJECTION_UNITS) as PPA_PROJECTION_UNITS,
											   COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID,
											    [QUARTER],
                                                [YEAR]
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_DISCOUNT_DOLLAR as ACTUAL_PPA_SALES,
                                                       ACTUAL_DISCOUNT_DOLLAR as ACTUAL_PPA_RPU,
                                                       RS_CONTRACT_SID,
                                                       NS.ACTUAL_SALES as ACTUAL_SALES,
                                                       NS.ACTUAL_UNITS as ACTUAL_UNITS,
													   NAP.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_PPA NAP
                                                       INNER JOIN NM_ACTUAL_SALES NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
																  INNER JOIN PROJECTION_DETAILS PD
																  ON NS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
																  	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PROJECTION_DISCOUNT_DOLLAR as PPA_RPU,
                                                                 PROJECTION_DISCOUNT_DOLLAR as PROJECTION_PPA_SALES,
                                                                 RS_CONTRACT_SID,
                                                                 NS.PROJECTION_SALES as PROJECTION_SALES,
                                                                 NS.PROJECTION_UNITS as PROJECTION_UNITS,
																 NPP.PROJECTION_DETAILS_SID
                                                          FROM   NM_PPA_PROJECTION NPP
                                                                 INNER JOIN #NM_SALES_PROJECTION NS
                                                                          ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NPP.PERIOD_SID
																  INNER JOIN PROJECTION_DETAILS PD
																  ON NS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID  
																  	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID 
																  where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)

                                                          AND    NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
											INNER JOIN PERIOD P ON P.PERIOD_SID=COALESCE(ACT.PERIOD_SID,PROJ.PERIOD_SID)
                                        GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID)
										 ,QUARTER,YEAR) PPA
                                    ON PPA.QUARTER = SALES.QUARTER
									AND PPA.YEAR=SALES.YEAR     
									   AND COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) = PPA.PROJECTION_MASTER_SID               
									  ORDER  BY SALES.QUARTER,sales.YEAR
                        END
                      ELSE IF @PROJ_FREQUENCY = 'SEMI-ANNUAL'-------------------------------it will display the semi_annual wise data  -------------------------
                        BEGIN
                            INSERT INTO #PIVOT_RESULT
                                                
                                        (PROJECTION_ID,
										PERIOD,
                                         [YEAR],
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
										 INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
										 CONTRACT_SALES_ACTUALS,
                                         CONTRACT_SALES_PROJECTED,
										 CONTRACT_UNITS_ACTUALS,
                                         CONTRACT_UNITS_PROJECTED,
										 TOTAL_DISCOUNT,
                                         TOTAL_DISCOUNT_PROJECTED,
										 TOTAL_DISCOUNT_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
										 PPA_DISCOUNT_ACTUALS,
                                         PPA_DISCOUNT_PROJECTED,
										 PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                         PPA_DISCOUNT_PROJECTED_PERCENTAGE,
										 TOTAL_DISCOUNT_RPU_ACTUALS,
                                         TOTAL_DISCOUNT_RPU_PROJECTED,
										 TOTAL_PPA_RPU_ACTUALS,
                                         TOTAL_PPA_RPU_PROJECTED,
										 TOTAL_RPU_ACTUAL,
                                         TOTAL_RPU_PROJECTED,
										 NET_SALES,
                                         NET_SALES_PROJECTED,
										 COGS_ACTUALS,
                                         COGS_PROJECTED,
										 NET_PROFIT_ACTUAL,
                                         NET_PROFIT_PROJECTED,
										 TOTAL_RETURNS_RPU_ACTUALS,
                                         TOTAL_RETURNS_RPU_PROJECTED,
										 RETURNS_ACTUALS_AMOUNT,
                                         RETURNS_PROJECTED_AMOUNT,
										 RETURNS_ACTUALS_PERCENTAGE,
                                         RETURNS_PROJECTED_PERCENTAGE,
										 NET_SALES_OF_EX_FACTORY_ACTUALS,
										 NET_SALES_OF_EX_FACTORY_PROJECTED
										 ,DISCOUNT_OF_EX_FACTORY_ACTUALS,
										 DISCOUNT_OF_EX_FACTORY_PROJECTED)
                            SELECT COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
                                   COALESCE(GTS.SEMI_ANNUAL, SALES.SEMI_ANNUAL),
                                   COALESCE(GTS.YEARLY, SALES.[YEAR]),
                                    Isnull(GTS.GTS_SALES_ACTUALS, 0) AS EX_FACTORY_SALES_ACTUALS,
                                   Isnull(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
								   Isnull(GTS.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS,
                                   Isnull(GTS.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
								   Isnull(GTS.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_ACTUALS,
                                   Isnull(GTS.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
								   (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUAL_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                   ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
								   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0)AS CONTRACT_SALES_ACTUALS,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
								   Isnull(CONTRACT_UNITS_ACTUALS, 0)AS CONTRACT_UNITS_ACTUALS,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS TOTAL_DISCOUNT,
                                   Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
                                   ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS PPA_DISCOUNT_ACTUALS,
								   Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS PPA_DISCOUNT_PROJECTED,
								   Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100 AS PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                   Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100 AS PPA_DISCOUNT_PROJECTED_PERCENTAGE,
								   (Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_DISCOUNT_RPU_ACTUALS,
                                   (Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_DISCOUNT_RPU_PROJECTED,
                                   (Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_PPA_RPU_ACTUALS,
                                   (Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_PPA_RPU_PROJECTED,
                                   (Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUAL,
                                   (Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
								   ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) AS NET_SALES,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
								   Isnull(SALES.COGS_ACTUALS, 0) AS COGS_ACTUALS,
                                   Isnull(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL,
                                   ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED,
								   000000.0 AS TOTAL_RETURNS_RPU_ACTUALS,
                                   000000.0 as TOTAL_RETURNS_RPU_PROJECTED,
								   000000.0 as RETURNS_ACTUALS_AMOUNT,
                                   000000.0 as RETURNS_PROJECTED_AMOUNT,
								   000000.0 as RETURNS_ACTUALS_PERCENTAGE,
                                   000000.0 as RETURNS_PROJECTED_PERCENTAGE,
								  (COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100) AS NET_SALES_OF_EX_FACTORY_ACTUALS,
                              (COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100) AS NET_SALES_OF_EX_FACTORY_PROJECTED
							,COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100 AS DISCOUNT_OF_EX_FACTORY_ACTUALS,
							  COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100 AS DISCOUNT_OF_EX_FACTORY_PROJECTED
								   
								   
                            FROM   (SELECT PF.PROJECTION_MASTER_SID,
                                           Sum(INVENTORY_ACTUAL_SALES) as ACT_AMOUNT_WITHDRAWN,
                                           Sum(INVENTORY_ACTUAL_UNITS) as ACT_UNITS_WITHDRAWN,
                                           Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)) as FOR_AMOUNT_WITHDRAWN,
                                           Sum(COALESCE(INVENTORY_FORECAST_UNITS, INVENTORY_ACTUAL_UNITS)) as FOR_UNITS_WITHDRAWN,
                                           Sum(DEMAND_ACTUAL_SALES) as DEMAND_SALES_ACTUAL,
                                           Sum(DEMAND_ACTUAL_UNITS) as ACT_GROSS_UNITS,
                                           Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)) as DEMAND_SALES_PROJECTED,
                                           Sum(COALESCE(DEMAND_FORECAST_UNITS, DEMAND_ACTUAL_UNITS)) as FOR_GROSS_UNITS,
                                           Sum(EXFACTORY_ACTUAL_SALES) as GTS_SALES_ACTUALS,
                                           Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) as GTS_SALES_PROJECTED,
                                           Sum(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS)) as UNITS,
                                           P.SEMI_ANNUAL,
                                           P.[YEAR] YEARLY
                                    FROM   #PRODUCT_FILE_TEMP PF
                                           JOIN PERIOD P
                                             ON P.PERIOD_SID = PF.PERIOD_SID
                                           JOIN #PROJECTION_MASTER PM
                                             ON PM.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID
                                                AND PM.ID <> 1
                                    GROUP  BY PF.PROJECTION_MASTER_SID,
                                              P.[YEAR],
                                              P.SEMI_ANNUAL) GTS
                                      LEFT JOIN (SELECT coalesce(act.PROJECTION_MASTER_SID,proj.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID,
											   Sum(ACTUAL_SALES) as CONTRACT_SALES_ACTUALS,
                                               Sum(PROJECTION_SALES) as CONTRACT_SALES_PROJECTED,
                                               Sum(ACTUAL_UNITS) as CONTRACT_UNITS_ACTUALS,
                                               Sum(PROJECTION_UNITS) as CONTRACT_UNITS_PROJECTED,
                                               Sum(COGS_ACTUAL) as COGS_ACTUALS,
                                                Sum(COGS_PROJECTED) as COGS_PROJECTED,
											    SEMI_ANNUAL,
                                                [YEAR]
                                        --, ITEM_MASTER_SID = COALESCE(ACT.ITEM_MASTER_SID, PROJ.ITEM_MASTER_SID)
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       (Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ) as COGS_ACTUAL,
													   NAS.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_SALES NAS
												INNER JOIN PROJECTION_DETAILS PD
												ON NAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
															   	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
																   where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NSP.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 ( Isnull(NSP.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ) as COGS_PROJECTED,
																 NSP.PROJECTION_DETAILS_SID
                                                          FROM   #NM_SALES_PROJECTION NSP
                                                                	INNER JOIN PROJECTION_DETAILS PD
												ON NSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
															   	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NSP.PERIOD_SID = U.PERIOD_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                                                            AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
														 INNER JOIN PERIOD P ON P.PERIOD_SID=COALESCE(ACT.PERIOD_SID,PROJ.PERIOD_SID)
                                        GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID,PROJ.PROJECTION_MASTER_SID)
										,   SEMI_ANNUAL,
                                        [YEAR]) SALES
										ON SALES.SEMI_ANNUAL=GTS.SEMI_ANNUAL
										AND SALES.YEAR=GTS.YEARLY
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) as CONTRACT_DISCOUNT_ACTUALS,
                                                Sum(PROJECTION_SALES) as CONTRACT_DISCOUNT_PROJECTED,
											   coalesce(act.PROJECTION_MASTER_SID,proj.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID,
											  SEMI_ANNUAL,
                                                [YEAR]
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                       NAD.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_CONTRACT_SID,
													   NAD.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_DISCOUNT NAD
                                               INNER JOIN PROJECTION_DETAILS PD
												ON NAD.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
													INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NDP.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_CONTRACT_SID,
																 NDP.PROJECTION_DETAILS_SID
                                                          FROM   NM_DISCOUNT_PROJECTION NDP
                                                                        	INNER JOIN PROJECTION_DETAILS PD
											                              	ON NDP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
																				INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																            where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                                                            AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
														 INNER JOIN PERIOD P ON P.PERIOD_SID=COALESCE(ACT.PERIOD_SID,PROJ.PERIOD_SID)
                                        GROUP  BY 
										 COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID)
										 ,SEMI_ANNUAL,YEAR) DISC
                                    ON DISC.SEMI_ANNUAL = COALESCE(GTS.SEMI_ANNUAL,SALES.SEMI_ANNUAL)
									AND DISC.YEAR=COALESCE(GTS.YEARLY,SALES.YEAR)
									 AND DISC.PROJECTION_MASTER_SID = COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID)
                             LEFT JOIN (SELECT Sum(ACTUAL_PPA_SALES) as PPA_DISCOUNT_ACTUALS,
                                               Sum(ACTUAL_PPA_RPU) as PPA_RPU_ACTUALS,
                                               Sum(PROJECTION_PPA_SALES) as PPA_DISCOUNT_PROJECTED,
                                               Sum(PPA_RPU) as PPA_RPU_PROJECTED,
                                               Sum(ACTUAL_SALES) as PPA_ACTUAL_SALES,
                                               Sum(ACTUAL_UNITS) as PPA_ACTUAL_UNITS,
                                               Sum(PROJECTION_SALES) as PPA_PROJECTION_SALES,
                                               Sum(PROJECTION_UNITS) as PPA_PROJECTION_UNITS,
											   COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID,
											    SEMI_ANNUAL,
                                                [YEAR]
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_DISCOUNT_DOLLAR as ACTUAL_PPA_SALES,
                                                       ACTUAL_DISCOUNT_DOLLAR as ACTUAL_PPA_RPU,
                                                       RS_CONTRACT_SID,
                                                       NS.ACTUAL_SALES as ACTUAL_SALES,
                                                       NS.ACTUAL_UNITS as ACTUAL_UNITS,
													   NAP.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_PPA NAP
                                                       INNER JOIN NM_ACTUAL_SALES NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
																  INNER JOIN PROJECTION_DETAILS PD
																  ON NS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
																  	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PROJECTION_DISCOUNT_DOLLAR as PPA_RPU,
                                                                 PROJECTION_DISCOUNT_DOLLAR as PROJECTION_PPA_SALES, 
                                                                 RS_CONTRACT_SID,
                                                                 NS.PROJECTION_SALES as PROJECTION_SALES, 
                                                                 NS.PROJECTION_UNITS as PROJECTION_UNITS,
																 NPP.PROJECTION_DETAILS_SID
                                                          FROM   NM_PPA_PROJECTION NPP
                                                                 INNER JOIN #NM_SALES_PROJECTION NS
                                                                          ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NPP.PERIOD_SID
																  INNER JOIN PROJECTION_DETAILS PD
																  ON NS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID   
																  	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																  where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)

                                                          AND    NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
											INNER JOIN PERIOD P ON P.PERIOD_SID=COALESCE(ACT.PERIOD_SID,PROJ.PERIOD_SID)
                                        GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID)
										 ,SEMI_ANNUAL,YEAR) PPA
                                    ON PPA.SEMI_ANNUAL = SALES.SEMI_ANNUAL
									AND PPA.YEAR=SALES.YEAR     
									   AND COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) = PPA.PROJECTION_MASTER_SID               
									  ORDER  BY SALES.SEMI_ANNUAL,
									  sales.YEAR
                        END
                      ELSE--------------------------------------------it will display the yearly wise data  -------------------------
                        BEGIN
                            INSERT INTO #PIVOT_RESULT
                                              
                                        (PROJECTION_ID,
                                         [YEAR],
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
										 INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
										 CONTRACT_SALES_ACTUALS,
                                         CONTRACT_SALES_PROJECTED,
										 CONTRACT_UNITS_ACTUALS,
                                         CONTRACT_UNITS_PROJECTED,
										 TOTAL_DISCOUNT,
                                         TOTAL_DISCOUNT_PROJECTED,
										 TOTAL_DISCOUNT_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
										 PPA_DISCOUNT_ACTUALS,
                                         PPA_DISCOUNT_PROJECTED,
										 PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                         PPA_DISCOUNT_PROJECTED_PERCENTAGE,
										 TOTAL_DISCOUNT_RPU_ACTUALS,
                                         TOTAL_DISCOUNT_RPU_PROJECTED,
										 TOTAL_PPA_RPU_ACTUALS,
                                         TOTAL_PPA_RPU_PROJECTED,
										 TOTAL_RPU_ACTUAL,
                                         TOTAL_RPU_PROJECTED,
										 NET_SALES,
                                         NET_SALES_PROJECTED,
										 COGS_ACTUALS,
                                         COGS_PROJECTED,
										 NET_PROFIT_ACTUAL,
                                         NET_PROFIT_PROJECTED,
										 TOTAL_RETURNS_RPU_ACTUALS,
                                         TOTAL_RETURNS_RPU_PROJECTED,
										 RETURNS_ACTUALS_AMOUNT,
                                         RETURNS_PROJECTED_AMOUNT,
										 RETURNS_ACTUALS_PERCENTAGE,
                                         RETURNS_PROJECTED_PERCENTAGE
										 ,NET_SALES_OF_EX_FACTORY_ACTUALS,
										 NET_SALES_OF_EX_FACTORY_PROJECTED
										 ,DISCOUNT_OF_EX_FACTORY_ACTUALS
										 ,DISCOUNT_OF_EX_FACTORY_PROJECTED)
                            SELECT COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
                                   COALESCE(GTS.[YEARLY], SALES.[YEAR])                             AS YEARS,
                                   Isnull(GTS.GTS_SALES_ACTUALS, 0) AS EX_FACTORY_SALES_ACTUALS,
                                   Isnull(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
								   Isnull(GTS.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS,
                                   Isnull(GTS.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
								   Isnull(GTS.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_ACTUALS,
                                   Isnull(GTS.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
								   (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUAL_PERCENT,
                                   (Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                                   ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
								   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0)AS CONTRACT_SALES_ACTUALS,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
								   Isnull(CONTRACT_UNITS_ACTUALS, 0)AS CONTRACT_UNITS_ACTUALS,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS TOTAL_DISCOUNT,
                                   Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                    + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PERCENTAGE,
                                   ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) AS PPA_DISCOUNT_ACTUALS,
								   Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) AS PPA_DISCOUNT_PROJECTED,
								   Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100 AS PPA_DISCOUNT_ACTUALS_PERCENTAGE,
                                   Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100 AS PPA_DISCOUNT_PROJECTED_PERCENTAGE,
								   (Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_DISCOUNT_RPU_ACTUALS,
                                   (Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_DISCOUNT_RPU_PROJECTED,
                                   (Isnull(PPA.PPA_RPU_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_PPA_RPU_ACTUALS,
                                   (Isnull(PPA.PPA_RPU_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_PPA_RPU_PROJECTED,
                                   (Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(PPA.PPA_RPU_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUAL,
                                   (Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(PPA.PPA_RPU_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
								   ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) AS NET_SALES,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
								   Isnull(SALES.COGS_ACTUALS, 0) AS COGS_ACTUALS,
                                   Isnull(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ) - ( Isnull(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL,
                                   ( ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                      + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED,
								   000000.0 AS TOTAL_RETURNS_RPU_ACTUALS,
                                   000000.0 as TOTAL_RETURNS_RPU_PROJECTED,
								   000000.0 as RETURNS_ACTUALS_AMOUNT,
                                   000000.0 as RETURNS_PROJECTED_AMOUNT,
								   000000.0 as RETURNS_ACTUALS_PERCENTAGE,
                                   000000.0 as RETURNS_PROJECTED_PERCENTAGE,
								  (COALESCE((( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100) AS NET_SALES_OF_EX_FACTORY_ACTUALS,
                              (COALESCE((( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100) AS NET_SALES_OF_EX_FACTORY_PROJECTED
							,COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0) + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100 AS DISCOUNT_OF_EX_FACTORY_ACTUALS,
							  COALESCE(((Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100 AS DISCOUNT_OF_EX_FACTORY_PROJECTED
								   
								   
                            FROM   (SELECT PF.PROJECTION_MASTER_SID,
                                           Sum(INVENTORY_ACTUAL_SALES) as ACT_AMOUNT_WITHDRAWN,
                                           Sum(INVENTORY_ACTUAL_UNITS) as ACT_UNITS_WITHDRAWN,
                                           Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)) as FOR_AMOUNT_WITHDRAWN,
                                           Sum(COALESCE(INVENTORY_FORECAST_UNITS, INVENTORY_ACTUAL_UNITS)) as FOR_UNITS_WITHDRAWN,
                                           Sum(DEMAND_ACTUAL_SALES) as DEMAND_SALES_ACTUAL,
                                           Sum(DEMAND_ACTUAL_UNITS) as ACT_GROSS_UNITS,
                                           Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)) as DEMAND_SALES_PROJECTED,
                                           Sum(COALESCE(DEMAND_FORECAST_UNITS, DEMAND_ACTUAL_UNITS)) as FOR_GROSS_UNITS,
                                           Sum(EXFACTORY_ACTUAL_SALES) as GTS_SALES_ACTUALS,
                                           Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)) as GTS_SALES_PROJECTED,
                                           Sum(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS)) as UNITS,
                                           P.[YEAR] YEARLY
                                    FROM   #PRODUCT_FILE_TEMP PF
                                           JOIN PERIOD P
                                             ON P.PERIOD_SID = PF.PERIOD_SID
                                           JOIN #PROJECTION_MASTER PM
                                             ON PM.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID
                                                AND PM.ID <> 1
                                    GROUP  BY PF.PROJECTION_MASTER_SID,
                                              P.[YEAR]) GTS
                                           
                                    LEFT JOIN (SELECT Sum(ACTUAL_SALES) as CONTRACT_SALES_ACTUALS,
                                              Sum(PROJECTION_SALES) as CONTRACT_SALES_PROJECTED,
                                              COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID,
                                              Sum(ACTUAL_UNITS) as CONTRACT_UNITS_ACTUALS,
                                              Sum(PROJECTION_UNITS) as CONTRACT_UNITS_PROJECTED,
                                              Sum(COGS_ACTUAL) as COGS_ACTUALS,
                                              Sum(COGS_PROJECTED) as COGS_PROJECTED,
                                                [YEAR]
                                        --, ITEM_MASTER_SID = COALESCE(ACT.ITEM_MASTER_SID, PROJ.ITEM_MASTER_SID)
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       (Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ) as COGS_ACTUAL
													   ,NAS.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_SALES NAS
												INNER JOIN PROJECTION_DETAILS PD
												ON NAS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
															   	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
																   where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NSP.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                            ( Isnull(NSP.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) ) as COGS_PROJECTED
															,NSP.PROJECTION_DETAILS_SID
                                                          FROM   #NM_SALES_PROJECTION NSP
                                                                	INNER JOIN PROJECTION_DETAILS PD
												ON NSP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
															   	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NSP.PERIOD_SID = U.PERIOD_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                                                            AND NSP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
														 INNER JOIN PERIOD P ON P.PERIOD_SID=COALESCE(ACT.PERIOD_SID,PROJ.PERIOD_SID)
                                        GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID,PROJ.PROJECTION_MASTER_SID),
                                        [YEAR]) SALES
										ON  SALES.YEAR=GTS.YEARLY
                             LEFT JOIN (SELECT  Sum(ACTUAL_SALES) as CONTRACT_DISCOUNT_ACTUALS,
                                                Sum(PROJECTION_SALES) as CONTRACT_DISCOUNT_PROJECTED,
											   coalesce(act.PROJECTION_MASTER_SID,proj.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID,
                                                [YEAR]
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                       NAD.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       RS_CONTRACT_SID,
													   NAD.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_DISCOUNT NAD
                                               INNER JOIN PROJECTION_DETAILS PD
												ON NAD.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
													INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NDP.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 RS_CONTRACT_SID,
																 NDP.PROJECTION_DETAILS_SID
                                                          FROM   NM_DISCOUNT_PROJECTION NDP
                                                                        	INNER JOIN PROJECTION_DETAILS PD
												ON NDP.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
													INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                                                            AND NDP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
														 INNER JOIN PERIOD P ON P.PERIOD_SID=COALESCE(ACT.PERIOD_SID,PROJ.PERIOD_SID)
                                        GROUP  BY 
										 COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID)
										 ,YEAR) DISC
                                    ON  DISC.YEAR=COALESCE(GTS.YEARLY,SALES.YEAR)
									 AND DISC.PROJECTION_MASTER_SID = COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID)
                             LEFT JOIN (SELECT  Sum(ACTUAL_PPA_SALES) as PPA_DISCOUNT_ACTUALS,
                                                Sum(ACTUAL_PPA_RPU) as PPA_RPU_ACTUALS,
                                                Sum(PROJECTION_PPA_SALES) as PPA_DISCOUNT_PROJECTED,
                                                Sum(PPA_RPU) as PPA_RPU_PROJECTED,
                                                Sum(ACTUAL_SALES) as PPA_ACTUAL_SALES,
                                                Sum(ACTUAL_UNITS) as PPA_ACTUAL_UNITS,
                                                Sum(PROJECTION_SALES) as PPA_PROJECTION_SALES,
                                                Sum(PROJECTION_UNITS) as PPA_PROJECTION_UNITS,
											    COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID) as PROJECTION_MASTER_SID,
                                                [YEAR]
                                        FROM   (SELECT PD.PROJECTION_MASTER_SID,
                                                       NAP.PERIOD_SID,
                                                       ACTUAL_DISCOUNT_DOLLAR as ACTUAL_PPA_SALES,
                                                       ACTUAL_DISCOUNT_DOLLAR as ACTUAL_PPA_RPU,
                                                       RS_CONTRACT_SID,
                                                       NS.ACTUAL_SALES as ACTUAL_SALES,
                                                       NS.ACTUAL_UNITS aS ACTUAL_UNITS
													   ,NAP.PROJECTION_DETAILS_SID
                                                FROM   NM_ACTUAL_PPA NAP
                                                       INNER JOIN NM_ACTUAL_SALES NS
                                                               ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NAP.PERIOD_SID
																  INNER JOIN PROJECTION_DETAILS PD
																  ON NS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID
																  	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																     where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_MASTER_SID,
                                                                 NPP.PERIOD_SID,
                                                                 PROJECTION_DISCOUNT_DOLLAR AS PPA_RPU,
                                                                 PROJECTION_DISCOUNT_DOLLAR AS PROJECTION_PPA_SALES,
                                                                 RS_CONTRACT_SID,
                                                                  NS.PROJECTION_SALES AS PROJECTION_SALES,
                                                                  NS.PROJECTION_UNITS AS PROJECTION_UNITS,
																  NPP.PROJECTION_DETAILS_SID
                                                          FROM   NM_PPA_PROJECTION NPP
                                                                 INNER JOIN #NM_SALES_PROJECTION NS
                                                                          ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                  AND NS.PERIOD_SID = NPP.PERIOD_SID
																  INNER JOIN PROJECTION_DETAILS PD
																  ON NS.PROJECTION_DETAILS_SID=PD.PROJECTION_DETAILS_SID   
																  	INNER JOIN #CURRENT_CPP_COMP_PRIOR_CPP CC
														ON CC.CCP_DETAILS_SID=PD.CCP_DETAILS_SID
																  where EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PM.PROJECTION_MASTER_SID=PD.PROJECTION_MASTER_SID)

                                                          AND    NPP.PERIOD_SID BETWEEN @PROJ_START_PERIOD_SID AND @END_PERIOD_SID) PROJ
                                                      ON ACT.PROJECTION_MASTER_SID = PROJ.PROJECTION_MASTER_SID
													  AND ACT.PROJECTION_DETAILS_SID=PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                         AND ACT.RS_CONTRACT_SID = PROJ.RS_CONTRACT_SID
											INNER JOIN PERIOD P ON P.PERIOD_SID=COALESCE(ACT.PERIOD_SID,PROJ.PERIOD_SID)
                                        GROUP  BY COALESCE(ACT.PROJECTION_MASTER_SID, PROJ.PROJECTION_MASTER_SID)
										,YEAR) PPA
                                    ON  PPA.YEAR=SALES.YEAR     
									   AND COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) = PPA.PROJECTION_MASTER_SID               
									  ORDER  BY SALES.YEAR
									  END

                      --SECOND TO LAST PROJECTION_DETAILS_SID(PULL FROM MAIN TABLE) END
                      ------------- PIVOTING START
                            DECLARE @SQL       NVARCHAR(MAX),
                              @LOOP_CNTR INT,
                              @MAX_CCP   INT

                      SET @SQL = 'SELECT YEAR,PERIOD, '
                      SET @LOOP_CNTR = 1
                      SET @MAX_CCP = (SELECT Max(ID)
                                      FROM   #PROJECTION_MASTER)

                      WHILE @LOOP_CNTR <= @MAX_CCP
                        BEGIN
						IF @LOOP_CNTR=1
						BEGIN
                            SET @SQL = @SQL+'EX_FACTORY_SALES_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_ACTUALS END),0) ELSE NULL END,
								 EX_FACTORY_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_PROJECTED END),0),
                                  DEMAND_SALES_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_ACTUALS END),0) ELSE NULL END,
                                                                DEMAND_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_PROJECTED END),0),
                                                                INVENTORY_WITHDRAWAL_SALES_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUALS END),0) ELSE NULL END,
                                                                INVENTORY_WITHDRAWAL_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED END),0),
                                                                EX_FACTORY_SALES_ACTUALS_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_ACTUALS_PERCENT END),0) ELSE NULL END,
                                                                EX_FACTORY_SALES_PROJECTED_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_PROJECTED_PERCENT END),0),
                                                                DEMAND_SALES_ACTUALS_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_ACTUALS_PERCENT END),0) ELSE NULL END,
                                                                DEMAND_SALES_PROJECTED_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_PROJECTED_PERCENT END),0),
                                                                INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT END),0) ELSE NULL END,
                                                                INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT END),0),
                                                      CONTRACT_SALES_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_SALES_ACTUALS END),0) ELSE NULL END,
                                                      CONTRACT_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_SALES_PROJECTED END),0),
                                                                     CONTRACT_UNITS_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_UNITS_ACTUALS END),0) ELSE NULL END,
                                                                     CONTRACT_UNITS_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_UNITS_PROJECTED END),0),
                                                                     TOTAL_DISCOUNT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT END),0) ELSE NULL END,
                                                                     TOTAL_DISCOUNT_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT_PROJECTED END),0),
                                                                     TOTAL_DISCOUNT_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT_PERCENTAGE END),0) ELSE NULL END,
                                                                     TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                                                                     PPA_DISCOUNT_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN PPA_DISCOUNT_ACTUALS END),0) ELSE NULL END,
                                                                     PPA_DISCOUNT_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN PPA_DISCOUNT_PROJECTED END),0),
                                                                     PPA_DISCOUNT_ACTUALS_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN PPA_DISCOUNT_ACTUALS_PERCENTAGE END),0) ELSE NULL END,
                                                                     PPA_DISCOUNT_PROJECTED_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN PPA_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                                                                TOTAL_DISCOUNT_RPU_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_DISCOUNT_RPU_ACTUALS END),0) ELSE NULL END,
                                                                TOTAL_DISCOUNT_RPU_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_DISCOUNT_RPU_PROJECTED END),0),
                                                                  TOTAL_PPA_RPU_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_PPA_RPU_ACTUALS END),0) ELSE NULL END,
                                                                  TOTAL_PPA_RPU_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_PPA_RPU_PROJECTED END),0),
                                                                     TOTAL_RPU_ACTUAL_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_RPU_ACTUAL END),0) ELSE NULL END,
                                                                     TOTAL_RPU_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_RPU_PROJECTED END),0),
                                                                     NET_SALES_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_SALES END),0) ELSE NULL END,
                                                                     NET_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_SALES_PROJECTED END),0),
                                                                COGS_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN COGS_ACTUALS END),0) ELSE NULL END,
                                                                COGS_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN COGS_PROJECTED END),0),
                                                                NET_PROFIT_ACTUAL_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_PROFIT_ACTUAL END),0) ELSE NULL END,
                                                                NET_PROFIT_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_PROFIT_PROJECTED END),0),
                                                                TOTAL_RETURNS_RPU_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_RETURNS_RPU_ACTUALS END),0) ELSE NULL END,
                                                                TOTAL_RETURNS_RPU_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_RETURNS_RPU_PROJECTED END),0),
                                                                RETURNS_ACTUALS_AMOUNT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN RETURNS_ACTUALS_AMOUNT END),0) ELSE NULL END,
                                                                RETURNS_PROJECTED_AMOUNT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN RETURNS_PROJECTED_AMOUNT END),0),
                                                                RETURNS_ACTUALS_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN RETURNS_ACTUALS_PERCENTAGE END),0) ELSE NULL END,
                                                                RETURNS_PROJECTED_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + 'THEN RETURNS_PROJECTED_PERCENTAGE END),0),
                                                                     NET_SALES_ACTUAL_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_SALES_OF_EX_FACTORY_ACTUALS END),0) ELSE NULL END,
                                                                     NET_SALES_PROJECTED_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_SALES_OF_EX_FACTORY_PROJECTED END),0),
                                                                     DISCOUNT_OF_EX_FACTORY_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' =  CASE WHEN '''+CAST(@CURRENT_DATE AS VARCHAR(10))+''' > DATEFROMPARTS(YEAR,PERIOD,01) THEN  ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),0) ELSE NULL END,
                                                                     DISCOUNT_OF_EX_FACTORY_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0), '
							END
							ELSE
							BEGIN
							 SET @SQL = @SQL+'EX_FACTORY_SALES_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_ACTUALS END),0),
								 EX_FACTORY_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_PROJECTED END),0),
                                  DEMAND_SALES_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_ACTUALS END),0),
                                                                DEMAND_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_PROJECTED END),0),
                                                                INVENTORY_WITHDRAWAL_SALES_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUALS END),0),
                                                                INVENTORY_WITHDRAWAL_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED END),0),
                                                                EX_FACTORY_SALES_ACTUALS_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_ACTUALS_PERCENT END),0),
                                                                EX_FACTORY_SALES_PROJECTED_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_PROJECTED_PERCENT END),0),
                                                                DEMAND_SALES_ACTUALS_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_ACTUALS_PERCENT END),0),
                                                                DEMAND_SALES_PROJECTED_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_PROJECTED_PERCENT END),0),
                                                                INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT END),0),
                                                                INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT END),0),
                                                      CONTRACT_SALES_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_SALES_ACTUALS END),0),
                                                      CONTRACT_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_SALES_PROJECTED END),0),
                                                                     CONTRACT_UNITS_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_UNITS_ACTUALS END),0),
                                                                     CONTRACT_UNITS_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_UNITS_PROJECTED END),0),
                                                                     TOTAL_DISCOUNT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT END),0),
                                                                     TOTAL_DISCOUNT_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT_PROJECTED END),0),
                                                                     TOTAL_DISCOUNT_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT_PERCENTAGE END),0),
                                                                     TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                                                                     PPA_DISCOUNT_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN PPA_DISCOUNT_ACTUALS END),0),
                                                                     PPA_DISCOUNT_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN PPA_DISCOUNT_PROJECTED END),0),
                                                                     PPA_DISCOUNT_ACTUALS_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN PPA_DISCOUNT_ACTUALS_PERCENTAGE END),0),
                                                                     PPA_DISCOUNT_PROJECTED_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN PPA_DISCOUNT_PROJECTED_PERCENTAGE END),0),
                                                                TOTAL_DISCOUNT_RPU_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_DISCOUNT_RPU_ACTUALS END),0),
                                                                TOTAL_DISCOUNT_RPU_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_DISCOUNT_RPU_PROJECTED END),0),
                                                                  TOTAL_PPA_RPU_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_PPA_RPU_ACTUALS END),0),
                                                                  TOTAL_PPA_RPU_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_PPA_RPU_PROJECTED END),0),
                                                                     TOTAL_RPU_ACTUAL_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_RPU_ACTUAL END),0),
                                                                     TOTAL_RPU_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_RPU_PROJECTED END),0),
                                                                     NET_SALES_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_SALES END),0),
                                                                     NET_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_SALES_PROJECTED END),0),
                                                                COGS_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN COGS_ACTUALS END),0),
                                                                COGS_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN COGS_PROJECTED END),0),
                                                                NET_PROFIT_ACTUAL_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_PROFIT_ACTUAL END),0),
                                                                NET_PROFIT_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_PROFIT_PROJECTED END),0),
                                                                TOTAL_RETURNS_RPU_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_RETURNS_RPU_ACTUALS END),0),
                                                                TOTAL_RETURNS_RPU_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN TOTAL_RETURNS_RPU_PROJECTED END),0),
                                                                RETURNS_ACTUALS_AMOUNT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN RETURNS_ACTUALS_AMOUNT END),0),
                                                                RETURNS_PROJECTED_AMOUNT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN RETURNS_PROJECTED_AMOUNT END),0),
                                                                RETURNS_ACTUALS_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN RETURNS_ACTUALS_PERCENTAGE END),0),
                                                                RETURNS_PROJECTED_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + 'THEN RETURNS_PROJECTED_PERCENTAGE END),0),
                                                                     NET_SALES_ACTUAL_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_SALES_OF_EX_FACTORY_ACTUALS END),0),
                                                                     NET_SALES_PROJECTED_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN NET_SALES_OF_EX_FACTORY_PROJECTED END),0),
                                                                     DISCOUNT_OF_EX_FACTORY_ACTUALS_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),0),
                                                                     DISCOUNT_OF_EX_FACTORY_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(20))
                                        + ' THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0), '
										END
                            SET @LOOP_CNTR += 1
                        END

                      SET @SQL = LEFT(@SQL, Len(@SQL) - 1)
                    SET @SQL += ' FROM   #PIVOT_RESULT PR
               INNER JOIN #PROJECTION_MASTER PM
                       ON PR.PROJECTION_ID = PM.PROJECTION_MASTER_SID
                                  GROUP  BY [YEAR],PERIOD
                                  ORDER BY [YEAR],PERIOD'

                      EXEC Sp_executesql
                        @SQL
                  ------------PIVOTING END
                  END
            --    ELSE -- PERIOD VIEW
            --      BEGIN
            --          IF @PROJ_FREQUENCY = 'MONTHLY'
            --            BEGIN
            --                SELECT @PROJECTION_SID PROJECTION_ID,
            --                       GTS_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
            --                       GTS_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
            --                       GTS_UNITS = Isnull(GTS.UNITS, 0),
            --                       P.[MONTH],
            --                       P.[YEAR],
            --                       CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
            --                       CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
            --                       CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
            --                       CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
            --                       TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                        + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
            --                       TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                  + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
            --                       TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
            --                       TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
            --                       --TOTAL_DISCOUNT_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) +
            --                       --                              + COALESCE(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(PPA.PPA_ACTUAL_SALES, 0), 0) ) * 100,
            --                       --TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) +
            --                       --                                        + COALESCE(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(PPA.PPA_PROJECTION_SALES, 0), 0) ) * 100,
            --                       ----DISC.CONTRACT_DISCOUNT_ACTUALS,  SALES.CONTRACT_SALES_ACTUALS,    PPA.PPA_DISCOUNT_ACTUALS,  PPA.PPA_ACTUAL_SALES,   
            --                       EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
            --                       EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
            --                       PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
            --                       PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
            --                       PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
            --                       PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
            --                       NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
            --                       NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                                                           + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) )
            --                FROM   (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
            --                               GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
            --                               UNITS = Sum(UNITS),
            --                               MONTHLY PERIOD,
            --                               YEARLY,
            --                               PERIOD_SID
            --                        FROM   #TEMP_GTS_DETAILS G
            --                               --INNER JOIN @ITEM_INFO ACT
            --                               --        ON G.ITEMID = ACT.ITEM_ID
            --                               INNER JOIN PERIOD P
            --                                       ON P.[MONTH] = G.MONTHLY
            --                                          AND P.[YEAR] = G.YEARLY
            --                        GROUP  BY MONTHLY,
            --                                  YEARLY,
            --                                  PERIOD_SID) GTS
            --                       FULL JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
            --                                         CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
            --                                         PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID),
            --                                         CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
            --                                         CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS)
            --                                  FROM   (SELECT PROJECTION_DETAILS_SID,
            --                                                 PERIOD_SID,
            --                                                 ACTUAL_SALES,
            --                                                 ACTUAL_UNITS
            --                                          FROM   NM_ACTUAL_SALES NAS
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
            --                                         FULL JOIN (SELECT PROJECTION_DETAILS_SID,
            --                                                           PERIOD_SID,
            --                                                           PROJECTION_SALES,
            --                                                           PROJECTION_UNITS
            --                                                    FROM   NM_SALES_PROJECTION NPS
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                  GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SALES
            --                              ON SALES.PERIOD_SID = GTS.PERIOD_SID
            --                       INNER JOIN PERIOD P
            --                               ON P.PERIOD_SID = SALES.PERIOD_SID
            --                       LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
            --                                         CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
            --                                         PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
            --                                  FROM   (SELECT PROJECTION_DETAILS_SID,
            --                                                 PERIOD_SID,
            --                                                 ACTUAL_SALES,
            --                                                 RS_MODEL_SID
            --                                          FROM   NM_ACTUAL_DISCOUNT NAD
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
            --                                                 AND EXISTS (SELECT 1
            --                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
            --                                                             WHERE  A.TOKEN = NAD.RS_MODEL_SID)) ACT
            --                                         FULL JOIN (SELECT PROJECTION_DETAILS_SID,
            --                                                           PERIOD_SID,
            --                                                           PROJECTION_SALES,
            --                                                           RS_MODEL_SID
            --                                                    FROM   NM_DISCOUNT_PROJECTION NDP
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
            --                                                           AND EXISTS (SELECT 1
            --                                                                       FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
            --                                                                       WHERE  A.TOKEN = NDP.RS_MODEL_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                                   AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
            --                                  GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) DISC
            --                              ON DISC.PERIOD_SID = P.PERIOD_SID
            --                       LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
            --                                         PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
            --                                         PPA_PROJECTION_SALES = Sum(PPA_PROJECTION_SALES),
            --                                         PPA_PROJECTION_UNITS = Sum(PPA_PROJECTION_UNITS),
            --                                         PPA_ACTUAL_SALES = Sum(PPA_ACTUAL_SALES),
            --                                         PPA_ACTUAL_UNITS = Sum(PPA_ACTUAL_UNITS),
            --                                         PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
            --                                  FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
            --                                                 NAP.PERIOD_SID,
            --                                                 ACTUAL_SALES = ACTUAL_DISCOUNT_DOLLAR,
            --                                                 PPA_ACTUAL_SALES = NS.ACTUAL_SALES,
            --                                                 PPA_ACTUAL_UNITS = NS.ACTUAL_UNITS,
            --                                                 RS_MODEL_SID
            --                                          FROM   NM_ACTUAL_PPA NAP
            --                                                 INNER JOIN NM_ACTUAL_SALES NS
            --                                                         ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
            --                                                            AND NS.PERIOD_SID = NAP.PERIOD_SID
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
            --                                         FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
            --                                                           NPP.PERIOD_SID,
            --                                                           PROJECTION_SALES = PROJECTION_DISCOUNT_DOLLAR,
            --                                                           PPA_PROJECTION_SALES = NS.PROJECTION_SALES,
            --                                                           PPA_PROJECTION_UNITS = NS.PROJECTION_UNITS,
            --                                                           RS_MODEL_SID
            --                                                    FROM   NM_PPA_PROJECTION NPP
            --                                                           INNER JOIN NM_SALES_PROJECTION NS
            --                                                                   ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
            --                                                                      AND NS.PERIOD_SID = NPP.PERIOD_SID
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                                   AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
            --                                  GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) PPA
            --                              ON PPA.PERIOD_SID = SALES.PERIOD_SID
            --                ORDER  BY P.[YEAR],
            --                          P.[MONTH]
            --            END
            --          ELSE IF @PROJ_FREQUENCY = 'QUARTERLY'
            --            BEGIN
            --                SELECT @PROJECTION_SID PROJECTION_ID,
            --                       GTS_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
            --                       GTS_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
            --                       GTS_UNITS = Isnull(GTS.UNITS, 0),
            --                       SALES.[QUARTER],
            --                       SALES.[YEAR],
            --                       CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
            --                       CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
            --                       CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
            --                       CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
            --                       TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                        + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
            --                       TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                  + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
            --                       TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
            --                       TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
            --                       --TOTAL_DISCOUNT_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) +
            --                       --                              + COALESCE(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(PPA.PPA_ACTUAL_SALES, 0), 0) ) * 100,
            --                       --TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) +
            --                       --                                        + COALESCE(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(PPA.PPA_PROJECTION_SALES, 0), 0) ) * 100,
            --                       ----DISC.CONTRACT_DISCOUNT_ACTUALS,  SALES.CONTRACT_SALES_ACTUALS,    PPA.PPA_DISCOUNT_ACTUALS,  PPA.PPA_ACTUAL_SALES,   
            --                       EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
            --                       EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
            --                       PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
            --                       PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
            --                       PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
            --                       PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
            --                       NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
            --                       NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                                                           + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) )
            --                FROM   (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
            --                               GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
            --                               UNITS = Sum(UNITS),
            --                               QUARTERLY,
            --                               YEARLY
            --                        FROM   #TEMP_GTS_DETAILS G
            --                        --INNER JOIN @ITEM_INFO ACT
            --                        --        ON G.ITEMID = ACT.ITEM_ID
            --                        GROUP  BY QUARTERLY,
            --                                  YEARLY) GTS
            --                       FULL JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
            --                                         CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
            --                                         [QUARTER],
            --                                         [YEAR],
            --                                         CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
            --                                         CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS)
            --                                  FROM   (SELECT PROJECTION_DETAILS_SID,
            --                                                 PERIOD_SID,
            --                                                 ACTUAL_SALES,
            --                                                 ACTUAL_UNITS
            --                                          FROM   NM_ACTUAL_SALES NAS
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
            --                                         FULL JOIN (SELECT PROJECTION_DETAILS_SID,
            --                                                           PERIOD_SID,
            --                                                           PROJECTION_SALES,
            --                                                           PROJECTION_UNITS
            --                                                    FROM   NM_SALES_PROJECTION NPS
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                         INNER JOIN PERIOD P
            --                                                 ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
            --                                  GROUP  BY [QUARTER],
            --                                            [YEAR]) SALES
            --                              ON SALES.[QUARTER] = GTS.QUARTERLY
            --                                 AND SALES.[YEAR] = GTS.YEARLY
            --                       LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
            --                                         CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
            --                                         [QUARTER],
            --                                         [YEAR]
            --                                  FROM   (SELECT PROJECTION_DETAILS_SID,
            --                                                 PERIOD_SID,
            --                                                 ACTUAL_SALES,
            --                                                 RS_MODEL_SID
            --                                          FROM   NM_ACTUAL_DISCOUNT NAD
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
            --                                                 AND EXISTS (SELECT 1
            --                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
            --                                                             WHERE  A.TOKEN = NAD.RS_MODEL_SID)) ACT
            --                                         FULL JOIN (SELECT PROJECTION_DETAILS_SID,
            --                                                           PERIOD_SID,
            --                                                           PROJECTION_SALES,
            --                                                           RS_MODEL_SID
            --                                                    FROM   NM_DISCOUNT_PROJECTION NDP
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
            --                                                           AND EXISTS (SELECT 1
            --                                                                       FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
            --                                                                       WHERE  A.TOKEN = NDP.RS_MODEL_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                                   AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
            --                                         INNER JOIN PERIOD P
            --                                                 ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
            --                                  GROUP  BY [QUARTER],
            --                                            [YEAR]) DISC
            --                              ON DISC.[QUARTER] = SALES.[QUARTER]
            --                                 AND DISC.[YEAR] = SALES.[YEAR]
            --                       LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
            --                                         PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
            --                                         PPA_PROJECTION_SALES = Sum(PPA_PROJECTION_SALES),
            --                                         PPA_PROJECTION_UNITS = Sum(PPA_PROJECTION_UNITS),
            --                                         PPA_ACTUAL_SALES = Sum(PPA_ACTUAL_SALES),
            --                                         PPA_ACTUAL_UNITS = Sum(PPA_ACTUAL_UNITS),
            --                                         [QUARTER],
            --                                         [YEAR]
            --                                  FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
            --                                                 NAP.PERIOD_SID,
            --                                                 ACTUAL_SALES = ACTUAL_DISCOUNT_DOLLAR,
            --                                                 PPA_ACTUAL_SALES = NS.ACTUAL_SALES,
            --                                                 PPA_ACTUAL_UNITS = NS.ACTUAL_UNITS,
            --                                                 RS_MODEL_SID
            --                                          FROM   NM_ACTUAL_PPA NAP
            --                                                 INNER JOIN NM_ACTUAL_SALES NS
            --                                                         ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
            --                                                            AND NS.PERIOD_SID = NAP.PERIOD_SID
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
            --                                         FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
            --                                                           NPP.PERIOD_SID,
            --                                                           PROJECTION_SALES = PROJECTION_DISCOUNT_DOLLAR,
            --                                                           PPA_PROJECTION_SALES = NS.PROJECTION_SALES,
            --                                                           PPA_PROJECTION_UNITS = NS.PROJECTION_UNITS,
            --                                                           RS_MODEL_SID
            --                                                    FROM   NM_PPA_PROJECTION NPP
            --                                                           INNER JOIN NM_SALES_PROJECTION NS
            --                                                                   ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
            --                                                                      AND NS.PERIOD_SID = NPP.PERIOD_SID
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                                   AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
            --                                         INNER JOIN PERIOD P
            --                                                 ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
            --                                  GROUP  BY [QUARTER],
            --                                            [YEAR]) PPA
            --                              ON PPA.[QUARTER] = SALES.[QUARTER]
            --                                 AND PPA.[YEAR] = SALES.[YEAR]
            --                ORDER  BY SALES.[YEAR],
            --                          SALES.[QUARTER]
            --            END
            --          ELSE IF @PROJ_FREQUENCY = 'SEMI-ANNUAL'
            --            BEGIN
            --                SELECT @PROJECTION_SID PROJECTION_ID,
            --                       GTS_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
            --                       GTS_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
            --                       GTS_UNITS = Isnull(GTS.UNITS, 0),
            --                       SALES.[SEMI_ANNUAL],
            --                       SALES.[YEAR],
            --                       CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
            --                       CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
            --                       CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
            --                       CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
            --                       TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                        + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
            --                       TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                  + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
            --                       TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
            --                       TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
            --                       --TOTAL_DISCOUNT_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) +
            --                       --                              + COALESCE(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(PPA.PPA_ACTUAL_SALES, 0), 0) ) * 100,
            --                       --TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) +
            --                       --                                        + COALESCE(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(PPA.PPA_PROJECTION_SALES, 0), 0) ) * 100,
            --                       ----DISC.CONTRACT_DISCOUNT_ACTUALS,  SALES.CONTRACT_SALES_ACTUALS,    PPA.PPA_DISCOUNT_ACTUALS,  PPA.PPA_ACTUAL_SALES,   
            --                       EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
            --                       EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
            --                       PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
            --                       PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
            --                       PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
            --                       PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
            --                       NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
            --                       NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                                                           + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) )
            --                FROM   (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
            --                               GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
            --                               Sum(GTS.UNITS) UNITS,
            --                               CASE
            --                                 WHEN GTS.QUARTERLY <= 2 THEN 1
            --                                 ELSE 2
            --                               END            SEMI_ANNUAL,
            --                               GTS.YEARLY
            --                        FROM   #TEMP_GTS_DETAILS GTS
            --                        --JOIN @ITEM_INFO ACT
            --                        --  ON GTS.ITEMID = ACT.ITEM_ID
            --                        GROUP  BY CASE
            --                                    WHEN GTS.QUARTERLY <= 2 THEN 1
            --                                    ELSE 2
            --                                  END,
            --                                  GTS.YEARLY) GTS
            --                       FULL JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
            --                                         CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
            --                                         SEMI_ANNUAL,
            --                                         [YEAR],
            --                                         CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
            --                                         CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS)
            --                                  FROM   (SELECT PROJECTION_DETAILS_SID,
            --                                                 PERIOD_SID,
            --                                                 ACTUAL_SALES,
            --                                                 ACTUAL_UNITS
            --                                          FROM   NM_ACTUAL_SALES NAS
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
            --                                         FULL JOIN (SELECT PROJECTION_DETAILS_SID,
            --                                                           PERIOD_SID,
            --                                                           PROJECTION_SALES,
            --                                                           PROJECTION_UNITS
            --                                                    FROM   NM_SALES_PROJECTION NPS
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                         INNER JOIN PERIOD P
            --                                                 ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
            --                                  GROUP  BY SEMI_ANNUAL,
            --                                            [YEAR]) SALES
            --                              ON SALES.SEMI_ANNUAL = GTS.SEMI_ANNUAL
            --                                 AND SALES.[YEAR] = GTS.YEARLY
            --                       LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
            --                                         CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
            --                                         SEMI_ANNUAL,
            --                                         [YEAR]
            --                                  FROM   (SELECT PROJECTION_DETAILS_SID,
            --                                                 PERIOD_SID,
            --                                                 ACTUAL_SALES,
            --                                                 RS_MODEL_SID
            --                                          FROM   NM_ACTUAL_DISCOUNT NAD
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
            --                                                 AND EXISTS (SELECT 1
            --                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
            --                                                             WHERE  A.TOKEN = NAD.RS_MODEL_SID)) ACT
            --                                         FULL JOIN (SELECT PROJECTION_DETAILS_SID,
            --                                                           PERIOD_SID,
            --                                                           PROJECTION_SALES,
            --                                                           RS_MODEL_SID
            --                                                    FROM   NM_DISCOUNT_PROJECTION NDP
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
            --                                                           AND EXISTS (SELECT 1
            --                                                                       FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
            --                                                                       WHERE  A.TOKEN = NDP.RS_MODEL_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                                   AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
            --                                         INNER JOIN PERIOD P
            --                                                 ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
            --                                  GROUP  BY SEMI_ANNUAL,
            --                                            [YEAR]) DISC
            --                              ON DISC.SEMI_ANNUAL = SALES.SEMI_ANNUAL
            --                                 AND DISC.[YEAR] = SALES.[YEAR]
            --                       LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
            --                                         PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
            --                                         PPA_PROJECTION_SALES = Sum(PPA_PROJECTION_SALES),
            --                                         PPA_PROJECTION_UNITS = Sum(PPA_PROJECTION_UNITS),
            --                                         PPA_ACTUAL_SALES = Sum(PPA_ACTUAL_SALES),
            --                                         PPA_ACTUAL_UNITS = Sum(PPA_ACTUAL_UNITS),
            --                                         SEMI_ANNUAL,
            --                                         [YEAR]
            --                                  FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
            --                                                 NAP.PERIOD_SID,
            --                                                 ACTUAL_SALES = ACTUAL_DISCOUNT_DOLLAR,
            --                                                 PPA_ACTUAL_SALES = NS.ACTUAL_SALES,
            --                                                 PPA_ACTUAL_UNITS = NS.ACTUAL_UNITS,
            --                                                 RS_MODEL_SID
            --                                          FROM   NM_ACTUAL_PPA NAP
            --                                                 INNER JOIN NM_ACTUAL_SALES NS
            --                                                         ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
            --                                                            AND NS.PERIOD_SID = NAP.PERIOD_SID
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
            --                                         FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
            --                                                           NPP.PERIOD_SID,
            --                                                           PROJECTION_SALES = PROJECTION_DISCOUNT_DOLLAR,
            --                                                           PPA_PROJECTION_SALES = NS.PROJECTION_SALES,
            --                                                           PPA_PROJECTION_UNITS = NS.PROJECTION_UNITS,
            --                                                           RS_MODEL_SID
            --                                                    FROM   NM_PPA_PROJECTION NPP
            --                                                           INNER JOIN NM_SALES_PROJECTION NS
            --                                                                   ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
            --                                                                      AND NS.PERIOD_SID = NPP.PERIOD_SID
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                                   AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
            --                                         INNER JOIN PERIOD P
            --                                                 ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
            --                                  GROUP  BY SEMI_ANNUAL,
            --                                            [YEAR]) PPA
            --                              ON PPA.SEMI_ANNUAL = SALES.SEMI_ANNUAL
            --                                 AND PPA.[YEAR] = SALES.[YEAR]
            --                ORDER  BY SALES.[YEAR],
            --                          SALES.[SEMI_ANNUAL]
            --            END
            --          ELSE
            --            BEGIN
            --                SELECT @PROJECTION_SID PROJECTION_ID,
            --                       GTS_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
            --                       GTS_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
            --                       GTS_UNITS = Isnull(GTS.UNITS, 0),
            --                       SALES.[YEAR],
            --                       CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
            --                       CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
            --                       CONTRACT_UNITS_ACTUALS = Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0),
            --                       CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
            --                       TOTAL_DISCOUNT = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                        + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
            --                       TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                  + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
            --                       TOTAL_DISCOUNT_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                                              + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
            --                       TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                                        + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
            --                       --TOTAL_DISCOUNT_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) +
            --                       --                              + COALESCE(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(PPA.PPA_ACTUAL_SALES, 0), 0) ) * 100,
            --                       --TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( COALESCE(DISC.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) +
            --                       --                                        + COALESCE(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(PPA.PPA_PROJECTION_SALES, 0), 0) ) * 100,
            --                       ----DISC.CONTRACT_DISCOUNT_ACTUALS,  SALES.CONTRACT_SALES_ACTUALS,    PPA.PPA_DISCOUNT_ACTUALS,  PPA.PPA_ACTUAL_SALES,   
            --                       EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
            --                       EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
            --                       PPA_DISCOUNT = Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0),
            --                       PPA_DISCOUNT_PROJECTED = Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),
            --                       PPA_DISCOUNT_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) * 100,
            --                       PPA_DISCOUNT_PROJECTED_PERCENTAGE = Isnull(PPA.PPA_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) * 100,
            --                       NET_SALES = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS, 0)
            --                                                                                 + Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0) ) ),
            --                       NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED, 0)
            --                                                                                           + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) )
            --                FROM   (SELECT GTS_SALES_ACTUALS = Sum(GTS_SALES_ACTUALS),
            --                               GTS_SALES_PROJECTED = Sum(GTS_SALES_PROJECTED),
            --                               Sum(GTS.UNITS) UNITS,
            --                               GTS.YEARLY
            --                        FROM   #TEMP_GTS_DETAILS GTS
            --                        --JOIN @ITEM_INFO ACT
            --                        --  ON GTS.ITEMID = ACT.ITEM_ID
            --                        GROUP  BY GTS.YEARLY) GTS
            --                       FULL JOIN (SELECT CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES),
            --                                         CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES),
            --                                         [YEAR],
            --                                         CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS),
            --                                         CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS)
            --                                  FROM   (SELECT PROJECTION_DETAILS_SID,
            --                                                 PERIOD_SID,
            --                                                 ACTUAL_SALES,
            --                                                 ACTUAL_UNITS
            --                                          FROM   NM_ACTUAL_SALES NAS
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
            --                                         FULL JOIN (SELECT PROJECTION_DETAILS_SID,
            --                                                           PERIOD_SID,
            --                                                           PROJECTION_SALES,
            --                                                           PROJECTION_UNITS
            --                                                    FROM   NM_SALES_PROJECTION NPS
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                         INNER JOIN PERIOD P
            --                                                 ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
            --                                  GROUP  BY [YEAR]) SALES
            --                              ON SALES.[YEAR] = GTS.YEARLY
            --                       LEFT JOIN (SELECT CONTRACT_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
            --                                         CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
            --                                         [YEAR]
            --                                  FROM   (SELECT PROJECTION_DETAILS_SID,
            --                                                 PERIOD_SID,
            --                                                 ACTUAL_SALES,
            --                                                 RS_MODEL_SID
            --                                          FROM   NM_ACTUAL_DISCOUNT NAD
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAD.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
            --                                                 AND EXISTS (SELECT 1
            --                                                             FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
            --                                                             WHERE  A.TOKEN = NAD.RS_MODEL_SID)) ACT
            --                                         FULL JOIN (SELECT PROJECTION_DETAILS_SID,
            --                                                           PERIOD_SID,
            --                                                           PROJECTION_SALES,
            --                                                           RS_MODEL_SID
            --                                                    FROM   NM_DISCOUNT_PROJECTION NDP
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)
            --                                                           AND EXISTS (SELECT 1
            --                                                                       FROM   [DBO].[Udf_splitstring](@DISCOUNT_ID, ',') A
            --                                                                       WHERE  A.TOKEN = NDP.RS_MODEL_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                                   AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
            --                                         INNER JOIN PERIOD P
            --                                                 ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
            --                                  GROUP  BY [YEAR]) DISC
            --                              ON DISC.[YEAR] = SALES.[YEAR]
            --                       LEFT JOIN (SELECT PPA_DISCOUNT_ACTUALS = Sum(ACTUAL_SALES),
            --                                         PPA_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES),
            --                                         PPA_PROJECTION_SALES = Sum(PPA_PROJECTION_SALES),
            --                                         PPA_PROJECTION_UNITS = Sum(PPA_PROJECTION_UNITS),
            --                                         PPA_ACTUAL_SALES = Sum(PPA_ACTUAL_SALES),
            --                                         PPA_ACTUAL_UNITS = Sum(PPA_ACTUAL_UNITS),
            --                                         [YEAR]
            --                                  FROM   (SELECT NAP.PROJECTION_DETAILS_SID,
            --                                                 NAP.PERIOD_SID,
            --                                                 ACTUAL_SALES = ACTUAL_DISCOUNT_DOLLAR,
            --                                                 PPA_ACTUAL_SALES = NS.ACTUAL_SALES,
            --                                                 PPA_ACTUAL_UNITS = NS.ACTUAL_UNITS,
            --                                                 RS_MODEL_SID
            --                                          FROM   NM_ACTUAL_PPA NAP
            --                                                 INNER JOIN NM_ACTUAL_SALES NS
            --                                                         ON NS.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
            --                                                            AND NS.PERIOD_SID = NAP.PERIOD_SID
            --                                          WHERE  EXISTS (SELECT 1
            --                                                         FROM   PROJECTION_DETAILS PD
            --                                                         WHERE  PD.PROJECTION_DETAILS_SID = NAP.PROJECTION_DETAILS_SID
            --                                                                AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) ACT
            --                                         FULL JOIN (SELECT NPP.PROJECTION_DETAILS_SID,
            --                                                           NPP.PERIOD_SID,
            --                                                           PROJECTION_SALES = PROJECTION_DISCOUNT_DOLLAR,
            --                                                           PPA_PROJECTION_SALES = NS.PROJECTION_SALES,
            --                                                           PPA_PROJECTION_UNITS = NS.PROJECTION_UNITS,
            --                                                           RS_MODEL_SID
            --                                                    FROM   NM_PPA_PROJECTION NPP
            --                                                           INNER JOIN NM_SALES_PROJECTION NS
            --                                                                   ON NS.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
            --                                                                      AND NS.PERIOD_SID = NPP.PERIOD_SID
            --                                                    WHERE  EXISTS (SELECT 1
            --                                                                   FROM   PROJECTION_DETAILS PD
            --                                                                   WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
            --                                                                          AND PD.PROJECTION_MASTER_SID = @PROJECTION_SID)) PROJ
            --                                                ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
            --                                                   AND ACT.PERIOD_SID = PROJ.PERIOD_SID
            --                                                   AND ACT.RS_MODEL_SID = PROJ.RS_MODEL_SID
            --                                         INNER JOIN PERIOD P
            --                                                 ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
            --                                  GROUP  BY [YEAR]) PPA
            --                              ON PPA.[YEAR] = SALES.[YEAR]
            --                ORDER  BY SALES.[YEAR]
            --            END
            --      END
            --END
 
END TRY

      BEGIN CATCH
          DECLARE @ERRORMESSAGE NVARCHAR(4000);
          DECLARE @ERRORSEVERITY INT;
          DECLARE @ERRORSTATE INT;
          DECLARE @ERRORNUMBER INT;
          DECLARE @ERRORPROCEDURE VARCHAR(200);
          DECLARE @ERRORLINE INT;

          EXEC Usperrorcollector

          SELECT @ERRORMESSAGE = Error_message(),
                 @ERRORSEVERITY = Error_severity(),
                 @ERRORSTATE = Error_state(),
                 @ERRORPROCEDURE = Error_procedure(),
                 @ERRORLINE = Error_line(),
                 @ERRORNUMBER = Error_number()

          RAISERROR ( @ERRORMESSAGE,-- MESSAGE TEXT.
                      @ERRORSEVERITY,-- SEVERITY.
                      @ERRORSTATE,-- STATE.
                      @ERRORPROCEDURE,-- PROCEDURE_NAME.
                      @ERRORNUMBER,-- ERRORNUMBER
                      @ERRORLINE -- ERRORLINE
          );
      END CATCH
  END 


