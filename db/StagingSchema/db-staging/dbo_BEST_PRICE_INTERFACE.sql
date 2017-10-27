IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'BEST_PRICE_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[BEST_PRICE_INTERFACE]
        (
           [BEST_PRICE_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [ITEM_ID]                 VARCHAR(50) NOT NULL,
           [ITEM_NO]                 VARCHAR(50) NULL,
           [ITEM_DESC]               VARCHAR(250) NULL,
           [BEGINNING_WAC_PACKAGE]   NUMERIC(20, 6) NULL,
           [SELLING_PRICE]           NUMERIC(20, 6) NULL,
           [INITIAL_BEST_PRICE]      NUMERIC(20, 6) NULL,
           [INITIAL_DISCOUNT]        NUMERIC(20, 6) NULL,
           [CONTRACT_NO]             VARCHAR(50) NULL,
           [CONTRACT_ID]             VARCHAR(50) NOT NULL,
           [ACCOUNT_ID]              VARCHAR(100) NOT NULL,
           [CONTRACT_START_DATE]     DATETIME NOT NULL,
           [CONTRACT_END_DATE]       DATETIME NULL,
           [PERIOD]                  VARCHAR(10) NULL,
           [CREATED_BY]              VARCHAR(50) NULL,
           [CREATED_DATE]            DATETIME NULL,
           [MODIFIED_BY]             VARCHAR(50) NULL,
           [MODIFIED_DATE]           DATETIME NULL,
           [BATCH_ID]                VARCHAR(38) NOT NULL,
           [SOURCE]                  VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]   VARCHAR(10) NOT NULL
        )
  END

GO