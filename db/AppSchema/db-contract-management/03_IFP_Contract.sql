IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'IFP_CONTRACT'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[IFP_CONTRACT]
        (
           IFP_CONTRACT_SID             INT NOT NULL IDENTITY(1, 1),
           IFP_MODEL_SID                INT NOT NULL,
           IFP_NAME                     VARCHAR(100) NOT NULL,
           IFP_TYPE                     INT NULL,
           IFP_CATEGORY                 INT NULL,
           IFP_DESIGNATION              VARCHAR(50) NULL,
           IFP_STATUS                   INT NOT NULL,
           IFP_START_DATE               DATETIME NOT NULL,
           IFP_END_DATE                 DATETIME NULL,
           CFP_CONTRACT_SID             INT NULL,
           CONTRACT_MASTER_SID          INT NOT NULL,
           IFP_CONTRACT_ATTACHED_STATUS INT NULL,
           IFP_CONTRACT_ATTACHED_DATE   DATETIME NULL,
           PARENT_IFP_ID                VARCHAR(50) NULL,
           PARENT_IFP_NAME              VARCHAR(100) NULL,
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

IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'IFP_DESIGNATION'
                  AND Object_name(object_id) = 'IFP_CONTRACT')
  BEGIN
      DROP STATISTICS dbo.IFP_CONTRACT.IFP_DESIGNATION 
  END 

GO

------------------------IFP_CONTRACT------------------------------------------------

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IFP_CONTRACT'
                      AND COLUMN_NAME = 'IFP_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IFP_CONTRACT
        ADD IFP_NO VARCHAR(50) 
  END

GO

-----------------------------UPDATE IFP_NO IN IFP_CONTRACT TABLE ----------------------------------
IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IFP_CONTRACT'
                      AND COLUMN_NAME = 'IFP_NO'
                      AND TABLE_SCHEMA = 'DBO')
BEGIN
UPDATE C 
SET C.IFP_NO=CF.IFP_NO
FROM IFP_CONTRACT C
INNER JOIN IFP_MODEL CF
ON C.IFP_MODEL_SID=CF.IFP_MODEL_SID
WHERE C.IFP_NO IS NULL
END
GO

--------------------------------------------ALTER

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'IFP_CONTRACT'
                  AND COLUMN_NAME = 'IFP_DESIGNATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IFP_CONTRACT
        ALTER COLUMN IFP_DESIGNATION INT
  END

GO

