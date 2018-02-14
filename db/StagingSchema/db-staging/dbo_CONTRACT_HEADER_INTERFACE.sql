IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CONTRACT_HEADER_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE [dbo].[CONTRACT_HEADER_INTERFACE]
        (
           [CONTRACT_HEADER_INTERFACE_ID]      NUMERIC(38, 0) NOT NULL,
           [ORGANIZATION_KEY]                  VARCHAR(50) NULL,
           [CONTRACT_ID]                       VARCHAR(38) NOT NULL,
           [CONTRACT_NO]                       VARCHAR(50) NULL,
           [CONTRACT_NAME]                     VARCHAR(100) NULL,
           [CONTRACT_TYPE]                     VARCHAR(50) NULL,
           [CONTRACT_STATUS]                   VARCHAR(20) NOT NULL,
           [DOC_TYPE]                          VARCHAR(50) NULL,
           [DOC_CLASS]                         VARCHAR(50) NULL,
           [COMPANY_IDENTIFIER_CODE_QUALIFIER] VARCHAR(25) NULL,
           [COMPANY_NO]                        VARCHAR(50) NOT NULL,
           [COMPANY_NAME]                      VARCHAR(100) NULL,
           [TP_IDENTIFIER_CODE_QUALIFIER]      VARCHAR(25) NULL,
           [TRADING_PARTNER_NO]                VARCHAR(50) NULL,
           [TRADING_PARTNER_NAME]              VARCHAR(100) NULL,
           [TP_CONTRACT_ID]                    VARCHAR(38) NULL,
           [TP_CONTRACT_NAME]                  VARCHAR(100) NULL,
           [TRADE_CLASS]                       VARCHAR(50) NULL,
           [START_DATE]                        DATETIME NOT NULL,
           [END_DATE]                          DATETIME NULL,
           [TERM]                              VARCHAR(38) NULL,
           [RENEGOTIATION_START_DATE]          DATETIME NULL,
           [RENEGOTIATION_END_DATE]            DATETIME NULL,
           [PRICEPROTECTION_START_DATE]        DATETIME NULL,
           [PRICEPROTECTION_END_DATE]          DATETIME NULL,
           [ADVANCE_NOTICE_DAYS]               NUMERIC(20, 0) NULL,
           [INSIDE_OWNER]                      VARCHAR(50) NULL,
           [INSIDE_PHONE]                      VARCHAR(50) NULL,
           [INSIDE_AUTHOR]                     VARCHAR(50) NULL,
           [INSIDE_ADDITIONAL]                 VARCHAR(50) NULL,
           [INSIDE_ADDITIONAL_NAME]            VARCHAR(50) NULL,
           [INSIDE_ADDITIONAL_PHONE]           VARCHAR(50) NULL,
           [OUTSIDE_OWNER]                     VARCHAR(50) NULL,
           [OUTSIDE_PHONE]                     VARCHAR(50) NULL,
           [OUTSIDE_AUTHOR]                    VARCHAR(50) NULL,
           [OUTSIDE_ADDITIONAL]                VARCHAR(50) NULL,
           [OUTSIDE_ADDITIONAL_NAME]           VARCHAR(50) NULL,
           [OUTSIDE_ADDITIONAL_PHONE]          VARCHAR(50) NULL,
           [AFFILIATED_CONTRACT_INFO]          VARCHAR(50) NULL,
           [SHIPPING_TERMS]                    VARCHAR(38) NULL,
           [PROPOSAL_START_DATE]               DATETIME NULL,
           [PROPOSAL_END_DATE]                 DATETIME NULL,
           [ORIGINAL_START_DATE]               DATETIME NULL,
           [ORIGINAL_END_DATE]                 DATETIME NULL,
           [AWARD_STATUS]                      VARCHAR(20) NULL,
           [LAST_UPDATED_DATE]                 DATETIME NULL,
           [PRICE_ESCALATION_CLAUSE]           VARCHAR(50) NULL,
           [EXEMPT_FROM_LOW_PRICE]             VARCHAR(25) NULL,
           [PRICE_RESET_INDICATOR]             VARCHAR(20) NULL,
           [CANCELLATION_CLAUSE]               VARCHAR(50) NULL,
           [MOST_FAVORED_NATION]               VARCHAR(50) NULL,
           [CATEGORY]                          VARCHAR(50) NULL,
           [CURRENCY]                          VARCHAR(20) NULL,
           [MINIMUM_ORDER]                     VARCHAR(50) NULL,
           [PAYMENT_TERMS]                     VARCHAR(30) NULL,
           [CONTRACT_ALIAS_NO]                 VARCHAR(50) NULL,
           [CONTRACT_ALIAS_NAME]               VARCHAR(100) NULL,
           [CREATED_BY]                        VARCHAR(50) NULL,
           [CREATED_DATE]                      DATETIME NULL,
           [MODIFIED_BY]                       VARCHAR(50) NULL,
           [MODIFIED_DATE]                     DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]             VARCHAR(10) NOT NULL,
           [BATCH_ID]                          VARCHAR(38) NOT NULL,
           [SOURCE]                            VARCHAR(50) NULL
        )
  END
GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'CONTRACT_HEADER_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'TRADE_CLASS'
                 AND DATA_TYPE = 'VARCHAR'
                 AND CHARACTER_MAXIMUM_LENGTH = 50)
  BEGIN
      ALTER TABLE [dbo].[CONTRACT_HEADER_INTERFACE]
        ALTER COLUMN [TRADE_CLASS] VARCHAR(100)
  END

GO

------- ADD COLUMN ---------


IF NOT EXISTS(SELECT 1
      FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'CONTRACT_HEADER_INTERFACE'
        	  AND COLUMN_NAME  = 'CONTRACT_ELIGIBLE_DATE'
			  AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE CONTRACT_HEADER_INTERFACE
        ADD CONTRACT_ELIGIBLE_DATE DATETIME NULL
    END
GO