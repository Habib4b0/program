IF NOT EXISTS (SELECT 'X'
               FROM     SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[PS_CONTRACT]
        (
           PS_CONTRACT_SID             INT NOT NULL IDENTITY(1, 1),
           PS_MODEL_SID                INT NOT NULL,
           PS_NAME                     VARCHAR(100) NULL,
           PS_TYPE                     INT NULL,
           PS_CATEGORY                 INT NULL,
           PS_STATUS                   INT NULL,
           PS_DESIGNATION              VARCHAR(50) NULL,
           PS_START_DATE               DATETIME NOT NULL,
           PS_END_DATE                 DATETIME NULL,
           CONTRACT_MASTER_SID         INT NOT NULL,
           CFP_CONTRACT_SID            INT NULL,
           IFP_CONTRACT_SID            INT NULL,
           PS_CONTRACT_ATTACHED_STATUS INT NULL,
           PS_CONTRACT_ATTACHED_DATE   DATETIME NULL,
           PARENT_PS_ID                VARCHAR(50) NULL,
           PARENT_PS_NAME              VARCHAR(100) NULL,
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

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'PS_DESIGNATION'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.PS_CONTRACT.PS_DESIGNATION
  END

GO

------------------------------------------------------------PS_CONTRACT--------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT'
                      AND COLUMN_NAME = 'PS_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT
        ADD PS_NO VARCHAR(50) 
  END

GO

-----------------------------UPDATE PS_NO IN PS_CONTRACT TABLE ----------------------------------
IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT'
                      AND COLUMN_NAME = 'PS_NO'
                      AND TABLE_SCHEMA = 'DBO')
BEGIN
UPDATE C 
SET C.PS_NO=CF.PS_NO
FROM PS_CONTRACT C
INNER JOIN PS_MODEL CF
ON C.PS_MODEL_SID=CF.PS_MODEL_SID
WHERE C.PS_NO IS NULL
END
GO

-------------------------------------------ALTER

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT'
                  AND COLUMN_NAME = 'PS_DESIGNATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT
        ALTER COLUMN PS_DESIGNATION INT
  END

GO
-------------------------------------PS_NO ALTER NULL TO NOT NULL----------------------------------------
IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT'
                      AND COLUMN_NAME = 'PS_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT
        ALTER COLUMN PS_NO VARCHAR(50) NOT NULL
  END

GO
---------------------------------------------------ADIING COLUMNS STARTS HERE---------------------------
--PS_TRADE_CLASS
IF NOT EXISTS(SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'PS_CONTRACT'
                     AND COLUMN_NAME = 'PS_TRADE_CLASS'
                     AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT
        ADD PS_TRADE_CLASS INT NULL
  END

GO

---------------------------------------------------ADIING COLUMNS ENDS HERE---------------------------
---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'PS_CONTRACT'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_PS_CONTRACT_PS_CONTRACT_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [DBO].[PS_CONTRACT]
    ADD CONSTRAINT PK_PS_CONTRACT_PS_CONTRACT_SID PRIMARY KEY(PS_CONTRACT_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT')
                      AND NAME = 'DF_PS_CONTRACT_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT]
        ADD CONSTRAINT [DF_PS_CONTRACT_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT')
                      AND NAME = 'DF_PS_CONTRACT_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT]
        ADD CONSTRAINT [DF_PS_CONTRACT_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT')
                      AND NAME = 'DF_PS_CONTRACT_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT]
        ADD CONSTRAINT [DF_PS_CONTRACT_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT')
                      AND NAME = 'DF_PS_CONTRACT_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT]
        ADD CONSTRAINT [DF_PS_CONTRACT_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT')
                      AND NAME = 'DF_PS_CONTRACT_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT]
        ADD CONSTRAINT [DF_PS_CONTRACT_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

----------------------------------------------UNIQUE_CONSTRAINT------------------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'PS_CONTRACT')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('PS_CONTRACT')
                            AND NAME = 'UQ_PS_CONTRACT_PS_MODEL_SID_IFP_CONTRACT_SID_CFP_CONTRACT_SID_CONTRACT_MASTER_SID')
        BEGIN
            ALTER TABLE PS_CONTRACT
              ADD CONSTRAINT UQ_PS_CONTRACT_PS_MODEL_SID_IFP_CONTRACT_SID_CFP_CONTRACT_SID_CONTRACT_MASTER_SID UNIQUE(PS_MODEL_SID, IFP_CONTRACT_SID, CFP_CONTRACT_SID, CONTRACT_MASTER_SID)
        END
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'PS_CONTRACT'--TABLE NAME
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

------------------------------------------HIST_PS_CONTRACT---------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_PS_CONTRACT]
        (
           PS_CONTRACT_SID             INT NOT NULL,
           PS_MODEL_SID                INT NOT NULL,
           PS_NAME                     VARCHAR(100) NULL,
           PS_TYPE                     INT NULL,
           PS_CATEGORY                 INT NULL,
           PS_STATUS                   INT NULL,
           PS_DESIGNATION              VARCHAR(50) NULL,
           PS_START_DATE               DATETIME NOT NULL,
           PS_END_DATE                 DATETIME NULL,
           CONTRACT_MASTER_SID         INT NOT NULL,
           CFP_CONTRACT_SID            INT NULL,
           IFP_CONTRACT_SID            INT NULL,
           PS_CONTRACT_ATTACHED_STATUS INT NULL,
           PS_CONTRACT_ATTACHED_DATE   DATETIME NULL,
           PARENT_PS_ID                VARCHAR(50) NULL,
           PARENT_PS_NAME              VARCHAR(100) NULL,
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

---------------------------------------------------ADIING COLUMNS STARTS HERE---------------------------
--PS_TRADE_CLASS
IF NOT EXISTS(SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'HIST_PS_CONTRACT'
                     AND COLUMN_NAME = 'PS_TRADE_CLASS'
                     AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT
        ADD PS_TRADE_CLASS INT NULL
  END

GO

---HIST_PS_CONTRACT
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT'
                      AND COLUMN_NAME = 'PS_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT
        ADD PS_NO VARCHAR(50) 
  END

GO
IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT'
                      AND COLUMN_NAME = 'PS_NO'
                      AND TABLE_SCHEMA = 'DBO')
BEGIN
UPDATE C 
SET C.PS_NO=CF.PS_NO
FROM HIST_PS_CONTRACT C
INNER JOIN HIST_PS_MODEL CF
ON C.PS_MODEL_SID=CF.PS_MODEL_SID
WHERE C.PS_NO IS NULL
END
GO

--------------------------------ALTER

IF EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT'
                      AND COLUMN_NAME = 'PS_NO'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT
        ALTER COLUMN PS_NO VARCHAR(50) NOT NULL
  END

GO
---------------------------------------------------ADIING COLUMNS ENDS HERE---------------------------
IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'PS_DESIGNATION'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_CONTRACT.PS_DESIGNATION
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT'
                  AND COLUMN_NAME = 'PS_DESIGNATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT
        ALTER COLUMN PS_DESIGNATION INT
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_PS_CONTRACT')
                      AND NAME = 'DF_HIST_PS_CONTRACT_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_PS_CONTRACT]
        ADD CONSTRAINT [DF_HIST_PS_CONTRACT_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_PS_CONTRACT'--TABLE NAME
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

-----------------------------------------------PS_CONTRACT TRIGGER------------------------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_PS_CONTRACT_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_CONTRACT_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_CONTRACT_INS]
ON [DBO].[PS_CONTRACT]
AFTER INSERT
AS
  BEGIN
      SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_PS_CONTRACT
                    (PS_CONTRACT_SID,
                     PS_MODEL_SID,
                     PS_NAME,
                     PS_TYPE,
                     PS_CATEGORY,
                     PS_STATUS,
                     PS_DESIGNATION,
                     PS_START_DATE,
                     PS_END_DATE,
                     CONTRACT_MASTER_SID,
                     CFP_CONTRACT_SID,
                     IFP_CONTRACT_SID,
                     PS_CONTRACT_ATTACHED_STATUS,
                     PS_CONTRACT_ATTACHED_DATE,
                     PARENT_PS_ID,
                     PARENT_PS_NAME,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG,
                     PS_TRADE_CLASS,
					 PS_NO)
        SELECT PS_CONTRACT_SID,
               PS_MODEL_SID,
               PS_NAME,
               PS_TYPE,
               PS_CATEGORY,
               PS_STATUS,
               PS_DESIGNATION,
               PS_START_DATE,
               PS_END_DATE,
               CONTRACT_MASTER_SID,
               CFP_CONTRACT_SID,
               IFP_CONTRACT_SID,
               PS_CONTRACT_ATTACHED_STATUS,
               PS_CONTRACT_ATTACHED_DATE,
               PARENT_PS_ID,
               PARENT_PS_NAME,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'A',
               PS_TRADE_CLASS
			   ,PS_NO
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_PS_CONTRACT_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_CONTRACT_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_CONTRACT_UPD]
ON [DBO].[PS_CONTRACT]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_PS_CONTRACT
                    (PS_CONTRACT_SID,
                     PS_MODEL_SID,
                     PS_NAME,
                     PS_TYPE,
                     PS_CATEGORY,
                     PS_STATUS,
                     PS_DESIGNATION,
                     PS_START_DATE,
                     PS_END_DATE,
                     CONTRACT_MASTER_SID,
                     CFP_CONTRACT_SID,
                     IFP_CONTRACT_SID,
                     PS_CONTRACT_ATTACHED_STATUS,
                     PS_CONTRACT_ATTACHED_DATE,
                     PARENT_PS_ID,
                     PARENT_PS_NAME,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG,
                     PS_TRADE_CLASS
					 ,PS_NO)
        SELECT PS_CONTRACT_SID,
               PS_MODEL_SID,
               PS_NAME,
               PS_TYPE,
               PS_CATEGORY,
               PS_STATUS,
               PS_DESIGNATION,
               PS_START_DATE,
               PS_END_DATE,
               CONTRACT_MASTER_SID,
               CFP_CONTRACT_SID,
               IFP_CONTRACT_SID,
               PS_CONTRACT_ATTACHED_STATUS,
               PS_CONTRACT_ATTACHED_DATE,
               PARENT_PS_ID,
               PARENT_PS_NAME,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'C',
               PS_TRADE_CLASS
			   ,PS_NO
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_PS_CONTRACT_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_CONTRACT_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_CONTRACT_DEL]
ON [DBO].[PS_CONTRACT]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_PS_CONTRACT
                    (PS_CONTRACT_SID,
                     PS_MODEL_SID,
                     PS_NAME,
                     PS_TYPE,
                     PS_CATEGORY,
                     PS_STATUS,
                     PS_DESIGNATION,
                     PS_START_DATE,
                     PS_END_DATE,
                     CONTRACT_MASTER_SID,
                     CFP_CONTRACT_SID,
                     IFP_CONTRACT_SID,
                     PS_CONTRACT_ATTACHED_STATUS,
                     PS_CONTRACT_ATTACHED_DATE,
                     PARENT_PS_ID,
                     PARENT_PS_NAME,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG,
                     PS_TRADE_CLASS
					 ,PS_NO)
        SELECT PS_CONTRACT_SID,
               PS_MODEL_SID,
               PS_NAME,
               PS_TYPE,
               PS_CATEGORY,
               PS_STATUS,
               PS_DESIGNATION,
               PS_START_DATE,
               PS_END_DATE,
               CONTRACT_MASTER_SID,
               CFP_CONTRACT_SID,
               IFP_CONTRACT_SID,
               PS_CONTRACT_ATTACHED_STATUS,
               PS_CONTRACT_ATTACHED_DATE,
               PARENT_PS_ID,
               PARENT_PS_NAME,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               'D',
               PS_TRADE_CLASS
			   ,PS_NO
        FROM   DELETED
  END

GO

------------------------------------------PS_CONTRACT_DETAILS----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[PS_CONTRACT_DETAILS]
        (
           PS_CONTRACT_DETAILS_SID     INT NOT NULL IDENTITY(1, 1),
           PS_CONTRACT_SID             INT NOT NULL,
           ITEM_MASTER_SID             INT NOT NULL,
           CONTRACT_PRICE              NUMERIC(22, 6) NULL,
           ITEM_PRICING_QUALIFIER_SID  INT NOT NULL,
           CONTRACT_PRICE_START_DATE   DATETIME NOT NULL,
           CONTRACT_PRICE_END_DATE     DATETIME NULL,
           BASE_PRICE                  NUMERIC(22, 6) NULL,
           PRICE_TOLERANCE             NUMERIC(22, 6) NULL,
           PRICE_TOLERANCE_TYPE        INT NULL,
           PRICE_TOLERANCE_FREQUENCY   INT NULL,
           PRICE_TOLERANCE_INTERVAL    INT NULL,
           PRICE_PROTECTION_START_DATE DATETIME NULL,
           PRICE_PROTECTION_END_DATE   DATETIME NULL,
           PRICE_REVISION              NUMERIC(22, 6) NULL,
           REVISION_DATE               DATETIME NULL,
           SUGGESTED_PRICE             NUMERIC(22, 6) NULL,
           PRICE                       NUMERIC(22, 6) NOT NULL,
           PS_CONTRACT_ATTACHED_STATUS INT NULL,
           PS_CONTRACT_ATTACHED_DATE   DATETIME NULL,
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

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'BRAND_MASTER_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD BRAND_MASTER_SID INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD BASE_PRICE_TYPE VARCHAR(50)
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_ENTRY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD BASE_PRICE_ENTRY NUMERIC(22, 6)
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD BASE_PRICE_DATE DATETIME
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD NET_BASE_PRICE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_DDLB'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD BASE_PRICE_DDLB INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD NET_BASE_PRICE_FORMULA_ID INT
  END

GO

------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'SUBSEQUENT_PERIOD_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD SUBSEQUENT_PERIOD_PRICE_TYPE INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD NET_SUBSEQUENT_PERIOD_PRICE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD NET_SUBSEQUENT_PRICE_FORMULA_ID INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD RESET_PRICE_TYPE INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD NET_RESET_PRICE_TYPE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD NET_RESET_PRICE_FORMULA_ID INT
  END

GO

-------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NEP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD NEP NUMERIC(22, 6)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_STATUS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD PRICE_PROTECTION_STATUS INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD PRICE_PROTECTION_PRICE_TYPE INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NEP_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD NEP_FORMULA VARCHAR(100)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'MAX_INCREMENTAL_CHANGE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD MAX_INCREMENTAL_CHANGE NUMERIC(22, 6)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_ELIGIBLE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD RESET_ELIGIBLE VARCHAR(100)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD RESET_TYPE INT
  END

GO-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD RESET_DATE DATETIME
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_INTERVAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD RESET_INTERVAL INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_FREQUENCY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD RESET_FREQUENCY INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD NET_PRICE_TYPE VARCHAR(100)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD NET_PRICE_TYPE_FORMULA VARCHAR(100)
  END

GO

---------------------- DROP STATISTICS -----------------------------
IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_INTERVAL'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_CONTRACT_DETAILS.PRICE_TOLERANCE_INTERVAL
  END

GO

--------------------------------DATATYPE CHANGES STARTS----------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'PRICE_TOLERANCE_INTERVAL'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_INTERVAL INT
  END

GO

---------------------------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_BASE_PRICE'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_CONTRACT_DETAILS.NET_BASE_PRICE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_BASE_PRICE'
                  AND TABLE_NAME = 'PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN NET_BASE_PRICE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_CONTRACT_DETAILS.NET_SUBSEQUENT_PERIOD_PRICE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                  AND TABLE_NAME = 'PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN NET_SUBSEQUENT_PERIOD_PRICE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_RESET_PRICE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_CONTRACT_DETAILS.NET_RESET_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_RESET_PRICE_TYPE'
                  AND TABLE_NAME = 'PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN NET_RESET_PRICE_TYPE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'BASE_PRICE_ENTRY'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_CONTRACT_DETAILS.BASE_PRICE_ENTRY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'BASE_PRICE_ENTRY'
                  AND TABLE_NAME = 'PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN BASE_PRICE_ENTRY NUMERIC(22, 6)
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_FREQUENCY'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_CONTRACT_DETAILS.PRICE_TOLERANCE_FREQUENCY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'PRICE_TOLERANCE_FREQUENCY'
                  AND TABLE_NAME = 'PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_FREQUENCY INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.PS_CONTRACT_DETAILS.PRICE_TOLERANCE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'PRICE_TOLERANCE_TYPE'
                  AND TABLE_NAME = 'PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_TYPE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'BASE_PRICE_TYPE')
  BEGIN
      DROP STATISTICS PS_CONTRACT_DETAILS.BASE_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN BASE_PRICE_TYPE INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NEP_FORMULA')
  BEGIN
      DROP STATISTICS PS_CONTRACT_DETAILS.NEP_FORMULA
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NEP_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN NEP_FORMULA INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'RESET_ELIGIBLE')
  BEGIN
      DROP STATISTICS PS_CONTRACT_DETAILS.RESET_ELIGIBLE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'RESET_ELIGIBLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN RESET_ELIGIBLE INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NET_PRICE_TYPE')
  BEGIN
      DROP STATISTICS PS_CONTRACT_DETAILS.NET_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NET_PRICE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN NET_PRICE_TYPE INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NET_PRICE_TYPE_FORMULA')
  BEGIN
      DROP STATISTICS PS_CONTRACT_DETAILS.NET_PRICE_TYPE_FORMULA
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN NET_PRICE_TYPE_FORMULA INT NULL
  END

GO

-------------------------------------------------------- PS_CONTRACT_DETAILS
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'BASE_PRICE_TYPE')
  BEGIN
      DROP STATISTICS PS_CONTRACT_DETAILS.BASE_PRICE_TYPE
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN BASE_PRICE_TYPE INT NULL
  END
GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NEP_FORMULA')
  BEGIN
      DROP STATISTICS PS_CONTRACT_DETAILS.NEP_FORMULA
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NEP_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN NEP_FORMULA INT NULL
  END
GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'RESET_ELIGIBLE')
  BEGIN
      DROP STATISTICS PS_CONTRACT_DETAILS.RESET_ELIGIBLE
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'RESET_ELIGIBLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN RESET_ELIGIBLE INT NULL
  END
GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NET_PRICE_TYPE')
  BEGIN
      DROP STATISTICS PS_CONTRACT_DETAILS.NET_PRICE_TYPE
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NET_PRICE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN NET_PRICE_TYPE INT NULL
  END
GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NET_PRICE_TYPE_FORMULA')
  BEGIN
      DROP STATISTICS PS_CONTRACT_DETAILS.NET_PRICE_TYPE_FORMULA
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PS_CONTRACT_DETAILS
        ALTER COLUMN NET_PRICE_TYPE_FORMULA INT NULL
  END
GO

---------------------DATATYPE CHANGE ENDS---------------------
----------ALG-81------
IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'PS_CONTRACT_DETAILS'
			AND COLUMN_NAME = 'PPA_INDEX_NO'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	IF EXISTS (
			SELECT 1
			FROM PS_CONTRACT_DETAILS
			)
	BEGIN
		ALTER TABLE PS_CONTRACT_DETAILS ADD PPA_INDEX_NO INT
	END
	ELSE
	BEGIN
		ALTER TABLE PS_CONTRACT_DETAILS ADD PPA_INDEX_NO INT NOT NULL
	END
END
GO

IF EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'PS_CONTRACT_DETAILS'
			AND COLUMN_NAME = 'PPA_INDEX_NO'
			AND TABLE_SCHEMA = 'DBO'
			AND DATA_TYPE = 'INT'
			AND IS_NULLABLE = 'YES'
		)
