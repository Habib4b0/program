IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'FORMULA_DETAILS_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE [dbo].[FORMULA_DETAILS_INTERFACE]
        (
           [FORMULA_DETAILS_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [FORMULA_ID]                   NUMERIC(38, 0) NOT NULL,
           [FORMULA_NO]                   VARCHAR(50) NOT NULL,
           [FORMULA_DESC]                 VARCHAR(50) NOT NULL,
           [COMPANY_ID]                   NUMERIC(38, 0) NOT NULL,
           [ITEM_ID]                      VARCHAR(38) NOT NULL,
           [START_DATE]                   DATETIME NOT NULL,
           [END_DATE]                     DATETIME NULL,
           [REBATE_PERCENT_1]             NUMERIC(20, 2) NULL,
           [REBATE_PERCENT_2]             NUMERIC(20, 2) NULL,
           [REBATE_PERCENT_3]             NUMERIC(20, 2) NULL,
           [CONTRACT_PRICE_1]             NUMERIC(20, 6) NULL,
           [CONTRACT_PRICE_2]             NUMERIC(20, 6) NULL,
           [CONTRACT_PRICE_3]             NUMERIC(20, 6) NULL,
           [ADD_CHG_DEL_INDICATOR]        VARCHAR(10) NOT NULL,
           [CREATED_BY]                   VARCHAR(50) NULL,
           [CREATED_DATE]                 DATETIME NULL,
           [MODIFIED_BY]                  VARCHAR(50) NULL,
           [MODIFIED_DATE]                DATETIME NULL,
           [BATCH_ID]                     VARCHAR(38) NOT NULL,
           [SOURCE]                       VARCHAR(50) NULL
        )
  END
GO 

IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORMULA_DETAILS_INTERFACE'
                      AND COLUMN_NAME = 'COMPANY_ID'
					  AND DATA_TYPE = 'NUMERIC'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE FORMULA_DETAILS_INTERFACE
        ALTER COLUMN COMPANY_ID VARCHAR(50) NOT NULL
  END
GO