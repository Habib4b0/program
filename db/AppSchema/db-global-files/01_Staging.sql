﻿IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_ACTUAL_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_ACTUAL_MASTER]
        (
           [ACTUAL_INTFID]                  NUMERIC(38, 0) NOT NULL,
           [ACTUAL_ID]                      VARCHAR(50) NOT NULL,
           [CONTRACT_ID]                    VARCHAR(50) NOT NULL,
           [UPLOAD_DATE]                    DATETIME NULL,
           [PROVISION_ID]                   VARCHAR(50) NOT NULL,
           [ACCRUAL_ACTUAL_START_DATE]      DATETIME NOT NULL,
           [ACCRUAL_ACTUAL_END_DATE]        DATETIME NOT NULL,
           [ITEM_ID]                        VARCHAR(50) NOT NULL,
           [ITEM_IDENTIFIER_CODE_QUALIFIER] VARCHAR(50) NULL,
           [ITEM_NO]                        VARCHAR(50) NULL,
           [SETTLEMENT_METHOD]              VARCHAR(50) NULL,
           [CASH_PAID_DATE]                 DATETIME NOT NULL,
           [AMOUNT]                         NUMERIC(20, 6) NOT NULL,
           [QUANTITY]                       NUMERIC(20, 4) NOT NULL,
           [QUANTITY_INCLUSION]             VARCHAR(10) NOT NULL,
           [SETTLEMENT_NO]                  VARCHAR(100) NULL,
           [INVOICE_LINE_NUMBER]            VARCHAR(100) NULL,
           [ACCOUNT_ID]                     VARCHAR(100) NOT NULL,
           [ACCT_IDENTIFIER_CODE_QUALIFIER] VARCHAR(100) NULL,
           [ACCOUNT_NO]                     VARCHAR(100) NULL,
           [ACCOUNT_NAME]                   VARCHAR(100) NULL,
           [ANALYSIS_CODE]                  CHAR(50) NULL,
           [IS_ACTIVE]                      CHAR(2) NULL,
           [COM_DIV_MKT_BRAND_PROD_KEY]     VARCHAR(100) NULL,
           [PARENTCOM_DIVMKT_BRAND_PRODKEY] VARCHAR(100) NULL,
           [PRICE_ADJUSTMENT_NAME]          VARCHAR(100) NULL,
           [PRICE]                          NUMERIC(20, 6) NULL,
           [RECORD_SEQUENCE]                VARCHAR(30) NULL,
           [SENT_OUT]                       CHAR(5) NULL,
           [ACCRUAL_PROCESSED]              CHAR(5) NULL,
           [DIVISION_ID]                    VARCHAR(38) NULL,
           [MARKET_ID]                      VARCHAR(38) NULL,
           [BRAND_ID]                       VARCHAR(38) NULL,
           [CLAIM_INDICATOR]                VARCHAR(50) NULL,
           [SALES_AMOUNT]                   NUMERIC(20, 6) NOT NULL,
           [ORGANIZATION_KEY]               VARCHAR(50) NOT NULL,
           [MANDATED_DISCOUNT_AMOUNT]       NUMERIC(20, 6) NULL,
           [PROVISION_CLAIM_INDICATOR]      VARCHAR(10) NULL,
           [PROGRAM_STATE_CODE]             VARCHAR(10) NULL,
           [DISPENSED_DATE]                 DATETIME NULL,
           [CREATED_BY]                     VARCHAR(50) NULL,
           [CREATED_DATE]                   DATETIME NULL,
           [MODIFIED_BY]                    VARCHAR(50) NULL,
           [MODIFIED_DATE]                  DATETIME NULL,
           [BATCH_ID]                       VARCHAR(38) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]          VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_ACTUAL_MASTER'--TABLE NAME
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




IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_AVERAGE_SHELF_LIFE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_AVERAGE_SHELF_LIFE]
        (
           [AVERAGE_SHELF_LIFE_INTFID] NUMERIC(38, 0) NOT NULL,
           [ITEM_ID_TYPE]              VARCHAR(10) NOT NULL,
           [ITEM_ID]                   VARCHAR(50) NOT NULL,
           [AVG_SHELF_LIFE]            NUMERIC(20, 6) NOT NULL,
           [CREATED_BY]                VARCHAR(50) NULL,
           [CREATED_DATE]              DATETIME NULL,
           [MODIFIED_BY]               VARCHAR(50) NULL,
           [MODIFIED_DATE]             DATETIME NULL,
           [BATCHID]                   VARCHAR(38) NOT NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]     VARCHAR(10)  NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_AVERAGE_SHELF_LIFE'--TABLE NAME
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


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_BEST_PRICE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_BEST_PRICE]
        (
           [BEST_PRICE_INTFID]     NUMERIC(38, 0) NULL,
           [ITEM_ID]               VARCHAR(50) NULL,
           [ITEM_NO]               VARCHAR(50) NULL,
           [ITEM_DESC]             VARCHAR(250) NULL,
           [BEGINNING_WAC_PACKAGE] NUMERIC(20, 6) NULL,
           [SELLING_PRICE]         NUMERIC(20, 6) NULL,
           [INITIAL_BEST_PRICE]    NUMERIC(20, 6) NULL,
           [INITIAL_DISCOUNT]      NUMERIC(20, 6) NULL,
           [CONTRACT_NO]           VARCHAR(50) NULL,
           [CONTRACT_ID]           VARCHAR(50) NULL,
           [ACCOUNT_ID]            VARCHAR(100) NULL,
           [CONTRACT_START_DATE]   DATETIME NULL,
           [CONTRACT_END_DATE]     DATETIME NULL,
           [PERIOD]                VARCHAR(10) NULL,
           [CREATED_BY]            VARCHAR(50) NULL,
           [CREATED_DATE]          DATETIME NULL,
           [MODIFIED_BY]           VARCHAR(50) NULL,
           [MODIFIED_DATE]         DATETIME NULL,
           [BATCH_ID]              VARCHAR(38) NOT NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR] VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_BEST_PRICE'--TABLE NAME
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


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_CFP'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_CFP]
        (
           [CFP_INTFID]                  NUMERIC(38, 0) NOT NULL,
           [CFP_ID]                      VARCHAR(38) NOT NULL,
           [CFP_NO]                      VARCHAR(50) NULL,
           [CFP_NAME]                    VARCHAR(100) NULL,
           [CFP_TYPE]                    VARCHAR(50) NULL,
           [CFP_CATEGORY]                VARCHAR(50) NULL,
           [CFP_DESIGNATION]             VARCHAR(50) NULL,
           [PARENT_CFP_ID]               VARCHAR(50) NULL,
           [PARENT_CFP_NAME]             VARCHAR(50) NULL,
           [CFP_STATUS]                  VARCHAR(20) NOT NULL,
           [CFP_TRADE_CLASS]             VARCHAR(100) NULL,
           [CFP_START_DATE]              DATETIME NOT NULL,
           [CFP_END_DATE]                DATETIME NULL,
           [TRADING_PARTNER_ID]          VARCHAR(50) NOT NULL,
           [IDENTIFIER_CODE_QUALIFIER]   VARCHAR(20) NULL,
           [TRADING_PARTNER_NO]          VARCHAR(50) NULL,
           [TRADING_PARTNER_NAME]        VARCHAR(100) NULL,
           [TRADING_PARTNER_CONTRACT_NO] VARCHAR(50) NULL,
           [START_DATE]                  DATETIME NOT NULL,
           [END_DATE]                    DATETIME NULL,
           [CONTRACT_ID]                 VARCHAR(50) NOT NULL,
           [ATTACHED_STATUS]             VARCHAR(20) NOT NULL,
           [ATTACHED_DATE]               DATETIME NOT NULL,
           [TRADE_CLASS]                 VARCHAR(100) NULL,
           [TRADE_CLASS_START_DATE]      DATETIME NULL,
           [TRADE_CLASS_END_DATE]        DATETIME NULL,
           [INTERNAL_NOTES]              VARCHAR(1000) NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL
        )
  END
GO
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_CFP'
                      AND COLUMN_NAME = 'STAG_CFP_SID'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE STAG_CFP
        ADD STAG_CFP_SID INT IDENTITY(1,1) NOT NULL
  END
