IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_FAMILY_PLAN_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[ITEM_FAMILY_PLAN_INTERFACE]
        (
           [ITEM_FAMILY_PLAN_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [ITEM_FAMILY_PLAN_ID]           VARCHAR(50) NOT NULL,
           [ITEM_FAMILY_PLAN_NO]           VARCHAR(50) NULL,
           [ITEM_FAMILY_PLAN_NAME]         VARCHAR(100) NULL,
           [ITEM_FAMILY_PLAN_TYPE]         VARCHAR(50) NULL,
           [ITEM_FAMILY_PLAN_CATEGORY]     VARCHAR(50) NULL,
           [ITEM_FAMILY_PLAN_DESIGNATION]  VARCHAR(50) NULL,
           [PARENT_ITEM_FAMILY_PLAN_ID]    VARCHAR(50) NULL,
           [PARENT_ITEM_FAMILY_PLAN_NAME]  VARCHAR(100) NULL,
           [ITEM_FAMILY_PLAN_STATUS]       VARCHAR(50) NOT NULL,
           [ITEM_FAMILY_PLAN_START_DATE]   DATETIME NOT NULL,
           [ITEM_FAMILY_PLAN_END_DATE]     DATETIME NULL,
           [ITEM_ID]                       VARCHAR(50) NOT NULL,
           [IDENTIFIER_CODE_QUALIFIER]     VARCHAR(25) NULL,
           [ITEM_NO]                       VARCHAR(50) NULL,
           [ITEM_NAME]                     VARCHAR(100) NULL,
           [ATTACHED_STATUS]               VARCHAR(50) NOT NULL,
           [ATTACHED_DATE]                 DATETIME NOT NULL,
           [START_DATE]                    DATETIME NOT NULL,
           [END_DATE]                      DATETIME NULL,
           [TOTAL_VOLUME_COMMITMENT]       VARCHAR(50) NULL,
           [TOTAL_DOLLAR_COMMITMENT]       VARCHAR(50) NULL,
           [TOTAL_MARKETSHARE_COMMITMENT]  VARCHAR(50) NULL,
           [COMMITMENT_PERIOD]             VARCHAR(50) NULL,
           [CONTRACT_ID]                   VARCHAR(50) NOT NULL,
           [COMPANY_FAMILY_PLAN_ID]        VARCHAR(50) NULL,
           [MEMBER_FAMILY_PLAN_ID]         VARCHAR(50) NULL,
           [CREATED_BY]                    VARCHAR(50) NULL,
           [CREATED_DATE]                  DATETIME NULL,
           [MODIFIED_BY]                   VARCHAR(50) NULL,
           [MODIFIED_DATE]                 DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]         VARCHAR(10) NOT NULL,
           [BATCH_ID]                      VARCHAR(38) NOT NULL,
           [SOURCE]                        VARCHAR(50) NULL
        )
  END

GO