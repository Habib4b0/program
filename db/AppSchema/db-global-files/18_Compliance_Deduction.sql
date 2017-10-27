------------------------------------------------------------------------------------------------------CDR_MODEL
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CDR_MODEL'
                      AND TABLE_SCHEMA = 'DBO')
  CREATE TABLE CDR_MODEL
    (
       CDR_MODEL_SID  INT IDENTITY(1, 1),
       RULE_TYPE      INT,
       RULE_NO        VARCHAR(50),
       RULE_NAME      VARCHAR(100),
       RULE_CATEGORY  INT,
       INTERNAL_NOTES VARCHAR(4000),
       CREATED_DATE   DATETIME,
       CREATED_BY     INT,
       MODIFIED_DATE  DATETIME,
       MODIFIED_BY    INT
    )

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'CDR_MODEL'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CDR_MODEL_CREATED_BY')
  BEGIN
      ALTER TABLE CDR_MODEL
        ADD CONSTRAINT DF_CDR_MODEL_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'CDR_MODEL'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CDR_MODEL_CREATED_DATE')
  BEGIN
      ALTER TABLE CDR_MODEL
        ADD CONSTRAINT DF_CDR_MODEL_CREATED_DATE DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'CDR_MODEL'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CDR_MODEL_MODIFIED_BY')
  BEGIN
      ALTER TABLE CDR_MODEL
        ADD CONSTRAINT DF_CDR_MODEL_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'CDR_MODEL'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CDR_MODEL_MODIFIED_DATE')
  BEGIN
      ALTER TABLE CDR_MODEL
        ADD CONSTRAINT DF_CDR_MODEL_MODIFIED_DATE DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_CDR_MODEL_CDR_MODEL_SID'
                     AND TABLE_NAME = 'CDR_MODEL')
  BEGIN
      ALTER TABLE [DBO].CDR_MODEL
        ADD CONSTRAINT PK_CDR_MODEL_CDR_MODEL_SID PRIMARY KEY(CDR_MODEL_SID)
  END

GO

--------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_CDR_MODEL'
                      AND TABLE_SCHEMA = 'DBO')
  CREATE TABLE [DBO].[HIST_CDR_MODEL]
    (
       [CDR_MODEL_SID]  [INT],
       [RULE_TYPE]      [INT] NULL,
       [RULE_NO]        [VARCHAR](50) NULL,
       [RULE_NAME]      [VARCHAR](100) NULL,
       [RULE_CATEGORY]  [INT] NULL,
       [INTERNAL_NOTES] VARCHAR(4000),
       [CREATED_DATE]   [DATETIME] NULL,
       [CREATED_BY]     [INT] NULL,
       [MODIFIED_DATE]  [DATETIME] NULL,
       [MODIFIED_BY]    [INT] NULL,
       ACTION_DATE      DATETIME,
       ACTION_FLAG      CHAR
    )

GO

---------------------------------------------------------------------------------------------------------
IF NOT EXISTS (SELECT *
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'HIST_CDR_MODEL'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_HIST_CDR_MODEL_ACTION_DATE')
  BEGIN
      ALTER TABLE HIST_CDR_MODEL
        ADD CONSTRAINT DF_HIST_CDR_MODEL_ACTION_DATE DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

-------------------------------------------------------------------------------------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CDR_MODEL_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_CDR_MODEL_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_CDR_MODEL_UPD]
ON [DBO].CDR_MODEL
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_CDR_MODEL
                    (CDR_MODEL_SID,
                     RULE_TYPE,
                     RULE_NO,
                     RULE_NAME,
                     RULE_CATEGORY,
                     INTERNAL_NOTES,
                     CREATED_DATE,
                     CREATED_BY,
                     MODIFIED_DATE,
                     MODIFIED_BY,
                     ACTION_FLAG)
        SELECT CDR_MODEL_SID,
               RULE_TYPE,
               RULE_NO,
               RULE_NAME,
               RULE_CATEGORY,
               INTERNAL_NOTES,
               CREATED_DATE,
               CREATED_BY,
               MODIFIED_DATE,
               MODIFIED_BY,
               'C'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CDR_MODEL_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_CDR_MODEL_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_CDR_MODEL_INS]
ON [DBO].CDR_MODEL
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_CDR_MODEL
                    (CDR_MODEL_SID,
                     RULE_TYPE,
                     RULE_NO,
                     RULE_NAME,
                     RULE_CATEGORY,
                     INTERNAL_NOTES,
                     CREATED_DATE,
                     CREATED_BY,
                     MODIFIED_DATE,
                     MODIFIED_BY,
                     ACTION_FLAG)
        SELECT CDR_MODEL_SID,
               RULE_TYPE,
               RULE_NO,
               RULE_NAME,
               RULE_CATEGORY,
               INTERNAL_NOTES,
               CREATED_DATE,
               CREATED_BY,
               MODIFIED_DATE,
               MODIFIED_BY,
               'A'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CDR_MODEL_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_CDR_MODEL_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_CDR_MODEL_DEL]
