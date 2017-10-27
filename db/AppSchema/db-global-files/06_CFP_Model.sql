IF NOT EXISTS(SELECT 1
              FROM   SYS.TABLES
              WHERE  Object_name(OBJECT_ID) = 'CFP_MODEL'
                     AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE CFP_MODEL
        (
           CFP_MODEL_SID      INT NOT NULL IDENTITY(1, 1),
           CFP_ID             VARCHAR(50) NOT NULL,
           CFP_NO             VARCHAR(50) NOT NULL,
           CFP_NAME           VARCHAR(100) NOT NULL,
           CFP_TYPE           INT NULL,
           CFP_CATEGORY       INT NULL,
           CFP_DESIGNATION    VARCHAR(50) NULL,
           CFP_STATUS         INT NOT NULL,
           CFP_TRADE_CLASS    INT NULL,
           CFP_START_DATE     DATETIME NOT NULL,
           CFP_END_DATE       DATETIME NULL,
           PARENT_CFP_ID      INT NULL,
           PARENT_CFP_NAME    VARCHAR(50) NULL,
           INTERNAL_NOTES     VARCHAR(4000) NULL,
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

  IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CFP_MODEL'
                      AND COLUMN_NAME = 'SALES_INCLUSION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE CFP_MODEL
        ADD SALES_INCLUSION INT NULL
  END

GO 

---------PRIMARY KEY CONSTRAINTS--------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_MODEL'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_CFP_MODEL_CFP_MODEL_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE CFP_MODEL
        ADD CONSTRAINT PK_CFP_MODEL_CFP_MODEL_SID PRIMARY KEY(CFP_MODEL_SID)
  END

GO
-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT *
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_MODEL'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_MODEL_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE CFP_MODEL
        ADD CONSTRAINT DF_CFP_MODEL_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_MODEL'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_MODEL_CREATED_BY')
  BEGIN
      ALTER TABLE CFP_MODEL
        ADD CONSTRAINT DF_CFP_MODEL_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_MODEL'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_MODEL_CREATED_DATE')
  BEGIN
      ALTER TABLE CFP_MODEL
        ADD CONSTRAINT DF_CFP_MODEL_CREATED_DATE DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_MODEL'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_MODEL_MODIFIED_BY')
  BEGIN
      ALTER TABLE CFP_MODEL
        ADD CONSTRAINT DF_CFP_MODEL_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_MODEL'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_MODEL_MODIFIED_DATE')
  BEGIN
      ALTER TABLE CFP_MODEL
        ADD CONSTRAINT DF_CFP_MODEL_MODIFIED_DATE DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

-------------------------------UNIQUE_CONSTRAINT-------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'CFP_MODEL')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = Object_id('CFP_MODEL')
                            AND NAME = 'UQ_CFP_MODEL_CFP_ID')
        BEGIN
            ALTER TABLE CFP_MODEL
              ADD CONSTRAINT UQ_CFP_MODEL_CFP_ID UNIQUE(CFP_ID)
        END
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'CFP_MODEL'--TABLE NAME
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