GO
--P.K for STAG_CFP
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'STAG_CFP'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_STAG_CFP_STAG_CFP_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].STAG_CFP
        ADD CONSTRAINT PK_STAG_CFP_STAG_CFP_SID PRIMARY KEY (STAG_CFP_SID)
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_CFP'--TABLE NAME
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

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_COMPANY_IDENTIFIER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_COMPANY_IDENTIFIER]
        (
           [COMPANY_IDENTIFIER_INTFID]      NUMERIC(38, 0) NOT NULL,
           [COMPANY_ID]                     VARCHAR(38) NOT NULL,
           [COMPANY_NO]                     VARCHAR(50) NOT NULL,
           [COMPANY_NAME]                   VARCHAR(100) NOT NULL,
           [IDENTIFIER_CODE_QUALIFIER]      VARCHAR(25) NOT NULL,
           [IDENTIFIER_CODE_QUALIFIER_NAME] VARCHAR(100) NOT NULL,
           [COMPANY_IDENTIFIER]             VARCHAR(50) NOT NULL,
           [IDENTIFIER_STATUS]              VARCHAR(20) NOT NULL,
           [START_DATE]                     DATETIME NULL,
           [END_DATE]                       DATETIME NULL,
           [ENTITY_CODE]                    VARCHAR(30) NULL,
           [CREATED_BY]                     VARCHAR(50) NULL,
           [CREATED_DATE]                   DATETIME NULL,
           [MODIFIED_BY]                    VARCHAR(50) NULL,
           [MODIFIED_DATE]                  DATETIME NULL,
           [BATCH_ID]                       VARCHAR(38) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]          VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_COMPANY_IDENTIFIER'--TABLE NAME
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


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_COMPANY_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_COMPANY_MASTER]
        (
           [COMPANY_MASTER_INTFID] NUMERIC(38, 0) NULL,
           [ORGANIZATION_KEY]      VARCHAR(100) NULL,
           [COMPANY_ID]            VARCHAR(38) NOT NULL,
           [COMPANY_NO]            VARCHAR(50) NOT NULL,
           [COMPANY_NAME]          VARCHAR(100) NOT NULL,
           [COMPANY_TYPE]          VARCHAR(50) NOT NULL,
           [COMPANY_STATUS]        VARCHAR(20) NOT NULL,
           [LIVES]                 NUMERIC(38, 0) NULL,
           [COMPANY_END_DATE]      DATETIME NULL,
           [UDC1]                  VARCHAR(100) NULL,
           [UDC2]                  VARCHAR(100) NULL,
           [UDC3]                  VARCHAR(100) NULL,
           [UDC4]                  VARCHAR(100) NULL,
           [UDC5]                  VARCHAR(100) NULL,
           [UDC6]                  VARCHAR(100) NULL,
           [COMPANY_GROUP]         VARCHAR(50) NULL,
           [FINANCIAL_SYSTEM]      VARCHAR(50) NULL,
           [ADDRESS_1]             VARCHAR(100) NULL,
           [ADDRESS_2]             VARCHAR(100) NULL,
           [CITY]                  VARCHAR(50) NULL,
           [STATE]                 VARCHAR(50) NULL,
           [ZIP_CODE]              VARCHAR(50) NULL,
           [COUNTRY]               VARCHAR(50) NULL,
           [REGION_CODE]           VARCHAR(50) NULL,
           [LAST_UPDATED_DATE]     DATETIME NULL,
           [COMPANY_START_DATE]    DATETIME NOT NULL,
           [STATUS]                VARCHAR(1) NULL,
           [COMPANY_CATEGORY]      VARCHAR(50) NULL,
           [CREATED_BY]            VARCHAR(50) NULL,
           [CREATED_DATE]          DATETIME NULL,
           [MODIFIED_BY]           VARCHAR(50) NULL,
           [MODIFIED_DATE]         DATETIME NULL,
           [BATCH_ID]              VARCHAR(38) NOT NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR] VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_COMPANY_MASTER'--TABLE NAME
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


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_COMPANY_PARENT'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_COMPANY_PARENT]
        (
           [PARENT_DETAILS_INTFID]   NUMERIC(38, 0) NOT NULL,
           [COMPANY_ID]              VARCHAR(38) NOT NULL,
           [PARENT_COMPANY_ID]       VARCHAR(50) NOT NULL,
           [PARENT_START_DATE]       DATETIME NOT NULL,
           [PARENT_END_DATE]         DATETIME NULL,
           [PRIOR_PARENT_COMPANY_ID] VARCHAR(50) NULL,
           [PRIOR_PARENT_START_DATE] DATETIME NULL,
           [LAST_UPDATED_DATE]       DATETIME NULL,
           [CREATED_BY]              VARCHAR(50) NULL,
           [CREATED_DATE]            DATETIME NULL,
           [MODIFIED_BY]             VARCHAR(50) NULL,
           [MODIFIED_DATE]           DATETIME NULL,
           [BATCH_ID]                VARCHAR(38) NOT NULL,
           [SOURCE]                  VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]   VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_COMPANY_PARENT'--TABLE NAME
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


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_COMPANY_TRADE_CLASS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_COMPANY_TRADE_CLASS]
        (
           [TRADE_CLASS_INTFID]           NUMERIC(38) NOT NULL,
           [COMPANY_ID]                   VARCHAR(38) NOT NULL,
           [TRADE_CLASS_START_DATE]       DATETIME NOT NULL,
           [TRADE_CLASS_END_DATE]         DATETIME NULL,
           [TRADE_CLASS]                  VARCHAR(100) NOT NULL,
           [PRIOR_TRADE_CLASS]            VARCHAR(50) NULL,
           [PRIOR_TRADE_CLASS_START_DATE] DATETIME NULL,
           [LAST_UPDATED_DATE]            DATETIME NULL,
           [CREATED_BY]                   VARCHAR(50) NULL,
           [CREATED_DATE]                 DATETIME NULL,
           [MODIFIED_BY]                  VARCHAR(50) NULL,
           [MODIFIED_DATE]                DATETIME NULL,
           [BATCH_ID]                     VARCHAR(38) NOT NULL,
           [SOURCE]                       VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]        VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_COMPANY_TRADE_CLASS'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_CONTRACT_HEADER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_CONTRACT_HEADER]
        (
           [CONTRACT_HEADER_INTFID]            NUMERIC(38, 0) NOT NULL,
           [ORGANIZATION_KEY]                  VARCHAR(50) NULL,
           [CONTRACT_ID]                       VARCHAR(38) NOT NULL,
           [CONTRACT_NO]                       VARCHAR(50) NULL,
           [CONTRACT_NAME]                     VARCHAR(100) NULL,
           [CONTRACT_TYPE]                     VARCHAR(50) NULL,
           [CONTRACT_STATUS]                   VARCHAR(20) NOT NULL,
           [DOC_TYPE]                          VARCHAR(50) NULL,
           [DOC_CLASS]                         VARCHAR(50) NULL,
           [COMPANY_IDENT_CODE_QUALIFIER]      VARCHAR(25) NULL,
           [COMPANY_NO]                        VARCHAR(50) NOT NULL,
           [COMPANY_NAME]                      VARCHAR(100) NULL,
           [TP_IDENTIFIER_CODE_QUALIFIER]      VARCHAR(25) NULL,
           [TRADING_PARTNER_NO]                VARCHAR(50) NULL,
           [TRADING_PARTNER_NAME]              VARCHAR(100) NULL,
           [TP_CONTRACT_ID]                    VARCHAR(38) NULL,
           [TP_CONTRACT_NAME]                  VARCHAR(100) NULL,
           [TRADE_CLASS]                       VARCHAR(100) NULL,
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
           [BATCH_ID]                          VARCHAR(38) NOT NULL,
           [SOURCE]                            VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]            VARCHAR(10) NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_CONTRACT_HEADER'
                      AND COLUMN_NAME = 'STAG_CONTRACT_HEADER_SID'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE STAG_CONTRACT_HEADER
        ADD STAG_CONTRACT_HEADER_SID INT IDENTITY(1,1) NOT NULL
  END
GO
--P.K for STAG_CONTRACT_HEADER
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'STAG_CONTRACT_HEADER'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_STAG_CONTRACT_HEADER_STAG_CONTRACT_HEADER_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].STAG_CONTRACT_HEADER
        ADD CONSTRAINT PK_STAG_CONTRACT_HEADER_STAG_CONTRACT_HEADER_SID PRIMARY KEY (STAG_CONTRACT_HEADER_SID)
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_CONTRACT_HEADER'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_CPI'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_CPI]
        (
           [CPI_INTFID]            NUMERIC(38, 0) NOT NULL,
           [INDEX_ID]              NUMERIC(22, 0) NULL,
           [STATUS]                VARCHAR(20) NULL,
           [INDEX_TYPE]            VARCHAR(50) NULL,
           [EFFECTIVE_DATE]        DATETIME NULL,
           [INDEX_VALUE]           NUMERIC(22, 6) NULL,
           [CREATED_BY]            VARCHAR(50) NULL,
           [CREATED_DATE]          DATETIME NULL,
           [MODIFIED_BY]           VARCHAR(50) NULL,
           [MODIFIED_DATE]         DATETIME NULL,
           [BATCH_ID]              VARCHAR(38) NOT NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR] VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_CPI'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_FORECAST_SALES'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_FORECAST_SALES]
        (
           [FORECAST_INTFID]                   NUMERIC(38, 0) NOT NULL,
           [FORECAST_YEAR]                     VARCHAR(5) NULL,
           [FORECAST_MONTH]                    VARCHAR(25) NULL,
           [NDC]                               VARCHAR(50) NULL,
           [FORECAST_START_DATE]               DATETIME NULL,
           [UNITS]                             NUMERIC(20, 6) NULL,
           [DOLLARS]                           NUMERIC(20, 6) NULL,
           [FORECAST_VALUE_TYPE]               VARCHAR(50) NULL,
           [FORECAST_VALUE]                    VARCHAR(25) NULL,
           [PRODUCT]                           VARCHAR(50) NULL,
           [BRAND]                             VARCHAR(50) NULL,
           [SEGMENT]                           VARCHAR(100) NULL,
           [PERCENTAGE_ESTIMATE]               VARCHAR(25) NULL,
           [PERCENTAGE_ESTIMATE_YEAR]          VARCHAR(5) NULL,
           [ACTUAL_SALES_PERCENTAGE]           VARCHAR(25) NULL,
           [ACTUAL_SALES_PERCENTAGE_MONTH]     VARCHAR(25) NULL,
           [FORECASTED_SALES_PERCENTAGE]       VARCHAR(25) NULL,
           [FORECASTED_SALES_PERCENT_MONTH] VARCHAR(25) NULL,
           [FORECAST_VER]                      VARCHAR(15) NULL,
           [PRICE]                             NUMERIC(20, 6) NULL,
           [COUNTRY]                           VARCHAR(25) NOT NULL,
           [FORECAST_NAME]                     VARCHAR(100) NULL,
           [FORECAST_DATE]                     DATETIME NOT NULL,
           [CREATED_BY]                        VARCHAR(50) NULL,
           [CREATED_DATE]                      DATETIME NULL,
           [MODIFIED_BY]                       VARCHAR(50) NULL,
           [MODIFIED_DATE]                     DATETIME NULL,
           [BATCH_ID]                          VARCHAR(38) NOT NULL,
           [SOURCE]                            VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]             VARCHAR(10) NOT NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_FORECAST_SALES'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_FORMULA_DETAILS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_FORMULA_DETAILS]
        (
           [FORMULA_DETAILS_INTFID] NUMERIC(38, 0) NOT NULL,
           [FORMULA_ID]             NUMERIC(38, 0) NOT NULL,
           [FORMULA_NO]             VARCHAR(50) NOT NULL,
           [FORMULA_DESC]           VARCHAR(50) NOT NULL,
           [COMPANY_ID]             VARCHAR(38) NOT NULL,
           [ITEM_ID]                VARCHAR(38) NOT NULL,
           [START_DATE]             DATETIME NOT NULL,
           [END_DATE]               DATETIME NULL,
           [REBATE_PERCENT_1]       NUMERIC(20, 2) NULL,
           [REBATE_PERCENT_2]       NUMERIC(20, 2) NULL,
           [REBATE_PERCENT_3]       NUMERIC(20, 2) NULL,
           [CONTRACT_PRICE_1]       NUMERIC(20, 6) NULL,
           [CONTRACT_PRICE_2]       NUMERIC(20, 6) NULL,
           [CONTRACT_PRICE_3]       NUMERIC(20, 6) NULL,
           [CREATED_BY]             VARCHAR(50) NULL,
           [CREATED_DATE]           DATETIME NULL,
           [MODIFIED_BY]            VARCHAR(50) NULL,
           [MODIFIED_DATE]          DATETIME NULL,
           [BATCH_ID]               VARCHAR(38) NOT NULL,
           [SOURCE]                 VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]  VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_FORMULA_DETAILS'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_GL_BALANCE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_GL_BALANCE]
        (
           [GL_BALANCE_INTFID] NUMERIC(38, 0) NOT NULL,
           [ACCOUNT_ID]            VARCHAR(100) NOT NULL,
           [ACCOUNT_NO]            VARCHAR(100) NOT NULL,
           [BALANCE]              NUMERIC(20, 6) NOT NULL,
           [UPLOAD_DATE]           DATETIME NOT NULL,
           [IS_ACTIVE]             CHAR(10) NULL,
           [INSERTED_DATE]         DATETIME NULL,
           [YEAR]                 VARCHAR(10) NULL,
           [PERIOD]               VARCHAR(2) NULL,
           [CLOSE_INDICATOR]       CHAR(10) NULL,
           [CREATED_BY]            VARCHAR(50) NULL,
           [CREATED_DATE]          DATETIME NULL,
           [MODIFIED_BY]           VARCHAR(50) NULL,
           [MODIFIED_DATE]         DATETIME NULL,
           [BATCH_ID]              VARCHAR(38) NOT NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR] VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_GL_BALANCE'--TABLE NAME
