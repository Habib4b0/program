IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'IFP_MODEL'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[IFP_MODEL]
        (
           IFP_MODEL_SID                INT NOT NULL IDENTITY(1, 1),
           IFP_ID                       VARCHAR(50) NOT NULL,
           IFP_NO                       VARCHAR(50) NOT NULL,
           IFP_NAME                     VARCHAR(100) NOT NULL,
           IFP_TYPE                     INT NULL,
           IFP_CATEGORY                 INT NULL,
           IFP_DESIGNATION              VARCHAR(50) NULL,
           IFP_STATUS                   INT NOT NULL,
           IFP_START_DATE               DATETIME NOT NULL,
           IFP_END_DATE                 DATETIME NULL,
           PARENT_IFP_ID                VARCHAR(50) NULL,
           PARENT_IFP_NAME              VARCHAR(100) NULL,
           TOTAL_VOLUME_COMMITMENT      VARCHAR(50) NULL,
           TOTAL_DOLLAR_COMMITMENT      VARCHAR(50) NULL,
           TOTAL_MARKETSHARE_COMMITMENT VARCHAR(50) NULL,
           COMMITMENT_PERIOD            VARCHAR(50) NULL,
           INTERNAL_NOTES               VARCHAR(4000),
           INBOUND_STATUS               CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS           BIT NOT NULL,
           BATCH_ID                     VARCHAR(50) NULL,
           [SOURCE]                     VARCHAR(50) NULL,
           [CREATED_BY]                 INT NOT NULL,
           [CREATED_DATE]               DATETIME NOT NULL,
           [MODIFIED_BY]                INT NOT NULL,
           [MODIFIED_DATE]              DATETIME NOT NULL
        )
  END

