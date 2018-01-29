-------------------------------------COMPANY_MASTER---------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'COMPANY_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[COMPANY_MASTER]
        (
           [COMPANY_MASTER_SID] INT IDENTITY(1, 1) NOT NULL,
           [COMPANY_ID]         VARCHAR(50) NOT NULL,
           [COMPANY_NO]         VARCHAR(50) NOT NULL,
           [COMPANY_NAME]       VARCHAR(100) NOT NULL,
           [COMPANY_TYPE]       INT NOT NULL,
           [COMPANY_STATUS]     INT NOT NULL,
           [COMPANY_CATEGORY]   INT NULL,
           [COMPANY_GROUP]      VARCHAR(50) NULL,
           [COMPANY_START_DATE] DATETIME NOT NULL,
           [COMPANY_END_DATE]   DATETIME NULL,
           [ORGANIZATION_KEY]   INT NULL,
           [LIVES]              INT NULL,
           [FINANCIAL_SYSTEM]   VARCHAR(50) NULL,
           [ADDRESS1]           VARCHAR(100) NULL,
           [ADDRESS2]           VARCHAR(100) NULL,
           [CITY]               VARCHAR(50) NULL,
           [STATE]              INT NULL,
           [ZIP_CODE]           VARCHAR(50) NULL,
           [COUNTRY]            INT NULL,
           [REGION_CODE]        VARCHAR(50) NULL,
           [LAST_UPDATED_DATE]  DATETIME NULL,
           INTERNAL_NOTES       VARCHAR(4000) NULL,
           INBOUND_STATUS       CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS   BIT NOT NULL,
           BATCH_ID             VARCHAR(50) NULL,
           [SOURCE]             VARCHAR(50) NULL,
           [CREATED_BY]         INT NOT NULL,
           [CREATED_DATE]       DATETIME NOT NULL,
           [MODIFIED_BY]        INT NOT NULL,
           [MODIFIED_DATE]      DATETIME NOT NULL
        )
  END

GO
------------------------------ datatype change ----------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'COMPANY_GROUP'
                  AND Object_name(OBJECT_ID) = 'COMPANY_MASTER')
  BEGIN
      DROP STATISTICS DBO.COMPANY_MASTER.COMPANY_GROUP
  END 
GO


IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'COMPANY_GROUP'
                  AND TABLE_NAME = 'COMPANY_MASTER'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE COMPANY_MASTER
        ALTER COLUMN COMPANY_GROUP INT
  END 
GO

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_COMPANY_MASTER_COMPANY_MASTER_SID'
                     AND TABLE_NAME = 'COMPANY_MASTER')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_MASTER]
        ADD CONSTRAINT PK_COMPANY_MASTER_COMPANY_MASTER_SID PRIMARY KEY(COMPANY_MASTER_SID)
  END

GO

IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='COMPANY_MASTER' AND COLUMN_NAME='ORG_KEY')
BEGIN
exec sp_RENAME 'COMPANY_MASTER.ORG_KEY','ORGANIZATION_KEY','COLUMN'
END

GO

IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'ORG_KEY'
                  AND Object_name(object_id) = 'COMPANY_MASTER')
  BEGIN
      DROP STATISTICS DBO.COMPANY_MASTER.ORG_KEY
  END 

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'COMPANY_MASTER'
                  AND COLUMN_NAME = 'ORGANIZATION_KEY')
  BEGIN
      ALTER TABLE COMPANY_MASTER
        ALTER COLUMN ORGANIZATION_KEY INT
  END

GO 

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_MASTER')
                      AND NAME = 'DF_COMPANY_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_MASTER]
        ADD CONSTRAINT [DF_COMPANY_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_MASTER')
                      AND NAME = 'DF_COMPANY_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_MASTER]
        ADD CONSTRAINT [DF_COMPANY_MASTER_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_MASTER')
                      AND NAME = 'DF_COMPANY_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_MASTER]
        ADD CONSTRAINT [DF_COMPANY_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_MASTER')
                      AND NAME = 'DF_COMPANY_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_MASTER]
        ADD CONSTRAINT [DF_COMPANY_MASTER_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.COMPANY_MASTER')
                      AND NAME = 'DF_COMPANY_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_MASTER]
        ADD CONSTRAINT DF_COMPANY_MASTER_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO
-------------------------------COMPANY_MASTER unique key-----------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'COMPANY_MASTER') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('COMPANY_MASTER')
                      AND NAME = 'UQ_COMPANY_MASTER_COMPANY_ID')
  BEGIN
      ALTER TABLE COMPANY_MASTER
        ADD CONSTRAINT UQ_COMPANY_MASTER_COMPANY_ID UNIQUE(COMPANY_ID)
  END
END

GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'COMPANY_MASTER'--TABLE NAME
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


------------------------------HIST_COMPANY_MASTER--------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_COMPANY_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_COMPANY_MASTER]
        (
           [COMPANY_MASTER_SID] INT NOT NULL,
           [COMPANY_ID]         VARCHAR(50) NOT NULL,
           [COMPANY_NO]         VARCHAR(50) NOT NULL,
           [COMPANY_NAME]       VARCHAR(100) NOT NULL,
           [COMPANY_TYPE]       INT NOT NULL,
           [COMPANY_STATUS]     INT NOT NULL,
           [COMPANY_CATEGORY]   INT NULL,
           [COMPANY_GROUP]      VARCHAR(50) NULL,
           [COMPANY_START_DATE] DATETIME NOT NULL,
           [COMPANY_END_DATE]   DATETIME NULL,
           [ORGANIZATION_KEY]   INT NULL,
           [LIVES]              INT NULL,
           [FINANCIAL_SYSTEM]   VARCHAR(50) NULL,
           [ADDRESS1]           VARCHAR(100) NULL,
           [ADDRESS2]           VARCHAR(100) NULL,
           [CITY]               VARCHAR(50) NULL,
           [STATE]              INT NULL,
           [ZIP_CODE]           VARCHAR(50) NULL,
           [COUNTRY]            INT NULL,
           [REGION_CODE]        VARCHAR(50) NULL,
           [LAST_UPDATED_DATE]  DATETIME NULL,
           INTERNAL_NOTES       VARCHAR(4000) NULL,
           INBOUND_STATUS       CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS   BIT NOT NULL,
           BATCH_ID             VARCHAR(50) NULL,
           [SOURCE]             VARCHAR(50) NULL,
           [CREATED_BY]         INT NOT NULL,
           [CREATED_DATE]       DATETIME NOT NULL,
           [MODIFIED_BY]        INT NULL,
           [MODIFIED_DATE]      DATETIME NULL,
           ACTION_DATE          DATETIME NOT NULL,
           ACTION_FLAG          CHAR(1) NOT NULL
        )
  END

GO

---------------------------- datatype change -----------------------------

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'COMPANY_GROUP'
                  AND Object_name(OBJECT_ID) = 'HIST_COMPANY_MASTER')
  BEGIN
      DROP STATISTICS DBO.HIST_COMPANY_MASTER.COMPANY_GROUP
  END 

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'COMPANY_GROUP'
                  AND TABLE_NAME = 'HIST_COMPANY_MASTER'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE HIST_COMPANY_MASTER
        ALTER COLUMN COMPANY_GROUP INT
  END 

