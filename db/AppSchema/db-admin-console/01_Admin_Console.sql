IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'COMPANY_GROUP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[COMPANY_GROUP]
        (
           [COMPANY_GROUP_SID]         INT IDENTITY(1, 1) NOT NULL,
           [COMPANY_GROUP_NO]          VARCHAR(50) NULL,
           [COMPANY_GROUP_NAME]        VARCHAR(100) NULL,
           [COMPANY_GROUP_DESCRIPTION] VARCHAR(250) NULL,
           [COMPANY_FILTER]            VARCHAR(4000) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL,
           [VERSION_NO]                INT NOT NULL
        )
  END

GO

-----------------------DEFAULT_CONSTRAINTS----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_GROUP')
                      AND NAME = 'DF_COMPANY_GROUP_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_GROUP]
        ADD CONSTRAINT [DF_COMPANY_GROUP_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_GROUP')
                      AND NAME = 'DF_COMPANY_GROUP_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_GROUP]
        ADD CONSTRAINT [DF_COMPANY_GROUP_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_GROUP')
                      AND NAME = 'DF_COMPANY_GROUP_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_GROUP]
        ADD CONSTRAINT [DF_COMPANY_GROUP_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_GROUP')
                      AND NAME = 'DF_COMPANY_GROUP_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_GROUP]
        ADD CONSTRAINT [DF_COMPANY_GROUP_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_GROUP')
                      AND NAME = 'DF_COMPANY_GROUP_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_GROUP]
        ADD CONSTRAINT [DF_COMPANY_GROUP_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

-------------------primary key-----------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_COMPANY_GROUP_COMPANY_GROUP_SID'
                     AND TABLE_NAME = 'COMPANY_GROUP')
BEGIN
  ALTER TABLE COMPANY_GROUP
    ADD CONSTRAINT PK_COMPANY_GROUP_COMPANY_GROUP_SID PRIMARY KEY(COMPANY_GROUP_SID)
END

GO

--------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'COMPANY_GROUP'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_COMPANY_GROUP_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_COMPANY_GROUP_UPD
  END

GO

---------------------HIST_COMPANY_GROUP---------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_COMPANY_GROUP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_COMPANY_GROUP]
        (
           [COMPANY_GROUP_SID]         INT NOT NULL,
           [COMPANY_GROUP_NO]          VARCHAR(50) NULL,
           [COMPANY_GROUP_NAME]        VARCHAR(100) NULL,
           [COMPANY_GROUP_DESCRIPTION] VARCHAR(250) NULL,
           [COMPANY_FILTER]            VARCHAR(4000) NULL,
           [CREATED_BY]                INT NOT NULL,
           [CREATED_DATE]              DATETIME NOT NULL,
           [MODIFIED_BY]               INT NOT NULL,
           [MODIFIED_DATE]             DATETIME NOT NULL,
           [VERSION_NO]                INT NOT NULL,
           [ACTION_FLAG]               CHAR(1) NOT NULL,
           [ACTION_DATE]               DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_COMPANY_GROUP')
                      AND NAME = 'DF_HIST_COMPANY_GROUP_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_COMPANY_GROUP]
        ADD CONSTRAINT [DF_HIST_COMPANY_GROUP_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_COMPANY_GROUP')
                      AND NAME = 'PK_HIST_COMPANY_GROUP_COMPANY_GROUP_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_COMPANY_GROUP]
        ADD CONSTRAINT PK_HIST_COMPANY_GROUP_COMPANY_GROUP_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (COMPANY_GROUP_SID, ACTION_FLAG, VERSION_NO)
  END

GO

--------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_COMPANY_GROUP'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_COMPANY_GROUP_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_COMPANY_GROUP_UPD
  END

GO

-------------trigger-------------------
CREATE TRIGGER [DBO].[TRG_COMPANY_GROUP_UPD]
ON [DBO].COMPANY_GROUP
--INSTEAD OF UPDATE
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      --UPDATE A
      --SET    A.[COMPANY_GROUP_NO] = B.[COMPANY_GROUP_NO],
      --       A.[COMPANY_GROUP_NAME] = B.[COMPANY_GROUP_NAME],
      --       A.[COMPANY_GROUP_DESCRIPTION] = B.[COMPANY_GROUP_DESCRIPTION],
      --       A.[CREATED_BY] = B.[CREATED_BY],
      --       A.[CREATED_DATE] = B.[CREATED_DATE],
      --       A.[MODIFIED_BY] = B.[MODIFIED_BY],
      --       A.[MODIFIED_DATE] = B.[MODIFIED_DATE],
      --       A.[VERSION_NO] = A.VERSION_NO + 1
      --FROM   COMPANY_GROUP A
      --       JOIN (SELECT *
      --             FROM   INSERTED
      --             EXCEPT
      --             SELECT *
      --             FROM   DELETED) B ON B.[COMPANY_GROUP_SID] = A.[COMPANY_GROUP_SID]
      --INSERT INTO HIST_COMPANY_GROUP
      --            ([COMPANY_GROUP_SID],
      --             [COMPANY_GROUP_NO],
      --             [COMPANY_GROUP_NAME],
      --             [COMPANY_GROUP_DESCRIPTION],
      --             [CREATED_BY],
      --             [CREATED_DATE],
      --             [MODIFIED_BY],
      --             [MODIFIED_DATE],
      --             [VERSION_NO],
      --             [ACTION_FLAG])
      --SELECT [COMPANY_GROUP_SID],
      --       [COMPANY_GROUP_NO],
      --       [COMPANY_GROUP_NAME],
      --       [COMPANY_GROUP_DESCRIPTION],
      --       [CREATED_BY],
      --       [CREATED_DATE],
      --       [MODIFIED_BY],
      --       [MODIFIED_DATE],
      --       [VERSION_NO]+1,
      --       'C'
      --FROM   INSERTED I
      --WHERE  EXISTS (SELECT 1
      --               FROM   (SELECT *
      --                       FROM   INSERTED
      --                       EXCEPT
      --                       SELECT *
      --                       FROM   DELETED) A
      --               WHERE  A.[COMPANY_GROUP_SID] = I.[COMPANY_GROUP_SID])
      INSERT INTO HIST_COMPANY_GROUP
                  ([COMPANY_GROUP_SID],
                   [COMPANY_GROUP_NO],
                   [COMPANY_GROUP_NAME],
                   [COMPANY_GROUP_DESCRIPTION],
                   COMPANY_FILTER,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   VERSION_NO,
                   [ACTION_FLAG])
      SELECT [COMPANY_GROUP_SID],
             [COMPANY_GROUP_NO],
             [COMPANY_GROUP_NAME],
             [COMPANY_GROUP_DESCRIPTION],
             COMPANY_FILTER,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             VERSION_NO,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_COMPANY_GROUP_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_COMPANY_GROUP_INS]
  END

GO

CREATE TRIGGER [DBO].[TRG_COMPANY_GROUP_INS]
ON [DBO].COMPANY_GROUP
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_COMPANY_GROUP
                  ([COMPANY_GROUP_SID],
                   [COMPANY_GROUP_NO],
                   [COMPANY_GROUP_NAME],
                   [COMPANY_GROUP_DESCRIPTION],
                   COMPANY_FILTER,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   VERSION_NO,
                   [ACTION_FLAG])
      SELECT [COMPANY_GROUP_SID],
             [COMPANY_GROUP_NO],
             [COMPANY_GROUP_NAME],
             [COMPANY_GROUP_DESCRIPTION],
             COMPANY_FILTER,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             VERSION_NO,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_COMPANY_GROUP_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_COMPANY_GROUP_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_COMPANY_GROUP_DEL]
ON [DBO].COMPANY_GROUP
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_COMPANY_GROUP
                  ([COMPANY_GROUP_SID],
                   [COMPANY_GROUP_NO],
                   [COMPANY_GROUP_NAME],
                   [COMPANY_GROUP_DESCRIPTION],
                   COMPANY_FILTER,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   VERSION_NO,
                   [ACTION_FLAG])
      SELECT [COMPANY_GROUP_SID],
             [COMPANY_GROUP_NO],
             [COMPANY_GROUP_NAME],
             [COMPANY_GROUP_DESCRIPTION],
             COMPANY_FILTER,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             VERSION_NO,
             'D'
      FROM   DELETED
  END

GO

-----------------------COMPANY_GROUP_DETAILS----------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'COMPANY_GROUP_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[COMPANY_GROUP_DETAILS]
        (
           [COMPANY_GROUP_DETAILS_SID]  INT IDENTITY(1, 1) NOT NULL,
           [COMPANY_GROUP_SID]          INT NULL,
           [COMPANY_MASTER_SID]         INT NULL,
           [COMPANY_TRADECLASS_SID]     INT NULL,
           [COMPANY_PARENT_DETAILS_SID] INT NULL,
           [CREATED_BY]                 INT NOT NULL,
           [CREATED_DATE]               DATETIME NOT NULL,
           [MODIFIED_BY]                INT NOT NULL,
           [MODIFIED_DATE]              DATETIME NOT NULL,
           [VERSION_NO]                 INT NOT NULL
        )
  END

GO

---------------------------default_constraint--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_GROUP_DETAILS')
                      AND NAME = 'DF_COMPANY_GROUP_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_GROUP_DETAILS]
        ADD CONSTRAINT [DF_COMPANY_GROUP_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_GROUP_DETAILS')
                      AND NAME = 'DF_COMPANY_GROUP_DETAILS_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_GROUP_DETAILS]
        ADD CONSTRAINT [DF_COMPANY_GROUP_DETAILS_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_GROUP_DETAILS')
                      AND NAME = 'DF_COMPANY_GROUP_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_GROUP_DETAILS]
        ADD CONSTRAINT [DF_COMPANY_GROUP_DETAILS_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_GROUP_DETAILS')
                      AND NAME = 'DF_COMPANY_GROUP_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_GROUP_DETAILS]
        ADD CONSTRAINT [DF_COMPANY_GROUP_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_GROUP_DETAILS')
                      AND NAME = 'DF_COMPANY_GROUP_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_GROUP_DETAILS]
        ADD CONSTRAINT [DF_COMPANY_GROUP_DETAILS_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

----------------------primary_key-----------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_COMPANY_GROUP_DETAILS_COMPANY_GROUP_DETAILS_SID'
                     AND TABLE_NAME = 'COMPANY_GROUP_DETAILS')
  ALTER TABLE COMPANY_GROUP_DETAILS
    ADD CONSTRAINT PK_COMPANY_GROUP_DETAILS_COMPANY_GROUP_DETAILS_SID PRIMARY KEY(COMPANY_GROUP_DETAILS_SID)

GO

------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'COMPANY_GROUP_DETAILS'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

--------------------------HIST_COMPANY_GROUP_DETAILS------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_COMPANY_GROUP_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_COMPANY_GROUP_DETAILS]
        (
           [COMPANY_GROUP_DETAILS_SID]  INT NOT NULL,
           [COMPANY_GROUP_SID]          INT NULL,
           [COMPANY_MASTER_SID]         INT NULL,
           [COMPANY_TRADECLASS_SID]     INT NULL,
           [COMPANY_PARENT_DETAILS_SID] INT NULL,
           [CREATED_BY]                 INT NOT NULL,
           [CREATED_DATE]               DATETIME NOT NULL,
           [MODIFIED_BY]                INT NOT NULL,
           [MODIFIED_DATE]              DATETIME NOT NULL,
           [VERSION_NO]                 INT NOT NULL,
           [ACTION_FLAG]                CHAR(1) NOT NULL,
           [ACTION_DATE]                DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_COMPANY_GROUP_DETAILS')
                      AND NAME = 'DF_HIST_COMPANY_GROUP_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_COMPANY_GROUP_DETAILS]
        ADD CONSTRAINT [DF_HIST_COMPANY_GROUP_DETAILS_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_COMPANY_GROUP_DETAILS')
                      AND NAME = 'PK_HIST_COMPANY_GROUP_DETAILS_COMPANY_GROUP_DETAILS_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_COMPANY_GROUP_DETAILS]
        ADD CONSTRAINT PK_HIST_COMPANY_GROUP_DETAILS_COMPANY_GROUP_DETAILS_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (COMPANY_GROUP_DETAILS_SID, ACTION_FLAG, VERSION_NO)
  END

GO

------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_COMPANY_GROUP_DETAILS'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

------------------------trigger-------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_COMPANY_GROUP_DETAILS_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_COMPANY_GROUP_DETAILS_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_COMPANY_GROUP_DETAILS_UPD]
ON [DBO].COMPANY_GROUP_DETAILS
--INSTEAD OF UPDATE
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      --UPDATE A
      --SET    A.[COMPANY_GROUP_SID] = B.[COMPANY_GROUP_SID],
      --       A.[COMPANY_MASTER_SID] = B.[COMPANY_MASTER_SID],
      --       A.[COMPANY_TRADECLASS_SID] = B.[COMPANY_TRADECLASS_SID],
      --       A.[COMPANY_PARENT_DETAILS_SID] = B.[COMPANY_PARENT_DETAILS_SID],
      --       A.[CREATED_BY] = B.[CREATED_BY],
      --       A.[CREATED_DATE] = B.[CREATED_DATE],
      --       A.[MODIFIED_BY] = B.[MODIFIED_BY],
      --       A.[MODIFIED_DATE] = B.[MODIFIED_DATE],
      --       A.[VERSION_NO] = A.[VERSION_NO] + 1
      --FROM   COMPANY_GROUP_DETAILS A
      --       JOIN (SELECT *
      --             FROM   INSERTED
      --             EXCEPT
      --             SELECT *
      --             FROM   DELETED) B ON B.[COMPANY_GROUP_DETAILS_SID] = A.[COMPANY_GROUP_DETAILS_SID]
      --INSERT INTO HIST_COMPANY_GROUP_DETAILS
      --            ([COMPANY_GROUP_DETAILS_SID],
      --             [COMPANY_GROUP_SID],
      --             [COMPANY_MASTER_SID],
      --             [COMPANY_TRADECLASS_SID],
      --             [COMPANY_PARENT_DETAILS_SID],
      --             [CREATED_BY],
      --             [CREATED_DATE],
      --             [MODIFIED_BY],
      --             [MODIFIED_DATE],
      --             [VERSION_NO],
      --             [ACTION_FLAG])
      --SELECT [COMPANY_GROUP_DETAILS_SID],
      --       [COMPANY_GROUP_SID],
      --       [COMPANY_MASTER_SID],
      --       [COMPANY_TRADECLASS_SID],
      --       [COMPANY_PARENT_DETAILS_SID],
      --       [CREATED_BY],
      --       [CREATED_DATE],
      --       [MODIFIED_BY],
      --       [MODIFIED_DATE],
      --       [VERSION_NO]+1,
      --       'C'
      --FROM   INSERTED I
      --WHERE  EXISTS (SELECT 1
      --               FROM   (SELECT *
      --                       FROM   INSERTED
      --                       EXCEPT
      --                       SELECT *
      --                       FROM   DELETED) A
      --               WHERE  A.[COMPANY_GROUP_DETAILS_SID] = I.[COMPANY_GROUP_DETAILS_SID])
      INSERT INTO HIST_COMPANY_GROUP_DETAILS
                  ([COMPANY_GROUP_DETAILS_SID],
                   [COMPANY_GROUP_SID],
                   [COMPANY_MASTER_SID],
                   [COMPANY_TRADECLASS_SID],
                   [COMPANY_PARENT_DETAILS_SID],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT [COMPANY_GROUP_DETAILS_SID],
             [COMPANY_GROUP_SID],
             [COMPANY_MASTER_SID],
             [COMPANY_TRADECLASS_SID],
             [COMPANY_PARENT_DETAILS_SID],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_COMPANY_GROUP_DETAILS_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_COMPANY_GROUP_DETAILS_INS]
  END

GO

CREATE TRIGGER [DBO].[TRG_COMPANY_GROUP_DETAILS_INS]
ON [DBO].COMPANY_GROUP_DETAILS
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_COMPANY_GROUP_DETAILS
                  ([COMPANY_GROUP_DETAILS_SID],
                   [COMPANY_GROUP_SID],
                   [COMPANY_MASTER_SID],
                   [COMPANY_TRADECLASS_SID],
                   [COMPANY_PARENT_DETAILS_SID],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT [COMPANY_GROUP_DETAILS_SID],
             [COMPANY_GROUP_SID],
             [COMPANY_MASTER_SID],
             [COMPANY_TRADECLASS_SID],
             [COMPANY_PARENT_DETAILS_SID],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_COMPANY_GROUP_DETAILS_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_COMPANY_GROUP_DETAILS_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_COMPANY_GROUP_DETAILS_DEL]
ON [DBO].COMPANY_GROUP_DETAILS
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_COMPANY_GROUP_DETAILS
                  ([COMPANY_GROUP_DETAILS_SID],
                   [COMPANY_GROUP_SID],
                   [COMPANY_MASTER_SID],
                   [COMPANY_TRADECLASS_SID],
                   [COMPANY_PARENT_DETAILS_SID],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT [COMPANY_GROUP_DETAILS_SID],
             [COMPANY_GROUP_SID],
             [COMPANY_MASTER_SID],
             [COMPANY_TRADECLASS_SID],
             [COMPANY_PARENT_DETAILS_SID],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'D'
      FROM   DELETED
  END

GO

------------------------------DEDUCTION_GROUP-----------------------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'DEDUCTION_GROUP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[DEDUCTION_GROUP]
        (
           DEDUCTION_GROUP_SID         INT IDENTITY(1, 1) NOT NULL,
           DEDUCTION_GROUP_NO          VARCHAR(30) NULL,
           DEDUCTION_GROUP_NAME        VARCHAR(30) NULL,
           DEDUCTION_GROUP_DESCRIPTION VARCHAR(30) NULL,
           DEDUCTION_FILTER            VARCHAR(400) NULL,
           CREATED_BY                  INT NOT NULL,
           CREATED_DATE                DATETIME NOT NULL,
           MODIFIED_BY                 INT NOT NULL,
           MODIFIED_DATE               DATETIME NOT NULL,
           VERSION_NO                  INT NOT NULL
        )
  END

--------------primary_key-----------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'DEDUCTION_GROUP'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_DEDUCTION_GROUP_DEDUCTION_GROUP_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[DEDUCTION_GROUP]
        ADD CONSTRAINT PK_DEDUCTION_GROUP_DEDUCTION_GROUP_SID PRIMARY KEY(DEDUCTION_GROUP_SID)
  END

GO

----------------default_constraint--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEDUCTION_GROUP')
                      AND NAME = 'DF_DEDUCTION_GROUP_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[DEDUCTION_GROUP]
        ADD CONSTRAINT [DF_DEDUCTION_GROUP_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEDUCTION_GROUP')
                      AND NAME = 'DF_DEDUCTION_GROUP_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[DEDUCTION_GROUP]
        ADD CONSTRAINT [DF_DEDUCTION_GROUP_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEDUCTION_GROUP')
                      AND NAME = 'DF_DEDUCTION_GROUP_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[DEDUCTION_GROUP]
        ADD CONSTRAINT [DF_DEDUCTION_GROUP_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEDUCTION_GROUP')
                      AND NAME = 'DF_DEDUCTION_GROUP_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[DEDUCTION_GROUP]
        ADD CONSTRAINT [DF_DEDUCTION_GROUP_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEDUCTION_GROUP')
                      AND NAME = 'DF_DEDUCTION_GROUP_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[DEDUCTION_GROUP]
        ADD CONSTRAINT [DF_DEDUCTION_GROUP_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

----------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'DEDUCTION_GROUP'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

--------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_DEDUCTION_GROUP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_DEDUCTION_GROUP]
        (
           DEDUCTION_GROUP_SID         INT NOT NULL,
           DEDUCTION_GROUP_NO          VARCHAR(30) NULL,
           DEDUCTION_GROUP_NAME        VARCHAR(30) NULL,
           DEDUCTION_GROUP_DESCRIPTION VARCHAR(30) NULL,
           DEDUCTION_FILTER            VARCHAR(400) NULL,
           CREATED_BY                  INT NOT NULL,
           CREATED_DATE                DATETIME NOT NULL,
           MODIFIED_BY                 INT NOT NULL,
           MODIFIED_DATE               DATETIME NOT NULL,
           VERSION_NO                  INT NOT NULL,
           [ACTION_FLAG]               CHAR(1) NOT NULL,
           [ACTION_DATE]               DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_DEDUCTION_GROUP')
                      AND NAME = 'DF_HIST_DEDUCTION_GROUP_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_DEDUCTION_GROUP]
        ADD CONSTRAINT [DF_HIST_DEDUCTION_GROUP_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_DEDUCTION_GROUP')
                      AND NAME = 'PK_HIST_DEDUCTION_GROUP_DEDUCTION_GROUP_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_DEDUCTION_GROUP]
        ADD CONSTRAINT PK_HIST_DEDUCTION_GROUP_DEDUCTION_GROUP_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (DEDUCTION_GROUP_SID, ACTION_FLAG, VERSION_NO)
  END

GO

----------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_DEDUCTION_GROUP'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

-------------trigger-------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEDUCTION_GROUP_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_DEDUCTION_GROUP_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_DEDUCTION_GROUP_UPD]
ON [DBO].DEDUCTION_GROUP
--INSTEAD OF UPDATE
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEDUCTION_GROUP
                  ([DEDUCTION_GROUP_SID],
                   [DEDUCTION_GROUP_NO],
                   [DEDUCTION_GROUP_NAME],
                   [DEDUCTION_GROUP_DESCRIPTION],
                   [DEDUCTION_FILTER],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT [DEDUCTION_GROUP_SID],
             [DEDUCTION_GROUP_NO],
             [DEDUCTION_GROUP_NAME],
             [DEDUCTION_GROUP_DESCRIPTION],
             [DEDUCTION_FILTER],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             VERSION_NO,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEDUCTION_GROUP_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_DEDUCTION_GROUP_INS]
  END

GO

CREATE TRIGGER [DBO].[TRG_DEDUCTION_GROUP_INS]
ON [DBO].DEDUCTION_GROUP
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEDUCTION_GROUP
                  ([DEDUCTION_GROUP_SID],
                   [DEDUCTION_GROUP_NO],
                   [DEDUCTION_GROUP_NAME],
                   [DEDUCTION_GROUP_DESCRIPTION],
                   [DEDUCTION_FILTER],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT [DEDUCTION_GROUP_SID],
             [DEDUCTION_GROUP_NO],
             [DEDUCTION_GROUP_NAME],
             [DEDUCTION_GROUP_DESCRIPTION],
             [DEDUCTION_FILTER],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             VERSION_NO,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEDUCTION_GROUP_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_DEDUCTION_GROUP_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_DEDUCTION_GROUP_DEL]
ON [DBO].DEDUCTION_GROUP
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEDUCTION_GROUP
                  ([DEDUCTION_GROUP_SID],
                   [DEDUCTION_GROUP_NO],
                   [DEDUCTION_GROUP_NAME],
                   [DEDUCTION_GROUP_DESCRIPTION],
                   [DEDUCTION_FILTER],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT [DEDUCTION_GROUP_SID],
             [DEDUCTION_GROUP_NO],
             [DEDUCTION_GROUP_NAME],
             [DEDUCTION_GROUP_DESCRIPTION],
             [DEDUCTION_FILTER],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             VERSION_NO,
             'D'
      FROM   DELETED
  END

GO

----------------------DEDUCTION_GROUP_DETAILS---------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'DEDUCTION_GROUP_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[DEDUCTION_GROUP_DETAILS]
        (
           DEDUCTION_GROUP_DETAILS_SID INT IDENTITY(1, 1) NOT NULL,
           DEDUCTION_GROUP_SID         INT NULL,
           RS_MODEL_SID                INT NULL,
           CREATED_BY                  INT NOT NULL,
           CREATED_DATE                DATETIME NOT NULL,
           MODIFIED_BY                 INT NOT NULL,
           MODIFIED_DATE               DATETIME NOT NULL,
           VERSION_NO                  INT NOT NULL
        )
  END

---------------------------primary_key-----------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'DEDUCTION_GROUP_DETAILS'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_DEDUCTION_GROUP_DETAILS_DEDUCTION_GROUP_DETAILS_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[DEDUCTION_GROUP_DETAILS]
        ADD CONSTRAINT PK_DEDUCTION_GROUP_DETAILS_DEDUCTION_GROUP_DETAILS_SID PRIMARY KEY(DEDUCTION_GROUP_DETAILS_SID)
  END

GO

-------------------------default_constraint--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEDUCTION_GROUP_DETAILS')
                      AND NAME = 'DF_DEDUCTION_GROUP_DETAILS_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[DEDUCTION_GROUP_DETAILS]
        ADD CONSTRAINT [DF_DEDUCTION_GROUP_DETAILS_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEDUCTION_GROUP_DETAILS')
                      AND NAME = 'DF_DEDUCTION_GROUP_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[DEDUCTION_GROUP_DETAILS]
        ADD CONSTRAINT [DF_DEDUCTION_GROUP_DETAILS_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEDUCTION_GROUP_DETAILS')
                      AND NAME = 'DF_DEDUCTION_GROUP_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[DEDUCTION_GROUP_DETAILS]
        ADD CONSTRAINT [DF_DEDUCTION_GROUP_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEDUCTION_GROUP_DETAILS')
                      AND NAME = 'DF_DEDUCTION_GROUP_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[DEDUCTION_GROUP_DETAILS]
        ADD CONSTRAINT [DF_DEDUCTION_GROUP_DETAILS_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.DEDUCTION_GROUP_DETAILS')
                      AND NAME = 'DF_DEDUCTION_GROUP_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[DEDUCTION_GROUP_DETAILS]
        ADD CONSTRAINT [DF_DEDUCTION_GROUP_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

----------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'DEDUCTION_GROUP_DETAILS'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

--------------------HIST_DEDUCTION_GROUP_DETAILS--------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_DEDUCTION_GROUP_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_DEDUCTION_GROUP_DETAILS]
        (
           DEDUCTION_GROUP_DETAILS_SID INT NOT NULL,
           DEDUCTION_GROUP_SID         INT NULL,
           RS_MODEL_SID                INT NULL,
           CREATED_BY                  INT NOT NULL,
           CREATED_DATE                DATETIME NOT NULL,
           MODIFIED_BY                 INT NOT NULL,
           MODIFIED_DATE               DATETIME NOT NULL,
           VERSION_NO                  INT NOT NULL,
           [ACTION_FLAG]               CHAR(1) NOT NULL,
           [ACTION_DATE]               DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_DEDUCTION_GROUP_DETAILS')
                      AND NAME = 'DF_HIST_DEDUCTION_GROUP_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_DEDUCTION_GROUP_DETAILS]
        ADD CONSTRAINT [DF_HIST_DEDUCTION_GROUP_DETAILS_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_DEDUCTION_GROUP_DETAILS')
                      AND NAME = 'PK_HIST_DEDUCTION_GROUP_DETAILS_DEDUCTION_GROUP_DETAILS_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_DEDUCTION_GROUP_DETAILS]
        ADD CONSTRAINT PK_HIST_DEDUCTION_GROUP_DETAILS_DEDUCTION_GROUP_DETAILS_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (DEDUCTION_GROUP_DETAILS_SID, ACTION_FLAG, VERSION_NO)
  END

GO

----------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_DEDUCTION_GROUP_DETAILS'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

-------------------------trigger-------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEDUCTION_GROUP_DETAILS_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_DEDUCTION_GROUP_DETAILS_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_DEDUCTION_GROUP_DETAILS_UPD]
ON [DBO].DEDUCTION_GROUP_DETAILS
--INSTEAD OF UPDATE
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEDUCTION_GROUP_DETAILS
                  ([DEDUCTION_GROUP_DETAILS_SID],
                   [DEDUCTION_GROUP_SID],
                   [RS_MODEL_SID],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT [DEDUCTION_GROUP_DETAILS_SID],
             [DEDUCTION_GROUP_SID],
             [RS_MODEL_SID],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             VERSION_NO,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEDUCTION_GROUP_DETAILS_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_DEDUCTION_GROUP_DETAILS_INS]
  END

GO

CREATE TRIGGER [DBO].[TRG_DEDUCTION_GROUP_DETAILS_INS]
ON [DBO].DEDUCTION_GROUP_DETAILS
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEDUCTION_GROUP_DETAILS
                  ([DEDUCTION_GROUP_DETAILS_SID],
                   [DEDUCTION_GROUP_SID],
                   [RS_MODEL_SID],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT [DEDUCTION_GROUP_DETAILS_SID],
             [DEDUCTION_GROUP_SID],
             [RS_MODEL_SID],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             VERSION_NO,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_DEDUCTION_GROUP_DETAILS_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_DEDUCTION_GROUP_DETAILS_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_DEDUCTION_GROUP_DETAILS_DEL]
ON [DBO].DEDUCTION_GROUP_DETAILS
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_DEDUCTION_GROUP_DETAILS
                  ([DEDUCTION_GROUP_DETAILS_SID],
                   [DEDUCTION_GROUP_SID],
                   [RS_MODEL_SID],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT [DEDUCTION_GROUP_DETAILS_SID],
             [DEDUCTION_GROUP_SID],
             [RS_MODEL_SID],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             VERSION_NO,
             'D'
      FROM   DELETED
  END

GO

-----------------------------FILE_MANAGEMENT------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'FILE_MANAGEMENT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[FILE_MANAGEMENT]
        (
           [FILE_MANAGEMENT_SID] INT IDENTITY(1, 1) NOT NULL,
           [FORECAST_NAME]       VARCHAR(50) NULL,
           [FORECAST_SOURCE]     VARCHAR(100) NULL,
           [VERSION]             VARCHAR(15) NULL,
           [COUNTRY]             INT NULL,
           [FILE_TYPE]           VARCHAR(50) NULL,
           [FROM_PERIOD]         DATETIME NULL,
           [TO_PERIOD]           DATETIME NULL,
           [CREATED_BY]          INT NOT NULL,
           [CREATED_DATE]        DATETIME NOT NULL,
           [MODIFIED_BY]         INT NOT NULL,
           [MODIFIED_DATE]       DATETIME NOT NULL,
           [VERSION_NO]          INT NOT NULL
        )
  END

GO

--------------------------------DROP_STATS-----------------------------
--------------------------------FILE_MANAGEMENT------------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'FILE_MANAGEMENT'
                  AND AUTO_CREATED = 0
                  AND NAME = 'FILE_TYPE')
  BEGIN
      DROP STATISTICS FILE_MANAGEMENT.FILE_TYPE
  END

GO

--RENAME
--------------------------------FILE_MANAGEMENT------------------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'FILE_MANAGEMENT'
                  AND COLUMN_NAME = 'FILE_TYPE'
                  AND TABLE_SCHEMA = 'DBO'
				  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      EXEC Sp_rename
        'FILE_MANAGEMENT.FILE_TYPE',
        'FILE_SOURCE',
        'COLUMN'
  END

GO


--ADD_COLUMS
---------------------------------FILE_MANAGEMENT---------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FILE_MANAGEMENT'
                      AND COLUMN_NAME = 'FILE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE [DBO].[FILE_MANAGEMENT]
        ADD FILE_TYPE INT NULL
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FILE_MANAGEMENT'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE [DBO].[FILE_MANAGEMENT]
        ADD BUSINESS_UNIT INT NULL
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FILE_MANAGEMENT'
                      AND COLUMN_NAME = 'COMPANY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE FILE_MANAGEMENT
        ADD  COMPANY INT 
  END

GO


-----------------------------default_constraint--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.FILE_MANAGEMENT')
                      AND NAME = 'DF_FILE_MANAGEMENT_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[FILE_MANAGEMENT]
        ADD CONSTRAINT [DF_FILE_MANAGEMENT_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.FILE_MANAGEMENT')
                      AND NAME = 'DF_FILE_MANAGEMENT_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[FILE_MANAGEMENT]
        ADD CONSTRAINT [DF_FILE_MANAGEMENT_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.FILE_MANAGEMENT')
                      AND NAME = 'DF_FILE_MANAGEMENT_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[FILE_MANAGEMENT]
        ADD CONSTRAINT [DF_FILE_MANAGEMENT_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.FILE_MANAGEMENT')
                      AND NAME = 'DF_FILE_MANAGEMENT_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[FILE_MANAGEMENT]
        ADD CONSTRAINT [DF_FILE_MANAGEMENT_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.FILE_MANAGEMENT')
                      AND NAME = 'DF_FILE_MANAGEMENT_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[FILE_MANAGEMENT]
        ADD CONSTRAINT [DF_FILE_MANAGEMENT_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

---------------------------primary_key-----------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_FILE_MANAGEMENT_FILE_MANAGEMENT_SID'
                     AND TABLE_NAME = 'FILE_MANAGEMENT')

BEGIN
  ALTER TABLE FILE_MANAGEMENT
    ADD CONSTRAINT PK_FILE_MANAGEMENT_FILE_MANAGEMENT_SID PRIMARY KEY(FILE_MANAGEMENT_SID)
END

GO

---------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'FILE_MANAGEMENT'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

----------------------HIST_FILE_MANAGEMENT-------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_FILE_MANAGEMENT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_FILE_MANAGEMENT]
        (
           [FILE_MANAGEMENT_SID] INT NOT NULL,
           [FORECAST_NAME]       VARCHAR(50) NULL,
           [FORECAST_SOURCE]     VARCHAR(100) NULL,
           [VERSION]             VARCHAR(15) NULL,
           [COUNTRY]             INT NULL,
           [FILE_TYPE]           VARCHAR(50) NULL,
           [FROM_PERIOD]         DATETIME NULL,
           [TO_PERIOD]           DATETIME NULL,
           [CREATED_BY]          INT NOT NULL,
           [CREATED_DATE]        DATETIME NOT NULL,
           [MODIFIED_BY]         INT NOT NULL,
           [MODIFIED_DATE]       DATETIME NOT NULL,
           [VERSION_NO]          INT NOT NULL,
           [ACTION_FLAG]         CHAR(1) NOT NULL,
           [ACTION_DATE]         DATETIME NOT NULL
        )
  END

GO


--------------------------------HIST_FILE_MANAGEMENT-----------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'HIST_FILE_MANAGEMENT'
                  AND AUTO_CREATED = 0
                  AND NAME = 'FILE_TYPE')
  BEGIN
      DROP STATISTICS HIST_FILE_MANAGEMENT.FILE_TYPE
  END

GO
-----------------------HIST_FILE_MANAGEMENT---------------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_FILE_MANAGEMENT'
                  AND COLUMN_NAME = 'FILE_TYPE'
                  AND TABLE_SCHEMA = 'DBO'
				  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      EXEC Sp_rename
        'HIST_FILE_MANAGEMENT.FILE_TYPE',
        'FILE_SOURCE',
        'COLUMN'
  END

GO
---------------------HIST_FILE_MANAGEMENT---------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_FILE_MANAGEMENT'
                      AND COLUMN_NAME = 'FILE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE [DBO].[HIST_FILE_MANAGEMENT]
        ADD FILE_TYPE INT NULL
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_FILE_MANAGEMENT'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE [DBO].[HIST_FILE_MANAGEMENT]
        ADD BUSINESS_UNIT INT NULL
  END

GO


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_FILE_MANAGEMENT'
                      AND COLUMN_NAME = 'COMPANY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_FILE_MANAGEMENT
        ADD  COMPANY INT 
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_FILE_MANAGEMENT')
                      AND NAME = 'DF_HIST_FILE_MANAGEMENT_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_FILE_MANAGEMENT]
        ADD CONSTRAINT [DF_HIST_FILE_MANAGEMENT_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_FILE_MANAGEMENT')
                      AND NAME = 'PK_HIST_FILE_MANAGEMENT_FILE_MANAGEMENT_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_FILE_MANAGEMENT]
        ADD CONSTRAINT PK_HIST_FILE_MANAGEMENT_FILE_MANAGEMENT_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (FILE_MANAGEMENT_SID, ACTION_FLAG, VERSION_NO)
  END

GO

---------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_FILE_MANAGEMENT'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

----------------------------------TRIGGER------------------------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_FILE_MANAGEMENT_UPD')
  BEGIN
      DROP TRIGGER dbo.[TRG_FILE_MANAGEMENT_UPD]
  END

GO

CREATE TRIGGER [dbo].[TRG_FILE_MANAGEMENT_UPD]
ON [dbo].[FILE_MANAGEMENT]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
         AND EXISTS (SELECT *
                     FROM   DELETED)
        INSERT INTO HIST_FILE_MANAGEMENT
                    (FILE_MANAGEMENT_SID,
                     FORECAST_NAME,
                     FORECAST_SOURCE,
                     VERSION,
                     COUNTRY,
                     FILE_TYPE,
                     FILE_SOURCE,
                     FROM_PERIOD,
                     TO_PERIOD,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     VERSION_NO,
                     ACTION_FLAG,
					 BUSINESS_UNIT
					 ,COMPANY)
        SELECT FILE_MANAGEMENT_SID,
               FORECAST_NAME,
               FORECAST_SOURCE,
               VERSION,
               COUNTRY,
               FILE_TYPE,
               FILE_SOURCE,
               FROM_PERIOD,
               TO_PERIOD,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               VERSION_NO,
               'C',
			   BUSINESS_UNIT,
			   COMPANY
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_FILE_MANAGEMENT_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_FILE_MANAGEMENT_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_FILE_MANAGEMENT_INS]
ON [dbo].[FILE_MANAGEMENT]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
        INSERT INTO HIST_FILE_MANAGEMENT
                    (FILE_MANAGEMENT_SID,
                     FORECAST_NAME,
                     FORECAST_SOURCE,
                     VERSION,
                     COUNTRY,
                     FILE_TYPE,
                     FILE_SOURCE,
                     FROM_PERIOD,
                     TO_PERIOD,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     VERSION_NO,
                     ACTION_FLAG,
					 BUSINESS_UNIT,
					 COMPANY)
        SELECT FILE_MANAGEMENT_SID,
               FORECAST_NAME,
               FORECAST_SOURCE,
               VERSION,
               COUNTRY,
               FILE_TYPE,
               FILE_SOURCE,
               FROM_PERIOD,
               TO_PERIOD,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               VERSION_NO,
               'A',
			   BUSINESS_UNIT
			   ,COMPANY
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_FILE_MANAGEMENT_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_FILE_MANAGEMENT_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_FILE_MANAGEMENT_DEL]
ON [dbo].[FILE_MANAGEMENT]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   DELETED)
        INSERT INTO HIST_FILE_MANAGEMENT
                    (FILE_MANAGEMENT_SID,
                     FORECAST_NAME,
                     FORECAST_SOURCE,
                     VERSION,
                     COUNTRY,
                     FILE_TYPE,
                     FILE_SOURCE,
                     FROM_PERIOD,
                     TO_PERIOD,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     VERSION_NO,
                     ACTION_FLAG,
					 BUSINESS_UNIT
					 ,COMPANY)
        SELECT FILE_MANAGEMENT_SID,
               FORECAST_NAME,
               FORECAST_SOURCE,
               VERSION,
               COUNTRY,
               FILE_TYPE,
               FILE_SOURCE,
               FROM_PERIOD,
               TO_PERIOD,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               VERSION_NO,
               'D',
			   BUSINESS_UNIT
			   ,COMPANY
        FROM   DELETED
  END

GO 




---------------------------HIERARCHY_DEFINITION-----------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIERARCHY_DEFINITION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIERARCHY_DEFINITION]
        (
           [HIERARCHY_DEFINITION_SID] INT IDENTITY(1, 1) NOT NULL,
           [HIERARCHY_NAME]           VARCHAR(100) NULL,
           [HIERARCHY_TYPE]           INT NULL,
           [HIERARCHY_CATEGORY]       INT NULL,
           [NO_OF_LEVELS]             INT NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL,
           [VERSION_NO]               INT NOT NULL
        )
  END

GO

-----------------------------------default_constraint--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_DEFINITION')
                      AND NAME = 'DF_HIERARCHY_DEFINITION_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_DEFINITION_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_DEFINITION')
                      AND NAME = 'DF_HIERARCHY_DEFINITION_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_DEFINITION_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_DEFINITION')
                      AND NAME = 'DF_HIERARCHY_DEFINITION_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_DEFINITION_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_DEFINITION')
                      AND NAME = 'DF_HIERARCHY_DEFINITION_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_DEFINITION_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_DEFINITION')
                      AND NAME = 'DF_HIERARCHY_DEFINITION_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_DEFINITION_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

--------------primary_key-----------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_HIERARCHY_DEFINITION_HIERARCHY_DEFINITION_SID'
                     AND TABLE_NAME = 'HIERARCHY_DEFINITION')
  ALTER TABLE HIERARCHY_DEFINITION
    ADD CONSTRAINT PK_HIERARCHY_DEFINITION_HIERARCHY_DEFINITION_SID PRIMARY KEY(HIERARCHY_DEFINITION_SID)

GO

---------------------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIERARCHY_DEFINITION'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

---------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_HIERARCHY_DEFINITION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_HIERARCHY_DEFINITION]
        (
           [HIERARCHY_DEFINITION_SID] INT NOT NULL,
           [HIERARCHY_NAME]           VARCHAR(100) NULL,
           [HIERARCHY_TYPE]           INT NULL,
           [HIERARCHY_CATEGORY]       INT NULL,
           [NO_OF_LEVELS]             INT NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL,
           [VERSION_NO]               INT NOT NULL,
           [ACTION_FLAG]              CHAR(1) NOT NULL,
           [ACTION_DATE]              DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_HIERARCHY_DEFINITION')
                      AND NAME = 'DF_HIST_HIERARCHY_DEFINITION_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_HIERARCHY_DEFINITION]
        ADD CONSTRAINT [DF_HIST_HIERARCHY_DEFINITION_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_HIERARCHY_DEFINITION')
                      AND NAME = 'PK_HIST_HIERARCHY_DEFINITION_HIERARCHY_DEFINITION_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_HIERARCHY_DEFINITION]
        ADD CONSTRAINT PK_HIST_HIERARCHY_DEFINITION_HIERARCHY_DEFINITION_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (HIERARCHY_DEFINITION_SID, ACTION_FLAG, VERSION_NO)
  END

GO

---------------------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_HIERARCHY_DEFINITION'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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
--------------------------------------------------Hierarchy Route Builder
--IF NOT EXISTS (SELECT
--    'X'
--  FROM INFORMATION_SCHEMA.TABLES
--  WHERE TABLE_NAME = 'HIERARCHY_ENTITY_MASTER'
--  AND TABLE_SCHEMA = 'dbo')
--BEGIN
--  CREATE
--  TABLE
--  HIERARCHY_ENTITY_MASTER (
--    ENTITY_ID int IDENTITY (
--    1,
--    1
--    ) NOT NULL PRIMARY KEY,
--    ENTITY_NAME varchar(100) NOT NULL,
--    HIERARCHY_TABLE_MASTER_SID int NOT NULL,
--  )
--END
--GO
--IF NOT EXISTS (SELECT
--    1
--  FROM HIERARCHY_ENTITY_MASTER)
--BEGIN
--  INSERT INTO HIERARCHY_ENTITY_MASTER (ENTITY_NAME,
--  HIERARCHY_TABLE_MASTER_SID)
--    VALUES ('Company', 4);

--  INSERT INTO HIERARCHY_ENTITY_MASTER (ENTITY_NAME,
--  HIERARCHY_TABLE_MASTER_SID)
--    VALUES ('Contract', 1);

--  INSERT INTO HIERARCHY_ENTITY_MASTER (ENTITY_NAME,
--  HIERARCHY_TABLE_MASTER_SID)
--    VALUES ('Company_Identifier', 5);

--  INSERT INTO HIERARCHY_ENTITY_MASTER (ENTITY_NAME,
--  HIERARCHY_TABLE_MASTER_SID)
--    VALUES ('Company_parent', 6);

--  INSERT INTO HIERARCHY_ENTITY_MASTER (ENTITY_NAME,
--  HIERARCHY_TABLE_MASTER_SID)
--    VALUES ('Company_trade_class', 7);

--  INSERT INTO HIERARCHY_ENTITY_MASTER (ENTITY_NAME,
--  HIERARCHY_TABLE_MASTER_SID)
--    VALUES ('ITEM', 12);

--  INSERT INTO HIERARCHY_ENTITY_MASTER (ENTITY_NAME,
--  HIERARCHY_TABLE_MASTER_SID)
--    VALUES ('COMPANY', 8);

--  INSERT INTO HIERARCHY_ENTITY_MASTER (ENTITY_NAME,
--  HIERARCHY_TABLE_MASTER_SID)
--    VALUES ('ITEM_IDENTIFIER', 10);

--  INSERT INTO HIERARCHY_ENTITY_MASTER (ENTITY_NAME,
--  HIERARCHY_TABLE_MASTER_SID)
--    VALUES ('BRAND_MASTERR', 11);
--END
--GO 

--IF EXISTS (SELECT
--    NAME
--  FROM SYS.TABLES
--  WHERE NAME = 'HIERARCHY_ENTITY_MASTER')
--BEGIN
--  IF NOT EXISTS (SELECT
--      1
--    FROM SYS.KEY_CONSTRAINTS
--    WHERE TYPE_DESC = 'UNIQUE_CONSTRAINT'
--    AND PARENT_OBJECT_ID = OBJECT_ID('HIERARCHY_ENTITY_MASTER')
--    AND NAME = 'UQ_HIERARCHY_ENTITY_MASTER_HIERARCHY_TABLE_MASTER_SID')
--  BEGIN
--    ALTER TABLE HIERARCHY_ENTITY_MASTER
--    ADD CONSTRAINT UQ_HIERARCHY_ENTITY_MASTER_HIERARCHY_TABLE_MASTER_SID UNIQUE (HIERARCHY_TABLE_MASTER_SID)
--  END
--END
--GO 
-----------------------------------------statistics-----------------------
--DECLARE @SQL NVARCHAR(MAX)
--DECLARE @TABLENAME VARCHAR(100)
--DECLARE @STATSNAME VARCHAR(200)
--DECLARE @TABLENAME1 VARCHAR(100)
--DECLARE @SCHEMANAME VARCHAR(30)
--DECLARE @SCHEMANAME1 VARCHAR(30)

--SET @TABLENAME1 = 'HIERARCHY_ENTITY_MASTER'--TABLE NAME
--SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
--IF EXISTS (SELECT 'X'
--           FROM   SYS.STATS S
--                  JOIN SYS.TABLES T
--                    ON S.OBJECT_ID = T.OBJECT_ID
--           WHERE  AUTO_CREATED = 1
--                  AND NOT EXISTS (SELECT 1
--                                  FROM   SYS.INDEXES
--                                  WHERE  S.NAME = NAME)
--                  AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
--                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
--  BEGIN
--      DECLARE CUR CURSOR STATIC FOR
--        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
--               S.NAME                   AS 'STATSNAME',
--               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
--        FROM   SYS.STATS S
--               JOIN SYS.TABLES T
--                 ON S.OBJECT_ID = T.OBJECT_ID
--        WHERE  AUTO_CREATED = 1
--               AND NOT EXISTS (SELECT 1
--                               FROM   SYS.INDEXES
--                               WHERE  S.NAME = NAME)
--               AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
--               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

--      OPEN CUR

--      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

--      WHILE @@FETCH_STATUS = 0
--        BEGIN
--            SET @SQL = 'DROP STATISTICS ' + Quotename(@SCHEMANAME)
--                       + '.' + Quotename(@TABLENAME) + '.'
--                       + Quotename(@STATSNAME)

--            --PRINT @SQL
--            EXEC SP_EXECUTESQL
--              @SQL

--            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
--        END

--      CLOSE CUR

--      DEALLOCATE CUR
--  END

--DECLARE @STATS NVARCHAR(MAX)
--DECLARE CUR1 CURSOR STATIC FOR
--  SELECT 'CREATE STATISTICS ' + Quotename(C.NAME)
--         + ' ON ' + Quotename(SCHEMA_NAME(SCHEMA_ID))
--         + '.' + Quotename(T.NAME) + ' ('
--         + Quotename(C.NAME) + ') WITH FULLSCAN'
--  FROM   SYS.TABLES T
--         JOIN SYS.COLUMNS C
--           ON T.OBJECT_ID = C.OBJECT_ID
--  WHERE  NOT EXISTS (SELECT 1
--                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
--                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
--                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
--                     WHERE  CC.TABLE_NAME = T.NAME
--                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
--                            AND C.NAME = COLUMN_NAME)
--         AND NOT EXISTS (SELECT 1
--                         FROM   SYS.STATS S
--                         WHERE  S.OBJECT_ID = C.OBJECT_ID
--                                AND S.NAME = C.NAME)
--         AND T.NAME = @TABLENAME1 -- TABLE NAME
--         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
--  ORDER  BY T.NAME

--OPEN CUR1

--FETCH NEXT FROM CUR1 INTO @STATS

--WHILE @@FETCH_STATUS = 0
--  BEGIN
--      --PRINT @STATS
--      EXEC SP_EXECUTESQL
--        @STATS

--      FETCH NEXT FROM CUR1 INTO @STATS
--  END

--CLOSE CUR1

--DEALLOCATE CUR1

--GO


--IF NOT EXISTS (SELECT
--    'X'
--  FROM INFORMATION_SCHEMA.TABLES
--  WHERE TABLE_NAME = 'HIERARCHY_TABLE_MASTER'
--  AND TABLE_SCHEMA = 'dbo')
--BEGIN

--  CREATE TABLE HIERARCHY_TABLE_MASTER (
--    MASTER_TABLE_SID int IDENTITY (1, 1) NOT NULL PRIMARY KEY,
--    TABLE_NAME varchar(100) NOT NULL,
	
--  )
--END
--GO 

--	IF NOT EXISTS (SELECT 1
--               FROM   INFORMATION_SCHEMA.COLUMNS
--               WHERE  TABLE_NAME = 'HIERARCHY_TABLE_MASTER'
--                      AND COLUMN_NAME = 'COLUMN_NAME'
--					  AND TABLE_SCHEMA='DBO')
--  BEGIN
--      ALTER TABLE HIERARCHY_TABLE_MASTER
--        ADD COLUMN_NAME VARCHAR(100)
--  END
--GO


--	IF NOT EXISTS (SELECT 1
--               FROM   INFORMATION_SCHEMA.COLUMNS
--               WHERE  TABLE_NAME = 'HIERARCHY_TABLE_MASTER'
--                      AND COLUMN_NAME = 'VALUE'
--					  AND TABLE_SCHEMA='DBO')
--  BEGIN
--      ALTER TABLE HIERARCHY_TABLE_MASTER
--        ADD VALUE VARCHAR(100)
--  END
--GO


--IF NOT EXISTS (SELECT
--    1
--  FROM HIERARCHY_TABLE_MASTER)
--BEGIN
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('CONTRACT_MASTER');
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('CFP_CONTRACT');
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('CFP_CONTRACT_DETAILS');
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('COMPANY_MASTER');
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('COMPANY_IDENTIFIER');
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('COMPANY_PARENT_DETAILS');
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('COMPANY_TRADE_CLASS');
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('COMPANY_MASTER');
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('GL_COST_CENTER_MASTER');
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('ITEM_IDENTIFIER');
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('BRAND_MASTER');
--  INSERT INTO HIERARCHY_TABLE_MASTER (TABLE_NAME)
--    VALUES ('ITEM_MASTER');

--END
--GO 
--	IF  EXISTS (SELECT 1
--               FROM   HIERARCHY_TABLE_MASTER
--               WHERE 
--                       VALUE IS NULL
--					  AND COLUMN_NAME IS NULL)

-- BEGIN
-- UPDATE HIERARCHY_TABLE_MASTER
-- SET VALUE='D',COLUMN_NAME='INBOUND_STATUS'
-- WHERE   VALUE IS NULL
--					  AND COLUMN_NAME IS NULL
-- END
-- GO

-----------------------------------------statistics-----------------------
--DECLARE @SQL NVARCHAR(MAX)
--DECLARE @TABLENAME VARCHAR(100)
--DECLARE @STATSNAME VARCHAR(200)
--DECLARE @TABLENAME1 VARCHAR(100)
--DECLARE @SCHEMANAME VARCHAR(30)
--DECLARE @SCHEMANAME1 VARCHAR(30)

--SET @TABLENAME1 = 'HIERARCHY_TABLE_MASTER'--TABLE NAME
--SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
--IF EXISTS (SELECT 'X'
--           FROM   SYS.STATS S
--                  JOIN SYS.TABLES T
--                    ON S.OBJECT_ID = T.OBJECT_ID
--           WHERE  AUTO_CREATED = 1
--                  AND NOT EXISTS (SELECT 1
--                                  FROM   SYS.INDEXES
--                                  WHERE  S.NAME = NAME)
--                  AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
--                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
--  BEGIN
--      DECLARE CUR CURSOR STATIC FOR
--        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
--               S.NAME                   AS 'STATSNAME',
--               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
--        FROM   SYS.STATS S
--               JOIN SYS.TABLES T
--                 ON S.OBJECT_ID = T.OBJECT_ID
--        WHERE  AUTO_CREATED = 1
--               AND NOT EXISTS (SELECT 1
--                               FROM   SYS.INDEXES
--                               WHERE  S.NAME = NAME)
--               AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
--               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

--      OPEN CUR

--      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

--      WHILE @@FETCH_STATUS = 0
--        BEGIN
--            SET @SQL = 'DROP STATISTICS ' + Quotename(@SCHEMANAME)
--                       + '.' + Quotename(@TABLENAME) + '.'
--                       + Quotename(@STATSNAME)

--            --PRINT @SQL
--            EXEC SP_EXECUTESQL
--              @SQL

--            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
--        END

--      CLOSE CUR

--      DEALLOCATE CUR
--  END

--DECLARE @STATS NVARCHAR(MAX)
--DECLARE CUR1 CURSOR STATIC FOR
--  SELECT 'CREATE STATISTICS ' + Quotename(C.NAME)
--         + ' ON ' + Quotename(SCHEMA_NAME(SCHEMA_ID))
--         + '.' + Quotename(T.NAME) + ' ('
--         + Quotename(C.NAME) + ') WITH FULLSCAN'
--  FROM   SYS.TABLES T
--         JOIN SYS.COLUMNS C
--           ON T.OBJECT_ID = C.OBJECT_ID
--  WHERE  NOT EXISTS (SELECT 1
--                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
--                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
--                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
--                     WHERE  CC.TABLE_NAME = T.NAME
--                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
--                            AND C.NAME = COLUMN_NAME)
--         AND NOT EXISTS (SELECT 1
--                         FROM   SYS.STATS S
--                         WHERE  S.OBJECT_ID = C.OBJECT_ID
--                                AND S.NAME = C.NAME)
--         AND T.NAME = @TABLENAME1 -- TABLE NAME
--         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
--  ORDER  BY T.NAME

--OPEN CUR1

--FETCH NEXT FROM CUR1 INTO @STATS

--WHILE @@FETCH_STATUS = 0
--  BEGIN
--      --PRINT @STATS
--      EXEC SP_EXECUTESQL
--        @STATS

--      FETCH NEXT FROM CUR1 INTO @STATS
--  END

--CLOSE CUR1

--DEALLOCATE CUR1

--GO



--IF NOT EXISTS (SELECT
--    'X'
--  FROM INFORMATION_SCHEMA.TABLES
--  WHERE TABLE_NAME = 'HIERARCHY_TABLE_RELATION'
--  AND TABLE_SCHEMA = 'dbo')
--BEGIN


--  CREATE TABLE HIERARCHY_TABLE_RELATION (
--    LEFT_TABLE_SID int NOT NULL,
--    RIGHT_TABLE_SID int NOT NULL,
--    LEFT_COLUMN_NAME varchar(100),
--    RIGHT_COLUMN_NAME varchar(100),
--  )
--END
--GO 
--IF NOT EXISTS (SELECT
--    1
--  FROM HIERARCHY_TABLE_RELATION)
--BEGIN
--  INSERT INTO HIERARCHY_TABLE_RELATION (LEFT_TABLE_SID, RIGHT_TABLE_SID, LEFT_COLUMN_NAME, RIGHT_COLUMN_NAME)
--    VALUES (1, 2, 'CONTRACT_MASTER_SID', 'CONTRACT_MASTER_SID');
--  INSERT INTO HIERARCHY_TABLE_RELATION (LEFT_TABLE_SID, RIGHT_TABLE_SID, LEFT_COLUMN_NAME, RIGHT_COLUMN_NAME)
--    VALUES (2, 3, 'CFP_CONTRACT_SID', 'CFP_CONTRACT_SID');
--  INSERT INTO HIERARCHY_TABLE_RELATION (LEFT_TABLE_SID, RIGHT_TABLE_SID, LEFT_COLUMN_NAME, RIGHT_COLUMN_NAME)
--    VALUES (3, 4, 'COMPANY_MASTER_SID', 'COMPANY_MASTER_SID');
--  INSERT INTO HIERARCHY_TABLE_RELATION (LEFT_TABLE_SID, RIGHT_TABLE_SID, LEFT_COLUMN_NAME, RIGHT_COLUMN_NAME)
--    VALUES (4, 5, 'COMPANY_MASTER_SID', 'COMPANY_MASTER_SID');
--  INSERT INTO HIERARCHY_TABLE_RELATION (LEFT_TABLE_SID, RIGHT_TABLE_SID, LEFT_COLUMN_NAME, RIGHT_COLUMN_NAME)
--    VALUES (4, 6, 'COMPANY_MASTER_SID', 'COMPANY_MASTER_SID');
--  INSERT INTO HIERARCHY_TABLE_RELATION (LEFT_TABLE_SID, RIGHT_TABLE_SID, LEFT_COLUMN_NAME, RIGHT_COLUMN_NAME)
--    VALUES (4, 7, 'COMPANY_MASTER_SID', 'COMPANY_MASTER_SID');
--  INSERT INTO HIERARCHY_TABLE_RELATION (LEFT_TABLE_SID, RIGHT_TABLE_SID, LEFT_COLUMN_NAME, RIGHT_COLUMN_NAME)
--    VALUES (8, 9, 'COMPANY_NO', 'COMPANY_CODE');
--  INSERT INTO HIERARCHY_TABLE_RELATION (LEFT_TABLE_SID, RIGHT_TABLE_SID, LEFT_COLUMN_NAME, RIGHT_COLUMN_NAME)
--    VALUES (9, 12, 'NDC8', 'NDC8');
--  INSERT INTO HIERARCHY_TABLE_RELATION (LEFT_TABLE_SID, RIGHT_TABLE_SID, LEFT_COLUMN_NAME, RIGHT_COLUMN_NAME)
--    VALUES (12, 10, 'ITEM_MASTER_SID', 'ITEM_MASTER_SID');
--  INSERT INTO HIERARCHY_TABLE_RELATION (LEFT_TABLE_SID, RIGHT_TABLE_SID, LEFT_COLUMN_NAME, RIGHT_COLUMN_NAME)
--    VALUES (12, 11, 'BRAND_MASTER_SID', 'BRAND_MASTER_SID');

--END
--GO
--IF EXISTS (SELECT
--    NAME
--  FROM SYS.TABLES
--  WHERE NAME = 'HIERARCHY_TABLE_RELATION')
--BEGIN
--  IF NOT EXISTS (SELECT
--      1
--    FROM SYS.KEY_CONSTRAINTS
--    WHERE TYPE_DESC = 'UNIQUE_CONSTRAINT'
--    AND PARENT_OBJECT_ID = OBJECT_ID('HIERARCHY_TABLE_RELATION')
--    AND NAME = 'UQ_HIERARCHY_TABLE_RELATION_LEFT_TABLE_SID_RIGHT_TABLE_SID')
--  BEGIN
--    ALTER TABLE HIERARCHY_TABLE_RELATION
--    ADD CONSTRAINT UQ_HIERARCHY_TABLE_RELATION_LEFT_TABLE_SID_RIGHT_TABLE_SID UNIQUE (LEFT_TABLE_SID, RIGHT_TABLE_SID)
--  END
--END
--GO
-----------------------------------------statistics-----------------------
--DECLARE @SQL NVARCHAR(MAX)
--DECLARE @TABLENAME VARCHAR(100)
--DECLARE @STATSNAME VARCHAR(200)
--DECLARE @TABLENAME1 VARCHAR(100)
--DECLARE @SCHEMANAME VARCHAR(30)
--DECLARE @SCHEMANAME1 VARCHAR(30)

--SET @TABLENAME1 = 'HIERARCHY_TABLE_RELATION'--TABLE NAME
--SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
--IF EXISTS (SELECT 'X'
--           FROM   SYS.STATS S
--                  JOIN SYS.TABLES T
--                    ON S.OBJECT_ID = T.OBJECT_ID
--           WHERE  AUTO_CREATED = 1
--                  AND NOT EXISTS (SELECT 1
--                                  FROM   SYS.INDEXES
--                                  WHERE  S.NAME = NAME)
--                  AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
--                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
--  BEGIN
--      DECLARE CUR CURSOR STATIC FOR
--        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
--               S.NAME                   AS 'STATSNAME',
--               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
--        FROM   SYS.STATS S
--               JOIN SYS.TABLES T
--                 ON S.OBJECT_ID = T.OBJECT_ID
--        WHERE  AUTO_CREATED = 1
--               AND NOT EXISTS (SELECT 1
--                               FROM   SYS.INDEXES
--                               WHERE  S.NAME = NAME)
--               AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
--               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

--      OPEN CUR

--      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

--      WHILE @@FETCH_STATUS = 0
--        BEGIN
--            SET @SQL = 'DROP STATISTICS ' + Quotename(@SCHEMANAME)
--                       + '.' + Quotename(@TABLENAME) + '.'
--                       + Quotename(@STATSNAME)

--            --PRINT @SQL
--            EXEC SP_EXECUTESQL
--              @SQL

--            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
--        END

--      CLOSE CUR

--      DEALLOCATE CUR
--  END

--DECLARE @STATS NVARCHAR(MAX)
--DECLARE CUR1 CURSOR STATIC FOR
--  SELECT 'CREATE STATISTICS ' + Quotename(C.NAME)
--         + ' ON ' + Quotename(SCHEMA_NAME(SCHEMA_ID))
--         + '.' + Quotename(T.NAME) + ' ('
--         + Quotename(C.NAME) + ') WITH FULLSCAN'
--  FROM   SYS.TABLES T
--         JOIN SYS.COLUMNS C
--           ON T.OBJECT_ID = C.OBJECT_ID
--  WHERE  NOT EXISTS (SELECT 1
--                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
--                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
--                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
--                     WHERE  CC.TABLE_NAME = T.NAME
--                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
--                            AND C.NAME = COLUMN_NAME)
--         AND NOT EXISTS (SELECT 1
--                         FROM   SYS.STATS S
--                         WHERE  S.OBJECT_ID = C.OBJECT_ID
--                                AND S.NAME = C.NAME)
--         AND T.NAME = @TABLENAME1 -- TABLE NAME
--         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
--  ORDER  BY T.NAME

--OPEN CUR1

--FETCH NEXT FROM CUR1 INTO @STATS

--WHILE @@FETCH_STATUS = 0
--  BEGIN
--      --PRINT @STATS
--      EXEC SP_EXECUTESQL
--        @STATS

--      FETCH NEXT FROM CUR1 INTO @STATS
--  END

--CLOSE CUR1

--DEALLOCATE CUR1

--GO

--IF NOT EXISTS (SELECT
--    'X'
--  FROM INFORMATION_SCHEMA.TABLES
--  WHERE TABLE_NAME = 'HIERARCHY_TYPE_TABLE_RELATION'
--  AND TABLE_SCHEMA = 'dbo')
--BEGIN


--  CREATE TABLE HIERARCHY_TYPE_TABLE_RELATION (
--    ENTITY_ID int NOT NULL,
--    TABLE_NAME varchar(100) NOT NULL,
--    HIERARCHY_TYPE varchar(100) NOT NULL,
--  )
--END
--GO
--IF NOT EXISTS (SELECT
--    1
--  FROM HIERARCHY_TYPE_TABLE_RELATION)
--BEGIN
--  INSERT INTO HIERARCHY_TYPE_TABLE_RELATION (ENTITY_ID, TABLE_NAME, HIERARCHY_TYPE)
--    VALUES (1, 'COMPANY_MASTER', 'CUSTOMER HIERARCHY');
--  INSERT INTO HIERARCHY_TYPE_TABLE_RELATION (ENTITY_ID, TABLE_NAME, HIERARCHY_TYPE)
--    VALUES (2, 'CONTRACT_MASTER', 'CUSTOMER HIERARCHY');
--  INSERT INTO HIERARCHY_TYPE_TABLE_RELATION (ENTITY_ID, TABLE_NAME, HIERARCHY_TYPE)
--    VALUES (3, 'COMPANY_IDENTIFIER', 'CUSTOMER HIERARCHY');
--  INSERT INTO HIERARCHY_TYPE_TABLE_RELATION (ENTITY_ID, TABLE_NAME, HIERARCHY_TYPE)
--    VALUES (4, 'COMPANY_PARENT_DETAILS', 'CUSTOMER HIERARCHY');
--  INSERT INTO HIERARCHY_TYPE_TABLE_RELATION (ENTITY_ID, TABLE_NAME, HIERARCHY_TYPE)
--    VALUES (5, 'COMPANY_TRADE_CLASS', 'CUSTOMER HIERARCHY');
--  INSERT INTO HIERARCHY_TYPE_TABLE_RELATION (ENTITY_ID, TABLE_NAME, HIERARCHY_TYPE)
--    VALUES (6, 'ITEM_MASTER', 'PRODUCT HIERARCHY');
--  INSERT INTO HIERARCHY_TYPE_TABLE_RELATION (ENTITY_ID, TABLE_NAME, HIERARCHY_TYPE)
--    VALUES (7, 'COMPANY_MASTER', 'PRODUCT HIERARCHY');
--  INSERT INTO HIERARCHY_TYPE_TABLE_RELATION (ENTITY_ID, TABLE_NAME, HIERARCHY_TYPE)
--    VALUES (8, 'ITEM_IDENTIFIER', 'PRODUCT HIERARCHY');
--  INSERT INTO HIERARCHY_TYPE_TABLE_RELATION (ENTITY_ID, TABLE_NAME, HIERARCHY_TYPE)
--    VALUES (9, 'BRAND_MASTER', 'PRODUCT HIERARCHY');


--END
--GO
--IF EXISTS (SELECT
--    NAME
--  FROM SYS.TABLES
--  WHERE NAME = 'HIERARCHY_TYPE_TABLE_RELATION')
--BEGIN
--  IF NOT EXISTS (SELECT
--      1
--    FROM SYS.KEY_CONSTRAINTS
--    WHERE TYPE_DESC = 'UNIQUE_CONSTRAINT'
--    AND PARENT_OBJECT_ID = OBJECT_ID('HIERARCHY_TYPE_TABLE_RELATION')
--    AND NAME = 'UQ_HIERARCHY_TYPE_TABLE_RELATION_ENTITY_ID_TABLE_NAME_HIERARCHY_TYPE')
--  BEGIN
--    ALTER TABLE HIERARCHY_TYPE_TABLE_RELATION
--    ADD CONSTRAINT UQ_HIERARCHY_TYPE_TABLE_RELATION_ENTITY_ID_TABLE_NAME_HIERARCHY_TYPE UNIQUE (ENTITY_ID, TABLE_NAME, HIERARCHY_TYPE)
--  END
--END
--GO
-----------------------------------------statistics-----------------------
--DECLARE @SQL NVARCHAR(MAX)
--DECLARE @TABLENAME VARCHAR(100)
--DECLARE @STATSNAME VARCHAR(200)
--DECLARE @TABLENAME1 VARCHAR(100)
--DECLARE @SCHEMANAME VARCHAR(30)
--DECLARE @SCHEMANAME1 VARCHAR(30)

--SET @TABLENAME1 = 'HIERARCHY_TYPE_TABLE_RELATION'--TABLE NAME
--SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
--IF EXISTS (SELECT 'X'
--           FROM   SYS.STATS S
--                  JOIN SYS.TABLES T
--                    ON S.OBJECT_ID = T.OBJECT_ID
--           WHERE  AUTO_CREATED = 1
--                  AND NOT EXISTS (SELECT 1
--                                  FROM   SYS.INDEXES
--                                  WHERE  S.NAME = NAME)
--                  AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
--                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
--  BEGIN
--      DECLARE CUR CURSOR STATIC FOR
--        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
--               S.NAME                   AS 'STATSNAME',
--               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
--        FROM   SYS.STATS S
--               JOIN SYS.TABLES T
--                 ON S.OBJECT_ID = T.OBJECT_ID
--        WHERE  AUTO_CREATED = 1
--               AND NOT EXISTS (SELECT 1
--                               FROM   SYS.INDEXES
--                               WHERE  S.NAME = NAME)
--               AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
--               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

--      OPEN CUR

--      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME

--      WHILE @@FETCH_STATUS = 0
--        BEGIN
--            SET @SQL = 'DROP STATISTICS ' + Quotename(@SCHEMANAME)
--                       + '.' + Quotename(@TABLENAME) + '.'
--                       + Quotename(@STATSNAME)

--            --PRINT @SQL
--            EXEC SP_EXECUTESQL
--              @SQL

--            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME
--        END

--      CLOSE CUR

--      DEALLOCATE CUR
--  END

--DECLARE @STATS NVARCHAR(MAX)
--DECLARE CUR1 CURSOR STATIC FOR
--  SELECT 'CREATE STATISTICS ' + Quotename(C.NAME)
--         + ' ON ' + Quotename(SCHEMA_NAME(SCHEMA_ID))
--         + '.' + Quotename(T.NAME) + ' ('
--         + Quotename(C.NAME) + ') WITH FULLSCAN'
--  FROM   SYS.TABLES T
--         JOIN SYS.COLUMNS C
--           ON T.OBJECT_ID = C.OBJECT_ID
--  WHERE  NOT EXISTS (SELECT 1
--                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
--                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC
--                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
--                     WHERE  CC.TABLE_NAME = T.NAME
--                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID)
--                            AND C.NAME = COLUMN_NAME)
--         AND NOT EXISTS (SELECT 1
--                         FROM   SYS.STATS S
--                         WHERE  S.OBJECT_ID = C.OBJECT_ID
--                                AND S.NAME = C.NAME)
--         AND T.NAME = @TABLENAME1 -- TABLE NAME
--         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1
--  ORDER  BY T.NAME

--OPEN CUR1

--FETCH NEXT FROM CUR1 INTO @STATS

--WHILE @@FETCH_STATUS = 0
--  BEGIN
--      --PRINT @STATS
--      EXEC SP_EXECUTESQL
--        @STATS

--      FETCH NEXT FROM CUR1 INTO @STATS
--  END

--CLOSE CUR1

--DEALLOCATE CUR1

--GO

--IF NOT EXISTS (SELECT
--    'X'
--  FROM INFORMATION_SCHEMA.TABLES
--  WHERE TABLE_NAME = 'HIERARCHY_SINGLE_COLUMN_RELATION'
--  AND TABLE_SCHEMA = 'dbo')
--BEGIN
	
	
--CREATE TABLE HIERARCHY_SINGLE_COLUMN_RELATION (
--	COLUMN_RELATION_SID int IDENTITY (1, 1) NOT NULL,
--	ACTUAL_TABLE_NAME varchar(100) NOT NULL,
--	ACTUAL_COLUMN_NAME varchar(100) NOT NULL,
--	REFERENCE_TABLE_NAME varchar(100) NOT NULL,
--	MAPPING_COLUMN_NAME varchar(100),
--	DESC_COLUMN_NAME varchar(100),
--	PRIMARY_KEY_COLUMN_NAME varchar(100) NOT NULL,
--)
--END
--GO


--IF NOT EXISTS (SELECT
--    1
--  FROM HIERARCHY_SINGLE_COLUMN_RELATION)
--BEGIN
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'ADVANCE_NOTICE_DAYS', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'AFFILIATED_CONTRACT_INFO', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'AWARD_STATUS', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'BATCH_ID', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'BUNIT_COMPANY_MASTER_SID', 'COMPANY_MASTER', 'COMPANY_MASTER_SID', 'COMPANY_NO', 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CANCELLATION_CLAUSE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CATEGORY', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CONT_HOLD_COMPANY_MASTER_SID', 'COMPANY_MASTER', 'COMPANY_MASTER_SID', 'COMPANY_NO', 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CONTRACT_ID', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CONTRACT_MASTER_SID', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CONTRACT_NAME', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CONTRACT_NO', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CONTRACT_STATUS', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CONTRACT_TRADE_CLASS', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CONTRACT_TYPE', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CREATED_BY', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CREATED_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'CURRENCY', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'DOCUMENT_CLASS', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'DOCUMENT_TYPE', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'END_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'EXEMPT_FROM_LOW_PRICE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'INBOUND_STATUS', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'INSIDE_ADDITIONAL', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'INSIDE_ADDITIONAL_NAME', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'INSIDE_ADDITIONAL_PHONE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'INSIDE_AUTHOR', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'INSIDE_OWNER', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'INSIDE_PHONE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'INTERNAL_NOTES', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'LAST_UPDATED_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'MANF_COMPANY_MASTER_SID', 'COMPANY_MASTER', 'COMPANY_MASTER_SID', 'COMPANY_NO', 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'MINIMUM_ORDER', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'MODIFIED_BY', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'MODIFIED_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'MOST_FAVORED_NATION', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'ORGANIZATION_KEY', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'ORIGINAL_END_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'ORIGINAL_START_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'OUTSIDE_ADDITIONAL', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'OUTSIDE_ADDITIONAL_NAME', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'OUTSIDE_ADDITIONAL_PHONE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'OUTSIDE_AUTHOR', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'OUTSIDE_OWNER', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'OUTSIDE_PHONE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'PAYMENT_TERMS', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'PRICE_ESCALATION_CLAUSE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'PRICE_RESET_INDICATOR', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'PRICEPROTECTION_END_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'PRICEPROTECTION_START_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'PROCESS_STATUS', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'PROPOSAL_END_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'PROPOSAL_START_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'RECORD_LOCK_STATUS', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'RENEGOTIATION_END_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'RENEGOTIATION_START_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'SHIPPING_TERMS', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'SOURCE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'START_DATE', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'CONTRACT_MASTER', 'TERM', 'CONTRACT_MASTER', NULL, NULL, 'CONTRACT_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'ADDRESS1', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'ADDRESS2', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'BATCH_ID', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'CITY', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'COMPANY_CATEGORY', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'COMPANY_END_DATE', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'COMPANY_GROUP', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'COMPANY_ID', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'COMPANY_MASTER_SID', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'COMPANY_NAME', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'COMPANY_NO', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'COMPANY_START_DATE', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'COMPANY_STATUS', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'COMPANY_TYPE', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'COUNTRY', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'CREATED_BY', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'CREATED_DATE', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'FINANCIAL_SYSTEM', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'INBOUND_STATUS', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'INTERNAL_NOTES', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'LAST_UPDATED_DATE', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'LIVES', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'MODIFIED_BY', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'MODIFIED_DATE', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'ORGANIZATION_KEY', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'RECORD_LOCK_STATUS', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'REGION_CODE', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'SOURCE', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'STATE', 'HELPER_TABLE', 'HELPER_TABLE_SID', 'DESCRIPTION', 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_MASTER', 'ZIP_CODE', 'COMPANY_MASTER', NULL, NULL, 'COMPANY_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ACQUIRED_AMP', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ACQUIRED_BAMP', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ACQUISITION_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ALT_BASE_CPI', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ALT_BASELINE_AMP', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'AUTHORIZED_GENERIC', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'AUTHORIZED_GENERIC_END_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'AUTHORIZED_GENERIC_START_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'BASE_CPI', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'BASE_CPI_PERIOD', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'BASE_CPI_PRECISION', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'BASELINE_AMP', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'BASELINE_AMP_PRECISION', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'BATCH_ID', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'BRAND_MASTER_SID', 'BRAND_MASTER', 'BRAND_MASTER_SID', 'BRAND_NAME', 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'CLOTTING_FACTOR_END_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'CLOTTING_FACTOR_INDICATOR', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'CLOTTING_FACTOR_START_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'CREATED_BY', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'CREATED_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'DISCONTINUATION_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'DIVESTITURE_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'DOSES_PER_UNIT', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'DRA', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'DUAL_PRICING_INDICATOR', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'FIRST_SALE_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'FORM', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'INBOUND_STATUS', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'INTERNAL_NOTES', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_CATEGORY', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_CLASS', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_CODE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_DESC', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_END_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_FAMILY_ID', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_ID', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_MASTER_SID', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_NAME', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_NO', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_START_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_STATUS', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_TYPE', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ITEM_TYPE_INDICATION', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'LABELER_CODE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'LAST_LOT_EXPIRATION_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'MANUFACTURER_ID', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'MARKET_TERMINATION_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'MODIFIED_BY', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'MODIFIED_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'NDC8', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'NDC9', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'NEW_FORMULATION', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'NEW_FORMULATION_END_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'NEW_FORMULATION_INDICATOR', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'NEW_FORMULATION_START_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'NON_FEDERAL_EXPIRATION_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'OBRA_BAMP', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'ORGANIZATION_KEY', 'COMPANY_MASTER', 'COMPANY_MASTER_SID', 'COMPANY_NO', 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'PACKAGE_SIZE', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'PACKAGE_SIZE_CODE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'PACKAGE_SIZE_INTRO_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'PEDIATRIC_EXCLUSIVE_END_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'PEDIATRIC_EXCLUSIVE_INDICATOR', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'PEDIATRIC_EXCLUSIVE_START_DATE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'PRIMARY_UOM', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'RECORD_LOCK_STATUS', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'SECONDARY_UOM', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'SHELF_LIFE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'SHELF_LIFE_TYPE', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'SOURCE', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'STRENGTH', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'THERAPEUTIC_CLASS', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_MASTER', 'UPPS', 'ITEM_MASTER', NULL, NULL, 'ITEM_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'BATCH_ID', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'CREATED_BY', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'CREATED_DATE', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'END_DATE', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'ENTITY_CODE', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'IDENTIFIER_STATUS', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'INBOUND_STATUS', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'ITEM_IDENTIFIER_SID', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'ITEM_IDENTIFIER_VALUE', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'ITEM_MASTER_SID', 'ITEM_MASTER', 'ITEM_MASTER_SID', 'ITEM_NO', 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'ITEM_QUALIFIER_SID', 'ITEM_QUALIFIER', 'ITEM_QUALIFIER_SID', 'ITEM_QUALIFIER_NAME', 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'MODIFIED_BY', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'MODIFIED_DATE', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'RECORD_LOCK_STATUS', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'SOURCE', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'ITEM_IDENTIFIER', 'START_DATE', 'ITEM_IDENTIFIER', NULL, NULL, 'ITEM_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'BATCH_ID', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'BRAND_DESC', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'BRAND_ID', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'BRAND_MASTER_SID', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'BRAND_NAME', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'CREATED_BY', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'CREATED_DATE', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'DISPLAY_BRAND', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'INBOUND_STATUS', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'MODIFIED_BY', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'MODIFIED_DATE', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'RECORD_LOCK_STATUS', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'BRAND_MASTER', 'SOURCE', 'BRAND_MASTER', NULL, NULL, 'BRAND_MASTER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'BATCH_ID', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'COMPANY_IDENTIFIER_SID', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'COMPANY_IDENTIFIER_VALUE', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'COMPANY_MASTER_SID', 'COMPANY_MASTER', 'COMPANY_MASTER_SID', 'COMPANY_NO', 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'COMPANY_QUALIFIER_SID', 'COMPANY_QUALIFIER', 'COMPANY_QUALIFIER_SID', 'COMPANY_QUALIFIER_NAME', 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'CREATED_BY', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'CREATED_DATE', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'END_DATE', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'ENTITY_CODE', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'IDENTIFIER_STATUS', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'INBOUND_STATUS', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'MODIFIED_BY', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'MODIFIED_DATE', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'RECORD_LOCK_STATUS', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'SOURCE', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_IDENTIFIER', 'START_DATE', 'COMPANY_IDENTIFIER', NULL, NULL, 'COMPANY_IDENTIFIER_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'BATCH_ID', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'COMPANY_MASTER_SID', 'COMPANY_MASTER', 'COMPANY_MASTER_SID', 'COMPANY_NO', 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'COMPANY_PARENT_DETAILS_SID', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'CREATED_BY', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'CREATED_DATE', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'INBOUND_STATUS', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'LAST_UPDATED_DATE', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'MODIFIED_BY', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'MODIFIED_DATE', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'PARENT_COMPANY_MASTER_SID', 'COMPANY_MASTER', 'COMPANY_MASTER_SID', 'COMPANY_NO', 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'PARENT_END_DATE', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'PARENT_START_DATE', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'PRIOR_PARENT_CMPY_MASTER_SID', 'COMPANY_MASTER', 'COMPANY_MASTER_SID', 'COMPANY_NO', 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'PRIOR_PARENT_START_DATE', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'RECORD_LOCK_STATUS', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_PARENT_DETAILS', 'SOURCE', 'COMPANY_PARENT_DETAILS', NULL, NULL, 'COMPANY_PARENT_DETAILS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'BATCH_ID', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'COMPANY_MASTER_SID', 'COMPANY_MASTER', 'COMPANY_MASTER_SID', 'COMPANY_NO', 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'COMPANY_TRADE_CLASS', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'COMPANY_TRADE_CLASS_SID', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'CREATED_BY', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'CREATED_DATE', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'INBOUND_STATUS', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'LAST_UPDATED_DATE', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'MODIFIED_BY', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'MODIFIED_DATE', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'PRIOR_TRADE_CLASS', 'HELPER_TABLE', 'HELPER_TABLE_SID', NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'PRIOR_TRADE_CLASS_START_DATE', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'RECORD_LOCK_STATUS', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'SOURCE', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'TRADE_CLASS_END_DATE', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--INSERT INTO HIERARCHY_SINGLE_COLUMN_RELATION
--( ACTUAL_TABLE_NAME, ACTUAL_COLUMN_NAME, REFERENCE_TABLE_NAME, MAPPING_COLUMN_NAME, DESC_COLUMN_NAME, PRIMARY_KEY_COLUMN_NAME)
--VALUES( 'COMPANY_TRADE_CLASS', 'TRADE_CLASS_START_DATE', 'COMPANY_TRADE_CLASS', NULL, NULL, 'COMPANY_TRADE_CLASS_SID');
--END
--GO

----------------------------


IF  EXISTS (
		SELECT 1
		FROM HIERARCHY_SINGLE_COLUMN_RELATION
		WHERE ACTUAL_TABLE_NAME = 'ITEM_MASTER'
			AND ACTUAL_COLUMN_NAME = 'NDC9'
			AND REFERENCE_TABLE_NAME='ITEM_MASTER'
			AND PRIMARY_KEY_COLUMN_NAME='ITEM_MASTER_SID'
		)

		BEGIN
					UPDATE HIERARCHY_SINGLE_COLUMN_RELATION
			SET
			MAPPING_COLUMN_NAME='ITEM_MASTER_SID',
			DESC_COLUMN_NAME='ITEM_NAME'
				WHERE ACTUAL_TABLE_NAME = 'ITEM_MASTER'
			AND ACTUAL_COLUMN_NAME = 'NDC9'
			AND REFERENCE_TABLE_NAME='ITEM_MASTER'
			AND PRIMARY_KEY_COLUMN_NAME='ITEM_MASTER_SID'
			END
			GO


------------------------------------AGN-413---------------- 

IF EXISTS(
	SELECT
		1
	from
		HIERARCHY_SINGLE_COLUMN_RELATION
	where
		MAPPING_COLUMN_NAME = 'HELPER_TABLE_SID'
		and (DESC_COLUMN_NAME <> 'DESCRIPTION'  or DESC_COLUMN_NAME  is null )
) BEGIN UPDATE
	HIERARCHY_SINGLE_COLUMN_RELATION
SET
	DESC_COLUMN_NAME = 'DESCRIPTION'
where
	MAPPING_COLUMN_NAME = 'HELPER_TABLE_SID'
END
GO

---------------------------AGN-489----------------------------------
IF EXISTS (
  SELECT 1
  FROM HIERARCHY_SINGLE_COLUMN_RELATION
  WHERE ACTUAL_TABLE_NAME = 'CONTRACT_MASTER'
  AND ACTUAL_COLUMN_NAME = 'CONT_HOLD_COMPANY_MASTER_SID'
  AND REFERENCE_TABLE_NAME = 'COMPANY_MASTER'
  AND DESC_COLUMN_NAME = 'COMPANY_NO')
BEGIN
  UPDATE HIERARCHY_SINGLE_COLUMN_RELATION
  SET DESC_COLUMN_NAME = 'COMPANY_NAME'
  WHERE ACTUAL_TABLE_NAME = 'CONTRACT_MASTER'
  AND ACTUAL_COLUMN_NAME = 'CONT_HOLD_COMPANY_MASTER_SID'
  AND REFERENCE_TABLE_NAME = 'COMPANY_MASTER'
  AND DESC_COLUMN_NAME = 'COMPANY_NO'
END

GO
---------------------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIERARCHY_SINGLE_COLUMN_RELATION'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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


-------------------trigger-------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_DEFINITION_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_HIERARCHY_DEFINITION_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_HIERARCHY_DEFINITION_UPD]
ON [DBO].HIERARCHY_DEFINITION
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_DEFINITION
                  ([HIERARCHY_DEFINITION_SID],
                   [HIERARCHY_NAME],
                   [HIERARCHY_TYPE],
                   [HIERARCHY_CATEGORY],
                   [NO_OF_LEVELS],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT[HIERARCHY_DEFINITION_SID],
            [HIERARCHY_NAME],
            [HIERARCHY_TYPE],
            [HIERARCHY_CATEGORY],
            [NO_OF_LEVELS],
            [CREATED_BY],
            [CREATED_DATE],
            [MODIFIED_BY],
            [MODIFIED_DATE],
            [VERSION_NO],
            'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_DEFINITION_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_HIERARCHY_DEFINITION_INS]
  END

GO

CREATE TRIGGER [DBO].[TRG_HIERARCHY_DEFINITION_INS]
ON [DBO].HIERARCHY_DEFINITION
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_DEFINITION
                  ([HIERARCHY_DEFINITION_SID],
                   [HIERARCHY_NAME],
                   [HIERARCHY_TYPE],
                   [HIERARCHY_CATEGORY],
                   [NO_OF_LEVELS],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT[HIERARCHY_DEFINITION_SID],
            [HIERARCHY_NAME],
            [HIERARCHY_TYPE],
            [HIERARCHY_CATEGORY],
            [NO_OF_LEVELS],
            [CREATED_BY],
            [CREATED_DATE],
            [MODIFIED_BY],
            [MODIFIED_DATE],
            [VERSION_NO],
            'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_DEFINITION_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_HIERARCHY_DEFINITION_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_HIERARCHY_DEFINITION_DEL]
ON [DBO].HIERARCHY_DEFINITION
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_DEFINITION
                  ([HIERARCHY_DEFINITION_SID],
                   [HIERARCHY_NAME],
                   [HIERARCHY_TYPE],
                   [HIERARCHY_CATEGORY],
                   [NO_OF_LEVELS],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT[HIERARCHY_DEFINITION_SID],
            [HIERARCHY_NAME],
            [HIERARCHY_TYPE],
            [HIERARCHY_CATEGORY],
            [NO_OF_LEVELS],
            [CREATED_BY],
            [CREATED_DATE],
            [MODIFIED_BY],
            [MODIFIED_DATE],
            [VERSION_NO],
            'D'
      FROM   DELETED I
  END

GO

---------------------------------------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIERARCHY_LEVEL_DEFINITION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIERARCHY_LEVEL_DEFINITION]
        (
           [HIERARCHY_LEVEL_DEFINITION_SID] INT IDENTITY(1, 1) NOT NULL,
           [LEVEL_NO]                       NUMERIC(5, 0) NULL,
           [LEVEL_NAME]                     VARCHAR(100) NULL,
           [LEVEL_VALUE_REFERENCE]          VARCHAR(20) NULL,
           [HIERARCHY_DEFINITION_SID]       INT NULL,
           [TABLE_NAME]                     VARCHAR(128) NULL,
           [FIELD_NAME]                     VARCHAR(128) NULL,
           [INCLUSION_RULE_TYPE]            VARCHAR(5) NULL,
           [INCLUSION_RULE]                 VARCHAR(100) NULL,
           [EXCLUSION_RULE_TYPE]            VARCHAR(5) NULL,
           [EXCLUSION_RULE]                 VARCHAR(100) NULL,
           [INCLUSION_CONDITION]            VARCHAR(3) NULL,
           [EXCLUSION_CONDITION]            VARCHAR(3) NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL,
           [VERSION_NO]                     INT NOT NULL
        )
  END

GO

--------------------------default_constraint--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_LEVEL_DEFINITION')
                      AND NAME = 'DF_HIERARCHY_LEVEL_DEFINITION_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_LEVEL_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_LEVEL_DEFINITION_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_LEVEL_DEFINITION')
                      AND NAME = 'DF_HIERARCHY_LEVEL_DEFINITION_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_LEVEL_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_LEVEL_DEFINITION_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_LEVEL_DEFINITION')
                      AND NAME = 'DF_HIERARCHY_LEVEL_DEFINITION_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_LEVEL_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_LEVEL_DEFINITION_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_LEVEL_DEFINITION')
                      AND NAME = 'DF_HIERARCHY_LEVEL_DEFINITION_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_LEVEL_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_LEVEL_DEFINITION_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_LEVEL_DEFINITION')
                      AND NAME = 'DF_HIERARCHY_LEVEL_DEFINITION_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_LEVEL_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_LEVEL_DEFINITION_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

--Additional Columns
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIERARCHY_LEVEL_DEFINITION'
                      AND COLUMN_NAME = 'INCLUSION_RULE_TYPE')
  BEGIN
      ALTER TABLE HIERARCHY_LEVEL_DEFINITION
        ADD INCLUSION_RULE_TYPE VARCHAR(5)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIERARCHY_LEVEL_DEFINITION'
                      AND COLUMN_NAME = 'INCLUSION_RULE')
  BEGIN
      ALTER TABLE HIERARCHY_LEVEL_DEFINITION
        ADD INCLUSION_RULE VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIERARCHY_LEVEL_DEFINITION'
                      AND COLUMN_NAME = 'EXCLUSION_RULE_TYPE')
  BEGIN
      ALTER TABLE HIERARCHY_LEVEL_DEFINITION
        ADD EXCLUSION_RULE_TYPE VARCHAR(5)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIERARCHY_LEVEL_DEFINITION'
                      AND COLUMN_NAME = 'EXCLUSION_RULE')
  BEGIN
      ALTER TABLE HIERARCHY_LEVEL_DEFINITION
        ADD EXCLUSION_RULE VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIERARCHY_LEVEL_DEFINITION'
                      AND COLUMN_NAME = 'INCLUSION_CONDITION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIERARCHY_LEVEL_DEFINITION
        ADD INCLUSION_CONDITION VARCHAR(3)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIERARCHY_LEVEL_DEFINITION'
                      AND COLUMN_NAME = 'EXCLUSION_CONDITION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIERARCHY_LEVEL_DEFINITION
        ADD EXCLUSION_CONDITION VARCHAR(3)
  END

GO

-----------------------primary_key-----------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_HIERARCHY_LEVEL_DEFINITION_HIERARCHY_LEVEL_DEFINITION_SID'
                     AND TABLE_NAME = 'HIERARCHY_LEVEL_DEFINITION')
  ALTER TABLE HIERARCHY_LEVEL_DEFINITION
    ADD CONSTRAINT PK_HIERARCHY_LEVEL_DEFINITION_HIERARCHY_LEVEL_DEFINITION_SID PRIMARY KEY(HIERARCHY_LEVEL_DEFINITION_SID)

GO

---------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLE_NAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLE_NAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLE_NAME1 = 'HIERARCHY_LEVEL_DEFINITION'--TABLE NAME
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME
IF EXISTS (SELECT 'X'
           FROM   SYS.STATS S
                  JOIN SYS.TABLES T
                    ON S.OBJECT_ID = T.OBJECT_ID
           WHERE  AUTO_CREATED = 1
                  AND NOT EXISTS (SELECT 1
                                  FROM   SYS.INDEXES
                                  WHERE  S.NAME = NAME)
                  AND Object_name(S.OBJECT_ID) = @TABLE_NAME1 -- TABLE NAME
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLE_NAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
        WHERE  AUTO_CREATED = 1
               AND NOT EXISTS (SELECT 1
                               FROM   SYS.INDEXES
                               WHERE  S.NAME = NAME)
               AND Object_name(S.OBJECT_ID) = @TABLE_NAME1 -- TABLE NAME
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1

      OPEN CUR

      FETCH NEXT FROM CUR INTO @TABLE_NAME, @STATSNAME, @SCHEMANAME

      WHILE @@FETCH_STATUS = 0
        BEGIN
            SET @SQL = 'DROP STATISTICS ' + Quotename(@SCHEMANAME)
                       + '.' + Quotename(@TABLE_NAME) + '.'
                       + Quotename(@STATSNAME)

            --PRINT @SQL
            EXEC SP_EXECUTESQL
              @SQL

            FETCH NEXT FROM CUR INTO @TABLE_NAME, @STATSNAME, @SCHEMANAME
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
         AND T.NAME = @TABLE_NAME1 -- TABLE NAME
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

----------------------------HIST_HIERARCHY_LEVEL_DEFN---------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_HIERARCHY_LEVEL_DEFN'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_HIERARCHY_LEVEL_DEFN]
        (
           [HIERARCHY_LEVEL_DEFINITION_SID] INT NOT NULL,
           [LEVEL_NO]                       NUMERIC(5, 0) NULL,
           [LEVEL_NAME]                     VARCHAR(100) NULL,
           [LEVEL_VALUE_REFERENCE]          VARCHAR(20) NULL,
           [HIERARCHY_DEFINITION_SID]       INT NULL,
           [TABLE_NAME]                     VARCHAR(128) NULL,
           [FIELD_NAME]                     VARCHAR(128) NULL,
           [INCLUSION_RULE_TYPE]            VARCHAR(5) NULL,
           [INCLUSION_RULE]                 VARCHAR(100) NULL,
           [EXCLUSION_RULE_TYPE]            VARCHAR(5) NULL,
           [EXCLUSION_RULE]                 VARCHAR(100) NULL,
           [INCLUSION_CONDITION]            VARCHAR(3) NULL,
           [EXCLUSION_CONDITION]            VARCHAR(3) NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL,
           [VERSION_NO]                     INT NOT NULL,
           [ACTION_FLAG]                    CHAR(1) NOT NULL,
           [ACTION_DATE]                    DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_HIERARCHY_LEVEL_DEFN')
                      AND NAME = 'DF_HIST_HIERARCHY_LEVEL_DEFN_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_HIERARCHY_LEVEL_DEFN]
        ADD CONSTRAINT [DF_HIST_HIERARCHY_LEVEL_DEFN_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_HIERARCHY_LEVEL_DEFN')
                      AND NAME = 'PK_HIST_HIERARCHY_LEVEL_DEFN_HIERARCHY_LEVEL_DEFINITION_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_HIERARCHY_LEVEL_DEFN]
        ADD CONSTRAINT PK_HIST_HIERARCHY_LEVEL_DEFN_HIERARCHY_LEVEL_DEFINITION_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (HIERARCHY_LEVEL_DEFINITION_SID, ACTION_FLAG, VERSION_NO)
  END

GO

--Additional Columns
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_HIERARCHY_LEVEL_DEFN'
                      AND COLUMN_NAME = 'INCLUSION_RULE_TYPE')
  BEGIN
      ALTER TABLE HIST_HIERARCHY_LEVEL_DEFN
        ADD INCLUSION_RULE_TYPE VARCHAR(5)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_HIERARCHY_LEVEL_DEFN'
                      AND COLUMN_NAME = 'INCLUSION_RULE')
  BEGIN
      ALTER TABLE HIST_HIERARCHY_LEVEL_DEFN
        ADD INCLUSION_RULE VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_HIERARCHY_LEVEL_DEFN'
                      AND COLUMN_NAME = 'EXCLUSION_RULE_TYPE')
  BEGIN
      ALTER TABLE HIST_HIERARCHY_LEVEL_DEFN
        ADD EXCLUSION_RULE_TYPE VARCHAR(5)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_HIERARCHY_LEVEL_DEFN'
                      AND COLUMN_NAME = 'EXCLUSION_RULE')
  BEGIN
      ALTER TABLE HIST_HIERARCHY_LEVEL_DEFN
        ADD EXCLUSION_RULE VARCHAR(100)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_HIERARCHY_LEVEL_DEFN'
                      AND COLUMN_NAME = 'INCLUSION_CONDITION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_HIERARCHY_LEVEL_DEFN
        ADD INCLUSION_CONDITION VARCHAR(3)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_HIERARCHY_LEVEL_DEFN'
                      AND COLUMN_NAME = 'EXCLUSION_CONDITION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_HIERARCHY_LEVEL_DEFN
        ADD EXCLUSION_CONDITION VARCHAR(3)
  END

GO

---------------------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_HIERARCHY_LEVEL_DEFN'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

------------------------------------------trigger-------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_LEVEL_DEFINITION_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_HIERARCHY_LEVEL_DEFINITION_DEL]
  END

GO

CREATE TRIGGER [dbo].[TRG_HIERARCHY_LEVEL_DEFINITION_DEL]
ON [dbo].[HIERARCHY_LEVEL_DEFINITION]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_LEVEL_DEFN
                  (HIERARCHY_LEVEL_DEFINITION_SID,
                   LEVEL_NO,
                   LEVEL_NAME,
                   LEVEL_VALUE_REFERENCE,
                   HIERARCHY_DEFINITION_SID,
                   TABLE_NAME,
                   FIELD_NAME,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   INCLUSION_RULE_TYPE,
                   INCLUSION_RULE,
                   EXCLUSION_RULE_TYPE,
                   EXCLUSION_RULE,
                   INCLUSION_CONDITION,
                   EXCLUSION_CONDITION,
                   [ACTION_FLAG])
      SELECT HIERARCHY_LEVEL_DEFINITION_SID,
             LEVEL_NO,
             LEVEL_NAME,
             LEVEL_VALUE_REFERENCE,
             HIERARCHY_DEFINITION_SID,
             TABLE_NAME,
             FIELD_NAME,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             INCLUSION_RULE_TYPE,
             INCLUSION_RULE,
             EXCLUSION_RULE_TYPE,
             EXCLUSION_RULE,
             INCLUSION_CONDITION,
             EXCLUSION_CONDITION,
             'D'
      FROM   DELETED I
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_LEVEL_DEFINITION_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_HIERARCHY_LEVEL_DEFINITION_INS]
  END

GO

CREATE TRIGGER [dbo].[TRG_HIERARCHY_LEVEL_DEFINITION_INS]
ON [dbo].[HIERARCHY_LEVEL_DEFINITION]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_LEVEL_DEFN
                  (HIERARCHY_LEVEL_DEFINITION_SID,
                   LEVEL_NO,
                   LEVEL_NAME,
                   LEVEL_VALUE_REFERENCE,
                   HIERARCHY_DEFINITION_SID,
                   TABLE_NAME,
                   FIELD_NAME,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   INCLUSION_RULE_TYPE,
                   INCLUSION_RULE,
                   EXCLUSION_RULE_TYPE,
                   EXCLUSION_RULE,
                   INCLUSION_CONDITION,
                   EXCLUSION_CONDITION,
                   [ACTION_FLAG])
      SELECT HIERARCHY_LEVEL_DEFINITION_SID,
             LEVEL_NO,
             LEVEL_NAME,
             LEVEL_VALUE_REFERENCE,
             HIERARCHY_DEFINITION_SID,
             TABLE_NAME,
             FIELD_NAME,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             INCLUSION_RULE_TYPE,
             INCLUSION_RULE,
             EXCLUSION_RULE_TYPE,
             EXCLUSION_RULE,
             INCLUSION_CONDITION,
             EXCLUSION_CONDITION,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_LEVEL_DEFINITION_UPD')
  BEGIN
      DROP TRIGGER DBO.[TRG_HIERARCHY_LEVEL_DEFINITION_UPD]
  END

GO

CREATE TRIGGER [dbo].[TRG_HIERARCHY_LEVEL_DEFINITION_UPD]
ON [dbo].[HIERARCHY_LEVEL_DEFINITION]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_LEVEL_DEFN
                  (HIERARCHY_LEVEL_DEFINITION_SID,
                   LEVEL_NO,
                   LEVEL_NAME,
                   LEVEL_VALUE_REFERENCE,
                   HIERARCHY_DEFINITION_SID,
                   TABLE_NAME,
                   FIELD_NAME,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   INCLUSION_RULE_TYPE,
                   INCLUSION_RULE,
                   EXCLUSION_RULE_TYPE,
                   EXCLUSION_RULE,
                   INCLUSION_CONDITION,
                   EXCLUSION_CONDITION,
                   [ACTION_FLAG])
      SELECT HIERARCHY_LEVEL_DEFINITION_SID,
             LEVEL_NO,
             LEVEL_NAME,
             LEVEL_VALUE_REFERENCE,
             HIERARCHY_DEFINITION_SID,
             TABLE_NAME,
             FIELD_NAME,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             INCLUSION_RULE_TYPE,
             INCLUSION_RULE,
             EXCLUSION_RULE_TYPE,
             EXCLUSION_RULE,
             INCLUSION_CONDITION,
             EXCLUSION_CONDITION,
             'C'
      FROM   INSERTED I
  END

GO

------------------------------HIERARCHY_LEVEL_VALUES---------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIERARCHY_LEVEL_VALUES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIERARCHY_LEVEL_VALUES]
        (
           [HIERARCHY_LEVEL_VALUES_SID]     INT IDENTITY(1, 1) NOT NULL,
           [LEVEL_VALUES]                   VARCHAR(100) NULL,
           [HIERARCHY_LEVEL_DEFINITION_SID] INT NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL,
           [VERSION_NO]                     INT NOT NULL
        )
  END

GO

-----------------------------------------default_constraint--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_LEVEL_VALUES')
                      AND NAME = 'DF_HIERARCHY_LEVEL_VALUES_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_LEVEL_VALUES]
        ADD CONSTRAINT [DF_HIERARCHY_LEVEL_VALUES_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_LEVEL_VALUES')
                      AND NAME = 'DF_HIERARCHY_LEVEL_VALUES_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_LEVEL_VALUES]
        ADD CONSTRAINT [DF_HIERARCHY_LEVEL_VALUES_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_LEVEL_VALUES')
                      AND NAME = 'DF_HIERARCHY_LEVEL_VALUES_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_LEVEL_VALUES]
        ADD CONSTRAINT [DF_HIERARCHY_LEVEL_VALUES_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_LEVEL_VALUES')
                      AND NAME = 'DF_HIERARCHY_LEVEL_VALUES_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_LEVEL_VALUES]
        ADD CONSTRAINT [DF_HIERARCHY_LEVEL_VALUES_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIERARCHY_LEVEL_VALUES')
                      AND NAME = 'DF_HIERARCHY_LEVEL_VALUES_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_LEVEL_VALUES]
        ADD CONSTRAINT [DF_HIERARCHY_LEVEL_VALUES_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

--------------------------primary_key-----------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_HIERARCHY_LEVEL_VALUES_HIERARCHY_LEVEL_VALUES_SID'
                     AND TABLE_NAME = 'HIERARCHY_LEVEL_VALUES')
  ALTER TABLE HIERARCHY_LEVEL_VALUES
    ADD CONSTRAINT PK_HIERARCHY_LEVEL_VALUES_HIERARCHY_LEVEL_VALUES_SID PRIMARY KEY(HIERARCHY_LEVEL_VALUES_SID)

GO

-----------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIERARCHY_LEVEL_VALUES'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

--------------------------HIST_HIERARCHY_LEVEL_VALUES-------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_HIERARCHY_LEVEL_VALUES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_HIERARCHY_LEVEL_VALUES]
        (
           [HIERARCHY_LEVEL_VALUES_SID]     INT NOT NULL,
           [LEVEL_VALUES]                   VARCHAR(100) NULL,
           [HIERARCHY_LEVEL_DEFINITION_SID] INT NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL,
           [VERSION_NO]                     INT NOT NULL,
           [ACTION_FLAG]                    CHAR(1) NOT NULL,
           [ACTION_DATE]                    DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_HIERARCHY_LEVEL_VALUES')
                      AND NAME = 'PK_HIST_HIERARCHY_LEVEL_VALUES_HIERARCHY_LEVEL_VALUES_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_HIERARCHY_LEVEL_VALUES]
        ADD CONSTRAINT PK_HIST_HIERARCHY_LEVEL_VALUES_HIERARCHY_LEVEL_VALUES_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (HIERARCHY_LEVEL_VALUES_SID, ACTION_FLAG, VERSION_NO)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_HIERARCHY_LEVEL_VALUES')
                      AND NAME = 'DF_HIST_HIERARCHY_LEVEL_VALUES_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_HIERARCHY_LEVEL_VALUES]
        ADD CONSTRAINT [DF_HIST_HIERARCHY_LEVEL_VALUES_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

---------------------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_HIERARCHY_LEVEL_VALUES'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

---------------------------------------trigger-------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_LEVEL_VALUES_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_HIERARCHY_LEVEL_VALUES_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_HIERARCHY_LEVEL_VALUES_UPD]
ON [DBO].HIERARCHY_LEVEL_VALUES
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_LEVEL_VALUES
                  (HIERARCHY_LEVEL_VALUES_SID,
                   LEVEL_VALUES,
                   HIERARCHY_LEVEL_DEFINITION_SID,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT HIERARCHY_LEVEL_VALUES_SID,
             LEVEL_VALUES,
             HIERARCHY_LEVEL_DEFINITION_SID,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'C'
      FROM   INSERTED I
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_LEVEL_VALUES_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_HIERARCHY_LEVEL_VALUES_INS]
  END

GO

CREATE TRIGGER [DBO].[TRG_HIERARCHY_LEVEL_VALUES_INS]
ON [DBO].HIERARCHY_LEVEL_VALUES
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_LEVEL_VALUES
                  (HIERARCHY_LEVEL_VALUES_SID,
                   LEVEL_VALUES,
                   HIERARCHY_LEVEL_DEFINITION_SID,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT HIERARCHY_LEVEL_VALUES_SID,
             LEVEL_VALUES,
             HIERARCHY_LEVEL_DEFINITION_SID,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_LEVEL_VALUES_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_HIERARCHY_LEVEL_VALUES_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_HIERARCHY_LEVEL_VALUES_DEL]
ON [DBO].HIERARCHY_LEVEL_VALUES
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_LEVEL_VALUES
                  (HIERARCHY_LEVEL_VALUES_SID,
                   LEVEL_VALUES,
                   HIERARCHY_LEVEL_DEFINITION_SID,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT HIERARCHY_LEVEL_VALUES_SID,
             LEVEL_VALUES,
             HIERARCHY_LEVEL_DEFINITION_SID,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'D'
      FROM   DELETED I
  END

GO

--------------------------ITEM_GROUP------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_GROUP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ITEM_GROUP]
        (
           [ITEM_GROUP_SID]         INT IDENTITY(1, 1) NOT NULL,
           [ITEM_GROUP_NO]          VARCHAR(100) NULL,
           [ITEM_GROUP_NAME]        VARCHAR(100) NULL,
           [ITEM_GROUP_DESCRIPTION] VARCHAR(250) NULL,
           [COMPANY_MASTER_SID]     INT NULL,
           [ITEM_FILTER]            VARCHAR(4000) NULL,
           [CREATED_BY]             INT NOT NULL,
           [CREATED_DATE]           DATETIME NOT NULL,
           [MODIFIED_BY]            INT NOT NULL,
           [MODIFIED_DATE]          DATETIME NOT NULL,
           [VERSION_NO]             INT NOT NULL
        )
  END

GO

----------------------default_constraint--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ITEM_GROUP')
                      AND NAME = 'DF_ITEM_GROUP_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[ITEM_GROUP]
        ADD CONSTRAINT [DF_ITEM_GROUP_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ITEM_GROUP')
                      AND NAME = 'DF_ITEM_GROUP_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[ITEM_GROUP]
        ADD CONSTRAINT [DF_ITEM_GROUP_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ITEM_GROUP')
                      AND NAME = 'DF_ITEM_GROUP_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ITEM_GROUP]
        ADD CONSTRAINT [DF_ITEM_GROUP_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ITEM_GROUP')
                      AND NAME = 'DF_ITEM_GROUP_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[ITEM_GROUP]
        ADD CONSTRAINT [DF_ITEM_GROUP_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ITEM_GROUP')
                      AND NAME = 'DF_ITEM_GROUP_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ITEM_GROUP]
        ADD CONSTRAINT [DF_ITEM_GROUP_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

----------------------------primary_key-----------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_ITEM_GROUP_ITEM_GROUP_SID'
                     AND TABLE_NAME = 'ITEM_GROUP')

BEGIN
  ALTER TABLE ITEM_GROUP
    ADD CONSTRAINT PK_ITEM_GROUP_ITEM_GROUP_SID PRIMARY KEY(ITEM_GROUP_SID)
END

GO

---------------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ITEM_GROUP'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

-------------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ITEM_GROUP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_ITEM_GROUP]
        (
           [ITEM_GROUP_SID]         INT NOT NULL,
           [ITEM_GROUP_NO]          VARCHAR(50) NULL,
           [ITEM_GROUP_NAME]        VARCHAR(100) NULL,
           [ITEM_GROUP_DESCRIPTION] VARCHAR(250) NULL,
           [COMPANY_MASTER_SID]     INT NULL,
           [ITEM_FILTER]            VARCHAR(4000) NULL,
           [CREATED_BY]             INT NOT NULL,
           [CREATED_DATE]           DATETIME NOT NULL,
           [MODIFIED_BY]            INT NOT NULL,
           [MODIFIED_DATE]          DATETIME NOT NULL,
           [VERSION_NO]             INT NOT NULL,
           [ACTION_FLAG]            CHAR(1) NOT NULL,
           [ACTION_DATE]            DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_ITEM_GROUP')
                      AND NAME = 'DF_HIST_ITEM_GROUP_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_ITEM_GROUP]
        ADD CONSTRAINT [DF_HIST_ITEM_GROUP_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_ITEM_GROUP')
                      AND NAME = 'PK_HIST_ITEM_GROUP_ITEM_GROUP_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_ITEM_GROUP]
        ADD CONSTRAINT PK_HIST_ITEM_GROUP_ITEM_GROUP_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (ITEM_GROUP_SID, ACTION_FLAG, VERSION_NO)
  END

GO

---------------------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ITEM_GROUP'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

----------------------------trigger-------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_GROUP_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_ITEM_GROUP_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_GROUP_UPD]
ON [DBO].ITEM_GROUP
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_GROUP
                  (ITEM_GROUP_SID,
                   ITEM_GROUP_NO,
                   ITEM_GROUP_NAME,
                   ITEM_GROUP_DESCRIPTION,
                   COMPANY_MASTER_SID,
                   ITEM_FILTER,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT ITEM_GROUP_SID,
             ITEM_GROUP_NO,
             ITEM_GROUP_NAME,
             ITEM_GROUP_DESCRIPTION,
             COMPANY_MASTER_SID,
             ITEM_FILTER,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'C'
      FROM   INSERTED I
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_GROUP_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_ITEM_GROUP_INS]
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_GROUP_INS]
ON [DBO].ITEM_GROUP
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_GROUP
                  (ITEM_GROUP_SID,
                   ITEM_GROUP_NO,
                   ITEM_GROUP_NAME,
                   ITEM_GROUP_DESCRIPTION,
                   COMPANY_MASTER_SID,
                   ITEM_FILTER,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT ITEM_GROUP_SID,
             ITEM_GROUP_NO,
             ITEM_GROUP_NAME,
             ITEM_GROUP_DESCRIPTION,
             COMPANY_MASTER_SID,
             ITEM_FILTER,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_GROUP_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_ITEM_GROUP_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_GROUP_DEL]
ON [DBO].ITEM_GROUP
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_GROUP
                  (ITEM_GROUP_SID,
                   ITEM_GROUP_NO,
                   ITEM_GROUP_NAME,
                   ITEM_GROUP_DESCRIPTION,
                   COMPANY_MASTER_SID,
                   ITEM_FILTER,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT ITEM_GROUP_SID,
             ITEM_GROUP_NO,
             ITEM_GROUP_NAME,
             ITEM_GROUP_DESCRIPTION,
             COMPANY_MASTER_SID,
             ITEM_FILTER,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'D'
      FROM   DELETED I
  END

GO

--------------------------------ITEM_GROUP_DETAILS-----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_GROUP_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ITEM_GROUP_DETAILS]
        (
           [ITEM_GROUP_DETAILS_SID] INT IDENTITY(1, 1) NOT NULL,
           [ITEM_GROUP_SID]         INT NULL,
           [ITEM_MASTER_SID]        INT NULL,
           [CREATED_BY]             INT NOT NULL,
           [CREATED_DATE]           DATETIME NOT NULL,
           [MODIFIED_BY]            INT NOT NULL,
           [MODIFIED_DATE]          DATETIME NOT NULL,
           [VERSION_NO]             INT NOT NULL
        )
  END

GO

---------------------------------------default_constraint--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ITEM_GROUP_DETAILS')
                      AND NAME = 'DF_ITEM_GROUP_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ITEM_GROUP_DETAILS]
        ADD CONSTRAINT [DF_ITEM_GROUP_DETAILS_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ITEM_GROUP_DETAILS')
                      AND NAME = 'DF_ITEM_GROUP_DETAILS_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[ITEM_GROUP_DETAILS]
        ADD CONSTRAINT [DF_ITEM_GROUP_DETAILS_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ITEM_GROUP_DETAILS')
                      AND NAME = 'DF_ITEM_GROUP_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[ITEM_GROUP_DETAILS]
        ADD CONSTRAINT [DF_ITEM_GROUP_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ITEM_GROUP_DETAILS')
                      AND NAME = 'DF_ITEM_GROUP_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[ITEM_GROUP_DETAILS]
        ADD CONSTRAINT [DF_ITEM_GROUP_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ITEM_GROUP_DETAILS')
                      AND NAME = 'DF_ITEM_GROUP_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ITEM_GROUP_DETAILS]
        ADD CONSTRAINT [DF_ITEM_GROUP_DETAILS_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

--------------primary_key-----------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_ITEM_GROUP_DETAILS_ITEM_GROUP_DETAILS_SID'
                     AND TABLE_NAME = 'ITEM_GROUP_DETAILS')
  BEGIN
      ALTER TABLE [DBO].[ITEM_GROUP_DETAILS]
        ADD CONSTRAINT PK_ITEM_GROUP_DETAILS_ITEM_GROUP_DETAILS_SID PRIMARY KEY(ITEM_GROUP_DETAILS_SID)
  END

GO

-------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ITEM_GROUP_DETAILS'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

----------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ITEM_GROUP_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_ITEM_GROUP_DETAILS]
        (
           [ITEM_GROUP_DETAILS_SID] INT NOT NULL,
           [ITEM_GROUP_SID]         INT NULL,
           [ITEM_MASTER_SID]        INT NULL,
           [CREATED_BY]             INT NOT NULL,
           [CREATED_DATE]           DATETIME NOT NULL,
           [MODIFIED_BY]            INT NOT NULL,
           [MODIFIED_DATE]          DATETIME NOT NULL,
           [VERSION_NO]             INT NOT NULL,
           [ACTION_FLAG]            CHAR(1) NOT NULL,
           [ACTION_DATE]            DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_ITEM_GROUP_DETAILS')
                      AND NAME = 'DF_HIST_ITEM_GROUP_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_ITEM_GROUP_DETAILS]
        ADD CONSTRAINT [DF_HIST_ITEM_GROUP_DETAILS_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_ITEM_GROUP_DETAILS')
                      AND NAME = 'PK_HIST_ITEM_GROUP_DETAILS_ITEM_GROUP_DETAILS_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_ITEM_GROUP_DETAILS]
        ADD CONSTRAINT PK_HIST_ITEM_GROUP_DETAILS_ITEM_GROUP_DETAILS_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (ITEM_GROUP_DETAILS_SID, ACTION_FLAG, VERSION_NO)
  END

GO

---------------------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ITEM_GROUP_DETAILS'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

------------------trigger-------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_GROUP_DETAILS_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_ITEM_GROUP_DETAILS_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_GROUP_DETAILS_UPD]
ON [DBO].ITEM_GROUP_DETAILS
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_GROUP_DETAILS
                  (ITEM_GROUP_DETAILS_SID,
                   ITEM_GROUP_SID,
                   ITEM_MASTER_SID,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT ITEM_GROUP_DETAILS_SID,
             ITEM_GROUP_SID,
             ITEM_MASTER_SID,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'C'
      FROM   INSERTED I
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_GROUP_DETAILS_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_ITEM_GROUP_DETAILS_INS]
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_GROUP_DETAILS_INS]
ON [DBO].ITEM_GROUP_DETAILS
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_GROUP_DETAILS
                  (ITEM_GROUP_DETAILS_SID,
                   ITEM_GROUP_SID,
                   ITEM_MASTER_SID,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT ITEM_GROUP_DETAILS_SID,
             ITEM_GROUP_SID,
             ITEM_MASTER_SID,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_ITEM_GROUP_DETAILS_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_ITEM_GROUP_DETAILS_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_ITEM_GROUP_DETAILS_DEL]
ON [DBO].ITEM_GROUP_DETAILS
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_ITEM_GROUP_DETAILS
                  (ITEM_GROUP_DETAILS_SID,
                   ITEM_GROUP_SID,
                   ITEM_MASTER_SID,
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT ITEM_GROUP_DETAILS_SID,
             ITEM_GROUP_SID,
             ITEM_MASTER_SID,
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'D'
      FROM   DELETED I
  END

GO

-------------------------RELATIONSHIP_BUILDER-------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'RELATIONSHIP_BUILDER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[RELATIONSHIP_BUILDER]
        (
           [RELATIONSHIP_BUILDER_SID] INT IDENTITY(1, 1) NOT NULL,
           [RELATIONSHIP_NAME]        VARCHAR(200) NULL,
           [RELATIONSHIP_TYPE]        INT NULL,
           [RELATIONSHIP_DESCRIPTION] VARCHAR(200) NULL,
           [HIERARCHY_DEFINITION_SID] INT NULL,
           [START_DATE]               DATETIME NULL,
           [BUILD_TYPE]               VARCHAR(20) NULL,
           [VERSION_NO]               INT NOT NULL,
           [HIERARCHY_VERSION]        INT NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL
        )
  END

GO

---------------------------default_constraint--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RELATIONSHIP_BUILDER')
                      AND NAME = 'DF_RELATIONSHIP_BUILDER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[RELATIONSHIP_BUILDER]
        ADD CONSTRAINT [DF_RELATIONSHIP_BUILDER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RELATIONSHIP_BUILDER')
                      AND NAME = 'DF_RELATIONSHIP_BUILDER_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[RELATIONSHIP_BUILDER]
        ADD CONSTRAINT [DF_RELATIONSHIP_BUILDER_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RELATIONSHIP_BUILDER')
                      AND NAME = 'DF_RELATIONSHIP_BUILDER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RELATIONSHIP_BUILDER]
        ADD CONSTRAINT [DF_RELATIONSHIP_BUILDER_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RELATIONSHIP_BUILDER')
                      AND NAME = 'DF_RELATIONSHIP_BUILDER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[RELATIONSHIP_BUILDER]
        ADD CONSTRAINT [DF_RELATIONSHIP_BUILDER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RELATIONSHIP_BUILDER')
                      AND NAME = 'DF_RELATIONSHIP_BUILDER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RELATIONSHIP_BUILDER]
        ADD CONSTRAINT [DF_RELATIONSHIP_BUILDER_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

--Additional Columns
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RELATIONSHIP_BUILDER'
                      AND COLUMN_NAME = 'BUILD_TYPE')
  BEGIN
      ALTER TABLE RELATIONSHIP_BUILDER
        ADD BUILD_TYPE VARCHAR(20)
  END

GO


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RELATIONSHIP_BUILDER'
                      AND COLUMN_NAME = 'DEDUCTION_RELATION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RELATIONSHIP_BUILDER
        ADD DEDUCTION_RELATION  INT
  END

GO

---------------------------primary_key-----------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_RELATIONSHIP_BUILDER_RELATIONSHIP_BUILDER_SID'
                     AND TABLE_NAME = 'RELATIONSHIP_BUILDER')
  ALTER TABLE RELATIONSHIP_BUILDER
    ADD CONSTRAINT PK_RELATIONSHIP_BUILDER_RELATIONSHIP_BUILDER_SID PRIMARY KEY(RELATIONSHIP_BUILDER_SID)

GO

------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'RELATIONSHIP_BUILDER'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

----------------------------------RELATIONSHIP_LEVEL_DEFINITION----------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'RELATIONSHIP_LEVEL_DEFINITION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[RELATIONSHIP_LEVEL_DEFINITION]
        (
           [RELATIONSHIP_LEVEL_SID]         INT IDENTITY(1, 1) NOT NULL,
           [RELATIONSHIP_BUILDER_SID]       INT NULL,
           [HIERARCHY_LEVEL_DEFINITION_SID] INT NULL,
           [RELATIONSHIP_LEVEL_VALUES]      VARCHAR(50) NULL,
           [LEVEL_NO]                       VARCHAR(50) NULL,
           [LEVEL_NAME]                     VARCHAR(200) NULL,
           [PARENT_NODE]                    VARCHAR(100) NULL,
           [HIERARCHY_NO]                   VARCHAR(50) NULL,
           [FLAG]                           VARCHAR(50) NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL,
           [VERSION_NO]                     INT NOT NULL
        )
  END

GO

------------------------default_constraint--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RELATIONSHIP_LEVEL_DEFINITION')
                      AND NAME = 'DF_RELATIONSHIP_LEVEL_DEFINITION_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[RELATIONSHIP_LEVEL_DEFINITION]
        ADD CONSTRAINT [DF_RELATIONSHIP_LEVEL_DEFINITION_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RELATIONSHIP_LEVEL_DEFINITION')
                      AND NAME = 'DF_RELATIONSHIP_LEVEL_DEFINITION_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[RELATIONSHIP_LEVEL_DEFINITION]
        ADD CONSTRAINT [DF_RELATIONSHIP_LEVEL_DEFINITION_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RELATIONSHIP_LEVEL_DEFINITION')
                      AND NAME = 'DF_RELATIONSHIP_LEVEL_DEFINITION_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[RELATIONSHIP_LEVEL_DEFINITION]
        ADD CONSTRAINT [DF_RELATIONSHIP_LEVEL_DEFINITION_CREATED_DATE] DEFAULT Getdate() FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RELATIONSHIP_LEVEL_DEFINITION')
                      AND NAME = 'DF_RELATIONSHIP_LEVEL_DEFINITION_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[RELATIONSHIP_LEVEL_DEFINITION]
        ADD CONSTRAINT [DF_RELATIONSHIP_LEVEL_DEFINITION_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.RELATIONSHIP_LEVEL_DEFINITION')
                      AND NAME = 'DF_RELATIONSHIP_LEVEL_DEFINITION_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[RELATIONSHIP_LEVEL_DEFINITION]
        ADD CONSTRAINT [DF_RELATIONSHIP_LEVEL_DEFINITION_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RELATIONSHIP_LEVEL_DEFINITION'
                      AND COLUMN_NAME = 'HIERARCHY_NO'
                      AND TABLE_SCHEMA = 'DBO' AND DATA_TYPE='VARCHAR')
  BEGIN
      ALTER TABLE RELATIONSHIP_LEVEL_DEFINITION
       ALTER COLUMN HIERARCHY_NO VARCHAR (8000);
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RELATIONSHIP_LEVEL_DEFINITION'
                      AND COLUMN_NAME = 'PARENT_HIERARCHY_NO')
  BEGIN
     ALTER TABLE RELATIONSHIP_LEVEL_DEFINITION ADD PARENT_HIERARCHY_NO VARCHAR(8000)
  END

GO
--------------------------primary_key-----------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_RELATIONSHIP_LEVEL_DEFINITION_RELATIONSHIP_LEVEL_SID'
                     AND TABLE_NAME = 'RELATIONSHIP_LEVEL_DEFINITION')
  ALTER TABLE RELATIONSHIP_LEVEL_DEFINITION
    ADD CONSTRAINT PK_RELATIONSHIP_LEVEL_DEFINITION_RELATIONSHIP_LEVEL_SID PRIMARY KEY(RELATIONSHIP_LEVEL_SID)

GO
----------------------------------UPDATING PARENT HIERARCHY_NO----------------------------
IF EXISTS (
		SELECT 1
		FROM RELATIONSHIP_LEVEL_DEFINITION
		WHERE PARENT_HIERARCHY_NO IS NULL
		)
	UPDATE A
	SET A.PARENT_HIERARCHY_NO = V.C
		,a.VERSION_NO = a.VERSION_NO + 1
	FROM RELATIONSHIP_LEVEL_DEFINITION A
	JOIN (
		SELECT R1.RELATIONSHIP_LEVEL_SID
			,CONCAT (
				R1.RELATIONSHIP_BUILDER_SID
				,B.PARENT_HI
				)
		FROM RELATIONSHIP_LEVEL_DEFINITION R1
		CROSS APPLY (
			SELECT '~' + RELATIONSHIP_LEVEL_VALUES
			FROM RELATIONSHIP_LEVEL_DEFINITION R2
			WHERE R1.RELATIONSHIP_BUILDER_SID = R2.RELATIONSHIP_BUILDER_SID
				AND R1.LEVEL_NO >= R2.LEVEL_NO
				AND R1.HIERARCHY_NO LIKE R2.HIERARCHY_NO + '%'
			ORDER BY LEVEL_NO
			FOR XML PATH('')
			) B(PARENT_HI)
		) V(A, C) ON V.A = A.RELATIONSHIP_LEVEL_SID
		AND a.PARENT_HIERARCHY_NO IS NULL
GO


--------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'RELATIONSHIP_LEVEL_DEFINITION'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

--------------------------HIST_RELATIONSHIP_BUILDER--------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_RELATIONSHIP_BUILDER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_RELATIONSHIP_BUILDER]
        (
           [RELATIONSHIP_BUILDER_SID] INT NOT NULL,
           [RELATIONSHIP_NAME]        VARCHAR(200) NULL,
           [RELATIONSHIP_TYPE]        INT NULL,
           [RELATIONSHIP_DESCRIPTION] VARCHAR(200) NULL,
           [HIERARCHY_DEFINITION_SID] INT NULL,
           [START_DATE]               DATETIME NULL,
           [BUILD_TYPE]               VARCHAR(20) NULL,
           [VERSION_NO]               INT NOT NULL,
           [HIERARCHY_VERSION]        INT NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL,
           [ACTION_FLAG]              CHAR(1) NOT NULL,
           [ACTION_DATE]              DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_RELATIONSHIP_BUILDER')
                      AND NAME = 'DF_HIST_RELATIONSHIP_BUILDER_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_RELATIONSHIP_BUILDER]
        ADD CONSTRAINT [DF_HIST_RELATIONSHIP_BUILDER_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_RELATIONSHIP_BUILDER')
                      AND NAME = 'PK_HIST_RELATIONSHIP_BUILDER_RELATIONSHIP_BUILDER_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_RELATIONSHIP_BUILDER]
        ADD CONSTRAINT PK_HIST_RELATIONSHIP_BUILDER_RELATIONSHIP_BUILDER_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (RELATIONSHIP_BUILDER_SID, ACTION_FLAG, VERSION_NO)
  END

GO

--Additional Column
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RELATIONSHIP_BUILDER'
                      AND COLUMN_NAME = 'BUILD_TYPE')
  BEGIN
      ALTER TABLE HIST_RELATIONSHIP_BUILDER
        ADD BUILD_TYPE VARCHAR(20)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RELATIONSHIP_BUILDER'
                      AND COLUMN_NAME = 'DEDUCTION_RELATION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RELATIONSHIP_BUILDER
        ADD DEDUCTION_RELATION  INT
  END

GO


---------------------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_RELATIONSHIP_BUILDER'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

------------------------------trigger-------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RELATIONSHIP_BUILDER_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_RELATIONSHIP_BUILDER_DEL]
  END

GO

CREATE TRIGGER [dbo].[TRG_RELATIONSHIP_BUILDER_DEL]
ON [dbo].[RELATIONSHIP_BUILDER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_RELATIONSHIP_BUILDER
                  (RELATIONSHIP_BUILDER_SID,
                   RELATIONSHIP_NAME,
                   RELATIONSHIP_TYPE,
                   RELATIONSHIP_DESCRIPTION,
                   HIERARCHY_DEFINITION_SID,
                   [START_DATE],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [HIERARCHY_VERSION],
                   [BUILD_TYPE],
                   [ACTION_FLAG],
				   DEDUCTION_RELATION)
      SELECT RELATIONSHIP_BUILDER_SID,
             RELATIONSHIP_NAME,
             RELATIONSHIP_TYPE,
             RELATIONSHIP_DESCRIPTION,
             HIERARCHY_DEFINITION_SID,
             [START_DATE],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             [HIERARCHY_VERSION],
             [BUILD_TYPE],
             'D',
			 DEDUCTION_RELATION
      FROM   DELETED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RELATIONSHIP_BUILDER_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_RELATIONSHIP_BUILDER_INS]
  END

GO

CREATE TRIGGER [dbo].[TRG_RELATIONSHIP_BUILDER_INS]
ON [dbo].[RELATIONSHIP_BUILDER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_RELATIONSHIP_BUILDER
                  (RELATIONSHIP_BUILDER_SID,
                   RELATIONSHIP_NAME,
                   RELATIONSHIP_TYPE,
                   RELATIONSHIP_DESCRIPTION,
                   HIERARCHY_DEFINITION_SID,
                   [START_DATE],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [HIERARCHY_VERSION],
                   [BUILD_TYPE],
                   [ACTION_FLAG],
				   DEDUCTION_RELATION)
      SELECT RELATIONSHIP_BUILDER_SID,
             RELATIONSHIP_NAME,
             RELATIONSHIP_TYPE,
             RELATIONSHIP_DESCRIPTION,
             HIERARCHY_DEFINITION_SID,
             [START_DATE],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             [HIERARCHY_VERSION],
             [BUILD_TYPE],
             'A',
			 DEDUCTION_RELATION
      FROM   INSERTED
  END

go

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RELATIONSHIP_BUILDER_UPD')
  BEGIN
      DROP TRIGGER DBO.[TRG_RELATIONSHIP_BUILDER_UPD]
  END

go

CREATE TRIGGER [dbo].[TRG_RELATIONSHIP_BUILDER_UPD]
ON [dbo].[RELATIONSHIP_BUILDER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_RELATIONSHIP_BUILDER
                  (RELATIONSHIP_BUILDER_SID,
                   RELATIONSHIP_NAME,
                   RELATIONSHIP_TYPE,
                   RELATIONSHIP_DESCRIPTION,
                   HIERARCHY_DEFINITION_SID,
                   [START_DATE],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [HIERARCHY_VERSION],
                   [BUILD_TYPE],
                   [ACTION_FLAG],
				   DEDUCTION_RELATION)
      SELECT RELATIONSHIP_BUILDER_SID,
             RELATIONSHIP_NAME,
             RELATIONSHIP_TYPE,
             RELATIONSHIP_DESCRIPTION,
             HIERARCHY_DEFINITION_SID,
             [START_DATE],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             [HIERARCHY_VERSION],
             [BUILD_TYPE],
             'C',
			 DEDUCTION_RELATION
      FROM   INSERTED
  END

GO

-----------------------------HIST_RELATIONSHIP_LEVEL_DEFN-------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_RELATIONSHIP_LEVEL_DEFN'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_RELATIONSHIP_LEVEL_DEFN]
        (
           [RELATIONSHIP_LEVEL_SID]         INT NOT NULL,
           [RELATIONSHIP_BUILDER_SID]       INT NULL,
           [HIERARCHY_LEVEL_DEFINITION_SID] INT NULL,
           [RELATIONSHIP_LEVEL_VALUES]      VARCHAR(50) NULL,
           [LEVEL_NO]                       VARCHAR(50) NULL,
           [LEVEL_NAME]                     VARCHAR(200) NULL,
           [PARENT_NODE]                    VARCHAR(100) NULL,
           [HIERARCHY_NO]                   VARCHAR(50) NULL,
           [FLAG]                           VARCHAR(50) NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL,
           [VERSION_NO]                     INT NOT NULL,
           [ACTION_FLAG]                    CHAR(1) NOT NULL,
           [ACTION_DATE]                    DATETIME NOT NULL
        )
  END

GO

IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RELATIONSHIP_LEVEL_DEFN'
                      AND COLUMN_NAME = 'HIERARCHY_NO'
                      AND TABLE_SCHEMA = 'DBO' AND DATA_TYPE='VARCHAR')
  BEGIN
      ALTER TABLE HIST_RELATIONSHIP_LEVEL_DEFN
       ALTER COLUMN HIERARCHY_NO VARCHAR (8000);
  END

GO



IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_RELATIONSHIP_LEVEL_DEFN')
                      AND NAME = 'DF_HIST_RELATIONSHIP_LEVEL_DEFN_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_RELATIONSHIP_LEVEL_DEFN]
        ADD CONSTRAINT [DF_HIST_RELATIONSHIP_LEVEL_DEFN_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   sys.key_constraints
               WHERE  parent_object_id = Object_id('dbo.HIST_RELATIONSHIP_LEVEL_DEFN')
                      AND NAME = 'PK_HIST_RELATIONSHIP_LEVEL_DEFN_RELATIONSHIP_LEVEL_SID_ACTION_FLAG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[HIST_RELATIONSHIP_LEVEL_DEFN]
        ADD CONSTRAINT PK_HIST_RELATIONSHIP_LEVEL_DEFN_RELATIONSHIP_LEVEL_SID_ACTION_FLAG_VERSION_NO PRIMARY KEY (RELATIONSHIP_LEVEL_SID, ACTION_FLAG, VERSION_NO)
  END

GO

---------------------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_RELATIONSHIP_LEVEL_DEFN'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

----------------------------trigger-------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RELATIONSHIP_LEVEL_DEFINITION_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_RELATIONSHIP_LEVEL_DEFINITION_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_RELATIONSHIP_LEVEL_DEFINITION_UPD]
ON [DBO].RELATIONSHIP_LEVEL_DEFINITION
--INSTEAD OF UPDATE
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      --UPDATE A
      --SET    A.[RELATIONSHIP_BUILDER_SID] = B.[RELATIONSHIP_BUILDER_SID],
      --       A.[HIERARCHY_LEVEL_DEFINITION_SID] = B.[HIERARCHY_LEVEL_DEFINITION_SID],
      --       A.[RELATIONSHIP_LEVEL_VALUES] = B.[RELATIONSHIP_LEVEL_VALUES],
      --       A.[LEVEL_NO] = B.[LEVEL_NO],
      --       A.[LEVEL_NAME] = B.[LEVEL_NAME],
      --       A.[PARENT_NODE] = B.[PARENT_NODE],
      --       A.[CREATED_BY] = B.[CREATED_BY],
      --       A.[CREATED_DATE] = B.[CREATED_DATE],
      --       A.[MODIFIED_BY] = B.[MODIFIED_BY],
      --       A.[MODIFIED_DATE] = B.[MODIFIED_DATE],
      --       A.[VERSION_NO] = A.[VERSION_NO] + 1
      --FROM   RELATIONSHIP_LEVEL_DEFINITION A
      --       JOIN (SELECT *
      --             FROM   INSERTED
      --             EXCEPT
      --             SELECT *
      --             FROM   DELETED) B ON B.[RELATIONSHIP_LEVEL_SID] = A.[RELATIONSHIP_LEVEL_SID]
      --INSERT INTO HIST_RELATIONSHIP_LEVEL_DEFN
      --            (RELATIONSHIP_LEVEL_SID,
      --             RELATIONSHIP_BUILDER_SID,
      --             HIERARCHY_LEVEL_DEFINITION_SID,
      --             RELATIONSHIP_LEVEL_VALUES,
      --             LEVEL_NO,
      --             LEVEL_NAME,
      --             PARENT_NODE,
      --             [CREATED_BY],
      --             [CREATED_DATE],
      --             [MODIFIED_BY],
      --             [MODIFIED_DATE],
      --             [VERSION_NO],
      --             [ACTION_FLAG])
      --SELECT RELATIONSHIP_LEVEL_SID,
      --       RELATIONSHIP_BUILDER_SID,
      --       HIERARCHY_LEVEL_DEFINITION_SID,
      --       RELATIONSHIP_LEVEL_VALUES,
      --       LEVEL_NO,
      --       LEVEL_NAME,
      --       PARENT_NODE,
      --       [CREATED_BY],
      --       [CREATED_DATE],
      --       [MODIFIED_BY],
      --       [MODIFIED_DATE],
      --       [VERSION_NO]+1,
      --       'C'
      --FROM   INSERTED I
      --WHERE  EXISTS (SELECT 1
      --               FROM   (SELECT *
      --                       FROM   INSERTED
      --                       EXCEPT
      --                       SELECT *
      --                       FROM   DELETED) A
      --               WHERE  A.[RELATIONSHIP_LEVEL_SID] = I.[RELATIONSHIP_LEVEL_SID])
      INSERT INTO HIST_RELATIONSHIP_LEVEL_DEFN
                  (RELATIONSHIP_LEVEL_SID,
                   RELATIONSHIP_BUILDER_SID,
                   HIERARCHY_LEVEL_DEFINITION_SID,
                   RELATIONSHIP_LEVEL_VALUES,
                   LEVEL_NO,
                   LEVEL_NAME,
                   PARENT_NODE,
                   [HIERARCHY_NO],
                   [FLAG],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT RELATIONSHIP_LEVEL_SID,
             RELATIONSHIP_BUILDER_SID,
             HIERARCHY_LEVEL_DEFINITION_SID,
             RELATIONSHIP_LEVEL_VALUES,
             LEVEL_NO,
             LEVEL_NAME,
             PARENT_NODE,
             [HIERARCHY_NO],
             [FLAG],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RELATIONSHIP_LEVEL_DEFINITION_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_RELATIONSHIP_LEVEL_DEFINITION_INS]
  END

GO

CREATE TRIGGER [DBO].[TRG_RELATIONSHIP_LEVEL_DEFINITION_INS]
ON [DBO].RELATIONSHIP_LEVEL_DEFINITION
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_RELATIONSHIP_LEVEL_DEFN
                  (RELATIONSHIP_LEVEL_SID,
                   RELATIONSHIP_BUILDER_SID,
                   HIERARCHY_LEVEL_DEFINITION_SID,
                   RELATIONSHIP_LEVEL_VALUES,
                   LEVEL_NO,
                   LEVEL_NAME,
                   PARENT_NODE,
                   [HIERARCHY_NO],
                   [FLAG],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT RELATIONSHIP_LEVEL_SID,
             RELATIONSHIP_BUILDER_SID,
             HIERARCHY_LEVEL_DEFINITION_SID,
             RELATIONSHIP_LEVEL_VALUES,
             LEVEL_NO,
             LEVEL_NAME,
             PARENT_NODE,
             [HIERARCHY_NO],
             [FLAG],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RELATIONSHIP_LEVEL_DEFINITION_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_RELATIONSHIP_LEVEL_DEFINITION_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_RELATIONSHIP_LEVEL_DEFINITION_DEL]
ON [DBO].RELATIONSHIP_LEVEL_DEFINITION
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_RELATIONSHIP_LEVEL_DEFN
                  (RELATIONSHIP_LEVEL_SID,
                   RELATIONSHIP_BUILDER_SID,
                   HIERARCHY_LEVEL_DEFINITION_SID,
                   RELATIONSHIP_LEVEL_VALUES,
                   LEVEL_NO,
                   LEVEL_NAME,
                   PARENT_NODE,
                   [HIERARCHY_NO],
                   [FLAG],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [VERSION_NO],
                   [ACTION_FLAG])
      SELECT RELATIONSHIP_LEVEL_SID,
             RELATIONSHIP_BUILDER_SID,
             HIERARCHY_LEVEL_DEFINITION_SID,
             RELATIONSHIP_LEVEL_VALUES,
             LEVEL_NO,
             LEVEL_NAME,
             PARENT_NODE,
             [HIERARCHY_NO],
             [FLAG],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             [VERSION_NO],
             'D'
      FROM   DELETED I
  END

GO

--------------------------FORECAST_CONFIG------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [dbo].[FORECAST_CONFIG]
        (
           [FORECAST_CONFIG_SID]   [INT] IDENTITY(1, 1) NOT NULL,
           [BUSINESS_PROCESS_TYPE] [VARCHAR](50) NOT NULL,
           [PROCESS_MODE]          [BIT] NOT NULL,
           [PROCESS_TYPE]          [BIT] NOT NULL,
           [HIST_FREQ]             VARCHAR(10) NULL,
           [HIST_VALUE]            SMALLINT NULL,
           [PROJ_FREQ]             VARCHAR(10) NULL,
           [PROJ_VALUE]            SMALLINT NULL,
           [FROM_DATE]             [DATETIME] NULL,
           [TO_DATE]               [DATETIME] NULL,
           [VERSION_NO]            [INT] NOT NULL,
           [CREATED_BY]            [INT] NOT NULL,
           [CREATED_DATE]          [DATETIME] NOT NULL,
           [MODIFIED_BY]           [INT] NOT NULL,
           [MODIFIED_DATE]         [DATETIME] NOT NULL
        )
  END

GO

---- Column Addition starts here--


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                      AND COLUMN_NAME = 'ACTIVE_START_DATE')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ADD ACTIVE_START_DATE DATETIME NULL
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                      AND COLUMN_NAME = 'ACTIVE_END_DATE')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ADD ACTIVE_END_DATE DATETIME NULL
  END

GO

IF NOT EXISTS (SELECT '1'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                      AND COLUMN_NAME = 'PROJ_HIST_FREQ'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ADD PROJ_HIST_FREQ INT
  END

GO

IF NOT EXISTS (SELECT '1'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                      AND COLUMN_NAME = 'PROJ_HIST_VALUE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ADD PROJ_HIST_VALUE INT
  END

GO

IF NOT EXISTS (SELECT '1'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                      AND COLUMN_NAME = 'HISTORICAL_DATA_INTERVAL_FROM'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ADD HISTORICAL_DATA_INTERVAL_FROM DATETIME
  END

GO

IF NOT EXISTS (SELECT '1'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                      AND COLUMN_NAME = 'HISTORICAL_DATA_INTERVAL_TO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ADD HISTORICAL_DATA_INTERVAL_TO DATETIME
  END

GO

IF NOT EXISTS (SELECT '1'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                      AND COLUMN_NAME = 'HISTORICAL_TIME_PERIOD_FROM'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ADD HISTORICAL_TIME_PERIOD_FROM DATETIME
  END

GO

IF NOT EXISTS (SELECT '1'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                      AND COLUMN_NAME = 'FUTURE_TIME_PERIOD_FROM'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ADD FUTURE_TIME_PERIOD_FROM DATETIME
  END 

GO


---- Column Addition ends here--

-------------------------------------------------------data type change starts here ---------------------------------------------------------------------
--HIST_FREQ
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'FORECAST_CONFIG'
                  AND AUTO_CREATED = 0
                  AND NAME = 'HIST_FREQ')
  BEGIN
        DROP STATISTICS FORECAST_CONFIG.HIST_FREQ
  END

GO 
--PROJ_FREQ
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'FORECAST_CONFIG'
                  AND AUTO_CREATED = 0
                  AND NAME = 'PROJ_FREQ')
  BEGIN
        DROP STATISTICS FORECAST_CONFIG.PROJ_FREQ
  END

GO 
--PROJ_HIST_VALUE
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'FORECAST_CONFIG'
                  AND AUTO_CREATED = 0
                  AND NAME = 'PROJ_HIST_VALUE')
  BEGIN
        DROP STATISTICS FORECAST_CONFIG.PROJ_HIST_VALUE
  END

GO 

--BUSINESS_PROCESS_TYPE
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  Object_name(OBJECT_ID) = 'FORECAST_CONFIG'
                  AND AUTO_CREATED = 0
                  AND NAME = 'BUSINESS_PROCESS_TYPE')
  BEGIN
        DROP STATISTICS FORECAST_CONFIG.BUSINESS_PROCESS_TYPE
  END

GO


--HIST_FREQ
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                  AND COLUMN_NAME = 'HIST_FREQ'
                  AND TABLE_SCHEMA = 'DBO'
				  AND DATA_TYPE='VARCHAR')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ALTER COLUMN HIST_FREQ INT 
  END

GO
--PROJ_FREQ
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                  AND COLUMN_NAME = 'PROJ_FREQ'
                  AND TABLE_SCHEMA = 'DBO'
				  AND DATA_TYPE='VARCHAR')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ALTER COLUMN PROJ_FREQ INT 
  END

GO
--PROJ_HIST_VALUE
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                  AND COLUMN_NAME = 'PROJ_HIST_VALUE'
                  AND TABLE_SCHEMA = 'DBO'
				  AND DATA_TYPE='VARCHAR')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ALTER COLUMN PROJ_HIST_VALUE INT 
  END

GO
--BUSINESS_PROCESS_TYPE
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'FORECAST_CONFIG'
                  AND COLUMN_NAME = 'BUSINESS_PROCESS_TYPE'
                  AND TABLE_SCHEMA = 'DBO'
				  AND DATA_TYPE='VARCHAR')
  BEGIN
      ALTER TABLE FORECAST_CONFIG
        ALTER COLUMN BUSINESS_PROCESS_TYPE INT NOT NULL
  END

GO
----------------------------------------datatype changes ends here--------------------------------------------------------------------------------
-------------------------------Primary Key
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_FORECAST_CONFIG_FORECAST_CONFIG_SID'
                     AND TABLE_NAME = 'FORECAST_CONFIG')

 BEGIN
  ALTER TABLE FORECAST_CONFIG
    ADD CONSTRAINT PK_FORECAST_CONFIG_FORECAST_CONFIG_SID PRIMARY KEY(FORECAST_CONFIG_SID)
 END

GO

-------------------------------Default Constraints
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.FORECAST_CONFIG')
                      AND NAME = 'DF_FORECAST_CONFIG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[FORECAST_CONFIG]
        ADD CONSTRAINT [DF_FORECAST_CONFIG_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.FORECAST_CONFIG')
                      AND NAME = 'DF_FORECAST_CONFIG_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[FORECAST_CONFIG]
        ADD CONSTRAINT [DF_FORECAST_CONFIG_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.FORECAST_CONFIG')
                      AND NAME = 'DF_FORECAST_CONFIG_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[FORECAST_CONFIG]
        ADD CONSTRAINT [DF_FORECAST_CONFIG_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.FORECAST_CONFIG')
                      AND NAME = 'DF_FORECAST_CONFIG_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[FORECAST_CONFIG]
        ADD CONSTRAINT [DF_FORECAST_CONFIG_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.FORECAST_CONFIG')
                      AND NAME = 'DF_FORECAST_CONFIG_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[FORECAST_CONFIG]
        ADD CONSTRAINT [DF_FORECAST_CONFIG_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.FORECAST_CONFIG')
                      AND NAME = 'DF_FORECAST_CONFIG_PROCESS_MODE')
  BEGIN
      ALTER TABLE [DBO].[FORECAST_CONFIG]
        ADD CONSTRAINT [DF_FORECAST_CONFIG_PROCESS_MODE] DEFAULT (0) FOR PROCESS_MODE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.FORECAST_CONFIG')
                      AND NAME = 'DF_FORECAST_CONFIG_PROCESS_TYPE')
  BEGIN
      ALTER TABLE [DBO].[FORECAST_CONFIG]
        ADD CONSTRAINT [DF_FORECAST_CONFIG_PROCESS_TYPE] DEFAULT (0) FOR PROCESS_TYPE
  END

GO

---------------------------------------statistics-----------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'FORECAST_CONFIG'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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


--------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'IMTD_LEVEL_VALUES'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[IMTD_LEVEL_VALUES]
        (
           [IMTD_LEVEL_VALUES_SID] INT IDENTITY(1, 1) NOT NULL,
           [LEVEL_NO]              INT NULL,
           [LEVEL_NAME]            VARCHAR(100) NULL,
           [LEVEL_VALUES]          VARCHAR(100) NULL,
           [CREATED_BY]            INT NOT NULL,
           [CREATED_DATE]          DATETIME NOT NULL,
           [MODIFIED_BY]           INT NOT NULL,
           [MODIFIED_DATE]         DATETIME NOT NULL,
           [VERSION_NO]            INT NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_name(object_id) = N'IMTD_LEVEL_VALUES'
                      AND OBJECT_SCHEMA_NAME(object_id) = N'dbo'
                      AND [Name] = N'PK_IMTD_LEVEL_VALUES_IMTD_LEVEL_VALUES_SID')
  BEGIN
      ALTER TABLE [dbo].[IMTD_LEVEL_VALUES]
        ADD CONSTRAINT [PK_IMTD_LEVEL_VALUES_IMTD_LEVEL_VALUES_SID] PRIMARY KEY CLUSTERED ( [IMTD_LEVEL_VALUES_SID] ASC ) WITH (FILLFACTOR = 80) ON [PRIMARY]
  END

GO
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.IMTD_LEVEL_VALUES')
                      AND NAME = 'DF_IMTD_LEVEL_VALUES_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[IMTD_LEVEL_VALUES]
        ADD CONSTRAINT [DF_IMTD_LEVEL_VALUES_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.IMTD_LEVEL_VALUES')
                      AND NAME = 'DF_IMTD_LEVEL_VALUES_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[IMTD_LEVEL_VALUES]
        ADD CONSTRAINT [DF_IMTD_LEVEL_VALUES_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.IMTD_LEVEL_VALUES')
                      AND NAME = 'DF_IMTD_LEVEL_VALUES_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[IMTD_LEVEL_VALUES]
        ADD CONSTRAINT [DF_IMTD_LEVEL_VALUES_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.IMTD_LEVEL_VALUES')
                      AND NAME = 'DF_IMTD_LEVEL_VALUES_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[IMTD_LEVEL_VALUES]
        ADD CONSTRAINT [DF_IMTD_LEVEL_VALUES_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'IMTD_LEVEL_VALUES'--TABLE NAME
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
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1)
  BEGIN
      DECLARE CUR CURSOR STATIC FOR
        SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME',
               S.NAME                   AS 'STATSNAME',
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME'
        FROM   SYS.STATS S
               JOIN SYS.TABLES T
                 ON S.OBJECT_ID = T.OBJECT_ID
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

--------------------------------------- PROJECTION NAME CONFIG ----------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'PROJECTION_NAME_CONFIG'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE PROJECTION_NAME_CONFIG
        (
           [PROJECTION_NAME_CONFIG_SID] INT IDENTITY(1, 1) NOT NULL,
           [BUSINESS_PROCESS_TYPE]      VARCHAR(50) NOT NULL,
           [SELECTED_ATTRIBUTES]        VARCHAR(400) NOT NULL,
           [VERSION_NO]                 [INT] NOT NULL,
           [CREATED_BY]                 [INT] NOT NULL,
           [CREATED_DATE]               [DATETIME] NOT NULL,
           [MODIFIED_BY]                [INT] NOT NULL,
           [MODIFIED_DATE]              [DATETIME] NOT NULL
        )
  END

GO

--Primary Key
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_PROJECTION_NAME_CONFIG_PROJECTION_NAME_CONFIG_SID'
                     AND TABLE_NAME = 'PROJECTION_NAME_CONFIG')
  ALTER TABLE PROJECTION_NAME_CONFIG
    ADD CONSTRAINT PK_PROJECTION_NAME_CONFIG_PROJECTION_NAME_CONFIG_SID PRIMARY KEY(PROJECTION_NAME_CONFIG_SID)

GO

--Default Constraints
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.PROJECTION_NAME_CONFIG')
                      AND NAME = 'DF_PROJECTION_NAME_CONFIG_VERSION_NO')
  BEGIN
      ALTER TABLE [DBO].[PROJECTION_NAME_CONFIG]
        ADD CONSTRAINT [DF_PROJECTION_NAME_CONFIG_VERSION_NO] DEFAULT (1) FOR VERSION_NO
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.PROJECTION_NAME_CONFIG')
                      AND NAME = 'DF_PROJECTION_NAME_CONFIG_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[PROJECTION_NAME_CONFIG]
        ADD CONSTRAINT [DF_PROJECTION_NAME_CONFIG_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.PROJECTION_NAME_CONFIG')
                      AND NAME = 'DF_PROJECTION_NAME_CONFIG_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PROJECTION_NAME_CONFIG]
        ADD CONSTRAINT [DF_PROJECTION_NAME_CONFIG_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.PROJECTION_NAME_CONFIG')
                      AND NAME = 'DF_PROJECTION_NAME_CONFIG_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[PROJECTION_NAME_CONFIG]
        ADD CONSTRAINT [DF_PROJECTION_NAME_CONFIG_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.PROJECTION_NAME_CONFIG')
                      AND NAME = 'DF_PROJECTION_NAME_CONFIG_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PROJECTION_NAME_CONFIG]
        ADD CONSTRAINT [DF_PROJECTION_NAME_CONFIG_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

--------------------------------------------------------HIST_HIERARCHY_RULES_DEFINITION--------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_HIERARCHY_RULES_DEFINITION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_HIERARCHY_RULES_DEFINITION]
        (
           [HIERARCHY_RULES_DEFINITION_SID] INT NOT NULL,
           [RULE_FLOW_GROUP_NAME]           VARCHAR(100) NOT NULL,
           [RULE_NAME]                      VARCHAR(100) NOT NULL,
           [TABLE_NAME]                     VARCHAR(30) NOT NULL,
           [COLUMN_NAME]                    VARCHAR(30) NOT NULL,
           [CONDITION]                      VARCHAR(100) NOT NULL,
           [VALUE]                          VARCHAR(100) NOT NULL,
           [INBOUND_STATUS]                 CHAR(1) NOT NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL,
           [ACTION_FLAG]                    CHAR(1) NOT NULL,
           [ACTION_DATE]                    DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_HIERARCHY_RULES_DEFINITION')
                      AND NAME = 'DF_HIST_HIERARCHY_RULES_DEFINITION_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_HIERARCHY_RULES_DEFINITION]
        ADD CONSTRAINT [DF_HIST_HIERARCHY_RULES_DEFINITION_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

----------------------------------------------------------- HIERARCHY_RULES_DEFINITION ---------------------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIERARCHY_RULES_DEFINITION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIERARCHY_RULES_DEFINITION]
        (
           [HIERARCHY_RULES_DEFINITION_SID] INT IDENTITY(1, 1) NOT NULL,
           [RULE_FLOW_GROUP_NAME]           VARCHAR(100) NOT NULL,
           [RULE_NAME]                      VARCHAR(100) NOT NULL,
           [TABLE_NAME]                     VARCHAR(30) NOT NULL,
           [COLUMN_NAME]                    VARCHAR(30) NOT NULL,
           [CONDITION]                      VARCHAR(100) NOT NULL,
           [VALUE]                          VARCHAR(100) NOT NULL,
           [INBOUND_STATUS]                 CHAR(1) NOT NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL
        )
  END

GO

--Primary Key
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'HIERARCHY_RULES_DEFINITION'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_HIERARCHY_RULES_DEFINITION_HIERARCHY_RULES_DEFINITION_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_RULES_DEFINITION]
        ADD CONSTRAINT [PK_HIERARCHY_RULES_DEFINITION_HIERARCHY_RULES_DEFINITION_SID] PRIMARY KEY ( [HIERARCHY_RULES_DEFINITION_SID])
  END

GO

-----------------DEFAULT CONSTRAINTS---------------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.DEFAULT_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'HIERARCHY_RULES_DEFINITION'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'DF_HIERARCHY_RULES_DEFINITION_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_RULES_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_RULES_DEFINITION_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS(SELECT 1
              FROM   SYS.DEFAULT_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'HIERARCHY_RULES_DEFINITION'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'DF_HIERARCHY_RULES_DEFINITION_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_RULES_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_RULES_DEFINITION_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS(SELECT 1
              FROM   SYS.DEFAULT_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'HIERARCHY_RULES_DEFINITION'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'DF_HIERARCHY_RULES_DEFINITION_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_RULES_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_RULES_DEFINITION_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS(SELECT 1
              FROM   SYS.DEFAULT_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'HIERARCHY_RULES_DEFINITION'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'DF_HIERARCHY_RULES_DEFINITION_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIERARCHY_RULES_DEFINITION]
        ADD CONSTRAINT [DF_HIERARCHY_RULES_DEFINITION_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

------Triggers
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_RULES_DEFINITION_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_HIERARCHY_RULES_DEFINITION_DEL]
  END

GO

CREATE TRIGGER [dbo].[TRG_HIERARCHY_RULES_DEFINITION_DEL]
ON [dbo].[HIERARCHY_RULES_DEFINITION]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_RULES_DEFINITION
                  ([HIERARCHY_RULES_DEFINITION_SID],
                   [RULE_FLOW_GROUP_NAME],
                   [RULE_NAME],
                   [TABLE_NAME],
                   [COLUMN_NAME],
                   [CONDITION],
                   [VALUE],
                   [INBOUND_STATUS],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [ACTION_FLAG])
      SELECT [HIERARCHY_RULES_DEFINITION_SID],
             [RULE_FLOW_GROUP_NAME],
             [RULE_NAME],
             [TABLE_NAME],
             [COLUMN_NAME],
             [CONDITION],
             [VALUE],
             [INBOUND_STATUS],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             'D'
      FROM   DELETED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_RULES_DEFINITION_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_HIERARCHY_RULES_DEFINITION_INS]
  END

GO

--------------------------
CREATE TRIGGER [dbo].[TRG_HIERARCHY_RULES_DEFINITION_INS]
ON [dbo].[HIERARCHY_RULES_DEFINITION]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_RULES_DEFINITION
                  ([HIERARCHY_RULES_DEFINITION_SID],
                   [RULE_FLOW_GROUP_NAME],
                   [RULE_NAME],
                   [TABLE_NAME],
                   [COLUMN_NAME],
                   [CONDITION],
                   [VALUE],
                   [INBOUND_STATUS],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [ACTION_FLAG])
      SELECT [HIERARCHY_RULES_DEFINITION_SID],
             [RULE_FLOW_GROUP_NAME],
             [RULE_NAME],
             [TABLE_NAME],
             [COLUMN_NAME],
             [CONDITION],
             [VALUE],
             [INBOUND_STATUS],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             'A'
      FROM   INSERTED
  END

GO

-----------------------------------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HIERARCHY_RULES_DEFINITION_UPD')
  BEGIN
      DROP TRIGGER DBO.[TRG_HIERARCHY_RULES_DEFINITION_UPD]
  END

GO

CREATE TRIGGER [dbo].[TRG_HIERARCHY_RULES_DEFINITION_UPD]
ON [dbo].[HIERARCHY_RULES_DEFINITION]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HIERARCHY_RULES_DEFINITION
                  ([HIERARCHY_RULES_DEFINITION_SID],
                   [RULE_FLOW_GROUP_NAME],
                   [RULE_NAME],
                   [TABLE_NAME],
                   [COLUMN_NAME],
                   [CONDITION],
                   [VALUE],
                   [INBOUND_STATUS],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [ACTION_FLAG])
      SELECT [HIERARCHY_RULES_DEFINITION_SID],
             [RULE_FLOW_GROUP_NAME],
             [RULE_NAME],
             [TABLE_NAME],
             [COLUMN_NAME],
             [CONDITION],
             [VALUE],
             [INBOUND_STATUS],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             'C'
      FROM   INSERTED
  END

GO

---------------------------- BPM Table---------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'STPL_BPM_SSO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[STPL_BPM_SSO]
        (
           ID          INT IDENTITY(1, 1) NOT NULL,
           USERNAME    VARCHAR(100) NULL,
           PASSWORD    VARCHAR(100) NULL,
           SESSIONID   VARCHAR(100) NULL,
           CREATEDDATE DATETIME NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.STPL_BPM_SSO')
                      AND NAME = 'DF_STPL_BPM_SSO_CREATEDDATE')
  BEGIN
      ALTER TABLE [DBO].[STPL_BPM_SSO]
        ADD CONSTRAINT [DF_STPL_BPM_SSO_CREATEDDATE] DEFAULT (Getdate()) FOR CREATEDDATE
  END

GO 

IF NOT EXISTS (
		SELECT 'X'
		FROM INFORMATION_SCHEMA.TABLES
		WHERE TABLE_NAME = 'HIST_STPL_BPM_SSO'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	CREATE TABLE [DBO].[HIST_STPL_BPM_SSO] (
		ID int NOT NULL
		,USERNAME varchar(100) NULL
		,PASSWORD varchar(100) NULL
		,SESSIONID varchar(100) NULL
		,CREATEDDATE datetime NULL
		,ACTION_DATE DATETIME NOT NULL
		,ACTION_FLAG CHAR(1) NOT NULL
		)
END
GO

IF NOT EXISTS (
		SELECT 'X'
		FROM SYS.DEFAULT_CONSTRAINTS
		WHERE parent_object_id = Object_id('DBO.HIST_STPL_BPM_SSO')
			AND NAME = 'DF_HIST_STPL_BPM_SSO_ACTION_DATE'
		)
BEGIN
	ALTER TABLE [DBO].[HIST_STPL_BPM_SSO] ADD CONSTRAINT [DF_HIST_STPL_BPM_SSO_ACTION_DATE] DEFAULT(Getdate())
	FOR ACTION_DATE
END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_STPL_BPM_SSO' --TABLE NAME
SET @SCHEMANAME1 = 'dbo' -- SCHEMA NAME

IF EXISTS (
		SELECT 'X'
		FROM SYS.STATS S
		JOIN SYS.TABLES T ON S.OBJECT_ID = T.OBJECT_ID
		WHERE AUTO_CREATED = 1
			AND NOT EXISTS (
				SELECT 1
				FROM SYS.INDEXES
				WHERE S.NAME = NAME
				)
			AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
			AND Schema_name(SCHEMA_ID) = @SCHEMANAME1
		)
BEGIN
	DECLARE CUR CURSOR STATIC
	FOR
	SELECT Object_name(S.OBJECT_ID) AS 'TABLENAME'
		,S.NAME AS 'STATSNAME'
		,Schema_name(T.SCHEMA_ID) AS 'SCHEMA_NAME'
	FROM SYS.STATS S
	JOIN SYS.TABLES T ON S.OBJECT_ID = T.OBJECT_ID
	WHERE AUTO_CREATED = 1
		AND NOT EXISTS (
			SELECT 1
			FROM SYS.INDEXES
			WHERE S.NAME = NAME
			)
		AND Object_name(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME
		AND Schema_name(SCHEMA_ID) = @SCHEMANAME1

	OPEN CUR

	FETCH NEXT
	FROM CUR
	INTO @TABLENAME
		,@STATSNAME
		,@SCHEMANAME

	WHILE @@FETCH_STATUS = 0
	BEGIN
		SET @SQL = 'DROP STATISTICS ' + Quotename(@SCHEMANAME) + '.' + Quotename(@TABLENAME) + '.' + Quotename(@STATSNAME)

		--PRINT @SQL
		EXEC Sp_executesql @SQL

		FETCH NEXT
		FROM CUR
		INTO @TABLENAME
			,@STATSNAME
			,@SCHEMANAME
	END

	CLOSE CUR

	DEALLOCATE CUR
END

DECLARE @STATS NVARCHAR(MAX)

DECLARE CUR1 CURSOR STATIC
FOR
SELECT 'CREATE STATISTICS ' + Quotename(C.NAME) + ' ON ' + Quotename(Schema_name(SCHEMA_ID)) + '.' + Quotename(T.NAME) + ' (' + Quotename(C.NAME) + ') WITH FULLSCAN'
FROM SYS.TABLES T
JOIN SYS.COLUMNS C ON T.OBJECT_ID = C.OBJECT_ID
WHERE NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC
		INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME
		WHERE CC.TABLE_NAME = T.NAME
			AND CC.TABLE_SCHEMA = Schema_name(SCHEMA_ID)
			AND C.NAME = COLUMN_NAME
		)
	AND NOT EXISTS (
		SELECT 1
		FROM SYS.STATS S
		WHERE S.OBJECT_ID = C.OBJECT_ID
			AND S.NAME = C.NAME
		)
	AND T.NAME = @TABLENAME1 -- TABLE NAME
	AND Schema_name(SCHEMA_ID) = @SCHEMANAME1
ORDER BY T.NAME

OPEN CUR1

FETCH NEXT
FROM CUR1
INTO @STATS

WHILE @@FETCH_STATUS = 0
BEGIN
	--PRINT @STATS
	EXEC Sp_executesql @STATS

	FETCH NEXT
	FROM CUR1
	INTO @STATS
END

CLOSE CUR1

DEALLOCATE CUR1
GO

---------------------------------------------------TRIGGERS---------------------
IF EXISTS (
		SELECT 'X'
		FROM SYS.TRIGGERS
		WHERE [Name] = N'TRG_STPL_BPM_SSO_UPD'
		)
BEGIN
	DROP TRIGGER dbo.TRG_STPL_BPM_SSO_UPD
END
GO

CREATE TRIGGER [dbo].[TRG_STPL_BPM_SSO_UPD] ON [dbo].[STPL_BPM_SSO]
AFTER UPDATE
AS
BEGIN
SET NOCOUNT ON
	IF EXISTS (
			SELECT *
			FROM INSERTED
			)
		AND EXISTS (
			SELECT *
			FROM DELETED
			)
		INSERT INTO HIST_STPL_BPM_SSO (
			ID
			,USERNAME
			,PASSWORD
			,SESSIONID
			,CREATEDDATE
			,ACTION_FLAG
			)
		SELECT ID
			,USERNAME
			,PASSWORD
			,SESSIONID
			,CREATEDDATE
			,'C'
		FROM INSERTED
END
GO

IF EXISTS (
		SELECT 'X'
		FROM SYS.TRIGGERS
		WHERE [Name] = N'TRG_STPL_BPM_SSO_INS'
		)
BEGIN
	DROP TRIGGER dbo.TRG_STPL_BPM_SSO_INS
END
GO

CREATE TRIGGER [dbo].[TRG_STPL_BPM_SSO_INS] ON [dbo].[STPL_BPM_SSO]
AFTER INSERT
AS
BEGIN
SET NOCOUNT ON
	IF EXISTS (
			SELECT *
			FROM INSERTED
			)
		INSERT INTO HIST_STPL_BPM_SSO (
			ID
			,USERNAME
			,PASSWORD
			,SESSIONID
			,CREATEDDATE
			,ACTION_FLAG
			)
		SELECT ID
			,USERNAME
			,PASSWORD
			,SESSIONID
			,CREATEDDATE
			,'A'
		FROM INSERTED
END
GO

IF EXISTS (
		SELECT 'X'
		FROM SYS.TRIGGERS
		WHERE [Name] = N'TRG_STPL_BPM_SSO_DEL'
		)
BEGIN
	DROP TRIGGER dbo.TRG_STPL_BPM_SSO_DEL
END
GO

CREATE TRIGGER [dbo].[TRG_STPL_BPM_SSO_DEL] ON [dbo].[STPL_BPM_SSO]
AFTER DELETE
AS
BEGIN
SET NOCOUNT ON
	IF EXISTS (
			SELECT *
			FROM DELETED
			)
		INSERT INTO HIST_STPL_BPM_SSO (
			ID
			,USERNAME
			,PASSWORD
			,SESSIONID
			,CREATEDDATE
			,ACTION_FLAG
			)
		SELECT ID
			,USERNAME
			,PASSWORD
			,SESSIONID
			,CREATEDDATE
			,'D'
		FROM DELETED
END
GO




----------------------

IF NOT EXISTS (
		SELECT 'X'
		FROM INFORMATION_SCHEMA.TABLES
		WHERE TABLE_NAME = 'HIERARCHY_DISPLAY_FORMAT'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	CREATE TABLE [DBO].[HIERARCHY_DISPLAY_FORMAT] (
		HIERARCHY_DISPLAY_FORMAT_SID INT IDENTITY(1, 1) NOT NULL
		,TABLE_NAME VARCHAR(100)
		,COLUMN_NAME VARCHAR(100)
		,SELECTED_COLUMN_NAME VARCHAR(100)
		)
END
GO
IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.TABLES
		WHERE TABLE_NAME = 'HIERARCHY_LEVEL_QUERIES'
		)
	CREATE TABLE HIERARCHY_LEVEL_QUERIES (
		HIERARCHY_LEVEL_DEFINITION_SID INT NOT NULL
		,LEVEL_QUERY VARCHAR(MAX)
		)
GO

IF NOT EXISTS (
		SELECT 'X'
		FROM INFORMATION_SCHEMA.TABLE_CONSTRAINTS
		WHERE CONSTRAINT_NAME = 'PK_HIERARCHY_LEVEL_QUERIES_HIERARCHY_LEVEL_DEFINITION_SID'
			AND TABLE_NAME = 'HIERARCHY_LEVEL_QUERIES'
		)
BEGIN
	ALTER TABLE HIERARCHY_LEVEL_QUERIES ADD CONSTRAINT PK_HIERARCHY_LEVEL_QUERIES_HIERARCHY_LEVEL_DEFINITION_SID PRIMARY KEY (HIERARCHY_LEVEL_DEFINITION_SID)
END
GO
----------------------------------
INSERT INTO HIERARCHY_LEVEL_VALUES
(
LEVEL_VALUES,
HIERARCHY_LEVEL_DEFINITION_SID,
VERSION_NO
)

SELECT 
HHL.LEVEL_VALUES,
HHL.HIERARCHY_LEVEL_DEFINITION_SID,
HHL.VERSION_NO
FROM HIST_HIERARCHY_LEVEL_VALUES HHL JOIN 
HIERARCHY_LEVEL_VALUES HLV ON HHL.HIERARCHY_LEVEL_VALUES_SID=HLV.HIERARCHY_LEVEL_VALUES_SID AND ACTION_FLAG<>'D'
AND HHL.HIERARCHY_LEVEL_DEFINITION_SID IS NOT NULL
WHERE NOT EXISTS(
SELECT 1 FROM HIERARCHY_LEVEL_VALUES HLV WHERE HLV.HIERARCHY_LEVEL_DEFINITION_SID=HHL.HIERARCHY_LEVEL_DEFINITION_SID
AND HLV.VERSION_NO=HHL.VERSION_NO AND HHL.LEVEL_VALUES=HLV.LEVEL_VALUES AND HHL.HIERARCHY_LEVEL_DEFINITION_SID IS NOT NULL
)





INSERT INTO HIERARCHY_LEVEL_DEFINITION
(
LEVEL_NO,
LEVEL_NAME,
LEVEL_VALUE_REFERENCE,
HIERARCHY_DEFINITION_SID,
TABLE_NAME,
FIELD_NAME,
INCLUSION_RULE_TYPE,
INCLUSION_RULE,
EXCLUSION_RULE_TYPE,
EXCLUSION_RULE,
INCLUSION_CONDITION,
EXCLUSION_CONDITION,
VERSION_NO


)



SELECT 
HLD.LEVEL_NO,
HLD.LEVEL_NAME,
HLD.LEVEL_VALUE_REFERENCE,
HLD.HIERARCHY_DEFINITION_SID,
HLD.TABLE_NAME,
HLD.FIELD_NAME,
HLD.INCLUSION_RULE_TYPE,
HLD.INCLUSION_RULE,
HLD.EXCLUSION_RULE_TYPE,
HLD.EXCLUSION_RULE,
HLD.INCLUSION_CONDITION,
HLD.EXCLUSION_CONDITION,
HLD.VERSION_NO


FROM HIST_HIERARCHY_LEVEL_DEFN  HLD JOIN 

HIERARCHY_LEVEL_DEFINITION HD  ON HD.HIERARCHY_LEVEL_DEFINITION_SID=HLD.HIERARCHY_LEVEL_DEFINITION_SID AND ACTION_FLAG<>'D'

WHERE NOT EXISTS
(
SELECT 1 FROM HIERARCHY_LEVEL_DEFINITION  HMS WHERE  
HLD.LEVEL_NO=                          LEVEL_NO                   AND
HLD.LEVEL_NAME=						   LEVEL_NAME				  AND
HLD.LEVEL_VALUE_REFERENCE=			   LEVEL_VALUE_REFERENCE	  AND
HLD.HIERARCHY_DEFINITION_SID=		   HIERARCHY_DEFINITION_SID	  AND
HLD.TABLE_NAME=						   TABLE_NAME				  AND
HLD.FIELD_NAME=						   FIELD_NAME				  AND
HLD.INCLUSION_RULE_TYPE=			   INCLUSION_RULE_TYPE		  AND
HLD.INCLUSION_RULE=					   INCLUSION_RULE			  AND
HLD.EXCLUSION_RULE_TYPE=			   EXCLUSION_RULE_TYPE		  AND
HLD.EXCLUSION_RULE=					   EXCLUSION_RULE			  AND
HLD.INCLUSION_CONDITION=			   INCLUSION_CONDITION		  AND
HLD.EXCLUSION_CONDITION		=		   EXCLUSION_CONDITION		AND
HLD.VERSION_NO=VERSION_NO  


)

------------------triggers

IF EXISTS(
SELECT 1 FROM SYS.triggers WHERE NAME='TRG_HIERARCHY_LEVEL_DEFINITION_DEL' AND is_disabled=0 AND OBJECT_NAME(parent_id)='HIERARCHY_LEVEL_DEFINITION'
)
BEGIN 
ALTER TABLE HIERARCHY_LEVEL_DEFINITION DISABLE TRIGGER TRG_HIERARCHY_LEVEL_DEFINITION_DEL  
END


IF EXISTS(
SELECT 1 FROM SYS.triggers WHERE NAME='TRG_HIERARCHY_LEVEL_DEFINITION_INS' AND is_disabled=0 AND OBJECT_NAME(parent_id)='HIERARCHY_LEVEL_DEFINITION'
)
BEGIN 
ALTER TABLE HIERARCHY_LEVEL_DEFINITION DISABLE TRIGGER TRG_HIERARCHY_LEVEL_DEFINITION_INS  
END


IF EXISTS(
SELECT 1 FROM SYS.triggers WHERE NAME='TRG_HIERARCHY_LEVEL_DEFINITION_UPD' AND is_disabled=0 AND OBJECT_NAME(parent_id)='HIERARCHY_LEVEL_DEFINITION'
)
BEGIN 
ALTER TABLE HIERARCHY_LEVEL_DEFINITION DISABLE TRIGGER TRG_HIERARCHY_LEVEL_DEFINITION_UPD 
END



IF EXISTS(
SELECT 1 FROM SYS.triggers WHERE NAME='TRG_HIERARCHY_LEVEL_VALUES_UPD' AND is_disabled=0 AND OBJECT_NAME(parent_id)='HIERARCHY_LEVEL_VALUES'
)
BEGIN 
ALTER TABLE HIERARCHY_LEVEL_VALUES DISABLE TRIGGER TRG_HIERARCHY_LEVEL_VALUES_UPD  
END


IF EXISTS(
SELECT 1 FROM SYS.triggers WHERE NAME='TRG_HIERARCHY_LEVEL_VALUES_INS' AND is_disabled=0 AND OBJECT_NAME(parent_id)='HIERARCHY_LEVEL_VALUES'
)
BEGIN 
ALTER TABLE HIERARCHY_LEVEL_VALUES DISABLE TRIGGER TRG_HIERARCHY_LEVEL_VALUES_INS  
END


IF EXISTS(
SELECT 1 FROM SYS.triggers WHERE NAME='TRG_HIERARCHY_LEVEL_VALUES_DEL' AND is_disabled=0 AND OBJECT_NAME(parent_id)='HIERARCHY_LEVEL_VALUES'
)
BEGIN 
ALTER TABLE HIERARCHY_LEVEL_VALUES DISABLE TRIGGER TRG_HIERARCHY_LEVEL_VALUES_DEL 
END



IF EXISTS(
SELECT 1 FROM SYS.triggers WHERE NAME='TRG_RELATIONSHIP_LEVEL_DEFINITION_UPD' AND is_disabled=0 AND OBJECT_NAME(parent_id)='RELATIONSHIP_LEVEL_DEFINITION'
)
BEGIN 
ALTER TABLE RELATIONSHIP_LEVEL_DEFINITION DISABLE TRIGGER TRG_RELATIONSHIP_LEVEL_DEFINITION_UPD  
END


IF EXISTS(
SELECT 1 FROM SYS.triggers WHERE NAME='TRG_RELATIONSHIP_LEVEL_DEFINITION_INS' AND is_disabled=0 AND OBJECT_NAME(parent_id)='RELATIONSHIP_LEVEL_DEFINITION'
)
BEGIN 
ALTER TABLE RELATIONSHIP_LEVEL_DEFINITION DISABLE TRIGGER TRG_RELATIONSHIP_LEVEL_DEFINITION_INS  
END


IF EXISTS(
SELECT 1 FROM SYS.triggers WHERE NAME='TRG_RELATIONSHIP_LEVEL_DEFINITION_DEL' AND is_disabled=0 AND OBJECT_NAME(parent_id)='RELATIONSHIP_LEVEL_DEFINITION'
)
BEGIN 
ALTER TABLE RELATIONSHIP_LEVEL_DEFINITION DISABLE TRIGGER TRG_RELATIONSHIP_LEVEL_DEFINITION_DEL 
END
-------------------------------------------HIERARCHY_RESTRICTION_MASTER (ALG-2859)
--IF NOT EXISTS (
--		SELECT 'X'
--		FROM INFORMATION_SCHEMA.TABLES
--		WHERE TABLE_NAME = 'HIERARCHY_RESTRICTION_MASTER'
--			AND TABLE_SCHEMA = 'dbo'
--		)
--BEGIN
--	CREATE TABLE HIERARCHY_RESTRICTION_MASTER (
--		HIERARCHY_TABLE_MASTER_SID INT NOT NULL,
--		ACTUAL_TABLE_NAME VARCHAR(100) NOT NULL,
--		ACTUAL_COLUMN_NAME VARCHAR(100) NOT NULL,
--		REFERENCE_TABLE_NAME VARCHAR(100) NOT NULL,
--		REFERENCE_COLUMN_NAME VARCHAR(100) NOT NULL,
--		RESTRICTION_COLUMN_NAME VARCHAR(100) NOT NULL,
--		RESTRICTION_VALUE VARCHAR(100) NOT NULL
--		)
--END
--GO

-----------------------PRIMARY KEY CONSTRAINT------------------------
--IF NOT EXISTS (
--		SELECT 1
--		FROM SYS.KEY_CONSTRAINTS
--		WHERE Object_name(PARENT_OBJECT_ID) = 'HIERARCHY_RESTRICTION_MASTER'
--			AND Schema_name(SCHEMA_ID) = 'DBO'
--			AND NAME = 'PK_HIERARCHY_RESTRICTION_MASTER_HIERARCHY_TABLE_MASTER_SID'
--			AND TYPE = 'PK'
--		)
--BEGIN
--	ALTER TABLE [dbo].[HIERARCHY_RESTRICTION_MASTER] ADD CONSTRAINT PK_HIERARCHY_RESTRICTION_MASTER_HIERARCHY_TABLE_MASTER_SID PRIMARY KEY (HIERARCHY_TABLE_MASTER_SID)
--END
--GO

-------------------------------------------------------HIERACHY NEW TABLE SCRIPTS---------------------------



IF NOT EXISTS (
		SELECT 'X'
		FROM INFORMATION_SCHEMA.TABLES
		WHERE TABLE_NAME = 'HIERARCHY_ENTITY_MASTER'
			AND TABLE_SCHEMA = 'dbo'
		)
BEGIN
	CREATE TABLE HIERARCHY_ENTITY_MASTER (
		ENTITY_ID INT IDENTITY(1, 1) NOT NULL 
		,ENTITY_NAME VARCHAR(100) NOT NULL
		,HIERARCHY_TABLE_MASTER_SID INT NOT NULL
		,
		)
END
GO
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'HIERARCHY_ENTITY_MASTER'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_HIERARCHY_ENTITY_MASTER_ENTITY_ID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].HIERARCHY_ENTITY_MASTER
        ADD CONSTRAINT PK_HIERARCHY_ENTITY_MASTER_ENTITY_ID PRIMARY KEY ( ENTITY_ID)
  END

GO

IF EXISTS (
		SELECT NAME
		FROM SYS.TABLES
		WHERE NAME = 'HIERARCHY_ENTITY_MASTER'
		)
BEGIN
	IF NOT EXISTS (
			SELECT 1
			FROM SYS.KEY_CONSTRAINTS
			WHERE TYPE_DESC = 'UNIQUE_CONSTRAINT'
				AND PARENT_OBJECT_ID = OBJECT_ID('HIERARCHY_ENTITY_MASTER')
				AND NAME = 'UQ_HIERARCHY_ENTITY_MASTER_HIERARCHY_TABLE_MASTER_SID'
			)
	BEGIN
		ALTER TABLE HIERARCHY_ENTITY_MASTER ADD CONSTRAINT UQ_HIERARCHY_ENTITY_MASTER_HIERARCHY_TABLE_MASTER_SID UNIQUE (HIERARCHY_TABLE_MASTER_SID)
	END
END
GO


 
--------------------


IF NOT EXISTS (
		SELECT 'X'
		FROM INFORMATION_SCHEMA.TABLES
		WHERE TABLE_NAME = 'HIERARCHY_TABLE_RELATION'
			AND TABLE_SCHEMA = 'dbo'
		)
BEGIN
	CREATE TABLE HIERARCHY_TABLE_RELATION (
		LEFT_TABLE_SID INT NOT NULL
		,RIGHT_TABLE_SID INT NOT NULL
		,LEFT_COLUMN_NAME VARCHAR(100)
		,RIGHT_COLUMN_NAME VARCHAR(100)
		,JOIN_TYPE VARCHAR(50)
		)
END
GO

IF EXISTS (
		SELECT NAME
		FROM SYS.TABLES
		WHERE NAME = 'HIERARCHY_TABLE_RELATION'
		)
BEGIN
	IF NOT EXISTS (
			SELECT 1
			FROM SYS.KEY_CONSTRAINTS
			WHERE TYPE_DESC = 'UNIQUE_CONSTRAINT'
				AND PARENT_OBJECT_ID = OBJECT_ID('HIERARCHY_TABLE_RELATION')
				AND NAME = 'UQ_HIERARCHY_TABLE_RELATION_LEFT_TABLE_SID_RIGHT_TABLE_SID'
			)
	BEGIN
		ALTER TABLE HIERARCHY_TABLE_RELATION ADD CONSTRAINT UQ_HIERARCHY_TABLE_RELATION_LEFT_TABLE_SID_RIGHT_TABLE_SID UNIQUE (LEFT_TABLE_SID, RIGHT_TABLE_SID)
	END
END
GO

 ----------------------------------------------------------------------------------------

IF NOT EXISTS (
		SELECT 'X'
		FROM INFORMATION_SCHEMA.TABLES
		WHERE TABLE_NAME = 'HIERARCHY_SINGLE_COLUMN_RELATION'
			AND TABLE_SCHEMA = 'dbo'
		)
BEGIN
	CREATE TABLE HIERARCHY_SINGLE_COLUMN_RELATION (
		COLUMN_RELATION_SID INT IDENTITY(1, 1) NOT NULL
		,ACTUAL_TABLE_NAME VARCHAR(100) NOT NULL
		,ACTUAL_COLUMN_NAME VARCHAR(100) NOT NULL
		,REFERENCE_TABLE_NAME VARCHAR(100) NOT NULL
		,MAPPING_COLUMN_NAME VARCHAR(100)
		,DESC_COLUMN_NAME VARCHAR(100)
		,PRIMARY_KEY_COLUMN_NAME VARCHAR(100) NOT NULL
		,
		)
END
GO




---------------------------------------------------------------------------------------------
IF NOT EXISTS (
		SELECT 'X'
		FROM INFORMATION_SCHEMA.TABLES
		WHERE TABLE_NAME = 'HIERARCHY_TYPE_TABLE_RELATION'
			AND TABLE_SCHEMA = 'dbo'
		)
BEGIN
	CREATE TABLE HIERARCHY_TYPE_TABLE_RELATION (
		ENTITY_ID INT NOT NULL
		,TABLE_NAME VARCHAR(100) NOT NULL
		,HIERARCHY_TYPE VARCHAR(100) NOT NULL
		,
		)
END
GO


IF EXISTS (
		SELECT NAME
		FROM SYS.TABLES
		WHERE NAME = 'HIERARCHY_TYPE_TABLE_RELATION'
		)
BEGIN
	IF NOT EXISTS (
			SELECT 1
			FROM SYS.KEY_CONSTRAINTS
			WHERE TYPE_DESC = 'UNIQUE_CONSTRAINT'
				AND PARENT_OBJECT_ID = OBJECT_ID('HIERARCHY_TYPE_TABLE_RELATION')
				AND NAME = 'UQ_HIERARCHY_TYPE_TABLE_RELATION_ENTITY_ID_TABLE_NAME_HIERARCHY_TYPE'
			)
	BEGIN
		ALTER TABLE HIERARCHY_TYPE_TABLE_RELATION ADD CONSTRAINT UQ_HIERARCHY_TYPE_TABLE_RELATION_ENTITY_ID_TABLE_NAME_HIERARCHY_TYPE UNIQUE (ENTITY_ID, TABLE_NAME, HIERARCHY_TYPE)
	END
END
GO

-------------------------------------------------------------------

IF NOT EXISTS (
		SELECT 'X'
		FROM INFORMATION_SCHEMA.TABLES
		WHERE TABLE_NAME = 'HIERARCHY_RESTRICTION_MASTER'
			AND TABLE_SCHEMA = 'dbo'
		)
BEGIN
	CREATE TABLE HIERARCHY_RESTRICTION_MASTER (
		HIERARCHY_TABLE_MASTER_SID INT  NULL
		,ACTUAL_TABLE_NAME VARCHAR(100)  NULL
		,ACTUAL_COLUMN_NAME VARCHAR(100)  NULL
		,REFERENCE_TABLE_NAME VARCHAR(100)  NULL
		,REFERENCE_COLUMN_NAME VARCHAR(100)  NULL
		,RESTRICTION_COLUMN_NAME VARCHAR(100)  NULL
		,RESTRICTION_VALUE VARCHAR(100)  NULL
		)
END
GO


---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS (
		SELECT 1
		FROM SYS.KEY_CONSTRAINTS
		WHERE Object_name(PARENT_OBJECT_ID) = 'HIERARCHY_RESTRICTION_MASTER'
			AND Schema_name(SCHEMA_ID) = 'DBO'
			AND NAME = 'PK_HIERARCHY_RESTRICTION_MASTER_HIERARCHY_TABLE_MASTER_SID'
			AND TYPE = 'PK'
		)
BEGIN
	ALTER TABLE [dbo].[HIERARCHY_RESTRICTION_MASTER] ADD CONSTRAINT PK_HIERARCHY_RESTRICTION_MASTER_HIERARCHY_TABLE_MASTER_SID PRIMARY KEY (HIERARCHY_TABLE_MASTER_SID)
END
GO
------------------------------------HIERARCHY_TABLE_MASTER----------------
IF NOT EXISTS (SELECT
    'X'
  FROM INFORMATION_SCHEMA.TABLES
  WHERE TABLE_NAME = 'HIERARCHY_TABLE_MASTER'
  AND TABLE_SCHEMA = 'dbo')
BEGIN

  CREATE TABLE HIERARCHY_TABLE_MASTER (
    MASTER_TABLE_SID int IDENTITY (1, 1) NOT NULL ,
    TABLE_NAME varchar(100) NOT NULL,
	
  )
END
GO 


IF NOT EXISTS (
		SELECT 1
		FROM SYS.KEY_CONSTRAINTS
		WHERE Object_name(PARENT_OBJECT_ID) = 'HIERARCHY_TABLE_MASTER'
			AND Schema_name(SCHEMA_ID) = 'DBO'
			AND NAME = 'PK_HIERARCHY_TABLE_MASTER_MASTER_TABLE_SID'
			AND TYPE = 'PK'
		)
BEGIN
	ALTER TABLE [dbo].HIERARCHY_TABLE_MASTER ADD CONSTRAINT PK_HIERARCHY_TABLE_MASTER_MASTER_TABLE_SID PRIMARY KEY (MASTER_TABLE_SID)
END
GO

	IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIERARCHY_TABLE_MASTER'
                      AND COLUMN_NAME = 'COLUMN_NAME'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE HIERARCHY_TABLE_MASTER
        ADD COLUMN_NAME VARCHAR(100)
  END
GO


	IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIERARCHY_TABLE_MASTER'
                      AND COLUMN_NAME = 'VALUE'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE HIERARCHY_TABLE_MASTER
        ADD VALUE VARCHAR(100)
  END
GO