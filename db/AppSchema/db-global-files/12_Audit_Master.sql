-------------------------------------AUDIT_MASTER_INBOUND-------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'AUDIT_MASTER_INBOUND'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[AUDIT_MASTER_INBOUND]
        (
           [AUDIT_INBOUND_SID]            INT IDENTITY(1, 1) NOT NULL,
           [APPLICATION_PROCESS]          VARCHAR(150) NULL,
           [FILE_NAME]                    VARCHAR(100) NULL,
           [SENT_RECORD_COUNT]            INT NULL,
           [RECEIVED_RECORD_COUNT]        INT NULL,
           [SENT_RECORD_AMOUNT]           NUMERIC(22, 6) NULL,
           [SENT_RECORD_AMOUNT_ATTRIBUTE] VARCHAR(50) NULL,
           [RECEIVED_RECORD_AMOUNT]       NUMERIC(22, 6) NULL,
           [RECEIVED_RECORD_AMOUNT_ATTR]  VARCHAR(50) NULL,
           [INVALID_RECORD_COUNT]         INT NULL,
           [INVALID_RECORD_AMOUNT]        NUMERIC(22, 6) NULL,
           [VALID_RECORD_AMOUNT]          NUMERIC(22, 6) NULL,
           [DISCREPANCY_AMOUNT]           NUMERIC(22, 6) NULL,
           [INTERFACE_RUN_START_DATE]     DATETIME NULL,
           [INTERFACE_RUN_END_DATE]       DATETIME NULL,
           [ADD_COUNT]                    INT NULL,
           [CHANGE_COUNT]                 INT NULL,
           [DELETE_COUNT]                 INT NULL,
           [STATUS]                       CHAR(1) NULL,
           [UNPROCESSED_RECORDS]          VARCHAR(20) NULL,
           [BATCH_ID]                     VARCHAR(50) NULL,
           [SOURCE]                       VARCHAR(50) NULL,
           [CREATED_BY]                   INT NOT NULL,
           [CREATED_DATE]                 DATETIME NOT NULL,
           [MODIFIED_BY]                  INT NOT NULL,
           [MODIFIED_DATE]                DATETIME NOT NULL
        )
  END

GO
------------------------------------------------ column addition
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'AUDIT_MASTER_INBOUND'
                      AND COLUMN_NAME = 'UNITS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD UNITS NUMERIC(22,6) NULL
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'AUDIT_MASTER_INBOUND'
                      AND COLUMN_NAME = 'SALES_AMOUNT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD SALES_AMOUNT NUMERIC(22,6) NULL
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'AUDIT_MASTER_INBOUND'
                      AND COLUMN_NAME = 'DEDUCTION_AMOUNT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD DEDUCTION_AMOUNT NUMERIC(22,6) NULL
  END

GO




-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_AUDIT_MASTER_INBOUND_AUDIT_INBOUND_SID'
                 AND TABLE_NAME = 'AUDIT_MASTER_INBOUND')
  ALTER TABLE AUDIT_MASTER_INBOUND
    ADD CONSTRAINT PK_AUDIT_MASTER_INBOUND_AUDIT_INBOUND_SID PRIMARY KEY(AUDIT_INBOUND_SID)

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_INBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_INBOUND_CREATED_BY')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_INBOUND_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_INBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_INBOUND_CREATED_DATE')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_INBOUND_CREATED_DATE DEFAULT (GetDate()) FOR CREATED_DATE
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_INBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_INBOUND_MODIFIED_BY')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_INBOUND_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_INBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_INBOUND_MODIFIED_DATE')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_INBOUND_MODIFIED_DATE DEFAULT (GetDate()) FOR MODIFIED_DATE
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_INBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_INBOUND_ADD_COUNT')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_INBOUND_ADD_COUNT DEFAULT (0) FOR ADD_COUNT
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_INBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_INBOUND_INVALID_RECORD_COUNT')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_INBOUND_INVALID_RECORD_COUNT DEFAULT (0) FOR INVALID_RECORD_COUNT
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_INBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_INBOUND_CHANGE_COUNT')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_INBOUND_CHANGE_COUNT DEFAULT (0) FOR CHANGE_COUNT
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_INBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_INBOUND_DELETE_COUNT')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_INBOUND_DELETE_COUNT DEFAULT (0) FOR DELETE_COUNT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_INBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_INBOUND_UNPROCESSED_RECORDS')
  BEGIN
      ALTER TABLE AUDIT_MASTER_INBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_INBOUND_UNPROCESSED_RECORDS DEFAULT (0) FOR UNPROCESSED_RECORDS
  END

GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'AUDIT_MASTER_INBOUND'--TABLE NAME
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
      EXEC sp_Executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 

---------------------------HIST_AUDIT_MASTER_INBOUND---------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_AUDIT_MASTER_INBOUND'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_AUDIT_MASTER_INBOUND]
        (
           [AUDIT_INBOUND_SID]            INT NOT NULL,
           [APPLICATION_PROCESS]          VARCHAR(150) NULL,
           [FILE_NAME]                    VARCHAR(100) NULL,
           [SENT_RECORD_COUNT]            INT NULL,
           [RECEIVED_RECORD_COUNT]        INT NULL,
           [SENT_RECORD_AMOUNT]           NUMERIC(22, 6) NULL,
           [SENT_RECORD_AMOUNT_ATTRIBUTE] VARCHAR(50) NULL,
           [RECEIVED_RECORD_AMOUNT]       NUMERIC(22, 6) NULL,
           [RECEIVED_RECORD_AMOUNT_ATTR]  VARCHAR(50) NULL,
           [INVALID_RECORD_COUNT]         INT NULL,
           [INVALID_RECORD_AMOUNT]        NUMERIC(22, 6) NULL,
           [VALID_RECORD_AMOUNT]          NUMERIC(22, 6) NULL,
           [DISCREPANCY_AMOUNT]           NUMERIC(22, 6) NULL,
           [INTERFACE_RUN_START_DATE]     DATETIME NULL,
           [INTERFACE_RUN_END_DATE]       DATETIME NULL,
           [ADD_COUNT]                    INT NULL,
           [CHANGE_COUNT]                 INT NULL,
           [DELETE_COUNT]                 INT NULL,
           [STATUS]                       CHAR(1) NULL,
           [UNPROCESSED_RECORDS]          VARCHAR(20) NULL,
           [BATCH_ID]                     VARCHAR(50) NULL,
           [SOURCE]                       VARCHAR(50) NULL,
           [CREATED_BY]                   INT NOT NULL,
           [CREATED_DATE]                 DATETIME NOT NULL,
           [MODIFIED_BY]                  INT NOT NULL,
           [MODIFIED_DATE]                DATETIME NOT NULL,
           [ACTION_FLAG]                  CHAR(1) NOT NULL,
           [ACTION_DATE]                  DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_AUDIT_MASTER_INBOUND')
                      AND NAME = 'DF_HIST_AUDIT_MASTER_INBOUND_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_AUDIT_MASTER_INBOUND]
        ADD CONSTRAINT [DF_HIST_AUDIT_MASTER_INBOUND_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO
----------------------column addition scripts---------------------------
	IF NOT EXISTS (SELECT 1
	               FROM   INFORMATION_SCHEMA.COLUMNS
	               WHERE  TABLE_NAME = 'HIST_AUDIT_MASTER_INBOUND'
	                      AND COLUMN_NAME = 'UNITS'
	                      AND TABLE_SCHEMA = 'DBO')
	  BEGIN
	      ALTER TABLE HIST_AUDIT_MASTER_INBOUND
	        ADD UNITS NUMERIC(22,6) NULL
	  END
	
	GO
	
	IF NOT EXISTS (SELECT 1
	               FROM   INFORMATION_SCHEMA.COLUMNS
	               WHERE  TABLE_NAME = 'HIST_AUDIT_MASTER_INBOUND'
	                      AND COLUMN_NAME = 'SALES_AMOUNT'
	                      AND TABLE_SCHEMA = 'DBO')
	  BEGIN
	      ALTER TABLE HIST_AUDIT_MASTER_INBOUND
	        ADD SALES_AMOUNT NUMERIC(22,6) NULL
	  END
	
	GO
	
	IF NOT EXISTS (SELECT 1
	               FROM   INFORMATION_SCHEMA.COLUMNS
	               WHERE  TABLE_NAME = 'HIST_AUDIT_MASTER_INBOUND'
	                      AND COLUMN_NAME = 'DEDUCTION_AMOUNT'
	                      AND TABLE_SCHEMA = 'DBO')
	  BEGIN
	      ALTER TABLE HIST_AUDIT_MASTER_INBOUND
	        ADD DEDUCTION_AMOUNT NUMERIC(22,6) NULL
	  END
	
	GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_AUDIT_MASTER_INBOUND'--TABLE NAME
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


-------------------------AUDIT_MASTER_INBOUND TRIGGER--------------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_AUDIT_MASTER_INBOUND_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_AUDIT_MASTER_INBOUND_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_AUDIT_MASTER_INBOUND_INS]
ON [dbo].[AUDIT_MASTER_INBOUND]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_AUDIT_MASTER_INBOUND
                  (AUDIT_INBOUND_SID,
                   APPLICATION_PROCESS,
                   FILE_NAME,
                   SENT_RECORD_COUNT,
                   RECEIVED_RECORD_COUNT,
                   SENT_RECORD_AMOUNT,
                   SENT_RECORD_AMOUNT_ATTRIBUTE,
                   RECEIVED_RECORD_AMOUNT,
                   RECEIVED_RECORD_AMOUNT_ATTR,
                   INVALID_RECORD_COUNT,
                   INVALID_RECORD_AMOUNT,
                   VALID_RECORD_AMOUNT,
                   DISCREPANCY_AMOUNT,
                   INTERFACE_RUN_START_DATE,
                   INTERFACE_RUN_END_DATE,
                   ADD_COUNT,
                   CHANGE_COUNT,
                   DELETE_COUNT,
                   STATUS,
                   UNPROCESSED_RECORDS,
                   BATCH_ID,
                   SOURCE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
				   MODIFIED_DATE,
				   UNITS,
				   SALES_AMOUNT,
				   DEDUCTION_AMOUNT,
                   ACTION_FLAG)
      SELECT AUDIT_INBOUND_SID,
             APPLICATION_PROCESS,
             FILE_NAME,
             SENT_RECORD_COUNT,
             RECEIVED_RECORD_COUNT,
             SENT_RECORD_AMOUNT,
             SENT_RECORD_AMOUNT_ATTRIBUTE,
             RECEIVED_RECORD_AMOUNT,
             RECEIVED_RECORD_AMOUNT_ATTR,
             INVALID_RECORD_COUNT,
             INVALID_RECORD_AMOUNT,
             VALID_RECORD_AMOUNT,
             DISCREPANCY_AMOUNT,
             INTERFACE_RUN_START_DATE,
             INTERFACE_RUN_END_DATE,
             ADD_COUNT,
             CHANGE_COUNT,
             DELETE_COUNT,
             STATUS,
             UNPROCESSED_RECORDS,
             BATCH_ID,
             SOURCE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             UNITS,
			 SALES_AMOUNT,
			 DEDUCTION_AMOUNT,
             'A'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_AUDIT_MASTER_INBOUND_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_AUDIT_MASTER_INBOUND_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_AUDIT_MASTER_INBOUND_UPD]
