IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'GCM_ITEM_DETAILS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE GCM_ITEM_DETAILS
        (
           [GCM_ITEM_DETAILS_SID] [INT] IDENTITY(1, 1) NOT NULL,
           [ITEM_IFP_DETAILS_SID] [INT] NULL,
           [ITEM_MASTER_SID]      [INT] NULL,
           [ITEM_NO]              [VARCHAR](50) NULL,
           [ITEM_NAME]            [VARCHAR](100) NULL,
           [BRAND_NAME]           [VARCHAR](100) NULL,
           [ITEM_STATUS]          [VARCHAR](50) NULL,
           [THERAPUTIC_CLASS]     [VARCHAR](50) NULL,
           [ITEM_START_DATE]      [DATETIME] NULL,
           [ITEM_END_DATE]        [DATETIME] NULL,
           [ITEM_STATUS_SID]      [INT] NULL,
		   ITEM_ID	              VARCHAR(50) NULL,
		   IFP_MODEL_SID	      INT NULL,
		   IFP_DETAILS_START_DATE DATETIME,
		   IFP_DETAILS_END_DATE	  DATETIME,
           [CHECK_RECORD]         [BIT] NULL,
           [INBOUND_STATUS]       [VARCHAR](50) NULL,
           [USER_ID]              [INT] NULL,
           [SESSION_ID]           [VARCHAR](100) NULL,
           [OPERATION]            [VARCHAR](100) NULL,
           [CREATED_BY]           [INT] NULL,
           [CREATED_DATE]         [DATETIME] NULL,
           [MODIFIED_BY]          [INT] NULL,
           [MODIFIED_DATE]        [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_GCM_ITEM_DETAILS_GCM_ITEM_DETAILS_SID'
                      AND TABLE_NAME = 'GCM_ITEM_DETAILS')
  BEGIN
      ALTER TABLE [DBO].[GCM_ITEM_DETAILS]
        ADD CONSTRAINT PK_GCM_ITEM_DETAILS_GCM_ITEM_DETAILS_SID PRIMARY KEY (GCM_ITEM_DETAILS_SID)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   information_schema.columns
               WHERE  table_name = 'GCM_ITEM_DETAILS'
                      AND column_name = 'ITEM_STATUS'
                      AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE GCM_ITEM_DETAILS
        ALTER COLUMN ITEM_STATUS INT
  END

GO

IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_ITEM_DETAILS'
                  AND column_name = 'ITEM_STATUS_SID'
                  AND table_schema = 'DBO')
  BEGIN
      ALTER TABLE GCM_ITEM_DETAILS
        ADD ITEM_STATUS_SID INT
  END

GO


IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_ITEM_DETAILS'
                  AND column_name = 'ITEM_ID'
                  AND table_schema = 'DBO')
  BEGIN


ALTER TABLE GCM_ITEM_DETAILS ADD ITEM_ID	VARCHAR(50);

  END

GO

IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_ITEM_DETAILS'
                  AND column_name = 'IFP_MODEL_SID'
                  AND table_schema = 'DBO')
  BEGIN


ALTER TABLE GCM_ITEM_DETAILS ADD IFP_MODEL_SID	INT;

  END

GO


IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_ITEM_DETAILS'
                  AND column_name = 'IFP_DETAILS_START_DATE'
                  AND table_schema = 'DBO')
  BEGIN


ALTER TABLE GCM_ITEM_DETAILS ADD IFP_DETAILS_START_DATE	DATETIME;

  END

GO

IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_ITEM_DETAILS'
                  AND column_name = 'IFP_DETAILS_END_DATE'
                  AND table_schema = 'DBO')
  BEGIN

ALTER TABLE GCM_ITEM_DETAILS ADD IFP_DETAILS_END_DATE	DATETIME;

  END

GO




IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'GCM_COMPANY_DETAILS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE GCM_COMPANY_DETAILS
        (
           [GCM_COMPANY_DETAILS_SID] [INT] IDENTITY(1, 1) NOT NULL,
           [ITEM_CFP_DETAILS_SID]    [INT] NULL,
           [COMPANY_MASTER_SID]      [INT] NULL,
           [COMPANY_NO]              [VARCHAR](50) NULL,
           [COMPANY_NAME]            [VARCHAR](100) NULL,
           [COMPANY_START_DATE]      [DATETIME] NULL,
           [COMPANY_END_DATE]        [DATETIME] NULL,
           [COMPANY_ID]              [VARCHAR](50) NULL,
           [CFP_DETAILS_TRADE_CLASS] [VARCHAR](100) NULL,
           [COMPANY_STATUS_SID]      [INT] NULL,
           [COMPANY_STATUS]          [VARCHAR](50) NULL,
           [MODULE_NAME]             [VARCHAR](100) NULL,
           [SUB_MODULE_NAME]         [VARCHAR](100) NULL,
		   CFP_MODEL_SID	         INT NULL ,
		   CFP_DETAILS_START_DATE	 DATETIME,
		   CFP_DETAILS_END_DATE	     DATETIME,
           [CHECK_RECORD]            [BIT] NULL,
           [USER_ID]                 [INT] NULL,
           [SESSION_ID]              [VARCHAR](100) NULL,
           [OPERATION]               [VARCHAR](100) NULL,
           [CREATED_BY]              [INT] NULL,
           [CREATED_DATE]            [DATETIME] NULL,
           [MODIFIED_BY]             [INT] NULL,
           [MODIFIED_DATE]           [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_COMPANY_DETAILS'
                  AND column_name = 'COMPANY_STATUS_SID'
                  AND table_schema = 'DBO')
  BEGIN
      ALTER TABLE GCM_COMPANY_DETAILS
        ADD COMPANY_STATUS_SID INT
  END

GO

IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_COMPANY_DETAILS'
                  AND column_name = 'COMPANY_STATUS'
                  AND table_schema = 'DBO')
  BEGIN
      ALTER TABLE GCM_COMPANY_DETAILS
        ADD COMPANY_STATUS INT
  END

GO

IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_COMPANY_DETAILS'
                  AND column_name = 'CFP_MODEL_SID'
                  AND table_schema = 'DBO')
  BEGIN

ALTER TABLE GCM_COMPANY_DETAILS ADD CFP_MODEL_SID	INT;

  END

GO


IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_COMPANY_DETAILS'
                  AND column_name = 'CFP_DETAILS_START_DATE'
                  AND table_schema = 'DBO')
  BEGIN

ALTER TABLE GCM_COMPANY_DETAILS ADD CFP_DETAILS_START_DATE	DATETIME;

  END

GO


IF NOT  EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_COMPANY_DETAILS'
                  AND column_name = 'CFP_DETAILS_END_DATE'
                  AND table_schema = 'DBO')
  BEGIN


