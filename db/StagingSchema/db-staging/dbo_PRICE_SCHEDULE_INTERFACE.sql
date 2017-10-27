IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE [dbo].[PRICE_SCHEDULE_INTERFACE]
        (
           [PRICE_SCHEDULE_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [PRICE_SCHEDULE_ID]           VARCHAR(38) NOT NULL,
           [PRICE_SCHEDULE_NO]           VARCHAR(50) NULL,
           [PRICE_SCHEDULE_NAME]         VARCHAR(100) NULL,
           [PRICE_SCHEDULE_TYPE]         VARCHAR(50) NULL,
           [PRICE_SCHEDULE_CATEGORY]     VARCHAR(50) NULL,
           [TRADE_CLASS]                 VARCHAR(50) NULL,
           [PRICE_SCHEDULE_DESIGNATION]  VARCHAR(50) NULL,
           [PARENT_PRICE_SCHEDULE_ID]    VARCHAR(50) NULL,
           [PARENT_PRICE_SCHEDULE_NAME]  VARCHAR(100) NULL,
           [PRICE_SCHEDULE_STATUS]       VARCHAR(20) NOT NULL,
           [PRICE_SCHEDULE_START_DATE]   DATETIME NOT NULL,
           [PRICE_SCHEDULE_END_DATE]     DATETIME NULL,
           [ITEM_ID]                     VARCHAR(38) NOT NULL,
           [IDENTIFIER_CODE_QUALIFIER]   VARCHAR(20) NULL,
           [ITEM_NO]                     VARCHAR(50) NULL,
           [ITEM_NAME]                   VARCHAR(100) NULL,
           [ATTACHED_STATUS]             VARCHAR(20) NOT NULL,
           [ATTACHED_DATE]               DATETIME NOT NULL,
           [CONTRACT_PRICE_START_DATE]   DATETIME NOT NULL,
           [CONTRACT_PRICE_END_DATE]     DATETIME NULL,
           [PRICE_TYPE]                  VARCHAR(50) NOT NULL,
           [CONTRACT_PRICE]              NUMERIC(20, 6) NULL,
           [PRICE_REVISION]              NUMERIC(20, 6) NULL,
           [REVISION_DATE]               DATETIME NULL,
           [PRICE_PROTECTION_START_DATE] DATETIME NULL,
           [PRICE_PROTECTION_END_DATE]   DATETIME NULL,
           [PRICE_TOLERANCE_TYPE]        VARCHAR(50) NULL,
           [PRICE_TOLERANCE]             NUMERIC(20, 6) NULL,
           [BASE_PRICE]                  NUMERIC(20, 6) NULL,
           [PRICE_TOLERANCE_FREQUENCY]   VARCHAR(50) NULL,
           [PRICE_TOLERANCE_INTERVAL]    NUMERIC(38, 0) NULL,
           [PRICE_PLAN_ID]               VARCHAR(38) NULL,
           [PRICE_PLAN_NAME]             VARCHAR(100) NULL,
           [SUGGESTED_PRICE]             NUMERIC(20, 6) NULL,
           [CONTRACT_ID]                 VARCHAR(50) NOT NULL,
           [COMPANY_FAMILY_PLAN_ID]      VARCHAR(50) NULL,
           [MEMBER_FAMILY_PLAN_ID]       VARCHAR(50) NULL,
           [ITEM_FAMILY_PLAN_ID]         VARCHAR(50) NOT NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [PRICE]                       NUMERIC(20, 6) NOT NULL
        )
  END
GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'TRADE_CLASS'
                 AND DATA_TYPE = 'VARCHAR'
                 AND CHARACTER_MAXIMUM_LENGTH = 50)
  BEGIN
      ALTER TABLE [dbo].[PRICE_SCHEDULE_INTERFACE]
        ALTER COLUMN [TRADE_CLASS] VARCHAR(100)
  END
GO