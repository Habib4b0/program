IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_PROJECTION_VARIANCE'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_PROJECTION_VARIANCE]
  END

GO
CREATE PROCEDURE [dbo].[PRC_PROJECTION_VARIANCE] (@PROJECTION_SID         VARCHAR(500),
                                                 @USER_ID                INT,
                                                 @SESSION_ID             VARCHAR(50),
                                                 @DISCOUNT_LEVEL         VARCHAR(50),
                                                 @FREQUENCY              CHAR(1),
                                                 @CP_INDICATOR           CHAR(1),
                                                 @GROUP_FILTER           VARCHAR(50),
                                                 @GROUP_FILTER_VALUE     VARCHAR(50),
                                                 @HIERARCHY_NO           VARCHAR(500) = NULL,
                                                 @LEVEL_NO               INT = NULL,
                                                 @VIEW                   VARCHAR(50),
                                                 @CUSTOM_VIEW_MASTER_SID INT = NULL,
                                                 @VIEW_TAB               VARCHAR(50),
                                                 @UOM_CODE               VARCHAR(10),
                                                 @SALES_INCLUSION        BIT,
                                                 @DEDUCTION_INCLUSION    BIT,
                                                 @FILTER_UDCS            VARCHAR(MAX) = NULL,
                                                 @SCREEN_NAME            VARCHAR(50)=NULL)
AS
  BEGIN
      DECLARE @FROM_DATE                     DATE,
              @STARTFROM                     DATE,
              @PROJECTION_DATE               DATE,
              @ACTUALPERIODS                 INT,
              @PROJECTIONPERIODS             INT,
              @NDC8                          VARCHAR(200),
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
              @DED_RELATIONSHIP_BUILDER_SID  INT,
              @BUSINESS_UNIT                 INT -----------GAL-808
              ,
              @COMPANY                       INT -----GAL-808
      DECLARE @D_MASTER_TABLE     VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJ_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @D_ACTUAL_TABLE     VARCHAR(200) = Concat ('ST_NM_ACTUAL_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @D_PROJECTION_TABLE VARCHAR(200) = Concat ('ST_NM_DISCOUNT_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
      DECLARE @S_MASTER_TABLE     VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @S_ACTUAL_TABLE     VARCHAR(200) = Concat ('ST_NM_ACTUAL_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @S_PROJECTION_TABLE VARCHAR(200) = Concat ('ST_NM_SALES_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @P_PROJECTION_TABLE VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @P_MASTER_TABLE     VARCHAR(200) = Concat ('ST_NM_PPA_PROJECTION_MASTER_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @P_ACTUAL_TABLE     VARCHAR(200) = Concat ('ST_NM_ACTUAL_PPA_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @CCP_HIERARCHY      VARCHAR(200) = Concat ('ST_CCP_HIERARCHY_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @PRODUCT_TABLE      VARCHAR(MAX) = Concat ('ST_PRODUCT_FILE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @ITEM_UOM_TABLE     VARCHAR(MAX) = Concat ('ST_ITEM_UOM_DETAILS_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
      DECLARE @CUSTOMER_TABLE_SALES VARCHAR(200) = Concat('ST_CUSTOMER_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
      DECLARE @PRODUCT_TABLE_SALES VARCHAR(200) = Concat ('ST_PRODUCT_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @CUSTOM_TABLE_SALES  VARCHAR(200) = Concat ('ST_CUSTOM_SALES_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
      DECLARE @CUSTOMER_TABLE_DISCOUNT VARCHAR(200) = Concat('ST_CUSTOMER_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', ''))
      DECLARE @PRODUCT_TABLE_DISCOUNT VARCHAR(200) = Concat ('ST_PRODUCT_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @STATUS_TABLE           VARCHAR(200) = Concat ('ST_STATUS_TABLE_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
              @CUSTOM_TABLE_DISCOUNT  VARCHAR(200) = Concat ('ST_CUSTOM_DISCOUNT_', @USER_ID, '_', @SESSION_ID, '_', Replace(CONVERT(VARCHAR(50), Getdate(), 2), '.', '')),
			  @CUSTOM_CCP_SALES VARCHAR(200) = CONCAT ('CUSTOM_CCP_SALES_',@CUSTOM_VIEW_MASTER_SID),
              @sales_table            VARCHAR(200),
              @discount_table         VARCHAR(200)
      DECLARE @SQL1 NVARCHAR(MAX)

      IF @CP_INDICATOR = 'c'
        BEGIN
            SET @sales_table=@CUSTOMER_TABLE_SALES
            SET @discount_table=@CUSTOMER_TABLE_DISCOUNT
        END
      ELSE IF @CP_INDICATOR = 'P'
        BEGIN
            SET @sales_table=@PRODUCT_TABLE_SALES
            SET @discount_table=@PRODUCT_TABLE_DISCOUNT
        END
      ELSE IF @CP_INDICATOR NOT IN ( 'c', 'p' )
        BEGIN
            SET @sales_table=@CUSTOM_TABLE_SALES
            SET @discount_table=@CUSTOM_TABLE_DISCOUNT
        END

      IF Object_id('TEMPDB..#CCP_DETAILS_TEMP') IS NOT NULL
        DROP TABLE #CCP_DETAILS_TEMP

      CREATE TABLE #CCP_DETAILS_TEMP
        (
           CCP_DETAILS_SID     INT,
           COMPANY_MASTER_SID  INT,
           ITEM_MASTER_SID     INT,
           CONTRACT_MASTER_SID INT
        )

      EXEC ( 'INSERT INTO #CCP_DETAILS_TEMP(CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID)

                                           SELECT CH.CCP_DETAILS_SID,COMPANY_MASTER_SID,ITEM_MASTER_SID,CONTRACT_MASTER_SID FROM ' + @CCP_HIERARCHY + ' CH JOIN CCP_DETAILS CD ON CH.CCP_DETAILS_SID = CD.CCP_DETAILS_SID ' )

      IF Object_id('TEMPDB..#ITEM_UOM_DETAILS') IS NOT NULL
        DROP TABLE #ITEM_UOM_DETAILS

      CREATE TABLE #ITEM_UOM_DETAILS
        (
           ITEM_MASTER_SID INT,
           UOM_VALUE       NUMERIC(38, 6),
           UOM_CODE        VARCHAR(50)
        )

      SET @SQL1 = 'INSERT INTO #ITEM_UOM_DETAILS

(ITEM_MASTER_SID,UOM_VALUE,UOM_CODE)

SELECT ITEM_MASTER_SID,UOM_VALUE,UOM_CODE FROM '
                  + @ITEM_UOM_TABLE
                  + ' WHERE UOM_CODE=@UOM_CODE'

      EXEC Sp_executesql
        @SQL1,
        N'@UOM_CODE VARCHAR(50)',
        @UOM_CODE = @UOM_CODE

      IF Object_id('TEMPDB..#MULTISELECT_DISCOUNTS') IS NOT NULL
        DROP TABLE #MULTISELECT_DISCOUNTS;

      CREATE TABLE #MULTISELECT_DISCOUNTS
        (
           CCP_DETAILS_SID     INT,
           RS_CONTRACT_SID     INT,
           PRICE_GROUP_TYPE    VARCHAR(50),
           CHECK_RECORD        BIT,
           PV_FILTERS          BIT,
           SELECTED_LEVEL      VARCHAR(8000),
           RS_MODEL_SID        INT,
           DEDUCTION_INCLUSION BIT,
           SALES_INCLUSION     BIT,
           --FILTER_CCP BIT,
           SELECTED_SID        INT
        )

      SET @SQL1 = Concat ('INSERT INTO #MULTISELECT_DISCOUNTS (

       CCP_DETAILS_SID

       ,RS_CONTRACT_SID

       ,PRICE_GROUP_TYPE

       ,CHECK_RECORD

       ,PV_FILTERS

       ,RS_MODEL_SID

       ,DEDUCTION_INCLUSION,SALES_INCLUSION--,FILTER_CCP

       )
	   
SELECT DISTINCT A.CCP_DETAILS_SID

       ,A.RS_CONTRACT_SID

       ,A.PRICE_GROUP_TYPE

       ,A.CHECK_RECORD

       ,A.PV_FILTERS

       ,A.RS_MODEL_SID

       ,A.DEDUCTION_INCLUSION

       ,B1.SALES_INCLUSION--,A.FILTER_CCP

FROM  ', @D_MASTER_TABLE, ' A JOIN ', @S_MASTER_TABLE, ' B1 ON A.CCP_DETAILS_SID=B1.CCP_DETAILS_SID 

WHERE --- CASE WHEN @SCREEN_NAME IN (''SALES'' ,''DISCOUNT'') THEN  1 ELSE PV_FILTERS END = 1 AND

 EXISTS (SELECT 1 FROM #CCP_DETAILS_TEMP B WHERE A.CCP_DETAILS_SID=B.CCP_DETAILS_SID)')

      EXEC Sp_executesql
        @SQL1,
        N'@SCREEN_NAME VARCHAR(50)',
        @SCREEN_NAME = @SCREEN_NAME

      IF Object_id('TEMPDB..#HELPER_TABLE') IS NOT NULL
        DROP TABLE #HELPER_TABLE;

      CREATE TABLE #HELPER_TABLE
        (
           HELPER_TABLE_SID INT,
           DESCRIPTION      VARCHAR(100)
        )

      INSERT INTO #HELPER_TABLE
                  (HELPER_TABLE_SID,
                   DESCRIPTION)
      SELECT HELPER_TABLE_SID,
             DESCRIPTION
      FROM   HELPER_TABLE HT
      WHERE  ( EXISTS (SELECT 1
                       FROM   Udf_splitstring(@FILTER_UDCS, ',') A
                       WHERE  A.TOKEN = CONVERT(VARCHAR(100), HT.HELPER_TABLE_SID))
                OR @FILTER_UDCS IS NULL )
             AND @DISCOUNT_LEVEL <> 'PROGRAM'
             AND @DISCOUNT_LEVEL <> 'SCHEDULE ID'

      IF ( @DISCOUNT_LEVEL LIKE '%UDC%' )
        BEGIN
            UPDATE A
            SET    A.SELECTED_LEVEL = HT.DESCRIPTION,
                   SELECTED_SID = CASE @DISCOUNT_LEVEL
                                    WHEN 'UDC 1' THEN UDC1
                                    WHEN 'UDC 2' THEN UDC2
                                    WHEN 'UDC 3' THEN UDC3
                                    WHEN 'UDC 4' THEN UDC4
                                    WHEN 'UDC 5' THEN UDC5
                                    WHEN 'UDC 6' THEN UDC6
                                  END
            FROM   #MULTISELECT_DISCOUNTS A
                   INNER JOIN UDCS B
                           ON B.MASTER_TYPE = 'RS_CONTRACT'
                              AND A.RS_CONTRACT_SID = B.MASTER_SID
                   INNER JOIN #HELPER_TABLE HT
                           ON HT.HELPER_TABLE_SID = CASE @DISCOUNT_LEVEL
                                                      WHEN 'UDC 1' THEN UDC1
                                                      WHEN 'UDC 2' THEN UDC2
                                                      WHEN 'UDC 3' THEN UDC3
                                                      WHEN 'UDC 4' THEN UDC4
                                                      WHEN 'UDC 5' THEN UDC5
                                                      WHEN 'UDC 6' THEN UDC6
                                                    END
        END
      ELSE IF ( @DISCOUNT_LEVEL = 'PROGRAM'
            OR @DISCOUNT_LEVEL = 'SCHEDULE ID' )
        BEGIN
            UPDATE A
            SET    A.SELECTED_LEVEL = Iif(@DISCOUNT_LEVEL = 'PROGRAM', RS_NAME, RS_ID),
                   SELECTED_SID = B.RS_CONTRACT_SID
            FROM   #MULTISELECT_DISCOUNTS A
                   INNER JOIN RS_CONTRACT B
                           ON A.RS_CONTRACT_SID = B.RS_CONTRACT_SID
            WHERE  ( EXISTS (SELECT 1
                             FROM   Udf_splitstring(@FILTER_UDCS, ',') A
                             WHERE  A.TOKEN = CONVERT(VARCHAR(100), B.RS_CONTRACT_SID))
                      OR @FILTER_UDCS IS NULL )
        END
      ELSE
        BEGIN
            UPDATE A
            SET    A.SELECTED_LEVEL = HT.DESCRIPTION,
                   SELECTED_SID = CASE @DISCOUNT_LEVEL
                                    WHEN 'SCHEDULE CATEGORY' THEN RS_CATEGORY
                                    WHEN 'SCHEDULE TYPE' THEN RS_TYPE
                                    WHEN 'PROGRAM CATEGORY' THEN REBATE_PROGRAM_TYPE
                                    WHEN 'PROGRAM TYPE' THEN REBATE_PROGRAM_TYPE
                                  END
            FROM   #MULTISELECT_DISCOUNTS A
                   INNER JOIN RS_CONTRACT B
                           ON A.RS_CONTRACT_SID = B.RS_CONTRACT_SID
                   INNER JOIN #HELPER_TABLE HT
                           ON HT.HELPER_TABLE_SID = CASE @DISCOUNT_LEVEL
                                                      WHEN 'SCHEDULE CATEGORY' THEN RS_CATEGORY
                                                      WHEN 'SCHEDULE TYPE' THEN RS_TYPE
                                                      WHEN 'PROGRAM CATEGORY' THEN REBATE_PROGRAM_TYPE
                                                      WHEN 'PROGRAM TYPE' THEN REBATE_PROGRAM_TYPE
                                                    END
        END

      DELETE FROM #MULTISELECT_DISCOUNTS
      WHERE  SELECTED_LEVEL IS NULL
             AND @SCREEN_NAME NOT IN ( 'SALES', 'DISCOUNT' )

      ---select max(level_no from cust_view_details where CUSTOM_VIEW_MASTER_SID=@CUSTOM_VIEW_MASTER_SID
      IF Object_id('TEMPDB..#PROJECTION_MASTER') IS NOT NULL
        TRUNCATE TABLE #PROJECTION_MASTER
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
             PROJECTION_ID                                      INT,
             HIERARCHY_NO                                       VARCHAR(8000),
             PARENT_HIERARCHY_NO                                VARCHAR(8000),
             LEVEL_NAME                                         VARCHAR(100),
             level_no                                           INT,
             [PERIOD]                                           SMALLINT,
             [YEAR]                                             INT,
             CP_INDICATOR                                       CHAR(1),
             EX_FACTORY_SALES_ACCRUALS                          NUMERIC(38, 6),
             EX_FACTORY_SALES_ACTUALS                           NUMERIC(38, 6),
             EX_FACTORY_SALES_PROJECTED                         NUMERIC(38, 6),
             DEMAND_SALES_ACCRUALS                              NUMERIC(38, 6),
             DEMAND_SALES_ACTUALS                               NUMERIC(38, 6),
             DEMAND_SALES_PROJECTED                             NUMERIC(38, 6),
             INVENTORY_WITHDRAWAL_SALES_ACCRUALS                NUMERIC(38, 6),
             INVENTORY_WITHDRAWAL_SALES_ACTUALS                 NUMERIC(38, 6),
             INVENTORY_WITHDRAWAL_SALES_PROJECTED               NUMERIC(38, 6),
             EX_FACTORY_SALES_ACCRUAL_PERCENT                   NUMERIC(38, 6),
             EX_FACTORY_SALES_ACTUALS_PERCENT                   NUMERIC(38, 15),
             EX_FACTORY_SALES_PROJECTED_PERCENT                 NUMERIC(38, 15),
             DEMAND_SALES_ACCRUAL_PERCENT                       NUMERIC(38, 6),
             DEMAND_SALES_ACTUALS_PERCENT                       NUMERIC(38, 15),
             DEMAND_SALES_PROJECTED_PERCENT                     NUMERIC(38, 15),
             INVENTORY_WITHDRAWAL_SALES_ACCRUAL_PERCENT         NUMERIC(38, 6),
             INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT         NUMERIC(38, 15),
             INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT       NUMERIC(38, 15),
             CONTRACT_SALES_ACCRUAL                             NUMERIC(38, 6),
             CONTRACT_SALES_ACTUALS                             NUMERIC(38, 15),
             CONTRACT_SALES_PROJECTED                           NUMERIC(38, 15),
             CONTRACT_UNITS_ACCRUAL                             NUMERIC(38, 6),
             CONTRACT_UNITS_ACTUALS                             NUMERIC(38, 15),
             CONTRACT_UNITS_PROJECTED                           NUMERIC(38, 15),
             TOTAL_DISCOUNT_ACCRUAL                             NUMERIC(38, 6),
             TOTAL_DISCOUNT_ACTUALS                             NUMERIC(38, 15),
             TOTAL_DISCOUNT_PROJECTED                           NUMERIC(38, 15),
             TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE                  NUMERIC(38, 6),
             TOTAL_DISCOUNT_ACTUALS_PERCENTAGE                  NUMERIC(38, 15),
             TOTAL_DISCOUNT_PROJECTED_PERCENTAGE                NUMERIC(38, 15),
             TOTAL_RPU_ACCRUAL                                  NUMERIC(38, 6),
             TOTAL_RPU_ACTUALS                                  NUMERIC(38, 6),
             TOTAL_RPU_PROJECTED                                NUMERIC(38, 6),
             NET_SALES_ACCRUAL                                  NUMERIC(38, 6),
             NET_SALES_ACTUALS                                  NUMERIC(38, 15),
             NET_SALES_PROJECTED                                NUMERIC(38, 15),
             COGS_ACCRUAL                                       NUMERIC(38, 6),
             COGS_ACTUALS                                       NUMERIC(38, 6),
             COGS_PROJECTED                                     NUMERIC(38, 6),
             NET_PROFIT_ACCRUAL                                 NUMERIC(38, 6),
             NET_PROFIT_ACTUALS                                 NUMERIC(38, 6),
             NET_PROFIT_PROJECTED                               NUMERIC(38, 6),
             NET_SALES_OF_EX_FACTORY_ACCRUAL                    NUMERIC(38, 6),
             NET_SALES_OF_EX_FACTORY_ACTUALS                    NUMERIC(38, 15),
             NET_SALES_OF_EX_FACTORY_PROJECTED                  NUMERIC(38, 15),
             DISCOUNT_OF_EX_FACTORY_ACCRUAL                     NUMERIC(38, 6),
             DISCOUNT_OF_EX_FACTORY_ACTUALS                     NUMERIC(38, 6),
             DISCOUNT_OF_EX_FACTORY_PROJECTED                   NUMERIC(38, 6),
             NET_EX_FACTORY_SALES_ACCRUAL                       NUMERIC(38, 6),
             NET_EX_FACTORY_SALES_ACTUALS                       NUMERIC(38, 6),
             NET_EX_FACTORY_SALES_PROJECTED                     NUMERIC(38, 6),
             NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS   NUMERIC(38, 6),
             NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED NUMERIC(38, 6),
             NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACCRUAL   NUMERIC(38, 6)
          )

      DECLARE @STAT_SALES_SID INT
      DECLARE @END_SALES_SID INT
      DECLARE @PROJ_START_SID INT
      DECLARE @PROJ_END_SID INT
      DECLARE @SQL_DT11 NVARCHAR(MAX)

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
              @PROJ_START_PERIOD_SID         INT,
              @START_DATE                    DATETIME,
              @PROD_RELATIONSHIP_BUILDER_SID INT,
              @MIN_LEVEL                     INT,
              @CURRENT_DATE                  DATE,
              @VERSION_NO                    INT,
              @PROJECTION_CUST_VERSION       INT,
              @PROJECTION_PROD_VERSION       INT

      SELECT @FIRST_PROJ_SID = PM.PROJECTION_MASTER_SID
      FROM   #PROJECTION_MASTER PM
      WHERE  ID = 1

      SELECT @STAT_SALES_SID = Max(CASE
                                     WHEN PERIOD_DATE = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0) THEN PERIOD_SID
                                   END),
             @END_SALES_SID = Max(CASE
                                    WHEN PERIOD_DATE = Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0) THEN PERIOD_SID
                                  END),
             @PROJ_START_SID = Max(CASE
                                     WHEN PERIOD_DATE = Datefromparts(Year(FROM_DATE), 1, 1) THEN PERIOD_SID
                                   END),
             @PROJ_END_SID = Max(CASE
                                   WHEN PERIOD_DATE = Datefromparts(Year(TO_DATE), 12, 1) THEN PERIOD_SID
                                 END)
      FROM   PROJECTION_MASTER
             INNER JOIN PERIOD P
                     ON PERIOD_DATE IN ( Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0), Dateadd(QQ, Datediff(QQ, 0, Getdate()), 0), Datefromparts(Year(FROM_DATE), 1, 1), Datefromparts(Year(TO_DATE), 12, 1) )
      WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID

      IF Object_id('TEMPDB.DBO.#PERIOD', 'U') IS NOT NULL
        DROP TABLE #PERIOD;

      SELECT PERIOD_SID,
             PERIOD_DATE,
             MONTH,
             QUARTER,
             SEMI_ANNUAL,
             YEAR,
             PERIOD = CASE
                        WHEN LEFT(@FREQUENCY, 1) = 'M' THEN MONTH
                        WHEN LEFT(@FREQUENCY, 1) = 'Q' THEN QUARTER
                        WHEN LEFT(@FREQUENCY, 1) = 'S' THEN SEMI_ANNUAL
                        ELSE 01
                      END
      INTO   #PERIOD
      FROM   PERIOD
      WHERE  PERIOD_SID BETWEEN @STAT_SALES_SID AND @PROJ_END_SID

      --WHERE  PERIOD_SID BETWEEN 613 AND 618
      SELECT TOP 1 @STARTFROM = Dateadd(YY, Datediff(YY, 0, Dateadd(YY, -3, Getdate())), 0),
                   @START_DATE = Dateadd(DD, 1, Eomonth(FROM_DATE, -1)),
                   @PROJECTION_DATE = Dateadd(DD, 1, Eomonth(TO_DATE, -1)),
                   @CUST_RELATIONSHIP_BUILDER_SID = CUST_RELATIONSHIP_BUILDER_SID,
                   @PROD_RELATIONSHIP_BUILDER_SID = PROD_RELATIONSHIP_BUILDER_SID,
                   @DED_RELATIONSHIP_BUILDER_SID = DED_RELATIONSHIP_BULDER_SID,
                   @MIN_LEVEL = CASE @CP_INDICATOR
                                  WHEN 'C' THEN CUSTOMER_HIERARCHY_LEVEL
                                  WHEN 'P' THEN PRODUCT_HIERARCHY_LEVEL
                                END,
                   @BUSINESS_UNIT = BUSINESS_UNIT,
                   -----GAL-808
                   @COMPANY = COMPANY_MASTER_SID -----GAL-808
                   ,
                   @PROJECTION_CUST_VERSION = PROJECTION_CUST_VERSION,
                   @PROJECTION_PROD_VERSION = PROJECTION_PROD_VERSION
      FROM   PROJECTION_MASTER
      WHERE  PROJECTION_MASTER_SID = @FIRST_PROJ_SID

      SELECT @START_PERIOD_SID = Max(CASE
                                       WHEN PERIOD_DATE = @STARTFROM THEN PERIOD_SID
                                     END),
             @END_PERIOD_SID = Max(CASE
                                     WHEN PERIOD_DATE = Dateadd(MM, Datediff(MM, 0, @PROJECTION_DATE), 0) THEN PERIOD_SID
                                   END),
             @PROJ_START_PERIOD_SID = Max(CASE
                                            WHEN PERIOD_DATE = @START_DATE THEN PERIOD_SID
                                          END)
      FROM   #PERIOD
      WHERE  PERIOD_DATE IN ( @STARTFROM, @PROJECTION_DATE, @START_DATE )

      SET @ACTUALPERIODS = Datediff(YY, @FROM_DATE, @STARTFROM)
      SET @PROJECTIONPERIODS = Datediff(YY, @STARTFROM, @PROJECTION_DATE)

      IF Object_id('TEMPDB..#CCP') IS NOT NULL
        DROP TABLE #CCP

      CREATE TABLE #CCP
        (
           CCP_DETAILS_SID     INT,
           HIERARCHY_NO        VARCHAR(8000),
           LEVEL_NAME          VARCHAR(100),
           PARENT_HIERARCHY_NO VARCHAR(8000),
           LEVEL_NO            INT,
           RS_CONTRACT_SID     INT,
           rowid               INT
        )

      IF @CP_INDICATOR IN ( 'C', 'P' )
        BEGIN
            DECLARE @RELATIONSHIP_BUILDER_SID INT

            SET @RELATIONSHIP_BUILDER_SID = CASE @CP_INDICATOR
                                              WHEN 'C' THEN @CUST_RELATIONSHIP_BUILDER_SID
                                              ELSE @PROD_RELATIONSHIP_BUILDER_SID
                                            END
            SET @VERSION_NO = CASE @CP_INDICATOR
                                WHEN 'C' THEN @PROJECTION_CUST_VERSION
                                ELSE @PROJECTION_PROD_VERSION
                              END
            SET @SQL1 = Concat ('SELECT DISTINCT HIERARCHY_NO,

       CH.CCP_DETAILS_SID,

       LEVEL_NAME,RLD.LEVEL_NO,HIERARCHY_NO AS PARENT_HIERARCHY_NO

FROM   RELATIONSHIP_LEVEL_DEFINITION RLD

       JOIN ', @CCP_HIERARCHY, ' CH

         ON ( CH.CUST_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''

              AND @CP_INDICATOR = ''C'' )

             OR ( CH.PROD_HIERARCHY_NO LIKE RLD.HIERARCHY_NO + ''%''

                  AND @CP_INDICATOR = ''P'' )

  ', CASE @GROUP_FILTER
                               WHEN 'SALES' THEN '

                                         JOIN '
                                                 + @S_MASTER_TABLE
                                                 + ' S ON S.CCP_DETAILS_SID = CH.CCP_DETAILS_SID'
                               WHEN 'DISCOUNT' THEN '

                                         JOIN '
                                                    + @D_MASTER_TABLE
                                                    + ' S ON S.CCP_DETAILS_SID = CH.CCP_DETAILS_SID'
                               WHEN 'PPA' THEN '

                                         JOIN '
                                               + @P_MASTER_TABLE
                                               + ' S ON S.CCP_DETAILS_SID = CH.CCP_DETAILS_SID'
                             END, CASE
                                    WHEN @SCREEN_NAME IS NULL THEN ' JOIN ' + @D_MASTER_TABLE
                                                                   + ' D ON S.CCP_DETAILS_SID = D.CCP_DETAILS_SID  AND PV_FILTERS=1 '
                                  END, '

                        

                WHERE  (S.USER_GROUP = ''', @GROUP_FILTER_VALUE, ''' OR ''ALL'' = ''', @GROUP_FILTER_VALUE, ''')

AND  RLD.RELATIONSHIP_BUILDER_SID =  ', @RELATIONSHIP_BUILDER_SID, '

AND RLD.VERSION_NO= ', @VERSION_NO, '

AND  RLD.LEVEL_NO >= ', @MIN_LEVEL, ' ')

            INSERT INTO #CCP
                        (HIERARCHY_NO,
                         CCP_DETAILS_SID,
                         LEVEL_NAME,
                         LEVEL_NO,
                         PARENT_HIERARCHY_NO)
            EXEC Sp_executesql
              @SQL1,
              N'@CP_INDICATOR CHAR(1)',
              @CP_INDICATOR = @CP_INDICATOR
        END
      ELSE
        BEGIN
            DECLARE @CUSTOM_SQL NVARCHAR(MAX)=''

            SET @CUSTOM_SQL=Concat('select c.HIERARCHY_NO
   ,c.CCP_DETAILS_SID
,c.LEVEL_NO
,c.RS_CONTRACT_SID
,c.ROWID,HIERARCHY_NO PARENT_HIERARCHY_NO from  ',@CUSTOM_CCP_SALES,' C
                  join ', @CCP_HIERARCHY, ' C1
                     on c1.CCP_DETAILS_SID = c.CCP_DETAILS_SID 
					 where c.CUST_VIEW_MASTER_SID=', @CUSTOM_VIEW_MASTER_SID, ' and exists (select 1 from #MULTISELECT_DISCOUNTS md where md.CCP_DETAILS_SID = c.CCP_DETAILS_SID
					 and (md.RS_CONTRACT_SID = c.RS_CONTRACT_SID or c.RS_CONTRACT_SID=0))')

            INSERT INTO #CCP
                        (HIERARCHY_NO,
                         CCP_DETAILS_SID,
                         LEVEL_NO,
                         RS_CONTRACT_SID,
                         ROWID,
                         PARENT_HIERARCHY_NO)
            EXEC Sp_executesql
              @CUSTOM_SQL
        END

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

      DECLARE @D_SQL NVARCHAR(MAX)

      --SELECT GETDATE() AS DI
      IF Object_id('TEMPDB..#DISCOUNT_INFO') IS NOT NULL
        DROP TABLE #DISCOUNT_INFO

      CREATE TABLE #DISCOUNT_INFO
        (
           TOKEN        VARCHAR(100),
           DISCOUNT     VARCHAR(100),
           SELECTED_SID VARCHAR(50)
        )

      SET @D_SQL = Concat ('INSERT INTO #DISCOUNT_INFO (

       TOKEN

       ,DISCOUNT

       ,SELECTED_SID

       )

SELECT DISTINCT CASE

              WHEN ''', @DISCOUNT_LEVEL, ''' = ''PROGRAM''

                     THEN CAST(RS_CONTRACT_SID AS VARCHAR(50))

              ELSE SELECTED_LEVEL

              END AS TOKEN

       ,SELECTED_LEVEL AS DISCOUNT

       ,SELECTED_SID

FROM #MULTISELECT_DISCOUNTS

WHERE
SELECTED_SID IS NOT NULL


')

      EXEC (@D_SQL)

      DECLARE @SCHEDULE_ID_LEVEL_NO INT,
              @SCHEDULE_ID_LEVEL    BIT=0

      SET @SCHEDULE_ID_LEVEL_NO=(SELECT DISTINCT LEVEL_NO + 1
                                 FROM   #CCP
                                 WHERE  LEVEL_NAME = 'SCHEDULE ID')

      ---SELECT * FROM CUSTOM_VIEW_DETAILS WHERE CUSTOM_VIEW_MASTER_SID=519
      --SELECT * FROM RELATIONSHIP_BUILDER
      IF EXISTS (SELECT 1
                 FROM   #CCP
                 WHERE  LEVEL_NAME = 'SCHEDULE ID')
        BEGIN
            SET @SCHEDULE_ID_LEVEL=1
        END

      --------------------------------------------------------------------
      -------------------------------------------------------------------------------------
      DECLARE @ITEMID [DBO].[UDT_ITEM]

      INSERT INTO @ITEMID
      SELECT DISTINCT ITEM_MASTER_SID
      FROM   #MULTISELECT_DISCOUNTS A
             JOIN ccp_details cd
               ON cd.CCP_DETAILS_SID = a.CCP_DETAILS_SID

      SET @CURRENT_DATE = CASE
                            WHEN LEFT(@FREQUENCY, 1) = 'M' THEN Dateadd(DD, 1, Eomonth(Getdate(), -1))
                            WHEN LEFT(@FREQUENCY, 1) = 'Q' THEN Datefromparts(Year(Getdate()), Datepart(QUARTER, Getdate()), 01)
                            WHEN LEFT(@FREQUENCY, 1) = 'S' THEN Datefromparts(Year(Getdate()), ( ( ( ( ( Month(Getdate()) ) - 1 ) / 6 ) * 6 ) + 1 ), 01)
                            ELSE Datefromparts(Year(Getdate()), 01, 01)
                          END
      SET @SALES_INCLUSION = CONVERT(CHAR(1), @SALES_INCLUSION)
      SET @DEDUCTION_INCLUSION = CONVERT(CHAR(1), @DEDUCTION_INCLUSION)

      IF Object_id('TEMPDB..#ITEM_PRICING') IS NOT NULL
        DROP TABLE #ITEM_PRICING

      SELECT *
      INTO   #ITEM_PRICING
      FROM   [DBO].[Udf_item_pricing](@ITEMID, 'COGS', @START_PERIOD_SID, @END_PERIOD_SID, 'EACH')

      IF Object_id('TEMPDB..#ACCRUAL_DISCOUNT') IS NOT NULL
        DROP TABLE #ACCRUAL_DISCOUNT;

      CREATE TABLE #ACCRUAL_DISCOUNT
        (
           --PROJECTION_MASTER_SID INT,
           CCP_DETAILS_SID     INT,
           PERIOD_SID          INT,
           RS_CONTRACT_SID     INT,
           DISCOUNT_AMOUNT     NUMERIC(38, 6),
           HIERARCHY_NO        VARCHAR(100),
           LEVEL_NO            VARCHAR(100),
           PARENT_HIERARCHY_NO VARCHAR(8000)
        )

      DECLARE @SQL_ACC NVARCHAR(max)

      SET @SQL_ACC = ' ;

 

WITH CTE

AS (

       SELECT DISTINCT COMPANY_MASTER_SID

              ,CONTRACT_MASTER_SID

              ,ITEM_MASTER_SID

              --,PROJECTION_MASTER_SID

              ,A.CCP_DETAILS_SID

              ,PERIOD_SID

              ,PERIOD_DATE

              ,YEAR

              ,RS.RS_CONTRACT_SID

              ,RS.RS_MODEL_SID

              ,HIERARCHY_NO

        ,LEVEL_NO

              ,COALESCE(PARENT_HIERARCHY_NO,HIERARCHY_NO) PARENT_HIERARCHY_NO

             

       FROM #ccp A
	   join ccp_details cd on cd.CCP_DETAILS_SID=a.CCP_DETAILS_SID
       JOIN #MULTISELECT_DISCOUNTS RS ON RS.CCP_DETAILS_SID = Cd.CCP_DETAILS_SID
	   
       JOIN #PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID

                     AND @END_PERIOD_SID

      

       )

INSERT INTO #ACCRUAL_DISCOUNT (

       --PROJECTION_MASTER_SID,

       CCP_DETAILS_SID

       ,PERIOD_SID

       ,RS_CONTRACT_SID

       ,DISCOUNT_AMOUNT

              ,HIERARCHY_NO

        ,LEVEL_NO

              ,PARENT_HIERARCHY_NO

       )

SELECT --PROJECTION_MASTER_SID,

       A2.CCP_DETAILS_SID

       ,PERIOD_SID

       ,A2.RS_CONTRACT_SID

       ,SUM(DEDUCTION_AMOUNT) / (DATEDIFF(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE) + 1) DISCOUNT_AMOUNT

              ,HIERARCHY_NO

        ,LEVEL_NO

              ,PARENT_HIERARCHY_NO

FROM ACCRUAL_MASTER A1

JOIN CTE A2 ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, 0))))

              AND CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_END_DATE, 0))))

       AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID

       AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID

       AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID

       AND A2.RS_MODEL_SID = A1.RS_MODEL_SID

       AND DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, - 1)) >= @STARTFROM

       AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJECTION_DATE

