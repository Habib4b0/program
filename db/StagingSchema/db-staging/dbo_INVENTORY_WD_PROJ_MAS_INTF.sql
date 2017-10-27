--------------------------------------INVENTORY_WD_PROJ_MAS_INTF--------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'INVENTORY_WD_PROJ_MAS_INTF'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[INVENTORY_WD_PROJ_MAS_INTF]
        (
           INVENTORY_WD_PROJ_MAS_INTF_ID NUMERIC(38, 0) NOT NULL,
           YEAR                               VARCHAR(5) NOT NULL,
           MONTH                              VARCHAR(25) NOT NULL,
           DAY                                VARCHAR(25) NULL,
           WEEK                               VARCHAR(25) NULL,
           ITEM_ID                            VARCHAR(38) NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER     VARCHAR(25) NULL,
           ITEM_IDENTIFIER                    VARCHAR(50) NULL,
           UNITS_WITHDRAWN                    NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN                   NUMERIC(20, 6) NULL,
		   PRICE							  NUMERIC(22,6) NULL,
           CREATED_BY                         VARCHAR(50) NULL,
           CREATED_DATE                       DATETIME NULL,
           MODIFIED_BY                        VARCHAR(50) NULL,
           MODIFIED_DATE                      DATETIME NULL,
           ADD_CHG_DEL_INDICATOR              VARCHAR(10) NOT NULL,
           BATCH_ID                           VARCHAR(38) NOT NULL,
           SOURCE                             VARCHAR(50) NOT NULL,
           FORECAST_NAME                      VARCHAR(100) NOT NULL,
           FORECAST_VER                       VARCHAR(15) NOT NULL,
           COUNTRY                            VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY                   VARCHAR(50) NOT NULL
        )
  END
  GO