BEGIN
	UPDATE PS_CONTRACT_DETAILS
	SET PPA_INDEX_NO = 1
	WHERE PPA_INDEX_NO IS NULL

	ALTER TABLE PS_CONTRACT_DETAILS

	ALTER COLUMN PPA_INDEX_NO INT NOT NULL
END



IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'PS_CONTRACT_DETAILS')
  BEGIN
      IF  EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('PS_CONTRACT_DETAILS')
                            AND NAME = 'UQ_PS_CONTRACT_DETAILS_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE')
        BEGIN
            ALTER TABLE PS_CONTRACT_DETAILS
            DROP CONSTRAINT UQ_PS_CONTRACT_DETAILS_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE 
        END
  END

GO


IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'PS_CONTRACT_DETAILS')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('PS_CONTRACT_DETAILS')
                            AND NAME = 'UQ_PS_CONTRACT_DETAILS_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE_PPA_INDEX_NO')
        BEGIN
            ALTER TABLE PS_CONTRACT_DETAILS
              ADD CONSTRAINT UQ_PS_CONTRACT_DETAILS_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE_PPA_INDEX_NO 
			  UNIQUE(PS_CONTRACT_SID, ITEM_MASTER_SID, CONTRACT_PRICE_START_DATE,PPA_INDEX_NO)
        END
  END

