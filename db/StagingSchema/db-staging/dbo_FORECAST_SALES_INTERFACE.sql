IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'FORECAST_SALES_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE [dbo].[FORECAST_SALES_INTERFACE]
        (
           [FORECAST_INTERFACE_ID]             NUMERIC(38, 0) NOT NULL,
           [FORECAST_YEAR]                     VARCHAR(5) NULL,
           [FORECAST_MONTH]                    VARCHAR(25) NULL,
           [NDC]                               VARCHAR(50) NULL,
           [FORECAST_START_DATE]               DATETIME NULL,
           [UNITS]                             NUMERIC(20, 6) NULL,
           [DOLLARS]                           NUMERIC(20, 6) NULL,
           [FORECAST_VALUE_TYPE]               VARCHAR(50) NULL,
           [FORECAST_VALUE]                    VARCHAR(25) NULL,
           [PRODUCT]                           VARCHAR(50) NULL,
           [BRAND]                             VARCHAR(50) NULL,
           [SEGMENT]                           VARCHAR(100) NULL,
           [PERCENTAGE_ESTIMATE]               VARCHAR(25) NULL,
           [PERCENTAGE_ESTIMATE_YEAR]          VARCHAR(5) NULL,
           [ACTUAL_SALES_PERCENTAGE]           VARCHAR(25) NULL,
           [ACTUAL_SALES_PERCENTAGE_MONTH]     VARCHAR(25) NULL,
           [FORECASTED_SALES_PERCENTAGE]       VARCHAR(25) NULL,
           [FORECASTED_SALES_PERCENTAGE_MONTH] VARCHAR(25) NULL,
           [ADD_CHG_DEL_INDICATOR]             VARCHAR(10) NOT NULL,
           [CREATED_BY]                        VARCHAR(50) NULL,
           [CREATED_DATE]                      DATETIME NULL,
           [MODIFIED_BY]                       VARCHAR(50) NULL,
           [MODIFIED_DATE]                     DATETIME NULL,
           [BATCH_ID]                          VARCHAR(38) NOT NULL,
           [SOURCE]                            VARCHAR(50) NULL,
           [FORECAST_VER]                      VARCHAR(15) NULL,
           [PRICE]                             NUMERIC(20, 6) NULL,
           [COUNTRY]                           VARCHAR(25) NOT NULL,
           [FORECAST_NAME]                     VARCHAR(100) NULL,
           [FORECAST_DATE]                     DATETIME NOT NULL
        )
  END
GO