---------------------------------HIST_CFP_MODEL---------------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.TABLES
              WHERE  Object_name(OBJECT_ID) = 'HIST_CFP_MODEL'
                     AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE HIST_CFP_MODEL
        (
           CFP_MODEL_SID      INT NOT NULL,
           CFP_ID             VARCHAR(50) NOT NULL,
           CFP_NO             VARCHAR(50) NOT NULL,
           CFP_NAME           VARCHAR(100) NOT NULL,
           CFP_TYPE           INT NULL,
           CFP_CATEGORY       INT NULL,
           CFP_DESIGNATION    VARCHAR(50) NULL,
           CFP_STATUS         INT NOT NULL,
           CFP_TRADE_CLASS    INT NULL,
           CFP_START_DATE     DATETIME NOT NULL,
           CFP_END_DATE       DATETIME NULL,
           PARENT_CFP_ID      INT NULL,
           PARENT_CFP_NAME    VARCHAR(50) NULL,
           INTERNAL_NOTES     VARCHAR(4000) NULL,
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

  IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_CFP_MODEL'
                      AND COLUMN_NAME = 'SALES_INCLUSION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_CFP_MODEL
        ADD SALES_INCLUSION INT NULL
  END

GO 

--IF NOT EXISTS(SELECT *
--			  FROM   SYS.KEY_CONSTRAINTS
--			  WHERE  OBJECT_NAME(PARENT_OBJECT_ID)='HIST_CFP_MODEL'
--			  AND SCHEMA_NAME(SCHEMA_ID)='DBO'
--			  AND NAME='PK_HIST_CFP_MODEL_CFP_MODEL_SID_VERSION_NO'
--			  AND TYPE='PK')
--  BEGIN
--	  ALTER TABLE HIST_CFP_MODEL
--		  ADD CONSTRAINT PK_HIST_CFP_MODEL_CFP_MODEL_SID_VERSION_NO PRIMARY KEY(CFP_MODEL_SID,VERSION_NO)
--  END
-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT *
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'HIST_CFP_MODEL'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_HIST_CFP_MODEL_ACTION_DATE')
  BEGIN
      ALTER TABLE HIST_CFP_MODEL
        ADD CONSTRAINT DF_HIST_CFP_MODEL_ACTION_DATE DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_CFP_MODEL'--TABLE NAME
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

----------------------------------CFP_MODEL TRIGGER--------------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_CFP_MODEL_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_CFP_MODEL_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_CFP_MODEL_UPD]
ON [dbo].[CFP_MODEL]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_CFP_MODEL
                    (CFP_MODEL_SID,
                     CFP_ID,
                     CFP_NO,
                     CFP_NAME,
                     CFP_TYPE,
                     CFP_CATEGORY,
                     CFP_DESIGNATION,
                     CFP_STATUS,
                     CFP_TRADE_CLASS,
                     CFP_START_DATE,
                     CFP_END_DATE,
                     PARENT_CFP_ID,
                     PARENT_CFP_NAME,
					 SALES_INCLUSION,
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
        SELECT CFP_MODEL_SID,
               CFP_ID,
               CFP_NO,
               CFP_NAME,
               CFP_TYPE,
               CFP_CATEGORY,
               CFP_DESIGNATION,
               CFP_STATUS,
               CFP_TRADE_CLASS,
               CFP_START_DATE,
               CFP_END_DATE,
               PARENT_CFP_ID,
               PARENT_CFP_NAME,
			   SALES_INCLUSION,
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
           WHERE  [Name] = N'TRG_CFP_MODEL_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_CFP_MODEL_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_CFP_MODEL_INS]
ON [dbo].[CFP_MODEL]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_CFP_MODEL
                    (CFP_MODEL_SID,
                     CFP_ID,
                     CFP_NO,
                     CFP_NAME,
                     CFP_TYPE,
                     CFP_CATEGORY,
                     CFP_DESIGNATION,
                     CFP_STATUS,
                     CFP_TRADE_CLASS,
                     CFP_START_DATE,
                     CFP_END_DATE,
                     PARENT_CFP_ID,
                     PARENT_CFP_NAME,
					 SALES_INCLUSION,
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
        SELECT CFP_MODEL_SID,
               CFP_ID,
               CFP_NO,
               CFP_NAME,
               CFP_TYPE,
               CFP_CATEGORY,
               CFP_DESIGNATION,
               CFP_STATUS,
               CFP_TRADE_CLASS,
               CFP_START_DATE,
               CFP_END_DATE,
               PARENT_CFP_ID,
               PARENT_CFP_NAME,
			   SALES_INCLUSION,
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
           WHERE  [Name] = N'TRG_CFP_MODEL_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_CFP_MODEL_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_CFP_MODEL_DEL]
ON [dbo].[CFP_MODEL]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_CFP_MODEL
                    (CFP_MODEL_SID,
                     CFP_ID,
                     CFP_NO,
                     CFP_NAME,
                     CFP_TYPE,
                     CFP_CATEGORY,
                     CFP_DESIGNATION,
                     CFP_STATUS,
                     CFP_TRADE_CLASS,
                     CFP_START_DATE,
                     CFP_END_DATE,
                     PARENT_CFP_ID,
                     PARENT_CFP_NAME,
					 SALES_INCLUSION,
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
        SELECT CFP_MODEL_SID,
               CFP_ID,
               CFP_NO,
               CFP_NAME,
               CFP_TYPE,
               CFP_CATEGORY,
               CFP_DESIGNATION,
               CFP_STATUS,
               CFP_TRADE_CLASS,
               CFP_START_DATE,
               CFP_END_DATE,
               PARENT_CFP_ID,
               PARENT_CFP_NAME,
			   SALES_INCLUSION,
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

------------------------------------CFP_DETAILS-----------------------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CFP_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE CFP_DETAILS
        (
           CFP_DETAILS_SID             INT IDENTITY(1, 1) NOT NULL,
           CFP_MODEL_SID               INT NOT NULL,
           COMPANY_MASTER_SID          INT NOT NULL,
           COMPANY_START_DATE          DATETIME NOT NULL,
           COMPANY_END_DATE            DATETIME NULL,
           COMPANY_CFP_ATTACHED_STATUS INT NULL,
           COMPANY_CFP_ATTACHED_DATE   DATETIME,
           TRADE_CLASS                 INT NULL,
           TRADING_PARTNER_CONTRACT_NO VARCHAR(50) NULL,
           TRADE_CLASS_START_DATE      DATETIME NULL,
           TRADE_CLASS_END_DATE        DATETIME NULL,
           INBOUND_STATUS              CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS          BIT NOT NULL,
           BATCH_ID                    VARCHAR(50) NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL
        )
  END

GO
-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'PK_CFP_DETAILS_CFP_DETAILS_SID'
                      AND TYPE = 'PK')
  BEGIN
      ALTER TABLE CFP_DETAILS
        ADD CONSTRAINT PK_CFP_DETAILS_CFP_DETAILS_SID PRIMARY KEY (CFP_DETAILS_SID)
  END

GO
-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT *
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_DETAILS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE CFP_DETAILS
        ADD CONSTRAINT DF_CFP_DETAILS_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE CFP_DETAILS
        ADD CONSTRAINT DF_CFP_DETAILS_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE CFP_DETAILS
        ADD CONSTRAINT DF_CFP_DETAILS_CREATED_DATE DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE CFP_DETAILS
        ADD CONSTRAINT DF_CFP_DETAILS_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE CFP_DETAILS
        ADD CONSTRAINT DF_CFP_DETAILS_MODIFIED_DATE DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

------------------------------------UNIQUE_CONSTRAINT------------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'CFP_DETAILS')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = Object_id('CFP_DETAILS')
                            AND NAME = 'UQ_CFP_DETAILS_CFP_MODEL_SID_COMPANY_MASTER_SID')
        BEGIN
            ALTER TABLE CFP_DETAILS
              ADD CONSTRAINT UQ_CFP_DETAILS_CFP_MODEL_SID_COMPANY_MASTER_SID UNIQUE(CFP_MODEL_SID, COMPANY_MASTER_SID)
        END
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'CFP_DETAILS'--TABLE NAME
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