SET @SCHEMANAME1 ='dbo' -- SCHEMA NAME
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


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_GL_COST_CENTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_GL_COST_CENTER]
        (
           [GL_COST_CENTER_INTFID] NUMERIC(38, 0) NOT NULL,
           [COMPANY_COST_CENTER]       VARCHAR(8) NOT NULL,
           [COMPANY_CODE]             VARCHAR(5) NOT NULL,
           [NDC8]                    VARCHAR(25) NOT NULL,
           [UPLOAD_DATE]              DATETIME NOT NULL,
           [CREATED_BY]               VARCHAR(50) NULL,
           [CREATED_DATE]             DATETIME NULL,
           [MODIFIED_BY]              VARCHAR(50) NULL,
           [MODIFIED_DATE]            DATETIME NULL,
           [BATCH_ID]                 VARCHAR(38) NOT NULL,
           [SOURCE]                   VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]   VARCHAR(10) NOT NULL
        )
  END
GO


    IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_GL_COST_CENTER'
                      AND COLUMN_NAME = 'NDC8'
                      AND TABLE_SCHEMA = 'DBO'
					  AND DATA_TYPE='VARCHAR'
					  AND CHARACTER_MAXIMUM_LENGTH=100)
  
ALTER TABLE STAG_GL_COST_CENTER
  ALTER COLUMN NDC8 VARCHAR(100) NOT NULL



DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_GL_COST_CENTER'--TABLE NAME
SET @SCHEMANAME1 ='dbo' -- SCHEMA NAME

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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_IFP'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_IFP]
        (
           [IFP_INTFID]                   NUMERIC(38, 0) NOT NULL,
           [IFP_ID]                       VARCHAR(50) NOT NULL,
           [IFP_NO]                       VARCHAR(50) NULL,
           [IFP_NAME]                     VARCHAR(100) NULL,
           [IFP_TYPE]                     VARCHAR(50) NULL,
           [IFP_CATEGORY]                 VARCHAR(50) NULL,
           [IFP_DESIGNATION]              VARCHAR(50) NULL,
           [PARENT_IFP_ID]                VARCHAR(50) NULL,
           [PARENT_IFP_NAME]              VARCHAR(100) NULL,
           [IFP_STATUS]                   VARCHAR(50) NOT NULL,
           [IFP_START_DATE]               DATETIME NOT NULL,
           [IFP_END_DATE]                 DATETIME NULL,
           [ITEM_ID]                      VARCHAR(50) NOT NULL,
           [IDENTIFIER_CODE_QUALIFIER]    VARCHAR(25) NULL,
           [ITEM_NO]                      VARCHAR(50) NULL,
           [ITEM_NAME]                    VARCHAR(100) NULL,
           [ATTACHED_STATUS]              VARCHAR(50) NOT NULL,
           [ATTACHED_DATE]                DATETIME NOT NULL,
           [START_DATE]                   DATETIME NOT NULL,
           [END_DATE]                     DATETIME NULL,
           [TOTAL_VOLUME_COMMITMENT]      VARCHAR(50) NULL,
           [TOTAL_DOLLAR_COMMITMENT]      VARCHAR(50) NULL,
           [TOTAL_MARKETSHARE_COMMITMENT] VARCHAR(50) NULL,
           [COMMITMENT_PERIOD]            VARCHAR(50) NULL,
           [CONTRACT_ID]                  VARCHAR(50) NOT NULL,
           [CFP_ID]                       VARCHAR(50) NULL,
           [MFP_ID]                       VARCHAR(50) NULL,
           [CREATED_BY]                   VARCHAR(50) NULL,
           [CREATED_DATE]                 DATETIME NULL,
           [MODIFIED_BY]                  VARCHAR(50) NULL,
           [MODIFIED_DATE]                DATETIME NULL,
           [BATCH_ID]                     VARCHAR(38) NOT NULL,
           [SOURCE]                       VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]        VARCHAR(10) NOT NULL
        )
  END
GO
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_IFP'
                      AND COLUMN_NAME = 'STAG_IFP_SID'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE STAG_IFP
        ADD STAG_IFP_SID INT IDENTITY(1,1) NOT NULL
  END
GO
--P.K for STAG_IFP
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'STAG_IFP'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_STAG_IFP_STAG_IFP_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].STAG_IFP
        ADD CONSTRAINT PK_STAG_IFP_STAG_IFP_SID PRIMARY KEY (STAG_IFP_SID)
  END

GO
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_IFP'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_ITEM_HIERARCHY'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_ITEM_HIERARCHY]
        (
           [ITEM_HIERARCHY_INTFID] NUMERIC(38, 0) NOT NULL,
           [LEVEL0_TAG]            VARCHAR(100) NULL,
           [LEVEL0]                VARCHAR(100) NULL,
           [LEVEL0_ALIAS]          VARCHAR(100) NULL,
           [LEVEL1]                VARCHAR(100) NULL,
           [LEVEL1_ALIAS]          VARCHAR(100) NULL,
           [LEVEL2]                VARCHAR(100) NULL,
           [LEVEL2_ALIAS]          VARCHAR(100) NULL,
           [LEVEL3]                VARCHAR(100) NULL,
           [LEVEL3_ALIAS]          VARCHAR(100) NULL,
           [LEVEL4]                VARCHAR(100) NULL,
           [LEVEL4_ALIAS]          VARCHAR(100) NULL,
           [LEVEL5]                VARCHAR(100) NULL,
           [LEVEL5_ALIAS]          VARCHAR(100) NULL,
           [LEVEL6]                VARCHAR(100) NULL,
           [LEVEL6_ALIAS]          VARCHAR(100) NULL,
           [LEVEL7]                VARCHAR(100) NULL,
           [LEVEL7_ALIAS]          VARCHAR(100) NULL,
           [LEVEL8]                VARCHAR(100) NULL,
           [LEVEL8_ALIAS]          VARCHAR(100) NULL,
           [LEVEL9]                VARCHAR(100) NULL,
           [LEVEL9_ALIAS]          VARCHAR(100) NULL,
           [LEVEL10]               VARCHAR(100) NULL,
           [LEVEL10_ALIAS]         VARCHAR(100) NULL,
           [LEVEL11]               VARCHAR(100) NULL,
           [LEVEL11_ALIAS]         VARCHAR(100) NULL,
           [LEVEL12]               VARCHAR(100) NULL,
           [LEVEL12_ALIAS]         VARCHAR(100) NULL,
           [LEVEL13]               VARCHAR(100) NULL,
           [LEVEL13_ALIAS]         VARCHAR(100) NULL,
           [LEVEL14]               VARCHAR(100) NULL,
           [LEVEL14_ALIAS]         VARCHAR(100) NULL,
           [LEVEL15]               VARCHAR(100) NULL,
           [LEVEL15_ALIAS]         VARCHAR(100) NULL,
           [LEVEL16]               VARCHAR(100) NULL,
           [LEVEL16_ALIAS]         VARCHAR(100) NULL,
           [LEVEL17]               VARCHAR(100) NULL,
           [LEVEL17_ALIAS]         VARCHAR(100) NULL,
           [LEVEL18]               VARCHAR(100) NULL,
           [LEVEL18_ALIAS]         VARCHAR(100) NULL,
           [LEVEL19]               VARCHAR(100) NULL,
           [LEVEL19_ALIAS]         VARCHAR(100) NULL,
           [LEVEL20]               VARCHAR(100) NULL,
           [LEVEL20_ALIAS]         VARCHAR(100) NULL,
           [GTN_BRAND]             VARCHAR(100) NULL,
           [GTN_THERAPEUTIC_CLASS] VARCHAR(100) NULL,
           [GTN_COMPANY]           VARCHAR(100) NOT NULL,
           [CREATED_BY]            VARCHAR(50) NULL,
           [CREATED_DATE]          DATETIME NULL,
           [MODIFIED_BY]           VARCHAR(50) NULL,
           [MODIFIED_DATE]         DATETIME NULL,
           [BATCH_ID]              VARCHAR(38) NOT NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR] VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_ITEM_HIERARCHY'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_ITEM_HIERARCHY_DEFINITION'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_ITEM_HIERARCHY_DEFINITION]
        (
           [ITEM_HIERARCHY_DEFN_INTFID] NUMERIC(38, 0) NOT NULL,
           [MEMBER]                     VARCHAR(100) NULL,
           [ALIAS]                      VARCHAR(100) NULL,
           [BPI_LVL]                    NUMERIC(38, 0) NULL,
           [CREATED_BY]                 VARCHAR(50) NULL,
           [CREATED_DATE]               DATETIME NULL,
           [MODIFIED_BY]                VARCHAR(50) NULL,
           [MODIFIED_DATE]              DATETIME NULL,
           [BATCH_ID]                   VARCHAR(38) NOT NULL,
           [SOURCE]                     VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]      VARCHAR(10) NOT NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_ITEM_HIERARCHY_DEFINITION'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_ITEM_IDENTIFIER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_ITEM_IDENTIFIER]
        (
           [ITEM_IDENTIFIER_INTFID]         NUMERIC(38, 0) NOT NULL,
           [ITEM_ID]                        VARCHAR(38) NOT NULL,
           [ITEM_NO]                        VARCHAR(50) NOT NULL,
           [ITEM_NAME]                      VARCHAR(100) NULL,
           [IDENTIFIER_CODE_QUALIFIER]      VARCHAR(25) NOT NULL,
           [IDENTIFIER_CODE_QUALIFIER_NAME] VARCHAR(100) NULL,
           [ITEM_IDENTIFIER]                VARCHAR(50) NOT NULL,
           [ITEM_STATUS]                    VARCHAR(20) NOT NULL,
           [START_DATE]                     DATETIME NULL,
           [END_DATE]                       DATETIME NULL,
           [ENTITY_CODE]                    VARCHAR(30) NULL,
           [CREATED_BY]                     VARCHAR(50) NULL,
           [CREATED_DATE]                   DATETIME NULL,
           [MODIFIED_BY]                    VARCHAR(50) NULL,
           [MODIFIED_DATE]                  DATETIME NULL,
           [BATCH_ID]                       VARCHAR(38) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]          VARCHAR(10) NOT NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_ITEM_IDENTIFIER'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_ITEM_MASTER'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_ITEM_MASTER]
        (
           [ITEM_MASTER_INTFID]             NUMERIC(38, 0) NOT NULL,
           [ITEM_ID]                        VARCHAR(38) NOT NULL,
           [ITEM_NO]                        VARCHAR(50) NOT NULL,
           [ITEM_CODE]                      VARCHAR(25) NOT NULL,
           [ITEM_NAME]                      VARCHAR(100) NOT NULL,
           [ITEM_DESC]                      VARCHAR(250) NULL,
           [PACKAGE_SIZE]                   VARCHAR(100) NULL,
           [PACKAGE_SIZE_CODE]              VARCHAR(25) NULL,
           [PACKAGE_SIZE_INTRO_DATE]        DATETIME NULL,
           [UPPS]                           NUMERIC(22, 6) NULL,
           [ITEM_START_DATE]                DATETIME NULL,
           [ITEM_END_DATE]                  DATETIME NULL,
           [ITEM_STATUS]                    VARCHAR(20) NOT NULL,
           [MANUFACTURER_ID]                VARCHAR(38) NULL,
           [MANUFACTURER_NO]                VARCHAR(50) NULL,
           [MANUFACTURER_NAME]              VARCHAR(100) NULL,
           [LABELER_CODE]                   VARCHAR(25) NULL,
           [ORGANIZATION_KEY]               VARCHAR(50) NULL,
           [ACQUISITION_DATE]               DATETIME NULL,
           [AUTHORIZED_GENERIC]             VARCHAR(50) NULL,
           [AUTHORIZED_GENERIC_START_DATE]  DATETIME NULL,
           [AUTHORIZED_GENERIC_END_DATE]    DATETIME NULL,
           [BRAND]                          VARCHAR(30) NULL,
           [FORM]                           VARCHAR(50) NOT NULL,
           [STRENGTH]                       VARCHAR(100) NOT NULL,
           [THERAPEUTIC_CLASS]              VARCHAR(50) NULL,
           [FIRST_SALE_DATE]                DATETIME NULL,
           [ITEM_TYPE_INDICATION]           VARCHAR(50) NULL,
           [ITEM_CLASS]                     VARCHAR(50) NULL,
           [ITEM_TYPE]                      VARCHAR(50) NULL,
           [MARKET_TERMINATION_DATE]        DATETIME NULL,
           [NEW_FORMULATION_INDICATOR]      VARCHAR(50) NULL,
           [NEW_FORMULATION]                VARCHAR(38) NULL,
           [NEW_FORMULATION_START_DATE]     DATETIME NULL,
           [NEW_FORMULATION_END_DATE]       DATETIME NULL,
           [PEDIATRIC_EXCLUSIVE_INDICATOR]  VARCHAR(50) NULL,
           [PEDIATRIC_EXCLUSIVE_START_DATE] DATETIME NULL,
           [PEDIATRIC_EXCLUSIVE_END_DATE]   DATETIME NULL,
           [CLOTTING_FACTOR_INDICATOR]      VARCHAR(50) NULL,
           [CLOTTING_FACTOR_START_DATE]     DATETIME NULL,
           [CLOTTING_FACTOR_END_DATE]       DATETIME NULL,
           [PRIMARY_UOM]                    VARCHAR(20) NULL,
           [SECONDARY_UOM]                  VARCHAR(20) NULL,
           [SHELF_LIFE]                     VARCHAR(30) NULL,
           [SHELF_LIFE_TYPE]                VARCHAR(30) NULL,
           [DUAL_PRICING_INDICATOR]         VARCHAR(30) NULL,
           [ITEM_FAMILY_ID]                 VARCHAR(38) NULL,
           [UDC1]                           VARCHAR(100) NULL,
           [UDC2]                           VARCHAR(100) NULL,
           [UDC3]                           VARCHAR(100) NULL,
           [UDC4]                           VARCHAR(100) NULL,
           [UDC5]                           VARCHAR(100) NULL,
           [UDC6]                           VARCHAR(100) NULL,
           [ACQUIRED_AMP]                   NUMERIC(20, 6) NULL,
           [ACQUIRED_BAMP]                  NUMERIC(20, 6) NULL,
           [OBRA_BAMP]                      NUMERIC(20, 6) NULL,
           [DRA]                            NUMERIC(20, 0) NULL,
           [DOSES_PER_UNIT]                 VARCHAR(25) NULL,
           [STATUS]                         VARCHAR(1) NULL,
           [DISCONTINUATION_DATE]           DATETIME NULL,
           [LASTLOTEXPIRATION_DATE]         DATETIME NULL,
           [DIVESTITURE_DATE]               DATETIME NULL,
           [NON_FEDERAL_EXPIRATION_DATE]    DATETIME NULL,
           [NDC9]                           VARCHAR(25) NOT NULL,
           [NDC8]                           VARCHAR(25) NOT NULL,
           [DISPLAY_BRAND]                  VARCHAR(30) NULL,
           [BRAND_ID]                       VARCHAR(25) NOT NULL,
           [ITEM_CATEGORY]                  VARCHAR(10) NULL,
           [BASELINE_AMP]                   NUMERIC(20, 6) NULL,
           [BASE_CPI_PERIOD]                DATETIME NULL,
           [BASE_CPI]                       NUMERIC(20, 6) NULL,
		   --BASE_CPI_PRECISION 				VARCHAR(50),--------cel-1445,cel-1611
		   --BASELINE_AMP_PRECISION 			VARCHAR(50),--------cel-1445,cel-1611
           [CREATED_BY]                     VARCHAR(50) NULL,
           [CREATED_DATE]                   DATETIME NULL,
           [MODIFIED_BY]                    VARCHAR(50) NULL,
           [MODIFIED_DATE]                  DATETIME NULL,
           [BATCH_ID]                       VARCHAR(38) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]          VARCHAR(10) NOT NULL
        )
  END