GO

--------------------------------------------------- 
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_COMPANY_MASTER'
                  AND COLUMN_NAME = 'MODIFIED_BY'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE HIST_COMPANY_MASTER
        ALTER COLUMN MODIFIED_BY INT NULL
  END 

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_COMPANY_MASTER'
                  AND COLUMN_NAME = 'MODIFIED_DATE'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE HIST_COMPANY_MASTER
        ALTER COLUMN MODIFIED_DATE DATETIME NULL
  END 
  
GO

IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='HIST_COMPANY_MASTER' AND COLUMN_NAME='ORG_KEY')
BEGIN
exec sp_RENAME 'HIST_COMPANY_MASTER.ORG_KEY','ORGANIZATION_KEY','COLUMN'
END

GO


IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'ORG_KEY'
                  AND Object_name(object_id) = 'HIST_COMPANY_MASTER')
  BEGIN
      DROP STATISTICS DBO.HIST_COMPANY_MASTER.ORG_KEY
  END 

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_COMPANY_MASTER'
                  AND COLUMN_NAME = 'ORGANIZATION_KEY')
  BEGIN
      ALTER TABLE HIST_COMPANY_MASTER
        ALTER COLUMN ORGANIZATION_KEY INT
  END

GO 
-----------------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_COMPANY_MASTER')
                      AND NAME = 'DF_HIST_COMPANY_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_COMPANY_MASTER]
        ADD CONSTRAINT [DF_HIST_COMPANY_MASTER_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.HIST_COMPANY_MASTER')
                      AND NAME = 'DF_HIST_COMPANY_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[HIST_COMPANY_MASTER]
        ADD CONSTRAINT DF_HIST_COMPANY_MASTER_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_COMPANY_MASTER'--TABLE NAME
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


-------------------------------COMPANY_MASTER TRIGGER-------------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_COMPANY_MASTER_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_MASTER_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_MASTER_UPD]
ON [dbo].[COMPANY_MASTER]
AFTER UPDATE
AS
  BEGIN
    SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_COMPANY_MASTER
                    (COMPANY_MASTER_SID,
                     COMPANY_ID,
                     COMPANY_NO,
                     COMPANY_NAME,
                     COMPANY_TYPE,
                     COMPANY_STATUS,
                     COMPANY_CATEGORY,
                     COMPANY_GROUP,
                     COMPANY_START_DATE,
                     COMPANY_END_DATE,
                     ORGANIZATION_KEY,
                     LIVES,
                     FINANCIAL_SYSTEM,
                     ADDRESS1,
                     ADDRESS2,
                     CITY,
                     STATE,
                     ZIP_CODE,
                     COUNTRY,
                     REGION_CODE,
                     LAST_UPDATED_DATE,
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
        SELECT COMPANY_MASTER_SID,
               COMPANY_ID,
               COMPANY_NO,
               COMPANY_NAME,
               COMPANY_TYPE,
               COMPANY_STATUS,
               COMPANY_CATEGORY,
               COMPANY_GROUP,
               COMPANY_START_DATE,
               COMPANY_END_DATE,
               ORGANIZATION_KEY,
               LIVES,
               FINANCIAL_SYSTEM,
               ADDRESS1,
               ADDRESS2,
               CITY,
               STATE,
               ZIP_CODE,
               COUNTRY,
               REGION_CODE,
               LAST_UPDATED_DATE,
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
           WHERE  [Name] = N'TRG_COMPANY_MASTER_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_MASTER_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_MASTER_INS]
ON [dbo].[COMPANY_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_COMPANY_MASTER
                    (COMPANY_MASTER_SID,
                     COMPANY_ID,
                     COMPANY_NO,
                     COMPANY_NAME,
                     COMPANY_TYPE,
                     COMPANY_STATUS,
                     COMPANY_CATEGORY,
                     COMPANY_GROUP,
                     COMPANY_START_DATE,
                     COMPANY_END_DATE,
                     ORGANIZATION_KEY,
                     LIVES,
                     FINANCIAL_SYSTEM,
                     ADDRESS1,
                     ADDRESS2,
                     CITY,
                     STATE,
                     ZIP_CODE,
                     COUNTRY,
                     REGION_CODE,
                     LAST_UPDATED_DATE,
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
        SELECT COMPANY_MASTER_SID,
               COMPANY_ID,
               COMPANY_NO,
               COMPANY_NAME,
               COMPANY_TYPE,
               COMPANY_STATUS,
               COMPANY_CATEGORY,
               COMPANY_GROUP,
               COMPANY_START_DATE,
               COMPANY_END_DATE,
               ORGANIZATION_KEY,
               LIVES,
               FINANCIAL_SYSTEM,
               ADDRESS1,
               ADDRESS2,
               CITY,
               STATE,
               ZIP_CODE,
               COUNTRY,
               REGION_CODE,
               LAST_UPDATED_DATE,
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
           WHERE  [Name] = N'TRG_COMPANY_MASTER_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_MASTER_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_MASTER_DEL]
ON [dbo].[COMPANY_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_COMPANY_MASTER
                    (COMPANY_MASTER_SID,
                     COMPANY_ID,
                     COMPANY_NO,
                     COMPANY_NAME,
                     COMPANY_TYPE,
                     COMPANY_STATUS,
                     COMPANY_CATEGORY,
                     COMPANY_GROUP,
                     COMPANY_START_DATE,
                     COMPANY_END_DATE,
                     ORGANIZATION_KEY,
                     LIVES,
                     FINANCIAL_SYSTEM,
                     ADDRESS1,
                     ADDRESS2,
                     CITY,
                     STATE,
                     ZIP_CODE,
                     COUNTRY,
                     REGION_CODE,
                     LAST_UPDATED_DATE,
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
        SELECT COMPANY_MASTER_SID,
               COMPANY_ID,
               COMPANY_NO,
               COMPANY_NAME,
               COMPANY_TYPE,
               COMPANY_STATUS,
               COMPANY_CATEGORY,
               COMPANY_GROUP,
               COMPANY_START_DATE,
               COMPANY_END_DATE,
               ORGANIZATION_KEY,
               LIVES,
               FINANCIAL_SYSTEM,
               ADDRESS1,
               ADDRESS2,
               CITY,
               STATE,
               ZIP_CODE,
               COUNTRY,
               REGION_CODE,
               LAST_UPDATED_DATE,
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


--------------------------------------COMPANY_QUALIFIER----------------------------------


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'COMPANY_QUALIFIER'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[COMPANY_QUALIFIER]
        (
           [COMPANY_QUALIFIER_SID]   INT IDENTITY(1, 1) NOT NULL,
           [COMPANY_QUALIFIER_VALUE] VARCHAR(25) NOT NULL,
           [COMPANY_QUALIFIER_NAME]  VARCHAR(100) NOT NULL,
		   EFFECTIVE_DATES			 VARCHAR(10),
		   NOTES					 VARCHAR(5000),
           [INBOUND_STATUS]          CHAR(1) NOT NULL,
           [RECORD_LOCK_STATUS]      BIT NOT NULL,
           [BATCH_ID]                VARCHAR(50) NOT NULL,
           [SOURCE]                  VARCHAR(50) NULL,
           [CREATED_BY]              INT NOT NULL,
           [CREATED_DATE]            DATETIME NOT NULL,
           [MODIFIED_BY]             INT NOT NULL,
           [MODIFIED_DATE]           DATETIME NOT NULL
        )
  END

GO

--------------------------------- column addition ------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'COMPANY_QUALIFIER'
                      AND COLUMN_NAME = 'EFFECTIVE_DATES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE COMPANY_QUALIFIER
        ADD EFFECTIVE_DATES VARCHAR(10)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'COMPANY_QUALIFIER'
                      AND COLUMN_NAME = 'NOTES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE COMPANY_QUALIFIER
        ADD NOTES VARCHAR(5000)
  END

GO
------------------------------------------------------------------------


IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_Name(OBJECT_ID) = N'COMPANY_QUALIFIER'
                  AND Object_scHema_Name(OBJECT_ID) = N'DBO'
                  AND [NAME] = N'PK_COMPANY_QUALIFIER_COMPANY_QUALIFIER_SID')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_QUALIFIER]
        ADD CONSTRAINT [PK_COMPANY_QUALIFIER_COMPANY_QUALIFIER_SID] PRIMARY KEY CLUSTERED ( [COMPANY_QUALIFIER_SID] ASC ) WITH (FILLFACTOR = 80) ON [PRIMARY]
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_QUALIFIER')
                  AND NAME = 'DF_COMPANY_QUALIFIER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_QUALIFIER]
        ADD CONSTRAINT [DF_COMPANY_QUALIFIER_RECORD_LOCK_STATUS] DEFAULT (1) FOR [RECORD_LOCK_STATUS]
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_QUALIFIER')
                  AND NAME = 'DF_COMPANY_QUALIFIER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_QUALIFIER]
        ADD CONSTRAINT [DF_COMPANY_QUALIFIER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_QUALIFIER')
                  AND NAME = 'DF_COMPANY_QUALIFIER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_QUALIFIER]
        ADD CONSTRAINT [DF_COMPANY_QUALIFIER_CREATED_DATE] DEFAULT (GetDate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_QUALIFIER')
                  AND NAME = 'DF_COMPANY_QUALIFIER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_QUALIFIER]
        ADD CONSTRAINT [DF_COMPANY_QUALIFIER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_QUALIFIER')
                  AND NAME = 'DF_COMPANY_QUALIFIER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_QUALIFIER]
        ADD CONSTRAINT [DF_COMPANY_QUALIFIER_MODIFIED_DATE] DEFAULT (GetDate()) FOR MODIFIED_DATE
  END

GO


--------------------------------unique constraint------------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'COMPANY_QUALIFIER') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('COMPANY_QUALIFIER')
                      AND NAME = 'UQ_COMPANY_QUALIFIER_COMPANY_QUALIFIER_VALUE')
  BEGIN
      ALTER TABLE COMPANY_QUALIFIER
        ADD CONSTRAINT UQ_COMPANY_QUALIFIER_COMPANY_QUALIFIER_VALUE UNIQUE(COMPANY_QUALIFIER_VALUE)
  END
END


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'COMPANY_QUALIFIER'--TABLE NAME
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


------------------------------------HIST_COMPANY_QUALIFIER----------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_COMPANY_QUALIFIER'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_COMPANY_QUALIFIER]
        (
           [COMPANY_QUALIFIER_SID]   INT NOT NULL,
           [COMPANY_QUALIFIER_VALUE] VARCHAR(25) NOT NULL,
           [COMPANY_QUALIFIER_NAME]  VARCHAR(100) NOT NULL,
		   EFFECTIVE_DATES			 VARCHAR(10),
		   NOTES					 VARCHAR(5000),
           [RECORD_LOCK_STATUS]      BIT NOT NULL,
           [BATCH_ID]                VARCHAR(50) NOT NULL,
           [SOURCE]                  VARCHAR(50) NULL,
           [INBOUND_STATUS]          CHAR(1) NOT NULL,
           [CREATED_BY]              INT NOT NULL,
           [CREATED_DATE]            DATETIME NOT NULL,
           [MODIFIED_BY]             INT NOT NULL,
           [MODIFIED_DATE]           DATETIME NOT NULL,
           ACTION_DATE               DATETIME NOT NULL,
           ACTION_FLAG               CHAR(1) NOT NULL
        )
  END

GO

--------------------------------------- column addition----------------------

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_COMPANY_QUALIFIER'
                      AND COLUMN_NAME = 'EFFECTIVE_DATES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_COMPANY_QUALIFIER
        ADD EFFECTIVE_DATES VARCHAR(10)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_COMPANY_QUALIFIER'
                      AND COLUMN_NAME = 'NOTES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_COMPANY_QUALIFIER
        ADD NOTES VARCHAR(5000)
  END

GO
------------------------------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.HIST_COMPANY_QUALIFIER')
                  AND NAME = 'DF_HIST_COMPANY_QUALIFIER_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_COMPANY_QUALIFIER]
        ADD CONSTRAINT [DF_HIST_COMPANY_QUALIFIER_ACTION_DATE] DEFAULT (GetDate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_COMPANY_QUALIFIER'--TABLE NAME
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

-----------------------------COMPANY_QUALIFIER TRIGGER ---------------------------


IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_COMPANY_QUALIFIER_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_QUALIFIER_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_QUALIFIER_UPD]
ON [dbo].[COMPANY_QUALIFIER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_COMPANY_QUALIFIER
                    (COMPANY_QUALIFIER_SID,
                     COMPANY_QUALIFIER_VALUE,
                     COMPANY_QUALIFIER_NAME,
					 EFFECTIVE_DATES,
					 NOTES,			
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_QUALIFIER_SID,
               COMPANY_QUALIFIER_VALUE,
               COMPANY_QUALIFIER_NAME,
			   EFFECTIVE_DATES,
			   NOTES,			
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
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
           WHERE  [Name] = N'TRG_COMPANY_QUALIFIER_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_QUALIFIER_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_QUALIFIER_INS]
ON [dbo].[COMPANY_QUALIFIER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_COMPANY_QUALIFIER
                    (COMPANY_QUALIFIER_SID,
                     COMPANY_QUALIFIER_VALUE,
                     COMPANY_QUALIFIER_NAME,
					 EFFECTIVE_DATES,
					 NOTES,			
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_QUALIFIER_SID,
               COMPANY_QUALIFIER_VALUE,
               COMPANY_QUALIFIER_NAME,
			   EFFECTIVE_DATES,
			   NOTES,			
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
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
           WHERE  [Name] = N'TRG_COMPANY_QUALIFIER_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_QUALIFIER_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_QUALIFIER_DEL]
ON [dbo].[COMPANY_QUALIFIER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_COMPANY_QUALIFIER
                    (COMPANY_QUALIFIER_SID,
                     COMPANY_QUALIFIER_VALUE,
                     COMPANY_QUALIFIER_NAME,
					 EFFECTIVE_DATES,
					 NOTES,			
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_QUALIFIER_SID,
               COMPANY_QUALIFIER_VALUE,
               COMPANY_QUALIFIER_NAME,
			   EFFECTIVE_DATES,
			   NOTES,			
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'D'
        FROM   DELETED
  END

GO

--------------------------------------COMPANY_IDENTIFIER------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'COMPANY_IDENTIFIER'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[COMPANY_IDENTIFIER]
        (
           [COMPANY_IDENTIFIER_SID]   INT IDENTITY(1, 1) NOT NULL,
           [COMPANY_QUALIFIER_SID]    INT NOT NULL,
           [COMPANY_MASTER_SID]       INT NOT NULL,
           [COMPANY_IDENTIFIER_VALUE] VARCHAR(50) NOT NULL,
           [IDENTIFIER_STATUS]        INT NOT NULL,
           [START_DATE]               DATETIME NOT NULL,
           [END_DATE]                 DATETIME NULL,
           [ENTITY_CODE]              VARCHAR(50) NULL,
           [INBOUND_STATUS]           CHAR(1) NOT NULL,
           [RECORD_LOCK_STATUS]       BIT NOT NULL,
           [BATCH_ID]                 VARCHAR(50) NOT NULL,
           [SOURCE]                   VARCHAR(50) NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_COMPANY_IDENTIFIER_COMPANY_IDENTIFIER_SID'
                 AND TABLE_NAME = 'COMPANY_IDENTIFIER')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_IDENTIFIER]
        ADD CONSTRAINT [PK_COMPANY_IDENTIFIER_COMPANY_IDENTIFIER_SID] PRIMARY KEY ( [COMPANY_IDENTIFIER_SID])
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('COMPANY_IDENTIFIER')
                  AND NAME = 'DF_COMPANY_IDENTIFIER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_IDENTIFIER]
        ADD CONSTRAINT [DF_COMPANY_IDENTIFIER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('COMPANY_IDENTIFIER')
                  AND NAME = 'DF_COMPANY_IDENTIFIER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_IDENTIFIER]
        ADD CONSTRAINT [DF_COMPANY_IDENTIFIER_CREATED_DATE] DEFAULT (GetDate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('COMPANY_IDENTIFIER')
                  AND NAME = 'DF_COMPANY_IDENTIFIER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_IDENTIFIER]
        ADD CONSTRAINT [DF_COMPANY_IDENTIFIER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('COMPANY_IDENTIFIER')
                  AND NAME = 'DF_COMPANY_IDENTIFIER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_IDENTIFIER]
        ADD CONSTRAINT [DF_COMPANY_IDENTIFIER_MODIFIED_DATE] DEFAULT (GetDate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('COMPANY_IDENTIFIER')
                  AND NAME = 'DF_COMPANY_IDENTIFIER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_IDENTIFIER]
        ADD CONSTRAINT [DF_COMPANY_IDENTIFIER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

----------------------------UNIQUE_CONSTRAINT------------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'COMPANY_IDENTIFIER') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('COMPANY_IDENTIFIER')
                      AND NAME = 'UQ_COMPANY_IDENTIFIER_COMPANY_IDENTIFIER_VALUE_COMPANY_QUALIFIER_SID_COMPANY_MASTER_SID_START_DATE')
  BEGIN
      ALTER TABLE COMPANY_IDENTIFIER
        ADD CONSTRAINT UQ_COMPANY_IDENTIFIER_COMPANY_IDENTIFIER_VALUE_COMPANY_QUALIFIER_SID_COMPANY_MASTER_SID_START_DATE UNIQUE(COMPANY_IDENTIFIER_VALUE,COMPANY_QUALIFIER_SID,COMPANY_MASTER_SID,START_DATE)
  END
END
GO



DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'COMPANY_IDENTIFIER'--TABLE NAME
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


------------------------------------HIST_COMPANY_IDENTIFIER-------------------------------------


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_COMPANY_IDENTIFIER'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_COMPANY_IDENTIFIER]
        (
           [COMPANY_IDENTIFIER_SID]   INT NOT NULL,
           [COMPANY_QUALIFIER_SID]    INT NOT NULL,
           [COMPANY_MASTER_SID]       INT NOT NULL,
           [COMPANY_IDENTIFIER_VALUE] VARCHAR(50) NOT NULL,
           [IDENTIFIER_STATUS]        INT NOT NULL,
           [START_DATE]               DATETIME NOT NULL,
           [END_DATE]                 DATETIME NULL,
           [ENTITY_CODE]              VARCHAR(50) NULL,
           [RECORD_LOCK_STATUS]       BIT NOT NULL,
           [BATCH_ID]                 VARCHAR(50) NOT NULL,
           [SOURCE]                   VARCHAR(50) NULL,
           [INBOUND_STATUS]           CHAR(1) NOT NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL,
           ACTION_DATE                DATETIME NOT NULL,
           ACTION_FLAG                CHAR(1) NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.HIST_COMPANY_IDENTIFIER')
                  AND NAME = 'DF_HIST_COMPANY_IDENTIFIER_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_COMPANY_IDENTIFIER]
        ADD CONSTRAINT [DF_HIST_COMPANY_IDENTIFIER_ACTION_DATE] DEFAULT (GetDate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_COMPANY_IDENTIFIER'--TABLE NAME
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





--------------------------------COMPANY_IDENTIFIER TRIGGER-------------------------------



IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_COMPANY_IDENTIFIER_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_IDENTIFIER_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_IDENTIFIER_UPD]
ON [dbo].[COMPANY_IDENTIFIER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_COMPANY_IDENTIFIER
                    (COMPANY_IDENTIFIER_SID,
                     COMPANY_QUALIFIER_SID,
                     COMPANY_MASTER_SID,
                     COMPANY_IDENTIFIER_VALUE,
                     IDENTIFIER_STATUS,
                     START_DATE,
                     END_DATE,
                     ENTITY_CODE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_IDENTIFIER_SID,
               COMPANY_QUALIFIER_SID,
               COMPANY_MASTER_SID,
               COMPANY_IDENTIFIER_VALUE,
               IDENTIFIER_STATUS,
               START_DATE,
               END_DATE,
               ENTITY_CODE,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
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
           WHERE  [Name] = N'TRG_COMPANY_IDENTIFIER_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_IDENTIFIER_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_IDENTIFIER_INS]
ON [dbo].[COMPANY_IDENTIFIER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_COMPANY_IDENTIFIER
                    (COMPANY_IDENTIFIER_SID,
                     COMPANY_QUALIFIER_SID,
                     COMPANY_MASTER_SID,
                     COMPANY_IDENTIFIER_VALUE,
                     IDENTIFIER_STATUS,
                     START_DATE,
                     END_DATE,
                     ENTITY_CODE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_IDENTIFIER_SID,
               COMPANY_QUALIFIER_SID,
               COMPANY_MASTER_SID,
               COMPANY_IDENTIFIER_VALUE,
               IDENTIFIER_STATUS,
               START_DATE,
               END_DATE,
               ENTITY_CODE,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
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
           WHERE  [Name] = N'TRG_COMPANY_IDENTIFIER_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_IDENTIFIER_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_IDENTIFIER_DEL]
ON [dbo].[COMPANY_IDENTIFIER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_COMPANY_IDENTIFIER
                    (COMPANY_IDENTIFIER_SID,
                     COMPANY_QUALIFIER_SID,
                     COMPANY_MASTER_SID,
                     COMPANY_IDENTIFIER_VALUE,
                     IDENTIFIER_STATUS,
                     START_DATE,
                     END_DATE,
                     ENTITY_CODE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_IDENTIFIER_SID,
               COMPANY_QUALIFIER_SID,
               COMPANY_MASTER_SID,
               COMPANY_IDENTIFIER_VALUE,
               IDENTIFIER_STATUS,
               START_DATE,
               END_DATE,
               ENTITY_CODE,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'D'
        FROM   DELETED
  END

GO

----------------------------------------COMPANY_TRADE_CLASS----------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'COMPANY_TRADE_CLASS'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[COMPANY_TRADE_CLASS]
        (
           COMPANY_TRADE_CLASS_SID      INT IDENTITY(1, 1) NOT NULL,
           COMPANY_MASTER_SID           INT NOT NULL,
           TRADE_CLASS_START_DATE       DATETIME NOT NULL,
           TRADE_CLASS_END_DATE         DATETIME NULL,
           COMPANY_TRADE_CLASS          INT NOT NULL,
           PRIOR_TRADE_CLASS            INT NULL,
           PRIOR_TRADE_CLASS_START_DATE DATETIME,
           LAST_UPDATED_DATE            DATETIME NULL,
           [INBOUND_STATUS]             CHAR(1) NOT NULL,
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

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_Name(OBJECT_ID) = N'COMPANY_TRADE_CLASS'
                  AND Object_scHema_Name(OBJECT_ID) = N'DBO'
                  AND [NAME] = N'PK_COMPANY_TRADE_CLASS_COMPANY_TRADE_CLASS_SID')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_TRADE_CLASS]
        ADD CONSTRAINT [PK_COMPANY_TRADE_CLASS_COMPANY_TRADE_CLASS_SID] PRIMARY KEY CLUSTERED ( [COMPANY_TRADE_CLASS_SID] ASC ) WITH (FILLFACTOR = 80) ON [PRIMARY]
  END

GO

IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='COMPANY_TRADE_CLASS' AND COLUMN_NAME='TRADE_CLASS')
BEGIN
exec sp_RENAME 'COMPANY_TRADE_CLASS.TRADE_CLASS','COMPANY_TRADE_CLASS','COLUMN'
END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_TRADE_CLASS')
                  AND NAME = 'DF_COMPANY_TRADE_CLASS_LAST_UPDATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_TRADE_CLASS]
        ADD CONSTRAINT [DF_COMPANY_TRADE_CLASS_LAST_UPDATED_DATE] DEFAULT (GetDate()) FOR LAST_UPDATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_TRADE_CLASS')
                  AND NAME = 'DF_COMPANY_TRADE_CLASS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_TRADE_CLASS]
        ADD CONSTRAINT [DF_COMPANY_TRADE_CLASS_RECORD_LOCK_STATUS] DEFAULT (1) FOR [RECORD_LOCK_STATUS]
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_TRADE_CLASS')
                  AND NAME = 'DF_COMPANY_TRADE_CLASS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_TRADE_CLASS]
        ADD CONSTRAINT [DF_COMPANY_TRADE_CLASS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_TRADE_CLASS')
                  AND NAME = 'DF_COMPANY_TRADE_CLASS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_TRADE_CLASS]
        ADD CONSTRAINT [DF_COMPANY_TRADE_CLASS_CREATED_DATE] DEFAULT (GetDate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_TRADE_CLASS')
                  AND NAME = 'DF_COMPANY_TRADE_CLASS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_TRADE_CLASS]
        ADD CONSTRAINT [DF_COMPANY_TRADE_CLASS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_TRADE_CLASS')
                  AND NAME = 'DF_COMPANY_TRADE_CLASS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_TRADE_CLASS]
        ADD CONSTRAINT [DF_COMPANY_TRADE_CLASS_MODIFIED_DATE] DEFAULT (GetDate()) FOR MODIFIED_DATE
  END

GO

----------------------------------UNIQUE_CONSTRAINT------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'COMPANY_TRADE_CLASS') 
BEGIN

IF  EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('COMPANY_TRADE_CLASS')
                      AND NAME = 'UQ_COMPANY_TRADE_CLASS_COMPANY_MASTER_SID_TRADE_CLASS_START_DATE_TRADE_CLASS')
  BEGIN
      ALTER TABLE COMPANY_TRADE_CLASS
        DROP CONSTRAINT UQ_COMPANY_TRADE_CLASS_COMPANY_MASTER_SID_TRADE_CLASS_START_DATE_TRADE_CLASS 
  END

IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('COMPANY_TRADE_CLASS')
                      AND NAME = 'UQ_COMPANY_TRADE_CLASS_COMPANY_MASTER_SID_TRADE_CLASS_START_DATE_COMPANY_TRADE_CLASS')
  BEGIN
      ALTER TABLE COMPANY_TRADE_CLASS
        ADD CONSTRAINT UQ_COMPANY_TRADE_CLASS_COMPANY_MASTER_SID_TRADE_CLASS_START_DATE_COMPANY_TRADE_CLASS UNIQUE(COMPANY_MASTER_SID,TRADE_CLASS_START_DATE,COMPANY_TRADE_CLASS)
  END
END
GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'COMPANY_TRADE_CLASS'--TABLE NAME
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


--------------------------------------HIST_COMPANY_TRADE_CLASS------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_COMPANY_TRADE_CLASS'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_COMPANY_TRADE_CLASS]
        (
           COMPANY_TRADE_CLASS_SID      INT NOT NULL,
           COMPANY_MASTER_SID           INT NOT NULL,
           TRADE_CLASS_START_DATE       DATETIME NOT NULL,
           TRADE_CLASS_END_DATE         DATETIME NULL,
           COMPANY_TRADE_CLASS          INT NOT NULL,
           PRIOR_TRADE_CLASS            INT NULL,
           PRIOR_TRADE_CLASS_START_DATE DATETIME,
           LAST_UPDATED_DATE            DATETIME NULL,
           [INBOUND_STATUS]             CHAR(1) NOT NULL,
           [RECORD_LOCK_STATUS]         BIT NOT NULL,
           [BATCH_ID]                   VARCHAR(50) NOT NULL,
           [SOURCE]                     VARCHAR(50) NULL,
           [CREATED_BY]                 INT NOT NULL,
           [CREATED_DATE]               DATETIME NOT NULL,
           [MODIFIED_BY]                INT NOT NULL,
           [MODIFIED_DATE]              DATETIME NOT NULL,
           ACTION_DATE                  DATETIME NOT NULL,
           ACTION_FLAG                  CHAR(1) NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.HIST_COMPANY_TRADE_CLASS')
                  AND NAME = 'DF_HIST_COMPANY_TRADE_CLASS_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_COMPANY_TRADE_CLASS]
        ADD CONSTRAINT [DF_HIST_COMPANY_TRADE_CLASS_ACTION_DATE] DEFAULT (GetDate()) FOR ACTION_DATE
  END

GO

IF EXISTS (SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME='HIST_COMPANY_TRADE_CLASS' AND COLUMN_NAME='TRADE_CLASS')
BEGIN
exec sp_RENAME 'HIST_COMPANY_TRADE_CLASS.TRADE_CLASS','COMPANY_TRADE_CLASS','COLUMN'
END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_COMPANY_TRADE_CLASS'--TABLE NAME
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


-------------------------------------COMPANY_TRADE_CLASS TRIGGER-----------------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_COMPANY_TRADE_CLASS_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_TRADE_CLASS_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_TRADE_CLASS_UPD]
ON [dbo].[COMPANY_TRADE_CLASS]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_COMPANY_TRADE_CLASS
                    (COMPANY_TRADE_CLASS_SID,
                     COMPANY_MASTER_SID,
                     TRADE_CLASS_START_DATE,
                     TRADE_CLASS_END_DATE,
                     COMPANY_TRADE_CLASS,
                     PRIOR_TRADE_CLASS,
                     PRIOR_TRADE_CLASS_START_DATE,
                     LAST_UPDATED_DATE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_TRADE_CLASS_SID,
               COMPANY_MASTER_SID,
               TRADE_CLASS_START_DATE,
               TRADE_CLASS_END_DATE,
               COMPANY_TRADE_CLASS,
               PRIOR_TRADE_CLASS,
               PRIOR_TRADE_CLASS_START_DATE,
               LAST_UPDATED_DATE,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
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
           WHERE  [Name] = N'TRG_COMPANY_TRADE_CLASS_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_TRADE_CLASS_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_TRADE_CLASS_INS]
ON [dbo].[COMPANY_TRADE_CLASS]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_COMPANY_TRADE_CLASS
                    (COMPANY_TRADE_CLASS_SID,
                     COMPANY_MASTER_SID,
                     TRADE_CLASS_START_DATE,
                     TRADE_CLASS_END_DATE,
                     COMPANY_TRADE_CLASS,
                     PRIOR_TRADE_CLASS,
                     PRIOR_TRADE_CLASS_START_DATE,
                     LAST_UPDATED_DATE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_TRADE_CLASS_SID,
               COMPANY_MASTER_SID,
               TRADE_CLASS_START_DATE,
               TRADE_CLASS_END_DATE,
               COMPANY_TRADE_CLASS,
               PRIOR_TRADE_CLASS,
               PRIOR_TRADE_CLASS_START_DATE,
               LAST_UPDATED_DATE,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
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
           WHERE  [Name] = N'TRG_COMPANY_TRADE_CLASS_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_TRADE_CLASS_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_TRADE_CLASS_DEL]
ON [dbo].[COMPANY_TRADE_CLASS]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_COMPANY_TRADE_CLASS
                    (COMPANY_TRADE_CLASS_SID,
                     COMPANY_MASTER_SID,
                     TRADE_CLASS_START_DATE,
                     TRADE_CLASS_END_DATE,
                     COMPANY_TRADE_CLASS,
                     PRIOR_TRADE_CLASS,
                     PRIOR_TRADE_CLASS_START_DATE,
                     LAST_UPDATED_DATE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_TRADE_CLASS_SID,
               COMPANY_MASTER_SID,
               TRADE_CLASS_START_DATE,
               TRADE_CLASS_END_DATE,
               COMPANY_TRADE_CLASS,
               PRIOR_TRADE_CLASS,
               PRIOR_TRADE_CLASS_START_DATE,
               LAST_UPDATED_DATE,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'D'
        FROM   DELETED
  END

GO

-----------------------------------------COMPANY_PARENT_DETAILS--------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'COMPANY_PARENT_DETAILS'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[COMPANY_PARENT_DETAILS]
        (
           COMPANY_PARENT_DETAILS_SID   INT IDENTITY(1, 1) NOT NULL,
           COMPANY_MASTER_SID           INT NOT NULL,
           PARENT_COMPANY_MASTER_SID    INT NOT NULL,
           PARENT_START_DATE            DATETIME NULL,
           PARENT_END_DATE              DATETIME NULL,
           PRIOR_PARENT_CMPY_MASTER_SID INT NULL,
           PRIOR_PARENT_START_DATE      DATETIME NULL,
           LAST_UPDATED_DATE            DATETIME NULL,
           [INBOUND_STATUS]             CHAR(1) NOT NULL,
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

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_Name(OBJECT_ID) = N'COMPANY_PARENT_DETAILS'
                  AND Object_scHema_Name(OBJECT_ID) = N'DBO'
                  AND [NAME] = N'PK_COMPANY_PARENT_DETAILS_COMPANY_PARENT_DETAILS_SID')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_PARENT_DETAILS]
        ADD CONSTRAINT [PK_COMPANY_PARENT_DETAILS_COMPANY_PARENT_DETAILS_SID] PRIMARY KEY CLUSTERED ( [COMPANY_PARENT_DETAILS_SID] ASC ) WITH (FILLFACTOR = 80) ON [PRIMARY]
  END

GO


IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_PARENT_DETAILS')
                  AND NAME = 'DF_COMPANY_PARENT_DETAILS_LAST_UPDATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_PARENT_DETAILS]
        ADD CONSTRAINT [DF_COMPANY_PARENT_DETAILS_LAST_UPDATED_DATE] DEFAULT (GetDate()) FOR LAST_UPDATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_PARENT_DETAILS')
                  AND NAME = 'DF_COMPANY_PARENT_DETAILS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_PARENT_DETAILS]
        ADD CONSTRAINT [DF_COMPANY_PARENT_DETAILS_RECORD_LOCK_STATUS] DEFAULT (1) FOR [RECORD_LOCK_STATUS]
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_PARENT_DETAILS')
                  AND NAME = 'DF_COMPANY_PARENT_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_PARENT_DETAILS]
        ADD CONSTRAINT [DF_COMPANY_PARENT_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_PARENT_DETAILS')
                  AND NAME = 'DF_COMPANY_PARENT_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_PARENT_DETAILS]
        ADD CONSTRAINT [DF_COMPANY_PARENT_DETAILS_CREATED_DATE] DEFAULT (GetDate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_PARENT_DETAILS')
                  AND NAME = 'DF_COMPANY_PARENT_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_PARENT_DETAILS]
        ADD CONSTRAINT [DF_COMPANY_PARENT_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.COMPANY_PARENT_DETAILS')
                  AND NAME = 'DF_COMPANY_PARENT_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[COMPANY_PARENT_DETAILS]
        ADD CONSTRAINT [DF_COMPANY_PARENT_DETAILS_MODIFIED_DATE] DEFAULT (GetDate()) FOR MODIFIED_DATE
  END

GO

---------------------------------------UNIQUE_CONSTRAINT------------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'COMPANY_PARENT_DETAILS') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('COMPANY_PARENT_DETAILS')
                      AND NAME = 'UQ_COMPANY_PARENT_DETAILS_COMPANY_MASTER_SID_PARENT_COMPANY_MASTER_SID_PARENT_START_DATE')
  BEGIN
      ALTER TABLE COMPANY_PARENT_DETAILS
        ADD CONSTRAINT UQ_COMPANY_PARENT_DETAILS_COMPANY_MASTER_SID_PARENT_COMPANY_MASTER_SID_PARENT_START_DATE UNIQUE(COMPANY_MASTER_SID,PARENT_COMPANY_MASTER_SID,PARENT_START_DATE)
  END
END
GO



DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'COMPANY_PARENT_DETAILS'--TABLE NAME
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



--------------------------------------HIST_COMPANY_PARENT_DETAILS---------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_COMPANY_PARENT_DETAILS'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_COMPANY_PARENT_DETAILS]
        (
           COMPANY_PARENT_DETAILS_SID   INT NOT NULL,
           COMPANY_MASTER_SID           INT NOT NULL,
           PARENT_COMPANY_MASTER_SID    INT NOT NULL,
           PARENT_START_DATE            DATETIME NULL,
           PARENT_END_DATE              DATETIME NULL,
           PRIOR_PARENT_CMPY_MASTER_SID INT NULL,
           PRIOR_PARENT_START_DATE      DATETIME NULL,
           LAST_UPDATED_DATE            DATETIME NULL,
           [RECORD_LOCK_STATUS]         BIT NOT NULL,
           [BATCH_ID]                   VARCHAR(50) NOT NULL,
           [SOURCE]                     VARCHAR(50) NULL,
           [INBOUND_STATUS]             CHAR(1) NOT NULL,
           [CREATED_BY]                 INT NOT NULL,
           [CREATED_DATE]               DATETIME NOT NULL,
           [MODIFIED_BY]                INT NOT NULL,
           [MODIFIED_DATE]              DATETIME NOT NULL,
           ACTION_DATE                  DATETIME NOT NULL,
           ACTION_FLAG                  CHAR(1) NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.HIST_COMPANY_PARENT_DETAILS')
                  AND NAME = 'DF_HIST_COMPANY_PARENT_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_COMPANY_PARENT_DETAILS]
        ADD CONSTRAINT DF_HIST_COMPANY_PARENT_DETAILS_ACTION_DATE DEFAULT (GetDate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_COMPANY_PARENT_DETAILS'--TABLE NAME
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


---------------------------------------COMPANY_PARENT_DETAILS TRIGGER-------------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_COMPANY_PARENT_DETAILS_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_PARENT_DETAILS_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_PARENT_DETAILS_UPD]
ON [dbo].[COMPANY_PARENT_DETAILS]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_COMPANY_PARENT_DETAILS
                    (COMPANY_PARENT_DETAILS_SID,
                     COMPANY_MASTER_SID,
                     PARENT_COMPANY_MASTER_SID,
                     PARENT_START_DATE,
                     PARENT_END_DATE,
                     PRIOR_PARENT_CMPY_MASTER_SID,
                     PRIOR_PARENT_START_DATE,
                     LAST_UPDATED_DATE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_PARENT_DETAILS_SID,
               COMPANY_MASTER_SID,
               PARENT_COMPANY_MASTER_SID,
               PARENT_START_DATE,
               PARENT_END_DATE,
               PRIOR_PARENT_CMPY_MASTER_SID,
               PRIOR_PARENT_START_DATE,
               LAST_UPDATED_DATE,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
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
           WHERE  [Name] = N'TRG_COMPANY_PARENT_DETAILS_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_PARENT_DETAILS_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_PARENT_DETAILS_INS]
ON [dbo].[COMPANY_PARENT_DETAILS]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_COMPANY_PARENT_DETAILS
                    (COMPANY_PARENT_DETAILS_SID,
                     COMPANY_MASTER_SID,
                     PARENT_COMPANY_MASTER_SID,
                     PARENT_START_DATE,
                     PARENT_END_DATE,
                     PRIOR_PARENT_CMPY_MASTER_SID,
                     PRIOR_PARENT_START_DATE,
                     LAST_UPDATED_DATE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_PARENT_DETAILS_SID,
               COMPANY_MASTER_SID,
               PARENT_COMPANY_MASTER_SID,
               PARENT_START_DATE,
               PARENT_END_DATE,
               PRIOR_PARENT_CMPY_MASTER_SID,
               PRIOR_PARENT_START_DATE,
               LAST_UPDATED_DATE,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
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
           WHERE  [Name] = N'TRG_COMPANY_PARENT_DETAILS_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_COMPANY_PARENT_DETAILS_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_COMPANY_PARENT_DETAILS_DEL]
ON [dbo].[COMPANY_PARENT_DETAILS]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_COMPANY_PARENT_DETAILS
                    (COMPANY_PARENT_DETAILS_SID,
                     COMPANY_MASTER_SID,
                     PARENT_COMPANY_MASTER_SID,
                     PARENT_START_DATE,
                     PARENT_END_DATE,
                     PRIOR_PARENT_CMPY_MASTER_SID,
                     PRIOR_PARENT_START_DATE,
                     LAST_UPDATED_DATE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT COMPANY_PARENT_DETAILS_SID,
               COMPANY_MASTER_SID,
               PARENT_COMPANY_MASTER_SID,
               PARENT_START_DATE,
               PARENT_END_DATE,
               PRIOR_PARENT_CMPY_MASTER_SID,
               PRIOR_PARENT_START_DATE,
               LAST_UPDATED_DATE,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               INBOUND_STATUS,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'D'
        FROM   DELETED
  END

GO


----------------------------------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'IMTD_COMPANY_FINANCIAL_CLOSE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE IMTD_COMPANY_FINANCIAL_CLOSE
        (
           COMPANY_MASTER_SID INT NOT NULL,
           MODE               INT NOT NULL,
           CALENDAR           INT NOT NULL,
           PERIOD_SID         INT NOT NULL,
           BUSINESS_DAY       INT NULL,
           [HOUR]             INT NULL,
           [MINUTE]           INT NULL,
           [STATUS]           INT NOT NULL,
           STATUS_PERIOD_DATE DATETIME NOT NULL,
           CREATED_BY         INT NOT NULL,
           CREATED_DATE       DATETIME NOT NULL,
		   USERS_ID			  VARCHAR(50) NOT NULL,
		   SESSION_ID		  VARCHAR(50) NOT NULL
        )
  END
	
GO

