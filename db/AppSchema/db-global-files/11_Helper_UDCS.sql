IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HELPER_TABLE'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HELPER_TABLE]
        (
           [HELPER_TABLE_SID] INT IDENTITY(1, 1) NOT NULL,
           [DESCRIPTION]      VARCHAR(50) NULL,
           [LIST_NAME]        VARCHAR(50) NULL,
           [REF_COUNT]        NUMERIC(38, 0) NULL,
           [CREATED_BY]       INT NOT NULL,
           [CREATED_DATE]     DATETIME NOT NULL,
           [MODIFIED_BY]      INT NOT NULL,
           [MODIFIED_DATE]    DATETIME NOT NULL          
        )
  END

GO
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_HELPER_TABLE_HELPER_TABLE_SID'
                 AND TABLE_NAME = 'HELPER_TABLE')
  ALTER TABLE HELPER_TABLE
    ADD CONSTRAINT PK_HELPER_TABLE_HELPER_TABLE_SID PRIMARY KEY(HELPER_TABLE_SID)


GO


IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.HELPER_TABLE')
                  AND NAME = 'DF_HELPER_TABLE_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[HELPER_TABLE]
        ADD CONSTRAINT [DF_HELPER_TABLE_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.HELPER_TABLE')
                  AND NAME = 'DF_HELPER_TABLE_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[HELPER_TABLE]
        ADD CONSTRAINT [DF_HELPER_TABLE_CREATED_DATE] DEFAULT (GetDate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.HELPER_TABLE')
                  AND NAME = 'DF_HELPER_TABLE_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[HELPER_TABLE]
        ADD CONSTRAINT [DF_HELPER_TABLE_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_Id('DBO.HELPER_TABLE')
                  AND NAME = 'DF_HELPER_TABLE_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[HELPER_TABLE]
        ADD CONSTRAINT [DF_HELPER_TABLE_MODIFIED_DATE] DEFAULT (GetDate()) FOR MODIFIED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HELPER_TABLE'--TABLE NAME----
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

--------------------------------------HIST_HELPER_TABLE------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_HELPER_TABLE'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_HELPER_TABLE]
        (
           [HELPER_TABLE_SID] INT NOT NULL,
           [DESCRIPTION]      VARCHAR(50) NULL,
           [LIST_NAME]        VARCHAR(50) NULL,
           [REF_COUNT]        NUMERIC(38, 0) NULL,
           [CREATED_BY]       INT NOT NULL,
           [CREATED_DATE]     DATETIME NOT NULL,
           [MODIFIED_BY]      INT NOT NULL,
           [MODIFIED_DATE]    DATETIME NOT NULL,          
           [ACTION_FLAG]      CHAR(1) NOT NULL,
           [ACTION_DATE]      DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.HIST_HELPER_TABLE')
                  AND NAME = 'DF_HIST_HELPER_TABLE_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_HELPER_TABLE]
        ADD CONSTRAINT [DF_HIST_HELPER_TABLE_ACTION_DATE] DEFAULT (GetDate()) FOR ACTION_DATE
  END

GO

---------------------------------UNIQUE_CONSTRAINT---------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'HELPER_TABLE')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                        AND PARENT_OBJECT_ID = Object_Id('HELPER_TABLE')
                        AND NAME = 'UQ_HELPER_TABLE_DESCRIPTION_LIST_NAME')
        BEGIN
            ALTER TABLE HELPER_TABLE
              ADD CONSTRAINT UQ_HELPER_TABLE_DESCRIPTION_LIST_NAME UNIQUE(DESCRIPTION, LIST_NAME)
        END
  END

GO 


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_HELPER_TABLE'--TABLE NAME
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

--------------------------HELPER_TABLE TRIGGER----------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HELPER_TABLE_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_HELPER_TABLE_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_HELPER_TABLE_UPD]
ON [DBO].HELPER_TABLE
AFTER UPDATE
AS
  BEGIN

      INSERT INTO HIST_HELPER_TABLE
                  ([HELPER_TABLE_SID],
                   [DESCRIPTION],
                   [LIST_NAME],
                   [REF_COUNT],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [ACTION_FLAG])
      SELECT [HELPER_TABLE_SID],
             [DESCRIPTION],
             [LIST_NAME],
             [REF_COUNT],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             'C'
      FROM   INSERTED I
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HELPER_TABLE_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_HELPER_TABLE_INS]
  END

GO

CREATE TRIGGER [DBO].[TRG_HELPER_TABLE_INS]
ON [DBO].HELPER_TABLE
AFTER INSERT
AS
  BEGIN
SET NOCOUNT ON
      INSERT INTO HIST_HELPER_TABLE
                  ([HELPER_TABLE_SID],
                   [DESCRIPTION],
                   [LIST_NAME],
                   [REF_COUNT],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [ACTION_FLAG])
      SELECT [HELPER_TABLE_SID],
             [DESCRIPTION],
             [LIST_NAME],
             [REF_COUNT],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             'A'
      FROM   INSERTED I
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_HELPER_TABLE_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_HELPER_TABLE_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_HELPER_TABLE_DEL]
ON [DBO].HELPER_TABLE
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_HELPER_TABLE
                  ([HELPER_TABLE_SID],
                   [DESCRIPTION],
                   [LIST_NAME],
                   [REF_COUNT],
                   [CREATED_BY],
                   [CREATED_DATE],
                   [MODIFIED_BY],
                   [MODIFIED_DATE],
                   [ACTION_FLAG])
      SELECT [HELPER_TABLE_SID],
             [DESCRIPTION],
             [LIST_NAME],
             [REF_COUNT],
             [CREATED_BY],
             [CREATED_DATE],
             [MODIFIED_BY],
             [MODIFIED_DATE],
             'D'
      FROM   DELETED I
  END

GO 


------------------------------UDCS--------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'UDCS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[UDCS]
        (
           UDCS_SID    INT IDENTITY(1, 1)NOT NULL,
           MASTER_SID  INT NOT NULL,
           MASTER_TYPE VARCHAR(50) NOT NULL,
           UDC1        INT NULL,
           UDC2        INT NULL,
           UDC3        INT NULL,
           UDC4        INT NULL,
           UDC5        INT NULL,
           UDC6        INT NULL,
           UDC7        INT NULL,
           UDC8        INT NULL,
           UDC9        INT NULL,
           UDC10       INT NULL,
           UDC11       INT NULL,
           UDC12       INT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_UDCS_UDCS_SID'
                     AND TABLE_NAME = 'UDCS')
