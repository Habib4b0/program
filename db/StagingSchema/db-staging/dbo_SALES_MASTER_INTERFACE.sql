IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SALES_MASTER_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SALES_MASTER_INTERFACE]
        (
           [SALES_INTERFACE_ID]        NUMERIC(38, 0) NOT NULL,
           [SALES_ID]                  VARCHAR(50) NOT NULL,
           [ORGANIZATION_KEY]          VARCHAR(100) NULL,
           [ITEM_ID]                   VARCHAR(38) NOT NULL,
           [ITEM_NO]                   VARCHAR(50) NOT NULL,
           [ACCOUNT_CODE_QUALIFIER]    VARCHAR(50) NULL,
           [PARENT_ITEM_ID]            VARCHAR(38) NULL,
           [PARENT_ITEM_NO]            VARCHAR(50) NULL,
           [ITEM_UOM]                  VARCHAR(50) NOT NULL,
           [DOC_TYPE]                  VARCHAR(50) NULL,
           [ORDER_NUMBER]              VARCHAR(50) NULL,
           [INVOICE_NUMBER]            VARCHAR(50) NULL,
           [INVOICE_LINE_NUMBER]       VARCHAR(50) NULL,
           [INVOICE_DATE]              DATETIME NOT NULL,
           [ORDER_TYPE]                VARCHAR(50) NULL,
           [ORDER_SUBTYPE]             VARCHAR(50) NULL,
           [ANALYSIS_CODE]             VARCHAR(50) NULL,
           [PRICE_ADJUSTMENT_NAME]     VARCHAR(100) NULL,
           [RECORD_SEQUENCE]           NUMERIC(38, 0) NULL,
           [PRICE]                     NUMERIC(20, 6) NULL,
           [QUANTITY]                  NUMERIC(38, 2) NOT NULL,
           [LOT_NO]                    VARCHAR(50) NULL,
           [AMOUNT]                    NUMERIC(20, 6) NOT NULL,
           [CONTRACT_ID]               VARCHAR(50) NULL,
           [CONTRACT_NO]               VARCHAR(50) NULL,
           [ACCOUNT_TYPE]              VARCHAR(100) NULL,
           [CUSTOMER_SUBTYPE]          VARCHAR(100) NULL,
           [WHOLESALE_OWNER_ID]        VARCHAR(100) NULL,
           [ACCOUNT_NO]                VARCHAR(50) NOT NULL,
           [ACCOUNT_NAME]              VARCHAR(100) NULL,
           [IDENTIFIER_CODE_QUALIFIER] VARCHAR(50) NULL,
           [CUSTOMER_COMPANY_CODE]     VARCHAR(100) NULL,
           [IS_ACTIVE]                 CHAR(1) NULL,
           [COMPANY_ID]                VARCHAR(38) NULL,
           [DIVISION_ID]               VARCHAR(38) NULL,
           [MARKET_ID]                 VARCHAR(38) NULL,
           [BRAND_ID]                  VARCHAR(38) NULL,
           [ADD_CHG_DEL_INDICATOR]     VARCHAR(10) NOT NULL,
           [CREATED_BY]                VARCHAR(50) NULL,
           [CREATED_DATE]              DATETIME NULL,
           [MODIFIED_BY]               VARCHAR(50) NULL,
           [MODIFIED_DATE]             DATETIME NULL,
           [UPLOAD_DATE]               DATETIME NULL,
           [BATCH_ID]                  VARCHAR(38) NOT NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [ORDER_RECEIVED_DATE]       DATETIME NOT NULL,
           [PROVISION_ID]              NUMERIC(22, 0) NULL,
           [ACCOUNT_ID]                VARCHAR(100) NOT NULL
        )
  END

GO