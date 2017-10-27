IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_ACTUAL_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].CUSTOMER_GTS_ACTUAL_INTERFACE
        (
           CUSTOMER_GTS_ACTUAL_INTF_ID NUMERIC(38, 0) NOT NULL,
           SALES_ID                    VARCHAR(50) NOT NULL,
           ORGANIZATION_KEY            VARCHAR(100) NOT NULL,
           ITEM_ID                     VARCHAR(38) NOT NULL,
           ITEM_UOM                    VARCHAR(50) NOT NULL,
           ORDER_NUMBER                VARCHAR(50) NOT NULL,
           INVOICE_NUMBER              VARCHAR(50) NOT NULL,
           INVOICE_LINE_NUMBER         VARCHAR(50) NOT NULL,
           INVOICE_DATE                DATETIME NOT NULL,
           QUANTITY                    NUMERIC(22,6) NOT NULL,
           LOT_NO                      VARCHAR(50) NULL,
           AMOUNT                      NUMERIC(22,6) NOT NULL,
           CONTRACT_ID                 VARCHAR(50) NULL,
           ACCOUNT_ID                  VARCHAR(100) NOT NULL,
           ORDER_RECEIVED_DATE         DATETIME NULL,
           BATCH_ID                    VARCHAR(50) NOT NULL,
           SOURCE                      VARCHAR(50) NOT NULL,
           CREATED_BY                  VARCHAR(50) NOT NULL,
           CREATED_DATE                DATETIME NOT NULL,
           MODIFIED_BY                 VARCHAR(50) NOT NULL,
           MODIFIED_DATE               DATETIME NOT NULL,
           ADD_CHG_DEL_INDICATOR       [VARCHAR](10) NOT NULL,
           PARENT_ACCOUNT_ID           VARCHAR(100) NULL
        )
  END

GO