GROUP BY --PROJECTION_MASTER_SID,

       A2.CCP_DETAILS_SID

       ,PERIOD_SID

       ,A2.RS_CONTRACT_SID

       ,ACCRUAL_PERIOD_START_DATE

       ,ACCRUAL_PERIOD_END_DATE

              ,HIERARCHY_NO

       ,LEVEL_NO

              ,PARENT_HIERARCHY_NO'

      EXEC Sp_executesql
        @SQL_ACC,
        N'@START_PERIOD_SID INT,@END_PERIOD_SID INT,@STARTFROM DATETIME,@PROJECTION_DATE DATETIME',
        @START_PERIOD_SID = @START_PERIOD_SID,
        @END_PERIOD_SID = @END_PERIOD_SID,
        @STARTFROM = @STARTFROM,
        @PROJECTION_DATE = @PROJECTION_DATE

      IF Object_id('tempdb..#PRODUCT_FILE_TEMP') IS NOT NULL
        DROP TABLE #PRODUCT_FILE_TEMP

      CREATE TABLE #PRODUCT_FILE_TEMP
        (
           PROJECTION_MASTER_SID          INT,
           ITEM_MASTER_SID                INT,
           PERIOD_SID                     INT,
           EXFACTORY_ACTUAL_SALES         NUMERIC(38, 6),
           EXFACTORY_ACTUAL_UNITS         NUMERIC(38, 6),
           EXFACTORY_FORECAST_SALES       NUMERIC(38, 6),
           EXFACTORY_FORECAST_UNITS       NUMERIC(38, 6),
           DEMAND_ACTUAL_SALES            NUMERIC(38, 6) sparse,
           DEMAND_ACTUAL_UNITS            NUMERIC(38, 6) sparse,
           DEMAND_FORECAST_SALES          NUMERIC(38, 6) sparse,
           DEMAND_FORECAST_UNITS          NUMERIC(38, 6) sparse,
           ADJUSTED_DEMAND_ACTUAL_SALES   NUMERIC(38, 6) sparse,
           ADJUSTED_DEMAND_ACTUAL_UNITS   NUMERIC(38, 6) sparse,
           ADJUSTED_DEMAND_FORECAST_SALES NUMERIC(38, 6) sparse,
           ADJUSTED_DEMAND_FORECAST_UNITS NUMERIC(38, 6) sparse,
           INVENTORY_ACTUAL_SALES         NUMERIC(38, 6) sparse,
           INVENTORY_ACTUAL_UNITS         NUMERIC(38, 6) sparse,
           INVENTORY_FORECAST_SALES       NUMERIC(38, 6) sparse,
           INVENTORY_FORECAST_UNITS       NUMERIC(38, 6) sparse,
           ITEM_PRICE                     NUMERIC(38, 6),
           EXFACTORY_CUST_ACTUAL_SALES    NUMERIC(38, 6) sparse,
           EXFACTORY_CUST_ACTUAL_UNITS    NUMERIC(38, 6) sparse,
           EXFACTORY_CUST_FORECAST_SALES  NUMERIC(38, 6) sparse,
           EXFACTORY_CUST_FORECAST_UNITS  NUMERIC(38, 6) sparse,
           INVENTORY_CUST_ACTUAL_SALES    NUMERIC(38, 6) sparse,
           INVENTORY_CUST_ACTUAL_UNITS    NUMERIC(38, 6) sparse,
           INVENTORY_CUST_FORECAST_SALES  NUMERIC(38, 6) sparse,
           INVENTORY_CUST_FORECAST_UNITS  NUMERIC(38, 6) sparse
        )

      IF ( (SELECT Count(ID)
            FROM   #PROJECTION_MASTER) > 1 )
        BEGIN
            INSERT INTO #PRODUCT_FILE_TEMP
            EXEC dbo.Prc_prior_proj_prod_file_data
        END

      --select @VIEW
      IF @VIEW = 'DETAIL_TOTAL_DISCOUNT'
        BEGIN
            IF Object_id('TEMPDB..#DATA_TABLE') IS NOT NULL
              DROP TABLE #DATA_TABLE

            SELECT HIERARCHY_NO,
                   COALESCE(PARENT_HIERARCHY_NO, HIERARCHY_NO) PARENT_HIERARCHY_NO,
                   LEVEL_NO,
                   level_name,
                   PERIOD,
                   YEAR,
                   PERIOD_SID,
                   item_master_sid
            INTO   #DATA_TABLE
            FROM   (SELECT DISTINCT cp.HIERARCHY_NO,
                                    cp.LEVEL_NO,
                                    cp.level_name,
                                    cp.PARENT_HIERARCHY_NO,
                                    cd.item_master_sid
                    FROM   #ccp cp
                           JOIN ccp_details cd
                             ON cd.ccp_details_sid = cp.ccp_details_sid) C
                   CROSS JOIN (SELECT DISTINCT PERIOD,
                                               YEAR,
                                               PERIOD_SID
                               FROM   #PERIOD
                               WHERE  PERIOD_SID BETWEEN @PROJ_START_SID AND @PROJ_END_SID) P

            DECLARE @SQL_DT NVARCHAR(MAX) = ''

            SET @SQL_DT = Concat (' INSERT INTO #PIVOT_RESULT (

                     PROJECTION_ID

                     ,LEVEL_NO

                     ,HIERARCHY_NO

                     ,PARENT_HIERARCHY_NO

                     ,CP_INDICATOR

                     ,PERIOD

                     ,YEAR

                     ,EX_FACTORY_SALES_ACTUALS

                     ,EX_FACTORY_SALES_PROJECTED

                     ,DEMAND_SALES_ACTUALS

                     ,DEMAND_SALES_PROJECTED

                     ,INVENTORY_WITHDRAWAL_SALES_ACTUALS

                     ,INVENTORY_WITHDRAWAL_SALES_PROJECTED

                     ,EX_FACTORY_SALES_ACTUALS_PERCENT

                     ,EX_FACTORY_SALES_PROJECTED_PERCENT

                     ,DEMAND_SALES_ACTUALS_PERCENT

                     ,DEMAND_SALES_PROJECTED_PERCENT

                     ,INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT

                     ,INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT

                     ,CONTRACT_SALES_ACTUALS

                     ,CONTRACT_SALES_PROJECTED

                     ,CONTRACT_UNITS_ACTUALS

                     ,CONTRACT_UNITS_PROJECTED

                     ,TOTAL_DISCOUNT_ACTUALS

                     ,TOTAL_DISCOUNT_PROJECTED

                     ,TOTAL_DISCOUNT_ACTUALS_PERCENTAGE

                     ,TOTAL_DISCOUNT_PROJECTED_PERCENTAGE

                     ,TOTAL_RPU_ACTUALS

                     ,TOTAL_RPU_PROJECTED

                     ,NET_SALES_ACTUALS

                     ,NET_SALES_PROJECTED

                     ,COGS_ACTUALS

                     ,COGS_PROJECTED

                     ,NET_PROFIT_ACTUALS

                     ,NET_PROFIT_PROJECTED

                     ,NET_SALES_OF_EX_FACTORY_ACTUALS

                     ,NET_SALES_OF_EX_FACTORY_PROJECTED

                     ,DISCOUNT_OF_EX_FACTORY_ACTUALS

                     ,DISCOUNT_OF_EX_FACTORY_PROJECTED

                     ,NET_EX_FACTORY_SALES_ACTUALS ---------------CEL-386

                     ,NET_EX_FACTORY_SALES_PROJECTED ---------------CEL-386

                     ,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS ---------------CEL-386                         

                     ,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED ---------------CEL-386

                     ,TOTAL_DISCOUNT_ACCRUAL

                     )

                SELECT  @FIRST_PROJ_SID  PROJECTION_ID,

                    DT.LEVEL_NO,

                   DT.HIERARCHY_NO,

                              DT.PARENT_HIERARCHY_NO,

                    @CP_INDICATOR ,

                   DT.PERIOD,

                   DT.YEAR,

                   EX_FACTORY_SALES_ACTUALS = CASE WHEN @CURRENT_DATE > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)  ELSE NULL END ,

                   EX_FACTORY_SALES_PROJECTED = ISNULL(GTS.GTS_SALES_PROJECTED, 0),

                   DEMAND_SALES_ACTUALS =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(GTS.DEMAND_SALES_ACTUAL, 0)  ELSE NULL END,

                   DEMAND_SALES_PROJECTED = ISNULL(GTS.DEMAND_SALES_PROJECTED, 0),

                   INVENTORY_WITHDRAWAL_SALES_ACTUALS =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(GTS.ACT_AMOUNT_WITHDRAWN, 0)  ELSE NULL END,

                   INVENTORY_WITHDRAWAL_SALES_PROJECTED = ISNULL(GTS.FOR_AMOUNT_WITHDRAWN, 0),

                   EX_FACTORY_SALES_ACTUALS_PERCENT = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100  ELSE NULL END,

                   EX_FACTORY_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,

                   DEMAND_SALES_ACTUALS_PERCENT =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100  ELSE NULL END,

                   DEMAND_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,

                   INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ( ISNULL(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100  ELSE NULL END,

                   INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( ISNULL(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,

                   CONTRACT_SALES_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(SALES.CONTRACT_SALES_ACTUALS, 0)  ELSE NULL END,

                   CONTRACT_SALES_PROJECTED = ISNULL(SALES.CONTRACT_SALES_PROJECTED, 0),

                   CONTRACT_UNITS_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(CONTRACT_UNITS_ACTUALS, 0)  ELSE NULL END,

                  CONTRACT_UNITS_PROJECTED = ISNULL(CONTRACT_UNITS_PROJECTED, 0),

                              TOTAL_DISCOUNT_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL

                                              + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0),0)  ELSE NULL END,

                   TOTAL_DISCOUNT_PROJECTED = ISNULL(DISC.CONTRACT_DISCOUNT_PROJECTED

                                              + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0), 0),

                   TOTAL_DISCOUNT_ACTUALS_PERCENTAGE = CASE WHEN @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ( ISNULL(( DISC.CONTRACT_DISCOUNT_ACTUAL

                                                                    + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100  ELSE NULL END,

                   TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( ISNULL(( DISC.CONTRACT_DISCOUNT_PROJECTED

                                                                    + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,

                   TOTAL_RPU_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ( ISNULL(( DISC.CONTRACT_DISCOUNT_ACTUAL

                                                    + ISNULL(PPA.PPA_ACTUAL_RPU, 0) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) )  ELSE NULL END,

                   TOTAL_RPU_PROJECTED = ( ISNULL(( DISC.CONTRACT_DISCOUNT_PROJECTED

                                                    + ISNULL(PPA.PPA_PROJECTED_RPU, 0) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),

                   NET_SALES_ACTUALS =CASE WHEN  @CURRENT_DATE > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  ISNULL(SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUAL

                                                                                       + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ), 0)  ELSE NULL END,

                   NET_SALES_PROJECTED = ISNULL(SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED

                                                                                       + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ),0),

                   COGS_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(SALES.COGS_ACTUALS, 0)  ELSE NULL END,

                   COGS_PROJECTED = ISNULL(SALES.COGS_PROJECTED, 0),

                   NET_PROFIT_ACTUALS =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  isnull(( ( SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUAL

                                                                                            + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0) ) ) - ( SALES.COGS_ACTUALS  )),0)  ELSE NULL END,

                   NET_PROFIT_PROJECTED = isnull(( ( SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED

                                                                                            + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ) - ( SALES.COGS_PROJECTED ) ),0),

                                                                                                                                                             

                              NET_SALES_OF_EX_FACTORY_ACTUALS =CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN  (COALESCE((( SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUAL

                                                                                       + Isnull(PPA.PPA_DISCOUNT_ACTUAL, 0) ) ))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100)  ELSE NULL END,

                   NET_SALES_OF_EX_FACTORY_PROJECTED = (COALESCE((( SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED

                                                                                                 + Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0) ) ))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100)

                     ,DISCOUNT_OF_EX_FACTORY_ACTUALS = CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN COALESCE(((DISC.CONTRACT_DISCOUNT_ACTUAL + Isnull(PPA.PPA_DISCOUNT_ACTUAL, 0)))/NULLIF(GTS.GTS_SALES_ACTUALS,0),0)*100  ELSE NULL END,

                           DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE((isnull((DISC.CONTRACT_DISCOUNT_PROJECTED)+ Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0),0))/NULLIF(GTS.GTS_SALES_PROJECTED,0),0)*100

                           ,NET_EX_FACTORY_SALES_ACTUALS=((CASE WHEN @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)  ELSE NULL END )-(CASE WHEN  @CURRENT_DATE > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(DISC.CONTRACT_DISCOUNT_ACTUAL

                                              + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0), 0)  ELSE NULL END))

                                                -----------cel-386

                                  ,NET_EX_FACTORY_SALES_PROJECTED =ISNULL(GTS.GTS_SALES_PROJECTED, 0)-(DISC.CONTRACT_DISCOUNT_PROJECTED + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0))-----------cel-386

                        ,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS=ISNULL(((CASE WHEN  @CURRENT_DATE > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)  ELSE NULL END )-(CASE WHEN  @CURRENT_DATE  > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN isnull(DISC.CONTRACT_DISCOUNT_ACTUAL

                                              + ISNULL(PPA.PPA_DISCOUNT_ACTUAL, 0),0)  ELSE NULL END))/nullif(CASE WHEN @CURRENT_DATE > DATEFROMPARTS( DT.YEAR, DT.PERIOD, 01) THEN ISNULL(GTS.GTS_SALES_ACTUALS, 0)  ELSE NULL END ,0),0)*100

                                                                             -----------cel-386

                                 

                                   ,NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED=ISNULL((ISNULL(GTS.GTS_SALES_PROJECTED, 0)-(DISC.CONTRACT_DISCOUNT_PROJECTED + ISNULL(PPA.PPA_DISCOUNT_PROJECTED, 0)))/NULLIF(GTS.GTS_SALES_PROJECTED, 0),0)*100

                                                                    -----------cel-386

                                  ,ACCRUAL_DISCOUNT AS TOTAL_DISCOUNT_ACCRUAL

 

            FROM   (

       SELECT DISTINCT HIERARCHY_NO
              ,YEAR
			  ,PERIOD
              ,LEVEL_NO
              ,PARENT_HIERARCHY_NO
       FROM #DATA_TABLE

       ) DT

                    JOIN (SELECT ACT_AMOUNT_WITHDRAWN = SUM(INVENTORY_ACTUAL_SALES)

              ,ACT_UNITS_WITHDRAWN = SUM(INVENTORY_ACTUAL_UNITS)

              ,FOR_AMOUNT_WITHDRAWN = SUM(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES))

              ,FOR_UNITS_WITHDRAWN = SUM(INVENTORY_FORECAST_UNITS)

              ,DEMAND_SALES_ACTUAL = SUM(DEMAND_ACTUAL_SALES)

              ,ACT_GROSS_UNITS = SUM(DEMAND_ACTUAL_UNITS)

              ,DEMAND_SALES_PROJECTED = SUM(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES))

              ,FOR_GROSS_UNITS = SUM(DEMAND_FORECAST_UNITS)

              ,GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES)

              ,GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES))

              ,UNITS = SUM(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS))

              ,A.PERIOD

              ,A.YEAR

              ,A.HIERARCHY_NO

              ,A.LEVEL_NO

              ,A.PARENT_HIERARCHY_NO

       FROM (SELECT distinct HIERARCHY_NO,LEVEL_NO,PARENT_HIERARCHY_NO,ITEM_MASTER_SID,period_sid,period,year FROM  #DATA_TABLE) A

       JOIN ', @PRODUCT_TABLE, ' PF

              ON PF.ITEM_MASTER_SID = A.ITEM_MASTER_SID AND PF.PERIOD_SID = A.PERIOD_SID

       GROUP BY A.PERIOD

              ,A.YEAR

              ,A.HIERARCHY_NO

              ,A.LEVEL_NO

              ,A.PARENT_HIERARCHY_NO) GTS

                          ON GTS.PERIOD = DT.PERIOD

                             AND GTS.YEAR = DT.YEAR

                                                  AND GTS.HIERARCHY_NO = DT.HIERARCHY_NO

                                                                                    AND GTS.PARENT_HIERARCHY_NO=DT.PARENT_HIERARCHY_NO

                             AND GTS.LEVEL_NO = DT.LEVEL_NO

 JOIN (SELECT
		CONTRACT_SALES_ACTUALS = Sum(ACTUAL_SALES)
		,CONTRACT_SALES_PROJECTED = Sum(PROJECTION_SALES)
		,CONTRACT_UNITS_ACTUALS = Sum(ACTUAL_UNITS)
		,CONTRACT_UNITS_PROJECTED = Sum(PROJECTION_UNITS)
		,COGS_ACTUALS = max(COGS_ACTUAL)
		,COGS_PROJECTED = max(COGS_PROJECTED)
		,A.PERIOD
        ,A.[YEAR]
        ,A.HIERARCHY_NO
       ,A.LEVEL_NO
	   ,A.PARENT_HIERARCHY_NO
	FROM (
		SELECT dt.HIERARCHY_NO
			,dt.PERIOD
	        ,dt.YEAR
			,Iif(( cts.SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ) AND cts.indicator=0, Isnull(SALES,0), NULL) ACTUAL_SALES
			,Iif(( cts.SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ) AND cts.indicator=0, Isnull(UNITS,0), NULL)   ACTUAL_UNITS
			,Iif(( cts.SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ) AND cts.indicator=1, Isnull(SALES,0), NULL) PROJECTION_SALES
			,Iif(( cts.SALES_INCLUSION = @SALES_INCLUSION OR @SALES_INCLUSION IS NULL ) AND cts.indicator=1, Isnull(UNITS,0), NULL) PROJECTION_UNITS
			--,DT.ITEM_MASTER_SID
			,COGS_ACTUAL = COGS_ACTUAL---(ISNULL(NAS.ACTUAL_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1) 
			,COGS_PROJECTED = COGS_ACTUAL---(ISNULL(NPS.PROJECTION_UNITS, 0) * ISNULL(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1) 
			,dt.LEVEL_NO
		 ,dt.PARENT_HIERARCHY_NO
		FROM #DATA_TABLE DT
		LEFT JOIN  ', CASE
                                            WHEN @CP_INDICATOR IN ( 'C', 'P' ) THEN @sales_table
                                            ELSE Concat('(SELECT PERIOD
,c.YEAR
,c.SALES
,c.UNITS
,c.SALES_INCLUSION
,c.INDICATOR,cs.HIERARCHY_NO
FROM  ', @sales_table, '  c
       JOIN ',@CUSTOM_CCP_SALES,' cs
         ON cs.ROWID = c.HIERARCHY_NO
WHERE  CUST_VIEW_MASTER_SID = ', @CUSTOM_VIEW_MASTER_SID, ' )')
                                          END, ' cts on cts.HIERARCHY_NO like dt.HIERARCHY_NO + ''%''
		AND cts.period=dt.period
		AND cts.year=dt.year
		LEFT JOIN (SELECT --dt.HIERARCHY_NO,
		p.year,p.period,COGS_ACTUAL = Sum((Isnull(NAS.QUANTITY, 0) * Isnull(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1)),
		COGS_PROJECTED = Sum((Isnull(NPS.PROJECTION_UNITS, 0) * Isnull(U.ITEM_PRICE, 0)) *  COALESCE(NULLIF(UOM_VALUE, 0),1) )
		  FROM  #CCP dt JOIN ccp_details cd ON cd.ccp_details_sid=dt.ccp_details_sid
		CROSS JOIN #period p
		LEFT JOIN ', @S_MASTER_TABLE, ' SPM ON SPM.CCP_DETAILS_SID = DT.CCP_DETAILS_SID
		LEFT JOIN (SELECT AD.CCP_DETAILS_SID,
                               PERIOD_SID,                               
                               SUM(QUANTITY) QUANTITY
                        FROM   [ACTUALS_DETAILS] AD
                        WHERE  QUANTITY_INCLUSION = ''Y''
                        GROUP  BY AD.CCP_DETAILS_SID,
                                  PERIOD_SID) NAS ON DT.CCP_DETAILS_SID = NAS.CCP_DETAILS_SID
			AND p.PERIOD_SID = NAS.PERIOD_SID
		LEFT JOIN ', @S_PROJECTION_TABLE, '  NPS ON NPS.CCP_DETAILS_SID = DT.CCP_DETAILS_SID
			AND p.PERIOD_SID = NPS.PERIOD_SID
		LEFT JOIN #ITEM_PRICING U ON cd.item_master_sid = U.ITEM_MASTER_SID
			AND p.PERIOD_SID = U.PERIOD_SID
		LEFT JOIN #ITEM_UOM_DETAILS UOM ON UOM.ITEM_MASTER_SID = cd.ITEM_MASTER_SID
		GROUP BY --dt.HIERARCHY_NO,
		p.year,p.period)  h ON --h.HIERARCHY_NO=dt.HIERARCHY_NO and
		 h.period=dt.period
		AND h.year=dt.year) a group by A.PERIOD
        ,A.[YEAR]
        ,A.HIERARCHY_NO
       ,A.LEVEL_NO
	   ,A.PARENT_HIERARCHY_NO

       ) SALES   ON SALES.PERIOD = DT.PERIOD

                             AND SALES.[YEAR] = DT.YEAR

                             AND SALES.HIERARCHY_NO = DT.HIERARCHY_NO

                                                AND SALES.PARENT_HIERARCHY_NO=DT.PARENT_HIERARCHY_NO

                             AND SALES.LEVEL_NO = DT.LEVEL_NO
LEFT JOIN ( 
	
	SELECT PERIOD
	    ,YEAR,LEVEL_NO,HIERARCHY_NO
		,CONTRACT_DISCOUNT_ACTUAL = Sum(ACTUAL_SALES)
		,CONTRACT_DISCOUNT_PROJECTED = Sum(PROJECTION_SALES)
		,ACCRUAL_DISCOUNT=Max(ACCRUAL_DISCOUNT),PARENT_HIERARCHY_NO
	FROM (
		SELECT dt.HIERARCHY_NO
			,dt.PERIOD
	        ,DT.LEVEL_NO
			--,DT.PERIOD_SID
			,Iif(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL ) AND cts.indicator=0, Isnull(DISCOUNT,0), NULL) ACTUAL_SALES
			,Iif(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION OR @DEDUCTION_INCLUSION IS NULL ) AND cts.indicator=1, Isnull(DISCOUNT,0), NULL)  PROJECTION_SALES
			,DISCOUNT_AMOUNT  ACCRUAL_DISCOUNT
			,Discount AS CONTRACT_DISCOUNT_ACTUALS,dt.YEAR,dt.PARENT_HIERARCHY_NO
		FROM #DATA_TABLE DT
		LEFT JOIN  (SELECT ctd.* FROM ', CASE
                                                               WHEN @CP_INDICATOR IN ( 'C', 'P' ) THEN @discount_table
                                                               ELSE Concat('(SELECT PERIOD
,c.YEAR
,c.DISCOUNT
,c.DEDUCTION_INCLUSION
,c.INDICATOR,cs.HIERARCHY_NO
FROM   ', @discount_table, ' c
       JOIN ',@CUSTOM_CCP_SALES,' cs
         ON cs.ROWID = c.HIERARCHY_NO
WHERE  CUST_VIEW_MASTER_SID = ', @CUSTOM_VIEW_MASTER_SID, ')')
                                                             END, '  ctd)cts ON cts.HIERARCHY_NO LIKE dt.HIERARCHY_NO +''%''
		AND cts.period=dt.period
		AND cts.year=dt.year
		LEFT JOIN #ACCRUAL_DISCOUNT AD ON cts.HIERARCHY_NO=dt.HIERARCHY_NO
		AND cts.period=dt.period
		AND cts.year=dt.year
		) a


       GROUP BY PERIOD

              ,[YEAR]

              ,A.HIERARCHY_NO

              ,A.LEVEL_NO

              ,A.PARENT_HIERARCHY_NO) DISC

                          ON DISC.PERIOD = DT.PERIOD

                             AND DISC.[YEAR] = DT.[YEAR]

                             AND DISC.HIERARCHY_NO = DT.HIERARCHY_NO

                                                AND DISC.PARENT_HIERARCHY_NO=DT.PARENT_HIERARCHY_NO

                             AND DISC.LEVEL_NO = DT.LEVEL_NO

                   LEFT JOIN (

                              SELECT PPA_DISCOUNT_PROJECTED = null

              ,PPA_PROJECTED_RPU = null

              ,null AS PPA_PROJECTION_SALES

              ,null AS PPA_PROJECTION_UNITS

              ,PPA_DISCOUNT_ACTUAL = null

              ,PPA_ACTUAL_RPU = null

              ,null AS PPA_ACTUAL_SALES

              ,null AS PPA_ACTUAL_UNITS

              ,PERIOD=null

              ,YEAR=null

              ,HIERARCHY_NO=null

              ,PARENT_HIERARCHY_NO=null

              ,LEVEL_NO=null

       ) PPA

                          ON PPA.PERIOD = DT.PERIOD

                             AND PPA.[YEAR] = DT.[YEAR]

                             AND PPA.HIERARCHY_NO = DT.HIERARCHY_NO

                                                AND PPA.PARENT_HIERARCHY_NO=DT.PARENT_HIERARCHY_NO

                             AND PPA.LEVEL_NO = DT.LEVEL_NO

            ORDER  BY DT.PERIOD,

                      DT.[YEAR],

                      DT.HIERARCHY_NO,

                                    DT.PARENT_HIERARCHY_NO')

            EXEC Sp_executesql
              @SQL_DT,
              N'@SALES_INCLUSION BIT,@DEDUCTION_INCLUSION  BIT,@FIRST_PROJ_SID  INT,@CP_INDICATOR CHAR(1),@CURRENT_DATE DATE,@DISCOUNT_LEVEL VARCHAR(50),@SCREEN_NAME VARCHAR(100)',
              @SALES_INCLUSION = @SALES_INCLUSION,
              @DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION,
              @FIRST_PROJ_SID = @FIRST_PROJ_SID,
              @CP_INDICATOR = @CP_INDICATOR,
              @CURRENT_DATE = @CURRENT_DATE,
              @DISCOUNT_LEVEL = @DISCOUNT_LEVEL,
              @SCREEN_NAME = @SCREEN_NAME

            ----- SELECT @SQL_DT,@SALES_INCLUSION,@DEDUCTION_INCLUSION,@FIRST_PROJ_SID,@CP_INDICATOR,@CURRENT_DATE,@DISCOUNT_LEVEL,@SCREEN_NAME
            IF EXISTS (SELECT 1
                       FROM   #PROJECTION_MASTER
                       WHERE  ID > 1)
              BEGIN
                  IF Object_id('TEMPDB..#PRIOR_PROJECTIONS') IS NOT NULL
                    DROP TABLE #PRIOR_PROJECTIONS

                  SELECT PD.CCP_DETAILS_SID,
                         PM.PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NO,
                         COALESCE(PARENT_HIERARCHY_NO, HIERARCHY_NO) PARENT_HIERARCHY_NO
                  INTO   #PRIOR_PROJECTIONS
                  FROM   #PROJECTION_MASTER PM
                         INNER JOIN PROJECTION_DETAILS PD
                                 ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                         INNER JOIN #CCP C
                                 ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                  WHERE  PM.ID <> 1

                  IF Object_id('TEMPDB..#SALES_INCLUSION') IS NOT NULL
                    DROP TABLE #SALES_INCLUSION

                  SELECT DISTINCT A.PROJECTION_DETAILS_SID,
                                  B.CCP_DETAILS_SID,
                                  CASE
                                    WHEN DESCRIPTION = 'YES' THEN 1
                                    ELSE 0
                                  END SALES_INCLUSION
                  INTO   #SALES_INCLUSION
                  FROM   NM_SALES_PROJECTION_MASTER A
                         INNER JOIN #PRIOR_PROJECTIONS B
                                 ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                    AND a.PROJECTION_MASTER_SID = b.PROJECTION_MASTER_SID
                         -- AND b.ID <> 1
                         INNER JOIN CCP_DETAILS CD
                                 ON CD.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                         INNER JOIN CFP_CONTRACT_DETAILS CC
                                 ON CC.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID
                         INNER JOIN CFP_CONTRACT CC1
                                 ON CC1.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
                                    AND CC1.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
                         INNER JOIN HELPER_TABLE HT
                                 ON HT.HELPER_TABLE_SID = CC1.SALES_INCLUSION

                  IF Object_id('TEMPDB..#DEDUCTION_INCLUSION') IS NOT NULL
                    DROP TABLE #DEDUCTION_INCLUSION

                  SELECT DISTINCT A.PROJECTION_DETAILS_SID,
                                  B.CCP_DETAILS_SID,
                                  RS.RS_CONTRACT_SID,
                                  CASE
                                    WHEN DESCRIPTION = 'YES' THEN 1
                                    ELSE 0
                                  END DEDUCTION_INCLUSION
                  INTO   #DEDUCTION_INCLUSION
                  FROM   NM_DISCOUNT_PROJ_MASTER A
                         INNER JOIN #PRIOR_PROJECTIONS B
                                 ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                    AND a.PROJECTION_MASTER_SID = b.PROJECTION_MASTER_SID
                         --AND b.ID <> 1
                         INNER JOIN RS_CONTRACT RS
                                 ON A.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
                         INNER JOIN HELPER_TABLE HT
                                 ON HT.HELPER_TABLE_SID = RS.DEDUCTION_INCLUSION

                  IF Object_id('TEMPDB..#PRIOR_ACCRUAL_DISCOUNT') IS NOT NULL
                    DROP TABLE #PRIOR_ACCRUAL_DISCOUNT

                  CREATE TABLE #PRIOR_ACCRUAL_DISCOUNT
                    (
                       PROJECTION_MASTER_SID  INT,
                       PROJECTION_DETAILS_SID INT,
                       PERIOD_SID             INT,
                       RS_CONTRACT_SID        INT,
                       DISCOUNT_AMOUNT        NUMERIC(38, 6),
                       HIERARCHY_NO           VARCHAR(8000),
                       LEVEL_NO               INT,
                       PARENT_HIERARCHY_NO    VARCHAR(8000),
                       ccp_details_sid        INT
                    );

                  WITH CTE
                       AS (SELECT DISTINCT COMPANY_MASTER_SID,
                                           CONTRACT_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           A.PROJECTION_MASTER_SID,
                                           --A.PROJECTION_DETAILS_SID,
                                           P.PERIOD_SID,
                                           PERIOD_DATE,
                                           YEAR,
                                           RS.RS_CONTRACT_SID,
                                           RS.RS_MODEL_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NO,
                                           PARENT_HIERARCHY_NO,
                                           a.ccp_details_sid
                           FROM   #PRIOR_PROJECTIONS A
                                  --INNER JOIN PROJECTION_DETAILS PD
                                  --        ON PD.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                  --           AND PD.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
                                  INNER JOIN CCP_DETAILS CD
                                          ON CD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                  --INNER JOIN NM_DISCOUNT_PROJECTION RS
                                  INNER JOIN NM_DISCOUNT_PROJ_MASTER RS
                                          --ON RS.PROJECTION_DETAILS_SID = A.PROJECTION_DETAILS_SID
                                          ON RS.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
                                             AND CD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                  INNER JOIN #PERIOD P
                                          --  ON P.PERIOD_SID BETWEEN 601 AND 672)
                                          ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID)
                  INSERT INTO #PRIOR_ACCRUAL_DISCOUNT
                              (PROJECTION_MASTER_SID,
                               --PROJECTION_DETAILS_SID,
                               PERIOD_SID,
                               RS_CONTRACT_SID,
                               DISCOUNT_AMOUNT,
                               HIERARCHY_NO,
                               LEVEL_NO,
                               PARENT_HIERARCHY_NO,
                               ccp_details_sid)
                  SELECT PROJECTION_MASTER_SID,
                         -- A2.PROJECTION_DETAILS_SID,
                         PERIOD_SID,
                         A2.RS_CONTRACT_SID,
                         Sum(DEDUCTION_AMOUNT) / ( Datediff(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE)
                                                   + 1 ) DISCOUNT_AMOUNT,
                         HIERARCHY_NO,
                         LEVEL_NO,
                         PARENT_HIERARCHY_NO,
                         a2.ccp_details_sid
                  FROM   ACCRUAL_MASTER A1
                         INNER JOIN CTE A2
                                 ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, 0)))) AND CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_END_DATE, 0))))
                                    AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID
                                    AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID
                                    AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID
                                    AND A2.RS_MODEL_SID = A1.RS_MODEL_SID
                                    AND Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, -1)) >= @STARTFROM
                                    AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJECTION_DATE
                  GROUP  BY PROJECTION_MASTER_SID,
                            -- A2.PROJECTION_DETAILS_SID,
                            PERIOD_SID,
                            A2.RS_CONTRACT_SID,
                            ACCRUAL_PERIOD_START_DATE,
                            ACCRUAL_PERIOD_END_DATE,
                            HIERARCHY_NO,
                            LEVEL_NO,
                            PARENT_HIERARCHY_NO,
                            a2.ccp_details_sid

                  IF Object_id('TEMPDB..#PRIOR_DATA_TABLE') IS NOT NULL
                    DROP TABLE #PRIOR_DATA_TABLE

                  SELECT PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         PARENT_HIERARCHY_NO,
                         LEVEL_NO,
                         PERIOD,
                         YEAR,
                         PERIOD_SID,
                         CCP_DETAILS_SID,
                         level_name
                  INTO   #PRIOR_DATA_TABLE
                  FROM   (SELECT DISTINCT HIERARCHY_NO,
                                          LEVEL_NO,
                                          CCP_DETAILS_SID,
                                          COALESCE(PARENT_HIERARCHY_NO, HIERARCHY_NO) PARENT_HIERARCHY_NO,
                                          level_name
                          FROM   #CCP) C
                         CROSS JOIN (SELECT DISTINCT PERIOD,
                                                     YEAR,
                                                     PERIOD_SID
                                     FROM   #PERIOD
                                     WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID) P
                         CROSS JOIN #PROJECTION_MASTER PM
                  WHERE  PM.ID <> 1
                         AND EXISTS (SELECT 1
                                     FROM   #PRIOR_PROJECTIONS ppr
                                     WHERE  ppr.CCP_DETAILS_SID = c.CCP_DETAILS_SID
                                            AND pm.PROJECTION_MASTER_SID = ppr.PROJECTION_MASTER_SID)

              /*
              SELECT PROJECTION_MASTER_SID,
               HIERARCHY_NO,
               PARENT_HIERARCHY_NO,
               LEVEL_NO,
               PERIOD,
               YEAR,
               PERIOD_SID
              -- CCP_DETAILS_SID
              into #PRIOR_DATA_TABLE
              from #data_table ft cross join #PROJECTION_MASTER PM
              WHERE  PM.ID <> 1*/
                  --IF Object_id('tempdb..#CURRENT_CPP_COMP_PRIOR_CPP') IS NOT NULL
                  --  DROP TABLE #CURRENT_CPP_COMP_PRIOR_CPP
                  --SELECT PM.PROJECTION_MASTER_SID,
                  --       CC.CCP_DETAILS_SID
                  --INTO   #CURRENT_CPP_COMP_PRIOR_CPP
                  --FROM   #PROJECTION_MASTER PM
                  --       INNER JOIN PROJECTION_DETAILS PD
                  --               ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                  --       INNER JOIN CCP_DETAILS CC
                  --               ON PD.CCP_DETAILS_SID = CC.CCP_DETAILS_SID
                  --WHERE  ID = 1
                  INSERT INTO #PIVOT_RESULT
                              (PROJECTION_ID,
                               LEVEL_NO,
                               HIERARCHY_NO,
                               PARENT_HIERARCHY_NO,
                               CP_INDICATOR,
                               PERIOD,
                               YEAR,
                               EX_FACTORY_SALES_PROJECTED,
                               EX_FACTORY_SALES_PROJECTED_PERCENT,
                               CONTRACT_SALES_PROJECTED,
                               CONTRACT_UNITS_PROJECTED,
                               TOTAL_DISCOUNT_PROJECTED,
                               TOTAL_DISCOUNT_PROJECTED_PERCENTAGE,
                               TOTAL_RPU_PROJECTED,
                               NET_SALES_PROJECTED,
                               COGS_PROJECTED,
                               NET_PROFIT_PROJECTED,
                               NET_SALES_OF_EX_FACTORY_PROJECTED,
                               DISCOUNT_OF_EX_FACTORY_PROJECTED,
                               NET_EX_FACTORY_SALES_PROJECTED,
                               NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED,
                               TOTAL_DISCOUNT_ACCRUAL)
                  SELECT PDT.PROJECTION_MASTER_SID AS PROJECTION_ID,
                         PDT.LEVEL_NO,
                         PDT.HIERARCHY_NO,
                         PDT.PARENT_HIERARCHY_NO,
                         @CP_INDICATOR,
                         PDT.PERIOD,
                         PDT.YEAR,
                         -- EX_FACTORY_SALES_ACTUALS = Isnull(GTS.GTS_SALES_ACTUALS, 0),
                         EX_FACTORY_SALES_PROJECTED = Isnull(GTS.GTS_SALES_PROJECTED, 0),
                         --DEMAND_SALES_ACTUALS = Isnull(GTS.DEMAND_SALES_ACTUAL, 0),
                         --DEMAND_SALES_PROJECTED = Isnull(GTS.DEMAND_SALES_PROJECTED, 0),
                         --INVENTORY_WITHDRAWAL_SALES_ACTUALS = Isnull(GTS.ACT_AMOUNT_WITHDRAWN, 0),
                         --INVENTORY_WITHDRAWAL_SALES_PROJECTED = Isnull(GTS.FOR_AMOUNT_WITHDRAWN, 0),
                         -- EX_FACTORY_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) ) * 100,
                         EX_FACTORY_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) ) * 100,
                         --DEMAND_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.DEMAND_SALES_ACTUAL, 0), 0) ) * 100,
                         --DEMAND_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.DEMAND_SALES_PROJECTED, 0), 0) ) * 100,
                         --INVENTORY_WITHDRAWAL_SALES_ACTUALS_PERCENT = ( Isnull(SALES.CONTRACT_SALES_ACTUALS / NULLIF(GTS.ACT_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                         --INVENTORY_WITHDRAWAL_SALES_PROJECTED_PERCENT = ( Isnull(SALES.CONTRACT_SALES_PROJECTED / NULLIF(GTS.FOR_AMOUNT_WITHDRAWN, 0), 0) ) * 100,
                         --CONTRACT_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS, 0),
                         CONTRACT_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED, 0),
                         -- CONTRACT_UNITS_ACTUALS = Isnull(CONTRACT_UNITS_ACTUALS, 0),
                         CONTRACT_UNITS_PROJECTED = Isnull(CONTRACT_UNITS_PROJECTED, 0),
                         --TOTAL_DISCOUNT_ACTUALS = Isnull(DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                         --                                                                   WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                         --                                                                   ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                         --                                                                 END ), 0),
                         TOTAL_DISCOUNT_PROJECTED = Isnull(DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                  WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                  ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                END ), 0),
                         --TOTAL_DISCOUNT_ACTUALS_PERCENTAGE = ( Isnull(( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                         --                                                                                    WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                         --                                                                                    ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                         --                                                                                  END ) ) / NULLIF(SALES.CONTRACT_SALES_ACTUALS, 0), 0) ) * 100,
                         TOTAL_DISCOUNT_PROJECTED_PERCENTAGE = ( Isnull(( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                 WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                 ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                               END ) ) / NULLIF(SALES.CONTRACT_SALES_PROJECTED, 0), 0) ) * 100,
                         --TOTAL_RPU_ACTUALS = ( Isnull(( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                         --                                                                    WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                         --                                                                    ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                         --                                                                  END ) ) / NULLIF(SALES.CONTRACT_UNITS_ACTUALS, 0), 0) ),
                         TOTAL_RPU_PROJECTED = ( Isnull(( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                 WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                 ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                               END ) ) / NULLIF(SALES.CONTRACT_UNITS_PROJECTED, 0), 0) ),
                         --NET_SALES_ACTUALS = Isnull(SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                         --                                                                                                 WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                         --                                                                                                 ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                         --                                                                                               END ) ), 0),
                         NET_SALES_PROJECTED = Isnull(SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                                WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                                ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                                              END ) ), 0),
                         --COGS_ACTUALS = Isnull(SALES.COGS_ACTUALS, 0),
                         COGS_PROJECTED = Isnull(SALES.COGS_PROJECTED, 0),
                         --NET_PROFIT_ACTUALS = Isnull(( ( SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                         --                                                                                                      WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                         --                                                                                                      ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                         --                                                                                                    END ) ) ) - ( SALES.COGS_ACTUALS ) ), 0),
                         NET_PROFIT_PROJECTED = Isnull(( SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                                   WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                                   ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                                                 END ) ) ) - ( SALES.COGS_PROJECTED ), 0),
                         -- NET_SALES_OF_EX_FACTORY_ACTUALS = COALESCE((( SALES.CONTRACT_SALES_ACTUALS - ( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                         --                                                                                                                     WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                         --                                                                                                                     ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                         --                                                                                                                   END ) ) )) / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) * 100,
                         NET_SALES_OF_EX_FACTORY_PROJECTED = COALESCE((( SALES.CONTRACT_SALES_PROJECTED - ( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                                                   WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                                                   ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                                                                 END ) ) )) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100,
                         --DISCOUNT_OF_EX_FACTORY_ACTUALS = COALESCE((( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                         --                                                                                  WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                         --                                                                                  ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                         --                                                                                END ) )) / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) * 100,
                         DISCOUNT_OF_EX_FACTORY_PROJECTED = COALESCE((( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                               WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                               ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                             END ) )) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100,
                         --NET_EX_FACTORY_SALES_ACTUALS = Isnull(Isnull(GTS.GTS_SALES_ACTUALS, 0) - ( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                         --                                                                                                                WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                         --                                                                                                                ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                         --                                                                                                              END ) ), 0),
                         NET_EX_FACTORY_SALES_PROJECTED = Isnull(Isnull(GTS.GTS_SALES_PROJECTED, 0) - ( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                                               WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                                               ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                                                             END ) ), 0),
                         --NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS = Isnull(( Isnull(GTS.GTS_SALES_ACTUALS, 0) - ( DISC.CONTRACT_DISCOUNT_ACTUALS + ( CASE
                         --                                                                                                                                      WHEN SALES.CONTRACT_SALES_ACTUALS = 0 THEN 0
                         --                                                                                                                                      ELSE Isnull(PPA.PPA_DISCOUNT_ACTUALS, 0)
                         --                                                                                                                                    END ) ) ) / NULLIF(GTS.GTS_SALES_ACTUALS, 0), 0) * 100,
                         NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED = Isnull(( Isnull(GTS.GTS_SALES_PROJECTED, 0) - ( DISC.CONTRACT_DISCOUNT_PROJECTED + ( CASE
                                                                                                                                                                     WHEN SALES.CONTRACT_SALES_PROJECTED = 0 THEN 0
                                                                                                                                                                     ELSE Isnull(PPA.PPA_DISCOUNT_PROJECTED, 0)
                                                                                                                                                                   END ) ) ) / NULLIF(GTS.GTS_SALES_PROJECTED, 0), 0) * 100,
                         ACCRUAL_DISCOUNT          AS TOTAL_DISCOUNT_ACCRUAL
                  FROM   (SELECT DISTINCT HIERARCHY_NO,
                                          PERIOD,
                                          YEAR,
                                          LEVEL_NO,
                                          PARENT_HIERARCHY_NO,
                                          PROJECTION_MASTER_SID
                          FROM   #PRIOR_DATA_TABLE) PDT
                         LEFT JOIN (SELECT --ACT_AMOUNT_WITHDRAWN = Sum(INVENTORY_ACTUAL_SALES),
                                   --ACT_UNITS_WITHDRAWN = Sum(INVENTORY_ACTUAL_UNITS),
                                   --FOR_AMOUNT_WITHDRAWN = Sum(COALESCE(INVENTORY_FORECAST_SALES, INVENTORY_ACTUAL_SALES)),
                                   --FOR_UNITS_WITHDRAWN = Sum(INVENTORY_FORECAST_UNITS),
                                   --DEMAND_SALES_ACTUAL = Sum(DEMAND_ACTUAL_SALES),
                                   --ACT_GROSS_UNITS = Sum(DEMAND_ACTUAL_UNITS),
                                   --DEMAND_SALES_PROJECTED = Sum(COALESCE(DEMAND_FORECAST_SALES, DEMAND_ACTUAL_SALES)),
                                   --FOR_GROSS_UNITS = Sum(DEMAND_FORECAST_UNITS),
                                   GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                   GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                   UNITS = Sum(COALESCE(EXFACTORY_FORECAST_UNITS, EXFACTORY_ACTUAL_UNITS)),
                                   A.PERIOD,
                                   A.YEAR,
                                   A.HIERARCHY_NO,
                                   A.LEVEL_NO,
                                   A.PARENT_HIERARCHY_NO,
                                   A.PROJECTION_MASTER_SID
                                    FROM   (SELECT DISTINCT A.PERIOD,
                                                            A.YEAR,
                                                            A.HIERARCHY_NO,
                                                            A.LEVEL_NO,
                                                            A.PARENT_HIERARCHY_NO,
                                                            A.PROJECTION_MASTER_SID,
                                                            period_sid,
                                                            item_master_Sid
                                            FROM   #PRIOR_DATA_TABLE A
                                                   INNER JOIN CCP_DETAILS B
                                                           ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID) A
                                           LEFT JOIN #PRODUCT_FILE_TEMP PF
                                                  ON PF.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
                                                     AND PF.ITEM_MASTER_SID = A.ITEM_MASTER_SID
                                                     AND PF.PERIOD_SID = A.PERIOD_SID
                                    GROUP  BY A.PERIOD,
                                              A.YEAR,
                                              A.HIERARCHY_NO,
                                              A.LEVEL_NO,
                                              A.PARENT_HIERARCHY_NO,
                                              A.PROJECTION_MASTER_SID) GTS
                                ON PDT.PROJECTION_MASTER_SID = GTS.PROJECTION_MASTER_SID
                                   AND PDT.[YEAR] = GTS.YEAR
                                   AND PDT.PERIOD = GTS.PERIOD
                                   AND GTS.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND GTS.LEVEL_NO = PDT.LEVEL_NO
                                   AND GTS.PARENT_HIERARCHY_NO = PDT.PARENT_HIERARCHY_NO
                         LEFT JOIN (SELECT --CONTRACT_SALES_ACTUALS = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                   --                                    OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_SALES, 0), NULL)),
                                   --CONTRACT_UNITS_ACTUALS = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                   --                                    OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_UNITS, 0), NULL)),
                                   --COGS_ACTUALS = Sum(Isnull(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                   --                                 OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_UNITS, 0), NULL), 0) * COALESCE(NULLIF(UOM_VALUE, 0), 1) * COALESCE(NULLIF(UOM_VALUE, 0), 1)),
                                   CONTRACT_SALES_PROJECTED = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                         OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL)),
                                   CONTRACT_UNITS_PROJECTED = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                         OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_UNITS, 0), NULL)),
                                   COGS_PROJECTED = Sum(Isnull(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                                                      OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_UNITS, 0), NULL), 0) * COALESCE(NULLIF(UOM_VALUE, 0), 1) * COALESCE(NULLIF(UOM_VALUE, 0), 1)),
                                   A.PERIOD,
                                   A.[YEAR],
                                   A.HIERARCHY_NO,
                                   A.LEVEL_NO,
                                   A.PARENT_HIERARCHY_NO,
                                   A.PROJECTION_MASTER_SID
                                    FROM   #PRIOR_DATA_TABLE A
                                           INNER JOIN CCP_DETAILS B
                                                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                           INNER JOIN PROJECTION_DETAILS PD
                                                   ON PD.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                           INNER JOIN NM_SALES_PROJECTION_MASTER SPM
                                                   ON SPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                           INNER JOIN #SALES_INCLUSION SI
                                                   ON SI.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                           LEFT JOIN NM_ACTUAL_SALES NPS
                                                  ON NPS.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                                     AND NPS.PERIOD_SID = A.PERIOD_SID
                                           LEFT JOIN NM_SALES_PROJECTION NP
                                                  ON NP.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                                     AND NP.PERIOD_SID = A.PERIOD_SID
                                           LEFT JOIN #ITEM_UOM_DETAILS UOM
                                                  ON UOM.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                           LEFT JOIN #ITEM_PRICING U
                                                  ON B.ITEM_MASTER_SID = U.ITEM_MASTER_SID
                                                     AND A.PERIOD_SID = U.PERIOD_SID
                                    GROUP  BY A.PERIOD,
                                              A.[YEAR],
                                              A.HIERARCHY_NO,
                                              A.LEVEL_NO,
                                              A.PARENT_HIERARCHY_NO,
                                              A.PROJECTION_MASTER_SID) SALES
                                ON SALES.PERIOD = PDT.PERIOD
                                   AND SALES.[YEAR] = PDT.YEAR
                                   AND SALES.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND SALES.PARENT_HIERARCHY_NO = PDT.PARENT_HIERARCHY_NO
                                   AND SALES.LEVEL_NO = PDT.LEVEL_NO
                                   AND SALES.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID
                         LEFT JOIN (SELECT --CONTRACT_DISCOUNT_ACTUALS = Sum(Iif(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
                                   --                                       OR @DEDUCTION_INCLUSION IS NULL ), Isnull(ACTUAL_SALES, 0), NULL)),
                                   A.PERIOD,
                                   A.[YEAR],
                                   A.HIERARCHY_NO,
                                   A.LEVEL_NO,
                                   Sum(DISCOUNT_AMOUNT) ACCRUAL_DISCOUNT,
                                   A.PARENT_HIERARCHY_NO,
                                   CONTRACT_DISCOUNT_PROJECTED = Sum(Iif(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
                                                                            OR @DEDUCTION_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL)),
                                   A.PROJECTION_MASTER_SID
                                    FROM   #PRIOR_DATA_TABLE A
                                           --INNER JOIN CCP_DETAILS B
                                           --        ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                           --INNER JOIN PROJECTION_DETAILS PD
                                           --        ON PD.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                           INNER JOIN NM_DISCOUNT_PROJ_MASTER NDPM
                                                   --ON NDPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                                   ON NDPM.CCP_DETAILS_SID = a.CCP_DETAILS_SID
                                                      AND NDPM.PROJECTION_MASTER_SID = a.PROJECTION_MASTER_SID
                                                      AND Iif(a.level_name = 'Schedule ID', a.HIERARCHY_NO, 1) = Iif(a.level_name = 'Schedule ID', NDPM.RS_CONTRACT_SID, 1)
                                           INNER JOIN #DEDUCTION_INCLUSION DI
                                                   --ON DI.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                                   ON DI.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                      AND DI.RS_CONTRACT_SID = NDPM.RS_CONTRACT_SID
                                           LEFT JOIN NM_ACTUAL_DISCOUNT NAD
                                                  --ON NAD.PROJECTION_DETAILS_SID = NDPM.PROJECTION_DETAILS_SID
                                                  ON NAD.CCP_DETAILS_SID = NDPM.CCP_DETAILS_SID
                                                     AND NDPM.PROJECTION_MASTER_SID = NAD.PROJECTION_MASTER_SID
                                                     AND NDPM.RS_CONTRACT_SID = NAD.RS_CONTRACT_SID
                                                     AND NAD.PERIOD_SID = A.PERIOD_SID
                                           LEFT JOIN NM_DISCOUNT_PROJECTION NDP
                                                  --ON NDPM.PROJECTION_DETAILS_SID = NDP.PROJECTION_DETAILS_SID
                                                  ON NDPM.CCP_DETAILS_SID = NDP.CCP_DETAILS_SID
                                                     AND NDPM.PROJECTION_MASTER_SID = NDP.PROJECTION_MASTER_SID
                                                     AND NDPM.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                                                     AND NDP.PERIOD_SID = A.PERIOD_SID
                                           LEFT JOIN #PRIOR_ACCRUAL_DISCOUNT AD
                                                  ON AD.PROJECTION_MASTER_SID = A.PROJECTION_MASTER_SID
                                                     AND AD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                                     AND NDPM.RS_CONTRACT_SID = AD.RS_CONTRACT_SID
                                                     AND A.PERIOD_SID = AD.PERIOD_SID
                                                     AND A.HIERARCHY_NO = AD.HIERARCHY_NO
                                                     AND A.PARENT_HIERARCHY_NO = AD.PARENT_HIERARCHY_NO
                                                     AND A.LEVEL_NO = AD.LEVEL_NO
                                    WHERE  EXISTS (SELECT 1
                                                   FROM   #DISCOUNT_INFO A
                                                   WHERE  CASE
                                                            WHEN @DISCOUNT_LEVEL = 'PROGRAM' THEN Cast(NDPM.RS_CONTRACT_SID AS VARCHAR(20))
                                                            ELSE NDPM.PRICE_GROUP_TYPE
                                                          END = A.TOKEN)
                                    GROUP  BY PERIOD,
                                              [YEAR],
                                              A.HIERARCHY_NO,
                                              A.LEVEL_NO,
                                              A.PARENT_HIERARCHY_NO,
                                              A.PROJECTION_MASTER_SID) DISC
                                ON DISC.PERIOD = PDT.PERIOD
                                   AND DISC.[YEAR] = PDT.YEAR
                                   AND DISC.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND DISC.PARENT_HIERARCHY_NO = PDT.PARENT_HIERARCHY_NO
                                   AND DISC.LEVEL_NO = PDT.LEVEL_NO
                                   AND DISC.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID
                         LEFT JOIN (SELECT PPA_DISCOUNT_PROJECTED = NULL,
                                           PPA_PROJECTED_RPU = NULL,
                                           NULL AS PPA_PROJECTION_SALES,
                                           NULL AS PPA_PROJECTION_UNITS,
                                           PPA_DISCOUNT_ACTUALS = NULL,
                                           PPA_ACTUAL_RPU = NULL,
                                           NULL AS PPA_ACTUAL_SALES,
                                           NULL AS PPA_ACTUAL_UNITS,
                                           PERIOD=NULL,
                                           YEAR=NULL,
                                           HIERARCHY_NO=NULL,
                                           PARENT_HIERARCHY_NO=NULL,
                                           LEVEL_NO=NULL,
                                           PROJECTION_MASTER_SID=NULL
                                   /* FROM   (SELECT PPA_RPU = Sum(ACTUAL_DISCOUNT_DOLLAR),
                                   ACTUAL_PPA_SALES = Sum(ACTUAL_DISCOUNT_DOLLAR),
                                   A.PERIOD,
                                   A.[YEAR],
                                   A.HIERARCHY_NO,
                                   A.LEVEL_NO,
                                   A.PARENT_HIERARCHY_NO,
                                   ACTUAL_SALES = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                          OR @SALES_INCLUSION IS NULL ), Isnull(NS.ACTUAL_SALES, 0), NULL)),
                                   ACTUAL_UNITS = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                          OR @SALES_INCLUSION IS NULL ), Isnull(NS.ACTUAL_UNITS, 0), NULL) * COALESCE(NULLIF(UOM_VALUE, 0), 1)),
                                   PPA_RPU_PROJ = Sum(PROJECTION_DISCOUNT_DOLLAR),
                                   PROJECTION_PPA_SALES = Sum(PROJECTION_DISCOUNT_DOLLAR),
                                   PROJECTION_SALES = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                              OR @SALES_INCLUSION IS NULL ), Isnull(NSP.PROJECTION_SALES, 0), NULL)),
                                   PROJECTION_UNITS = Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                              OR @SALES_INCLUSION IS NULL ), Isnull(NSP.PROJECTION_UNITS, 0), NULL) * COALESCE(NULLIF(UOM_VALUE, 0), 1)),
                                   A.PROJECTION_MASTER_SID
                                   FROM   #PRIOR_DATA_TABLE A
                                   INNER JOIN CCP_DETAILS B
                                   ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                   INNER JOIN PROJECTION_DETAILS PD
                                   ON PD.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                                   INNER JOIN NM_SALES_PROJECTION_MASTER SPM
                                   ON SPM.PROJECTION_DETAILS_SID = pd.PROJECTION_DETAILS_SID
                                   INNER JOIN #SALES_INCLUSION SI
                                   ON SI.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                   LEFT JOIN NM_ACTUAL_PPA NPP
                                   ON SPM.PROJECTION_DETAILS_SID = NPP.PROJECTION_DETAILS_SID
                                   AND NPP.PERIOD_SID = A.PERIOD_SID
                                   LEFT JOIN NM_PPA_PROJECTION NP
                                   ON SPM.PROJECTION_DETAILS_SID = NP.PROJECTION_DETAILS_SID
                                   AND NP.PERIOD_SID = A.PERIOD_SID
                                   INNER JOIN NM_ACTUAL_SALES NS
                                   ON NS.PROJECTION_DETAILS_SID = SPM.PROJECTION_DETAILS_SID
                                   AND NS.PERIOD_SID = A.PERIOD_SID
                                   LEFT JOIN NM_SALES_PROJECTION NSP
                                   ON NSP.PROJECTION_DETAILS_SID = SPM.PROJECTION_DETAILS_SID
                                   AND NSP.PERIOD_SID = A.PERIOD_SID
                                   LEFT JOIN #ITEM_UOM_DETAILS UOM
                                   ON UOM.ITEM_MASTER_SID = B.ITEM_MASTER_SID
                                   GROUP  BY A.PERIOD,
                                   A.[YEAR],
                                   A.HIERARCHY_NO,
                                   A.LEVEL_NO,
                                   A.PARENT_HIERARCHY_NO,
                                   A.PROJECTION_MASTER_SID) A*/) PPA
                                ON PPA.PERIOD = PDT.PERIOD
                                   AND PPA.[YEAR] = PDT.YEAR
                                   AND PPA.HIERARCHY_NO = PDT.HIERARCHY_NO
                                   AND PPA.PARENT_HIERARCHY_NO = PDT.PARENT_HIERARCHY_NO
                                   AND PPA.LEVEL_NO = PDT.LEVEL_NO
                                   AND PPA.PROJECTION_MASTER_SID = PDT.PROJECTION_MASTER_SID
              END

            DECLARE @SQL       NVARCHAR(MAX),
                    @LOOP_CNTR INT,
                    @MAX_CCP   INT

            SET @SQL = '

                           SELECT c.LEVEL_NAME,

                   pr.HIERARCHY_NO,

                   CP_INDICATOR,

                   PERIOD,

                   YEAR, '
            SET @LOOP_CNTR = 1
            SET @MAX_CCP = (SELECT Max(ID)
                            FROM   #PROJECTION_MASTER)

            WHILE @LOOP_CNTR <= @MAX_CCP
              BEGIN
                  SET @SQL += 'EX_FACTORY_SALES_ACCRUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN EX_FACTORY_SALES_ACCRUALS END),

                                                         EX_FACTORY_SALES_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN EX_FACTORY_SALES_ACTUALS END),

                                                         EX_FACTORY_SALES_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN EX_FACTORY_SALES_PROJECTED END),0),

                                                         DEMAND_SALES_ACCRUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DEMAND_SALES_ACCRUALS END),

                                                         DEMAND_SALES_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DEMAND_SALES_ACTUALS END),

                                                         DEMAND_SALES_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DEMAND_SALES_PROJECTED END),0),

                                  INVENTORY_WITHDRAWAL_SALES_ACCRUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN INVENTORY_WITHDRAWAL_SALES_ACCRUALS END),

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

                                                         EX_FACTORY_SALES_ACCRUAL_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN EX_FACTORY_SALES_ACCRUAL_PERCENT END),

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

                                                         DEMAND_SALES_ACCRUAL_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN DEMAND_SALES_ACCRUAL_PERCENT END),

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

                                                         INVENTORY_WITHDRAWAL_SALES_ACCRUAL_PERCENT_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN INVENTORY_WITHDRAWAL_SALES_ACCRUAL_PERCENT END),

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

                                                         CONTRACT_SALES_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN CONTRACT_SALES_ACCRUAL END),

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

                                                         CONTRACT_UNITS_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN CONTRACT_UNITS_ACCRUAL END),

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

                                                         TOTAL_DISCOUNT_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN IIF(TOTAL_DISCOUNT_ACCRUAL=0,NULL,TOTAL_DISCOUNT_ACCRUAL) END),

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

                                                         TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_DISCOUNT_ACCRUAL_PERCENTAGE END),

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

                                                         TOTAL_RPU_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN TOTAL_RPU_ACCRUAL END),

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

                                                         NET_SALES_ACCRUAL_'
                              + Cast( @LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_SALES_ACCRUAL END),

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

                                                         COGS_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN COGS_ACCRUAL END),

                                                         COGS_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN COGS_ACTUALS END),

                                                         COGS_PROJECTED_'
                              + Cast( @LOOP_CNTR AS VARCHAR(10))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN COGS_PROJECTED END),0),

                                                         NET_PROFIT_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_PROFIT_ACCRUAL END),

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

                                                         NET_SALES_OF_EX_FACTORY_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_SALES_OF_EX_FACTORY_ACCRUAL END),

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

                                                         DISCOUNT_OF_EX_FACTORY_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' THEN DISCOUNT_OF_EX_FACTORY_ACCRUAL END),

                                                         DISCOUNT_OF_EX_FACTORY_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' THEN DISCOUNT_OF_EX_FACTORY_ACTUALS END),

                                                         DISCOUNT_OF_EX_FACTORY_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' = ISNULL(MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(20))
                              + ' THEN DISCOUNT_OF_EX_FACTORY_PROJECTED END),0), 

                                                         NET_EX_FACTORY_SALES_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_EX_FACTORY_SALES_ACCRUAL END),

                                                         NET_EX_FACTORY_SALES_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_EX_FACTORY_SALES_ACTUALS END),

                                                         NET_EX_FACTORY_SALES_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(10))
                              + ' THEN NET_EX_FACTORY_SALES_PROJECTED END),

                                                         NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACCRUAL_'
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' THEN NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACCRUAL END),

                                                         NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS_'
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' THEN NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_ACTUALS END),

                                                         NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED_'
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' = MAX(CASE WHEN ID = '
                              + Cast(@LOOP_CNTR AS VARCHAR(MAX))
                              + ' THEN NET_EX_FACTORY_SALES_OF_EX_FACTORY_SALES_PROJECTED END),

                                  '
                  SET @LOOP_CNTR += 1
              END

            --SET @SQL = LEFT(@SQL, Len(@SQL) - 1)
            SET @SQL += ' C.PARENT_HIERARCHY_NO FROM   #PIVOT_RESULT PR JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID  = PR.PROJECTION_ID

                       JOIN #CCP C ON PR.HIERARCHY_NO = C.HIERARCHY_NO