ON [dbo].[AUDIT_MASTER_INBOUND]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_AUDIT_MASTER_INBOUND
                  (AUDIT_INBOUND_SID,
                   APPLICATION_PROCESS,
                   FILE_NAME,
                   SENT_RECORD_COUNT,
                   RECEIVED_RECORD_COUNT,
                   SENT_RECORD_AMOUNT,
                   SENT_RECORD_AMOUNT_ATTRIBUTE,
                   RECEIVED_RECORD_AMOUNT,
                   RECEIVED_RECORD_AMOUNT_ATTR,
                   INVALID_RECORD_COUNT,
                   INVALID_RECORD_AMOUNT,
                   VALID_RECORD_AMOUNT,
                   DISCREPANCY_AMOUNT,
                   INTERFACE_RUN_START_DATE,
                   INTERFACE_RUN_END_DATE,
                   ADD_COUNT,
                   CHANGE_COUNT,
                   DELETE_COUNT,
                   STATUS,
                   UNPROCESSED_RECORDS,
                   BATCH_ID,
                   SOURCE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   UNITS,
				   SALES_AMOUNT,
				   DEDUCTION_AMOUNT,
                   ACTION_FLAG)
      SELECT AUDIT_INBOUND_SID,
             APPLICATION_PROCESS,
             FILE_NAME,
             SENT_RECORD_COUNT,
             RECEIVED_RECORD_COUNT,
             SENT_RECORD_AMOUNT,
             SENT_RECORD_AMOUNT_ATTRIBUTE,
             RECEIVED_RECORD_AMOUNT,
             RECEIVED_RECORD_AMOUNT_ATTR,
             INVALID_RECORD_COUNT,
             INVALID_RECORD_AMOUNT,
             VALID_RECORD_AMOUNT,
             DISCREPANCY_AMOUNT,
             INTERFACE_RUN_START_DATE,
             INTERFACE_RUN_END_DATE,
             ADD_COUNT,
             CHANGE_COUNT,
             DELETE_COUNT,
             STATUS,
             UNPROCESSED_RECORDS,
             BATCH_ID,
             SOURCE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             UNITS,
		     SALES_AMOUNT,
		     DEDUCTION_AMOUNT,
             'C'
      FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_AUDIT_MASTER_INBOUND_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_AUDIT_MASTER_INBOUND_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_AUDIT_MASTER_INBOUND_DEL]
