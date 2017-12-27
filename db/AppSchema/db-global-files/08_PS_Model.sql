------------------------------------PS_MODEL----------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_MODEL'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[PS_MODEL]
        (
           PS_MODEL_SID       INT NOT NULL IDENTITY(1, 1),
           PS_ID              VARCHAR(50) NOT NULL,
           PS_NO              VARCHAR(50) NULL,
           PS_NAME            VARCHAR(100) NULL,
           PS_TYPE            INT NULL,
           PS_CATEGORY        INT NULL,
           PS_STATUS          INT NULL,
           PS_DESIGNATION     VARCHAR(50) NULL,
           PS_START_DATE      DATETIME NOT NULL,
           PS_END_DATE        DATETIME NULL,
           PARENT_PS_ID       VARCHAR(50) NULL,
           PARENT_PS_NAME     VARCHAR(100) NULL,
           PS_TRADE_CLASS     INT NULL,
           INTERNAL_NOTES     VARCHAR(4000),
           INBOUND_STATUS     CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS BIT NOT NULL,
           BATCH_ID           VARCHAR(50) NULL,
           [SOURCE]           VARCHAR(50) NULL,
           [CREATED_BY]       INT NOT NULL,
           [CREATED_DATE]     DATETIME NOT NULL,
           [MODIFIED_BY]      INT NOT NULL,
           [MODIFIED_DATE]    DATETIME NOT NULL
        )
  END

GO

------------------------ DATATYPE CHANGE ------------------------
IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'PS_DESIGNATION'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_MODEL')
  BEGIN
      DROP STATISTICS DBO.PS_MODEL.PS_DESIGNATION
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_MODEL'
                  AND COLUMN_NAME = 'PS_DESIGNATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_MODEL
        ALTER COLUMN PS_DESIGNATION INT
  END

GO