-------------------------------------ALTER NULL TO NOT NULL----------------------------------------
IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IFP_CONTRACT'
                      AND COLUMN_NAME = 'IFP_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IFP_CONTRACT
        ALTER COLUMN IFP_NO VARCHAR(50) NOT NULL
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'IFP_CONTRACT'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_IFP_CONTRACT_IFP_CONTRACT_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[IFP_CONTRACT]
        ADD CONSTRAINT PK_IFP_CONTRACT_IFP_CONTRACT_SID PRIMARY KEY(IFP_CONTRACT_SID)
  END

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_CONTRACT')
                      AND NAME = 'DF_IFP_CONTRACT_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[IFP_CONTRACT]
        ADD CONSTRAINT [DF_IFP_CONTRACT_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_CONTRACT')
                      AND NAME = 'DF_IFP_CONTRACT_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[IFP_CONTRACT]
        ADD CONSTRAINT [DF_IFP_CONTRACT_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_CONTRACT')
                      AND NAME = 'DF_IFP_CONTRACT_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[IFP_CONTRACT]
        ADD CONSTRAINT [DF_IFP_CONTRACT_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_CONTRACT')
                      AND NAME = 'DF_IFP_CONTRACT_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[IFP_CONTRACT]
        ADD CONSTRAINT [DF_IFP_CONTRACT_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_CONTRACT')
                      AND NAME = 'DF_IFP_CONTRACT_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[IFP_CONTRACT]
        ADD CONSTRAINT [DF_IFP_CONTRACT_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

--------------------------------------UNIQUE_CONSTRAINT--------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'IFP_CONTRACT')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = Object_id('IFP_CONTRACT')
                            AND NAME = 'UQ_IFP_CONTRACT_IFP_MODEL_SID_CONTRACT_MASTER_SID_CFP_CONTRACT_SID')
        BEGIN
            ALTER TABLE IFP_CONTRACT
              ADD CONSTRAINT UQ_IFP_CONTRACT_IFP_MODEL_SID_CONTRACT_MASTER_SID_CFP_CONTRACT_SID UNIQUE(IFP_MODEL_SID, CONTRACT_MASTER_SID, CFP_CONTRACT_SID)
        END
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'IFP_CONTRACT'--TABLE NAME
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

----------------------------HIST_IFP_CONTRACT----------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'HIST_IFP_CONTRACT'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_IFP_CONTRACT]
        (
           IFP_CONTRACT_SID             INT NOT NULL,
           IFP_MODEL_SID                INT NOT NULL,
           IFP_NAME                     VARCHAR(100) NOT NULL,
           IFP_TYPE                     INT NULL,
           IFP_CATEGORY                 INT NULL,
           IFP_DESIGNATION              VARCHAR(50) NULL,
           IFP_STATUS                   INT NOT NULL,
           IFP_START_DATE               DATETIME NOT NULL,
           IFP_END_DATE                 DATETIME NULL,
           CFP_CONTRACT_SID             INT NULL,
           CONTRACT_MASTER_SID          INT NOT NULL,
           IFP_CONTRACT_ATTACHED_STATUS INT NULL,
           IFP_CONTRACT_ATTACHED_DATE   DATETIME NULL,
           PARENT_IFP_ID                VARCHAR(50) NULL,
           PARENT_IFP_NAME              VARCHAR(100) NULL,
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


IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'IFP_DESIGNATION'
                  AND Object_name(object_id) = 'HIST_IFP_CONTRACT')
  BEGIN
      DROP STATISTICS dbo.HIST_IFP_CONTRACT.IFP_DESIGNATION 
  END 

GO


--HIST_IFP_CONTRACT
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_IFP_CONTRACT'
                      AND COLUMN_NAME = 'IFP_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_IFP_CONTRACT
        ADD IFP_NO VARCHAR(50)  NULL
  END

GO

IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_IFP_CONTRACT'
                      AND COLUMN_NAME = 'IFP_NO'
                      AND TABLE_SCHEMA = 'DBO')
BEGIN
UPDATE C 
SET C.IFP_NO=CF.IFP_NO
FROM HIST_IFP_CONTRACT C
INNER JOIN HIST_IFP_MODEL CF
ON C.IFP_MODEL_SID=CF.IFP_MODEL_SID
WHERE C.IFP_NO IS NULL
END
GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_IFP_CONTRACT'
                      AND COLUMN_NAME = 'IFP_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_IFP_CONTRACT
         ALTER COLUMN IFP_NO VARCHAR(50) NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_IFP_CONTRACT'
                  AND COLUMN_NAME = 'IFP_DESIGNATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_IFP_CONTRACT
        ALTER COLUMN IFP_DESIGNATION INT
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_IFP_CONTRACT')
                      AND NAME = 'DF_HIST_IFP_CONTRACT_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_IFP_CONTRACT]
        ADD CONSTRAINT [DF_HIST_IFP_CONTRACT_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

--IF NOT EXISTS (SELECT 'X'
--               FROM   sys.key_constraints
--               WHERE  parent_object_id = Object_id('dbo.HIST_IFP_CONTRACT')
--                      AND NAME = 'PK_HIST_IFP_CONTRACT_IFP_CONTRACT_SID_VERSION_NO')
--  BEGIN
--      ALTER TABLE [DBO].[HIST_IFP_CONTRACT]
--        ADD CONSTRAINT PK_HIST_IFP_CONTRACT_IFP_CONTRACT_SID_VERSION_NO PRIMARY KEY (IFP_CONTRACT_SID, VERSION_NO)
--  END
--GO
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_IFP_CONTRACT'--TABLE NAME
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

-----------------------------------------IFP_CONTRACT TRIGGER---------------------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_CONTRACT_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_IFP_CONTRACT_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_CONTRACT_INS]
ON [dbo].[IFP_CONTRACT]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_IFP_CONTRACT
                    (IFP_CONTRACT_SID,IFP_MODEL_SID,IFP_NAME,IFP_TYPE,
                     IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,IFP_START_DATE,
                     IFP_END_DATE,CFP_CONTRACT_SID,CONTRACT_MASTER_SID,IFP_CONTRACT_ATTACHED_STATUS,
                     IFP_CONTRACT_ATTACHED_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,INBOUND_STATUS,
                     RECORD_LOCK_STATUS,BATCH_ID,CREATED_BY,CREATED_DATE,
                     MODIFIED_BY,MODIFIED_DATE,ACTION_FLAG,IFP_NO)
        SELECT IFP_CONTRACT_SID,IFP_MODEL_SID,IFP_NAME,IFP_TYPE,
               IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,IFP_START_DATE,
               IFP_END_DATE,CFP_CONTRACT_SID,CONTRACT_MASTER_SID,IFP_CONTRACT_ATTACHED_STATUS,
               IFP_CONTRACT_ATTACHED_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,INBOUND_STATUS,
               RECORD_LOCK_STATUS,BATCH_ID,CREATED_BY,CREATED_DATE,
               MODIFIED_BY,MODIFIED_DATE,'A',IFP_NO
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_CONTRACT_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_IFP_CONTRACT_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_CONTRACT_UPD]
ON [dbo].[IFP_CONTRACT]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_IFP_CONTRACT
                    (IFP_CONTRACT_SID,IFP_MODEL_SID,IFP_NAME,IFP_TYPE,
                     IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,IFP_START_DATE,
                     IFP_END_DATE,CFP_CONTRACT_SID,CONTRACT_MASTER_SID,IFP_CONTRACT_ATTACHED_STATUS,
                     IFP_CONTRACT_ATTACHED_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,INBOUND_STATUS,
                     RECORD_LOCK_STATUS,BATCH_ID,CREATED_BY,CREATED_DATE,
                     MODIFIED_BY,MODIFIED_DATE,ACTION_FLAG,IFP_NO)
        SELECT IFP_CONTRACT_SID,IFP_MODEL_SID,IFP_NAME,IFP_TYPE,
               IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,IFP_START_DATE,
               IFP_END_DATE,CFP_CONTRACT_SID,CONTRACT_MASTER_SID,IFP_CONTRACT_ATTACHED_STATUS,
               IFP_CONTRACT_ATTACHED_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,INBOUND_STATUS,
               RECORD_LOCK_STATUS,BATCH_ID,CREATED_BY,CREATED_DATE,
               MODIFIED_BY,MODIFIED_DATE,'C',IFP_NO
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_CONTRACT_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_IFP_CONTRACT_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_CONTRACT_DEL]
ON [dbo].[IFP_CONTRACT]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_IFP_CONTRACT
                    (IFP_CONTRACT_SID,IFP_MODEL_SID,IFP_NAME,IFP_TYPE,
                     IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,IFP_START_DATE,
                     IFP_END_DATE,CFP_CONTRACT_SID,CONTRACT_MASTER_SID,IFP_CONTRACT_ATTACHED_STATUS,
                     IFP_CONTRACT_ATTACHED_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,INBOUND_STATUS,
                     RECORD_LOCK_STATUS,BATCH_ID,CREATED_BY,CREATED_DATE,
                     MODIFIED_BY,MODIFIED_DATE,ACTION_FLAG,IFP_NO)
        SELECT IFP_CONTRACT_SID,IFP_MODEL_SID,IFP_NAME,IFP_TYPE,
               IFP_CATEGORY,IFP_DESIGNATION,IFP_STATUS,IFP_START_DATE,
               IFP_END_DATE,CFP_CONTRACT_SID,CONTRACT_MASTER_SID,IFP_CONTRACT_ATTACHED_STATUS,
               IFP_CONTRACT_ATTACHED_DATE,PARENT_IFP_ID,PARENT_IFP_NAME,INBOUND_STATUS,
               RECORD_LOCK_STATUS,BATCH_ID,CREATED_BY,CREATED_DATE,
               MODIFIED_BY,MODIFIED_DATE,'D',IFP_NO
        FROM   DELETED
  END