ON [dbo].[AUDIT_MASTER_INBOUND]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_AUDIT_MASTER_INBOUND
                  (AUDIT_INBOUND_SID,
                   APPLICATION_PROCESS,
                   FILE_NAME,
                   SENT_RECORD_COUNT,
                   RECEIVED_RECORD_COUNT,
                   SENT_RECORD_AMOUNT,
                   SENT_RECORD_AMOUNT_ATTRIBUTE,
                   RECEIVED_RECORD_AMOUNT,
                   RECEIVED_RECORD_AMOUNT_ATTR,
                   INVALID_RECORD_COUNT,
                   INVALID_RECORD_AMOUNT,
                   VALID_RECORD_AMOUNT,
                   DISCREPANCY_AMOUNT,
                   INTERFACE_RUN_START_DATE,
                   INTERFACE_RUN_END_DATE,
                   ADD_COUNT,
                   CHANGE_COUNT,
                   DELETE_COUNT,
                   STATUS,
                   UNPROCESSED_RECORDS,
                   BATCH_ID,
                   SOURCE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   UNITS,
                   SALES_AMOUNT,
                   DEDUCTION_AMOUNT,
                   ACTION_FLAG)
      SELECT AUDIT_INBOUND_SID,
             APPLICATION_PROCESS,
             FILE_NAME,
             SENT_RECORD_COUNT,
             RECEIVED_RECORD_COUNT,
             SENT_RECORD_AMOUNT,
             SENT_RECORD_AMOUNT_ATTRIBUTE,
             RECEIVED_RECORD_AMOUNT,
             RECEIVED_RECORD_AMOUNT_ATTR,
             INVALID_RECORD_COUNT,
             INVALID_RECORD_AMOUNT,
             VALID_RECORD_AMOUNT,
             DISCREPANCY_AMOUNT,
             INTERFACE_RUN_START_DATE,
             INTERFACE_RUN_END_DATE,
             ADD_COUNT,
             CHANGE_COUNT,
             DELETE_COUNT,
             STATUS,
             UNPROCESSED_RECORDS,
             BATCH_ID,
             SOURCE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             UNITS,
			 SALES_AMOUNT,
			 DEDUCTION_AMOUNT,
             'D'
      FROM   DELETED
  END

GO

