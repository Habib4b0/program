IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'REBATE_PLAN_MASTER'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[REBATE_PLAN_MASTER]
        (
           REBATE_PLAN_MASTER_SID     INT NOT NULL IDENTITY(1, 1),
           REBATE_PLAN_ID             VARCHAR(50) NOT NULL,
           REBATE_PLAN_NO             VARCHAR(50) NOT NULL,
           REBATE_PLAN_NAME           VARCHAR(100) NULL,
           REBATE_PLAN_STATUS         INT NOT NULL,
           REBATE_PLAN_TYPE           INT NULL,
           REBATE_STRUCTURE           INT NOT NULL,
           REBATE_RANGE_BASED_ON      INT NOT NULL,
           REBATE_BASED_ON            INT NOT NULL,
           REBATE_RULE                VARCHAR(50) NULL,
           FORMULA_TYPE               INT NULL,
           MANF_COMPANY_MASTER_SID    INT NULL,
           BOGO_ELIGIBLE              CHAR(1) NULL,
           SELF_GROWTH_INDICATOR      CHAR(1) NULL,
           SELF_GROWTH_REFERENCE      VARCHAR(50) NULL,
           SELF_GROWTH_FROM           DATETIME NULL,
           SELF_GROWTH_TO             DATETIME NULL,
           MARKET_SHARE_INDICATOR     CHAR(1) NULL,
           MARKET_SHARE_REFERENCE     VARCHAR(50) NULL,
           MARKET_SHARE_FROM          DATETIME NULL,
           MARKET_SHARE_TO            DATETIME NULL,
           SECONDARY_REBATE_PLAN_ID   VARCHAR(50) NULL,
           SECONDARY_REBATE_PLAN_NO   VARCHAR(50) NULL,
           SECONDARY_REBATE_PLAN_NAME VARCHAR(100) NULL,
           INTERNAL_NOTES             VARCHAR(4000) NULL,
           INBOUND_STATUS             CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS         BIT NOT NULL,
           BATCH_ID                   VARCHAR(50) NULL,
           [SOURCE]                   VARCHAR(50) NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'REBATE_PLAN_MASTER'
                      AND COLUMN_NAME = 'NET_SALES_FORMULA_MASTER_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE REBATE_PLAN_MASTER
        ADD NET_SALES_FORMULA_MASTER_SID INT NULL
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'REBATE_PLAN_MASTER'
                      AND COLUMN_NAME = 'CDR_MODEL_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE REBATE_PLAN_MASTER
        ADD CDR_MODEL_SID INT
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'REBATE_PLAN_MASTER'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_REBATE_PLAN_MASTER_REBATE_PLAN_MASTER_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [dbo].[REBATE_PLAN_MASTER]
    ADD CONSTRAINT PK_REBATE_PLAN_MASTER_REBATE_PLAN_MASTER_SID PRIMARY KEY(REBATE_PLAN_MASTER_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_PLAN_MASTER')
                      AND NAME = 'DF_REBATE_PLAN_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[REBATE_PLAN_MASTER]
        ADD CONSTRAINT [DF_REBATE_PLAN_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_PLAN_MASTER')
                      AND NAME = 'DF_REBATE_PLAN_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[REBATE_PLAN_MASTER]
        ADD CONSTRAINT [DF_REBATE_PLAN_MASTER_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_PLAN_MASTER')
                      AND NAME = 'DF_REBATE_PLAN_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[REBATE_PLAN_MASTER]
        ADD CONSTRAINT [DF_REBATE_PLAN_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_PLAN_MASTER')
                      AND NAME = 'DF_REBATE_PLAN_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[REBATE_PLAN_MASTER]
        ADD CONSTRAINT [DF_REBATE_PLAN_MASTER_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_PLAN_MASTER')
                      AND NAME = 'DF_REBATE_PLAN_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[REBATE_PLAN_MASTER]
        ADD CONSTRAINT [DF_REBATE_PLAN_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'REBATE_PLAN_MASTER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = Object_id('REBATE_PLAN_MASTER')
                            AND NAME = 'UQ_REBATE_PLAN_MASTER_REBATE_PLAN_ID')
        BEGIN
            ALTER TABLE REBATE_PLAN_MASTER
              ADD CONSTRAINT UQ_REBATE_PLAN_MASTER_REBATE_PLAN_ID UNIQUE(REBATE_PLAN_ID)
        END
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_REBATE_PLAN_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_REBATE_PLAN_MASTER]
        (
           REBATE_PLAN_MASTER_SID     INT NOT NULL,
           REBATE_PLAN_ID             VARCHAR(50) NOT NULL,
           REBATE_PLAN_NO             VARCHAR(50) NOT NULL,
           REBATE_PLAN_NAME           VARCHAR(100) NULL,
           REBATE_PLAN_STATUS         INT NOT NULL,
           REBATE_PLAN_TYPE           INT NULL,
           REBATE_STRUCTURE           INT NOT NULL,
           REBATE_RANGE_BASED_ON      INT NOT NULL,
           REBATE_BASED_ON            INT NOT NULL,
           REBATE_RULE                VARCHAR(50) NULL,
           FORMULA_TYPE               INT NULL,
           MANF_COMPANY_MASTER_SID    INT NULL,
           BOGO_ELIGIBLE              CHAR(1) NULL,
           SELF_GROWTH_INDICATOR      CHAR(1) NULL,
           SELF_GROWTH_REFERENCE      VARCHAR(50) NULL,
           SELF_GROWTH_FROM           DATETIME NULL,
           SELF_GROWTH_TO             DATETIME NULL,
           MARKET_SHARE_INDICATOR     CHAR(1) NULL,
           MARKET_SHARE_REFERENCE     VARCHAR(50) NULL,
           MARKET_SHARE_FROM          DATETIME NULL,
           MARKET_SHARE_TO            DATETIME NULL,
           SECONDARY_REBATE_PLAN_ID   VARCHAR(50) NULL,
           SECONDARY_REBATE_PLAN_NO   VARCHAR(50) NULL,
           SECONDARY_REBATE_PLAN_NAME VARCHAR(100) NULL,
           INTERNAL_NOTES             VARCHAR(4000) NULL,
           INBOUND_STATUS             CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS         BIT NOT NULL,
           BATCH_ID                   VARCHAR(50) NULL,
           [SOURCE]                   VARCHAR(50) NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL,
           ACTION_FLAG                CHAR(1) NOT NULL,
           ACTION_DATE                DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_REBATE_PLAN_MASTER'
                      AND COLUMN_NAME = 'NET_SALES_FORMULA_MASTER_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_REBATE_PLAN_MASTER
        ADD NET_SALES_FORMULA_MASTER_SID INT NULL
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_REBATE_PLAN_MASTER'
                      AND COLUMN_NAME = 'CDR_MODEL_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_REBATE_PLAN_MASTER
        ADD CDR_MODEL_SID INT NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_REBATE_PLAN_MASTER')
                      AND NAME = 'DF_HIST_REBATE_PLAN_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_REBATE_PLAN_MASTER]
        ADD CONSTRAINT [DF_HIST_REBATE_PLAN_MASTER_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_REBATE_PLAN_MASTER'--TABLE NAME
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

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'REBATE_PLAN_MASTER'--TABLE NAME
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

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_REBATE_PLAN_MASTER_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_REBATE_PLAN_MASTER_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_REBATE_PLAN_MASTER_INS]
ON [dbo].[REBATE_PLAN_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_REBATE_PLAN_MASTER
                    (REBATE_PLAN_MASTER_SID,
                     REBATE_PLAN_ID,
                     REBATE_PLAN_NO,
                     REBATE_PLAN_NAME,
                     REBATE_PLAN_STATUS,
                     REBATE_PLAN_TYPE,
                     REBATE_STRUCTURE,
                     REBATE_RANGE_BASED_ON,
                     REBATE_BASED_ON,
                     REBATE_RULE,
                     FORMULA_TYPE,
                     MANF_COMPANY_MASTER_SID,
                     BOGO_ELIGIBLE,
                     SELF_GROWTH_INDICATOR,
                     SELF_GROWTH_REFERENCE,
                     SELF_GROWTH_FROM,
                     SELF_GROWTH_TO,
                     MARKET_SHARE_INDICATOR,
                     MARKET_SHARE_REFERENCE,
                     MARKET_SHARE_FROM,
                     MARKET_SHARE_TO,
                     SECONDARY_REBATE_PLAN_ID,
                     SECONDARY_REBATE_PLAN_NO,
                     SECONDARY_REBATE_PLAN_NAME,
                     INTERNAL_NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     NET_SALES_FORMULA_MASTER_SID,
                     CDR_MODEL_SID,
                     ACTION_FLAG)
        SELECT REBATE_PLAN_MASTER_SID,
               REBATE_PLAN_ID,
               REBATE_PLAN_NO,
               REBATE_PLAN_NAME,
               REBATE_PLAN_STATUS,
               REBATE_PLAN_TYPE,
               REBATE_STRUCTURE,
               REBATE_RANGE_BASED_ON,
               REBATE_BASED_ON,
               REBATE_RULE,
               FORMULA_TYPE,
               MANF_COMPANY_MASTER_SID,
               BOGO_ELIGIBLE,
               SELF_GROWTH_INDICATOR,
               SELF_GROWTH_REFERENCE,
               SELF_GROWTH_FROM,
               SELF_GROWTH_TO,
               MARKET_SHARE_INDICATOR,
               MARKET_SHARE_REFERENCE,
               MARKET_SHARE_FROM,
               MARKET_SHARE_TO,
               SECONDARY_REBATE_PLAN_ID,
               SECONDARY_REBATE_PLAN_NO,
               SECONDARY_REBATE_PLAN_NAME,
               INTERNAL_NOTES,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               NET_SALES_FORMULA_MASTER_SID,
               CDR_MODEL_SID,
               'A'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_REBATE_PLAN_MASTER_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_REBATE_PLAN_MASTER_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_REBATE_PLAN_MASTER_UPD]
ON [dbo].[REBATE_PLAN_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_REBATE_PLAN_MASTER
                    (REBATE_PLAN_MASTER_SID,
                     REBATE_PLAN_ID,
                     REBATE_PLAN_NO,
                     REBATE_PLAN_NAME,
                     REBATE_PLAN_STATUS,
                     REBATE_PLAN_TYPE,
                     REBATE_STRUCTURE,
                     REBATE_RANGE_BASED_ON,
                     REBATE_BASED_ON,
                     REBATE_RULE,
                     FORMULA_TYPE,
                     MANF_COMPANY_MASTER_SID,
                     BOGO_ELIGIBLE,
                     SELF_GROWTH_INDICATOR,
                     SELF_GROWTH_REFERENCE,
                     SELF_GROWTH_FROM,
                     SELF_GROWTH_TO,
                     MARKET_SHARE_INDICATOR,
                     MARKET_SHARE_REFERENCE,
                     MARKET_SHARE_FROM,
                     MARKET_SHARE_TO,
                     SECONDARY_REBATE_PLAN_ID,
                     SECONDARY_REBATE_PLAN_NO,
                     SECONDARY_REBATE_PLAN_NAME,
                     INTERNAL_NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     NET_SALES_FORMULA_MASTER_SID,
                     CDR_MODEL_SID,
                     ACTION_FLAG)
        SELECT REBATE_PLAN_MASTER_SID,
               REBATE_PLAN_ID,
               REBATE_PLAN_NO,
               REBATE_PLAN_NAME,
               REBATE_PLAN_STATUS,
               REBATE_PLAN_TYPE,
               REBATE_STRUCTURE,
               REBATE_RANGE_BASED_ON,
               REBATE_BASED_ON,
               REBATE_RULE,
               FORMULA_TYPE,
               MANF_COMPANY_MASTER_SID,
               BOGO_ELIGIBLE,
               SELF_GROWTH_INDICATOR,
               SELF_GROWTH_REFERENCE,
               SELF_GROWTH_FROM,
               SELF_GROWTH_TO,
               MARKET_SHARE_INDICATOR,
               MARKET_SHARE_REFERENCE,
               MARKET_SHARE_FROM,
               MARKET_SHARE_TO,
               SECONDARY_REBATE_PLAN_ID,
               SECONDARY_REBATE_PLAN_NO,
               SECONDARY_REBATE_PLAN_NAME,
               INTERNAL_NOTES,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               NET_SALES_FORMULA_MASTER_SID,
               CDR_MODEL_SID,
               'C'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_REBATE_PLAN_MASTER_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_REBATE_PLAN_MASTER_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_REBATE_PLAN_MASTER_DEL]
ON [dbo].[REBATE_PLAN_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_REBATE_PLAN_MASTER
                    (REBATE_PLAN_MASTER_SID,
                     REBATE_PLAN_ID,
                     REBATE_PLAN_NO,
                     REBATE_PLAN_NAME,
                     REBATE_PLAN_STATUS,
                     REBATE_PLAN_TYPE,
                     REBATE_STRUCTURE,
                     REBATE_RANGE_BASED_ON,
                     REBATE_BASED_ON,
                     REBATE_RULE,
                     FORMULA_TYPE,
                     MANF_COMPANY_MASTER_SID,
                     BOGO_ELIGIBLE,
                     SELF_GROWTH_INDICATOR,
                     SELF_GROWTH_REFERENCE,
                     SELF_GROWTH_FROM,
                     SELF_GROWTH_TO,
                     MARKET_SHARE_INDICATOR,
                     MARKET_SHARE_REFERENCE,
                     MARKET_SHARE_FROM,
                     MARKET_SHARE_TO,
                     SECONDARY_REBATE_PLAN_ID,
                     SECONDARY_REBATE_PLAN_NO,
                     SECONDARY_REBATE_PLAN_NAME,
                     INTERNAL_NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     NET_SALES_FORMULA_MASTER_SID,
                     CDR_MODEL_SID,
                     ACTION_FLAG)
        SELECT REBATE_PLAN_MASTER_SID,
               REBATE_PLAN_ID,
               REBATE_PLAN_NO,
               REBATE_PLAN_NAME,
               REBATE_PLAN_STATUS,
               REBATE_PLAN_TYPE,
               REBATE_STRUCTURE,
               REBATE_RANGE_BASED_ON,
               REBATE_BASED_ON,
               REBATE_RULE,
               FORMULA_TYPE,
               MANF_COMPANY_MASTER_SID,
               BOGO_ELIGIBLE,
               SELF_GROWTH_INDICATOR,
               SELF_GROWTH_REFERENCE,
               SELF_GROWTH_FROM,
               SELF_GROWTH_TO,
               MARKET_SHARE_INDICATOR,
               MARKET_SHARE_REFERENCE,
               MARKET_SHARE_FROM,
               MARKET_SHARE_TO,
               SECONDARY_REBATE_PLAN_ID,
               SECONDARY_REBATE_PLAN_NO,
               SECONDARY_REBATE_PLAN_NAME,
               INTERNAL_NOTES,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               NET_SALES_FORMULA_MASTER_SID,
               CDR_MODEL_SID,
               'D'
        FROM   DELETED
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'REBATE_PLAN_TIER'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[REBATE_PLAN_TIER]
        (
           REBATE_PLAN_TIER_SID       INT NOT NULL IDENTITY(1, 1),
           REBATE_PLAN_TIER_ID        VARCHAR(50) NOT NULL,
           REBATE_PLAN_MASTER_SID     INT NOT NULL,
           TIER_FROM                  NUMERIC(22, 6) NOT NULL,
           TIER_TO                    NUMERIC(22, 6) NULL,
           TIER_LEVEL                 VARCHAR(50) NOT NULL,
           TIER_OPERATOR              INT NOT NULL,
           TIER_VALUE                 NUMERIC(22, 6) NOT NULL,
           FREE_AMOUNT                NUMERIC(22, 6) NULL,
           TIER_TOLERANCE             NUMERIC(22, 6) NULL,
           FORMULA_NO                 VARCHAR(50) NULL,
           FORMULA_NAME               VARCHAR(100) NULL,
           SECONDARY_REBATE_PLAN_NO   VARCHAR(50) NULL,
           SECONDARY_REBATE_PLAN_NAME VARCHAR(100) NULL,
           SECONDARY_REBATE_PLAN_SID  INT NULL,
           INBOUND_STATUS             CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS         BIT NOT NULL,
           BATCH_ID                   VARCHAR(50) NULL,
           [SOURCE]                   VARCHAR(50) NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL
        )
  END

GO

----------------------- COLUMN ADDITION --------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'FORMULA_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE REBATE_PLAN_TIER
        ADD FORMULA_NO VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'FORMULA_NAME'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE REBATE_PLAN_TIER
        ADD FORMULA_NAME VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'SECONDARY_REBATE_PLAN_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE REBATE_PLAN_TIER
        ADD SECONDARY_REBATE_PLAN_NO VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'SECONDARY_REBATE_PLAN_NAME'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE REBATE_PLAN_TIER
        ADD SECONDARY_REBATE_PLAN_NAME VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'SECONDARY_REBATE_PLAN_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE REBATE_PLAN_TIER
        ADD SECONDARY_REBATE_PLAN_SID INT
  END

GO
--ITEM_PRICING_QUALIFIER_SID
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'ITEM_PRICING_QUALIFIER_SID'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE REBATE_PLAN_TIER
        ADD ITEM_PRICING_QUALIFIER_SID INT
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'RETURN_RATE_SID'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE REBATE_PLAN_TIER
        ADD RETURN_RATE_SID INT
  END
GO

-----------------------------ALG-1508--------------
IF EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'REBATE_PLAN_TIER'
			AND COLUMN_NAME = 'ITEM_PRICING_QUALIFIER_SID'
			AND TABLE_SCHEMA = 'DBO'
			AND DATA_TYPE = 'INT'
		)
BEGIN
	IF EXISTS (
			SELECT 1
			FROM SYS.STATS AS S
			INNER JOIN SYS.STATS_COLUMNS AS SC ON S.OBJECT_ID = SC.OBJECT_ID
				AND S.STATS_ID = SC.STATS_ID
			INNER JOIN SYS.COLUMNS AS C ON SC.OBJECT_ID = C.OBJECT_ID
				AND C.COLUMN_ID = SC.COLUMN_ID
			WHERE S.OBJECT_ID = OBJECT_ID('REBATE_PLAN_TIER')
				AND c.NAME = 'ITEM_PRICING_QUALIFIER_SID'
			)
	BEGIN
		DROP STATISTICS REBATE_PLAN_TIER.ITEM_PRICING_QUALIFIER_SID
	END

	ALTER TABLE REBATE_PLAN_TIER ALTER COLUMN ITEM_PRICING_QUALIFIER_SID VARCHAR(1000)
END
GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'REBATE_PLAN_TIER'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_REBATE_PLAN_TIER_REBATE_PLAN_TIER_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [dbo].[REBATE_PLAN_TIER]
    ADD CONSTRAINT PK_REBATE_PLAN_TIER_REBATE_PLAN_TIER_SID PRIMARY KEY(REBATE_PLAN_TIER_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_PLAN_TIER')
                      AND NAME = 'DF_REBATE_PLAN_TIER_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[REBATE_PLAN_TIER]
        ADD CONSTRAINT [DF_REBATE_PLAN_TIER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_PLAN_TIER')
                      AND NAME = 'DF_REBATE_PLAN_TIER_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[REBATE_PLAN_TIER]
        ADD CONSTRAINT [DF_REBATE_PLAN_TIER_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_PLAN_TIER')
                      AND NAME = 'DF_REBATE_PLAN_TIER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[REBATE_PLAN_TIER]
        ADD CONSTRAINT [DF_REBATE_PLAN_TIER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_PLAN_TIER')
                      AND NAME = 'DF_REBATE_PLAN_TIER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[REBATE_PLAN_TIER]
        ADD CONSTRAINT [DF_REBATE_PLAN_TIER_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_PLAN_TIER')
                      AND NAME = 'DF_REBATE_PLAN_TIER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[REBATE_PLAN_TIER]
        ADD CONSTRAINT [DF_REBATE_PLAN_TIER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'REBATE_PLAN_TIER')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = Object_id('REBATE_PLAN_TIER')
                            AND NAME = 'UQ_REBATE_PLAN_TIER_REBATE_PLAN_TIER_ID_REBATE_PLAN_MASTER_SID')
        BEGIN
            ALTER TABLE REBATE_PLAN_TIER
              ADD CONSTRAINT UQ_REBATE_PLAN_TIER_REBATE_PLAN_TIER_ID_REBATE_PLAN_MASTER_SID UNIQUE(REBATE_PLAN_TIER_ID, REBATE_PLAN_MASTER_SID)
        END
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_REBATE_PLAN_TIER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_REBATE_PLAN_TIER]
        (
           REBATE_PLAN_TIER_SID       INT NOT NULL,
           REBATE_PLAN_TIER_ID        VARCHAR(50) NOT NULL,
           REBATE_PLAN_MASTER_SID     INT NOT NULL,
           TIER_FROM                  NUMERIC(22, 6) NOT NULL,
           TIER_TO                    NUMERIC(22, 6) NULL,
           TIER_LEVEL                 VARCHAR(50) NOT NULL,
           TIER_OPERATOR              INT NOT NULL,
           TIER_VALUE                 NUMERIC(22, 6) NOT NULL,
           FREE_AMOUNT                NUMERIC(22, 6) NULL,
           TIER_TOLERANCE             NUMERIC(22, 6) NULL,
           FORMULA_NO                 VARCHAR(50) NULL,
           FORMULA_NAME               VARCHAR(100) NULL,
           SECONDARY_REBATE_PLAN_NO   VARCHAR(50) NULL,
           SECONDARY_REBATE_PLAN_NAME VARCHAR(100) NULL,
           SECONDARY_REBATE_PLAN_SID  INT NULL,
           INBOUND_STATUS             CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS         BIT NOT NULL,
           BATCH_ID                   VARCHAR(50) NULL,
           [SOURCE]                   VARCHAR(50) NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL,
           ACTION_FLAG                CHAR(1) NOT NULL,
           ACTION_DATE                DATETIME NOT NULL
        )
  END

GO

----------------------- COLUMN ADDITION --------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'FORMULA_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_REBATE_PLAN_TIER
        ADD FORMULA_NO VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'FORMULA_NAME'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_REBATE_PLAN_TIER
        ADD FORMULA_NAME VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'SECONDARY_REBATE_PLAN_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_REBATE_PLAN_TIER
        ADD SECONDARY_REBATE_PLAN_NO VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'SECONDARY_REBATE_PLAN_NAME'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_REBATE_PLAN_TIER
        ADD SECONDARY_REBATE_PLAN_NAME VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'SECONDARY_REBATE_PLAN_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_REBATE_PLAN_TIER
        ADD SECONDARY_REBATE_PLAN_SID INT
  END

GO
--ITEM_PRICING_QUALIFIER_SID
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'ITEM_PRICING_QUALIFIER_SID'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE HIST_REBATE_PLAN_TIER
        ADD ITEM_PRICING_QUALIFIER_SID INT
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_REBATE_PLAN_TIER'
                      AND COLUMN_NAME = 'RETURN_RATE_SID'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE HIST_REBATE_PLAN_TIER
        ADD RETURN_RATE_SID INT
  END
GO
-----------------------------ALG-1508--------------
IF EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'HIST_REBATE_PLAN_TIER'
			AND COLUMN_NAME = 'ITEM_PRICING_QUALIFIER_SID'
			AND TABLE_SCHEMA = 'DBO'
			AND DATA_TYPE = 'INT'
		)
BEGIN
	IF EXISTS (
			SELECT 1
			FROM SYS.STATS AS S
			INNER JOIN SYS.STATS_COLUMNS AS SC ON S.OBJECT_ID = SC.OBJECT_ID
				AND S.STATS_ID = SC.STATS_ID
			INNER JOIN SYS.COLUMNS AS C ON SC.OBJECT_ID = C.OBJECT_ID
				AND C.COLUMN_ID = SC.COLUMN_ID
			WHERE S.OBJECT_ID = OBJECT_ID('HIST_REBATE_PLAN_TIER')
				AND c.NAME = 'ITEM_PRICING_QUALIFIER_SID'
			)
	BEGIN
		DROP STATISTICS HIST_REBATE_PLAN_TIER.ITEM_PRICING_QUALIFIER_SID
	END

	ALTER TABLE HIST_REBATE_PLAN_TIER ALTER COLUMN  ITEM_PRICING_QUALIFIER_SID VARCHAR(1000)
END
GO

--------------------------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_REBATE_PLAN_TIER')
                      AND NAME = 'DF_HIST_REBATE_PLAN_TIER_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_REBATE_PLAN_TIER]
        ADD CONSTRAINT [DF_HIST_REBATE_PLAN_TIER_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_REBATE_PLAN_TIER'--TABLE NAME
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

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'REBATE_PLAN_TIER'--TABLE NAME
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

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_REBATE_PLAN_TIER_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_REBATE_PLAN_TIER_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_REBATE_PLAN_TIER_INS]
ON [dbo].[REBATE_PLAN_TIER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_REBATE_PLAN_TIER
                    (REBATE_PLAN_TIER_SID,
                     REBATE_PLAN_TIER_ID,
                     REBATE_PLAN_MASTER_SID,
                     TIER_FROM,
                     TIER_TO,
                     TIER_LEVEL,
                     TIER_OPERATOR,
                     TIER_VALUE,
                     FREE_AMOUNT,
                     TIER_TOLERANCE,
                     FORMULA_NO,
                     FORMULA_NAME,
                     SECONDARY_REBATE_PLAN_NO,
                     SECONDARY_REBATE_PLAN_NAME,
                     SECONDARY_REBATE_PLAN_SID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG,
					 ITEM_PRICING_QUALIFIER_SID,
					 RETURN_RATE_SID)
        SELECT REBATE_PLAN_TIER_SID,
               REBATE_PLAN_TIER_ID,
               REBATE_PLAN_MASTER_SID,
               TIER_FROM,
               TIER_TO,
               TIER_LEVEL,
               TIER_OPERATOR,
               TIER_VALUE,
               FREE_AMOUNT,
               TIER_TOLERANCE,
               FORMULA_NO,
               FORMULA_NAME,
               SECONDARY_REBATE_PLAN_NO,
               SECONDARY_REBATE_PLAN_NAME,
               SECONDARY_REBATE_PLAN_SID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'A',
			   ITEM_PRICING_QUALIFIER_SID,
			   RETURN_RATE_SID
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_REBATE_PLAN_TIER_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_REBATE_PLAN_TIER_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_REBATE_PLAN_TIER_UPD]
ON [dbo].[REBATE_PLAN_TIER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_REBATE_PLAN_TIER
                    (REBATE_PLAN_TIER_SID,
                     REBATE_PLAN_TIER_ID,
                     REBATE_PLAN_MASTER_SID,
                     TIER_FROM,
                     TIER_TO,
                     TIER_LEVEL,
                     TIER_OPERATOR,
                     TIER_VALUE,
                     FREE_AMOUNT,
                     TIER_TOLERANCE,
                     FORMULA_NO,
                     FORMULA_NAME,
                     SECONDARY_REBATE_PLAN_NO,
                     SECONDARY_REBATE_PLAN_NAME,
                     SECONDARY_REBATE_PLAN_SID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG,
					 ITEM_PRICING_QUALIFIER_SID,
					 RETURN_RATE_SID)
        SELECT REBATE_PLAN_TIER_SID,
               REBATE_PLAN_TIER_ID,
               REBATE_PLAN_MASTER_SID,
               TIER_FROM,
               TIER_TO,
               TIER_LEVEL,
               TIER_OPERATOR,
               TIER_VALUE,
               FREE_AMOUNT,
               TIER_TOLERANCE,
               FORMULA_NO,
               FORMULA_NAME,
               SECONDARY_REBATE_PLAN_NO,
               SECONDARY_REBATE_PLAN_NAME,
               SECONDARY_REBATE_PLAN_SID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'C',
			   ITEM_PRICING_QUALIFIER_SID,
			   RETURN_RATE_SID
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_REBATE_PLAN_TIER_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_REBATE_PLAN_TIER_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_REBATE_PLAN_TIER_DEL]
ON [dbo].[REBATE_PLAN_TIER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_REBATE_PLAN_TIER
                    (REBATE_PLAN_TIER_SID,
                     REBATE_PLAN_TIER_ID,
                     REBATE_PLAN_MASTER_SID,
                     TIER_FROM,
                     TIER_TO,
                     TIER_LEVEL,
                     TIER_OPERATOR,
                     TIER_VALUE,
                     FREE_AMOUNT,
                     TIER_TOLERANCE,
                     FORMULA_NO,
                     FORMULA_NAME,
                     SECONDARY_REBATE_PLAN_NO,
                     SECONDARY_REBATE_PLAN_NAME,
                     SECONDARY_REBATE_PLAN_SID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG,
					 ITEM_PRICING_QUALIFIER_SID,
					 RETURN_RATE_SID)
        SELECT REBATE_PLAN_TIER_SID,
               REBATE_PLAN_TIER_ID,
               REBATE_PLAN_MASTER_SID,
               TIER_FROM,
               TIER_TO,
               TIER_LEVEL,
               TIER_OPERATOR,
               TIER_VALUE,
               FREE_AMOUNT,
               TIER_TOLERANCE,
               FORMULA_NO,
               FORMULA_NAME,
               SECONDARY_REBATE_PLAN_NO,
               SECONDARY_REBATE_PLAN_NAME,
               SECONDARY_REBATE_PLAN_SID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'D',
			   ITEM_PRICING_QUALIFIER_SID,
			   RETURN_RATE_SID
        FROM   DELETED
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'REBATE_TIER_FORMULA'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[REBATE_TIER_FORMULA]
        (
           REBATE_TIER_FORMULA_SID  INT NOT NULL IDENTITY(1, 1),
           REBATE_PLAN_MASTER_SID   INT NOT NULL,
           REBATE_TIER_FORMULA_ID   VARCHAR(50) NOT NULL,
           REBATE_TIER_FORMULA_NO   VARCHAR(50) NOT NULL,
           REBATE_TIER_FORMULA_NAME VARCHAR(100) NOT NULL,
           INBOUND_STATUS           CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS       BIT NOT NULL,
           BATCH_ID                 VARCHAR(50) NULL,
           [SOURCE]                 VARCHAR(50) NULL,
           [CREATED_BY]             INT NOT NULL,
           [CREATED_DATE]           DATETIME NOT NULL,
           [MODIFIED_BY]            INT NOT NULL,
           [MODIFIED_DATE]          DATETIME NOT NULL
        )
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'REBATE_TIER_FORMULA'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_REBATE_TIER_FORMULA_REBATE_TIER_FORMULA_SID'
                     AND TYPE = 'PK')

BEGIN
  ALTER TABLE [dbo].[REBATE_TIER_FORMULA]
    ADD CONSTRAINT PK_REBATE_TIER_FORMULA_REBATE_TIER_FORMULA_SID PRIMARY KEY(REBATE_TIER_FORMULA_SID)
END

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_TIER_FORMULA')
                      AND NAME = 'DF_REBATE_TIER_FORMULA_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[REBATE_TIER_FORMULA]
        ADD CONSTRAINT [DF_REBATE_TIER_FORMULA_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_TIER_FORMULA')
                      AND NAME = 'DF_REBATE_TIER_FORMULA_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[REBATE_TIER_FORMULA]
        ADD CONSTRAINT [DF_REBATE_TIER_FORMULA_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_TIER_FORMULA')
                      AND NAME = 'DF_REBATE_TIER_FORMULA_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[REBATE_TIER_FORMULA]
        ADD CONSTRAINT [DF_REBATE_TIER_FORMULA_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_TIER_FORMULA')
                      AND NAME = 'DF_REBATE_TIER_FORMULA_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[REBATE_TIER_FORMULA]
        ADD CONSTRAINT [DF_REBATE_TIER_FORMULA_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.REBATE_TIER_FORMULA')
                      AND NAME = 'DF_REBATE_TIER_FORMULA_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[REBATE_TIER_FORMULA]
        ADD CONSTRAINT [DF_REBATE_TIER_FORMULA_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'REBATE_TIER_FORMULA')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = Object_id('REBATE_TIER_FORMULA')
                            AND NAME = 'UQ_REBATE_TIER_FORMULA_REBATE_TIER_FORMULA_ID_REBATE_PLAN_MASTER_SID')
        BEGIN
            ALTER TABLE REBATE_TIER_FORMULA
              ADD CONSTRAINT UQ_REBATE_TIER_FORMULA_REBATE_TIER_FORMULA_ID_REBATE_PLAN_MASTER_SID UNIQUE(REBATE_TIER_FORMULA_ID, REBATE_PLAN_MASTER_SID)
        END
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_REBATE_TIER_FORMULA'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_REBATE_TIER_FORMULA]
        (
           REBATE_TIER_FORMULA_SID  INT NOT NULL,
           REBATE_PLAN_MASTER_SID   INT NOT NULL,
           REBATE_TIER_FORMULA_ID   VARCHAR(50) NOT NULL,
           REBATE_TIER_FORMULA_NO   VARCHAR(50) NOT NULL,
           REBATE_TIER_FORMULA_NAME VARCHAR(100) NOT NULL,
           INBOUND_STATUS           CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS       BIT NOT NULL,
           BATCH_ID                 VARCHAR(50) NULL,
           [SOURCE]                 VARCHAR(50) NULL,
           [CREATED_BY]             INT NOT NULL,
           [CREATED_DATE]           DATETIME NOT NULL,
           [MODIFIED_BY]            INT NOT NULL,
           [MODIFIED_DATE]          DATETIME NOT NULL,
           ACTION_FLAG              CHAR(1) NOT NULL,
           ACTION_DATE              DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_REBATE_TIER_FORMULA')
                      AND NAME = 'DF_HIST_REBATE_TIER_FORMULA_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_REBATE_TIER_FORMULA]
        ADD CONSTRAINT [DF_HIST_REBATE_TIER_FORMULA_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_REBATE_TIER_FORMULA'--TABLE NAME
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

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'REBATE_TIER_FORMULA'--TABLE NAME
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

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_REBATE_TIER_FORMULA_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_REBATE_TIER_FORMULA_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_REBATE_TIER_FORMULA_INS]
ON [dbo].[REBATE_TIER_FORMULA]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_REBATE_TIER_FORMULA
                    (REBATE_TIER_FORMULA_SID,
                     REBATE_PLAN_MASTER_SID,
                     REBATE_TIER_FORMULA_ID,
                     REBATE_TIER_FORMULA_NO,
                     REBATE_TIER_FORMULA_NAME,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT REBATE_TIER_FORMULA_SID,
               REBATE_PLAN_MASTER_SID,
               REBATE_TIER_FORMULA_ID,
               REBATE_TIER_FORMULA_NO,
               REBATE_TIER_FORMULA_NAME,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
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
           WHERE  [Name] = N'TRG_REBATE_TIER_FORMULA_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_REBATE_TIER_FORMULA_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_REBATE_TIER_FORMULA_UPD]
ON [dbo].[REBATE_TIER_FORMULA]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_REBATE_TIER_FORMULA
                    (REBATE_TIER_FORMULA_SID,
                     REBATE_PLAN_MASTER_SID,
                     REBATE_TIER_FORMULA_ID,
                     REBATE_TIER_FORMULA_NO,
                     REBATE_TIER_FORMULA_NAME,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT REBATE_TIER_FORMULA_SID,
               REBATE_PLAN_MASTER_SID,
               REBATE_TIER_FORMULA_ID,
               REBATE_TIER_FORMULA_NO,
               REBATE_TIER_FORMULA_NAME,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
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
           WHERE  [Name] = N'TRG_REBATE_TIER_FORMULA_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_REBATE_TIER_FORMULA_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_REBATE_TIER_FORMULA_DEL]
ON [dbo].[REBATE_TIER_FORMULA]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_REBATE_TIER_FORMULA
                    (REBATE_TIER_FORMULA_SID,
                     REBATE_PLAN_MASTER_SID,
                     REBATE_TIER_FORMULA_ID,
                     REBATE_TIER_FORMULA_NO,
                     REBATE_TIER_FORMULA_NAME,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT REBATE_TIER_FORMULA_SID,
               REBATE_PLAN_MASTER_SID,
               REBATE_TIER_FORMULA_ID,
               REBATE_TIER_FORMULA_NO,
               REBATE_TIER_FORMULA_NAME,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'D'
        FROM   DELETED
  END

GO 