GO
----------------------------- datatype change ------------------------
IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'IFP_DESIGNATION'
                  AND Object_name(object_id) = 'IFP_MODEL')
  BEGIN
      DROP STATISTICS dbo.IFP_MODEL.IFP_DESIGNATION 
  END 

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'IFP_MODEL'
                  AND COLUMN_NAME = 'IFP_DESIGNATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IFP_MODEL
        ALTER COLUMN IFP_DESIGNATION INT
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'IFP_MODEL'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_IFP_MODEL_IFP_MODEL_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [dbo].[IFP_MODEL]
    ADD CONSTRAINT PK_IFP_MODEL_IFP_MODEL_SID PRIMARY KEY(IFP_MODEL_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_MODEL')
                      AND NAME = 'DF_IFP_MODEL_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[IFP_MODEL]
        ADD CONSTRAINT [DF_IFP_MODEL_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_MODEL')
                      AND NAME = 'DF_IFP_MODEL_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[IFP_MODEL]
        ADD CONSTRAINT [DF_IFP_MODEL_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_MODEL')
                      AND NAME = 'DF_IFP_MODEL_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[IFP_MODEL]
        ADD CONSTRAINT [DF_IFP_MODEL_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_MODEL')
                      AND NAME = 'DF_IFP_MODEL_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[IFP_MODEL]
        ADD CONSTRAINT [DF_IFP_MODEL_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_MODEL')
                      AND NAME = 'DF_IFP_MODEL_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[IFP_MODEL]
        ADD CONSTRAINT [DF_IFP_MODEL_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

----------------------------------UNIQUE_CONSTRAINT-------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'IFP_MODEL')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = Object_id('IFP_MODEL')
                            AND NAME = 'UQ_IFP_MODEL_IFP_ID')
        BEGIN
            ALTER TABLE IFP_MODEL
              ADD CONSTRAINT UQ_IFP_MODEL_IFP_ID UNIQUE(IFP_ID)
        END
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'IFP_MODEL'--TABLE NAME
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

--------------------------------------HIST_IFP_MODEL----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'HIST_IFP_MODEL'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_IFP_MODEL]
        (
           IFP_MODEL_SID                INT NOT NULL,
           IFP_ID                       VARCHAR(50) NOT NULL,
           IFP_NO                       VARCHAR(50) NOT NULL,
           IFP_NAME                     VARCHAR(100) NOT NULL,
           IFP_TYPE                     INT NULL,
           IFP_CATEGORY                 INT NULL,
           IFP_DESIGNATION              VARCHAR(50) NULL,
           IFP_STATUS                   INT NOT NULL,
           IFP_START_DATE               DATETIME NOT NULL,
           IFP_END_DATE                 DATETIME NULL,
           PARENT_IFP_ID                VARCHAR(50) NULL,
           PARENT_IFP_NAME              VARCHAR(100) NULL,
           TOTAL_VOLUME_COMMITMENT      VARCHAR(50) NULL,
           TOTAL_DOLLAR_COMMITMENT      VARCHAR(50) NULL,
           TOTAL_MARKETSHARE_COMMITMENT VARCHAR(50) NULL,
           COMMITMENT_PERIOD            VARCHAR(50) NULL,
           INTERNAL_NOTES               VARCHAR(4000),
           INBOUND_STATUS               CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS           BIT NOT NULL,
           BATCH_ID                     VARCHAR(50) NULL,
           [SOURCE]                     VARCHAR(50) NULL,
           [CREATED_BY]                 INT NOT NULL,
           [CREATED_DATE]               DATETIME NOT NULL,
           [MODIFIED_BY]                INT NOT NULL,
           [MODIFIED_DATE]              DATETIME NOT NULL,
           ACTION_FLAG                  CHAR(1) NOT NULL,
           ACTION_DATE                  DATETIME NOT NULL
        )
  END

GO

----------------------------- datatype change ------------------------

IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'IFP_DESIGNATION'
                  AND Object_name(object_id) = 'HIST_IFP_MODEL')
  BEGIN
      DROP STATISTICS dbo.HIST_IFP_MODEL.IFP_DESIGNATION 
  END 

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_IFP_MODEL'
                  AND COLUMN_NAME = 'IFP_DESIGNATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_IFP_MODEL
        ALTER COLUMN IFP_DESIGNATION INT
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_IFP_MODEL')
                      AND NAME = 'DF_HIST_IFP_MODEL_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_IFP_MODEL]
        ADD CONSTRAINT [DF_HIST_IFP_MODEL_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_IFP_MODEL'--TABLE NAME
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

---------------------------------------IFP_MODEL TRIGGER-------------------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_MODEL_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_IFP_MODEL_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_MODEL_UPD]
ON [dbo].[IFP_MODEL]
AFTER UPDATE
AS
  BEGIN
      SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_IFP_MODEL
                    (IFP_MODEL_SID,IFP_ID,IFP_NO,IFP_NAME,
                     IFP_TYPE,IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,
                     IFP_START_DATE,IFP_END_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,
                     TOTAL_VOLUME_COMMITMENT,TOTAL_DOLLAR_COMMITMENT,TOTAL_MARKETSHARE_COMMITMENT,COMMITMENT_PERIOD,
                     INTERNAL_NOTES,INBOUND_STATUS,RECORD_LOCK_STATUS,BATCH_ID,
                     [SOURCE],CREATED_BY,CREATED_DATE,MODIFIED_BY,
                     MODIFIED_DATE,ACTION_FLAG)
        SELECT IFP_MODEL_SID,IFP_ID,IFP_NO,IFP_NAME,
               IFP_TYPE,IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,
               IFP_START_DATE,IFP_END_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,
               TOTAL_VOLUME_COMMITMENT,TOTAL_DOLLAR_COMMITMENT,TOTAL_MARKETSHARE_COMMITMENT,COMMITMENT_PERIOD,
               INTERNAL_NOTES,INBOUND_STATUS,RECORD_LOCK_STATUS,BATCH_ID,
               [SOURCE],CREATED_BY,CREATED_DATE,MODIFIED_BY,
               MODIFIED_DATE,'C'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_MODEL_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_IFP_MODEL_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_MODEL_INS]
ON [dbo].[IFP_MODEL]
AFTER INSERT
AS
  BEGIN
      SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_IFP_MODEL
                    (IFP_MODEL_SID,IFP_ID,IFP_NO,IFP_NAME,
                     IFP_TYPE,IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,
                     IFP_START_DATE,IFP_END_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,
                     TOTAL_VOLUME_COMMITMENT,TOTAL_DOLLAR_COMMITMENT,TOTAL_MARKETSHARE_COMMITMENT,COMMITMENT_PERIOD,
                     INTERNAL_NOTES,INBOUND_STATUS,RECORD_LOCK_STATUS,BATCH_ID,
                     [SOURCE],CREATED_BY,CREATED_DATE,MODIFIED_BY,
                     MODIFIED_DATE,ACTION_FLAG)
        SELECT IFP_MODEL_SID,IFP_ID,IFP_NO,IFP_NAME,
               IFP_TYPE,IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,
               IFP_START_DATE,IFP_END_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,
               TOTAL_VOLUME_COMMITMENT,TOTAL_DOLLAR_COMMITMENT,TOTAL_MARKETSHARE_COMMITMENT,COMMITMENT_PERIOD,
               INTERNAL_NOTES,INBOUND_STATUS,RECORD_LOCK_STATUS,BATCH_ID,
               [SOURCE],CREATED_BY,CREATED_DATE,MODIFIED_BY,
               MODIFIED_DATE,'A'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_MODEL_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_IFP_MODEL_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_MODEL_DEL]