GO 

-------------------------------------IFP_CONTRACT_DETAILS--------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'IFP_CONTRACT_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[IFP_CONTRACT_DETAILS]
        (
           IFP_CONTRACT_DETAILS_SID     INT NOT NULL IDENTITY(1, 1),
           IFP_CONTRACT_SID             INT NOT NULL,
           ITEM_MASTER_SID              INT NOT NULL,
           ITEM_START_DATE              DATETIME NOT NULL,
           ITEM_END_DATE                DATETIME NULL,
           TOTAL_VOLUME_COMMITMENT      VARCHAR(50) NULL,
           TOTAL_DOLLAR_COMMITMENT      VARCHAR(50) NULL,
           TOTAL_MARKETSHARE_COMMITMENT VARCHAR(50) NULL,
           COMMITMENT_PERIOD            VARCHAR(50) NULL,
           IFP_CONTRACT_ATTACHED_STATUS INT NULL,
           IFP_CONTRACT_ATTACHED_DATE   DATETIME NULL,
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

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'IFP_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'ITEM_STATUS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE IFP_CONTRACT_DETAILS
        ADD ITEM_STATUS INT
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'IFP_CONTRACT_DETAILS'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_IFP_CONTRACT_DETAILS_IFP_CONTRACT_DETAILS_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [dbo].[IFP_CONTRACT_DETAILS]
    ADD CONSTRAINT PK_IFP_CONTRACT_DETAILS_IFP_CONTRACT_DETAILS_SID PRIMARY KEY(IFP_CONTRACT_DETAILS_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_CONTRACT_DETAILS')
                      AND NAME = 'DF_IFP_CONTRACT_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[IFP_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_IFP_CONTRACT_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_CONTRACT_DETAILS')
                      AND NAME = 'DF_IFP_CONTRACT_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[IFP_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_IFP_CONTRACT_DETAILS_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_CONTRACT_DETAILS')
                      AND NAME = 'DF_IFP_CONTRACT_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[IFP_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_IFP_CONTRACT_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_CONTRACT_DETAILS')
                      AND NAME = 'DF_IFP_CONTRACT_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[IFP_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_IFP_CONTRACT_DETAILS_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.IFP_CONTRACT_DETAILS')
                      AND NAME = 'DF_IFP_CONTRACT_DETAILS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[IFP_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_IFP_CONTRACT_DETAILS_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