---------------------PRIMARY KEY CONSTRAINT--------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'PS_MODEL'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_PS_MODEL_PS_MODEL_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [DBO].[PS_MODEL]
    ADD CONSTRAINT PK_PS_MODEL_PS_MODEL_SID PRIMARY KEY(PS_MODEL_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_MODEL')
                      AND NAME = 'DF_PS_MODEL_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[PS_MODEL]
        ADD CONSTRAINT [DF_PS_MODEL_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_MODEL')
                      AND NAME = 'DF_PS_MODEL_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PS_MODEL]
        ADD CONSTRAINT [DF_PS_MODEL_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_MODEL')
                      AND NAME = 'DF_PS_MODEL_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[PS_MODEL]
        ADD CONSTRAINT [DF_PS_MODEL_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_MODEL')
                      AND NAME = 'DF_PS_MODEL_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PS_MODEL]
        ADD CONSTRAINT [DF_PS_MODEL_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_MODEL')
                      AND NAME = 'DF_PS_MODEL_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[PS_MODEL]
        ADD CONSTRAINT [DF_PS_MODEL_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

--------------------------------UNIQUE_CONSTRAINT-------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'PS_MODEL')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('PS_MODEL')
                            AND NAME = 'UQ_PS_MODEL_PS_ID')
        BEGIN
            ALTER TABLE PS_MODEL
              ADD CONSTRAINT UQ_PS_MODEL_PS_ID UNIQUE(PS_ID)
        END
  END

GO

--------------------------COLUMN RENAME STARTS---------------------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_MODEL'
                  AND COLUMN_NAME = 'TRADE_CLASS')
  BEGIN
      EXEC SP_RENAME
        'PS_MODEL.TRADE_CLASS',
        'PS_TRADE_CLASS',
        'COLUMN'
  END

GO

--------------------------COLUMN RENAME ENDS---------------------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'PS_MODEL'--TABLE NAME
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

--------------------------------------HIST_PS_MODEL-------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_MODEL'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_PS_MODEL]
        (
           PS_MODEL_SID       INT NOT NULL,
           PS_ID              VARCHAR(50) NOT NULL,
           PS_NO              VARCHAR(50) NULL,
           PS_NAME            VARCHAR(100) NULL,
           PS_TYPE            INT NULL,
           PS_CATEGORY        INT NULL,
           PS_STATUS          INT NULL,
           PS_DESIGNATION     VARCHAR(50) NULL,
           PS_START_DATE      DATETIME NOT NULL,
           PS_END_DATE        DATETIME NULL,
           PARENT_PS_ID       VARCHAR(50) NULL,
           PARENT_PS_NAME     VARCHAR(100) NULL,
           PS_TRADE_CLASS     INT NULL,
           INTERNAL_NOTES     VARCHAR(4000),
           INBOUND_STATUS     CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS BIT NOT NULL,
           BATCH_ID           VARCHAR(50) NULL,
           [SOURCE]           VARCHAR(50) NULL,
           [CREATED_BY]       INT NOT NULL,
           [CREATED_DATE]     DATETIME NOT NULL,
           [MODIFIED_BY]      INT NOT NULL,
           [MODIFIED_DATE]    DATETIME NOT NULL,
           ACTION_FLAG        CHAR(1) NOT NULL,
           ACTION_DATE        DATETIME NOT NULL
        )
  END

GO

------------------------ DATATYPE CHANGE ------------------------
IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'PS_DESIGNATION'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_MODEL')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_MODEL.PS_DESIGNATION
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_MODEL'
                  AND COLUMN_NAME = 'PS_DESIGNATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_MODEL
        ALTER COLUMN PS_DESIGNATION INT
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_PS_MODEL')
                      AND NAME = 'DF_HIST_PS_MODEL_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_PS_MODEL]
        ADD CONSTRAINT [DF_HIST_PS_MODEL_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

--------------------------COLUMN RENAME STARTS---------------------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_MODEL'
                  AND COLUMN_NAME = 'TRADE_CLASS')
  BEGIN
      EXEC SP_RENAME
        'HIST_PS_MODEL.TRADE_CLASS',
        'PS_TRADE_CLASS',
        'COLUMN'
  END

GO

--------------------------COLUMN RENAME ENDS---------------------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_PS_MODEL'--TABLE NAME
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

-----------------------------------PS_MODEL TRIGGER----------------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_PS_MODEL_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_MODEL_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_MODEL_UPD]
ON [DBO].[PS_MODEL]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON 
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_PS_MODEL
                    (PS_MODEL_SID,
                     PS_ID,
                     PS_NO,
                     PS_NAME,
                     PS_TYPE,
                     PS_CATEGORY,
                     PS_STATUS,
                     PS_DESIGNATION,
                     PS_START_DATE,
                     PS_END_DATE,
                     PARENT_PS_ID,
                     PARENT_PS_NAME,
                     PS_TRADE_CLASS,
                     INTERNAL_NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT PS_MODEL_SID,
               PS_ID,
               PS_NO,
               PS_NAME,
               PS_TYPE,
               PS_CATEGORY,
               PS_STATUS,
               PS_DESIGNATION,
               PS_START_DATE,
               PS_END_DATE,
               PARENT_PS_ID,
               PARENT_PS_NAME,
               PS_TRADE_CLASS,
               INTERNAL_NOTES,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
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
           WHERE  [NAME] = N'TRG_PS_MODEL_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_MODEL_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_MODEL_INS]
ON [DBO].[PS_MODEL]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON 
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_PS_MODEL
                    (PS_MODEL_SID,
                     PS_ID,
                     PS_NO,
                     PS_NAME,
                     PS_TYPE,
                     PS_CATEGORY,
                     PS_STATUS,
                     PS_DESIGNATION,
                     PS_START_DATE,
                     PS_END_DATE,
                     PARENT_PS_ID,
                     PARENT_PS_NAME,
                     PS_TRADE_CLASS,
                     INTERNAL_NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT PS_MODEL_SID,
               PS_ID,
               PS_NO,
               PS_NAME,
               PS_TYPE,
               PS_CATEGORY,
               PS_STATUS,
               PS_DESIGNATION,
               PS_START_DATE,
               PS_END_DATE,
               PARENT_PS_ID,
               PARENT_PS_NAME,
               PS_TRADE_CLASS,
               INTERNAL_NOTES,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
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
           WHERE  [NAME] = N'TRG_PS_MODEL_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_MODEL_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_MODEL_DEL]
ON [DBO].[PS_MODEL]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON 
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_PS_MODEL
                    (PS_MODEL_SID,
                     PS_ID,
                     PS_NO,
                     PS_NAME,
                     PS_TYPE,
                     PS_CATEGORY,
                     PS_STATUS,
                     PS_DESIGNATION,
                     PS_START_DATE,
                     PS_END_DATE,
                     PARENT_PS_ID,
                     PARENT_PS_NAME,
                     PS_TRADE_CLASS,
                     INTERNAL_NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT PS_MODEL_SID,
               PS_ID,
               PS_NO,
               PS_NAME,
               PS_TYPE,
               PS_CATEGORY,
               PS_STATUS,
               PS_DESIGNATION,
               PS_START_DATE,
               PS_END_DATE,
               PARENT_PS_ID,
               PARENT_PS_NAME,
               PS_TRADE_CLASS,
               INTERNAL_NOTES,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'D'
        FROM   DELETED
  END

GO

-------------------------------------PS_DETAILS--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[PS_DETAILS]
        (
           PS_DETAILS_SID                INT NOT NULL IDENTITY(1, 1),
           PS_MODEL_SID                  INT NOT NULL,
           IFP_MODEL_SID                 INT NULL,
           ITEM_MASTER_SID               INT NOT NULL,
           CONTRACT_PRICE                NUMERIC(22, 6) NULL,
           ITEM_PRICING_QUALIFIER_SID    INT NULL,
           CONTRACT_PRICE_START_DATE     DATETIME NOT NULL,
           CONTRACT_PRICE_END_DATE       DATETIME NULL,
           ITEM_PS_ATTACHED_STATUS       INT NULL,
           ITEM_PS_ATTACHED_DATE         DATETIME,
           BASE_PRICE                    NUMERIC(22, 6) NULL,
           PRICE_TOLERANCE               NUMERIC(22, 6) NULL,
           PRICE_TOLERANCE_TYPE          INT NULL,
           PRICE_TOLERANCE_FREQUENCY     INT NULL,
           PRICE_TOLERANCE_INTERVAL      INT NULL,
           PRICE_PROTECTION_START_DATE   DATETIME NULL,
           PRICE_PROTECTION_END_DATE     DATETIME NULL,
           PRICE_REVISION                NUMERIC(22, 6) NULL,
           REVISION_DATE                 DATETIME NULL,
           SUGGESTED_PRICE               NUMERIC(22, 6) NULL,
           PRICE                         NUMERIC(22, 6) NOT NULL,
           [STATUS]                      INT NULL,
           [BRAND_MASTER_SID]            INT NULL,
           [NEP]                         NUMERIC(22, 6) NULL,
           [PRICE_PROTECTION_STATUS]     INT NULL,
           [PRICE_PROTECTION_PRICE_TYPE] INT NULL,
           [NEP_FORMULA]                 VARCHAR(100) NULL,
           [MAX_INCREMENTAL_CHANGE]      NUMERIC(22, 6) NULL,
           [RESET_ELIGIBLE]              VARCHAR(5) NULL,
           [RESET_TYPE]                  INT NULL,
           [RESET_DATE]                  DATETIME NULL,
           [RESET_INTERVAL]              INT NULL,
           [RESET_FREQUENCY]             INT NULL,
           [NET_PRICE_TYPE]              VARCHAR(5) NULL,
           [NET_PRICE_TYPE_FORMULA]      VARCHAR(100) NULL,
           INBOUND_STATUS                CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS            BIT NOT NULL,
           BATCH_ID                      VARCHAR(50) NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [CREATED_BY]                  INT NOT NULL,
           [CREATED_DATE]                DATETIME NOT NULL,
           [MODIFIED_BY]                 INT NOT NULL,
           [MODIFIED_DATE]               DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'STATUS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD STATUS INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD BASE_PRICE_TYPE VARCHAR(50)
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_ENTRY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD BASE_PRICE_ENTRY NUMERIC(22, 6)
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD BASE_PRICE_DATE DATETIME
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD NET_BASE_PRICE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_DDLB'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD BASE_PRICE_DDLB INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD NET_BASE_PRICE_FORMULA_ID INT
  END

GO

------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'SUBSEQUENT_PERIOD_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD SUBSEQUENT_PERIOD_PRICE_TYPE INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD NET_SUBSEQUENT_PERIOD_PRICE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD NET_SUBSEQUENT_PRICE_FORMULA_ID INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD RESET_PRICE_TYPE INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD NET_RESET_PRICE_TYPE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD NET_RESET_PRICE_FORMULA_ID INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'BRAND_MASTER_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD BRAND_MASTER_SID INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'NEP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD NEP NUMERIC(22, 6)
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_INTERVAL'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_DETAILS.PRICE_TOLERANCE_INTERVAL
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_DETAILS'
                  AND COLUMN_NAME = 'PRICE_TOLERANCE_INTERVAL'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_INTERVAL INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_STATUS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD PRICE_PROTECTION_STATUS INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD PRICE_PROTECTION_PRICE_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'NEP_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD NEP_FORMULA VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'MAX_INCREMENTAL_CHANGE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD MAX_INCREMENTAL_CHANGE NUMERIC(22, 6)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_ELIGIBLE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD RESET_ELIGIBLE DATETIME
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD RESET_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD RESET_DATE DATETIME
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_INTERVAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD RESET_INTERVAL INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_FREQUENCY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD RESET_FREQUENCY INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD NET_PRICE_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ADD NET_PRICE_TYPE_FORMULA VARCHAR(100)
  END

GO
--------------------------------ADDED COLUMN ADD_COPY_INDICATOR--------------------------------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PS_DETAILS' AND COLUMN_NAME  = 'ADD_COPY_INDICATOR' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PS_DETAILS
        ADD ADD_COPY_INDICATOR CHAR(1)
  END
GO

-----------------------------DATATYPE CHANGE STARTS----------------
-----------------------------DATATYPE CHANGE ENDS----------------
IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'RESET_ELIGIBLE'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_DETAILS.RESET_ELIGIBLE
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'NET_PRICE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_DETAILS.NET_PRICE_TYPE
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_ELIGIBLE'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN [RESET_ELIGIBLE] VARCHAR(5)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN NET_PRICE_TYPE VARCHAR(5)
  END

GO

-----------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_BASE_PRICE'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_DETAILS.NET_BASE_PRICE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_BASE_PRICE'
                  AND TABLE_NAME = 'PS_DETAILS')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN NET_BASE_PRICE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_DETAILS.NET_SUBSEQUENT_PERIOD_PRICE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                  AND TABLE_NAME = 'PS_DETAILS')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN NET_SUBSEQUENT_PERIOD_PRICE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_RESET_PRICE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_DETAILS.NET_RESET_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_RESET_PRICE_TYPE'
                  AND TABLE_NAME = 'PS_DETAILS')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN NET_RESET_PRICE_TYPE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'BASE_PRICE_ENTRY'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_DETAILS.BASE_PRICE_ENTRY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'BASE_PRICE_ENTRY'
                  AND TABLE_NAME = 'PS_DETAILS')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN BASE_PRICE_ENTRY NUMERIC(22, 6)
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_FREQUENCY'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_DETAILS.PRICE_TOLERANCE_FREQUENCY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'PRICE_TOLERANCE_FREQUENCY'
                  AND TABLE_NAME = 'PS_DETAILS')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_FREQUENCY INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_DETAILS.PRICE_TOLERANCE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'PRICE_TOLERANCE_TYPE'
                  AND TABLE_NAME = 'PS_DETAILS')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_TYPE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NEP_FORMULA')
  BEGIN
      DROP STATISTICS PS_DETAILS.NEP_FORMULA
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_DETAILS'
                  AND COLUMN_NAME = 'NEP_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN NEP_FORMULA INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NET_PRICE_TYPE_FORMULA')
  BEGIN
      DROP STATISTICS PS_DETAILS.NET_PRICE_TYPE_FORMULA
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_DETAILS'
                  AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN NET_PRICE_TYPE_FORMULA INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'BASE_PRICE_TYPE')
  BEGIN
      DROP STATISTICS PS_DETAILS.BASE_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_DETAILS'
                  AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_DETAILS
        ALTER COLUMN BASE_PRICE_TYPE INT NULL
  END

