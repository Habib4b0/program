IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'RETURNS_INTERFACE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE RETURNS_INTERFACE
        (
           RRESERVE_INTERFACE_ID       [NUMERIC](38, 0) NOT NULL,
           RRESERVE_ID                 VARCHAR(50) NOT NULL,
           VERSION                     VARCHAR(50) NOT NULL,
           SKU                         VARCHAR(50) NOT NULL,
           BRAND                       VARCHAR(120) NOT NULL,
           DESCRIPTION                 VARCHAR(250) NOT NULL,
           ORIG_SALE_MONTH             DATETIME NOT NULL,
           ORIG_SALE_UNITS             NUMERIC(22, 6) NOT NULL,
           ORIG_SALE_DOLLARS           NUMERIC(22, 6) NOT NULL,
           ASP                         NUMERIC(22, 6) NOT NULL,
           CUM_RETURN_UNITS            NUMERIC(22, 6) NOT NULL,
           ACTUAL_RETURN_RATE          NUMERIC(22, 6) NOT NULL,
           WEIGHTED_AVG_MONTHS         NUMERIC(22, 6) NOT NULL,
           FIRST_RETURN                DATETIME NOT NULL,
           LAST_RETURN                 DATETIME NOT NULL,
           MAX_EXPIRED_MONTH           DATETIME NOT NULL,
           MAX_EXPIRED_MONS_PLUSCUTOFF DATETIME NOT NULL,
           RETURN_COMPLETE             VARCHAR(1) NOT NULL,
           CALC_USED                   VARCHAR(20) NOT NULL,
           ORIG_SALE_MONTH_CUT_OFF     DATETIME NOT NULL,
           EXPECTED_RETURN_RATE        NUMERIC(22, 6) NOT NULL,
           ESTIMATED_RETURN_UNITS      NUMERIC(22, 6) NOT NULL,
           ADJ_ESTIMATED_RETURN_UNITS  NUMERIC(22, 6) NOT NULL,
           ADJ_VALUE_AT_ORIG_ASP       NUMERIC(22, 6) NOT NULL,
           POS_ESTIMATED_RETURN_UNITS  NUMERIC(22, 6) NOT NULL,
           VALUE_AT_ORIG_ASP           NUMERIC(22, 6) NOT NULL,
           LOAD_DATE                   DATETIME NOT NULL,
           PAST_EXPIRATION             VARCHAR(1) NOT NULL,
           WITHIN_50QRTILE             VARCHAR(1) NOT NULL,
           MAXIMUM                     NUMERIC(22, 6) NOT NULL,
           PCT_75TH                    NUMERIC(22, 6) NOT NULL,
           PCT_50TH                    NUMERIC(22, 6) NOT NULL,
           PCT_25TH                    NUMERIC(22, 6) NOT NULL,
           UPPER_LIMIT                 NUMERIC(22, 6) NOT NULL,
           LOWER_LIMIT                 NUMERIC(22, 6) NOT NULL,
           ADD_CHG_DEL_INDICATOR       VARCHAR(5) NOT NULL,
           BATCH_ID                    VARCHAR(50) NOT NULL,
           SOURCE                      VARCHAR(50) NOT NULL,
        )
  END

GO

----------------------------------------------------------ALTER SCRIPT----------------------------------------------------------
--FIRST_RETURN
IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'RETURNS_INTERFACE'
                     AND COLUMN_NAME = 'FIRST_RETURN'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='datetime'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].RETURNS_INTERFACE
        ALTER COLUMN FIRST_RETURN datetime NULL
  END

GO 
--LAST_RETURN
IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'RETURNS_INTERFACE'
                     AND COLUMN_NAME = 'LAST_RETURN'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='datetime'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].RETURNS_INTERFACE
        ALTER COLUMN LAST_RETURN datetime NULL
  END

GO 
--MAX_EXPIRED_MONTH
IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'RETURNS_INTERFACE'
                     AND COLUMN_NAME = 'MAX_EXPIRED_MONTH'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='datetime'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].RETURNS_INTERFACE
        ALTER COLUMN MAX_EXPIRED_MONTH datetime NULL
  END

GO
--CALC_USED
IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'RETURNS_INTERFACE'
                     AND COLUMN_NAME = 'CALC_USED'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='varchar'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].RETURNS_INTERFACE
        ALTER COLUMN CALC_USED varchar(20) NULL
  END

GO
--ORIG_SALE_MONTH_CUT_OFF

IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'RETURNS_INTERFACE'
                     AND COLUMN_NAME = 'ORIG_SALE_MONTH_CUT_OFF'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='datetime'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].RETURNS_INTERFACE
        ALTER COLUMN ORIG_SALE_MONTH_CUT_OFF datetime NULL
  END

GO