BEGIN
  ALTER TABLE UDCS
    ADD CONSTRAINT PK_UDCS_UDCS_SID PRIMARY KEY(UDCS_SID)
END

GO

--------------UNIQUE KEYS--------------------------
IF NOT EXISTS (SELECT *
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'UDCS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'UQ_UDCS_MASTER_SID_MASTER_TYPE'
                      AND TYPE = 'UQ')
  BEGIN
      ALTER TABLE UDCS
        ADD CONSTRAINT UQ_UDCS_MASTER_SID_MASTER_TYPE UNIQUE (MASTER_SID, MASTER_TYPE)
  END

GO 

DECLARE @RX VARCHAR(100) 

BEGIN 
    SELECT TOP 1 @RX = COMPANY_MASTER_SID 
    FROM   COMPANY_MASTER A JOIN HELPER_TABLE H ON A.COMPANY_TYPE=H.HELPER_TABLE_SID
    WHERE  (COMPANY_NAME LIKE '%RX%US%' OR COMPANY_NAME LIKE '%US%RX') AND H.DESCRIPTION='BUSINESS UNIT' AND H.LIST_NAME='COMPANY_TYPE'

    IF @RX IS NOT NULL 
      BEGIN 
          UPDATE ITEM_MASTER 
          SET    ORGANIZATION_KEY = @RX 
          WHERE  ORGANIZATION_KEY IS NULL 
      END 
END 

GO 

---------------------------------- HELPER TABLE INSERT SCRIPTS -----------------------------------
SET IDENTITY_INSERT HELPER_TABLE ON

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '-Select One-'
                      AND LIST_NAME = 'ALL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (HELPER_TABLE_SID,
                   DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      (0,'-Select One-','ALL',0 )
  END
  GO


SET IDENTITY_INSERT HELPER_TABLE OFF

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ADJUSTMENT_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ADJUSTMENT_TYPE','CategoryName',0 )
  END
  GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ALLOCATION_METHOD'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ALLOCATION_METHOD','CategoryName',0 )
  END
  GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'BASE_PRICE_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'BASE_PRICE_TYPE','CategoryName',0 )
  END
  GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'BASELINE_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'BASELINE_TYPE','CategoryName',0 )
  END
  GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'BUSINESS_PROCESS_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'BUSINESS_PROCESS_TYPE','CategoryName',0 )
  END
  GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CALCULATION_LEVEL'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CALCULATION_LEVEL','CategoryName',0 )
  END
  GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CALCULATION_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CALCULATION_TYPE','CategoryName',0 )
  END
  GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CFF_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CFF_TYPE','CategoryName',0 )
  END
  GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CFP_CATEGORY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CFP_CATEGORY','CategoryName',0 )
  END
  GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CFP_DESIGNATION'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CFP_DESIGNATION','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CFP_TRADE_CLASS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CFP_TRADE_CLASS','CategoryName',0 )
  END
GO
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CFP_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CFP_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COMP_UDC1'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COMP_UDC1','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COMP_UDC2'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COMP_UDC2','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COMP_UDC3'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COMP_UDC3','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COMP_UDC4'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COMP_UDC4','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COMP_UDC5'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COMP_UDC5','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COMP_UDC6'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COMP_UDC6','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COMPANY_CATEGORY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COMPANY_CATEGORY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COMPANY_GROUP'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COMPANY_GROUP','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COMPANY_TRADE_CLASS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COMPANY_TRADE_CLASS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COMPANY_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COMPANY_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COMPARISON'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COMPARISON','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CONTRACT_ALIAS_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CONTRACT_ALIAS_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CONTRACT_TRADE_CLASS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CONTRACT_TRADE_CLASS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CONTRACT_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CONTRACT_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'COUNTRY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'COUNTRY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'DEDUCTION_CALENDAR_CATEGORY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'DEDUCTION_CALENDAR_CATEGORY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'DOCUMENT_CLASS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'DOCUMENT_CLASS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'DOCUMENT_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'DOCUMENT_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'EVALUATION_RULE_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'EVALUATION_RULE_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'FILE_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'FILE_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'FORECAST_FREQUENCY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'FORECAST_FREQUENCY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'FORECAST_SALES_BASIS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'FORECAST_SALES_BASIS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'FORECAST_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'FORECAST_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'FORM'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'FORM','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'FORMULA_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'FORMULA_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'HIERARCHY_CATEGORY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'HIERARCHY_CATEGORY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'HIERARCHY_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'HIERARCHY_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'IFP_CATEGORY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'IFP_CATEGORY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'IFP_DESIGNATION'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'IFP_DESIGNATION','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'IFP_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'IFP_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'INTEREST_BEARING_BASIS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'INTEREST_BEARING_BASIS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'INTEREST_BEARING_INDICATOR'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'INTEREST_BEARING_INDICATOR','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ITEM_CATEGORY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ITEM_CATEGORY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ITEM_CLASS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ITEM_CLASS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ITEM_GROUP_MS_ASSOCIATION'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ITEM_GROUP_MS_ASSOCIATION','CategoryName',0 )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ITEM_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ITEM_TYPE','CategoryName',0 )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ITEM_TYPE_INDICATION'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ITEM_TYPE_INDICATION','CategoryName',0 )
  END