GO

--------------------AGN-81

IF NOT EXISTS (
SELECT 1
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'PS_DETAILS'
AND COLUMN_NAME = 'PPA_INDEX_NO'
AND TABLE_SCHEMA = 'DBO'
)
BEGIN
IF EXISTS (
SELECT 1
FROM PS_DETAILS
)
BEGIN
ALTER TABLE PS_DETAILS ADD PPA_INDEX_NO INT
END
ELSE
BEGIN
ALTER TABLE PS_DETAILS ADD PPA_INDEX_NO INT NOT NULL
END
END
GO

IF EXISTS (
SELECT 1
FROM INFORMATION_SCHEMA.COLUMNS
WHERE TABLE_NAME = 'PS_DETAILS'
AND COLUMN_NAME = 'PPA_INDEX_NO'
AND TABLE_SCHEMA = 'DBO'
AND DATA_TYPE = 'INT'
AND IS_NULLABLE = 'YES'
)
BEGIN
UPDATE PS_DETAILS
SET PPA_INDEX_NO = 1
WHERE PPA_INDEX_NO IS NULL

ALTER TABLE PS_DETAILS

ALTER COLUMN PPA_INDEX_NO INT NOT NULL
END 
GO 


------------------------------
---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'PS_DETAILS'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_PS_DETAILS_PS_DETAILS_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [DBO].[PS_DETAILS]
    ADD CONSTRAINT PK_PS_DETAILS_PS_DETAILS_SID PRIMARY KEY(PS_DETAILS_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_DETAILS')
                      AND NAME = 'DF_PS_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[PS_DETAILS]
        ADD CONSTRAINT [DF_PS_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_DETAILS')
                      AND NAME = 'DF_PS_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PS_DETAILS]
        ADD CONSTRAINT [DF_PS_DETAILS_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_DETAILS')
                      AND NAME = 'DF_PS_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[PS_DETAILS]
        ADD CONSTRAINT [DF_PS_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_DETAILS')
                      AND NAME = 'DF_PS_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PS_DETAILS]
        ADD CONSTRAINT [DF_PS_DETAILS_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_DETAILS')
                      AND NAME = 'DF_PS_DETAILS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[PS_DETAILS]
        ADD CONSTRAINT [DF_PS_DETAILS_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO
-------AGN-81
IF NOT EXISTS (
		SELECT 'X'
		FROM SYS.DEFAULT_CONSTRAINTS
		WHERE PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_DETAILS')
			AND NAME = 'DF_PS_DETAILS_PPA_INDEX_NO'
		)
BEGIN
	ALTER TABLE [DBO].[PS_DETAILS] ADD CONSTRAINT [DF_PS_DETAILS_PPA_INDEX_NO] DEFAULT(1)
	FOR PPA_INDEX_NO
END
GO



-------------------------------------UNIQUE_CONSTRAINT----------------------------------------
--IF EXISTS (SELECT NAME
--           FROM   SYS.TABLES
--           WHERE  NAME = 'PS_DETAILS')
--  BEGIN
--      IF NOT EXISTS (SELECT 1
--                     FROM   SYS.KEY_CONSTRAINTS
--                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
--                            AND PARENT_OBJECT_ID = OBJECT_ID('PS_DETAILS')
--                            AND NAME = 'UQ_PS_DETAILS_PS_MODEL_SID_IFP_MODEL_SID_ITEM_MASTER_SID')
--        BEGIN
--            ALTER TABLE PS_DETAILS
--              ADD CONSTRAINT UQ_PS_DETAILS_PS_MODEL_SID_IFP_MODEL_SID_ITEM_MASTER_SID UNIQUE(PS_MODEL_SID, IFP_MODEL_SID, ITEM_MASTER_SID)
--        END
--  END

--GO

-------------AGN-81
IF EXISTS (
		SELECT NAME
		FROM SYS.TABLES
		WHERE NAME = 'PS_DETAILS'
		)
BEGIN
	IF EXISTS (
			SELECT 1
			FROM SYS.KEY_CONSTRAINTS
			WHERE TYPE_DESC = 'UNIQUE_CONSTRAINT'
				AND PARENT_OBJECT_ID = OBJECT_ID('PS_DETAILS')
				AND NAME = 'UQ_PS_DETAILS_PS_MODEL_SID_IFP_MODEL_SID_ITEM_MASTER_SID'
			)
	BEGIN
		ALTER TABLE PS_DETAILS

		DROP CONSTRAINT UQ_PS_DETAILS_PS_MODEL_SID_IFP_MODEL_SID_ITEM_MASTER_SID
	END
END
GO

IF EXISTS (
		SELECT NAME
		FROM SYS.TABLES
		WHERE NAME = 'PS_DETAILS'
		)
BEGIN
	IF NOT EXISTS (
			SELECT 1
			FROM SYS.KEY_CONSTRAINTS
			WHERE TYPE_DESC = 'UNIQUE_CONSTRAINT'
				AND PARENT_OBJECT_ID = OBJECT_ID('PS_DETAILS')
				AND NAME = 'UQ_PS_DETAILS_PS_MODEL_SID_IFP_MODEL_SID_ITEM_MASTER_SID_PPA_INDEX_NO'
			)
	BEGIN
		ALTER TABLE PS_DETAILS ADD CONSTRAINT UQ_PS_DETAILS_PS_MODEL_SID_IFP_MODEL_SID_ITEM_MASTER_SID_PPA_INDEX_NO UNIQUE (
			PS_MODEL_SID
			,IFP_MODEL_SID
			,ITEM_MASTER_SID
			,PPA_INDEX_NO
			)
	END
END
GO
---------------------

-------------------------------------AGN-429-----------------------


IF EXISTS (
		SELECT *
		FROM SYS.stats
		WHERE NAME = 'PS_NO'
			AND Object_name(object_id) = 'PS_MODEL'
		)
BEGIN
	DROP STATISTICS dbo.PS_MODEL.PS_NO
END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_MODEL'
                  AND COLUMN_NAME = 'PS_NO'
                  AND IS_NULLABLE = 'YES')
  BEGIN
	  UPDATE PS_MODEL SET PS_NO = 0 WHERE PS_NO IS NULL

      ALTER TABLE PS_MODEL
        ALTER COLUMN PS_NO VARCHAR(50) NOT NULL
  END 
GO

IF EXISTS (
		SELECT *
		FROM SYS.stats
		WHERE NAME = 'PS_NAME'
			AND Object_name(object_id) = 'PS_MODEL'
		)
BEGIN
	DROP STATISTICS dbo.PS_MODEL.PS_NAME
END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_MODEL'
                  AND COLUMN_NAME = 'PS_NAME'
                  AND IS_NULLABLE = 'YES')
  BEGIN
	  UPDATE PS_MODEL SET PS_NAME = 0 WHERE PS_NAME IS NULL

      ALTER TABLE PS_MODEL
        ALTER COLUMN PS_NAME VARCHAR(50) NOT NULL
  END 
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'PS_DETAILS'--TABLE NAME
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

------------------------------------------HIST_PS_DETAILS-------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_PS_DETAILS]
        (
           PS_DETAILS_SID                INT NOT NULL,
           PS_MODEL_SID                  INT NOT NULL,
           IFP_MODEL_SID                 INT NULL,
           ITEM_MASTER_SID               INT NOT NULL,
           CONTRACT_PRICE                NUMERIC(22, 6) NULL,
           ITEM_PRICING_QUALIFIER_SID    INT NULL,
           CONTRACT_PRICE_START_DATE     DATETIME NOT NULL,
           CONTRACT_PRICE_END_DATE       DATETIME NULL,
           ITEM_PS_ATTACHED_STATUS       INT NULL,
           ITEM_PS_ATTACHED_DATE         DATETIME,
           BASE_PRICE                    NUMERIC(22, 6) NULL,
           PRICE_TOLERANCE               NUMERIC(22, 6) NULL,
           PRICE_TOLERANCE_TYPE          INT NULL,
           PRICE_TOLERANCE_FREQUENCY     INT NULL,
           PRICE_TOLERANCE_INTERVAL      INT NULL,
           PRICE_PROTECTION_START_DATE   DATETIME NULL,
           PRICE_PROTECTION_END_DATE     DATETIME NULL,
           PRICE_REVISION                NUMERIC(22, 6) NULL,
           REVISION_DATE                 DATETIME NULL,
           SUGGESTED_PRICE               NUMERIC(22, 6) NULL,
           PRICE                         NUMERIC(22, 6) NOT NULL,
           [STATUS]                      INT NULL,
           [BRAND_MASTER_SID]            INT NULL,
           [NEP]                         NUMERIC(22, 6) NULL,
           [PRICE_PROTECTION_STATUS]     INT NULL,
           [PRICE_PROTECTION_PRICE_TYPE] INT NULL,
           [NEP_FORMULA]                 VARCHAR(100) NULL,
           [MAX_INCREMENTAL_CHANGE]      NUMERIC(22, 6) NULL,
           [RESET_ELIGIBLE]              VARCHAR(5) NULL,
           [RESET_TYPE]                  INT NULL,
           [RESET_DATE]                  DATETIME NULL,
           [RESET_INTERVAL]              INT NULL,
           [RESET_FREQUENCY]             INT NULL,
           [NET_PRICE_TYPE]              VARCHAR(5) NULL,
           [NET_PRICE_TYPE_FORMULA]      VARCHAR(100) NULL,
           INBOUND_STATUS                CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS            BIT NOT NULL,
           BATCH_ID                      VARCHAR(50) NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [CREATED_BY]                  INT NOT NULL,
           [CREATED_DATE]                DATETIME NOT NULL,
           [MODIFIED_BY]                 INT NOT NULL,
           [MODIFIED_DATE]               DATETIME NOT NULL,
           ACTION_FLAG                   CHAR(1) NOT NULL,
           ACTION_DATE                   DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'STATUS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD STATUS INT
  END

GO

-----------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD BASE_PRICE_TYPE VARCHAR(50)
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_ENTRY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD BASE_PRICE_ENTRY NUMERIC(22, 6)
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD BASE_PRICE_DATE DATETIME
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD NET_BASE_PRICE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_DDLB'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD BASE_PRICE_DDLB INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD NET_BASE_PRICE_FORMULA_ID INT
  END

GO

------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'SUBSEQUENT_PERIOD_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD SUBSEQUENT_PERIOD_PRICE_TYPE INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD NET_SUBSEQUENT_PERIOD_PRICE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD NET_SUBSEQUENT_PRICE_FORMULA_ID INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD RESET_PRICE_TYPE INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD NET_RESET_PRICE_TYPE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD NET_RESET_PRICE_FORMULA_ID INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'BRAND_MASTER_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD BRAND_MASTER_SID INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'NEP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD NEP NUMERIC(22, 6)
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_INTERVAL'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_DETAILS.PRICE_TOLERANCE_INTERVAL
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                  AND COLUMN_NAME = 'PRICE_TOLERANCE_INTERVAL'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_INTERVAL INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_STATUS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD PRICE_PROTECTION_STATUS INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD PRICE_PROTECTION_PRICE_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'NEP_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD NEP_FORMULA VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'MAX_INCREMENTAL_CHANGE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD MAX_INCREMENTAL_CHANGE NUMERIC(22, 6)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_ELIGIBLE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD RESET_ELIGIBLE DATETIME
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD RESET_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD RESET_DATE DATETIME
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_INTERVAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD RESET_INTERVAL INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_FREQUENCY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD RESET_FREQUENCY INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD NET_PRICE_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ADD NET_PRICE_TYPE_FORMULA VARCHAR(100)
  END

GO

--------------------------DATATYPE CHANGE STARTS-------------------------
IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'RESET_ELIGIBLE'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_DETAILS.RESET_ELIGIBLE
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'NET_PRICE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_DETAILS.NET_PRICE_TYPE
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_ELIGIBLE'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN [RESET_ELIGIBLE] VARCHAR(5)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN NET_PRICE_TYPE VARCHAR(5)
  END

GO

------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_BASE_PRICE'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_DETAILS.NET_BASE_PRICE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_BASE_PRICE'
                  AND TABLE_NAME = 'HIST_PS_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN NET_BASE_PRICE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_DETAILS.NET_SUBSEQUENT_PERIOD_PRICE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                  AND TABLE_NAME = 'HIST_PS_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN NET_SUBSEQUENT_PERIOD_PRICE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_RESET_PRICE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_DETAILS.NET_RESET_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_RESET_PRICE_TYPE'
                  AND TABLE_NAME = 'HIST_PS_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN NET_RESET_PRICE_TYPE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'BASE_PRICE_ENTRY'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_DETAILS.BASE_PRICE_ENTRY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'BASE_PRICE_ENTRY'
                  AND TABLE_NAME = 'HIST_PS_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN BASE_PRICE_ENTRY NUMERIC(22, 6)
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_FREQUENCY'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_DETAILS.PRICE_TOLERANCE_FREQUENCY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'PRICE_TOLERANCE_FREQUENCY'
                  AND TABLE_NAME = 'HIST_PS_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_FREQUENCY INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_DETAILS.PRICE_TOLERANCE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'PRICE_TOLERANCE_TYPE'
                  AND TABLE_NAME = 'HIST_PS_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_TYPE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NEP_FORMULA')
  BEGIN
      DROP STATISTICS HIST_PS_DETAILS.NEP_FORMULA
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                  AND COLUMN_NAME = 'NEP_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN NEP_FORMULA INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NET_PRICE_TYPE_FORMULA')
  BEGIN
      DROP STATISTICS HIST_PS_DETAILS.NET_PRICE_TYPE_FORMULA
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                  AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN NET_PRICE_TYPE_FORMULA INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'BASE_PRICE_TYPE')
  BEGIN
      DROP STATISTICS HIST_PS_DETAILS.BASE_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_DETAILS'
                  AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_DETAILS
        ALTER COLUMN BASE_PRICE_TYPE INT NULL
  END

GO

------------------
IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'HIST_PS_DETAILS'
			AND COLUMN_NAME = 'PPA_INDEX_NO'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
		ALTER TABLE HIST_PS_DETAILS ADD PPA_INDEX_NO INT

END
GO
------------ADDED COLUMN ADD_COPY_INDICATOR -----------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_PS_DETAILS' AND COLUMN_NAME  = 'ADD_COPY_INDICATOR' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_PS_DETAILS
        ADD ADD_COPY_INDICATOR CHAR(100)
  END
GO
---------------------------DATATYPE CHANGE ENDS-------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_PS_DETAILS')
                      AND NAME = 'DF_HIST_PS_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_PS_DETAILS]
        ADD CONSTRAINT [DF_HIST_PS_DETAILS_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

IF EXISTS (
		SELECT *
		FROM SYS.stats
		WHERE NAME = 'PS_NO'
			AND Object_name(object_id) = 'HIST_PS_MODEL'
		)
BEGIN
	DROP STATISTICS dbo.HIST_PS_MODEL.PS_NO
END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_MODEL'
                  AND COLUMN_NAME = 'PS_NO'
                  AND IS_NULLABLE = 'YES')
  BEGIN
	  UPDATE HIST_PS_MODEL SET PS_NO = 0 WHERE PS_NO IS NULL

      ALTER TABLE HIST_PS_MODEL
        ALTER COLUMN PS_NO VARCHAR(50) NOT NULL
  END 
GO

IF EXISTS (
		SELECT *
		FROM SYS.stats
		WHERE NAME = 'PS_NAME'
			AND Object_name(object_id) = 'HIST_PS_MODEL'
		)
BEGIN
	DROP STATISTICS dbo.HIST_PS_MODEL.PS_NAME
END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_MODEL'
                  AND COLUMN_NAME = 'PS_NAME'
                  AND IS_NULLABLE = 'YES')
  BEGIN
	  UPDATE HIST_PS_MODEL SET PS_NAME = 0 WHERE PS_NAME IS NULL

      ALTER TABLE HIST_PS_MODEL
        ALTER COLUMN PS_NAME VARCHAR(50) NOT NULL
  END 
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_PS_DETAILS'--TABLE NAME
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