ON [DBO].CDR_MODEL
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_CDR_MODEL
                    (CDR_MODEL_SID,
                     RULE_TYPE,
                     RULE_NO,
                     RULE_NAME,
                     RULE_CATEGORY,
                     INTERNAL_NOTES,
                     CREATED_DATE,
                     CREATED_BY,
                     MODIFIED_DATE,
                     MODIFIED_BY,
                     ACTION_FLAG)
        SELECT CDR_MODEL_SID,
               RULE_TYPE,
               RULE_NO,
               RULE_NAME,
               RULE_CATEGORY,
               INTERNAL_NOTES,
               CREATED_DATE,
               CREATED_BY,
               MODIFIED_DATE,
               MODIFIED_BY,
               'D'
        FROM   DELETED
  END

GO

------------------------------------------------------------------------------------------------------CDR_DETAILS
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CDR_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE CDR_DETAILS
        (
           CDR_DETAILS_SID           INT IDENTITY(1, 1),
           CDR_MODEL_SID             INT,
           LINE_TYPE                 VARCHAR(50),
           ITEM_GROUP_MS_ASSOCIATION VARCHAR(50),
           KEYWORD                   VARCHAR(50),
           OPERATOR                  VARCHAR(50),
           VALUE                     NUMERIC(22, 6),
           COMPARISON                VARCHAR(50),
           LOGICAL_OPERATOR          VARCHAR(50),
           CREATED_DATE              DATETIME,
           CREATED_BY                INT,
           MODIFIED_DATE             DATETIME,
           MODIFIED_BY               INT
        )
  END

GO

---------------------------- DATATYPE MODIFICATION -------------------------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'CDR_DETAILS'
                  AND COLUMN_NAME = 'LINE_TYPE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE CDR_DETAILS
        ALTER COLUMN LINE_TYPE INT
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'CDR_DETAILS'
                  AND COLUMN_NAME = 'ITEM_GROUP_MS_ASSOCIATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE CDR_DETAILS
        ALTER COLUMN ITEM_GROUP_MS_ASSOCIATION INT
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'CDR_DETAILS'
                  AND COLUMN_NAME = 'KEYWORD'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE CDR_DETAILS
        ALTER COLUMN KEYWORD INT
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'CDR_DETAILS'
                  AND COLUMN_NAME = 'OPERATOR'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE CDR_DETAILS
        ALTER COLUMN OPERATOR INT
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'CDR_DETAILS'
                  AND COLUMN_NAME = 'COMPARISON'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE CDR_DETAILS
        ALTER COLUMN COMPARISON INT
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'CDR_DETAILS'
                  AND COLUMN_NAME = 'LOGICAL_OPERATOR'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE CDR_DETAILS
        ALTER COLUMN LOGICAL_OPERATOR INT
  END

GO

----------------------------- PRIMARY KEY -------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_CDR_DETAILS_CDR_DETAILS_SID'
                      AND TABLE_NAME = 'CDR_DETAILS')
  BEGIN
      ALTER TABLE [DBO].CDR_DETAILS
        ADD CONSTRAINT PK_CDR_DETAILS_CDR_DETAILS_SID PRIMARY KEY (CDR_DETAILS_SID)
  END

GO