GO



IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_ITEM_MASTER'
                      AND COLUMN_NAME = 'NDC8'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR'
                      AND CHARACTER_MAXIMUM_LENGTH = 100)
BEGIN					  
  ALTER TABLE STAG_ITEM_MASTER
    ALTER COLUMN NDC8 VARCHAR(100) NOT NULL 
END
GO
--------cel-1445,cel-1611

IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'BASE_CPI_PRECISION'
                  AND Object_name(object_id) = 'STAG_ITEM_MASTER')
  BEGIN
      DROP STATISTICS dbo.STAG_ITEM_MASTER.BASE_CPI_PRECISION 
  END 

GO

IF EXISTS (
		SELECT 1
		FROM information_schema.columns
		WHERE table_name = 'STAG_ITEM_MASTER'
			AND column_name = 'BASE_CPI_PRECISION'
			AND table_schema = 'DBO'
		)
BEGIN
	ALTER TABLE STAG_ITEM_MASTER DROP COLUMN BASE_CPI_PRECISION
END
GO
IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'BASELINE_AMP_PRECISION'
                  AND Object_name(object_id) = 'STAG_ITEM_MASTER')
  BEGIN
      DROP STATISTICS dbo.STAG_ITEM_MASTER.BASELINE_AMP_PRECISION 
  END 

GO
IF EXISTS (
		SELECT 1
		FROM information_schema.columns
		WHERE table_name = 'STAG_ITEM_MASTER'
			AND column_name = 'BASELINE_AMP_PRECISION'
			AND table_schema = 'DBO'
		)
BEGIN
	ALTER TABLE STAG_ITEM_MASTER DROP COLUMN BASELINE_AMP_PRECISION
END
GO
--------cel-1445,,cel-1611
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_ITEM_MASTER'--TABLE NAME
SET @SCHEMANAME1 ='dbo' -- SCHEMA NAME

IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
           JOIN   SYS.TABLES T ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
              AND NOT EXISTS (SELECT 1
                              FROM   SYS.INDEXES
                              WHERE  S.NAME = NAME)
              AND Object_Name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
              AND scHema_Name(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_Name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               scHema_Name(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
        JOIN   SYS.TABLES T ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
           AND NOT EXISTS (SELECT 1
                           FROM   SYS.INDEXES
                           WHERE  S.NAME = NAME)
           AND Object_Name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
           AND scHema_Name(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + QuoteName(@SCHEMANAME)
                       + '.' + QuoteName(@TABLENAME) + '.'
                       + QuoteName(@STATSNAME)

            --PRINT @SQL
            EXEC sp_Executesql
              @SQL

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
        END

      CLOSE CUR

      DEALLOCATE CUR
  END

DECLARE @STATS NVARCHAR(MAX)
DECLARE CUR1 CURSOR STATIC FOR
  SELECT 'CREATE STATISTICS ' + QuoteName(C.NAME)
         + ' ON ' + QuoteName(scHema_Name(SCHEMA_ID))
         + '.' + QuoteName(T.NAME) + ' ('
         + QuoteName(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
  JOIN   SYS.COLUMNS C ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT     1
                     FROM       INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                     INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE      CC.TABLE_NAME = T.NAME
                            AND CC.TABLE_SCHEMA = scHema_Name(SCHEMA_ID)
                            AND C.NAME = COLUMN_NAME)
     AND NOT EXISTS (SELECT 1
                     FROM   SYS.STATS S
                     WHERE  S.OBJECT_ID = C.OBJECT_ID
                        AND S.NAME = C.NAME)
     AND T.NAME = @TABLENAME1 -- TABLE NAME
     AND scHema_Name(SCHEMA_ID) = @SCHEMANAME1
  ORDER  BY T.NAME

OPEN CUR1

FETCH NEXT FROM CUR1 INTO @STATS

WHILE @@FETCH_STATUS = 0
  BEGIN
      --PRINT @STATS
      EXEC sp_Executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_ITEM_PRICING'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_ITEM_PRICING]
        (
           [ITEM_PRICING_INTFID]         NUMERIC(38, 0) NOT NULL,
           [ITEM_ID]                     VARCHAR(38) NOT NULL,
           [ITEM_NO]                     VARCHAR(50) NOT NULL,
           [ITEM_NAME]                   VARCHAR(100) NULL,
           [ITEM_UOM]                    VARCHAR(20) NOT NULL,
           [PRICING_CODE_QUALIFIER]      VARCHAR(25) NOT NULL,
           [PRICING_CODE_QUALIFIER_NAME] VARCHAR(100) NULL,
           [ITEM_PRICE]                  NUMERIC(20, 6) NOT NULL,
           [PRICING_CODE_STATUS]         VARCHAR(20) NOT NULL,
           [START_DATE]                  DATETIME NOT NULL,
           [END_DATE]                    DATETIME NULL,
           [ENTITY_CODE]                 VARCHAR(30) NULL,
		   ---ITEM_PRICE_PRECISION 		 VARCHAR(50) NULL,------CEL-1445,CEL-1611
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL
        )
  END

GO
------CEL-1445,CEL-1611
IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'ITEM_PRICE_PRECISION'
                  AND Object_name(object_id) = 'STAG_ITEM_PRICING')
  BEGIN
      DROP STATISTICS dbo.STAG_ITEM_PRICING.ITEM_PRICE_PRECISION 
  END 

GO
IF EXISTS (
		SELECT 1
		FROM information_schema.columns
		WHERE table_name = 'STAG_ITEM_PRICING'
			AND column_name = 'ITEM_PRICE_PRECISION'
			AND table_schema = 'DBO'
		)
BEGIN
	ALTER TABLE STAG_ITEM_PRICING DROP COLUMN ITEM_PRICE_PRECISION
END
	GO
------CEL-1445,CEL-1611
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_ITEM_PRICING'--TABLE NAME
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


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_LOT_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_LOT_MASTER]
        (
           [LOT_MASTER_INTFID]     NUMERIC(38, 0) NOT NULL,
           [ITEM_ID]               VARCHAR(50) NOT NULL,
           [LOT_NO]                VARCHAR(30) NOT NULL,
           [LAST_LOT_SOLD_DATE]    DATETIME NOT NULL,
           [LOT_EXPIRATION_DATE]   DATETIME NULL,
           [CREATED_BY]            VARCHAR(50) NULL,
           [CREATED_DATE]          DATETIME NULL,
           [MODIFIED_BY]           VARCHAR(50) NULL,
           [MODIFIED_DATE]         DATETIME NULL,
           [BATCH_ID]              VARCHAR(38) NOT NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR] VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_LOT_MASTER'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_MASTER_DATA_ATTRIBUTE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_MASTER_DATA_ATTRIBUTE]
        (
           [DATA_ATTRIBUTE_INTFID] NUMERIC(38, 0) NOT NULL,
           [MASTER_TYPE]                  VARCHAR(100) NOT NULL,
           [MASTER_ID]                    VARCHAR(100) NOT NULL,
           [MASTER_ATTRIBUTE]             VARCHAR(100) NOT NULL,
           [COLUMN_1]                     VARCHAR(100) NOT NULL,
           [COLUMN_2]                     VARCHAR(100) NULL,
           [COLUMN_3]                     VARCHAR(100) NULL,
           [COLUMN_4]                     VARCHAR(100) NULL,
           [COLUMN_5]                     VARCHAR(100) NULL,
           [COLUMN_6]                     VARCHAR(100) NULL,
           [COLUMN_7]                     VARCHAR(100) NULL,
           [COLUMN_8]                     VARCHAR(100) NULL,
           [COLUMN_9]                     VARCHAR(100) NULL,
           [COLUMN_10]                    VARCHAR(100) NULL,
           [COLUMN_11]                    VARCHAR(100) NULL,
           [COLUMN_12]                    VARCHAR(100) NULL,
           [COLUMN_13]                    VARCHAR(100) NULL,
           [COLUMN_14]                    VARCHAR(100) NULL,
           [COLUMN_15]                    VARCHAR(100) NULL,
           [COLUMN_16]                    VARCHAR(100) NULL,
           [COLUMN_17]                    VARCHAR(100) NULL,
           [COLUMN_18]                    VARCHAR(100) NULL,
           [COLUMN_19]                    VARCHAR(100) NULL,
           [COLUMN_20]                    VARCHAR(100) NULL,
           [CREATED_BY]                   VARCHAR(50) NULL,
           [CREATED_DATE]                 DATETIME NULL,
           [MODIFIED_BY]                  VARCHAR(50) NULL,
           [MODIFIED_DATE]                DATETIME NULL,
           [BATCH_ID]                     VARCHAR(38) NOT NULL,
           [SOURCE]                       VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]        VARCHAR(10) NOT NULL
        )
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_MASTER_DATA_ATTRIBUTE'--TABLE NAME
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


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_PRICE_SCHEDULE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_PRICE_SCHEDULE]
        (
           [PRICE_SCHEDULE_INTFID]       NUMERIC(38, 0) NOT NULL,
           [PRICE_SCHEDULE_ID]           VARCHAR(38) NOT NULL,
           [PRICE_SCHEDULE_NO]           VARCHAR(50) NULL,
           [PRICE_SCHEDULE_NAME]         VARCHAR(100) NULL,
           [PRICE_SCHEDULE_TYPE]         VARCHAR(50) NULL,
           [PRICE_SCHEDULE_CATEGORY]     VARCHAR(50) NULL,
           [TRADE_CLASS]                 VARCHAR(100) NULL,
           [PRICE_SCHEDULE_DESIGNATION]  VARCHAR(50) NULL,
           [PARENT_PRICE_SCHEDULE_ID]    VARCHAR(50) NULL,
           [PARENT_PRICES_CHEDULE_NAME]  VARCHAR(100) NULL,
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
           [CFP_ID]                      VARCHAR(50) NULL,
           [MFP_ID]                      VARCHAR(50) NULL,
           [IFP_ID]                      VARCHAR(50) NOT NULL,
           [PRICE]                       NUMERIC(20, 6) NOT NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL
        )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_PRICE_SCHEDULE'
                      AND COLUMN_NAME = 'STAG_PRICE_SCHEDULE_SID'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE STAG_PRICE_SCHEDULE
        ADD STAG_PRICE_SCHEDULE_SID INT IDENTITY(1,1) NOT NULL
  END