-------------------------------------------PS_DETAILS TRIGGER--------------------------------------
--------------------------------------UPDATE TRIGGER STARTS------------------- 
---------------------------TRGGER_PS_DETAILS-----------------------------------------------------------
-----TRG_PS_DETAILS_UPD---
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_PS_DETAILS_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_DETAILS_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_DETAILS_UPD]
ON [DBO].[PS_DETAILS]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON 
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_PS_DETAILS
                    (PS_DETAILS_SID,
                     PS_MODEL_SID,
                     IFP_MODEL_SID,
                     ITEM_MASTER_SID,
                     CONTRACT_PRICE,
                     ITEM_PRICING_QUALIFIER_SID,
                     CONTRACT_PRICE_START_DATE,
                     CONTRACT_PRICE_END_DATE,
                     ITEM_PS_ATTACHED_STATUS,
                     ITEM_PS_ATTACHED_DATE,
                     BASE_PRICE,
                     PRICE_TOLERANCE,
                     PRICE_TOLERANCE_TYPE,
                     PRICE_TOLERANCE_FREQUENCY,
                     PRICE_TOLERANCE_INTERVAL,
                     PRICE_PROTECTION_START_DATE,
                     PRICE_PROTECTION_END_DATE,
                     PRICE_REVISION,
                     REVISION_DATE,
                     SUGGESTED_PRICE,
                     PRICE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     STATUS,
                     BRAND_MASTER_SID,
                     NEP,
                     PRICE_PROTECTION_STATUS,
                     PRICE_PROTECTION_PRICE_TYPE,
                     NEP_FORMULA,
                     MAX_INCREMENTAL_CHANGE,
                     RESET_ELIGIBLE,
                     RESET_TYPE,
                     RESET_DATE,
                     RESET_INTERVAL,
                     RESET_FREQUENCY,
                     NET_PRICE_TYPE,
                     NET_PRICE_TYPE_FORMULA,
                     BASE_PRICE_TYPE,
                     BASE_PRICE_ENTRY,
                     BASE_PRICE_DATE,
                     NET_BASE_PRICE,
                     BASE_PRICE_DDLB,
                     NET_BASE_PRICE_FORMULA_ID,
                     NET_SUBSEQUENT_PERIOD_PRICE,
                     NET_SUBSEQUENT_PRICE_FORMULA_ID,
                     RESET_PRICE_TYPE,
                     NET_RESET_PRICE_TYPE,
                     NET_RESET_PRICE_FORMULA_ID,
                     SUBSEQUENT_PERIOD_PRICE_TYPE,
					 PPA_INDEX_NO,
					 ADD_COPY_INDICATOR,
                     ACTION_FLAG)
        SELECT PS_DETAILS_SID,
               PS_MODEL_SID,
               IFP_MODEL_SID,
               ITEM_MASTER_SID,
               CONTRACT_PRICE,
               ITEM_PRICING_QUALIFIER_SID,
               CONTRACT_PRICE_START_DATE,
               CONTRACT_PRICE_END_DATE,
               ITEM_PS_ATTACHED_STATUS,
               ITEM_PS_ATTACHED_DATE,
               BASE_PRICE,
               PRICE_TOLERANCE,
               PRICE_TOLERANCE_TYPE,
               PRICE_TOLERANCE_FREQUENCY,
               PRICE_TOLERANCE_INTERVAL,
               PRICE_PROTECTION_START_DATE,
               PRICE_PROTECTION_END_DATE,
               PRICE_REVISION,
               REVISION_DATE,
               SUGGESTED_PRICE,
               PRICE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               STATUS,
               BRAND_MASTER_SID,
               NEP,
               PRICE_PROTECTION_STATUS,
               PRICE_PROTECTION_PRICE_TYPE,
               NEP_FORMULA,
               MAX_INCREMENTAL_CHANGE,
               RESET_ELIGIBLE,
               RESET_TYPE,
               RESET_DATE,
               RESET_INTERVAL,
               RESET_FREQUENCY,
               NET_PRICE_TYPE,
               NET_PRICE_TYPE_FORMULA,
               BASE_PRICE_TYPE,
               BASE_PRICE_ENTRY,
               BASE_PRICE_DATE,
               NET_BASE_PRICE,
               BASE_PRICE_DDLB,
               NET_BASE_PRICE_FORMULA_ID,
               NET_SUBSEQUENT_PERIOD_PRICE,
               NET_SUBSEQUENT_PRICE_FORMULA_ID,
               RESET_PRICE_TYPE,
               NET_RESET_PRICE_TYPE,
               NET_RESET_PRICE_FORMULA_ID,
               SUBSEQUENT_PERIOD_PRICE_TYPE,
			   PPA_INDEX_NO,
			   ADD_COPY_INDICATOR,
               'C'
        FROM   INSERTED
  END