AND PR.LEVEL_NO=c.LEVEL_NO
                                     AND PR.PARENT_HIERARCHY_NO=COALESCE(C.PARENT_HIERARCHY_NO,C.HIERARCHY_NO)


                GROUP  BY   C.LEVEL_NO,c.LEVEL_NAME,PR.HIERARCHY_NO,CP_INDICATOR,PERIOD,YEAR,C.PARENT_HIERARCHY_NO                              

                ORDER  BY C.LEVEL_NO, PR.HIERARCHY_NO,C.PARENT_HIERARCHY_NO,[YEAR],PERIOD                   

                           '

            EXEC Sp_executesql
              @SQL
        END
      ELSE
        BEGIN
            IF Object_id('TEMPDB..#RS_COMBINATION') IS NOT NULL
              DROP TABLE #RS_COMBINATION

            CREATE TABLE #RS_COMBINATION
              (
                 HIERARCHY_NO        VARCHAR(100),
                 LEVEL_NAME          VARCHAR(100),
                 CCP_DETAILS_SID     INT,
                 RS_CONTRACT_SID     INT,
                 -- ITEM_MASTER_SID     INT,
                 PARENT_HIERARCHY_NO VARCHAR(8000),
                 DISCOUNT            VARCHAR(100),
                 DEDUCTION_INCLUSION BIT,
                 SALES_INCLUSION     BIT,--FILTER_CCP bit,
                 LEVEL_NO            INT,
                 SELECTED_SID        VARCHAR(50),
                 row_id              INT
              )

            INSERT INTO #RS_COMBINATION
                        (HIERARCHY_NO,
                         LEVEL_NAME,
                         ccp_details_sid,
                         rs_contract_Sid,
                         PARENT_HIERARCHY_NO,
                         DISCOUNT,
                         DEDUCTION_INCLUSION,
                         SALES_INCLUSION,
                         level_no,
                         selected_sid,
                         row_id)
            SELECT ch.HIERARCHY_NO,
                   LEVEL_NAME,
                   md.ccp_details_sid,
                   md.rs_contract_Sid,
                   PARENT_HIERARCHY_NO,
                   --md.selected_sid,
				   md.SELECTED_LEVEL,
                   DEDUCTION_INCLUSION,
                   SALES_INCLUSION,
                   level_no,
                   md.selected_sid,
                   rowid
            FROM   #MULTISELECT_DISCOUNTS md
                   JOIN #CCP ch
                     ON ch.ccp_details_sid = md.ccp_details_sid
                        AND ( md.RS_CONTRACT_SID = ch.RS_CONTRACT_SID
                               OR Isnull(ch.RS_CONTRACT_SID, 0) = 0 )