GO
---------------------------------------------
---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'PS_CONTRACT_DETAILS'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_PS_CONTRACT_DETAILS_PS_CONTRACT_DETAILS_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [DBO].[PS_CONTRACT_DETAILS]
    ADD CONSTRAINT PK_PS_CONTRACT_DETAILS_PS_CONTRACT_DETAILS_SID PRIMARY KEY(PS_CONTRACT_DETAILS_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS')
                      AND NAME = 'DF_PS_CONTRACT_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_PS_CONTRACT_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS')
                      AND NAME = 'DF_PS_CONTRACT_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_PS_CONTRACT_DETAILS_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS')
                      AND NAME = 'DF_PS_CONTRACT_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_PS_CONTRACT_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS')
                      AND NAME = 'DF_PS_CONTRACT_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_PS_CONTRACT_DETAILS_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS')
                      AND NAME = 'DF_PS_CONTRACT_DETAILS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_PS_CONTRACT_DETAILS_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

-------------ALG-81
IF NOT EXISTS (
		SELECT 'X'
		FROM SYS.DEFAULT_CONSTRAINTS
		WHERE PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS')
			AND NAME = 'DF_PS_CONTRACT_DETAILS_PPA_INDEX_NO'
		)
BEGIN
	ALTER TABLE [DBO].[PS_CONTRACT_DETAILS] ADD CONSTRAINT [DF_PS_CONTRACT_DETAILS_PPA_INDEX_NO] DEFAULT(1)
	FOR PPA_INDEX_NO