GO

-----TRG_PS_DETAILS_INS---
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_PS_DETAILS_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_DETAILS_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_DETAILS_INS]
ON [DBO].[PS_DETAILS]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON 
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_PS_DETAILS
                    (PS_DETAILS_SID,
                     PS_MODEL_SID,
                     IFP_MODEL_SID,
                     ITEM_MASTER_SID,
                     CONTRACT_PRICE,
                     ITEM_PRICING_QUALIFIER_SID,
                     CONTRACT_PRICE_START_DATE,
                     CONTRACT_PRICE_END_DATE,
                     ITEM_PS_ATTACHED_STATUS,
                     ITEM_PS_ATTACHED_DATE,
                     BASE_PRICE,
                     PRICE_TOLERANCE,
                     PRICE_TOLERANCE_TYPE,
                     PRICE_TOLERANCE_FREQUENCY,
                     PRICE_TOLERANCE_INTERVAL,
                     PRICE_PROTECTION_START_DATE,
                     PRICE_PROTECTION_END_DATE,
                     PRICE_REVISION,
                     REVISION_DATE,
                     SUGGESTED_PRICE,
                     PRICE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     STATUS,
                     BRAND_MASTER_SID,
                     NEP,
                     PRICE_PROTECTION_STATUS,
                     PRICE_PROTECTION_PRICE_TYPE,
                     NEP_FORMULA,
                     MAX_INCREMENTAL_CHANGE,
                     RESET_ELIGIBLE,
                     RESET_TYPE,
                     RESET_DATE,
                     RESET_INTERVAL,
                     RESET_FREQUENCY,
                     NET_PRICE_TYPE,
                     NET_PRICE_TYPE_FORMULA,
                     BASE_PRICE_TYPE,
                     BASE_PRICE_ENTRY,
                     BASE_PRICE_DATE,
                     NET_BASE_PRICE,
                     BASE_PRICE_DDLB,
                     NET_BASE_PRICE_FORMULA_ID,
                     NET_SUBSEQUENT_PERIOD_PRICE,
                     NET_SUBSEQUENT_PRICE_FORMULA_ID,
                     RESET_PRICE_TYPE,
                     NET_RESET_PRICE_TYPE,
                     NET_RESET_PRICE_FORMULA_ID,
                     SUBSEQUENT_PERIOD_PRICE_TYPE,
					 PPA_INDEX_NO,
					 ADD_COPY_INDICATOR,
                     ACTION_FLAG)
        SELECT PS_DETAILS_SID,
               PS_MODEL_SID,
               IFP_MODEL_SID,
               ITEM_MASTER_SID,
               CONTRACT_PRICE,
               ITEM_PRICING_QUALIFIER_SID,
               CONTRACT_PRICE_START_DATE,
               CONTRACT_PRICE_END_DATE,
               ITEM_PS_ATTACHED_STATUS,
               ITEM_PS_ATTACHED_DATE,
               BASE_PRICE,
               PRICE_TOLERANCE,
               PRICE_TOLERANCE_TYPE,
               PRICE_TOLERANCE_FREQUENCY,
               PRICE_TOLERANCE_INTERVAL,
               PRICE_PROTECTION_START_DATE,
               PRICE_PROTECTION_END_DATE,
               PRICE_REVISION,
               REVISION_DATE,
               SUGGESTED_PRICE,
               PRICE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               STATUS,
               BRAND_MASTER_SID,
               NEP,
               PRICE_PROTECTION_STATUS,
               PRICE_PROTECTION_PRICE_TYPE,
               NEP_FORMULA,
               MAX_INCREMENTAL_CHANGE,
               RESET_ELIGIBLE,
               RESET_TYPE,
               RESET_DATE,
               RESET_INTERVAL,
               RESET_FREQUENCY,
               NET_PRICE_TYPE,
               NET_PRICE_TYPE_FORMULA,
               BASE_PRICE_TYPE,
               BASE_PRICE_ENTRY,
               BASE_PRICE_DATE,
               NET_BASE_PRICE,
               BASE_PRICE_DDLB,
               NET_BASE_PRICE_FORMULA_ID,
               NET_SUBSEQUENT_PERIOD_PRICE,
               NET_SUBSEQUENT_PRICE_FORMULA_ID,
               RESET_PRICE_TYPE,
               NET_RESET_PRICE_TYPE,
               NET_RESET_PRICE_FORMULA_ID,
               SUBSEQUENT_PERIOD_PRICE_TYPE,
			   PPA_INDEX_NO,
			   ADD_COPY_INDICATOR,
               'A'
        FROM   INSERTED
  END