--select * from #MULTISELECT_DISCOUNTS
            IF Object_id('TEMPDB..#FILE_DATA') IS NOT NULL
              DROP TABLE #FILE_DATA

            CREATE TABLE #FILE_DATA
              (
                 PERIOD              INT,
                 YEAR                INT,
                 RS_CONTRACT_SID     INT,
                 GTS_SALES_ACTUALS   NUMERIC(38, 6),
                 GTS_SALES_PROJECTED NUMERIC(38, 6),
                 HIERARCHY_NO        VARCHAR(100),
                 LEVEL_NO            INT,
                 DISCOUNT            VARCHAR(100),
                 PARENT_HIERARCHY_NO VARCHAR(8000)
              )

            SET @SQL = 'INSERT INTO #FILE_DATA (

       PERIOD

       ,YEAR

       ,RS_CONTRACT_SID

       ,GTS_SALES_ACTUALS

       ,GTS_SALES_PROJECTED

       ,HIERARCHY_NO

       ,LEVEL_NO

       ,DISCOUNT

       ,PARENT_HIERARCHY_NO

       )

       SELECT P.PERIOD

       ,P.YEAR

       ,RS_CONTRACT_SID

       ,GTS_SALES_ACTUALS = SUM(EXFACTORY_ACTUAL_SALES)

       ,GTS_SALES_PROJECTED = SUM(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES))

       ,HIERARCHY_NO

       ,LEVEL_NO

       ,DISCOUNT

       ,PARENT_HIERARCHY_NO FROM '
                       + @PRODUCT_TABLE
                       + ' PF JOIN #PERIOD P ON P.PERIOD_SID = PF.PERIOD_SID INNER JOIN (

       SELECT DISTINCT r.HIERARCHY_NO

              ,r.DISCOUNT

              ,ITEM_MASTER_SID

              ,r.LEVEL_NO

              ,r.RS_CONTRACT_SID

              ,r.PARENT_HIERARCHY_NO

       FROM #RS_COMBINATION r join #ccp_details_temp cd on cd.ccp_details_sid=r.ccp_details_sid

       ) CI ON PF.ITEM_MASTER_SID = CI.ITEM_MASTER_SID GROUP BY P.PERIOD

       ,P.YEAR

       ,RS_CONTRACT_SID

       ,DISCOUNT

       ,HIERARCHY_NO

       ,LEVEL_NO

       ,PARENT_HIERARCHY_NO'

            EXEC Sp_executesql
              @SQL

            IF Object_id('TEMPDB..#DISCOUNT_DATA_TABLE') IS NOT NULL
              DROP TABLE #DISCOUNT_DATA_TABLE

            SELECT HIERARCHY_NO,
                   PERIOD,
                   YEAR,
                   DISCOUNT,
                   PARENT_HIERARCHY_NO,
                   SELECTED_SID,
                   LEVEL_NO--,row_id
            INTO   #DISCOUNT_DATA_TABLE
            FROM   (SELECT DISTINCT HIERARCHY_NO,
                                    DISCOUNT,
                                    PARENT_HIERARCHY_NO,
                                    SELECTED_SID,
                                    LEVEL_NO,
                                    row_id
                    FROM   #RS_COMBINATION) r
                   CROSS JOIN (SELECT DISTINCT PERIOD,
                                               YEAR
                               FROM   #PERIOD
                               WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                              --WHERE  PERIOD_SID BETWEEN 601 AND 672--@END_PERIOD_SID
                              ) P
							
            IF Object_id('TEMPDB..#actuals_details') IS NOT NULL
              DROP TABLE #actuals_details

            SELECT ccp_details_sid,
                   rs_model_sid,
                   period_sid,
                   Max(DISCOUNT) AS DISCOUNT,
                   Sum(SALES)    SALES,
                   Sum(units)    units,
                   rs_contract_Sid--,selected_sid
            INTO   #actuals_details
            FROM   (SELECT ad.ccp_details_sid,
                           ad.rs_model_sid,
                           ad.period_sid,
                           Sum(DISCOUNT)                                      AS DISCOUNT,
                           Sum(Iif(QUANTITY_INCLUSION = 'Y', SALES, NULL))    SALES,
                           Sum(Iif(QUANTITY_INCLUSION = 'Y', QUANTITY, NULL)) units,
                           QUANTITY_INCLUSION,
                           rs_contract_Sid--,selected_sid
                    FROM   actuals_details ad
                           JOIN #MULTISELECT_DISCOUNTS md
                             ON md.CCP_DETAILS_SID = ad.CCP_DETAILS_SID
                                AND md.RS_MODEL_SID = ad.RS_MODEL_SID
                    GROUP  BY ad.ccp_details_sid,
                              ad.rs_model_sid,
                              ad.period_sid,
                              QUANTITY_INCLUSION,
                              rs_contract_Sid)ad
            GROUP  BY ccp_details_sid,
                      rs_model_sid,
                      period_sid,
                      rs_contract_Sid--,selected_sid

            IF Object_id('TEMPDB..#ACCRUAL_DISCOUNT1') IS NOT NULL
              DROP TABLE #ACCRUAL_DISCOUNT1;

            CREATE TABLE #ACCRUAL_DISCOUNT1
              (
                 --CCP_DETAILS_SID     INT,
                 PERIOD          INT,
                 year            INT,
                 --RS_CONTRACT_SID     INT,
                 DISCOUNT_AMOUNT NUMERIC(38, 6),
                 --PARENT_HIERARCHY_NO VARCHAR(8000),
                 HIERARCHY_NO    VARCHAR(100),
                 LEVEL_NO        INT,
                 Discount        varchar(100)
              )

            SET @SQL_ACC = ' ;

 

WITH CTE

AS (

       SELECT DISTINCT COMPANY_MASTER_SID

              ,CONTRACT_MASTER_SID

              ,CD.ITEM_MASTER_SID

              ,A.CCP_DETAILS_SID

              ,PERIOD_SID

              ,PERIOD_DATE

              ,YEAR

              ,RS.RS_CONTRACT_SID

              ,RS.RS_MODEL_SID

              ,A.PARENT_HIERARCHY_NO

              ,A.HIERARCHY_NO

              ,A.LEVEL_NO
			  ,a.DISCOUNT
       FROM #RS_COMBINATION A

       JOIN #CCP_DETAILS_TEMP CD ON CD.CCP_DETAILS_SID=A.CCP_DETAILS_SID

       JOIN #MULTISELECT_DISCOUNTS RS ON RS.CCP_DETAILS_SID = A.CCP_DETAILS_SID

       AND RS.SELECTED_LEVEL=A.DISCOUNT

       JOIN #PERIOD P ON P.PERIOD_SID BETWEEN @START_PERIOD_SID

                     AND @END_PERIOD_SID

                    

       )

INSERT INTO #ACCRUAL_DISCOUNT1 (

       --CCP_DETAILS_SID,

       PERIOD,year

       --,RS_CONTRACT_SID

       ,DISCOUNT_AMOUNT

       ,HIERARCHY_NO

       --,PARENT_HIERARCHY_NO

       ,LEVEL_NO
	   ,DISCOUNT
       )