END
GO
--------- ADDING COLUMN ------
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PS_CONTRACT_DETAILS' AND COLUMN_NAME  = 'ADD_COPY_INDICATOR' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PS_CONTRACT_DETAILS
        ADD ADD_COPY_INDICATOR CHAR(1)
  END
GO


------------------------------------------UNIQUE_CONSTRAINT--------------------------------------
--IF EXISTS (SELECT NAME
--           FROM   SYS.TABLES
--           WHERE  NAME = 'PS_CONTRACT_DETAILS')
--  BEGIN
--      IF NOT EXISTS (SELECT 1
--                     FROM   SYS.KEY_CONSTRAINTS
--                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
--                            AND PARENT_OBJECT_ID = OBJECT_ID('PS_CONTRACT_DETAILS')
--                            AND NAME = 'UQ_PS_CONTRACT_DETAILS_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE')
--        BEGIN
--            ALTER TABLE PS_CONTRACT_DETAILS
--              ADD CONSTRAINT UQ_PS_CONTRACT_DETAILS_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE UNIQUE(PS_CONTRACT_SID, ITEM_MASTER_SID, CONTRACT_PRICE_START_DATE)
--        END
--  END

--GO
-------------------ALG-81

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'PS_CONTRACT_DETAILS')
  BEGIN
      IF  EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('PS_CONTRACT_DETAILS')
                            AND NAME = 'UQ_PS_CONTRACT_DETAILS_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE')
        BEGIN
            ALTER TABLE PS_CONTRACT_DETAILS
            DROP CONSTRAINT UQ_PS_CONTRACT_DETAILS_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE 
        END
  END

