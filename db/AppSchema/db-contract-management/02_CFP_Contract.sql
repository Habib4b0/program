IF NOT EXISTS (SELECT 1
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CFP_CONTRACT'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE CFP_CONTRACT
        (
           CFP_CONTRACT_SID             INT NOT NULL IDENTITY(1, 1),
           CFP_MODEL_SID                INT NOT NULL,
           CFP_NAME                     VARCHAR(100) NOT NULL,
           CFP_TYPE                     INT NULL,
           CFP_CATEGORY                 INT NULL,
           CFP_DESIGNATION              VARCHAR(50) NULL,
           CFP_STATUS                   INT NOT NULL,
           CFP_TRADE_CLASS              INT NULL,
           CFP_START_DATE               DATETIME NOT NULL,
           CFP_END_DATE                 DATETIME NULL,
           CONTRACT_MASTER_SID          INT NOT NULL,
           CFP_CONTRACT_ATTACHED_STATUS INT NULL,
           CFP_CONTRACT_ATTACHED_DATE   DATETIME NULL,
           PARENT_CFP_ID                INT NULL,
           PARENT_CFP_NAME              VARCHAR(50) NULL,
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
  
 ----------------------------------------------
 IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CFP_CONTRACT'
                      AND COLUMN_NAME = 'SALES_INCLUSION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE CFP_CONTRACT
        ADD SALES_INCLUSION INT NULL
  END

GO 

 ---------------------------------CFP_NO----------------------------------------------

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CFP_CONTRACT'
                      AND COLUMN_NAME = 'CFP_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE CFP_CONTRACT
        ADD CFP_NO VARCHAR(50) 
  END

GO
-----------------------------UPDATE CFP_NO IN CFP_CONTRACT TABLE ----------------------------------
IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CFP_CONTRACT'
                      AND COLUMN_NAME = 'CFP_NO'
                      AND TABLE_SCHEMA = 'DBO'
					  )
BEGIN
UPDATE C 
SET C.CFP_NO=CF.CFP_NO
FROM CFP_CONTRACT C
INNER JOIN CFP_MODEL CF
ON C.CFP_MODEL_SID=CF.CFP_MODEL_SID
WHERE C.CFP_NO IS NULL
END
GO

-------------------------------------CFP_NO ALTER NULL TO NOT NULL----------------------------------------
IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CFP_CONTRACT'
                      AND COLUMN_NAME = 'CFP_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE CFP_CONTRACT
        ALTER COLUMN CFP_NO VARCHAR(50) NOT NULL
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'PK_CFP_CONTRACT_CFP_CONTRACT_SID'
                      AND TYPE = 'PK')
  BEGIN
      ALTER TABLE CFP_CONTRACT
        ADD CONSTRAINT PK_CFP_CONTRACT_CFP_CONTRACT_SID PRIMARY KEY (CFP_CONTRACT_SID)
  END

GO
-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE CFP_CONTRACT
        ADD CONSTRAINT DF_CFP_CONTRACT_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_CREATED_BY')
  BEGIN
      ALTER TABLE CFP_CONTRACT
        ADD CONSTRAINT DF_CFP_CONTRACT_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_CREATED_DATE')
  BEGIN
      ALTER TABLE CFP_CONTRACT
        ADD CONSTRAINT DF_CFP_CONTRACT_CREATED_DATE DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_MODIFIED_BY')
  BEGIN
      ALTER TABLE CFP_CONTRACT
        ADD CONSTRAINT DF_CFP_CONTRACT_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_MODIFIED_DATE')
  BEGIN
      ALTER TABLE CFP_CONTRACT
        ADD CONSTRAINT DF_CFP_CONTRACT_MODIFIED_DATE DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO
-------------------------------------UNIQUE_CONSTRAINT----------------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'CFP_CONTRACT') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('CFP_CONTRACT')
                      AND NAME = 'UQ_CFP_CONTRACT_CFP_MODEL_SID_CONTRACT_MASTER_SID')
  BEGIN
      ALTER TABLE CFP_CONTRACT
        ADD CONSTRAINT UQ_CFP_CONTRACT_CFP_MODEL_SID_CONTRACT_MASTER_SID UNIQUE(CFP_MODEL_SID ,CONTRACT_MASTER_SID)
  END
END
GO



DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'CFP_CONTRACT'--TABLE NAME
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

-----------------------------------HIST_CFP_CONTRACT---------------------------------