-------------------------------HIST_CFP_DETAILS-------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'HIST_CFP_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE HIST_CFP_DETAILS
        (
           CFP_DETAILS_SID             INT NOT NULL,
           CFP_MODEL_SID               INT NOT NULL,
           COMPANY_MASTER_SID          INT NOT NULL,
           COMPANY_START_DATE          DATETIME NOT NULL,
           COMPANY_END_DATE            DATETIME NULL,
           COMPANY_CFP_ATTACHED_STATUS INT NULL,
           COMPANY_CFP_ATTACHED_DATE   DATETIME,
           TRADE_CLASS                 INT NULL,
           TRADING_PARTNER_CONTRACT_NO VARCHAR(50) NULL,
           TRADE_CLASS_START_DATE      DATETIME NULL,
           TRADE_CLASS_END_DATE        DATETIME NULL,
           INBOUND_STATUS              CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS          BIT NOT NULL,
           BATCH_ID                    VARCHAR(50) NULL,
           [SOURCE]                    VARCHAR(50) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL,
           ACTION_FLAG                 CHAR(1) NOT NULL,
           ACTION_DATE                 DATETIME NOT NULL
        )
  END


GO
---------------PRIMARY KEYS--------------------
--IF NOT EXISTS (SELECT 1 FROM SYS.KEY_CONSTRAINTS
--               WHERE OBJECT_NAME(PARENT_OBJECT_ID)='HIST_CFP_MODEL_DETAILS'
--			   AND SCHEMA_NAME(SCHEMA_ID)='DBO'
--			   AND NAME='PK_HIST_CFP_MODEL_DETAILS_CFP_MODEL_DETAILS_SID_VERSION_NO'
--			   AND TYPE='PK')
--BEGIN
--ALTER TABLE HIST_CFP_MODEL_DETAILS ADD CONSTRAINT PK_HIST_CFP_MODEL_DETAILS_CFP_MODEL_DETAILS_SID_VERSION_NO 
--PRIMARY KEY (CFP_MODEL_DETAILS_SID,VERSION_NO)
--END
-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT *
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'HIST_CFP_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_HIST_CFP_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE HIST_CFP_DETAILS
        ADD CONSTRAINT DF_HIST_CFP_DETAILS_ACTION_DATE DEFAULT (Getdate()) FOR ACTION_DATE
  END

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_CFP_DETAILS'--TABLE NAME
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