GO
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ITEM_UDC1'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ITEM_UDC1','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ITEM_UDC2'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ITEM_UDC2','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ITEM_UDC3'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ITEM_UDC3','CategoryName',0 )
  END
 GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ITEM_UDC4'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ITEM_UDC4','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ITEM_UDC5'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ITEM_UDC5','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ITEM_UDC6'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ITEM_UDC6','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'KEYWORD'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'KEYWORD','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'LEVEL'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'LEVEL','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'LINE_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'LINE_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'LOCKED_STATUS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'LOCKED_STATUS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'LOGICAL_OPERATOR'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'LOGICAL_OPERATOR','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NS_FORMULA_CATEGORY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NS_FORMULA_CATEGORY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NS_FORMULA_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NS_FORMULA_TYPE','CategoryName',0 )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'OPERATOR'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'OPERATOR','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ORGANIZATION_KEY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ORGANIZATION_KEY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PACKAGE_SIZE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PACKAGE_SIZE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PAYMENT_FREQUENCY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PAYMENT_FREQUENCY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PAYMENT_LEVEL'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PAYMENT_LEVEL','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PAYMENT_METHOD'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PAYMENT_METHOD','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PAYMENT_TERMS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PAYMENT_TERMS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PERIODS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PERIODS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PRICE_MASS_FIELD'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PRICE_MASS_FIELD','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PRICE_RESET_INDICATOR'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PRICE_RESET_INDICATOR','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PRICE_TOLERANCE_FREQUENCY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PRICE_TOLERANCE_FREQUENCY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PRICE_TOLERANCE_INTERVAL'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PRICE_TOLERANCE_INTERVAL','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PRICE_TOLERANCE_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PRICE_TOLERANCE_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PRICING_SCENERIOS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PRICING_SCENERIOS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PROCESS_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PROCESS_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PROFILE_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PROFILE_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PS_CATEGORY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PS_CATEGORY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PS_DESIGNATION'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PS_DESIGNATION','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PS_TRADE_CLASS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PS_TRADE_CLASS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PS_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PS_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RAR_ACCOUNT'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RAR_ACCOUNT','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REASON_CODES'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REASON_CODES','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REBATE_BASED_ON'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REBATE_BASED_ON','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REBATE_FREQUENCY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REBATE_FREQUENCY','CategoryName',0 )
  END
  GO
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REBATE_PLAN_LEVEL'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REBATE_PLAN_LEVEL','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REBATE_PLAN_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REBATE_PLAN_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REBATE_PROCESSING_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REBATE_PROCESSING_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REBATE_PROGRAM_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REBATE_PROGRAM_TYPE','CategoryName',0 )
  END

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REBATE_RANGE_BASED_ON'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REBATE_RANGE_BASED_ON','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REBATE_RULE_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REBATE_RULE_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REBATE_STRUCTURE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REBATE_STRUCTURE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RELATIONSHIP_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RELATIONSHIP_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REPORT_NAME'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REPORT_NAME','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'REPORT_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'REPORT_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RESET_TYPE'
                      AND LIST_NAME = 'Categoryname')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RESET_TYPE','Categoryname',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ROUNDING'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ROUNDING','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_CALENDAR'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_CALENDAR','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_CATEGORY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_CATEGORY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_DESIGNATION'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_DESIGNATION','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_PROCESSING_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_PROCESSING_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_TRADE_CLASS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_TRADE_CLASS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_UDC1'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_UDC1','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_UDC2'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_UDC2','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_UDC3'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_UDC3','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_UDC4'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_UDC4','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_UDC5'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_UDC5','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_UDC6'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_UDC6','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RS_VALIDATION_PROFILE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RS_VALIDATION_PROFILE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RULE_CATEGORY'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RULE_CATEGORY','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RULE_LEVEL'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RULE_LEVEL','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RULE_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RULE_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'SALES_BASIS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'SALES_BASIS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'SHELF_LIFE_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'SHELF_LIFE_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'STATE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'STATE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'STATUS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'STATUS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'STRENGTH'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'STRENGTH','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'THERAPEUTIC_CLASS'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'THERAPEUTIC_CLASS','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'TIER_OPERATOR'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'TIER_OPERATOR','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'UNIT_ACCOUNT'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'UNIT_ACCOUNT','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'UOM'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'UOM','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Validation Profile'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Validation Profile','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'WorkFlowStatus'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'WorkFlowStatus','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Active'
                      AND LIST_NAME = 'STATUS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Active','STATUS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Discontinued'
                      AND LIST_NAME = 'STATUS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Discontinued','STATUS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Divested'
                      AND LIST_NAME = 'STATUS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Divested','STATUS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Obsolete'
                      AND LIST_NAME = 'STATUS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Obsolete','STATUS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Pre-Release'
                      AND LIST_NAME = 'STATUS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Pre-Release','STATUS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Yes'
                      AND LIST_NAME = 'STATUS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Yes','STATUS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Inactive'
                      AND LIST_NAME = 'STATUS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Inactive','STATUS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'UDC 1'
                      AND LIST_NAME = 'COMP_UDC1')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'UDC 1','COMP_UDC1','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'UDC 2'
                      AND LIST_NAME = 'COMP_UDC2')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'UDC 2','COMP_UDC2','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'UDC 3'
                      AND LIST_NAME = 'COMP_UDC3')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'UDC 3','COMP_UDC3','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'UDC 4'
                      AND LIST_NAME = 'COMP_UDC4')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'UDC 4','COMP_UDC4','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'UDC 5'
                      AND LIST_NAME = 'COMP_UDC5')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'UDC 5','COMP_UDC5','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'UDC 6'
                      AND LIST_NAME = 'COMP_UDC6')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'UDC 6','COMP_UDC6','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Child'
                      AND LIST_NAME = 'CFP_DESIGNATION')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Child','CFP_DESIGNATION','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Parent'
                      AND LIST_NAME = 'CFP_DESIGNATION')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Parent','CFP_DESIGNATION','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Child'
                      AND LIST_NAME = 'IFP_DESIGNATION')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Child','IFP_DESIGNATION','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Parent'
                      AND LIST_NAME = 'IFP_DESIGNATION')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Parent','IFP_DESIGNATION','0' )
  END
GO


IF EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = ' Manual'
                      AND LIST_NAME = 'BASE_PRICE_TYPE')
  BEGIN
      UPDATE HELPER_TABLE SET DESCRIPTION = 'Manual'
               WHERE  DESCRIPTION = ' Manual'
                      AND LIST_NAME = 'BASE_PRICE_TYPE'
  END