GO

-----TRG_PS_DETAILS_DEL---
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_PS_DETAILS_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_DETAILS_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_DETAILS_DEL]
ON [DBO].[PS_DETAILS]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON 
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_PS_DETAILS
                    (PS_DETAILS_SID,
                     PS_MODEL_SID,
                     IFP_MODEL_SID,
                     ITEM_MASTER_SID,
                     CONTRACT_PRICE,
                     ITEM_PRICING_QUALIFIER_SID,
                     CONTRACT_PRICE_START_DATE,
                     CONTRACT_PRICE_END_DATE,
                     ITEM_PS_ATTACHED_STATUS,
                     ITEM_PS_ATTACHED_DATE,
                     BASE_PRICE,
                     PRICE_TOLERANCE,
                     PRICE_TOLERANCE_TYPE,
                     PRICE_TOLERANCE_FREQUENCY,
                     PRICE_TOLERANCE_INTERVAL,
                     PRICE_PROTECTION_START_DATE,
                     PRICE_PROTECTION_END_DATE,
                     PRICE_REVISION,
                     REVISION_DATE,
                     SUGGESTED_PRICE,
                     PRICE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     STATUS,
                     BRAND_MASTER_SID,
                     NEP,
                     PRICE_PROTECTION_STATUS,
                     PRICE_PROTECTION_PRICE_TYPE,
                     NEP_FORMULA,
                     MAX_INCREMENTAL_CHANGE,
                     RESET_ELIGIBLE,
                     RESET_TYPE,
                     RESET_DATE,
                     RESET_INTERVAL,
                     RESET_FREQUENCY,
                     NET_PRICE_TYPE,
                     NET_PRICE_TYPE_FORMULA,
                     BASE_PRICE_TYPE,
                     BASE_PRICE_ENTRY,
                     BASE_PRICE_DATE,
                     NET_BASE_PRICE,
                     BASE_PRICE_DDLB,
                     NET_BASE_PRICE_FORMULA_ID,
                     NET_SUBSEQUENT_PERIOD_PRICE,
                     NET_SUBSEQUENT_PRICE_FORMULA_ID,
                     RESET_PRICE_TYPE,
                     NET_RESET_PRICE_TYPE,
                     NET_RESET_PRICE_FORMULA_ID,
                     SUBSEQUENT_PERIOD_PRICE_TYPE,
					 PPA_INDEX_NO,
					 ADD_COPY_INDICATOR,
                     ACTION_FLAG)
        SELECT PS_DETAILS_SID,
               PS_MODEL_SID,
               IFP_MODEL_SID,
               ITEM_MASTER_SID,
               CONTRACT_PRICE,
               ITEM_PRICING_QUALIFIER_SID,
               CONTRACT_PRICE_START_DATE,
               CONTRACT_PRICE_END_DATE,
               ITEM_PS_ATTACHED_STATUS,
               ITEM_PS_ATTACHED_DATE,
               BASE_PRICE,
               PRICE_TOLERANCE,
               PRICE_TOLERANCE_TYPE,
               PRICE_TOLERANCE_FREQUENCY,
               PRICE_TOLERANCE_INTERVAL,
               PRICE_PROTECTION_START_DATE,
               PRICE_PROTECTION_END_DATE,
               PRICE_REVISION,
               REVISION_DATE,
               SUGGESTED_PRICE,
               PRICE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               STATUS,
               BRAND_MASTER_SID,
               NEP,
               PRICE_PROTECTION_STATUS,
               PRICE_PROTECTION_PRICE_TYPE,
               NEP_FORMULA,
               MAX_INCREMENTAL_CHANGE,
               RESET_ELIGIBLE,
               RESET_TYPE,
               RESET_DATE,
               RESET_INTERVAL,
               RESET_FREQUENCY,
               NET_PRICE_TYPE,
               NET_PRICE_TYPE_FORMULA,
               BASE_PRICE_TYPE,
               BASE_PRICE_ENTRY,
               BASE_PRICE_DATE,
               NET_BASE_PRICE,
               BASE_PRICE_DDLB,
               NET_BASE_PRICE_FORMULA_ID,
               NET_SUBSEQUENT_PERIOD_PRICE,
               NET_SUBSEQUENT_PRICE_FORMULA_ID,
               RESET_PRICE_TYPE,
               NET_RESET_PRICE_TYPE,
               NET_RESET_PRICE_FORMULA_ID,
               SUBSEQUENT_PERIOD_PRICE_TYPE,
			   PPA_INDEX_NO,
			   ADD_COPY_INDICATOR,
               'D'
        FROM   DELETED
  END