IF NOT EXISTS (SELECT 1
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'HIST_CFP_CONTRACT'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE HIST_CFP_CONTRACT
        (
           CFP_CONTRACT_SID             INT NOT NULL,
           CFP_MODEL_SID                INT NOT NULL,
           CFP_NAME                     VARCHAR(100) NOT NULL,
           CFP_TYPE                     INT NULL,
           CFP_CATEGORY                 INT NULL,
           CFP_DESIGNATION              VARCHAR(50) NULL,
           CFP_STATUS                   INT NOT NULL,
           CFP_TRADE_CLASS              INT NULL,
           CFP_START_DATE               DATETIME NOT NULL,
           CFP_END_DATE                 DATETIME NULL,
           CONTRACT_MASTER_SID          INT NOT NULL,
           CFP_CONTRACT_ATTACHED_STATUS INT NULL,
           CFP_CONTRACT_ATTACHED_DATE   DATETIME NULL,
           PARENT_CFP_ID                INT NULL,
           PARENT_CFP_NAME              VARCHAR(50) NULL,
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
----------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_CFP_CONTRACT'
                      AND COLUMN_NAME = 'SALES_INCLUSION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_CFP_CONTRACT
        ADD SALES_INCLUSION INT NULL
  END

GO 
----------------------------------------------------
---HIST_CFP_CONTRACT
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_CFP_CONTRACT'
                      AND COLUMN_NAME = 'CFP_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_CFP_CONTRACT
        ADD CFP_NO VARCHAR(50) 
  END

GO

IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_CFP_CONTRACT'
                      AND COLUMN_NAME = 'CFP_NO'
                      AND TABLE_SCHEMA = 'DBO'
					  )
BEGIN
UPDATE C 
SET C.CFP_NO=CF.CFP_NO
FROM HIST_CFP_CONTRACT C
INNER JOIN HIST_CFP_MODEL CF
ON C.CFP_MODEL_SID=CF.CFP_MODEL_SID
WHERE C.CFP_NO IS NULL
END
GO


IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_CFP_CONTRACT'
                      AND COLUMN_NAME = 'CFP_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_CFP_CONTRACT
        ALTER COLUMN CFP_NO VARCHAR(50) NOT NULL
  END

GO
---------------PRIMARY KEYS--------------------
--IF NOT EXISTS (SELECT 1 FROM SYS.KEY_CONSTRAINTS
--               WHERE OBJECT_NAME(PARENT_OBJECT_ID)='HIST_CFP_CONTRACT_MODEL'
--			   AND SCHEMA_NAME(SCHEMA_ID)='DBO'
--			   AND NAME='PK_HIST_CFP_CONTRACT_MODEL_CFP_CONTRACT_MODEL_SID_VERSION_NO'
--			   AND TYPE='PK')
--BEGIN
--ALTER TABLE HIST_CFP_CONTRACT_MODEL ADD CONSTRAINT PK_HIST_CFP_CONTRACT_MODEL_CFP_CONTRACT_MODEL_SID_VERSION_NO 
--PRIMARY KEY (CFP_CONTRACT_MODEL_SID,VERSION_NO)
--END
-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT *
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'HIST_CFP_CONTRACT'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_HIST_CFP_CONTRACT_ACTION_DATE')
  BEGIN
      ALTER TABLE HIST_CFP_CONTRACT
        ADD CONSTRAINT DF_HIST_CFP_CONTRACT_ACTION_DATE DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_CFP_CONTRACT'--TABLE NAME
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


-------------------------------------CFP_CONTRACT TRIGGER---------------------------------
IF EXISTS (
		SELECT 'X'
		FROM SYS.TRIGGERS
		WHERE [Name] = N'TRG_CFP_CONTRACT_INS'
		)
BEGIN
	DROP TRIGGER dbo.TRG_CFP_CONTRACT_INS
END
GO

CREATE TRIGGER [dbo].[TRG_CFP_CONTRACT_INS] ON [dbo].[CFP_CONTRACT]
AFTER INSERT
AS
BEGIN
SET NOCOUNT ON
	IF EXISTS (
			SELECT *
			FROM INSERTED
			)
		INSERT INTO HIST_CFP_CONTRACT (
			CFP_CONTRACT_SID
			,CFP_MODEL_SID
			,CFP_NAME
			,CFP_TYPE
			,CFP_CATEGORY
			,CFP_DESIGNATION
			,CFP_STATUS
			,CFP_TRADE_CLASS
			,CFP_START_DATE
			,CFP_END_DATE
			,CONTRACT_MASTER_SID
			,CFP_CONTRACT_ATTACHED_STATUS
			,CFP_CONTRACT_ATTACHED_DATE
			,PARENT_CFP_ID
			,PARENT_CFP_NAME
			,INBOUND_STATUS
			,RECORD_LOCK_STATUS
			,BATCH_ID
			,SOURCE
			,CREATED_BY
			,CREATED_DATE
			,MODIFIED_BY
			,MODIFIED_DATE
			,SALES_INCLUSION
			,ACTION_FLAG
			,CFP_NO
			)
		SELECT CFP_CONTRACT_SID
			,CFP_MODEL_SID
			,CFP_NAME
			,CFP_TYPE
			,CFP_CATEGORY
			,CFP_DESIGNATION
			,CFP_STATUS
			,CFP_TRADE_CLASS
			,CFP_START_DATE
			,CFP_END_DATE
			,CONTRACT_MASTER_SID
			,CFP_CONTRACT_ATTACHED_STATUS
			,CFP_CONTRACT_ATTACHED_DATE
			,PARENT_CFP_ID
			,PARENT_CFP_NAME
			,INBOUND_STATUS
			,RECORD_LOCK_STATUS
			,BATCH_ID
			,SOURCE
			,CREATED_BY
			,CREATED_DATE
			,MODIFIED_BY
			,MODIFIED_DATE
			,SALES_INCLUSION
			,'A'
			,CFP_NO
		FROM INSERTED
END
GO

IF EXISTS (
		SELECT 'X'
		FROM SYS.TRIGGERS
		WHERE [Name] = N'TRG_CFP_CONTRACT_UPD'
		)
BEGIN
	DROP TRIGGER dbo.TRG_CFP_CONTRACT_UPD
END
GO

CREATE TRIGGER [dbo].[TRG_CFP_CONTRACT_UPD] ON [dbo].[CFP_CONTRACT]
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
		INSERT INTO HIST_CFP_CONTRACT (
			CFP_CONTRACT_SID
			,CFP_MODEL_SID
			,CFP_NAME
			,CFP_TYPE
			,CFP_CATEGORY
			,CFP_DESIGNATION
			,CFP_STATUS
			,CFP_TRADE_CLASS
			,CFP_START_DATE
			,CFP_END_DATE
			,CONTRACT_MASTER_SID
			,CFP_CONTRACT_ATTACHED_STATUS
			,CFP_CONTRACT_ATTACHED_DATE
			,PARENT_CFP_ID
			,PARENT_CFP_NAME
			,INBOUND_STATUS
			,RECORD_LOCK_STATUS
			,BATCH_ID
			,SOURCE
			,CREATED_BY
			,CREATED_DATE
			,MODIFIED_BY
			,MODIFIED_DATE
			,SALES_INCLUSION
			,ACTION_FLAG
			,CFP_NO
			)
		SELECT CFP_CONTRACT_SID
			,CFP_MODEL_SID
			,CFP_NAME
			,CFP_TYPE
			,CFP_CATEGORY
			,CFP_DESIGNATION
			,CFP_STATUS
			,CFP_TRADE_CLASS
			,CFP_START_DATE
			,CFP_END_DATE
			,CONTRACT_MASTER_SID
			,CFP_CONTRACT_ATTACHED_STATUS
			,CFP_CONTRACT_ATTACHED_DATE
			,PARENT_CFP_ID
			,PARENT_CFP_NAME
			,INBOUND_STATUS
			,RECORD_LOCK_STATUS
			,BATCH_ID
			,SOURCE
			,CREATED_BY
			,CREATED_DATE
			,MODIFIED_BY
			,MODIFIED_DATE
			,SALES_INCLUSION
			,'C'
			,CFP_NO
		FROM INSERTED
END
GO

IF EXISTS (
		SELECT 'X'
		FROM SYS.TRIGGERS
		WHERE [Name] = N'TRG_CFP_CONTRACT_DEL'
		)
BEGIN
	DROP TRIGGER dbo.TRG_CFP_CONTRACT_DEL
END
GO

CREATE TRIGGER [dbo].[TRG_CFP_CONTRACT_DEL] ON [dbo].[CFP_CONTRACT]
AFTER DELETE
AS
BEGIN
SET NOCOUNT ON
	IF EXISTS (
			SELECT *
			FROM DELETED
			)
		INSERT INTO HIST_CFP_CONTRACT (
			CFP_CONTRACT_SID
			,CFP_MODEL_SID
			,CFP_NAME
			,CFP_TYPE
			,CFP_CATEGORY
			,CFP_DESIGNATION
			,CFP_STATUS
			,CFP_TRADE_CLASS
			,CFP_START_DATE
			,CFP_END_DATE
			,CONTRACT_MASTER_SID
			,CFP_CONTRACT_ATTACHED_STATUS
			,CFP_CONTRACT_ATTACHED_DATE
			,PARENT_CFP_ID
			,PARENT_CFP_NAME
			,INBOUND_STATUS
			,RECORD_LOCK_STATUS
			,BATCH_ID
			,SOURCE
			,CREATED_BY
			,CREATED_DATE
			,MODIFIED_BY
			,MODIFIED_DATE
			,SALES_INCLUSION
			,ACTION_FLAG
			,CFP_NO
			)
		SELECT CFP_CONTRACT_SID
			,CFP_MODEL_SID
			,CFP_NAME
			,CFP_TYPE
			,CFP_CATEGORY
			,CFP_DESIGNATION
			,CFP_STATUS
			,CFP_TRADE_CLASS
			,CFP_START_DATE
			,CFP_END_DATE
			,CONTRACT_MASTER_SID
			,CFP_CONTRACT_ATTACHED_STATUS
			,CFP_CONTRACT_ATTACHED_DATE
			,PARENT_CFP_ID
			,PARENT_CFP_NAME
			,INBOUND_STATUS
			,RECORD_LOCK_STATUS
			,BATCH_ID
			,SOURCE
			,CREATED_BY
			,CREATED_DATE
			,MODIFIED_BY
			,MODIFIED_DATE
			,SALES_INCLUSION
			,'D'
			,CFP_NO
		FROM DELETED
END
GO
-----------------------------------CFP_CONTRACT_DETAILS-----------------------------------

IF NOT EXISTS (SELECT 1
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CFP_CONTRACT_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE CFP_CONTRACT_DETAILS
        (
           CFP_CONTRACT_DETAILS_SID     INT NOT NULL IDENTITY(1, 1),
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
           [MODIFIED_DATE]              DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'PK_CFP_CONTRACT_DETAILS_CFP_CONTRACT_DETAILS_SID'
                      AND TYPE = 'PK')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS
        ADD CONSTRAINT PK_CFP_CONTRACT_DETAILS_CFP_CONTRACT_DETAILS_SID PRIMARY KEY (CFP_CONTRACT_DETAILS_SID)
  END

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_DETAILS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS
        ADD CONSTRAINT DF_CFP_CONTRACT_DETAILS_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS
        ADD CONSTRAINT DF_CFP_CONTRACT_DETAILS_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS
        ADD CONSTRAINT DF_CFP_CONTRACT_DETAILS_CREATED_DATE DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS
        ADD CONSTRAINT DF_CFP_CONTRACT_DETAILS_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CFP_CONTRACT_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CFP_CONTRACT_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS
        ADD CONSTRAINT DF_CFP_CONTRACT_DETAILS_MODIFIED_DATE DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

-------------------------------------UNIQUE_CONSTRAINT----------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'CFP_CONTRACT_DETAILS') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('CFP_CONTRACT_DETAILS')
                      AND NAME = 'UQ_CFP_CONTRACT_DETAILS_COMPANY_MASTER_SID_CFP_CONTRACT_SID_COMPANY_START_DATE')
  BEGIN
      ALTER TABLE CFP_CONTRACT_DETAILS
        ADD CONSTRAINT UQ_CFP_CONTRACT_DETAILS_COMPANY_MASTER_SID_CFP_CONTRACT_SID_COMPANY_START_DATE UNIQUE(COMPANY_MASTER_SID, CFP_CONTRACT_SID,COMPANY_START_DATE)
  END