select --a.CCP_DETAILS_SID,

       period,year
	   --,A.RS_CONTRACT_SID,sum(DISCOUNT_AMOUNT),HIERARCHY_NO
       ,sum(DISCOUNT_AMOUNT),HIERARCHY_NO

       --,PARENT_HIERARCHY_NO

       ,LEVEL_NO,DISCOUNT from 
(SELECT A2.CCP_DETAILS_SID

       ,PERIOD_SID

       ,A2.RS_CONTRACT_SID

       ,SUM(DEDUCTION_AMOUNT) / (DATEDIFF(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE) + 1) DISCOUNT_AMOUNT

       ,HIERARCHY_NO

       ,PARENT_HIERARCHY_NO

       ,LEVEL_NO
	   ,a2.DISCOUNT
FROM ACCRUAL_MASTER A1

JOIN CTE A2 ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, 0))))

              AND CONVERT(DATETIME, DATEADD(MM, - 1, DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_END_DATE, 0))))

       AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID

       AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID

       AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID

       AND A2.RS_MODEL_SID = A1.RS_MODEL_SID

       AND DATEADD(DD, 1, EOMONTH(A1.ACCRUAL_PERIOD_START_DATE, - 1)) >= @STARTFROM

       AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJECTION_DATE

GROUP BY A2.CCP_DETAILS_SID

       ,PERIOD_SID

       ,A2.RS_CONTRACT_SID

       ,ACCRUAL_PERIOD_START_DATE

       ,ACCRUAL_PERIOD_END_DATE

       ,HIERARCHY_NO

       ,PARENT_HIERARCHY_NO

       ,LEVEL_NO,a2.DISCOUNT)a  join #period p on p.period_sid=a.period_sid
	   group by --a.CCP_DETAILS_SID
	   HIERARCHY_NO
       ,period,year

       --,A.RS_CONTRACT_SID,HIERARCHY_NO

       --,PARENT_HIERARCHY_NO

       ,LEVEL_NO,DISCOUNT