GO

--------------------------------------DELETE TRIGGER ENDS-------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[IMTD_PS_DETAILS]
        (
           IMTD_PS_DETAILS_SID            INT IDENTITY(1, 1) NOT NULL,
           PS_MODEL_SID                   INT,
           PS_DETAILS_SID                 INT,
           CONTRACT_MASTER_SID            INT,
           CFP_MODEL_SID                  INT,
           ITEM_MASTER_SID                INT,
           ITEM_ID                        VARCHAR(38),
           ITEM_NO                        VARCHAR(50),
           ITEM_NAME                      VARCHAR(100),
           IFP_MODEL_SID                  INT,
           IFP_NO                         VARCHAR(50),
           PS_DETAILS_PRICE               NUMERIC(22, 6),
           PS_DETAILS_PRICETYPE           VARCHAR(50),
           PS_DETAILS_PRICE_TOLERANCE     NUMERIC(22, 6),
           PS_DTLS_PRICE_TOLERANCE_TYPE   VARCHAR(50),
           PS_DTLS_PRICE_TOLERANCE_INTRVL INT,
           PS_DTLS_PRICE_TOLERANCE_FREQ   VARCHAR(50),
           PS_DETAILS_PRIC_PRTCN_STDATE   DATETIME,
           PS_DETAILS_PRIC_PRTCN_EDDATE   DATETIME,
           PS_DETAILS_BASE_PRICE          NUMERIC(22, 6),
           PS_DETAILS_CONTRACT_PRICE      NUMERIC(22, 6),
           PS_DTLS_CONT_PRICE_STARTDATE   DATETIME,
           PS_DTLS_CONT_PRICE_ENDDATE     DATETIME,
           PS_DETAILS_REVISION_DATE       DATETIME,
           PS_DETAILS_ATTACHED_DATE       DATETIME,
           PS_DETAILS_ATTACHED_STATUS     INT,
           PS_DETAILS_PRICE_REVISION      NUMERIC(22, 6),
           PS_DETAILS_PRICE_PLAN_ID       VARCHAR(38),
           PS_DETAILS_SUGGESTED_PRICE     NUMERIC(22, 6),
           [STATUS]                       INT NULL,
           [BRAND_MASTER_SID]             INT NULL,
           [NEP]                          NUMERIC(22, 6) NULL,
           [PRICE_PROTECTION_STATUS]      INT NULL,
           [PRICE_PROTECTION_PRICE_TYPE]  INT NULL,
           [NEP_FORMULA]                  VARCHAR(100) NULL,
           [MAX_INCREMENTAL_CHANGE]       NUMERIC(22, 6) NULL,
           [RESET_ELIGIBLE]               VARCHAR(5) NULL,
           [RESET_TYPE]                   INT NULL,
           [RESET_DATE]                   DATETIME NULL,
           [RESET_INTERVAL]               INT NULL,
           [RESET_FREQUENCY]              INT NULL,
           [NET_PRICE_TYPE]               VARCHAR(5) NULL,
           [NET_PRICE_TYPE_FORMULA]       VARCHAR(100) NULL,
           PS_DETAILS_CREATED_DATE        DATETIME,
           PS_DETAILS_CREATED_BY          VARCHAR(50),
           PS_DETAILS_MODIFIED_DATE       DATETIME,
           PS_DETAILS_MODIFIED_BY         VARCHAR(50),
           USERS_SID                      VARCHAR(50),
           SESSION_ID                     VARCHAR(100),
           CHECK_RECORD                   BIT,
           IMTD_CREATED_DATE              DATETIME,
           OPERATION                      VARCHAR(100),
           [CREATED_BY]                   INT NOT NULL,
           [CREATED_DATE]                 DATETIME NOT NULL,
           [MODIFIED_BY]                  INT NOT NULL,
           [MODIFIED_DATE]                DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'STATUS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD STATUS INT
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD BASE_PRICE_TYPE VARCHAR(50)
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_ENTRY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD BASE_PRICE_ENTRY NUMERIC(22, 6)
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD BASE_PRICE_DATE DATETIME
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_DDLB'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD BASE_PRICE_DDLB INT
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_BASE_PRICE BIT
  END

GO

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_BASE_PRICE_FORMULA_ID INT
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE_FORMULA_NAME'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_BASE_PRICE_FORMULA_NAME VARCHAR(50)
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE_FORMULA_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_BASE_PRICE_FORMULA_NO VARCHAR(50)
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'SUBSEQUENT_PERIOD_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD SUBSEQUENT_PERIOD_PRICE_TYPE INT
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_SUBSEQUENT_PERIOD_PRICE BIT
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_SUBSEQUENT_PRICE_FORMULA_ID INT
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PRICE_FORMULA_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_SUBSEQUENT_PRICE_FORMULA_NO VARCHAR(50)
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PRICE_FORMULA_NAME'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_SUBSEQUENT_PRICE_FORMULA_NAME VARCHAR(50)
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD RESET_PRICE_TYPE INT
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_RESET_PRICE_TYPE BIT
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_RESET_PRICE_FORMULA_ID INT
  END

GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_FORMULA_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_RESET_PRICE_FORMULA_NO VARCHAR(50)
  END

GO
---------------------------Adding PS_ID------------------------------------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'IMTD_PS_DETAILS' AND COLUMN_NAME  = 'PS_ID' AND TABLE_SCHEMA = 'DBO')
    BEGIN
               
      ALTER TABLE IMTD_PS_DETAILS
        ADD PS_ID VARCHAR(100)
  END
