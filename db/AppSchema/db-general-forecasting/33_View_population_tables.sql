IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CUSTOMER_SALES'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[CUSTOMER_SALES]
        (
           HIERARCHY_NO    VARCHAR(8000) NOT NULL,
           PERIOD          SMALLINT NOT NULL,
           [YEAR]          SMALLINT NOT NULL,
           SALES           NUMERIC(22, 6) NULL,
           UNITS           NUMERIC(22, 6) NULL,
           SALES_INCLUSION BIT NULL,
           INDICATOR       BIT NULL,
           ACCOUNT_GROWTH  NUMERIC(22, 6) NULL,
           PRODUCT_GROWTH  NUMERIC(22, 6) NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'PRODUCT_SALES'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[PRODUCT_SALES]
        (
           HIERARCHY_NO    VARCHAR(8000) NOT NULL,
           PERIOD          SMALLINT NOT NULL,
           [YEAR]          SMALLINT NOT NULL,
           SALES           NUMERIC(22, 6) NULL,
           UNITS           NUMERIC(22, 6) NULL,
           SALES_INCLUSION BIT NULL,
           INDICATOR       BIT NULL,
           ACCOUNT_GROWTH  NUMERIC(22, 6) NULL,
           PRODUCT_GROWTH  NUMERIC(22, 6) NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CUSTOM_DISCOUNT'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[CUSTOM_DISCOUNT]
        (
           HIERARCHY_NO        INT NOT NULL,
           RS_CONTRACT_SID     INT NOT NULL,
           PERIOD              SMALLINT NOT NULL,
           [YEAR]              SMALLINT NOT NULL,
           DISCOUNT            NUMERIC(22, 6) NULL,
           SALES               NUMERIC(22, 6) NULL,
           UNITS               NUMERIC(22, 6) NULL,
           DEDUCTION_INCLUSION BIT NULL,
           INDICATOR           BIT NULL,
           GROWTH              NUMERIC(22, 6)
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CUSTOM_SALES'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[CUSTOM_SALES]
        (
           HIERARCHY_NO    INT NOT NULL,
           PERIOD          SMALLINT NOT NULL,
           [YEAR]          SMALLINT NOT NULL,
           SALES           NUMERIC(22, 6) NULL,
           UNITS           NUMERIC(22, 6) NULL,
           SALES_INCLUSION BIT NULL,
           INDICATOR       BIT NULL,
           ACCOUNT_GROWTH  NUMERIC(22, 6) NULL,
           PRODUCT_GROWTH  NUMERIC(22, 6) NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CUSTOMER_DISCOUNT'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[CUSTOMER_DISCOUNT]
        (
           HIERARCHY_NO        VARCHAR(8000) NOT NULL,
           RS_CONTRACT_SID     INT NOT NULL,
           PERIOD              SMALLINT NOT NULL,
           [YEAR]              SMALLINT NOT NULL,
           DISCOUNT            NUMERIC(22, 6) NULL,
           SALES               NUMERIC(22, 6) NULL,
           UNITS               NUMERIC(22, 6) NULL,
           DEDUCTION_INCLUSION BIT NULL,
           INDICATOR           BIT NULL,
           GROWTH              NUMERIC(22, 6)
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'PRODUCT_DISCOUNT'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[PRODUCT_DISCOUNT]
        (
           HIERARCHY_NO        VARCHAR(8000) NOT NULL,
           RS_CONTRACT_SID     INT NOT NULL,
           PERIOD              SMALLINT NOT NULL,
           [YEAR]              SMALLINT NOT NULL,
           SALES               NUMERIC(22, 6) NULL,
           UNITS               NUMERIC(22, 6) NULL,
           DISCOUNT            NUMERIC(22, 6) NULL,
           DEDUCTION_INCLUSION BIT NULL,
           INDICATOR           BIT NULL,
           GROWTH              NUMERIC(22, 6)
        )
  END

GO 