GO
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Manual'
                      AND LIST_NAME = 'BASE_PRICE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Manual','BASE_PRICE_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Date'
                      AND LIST_NAME = 'BASE_PRICE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Date','BASE_PRICE_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Price Type'
                      AND LIST_NAME = 'BASE_PRICE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Price Type','BASE_PRICE_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '1'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '1','PRICE_TOLERANCE_INTERVAL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '2'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '2','PRICE_TOLERANCE_INTERVAL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '3'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '3','PRICE_TOLERANCE_INTERVAL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '4'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '4','PRICE_TOLERANCE_INTERVAL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '5'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '5','PRICE_TOLERANCE_INTERVAL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '6'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '6','PRICE_TOLERANCE_INTERVAL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '7'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '7','PRICE_TOLERANCE_INTERVAL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '8'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '8','PRICE_TOLERANCE_INTERVAL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '9'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '9','PRICE_TOLERANCE_INTERVAL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '10'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '10','PRICE_TOLERANCE_INTERVAL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '11'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '11','PRICE_TOLERANCE_INTERVAL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '12'
                      AND LIST_NAME = 'PRICE_TOLERANCE_INTERVAL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '12','PRICE_TOLERANCE_INTERVAL','0' )
  END
  GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Monthly'
                      AND LIST_NAME = 'PRICE_TOLERANCE_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Monthly','PRICE_TOLERANCE_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Quarterly'
                      AND LIST_NAME = 'PRICE_TOLERANCE_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Quarterly','PRICE_TOLERANCE_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Semi-Annual'
                      AND LIST_NAME = 'PRICE_TOLERANCE_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Semi-Annual','PRICE_TOLERANCE_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Annual'
                      AND LIST_NAME = 'PRICE_TOLERANCE_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Annual','PRICE_TOLERANCE_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'percentage'
                      AND LIST_NAME = 'PRICE_TOLERANCE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'percentage','PRICE_TOLERANCE_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'dollar'
                      AND LIST_NAME = 'PRICE_TOLERANCE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'dollar','PRICE_TOLERANCE_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Effective Date'
                      AND LIST_NAME = 'RESET_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Effective Date','RESET_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Violation Date'
                      AND LIST_NAME = 'RESET_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Violation Date','RESET_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Interval Frequency'
                      AND LIST_NAME = 'RESET_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Interval Frequency','RESET_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Managed Care'
                      AND LIST_NAME = 'REBATE_PLAN_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Managed Care','REBATE_PLAN_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Medicaid'
                      AND LIST_NAME = 'REBATE_PLAN_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Medicaid','REBATE_PLAN_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Service Fees'
                      AND LIST_NAME = 'REBATE_PLAN_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Service Fees','REBATE_PLAN_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Flat'
                      AND LIST_NAME = 'REBATE_STRUCTURE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Flat','REBATE_STRUCTURE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Level'
                      AND LIST_NAME = 'REBATE_STRUCTURE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Level','REBATE_STRUCTURE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Tier'
                      AND LIST_NAME = 'REBATE_STRUCTURE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Tier','REBATE_STRUCTURE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Dollars'
                      AND LIST_NAME = 'REBATE_RANGE_BASED_ON')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Dollars','REBATE_RANGE_BASED_ON','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Units'
                      AND LIST_NAME = 'REBATE_RANGE_BASED_ON')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Units','REBATE_RANGE_BASED_ON','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Gross Contract Sales'
                      AND LIST_NAME = 'REBATE_BASED_ON')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Gross Contract Sales','REBATE_BASED_ON','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Gross Contract Units'
                      AND LIST_NAME = 'REBATE_BASED_ON')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Gross Contract Units','REBATE_BASED_ON','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Net Contract Sales'
                      AND LIST_NAME = 'REBATE_BASED_ON')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Net Contract Sales','REBATE_BASED_ON','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Net Contract Units'
                      AND LIST_NAME = 'REBATE_BASED_ON')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Net Contract Units','REBATE_BASED_ON','0' )
  END
GO

  IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Contract CMS Units'
                      AND LIST_NAME = 'REBATE_BASED_ON')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Contract CMS Units','REBATE_BASED_ON','0' )
  END
GO
  
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '$'
                      AND LIST_NAME = 'TIER_OPERATOR')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '$','TIER_OPERATOR','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '%'
                      AND LIST_NAME = 'TIER_OPERATOR')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '%','TIER_OPERATOR','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Annually'
                      AND LIST_NAME = 'PAYMENT_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Annually','PAYMENT_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Monthly'
                      AND LIST_NAME = 'PAYMENT_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Monthly','PAYMENT_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Quarterly'
                      AND LIST_NAME = 'PAYMENT_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Quarterly','PAYMENT_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Semi-Annually'
                      AND LIST_NAME = 'PAYMENT_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Semi-Annually','PAYMENT_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Check'
                      AND LIST_NAME = 'PAYMENT_METHOD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Check','PAYMENT_METHOD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Credit Memo'
                      AND LIST_NAME = 'PAYMENT_METHOD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Credit Memo','PAYMENT_METHOD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Credit Order'
                      AND LIST_NAME = 'PAYMENT_METHOD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Credit Order','PAYMENT_METHOD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Deduction Calendar'
                      AND LIST_NAME = 'CALCULATION_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Deduction Calendar','CALCULATION_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Formula'
                      AND LIST_NAME = 'CALCULATION_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Formula','CALCULATION_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Rebate Plan'
                      AND LIST_NAME = 'CALCULATION_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Rebate Plan','CALCULATION_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Single'
                      AND LIST_NAME = 'CALCULATION_LEVEL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Single','CALCULATION_LEVEL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Line Level'
                      AND LIST_NAME = 'CALCULATION_LEVEL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Line Level','CALCULATION_LEVEL','0' )
  END
