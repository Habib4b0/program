IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'REBATE_SCHEDULE_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

     CREATE TABLE [dbo].[REBATE_SCHEDULE_INTERFACE]
        (
           [REBATE_SCHEDULE_INTERFACE_ID]   NUMERIC(38, 0) NOT NULL,
           [REBATE_SCHEDULE_ID]             VARCHAR(38) NOT NULL,
           [REBATE_SCHEDULE_NO]             VARCHAR(50) NULL,
           [REBATE_SCHEDULE_NAME]           VARCHAR(100) NULL,
           [REBATE_SCHEDULE_TYPE]           VARCHAR(25) NOT NULL,
           [REBATE_PROGRAM_TYPE]            VARCHAR(25) NOT NULL,
           [REBATE_SCHEDULE_CATEGORY]       VARCHAR(25) NULL,
           [TRADE_CLASS]                    VARCHAR(50) NULL,
           [REBATE_SCHEDULE_DESIGNATION]    VARCHAR(25) NULL,
           [PARENT_REBATE_SCHEDULE_ID]      VARCHAR(50) NULL,
           [PARENT_REBATE_SCHEDULE_NAME]    VARCHAR(100) NULL,
           [REBATE_SCHEDULE_STATUS]         VARCHAR(10) NULL,
           [REBATE_SCHEDULE_TRANS_REF_ID]   VARCHAR(50) NULL,
           [REBATE_SCHEDULE_TRANS_REF_NO]   VARCHAR(50) NULL,
           [REBATE_SCHEDULE_TRANS_REF_NAME] VARCHAR(100) NULL,
           [PAYMENT_METHOD]                 VARCHAR(25) NULL,
           [PAYMENT_FREQUENCY]              VARCHAR(25) NULL,
           [PAYMENT_TERMS]                  VARCHAR(25) NULL,
           [REBATE_FREQUENCY]               VARCHAR(25) NOT NULL,
           [CALENDAR]                       VARCHAR(25) NOT NULL,
           [REBATE_PROCESSING_TYPE]         VARCHAR(50) NULL,
           [VALIDATION_PROFILE]             VARCHAR(50) NULL,
           [REBATE_RULE_TYPE]               VARCHAR(100) NULL,
           [REBATE_RULE_ASSOCIATION]        VARCHAR(100) NULL,
           [REBATE_PLAN_LEVEL]              VARCHAR(50) NULL,
           [INTEREST_BEARING_INDICATOR]     CHAR(2) NULL,
           [INTEREST_BEARING_BASIS]         VARCHAR(50) NULL,
           [PAYMENT_GRACE_PERIOD]           VARCHAR(25) NULL,
           [MAKE_PAYABLE_TO]                VARCHAR(50) NULL,
           [ADDRESS_1]                      VARCHAR(100) NULL,
           [ADDRESS_2]                      VARCHAR(100) NULL,
           [CITY]                           VARCHAR(50) NULL,
           [STATE]                          VARCHAR(50) NULL,
           [ZIP_CODE]                       VARCHAR(50) NULL,
           [ITEM_ID]                        VARCHAR(50) NOT NULL,
           [IDENTIFIER_CODE_QUALIFIER]      VARCHAR(50) NULL,
           [ITEM_NO]                        VARCHAR(50) NULL,
           [ITEM_NAME]                      VARCHAR(50) NULL,
           [ATTACHED_STATUS]                VARCHAR(10) NOT NULL,
           [ATTACHED_DATE]                  DATETIME NOT NULL,
           [ITEM_START_DATE]                DATETIME NOT NULL,
           [ITEM_END_DATE]                  DATETIME NULL,
           [FORMULA_TYPE]                   VARCHAR(25) NULL,
           [FORMULA_ID]                     VARCHAR(38) NULL,
           [FORMULA_NO]                     VARCHAR(50) NULL,
           [FORMULA_NAME]                   VARCHAR(50) NULL,
           [REBATE_PLAN_ID]                 VARCHAR(50) NULL,
           [REBATE_AMOUNT]                  NUMERIC(20, 6) NULL,
           [ITEM_REBATE_START_DATE]         DATETIME NOT NULL,
           [ITEM_REBATE_END_DATE]           DATETIME NULL,
           [BUNDLE_NO]                      VARCHAR(25) NULL,
           [INTERNAL_NOTES]                 VARCHAR(1000) NULL,
           [CONTRACT_ID]                    VARCHAR(50) NOT NULL,
           [COMPANY_FAMILY_PLAN_ID]         VARCHAR(50) NULL,
           [MEMBER_FAMILY_PLAN_ID]          VARCHAR(50) NULL,
           [ITEM_FAMILY_PLAN_ID]            VARCHAR(50) NOT NULL,
           [PRICE_SCHEDULE_ID]              VARCHAR(50) NOT NULL,
           [CREATED_BY]                     VARCHAR(50) NULL,
           [CREATED_DATE]                   DATETIME NULL,
           [MODIFIED_BY]                    VARCHAR(50) NULL,
           [MODIFIED_DATE]                  DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]          VARCHAR(10) NOT NULL,
           [BATCH_ID]                       VARCHAR(38) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [UDC1]                           VARCHAR(100) NULL,
           [UDC2]                           VARCHAR(100) NULL,
           [UDC3]                           VARCHAR(100) NULL,
           [UDC4]                           VARCHAR(100) NULL,
           [UDC5]                           VARCHAR(100) NULL,
           [UDC6]                           VARCHAR(100) NULL,
           [FORMULA_METHOD_ID]              VARCHAR(38) NULL
        )
  END
GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'REBATE_SCHEDULE_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'TRADE_CLASS'
                 AND DATA_TYPE = 'VARCHAR'
                 AND CHARACTER_MAXIMUM_LENGTH = 50)
  BEGIN
      ALTER TABLE [dbo].[REBATE_SCHEDULE_INTERFACE]
        ALTER COLUMN [TRADE_CLASS] VARCHAR(100)
  END
GO 


