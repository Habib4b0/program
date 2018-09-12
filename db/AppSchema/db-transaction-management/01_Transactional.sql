 -------------ACTUALS_MASTER--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ACTUALS_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ACTUALS_MASTER]
        (
           [ACTUALS_MASTER_SID]             INT IDENTITY(1, 1) NOT NULL,
           [ACTUAL_ID]                      VARCHAR(50) NOT NULL,
           [CONTRACT_ID]                    VARCHAR(50) NOT NULL,
           [UPLOAD_DATE]                    DATETIME NULL,
           [PROVISION_ID]                   VARCHAR(50) NULL,
           [ACCRUAL_ACTUAL_START_DATE]      DATETIME NOT NULL,
           [ACCRUAL_ACTUAL_END_DATE]        DATETIME NOT NULL,
           [ITEM_ID]                        VARCHAR(50) NOT NULL,
           [ITEM_IDENTIFIER_CODE_QUALIFIER] VARCHAR(50) NULL,
           [ITEM_NO]                        VARCHAR(50) NULL,
           [COMPANY_MASTER_SID]             INT NOT NULL,
           [CONTRACT_MASTER_SID]            INT NOT NULL,
           [ITEM_MASTER_SID]                INT NOT NULL,
           [SETTLEMENT_METHOD_NO]           VARCHAR(50) NULL,
           [CASH_PAID_DATE]                 DATETIME NOT NULL,
           [AMOUNT]                         NUMERIC(22, 6) NOT NULL,
           [QUANTITY]                       NUMERIC(22, 6) NOT NULL,
           [QUANTITY_INCLUSION]             VARCHAR(10) NOT NULL,
           [SETTLEMENT_NO]                  VARCHAR(100) NULL,
           [INVOICE_LINE_NO]                VARCHAR(100) NULL,
           [ACCOUNT_ID]                     VARCHAR(100) NOT NULL,
           [ACCT_IDENTIFIER_CODE_QUALIFIER] VARCHAR(100) NULL,
           [ACCOUNT_NO]                     VARCHAR(100) NULL,
           [ACCOUNT_NAME]                   VARCHAR(100) NULL,
           [ANALYSIS_CODE]                  CHAR(50) NULL,
           [IS_ACTIVE]                      CHAR(2) NULL,
           [COM_DIV_MKT_BRAND_PROD_KEY]     VARCHAR(100) NULL,
           [PARENTCOM_DIVMKT_BRAND_PRODKEY] VARCHAR(100) NULL,
           [PRICE_ADJUSTMENT_NAME]          VARCHAR(100) NULL,
           [PRICE]                          NUMERIC(22, 6) NULL,
           [RECORD_SEQUENCE]                VARCHAR(30) NULL,
           [SENT_OUT]                       CHAR(5) NULL,
           [ACCRUAL_PROCESSED]              CHAR(5) NULL,
           [DIVISION_ID]                    VARCHAR(38) NULL,
           [MARKET_ID]                      VARCHAR(38) NULL,
           [BRAND_ID]                       VARCHAR(38) NULL,
           [CLAIM_INDICATOR]                VARCHAR(50) NULL,
           [SALES_AMOUNT]                   NUMERIC(22, 6) NOT NULL,
           [ORGANIZATION_KEY]               VARCHAR(50) NOT NULL,
           [MANDATED_DISCOUNT_AMOUNT]       NUMERIC(22, 6) NULL,
           [PROVISION_CLAIM_INDICATOR]      VARCHAR(10) NULL,
           [PROGRAM_STATE_CODE]             VARCHAR(10) NULL,
           [DISPENSED_DATE]                 DATETIME NULL,
           [INBOUND_STATUS]                 CHAR(1) NULL,
           [RECORD_LOCK_STATUS]             BIT NOT NULL,
           [BATCH_ID]                       VARCHAR(50) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL
        )
  END

 GO

-------------PRIMARY KEYS--------------------

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_ACTUALS_MASTER_ACTUALS_MASTER_SID'
                     AND TABLE_NAME = 'ACTUALS_MASTER')
BEGIN
  ALTER TABLE ACTUALS_MASTER
    ADD CONSTRAINT PK_ACTUALS_MASTER_ACTUALS_MASTER_SID PRIMARY KEY(ACTUALS_MASTER_SID)
END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACTUALS_MASTER')
                      AND NAME = 'DF_ACTUALS_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[ACTUALS_MASTER]
        ADD CONSTRAINT [DF_ACTUALS_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACTUALS_MASTER')
                      AND NAME = 'DF_ACTUALS_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[ACTUALS_MASTER]
        ADD CONSTRAINT [DF_ACTUALS_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACTUALS_MASTER')
                      AND NAME = 'DF_ACTUALS_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ACTUALS_MASTER]
        ADD CONSTRAINT [DF_ACTUALS_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACTUALS_MASTER')
                      AND NAME = 'DF_ACTUALS_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[ACTUALS_MASTER]
        ADD CONSTRAINT [DF_ACTUALS_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACTUALS_MASTER')
                      AND NAME = 'DF_ACTUALS_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ACTUALS_MASTER]
        ADD CONSTRAINT [DF_ACTUALS_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'ACTUALS_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('ACTUALS_MASTER')
                            AND NAME = 'UQ_ACTUALS_MASTER_ACTUAL_ID')
        BEGIN
            ALTER TABLE ACTUALS_MASTER
              ADD CONSTRAINT UQ_ACTUALS_MASTER_ACTUAL_ID UNIQUE(ACTUAL_ID)
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ACTUALS_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------HIST_ACTUALS_MASTER---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ACTUALS_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_ACTUALS_MASTER]
        (
           [ACTUALS_MASTER_SID]             INT NOT NULL,
           [ACTUAL_ID]                      VARCHAR(50) NOT NULL,
           [CONTRACT_ID]                    VARCHAR(50) NOT NULL,
           [UPLOAD_DATE]                    DATETIME NULL,
           [PROVISION_ID]                   VARCHAR(50) NULL,
           [ACCRUAL_ACTUAL_START_DATE]      DATETIME NOT NULL,
           [ACCRUAL_ACTUAL_END_DATE]        DATETIME NOT NULL,
           [ITEM_ID]                        VARCHAR(50) NOT NULL,
           [ITEM_IDENTIFIER_CODE_QUALIFIER] VARCHAR(50) NULL,
           [ITEM_NO]                        VARCHAR(50) NULL,
           [COMPANY_MASTER_SID]             INT NOT NULL,
           [CONTRACT_MASTER_SID]            INT NOT NULL,
           [ITEM_MASTER_SID]                INT NOT NULL,
           [SETTLEMENT_METHOD_NO]           VARCHAR(50) NULL,
           [CASH_PAID_DATE]                 DATETIME NOT NULL,
           [AMOUNT]                         NUMERIC(22, 6) NOT NULL,
           [QUANTITY]                       NUMERIC(22, 6) NOT NULL,
           [QUANTITY_INCLUSION]             VARCHAR(10) NOT NULL,
           [SETTLEMENT_NO]                  VARCHAR(100) NULL,
           [INVOICE_LINE_NO]                VARCHAR(100) NULL,
           [ACCOUNT_ID]                     VARCHAR(100) NOT NULL,
           [ACCT_IDENTIFIER_CODE_QUALIFIER] VARCHAR(100) NULL,
           [ACCOUNT_NO]                     VARCHAR(100) NULL,
           [ACCOUNT_NAME]                   VARCHAR(100) NULL,
           [ANALYSIS_CODE]                  CHAR(50) NULL,
           [IS_ACTIVE]                      CHAR(2) NULL,
           [COM_DIV_MKT_BRAND_PROD_KEY]     VARCHAR(100) NULL,
           [PARENTCOM_DIVMKT_BRAND_PRODKEY] VARCHAR(100) NULL,
           [PRICE_ADJUSTMENT_NAME]          VARCHAR(100) NULL,
           [PRICE]                          NUMERIC(22, 6) NULL,
           [RECORD_SEQUENCE]                VARCHAR(30) NULL,
           [SENT_OUT]                       CHAR(5) NULL,
           [ACCRUAL_PROCESSED]              CHAR(5) NULL,
           [DIVISION_ID]                    VARCHAR(38) NULL,
           [MARKET_ID]                      VARCHAR(38) NULL,
           [BRAND_ID]                       VARCHAR(38) NULL,
           [CLAIM_INDICATOR]                VARCHAR(50) NULL,
           [SALES_AMOUNT]                   NUMERIC(22, 6) NOT NULL,
           [ORGANIZATION_KEY]               VARCHAR(50) NOT NULL,
           [MANDATED_DISCOUNT_AMOUNT]       NUMERIC(22, 6) NULL,
           [PROVISION_CLAIM_INDICATOR]      VARCHAR(10) NULL,
           [PROGRAM_STATE_CODE]             VARCHAR(10) NULL,
           [DISPENSED_DATE]                 DATETIME NULL,
           [RECORD_LOCK_STATUS]             BIT NOT NULL,
           [INBOUND_STATUS]                 CHAR(1) NULL,
           [BATCH_ID]                       VARCHAR(50) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL,
           [ACTION_FLAG]                    CHAR(1) NOT NULL,
           [ACTION_DATE]                    DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_ACTUALS_MASTER')
                      AND NAME = 'DF_HIST_ACTUALS_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_ACTUALS_MASTER]
        ADD CONSTRAINT [DF_HIST_ACTUALS_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ACTUALS_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ACTUALS_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_ACTUALS_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_ACTUALS_MASTER_INS]
ON [DBO].[ACTUALS_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ACTUALS_MASTER
                  (ACTUALS_MASTER_SID,
                   ACTUAL_ID,
                   CONTRACT_ID,
                   UPLOAD_DATE,
                   PROVISION_ID,
                   ACCRUAL_ACTUAL_START_DATE,
                   ACCRUAL_ACTUAL_END_DATE,
                   ITEM_ID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_NO,
                   [COMPANY_MASTER_SID],
                   [CONTRACT_MASTER_SID],
                   [ITEM_MASTER_SID],
                   SETTLEMENT_METHOD_NO,
                   CASH_PAID_DATE,
                   AMOUNT,
                   QUANTITY,
                   QUANTITY_INCLUSION,
                   SETTLEMENT_NO,
                   INVOICE_LINE_NO,
                   ACCOUNT_ID,
                   ACCT_IDENTIFIER_CODE_QUALIFIER,
                   ACCOUNT_NO,
                   ACCOUNT_NAME,
                   ANALYSIS_CODE,
                   IS_ACTIVE,
                   COM_DIV_MKT_BRAND_PROD_KEY,
                   PARENTCOM_DIVMKT_BRAND_PRODKEY,
                   PRICE_ADJUSTMENT_NAME,
                   PRICE,
                   RECORD_SEQUENCE,
                   SENT_OUT,
                   ACCRUAL_PROCESSED,
                   DIVISION_ID,
                   MARKET_ID,
                   BRAND_ID,
                   CLAIM_INDICATOR,
                   SALES_AMOUNT,
                   ORGANIZATION_KEY,
                   MANDATED_DISCOUNT_AMOUNT,
                   PROVISION_CLAIM_INDICATOR,
                   PROGRAM_STATE_CODE,
                   DISPENSED_DATE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT ACTUALS_MASTER_SID,
             ACTUAL_ID,
             CONTRACT_ID,
             UPLOAD_DATE,
             PROVISION_ID,
             ACCRUAL_ACTUAL_START_DATE,
             ACCRUAL_ACTUAL_END_DATE,
             ITEM_ID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_NO,
             [COMPANY_MASTER_SID],
             [CONTRACT_MASTER_SID],
             [ITEM_MASTER_SID],
             SETTLEMENT_METHOD_NO,
             CASH_PAID_DATE,
             AMOUNT,
             QUANTITY,
             QUANTITY_INCLUSION,
             SETTLEMENT_NO,
             INVOICE_LINE_NO,
             ACCOUNT_ID,
             ACCT_IDENTIFIER_CODE_QUALIFIER,
             ACCOUNT_NO,
             ACCOUNT_NAME,
             ANALYSIS_CODE,
             IS_ACTIVE,
             COM_DIV_MKT_BRAND_PROD_KEY,
             PARENTCOM_DIVMKT_BRAND_PRODKEY,
             PRICE_ADJUSTMENT_NAME,
             PRICE,
             RECORD_SEQUENCE,
             SENT_OUT,
             ACCRUAL_PROCESSED,
             DIVISION_ID,
             MARKET_ID,
             BRAND_ID,
             CLAIM_INDICATOR,
             SALES_AMOUNT,
             ORGANIZATION_KEY,
             MANDATED_DISCOUNT_AMOUNT,
             PROVISION_CLAIM_INDICATOR,
             PROGRAM_STATE_CODE,
             DISPENSED_DATE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ACTUALS_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_ACTUALS_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_ACTUALS_MASTER_UPD]
ON [DBO].[ACTUALS_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ACTUALS_MASTER
                  (ACTUALS_MASTER_SID,
                   ACTUAL_ID,
                   CONTRACT_ID,
                   UPLOAD_DATE,
                   PROVISION_ID,
                   ACCRUAL_ACTUAL_START_DATE,
                   ACCRUAL_ACTUAL_END_DATE,
                   ITEM_ID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_NO,
                   [COMPANY_MASTER_SID],
                   [CONTRACT_MASTER_SID],
                   [ITEM_MASTER_SID],
                   SETTLEMENT_METHOD_NO,
                   CASH_PAID_DATE,
                   AMOUNT,
                   QUANTITY,
                   QUANTITY_INCLUSION,
                   SETTLEMENT_NO,
                   INVOICE_LINE_NO,
                   ACCOUNT_ID,
                   ACCT_IDENTIFIER_CODE_QUALIFIER,
                   ACCOUNT_NO,
                   ACCOUNT_NAME,
                   ANALYSIS_CODE,
                   IS_ACTIVE,
                   COM_DIV_MKT_BRAND_PROD_KEY,
                   PARENTCOM_DIVMKT_BRAND_PRODKEY,
                   PRICE_ADJUSTMENT_NAME,
                   PRICE,
                   RECORD_SEQUENCE,
                   SENT_OUT,
                   ACCRUAL_PROCESSED,
                   DIVISION_ID,
                   MARKET_ID,
                   BRAND_ID,
                   CLAIM_INDICATOR,
                   SALES_AMOUNT,
                   ORGANIZATION_KEY,
                   MANDATED_DISCOUNT_AMOUNT,
                   PROVISION_CLAIM_INDICATOR,
                   PROGRAM_STATE_CODE,
                   DISPENSED_DATE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT ACTUALS_MASTER_SID,
             ACTUAL_ID,
             CONTRACT_ID,
             UPLOAD_DATE,
             PROVISION_ID,
             ACCRUAL_ACTUAL_START_DATE,
             ACCRUAL_ACTUAL_END_DATE,
             ITEM_ID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_NO,
             [COMPANY_MASTER_SID],
             [CONTRACT_MASTER_SID],
             [ITEM_MASTER_SID],
             SETTLEMENT_METHOD_NO,
             CASH_PAID_DATE,
             AMOUNT,
             QUANTITY,
             QUANTITY_INCLUSION,
             SETTLEMENT_NO,
             INVOICE_LINE_NO,
             ACCOUNT_ID,
             ACCT_IDENTIFIER_CODE_QUALIFIER,
             ACCOUNT_NO,
             ACCOUNT_NAME,
             ANALYSIS_CODE,
             IS_ACTIVE,
             COM_DIV_MKT_BRAND_PROD_KEY,
             PARENTCOM_DIVMKT_BRAND_PRODKEY,
             PRICE_ADJUSTMENT_NAME,
             PRICE,
             RECORD_SEQUENCE,
             SENT_OUT,
             ACCRUAL_PROCESSED,
             DIVISION_ID,
             MARKET_ID,
             BRAND_ID,
             CLAIM_INDICATOR,
             SALES_AMOUNT,
             ORGANIZATION_KEY,
             MANDATED_DISCOUNT_AMOUNT,
             PROVISION_CLAIM_INDICATOR,
             PROGRAM_STATE_CODE,
             DISPENSED_DATE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ACTUALS_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_ACTUALS_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_ACTUALS_MASTER_DEL]
ON [DBO].[ACTUALS_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ACTUALS_MASTER
                  (ACTUALS_MASTER_SID,
                   ACTUAL_ID,
                   CONTRACT_ID,
                   UPLOAD_DATE,
                   PROVISION_ID,
                   ACCRUAL_ACTUAL_START_DATE,
                   ACCRUAL_ACTUAL_END_DATE,
                   ITEM_ID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_NO,
                   [COMPANY_MASTER_SID],
                   [CONTRACT_MASTER_SID],
                   [ITEM_MASTER_SID],
                   SETTLEMENT_METHOD_NO,
                   CASH_PAID_DATE,
                   AMOUNT,
                   QUANTITY,
                   QUANTITY_INCLUSION,
                   SETTLEMENT_NO,
                   INVOICE_LINE_NO,
                   ACCOUNT_ID,
                   ACCT_IDENTIFIER_CODE_QUALIFIER,
                   ACCOUNT_NO,
                   ACCOUNT_NAME,
                   ANALYSIS_CODE,
                   IS_ACTIVE,
                   COM_DIV_MKT_BRAND_PROD_KEY,
                   PARENTCOM_DIVMKT_BRAND_PRODKEY,
                   PRICE_ADJUSTMENT_NAME,
                   PRICE,
                   RECORD_SEQUENCE,
                   SENT_OUT,
                   ACCRUAL_PROCESSED,
                   DIVISION_ID,
                   MARKET_ID,
                   BRAND_ID,
                   CLAIM_INDICATOR,
                   SALES_AMOUNT,
                   ORGANIZATION_KEY,
                   MANDATED_DISCOUNT_AMOUNT,
                   PROVISION_CLAIM_INDICATOR,
                   PROGRAM_STATE_CODE,
                   DISPENSED_DATE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT ACTUALS_MASTER_SID,
             ACTUAL_ID,
             CONTRACT_ID,
             UPLOAD_DATE,
             PROVISION_ID,
             ACCRUAL_ACTUAL_START_DATE,
             ACCRUAL_ACTUAL_END_DATE,
             ITEM_ID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_NO,
             [COMPANY_MASTER_SID],
             [CONTRACT_MASTER_SID],
             [ITEM_MASTER_SID],
             SETTLEMENT_METHOD_NO,
             CASH_PAID_DATE,
             AMOUNT,
             QUANTITY,
             QUANTITY_INCLUSION,
             SETTLEMENT_NO,
             INVOICE_LINE_NO,
             ACCOUNT_ID,
             ACCT_IDENTIFIER_CODE_QUALIFIER,
             ACCOUNT_NO,
             ACCOUNT_NAME,
             ANALYSIS_CODE,
             IS_ACTIVE,
             COM_DIV_MKT_BRAND_PROD_KEY,
             PARENTCOM_DIVMKT_BRAND_PRODKEY,
             PRICE_ADJUSTMENT_NAME,
             PRICE,
             RECORD_SEQUENCE,
             SENT_OUT,
             ACCRUAL_PROCESSED,
             DIVISION_ID,
             MARKET_ID,
             BRAND_ID,
             CLAIM_INDICATOR,
             SALES_AMOUNT,
             ORGANIZATION_KEY,
             MANDATED_DISCOUNT_AMOUNT,
             PROVISION_CLAIM_INDICATOR,
             PROGRAM_STATE_CODE,
             DISPENSED_DATE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   DELETED
  END

GO
--------------------------------------ACTUALS_DETAILS TABLE-----------------------------------------------


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ACTUALS_DETAILS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[ACTUALS_DETAILS]

  ( 
     [PERIOD_SID]          [INT] NOT NULL, 
     [RS_MODEL_SID]        [VARCHAR](50)  NOT NULL,  
	 [CCP_DETAILS_SID]     [INT] NOT NULL ,
     [SALES]               [NUMERIC](22, 6) NOT NULL, 
     [QUANTITY]            [NUMERIC](22, 6) NOT NULL, 
     [DISCOUNT]            [NUMERIC](22, 6) NOT NULL, 
     [QUANTITY_INCLUSION]  [VARCHAR](10) NOT NULL,
	 [ACTUAL_ID]            [VARCHAR](50) NOT NULL,
	 
	 
  ) 


   END
GO

------Adding 'CASH_PAID_PERIOD' Column in table 'ACTUALS_DETAILS' (Related to ARM-BSR)
IF NOT EXISTS (SELECT 1 
               FROM   INFORMATION_SCHEMA.COLUMNS 
               WHERE  TABLE_NAME = 'ACTUALS_DETAILS' 
                      AND COLUMN_NAME = 'CASH_PAID_PERIOD' 
                      AND TABLE_SCHEMA = 'DBO') 
  BEGIN 
      ALTER TABLE ACTUALS_DETAILS 
        ADD CASH_PAID_PERIOD INT 
  END 

GO

IF EXISTS (SELECT 1 
               FROM   INFORMATION_SCHEMA.COLUMNS 
               WHERE  TABLE_NAME = 'ACTUALS_DETAILS' 
                      AND COLUMN_NAME = 'CASH_PAID_PERIOD' 
                      AND TABLE_SCHEMA = 'DBO') 

IF EXISTS (SELECT 1 
               FROM   ACTUALS_DETAILS
               WHERE  CASH_PAID_PERIOD IS NULL) 

  BEGIN
  IF OBJECT_ID('TEMPDB..#ACTUALS_DETAILS') IS NOT NULL
	DROP TABLE #ACTUALS_DETAILS

	SELECT * INTO #ACTUALS_DETAILS
	FROM ACTUALS_DETAILS

			BEGIN TRY
				BEGIN TRANSACTION

					IF OBJECT_ID('ACTUALS_DETAILS') IS NOT NULL

						TRUNCATE TABLE ACTUALS_DETAILS

						-- THIS INSERT STATEMENT INSERTS ACTUALS FROM ACTUAL_MASTER TO ACTUALS_DETAILS FOR THE CCPS THAT ARE CREATED FROM APPLICATION
						INSERT INTO ACTUALS_DETAILS
						(
						 [PERIOD_SID]         
						 ,[RS_MODEL_SID]      
						 ,[CCP_DETAILS_SID]   
						 ,[SALES]             
						 ,[QUANTITY]          
						 ,[DISCOUNT]          
						 ,[QUANTITY_INCLUSION]
						 ,[ACTUAL_ID] 
						 ,[CASH_PAID_PERIOD]
						)


						SELECT       
						A.PERIOD_SID, 
						RM.RS_MODEL_SID, 
						CCP.CCP_DETAILS_SID ,
						ISNULL((A.SALES), 0)               AS SALES, 
						ISNULL((A.QUANTITY), 0)            AS QUANTITY, 
						ISNULL((A.DISCOUNT), 0)            AS DISCOUNT, 
						ISNULL((A.QUANTITY_INCLUSION), 'Y')AS QUANTITY_INCLUSION,
						A.ACTUAL_ID,
						CASH_PERIOD_SID
						FROM   (SELECT CONTRACT_MASTER_SID, 
											 COMPANY_MASTER_SID, 
											 ITEM_MASTER_SID, 
											 PROVISION_ID, 
											 PERIOD_SID, 
											 YEAR, 
											 MONTH, 
											 QUARTER, 
											 PERIOD_DATE, 
											 SALES, 
											 QUANTITY, 
											 DISCOUNT, 
											 QUANTITY_INCLUSION ,
											 B.ACTUAL_ID,
											 CASH_PERIOD_SID
									  FROM   PERIOD A 
											 JOIN (SELECT PROVISION_ID, 
														  ACCRUAL_ACTUAL_START_DATE   START_DATE, 
														  ACCRUAL_ACTUAL_END_DATE     END_DATE, 
														  QUANTITY_INCLUSION, 
														  A1.CONTRACT_MASTER_SID, 
														  A1.COMPANY_MASTER_SID, 
														  A1.ITEM_MASTER_SID,
														  (SALES_AMOUNT) / ( ISNULL(DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE),0)+ 1 ) SALES, 
														  (QUANTITY) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
																			+ 1 )     QUANTITY, 
														  (AMOUNT) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
																		  + 1 )       DISCOUNT
															,A1.ACTUAL_ID
															,P1.PERIOD_SID AS CASH_PERIOD_SID
												   FROM   ACTUALS_MASTER A1
														  JOIN PERIOD P1 ON DATEADD(DD,1,EOMONTH(A1.CASH_PAID_DATE,-1))=P1.PERIOD_DATE) B 
									 ON A.PERIOD_DATE BETWEEN B.START_DATE AND B.END_DATE)A  
									 JOIN RS_MODEL RM 
									   ON RM.RS_ID = A.PROVISION_ID 
									 JOIN CCP_DETAILS CCP 
									   ON CCP.COMPANY_MASTER_SID = A.COMPANY_MASTER_SID 
										  AND CCP.ITEM_MASTER_SID = A.ITEM_MASTER_SID 
										  AND CCP.CONTRACT_MASTER_SID = A.CONTRACT_MASTER_SID
									  AND NOT EXISTS (SELECT 1 FROM ACTUALS_DETAILS AD WHERE A.ACTUAL_ID = AD.ACTUAL_ID )
    
				COMMIT TRANSACTION;
			END TRY
			BEGIN CATCH
				IF @@TRANCOUNT > 0
					ROLLBACK;
			END CATCH
	END
GO
-------------------NONCLUSTERED INDEX------------------------------------

IF NOT EXISTS (SELECT name FROM sys.indexes  
            WHERE name = N'IXN_ACTUALS_DETAILS_PERIOD_SID_RS_MODEL_SID_CCP_DETAILS_SID_QUANTITY_INCLUSION')   
  CREATE NONCLUSTERED INDEX IXN_ACTUALS_DETAILS_PERIOD_SID_RS_MODEL_SID_CCP_DETAILS_SID_QUANTITY_INCLUSION   
    ON ACTUALS_DETAILS (PERIOD_SID,CCP_DETAILS_SID,RS_MODEL_SID,QUANTITY_INCLUSION)  INCLUDE(
	SALES,QUANTITY,DISCOUNT)
GO  
   
---------------------------------STATISTICS-----------------------------------

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ACTUALS_DETAILS'--TABLE NAME
SET @SCHEMANAME1 ='dbo' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND Schema_name(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               Schema_name(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND Schema_name(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR
      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + Quotename(@SCHEMANAME)
                       + '.' + Quotename(@TABLENAME) + '.'
                       + Quotename(@STATSNAME)

            --PRINT @SQL
            EXEC Sp_executesql
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END
DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + Quotename(C.NAME)
         + ' ON ' + Quotename(Schema_name(SCHEMA_ID))
         + '.' + Quotename(T.NAME) + ' ('
         + Quotename(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = Schema_name(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND Schema_name(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME
OPEN CUR1
FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END
CLOSE CUR1
DEALLOCATE CUR1
GO 

-------------AVERAGE_SHELF_LIFE_MASTER----------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'AVERAGE_SHELF_LIFE_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[AVERAGE_SHELF_LIFE_MASTER]
        (
           [AVERAGE_SHELF_LIFE_MASTER_SID] INT IDENTITY(1, 1) NOT NULL,
           [ITEM_ID_TYPE]                  VARCHAR(10) NOT NULL,
           [ITEM_ID]                       VARCHAR(50) NOT NULL,
           [AVG_SHELF_LIFE]                NUMERIC(22, 6) NOT NULL,
           [INBOUND_STATUS]                CHAR(1) NULL,
           [RECORD_LOCK_STATUS]            BIT NOT NULL,
           [BATCH_ID]                      VARCHAR(50) NOT NULL,
           [SOURCE]                        VARCHAR(50) NULL,
           [CREATED_BY]                    INT NOT NULL,
           [CREATED_DATE]                  DATETIME NOT NULL,
           [MODIFIED_BY]                   INT NOT NULL,
           [MODIFIED_DATE]                 DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_AVERAGE_SHELF_LIFE_MASTER_AVERAGE_SHELF_LIFE_MASTER_SID'
                     AND TABLE_NAME = 'AVERAGE_SHELF_LIFE_MASTER')
BEGIN
  ALTER TABLE AVERAGE_SHELF_LIFE_MASTER
    ADD CONSTRAINT PK_AVERAGE_SHELF_LIFE_MASTER_AVERAGE_SHELF_LIFE_MASTER_SID PRIMARY KEY(AVERAGE_SHELF_LIFE_MASTER_SID)
END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.AVERAGE_SHELF_LIFE_MASTER')
                      AND NAME = 'DF_AVERAGE_SHELF_LIFE_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[AVERAGE_SHELF_LIFE_MASTER]
        ADD CONSTRAINT [DF_AVERAGE_SHELF_LIFE_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.AVERAGE_SHELF_LIFE_MASTER')
                      AND NAME = 'DF_AVERAGE_SHELF_LIFE_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[AVERAGE_SHELF_LIFE_MASTER]
        ADD CONSTRAINT [DF_AVERAGE_SHELF_LIFE_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.AVERAGE_SHELF_LIFE_MASTER')
                      AND NAME = 'DF_AVERAGE_SHELF_LIFE_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[AVERAGE_SHELF_LIFE_MASTER]
        ADD CONSTRAINT [DF_AVERAGE_SHELF_LIFE_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.AVERAGE_SHELF_LIFE_MASTER')
                      AND NAME = 'DF_AVERAGE_SHELF_LIFE_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[AVERAGE_SHELF_LIFE_MASTER]
        ADD CONSTRAINT [DF_AVERAGE_SHELF_LIFE_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.AVERAGE_SHELF_LIFE_MASTER')
                      AND NAME = 'DF_AVERAGE_SHELF_LIFE_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[AVERAGE_SHELF_LIFE_MASTER]
        ADD CONSTRAINT [DF_AVERAGE_SHELF_LIFE_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

---------------------UNIQUE_CONSTRAINT-----------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'AVERAGE_SHELF_LIFE_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('AVERAGE_SHELF_LIFE_MASTER')
                            AND NAME = 'UQ_AVERAGE_SHELF_LIFE_MASTER_ITEM_ID_TYPE_ITEM_ID')
        BEGIN
            ALTER TABLE AVERAGE_SHELF_LIFE_MASTER
              ADD CONSTRAINT UQ_AVERAGE_SHELF_LIFE_MASTER_ITEM_ID_TYPE_ITEM_ID UNIQUE(ITEM_ID_TYPE, ITEM_ID)
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'AVERAGE_SHELF_LIFE_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

---------------------HIST_AVERAGE_SHELF_LIFE_MASTER----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_AVERAGE_SHELF_LIFE_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_AVERAGE_SHELF_LIFE_MASTER]
        (
           [AVERAGE_SHELF_LIFE_MASTER_SID] INT NOT NULL,
           [ITEM_ID_TYPE]                  VARCHAR(10) NOT NULL,
           [ITEM_ID]                       VARCHAR(50) NOT NULL,
           [AVG_SHELF_LIFE]                NUMERIC(22, 6) NOT NULL,
           [INBOUND_STATUS]                CHAR(1) NULL,
           [RECORD_LOCK_STATUS]            BIT NOT NULL,
           [BATCH_ID]                      VARCHAR(50) NOT NULL,
           [SOURCE]                        VARCHAR(50) NULL,
           [CREATED_BY]                    INT NOT NULL,
           [CREATED_DATE]                  DATETIME NOT NULL,
           [MODIFIED_BY]                   INT NOT NULL,
           [MODIFIED_DATE]                 DATETIME NOT NULL,
           [ACTION_FLAG]                   CHAR(1) NOT NULL,
           [ACTION_DATE]                   DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_AVERAGE_SHELF_LIFE_MASTER')
                      AND NAME = 'DF_HIST_AVERAGE_SHELF_LIFE_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_AVERAGE_SHELF_LIFE_MASTER]
        ADD CONSTRAINT [DF_HIST_AVERAGE_SHELF_LIFE_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_AVERAGE_SHELF_LIFE_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_AVG_SHELF_LIFE_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_AVG_SHELF_LIFE_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_AVG_SHELF_LIFE_MASTER_INS]
ON [DBO].[AVERAGE_SHELF_LIFE_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_AVERAGE_SHELF_LIFE_MASTER
                  (AVERAGE_SHELF_LIFE_MASTER_SID,
                   ITEM_ID_TYPE,
                   ITEM_ID,
                   AVG_SHELF_LIFE,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT AVERAGE_SHELF_LIFE_MASTER_SID,
             ITEM_ID_TYPE,
             ITEM_ID,
             AVG_SHELF_LIFE,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_AVG_SHELF_LIFE_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_AVG_SHELF_LIFE_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_AVG_SHELF_LIFE_MASTER_UPD]
ON [DBO].[AVERAGE_SHELF_LIFE_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_AVERAGE_SHELF_LIFE_MASTER
                  (AVERAGE_SHELF_LIFE_MASTER_SID,
                   ITEM_ID_TYPE,
                   ITEM_ID,
                   AVG_SHELF_LIFE,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT AVERAGE_SHELF_LIFE_MASTER_SID,
             ITEM_ID_TYPE,
             ITEM_ID,
             AVG_SHELF_LIFE,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_AVG_SHELF_LIFE_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_AVG_SHELF_LIFE_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_AVG_SHELF_LIFE_MASTER_DEL]
ON [DBO].[AVERAGE_SHELF_LIFE_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_AVERAGE_SHELF_LIFE_MASTER
                  (AVERAGE_SHELF_LIFE_MASTER_SID,
                   ITEM_ID_TYPE,
                   ITEM_ID,
                   AVG_SHELF_LIFE,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT AVERAGE_SHELF_LIFE_MASTER_SID,
             ITEM_ID_TYPE,
             ITEM_ID,
             AVG_SHELF_LIFE,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   DELETED
  END

GO

------------------------BEST_PRICE_MASTER------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'BEST_PRICE_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[BEST_PRICE_MASTER]
        (
           [BEST_PRICE_MASTER_SID] INT IDENTITY(1, 1) NOT NULL,
           [ITEM_ID]               VARCHAR(50) NOT NULL,
           [ITEM_NO]               VARCHAR(50) NULL,
           [ITEM_DESC]             VARCHAR(250) NULL,
           [BEGINNING_WAC_PACKAGE] NUMERIC(22, 6) NULL,
           [SELLING_PRICE]         NUMERIC(22, 6) NULL,
           [INITIAL_BEST_PRICE]    NUMERIC(22, 6) NULL,
           [INITIAL_DISCOUNT]      NUMERIC(22, 6) NULL,
           [CONTRACT_NO]           VARCHAR(50) NULL,
           [CONTRACT_ID]           VARCHAR(50) NOT NULL,
           [ACCOUNT_ID]            VARCHAR(100) NOT NULL,
           [CONTRACT_START_DATE]   DATETIME NOT NULL,
           [CONTRACT_END_DATE]     DATETIME NULL,
           [PERIOD]                VARCHAR(10) NULL,
           [INBOUND_STATUS]        CHAR(1) NULL,
           [RECORD_LOCK_STATUS]    BIT NOT NULL,
           [BATCH_ID]              VARCHAR(50) NOT NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [CREATED_BY]            INT NOT NULL,
           [CREATED_DATE]          DATETIME NOT NULL,
           [MODIFIED_BY]           INT NOT NULL,
           [MODIFIED_DATE]         DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_BEST_PRICE_MASTER_BEST_PRICE_MASTER_SID'
                     AND TABLE_NAME = 'BEST_PRICE_MASTER')
BEGIN
  ALTER TABLE BEST_PRICE_MASTER
    ADD CONSTRAINT PK_BEST_PRICE_MASTER_BEST_PRICE_MASTER_SID PRIMARY KEY(BEST_PRICE_MASTER_SID)
END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.BEST_PRICE_MASTER')
                      AND NAME = 'DF_BEST_PRICE_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[BEST_PRICE_MASTER]
        ADD CONSTRAINT [DF_BEST_PRICE_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.BEST_PRICE_MASTER')
                      AND NAME = 'DF_BEST_PRICE_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[BEST_PRICE_MASTER]
        ADD CONSTRAINT [DF_BEST_PRICE_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.BEST_PRICE_MASTER')
                      AND NAME = 'DF_BEST_PRICE_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[BEST_PRICE_MASTER]
        ADD CONSTRAINT [DF_BEST_PRICE_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.BEST_PRICE_MASTER')
                      AND NAME = 'DF_BEST_PRICE_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[BEST_PRICE_MASTER]
        ADD CONSTRAINT [DF_BEST_PRICE_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.BEST_PRICE_MASTER')
                      AND NAME = 'DF_BEST_PRICE_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[BEST_PRICE_MASTER]
        ADD CONSTRAINT [DF_BEST_PRICE_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

------------------------UNIQUE_CONSTRAINT-------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'BEST_PRICE_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('BEST_PRICE_MASTER')
                            AND NAME = 'UQ_BEST_PRICE_MASTER_ITEM_ID_ACCOUNT_ID_PERIOD_CONTRACT_ID')
        BEGIN
            ALTER TABLE BEST_PRICE_MASTER
              ADD CONSTRAINT UQ_BEST_PRICE_MASTER_ITEM_ID_ACCOUNT_ID_PERIOD_CONTRACT_ID UNIQUE(ITEM_ID, ACCOUNT_ID, PERIOD, CONTRACT_ID)
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'BEST_PRICE_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

--------------------HIST_BEST_PRICE_MASTER-------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_BEST_PRICE_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_BEST_PRICE_MASTER]
        (
           [BEST_PRICE_MASTER_SID] INT NOT NULL,
           [ITEM_ID]               VARCHAR(50) NOT NULL,
           [ITEM_NO]               VARCHAR(50) NULL,
           [ITEM_DESC]             VARCHAR(250) NULL,
           [BEGINNING_WAC_PACKAGE] NUMERIC(22, 6) NULL,
           [SELLING_PRICE]         NUMERIC(22, 6) NULL,
           [INITIAL_BEST_PRICE]    NUMERIC(22, 6) NULL,
           [INITIAL_DISCOUNT]      NUMERIC(22, 6) NULL,
           [CONTRACT_NO]           VARCHAR(50) NULL,
           [CONTRACT_ID]           VARCHAR(50) NOT NULL,
           [ACCOUNT_ID]            VARCHAR(100) NOT NULL,
           [CONTRACT_START_DATE]   DATETIME NOT NULL,
           [CONTRACT_END_DATE]     DATETIME NULL,
           [PERIOD]                VARCHAR(10) NULL,
           [INBOUND_STATUS]        CHAR(1) NULL,
           [RECORD_LOCK_STATUS]    BIT NOT NULL,
           [BATCH_ID]              VARCHAR(50) NOT NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [CREATED_BY]            INT NOT NULL,
           [CREATED_DATE]          DATETIME NOT NULL,
           [MODIFIED_BY]           INT NOT NULL,
           [MODIFIED_DATE]         DATETIME NOT NULL,
           [ACTION_FLAG]           CHAR(1) NOT NULL,
           [ACTION_DATE]           DATETIME NOT NULL
        )
  END

GO

------------------DEFAULT_CONSTRAINTS-------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_BEST_PRICE_MASTER')
                      AND NAME = 'DF_HIST_BEST_PRICE_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_BEST_PRICE_MASTER]
        ADD CONSTRAINT [DF_HIST_BEST_PRICE_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_BEST_PRICE_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_BEST_PRICE_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_BEST_PRICE_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_BEST_PRICE_MASTER_INS]
ON [DBO].[BEST_PRICE_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_BEST_PRICE_MASTER
                  (BEST_PRICE_MASTER_SID,
                   ITEM_ID,
                   ITEM_NO,
                   ITEM_DESC,
                   BEGINNING_WAC_PACKAGE,
                   SELLING_PRICE,
                   INITIAL_BEST_PRICE,
                   INITIAL_DISCOUNT,
                   CONTRACT_NO,
                   CONTRACT_ID,
                   ACCOUNT_ID,
                   CONTRACT_START_DATE,
                   CONTRACT_END_DATE,
                   PERIOD,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT BEST_PRICE_MASTER_SID,
             ITEM_ID,
             ITEM_NO,
             ITEM_DESC,
             BEGINNING_WAC_PACKAGE,
             SELLING_PRICE,
             INITIAL_BEST_PRICE,
             INITIAL_DISCOUNT,
             CONTRACT_NO,
             CONTRACT_ID,
             ACCOUNT_ID,
             CONTRACT_START_DATE,
             CONTRACT_END_DATE,
             PERIOD,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_BEST_PRICE_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_BEST_PRICE_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_BEST_PRICE_MASTER_UPD]
ON [DBO].[BEST_PRICE_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_BEST_PRICE_MASTER
                  (BEST_PRICE_MASTER_SID,
                   ITEM_ID,
                   ITEM_NO,
                   ITEM_DESC,
                   BEGINNING_WAC_PACKAGE,
                   SELLING_PRICE,
                   INITIAL_BEST_PRICE,
                   INITIAL_DISCOUNT,
                   CONTRACT_NO,
                   CONTRACT_ID,
                   ACCOUNT_ID,
                   CONTRACT_START_DATE,
                   CONTRACT_END_DATE,
                   PERIOD,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT BEST_PRICE_MASTER_SID,
             ITEM_ID,
             ITEM_NO,
             ITEM_DESC,
             BEGINNING_WAC_PACKAGE,
             SELLING_PRICE,
             INITIAL_BEST_PRICE,
             INITIAL_DISCOUNT,
             CONTRACT_NO,
             CONTRACT_ID,
             ACCOUNT_ID,
             CONTRACT_START_DATE,
             CONTRACT_END_DATE,
             PERIOD,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_BEST_PRICE_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_BEST_PRICE_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_BEST_PRICE_MASTER_DEL]
ON [DBO].[BEST_PRICE_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_BEST_PRICE_MASTER
                  (BEST_PRICE_MASTER_SID,
                   ITEM_ID,
                   ITEM_NO,
                   ITEM_DESC,
                   BEGINNING_WAC_PACKAGE,
                   SELLING_PRICE,
                   INITIAL_BEST_PRICE,
                   INITIAL_DISCOUNT,
                   CONTRACT_NO,
                   CONTRACT_ID,
                   ACCOUNT_ID,
                   CONTRACT_START_DATE,
                   CONTRACT_END_DATE,
                   PERIOD,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT BEST_PRICE_MASTER_SID,
             ITEM_ID,
             ITEM_NO,
             ITEM_DESC,
             BEGINNING_WAC_PACKAGE,
             SELLING_PRICE,
             INITIAL_BEST_PRICE,
             INITIAL_DISCOUNT,
             CONTRACT_NO,
             CONTRACT_ID,
             ACCOUNT_ID,
             CONTRACT_START_DATE,
             CONTRACT_END_DATE,
             PERIOD,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   DELETED
  END

GO

----------------------CPI_INDEX_MASTER-----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CPI_INDEX_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[CPI_INDEX_MASTER]
        (
           [CPI_INDEX_MASTER_SID] INT IDENTITY(1, 1) NOT NULL,
           [INDEX_ID]             INT NULL,
           [STATUS]               VARCHAR(10) NULL,
           [INDEX_TYPE]           VARCHAR(50) NULL,
           [EFFECTIVE_DATE]       DATETIME NULL,
           [INDEX_VALUE]          NUMERIC(22, 6) NULL,
           [INBOUND_STATUS]       CHAR(1) NULL,
           [RECORD_LOCK_STATUS]   BIT NOT NULL,
           [BATCH_ID]             VARCHAR(50) NOT NULL,
           [SOURCE]               VARCHAR(50) NULL,
           [CREATED_BY]           INT NOT NULL,
           [CREATED_DATE]         DATETIME NOT NULL,
           [MODIFIED_BY]          INT NOT NULL,
           [MODIFIED_DATE]        DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_CPI_INDEX_MASTER_CPI_INDEX_MASTER_SID'
                     AND TABLE_NAME = 'CPI_INDEX_MASTER')
  BEGIN
      ALTER TABLE CPI_INDEX_MASTER
        ADD CONSTRAINT PK_CPI_INDEX_MASTER_CPI_INDEX_MASTER_SID PRIMARY KEY(CPI_INDEX_MASTER_SID)
  END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CPI_INDEX_MASTER')
                      AND NAME = 'DF_CPI_INDEX_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[CPI_INDEX_MASTER]
        ADD CONSTRAINT [DF_CPI_INDEX_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CPI_INDEX_MASTER')
                      AND NAME = 'DF_CPI_INDEX_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[CPI_INDEX_MASTER]
        ADD CONSTRAINT [DF_CPI_INDEX_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CPI_INDEX_MASTER')
                      AND NAME = 'DF_CPI_INDEX_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[CPI_INDEX_MASTER]
        ADD CONSTRAINT [DF_CPI_INDEX_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CPI_INDEX_MASTER')
                      AND NAME = 'DF_CPI_INDEX_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[CPI_INDEX_MASTER]
        ADD CONSTRAINT [DF_CPI_INDEX_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CPI_INDEX_MASTER')
                      AND NAME = 'DF_CPI_INDEX_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[CPI_INDEX_MASTER]
        ADD CONSTRAINT [DF_CPI_INDEX_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

----------------------UNIQUE_CONSTRAINT------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'CPI_INDEX_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('CPI_INDEX_MASTER')
                            AND NAME = 'UQ_CPI_INDEX_MASTER_INDEX_ID')
        BEGIN
            ALTER TABLE CPI_INDEX_MASTER
              ADD CONSTRAINT UQ_CPI_INDEX_MASTER_INDEX_ID UNIQUE(INDEX_ID)
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'CPI_INDEX_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

------------------HIST_CPI_INDEX_MASTER---------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_CPI_INDEX_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_CPI_INDEX_MASTER]
        (
           [CPI_INDEX_MASTER_SID] INT NOT NULL,
           [INDEX_ID]             INT NULL,
           [STATUS]               VARCHAR(10) NULL,
           [INDEX_TYPE]           VARCHAR(50) NULL,
           [EFFECTIVE_DATE]       DATETIME NULL,
           [INDEX_VALUE]          NUMERIC(22, 6) NULL,
           [INBOUND_STATUS]       CHAR(1) NULL,
           [RECORD_LOCK_STATUS]   BIT NOT NULL,
           [BATCH_ID]             VARCHAR(50) NOT NULL,
           [SOURCE]               VARCHAR(50) NULL,
           [CREATED_BY]           INT NOT NULL,
           [CREATED_DATE]         DATETIME NOT NULL,
           [MODIFIED_BY]          INT NOT NULL,
           [MODIFIED_DATE]        DATETIME NOT NULL,
           [ACTION_FLAG]          CHAR(1) NOT NULL,
           [ACTION_DATE]          DATETIME NOT NULL
        )
  END

GO

---------------------HIST_CPI_INDEX_MASTER---------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_CPI_INDEX_MASTER')
                      AND NAME = 'DF_HIST_CPI_INDEX_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_CPI_INDEX_MASTER]
        ADD CONSTRAINT [DF_HIST_CPI_INDEX_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

------------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_CPI_INDEX_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CPI_INDEX_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_CPI_INDEX_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_CPI_INDEX_MASTER_INS]
ON [DBO].[CPI_INDEX_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_CPI_INDEX_MASTER
                  (CPI_INDEX_MASTER_SID,
                   INDEX_ID,
                   STATUS,
                   INDEX_TYPE,
                   EFFECTIVE_DATE,
                   INDEX_VALUE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT CPI_INDEX_MASTER_SID,
             INDEX_ID,
             STATUS,
             INDEX_TYPE,
             EFFECTIVE_DATE,
             INDEX_VALUE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CPI_INDEX_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_CPI_INDEX_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_CPI_INDEX_MASTER_UPD]
ON [DBO].[CPI_INDEX_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_CPI_INDEX_MASTER
                  (CPI_INDEX_MASTER_SID,
                   INDEX_ID,
                   STATUS,
                   INDEX_TYPE,
                   EFFECTIVE_DATE,
                   INDEX_VALUE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT CPI_INDEX_MASTER_SID,
             INDEX_ID,
             STATUS,
             INDEX_TYPE,
             EFFECTIVE_DATE,
             INDEX_VALUE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CPI_INDEX_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_CPI_INDEX_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_CPI_INDEX_MASTER_DEL]
ON [DBO].[CPI_INDEX_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_CPI_INDEX_MASTER
                  (CPI_INDEX_MASTER_SID,
                   INDEX_ID,
                   STATUS,
                   INDEX_TYPE,
                   EFFECTIVE_DATE,
                   INDEX_VALUE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT CPI_INDEX_MASTER_SID,
             INDEX_ID,
             STATUS,
             INDEX_TYPE,
             EFFECTIVE_DATE,
             INDEX_VALUE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   DELETED
  END

GO

--------------------------FORECASTING_MASTER---------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'FORECASTING_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[FORECASTING_MASTER]
        (
           [FORECAST_MASTER_SID]            INT IDENTITY(1, 1) NOT NULL,
           [FORECAST_YEAR]                  SMALLINT NULL,
           [FORECAST_MONTH]                 TINYINT NULL,
           [NDC]                            VARCHAR(50) NULL,
           [FORECAST_START_DATE]            DATETIME NULL,
           [UNITS]                          NUMERIC(22, 6) NULL,
           [DOLLARS]                        NUMERIC(22, 6) NULL,
           [FORECAST_VALUE_TYPE]            VARCHAR(50) NULL,
           [FORECAST_VALUE]                 VARCHAR(50) NULL,
           [PRODUCT]                        VARCHAR(50) NULL,
           [BRAND]                          VARCHAR(50) NULL,
           [SEGMENT]                        VARCHAR(100) NULL,
           [PERCENTAGE_ESTIMATE]            VARCHAR(50) NULL,
           [PERCENTAGE_ESTIMATE_YEAR]       SMALLINT NULL,
           [ACTUAL_SALES_PERCENTAGE]        VARCHAR(50) NULL,
           [ACTUAL_SALES_PERCENTAGE_MONTH]  TINYINT NULL,
           [FORECASTED_SALES_PERCENTAGE]    VARCHAR(50) NULL,
           [FORECASTED_SALES_PERCENT_MONTH] TINYINT NULL,
           [FORECAST_VER]                   VARCHAR(50) NULL,
           [PRICE]                          NUMERIC(22, 6) NULL,
           [COUNTRY]                        VARCHAR(50) NOT NULL,
           [FORECAST_NAME]                  VARCHAR(100) NULL,
           [FORECAST_DATE]                  DATETIME NOT NULL,
           [INBOUND_STATUS]                 CHAR(1) NULL,
           [RECORD_LOCK_STATUS]             BIT NOT NULL,
           [BATCH_ID]                       VARCHAR(50) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  OBJECT_NAME(OBJECT_ID) = N'FORECASTING_MASTER'
                      AND OBJECT_SCHEMA_NAME(OBJECT_ID) = N'DBO'
                      AND [NAME] = N'PK_FORECASTING_MASTER_FORECAST_MASTER_SID')
  BEGIN
      ALTER TABLE [DBO].[FORECASTING_MASTER]
        ADD CONSTRAINT [PK_FORECASTING_MASTER_FORECAST_MASTER_SID] PRIMARY KEY ( [FORECAST_MASTER_SID] )
  END

GO

--GAL-5751 CHANGES
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECASTING_MASTER'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE FORECASTING_MASTER		
        ADD BUSINESS_UNIT INT 
  END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.FORECASTING_MASTER')
                      AND NAME = 'DF_FORECASTING_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[FORECASTING_MASTER]
        ADD CONSTRAINT [DF_FORECASTING_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.FORECASTING_MASTER')
                      AND NAME = 'DF_FORECASTING_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[FORECASTING_MASTER]
        ADD CONSTRAINT [DF_FORECASTING_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.FORECASTING_MASTER')
                      AND NAME = 'DF_FORECASTING_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[FORECASTING_MASTER]
        ADD CONSTRAINT [DF_FORECASTING_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.FORECASTING_MASTER')
                      AND NAME = 'DF_FORECASTING_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[FORECASTING_MASTER]
        ADD CONSTRAINT [DF_FORECASTING_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.FORECASTING_MASTER')
                      AND NAME = 'DF_FORECASTING_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[FORECASTING_MASTER]
        ADD CONSTRAINT [DF_FORECASTING_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

-------------------------UNIQUE_CONSTRAINT------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'FORECASTING_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('FORECASTING_MASTER')
                            AND NAME = 'UQ_FORECASTING_MASTER_COUNTRY_FORECAST_YEAR_FORECAST_MONTH_NDC_FORECAST_START_DATE_CREATED_DATE')
        BEGIN
            ALTER TABLE FORECASTING_MASTER
              ADD CONSTRAINT UQ_FORECASTING_MASTER_COUNTRY_FORECAST_YEAR_FORECAST_MONTH_NDC_FORECAST_START_DATE_CREATED_DATE UNIQUE(COUNTRY, FORECAST_YEAR, FORECAST_MONTH, NDC, FORECAST_START_DATE, CREATED_DATE)
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'FORECASTING_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

--------------------HIST_FORECASTING_MASTER-----------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_FORECASTING_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_FORECASTING_MASTER]
        (
           [FORECAST_MASTER_SID]            INT NOT NULL,
           [FORECAST_YEAR]                  SMALLINT NULL,
           [FORECAST_MONTH]                 TINYINT NULL,
           [NDC]                            VARCHAR(50) NULL,
           [FORECAST_START_DATE]            DATETIME NULL,
           [UNITS]                          NUMERIC(22, 6) NULL,
           [DOLLARS]                        NUMERIC(22, 6) NULL,
           [FORECAST_VALUE_TYPE]            VARCHAR(50) NULL,
           [FORECAST_VALUE]                 VARCHAR(50) NULL,
           [PRODUCT]                        VARCHAR(50) NULL,
           [BRAND]                          VARCHAR(50) NULL,
           [SEGMENT]                        VARCHAR(100) NULL,
           [PERCENTAGE_ESTIMATE]            VARCHAR(50) NULL,
           [PERCENTAGE_ESTIMATE_YEAR]       SMALLINT NULL,
           [ACTUAL_SALES_PERCENTAGE]        VARCHAR(50) NULL,
           [ACTUAL_SALES_PERCENTAGE_MONTH]  TINYINT NULL,
           [FORECASTED_SALES_PERCENTAGE]    VARCHAR(50) NULL,
           [FORECASTED_SALES_PERCENT_MONTH] TINYINT NULL,
           [FORECAST_VER]                   VARCHAR(50) NULL,
           [PRICE]                          NUMERIC(22, 6) NULL,
           [COUNTRY]                        VARCHAR(50) NOT NULL,
           [FORECAST_NAME]                  VARCHAR(100) NULL,
           [FORECAST_DATE]                  DATETIME NOT NULL,
           [INBOUND_STATUS]                 CHAR(1) NULL,
           [RECORD_LOCK_STATUS]             BIT NOT NULL,
           [BATCH_ID]                       VARCHAR(50) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL,
           [ACTION_FLAG]                    CHAR(1) NOT NULL,
           [ACTION_DATE]                    DATETIME NOT NULL,
        )
  END

GO

-------------------DEFAULT_CONSTRAINTS----------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_FORECASTING_MASTER')
                      AND NAME = 'DF_HIST_FORECASTING_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_FORECASTING_MASTER]
        ADD CONSTRAINT [DF_HIST_FORECASTING_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO
--GAL-5751 CHANGES
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_FORECASTING_MASTER'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_FORECASTING_MASTER		
        ADD BUSINESS_UNIT INT 
  END

GO

-----------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_FORECASTING_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_FORECASTING_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_FORECASTING_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_FORECASTING_MASTER_INS]
ON [DBO].[FORECASTING_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_FORECASTING_MASTER
                  (FORECAST_MASTER_SID,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   NDC,
                   FORECAST_START_DATE,
                   UNITS,
                   DOLLARS,
                   FORECAST_VALUE_TYPE,
                   FORECAST_VALUE,
                   PRODUCT,
                   BRAND,
                   SEGMENT,
                   PERCENTAGE_ESTIMATE,
                   PERCENTAGE_ESTIMATE_YEAR,
                   ACTUAL_SALES_PERCENTAGE,
                   ACTUAL_SALES_PERCENTAGE_MONTH,
                   FORECASTED_SALES_PERCENTAGE,
                   FORECASTED_SALES_PERCENT_MONTH,
                   FORECAST_VER,
                   PRICE,
                   COUNTRY,
                   FORECAST_NAME,
                   FORECAST_DATE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
				   BUSINESS_UNIT)
      SELECT FORECAST_MASTER_SID,
             FORECAST_YEAR,
             FORECAST_MONTH,
             NDC,
             FORECAST_START_DATE,
             UNITS,
             DOLLARS,
             FORECAST_VALUE_TYPE,
             FORECAST_VALUE,
             PRODUCT,
             BRAND,
             SEGMENT,
             PERCENTAGE_ESTIMATE,
             PERCENTAGE_ESTIMATE_YEAR,
             ACTUAL_SALES_PERCENTAGE,
             ACTUAL_SALES_PERCENTAGE_MONTH,
             FORECASTED_SALES_PERCENTAGE,
             FORECASTED_SALES_PERCENT_MONTH,
             FORECAST_VER,
             PRICE,
             COUNTRY,
             FORECAST_NAME,
             FORECAST_DATE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO


IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_FORECASTING_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_FORECASTING_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_FORECASTING_MASTER_UPD]
ON [DBO].[FORECASTING_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_FORECASTING_MASTER
                  (FORECAST_MASTER_SID,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   NDC,
                   FORECAST_START_DATE,
                   UNITS,
                   DOLLARS,
                   FORECAST_VALUE_TYPE,
                   FORECAST_VALUE,
                   PRODUCT,
                   BRAND,
                   SEGMENT,
                   PERCENTAGE_ESTIMATE,
                   PERCENTAGE_ESTIMATE_YEAR,
                   ACTUAL_SALES_PERCENTAGE,
                   ACTUAL_SALES_PERCENTAGE_MONTH,
                   FORECASTED_SALES_PERCENTAGE,
                   FORECASTED_SALES_PERCENT_MONTH,
                   FORECAST_VER,
                   PRICE,
                   COUNTRY,
                   FORECAST_NAME,
                   FORECAST_DATE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
				   BUSINESS_UNIT)
      SELECT FORECAST_MASTER_SID,
             FORECAST_YEAR,
             FORECAST_MONTH,
             NDC,
             FORECAST_START_DATE,
             UNITS,
             DOLLARS,
             FORECAST_VALUE_TYPE,
             FORECAST_VALUE,
             PRODUCT,
             BRAND,
             SEGMENT,
             PERCENTAGE_ESTIMATE,
             PERCENTAGE_ESTIMATE_YEAR,
             ACTUAL_SALES_PERCENTAGE,
             ACTUAL_SALES_PERCENTAGE_MONTH,
             FORECASTED_SALES_PERCENTAGE,
             FORECASTED_SALES_PERCENT_MONTH,
             FORECAST_VER,
             PRICE,
             COUNTRY,
             FORECAST_NAME,
             FORECAST_DATE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_FORECASTING_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_FORECASTING_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_FORECASTING_MASTER_DEL]
ON [DBO].[FORECASTING_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_FORECASTING_MASTER
                  (FORECAST_MASTER_SID,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   NDC,
                   FORECAST_START_DATE,
                   UNITS,
                   DOLLARS,
                   FORECAST_VALUE_TYPE,
                   FORECAST_VALUE,
                   PRODUCT,
                   BRAND,
                   SEGMENT,
                   PERCENTAGE_ESTIMATE,
                   PERCENTAGE_ESTIMATE_YEAR,
                   ACTUAL_SALES_PERCENTAGE,
                   ACTUAL_SALES_PERCENTAGE_MONTH,
                   FORECASTED_SALES_PERCENTAGE,
                   FORECASTED_SALES_PERCENT_MONTH,
                   FORECAST_VER,
                   PRICE,
                   COUNTRY,
                   FORECAST_NAME,
                   FORECAST_DATE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
				   BUSINESS_UNIT)
      SELECT FORECAST_MASTER_SID,
             FORECAST_YEAR,
             FORECAST_MONTH,
             NDC,
             FORECAST_START_DATE,
             UNITS,
             DOLLARS,
             FORECAST_VALUE_TYPE,
             FORECAST_VALUE,
             PRODUCT,
             BRAND,
             SEGMENT,
             PERCENTAGE_ESTIMATE,
             PERCENTAGE_ESTIMATE_YEAR,
             ACTUAL_SALES_PERCENTAGE,
             ACTUAL_SALES_PERCENTAGE_MONTH,
             FORECASTED_SALES_PERCENTAGE,
             FORECASTED_SALES_PERCENT_MONTH,
             FORECAST_VER,
             PRICE,
             COUNTRY,
             FORECAST_NAME,
             FORECAST_DATE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
			 BUSINESS_UNIT
      FROM   DELETED
  END

GO

-----------------------FORMULA_DETAILS_MASTER-----------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'FORMULA_DETAILS_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[FORMULA_DETAILS_MASTER]
        (
           [FORMULA_DETAILS_MASTER_SID] INT IDENTITY(1, 1) NOT NULL,
           [FORMULA_ID]                 BIGINT NOT NULL,
           [FORMULA_NO]                 VARCHAR(50) NOT NULL,
           [FORMULA_DESC]               VARCHAR(50) NOT NULL,
           [COMPANY_ID]                 VARCHAR(38) NOT NULL,
           [ITEM_ID]                    VARCHAR(38) NOT NULL,
           [START_DATE]                 DATETIME NOT NULL,
           [END_DATE]                   DATETIME NULL,
           [REBATE_PERCENT_1]           NUMERIC(22, 6) NULL,
           [REBATE_PERCENT_2]           NUMERIC(22, 6) NULL,
           [REBATE_PERCENT_3]           NUMERIC(22, 6) NULL,
           [CONTRACT_PRICE_1]           NUMERIC(22, 6) NULL,
           [CONTRACT_PRICE_2]           NUMERIC(22, 6) NULL,
           [CONTRACT_PRICE_3]           NUMERIC(22, 6) NULL,
           [INBOUND_STATUS]             CHAR(1) NULL,
           [RECORD_LOCK_STATUS]         BIT NOT NULL,
           [BATCH_ID]                   VARCHAR(50) NOT NULL,
           [SOURCE]                     VARCHAR(50) NULL,
           [CREATED_BY]                 INT NOT NULL,
           [CREATED_DATE]               DATETIME NOT NULL,
           [MODIFIED_BY]                INT NOT NULL,
           [MODIFIED_DATE]              DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_FORMULA_DETAILS_MASTER_FORMULA_DETAILS_MASTER_SID'
                     AND TABLE_NAME = 'FORMULA_DETAILS_MASTER')
  BEGIN
      ALTER TABLE FORMULA_DETAILS_MASTER
        ADD CONSTRAINT PK_FORMULA_DETAILS_MASTER_FORMULA_DETAILS_MASTER_SID PRIMARY KEY(FORMULA_DETAILS_MASTER_SID)
  END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.FORMULA_DETAILS_MASTER')
                      AND NAME = 'DF_FORMULA_DETAILS_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[FORMULA_DETAILS_MASTER]
        ADD CONSTRAINT [DF_FORMULA_DETAILS_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.FORMULA_DETAILS_MASTER')
                      AND NAME = 'DF_FORMULA_DETAILS_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[FORMULA_DETAILS_MASTER]
        ADD CONSTRAINT [DF_FORMULA_DETAILS_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.FORMULA_DETAILS_MASTER')
                      AND NAME = 'DF_FORMULA_DETAILS_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[FORMULA_DETAILS_MASTER]
        ADD CONSTRAINT [DF_FORMULA_DETAILS_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.FORMULA_DETAILS_MASTER')
                      AND NAME = 'DF_FORMULA_DETAILS_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[FORMULA_DETAILS_MASTER]
        ADD CONSTRAINT [DF_FORMULA_DETAILS_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.FORMULA_DETAILS_MASTER')
                      AND NAME = 'DF_FORMULA_DETAILS_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[FORMULA_DETAILS_MASTER]
        ADD CONSTRAINT [DF_FORMULA_DETAILS_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

---------------------UNIQUE_CONSTRAINT------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'FORMULA_DETAILS_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('FORMULA_DETAILS_MASTER')
                            AND NAME = 'UQ_FORMULA_DETAILS_MASTER_FORMULA_ID_COMPANY_ID_ITEM_ID_START_DATE')
        BEGIN
            ALTER TABLE FORMULA_DETAILS_MASTER
              ADD CONSTRAINT UQ_FORMULA_DETAILS_MASTER_FORMULA_ID_COMPANY_ID_ITEM_ID_START_DATE UNIQUE(FORMULA_ID, COMPANY_ID, ITEM_ID, START_DATE)
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'FORMULA_DETAILS_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------------HIST_FORMULA_DETAILS_MASTER--------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_FORMULA_DETAILS_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_FORMULA_DETAILS_MASTER]
        (
           [FORMULA_DETAILS_MASTER_SID] INT NOT NULL,
           [FORMULA_ID]                 BIGINT NOT NULL,
           [FORMULA_NO]                 VARCHAR(50) NOT NULL,
           [FORMULA_DESC]               VARCHAR(50) NOT NULL,
           [COMPANY_ID]                 VARCHAR(38) NOT NULL,
           [ITEM_ID]                    VARCHAR(38) NOT NULL,
           [START_DATE]                 DATETIME NOT NULL,
           [END_DATE]                   DATETIME NULL,
           [REBATE_PERCENT_1]           NUMERIC(22, 6) NULL,
           [REBATE_PERCENT_2]           NUMERIC(22, 6) NULL,
           [REBATE_PERCENT_3]           NUMERIC(22, 6) NULL,
           [CONTRACT_PRICE_1]           NUMERIC(22, 6) NULL,
           [CONTRACT_PRICE_2]           NUMERIC(22, 6) NULL,
           [CONTRACT_PRICE_3]           NUMERIC(22, 6) NULL,
           [INBOUND_STATUS]             CHAR(1) NULL,
           [RECORD_LOCK_STATUS]         BIT NOT NULL,
           [BATCH_ID]                   VARCHAR(50) NOT NULL,
           [SOURCE]                     VARCHAR(50) NULL,
           [CREATED_BY]                 INT NOT NULL,
           [CREATED_DATE]               DATETIME NOT NULL,
           [MODIFIED_BY]                INT NULL,
           [MODIFIED_DATE]              DATETIME NULL,
           [ACTION_FLAG]                CHAR(1) NOT NULL,
           [ACTION_DATE]                DATETIME NOT NULL
        )
  END

GO

-------------ALTER COLUMN MODIFIED_BY--------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_FORMULA_DETAILS_MASTER'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE HIST_FORMULA_DETAILS_MASTER
        ALTER COLUMN MODIFIED_BY INT NULL
  END

GO

-------------ALTER COLUMN MODIFIED_DATE--------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_FORMULA_DETAILS_MASTER'
                  AND COLUMN_NAME = 'MODIFIED_DATE'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE HIST_FORMULA_DETAILS_MASTER
        ALTER COLUMN MODIFIED_DATE DATETIME NULL
  END

GO

--------------------------DEFAULT_CONSTRAINTS---------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_FORMULA_DETAILS_MASTER')
                      AND NAME = 'DF_HIST_FORMULA_DETAILS_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_FORMULA_DETAILS_MASTER]
        ADD CONSTRAINT [DF_HIST_FORMULA_DETAILS_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_FORMULA_DETAILS_MASTER')
                      AND NAME = 'DF_HIST_FORMULA_DETAILS_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[HIST_FORMULA_DETAILS_MASTER]
        ADD CONSTRAINT [DF_HIST_FORMULA_DETAILS_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

--------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_FORMULA_DETAILS_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_FORMULA_DETAILS_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_FORMULA_DETAILS_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_FORMULA_DETAILS_MASTER_INS]
ON [DBO].[FORMULA_DETAILS_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_FORMULA_DETAILS_MASTER
                  (FORMULA_DETAILS_MASTER_SID,
                   FORMULA_ID,
                   FORMULA_NO,
                   FORMULA_DESC,
                   COMPANY_ID,
                   ITEM_ID,
                   START_DATE,
                   END_DATE,
                   REBATE_PERCENT_1,
                   REBATE_PERCENT_2,
                   REBATE_PERCENT_3,
                   CONTRACT_PRICE_1,
                   CONTRACT_PRICE_2,
                   CONTRACT_PRICE_3,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT FORMULA_DETAILS_MASTER_SID,
             FORMULA_ID,
             FORMULA_NO,
             FORMULA_DESC,
             COMPANY_ID,
             ITEM_ID,
             START_DATE,
             END_DATE,
             REBATE_PERCENT_1,
             REBATE_PERCENT_2,
             REBATE_PERCENT_3,
             CONTRACT_PRICE_1,
             CONTRACT_PRICE_2,
             CONTRACT_PRICE_3,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_FORMULA_DETAILS_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_FORMULA_DETAILS_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_FORMULA_DETAILS_MASTER_UPD]
ON [DBO].[FORMULA_DETAILS_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_FORMULA_DETAILS_MASTER
                  (FORMULA_DETAILS_MASTER_SID,
                   FORMULA_ID,
                   FORMULA_NO,
                   FORMULA_DESC,
                   COMPANY_ID,
                   ITEM_ID,
                   START_DATE,
                   END_DATE,
                   REBATE_PERCENT_1,
                   REBATE_PERCENT_2,
                   REBATE_PERCENT_3,
                   CONTRACT_PRICE_1,
                   CONTRACT_PRICE_2,
                   CONTRACT_PRICE_3,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT FORMULA_DETAILS_MASTER_SID,
             FORMULA_ID,
             FORMULA_NO,
             FORMULA_DESC,
             COMPANY_ID,
             ITEM_ID,
             START_DATE,
             END_DATE,
             REBATE_PERCENT_1,
             REBATE_PERCENT_2,
             REBATE_PERCENT_3,
             CONTRACT_PRICE_1,
             CONTRACT_PRICE_2,
             CONTRACT_PRICE_3,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_FORMULA_DETAILS_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_FORMULA_DETAILS_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_FORMULA_DETAILS_MASTER_DEL]
ON [DBO].[FORMULA_DETAILS_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_FORMULA_DETAILS_MASTER
                  (FORMULA_DETAILS_MASTER_SID,
                   FORMULA_ID,
                   FORMULA_NO,
                   FORMULA_DESC,
                   COMPANY_ID,
                   ITEM_ID,
                   START_DATE,
                   END_DATE,
                   REBATE_PERCENT_1,
                   REBATE_PERCENT_2,
                   REBATE_PERCENT_3,
                   CONTRACT_PRICE_1,
                   CONTRACT_PRICE_2,
                   CONTRACT_PRICE_3,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT FORMULA_DETAILS_MASTER_SID,
             FORMULA_ID,
             FORMULA_NO,
             FORMULA_DESC,
             COMPANY_ID,
             ITEM_ID,
             START_DATE,
             END_DATE,
             REBATE_PERCENT_1,
             REBATE_PERCENT_2,
             REBATE_PERCENT_3,
             CONTRACT_PRICE_1,
             CONTRACT_PRICE_2,
             CONTRACT_PRICE_3,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   DELETED
  END

GO

-----------------------GL_BALANCE_MASTER---------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'GL_BALANCE_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[GL_BALANCE_MASTER]
        (
           [GL_BALANCE_MASTER_SID] INT IDENTITY(1, 1) NOT NULL,
           [ACCOUNT_ID]            VARCHAR(100) NOT NULL,
           [ACCOUNT_NO]            VARCHAR(100) NOT NULL,
           [BALANCE]               NUMERIC(22, 6) NOT NULL,
           [UPLOAD_DATE]           DATETIME NOT NULL,
           [IS_ACTIVE]             BIT NULL,
           [INSERTED_DATE]         DATETIME NULL,
           [YEAR]                  SMALLINT NULL,
           [PERIOD]                VARCHAR(3) NULL,
           [CLOSE_INDICATOR]       VARCHAR(10) NULL,
           [INBOUND_STATUS]        CHAR(1) NULL,
           [RECORD_LOCK_STATUS]    BIT NOT NULL,
           [BATCH_ID]              VARCHAR(50) NOT NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [CREATED_BY]            INT NOT NULL,
           [CREATED_DATE]          DATETIME NOT NULL,
           [MODIFIED_BY]           INT NOT NULL,
           [MODIFIED_DATE]         DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_GL_BALANCE_MASTER_GL_BALANCE_MASTER_SID'
                     AND TABLE_NAME = 'GL_BALANCE_MASTER')
BEGIN
  ALTER TABLE GL_BALANCE_MASTER
    ADD CONSTRAINT PK_GL_BALANCE_MASTER_GL_BALANCE_MASTER_SID PRIMARY KEY(GL_BALANCE_MASTER_SID)
END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.GL_BALANCE_MASTER')
                      AND NAME = 'DF_GL_BALANCE_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[GL_BALANCE_MASTER]
        ADD CONSTRAINT [DF_GL_BALANCE_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.GL_BALANCE_MASTER')
                      AND NAME = 'DF_GL_BALANCE_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[GL_BALANCE_MASTER]
        ADD CONSTRAINT [DF_GL_BALANCE_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.GL_BALANCE_MASTER')
                      AND NAME = 'DF_GL_BALANCE_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[GL_BALANCE_MASTER]
        ADD CONSTRAINT [DF_GL_BALANCE_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.GL_BALANCE_MASTER')
                      AND NAME = 'DF_GL_BALANCE_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[GL_BALANCE_MASTER]
        ADD CONSTRAINT [DF_GL_BALANCE_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.GL_BALANCE_MASTER')
                      AND NAME = 'DF_GL_BALANCE_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[GL_BALANCE_MASTER]
        ADD CONSTRAINT [DF_GL_BALANCE_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

---------------------UNIQUE_CONSTRAINT----------------------
IF EXISTS (SELECT 1
           FROM   SYS.OBJECTS
           WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'GL_BALANCE_MASTER'
                  AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                  AND NAME = 'UQ_GL_BALANCE_MASTER_ACCOUNT_NO_CREATED_DATE_PERIOD')
  BEGIN
      ALTER TABLE DBO.GL_BALANCE_MASTER
        DROP CONSTRAINT UQ_GL_BALANCE_MASTER_ACCOUNT_NO_CREATED_DATE_PERIOD;
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.OBJECTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'GL_BALANCE_MASTER'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'UQ_GL_BALANCE_MASTER_ACCOUNT_NO_CREATED_DATE_PERIOD_YEAR')
  BEGIN
      ALTER TABLE DBO.GL_BALANCE_MASTER
        ADD CONSTRAINT UQ_GL_BALANCE_MASTER_ACCOUNT_NO_CREATED_DATE_PERIOD_YEAR UNIQUE(ACCOUNT_NO, CREATED_DATE, PERIOD, [YEAR]);
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'GL_BALANCE_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-----------------------HIST_GL_BALANCE_MASTER-------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_GL_BALANCE_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_GL_BALANCE_MASTER]
        (
           [GL_BALANCE_MASTER_SID] INT NOT NULL,
           [ACCOUNT_ID]            VARCHAR(100) NOT NULL,
           [ACCOUNT_NO]            VARCHAR(100) NOT NULL,
           [BALANCE]               NUMERIC(22, 6) NOT NULL,
           [UPLOAD_DATE]           DATETIME NOT NULL,
           [IS_ACTIVE]             BIT NULL,
           [INSERTED_DATE]         DATETIME NULL,
           [YEAR]                  SMALLINT NULL,
           [PERIOD]                VARCHAR(3) NULL,
           [CLOSE_INDICATOR]       VARCHAR(10) NULL,
           [INBOUND_STATUS]        CHAR(1) NULL,
           [RECORD_LOCK_STATUS]    BIT NOT NULL,
           [BATCH_ID]              VARCHAR(50) NOT NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [CREATED_BY]            INT NOT NULL,
           [CREATED_DATE]          DATETIME NOT NULL,
           [MODIFIED_BY]           INT NOT NULL,
           [MODIFIED_DATE]         DATETIME NOT NULL,
           [ACTION_FLAG]           CHAR(1) NOT NULL,
           [ACTION_DATE]           DATETIME NOT NULL
        )
  END

GO

--------------DEFAULT_CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_GL_BALANCE_MASTER')
                      AND NAME = 'DF_HIST_GL_BALANCE_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_GL_BALANCE_MASTER]
        ADD CONSTRAINT [DF_HIST_GL_BALANCE_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

-------------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_GL_BALANCE_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_GL_BALANCE_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_GL_BALANCE_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_GL_BALANCE_MASTER_INS]
ON [DBO].[GL_BALANCE_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_GL_BALANCE_MASTER
                  (GL_BALANCE_MASTER_SID,
                   ACCOUNT_ID,
                   ACCOUNT_NO,
                   BALANCE,
                   UPLOAD_DATE,
                   IS_ACTIVE,
                   INSERTED_DATE,
                   YEAR,
                   PERIOD,
                   CLOSE_INDICATOR,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT GL_BALANCE_MASTER_SID,
             ACCOUNT_ID,
             ACCOUNT_NO,
             BALANCE,
             UPLOAD_DATE,
             IS_ACTIVE,
             INSERTED_DATE,
             YEAR,
             PERIOD,
             CLOSE_INDICATOR,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_GL_BALANCE_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_GL_BALANCE_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_GL_BALANCE_MASTER_UPD]
ON [DBO].[GL_BALANCE_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_GL_BALANCE_MASTER
                  (GL_BALANCE_MASTER_SID,
                   ACCOUNT_ID,
                   ACCOUNT_NO,
                   BALANCE,
                   UPLOAD_DATE,
                   IS_ACTIVE,
                   INSERTED_DATE,
                   YEAR,
                   PERIOD,
                   CLOSE_INDICATOR,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT GL_BALANCE_MASTER_SID,
             ACCOUNT_ID,
             ACCOUNT_NO,
             BALANCE,
             UPLOAD_DATE,
             IS_ACTIVE,
             INSERTED_DATE,
             YEAR,
             PERIOD,
             CLOSE_INDICATOR,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_GL_BALANCE_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_GL_BALANCE_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_GL_BALANCE_MASTER_DEL]
ON [DBO].[GL_BALANCE_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_GL_BALANCE_MASTER
                  (GL_BALANCE_MASTER_SID,
                   ACCOUNT_ID,
                   ACCOUNT_NO,
                   BALANCE,
                   UPLOAD_DATE,
                   IS_ACTIVE,
                   INSERTED_DATE,
                   YEAR,
                   PERIOD,
                   CLOSE_INDICATOR,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT GL_BALANCE_MASTER_SID,
             ACCOUNT_ID,
             ACCOUNT_NO,
             BALANCE,
             UPLOAD_DATE,
             IS_ACTIVE,
             INSERTED_DATE,
             YEAR,
             PERIOD,
             CLOSE_INDICATOR,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   DELETED
  END

GO

-----------------------GL_COST_CENTER_MASTER------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'GL_COST_CENTER_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[GL_COST_CENTER_MASTER]
        (
           [GL_COST_CENTER_MASTER_SID] INT IDENTITY(1, 1) NOT NULL,
           [COMPANY_COST_CENTER]       VARCHAR(8) NOT NULL,
           [COMPANY_CODE]              VARCHAR(5) NULL,
           [NDC8]                      VARCHAR(25) NOT NULL,
           [UPLOAD_DATE]               DATETIME NOT NULL,
           [INBOUND_STATUS]            CHAR(1) NULL,
           [RECORD_LOCK_STATUS]        BIT NOT NULL,
           [BATCH_ID]                  VARCHAR(50) NOT NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL
        )
  END

GO

--------------------SIZE CHANGES-------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GL_COST_CENTER_MASTER'
                      AND COLUMN_NAME = 'NDC8'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR'
                      AND CHARACTER_MAXIMUM_LENGTH = 100)
BEGIN
  ALTER TABLE GL_COST_CENTER_MASTER
    ALTER COLUMN NDC8 VARCHAR(100) NOT NULL
END
GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_GL_COST_CENTER_MASTER_GL_COST_CENTER_MASTER_SID'
                     AND TABLE_NAME = 'GL_COST_CENTER_MASTER')
  BEGIN
      ALTER TABLE GL_COST_CENTER_MASTER
        ADD CONSTRAINT PK_GL_COST_CENTER_MASTER_GL_COST_CENTER_MASTER_SID PRIMARY KEY(GL_COST_CENTER_MASTER_SID)
  END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.GL_COST_CENTER_MASTER')
                      AND NAME = 'DF_GL_COST_CENTER_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[GL_COST_CENTER_MASTER]
        ADD CONSTRAINT [DF_GL_COST_CENTER_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.GL_COST_CENTER_MASTER')
                      AND NAME = 'DF_GL_COST_CENTER_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[GL_COST_CENTER_MASTER]
        ADD CONSTRAINT [DF_GL_COST_CENTER_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.GL_COST_CENTER_MASTER')
                      AND NAME = 'DF_GL_COST_CENTER_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[GL_COST_CENTER_MASTER]
        ADD CONSTRAINT [DF_GL_COST_CENTER_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.GL_COST_CENTER_MASTER')
                      AND NAME = 'DF_GL_COST_CENTER_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[GL_COST_CENTER_MASTER]
        ADD CONSTRAINT [DF_GL_COST_CENTER_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.GL_COST_CENTER_MASTER')
                      AND NAME = 'DF_GL_COST_CENTER_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[GL_COST_CENTER_MASTER]
        ADD CONSTRAINT [DF_GL_COST_CENTER_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

------------------UNIQUE_CONSTRAINT--------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'GL_COST_CENTER_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('GL_COST_CENTER_MASTER')
                            AND NAME = 'UQ_GL_COST_CENTER_MASTER_COMPANY_COST_CENTER_NDC8_COMPANY_CODE')
        BEGIN
            ALTER TABLE GL_COST_CENTER_MASTER
              ADD CONSTRAINT UQ_GL_COST_CENTER_MASTER_COMPANY_COST_CENTER_NDC8_COMPANY_CODE UNIQUE(COMPANY_COST_CENTER, NDC8, COMPANY_CODE)
        END
  END

GO

-----------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'GL_COST_CENTER_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------------------------- HIST_GL_COST_CENTER_MASTER ---------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_GL_COST_CENTER_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_GL_COST_CENTER_MASTER]
        (
           [GL_COST_CENTER_MASTER_SID] INT NOT NULL,
           [COMPANY_COST_CENTER]       VARCHAR(8) NOT NULL,
           [COMPANY_CODE]              VARCHAR(5) NULL,
           [NDC8]                      VARCHAR(25) NOT NULL,
           [UPLOAD_DATE]               DATETIME NOT NULL,
           [INBOUND_STATUS]            CHAR(1) NULL,
           [RECORD_LOCK_STATUS]        BIT NOT NULL,
           [BATCH_ID]                  VARCHAR(50) NOT NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL,
           [ACTION_FLAG]               CHAR(1) NOT NULL,
           [ACTION_DATE]               DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_GL_COST_CENTER_MASTER')
                      AND NAME = 'DF_HIST_GL_COST_CENTER_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_GL_COST_CENTER_MASTER]
        ADD CONSTRAINT [DF_HIST_GL_COST_CENTER_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

-----------------------SIZE CHANGES---------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_GL_COST_CENTER_MASTER'
                      AND COLUMN_NAME = 'NDC8'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR'
                      AND CHARACTER_MAXIMUM_LENGTH = 100)
BEGIN
  ALTER TABLE HIST_GL_COST_CENTER_MASTER
    ALTER COLUMN NDC8 VARCHAR(100) NOT NULL
END

GO

-----------------------------------------------------------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_GL_COST_CENTER_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_GL_COST_CENTER_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_GL_COST_CENTER_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_GL_COST_CENTER_MASTER_INS]
ON [DBO].[GL_COST_CENTER_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_GL_COST_CENTER_MASTER
                  (GL_COST_CENTER_MASTER_SID,
                   COMPANY_COST_CENTER,
                   COMPANY_CODE,
                   NDC8,
                   UPLOAD_DATE,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT GL_COST_CENTER_MASTER_SID,
             COMPANY_COST_CENTER,
             COMPANY_CODE,
             NDC8,
             UPLOAD_DATE,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_GL_COST_CENTER_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_GL_COST_CENTER_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_GL_COST_CENTER_MASTER_UPD]
ON [DBO].[GL_COST_CENTER_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_GL_COST_CENTER_MASTER
                  (GL_COST_CENTER_MASTER_SID,
                   COMPANY_COST_CENTER,
                   COMPANY_CODE,
                   NDC8,
                   UPLOAD_DATE,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT GL_COST_CENTER_MASTER_SID,
             COMPANY_COST_CENTER,
             COMPANY_CODE,
             NDC8,
             UPLOAD_DATE,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_GL_COST_CENTER_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_GL_COST_CENTER_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_GL_COST_CENTER_MASTER_DEL]
ON [DBO].[GL_COST_CENTER_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_GL_COST_CENTER_MASTER
                  (GL_COST_CENTER_MASTER_SID,
                   COMPANY_COST_CENTER,
                   COMPANY_CODE,
                   NDC8,
                   UPLOAD_DATE,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT GL_COST_CENTER_MASTER_SID,
             COMPANY_COST_CENTER,
             COMPANY_CODE,
             NDC8,
             UPLOAD_DATE,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   DELETED
  END

GO

-------------------ITEM_HIERARCHY_DEF_MASTER--------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_HIERARCHY_DEF_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ITEM_HIERARCHY_DEF_MASTER]
        (
           [ITEM_HIERARCHY_DEF_MASTER_SID] INT IDENTITY(1, 1) NOT NULL,
           [MEMBER]                        VARCHAR(100) NULL,
           [ALIAS]                         VARCHAR(100) NULL,
           [BPI_LVL]                       INT NULL,
           [INBOUND_STATUS]                CHAR(1) NULL,
           [RECORD_LOCK_STATUS]            BIT NOT NULL,
           [BATCH_ID]                      VARCHAR(50) NOT NULL,
           [SOURCE]                        VARCHAR(50) NULL,
           [CREATED_BY]                    INT NOT NULL,
           [CREATED_DATE]                  DATETIME NOT NULL,
           [MODIFIED_BY]                   INT NOT NULL,
           [MODIFIED_DATE]                 DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_ITEM_HIERARCHY_DEF_MASTER_ITEM_HIERARCHY_DEF_MASTER_SID'
                     AND TABLE_NAME = 'ITEM_HIERARCHY_DEF_MASTER')
  BEGIN
      ALTER TABLE ITEM_HIERARCHY_DEF_MASTER
        ADD CONSTRAINT PK_ITEM_HIERARCHY_DEF_MASTER_ITEM_HIERARCHY_DEF_MASTER_SID PRIMARY KEY(ITEM_HIERARCHY_DEF_MASTER_SID)
  END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_HIERARCHY_DEF_MASTER')
                      AND NAME = 'DF_ITEM_HIERARCHY_DEF_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[ITEM_HIERARCHY_DEF_MASTER]
        ADD CONSTRAINT [DF_ITEM_HIERARCHY_DEF_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_HIERARCHY_DEF_MASTER')
                      AND NAME = 'DF_ITEM_HIERARCHY_DEF_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[ITEM_HIERARCHY_DEF_MASTER]
        ADD CONSTRAINT [DF_ITEM_HIERARCHY_DEF_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_HIERARCHY_DEF_MASTER')
                      AND NAME = 'DF_ITEM_HIERARCHY_DEF_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ITEM_HIERARCHY_DEF_MASTER]
        ADD CONSTRAINT [DF_ITEM_HIERARCHY_DEF_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_HIERARCHY_DEF_MASTER')
                      AND NAME = 'DF_ITEM_HIERARCHY_DEF_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[ITEM_HIERARCHY_DEF_MASTER]
        ADD CONSTRAINT [DF_ITEM_HIERARCHY_DEF_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_HIERARCHY_DEF_MASTER')
                      AND NAME = 'DF_ITEM_HIERARCHY_DEF_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ITEM_HIERARCHY_DEF_MASTER]
        ADD CONSTRAINT [DF_ITEM_HIERARCHY_DEF_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

------------------UNIQUE_CONSTRAINT----------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'ITEM_HIERARCHY_DEF_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('ITEM_HIERARCHY_DEF_MASTER')
                            AND NAME = 'UQ_ITEM_HIERARCHY_DEF_MASTER_MEMBER')
        BEGIN
            ALTER TABLE ITEM_HIERARCHY_DEF_MASTER
              ADD CONSTRAINT UQ_ITEM_HIERARCHY_DEF_MASTER_MEMBER UNIQUE(MEMBER)
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ITEM_HIERARCHY_DEF_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

----------------HIST_ITEM_HIERARCHY_DEF_MASTER-----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ITEM_HIERARCHY_DEF_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_ITEM_HIERARCHY_DEF_MASTER]
        (
           [ITEM_HIERARCHY_DEF_MASTER_SID] INT NOT NULL,
           [MEMBER]                        VARCHAR(100) NULL,
           [ALIAS]                         VARCHAR(100) NULL,
           [BPI_LVL]                       INT NULL,
           [INBOUND_STATUS]                CHAR(1) NULL,
           [RECORD_LOCK_STATUS]            BIT NOT NULL,
           [BATCH_ID]                      VARCHAR(50) NOT NULL,
           [SOURCE]                        VARCHAR(50) NULL,
           [CREATED_BY]                    INT NOT NULL,
           [CREATED_DATE]                  DATETIME NOT NULL,
           [MODIFIED_BY]                   INT NOT NULL,
           [MODIFIED_DATE]                 DATETIME NOT NULL,
           [ACTION_FLAG]                   CHAR(1) NOT NULL,
           [ACTION_DATE]                   DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_ITEM_HIERARCHY_DEF_MASTER')
                      AND NAME = 'DF_HIST_ITEM_HIERARCHY_DEF_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_ITEM_HIERARCHY_DEF_MASTER]
        ADD CONSTRAINT [DF_HIST_ITEM_HIERARCHY_DEF_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ITEM_HIERARCHY_DEF_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_HIER_DEF_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_ITEM_HIER_DEF_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_HIER_DEF_MASTER_INS]
ON [DBO].[ITEM_HIERARCHY_DEF_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_HIERARCHY_DEF_MASTER
                  (ITEM_HIERARCHY_DEF_MASTER_SID,
                   MEMBER,
                   ALIAS,
                   BPI_LVL,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT ITEM_HIERARCHY_DEF_MASTER_SID,
             MEMBER,
             ALIAS,
             BPI_LVL,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_HIER_DEF_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_ITEM_HIER_DEF_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_HIER_DEF_MASTER_UPD]
ON [DBO].[ITEM_HIERARCHY_DEF_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_HIERARCHY_DEF_MASTER
                  (ITEM_HIERARCHY_DEF_MASTER_SID,
                   MEMBER,
                   ALIAS,
                   BPI_LVL,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT ITEM_HIERARCHY_DEF_MASTER_SID,
             MEMBER,
             ALIAS,
             BPI_LVL,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_HIER_DEF_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_ITEM_HIER_DEF_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_HIER_DEF_MASTER_DEL]
ON [DBO].[ITEM_HIERARCHY_DEF_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_HIERARCHY_DEF_MASTER
                  (ITEM_HIERARCHY_DEF_MASTER_SID,
                   MEMBER,
                   ALIAS,
                   BPI_LVL,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT ITEM_HIERARCHY_DEF_MASTER_SID,
             MEMBER,
             ALIAS,
             BPI_LVL,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   DELETED
  END

GO

--------------------ITEM_HIERARCHY_MASTER----------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_HIERARCHY_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ITEM_HIERARCHY_MASTER]
        (
           [ITEM_HIERARCHY_MASTER_SID] INT IDENTITY(1, 1) NOT NULL,
           [LEVEL0_TAG]                VARCHAR(100) NULL,
           [LEVEL0]                    VARCHAR(100) NULL,
           [LEVEL0_ALIAS]              VARCHAR(100) NULL,
           [LEVEL1]                    VARCHAR(100) NULL,
           [LEVEL1_ALIAS]              VARCHAR(100) NULL,
           [LEVEL2]                    VARCHAR(100) NULL,
           [LEVEL2_ALIAS]              VARCHAR(100) NULL,
           [LEVEL3]                    VARCHAR(100) NULL,
           [LEVEL3_ALIAS]              VARCHAR(100) NULL,
           [LEVEL4]                    VARCHAR(100) NULL,
           [LEVEL4_ALIAS]              VARCHAR(100) NULL,
           [LEVEL5]                    VARCHAR(100) NULL,
           [LEVEL5_ALIAS]              VARCHAR(100) NULL,
           [LEVEL6]                    VARCHAR(100) NULL,
           [LEVEL6_ALIAS]              VARCHAR(100) NULL,
           [LEVEL7]                    VARCHAR(100) NULL,
           [LEVEL7_ALIAS]              VARCHAR(100) NULL,
           [LEVEL8]                    VARCHAR(100) NULL,
           [LEVEL8_ALIAS]              VARCHAR(100) NULL,
           [LEVEL9]                    VARCHAR(100) NULL,
           [LEVEL9_ALIAS]              VARCHAR(100) NULL,
           [LEVEL10]                   VARCHAR(100) NULL,
           [LEVEL10_ALIAS]             VARCHAR(100) NULL,
           [LEVEL11]                   VARCHAR(100) NULL,
           [LEVEL11_ALIAS]             VARCHAR(100) NULL,
           [LEVEL12]                   VARCHAR(100) NULL,
           [LEVEL12_ALIAS]             VARCHAR(100) NULL,
           [LEVEL13]                   VARCHAR(100) NULL,
           [LEVEL13_ALIAS]             VARCHAR(100) NULL,
           [LEVEL14]                   VARCHAR(100) NULL,
           [LEVEL14_ALIAS]             VARCHAR(100) NULL,
           [LEVEL15]                   VARCHAR(100) NULL,
           [LEVEL15_ALIAS]             VARCHAR(100) NULL,
           [LEVEL16]                   VARCHAR(100) NULL,
           [LEVEL16_ALIAS]             VARCHAR(100) NULL,
           [LEVEL17]                   VARCHAR(100) NULL,
           [LEVEL17_ALIAS]             VARCHAR(100) NULL,
           [LEVEL18]                   VARCHAR(100) NULL,
           [LEVEL18_ALIAS]             VARCHAR(100) NULL,
           [LEVEL19]                   VARCHAR(100) NULL,
           [LEVEL19_ALIAS]             VARCHAR(100) NULL,
           [LEVEL20]                   VARCHAR(100) NULL,
           [LEVEL20_ALIAS]             VARCHAR(100) NULL,
           [GTN_COMPANY]               VARCHAR(100) NOT NULL,
           [GTN_BRAND]                 VARCHAR(100) NULL,
           [GTN_THERAPEUTIC_CLASS]     VARCHAR(100) NULL,
           [INBOUND_STATUS]            CHAR(1) NULL,
           [RECORD_LOCK_STATUS]        BIT NOT NULL,
           [BATCH_ID]                  VARCHAR(50) NOT NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  OBJECT_NAME(OBJECT_ID) = N'ITEM_HIERARCHY_MASTER'
                      AND OBJECT_SCHEMA_NAME(OBJECT_ID) = N'DBO'
                      AND [NAME] = N'PK_ITEM_HIERARCHY_MASTER_ITEM_HIERARCHY_MASTER_SID')
  BEGIN
      ALTER TABLE [DBO].[ITEM_HIERARCHY_MASTER]
        ADD CONSTRAINT [PK_ITEM_HIERARCHY_MASTER_ITEM_HIERARCHY_MASTER_SID] PRIMARY KEY ( [ITEM_HIERARCHY_MASTER_SID])
  END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_HIERARCHY_MASTER')
                      AND NAME = 'DF_ITEM_HIERARCHY_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[ITEM_HIERARCHY_MASTER]
        ADD CONSTRAINT [DF_ITEM_HIERARCHY_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_HIERARCHY_MASTER')
                      AND NAME = 'DF_ITEM_HIERARCHY_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[ITEM_HIERARCHY_MASTER]
        ADD CONSTRAINT [DF_ITEM_HIERARCHY_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_HIERARCHY_MASTER')
                      AND NAME = 'DF_ITEM_HIERARCHY_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ITEM_HIERARCHY_MASTER]
        ADD CONSTRAINT [DF_ITEM_HIERARCHY_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_HIERARCHY_MASTER')
                      AND NAME = 'DF_ITEM_HIERARCHY_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[ITEM_HIERARCHY_MASTER]
        ADD CONSTRAINT [DF_ITEM_HIERARCHY_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_HIERARCHY_MASTER')
                      AND NAME = 'DF_ITEM_HIERARCHY_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ITEM_HIERARCHY_MASTER]
        ADD CONSTRAINT [DF_ITEM_HIERARCHY_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

---------------------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'ITEM_HIERARCHY_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('ITEM_HIERARCHY_MASTER')
                            AND NAME = 'UQ_ITEM_HIERARCHY_MASTER_LEVEL0_GTN_COMPANY')
        BEGIN
            ALTER TABLE ITEM_HIERARCHY_MASTER
              ADD CONSTRAINT UQ_ITEM_HIERARCHY_MASTER_LEVEL0_GTN_COMPANY UNIQUE(LEVEL0, GTN_COMPANY)
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ITEM_HIERARCHY_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

---------------------HIST_ITEM_HIERARCHY_MASTER----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ITEM_HIERARCHY_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_ITEM_HIERARCHY_MASTER]
        (
           [ITEM_HIERARCHY_MASTER_SID] INT NOT NULL,
           [LEVEL0_TAG]                VARCHAR(100) NULL,
           [LEVEL0]                    VARCHAR(100) NULL,
           [LEVEL0_ALIAS]              VARCHAR(100) NULL,
           [LEVEL1]                    VARCHAR(100) NULL,
           [LEVEL1_ALIAS]              VARCHAR(100) NULL,
           [LEVEL2]                    VARCHAR(100) NULL,
           [LEVEL2_ALIAS]              VARCHAR(100) NULL,
           [LEVEL3]                    VARCHAR(100) NULL,
           [LEVEL3_ALIAS]              VARCHAR(100) NULL,
           [LEVEL4]                    VARCHAR(100) NULL,
           [LEVEL4_ALIAS]              VARCHAR(100) NULL,
           [LEVEL5]                    VARCHAR(100) NULL,
           [LEVEL5_ALIAS]              VARCHAR(100) NULL,
           [LEVEL6]                    VARCHAR(100) NULL,
           [LEVEL6_ALIAS]              VARCHAR(100) NULL,
           [LEVEL7]                    VARCHAR(100) NULL,
           [LEVEL7_ALIAS]              VARCHAR(100) NULL,
           [LEVEL8]                    VARCHAR(100) NULL,
           [LEVEL8_ALIAS]              VARCHAR(100) NULL,
           [LEVEL9]                    VARCHAR(100) NULL,
           [LEVEL9_ALIAS]              VARCHAR(100) NULL,
           [LEVEL10]                   VARCHAR(100) NULL,
           [LEVEL10_ALIAS]             VARCHAR(100) NULL,
           [LEVEL11]                   VARCHAR(100) NULL,
           [LEVEL11_ALIAS]             VARCHAR(100) NULL,
           [LEVEL12]                   VARCHAR(100) NULL,
           [LEVEL12_ALIAS]             VARCHAR(100) NULL,
           [LEVEL13]                   VARCHAR(100) NULL,
           [LEVEL13_ALIAS]             VARCHAR(100) NULL,
           [LEVEL14]                   VARCHAR(100) NULL,
           [LEVEL14_ALIAS]             VARCHAR(100) NULL,
           [LEVEL15]                   VARCHAR(100) NULL,
           [LEVEL15_ALIAS]             VARCHAR(100) NULL,
           [LEVEL16]                   VARCHAR(100) NULL,
           [LEVEL16_ALIAS]             VARCHAR(100) NULL,
           [LEVEL17]                   VARCHAR(100) NULL,
           [LEVEL17_ALIAS]             VARCHAR(100) NULL,
           [LEVEL18]                   VARCHAR(100) NULL,
           [LEVEL18_ALIAS]             VARCHAR(100) NULL,
           [LEVEL19]                   VARCHAR(100) NULL,
           [LEVEL19_ALIAS]             VARCHAR(100) NULL,
           [LEVEL20]                   VARCHAR(100) NULL,
           [LEVEL20_ALIAS]             VARCHAR(100) NULL,
           [GTN_COMPANY]               VARCHAR(100) NOT NULL,
           [GTN_BRAND]                 VARCHAR(100) NULL,
           [GTN_THERAPEUTIC_CLASS]     VARCHAR(100) NULL,
           [INBOUND_STATUS]            CHAR(1) NULL,
           [RECORD_LOCK_STATUS]        BIT NOT NULL,
           [BATCH_ID]                  VARCHAR(50) NOT NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL,
           [ACTION_FLAG]               CHAR(1) NOT NULL,
           [ACTION_DATE]               DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_ITEM_HIERARCHY_MASTER')
                      AND NAME = 'DF_HIST_ITEM_HIERARCHY_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_ITEM_HIERARCHY_MASTER]
        ADD CONSTRAINT [DF_HIST_ITEM_HIERARCHY_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ITEM_HIERARCHY_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_HIERARCHY_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_ITEM_HIERARCHY_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_HIERARCHY_MASTER_INS]
ON [DBO].[ITEM_HIERARCHY_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_HIERARCHY_MASTER
                  (ITEM_HIERARCHY_MASTER_SID,
                   LEVEL0_TAG,
                   LEVEL0,
                   LEVEL0_ALIAS,
                   LEVEL1,
                   LEVEL1_ALIAS,
                   LEVEL2,
                   LEVEL2_ALIAS,
                   LEVEL3,
                   LEVEL3_ALIAS,
                   LEVEL4,
                   LEVEL4_ALIAS,
                   LEVEL5,
                   LEVEL5_ALIAS,
                   LEVEL6,
                   LEVEL6_ALIAS,
                   LEVEL7,
                   LEVEL7_ALIAS,
                   LEVEL8,
                   LEVEL8_ALIAS,
                   LEVEL9,
                   LEVEL9_ALIAS,
                   LEVEL10,
                   LEVEL10_ALIAS,
                   LEVEL11,
                   LEVEL11_ALIAS,
                   LEVEL12,
                   LEVEL12_ALIAS,
                   LEVEL13,
                   LEVEL13_ALIAS,
                   LEVEL14,
                   LEVEL14_ALIAS,
                   LEVEL15,
                   LEVEL15_ALIAS,
                   LEVEL16,
                   LEVEL16_ALIAS,
                   LEVEL17,
                   LEVEL17_ALIAS,
                   LEVEL18,
                   LEVEL18_ALIAS,
                   LEVEL19,
                   LEVEL19_ALIAS,
                   LEVEL20,
                   LEVEL20_ALIAS,
                   GTN_COMPANY,
                   GTN_BRAND,
                   GTN_THERAPEUTIC_CLASS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   ACTION_FLAG,
                   INBOUND_STATUS,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT ITEM_HIERARCHY_MASTER_SID,
             LEVEL0_TAG,
             LEVEL0,
             LEVEL0_ALIAS,
             LEVEL1,
             LEVEL1_ALIAS,
             LEVEL2,
             LEVEL2_ALIAS,
             LEVEL3,
             LEVEL3_ALIAS,
             LEVEL4,
             LEVEL4_ALIAS,
             LEVEL5,
             LEVEL5_ALIAS,
             LEVEL6,
             LEVEL6_ALIAS,
             LEVEL7,
             LEVEL7_ALIAS,
             LEVEL8,
             LEVEL8_ALIAS,
             LEVEL9,
             LEVEL9_ALIAS,
             LEVEL10,
             LEVEL10_ALIAS,
             LEVEL11,
             LEVEL11_ALIAS,
             LEVEL12,
             LEVEL12_ALIAS,
             LEVEL13,
             LEVEL13_ALIAS,
             LEVEL14,
             LEVEL14_ALIAS,
             LEVEL15,
             LEVEL15_ALIAS,
             LEVEL16,
             LEVEL16_ALIAS,
             LEVEL17,
             LEVEL17_ALIAS,
             LEVEL18,
             LEVEL18_ALIAS,
             LEVEL19,
             LEVEL19_ALIAS,
             LEVEL20,
             LEVEL20_ALIAS,
             GTN_COMPANY,
             GTN_BRAND,
             GTN_THERAPEUTIC_CLASS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             'A',
             INBOUND_STATUS,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_HIERARCHY_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_ITEM_HIERARCHY_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_HIERARCHY_MASTER_UPD]
ON [DBO].[ITEM_HIERARCHY_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_HIERARCHY_MASTER
                  (ITEM_HIERARCHY_MASTER_SID,
                   LEVEL0_TAG,
                   LEVEL0,
                   LEVEL0_ALIAS,
                   LEVEL1,
                   LEVEL1_ALIAS,
                   LEVEL2,
                   LEVEL2_ALIAS,
                   LEVEL3,
                   LEVEL3_ALIAS,
                   LEVEL4,
                   LEVEL4_ALIAS,
                   LEVEL5,
                   LEVEL5_ALIAS,
                   LEVEL6,
                   LEVEL6_ALIAS,
                   LEVEL7,
                   LEVEL7_ALIAS,
                   LEVEL8,
                   LEVEL8_ALIAS,
                   LEVEL9,
                   LEVEL9_ALIAS,
                   LEVEL10,
                   LEVEL10_ALIAS,
                   LEVEL11,
                   LEVEL11_ALIAS,
                   LEVEL12,
                   LEVEL12_ALIAS,
                   LEVEL13,
                   LEVEL13_ALIAS,
                   LEVEL14,
                   LEVEL14_ALIAS,
                   LEVEL15,
                   LEVEL15_ALIAS,
                   LEVEL16,
                   LEVEL16_ALIAS,
                   LEVEL17,
                   LEVEL17_ALIAS,
                   LEVEL18,
                   LEVEL18_ALIAS,
                   LEVEL19,
                   LEVEL19_ALIAS,
                   LEVEL20,
                   LEVEL20_ALIAS,
                   GTN_COMPANY,
                   GTN_BRAND,
                   GTN_THERAPEUTIC_CLASS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   ACTION_FLAG,
                   INBOUND_STATUS,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT ITEM_HIERARCHY_MASTER_SID,
             LEVEL0_TAG,
             LEVEL0,
             LEVEL0_ALIAS,
             LEVEL1,
             LEVEL1_ALIAS,
             LEVEL2,
             LEVEL2_ALIAS,
             LEVEL3,
             LEVEL3_ALIAS,
             LEVEL4,
             LEVEL4_ALIAS,
             LEVEL5,
             LEVEL5_ALIAS,
             LEVEL6,
             LEVEL6_ALIAS,
             LEVEL7,
             LEVEL7_ALIAS,
             LEVEL8,
             LEVEL8_ALIAS,
             LEVEL9,
             LEVEL9_ALIAS,
             LEVEL10,
             LEVEL10_ALIAS,
             LEVEL11,
             LEVEL11_ALIAS,
             LEVEL12,
             LEVEL12_ALIAS,
             LEVEL13,
             LEVEL13_ALIAS,
             LEVEL14,
             LEVEL14_ALIAS,
             LEVEL15,
             LEVEL15_ALIAS,
             LEVEL16,
             LEVEL16_ALIAS,
             LEVEL17,
             LEVEL17_ALIAS,
             LEVEL18,
             LEVEL18_ALIAS,
             LEVEL19,
             LEVEL19_ALIAS,
             LEVEL20,
             LEVEL20_ALIAS,
             GTN_COMPANY,
             GTN_BRAND,
             GTN_THERAPEUTIC_CLASS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             'C',
             INBOUND_STATUS,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_HIERARCHY_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_ITEM_HIERARCHY_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_HIERARCHY_MASTER_DEL]
ON [DBO].[ITEM_HIERARCHY_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_HIERARCHY_MASTER
                  (ITEM_HIERARCHY_MASTER_SID,
                   LEVEL0_TAG,
                   LEVEL0,
                   LEVEL0_ALIAS,
                   LEVEL1,
                   LEVEL1_ALIAS,
                   LEVEL2,
                   LEVEL2_ALIAS,
                   LEVEL3,
                   LEVEL3_ALIAS,
                   LEVEL4,
                   LEVEL4_ALIAS,
                   LEVEL5,
                   LEVEL5_ALIAS,
                   LEVEL6,
                   LEVEL6_ALIAS,
                   LEVEL7,
                   LEVEL7_ALIAS,
                   LEVEL8,
                   LEVEL8_ALIAS,
                   LEVEL9,
                   LEVEL9_ALIAS,
                   LEVEL10,
                   LEVEL10_ALIAS,
                   LEVEL11,
                   LEVEL11_ALIAS,
                   LEVEL12,
                   LEVEL12_ALIAS,
                   LEVEL13,
                   LEVEL13_ALIAS,
                   LEVEL14,
                   LEVEL14_ALIAS,
                   LEVEL15,
                   LEVEL15_ALIAS,
                   LEVEL16,
                   LEVEL16_ALIAS,
                   LEVEL17,
                   LEVEL17_ALIAS,
                   LEVEL18,
                   LEVEL18_ALIAS,
                   LEVEL19,
                   LEVEL19_ALIAS,
                   LEVEL20,
                   LEVEL20_ALIAS,
                   GTN_COMPANY,
                   GTN_BRAND,
                   GTN_THERAPEUTIC_CLASS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   ACTION_FLAG,
                   INBOUND_STATUS,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT ITEM_HIERARCHY_MASTER_SID,
             LEVEL0_TAG,
             LEVEL0,
             LEVEL0_ALIAS,
             LEVEL1,
             LEVEL1_ALIAS,
             LEVEL2,
             LEVEL2_ALIAS,
             LEVEL3,
             LEVEL3_ALIAS,
             LEVEL4,
             LEVEL4_ALIAS,
             LEVEL5,
             LEVEL5_ALIAS,
             LEVEL6,
             LEVEL6_ALIAS,
             LEVEL7,
             LEVEL7_ALIAS,
             LEVEL8,
             LEVEL8_ALIAS,
             LEVEL9,
             LEVEL9_ALIAS,
             LEVEL10,
             LEVEL10_ALIAS,
             LEVEL11,
             LEVEL11_ALIAS,
             LEVEL12,
             LEVEL12_ALIAS,
             LEVEL13,
             LEVEL13_ALIAS,
             LEVEL14,
             LEVEL14_ALIAS,
             LEVEL15,
             LEVEL15_ALIAS,
             LEVEL16,
             LEVEL16_ALIAS,
             LEVEL17,
             LEVEL17_ALIAS,
             LEVEL18,
             LEVEL18_ALIAS,
             LEVEL19,
             LEVEL19_ALIAS,
             LEVEL20,
             LEVEL20_ALIAS,
             GTN_COMPANY,
             GTN_BRAND,
             GTN_THERAPEUTIC_CLASS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             'D',
             INBOUND_STATUS,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   DELETED
  END

GO

----------------------LOT_MASTER-------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'LOT_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[LOT_MASTER]
        (
           [LOT_MASTER_SID]      INT IDENTITY(1, 1) NOT NULL,
           [ITEM_ID]             VARCHAR(50) NOT NULL,
           [LOT_NO]              VARCHAR(30) NOT NULL,
           [LAST_LOT_SOLD_DATE]  DATETIME NOT NULL,
           [LOT_EXPIRATION_DATE] DATETIME NULL,
           [INBOUND_STATUS]      CHAR(1) NULL,
           [RECORD_LOCK_STATUS]  BIT NOT NULL,
           [BATCH_ID]            VARCHAR(50) NOT NULL,
           [SOURCE]              VARCHAR(50) NULL,
           [CREATED_BY]          INT NOT NULL,
           [CREATED_DATE]        DATETIME NOT NULL,
           [MODIFIED_BY]         INT NOT NULL,
           [MODIFIED_DATE]       DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_LOT_MASTER_LOT_MASTER_SID'
                     AND TABLE_NAME = 'LOT_MASTER')
  BEGIN
      ALTER TABLE LOT_MASTER
        ADD CONSTRAINT PK_LOT_MASTER_LOT_MASTER_SID PRIMARY KEY(LOT_MASTER_SID)
  END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.LOT_MASTER')
                      AND NAME = 'DF_LOT_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[LOT_MASTER]
        ADD CONSTRAINT [DF_LOT_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.LOT_MASTER')
                      AND NAME = 'DF_LOT_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[LOT_MASTER]
        ADD CONSTRAINT [DF_LOT_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.LOT_MASTER')
                      AND NAME = 'DF_LOT_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[LOT_MASTER]
        ADD CONSTRAINT [DF_LOT_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.LOT_MASTER')
                      AND NAME = 'DF_LOT_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[LOT_MASTER]
        ADD CONSTRAINT [DF_LOT_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.LOT_MASTER')
                      AND NAME = 'DF_LOT_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[LOT_MASTER]
        ADD CONSTRAINT [DF_LOT_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

----------------UNIQUE_CONSTRAINT------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'LOT_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('LOT_MASTER')
                            AND NAME = 'UQ_LOT_MASTER_ITEM_ID_LOT_NO')
        BEGIN
            ALTER TABLE LOT_MASTER
              ADD CONSTRAINT UQ_LOT_MASTER_ITEM_ID_LOT_NO UNIQUE(ITEM_ID, LOT_NO)
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'LOT_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_LOT_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_LOT_MASTER]
        (
           [LOT_MASTER_SID]      INT NOT NULL,
           [ITEM_ID]             VARCHAR(50) NOT NULL,
           [LOT_NO]              VARCHAR(30) NOT NULL,
           [LAST_LOT_SOLD_DATE]  DATETIME NOT NULL,
           [LOT_EXPIRATION_DATE] DATETIME NULL,
           [INBOUND_STATUS]      CHAR(1) NULL,
           [RECORD_LOCK_STATUS]  BIT NOT NULL,
           [BATCH_ID]            VARCHAR(50) NOT NULL,
           [SOURCE]              VARCHAR(50) NULL,
           [CREATED_BY]          INT NOT NULL,
           [CREATED_DATE]        DATETIME NOT NULL,
           [MODIFIED_BY]         INT NOT NULL,
           [MODIFIED_DATE]       DATETIME NOT NULL,
           [ACTION_FLAG]         CHAR(1) NOT NULL,
           [ACTION_DATE]         DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_LOT_MASTER')
                      AND NAME = 'DF_HIST_LOT_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_LOT_MASTER]
        ADD CONSTRAINT [DF_HIST_LOT_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_LOT_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_LOT_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_LOT_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_LOT_MASTER_INS]
ON [DBO].[LOT_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_LOT_MASTER
                  (LOT_MASTER_SID,
                   ITEM_ID,
                   LOT_NO,
                   LAST_LOT_SOLD_DATE,
                   LOT_EXPIRATION_DATE,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT LOT_MASTER_SID,
             ITEM_ID,
             LOT_NO,
             LAST_LOT_SOLD_DATE,
             LOT_EXPIRATION_DATE,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_LOT_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_LOT_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_LOT_MASTER_UPD]
ON [DBO].[LOT_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_LOT_MASTER
                  (LOT_MASTER_SID,
                   ITEM_ID,
                   LOT_NO,
                   LAST_LOT_SOLD_DATE,
                   LOT_EXPIRATION_DATE,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT LOT_MASTER_SID,
             ITEM_ID,
             LOT_NO,
             LAST_LOT_SOLD_DATE,
             LOT_EXPIRATION_DATE,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_LOT_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_LOT_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_LOT_MASTER_DEL]
ON [DBO].[LOT_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_LOT_MASTER
                  (LOT_MASTER_SID,
                   ITEM_ID,
                   LOT_NO,
                   LAST_LOT_SOLD_DATE,
                   LOT_EXPIRATION_DATE,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT LOT_MASTER_SID,
             ITEM_ID,
             LOT_NO,
             LAST_LOT_SOLD_DATE,
             LOT_EXPIRATION_DATE,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   DELETED
  END

GO

--------------------MASTER_DATA_ATTRIBUTE--------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'MASTER_DATA_ATTRIBUTE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[MASTER_DATA_ATTRIBUTE]
        (
           [MASTER_DATA_ATTRIBUTE_SID] INT IDENTITY(1, 1) NOT NULL,
           [MASTER_TYPE]               VARCHAR(100) NOT NULL,
           [MASTER_ID]                 VARCHAR(100) NOT NULL,
           [MASTER_ATTRIBUTE]          VARCHAR(100) NOT NULL,
           [COLUMN_1]                  VARCHAR(100) NOT NULL,
           [COLUMN_2]                  VARCHAR(100) NULL,
           [COLUMN_3]                  VARCHAR(100) NULL,
           [COLUMN_4]                  VARCHAR(100) NULL,
           [COLUMN_5]                  VARCHAR(100) NULL,
           [COLUMN_6]                  VARCHAR(100) NULL,
           [COLUMN_7]                  VARCHAR(100) NULL,
           [COLUMN_8]                  VARCHAR(100) NULL,
           [COLUMN_9]                  VARCHAR(100) NULL,
           [COLUMN_10]                 VARCHAR(100) NULL,
           [COLUMN_11]                 VARCHAR(100) NULL,
           [COLUMN_12]                 VARCHAR(100) NULL,
           [COLUMN_13]                 VARCHAR(100) NULL,
           [COLUMN_14]                 VARCHAR(100) NULL,
           [COLUMN_15]                 VARCHAR(100) NULL,
           [COLUMN_16]                 VARCHAR(100) NULL,
           [COLUMN_17]                 VARCHAR(100) NULL,
           [COLUMN_18]                 VARCHAR(100) NULL,
           [COLUMN_19]                 VARCHAR(100) NULL,
           [COLUMN_20]                 VARCHAR(100) NULL,
           [INBOUND_STATUS]            CHAR(1) NULL,
           [RECORD_LOCK_STATUS]        BIT NOT NULL,
           [BATCH_ID]                  VARCHAR(50) NOT NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_MASTER_DATA_ATTRIBUTE_MASTER_DATA_ATTRIBUTE_SID'
                     AND TABLE_NAME = 'MASTER_DATA_ATTRIBUTE')
					 BEGIN
  ALTER TABLE MASTER_DATA_ATTRIBUTE
    ADD CONSTRAINT PK_MASTER_DATA_ATTRIBUTE_MASTER_DATA_ATTRIBUTE_SID PRIMARY KEY(MASTER_DATA_ATTRIBUTE_SID)
END
GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.MASTER_DATA_ATTRIBUTE')
                      AND NAME = 'DF_MASTER_DATA_ATTRIBUTE_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[MASTER_DATA_ATTRIBUTE]
        ADD CONSTRAINT [DF_MASTER_DATA_ATTRIBUTE_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.MASTER_DATA_ATTRIBUTE')
                      AND NAME = 'DF_MASTER_DATA_ATTRIBUTE_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[MASTER_DATA_ATTRIBUTE]
        ADD CONSTRAINT [DF_MASTER_DATA_ATTRIBUTE_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.MASTER_DATA_ATTRIBUTE')
                      AND NAME = 'DF_MASTER_DATA_ATTRIBUTE_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[MASTER_DATA_ATTRIBUTE]
        ADD CONSTRAINT [DF_MASTER_DATA_ATTRIBUTE_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.MASTER_DATA_ATTRIBUTE')
                      AND NAME = 'DF_MASTER_DATA_ATTRIBUTE_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[MASTER_DATA_ATTRIBUTE]
        ADD CONSTRAINT [DF_MASTER_DATA_ATTRIBUTE_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.MASTER_DATA_ATTRIBUTE')
                      AND NAME = 'DF_MASTER_DATA_ATTRIBUTE_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[MASTER_DATA_ATTRIBUTE]
        ADD CONSTRAINT [DF_MASTER_DATA_ATTRIBUTE_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'MASTER_DATA_ATTRIBUTE'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

----------------------HIST_MASTER_DATA_ATTRIBUTE-----------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_MASTER_DATA_ATTRIBUTE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_MASTER_DATA_ATTRIBUTE]
        (
           [MASTER_DATA_ATTRIBUTE_SID] INT NOT NULL,
           [MASTER_TYPE]               VARCHAR(100) NOT NULL,
           [MASTER_ID]                 VARCHAR(100) NOT NULL,
           [MASTER_ATTRIBUTE]          VARCHAR(100) NOT NULL,
           [COLUMN_1]                  VARCHAR(100) NOT NULL,
           [COLUMN_2]                  VARCHAR(100) NULL,
           [COLUMN_3]                  VARCHAR(100) NULL,
           [COLUMN_4]                  VARCHAR(100) NULL,
           [COLUMN_5]                  VARCHAR(100) NULL,
           [COLUMN_6]                  VARCHAR(100) NULL,
           [COLUMN_7]                  VARCHAR(100) NULL,
           [COLUMN_8]                  VARCHAR(100) NULL,
           [COLUMN_9]                  VARCHAR(100) NULL,
           [COLUMN_10]                 VARCHAR(100) NULL,
           [COLUMN_11]                 VARCHAR(100) NULL,
           [COLUMN_12]                 VARCHAR(100) NULL,
           [COLUMN_13]                 VARCHAR(100) NULL,
           [COLUMN_14]                 VARCHAR(100) NULL,
           [COLUMN_15]                 VARCHAR(100) NULL,
           [COLUMN_16]                 VARCHAR(100) NULL,
           [COLUMN_17]                 VARCHAR(100) NULL,
           [COLUMN_18]                 VARCHAR(100) NULL,
           [COLUMN_19]                 VARCHAR(100) NULL,
           [COLUMN_20]                 VARCHAR(100) NULL,
           [INBOUND_STATUS]            CHAR(1) NULL,
           [RECORD_LOCK_STATUS]        BIT NOT NULL,
           [BATCH_ID]                  VARCHAR(50) NOT NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL,
           [ACTION_FLAG]               CHAR(1) NOT NULL,
           [ACTION_DATE]               DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_MASTER_DATA_ATTRIBUTE')
                      AND NAME = 'DF_HIST_MASTER_DATA_ATTRIBUTE_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_MASTER_DATA_ATTRIBUTE]
        ADD CONSTRAINT [DF_HIST_MASTER_DATA_ATTRIBUTE_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_MASTER_DATA_ATTRIBUTE'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_MASTER_DATA_ATTRIBUTE_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_MASTER_DATA_ATTRIBUTE_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_MASTER_DATA_ATTRIBUTE_INS]
ON [DBO].[MASTER_DATA_ATTRIBUTE]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_MASTER_DATA_ATTRIBUTE
                  (MASTER_DATA_ATTRIBUTE_SID,
                   MASTER_TYPE,
                   MASTER_ID,
                   MASTER_ATTRIBUTE,
                   COLUMN_1,
                   COLUMN_2,
                   COLUMN_3,
                   COLUMN_4,
                   COLUMN_5,
                   COLUMN_6,
                   COLUMN_7,
                   COLUMN_8,
                   COLUMN_9,
                   COLUMN_10,
                   COLUMN_11,
                   COLUMN_12,
                   COLUMN_13,
                   COLUMN_14,
                   COLUMN_15,
                   COLUMN_16,
                   COLUMN_17,
                   COLUMN_18,
                   COLUMN_19,
                   COLUMN_20,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT MASTER_DATA_ATTRIBUTE_SID,
             MASTER_TYPE,
             MASTER_ID,
             MASTER_ATTRIBUTE,
             COLUMN_1,
             COLUMN_2,
             COLUMN_3,
             COLUMN_4,
             COLUMN_5,
             COLUMN_6,
             COLUMN_7,
             COLUMN_8,
             COLUMN_9,
             COLUMN_10,
             COLUMN_11,
             COLUMN_12,
             COLUMN_13,
             COLUMN_14,
             COLUMN_15,
             COLUMN_16,
             COLUMN_17,
             COLUMN_18,
             COLUMN_19,
             COLUMN_20,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_MASTER_DATA_ATTRIBUTE_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_MASTER_DATA_ATTRIBUTE_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_MASTER_DATA_ATTRIBUTE_UPD]
ON [DBO].[MASTER_DATA_ATTRIBUTE]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_MASTER_DATA_ATTRIBUTE
                  (MASTER_DATA_ATTRIBUTE_SID,
                   MASTER_TYPE,
                   MASTER_ID,
                   MASTER_ATTRIBUTE,
                   COLUMN_1,
                   COLUMN_2,
                   COLUMN_3,
                   COLUMN_4,
                   COLUMN_5,
                   COLUMN_6,
                   COLUMN_7,
                   COLUMN_8,
                   COLUMN_9,
                   COLUMN_10,
                   COLUMN_11,
                   COLUMN_12,
                   COLUMN_13,
                   COLUMN_14,
                   COLUMN_15,
                   COLUMN_16,
                   COLUMN_17,
                   COLUMN_18,
                   COLUMN_19,
                   COLUMN_20,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT MASTER_DATA_ATTRIBUTE_SID,
             MASTER_TYPE,
             MASTER_ID,
             MASTER_ATTRIBUTE,
             COLUMN_1,
             COLUMN_2,
             COLUMN_3,
             COLUMN_4,
             COLUMN_5,
             COLUMN_6,
             COLUMN_7,
             COLUMN_8,
             COLUMN_9,
             COLUMN_10,
             COLUMN_11,
             COLUMN_12,
             COLUMN_13,
             COLUMN_14,
             COLUMN_15,
             COLUMN_16,
             COLUMN_17,
             COLUMN_18,
             COLUMN_19,
             COLUMN_20,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_MASTER_DATA_ATTRIBUTE_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_MASTER_DATA_ATTRIBUTE_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_MASTER_DATA_ATTRIBUTE_DEL]
ON [DBO].[MASTER_DATA_ATTRIBUTE]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_MASTER_DATA_ATTRIBUTE
                  (MASTER_DATA_ATTRIBUTE_SID,
                   MASTER_TYPE,
                   MASTER_ID,
                   MASTER_ATTRIBUTE,
                   COLUMN_1,
                   COLUMN_2,
                   COLUMN_3,
                   COLUMN_4,
                   COLUMN_5,
                   COLUMN_6,
                   COLUMN_7,
                   COLUMN_8,
                   COLUMN_9,
                   COLUMN_10,
                   COLUMN_11,
                   COLUMN_12,
                   COLUMN_13,
                   COLUMN_14,
                   COLUMN_15,
                   COLUMN_16,
                   COLUMN_17,
                   COLUMN_18,
                   COLUMN_19,
                   COLUMN_20,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE)
      SELECT MASTER_DATA_ATTRIBUTE_SID,
             MASTER_TYPE,
             MASTER_ID,
             MASTER_ATTRIBUTE,
             COLUMN_1,
             COLUMN_2,
             COLUMN_3,
             COLUMN_4,
             COLUMN_5,
             COLUMN_6,
             COLUMN_7,
             COLUMN_8,
             COLUMN_9,
             COLUMN_10,
             COLUMN_11,
             COLUMN_12,
             COLUMN_13,
             COLUMN_14,
             COLUMN_15,
             COLUMN_16,
             COLUMN_17,
             COLUMN_18,
             COLUMN_19,
             COLUMN_20,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE
      FROM   DELETED
  END

GO

-------------------------SALES_MASTER--------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SALES_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[SALES_MASTER]
        (
           [SALES_MASTER_SID]          INT IDENTITY(1, 1) NOT NULL,
           [SALES_ID]                  VARCHAR(50) NOT NULL,
           [ORGANIZATION_KEY]          VARCHAR(100) NULL,
           [ITEM_ID]                   VARCHAR(38) NOT NULL,
           [ITEM_NO]                   VARCHAR(50) NOT NULL,
           [ITEM_CODE_QUALIFIER]       VARCHAR(50) NULL,
           [PARENT_ITEM_ID]            VARCHAR(38) NULL,
           [ITEM_PARENT_NO]            VARCHAR(50) NULL,
           [ITEM_UOM]                  VARCHAR(50) NOT NULL,
           [DOC_TYPE]                  VARCHAR(50) NULL,
           [ORDER_NUMBER]              VARCHAR(50) NULL,
           [INVOICE_NUMBER]            VARCHAR(50) NULL,
           [INVOICE_LINE_NUMBER]       VARCHAR(50) NULL,
           [INVOICE_DATE]              DATETIME NOT NULL,
           [ORDER_TYPE]                VARCHAR(50) NULL,
           [ORDER_SUBTYPE]             VARCHAR(50) NULL,
           [ANALYSIS_CODE]             VARCHAR(50) NULL,
           [PRICE_ADJUSTMENT_NAME]     VARCHAR(100) NULL,
           [RECORD_SEQUENCE]           NUMERIC(38, 0) NULL,
           [PRICE]                     NUMERIC(22, 6) NULL,
           [QUANTITY]                  NUMERIC(38, 2) NOT NULL,
           [LOT_NO]                    VARCHAR(50) NULL,
           [AMOUNT]                    NUMERIC(22, 6) NOT NULL,
           [CONTRACT_ID]               VARCHAR(50) NULL,
           [CONTRACT_NO]               VARCHAR(50) NULL,
           [ACCOUNT_TYPE]              VARCHAR(100) NULL,
           [CUSTOMER_SUBTYPE]          VARCHAR(100) NULL,
           [WHOLESALE_OWNER_ID]        VARCHAR(100) NULL,
           [ACCOUNT_NO]                VARCHAR(50) NOT NULL,
           [ACCOUNT_NAME]              VARCHAR(100) NULL,
           [IDENTIFIER_CODE_QUALIFIER] VARCHAR(50) NULL,
           [CUSTOMER_COMPANY_CODE]     VARCHAR(100) NULL,
           [IS_ACTIVE]                 CHAR(1) NULL,
           [COMPANY_ID]                VARCHAR(38) NULL,
           [DIVISION_ID]               VARCHAR(38) NULL,
           [MARKET_ID]                 VARCHAR(38) NULL,
           [BRAND_ID]                  VARCHAR(38) NULL,
           [PROVISION_ID]              NUMERIC(22, 0) NULL,
           [ACCOUNT_ID]                VARCHAR(100) NOT NULL,
           [ORDER_RECEIVED_DATE]       DATETIME NOT NULL,
           [UPLOAD_DATE]               DATETIME NULL,
           [INBOUND_STATUS]            CHAR(1) NULL,
           [RECORD_LOCK_STATUS]        BIT NOT NULL,
           [BATCH_ID]                  VARCHAR(50) NOT NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL
        )
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'SALES_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'PROVISION_ID')
  BEGIN
      DROP STATISTICS SALES_MASTER.PROVISION_ID
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'SALES_MASTER'
                  AND COLUMN_NAME = 'SALES_MASTER'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE SALES_MASTER
        ALTER COLUMN PROVISION_ID VARCHAR(50)
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_SALES_MASTER_SALES_MASTER_SID'
                     AND TABLE_NAME = 'SALES_MASTER')
  ALTER TABLE SALES_MASTER
    ADD CONSTRAINT PK_SALES_MASTER_SALES_MASTER_SID PRIMARY KEY(SALES_MASTER_SID)

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.SALES_MASTER')
                      AND NAME = 'DF_SALES_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[SALES_MASTER]
        ADD CONSTRAINT [DF_SALES_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.SALES_MASTER')
                      AND NAME = 'DF_SALES_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[SALES_MASTER]
        ADD CONSTRAINT [DF_SALES_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.SALES_MASTER')
                      AND NAME = 'DF_SALES_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[SALES_MASTER]
        ADD CONSTRAINT [DF_SALES_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.SALES_MASTER')
                      AND NAME = 'DF_SALES_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[SALES_MASTER]
        ADD CONSTRAINT [DF_SALES_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.SALES_MASTER')
                      AND NAME = 'DF_SALES_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[SALES_MASTER]
        ADD CONSTRAINT [DF_SALES_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

------------------UNIQUE_CONSTRAINT-----------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'SALES_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('SALES_MASTER')
                            AND NAME = 'UQ_SALES_MASTER_SALES_ID')
        BEGIN
            ALTER TABLE SALES_MASTER
              ADD CONSTRAINT UQ_SALES_MASTER_SALES_ID UNIQUE(SALES_ID)
        END
  END

GO

-------------------- ALTER SCRIPT------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'SALES_MASTER'
                      AND COLUMN_NAME = 'COMPANY_MASTER_SID')
  BEGIN
      ALTER TABLE [DBO].SALES_MASTER
        ADD COMPANY_MASTER_SID INT NULL
  END

GO

-------------------- ALTER SCRIPT------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'SALES_MASTER'
                      AND COLUMN_NAME = 'CONTRACT_MASTER_SID')
  BEGIN
      ALTER TABLE [DBO].SALES_MASTER
        ADD CONTRACT_MASTER_SID INT NULL
  END

GO

-------------------- ALTER SCRIPT------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'SALES_MASTER'
                      AND COLUMN_NAME = 'ITEM_MASTER_SID')
  BEGIN
      ALTER TABLE [DBO].SALES_MASTER
        ADD ITEM_MASTER_SID INT NULL
  END

GO

-------------------- ALTER SCRIPT------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'SALES_MASTER'
                      AND COLUMN_NAME = 'RS_MODEL_SID')
  BEGIN
      ALTER TABLE [DBO].SALES_MASTER
        ADD RS_MODEL_SID INT NULL
  END

GO

-------------------- ALTER SCRIPT------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'SALES_MASTER'
                      AND COLUMN_NAME = 'BRAND_MASTER_SID')
  BEGIN
      ALTER TABLE [DBO].SALES_MASTER
        ADD BRAND_MASTER_SID INT NULL
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SALES_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_SALES_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_SALES_MASTER]
        (
           [SALES_MASTER_SID]          INT NOT NULL,
           [SALES_ID]                  VARCHAR(50) NOT NULL,
           [ORGANIZATION_KEY]          VARCHAR(100) NULL,
           [ITEM_ID]                   VARCHAR(38) NOT NULL,
           [ITEM_NO]                   VARCHAR(50) NOT NULL,
           [ITEM_CODE_QUALIFIER]       VARCHAR(50) NULL,
           [PARENT_ITEM_ID]            VARCHAR(38) NULL,
           [ITEM_PARENT_NO]            VARCHAR(50) NULL,
           [ITEM_UOM]                  VARCHAR(50) NOT NULL,
           [DOC_TYPE]                  VARCHAR(50) NULL,
           [ORDER_NUMBER]              VARCHAR(50) NULL,
           [INVOICE_NUMBER]            VARCHAR(50) NULL,
           [INVOICE_LINE_NUMBER]       VARCHAR(50) NULL,
           [INVOICE_DATE]              DATETIME NOT NULL,
           [ORDER_TYPE]                VARCHAR(50) NULL,
           [ORDER_SUBTYPE]             VARCHAR(50) NULL,
           [ANALYSIS_CODE]             VARCHAR(50) NULL,
           [PRICE_ADJUSTMENT_NAME]     VARCHAR(100) NULL,
           [RECORD_SEQUENCE]           NUMERIC(38, 0) NULL,
           [PRICE]                     NUMERIC(22, 6) NULL,
           [QUANTITY]                  NUMERIC(38, 2) NOT NULL,
           [LOT_NO]                    VARCHAR(50) NULL,
           [AMOUNT]                    NUMERIC(22, 6) NOT NULL,
           [CONTRACT_ID]               VARCHAR(50) NULL,
           [CONTRACT_NO]               VARCHAR(50) NULL,
           [ACCOUNT_TYPE]              VARCHAR(100) NULL,
           [CUSTOMER_SUBTYPE]          VARCHAR(100) NULL,
           [WHOLESALE_OWNER_ID]        VARCHAR(100) NULL,
           [ACCOUNT_NO]                VARCHAR(50) NOT NULL,
           [ACCOUNT_NAME]              VARCHAR(100) NULL,
           [IDENTIFIER_CODE_QUALIFIER] VARCHAR(50) NULL,
           [CUSTOMER_COMPANY_CODE]     VARCHAR(100) NULL,
           [IS_ACTIVE]                 CHAR(1) NULL,
           [COMPANY_ID]                VARCHAR(38) NULL,
           [DIVISION_ID]               VARCHAR(38) NULL,
           [MARKET_ID]                 VARCHAR(38) NULL,
           [BRAND_ID]                  VARCHAR(38) NULL,
           [PROVISION_ID]              NUMERIC(22, 0) NULL,
           [ACCOUNT_ID]                VARCHAR(100) NOT NULL,
           [ORDER_RECEIVED_DATE]       DATETIME NOT NULL,
           [UPLOAD_DATE]               DATETIME NULL,
           [INBOUND_STATUS]            CHAR(1) NULL,
           [RECORD_LOCK_STATUS]        BIT NOT NULL,
           [BATCH_ID]                  VARCHAR(50) NOT NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL,
           [ACTION_FLAG]               CHAR(1) NOT NULL,
           [ACTION_DATE]               DATETIME NOT NULL
        )
  END

GO

----------------------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_SALES_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'PROVISION_ID')
  BEGIN
      DROP STATISTICS HIST_SALES_MASTER.PROVISION_ID
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_SALES_MASTER'
                  AND COLUMN_NAME = 'PROVISION_ID'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_SALES_MASTER
        ALTER COLUMN PROVISION_ID VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_SALES_MASTER')
                      AND NAME = 'DF_HIST_SALES_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_SALES_MASTER]
        ADD CONSTRAINT [DF_HIST_SALES_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

-------------------- ALTER SCRIPT------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_SALES_MASTER'
                      AND COLUMN_NAME = 'COMPANY_MASTER_SID')
  BEGIN
      ALTER TABLE [DBO].HIST_SALES_MASTER
        ADD COMPANY_MASTER_SID INT NULL
  END

GO

-------------------- ALTER SCRIPT------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_SALES_MASTER'
                      AND COLUMN_NAME = 'CONTRACT_MASTER_SID')
  BEGIN
      ALTER TABLE [DBO].HIST_SALES_MASTER
        ADD CONTRACT_MASTER_SID INT NULL
  END

GO

-------------------- ALTER SCRIPT------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_SALES_MASTER'
                      AND COLUMN_NAME = 'ITEM_MASTER_SID')
  BEGIN
      ALTER TABLE [DBO].HIST_SALES_MASTER
        ADD ITEM_MASTER_SID INT NULL
  END

GO

-------------------- ALTER SCRIPT------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_SALES_MASTER'
                      AND COLUMN_NAME = 'RS_MODEL_SID')
  BEGIN
      ALTER TABLE [DBO].HIST_SALES_MASTER
        ADD RS_MODEL_SID INT NULL
  END

GO

-------------------- ALTER SCRIPT------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_SALES_MASTER'
                      AND COLUMN_NAME = 'BRAND_MASTER_SID')
  BEGIN
      ALTER TABLE [DBO].HIST_SALES_MASTER
        ADD BRAND_MASTER_SID INT NULL
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_SALES_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_SALES_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_SALES_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_SALES_MASTER_INS]
ON [DBO].[SALES_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO DBO.HIST_SALES_MASTER
                  (SALES_MASTER_SID,
                   SALES_ID,
                   ORGANIZATION_KEY,
                   ITEM_ID,
                   ITEM_NO,
                   ITEM_CODE_QUALIFIER,
                   PARENT_ITEM_ID,
                   ITEM_PARENT_NO,
                   ITEM_UOM,
                   DOC_TYPE,
                   ORDER_NUMBER,
                   INVOICE_NUMBER,
                   INVOICE_LINE_NUMBER,
                   INVOICE_DATE,
                   ORDER_TYPE,
                   ORDER_SUBTYPE,
                   ANALYSIS_CODE,
                   PRICE_ADJUSTMENT_NAME,
                   RECORD_SEQUENCE,
                   PRICE,
                   QUANTITY,
                   LOT_NO,
                   AMOUNT,
                   CONTRACT_ID,
                   CONTRACT_NO,
                   ACCOUNT_TYPE,
                   CUSTOMER_SUBTYPE,
                   WHOLESALE_OWNER_ID,
                   ACCOUNT_NO,
                   ACCOUNT_NAME,
                   IDENTIFIER_CODE_QUALIFIER,
                   CUSTOMER_COMPANY_CODE,
                   IS_ACTIVE,
                   COMPANY_ID,
                   DIVISION_ID,
                   MARKET_ID,
                   BRAND_ID,
                   PROVISION_ID,
                   ACCOUNT_ID,
                   ORDER_RECEIVED_DATE,
                   UPLOAD_DATE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   COMPANY_MASTER_SID,
                   CONTRACT_MASTER_SID,
                   ITEM_MASTER_SID,
                   BRAND_MASTER_SID,
                   RS_MODEL_SID)
      SELECT SALES_MASTER_SID,
             SALES_ID,
             ORGANIZATION_KEY,
             ITEM_ID,
             ITEM_NO,
             ITEM_CODE_QUALIFIER,
             PARENT_ITEM_ID,
             ITEM_PARENT_NO,
             ITEM_UOM,
             DOC_TYPE,
             ORDER_NUMBER,
             INVOICE_NUMBER,
             INVOICE_LINE_NUMBER,
             INVOICE_DATE,
             ORDER_TYPE,
             ORDER_SUBTYPE,
             ANALYSIS_CODE,
             PRICE_ADJUSTMENT_NAME,
             RECORD_SEQUENCE,
             PRICE,
             QUANTITY,
             LOT_NO,
             AMOUNT,
             CONTRACT_ID,
             CONTRACT_NO,
             ACCOUNT_TYPE,
             CUSTOMER_SUBTYPE,
             WHOLESALE_OWNER_ID,
             ACCOUNT_NO,
             ACCOUNT_NAME,
             IDENTIFIER_CODE_QUALIFIER,
             CUSTOMER_COMPANY_CODE,
             IS_ACTIVE,
             COMPANY_ID,
             DIVISION_ID,
             MARKET_ID,
             BRAND_ID,
             PROVISION_ID,
             ACCOUNT_ID,
             ORDER_RECEIVED_DATE,
             UPLOAD_DATE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'A',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             COMPANY_MASTER_SID,
             CONTRACT_MASTER_SID,
             ITEM_MASTER_SID,
             BRAND_MASTER_SID,
             RS_MODEL_SID
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_SALES_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_SALES_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_SALES_MASTER_UPD]
ON [DBO].[SALES_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO DBO.HIST_SALES_MASTER
                  (SALES_MASTER_SID,
                   SALES_ID,
                   ORGANIZATION_KEY,
                   ITEM_ID,
                   ITEM_NO,
                   ITEM_CODE_QUALIFIER,
                   PARENT_ITEM_ID,
                   ITEM_PARENT_NO,
                   ITEM_UOM,
                   DOC_TYPE,
                   ORDER_NUMBER,
                   INVOICE_NUMBER,
                   INVOICE_LINE_NUMBER,
                   INVOICE_DATE,
                   ORDER_TYPE,
                   ORDER_SUBTYPE,
                   ANALYSIS_CODE,
                   PRICE_ADJUSTMENT_NAME,
                   RECORD_SEQUENCE,
                   PRICE,
                   QUANTITY,
                   LOT_NO,
                   AMOUNT,
                   CONTRACT_ID,
                   CONTRACT_NO,
                   ACCOUNT_TYPE,
                   CUSTOMER_SUBTYPE,
                   WHOLESALE_OWNER_ID,
                   ACCOUNT_NO,
                   ACCOUNT_NAME,
                   IDENTIFIER_CODE_QUALIFIER,
                   CUSTOMER_COMPANY_CODE,
                   IS_ACTIVE,
                   COMPANY_ID,
                   DIVISION_ID,
                   MARKET_ID,
                   BRAND_ID,
                   PROVISION_ID,
                   ACCOUNT_ID,
                   ORDER_RECEIVED_DATE,
                   UPLOAD_DATE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   COMPANY_MASTER_SID,
                   CONTRACT_MASTER_SID,
                   ITEM_MASTER_SID,
                   BRAND_MASTER_SID,
                   RS_MODEL_SID)
      SELECT SALES_MASTER_SID,
             SALES_ID,
             ORGANIZATION_KEY,
             ITEM_ID,
             ITEM_NO,
             ITEM_CODE_QUALIFIER,
             PARENT_ITEM_ID,
             ITEM_PARENT_NO,
             ITEM_UOM,
             DOC_TYPE,
             ORDER_NUMBER,
             INVOICE_NUMBER,
             INVOICE_LINE_NUMBER,
             INVOICE_DATE,
             ORDER_TYPE,
             ORDER_SUBTYPE,
             ANALYSIS_CODE,
             PRICE_ADJUSTMENT_NAME,
             RECORD_SEQUENCE,
             PRICE,
             QUANTITY,
             LOT_NO,
             AMOUNT,
             CONTRACT_ID,
             CONTRACT_NO,
             ACCOUNT_TYPE,
             CUSTOMER_SUBTYPE,
             WHOLESALE_OWNER_ID,
             ACCOUNT_NO,
             ACCOUNT_NAME,
             IDENTIFIER_CODE_QUALIFIER,
             CUSTOMER_COMPANY_CODE,
             IS_ACTIVE,
             COMPANY_ID,
             DIVISION_ID,
             MARKET_ID,
             BRAND_ID,
             PROVISION_ID,
             ACCOUNT_ID,
             ORDER_RECEIVED_DATE,
             UPLOAD_DATE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'C',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             COMPANY_MASTER_SID,
             CONTRACT_MASTER_SID,
             ITEM_MASTER_SID,
             BRAND_MASTER_SID,
             RS_MODEL_SID
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_SALES_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_SALES_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_SALES_MASTER_DEL]
ON [DBO].[SALES_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO DBO.HIST_SALES_MASTER
                  (SALES_MASTER_SID,
                   SALES_ID,
                   ORGANIZATION_KEY,
                   ITEM_ID,
                   ITEM_NO,
                   ITEM_CODE_QUALIFIER,
                   PARENT_ITEM_ID,
                   ITEM_PARENT_NO,
                   ITEM_UOM,
                   DOC_TYPE,
                   ORDER_NUMBER,
                   INVOICE_NUMBER,
                   INVOICE_LINE_NUMBER,
                   INVOICE_DATE,
                   ORDER_TYPE,
                   ORDER_SUBTYPE,
                   ANALYSIS_CODE,
                   PRICE_ADJUSTMENT_NAME,
                   RECORD_SEQUENCE,
                   PRICE,
                   QUANTITY,
                   LOT_NO,
                   AMOUNT,
                   CONTRACT_ID,
                   CONTRACT_NO,
                   ACCOUNT_TYPE,
                   CUSTOMER_SUBTYPE,
                   WHOLESALE_OWNER_ID,
                   ACCOUNT_NO,
                   ACCOUNT_NAME,
                   IDENTIFIER_CODE_QUALIFIER,
                   CUSTOMER_COMPANY_CODE,
                   IS_ACTIVE,
                   COMPANY_ID,
                   DIVISION_ID,
                   MARKET_ID,
                   BRAND_ID,
                   PROVISION_ID,
                   ACCOUNT_ID,
                   ORDER_RECEIVED_DATE,
                   UPLOAD_DATE,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   ACTION_FLAG,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   COMPANY_MASTER_SID,
                   CONTRACT_MASTER_SID,
                   ITEM_MASTER_SID,
                   BRAND_MASTER_SID,
                   RS_MODEL_SID)
      SELECT SALES_MASTER_SID,
             SALES_ID,
             ORGANIZATION_KEY,
             ITEM_ID,
             ITEM_NO,
             ITEM_CODE_QUALIFIER,
             PARENT_ITEM_ID,
             ITEM_PARENT_NO,
             ITEM_UOM,
             DOC_TYPE,
             ORDER_NUMBER,
             INVOICE_NUMBER,
             INVOICE_LINE_NUMBER,
             INVOICE_DATE,
             ORDER_TYPE,
             ORDER_SUBTYPE,
             ANALYSIS_CODE,
             PRICE_ADJUSTMENT_NAME,
             RECORD_SEQUENCE,
             PRICE,
             QUANTITY,
             LOT_NO,
             AMOUNT,
             CONTRACT_ID,
             CONTRACT_NO,
             ACCOUNT_TYPE,
             CUSTOMER_SUBTYPE,
             WHOLESALE_OWNER_ID,
             ACCOUNT_NO,
             ACCOUNT_NAME,
             IDENTIFIER_CODE_QUALIFIER,
             CUSTOMER_COMPANY_CODE,
             IS_ACTIVE,
             COMPANY_ID,
             DIVISION_ID,
             MARKET_ID,
             BRAND_ID,
             PROVISION_ID,
             ACCOUNT_ID,
             ORDER_RECEIVED_DATE,
             UPLOAD_DATE,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             'D',
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             COMPANY_MASTER_SID,
             CONTRACT_MASTER_SID,
             ITEM_MASTER_SID,
             BRAND_MASTER_SID,
             RS_MODEL_SID
      FROM   DELETED
  END

GO

---------------------- ACCRUAL MASTER TABLE --------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ACCRUAL_MASTER]
        (
           [ACCRUAL_MASTER_SID]        [INT] IDENTITY(1, 1) NOT NULL,
           [ACCRUAL_ID]                [VARCHAR](50) NOT NULL,
           [SALES_MASTER_ID]           [VARCHAR](50) NOT NULL,
           [GL_COMPANY_MASTER_SID]     [INT] NULL,
           [GL_COMPANY_NAME]           [VARCHAR](100) NULL,
           [GL_ACCOUNT]                [VARCHAR](250) NOT NULL,
           [COMPANY_MASTER_SID]        [INT] NULL,
           [COMPANY_ID]                [VARCHAR](50) NOT NULL,
           [COMPANY_NO]                [VARCHAR](50) NOT NULL,
           [COMP_IDENT_CODE_QUALIFIER] [VARCHAR](30) NULL,
           [COMPANY_COST_CENTER]       [VARCHAR](8) NOT NULL,
           [ACCOUNT_ID]                [VARCHAR](50) NOT NULL,
           [ACCOUNT_NO]                [VARCHAR](50) NULL,
           [ACCOUNT_NAME]              [VARCHAR](100) NULL,
           [ACCT_IDENT_CODE_QUALIFIER] [VARCHAR](50) NULL,
           [ITEM_MASTER_SID]           [INT] NULL,
           [ITEM_ID]                   [VARCHAR](50) NOT NULL,
           [ITEM_NO]                   [VARCHAR](50) NULL,
           [ITEM_IDENT_CODE_QUALIFIER] [VARCHAR](50) NULL,
           [CONTRACT_MASTER_SID]       [INT] NULL,
           [CONTRACT_ID]               [VARCHAR](50) NOT NULL,
           [CONTRACT_NO]               [VARCHAR](50) NULL,
           [CONTRACT_NAME]             [VARCHAR](100) NULL,
           [BRAND_ID]                  [VARCHAR](50) NULL,
           [CATEGORY_ID]               [VARCHAR](50) NULL,
           [RS_MODEL_SID]              [INT] NULL,
           [PROVISION_ID]              [VARCHAR](50) NOT NULL,
           [ACCRUAL_TYPE]              [VARCHAR](50) NULL,
           [ACCRUAL_PERIOD_START_DATE] [DATETIME] NOT NULL,
           [ACCRUAL_PERIOD_END_DATE]   [DATETIME] NOT NULL,
           [AMOUNT]                    [NUMERIC](22, 6) NOT NULL,
           [GL_AMOUNT]                 [NUMERIC](22, 6) NULL,
           [SUB_LEDGER]                [VARCHAR](50) NOT NULL,
           [SUB_LEDGER_TYPE]           [VARCHAR](50) NOT NULL,
           [DOCUMENT_TYPE]             [VARCHAR](50) NOT NULL,
           [POSTING_DATE]              [DATETIME] NULL,
           [GL_DATE]                   [DATETIME] NULL,
           [RECORD_CREATED_DATE]       [DATETIME] NULL,
           [INBOUND_STATUS]            [CHAR](1) NULL,
           [RECORD_LOCK_STATUS]        [BIT] NOT NULL,
           [BATCH_ID]                  [VARCHAR](50) NOT NULL,
           [SOURCE]                    [VARCHAR](50) NULL,
           [CREATED_BY]                [INT] NOT NULL,
           [CREATED_DATE]              [DATETIME] NOT NULL,
           [MODIFIED_BY]               [INT] NOT NULL,
           [MODIFIED_DATE]             [DATETIME] NOT NULL,
           [BRAND_MASTER_SID]          [INT] NULL,
           [PROGRAM_TYPE]              [VARCHAR](50) NULL,
           [PROGRAM_NO]                [VARCHAR](50) NULL,
           [ITEM_NAME]                 [VARCHAR](100) NULL,
           [POSTING_INDICATOR]         [BIT] NULL,
           [BRAND_NAME]                [VARCHAR](50) NULL
        )
  END

GO
---------------------------------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'COMPANY_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD COMPANY_NAME VARCHAR(100)
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'BUSINESS_UNIT_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD BUSINESS_UNIT_ID VARCHAR(50) NOT NULL
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'BUSINESS_UNIT_NO' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD BUSINESS_UNIT_NO VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'BU_IDENT_CODE_QUALIFIER' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD BU_IDENT_CODE_QUALIFIER VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'BUSINESS_UNIT_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD BUSINESS_UNIT_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'ITEM_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD ITEM_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'BRAND_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD BRAND_NAME VARCHAR(30) 
  END
GO
IF EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN
     exec sp_RENAME 'ACCRUAL_MASTER.AMOUNT', 'DEDUCTION_AMOUNT' , 'COLUMN';
  END
GO

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_CATEGORY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD ACCRUAL_CATEGORY VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_SCHEDULE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD ACCRUAL_SCHEDULE_TYPE VARCHAR(25) 
  END
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_PROGRAM_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD ACCRUAL_PROGRAM_TYPE VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'SALES_AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD SALES_AMOUNT NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'QUANTITY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD QUANTITY NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC1' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD ACCRUAL_UDC1 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC2' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD ACCRUAL_UDC2 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC3' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD ACCRUAL_UDC3 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC4' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD ACCRUAL_UDC4 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC5' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD ACCRUAL_UDC5 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC6' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD ACCRUAL_UDC6 VARCHAR(100) 
  END
GO
-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_ACCRUAL_MASTER_ACCRUAL_MASTER_SID'
                     AND TABLE_NAME = 'ACCRUAL_MASTER')
  BEGIN
      ALTER TABLE ACCRUAL_MASTER
        ADD CONSTRAINT PK_ACCRUAL_MASTER_ACCRUAL_MASTER_SID PRIMARY KEY(ACCRUAL_MASTER_SID)
  END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACCRUAL_MASTER')
                      AND NAME = 'DF_ACCRUAL_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_MASTER]
        ADD CONSTRAINT [DF_ACCRUAL_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACCRUAL_MASTER')
                      AND NAME = 'DF_ACCRUAL_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_MASTER]
        ADD CONSTRAINT [DF_ACCRUAL_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACCRUAL_MASTER')
                      AND NAME = 'DF_ACCRUAL_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_MASTER]
        ADD CONSTRAINT [DF_ACCRUAL_MASTER_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACCRUAL_MASTER')
                      AND NAME = 'DF_ACCRUAL_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_MASTER]
        ADD CONSTRAINT [DF_ACCRUAL_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACCRUAL_MASTER')
                      AND NAME = 'DF_ACCRUAL_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_MASTER]
        ADD CONSTRAINT [DF_ACCRUAL_MASTER_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO


IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                      AND COLUMN_NAME = 'PROVISION_ID'
                      AND TABLE_SCHEMA = 'DBO' )
  BEGIN
      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN  PROVISION_ID VARCHAR(50) NULL
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                      AND COLUMN_NAME = 'ITEM_ID'
                      AND TABLE_SCHEMA = 'DBO' )
  BEGIN
      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN  ITEM_ID VARCHAR(50) NULL
  END
GO

-----------------FOR AGN-801-----------------------
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'GL_ACCOUNT' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN GL_ACCOUNT VARCHAR(250) NULL
  END 
    GO
	
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'COMPANY_NO' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN COMPANY_NO VARCHAR(50) NULL
  END 
    GO
	
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'COMPANY_COST_CENTER' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN COMPANY_COST_CENTER VARCHAR(8) NULL
  END 
    GO
	

  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'CONTRACT_ID' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN CONTRACT_ID VARCHAR(50) NULL
  END 
    GO

  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'ACCOUNT_ID' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN ACCOUNT_ID VARCHAR(50) NULL
  END 
    GO
   
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'SUB_LEDGER' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN SUB_LEDGER VARCHAR(50) NULL
  END 
    GO
  

  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'SUB_LEDGER_TYPE' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN SUB_LEDGER_TYPE VARCHAR(50) NULL
  END 
    GO
    
  
  
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'DOCUMENT_TYPE' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN DOCUMENT_TYPE VARCHAR(50) NULL
  END 
    GO
 
	 IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'BUSINESS_UNIT_SID' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN BUSINESS_UNIT_SID VARCHAR(50) NULL
  END 
  
  GO
  
	 IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'SALES_MASTER_ID' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN SALES_MASTER_ID VARCHAR(50) NULL
  END 
    GO
    

-----------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ACCRUAL_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO
-------------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'ACCRUAL_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('ACCRUAL_MASTER')
                            AND NAME = 'UQ_ACCRUAL_MASTER_ACCRUAL_ID')
        BEGIN
            ALTER TABLE ACCRUAL_MASTER
              ADD CONSTRAINT UQ_ACCRUAL_MASTER_ACCRUAL_ID UNIQUE(ACCRUAL_ID)
        END
  END

GO
-------------------BUSINESS_UNIT_SID-------------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'BUSINESS_UNIT_SID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD BUSINESS_UNIT_SID INT NULL 
  END
GO
--------------NOT NULL QUERY------------------

IF EXISTS (
    SELECT 1 FROM ACCRUAL_MASTER
      WHERE BUSINESS_UNIT_SID IS NULL)
BEGIN

UPDATE ACCRUAL_MASTER
SET BUSINESS_UNIT_SID = 1
WHERE BUSINESS_UNIT_SID IS NULL

END
GO
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'ACCRUAL_MASTER'
                  AND NAME = 'BUSINESS_UNIT_SID')
  BEGIN
      DROP STATISTICS ACCRUAL_MASTER.BUSINESS_UNIT_SID
  END
  GO


IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'BUSINESS_UNIT_SID'
                  AND IS_NULLABLE = 'YES')
  BEGIN
      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN BUSINESS_UNIT_SID VARCHAR(50) NOT NULL
  END

GO
-----------------------ADDING COLUMN IN ACCRUAL_MASTER------------------------------------

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_INBOUND_INTERFACE_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_MASTER
        ADD ACCRUAL_INBOUND_INTERFACE_ID INT null
  END
  GO
---------------NOT NULL QUERY ACCRUAL_MASTER-------------------

IF EXISTS (
    SELECT 1 FROM ACCRUAL_MASTER
      WHERE ACCRUAL_INBOUND_INTERFACE_ID IS NULL)
BEGIN

UPDATE ACCRUAL_MASTER
SET ACCRUAL_INBOUND_INTERFACE_ID = 1
WHERE ACCRUAL_INBOUND_INTERFACE_ID IS NULL

END
GO


IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'ACCRUAL_INBOUND_INTERFACE_ID'
                  AND IS_NULLABLE = 'YES')
  BEGIN
      ALTER TABLE ACCRUAL_MASTER
        ALTER COLUMN ACCRUAL_INBOUND_INTERFACE_ID INT NOT NULL
  END

GO


---------------------- HIST ACCRUAL MASTER TABLE --------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_ACCRUAL_MASTER]
        (
           [ACCRUAL_MASTER_SID]        [INT] NOT NULL,
           [ACCRUAL_ID]                [VARCHAR](50) NOT NULL,
           [SALES_MASTER_ID]           [VARCHAR](50) NOT NULL,
           [GL_COMPANY_MASTER_SID]     [INT] NULL,
           [GL_COMPANY_NAME]           [VARCHAR](100) NULL,
           [GL_ACCOUNT]                [VARCHAR](250) NOT NULL,
           [COMPANY_MASTER_SID]        [INT] NULL,
           [COMPANY_ID]                [VARCHAR](50) NOT NULL,
           [COMPANY_NO]                [VARCHAR](50) NOT NULL,
           [COMP_IDENT_CODE_QUALIFIER] [VARCHAR](30) NULL,
           [COMPANY_COST_CENTER]       [VARCHAR](8) NOT NULL,
           [ACCOUNT_ID]                [VARCHAR](50) NOT NULL,
           [ACCOUNT_NO]                [VARCHAR](50) NULL,
           [ACCOUNT_NAME]              [VARCHAR](100) NULL,
           [ACCT_IDENT_CODE_QUALIFIER] [VARCHAR](50) NULL,
           [ITEM_MASTER_SID]           [INT] NULL,
           [ITEM_ID]                   [VARCHAR](50) NOT NULL,
           [ITEM_NO]                   [VARCHAR](50) NULL,
           [ITEM_IDENT_CODE_QUALIFIER] [VARCHAR](50) NULL,
           [CONTRACT_MASTER_SID]       [INT] NULL,
           [CONTRACT_ID]               [VARCHAR](50) NOT NULL,
           [CONTRACT_NO]               [VARCHAR](50) NULL,
           [CONTRACT_NAME]             [VARCHAR](100) NULL,
           [BRAND_ID]                  [VARCHAR](50) NULL,
           [CATEGORY_ID]               [VARCHAR](50) NULL,
           [RS_MODEL_SID]              [INT] NULL,
           [PROVISION_ID]              [VARCHAR](50) NOT NULL,
           [ACCRUAL_TYPE]              [VARCHAR](50) NULL,
           [ACCRUAL_PERIOD_START_DATE] [DATETIME] NOT NULL,
           [ACCRUAL_PERIOD_END_DATE]   [DATETIME] NOT NULL,
           [AMOUNT]                    [NUMERIC](22, 6) NOT NULL,
           [GL_AMOUNT]                 [NUMERIC](22, 6) NULL,
           [SUB_LEDGER]                [VARCHAR](50) NOT NULL,
           [SUB_LEDGER_TYPE]           [VARCHAR](50) NOT NULL,
           [DOCUMENT_TYPE]             [VARCHAR](50) NOT NULL,
           [POSTING_DATE]              [DATETIME] NULL,
           [GL_DATE]                   [DATETIME] NULL,
           [RECORD_CREATED_DATE]       [DATETIME] NULL,
           [INBOUND_STATUS]            [CHAR](1) NULL,
           [RECORD_LOCK_STATUS]        [BIT] NOT NULL,
           [BATCH_ID]                  [VARCHAR](50) NOT NULL,
           [SOURCE]                    [VARCHAR](50) NULL,
           [CREATED_BY]                [INT] NOT NULL,
           [CREATED_DATE]              [DATETIME] NOT NULL,
           [MODIFIED_BY]               [INT] NOT NULL,
           [MODIFIED_DATE]             [DATETIME] NOT NULL,
           [BRAND_MASTER_SID]          [INT] NULL,
           [PROGRAM_TYPE]              [VARCHAR](50) NULL,
           [PROGRAM_NO]                [VARCHAR](50) NULL,
           [ITEM_NAME]                 [VARCHAR](100) NULL,
           [POSTING_INDICATOR]         [BIT] NULL,
           [BRAND_NAME]                [VARCHAR](50) NULL,
           [ACTION_FLAG]               [CHAR](1),
           [ACTION_DATE]               [DATETIME]
        )
  END

GO
-----------------------------------------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'COMPANY_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD COMPANY_NAME VARCHAR(100)
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'BUSINESS_UNIT_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD BUSINESS_UNIT_ID VARCHAR(50) NOT NULL
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'BUSINESS_UNIT_NO' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD BUSINESS_UNIT_NO VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'BU_IDENT_CODE_QUALIFIER' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD BU_IDENT_CODE_QUALIFIER VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'BUSINESS_UNIT_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD BUSINESS_UNIT_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'ITEM_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD ITEM_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'BRAND_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD BRAND_NAME VARCHAR(30) 
  END
GO
IF EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN
     exec sp_RENAME 'HIST_ACCRUAL_MASTER.AMOUNT', 'DEDUCTION_AMOUNT' , 'COLUMN';
  END
GO

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_CATEGORY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD ACCRUAL_CATEGORY VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_SCHEDULE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD ACCRUAL_SCHEDULE_TYPE VARCHAR(25) 
  END
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_PROGRAM_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER 
        ADD ACCRUAL_PROGRAM_TYPE VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'SALES_AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD SALES_AMOUNT NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'QUANTITY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD QUANTITY NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC1' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD ACCRUAL_UDC1 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC2' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD ACCRUAL_UDC2 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC3' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD ACCRUAL_UDC3 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC4' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD ACCRUAL_UDC4 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC5' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD ACCRUAL_UDC5 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_UDC6' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD ACCRUAL_UDC6 VARCHAR(100) 
  END
GO

-----------------FOR AGN-801-----------------------
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'GL_ACCOUNT' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN GL_ACCOUNT VARCHAR(250) NULL
  END 
    GO
	
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'COMPANY_NO' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN COMPANY_NO VARCHAR(50) NULL
  END 
    GO
	
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'COMPANY_COST_CENTER' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN COMPANY_COST_CENTER VARCHAR(8) NULL
  END 
    GO
	

  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'CONTRACT_ID' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN CONTRACT_ID VARCHAR(50) NULL
  END 
    GO
	

  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'ACCOUNT_ID' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN ACCOUNT_ID VARCHAR(50) NULL
  END 
    GO
	
	
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'SUB_LEDGER' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN SUB_LEDGER VARCHAR(50) NULL
  END 
    GO

  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'SUB_LEDGER_TYPE' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN SUB_LEDGER_TYPE VARCHAR(50) NULL
  END 
    GO
    
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'DOCUMENT_TYPE' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN DOCUMENT_TYPE VARCHAR(50) NULL
  END 
    GO
	
	 IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'SALES_MASTER_ID' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN SALES_MASTER_ID VARCHAR(50) NULL
  END 
    GO

	 IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'PROVISION_ID' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN PROVISION_ID VARCHAR(50) NULL
  END 
    GO
    
   
	 IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'ITEM_ID' AND IS_NULLABLE = 'NO')
  BEGIN

      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN ITEM_ID VARCHAR(50) NULL
  END 
    GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_ACCRUAL_MASTER')
                      AND NAME = 'DF_ACCRUAL_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_ACCRUAL_MASTER]
        ADD CONSTRAINT [DF_ACCRUAL_MASTER_ACTION_DATE] DEFAULT (GETDATE()) FOR [ACTION_DATE]
  END

GO

-----------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ACCRUAL_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-----------------------ADDING COLUMN IN HIST_ACCRUAL_MASTER------------------------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_MASTER' AND COLUMN_NAME  = 'ACCRUAL_INBOUND_INTERFACE_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_MASTER
        ADD ACCRUAL_INBOUND_INTERFACE_ID INT null
  END
  GO
---------------NOT NULL QUERY HIST_ACCRUAL_MASTER-------------------

IF EXISTS (
    SELECT 1 FROM HIST_ACCRUAL_MASTER
      WHERE ACCRUAL_INBOUND_INTERFACE_ID IS NULL)
BEGIN

UPDATE HIST_ACCRUAL_MASTER
SET ACCRUAL_INBOUND_INTERFACE_ID = 1
WHERE ACCRUAL_INBOUND_INTERFACE_ID IS NULL

END
GO


IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ACCRUAL_MASTER'
                  AND COLUMN_NAME = 'ACCRUAL_INBOUND_INTERFACE_ID'
                  AND IS_NULLABLE = 'YES')
  BEGIN
      ALTER TABLE HIST_ACCRUAL_MASTER
        ALTER COLUMN ACCRUAL_INBOUND_INTERFACE_ID INT NOT NULL
  END

GO


-------------TRIGGER-------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ACCRUAL_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_ACCRUAL_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_ACCRUAL_MASTER_UPD]
ON [DBO].[ACCRUAL_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
        INSERT INTO HIST_ACCRUAL_MASTER
                    (ACCRUAL_MASTER_SID,
                     ACCRUAL_ID,
                     SALES_MASTER_ID,
                     GL_COMPANY_MASTER_SID,
                     GL_COMPANY_NAME,
                     GL_ACCOUNT,
                     COMPANY_MASTER_SID,
                     COMPANY_ID,
                     COMPANY_NO,
                     COMP_IDENT_CODE_QUALIFIER,
                     COMPANY_COST_CENTER,
                     ACCOUNT_ID,
                     ACCOUNT_NO,
                     ACCOUNT_NAME,
                     ACCT_IDENT_CODE_QUALIFIER,
                     ITEM_MASTER_SID,
                     ITEM_ID,
                     ITEM_NO,
                     ITEM_IDENT_CODE_QUALIFIER,
                     CONTRACT_MASTER_SID,
                     CONTRACT_ID,
                     CONTRACT_NO,
                     CONTRACT_NAME,
                     BRAND_ID,
                     CATEGORY_ID,
                     RS_MODEL_SID,
                     PROVISION_ID,
                     ACCRUAL_TYPE,
                     ACCRUAL_PERIOD_START_DATE,
                     ACCRUAL_PERIOD_END_DATE,
                     DEDUCTION_AMOUNT,
                     GL_AMOUNT,
                     SUB_LEDGER,
                     SUB_LEDGER_TYPE,
                     DOCUMENT_TYPE,
                     POSTING_DATE,
                     GL_DATE,
                     RECORD_CREATED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG,
                     BRAND_MASTER_SID,
                     PROGRAM_TYPE,
                     PROGRAM_NO,
                     ITEM_NAME,
                     BRAND_NAME,
					 COMPANY_NAME,
                     BUSINESS_UNIT_ID,
                     BUSINESS_UNIT_NO,
                     BU_IDENT_CODE_QUALIFIER,
                     BUSINESS_UNIT_NAME,
                     ACCRUAL_CATEGORY,
                     ACCRUAL_SCHEDULE_TYPE,
                     ACCRUAL_PROGRAM_TYPE,
                     SALES_AMOUNT,
                     QUANTITY,
                     ACCRUAL_UDC1,
                     ACCRUAL_UDC2,
                     ACCRUAL_UDC3,
                     ACCRUAL_UDC4,
                     ACCRUAL_UDC5,
                     ACCRUAL_UDC6,
					 ACCRUAL_INBOUND_INTERFACE_ID)
        SELECT ACCRUAL_MASTER_SID,
               ACCRUAL_ID,
               SALES_MASTER_ID,
               GL_COMPANY_MASTER_SID,
               GL_COMPANY_NAME,
               GL_ACCOUNT,
               COMPANY_MASTER_SID,
               COMPANY_ID,
               COMPANY_NO,
               COMP_IDENT_CODE_QUALIFIER,
               COMPANY_COST_CENTER,
               ACCOUNT_ID,
               ACCOUNT_NO,
               ACCOUNT_NAME,
               ACCT_IDENT_CODE_QUALIFIER,
               ITEM_MASTER_SID,
               ITEM_ID,
               ITEM_NO,
               ITEM_IDENT_CODE_QUALIFIER,
               CONTRACT_MASTER_SID,
               CONTRACT_ID,
               CONTRACT_NO,
               CONTRACT_NAME,
               BRAND_ID,
               CATEGORY_ID,
               RS_MODEL_SID,
               PROVISION_ID,
               ACCRUAL_TYPE,
               ACCRUAL_PERIOD_START_DATE,
               ACCRUAL_PERIOD_END_DATE,
               DEDUCTION_AMOUNT,
               GL_AMOUNT,
               SUB_LEDGER,
               SUB_LEDGER_TYPE,
               DOCUMENT_TYPE,
               POSTING_DATE,
               GL_DATE,
               RECORD_CREATED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'C',
               BRAND_MASTER_SID,
               PROGRAM_TYPE,
               PROGRAM_NO,
               ITEM_NAME,
               BRAND_NAME,
			   COMPANY_NAME,
               BUSINESS_UNIT_ID,
               BUSINESS_UNIT_NO,
               BU_IDENT_CODE_QUALIFIER,
               BUSINESS_UNIT_NAME,
               ACCRUAL_CATEGORY,
               ACCRUAL_SCHEDULE_TYPE,
               ACCRUAL_PROGRAM_TYPE,
               SALES_AMOUNT,
               QUANTITY,
               ACCRUAL_UDC1,
               ACCRUAL_UDC2,
               ACCRUAL_UDC3,
               ACCRUAL_UDC4,
               ACCRUAL_UDC5,
               ACCRUAL_UDC6,
			   ACCRUAL_INBOUND_INTERFACE_ID
               
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ACCRUAL_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_ACCRUAL_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_ACCRUAL_MASTER_INS]
ON [DBO].[ACCRUAL_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
        INSERT INTO HIST_ACCRUAL_MASTER
                    (ACCRUAL_MASTER_SID,
                     ACCRUAL_ID,
                     SALES_MASTER_ID,
                     GL_COMPANY_MASTER_SID,
                     GL_COMPANY_NAME,
                     GL_ACCOUNT,
                     COMPANY_MASTER_SID,
                     COMPANY_ID,
                     COMPANY_NO,
                     COMP_IDENT_CODE_QUALIFIER,
                     COMPANY_COST_CENTER,
                     ACCOUNT_ID,
                     ACCOUNT_NO,
                     ACCOUNT_NAME,
                     ACCT_IDENT_CODE_QUALIFIER,
                     ITEM_MASTER_SID,
                     ITEM_ID,
                     ITEM_NO,
                     ITEM_IDENT_CODE_QUALIFIER,
                     CONTRACT_MASTER_SID,
                     CONTRACT_ID,
                     CONTRACT_NO,
                     CONTRACT_NAME,
                     BRAND_ID,
                     CATEGORY_ID,
                     RS_MODEL_SID,
                     PROVISION_ID,
                     ACCRUAL_TYPE,
                     ACCRUAL_PERIOD_START_DATE,
                     ACCRUAL_PERIOD_END_DATE,
                     DEDUCTION_AMOUNT,
                     GL_AMOUNT,
                     SUB_LEDGER,
                     SUB_LEDGER_TYPE,
                     DOCUMENT_TYPE,
                     POSTING_DATE,
                     GL_DATE,
                     RECORD_CREATED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG,
                     BRAND_MASTER_SID,
                     PROGRAM_TYPE,
                     PROGRAM_NO,
                     ITEM_NAME,
                     BRAND_NAME,
					 COMPANY_NAME,
                     BUSINESS_UNIT_ID,
                     BUSINESS_UNIT_NO,
                     BU_IDENT_CODE_QUALIFIER,
                     BUSINESS_UNIT_NAME,
                     ACCRUAL_CATEGORY,
                     ACCRUAL_SCHEDULE_TYPE,
                     ACCRUAL_PROGRAM_TYPE,
                     SALES_AMOUNT,
                     QUANTITY,
                     ACCRUAL_UDC1,
                     ACCRUAL_UDC2,
                     ACCRUAL_UDC3,
                     ACCRUAL_UDC4,
                     ACCRUAL_UDC5,
                     ACCRUAL_UDC6,
					 ACCRUAL_INBOUND_INTERFACE_ID)
        SELECT ACCRUAL_MASTER_SID,
               ACCRUAL_ID,
               SALES_MASTER_ID,
               GL_COMPANY_MASTER_SID,
               GL_COMPANY_NAME,
               GL_ACCOUNT,
               COMPANY_MASTER_SID,
               COMPANY_ID,
               COMPANY_NO,
               COMP_IDENT_CODE_QUALIFIER,
               COMPANY_COST_CENTER,
               ACCOUNT_ID,
               ACCOUNT_NO,
               ACCOUNT_NAME,
               ACCT_IDENT_CODE_QUALIFIER,
               ITEM_MASTER_SID,
               ITEM_ID,
               ITEM_NO,
               ITEM_IDENT_CODE_QUALIFIER,
               CONTRACT_MASTER_SID,
               CONTRACT_ID,
               CONTRACT_NO,
               CONTRACT_NAME,
               BRAND_ID,
               CATEGORY_ID,
               RS_MODEL_SID,
               PROVISION_ID,
               ACCRUAL_TYPE,
               ACCRUAL_PERIOD_START_DATE,
               ACCRUAL_PERIOD_END_DATE,
               DEDUCTION_AMOUNT,
               GL_AMOUNT,
               SUB_LEDGER,
               SUB_LEDGER_TYPE,
               DOCUMENT_TYPE,
               POSTING_DATE,
               GL_DATE,
               RECORD_CREATED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'A',
               BRAND_MASTER_SID,
               PROGRAM_TYPE,
               PROGRAM_NO,
               ITEM_NAME,
               BRAND_NAME,
			   COMPANY_NAME,
               BUSINESS_UNIT_ID,
               BUSINESS_UNIT_NO,
               BU_IDENT_CODE_QUALIFIER,
               BUSINESS_UNIT_NAME,
               ACCRUAL_CATEGORY,
               ACCRUAL_SCHEDULE_TYPE,
               ACCRUAL_PROGRAM_TYPE,
               SALES_AMOUNT,
               QUANTITY,
               ACCRUAL_UDC1,
               ACCRUAL_UDC2,
               ACCRUAL_UDC3,
               ACCRUAL_UDC4,
               ACCRUAL_UDC5,
               ACCRUAL_UDC6,
			   ACCRUAL_INBOUND_INTERFACE_ID
               
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ACCRUAL_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_ACCRUAL_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_ACCRUAL_MASTER_DEL]
ON [DBO].[ACCRUAL_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
        INSERT INTO HIST_ACCRUAL_MASTER
                    (ACCRUAL_MASTER_SID,
                     ACCRUAL_ID,
                     SALES_MASTER_ID,
                     GL_COMPANY_MASTER_SID,
                     GL_COMPANY_NAME,
                     GL_ACCOUNT,
                     COMPANY_MASTER_SID,
                     COMPANY_ID,
                     COMPANY_NO,
                     COMP_IDENT_CODE_QUALIFIER,
                     COMPANY_COST_CENTER,
                     ACCOUNT_ID,
                     ACCOUNT_NO,
                     ACCOUNT_NAME,
                     ACCT_IDENT_CODE_QUALIFIER,
                     ITEM_MASTER_SID,
                     ITEM_ID,
                     ITEM_NO,
                     ITEM_IDENT_CODE_QUALIFIER,
                     CONTRACT_MASTER_SID,
                     CONTRACT_ID,
                     CONTRACT_NO,
                     CONTRACT_NAME,
                     BRAND_ID,
                     CATEGORY_ID,
                     RS_MODEL_SID,
                     PROVISION_ID,
                     ACCRUAL_TYPE,
                     ACCRUAL_PERIOD_START_DATE,
                     ACCRUAL_PERIOD_END_DATE,
                     DEDUCTION_AMOUNT,
                     GL_AMOUNT,
                     SUB_LEDGER,
                     SUB_LEDGER_TYPE,
                     DOCUMENT_TYPE,
                     POSTING_DATE,
                     GL_DATE,
                     RECORD_CREATED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG,
                     BRAND_MASTER_SID,
                     PROGRAM_TYPE,
                     PROGRAM_NO,
                     ITEM_NAME,
                     BRAND_NAME,
					 COMPANY_NAME,
                     BUSINESS_UNIT_ID,
                     BUSINESS_UNIT_NO,
                     BU_IDENT_CODE_QUALIFIER,
                     BUSINESS_UNIT_NAME,
                     ACCRUAL_CATEGORY,
                     ACCRUAL_SCHEDULE_TYPE,
                     ACCRUAL_PROGRAM_TYPE,
                     SALES_AMOUNT,
                     QUANTITY,
                     ACCRUAL_UDC1,
                     ACCRUAL_UDC2,
                     ACCRUAL_UDC3,
                     ACCRUAL_UDC4,
                     ACCRUAL_UDC5,
                     ACCRUAL_UDC6,
					 ACCRUAL_INBOUND_INTERFACE_ID)
        SELECT ACCRUAL_MASTER_SID,
               ACCRUAL_ID,
               SALES_MASTER_ID,
               GL_COMPANY_MASTER_SID,
               GL_COMPANY_NAME,
               GL_ACCOUNT,
               COMPANY_MASTER_SID,
               COMPANY_ID,
               COMPANY_NO,
               COMP_IDENT_CODE_QUALIFIER,
               COMPANY_COST_CENTER,
               ACCOUNT_ID,
               ACCOUNT_NO,
               ACCOUNT_NAME,
               ACCT_IDENT_CODE_QUALIFIER,
               ITEM_MASTER_SID,
               ITEM_ID,
               ITEM_NO,
               ITEM_IDENT_CODE_QUALIFIER,
               CONTRACT_MASTER_SID,
               CONTRACT_ID,
               CONTRACT_NO,
               CONTRACT_NAME,
               BRAND_ID,
               CATEGORY_ID,
               RS_MODEL_SID,
               PROVISION_ID,
               ACCRUAL_TYPE,
               ACCRUAL_PERIOD_START_DATE,
               ACCRUAL_PERIOD_END_DATE,
               DEDUCTION_AMOUNT,
               GL_AMOUNT,
               SUB_LEDGER,
               SUB_LEDGER_TYPE,
               DOCUMENT_TYPE,
               POSTING_DATE,
               GL_DATE,
               RECORD_CREATED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'D',
               BRAND_MASTER_SID,
               PROGRAM_TYPE,
               PROGRAM_NO,
               ITEM_NAME,
               BRAND_NAME,
			   COMPANY_NAME,
               BUSINESS_UNIT_ID,
               BUSINESS_UNIT_NO,
               BU_IDENT_CODE_QUALIFIER,
               BUSINESS_UNIT_NAME,
               ACCRUAL_CATEGORY,
               ACCRUAL_SCHEDULE_TYPE,
               ACCRUAL_PROGRAM_TYPE,
               SALES_AMOUNT,
               QUANTITY,
               ACCRUAL_UDC1,
               ACCRUAL_UDC2,
               ACCRUAL_UDC3,
               ACCRUAL_UDC4,
               ACCRUAL_UDC5,
               ACCRUAL_UDC6,
			   ACCRUAL_INBOUND_INTERFACE_ID
               
        FROM   DELETED
  END

GO

--------------------------------------ACCRUAL_DETAILS-----------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ACCRUAL_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ACCRUAL_DETAILS]
        (
     
			[ACCRUAL_DETAILS_SID] [int] IDENTITY(1,1) NOT NULL,
			[ACCRUAL_ID] [varchar](50) NOT NULL,
			[SALES_MASTER_ID] [varchar](50) NULL,
			[GL_ACCOUNT] [varchar](50) NOT NULL,
			[GL_COMPANY_MASTER_SID] [int] NULL,
			[COMPANY_COST_CENTER] [varchar](50) NOT NULL,
			[ACCOUNT_ID] [varchar](50) NOT NULL,
			[ACCOUNT_NO] [varchar](50) NULL,
			[ACCOUNT_NAME] [varchar](100) NULL,
			[ACCT_IDENTIFIER_CODE_QUALIFIER] [varchar](50) NULL,
			[COMPANY_MASTER_SID] [int] NULL,
			[COMPANY_ID] [varchar](50) NOT NULL,
			[COMPANY_IDENTIFIER_CODE_QUALIFIER] [varchar](50) NULL,
			[COMPANY_NO] [varchar](50) NULL,
			[CONTRACT_MASTER_SID] [int] NULL,
			[CONTRACT_ID] [varchar](50) NOT NULL,
			[CONTRACT_NO] [varchar](50) NULL,
			[CONTRACT_NAME] [varchar](100) NULL,
			[BRAND_MASTER_SID] [int] NULL,
			[BRAND_ID] [varchar](50) NULL,
			[ITEM_MASTER_SID] [int] NULL,
			[ITEM_ID] [varchar](50) NOT NULL,
			[ITEM_IDENTIFIER_CODE_QUALIFIER] [varchar](50) NULL,
			[ITEM_NO] [varchar](50) NULL,
			[ACCRUAL_TYPE] [varchar](50) NULL,
			[CATEGORY_ID] [varchar](50) NULL,
			[RS_MODEL_SID] [int] NULL,
			[PROVISION_ID] [varchar](50) NOT NULL,
			[DEDUCTION_TYPE] [varchar](50) NULL,
			[DEDUCTION_SUB_TYPE] [varchar](50) NULL,
			[ACCRUAL_PERIOD_START_DATE] [datetime] NOT NULL,
			[ACCRUAL_PERIOD_END_DATE] [datetime] NOT NULL,
			[AMOUNT] [numeric](22, 6) NOT NULL,
			[SUB_LEDGER] [varchar](50) NOT NULL,
			[SUB_LEDGER_TYPE] [varchar](50) NOT NULL,
			[DOCUMENT_TYPE] [varchar](50) NOT NULL,
			[POSTING_INDICATOR] [varchar](50) NULL,
			[POSTING_DATE] [datetime] NULL,
			[GL_DATE] [datetime] NULL,
			[RECORD_CREATED_DATE] [datetime] NULL,
			[INBOUND_STATUS] [char](1) NULL,
			[RECORD_LOCK_STATUS] [bit] NOT NULL,
			[BATCH_ID] [varchar](50) NOT NULL,
			[SOURCE] [varchar](50) NULL,
			[CREATED_BY] [int] NULL,
			[CREATED_DATE] [datetime] NULL,
			[MODIFIED_BY] [int] NULL,
			[MODIFIED_DATE] [datetime] NULL,
			[POSTING_STATUS] [char](1) NULL,
			[VERSION_NO] [int] NULL,
			[PROGRAM_TYPE] [int] NULL,
			[PROGRAM_NO] [varchar](50) NULL,
			[ITEM_NAME] [varchar](50) NULL,
			[BRAND_NAME] [varchar](50) NULL
        )
  END

GO

IF NOT EXISTS(SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'ACCRUAL_DETAILS'
                     AND TABLE_SCHEMA = 'DBO'
                     AND COLUMN_NAME = 'PROGRAM_TYPE')
  BEGIN
      ALTER TABLE ACCRUAL_DETAILS
        ADD PROGRAM_TYPE INT NULL
  END

GO

IF NOT EXISTS(SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'ACCRUAL_DETAILS'
                     AND TABLE_SCHEMA = 'DBO'
                     AND COLUMN_NAME = 'PROGRAM_NO')
  BEGIN
      ALTER TABLE ACCRUAL_DETAILS
        ADD PROGRAM_NO VARCHAR(50) NULL
  END

GO

IF NOT EXISTS(SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'ACCRUAL_DETAILS'
                     AND TABLE_SCHEMA = 'DBO'
                     AND COLUMN_NAME = 'ITEM_NAME')
  BEGIN
      ALTER TABLE ACCRUAL_DETAILS
        ADD ITEM_NAME VARCHAR(50) NULL
  END

GO

IF NOT EXISTS(SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'ACCRUAL_DETAILS'
                     AND TABLE_SCHEMA = 'DBO'
                     AND COLUMN_NAME = 'BRAND_NAME')
  BEGIN
      ALTER TABLE ACCRUAL_DETAILS
        ADD BRAND_NAME VARCHAR(50) NULL
  END

GO

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_ACCRUAL_DETAILS_ACCRUAL_DETAILS_SID'
                     AND TABLE_NAME = 'ACCRUAL_DETAILS')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_DETAILS]
        ADD CONSTRAINT PK_ACCRUAL_DETAILS_ACCRUAL_DETAILS_SID PRIMARY KEY(ACCRUAL_DETAILS_SID)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACCRUAL_DETAILS')
                      AND NAME = 'DF_ACCRUAL_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_DETAILS]
        ADD CONSTRAINT [DF_ACCRUAL_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACCRUAL_DETAILS')
                      AND NAME = 'DF_ACCRUAL_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_DETAILS]
        ADD CONSTRAINT [DF_ACCRUAL_DETAILS_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACCRUAL_DETAILS')
                      AND NAME = 'DF_ACCRUAL_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_DETAILS]
        ADD CONSTRAINT [DF_ACCRUAL_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACCRUAL_DETAILS')
                      AND NAME = 'DF_ACCRUAL_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_DETAILS]
        ADD CONSTRAINT [DF_ACCRUAL_DETAILS_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ACCRUAL_DETAILS')
                      AND NAME = 'DF_ACCRUAL_DETAILS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_DETAILS]
        ADD CONSTRAINT DF_ACCRUAL_DETAILS_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ACCRUAL_DETAILS' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO
-----------------------------------------------------------

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'COMPANY_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD COMPANY_NAME VARCHAR(100)
  END
GO

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'BUSINESS_UNIT_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD BUSINESS_UNIT_ID VARCHAR(50) NOT NULL
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'BUSINESS_UNIT_NO' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD BUSINESS_UNIT_NO VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'BU_IDENT_CODE_QUALIFIER' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD BU_IDENT_CODE_QUALIFIER VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'BUSINESS_UNIT_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD BUSINESS_UNIT_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ITEM_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD ITEM_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'BRAND_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD BRAND_NAME VARCHAR(30) 
  END
GO
IF EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN

     exec sp_RENAME 'ACCRUAL_DETAILS.AMOUNT', 'DEDUCTION_AMOUNT' , 'COLUMN'
  END
GO

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_CATEGORY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD ACCRUAL_CATEGORY VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_SCHEDULE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD ACCRUAL_SCHEDULE_TYPE VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_PROGRAM_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS 
        ADD ACCRUAL_PROGRAM_TYPE VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'SALES_AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD SALES_AMOUNT NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'QUANTITY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD QUANTITY NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC1' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD ACCRUAL_UDC1 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC2' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD ACCRUAL_UDC2 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC3' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD ACCRUAL_UDC3 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC4' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD ACCRUAL_UDC4 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC5' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD ACCRUAL_UDC5 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC6' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ACCRUAL_DETAILS
        ADD ACCRUAL_UDC6 VARCHAR(100) 
  END
GO

---------------------------------------HIST_ACCRUAL_DETAILS
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ACCRUAL_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_ACCRUAL_DETAILS]
        (
           [ACCRUAL_DETAILS_SID]               [INT] NOT NULL,
           [ACCRUAL_ID]                        [VARCHAR](50) NOT NULL,
           [SALES_MASTER_ID]                   [VARCHAR](50) NULL,
           [GL_ACCOUNT]                        [VARCHAR](50) NOT NULL,
           [GL_COMPANY_MASTER_SID]             [INT] NOT NULL,
           [COMPANY_COST_CENTER]               [VARCHAR](50) NOT NULL,
           [ACCOUNT_ID]                        [VARCHAR](50) NOT NULL,
           [ACCOUNT_NO]                        [VARCHAR](50) NULL,
           [ACCOUNT_NAME]                      [VARCHAR](100) NULL,
           [ACCT_IDENTIFIER_CODE_QUALIFIER]    [VARCHAR](50) NULL,
           [COMPANY_MASTER_SID]                [INT] NOT NULL,
           [COMPANY_ID]                        [VARCHAR](50) NOT NULL,
           [COMPANY_IDENTIFIER_CODE_QUALIFIER] [VARCHAR](50) NULL,
           [COMPANY_NO]                        [VARCHAR](50) NULL,
           [CONTRACT_MASTER_SID]               [INT] NOT NULL,
           [CONTRACT_ID]                       [VARCHAR](50) NOT NULL,
           [CONTRACT_NO]                       [VARCHAR](50) NULL,
           [CONTRACT_NAME]                     [VARCHAR](100) NULL,
           [BRAND_MASTER_SID]                  [INT] NULL,
           [BRAND_ID]                          [VARCHAR](50) NULL,
           [ITEM_MASTER_SID]                   [INT] NOT NULL,
           [ITEM_ID]                           [VARCHAR](50) NOT NULL,
           [ITEM_IDENTIFIER_CODE_QUALIFIER]    [VARCHAR](50) NULL,
           [ITEM_NO]                           [VARCHAR](50) NULL,
           [ACCRUAL_TYPE]                      [VARCHAR](50) NULL,
           [CATEGORY_ID]                       [VARCHAR](50) NULL,
           [RS_MODEL_SID]                      [INT] NOT NULL,
           [PROVISION_ID]                      [VARCHAR](50) NOT NULL,
           [DEDUCTION_TYPE]                    [VARCHAR](50) NULL,
           [DEDUCTION_SUB_TYPE]                [VARCHAR](50) NULL,
           [ACCRUAL_PERIOD_START_DATE]         [DATETIME] NOT NULL,
           [ACCRUAL_PERIOD_END_DATE]           [DATETIME] NOT NULL,
           [AMOUNT]                            [NUMERIC](22, 6) NOT NULL,
           [SUB_LEDGER]                        [VARCHAR](50) NOT NULL,
           [SUB_LEDGER_TYPE]                   [VARCHAR](50) NOT NULL,
           [DOCUMENT_TYPE]                     [VARCHAR](50) NOT NULL,
           [POSTING_INDICATOR]                 [VARCHAR](50) NULL,
           [POSTING_DATE]                      [DATETIME] NULL,
           [GL_DATE]                           [DATETIME] NULL,
           [RECORD_CREATED_DATE]               [DATETIME] NULL,
           [INBOUND_STATUS]                    [CHAR](1) NULL,
           [RECORD_LOCK_STATUS]                [BIT] NOT NULL,
           [BATCH_ID]                          [VARCHAR](50) NOT NULL,
           [SOURCE]                            [VARCHAR](50) NULL,
           [CREATED_BY]                        [INT] NULL,
           [CREATED_DATE]                      [DATETIME] NULL,
           [MODIFIED_BY]                       [INT] NULL,
           [MODIFIED_DATE]                     [DATETIME] NULL,
           [POSTING_STATUS]                    [CHAR](1) NULL,
           [VERSION_NO]                        [INT] NULL,
           ACTION_DATE                         DATETIME NOT NULL,
           ACTION_FLAG                         CHAR(1) NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ACCRUAL_DETAILS'
                      AND COLUMN_NAME = 'PROGRAM_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD PROGRAM_TYPE INT
  END

GO

--
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ACCRUAL_DETAILS'
                      AND COLUMN_NAME = 'PROGRAM_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD PROGRAM_NO VARCHAR(50)
  END

GO

--
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ACCRUAL_DETAILS'
                      AND COLUMN_NAME = 'ITEM_NAME'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD ITEM_NAME VARCHAR(50)
  END

GO

--
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ACCRUAL_DETAILS'
                      AND COLUMN_NAME = 'BRAND_NAME'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD BRAND_NAME VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_ACCRUAL_DETAILS')
                      AND NAME = 'DF_HIST_ACCRUAL_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_ACCRUAL_DETAILS]
        ADD CONSTRAINT [DF_HIST_ACCRUAL_DETAILS_ACTION_DATE] DEFAULT(GETDATE()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ACCRUAL_DETAILS' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO
--------------------------------------------------------------------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'COMPANY_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD COMPANY_NAME VARCHAR(100)
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'BUSINESS_UNIT_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD BUSINESS_UNIT_ID VARCHAR(50) NOT NULL
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'BUSINESS_UNIT_NO' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD BUSINESS_UNIT_NO VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'BU_IDENT_CODE_QUALIFIER' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD BU_IDENT_CODE_QUALIFIER VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'BUSINESS_UNIT_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD BUSINESS_UNIT_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ITEM_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD ITEM_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'BRAND_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD BRAND_NAME VARCHAR(30) 
  END
GO
IF EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN
     exec sp_RENAME 'HIST_ACCRUAL_DETAILS.AMOUNT', 'DEDUCTION_AMOUNT' , 'COLUMN';
  END
GO

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_CATEGORY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD ACCRUAL_CATEGORY VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_SCHEDULE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD ACCRUAL_SCHEDULE_TYPE VARCHAR(25) 
  END
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_PROGRAM_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS 
        ADD ACCRUAL_PROGRAM_TYPE VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'SALES_AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD SALES_AMOUNT NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'QUANTITY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD QUANTITY NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC1' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD ACCRUAL_UDC1 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC2' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD ACCRUAL_UDC2 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC3' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD ACCRUAL_UDC3 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC4' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD ACCRUAL_UDC4 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC5' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD ACCRUAL_UDC5 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ACCRUAL_DETAILS' AND COLUMN_NAME  = 'ACCRUAL_UDC6' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ACCRUAL_DETAILS
        ADD ACCRUAL_UDC6 VARCHAR(100) 
  END
GO

---------------------------------------------------TRIGGERS---------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ACCRUAL_DETAILS_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_ACCRUAL_DETAILS_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_ACCRUAL_DETAILS_UPD]
ON [DBO].[ACCRUAL_DETAILS]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
         AND EXISTS (SELECT *
                     FROM   DELETED)
        INSERT INTO HIST_ACCRUAL_DETAILS
                    (ACCRUAL_DETAILS_SID,
                     ACCRUAL_ID,
                     SALES_MASTER_ID,
                     GL_ACCOUNT,
                     GL_COMPANY_MASTER_SID,
                     COMPANY_COST_CENTER,
                     ACCOUNT_ID,
                     ACCOUNT_NO,
                     ACCOUNT_NAME,
                     ACCT_IDENTIFIER_CODE_QUALIFIER,
                     COMPANY_MASTER_SID,
                     COMPANY_ID,
                     COMPANY_IDENTIFIER_CODE_QUALIFIER,
                     COMPANY_NO,
                     CONTRACT_MASTER_SID,
                     CONTRACT_ID,
                     CONTRACT_NO,
                     CONTRACT_NAME,
                     BRAND_MASTER_SID,
                     BRAND_ID,
                     ITEM_MASTER_SID,
                     ITEM_ID,
                     ITEM_IDENTIFIER_CODE_QUALIFIER,
                     ITEM_NO,
                     ACCRUAL_TYPE,
                     CATEGORY_ID,
                     RS_MODEL_SID,
                     PROVISION_ID,
                     DEDUCTION_TYPE,
                     DEDUCTION_SUB_TYPE,
                     ACCRUAL_PERIOD_START_DATE,
                     ACCRUAL_PERIOD_END_DATE,
                     DEDUCTION_AMOUNT,
                     SUB_LEDGER,
                     SUB_LEDGER_TYPE,
                     DOCUMENT_TYPE,
                     POSTING_INDICATOR,
                     POSTING_DATE,
                     GL_DATE,
                     RECORD_CREATED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     POSTING_STATUS,
                     VERSION_NO,
                     ACTION_FLAG,
                     PROGRAM_TYPE,
                     PROGRAM_NO,
                     ITEM_NAME,
                     BRAND_NAME)
        SELECT ACCRUAL_DETAILS_SID,
               ACCRUAL_ID,
               SALES_MASTER_ID,
               GL_ACCOUNT,
               GL_COMPANY_MASTER_SID,
               COMPANY_COST_CENTER,
               ACCOUNT_ID,
               ACCOUNT_NO,
               ACCOUNT_NAME,
               ACCT_IDENTIFIER_CODE_QUALIFIER,
               COMPANY_MASTER_SID,
               COMPANY_ID,
               COMPANY_IDENTIFIER_CODE_QUALIFIER,
               COMPANY_NO,
               CONTRACT_MASTER_SID,
               CONTRACT_ID,
               CONTRACT_NO,
               CONTRACT_NAME,
               BRAND_MASTER_SID,
               BRAND_ID,
               ITEM_MASTER_SID,
               ITEM_ID,
               ITEM_IDENTIFIER_CODE_QUALIFIER,
               ITEM_NO,
               ACCRUAL_TYPE,
               CATEGORY_ID,
               RS_MODEL_SID,
               PROVISION_ID,
               DEDUCTION_TYPE,
               DEDUCTION_SUB_TYPE,
               ACCRUAL_PERIOD_START_DATE,
               ACCRUAL_PERIOD_END_DATE,
               DEDUCTION_AMOUNT,
               SUB_LEDGER,
               SUB_LEDGER_TYPE,
               DOCUMENT_TYPE,
               POSTING_INDICATOR,
               POSTING_DATE,
               GL_DATE,
               RECORD_CREATED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               POSTING_STATUS,
               VERSION_NO,
               'C',
               PROGRAM_TYPE,
               PROGRAM_NO,
               ITEM_NAME,
               BRAND_NAME
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ACCRUAL_DETAILS_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_ACCRUAL_DETAILS_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_ACCRUAL_DETAILS_INS]
ON [DBO].[ACCRUAL_DETAILS]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
        INSERT INTO HIST_ACCRUAL_DETAILS
                    (ACCRUAL_DETAILS_SID,
                     ACCRUAL_ID,
                     SALES_MASTER_ID,
                     GL_ACCOUNT,
                     GL_COMPANY_MASTER_SID,
                     COMPANY_COST_CENTER,
                     ACCOUNT_ID,
                     ACCOUNT_NO,
                     ACCOUNT_NAME,
                     ACCT_IDENTIFIER_CODE_QUALIFIER,
                     COMPANY_MASTER_SID,
                     COMPANY_ID,
                     COMPANY_IDENTIFIER_CODE_QUALIFIER,
                     COMPANY_NO,
                     CONTRACT_MASTER_SID,
                     CONTRACT_ID,
                     CONTRACT_NO,
                     CONTRACT_NAME,
                     BRAND_MASTER_SID,
                     BRAND_ID,
                     ITEM_MASTER_SID,
                     ITEM_ID,
                     ITEM_IDENTIFIER_CODE_QUALIFIER,
                     ITEM_NO,
                     ACCRUAL_TYPE,
                     CATEGORY_ID,
                     RS_MODEL_SID,
                     PROVISION_ID,
                     DEDUCTION_TYPE,
                     DEDUCTION_SUB_TYPE,
                     ACCRUAL_PERIOD_START_DATE,
                     ACCRUAL_PERIOD_END_DATE,
                     DEDUCTION_AMOUNT,
                     SUB_LEDGER,
                     SUB_LEDGER_TYPE,
                     DOCUMENT_TYPE,
                     POSTING_INDICATOR,
                     POSTING_DATE,
                     GL_DATE,
                     RECORD_CREATED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     POSTING_STATUS,
                     VERSION_NO,
                     ACTION_FLAG,
                     PROGRAM_TYPE,
                     PROGRAM_NO,
                     ITEM_NAME,
                     BRAND_NAME)
        SELECT ACCRUAL_DETAILS_SID,
               ACCRUAL_ID,
               SALES_MASTER_ID,
               GL_ACCOUNT,
               GL_COMPANY_MASTER_SID,
               COMPANY_COST_CENTER,
               ACCOUNT_ID,
               ACCOUNT_NO,
               ACCOUNT_NAME,
               ACCT_IDENTIFIER_CODE_QUALIFIER,
               COMPANY_MASTER_SID,
               COMPANY_ID,
               COMPANY_IDENTIFIER_CODE_QUALIFIER,
               COMPANY_NO,
               CONTRACT_MASTER_SID,
               CONTRACT_ID,
               CONTRACT_NO,
               CONTRACT_NAME,
               BRAND_MASTER_SID,
               BRAND_ID,
               ITEM_MASTER_SID,
               ITEM_ID,
               ITEM_IDENTIFIER_CODE_QUALIFIER,
               ITEM_NO,
               ACCRUAL_TYPE,
               CATEGORY_ID,
               RS_MODEL_SID,
               PROVISION_ID,
               DEDUCTION_TYPE,
               DEDUCTION_SUB_TYPE,
               ACCRUAL_PERIOD_START_DATE,
               ACCRUAL_PERIOD_END_DATE,
               DEDUCTION_AMOUNT,
               SUB_LEDGER,
               SUB_LEDGER_TYPE,
               DOCUMENT_TYPE,
               POSTING_INDICATOR,
               POSTING_DATE,
               GL_DATE,
               RECORD_CREATED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               POSTING_STATUS,
               VERSION_NO,
               'A',
               PROGRAM_TYPE,
               PROGRAM_NO,
               ITEM_NAME,
               BRAND_NAME
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ACCRUAL_DETAILS_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_ACCRUAL_DETAILS_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_ACCRUAL_DETAILS_DEL]
ON [DBO].[ACCRUAL_DETAILS]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   DELETED)
        INSERT INTO HIST_ACCRUAL_DETAILS
                    (ACCRUAL_DETAILS_SID,
                     ACCRUAL_ID,
                     SALES_MASTER_ID,
                     GL_ACCOUNT,
                     GL_COMPANY_MASTER_SID,
                     COMPANY_COST_CENTER,
                     ACCOUNT_ID,
                     ACCOUNT_NO,
                     ACCOUNT_NAME,
                     ACCT_IDENTIFIER_CODE_QUALIFIER,
                     COMPANY_MASTER_SID,
                     COMPANY_ID,
                     COMPANY_IDENTIFIER_CODE_QUALIFIER,
                     COMPANY_NO,
                     CONTRACT_MASTER_SID,
                     CONTRACT_ID,
                     CONTRACT_NO,
                     CONTRACT_NAME,
                     BRAND_MASTER_SID,
                     BRAND_ID,
                     ITEM_MASTER_SID,
                     ITEM_ID,
                     ITEM_IDENTIFIER_CODE_QUALIFIER,
                     ITEM_NO,
                     ACCRUAL_TYPE,
                     CATEGORY_ID,
                     RS_MODEL_SID,
                     PROVISION_ID,
                     DEDUCTION_TYPE,
                     DEDUCTION_SUB_TYPE,
                     ACCRUAL_PERIOD_START_DATE,
                     ACCRUAL_PERIOD_END_DATE,
                     DEDUCTION_AMOUNT,
                     SUB_LEDGER,
                     SUB_LEDGER_TYPE,
                     DOCUMENT_TYPE,
                     POSTING_INDICATOR,
                     POSTING_DATE,
                     GL_DATE,
                     RECORD_CREATED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     POSTING_STATUS,
                     VERSION_NO,
                     ACTION_FLAG,
                     PROGRAM_TYPE,
                     PROGRAM_NO,
                     ITEM_NAME,
                     BRAND_NAME)
        SELECT ACCRUAL_DETAILS_SID,
               ACCRUAL_ID,
               SALES_MASTER_ID,
               GL_ACCOUNT,
               GL_COMPANY_MASTER_SID,
               COMPANY_COST_CENTER,
               ACCOUNT_ID,
               ACCOUNT_NO,
               ACCOUNT_NAME,
               ACCT_IDENTIFIER_CODE_QUALIFIER,
               COMPANY_MASTER_SID,
               COMPANY_ID,
               COMPANY_IDENTIFIER_CODE_QUALIFIER,
               COMPANY_NO,
               CONTRACT_MASTER_SID,
               CONTRACT_ID,
               CONTRACT_NO,
               CONTRACT_NAME,
               BRAND_MASTER_SID,
               BRAND_ID,
               ITEM_MASTER_SID,
               ITEM_ID,
               ITEM_IDENTIFIER_CODE_QUALIFIER,
               ITEM_NO,
               ACCRUAL_TYPE,
               CATEGORY_ID,
               RS_MODEL_SID,
               PROVISION_ID,
               DEDUCTION_TYPE,
               DEDUCTION_SUB_TYPE,
               ACCRUAL_PERIOD_START_DATE,
               ACCRUAL_PERIOD_END_DATE,
               DEDUCTION_AMOUNT,
               SUB_LEDGER,
               SUB_LEDGER_TYPE,
               DOCUMENT_TYPE,
               POSTING_INDICATOR,
               POSTING_DATE,
               GL_DATE,
               RECORD_CREATED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               POSTING_STATUS,
               VERSION_NO,
               'D',
               PROGRAM_TYPE,
               PROGRAM_NO,
               ITEM_NAME,
               BRAND_NAME
        FROM   DELETED
  END

GO

-----------------------------------------------ACCRUAL_OUTBOUND--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ACCRUAL_OUTBOUND'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ACCRUAL_OUTBOUND]
        (
           [ACCRUAL_OUTBOUND_SID]              [NUMERIC](38, 0) IDENTITY(1, 1) NOT NULL,
           [ACCRUAL_ID]                        [VARCHAR](50) NOT NULL,
           [SALES_MASTER_ID]                   [VARCHAR](50) NULL,
           [GL_ACCOUNT]                        [VARCHAR](50) NOT NULL,
           [COMPANY_ID]                        [VARCHAR](50) NOT NULL,
           [COMPANY_IDENTIFIER_CODE_QUALIFIER] [VARCHAR](50) NULL,
           [COMPANY_NO]                        [VARCHAR](50) NOT NULL,
           [COMPANY_COST_CENTER]               [VARCHAR](50) NOT NULL,
           [ACCOUNT_ID]                        [VARCHAR](50) NOT NULL,
           [ACCT_IDENTIFIER_CODE_QUALIFIER]    [VARCHAR](50) NULL,
           [ACCOUNT_NO]                        [VARCHAR](50) NULL,
           [ACCOUNT_NAME]                      [VARCHAR](100) NULL,
           [CONTRACT_ID]                       [VARCHAR](50) NOT NULL,
           [BRAND_ID]                          [VARCHAR](50) NULL,
           [ITEM_ID]                           [VARCHAR](50) NOT NULL,
           [ITEM_IDENTIFIER_CODE_QUALIFIER]    [VARCHAR](50) NULL,
           [ITEM_NO]                           [VARCHAR](50) NULL,
           [ACCRUAL_TYPE]                      [VARCHAR](50) NULL,
           [CATEGORY_ID]                       [VARCHAR](50) NULL,
           [PROVISION_ID]                      [VARCHAR](50) NOT NULL,
           [DEDUCTION_TYPE]                    [VARCHAR](50) NULL,
           [DEDUCTION_SUB_TYPE]                [VARCHAR](50) NULL,
           [ACCRUAL_PERIOD_START_DATE]         [DATETIME] NOT NULL,
           [ACCRUAL_PERIOD_END_DATE]           [DATETIME] NOT NULL,
           [AMOUNT]                            [NUMERIC](22, 6) NOT NULL,
           [SUB_LEDGER]                        [VARCHAR](50) NOT NULL,
           [SUB_LEDGER_TYPE]                   [VARCHAR](50) NOT NULL,
           [DOCUMENT_TYPE]                     [VARCHAR](50) NOT NULL,
           [POSTING_INDICATOR]                 [VARCHAR](50) NULL,
           [POSTING_DATE]                      [DATETIME] NULL,
           [GL_DATE]                           [DATETIME] NULL,
           [RECORD_CREATED_DATE]               [DATETIME] NULL,
           [BATCH_ID]                          [VARCHAR](50) NOT NULL,
           [SOURCE]                            [VARCHAR](50) NULL,
           [CREATED_BY]                        [VARCHAR](50) NULL,
           [CREATED_DATE]                      [DATETIME] NULL,
           [MODIFIED_BY]                       [VARCHAR](50) NULL,
           [MODIFIED_DATE]                     [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_ACCRUAL_OUTBOUND_ACCRUAL_OUTBOUND_SID'
                     AND TABLE_NAME = 'ACCRUAL_OUTBOUND')
  BEGIN
      ALTER TABLE [DBO].[ACCRUAL_OUTBOUND]
        ADD CONSTRAINT PK_ACCRUAL_OUTBOUND_ACCRUAL_OUTBOUND_SID PRIMARY KEY(ACCRUAL_OUTBOUND_SID)
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'ACCRUAL_OUTBOUND'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS ACCRUAL_OUTBOUND.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_OUTBOUND'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE ACCRUAL_OUTBOUND
        ALTER COLUMN CREATED_BY INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'ACCRUAL_OUTBOUND'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS ACCRUAL_OUTBOUND.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ACCRUAL_OUTBOUND'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE ACCRUAL_OUTBOUND
        ALTER COLUMN MODIFIED_BY INT NULL
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ACCRUAL_OUTBOUND' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

---------------------------------------DEMAND_ACTUAL------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'DEMAND_ACTUAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].DEMAND_ACTUAL
        (
           DEMAND_ACTUAL_SID              INT IDENTITY(1, 1) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           FORECAST_YEAR                  [VARCHAR](5) NOT NULL,
           FORECAST_MONTH                 [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
           BRAND_MASTER_SID               INT NULL,
           SEGMENT                        [VARCHAR](50) NULL,
           MARKET_SIZE_UNITS              NUMERIC (22, 6) NOT NULL,
           MARKET_SHARE_RATIO             NUMERIC(25) NOT NULL,
           MARKET_SHARE_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NULL,
           GROSS_UNITS                    NUMERIC (22, 6) NOT NULL,
           GROSS_PRICE                    NUMERIC (22, 6) NOT NULL,
           GROSS_AMOUNT                   NUMERIC (22, 6) NOT NULL,
           NET_SALES_PRICE                NUMERIC (22, 6) NOT NULL,
           NET_SALES_AMOUNT               NUMERIC (22, 6) NOT NULL,
           CREATED_BY                     [VARCHAR](50) NOT NULL,
           CREATED_DATE                   [DATETIME] NOT NULL,
           MODIFIED_BY                    [VARCHAR](50) NOT NULL,
           MODIFIED_DATE                  [DATETIME] NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_DEMAND_ACTUAL_DEMAND_ACTUAL_SID'
                     AND TABLE_NAME = 'DEMAND_ACTUAL')
  ALTER TABLE DEMAND_ACTUAL
    ADD CONSTRAINT PK_DEMAND_ACTUAL_DEMAND_ACTUAL_SID PRIMARY KEY(DEMAND_ACTUAL_SID)

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.DEMAND_ACTUAL')
                      AND NAME = 'DF_DEMAND_ACTUAL_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].DEMAND_ACTUAL
        ADD CONSTRAINT DF_DEMAND_ACTUAL_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.DEMAND_ACTUAL')
                      AND NAME = 'DF_DEMAND_ACTUAL_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].DEMAND_ACTUAL
        ADD CONSTRAINT DF_DEMAND_ACTUAL_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.DEMAND_ACTUAL')
                      AND NAME = 'DF_DEMAND_ACTUAL_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].DEMAND_ACTUAL
        ADD CONSTRAINT DF_DEMAND_ACTUAL_CREATED_DATE DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.DEMAND_ACTUAL')
                      AND NAME = 'DF_DEMAND_ACTUAL_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].DEMAND_ACTUAL
        ADD CONSTRAINT DF_DEMAND_ACTUAL_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.DEMAND_ACTUAL')
                      AND NAME = 'DF_DEMAND_ACTUAL_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].DEMAND_ACTUAL
        ADD CONSTRAINT DF_DEMAND_ACTUAL_MODIFIED_DATE DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'DEMAND_ACTUAL')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('DEMAND_ACTUAL')
                            AND NAME = 'UQ_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COUNTRY_ORGANIZATION_KEY')
        BEGIN
            ALTER TABLE DEMAND_ACTUAL
              ADD CONSTRAINT UQ_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COUNTRY_ORGANIZATION_KEY UNIQUE(FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH, ITEM_ID, COUNTRY, ORGANIZATION_KEY)
        END
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS .STATS
           WHERE  NAME = 'MARKET_SHARE_RATIO'
                  AND OBJECT_ID = OBJECT_ID('DEMAND_ACTUAL'))
  BEGIN
      DROP STATISTICS DEMAND_ACTUAL.MARKET_SHARE_RATIO
  END

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'MARKET_SHARE_RATIO'
                  AND DATA_TYPE = 'NUMERIC'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE DEMAND_ACTUAL
        ALTER COLUMN MARKET_SHARE_RATIO VARCHAR(25)
  END

GO

-------------DROP UQ
IF EXISTS(SELECT 1
          FROM   SYS.KEY_CONSTRAINTS
          WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                 AND PARENT_OBJECT_ID = OBJECT_ID('DEMAND_ACTUAL')
                 AND NAME = 'UQ_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COUNTRY_ORGANIZATION_KEY')
  BEGIN
      ALTER TABLE DEMAND_ACTUAL
        DROP CONSTRAINT UQ_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COUNTRY_ORGANIZATION_KEY
  END

GO

-------------CRAETE NEW UNIQUE_CONSTRAINT
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'DEMAND_ACTUAL')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('DEMAND_ACTUAL')
                            AND NAME = 'UQ_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_SOURCE_COUNTRY_ORGANIZATION_KEY')
        BEGIN
            ALTER TABLE DEMAND_ACTUAL
              ADD CONSTRAINT UQ_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_SOURCE_COUNTRY_ORGANIZATION_KEY UNIQUE(FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH, ITEM_ID, SOURCE, COUNTRY, ORGANIZATION_KEY)
        END
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'DEMAND_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS DEMAND_ACTUAL.CREATED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('DEMAND_ACTUAL')
                  AND NAME = 'DF_DEMAND_ACTUAL_CREATED_BY')
  BEGIN
      ALTER TABLE DEMAND_ACTUAL
        DROP CONSTRAINT DF_DEMAND_ACTUAL_CREATED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE DEMAND_ACTUAL
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEMAND_ACTUAL')
                      AND NAME = 'DF_DEMAND_ACTUAL_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].DEMAND_ACTUAL
        ADD CONSTRAINT DF_DEMAND_ACTUAL_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

---------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'DEMAND_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS DEMAND_ACTUAL.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('DEMAND_ACTUAL')
                  AND NAME = 'DF_DEMAND_ACTUAL_MODIFIED_BY')
  BEGIN
      ALTER TABLE DEMAND_ACTUAL
        DROP CONSTRAINT DF_DEMAND_ACTUAL_MODIFIED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE DEMAND_ACTUAL
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEMAND_ACTUAL')
                      AND NAME = 'DF_DEMAND_ACTUAL_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].DEMAND_ACTUAL
        ADD CONSTRAINT DF_DEMAND_ACTUAL_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

-------------DROP UQ
IF EXISTS(SELECT 1
          FROM   SYS.KEY_CONSTRAINTS
          WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                 AND PARENT_OBJECT_ID = OBJECT_ID('DEMAND_ACTUAL')
                 AND NAME = 'UQ_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_SOURCE_COUNTRY_ORGANIZATION_KEY')
  BEGIN
      ALTER TABLE DEMAND_ACTUAL
        DROP CONSTRAINT UQ_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_SOURCE_COUNTRY_ORGANIZATION_KEY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'FORECAST_MONTH'
                  AND TABLE_NAME = 'DEMAND_ACTUAL'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE DEMAND_ACTUAL
        ALTER COLUMN FORECAST_MONTH TINYINT
  END 
GO

-------------CRAETE NEW UNIQUE_CONSTRAINT
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'DEMAND_ACTUAL')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('DEMAND_ACTUAL')
                            AND NAME = 'UQ_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_SOURCE_COUNTRY_ORGANIZATION_KEY')
        BEGIN
            ALTER TABLE DEMAND_ACTUAL
              ADD CONSTRAINT UQ_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_SOURCE_COUNTRY_ORGANIZATION_KEY UNIQUE(FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH, ITEM_ID, SOURCE, COUNTRY, ORGANIZATION_KEY)
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'DEMAND_ACTUAL'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

--------------------------------------------HIST_DEMAND_ACTUAL-------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_DEMAND_ACTUAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_DEMAND_ACTUAL
        (
           DEMAND_ACTUAL_SID              INT NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           FORECAST_YEAR                  [VARCHAR](5) NOT NULL,
           FORECAST_MONTH                 [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
           BRAND_MASTER_SID               INT NULL,
           SEGMENT                        [VARCHAR](50) NULL,
           MARKET_SIZE_UNITS              NUMERIC (22, 6) NOT NULL,
           MARKET_SHARE_RATIO             NUMERIC(25) NOT NULL,
           MARKET_SHARE_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NULL,
           GROSS_UNITS                    NUMERIC (22, 6) NOT NULL,
           GROSS_PRICE                    NUMERIC (22, 6) NOT NULL,
           GROSS_AMOUNT                   NUMERIC (22, 6) NOT NULL,
           NET_SALES_PRICE                NUMERIC (22, 6) NOT NULL,
           NET_SALES_AMOUNT               NUMERIC (22, 6) NOT NULL,
           CREATED_BY                     [VARCHAR](50) NOT NULL,
           CREATED_DATE                   [DATETIME] NOT NULL,
           MODIFIED_BY                    [VARCHAR](50) NOT NULL,
           MODIFIED_DATE                  [DATETIME] NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL,
           [ACTION_FLAG]                  CHAR(1) NOT NULL,
           [ACTION_DATE]                  DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_DEMAND_ACTUAL')
                      AND NAME = 'DF_HIST_DEMAND_ACTUAL_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_DEMAND_ACTUAL
        ADD CONSTRAINT DF_HIST_DEMAND_ACTUAL_ACTION_DATE DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS .STATS
           WHERE  NAME = 'MARKET_SHARE_RATIO'
                  AND OBJECT_ID = OBJECT_ID('HIST_DEMAND_ACTUAL'))
  BEGIN
      DROP STATISTICS HIST_DEMAND_ACTUAL.MARKET_SHARE_RATIO
  END

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'MARKET_SHARE_RATIO'
                  AND DATA_TYPE = 'NUMERIC'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_DEMAND_ACTUAL
        ALTER COLUMN MARKET_SHARE_RATIO VARCHAR(25)
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_DEMAND_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS HIST_DEMAND_ACTUAL.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_DEMAND_ACTUAL
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_DEMAND_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS HIST_DEMAND_ACTUAL.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_DEMAND_ACTUAL
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_DEMAND_ACTUAL'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEMAND_ACTUAL_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_DEMAND_ACTUAL_INS
  END

GO

CREATE TRIGGER [DBO].TRG_DEMAND_ACTUAL_INS
ON [DBO].DEMAND_ACTUAL
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEMAND_ACTUAL
                  (DEMAND_ACTUAL_SID,
                   FORECAST_TYPE,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG)
      SELECT DEMAND_ACTUAL_SID,
             FORECAST_TYPE,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEMAND_ACTUAL_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_DEMAND_ACTUAL_UPD
  END

GO

CREATE TRIGGER [DBO].TRG_DEMAND_ACTUAL_UPD
ON [DBO].DEMAND_ACTUAL
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEMAND_ACTUAL
                  (DEMAND_ACTUAL_SID,
                   FORECAST_TYPE,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG])
      SELECT DEMAND_ACTUAL_SID,
             FORECAST_TYPE,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEMAND_ACTUAL_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_DEMAND_ACTUAL_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_DEMAND_ACTUAL_DEL
ON [DBO].DEMAND_ACTUAL
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEMAND_ACTUAL
                  (DEMAND_ACTUAL_SID,
                   FORECAST_TYPE,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG])
      SELECT DEMAND_ACTUAL_SID,
             FORECAST_TYPE,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'D'
      FROM   DELETED
  END

GO

-----------------------------------DEMAND_FORECAST--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'DEMAND_FORECAST'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[DEMAND_FORECAST]
        (
           DEMAND_FORECAST_SID            INT IDENTITY(1, 1) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           FORECAST_YEAR                  [VARCHAR](5) NOT NULL,
           FORECAST_MONTH                 [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
           BRAND_MASTER_SID               INT NOT NULL,
           SEGMENT                        [VARCHAR](50) NULL,
           MARKET_SIZE_UNITS              NUMERIC (22, 6) NOT NULL,
           MARKET_SHARE_RATIO             [VARCHAR](25) NOT NULL,
           MARKET_SHARE_UNITS             NUMERIC (22, 6) NOT NULL,
           UNCAPTURED_UNITS               NUMERIC (22, 6) NOT NULL,
           UNCAPTURED_UNITS_RATIO         [VARCHAR](25) NULL,
           TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NULL,
           INVENTORY_UNIT_CHANGE          NUMERIC (22, 6) NOT NULL,
           GROSS_UNITS                    NUMERIC (22, 6) NOT NULL,
           GROSS_PRICE                    NUMERIC (22, 6) NOT NULL,
           GROSS_AMOUNT                   NUMERIC (22, 6) NOT NULL,
           NET_SALES_PRICE                NUMERIC (22, 6) NOT NULL,
           NET_SALES_AMOUNT               NUMERIC (22, 6) NOT NULL,
           CREATED_BY                     [VARCHAR](50) NOT NULL,
           CREATED_DATE                   [DATETIME] NOT NULL,
           MODIFIED_BY                    [VARCHAR](50) NOT NULL,
           MODIFIED_DATE                  [DATETIME] NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           FORECAST_NAME                  [VARCHAR](100) NOT NULL,
           FORECAST_VER                   [VARCHAR](15) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL
        )
  END

GO

-- BRAND_MASTER_SID COLUMN CHANGED FROM NOTNULL  TO NULL
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'DEMAND_FORECAST'
                  AND COLUMN_NAME = 'BRAND_MASTER_SID'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'INT'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].DEMAND_FORECAST
        ALTER COLUMN BRAND_MASTER_SID INT NULL
  END

GO


-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_DEMAND_FORECAST_DEMAND_FORECAST_SID'
                     AND TABLE_NAME = 'DEMAND_FORECAST')
  ALTER TABLE DEMAND_FORECAST
    ADD CONSTRAINT PK_DEMAND_FORECAST_DEMAND_FORECAST_SID PRIMARY KEY(DEMAND_FORECAST_SID)

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.DEMAND_FORECAST')
                      AND NAME = 'DF_DEMAND_FORECAST_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].DEMAND_FORECAST
        ADD CONSTRAINT DF_DEMAND_FORECAST_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.DEMAND_FORECAST')
                      AND NAME = 'DF_DEMAND_FORECAST_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].DEMAND_FORECAST
        ADD CONSTRAINT DF_DEMAND_FORECAST_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.DEMAND_FORECAST')
                      AND NAME = 'DF_DEMAND_FORECAST_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].DEMAND_FORECAST
        ADD CONSTRAINT DF_DEMAND_FORECAST_CREATED_DATE DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.DEMAND_FORECAST')
                      AND NAME = 'DF_DEMAND_FORECAST_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].DEMAND_FORECAST
        ADD CONSTRAINT DF_DEMAND_FORECAST_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.DEMAND_FORECAST')
                      AND NAME = 'DF_DEMAND_FORECAST_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].DEMAND_FORECAST
        ADD CONSTRAINT DF_DEMAND_FORECAST_MODIFIED_DATE DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------UNIQUE_CONSTRAINT---------------------
--IF EXISTS (SELECT NAME
--           FROM   SYS.TABLES
--           WHERE  NAME = 'DEMAND_FORECAST')
--  BEGIN
--      IF NOT EXISTS (SELECT 1
--                     FROM   SYS.KEY_CONSTRAINTS
--                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
--                            AND PARENT_OBJECT_ID = OBJECT_ID('DEMAND_FORECAST')
--                            AND NAME = 'UQ_DEMAND_FORECAST_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COUNTRY_ORGANIZATION_KEY')
--        BEGIN
--            ALTER TABLE DEMAND_FORECAST
--              ADD CONSTRAINT UQ_DEMAND_FORECAST_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COUNTRY_ORGANIZATION_KEY UNIQUE(FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH, ITEM_ID, COUNTRY, ORGANIZATION_KEY)
--        END
--  END

--GO

--DROP UQ
IF EXISTS(SELECT 1
          FROM   SYS.KEY_CONSTRAINTS
          WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                 AND PARENT_OBJECT_ID = OBJECT_ID('DEMAND_FORECAST')
                 AND NAME = 'UQ_DEMAND_FORECAST_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COUNTRY_ORGANIZATION_KEY')
  BEGIN
      ALTER TABLE DEMAND_FORECAST
        DROP CONSTRAINT UQ_DEMAND_FORECAST_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COUNTRY_ORGANIZATION_KEY
  END

GO


IF EXISTS(SELECT 1
          FROM   SYS.KEY_CONSTRAINTS
          WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                 AND PARENT_OBJECT_ID = OBJECT_ID('DEMAND_FORECAST')
                 AND NAME = 'UQ_DEMAND_FORECAST_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_SOURCE_FORECAST_NAME_FORECAST_VER_COUNTRY_ORGANIZATION_KEY')
  BEGIN
      ALTER TABLE DEMAND_FORECAST
        DROP CONSTRAINT UQ_DEMAND_FORECAST_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_SOURCE_FORECAST_NAME_FORECAST_VER_COUNTRY_ORGANIZATION_KEY
  END

GO

-------------------------COLUMN ALTER CELG-985--------------------- 
IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS 
           WHERE COLUMN_NAME = 'FORECAST_MONTH' AND 
           TABLE_NAME = 'DEMAND_FORECAST' AND DATA_TYPE = 'VARCHAR') 
BEGIN 
ALTER TABLE DEMAND_FORECAST ALTER COLUMN FORECAST_MONTH TINYINT 
END 

GO

--CREATE NEW UNIQUE_CONSTRAINT
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'DEMAND_FORECAST')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('DEMAND_FORECAST')
                            AND NAME = 'UQ_DEMAND_FORECAST_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_SOURCE_FORECAST_NAME_FORECAST_VER_COUNTRY_ORGANIZATION_KEY')
        BEGIN
            ALTER TABLE DEMAND_FORECAST
              ADD CONSTRAINT UQ_DEMAND_FORECAST_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_SOURCE_FORECAST_NAME_FORECAST_VER_COUNTRY_ORGANIZATION_KEY UNIQUE(FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH, ITEM_ID, SOURCE, FORECAST_NAME, FORECAST_VER, COUNTRY, ORGANIZATION_KEY)
        END
  END

GO



IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'DEMAND_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS DEMAND_FORECAST.CREATED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('DEMAND_FORECAST')
                  AND NAME = 'DF_DEMAND_FORECAST_CREATED_BY')
  BEGIN
      ALTER TABLE DEMAND_FORECAST
        DROP CONSTRAINT DF_DEMAND_FORECAST_CREATED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'DEMAND_FORECAST'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE DEMAND_FORECAST
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEMAND_FORECAST')
                      AND NAME = 'DF_DEMAND_FORECAST_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].DEMAND_FORECAST
        ADD CONSTRAINT DF_DEMAND_FORECAST_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

-----------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'DEMAND_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS DEMAND_FORECAST.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('DEMAND_FORECAST')
                  AND NAME = 'DF_DEMAND_FORECAST_MODIFIED_BY')
  BEGIN
      ALTER TABLE DEMAND_FORECAST
        DROP CONSTRAINT DF_DEMAND_FORECAST_MODIFIED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'DEMAND_FORECAST'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE DEMAND_FORECAST
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEMAND_FORECAST')
                      AND NAME = 'DF_DEMAND_FORECAST_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].DEMAND_FORECAST
        ADD CONSTRAINT DF_DEMAND_FORECAST_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO
--gal-5751 changes
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'DEMAND_FORECAST'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE DEMAND_FORECAST		
        ADD BUSINESS_UNIT INT 
  END

GO
-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'DEMAND_FORECAST'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

------------------------------------HIST_DEMAND_FORECAST----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_DEMAND_FORECAST'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_DEMAND_FORECAST
        (
           DEMAND_FORECAST_SID            INT NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           FORECAST_YEAR                  [VARCHAR](5) NOT NULL,
           FORECAST_MONTH                 [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
           BRAND_MASTER_SID               INT NOT NULL,
           SEGMENT                        [VARCHAR](50) NULL,
           MARKET_SIZE_UNITS              NUMERIC (22, 6) NOT NULL,
           MARKET_SHARE_RATIO             [VARCHAR](25) NOT NULL,
           MARKET_SHARE_UNITS             NUMERIC (22, 6) NOT NULL,
           UNCAPTURED_UNITS               NUMERIC (22, 6) NOT NULL,
           UNCAPTURED_UNITS_RATIO         [VARCHAR](25) NULL,
           TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NULL,
           INVENTORY_UNIT_CHANGE          NUMERIC (22, 6) NOT NULL,
           GROSS_UNITS                    NUMERIC (22, 6) NOT NULL,
           GROSS_PRICE                    NUMERIC (22, 6) NOT NULL,
           GROSS_AMOUNT                   NUMERIC (22, 6) NOT NULL,
           NET_SALES_PRICE                NUMERIC (22, 6) NOT NULL,
           NET_SALES_AMOUNT               NUMERIC (22, 6) NOT NULL,
           CREATED_BY                     [VARCHAR](50) NOT NULL,
           CREATED_DATE                   [DATETIME] NOT NULL,
           MODIFIED_BY                    [VARCHAR](50) NOT NULL,
           MODIFIED_DATE                  [DATETIME] NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           FORECAST_NAME                  [VARCHAR](100) NOT NULL,
           FORECAST_VER                   [VARCHAR](15) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL,
           ACTION_FLAG                    CHAR(1) NOT NULL,
           ACTION_DATE                    DATETIME NOT NULL
        )
  END

GO

---BRAND_MASTER_SID COLUMN CHANGED FROM NOTNULL  TO NULL
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_DEMAND_FORECAST'
                  AND COLUMN_NAME = 'BRAND_MASTER_SID'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'INT'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].HIST_DEMAND_FORECAST
        ALTER COLUMN BRAND_MASTER_SID INT NULL
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_DEMAND_FORECAST')
                      AND NAME = 'DF_HIST_DEMAND_FORECAST_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_DEMAND_FORECAST
        ADD CONSTRAINT [DF_HIST_DEMAND_FORECAST_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_DEMAND_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS HIST_DEMAND_FORECAST.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_DEMAND_FORECAST'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_DEMAND_FORECAST
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_DEMAND_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS HIST_DEMAND_FORECAST.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_DEMAND_FORECAST'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_DEMAND_FORECAST
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO
--gal-5751 changes
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_DEMAND_FORECAST'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_DEMAND_FORECAST		
        ADD BUSINESS_UNIT INT 
  END

GO
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_DEMAND_FORECAST'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEMAND_FORECAST_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_DEMAND_FORECAST_INS
  END

GO

CREATE TRIGGER [DBO].TRG_DEMAND_FORECAST_INS
ON [DBO].DEMAND_FORECAST
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEMAND_FORECAST
                  (DEMAND_FORECAST_SID,
                   FORECAST_TYPE,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   UNCAPTURED_UNITS,
                   UNCAPTURED_UNITS_RATIO,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   INVENTORY_UNIT_CHANGE,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG,
				   BUSINESS_UNIT)
      SELECT DEMAND_FORECAST_SID,
             FORECAST_TYPE,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             UNCAPTURED_UNITS,
             UNCAPTURED_UNITS_RATIO,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             INVENTORY_UNIT_CHANGE,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'A',
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEMAND_FORECAST_UPD')
  BEGIN
      DROP TRIGGER DBO.[TRG_DEMAND_FORECAST_UPD]
  END

GO

CREATE TRIGGER [DBO].[TRG_DEMAND_FORECAST_UPD]
ON [DBO].DEMAND_FORECAST
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEMAND_FORECAST
                  (DEMAND_FORECAST_SID,
                   FORECAST_TYPE,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   UNCAPTURED_UNITS,
                   UNCAPTURED_UNITS_RATIO,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   INVENTORY_UNIT_CHANGE,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG,
				   BUSINESS_UNIT)
      SELECT DEMAND_FORECAST_SID,
             FORECAST_TYPE,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             UNCAPTURED_UNITS,
             UNCAPTURED_UNITS_RATIO,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             INVENTORY_UNIT_CHANGE,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'C',
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEMAND_FORECAST_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_DEMAND_FORECAST_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_DEMAND_FORECAST_DEL
ON [DBO].DEMAND_FORECAST
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEMAND_FORECAST
                  (DEMAND_FORECAST_SID,
                   FORECAST_TYPE,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   UNCAPTURED_UNITS,
                   UNCAPTURED_UNITS_RATIO,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   INVENTORY_UNIT_CHANGE,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG,
				   BUSINESS_UNIT)
      SELECT DEMAND_FORECAST_SID,
             FORECAST_TYPE,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             UNCAPTURED_UNITS,
             UNCAPTURED_UNITS_RATIO,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             INVENTORY_UNIT_CHANGE,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'D',
			 BUSINESS_UNIT
      FROM   DELETED
  END

GO

--------------------------------------INVENTORY_WD_ACTUAL_DT--------------------------------------
------------------------INVENTORY_WD_ACTUAL_DT------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'INVENTORY_WD_ACTUAL_DT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[INVENTORY_WD_ACTUAL_DT]
        (
           INVENTORY_WD_ACTUAL_DT_SID     INT IDENTITY(1, 1) NOT NULL,
           YEAR                           VARCHAR(5) NOT NULL,
           MONTH                          VARCHAR(25) NOT NULL,
           DAY                            VARCHAR(25) NULL,
           WEEK                           VARCHAR(25) NULL,
           COMPANY_ID                     VARCHAR(38) NOT NULL,
           COMPANY_MASTER_SID             INT NOT NULL,
           IDENTIFIER_CODE_QUALIFIER      VARCHAR(25) NULL,
           COMPANY_IDENTIFIER             VARCHAR(50) NULL,
           ITEM_ID                        VARCHAR(38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER VARCHAR(25) NULL,
           ITEM_IDENTIFIER                VARCHAR(50) NULL,
           UNITS_WITHDRAWN                NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN               NUMERIC(20, 6) NULL,
           UNITS_ON_HAND                  NUMERIC(20, 6) NOT NULL,
           AMOUNT_ON_HAND                 NUMERIC(20, 6) NULL,
           QUANTITY_ON_ORDER              NUMERIC(20, 6) NULL,
           AMOUNT_ON_ORDER                NUMERIC(20, 6) NULL,
           QUANTITY_RECEIVED              NUMERIC(20, 6) NULL,
           AMOUNT_RECEIVED                NUMERIC(20, 6) NULL,
           CREATED_BY                     VARCHAR(50) NOT NULL,
           CREATED_DATE                   DATETIME NOT NULL,
           MODIFIED_BY                    VARCHAR(50) NOT NULL,
           MODIFIED_DATE                  DATETIME NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       VARCHAR(38) NOT NULL,
           SOURCE                         VARCHAR(50) NOT NULL,
           COUNTRY                        VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY               VARCHAR(50) NOT NULL
        )
  END
GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_INVENTORY_WD_ACTUAL_DT_INVENTORY_WD_ACTUAL_DT_SID'
                      AND TABLE_NAME = 'INVENTORY_WD_ACTUAL_DT')
  ALTER TABLE INVENTORY_WD_ACTUAL_DT
    ADD CONSTRAINT PK_INVENTORY_WD_ACTUAL_DT_INVENTORY_WD_ACTUAL_DT_SID PRIMARY KEY (INVENTORY_WD_ACTUAL_DT_SID)

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_RECORD_LOCK_STATUS DEFAULT(1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_CREATED_DATE DEFAULT(GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_DATE DEFAULT(GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'INVENTORY_WD_ACTUAL_DT')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('INVENTORY_WD_ACTUAL_DT')
                            AND NAME = 'UQ_INVENTORY_WD_ACTUAL_DT_YEAR_MONTH_DAY_WEEK_COMPANY_ID_ITEM_ID_COUNTRY_ORGANIZATION_KEY')
        BEGIN
            ALTER TABLE INVENTORY_WD_ACTUAL_DT
              ADD CONSTRAINT UQ_INVENTORY_WD_ACTUAL_DT_YEAR_MONTH_DAY_WEEK_COMPANY_ID_ITEM_ID_COUNTRY_ORGANIZATION_KEY UNIQUE(YEAR, MONTH, DAY, WEEK, COMPANY_ID, ITEM_ID, COUNTRY, ORGANIZATION_KEY)
        END
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'INVENTORY_WD_ACTUAL_DT'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS INVENTORY_WD_ACTUAL_DT.CREATED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('INVENTORY_WD_ACTUAL_DT')
                  AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_CREATED_BY')
  BEGIN
      ALTER TABLE INVENTORY_WD_ACTUAL_DT
        DROP CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_CREATED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'INVENTORY_WD_ACTUAL_DT'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE INVENTORY_WD_ACTUAL_DT
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

---------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'INVENTORY_WD_ACTUAL_DT'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS INVENTORY_WD_ACTUAL_DT.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('INVENTORY_WD_ACTUAL_DT')
                  AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_BY')
  BEGIN
      ALTER TABLE INVENTORY_WD_ACTUAL_DT
        DROP CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'INVENTORY_WD_ACTUAL_DT'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE INVENTORY_WD_ACTUAL_DT
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO
-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'INVENTORY_WD_ACTUAL_DT' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------HIST_INVENTORY_WD_ACTUAL_DT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_ACTUAL_DT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_INVENTORY_WD_ACTUAL_DT
        (
           INVENTORY_WD_ACTUAL_DT_SID     INT NOT NULL,
           YEAR                           VARCHAR(5) NOT NULL,
           MONTH                          VARCHAR(25) NOT NULL,
           DAY                            VARCHAR(25) NULL,
           WEEK                           VARCHAR(25) NULL,
           COMPANY_ID                     VARCHAR(38) NOT NULL,
           COMPANY_MASTER_SID             INT NOT NULL,
           IDENTIFIER_CODE_QUALIFIER      VARCHAR(25) NULL,
           COMPANY_IDENTIFIER             VARCHAR(50) NULL,
           ITEM_ID                        VARCHAR(38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER VARCHAR(25) NULL,
           ITEM_IDENTIFIER                VARCHAR(50) NULL,
           UNITS_WITHDRAWN                NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN               NUMERIC(20, 6) NULL,
           UNITS_ON_HAND                  NUMERIC(20, 6) NOT NULL,
           AMOUNT_ON_HAND                 NUMERIC(20, 6) NULL,
           QUANTITY_ON_ORDER              NUMERIC(20, 6) NULL,
           AMOUNT_ON_ORDER                NUMERIC(20, 6) NULL,
           QUANTITY_RECEIVED              NUMERIC(20, 6) NULL,
           AMOUNT_RECEIVED                NUMERIC(20, 6) NULL,
           CREATED_BY                     VARCHAR(50) NOT NULL,
           CREATED_DATE                   DATETIME NOT NULL,
           MODIFIED_BY                    VARCHAR(50) NOT NULL,
           MODIFIED_DATE                  DATETIME NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       VARCHAR(38) NOT NULL,
           SOURCE                         VARCHAR(50) NOT NULL,
           COUNTRY                        VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY               VARCHAR(50) NOT NULL,
           [ACTION_FLAG]                  CHAR(1) NOT NULL,
           [ACTION_DATE]                  DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_HIST_INVENTORY_WD_ACTUAL_DT_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_HIST_INVENTORY_WD_ACTUAL_DT_ACTION_DATE DEFAULT(GETDATE()) FOR ACTION_DATE
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_INVENTORY_WD_ACTUAL_DT'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS HIST_INVENTORY_WD_ACTUAL_DT.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_ACTUAL_DT'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_INVENTORY_WD_ACTUAL_DT
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_INVENTORY_WD_ACTUAL_DT'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS HIST_INVENTORY_WD_ACTUAL_DT.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_ACTUAL_DT'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_INVENTORY_WD_ACTUAL_DT
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_INVENTORY_WD_ACTUAL_DT' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_ACTUAL_DT_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_ACTUAL_DT_INS
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_ACTUAL_DT_INS
ON [DBO].INVENTORY_WD_ACTUAL_DT
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_ACTUAL_DT
                  (INVENTORY_WD_ACTUAL_DT_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   IDENTIFIER_CODE_QUALIFIER,
                   COMPANY_IDENTIFIER,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   UNITS_ON_HAND,
                   AMOUNT_ON_HAND,
                   QUANTITY_ON_ORDER,
                   AMOUNT_ON_ORDER,
                   QUANTITY_RECEIVED,
                   AMOUNT_RECEIVED,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG)
      SELECT INVENTORY_WD_ACTUAL_DT_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             IDENTIFIER_CODE_QUALIFIER,
             COMPANY_IDENTIFIER,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             UNITS_ON_HAND,
             AMOUNT_ON_HAND,
             QUANTITY_ON_ORDER,
             AMOUNT_ON_ORDER,
             QUANTITY_RECEIVED,
             AMOUNT_RECEIVED,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_ACTUAL_DT_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_ACTUAL_DT_UPD
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_ACTUAL_DT_UPD
ON [DBO].INVENTORY_WD_ACTUAL_DT
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_ACTUAL_DT
                  (INVENTORY_WD_ACTUAL_DT_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   IDENTIFIER_CODE_QUALIFIER,
                   COMPANY_IDENTIFIER,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   UNITS_ON_HAND,
                   AMOUNT_ON_HAND,
                   QUANTITY_ON_ORDER,
                   AMOUNT_ON_ORDER,
                   QUANTITY_RECEIVED,
                   AMOUNT_RECEIVED,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG])
      SELECT INVENTORY_WD_ACTUAL_DT_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             IDENTIFIER_CODE_QUALIFIER,
             COMPANY_IDENTIFIER,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             UNITS_ON_HAND,
             AMOUNT_ON_HAND,
             QUANTITY_ON_ORDER,
             AMOUNT_ON_ORDER,
             QUANTITY_RECEIVED,
             AMOUNT_RECEIVED,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_ACTUAL_DT_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_ACTUAL_DT_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_ACTUAL_DT_DEL
ON [DBO].INVENTORY_WD_ACTUAL_DT
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_ACTUAL_DT
                  (INVENTORY_WD_ACTUAL_DT_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   IDENTIFIER_CODE_QUALIFIER,
                   COMPANY_IDENTIFIER,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   UNITS_ON_HAND,
                   AMOUNT_ON_HAND,
                   QUANTITY_ON_ORDER,
                   AMOUNT_ON_ORDER,
                   QUANTITY_RECEIVED,
                   AMOUNT_RECEIVED,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG])
      SELECT INVENTORY_WD_ACTUAL_DT_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             IDENTIFIER_CODE_QUALIFIER,
             COMPANY_IDENTIFIER,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             UNITS_ON_HAND,
             AMOUNT_ON_HAND,
             QUANTITY_ON_ORDER,
             AMOUNT_ON_ORDER,
             QUANTITY_RECEIVED,
             AMOUNT_RECEIVED,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'D'
      FROM   DELETED
  END

GO

------------------------INVENTORY_WD_ACTUAL_DT------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'INVENTORY_WD_ACTUAL_DT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[INVENTORY_WD_ACTUAL_DT]
        (
           INVENTORY_WD_ACTUAL_DT_SID     INT IDENTITY(1, 1) NOT NULL,
           YEAR                           VARCHAR(5) NOT NULL,
           MONTH                          VARCHAR(25) NOT NULL,
           DAY                            VARCHAR(25) NULL,
           WEEK                           VARCHAR(25) NULL,
           COMPANY_ID                     VARCHAR(38) NOT NULL,
           COMPANY_MASTER_SID             INT NOT NULL,
           IDENTIFIER_CODE_QUALIFIER      VARCHAR(25) NULL,
           COMPANY_IDENTIFIER             VARCHAR(50) NULL,
           ITEM_ID                        VARCHAR(38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER VARCHAR(25) NULL,
           ITEM_IDENTIFIER                VARCHAR(50) NOT NULL,
           UNITS_WITHDRAWN                NUMERIC(20, 6) NULL,
           AMOUNT_WITHDRAWN               NUMERIC(20, 6) NULL,
           UNITS_ON_HAND                  NUMERIC(20, 6) NULL,
           AMOUNT_ON_HAND                 NUMERIC(20, 6) NULL,
           QUANTITY_ON_ORDER              NUMERIC(20, 6) NULL,
           AMOUNT_ON_ORDER                NUMERIC(20, 6) NULL,
           QUANTITY_RECEIVED              NUMERIC(20, 6) NULL,
           AMOUNT_RECEIVED                NUMERIC(20, 6) NULL,
           CREATED_BY                     VARCHAR(50) NOT NULL,
           CREATED_DATE                   DATETIME NOT NULL,
           MODIFIED_BY                    VARCHAR(50) NOT NULL,
           MODIFIED_DATE                  DATETIME NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       VARCHAR(38) NOT NULL,
           SOURCE                         VARCHAR(50) NOT NULL,
           COUNTRY                        VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY               VARCHAR(50) NOT NULL
        )
  END
GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_INVENTORY_WD_ACTUAL_DT_INVENTORY_WD_ACTUAL_DT_SID'
                      AND TABLE_NAME = 'INVENTORY_WD_ACTUAL_DT')

BEGIN
  ALTER TABLE INVENTORY_WD_ACTUAL_DT
    ADD CONSTRAINT PK_INVENTORY_WD_ACTUAL_DT_INVENTORY_WD_ACTUAL_DT_SID PRIMARY KEY (INVENTORY_WD_ACTUAL_DT_SID)
END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_RECORD_LOCK_STATUS DEFAULT(1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_CREATED_DATE DEFAULT(GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_DT_MODIFIED_DATE DEFAULT(GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'INVENTORY_WD_ACTUAL_DT')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('INVENTORY_WD_ACTUAL_DT')
                            AND NAME = 'UQ_INVENTORY_WD_ACTUAL_DT_YEAR_MONTH_DAY_WEEK_COMPANY_ID_ITEM_ID_COUNTRY_ORGANIZATION_KEY')
        BEGIN
            ALTER TABLE INVENTORY_WD_ACTUAL_DT
              ADD CONSTRAINT UQ_INVENTORY_WD_ACTUAL_DT_YEAR_MONTH_DAY_WEEK_COMPANY_ID_ITEM_ID_COUNTRY_ORGANIZATION_KEY UNIQUE(YEAR, MONTH, DAY, WEEK, COMPANY_ID, ITEM_ID, COUNTRY, ORGANIZATION_KEY)
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'INVENTORY_WD_ACTUAL_DT' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------HIST_INVENTORY_WD_ACTUAL_DT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_ACTUAL_DT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_INVENTORY_WD_ACTUAL_DT
        (
           INVENTORY_WD_ACTUAL_DT_SID     INT NOT NULL,
           YEAR                           VARCHAR(5) NOT NULL,
           MONTH                          VARCHAR(25) NOT NULL,
           DAY                            VARCHAR(25) NULL,
           WEEK                           VARCHAR(25) NULL,
           COMPANY_ID                     VARCHAR(38) NOT NULL,
           COMPANY_MASTER_SID             INT NOT NULL,
           IDENTIFIER_CODE_QUALIFIER      VARCHAR(25) NULL,
           COMPANY_IDENTIFIER             VARCHAR(50) NULL,
           ITEM_ID                        VARCHAR(38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER VARCHAR(25) NULL,
           ITEM_IDENTIFIER                VARCHAR(50) NOT NULL,
           UNITS_WITHDRAWN                NUMERIC(20, 6) NULL,
           AMOUNT_WITHDRAWN               NUMERIC(20, 6) NULL,
           UNITS_ON_HAND                  NUMERIC(20, 6) NULL,
           AMOUNT_ON_HAND                 NUMERIC(20, 6) NULL,
           QUANTITY_ON_ORDER              NUMERIC(20, 6) NULL,
           AMOUNT_ON_ORDER                NUMERIC(20, 6) NULL,
           QUANTITY_RECEIVED              NUMERIC(20, 6) NULL,
           AMOUNT_RECEIVED                NUMERIC(20, 6) NULL,
           CREATED_BY                     VARCHAR(50) NOT NULL,
           CREATED_DATE                   DATETIME NOT NULL,
           MODIFIED_BY                    VARCHAR(50) NOT NULL,
           MODIFIED_DATE                  DATETIME NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       VARCHAR(38) NOT NULL,
           SOURCE                         VARCHAR(50) NOT NULL,
           COUNTRY                        VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY               VARCHAR(50) NOT NULL,
           [ACTION_FLAG]                  CHAR(1) NOT NULL,
           [ACTION_DATE]                  DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_HIST_INVENTORY_WD_ACTUAL_DT_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_INVENTORY_WD_ACTUAL_DT
        ADD CONSTRAINT DF_HIST_INVENTORY_WD_ACTUAL_DT_ACTION_DATE DEFAULT(GETDATE()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_INVENTORY_WD_ACTUAL_DT' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_ACTUAL_DT_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_ACTUAL_DT_INS
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_ACTUAL_DT_INS
ON [DBO].INVENTORY_WD_ACTUAL_DT
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_ACTUAL_DT
                  (INVENTORY_WD_ACTUAL_DT_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   IDENTIFIER_CODE_QUALIFIER,
                   COMPANY_IDENTIFIER,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   UNITS_ON_HAND,
                   AMOUNT_ON_HAND,
                   QUANTITY_ON_ORDER,
                   AMOUNT_ON_ORDER,
                   QUANTITY_RECEIVED,
                   AMOUNT_RECEIVED,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG)
      SELECT INVENTORY_WD_ACTUAL_DT_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             IDENTIFIER_CODE_QUALIFIER,
             COMPANY_IDENTIFIER,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             UNITS_ON_HAND,
             AMOUNT_ON_HAND,
             QUANTITY_ON_ORDER,
             AMOUNT_ON_ORDER,
             QUANTITY_RECEIVED,
             AMOUNT_RECEIVED,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_ACTUAL_DT_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_ACTUAL_DT_UPD
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_ACTUAL_DT_UPD
ON [DBO].INVENTORY_WD_ACTUAL_DT
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_ACTUAL_DT
                  (INVENTORY_WD_ACTUAL_DT_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   IDENTIFIER_CODE_QUALIFIER,
                   COMPANY_IDENTIFIER,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   UNITS_ON_HAND,
                   AMOUNT_ON_HAND,
                   QUANTITY_ON_ORDER,
                   AMOUNT_ON_ORDER,
                   QUANTITY_RECEIVED,
                   AMOUNT_RECEIVED,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG])
      SELECT INVENTORY_WD_ACTUAL_DT_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             IDENTIFIER_CODE_QUALIFIER,
             COMPANY_IDENTIFIER,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             UNITS_ON_HAND,
             AMOUNT_ON_HAND,
             QUANTITY_ON_ORDER,
             AMOUNT_ON_ORDER,
             QUANTITY_RECEIVED,
             AMOUNT_RECEIVED,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_ACTUAL_DT_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_ACTUAL_DT_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_ACTUAL_DT_DEL
ON [DBO].INVENTORY_WD_ACTUAL_DT
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_ACTUAL_DT
                  (INVENTORY_WD_ACTUAL_DT_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   IDENTIFIER_CODE_QUALIFIER,
                   COMPANY_IDENTIFIER,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   UNITS_ON_HAND,
                   AMOUNT_ON_HAND,
                   QUANTITY_ON_ORDER,
                   AMOUNT_ON_ORDER,
                   QUANTITY_RECEIVED,
                   AMOUNT_RECEIVED,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG])
      SELECT INVENTORY_WD_ACTUAL_DT_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             IDENTIFIER_CODE_QUALIFIER,
             COMPANY_IDENTIFIER,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             UNITS_ON_HAND,
             AMOUNT_ON_HAND,
             QUANTITY_ON_ORDER,
             AMOUNT_ON_ORDER,
             QUANTITY_RECEIVED,
             AMOUNT_RECEIVED,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'D'
      FROM   DELETED
  END

GO

-------------------------------------------INVENTORY_WD_ACTUAL_MAS-----------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'INVENTORY_WD_ACTUAL_MAS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[INVENTORY_WD_ACTUAL_MAS]
        (
           INVENTORY_WD_ACTUAL_MAS_SID    INT IDENTITY(1, 1) NOT NULL,
           YEAR                           VARCHAR(5) NOT NULL,
           MONTH                          VARCHAR(25) NOT NULL,
           DAY                            VARCHAR(25) NULL,
           WEEK                           VARCHAR(25) NULL,
           ITEM_ID                        VARCHAR(38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER VARCHAR(25) NULL,
           ITEM_IDENTIFIER                VARCHAR(50) NULL,
           UNITS_WITHDRAWN                NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN               NUMERIC(20, 6) NULL,
           UNITS_ON_HAND                  NUMERIC(20, 6) NOT NULL,
           AMOUNT_ON_HAND                 NUMERIC(20, 6) NULL,
           QUANTITY_ON_ORDER              NUMERIC(20, 6) NULL,
           AMOUNT_ON_ORDER                NUMERIC(20, 6) NULL,
           QUANTITY_RECEIVED              NUMERIC(20, 6) NULL,
           AMOUNT_RECEIVED                NUMERIC(20, 6) NULL,
           CREATED_BY                     VARCHAR(50) NOT NULL,
           CREATED_DATE                   DATETIME NOT NULL,
           MODIFIED_BY                    VARCHAR(50) NOT NULL,
           MODIFIED_DATE                  DATETIME NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       VARCHAR(38) NOT NULL,
           SOURCE                         VARCHAR(50) NOT NULL,
           COUNTRY                        VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY               VARCHAR(50) NOT NULL
        )
  END
GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_INVENTORY_WD_ACTUAL_MAS_INVENTORY_WD_ACTUAL_MAS_SID'
                      AND TABLE_NAME = 'INVENTORY_WD_ACTUAL_MAS')
BEGIN
  ALTER TABLE INVENTORY_WD_ACTUAL_MAS
    ADD CONSTRAINT PK_INVENTORY_WD_ACTUAL_MAS_INVENTORY_WD_ACTUAL_MAS_SID PRIMARY KEY (INVENTORY_WD_ACTUAL_MAS_SID)
END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_MAS')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_MAS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_MAS_RECORD_LOCK_STATUS DEFAULT(1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_MAS')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_MAS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_MAS_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_MAS')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_MAS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_MAS_CREATED_DATE DEFAULT(GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_MAS')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_MAS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_MAS_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_ACTUAL_MAS')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_MAS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_MAS_MODIFIED_DATE DEFAULT(GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------------------------UNIQUE_CONSTRAINT-------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'INVENTORY_WD_ACTUAL_MAS')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('INVENTORY_WD_ACTUAL_MAS')
                            AND NAME = 'UQ_INVENTORY_WD_ACTUAL_MAS_YEAR_MONTH_DAY_WEEK_ITEM_ID_COUNTRY_ORGANIZATION_KEY')
        BEGIN
            ALTER TABLE INVENTORY_WD_ACTUAL_MAS
              ADD CONSTRAINT UQ_INVENTORY_WD_ACTUAL_MAS_YEAR_MONTH_DAY_WEEK_ITEM_ID_COUNTRY_ORGANIZATION_KEY UNIQUE(YEAR, MONTH, DAY, WEEK, ITEM_ID, COUNTRY, ORGANIZATION_KEY)
        END
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'INVENTORY_WD_ACTUAL_MAS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS INVENTORY_WD_ACTUAL_MAS.CREATED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('INVENTORY_WD_ACTUAL_MAS')
                  AND NAME = 'DF_INVENTORY_WD_ACTUAL_MAS_CREATED_BY')
  BEGIN
      ALTER TABLE INVENTORY_WD_ACTUAL_MAS
        DROP CONSTRAINT DF_INVENTORY_WD_ACTUAL_MAS_CREATED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'INVENTORY_WD_ACTUAL_MAS'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE INVENTORY_WD_ACTUAL_MAS
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.INVENTORY_WD_ACTUAL_MAS')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_MAS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_MAS_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

-------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'INVENTORY_WD_ACTUAL_MAS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS INVENTORY_WD_ACTUAL_MAS.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('INVENTORY_WD_ACTUAL_MAS')
                  AND NAME = 'DF_INVENTORY_WD_ACTUAL_MAS_MODIFIED_BY')
  BEGIN
      ALTER TABLE INVENTORY_WD_ACTUAL_MAS
        DROP CONSTRAINT DF_INVENTORY_WD_ACTUAL_MAS_MODIFIED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'INVENTORY_WD_ACTUAL_MAS'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE INVENTORY_WD_ACTUAL_MAS
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.INVENTORY_WD_ACTUAL_MAS')
                      AND NAME = 'DF_INVENTORY_WD_ACTUAL_MAS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_ACTUAL_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_ACTUAL_MAS_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO


-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'INVENTORY_WD_ACTUAL_MAS' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------HIST_INVENTORY_WD_ACTUAL_MAS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_ACTUAL_MAS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_INVENTORY_WD_ACTUAL_MAS
        (
           INVENTORY_WD_ACTUAL_MAS_SID    INT NOT NULL,
           YEAR                           VARCHAR(5) NOT NULL,
           MONTH                          VARCHAR(25) NOT NULL,
           DAY                            VARCHAR(25) NULL,
           WEEK                           VARCHAR(25) NULL,
           ITEM_ID                        VARCHAR(38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER VARCHAR(25) NULL,
           ITEM_IDENTIFIER                VARCHAR(50) NULL,
           UNITS_WITHDRAWN                NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN               NUMERIC(20, 6) NULL,
           UNITS_ON_HAND                  NUMERIC(20, 6) NOT NULL,
           AMOUNT_ON_HAND                 NUMERIC(20, 6) NULL,
           QUANTITY_ON_ORDER              NUMERIC(20, 6) NULL,
           AMOUNT_ON_ORDER                NUMERIC(20, 6) NULL,
           QUANTITY_RECEIVED              NUMERIC(20, 6) NULL,
           AMOUNT_RECEIVED                NUMERIC(20, 6) NULL,
           CREATED_BY                     VARCHAR(50) NOT NULL,
           CREATED_DATE                   DATETIME NOT NULL,
           MODIFIED_BY                    VARCHAR(50) NOT NULL,
           MODIFIED_DATE                  DATETIME NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       VARCHAR(38) NOT NULL,
           SOURCE                         VARCHAR(50) NOT NULL,
           COUNTRY                        VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY               VARCHAR(50) NOT NULL,
           [ACTION_FLAG]                  CHAR(1) NOT NULL,
           [ACTION_DATE]                  DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_INVENTORY_WD_ACTUAL_MAS')
                      AND NAME = 'DF_HIST_INVENTORY_WD_ACTUAL_MAS_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_INVENTORY_WD_ACTUAL_MAS
        ADD CONSTRAINT DF_HIST_INVENTORY_WD_ACTUAL_MAS_ACTION_DATE DEFAULT(GETDATE()) FOR ACTION_DATE
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_INVENTORY_WD_ACTUAL_MAS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS HIST_INVENTORY_WD_ACTUAL_MAS.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_ACTUAL_MAS'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_INVENTORY_WD_ACTUAL_MAS
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_INVENTORY_WD_ACTUAL_MAS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS HIST_INVENTORY_WD_ACTUAL_MAS.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_ACTUAL_MAS'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_INVENTORY_WD_ACTUAL_MAS
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_INVENTORY_WD_ACTUAL_MAS' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_ACTUAL_MAS_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_ACTUAL_MAS_INS
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_ACTUAL_MAS_INS
ON [DBO].INVENTORY_WD_ACTUAL_MAS
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_ACTUAL_MAS
                  (INVENTORY_WD_ACTUAL_MAS_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   UNITS_ON_HAND,
                   AMOUNT_ON_HAND,
                   QUANTITY_ON_ORDER,
                   AMOUNT_ON_ORDER,
                   QUANTITY_RECEIVED,
                   AMOUNT_RECEIVED,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG)
      SELECT INVENTORY_WD_ACTUAL_MAS_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             UNITS_ON_HAND,
             AMOUNT_ON_HAND,
             QUANTITY_ON_ORDER,
             AMOUNT_ON_ORDER,
             QUANTITY_RECEIVED,
             AMOUNT_RECEIVED,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_ACTUAL_MAS_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_ACTUAL_MAS_UPD
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_ACTUAL_MAS_UPD
ON [DBO].INVENTORY_WD_ACTUAL_MAS
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_ACTUAL_MAS
                  (INVENTORY_WD_ACTUAL_MAS_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   UNITS_ON_HAND,
                   AMOUNT_ON_HAND,
                   QUANTITY_ON_ORDER,
                   AMOUNT_ON_ORDER,
                   QUANTITY_RECEIVED,
                   AMOUNT_RECEIVED,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG])
      SELECT INVENTORY_WD_ACTUAL_MAS_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             UNITS_ON_HAND,
             AMOUNT_ON_HAND,
             QUANTITY_ON_ORDER,
             AMOUNT_ON_ORDER,
             QUANTITY_RECEIVED,
             AMOUNT_RECEIVED,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_ACTUAL_MAS_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_ACTUAL_MAS_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_ACTUAL_MAS_DEL
ON [DBO].INVENTORY_WD_ACTUAL_MAS
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_ACTUAL_MAS
                  (INVENTORY_WD_ACTUAL_MAS_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   UNITS_ON_HAND,
                   AMOUNT_ON_HAND,
                   QUANTITY_ON_ORDER,
                   AMOUNT_ON_ORDER,
                   QUANTITY_RECEIVED,
                   AMOUNT_RECEIVED,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG])
      SELECT INVENTORY_WD_ACTUAL_MAS_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             UNITS_ON_HAND,
             AMOUNT_ON_HAND,
             QUANTITY_ON_ORDER,
             AMOUNT_ON_ORDER,
             QUANTITY_RECEIVED,
             AMOUNT_RECEIVED,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'D'
      FROM   DELETED
  END

GO

--------------------------------------INVENTORY_WD_PROJ_DT--------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'INVENTORY_WD_PROJ_DT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[INVENTORY_WD_PROJ_DT]
        (
           INVENTORY_WD_PROJ_DT_SID       INT IDENTITY(1, 1) NOT NULL,
           YEAR                           VARCHAR(5) NOT NULL,
           MONTH                          VARCHAR(25) NOT NULL,
           DAY                            VARCHAR(25) NULL,
           WEEK                           VARCHAR(25) NULL,
           COMPANY_ID                     VARCHAR(38) NOT NULL,
           COMPANY_MASTER_SID             INT NOT NULL,
           IDENTIFIER_CODE_QUALIFIER      VARCHAR(25) NULL,
           COMPANY_IDENTIFIER             VARCHAR(50) NULL,
           ITEM_ID                        VARCHAR(38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER VARCHAR(25) NULL,
           ITEM_IDENTIFIER                VARCHAR(50) NULL,
           UNITS_WITHDRAWN                NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN               NUMERIC(20, 6) NULL,
           PRICE                          NUMERIC(20, 6) NULL,
           CREATED_BY                     VARCHAR(50) NOT NULL,
           CREATED_DATE                   DATETIME NOT NULL,
           MODIFIED_BY                    VARCHAR(50) NOT NULL,
           MODIFIED_DATE                  DATETIME NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       VARCHAR(38) NOT NULL,
           SOURCE                         VARCHAR(50) NOT NULL,
           FORECAST_NAME                  VARCHAR(100) NOT NULL,
           FORECAST_VER                   VARCHAR(15) NOT NULL,
           COUNTRY                        VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY               VARCHAR(50) NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_INVENTORY_WD_PROJ_DT_INVENTORY_WD_PROJ_DT_SID'
                      AND TABLE_NAME = 'INVENTORY_WD_PROJ_DT')

BEGIN
  ALTER TABLE INVENTORY_WD_PROJ_DT
    ADD CONSTRAINT PK_INVENTORY_WD_PROJ_DT_INVENTORY_WD_PROJ_DT_SID PRIMARY KEY (INVENTORY_WD_PROJ_DT_SID)
END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_PROJ_DT')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_DT_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_DT
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_DT_RECORD_LOCK_STATUS DEFAULT(1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_PROJ_DT')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_DT_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_DT
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_DT_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_PROJ_DT')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_DT_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_DT
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_DT_CREATED_DATE DEFAULT(GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_PROJ_DT')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_DT_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_DT
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_DT_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_PROJ_DT')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_DT_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_DT
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_DT_MODIFIED_DATE DEFAULT(GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------------------------UNIQUE_CONSTRAINT-------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'INVENTORY_WD_PROJ_DT')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('INVENTORY_WD_PROJ_DT')
                            AND NAME = 'UQ_INVENTORY_WD_PROJ_DT_YEAR_MONTH_DAY_WEEK_COMPANY_ID_ITEM_ID_SOURCE_FORECAST_NAME_FORECAST_VER_COUNTRY_ORGANIZATION_KEY')
        BEGIN
            ALTER TABLE INVENTORY_WD_PROJ_DT
              ADD CONSTRAINT UQ_INVENTORY_WD_PROJ_DT_YEAR_MONTH_DAY_WEEK_COMPANY_ID_ITEM_ID_SOURCE_FORECAST_NAME_FORECAST_VER_COUNTRY_ORGANIZATION_KEY UNIQUE(YEAR, MONTH, DAY, WEEK, COMPANY_ID, ITEM_ID, SOURCE, FORECAST_NAME, FORECAST_VER, COUNTRY, ORGANIZATION_KEY)
        END
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'INVENTORY_WD_PROJ_DT'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS INVENTORY_WD_PROJ_DT.CREATED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('INVENTORY_WD_PROJ_DT')
                  AND NAME = 'DF_INVENTORY_WD_PROJ_DT_CREATED_BY')
  BEGIN
      ALTER TABLE INVENTORY_WD_PROJ_DT
        DROP CONSTRAINT DF_INVENTORY_WD_PROJ_DT_CREATED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'INVENTORY_WD_PROJ_DT'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE INVENTORY_WD_PROJ_DT
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.INVENTORY_WD_PROJ_DT')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_DT_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_DT
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_DT_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

--------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'INVENTORY_WD_PROJ_DT'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS INVENTORY_WD_PROJ_DT.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('INVENTORY_WD_PROJ_DT')
                  AND NAME = 'DF_INVENTORY_WD_PROJ_DT_MODIFIED_BY')
  BEGIN
      ALTER TABLE INVENTORY_WD_PROJ_DT
        DROP CONSTRAINT DF_INVENTORY_WD_PROJ_DT_MODIFIED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'INVENTORY_WD_PROJ_DT'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE INVENTORY_WD_PROJ_DT
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.INVENTORY_WD_PROJ_DT')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_DT_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_DT
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_DT_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO
--
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'INVENTORY_WD_PROJ_DT'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE INVENTORY_WD_PROJ_DT		
        ADD BUSINESS_UNIT INT
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'INVENTORY_WD_PROJ_DT' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------HIST_INVENTORY_WD_PROJ_DT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_PROJ_DT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_INVENTORY_WD_PROJ_DT
        (
           INVENTORY_WD_PROJ_DT_SID       INT NOT NULL,
           YEAR                           VARCHAR(5) NOT NULL,
           MONTH                          VARCHAR(25) NOT NULL,
           DAY                            VARCHAR(25) NULL,
           WEEK                           VARCHAR(25) NULL,
           COMPANY_ID                     VARCHAR(38) NOT NULL,
           COMPANY_MASTER_SID             INT NOT NULL,
           IDENTIFIER_CODE_QUALIFIER      VARCHAR(25) NULL,
           COMPANY_IDENTIFIER             VARCHAR(50) NULL,
           ITEM_ID                        VARCHAR(38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER VARCHAR(25) NULL,
           ITEM_IDENTIFIER                VARCHAR(50) NULL,
           UNITS_WITHDRAWN                NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN               NUMERIC(20, 6) NULL,
           PRICE                          NUMERIC(20, 6) NULL,
           CREATED_BY                     VARCHAR(50) NOT NULL,
           CREATED_DATE                   DATETIME NOT NULL,
           MODIFIED_BY                    VARCHAR(50) NOT NULL,
           MODIFIED_DATE                  DATETIME NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       VARCHAR(38) NOT NULL,
           SOURCE                         VARCHAR(50) NOT NULL,
           FORECAST_NAME                  VARCHAR(100) NOT NULL,
           FORECAST_VER                   VARCHAR(15) NOT NULL,
           COUNTRY                        VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY               VARCHAR(50) NOT NULL,
           [ACTION_FLAG]                  CHAR(1) NOT NULL,
           [ACTION_DATE]                  DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_INVENTORY_WD_PROJ_DT')
                      AND NAME = 'DF_HIST_INVENTORY_WD_PROJ_DT_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_INVENTORY_WD_PROJ_DT
        ADD CONSTRAINT DF_HIST_INVENTORY_WD_PROJ_DT_ACTION_DATE DEFAULT(GETDATE()) FOR ACTION_DATE
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_INVENTORY_WD_PROJ_DT'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS HIST_INVENTORY_WD_PROJ_DT.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_PROJ_DT'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_INVENTORY_WD_PROJ_DT
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_INVENTORY_WD_PROJ_DT'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS HIST_INVENTORY_WD_PROJ_DT.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_PROJ_DT'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_INVENTORY_WD_PROJ_DT
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO
--
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_PROJ_DT'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_INVENTORY_WD_PROJ_DT		
        ADD BUSINESS_UNIT INT
  END

GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_INVENTORY_WD_PROJ_DT' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_PROJ_DT_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_PROJ_DT_INS
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_PROJ_DT_INS
ON [DBO].INVENTORY_WD_PROJ_DT
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_PROJ_DT
                  (INVENTORY_WD_PROJ_DT_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   IDENTIFIER_CODE_QUALIFIER,
                   COMPANY_IDENTIFIER,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   PRICE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG,
				   BUSINESS_UNIT)
      SELECT INVENTORY_WD_PROJ_DT_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             IDENTIFIER_CODE_QUALIFIER,
             COMPANY_IDENTIFIER,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             PRICE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'A',
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_PROJ_DT_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_PROJ_DT_UPD
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_PROJ_DT_UPD
ON [DBO].INVENTORY_WD_PROJ_DT
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_PROJ_DT
                  (INVENTORY_WD_PROJ_DT_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   IDENTIFIER_CODE_QUALIFIER,
                   COMPANY_IDENTIFIER,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   PRICE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG],
				   BUSINESS_UNIT)
      SELECT INVENTORY_WD_PROJ_DT_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             IDENTIFIER_CODE_QUALIFIER,
             COMPANY_IDENTIFIER,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             PRICE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'C',
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_PROJ_DT_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_PROJ_DT_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_PROJ_DT_DEL
ON [DBO].INVENTORY_WD_PROJ_DT
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_PROJ_DT
                  (INVENTORY_WD_PROJ_DT_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   IDENTIFIER_CODE_QUALIFIER,
                   COMPANY_IDENTIFIER,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   PRICE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG],
				   BUSINESS_UNIT)
      SELECT INVENTORY_WD_PROJ_DT_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             IDENTIFIER_CODE_QUALIFIER,
             COMPANY_IDENTIFIER,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             PRICE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'D',
			 BUSINESS_UNIT
      FROM   DELETED
  END

GO

--------------------------------------INVENTORY_WD_PROJ_MAS--------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'INVENTORY_WD_PROJ_MAS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[INVENTORY_WD_PROJ_MAS]
        (
           INVENTORY_WD_PROJ_MAS_SID      INT IDENTITY(1, 1) NOT NULL,
           YEAR                           VARCHAR(5) NOT NULL,
           MONTH                          VARCHAR(25) NOT NULL,
           DAY                            VARCHAR(25) NULL,
           WEEK                           VARCHAR(25) NULL,
           ITEM_ID                        VARCHAR(38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER VARCHAR(25) NULL,
           ITEM_IDENTIFIER                VARCHAR(50) NULL,
           UNITS_WITHDRAWN                NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN               NUMERIC(20, 6) NULL,
           PRICE                          NUMERIC(22, 6) NULL,
           CREATED_BY                     VARCHAR(50) NOT NULL,
           CREATED_DATE                   DATETIME NOT NULL,
           MODIFIED_BY                    VARCHAR(50) NOT NULL,
           MODIFIED_DATE                  DATETIME NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       VARCHAR(38) NOT NULL,
           SOURCE                         VARCHAR(50) NOT NULL,
           FORECAST_NAME                  VARCHAR(100) NOT NULL,
           FORECAST_VER                   VARCHAR(15) NOT NULL,
           COUNTRY                        VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY               VARCHAR(50) NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_INVENTORY_WD_PROJ_MAS_INVENTORY_WD_PROJ_MAS_SID'
                      AND TABLE_NAME = 'INVENTORY_WD_PROJ_MAS')
  ALTER TABLE INVENTORY_WD_PROJ_MAS
    ADD CONSTRAINT PK_INVENTORY_WD_PROJ_MAS_INVENTORY_WD_PROJ_MAS_SID PRIMARY KEY (INVENTORY_WD_PROJ_MAS_SID)

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_PROJ_MAS')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_MAS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_MAS_RECORD_LOCK_STATUS DEFAULT(1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_PROJ_MAS')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_MAS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_MAS_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_PROJ_MAS')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_MAS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_MAS_CREATED_DATE DEFAULT(GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_PROJ_MAS')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_MAS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_MAS_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.INVENTORY_WD_PROJ_MAS')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_MAS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_MAS_MODIFIED_DATE DEFAULT(GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------------------------UNIQUE_CONSTRAINT-------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'INVENTORY_WD_PROJ_MAS')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('INVENTORY_WD_PROJ_MAS')
                            AND NAME = 'UQ_INVENTORY_WD_PROJ_MAS_YEAR_MONTH_DAY_WEEK_ITEM_ID_SOURCE_FORECAST_NAME_FORECAST_VER_COUNTRY_ORGANIZATION_KEY')
        BEGIN
            ALTER TABLE INVENTORY_WD_PROJ_MAS
              ADD CONSTRAINT UQ_INVENTORY_WD_PROJ_MAS_YEAR_MONTH_DAY_WEEK_ITEM_ID_SOURCE_FORECAST_NAME_FORECAST_VER_COUNTRY_ORGANIZATION_KEY UNIQUE(YEAR, MONTH, DAY, WEEK, ITEM_ID, SOURCE, FORECAST_NAME, FORECAST_VER, COUNTRY, ORGANIZATION_KEY)
        END
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'INVENTORY_WD_PROJ_MAS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS INVENTORY_WD_PROJ_MAS.CREATED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('INVENTORY_WD_PROJ_MAS')
                  AND NAME = 'DF_INVENTORY_WD_PROJ_MAS_CREATED_BY')
  BEGIN
      ALTER TABLE INVENTORY_WD_PROJ_MAS
        DROP CONSTRAINT DF_INVENTORY_WD_PROJ_MAS_CREATED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'INVENTORY_WD_PROJ_MAS'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE INVENTORY_WD_PROJ_MAS
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.INVENTORY_WD_PROJ_MAS')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_MAS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_MAS_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

---------------------------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'INVENTORY_WD_PROJ_MAS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS INVENTORY_WD_PROJ_MAS.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('INVENTORY_WD_PROJ_MAS')
                  AND NAME = 'DF_INVENTORY_WD_PROJ_MAS_MODIFIED_BY')
  BEGIN
      ALTER TABLE INVENTORY_WD_PROJ_MAS
        DROP CONSTRAINT DF_INVENTORY_WD_PROJ_MAS_MODIFIED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'INVENTORY_WD_PROJ_MAS'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE INVENTORY_WD_PROJ_MAS
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.INVENTORY_WD_PROJ_MAS')
                      AND NAME = 'DF_INVENTORY_WD_PROJ_MAS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].INVENTORY_WD_PROJ_MAS
        ADD CONSTRAINT DF_INVENTORY_WD_PROJ_MAS_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO

--
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'INVENTORY_WD_PROJ_MAS'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE INVENTORY_WD_PROJ_MAS		
        ADD BUSINESS_UNIT INT
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'INVENTORY_WD_PROJ_MAS' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------HIST_INVENTORY_WD_PROJ_MAS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_PROJ_MAS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_INVENTORY_WD_PROJ_MAS
        (
           INVENTORY_WD_PROJ_MAS_SID      INT NOT NULL,
           YEAR                           VARCHAR(5) NOT NULL,
           MONTH                          VARCHAR(25) NOT NULL,
           DAY                            VARCHAR(25) NULL,
           WEEK                           VARCHAR(25) NULL,
           ITEM_ID                        VARCHAR(38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER VARCHAR(25) NULL,
           ITEM_IDENTIFIER                VARCHAR(50) NULL,
           UNITS_WITHDRAWN                NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN               NUMERIC(20, 6) NULL,
           PRICE                          NUMERIC(22, 6) NULL,
           CREATED_BY                     VARCHAR(50) NOT NULL,
           CREATED_DATE                   DATETIME NOT NULL,
           MODIFIED_BY                    VARCHAR(50) NOT NULL,
           MODIFIED_DATE                  DATETIME NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       VARCHAR(38) NOT NULL,
           SOURCE                         VARCHAR(50) NOT NULL,
           FORECAST_NAME                  VARCHAR(100) NOT NULL,
           FORECAST_VER                   VARCHAR(15) NOT NULL,
           COUNTRY                        VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY               VARCHAR(50) NOT NULL,
           [ACTION_FLAG]                  CHAR(1) NOT NULL,
           [ACTION_DATE]                  DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_INVENTORY_WD_PROJ_MAS')
                      AND NAME = 'DF_HIST_INVENTORY_WD_PROJ_MAS_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_INVENTORY_WD_PROJ_MAS
        ADD CONSTRAINT DF_HIST_INVENTORY_WD_PROJ_MAS_ACTION_DATE DEFAULT(GETDATE()) FOR ACTION_DATE
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_INVENTORY_WD_PROJ_MAS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS HIST_INVENTORY_WD_PROJ_MAS.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_PROJ_MAS'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_INVENTORY_WD_PROJ_MAS
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_INVENTORY_WD_PROJ_MAS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS HIST_INVENTORY_WD_PROJ_MAS.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_PROJ_MAS'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_INVENTORY_WD_PROJ_MAS
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO 
--
--
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_INVENTORY_WD_PROJ_MAS'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_INVENTORY_WD_PROJ_MAS		
        ADD BUSINESS_UNIT INT
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_INVENTORY_WD_PROJ_MAS' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_PROJ_MAS_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_PROJ_MAS_INS
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_PROJ_MAS_INS
ON [DBO].INVENTORY_WD_PROJ_MAS
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_PROJ_MAS
                  (INVENTORY_WD_PROJ_MAS_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   PRICE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG,
				   BUSINESS_UNIT)
      SELECT INVENTORY_WD_PROJ_MAS_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             PRICE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'A',
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_PROJ_MAS_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_PROJ_MAS_UPD
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_PROJ_MAS_UPD
ON [DBO].INVENTORY_WD_PROJ_MAS
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_PROJ_MAS
                  (INVENTORY_WD_PROJ_MAS_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   PRICE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG],
				   BUSINESS_UNIT)
      SELECT INVENTORY_WD_PROJ_MAS_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             PRICE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'C',
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_INVENTORY_WD_PROJ_MAS_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_INVENTORY_WD_PROJ_MAS_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_INVENTORY_WD_PROJ_MAS_DEL
ON [DBO].INVENTORY_WD_PROJ_MAS
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_INVENTORY_WD_PROJ_MAS
                  (INVENTORY_WD_PROJ_MAS_SID,
                   YEAR,
                   MONTH,
                   DAY,
                   WEEK,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   UNITS_WITHDRAWN,
                   AMOUNT_WITHDRAWN,
                   PRICE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG],
				   BUSINESS_UNIT)
      SELECT INVENTORY_WD_PROJ_MAS_SID,
             YEAR,
             MONTH,
             DAY,
             WEEK,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             UNITS_WITHDRAWN,
             AMOUNT_WITHDRAWN,
             PRICE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'D',
			 BUSINESS_UNIT
      FROM   DELETED
  END

GO

-------------RETURNS_MASTER--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'RETURNS_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE RETURNS_MASTER
        (
           RETURNS_MASTER_SID          INT IDENTITY(1, 1) NOT NULL,
           RRESERVE_ID                 VARCHAR(50) NOT NULL,
           VERSION                     VARCHAR(50) NOT NULL,
           SKU                         VARCHAR(50) NOT NULL,
           ITEM_MASTER_SID             INT NOT NULL,
           BRAND                       VARCHAR(120) NOT NULL,
           BRAND_MASTER_SID            INT NOT NULL,
           DESCRIPTION                 VARCHAR(250) NOT NULL,
           ORIG_SALE_MONTH             DATETIME NOT NULL,
           ORIG_SALE_UNITS             NUMERIC(22, 6) NOT NULL,
           ORIG_SALE_DOLLARS           NUMERIC(22, 6) NOT NULL,
           ASP                         NUMERIC(22, 6) NOT NULL,
           CUM_RETURN_UNITS            NUMERIC(22, 6) NOT NULL,
           ACTUAL_RETURN_RATE          NUMERIC(22, 6) NOT NULL,
           WEIGHTED_AVG_MONTHS         NUMERIC(22, 6) NOT NULL,
           FIRST_RETURN                DATETIME NOT NULL,
           LAST_RETURN                 DATETIME NOT NULL,
           MAX_EXPIRED_MONTH           DATETIME NOT NULL,
           MAX_EXPIRED_MONS_PLUSCUTOFF DATETIME NOT NULL,
           RETURN_COMPLETE             VARCHAR(1) NOT NULL,
           CALC_USED                   VARCHAR(20) NOT NULL,
           ORIG_SALE_MONTH_CUT_OFF     DATETIME NOT NULL,
           EXPECTED_RETURN_RATE        NUMERIC(22, 6) NOT NULL,
           ESTIMATED_RETURN_UNITS      NUMERIC(22, 6) NOT NULL,
           ADJ_ESTIMATED_RETURN_UNITS  NUMERIC(22, 6) NOT NULL,
           ADJ_VALUE_AT_ORIG_ASP       NUMERIC(22, 6) NOT NULL,
           POS_ESTIMATED_RETURN_UNITS  NUMERIC(22, 6) NOT NULL,
           VALUE_AT_ORIG_ASP           NUMERIC(22, 6) NOT NULL,
           LOAD_DATE                   DATETIME NOT NULL,
           PAST_EXPIRATION             VARCHAR(1) NOT NULL,
           WITHIN_50QRTILE             VARCHAR(1) NOT NULL,
           MAXIMUM                     NUMERIC(22, 6) NOT NULL,
           PCT_75TH                    NUMERIC(22, 6) NOT NULL,
           PCT_50TH                    NUMERIC(22, 6) NOT NULL,
           PCT_25TH                    NUMERIC(22, 6) NOT NULL,
           UPPER_LIMIT                 NUMERIC(22, 6) NOT NULL,
           LOWER_LIMIT                 NUMERIC(22, 6) NOT NULL,
           ADD_CHG_DEL_INDICATOR       VARCHAR(5) NOT NULL,
           BATCH_ID                    VARCHAR(50) NOT NULL,
           SOURCE                      VARCHAR(50) NOT NULL,
           INBOUND_STATUS              CHAR(1) NULL,
           RECORD_LOCK_STATUS          BIT NOT NULL,
           CREATED_BY                  [VARCHAR](50) NULL,
           CREATED_DATE                [DATETIME] NULL,
           MODIFIED_BY                 [VARCHAR](50) NULL,
           MODIFIED_DATE               [DATETIME] NULL,
        )
  END

GO

-----------------------------------------ALTER COLUMNS FOR (NOT NULL TO NULL) RETURNS_MASTER----------------------------------------------
--FIRST_RETURN
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'FIRST_RETURN')
  BEGIN
      DROP STATISTICS RETURNS_MASTER.FIRST_RETURN
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RETURNS_MASTER'
                  AND COLUMN_NAME = 'FIRST_RETURN'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'DATETIME'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ALTER COLUMN FIRST_RETURN DATETIME NULL
  END

GO

--LAST_RETURN
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'LAST_RETURN')
  BEGIN
      DROP STATISTICS RETURNS_MASTER.LAST_RETURN
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RETURNS_MASTER'
                  AND COLUMN_NAME = 'LAST_RETURN'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'DATETIME'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ALTER COLUMN LAST_RETURN DATETIME NULL
  END

GO

--MAX_EXPIRED_MONTH
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MAX_EXPIRED_MONTH')
  BEGIN
      DROP STATISTICS RETURNS_MASTER.MAX_EXPIRED_MONTH
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RETURNS_MASTER'
                  AND COLUMN_NAME = 'MAX_EXPIRED_MONTH'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'DATETIME'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ALTER COLUMN MAX_EXPIRED_MONTH DATETIME NULL
  END

GO

--CALC_USED
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CALC_USED')
  BEGIN
      DROP STATISTICS RETURNS_MASTER.CALC_USED
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RETURNS_MASTER'
                  AND COLUMN_NAME = 'CALC_USED'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ALTER COLUMN CALC_USED VARCHAR(20) NULL
  END

GO

--ORIG_SALE_MONTH_CUT_OFF
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'ORIG_SALE_MONTH_CUT_OFF')
  BEGIN
      DROP STATISTICS RETURNS_MASTER.ORIG_SALE_MONTH_CUT_OFF
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RETURNS_MASTER'
                  AND COLUMN_NAME = 'ORIG_SALE_MONTH_CUT_OFF'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'DATETIME'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ALTER COLUMN ORIG_SALE_MONTH_CUT_OFF DATETIME NULL
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_RETURNS_MASTER_RETURNS_MASTER_SID'
                     AND TABLE_NAME = 'RETURNS_MASTER')
BEGIN
  ALTER TABLE RETURNS_MASTER
    ADD CONSTRAINT PK_RETURNS_MASTER_RETURNS_MASTER_SID PRIMARY KEY(RETURNS_MASTER_SID)
END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RETURNS_MASTER')
                      AND NAME = 'DF_RETURNS_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ADD CONSTRAINT DF_RETURNS_MASTER_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RETURNS_MASTER')
                      AND NAME = 'DF_RETURNS_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ADD CONSTRAINT DF_RETURNS_MASTER_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RETURNS_MASTER')
                      AND NAME = 'DF_RETURNS_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ADD CONSTRAINT DF_RETURNS_MASTER_CREATED_DATE DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RETURNS_MASTER')
                      AND NAME = 'DF_RETURNS_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ADD CONSTRAINT DF_RETURNS_MASTER_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RETURNS_MASTER')
                      AND NAME = 'DF_RETURNS_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ADD CONSTRAINT DF_RETURNS_MASTER_MODIFIED_DATE DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'RETURNS_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('RETURNS_MASTER')
                            AND NAME = 'UQ_RETURNS_MASTER_RRESERVE_ID_VERSION_SKU_ORIG_SALE_MONTH')
        BEGIN
            ALTER TABLE RETURNS_MASTER
              ADD CONSTRAINT UQ_RETURNS_MASTER_RRESERVE_ID_VERSION_SKU_ORIG_SALE_MONTH UNIQUE(RRESERVE_ID, VERSION, SKU, ORIG_SALE_MONTH)
        END
  END

GO
----------------RETURNS---------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS RETURNS_MASTER.CREATED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('RETURNS_MASTER')
                  AND NAME = 'DF_RETURNS_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE RETURNS_MASTER
        DROP CONSTRAINT DF_RETURNS_MASTER_CREATED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RETURNS_MASTER'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE RETURNS_MASTER
        ALTER COLUMN CREATED_BY INT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RETURNS_MASTER')
                      AND NAME = 'DF_RETURNS_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ADD CONSTRAINT DF_RETURNS_MASTER_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS RETURNS_MASTER.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('RETURNS_MASTER')
                  AND NAME = 'DF_RETURNS_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE RETURNS_MASTER
        DROP CONSTRAINT DF_RETURNS_MASTER_MODIFIED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RETURNS_MASTER'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE RETURNS_MASTER
        ALTER COLUMN MODIFIED_BY INT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RETURNS_MASTER')
                      AND NAME = 'DF_RETURNS_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].RETURNS_MASTER
        ADD CONSTRAINT DF_RETURNS_MASTER_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'RETURNS_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------HIST_RETURNS_MASTER---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_RETURNS_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE HIST_RETURNS_MASTER
        (
           RETURNS_MASTER_SID          INT,
           RRESERVE_ID                 VARCHAR(50) NOT NULL,
           VERSION                     VARCHAR(50) NOT NULL,
           SKU                         VARCHAR(50) NOT NULL,
           ITEM_MASTER_SID             INT NOT NULL,
           BRAND                       VARCHAR(120) NOT NULL,
           BRAND_MASTER_SID            INT NOT NULL,
           DESCRIPTION                 VARCHAR(250) NOT NULL,
           ORIG_SALE_MONTH             DATETIME NOT NULL,
           ORIG_SALE_UNITS             NUMERIC(22, 6) NOT NULL,
           ORIG_SALE_DOLLARS           NUMERIC(22, 6) NOT NULL,
           ASP                         NUMERIC(22, 6) NOT NULL,
           CUM_RETURN_UNITS            NUMERIC(22, 6) NOT NULL,
           ACTUAL_RETURN_RATE          NUMERIC(22, 6) NOT NULL,
           WEIGHTED_AVG_MONTHS         NUMERIC(22, 6) NOT NULL,
           FIRST_RETURN                DATETIME NOT NULL,
           LAST_RETURN                 DATETIME NOT NULL,
           MAX_EXPIRED_MONTH           DATETIME NOT NULL,
           MAX_EXPIRED_MONS_PLUSCUTOFF DATETIME NOT NULL,
           RETURN_COMPLETE             VARCHAR(1) NOT NULL,
           CALC_USED                   VARCHAR(20) NOT NULL,
           ORIG_SALE_MONTH_CUT_OFF     DATETIME NOT NULL,
           EXPECTED_RETURN_RATE        NUMERIC(22, 6) NOT NULL,
           ESTIMATED_RETURN_UNITS      NUMERIC(22, 6) NOT NULL,
           ADJ_ESTIMATED_RETURN_UNITS  NUMERIC(22, 6) NOT NULL,
           ADJ_VALUE_AT_ORIG_ASP       NUMERIC(22, 6) NOT NULL,
           POS_ESTIMATED_RETURN_UNITS  NUMERIC(22, 6) NOT NULL,
           VALUE_AT_ORIG_ASP           NUMERIC(22, 6) NOT NULL,
           LOAD_DATE                   DATETIME NOT NULL,
           PAST_EXPIRATION             VARCHAR(1) NOT NULL,
           WITHIN_50QRTILE             VARCHAR(1) NOT NULL,
           MAXIMUM                     NUMERIC(22, 6) NOT NULL,
           PCT_75TH                    NUMERIC(22, 6) NOT NULL,
           PCT_50TH                    NUMERIC(22, 6) NOT NULL,
           PCT_25TH                    NUMERIC(22, 6) NOT NULL,
           UPPER_LIMIT                 NUMERIC(22, 6) NOT NULL,
           LOWER_LIMIT                 NUMERIC(22, 6) NOT NULL,
           ADD_CHG_DEL_INDICATOR       VARCHAR(5) NOT NULL,
           BATCH_ID                    VARCHAR(50) NOT NULL,
           SOURCE                      VARCHAR(50) NOT NULL,
           INBOUND_STATUS              CHAR(1) NULL,
           RECORD_LOCK_STATUS          BIT NOT NULL,
           CREATED_BY                  [VARCHAR](50) NULL,
           CREATED_DATE                [DATETIME] NULL,
           MODIFIED_BY                 [VARCHAR](50) NULL,
           MODIFIED_DATE               [DATETIME] NULL,
           [ACTION_FLAG]               CHAR(1) NOT NULL,
           [ACTION_DATE]               DATETIME NOT NULL
        )
  END

GO

-----------------------------------------ALTER COLUMNS FOR (NOT NULL TO NULL) HIST_RETURNS_MASTER----------------------------------------------
--FIRST_RETURN
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'FIRST_RETURN')
  BEGIN
      DROP STATISTICS HIST_RETURNS_MASTER.FIRST_RETURN
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RETURNS_MASTER'
                  AND COLUMN_NAME = 'FIRST_RETURN'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'DATETIME'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].HIST_RETURNS_MASTER
        ALTER COLUMN FIRST_RETURN DATETIME NULL
  END

GO

--LAST_RETURN
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'LAST_RETURN')
  BEGIN
      DROP STATISTICS HIST_RETURNS_MASTER.LAST_RETURN
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RETURNS_MASTER'
                  AND COLUMN_NAME = 'LAST_RETURN'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'DATETIME'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].HIST_RETURNS_MASTER
        ALTER COLUMN LAST_RETURN DATETIME NULL
  END

GO

--MAX_EXPIRED_MONTH
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MAX_EXPIRED_MONTH')
  BEGIN
      DROP STATISTICS HIST_RETURNS_MASTER.MAX_EXPIRED_MONTH
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RETURNS_MASTER'
                  AND COLUMN_NAME = 'MAX_EXPIRED_MONTH'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'DATETIME'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].HIST_RETURNS_MASTER
        ALTER COLUMN MAX_EXPIRED_MONTH DATETIME NULL
  END

GO

--CALC_USED
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CALC_USED')
  BEGIN
      DROP STATISTICS HIST_RETURNS_MASTER.CALC_USED
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RETURNS_MASTER'
                  AND COLUMN_NAME = 'CALC_USED'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].HIST_RETURNS_MASTER
        ALTER COLUMN CALC_USED VARCHAR(20) NULL
  END

GO

--ORIG_SALE_MONTH_CUT_OFF
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'ORIG_SALE_MONTH_CUT_OFF')
  BEGIN
      DROP STATISTICS HIST_RETURNS_MASTER.ORIG_SALE_MONTH_CUT_OFF
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RETURNS_MASTER'
                  AND COLUMN_NAME = 'ORIG_SALE_MONTH_CUT_OFF'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'DATETIME'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [DBO].HIST_RETURNS_MASTER
        ALTER COLUMN ORIG_SALE_MONTH_CUT_OFF DATETIME NULL
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_RETURNS_MASTER')
                      AND NAME = 'DF_HIST_RETURNS_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_RETURNS_MASTER
        ADD CONSTRAINT DF_HIST_RETURNS_MASTER_ACTION_DATE DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS HIST_RETURNS_MASTER.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RETURNS_MASTER'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_RETURNS_MASTER
        ALTER COLUMN CREATED_BY INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_RETURNS_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS HIST_RETURNS_MASTER.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RETURNS_MASTER'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_RETURNS_MASTER
        ALTER COLUMN MODIFIED_BY INT NULL
  END

GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_RETURNS_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIST_RETURNS_MASTER_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_HIST_RETURNS_MASTER_INS
  END

GO

CREATE TRIGGER [DBO].TRG_HIST_RETURNS_MASTER_INS
ON [DBO].RETURNS_MASTER
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_RETURNS_MASTER
                  (RETURNS_MASTER_SID,
                   RRESERVE_ID,
                   VERSION,
                   SKU,
                   ITEM_MASTER_SID,
                   BRAND,
                   BRAND_MASTER_SID,
                   DESCRIPTION,
                   ORIG_SALE_MONTH,
                   ORIG_SALE_UNITS,
                   ORIG_SALE_DOLLARS,
                   ASP,
                   CUM_RETURN_UNITS,
                   ACTUAL_RETURN_RATE,
                   WEIGHTED_AVG_MONTHS,
                   FIRST_RETURN,
                   LAST_RETURN,
                   MAX_EXPIRED_MONTH,
                   MAX_EXPIRED_MONS_PLUSCUTOFF,
                   RETURN_COMPLETE,
                   CALC_USED,
                   ORIG_SALE_MONTH_CUT_OFF,
                   EXPECTED_RETURN_RATE,
                   ESTIMATED_RETURN_UNITS,
                   ADJ_ESTIMATED_RETURN_UNITS,
                   ADJ_VALUE_AT_ORIG_ASP,
                   POS_ESTIMATED_RETURN_UNITS,
                   VALUE_AT_ORIG_ASP,
                   LOAD_DATE,
                   PAST_EXPIRATION,
                   WITHIN_50QRTILE,
                   MAXIMUM,
                   PCT_75TH,
                   PCT_50TH,
                   PCT_25TH,
                   UPPER_LIMIT,
                   LOWER_LIMIT,
                   ADD_CHG_DEL_INDICATOR,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   [ACTION_FLAG])
      SELECT RETURNS_MASTER_SID,
             RRESERVE_ID,
             VERSION,
             SKU,
             ITEM_MASTER_SID,
             BRAND,
             BRAND_MASTER_SID,
             DESCRIPTION,
             ORIG_SALE_MONTH,
             ORIG_SALE_UNITS,
             ORIG_SALE_DOLLARS,
             ASP,
             CUM_RETURN_UNITS,
             ACTUAL_RETURN_RATE,
             WEIGHTED_AVG_MONTHS,
             FIRST_RETURN,
             LAST_RETURN,
             MAX_EXPIRED_MONTH,
             MAX_EXPIRED_MONS_PLUSCUTOFF,
             RETURN_COMPLETE,
             CALC_USED,
             ORIG_SALE_MONTH_CUT_OFF,
             EXPECTED_RETURN_RATE,
             ESTIMATED_RETURN_UNITS,
             ADJ_ESTIMATED_RETURN_UNITS,
             ADJ_VALUE_AT_ORIG_ASP,
             POS_ESTIMATED_RETURN_UNITS,
             VALUE_AT_ORIG_ASP,
             LOAD_DATE,
             PAST_EXPIRATION,
             WITHIN_50QRTILE,
             MAXIMUM,
             PCT_75TH,
             PCT_50TH,
             PCT_25TH,
             UPPER_LIMIT,
             LOWER_LIMIT,
             ADD_CHG_DEL_INDICATOR,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIST_RETURNS_MASTER_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_HIST_RETURNS_MASTER_UPD
  END

GO

CREATE TRIGGER [DBO].TRG_HIST_RETURNS_MASTER_UPD
ON [DBO].RETURNS_MASTER
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_RETURNS_MASTER
                  (RETURNS_MASTER_SID,
                   RRESERVE_ID,
                   VERSION,
                   SKU,
                   ITEM_MASTER_SID,
                   BRAND,
                   BRAND_MASTER_SID,
                   DESCRIPTION,
                   ORIG_SALE_MONTH,
                   ORIG_SALE_UNITS,
                   ORIG_SALE_DOLLARS,
                   ASP,
                   CUM_RETURN_UNITS,
                   ACTUAL_RETURN_RATE,
                   WEIGHTED_AVG_MONTHS,
                   FIRST_RETURN,
                   LAST_RETURN,
                   MAX_EXPIRED_MONTH,
                   MAX_EXPIRED_MONS_PLUSCUTOFF,
                   RETURN_COMPLETE,
                   CALC_USED,
                   ORIG_SALE_MONTH_CUT_OFF,
                   EXPECTED_RETURN_RATE,
                   ESTIMATED_RETURN_UNITS,
                   ADJ_ESTIMATED_RETURN_UNITS,
                   ADJ_VALUE_AT_ORIG_ASP,
                   POS_ESTIMATED_RETURN_UNITS,
                   VALUE_AT_ORIG_ASP,
                   LOAD_DATE,
                   PAST_EXPIRATION,
                   WITHIN_50QRTILE,
                   MAXIMUM,
                   PCT_75TH,
                   PCT_50TH,
                   PCT_25TH,
                   UPPER_LIMIT,
                   LOWER_LIMIT,
                   ADD_CHG_DEL_INDICATOR,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   [ACTION_FLAG])
      SELECT RETURNS_MASTER_SID,
             RRESERVE_ID,
             VERSION,
             SKU,
             ITEM_MASTER_SID,
             BRAND,
             BRAND_MASTER_SID,
             DESCRIPTION,
             ORIG_SALE_MONTH,
             ORIG_SALE_UNITS,
             ORIG_SALE_DOLLARS,
             ASP,
             CUM_RETURN_UNITS,
             ACTUAL_RETURN_RATE,
             WEIGHTED_AVG_MONTHS,
             FIRST_RETURN,
             LAST_RETURN,
             MAX_EXPIRED_MONTH,
             MAX_EXPIRED_MONS_PLUSCUTOFF,
             RETURN_COMPLETE,
             CALC_USED,
             ORIG_SALE_MONTH_CUT_OFF,
             EXPECTED_RETURN_RATE,
             ESTIMATED_RETURN_UNITS,
             ADJ_ESTIMATED_RETURN_UNITS,
             ADJ_VALUE_AT_ORIG_ASP,
             POS_ESTIMATED_RETURN_UNITS,
             VALUE_AT_ORIG_ASP,
             LOAD_DATE,
             PAST_EXPIRATION,
             WITHIN_50QRTILE,
             MAXIMUM,
             PCT_75TH,
             PCT_50TH,
             PCT_25TH,
             UPPER_LIMIT,
             LOWER_LIMIT,
             ADD_CHG_DEL_INDICATOR,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIST_RETURNS_MASTER_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_HIST_RETURNS_MASTER_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_HIST_RETURNS_MASTER_DEL
ON [DBO].RETURNS_MASTER
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_RETURNS_MASTER
                  (RETURNS_MASTER_SID,
                   RRESERVE_ID,
                   VERSION,
                   SKU,
                   ITEM_MASTER_SID,
                   BRAND,
                   BRAND_MASTER_SID,
                   DESCRIPTION,
                   ORIG_SALE_MONTH,
                   ORIG_SALE_UNITS,
                   ORIG_SALE_DOLLARS,
                   ASP,
                   CUM_RETURN_UNITS,
                   ACTUAL_RETURN_RATE,
                   WEIGHTED_AVG_MONTHS,
                   FIRST_RETURN,
                   LAST_RETURN,
                   MAX_EXPIRED_MONTH,
                   MAX_EXPIRED_MONS_PLUSCUTOFF,
                   RETURN_COMPLETE,
                   CALC_USED,
                   ORIG_SALE_MONTH_CUT_OFF,
                   EXPECTED_RETURN_RATE,
                   ESTIMATED_RETURN_UNITS,
                   ADJ_ESTIMATED_RETURN_UNITS,
                   ADJ_VALUE_AT_ORIG_ASP,
                   POS_ESTIMATED_RETURN_UNITS,
                   VALUE_AT_ORIG_ASP,
                   LOAD_DATE,
                   PAST_EXPIRATION,
                   WITHIN_50QRTILE,
                   MAXIMUM,
                   PCT_75TH,
                   PCT_50TH,
                   PCT_25TH,
                   UPPER_LIMIT,
                   LOWER_LIMIT,
                   ADD_CHG_DEL_INDICATOR,
                   BATCH_ID,
                   SOURCE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   [ACTION_FLAG])
      SELECT RETURNS_MASTER_SID,
             RRESERVE_ID,
             VERSION,
             SKU,
             ITEM_MASTER_SID,
             BRAND,
             BRAND_MASTER_SID,
             DESCRIPTION,
             ORIG_SALE_MONTH,
             ORIG_SALE_UNITS,
             ORIG_SALE_DOLLARS,
             ASP,
             CUM_RETURN_UNITS,
             ACTUAL_RETURN_RATE,
             WEIGHTED_AVG_MONTHS,
             FIRST_RETURN,
             LAST_RETURN,
             MAX_EXPIRED_MONTH,
             MAX_EXPIRED_MONS_PLUSCUTOFF,
             RETURN_COMPLETE,
             CALC_USED,
             ORIG_SALE_MONTH_CUT_OFF,
             EXPECTED_RETURN_RATE,
             ESTIMATED_RETURN_UNITS,
             ADJ_ESTIMATED_RETURN_UNITS,
             ADJ_VALUE_AT_ORIG_ASP,
             POS_ESTIMATED_RETURN_UNITS,
             VALUE_AT_ORIG_ASP,
             LOAD_DATE,
             PAST_EXPIRATION,
             WITHIN_50QRTILE,
             MAXIMUM,
             PCT_75TH,
             PCT_50TH,
             PCT_25TH,
             UPPER_LIMIT,
             LOWER_LIMIT,
             ADD_CHG_DEL_INDICATOR,
             BATCH_ID,
             SOURCE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             'D'
      FROM   DELETED
  END

GO

----------------------------------------------------CUSTOMER_GTS_ACTUAL------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_ACTUAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].CUSTOMER_GTS_ACTUAL
        (
           CUSTOMER_GTS_ACTUAL_SID INT IDENTITY(1, 1) NOT NULL,
           SALES_ID                VARCHAR(50) NOT NULL,
           ORGANIZATION_KEY        VARCHAR(100) NOT NULL,
           ITEM_ID                 VARCHAR(38) NOT NULL,
           ITEM_UOM                VARCHAR(50) NOT NULL,
           ORDER_NUMBER            VARCHAR(50) NOT NULL,
           INVOICE_NUMBER          VARCHAR(50) NOT NULL,
           INVOICE_LINE_NUMBER     VARCHAR(50) NOT NULL,
           INVOICE_DATE            DATETIME NOT NULL,
           QUANTITY                NUMERIC(22, 6) NOT NULL,
           LOT_NO                  VARCHAR(50) NULL,
           AMOUNT                  NUMERIC(22, 6) NOT NULL,
           CONTRACT_ID             VARCHAR(50) NULL,
           ACCOUNT_ID              VARCHAR(100) NOT NULL,
           ORDER_RECEIVED_DATE     DATETIME NULL,
           BATCH_ID                VARCHAR(50) NOT NULL,
           SOURCE                  VARCHAR(50) NOT NULL,
           CREATED_BY              VARCHAR(50) NOT NULL,
           CREATED_DATE            DATETIME NOT NULL,
           MODIFIED_BY             VARCHAR(50) NOT NULL,
           MODIFIED_DATE           DATETIME NOT NULL,
           INBOUND_STATUS          CHAR(1) NULL,
           PARENT_ACCOUNT_ID       VARCHAR(100) NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_CUSTOMER_GTS_ACTUAL_CUSTOMER_GTS_ACTUAL_SID'
                     AND TABLE_NAME = 'CUSTOMER_GTS_ACTUAL')
BEGIN
  ALTER TABLE CUSTOMER_GTS_ACTUAL
    ADD CONSTRAINT PK_CUSTOMER_GTS_ACTUAL_CUSTOMER_GTS_ACTUAL_SID PRIMARY KEY(CUSTOMER_GTS_ACTUAL_SID)
END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CUSTOMER_GTS_ACTUAL')
                      AND NAME = 'DF_CUSTOMER_GTS_ACTUAL_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_ACTUAL
        ADD CONSTRAINT DF_CUSTOMER_GTS_ACTUAL_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CUSTOMER_GTS_ACTUAL')
                      AND NAME = 'DF_CUSTOMER_GTS_ACTUAL_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_ACTUAL
        ADD CONSTRAINT DF_CUSTOMER_GTS_ACTUAL_CREATED_DATE DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CUSTOMER_GTS_ACTUAL')
                      AND NAME = 'DF_CUSTOMER_GTS_ACTUAL_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_ACTUAL
        ADD CONSTRAINT DF_CUSTOMER_GTS_ACTUAL_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CUSTOMER_GTS_ACTUAL')
                      AND NAME = 'DF_CUSTOMER_GTS_ACTUAL_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_ACTUAL
        ADD CONSTRAINT DF_CUSTOMER_GTS_ACTUAL_MODIFIED_DATE DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'CUSTOMER_GTS_ACTUAL')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('CUSTOMER_GTS_ACTUAL')
                            AND NAME = 'UQ_CUSTOMER_GTS_ACTUAL_ORGANIZATION_KEY_ITEM_ID_ORDER_NUMBER_INVOICE_NUMBER_INVOICE_LINE_NUMBER_INVOICE_DATE_ACCOUNT_ID')
        BEGIN
            ALTER TABLE CUSTOMER_GTS_ACTUAL
              ADD CONSTRAINT UQ_CUSTOMER_GTS_ACTUAL_ORGANIZATION_KEY_ITEM_ID_ORDER_NUMBER_INVOICE_NUMBER_INVOICE_LINE_NUMBER_INVOICE_DATE_ACCOUNT_ID UNIQUE(ORGANIZATION_KEY, ITEM_ID, ORDER_NUMBER, INVOICE_NUMBER, INVOICE_LINE_NUMBER, INVOICE_DATE, ACCOUNT_ID)
        END
  END

GO

-------------------------------------COLUMN ADDITION STARTS HERE------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_ACTUAL'
                      AND COLUMN_NAME = 'CUSTOMER_GTS_ACTUAL_INTF_ID')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_ACTUAL
        ADD CUSTOMER_GTS_ACTUAL_INTF_ID NUMERIC(38, 0) NOT NULL
  END

GO
--
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'CUSTOMER_GTS_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS CUSTOMER_GTS_ACTUAL.CREATED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('CUSTOMER_GTS_ACTUAL')
                  AND NAME = 'DF_CUSTOMER_GTS_ACTUAL_CREATED_BY')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_ACTUAL
        DROP CONSTRAINT DF_CUSTOMER_GTS_ACTUAL_CREATED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'CUSTOMER_GTS_ACTUAL'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_ACTUAL
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.CUSTOMER_GTS_ACTUAL')
                      AND NAME = 'DF_CUSTOMER_GTS_ACTUAL_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_ACTUAL
        ADD CONSTRAINT DF_CUSTOMER_GTS_ACTUAL_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

-----------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'CUSTOMER_GTS_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS CUSTOMER_GTS_ACTUAL.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('CUSTOMER_GTS_ACTUAL')
                  AND NAME = 'DF_CUSTOMER_GTS_ACTUAL_MODIFIED_BY')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_ACTUAL
        DROP CONSTRAINT DF_CUSTOMER_GTS_ACTUAL_MODIFIED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'CUSTOMER_GTS_ACTUAL'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_ACTUAL
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.CUSTOMER_GTS_ACTUAL')
                      AND NAME = 'DF_CUSTOMER_GTS_ACTUAL_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_ACTUAL
        ADD CONSTRAINT DF_CUSTOMER_GTS_ACTUAL_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO
-------------------------------------COLUMN ADDITION ENDS HERE------------------------------------------
-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'CUSTOMER_GTS_ACTUAL'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-----------------------------------------------------HIST_CUSTOMER_GTS_ACTUAL----------------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_CUSTOMER_GTS_ACTUAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_CUSTOMER_GTS_ACTUAL
        (
           CUSTOMER_GTS_ACTUAL_SID INT NOT NULL,
           SALES_ID                VARCHAR(50) NOT NULL,
           ORGANIZATION_KEY        VARCHAR(100) NOT NULL,
           ITEM_ID                 VARCHAR(38) NOT NULL,
           ITEM_UOM                VARCHAR(50) NOT NULL,
           ORDER_NUMBER            VARCHAR(50) NOT NULL,
           INVOICE_NUMBER          VARCHAR(50) NOT NULL,
           INVOICE_LINE_NUMBER     VARCHAR(50) NOT NULL,
           INVOICE_DATE            DATETIME NOT NULL,
           QUANTITY                NUMERIC(22, 6) NOT NULL,
           LOT_NO                  VARCHAR(50) NULL,
           AMOUNT                  NUMERIC(22, 6) NOT NULL,
           CONTRACT_ID             VARCHAR(50) NULL,
           ACCOUNT_ID              VARCHAR(100) NOT NULL,
           ORDER_RECEIVED_DATE     DATETIME NULL,
           BATCH_ID                VARCHAR(50) NOT NULL,
           SOURCE                  VARCHAR(50) NOT NULL,
           CREATED_BY              VARCHAR(50) NOT NULL,
           CREATED_DATE            DATETIME NOT NULL,
           MODIFIED_BY             VARCHAR(50) NOT NULL,
           MODIFIED_DATE           DATETIME NOT NULL,
           INBOUND_STATUS          CHAR(1) NULL,
           PARENT_ACCOUNT_ID       VARCHAR(100) NULL,
           ACTION_FLAG             CHAR(1) NOT NULL,
           ACTION_DATE             DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_CUSTOMER_GTS_ACTUAL')
                      AND NAME = 'DF_HIST_CUSTOMER_GTS_ACTUAL_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_CUSTOMER_GTS_ACTUAL
        ADD CONSTRAINT DF_HIST_CUSTOMER_GTS_ACTUAL_ACTION_DATE DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

---------------------------------CLOUMN ADDITION STARTS HERE----------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_CUSTOMER_GTS_ACTUAL'
                      AND COLUMN_NAME = 'CUSTOMER_GTS_ACTUAL_INTF_ID')
  BEGIN
      ALTER TABLE HIST_CUSTOMER_GTS_ACTUAL
        ADD CUSTOMER_GTS_ACTUAL_INTF_ID NUMERIC(38, 0) NOT NULL
  END

GO
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_CUSTOMER_GTS_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS HIST_CUSTOMER_GTS_ACTUAL.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_CUSTOMER_GTS_ACTUAL'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_CUSTOMER_GTS_ACTUAL
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_CUSTOMER_GTS_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS HIST_CUSTOMER_GTS_ACTUAL.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_CUSTOMER_GTS_ACTUAL'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_CUSTOMER_GTS_ACTUAL
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO
-------------------------------COLUMN ADDITION END HERE--------------------------------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_CUSTOMER_GTS_ACTUAL'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CUSTOMER_GTS_ACTUAL_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_CUSTOMER_GTS_ACTUAL_INS
  END

GO

CREATE TRIGGER [DBO].TRG_CUSTOMER_GTS_ACTUAL_INS
ON [DBO].CUSTOMER_GTS_ACTUAL
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_CUSTOMER_GTS_ACTUAL
                  (CUSTOMER_GTS_ACTUAL_SID,
                   SALES_ID,
                   ORGANIZATION_KEY,
                   ITEM_ID,
                   ITEM_UOM,
                   ORDER_NUMBER,
                   INVOICE_NUMBER,
                   INVOICE_LINE_NUMBER,
                   INVOICE_DATE,
                   QUANTITY,
                   LOT_NO,
                   AMOUNT,
                   CONTRACT_ID,
                   ACCOUNT_ID,
                   ORDER_RECEIVED_DATE,
                   BATCH_ID,
                   SOURCE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   PARENT_ACCOUNT_ID,
                   CUSTOMER_GTS_ACTUAL_INTF_ID,
                   ACTION_FLAG)
      SELECT CUSTOMER_GTS_ACTUAL_SID,
             SALES_ID,
             ORGANIZATION_KEY,
             ITEM_ID,
             ITEM_UOM,
             ORDER_NUMBER,
             INVOICE_NUMBER,
             INVOICE_LINE_NUMBER,
             INVOICE_DATE,
             QUANTITY,
             LOT_NO,
             AMOUNT,
             CONTRACT_ID,
             ACCOUNT_ID,
             ORDER_RECEIVED_DATE,
             BATCH_ID,
             SOURCE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             PARENT_ACCOUNT_ID,
             CUSTOMER_GTS_ACTUAL_INTF_ID,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CUSTOMER_GTS_ACTUAL_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_CUSTOMER_GTS_ACTUAL_UPD
  END

GO

CREATE TRIGGER [DBO].TRG_CUSTOMER_GTS_ACTUAL_UPD
ON [DBO].CUSTOMER_GTS_ACTUAL
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_CUSTOMER_GTS_ACTUAL
                  (CUSTOMER_GTS_ACTUAL_SID,
                   SALES_ID,
                   ORGANIZATION_KEY,
                   ITEM_ID,
                   ITEM_UOM,
                   ORDER_NUMBER,
                   INVOICE_NUMBER,
                   INVOICE_LINE_NUMBER,
                   INVOICE_DATE,
                   QUANTITY,
                   LOT_NO,
                   AMOUNT,
                   CONTRACT_ID,
                   ACCOUNT_ID,
                   ORDER_RECEIVED_DATE,
                   BATCH_ID,
                   SOURCE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   PARENT_ACCOUNT_ID,
                   CUSTOMER_GTS_ACTUAL_INTF_ID,
                   ACTION_FLAG)
      SELECT CUSTOMER_GTS_ACTUAL_SID,
             SALES_ID,
             ORGANIZATION_KEY,
             ITEM_ID,
             ITEM_UOM,
             ORDER_NUMBER,
             INVOICE_NUMBER,
             INVOICE_LINE_NUMBER,
             INVOICE_DATE,
             QUANTITY,
             LOT_NO,
             AMOUNT,
             CONTRACT_ID,
             ACCOUNT_ID,
             ORDER_RECEIVED_DATE,
             BATCH_ID,
             SOURCE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             PARENT_ACCOUNT_ID,
             CUSTOMER_GTS_ACTUAL_INTF_ID,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CUSTOMER_GTS_ACTUAL_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_CUSTOMER_GTS_ACTUAL_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_CUSTOMER_GTS_ACTUAL_DEL
ON [DBO].CUSTOMER_GTS_ACTUAL
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_CUSTOMER_GTS_ACTUAL
                  (CUSTOMER_GTS_ACTUAL_SID,
                   SALES_ID,
                   ORGANIZATION_KEY,
                   ITEM_ID,
                   ITEM_UOM,
                   ORDER_NUMBER,
                   INVOICE_NUMBER,
                   INVOICE_LINE_NUMBER,
                   INVOICE_DATE,
                   QUANTITY,
                   LOT_NO,
                   AMOUNT,
                   CONTRACT_ID,
                   ACCOUNT_ID,
                   ORDER_RECEIVED_DATE,
                   BATCH_ID,
                   SOURCE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   PARENT_ACCOUNT_ID,
                   CUSTOMER_GTS_ACTUAL_INTF_ID,
                   ACTION_FLAG)
      SELECT CUSTOMER_GTS_ACTUAL_SID,
             SALES_ID,
             ORGANIZATION_KEY,
             ITEM_ID,
             ITEM_UOM,
             ORDER_NUMBER,
             INVOICE_NUMBER,
             INVOICE_LINE_NUMBER,
             INVOICE_DATE,
             QUANTITY,
             LOT_NO,
             AMOUNT,
             CONTRACT_ID,
             ACCOUNT_ID,
             ORDER_RECEIVED_DATE,
             BATCH_ID,
             SOURCE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             PARENT_ACCOUNT_ID,
             CUSTOMER_GTS_ACTUAL_INTF_ID,
             'D'
      FROM   DELETED
  END

GO

-----------------------------------------CUSTOMER_FORECAST-----------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_FORECAST'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[CUSTOMER_GTS_FORECAST]
        (
           CUSTOMER_GTS_FORECAST_SID INT IDENTITY(1, 1) NOT NULL,
           FORECAST_YEAR             VARCHAR(5) NOT NULL,
           FORECAST_MONTH            VARCHAR(25) NOT NULL,
           ITEM_ID                   VARCHAR(50) NOT NULL,
           ITEM_MASTER_SID           INT NOT NULL,
           COMPANY_ID                VARCHAR(50) NOT NULL,
           COMPANY_MASTER_SID        INT NOT NULL,
           UNITS                     NUMERIC(22, 6) NOT NULL,
           PRICE_TYPE                VARCHAR(50) NULL,
           PRICE                     NUMERIC(22, 6) NULL,
           SALES_AMOUNT              NUMERIC(22, 6) NULL,
           SALES_INCLUSION           VARCHAR(10) NULL,
           DEDUCTION_ID              VARCHAR(50) NULL,
           DEDUCTION_CATEGORY        INT NULL,
           DEDUCTION_TYPE            INT NULL,
           DEDUCTION_PROGRAM_TYPE    INT NULL,
           ADJUSTMENT_CODE           VARCHAR(50) NULL,
           DEDUCTION_RATE            NUMERIC(22, 6) NULL,
           DEDUCTION_AMOUNT          NUMERIC(22, 6) NULL,
           DEDUCTION_INCLUSION       VARCHAR(10) NULL,
           FORECAST_VALUE_TYPE       VARCHAR(50) NULL,
           BRAND                     VARCHAR(50) NULL,
           BRAND_MASTER_SID          INT NULL,
           SEGMENT                   VARCHAR(100) NULL,
           ADD_CHG_DEL_INDICATOR     VARCHAR(10) NOT NULL,
           CREATED_BY                VARCHAR(50) NOT NULL,
           CREATED_DATE              DATETIME NOT NULL,
           MODIFIED_BY               VARCHAR(50) NOT NULL,
           MODIFIED_DATE             DATETIME NOT NULL,
           BATCH_ID                  VARCHAR(38) NOT NULL,
           SOURCE                    VARCHAR(50) NOT NULL,
           FORECAST_VER              VARCHAR(15) NOT NULL,
           COUNTRY                   VARCHAR(25) NOT NULL,
           FORECAST_NAME             VARCHAR(100) NOT NULL,
           FORECAST_DATE             DATETIME NULL,
           INBOUND_STATUS            CHAR(1) NULL,
           RECORD_LOCK_STATUS        BIT NOT NULL
        )
  END

-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_CUSTOMER_GTS_FORECAST_CUSTOMER_GTS_FORECAST_SID'
                      AND TABLE_NAME = 'CUSTOMER_GTS_FORECAST')
  ALTER TABLE CUSTOMER_GTS_FORECAST
    ADD CONSTRAINT PK_CUSTOMER_GTS_FORECAST_CUSTOMER_GTS_FORECAST_SID PRIMARY KEY (CUSTOMER_GTS_FORECAST_SID)

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CUSTOMER_GTS_FORECAST')
                      AND NAME = 'DF_CUSTOMER_GTS_FORECAST_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_FORECAST
        ADD CONSTRAINT DF_CUSTOMER_GTS_FORECAST_RECORD_LOCK_STATUS DEFAULT(1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CUSTOMER_GTS_FORECAST')
                      AND NAME = 'DF_CUSTOMER_GTS_FORECAST_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_FORECAST
        ADD CONSTRAINT DF_CUSTOMER_GTS_FORECAST_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CUSTOMER_GTS_FORECAST')
                      AND NAME = 'DF_CUSTOMER_GTS_FORECAST_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_FORECAST
        ADD CONSTRAINT DF_CUSTOMER_GTS_FORECAST_CREATED_DATE DEFAULT(GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CUSTOMER_GTS_FORECAST')
                      AND NAME = 'DF_CUSTOMER_GTS_FORECAST_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_FORECAST
        ADD CONSTRAINT DF_CUSTOMER_GTS_FORECAST_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.CUSTOMER_GTS_FORECAST')
                      AND NAME = 'DF_CUSTOMER_GTS_FORECAST_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_FORECAST
        ADD CONSTRAINT DF_CUSTOMER_GTS_FORECAST_MODIFIED_DATE DEFAULT(GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'CUSTOMER_GTS_FORECAST')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('CUSTOMER_GTS_FORECAST')
                            AND NAME = 'UQ_CUSTOMER_GTS_FORECAST_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COMPANY_ID_ACDI_BATCH_ID_SOURCE_FORECAST_VER_COUNTRY_FORECAST_NAME')
        BEGIN
            ALTER TABLE CUSTOMER_GTS_FORECAST
              ADD CONSTRAINT UQ_CUSTOMER_GTS_FORECAST_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COMPANY_ID_ACDI_BATCH_ID_SOURCE_FORECAST_VER_COUNTRY_FORECAST_NAME UNIQUE(FORECAST_YEAR, FORECAST_MONTH, ITEM_ID, COMPANY_ID, ADD_CHG_DEL_INDICATOR, BATCH_ID, SOURCE, FORECAST_VER, COUNTRY, FORECAST_NAME)
        END
  END

GO

--------------------------------------------------------COLUMN ADDITION STARTS HERE----------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_FORECAST'
                      AND COLUMN_NAME = 'CUSTOMER_GTS_FORECAST_INTF_ID')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_FORECAST
        ADD CUSTOMER_GTS_FORECAST_INTF_ID NUMERIC(38, 0) NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'CUSTOMER_GTS_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS CUSTOMER_GTS_FORECAST.CREATED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('CUSTOMER_GTS_FORECAST')
                  AND NAME = 'DF_CUSTOMER_GTS_FORECAST_CREATED_BY')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_FORECAST
        DROP CONSTRAINT DF_CUSTOMER_GTS_FORECAST_CREATED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'CUSTOMER_GTS_FORECAST'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_FORECAST
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.CUSTOMER_GTS_FORECAST')
                      AND NAME = 'DF_CUSTOMER_GTS_FORECAST_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_FORECAST
        ADD CONSTRAINT DF_CUSTOMER_GTS_FORECAST_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

--------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'CUSTOMER_GTS_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS CUSTOMER_GTS_FORECAST.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('CUSTOMER_GTS_FORECAST')
                  AND NAME = 'DF_CUSTOMER_GTS_FORECAST_MODIFIED_BY')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_FORECAST
        DROP CONSTRAINT DF_CUSTOMER_GTS_FORECAST_MODIFIED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'CUSTOMER_GTS_FORECAST'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_FORECAST
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.CUSTOMER_GTS_FORECAST')
                      AND NAME = 'DF_CUSTOMER_GTS_FORECAST_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].CUSTOMER_GTS_FORECAST
        ADD CONSTRAINT DF_CUSTOMER_GTS_FORECAST_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO
--
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CUSTOMER_GTS_FORECAST'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE CUSTOMER_GTS_FORECAST		
        ADD BUSINESS_UNIT INT
  END

GO
--------------------------------------------------------COLUMN ADDITION ENDS HERE----------------------------------
-------------STATISTICS-----------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'CUSTOMER_GTS_FORECAST' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

------------------------HIST_CUSTOMER_FORECAST------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_CUSTOMER_GTS_FORECAST'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_CUSTOMER_GTS_FORECAST]
        (
           CUSTOMER_GTS_FORECAST_SID INT NOT NULL,
           FORECAST_YEAR             VARCHAR(5) NOT NULL,
           FORECAST_MONTH            VARCHAR(25) NOT NULL,
           ITEM_ID                   VARCHAR(50) NOT NULL,
           ITEM_MASTER_SID           INT NOT NULL,
           COMPANY_ID                VARCHAR(50) NOT NULL,
           COMPANY_MASTER_SID        INT NOT NULL,
           UNITS                     NUMERIC(22, 6) NOT NULL,
           PRICE_TYPE                VARCHAR(50) NULL,
           PRICE                     NUMERIC(22, 6) NULL,
           SALES_AMOUNT              NUMERIC(22, 6) NULL,
           SALES_INCLUSION           VARCHAR(10) NULL,
           DEDUCTION_ID              VARCHAR(50) NULL,
           DEDUCTION_CATEGORY        INT NULL,
           DEDUCTION_TYPE            INT NULL,
           DEDUCTION_PROGRAM_TYPE    INT NULL,
           ADJUSTMENT_CODE           VARCHAR(50) NULL,
           DEDUCTION_RATE            NUMERIC(22, 6) NULL,
           DEDUCTION_AMOUNT          NUMERIC(22, 6) NULL,
           DEDUCTION_INCLUSION       VARCHAR(10) NULL,
           FORECAST_VALUE_TYPE       VARCHAR(50) NULL,
           BRAND                     VARCHAR(50) NULL,
           BRAND_MASTER_SID          INT NULL,
           SEGMENT                   VARCHAR(100) NULL,
           ADD_CHG_DEL_INDICATOR     VARCHAR(10) NOT NULL,
           CREATED_BY                VARCHAR(50) NOT NULL,
           CREATED_DATE              DATETIME NOT NULL,
           MODIFIED_BY               VARCHAR(50) NOT NULL,
           MODIFIED_DATE             DATETIME NOT NULL,
           BATCH_ID                  VARCHAR(38) NOT NULL,
           SOURCE                    VARCHAR(50) NOT NULL,
           FORECAST_VER              VARCHAR(15) NOT NULL,
           COUNTRY                   VARCHAR(25) NOT NULL,
           FORECAST_NAME             VARCHAR(100) NOT NULL,
           FORECAST_DATE             DATETIME NULL,
           INBOUND_STATUS            CHAR(1) NULL,
           RECORD_LOCK_STATUS        BIT NOT NULL,
           [ACTION_FLAG]             CHAR(1) NOT NULL,
           [ACTION_DATE]             DATETIME NOT NULL
        )
  END
GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_CUSTOMER_GTS_FORECAST')
                      AND NAME = 'DF_HIST_CUSTOMER_GTS_FORECAST_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_CUSTOMER_GTS_FORECAST
        ADD CONSTRAINT DF_HIST_CUSTOMER_GTS_FORECAST_ACTION_DATE DEFAULT(GETDATE()) FOR ACTION_DATE
  END

GO

------------------------------------COLUMN ADDTION START HERE---------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_CUSTOMER_GTS_FORECAST'
                      AND COLUMN_NAME = 'CUSTOMER_GTS_FORECAST_INTF_ID')
  BEGIN
      ALTER TABLE HIST_CUSTOMER_GTS_FORECAST
        ADD CUSTOMER_GTS_FORECAST_INTF_ID NUMERIC(38, 0) NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_CUSTOMER_GTS_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS HIST_CUSTOMER_GTS_FORECAST.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_CUSTOMER_GTS_FORECAST'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_CUSTOMER_GTS_FORECAST
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_CUSTOMER_GTS_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS HIST_CUSTOMER_GTS_FORECAST.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_CUSTOMER_GTS_FORECAST'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_CUSTOMER_GTS_FORECAST
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO
--
--
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_CUSTOMER_GTS_FORECAST'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_CUSTOMER_GTS_FORECAST		
        ADD BUSINESS_UNIT INT
  END

GO

------------------------------------COLUMN ADDTION START HERE---------------------------------------
---------------------------------------------STATISTICS-------------------------------------------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_CUSTOMER_GTS_FORECAST' --TABLE NAME
SET @SCHEMANAME1 = 'DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CUSTOMER_GTS_FORECAST_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_CUSTOMER_GTS_FORECAST_INS
  END

GO

CREATE TRIGGER [DBO].TRG_CUSTOMER_GTS_FORECAST_INS
ON [DBO].CUSTOMER_GTS_FORECAST
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_CUSTOMER_GTS_FORECAST
                  (CUSTOMER_GTS_FORECAST_SID,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   UNITS,
                   PRICE_TYPE,
                   PRICE,
                   SALES_AMOUNT,
                   SALES_INCLUSION,
                   DEDUCTION_ID,
                   DEDUCTION_CATEGORY,
                   DEDUCTION_TYPE,
                   DEDUCTION_PROGRAM_TYPE,
                   ADJUSTMENT_CODE,
                   DEDUCTION_RATE,
                   DEDUCTION_AMOUNT,
                   DEDUCTION_INCLUSION,
                   FORECAST_VALUE_TYPE,
                   BRAND,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   ADD_CHG_DEL_INDICATOR,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_VER,
                   COUNTRY,
                   FORECAST_NAME,
                   FORECAST_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   CUSTOMER_GTS_FORECAST_INTF_ID,
                   [ACTION_FLAG],
				   BUSINESS_UNIT)
      SELECT CUSTOMER_GTS_FORECAST_SID,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             UNITS,
             PRICE_TYPE,
             PRICE,
             SALES_AMOUNT,
             SALES_INCLUSION,
             DEDUCTION_ID,
             DEDUCTION_CATEGORY,
             DEDUCTION_TYPE,
             DEDUCTION_PROGRAM_TYPE,
             ADJUSTMENT_CODE,
             DEDUCTION_RATE,
             DEDUCTION_AMOUNT,
             DEDUCTION_INCLUSION,
             FORECAST_VALUE_TYPE,
             BRAND,
             BRAND_MASTER_SID,
             SEGMENT,
             ADD_CHG_DEL_INDICATOR,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             BATCH_ID,
             SOURCE,
             FORECAST_VER,
             COUNTRY,
             FORECAST_NAME,
             FORECAST_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             CUSTOMER_GTS_FORECAST_INTF_ID,
             'A',
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CUSTOMER_GTS_FORECAST_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_CUSTOMER_GTS_FORECAST_UPD
  END

GO

CREATE TRIGGER [DBO].TRG_CUSTOMER_GTS_FORECAST_UPD
ON [DBO].CUSTOMER_GTS_FORECAST
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_CUSTOMER_GTS_FORECAST
                  (CUSTOMER_GTS_FORECAST_SID,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   UNITS,
                   PRICE_TYPE,
                   PRICE,
                   SALES_AMOUNT,
                   SALES_INCLUSION,
                   DEDUCTION_ID,
                   DEDUCTION_CATEGORY,
                   DEDUCTION_TYPE,
                   DEDUCTION_PROGRAM_TYPE,
                   ADJUSTMENT_CODE,
                   DEDUCTION_RATE,
                   DEDUCTION_AMOUNT,
                   DEDUCTION_INCLUSION,
                   FORECAST_VALUE_TYPE,
                   BRAND,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   ADD_CHG_DEL_INDICATOR,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_VER,
                   COUNTRY,
                   FORECAST_NAME,
                   FORECAST_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   CUSTOMER_GTS_FORECAST_INTF_ID,
                   [ACTION_FLAG],
				   BUSINESS_UNIT)
      SELECT CUSTOMER_GTS_FORECAST_SID,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             UNITS,
             PRICE_TYPE,
             PRICE,
             SALES_AMOUNT,
             SALES_INCLUSION,
             DEDUCTION_ID,
             DEDUCTION_CATEGORY,
             DEDUCTION_TYPE,
             DEDUCTION_PROGRAM_TYPE,
             ADJUSTMENT_CODE,
             DEDUCTION_RATE,
             DEDUCTION_AMOUNT,
             DEDUCTION_INCLUSION,
             FORECAST_VALUE_TYPE,
             BRAND,
             BRAND_MASTER_SID,
             SEGMENT,
             ADD_CHG_DEL_INDICATOR,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             BATCH_ID,
             SOURCE,
             FORECAST_VER,
             COUNTRY,
             FORECAST_NAME,
             FORECAST_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             CUSTOMER_GTS_FORECAST_INTF_ID,
             'C',
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CUSTOMER_GTS_FORECAST_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_CUSTOMER_GTS_FORECAST_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_CUSTOMER_GTS_FORECAST_DEL
ON [DBO].CUSTOMER_GTS_FORECAST
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_CUSTOMER_GTS_FORECAST
                  (CUSTOMER_GTS_FORECAST_SID,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   COMPANY_ID,
                   COMPANY_MASTER_SID,
                   UNITS,
                   PRICE_TYPE,
                   PRICE,
                   SALES_AMOUNT,
                   SALES_INCLUSION,
                   DEDUCTION_ID,
                   DEDUCTION_CATEGORY,
                   DEDUCTION_TYPE,
                   DEDUCTION_PROGRAM_TYPE,
                   ADJUSTMENT_CODE,
                   DEDUCTION_RATE,
                   DEDUCTION_AMOUNT,
                   DEDUCTION_INCLUSION,
                   FORECAST_VALUE_TYPE,
                   BRAND,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   ADD_CHG_DEL_INDICATOR,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_VER,
                   COUNTRY,
                   FORECAST_NAME,
                   FORECAST_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   CUSTOMER_GTS_FORECAST_INTF_ID,
                   [ACTION_FLAG],
				   BUSINESS_UNIT)
      SELECT CUSTOMER_GTS_FORECAST_SID,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             COMPANY_ID,
             COMPANY_MASTER_SID,
             UNITS,
             PRICE_TYPE,
             PRICE,
             SALES_AMOUNT,
             SALES_INCLUSION,
             DEDUCTION_ID,
             DEDUCTION_CATEGORY,
             DEDUCTION_TYPE,
             DEDUCTION_PROGRAM_TYPE,
             ADJUSTMENT_CODE,
             DEDUCTION_RATE,
             DEDUCTION_AMOUNT,
             DEDUCTION_INCLUSION,
             FORECAST_VALUE_TYPE,
             BRAND,
             BRAND_MASTER_SID,
             SEGMENT,
             ADD_CHG_DEL_INDICATOR,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             BATCH_ID,
             SOURCE,
             FORECAST_VER,
             COUNTRY,
             FORECAST_NAME,
             FORECAST_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             CUSTOMER_GTS_FORECAST_INTF_ID,
             'D',
			 BUSINESS_UNIT
      FROM   DELETED
  END

GO

-------------ADJUSTED_DEMAND_ACTUAL--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ADJUSTED_DEMAND_ACTUAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].ADJUSTED_DEMAND_ACTUAL
        (
           ADJUSTED_DEMAND_ACTUAL_SID     INT IDENTITY(1, 1) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           FORECAST_YEAR                  [VARCHAR](5) NOT NULL,
           FORECAST_MONTH                 [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
           BRAND_MASTER_SID               INT NULL,
           SEGMENT                        [VARCHAR](50) NULL,
           MARKET_SIZE_UNITS              NUMERIC (22, 6) NOT NULL,
           MARKET_SHARE_RATIO             NUMERIC(25) NOT NULL,
           MARKET_SHARE_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NULL,
           GROSS_UNITS                    NUMERIC (22, 6) NOT NULL,
           GROSS_PRICE                    NUMERIC (22, 6) NOT NULL,
           GROSS_AMOUNT                   NUMERIC (22, 6) NOT NULL,
           NET_SALES_PRICE                NUMERIC (22, 6) NOT NULL,
           NET_SALES_AMOUNT               NUMERIC (22, 6) NOT NULL,
           CREATED_BY                     [VARCHAR](50) NOT NULL,
           CREATED_DATE                   [DATETIME] NOT NULL,
           MODIFIED_BY                    [VARCHAR](50) NOT NULL,
           MODIFIED_DATE                  [DATETIME] NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_ADJUSTED_DEMAND_ACTUAL_ADJUSTED_DEMAND_ACTUAL_SID'
                     AND TABLE_NAME = 'ADJUSTED_DEMAND_ACTUAL')
BEGIN
  ALTER TABLE ADJUSTED_DEMAND_ACTUAL
    ADD CONSTRAINT PK_ADJUSTED_DEMAND_ACTUAL_ADJUSTED_DEMAND_ACTUAL_SID PRIMARY KEY(ADJUSTED_DEMAND_ACTUAL_SID)
END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ADJUSTED_DEMAND_ACTUAL')
                      AND NAME = 'DF_ADJUSTED_DEMAND_ACTUAL_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_ACTUAL
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_ACTUAL_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ADJUSTED_DEMAND_ACTUAL')
                      AND NAME = 'DF_ADJUSTED_DEMAND_ACTUAL_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_ACTUAL
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_ACTUAL_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ADJUSTED_DEMAND_ACTUAL')
                      AND NAME = 'DF_ADJUSTED_DEMAND_ACTUAL_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_ACTUAL
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_ACTUAL_CREATED_DATE DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ADJUSTED_DEMAND_ACTUAL')
                      AND NAME = 'DF_ADJUSTED_DEMAND_ACTUAL_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_ACTUAL
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_ACTUAL_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ADJUSTED_DEMAND_ACTUAL')
                      AND NAME = 'DF_ADJUSTED_DEMAND_ACTUAL_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_ACTUAL
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_ACTUAL_MODIFIED_DATE DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

-------------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'ADJUSTED_DEMAND_ACTUAL')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('ADJUSTED_DEMAND_ACTUAL')
                            AND NAME = 'UQ_ADJUSTED_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COUNTRY_ORGANIZATION_KEY')
        BEGIN
            ALTER TABLE ADJUSTED_DEMAND_ACTUAL
              ADD CONSTRAINT UQ_ADJUSTED_DEMAND_ACTUAL_FORECAST_TYPE_FORECAST_YEAR_FORECAST_MONTH_ITEM_ID_COUNTRY_ORGANIZATION_KEY UNIQUE(FORECAST_TYPE, FORECAST_YEAR, FORECAST_MONTH, ITEM_ID, COUNTRY, ORGANIZATION_KEY)
        END
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'ADJUSTED_DEMAND_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS ADJUSTED_DEMAND_ACTUAL.CREATED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('ADJUSTED_DEMAND_ACTUAL')
                  AND NAME = 'DF_ADJUSTED_DEMAND_ACTUAL_CREATED_BY')
  BEGIN
      ALTER TABLE ADJUSTED_DEMAND_ACTUAL
        DROP CONSTRAINT DF_ADJUSTED_DEMAND_ACTUAL_CREATED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ADJUSTED_DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE ADJUSTED_DEMAND_ACTUAL
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ADJUSTED_DEMAND_ACTUAL')
                      AND NAME = 'DF_ADJUSTED_DEMAND_ACTUAL_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_ACTUAL
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_ACTUAL_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

-----------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'ADJUSTED_DEMAND_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS ADJUSTED_DEMAND_ACTUAL.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('ADJUSTED_DEMAND_ACTUAL')
                  AND NAME = 'DF_ADJUSTED_DEMAND_ACTUAL_MODIFIED_BY')
  BEGIN
      ALTER TABLE ADJUSTED_DEMAND_ACTUAL
        DROP CONSTRAINT DF_ADJUSTED_DEMAND_ACTUAL_MODIFIED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ADJUSTED_DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE ADJUSTED_DEMAND_ACTUAL
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ADJUSTED_DEMAND_ACTUAL')
                      AND NAME = 'DF_ADJUSTED_DEMAND_ACTUAL_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_ACTUAL
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_ACTUAL_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO
-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ADJUSTED_DEMAND_ACTUAL'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------HIST_ADJUSTED_DEMAND_ACTUAL---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ADJUSTED_DEMAND_ACTUAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_ADJUSTED_DEMAND_ACTUAL
        (
           ADJUSTED_DEMAND_ACTUAL_SID     INT NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           FORECAST_YEAR                  [VARCHAR](5) NOT NULL,
           FORECAST_MONTH                 [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
           BRAND_MASTER_SID               INT NULL,
           SEGMENT                        [VARCHAR](50) NULL,
           MARKET_SIZE_UNITS              NUMERIC (22, 6) NOT NULL,
           MARKET_SHARE_RATIO             NUMERIC(25) NOT NULL,
           MARKET_SHARE_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NULL,
           GROSS_UNITS                    NUMERIC (22, 6) NOT NULL,
           GROSS_PRICE                    NUMERIC (22, 6) NOT NULL,
           GROSS_AMOUNT                   NUMERIC (22, 6) NOT NULL,
           NET_SALES_PRICE                NUMERIC (22, 6) NOT NULL,
           NET_SALES_AMOUNT               NUMERIC (22, 6) NOT NULL,
           CREATED_BY                     [VARCHAR](50) NOT NULL,
           CREATED_DATE                   [DATETIME] NOT NULL,
           MODIFIED_BY                    [VARCHAR](50) NOT NULL,
           MODIFIED_DATE                  [DATETIME] NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL,
           [ACTION_FLAG]                  CHAR(1) NOT NULL,
           [ACTION_DATE]                  DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_ADJUSTED_DEMAND_ACTUAL')
                      AND NAME = 'DF_HIST_ADJUSTED_DEMAND_ACTUAL_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_ADJUSTED_DEMAND_ACTUAL
        ADD CONSTRAINT DF_HIST_ADJUSTED_DEMAND_ACTUAL_ACTION_DATE DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_ADJUSTED_DEMAND_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS HIST_ADJUSTED_DEMAND_ACTUAL.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ADJUSTED_DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_ADJUSTED_DEMAND_ACTUAL
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_ADJUSTED_DEMAND_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS HIST_ADJUSTED_DEMAND_ACTUAL.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ADJUSTED_DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_ADJUSTED_DEMAND_ACTUAL
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ADJUSTED_DEMAND_ACTUAL'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

---------------ALTER SCRIPT
IF EXISTS (SELECT 1
           FROM   SYS .STATS
           WHERE  NAME = 'MARKET_SHARE_RATIO'
                  AND OBJECT_ID = OBJECT_ID('ADJUSTED_DEMAND_ACTUAL'))
  BEGIN
      DROP STATISTICS ADJUSTED_DEMAND_ACTUAL.MARKET_SHARE_RATIO
  END

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ADJUSTED_DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'MARKET_SHARE_RATIO'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'NUMERIC')
  BEGIN
      ALTER TABLE [ADJUSTED_DEMAND_ACTUAL]
        ALTER COLUMN MARKET_SHARE_RATIO VARCHAR(25)
  END

IF EXISTS (SELECT 1
           FROM   SYS .STATS
           WHERE  NAME = 'MARKET_SHARE_RATIO'
                  AND OBJECT_ID = OBJECT_ID('HIST_ADJUSTED_DEMAND_ACTUAL'))
  BEGIN
      DROP STATISTICS HIST_ADJUSTED_DEMAND_ACTUAL.MARKET_SHARE_RATIO
  END

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ADJUSTED_DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'MARKET_SHARE_RATIO'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'NUMERIC')
  BEGIN
      ALTER TABLE [HIST_ADJUSTED_DEMAND_ACTUAL]
        ALTER COLUMN MARKET_SHARE_RATIO VARCHAR(25)
  END

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'ADJUSTED_DEMAND_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MARKET_SHARE_RATIO')
  BEGIN
      DROP STATISTICS ADJUSTED_DEMAND_ACTUAL.MARKET_SHARE_RATIO
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_ADJUSTED_DEMAND_ACTUAL'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MARKET_SHARE_RATIO')
  BEGIN
      DROP STATISTICS HIST_ADJUSTED_DEMAND_ACTUAL.MARKET_SHARE_RATIO
  END

GO

------------
-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ADJUSTED_DEMAND_ACTUAL_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_ADJUSTED_DEMAND_ACTUAL_INS
  END

GO

CREATE TRIGGER [DBO].TRG_ADJUSTED_DEMAND_ACTUAL_INS
ON [DBO].ADJUSTED_DEMAND_ACTUAL
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ADJUSTED_DEMAND_ACTUAL
                  (ADJUSTED_DEMAND_ACTUAL_SID,
                   FORECAST_TYPE,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG)
      SELECT ADJUSTED_DEMAND_ACTUAL_SID,
             FORECAST_TYPE,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ADJUSTED_DEMAND_ACTUAL_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_ADJUSTED_DEMAND_ACTUAL_UPD
  END

GO

CREATE TRIGGER [DBO].TRG_ADJUSTED_DEMAND_ACTUAL_UPD
ON [DBO].ADJUSTED_DEMAND_ACTUAL
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ADJUSTED_DEMAND_ACTUAL
                  (ADJUSTED_DEMAND_ACTUAL_SID,
                   FORECAST_TYPE,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG])
      SELECT ADJUSTED_DEMAND_ACTUAL_SID,
             FORECAST_TYPE,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ADJUSTED_DEMAND_ACTUAL_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_ADJUSTED_DEMAND_ACTUAL_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_ADJUSTED_DEMAND_ACTUAL_DEL
ON [DBO].ADJUSTED_DEMAND_ACTUAL
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ADJUSTED_DEMAND_ACTUAL
                  (ADJUSTED_DEMAND_ACTUAL_SID,
                   FORECAST_TYPE,
                   FORECAST_YEAR,
                   FORECAST_MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   [ACTION_FLAG])
      SELECT ADJUSTED_DEMAND_ACTUAL_SID,
             FORECAST_TYPE,
             FORECAST_YEAR,
             FORECAST_MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             COUNTRY,
             ORGANIZATION_KEY,
             'D'
      FROM   DELETED
  END

GO

-------------ADJUSTED_DEMAND_FORECAST--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ADJUSTED_DEMAND_FORECAST'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ADJUSTED_DEMAND_FORECAST]
        (
           ADJUSTED_DEMAND_FORECAST_SID   INT IDENTITY(1, 1) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           YEAR                           [VARCHAR](5) NOT NULL,
           MONTH                          [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
           BRAND_MASTER_SID               INT NOT NULL,
           SEGMENT                        [VARCHAR](50) NULL,
           MARKET_SIZE_UNITS              NUMERIC (22, 6) NULL,
           MARKET_SHARE_RATIO             [VARCHAR](25) NULL,
           MARKET_SHARE_UNITS             NUMERIC (22, 6) NULL,
           UNCAPTURED_UNITS               NUMERIC (22, 6) NULL,
           UNCAPTURED_UNITS_RATIO         [VARCHAR](25) NULL,
           TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NOT NULL,
           INVENTORY_UNIT_CHANGE          NUMERIC (22, 6) NULL,
           GROSS_UNITS                    NUMERIC (22, 6) NULL,
           GROSS_PRICE                    NUMERIC (22, 6) NULL,
           GROSS_AMOUNT                   NUMERIC (22, 6) NULL,
           NET_SALES_PRICE                NUMERIC (22, 6) NULL,
           NET_SALES_AMOUNT               NUMERIC (22, 6) NULL,
           CREATED_BY                     [VARCHAR](50) NOT NULL,
           CREATED_DATE                   [DATETIME] NOT NULL,
           MODIFIED_BY                    [VARCHAR](50) NOT NULL,
           MODIFIED_DATE                  [DATETIME] NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           FORECAST_NAME                  [VARCHAR](100) NOT NULL,
           FORECAST_VER                   [VARCHAR](15) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_ADJUSTED_DEMAND_FORECAST_ADJUSTED_DEMAND_FORECAST_SID'
                     AND TABLE_NAME = 'ADJUSTED_DEMAND_FORECAST')
BEGIN
  ALTER TABLE ADJUSTED_DEMAND_FORECAST
    ADD CONSTRAINT PK_ADJUSTED_DEMAND_FORECAST_ADJUSTED_DEMAND_FORECAST_SID PRIMARY KEY(ADJUSTED_DEMAND_FORECAST_SID)
END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ADJUSTED_DEMAND_FORECAST')
                      AND NAME = 'DF_ADJUSTED_DEMAND_FORECAST_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_FORECAST
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_FORECAST_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ADJUSTED_DEMAND_FORECAST')
                      AND NAME = 'DF_ADJUSTED_DEMAND_FORECAST_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_FORECAST
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_FORECAST_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ADJUSTED_DEMAND_FORECAST')
                      AND NAME = 'DF_ADJUSTED_DEMAND_FORECAST_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_FORECAST
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_FORECAST_CREATED_DATE DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ADJUSTED_DEMAND_FORECAST')
                      AND NAME = 'DF_ADJUSTED_DEMAND_FORECAST_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_FORECAST
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_FORECAST_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ADJUSTED_DEMAND_FORECAST')
                      AND NAME = 'DF_ADJUSTED_DEMAND_FORECAST_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_FORECAST
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_FORECAST_MODIFIED_DATE DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

-----------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'ADJUSTED_DEMAND_FORECAST')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('ADJUSTED_DEMAND_FORECAST')
                            AND NAME = 'UQ_ADJUSTED_DEMAND_FORECAST_FORECAST_TYPE_YEAR_MONTH_ITEM_ID_SOURCE_FORECAST_NAME_FORECAST_VER_COUNTRY_ORGANIZATION_KEY')
        BEGIN
            ALTER TABLE ADJUSTED_DEMAND_FORECAST
              ADD CONSTRAINT UQ_ADJUSTED_DEMAND_FORECAST_FORECAST_TYPE_YEAR_MONTH_ITEM_ID_SOURCE_FORECAST_NAME_FORECAST_VER_COUNTRY_ORGANIZATION_KEY UNIQUE(FORECAST_TYPE, YEAR, MONTH, ITEM_ID, SOURCE, FORECAST_NAME, FORECAST_VER, COUNTRY, ORGANIZATION_KEY)
        END
  END

GO

--ALTER SCRIPT
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ADJUSTED_DEMAND_FORECAST'
                  AND COLUMN_NAME = 'BRAND_MASTER_SID'
                  AND TABLE_SCHEMA = 'DBO'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [ADJUSTED_DEMAND_FORECAST]
        ALTER COLUMN BRAND_MASTER_SID INT NULL
  END

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'ADJUSTED_DEMAND_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS ADJUSTED_DEMAND_FORECAST.CREATED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('ADJUSTED_DEMAND_FORECAST')
                  AND NAME = 'DF_ADJUSTED_DEMAND_FORECAST_CREATED_BY')
  BEGIN
      ALTER TABLE ADJUSTED_DEMAND_FORECAST
        DROP CONSTRAINT DF_ADJUSTED_DEMAND_FORECAST_CREATED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ADJUSTED_DEMAND_FORECAST'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE ADJUSTED_DEMAND_FORECAST
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ADJUSTED_DEMAND_FORECAST')
                      AND NAME = 'DF_ADJUSTED_DEMAND_FORECAST_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_FORECAST
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_FORECAST_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

---------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'ADJUSTED_DEMAND_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS ADJUSTED_DEMAND_FORECAST.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.DEFAULT_CONSTRAINTS
           WHERE  PARENT_OBJECT_ID = Object_id('ADJUSTED_DEMAND_FORECAST')
                  AND NAME = 'DF_ADJUSTED_DEMAND_FORECAST_MODIFIED_BY')
  BEGIN
      ALTER TABLE ADJUSTED_DEMAND_FORECAST
        DROP CONSTRAINT DF_ADJUSTED_DEMAND_FORECAST_MODIFIED_BY;
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ADJUSTED_DEMAND_FORECAST'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE ADJUSTED_DEMAND_FORECAST
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ADJUSTED_DEMAND_FORECAST')
                      AND NAME = 'DF_ADJUSTED_DEMAND_FORECAST_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].ADJUSTED_DEMAND_FORECAST
        ADD CONSTRAINT DF_ADJUSTED_DEMAND_FORECAST_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO
--
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ADJUSTED_DEMAND_FORECAST'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE ADJUSTED_DEMAND_FORECAST		
        ADD BUSINESS_UNIT INT 
  END

GO



-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ADJUSTED_DEMAND_FORECAST'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------HIST_ADJUSTED_DEMAND_FORECAST---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ADJUSTED_DEMAND_FORECAST'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_ADJUSTED_DEMAND_FORECAST
        (
           ADJUSTED_DEMAND_FORECAST_SID   INT NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           YEAR                           [VARCHAR](5) NOT NULL,
           MONTH                          [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_MASTER_SID                INT NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
           BRAND_MASTER_SID               INT NOT NULL,
           SEGMENT                        [VARCHAR](50) NULL,
           MARKET_SIZE_UNITS              NUMERIC (22, 6) NULL,
           MARKET_SHARE_RATIO             [VARCHAR](25) NULL,
           MARKET_SHARE_UNITS             NUMERIC (22, 6) NULL,
           UNCAPTURED_UNITS               NUMERIC (22, 6) NULL,
           UNCAPTURED_UNITS_RATIO         [VARCHAR](25) NULL,
           TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NOT NULL,
           INVENTORY_UNIT_CHANGE          NUMERIC (22, 6) NULL,
           GROSS_UNITS                    NUMERIC (22, 6) NULL,
           GROSS_PRICE                    NUMERIC (22, 6) NULL,
           GROSS_AMOUNT                   NUMERIC (22, 6) NULL,
           NET_SALES_PRICE                NUMERIC (22, 6) NULL,
           NET_SALES_AMOUNT               NUMERIC (22, 6) NULL,
           CREATED_BY                     [VARCHAR](50) NOT NULL,
           CREATED_DATE                   [DATETIME] NOT NULL,
           MODIFIED_BY                    [VARCHAR](50) NOT NULL,
           MODIFIED_DATE                  [DATETIME] NOT NULL,
           INBOUND_STATUS                 CHAR(1) NULL,
           RECORD_LOCK_STATUS             BIT NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           FORECAST_NAME                  [VARCHAR](100) NOT NULL,
           FORECAST_VER                   [VARCHAR](15) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL,
           ACTION_FLAG                    CHAR(1) NOT NULL,
           ACTION_DATE                    DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_ADJUSTED_DEMAND_FORECAST')
                      AND NAME = 'DF_HIST_ADJUSTED_DEMAND_FORECAST_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_ADJUSTED_DEMAND_FORECAST
        ADD CONSTRAINT [DF_HIST_ADJUSTED_DEMAND_FORECAST_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

--ALTER SCRIPT
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ADJUSTED_DEMAND_FORECAST'
                  AND COLUMN_NAME = 'BRAND_MASTER_SID'
                  AND TABLE_SCHEMA = 'DBO'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE [HIST_ADJUSTED_DEMAND_FORECAST]
        ALTER COLUMN BRAND_MASTER_SID INT NULL
  END

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_ADJUSTED_DEMAND_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS HIST_ADJUSTED_DEMAND_FORECAST.CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ADJUSTED_DEMAND_FORECAST'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_ADJUSTED_DEMAND_FORECAST
        ALTER COLUMN CREATED_BY INT NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_ADJUSTED_DEMAND_FORECAST'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MODIFIED_BY')
  BEGIN
      DROP STATISTICS HIST_ADJUSTED_DEMAND_FORECAST.MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ADJUSTED_DEMAND_FORECAST'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_ADJUSTED_DEMAND_FORECAST
        ALTER COLUMN MODIFIED_BY INT NOT NULL
  END

GO
--
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ADJUSTED_DEMAND_FORECAST'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ADJUSTED_DEMAND_FORECAST		
        ADD BUSINESS_UNIT INT 
  END

GO



DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ADJUSTED_DEMAND_FORECAST'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ADJUSTED_DEMAND_FORECAST_INS')
  BEGIN
      DROP TRIGGER TRG_ADJUSTED_DEMAND_FORECAST_INS
  END

GO

CREATE TRIGGER TRG_ADJUSTED_DEMAND_FORECAST_INS
ON ADJUSTED_DEMAND_FORECAST
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ADJUSTED_DEMAND_FORECAST
                  (ADJUSTED_DEMAND_FORECAST_SID,
                   FORECAST_TYPE,
                   YEAR,
                   MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   UNCAPTURED_UNITS,
                   UNCAPTURED_UNITS_RATIO,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   INVENTORY_UNIT_CHANGE,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG,
				   BUSINESS_UNIT)
      SELECT ADJUSTED_DEMAND_FORECAST_SID,
             FORECAST_TYPE,
             YEAR,
             MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             UNCAPTURED_UNITS,
             UNCAPTURED_UNITS_RATIO,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             INVENTORY_UNIT_CHANGE,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'A',
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ADJUSTED_DEMAND_FORECAST_UPD')
  BEGIN
      DROP TRIGGER DBO.[TRG_ADJUSTED_DEMAND_FORECAST_UPD]
  END

GO

CREATE TRIGGER [DBO].[TRG_ADJUSTED_DEMAND_FORECAST_UPD]
ON [DBO].ADJUSTED_DEMAND_FORECAST
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ADJUSTED_DEMAND_FORECAST
                  (ADJUSTED_DEMAND_FORECAST_SID,
                   FORECAST_TYPE,
                   YEAR,
                   MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   UNCAPTURED_UNITS,
                   UNCAPTURED_UNITS_RATIO,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   INVENTORY_UNIT_CHANGE,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG,
				   BUSINESS_UNIT)
      SELECT ADJUSTED_DEMAND_FORECAST_SID,
             FORECAST_TYPE,
             YEAR,
             MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             UNCAPTURED_UNITS,
             UNCAPTURED_UNITS_RATIO,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             INVENTORY_UNIT_CHANGE,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'C',
			 BUSINESS_UNIT
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ADJUSTED_DEMAND_FORECAST_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_ADJUSTED_DEMAND_FORECAST_DEL
  END

GO

CREATE TRIGGER [DBO].TRG_ADJUSTED_DEMAND_FORECAST_DEL
ON [DBO].ADJUSTED_DEMAND_FORECAST
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ADJUSTED_DEMAND_FORECAST
                  (ADJUSTED_DEMAND_FORECAST_SID,
                   FORECAST_TYPE,
                   YEAR,
                   MONTH,
                   ITEM_ID,
                   ITEM_MASTER_SID,
                   ITEM_IDENTIFIER_CODE_QUALIFIER,
                   ITEM_IDENTIFIER,
                   BRAND_ID,
                   BRAND_MASTER_SID,
                   SEGMENT,
                   MARKET_SIZE_UNITS,
                   MARKET_SHARE_RATIO,
                   MARKET_SHARE_UNITS,
                   UNCAPTURED_UNITS,
                   UNCAPTURED_UNITS_RATIO,
                   TOTAL_DEMAND_UNITS,
                   TOTAL_DEMAND_AMOUNT,
                   INVENTORY_UNIT_CHANGE,
                   GROSS_UNITS,
                   GROSS_PRICE,
                   GROSS_AMOUNT,
                   NET_SALES_PRICE,
                   NET_SALES_AMOUNT,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   FORECAST_NAME,
                   FORECAST_VER,
                   COUNTRY,
                   ORGANIZATION_KEY,
                   ACTION_FLAG,
				   BUSINESS_UNIT)
      SELECT ADJUSTED_DEMAND_FORECAST_SID,
             FORECAST_TYPE,
             YEAR,
             MONTH,
             ITEM_ID,
             ITEM_MASTER_SID,
             ITEM_IDENTIFIER_CODE_QUALIFIER,
             ITEM_IDENTIFIER,
             BRAND_ID,
             BRAND_MASTER_SID,
             SEGMENT,
             MARKET_SIZE_UNITS,
             MARKET_SHARE_RATIO,
             MARKET_SHARE_UNITS,
             UNCAPTURED_UNITS,
             UNCAPTURED_UNITS_RATIO,
             TOTAL_DEMAND_UNITS,
             TOTAL_DEMAND_AMOUNT,
             INVENTORY_UNIT_CHANGE,
             GROSS_UNITS,
             GROSS_PRICE,
             GROSS_AMOUNT,
             NET_SALES_PRICE,
             NET_SALES_AMOUNT,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             FORECAST_NAME,
             FORECAST_VER,
             COUNTRY,
             ORGANIZATION_KEY,
             'D',
			 BUSINESS_UNIT
      FROM   DELETED
  END

GO 
-------------RETURN_RESERVE--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'RETURN_RESERVE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].RETURN_RESERVE
        (
           RETURN_RESERVE_SID     INT IDENTITY(1, 1) NOT NULL,
           RETURN_RESERVE_INTF_ID NUMERIC(38, 0) NOT NULL,
           [YEAR]                 SMALLINT NOT NULL,
           [MONTH]                TINYINT NOT NULL,
           BRAND_MASTER_SID       INT NULL,
           BRAND_ID               VARCHAR(50) NULL,
           BRAND_NAME             VARCHAR(50) NULL,
           ITEM_MASTER_SID        INT NOT NULL,
           ITEM_ID                VARCHAR(50) NOT NULL,
           ITEM_NO                VARCHAR(50) NULL,
           ITEM_NAME              VARCHAR(50) NULL,
           UNITS                  NUMERIC(22, 6) NULL,
           AMOUNT                 NUMERIC(22, 6) NOT NULL,
           COUNTRY                VARCHAR(25) NULL,
           BU_COMPANY_MASTER_SID  INT NOT NULL,
           BUSINESS_UNIT          VARCHAR(50) NOT NULL,
           BUSINESS_UNIT_NAME     VARCHAR(100) NOT NULL,
           GL_COMPANY_MASTER_SID  INT NOT NULL,
           GL_COMPANY             VARCHAR(50) NOT NULL,
          -- COMPANY_ID             VARCHAR(50) NOT NULL,
           COMPANY_NO             VARCHAR(50) NOT NULL,
           COMPANY_NAME           VARCHAR(100) NOT NULL,
           DIVISION               VARCHAR(50) NOT NULL,
           COST_CENTER            VARCHAR(50) NOT NULL,
           ACCOUNT                VARCHAR(50) NOT NULL,
           PROJECT                VARCHAR(50) NOT NULL,
           FUTURE1                VARCHAR(50) NOT NULL,
           FUTURE2                VARCHAR(50) NOT NULL,
           CREATED_BY             VARCHAR(50) NOT NULL,
           CREATED_DATE           DATETIME NOT NULL,
           MODIFIED_BY            VARCHAR(50) NOT NULL,
           MODIFIED_DATE          DATETIME NOT NULL,
           INBOUND_STATUS         CHAR(1) NULL,
           RECORD_LOCK_STATUS     BIT NOT NULL,
           BATCH_ID               VARCHAR(38) NOT NULL,
           SOURCE                 VARCHAR(50) NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_RETURN_RESERVE_RETURN_RESERVE_SID'
                     AND TABLE_NAME = 'RETURN_RESERVE')
  ALTER TABLE RETURN_RESERVE
    ADD CONSTRAINT PK_RETURN_RESERVE_RETURN_RESERVE_SID PRIMARY KEY(RETURN_RESERVE_SID)

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RETURN_RESERVE')
                      AND NAME = 'DF_RETURN_RESERVE_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].RETURN_RESERVE
        ADD CONSTRAINT DF_RETURN_RESERVE_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RETURN_RESERVE')
                      AND NAME = 'DF_RETURN_RESERVE_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].RETURN_RESERVE
        ADD CONSTRAINT DF_RETURN_RESERVE_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RETURN_RESERVE')
                      AND NAME = 'DF_RETURN_RESERVE_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].RETURN_RESERVE
        ADD CONSTRAINT DF_RETURN_RESERVE_CREATED_DATE DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RETURN_RESERVE')
                      AND NAME = 'DF_RETURN_RESERVE_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].RETURN_RESERVE
        ADD CONSTRAINT DF_RETURN_RESERVE_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RETURN_RESERVE')
                      AND NAME = 'DF_RETURN_RESERVE_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].RETURN_RESERVE
        ADD CONSTRAINT DF_RETURN_RESERVE_MODIFIED_DATE DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

-------------UNIQUE_CONSTRAINT---------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'RETURN_RESERVE')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = Object_id('RETURN_RESERVE')
                            AND NAME = 'UQ_RETURN_RESERVE_YEAR_MONTH_ITEM_ID_BUSINESS_UNIT_GL_COMPANY')
        BEGIN
            ALTER TABLE RETURN_RESERVE
              ADD CONSTRAINT UQ_RETURN_RESERVE_YEAR_MONTH_ITEM_ID_BUSINESS_UNIT_GL_COMPANY UNIQUE(BUSINESS_UNIT, GL_COMPANY, ITEM_ID, YEAR, MONTH )
        END
  END

GO

-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'RETURN_RESERVE'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND Schema_name(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               Schema_name(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND Schema_name(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + Quotename(@SCHEMANAME)
                       + '.' + Quotename(@TABLENAME) + '.'
                       + Quotename(@STATSNAME)

            --PRINT @SQL
            EXEC Sp_executesql
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + Quotename(C.NAME)
         + ' ON ' + Quotename(Schema_name(SCHEMA_ID))
         + '.' + Quotename(T.NAME) + ' ('
         + Quotename(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = Schema_name(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND Schema_name(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

-------------HIST_RETURN_RESERVE---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_RETURN_RESERVE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_RETURN_RESERVE
        (
           RETURN_RESERVE_SID     INT  NOT NULL,
           RETURN_RESERVE_INTF_ID NUMERIC(38, 0) NOT NULL,
           [YEAR]                 SMALLINT NOT NULL,
           [MONTH]                TINYINT NOT NULL,
           BRAND_MASTER_SID       INT NULL,
           BRAND_ID               VARCHAR(50) NULL,
           BRAND_NAME             VARCHAR(50) NULL,
           ITEM_MASTER_SID        INT NOT NULL,
           ITEM_ID                VARCHAR(50) NOT NULL,
           ITEM_NO                VARCHAR(50) NULL,
           ITEM_NAME              VARCHAR(50) NULL,
           UNITS                  NUMERIC(22, 6) NULL,
           AMOUNT                 NUMERIC(22, 6) NOT NULL,
           COUNTRY                VARCHAR(25) NULL,
           BU_COMPANY_MASTER_SID  INT NOT NULL,
           BUSINESS_UNIT          VARCHAR(50) NOT NULL,
           BUSINESS_UNIT_NAME     VARCHAR(100) NOT NULL,
           GL_COMPANY_MASTER_SID  INT NOT NULL,
           GL_COMPANY             VARCHAR(50) NOT NULL,
          -- COMPANY_ID             VARCHAR(50) NOT NULL,
           COMPANY_NO             VARCHAR(50) NOT NULL,
           COMPANY_NAME           VARCHAR(100) NOT NULL,
           DIVISION               VARCHAR(50) NOT NULL,
           COST_CENTER            VARCHAR(50) NOT NULL,
           ACCOUNT                VARCHAR(50) NOT NULL,
           PROJECT                VARCHAR(50) NOT NULL,
           FUTURE1                VARCHAR(50) NOT NULL,
           FUTURE2                VARCHAR(50) NOT NULL,
           CREATED_BY             VARCHAR(50) NOT NULL,
           CREATED_DATE           DATETIME NOT NULL,
           MODIFIED_BY            VARCHAR(50) NOT NULL,
           MODIFIED_DATE          DATETIME NOT NULL,
           INBOUND_STATUS         CHAR(1) NULL,
           RECORD_LOCK_STATUS     BIT NOT NULL,
           BATCH_ID               VARCHAR(38) NOT NULL,
           SOURCE                 VARCHAR(50) NULL,
           [ACTION_FLAG]          CHAR(1) NOT NULL,
           [ACTION_DATE]          DATETIME NOT NULL
        )
  END

GO

-------------DEFAULT_CONSTRAINT---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIST_RETURN_RESERVE')
                      AND NAME = 'DF_HIST_RETURN_RESERVE_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].HIST_RETURN_RESERVE
        ADD CONSTRAINT DF_HIST_RETURN_RESERVE_ACTION_DATE DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_RETURN_RESERVE'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND Schema_name(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               Schema_name(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND Schema_name(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + Quotename(@SCHEMANAME)
                       + '.' + Quotename(@TABLENAME) + '.'
                       + Quotename(@STATSNAME)

            --PRINT @SQL
            EXEC Sp_executesql
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + Quotename(C.NAME)
         + ' ON ' + Quotename(Schema_name(SCHEMA_ID))
         + '.' + Quotename(T.NAME) + ' ('
         + Quotename(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = Schema_name(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND Schema_name(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO


-------------TRIGGERS--------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RETURN_RESERVE_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_RETURN_RESERVE_INS
  END

GO

CREATE TRIGGER [DBO].TRG_RETURN_RESERVE_INS
ON [DBO].RETURN_RESERVE
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_RETURN_RESERVE
                  (RETURN_RESERVE_SID,
                   RETURN_RESERVE_INTF_ID,
                   [YEAR],
                   [MONTH],
                   BRAND_MASTER_SID,
                   BRAND_ID,
                   BRAND_NAME,
                   ITEM_MASTER_SID,
                   ITEM_ID,
                   ITEM_NO,
                   ITEM_NAME,
                   UNITS,
                   AMOUNT,
                   COUNTRY,
                   BU_COMPANY_MASTER_SID,
                   BUSINESS_UNIT,
                   BUSINESS_UNIT_NAME,
                   GL_COMPANY_MASTER_SID,
                   GL_COMPANY,
                 --  COMPANY_ID,
                   COMPANY_NO,
                   COMPANY_NAME,
                   DIVISION,
                   COST_CENTER,
                   ACCOUNT,
                   PROJECT,
                   FUTURE1,
                   FUTURE2,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   ACTION_FLAG)
      SELECT RETURN_RESERVE_SID,
             RETURN_RESERVE_INTF_ID,
             [YEAR],
             [MONTH],
             BRAND_MASTER_SID,
             BRAND_ID,
             BRAND_NAME,
             ITEM_MASTER_SID,
             ITEM_ID,
             ITEM_NO,
             ITEM_NAME,
             UNITS,
             AMOUNT,
             COUNTRY,
             BU_COMPANY_MASTER_SID,
             BUSINESS_UNIT,
             BUSINESS_UNIT_NAME,
             GL_COMPANY_MASTER_SID,
             GL_COMPANY,
            -- COMPANY_ID,
             COMPANY_NO,
             COMPANY_NAME,
             DIVISION,
             COST_CENTER,
             ACCOUNT,
             PROJECT,
             FUTURE1,
             FUTURE2,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RETURN_RESERVE_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_RETURN_RESERVE_UPD
  END

GO

CREATE TRIGGER [DBO].TRG_RETURN_RESERVE_UPD
ON [DBO].RETURN_RESERVE
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_RETURN_RESERVE
                  (RETURN_RESERVE_SID,
                   RETURN_RESERVE_INTF_ID,
                   [YEAR],
                   [MONTH],
                   BRAND_MASTER_SID,
                   BRAND_ID,
                   BRAND_NAME,
                   ITEM_MASTER_SID,
                   ITEM_ID,
                   ITEM_NO,
                   ITEM_NAME,
                   UNITS,
                   AMOUNT,
                   COUNTRY,
                   BU_COMPANY_MASTER_SID,
                   BUSINESS_UNIT,
                   BUSINESS_UNIT_NAME,
                   GL_COMPANY_MASTER_SID,
                   GL_COMPANY,
                  -- COMPANY_ID,
                   COMPANY_NO,
                   COMPANY_NAME,
                   DIVISION,
                   COST_CENTER,
                   ACCOUNT,
                   PROJECT,
                   FUTURE1,
                   FUTURE2,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   [ACTION_FLAG])
      SELECT RETURN_RESERVE_SID,
             RETURN_RESERVE_INTF_ID,
             [YEAR],
             [MONTH],
             BRAND_MASTER_SID,
             BRAND_ID,
             BRAND_NAME,
             ITEM_MASTER_SID,
             ITEM_ID,
             ITEM_NO,
             ITEM_NAME,
             UNITS,
             AMOUNT,
             COUNTRY,
             BU_COMPANY_MASTER_SID,
             BUSINESS_UNIT,
             BUSINESS_UNIT_NAME,
             GL_COMPANY_MASTER_SID,
             GL_COMPANY,
            -- COMPANY_ID,
             COMPANY_NO,
             COMPANY_NAME,
             DIVISION,
             COST_CENTER,
             ACCOUNT,
             PROJECT,
             FUTURE1,
             FUTURE2,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RETURN_RESERVE_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_RETURN_RESERVE_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_RETURN_RESERVE_DEL]
ON [DBO].RETURN_RESERVE
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_RETURN_RESERVE
                  (RETURN_RESERVE_SID,
                   RETURN_RESERVE_INTF_ID,
                   [YEAR],
                   [MONTH],
                   BRAND_MASTER_SID,
                   BRAND_ID,
                   BRAND_NAME,
                   ITEM_MASTER_SID,
                   ITEM_ID,
                   ITEM_NO,
                   ITEM_NAME,
                   UNITS,
                   AMOUNT,
                   COUNTRY,
                   BU_COMPANY_MASTER_SID,
                   BUSINESS_UNIT,
                   BUSINESS_UNIT_NAME,
                   GL_COMPANY_MASTER_SID,
                   GL_COMPANY,
                  -- COMPANY_ID,
                   COMPANY_NO,
                   COMPANY_NAME,
                   DIVISION,
                   COST_CENTER,
                   ACCOUNT,
                   PROJECT,
                   FUTURE1,
                   FUTURE2,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   INBOUND_STATUS,
                   RECORD_LOCK_STATUS,
                   BATCH_ID,
                   SOURCE,
                   [ACTION_FLAG])
      SELECT RETURN_RESERVE_SID,
             RETURN_RESERVE_INTF_ID,
             [YEAR],
             [MONTH],
             BRAND_MASTER_SID,
             BRAND_ID,
             BRAND_NAME,
             ITEM_MASTER_SID,
             ITEM_ID,
             ITEM_NO,
             ITEM_NAME,
             UNITS,
             AMOUNT,
             COUNTRY,
             BU_COMPANY_MASTER_SID,
             BUSINESS_UNIT,
             BUSINESS_UNIT_NAME,
             GL_COMPANY_MASTER_SID,
             GL_COMPANY,
            -- COMPANY_ID,
             COMPANY_NO,
             COMPANY_NAME,
             DIVISION,
             COST_CENTER,
             ACCOUNT,
             PROJECT,
             FUTURE1,
             FUTURE2,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             INBOUND_STATUS,
             RECORD_LOCK_STATUS,
             BATCH_ID,
             SOURCE,
             'D'
      FROM   DELETED
  END

GO


---------------------------------ITEM_UOM-------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_UOM'---4
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ITEM_UOM]
      
        (  [ITEM_UOM_SID]                     INT IDENTITY(1, 1) NOT NULL, 		           
           [ITEM_ID]                           VARCHAR(38) NOT NULL,
           [ITEM_MASTER_SID]                   INT NOT NULL,
           [ITEM_NO]                           VARCHAR(50) NULL,
           [ITEM_NAME]                         VARCHAR(100) NULL,
           [PRIMARY_UOM_BASELINE_FACTOR]       NUMERIC(22,6) NOT NULL,
           [PRIMARY_UOM_CODE]                  INT NOT NULL,
           [PRIMARY_UOM_NAME]                  VARCHAR(20) NOT NULL,
           [SECONDARY_UOM_CONVERSION_FACTOR]   NUMERIC(22,6) NOT NULL,
           [SECONDARY_UOM_CODE]                INT NOT NULL,
           [SECONDARY_UOM_NAME]                VARCHAR(20) NOT NULL,
           [STATUS]                            INT NOT NULL,
           [UPLOAD_DATE]                       DATETIME NOT NULL,
           [MODIFIED_BY]                       VARCHAR(50) NOT NULL ,
           [MODIFIED_DATE]                     DATETIME NOT NULL,
           [CREATED_BY]                        VARCHAR(38) NOT NULL  ,
           [CREATED_DATE]                      DATETIME NOT NULL ,
           [INBOUND_STATUS]             	   CHAR(1) NOT NULL,
           [BATCH_ID]					       VARCHAR(38) NOT NULL,
           [SOURCE]                      	   VARCHAR(50) NULL
     )
  END
  GO
---DEFAULT CONSTARINT-------

  
 IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_UOM')
                      AND NAME = 'DF_ITEM_UOM_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ITEM_UOM]
        ADD CONSTRAINT [DF_ITEM_UOM_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

 IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_UOM')
                      AND NAME = 'DF_ITEM_UOM_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ITEM_UOM]
        ADD CONSTRAINT [DF_ITEM_UOM_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO


IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_UOM')
                      AND NAME = 'DF_ITEM_UOM_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[ITEM_UOM]
        ADD CONSTRAINT [DF_ITEM_UOM_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO


IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ITEM_UOM')
                      AND NAME = 'DF_ITEM_UOM_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[ITEM_UOM]
        ADD CONSTRAINT [DF_ITEM_UOM_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO
 ------------UNIQUE KEY CONSTRAINT---------
 
--IF EXISTS (SELECT NAME
--           FROM   SYS.TABLES
--           WHERE  NAME = 'ITEM_UOM')
--  BEGIN
--      IF NOT EXISTS (SELECT 1
--                     FROM   SYS.KEY_CONSTRAINTS
--                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
--                            AND PARENT_OBJECT_ID = OBJECT_ID('ITEM_UOM')
--                            AND NAME = 'UQ_ITEM_UOM_ITEM_ID_PRIMARY_UOM_CODE_SECONDARY_UOM_CODE')
--        BEGIN
--            ALTER TABLE ITEM_UOM
--              ADD CONSTRAINT UQ_ITEM_UOM_ITEM_ID_PRIMARY_UOM_CODE_SECONDARY_UOM_CODE UNIQUE(ITEM_ID, PRIMARY_UOM_CODE, SECONDARY_UOM_CODE)
--        END
--  END

--GO 
---------------------------------------------------
IF EXISTS (
		SELECT NAME
		FROM sys.tables
		WHERE NAME = 'ITEM_UOM'
		)
BEGIN
	IF NOT EXISTS (
			SELECT 1
			FROM SYS.key_constraints
			WHERE PARENT_OBJECT_ID = OBJECT_ID('ITEM_UOM')
				AND NAME = 'UQ_ITEM_UOM_ITEM_ID_PRIMARY_UOM_CODE_SECONDARY_UOM_CODE'
			)
	BEGIN
		ALTER TABLE ITEM_UOM ADD CONSTRAINT UQ_ITEM_UOM_ITEM_ID_PRIMARY_UOM_CODE_SECONDARY_UOM_CODE UNIQUE (
			ITEM_ID
			,PRIMARY_UOM_CODE
			,SECONDARY_UOM_CODE
			)
	END
END
GO

-----------------------ADDING COLUMN IN ITEM_UOM------------------------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'ITEM_UOM' AND COLUMN_NAME  = 'ITEM_UOM_CONVERSION_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE ITEM_UOM
        ADD ITEM_UOM_CONVERSION_ID numeric(38,0) null
  END
  GO
---------------NOT NULL QUERY ITEM_UOM-------------------

IF EXISTS (
    SELECT 1 FROM ITEM_UOM
      WHERE ITEM_UOM_CONVERSION_ID IS NULL)
BEGIN

UPDATE ITEM_UOM
SET ITEM_UOM_CONVERSION_ID = 1
WHERE ITEM_UOM_CONVERSION_ID IS NULL

END
GO


IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ITEM_UOM'
                  AND COLUMN_NAME = 'ITEM_UOM_CONVERSION_ID'
                  AND IS_NULLABLE = 'YES')
  BEGIN
      ALTER TABLE ITEM_UOM
        ALTER COLUMN ITEM_UOM_CONVERSION_ID numeric(38,0) NOT NULL
  END

GO

  
  -------------Add a primary Key For ITEM_UOM-------------------

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_ITEM_UOM_ITEM_UOM_SID'
                     AND TABLE_NAME = 'ITEM_UOM')
  BEGIN
      ALTER TABLE [DBO].[ITEM_UOM]
      ADD CONSTRAINT PK_ITEM_UOM_ITEM_UOM_SID PRIMARY KEY(ITEM_UOM_SID)
  END

GO
 

-------------STATISTICS--------------------


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ITEM_UOM'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

 ----------------------------------HIST_ITEM_UOM------------------------------------------


 IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ITEM_UOM'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_ITEM_UOM]
        (          
		   [ITEM_UOM_SID]                      INT NOT NULL, 		           
           [ITEM_ID]                           VARCHAR(38) NOT NULL,
           [ITEM_MASTER_SID]                   INT NOT NULL,
           [ITEM_NO]                           VARCHAR(50) NULL,
           [ITEM_NAME]                         VARCHAR(100) NULL,
           [PRIMARY_UOM_BASELINE_FACTOR]       NUMERIC(22,6) NOT NULL,
           [PRIMARY_UOM_CODE]                  INT NOT NULL,
           [PRIMARY_UOM_NAME]                  VARCHAR(20) NOT NULL,
           [SECONDARY_UOM_CONVERSION_FACTOR]   NUMERIC(22,6) NOT NULL,
           [SECONDARY_UOM_CODE]                INT NOT NULL,
           [SECONDARY_UOM_NAME]                VARCHAR(20) NOT NULL,
           [STATUS]                            INT NOT NULL,
           [UPLOAD_DATE]                       DATETIME NOT NULL,
           [MODIFIED_BY]                       VARCHAR(50) NULL,
           [MODIFIED_DATE]                     DATETIME NULL,
           [CREATED_BY]                        VARCHAR(38) NULL,
           [CREATED_DATE]                      DATETIME NULL,
           [INBOUND_STATUS]             	   CHAR(1) NOT NULL,
           [BATCH_ID]					       VARCHAR(38) NOT NULL,
           [SOURCE]                      	   VARCHAR(50) NULL,
           [ACTION_FLAG]               CHAR(1) NOT NULL,
           [ACTION_DATE]               DATETIME NOT NULL
     )
  END
GO
---DEFAULT CONSTRAINT-------
  IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_ITEM_UOM')
                      AND NAME = 'DF_HIST_ITEM_UOM_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_ITEM_UOM]
        ADD CONSTRAINT [DF_HIST_ITEM_UOM_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO
-----------------------ADDING COLUMN IN HIST_ITEM_UOM------------------------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_ITEM_UOM' AND COLUMN_NAME  = 'ITEM_UOM_CONVERSION_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_ITEM_UOM
        ADD ITEM_UOM_CONVERSION_ID numeric(38,0) null
  END
  GO
---------------NOT NULL QUERY HIST_ITEM_UOM-------------------

IF EXISTS (
    SELECT 1 FROM HIST_ITEM_UOM
      WHERE ITEM_UOM_CONVERSION_ID IS NULL)
BEGIN

UPDATE HIST_ITEM_UOM
SET ITEM_UOM_CONVERSION_ID = 1
WHERE ITEM_UOM_CONVERSION_ID IS NULL

END
GO


IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ITEM_UOM'
                  AND COLUMN_NAME = 'ITEM_UOM_CONVERSION_ID'
                  AND IS_NULLABLE = 'YES')
  BEGIN
      ALTER TABLE HIST_ITEM_UOM
        ALTER COLUMN ITEM_UOM_CONVERSION_ID numeric(38,0) NOT NULL
  END

GO
-------------STATISTICS--------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ITEM_UOM'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO


-----------------Trigger for INSERT History Table-----------------------

 
 IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_UOM_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_ITEM_UOM_INS]
  END
GO

CREATE
	TRIGGER [DBO].[TRG_ITEM_UOM_INS] ON
	[DBO].ITEM_UOM AFTER INSERT
		AS BEGIN
	SET
		NOCOUNT ON
		INSERT
			INTO
				HIST_ITEM_UOM(
					[ITEM_UOM_SID],             
           			[ITEM_ID],
           			[ITEM_MASTER_SID] ,
					[ITEM_NO],
					[ITEM_NAME],
					[PRIMARY_UOM_BASELINE_FACTOR],
					[PRIMARY_UOM_CODE],
					[PRIMARY_UOM_NAME],
					[SECONDARY_UOM_CONVERSION_FACTOR],
					[SECONDARY_UOM_CODE],
					[SECONDARY_UOM_NAME],
					[STATUS],
					[UPLOAD_DATE],
					[MODIFIED_BY],
					[MODIFIED_DATE],
					[CREATED_BY],
					[CREATED_DATE],
					[INBOUND_STATUS],
					[BATCH_ID],
					[SOURCE],
					[ACTION_FLAG],
					[ITEM_UOM_CONVERSION_ID]
				) SELECT
					
						ITEM_UOM_SID,             
           				ITEM_ID,
           				ITEM_MASTER_SID ,
           				ITEM_NO,
						ITEM_NAME,
						PRIMARY_UOM_BASELINE_FACTOR,
						PRIMARY_UOM_CODE,
						PRIMARY_UOM_NAME,
						SECONDARY_UOM_CONVERSION_FACTOR,
						SECONDARY_UOM_CODE,
						SECONDARY_UOM_NAME,
						STATUS,
						UPLOAD_DATE,
						MODIFIED_BY,
						MODIFIED_DATE,
						CREATED_BY,
						CREATED_DATE,
						INBOUND_STATUS,
						BATCH_ID,
						SOURCE,
						'A',
						ITEM_UOM_CONVERSION_ID
					
				FROM
					inserted
				END
				GO
  
  
  -------------trigger for Update in History Table-------------------
  
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_UOM_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_ITEM_UOM_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_UOM_UPD]
ON [DBO].ITEM_UOM
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      
     INSERT INTO HIST_ITEM_UOM
                  ([ITEM_UOM_SID],             
           [ITEM_ID],
           [ITEM_MASTER_SID] ,
           [ITEM_NO],
           [ITEM_NAME],
           [PRIMARY_UOM_BASELINE_FACTOR] ,
           [PRIMARY_UOM_CODE],
           [PRIMARY_UOM_NAME] ,
           [SECONDARY_UOM_CONVERSION_FACTOR],
           [SECONDARY_UOM_CODE],
           [SECONDARY_UOM_NAME],
           [STATUS],
           [UPLOAD_DATE],
           [MODIFIED_BY],
           [MODIFIED_DATE],
           [CREATED_BY],
           [CREATED_DATE],
           [INBOUND_STATUS],
           [BATCH_ID],
           [SOURCE],
           [ACTION_FLAG],
		   [ITEM_UOM_CONVERSION_ID])
      SELECT [ITEM_UOM_SID],             
           [ITEM_ID],
           [ITEM_MASTER_SID] ,
           [ITEM_NO],
           [ITEM_NAME],
           [PRIMARY_UOM_BASELINE_FACTOR] ,
           [PRIMARY_UOM_CODE],
           [PRIMARY_UOM_NAME] ,
           [SECONDARY_UOM_CONVERSION_FACTOR],
           [SECONDARY_UOM_CODE],
           [SECONDARY_UOM_NAME],
           [STATUS],
           [UPLOAD_DATE],
           [MODIFIED_BY],
           [MODIFIED_DATE],
           [CREATED_BY],
           [CREATED_DATE],
           [INBOUND_STATUS],
           [BATCH_ID],
           [SOURCE],'C',[ITEM_UOM_CONVERSION_ID]
      FROM   INSERTED
  END

GO


-------------trigger for DELETE in History Table-------------------
 


IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_UOM_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_ITEM_UOM_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_UOM_DEL]
ON [DBO].ITEM_UOM
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_UOM
                  ([ITEM_UOM_SID],             
           [ITEM_ID],
           [ITEM_MASTER_SID] ,
           [ITEM_NO],
           [ITEM_NAME],
           [PRIMARY_UOM_BASELINE_FACTOR] ,
           [PRIMARY_UOM_CODE],
           [PRIMARY_UOM_NAME] ,
           [SECONDARY_UOM_CONVERSION_FACTOR],
           [SECONDARY_UOM_CODE],
           [SECONDARY_UOM_NAME],
           [STATUS],
           [UPLOAD_DATE],
           [MODIFIED_BY],
           [MODIFIED_DATE],
           [CREATED_BY],
           [CREATED_DATE],
           [INBOUND_STATUS],
           [BATCH_ID],
           [SOURCE],
           [ACTION_FLAG],
		   [ITEM_UOM_CONVERSION_ID])
      SELECT [ITEM_UOM_SID],             
           [ITEM_ID],
           [ITEM_MASTER_SID] ,
           [ITEM_NO],
           [ITEM_NAME],
           [PRIMARY_UOM_BASELINE_FACTOR] ,
           [PRIMARY_UOM_CODE],
           [PRIMARY_UOM_NAME] ,
           [SECONDARY_UOM_CONVERSION_FACTOR],
           [SECONDARY_UOM_CODE],
           [SECONDARY_UOM_NAME],
           [STATUS],
           [UPLOAD_DATE],
           [MODIFIED_BY],
           [MODIFIED_DATE],
           [CREATED_BY],
           [CREATED_DATE],
           [INBOUND_STATUS],
           [BATCH_ID],
           [SOURCE],'D',[ITEM_UOM_CONVERSION_ID]
      FROM   DELETED
  END

GO

--------------------------------RETURN_RATE_FORECATS_MASTER-----------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'RETURN_RATE_FORECAST'
                      AND TABLE_SCHEMA = 'DBO')
BEGIN
      CREATE TABLE [DBO].[RETURN_RATE_FORECAST]
        (          
           [RETURN_RATE_FORECAST_SID]   INT NOT NULL IDENTITY(1, 1), 
           [ITEM_MASTER_SID]            INT NOT NULL,
           [ITEM_ID]                     VARCHAR(38) NOT NULL,
           [ITEM_NO]                     VARCHAR(50) NULL,
           [ITEM_NAME]                   VARCHAR(100) NULL,
           [FORECAST_YEAR]               VARCHAR(5) NULL,
           [FORECAST_MONTH]              VARCHAR(25) NULL,
           [RATE]                        NUMERIC(22, 6) NULL,
           [FORECAST_NAME]               VARCHAR(100) NULL,
           [FORECAST_VER]                VARCHAR(15) NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [INBOUND_STATUS]              VARCHAR(10) NOT NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL
     )
END

GO


-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RETURN_RATE_FORECAST')
                      AND NAME = 'DF_RETURN_RATE_FORECAST_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[RETURN_RATE_FORECAST]
        ADD CONSTRAINT [DF_RETURN_RATE_FORECAST_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END
  
  GO
  



IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RETURN_RATE_FORECAST')
                      AND NAME = 'DF_RETURN_RATE_FORECAST_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RETURN_RATE_FORECAST]
        ADD CONSTRAINT [DF_RETURN_RATE_FORECAST_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END
    
	GO


IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RETURN_RATE_FORECAST')
                      AND NAME = 'DF_RETURN_RATE_FORECAST_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[RETURN_RATE_FORECAST]
        ADD CONSTRAINT [DF_RETURN_RATE_FORECAST_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END
  
  GO
    



IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RETURN_RATE_FORECAST')
                      AND NAME = 'DF_RETURN_RATE_FORECAST_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RETURN_RATE_FORECAST]
        ADD CONSTRAINT [DF_RETURN_RATE_FORECAST_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END
  
  GO

-----------------------UNIQUE KEY CONSTARINT-------------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'RETURN_RATE_FORECAST')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('RETURN_RATE_FORECAST')
                            AND NAME = 'UQ_RETURN_RATE_FORECAST_ITEM_ID_FORECAST_YEAR_FORECAST_MONTH')
        BEGIN
            ALTER TABLE RETURN_RATE_FORECAST
              ADD CONSTRAINT UQ_RETURN_RATE_FORECAST_ITEM_ID_FORECAST_YEAR_FORECAST_MONTH UNIQUE(ITEM_ID, FORECAST_YEAR, FORECAST_MONTH)
        END
  END
  
  GO
  
  ------------------------ADD COLUMN IN RETURN_RATE_FORECAST-----------------

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'RETURN_RATE_FORECAST' AND COLUMN_NAME  = 'RETURN_RATE_FORECAST_INTERFACE_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE RETURN_RATE_FORECAST
        ADD RETURN_RATE_FORECAST_INTERFACE_ID numeric(38,0) null
  END
  GO
---------------NOT NULL QUERY RETURN_RATE_FORECAST-------------------

IF EXISTS (
    SELECT 1 FROM RETURN_RATE_FORECAST
      WHERE RETURN_RATE_FORECAST_INTERFACE_ID IS NULL)
BEGIN

UPDATE RETURN_RATE_FORECAST
SET RETURN_RATE_FORECAST_INTERFACE_ID = 1
WHERE RETURN_RATE_FORECAST_INTERFACE_ID IS NULL

END
GO


IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RETURN_RATE_FORECAST'
                  AND COLUMN_NAME = 'RETURN_RATE_FORECAST_INTERFACE_ID'
                  AND IS_NULLABLE = 'YES')
  BEGIN
      ALTER TABLE RETURN_RATE_FORECAST
        ALTER COLUMN RETURN_RATE_FORECAST_INTERFACE_ID numeric(38,0) NOT NULL
  END

GO

  -------------Add a primary Key For RETURN_RATE_FORECAST-------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_RETURN_RATE_FORECAST_RETURN_RATE_FORECAST_SID'
                     AND TABLE_NAME = 'RETURN_RATE_FORECAST')
  BEGIN
      ALTER TABLE [DBO].[RETURN_RATE_FORECAST]
      ADD CONSTRAINT PK_RETURN_RATE_FORECAST_RETURN_RATE_FORECAST_SID PRIMARY KEY(RETURN_RATE_FORECAST_SID)
  END

GO

------------------RETURN_RATE_FORECAST_MASTER_STATISTICS---------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'RETURN_RATE_FORECAST'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

----------------------------------HIST_RETURN_RATE_FORECAST------------------------------------------
 
 IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_RETURN_RATE_FORECAST'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_RETURN_RATE_FORECAST]
        (          
           [RETURN_RATE_FORECAST_SID]   NUMERIC(38, 0) NOT NULL, 
           [ITEM_MASTER_SID]            INT NOT NULL,
           [ITEM_ID]                     VARCHAR(38) NOT NULL,
           [ITEM_NO]                     VARCHAR(50) NULL,
           [ITEM_NAME]                   VARCHAR(100) NULL,
           [FORECAST_YEAR]               VARCHAR(5) NULL,
           [FORECAST_MONTH]              VARCHAR(25) NULL,
           [RATE]                        NUMERIC(22, 6) NULL,
           [FORECAST_NAME]               VARCHAR(100) NULL,
           [FORECAST_VER]                VARCHAR(15) NULL, 
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [INBOUND_STATUS]             VARCHAR(10) NOT NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [ACTION_FLAG]                 CHAR(1) NOT NULL,
           [ACTION_DATE]                 DATETIME NOT NULL
         
     )
  END
  GO
  
  ------------------------DEFAULT CONSTRAINT--------------------

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_RETURN_RATE_FORECAST')
                      AND NAME = 'DF_HIST_RETURN_RATE_FORECAST_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_RETURN_RATE_FORECAST]
        ADD CONSTRAINT [DF_HIST_RETURN_RATE_FORECAST_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END
  GO
  -----------------------------ADD COLUMN IN HIST_RETURN_RATE_FOREACST ----------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_RETURN_RATE_FORECAST' AND COLUMN_NAME  = 'RETURN_RATE_FORECAST_INTERFACE_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_RETURN_RATE_FORECAST
        ADD RETURN_RATE_FORECAST_INTERFACE_ID numeric(38,0) null
  END
  GO

---------------NOT NULL QUERY-------------------

IF EXISTS (
    SELECT 1 FROM HIST_RETURN_RATE_FORECAST
      WHERE RETURN_RATE_FORECAST_INTERFACE_ID IS NULL)
BEGIN

UPDATE HIST_RETURN_RATE_FORECAST
SET RETURN_RATE_FORECAST_INTERFACE_ID = 1
WHERE RETURN_RATE_FORECAST_INTERFACE_ID IS NULL

END
GO


IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RETURN_RATE_FORECAST'
                  AND COLUMN_NAME = 'RETURN_RATE_FORECAST_INTERFACE_ID'
                  )
  BEGIN
      ALTER TABLE HIST_RETURN_RATE_FORECAST
        ALTER COLUMN RETURN_RATE_FORECAST_INTERFACE_ID numeric(38,0) NOT NULL
  END
  GO

---------------------------HIST_STATISTICS------------------------------------
  
 DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_RETURN_RATE_FORECAST'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME)
                       + '.' + QUOTENAME(@TABLENAME) + '.'
                       + QUOTENAME(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME)
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID))
         + '.' + QUOTENAME(T.NAME) + ' ('
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
         JOIN SYS.COLUMNS C
           ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT 1
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE  CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
         AND NOT EXISTS (SELECT 1
                         FROM   SYS.STATS S
                         WHERE  S.OBJECT_ID = C.OBJECT_ID
                                AND S.NAME = C.NAME)
         AND T.NAME = @TABLENAME1 -- TABLE NAME
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC SP_EXECUTESQL
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

----------------------------------------RETURN_RATE_FORECAST_TRIGGERS--------------------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RETURN_RATE_FORECAST_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_RETURN_RATE_FORECAST_UPD
  END
  GO

CREATE TRIGGER [DBO].[TRG_RETURN_RATE_FORECAST_UPD]
ON [DBO].[RETURN_RATE_FORECAST]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
         AND EXISTS (SELECT *
                     FROM   DELETED)
        INSERT INTO HIST_RETURN_RATE_FORECAST
                    (RETURN_RATE_FORECAST_SID,
					ITEM_MASTER_SID,
				   ITEM_ID,                 
				   ITEM_NO,                 
				   ITEM_NAME,               
				   FORECAST_YEAR,           
				   FORECAST_MONTH,         
				   RATE,                    
				   FORECAST_NAME,           
				   FORECAST_VER,            
				   MODIFIED_BY,             
				   MODIFIED_DATE,           
				   CREATED_BY,             
				   CREATED_DATE,            
				   INBOUND_STATUS,   
				   BATCH_ID,                
				   SOURCE, 
				   ACTION_FLAG,
				   RETURN_RATE_FORECAST_INTERFACE_ID)
        SELECT RETURN_RATE_FORECAST_SID,
					ITEM_MASTER_SID,
				   ITEM_ID,                 
				   ITEM_NO,                 
				   ITEM_NAME,               
				   FORECAST_YEAR,           
				   FORECAST_MONTH,          
				   RATE,                    
				   FORECAST_NAME,           
				   FORECAST_VER,            
				   MODIFIED_BY,             
				   MODIFIED_DATE,           
				   CREATED_BY,             
				   CREATED_DATE,            
				   INBOUND_STATUS,   
				   BATCH_ID,                
				   SOURCE, 
					'C',
					RETURN_RATE_FORECAST_INTERFACE_ID
        FROM   INSERTED
  END
  GO



IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RETURN_RATE_FORECAST_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_RETURN_RATE_FORECAST_INS
  END
  GO



CREATE TRIGGER [DBO].[TRG_RETURN_RATE_FORECAST_INS]
ON [DBO].[RETURN_RATE_FORECAST]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
         
        INSERT INTO HIST_RETURN_RATE_FORECAST
                    (RETURN_RATE_FORECAST_SID,
					ITEM_MASTER_SID,
				   ITEM_ID,                 
				   ITEM_NO,                 
				   ITEM_NAME,               
				   FORECAST_YEAR,           
				   FORECAST_MONTH,          
				   RATE,                    
				   FORECAST_NAME,           
				   FORECAST_VER,            
				   MODIFIED_BY,             
				   MODIFIED_DATE,           
				   CREATED_BY,             
				   CREATED_DATE,            
				   INBOUND_STATUS,   
				   BATCH_ID,                
				   SOURCE, 
				   ACTION_FLAG,
				   RETURN_RATE_FORECAST_INTERFACE_ID)
        SELECT RETURN_RATE_FORECAST_SID,
					ITEM_MASTER_SID,
				   ITEM_ID,                 
				   ITEM_NO,                 
				   ITEM_NAME,               
				   FORECAST_YEAR,           
				   FORECAST_MONTH,         
				   RATE,                    
				   FORECAST_NAME,           
				   FORECAST_VER,            
				   MODIFIED_BY,             
				   MODIFIED_DATE,           
				   CREATED_BY,             
				   CREATED_DATE,            
				   INBOUND_STATUS,   
				   BATCH_ID,                
				   SOURCE, 
				  'A',
				  RETURN_RATE_FORECAST_INTERFACE_ID
        FROM   INSERTED
  END
  GO




IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RETURN_RATE_FORECAST_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_RETURN_RATE_FORECAST_DEL
  END
  GO



CREATE TRIGGER [DBO].[TRG_RETURN_RATE_FORECAST_DEL]
ON [DBO].[RETURN_RATE_FORECAST]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   DELETED)
        INSERT INTO HIST_RETURN_RATE_FORECAST
                    (RETURN_RATE_FORECAST_SID,
					ITEM_MASTER_SID,
				   ITEM_ID,                 
				   ITEM_NO,                 
				   ITEM_NAME,               
				   FORECAST_YEAR,           
				   FORECAST_MONTH,          
				   RATE,                    
				   FORECAST_NAME,           
				   FORECAST_VER,            
				   MODIFIED_BY,             
				   MODIFIED_DATE,           
				   CREATED_BY,             
				   CREATED_DATE,            
				   INBOUND_STATUS,   
				   BATCH_ID,                
				   SOURCE, 
				   ACTION_FLAG,
				   RETURN_RATE_FORECAST_INTERFACE_ID)
        SELECT RETURN_RATE_FORECAST_SID,
					ITEM_MASTER_SID,
				   ITEM_ID,                 
				   ITEM_NO,                 
				   ITEM_NAME,               
				   FORECAST_YEAR,           
				   FORECAST_MONTH,          
				   RATE,                    
				   FORECAST_NAME,           
				   FORECAST_VER,            
				   MODIFIED_BY,             
				   MODIFIED_DATE,           
				   CREATED_BY,             
				   CREATED_DATE,            
				   INBOUND_STATUS,   
				   BATCH_ID,                
				   SOURCE, 
				   'D',
				   RETURN_RATE_FORECAST_INTERFACE_ID
        FROM   DELETED
  END
  GO