------------------------------------------AUDIT_MASTER_OUTBOUND---------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'AUDIT_MASTER_OUTBOUND'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[AUDIT_MASTER_OUTBOUND]
        (
           AUDIT_OUTBOUND_SID             INT IDENTITY(1, 1) NOT NULL,
           [APPLICATION_PROCESS]          VARCHAR(150) NULL,
           [FILE_NAME]                    VARCHAR(100) NULL,
           [SENT_RECORD_COUNT]            INT NULL,
           [RECEIVED_RECORD_COUNT]        INT NULL,
           [SENT_RECORD_AMOUNT]           NUMERIC(22, 6) NULL,
           [SENT_RECORD_AMOUNT_ATTRIBUTE] VARCHAR(50) NULL,
           [RECEIVED_RECORD_AMOUNT]       NUMERIC(22, 6) NULL,
           [RECEIVED_RECORD_AMOUNT_ATTR]  VARCHAR(50) NULL,
           [INVALID_RECORD_COUNT]         INT NULL,
           [VALID_RECORD_COUNT]           INT NULL,
           [INVALID_RECORD_AMOUNT]        NUMERIC(22, 6) NULL,
           [VALID_RECORD_AMOUNT]          NUMERIC(22, 6) NULL,
           [DISCREPANCY_AMOUNT]           NUMERIC(22, 6) NULL,
           [INTERFACE_RUN_START_DATE]     DATETIME NULL,
           [INTERFACE_RUN_END_DATE]       DATETIME NULL,
           [BATCH_ID]                     VARCHAR(50) NULL,
           [SOURCE]                       VARCHAR(50) NULL,
           [CREATED_BY]                   INT NOT NULL,
           [CREATED_DATE]                 DATETIME NOT NULL,
           [MODIFIED_BY]                  INT NOT NULL,
           [MODIFIED_DATE]                DATETIME NOT NULL
        )
  END

GO

-------------PRIMARY KEYS--------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_AUDIT_MASTER_OUTBOUND_AUDIT_OUTBOUND_SID'
                 AND TABLE_NAME = 'AUDIT_MASTER_OUTBOUND')
  ALTER TABLE AUDIT_MASTER_OUTBOUND
    ADD CONSTRAINT PK_AUDIT_MASTER_OUTBOUND_AUDIT_OUTBOUND_SID PRIMARY KEY(AUDIT_OUTBOUND_SID)

GO

-------------DEFAULT CONSTRAINTS---------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_OUTBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_OUTBOUND_CREATED_BY')
  BEGIN
      ALTER TABLE AUDIT_MASTER_OUTBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_OUTBOUND_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_OUTBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_OUTBOUND_CREATED_DATE')
  BEGIN
      ALTER TABLE AUDIT_MASTER_OUTBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_OUTBOUND_CREATED_DATE DEFAULT (GetDate()) FOR CREATED_DATE
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_OUTBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_OUTBOUND_MODIFIED_BY')
  BEGIN
      ALTER TABLE AUDIT_MASTER_OUTBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_OUTBOUND_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'AUDIT_MASTER_OUTBOUND'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_AUDIT_MASTER_OUTBOUND_MODIFIED_DATE')
  BEGIN
      ALTER TABLE AUDIT_MASTER_OUTBOUND
        ADD CONSTRAINT DF_AUDIT_MASTER_OUTBOUND_MODIFIED_DATE DEFAULT (GetDate()) FOR MODIFIED_DATE
  END
GO

GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'AUDIT_MASTER_OUTBOUND'--TABLE NAME
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
      EXEC sp_Executesql
        @STATS

      FETCH NEXT FROM CUR1 INTO @STATS
  END

CLOSE CUR1

DEALLOCATE CUR1

GO 