ALTER TABLE GCM_COMPANY_DETAILS ADD CFP_DETAILS_END_DATE	DATETIME;

  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_GCM_COMPANY_DETAILS_GCM_COMPANY_DETAILS_SID'
                      AND TABLE_NAME = 'GCM_COMPANY_DETAILS')
  BEGIN
      ALTER TABLE [DBO].[GCM_COMPANY_DETAILS]
        ADD CONSTRAINT PK_GCM_COMPANY_DETAILS_GCM_COMPANY_DETAILS_SID PRIMARY KEY (GCM_COMPANY_DETAILS_SID)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'GCM_COMPANY_LINK'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE GCM_COMPANY_LINK
        (
           [GCM_COMPANY_LINK_SID] [INT] IDENTITY(1, 1) NOT NULL,
           [COMPANY_MASTER_SID]   [INT] NULL,
           [COMPANY_ID]           [VARCHAR](50) NULL,
           [COMPANY_NO]           [VARCHAR](50) NULL,
           [COMPANY_NAME]         [VARCHAR](100) NULL,
           [LINK_ID]              [VARCHAR](50) NULL,
           [CHECK_RECORD]         [BIT] NULL,
           [USER_ID]              [INT] NULL,
           [SESSION_ID]           [VARCHAR](100) NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_GCM_COMPANY_LINK_GCM_COMPANY_LINK_SID'
                      AND TABLE_NAME = 'GCM_COMPANY_LINK')
  BEGIN
      ALTER TABLE [DBO].[GCM_COMPANY_LINK]
        ADD CONSTRAINT PK_GCM_COMPANY_LINK_GCM_COMPANY_LINK_SID PRIMARY KEY (GCM_COMPANY_LINK_SID)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE GCM_GLOBAL_DETAILS
        (
           [GCM_GLOBAL_DETAILS_SID]       [INT] IDENTITY(1, 1) NOT NULL,
           [PROJECTION_MASTER_SID]        [INT] NULL,
           [CFP_CONTRACT_SID]             [INT] NULL,
           [IFP_CONTRACT_SID]             [INT] NULL,
           [PS_CONTRACT_SID]              [INT] NULL,
           [RS_CONTRACT_SID]              [INT] NULL,
           [CONTRACT_MASTER_SID]          [INT] NULL,
           [ITEM_MASTER_SID]              [INT] NULL,
           [FORMULA_ID]                   [VARCHAR](50) NULL,
           [REBATE_PLAN_SYSTEM_ID]        [INT] NULL,
           [FORMULA_METHOD_ID]            [VARCHAR](50) NULL,
           [ITEM_NO]                      [VARCHAR](50) NULL,
           [ITEM_NAME]                    [VARCHAR](100) NULL,
           [BRAND_NAME]                   [VARCHAR](100) NULL,
           [THERAPUTIC_CLASS]             [VARCHAR](100) NULL,
           [ITEM_ID]                      [VARCHAR](50) NULL,
           [IFP_MODEL_SID]                [INT] NULL,
           [WORKFLOW_MASTER_SID]          [INT] NULL,
           [SCREEN_NAME]                  [VARCHAR](100) NULL,
           [CFP_START_DATE]               [DATETIME] NULL,
           [CFP_END_DATE]                 [DATETIME] NULL,
           [CFP_STATUS]                   [VARCHAR](100) NULL,
           [FORECASTING_TYPE]             [VARCHAR](50) NULL,
           [START_DATE]                   [DATETIME] NULL,
           [END_DATE]                     [DATETIME] NULL,
           [CONTRACT_PRICE_START_DATE]    [DATETIME] NULL,
           [CONTRACT_PRICE_END_DATE]      [DATETIME] NULL,
           [ITEM_STATUS]                  [VARCHAR](50) NULL,
           [PRICE_TOLERANCE]              [NUMERIC](22, 6) NULL,
           [PRICE_PROTECTION_START_DATE]  [DATETIME] NULL,
           [PRICE_PROTECTION_END_DATE]    [DATETIME] NULL,
           [PRICE_TOLERANCE_TYPE]         [INT] NULL,
           [PRICE_TOLERANCE_INTERVAL]     [INT] NULL,
           [PRICE_TOLERANCE_FREQUENCY]    [INT] NULL,
           [BASE_PRICE]                   [NUMERIC](22, 6) NULL,
           [CONTRACT_PRICE]               [NUMERIC](22, 6) NULL,
           [PRICE]                        [NUMERIC](22, 6) NULL,
           [ITEM_REBATE_START_DATE]       [DATETIME] NULL,
           [ITEM_REBATE_END_DATE]         [DATETIME] NULL,
           [REBATE_AMOUNT]                [INT] NULL,
           [OPERATION]                    [VARCHAR](100) NULL,
           [SUB_MODULE_NAME]              [VARCHAR](100) NULL,
           [MODULE_NAME]                  [VARCHAR](100) NULL,
		   ITEM_TYPE	                  VARCHAR(100) NULL,
		   PAYMENT_FREQUENCY	          VARCHAR(100) NULL,
		   PAYMENT_METHOD	              VARCHAR(100) NULL,
		   CALENDAR	                      VARCHAR(100) NULL,
           [ITEM_STATUS_SID]              [INT] NULL,
           [REBATE_PLAN_NAME]             [VARCHAR](100) NULL,
           [FORMULA_NAME]                 [VARCHAR](100) NULL,
           [PRICING_QUALIFIER_SID]        [VARCHAR](50) NULL,
           [IFP_CONTRACT_ATTACHED_STATUS] [VARCHAR](50) NULL,
           [TEMP_STATUS]                  [VARCHAR](50) NULL,
           [STATUS]                       [VARCHAR](50) NULL,
           [TEMP_START_DATE]              [DATETIME] NULL,
           [TEMP_END_DATE]                [DATETIME] NULL,
           [TEMP_ITEM_MASTER_SID]         [VARCHAR](50) NULL,
		   [CHECK_RECORD]                 [BIT] NULL,
           [USER_ID]                      [INT] NULL,
           [SESSION_ID]                   [VARCHAR](100) NULL,
           [CREATED_BY]                   [INT] NULL,
           [CREATED_DATE]                 [DATETIME] NULL,
           [MODIFIED_BY]                  [INT] NULL,
           [MODIFIED_DATE]                [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'ITEM_STATUS_SID')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD ITEM_STATUS_SID INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'REBATE_PLAN_NAME')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD REBATE_PLAN_NAME VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'FORMULA_NAME')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD FORMULA_NAME VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'PRICING_QUALIFIER_SID')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD PRICING_QUALIFIER_SID VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'IFP_CONTRACT_ATTACHED_STATUS')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD IFP_CONTRACT_ATTACHED_STATUS VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'TEMP_STATUS')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD TEMP_STATUS VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'STATUS')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD [STATUS] VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'TEMP_START_DATE')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD TEMP_START_DATE DATETIME
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'TEMP_END_DATE')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD TEMP_END_DATE DATETIME
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'TEMP_ITEM_MASTER_SID')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD TEMP_ITEM_MASTER_SID VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_GLOBAL_DETAILS'
                  AND column_name = 'ITEM_TYPE'
                  AND table_schema = 'DBO')
  BEGIN