END
GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'CFP_CONTRACT_DETAILS'--TABLE NAME
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

---------------------------------------HIST_CFP_CONTRACT_DETAILS--------------------------------------------


IF NOT EXISTS (SELECT 1
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'HIST_CFP_CONTRACT_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE HIST_CFP_CONTRACT_DETAILS
        (
           CFP_CONTRACT_DETAILS_SID     INT NOT NULL,
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
           ACTION_FLAG                  CHAR(1) NOT NULL,
           ACTION_DATE                  DATETIME NOT NULL
        )
  END

GO
---------------PRIMARY KEYS--------------------
--IF NOT EXISTS (SELECT 1 FROM SYS.KEY_CONSTRAINTS
--               WHERE OBJECT_NAME(PARENT_OBJECT_ID)='HIST_CFPCONTRACTMODEL_DETAILS'
--			   AND SCHEMA_NAME(SCHEMA_ID)='DBO'
--			   AND NAME='PK_HIST_CFPCONTRACTMODEL_DETAILS_CFP_CONTRACTMODEL_DETAILS_SID_VERSION_NO'
--			   AND TYPE='PK')
--BEGIN
--ALTER TABLE HIST_CFPCONTRACTMODEL_DETAILS ADD CONSTRAINT PK_HIST_CFPCONTRACTMODEL_DETAILS_CFP_CONTRACTMODEL_DETAILS_SID_VERSION_NO
--PRIMARY KEY (CFP_CONTRACTMODEL_DETAILS_SID,VERSION_NO)
--END
-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT *
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'HIST_CFP_CONTRACT_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_HIST_CFP_CONTRACT_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE HIST_CFP_CONTRACT_DETAILS
        ADD CONSTRAINT DF_HIST_CFP_CONTRACT_DETAILS_ACTION_DATE DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_CFP_CONTRACT_DETAILS'--TABLE NAME
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


----------------------------------------CFP_CONTRACT_DETAILS TRIGGER----------------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_CFP_CONTRACT_DETAILS_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_CFP_CONTRACT_DETAILS_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_CFP_CONTRACT_DETAILS_INS]
ON [dbo].[CFP_CONTRACT_DETAILS]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_CFP_CONTRACT_DETAILS
                    (CFP_CONTRACT_DETAILS_SID,
                     CFP_CONTRACT_SID,
                     COMPANY_MASTER_SID,
                     COMPANY_START_DATE,
                     COMPANY_END_DATE,
                     TRADE_CLASS,
                     TRADE_CLASS_START_DATE,
                     TRADE_CLASS_END_DATE,
                     CFP_CONTRACT_ATTACHED_STATUS,
                     CFP_CONTRACT_ATTACHED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT CFP_CONTRACT_DETAILS_SID,
               CFP_CONTRACT_SID,
               COMPANY_MASTER_SID,
               COMPANY_START_DATE,
               COMPANY_END_DATE,
               TRADE_CLASS,
               TRADE_CLASS_START_DATE,
               TRADE_CLASS_END_DATE,
               CFP_CONTRACT_ATTACHED_STATUS,
               CFP_CONTRACT_ATTACHED_DATE,
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
           WHERE  [Name] = N'TRG_CFP_CONTRACT_DETAILS_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_CFP_CONTRACT_DETAILS_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_CFP_CONTRACT_DETAILS_UPD]
ON [dbo].[CFP_CONTRACT_DETAILS]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_CFP_CONTRACT_DETAILS
                    (CFP_CONTRACT_DETAILS_SID,
                     CFP_CONTRACT_SID,
                     COMPANY_MASTER_SID,
                     COMPANY_START_DATE,
                     COMPANY_END_DATE,
                     TRADE_CLASS,
                     TRADE_CLASS_START_DATE,
                     TRADE_CLASS_END_DATE,
                     CFP_CONTRACT_ATTACHED_STATUS,
                     CFP_CONTRACT_ATTACHED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT CFP_CONTRACT_DETAILS_SID,
               CFP_CONTRACT_SID,
               COMPANY_MASTER_SID,
               COMPANY_START_DATE,
               COMPANY_END_DATE,
               TRADE_CLASS,
               TRADE_CLASS_START_DATE,
               TRADE_CLASS_END_DATE,
               CFP_CONTRACT_ATTACHED_STATUS,
               CFP_CONTRACT_ATTACHED_DATE,
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
           WHERE  [Name] = N'TRG_CFP_CONTRACT_DETAILS_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_CFP_CONTRACT_DETAILS_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_CFP_CONTRACT_DETAILS_DEL]
ON [dbo].[CFP_CONTRACT_DETAILS]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_CFP_CONTRACT_DETAILS
                    (CFP_CONTRACT_DETAILS_SID,
                     CFP_CONTRACT_SID,
                     COMPANY_MASTER_SID,
                     COMPANY_START_DATE,
                     COMPANY_END_DATE,
                     TRADE_CLASS,
                     TRADE_CLASS_START_DATE,
                     TRADE_CLASS_END_DATE,
                     CFP_CONTRACT_ATTACHED_STATUS,
                     CFP_CONTRACT_ATTACHED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT CFP_CONTRACT_DETAILS_SID,
               CFP_CONTRACT_SID,
               COMPANY_MASTER_SID,
               COMPANY_START_DATE,
               COMPANY_END_DATE,
               TRADE_CLASS,
               TRADE_CLASS_START_DATE,
               TRADE_CLASS_END_DATE,
               CFP_CONTRACT_ATTACHED_STATUS,
               CFP_CONTRACT_ATTACHED_DATE,
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