GO

---------------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_FORMULA_NAME'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_RESET_PRICE_FORMULA_NAME VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'BRAND_MASTER_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD BRAND_MASTER_SID INT
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NEP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NEP NUMERIC(22, 6)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_STATUS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD PRICE_PROTECTION_STATUS INT
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD PRICE_PROTECTION_PRICE_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NEP_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NEP_FORMULA VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'MAX_INCREMENTAL_CHANGE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD MAX_INCREMENTAL_CHANGE NUMERIC(22, 6)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_ELIGIBLE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD RESET_ELIGIBLE DATETIME
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD RESET_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD RESET_DATE DATETIME
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_INTERVAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD RESET_INTERVAL INT
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_FREQUENCY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD RESET_FREQUENCY INT
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_PRICE_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ADD NET_PRICE_TYPE_FORMULA VARCHAR(100)
  END

GO

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_IMTD_PS_DETAILS_IMTD_PS_DETAILS_SID'
                     AND TABLE_NAME = 'IMTD_PS_DETAILS')
  BEGIN
      ALTER TABLE [DBO].[IMTD_PS_DETAILS]
        ADD CONSTRAINT PK_IMTD_PS_DETAILS_IMTD_PS_DETAILS_SID PRIMARY KEY(IMTD_PS_DETAILS_SID)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.IMTD_PS_DETAILS')
                      AND NAME = 'DF_IMTD_PS_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[IMTD_PS_DETAILS]
        ADD CONSTRAINT [DF_IMTD_PS_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.IMTD_PS_DETAILS')
                      AND NAME = 'DF_IMTD_PS_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[IMTD_PS_DETAILS]
        ADD CONSTRAINT [DF_IMTD_PS_DETAILS_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.IMTD_PS_DETAILS')
                      AND NAME = 'DF_IMTD_PS_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[IMTD_PS_DETAILS]
        ADD CONSTRAINT [DF_IMTD_PS_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO




-------------------------------DATATYPE CHANGE STARTS----------------------
IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'RESET_ELIGIBLE'
                  AND OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.IMTD_PS_DETAILS.RESET_ELIGIBLE
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'NET_PRICE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.IMTD_PS_DETAILS.NET_PRICE_TYPE
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'RESET_ELIGIBLE'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN [RESET_ELIGIBLE] VARCHAR(5)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN NET_PRICE_TYPE VARCHAR(5)
  END

GO

------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_BASE_PRICE'
                  AND OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.IMTD_PS_DETAILS.NET_BASE_PRICE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_BASE_PRICE'
                  AND TABLE_NAME = 'IMTD_PS_DETAILS')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN NET_BASE_PRICE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                  AND OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.IMTD_PS_DETAILS.NET_SUBSEQUENT_PERIOD_PRICE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                  AND TABLE_NAME = 'IMTD_PS_DETAILS')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN NET_SUBSEQUENT_PERIOD_PRICE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_RESET_PRICE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.IMTD_PS_DETAILS.NET_RESET_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_RESET_PRICE_TYPE'
                  AND TABLE_NAME = 'IMTD_PS_DETAILS')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN NET_RESET_PRICE_TYPE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'BASE_PRICE_ENTRY'
                  AND OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS')
  BEGIN
      DROP STATISTICS DBO.IMTD_PS_DETAILS.BASE_PRICE_ENTRY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'BASE_PRICE_ENTRY'
                  AND TABLE_NAME = 'IMTD_PS_DETAILS')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN BASE_PRICE_ENTRY NUMERIC(22, 6)
  END

GO

-----------------------------
-------------------------------DATATYPE CHANGE ENDS----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.IMTD_PS_DETAILS')
                      AND NAME = 'DF_IMTD_PS_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[IMTD_PS_DETAILS]
        ADD CONSTRAINT [DF_IMTD_PS_DETAILS_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

----------------------------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'PS_DETAILS_PRICETYPE')
  BEGIN
      DROP STATISTICS IMTD_PS_DETAILS.PS_DETAILS_PRICETYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                  AND COLUMN_NAME = 'PS_DETAILS_PRICETYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN PS_DETAILS_PRICETYPE INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'PS_DTLS_PRICE_TOLERANCE_TYPE')
  BEGIN
      DROP STATISTICS IMTD_PS_DETAILS.PS_DTLS_PRICE_TOLERANCE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                  AND COLUMN_NAME = 'PS_DTLS_PRICE_TOLERANCE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN PS_DTLS_PRICE_TOLERANCE_TYPE INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'PS_DTLS_PRICE_TOLERANCE_FREQ')
  BEGIN
      DROP STATISTICS IMTD_PS_DETAILS.PS_DTLS_PRICE_TOLERANCE_FREQ
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                  AND COLUMN_NAME = 'PS_DTLS_PRICE_TOLERANCE_FREQ'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN PS_DTLS_PRICE_TOLERANCE_FREQ INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NEP_FORMULA')
  BEGIN
      DROP STATISTICS IMTD_PS_DETAILS.NEP_FORMULA
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                  AND COLUMN_NAME = 'NEP_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN NEP_FORMULA INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NET_PRICE_TYPE_FORMULA')
  BEGIN
      DROP STATISTICS IMTD_PS_DETAILS.NET_PRICE_TYPE_FORMULA
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                  AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN NET_PRICE_TYPE_FORMULA INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'PS_DETAILS_CREATED_BY')
  BEGIN
      DROP STATISTICS IMTD_PS_DETAILS.PS_DETAILS_CREATED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                  AND COLUMN_NAME = 'PS_DETAILS_CREATED_BY'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN PS_DETAILS_CREATED_BY INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'PS_DETAILS_MODIFIED_BY')
  BEGIN
      DROP STATISTICS IMTD_PS_DETAILS.PS_DETAILS_MODIFIED_BY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                  AND COLUMN_NAME = 'PS_DETAILS_MODIFIED_BY'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN PS_DETAILS_MODIFIED_BY INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'USERS_SID')
  BEGIN
      DROP STATISTICS IMTD_PS_DETAILS.USERS_SID
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                  AND COLUMN_NAME = 'USERS_SID'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN USERS_SID INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'IMTD_PS_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'BASE_PRICE_TYPE')
  BEGIN
      DROP STATISTICS IMTD_PS_DETAILS.BASE_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'IMTD_PS_DETAILS'
                  AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IMTD_PS_DETAILS
        ALTER COLUMN BASE_PRICE_TYPE INT NULL
  END

GO
---------------AGN-81
IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'IMTD_PS_DETAILS'
			AND COLUMN_NAME = 'PPA_INDEX_NO'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	
		ALTER TABLE IMTD_PS_DETAILS ADD PPA_INDEX_NO INT

END
GO

-------------Default Constraint for PPA_INDEX_NO---------
IF NOT EXISTS (
		SELECT 'X'
		FROM SYS.DEFAULT_CONSTRAINTS
		WHERE PARENT_OBJECT_ID = OBJECT_ID('DBO.IMTD_PS_DETAILS')
			AND NAME = 'DF_IMTD_PS_DETAILS_PPA_INDEX_NO'
		)
BEGIN
	ALTER TABLE [DBO].[IMTD_PS_DETAILS] ADD CONSTRAINT [DF_IMTD_PS_DETAILS_PPA_INDEX_NO] DEFAULT(1)
	FOR PPA_INDEX_NO
END
GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'IMTD_PS_DETAILS'--TABLE NAME
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