GO


IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'PS_CONTRACT_DETAILS')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('PS_CONTRACT_DETAILS')
                            AND NAME = 'UQ_PS_CONTRACT_DETAILS_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE_PPA_INDEX_NO')
        BEGIN
            ALTER TABLE PS_CONTRACT_DETAILS
              ADD CONSTRAINT UQ_PS_CONTRACT_DETAILS_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE_PPA_INDEX_NO 
			  UNIQUE(PS_CONTRACT_SID, ITEM_MASTER_SID, CONTRACT_PRICE_START_DATE,PPA_INDEX_NO)
        END
  END

GO
----------------------------------------------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'PS_CONTRACT_DETAILS'--TABLE NAME
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

----------------------------------------------HIST_PS_CONTRACT_DETAILS-----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_PS_CONTRACT_DETAILS]
        (
           PS_CONTRACT_DETAILS_SID     INT NOT NULL,
           PS_CONTRACT_SID             INT NOT NULL,
           ITEM_MASTER_SID             INT NOT NULL,
           CONTRACT_PRICE              NUMERIC(22, 6) NULL,
           ITEM_PRICING_QUALIFIER_SID  INT NOT NULL,
           CONTRACT_PRICE_START_DATE   DATETIME NOT NULL,
           CONTRACT_PRICE_END_DATE     DATETIME NULL,
           BASE_PRICE                  NUMERIC(22, 6) NULL,
           PRICE_TOLERANCE             NUMERIC(22, 6) NULL,
           PRICE_TOLERANCE_TYPE        INT NULL,
           PRICE_TOLERANCE_FREQUENCY   INT NULL,
           PRICE_TOLERANCE_INTERVAL    INT NULL,
           PRICE_PROTECTION_START_DATE DATETIME NULL,
           PRICE_PROTECTION_END_DATE   DATETIME NULL,
           PRICE_REVISION              NUMERIC(22, 6) NULL,
           REVISION_DATE               DATETIME NULL,
           SUGGESTED_PRICE             NUMERIC(22, 6) NULL,
           PRICE                       NUMERIC(22, 6) NOT NULL,
           PS_CONTRACT_ATTACHED_STATUS INT NULL,
           PS_CONTRACT_ATTACHED_DATE   DATETIME NULL,
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

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NEP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD NEP NUMERIC(22, 6)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD BASE_PRICE_TYPE VARCHAR(50)
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_ENTRY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD BASE_PRICE_ENTRY NUMERIC(22, 6)
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD BASE_PRICE_DATE DATETIME
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD NET_BASE_PRICE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'BASE_PRICE_DDLB'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD BASE_PRICE_DDLB INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_BASE_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD NET_BASE_PRICE_FORMULA_ID INT
  END

GO

------------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'SUBSEQUENT_PERIOD_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD SUBSEQUENT_PERIOD_PRICE_TYPE INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD NET_SUBSEQUENT_PERIOD_PRICE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_SUBSEQUENT_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD NET_SUBSEQUENT_PRICE_FORMULA_ID INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD RESET_PRICE_TYPE INT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD NET_RESET_PRICE_TYPE BIT
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_RESET_PRICE_FORMULA_ID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD NET_RESET_PRICE_FORMULA_ID INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_STATUS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD PRICE_PROTECTION_STATUS INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'PRICE_PROTECTION_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD PRICE_PROTECTION_PRICE_TYPE INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NEP_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD NEP_FORMULA VARCHAR(100)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'MAX_INCREMENTAL_CHANGE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD MAX_INCREMENTAL_CHANGE NUMERIC(22, 6)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_ELIGIBLE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD RESET_ELIGIBLE VARCHAR(100)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD RESET_TYPE INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_DATE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD RESET_DATE DATETIME
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_INTERVAL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD RESET_INTERVAL INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'RESET_FREQUENCY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD RESET_FREQUENCY INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD NET_PRICE_TYPE VARCHAR(100)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD NET_PRICE_TYPE_FORMULA VARCHAR(100)
  END

GO

--------------------- DROP STATISTICS --------------------------------
IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_INTERVAL'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_CONTRACT_DETAILS.PRICE_TOLERANCE_INTERVAL
  END

GO

-------------------COLUMN DATATYPE CHANGE STARTS--------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'PRICE_TOLERANCE_INTERVAL'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_INTERVAL INT
  END

GO

--------------------------------------------------------
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_BASE_PRICE'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_CONTRACT_DETAILS.NET_BASE_PRICE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_BASE_PRICE'
                  AND TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN NET_BASE_PRICE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_CONTRACT_DETAILS.NET_SUBSEQUENT_PERIOD_PRICE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_SUBSEQUENT_PERIOD_PRICE'
                  AND TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN NET_SUBSEQUENT_PERIOD_PRICE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'NET_RESET_PRICE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_CONTRACT_DETAILS.NET_RESET_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'NET_RESET_PRICE_TYPE'
                  AND TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN NET_RESET_PRICE_TYPE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'BASE_PRICE_ENTRY'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_CONTRACT_DETAILS.BASE_PRICE_ENTRY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'BASE_PRICE_ENTRY'
                  AND TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN BASE_PRICE_ENTRY NUMERIC(22, 6)
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_FREQUENCY'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_CONTRACT_DETAILS.PRICE_TOLERANCE_FREQUENCY
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'PRICE_TOLERANCE_FREQUENCY'
                  AND TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_FREQUENCY INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'PRICE_TOLERANCE_TYPE'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      DROP STATISTICS DBO.HIST_PS_CONTRACT_DETAILS.PRICE_TOLERANCE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'PRICE_TOLERANCE_TYPE'
                  AND TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN PRICE_TOLERANCE_TYPE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'BASE_PRICE_TYPE')
  BEGIN
      DROP STATISTICS HIST_PS_CONTRACT_DETAILS.BASE_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN BASE_PRICE_TYPE INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NEP_FORMULA')
  BEGIN
      DROP STATISTICS HIST_PS_CONTRACT_DETAILS.NEP_FORMULA
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NEP_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN NEP_FORMULA INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'RESET_ELIGIBLE')
  BEGIN
      DROP STATISTICS HIST_PS_CONTRACT_DETAILS.RESET_ELIGIBLE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'RESET_ELIGIBLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN RESET_ELIGIBLE INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NET_PRICE_TYPE')
  BEGIN
      DROP STATISTICS HIST_PS_CONTRACT_DETAILS.NET_PRICE_TYPE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NET_PRICE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN NET_PRICE_TYPE INT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NET_PRICE_TYPE_FORMULA')
  BEGIN
      DROP STATISTICS HIST_PS_CONTRACT_DETAILS.NET_PRICE_TYPE_FORMULA
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN NET_PRICE_TYPE_FORMULA INT NULL
  END