ON [dbo].[IFP_MODEL]
AFTER DELETE
AS
  BEGIN
      SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_IFP_MODEL
                    (IFP_MODEL_SID,IFP_ID,IFP_NO,IFP_NAME,
                     IFP_TYPE,IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,
                     IFP_START_DATE,IFP_END_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,
                     TOTAL_VOLUME_COMMITMENT,TOTAL_DOLLAR_COMMITMENT,TOTAL_MARKETSHARE_COMMITMENT,COMMITMENT_PERIOD,
                     INTERNAL_NOTES,INBOUND_STATUS,RECORD_LOCK_STATUS,BATCH_ID,
                     [SOURCE],CREATED_BY,CREATED_DATE,MODIFIED_BY,
                     MODIFIED_DATE,ACTION_FLAG)
        SELECT IFP_MODEL_SID,IFP_ID,IFP_NO,IFP_NAME,
               IFP_TYPE,IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,
               IFP_START_DATE,IFP_END_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,
               TOTAL_VOLUME_COMMITMENT,TOTAL_DOLLAR_COMMITMENT,TOTAL_MARKETSHARE_COMMITMENT,COMMITMENT_PERIOD,
               INTERNAL_NOTES,INBOUND_STATUS,RECORD_LOCK_STATUS,BATCH_ID,
               [SOURCE],CREATED_BY,CREATED_DATE,MODIFIED_BY,
               MODIFIED_DATE,'D'
        FROM   DELETED
  END

GO