GO
--P.K for STAG_PRICE_SCHEDULE
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'STAG_PRICE_SCHEDULE'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_STAG_PRICE_SCHEDULE_STAG_PRICE_SCHEDULE_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].STAG_PRICE_SCHEDULE
        ADD CONSTRAINT PK_STAG_PRICE_SCHEDULE_STAG_PRICE_SCHEDULE_SID PRIMARY KEY (STAG_PRICE_SCHEDULE_SID)
  END

GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_PRICE_SCHEDULE'--TABLE NAME
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


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_REBATE_PLAN'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_REBATE_PLAN]
        (
           [REBATE_PLAN_INTFID]         NUMERIC(38, 0) NOT NULL,
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
           [MARKET_SHAR_EFROM]          DATETIME NULL,
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
           [CFP_ID]                     VARCHAR(50) NULL,
           [MFP_ID]                     VARCHAR(50) NULL,
           [IFP_ID]                     VARCHAR(50) NOT NULL,
           [PRICE_SCHEDULE_ID]          VARCHAR(50) NOT NULL,
           [REBATE_SCHEDULE_ID]         VARCHAR(50) NOT NULL,
           [CREATED_BY]                 VARCHAR(50) NULL,
           [CREATED_DATE]               DATETIME NULL,
           [MODIFIED_BY]                VARCHAR(50) NULL,
           [MODIFIED_DATE]              DATETIME NULL,
           [BATCH_ID]                   VARCHAR(38) NOT NULL,
           [SOURCE]                     VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]      VARCHAR(10) NOT NULL
        )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_REBATE_PLAN'
                      AND COLUMN_NAME = 'STAG_REBATE_PLAN_SID'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE STAG_REBATE_PLAN
        ADD STAG_REBATE_PLAN_SID INT IDENTITY(1,1) NOT NULL
  END
GO
--P.K for STAG_REBATE_PLAN
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'STAG_REBATE_PLAN'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_STAG_REBATE_PLAN_STAG_REBATE_PLAN_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].STAG_REBATE_PLAN
        ADD CONSTRAINT PK_STAG_REBATE_PLAN_STAG_REBATE_PLAN_SID PRIMARY KEY (STAG_REBATE_PLAN_SID)
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_REBATE_PLAN'--TABLE NAME
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


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_REBATE_SCHEDULE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_REBATE_SCHEDULE]
        (
           [REBATE_SCHEDULE_INTFID]         NUMERIC(38, 0) NOT NULL,
           [REBATE_SCHEDULE_ID]             VARCHAR(38) NOT NULL,
           [REBATE_SCHEDULE_NO]             VARCHAR(50) NULL,
           [REBATE_SCHEDULE_NAME]           VARCHAR(100) NULL,
           [REBATE_SCHEDULE_TYPE]           VARCHAR(25) NOT NULL,
           [REBATE_PROGRAM_TYPE]            VARCHAR(25) NOT NULL,
           [REBATE_SCHEDULE_CATEGORY]       VARCHAR(25) NULL,
           [TRADE_CLASS]                    VARCHAR(100) NULL,
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
           [ITEM-NAME]                      VARCHAR(50) NULL,
           [ATTACHED_STATUS]                VARCHAR(10) NOT NULL,
           [ATTACHED_DATE]                  DATETIME NOT NULL,
           [ITEM-START_DATE]                DATETIME NOT NULL,
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
           [CFP_ID]                         VARCHAR(50) NULL,
           [MFP_ID]                         VARCHAR(50) NULL,
           [IFP_ID]                         VARCHAR(50) NOT NULL,
           [PRICE_SCHEDULE_ID]              VARCHAR(50) NOT NULL,
           [UDC1]                           VARCHAR(100) NULL,
           [UDC2]                           VARCHAR(100) NULL,
           [UDC3]                           VARCHAR(100) NULL,
           [UDC4]                           VARCHAR(100) NULL,
           [UDC5]                           VARCHAR(100) NULL,
           [UDC6]                           VARCHAR(100) NULL,
           [FORMULA_METHOD_ID]              VARCHAR(38) NULL,
           [CREATED_BY]                     VARCHAR(50) NULL,
           [CREATED_DATE]                   DATETIME NULL,
           [MODIFIED_BY]                    VARCHAR(50) NULL,
           [MODIFIED_DATE]                  DATETIME NULL,
           [BATCH_ID]                       VARCHAR(38) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]          VARCHAR(10) NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_REBATE_SCHEDULE'
                      AND COLUMN_NAME = 'STAG_REBATE_SCHEDULE_SID'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE STAG_REBATE_SCHEDULE
        ADD STAG_REBATE_SCHEDULE_SID INT IDENTITY(1,1) NOT NULL
  END
GO
--P.K for STAG_REBATE_SCHEDULE
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'STAG_REBATE_SCHEDULE'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_STAG_REBATE_SCHEDULE_STAG_REBATE_SCHEDULE_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].STAG_REBATE_SCHEDULE
        ADD CONSTRAINT PK_STAG_REBATE_SCHEDULE_STAG_REBATE_SCHEDULE_SID PRIMARY KEY (STAG_REBATE_SCHEDULE_SID)
  END

GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_REBATE_SCHEDULE'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_SALES_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[STAG_SALES_MASTER]
        (
           [SALES_INTFID]       NUMERIC(38, 0) NOT NULL,
           [SALES_ID]                  VARCHAR(50) NOT NULL,
           [ORGANIZATION_KEY]          VARCHAR(100) NULL,
           [ITEM_ID]                   VARCHAR(38) NOT NULL,
           [ITEM_NO]                   VARCHAR(50) NOT NULL,
           [ACCOUNT_CODE_QUALIFIER]    VARCHAR(50) NULL,
           [PARENT_ITEM_ID]            VARCHAR(38) NULL,
           [PARENT_ITEM_NO]            VARCHAR(50) NULL,
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
           [PRICE]                     NUMERIC(20, 6) NULL,
           [QUANTITY]                  NUMERIC(38, 2) NOT NULL,
           [LOT_NO]                    VARCHAR(50) NULL,
           [AMOUNT]                    NUMERIC(20, 6) NOT NULL,
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
           [UPLOAD_DATE]               DATETIME NULL,
           [ORDER_RECEIVED_DATE]       DATETIME NULL,
           [PROVISION_ID]              NUMERIC(22, 0) NULL,
           [ACCOUNT_ID]                VARCHAR(100) NOT NULL,
           [CREATED_BY]                VARCHAR(50) NULL,
           [CREATED_DATE]              DATETIME NULL,
           [MODIFIED_BY]               VARCHAR(50) NULL,
           [MODIFIED_DATE]             DATETIME NULL,
           [BATCH_ID]                  VARCHAR(38) NOT NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]     VARCHAR(10) NOT NULL
        )
  END

GO

-------------------------------- 
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'STAG_SALES_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'PROVISION_ID')
  BEGIN
      DROP STATISTICS STAG_SALES_MASTER.PROVISION_ID
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'STAG_SALES_MASTER'
                  AND COLUMN_NAME = 'PROVISION_ID'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE STAG_SALES_MASTER
        ALTER COLUMN PROVISION_ID VARCHAR(50)
  END

GO

------------------------------------


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_SALES_MASTER'--TABLE NAME
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

----------------------------------------------------STAG_GL_POSTING-------------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_GL_POSTING'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[STAG_GL_POSTING]
        (
           [GL_POSTING_INTFID]     NUMERIC(38, 0) NOT NULL,
           [ACCRUAL_ID]            VARCHAR(50) NOT NULL,
           [STATUS]                CHAR(1) NOT NULL,
           [BATCH_ID]              VARCHAR(50) NOT NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [CREATED_BY]            VARCHAR(50) NOT NULL,
           [CREATED_DATE]          DATETIME NOT NULL,
           [MODIFIED_BY]           VARCHAR(50) NULL,
           [MODIFIED_DATE]         DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR] VARCHAR(10) NOT NULL
        )
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'STAG_GL_POSTING'
                  AND AUTO_CREATED = 0
                  AND NAME = 'GL_POSTING_INTFID')
  BEGIN
      DROP STATISTICS STAG_GL_POSTING.GL_POSTING_INTFID
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_GL_POSTING'
                      AND COLUMN_NAME = 'GL_POSTING_INTFID'
					  AND TABLE_SCHEMA='DBO')
