IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_ACTUAL_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_ACTUAL_MASTER]
        (
           [ACTUAL_INTFID]                  [NUMERIC](38, 0) NOT NULL,
           [ACTUAL_ID]                      [VARCHAR](200) NULL,
           [CONTRACT_ID]                    [VARCHAR](200) NULL,
           [UPLOAD_DATE]                    [VARCHAR](200)  NULL,
           [PROVISION_ID]                   [VARCHAR](200) NULL,
           [ACCRUAL_ACTUAL_START_DATE]      [VARCHAR](200)  NULL,
           [ACCRUAL_ACTUAL_END_DATE]        [VARCHAR](200)  NULL,
           [ITEM_ID]                        [VARCHAR](200) NULL,
           [ITEM_IDENTIFIER_CODE_QUALIFIER] [VARCHAR](200) NULL,
           [ITEM_NO]                        [VARCHAR](200) NULL,
           [SETTLEMENT_METHOD]              [VARCHAR](200) NULL,
           [CASH_PAID_DATE]                 [VARCHAR](200)  NULL,
           [AMOUNT]                         [VARCHAR](200) NULL,
           [QUANTITY]                       [VARCHAR](200) NULL,
           [QUANTITY_INCLUSION]             [VARCHAR](200) NULL,
           [SETTLEMENT_NO]                  [VARCHAR](200) NULL,
           [INVOICE_LINE_NUMBER]            [VARCHAR](200) NULL,
           [ACCOUNT_ID]                     [VARCHAR](200) NULL,
           [ACCT_IDENTIFIER_CODE_QUALIFIER] [VARCHAR](200) NULL,
           [ACCOUNT_NO]                     [VARCHAR](200) NULL,
           [ACCOUNT_NAME]                   [VARCHAR](200) NULL,
           [ANALYSIS_CODE]                  [VARCHAR](200) NULL,
           [IS_ACTIVE]                      [VARCHAR](200) NULL,
           [COM_DIV_MKT_BRAND_PROD_KEY]     [VARCHAR](200) NULL,
           [PARENTCOM_DIVMKT_BRAND_PRODKEY] [VARCHAR](200) NULL,
           [PRICE_ADJUSTMENT_NAME]          [VARCHAR](200) NULL,
           [PRICE]                          [VARCHAR](200) NULL,
           [RECORD_SEQUENCE]                [VARCHAR](200) NULL,
           [SENT_OUT]                       [VARCHAR](200) NULL,
           [ACCRUAL_PROCESSED]              [VARCHAR](200) NULL,
           [DIVISION_ID]                    [VARCHAR](200) NULL,
           [MARKET_ID]                      [VARCHAR](200) NULL,
           [BRAND_ID]                       [VARCHAR](200) NULL,
           [CLAIM_INDICATOR]                [VARCHAR](200) NULL,
           [SALES_AMOUNT]                   [VARCHAR](200) NULL,
           [ORGANIZATION_KEY]               [VARCHAR](200) NULL,
           [MANDATED_DISCOUNT_AMOUNT]       [VARCHAR](200) NULL,
           [PROVISION_CLAIM_INDICATOR]      [VARCHAR](200) NULL,
           [PROGRAM_STATE_CODE]             [VARCHAR](200) NULL,
           [DISPENSED_DATE]                 [VARCHAR](200)  NULL,
           [CREATED_BY]                     [VARCHAR](200) NULL,
           [CREATED_DATE]                   [DATETIME] NULL,
           [MODIFIED_BY]                    [VARCHAR](200) NULL,
           [MODIFIED_DATE]                  [DATETIME] NULL,
           [BATCH_ID]                       [VARCHAR](200) NULL,
           [SOURCE]                         [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]          [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]             [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_ACTUAL_MASTER')
                      AND NAME = 'DF_SNAP_ACTUAL_MASTER_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_ACTUAL_MASTER]
        ADD CONSTRAINT [DF_SNAP_ACTUAL_MASTER_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_ACTUAL_MASTER'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_AVERAGE_SHELF_LIFE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_AVERAGE_SHELF_LIFE]
        (
           [AVERAGE_SHELF_LIFE_INTFID] [NUMERIC](38, 0) NOT NULL,
           [ITEM_ID_TYPE]              [VARCHAR](200) NULL,
           [ITEM_ID]                   [VARCHAR](200) NULL,
           [AVG_SHELF_LIFE]            [VARCHAR](200) NULL,
           [CREATED_BY]                [VARCHAR](200) NULL,
           [CREATED_DATE]              [DATETIME] NULL,
           [MODIFIED_BY]               [VARCHAR](200) NULL,
           [MODIFIED_DATE]             [DATETIME] NULL,
           [BATCH_ID]                  [VARCHAR](200) NULL,
           [SOURCE]                    [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]     [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]        [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_AVERAGE_SHELF_LIFE')
                      AND NAME = 'DF_SNAP_AVERAGE_SHELF_LIFE_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_AVERAGE_SHELF_LIFE]
        ADD CONSTRAINT [DF_SNAP_AVERAGE_SHELF_LIFE_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_AVERAGE_SHELF_LIFE'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_BEST_PRICE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_BEST_PRICE]
        (
           [BEST_PRICE_INTFID]     [NUMERIC](38, 0) NOT NULL,
           [ITEM_ID]               [VARCHAR](200) NULL,
           [ITEM_NO]               [VARCHAR](200) NULL,
           [ITEM_DESC]             [VARCHAR](250) NULL,
           [BEGINNING_WAC_PACKAGE] [VARCHAR](200) NULL,
           [SELLING_PRICE]         [VARCHAR](200) NULL,
           [INITIAL_BEST_PRICE]    [VARCHAR](200) NULL,
           [INITIAL_DISCOUNT]      [VARCHAR](200) NULL,
           [CONTRACT_NO]           [VARCHAR](200) NULL,
           [CONTRACT_ID]           [VARCHAR](200) NULL,
           [ACCOUNT_ID]            [VARCHAR](200) NULL,
           [CONTRACT_START_DATE]   [VARCHAR](200)  NULL,
           [CONTRACT_END_DATE]     [VARCHAR](200)  NULL,
           [PERIOD]                [VARCHAR](200) NULL,
           [CREATED_BY]            [VARCHAR](200) NULL,
           [CREATED_DATE]          [DATETIME] NULL,
           [MODIFIED_BY]           [VARCHAR](200) NULL,
           [MODIFIED_DATE]         [DATETIME] NULL,
           [BATCH_ID]              [VARCHAR](200) NULL,
           [SOURCE]                [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR] [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]    [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_BEST_PRICE')
                      AND NAME = 'DF_SNAP_BEST_PRICE_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_BEST_PRICE]
        ADD CONSTRAINT [DF_SNAP_BEST_PRICE_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_BEST_PRICE'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_CFP'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_CFP]
        (
           [CFP_INTFID]                  [NUMERIC](38, 0) NOT NULL,
           [CFP_ID]                      [VARCHAR](200) NULL,
           [CFP_NO]                      [VARCHAR](200) NULL,
           [CFP_NAME]                    [VARCHAR](200) NULL,
           [CFP_TYPE]                    [VARCHAR](200) NULL,
           [CFP_CATEGORY]                [VARCHAR](200) NULL,
           [CFP_DESIGNATION]             [VARCHAR](200) NULL,
           [PARENT_CFP_ID]               [VARCHAR](200) NULL,
           [PARENT_CFP_NAME]             [VARCHAR](200) NULL,
           [CFP_STATUS]                  [VARCHAR](200) NULL,
           [CFP_TRADE_CLASS]             [VARCHAR](200) NULL,
           [CFP_START_DATE]              [VARCHAR](200)  NULL,
           [CFP_END_DATE]                [VARCHAR](200)  NULL,
           [TRADING_PARTNER_ID]          [VARCHAR](200) NULL,
           [IDENTIFIER_CODE_QUALIFIER]   [VARCHAR](200) NULL,
           [TRADING_PARTNER_NO]          [VARCHAR](200) NULL,
           [TRADING_PARTNER_NAME]        [VARCHAR](200) NULL,
           [TRADING_PARTNER_CONTRACT_NO] [VARCHAR](200) NULL,
           [START_DATE]                  [VARCHAR](200)  NULL,
           [END_DATE]                    [VARCHAR](200)  NULL,
           [CONTRACT_ID]                 [VARCHAR](200) NULL,
           [ATTACHED_STATUS]             [VARCHAR](200) NULL,
           [ATTACHED_DATE]               [VARCHAR](200)  NULL,
           [TRADE_CLASS]                 [VARCHAR](200) NULL,
           [TRADE_CLASS_START_DATE]      [VARCHAR](200)  NULL,
           [TRADE_CLASS_END_DATE]        [VARCHAR](200)  NULL,
           [INTERNAL_NOTES]              [VARCHAR](1000) NULL,
           [CREATED_BY]                  [VARCHAR](200) NULL,
           [CREATED_DATE]                [DATETIME] NULL,
           [MODIFIED_BY]                 [VARCHAR](200) NULL,
           [MODIFIED_DATE]               [DATETIME] NULL,
           [BATCH_ID]                    [VARCHAR](200) NULL,
           [SOURCE]                      [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]       [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]          [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_CFP')
                      AND NAME = 'DF_SNAP_CFP_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_CFP]
        ADD CONSTRAINT [DF_SNAP_CFP_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO
-----------------------------------AGN-428----------------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_CFP' AND COLUMN_NAME  = 'SALES_INCLUSION' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_CFP
        ADD SALES_INCLUSION VARCHAR(200)  NULL
 END
 GO
 
IF EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.tables
		WHERE TABLE_NAME = 'SNAP_CFP_2017_SEMI_ANNUAL_2'
		)
BEGIN
	IF NOT EXISTS (
			SELECT 1
			FROM INFORMATION_SCHEMA.COLUMNS
			WHERE TABLE_NAME = 'SNAP_CFP_2017_SEMI_ANNUAL_2'
				AND COLUMN_NAME = 'SALES_INCLUSION'
				AND TABLE_SCHEMA = 'DBO'
			)
	BEGIN
		DROP TABLE SNAP_CFP_2017_SEMI_ANNUAL_2
	END
END
GO

------- ADD COLUMN -------
IF NOT EXISTS(
      SELECT 1 
	  FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_CFP'
	  AND COLUMN_NAME  = 'CFP_ELIGIBILITY_DATE' 
	  AND TABLE_SCHEMA = 'DBO')
    
	BEGIN
                
      ALTER TABLE SNAP_CFP
        ADD CFP_ELIGIBILITY_DATE DATETIME NULL
    END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_CFP'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_COMPANY_IDENTIFIER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_COMPANY_IDENTIFIER]
        (
           [COMPANY_IDENTIFIER_INTFID]      [NUMERIC](38, 0) NOT NULL,
           [COMPANY_ID]                     [VARCHAR](200) NULL,
           [COMPANY_NO]                     [VARCHAR](200) NULL,
           [COMPANY_NAME]                   [VARCHAR](200) NULL,
           [IDENTIFIER_CODE_QUALIFIER]      [VARCHAR](200) NULL,
           [IDENTIFIER_CODE_QUALIFIER_NAME] [VARCHAR](200) NULL,
           [COMPANY_IDENTIFIER]             [VARCHAR](200) NULL,
           [IDENTIFIER_STATUS]              [VARCHAR](200) NULL,
           [START_DATE]                     [VARCHAR](200)  NULL,
           [END_DATE]                       [VARCHAR](200)  NULL,
           [ENTITY_CODE]                    [VARCHAR](200) NULL,
           [CREATED_BY]                     [VARCHAR](200) NULL,
           [CREATED_DATE]                   [DATETIME] NULL,
           [MODIFIED_BY]                    [VARCHAR](200) NULL,
           [MODIFIED_DATE]                  [DATETIME] NULL,
           [BATCH_ID]                       [VARCHAR](200) NULL,
           [SOURCE]                         [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]          [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]             [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_COMPANY_IDENTIFIER')
                      AND NAME = 'DF_SNAP_COMPANY_IDENTIFIER_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_COMPANY_IDENTIFIER]
        ADD CONSTRAINT [DF_SNAP_COMPANY_IDENTIFIER_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_COMPANY_IDENTIFIER'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_COMPANY_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_COMPANY_MASTER]
        (
           [COMPANY_MASTER_INTFID] [NUMERIC](38, 0) NOT NULL,
           [ORGANIZATION_KEY]      [VARCHAR](200) NULL,
           [COMPANY_ID]            [VARCHAR](200) NULL,
           [COMPANY_NO]            [VARCHAR](200) NULL,
           [COMPANY_NAME]          [VARCHAR](200) NULL,
           [COMPANY_TYPE]          [VARCHAR](200) NULL,
           [COMPANY_STATUS]        [VARCHAR](200) NULL,
           [LIVES]                 [VARCHAR](200) NULL,
           [COMPANY_END_DATE]      [VARCHAR](200)  NULL,
           [UDC1]                  [VARCHAR](200) NULL,
           [UDC2]                  [VARCHAR](200) NULL,
           [UDC3]                  [VARCHAR](200) NULL,
           [UDC4]                  [VARCHAR](200) NULL,
           [UDC5]                  [VARCHAR](200) NULL,
           [UDC6]                  [VARCHAR](200) NULL,
           [COMPANY_GROUP]         [VARCHAR](200) NULL,
           [FINANCIAL_SYSTEM]      [VARCHAR](200) NULL,
           [ADDRESS_1]             [VARCHAR](200) NULL,
           [ADDRESS_2]             [VARCHAR](200) NULL,
           [CITY]                  [VARCHAR](200) NULL,
           [STATE]                 [VARCHAR](200) NULL,
           [ZIP_CODE]              [VARCHAR](200) NULL,
           [COUNTRY]               [VARCHAR](200) NULL,
           [REGION_CODE]           [VARCHAR](200) NULL,
           [LAST_UPDATED_DATE]     [VARCHAR](200)  NULL,
           [COMPANY_START_DATE]    [VARCHAR](200)  NULL,
           [STATUS]                [VARCHAR](200) NULL,
           [COMPANY_CATEGORY]      [VARCHAR](200) NULL,
           [CREATED_BY]            [VARCHAR](200) NULL,
           [CREATED_DATE]          [DATETIME] NULL,
           [MODIFIED_BY]           [VARCHAR](200) NULL,
           [MODIFIED_DATE]         [DATETIME] NULL,
           [BATCH_ID]              [VARCHAR](200) NULL,
           [SOURCE]                [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR] [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]    [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_COMPANY_MASTER')
                      AND NAME = 'DF_SNAP_COMPANY_MASTER_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_COMPANY_MASTER]
        ADD CONSTRAINT [DF_SNAP_COMPANY_MASTER_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_COMPANY_MASTER'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_COMPANY_PARENT'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_COMPANY_PARENT]
        (
           [PARENT_DETAILS_INTFID]   [NUMERIC](38, 0) NOT NULL,
           [COMPANY_ID]              [VARCHAR](200) NULL,
           [PARENT_COMPANY_ID]       [VARCHAR](200) NULL,
           [PARENT_START_DATE]       [VARCHAR](200)  NULL,
           [PARENT_END_DATE]         [VARCHAR](200)  NULL,
           [PRIOR_PARENT_COMPANY_ID] [VARCHAR](200) NULL,
           [PRIOR_PARENT_START_DATE] [VARCHAR](200)  NULL,
           [LAST_UPDATED_DATE]       [VARCHAR](200)  NULL,
           [CREATED_BY]              [VARCHAR](200) NULL,
           [CREATED_DATE]            [DATETIME] NULL,
           [MODIFIED_BY]             [VARCHAR](200) NULL,
           [MODIFIED_DATE]           [DATETIME] NULL,
           [BATCH_ID]                [VARCHAR](200) NULL,
           [SOURCE]                  [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]   [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]      [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_COMPANY_PARENT')
                      AND NAME = 'DF_SNAP_COMPANY_PARENT_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_COMPANY_PARENT]
        ADD CONSTRAINT [DF_SNAP_COMPANY_PARENT_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_COMPANY_PARENT'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_COMPANY_TRADE_CLASS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_COMPANY_TRADE_CLASS]
        (
           [TRADE_CLASS_INTFID]           [NUMERIC](38, 0) NOT NULL,
           [COMPANY_ID]                   [VARCHAR](200) NULL,
           [TRADE_CLASS_START_DATE]       [VARCHAR](200)  NULL,
           [TRADE_CLASS_END_DATE]         [VARCHAR](200)  NULL,
           [TRADE_CLASS]                  [VARCHAR](200) NULL,
           [PRIOR_TRADE_CLASS]            [VARCHAR](200) NULL,
           [PRIOR_TRADE_CLASS_START_DATE] [VARCHAR](200)  NULL,
           [LAST_UPDATED_DATE]            [VARCHAR](200)  NULL,
           [CREATED_BY]                   [VARCHAR](200) NULL,
           [CREATED_DATE]                 [DATETIME] NULL,
           [MODIFIED_BY]                  [VARCHAR](200) NULL,
           [MODIFIED_DATE]                [DATETIME] NULL,
           [BATCH_ID]                     [VARCHAR](200) NULL,
           [SOURCE]                       [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]        [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]           [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_COMPANY_TRADE_CLASS')
                      AND NAME = 'DF_SNAP_COMPANY_TRADE_CLASS_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_COMPANY_TRADE_CLASS]
        ADD CONSTRAINT [DF_SNAP_COMPANY_TRADE_CLASS_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_COMPANY_TRADE_CLASS'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_CONTRACT_HEADER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_CONTRACT_HEADER]
        (
           [CONTRACT_HEADER_INTFID]       [NUMERIC](38, 0) NOT NULL,
           [ORGANIZATION_KEY]             [VARCHAR](200) NULL,
           [CONTRACT_ID]                  [VARCHAR](200) NULL,
           [CONTRACT_NO]                  [VARCHAR](200) NULL,
           [CONTRACT_NAME]                [VARCHAR](200) NULL,
           [CONTRACT_TYPE]                [VARCHAR](200) NULL,
           [CONTRACT_STATUS]              [VARCHAR](200) NULL,
           [DOC_TYPE]                     [VARCHAR](200) NULL,
           [DOC_CLASS]                    [VARCHAR](200) NULL,
           [COMPANY_IDENT_CODE_QUALIFIER] [VARCHAR](200) NULL,
           [COMPANY_NO]                   [VARCHAR](200) NULL,
           [COMPANY_NAME]                 [VARCHAR](200) NULL,
           [TP_IDENTIFIER_CODE_QUALIFIER] [VARCHAR](200) NULL,
           [TRADING_PARTNER_NO]           [VARCHAR](200) NULL,
           [TRADING_PARTNER_NAME]         [VARCHAR](200) NULL,
           [TP_CONTRACT_ID]               [VARCHAR](200) NULL,
           [TP_CONTRACT_NAME]             [VARCHAR](200) NULL,
           [TRADE_CLASS]                  [VARCHAR](200) NULL,
           [START_DATE]                   [VARCHAR](200)  NULL,
           [END_DATE]                     [VARCHAR](200)  NULL,
           [TERM]                         [VARCHAR](200) NULL,
           [RENEGOTIATION_START_DATE]     [VARCHAR](200)  NULL,
           [RENEGOTIATION_END_DATE]       [VARCHAR](200)  NULL,
           [PRICEPROTECTION_START_DATE]   [VARCHAR](200)  NULL,
           [PRICEPROTECTION_END_DATE]     [VARCHAR](200)  NULL,
           [ADVANCE_NOTICE_DAYS]          [VARCHAR](200) NULL,
           [INSIDE_OWNER]                 [VARCHAR](200) NULL,
           [INSIDE_PHONE]                 [VARCHAR](200) NULL,
           [INSIDE_AUTHOR]                [VARCHAR](200) NULL,
           [INSIDE_ADDITIONAL]            [VARCHAR](200) NULL,
           [INSIDE_ADDITIONAL_NAME]       [VARCHAR](200) NULL,
           [INSIDE_ADDITIONAL_PHONE]      [VARCHAR](200) NULL,
           [OUTSIDE_OWNER]                [VARCHAR](200) NULL,
           [OUTSIDE_PHONE]                [VARCHAR](200) NULL,
           [OUTSIDE_AUTHOR]               [VARCHAR](200) NULL,
           [OUTSIDE_ADDITIONAL]           [VARCHAR](200) NULL,
           [OUTSIDE_ADDITIONAL_NAME]      [VARCHAR](200) NULL,
           [OUTSIDE_ADDITIONAL_PHONE]     [VARCHAR](200) NULL,
           [AFFILIATED_CONTRACT_INFO]     [VARCHAR](200) NULL,
           [SHIPPING_TERMS]               [VARCHAR](200) NULL,
           [PROPOSAL_START_DATE]          [VARCHAR](200)  NULL,
           [PROPOSAL_END_DATE]            [VARCHAR](200)  NULL,
           [ORIGINAL_START_DATE]          [VARCHAR](200)  NULL,
           [ORIGINAL_END_DATE]            [VARCHAR](200)  NULL,
           [AWARD_STATUS]                 [VARCHAR](200) NULL,
           [LAST_UPDATED_DATE]            [VARCHAR](200)  NULL,
           [PRICE_ESCALATION_CLAUSE]      [VARCHAR](200) NULL,
           [EXEMPT_FROM_LOW_PRICE]        [VARCHAR](200) NULL,
           [PRICE_RESET_INDICATOR]        [VARCHAR](200) NULL,
           [CANCELLATION_CLAUSE]          [VARCHAR](200) NULL,
           [MOST_FAVORED_NATION]          [VARCHAR](200) NULL,
           [CATEGORY]                     [VARCHAR](200) NULL,
           [CURRENCY]                     [VARCHAR](200) NULL,
           [MINIMUM_ORDER]                [VARCHAR](200) NULL,
           [PAYMENT_TERMS]                [VARCHAR](200) NULL,
           [CONTRACT_ALIAS_NO]            [VARCHAR](200) NULL,
           [CONTRACT_ALIAS_NAME]          [VARCHAR](200) NULL,
           [CREATED_BY]                   [VARCHAR](200) NULL,
           [CREATED_DATE]                 [DATETIME] NULL,
           [MODIFIED_BY]                  [VARCHAR](200) NULL,
           [MODIFIED_DATE]                [DATETIME] NULL,
           [BATCH_ID]                     [VARCHAR](200) NULL,
           [SOURCE]                       [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]        [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]           [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_CONTRACT_HEADER')
                      AND NAME = 'DF_SNAP_CONTRACT_HEADER_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_CONTRACT_HEADER]
        ADD CONSTRAINT [DF_SNAP_CONTRACT_HEADER_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

------- ADD COLUMN --------

IF NOT EXISTS(SELECT 1 
              FROM INFORMATION_SCHEMA.COLUMNS
              WHERE TABLE_NAME = 'SNAP_CONTRACT_HEADER' 
			  AND COLUMN_NAME  = 'CONTRACT_ELIGIBLE_DATE' 
			  AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_CONTRACT_HEADER
        ADD CONTRACT_ELIGIBLE_DATE DATETIME NULL
    END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_CONTRACT_HEADER'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_CPI'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_CPI]
        (
           [CPI_INTFID]            [NUMERIC](38, 0) NOT NULL,
           [INDEX_ID]              [VARCHAR](200) NULL,
           [STATUS]                [VARCHAR](200) NULL,
           [INDEX_TYPE]            [VARCHAR](200) NULL,
           [EFFECTIVE_DATE]        [VARCHAR](200)  NULL,
           [INDEX_VALUE]           [VARCHAR](200) NULL,
           [CREATED_BY]            [VARCHAR](200) NULL,
           [CREATED_DATE]          [DATETIME] NULL,
           [MODIFIED_BY]           [VARCHAR](200) NULL,
           [MODIFIED_DATE]         [DATETIME] NULL,
           [BATCH_ID]              [VARCHAR](200) NULL,
           [SOURCE]                [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR] [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]    [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_CPI')
                      AND NAME = 'DF_SNAP_CPI_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_CPI]
        ADD CONSTRAINT [DF_SNAP_CPI_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_CPI'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_FORECAST_SALES'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_FORECAST_SALES]
        (
           [FORECAST_SALES_INTFID]          [NUMERIC](38, 0) NOT NULL,
           [FORECAST_YEAR]                  [VARCHAR](200) NULL,
           [FORECAST_MONTH]                 [VARCHAR](200) NULL,
           [NDC]                            [VARCHAR](200) NULL,
           [FORECAST_START_DATE]            [VARCHAR](200)  NULL,
           [UNITS]                          [VARCHAR](200) NULL,
           [DOLLARS]                        [VARCHAR](200) NULL,
           [FORECAST_VALUE_TYPE]            [VARCHAR](200) NULL,
           [FORECAST_VALUE]                 [VARCHAR](200) NULL,
           [PRODUCT]                        [VARCHAR](200) NULL,
           [BRAND]                          [VARCHAR](200) NULL,
           [SEGMENT]                        [VARCHAR](200) NULL,
           [PERCENTAGE_ESTIMATE]            [VARCHAR](200) NULL,
           [PERCENTAGE_ESTIMATE_YEAR]       [VARCHAR](200) NULL,
           [ACTUAL_SALES_PERCENTAGE]        [VARCHAR](200) NULL,
           [ACTUAL_SALES_PERCENTAGE_MONTH]  [VARCHAR](200) NULL,
           [FORECASTED_SALES_PERCENTAGE]    [VARCHAR](200) NULL,
           [FORECASTED_SALES_PERCENT_MONTH] [VARCHAR](200) NULL,
           [FORECAST_VER]                   [VARCHAR](200) NULL,
           [PRICE]                          [VARCHAR](200) NULL,
           [COUNTRY]                        [VARCHAR](200) NULL,
           [FORECAST_NAME]                  [VARCHAR](200) NULL,
           [FORECAST_DATE]                  [VARCHAR](200)  NULL,
           [CREATED_BY]                     [VARCHAR](200) NULL,
           [CREATED_DATE]                   [DATETIME] NULL,
           [MODIFIED_BY]                    [VARCHAR](200) NULL,
           [MODIFIED_DATE]                  [DATETIME] NULL,
           [BATCH_ID]                       [VARCHAR](200) NULL,
           [SOURCE]                         [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]          [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]             [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_FORECAST_SALES')
                      AND NAME = 'DF_SNAP_FORECAST_SALES_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_FORECAST_SALES]
        ADD CONSTRAINT [DF_SNAP_FORECAST_SALES_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_FORECAST_SALES'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_FORMULA_DETAILS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_FORMULA_DETAILS]
        (
           [FORMULA_DETAILS_INTFID] [NUMERIC](38, 0) NOT NULL,
           [FORMULA_ID]             [VARCHAR](200) NULL,
           [FORMULA_NO]             [VARCHAR](200) NULL,
           [FORMULA_DESC]           [VARCHAR](200) NULL,
           [COMPANY_ID]             [VARCHAR](200) NULL,
           [ITEM_ID]                [VARCHAR](200) NULL,
           [START_DATE]             [VARCHAR](200)  NULL,
           [END_DATE]               [VARCHAR](200)  NULL,
           [REBATE_PERCENT_1]       [VARCHAR](200) NULL,
           [REBATE_PERCENT_2]       [VARCHAR](200) NULL,
           [REBATE_PERCENT_3]       [VARCHAR](200) NULL,
           [CONTRACT_PRICE_1]       [VARCHAR](200) NULL,
           [CONTRACT_PRICE_2]       [VARCHAR](200) NULL,
           [CONTRACT_PRICE_3]       [VARCHAR](200) NULL,
           [CREATED_BY]             [VARCHAR](200) NULL,
           [CREATED_DATE]           [DATETIME] NULL,
           [MODIFIED_BY]            [VARCHAR](200) NULL,
           [MODIFIED_DATE]          [DATETIME] NULL,
           [BATCH_ID]               [VARCHAR](200) NULL,
           [SOURCE]                 [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]  [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]     [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_FORMULA_DETAILS')
                      AND NAME = 'DF_SNAP_FORMULA_DETAILS_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_FORMULA_DETAILS]
        ADD CONSTRAINT [DF_SNAP_FORMULA_DETAILS_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_FORMULA_DETAILS'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_GL_BALANCE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_GL_BALANCE]
        (
           [GL_BALANCE_INTFID]     [NUMERIC](38, 0) NOT NULL,
           [ACCOUNT_ID]            [VARCHAR](200) NULL,
           [ACCOUNT_NO]            [VARCHAR](200) NULL,
           [BALANCE]               [VARCHAR](200) NULL,
           [UPLOAD_DATE]           [VARCHAR](200)  NULL,
           [IS_ACTIVE]             [VARCHAR](200) NULL,
           [INSERTED_DATE]         [VARCHAR](200)  NULL,
           [YEAR]                  [VARCHAR](200) NULL,
           [PERIOD]                [VARCHAR](200) NULL,
           [CLOSE_INDICATOR]       [VARCHAR](200) NULL,
           [CREATED_BY]            [VARCHAR](200) NULL,
           [CREATED_DATE]          [DATETIME] NULL,
           [MODIFIED_BY]           [VARCHAR](200) NULL,
           [MODIFIED_DATE]         [DATETIME] NULL,
           [BATCH_ID]              [VARCHAR](200) NULL,
           [SOURCE]                [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR] [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]    [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_GL_BALANCE')
                      AND NAME = 'DF_SNAP_GL_BALANCE_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_GL_BALANCE]
        ADD CONSTRAINT [DF_SNAP_GL_BALANCE_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_GL_BALANCE'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_GL_COST_CENTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_GL_COST_CENTER]
        (
           [GL_COST_CENTER_INTFID] [NUMERIC](38, 0) NOT NULL,
           [COMPANY_COST_CENTER]   [VARCHAR](200) NULL,
           [COMPANY_CODE]          [VARCHAR](200) NULL,
           [NDC8]                  [VARCHAR](200) NULL,
           [UPLOAD_DATE]           [VARCHAR](200)  NULL,
           [CREATED_BY]            [VARCHAR](200) NULL,
           [CREATED_DATE]          [DATETIME] NULL,
           [MODIFIED_BY]           [VARCHAR](200) NULL,
           [MODIFIED_DATE]         [DATETIME] NULL,
           [BATCH_ID]              [VARCHAR](200) NULL,
           [SOURCE]                [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR] [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]    [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_GL_COST_CENTER')
                      AND NAME = 'DF_SNAP_GL_COST_CENTER_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_GL_COST_CENTER]
        ADD CONSTRAINT [DF_SNAP_GL_COST_CENTER_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_GL_COST_CENTER'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_IFP'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_IFP]
        (
           [IFP_INTFID]                   [NUMERIC](38, 0) NOT NULL,
           [IFP_ID]                       [VARCHAR](200) NULL,
           [IFP_NO]                       [VARCHAR](200) NULL,
           [IFP_NAME]                     [VARCHAR](200) NULL,
           [IFP_TYPE]                     [VARCHAR](200) NULL,
           [IFP_CATEGORY]                 [VARCHAR](200) NULL,
           [IFP_DESIGNATION]              [VARCHAR](200) NULL,
           [PARENT_IFP_ID]                [VARCHAR](200) NULL,
           [PARENT_IFP_NAME]              [VARCHAR](200) NULL,
           [IFP_STATUS]                   [VARCHAR](200) NULL,
           [IFP_START_DATE]               [VARCHAR](200)  NULL,
           [IFP_END_DATE]                 [VARCHAR](200)  NULL,
           [ITEM_ID]                      [VARCHAR](200) NULL,
           [IDENTIFIER_CODE_QUALIFIER]    [VARCHAR](200) NULL,
           [ITEM_NO]                      [VARCHAR](200) NULL,
           [ITEM_NAME]                    [VARCHAR](200) NULL,
           [ATTACHED_STATUS]              [VARCHAR](200) NULL,
           [ATTACHED_DATE]                [VARCHAR](200)  NULL,
           [START_DATE]                   [VARCHAR](200)  NULL,
           [END_DATE]                     [VARCHAR](200)  NULL,
           [TOTAL_VOLUME_COMMITMENT]      [VARCHAR](200) NULL,
           [TOTAL_DOLLAR_COMMITMENT]      [VARCHAR](200) NULL,
           [TOTAL_MARKETSHARE_COMMITMENT] [VARCHAR](200) NULL,
           [COMMITMENT_PERIOD]            [VARCHAR](200) NULL,
           [CONTRACT_ID]                  [VARCHAR](200) NULL,
           [CFP_ID]                       [VARCHAR](200) NULL,
           [MFP_ID]                       [VARCHAR](200) NULL,
           [CREATED_BY]                   [VARCHAR](200) NULL,
           [CREATED_DATE]                 [DATETIME] NULL,
           [MODIFIED_BY]                  [VARCHAR](200) NULL,
           [MODIFIED_DATE]                [DATETIME] NULL,
           [BATCH_ID]                     [VARCHAR](200) NULL,
           [SOURCE]                       [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]        [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]           [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_IFP')
                      AND NAME = 'DF_SNAP_IFP_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_IFP]
        ADD CONSTRAINT [DF_SNAP_IFP_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_IFP'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_ITEM_HIERARCHY'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_ITEM_HIERARCHY]
        (
           [ITEM_HIERARCHY_INTF_ID] [NUMERIC](38, 0) NOT NULL,
           [LEVEL0_TAG]             [VARCHAR](200) NULL,
           [LEVEL0]                 [VARCHAR](200) NULL,
           [LEVEL0_ALIAS]           [VARCHAR](200) NULL,
           [LEVEL1]                 [VARCHAR](200) NULL,
           [LEVEL1_ALIAS]           [VARCHAR](200) NULL,
           [LEVEL2]                 [VARCHAR](200) NULL,
           [LEVEL2_ALIAS]           [VARCHAR](200) NULL,
           [LEVEL3]                 [VARCHAR](200) NULL,
           [LEVEL3_ALIAS]           [VARCHAR](200) NULL,
           [LEVEL4]                 [VARCHAR](200) NULL,
           [LEVEL4_ALIAS]           [VARCHAR](200) NULL,
           [LEVEL5]                 [VARCHAR](200) NULL,
           [LEVEL5_ALIAS]           [VARCHAR](200) NULL,
           [LEVEL6]                 [VARCHAR](200) NULL,
           [LEVEL6_ALIAS]           [VARCHAR](200) NULL,
           [LEVEL7]                 [VARCHAR](200) NULL,
           [LEVEL7_ALIAS]           [VARCHAR](200) NULL,
           [LEVEL8]                 [VARCHAR](200) NULL,
           [LEVEL8_ALIAS]           [VARCHAR](200) NULL,
           [LEVEL9]                 [VARCHAR](200) NULL,
           [LEVEL9_ALIAS]           [VARCHAR](200) NULL,
           [LEVEL10]                [VARCHAR](200) NULL,
           [LEVEL10_ALIAS]          [VARCHAR](200) NULL,
           [LEVEL11]                [VARCHAR](200) NULL,
           [LEVEL11_ALIAS]          [VARCHAR](200) NULL,
           [LEVEL12]                [VARCHAR](200) NULL,
           [LEVEL12_ALIAS]          [VARCHAR](200) NULL,
           [LEVEL13]                [VARCHAR](200) NULL,
           [LEVEL13_ALIAS]          [VARCHAR](200) NULL,
           [LEVEL14]                [VARCHAR](200) NULL,
           [LEVEL14_ALIAS]          [VARCHAR](200) NULL,
           [LEVEL15]                [VARCHAR](200) NULL,
           [LEVEL15_ALIAS]          [VARCHAR](200) NULL,
           [LEVEL16]                [VARCHAR](200) NULL,
           [LEVEL16_ALIAS]          [VARCHAR](200) NULL,
           [LEVEL17]                [VARCHAR](200) NULL,
           [LEVEL17_ALIAS]          [VARCHAR](200) NULL,
           [LEVEL18]                [VARCHAR](200) NULL,
           [LEVEL18_ALIAS]          [VARCHAR](200) NULL,
           [LEVEL19]                [VARCHAR](200) NULL,
           [LEVEL19_ALIAS]          [VARCHAR](200) NULL,
           [LEVEL20]                [VARCHAR](200) NULL,
           [LEVEL20_ALIAS]          [VARCHAR](200) NULL,
           [GTN_BRAND]              [VARCHAR](200) NULL,
           [GTN_THERAPEUTIC_CLASS]  [VARCHAR](200) NULL,
           [GTN_COMPANY]            [VARCHAR](200) NULL,
           [CREATED_BY]             [VARCHAR](200) NULL,
           [CREATED_DATE]           [DATETIME] NULL,
           [MODIFIED_BY]            [VARCHAR](200) NULL,
           [MODIFIED_DATE]          [DATETIME] NULL,
           [BATCH_ID]               [VARCHAR](200) NULL,
           [SOURCE]                 [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]  [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]     [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_ITEM_HIERARCHY')
                      AND NAME = 'DF_SNAP_ITEM_HIERARCHY_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_ITEM_HIERARCHY]
        ADD CONSTRAINT [DF_SNAP_ITEM_HIERARCHY_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_ITEM_HIERARCHY'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_ITEM_HIERARCHY_DEFINITION'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_ITEM_HIERARCHY_DEFINITION]
        (
           [ITEM_HIERARCHY_DEFN_INTFID_ID] [NUMERIC](38, 0) NOT NULL,
           [MEMBER]                        [VARCHAR](200) NULL,
           [ALIAS]                         [VARCHAR](200) NULL,
           [BPI_LVL]                       [VARCHAR](200) NULL,
           [CREATED_BY]                    [VARCHAR](200) NULL,
           [CREATED_DATE]                  [DATETIME] NULL,
           [MODIFIED_BY]                   [VARCHAR](200) NULL,
           [MODIFIED_DATE]                 [DATETIME] NULL,
           [BATCH_ID]                      [VARCHAR](200) NULL,
           [SOURCE]                        [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]         [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]            [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_ITEM_HIERARCHY_DEFINITION')
                      AND NAME = 'DF_SNAP_ITEM_HIERARCHY_DEFINITION_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_ITEM_HIERARCHY_DEFINITION]
        ADD CONSTRAINT [DF_SNAP_ITEM_HIERARCHY_DEFINITION_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_ITEM_HIERARCHY_DEFINITION'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_ITEM_IDENTIFIER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_ITEM_IDENTIFIER]
        (
           [ITEM_IDENTIFIER_INTFID]         [NUMERIC](38, 0) NOT NULL,
           [ITEM_ID]                        [VARCHAR](200) NULL,
           [ITEM_NO]                        [VARCHAR](200) NULL,
           [ITEM_NAME]                      [VARCHAR](200) NULL,
           [IDENTIFIER_CODE_QUALIFIER]      [VARCHAR](200) NULL,
           [IDENTIFIER_CODE_QUALIFIER_NAME] [VARCHAR](200) NULL,
           [ITEM_IDENTIFIER]                [VARCHAR](200) NULL,
           [ITEM_STATUS]                    [VARCHAR](200) NULL,
           [START_DATE]                     [VARCHAR](200)  NULL,
           [END_DATE]                       [VARCHAR](200)  NULL,
           [ENTITY_CODE]                    [VARCHAR](200) NULL,
           [CREATED_BY]                     [VARCHAR](200) NULL,
           [CREATED_DATE]                   [DATETIME] NULL,
           [MODIFIED_BY]                    [VARCHAR](200) NULL,
           [MODIFIED_DATE]                  [DATETIME] NULL,
           [ADD_CHG_DEL_INDICATOR]          [VARCHAR](200) NULL,
           [BATCH_ID]                       [VARCHAR](200) NULL,
           [SOURCE]                         [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]             [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_ITEM_IDENTIFIER')
                      AND NAME = 'DF_SNAP_ITEM_IDENTIFIER_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_ITEM_IDENTIFIER]
        ADD CONSTRAINT [DF_SNAP_ITEM_IDENTIFIER_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_ITEM_IDENTIFIER'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_ITEM_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_ITEM_MASTER]
        (
           [ITEM_MASTER_INTFID]             [NUMERIC](38, 0) NOT NULL,
           [ITEM_ID]                        [VARCHAR](200) NULL,
           [ITEM_NO]                        [VARCHAR](200) NULL,
           [ITEM_CODE]                      [VARCHAR](200) NULL,
           [ITEM_NAME]                      [VARCHAR](200) NULL,
           [ITEM_DESC]                      [VARCHAR](250) NULL,
           [PACKAGE_SIZE]                   [VARCHAR](200) NULL,
           [PACKAGE_SIZE_CODE]              [VARCHAR](200) NULL,
           [PACKAGE_SIZE_INTRO_DATE]        [VARCHAR](200)  NULL,
           [UPPS]                           [VARCHAR](200) NULL,
           [ITEM_START_DATE]                [VARCHAR](200)  NULL,
           [ITEM_END_DATE]                  [VARCHAR](200)  NULL,
           [ITEM_STATUS]                    [VARCHAR](200) NULL,
           [MANUFACTURER_ID]                [VARCHAR](200) NULL,
           [MANUFACTURER_NO]                [VARCHAR](200) NULL,
           [MANUFACTURER_NAME]              [VARCHAR](200) NULL,
           [LABELER_CODE]                   [VARCHAR](200) NULL,
           [ORGANIZATION_KEY]               [VARCHAR](200) NULL,
           [ACQUISITION_DATE]               [VARCHAR](200)  NULL,
           [AUTHORIZED_GENERIC]             [VARCHAR](200) NULL,
           [AUTHORIZED_GENERIC_START_DATE]  [VARCHAR](200)  NULL,
           [AUTHORIZED_GENERIC_END_DATE]    [VARCHAR](200)  NULL,
           [BRAND]                          [VARCHAR](200) NULL,
           [FORM]                           [VARCHAR](200) NULL,
           [STRENGTH]                       [VARCHAR](200) NULL,
           [THERAPEUTIC_CLASS]              [VARCHAR](200) NULL,
           [FIRST_SALE_DATE]                [VARCHAR](200)  NULL,
           [ITEM_TYPE_INDICATION]           [VARCHAR](200) NULL,
           [ITEM_CLASS]                     [VARCHAR](200) NULL,
           [ITEM_TYPE]                      [VARCHAR](200) NULL,
           [MARKET_TERMINATION_DATE]        [VARCHAR](200)  NULL,
           [NEW_FORMULATION_INDICATOR]      [VARCHAR](200) NULL,
           [NEW_FORMULATION]                [VARCHAR](200) NULL,
           [NEW_FORMULATION_START_DATE]     [VARCHAR](200)  NULL,
           [NEW_FORMULATION_END_DATE]       [VARCHAR](200)  NULL,
           [PEDIATRIC_EXCLUSIVE_INDICATOR]  [VARCHAR](200) NULL,
           [PEDIATRIC_EXCLUSIVE_START_DATE] [VARCHAR](200)  NULL,
           [PEDIATRIC_EXCLUSIVE_END_DATE]   [VARCHAR](200)  NULL,
           [CLOTTING_FACTOR_INDICATOR]      [VARCHAR](200) NULL,
           [CLOTTING_FACTOR_START_DATE]     [VARCHAR](200)  NULL,
           [CLOTTING_FACTOR_END_DATE]       [VARCHAR](200)  NULL,
           [PRIMARY_UOM]                    [VARCHAR](200) NULL,
           [SECONDARY_UOM]                  [VARCHAR](200) NULL,
           [SHELF_LIFE]                     [VARCHAR](200) NULL,
           [SHELF_LIFE_TYPE]                [VARCHAR](200) NULL,
           [DUAL_PRICING_INDICATOR]         [VARCHAR](200) NULL,
           [ITEM_FAMILY_ID]                 [VARCHAR](200) NULL,
           [UDC1]                           [VARCHAR](200) NULL,
           [UDC2]                           [VARCHAR](200) NULL,
           [UDC3]                           [VARCHAR](200) NULL,
           [UDC4]                           [VARCHAR](200) NULL,
           [UDC5]                           [VARCHAR](200) NULL,
           [UDC6]                           [VARCHAR](200) NULL,
           [ACQUIRED_AMP]                   [VARCHAR](200) NULL,
           [ACQUIRED_BAMP]                  [VARCHAR](200) NULL,
           [OBRA_BAMP]                      [VARCHAR](200) NULL,
           [DRA]                            [VARCHAR](200) NULL,
           [DOSES_PER_UNIT]                 [VARCHAR](200) NULL,
           [STATUS]                         [VARCHAR](200) NULL,
           [DISCONTINUATION_DATE]           [VARCHAR](200)  NULL,
           [LAST_LOT_EXPIRATION_DATE]       [VARCHAR](200)  NULL,
           [DIVESTITURE_DATE]               [VARCHAR](200)  NULL,
           [NON_FEDERAL_EXPIRATION_DATE]    [VARCHAR](200)  NULL,
           [NDC9]                           [VARCHAR](200) NULL,
           [NDC8]                           [VARCHAR](200) NULL,
           [DISPLAY_BRAND]                  [VARCHAR](200) NULL,
           [BRAND_ID]                       [VARCHAR](200) NULL,
           [ITEM_CATEGORY]                  [VARCHAR](200) NULL,
           [BASELINE_AMP]                   [VARCHAR](200) NULL,
           [BASE_CPI_PERIOD]                [VARCHAR](200)  NULL,
           [BASE_CPI]                       [VARCHAR](200) NULL,
           [CREATED_BY]                     [VARCHAR](200) NULL,
           [CREATED_DATE]                   [DATETIME] NULL,
           [MODIFIED_BY]                    [VARCHAR](200) NULL,
           [MODIFIED_DATE]                  [DATETIME] NULL,
           [BATCH_ID]                       [VARCHAR](200) NULL,
           [SOURCE]                         [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]          [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]             [DATETIME] NULL
        )
  END

GO
--------------CEL-1445---------------------cel-1611
IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'BASE_CPI_PRECISION'
                  AND Object_name(object_id) = 'SNAP_ITEM_MASTER')
  BEGIN
      DROP STATISTICS dbo.SNAP_ITEM_MASTER.BASE_CPI_PRECISION 
  END 

GO
IF EXISTS (
		SELECT 1
		FROM information_schema.columns
		WHERE table_name = 'SNAP_ITEM_MASTER'
			AND column_name = 'BASE_CPI_PRECISION'
			AND table_schema = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_ITEM_MASTER DROP COLUMN BASE_CPI_PRECISION
END
	GO
IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'BASELINE_AMP_PRECISION'
                  AND Object_name(object_id) = 'SNAP_ITEM_MASTER')
  BEGIN
      DROP STATISTICS dbo.SNAP_ITEM_MASTER.BASELINE_AMP_PRECISION 
  END 

GO	
IF EXISTS (
		SELECT 1
		FROM information_schema.columns
		WHERE table_name = 'SNAP_ITEM_MASTER'
			AND column_name = 'BASELINE_AMP_PRECISION'
			AND table_schema = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_ITEM_MASTER DROP COLUMN BASELINE_AMP_PRECISION
END
	GO

--------------CEL-1445---------------------cel-1611

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_ITEM_MASTER')
                      AND NAME = 'DF_SNAP_ITEM_MASTER_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_ITEM_MASTER]
        ADD CONSTRAINT [DF_SNAP_ITEM_MASTER_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_ITEM_MASTER'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_ITEM_PRICING'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_ITEM_PRICING]
        (
           [ITEM_PRICING_INTFID]         [NUMERIC](38, 0) NOT NULL,
           [ITEM_ID]                     [VARCHAR](200) NULL,
           [ITEM_NO]                     [VARCHAR](200) NULL,
           [ITEM_NAME]                   [VARCHAR](200) NULL,
           [ITEM_UOM]                    [VARCHAR](200) NULL,
           [PRICING_CODE_QUALIFIER]      [VARCHAR](200) NULL,
           [PRICING_CODE_QUALIFIER_NAME] [VARCHAR](200) NULL,
           [ITEM_PRICE]                  [VARCHAR](200) NULL,
           [PRICING_CODE_STATUS]         [VARCHAR](200) NULL,
           [START_DATE]                  [VARCHAR](200)  NULL,
           [END_DATE]                    [VARCHAR](200)  NULL,
           [ENTITY_CODE]                 [VARCHAR](200) NULL,
           [CREATED_BY]                  [VARCHAR](200) NULL,
           [CREATED_DATE]                [DATETIME] NULL,
           [MODIFIED_BY]                 [VARCHAR](200) NULL,
           [MODIFIED_DATE]               [DATETIME] NULL,
           [BATCH_ID]                    [VARCHAR](200) NULL,
           [SOURCE]                      [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]       [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]          [DATETIME] NULL
        )
  END

GO

--------CEL-1445,CEL-1611---------------------
IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'ITEM_PRICE_PRECISION'
                  AND Object_name(object_id) = 'SNAP_ITEM_PRICING')
  BEGIN
      DROP STATISTICS dbo.SNAP_ITEM_PRICING.ITEM_PRICE_PRECISION 
  END 

GO
IF EXISTS (
		SELECT 1
		FROM information_schema.columns
		WHERE table_name = 'SNAP_ITEM_PRICING'
			AND column_name = 'ITEM_PRICE_PRECISION'
			AND table_schema = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_ITEM_PRICING DROP COLUMN ITEM_PRICE_PRECISION
END
	GO
------------------------------CEL-1445,CEL-1611

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_ITEM_PRICING')
                      AND NAME = 'DF_SNAP_ITEM_PRICING_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_ITEM_PRICING]
        ADD CONSTRAINT [DF_SNAP_ITEM_PRICING_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_ITEM_PRICING'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_LOT_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_LOT_MASTER]
        (
           [LOT_MASTER_INTFID]     [NUMERIC](38, 0) NOT NULL,
           [ITEM_ID]               [VARCHAR](200) NULL,
           [LOT_NO]                [VARCHAR](200) NULL,
           [LAST_LOT_SOLD_DATE]    [VARCHAR](200)  NULL,
           [LOT_EXPIRATION_DATE]   [VARCHAR](200)  NULL,
           [CREATED_BY]            [VARCHAR](200) NULL,
           [CREATED_DATE]          [DATETIME] NULL,
           [MODIFIED_BY]           [VARCHAR](200) NULL,
           [MODIFIED_DATE]         [DATETIME] NULL,
           [BATCH_ID]              [VARCHAR](200) NULL,
           [SOURCE]                [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR] [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]    [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_LOT_MASTER')
                      AND NAME = 'DF_SNAP_LOT_MASTER_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_LOT_MASTER]
        ADD CONSTRAINT [DF_SNAP_LOT_MASTER_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_LOT_MASTER'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_MASTER_DATA_ATTRIBUTE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_MASTER_DATA_ATTRIBUTE]
        (
           [DATA_ATTRIBUTE_INTF_ID] [NUMERIC](38, 0) NOT NULL,
           [MASTER_TYPE]            [VARCHAR](200) NULL,
           [MASTER_ID]              [VARCHAR](200) NULL,
           [MASTER_ATTRIBUTE]       [VARCHAR](200) NULL,
           [COLUMN_1]               [VARCHAR](200) NULL,
           [COLUMN_2]               [VARCHAR](200) NULL,
           [COLUMN_3]               [VARCHAR](200) NULL,
           [COLUMN_4]               [VARCHAR](200) NULL,
           [COLUMN_5]               [VARCHAR](200) NULL,
           [COLUMN_6]               [VARCHAR](200) NULL,
           [COLUMN_7]               [VARCHAR](200) NULL,
           [COLUMN_8]               [VARCHAR](200) NULL,
           [COLUMN_9]               [VARCHAR](200) NULL,
           [COLUMN_10]              [VARCHAR](200) NULL,
           [COLUMN_11]              [VARCHAR](200) NULL,
           [COLUMN_12]              [VARCHAR](200) NULL,
           [COLUMN_13]              [VARCHAR](200) NULL,
           [COLUMN_14]              [VARCHAR](200) NULL,
           [COLUMN_15]              [VARCHAR](200) NULL,
           [COLUMN_16]              [VARCHAR](200) NULL,
           [COLUMN_17]              [VARCHAR](200) NULL,
           [COLUMN_18]              [VARCHAR](200) NULL,
           [COLUMN_19]              [VARCHAR](200) NULL,
           [COLUMN_20]              [VARCHAR](200) NULL,
           [CREATED_BY]             [VARCHAR](200) NULL,
           [CREATED_DATE]           [DATETIME] NULL,
           [MODIFIED_BY]            [VARCHAR](200) NULL,
           [MODIFIED_DATE]          [DATETIME] NULL,
           [BATCH_ID]               [VARCHAR](200) NULL,
           [SOURCE]                 [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]  [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]     [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_MASTER_DATA_ATTRIBUTE')
                      AND NAME = 'DF_SNAP_MASTER_DATA_ATTRIBUTE_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_MASTER_DATA_ATTRIBUTE]
        ADD CONSTRAINT [DF_SNAP_MASTER_DATA_ATTRIBUTE_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_MASTER_DATA_ATTRIBUTE'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_PRICE_SCHEDULE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_PRICE_SCHEDULE]
        (
           [PRICE_SCHEDULE_INTFID]       [NUMERIC](38, 0) NOT NULL,
           [PRICE_SCHEDULE_ID]           [VARCHAR](200) NULL,
           [PRICE_SCHEDULE_NO]           [VARCHAR](200) NULL,
           [PRICE_SCHEDULE_NAME]         [VARCHAR](200) NULL,
           [PRICE_SCHEDULE_TYPE]         [VARCHAR](200) NULL,
           [PRICE_SCHEDULE_CATEGORY]     [VARCHAR](200) NULL,
           [TRADE_CLASS]                 [VARCHAR](200) NULL,
           [PRICE_SCHEDULE_DESIGNATION]  [VARCHAR](200) NULL,
           [PARENT_PRICE_SCHEDULE_ID]    [VARCHAR](200) NULL,
           [PARENT_PRICE_SCHEDULE_NAME]  [VARCHAR](200) NULL,
           [PRICE_SCHEDULE_STATUS]       [VARCHAR](200) NULL,
           [PRICE_SCHEDULE_START_DATE]   [VARCHAR](200)  NULL,
           [PRICE_SCHEDULE_END_DATE]     [VARCHAR](200)  NULL,
           [ITEM_ID]                     [VARCHAR](200) NULL,
           [IDENTIFIER_CODE_QUALIFIER]   [VARCHAR](200) NULL,
           [ITEM_NO]                     [VARCHAR](200) NULL,
           [ITEM_NAME]                   [VARCHAR](200) NULL,
           [ATTACHED_STATUS]             [VARCHAR](200) NULL,
           [ATTACHED_DATE]               [VARCHAR](200)  NULL,
           [CONTRACT_PRICE_START_DATE]   [VARCHAR](200)  NULL,
           [CONTRACT_PRICE_END_DATE]     [VARCHAR](200)  NULL,
           [PRICE_TYPE]                  [VARCHAR](200) NULL,
           [CONTRACT_PRICE]              [VARCHAR](200) NULL,
           [PRICE_REVISION]              [VARCHAR](200) NULL,
           [REVISION_DATE]               [VARCHAR](200)  NULL,
           [PRICE_PROTECTION_START_DATE] [VARCHAR](200)  NULL,
           [PRICE_PROTECTION_END_DATE]   [VARCHAR](200)  NULL,
           [PRICE_TOLERANCE_TYPE]        [VARCHAR](200) NULL,
           [PRICE_TOLERANCE]             [VARCHAR](200) NULL,
           [BASE_PRICE]                  [VARCHAR](200) NULL,
           [PRICE_TOLERANCE_FREQUENCY]   [VARCHAR](200) NULL,
           [PRICE_TOLERANCE_INTERVAL]    [VARCHAR](200) NULL,
           [PRICE_PLAN_ID]               [VARCHAR](200) NULL,
           [PRICE_PLAN_NAME]             [VARCHAR](200) NULL,
           [SUGGESTED_PRICE]             [VARCHAR](200) NULL,
           [CONTRACT_ID]                 [VARCHAR](200) NULL,
           [CFP_ID]                      [VARCHAR](200) NULL,
           [MFP_ID]                      [VARCHAR](200) NULL,
           [IFP_ID]                      [VARCHAR](200) NULL,
           [PRICE]                       [VARCHAR](200) NULL,
           [CREATED_BY]                  [VARCHAR](200) NULL,
           [CREATED_DATE]                [DATETIME] NULL,
           [MODIFIED_BY]                 [VARCHAR](200) NULL,
           [MODIFIED_DATE]               [DATETIME] NULL,
           [BATCH_ID]                    [VARCHAR](200) NULL,
           [SOURCE]                      [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]       [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]          [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_PRICE_SCHEDULE')
                      AND NAME = 'DF_SNAP_PRICE_SCHEDULE_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_PRICE_SCHEDULE]
        ADD CONSTRAINT [DF_SNAP_PRICE_SCHEDULE_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

----------------------------AGN-429----------------------------

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'INTERNAL_NOTES' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD INTERNAL_NOTES VARCHAR(200) NULL
END
  GO
  
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'PRICE_PROTECTION_STATUS' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD PRICE_PROTECTION_STATUS VARCHAR(200) NULL
END
  GO
  
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'PRICE_PROTECTION_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD PRICE_PROTECTION_PRICE_TYPE VARCHAR(200) NULL
END
  GO
  
  IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'NEP' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD NEP VARCHAR(200) NULL
END
  GO
    IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'NEP_FORMULA' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD NEP_FORMULA VARCHAR(200) NULL
END
  GO
  
      IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'BASE_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD BASE_PRICE_TYPE VARCHAR(200) NULL
END
  GO
      IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'NET_BASE_PRICE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD NET_BASE_PRICE VARCHAR(200) NULL
END
  GO
    IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'NET_BASE_PRICE_FORMULA' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD NET_BASE_PRICE_FORMULA VARCHAR(200) NULL
END
  GO
  
   IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'SUBSEQUENT_PERIOD_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD SUBSEQUENT_PERIOD_PRICE_TYPE VARCHAR(200) NULL
END
  GO
   IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'NET_SUBSEQUENT_PERIOD_PRICE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD NET_SUBSEQUENT_PERIOD_PRICE VARCHAR(200) NULL
END
  GO
  
     IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'NET_SUBSEQUENT_PERIOD_PRICE_FORMULA' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD NET_SUBSEQUENT_PERIOD_PRICE_FORMULA VARCHAR(200) NULL
END
  GO
  
       IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'MAX_INCREMENTAL_CHANGE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD MAX_INCREMENTAL_CHANGE VARCHAR(200) NULL
END
  GO
  
         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'RESET_ELIGIBLE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD RESET_ELIGIBLE VARCHAR(200) NULL
END
  GO
      IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'RESET_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD RESET_TYPE VARCHAR(200) NULL
END
  GO
  
  IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'RESET_DATE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD RESET_DATE VARCHAR(200) NULL
END
  GO
  
        IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'RESET_INTERVAL' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD RESET_INTERVAL VARCHAR(200) NULL
END
  GO
  
        IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'RESET_FREQUENCY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD RESET_FREQUENCY VARCHAR(200) NULL
END
  GO

         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'RESET_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD RESET_PRICE_TYPE VARCHAR(200) NULL
END
  GO
         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'NET_RESET_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD NET_RESET_PRICE_TYPE VARCHAR(200) NULL
END
  GO
         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'NET_RESET_PRICE_FORMULA' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD NET_RESET_PRICE_FORMULA VARCHAR(200) NULL
END
  GO
         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'NET_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD NET_PRICE_TYPE VARCHAR(200) NULL
END
  GO
         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE' AND COLUMN_NAME  = 'NET_PRICE_TYPE_FORMULA' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_PRICE_SCHEDULE
        ADD NET_PRICE_TYPE_FORMULA VARCHAR(200) NULL
END
  GO

  IF EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.TABLES
		WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE_2017_SEMI_ANNUAL_2'
		)
BEGIN

  IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_PRICE_SCHEDULE_2017_SEMI_ANNUAL_2' 
      AND COLUMN_NAME  = 'INTERNAL_NOTES' 
      AND COLUMN_NAME  = 'PRICE_PROTECTION_STATUS' 
      AND COLUMN_NAME  = 'PRICE_PROTECTION_PRICE_TYPE' 
      AND COLUMN_NAME  = 'NEP' 
      AND COLUMN_NAME  = 'NEP_FORMULA' 
      AND COLUMN_NAME  = 'BASE_PRICE_TYPE' 
      AND COLUMN_NAME  = 'NET_BASE_PRICE' 
      AND COLUMN_NAME  = 'NET_BASE_PRICE_FORMULA' 
      AND COLUMN_NAME  = 'SUBSEQUENT_PERIOD_PRICE_TYPE' 
      AND COLUMN_NAME  = 'NET_SUBSEQUENT_PERIOD_PRICE' 
      AND COLUMN_NAME  = 'NET_SUBSEQUENT_PERIOD_PRICE_FORMULA' 
      AND COLUMN_NAME  = 'MAX_INCREMENTAL_CHANGE' 
      AND COLUMN_NAME  = 'RESET_ELIGIBLE' 
      AND COLUMN_NAME  = 'RESET_TYPE' 
      AND COLUMN_NAME  = 'RESET_DATE' 
      AND COLUMN_NAME  = 'RESET_INTERVAL' 
      AND COLUMN_NAME  = 'RESET_FREQUENCY' 
      AND COLUMN_NAME  = 'RESET_PRICE_TYPE' 
      AND COLUMN_NAME  = 'NET_RESET_PRICE_TYPE' 
      AND COLUMN_NAME  = 'NET_RESET_PRICE_FORMULA' 
      AND COLUMN_NAME  = 'NET_PRICE_TYPE' 
      AND COLUMN_NAME  = 'NET_PRICE_TYPE_FORMULA' 
      AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      DROP TABLE SNAP_PRICE_SCHEDULE_2017_SEMI_ANNUAL_2
  END
  END
  GO
  
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_PRICE_SCHEDULE'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_REBATE_PLAN'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_REBATE_PLAN]
        (
           [REBATE_PLAN_INTFID]         [NUMERIC](38, 0) NOT NULL,
           [REBATE_PLAN_ID]             [VARCHAR](200) NULL,
           [REBATE_PLAN_NO]             [VARCHAR](200) NULL,
           [REBATE_PLAN_NAME]           [VARCHAR](200) NULL,
           [REBATE_PLAN_STATUS]         [VARCHAR](200) NULL,
           [REBATE_PLAN_TYPE]           [VARCHAR](200) NULL,
           [INTERNAL_NOTES]             [VARCHAR](1000) NULL,
           [FORMULA_TYPE]               [VARCHAR](200) NULL,
           [BOGO_ELIGIBLE]              [VARCHAR](200) NULL,
           [SELF_GROWTH_INDICATOR]      [VARCHAR](200) NULL,
           [SELF_GROWTH_REFERENCE]      [VARCHAR](200) NULL,
           [SELF_GROWTH_FROM]           [VARCHAR](200)  NULL,
           [SELF_GROWTH_TO]             [VARCHAR](200)  NULL,
           [MARKET_SHARE_INDICATOR]     [VARCHAR](200) NULL,
           [MARKET_SHARE_REFERENCE]     [VARCHAR](200) NULL,
           [MARKET_SHARE_FROM]          [VARCHAR](200)  NULL,
           [MARKET_SHARE_TO]            [VARCHAR](200)  NULL,
           [TIER_FORMULA_ID]            [VARCHAR](200) NULL,
           [TIER_FORMULA_NO]            [VARCHAR](200) NULL,
           [TIER_FORMULA_NAME]          [VARCHAR](200) NULL,
           [REBATE_STRUCTURE]           [VARCHAR](200) NULL,
           [REBATE_RANGE_BASED_ON]      [VARCHAR](200) NULL,
           [REBATE_BASED_ON]            [VARCHAR](200) NULL,
           [REBATE_RULE]                [VARCHAR](200) NULL,
           [REBATE_PLAN_TIER_ID]        [VARCHAR](200) NULL,
           [TIER_FROM]                  [VARCHAR](200) NULL,
           [TIER_TO]                    [VARCHAR](200) NULL,
           [TIER_LEVEL]                 [VARCHAR](200) NULL,
           [TIER_OPERATOR]              [VARCHAR](200) NULL,
           [TIER_VALUE]                 [VARCHAR](200) NULL,
           [FREE_AMOUNT]                [VARCHAR](200) NULL,
           [TIER_TOLERANCE]             [VARCHAR](200) NULL,
           [SECONDARY_REBATE_PLAN_ID]   [VARCHAR](200) NULL,
           [SECONDARY_REBATE_PLAN_NO]   [VARCHAR](200) NULL,
           [SECONDARY_REBATE_PLAN_NAME] [VARCHAR](200) NULL,
           [CONTRACT_ID]                [VARCHAR](200) NULL,
           [CFP_ID]                     [VARCHAR](200) NULL,
           [MFP_ID]                     [VARCHAR](200) NULL,
           [IFP_ID]                     [VARCHAR](200) NULL,
           [PRICE_SCHEDULE_ID]          [VARCHAR](200) NULL,
           [REBATE_SCHEDULE_ID]         [VARCHAR](200) NULL,
           [CREATED_BY]                 [VARCHAR](200) NULL,
           [CREATED_DATE]               [DATETIME] NULL,
           [MODIFIED_BY]                [VARCHAR](200) NULL,
           [MODIFIED_DATE]              [DATETIME] NULL,
           [BATCH_ID]                   [VARCHAR](200) NULL,
           [SOURCE]                     [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]      [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]         [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_REBATE_PLAN')
                      AND NAME = 'DF_SNAP_REBATE_PLAN_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_REBATE_PLAN]
        ADD CONSTRAINT [DF_SNAP_REBATE_PLAN_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_REBATE_PLAN'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_REBATE_SCHEDULE]
        (
           [REBATE_SCHEDULE_INTFID]         [NUMERIC](38, 0) NOT NULL,
           [REBATE_SCHEDULE_ID]             [VARCHAR](200) NULL,
           [REBATE_SCHEDULE_NO]             [VARCHAR](200) NULL,
           [REBATE_SCHEDULE_NAME]           [VARCHAR](200) NULL,
           [REBATE_SCHEDULE_TYPE]           [VARCHAR](200) NULL,
           [REBATE_PROGRAM_TYPE]            [VARCHAR](200) NULL,
           [REBATE_SCHEDULE_CATEGORY]       [VARCHAR](200) NULL,
           [TRADE_CLASS]                    [VARCHAR](200) NULL,
           [REBATE_SCHEDULE_DESIGNATION]    [VARCHAR](200) NULL,
           [PARENT_REBATE_SCHEDULE_ID]      [VARCHAR](200) NULL,
           [PARENT_REBATE_SCHEDULE_NAME]    [VARCHAR](200) NULL,
           [REBATE_SCHEDULE_STATUS]         [VARCHAR](200) NULL,
           [REBATE_SCHEDULE_TRANS_REF_ID]   [VARCHAR](200) NULL,
           [REBATE_SCHEDULE_TRANS_REF_NO]   [VARCHAR](200) NULL,
           [REBATE_SCHEDULE_TRANS_REF_NAME] [VARCHAR](200) NULL,
           [PAYMENT_METHOD]                 [VARCHAR](200) NULL,
           [PAYMENT_FREQUENCY]              [VARCHAR](200) NULL,
           [PAYMENT_TERMS]                  [VARCHAR](200) NULL,
           [REBATE_FREQUENCY]               [VARCHAR](200) NULL,
           [CALENDAR]                       [VARCHAR](200) NULL,
           [REBATE_PROCESSING_TYPE]         [VARCHAR](200) NULL,
           [VALIDATION_PROFILE]             [VARCHAR](200) NULL,
           [REBATE_RULE_TYPE]               [VARCHAR](200) NULL,
           [REBATE_RULE_ASSOCIATION]        [VARCHAR](200) NULL,
           [REBATE_PLAN_LEVEL]              [VARCHAR](200) NULL,
           [INTEREST_BEARING_INDICATOR]     [VARCHAR](200) NULL,
           [INTEREST_BEARING_BASIS]         [VARCHAR](200) NULL,
           [PAYMENT_GRACE_PERIOD]           [VARCHAR](200) NULL,
           [MAKE_PAYABLE_TO]                [VARCHAR](200) NULL,
           [ADDRESS_1]                      [VARCHAR](200) NULL,
           [ADDRESS_2]                      [VARCHAR](200) NULL,
           [CITY]                           [VARCHAR](200) NULL,
           [STATE]                          [VARCHAR](200) NULL,
           [ZIP_CODE]                       [VARCHAR](200) NULL,
           [ITEM_ID]                        [VARCHAR](200) NULL,
           [IDENTIFIER_CODE_QUALIFIER]      [VARCHAR](200) NULL,
           [ITEM_NO]                        [VARCHAR](200) NULL,
           [ITEM_NAME]                      [VARCHAR](200) NULL,
           [ATTACHED_STATUS]                [VARCHAR](200) NULL,
           [ATTACHED_DATE]                  [VARCHAR](200)  NULL,
           [ITEM_START_DATE]                [VARCHAR](200)  NULL,
           [ITEM_END_DATE]                  [VARCHAR](200)  NULL,
           [FORMULA_TYPE]                   [VARCHAR](200) NULL,
           [FORMULA_ID]                     [VARCHAR](200) NULL,
           [FORMULA_NO]                     [VARCHAR](200) NULL,
           [FORMULA_NAME]                   [VARCHAR](200) NULL,
           [REBATE_PLAN_ID]                 [VARCHAR](200) NULL,
           [REBATE_AMOUNT]                  [VARCHAR](200) NULL,
           [ITEM_REBATE_START_DATE]         [VARCHAR](200)  NULL,
           [ITEM_REBATE_END_DATE]           [VARCHAR](200)  NULL,
           [BUNDLE_NO]                      [VARCHAR](200) NULL,
           [INTERNAL_NOTES]                 [VARCHAR](1000) NULL,
           [CONTRACT_ID]                    [VARCHAR](200) NULL,
           [CFP_ID]                         [VARCHAR](200) NULL,
           [MFP_ID]                         [VARCHAR](200) NULL,
           [IFP_ID]                         [VARCHAR](200) NULL,
           [PRICE_SCHEDULE_ID]              [VARCHAR](200) NULL,
           [UDC1]                           [VARCHAR](200) NULL,
           [UDC2]                           [VARCHAR](200) NULL,
           [UDC3]                           [VARCHAR](200) NULL,
           [UDC4]                           [VARCHAR](200) NULL,
           [UDC5]                           [VARCHAR](200) NULL,
           [UDC6]                           [VARCHAR](200) NULL,
           [FORMULA_METHOD_ID]              [VARCHAR](200) NULL,
           [CREATED_BY]                     [VARCHAR](200) NULL,
           [CREATED_DATE]                   [DATETIME] NULL,
           [MODIFIED_BY]                    [VARCHAR](200) NULL,
           [MODIFIED_DATE]                  [DATETIME] NULL,
           [BATCH_ID]                       [VARCHAR](200) NULL,
           [SOURCE]                         [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]          [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]             [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_REBATE_SCHEDULE')
                      AND NAME = 'DF_SNAP_REBATE_SCHEDULE_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_REBATE_SCHEDULE]
        ADD CONSTRAINT [DF_SNAP_REBATE_SCHEDULE_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO
-------------------------- COLUMN ADDITION IN SNAP_REBATE_SCHEDULE --------------------------
 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'DEDUCTION_INCLUSION'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD DEDUCTION_INCLUSION VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'REBATE_SCHEDULE_ALIAS_ID'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD REBATE_SCHEDULE_ALIAS_ID VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'PAYMENT_LEVEL'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD PAYMENT_LEVEL VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'EVALUATION_RULE_LEVEL'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD EVALUATION_RULE_LEVEL VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'EVALUATION_RULE'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD EVALUATION_RULE VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'EVALUATION_RULE_BUNDLE'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD EVALUATION_RULE_BUNDLE VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'CALCULATION_TYPE'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD CALCULATION_TYPE VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'CALCULATION_RULE'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD CALCULATION_RULE VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'CALCULATION_RULE_LEVEL'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD CALCULATION_RULE_LEVEL VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'CALCULATION_RULE_BUNDLE'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD CALCULATION_RULE_BUNDLE VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'NET_SALES_FORMULA'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD NET_SALES_FORMULA VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'NET_SALES_RULE'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD NET_SALES_RULE VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'DEDUCTION_CALENDAR_NO'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD DEDUCTION_CALENDAR_NO VARCHAR(200) NULL
END
GO

 IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE'
			AND COLUMN_NAME = 'DEDUCTION_CALENDAR_NAME'
			AND TABLE_SCHEMA = 'DBO')
BEGIN
	ALTER TABLE SNAP_REBATE_SCHEDULE 
		ADD DEDUCTION_CALENDAR_NAME VARCHAR(200) NULL
END
GO
---------------------SNAP_REBATE_SCHEDULE_2017_SEMI_ANNUAL_2-----------------------
IF EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.TABLES
		WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE_2017_SEMI_ANNUAL_2'
		)
BEGIN
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_REBATE_SCHEDULE_2017_SEMI_ANNUAL_2'
      AND COLUMN_NAME  = 'DEDUCTION_INCLUSION'
      AND COLUMN_NAME  = 'REBATE_SCHEDULE_ALIAS_ID' 
      AND COLUMN_NAME  = 'PAYMENT_LEVEL' 
      AND COLUMN_NAME  = 'EVALUATION_RULE_LEVEL' 
      AND COLUMN_NAME  = 'EVALUATION_RULE' 
      AND COLUMN_NAME  = 'EVALUATION_RULE_BUNDLE' 
      AND COLUMN_NAME  = 'CALCULATION_TYPE' 
      AND COLUMN_NAME  = 'CALCULATION_RULE' 
      AND COLUMN_NAME  = 'CALCULATION_RULE_LEVEL' 
      AND COLUMN_NAME  = 'CALCULATION_RULE_BUNDLE' 
      AND COLUMN_NAME  = 'NET_SALES_FORMULA' 
      AND COLUMN_NAME  = 'NET_SALES_RULE' 
      AND COLUMN_NAME  = 'DEDUCTION_CALENDAR_NO' 
      AND COLUMN_NAME  = 'DEDUCTION_CALENDAR_NAME' 
      AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      DROP TABLE SNAP_REBATE_SCHEDULE_2017_SEMI_ANNUAL_2
  END
  END
  GO
   
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_REBATE_SCHEDULE'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_SALES_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[SNAP_SALES_MASTER]
        (
           [SALES_INTFID]              [NUMERIC](38, 0) NOT NULL,
           [SALES_ID]                  [VARCHAR](200) NULL,
           [ORGANIZATION_KEY]          [VARCHAR](200) NULL,
           [ITEM_ID]                   [VARCHAR](200) NULL,
           [ITEM_NO]                   [VARCHAR](200) NULL,
           [ACCOUNT_CODE_QUALIFIER]    [VARCHAR](200) NULL,
           [PARENT_ITEM_ID]            [VARCHAR](200) NULL,
           [PARENT_ITEM_NO]            [VARCHAR](200) NULL,
           [ITEM_UOM]                  [VARCHAR](200) NULL,
           [DOC_TYPE]                  [VARCHAR](200) NULL,
           [ORDER_NUMBER]              [VARCHAR](200) NULL,
           [INVOICE_NUMBER]            [VARCHAR](200) NULL,
           [INVOICE_LINE_NUMBER]       [VARCHAR](200) NULL,
           [INVOICE_DATE]              [VARCHAR](200)  NULL,
           [ORDER_TYPE]                [VARCHAR](200) NULL,
           [ORDER_SUBTYPE]             [VARCHAR](200) NULL,
           [ANALYSIS_CODE]             [VARCHAR](200) NULL,
           [PRICE_ADJUSTMENT_NAME]     [VARCHAR](200) NULL,
           [RECORD_SEQUENCE]           [VARCHAR](200) NULL,
           [PRICE]                     [VARCHAR](200) NULL,
           [QUANTITY]                  [VARCHAR](200) NULL,
           [LOT_NO]                    [VARCHAR](200) NULL,
           [AMOUNT]                    [VARCHAR](200) NULL,
           [CONTRACT_ID]               [VARCHAR](200) NULL,
           [CONTRACT_NO]               [VARCHAR](200) NULL,
           [ACCOUNT_TYPE]              [VARCHAR](200) NULL,
           [CUSTOMER_SUBTYPE]          [VARCHAR](200) NULL,
           [WHOLESALE_OWNER_ID]        [VARCHAR](200) NULL,
           [ACCOUNT_NO]                [VARCHAR](200) NULL,
           [ACCOUNT_NAME]              [VARCHAR](200) NULL,
           [IDENTIFIER_CODE_QUALIFIER] [VARCHAR](200) NULL,
           [CUSTOMER_COMPANY_CODE]     [VARCHAR](200) NULL,
           [IS_ACTIVE]                 [VARCHAR](200) NULL,
           [COMPANY_ID]                [VARCHAR](200) NULL,
           [DIVISION_ID]               [VARCHAR](200) NULL,
           [MARKET_ID]                 [VARCHAR](200) NULL,
           [BRAND_ID]                  [VARCHAR](200) NULL,
           [UPLOAD_DATE]               [VARCHAR](200)  NULL,
           [ORDER_RECEIVED_DATE]       [VARCHAR](200)  NULL,
           [PROVISION_ID]              [VARCHAR](200) NULL,
           [ACCOUNT_ID]                [VARCHAR](200) NULL,
           [CREATED_BY]                [VARCHAR](200) NULL,
           [CREATED_DATE]              [DATETIME] NULL,
           [MODIFIED_BY]               [VARCHAR](200) NULL,
           [MODIFIED_DATE]             [DATETIME] NULL,
           [BATCH_ID]                  [VARCHAR](200) NULL,
           [SOURCE]                    [VARCHAR](200) NULL,
           [ADD_CHG_DEL_INDICATOR]     [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]        [DATETIME] NULL
        )
  END

GO


IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'SNAP_SALES_MASTER'
                  AND AUTO_CREATED = 0
                  AND NAME = 'PROVISION_ID')
  BEGIN
      DROP STATISTICS SNAP_SALES_MASTER.PROVISION_ID
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'SNAP_SALES_MASTER'
                  AND COLUMN_NAME = 'PROVISION_ID'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE SNAP_SALES_MASTER
        ALTER COLUMN PROVISION_ID VARCHAR(200)
  END

GO 


IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_SALES_MASTER')
                      AND NAME = 'DF_SNAP_SALES_MASTER_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_SALES_MASTER]
        ADD CONSTRAINT [DF_SNAP_SALES_MASTER_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_SALES_MASTER'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO


--------------------------------------------------SNAP_ACCRUAL_INBOUND----------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_ACCRUAL_INBOUND'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [dbo].[SNAP_ACCRUAL_INBOUND]
        (
           [ACCRUAL_INTFID]            [NUMERIC](38, 0) NOT NULL,
           [ACCRUAL_ID]                [VARCHAR](200)  NULL,
           [SALES_MASTER_ID]           [VARCHAR](200) NULL,
           [GL_ACCOUNT]                [VARCHAR](200)  NULL,
           [COMPANY_ID]                [VARCHAR](200)  NULL,
           [COMPANY_NO]                [VARCHAR](200)  NULL,
           [COMP_IDENT_CODE_QUALIFIER] [VARCHAR](200) NULL,
           [COMPANY_COST_CENTER]       [VARCHAR](200)  NULL,
           [ACCOUNT_ID]                [VARCHAR](200)  NULL,
           [ACCOUNT_NO]                [VARCHAR](200) NULL,
           [ACCOUNT_NAME]              [VARCHAR](200) NULL,
           [ACCT_IDENT_CODE_QUALIFIER] [VARCHAR](200) NULL,
           [ITEM_ID]                   [VARCHAR](200)  NULL,
           [ITEM_NO]                   [VARCHAR](200) NULL,
           [ITEM_IDENT_CODE_QUALIFIER] [VARCHAR](200) NULL,
           [CONTRACT_ID]               [VARCHAR](200)  NULL,
           [BRAND_ID]                  [VARCHAR](200) NULL,
           [CATEGORY_ID]               [VARCHAR](200) NULL,
           [PROVISION_ID]              [VARCHAR](200)  NULL,
           [ACCRUAL_TYPE]              [VARCHAR](200) NULL,
           [ACCRUAL_PERIOD_START_DATE] [VARCHAR](200)  NULL,
           [ACCRUAL_PERIOD_END_DATE]   [VARCHAR](200)  NULL,
           [AMOUNT]                    [VARCHAR](200)  NULL,
           [SUB_LEDGER]                [VARCHAR](200)  NULL,
           [SUB_LEDGER_TYPE]           [VARCHAR](200)  NULL,
           [DOCUMENT_TYPE]             [VARCHAR](200)  NULL,
           [POSTING_DATE]              [VARCHAR](200) NULL,
           [GL_DATE]                   [VARCHAR](200) NULL,
           [RECORD_CREATED_DATE]       [VARCHAR](200) NULL,
           [BATCH_ID]                  [VARCHAR](200)  NULL,
           [SOURCE]                    [VARCHAR](200) NULL,
           [CREATED_BY]                [VARCHAR](200)  NULL,
           [CREATED_DATE]              [DATETIME]  NULL,
           [MODIFIED_BY]               [VARCHAR](200)  NULL,
           [MODIFIED_DATE]             [DATETIME]  NULL,
           [INTF_INSERTED_DATE]        [DATETIME]
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.SNAP_ACCRUAL_INBOUND')
                      AND NAME = 'DF_SNAP_ACCRUAL_INBOUND_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [DBO].[SNAP_ACCRUAL_INBOUND]
        ADD CONSTRAINT [DF_SNAP_ACCRUAL_INBOUND_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

IF  EXISTS (SELECT 1
FROM SYS.STATS
WHERE OBJECT_NAME(OBJECT_ID) = 'SNAP_ACCRUAL_INBOUND'
	AND AUTO_CREATED = 0
   AND NAME='CREATED_DATE')
  BEGIN
      DROP STATISTICS SNAP_ACCRUAL_INBOUND.CREATED_DATE
  END
GO

IF  EXISTS (SELECT 1
FROM SYS.STATS
WHERE OBJECT_NAME(OBJECT_ID) = 'SNAP_ACCRUAL_INBOUND'
	AND AUTO_CREATED = 0
   AND NAME='MODIFIED_DATE')
  BEGIN
      DROP STATISTICS SNAP_ACCRUAL_INBOUND.MODIFIED_DATE
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'SNAP_ACCRUAL_INBOUND'
                      AND COLUMN_NAME = 'CREATED_DATE'
                      AND TABLE_SCHEMA = 'DBO' )
  BEGIN
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ALTER COLUMN  CREATED_DATE VARCHAR(200) 
  END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'SNAP_ACCRUAL_INBOUND'
                      AND COLUMN_NAME = 'MODIFIED_DATE'
                      AND TABLE_SCHEMA = 'DBO' )
  BEGIN
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ALTER COLUMN  MODIFIED_DATE VARCHAR(200) 
  END
GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_ACCRUAL_INBOUND'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO
---------------ADDING COLUMNS -------------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'COMPANY_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD COMPANY_NAME VARCHAR(100)
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'BUSINESS_UNIT_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD BUSINESS_UNIT_ID VARCHAR(50) NOT NULL
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'BUSINESS_UNIT_NO' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD BUSINESS_UNIT_NO VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'BU_IDENT_CODE_QUALIFIER' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD BU_IDENT_CODE_QUALIFIER VARCHAR(50) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'BUSINESS_UNIT_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD BUSINESS_UNIT_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ITEM_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD ITEM_NAME VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'BRAND_NAME' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD BRAND_NAME VARCHAR(30) 
  END
GO
IF EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN
     exec sp_RENAME 'SNAP_ACCRUAL_INBOUND.AMOUNT', 'DEDUCTION_AMOUNT' , 'COLUMN';
  END
GO

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_CATEGORY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD ACCRUAL_CATEGORY VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_SCHEDULE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD ACCRUAL_SCHEDULE_TYPE VARCHAR(25) 
  END
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_PROGRAM_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND 
        ADD ACCRUAL_PROGRAM_TYPE VARCHAR(25) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'SALES_AMOUNT' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD SALES_AMOUNT NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'QUANTITY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD QUANTITY NUMERIC(22,6) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC1' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC1 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC2' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC2 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC3' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC3 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC4' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC4 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC5' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC5 VARCHAR(100) 
  END
GO
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'SNAP_ACCRUAL_INBOUND' AND COLUMN_NAME  = 'ACCRUAL_UDC6' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ADD ACCRUAL_UDC6 VARCHAR(100) 
  END
GO
   -----------------ALTER COLUMNS FROM NOT NULL TO NULL COLUMN------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'SNAP_ACCRUAL_INBOUND'
                  AND NAME = 'ACCRUAL_INTFID')
  BEGIN
      DROP STATISTICS SNAP_ACCRUAL_INBOUND.ACCRUAL_INTFID
  END
  GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'SNAP_ACCRUAL_INBOUND'
                  AND COLUMN_NAME = 'ACCRUAL_INTFID'
                  AND IS_NULLABLE = 'No')
  BEGIN
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ALTER COLUMN ACCRUAL_INTFID NUMERIC NULL
  END
GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'SNAP_ACCRUAL_INBOUND'
                  AND NAME = 'BUSINESS_UNIT_ID')
  BEGIN
      DROP STATISTICS SNAP_ACCRUAL_INBOUND.BUSINESS_UNIT_ID
  END
  GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'SNAP_ACCRUAL_INBOUND'
                  AND COLUMN_NAME = 'BUSINESS_UNIT_ID'
                  AND IS_NULLABLE = 'No')
  BEGIN
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ALTER COLUMN BUSINESS_UNIT_ID VARCHAR(50) NULL
  END

GO
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'SNAP_ACCRUAL_INBOUND'
                  AND NAME = 'SALES_AMOUNT')
  BEGIN
      DROP STATISTICS SNAP_ACCRUAL_INBOUND.SALES_AMOUNT
  END
  GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'SNAP_ACCRUAL_INBOUND'
                  AND COLUMN_NAME = 'SALES_AMOUNT'
                  )
  BEGIN
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ALTER COLUMN SALES_AMOUNT VARCHAR(50) NULL
  END

GO
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'SNAP_ACCRUAL_INBOUND'
                  AND NAME = 'QUANTITY')
  BEGIN
      DROP STATISTICS SNAP_ACCRUAL_INBOUND.QUANTITY
  END
  GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'SNAP_ACCRUAL_INBOUND'
                  AND COLUMN_NAME = 'QUANTITY'
                  )
  BEGIN
      ALTER TABLE SNAP_ACCRUAL_INBOUND
        ALTER COLUMN QUANTITY VARCHAR(50) NULL
  END

GO
-------------------------------------------------SNAP_ACCRUAL_OUTBOUND-------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_ACCRUAL_OUTBOUND'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [dbo].[SNAP_ACCRUAL_OUTBOUND]
        (
           [ACCRUAL_OUTBOUND_SID]              [NUMERIC](38, 0) NOT NULL,
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
           [MODIFIED_DATE]                     [DATETIME] NULL,
           [INTF_INSERTED_DATE]                [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.SNAP_ACCRUAL_OUTBOUND')
                      AND NAME = 'DF_SNAP_ACCRUAL_OUTBOUND_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [DBO].[SNAP_ACCRUAL_OUTBOUND]
        ADD CONSTRAINT [DF_SNAP_ACCRUAL_OUTBOUND_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_ACCRUAL_OUTBOUND'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO
-------------------------------------------------SNAP_GL_POSTING-------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_GL_POSTING'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [dbo].[SNAP_GL_POSTING]
        (
           [ACCRUAL_INTFID]     [NUMERIC](38, 0) NOT NULL,
           [ACCRUAL_ID]         [VARCHAR](200) NULL,
           [STATUS]             [VARCHAR](200)  NULL,
           [BATCH_ID]           [VARCHAR](200)  NULL,
           [SOURCE]             [VARCHAR](200) NULL,
           [CREATED_BY]         [VARCHAR](200) NULL,
           [CREATED_DATE]       [DATETIME]  NULL,
           [MODIFIED_BY]        [VARCHAR](200) NULL,
           [MODIFIED_DATE]      [DATETIME] NULL,
           [INTF_INSERTED_DATE] [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.SNAP_GL_POSTING')
                      AND NAME = 'DF_SNAP_GL_POSTING_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [DBO].[SNAP_GL_POSTING]
        ADD CONSTRAINT [DF_SNAP_GL_POSTING_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_GL_POSTING'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO


----------------------------------------SNAP_DEMAND_ACTUAL------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_DEMAND_ACTUAL'
                      AND TABLE_SCHEMA = 'dbo')

   BEGIN
      CREATE  TABLE   SNAP_DEMAND_ACTUAL
        (
           DEMAND_ACTUAL_INTERFACE_ID     [NUMERIC](38, 0) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](200) NULL,
           FORECAST_YEAR                  [VARCHAR](200) NULL,
           FORECAST_MONTH                 [VARCHAR](200) NULL,
           ITEM_ID                        [VARCHAR](200) NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](200) NULL,
           ITEM_IDENTIFIER                [VARCHAR](200) NULL,
           BRAND_ID                       [VARCHAR](200) NULL,
           SEGMENT                        [VARCHAR](200) NULL,
           MARKET_SIZE_UNITS             [VARCHAR](200) NULL,
           MARKET_SHARE_RATIO             [VARCHAR](200) NULL,
           MARKET_SHARE_UNITS             [VARCHAR](200) NULL,
           TOTAL_DEMAND_UNITS             [VARCHAR](200) NULL,
           TOTAL_DEMAND_AMOUNT            [VARCHAR](200) NULL,
           GROSS_UNITS                    [VARCHAR](200) NULL,
           GROSS_PRICE                    [VARCHAR](200) NULL,
           GROSS_AMOUNT                   [VARCHAR](200) NULL,
           NET_SALES_PRICE                [VARCHAR](200) NULL,
           NET_SALES_AMOUNT               [VARCHAR](200) NULL,
           CREATED_BY                     [VARCHAR](200) NULL,
           CREATED_DATE                   [DATETIME] NULL,
           MODIFIED_BY                    [VARCHAR](200) NULL,
           MODIFIED_DATE                  [DATETIME] NULL,
           ADD_CHG_DEL_INDICATOR          [VARCHAR](200) NULL,
           BATCH_ID                       [VARCHAR](200) NULL,
           SOURCE                         [VARCHAR](200) NULL,
           COUNTRY                        [VARCHAR](200) NULL,
           ORGANIZATION_KEY               [VARCHAR](200) NULL,
		   INTF_INSERTED_DATE             [DATETIME] NULL
		   )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_DEMAND_ACTUAL')
                      AND NAME = 'DF_SNAP_DEMAND_ACTUAL_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_DEMAND_ACTUAL]
        ADD CONSTRAINT [DF_SNAP_DEMAND_ACTUAL_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_DEMAND_ACTUAL'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO

----------------------------------------------SNAP_DEMAND_FORECAST-----------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_DEMAND_FORECAST'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE SNAP_DEMAND_FORECAST
        (
           DEMAND_FORECAST_INTERFACE_ID   [NUMERIC](38, 0) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](200) NULL,
           FORECAST_YEAR                  [VARCHAR](200) NULL,
           FORECAST_MONTH                 [VARCHAR](200) NULL,
           ITEM_ID                        [VARCHAR](200) NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](200) NULL,
           ITEM_IDENTIFIER                [VARCHAR](200) NULL,
           BRAND_ID                       [VARCHAR](200) NULL,
           SEGMENT                        [VARCHAR](200) NULL,
           MARKET_SIZE_UNITS              [VARCHAR](200) NULL,
           MARKET_SHARE_RATIO             [VARCHAR](200) NULL,
           MARKET_SHARE_UNITS             [VARCHAR](200) NULL,
           UNCAPTURED_UNITS               [VARCHAR](200) NULL,
           UNCAPTURED_UNITS_RATIO         [VARCHAR](200) NULL,
           TOTAL_DEMAND_UNITS             [VARCHAR](200) NULL,
           TOTAL_DEMAND_AMOUNT            [VARCHAR](200) NULL,
           INVENTORY_UNIT_CHANGE          [VARCHAR](200) NULL,
           GROSS_UNITS                    [VARCHAR](200) NULL,
		   GROSS_PRICE                     [VARCHAR](200) NULL,
           GROSS_AMOUNT                   [VARCHAR](200) NULL,
           NET_SALES_PRICE                [VARCHAR](200) NULL,
           NET_SALES_AMOUNT               [VARCHAR](200) NULL,
           CREATED_BY                     [VARCHAR](200) NULL,
           CREATED_DATE                   [DATETIME] NULL,
           MODIFIED_BY                    [VARCHAR](200) NULL,
           MODIFIED_DATE                  [DATETIME] NULL,
           ADD_CHG_DEL_INDICATOR          [VARCHAR](200) NULL,
           BATCH_ID                       [VARCHAR](200) NULL,
           SOURCE                         [VARCHAR](200) NULL,
           FORECAST_NAME                  [VARCHAR](200) NULL,
           FORECAST_VER                   [VARCHAR](200) NULL,
           COUNTRY                        [VARCHAR](200) NULL,
           ORGANIZATION_KEY               [VARCHAR](200) NULL,
           INTF_INSERTED_DATE             [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_DEMAND_FORECAST')
                      AND NAME = 'DF_SNAP_DEMAND_FORECAST_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].SNAP_DEMAND_FORECAST
        ADD CONSTRAINT DF_SNAP_DEMAND_FORECAST_INTF_INSERTED_DATE DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_DEMAND_FORECAST'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 
-----------------------------------SNAP_INVENTORY_WD_ACTUAL_DT---------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_INVENTORY_WD_ACTUAL_DT'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE SNAP_INVENTORY_WD_ACTUAL_DT
        (
           INVENTORY_WD_ACTUAL_DT_INTF_ID [NUMERIC](38, 0) NOT NULL,
           YEAR                               VARCHAR(200) NULL,
           MONTH                              VARCHAR(200) NULL,
           DAY                                VARCHAR(200) NULL,
           WEEK                               VARCHAR(200) NULL,
           COMPANY_ID                         VARCHAR(200) NULL,
           IDENTIFIER_CODE_QUALIFIER          VARCHAR(200) NULL,
           COMPANY_IDENTIFIER                 VARCHAR(200) NULL,
           ITEM_ID                            VARCHAR(200) NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER     VARCHAR(200) NULL,
           ITEM_IDENTIFIER                    VARCHAR(200) NULL,
           UNITS_WITHDRAWN                    VARCHAR(200) NULL,
           AMOUNT_WITHDRAWN                   VARCHAR(200) NULL,
           UNITS_ON_HAND                      VARCHAR(200) NULL,
           AMOUNT_ON_HAND                     VARCHAR(200) NULL,
           QUANTITY_ON_ORDER                  VARCHAR(200) NULL,
           AMOUNT_ON_ORDER                    VARCHAR(200) NULL,
           QUANTITY_RECEIVED                  VARCHAR(200) NULL,
           AMOUNT_RECEIVED                    VARCHAR(200) NULL,
           CREATED_BY                         VARCHAR(200) NULL,
           CREATED_DATE                       DATETIME NULL,
           MODIFIED_BY                        VARCHAR(200) NULL,
           MODIFIED_DATE                      DATETIME NULL,
           ADD_CHG_DEL_INDICATOR              VARCHAR(200) NULL,
           BATCH_ID                           VARCHAR(200) NULL,
           SOURCE                             VARCHAR(200) NULL,
           COUNTRY                            VARCHAR(200) NULL,
           ORGANIZATION_KEY                   VARCHAR(200) NULL,
           INTF_INSERTED_DATE                 DATETIME NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_INVENTORY_WD_ACTUAL_DT')
                      AND NAME = 'DF_SNAP_INVENTORY_WD_ACTUAL_DT_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_INVENTORY_WD_ACTUAL_DT]
        ADD CONSTRAINT [DF_SNAP_INVENTORY_WD_ACTUAL_DT_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_INVENTORY_WD_ACTUAL_DT'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 

---------------------------SNAP_INVENTORY_WD_ACTUAL_MAS---------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_INVENTORY_WD_ACTUAL_MAS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[SNAP_INVENTORY_WD_ACTUAL_MAS]
        (
           INVENTORY_WD_ACTUAL_MAS_INTF_ID NUMERIC(38, 0) NOT NULL,
           YEAR                               VARCHAR(200) NULL,
           MONTH                              VARCHAR(200) NULL,
           DAY                                VARCHAR(200) NULL,
           WEEK                               VARCHAR(200) NULL,
           ITEM_ID                            VARCHAR(200) NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER     VARCHAR(200) NULL,
           ITEM_IDENTIFIER                    VARCHAR(200) NULL,
           UNITS_WITHDRAWN                    VARCHAR(200)  NULL,
           AMOUNT_WITHDRAWN                   VARCHAR(200) NULL,
           UNITS_ON_HAND                      VARCHAR(200)  NULL,
           AMOUNT_ON_HAND                     VARCHAR(200) NULL,
           QUANTITY_ON_ORDER                  VARCHAR(200) NULL,
           AMOUNT_ON_ORDER                    VARCHAR(200) NULL,
           QUANTITY_RECEIVED                  VARCHAR(200) NULL,
           AMOUNT_RECEIVED                    VARCHAR(200) NULL,
           CREATED_BY                         VARCHAR(200) NULL,
           CREATED_DATE                       DATETIME NULL,
           MODIFIED_BY                        VARCHAR(200) NULL,
           MODIFIED_DATE                      DATETIME NULL,
           ADD_CHG_DEL_INDICATOR              VARCHAR(200) NULL,
           BATCH_ID                           VARCHAR(200) NULL,
           SOURCE                             VARCHAR(200) NULL,
           COUNTRY                            VARCHAR(200) NULL,
           ORGANIZATION_KEY                   VARCHAR(200) NULL,
           INTF_INSERTED_DATE                 DATETIME NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_INVENTORY_WD_ACTUAL_MAS')
                      AND NAME = 'DF_SNAP_INVENTORY_WD_ACTUAL_MAS_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_INVENTORY_WD_ACTUAL_MAS]
        ADD CONSTRAINT [DF_SNAP_INVENTORY_WD_ACTUAL_MAS_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO
--
IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'SNAP_INVENTORY_WD_ACTUAL_MAS'
                      AND COLUMN_NAME = 'INVENTORY_WD_ACTUAL_MAS_INTF_ID'
					  AND TABLE_SCHEMA='DBO')
begin
exec sp_RENAME 'SNAP_INVENTORY_WD_ACTUAL_MAS.INVENTORY_WD_ACTUAL_MAS_INTF_ID' , 'INVENTORY_WD_ACT_MAS_INTF_ID', 'COLUMN'
end
go
--

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'SNAP_INVENTORY_WD_ACTUAL_MAS'
                      AND COLUMN_NAME = 'INVENTORY_WD_ACTUAL_MAS_INTF_ID'
					  AND TABLE_SCHEMA='DBO')
begin
exec sp_RENAME 'SNAP_INVENTORY_WD_ACTUAL_MAS.INVENTORY_WD_ACTUAL_MAS_INTF_ID' , 'INVENTORY_WD_ACT_MAS_INTF_ID', 'COLUMN'
end
go

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_INVENTORY_WD_ACTUAL_MAS'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 



---------------------------SNAP_INVENTORY_WD_PROJ_DT---------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_INVENTORY_WD_PROJ_DT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[SNAP_INVENTORY_WD_PROJ_DT]
        (
           INVENTORY_WD_PROJ_DT_INTF_ID NUMERIC(38, 0) NOT NULL,
           YEAR                             VARCHAR(200) NULL,
           MONTH                            VARCHAR(200) NULL,
           DAY                              VARCHAR(200) NULL,
           WEEK                             VARCHAR(200) NULL,
           COMPANY_ID                       VARCHAR(200) NULL,
           IDENTIFIER_CODE_QUALIFIER        VARCHAR(200) NULL,
           COMPANY_IDENTIFIER               VARCHAR(200) NULL,
           ITEM_ID                          VARCHAR(200) NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER   VARCHAR(200) NULL,
           ITEM_IDENTIFIER                  VARCHAR(200) NULL,
           UNITS_WITHDRAWN                  VARCHAR(200) NULL,
           AMOUNT_WITHDRAWN                 VARCHAR(200) NULL,
           PRICE                            VARCHAR(200) NULL,
           CREATED_BY                       VARCHAR(200) NULL,
           CREATED_DATE                     DATETIME NULL,
           MODIFIED_BY                      VARCHAR(200) NULL,
           MODIFIED_DATE                    DATETIME NULL,
           ADD_CHG_DEL_INDICATOR            VARCHAR(200) NULL,
           BATCH_ID                         VARCHAR(200) NULL,
           SOURCE                           VARCHAR(200) NULL,
           FORECAST_NAME                    VARCHAR(200) NULL,
           FORECAST_VER                     VARCHAR(200) NULL,
           COUNTRY                          VARCHAR(200) NULL,
           ORGANIZATION_KEY                 VARCHAR(200) NULL,
           INTF_INSERTED_DATE               DATETIME NULL
        )
  END
  GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_INVENTORY_WD_PROJ_DT')
                      AND NAME = 'DF_SNAP_INVENTORY_WD_PROJ_DT_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_INVENTORY_WD_PROJ_DT]
        ADD CONSTRAINT [DF_SNAP_INVENTORY_WD_PROJ_DT_INTF_INSERTED_DATE] DEFAULT(Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_INVENTORY_WD_PROJ_DT' --TABLE NAME
SET @SCHEMANAME1 = 'dbo' -- SCHEMA NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 

---------------------------SNAP_INVENTORY_WD_PROJ_MAS---------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_INVENTORY_WD_PROJ_MAS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[SNAP_INVENTORY_WD_PROJ_MAS]
        (
           INVENTORY_WD_PROJ_MAS_INTF_ID NUMERIC(38, 0) NOT NULL,
           YEAR                               VARCHAR(200) NULL,
           MONTH                              VARCHAR(200) NULL,
           DAY                                VARCHAR(200) NULL,
           WEEK                               VARCHAR(200) NULL,
           ITEM_ID                            VARCHAR(200) NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER     VARCHAR(200) NULL,
           ITEM_IDENTIFIER                    VARCHAR(200) NULL,
           UNITS_WITHDRAWN                    VARCHAR(200) NULL,
           AMOUNT_WITHDRAWN                   VARCHAR(200) NULL,
		   PRICE							  NUMERIC(22,6) NULL,
           CREATED_BY                         VARCHAR(200) NULL,
           CREATED_DATE                       DATETIME NULL,
           MODIFIED_BY                        VARCHAR(200) NULL,
           MODIFIED_DATE                      DATETIME NULL,
           ADD_CHG_DEL_INDICATOR              VARCHAR(200) NULL,
           BATCH_ID                           VARCHAR(200) NULL,
           SOURCE                             VARCHAR(200) NULL,
           FORECAST_NAME                      VARCHAR(200) NULL,
           FORECAST_VER                       VARCHAR(200) NULL,
           COUNTRY                            VARCHAR(200) NULL,
           ORGANIZATION_KEY                   VARCHAR(200) NULL,
           INTF_INSERTED_DATE                 DATETIME NULL
        )
  END
  GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_INVENTORY_WD_PROJ_MAS')
                      AND NAME = 'DF_SNAP_INVENTORY_WD_PROJ_MAS_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_INVENTORY_WD_PROJ_MAS]
        ADD CONSTRAINT [DF_SNAP_INVENTORY_WD_PROJ_MAS_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_INVENTORY_WD_PROJ_MAS'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 
--------RETURNS TABLE SCRIPT
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_RETURNS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE SNAP_RETURNS
        (
           RRESERVE_INTERFACE_ID       [NUMERIC](38, 0) NOT NULL,
           RRESERVE_ID                 [VARCHAR](200) NULL,
           VERSION                     [VARCHAR](200) NULL,
           SKU                         [VARCHAR](200) NULL,
           BRAND                       [VARCHAR](200) NULL,
           DESCRIPTION                 [VARCHAR](200) NULL,
           ORIG_SALE_MONTH             [VARCHAR](200) NULL,
           ORIG_SALE_UNITS             [VARCHAR](200) NULL,
           ORIG_SALE_DOLLARS           [VARCHAR](200) NULL,
           ASP                         [VARCHAR](200) NULL,
           CUM_RETURN_UNITS            [VARCHAR](200) NULL,
           ACTUAL_RETURN_RATE          [VARCHAR](200) NULL,
           WEIGHTED_AVG_MONTHS         [VARCHAR](200) NULL,
           FIRST_RETURN                [VARCHAR](200) NULL,
           LAST_RETURN                 [VARCHAR](200) NULL,
           MAX_EXPIRED_MONTH           [VARCHAR](200) NULL,
           MAX_EXPIRED_MONS_PLUSCUTOFF [VARCHAR](200) NULL,
           RETURN_COMPLETE             [VARCHAR](200) NULL,
           CALC_USED                   [VARCHAR](200) NULL,
           ORIG_SALE_MONTH_CUT_OFF     [VARCHAR](200) NULL,
           EXPECTED_RETURN_RATE        [VARCHAR](200) NULL,
           ESTIMATED_RETURN_UNITS      [VARCHAR](200) NULL,
           ADJ_ESTIMATED_RETURN_UNITS  [VARCHAR](200) NULL,
           ADJ_VALUE_AT_ORIG_ASP       [VARCHAR](200) NULL,
           POS_ESTIMATED_RETURN_UNITS  [VARCHAR](200) NULL,
           VALUE_AT_ORIG_ASP           [VARCHAR](200) NULL,
           LOAD_DATE                   [VARCHAR](200) NULL,
           PAST_EXPIRATION             [VARCHAR](200) NULL,
           WITHIN_50QRTILE             [VARCHAR](200) NULL,
           MAXIMUM                     [VARCHAR](200) NULL,
           PCT_75TH                    [VARCHAR](200) NULL,
           PCT_50TH                    [VARCHAR](200) NULL,
           PCT_25TH                    [VARCHAR](200) NULL,
           UPPER_LIMIT                 [VARCHAR](200) NULL,
           LOWER_LIMIT                 [VARCHAR](200) NULL,
           ADD_CHG_DEL_INDICATOR       [VARCHAR](200) NULL,
           BATCH_ID                    [VARCHAR](200) NULL,
           SOURCE                      [VARCHAR](200) NULL,
           CREATED_BY                  [VARCHAR](200) NULL,
           CREATED_DATE                [DATETIME] NULL,
           MODIFIED_BY                 [VARCHAR](200) NULL,
           MODIFIED_DATE               [DATETIME] NULL,
           INTF_INSERTED_DATE          [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_RETURNS')
                      AND NAME = 'DF_SNAP_RETURNS_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].SNAP_RETURNS
        ADD CONSTRAINT DF_SNAP_RETURNS_INTF_INSERTED_DATE DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_RETURNS'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 

-------------------------SNAP_CUSTOMER_GTS_ACTUAL-----------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_CUSTOMER_GTS_ACTUAL'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE SNAP_CUSTOMER_GTS_ACTUAL
        (
           CUSTOMER_GTS_ACTUAL_INTF_ID NUMERIC(38, 0) NOT NULL,
           SALES_ID                    VARCHAR(200) NULL,
           ORGANIZATION_KEY            VARCHAR(200) NULL,
           ITEM_ID                     VARCHAR(200) NULL,
           ITEM_UOM                    VARCHAR(200) NULL,
           ORDER_NUMBER                VARCHAR(200) NULL,
           INVOICE_NUMBER              VARCHAR(200) NULL,
           INVOICE_LINE_NUMBER         VARCHAR(200) NULL,
           INVOICE_DATE                DATETIME NOT NULL,
           QUANTITY                    VARCHAR(200) NULL,
           LOT_NO                      VARCHAR(200) NULL,
           AMOUNT                      VARCHAR(200) NULL,
           CONTRACT_ID                 VARCHAR(200) NULL,
           ACCOUNT_ID                  VARCHAR(200) NULL,
           ORDER_RECEIVED_DATE         DATETIME NULL,
           BATCH_ID                    VARCHAR(200) NULL,
           SOURCE                      VARCHAR(200) NULL,
           CREATED_BY                  VARCHAR(200) NULL,
           CREATED_DATE                VARCHAR(200) NULL,
           MODIFIED_BY                 VARCHAR(200) NULL,
           MODIFIED_DATE               DATETIME NOT NULL,
           ADD_CHG_DEL_INDICATOR       VARCHAR(200) NULL,
           PARENT_ACCOUNT_ID           VARCHAR(200) NULL,
           INTF_INSERTED_DATE          DATETIME NULL
        )
  END

GO
---------------------------------------SNAP_CUSTOMER_GTS_ACTUAL NOT NULL TO NULL-----------------------------------------------------------
--INVOICE_DATE
IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'SNAP_CUSTOMER_GTS_ACTUAL'
                     AND COLUMN_NAME = 'INVOICE_DATE'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='DATETIME'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].SNAP_CUSTOMER_GTS_ACTUAL
        ALTER COLUMN INVOICE_DATE DATETIME NULL
  END

GO
--MODIFIED_DATE
IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'SNAP_CUSTOMER_GTS_ACTUAL'
                     AND COLUMN_NAME = 'MODIFIED_DATE'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='DATETIME'
					 AND IS_NULLABLE='NO')
  BEGIN
      ALTER TABLE [DBO].SNAP_CUSTOMER_GTS_ACTUAL
        ALTER COLUMN MODIFIED_DATE DATETIME NULL
  END

GO

-----------------DEFAULT CONSTARINT
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_CUSTOMER_GTS_ACTUAL')
                      AND NAME = 'DF_SNAP_CUSTOMER_GTS_ACTUAL_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].SNAP_CUSTOMER_GTS_ACTUAL
        ADD CONSTRAINT DF_SNAP_CUSTOMER_GTS_ACTUAL_INTF_INSERTED_DATE DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_CUSTOMER_GTS_ACTUAL'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 


-----------------------------SNAP_CUSTOMER_GTS_FORECAST----------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_CUSTOMER_GTS_FORECAST'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN 
      CREATE TABLE SNAP_CUSTOMER_GTS_FORECAST
        (
           CUSTOMER_GTS_FORECAST_INTF_ID NUMERIC(38, 0) NOT NULL,
           FORECAST_YEAR                 VARCHAR(200) NULL,
           FORECAST_MONTH                VARCHAR(200) NULL,
           ITEM_ID                       VARCHAR(200) NULL,
           COMPANY_ID                    VARCHAR(200) NULL,
           UNITS                         VARCHAR(200) NULL,
           PRICE_TYPE                    VARCHAR(200) NULL,
           PRICE                         VARCHAR(200) NULL,
           SALES_AMOUNT                  VARCHAR(200) NULL,
           SALES_INCLUSION               VARCHAR(200) NULL,
           DEDUCTION_ID                  VARCHAR(200) NULL,
           DEDUCTION_CATEGORY            VARCHAR(200) NULL,
           DEDUCTION_TYPE                VARCHAR(200) NULL,
           DEDUCTION_PROGRAM_TYPE        VARCHAR(200) NULL,
           ADJUSTMENT_CODE               VARCHAR(200) NULL,
           DEDUCTION_RATE                VARCHAR(200) NULL,
           DEDUCTION_AMOUNT              VARCHAR(200) NULL,
           DEDUCTION_INCLUSION           VARCHAR(200) NULL,
           FORECAST_VALUE_TYPE           VARCHAR(200) NULL,
           BRAND                         VARCHAR(200) NULL,
           SEGMENT                       VARCHAR(200) NULL,
           ADD_CHG_DEL_INDICATOR         VARCHAR(200) NULL,
           CREATED_BY                    VARCHAR(200) NULL,
           CREATED_DATE                  DATETIME NULL,
           MODIFIED_BY                   VARCHAR(200) NULL,
           MODIFIED_DATE                 DATETIME NULL,
           BATCH_ID                      VARCHAR(200) NULL,
           SOURCE                        VARCHAR(200) NULL,
           FORECAST_VER                  VARCHAR(200) NULL,
           COUNTRY                       VARCHAR(200) NULL,
           FORECAST_NAME                 VARCHAR(200) NULL,
           FORECAST_DATE                 DATETIME NULL,
           UDC1                          VARCHAR(200) NULL,
           UDC2                          VARCHAR(200) NULL,
           UDC3                          VARCHAR(200) NULL,
           UDC4                          VARCHAR(200) NULL,
           UDC5                          VARCHAR(200) NULL,
           UDC6                          VARCHAR(200) NULL,
           INTF_INSERTED_DATE            [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_CUSTOMER_GTS_FORECAST')
                      AND NAME = 'DF_SNAP_CUSTOMER_GTS_FORECAST_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_CUSTOMER_GTS_FORECAST]
        ADD CONSTRAINT [DF_SNAP_CUSTOMER_GTS_FORECAST_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_CUSTOMER_GTS_FORECAST'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 


------------------------------------SNAP_ADJUSTED_DEMAND_ACTUAL--------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_ADJUSTED_DEMAND_ACTUAL'
                      AND TABLE_SCHEMA = 'dbo')

   BEGIN
      CREATE  TABLE   SNAP_ADJUSTED_DEMAND_ACTUAL
        (
           ADJUSTED_DEMAND_ACTUAL_INTF_ID     [NUMERIC](38, 0) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](200) NULL,
           FORECAST_YEAR                  [VARCHAR](200) NULL,
           FORECAST_MONTH                 [VARCHAR](200) NULL,
           ITEM_ID                        [VARCHAR](200) NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](200) NULL,
           ITEM_IDENTIFIER                [VARCHAR](200) NULL,
           BRAND_ID                       [VARCHAR](200) NULL,
           SEGMENT                        [VARCHAR](200) NULL,
           MARKET_SIZE_UNITS             [VARCHAR](200) NULL,
           MARKET_SHARE_RATIO             [VARCHAR](200) NULL,
           MARKET_SHARE_UNITS             [VARCHAR](200) NULL,
           TOTAL_DEMAND_UNITS             [VARCHAR](200) NULL,
           TOTAL_DEMAND_AMOUNT            [VARCHAR](200) NULL,
           GROSS_UNITS                    [VARCHAR](200) NULL,
           GROSS_PRICE                    [VARCHAR](200) NULL,
           GROSS_AMOUNT                   [VARCHAR](200) NULL,
           NET_SALES_PRICE                [VARCHAR](200) NULL,
           NET_SALES_AMOUNT               [VARCHAR](200) NULL,
           CREATED_BY                     [VARCHAR](200) NULL,
           CREATED_DATE                   [DATETIME] NULL,
           MODIFIED_BY                    [VARCHAR](200) NULL,
           MODIFIED_DATE                  [DATETIME] NULL,
           ADD_CHG_DEL_INDICATOR          [VARCHAR](200) NULL,
           BATCH_ID                       [VARCHAR](200) NULL,
           SOURCE                         [VARCHAR](200) NULL,
           COUNTRY                        [VARCHAR](200) NULL,
           ORGANIZATION_KEY               [VARCHAR](200) NULL,
		   INTF_INSERTED_DATE             [DATETIME] NULL
		   )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_ADJUSTED_DEMAND_ACTUAL')
                      AND NAME = 'DF_SNAP_ADJUSTED_DEMAND_ACTUAL_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].[SNAP_ADJUSTED_DEMAND_ACTUAL]
        ADD CONSTRAINT [DF_SNAP_ADJUSTED_DEMAND_ACTUAL_INTF_INSERTED_DATE] DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_ADJUSTED_DEMAND_ACTUAL'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO


--------------------------------SNAP_ADJUSTED_DEMAND_FORECAST------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_ADJUSTED_DEMAND_FORECAST'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE SNAP_ADJUSTED_DEMAND_FORECAST
        (
           ADJUSTED_DEMAND_FORECAST_INTF_ID   [NUMERIC](38, 0) NOT NULL,
           FORECAST_TYPE                  [VARCHAR](200) NULL,
           YEAR                  [VARCHAR](200) NULL,
           MONTH                 [VARCHAR](200) NULL,
           ITEM_ID                        [VARCHAR](200) NULL,
           ITEM_IDENTIFIER_CODE_QUALIFIER [VARCHAR](200) NULL,
           ITEM_IDENTIFIER                [VARCHAR](200) NULL,
           BRAND_ID                       [VARCHAR](200) NULL,
           SEGMENT                        [VARCHAR](200) NULL,
           MARKET_SIZE_UNITS              [VARCHAR](200) NULL,
           MARKET_SHARE_RATIO             [VARCHAR](200) NULL,
           MARKET_SHARE_UNITS             [VARCHAR](200) NULL,
           UNCAPTURED_UNITS               [VARCHAR](200) NULL,
           UNCAPTURED_UNITS_RATIO         [VARCHAR](200) NULL,
           TOTAL_DEMAND_UNITS             [VARCHAR](200) NULL,
           TOTAL_DEMAND_AMOUNT            [VARCHAR](200) NULL,
           INVENTORY_UNIT_CHANGE          [VARCHAR](200) NULL,
           GROSS_UNITS                    [VARCHAR](200) NULL,
		   GROSS_PRICE                     [VARCHAR](200) NULL,
           GROSS_AMOUNT                   [VARCHAR](200) NULL,
           NET_SALES_PRICE                [VARCHAR](200) NULL,
           NET_SALES_AMOUNT               [VARCHAR](200) NULL,
           CREATED_BY                     [VARCHAR](200) NULL,
           CREATED_DATE                   [DATETIME] NULL,
           MODIFIED_BY                    [VARCHAR](200) NULL,
           MODIFIED_DATE                  [DATETIME] NULL,
           ADD_CHG_DEL_INDICATOR          [VARCHAR](200) NULL,
           BATCH_ID                       [VARCHAR](200) NULL,
           SOURCE                         [VARCHAR](200) NULL,
           FORECAST_NAME                  [VARCHAR](200) NULL,
           FORECAST_VER                   [VARCHAR](200) NULL,
           COUNTRY                        [VARCHAR](200) NULL,
           ORGANIZATION_KEY               [VARCHAR](200) NULL,
           INTF_INSERTED_DATE             [DATETIME] NULL
        )
  END
GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_ADJUSTED_DEMAND_FORECAST')
                      AND NAME = 'DF_SNAP_ADJUSTED_DEMAND_FORECAST_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].SNAP_ADJUSTED_DEMAND_FORECAST
        ADD CONSTRAINT DF_SNAP_ADJUSTED_DEMAND_FORECAST_INTF_INSERTED_DATE DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_ADJUSTED_DEMAND_FORECAST'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_RETURN_RESERVE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].SNAP_RETURN_RESERVE
        (
           RETURN_RESERVE_INTF_ID NUMERIC(38, 0) NOT NULL,
           [YEAR]                 [VARCHAR](200) NULL,
           [MONTH]                [VARCHAR](200) NULL,
           BRAND_ID               [VARCHAR](200) NULL,
           BRAND_NAME             [VARCHAR](200) NULL,
           ITEM_ID                [VARCHAR](200) NULL,
           ITEM_NO                [VARCHAR](200) NULL,
           ITEM_NAME              [VARCHAR](200) NULL,
           UNITS                  [VARCHAR](200) NULL,
           AMOUNT                 [VARCHAR](200) NULL,
           COUNTRY                [VARCHAR](200) NULL,
           BUSINESS_UNIT          [VARCHAR](200) NULL,
           GL_COMPANY             [VARCHAR](200) NULL,
           COMPANY_NO             [VARCHAR](200) NULL,
           DIVISION               [VARCHAR](200) NULL,
           COST_CENTER            [VARCHAR](200) NULL,
           ACCOUNT                [VARCHAR](200) NULL,
           PROJECT                [VARCHAR](200) NULL,
           FUTURE1                [VARCHAR](200) NULL,
           FUTURE2                [VARCHAR](200) NULL,
           UDC1                   [VARCHAR](200) NULL,
           UDC2                   [VARCHAR](200) NULL,
           UDC3                   [VARCHAR](200) NULL,
           UDC4                   [VARCHAR](200) NULL,
           UDC5                   [VARCHAR](200) NULL,
           UDC6                   [VARCHAR](200) NULL,
           ADD_CHG_DEL_INDICATOR  [VARCHAR](200) NULL,
           CREATED_BY             [VARCHAR](200) NULL,
           CREATED_DATE           DATETIME  NULL,
           MODIFIED_BY            [VARCHAR](200) NULL,
           MODIFIED_DATE          DATETIME  NULL,
           BATCH_ID               [VARCHAR](200) NULL,
           SOURCE                 [VARCHAR](200) NULL,
           [INTF_INSERTED_DATE]   [DATETIME] NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_RETURN_RESERVE')
                      AND NAME = 'DF_SNAP_RETURN_RESERVE_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].SNAP_RETURN_RESERVE
        ADD CONSTRAINT DF_SNAP_RETURN_RESERVE_INTF_INSERTED_DATE DEFAULT (Getdate()) FOR INTF_INSERTED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_RETURN_RESERVE'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1
DEALLOCATE CUR1
GO

  --------------------------------SNAP_ITEM_UOM--------------------------------------------

 IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_ITEM_UOM'---2
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[SNAP_ITEM_UOM]
        (          
		   [ITEM_UOM_CONVERSION_ID]         INT NOT NULL,             
           [ITEM_ID]                           VARCHAR(200) NULL,
           [ITEM_NO]                           VARCHAR(200) NULL,
           [ITEM_NAME]                         VARCHAR(200) NULL,
           [PRIMARY_UOM_BASELINE_FACTOR]       VARCHAR(200)  NULL,
           [PRIMARY_UOM_CODE]                  VARCHAR(200)  NULL,
           [PRIMARY_UOM_NAME]                  VARCHAR(200)  NULL,
           [SECONDARY_UOM_CONVERSION_FACTOR]   VARCHAR(200)  NULL,
           [SECONDARY_UOM_CODE]                VARCHAR(200)  NULL,
           [SECONDARY_UOM_NAME]                VARCHAR(200)  NULL,
           [STATUS]                            VARCHAR(200)  NULL,
           [UPLOAD_DATE]                       DATETIME NULL,
           [MODIFIED_BY]                       VARCHAR(200) NULL,
           [MODIFIED_DATE]                     DATETIME NULL,
           [CREATED_BY]                        VARCHAR(200) NULL,
           [CREATED_DATE]                      DATETIME NULL,
           [INTF_INSERTED_DATE] 			   DATETIME  NULL,
           [ADD_CHG_DEL_INDICATOR]             VARCHAR(200)  NULL,
           [BATCH_ID]					       VARCHAR(200)  NULL,
           [SOURCE]                      	   VARCHAR(200) NULL
     )
  END
GO
  -----DEFAULT CONSTRAINT-------------
   
   IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('dbo.SNAP_ITEM_UOM')
                      AND NAME = 'DF_SNAP_ITEM_UOM_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [dbo].SNAP_ITEM_UOM
        ADD CONSTRAINT DF_SNAP_ITEM_UOM_INTF_INSERTED_DATE DEFAULT (Getdate()) FOR INTF_INSERTED_DATE

  END
        
        GO
   
  

IF  EXISTS (SELECT 1
FROM SYS.STATS
WHERE OBJECT_NAME(OBJECT_ID) = 'SNAP_ITEM_UOM'
     AND AUTO_CREATED = 0
   AND NAME='ITEM_UOM_CONVERSION_ID')
  BEGIN
      DROP STATISTICS SNAP_ITEM_UOM.ITEM_UOM_CONVERSION_ID
  END
GO
 

 IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'SNAP_ITEM_UOM'
                      AND COLUMN_NAME = 'ITEM_UOM_CONVERSION_ID'
                      AND TABLE_SCHEMA = 'DBO' )
  BEGIN
      ALTER TABLE SNAP_ITEM_UOM
        ALTER COLUMN  ITEM_UOM_CONVERSION_ID  [NUMERIC](38, 0)  NOT NULL
  END
 GO
 
   IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'SNAP_ITEM_UOM_2017_SEMI_ANNUAL_2' AND COLUMN_NAME='ITEM_UOM_CONVERSION_ID' AND DATA_TYPE='INT'
                                           AND TABLE_SCHEMA = 'DBO' )
  BEGIN
     DROP TABLE SNAP_ITEM_UOM_2017_SEMI_ANNUAL_2
  END
 GO
 
   
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_ITEM_UOM'--TABLE NAME
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
      EXEC Sp_executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1
DEALLOCATE CUR1
GO

--------------------------------SNAP_RETURN_RATE_FORECAST--------------------------------------------
 
 IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'SNAP_RETURN_RATE_FORECAST'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[SNAP_RETURN_RATE_FORECAST]
        (          
           [RETURN_RATE_FORECAST_INTERFACE_ID]         VARCHAR(200)  NULL,             
           [ITEM_ID]                     VARCHAR(200)  NULL,
           [ITEM_NO]                     VARCHAR(200) NULL,
           [ITEM_NAME]                   VARCHAR(200) NULL,
           [FORECAST_YEAR]               VARCHAR(200) NULL,
           [FORECAST_MONTH]              VARCHAR(25) NULL,
           [RATE]                        VARCHAR(200) NULL,
           [FORECAST_NAME]               VARCHAR(200) NULL,
           [FORECAST_VER]                VARCHAR(200) NULL,
           [CREATED_BY]                  VARCHAR(200) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(200) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [INTF_INSERTED_DATE]           DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(200)  NULL,
           [BATCH_ID]                    VARCHAR(200)  NULL,
           [SOURCE]                      VARCHAR(200) NULL
     )
  END
  GO
  
  ------------------------DEFAULT CONSTRAINT--------------------

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.SNAP_RETURN_RATE_FORECAST')
                      AND NAME = 'DF_SNAP_RETURN_RATE_FORECAST_INTF_INSERTED_DATE')
  BEGIN
      ALTER TABLE [DBO].[SNAP_RETURN_RATE_FORECAST]
        ADD CONSTRAINT [DF_SNAP_RETURN_RATE_FORECAST_INTF_INSERTED_DATE] DEFAULT (GETDATE()) FOR INTF_INSERTED_DATE
  END
  GO
  
  -------------------------------------------SNAP STATISTICS------------------------------------

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'SNAP_RETURN_RATE_FORECAST'--TABLE NAME
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
