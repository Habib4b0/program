IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ACTUAL_MASTER_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[ACTUAL_MASTER_INTERFACE]
        (
           [ACTUAL_INTERFACE_ID]            NUMERIC(38, 0) NOT NULL,
           [ACTUAL_ID]                      VARCHAR(50) NOT NULL,
           [CONTRACT_ID]                    VARCHAR(50) NOT NULL,
           [UPLOAD_DATE]                    DATETIME NULL,
           [PROVISION_ID]                   VARCHAR(50) NOT NULL,
           [ACCRUAL_ACTUAL_START_DATE]      DATETIME NOT NULL,
           [ACCRUAL_ACTUAL_END_DATE]        DATETIME NOT NULL,
           [ITEM_ID]                        VARCHAR(50) NOT NULL,
           [ITEM_IDENTIFIER_CODE_QUALIFIER] VARCHAR(50) NULL,
           [ITEM_NO]                        VARCHAR(50) NULL,
           [SETTLEMENT_METHOD]              VARCHAR(50) NULL,
           [CASH_PAID_DATE]                 DATETIME NOT NULL,
           [AMOUNT]                         NUMERIC(20, 6) NOT NULL,
           [QUANTITY]                       NUMERIC(20, 6) NOT NULL,
           [QUANTITY_INCLUSION]             VARCHAR(10) NOT NULL,
           [SETTLEMENT_NO]                  VARCHAR(100) NULL,
           [INVOICE_LINE_NUMBER]            VARCHAR(100) NULL,
           [ACCOUNT_ID]                     VARCHAR(100) NOT NULL,
           [ACCT_IDENTIFIER_CODE_QUALIFIER] VARCHAR(100) NULL,
           [ACCOUNT_NO]                     VARCHAR(100) NULL,
           [ACCOUNT_NAME]                   VARCHAR(100) NULL,
           [ANALYSIS_CODE]                  CHAR(50) NULL,
           [IS_ACTIVE]                      CHAR(2) NULL,
           [COM_DIV_MKT_BRAND_PROD_KEY]     VARCHAR(100) NULL,
           [PARENTCOM_DIVMKT_BRAND_PRODKEY] VARCHAR(100) NULL,
           [PRICE_ADJUSTMENT_NAME]          VARCHAR(100) NULL,
           [PRICE]                          NUMERIC(20, 6) NULL,
           [RECORD_SEQUENCE]                VARCHAR(30) NULL,
           [SENT_OUT]                       CHAR(5) NULL,
           [ACCRUAL_PROCESSED]              CHAR(5) NULL,
           [DIVISION_ID]                    VARCHAR(38) NULL,
           [MARKET_ID]                      VARCHAR(38) NULL,
           [BRAND_ID]                       VARCHAR(38) NULL,
           [ADD_CHG_DEL_INDICATOR]          VARCHAR(10) NOT NULL,
           [CLAIM_INDICATOR]                VARCHAR(50) NULL,
           [CREATED_BY]                     VARCHAR(50) NULL,
           [CREATED_DATE]                   DATETIME NULL,
           [MODIFIED_BY]                    VARCHAR(50) NULL,
           [MODIFIED_DATE]                  DATETIME NULL,
           [BATCH_ID]                       VARCHAR(38) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [SALES_AMOUNT]                   NUMERIC(20, 6) NOT NULL,
           [ORGANIZATION_KEY]               VARCHAR(50) NOT NULL,
           [MANDATED_DISCOUNT_AMOUNT]       NUMERIC(20, 6) NULL,
           [PROVISION_CLAIM_INDICATOR]      VARCHAR(10) NULL,
           [PROGRAM_STATE_CODE]             VARCHAR(10) NULL,
           [DISPENSED_DATE]                 DATETIME NULL
        )
  END

GO 
