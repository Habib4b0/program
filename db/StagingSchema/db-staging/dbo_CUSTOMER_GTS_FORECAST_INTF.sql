------------------------CUSTOMER_FORECAST_INTERFACE------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_FORECAST_INTF'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[CUSTOMER_GTS_FORECAST_INTF]
        (
           CUSTOMER_GTS_FORECAST_INTF_ID NUMERIC(38, 0) NOT NULL,
           FORECAST_YEAR                 VARCHAR(5) NOT NULL,
           FORECAST_MONTH                VARCHAR(25) NOT NULL,
           ITEM_ID                       VARCHAR(50) NOT NULL,
           COMPANY_ID                    VARCHAR(50) NOT NULL,
           UNITS                         NUMERIC(22, 6) NOT NULL,
           PRICE_TYPE                    VARCHAR(50) NULL,
           PRICE                         NUMERIC(22, 6) NULL,
           SALES_AMOUNT                  NUMERIC(22, 6) NULL,
           SALES_INCLUSION               VARCHAR(10) NULL,
           DEDUCTION_ID                  VARCHAR(50) NULL,
           DEDUCTION_CATEGORY            VARCHAR(50) NULL,
           DEDUCTION_TYPE                VARCHAR(50) NULL,
           DEDUCTION_PROGRAM_TYPE        VARCHAR(50) NULL,
           ADJUSTMENT_CODE               VARCHAR(50) NULL,
           DEDUCTION_RATE               NUMERIC(22, 6) NULL,
           DEDUCTION_AMOUNT              NUMERIC(22, 6) NULL,
           DEDUCTION_INCLUSION           VARCHAR(10) NULL,
           FORECAST_VALUE_TYPE           VARCHAR(50) NULL,
           BRAND                         VARCHAR(50) NULL,
           SEGMENT                       VARCHAR(100) NULL,
           ADD_CHG_DEL_INDICATOR         VARCHAR(10) NOT NULL,
           CREATED_BY                    VARCHAR(50) NULL,
           CREATED_DATE                  DATETIME NULL,
           MODIFIED_BY                   VARCHAR(50) NULL,
           MODIFIED_DATE                 DATETIME NULL,
           BATCH_ID                      VARCHAR(38) NOT NULL,
           SOURCE                        VARCHAR(50) NOT NULL,
           FORECAST_VER                  VARCHAR(15) NOT NULL,
           COUNTRY                       VARCHAR(25) NOT NULL,
           FORECAST_NAME                 VARCHAR(100) NOT NULL,
           FORECAST_DATE                 DATETIME NULL,
           UDC1                          VARCHAR(100) NOT NULL,
           UDC2                          VARCHAR(100) NOT NULL,
           UDC3                          VARCHAR(100) NOT NULL,
           UDC4                          VARCHAR(100) NOT NULL,
           UDC5                          VARCHAR(100) NOT NULL,
           UDC6                          VARCHAR(100) NOT NULL
        )
  END 
GO

------
IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_forecast_INTf'
                      AND COLUMN_NAME = 'UDC1'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_forecast_INTf
        ALTER COLUMN UDC1 VARCHAR(100)  NULL
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_forecast_INTf'
                      AND COLUMN_NAME = 'UDC2'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_forecast_INTf
        ALTER COLUMN UDC2 VARCHAR(100)  NULL
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_forecast_INTf'
                      AND COLUMN_NAME = 'UDC3'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_forecast_INTf
        ALTER COLUMN UDC3 VARCHAR(100)  NULL
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_forecast_INTf'
                      AND COLUMN_NAME = 'UDC4'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_forecast_INTf
        ALTER COLUMN UDC4 VARCHAR(100)  NULL
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_forecast_INTf'
                      AND COLUMN_NAME = 'UDC5'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_forecast_INTf
        ALTER COLUMN UDC5 VARCHAR(100)  NULL
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_forecast_INTf'
                      AND COLUMN_NAME = 'UDC6'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_forecast_INTf
        ALTER COLUMN UDC6 VARCHAR(100)  NULL
  END
GO