GO

  IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Bundle'
                      AND LIST_NAME = 'CALCULATION_LEVEL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Bundle','CALCULATION_LEVEL','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Standard Validation'
                      AND LIST_NAME = 'RS_VALIDATION_PROFILE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Standard Validation','RS_VALIDATION_PROFILE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Net Sales'
                      AND LIST_NAME = 'RULE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Net Sales','RULE_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Evaluation'
                      AND LIST_NAME = 'RULE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Evaluation','RULE_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Calculation'
                      AND LIST_NAME = 'RULE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Calculation','RULE_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Item'
                      AND LIST_NAME = 'LINE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Item','LINE_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Item'
                      AND LIST_NAME = 'ITEM_GROUP_MS_ASSOCIATION')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Item','ITEM_GROUP_MS_ASSOCIATION','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Sales'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Sales','KEYWORD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Units'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Units','KEYWORD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Deduction'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Deduction','KEYWORD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '<'
                      AND LIST_NAME = 'OPERATOR')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '<','OPERATOR','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '<='
                      AND LIST_NAME = 'OPERATOR')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '<=','OPERATOR','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '='
                      AND LIST_NAME = 'OPERATOR')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '=','OPERATOR','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '>'
                      AND LIST_NAME = 'OPERATOR')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '>','OPERATOR','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '>='
                      AND LIST_NAME = 'OPERATOR')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( '>=','OPERATOR','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Gross Trade Sales'
                      AND LIST_NAME = 'COMPARISON')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Gross Trade Sales','COMPARISON','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Contract Sales'
                      AND LIST_NAME = 'COMPARISON')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Contract Sales','COMPARISON','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Demand'
                      AND LIST_NAME = 'COMPARISON')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Demand','COMPARISON','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Inventory Withdrawals'
                      AND LIST_NAME = 'COMPARISON')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Inventory Withdrawals','COMPARISON','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'AND'
                      AND LIST_NAME = 'LOGICAL_OPERATOR')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'AND','LOGICAL_OPERATOR','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'OR'
                      AND LIST_NAME = 'LOGICAL_OPERATOR')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'OR','LOGICAL_OPERATOR','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Contract'
                      AND LIST_NAME = 'NS_FORMULA_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Contract','NS_FORMULA_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Deduction Type'
                      AND LIST_NAME = 'NS_FORMULA_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Deduction Type','NS_FORMULA_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Contract Deduction'
                      AND LIST_NAME = 'NS_FORMULA_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Contract Deduction','NS_FORMULA_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Alliance'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Alliance','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Direct'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Direct','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'FFS'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'FFS','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'HMO'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'HMO','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Mc Commercial'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Mc Commercial','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Medicare'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Medicare','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'MM'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'MM','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PBM'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PBM','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Phs'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Phs','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PPO'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PPO','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Pracute'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Pracute','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Prases'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Prases','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Retail'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Retail','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Royalty'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Royalty','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Tricare'
                      AND LIST_NAME = 'CONTRACT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Tricare','CONTRACT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NDC 8'
                      AND LIST_NAME = 'ITEM_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NDC 8','ITEM_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NDC 9'
                      AND LIST_NAME = 'ITEM_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NDC 9','ITEM_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NDC 10'
                      AND LIST_NAME = 'ITEM_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NDC 10','ITEM_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NDC 11'
                      AND LIST_NAME = 'ITEM_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NDC 11','ITEM_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Monthly'
                      AND LIST_NAME = 'FORECAST_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Monthly','FORECAST_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Quarterly'
                      AND LIST_NAME = 'FORECAST_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Quarterly','FORECAST_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Semi-Annually'
                      AND LIST_NAME = 'FORECAST_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Semi-Annually','FORECAST_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Annually'
                      AND LIST_NAME = 'FORECAST_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Annually','FORECAST_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Non Mandated'
                      AND LIST_NAME = 'BUSINESS_PROCESS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Non Mandated','BUSINESS_PROCESS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Mandated'
                      AND LIST_NAME = 'BUSINESS_PROCESS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Mandated','BUSINESS_PROCESS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Channel'
                      AND LIST_NAME = 'BUSINESS_PROCESS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Channel','BUSINESS_PROCESS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Accounting Close'
                      AND LIST_NAME = 'BUSINESS_PROCESS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Accounting Close','BUSINESS_PROCESS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Pricing'
                      AND LIST_NAME = 'BUSINESS_PROCESS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Pricing','BUSINESS_PROCESS','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Manual'
                      AND LIST_NAME = 'PROCESS_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Manual','PROCESS_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Automatic'
                      AND LIST_NAME = 'PROCESS_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Automatic','PROCESS_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Both'
                      AND LIST_NAME = 'PROCESS_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Both','PROCESS_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Latest Estimate'
                      AND LIST_NAME = 'CFF_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Latest Estimate','CFF_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Update Cycle'
                      AND LIST_NAME = 'CFF_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Update Cycle','CFF_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Both'
                      AND LIST_NAME = 'CFF_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Both','CFF_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Approved'
                      AND LIST_NAME = 'WorkFlowStatus')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Approved','WorkFlowStatus','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Canceled '
                      AND LIST_NAME = 'WorkFlowStatus')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Canceled ','WorkFlowStatus','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Pending'
                      AND LIST_NAME = 'WorkFlowStatus')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Pending','WorkFlowStatus','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Rejected'
                      AND LIST_NAME = 'WorkFlowStatus')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Rejected','WorkFlowStatus','0' )
  END
GO
-----------GAL-9440 Missed entry
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Withdrawn'
                      AND LIST_NAME = 'WorkFlowStatus')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Withdrawn','WorkFlowStatus','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Withdrawn'
                      AND LIST_NAME = 'WorkFlowStatus')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Withdrawn','WorkFlowStatus','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'AL'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'AL','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'AK'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'AK','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'AZ'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'AZ','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'AR'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'AR','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CA'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CA','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CO'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CO','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'CT'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'CT','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'DE'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'DE','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'FL'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'FL','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'GA'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'GA','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'HI'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'HI','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ID'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ID','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'IL'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'IL','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'IN'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'IN','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'IA'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'IA','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'KS'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'KS','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'KY'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'KY','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'LA'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'LA','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ME'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ME','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'MD'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'MD','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'MA'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'MA','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'MI'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'MI','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'MN'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'MN','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'MS'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'MS','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'MO'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'MO','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'MT'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'MT','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NE'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NE','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NV'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NV','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NH'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NH','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NJ'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NJ','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NM'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NM','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NY'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NY','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NC'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NC','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ND'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ND','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'OH'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'OH','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'OK'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'OK','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'OR'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'OR','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PA'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PA','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'RI'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'RI','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'SC'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'SC','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'SD'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'SD','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'TN'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'TN','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'TX'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'TX','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'UT'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'UT','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'VT'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'VT','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'VA'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'VA','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'WA'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'WA','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'WV'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'WV','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'WI'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'WI','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'WY'
                      AND LIST_NAME = 'STATE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'WY','STATE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Supplemental'
                      AND LIST_NAME = 'FORMULA_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Supplemental','FORMULA_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Adjusted Demand'
                      AND LIST_NAME = 'FILE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Adjusted Demand','FILE_TYPE',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Customer Sales'
                      AND LIST_NAME = 'FILE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Customer Sales','FILE_TYPE',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Demand'
                      AND LIST_NAME = 'FILE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Demand','FILE_TYPE',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Ex-Factory Sales'
                      AND LIST_NAME = 'FILE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Ex-Factory Sales','FILE_TYPE',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Inventory Withdrawal - Forecast Detail'
                      AND LIST_NAME = 'FILE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Inventory Withdrawal - Forecast Detail','FILE_TYPE',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Inventory Withdrawal - Forecast Summary'
                      AND LIST_NAME = 'FILE_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Inventory Withdrawal - Forecast Summary','FILE_TYPE',0 )
  END 
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'US'
                      AND LIST_NAME = 'COUNTRY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'US','COUNTRY',0 )
  END 