-------------------------------------UNIQUE_CONSTRAINT-------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'IFP_CONTRACT_DETAILS') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('IFP_CONTRACT_DETAILS')
                      AND NAME = 'UQ_IFP_CONTRACT_DETAILS_IFP_CONTRACT_SID_ITEM_MASTER_SID_ITEM_START_DATE')
  BEGIN
      ALTER TABLE IFP_CONTRACT_DETAILS
        ADD CONSTRAINT UQ_IFP_CONTRACT_DETAILS_IFP_CONTRACT_SID_ITEM_MASTER_SID_ITEM_START_DATE UNIQUE(IFP_CONTRACT_SID , ITEM_MASTER_SID, ITEM_START_DATE)
  END
END
GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'IFP_CONTRACT_DETAILS'--TABLE NAME
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

------------------------------------HIST_IFP_CONTRACT_DETAILS--------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'HIST_IFP_CONTRACT_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_IFP_CONTRACT_DETAILS]
        (
           IFP_CONTRACT_DETAILS_SID     INT NOT NULL,
           IFP_CONTRACT_SID             INT NOT NULL,
           ITEM_MASTER_SID              INT NOT NULL,
           ITEM_START_DATE              DATETIME NOT NULL,
           ITEM_END_DATE                DATETIME NULL,
           TOTAL_VOLUME_COMMITMENT      VARCHAR(50) NULL,
           TOTAL_DOLLAR_COMMITMENT      VARCHAR(50) NULL,
           TOTAL_MARKETSHARE_COMMITMENT VARCHAR(50) NULL,
           COMMITMENT_PERIOD            VARCHAR(50) NULL,
           IFP_CONTRACT_ATTACHED_STATUS INT NULL,
           IFP_CONTRACT_ATTACHED_DATE   DATETIME NULL,
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

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_IFP_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'ITEM_STATUS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_IFP_CONTRACT_DETAILS
        ADD ITEM_STATUS INT
  END

GO 

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_IFP_CONTRACT_DETAILS')
                      AND NAME = 'DF_HIST_IFP_CONTRACT_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_IFP_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_HIST_IFP_CONTRACT_DETAILS_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_IFP_CONTRACT_DETAILS'--TABLE NAME
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


-----------------------------------IFP_CONTRACT_DETAILS TRIGGER------------------------------


IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_CONTRACT_DETAILS_UPD')
  BEGIN
      DROP TRIGGER dbo.[TRG_IFP_CONTRACT_DETAILS_UPD]
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_CONTRACT_DETAILS_UPD]
ON [dbo].[IFP_CONTRACT_DETAILS]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
         AND EXISTS (SELECT *
                     FROM   DELETED)
        INSERT INTO HIST_IFP_CONTRACT_DETAILS
                    (IFP_CONTRACT_DETAILS_SID,
                     IFP_CONTRACT_SID,
                     ITEM_MASTER_SID,
                     ITEM_START_DATE,
                     ITEM_END_DATE,
                     TOTAL_VOLUME_COMMITMENT,
                     TOTAL_DOLLAR_COMMITMENT,
                     TOTAL_MARKETSHARE_COMMITMENT,
                     COMMITMENT_PERIOD,
                     IFP_CONTRACT_ATTACHED_STATUS,
                     IFP_CONTRACT_ATTACHED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ITEM_STATUS,
                     ACTION_FLAG)
        SELECT IFP_CONTRACT_DETAILS_SID,
               IFP_CONTRACT_SID,
               ITEM_MASTER_SID,
               ITEM_START_DATE,
               ITEM_END_DATE,
               TOTAL_VOLUME_COMMITMENT,
               TOTAL_DOLLAR_COMMITMENT,
               TOTAL_MARKETSHARE_COMMITMENT,
               COMMITMENT_PERIOD,
               IFP_CONTRACT_ATTACHED_STATUS,
               IFP_CONTRACT_ATTACHED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               ITEM_STATUS,
               'C'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_CONTRACT_DETAILS_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_IFP_CONTRACT_DETAILS_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_CONTRACT_DETAILS_INS]