ALTER TABLE GCM_GLOBAL_DETAILS ADD ITEM_TYPE	VARCHAR(100);

  END

GO

IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_GLOBAL_DETAILS'
                  AND column_name = 'PAYMENT_FREQUENCY'
                  AND table_schema = 'DBO')
  BEGIN

ALTER TABLE GCM_GLOBAL_DETAILS ADD PAYMENT_FREQUENCY	VARCHAR(100);

  END

GO

IF NOT  EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_GLOBAL_DETAILS'
                  AND column_name = 'PAYMENT_METHOD'
                  AND table_schema = 'DBO')
  BEGIN

ALTER TABLE GCM_GLOBAL_DETAILS ADD PAYMENT_METHOD	VARCHAR(100);

  END

GO

IF NOT EXISTS (SELECT 1
           FROM   information_schema.columns
           WHERE  table_name = 'GCM_GLOBAL_DETAILS'
                  AND column_name = 'CALENDAR'
                  AND table_schema = 'DBO')
  BEGIN

ALTER TABLE GCM_GLOBAL_DETAILS ADD CALENDAR	VARCHAR(100)	;

  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_GCM_GLOBAL_DETAILS_GCM_GLOBAL_DETAILS_SID'
                      AND TABLE_NAME = 'GCM_GLOBAL_DETAILS')
  BEGIN
      ALTER TABLE [DBO].[GCM_GLOBAL_DETAILS]
        ADD CONSTRAINT PK_GCM_GLOBAL_DETAILS_GCM_GLOBAL_DETAILS_SID PRIMARY KEY (GCM_GLOBAL_DETAILS_SID)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'CFP_MODEL_SID')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD CFP_MODEL_SID INT NULL
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'PS_MODEL_SID')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD PS_MODEL_SID INT NULL
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'RS_MODEL_SID')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD RS_MODEL_SID INT NULL
  END

GO

--------------------------------- ALG-2021

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'NEP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD NEP NUMERIC(22, 6)
  END

GO


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_STATUS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD PRICE_PROTECTION_STATUS INT
  END