GO


IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PR'
                      AND LIST_NAME = 'COUNTRY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'PR','COUNTRY',0 )
  END 
GO
----------------------------------------------------------------- ARP

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ACCOUNT'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ACCOUNT','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ACCOUNT_TYPE'
                      AND LIST_NAME = 'CategoryName')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'ACCOUNT_TYPE','CategoryName',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Sales Dollars'
                      AND LIST_NAME = 'ACCOUNT')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Sales Dollars','ACCOUNT','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Sales Units'
                      AND LIST_NAME = 'ACCOUNT')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Sales Units','ACCOUNT','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Deduction'
                      AND LIST_NAME = 'ACCOUNT')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Deduction','ACCOUNT','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Ex-Factory Sales Dollars'
                      AND LIST_NAME = 'ACCOUNT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Ex-Factory Sales Dollars','ACCOUNT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Ex-Factory Sales Units'
                      AND LIST_NAME = 'ACCOUNT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Ex-Factory Sales Units','ACCOUNT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Accrual Rate'
                      AND LIST_NAME = 'ACCOUNT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Accrual Rate','ACCOUNT_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Accrual Dollars'
                      AND LIST_NAME = 'ACCOUNT_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Accrual Dollars','ACCOUNT_TYPE','0' )
  END
GO

  IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Returns Rate'
                      AND LIST_NAME = 'TIER_PERCENT_VALUE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Returns Rate','TIER_PERCENT_VALUE','0' )
  END
GO

  ----------------------------------------------------------

  IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'YES'
                      AND LIST_NAME = 'LOCKED_STATUS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'YES','LOCKED_STATUS',0 )
  END
GO

  IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'NO'
                      AND LIST_NAME = 'LOCKED_STATUS')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'NO','LOCKED_STATUS',0 )
  END
GO
 ----------------------------------------------------------
IF NOT EXISTS (SELECT 1 
               FROM   HELPER_TABLE 
               WHERE  DESCRIPTION = 'National Assumptions' 
                      AND LIST_NAME = 'BUSINESS_PROCESS_TYPE') 
  BEGIN 
      INSERT INTO HELPER_TABLE 
                  (DESCRIPTION, 
                   LIST_NAME, 
                   REF_COUNT) 
      VALUES      ( 'National Assumptions', 
                    'BUSINESS_PROCESS_TYPE', 
                    0 ) 
  END 

GO 

IF NOT EXISTS (SELECT 1 
               FROM   HELPER_TABLE 
               WHERE  DESCRIPTION = 'Returns' 
                      AND LIST_NAME = 'BUSINESS_PROCESS_TYPE') 
  BEGIN 
      INSERT INTO HELPER_TABLE 
                  (DESCRIPTION, 
                   LIST_NAME, 
                   REF_COUNT) 
      VALUES      ( 'Returns', 
                    'BUSINESS_PROCESS_TYPE', 
                    0 ) 
  END 

GO 

IF NOT EXISTS (SELECT 1 
               FROM   HELPER_TABLE 
               WHERE  DESCRIPTION = 'Accounting Closure' 
                      AND LIST_NAME = 'BUSINESS_PROCESS_TYPE') 
  BEGIN 
      INSERT INTO HELPER_TABLE 
                  (DESCRIPTION, 
                   LIST_NAME, 
                   REF_COUNT) 
      VALUES      ( 'Accounting Closure', 
                    'BUSINESS_PROCESS_TYPE', 
                    0 ) 
  END 

go 

IF NOT EXISTS (SELECT 1 
               FROM   HELPER_TABLE 
               WHERE  DESCRIPTION = 'Commercial' 
                      AND LIST_NAME = 'BUSINESS_PROCESS_TYPE') 
  BEGIN 
      INSERT INTO HELPER_TABLE 
                  (DESCRIPTION, 
                   LIST_NAME, 
                   REF_COUNT) 
      VALUES      ( 'Commercial', 
                    'BUSINESS_PROCESS_TYPE', 
                    0 ) 
  END 

GO 

IF NOT EXISTS (SELECT 1 
               FROM   HELPER_TABLE 
               WHERE  DESCRIPTION = 'Government' 
                      AND LIST_NAME = 'BUSINESS_PROCESS_TYPE') 
  BEGIN 
      INSERT INTO HELPER_TABLE 
                  (DESCRIPTION, 
                   LIST_NAME, 
                   REF_COUNT) 
      VALUES      ( 'Government', 
                    'BUSINESS_PROCESS_TYPE', 
                    0 ) 
  END 

GO 

IF NOT EXISTS (SELECT 1 
               FROM   HELPER_TABLE 
               WHERE  DESCRIPTION = 'Consolidated Financial Forecast' 
                      AND LIST_NAME = 'BUSINESS_PROCESS_TYPE') 
  BEGIN 
      INSERT INTO HELPER_TABLE 
                  (DESCRIPTION, 
                   LIST_NAME, 
                   REF_COUNT) 
      VALUES      ( 'Consolidated Financial Forecast', 
                    'BUSINESS_PROCESS_TYPE', 
                    0 ) 
  END 

GO 

