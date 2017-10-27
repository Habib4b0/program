IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'RETURN_RESERVE_INTERFACE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE RETURN_RESERVE_INTERFACE
        (
           RETURN_RESERVE_INTF_ID NUMERIC(38, 0) NOT NULL,
           [YEAR]                 VARCHAR(5) NOT NULL,
           [MONTH]                VARCHAR(25) NOT NULL,
           BRAND_ID               VARCHAR(50) NULL,
           BRAND_NAME             VARCHAR(50) NULL,
           ITEM_ID                VARCHAR(50) NOT NULL,
           ITEM_NO                VARCHAR(50) NULL,
           ITEM_NAME              VARCHAR(50) NULL,
           UNITS                  NUMERIC(22, 6) NULL,
           AMOUNT                 NUMERIC(22, 6) NOT NULL,
           COUNTRY                VARCHAR(25) NULL,
           BUSINESS_UNIT          VARCHAR(50) NOT NULL,
           GL_COMPANY             VARCHAR(50) NOT NULL,
           COMPANY_NO             VARCHAR(50) NOT NULL,
           DIVISION               VARCHAR(50) NOT NULL,
           COST_CENTER            VARCHAR(50) NOT NULL,
           ACCOUNT                VARCHAR(50) NOT NULL,
           PROJECT                VARCHAR(50) NOT NULL,
           FUTURE1                VARCHAR(50) NOT NULL,
           FUTURE2                VARCHAR(50) NOT NULL,
           UDC1                   VARCHAR(100) NULL,
           UDC2                   VARCHAR(100) NULL,
           UDC3                   VARCHAR(100) NULL,
           UDC4                   VARCHAR(100) NULL,
           UDC5                   VARCHAR(100) NULL,
           UDC6                   VARCHAR(100) NULL,
           ADD_CHG_DEL_INDICATOR  VARCHAR(10) NOT NULL,
           CREATED_BY             VARCHAR(50) NOT NULL,
           CREATED_DATE           DATETIME NOT NULL,
           MODIFIED_BY            VARCHAR(50) NOT NULL,
           MODIFIED_DATE          DATETIME NOT NULL,
           BATCH_ID               VARCHAR(38) NOT NULL,
           SOURCE                 VARCHAR(50) NULL
        )
  END

GO