---------------------------------CFP_DETAILS TRIGGER-----------------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_CFP_DETAILS_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_CFP_DETAILS_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_CFP_DETAILS_UPD]
ON [dbo].[CFP_DETAILS]
AFTER UPDATE
AS
  BEGIN
      SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_CFP_DETAILS
                    (CFP_DETAILS_SID,
                     CFP_MODEL_SID,
                     COMPANY_MASTER_SID,
                     COMPANY_START_DATE,
                     COMPANY_END_DATE,
                     COMPANY_CFP_ATTACHED_STATUS,
                     COMPANY_CFP_ATTACHED_DATE,
                     TRADE_CLASS,
                     TRADING_PARTNER_CONTRACT_NO,
                     TRADE_CLASS_START_DATE,
                     TRADE_CLASS_END_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT CFP_DETAILS_SID,
               CFP_MODEL_SID,
               COMPANY_MASTER_SID,
               COMPANY_START_DATE,
               COMPANY_END_DATE,
               COMPANY_CFP_ATTACHED_STATUS,
               COMPANY_CFP_ATTACHED_DATE,
               TRADE_CLASS,
               TRADING_PARTNER_CONTRACT_NO,
               TRADE_CLASS_START_DATE,
               TRADE_CLASS_END_DATE,
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
           WHERE  [Name] = N'TRG_CFP_DETAILS_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_CFP_DETAILS_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_CFP_DETAILS_INS]
ON [dbo].[CFP_DETAILS]
AFTER INSERT
AS
  BEGIN
      SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_CFP_DETAILS
                    (CFP_DETAILS_SID,
                     CFP_MODEL_SID,
                     COMPANY_MASTER_SID,
                     COMPANY_START_DATE,
                     COMPANY_END_DATE,
                     COMPANY_CFP_ATTACHED_STATUS,
                     COMPANY_CFP_ATTACHED_DATE,
                     TRADE_CLASS,
                     TRADING_PARTNER_CONTRACT_NO,
                     TRADE_CLASS_START_DATE,
                     TRADE_CLASS_END_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT CFP_DETAILS_SID,
               CFP_MODEL_SID,
               COMPANY_MASTER_SID,
               COMPANY_START_DATE,
               COMPANY_END_DATE,
               COMPANY_CFP_ATTACHED_STATUS,
               COMPANY_CFP_ATTACHED_DATE,
               TRADE_CLASS,
               TRADING_PARTNER_CONTRACT_NO,
               TRADE_CLASS_START_DATE,
               TRADE_CLASS_END_DATE,
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
           WHERE  [Name] = N'TRG_CFP_DETAILS_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_CFP_DETAILS_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_CFP_DETAILS_DEL]
ON [dbo].[CFP_DETAILS]
AFTER DELETE
AS
  BEGIN
      SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_CFP_DETAILS
                    (CFP_DETAILS_SID,
                     CFP_MODEL_SID,
                     COMPANY_MASTER_SID,
                     COMPANY_START_DATE,
                     COMPANY_END_DATE,
                     COMPANY_CFP_ATTACHED_STATUS,
                     COMPANY_CFP_ATTACHED_DATE,
                     TRADE_CLASS,
                     TRADING_PARTNER_CONTRACT_NO,
                     TRADE_CLASS_START_DATE,
                     TRADE_CLASS_END_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT CFP_DETAILS_SID,
               CFP_MODEL_SID,
               COMPANY_MASTER_SID,
               COMPANY_START_DATE,
               COMPANY_END_DATE,
               COMPANY_CFP_ATTACHED_STATUS,
               COMPANY_CFP_ATTACHED_DATE,
               TRADE_CLASS,
               TRADING_PARTNER_CONTRACT_NO,
               TRADE_CLASS_START_DATE,
               TRADE_CLASS_END_DATE,
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

--------------------------IMTD_CFP_DETAILS---------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'IMTD_CFP_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[IMTD_CFP_DETAILS]
        (
           IMTD_CFP_DETAILS_SID        INT IDENTITY(1, 1) NOT NULL,
           CFP_MODEL_SID               INT,
           COMPANY_MASTER_SID          INT,
           CFP_DETAILS_SID             INT,
           COMPANY_ID                  VARCHAR(38),
           COMPANY_NO                  VARCHAR(50),
           COMPANY_NAME                VARCHAR(100),
           COMPANY_TYPE                VARCHAR(50),
           COMPANY_START_DATE          DATETIME,
           COMPANY_END_DATE            DATETIME,
           COMPANY_STATUS              INT,
           TRADING_PARTNER_CONTRACT_NO VARCHAR(50),
           CFP_DETAILS_ATTACHED_DATE   DATETIME,
           CFP_DETAILS_START_DATE      DATETIME,
           CFP_DETAILS_END_DATE        DATETIME,
           CFP_DETAILS_ATTACHED_STATUS INT,
           CFP_DETAILS_TRADE_CLASS     VARCHAR(100),
           CFP_DETAILS_TC_START_DATE   DATETIME,
           CFP_DETAILS_TC_END_DATE     DATETIME,
           CFP_DETAILS_CREATED_DATE    DATETIME,
           CFP_DETAILS_CREATED_BY      VARCHAR(50),
           CFP_DETAILS_MODIFIED_DATE   DATETIME,
           CFP_DETAILS_MODIFIED_BY     VARCHAR(50),
           USERS_SID                   VARCHAR(50),
           SESSION_ID                  VARCHAR(100),
           IMTD_CREATED_DATE           DATETIME,
           CHECK_RECORD                BIT,
           OPERATION                   VARCHAR(100),
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_IMTD_CFP_DETAILS_IMTD_CFP_DETAILS_SID'
                     AND TABLE_NAME = 'IMTD_CFP_DETAILS')
  BEGIN
      ALTER TABLE [DBO].[IMTD_CFP_DETAILS]
        ADD CONSTRAINT PK_IMTD_CFP_DETAILS_IMTD_CFP_DETAILS_SID PRIMARY KEY(IMTD_CFP_DETAILS_SID)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.IMTD_CFP_DETAILS')
                      AND NAME = 'DF_IMTD_CFP_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[IMTD_CFP_DETAILS]
        ADD CONSTRAINT [DF_IMTD_CFP_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.IMTD_CFP_DETAILS')
                      AND NAME = 'DF_IMTD_CFP_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[IMTD_CFP_DETAILS]
        ADD CONSTRAINT [DF_IMTD_CFP_DETAILS_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.IMTD_CFP_DETAILS')
                      AND NAME = 'DF_IMTD_CFP_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[IMTD_CFP_DETAILS]
        ADD CONSTRAINT [DF_IMTD_CFP_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.IMTD_CFP_DETAILS')
                      AND NAME = 'DF_IMTD_CFP_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[IMTD_CFP_DETAILS]
        ADD CONSTRAINT [DF_IMTD_CFP_DETAILS_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'IMTD_CFP_DETAILS'--TABLE NAME
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


---------------alg-1604 cr----------------
-----------------------------------CFP_CONTRACT_DETAILS_PENDING-----------------------------------

IF NOT EXISTS (SELECT 1
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CFP_CONTRACT_DETAILS_PENDING'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE CFP_CONTRACT_DETAILS_PENDING
        (
           CFP_CONTRACT_DETAILS_PENDING_SID     INT NOT NULL IDENTITY(1, 1),
           CFP_CONTRACT_SID             INT NOT NULL,
           COMPANY_MASTER_SID           INT NOT NULL,
           COMPANY_START_DATE           DATETIME NOT NULL,
           COMPANY_END_DATE             DATETIME NULL,
           TRADE_CLASS                  INT NULL,
           TRADE_CLASS_START_DATE       DATETIME NULL,
           TRADE_CLASS_END_DATE         DATETIME NULL,
           CFP_CONTRACT_ATTACHED_STATUS INT NULL,
           CFP_CONTRACT_ATTACHED_DATE   DATETIME NULL,
           INBOUND_STATUS               CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS           BIT NOT NULL,
           BATCH_ID                     VARCHAR(50) NULL,
           [SOURCE]                     VARCHAR(50) NULL,
           [CREATED_BY]                 INT NOT NULL,
           [CREATED_DATE]               DATETIME NOT NULL,
           [MODIFIED_BY]                INT NOT NULL,
           [MODIFIED_DATE]              DATETIME NOT NULL,
		   CHECK_RECORD                BIT
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS_PENDING'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'PK_CFP_CONTRACT_DETAILS_PENDING_CFP_CONTRACT_DETAILS_PENDING_SID'
                      AND TYPE = 'PK')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS_PENDING
        ADD CONSTRAINT PK_CFP_CONTRACT_DETAILS_PENDING_CFP_CONTRACT_DETAILS_PENDING_SID PRIMARY KEY (CFP_CONTRACT_DETAILS_PENDING_SID)
  END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS_PENDING'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_DETAILS_PENDING_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS_PENDING
        ADD CONSTRAINT DF_CFP_CONTRACT_DETAILS_PENDING_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS_PENDING'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_DETAILS_PENDING_CREATED_BY')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS_PENDING
        ADD CONSTRAINT DF_CFP_CONTRACT_DETAILS_PENDING_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS_PENDING'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_DETAILS_PENDING_CREATED_DATE')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS_PENDING
        ADD CONSTRAINT DF_CFP_CONTRACT_DETAILS_PENDING_CREATED_DATE DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS_PENDING'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_DETAILS_PENDING_MODIFIED_BY')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS_PENDING
        ADD CONSTRAINT DF_CFP_CONTRACT_DETAILS_PENDING_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS_PENDING'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_DETAILS_PENDING_MODIFIED_DATE')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS_PENDING
        ADD CONSTRAINT DF_CFP_CONTRACT_DETAILS_PENDING_MODIFIED_DATE DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

-------------------------------------UNIQUE_CONSTRAINT----------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'CFP_CONTRACT_DETAILS_PENDING') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('CFP_CONTRACT_DETAILS_PENDING')
                      AND NAME = 'UQ_CFP_CONTRACT_DETAILS_PENDING_COMPANY_MASTER_SID_CFP_CONTRACT_SID_COMPANY_START_DATE')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS_PENDING
        ADD CONSTRAINT UQ_CFP_CONTRACT_DETAILS_PENDING_COMPANY_MASTER_SID_CFP_CONTRACT_SID_COMPANY_START_DATE UNIQUE(COMPANY_MASTER_SID, CFP_CONTRACT_SID,COMPANY_START_DATE)
  END
END
GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'CFP_CONTRACT_DETAILS_PENDING'--TABLE NAME
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