GO


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'NEP_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD NEP_FORMULA VARCHAR(100);
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'MAX_INCREMENTAL_CHANGE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD MAX_INCREMENTAL_CHANGE NUMERIC(22, 6)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'RESET_ELIGIBLE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD RESET_ELIGIBLE VARCHAR(5)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'RESET_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD RESET_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'RESET_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD RESET_DATE DATETIME
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'RESET_INTERVAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD RESET_INTERVAL INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'RESET_FREQUENCY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD RESET_FREQUENCY INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD NET_PRICE_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD NET_PRICE_TYPE_FORMULA VARCHAR(100);
  END

GO


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD RESET_PRICE_TYPE INT
  END

GO


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD NET_RESET_PRICE_TYPE VARCHAR(5);
  END

GO


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD NET_RESET_PRICE_FORMULA_ID VARCHAR(50);
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD BASE_PRICE_TYPE VARCHAR(50)
  END

GO


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'SUBSEQUENT_PERIOD_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD SUBSEQUENT_PERIOD_PRICE_TYPE INT
  END

GO


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD NET_SUBSEQUENT_PERIOD_PRICE VARCHAR(50);
  END

GO



IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD NET_SUBSEQUENT_PRICE_FORMULA_ID VARCHAR(50);
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'NET_BASELINE_WAC_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD NET_BASELINE_WAC_FORMULA_ID VARCHAR(50);
  END

GO
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD NET_BASE_PRICE VARCHAR(50);
  END

GO
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD PRICE_TYPE INT
  END

GO
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD PRICE_PROTECTION_PRICE_TYPE INT
  END

GO
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'BASELINE_WAC_MANUAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD BASELINE_WAC_MANUAL NUMERIC(22, 6)
  END

GO
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'BASELINE_WAC_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD BASELINE_WAC_DATE DATETIME
  END

GO
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GCM_GLOBAL_DETAILS'
                      AND COLUMN_NAME = 'BASELINE_WAC_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE GCM_GLOBAL_DETAILS
        ADD BASELINE_WAC_PRICE_TYPE INT
  END

GO



------------------------------------------------------


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'GCM_CONTRACT_DETAILS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE GCM_CONTRACT_DETAILS
        (
           [GCM_CONTRACT_DETAILS_SID] [INT] IDENTITY(1, 1) NOT NULL,
           [COMPONENT_ID]             [VARCHAR](50) NULL,
           [COMPONENT_TYPE]           [VARCHAR](50) NULL,
           [COMPONENT_NO]             [VARCHAR](50) NULL,
           [COMPONENT_NAME]           [VARCHAR](50) NULL,
           [COMPONENT_STATUS]         [VARCHAR](50) NULL,
           [FILE_NAME]                [VARCHAR](50) NULL,
           [START_DATE]               [DATETIME] NULL,
           [END_DATE]                 [DATETIME] NULL,
           [PAYMENT_FREQUENCY]        [VARCHAR](50) NULL,
           [PROGRAM_TYPE]             [VARCHAR](50) NULL,
           [PLAN_LEVEL]               [VARCHAR](50) NULL,
           [PAYMENT_METHOD]           [VARCHAR](50) NULL,
           [RS_CALENDAR]              [VARCHAR](50) NULL,
           [USER_ID]                  [INT] NULL,
           [SESSION_ID]               [VARCHAR](50) NULL,
           [CREATED_BY]               [INT] NULL,
           [CREATED_DATE]             [DATETIME] NULL,
           [MODIFIED_BY]              [INT] NULL,
           [MODIFIED_DATE]            [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_GCM_CONTRACT_DETAILS_GCM_CONTRACT_DETAILS_SID'
                      AND TABLE_NAME = 'GCM_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE [DBO].[GCM_CONTRACT_DETAILS]
        ADD CONSTRAINT PK_GCM_CONTRACT_DETAILS_GCM_CONTRACT_DETAILS_SID PRIMARY KEY (GCM_CONTRACT_DETAILS_SID)
  END

GO 