BEGIN
EXEC SP_RENAME 'STAG_GL_POSTING.GL_POSTING_INTFID' , 'GL_POSTING_INTERFACE_ID', 'COLUMN'

END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'STAG_GL_POSTING'
                  AND COLUMN_NAME = 'GL_POSTING_INTERFACE_ID'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE STAG_GL_POSTING
        ALTER COLUMN GL_POSTING_INTERFACE_ID INT NOT NULL
  END
GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'STAG_GL_POSTING'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_BY')
  BEGIN
      DROP STATISTICS STAG_GL_POSTING.CREATED_BY
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'STAG_GL_POSTING'
                  AND COLUMN_NAME = 'CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE STAG_GL_POSTING
        ALTER COLUMN CREATED_BY VARCHAR(50) NULL
  END
GO


IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'STAG_GL_POSTING'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CREATED_DATE')
  BEGIN
      DROP STATISTICS STAG_GL_POSTING.CREATED_DATE
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'STAG_GL_POSTING'
                  AND COLUMN_NAME = 'CREATED_DATE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE STAG_GL_POSTING
        ALTER COLUMN CREATED_DATE DATETIME NULL
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_GL_POSTING'--TABLE NAME
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
-------------------------------------------------------STAG_ACCRUAL_INBOUND-----------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_ACCRUAL_INBOUND'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [dbo].[STAG_ACCRUAL_INBOUND]
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

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_ACCRUAL_INBOUND'--TABLE NAME
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
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'COMPANY_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD COMPANY_NAME VARCHAR(100)
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'BUSINESS_UNIT_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD BUSINESS_UNIT_ID VARCHAR(50) NOT NULL
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'BUSINESS_UNIT_NO' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD BUSINESS_UNIT_NO VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'BU_IDENT_CODE_QUALIFIER' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD BU_IDENT_CODE_QUALIFIER VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'BUSINESS_UNIT_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD BUSINESS_UNIT_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ITEM_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD ITEM_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'BRAND_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD BRAND_NAME VARCHAR(30) 
  END
GO
IF EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN
     exec sp_RENAME 'STAG_ACCRUAL_INBOUND.AMOUNT', 'DEDUCTION_AMOUNT' , 'COLUMN';
  END
GO

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_CATEGORY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD ACCRUAL_CATEGORY VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_SCHEDULE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD ACCRUAL_SCHEDULE_TYPE VARCHAR(25) 
  END
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_PROGRAM_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND 
        ADD ACCRUAL_PROGRAM_TYPE VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'SALES_AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD SALES_AMOUNT NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'QUANTITY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD QUANTITY NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC1' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC1 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC2' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC2 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC3' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC3 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC4' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC4 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC5' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC5 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'STAG_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC6' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE STAG_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC6 VARCHAR(100) 
  END
GO

----------------------------------------STAG_DEMAND_ACTUAL--------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_DEMAND_ACTUAL'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE  [DBO].[STAG_DEMAND_ACTUAL]
        (
           DEMAND_ACTUAL_INTERFACE_ID     [NUMERIC](38, 0) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           FORECAST_YEAR                  [VARCHAR](5) NOT NULL,
           FORECAST_MONTH                 [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
           SEGMENT                        [VARCHAR](50) NULL,
           MARKET_SIZE_UNITS             NUMERIC (22, 6) NOT NULL,
           MARKET_SHARE_RATIO             NUMERIC(25) NOT NULL,
           MARKET_SHARE_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NULL,
           GROSS_UNITS                    NUMERIC (22, 6) NOT NULL,
           GROSS_PRICE                    NUMERIC (22, 6) NOT NULL,
           GROSS_AMOUNT                   NUMERIC (22, 6) NOT NULL,
           NET_SALES_PRICE                NUMERIC (22, 6) NOT NULL,
           NET_SALES_AMOUNT               NUMERIC (22, 6) NOT NULL,
           CREATED_BY                     [VARCHAR](50) NULL,
           CREATED_DATE                   [DATETIME] NULL,
           MODIFIED_BY                    [VARCHAR](50) NULL,
           MODIFIED_DATE                  [DATETIME] NULL,
           ADD_CHG_DEL_INDICATOR          [VARCHAR](10) NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_DEMAND_ACTUAL'--TABLE NAME
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

IF NOT EXISTS (SELECT 1
               FROM   SYS .STATS
               WHERE  NAME = 'STAG_DEMAND_ACTUAL.MARKET_SHARE_RATIO'
                      AND OBJECT_ID = Object_id('STAG_DEMAND_ACTUAL'))
  BEGIN
      DROP STATISTICS STAG_DEMAND_ACTUAL.MARKET_SHARE_RATIO
  END

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'STAG_DEMAND_ACTUAL'
                  AND COLUMN_NAME = 'MARKET_SHARE_RATIO'
                  AND DATA_TYPE = 'NUMERIC'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE STAG_DEMAND_ACTUAL
        ALTER COLUMN MARKET_SHARE_RATIO VARCHAR(25)
  END

GO 



------------------------------------------STAG_DEMAND_FORECAST-------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_DEMAND_FORECAST'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [DBO].[STAG_DEMAND_FORECAST]
        (
           DEMAND_FORECAST_INTERFACE_ID   [NUMERIC](38, 0) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           FORECAST_YEAR                  [VARCHAR](5) NOT NULL,
           FORECAST_MONTH                 [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
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
           CREATED_BY                     [VARCHAR](50) NULL,
           CREATED_DATE                   [DATETIME] NULL,
           MODIFIED_BY                    [VARCHAR](50) NULL,
           MODIFIED_DATE                  [DATETIME] NULL,
           ADD_CHG_DEL_INDICATOR          [VARCHAR](10) NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           FORECAST_NAME                  [VARCHAR](100) NOT NULL,
           FORECAST_VER                   [VARCHAR](15) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_DEMAND_FORECAST'--TABLE NAME
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


------------------------STAG_INVENTORY_WD_ACTUAL_DT------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_INVENTORY_WD_ACTUAL_DT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[STAG_INVENTORY_WD_ACTUAL_DT]
        (
           INVENTORY_WD_ACTUAL_DT_INTF_ID [NUMERIC](38, 0) NOT NULL,
           YEAR                               VARCHAR(5) NOT NULL,
           MONTH                              VARCHAR(25) NOT NULL,
           DAY                                VARCHAR(25) NULL,
           WEEK                               VARCHAR(25) NULL,
           COMPANY_ID                         VARCHAR(38) NOT NULL,
           IDENTIFIER_CODE_QUALIFIER          VARCHAR(25) NULL,
           COMPANY_IDENTIFIER                 VARCHAR(50) NULL,
           ITEM_ID                            VARCHAR(38) NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER     VARCHAR(25) NULL,
           ITEM_IDENTIFIER                    VARCHAR(50)  NULL,
           UNITS_WITHDRAWN                    NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN                   NUMERIC(20, 6) NULL,
           UNITS_ON_HAND                      NUMERIC(20, 6) NOT NULL,
           AMOUNT_ON_HAND                     NUMERIC(20, 6) NULL,
           QUANTITY_ON_ORDER                  NUMERIC(20, 6) NULL,
           AMOUNT_ON_ORDER                    NUMERIC(20, 6) NULL,
           QUANTITY_RECEIVED                  NUMERIC(20, 6) NULL,
           AMOUNT_RECEIVED                    NUMERIC(20, 6) NULL,
           CREATED_BY                         VARCHAR(50) NULL,
           CREATED_DATE                       DATETIME NULL,
           MODIFIED_BY                        VARCHAR(50) NULL,
           MODIFIED_DATE                      DATETIME  NULL,
           ADD_CHG_DEL_INDICATOR              VARCHAR(10) NOT NULL,
           BATCH_ID                           VARCHAR(38) NOT NULL,
           SOURCE                             VARCHAR(50) NOT NULL,
           COUNTRY                            VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY                   VARCHAR(50) NOT NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_INVENTORY_WD_ACTUAL_DT'--TABLE NAME
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




----------------------STAG_INVENTORY_WD_ACTUAL_MAS-------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_INVENTORY_WD_ACTUAL_MAS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[STAG_INVENTORY_WD_ACTUAL_MAS]
        (
           INVENTORY_WD_ACTUAL_MAS_INTF_ID NUMERIC(38, 0) NOT NULL,
           YEAR                               VARCHAR(5) NOT NULL,
           MONTH                              VARCHAR(25) NOT NULL,
           DAY                                VARCHAR(25) NULL,
           WEEK                               VARCHAR(25) NULL,
           ITEM_ID                            VARCHAR(38) NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER     VARCHAR(25) NULL,
           ITEM_IDENTIFIER                    VARCHAR(50) NULL,
           UNITS_WITHDRAWN                    NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN                   NUMERIC(20, 6) NULL,
           UNITS_ON_HAND                      NUMERIC(20, 6) NOT NULL,
           AMOUNT_ON_HAND                     NUMERIC(20, 6) NULL,
           QUANTITY_ON_ORDER                  NUMERIC(20, 6) NULL,
           AMOUNT_ON_ORDER                    NUMERIC(20, 6) NULL,
           QUANTITY_RECEIVED                  NUMERIC(20, 6) NULL,
           AMOUNT_RECEIVED                    NUMERIC(20, 6) NULL,
           CREATED_BY                         VARCHAR(50) NULL,
           CREATED_DATE                       DATETIME NULL,
           MODIFIED_BY                        VARCHAR(50) NULL,
           MODIFIED_DATE                      DATETIME NULL,
           ADD_CHG_DEL_INDICATOR              VARCHAR(10) NOT NULL,
           BATCH_ID                           VARCHAR(38) NOT NULL,
           SOURCE                             VARCHAR(50) NOT NULL,
           COUNTRY                            VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY                   VARCHAR(50) NOT NULL
        )
  END

GO
--Re Name
IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_INVENTORY_WD_ACTUAL_MAS'
                      AND COLUMN_NAME = 'INVENTORY_WD_ACTUAL_MAS_INTF_ID'
					  AND TABLE_SCHEMA='DBO')
begin
exec sp_RENAME 'STAG_INVENTORY_WD_ACTUAL_MAS.INVENTORY_WD_ACTUAL_MAS_INTF_ID' , 'INVENTORY_WD_ACT_MAS_INTF_ID', 'COLUMN'

end
go

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_INVENTORY_WD_ACTUAL_MAS'--TABLE NAME
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

----------------------STAG_INVENTORY_WD_PROJ_DT-------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_INVENTORY_WD_PROJ_DT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[STAG_INVENTORY_WD_PROJ_DT]
        (
           INVENTORY_WD_PROJ_DT_INTF_ID NUMERIC(38, 0) NOT NULL,
           YEAR                             VARCHAR(5) NOT NULL,
           MONTH                            VARCHAR(25) NOT NULL,
           DAY                              VARCHAR(25) NULL,
           WEEK                             VARCHAR(25) NULL,
           COMPANY_ID                       VARCHAR(38) NOT NULL,
           IDENTIFIER_CODE_QUALIFIER        VARCHAR(25) NULL,
           COMPANY_IDENTIFIER               VARCHAR(50) NULL,
           ITEM_ID                          VARCHAR(38) NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER   VARCHAR(25) NULL,
           ITEM_IDENTIFIER                  VARCHAR(50)  NULL,
           UNITS_WITHDRAWN                  NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN                 NUMERIC(20, 6) NULL,
           PRICE                            NUMERIC(20, 6) NULL,
           CREATED_BY                       VARCHAR(50) NULL,
           CREATED_DATE                     DATETIME NULL,
           MODIFIED_BY                      VARCHAR(50) NULL,
           MODIFIED_DATE                    DATETIME NULL,
           ADD_CHG_DEL_INDICATOR            VARCHAR(10) NOT NULL,
           BATCH_ID                         VARCHAR(38) NOT NULL,
           SOURCE                           VARCHAR(50) NOT NULL,
           FORECAST_NAME                    VARCHAR(100) NOT NULL,
           FORECAST_VER                     VARCHAR(15) NOT NULL,
           COUNTRY                          VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY                 VARCHAR(50) NOT NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_INVENTORY_WD_PROJ_DT'--TABLE NAME
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



----------------------STAG_INVENTORY_WD_PROJ_MAS-------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_INVENTORY_WD_PROJ_MAS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[STAG_INVENTORY_WD_PROJ_MAS]
        (
           INVENTORY_WD_PROJ_MAS_INTF_ID NUMERIC(38, 0) NOT NULL,
           YEAR                               VARCHAR(5) NOT NULL,
           MONTH                              VARCHAR(25) NOT NULL,
           DAY                                VARCHAR(25) NULL,
           WEEK                               VARCHAR(25) NULL,
           ITEM_ID                            VARCHAR(38) NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER     VARCHAR(25) NULL,
           ITEM_IDENTIFIER                    VARCHAR(50) NULL,
           UNITS_WITHDRAWN                    NUMERIC(20, 6) NOT NULL,
           AMOUNT_WITHDRAWN                   NUMERIC(20, 6) NULL,
		   PRICE							  NUMERIC(22,6) NULL,
           CREATED_BY                         VARCHAR(50) NULL,
           CREATED_DATE                       DATETIME NULL,
           MODIFIED_BY                        VARCHAR(50) NULL,
           MODIFIED_DATE                      DATETIME NULL,
           ADD_CHG_DEL_INDICATOR              VARCHAR(10) NOT NULL,
           BATCH_ID                           VARCHAR(38) NOT NULL,
           SOURCE                             VARCHAR(50) NOT NULL,
           FORECAST_NAME                      VARCHAR(100) NOT NULL,
           FORECAST_VER                       VARCHAR(15) NOT NULL,
           COUNTRY                            VARCHAR(25) NOT NULL,
           ORGANIZATION_KEY                   VARCHAR(50) NOT NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_INVENTORY_WD_PROJ_MAS'--TABLE NAME
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


---------------------------STAG_RETURNS------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_RETURNS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE STAG_RETURNS
        (
           RRESERVE_INTERFACE_ID       [NUMERIC](38, 0) NOT NULL,
           RRESERVE_ID                 VARCHAR(50) NOT NULL,
           VERSION                     VARCHAR(50) NOT NULL,
           SKU                         VARCHAR(50) NOT NULL,
           BRAND                       VARCHAR(120) NOT NULL,
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
           SOURCE                      VARCHAR(50) NOT NULL
        )
  END

GO
----------------------------------------------------Adding Columns Script Starts Here--------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_RETURNS'
                      AND COLUMN_NAME = 'CREATED_BY'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE STAG_RETURNS
        ADD CREATED_BY [VARCHAR](200) NULL
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_RETURNS'
                      AND COLUMN_NAME = 'CREATED_DATE'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE STAG_RETURNS
        ADD CREATED_DATE DATETIME NULL
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_RETURNS'
                      AND COLUMN_NAME = 'MODIFIED_BY'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE STAG_RETURNS
        ADD MODIFIED_BY [VARCHAR](200) NULL
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_RETURNS'
                      AND COLUMN_NAME = 'MODIFIED_DATE'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE STAG_RETURNS
        ADD MODIFIED_DATE [DATETIME] NULL
  END
GO

----------------------------------------------------Adding Columns Script Ends Here--------------------------------------------------
-----------------------------------------ALTER COLUMNS for (NOT NULL to NULL) STAG_RETURNS----------------------------------------------
--FIRST_RETURN
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'STAG_RETURNS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'FIRST_RETURN')
  BEGIN
      DROP STATISTICS STAG_RETURNS.FIRST_RETURN
  END

GO 

IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'STAG_RETURNS'
                     AND COLUMN_NAME = 'FIRST_RETURN'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='datetime'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].STAG_RETURNS
        ALTER COLUMN FIRST_RETURN datetime NULL
  END

GO 
--LAST_RETURN
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'STAG_RETURNS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'LAST_RETURN')
  BEGIN
      DROP STATISTICS STAG_RETURNS.LAST_RETURN
  END

GO

IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'STAG_RETURNS'
                     AND COLUMN_NAME = 'LAST_RETURN'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='datetime'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].STAG_RETURNS
        ALTER COLUMN LAST_RETURN datetime NULL
  END

GO 
--MAX_EXPIRED_MONTH
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'STAG_RETURNS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'MAX_EXPIRED_MONTH')
  BEGIN
      DROP STATISTICS STAG_RETURNS.MAX_EXPIRED_MONTH
  END

GO

IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'STAG_RETURNS'
                     AND COLUMN_NAME = 'MAX_EXPIRED_MONTH'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='datetime'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].STAG_RETURNS
        ALTER COLUMN MAX_EXPIRED_MONTH datetime NULL
  END

GO
--CALC_USED
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'STAG_RETURNS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CALC_USED')
  BEGIN
      DROP STATISTICS STAG_RETURNS.CALC_USED
  END

GO

IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'STAG_RETURNS'
                     AND COLUMN_NAME = 'CALC_USED'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='varchar'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].STAG_RETURNS
        ALTER COLUMN CALC_USED varchar(20) NULL
  END

GO
--ORIG_SALE_MONTH_CUT_OFF
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'STAG_RETURNS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'ORIG_SALE_MONTH_CUT_OFF')
  BEGIN
      DROP STATISTICS STAG_RETURNS.ORIG_SALE_MONTH_CUT_OFF
  END

GO

IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'STAG_RETURNS'
                     AND COLUMN_NAME = 'ORIG_SALE_MONTH_CUT_OFF'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='datetime'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].STAG_RETURNS
        ALTER COLUMN ORIG_SALE_MONTH_CUT_OFF datetime NULL
  END