-------------------------------------------IFP_DETAILS------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'IFP_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[IFP_DETAILS]
        (
           IFP_DETAILS_SID          INT NOT NULL IDENTITY(1, 1),
           IFP_MODEL_SID            INT NOT NULL,
           ITEM_MASTER_SID          INT NOT NULL,
           [START_DATE]             DATETIME NOT NULL,
           END_DATE                 DATETIME NULL,
           ITEM_IFP_ATTACHED_STATUS INT NULL,
           ITEM_IFP_ATTACHED_DATE   DATETIME,
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
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'IFP_DETAILS'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_IFP_DETAILS_IFP_DETAILS_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [dbo].[IFP_DETAILS]
    ADD CONSTRAINT PK_IFP_DETAILS_IFP_DETAILS_SID PRIMARY KEY(IFP_DETAILS_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_DETAILS')
                      AND NAME = 'DF_IFP_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[IFP_DETAILS]
        ADD CONSTRAINT [DF_IFP_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_DETAILS')
                      AND NAME = 'DF_IFP_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[IFP_DETAILS]
        ADD CONSTRAINT [DF_IFP_DETAILS_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_DETAILS')
                      AND NAME = 'DF_IFP_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[IFP_DETAILS]
        ADD CONSTRAINT [DF_IFP_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_DETAILS')
                      AND NAME = 'DF_IFP_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[IFP_DETAILS]
        ADD CONSTRAINT [DF_IFP_DETAILS_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_DETAILS')
                      AND NAME = 'DF_IFP_DETAILS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[IFP_DETAILS]
        ADD CONSTRAINT [DF_IFP_DETAILS_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

---------------------------------UNIQUE_CONSTRAINT--------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'IFP_DETAILS')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = Object_id('IFP_DETAILS')
                            AND NAME = 'UQ_IFP_DETAILS_IFP_MODEL_SID_ITEM_MASTER_SID')
        BEGIN
            ALTER TABLE IFP_DETAILS
              ADD CONSTRAINT UQ_IFP_DETAILS_IFP_MODEL_SID_ITEM_MASTER_SID UNIQUE(IFP_MODEL_SID, ITEM_MASTER_SID)
        END
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'IFP_DETAILS'--TABLE NAME
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

--------------------------------------HIST_IFP_DETAILS----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'HIST_IFP_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_IFP_DETAILS]
        (
           IFP_DETAILS_SID          INT NOT NULL,
           IFP_MODEL_SID            INT NOT NULL,
           ITEM_MASTER_SID          INT NOT NULL,
           [START_DATE]             DATETIME NOT NULL,
           END_DATE                 DATETIME NULL,
           ITEM_IFP_ATTACHED_STATUS INT NULL,
           ITEM_IFP_ATTACHED_DATE   DATETIME,
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
               WHERE  parent_object_id = Object_id('dbo.HIST_IFP_DETAILS')
                      AND NAME = 'DF_HIST_IFP_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_IFP_DETAILS]
        ADD CONSTRAINT [DF_HIST_IFP_DETAILS_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_IFP_DETAILS'--TABLE NAME
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

-------------------------------------IFP_DETAILS TRIGGER-----------------------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_DETAILS_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_IFP_DETAILS_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_DETAILS_UPD]
ON [dbo].[IFP_DETAILS]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_IFP_DETAILS
                    (IFP_DETAILS_SID,IFP_MODEL_SID,ITEM_MASTER_SID,START_DATE,
                     END_DATE,ITEM_IFP_ATTACHED_STATUS,ITEM_IFP_ATTACHED_DATE,INBOUND_STATUS,
                     RECORD_LOCK_STATUS,BATCH_ID,[SOURCE],CREATED_BY,
                     CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ACTION_FLAG)
        SELECT IFP_DETAILS_SID,IFP_MODEL_SID,ITEM_MASTER_SID,START_DATE,
               END_DATE,ITEM_IFP_ATTACHED_STATUS,ITEM_IFP_ATTACHED_DATE,INBOUND_STATUS,
               RECORD_LOCK_STATUS,BATCH_ID,[SOURCE],CREATED_BY,
               CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'C'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_DETAILS_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_IFP_DETAILS_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_DETAILS_INS]
ON [dbo].[IFP_DETAILS]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_IFP_DETAILS
                    (IFP_DETAILS_SID,IFP_MODEL_SID,ITEM_MASTER_SID,START_DATE,
                     END_DATE,ITEM_IFP_ATTACHED_STATUS,ITEM_IFP_ATTACHED_DATE,INBOUND_STATUS,
                     RECORD_LOCK_STATUS,BATCH_ID,[SOURCE],CREATED_BY,
                     CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ACTION_FLAG)
        SELECT IFP_DETAILS_SID,IFP_MODEL_SID,ITEM_MASTER_SID,START_DATE,
               END_DATE,ITEM_IFP_ATTACHED_STATUS,ITEM_IFP_ATTACHED_DATE,INBOUND_STATUS,
               RECORD_LOCK_STATUS,BATCH_ID,[SOURCE],CREATED_BY,
               CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'A'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_DETAILS_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_IFP_DETAILS_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_DETAILS_DEL]
ON [dbo].[IFP_DETAILS]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_IFP_DETAILS
                    (IFP_DETAILS_SID,IFP_MODEL_SID,ITEM_MASTER_SID,START_DATE,
                     END_DATE,ITEM_IFP_ATTACHED_STATUS,ITEM_IFP_ATTACHED_DATE,INBOUND_STATUS,
                     RECORD_LOCK_STATUS,BATCH_ID,[SOURCE],CREATED_BY,
                     CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,ACTION_FLAG)
        SELECT IFP_DETAILS_SID,IFP_MODEL_SID,ITEM_MASTER_SID,START_DATE,
               END_DATE,ITEM_IFP_ATTACHED_STATUS,ITEM_IFP_ATTACHED_DATE,INBOUND_STATUS,
               RECORD_LOCK_STATUS,BATCH_ID,[SOURCE],CREATED_BY,
               CREATED_DATE,MODIFIED_BY,MODIFIED_DATE,'D'
        FROM   DELETED
  END

GO
------------------------IMTD_IFP_DETAILS---------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'IMTD_IFP_DETAILS'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[IMTD_IFP_DETAILS]
        (
           [IMTD_IFP_DETAILS_SID]         INT IDENTITY(1, 1) NOT NULL,
           [IFP_MODEL_SID]                INT,
           [ITEM_MASTER_SID]              INT,
           [CONTRACT_MASTER_SID]          INT,
           [CFP_MODEL_SID]                INT,
           [ITEM_ID]                      VARCHAR(38),
           [ITEM_NO]                      VARCHAR(50),
           [ITEM_NAME]                    VARCHAR(100),
           [ITEM_STATUS]                  INT,
           [ITEM_START_DATE]              DATETIME,
           [ITEM_END_DATE]                DATETIME,
           [ITEM_PACKAGE_SIZE]            VARCHAR(100),
           [ITEM_THERAPEUTIC_CLASS]       VARCHAR(50),
           [ITEM_PRIMARY_UOM]             VARCHAR(20),
           [ITEM_BRAND]                   VARCHAR(100),
           [ITEM_FORM]                    VARCHAR(50),
           [ITEM_STRENGTH]                VARCHAR(100),
           [IFP_DETAILS_SID]              INT,
           [IFP_DETAILS_START_DATE]       DATETIME,
           [IFP_DETAILS_END_DATE]         DATETIME,
           [IFP_DETAILS_ATTACHED_STATUS]  INT,
           [IFP_DETAILS_CREATED_BY]       VARCHAR(50),
           [IFP_DETAILS_CREATED_DATE]     DATETIME,
           [IFP_DETAILS_MODIFIED_BY]      VARCHAR(50),
           [IFP_DETAILS_MODIFIED_DATE]    DATETIME,
           [IFP_DETAILS_ATTACHED_DATE]    DATETIME,
           [TOTAL_VOLUME_COMMITMENT]      VARCHAR(50),
           [TOTAL_DOLLAR_COMMITMENT]      VARCHAR(50),
           [TOTAL_MARKETSHARE_COMMITMENT] VARCHAR(50),
           [COMMITMENT_PERIOD]            VARCHAR(50),
           [USERS_SID]                    INT,
           [SESSION_ID]                   VARCHAR(100),
           [IMTD_CREATEDDATE]             DATETIME,
           [CHECK_BOX]                    BIT,
           [OPERATION]                    VARCHAR(100),
           [CREATED_BY]                   INT NOT NULL,
           [CREATED_DATE]                 DATETIME NOT NULL,
           [MODIFIED_BY]                  INT NOT NULL,
           [MODIFIED_DATE]                DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_IMTD_IFP_DETAILS_IMTD_IFP_DETAILS_SID'
                 AND TABLE_NAME = 'IMTD_IFP_DETAILS')
  BEGIN
      ALTER TABLE [DBO].[IMTD_IFP_DETAILS]
        ADD CONSTRAINT PK_IMTD_IFP_DETAILS_IMTD_IFP_DETAILS_SID PRIMARY KEY([IMTD_IFP_DETAILS_SID])
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.IMTD_IFP_DETAILS')
                  AND NAME = 'DF_IMTD_IFP_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[IMTD_IFP_DETAILS]
        ADD CONSTRAINT [DF_IMTD_IFP_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.IMTD_IFP_DETAILS')
                  AND NAME = 'DF_IMTD_IFP_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[IMTD_IFP_DETAILS]
        ADD CONSTRAINT [DF_IMTD_IFP_DETAILS_CREATED_DATE] DEFAULT (GetDate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.IMTD_IFP_DETAILS')
                  AND NAME = 'DF_IMTD_IFP_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[IMTD_IFP_DETAILS]
        ADD CONSTRAINT [DF_IMTD_IFP_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.IMTD_IFP_DETAILS')
                  AND NAME = 'DF_IMTD_IFP_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[IMTD_IFP_DETAILS]
        ADD CONSTRAINT [DF_IMTD_IFP_DETAILS_MODIFIED_DATE] DEFAULT (GetDate()) FOR MODIFIED_DATE
  END

GO

--Column Addition 5-May-2015

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IMTD_IFP_DETAILS'
                  AND COLUMN_NAME = 'ITEM_DESC')
  BEGIN
      ALTER TABLE IMTD_IFP_DETAILS
        ADD ITEM_DESC VARCHAR(250) NULL
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'IMTD_IFP_DETAILS'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
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