GO


IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'BASE_PRICE_TYPE')
  BEGIN
      DROP STATISTICS HIST_PS_CONTRACT_DETAILS.BASE_PRICE_TYPE
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'BASE_PRICE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN BASE_PRICE_TYPE INT NULL
  END
GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NEP_FORMULA')
  BEGIN
      DROP STATISTICS HIST_PS_CONTRACT_DETAILS.NEP_FORMULA
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NEP_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN NEP_FORMULA INT NULL
  END
GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'RESET_ELIGIBLE')
  BEGIN
      DROP STATISTICS HIST_PS_CONTRACT_DETAILS.RESET_ELIGIBLE
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'RESET_ELIGIBLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN RESET_ELIGIBLE INT NULL
  END
GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NET_PRICE_TYPE')
  BEGIN
      DROP STATISTICS HIST_PS_CONTRACT_DETAILS.NET_PRICE_TYPE
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NET_PRICE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN NET_PRICE_TYPE INT NULL
  END
GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_PS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'NET_PRICE_TYPE_FORMULA')
  BEGIN
      DROP STATISTICS HIST_PS_CONTRACT_DETAILS.NET_PRICE_TYPE_FORMULA
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'NET_PRICE_TYPE_FORMULA'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ALTER COLUMN NET_PRICE_TYPE_FORMULA INT NULL
  END
GO
-----------------------ALG-81
IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS'
			AND COLUMN_NAME = 'PPA_INDEX_NO'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
		ALTER TABLE HIST_PS_CONTRACT_DETAILS ADD PPA_INDEX_NO INT 	
END
GO
---------------ADDING COLUMN

IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'HIST_PS_CONTRACT_DETAILS' AND COLUMN_NAME  = 'ADD_COPY_INDICATOR' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE HIST_PS_CONTRACT_DETAILS
        ADD ADD_COPY_INDICATOR CHAR(1)
  END
GO

-------------------COLUMN DATATYPE CHANGE ENDS--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_PS_CONTRACT_DETAILS')
                      AND NAME = 'DF_HIST_PS_CONTRACT_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_PS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_HIST_PS_CONTRACT_DETAILS_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_PS_CONTRACT_DETAILS'--TABLE NAME
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

---------------------------------------------PS_CONTRACT_DETAILS TRIGGER------------------------------------
---------------------------TRGGER_PS_CONTRACT_DETAILS-----------------------------------------------------------
-----TRG_PS_CONTRACT_DETAILS_UPD---
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_PS_CONTRACT_DETAILS_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_CONTRACT_DETAILS_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_CONTRACT_DETAILS_UPD]
ON [DBO].[PS_CONTRACT_DETAILS]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_PS_CONTRACT_DETAILS
                    (PS_CONTRACT_DETAILS_SID,
                     PS_CONTRACT_SID,
                     ITEM_MASTER_SID,
                     CONTRACT_PRICE,
                     ITEM_PRICING_QUALIFIER_SID,
                     CONTRACT_PRICE_START_DATE,
                     CONTRACT_PRICE_END_DATE,
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
                     PS_CONTRACT_ATTACHED_STATUS,
                     PS_CONTRACT_ATTACHED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
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
                     SUBSEQUENT_PERIOD_PRICE_TYPE,
                     NET_SUBSEQUENT_PERIOD_PRICE,
                     NET_SUBSEQUENT_PRICE_FORMULA_ID,
                     RESET_PRICE_TYPE,
                     NET_RESET_PRICE_TYPE,
                     NET_RESET_PRICE_FORMULA_ID,
					 PPA_INDEX_NO,
					 ADD_COPY_INDICATOR,
                     ACTION_FLAG)
        SELECT PS_CONTRACT_DETAILS_SID,
               PS_CONTRACT_SID,
               ITEM_MASTER_SID,
               CONTRACT_PRICE,
               ITEM_PRICING_QUALIFIER_SID,
               CONTRACT_PRICE_START_DATE,
               CONTRACT_PRICE_END_DATE,
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
               PS_CONTRACT_ATTACHED_STATUS,
               PS_CONTRACT_ATTACHED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
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
               SUBSEQUENT_PERIOD_PRICE_TYPE,
               NET_SUBSEQUENT_PERIOD_PRICE,
               NET_SUBSEQUENT_PRICE_FORMULA_ID,
               RESET_PRICE_TYPE,
               NET_RESET_PRICE_TYPE,
               NET_RESET_PRICE_FORMULA_ID,
			   PPA_INDEX_NO,
			   ADD_COPY_INDICATOR,
               'C'
        FROM   INSERTED
  END

GO