GO
-----------------------------------------ALTER COLUMNS for (NOT NULL to NULL) STAG_RETURNS ENDS HERE--------------------------------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_RETURNS'--TABLE NAME
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
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'TEMP_ACCRUAL_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE TEMP_ACCRUAL_DETAILS
        (
           ACCRUAL_DETAIL_SID       [NUMERIC](38, 0)  NULL,
   
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_ACCRUAL_DETAILS'--TABLE NAME
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
--RETURNS ends

-------------------------------STAG_CUSTOMER_GTS_ACTUAL----------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_CUSTOMER_GTS_ACTUAL'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN 
      CREATE TABLE [DBO].STAG_CUSTOMER_GTS_ACTUAL
        (
           CUSTOMER_GTS_ACTUAL_INTF_ID NUMERIC(38, 0) NOT NULL,
           SALES_ID                         VARCHAR(50) NOT NULL,
           ORGANIZATION_KEY                 VARCHAR(100) NOT NULL,
           ITEM_ID                          VARCHAR(38) NOT NULL,
           ITEM_UOM                         VARCHAR(50) NOT NULL,
           ORDER_NUMBER                     VARCHAR(50) NOT NULL,
           INVOICE_NUMBER                   VARCHAR(50) NOT NULL,
           INVOICE_LINE_NUMBER              VARCHAR(50) NOT NULL,
           INVOICE_DATE                     DATETIME NOT NULL,
           QUANTITY                         NUMERIC(22,6) NOT NULL,
           LOT_NO                           VARCHAR(50) NULL,
           AMOUNT                           NUMERIC(22, 6) NOT NULL,
           CONTRACT_ID                      VARCHAR(50) NULL,
           ACCOUNT_ID                       VARCHAR(100) NOT NULL,
           ORDER_RECEIVED_DATE              DATETIME NULL,
           BATCH_ID                         VARCHAR(50) NOT NULL,
           SOURCE                           VARCHAR(50) NOT NULL,
           CREATED_BY                       VARCHAR(50) NOT NULL,
           CREATED_DATE                     DATETIME NOT NULL,
           MODIFIED_BY                      VARCHAR(50) NOT NULL,
           MODIFIED_DATE                    DATETIME NOT NULL,
           ADD_CHG_DEL_INDICATOR            VARCHAR(10) NOT NULL,
           PARENT_ACCOUNT_ID                VARCHAR(100) NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_CUSTOMER_GTS_ACTUAL'--TABLE NAME
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




----------------------------------------STAG_CUSTOMER_FORECAST------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_CUSTOMER_GTS_FORECAST'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN 
      CREATE TABLE [DBO].[STAG_CUSTOMER_GTS_FORECAST]
        (
           CUSTOMER_GTS_FORECAST_INTF_ID NUMERIC(38, 0) NOT NULL,
           FORECAST_YEAR                 VARCHAR(5) NOT NULL,
           FORECAST_MONTH                VARCHAR(25) NOT NULL,
           ITEM_ID                       VARCHAR(50) NOT NULL,
           COMPANY_ID                    VARCHAR(50) NOT NULL,
           UNITS                         NUMERIC(22, 6) NOT NULL,
           PRICE_TYPE                    VARCHAR(50) NULL,
           PRICE                         NUMERIC(22, 6) NULL,
           SALES_AMOUNT                  NUMERIC(22, 6) NULL,
           SALES_INCLUSION               VARCHAR(10) NULL,
           DEDUCTION_ID                  VARCHAR(50) NULL,
           DEDUCTION_CATEGORY            VARCHAR(50) NULL,
           DEDUCTION_TYPE                VARCHAR(50) NULL,
           DEDUCTION_PROGRAM_TYPE        VARCHAR(50) NULL,
           ADJUSTMENT_CODE               VARCHAR(50) NULL,
           DEDUCTION_RATE                NUMERIC(22, 6) NULL,
           DEDUCTION_AMOUNT              NUMERIC(22, 6) NULL,
           DEDUCTION_INCLUSION           VARCHAR(10) NULL,
           FORECAST_VALUE_TYPE           VARCHAR(50) NULL,
           BRAND                         VARCHAR(50) NULL,
           SEGMENT                       VARCHAR(100) NULL,
           ADD_CHG_DEL_INDICATOR         VARCHAR(10) NOT NULL,
           CREATED_BY                    VARCHAR(50) NULL,
           CREATED_DATE                  DATETIME NULL,
           MODIFIED_BY                   VARCHAR(50) NULL,
           MODIFIED_DATE                 DATETIME NULL,
           BATCH_ID                      VARCHAR(38) NOT NULL,
           SOURCE                        VARCHAR(50) NOT NULL,
           FORECAST_VER                  VARCHAR(15) NOT NULL,
           COUNTRY                       VARCHAR(25) NOT NULL,
           FORECAST_NAME                 VARCHAR(100) NOT NULL,
           FORECAST_DATE                 DATETIME NULL,
           UDC1                          VARCHAR(100) NOT NULL,
           UDC2                          VARCHAR(100) NOT NULL,
           UDC3                          VARCHAR(100) NOT NULL,
           UDC4                          VARCHAR(100) NOT NULL,
           UDC5                          VARCHAR(100) NOT NULL,
           UDC6                          VARCHAR(100) NOT NULL
        )
  END

GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_CUSTOMER_GTS_FORECAST'
                      AND COLUMN_NAME = 'UDC1'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE STAG_CUSTOMER_GTS_FORECAST
        ALTER COLUMN UDC1 VARCHAR(100)  NULL
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_CUSTOMER_GTS_FORECAST'
                      AND COLUMN_NAME = 'UDC2'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE STAG_CUSTOMER_GTS_FORECAST
        ALTER COLUMN UDC2 VARCHAR(100)  NULL
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_CUSTOMER_GTS_FORECAST'
                      AND COLUMN_NAME = 'UDC3'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE STAG_CUSTOMER_GTS_FORECAST
        ALTER COLUMN UDC3 VARCHAR(100)  NULL
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_CUSTOMER_GTS_FORECAST'
                      AND COLUMN_NAME = 'UDC4'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE STAG_CUSTOMER_GTS_FORECAST
        ALTER COLUMN UDC4 VARCHAR(100)  NULL
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_CUSTOMER_GTS_FORECAST'
                      AND COLUMN_NAME = 'UDC5'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE STAG_CUSTOMER_GTS_FORECAST
        ALTER COLUMN UDC5 VARCHAR(100)  NULL
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'STAG_CUSTOMER_GTS_FORECAST'
                      AND COLUMN_NAME = 'UDC6'
					  AND TABLE_SCHEMA='DBO'
AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE STAG_CUSTOMER_GTS_FORECAST
        ALTER COLUMN UDC6 VARCHAR(100)  NULL
  END
GO
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_CUSTOMER_GTS_FORECAST'--TABLE NAME
SET @SCHEMANAME1 ='dbo' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
           JOIN   SYS.TABLES T ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
        JOIN   SYS.TABLES T ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + Quotename(@SCHEMANAME)
                       + '.' + Quotename(@TABLENAME) + '.'
                       + Quotename(@STATSNAME)

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
  SELECT 'CREATE STATISTICS ' + Quotename(C.NAME)
         + ' ON ' + Quotename(SCHEMA_NAME(SCHEMA_ID))
         + '.' + Quotename(T.NAME) + ' ('
         + Quotename(C.NAME) + ') WITH FULLSCAN'
  FROM   SYS.TABLES T
  JOIN   SYS.COLUMNS C ON T.OBJECT_ID = C.OBJECT_ID
  WHERE  NOT EXISTS (SELECT     1
                     FROM       INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
                     INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
                     WHERE      CC.TABLE_NAME = T.NAME
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


-----------------------------STAG_ADJUSTED_DEMAND_ACTUAL-----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_ADJUSTED_DEMAND_ACTUAL'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE  [DBO].[STAG_ADJUSTED_DEMAND_ACTUAL]
        (
           ADJUSTED_DEMAND_ACTUAL_INTF_ID     [NUMERIC](38, 0) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           FORECAST_YEAR                  [VARCHAR](5) NOT NULL,
           FORECAST_MONTH                 [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
           SEGMENT                        [VARCHAR](50) NULL,
           MARKET_SIZE_UNITS             NUMERIC (22, 6) NOT NULL,
           MARKET_SHARE_RATIO             NUMERIC(25) NOT NULL,
           MARKET_SHARE_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NULL,
           GROSS_UNITS                    NUMERIC (22, 6) NOT NULL,
           GROSS_PRICE                    NUMERIC (22, 6) NOT NULL,
           GROSS_AMOUNT                   NUMERIC (22, 6) NOT NULL,
           NET_SALES_PRICE                NUMERIC (22, 6) NOT NULL,
           NET_SALES_AMOUNT               NUMERIC (22, 6) NOT NULL,
           CREATED_BY                     [VARCHAR](50) NULL,
           CREATED_DATE                   [DATETIME] NULL,
           MODIFIED_BY                    [VARCHAR](50) NULL,
           MODIFIED_DATE                  [DATETIME] NULL,
           ADD_CHG_DEL_INDICATOR          [VARCHAR](10) NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_ADJUSTED_DEMAND_ACTUAL'--TABLE NAME
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


--------------------------STAG_ADJUSTED_DEMAND_FORECAST------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_ADJUSTED_DEMAND_FORECAST'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [DBO].[STAG_ADJUSTED_DEMAND_FORECAST]
        (
           ADJUSTED_DEMAND_FORECAST_INTF_ID   [NUMERIC](38, 0) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](50) NOT NULL,
           YEAR                  [VARCHAR](5) NOT NULL,
           MONTH                 [VARCHAR](25) NOT NULL,
           ITEM_ID                        [VARCHAR](38) NOT NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](25) NULL,
           ITEM_IDENTIFIER                [VARCHAR](50) NULL,
           BRAND_ID                       [VARCHAR](50) NULL,
           SEGMENT                        [VARCHAR](50) NULL,
           MARKET_SIZE_UNITS              NUMERIC (22, 6)  NULL,
           MARKET_SHARE_RATIO             [VARCHAR](25)  NULL,
           MARKET_SHARE_UNITS             NUMERIC (22, 6)  NULL,
           UNCAPTURED_UNITS               NUMERIC (22, 6)  NULL,
           UNCAPTURED_UNITS_RATIO         [VARCHAR](25) NULL,
           TOTAL_DEMAND_UNITS             NUMERIC (22, 6) NOT NULL,
           TOTAL_DEMAND_AMOUNT            NUMERIC (22, 6) NOT NULL,
           INVENTORY_UNIT_CHANGE          NUMERIC (22, 6)  NULL,
           GROSS_UNITS                    NUMERIC (22, 6)  NULL,
		   GROSS_PRICE                    NUMERIC (22, 6)  NULL,
           GROSS_AMOUNT                   NUMERIC (22, 6)  NULL,
           NET_SALES_PRICE                NUMERIC (22, 6)  NULL,
           NET_SALES_AMOUNT               NUMERIC (22, 6)  NULL,
           CREATED_BY                     [VARCHAR](50) NULL,
           CREATED_DATE                   [DATETIME] NULL,
           MODIFIED_BY                    [VARCHAR](50) NULL,
           MODIFIED_DATE                  [DATETIME] NULL,
           ADD_CHG_DEL_INDICATOR          [VARCHAR](10) NOT NULL,
           BATCH_ID                       [VARCHAR](38) NOT NULL,
           SOURCE                         [VARCHAR](50) NOT NULL,
           FORECAST_NAME                  [VARCHAR](100) NOT NULL,
           FORECAST_VER                   [VARCHAR](15) NOT NULL,
           COUNTRY                        [VARCHAR](25) NOT NULL,
           ORGANIZATION_KEY               [VARCHAR](50) NOT NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_ADJUSTED_DEMAND_FORECAST'--TABLE NAME
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

------STAG_RETURN_RESERVE-------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_RETURN_RESERVE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].STAG_RETURN_RESERVE
        (
           RETURN_RESERVE_INTF_ID NUMERIC(38, 0) NOT NULL,
           [YEAR]                 VARCHAR(5) NOT NULL,
           [MONTH]                VARCHAR(25) NOT NULL,
           BRAND_ID               VARCHAR(50) NULL,
           BRAND_NAME             VARCHAR(50) NULL,
           ITEM_ID                VARCHAR(50) NOT NULL,
           ITEM_NO                VARCHAR(50) NULL,
           ITEM_NAME              VARCHAR(50) NULL,
           UNITS                  NUMERIC(22, 6) NULL,
           AMOUNT                 NUMERIC(22, 6) NOT NULL,
           COUNTRY                VARCHAR(25) NULL,
           BUSINESS_UNIT          VARCHAR(50) NOT NULL,
           GL_COMPANY             VARCHAR(50) NOT NULL,
           COMPANY_NO             VARCHAR(50) NOT NULL,
           DIVISION               VARCHAR(50) NOT NULL,
           COST_CENTER            VARCHAR(50) NOT NULL,
           ACCOUNT                VARCHAR(50) NOT NULL,
           PROJECT                VARCHAR(50) NOT NULL,
           FUTURE1                VARCHAR(50) NOT NULL,
           FUTURE2                VARCHAR(50) NOT NULL,
           UDC1                   VARCHAR(100) NULL,
           UDC2                   VARCHAR(100) NULL,
           UDC3                   VARCHAR(100) NULL,
           UDC4                   VARCHAR(100) NULL,
           UDC5                   VARCHAR(100) NULL,
           UDC6                   VARCHAR(100) NULL,
           ADD_CHG_DEL_INDICATOR  VARCHAR(10) NOT NULL,
           CREATED_BY             VARCHAR(50) NOT NULL,
           CREATED_DATE           DATETIME NOT NULL,
           MODIFIED_BY            VARCHAR(50) NOT NULL,
           MODIFIED_DATE          DATETIME NOT NULL,
           BATCH_ID               VARCHAR(38) NOT NULL,
           SOURCE                 VARCHAR(50) NULL
        )
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_RETURN_RESERVE'--TABLE NAME
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

------STAG_ITEM_UOM-------------------------------

IF NOT EXISTS (SELECT TABLE_NAME
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STAG_ITEM_UOM'---3
                      AND TABLE_SCHEMA = 'DBO')
                     
  BEGIN
      CREATE TABLE [DBO].[STAG_ITEM_UOM]
        (  	       
		   [ITEM_UOM_CONVERSION_ID]            NUMERIC(38,0) NOT NULL,           
           [ITEM_ID]                           VARCHAR(38) NOT NULL,
           [ITEM_NO]                           VARCHAR(50) NULL,
           [ITEM_NAME]                         VARCHAR(100) NULL,
           [PRIMARY_UOM_BASELINE_FACTOR]       NUMERIC(22,6) NOT NULL,
           [PRIMARY_UOM_CODE]                  VARCHAR(20) NOT NULL,
           [PRIMARY_UOM_NAME]                  VARCHAR(20) NOT NULL,
           [SECONDARY_UOM_CONVERSION_FACTOR]   NUMERIC(22,6) NOT NULL,
           [SECONDARY_UOM_CODE]                VARCHAR(20) NOT NULL,
           [SECONDARY_UOM_NAME]                VARCHAR(20) NOT NULL,
           [STATUS]                            VARCHAR(20) NOT NULL,
           [UPLOAD_DATE]                       DATETIME NOT NULL,
           [MODIFIED_BY]                       VARCHAR(50)  NULL,
           [MODIFIED_DATE]                     DATETIME  NULL,
           [CREATED_BY]                        VARCHAR(38)  NULL,
           [CREATED_DATE]                      DATETIME  NULL,
           [ADD_CHG_DEL_INDICATOR]             VARCHAR(10) NOT NULL,
           [BATCH_ID]					       VARCHAR(38) NOT NULL,
           [SOURCE]                      	   VARCHAR(50) NULL
     )
  END

 GO
 
 
 
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'STAG_ITEM_UOM'--TABLE NAME
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





