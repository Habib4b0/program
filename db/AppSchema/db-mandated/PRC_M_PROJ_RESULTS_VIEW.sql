IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_M_PROJ_RESULTS_VIEW'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_M_PROJ_RESULTS_VIEW]
  END

GO

CREATE PROCEDURE [dbo].[PRC_M_PROJ_RESULTS_VIEW] (@PROJECTION_MASTER_SID VARCHAR(500),
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
                  @ITEM_UDT              UDT_ITEM,
                  @FORECAST_NAME         VARCHAR(50),
                  @FORECAST_VERSION      VARCHAR(15),
                  @FORECAST_NAME_INV     VARCHAR(50),
                  @FORECAST_VERSION_INV  VARCHAR(15),
                  @FORECAST_NAME_DM      VARCHAR(50),
                  @FORECAST_VERSION_DM   VARCHAR(15),
                  @ITEM_UOM              VARCHAR(50) = 'EACH' -- 'UN' TO 'EACH' GALUAT-46
                  ,
                  @PROJ_START_PERIOD_SID INT,
                  @START_DATE            DATETIME,
                  @BUSINESS_UNIT         INT,
                  @COMPANY_ID            INT

          IF OBJECT_ID('TEMPDB..#TEMP_PROJECTION_MASTER') IS NOT NULL
            DROP TABLE #TEMP_PROJECTION_MASTER

          CREATE TABLE #TEMP_PROJECTION_MASTER
            (
               ID                    INT IDENTITY(1, 1),
               PROJECTION_MASTER_SID INT
            )

          IF OBJECT_ID('TEMPDB..#PIVOT_RESULT') IS NOT NULL
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

          SET @TEMP_PROJ_SID = REPLACE(@PROJECTION_MASTER_SID + ',', ',,', ',')

          WHILE PATINDEX('%,%', @TEMP_PROJ_SID) <> 0
            BEGIN
                SELECT @SP = PATINDEX('%,%', @TEMP_PROJ_SID)

                SELECT @SP_PROJ_SID = LEFT(@TEMP_PROJ_SID, @SP - 1)

                SELECT @TEMP_PROJ_SID = STUFF(@TEMP_PROJ_SID, 1, @SP, '')

                INSERT INTO #TEMP_PROJECTION_MASTER
                            (PROJECTION_MASTER_SID)
                SELECT @SP_PROJ_SID
            END

          DECLARE @FIRST_PROJ_SID INT

          SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID
          FROM   #TEMP_PROJECTION_MASTER PM
          WHERE  ID = 1

          SELECT TOP 1 @STARTFROM = DATEADD(YY, DATEDIFF(YY, 0, DATEADD(YY, -3, GETDATE())), 0),
                       @START_DATE = DATEADD(DD, 1, EOMONTH(FROM_DATE, -1)),
                       @PROJECTION_DATE = DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
          FROM   PROJECTION_MASTER
          WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
		  ORDER BY PROJECTION_MASTER_SID

          SELECT @START_PERIOD_SID = MAX(CASE
                                           WHEN PERIOD_DATE = @STARTFROM THEN PERIOD_SID
                                         END),
                 @END_PERIOD_SID = MAX(CASE
                                         WHEN PERIOD_DATE = DATEADD(MM, DATEDIFF(MM, 0, @PROJECTION_DATE), 0) THEN PERIOD_SID
                                       END),
                 @PROJ_START_PERIOD_SID = MAX(CASE
                                                WHEN PERIOD_DATE = @START_DATE THEN PERIOD_SID
                                              END)
          FROM   PERIOD
          WHERE  PERIOD_DATE IN ( @STARTFROM, @PROJECTION_DATE, @START_DATE )

          
          IF OBJECT_ID('TEMPDB..#TEMP_PERIOD') IS NOT NULL
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

          IF OBJECT_ID('TEMPDB..#ITEM_INFO') IS NOT NULL
            DROP TABLE #ITEM_INFO

          CREATE TABLE #ITEM_INFO
            (
               PROJECTION_DETAILS_SID INT,
               ITEM_ID                VARCHAR(50),
               NDC8                   VARCHAR(20),
               ITEM_MASTER_SID        INT
            )

          INSERT INTO #ITEM_INFO
                      (PROJECTION_DETAILS_SID,
                       ITEM_ID,
                       NDC8,
                       ITEM_MASTER_SID)
          SELECT DISTINCT PD.PROJECTION_DETAILS_SID,
                          IM.ITEM_ID,
                          IM.NDC8,
                          IM.ITEM_MASTER_SID
          FROM   ITEM_MASTER IM
                 INNER JOIN CCP_DETAILS CCP
                         ON CCP.ITEM_MASTER_SID = IM.ITEM_MASTER_SID
                 INNER JOIN PROJECTION_DETAILS PD
                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
          WHERE  PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID

          INSERT INTO @ITEM_UDT
                      (ITEM_MASTER_SID)
          SELECT DISTINCT ITEM_MASTER_SID
          FROM   #ITEM_INFO

          IF OBJECT_ID('TEMPDB..#TEMP_CCP') IS NOT NULL
            DROP TABLE #TEMP_CCP

          CREATE TABLE #TEMP_CCP
            (
               COMPANY_MASTER_SID     INT,
               CONTRACT_MASTER_SID    INT,
               ITEM_MASTER_SID        INT,
               PROJECTION_DETAILS_SID INT PRIMARY KEY,
               PROJECTION_MASTER_SID  INT,
               BUSINESS_UNIT          INT,
               COMPANY                INT
            )

          INSERT INTO #TEMP_CCP
                      (COMPANY_MASTER_SID,
                       CONTRACT_MASTER_SID,
                       ITEM_MASTER_SID,
                       PROJECTION_DETAILS_SID,
                       PROJECTION_MASTER_SID,
                       BUSINESS_UNIT,
                       COMPANY)
          SELECT CCP.COMPANY_MASTER_SID,
                 CCP.CONTRACT_MASTER_SID,
                 CCP.ITEM_MASTER_SID,
                 PD.PROJECTION_DETAILS_SID,
                 PM.PROJECTION_MASTER_SID,
                 BUSINESS_UNIT,
                 PM.COMPANY_MASTER_SID AS COMPANY
          FROM   CCP_DETAILS CCP
                 INNER JOIN PROJECTION_DETAILS PD
                         ON CCP.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                 INNER JOIN PROJECTION_MASTER PM
                         ON PD.PROJECTION_MASTER_SID = PM.PROJECTION_MASTER_SID
          WHERE  PM.PROJECTION_MASTER_SID = @FIRST_PROJ_SID

          --------------------GAL-808------------------------------------------
          SELECT @BUSINESS_UNIT = BUSINESS_UNIT,
                 @COMPANY_ID = COMPANY
          FROM   #TEMP_CCP

          ----------------------------------------------------------------------------------
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
                         AND HT.[DESCRIPTION] IN ( 'EX-FACTORY SALES', 'DEMAND', 'INVENTORY WITHDRAWAL - FORECAST SUMMARY' )
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
          WHERE  FILE_TYPE = 'INVENTORY WITHDRAWAL - FORECAST SUMMARY'

          SELECT @FORECAST_NAME_DM = FORECAST_NAME,
                 @FORECAST_VERSION_DM = [VERSION]
          FROM   @FILE_VER
          WHERE  FILE_TYPE = 'DEMAND'

          IF OBJECT_ID('TEMPDB..#TEMP_GTS_DETAILS') IS NOT NULL
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
                          COALESCE(A.FORECAST_GTS_SALES, A.ACTUAL_GTS_SALES) AS FORECAST_GTS_SALES,
                          A.ACTUAL_GTS_SALES,
                          COALESCE(A.FORECAST_GTS_UNITS, A.ACTUAL_GTS_UNITS) AS UNITS,
                          P.MONTH,
                          P.QUARTER,
                          P.YEAR
          FROM   UDF_GTS_WAC(@ITEM_UDT, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME, @FORECAST_VERSION) A
                 INNER JOIN PERIOD P
                         ON P.PERIOD_SID = A.PERIOD_SID
                 INNER JOIN #ITEM_INFO II
                         ON II.ITEM_MASTER_SID = A.ITEM_MASTER_SID
          ORDER  BY P.MONTH,
                    P.QUARTER,
                    P.YEAR

          DECLARE @ITEMID [DBO].[UDT_ITEM]

          INSERT INTO @ITEMID
          SELECT IM.ITEM_MASTER_SID
          FROM   ITEM_MASTER IM
          WHERE  EXISTS (SELECT 1
                         FROM   #TEMP_CCP A
                         WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                AND IM.ITEM_MASTER_SID = A.ITEM_MASTER_SID)

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
                 I.PERIOD_SID,
                 ACT_AMOUNT_WITHDRAWN,
                 ACT_UNITS_WITHDRAWN,
                 FOR_AMOUNT_WITHDRAWN,
                 FOR_UNITS_WITHDRAWN,
                 YEAR,
                 MONTH,
                 QUARTER,
                 SEMI_ANNUAL
          FROM   [DBO].[UDF_INVENTORY_WAC](@ITEMID, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME_INV, @FORECAST_VERSION_INV) I
                 INNER JOIN PERIOD P
                         ON P.PERIOD_SID = I.PERIOD_SID
                 INNER JOIN @ITEMID IM
                         ON I.ITEM_MASTER_SID = IM.ITEM_MASTER_SID

          -------------------------------------------------------DEMAND------------------------------------------------
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
                       YEARLY,
                       MONTHLY,
                       QUARTERLY,
                       SEMI_ANNUAL)
          SELECT D.ITEM_MASTER_SID,
                 P.PERIOD_SID,
                 ACT_GROSS_AMOUNT,
                 ACT_GROSS_UNITS,
                 COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT),
                 COALESCE(FOR_GROSS_UNITS, ACT_GROSS_UNITS),
                 P.[YEAR],
                 P.MONTH,
                 P.[QUARTER],
                 P.SEMI_ANNUAL
          FROM   [DBO].[UDF_DEMAND_WAC](@ITEMID, @START_PERIOD_SID, @END_PERIOD_SID, @FORECAST_NAME_DM, @FORECAST_VERSION_DM) D
                 INNER JOIN PERIOD P
                         ON P.PERIOD_SID = D.PERIOD_SID
                 INNER JOIN @ITEMID IM
                         ON D.ITEM_MASTER_SID = IM.ITEM_MASTER_SID

          --------------------------------------------------ITEM_PRICING-----------------------------------------------
          IF OBJECT_ID('TEMPDB..#ITEM_PRICING') IS NOT NULL
            DROP TABLE #ITEM_PRICING

          SELECT ITEM_MASTER_SID,
             PERIOD_SID,
             PRICING_QUALIFIER,
             ITEM_PRICE
          INTO   #ITEM_PRICING
          FROM   [DBO].[UDF_ITEM_PRICING](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, @ITEM_UOM) -- @ITEM_UOM  'UN' TO 'EACH'  GALUTA-46
          -------------------------------------------------------END---------------------------------------
          IF ( @SCREEN_NAME = 'PROJECTION RESULTS' )
            BEGIN
                IF ( @FREQUENCY = 'MONTHLY' )
                  BEGIN
                      SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                      FROM   PERIOD
                      WHERE  MONTH = @ACTUAL_START_FREQ
                             AND YEAR = @ACTUAL_START_YEAR

                      SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)

                      SELECT @FIRST_PROJ_SID PROJECTION_ID,
                             ISNULL(GTS.GTS_SALES_ACTUALS, 0) AS EX_FACTORY_SALES_ACTUALS,
                             ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                             P.[MONTH],
                             P.[YEAR],
                             ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                             ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                             ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                             ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS ,
                             ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                             ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                     + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                           + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                             ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                             + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                             ISNULL(DEMAND.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS,
                             ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                             ISNULL(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_ACTUAL,
                             ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_PROJECTED,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUALS_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                             ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUALS,
                             (ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )AS TOTAL_MANDATED_RPU_PROJECTED,
                             ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUALS,
                             ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                            + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUALS,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                             ISNULL(SALES.COGS_ACTUALS, 0) AS COGS_ACTUALS,
                             ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                               + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) - ( ISNULL(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                      FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                     SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                     SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                     SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
                                     MONTHLY,
                                     YEARLY,
                                     PERIOD_SID
                              FROM   #INVENTORY
                              GROUP  BY MONTHLY,
                                        YEARLY,
                                        PERIOD_SID) INVENTORY
                             RIGHT JOIN (SELECT DISTINCT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                         SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                         SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                         SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
                                                         MONTHLY,
                                                         YEARLY,
                                                         PERIOD_SID
                                         FROM   #DEMAND
                                         GROUP  BY MONTHLY,
                                                   YEARLY,
                                                   PERIOD_SID) DEMAND
                                     ON INVENTORY.PERIOD_SID = DEMAND.PERIOD_SID
                             RIGHT JOIN (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                                SUM(COALESCE(GTS_SALES_PROJECTED, GTS_SALES_ACTUALS)) AS GTS_SALES_PROJECTED,
                                                SUM(UNITS) AS UNITS ,
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
                                     ON DEMAND.PERIOD_SID = GTS.PERIOD_SID
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                               COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID,
                                               SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS ,
                                               SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                               SUM(COGS_ACTUAL) AS COGS_ACTUALS,
                                               SUM(COGS_PROJECTED) AS COGS_PROJECTED 
                                        FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       ( ISNULL(NAS.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) ) AS COGS_ACTUAL
                                                FROM   M_ACTUAL_SALES NAS
                                                       INNER JOIN PROJECTION_DETAILS PD
                                                               ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 ( ISNULL(NPS.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) ) AS COGS_PROJECTED
                                                          FROM   M_SALES_PROJECTION NPS
                                                                 INNER JOIN PROJECTION_DETAILS PD
                                                                         ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                                                            AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SALES
                                    ON SALES.PERIOD_SID = GTS.PERIOD_SID
                             INNER JOIN PERIOD P
                                     ON P.PERIOD_SID = COALESCE(SALES.PERIOD_SID, GTS.PERIOD_SID)
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                               SUM(ACTUAL_RPU) AS ACTUAL_RPU,
                                               SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                               COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID 
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   M_ACTUAL_DISCOUNT NAS
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   M_DISCOUNT_PROJECTION NPS
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) DISCOUNT
                                    ON DISCOUNT.PERIOD_SID = SALES.PERIOD_SID
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                               SUM(ACTUAL_RPU) AS ACTUAL_RPU,
                                               SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                               COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID 
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                        GROUP  BY COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)) SUPPLEMENTAL
                                    ON SUPPLEMENTAL.PERIOD_SID = DISCOUNT.PERIOD_SID
                      ORDER  BY P.YEAR,
                                P.MONTH
                  END
                ELSE IF ( @FREQUENCY = 'QUARTERLY' )
                  BEGIN
                      SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                      FROM   PERIOD
                      WHERE  QUARTER = @ACTUAL_START_FREQ
                             AND YEAR = @ACTUAL_START_YEAR

                      SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)
					  

                      SELECT @FIRST_PROJ_SID                          PROJECTION_ID,
                             ISNULL(GTS.GTS_SALES_ACTUALS, 0) AS EX_FACTORY_SALES_ACTUALS,
                             ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                             COALESCE(SALES.[QUARTER], GTS.QUARTERLY) [QUARTER],
                             COALESCE(SALES.[YEAR], GTS.YEARLY)       [YEAR],
                             ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                             ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                             ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                             ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS ,
                             ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                             ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                     + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                           + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                             ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                             + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                             ISNULL(DEMAND.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS,
                             ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                             ISNULL(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_ACTUAL,
                             ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_PROJECTED,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUALS_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                             ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUALS,
                             (ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )AS TOTAL_MANDATED_RPU_PROJECTED,
                             ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUALS,
                             ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                            + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUALS,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                             ISNULL(SALES.COGS_ACTUAL, 0) AS COGS_ACTUAL,
                             ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                               + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) - ( ISNULL(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                      FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                     SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                     SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                     SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
                                     QUARTERLY,
                                     YEARLY
                              FROM   #INVENTORY
                              GROUP  BY QUARTERLY,
                                        YEARLY) INVENTORY
                             RIGHT JOIN (SELECT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
                                                QUARTERLY,
                                                YEARLY
                                         FROM   #DEMAND
                                         GROUP  BY QUARTERLY,
                                                   YEARLY) DEMAND
                                     ON INVENTORY.QUARTERLY = DEMAND.QUARTERLY
                                        AND INVENTORY.YEARLY = DEMAND.YEARLY
                             RIGHT JOIN (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                                SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                                SUM(UNITS) AS UNITS ,
                                                QUARTERLY,
                                                YEARLY
                                         FROM   #TEMP_GTS_DETAILS G
                                         GROUP  BY QUARTERLY,
                                                   YEARLY) GTS
                                     ON GTS.QUARTERLY = DEMAND.QUARTERLY
                                        AND GTS.YEARLY = DEMAND.YEARLY
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                               [QUARTER],
                                               [YEAR],
                                               SUM(COGS_ACTUAL) AS COGS_ACTUAL,
                                               SUM(COGS_PROJECTED) AS COGS_PROJECTED ,
                                               SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS ,
                                               SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
                                        FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       ( ISNULL(NAS.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) ) AS COGS_ACTUAL
                                                FROM   M_ACTUAL_SALES NAS
                                                       INNER JOIN PROJECTION_DETAILS PD
                                                               ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 ( ISNULL(NPS.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) ) AS COGS_PROJECTED
                                                          FROM   M_SALES_PROJECTION NPS
                                                                 INNER JOIN PROJECTION_DETAILS PD
                                                                         ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                                                            AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        WHERE  P.PERIOD_SID BETWEEN ISNULL(@ACTUAL_START_PERIOD, 1) AND @ACTUAL_END_PERIOD
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) SALES
                                    ON SALES.[QUARTER] = GTS.QUARTERLY
                                       AND SALES.[YEAR] = GTS.YEARLY
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                               SUM(ACTUAL_RPU) AS ACTUAL_RPU,
                                               SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                               QUARTER,
                                               YEAR
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   M_ACTUAL_DISCOUNT NAS
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   M_DISCOUNT_PROJECTION NPS
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) DISCOUNT
                                    ON DISCOUNT.[QUARTER] = SALES.[QUARTER]
                                       AND DISCOUNT.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                               SUM(ACTUAL_RPU) AS ACTUAL_RPU,
                                               SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                               [QUARTER],
                                               [YEAR]
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [QUARTER],
                                                  [YEAR]) SUPPLEMENTAL
                                    ON SUPPLEMENTAL.[QUARTER] = SALES.[QUARTER]
                                       AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY),
                                COALESCE(SALES.[QUARTER], GTS.QUARTERLY)
                  END
                ELSE IF ( @FREQUENCY = 'SEMI-ANNUALLY' )
                  BEGIN
                      SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                      FROM   PERIOD
                      WHERE  YEAR = @ACTUAL_START_YEAR

                      SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)

                      SELECT @FIRST_PROJ_SID                                PROJECTION_ID,
                             ISNULL(GTS.GTS_SALES_ACTUALS, 0) AS EX_FACTORY_SALES_ACTUALS,
                             ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                             COALESCE(SALES.[SEMI_ANNUAL], GTS.SEMI_ANNUAL) [SEMI_ANNUAL],
                             COALESCE(SALES.[YEAR], GTS.YEARLY)             [YEAR],
                             ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                             ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                             ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                             ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS ,
                             ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                             ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                     + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                           + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                             ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                             + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                             ISNULL(DEMAND.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS,
                             ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                             ISNULL(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_ACTUAL,
                             ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_PROJECTED,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUALS_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                             ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUALS,
                             ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_MANDATED_RPU_PROJECTED,
                             ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUALS,
                             ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                            + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUALS,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                             ISNULL(SALES.COGS_ACTUAL, 0) AS COGS_ACTUAL,
                             ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                               + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) - ( ISNULL(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                      FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                     SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                     SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                     SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
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
                             RIGHT JOIN (SELECT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
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
                             RIGHT JOIN (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                                SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                                SUM(GTS.UNITS) UNITS,
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
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                               SEMI_ANNUAL,
                                               [YEAR],
                                               SUM(COGS_ACTUAL) AS COGS_ACTUAL,
                                               SUM(COGS_PROJECTED) AS COGS_PROJECTED ,
                                               SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS ,
                                               SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
                                        FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       ( ISNULL(NAS.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) ) AS COGS_ACTUAL
                                                FROM   M_ACTUAL_SALES NAS
                                                       INNER JOIN PROJECTION_DETAILS PD
                                                               ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 ( ISNULL(NPS.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) ) AS COGS_PROJECTED
                                                          FROM   M_SALES_PROJECTION NPS
                                                                 INNER JOIN PROJECTION_DETAILS PD
                                                                         ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                                                            AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY SEMI_ANNUAL,
                                                  [YEAR]) SALES
                                    ON SALES.SEMI_ANNUAL = GTS.SEMI_ANNUAL
                                       AND SALES.[YEAR] = GTS.YEARLY
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                               SUM(ACTUAL_RPU) AS ACTUAL_RPU,
                                               SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                               SEMI_ANNUAL,
                                               YEAR
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   M_ACTUAL_DISCOUNT NAS
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   M_DISCOUNT_PROJECTION NPS
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY SEMI_ANNUAL,
                                                  [YEAR]) DISCOUNT
                                    ON DISCOUNT.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                       AND DISCOUNT.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                               SUM(ACTUAL_RPU) AS ACTUAL_RPU,
                                               SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                               SEMI_ANNUAL,
                                               [YEAR]
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY SEMI_ANNUAL,
                                                  [YEAR]) SUPPLEMENTAL
                                    ON SUPPLEMENTAL.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                       AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY),
                                COALESCE(SALES.[SEMI_ANNUAL], GTS.SEMI_ANNUAL)
                  END
                ELSE
                  BEGIN
                      SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                      FROM   PERIOD
                      WHERE  YEAR = @ACTUAL_START_YEAR

                      SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)

                      SELECT @FIRST_PROJ_SID                    PROJECTION_ID,
                             ISNULL(GTS.GTS_SALES_ACTUALS, 0) AS EX_FACTORY_SALES_ACTUALS,
                             ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                             COALESCE(SALES.[YEAR], GTS.YEARLY) [YEAR],
                             ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                             ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                             ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                             ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS ,
                             ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                             ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                             ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                     + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                             ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                        + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                           + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS EX_FACTORY_SALES_ACTUALS_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                             ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                             + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                             ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                 + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                             ISNULL(DEMAND.DEMAND_SALES_ACTUAL, 0) AS DEMAND_SALES_ACTUALS,
                             ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                             ISNULL(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_ACTUAL,
                             ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_PROJECTED,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(DEMAND.DEMAND_SALES_ACTUAL, 0), 0) ) * 100 AS DEMAND_SALES_ACTUALS_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(INVENTORY.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_ACTUAL_PERCENT,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                             ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_MANDATED_RPU_ACTUALS,
                             (ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )AS TOTAL_MANDATED_RPU_PROJECTED,
                             ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_ACTUALS,
                             ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU_PROJECTED,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                            + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ) AS TOTAL_RPU_ACTUALS,
                             ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                             ISNULL(SALES.COGS_ACTUAL, 0) AS COGS_ACTUAL, 
                             ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                             ( ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                               + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) - ( ISNULL(SALES.COGS_ACTUALS, 0) ) ) AS NET_PROFIT_ACTUAL,
                             ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                      FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                     SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                     SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                     SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
                                     YEARLY
                              FROM   #INVENTORY
                              GROUP  BY YEARLY) INVENTORY
                             RIGHT JOIN (SELECT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
                                                YEARLY
                                         FROM   #DEMAND
                                         GROUP  BY YEARLY) DEMAND
                                     ON INVENTORY.YEARLY = DEMAND.YEARLY
                             RIGHT JOIN (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                                SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                                SUM(GTS.UNITS) UNITS,
                                                GTS.YEARLY
                                         FROM   #TEMP_GTS_DETAILS GTS
                                         GROUP  BY GTS.YEARLY) GTS
                                     ON DEMAND.YEARLY = GTS.YEARLY
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                               [YEAR],
                                               SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS ,
                                               SUM(COGS_ACTUAL) AS COGS_ACTUAL,
                                               SUM(COGS_PROJECTED) AS COGS_PROJECTED ,
                                               SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
                                        FROM   (SELECT PD.PROJECTION_DETAILS_SID,
                                                       NAS.PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_UNITS,
                                                       CCP.ITEM_MASTER_SID,
                                                       ( ISNULL(NAS.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) ) AS COGS_ACTUAL
                                                FROM   M_ACTUAL_SALES NAS
                                                       INNER JOIN PROJECTION_DETAILS PD
                                                               ON PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                       INNER JOIN CCP_DETAILS CCP
                                                               ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                       INNER JOIN #ITEM_PRICING U
                                                               ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                  AND NAS.PERIOD_SID = U.PERIOD_SID
                                                                  AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                               ) ACT
                                               FULL JOIN (SELECT PD.PROJECTION_DETAILS_SID,
                                                                 NPS.PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_UNITS,
                                                                 CCP.ITEM_MASTER_SID,
                                                                 ( ISNULL(NPS.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0) ) AS COGS_PROJECTED
                                                          FROM   M_SALES_PROJECTION NPS
                                                                 INNER JOIN PROJECTION_DETAILS PD
                                                                         ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                 INNER JOIN CCP_DETAILS CCP
                                                                         ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                                 INNER JOIN #ITEM_PRICING U
                                                                         ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                            AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                            AND PD.PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
                                                                            AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [YEAR]) SALES
                                    ON SALES.[YEAR] = GTS.YEARLY
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                               SUM(ACTUAL_RPU) AS ACTUAL_RPU,
                                               SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                               YEAR
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   M_ACTUAL_DISCOUNT NAS
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   M_DISCOUNT_PROJECTION NPS
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [YEAR]) DISCOUNT
                                    ON DISCOUNT.[YEAR] = SALES.[YEAR]
                             LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                               SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                               SUM(ACTUAL_RPU) AS ACTUAL_RPU,
                                               SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                               [YEAR]
                                        FROM   (SELECT PROJECTION_DETAILS_SID,
                                                       PERIOD_SID,
                                                       ACTUAL_SALES,
                                                       ACTUAL_RPU
                                                FROM   M_SUPPLEMENTAL_DISC_ACTUALS NAS
                                                WHERE  EXISTS (SELECT 1
                                                               FROM   PROJECTION_DETAILS PD
                                                               WHERE  PD.PROJECTION_DETAILS_SID = NAS.PROJECTION_DETAILS_SID
                                                                      AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                               ) ACT
                                               FULL JOIN (SELECT PROJECTION_DETAILS_SID,
                                                                 PERIOD_SID,
                                                                 PROJECTION_SALES,
                                                                 PROJECTION_RPU
                                                          FROM   M_SUPPLEMENTAL_DISC_PROJ NPS
                                                          WHERE  EXISTS (SELECT 1
                                                                         FROM   PROJECTION_DETAILS PD
                                                                         WHERE  PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                                                AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                                 AND NPS.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD) PROJ
                                                      ON ACT.PROJECTION_DETAILS_SID = PROJ.PROJECTION_DETAILS_SID
                                                         AND ACT.PERIOD_SID = PROJ.PERIOD_SID
                                               INNER JOIN PERIOD P
                                                       ON P.PERIOD_SID = COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID)
                                        GROUP  BY [YEAR]) SUPPLEMENTAL
                                    ON SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                      ORDER  BY COALESCE(SALES.[YEAR], GTS.YEARLY)
                  END
            END
          ELSE
            BEGIN
                IF ( @VIEW = 'PIVOT' )
                  BEGIN
                      IF ( @FREQUENCY = 'MONTHLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  MONTH = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)

                            INSERT INTO #PIVOT_RESULT
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
                            SELECT @FIRST_PROJ_SID PROJECTION_ID,
                                   D.[MONTH],
                                   D.[YEAR],
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                                   ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                                   ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   (ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )AS TOTAL_MANDATED_RPU_PROJECTED,
                                   ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                            FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                           SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                           SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                           SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
                                           MONTHLY,
                                           YEARLY,
                                           PERIOD_SID
                                    FROM   #INVENTORY
                                    GROUP  BY MONTHLY,
                                              YEARLY,
                                              PERIOD_SID) INVENTORY
                                   RIGHT JOIN (SELECT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                      SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                      SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                      SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
                                                      MONTHLY,
                                                      YEARLY,
                                                      PERIOD_SID
                                               FROM   #DEMAND
                                               GROUP  BY MONTHLY,
                                                         YEARLY,
                                                         PERIOD_SID) DEMAND
                                           ON INVENTORY.PERIOD_SID = DEMAND.PERIOD_SID
                                   RIGHT JOIN (SELECT SUM(COALESCE(GTS_SALES_PROJECTED, GTS_SALES_ACTUALS)) AS GTS_SALES_PROJECTED,
                                                      SUM(UNITS) AS UNITS ,
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
                                              AND GTS.YEARLY = DEMAND.YEARLY
                                              AND GTS.PERIOD = DEMAND.MONTHLY
                                   INNER JOIN PERIOD D
                                           ON D.PERIOD_SID = COALESCE(GTS.PERIOD_SID, DEMAND.PERIOD_SID)
                                   RIGHT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                      COALESCE(NPS.PERIOD_SID, U.PERIOD_SID) AS PERIOD_SID,
                                                      SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                      ( ISNULL(SUM(NPS.PROJECTION_UNITS), 0) * ISNULL(AVG(NULLIF(U.ITEM_PRICE, 0)), 0) ) AS COGS_PROJECTED --ISNULL(AVG(U.ITEM_PRICE), 0) )
                                               FROM   M_SALES_PROJECTION NPS
                                                      INNER JOIN PROJECTION_DETAILS PD
                                                              ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                      INNER JOIN CCP_DETAILS CCP
                                                              ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                      INNER JOIN #ITEM_PRICING U
                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                 AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                 AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                               GROUP  BY COALESCE(NPS.PERIOD_SID, U.PERIOD_SID) 
                                              ) SALES
                                           ON SALES.PERIOD_SID = GTS.PERIOD_SID
                                   INNER JOIN PERIOD P
                                           ON P.PERIOD_SID = COALESCE(SALES.PERIOD_SID, GTS.PERIOD_SID)
                                              AND P.PERIOD_SID BETWEEN COALESCE(@PROJ_START_PERIOD_SID, 1) AND @ACTUAL_END_PERIOD
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                                     PERIOD_SID,
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                              WHERE  EXISTS (SELECT 1
                                                             FROM   PROJECTION_DETAILS PD
                                                             WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                    AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                              GROUP  BY PERIOD_SID) DISCOUNT
                                          ON DISCOUNT.PERIOD_SID = SALES.PERIOD_SID
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     PERIOD_SID
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                              WHERE  EXISTS (SELECT 1
                                                             FROM   PROJECTION_DETAILS PD
                                                             WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                    AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                              GROUP  BY PERIOD_SID) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.PERIOD_SID = SALES.PERIOD_SID
                            WHERE  D.PERIOD_SID >= @ACTUAL_START_PERIOD
                            ORDER  BY D.[MONTH],
                                      D.[YEAR]
                        END
                      ELSE IF ( @FREQUENCY = 'QUARTERLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  QUARTER = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@ACTUAL_START_PERIOD, @PROJ_START_PERIOD_SID)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)

                            INSERT INTO #PIVOT_RESULT
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
                            SELECT @FIRST_PROJ_SID PROJECTION_ID,
                                   COALESCE(GTS.QUARTERLY, 0),
                                   COALESCE(GTS.YEARLY, 0),
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                                   ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                                   ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   (ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )AS TOTAL_MANDATED_RPU_PROJECTED,
                                   ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                            FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                           SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                           SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                           SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
                                           QUARTERLY,
                                           YEARLY
                                    FROM   #INVENTORY
                                    GROUP  BY QUARTERLY,
                                              YEARLY) INVENTORY
                                   RIGHT JOIN (SELECT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                      SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                      SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                      SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
                                                      QUARTERLY,
                                                      YEARLY
                                               FROM   #DEMAND
                                               GROUP  BY QUARTERLY,
                                                         YEARLY) DEMAND
                                           ON DEMAND.YEARLY = INVENTORY.YEARLY
                                              AND DEMAND.QUARTERLY = INVENTORY.QUARTERLY
                                   RIGHT JOIN (SELECT SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                                      SUM(UNITS) AS UNITS ,
                                                      QUARTERLY,
                                                      YEARLY
                                               FROM   #TEMP_GTS_DETAILS G
                                               GROUP  BY QUARTERLY,
                                                         YEARLY) GTS
                                           ON GTS.QUARTERLY = DEMAND.QUARTERLY
                                              AND GTS.YEARLY = DEMAND.YEARLY
                                   RIGHT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                      [QUARTER],
                                                      [YEAR],
                                                      SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                      ( ISNULL(SUM(NPS.PROJECTION_UNITS), 0) * ISNULL(AVG(U.ITEM_PRICE), 0) ) AS COGS_PROJECTED
                                               FROM   M_SALES_PROJECTION NPS
                                                      INNER JOIN PROJECTION_DETAILS PD
                                                              ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                      INNER JOIN CCP_DETAILS CCP
                                                              ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                      INNER JOIN #ITEM_PRICING U
                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                 AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                 AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                                      INNER JOIN PERIOD P
                                                              ON P.PERIOD_SID = NPS.PERIOD_SID
                                               WHERE  P.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD
                                               GROUP  BY [QUARTER],
                                                         [YEAR]) SALES
                                           ON SALES.[QUARTER] = GTS.QUARTERLY
                                              AND SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     [QUARTER],
                                                     [YEAR]
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              WHERE  EXISTS (SELECT 1
                                                             FROM   PROJECTION_DETAILS PD
                                                             WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                    AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                              GROUP  BY [QUARTER],
                                                        [YEAR]) DISCOUNT
                                          ON DISCOUNT.[QUARTER] = SALES.[QUARTER]
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     [QUARTER],
                                                     [YEAR]
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPP.PERIOD_SID
                                              WHERE  EXISTS (SELECT 1
                                                             FROM   PROJECTION_DETAILS PD
                                                             WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                    AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                              GROUP  BY [QUARTER],
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.[QUARTER] = SALES.[QUARTER]
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                            ORDER  BY SALES.[QUARTER],
                                      SALES.[YEAR]
                        END
                      ELSE IF ( @FREQUENCY = 'SEMI-ANNUALLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  SEMI_ANNUAL = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@ACTUAL_START_PERIOD, @PROJ_START_PERIOD_SID)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)

                            INSERT INTO #PIVOT_RESULT
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
                            SELECT @FIRST_PROJ_SID PROJECTION_ID,
                                   COALESCE(GTS.SEMI_ANNUAL, SALES.SEMI_ANNUAL),
                                   COALESCE(GTS.YEARLY, SALES.[YEAR]),
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                                   ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                                   ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   (ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )AS TOTAL_MANDATED_RPU_PROJECTED,
                                   ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                            FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                           SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                           SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                           SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
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
                                   RIGHT JOIN (SELECT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                      SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                      SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                      SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
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
                                   RIGHT JOIN (SELECT SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                                      SUM(GTS.UNITS) UNITS,
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
                                   RIGHT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                      SEMI_ANNUAL,
                                                      [YEAR],
                                                      SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                      ( ISNULL(SUM(NPS.PROJECTION_UNITS), 0) * ISNULL(AVG(NULLIF(U.ITEM_PRICE, 0)), 0) ) AS COGS_PROJECTED
                                               FROM   M_SALES_PROJECTION NPS
                                                      INNER JOIN PROJECTION_DETAILS PD
                                                              ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                      INNER JOIN CCP_DETAILS CCP
                                                              ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                      INNER JOIN #ITEM_PRICING U
                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                 AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                 AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                                      INNER JOIN PERIOD P
                                                              ON P.PERIOD_SID = NPS.PERIOD_SID
                                               WHERE  P.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD
                                               GROUP  BY [SEMI_ANNUAL],
                                                         [YEAR]) SALES
                                           ON SALES.[SEMI_ANNUAL] = GTS.SEMI_ANNUAL
                                              AND SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     [SEMI_ANNUAL],
                                                     [YEAR]
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              WHERE  EXISTS (SELECT 1
                                                             FROM   PROJECTION_DETAILS PD
                                                             WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                    AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                              GROUP  BY [SEMI_ANNUAL],
                                                        [YEAR]) DISCOUNT
                                          ON DISCOUNT.[SEMI_ANNUAL] = SALES.[SEMI_ANNUAL]
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     [SEMI_ANNUAL],
                                                     [YEAR]
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPP.PERIOD_SID
                                              WHERE  EXISTS (SELECT 1
                                                             FROM   PROJECTION_DETAILS PD
                                                             WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                    AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                              GROUP  BY [SEMI_ANNUAL],
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.[SEMI_ANNUAL] = SALES.[SEMI_ANNUAL]
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                            ORDER  BY SALES.SEMI_ANNUAL,
                                      SALES.YEAR
                        END
                      ELSE
                        BEGIN
                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)

                            INSERT INTO #PIVOT_RESULT
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
                            SELECT @FIRST_PROJ_SID                    PROJECTION_ID,
                                   COALESCE(GTS.YEARLY, SALES.[YEAR]) AS YEARS,
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                                   ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                                   ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   (ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )AS TOTAL_MANDATED_RPU_PROJECTED,
                                   ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                            FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                           SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                           SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                           SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
                                           YEARLY
                                    FROM   #INVENTORY
                                    GROUP  BY YEARLY) INVENTORY
                                   RIGHT JOIN (SELECT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                      SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                      SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                      SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
                                                      YEARLY
                                               FROM   #DEMAND
                                               GROUP  BY YEARLY) DEMAND
                                           ON DEMAND.YEARLY = INVENTORY.YEARLY
                                   RIGHT JOIN (SELECT SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                                      SUM(GTS.UNITS) UNITS,
                                                      GTS.YEARLY
                                               FROM   #TEMP_GTS_DETAILS GTS
                                               GROUP  BY GTS.YEARLY) GTS
                                           ON GTS.YEARLY = DEMAND.YEARLY
                                   RIGHT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                      [YEAR],
                                                      SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                      ( ISNULL(SUM(NPS.PROJECTION_UNITS), 0) * ISNULL(AVG(U.ITEM_PRICE), 0) ) AS COGS_PROJECTED
                                               FROM   M_SALES_PROJECTION NPS
                                                      INNER JOIN PROJECTION_DETAILS PD
                                                              ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                      INNER JOIN CCP_DETAILS CCP
                                                              ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                      INNER JOIN #ITEM_PRICING U
                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                 AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                 AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID
                                                      INNER JOIN PERIOD P
                                                              ON P.PERIOD_SID = NPS.PERIOD_SID
                                               WHERE  P.PERIOD_SID BETWEEN ISNULL(@PROJ_START_PERIOD_SID, 1) AND @ACTUAL_END_PERIOD
                                               GROUP  BY [YEAR]) SALES
                                           ON SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     [YEAR]
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              WHERE  EXISTS (SELECT 1
                                                             FROM   PROJECTION_DETAILS PD
                                                             WHERE  PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                    AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                              GROUP  BY [YEAR]) DISCOUNT
                                          ON DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     [YEAR]
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPP.PERIOD_SID
                                              WHERE  EXISTS (SELECT 1
                                                             FROM   PROJECTION_DETAILS PD
                                                             WHERE  PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                    AND PD.PROJECTION_MASTER_SID = @FIRST_PROJ_SID)
                                                     AND P.YEAR >= @ACTUAL_START_YEAR
                                              GROUP  BY [YEAR]) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                            ORDER  BY SALES.YEAR
                        END

                      IF ( @FREQUENCY = 'MONTHLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  MONTH = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)

                            INSERT INTO #PIVOT_RESULT
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
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                                   ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                                   ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   (ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )AS TOTAL_MANDATED_RPU_PROJECTED,
                                   ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                            FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                           SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                           SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                           SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
                                           MONTHLY,
                                           YEARLY,
                                           PERIOD_SID
                                    FROM   #INVENTORY
                                    GROUP  BY MONTHLY,
                                              YEARLY,
                                              PERIOD_SID) INVENTORY
                                   RIGHT JOIN (SELECT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                      SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                      SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                      SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
                                                      MONTHLY,
                                                      YEARLY,
                                                      PERIOD_SID
                                               FROM   #DEMAND
                                               GROUP  BY MONTHLY,
                                                         YEARLY,
                                                         PERIOD_SID) DEMAND
                                           ON INVENTORY.PERIOD_SID = DEMAND.PERIOD_SID
                                              AND INVENTORY.MONTHLY = DEMAND.MONTHLY
                                              AND INVENTORY.YEARLY = DEMAND.YEARLY
                                   RIGHT JOIN (SELECT PROJECTION_MASTER_SID,
                                                      GTS_SALES_ACTUALS,
                                                      GTS_SALES_PROJECTED,
                                                      UNITS,
                                                      PERIOD,
                                                      YEARLY,
                                                      PERIOD_SID
                                               FROM   (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                                              SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                                              SUM(UNITS) AS UNITS ,
                                                              MONTHLY PERIOD,
                                                              YEARLY,
                                                              PERIOD_SID
                                                       FROM   #TEMP_GTS_DETAILS G
                                                              INNER JOIN PERIOD P
                                                                      ON P.[MONTH] = G.MONTHLY
                                                                         AND P.[YEAR] = G.YEARLY
                                                       GROUP  BY MONTHLY,
                                                                 YEARLY,
                                                                 PERIOD_SID) A
                                                      CROSS JOIN (SELECT PM.PROJECTION_MASTER_SID
                                                                  FROM   #TEMP_PROJECTION_MASTER PM
                                                                  WHERE  ID <> 1) ACT) GTS
                                           ON DEMAND.PERIOD_SID = GTS.PERIOD_SID
                                   RIGHT JOIN (SELECT PROJECTION_MASTER_SID,
                                                      SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                      NPS.PERIOD_SID,
                                                      SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                      ( ISNULL(SUM(NPS.PROJECTION_UNITS), 0) * ISNULL(AVG(U.ITEM_PRICE), 0) ) AS COGS_PROJECTED
                                               FROM   M_SALES_PROJECTION NPS
                                                      INNER JOIN PROJECTION_DETAILS PD
                                                              ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                      INNER JOIN CCP_DETAILS CCP
                                                              ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                      INNER JOIN #ITEM_PRICING U
                                                              ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                 AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                 AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                  FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                  WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                               GROUP  BY PROJECTION_MASTER_SID,
                                                         NPS.PERIOD_SID) SALES
                                           ON SALES.PERIOD_SID = GTS.PERIOD_SID
                                   INNER JOIN PERIOD P
                                           ON P.PERIOD_SID = SALES.PERIOD_SID
                                              AND P.PERIOD_SID BETWEEN COALESCE(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD
                                   LEFT JOIN (SELECT PROJECTION_MASTER_SID,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     PERIOD_SID
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                AND NDP.SAVE_FLAG = 1
                                                                AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        PERIOD_SID) DISCOUNT
                                          ON DISCOUNT.PERIOD_SID = P.PERIOD_SID
                                             AND DISCOUNT.PROJECTION_MASTER_SID = SALES.PROJECTION_MASTER_SID
                                   LEFT JOIN (SELECT PROJECTION_MASTER_SID,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     PERIOD_SID
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        PERIOD_SID) SUPPLEMENTAL
                                          ON SUPPLEMENTAL.PERIOD_SID = SALES.PERIOD_SID
                                             AND SALES.PROJECTION_MASTER_SID = SUPPLEMENTAL.PROJECTION_MASTER_SID
                            ORDER  BY SALES.PERIOD_SID
                        END
                      ELSE IF ( @FREQUENCY = 'QUARTERLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  QUARTER = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@ACTUAL_START_PERIOD, @PROJ_START_PERIOD_SID)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)

                            INSERT INTO #PIVOT_RESULT
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
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                                   ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                                   ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   (ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )AS TOTAL_MANDATED_RPU_PROJECTED,
                                   ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                            FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                           SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                           SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                           SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
                                           QUARTERLY,
                                           YEARLY
                                    FROM   #INVENTORY
                                    GROUP  BY QUARTERLY,
                                              YEARLY) INVENTORY
                                   RIGHT JOIN (SELECT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                      SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                      SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                      SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
                                                      QUARTERLY,
                                                      YEARLY
                                               FROM   #DEMAND
                                               GROUP  BY QUARTERLY,
                                                         YEARLY) DEMAND
                                           ON DEMAND.YEARLY = INVENTORY.YEARLY
                                              AND DEMAND.QUARTERLY = INVENTORY.QUARTERLY
                                   RIGHT JOIN (SELECT PROJECTION_MASTER_SID,
                                                      GTS_SALES_ACTUALS,
                                                      GTS_SALES_PROJECTED,
                                                      UNITS,
                                                      QUARTERLY,
                                                      YEARLY
                                               FROM   (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                                              SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                                              SUM(UNITS) AS UNITS ,
                                                              QUARTERLY,
                                                              YEARLY
                                                       FROM   #TEMP_GTS_DETAILS G
                                                       GROUP  BY QUARTERLY,
                                                                 YEARLY) A
                                                      CROSS JOIN (SELECT PM.PROJECTION_MASTER_SID
                                                                  FROM   #TEMP_PROJECTION_MASTER PM
                                                                  WHERE  ID <> 1) ACT) GTS
                                           ON DEMAND.YEARLY = GTS.YEARLY
                                              AND DEMAND.QUARTERLY = GTS.QUARTERLY
                                   LEFT JOIN (SELECT PROJECTION_MASTER_SID,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                     [QUARTER],
                                                     [YEAR],
                                                     SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                     ( ISNULL(SUM(NPS.PROJECTION_UNITS), 0) * ISNULL(AVG(U.ITEM_PRICE), 0) ) AS COGS_PROJECTED
                                              FROM   M_SALES_PROJECTION NPS
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                     INNER JOIN CCP_DETAILS CCP
                                                             ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                     INNER JOIN #ITEM_PRICING U
                                                             ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPS.PERIOD_SID
                                              WHERE  P.PERIOD_SID BETWEEN COALESCE(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [QUARTER],
                                                        [YEAR]) SALES
                                          ON SALES.[QUARTER] = GTS.QUARTERLY
                                             AND SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     [QUARTER],
                                                     [YEAR],
                                                     PD.PROJECTION_MASTER_SID
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                AND NDP.SAVE_FLAG = 1
                                                                AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [QUARTER],
                                                        [YEAR]) DISCOUNT
                                          ON SALES.PROJECTION_MASTER_SID = DISCOUNT.PROJECTION_MASTER_SID
                                             AND DISCOUNT.[QUARTER] = SALES.[QUARTER]
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     [QUARTER],
                                                     [YEAR],
                                                     PROJECTION_MASTER_SID
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [QUARTER],
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SALES.PROJECTION_MASTER_SID = SUPPLEMENTAL.PROJECTION_MASTER_SID
                                             AND SUPPLEMENTAL.[QUARTER] = SALES.[QUARTER]
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                        END
                      ELSE IF ( @FREQUENCY = 'SEMI-ANNUALLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  SEMI_ANNUAL = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_PERIOD=COALESCE(@ACTUAL_START_PERIOD, @PROJ_START_PERIOD_SID)

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)

                            INSERT INTO #PIVOT_RESULT
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
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                                   ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                                   ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   (ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )AS TOTAL_MANDATED_RPU_PROJECTED,
                                   ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                            FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                           SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                           SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                           SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
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
                                   RIGHT JOIN (SELECT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                      SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                      SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                      SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
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
                                   RIGHT JOIN (SELECT PROJECTION_MASTER_SID,
                                                      GTS_SALES_ACTUALS,
                                                      GTS_SALES_PROJECTED,
                                                      UNITS,
                                                      SEMI_ANNUAL,
                                                      YEARLY
                                               FROM   (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                                              SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                                              SUM(GTS.UNITS) UNITS,
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
                                                                 GTS.YEARLY) A
                                                      CROSS JOIN (SELECT PM.PROJECTION_MASTER_SID
                                                                  FROM   #TEMP_PROJECTION_MASTER PM
                                                                  WHERE  ID <> 1) ACT) GTS
                                           ON DEMAND.SEMI_ANNUAL = GTS.SEMI_ANNUAL
                                              AND DEMAND.YEARLY = GTS.YEARLY
                                   LEFT JOIN (SELECT PROJECTION_MASTER_SID,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                     SEMI_ANNUAL,
                                                     [YEAR],
                                                     SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                     ( ISNULL(SUM(NPS.PROJECTION_UNITS), 0) * ISNULL(AVG(U.ITEM_PRICE), 0) ) AS COGS_PROJECTED
                                              FROM   M_SALES_PROJECTION NPS
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                     INNER JOIN CCP_DETAILS CCP
                                                             ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                     INNER JOIN #ITEM_PRICING U
                                                             ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPS.PERIOD_SID
                                              WHERE  P.PERIOD_SID BETWEEN COALESCE(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        SEMI_ANNUAL,
                                                        [YEAR]) SALES
                                          ON SALES.SEMI_ANNUAL = GTS.SEMI_ANNUAL
                                             AND SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     SEMI_ANNUAL,
                                                     [YEAR],
                                                     PROJECTION_MASTER_SID
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                AND NDP.SAVE_FLAG = 1
                                                                AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        SEMI_ANNUAL,
                                                        [YEAR]) DISCOUNT
                                          ON SALES.PROJECTION_MASTER_SID = DISCOUNT.PROJECTION_MASTER_SID
                                             AND DISCOUNT.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     SEMI_ANNUAL,
                                                     [YEAR],
                                                     PROJECTION_MASTER_SID
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        SEMI_ANNUAL,
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SALES.PROJECTION_MASTER_SID = SUPPLEMENTAL.PROJECTION_MASTER_SID
                                             AND SUPPLEMENTAL.SEMI_ANNUAL = SALES.SEMI_ANNUAL
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                        END
                      ELSE
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  YEAR = @ACTUAL_START_YEAR

                            SET @ACTUAL_START_YEAR=COALESCE(@ACTUAL_START_YEAR, (SELECT YEAR
                                                                                 FROM   PERIOD
                                                                                 WHERE  PERIOD_SID = @PROJ_START_PERIOD_SID))

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                      FROM   PERIOD P1
                      WHERE  EXISTS (SELECT DATEADD(DD, 1, EOMONTH(TO_DATE, -1))
                                            FROM   PROJECTION_MASTER P2
                                            WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID
											AND DATEADD(DD, 1, EOMONTH(P2.TO_DATE, -1))=P1.PERIOD_DATE)

                            INSERT INTO #PIVOT_RESULT
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
                                   COALESCE(GTS.[YEARLY], SALES.[YEAR])                             AS YEARS,
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS EX_FACTORY_SALES_PROJECTED,
                                   ISNULL(DEMAND.DEMAND_SALES_PROJECTED, 0) AS DEMAND_SALES_PROJECTED,
                                   ISNULL(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0) AS INVENTORY_WITHDRAWAL_SALES_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS EX_FACTORY_SALES_PROJECTED_PERCENT ,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(DEMAND.DEMAND_SALES_PROJECTED, 0), 0) ) * 100 AS DEMAND_SALES_PROJECTED_PERCENT,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(INVENTORY.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100 AS INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   (ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) )AS TOTAL_MANDATED_RPU_PROJECTED,
                                   ( ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_SUPPLEMENTAL_RPU,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ) AS TOTAL_RPU_PROJECTED,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED,
                                   ISNULL(SALES.COGS_PROJECTED, 0) AS COGS_PROJECTED,
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                          + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) - ( ISNULL(SALES.COGS_PROJECTED, 0) ) ) AS NET_PROFIT_PROJECTED
                            FROM   (SELECT SUM(ACT_AMOUNT_WITHDRAWN) AS ACT_AMOUNT_WITHDRAWN,
                                           SUM(ACT_UNITS_WITHDRAWN) AS ACT_UNITS_WITHDRAWN ,
                                           SUM(COALESCE(FOR_AMOUNT_WITHDRAWN, ACT_AMOUNT_WITHDRAWN)) AS FOR_AMOUNT_WITHDRAWN,
                                           SUM(FOR_UNITS_WITHDRAWN) AS FOR_UNITS_WITHDRAWN,
                                           YEARLY
                                    FROM   #INVENTORY
                                    GROUP  BY YEARLY) INVENTORY
                                   RIGHT JOIN (SELECT SUM(ACT_GROSS_AMOUNT) AS DEMAND_SALES_ACTUAL,
                                                      SUM(ACT_GROSS_UNITS) AS ACT_GROSS_UNITS,
                                                      SUM(COALESCE(FOR_GROSS_AMOUNT, ACT_GROSS_AMOUNT)) AS DEMAND_SALES_PROJECTED,
                                                      SUM(FOR_GROSS_UNITS) AS FOR_GROSS_UNITS,
                                                      YEARLY
                                               FROM   #DEMAND
                                               GROUP  BY YEARLY) DEMAND
                                           ON DEMAND.YEARLY = INVENTORY.YEARLY
                                   RIGHT JOIN (SELECT PROJECTION_MASTER_SID,
                                                      GTS_SALES_ACTUALS,
                                                      GTS_SALES_PROJECTED,
                                                      UNITS,
                                                      YEARLY
                                               FROM   (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                                              SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                                              SUM(GTS.UNITS) UNITS,
                                                              GTS.YEARLY
                                                       FROM   #TEMP_GTS_DETAILS GTS
                                                       GROUP  BY GTS.YEARLY) A
                                                      CROSS JOIN (SELECT PM.PROJECTION_MASTER_SID
                                                                  FROM   #TEMP_PROJECTION_MASTER PM
                                                                  WHERE  ID <> 1) ACT) GTS
                                           ON DEMAND.YEARLY = GTS.YEARLY
                                   LEFT JOIN (SELECT PROJECTION_MASTER_SID,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                     [YEAR],
                                                     SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED,
                                                     ( ISNULL(SUM(NPS.PROJECTION_UNITS), 0) * ISNULL(AVG(U.ITEM_PRICE), 0) ) AS COGS_PROJECTED
                                              FROM   M_SALES_PROJECTION NPS
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                                     INNER JOIN CCP_DETAILS CCP
                                                             ON PD.CCP_DETAILS_SID = CCP.CCP_DETAILS_SID
                                                     INNER JOIN #ITEM_PRICING U
                                                             ON CCP.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                                AND NPS.PERIOD_SID = U.PERIOD_SID
                                                                AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPS.PERIOD_SID
                                              WHERE  P.PERIOD_SID BETWEEN COALESCE(@PROJ_START_PERIOD_SID, @ACTUAL_START_PERIOD) AND @ACTUAL_END_PERIOD
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [YEAR]) SALES
                                          ON SALES.[YEAR] = GTS.YEARLY
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     [YEAR],
                                                     PROJECTION_MASTER_SID
                                              FROM   M_DISCOUNT_PROJECTION NDP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                                AND NDP.SAVE_FLAG = 1
                                                                AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NDP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [YEAR]) DISCOUNT
                                          ON SALES.PROJECTION_MASTER_SID = DISCOUNT.PROJECTION_MASTER_SID
                                             AND DISCOUNT.[YEAR] = SALES.[YEAR]
                                   LEFT JOIN (SELECT SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
                                                     SUM(PROJECTION_RPU) AS PROJECTED_RPU,
                                                     [YEAR],
                                                     PROJECTION_MASTER_SID
                                              FROM   M_SUPPLEMENTAL_DISC_PROJ NPP
                                                     INNER JOIN PROJECTION_DETAILS PD
                                                             ON PD.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                                                AND EXISTS (SELECT PM.PROJECTION_MASTER_SID
                                                                                                 FROM   #TEMP_PROJECTION_MASTER PM
                                                                                                 WHERE  ID <> 1 AND PD.PROJECTION_MASTER_SID=PM.PROJECTION_MASTER_SID)
                                                     INNER JOIN PERIOD P
                                                             ON P.PERIOD_SID = NPP.PERIOD_SID
                                              GROUP  BY PROJECTION_MASTER_SID,
                                                        [YEAR]) SUPPLEMENTAL
                                          ON SALES.PROJECTION_MASTER_SID = SUPPLEMENTAL.PROJECTION_MASTER_SID
                                             AND SUPPLEMENTAL.[YEAR] = SALES.[YEAR]
                        END

                      ------------- PIVOTING START
                      DECLARE @SQL       NVARCHAR(MAX),
                              @LOOP_CNTR INT,
                              @MAX_CCP   INT

                      SET @SQL = 'SELECT YEAR, PERIOD, '
                      SET @LOOP_CNTR = 1
                      SET @MAX_CCP = (SELECT MAX(ID)
                                      FROM   #TEMP_PROJECTION_MASTER)

                      WHILE @LOOP_CNTR <= @MAX_CCP
                        BEGIN
                            SET @SQL += '  EX_FACTORY_SALES_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_PROJECTED END),0),
									  DEMAND_SALES_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_PROJECTED END),0),
									  INVENTORY_WITHDRAWAL_SALES_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED END),0),
									  EX_FACTORY_SALES_PROJECTED_PERCENT_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN EX_FACTORY_SALES_PROJECTED_PERCENT END),0),
									  DEMAND_SALES_PROJECTED_PERCENT_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN DEMAND_SALES_PROJECTED_PERCENT END),0),
									  INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT_'
                                        + CAST( @LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT END),0),
									CONTRACT_SALES_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_SALES_PROJECTED END),0),
									CONTRACT_UNITS_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN CONTRACT_UNITS_PROJECTED END),0),
									MANDATED_DISCOUNT_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN MANDATED_DISCOUNT_PROJECTED END),0),
									SUPPLEMENTAL_DISCOUNT_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN SUPPLEMENTAL_DISCOUNT_PROJECTED END),0),
									MANDATED_DISCOUNT_PROJECTED_PERCENTAGE_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN MANDATED_DISCOUNT_PROJECTED_PERCENTAGE END),0),
									SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
									TOTAL_DISCOUNT_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' =ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT_PROJECTED END),0),
									TOTAL_DISCOUNT_PROJECTED_PERCENTAGE_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_DISCOUNT_PROJECTED_PERCENTAGE END),0),
									NET_SALES_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN NET_SALES_PROJECTED END),0),
									TOTAL_MANDATED_RPU_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_MANDATED_RPU END),0),
									TOTAL_SUPPLEMENTAL_RPU_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_SUPPLEMENTAL_RPU END),0),
							TOTAL_RPU_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN TOTAL_RPU_PROJECTED END),0),
									  COGS_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' = ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN COGS_PROJECTED END),0),
									  NET_PROFIT_PROJECTED_'
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' =ISNULL(MAX(CASE WHEN ID = '
                                        + CAST(@LOOP_CNTR AS VARCHAR(10))
                                        + ' THEN NET_PROFIT_PROJECTED END),0),'
                            SET @LOOP_CNTR += 1
                        END

                      SET @SQL = LEFT(@SQL, LEN(@SQL) - 1)
                      SET @SQL += ' FROM   #PIVOT_RESULT PR
					INNER JOIN #TEMP_PROJECTION_MASTER PM
					ON PR.PROJECTION_ID = PM.PROJECTION_MASTER_SID
					GROUP  BY [YEAR],PERIOD
					ORDER BY [YEAR],PERIOD'

                      EXEC SP_EXECUTESQL
                        @SQL
                  ------------- PIVOTING ENDS
                  END
                ELSE
                  BEGIN
                      IF ( @FREQUENCY = 'MONTHLY' )
                        BEGIN
                            SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  MONTH = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                            FROM   PERIOD P1
                            WHERE  EXISTS (SELECT DATEADD(MM, DATEDIFF(MM, 0, DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0)
                                                  FROM   PROJECTION_MASTER P2
                                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
												  AND DATEADD(MM, DATEDIFF(MM, 0, DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, P2.TO_DATE) + 1, 0))), 0)=P1.PERIOD_DATE)

                            SELECT @PROJECTION_MASTER_SID PROJECTION_ID,
                                   ISNULL(GTS.GTS_SALES_ACTUALS, 0) AS GTS_SALES_ACTUALS,
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS GTS_SALES_PROJECTED,
                                   P.[MONTH],
                                   P.[YEAR],
                                   ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS ,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                 + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS [% OF BUSINESS],
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS [% OF BUSINESS_PROJECTED],
                                   ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                   + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED
                            FROM   (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                           SUM(COALESCE(GTS_SALES_PROJECTED, GTS_SALES_ACTUALS)) AS GTS_SALES_PROJECTED,
                                           SUM(UNITS) AS UNITS ,
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
                                   RIGHT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                                      SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                      COALESCE(ACT.PERIOD_SID, PROJ.PERIOD_SID) AS PERIOD_SID,
                                                      SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS ,
                                                      SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
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
                                   LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
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
                                   LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
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
                            SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  QUARTER = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                            FROM   PERIOD P1
                            WHERE  EXISTS (SELECT DATEADD(MM, DATEDIFF(MM, 0, DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0)
                                                  FROM   PROJECTION_MASTER P2
                                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
												  AND DATEADD(MM, DATEDIFF(MM, 0, DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, P2.TO_DATE) + 1, 0))), 0)=P1.PERIOD_DATE)
							
							SELECT @PROJECTION_MASTER_SID PROJECTION_ID,
                                   ISNULL(GTS.GTS_SALES_ACTUALS, 0) AS GTS_SALES_ACTUALS,
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS GTS_SALES_PROJECTED,
                                   SALES.[QUARTER],
                                   SALES.[YEAR],
                                   ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS ,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                 + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS [% OF BUSINESS],
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS [% OF BUSINESS_PROJECTED],
                                   ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                   + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED
                            FROM   (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                           SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                           SUM(UNITS) AS UNITS ,
                                           QUARTERLY,
                                           YEARLY
                                    FROM   #TEMP_GTS_DETAILS G
                                           INNER JOIN #ITEM_INFO ACT
                                                   ON G.ITEMID = ACT.ITEM_ID
                                    GROUP  BY QUARTERLY,
                                              YEARLY) GTS
                                   RIGHT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                                      SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                      [QUARTER],
                                                      [YEAR],
                                                      SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS ,
                                                      SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
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
                                   LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
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
                                   LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
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
                            SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  SEMI_ANNUAL = @ACTUAL_START_FREQ
                                   AND YEAR = @ACTUAL_START_YEAR

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                            FROM   PERIOD P1
                            WHERE  EXISTS (SELECT DATEADD(MM, DATEDIFF(MM, 0, DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0)
                                                  FROM   PROJECTION_MASTER P2
                                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
												  AND DATEADD(MM, DATEDIFF(MM, 0, DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, P2.TO_DATE) + 1, 0))), 0)=P1.PERIOD_DATE)

                            SELECT @PROJECTION_MASTER_SID PROJECTION_ID,
                                   ISNULL(GTS.GTS_SALES_ACTUALS, 0) AS GTS_SALES_ACTUALS,
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS GTS_SALES_PROJECTED,
                                   SALES.[SEMI_ANNUAL],
                                   SALES.[YEAR],
                                   ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS ,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                 + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS [% OF BUSINESS],
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS [% OF BUSINESS_PROJECTED],
                                   ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                   + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED
                            FROM   (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                           SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                           SUM(GTS.UNITS) UNITS,
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
                                   RIGHT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                                      SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                      SEMI_ANNUAL,
                                                      [YEAR],
                                                      SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS ,
                                                      SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
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
                                   LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
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
                                   LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
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
                            SELECT @ACTUAL_START_PERIOD = MIN(PERIOD_SID)
                            FROM   PERIOD
                            WHERE  YEAR = @ACTUAL_START_YEAR

                            SELECT @ACTUAL_END_PERIOD = PERIOD_SID
                            FROM   PERIOD P1
                            WHERE  EXISTS (SELECT DATEADD(MM, DATEDIFF(MM, 0, DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, TO_DATE) + 1, 0))), 0)
                                                  FROM   PROJECTION_MASTER P2
                                                  WHERE  PROJECTION_MASTER_SID = @PROJECTION_MASTER_SID
												  AND DATEADD(MM, DATEDIFF(MM, 0, DATEADD(DAY, -1, DATEADD(QQ, DATEDIFF(QQ, 0, P2.TO_DATE) + 1, 0))), 0)=P1.PERIOD_DATE)

                            SELECT @PROJECTION_MASTER_SID PROJECTION_ID,
                                   ISNULL(GTS.GTS_SALES_ACTUALS, 0) AS GTS_SALES_ACTUALS,
                                   ISNULL(GTS.GTS_SALES_PROJECTED, 0) AS GTS_SALES_PROJECTED,
                                   SALES.[YEAR],
                                   ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) AS CONTRACT_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) AS CONTRACT_SALES_PROJECTED,
                                   ISNULL(SALES.CONTRACT_UNITS_ACTUALS, 0) AS CONTRACT_UNITS_ACTUALS,
                                   ISNULL(CONTRACT_UNITS_PROJECTED, 0) AS CONTRACT_UNITS_PROJECTED,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) AS MANDATED_DISCOUNT_ACTUALS,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) AS MANDATED_DISCOUNT_PROJECTED,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS SUPPLEMENTAL_DISCOUNT_ACTUALS ,
                                   ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS SUPPLEMENTAL_DISCOUNT_PROJECTED,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS MANDATED_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS MANDATED_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_ACTUALS_PERCENTAGE,
                                   ( COALESCE(ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS SUPPLEMENTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                           + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) AS TOTAL_DISCOUNT_ACTUAL,
                                   ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                              + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) AS TOTAL_DISCOUNT_PROJECTED,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                 + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100 AS TOTAL_DISCOUNT_ACTUAL_PERCENTAGE,
                                   ( ISNULL(( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                    + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100 AS TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                                   ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS_SALES_ACTUALS, 0), 0) ) * 100 AS [% OF BUSINESS],
                                   ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100 AS [% OF BUSINESS_PROJECTED],
                                   ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_ACTUALS, 0)
                                                                                                   + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_ACTUALS, 0) ) AS NET_SALES_ACTUALS,
                                   ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0) - ( ISNULL(DISCOUNT.CONTRACT_DISCOUNT_PROJECTED, 0)
                                                                                                       + ISNULL(SUPPLEMENTAL.CONTRACT_SUPPLEMENTAL_PROJECTED, 0) ) AS NET_SALES_PROJECTED
                            FROM   (SELECT SUM(GTS_SALES_ACTUALS) AS GTS_SALES_ACTUALS,
                                           SUM(GTS_SALES_PROJECTED) AS GTS_SALES_PROJECTED,
                                           SUM(GTS.UNITS) UNITS,
                                           GTS.YEARLY
                                    FROM   #TEMP_GTS_DETAILS GTS
                                           INNER JOIN #ITEM_INFO ACT
                                                   ON GTS.ITEMID = ACT.ITEM_ID
                                    GROUP  BY GTS.YEARLY) GTS
                                   RIGHT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SALES_ACTUALS,
                                                      SUM(PROJECTION_SALES) AS CONTRACT_SALES_PROJECTED,
                                                      [YEAR],
                                                      SUM(ACTUAL_UNITS) AS CONTRACT_UNITS_ACTUALS ,
                                                      SUM(PROJECTION_UNITS) AS CONTRACT_UNITS_PROJECTED
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
                                   LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_DISCOUNT_ACTUALS,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_DISCOUNT_PROJECTED,  
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
                                   LEFT JOIN (SELECT SUM(ACTUAL_SALES) AS CONTRACT_SUPPLEMENTAL_ACTUALS,
                                                     SUM(PROJECTION_SALES) AS CONTRACT_SUPPLEMENTAL_PROJECTED,
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

          DROP TABLE #TEMP_GTS_DETAILS
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