-----TRG_PS_CONTRACT_DETAILS_INS---
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_PS_CONTRACT_DETAILS_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_CONTRACT_DETAILS_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_CONTRACT_DETAILS_INS]
ON [DBO].[PS_CONTRACT_DETAILS]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_PS_CONTRACT_DETAILS
                    (PS_CONTRACT_DETAILS_SID,
                     PS_CONTRACT_SID,
                     ITEM_MASTER_SID,
                     CONTRACT_PRICE,
                     ITEM_PRICING_QUALIFIER_SID,
                     CONTRACT_PRICE_START_DATE,
                     CONTRACT_PRICE_END_DATE,
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
                     PS_CONTRACT_ATTACHED_STATUS,
                     PS_CONTRACT_ATTACHED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
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
                     SUBSEQUENT_PERIOD_PRICE_TYPE,
                     NET_SUBSEQUENT_PERIOD_PRICE,
                     NET_SUBSEQUENT_PRICE_FORMULA_ID,
                     RESET_PRICE_TYPE,
                     NET_RESET_PRICE_TYPE,
                     NET_RESET_PRICE_FORMULA_ID,
					 PPA_INDEX_NO,
					 ADD_COPY_INDICATOR,
                     ACTION_FLAG)
        SELECT PS_CONTRACT_DETAILS_SID,
               PS_CONTRACT_SID,
               ITEM_MASTER_SID,
               CONTRACT_PRICE,
               ITEM_PRICING_QUALIFIER_SID,
               CONTRACT_PRICE_START_DATE,
               CONTRACT_PRICE_END_DATE,
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
               PS_CONTRACT_ATTACHED_STATUS,
               PS_CONTRACT_ATTACHED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
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
               SUBSEQUENT_PERIOD_PRICE_TYPE,
               NET_SUBSEQUENT_PERIOD_PRICE,
               NET_SUBSEQUENT_PRICE_FORMULA_ID,
               RESET_PRICE_TYPE,
               NET_RESET_PRICE_TYPE,
               NET_RESET_PRICE_FORMULA_ID,
			   PPA_INDEX_NO,
			   ADD_COPY_INDICATOR,
               'A'
        FROM   INSERTED
  END

GO

-----TRG_PS_CONTRACT_DETAILS_DEL---
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_PS_CONTRACT_DETAILS_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_PS_CONTRACT_DETAILS_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_PS_CONTRACT_DETAILS_DEL]
ON [DBO].[PS_CONTRACT_DETAILS]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_PS_CONTRACT_DETAILS
                    (PS_CONTRACT_DETAILS_SID,
                     PS_CONTRACT_SID,
                     ITEM_MASTER_SID,
                     CONTRACT_PRICE,
                     ITEM_PRICING_QUALIFIER_SID,
                     CONTRACT_PRICE_START_DATE,
                     CONTRACT_PRICE_END_DATE,
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
                     PS_CONTRACT_ATTACHED_STATUS,
                     PS_CONTRACT_ATTACHED_DATE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
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
                     SUBSEQUENT_PERIOD_PRICE_TYPE,
                     NET_SUBSEQUENT_PERIOD_PRICE,
                     NET_SUBSEQUENT_PRICE_FORMULA_ID,
                     RESET_PRICE_TYPE,
                     NET_RESET_PRICE_TYPE,
                     NET_RESET_PRICE_FORMULA_ID,
					 PPA_INDEX_NO,
					 ADD_COPY_INDICATOR,
                     ACTION_FLAG)
        SELECT PS_CONTRACT_DETAILS_SID,
               PS_CONTRACT_SID,
               ITEM_MASTER_SID,
               CONTRACT_PRICE,
               ITEM_PRICING_QUALIFIER_SID,
               CONTRACT_PRICE_START_DATE,
               CONTRACT_PRICE_END_DATE,
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
               PS_CONTRACT_ATTACHED_STATUS,
               PS_CONTRACT_ATTACHED_DATE,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
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
               SUBSEQUENT_PERIOD_PRICE_TYPE,
               NET_SUBSEQUENT_PERIOD_PRICE,
               NET_SUBSEQUENT_PRICE_FORMULA_ID,
               RESET_PRICE_TYPE,
               NET_RESET_PRICE_TYPE,
               NET_RESET_PRICE_FORMULA_ID,
			   PPA_INDEX_NO,
			   ADD_COPY_INDICATOR,
               'D'
        FROM   DELETED
  END

GO 