IF NOT EXISTS (SELECT 1 
               FROM   HELPER_TABLE 
               WHERE  DESCRIPTION = 'Customer Hierarchy' 
                      AND LIST_NAME = 'HIERARCHY_CATEGORY') 
  BEGIN 
      INSERT INTO HELPER_TABLE 
                  (DESCRIPTION, 
                   LIST_NAME, 
                   REF_COUNT) 
      VALUES      ( 'Customer Hierarchy', 
                    'HIERARCHY_CATEGORY', 
                    0 ) 
  END 

GO 

IF NOT EXISTS (SELECT 1 
               FROM   HELPER_TABLE 
               WHERE  DESCRIPTION = 'Product Hierarchy' 
                      AND LIST_NAME = 'HIERARCHY_CATEGORY') 
  BEGIN 
      INSERT INTO HELPER_TABLE 
                  (DESCRIPTION, 
                   LIST_NAME, 
                   REF_COUNT) 
      VALUES      ( 'Product Hierarchy', 
                    'HIERARCHY_CATEGORY', 
                    0 ) 
  END 

GO 

IF NOT EXISTS (SELECT 1 
               FROM   HELPER_TABLE 
               WHERE  DESCRIPTION = 'Data selection' 
                      AND LIST_NAME = 'HIERARCHY_CATEGORY') 
  BEGIN 
      INSERT INTO HELPER_TABLE 
                  (DESCRIPTION, 
                   LIST_NAME, 
                   REF_COUNT) 
      VALUES      ( 'Data selection', 
                    'HIERARCHY_CATEGORY', 
                    0 ) 
  END 

GO 

IF NOT EXISTS (SELECT 1 
               FROM   HELPER_TABLE 
               WHERE  DESCRIPTION = 'Primary' 
                      AND LIST_NAME = 'HIERARCHY_TYPE') 
  BEGIN 
      INSERT INTO HELPER_TABLE 
                  (DESCRIPTION, 
                   LIST_NAME, 
                   REF_COUNT) 
      VALUES      ( 'Primary', 
                    'HIERARCHY_TYPE', 
                    0 ) 
  END 

GO 

IF NOT EXISTS (SELECT 1 
               FROM   HELPER_TABLE 
               WHERE  DESCRIPTION = 'Secondary' 
                      AND LIST_NAME = 'HIERARCHY_TYPE') 
  BEGIN 
      INSERT INTO HELPER_TABLE 
                  (DESCRIPTION, 
                   LIST_NAME, 
                   REF_COUNT) 
      VALUES      ( 'Secondary', 
                    'HIERARCHY_TYPE', 
                    0 ) 
  END 

GO 
---------------------------------------------------------------------- Financial close tab Mode DDLB

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Auto'
                      AND LIST_NAME = 'ARM_MODE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Auto','ARM_MODE','0' )
  END
GO


IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Manual'
                      AND LIST_NAME = 'ARM_MODE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Manual','ARM_MODE','0' )
  END
GO


IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = '% OF EX-FACTORY'
                      AND LIST_NAME = 'RETURNS_METHODOLOGY')
  BEGIN
   
INSERT INTO HELPER_TABLE
    ([DESCRIPTION], [LIST_NAME], [REF_COUNT])
VALUES
    ('% OF EX-FACTORY', 'RETURNS_METHODOLOGY', -1)

END  
GO
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'AVERAGE'
                      AND LIST_NAME = 'RETURNS_METHODOLOGY')
  BEGIN
    INSERT INTO HELPER_TABLE
    ([DESCRIPTION], [LIST_NAME], [REF_COUNT])
VALUES('AVERAGE', 'RETURNS_METHODOLOGY', -1)
END  
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'GROWTH RATE'
                      AND LIST_NAME = 'RETURNS_MASSUPDATE_FIELD')
  BEGIN

   INSERT INTO HELPER_TABLE
    ([DESCRIPTION], [LIST_NAME], [REF_COUNT])
VALUES ('GROWTH RATE', 'RETURNS_MASSUPDATE_FIELD', -1)
END  
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PROJECTED RETURN %'
                      AND LIST_NAME = 'RETURNS_MASSUPDATE_FIELD')
  BEGIN
    INSERT INTO HELPER_TABLE
    ([DESCRIPTION], [LIST_NAME], [REF_COUNT])
VALUES('PROJECTED RETURN %', 'RETURNS_MASSUPDATE_FIELD', -1)
END  
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PROJECTED RETURN AMOUNT'
                      AND LIST_NAME = 'RETURNS_MASSUPDATE_FIELD')
  BEGIN
    INSERT INTO HELPER_TABLE
    ([DESCRIPTION], [LIST_NAME], [REF_COUNT])
VALUES('PROJECTED RETURN AMOUNT', 'RETURNS_MASSUPDATE_FIELD', -1)

END  
GO
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'PROJECTED RPU'
                      AND LIST_NAME = 'RETURNS_MASSUPDATE_FIELD')
  BEGIN
   INSERT INTO HELPER_TABLE
    ([DESCRIPTION], [LIST_NAME], [REF_COUNT])
VALUES ('PROJECTED RPU', 'RETURNS_MASSUPDATE_FIELD', -1)

END  
GO
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'QUARTILE'
                      AND LIST_NAME = 'RETURNS_METHODOLOGY')
  BEGIN
    INSERT INTO HELPER_TABLE
    ([DESCRIPTION], [LIST_NAME], [REF_COUNT])
VALUES('QUARTILE', 'RETURNS_METHODOLOGY', -1)
END  
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'ROLLING ANNUAL TREND'
                      AND LIST_NAME = 'RETURNS_METHODOLOGY')
  BEGIN
   INSERT INTO HELPER_TABLE
    ([DESCRIPTION], [LIST_NAME], [REF_COUNT])
VALUES ('ROLLING ANNUAL TREND', 'RETURNS_METHODOLOGY', -1)
END  
GO
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'SINGLE PERIOD'
                      AND LIST_NAME = 'RETURNS_METHODOLOGY')
  BEGIN
   INSERT INTO HELPER_TABLE
    ([DESCRIPTION], [LIST_NAME], [REF_COUNT])