ON [dbo].[IFP_CONTRACT_DETAILS]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
        INSERT INTO HIST_IFP_CONTRACT_DETAILS
                    (IFP_CONTRACT_DETAILS_SID,
                     IFP_CONTRACT_SID,
                     ITEM_MASTER_SID,
                     ITEM_START_DATE,
                     ITEM_END_DATE,
                     TOTAL_VOLUME_COMMITMENT,
                     TOTAL_DOLLAR_COMMITMENT,
                     TOTAL_MARKETSHARE_COMMITMENT,
                     COMMITMENT_PERIOD,
                     IFP_CONTRACT_ATTACHED_STATUS,
                     IFP_CONTRACT_ATTACHED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ITEM_STATUS,
                     ACTION_FLAG)
        SELECT IFP_CONTRACT_DETAILS_SID,
               IFP_CONTRACT_SID,
               ITEM_MASTER_SID,
               ITEM_START_DATE,
               ITEM_END_DATE,
               TOTAL_VOLUME_COMMITMENT,
               TOTAL_DOLLAR_COMMITMENT,
               TOTAL_MARKETSHARE_COMMITMENT,
               COMMITMENT_PERIOD,
               IFP_CONTRACT_ATTACHED_STATUS,
               IFP_CONTRACT_ATTACHED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               ITEM_STATUS,
               'A'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_IFP_CONTRACT_DETAILS_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_IFP_CONTRACT_DETAILS_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_IFP_CONTRACT_DETAILS_DEL]
ON [dbo].[IFP_CONTRACT_DETAILS]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   DELETED)
        INSERT INTO HIST_IFP_CONTRACT_DETAILS
                    (IFP_CONTRACT_DETAILS_SID,
                     IFP_CONTRACT_SID,
                     ITEM_MASTER_SID,
                     ITEM_START_DATE,
                     ITEM_END_DATE,
                     TOTAL_VOLUME_COMMITMENT,
                     TOTAL_DOLLAR_COMMITMENT,
                     TOTAL_MARKETSHARE_COMMITMENT,
                     COMMITMENT_PERIOD,
                     IFP_CONTRACT_ATTACHED_STATUS,
                     IFP_CONTRACT_ATTACHED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ITEM_STATUS,
                     ACTION_FLAG)
        SELECT IFP_CONTRACT_DETAILS_SID,
               IFP_CONTRACT_SID,
               ITEM_MASTER_SID,
               ITEM_START_DATE,
               ITEM_END_DATE,
               TOTAL_VOLUME_COMMITMENT,
               TOTAL_DOLLAR_COMMITMENT,
               TOTAL_MARKETSHARE_COMMITMENT,
               COMMITMENT_PERIOD,
               IFP_CONTRACT_ATTACHED_STATUS,
               IFP_CONTRACT_ATTACHED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               ITEM_STATUS,
               'D'
        FROM   DELETED
  END

GO 
