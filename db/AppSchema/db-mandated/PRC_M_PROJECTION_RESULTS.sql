IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_M_PROJECTION_RESULTS'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_M_PROJECTION_RESULTS]
  END

GO

CREATE PROCEDURE [dbo].[PRC_M_PROJECTION_RESULTS] (@PROJECTION_MASTER_SID VARCHAR(500),
                                                   @USER_ID               INT,
                                                   @SESSION_ID            VARCHAR(50),
                                                   @FREQUENCY             VARCHAR(50),
                                                   @SCREEN_NAME           VARCHAR(20),
                                                   @ACTUAL_START_FREQ     INT,
                                                   @ACTUAL_START_YEAR     INT,
                                                   @VIEW                  VARCHAR(20) = NULL)
AS
  BEGIN
      SET NOCOUNT ON;

      BEGIN TRY
          DECLARE @ACTUAL_START_PERIOD INT
          DECLARE @ACTUAL_END_PERIOD INT
          DECLARE @STARTFROM             DATE,
                  @PROJECTION_DATE       DATE,
                  @SP                    INT,
                  @SP_PROJ_SID           INT,
                  @TEMP_PROJ_SID         VARCHAR(500),
                  @START_PERIOD_SID      INT,
                  @END_PERIOD_SID        INT,
                  @ITEM_UOM              VARCHAR(50) = 'EACH' -- 'UN' TO 'EACH' GALUAT-46
                  ,
                  @PROJ_START_PERIOD_SID INT,
                  @START_DATE            DATETIME
		 DECLARE @CCP_HIERARCHY VARCHAR(200) = Concat('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
		 DECLARE @PRODUCT_FILE  VARCHAR(max)= Concat('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
		         @S_ACTUAL_TABLE  VARCHAR(max)= Concat('ST_M_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @S_PROJECTION_TABLE  VARCHAR(max)= Concat('ST_M_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @DISCOUNT_ACTUAL   VARCHAR(200)= Concat('ST_M_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @DISCOUNT_PROJECTION   VARCHAR(200)= Concat('ST_M_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @SUPP_ACTUAL   VARCHAR(200)= Concat('ST_M_SUPPLEMENTAL_DISC_ACTUALS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
				 @SUPP_PROJ   VARCHAR(200)= Concat('ST_M_SUPPLEMENTAL_DISC_PROJ_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
		 DECLARE @DSQL NVARCHAR(MAX)
          IF Object_id('TEMPDB..#TEMP_PROJECTION_MASTER') IS NOT NULL
            DROP TABLE #TEMP_PROJECTION_MASTER

          CREATE TABLE #TEMP_PROJECTION_MASTER
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
                 [PERIOD]                                     SMALLINT,
                 [YEAR]                                       INT,
                 EX_FACTORY_SALES_PROJECTED                   NUMERIC(22, 6),
                 DEMAND_SALES_PROJECTED                       NUMERIC(22, 6),
                 INVENTORY_WITHDRAWAL_SALES_PROJECTED         NUMERIC(22, 6),
                 EX_FACTORY_SALES_PROJECTED_PERCENT           NUMERIC(38, 15),
                 DEMAND_SALES_PROJECTED_PERCENT               NUMERIC(38, 15),
                 INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT NUMERIC(38, 15),
                 CONTRACT_SALES_PROJECTED                     NUMERIC(38, 15),
                 CONTRACT_UNITS_PROJECTED                     NUMERIC(38, 15),
                 MANDATED_DISCOUNT_PROJECTED                  NUMERIC(38, 15),
                 MANDATED_DISCOUNT_PROJECTED_PERCENTAGE       NUMERIC(38, 15),
                 SUPPLEMENTAL_DISCOUNT_PROJECTED              NUMERIC(38, 15),
                 SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE   NUMERIC(38, 15),
                 TOTAL_DISCOUNT_PROJECTED                     NUMERIC(38, 15),
                 TOTAL_DISCOUNT_PROJECTED_PERCENTAGE          NUMERIC(38, 15),
                 [% OF BUSINESS_PROJECTED]                    NUMERIC(38, 15),
                 TOTAL_MANDATED_RPU                           NUMERIC(22, 6),
                 TOTAL_SUPPLEMENTAL_RPU                       NUMERIC(22, 6),
                 TOTAL_RPU_PROJECTED                          NUMERIC(22, 6),
                 NET_SALES_PROJECTED                          NUMERIC(38, 15),
                 COGS_PROJECTED                               NUMERIC(22, 6),
                 NET_PROFIT_PROJECTED                         NUMERIC(22, 6)
              )

          SET @TEMP_PROJ_SID = Replace(@PROJECTION_MASTER_SID + ',', ',,', ',')

          WHILE Patindex('%,%', @TEMP_PROJ_SID) <> 0
            BEGIN
                SELECT @SP = Patindex('%,%', @TEMP_PROJ_SID)

                SELECT @SP_PROJ_SID = LEFT(@TEMP_PROJ_SID, @SP - 1)

                SELECT @TEMP_PROJ_SID = Stuff(@TEMP_PROJ_SID, 1, @SP, '')

                INSERT INTO #TEMP_PROJECTION_MASTER
                            (PROJECTION_MASTER_SID)
                SELECT @SP_PROJ_SID
            END

          DECLARE @FIRST_PROJ_SID INT

          SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID
          FROM   #TEMP_PROJECTION_MASTER PM
          WHERE  ID = 1

          SELECT TOP 1 @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0),
                       @START_DATE = Dateadd(dd, 1, Eomonth(FROM_DATE, -1)),
                       @PROJECTION_DATE = Dateadd(dd, 1, Eomonth(TO_DATE, -1))
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
		  ORDER BY PROJECTION_MASTER_SID

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

          IF Object_id('TEMPDB..#TEMP_PERIOD') IS NOT NULL
            DROP TABLE #TEMP_PERIOD

          CREATE TABLE #TEMP_PERIOD
            (
               PROJECTION_MASTER_SID INT,
               TO_DATE               DATETIME
            )

          INSERT INTO #TEMP_PERIOD
                      (PROJECTION_MASTER_SID,
                       TO_DATE)
          SELECT PM.PROJECTION_MASTER_SID,
                 TO_DATE
          FROM   #TEMP_PROJECTION_MASTER T
                 INNER JOIN PROJECTION_MASTER PM
                         ON T.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
          WHERE  ID <> 1

          IF Object_id('TEMPDB..#ITEM_INFO') IS NOT NULL
            DROP TABLE #ITEM_INFO

          CREATE TABLE #ITEM_INFO
            (
               CCP_DETAILS_SID INT,
               ITEM_ID                VARCHAR(50),
               NDC8                   VARCHAR(20),
               ITEM_MASTER_SID        INT
            )

       SET @DSQL=CONCAT(   'INSERT INTO #ITEM_INFO
                      (CCP_DETAILS_SID,
                       ITEM_ID,
                       NDC8,
                       ITEM_MASTER_SID)
          SELECT DISTINCT PD.CCP_DETAILS_SID,
                          IM.ITEM_ID,
                          IM.NDC8,
                          IM.ITEM_MASTER_SID
          FROM   ITEM_MASTER IM
                 INNER JOIN CCP_DETAILS CCP
                         ON CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                 INNER JOIN ',@CCP_HIERARCHY,' PD
                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
          ')
		
		  EXEC sp_execute @DSQL


          

          IF Object_id('TEMPDB..#TEMP_CCP') IS NOT NULL
            DROP TABLE #TEMP_CCP

          CREATE TABLE #TEMP_CCP
            (
               COMPANY_MASTER_SID     INT,
               CONTRACT_MASTER_SID    INT,
               ITEM_MASTER_SID        INT,
               CCP_DETAILS_SID INT PRIMARY KEY,
               PROJECTION_MASTER_SID  INT,
               BUSINESS_UNIT          INT,
               COMPANY                INT
            )

          INSERT INTO #TEMP_CCP
                      (COMPANY_MASTER_SID,
                       CONTRACT_MASTER_SID,
                       ITEM_MASTER_SID,
                       CCP_DETAILS_SID,
                       PROJECTION_MASTER_SID,
                       BUSINESS_UNIT,
                       COMPANY)
          SELECT CCP.COMPANY_MASTER_SID,
                 CCP.CONTRACT_MASTER_SID,
                 CCP.ITEM_MASTER_SID,
                 PD.CCP_DETAILS_SID,
                 PM.PROJECTION_MASTER_SID,
                 BUSINESS_UNIT,
                 PM.COMPANY_MASTER_SID AS COMPANY
          FROM   CCP_DETAILS CCP
                 INNER JOIN #ITEM_INFO PD
                         ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                 INNER JOIN PROJECTION_MASTER PM
                         ON  PM.PROJECTION_MASTER_SID=  @FIRST_PROJ_SID
       

          --------------------GAL-808------------------------------------------
          --SELECT @BUSINESS_UNIT = BUSINESS_UNIT,
          --       @COMPANY_ID = COMPANY
          --FROM   #TEMP_CCP

          ------------------------------------------------------------------------------------
          --DECLARE @FILE_VER AS TABLE
          --  (
          --     FILE_TYPE     VARCHAR(100),
          --     FORECAST_NAME VARCHAR(100),
          --     VERSION       VARCHAR(100)
          --  )

          --INSERT INTO @FILE_VER
          --            (FILE_TYPE,
          --             FORECAST_NAME,
          --             VERSION)
          --SELECT FILE_TYPE,
          --       FORECAST_NAME,
          --       VERSION
          --FROM   (SELECT FT.FORECAST_NAME,
          --               FT.[VERSION],
          --               FILE_MANAGEMENT_SID,
          --               HT.[DESCRIPTION]                        AS FILE_TYPE,
          --               Row_number()
          --                 OVER (
          --                   PARTITION BY FILE_TYPE
          --                   ORDER BY FILE_MANAGEMENT_SID DESC ) AS RN
          --        FROM   FILE_MANAGEMENT FT
          --               INNER JOIN HELPER_TABLE HT
          --                       ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
          --        WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, Getdate())
          --                 AND FT.FROM_PERIOD IS NOT NULL )
          --               AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, Getdate())
          --                      OR FT.TO_PERIOD IS NULL )
          --               AND HT.LIST_NAME = 'FILE_TYPE'
          --               AND HT.[DESCRIPTION] IN ( 'EX-FACTORY SALES', 'DEMAND', 'Inventory Withdrawal - Forecast Summary' )
          --               AND FT.BUSINESS_UNIT = @BUSINESS_UNIT
          --               AND FT.COMPANY = @COMPANY_ID) A
          --WHERE  RN = 1

          --SELECT @FORECAST_NAME = FORECAST_NAME,
          --       @FORECAST_VERSION = [VERSION]
          --FROM   @FILE_VER
          --WHERE  FILE_TYPE = 'EX-FACTORY SALES'

          --SELECT @FORECAST_NAME_INV = FORECAST_NAME,
          --       @FORECAST_VERSION_INV = [VERSION]
          --FROM   @FILE_VER
          --WHERE  FILE_TYPE = 'Inventory Withdrawal - Forecast Summary'

          --SELECT @FORECAST_NAME_DM = FORECAST_NAME,
          --       @FORECAST_VERSION_DM = [VERSION]
          --FROM   @FILE_VER
          --WHERE  FILE_TYPE = 'DEMAND'



          DECLARE @ITEMID [DBO].[UDT_ITEM]

          INSERT INTO @ITEMID
          SELECT IM.ITEM_MASTER_SID
          FROM   ITEM_MASTER IM
          WHERE  EXISTS (SELECT 1
                         FROM   #TEMP_CCP A
                         WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID)


          --------------------------------------------------ITEM_PRICING-----------------------------------------------
          IF Object_id('TEMPDB..#ITEM_PRICING') IS NOT NULL
            DROP TABLE #ITEM_PRICING

          SELECT ITEM_MASTER_SID,
             PERIOD_SID,
             PRICING_QUALIFIER,
             ITEM_PRICE
          INTO   #ITEM_PRICING
          FROM   [dbo].[Udf_item_pricing](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, @ITEM_UOM) -- @ITEM_UOM  'UN' TO 'EACH'  GALUTA-46
          -------------------------------------------------------END---------------------------------------
          IF ( @SCREEN_NAME = 'PROJECTION RESULTS' )
            BEGIN
                IF ( @FREQUENCY = 'MONTHLY' )
                  BEGIN
                      SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                      FROM   PERIOD
                      WHERE  MONTH = @ACTUAL_START_FREQ
                             AND YEAR = @ACTUAL_START_YEAR

                      SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))

                     SET @DSQL=CONCAT( 'SELECT ',@FIRST_PROJ_SID,' PROJECTION_ID,
                             ISNULL(GTS_SALES_ACTUALS, 0) AS EX_FACTORY_SALES_ACTUALS,
                             ISNULL(GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                             P.[MONTH],
                             P.[YEAR],
                             Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                             Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                             Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                             Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                             Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                             Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                             Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                             Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                             ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                     + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                           + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                             ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS_SALES_PROJECTED, 0), 0) ),
                             Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                             + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                             DEMAND_SALES_ACTUALS = Isnull(DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = Isnull(DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = Isnull(ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = Isnull(FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_MANDATED_RPU_ACTUALS = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_MANDATED_RPU_PROJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_SUPPLEMENTAL_RPU_ACTUALS = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_SUPPLEMENTAL_RPU_PROJECTED = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUALS = ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                            + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             COGS_ACTUALS = Isnull(SALES.COGS_ACTUALS, 0),
                             COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                               + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) - ( Isnull(SALES.COGS_ACTUALS, 0) ) ),
                             NET_PROFIT_PROJECTED = ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) )
                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = Sum(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												p.PERIOD_SID
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                              GROUP  BY p.PERIOD_SID) GTS
                                     
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                               Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                               COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID,
                                               Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                               Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                               COGS_ACTUALS = Sum(COGS_ACTUAL),
                                               COGS_PROJECTED = Sum(COGS_PROJECTED)
                                        FROM   (SELECT NAS.CCP_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       COGS_ACTUAL = ( Isnull(NAS.ACTUAL_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                FROM   ',@S_ACTUAL_TABLE,' NAS
                                                       
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON NAS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                 ) ACT
                                               FULL JOIN (SELECT NPS.CCP_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 COGS_PROJECTED = ( Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0) )
                                                          FROM   ',@S_PROJECTION_TABLE,' NPS
                                                                
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            
                                                                            AND NPS.PERIOD_SID BETWEEN Isnull(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SALES
                                    ON SALES.PERIOD_SID = GTS.PERIOD_SID
                             INNER JOIN PERIOD P
                                     ON P.PERIOD_SID = COALESCE(SALES.PERIOD_SID, GTS.PERIOD_SID)
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                               Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                               ACTUAL_RPU = Sum(ACTUAL_RPU),
                                               PROJECTED_RPU = Sum(PROJECTION_RPU),
                                               COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID
                                        FROM   (SELECT CCP_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   ',@DISCOUNT_ACTUAL,' NAS
                                                ) ACT
                                               FULL JOIN (SELECT CCP_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   ',@DISCOUNT_PROJECTION,' NPS
                                                          WHERE  NPS.PERIOD_SID BETWEEN Isnull(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) DISCOUNT
                                    ON DISCOUNT.PERIOD_SID = SALES.PERIOD_SID
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS ,
                                               Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                               ACTUAL_RPU = Sum(ACTUAL_RPU),
                                               PROJECTED_RPU = Sum(PROJECTION_RPU),
                                               COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID
                                        FROM   (SELECT CCP_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   ',@SUPP_ACTUAL,' NAS
                                              ) ACT
                                               FULL JOIN (SELECT CCP_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   ',@SUPP_PROJ,' NPS
                                                          WHERE   NPS.PERIOD_SID BETWEEN Isnull(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SUPPLEMENTAL
                                    ON SUPPLEMENTAL.PERIOD_SID = DISCOUNT.PERIOD_SID
                      --WHERE P.PERIOD_SID BETWEEN IsNull(@ACTUAL_START_PERIOD, 1)
                      --		AND @ACTUAL_END_PERIOD
                      ORDER  BY P.YEAR,
                                P.MONTH')
								
				EXEC sp_execute @DSQL
                  END
                ELSE IF ( @FREQUENCY = 'QUARTERLY' )
                  BEGIN
				
                      SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                      FROM   PERIOD
                      WHERE  QUARTER = @ACTUAL_START_FREQ
                             AND YEAR = @ACTUAL_START_YEAR

                      SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))

                       SET @DSQL=CONCAT( '  SELECT ',@FIRST_PROJ_SID ,' PROJECTION_ID,
                             ISNULL(GTS_SALES_ACTUALS, 0) AS EX_FACTORY_SALES_ACTUALS,
                             ISNULL(GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                             COALESCE(SALES.[QUARTER], GTS.QUARTERLY) [QUARTER],
                             COALESCE(SALES.[YEAR], GTS.YEARLY)       [YEAR],
                             Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                             Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                             Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                             Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                             Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                             Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                             Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                             Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                             ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                     + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                           + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                             ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             EX_FACTORY_SALES_ACTUALS_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ),
                             Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                             + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                             DEMAND_SALES_ACTUALS = ISNULL(DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = ISNULL(DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = ISNULL(ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = ISNULL(FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUALS_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_MANDATED_RPU_ACTUALS = ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_MANDATED_RPU_PROJECTED = ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_SUPPLEMENTAL_RPU_ACTUALS = ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_SUPPLEMENTAL_RPU_PROJECTED = ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUALS = ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                            + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             COGS_ACTUAL = ISNULL(SALES.COGS_ACTUAL, 0),
                             COGS_PROJECTED = ISNULL(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                               + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) - ( ISNULL(SALES.COGS_ACTUAL, 0) ) ),
                             NET_PROFIT_PROJECTED = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) )
                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = SUM(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = SUM(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = SUM(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = SUM(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = SUM(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = SUM(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												P.QUARTER AS QUARTERLY,
												P.YEAR AS YEARLY
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                              GROUP  BY [QUARTER],
                                                  [YEAR]) GTS
                                    
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                               Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                               [QUARTER],
                                               [YEAR],
                                               COGS_ACTUAL = SUM(COGS_ACTUAL),
                                               COGS_PROJECTED = SUM(COGS_PROJECTED),
                                               Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                               Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
                                        FROM   (SELECT NAS.CCP_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       COGS_ACTUAL = ( ISNULL(NAS.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) )
                                                FROM   ',@S_ACTUAL_TABLE,' NAS
                                                       
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON NAS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID) ACT
                                               FULL JOIN (SELECT NPS.CCP_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 COGS_PROJECTED = ( ISNULL(NPS.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) )
                                                          FROM   ',@S_PROJECTION_TABLE,' NPS
                                                                
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            
                                                                            AND NPS.PERIOD_SID BETWEEN ISNULL(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                    
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) SALES
                                    ON SALES.[QUARTER] = GTS.QUARTERLY
                                       AND SALES.[YEAR] = GTS.YEARLY
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                               Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                               ACTUAL_RPU = SUM(ACTUAL_RPU),
                                               PROJECTED_RPU = SUM(PROJECTION_RPU),
                                               QUARTER,
                                               YEAR
                                        FROM   (SELECT CCP_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   ',@DISCOUNT_ACTUAL,' NAS) ACT
                                               FULL JOIN (SELECT CCP_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   ',@DISCOUNT_PROJECTION,' NPS
                                                          WHERE  NPS.PERIOD_SID BETWEEN ISNULL(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) DISCOUNT
                                    ON DISCOUNT.[QUARTER] = SALES.[QUARTER]
                                       AND DISCOUNT.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS ,
                                               Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                               ACTUAL_RPU = SUM(ACTUAL_RPU),
                                               PROJECTED_RPU = SUM(PROJECTION_RPU),
                                               [QUARTER],
                                               [YEAR]
                                        FROM   (SELECT CCP_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   ',@SUPP_ACTUAL,' NAS) ACT
                                               FULL JOIN (SELECT CCP_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   ',@SUPP_PROJ,' NPS
                                                          WHERE   NPS.PERIOD_SID BETWEEN ISNULL(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) SUPPLEMENTAL
                                    ON SUPPLEMENTAL.[QUARTER] = SALES.[QUARTER]
                                       AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY),
                                COALESCE(SALES.[QUARTER], GTS.QUARTERLY)')
								
				EXEC sp_execute @DSQL
			
                  END
                ELSE IF ( @FREQUENCY = 'SEMI-ANNUALLY' )
                  BEGIN
                      SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                      FROM   PERIOD
                      WHERE  YEAR = @ACTUAL_START_YEAR

                      SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))

                       SET @DSQL=CONCAT( '  SELECT ',@FIRST_PROJ_SID ,' PROJECTION_ID,
                             EX_FACTORY_SALES_ACTUALS = ISNULL(GTS.GTS_SALES_ACTUALS, 0),
                             EX_FACTORY_SALES_PROJECTED = ISNULL(GTS.GTS_SALES_PROJECTED, 0),
                             COALESCE(SALES.[SEMI_ANNUAL], GTS.SEMI_ANNUALLY) [SEMI_ANNUAL],
                             COALESCE(SALES.[YEAR], GTS.YEARLY)       [YEAR],
                             Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                             Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                             Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                             Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                             Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                             Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                             Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                             Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                             ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                     + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                           + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                             ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             EX_FACTORY_SALES_ACTUALS_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ),
                             Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                             + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                             DEMAND_SALES_ACTUALS = ISNULL(DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = ISNULL(DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = ISNULL(ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = ISNULL(FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUALS_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_MANDATED_RPU_ACTUALS = ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_MANDATED_RPU_PROJECTED = ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_SUPPLEMENTAL_RPU_ACTUALS = ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_SUPPLEMENTAL_RPU_PROJECTED = ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUALS = ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                            + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             COGS_ACTUAL = ISNULL(SALES.COGS_ACTUAL, 0),
                             COGS_PROJECTED = ISNULL(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                               + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) - ( ISNULL(SALES.COGS_ACTUAL, 0) ) ),
                             NET_PROFIT_PROJECTED = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) )
                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = SUM(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = SUM(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = SUM(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = SUM(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = SUM(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = SUM(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												P.SEMI_ANNUAL SEMI_ANNUALLY ,
												P.YEAR  YEARLY
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                              GROUP  BY [SEMI_ANNUAL],
                                                  [YEAR]) GTS
                                    
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                               Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                               [SEMI_ANNUAL],
                                               [YEAR],
                                               COGS_ACTUAL = SUM(COGS_ACTUAL),
                                               COGS_PROJECTED = SUM(COGS_PROJECTED),
                                               Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                               Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
                                        FROM   (SELECT NAS.CCP_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       COGS_ACTUAL = ( ISNULL(NAS.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) )
                                                FROM   ',@S_ACTUAL_TABLE,' NAS
                                                       
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON NAS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID) ACT
                                               FULL JOIN (SELECT NPS.CCP_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 COGS_PROJECTED = ( ISNULL(NPS.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) )
                                                          FROM   ',@S_PROJECTION_TABLE,' NPS
                                                                
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            
                                                                            AND NPS.PERIOD_SID BETWEEN ISNULL(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                     
                                        GROUP  BY [SEMI_ANNUAL],
                                                  [YEAR]) SALES
                                    ON SALES.[SEMI_ANNUAL] = GTS.SEMI_ANNUALLY
                                       AND SALES.[YEAR] = GTS.YEARLY
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                               Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                               ACTUAL_RPU = SUM(ACTUAL_RPU),
                                               PROJECTED_RPU = SUM(PROJECTION_RPU),
                                               SEMI_ANNUAL,
                                               YEAR
                                        FROM   (SELECT CCP_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   ',@DISCOUNT_ACTUAL,' NAS) ACT
                                               FULL JOIN (SELECT CCP_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   ',@DISCOUNT_PROJECTION,' NPS
                                                          WHERE  NPS.PERIOD_SID BETWEEN ISNULL(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [SEMI_ANNUAL],
                                                  [YEAR]) DISCOUNT
                                    ON DISCOUNT.[SEMI_ANNUAL] = SALES.[SEMI_ANNUAL]
                                       AND DISCOUNT.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS ,
                                               Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                               ACTUAL_RPU = SUM(ACTUAL_RPU),
                                               PROJECTED_RPU = SUM(PROJECTION_RPU),
                                               [SEMI_ANNUAL],
                                               [YEAR]
                                        FROM   (SELECT CCP_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   ',@SUPP_ACTUAL,' NAS) ACT
                                               FULL JOIN (SELECT CCP_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   ',@SUPP_PROJ,' NPS
                                                          WHERE   NPS.PERIOD_SID BETWEEN ISNULL(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.ccp_details_sid = PROJ.ccp_details_sid
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [SEMI_ANNUAL],
                                                  [YEAR]) SUPPLEMENTAL
                                    ON SUPPLEMENTAL.[SEMI_ANNUAL] = SALES.[SEMI_ANNUAL]
                                       AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY),
                                COALESCE(SALES.[SEMI_ANNUAL], GTS.SEMI_ANNUALLY)')
							

				EXEC sp_execute @DSQL
			
                  END
                ELSE
                  BEGIN
                      SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                      FROM   PERIOD
                      WHERE  YEAR = @ACTUAL_START_YEAR

                      SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))

                                     SET @DSQL=CONCAT( '  SELECT ',@FIRST_PROJ_SID ,' PROJECTION_ID,
                             EX_FACTORY_SALES_ACTUALS = ISNULL(GTS.GTS_SALES_ACTUALS, 0),
                             EX_FACTORY_SALES_PROJECTED = ISNULL(GTS.GTS_SALES_PROJECTED, 0),
                            
                             COALESCE(SALES.[YEAR], GTS.YEARLY)       [YEAR],
                             Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                             Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                             Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                             Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                             Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                             Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                             Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                             Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                             ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                     + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                           + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                             ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             EX_FACTORY_SALES_ACTUALS_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100,
                             EX_FACTORY_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ),
                             Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                             + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                             DEMAND_SALES_ACTUALS = ISNULL(DEMAND_SALES_ACTUAL, 0),
                             DEMAND_SALES_PROJECTED = ISNULL(DEMAND_SALES_PROJECTED, 0),
                             INVENTORY_WITHDRAWAL_ACTUAL = ISNULL(ACT_AMOUNT_WITHDRAWN, 0),
                             INVENTORY_WITHDRAWAL_PROJECTED = ISNULL(FOR_AMOUNT_WITHDRAWN, 0),
                             DEMAND_SALES_ACTUALS_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                             DEMAND_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                             TOTAL_MANDATED_RPU_ACTUALS = ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_MANDATED_RPU_PROJECTED = ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_SUPPLEMENTAL_RPU_ACTUALS = ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_SUPPLEMENTAL_RPU_PROJECTED = ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             TOTAL_RPU_ACTUALS = ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                            + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                             TOTAL_RPU_PROJECTED = ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                             COGS_ACTUAL = ISNULL(SALES.COGS_ACTUAL, 0),
                             COGS_PROJECTED = ISNULL(SALES.COGS_PROJECTED, 0),
                             NET_PROFIT_ACTUAL = ( ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                               + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) - ( ISNULL(SALES.COGS_ACTUAL, 0) ) ),
                             NET_PROFIT_PROJECTED = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) )
                      FROM   (SELECT ACT_AMOUNT_WITHDRAWN = SUM(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = SUM(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = SUM(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = SUM(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = SUM(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = SUM(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												
												P.YEAR AS YEARLY 
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                              GROUP  by
                                                  [YEAR]) GTS
                                    
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                               Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                              
                                               [YEAR],
                                               COGS_ACTUAL = SUM(COGS_ACTUAL),
                                               COGS_PROJECTED = SUM(COGS_PROJECTED),
                                               Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                               Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
                                        FROM   (SELECT NAS.CCP_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       COGS_ACTUAL = ( ISNULL(NAS.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) )
                                                FROM   ',@S_ACTUAL_TABLE,' NAS
                                                       
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON NAS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID) ACT
                                               FULL JOIN (SELECT NPS.CCP_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 COGS_PROJECTED = ( ISNULL(NPS.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) )
                                                          FROM   ',@S_PROJECTION_TABLE,' NPS
                                                                
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            
                                                                            AND NPS.PERIOD_SID BETWEEN ISNULL(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                         
                                        GROUP  BY 
                                                  [YEAR]) SALES
                                    ON  SALES.[YEAR] = GTS.YEARLY
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                               Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                               ACTUAL_RPU = SUM(ACTUAL_RPU),
                                               PROJECTED_RPU = SUM(PROJECTION_RPU),
                                               
                                               YEAR
                                        FROM   (SELECT CCP_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   ',@DISCOUNT_ACTUAL,' NAS) ACT
                                               FULL JOIN (SELECT CCP_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   ',@DISCOUNT_PROJECTION,' NPS
                                                          WHERE  NPS.PERIOD_SID BETWEEN ISNULL(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.CCP_DETAILS_SID = PROJ.CCP_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY 
                                                  [YEAR]) DISCOUNT
                                    ON  DISCOUNT.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS ,
                                               Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                               ACTUAL_RPU = SUM(ACTUAL_RPU),
                                               PROJECTED_RPU = SUM(PROJECTION_RPU),
                                               
                                               [YEAR]
                                        FROM   (SELECT CCP_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   ',@SUPP_ACTUAL,' NAS) ACT
                                               FULL JOIN (SELECT CCP_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   ',@SUPP_PROJ,' NPS
                                                          WHERE   NPS.PERIOD_SID BETWEEN ISNULL(',@PROJ_START_PERIOD_SID,',', isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,') PROJ
                                                      ON ACT.ccp_details_sid = PROJ.ccp_details_sid
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY 
                                                  [YEAR]) SUPPLEMENTAL
                                    ON  SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY)
                                ')

						
				EXEC sp_execute @DSQL
                  END
            END
          ELSE
	
            BEGIN
                IF ( @VIEW = 'PIVOT' )
                  BEGIN
                      IF ( @FREQUENCY = 'MONTHLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  MONTH = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))

                            SET @DSQL=CONCAT('  INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
                                         [PERIOD],
                                         [YEAR],
                                         EX_FACTORY_SALES_PROJECTED,
                                         DEMAND_SALES_PROJECTED,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                         EX_FACTORY_SALES_PROJECTED_PERCENT,
                                         DEMAND_SALES_PROJECTED_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                         CONTRACT_SALES_PROJECTED,
                                         CONTRACT_UNITS_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_MANDATED_RPU,
                                         TOTAL_SUPPLEMENTAL_RPU,
                                         TOTAL_RPU_PROJECTED,
                                         NET_SALES_PROJECTED,
                                         COGS_PROJECTED,
                                         NET_PROFIT_PROJECTED)
                            SELECT ',@FIRST_PROJ_SID,' PROJECTION_ID,
                                   d.[MONTH],
                                   d.[YEAR],
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND_SALES_PROJECTED, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(FOR_AMOUNT_WITHDRAWN, 0),
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   TOTAL_MANDATED_RPU_PROJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_SUPPLEMENTAL_RPU = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_PROJECTED = ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) )
                            FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = Sum(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												p.PERIOD_SID
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                              GROUP  BY p.PERIOD_SID) GTS
                                           
                                   INNER JOIN PERIOD D
                                           ON D.PERIOD_SID =GTS.PERIOD_SID
                                   RIGHT JOIN (SELECT Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                      PERIOD_SID = COALESCE(NPS.PERIOD_SID, U.PERIOD_SID),
                                                      Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                      --COGS_PROJECTED = SUM(PROJECTION_UNITS*U.ITEM_PRICE)
                                                      COGS_PROJECTED = ( Isnull(Sum(NPS.PROJECTION_UNITS), 0) * Isnull(Avg(NULLIF(U.ITEM_PRICE, 0)), 0) ) --ISNULL(AVG(U.ITEM_PRICE), 0) )
                                               FROM   ',@S_PROJECTION_TABLE,' NPS
                                                      
                                                      INNER JOIN CCP_DETAILS CCP
                                                              ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                      INNER JOIN #ITEM_PRICING U
                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                 AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                 
                                               GROUP  BY COALESCE(NPS.PERIOD_SID, U.PERIOD_SID) -- NPS.PERIOD_SID
                                              ) SALES
                                           ON SALES.PERIOD_SID = GTS.PERIOD_SID
                                   INNER JOIN PERIOD P
                                           ON P.PERIOD_SID = COALESCE(SALES.PERIOD_SID, GTS.PERIOD_SID)
                                              AND P.PERIOD_SID BETWEEN COALESCE(',@PROJ_START_PERIOD_SID,', 1) AND ',@ACTUAL_END_PERIOD,'
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     PERIOD_SID,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU)
                                              FROM   ',@DISCOUNT_PROJECTION,' NDP
                                              
                                              GROUP  BY PERIOD_SID) DISCOUNT
                                          ON DISCOUNT.PERIOD_SID = SALES.PERIOD_SID
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     PERIOD_SID
                                              FROM   ',@SUPP_PROJ,' NPP
                                              GROUP  BY PERIOD_SID) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.PERIOD_SID = SALES.PERIOD_SID
                            WHERE  d.PERIOD_SID >= ',@ACTUAL_START_PERIOD,'
                            ORDER  BY d.[MONTH],
                                      d.[YEAR]')
									  EXEC sp_execute @DSQL
                        END
                      ELSE IF ( @FREQUENCY = 'QUARTERLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  QUARTER = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@ACTUAL_START_PERIOD, @PROJ_START_PERIOD_SID)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))

                             SET @DSQL=CONCAT(' INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
                                         [PERIOD],
                                         [YEAR],
                                         EX_FACTORY_SALES_PROJECTED,
                                         DEMAND_SALES_PROJECTED,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                         EX_FACTORY_SALES_PROJECTED_PERCENT,
                                         DEMAND_SALES_PROJECTED_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                         CONTRACT_SALES_PROJECTED,
                                         CONTRACT_UNITS_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_MANDATED_RPU,
                                         TOTAL_SUPPLEMENTAL_RPU,
                                         TOTAL_RPU_PROJECTED,
                                         NET_SALES_PROJECTED,
                                         COGS_PROJECTED,
                                         NET_PROFIT_PROJECTED)
                            SELECT ',@FIRST_PROJ_SID,' PROJECTION_ID,
                                   COALESCE(GTS.QUARTERLY, 0),
                                   COALESCE(GTS.YEARLY, 0),
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND_SALES_PROJECTED, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(FOR_AMOUNT_WITHDRAWN, 0),
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   TOTAL_MANDATED_RPU_PROJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_SUPPLEMENTAL_RPU = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_PROJECTED = ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) )
                            FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = Sum(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												p.QUARTER AS QUARTERLY,
												P.YEAR AS YEARLY
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                                               GROUP  BY QUARTER,
                                                         YEAR) GTS
                                          
                                   RIGHT JOIN (SELECT Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                      [QUARTER],
                                                      [YEAR],
                                                      Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                      COGS_PROJECTED =  Isnull(Sum(NPS.PROJECTION_UNITS), 0) * Isnull(Avg(U.ITEM_PRICE), 0) 
                                               FROM   ',@S_PROJECTION_TABLE,' NPS
                                                     
                                                      INNER JOIN CCP_DETAILS CCP
                                                              ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                      INNER JOIN #ITEM_PRICING U
                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                 AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                
                                                      INNER JOIN PERIOD P
                                                              ON P.PERIOD_SID = NPS.PERIOD_SID
                                               WHERE  P.PERIOD_SID BETWEEN Isnull(',@PROJ_START_PERIOD_SID,', ',@ACTUAL_START_PERIOD,') AND ',@ACTUAL_END_PERIOD,'
                                               GROUP  BY [QUARTER],
                                                         [YEAR]) SALES
                                           ON SALES.[QUARTER] = GTS.QUARTERLY
                                              AND SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     [QUARTER],
                                                     [YEAR]
                                              FROM   ',@DISCOUNT_PROJECTION,' NDP
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              
                                              GROUP  BY [QUARTER],
                                                        [YEAR]) DISCOUNT
                                          ON DISCOUNT.[QUARTER] = SALES.[QUARTER]
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     [QUARTER],
                                                     [YEAR]
                                              FROM   ',@SUPP_PROJ,' NPP
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPP.PERIOD_SID
                                             
                                              GROUP  BY [QUARTER],
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.[QUARTER] = SALES.[QUARTER]
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                            ORDER  BY SALES.[QUARTER],
                                      SALES.[YEAR]')
							
							
									  EXEC sp_execute @DSQL
                        END
                      ELSE IF ( @FREQUENCY = 'SEMI-ANNUALLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  SEMI_ANNUAL = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@ACTUAL_START_PERIOD, @PROJ_START_PERIOD_SID)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))

                               SET @DSQL=CONCAT(' INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
                                         [PERIOD],
                                         [YEAR],
                                         EX_FACTORY_SALES_PROJECTED,
                                         DEMAND_SALES_PROJECTED,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                         EX_FACTORY_SALES_PROJECTED_PERCENT,
                                         DEMAND_SALES_PROJECTED_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                         CONTRACT_SALES_PROJECTED,
                                         CONTRACT_UNITS_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_MANDATED_RPU,
                                         TOTAL_SUPPLEMENTAL_RPU,
                                         TOTAL_RPU_PROJECTED,
                                         NET_SALES_PROJECTED,
                                         COGS_PROJECTED,
                                         NET_PROFIT_PROJECTED)
                            SELECT ',@FIRST_PROJ_SID,'  PROJECTION_ID,
                                   COALESCE(GTS.SEMI_ANNUAL, 0),
                                   COALESCE(GTS.YEAR, 0),
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND_SALES_PROJECTED, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(FOR_AMOUNT_WITHDRAWN, 0),
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   TOTAL_MANDATED_RPU_PROJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_SUPPLEMENTAL_RPU = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_PROJECTED = ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) )
                            FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = Sum(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												p.SEMI_ANNUAL,
												P.YEAR 
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                                               GROUP  BY SEMI_ANNUAL,
                                                         YEAR) GTS
                                          
                                   RIGHT JOIN (SELECT Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                      [SEMI_ANNUAL],
                                                      [YEAR],
                                                      Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                      COGS_PROJECTED = ( Isnull(Sum(NPS.PROJECTION_UNITS), 0) * Isnull(Avg(U.ITEM_PRICE), 0) )
                                               FROM   ',@S_PROJECTION_TABLE,' NPS
                                                     
                                                      INNER JOIN CCP_DETAILS CCP
                                                              ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                      INNER JOIN #ITEM_PRICING U
                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                 AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                
                                                      INNER JOIN PERIOD P
                                                              ON P.PERIOD_SID = NPS.PERIOD_SID
                                               WHERE  P.PERIOD_SID BETWEEN Isnull(',@PROJ_START_PERIOD_SID,', ',@ACTUAL_START_PERIOD,') AND ',@ACTUAL_END_PERIOD,'
                                               GROUP  BY [SEMI_ANNUAL],
                                                         [YEAR]) SALES
                                           ON SALES.[SEMI_ANNUAL] = GTS.SEMI_ANNUAL
                                              AND SALES.[YEAR] = GTS.YEAR
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     [SEMI_ANNUAL],
                                                     [YEAR]
                                              FROM   ',@DISCOUNT_PROJECTION,' NDP
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              
                                              GROUP  BY [SEMI_ANNUAL],
                                                        [YEAR]) DISCOUNT
                                          ON DISCOUNT.[SEMI_ANNUAL] = SALES.[SEMI_ANNUAL]
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     [SEMI_ANNUAL],
                                                     [YEAR]
                                              FROM   ',@SUPP_PROJ,' NPP
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPP.PERIOD_SID
                                             
                                              GROUP  BY [SEMI_ANNUAL],
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.[SEMI_ANNUAL] = SALES.[SEMI_ANNUAL]
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                            ORDER  BY SALES.[SEMI_ANNUAL],
                                      SALES.[YEAR]')
									
									  EXEC sp_execute @DSQL
                        END
                      ELSE
                        BEGIN
                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))

                              SET @DSQL=CONCAT(' INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
                                        
                                         [YEAR],
                                         EX_FACTORY_SALES_PROJECTED,
                                         DEMAND_SALES_PROJECTED,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                         EX_FACTORY_SALES_PROJECTED_PERCENT,
                                         DEMAND_SALES_PROJECTED_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                         CONTRACT_SALES_PROJECTED,
                                         CONTRACT_UNITS_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_MANDATED_RPU,
                                         TOTAL_SUPPLEMENTAL_RPU,
                                         TOTAL_RPU_PROJECTED,
                                         NET_SALES_PROJECTED,
                                         COGS_PROJECTED,
                                         NET_PROFIT_PROJECTED)
                            SELECT ',@FIRST_PROJ_SID,'  PROJECTION_ID,
                                  
                                   COALESCE(GTS.YEAR, 0),
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND_SALES_PROJECTED, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(FOR_AMOUNT_WITHDRAWN, 0),
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) )  / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   TOTAL_MANDATED_RPU_PROJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_SUPPLEMENTAL_RPU = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_PROJECTED = ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) )
                            FROM   (SELECT ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = Sum(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												
												P.YEAR 
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                                               GROUP  BY 
                                                         YEAR) GTS
                                          
                                   RIGHT JOIN (SELECT Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                      
                                                      [YEAR],
                                                      Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                      COGS_PROJECTED = ( Isnull(Sum(NPS.PROJECTION_UNITS), 0) * Isnull(Avg(U.ITEM_PRICE), 0) )
                                               FROM   ',@S_PROJECTION_TABLE,' NPS
                                                     
                                                      INNER JOIN CCP_DETAILS CCP
                                                              ON NPS.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                      INNER JOIN #ITEM_PRICING U
                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                 AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                
                                                      INNER JOIN PERIOD P
                                                              ON P.PERIOD_SID = NPS.PERIOD_SID
                                               WHERE  P.PERIOD_SID BETWEEN Isnull(',@PROJ_START_PERIOD_SID,', ',isnull(@ACTUAL_START_PERIOD,0),') AND ',@ACTUAL_END_PERIOD,'
                                               GROUP  BY
                                                         [YEAR]) SALES
                                           ON SALES.[YEAR] = GTS.YEAR
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     
                                                     [YEAR]
                                              FROM   ',@DISCOUNT_PROJECTION,' NDP
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              
                                              GROUP  BY 
                                                        [YEAR]) DISCOUNT
                                         on
                                              DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     
                                                     [YEAR]
                                              FROM   ',@SUPP_PROJ,' NPP
                                                     INNER JOIN PERIOD P
                                                     ON P.PERIOD_SID = NPP.PERIOD_SID
													 AND  P.YEAR>=',@ACTUAL_START_YEAR,'
                                             
                                              GROUP  by
                                                        [YEAR]) SUPPLEMENTAL
                                          ON  SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                            ORDER  BY 
                                      SALES.[YEAR]')
									
									  EXEC sp_execute @DSQL
									 
                        END

                      IF ( @FREQUENCY = 'MONTHLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  MONTH = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))
												  --CHANGE SHOULD START BY TK
                       set @dsql=CONCAT(' INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
                                         [PERIOD],
                                         [YEAR],
                                         EX_FACTORY_SALES_PROJECTED,
                                         DEMAND_SALES_PROJECTED,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                         EX_FACTORY_SALES_PROJECTED_PERCENT,
                                         DEMAND_SALES_PROJECTED_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                         CONTRACT_SALES_PROJECTED,
                                         CONTRACT_UNITS_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_MANDATED_RPU,
                                         TOTAL_SUPPLEMENTAL_RPU,
                                         TOTAL_RPU_PROJECTED,
                                         NET_SALES_PROJECTED,
                                         COGS_PROJECTED,
                                         NET_PROFIT_PROJECTED)
                            SELECT COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
                                   P.[MONTH],
                                   P.[YEAR],
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND_SALES_PROJECTED, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(FOR_AMOUNT_WITHDRAWN, 0),
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   TOTAL_MANDATED_RPU_PROJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_SUPPLEMENTAL_RPU = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_PROJECTED = ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) )
                            FROM   (SELECT ACT_AMOUNT_WITHDRAWN = SUM(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = SUM(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = SUM(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = SUM(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = SUM(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = SUM(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												projection_master_sid,
												P.PERIOD_SID 
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                                      CROSS JOIN (SELECT PM.PROJECTION_MASTER_SID
                                                                  FROM   #TEMP_PROJECTION_MASTER PM
                                                                  WHERE  ID <> 1) ACT
																  
																  
									 GROUP  BY 
                                                         P.period_sid,
														 P.YEAR,
														 act.projection_master_sid
														 
																					  
																 
                                           ) GTS
                                      
                                   RIGHT JOIN (SELECT PROJECTION_MASTER_SID,
                                                      Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                      NPS.PERIOD_SID,
                                                      Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                      COGS_PROJECTED = ( Isnull(Sum(NPS.PROJECTION_UNITS), 0) * Isnull(Avg(U.ITEM_PRICE), 0) )
                                               FROM   M_SALES_PROJECTION NPS
                                                      INNER JOIN PROJECTION_DETAILS PD
                                                              ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                      INNER JOIN CCP_DETAILS CCP
                                                              ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                      INNER JOIN #ITEM_PRICING U
                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                 AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                 AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                  FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                  WHERE  ID <> 1)
                                               GROUP  BY PROJECTION_MASTER_SID,
                                                         NPS.PERIOD_SID) SALES
                                           ON SALES.PERIOD_SID = GTS.PERIOD_SID
                                   INNER JOIN PERIOD P
                                           ON P.PERIOD_SID = SALES.PERIOD_SID
                                              AND P.PERIOD_SID BETWEEN COALESCE(',@PROJ_START_PERIOD_SID,',', @ACTUAL_START_PERIOD,') AND ',@ACTUAL_END_PERIOD,'
                                   --and  PERIOD_DATE= IsNull(@ACTUAL_START_PERIOD, 1)
                                   LEFT JOIN (SELECT PROJECTION_MASTER_SID,
                                                     Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     PERIOD_SID
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                AND NDP.SAVE_FLAG = 1
                                                                AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1)
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        PERIOD_SID) DISCOUNT
                                          ON DISCOUNT.PERIOD_SID = P.PERIOD_SID
                                             AND DISCOUNT.PROJECTION_MASTER_SID = SALES.PROJECTION_MASTER_SID
                                   LEFT JOIN (SELECT PROJECTION_MASTER_SID,
                                                     Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     PERIOD_SID
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1)
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        PERIOD_SID) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.PERIOD_SID = SALES.PERIOD_SID
                                             AND SALES.PROJECTION_MASTER_SID = SUPPLEMENTAL.PROJECTION_MASTER_SID
                            ORDER  BY SALES.PERIOD_SID')
							EXEC sp_execute @DSQL
                        END
                      ELSE IF ( @FREQUENCY = 'QUARTERLY' )
                        BEGIN
			
					
                            SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  QUARTER = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@ACTUAL_START_PERIOD, @PROJ_START_PERIOD_SID)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))

                        SET @DSQL=CONCAT('    INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
                                         [PERIOD],
                                         [YEAR],
                                         EX_FACTORY_SALES_PROJECTED,
                                         DEMAND_SALES_PROJECTED,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                         EX_FACTORY_SALES_PROJECTED_PERCENT,
                                         DEMAND_SALES_PROJECTED_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                         CONTRACT_SALES_PROJECTED,
                                         CONTRACT_UNITS_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_MANDATED_RPU,
                                         TOTAL_SUPPLEMENTAL_RPU,
                                         TOTAL_RPU_PROJECTED,
                                         NET_SALES_PROJECTED,
                                         COGS_PROJECTED,
                                         NET_PROFIT_PROJECTED)
                            SELECT COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
                                   COALESCE(GTS.QUARTERLY, SALES.[QUARTER]),
                                   COALESCE(GTS.YEARLY, SALES.[YEAR]),
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND_SALES_PROJECTED, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(FOR_AMOUNT_WITHDRAWN, 0),
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   TOTAL_MANDATED_RPU_PROJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_SUPPLEMENTAL_RPU = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_PROJECTED = ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) )
                            FROM   (SELECT ACT_AMOUNT_WITHDRAWN = SUM(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = SUM(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = SUM(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = SUM(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = SUM(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = SUM(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												act.projection_master_sid,
												P.QUARTER AS QUARTERLY ,
												P.YEAR AS YEARLY
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                                              
                                                      CROSS JOIN (SELECT PM.PROJECTION_MASTER_SID
                                                                  FROM   #TEMP_PROJECTION_MASTER PM
                                                                  WHERE  ID <> 1) ACT
																  
																  
									 GROUP  BY 
                                                         P.QUARTER,
														 P.YEAR,
														 act.projection_master_sid
														 
																					  
																  ) GTS
                                           
                                   LEFT JOIN (SELECT PROJECTION_MASTER_SID,
                                                     Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                     [QUARTER],
                                                     [YEAR],
                                                     Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                     COGS_PROJECTED = ( Isnull(Sum(NPS.PROJECTION_UNITS), 0) * Isnull(Avg(U.ITEM_PRICE), 0) )
                                              FROM   M_SALES_PROJECTION NPS
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                     INNER JOIN CCP_DETAILS CCP
                                                             ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                     INNER JOIN #ITEM_PRICING U
                                                             ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPS.PERIOD_SID
                                              WHERE  P.PERIOD_SID BETWEEN COALESCE(',@PROJ_START_PERIOD_SID,',', @ACTUAL_START_PERIOD,') AND ',@ACTUAL_END_PERIOD,'
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [QUARTER],
                                                        [YEAR]) SALES
                                          ON SALES.[QUARTER] = GTS.QUARTERLY
                                             AND SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     [QUARTER],
                                                     [YEAR],
                                                     PD.PROJECTION_MASTER_SID
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                AND NDP.SAVE_FLAG = 1
                                                                AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [QUARTER],
                                                        [YEAR]) DISCOUNT
                                          ON SALES.PROJECTION_MASTER_SID = DISCOUNT.PROJECTION_MASTER_SID
                                             AND DISCOUNT.[QUARTER] = SALES.[QUARTER]
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     [QUARTER],
                                                     [YEAR],
                                                     PROJECTION_MASTER_SID
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [QUARTER],
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SALES.PROJECTION_MASTER_SID = SUPPLEMENTAL.PROJECTION_MASTER_SID
                                             AND SUPPLEMENTAL.[QUARTER] = SALES.[QUARTER]
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]')
								
											 EXEC sp_execute @DSQL
									
                        END
                      ELSE IF ( @FREQUENCY = 'SEMI-ANNUALLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  SEMI_ANNUAL = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@ACTUAL_START_PERIOD, @PROJ_START_PERIOD_SID)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))

                           SET @DSQL=CONCAT('  INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
                                         [PERIOD],
                                         [YEAR],
                                         EX_FACTORY_SALES_PROJECTED,
                                         DEMAND_SALES_PROJECTED,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                         EX_FACTORY_SALES_PROJECTED_PERCENT,
                                         DEMAND_SALES_PROJECTED_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                         CONTRACT_SALES_PROJECTED,
                                         CONTRACT_UNITS_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_MANDATED_RPU,
                                         TOTAL_SUPPLEMENTAL_RPU,
                                         TOTAL_RPU_PROJECTED,
                                         NET_SALES_PROJECTED,
                                         COGS_PROJECTED,
                                         NET_PROFIT_PROJECTED)
                            SELECT COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
                                   COALESCE(GTS.SEMI_ANNUAL, SALES.SEMI_ANNUAL),
                                   COALESCE(GTS.YEARLY, SALES.[YEAR]),
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND_SALES_PROJECTED, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(FOR_AMOUNT_WITHDRAWN, 0),
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   TOTAL_MANDATED_RPU_PROJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_SUPPLEMENTAL_RPU = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_PROJECTED = ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) )
                            FROM   (SELECT ACT_AMOUNT_WITHDRAWN = SUM(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = SUM(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = SUM(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = SUM(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = SUM(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = SUM(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												projection_master_sid,
												p.semi_annual,
												P.YEAR AS YEARLY 
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                                               CROSS JOIN (SELECT PM.PROJECTION_MASTER_SID
                                                                  FROM   #TEMP_PROJECTION_MASTER PM
                                                                  WHERE  ID <> 1) ACT
																  
																  
									 GROUP  BY 
                                                        p.semi_annual,
														 P.YEAR,
														 act.projection_master_sid
														 
																					  
																  ) GTS
                                           
                                          
                                   LEFT JOIN (SELECT PROJECTION_MASTER_SID,
                                                     Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                     SEMI_ANNUAL,
                                                     [YEAR],
                                                     Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                     COGS_PROJECTED = ( Isnull(Sum(NPS.PROJECTION_UNITS), 0) * Isnull(Avg(U.ITEM_PRICE), 0) )
                                              FROM   M_SALES_PROJECTION NPS
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                     INNER JOIN CCP_DETAILS CCP
                                                             ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                     INNER JOIN #ITEM_PRICING U
                                                             ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPS.PERIOD_SID
                                              WHERE  P.PERIOD_SID BETWEEN COALESCE(',@PROJ_START_PERIOD_SID,',', @ACTUAL_START_PERIOD,') AND ',@ACTUAL_END_PERIOD,'
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        SEMI_ANNUAL,
                                                        [YEAR]) SALES
                                          ON SALES.SEMI_ANNUAL = GTS.SEMI_ANNUAL
                                             AND SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     SEMI_ANNUAL,
                                                     [YEAR],
                                                     PROJECTION_MASTER_SID
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                AND NDP.SAVE_FLAG = 1
                                                                AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        SEMI_ANNUAL,
                                                        [YEAR]) DISCOUNT
                                          ON SALES.PROJECTION_MASTER_SID = DISCOUNT.PROJECTION_MASTER_SID
                                             AND DISCOUNT.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     SEMI_ANNUAL,
                                                     [YEAR],
                                                     PROJECTION_MASTER_SID
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        SEMI_ANNUAL,
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SALES.PROJECTION_MASTER_SID = SUPPLEMENTAL.PROJECTION_MASTER_SID
                                             AND SUPPLEMENTAL.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]')
										
										EXEC sp_execute @DSQL
									
                        END
                      ELSE
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_YEAR=COALESCE(@ACTUAL_START_YEAR, (SELECT year
                                                                                 FROM   period
                                                                                 WHERE  period_sid = @PROJ_START_PERIOD_SID))

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P
                      WHERE  EXISTS (SELECT Dateadd(dd, 1, Eomonth(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER PM
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND P.PERIOD_DATE=Dateadd(dd, 1, Eomonth(PM.TO_DATE, -1)))

                            SET @DSQL=CONCAT('   INSERT INTO #PIVOT_RESULT
                                        (PROJECTION_ID,
                                         [YEAR],
                                         EX_FACTORY_SALES_PROJECTED,
                                         DEMAND_SALES_PROJECTED,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                         EX_FACTORY_SALES_PROJECTED_PERCENT,
                                         DEMAND_SALES_PROJECTED_PERCENT,
                                         INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                         CONTRACT_SALES_PROJECTED,
                                         CONTRACT_UNITS_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                         MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                         SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_DISCOUNT_PROJECTED,
                                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                         TOTAL_MANDATED_RPU,
                                         TOTAL_SUPPLEMENTAL_RPU,
                                         TOTAL_RPU_PROJECTED,
                                         NET_SALES_PROJECTED,
                                         COGS_PROJECTED,
                                         NET_PROFIT_PROJECTED)
                            SELECT COALESCE(GTS.PROJECTION_MASTER_SID, SALES.PROJECTION_MASTER_SID) PROJECTION_ID,
                                   COALESCE(gts.[YEARly], SALES.[YEAR])                             AS YEARS,
                                   EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                                   DEMAND_SALES_PROJECTED = Isnull(DEMAND_SALES_PROJECTED, 0),
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(FOR_AMOUNT_WITHDRAWN, 0),
                                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                                   DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   TOTAL_MANDATED_RPU_PROJECTED = ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_SUPPLEMENTAL_RPU = ( Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   TOTAL_RPU_PROJECTED = ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                                   NET_PROFIT_PROJECTED = ( Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( Isnull(SALES.COGS_PROJECTED, 0) ) )
                            FROM   (SELECT ACT_AMOUNT_WITHDRAWN = SUM(INVENTORY_ACTUAL_SALES),
                                     ACT_UNITS_WITHDRAWN = SUM(INVENTORY_ACTUAL_UNITS),
                                     FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                     FOR_UNITS_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_UNITS,INVENTORY_ACTUAL_UNITS)),

									 DEMAND_SALES_ACTUAL = SUM(DEMAND_ACTUAL_SALES),
                                     ACT_GROSS_UNITS = SUM(DEMAND_ACTUAL_UNITS),
                                     DEMAND_SALES_PROJECTED = SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                     FOR_GROSS_UNITS = SUM(COALESCE(DEMAND_FORECAST_UNITS,DEMAND_ACTUAL_UNITS)),

												GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES),
                                                GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                                UNITS = SUM(COALESCE(EXFACTORY_FORECAST_UNITS,EXFACTORY_ACTUAL_UNITS)),
												projection_master_sid,
											
												P.YEAR AS YEARLY 
                              FROM   ',@PRODUCT_FILE,' PF JOIN PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID 
                                             CROSS JOIN (SELECT PM.PROJECTION_MASTER_SID
                                                                  FROM   #TEMP_PROJECTION_MASTER PM
                                                                  WHERE  ID <> 1) ACT
																  
																  
									 GROUP  BY 
                                                       
														 P.YEAR,
														 act.projection_master_sid
														 
																					  
																  ) GTS
                                           
                                         
                                   LEFT JOIN (SELECT PROJECTION_MASTER_SID,
                                                     Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                     [YEAR],
                                                     Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                     COGS_PROJECTED = ( Isnull(Sum(NPS.PROJECTION_UNITS), 0) * Isnull(Avg(U.ITEM_PRICE), 0) )
                                              FROM   M_SALES_PROJECTION NPS
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                     INNER JOIN CCP_DETAILS CCP
                                                             ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                     INNER JOIN #ITEM_PRICING U
                                                             ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPS.PERIOD_SID
                                              WHERE  P.PERIOD_SID  BETWEEN COALESCE(',@PROJ_START_PERIOD_SID,',', @ACTUAL_START_PERIOD,') AND ',@ACTUAL_END_PERIOD,'
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [YEAR]) SALES
                                          ON SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     [YEAR],
                                                     PROJECTION_MASTER_SID
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                AND NDP.SAVE_FLAG = 1
                                                                AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [YEAR]) DISCOUNT
                                          ON SALES.PROJECTION_MASTER_SID = DISCOUNT.PROJECTION_MASTER_SID
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     PROJECTED_RPU = Sum(PROJECTION_RPU),
                                                     [YEAR],
                                                     PROJECTION_MASTER_SID
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                AND PD.PROJECTION_MASTER_SID IN (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SALES.PROJECTION_MASTER_SID = SUPPLEMENTAL.PROJECTION_MASTER_SID
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                        --WHERE SUPPLEMENTAL.[YEAR]>=(SELECT year FROM PERIOD WHERE PERIOD_SID =@ACTUAL_START_PERIOD)')
						EXEC sp_execute @DSQL
                        END

                      ------------- PIVOTING START
                      DECLARE @SQL       NVARCHAR(MAX),
                              @LOOP_CNTR INT,
                              @MAX_CCP   INT

                      SET @SQL = 'SELECT YEAR, PERIOD, '
                      SET @LOOP_CNTR = 1
                      SET @MAX_CCP = (SELECT Max(ID)
                                      FROM   #TEMP_PROJECTION_MASTER)

                      WHILE @LOOP_CNTR <= @MAX_CCP
                        BEGIN
                            SET @SQL += '  EX_FACTORY_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_PROJECTED END),0),
									  DEMAND_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_PROJECTED END),0),
									  INVENTORY_WITHDRAWAL_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED END),0),
									  EX_FACTORY_SALES_PROJECTED_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_PROJECTED_PERCENT END),0),
									  DEMAND_SALES_PROJECTED_PERCENT_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_PROJECTED_PERCENT END),0),
									  INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT_'
                                        + Cast( @LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT END),0),
									CONTRACT_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_SALES_PROJECTED END),0),
									CONTRACT_UNITS_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_UNITS_PROJECTED END),0),
									MANDATED_DISCOUNT_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN MANDATED_DISCOUNT_PROJECTED END),0),
									SUPPLEMENTAL_DISCOUNT_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN SUPPLEMENTAL_DISCOUNT_PROJECTED END),0),
									MANDATED_DISCOUNT_PROJECTED_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN MANDATED_DISCOUNT_PROJECTED_PERCENTAGE END),0),
									SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
									TOTAL_DISCOUNT_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' =isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT_PROJECTED END),0),
									TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
									NET_SALES_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN NET_SALES_PROJECTED END),0),
									TOTAL_MANDATED_RPU_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_MANDATED_RPU END),0),
									TOTAL_SUPPLEMENTAL_RPU_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_SUPPLEMENTAL_RPU END),0),
							TOTAL_RPU_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_RPU_PROJECTED END),0),
									  COGS_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN COGS_PROJECTED END),0),
									  NET_PROFIT_PROJECTED_'
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' =isnull(MAX(CASE WHEN ID = '
                                        + Cast(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN NET_PROFIT_PROJECTED END),0),'
                            SET @LOOP_CNTR += 1
                        END

                      SET @SQL = LEFT(@SQL, Len(@SQL) - 1)
                      SET @SQL += ' FROM   #PIVOT_RESULT PR
					INNER JOIN #TEMP_PROJECTION_MASTER PM
					ON PR.PROJECTION_ID = PM.PROJECTION_MASTER_SID
					GROUP  BY [YEAR],PERIOD
					ORDER BY [YEAR],PERIOD'

                      EXEC Sp_executesql
                        @SQL


              IF OBJECT_ID('TEMPDB..#TEMP_GTS_DETAILS') IS NOT NULL
                     DROP TABLE #TEMP_GTS_DETAILS

              CREATE TABLE #TEMP_GTS_DETAILS (
                     ITEMID VARCHAR(50)
                     , GTS_SALES_PROJECTED NUMERIC(22, 6)
                     , GTS_SALES_ACTUALS NUMERIC(22, 6)
                     , UNITS NUMERIC(22, 6)
                     , MONTHLY INT
                     , QUARTERLY INT
                     , YEARLY INT
                     )

           SET @SQL=CONCAT('   INSERT INTO #TEMP_GTS_DETAILS (
                     ITEMID
                     , GTS_SALES_PROJECTED
                     , GTS_SALES_ACTUALS
                     , UNITS
                     , MONTHLY
                     , QUARTERLY
                     , YEARLY
                     )
					 
				SELECT ITEM_ID,
                				  
                GTS_SALES_PROJECTED=COALESCE(EXFACTORY_FORECAST_SALES,EXFACTORY_ACTUAL_SALES),
				EXFACTORY_ACTUAL_SALES,
                UNITS = COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS),
				MONTH,
				QUARTER,YEAR
                FROM ',@PRODUCT_FILE	,' P 
				JOIN ITEM_MASTER IM ON 
				IM.ITEM_MASTER_SID=P.ITEM_MASTER_SID 
				JOIN PERIOD P1 ON P1.PERIOD_SID=P.PERIOD_SID'
					 
					 )
					 EXEC sp_execute @SQL
           
                     
                  ------------- PIVOTING ENDS
                  END
                ELSE
                  BEGIN
                      IF ( @FREQUENCY = 'MONTHLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  MONTH = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                            FROM   PERIOD P
                            WHERE  EXISTS (SELECT Dateadd(mm, Datediff(mm, 0, Dateadd(day, -1, Dateadd(qq, Datediff(qq, 0, TO_DATE) + 1, 0))), 0)
                                                  FROM   PROJECTION_MASTER PM
                                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
												  AND P.PERIOD_DATE=Dateadd(mm, Datediff(mm, 0, Dateadd(day, -1, Dateadd(qq, Datediff(qq, 0, PM.TO_DATE) + 1, 0))), 0))

                            SELECT @PROJECTION_MASTER_SID PROJECTION_ID,
                                   Isnull(GTS.GTS_SALES_ACTUALS, 0) AS GTS_SALES_ACTUALS,
                                   Isnull(GTS.GTS_SALES_PROJECTED, 0) AS GTS_SALES_PROJECTED,
                                   P.[MONTH],
                                   P.[YEAR],
                                   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                 + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS [% OF BUSINESS],
                                   ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS [% OF BUSINESS_PROJECTED],
                                   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                   + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED
                            FROM   (SELECT Sum(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS, 
                                           Sum(COALESCE(GTS_SALES_PROJECTED, GTS_SALES_ACTUALS)) AS GTS_SALES_PROJECTED,
                                           Sum(UNITS) AS UNITS,
                                           MONTHLY PERIOD,
                                           YEARLY,
                                           PERIOD_SID
                                    FROM   #TEMP_GTS_DETAILS G
                                           INNER JOIN #ITEM_INFO ACT
                                                   ON G.ITEMID = ACT.ITEM_ID
                                           INNER JOIN PERIOD P
                                                   ON P.[MONTH] = G.MONTHLY
                                                      AND P.[YEAR] = G.YEARLY
                                    GROUP  BY MONTHLY,
                                              YEARLY,
                                              PERIOD_SID) GTS
                                   RIGHT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                                      Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                      COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID,
                                                      Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                                      Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
                                               FROM   (SELECT PROJECTION_DETAILS_SID,
                                                              PERIOD_SID,
                                                              ACTUAL_SALES,
                                                              ACTUAL_UNITS
                                                       FROM   M_ACTUAL_SALES NAS
                                                       WHERE  EXISTS (SELECT 1
                                                                      FROM   PROJECTION_DETAILS PD
                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                                      FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                        PERIOD_SID,
                                                                        PROJECTION_SALES,
                                                                        PROJECTION_UNITS
                                                                 FROM   M_SALES_PROJECTION NPS
                                                                 WHERE  EXISTS (SELECT 1
                                                                                FROM   PROJECTION_DETAILS PD
                                                                                WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                       AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) PROJ
                                                             ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SALES
                                           ON SALES.PERIOD_SID = GTS.PERIOD_SID
                                   INNER JOIN PERIOD P
                                           ON P.PERIOD_SID = SALES.PERIOD_SID
                                   LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                                     Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID
                                              FROM   (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             ACTUAL_SALES
                                                      FROM   M_ACTUAL_DISCOUNT NAS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                             AND NAS.SAVE_FLAG = 1) ACT
                                                     FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                       PERIOD_SID,
                                                                       PROJECTION_SALES
                                                                FROM   M_DISCOUNT_PROJECTION NPS
                                                                WHERE  EXISTS (SELECT 1
                                                                               FROM   PROJECTION_DETAILS PD
                                                                               WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                                       AND NPS.SAVE_FLAG = 1) PROJ
                                                            ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                               AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                              GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) DISCOUNT
                                          ON DISCOUNT.PERIOD_SID = SALES.PERIOD_SID
                                   LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS ,
                                                     Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID
                                              FROM   (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             ACTUAL_SALES
                                                      FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                                     FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                       PERIOD_SID,
                                                                       PROJECTION_SALES
                                                                FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                                WHERE  EXISTS (SELECT 1
                                                                               FROM   PROJECTION_DETAILS PD
                                                                               WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) PROJ
                                                            ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                               AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                              GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.PERIOD_SID = DISCOUNT.PERIOD_SID
                            ORDER  BY P.[MONTH],
                                      P.[YEAR]
                        END
                      ELSE IF ( @FREQUENCY = 'QUARTERLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  QUARTER = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                            FROM   PERIOD P
                            WHERE  EXISTS (SELECT Dateadd(mm, Datediff(mm, 0, Dateadd(day, -1, Dateadd(qq, Datediff(qq, 0, TO_DATE) + 1, 0))), 0)
                                                  FROM   PROJECTION_MASTER PM
                                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
												  AND P.PERIOD_DATE=Dateadd(mm, Datediff(mm, 0, Dateadd(day, -1, Dateadd(qq, Datediff(qq, 0, PM.TO_DATE) + 1, 0))), 0))

                            SELECT @PROJECTION_MASTER_SID PROJECTION_ID,
                                   Isnull(GTS.GTS_SALES_ACTUALS, 0) AS GTS_SALES_ACTUALS,
                                   Isnull(GTS.GTS_SALES_PROJECTED, 0) AS GTS_SALES_PROJECTED,
                                   SALES.[QUARTER],
                                   SALES.[YEAR],
                                   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                 + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS [% OF BUSINESS],
                                   ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS [% OF BUSINESS_PROJECTED],
                                   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                   + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED
                            FROM   (SELECT Sum(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS, 
                                           Sum(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED ,
                                           Sum(UNITS) AS UNITS,
                                           QUARTERLY,
                                           YEARLY
                                    FROM   #TEMP_GTS_DETAILS G
                                           INNER JOIN #ITEM_INFO ACT
                                                   ON G.ITEMID = ACT.ITEM_ID
                                    GROUP  BY QUARTERLY,
                                              YEARLY) GTS
                                   RIGHT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                                      Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                      [QUARTER],
                                                      [YEAR],
                                                      Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                                      Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
                                               FROM   (SELECT PROJECTION_DETAILS_SID,
                                                              PERIOD_SID,
                                                              ACTUAL_SALES,
                                                              ACTUAL_UNITS
                                                       FROM   M_ACTUAL_SALES NAS
                                                       WHERE  EXISTS (SELECT 1
                                                                      FROM   PROJECTION_DETAILS PD
                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                                      FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                        PERIOD_SID,
                                                                        PROJECTION_SALES,
                                                                        PROJECTION_UNITS
                                                                 FROM   M_SALES_PROJECTION NPS
                                                                 WHERE  EXISTS (SELECT 1
                                                                                FROM   PROJECTION_DETAILS PD
                                                                                WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                       AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) PROJ
                                                             ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                      INNER JOIN PERIOD P
                                                              ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                               GROUP  BY [QUARTER],
                                                         [YEAR]) SALES
                                           ON SALES.[QUARTER] = GTS.QUARTERLY
                                              AND SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                                     Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     [QUARTER],
                                                     [YEAR]
                                              FROM   (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             ACTUAL_SALES
                                                      FROM   M_ACTUAL_DISCOUNT NAS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                             AND NAS.SAVE_FLAG = 1) ACT
                                                     FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                       PERIOD_SID,
                                                                       PROJECTION_SALES
                                                                FROM   M_DISCOUNT_PROJECTION NPS
                                                                WHERE  EXISTS (SELECT 1
                                                                               FROM   PROJECTION_DETAILS PD
                                                                               WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                                       AND NPS.SAVE_FLAG = 1) PROJ
                                                            ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                               AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                              GROUP  BY [QUARTER],
                                                        [YEAR]) DISCOUNT
                                          ON DISCOUNT.[QUARTER] = SALES.[QUARTER]
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS ,
                                                     Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     [QUARTER],
                                                     [YEAR]
                                              FROM   (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             ACTUAL_SALES
                                                      FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                                     FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                       PERIOD_SID,
                                                                       PROJECTION_SALES
                                                                FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                                WHERE  EXISTS (SELECT 1
                                                                               FROM   PROJECTION_DETAILS PD
                                                                               WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) PROJ
                                                            ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                               AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                              GROUP  BY [QUARTER],
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.[QUARTER] = SALES.[QUARTER]
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                            ORDER  BY SALES.[YEAR],
                                      SALES.[QUARTER]
                        END
                      ELSE IF ( @FREQUENCY = 'SEMI-ANNUALLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  SEMI_ANNUAL = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                            FROM   PERIOD P
                            WHERE  EXISTS (SELECT Dateadd(mm, Datediff(mm, 0, Dateadd(day, -1, Dateadd(qq, Datediff(qq, 0, TO_DATE) + 1, 0))), 0)
                                                  FROM   PROJECTION_MASTER PM
                                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
												  AND P.PERIOD_DATE=Dateadd(mm, Datediff(mm, 0, Dateadd(day, -1, Dateadd(qq, Datediff(qq, 0, PM.TO_DATE) + 1, 0))), 0))

                            SELECT @PROJECTION_MASTER_SID PROJECTION_ID,
                                   Isnull(GTS.GTS_SALES_ACTUALS, 0) AS GTS_SALES_ACTUALS,
                                   Isnull(GTS.GTS_SALES_PROJECTED, 0) AS GTS_SALES_PROJECTED,
                                   SALES.[SEMI_ANNUAL],
                                   SALES.[YEAR],
                                   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                 + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS [% OF BUSINESS],
                                   ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS [% OF BUSINESS_PROJECTED],
                                   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                   + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED
                            FROM   (SELECT Sum(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS, 
                                           Sum(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED ,
                                           Sum(GTS.UNITS) UNITS,
                                           CASE
                                             WHEN GTS.QUARTERLY <= 2 THEN 1
                                             ELSE 2
                                           END            SEMI_ANNUAL,
                                           GTS.YEARLY
                                    FROM   #TEMP_GTS_DETAILS GTS
                                           INNER JOIN #ITEM_INFO ACT
                                                   ON GTS.ITEMID = ACT.ITEM_ID
                                    GROUP  BY CASE
                                                WHEN GTS.QUARTERLY <= 2 THEN 1
                                                ELSE 2
                                              END,
                                              GTS.YEARLY) GTS
                                   RIGHT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                                      Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                      SEMI_ANNUAL,
                                                      [YEAR],
                                                      Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                                      Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
                                               FROM   (SELECT PROJECTION_DETAILS_SID,
                                                              PERIOD_SID,
                                                              ACTUAL_SALES,
                                                              ACTUAL_UNITS
                                                       FROM   M_ACTUAL_SALES NAS
                                                       WHERE  EXISTS (SELECT 1
                                                                      FROM   PROJECTION_DETAILS PD
                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                                      FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                        PERIOD_SID,
                                                                        PROJECTION_SALES,
                                                                        PROJECTION_UNITS
                                                                 FROM   M_SALES_PROJECTION NPS
                                                                 WHERE  EXISTS (SELECT 1
                                                                                FROM   PROJECTION_DETAILS PD
                                                                                WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                       AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) PROJ
                                                             ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                      INNER JOIN PERIOD P
                                                              ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                               GROUP  BY SEMI_ANNUAL,
                                                         [YEAR]) SALES
                                           ON SALES.SEMI_ANNUAL = GTS.SEMI_ANNUAL
                                              AND SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                                     Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     SEMI_ANNUAL,
                                                     [YEAR]
                                              FROM   (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             ACTUAL_SALES
                                                      FROM   M_ACTUAL_DISCOUNT NAS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                             AND NAS.SAVE_FLAG = 1) ACT
                                                     FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                       PERIOD_SID,
                                                                       PROJECTION_SALES
                                                                FROM   M_DISCOUNT_PROJECTION NPS
                                                                WHERE  EXISTS (SELECT 1
                                                                               FROM   PROJECTION_DETAILS PD
                                                                               WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                                       AND NPS.SAVE_FLAG = 1) PROJ
                                                            ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                               AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                              GROUP  BY SEMI_ANNUAL,
                                                        [YEAR]) DISCOUNT
                                          ON DISCOUNT.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS ,
                                                     Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     SEMI_ANNUAL,
                                                     [YEAR]
                                              FROM   (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             ACTUAL_SALES
                                                      FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                                     FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                       PERIOD_SID,
                                                                       PROJECTION_SALES
                                                                FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                                WHERE  EXISTS (SELECT 1
                                                                               FROM   PROJECTION_DETAILS PD
                                                                               WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) PROJ
                                                            ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                               AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                              GROUP  BY SEMI_ANNUAL,
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                            ORDER  BY SALES.[YEAR],
                                      SALES.[SEMI_ANNUAL]
                        END
                      ELSE
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = Min(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  YEAR = @ACTUAL_START_YEAR

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                            FROM   PERIOD P
                            WHERE  EXISTS (SELECT Dateadd(mm, Datediff(mm, 0, Dateadd(day, -1, Dateadd(qq, Datediff(qq, 0, TO_DATE) + 1, 0))), 0)
                                                  FROM   PROJECTION_MASTER PM
                                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
												  AND P.PERIOD_DATE=Dateadd(mm, Datediff(mm, 0, Dateadd(day, -1, Dateadd(qq, Datediff(qq, 0, PM.TO_DATE) + 1, 0))), 0))

                            SELECT @PROJECTION_MASTER_SID PROJECTION_ID,
                                   Isnull(GTS.GTS_SALES_ACTUALS, 0) AS GTS_SALES_ACTUALS,
                                   Isnull(GTS.GTS_SALES_PROJECTED, 0) AS GTS_SALES_PROJECTED,
                                   SALES.[YEAR],
                                   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                                   Isnull(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   Isnull(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                                   Isnull(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                                   Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS,
                                   Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                 + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                                   ( Isnull(( Isnull(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS [% OF BUSINESS],
                                   ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS [% OF BUSINESS_PROJECTED],
                                   Isnull(SALES.CONTRACT_SALES_ACTUALS, 0) - ( Isnull(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                   + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + Isnull(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED
                            FROM   (SELECT Sum(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS, 
                                           Sum(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED ,
                                           Sum(GTS.UNITS) UNITS,
                                           GTS.YEARLY
                                    FROM   #TEMP_GTS_DETAILS GTS
                                           INNER JOIN #ITEM_INFO ACT
                                                   ON GTS.ITEMID = ACT.ITEM_ID
                                    GROUP  BY GTS.YEARLY) GTS
                                   RIGHT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                                      Sum(PROJECTION_SALES) AS  CONTRACT_SALES_PROJECTED ,
                                                      [YEAR],
                                                      Sum(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS,
                                                      Sum(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
                                               FROM   (SELECT PROJECTION_DETAILS_SID,
                                                              PERIOD_SID,
                                                              ACTUAL_SALES,
                                                              ACTUAL_UNITS
                                                       FROM   M_ACTUAL_SALES NAS
                                                       WHERE  EXISTS (SELECT 1
                                                                      FROM   PROJECTION_DETAILS PD
                                                                      WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                             AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                                      FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                        PERIOD_SID,
                                                                        PROJECTION_SALES,
                                                                        PROJECTION_UNITS
                                                                 FROM   M_SALES_PROJECTION NPS
                                                                 WHERE  EXISTS (SELECT 1
                                                                                FROM   PROJECTION_DETAILS PD
                                                                                WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                       AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) PROJ
                                                             ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                                AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                      INNER JOIN PERIOD P
                                                              ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                               GROUP  BY [YEAR]) SALES
                                           ON SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                                     Sum(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,
                                                     [YEAR]
                                              FROM   (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             ACTUAL_SALES
                                                      FROM   M_ACTUAL_DISCOUNT NAS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                             AND NAS.SAVE_FLAG = 1) ACT
                                                     FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                       PERIOD_SID,
                                                                       PROJECTION_SALES
                                                                FROM   M_DISCOUNT_PROJECTION NPS
                                                                WHERE  EXISTS (SELECT 1
                                                                               FROM   PROJECTION_DETAILS PD
                                                                               WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)
                                                                       AND NPS.SAVE_FLAG = 1) PROJ
                                                            ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                               AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                              GROUP  BY [YEAR]) DISCOUNT
                                          ON DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT Sum(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS ,
                                                     Sum(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     [YEAR]
                                              FROM   (SELECT PROJECTION_DETAILS_SID,
                                                             PERIOD_SID,
                                                             ACTUAL_SALES
                                                      FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                                      WHERE  EXISTS (SELECT 1
                                                                     FROM   PROJECTION_DETAILS PD
                                                                     WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) ACT
                                                     FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                       PERIOD_SID,
                                                                       PROJECTION_SALES
                                                                FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                                WHERE  EXISTS (SELECT 1
                                                                               FROM   PROJECTION_DETAILS PD
                                                                               WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                      AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID)) PROJ
                                                            ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                               AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                              GROUP  BY [YEAR]) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                            ORDER  BY SALES.[YEAR]
                        END
                  END
            END

 
      END TRY

      BEGIN CATCH
          DECLARE @ErrorMessage NVARCHAR(4000);
          DECLARE @ErrorSeverity INT;
          DECLARE @ErrorState INT;
          DECLARE @ErrorNumber INT;
          DECLARE @ErrorProcedure VARCHAR(200);
          DECLARE @Errorline INT;

          EXEC Usperrorcollector

          SELECT @ErrorMessage = Error_message(),
                 @ErrorSeverity = Error_severity(),
                 @ErrorState = Error_state(),
                 @ErrorProcedure = Error_procedure(),
                 @Errorline = Error_line(),
                 @ErrorNumber = Error_number()

          RAISERROR ( @ErrorMessage,-- Message text.
                      @ErrorSeverity,-- Severity.
                      @ErrorState,-- State.
                      @ErrorProcedure,-- Procedure_Name.
                      @ErrorNumber,-- ErrorNumber
                      @Errorline -- ErrorLine
          );
      END CATCH
  END 
