IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ACCRUAL_INBOUND_INTERFACE'
                      AND TABLE_SCHEMA = 'DBO')		
  BEGIN    
  
       CREATE TABLE [dbo].[ACCRUAL_INBOUND_INTERFACE]
        (
           [ACCRUAL_INBOUND_INTERFACE_ID] [INT] NOT NULL,
           [ACCRUAL_ID]                   [VARCHAR](50) NOT NULL,
           [SALES_MASTER_ID]              [VARCHAR](50) NULL,
           [GL_ACCOUNT]                   [VARCHAR](50) NOT NULL,
           [COMPANY_ID]                   [VARCHAR](50) NOT NULL,
           [COMPANY_NO]                   [VARCHAR](50) NOT NULL,
           [COMP_IDENT_CODE_QUALIFIER]    [VARCHAR](50) NULL,
           [COMPANY_COST_CENTER]          [VARCHAR](50) NOT NULL,
           [ACCOUNT_ID]                   [VARCHAR](50) NOT NULL,
           [ACCOUNT_NO]                   [VARCHAR](50) NULL,
           [ACCOUNT_NAME]                 [VARCHAR](100) NULL,
           [ACCT_IDENT_CODE_QUALIFIER]    [VARCHAR](50) NULL,
           [ITEM_ID]                      [VARCHAR](50) NOT NULL,
           [ITEM_NO]                      [VARCHAR](50) NULL,
           [ITEM_IDENT_CODE_QUALIFIER]    [VARCHAR](50) NULL,
           [CONTRACT_ID]                  [VARCHAR](50) NOT NULL,
           [CONTRACT_NO]                  [VARCHAR](50) NULL,
           [CONTRACT_NAME]                [VARCHAR](100) NULL,
           [BRAND_ID]                     [VARCHAR](50) NULL,
           [CATEGORY_ID]                  [VARCHAR](50) NULL,
           [PROVISION_ID]                 [VARCHAR](50) NOT NULL,
           [ACCRUAL_TYPE]                 [VARCHAR](50) NULL,
           [ACCRUAL_PERIOD_START_DATE]    [DATETIME] NOT NULL,
           [ACCRUAL_PERIOD_END_DATE]      [DATETIME] NOT NULL,
           [AMOUNT]                       [NUMERIC](22, 6) NOT NULL,
           [SUB_LEDGER]                   [VARCHAR](50) NOT NULL,
           [SUB_LEDGER_TYPE]              [VARCHAR](50) NOT NULL,
           [DOCUMENT_TYPE]                [VARCHAR](50) NOT NULL,
           [POSTING_DATE]                 [DATETIME] NULL,
           [GL_DATE]                      [DATETIME] NULL,
           [RECORD_CREATED_DATE]          [DATETIME] NULL,
           [BATCH_ID]                     [VARCHAR](50) NOT NULL,
           [SOURCE]                       [VARCHAR](50) NULL,
           [CREATED_BY]                   [VARCHAR](50) NULL,
           [CREATED_DATE]                 [DATETIME] NULL,
           [MODIFIED_BY]                  [VARCHAR](50) NULL,
           [MODIFIED_DATE]                [DATETIME] NULL,
           [ADD_CHG_DEL_INDICATOR]        [VARCHAR](10) NOT NULL
        )
  END

GO