'

            EXEC Sp_executesql
              @SQL_ACC,
              N'@START_PERIOD_SID INT,@END_PERIOD_SID INT,@STARTFROM DATETIME,@PROJECTION_DATE DATETIME',
              @START_PERIOD_SID = @START_PERIOD_SID,
              @END_PERIOD_SID = @END_PERIOD_SID,
              @STARTFROM = @STARTFROM,
              @PROJECTION_DATE = @PROJECTION_DATE

            IF Object_id('TEMPDB.DBO.#CURRENT_SALES', 'U') IS NOT NULL
              DROP TABLE #CURRENT_SALES;

            CREATE TABLE #CURRENT_SALES
              (
                 [HIERARCHY_NO]     VARCHAR(max),
                 [DISCOUNT]         varchar(100),
                 [level_no]         INT,
                 [period]           INT,
                 [year]             INT,
                 [PROJECTION_SALES] NUMERIC(22, 6),
                 [PROJECTION_UNITS] NUMERIC(22, 6),
                 Actual_sales       NUMERIC(22, 6),
                 actual_units       NUMERIC(22, 6)
              );

            SET @SQL_ACC=Concat(' INSERT INTO #CURRENT_SALES
                  ([HIERARCHY_NO],
                   [DISCOUNT],
                   [level_no],
                   [period],
                   [year],
                   [PROJECTION_SALES],
                   [PROJECTION_UNITS],Actual_sales,actual_units)
      SELECT rc.HIERARCHY_NO,
             rc.DISCOUNT,
             rc.level_no,
             period,
             year,
             Sum(PROJECTION_SALES) AS PROJECTION_SALES,
             Sum(PROJECTION_UNITS) AS PROJECTION_UNITS,
			 Actual_sales=sum(sales),actual_units=sum(units)
      -- into #CURRENT_SALES
      FROM   (SELECT DISTINCT HIERARCHY_NO,
                              ccp_details_sid,
                              DISCOUNT,
                              level_no
              FROM   #RS_COMBINATION) rc
             CROSS JOIN (SELECT period_sid,
                                period,
                                year
                         FROM   #period p
                         WHERE  P.PERIOD_SID BETWEEN ', @START_PERIOD_SID, ' AND ', @END_PERIOD_SID, ')p
             left JOIN ', @S_PROJECTION_TABLE, ' sa
               ON rc.ccp_details_sid = sa.ccp_details_sid and p.period_Sid=sa.period_sid
			 left join (select sum(sales) sales,sum(units) units,ccp_details_Sid,period_sid from #actuals_details
			 group by ccp_details_Sid,period_sid) ad 
			 ON rc.ccp_details_sid = ad.ccp_details_sid and p.period_Sid=ad.period_sid
      GROUP  BY rc.HIERARCHY_NO,
                rc.DISCOUNT,
                rc.level_no,
                period,
                year')

            EXEC Sp_executesql
              @SQL_ACC

            IF Object_id('TEMPDB.DBO.#CURRENT_Discount', 'U') IS NOT NULL
              DROP TABLE #CURRENT_Discount;

            CREATE TABLE #CURRENT_DISCOUNT
              (
                 [HIERARCHY_NO]  VARCHAR(max),
                 [DISCOUNT]      varchar(100),
                 [level_no]      INT,
                 [period]        INT,
                 [year]          INT,
                 Discount_amount NUMERIC(22, 6),
                 actual_discount NUMERIC(22, 6)
              );

            IF @CP_INDICATOR IN ( 'C', 'P' )
              BEGIN
                  SET @SQL_ACC=Concat(' INSERT INTO #CURRENT_Discount
                  ([HIERARCHY_NO],
                   [DISCOUNT],
                   [level_no],
                   [period],
                   [year],
                   [Discount_amount],actual_discount)
				   SELECT r.HIERARCHY_NO,
       r.DISCOUNT,
       r.level_no,
       d.period,
       d.year,
       Sum(iif(INDICATOR=1,d.discount,0)) Discount_amount,
	   Sum(iif(INDICATOR=0,d.discount,0)) actual_discount
FROM   (SELECT DISTINCT HIERARCHY_NO,
                        DISCOUNT,
                        level_no,row_id,selected_sid
        FROM   #RS_COMBINATION) r
       left JOIN ', @discount_table, ' d
       ON d.HIERARCHY_NO LIKE r.HIERARCHY_NO + ''%''
		   AND r.selected_sid = d.rs_contract_sid
GROUP  BY r.HIERARCHY_NO,
          r.DISCOUNT,
          r.level_no,
          d.period,
          d.year')
              END
            ELSE
              BEGIN
                  SET @SQL_ACC=Concat(' INSERT INTO #CURRENT_Discount
                  ([HIERARCHY_NO],
                   [level_no],
                   [period],
                   [year],
                   [Discount_amount],actual_discount)
				   SELECT r.HIERARCHY_NO,
             r.level_no,
             d.period,
             d.year,
             Sum(Iif(INDICATOR = 1, d.discount, 0)) Discount_amount,
             Sum(Iif(INDICATOR = 0, d.discount, 0)) actual_discount
      FROM   (SELECT DISTINCT HIERARCHY_NO,                            
                              level_no,
                              row_id
              FROM   #RS_COMBINATION) r
             LEFT JOIN (SELECT c.DISCOUNT,
                               c.PERIOD,
                               c.YEAR,
                               s.HIERARCHY_NO,
                               INDICATOR
                        FROM   ', @discount_table, ' c
                               JOIN ',@CUSTOM_CCP_SALES,' s
                                 ON s.ROWID = c.HIERARCHY_NO
                                    AND s.CUST_VIEW_MASTER_SID = ', @CUSTOM_VIEW_MASTER_SID, ') d
       ON d.HIERARCHY_NO LIKE r.HIERARCHY_NO + ''%''
GROUP  BY r.HIERARCHY_NO,
         
          r.level_no,
          d.period,
          d.year')
              END

            EXEC Sp_executesql
              @SQL_ACC
			 
            IF Object_id('TEMPDB..#DPIVOT_TABLE') IS NOT NULL
              DROP TABLE #DPIVOT_TABLE

            CREATE TABLE #DPIVOT_TABLE
              (
                 PROJECTION_MASTER_SID            INT,
                 LEVEL_NAME                       VARCHAR(100),
                 LEVEL_No                         INT,
                 HIERARCHY_NO                     VARCHAR(8000),
                 PARENT_HIERARCHY_NO              VARCHAR(8000),
                 CP_INDICATOR                     CHAR(1),
                 PERIOD                           INT,
                 YEAR                             INT,
                 DISCOUNT_AMOUNT_ACCRUAL          NUMERIC(38, 6),
                 DISCOUNT_AMOUNT                  NUMERIC(38, 6),
                 DISCOUNT_AMOUNT_PROJECTED        NUMERIC(38, 6),
                 DISCOUNT_RATE_ACCRUAL            NUMERIC(38, 6),
                 DISCOUNT_RATE                    NUMERIC(38, 6),
                 DISCOUNT_RATE_PROJECTED          NUMERIC(38, 6),
                 DISCOUNT_RPU_ACCRUAL             NUMERIC(38, 6),
                 DISCOUNT_RPU                     NUMERIC(38, 6),
                 DISCOUNT_RPU_PROJECTED           NUMERIC(38, 6),
                 DISCOUNT_OF_EX_FACTORY_ACRUAL    NUMERIC(38, 6),
                 DISCOUNT_OF_EX_FACTORY_ACTUALS   NUMERIC(38, 6),
                 DISCOUNT_OF_EX_FACTORY_PROJECTED NUMERIC(38, 6),
                 DISCOUNT                         VARCHAR(100),
                 SELECTED_SID                     INT
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
                         DISCOUNT_OF_EX_FACTORY_ACTUALS,
                         DISCOUNT_OF_EX_FACTORY_PROJECTED,
                         PERIOD,
                         YEAR,
                         HIERARCHY_NO,
                         LEVEL_NAME,
                         DISCOUNT_AMOUNT_ACCRUAL,
                         PARENT_HIERARCHY_NO,
                         LEVEL_No,
                         SELECTED_SID)
            SELECT DISTINCT @FIRST_PROJ_SID     AS PROJECTION_MASTER_SID,
                            dt.DISCOUNT,
                            @CP_INDICATOR       CP_INDICATOR,
                            DISCOUNT_AMOUNT = CASE
                                                WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN Isnull(cd.actual_discount, 0)
                                                ELSE NULL
                                              END,
                            DISCOUNT_AMOUNT_PROJECTED = Isnull(cd.Discount_amount, 0),
                            DISCOUNT_RATE = CASE
                                              WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN Isnull(cd.actual_discount / NULLIF(cs.ACTUAL_SALES, 0), 0) * 100
                                              ELSE NULL
                                            END,
                            DISCOUNT_RATE_PROJECTED = Isnull(cd.Discount_amount / NULLIF(cS.PROJECTION_SALES, 0), 0) * 100,
                            DISCOUNT_RPU = CASE
                                             WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN Isnull(cd.actual_discount / NULLIF(cS.ACTUAL_UNITS, 0), 0)
                                             ELSE NULL
                                           END,
                            DISCOUNT_RPU_PROJECTED = Isnull(cd.Discount_amount / NULLIF(cS.PROJECTION_UNITS, 0), 0),
                            DISCOUNT_OF_EX_FACTORY_ACTUALS = CASE
                                                               WHEN @CURRENT_DATE > Datefromparts(DT.[YEAR], DT.PERIOD, 01) THEN Isnull(cd.actual_discount / NULLIF(Fd.GTS_SALES_ACTUALS, 0), 0) * 100
                                                               ELSE NULL
                                                             END,
                            DISCOUNT_OF_EX_FACTORY_PROJECTED = Isnull(cd.Discount_amount / NULLIF(Fd.GTS_SALES_PROJECTED, 0), 0) * 100,
                            DT.PERIOD,
                            DT.[YEAR],
                            DT.HIERARCHY_NO,
                            NULL                AS LEVEL_NAME,
                            ad1.DISCOUNT_amount AS DISCOUNT_AMOUNT_ACCRUAL,
                            DT.HIERARCHY_NO     PARENT_HIERARCHY_NO,
                            dt.LEVEL_No,
                            DT.SELECTED_SID         SELECTED_SID
            FROM   #DISCOUNT_DATA_TABLE dt
                   LEFT JOIN #CURRENT_DISCOUNT cd
                          ON cd.[HIERARCHY_NO] = dt.[HIERARCHY_NO]
                             AND cd.[DISCOUNT] = dt.[DISCOUNT]
                             AND cd.[level_no] = dt.[level_no]
                             AND cd.[period] = dt.[period]
                             AND cd.[year] = dt.[year]
                   LEFT JOIN #CURRENT_SALES CS
                          ON cs.[HIERARCHY_NO] = dt.[HIERARCHY_NO]
                             AND cs.[DISCOUNT] = dt.[DISCOUNT]
                             AND cs.[level_no] = dt.[level_no]
                             AND cs.[period] = dt.[period]
                             AND cs.[year] = dt.[year]
                   LEFT JOIN #ACCRUAL_DISCOUNT1 ad1
                          ON ad1.[HIERARCHY_NO] = dt.[HIERARCHY_NO]
                             AND ad1.[DISCOUNT] = dt.[DISCOUNT]
                             AND ad1.[level_no] = dt.[level_no]
                             AND ad1.[period] = dt.[period]
                             AND ad1.[year] = dt.[year]
                   LEFT JOIN #FILE_DATA fd
                          ON fd.[HIERARCHY_NO] = dt.[HIERARCHY_NO]
                             AND fd.[DISCOUNT] = dt.[DISCOUNT]
                             AND fd.[level_no] = dt.[level_no]
                             AND fd.[period] = dt.[period]
                             AND fd.[year] = dt.[year]

            IF EXISTS (SELECT 1
                       FROM   #PROJECTION_MASTER
                       WHERE  ID > 1)
              BEGIN
                  IF Object_id('TEMPDB..#DPRIOR_PROJECTIONS') IS NOT NULL
                    DROP TABLE #DPRIOR_PROJECTIONS

                  SELECT PD.PROJECTION_DETAILS_SID,
                         C.CCP_DETAILS_SID,
                         PM.PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         level_no,
                         PARENT_HIERARCHY_NO PARENT_HIERARCHY_NO
                  INTO   #DPRIOR_PROJECTIONS
                  FROM   #PROJECTION_MASTER PM
                         INNER JOIN PROJECTION_DETAILS PD
                                 ON PM.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                         INNER JOIN #CCP C
                                 ON C.CCP_DETAILS_SID = PD.CCP_DETAILS_SID
                  WHERE  PM.ID <> 1

                  IF Object_id('TEMPDB..#DPRIOR_DATA_TABLE') IS NOT NULL
                    DROP TABLE #DPRIOR_DATA_TABLE

                  /*
                  SELECT PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         PERIOD,
                         YEAR,
                         DISCOUNT,
                         PARENT_HIERARCHY_NO,
                         level_no
                  INTO   #DPRIOR_DATA_TABLE
                  FROM   (SELECT DISTINCT HIERARCHY_NO,
                                          level_no,
                                          PARENT_HIERARCHY_NO PARENT_HIERARCHY_NO
                          FROM   #CCP) C
                         CROSS JOIN (SELECT DISTINCT PERIOD,
                                                     YEAR
                                     FROM   #PERIOD
                                     WHERE  PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID) P
                         CROSS JOIN #PROJECTION_MASTER PM
                         CROSS JOIN (SELECT DISCOUNT
                                     FROM   #DISCOUNT_INFO) D
                  WHERE  PM.ID <> 1
                  */
                  SELECT PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         PERIOD,
                         YEAR,
                         DISCOUNT,
                         PARENT_HIERARCHY_NO,
                         level_no
                  INTO   #DPRIOR_DATA_TABLE
                  FROM   #DISCOUNT_DATA_TABLE dt
                         CROSS JOIN #PROJECTION_MASTER pm
                  WHERE  PM.ID <> 1

                  IF Object_id('TEMPDB..#SALES_INCLUSION1') IS NOT NULL
                    DROP TABLE #SALES_INCLUSION1

                  SELECT DISTINCT --b.PROJECTION_DETAILS_SID,b.PROJECTION_master_sid,
                  B.CCP_DETAILS_SID,
                  CASE
                    WHEN DESCRIPTION = 'YES' THEN 1
                    ELSE 0
                  END SALES_INCLUSION
                  INTO   #SALES_INCLUSION1
                  FROM   --NM_SALES_PROJECTION_MASTER A
                  ---join #DPRIOR_PROJECTIONS b --ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                  #DPRIOR_PROJECTIONS b
                  /*INNER JOIN PROJECTION_DETAILS B
                  
                           ON A.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID AND EXISTS (
                  
                                         SELECT 1
                  
                                         FROM #DPRIOR_PROJECTIONS PM
                  
                                         WHERE PM.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID AND PM.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                  
                                         )
                  
                     INNER JOIN CCP_DETAILS CD
                  
                           ON CD.CCP_DETAILS_SID = B.CCP_DETAILS_SID*/
                  INNER JOIN #CCP_details_temp CD
                          ON CD.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                  INNER JOIN CFP_CONTRACT_DETAILS CC
                          ON CC.COMPANY_MASTER_SID = CD.COMPANY_MASTER_SID
                  INNER JOIN CFP_CONTRACT CC1
                          ON CC1.CFP_CONTRACT_SID = CC.CFP_CONTRACT_SID
                             AND CC1.CONTRACT_MASTER_SID = CD.CONTRACT_MASTER_SID
                  INNER JOIN HELPER_TABLE HT
                          ON HT.HELPER_TABLE_SID = CC1.SALES_INCLUSION
                  WHERE  EXISTS (SELECT 1
                                 FROM   #MULTISELECT_DISCOUNTS MD
                                 WHERE  MD.CCP_DETAILS_SID = CD.CCP_DETAILS_SID)

                  IF Object_id('TEMPDB..#DEDUCTION_INCLUSION1') IS NOT NULL
                    DROP TABLE #DEDUCTION_INCLUSION1

                  SELECT DISTINCT --B.PROJECTION_DETAILS_SID,
                  B.CCP_DETAILS_SID,--b.PROJECTION_master_sid,
                  RS.RS_CONTRACT_SID,
                  CASE
                    WHEN DESCRIPTION = 'YES' THEN 1
                    ELSE 0
                  END DEDUCTION_INCLUSION
                  INTO   #DEDUCTION_INCLUSION1
                  FROM   #MULTISELECT_DISCOUNTS A
                         INNER JOIN #DPRIOR_PROJECTIONS b
                                 ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID
                         /*INNER JOIN PROJECTION_DETAILS B
                         
                           ON A.CCP_DETAILS_SID = B.CCP_DETAILS_SID AND EXISTS (
                         
                                         SELECT 1
                         
                                         FROM #DPRIOR_PROJECTIONS PM
                         
                                         WHERE PM.PROJECTION_MASTER_SID = B.PROJECTION_MASTER_SID AND PM.PROJECTION_DETAILS_SID = B.PROJECTION_DETAILS_SID
                         
                                         )*/
                         INNER JOIN RS_CONTRACT RS
                                 ON A.RS_CONTRACT_SID = RS.RS_CONTRACT_SID
                         INNER JOIN HELPER_TABLE HT
                                 ON HT.HELPER_TABLE_SID = RS.DEDUCTION_INCLUSION
                  WHERE  EXISTS (SELECT 1
                                 FROM   #MULTISELECT_DISCOUNTS MD
                                 WHERE  MD.CCP_DETAILS_SID = b.CCP_DETAILS_SID)

                  IF Object_id('TEMPDB..#PRIOR_ACCRUAL_DISCOUNT1') IS NOT NULL
                    DROP TABLE #PRIOR_ACCRUAL_DISCOUNT1;

                  CREATE TABLE #PRIOR_ACCRUAL_DISCOUNT1
                    (
                       PROJECTION_MASTER_SID INT,
                       --PROJECTION_DETAILS_SID INT,
                       PERIOD_SID            INT,
                       RS_CONTRACT_SID       INT,
                       DISCOUNT_AMOUNT       NUMERIC(38, 6),
                       HIERARCHY_NO          VARCHAR(100),
                       LEVEL_NO              INT,
                       PARENT_HIERARCHY_NO   VARCHAR(8000),
                       SELECTED_SID          INT,
                       CCP_DETAILS_SID       INT
                    );

                  WITH CTE
                       AS (SELECT DISTINCT COMPANY_MASTER_SID,
                                           CONTRACT_MASTER_SID,
                                           ITEM_MASTER_SID,
                                           DP.PROJECTION_MASTER_SID,
                                           DP.PROJECTION_DETAILS_SID,
                                           PERIOD_SID,
                                           PERIOD_DATE,
                                           YEAR,
                                           RS.RS_CONTRACT_SID,
                                           RS.RS_MODEL_SID,
                                           HIERARCHY_NO,
                                           LEVEL_NO,
                                           PARENT_HIERARCHY_NO,
                                           SELECTED_SID,
                                           a.CCP_DETAILS_SID
                           FROM   #CCP_DETAILS_TEMP A
                                  --INNER JOIN PROJECTION_DETAILS PD
                                  --     ON PD.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                  INNER JOIN #DPRIOR_PROJECTIONS DP
                                          --ON DP.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID AND DP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
                                          ON DP.CCP_DETAILS_SID = A.CCP_DETAILS_SID
                                  INNER JOIN #MULTISELECT_DISCOUNTS RS
                                          ON RS.CCP_DETAILS_SID = DP.CCP_DETAILS_SID
                                  INNER JOIN #PERIOD P
                                          ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID)
                  INSERT INTO #PRIOR_ACCRUAL_DISCOUNT1
                              (PROJECTION_MASTER_SID,
                               --PROJECTION_DETAILS_SID,
                               PERIOD_SID,
                               RS_CONTRACT_SID,
                               DISCOUNT_AMOUNT,
                               HIERARCHY_NO,
                               LEVEL_NO,
                               PARENT_HIERARCHY_NO,
                               SELECTED_SID,
                               ccp_details_sid)
                  SELECT PROJECTION_MASTER_SID,
                         -- A2.PROJECTION_DETAILS_SID,
                         PERIOD_SID,
                         A2.RS_CONTRACT_SID,
                         Sum(DEDUCTION_AMOUNT) / ( Datediff(MM, A1.ACCRUAL_PERIOD_START_DATE, A1.ACCRUAL_PERIOD_END_DATE)
                                                   + 1 ) DISCOUNT_AMOUNT,
                         HIERARCHY_NO,
                         LEVEL_NO,
                         PARENT_HIERARCHY_NO,
                         SELECTED_SID,
                         a2.ccp_details_sid
                  FROM   ACCRUAL_MASTER A1
                         INNER JOIN CTE A2
                                 ON A2.PERIOD_DATE BETWEEN CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, 0)))) AND CONVERT(DATETIME, Dateadd(MM, -1, Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_END_DATE, 0))))
                                    AND A1.CONTRACT_MASTER_SID = A2.CONTRACT_MASTER_SID
                                    AND A1.COMPANY_MASTER_SID = A2.COMPANY_MASTER_SID
                                    AND A1.ITEM_MASTER_SID = A2.ITEM_MASTER_SID
                                    AND A2.RS_MODEL_SID = A1.RS_MODEL_SID
                                    AND Dateadd(DD, 1, Eomonth(A1.ACCRUAL_PERIOD_START_DATE, -1)) >= @STARTFROM
                                    AND A1.ACCRUAL_PERIOD_END_DATE <= @PROJECTION_DATE
                  GROUP  BY PROJECTION_MASTER_SID,
                            --A2.PROJECTION_DETAILS_SID,
                            PERIOD_SID,
                            A2.RS_CONTRACT_SID,
                            ACCRUAL_PERIOD_START_DATE,
                            ACCRUAL_PERIOD_END_DATE,
                            HIERARCHY_NO,
                            LEVEL_NO,
                            PARENT_HIERARCHY_NO,
                            SELECTED_SID,
                            a2.ccp_details_sid

                  IF Object_id('TEMPDB..#DPRIOR_ACTUAL_SALES_DATA') IS NOT NULL
                    DROP TABLE #DPRIOR_ACTUAL_SALES_DATA

                  SELECT s.PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NO,
                         DISCOUNT,
                         PERIOD,
                         [YEAR],
                         Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                    OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_SALES, 0), NULL))                                     AS CONTRACT_SALES_ACTUALS,
                         Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                    OR @SALES_INCLUSION IS NULL ), Isnull(ACTUAL_UNITS, 0), NULL) * COALESCE(NULLIF(UOM_VALUE, 0), 1)) AS CONTRACT_UNITS_ACTUALS,
                         PARENT_HIERARCHY_NO
                  INTO   #DPRIOR_ACTUAL_SALES_DATA
                  FROM   (SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                                          PP.HIERARCHY_NO,
                                          PP.LEVEL_NO,
                                          RC.DISCOUNT,
                                          --PP.PROJECTION_DETAILS_SID,
                                          PP.PARENT_HIERARCHY_NO,
                                          PP.CCP_DETAILS_SID
                          FROM   #DPRIOR_PROJECTIONS PP
                                 --INNER JOIN PROJECTION_DETAILS PD
                                 --     ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                                 INNER JOIN #RS_COMBINATION RC
                                         ON RC.CCP_DETAILS_SID = PP.CCP_DETAILS_SID
                                            AND RC.HIERARCHY_NO = PP.HIERARCHY_NO
                                            AND RC.LEVEL_NO = PP.LEVEL_NO
                                            AND COALESCE(RC.PARENT_HIERARCHY_NO, RC.HIERARCHY_NO) = COALESCE(PP.PARENT_HIERARCHY_NO, PP.HIERARCHY_NO) --PP.PARENT_HIERARCHY_NO
                         ) S
                         INNER JOIN #PERIOD P
                                 ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                         LEFT JOIN NM_ACTUAL_SALES NPS
                                --ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                ON S.CCP_DETAILS_SID = NPS.CCP_DETAILS_SID
                                   AND s.PROJECTION_MASTER_SID = NPS.PROJECTION_MASTER_SID
                                   AND P.PERIOD_SID = NPS.PERIOD_SID
                         INNER JOIN #SALES_INCLUSION1 SI
                                 --ON SI.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                 ON SI.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                         INNER JOIN #CCP_DETAILS_TEMP CD
                                 ON CD.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                         LEFT JOIN #ITEM_UOM_DETAILS UOM
                                ON UOM.ITEM_MASTER_SID = CD.ITEM_MASTER_SID
                  GROUP  BY s.PROJECTION_MASTER_SID,
                            HIERARCHY_NO,
                            LEVEL_NO,
                            DISCOUNT,
                            PERIOD,
                            [YEAR],
                            PARENT_HIERARCHY_NO

                  IF Object_id('TEMPDB..#DPRIOR_SALES_DATA') IS NOT NULL
                    DROP TABLE #DPRIOR_SALES_DATA

                  SELECT s.PROJECTION_MASTER_SID,
                         HIERARCHY_NO,
                         LEVEL_NO,
                         DISCOUNT,
                         PERIOD,
                         [YEAR],
                         Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                    OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL))                                     AS CONTRACT_SALES_PROJECTED,
                         Sum(Iif(( SALES_INCLUSION = @SALES_INCLUSION
                                    OR @SALES_INCLUSION IS NULL ), Isnull(PROJECTION_UNITS, 0), NULL) * COALESCE(NULLIF(UOM_VALUE, 0), 1)) AS CONTRACT_UNITS_PROJECTED,
                         PARENT_HIERARCHY_NO,
                         SELECTED_SID
                  INTO   #DPRIOR_SALES_DATA
                  FROM   (SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                                          PP.HIERARCHY_NO,
                                          PP.LEVEL_NO,
                                          RC.DISCOUNT,
                                          --PP.PROJECTION_DETAILS_SID,
                                          PP.PARENT_HIERARCHY_NO,
                                          PP.CCP_DETAILS_SID,
                                          RC.SELECTED_SID
                          FROM   #DPRIOR_PROJECTIONS PP
                                 --INNER JOIN PROJECTION_DETAILS PD
                                 --     ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                                 INNER JOIN #RS_COMBINATION RC
                                         ON RC.CCP_DETAILS_SID = PP.CCP_DETAILS_SID
                                            AND RC.HIERARCHY_NO = PP.HIERARCHY_NO
                                            AND RC.LEVEL_NO = PP.LEVEL_NO
                                            AND COALESCE(PP.PARENT_HIERARCHY_NO, pp.HIERARCHY_NO) = COALESCE(RC.PARENT_HIERARCHY_NO, RC.HIERARCHY_NO)) S
                         INNER JOIN #PERIOD P
                                 ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                         LEFT JOIN NM_SALES_PROJECTION NPS
                                --ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                ON S.PROJECTION_MASTER_SID = NPS.PROJECTION_MASTER_SID
                                   AND s.CCP_DETAILS_SID = NPS.CCP_DETAILS_SID
                                   AND P.PERIOD_SID = NPS.PERIOD_SID
                         INNER JOIN #SALES_INCLUSION1 SI
                                 ON SI.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                         INNER JOIN #CCP_DETAILS_TEMP CD
                                 ON CD.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                         LEFT JOIN #ITEM_UOM_DETAILS UOM
                                ON UOM.ITEM_MASTER_SID = CD.ITEM_MASTER_SID
                  GROUP  BY s.PROJECTION_MASTER_SID,
                            HIERARCHY_NO,
                            LEVEL_NO,
                            DISCOUNT,
                            PERIOD,
                            [YEAR],
                            PARENT_HIERARCHY_NO,
                            SELECTED_SID

                  IF Object_id('TEMPDB..#PRIOR_ACTUAL_DISCOUNT_DATA') IS NOT NULL
                    DROP TABLE #PRIOR_ACTUAL_DISCOUNT_DATA

                  SELECT s.PROJECTION_MASTER_SID,
                         S.HIERARCHY_NO,
                         S.LEVEL_NO,
                         DISCOUNT,
                         PERIOD,
                         [YEAR],
                         Isnull(Sum(Iif(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
                                           OR @DEDUCTION_INCLUSION IS NULL ), Isnull(ACTUAL_SALES, 0), NULL)), 0) AS CONTRACT_SALES_ACTUALS,
                         Sum(Isnull(DISCOUNT_AMOUNT, 0))                                                          ACCRUAL_DISCOUNT_ACTUAL,
                         S.PARENT_HIERARCHY_NO,
                         S.SELECTED_SID
                  INTO   #PRIOR_ACTUAL_DISCOUNT_DATA
                  FROM   (SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                                          PP.HIERARCHY_NO,
                                          PP.LEVEL_NO,
                                          RC.DISCOUNT,
                                          --PP.PROJECTION_DETAILS_SID,
                                          RC.RS_CONTRACT_SID,
                                          PP.PARENT_HIERARCHY_NO,
                                          RC.SELECTED_SID,
                                          pp.CCP_DETAILS_SID
                          FROM   #DPRIOR_PROJECTIONS PP
                                 --INNER JOIN PROJECTION_DETAILS PD
                                 --ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                                 INNER JOIN #RS_COMBINATION RC
                                         ON RC.CCP_DETAILS_SID = PP.CCP_DETAILS_SID
                                            AND RC.HIERARCHY_NO = PP.HIERARCHY_NO
                                            AND RC.LEVEL_NO = PP.LEVEL_NO
                                            AND COALESCE(PP.PARENT_HIERARCHY_NO, pp.HIERARCHY_NO) = COALESCE(RC.PARENT_HIERARCHY_NO, RC.HIERARCHY_NO)
                         /*WHERE EXISTS (
                         
                                         SELECT 1
                         
                                         FROM #RS_COMBINATION R
                         
                                         INNER JOIN RS_CONTRACT RS
                         
                                                ON RS.RS_CONTRACT_SID = R.RS_CONTRACT_SID
                         
                                         INNER JOIN HELPER_TABLE H
                         
                                                ON H.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE
                         
                                         WHERE H.DESCRIPTION <> 'PRICE PROTECTION' AND RC.DISCOUNT = R.DISCOUNT AND PP.PARENT_HIERARCHY_NO = R.PARENT_HIERARCHY_NO
                         
                                         )*/
                         ) S
                         INNER JOIN #PERIOD P
                                 ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                         LEFT JOIN NM_ACTUAL_DISCOUNT NPS
                                --ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                ON S.CCP_DETAILS_SID = NPS.CCP_DETAILS_SID
                                   AND s.PROJECTION_MASTER_SID = NPS.PROJECTION_MASTER_SID
                                   AND P.PERIOD_SID = NPS.PERIOD_SID
                         INNER JOIN #DEDUCTION_INCLUSION1 DI
                                 --ON DI.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                 ON DI.CCP_DETAILS_SID = S.CCP_DETAILS_SID
                                    AND DI.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                         LEFT JOIN #PRIOR_ACCRUAL_DISCOUNT1 AD
                                ON AD.PROJECTION_MASTER_SID = S.PROJECTION_MASTER_SID
                                   AND AD.ccp_details_sid = S.ccp_details_sid
                                   AND AD.RS_CONTRACT_SID = NPS.RS_CONTRACT_SID
                                   AND AD.HIERARCHY_NO = S.HIERARCHY_NO
                                   AND COALESCE(S.PARENT_HIERARCHY_NO, S.HIERARCHY_NO) = COALESCE(AD.PARENT_HIERARCHY_NO, AD.HIERARCHY_NO)
                                   AND AD.LEVEL_NO = S.LEVEL_NO
                                   AND AD.PERIOD_SID = P.PERIOD_SID
                  WHERE  EXISTS (SELECT 1
                                 FROM   #MULTISELECT_DISCOUNTS md
                                 WHERE  md.ccp_details_sid = di.CCP_DETAILS_SID
                                        AND md.RS_CONTRACT_SID = NPS.RS_CONTRACT_SID)
                  GROUP  BY S.PROJECTION_MASTER_SID,
                            S.HIERARCHY_NO,
                            S.LEVEL_NO,
                            DISCOUNT,
                            PERIOD,
                            [YEAR],
                            S.PARENT_HIERARCHY_NO,
                            S.SELECTED_SID

                  IF Object_id('TEMPDB..#PRIOR_DISCOUNT_DATA') IS NOT NULL
                    DROP TABLE #PRIOR_DISCOUNT_DATA

                  SELECT s.PROJECTION_MASTER_SID,
                         s.HIERARCHY_NO,
                         s.LEVEL_NO,
                         DISCOUNT,
                         PERIOD,
                         [YEAR],
                         Isnull(Sum(Iif(( DEDUCTION_INCLUSION = @DEDUCTION_INCLUSION
                                           OR @DEDUCTION_INCLUSION IS NULL ), Isnull(PROJECTION_SALES, 0), NULL)), 0) AS CONTRACT_SALES_PROJECTED,
                         Sum(Isnull(DISCOUNT_AMOUNT, 0))                                                              ACCRUAL_DISCOUNT_PROJ,
                         S.PARENT_HIERARCHY_NO,
                         S.SELECTED_SID
                  INTO   #PRIOR_DISCOUNT_DATA
                  FROM   (SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                                          PP.HIERARCHY_NO,
                                          PP.LEVEL_NO,
                                          RC.DISCOUNT,
                                          --PP.PROJECTION_DETAILS_SID,
                                          RC.RS_CONTRACT_SID,
                                          PP.PARENT_HIERARCHY_NO,
                                          RC.SELECTED_SID,
                                          pp.CCP_DETAILS_SID
                          FROM   #DPRIOR_PROJECTIONS PP
                                 --INNER JOIN PROJECTION_DETAILS PD
                                 --     ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                                 INNER JOIN #RS_COMBINATION RC
                                         ON RC.CCP_DETAILS_SID = PP.CCP_DETAILS_SID
                                            AND RC.HIERARCHY_NO = PP.HIERARCHY_NO
                                            AND RC.LEVEL_NO = PP.LEVEL_NO
                                            AND COALESCE(PP.PARENT_HIERARCHY_NO, pp.HIERARCHY_NO) = COALESCE(RC.PARENT_HIERARCHY_NO, RC.HIERARCHY_NO)
                          WHERE  EXISTS (SELECT 1
                                         FROM   #MULTISELECT_DISCOUNTS md
                                         WHERE  md.ccp_details_sid = RC.CCP_DETAILS_SID
                                                AND md.RS_CONTRACT_SID = RC.RS_CONTRACT_SID)
                         /*WHERE EXISTS (
                         
                                         SELECT 1
                         
                                         FROM #RS_COMBINATION R
                         
                                         INNER JOIN RS_CONTRACT RS
                         
                                                ON RS.RS_CONTRACT_SID = R.RS_CONTRACT_SID
                         
                                         INNER JOIN HELPER_TABLE H
                         
                                                ON H.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE
                         
                                         WHERE H.DESCRIPTION <> 'PRICE PROTECTION' AND RC.DISCOUNT = R.DISCOUNT AND PP.PARENT_HIERARCHY_NO = R.PARENT_HIERARCHY_NO
                         
                                         )*/
                         ) S
                         INNER JOIN #PERIOD P
                                 ON P.PERIOD_SID BETWEEN @START_PERIOD_SID AND @END_PERIOD_SID
                         LEFT JOIN NM_DISCOUNT_PROJECTION NPS
                                --ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID
                                ON S.PROJECTION_MASTER_SID = NPS.PROJECTION_MASTER_SID
                                   AND S.ccp_details_sid = NPS.ccp_details_sid
                                   AND NPS.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                                   AND P.PERIOD_SID = NPS.PERIOD_SID
                         INNER JOIN (SELECT DISTINCT *
                                     FROM   #DEDUCTION_INCLUSION1) DI
                                 --ON DI.PROJECTION_DETAILS_SID = S.PROJECTION_DETAILS_SID
                                 ON DI.ccp_details_sid = S.ccp_details_sid
                                    AND DI.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                         LEFT JOIN #PRIOR_ACCRUAL_DISCOUNT1 AD
                                ON AD.PROJECTION_MASTER_SID = S.PROJECTION_MASTER_SID
                                   AND AD.ccp_details_sid = S.ccp_details_sid
                                   AND AD.RS_CONTRACT_SID = S.RS_CONTRACT_SID
                                   AND AD.HIERARCHY_NO = S.HIERARCHY_NO
                                   AND COALESCE(S.PARENT_HIERARCHY_NO, S.HIERARCHY_NO) = COALESCE(AD.PARENT_HIERARCHY_NO, AD.HIERARCHY_NO)
                                   AND AD.LEVEL_NO = S.LEVEL_NO
                                   AND AD.PERIOD_SID = P.PERIOD_SID
                  WHERE  EXISTS (SELECT 1
                                 FROM   #MULTISELECT_DISCOUNTS md
                                 WHERE  md.ccp_details_sid = di.CCP_DETAILS_SID
                                        AND md.RS_CONTRACT_SID = NPS.RS_CONTRACT_SID)
                  GROUP  BY s.PROJECTION_MASTER_SID,
                            S.HIERARCHY_NO,
                            S.LEVEL_NO,
                            DISCOUNT,
                            PERIOD,
                            [YEAR],
                            S.PARENT_HIERARCHY_NO,
                            S.SELECTED_SID

                  IF Object_id('TEMPDB..#PRIOR_ACTUAL_PPA_DATA') IS NOT NULL
                    DROP TABLE #PRIOR_ACTUAL_PPA_DATA

                  /*
                  
                     SELECT PROJECTION_MASTER_SID,
                  
                           HIERARCHY_NO,
                  
                           LEVEL_NAME,
                  
                           DISCOUNT,
                  
                           PERIOD,
                  
                           [YEAR],
                  
                           ISNULL(SUM(ACTUAL_DISCOUNT_DOLLAR), 0) AS CONTRACT_SALES_ACTUALS,
                  
                           PARENT_HIERARCHY_NO
                  
                     INTO #PRIOR_ACTUAL_PPA_DATA
                  
                     FROM (
                  
                           SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                  
                                  PP.HIERARCHY_NO,
                  
                                  PP.LEVEL_NAME,
                  
                                  RC.DISCOUNT,
                  
                                  PD.PROJECTION_DETAILS_SID,
                  
                                  PP.PARENT_HIERARCHY_NO
                  
                           FROM #DPRIOR_PROJECTIONS PP
                  
                           INNER JOIN PROJECTION_DETAILS PD
                  
                                  ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                  
                           INNER JOIN #RS_COMBINATION RC
                  
                                  ON RC.CCP_DETAILS_SID = PD.CCP_DETAILS_SID AND RC.HIERARCHY_NO = PP.HIERARCHY_NO AND RC.LEVEL_NAME = PP.LEVEL_NAME AND PP.PARENT_HIERARCHY_NO = RC.PARENT_HIERARCHY_NO
                  
                           WHERE EXISTS (
                  
                                         SELECT 1
                  
                                         FROM #RS_COMBINATION R
                  
                                         INNER JOIN RS_CONTRACT RS
                  
                                                ON RS.RS_CONTRACT_SID = R.RS_CONTRACT_SID
                  
                                         INNER JOIN HELPER_TABLE H
                  
                                                ON H.HELPER_TABLE_SID = RS.REBATE_PROGRAM_TYPE
                  
                                         WHERE H.DESCRIPTION = 'PRICE PROTECTION' AND RC.DISCOUNT = R.DISCOUNT AND PP.PARENT_HIERARCHY_NO = R.PARENT_HIERARCHY_NO
                  
                                         )
                  
                           ) S
                  
                     INNER JOIN #PERIOD P
                  
                           ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
                  
                                         AND @END_PERIOD_SID
                  
                     LEFT JOIN NM_ACTUAL_PPA NPS
                  
                           ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID AND P.PERIOD_SID = NPS.PERIOD_SID
                  
                     GROUP BY PROJECTION_MASTER_SID,
                  
                           HIERARCHY_NO,
                  
                           LEVEL_NAME,
                  
                           DISCOUNT,
                  
                           PERIOD,
                  
                           [YEAR],
                  
                           PARENT_HIERARCHY_NO
                  
                           */
                  IF Object_id('TEMPDB..#PRIOR_PPA_DATA') IS NOT NULL
                    DROP TABLE #PRIOR_PPA_DATA

                  /*SELECT PROJECTION_MASTER_SID,
                  
                           HIERARCHY_NO,
                  
                           LEVEL_NAME,
                  
                           DISCOUNT,
                  
                           PERIOD,
                  
                           [YEAR],
                  
                           isnull(SUM(PROJECTION_DISCOUNT_DOLLAR), 0) AS CONTRACT_SALES_PROJECTED,
                  
                           PARENT_HIERARCHY_NO
                  
                     INTO #PRIOR_PPA_DATA
                  
                     FROM (
                  
                           SELECT DISTINCT PP.PROJECTION_MASTER_SID,
                  
                                  PP.HIERARCHY_NO,
                  
                                  PP.LEVEL_NAME,
                  
                                  RC.DISCOUNT,
                  
                                  PD.PROJECTION_DETAILS_SID,
                  
                                  PP.PARENT_HIERARCHY_NO
                  
                           FROM #DPRIOR_PROJECTIONS PP
                  
                           INNER JOIN PROJECTION_DETAILS PD
                  
                                  ON PD.PROJECTION_DETAILS_SID = PP.PROJECTION_DETAILS_SID
                  
                           INNER JOIN #RS_COMBINATION RC
                  
                                  ON RC.CCP_DETAILS_SID = PD.CCP_DETAILS_SID AND RC.HIERARCHY_NO = PP.HIERARCHY_NO AND RC.LEVEL_NAME = PP.LEVEL_NAME AND PP.PARENT_HIERARCHY_NO = RC.PARENT_HIERARCHY_NO
                  
                           WHERE EXISTS (
                  
                                         SELECT 1
                  
                                         FROM #RS_COMBINATION r
                  
                                         INNER JOIN RS_CONTRACT rs
                  
                                                ON rs.RS_CONTRACT_SID = r.RS_CONTRACT_SID
                  
                                         INNER JOIN HELPER_TABLE h
                  
                                                ON h.HELPER_TABLE_SID = rs.REBATE_PROGRAM_TYPE
                  
                                         WHERE h.DESCRIPTION = 'price protection' AND rc.DISCOUNT = r.DISCOUNT AND PP.PARENT_HIERARCHY_NO = R.PARENT_HIERARCHY_NO
                  
                                         )
                  
                           ) S
                  
                     INNER JOIN #PERIOD P
                  
                           ON P.PERIOD_SID BETWEEN @START_PERIOD_SID
                  
                                         AND @END_PERIOD_SID
                  
                     LEFT JOIN NM_PPA_PROJECTION NPS
                  
                           ON S.PROJECTION_DETAILS_SID = NPS.PROJECTION_DETAILS_SID AND P.PERIOD_SID = NPS.PERIOD_SID
                  
                     GROUP BY PROJECTION_MASTER_SID,
                  
                           HIERARCHY_NO,
                  
                           LEVEL_NAME,
                  
                           DISCOUNT,
                  
                           PERIOD,
                  
                           [YEAR],
                  
                           PARENT_HIERARCHY_NO
                  
                           */
                  IF Object_id('TEMPDB.DBO.#PRIOR_SALES', 'U') IS NOT NULL
                    DROP TABLE #PRIOR_SALES;

                  SELECT FD.PROJECTION_MASTER_SID,
                         FD.YEAR,
                         FD.PERIOD,
                         FD.HIERARCHY_NO,
                         FD.DISCOUNT,
                         NM_ACTUAL_SALES = COALESCE(( NAS.CONTRACT_SALES_ACTUALS ), 0),
                         NM_ACTUAL_UNITS = COALESCE(( NAS.CONTRACT_UNITS_ACTUALS ), 0),
                         NM_PROJECTED_SALES = COALESCE(( NSP.CONTRACT_SALES_PROJECTED ), 0),
                         NM_PROJECTED_UNITS = COALESCE(( NSP.CONTRACT_UNITS_PROJECTED ), 0),
                         FD.PARENT_HIERARCHY_NO,
                         FD.LEVEL_NO,
                         NSP.SELECTED_SID
                  INTO   #PRIOR_SALES
                  FROM   (SELECT DISTINCT YEAR,
                                          PERIOD,
                                          HIERARCHY_NO,
                                          DISCOUNT,
                                          PROJECTION_MASTER_SID,
                                          PARENT_HIERARCHY_NO,
                                          level_no
                          FROM   #DPRIOR_DATA_TABLE) FD
                         LEFT JOIN #DPRIOR_SALES_DATA NSP
                                ON FD.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
                                   AND FD.YEAR = NSP.YEAR
                                   AND FD.PERIOD = NSP.PERIOD
                                   AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
                                   AND FD.DISCOUNT = NSP.DISCOUNT
                                   AND COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO) = COALESCE(NSP.PARENT_HIERARCHY_NO, NSP.HIERARCHY_NO) ---FD.PARENT_HIERARCHY_NO = NSP.PARENT_HIERARCHY_NO
                         LEFT JOIN #DPRIOR_ACTUAL_SALES_DATA NAS
                                ON FD.PROJECTION_MASTER_SID = NAS.PROJECTION_MASTER_SID
                                   AND FD.YEAR = NAS.YEAR
                                   AND FD.PERIOD = NAS.PERIOD
                                   AND FD.HIERARCHY_NO = NAS.HIERARCHY_NO
                                   AND FD.LEVEL_NO = NAS.LEVEL_NO
                                   AND FD.DISCOUNT = NAS.DISCOUNT
                                   AND COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO) = COALESCE(NAS.PARENT_HIERARCHY_NO, NAS.HIERARCHY_NO) ---NAS.PARENT_HIERARCHY_NO
                  IF Object_id('TEMPDB.DBO.#PRIOR_DISCOUNT', 'U') IS NOT NULL
                    DROP TABLE #PRIOR_DISCOUNT;

                  SELECT FD.PROJECTION_MASTER_SID,
                         FD.YEAR,
                         FD.PERIOD,
                         ACTUAL_SALES = COALESCE(( NAD.CONTRACT_SALES_ACTUALS ), 0),
                         PROJECTION_SALES = COALESCE(( NSP.CONTRACT_SALES_PROJECTED ), 0),
                         COALESCE(ACCRUAL_DISCOUNT_PROJ, ACCRUAL_DISCOUNT_ACTUAL) ACCRUAL_DISCOUNT,
                         FD.DISCOUNT,
                         FD.HIERARCHY_NO,
                         FD.LEVEL_NO,
                         FD.PARENT_HIERARCHY_NO,
                         NSP.SELECTED_SID
                  INTO   #PRIOR_DISCOUNT
                  FROM   (SELECT YEAR,
                                 PERIOD,
                                 HIERARCHY_NO,
                                 LEVEL_NO,
                                 DISCOUNT,
                                 PROJECTION_MASTER_SID,
                                 PARENT_HIERARCHY_NO
                          FROM   #DPRIOR_DATA_TABLE A
                         /*WHERE EXISTS (
                         
                                         SELECT 1
                         
                                         FROM #PRIOR_DISCOUNT_DATA B
                         
                                         WHERE A.DISCOUNT = B.DISCOUNT
                         
                                         )*/
                         ) FD
                         JOIN #PRIOR_DISCOUNT_DATA NSP
                           ON FD.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID
                              AND FD.YEAR = NSP.YEAR
                              AND FD.PERIOD = NSP.PERIOD
                              AND FD.DISCOUNT = NSP.DISCOUNT
                              AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO
                              AND FD.LEVEL_NO = NSP.LEVEL_NO
                              AND COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO) = COALESCE(NSP.PARENT_HIERARCHY_NO, NSP.HIERARCHY_NO)
                         LEFT JOIN #PRIOR_ACTUAL_DISCOUNT_DATA NAD
                                ON FD.PROJECTION_MASTER_SID = NAD.PROJECTION_MASTER_SID
                                   AND FD.YEAR = NAD.YEAR
                                   AND FD.PERIOD = NAD.PERIOD
                                   AND FD.DISCOUNT = NAD.DISCOUNT
                                   AND FD.HIERARCHY_NO = NAD.HIERARCHY_NO
                                   AND FD.LEVEL_NO = NAD.LEVEL_NO
                                   AND COALESCE(FD.PARENT_HIERARCHY_NO, FD.HIERARCHY_NO) = COALESCE(NAD.PARENT_HIERARCHY_NO, NAD.HIERARCHY_NO) ---FD.PARENT_HIERARCHY_NO = NAD.PARENT_HIERARCHY_NO
                  IF Object_id('TEMPDB.DBO.#PRIOR_PPA', 'U') IS NOT NULL
                    DROP TABLE #PRIOR_PPA;

              /*
              
                     SELECT FD.PROJECTION_MASTER_SID,
              
                           FD.YEAR,
              
                           FD.PERIOD,
              
                           PPA_ACTUAL = COALESCE((NSP.CONTRACT_SALES_ACTUALS), 0),
              
                           PPA_DISCOUNT_PROJECTED = COALESCE((NAP.CONTRACT_SALES_PROJECTED), 0),
              
                           FD.DISCOUNT,
              
                           FD.HIERARCHY_NO,
              
                           FD.LEVEL_NAME,
              
                           FD.PARENT_HIERARCHY_NO
              
                     INTO #PRIOR_PPA
              
                     FROM (
              
                           SELECT YEAR,
              
                                  PERIOD,
              
                                  HIERARCHY_NO,
              
                                  LEVEL_NAME,
              
                                  DISCOUNT,
              
                                  PROJECTION_MASTER_SID,
              
                                  PARENT_HIERARCHY_NO
              
                           FROM #DPRIOR_DATA_TABLE A
              
                           WHERE NOT EXISTS (
              
                                         SELECT 1
              
                                         FROM #PRIOR_DISCOUNT_DATA B
              
                                         WHERE A.DISCOUNT = B.DISCOUNT
              
                                         )
              
                           ) FD
              
                     LEFT JOIN #PRIOR_ACTUAL_PPA_DATA NSP
              
                           ON FD.PROJECTION_MASTER_SID = NSP.PROJECTION_MASTER_SID AND fd.YEAR = NSP.YEAR AND FD.PERIOD = NSP.PERIOD AND FD.DISCOUNT = NSP.DISCOUNT AND FD.HIERARCHY_NO = NSP.HIERARCHY_NO AND FD.LEVEL_NAME = NSP.LEVEL_NAME AND FD.PARENT_HIERARCHY_NO = NSP.PARENT_HIERARCHY_NO
              
                     LEFT JOIN #PRIOR_PPA_DATA NAP
              
                           ON FD.PROJECTION_MASTER_SID = NAP.PROJECTION_MASTER_SID AND FD.YEAR = NAP.YEAR AND FD.PERIOD = NAP.PERIOD AND FD.DISCOUNT = NAP.DISCOUNT AND FD.HIERARCHY_NO = NAP.HIERARCHY_NO AND FD.LEVEL_NAME = NAP.LEVEL_NAME AND FD.PARENT_HIERARCHY_NO = NAP.PARENT_HIERARCHY_NO
              
                           */
                  --    select distinct * from   #DPIVOT_TABLE order by PROJECTION_MASTER_SID,HIERARCHY_NO,year,period
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
                               LEVEL_NAME,
                               DISCOUNT_AMOUNT_ACCRUAL,
                               PARENT_HIERARCHY_NO,
                               level_no,
                               SELECTED_SID)
                  SELECT DISTINCT s.PROJECTION_MASTER_SID,
                                  dt.DISCOUNT,
                                  @CP_INDICATOR    CP_INDICATOR,
                                  DISCOUNT_AMOUNT = Isnull(DT.ACTUAL_SALES, 0),
                                  DISCOUNT_AMOUNT_PROJECTED = Isnull(DT.PROJECTION_SALES, 0),
                                  DISCOUNT_RATE = Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_SALES, 0), 0) * 100,
                                  DISCOUNT_RATE_PROJECTED = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_SALES, 0), 0) * 100,
                                  DISCOUNT_RPU = Isnull(DT.ACTUAL_SALES / NULLIF(S.NM_ACTUAL_UNITS, 0), 0),
                                  DISCOUNT_RPU = Isnull(DT.PROJECTION_SALES / NULLIF(S.NM_PROJECTED_UNITS, 0), 0),
                                  Isnull(DT.ACTUAL_SALES / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100,
                                  Isnull(DT.PROJECTION_SALES / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100,
                                  s.PERIOD,
                                  s.[YEAR],
                                  s.HIERARCHY_NO,
                                  NULL             AS LEVEL_NAME,
                                  ACCRUAL_DISCOUNT AS DISCOUNT_AMOUNT_ACCRUAL,
                                  S.PARENT_HIERARCHY_NO,
                                  DT.level_no,
                                  DT.SELECTED_SID
                  FROM   #PRIOR_SALES S
                         INNER JOIN #PRIOR_DISCOUNT DT
                                 ON S.PROJECTION_MASTER_SID = DT.PROJECTION_MASTER_SID
                                    AND S.level_no = DT.level_no
                                    AND DT.HIERARCHY_NO = S.HIERARCHY_NO
                                    --AND DT.LEVEL_NO = S.LEVEL_NO
                                    AND DT.PERIOD = S.PERIOD
                                    AND DT.[YEAR] = S.[YEAR]
                                    AND DT.DISCOUNT = S.DISCOUNT
                                    AND DT.SELECTED_SID = S.SELECTED_SID
                                    AND COALESCE(DT.PARENT_HIERARCHY_NO, DT.HIERARCHY_NO) = COALESCE(S.PARENT_HIERARCHY_NO, S.HIERARCHY_NO) ---DT.PARENT_HIERARCHY_NO = S.PARENT_HIERARCHY_NO
                         LEFT JOIN (SELECT PF.PROJECTION_MASTER_SID,
                                           P.PERIOD,
                                           P.YEAR,
                                           dp.HIERARCHY_NO,
                                           dp.LEVEL_NO,
                                           dp.DISCOUNT,
                                           GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
                                           GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
                                           DP.PARENT_HIERARCHY_NO
                                    FROM   #PRODUCT_FILE_TEMP PF
                                           INNER JOIN #PERIOD P
                                                   ON P.PERIOD_SID = PF.PERIOD_SID
                                           INNER JOIN (SELECT DISTINCT DP.PROJECTION_MASTER_SID,
                                                                       dp.HIERARCHY_NO,
                                                                       dp.LEVEL_NO,
                                                                       rs.DISCOUNT,
                                                                       cdt.ITEM_MASTER_SID,
                                                                       DP.PARENT_HIERARCHY_NO
                                                       FROM   #DPRIOR_PROJECTIONS DP
                                                              --INNER JOIN PROJECTION_DETAILS PD
                                                              --     ON PD.PROJECTION_MASTER_SID = DP.PROJECTION_MASTER_SID AND PD.PROJECTION_DETAILS_SID = DP.PROJECTION_DETAILS_SID
                                                              INNER JOIN #RS_COMBINATION RS
                                                                      ON RS.CCP_DETAILS_SID = DP.CCP_DETAILS_SID
                                                                         AND COALESCE(DP.PARENT_HIERARCHY_NO, DP.HIERARCHY_NO) = COALESCE(RS.PARENT_HIERARCHY_NO, RS.HIERARCHY_NO)
                                                                         AND RS.HIERARCHY_NO = DP.HIERARCHY_NO
                                                                         AND RS.LEVEL_NO = DP.LEVEL_NO
                                                              INNER JOIN #CCP_DETAILS_TEMP cdt
                                                                      ON cdt.CCP_DETAILS_SID = rs.CCP_DETAILS_SID) DP
                                                   ON DP.PROJECTION_MASTER_SID = PF.PROJECTION_MASTER_SID
                                                      AND DP.ITEM_MASTER_SID = PF.ITEM_MASTER_SID
                                    GROUP  BY PF.PROJECTION_MASTER_SID,
                                              dp.HIERARCHY_NO,
                                              dp.LEVEL_NO,
                                              dp.DISCOUNT,
                                              P.PERIOD,
                                              P.YEAR,
                                              DP.PARENT_HIERARCHY_NO) F
                                ON F.PROJECTION_MASTER_SID = DT.PROJECTION_MASTER_SID
                                   AND F.YEAR = DT.YEAR
                                   AND F.PERIOD = DT.PERIOD
                                   AND F.HIERARCHY_NO = DT.HIERARCHY_NO
                                   AND COALESCE(dt.PARENT_HIERARCHY_NO, dt.HIERARCHY_NO) = COALESCE(f.PARENT_HIERARCHY_NO, f.HIERARCHY_NO)
                                   AND F.LEVEL_NO = DT.LEVEL_NO
                                   AND F.DISCOUNT = DT.DISCOUNT
              /*UNION ALL
              
                    
              
                     SELECT P.PROJECTION_MASTER_SID,
              
                           P.DISCOUNT,
              
                           @CP_INDICATOR CP_INDICATOR,
              
                           PPA_ACTUAL,
              
                           DISCOUNT_AMOUNT_PROJECTED = P.PPA_DISCOUNT_PROJECTED,
              
                           PPA_RATE = isnull(PPA_ACTUAL / nullif(s.NM_ACTUAL_SALES, 0), 0) * 100,
              
                           PPA_RATE_PROJECTION = isnull(PPA_DISCOUNT_PROJECTED / nullif(s.NM_ACTUAL_SALES, 0), 0) * 100,
              
                           PPA_RPU = isnull(PPA_ACTUAL / nullif(s.NM_PROJECTED_SALES, 0), 0),
              
                           PPA_RPU_PROJECTION = isnull(PPA_DISCOUNT_PROJECTED / nullif(s.NM_PROJECTED_UNITS, 0), 0),
              
                           Isnull(P.PPA_ACTUAL / NULLIF(F.GTS_SALES_ACTUALS, 0), 0) * 100,
              
                           Isnull(P.PPA_DISCOUNT_PROJECTED / NULLIF(F.GTS_SALES_PROJECTED, 0), 0) * 100,
              
                           P.PERIOD,
              
                           P.[YEAR],
              
                           P.HIERARCHY_NO,
              
                           P.LEVEL_NAME,
              
                           NULL AS DISCOUNT_AMOUNT_ACCRUAL,
              
                           P.PARENT_HIERARCHY_NO
              
                     FROM #PRIOR_PPA P
              
                     INNER JOIN #PRIOR_SALES S
              
                           ON P.PROJECTION_MASTER_SID = S.PROJECTION_MASTER_SID AND S.HIERARCHY_NO = P.HIERARCHY_NO AND P.LEVEL_NAME = S.LEVEL_NAME AND P.PARENT_HIERARCHY_NO = S.PARENT_HIERARCHY_NO AND P.YEAR = S.YEAR AND P.PERIOD = S.PERIOD AND P.DISCOUNT = S.DISCOUNT
              
                     LEFT JOIN (
              
                           SELECT PF.PROJECTION_MASTER_SID,
              
                                  P.PERIOD,
              
                                  P.YEAR,
              
                                  dp.HIERARCHY_NO,
              
                                  dp.LEVEL_NAME,
              
                                  dp.DISCOUNT,
              
                                  GTS_SALES_ACTUALS = Sum(EXFACTORY_ACTUAL_SALES),
              
                                  GTS_SALES_PROJECTED = Sum(COALESCE(EXFACTORY_FORECAST_SALES, EXFACTORY_ACTUAL_SALES)),
              
                                  DP.PARENT_HIERARCHY_NO
              
                           FROM #PRODUCT_FILE_TEMP PF
              
                           INNER JOIN #PERIOD P
              
                                  ON P.PERIOD_SID = PF.PERIOD_SID
              
                           INNER JOIN (
              
                                  SELECT DISTINCT DP.PROJECTION_MASTER_SID,
              
                                         DP.HIERARCHY_NO,
              
                                         DP.LEVEL_NAME,
              
                                         RS.ITEM_MASTER_SID,
              
                                         RS.DISCOUNT,
              
                                         DP.PARENT_HIERARCHY_NO
              
                                  FROM #DPRIOR_PROJECTIONS DP
              
                                  INNER JOIN PROJECTION_DETAILS PD
              
                                         ON PD.PROJECTION_MASTER_SID = DP.PROJECTION_MASTER_SID AND PD.PROJECTION_DETAILS_SID = DP.PROJECTION_DETAILS_SID
              
                                  INNER JOIN #RS_COMBINATION RS
              
                                         ON RS.CCP_DETAILS_SID = PD.CCP_DETAILS_SID AND RS.PARENT_HIERARCHY_NO = DP.PARENT_HIERARCHY_NO AND RS.HIERARCHY_NO = DP.HIERARCHY_NO AND RS.LEVEL_NAME = DP.LEVEL_NAME
              
                                  ) dp
              
                                  ON dp.PROJECTION_MASTER_SID = pf.PROJECTION_MASTER_SID AND dp.ITEM_MASTER_SID = pf.ITEM_MASTER_SID
              
                           GROUP BY PF.PROJECTION_MASTER_SID,
              
                                  dp.HIERARCHY_NO,
              
                                  dp.LEVEL_NAME,
              
                                  dp.DISCOUNT,
              
                                  P.PERIOD,
              
                                  P.YEAR,
              
                                  DP.PARENT_HIERARCHY_NO
              
                           ) F
              
                           ON F.PROJECTION_MASTER_SID = P.PROJECTION_MASTER_SID AND F.YEAR = P.YEAR AND F.PERIOD = P.PERIOD AND F.HIERARCHY_NO = P.HIERARCHY_NO AND F.LEVEL_NAME = P.LEVEL_NAME AND F.DISCOUNT = P.DISCOUNT AND F.PARENT_HIERARCHY_NO = P.PARENT_HIERARCHY_NO*/
              END

            BEGIN
                DECLARE @SQL2       NVARCHAR(MAX),
                        @LOOP_CNTR1 INT,
                        @MAX_CCP1   INT

                SET @SQL2 = '

                SELECT  DISCOUNT,pr.HIERARCHY_NO,

                   PERIOD,

                   YEAR, '
                SET @LOOP_CNTR1 = 1
                SET @MAX_CCP1 = (SELECT Max(ID)
                                 FROM   #PROJECTION_MASTER)

                WHILE @LOOP_CNTR1 <= @MAX_CCP1
                  BEGIN
                      SET @SQL2 += 'DISCOUNT_AMOUNT_ACCRUAL_'
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' = MAX(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' THEN IIF(DISCOUNT_AMOUNT_ACCRUAL=0,NULL,DISCOUNT_AMOUNT_ACCRUAL) END),

                                  DISCOUNT_AMOUNT_'
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' = MAX(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' THEN DISCOUNT_AMOUNT END),

                                  DISCOUNT_AMOUNT_PROJECTED_'
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' = ISNULL(MAX(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' THEN DISCOUNT_AMOUNT_PROJECTED END),0),

                                                         DISCOUNT_RATE_ACCRUAL_'
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' = MAX(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' THEN DISCOUNT_RATE_ACCRUAL END),

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

                                  DISCOUNT_RPU_ACCRUAL_'
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' = MAX(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' THEN DISCOUNT_RPU_ACCRUAL END),

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

                                  DISCOUNT_OF_EX_FACTORY_ACRUAL_'
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' = MAX(CASE WHEN ID = '
                                   + Cast(@LOOP_CNTR1 AS VARCHAR(10))
                                   + ' THEN DISCOUNT_OF_EX_FACTORY_ACRUAL END),

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

                --SET @SQL2 = LEFT(@SQL2, Len(@SQL2) - 1)
                SET @SQL2 += ' pr.PARENT_HIERARCHY_NO ,PR.SELECTED_SID   FROM   #DPIVOT_TABLE PR JOIN #PROJECTION_MASTER PM ON PM.PROJECTION_MASTER_SID  = PR.PROJECTION_MASTER_SID

                     --join #CCP c on pr.HIERARCHY_NO = c.HIERARCHY_NO

                                  -- AND COALESCE(C.PARENT_HIERARCHY_NO,C.HIERARCHY_NO)=PR.PARENT_HIERARCHY_NO
								 
                GROUP  BY pr.level_no,DISCOUNT,pr.HIERARCHY_NO,CP_INDICATOR,PERIOD,YEAR       ,pr.PARENT_HIERARCHY_NO  ,PR.SELECTED_SID                      

                ORDER BY pr.level_no,pr.HIERARCHY_NO,pr.PARENT_HIERARCHY_NO  ,PR.SELECTED_SID   ,'
                             + CASE WHEN ( @VIEW_TAB = 'PIVOT' AND @VIEW = 'DETAIL_DISCOUNT' ) THEN 'YEAR,PERIOD' ELSE 'DISCOUNT,YEAR,PERIOD' END

                EXEC Sp_executesql
                  @SQL2
            END
        END
  END 
GO