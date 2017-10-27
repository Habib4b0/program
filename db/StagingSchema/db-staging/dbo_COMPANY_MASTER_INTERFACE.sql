IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'COMPANY_MASTER_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[COMPANY_MASTER_INTERFACE]
        (
           [COMPANY_MASTER_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [ORGANIZATION_KEY]            VARCHAR(100) NULL,
           [COMPANY_ID]                  VARCHAR(38) NOT NULL,
           [COMPANY_NO]                  VARCHAR(50) NOT NULL,
           [COMPANY_NAME]                VARCHAR(100) NOT NULL,
           [COMPANY_TYPE]                VARCHAR(50) NOT NULL,
           [COMPANY_STATUS]              VARCHAR(20) NOT NULL,
           [LIVES]                       NUMERIC(38, 0) NULL,
           [COMPANY_END_DATE]            DATETIME NULL,
           [UDC1]                        VARCHAR(100) NULL,
           [UDC2]                        VARCHAR(100) NULL,
           [UDC3]                        VARCHAR(100) NULL,
           [UDC4]                        VARCHAR(100) NULL,
           [UDC5]                        VARCHAR(100) NULL,
           [UDC6]                        VARCHAR(100) NULL,
           [COMPANY_GROUP]               VARCHAR(50) NULL,
           [FINANCIAL_SYSTEM]            VARCHAR(50) NULL,
           [ADDRESS_1]                   VARCHAR(100) NULL,
           [ADDRESS_2]                   VARCHAR(100) NULL,
           [CITY]                        VARCHAR(50) NULL,
           [STATE]                       VARCHAR(50) NULL,
           [ZIP_CODE]                    VARCHAR(50) NULL,
           [COUNTRY]                     VARCHAR(50) NULL,
           [REGION_CODE]                 VARCHAR(50) NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [LAST_UPDATED_DATE]           DATETIME NULL,
           [COMPANY_START_DATE]          DATETIME NOT NULL,
           [COMPANY_CATEGORY]            VARCHAR(50) NULL
        )
  END
GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'COMPANY_MASTER_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'TRADE_CLASS')
  BEGIN
      ALTER TABLE [dbo].[COMPANY_MASTER_INTERFACE]
        DROP COLUMN [TRADE_CLASS]
  END
GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'COMPANY_MASTER_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'TRADE_CLASS_START_DATE')
  BEGIN
      ALTER TABLE [dbo].[COMPANY_MASTER_INTERFACE]
        DROP COLUMN [TRADE_CLASS_START_DATE]
  END

GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'COMPANY_MASTER_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'TRADE_CLASS_END_DATE')
  BEGIN
      ALTER TABLE [dbo].[COMPANY_MASTER_INTERFACE]
        DROP COLUMN [TRADE_CLASS_END_DATE]
  END

GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'COMPANY_MASTER_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'PARENT_COMPANY_NO')
  BEGIN
      ALTER TABLE [dbo].[COMPANY_MASTER_INTERFACE]
        DROP COLUMN [PARENT_COMPANY_NO]
  END

GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'COMPANY_MASTER_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'PARENT_START_DATE')
  BEGIN
      ALTER TABLE [dbo].[COMPANY_MASTER_INTERFACE]
        DROP COLUMN [PARENT_START_DATE]
  END

GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'COMPANY_MASTER_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'PARENT_END_DATE')
  BEGIN
      ALTER TABLE [dbo].[COMPANY_MASTER_INTERFACE]
        DROP COLUMN [PARENT_END_DATE]
  END

GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'COMPANY_MASTER_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'PRIOR_PARENT_COMPANY_NO')
  BEGIN
      ALTER TABLE [dbo].[COMPANY_MASTER_INTERFACE]
        DROP COLUMN [PRIOR_PARENT_COMPANY_NO]
  END

GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'COMPANY_MASTER_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'PRIOR_PARENT_START_DATE')
  BEGIN
      ALTER TABLE [dbo].[COMPANY_MASTER_INTERFACE]
        DROP COLUMN [PRIOR_PARENT_START_DATE]
  END
  
 GO