----------------------------- DEFAULT_CONSTRAINTS-------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'CDR_DETAILS'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CDR_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE CDR_DETAILS
        ADD CONSTRAINT DF_CDR_DETAILS_CREATED_BY DEFAULT(1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'CDR_DETAILS'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CDR_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE CDR_DETAILS
        ADD CONSTRAINT DF_CDR_DETAILS_CREATED_DATE DEFAULT(GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'CDR_DETAILS'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CDR_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE CDR_DETAILS
        ADD CONSTRAINT DF_CDR_DETAILS_MODIFIED_BY DEFAULT(1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'CDR_DETAILS'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_CDR_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE CDR_DETAILS
        ADD CONSTRAINT DF_CDR_DETAILS_MODIFIED_DATE DEFAULT(GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_CDR_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].HIST_CDR_DETAILS
        (
           [CDR_DETAILS_SID]           [INT],
           [CDR_MODEL_SID]             [INT] NULL,
           [LINE_TYPE]                 [VARCHAR](50) NULL,
           [ITEM_GROUP_MS_ASSOCIATION] [VARCHAR](50) NULL,
           [KEYWORD]                   [VARCHAR](50) NULL,
           [OPERATOR]                  [VARCHAR](50) NULL,
           [VALUE]                     [NUMERIC](22, 6) NULL,
           [COMPARISON]                [VARCHAR](50) NULL,
           [LOGICAL_OPERATOR]          [VARCHAR](50) NULL,
           [CREATED_DATE]              [DATETIME] NULL,
           [CREATED_BY]                [INT] NULL,
           [MODIFIED_DATE]             [DATETIME],
           [MODIFIED_BY]               [INT] NULL,
           ACTION_DATE                 DATETIME,
           ACTION_FLAG                 CHAR
        )
  END

GO

IF NOT EXISTS (SELECT *
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'HIST_CDR_DETAILS'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_HIST_CDR_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE HIST_CDR_DETAILS
        ADD CONSTRAINT DF_HIST_CDR_DETAILS_ACTION_DATE DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

-------------------------------------------------------------------------------------------------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CDR_DETAILS_UPD')
  BEGIN
      DROP TRIGGER DBO.[TRG_CDR_DETAILS_UPD]
  END

GO

CREATE TRIGGER [DBO].[TRG_CDR_DETAILS_UPD]
ON [DBO].[CDR_DETAILS]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_CDR_DETAILS
                    (CDR_DETAILS_SID,
                     CDR_MODEL_SID,
                     LINE_TYPE,
                     ITEM_GROUP_MS_ASSOCIATION,
                     KEYWORD,
                     OPERATOR,
                     VALUE,
                     COMPARISON,
                     LOGICAL_OPERATOR,
                     CREATED_DATE,
                     CREATED_BY,
                     MODIFIED_DATE,
                     MODIFIED_BY,
                     ACTION_FLAG)
        SELECT CDR_DETAILS_SID,
               CDR_MODEL_SID,
               LINE_TYPE,
               ITEM_GROUP_MS_ASSOCIATION,
               KEYWORD,
               OPERATOR,
               VALUE,
               COMPARISON,
               LOGICAL_OPERATOR,
               CREATED_DATE,
               CREATED_BY,
               MODIFIED_DATE,
               MODIFIED_BY,
               'C'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CDR_DETAILS_INS')
  BEGIN
      DROP TRIGGER DBO.[TRG_CDR_DETAILS_INS]
  END

GO

CREATE TRIGGER [DBO].[TRG_CDR_DETAILS_INS]
ON [DBO].[CDR_DETAILS]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_CDR_DETAILS
                    (CDR_DETAILS_SID,
                     CDR_MODEL_SID,
                     LINE_TYPE,
                     ITEM_GROUP_MS_ASSOCIATION,
                     KEYWORD,
                     OPERATOR,
                     VALUE,
                     COMPARISON,
                     LOGICAL_OPERATOR,
                     CREATED_DATE,
                     CREATED_BY,
                     MODIFIED_DATE,
                     MODIFIED_BY,
                     ACTION_FLAG)
        SELECT CDR_DETAILS_SID,
               CDR_MODEL_SID,
               LINE_TYPE,
               ITEM_GROUP_MS_ASSOCIATION,
               KEYWORD,
               OPERATOR,
               VALUE,
               COMPARISON,
               LOGICAL_OPERATOR,
               CREATED_DATE,
               CREATED_BY,
               MODIFIED_DATE,
               MODIFIED_BY,
               'A'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_CDR_DETAILS_DEL')
  BEGIN
      DROP TRIGGER DBO.[TRG_CDR_DETAILS_DEL]
  END

GO

CREATE TRIGGER [DBO].[TRG_CDR_DETAILS_DEL]
ON [DBO].[CDR_DETAILS]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_CDR_DETAILS
                    (CDR_DETAILS_SID,
                     CDR_MODEL_SID,
                     LINE_TYPE,
                     ITEM_GROUP_MS_ASSOCIATION,
                     KEYWORD,
                     OPERATOR,
                     VALUE,
                     COMPARISON,
                     LOGICAL_OPERATOR,
                     CREATED_DATE,
                     CREATED_BY,
                     MODIFIED_DATE,
                     MODIFIED_BY,
                     ACTION_FLAG)
        SELECT CDR_DETAILS_SID,
               CDR_MODEL_SID,
               LINE_TYPE,
               ITEM_GROUP_MS_ASSOCIATION,
               KEYWORD,
               OPERATOR,
               VALUE,
               COMPARISON,
               LOGICAL_OPERATOR,
               CREATED_DATE,
               CREATED_BY,
               MODIFIED_DATE,
               MODIFIED_BY,
               'D'
        FROM   DELETED
  END 
  GO