---------------------------------------HIST_AUDIT_MASTER_OUTBOUND----------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_AUDIT_MASTER_OUTBOUND'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_AUDIT_MASTER_OUTBOUND]
        (
           AUDIT_OUTBOUND_SID             INT NOT NULL,
           [APPLICATION_PROCESS]          VARCHAR(150) NULL,
           [FILE_NAME]                    VARCHAR(100) NULL,
           [SENT_RECORD_COUNT]            INT NULL,
           [RECEIVED_RECORD_COUNT]        INT NULL,
           [SENT_RECORD_AMOUNT]           NUMERIC(22, 6) NULL,
           [SENT_RECORD_AMOUNT_ATTRIBUTE] VARCHAR(50) NULL,
           [RECEIVED_RECORD_AMOUNT]       NUMERIC(22, 6) NULL,
           [RECEIVED_RECORD_AMOUNT_ATTR]  VARCHAR(50) NULL,
           [INVALID_RECORD_COUNT]         INT NULL,
           [VALID_RECORD_COUNT]           INT NULL,
           [INVALID_RECORD_AMOUNT]        NUMERIC(22, 6) NULL,
           [VALID_RECORD_AMOUNT]          NUMERIC(22, 6) NULL,
           [DISCREPANCY_AMOUNT]           NUMERIC(22, 6) NULL,
           [INTERFACE_RUN_START_DATE]     DATETIME NULL,
           [INTERFACE_RUN_END_DATE]       DATETIME NULL,
           [BATCH_ID]                     VARCHAR(50) NULL,
           [SOURCE]                       VARCHAR(50) NULL,
           [CREATED_BY]                   INT NOT NULL,
           [CREATED_DATE]                 DATETIME NOT NULL,
           [MODIFIED_BY]                  INT NOT NULL,
           [MODIFIED_DATE]                DATETIME NOT NULL,
           [ACTION_FLAG]                  CHAR(1) NOT NULL,
           [ACTION_DATE]                  DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_AUDIT_MASTER_OUTBOUND')
                      AND NAME = 'DF_HIST_AUDIT_MASTER_OUTBOUND_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_AUDIT_MASTER_OUTBOUND]
        ADD CONSTRAINT [DF_HIST_AUDIT_MASTER_OUTBOUND_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_AUDIT_MASTER_OUTBOUND'--TABLE NAME
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



-----------------------------------AUDIT_MASTER_OUTBOUND TRIGGER-------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_AUDIT_MASTER_OUTBOUND_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_AUDIT_MASTER_OUTBOUND_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_AUDIT_MASTER_OUTBOUND_INS]
ON [dbo].[AUDIT_MASTER_OUTBOUND]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_AUDIT_MASTER_OUTBOUND
                  (AUDIT_OUTBOUND_SID,
                   APPLICATION_PROCESS,
                   FILE_NAME,
                   SENT_RECORD_COUNT,
                   RECEIVED_RECORD_COUNT,
                   SENT_RECORD_AMOUNT,
                   SENT_RECORD_AMOUNT_ATTRIBUTE,
                   RECEIVED_RECORD_AMOUNT,
                   RECEIVED_RECORD_AMOUNT_ATTR,
                   INVALID_RECORD_COUNT,
                   VALID_RECORD_COUNT,
                   INVALID_RECORD_AMOUNT,
                   VALID_RECORD_AMOUNT,
                   DISCREPANCY_AMOUNT,
                   INTERFACE_RUN_START_DATE,
                   INTERFACE_RUN_END_DATE,
                   BATCH_ID,
                   SOURCE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   ACTION_FLAG)
      SELECT AUDIT_OUTBOUND_SID,
             APPLICATION_PROCESS,
             FILE_NAME,
             SENT_RECORD_COUNT,
             RECEIVED_RECORD_COUNT,
             SENT_RECORD_AMOUNT,
             SENT_RECORD_AMOUNT_ATTRIBUTE,
             RECEIVED_RECORD_AMOUNT,
             RECEIVED_RECORD_AMOUNT_ATTR,
             INVALID_RECORD_COUNT,
             VALID_RECORD_COUNT,
             INVALID_RECORD_AMOUNT,
             VALID_RECORD_AMOUNT,
             DISCREPANCY_AMOUNT,
             INTERFACE_RUN_START_DATE,
             INTERFACE_RUN_END_DATE,
             BATCH_ID,
             SOURCE,
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
           WHERE  [Name] = N'TRG_AUDIT_MASTER_OUTBOUND_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_AUDIT_MASTER_OUTBOUND_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_AUDIT_MASTER_OUTBOUND_UPD]
ON [dbo].[AUDIT_MASTER_OUTBOUND]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_AUDIT_MASTER_OUTBOUND
                  (AUDIT_OUTBOUND_SID,
                   APPLICATION_PROCESS,
                   FILE_NAME,
                   SENT_RECORD_COUNT,
                   RECEIVED_RECORD_COUNT,
                   SENT_RECORD_AMOUNT,
                   SENT_RECORD_AMOUNT_ATTRIBUTE,
                   RECEIVED_RECORD_AMOUNT,
                   RECEIVED_RECORD_AMOUNT_ATTR,
                   INVALID_RECORD_COUNT,
                   VALID_RECORD_COUNT,
                   INVALID_RECORD_AMOUNT,
                   VALID_RECORD_AMOUNT,
                   DISCREPANCY_AMOUNT,
                   INTERFACE_RUN_START_DATE,
                   INTERFACE_RUN_END_DATE,
                   BATCH_ID,
                   SOURCE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   ACTION_FLAG)
      SELECT AUDIT_OUTBOUND_SID,
             APPLICATION_PROCESS,
             FILE_NAME,
             SENT_RECORD_COUNT,
             RECEIVED_RECORD_COUNT,
             SENT_RECORD_AMOUNT,
             SENT_RECORD_AMOUNT_ATTRIBUTE,
             RECEIVED_RECORD_AMOUNT,
             RECEIVED_RECORD_AMOUNT_ATTR,
             INVALID_RECORD_COUNT,
             VALID_RECORD_COUNT,
             INVALID_RECORD_AMOUNT,
             VALID_RECORD_AMOUNT,
             DISCREPANCY_AMOUNT,
             INTERFACE_RUN_START_DATE,
             INTERFACE_RUN_END_DATE,
             BATCH_ID,
             SOURCE,
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
           WHERE  [Name] = N'TRG_AUDIT_MASTER_OUTBOUND_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_AUDIT_MASTER_OUTBOUND_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_AUDIT_MASTER_OUTBOUND_DEL]
ON [dbo].[AUDIT_MASTER_OUTBOUND]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      INSERT INTO HIST_AUDIT_MASTER_OUTBOUND
                  (AUDIT_OUTBOUND_SID,
                   APPLICATION_PROCESS,
                   FILE_NAME,
                   SENT_RECORD_COUNT,
                   RECEIVED_RECORD_COUNT,
                   SENT_RECORD_AMOUNT,
                   SENT_RECORD_AMOUNT_ATTRIBUTE,
                   RECEIVED_RECORD_AMOUNT,
                   RECEIVED_RECORD_AMOUNT_ATTR,
                   INVALID_RECORD_COUNT,
                   VALID_RECORD_COUNT,
                   INVALID_RECORD_AMOUNT,
                   VALID_RECORD_AMOUNT,
                   DISCREPANCY_AMOUNT,
                   INTERFACE_RUN_START_DATE,
                   INTERFACE_RUN_END_DATE,
                   BATCH_ID,
                   SOURCE,
                   CREATED_BY,
                   CREATED_DATE,
                   MODIFIED_BY,
                   MODIFIED_DATE,
                   ACTION_FLAG)
      SELECT AUDIT_OUTBOUND_SID,
             APPLICATION_PROCESS,
             FILE_NAME,
             SENT_RECORD_COUNT,
             RECEIVED_RECORD_COUNT,
             SENT_RECORD_AMOUNT,
             SENT_RECORD_AMOUNT_ATTRIBUTE,
             RECEIVED_RECORD_AMOUNT,
             RECEIVED_RECORD_AMOUNT_ATTR,
             INVALID_RECORD_COUNT,
             VALID_RECORD_COUNT,
             INVALID_RECORD_AMOUNT,
             VALID_RECORD_AMOUNT,
             DISCREPANCY_AMOUNT,
             INTERFACE_RUN_START_DATE,
             INTERFACE_RUN_END_DATE,
             BATCH_ID,
             SOURCE,
             CREATED_BY,
             CREATED_DATE,
             MODIFIED_BY,
             MODIFIED_DATE,
             'D'
      FROM   DELETED
  END

GO