IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'REBATE_PLAN_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[REBATE_PLAN_INTERFACE]
        (
           [REBATE_PLAN_INTERFACE_ID]   NUMERIC(38, 0) NOT NULL,
           [REBATE_PLAN_ID]             VARCHAR(38) NOT NULL,
           [REBATE_PLAN_NO]             VARCHAR(50) NULL,
           [REBATE_PLAN_NAME]           VARCHAR(100) NULL,
           [REBATE_PLAN_STATUS]         VARCHAR(20) NOT NULL,
           [REBATE_PLAN_TYPE]           VARCHAR(50) NULL,
           [INTERNAL_NOTES]             VARCHAR(1000) NULL,
           [FORMULA_TYPE]               VARCHAR(50) NULL,
           [BOGO_ELIGIBLE]              CHAR(1) NULL,
           [SELF_GROWTH_INDICATOR]      CHAR(1) NULL,
           [SELF_GROWTH_REFERENCE]      VARCHAR(38) NULL,
           [SELF_GROWTH_FROM]           DATETIME NULL,
           [SELF_GROWTH_TO]             DATETIME NULL,
           [MARKET_SHARE_INDICATOR]     CHAR(1) NULL,
           [MARKET_SHARE_REFERENCE]     VARCHAR(50) NULL,
           [MARKET_SHARE_FROM]          DATETIME NULL,
           [MARKET_SHARE_TO]            DATETIME NULL,
           [TIER_FORMULA_ID]            VARCHAR(38) NULL,
           [TIER_FORMULA_NO]            VARCHAR(50) NULL,
           [TIER_FORMULA_NAME]          VARCHAR(100) NULL,
           [REBATE_STRUCTURE]           VARCHAR(38) NOT NULL,
           [REBATE_RANGE_BASED_ON]      VARCHAR(50) NOT NULL,
           [REBATE_BASED_ON]            VARCHAR(50) NOT NULL,
           [REBATE_RULE]                VARCHAR(50) NULL,
           [REBATE_PLAN_TIER_ID]        VARCHAR(38) NOT NULL,
           [TIER_FROM]                  NUMERIC(22, 6) NOT NULL,
           [TIER_TO]                    NUMERIC(22, 6) NULL,
           [TIER_LEVEL]                 VARCHAR(38) NOT NULL,
           [TIER_OPERATOR]              VARCHAR(10) NOT NULL,
           [TIER_VALUE]                 NUMERIC(22, 6) NOT NULL,
           [FREE_AMOUNT]                NUMERIC(20, 6) NULL,
           [TIER_TOLERANCE]             NUMERIC(20, 4) NULL,
           [SECONDARY_REBATE_PLAN_ID]   VARCHAR(38) NULL,
           [SECONDARY_REBATE_PLAN_NO]   VARCHAR(50) NULL,
           [SECONDARY_REBATE_PLAN_NAME] VARCHAR(100) NULL,
           [CONTRACT_ID]                VARCHAR(50) NOT NULL,
           [COMPANY_FAMILY_PLAN_ID]     VARCHAR(50) NULL,
           [MEMBER_FAMILY_PLAN_ID]      VARCHAR(50) NULL,
           [ITEM_FAMILY_PLAN_ID]        VARCHAR(50) NOT NULL,
           [PRICE_SCHEDULE_ID]          VARCHAR(50) NOT NULL,
           [REBATE_SCHEDULE_ID]         VARCHAR(50) NOT NULL,
           [CREATED_BY]                 VARCHAR(50) NULL,
           [CREATED_DATE]               DATETIME NULL,
           [MODIFIED_BY]                VARCHAR(50) NULL,
           [MODIFIED_DATE]              DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]      VARCHAR(10) NOT NULL,
           [BATCH_ID]                   VARCHAR(38) NOT NULL,
           [SOURCE]                     VARCHAR(50) NULL
        )
  END

GO