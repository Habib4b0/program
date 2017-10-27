IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'DEMAND_FORECAST_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE   DEMAND_FORECAST_INTERFACE
  (
     DEMAND_FORECAST_INTERFACE_ID   [NUMERIC](38, 0) NOT NULL,
     FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
     FORECAST_YEAR                  [VARCHAR](5) NOT NULL,
     FORECAST_MONTH                 [VARCHAR](25) NOT NULL,
     ITEM_ID                        [VARCHAR](38) NOT NULL,
     ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
     ITEM_IDENTIFIER                [VARCHAR](50) NULL,
     BRAND_ID                       [VARCHAR](50) NULL,
     SEGMENT                        [VARCHAR](50) NULL,
     MARKET_SIZE_UNITS              NUMERIC (22, 6) NOT NULL,
     MARKET_SHARE_RATIO             [VARCHAR](25) NOT NULL,
     MARKET_SHARE_UNITS             NUMERIC (22, 6) NOT NULL,
     UNCAPTURED_UNITS               NUMERIC (22, 6) NOT NULL,
     UNCAPTURED_UNITS_RATIO         [VARCHAR](25) NULL,
     TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
     TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NULL,
     INVENTORY_UNIT_CHANGE          NUMERIC (22, 6) NOT NULL,
     GROSS_UNITS                    NUMERIC (22, 6) NOT NULL,
	 GROSS_PRICE                    NUMERIC (22, 6) NOT NULL,
     GROSS_AMOUNT                   NUMERIC (22, 6) NOT NULL,
     NET_SALES_PRICE                NUMERIC (22, 6) NOT NULL,
     NET_SALES_AMOUNT               NUMERIC (22, 6) NOT NULL,
     CREATED_BY                     [VARCHAR](50) NULL,
     CREATED_DATE                   [DATETIME] NULL,
     MODIFIED_BY                    [VARCHAR](50) NULL,
     MODIFIED_DATE                  [DATETIME] NULL,
     ADD_CHG_DEL_INDICATOR          [VARCHAR](10) NOT NULL,
     BATCH_ID                       [VARCHAR](38) NOT NULL,
     SOURCE                         [VARCHAR](50) NOT NULL,
     FORECAST_NAME                  [VARCHAR](100) NOT NULL,
     FORECAST_VER                   [VARCHAR](15) NOT NULL,
     COUNTRY                        [VARCHAR](25) NOT NULL,
     ORGANIZATION_KEY               [VARCHAR](50) NOT NULL
  ) 

  END

GO