------------------------------------------PS_CONTRACT_DETAILS_PENDING_PENDING----------------------------------------
IF NOT EXISTS (SELECT 'X' 
               FROM   SYS.TABLES 
               WHERE  OBJECT_NAME(OBJECT_ID) = 'PS_CONTRACT_DETAILS_PENDING' 
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO') 
  BEGIN 
      CREATE TABLE [DBO].PS_CONTRACT_DETAILS_PENDING
        ( 
           PS_CONT_DET_PENDING_SID         INT NOT NULL IDENTITY(1, 1), 
           PS_CONTRACT_SID                 INT NOT NULL, 
           ITEM_MASTER_SID                 INT NOT NULL, 
           CONTRACT_PRICE                  NUMERIC(22,6), 
           ITEM_PRICING_QUALIFIER_SID      INT NOT NULL, 
           CONTRACT_PRICE_START_DATE       DATETIME NOT NULL, 
           CONTRACT_PRICE_END_DATE         DATETIME, 
           BASE_PRICE                       NUMERIC(22, 6), 
           PRICE_TOLERANCE                  NUMERIC(22, 6), 
           PRICE_TOLERANCE_TYPE            INT, 
           PRICE_TOLERANCE_FREQUENCY       INT, 
           PRICE_TOLERANCE_INTERVAL        INT, 
           PRICE_PROTECTION_START_DATE     DATETIME, 
           PRICE_PROTECTION_END_DATE       DATETIME, 
           PRICE_REVISION                   NUMERIC(22, 6), 
           REVISION_DATE                   DATETIME, 
           SUGGESTED_PRICE                 NUMERIC(22, 6), 
           PRICE                           NUMERIC(22, 6) NOT NULL, 
           PS_CONTRACT_ATTACHED_STATUS     INT, 
           PS_CONTRACT_ATTACHED_DATE       DATETIME, 
           INBOUND_STATUS                  CHAR(1) NOT NULL, 
           RECORD_LOCK_STATUS              BIT NOT NULL, 
           BATCH_ID                        VARCHAR(100), 
           SOURCE                          VARCHAR(100), 
           CREATED_BY                      INT NOT NULL, 
           CREATED_DATE                    DATETIME NOT NULL, 
           MODIFIED_BY                     INT NOT NULL, 
           MODIFIED_DATE                   DATETIME NOT NULL, 
           BRAND_MASTER_SID                INT, 
           BASE_PRICE_TYPE                 INT, 
           BASE_PRICE_ENTRY                 NUMERIC(22, 6), 
           BASE_PRICE_DATE                 DATETIME, 
           NET_BASE_PRICE                  INT, 
           BASE_PRICE_DDLB                 INT, 
           NET_BASE_PRICE_FORMULA_ID       INT, 
           SUBSEQUENT_PERIOD_PRICE_TYPE    INT, 
           NET_SUBSEQUENT_PERIOD_PRICE     INT, 
           NET_SUBSEQUENT_PRICE_FORMULA_ID INT, 
           RESET_PRICE_TYPE                INT, 
           NET_RESET_PRICE_TYPE            INT, 
           NET_RESET_PRICE_FORMULA_ID      INT, 
           NEP                              NUMERIC(22, 6), 
           PRICE_PROTECTION_STATUS         INT, 
           PRICE_PROTECTION_PRICE_TYPE     INT, 
           NEP_FORMULA                     INT, 
           MAX_INCREMENTAL_CHANGE           NUMERIC(22, 6), 
           RESET_ELIGIBLE                  INT, 
           RESET_TYPE                      INT, 
           RESET_DATE                       NUMERIC(22, 6), 
           RESET_INTERVAL                  INT, 
           RESET_FREQUENCY                 INT, 
           NET_PRICE_TYPE                  INT, 
           NET_PRICE_TYPE_FORMULA          INT, 
           CHECK_RECORD                    BIT NOT NULL ,
		   PPA_INDEX_NO						INT NOT NULL
        ) 
  END 

GO 		
---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'PS_CONTRACT_DETAILS_PENDING'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_PS_CONTRACT_DETAILS_PENDING_PS_CONT_DET_PENDING_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [DBO].PS_CONTRACT_DETAILS_PENDING
    ADD CONSTRAINT PK_PS_CONTRACT_DETAILS_PENDING_PS_CONT_DET_PENDING_SID PRIMARY KEY(PS_CONT_DET_PENDING_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS_PENDING')
                      AND NAME = 'DF_PS_CONTRACT_DETAILS_PENDING_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT_DETAILS_PENDING]
        ADD CONSTRAINT [DF_PS_CONTRACT_DETAILS_PENDING_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS_PENDING')
                      AND NAME = 'DF_PS_CONTRACT_DETAILS_PENDING_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT_DETAILS_PENDING]
        ADD CONSTRAINT [DF_PS_CONTRACT_DETAILS_PENDING_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS_PENDING')
                      AND NAME = 'DF_PS_CONTRACT_DETAILS_PENDING_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT_DETAILS_PENDING]
        ADD CONSTRAINT [DF_PS_CONTRACT_DETAILS_PENDING_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS_PENDING')
                      AND NAME = 'DF_PS_CONTRACT_DETAILS_PENDING_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT_DETAILS_PENDING]
        ADD CONSTRAINT [DF_PS_CONTRACT_DETAILS_PENDING_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS_PENDING')
                      AND NAME = 'DF_PS_CONTRACT_DETAILS_PENDING_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT_DETAILS_PENDING]
        ADD CONSTRAINT [DF_PS_CONTRACT_DETAILS_PENDING_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.PS_CONTRACT_DETAILS_PENDING')
                      AND NAME = 'DF_PS_CONTRACT_DETAILS_PENDING_CHECK_RECORD')
  BEGIN
      ALTER TABLE [DBO].[PS_CONTRACT_DETAILS_PENDING]
        ADD CONSTRAINT DF_PS_CONTRACT_DETAILS_PENDING_CHECK_RECORD DEFAULT (0) FOR CHECK_RECORD
  END

GO
-----------------------ADDING COLUMN 
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PS_CONTRACT_DETAILS_PENDING' AND COLUMN_NAME  = 'ADD_COPY_INDICATOR' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PS_CONTRACT_DETAILS_PENDING
        ADD ADD_COPY_INDICATOR CHAR(1)
  END
GO


-----------------------ALG-2968-----------
IF EXISTS (SELECT 1
           FROM   SYS.stats
           WHERE  NAME = 'RESET_DATE'
                  AND Object_name(object_id) = 'PS_CONTRACT_DETAILS_PENDING')
  BEGIN
      DROP STATISTICS PS_CONTRACT_DETAILS_PENDING.RESET_DATE
  END 

GO


IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PS_CONTRACT_DETAILS_PENDING' 
	  AND COLUMN_NAME  = 'RESET_DATE' 
	  AND TABLE_SCHEMA = 'DBO'  AND 
	  DATA_TYPE='NUMERIC')
 BEGIN     
      ALTER TABLE PS_CONTRACT_DETAILS_PENDING
        ALTER COLUMN RESET_DATE DATETIME
  END
GO  

 

------------------------------------------UNIQUE_CONSTRAINT--------------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'PS_CONTRACT_DETAILS_PENDING')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('PS_CONTRACT_DETAILS_PENDING')
                            AND NAME = 'UQ_PS_CONTRACT_DETAILS_PENDING_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE_PPA_INDEX_NO')
        BEGIN
            ALTER TABLE PS_CONTRACT_DETAILS_PENDING
              ADD CONSTRAINT UQ_PS_CONTRACT_DETAILS_PENDING_PS_CONTRACT_SID_ITEM_MASTER_SID_CONTRACT_PRICE_START_DATE_PPA_INDEX_NO UNIQUE(PS_CONTRACT_SID, ITEM_MASTER_SID, CONTRACT_PRICE_START_DATE,PPA_INDEX_NO)
        END
  END

GO
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'PS_CONTRACT_DETAILS_PENDING'--TABLE NAME
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