VALUES ('SINGLE PERIOD', 'RETURNS_METHODOLOGY', -1)
END 
 GO
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'TIER 1'
                      AND LIST_NAME = 'RETURNS_METHODOLOGY')
  BEGIN
   INSERT INTO HELPER_TABLE
    ([DESCRIPTION], [LIST_NAME], [REF_COUNT])
VALUES ('TIER 1', 'RETURNS_METHODOLOGY', -1)
END  
GO
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'TIER 2'
                      AND LIST_NAME = 'RETURNS_METHODOLOGY')
  BEGIN
   INSERT INTO HELPER_TABLE
    ([DESCRIPTION], [LIST_NAME], [REF_COUNT])
VALUES ('TIER 2', 'RETURNS_METHODOLOGY', -1)

END  
GO
------------------------------------------------------------------------------------arm_status--------------------------------------
IF NOT EXISTS (
		SELECT 1
		FROM HELPER_TABLE
		WHERE DESCRIPTION = 'ARM_STATUS'
			AND LIST_NAME = 'CategoryName'
		)
BEGIN
	INSERT INTO HELPER_TABLE (
		DESCRIPTION
		,LIST_NAME
		,REF_COUNT
		)
	VALUES (
		'ARM_STATUS'
		,'CategoryName'
		,0
		)
END
GO
IF NOT EXISTS (
		SELECT 1
		FROM HELPER_TABLE
		WHERE DESCRIPTION = 'OPEN'
			AND LIST_NAME = 'ARM_STATUS'
		)
BEGIN
	INSERT INTO HELPER_TABLE (
		DESCRIPTION
		,LIST_NAME
		,REF_COUNT
		)
	VALUES (
		'OPEN'
		,'ARM_STATUS'
		,0
		)
END
GO
IF NOT EXISTS (
		SELECT 1
		FROM HELPER_TABLE
		WHERE DESCRIPTION = 'CLOSED'
			AND LIST_NAME = 'ARM_STATUS'
		)
BEGIN
	INSERT INTO HELPER_TABLE (
		DESCRIPTION
		,LIST_NAME
		,REF_COUNT
		)
	VALUES (
		'CLOSED'
		,'ARM_STATUS'
		,0
		)
END
GO

-----------------------------------------------cel-145---------------------------------------

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Line Item Level'
                      AND LIST_NAME = 'REBATE_PLAN_LEVEL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Line Item Level','REBATE_PLAN_LEVEL',-1 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Product Bundle Level'
                      AND LIST_NAME = 'REBATE_PLAN_LEVEL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Product Bundle Level','REBATE_PLAN_LEVEL',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Single Rebate Plan'
                      AND LIST_NAME = 'REBATE_PLAN_LEVEL')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Single Rebate Plan','REBATE_PLAN_LEVEL',0 )
  END
GO
-----------------------------------------------cel-145---------------------------------------
-----------------------------------------------cel-146---------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Monthly'
                      AND LIST_NAME = 'REBATE_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ('Monthly', 'REBATE_FREQUENCY', -1)
  END
GO


IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'F_semi'
                      AND LIST_NAME = 'REBATE_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ('F_semi', 'REBATE_FREQUENCY', -1)
  END
GO


IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Annually'
                      AND LIST_NAME = 'REBATE_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES     ('Annually', 'REBATE_FREQUENCY', 0)
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Quarterly'
                      AND LIST_NAME = 'REBATE_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ('Quarterly', 'REBATE_FREQUENCY', 0)
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Semi-Annually'
                      AND LIST_NAME = 'REBATE_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES       ('Semi-Annually', 'REBATE_FREQUENCY', 0)
  END
GO
-----------------------------------------------cel-146 end---------------------------------------
-----------------------------------------------cel-489 ---------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Complex'
                      AND LIST_NAME = 'FORMULA_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Complex','FORMULA_TYPE',0 )
  END
GO
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Simple'
                      AND LIST_NAME = 'FORMULA_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Simple','FORMULA_TYPE',0 )
  END
GO
-----------------------------------------------cel-489 end ---------------------------------------

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'UN'
                      AND LIST_NAME = 'UOM')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'UN','UOM',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'EACH'
                      AND LIST_NAME = 'UOM')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'EACH','UOM',0 )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Secondary'
                      AND LIST_NAME = 'RELATIONSHIP_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Secondary','RELATIONSHIP_TYPE','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Primary'
                      AND LIST_NAME = 'RELATIONSHIP_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Primary','RELATIONSHIP_TYPE','0' )
  END
GO
--------------------------cel-673-------
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Contract Sales'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Contract Sales','KEYWORD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Contract Units'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Contract Units','KEYWORD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Demand Sales'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Demand Sales','KEYWORD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Demand Units'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Demand Units','KEYWORD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Gross Trade Sales'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Gross Trade Sales','KEYWORD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Gross Trade Units'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Gross Trade Units','KEYWORD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Inventory Withdrawal Sales'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Inventory Withdrawal Sales','KEYWORD','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Inventory Withdrawal Units'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Inventory Withdrawal Units','KEYWORD','0' )
  END
GO


IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Contract Deductions'
                      AND LIST_NAME = 'KEYWORD')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Contract Deductions','KEYWORD','0' )
  END
GO


IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Month'
                      AND LIST_NAME = 'FORECAST_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Month','FORECAST_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Quarter'
                      AND LIST_NAME = 'FORECAST_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Quarter','FORECAST_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Semi-Annual'
                      AND LIST_NAME = 'FORECAST_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Semi-Annual','FORECAST_FREQUENCY','0' )
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Annual'
                      AND LIST_NAME = 'FORECAST_FREQUENCY')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Annual','FORECAST_FREQUENCY','0' )
  END
GO
----------------------------cel-703-------------------
IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Projections'
                      AND LIST_NAME = 'FORECAST_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Projections','FORECAST_TYPE',0 )
  END
GO


IF NOT EXISTS (SELECT 1
               FROM   HELPER_TABLE
               WHERE  DESCRIPTION = 'Actuals'
                      AND LIST_NAME = 'FORECAST_TYPE')
  BEGIN
      INSERT INTO HELPER_TABLE
                  (DESCRIPTION,
                   LIST_NAME,
                   REF_COUNT)
      VALUES      ( 'Actuals','FORECAST_TYPE',0 )
  END
GO
---------------------------------------