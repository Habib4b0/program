IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[RS_CONTRACT]
        (
           RS_CONTRACT_SID             INT NOT NULL IDENTITY(1, 1),
           RS_MODEL_SID                INT NOT NULL,
           RS_ID                       VARCHAR(50) NOT NULL,
           RS_NO                       VARCHAR(50) NOT NULL,
           RS_NAME                     VARCHAR(100) NULL,
           RS_TYPE                     INT NOT NULL,
           REBATE_PROGRAM_TYPE         INT NOT NULL,
           RS_CATEGORY                 INT NULL,
           RS_STATUS                   INT NULL,
           RS_DESIGNATION              INT NULL,
           RS_START_DATE               DATETIME NOT NULL,
           RS_END_DATE                 DATETIME NULL,
           RS_TRADE_CLASS              INT NULL,
           PARENT_RS_ID                VARCHAR(50) NULL,
           PARENT_RS_NAME              VARCHAR(100) NULL,
           CONTRACT_MASTER_SID         INT NOT NULL,
           CFP_CONTRACT_SID            INT NULL,
           IFP_CONTRACT_SID            INT NULL,
           PS_CONTRACT_SID             INT NULL,
           RS_CONTRACT_ATTACHED_STATUS INT NULL,
           RS_CONTRACT_ATTACHED_DATE   DATETIME,
           RS_TRANS_REF_ID             VARCHAR(50) NULL,
           RS_TRANS_REF_NO             VARCHAR(50) NULL,
           RS_TRANS_REF_NAME           VARCHAR(100) NULL,
           REBATE_RULE_TYPE            INT NULL,
           REBATE_RULE_ASSOCIATION     VARCHAR(100) NULL,
           REBATE_PLAN_LEVEL           VARCHAR(50) NULL,
           INTEREST_BEARING_INDICATOR  INT NULL,
           INTEREST_BEARING_BASIS      INT NULL,
           REBATE_PROCESSING_TYPE      INT NULL,
           REBATE_FREQUENCY            INT NOT NULL,
           PAYMENT_METHOD              INT NULL,
           PAYMENT_FREQUENCY           INT NULL,
           PAYMENT_TERMS               INT NULL,
           PAYMENT_GRACE_PERIOD        VARCHAR(25) NULL,
           RS_CALENDAR                 INT NULL,
           RS_VALIDATION_PROFILE       INT NULL,
           MAKE_PAYABLE_TO             VARCHAR(50) NULL,
           ADDRESS_1                   VARCHAR(100) NULL,
           ADDRESS_2                   VARCHAR(100) NULL,
           CITY                        VARCHAR(50) NULL,
           [STATE]                     INT NULL,
           ZIP_CODE                    VARCHAR(50) NULL,
           RS_ALIAS                    VARCHAR(100) NULL,
           FORMULA_METHOD_ID           VARCHAR(38) NULL,
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

-------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT'
                      AND COLUMN_NAME = 'DEDUCTION_INCLUSION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ADD DEDUCTION_INCLUSION INT NULL
  END

GO

----------------------------------------------
--------------------- DROP STATISTICS ---------------------------
IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'CALENDAR'
                  AND OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.RS_CONTRACT.CALENDAR
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'RS_DESIGNATION'
                  AND OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.RS_CONTRACT.RS_DESIGNATION
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'REBATE_PLAN_LEVEL'
                  AND OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.RS_CONTRACT.REBATE_PLAN_LEVEL
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RS_CONTRACT'
                  AND COLUMN_NAME = 'REBATE_PLAN_LEVEL'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ALTER COLUMN REBATE_PLAN_LEVEL INT
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'INTEREST_BEARING_INDICATOR'
                  AND OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.RS_CONTRACT.INTEREST_BEARING_INDICATOR
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RS_CONTRACT'
                  AND COLUMN_NAME = 'INTEREST_BEARING_INDICATOR'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ALTER COLUMN INTEREST_BEARING_INDICATOR INT
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'INTEREST_BEARING_BASIS'
                  AND OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.RS_CONTRACT.INTEREST_BEARING_BASIS
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RS_CONTRACT'
                  AND COLUMN_NAME = 'INTEREST_BEARING_BASIS'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ALTER COLUMN INTEREST_BEARING_BASIS INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT'
                      AND COLUMN_NAME = 'CALCULATION_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ADD CALCULATION_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT'
                      AND COLUMN_NAME = 'CALCULATION_LEVEL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ADD CALCULATION_LEVEL INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT'
                      AND COLUMN_NAME = 'CALCULATION_RULE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ADD CALCULATION_RULE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT'
                      AND COLUMN_NAME = 'CALCULATION_RULE_LEVEL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ADD CALCULATION_RULE_LEVEL INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT'
                      AND COLUMN_NAME = 'EVALUATION_RULE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ADD EVALUATION_RULE_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT'
                      AND COLUMN_NAME = 'EVALUATION_RULE_LEVEL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ADD EVALUATION_RULE_LEVEL INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT'
                      AND COLUMN_NAME = 'EVALUATION_RULE_OR_ASSOCIATION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ADD EVALUATION_RULE_OR_ASSOCIATION INT
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'RS_CONTRACT'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_RS_CONTRACT_RS_CONTRACT_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [DBO].[RS_CONTRACT]
    ADD CONSTRAINT PK_RS_CONTRACT_RS_CONTRACT_SID PRIMARY KEY(RS_CONTRACT_SID)

GO

-----------------------------------COLUMN RENAME STARTS-------------------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'VALIDATION_PROFILE'
                  AND TABLE_NAME = 'RS_CONTRACT')
  BEGIN
      EXEC SP_RENAME
        'RS_CONTRACT.VALIDATION_PROFILE',
        'RS_VALIDATION_PROFILE',
        'COLUMN'
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'TRADE_CLASS'
                  AND TABLE_NAME = 'RS_CONTRACT')
  BEGIN
      EXEC SP_RENAME
        'RS_CONTRACT.TRADE_CLASS',
        'RS_TRADE_CLASS',
        'COLUMN'
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'CALENDAR'
                  AND TABLE_NAME = 'RS_CONTRACT')
  BEGIN
      EXEC SP_RENAME
        'RS_CONTRACT.CALENDAR',
        'RS_CALENDAR',
        'COLUMN'
  END

GO

-----------------------------COLUMN RENAME ENDS--------------------------------------------
-----------------DATATYPE CHANGE STARTS------------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RS_CONTRACT'
                  AND COLUMN_NAME = 'RS_CALENDAR')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ALTER COLUMN RS_CALENDAR INT
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RS_CONTRACT'
                  AND COLUMN_NAME = 'RS_DESIGNATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT
        ALTER COLUMN RS_DESIGNATION INT
  END

GO

-----------------DATATYPE CHANGE ENDS------------------------
-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT')
                      AND NAME = 'DF_RS_CONTRACT_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT]
        ADD CONSTRAINT [DF_RS_CONTRACT_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT')
                      AND NAME = 'DF_RS_CONTRACT_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT]
        ADD CONSTRAINT [DF_RS_CONTRACT_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT')
                      AND NAME = 'DF_RS_CONTRACT_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT]
        ADD CONSTRAINT [DF_RS_CONTRACT_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT')
                      AND NAME = 'DF_RS_CONTRACT_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT]
        ADD CONSTRAINT [DF_RS_CONTRACT_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT')
                      AND NAME = 'DF_RS_CONTRACT_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT]
        ADD CONSTRAINT [DF_RS_CONTRACT_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'RS_CONTRACT')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('RS_CONTRACT')
                            AND NAME = 'UQ_RS_CONTRACT_RS_MODEL_SID_IFP_CONTRACT_SID_CFP_CONTRACT_SID_CONTRACT_MASTER_SID_PS_CONTRACT_SID')
        BEGIN
            ALTER TABLE RS_CONTRACT
              ADD CONSTRAINT UQ_RS_CONTRACT_RS_MODEL_SID_IFP_CONTRACT_SID_CFP_CONTRACT_SID_CONTRACT_MASTER_SID_PS_CONTRACT_SID UNIQUE(RS_MODEL_SID, IFP_CONTRACT_SID, CFP_CONTRACT_SID, CONTRACT_MASTER_SID, PS_CONTRACT_SID)
        END
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_RS_CONTRACT]
        (
           RS_CONTRACT_SID             INT NOT NULL,
           RS_MODEL_SID                INT NOT NULL,
           RS_ID                       VARCHAR(50) NOT NULL,
           RS_NO                       VARCHAR(50) NOT NULL,
           RS_NAME                     VARCHAR(100) NULL,
           RS_TYPE                     INT NOT NULL,
           REBATE_PROGRAM_TYPE         INT NOT NULL,
           RS_CATEGORY                 INT NULL,
           RS_STATUS                   INT NULL,
           RS_DESIGNATION              INT NULL,
           RS_START_DATE               DATETIME NOT NULL,
           RS_END_DATE                 DATETIME NULL,
           RS_TRADE_CLASS              INT NULL,
           PARENT_RS_ID                VARCHAR(50) NULL,
           PARENT_RS_NAME              VARCHAR(100) NULL,
           CONTRACT_MASTER_SID         INT NOT NULL,
           CFP_CONTRACT_SID            INT NULL,
           IFP_CONTRACT_SID            INT NULL,
           PS_CONTRACT_SID             INT NULL,
           RS_CONTRACT_ATTACHED_STATUS INT NULL,
           RS_CONTRACT_ATTACHED_DATE   DATETIME,
           RS_TRANS_REF_ID             VARCHAR(50) NULL,
           RS_TRANS_REF_NO             VARCHAR(50) NULL,
           RS_TRANS_REF_NAME           VARCHAR(100) NULL,
           REBATE_RULE_TYPE            INT NULL,
           REBATE_RULE_ASSOCIATION     VARCHAR(100) NULL,
           REBATE_PLAN_LEVEL           VARCHAR(50) NULL,
           INTEREST_BEARING_INDICATOR  INT NULL,
           INTEREST_BEARING_BASIS      INT NULL,
           REBATE_PROCESSING_TYPE      INT NULL,
           REBATE_FREQUENCY            INT NOT NULL,
           PAYMENT_METHOD              INT NULL,
           PAYMENT_FREQUENCY           INT NULL,
           PAYMENT_TERMS               INT NULL,
           PAYMENT_GRACE_PERIOD        VARCHAR(25) NULL,
           RS_CALENDAR                 INT NULL,
           RS_VALIDATION_PROFILE       INT NULL,
           MAKE_PAYABLE_TO             VARCHAR(50) NULL,
           ADDRESS_1                   VARCHAR(100) NULL,
           ADDRESS_2                   VARCHAR(100) NULL,
           CITY                        VARCHAR(50) NULL,
           [STATE]                     INT NULL,
           ZIP_CODE                    VARCHAR(50) NULL,
           RS_ALIAS                    VARCHAR(100) NULL,
           FORMULA_METHOD_ID           VARCHAR(38) NULL,
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

----------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                      AND COLUMN_NAME = 'DEDUCTION_INCLUSION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ADD DEDUCTION_INCLUSION INT NULL
  END

GO

-----------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                      AND COLUMN_NAME = 'CALCULATION_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ADD CALCULATION_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                      AND COLUMN_NAME = 'CALCULATION_LEVEL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ADD CALCULATION_LEVEL INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                      AND COLUMN_NAME = 'CALCULATION_RULE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ADD CALCULATION_RULE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                      AND COLUMN_NAME = 'CALCULATION_RULE_LEVEL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ADD CALCULATION_RULE_LEVEL INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                      AND COLUMN_NAME = 'EVALUATION_RULE_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ADD EVALUATION_RULE_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                      AND COLUMN_NAME = 'EVALUATION_RULE_LEVEL'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ADD EVALUATION_RULE_LEVEL INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                      AND COLUMN_NAME = 'EVALUATION_RULE_OR_ASSOCIATION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ADD EVALUATION_RULE_OR_ASSOCIATION INT
  END

GO

------------------------ DROP STATISTICS -----------------------------
IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'CALENDAR'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.HIST_RS_CONTRACT.CALENDAR
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'RS_DESIGNATION'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.HIST_RS_CONTRACT.RS_DESIGNATION
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'REBATE_PLAN_LEVEL'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.HIST_RS_CONTRACT.REBATE_PLAN_LEVEL
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                  AND COLUMN_NAME = 'REBATE_PLAN_LEVEL'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ALTER COLUMN REBATE_PLAN_LEVEL INT
  END

GO

--------------------------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_RS_CONTRACT')
                      AND NAME = 'DF_HIST_RS_CONTRACT_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_RS_CONTRACT]
        ADD CONSTRAINT [DF_HIST_RS_CONTRACT_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

--------------------------COLUMN RENAME STARTS------------------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'VALIDATION_PROFILE'
                  AND TABLE_NAME = 'HIST_RS_CONTRACT')
  BEGIN
      EXEC SP_RENAME
        'HIST_RS_CONTRACT.VALIDATION_PROFILE',
        'RS_VALIDATION_PROFILE',
        'COLUMN'
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'TRADE_CLASS'
                  AND TABLE_NAME = 'HIST_RS_CONTRACT')
  BEGIN
      EXEC SP_RENAME
        'HIST_RS_CONTRACT.TRADE_CLASS',
        'RS_TRADE_CLASS',
        'COLUMN'
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'CALENDAR'
                  AND TABLE_NAME = 'HIST_RS_CONTRACT')
  BEGIN
      EXEC SP_RENAME
        'HIST_RS_CONTRACT.CALENDAR',
        'RS_CALENDAR',
        'COLUMN'
  END

GO

--------------------------COLUMN RENAME ENDS------------------------------
-----------------------DATATPE CHANGE STARTS----------------------------
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                  AND COLUMN_NAME = 'RS_CALENDAR')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ALTER COLUMN RS_CALENDAR INT
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                  AND COLUMN_NAME = 'RS_DESIGNATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ALTER COLUMN RS_DESIGNATION INT
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'INTEREST_BEARING_INDICATOR'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.HIST_RS_CONTRACT.INTEREST_BEARING_INDICATOR
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                  AND COLUMN_NAME = 'INTEREST_BEARING_INDICATOR'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ALTER COLUMN INTEREST_BEARING_INDICATOR INT
  END

GO

IF EXISTS (SELECT *
           FROM   SYS.STATS
           WHERE  NAME = 'INTEREST_BEARING_BASIS'
                  AND OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT')
  BEGIN
      DROP STATISTICS DBO.HIST_RS_CONTRACT.INTEREST_BEARING_BASIS
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RS_CONTRACT'
                  AND COLUMN_NAME = 'INTEREST_BEARING_BASIS'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT
        ALTER COLUMN INTEREST_BEARING_BASIS INT
  END

GO

-----------------------DATATPE CHANGE ENDS----------------------------
DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_RS_CONTRACT'--TABLE NAME
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

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'RS_CONTRACT'--TABLE NAME
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

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RS_CONTRACT_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_RS_CONTRACT_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_RS_CONTRACT_INS]
ON [DBO].[RS_CONTRACT]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
        INSERT INTO HIST_RS_CONTRACT
                    (RS_CONTRACT_SID,
                     RS_MODEL_SID,
                     RS_ID,
                     RS_NO,
                     RS_NAME,
                     RS_TYPE,
                     REBATE_PROGRAM_TYPE,
                     RS_CATEGORY,
                     RS_STATUS,
                     RS_DESIGNATION,
                     RS_START_DATE,
                     RS_END_DATE,
                     RS_TRADE_CLASS,
                     PARENT_RS_ID,
                     PARENT_RS_NAME,
                     CONTRACT_MASTER_SID,
                     CFP_CONTRACT_SID,
                     IFP_CONTRACT_SID,
                     PS_CONTRACT_SID,
                     RS_CONTRACT_ATTACHED_STATUS,
                     RS_CONTRACT_ATTACHED_DATE,
                     RS_TRANS_REF_ID,
                     RS_TRANS_REF_NO,
                     RS_TRANS_REF_NAME,
                     REBATE_RULE_TYPE,
                     REBATE_RULE_ASSOCIATION,
                     REBATE_PLAN_LEVEL,
                     INTEREST_BEARING_INDICATOR,
                     INTEREST_BEARING_BASIS,
                     REBATE_PROCESSING_TYPE,
                     REBATE_FREQUENCY,
                     PAYMENT_METHOD,
                     PAYMENT_FREQUENCY,
                     PAYMENT_TERMS,
                     PAYMENT_GRACE_PERIOD,
                     RS_CALENDAR,
                     RS_VALIDATION_PROFILE,
                     MAKE_PAYABLE_TO,
                     ADDRESS_1,
                     ADDRESS_2,
                     CITY,
                     STATE,
                     ZIP_CODE,
                     RS_ALIAS,
                     FORMULA_METHOD_ID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     CALCULATION_TYPE,
                     CALCULATION_LEVEL,
                     CALCULATION_RULE,
                     CALCULATION_RULE_LEVEL,
                     EVALUATION_RULE_TYPE,
                     EVALUATION_RULE_LEVEL,
                     EVALUATION_RULE_OR_ASSOCIATION,
                     DEDUCTION_INCLUSION,
                     ACTION_FLAG)
        SELECT RS_CONTRACT_SID,
               RS_MODEL_SID,
               RS_ID,
               RS_NO,
               RS_NAME,
               RS_TYPE,
               REBATE_PROGRAM_TYPE,
               RS_CATEGORY,
               RS_STATUS,
               RS_DESIGNATION,
               RS_START_DATE,
               RS_END_DATE,
               RS_TRADE_CLASS,
               PARENT_RS_ID,
               PARENT_RS_NAME,
               CONTRACT_MASTER_SID,
               CFP_CONTRACT_SID,
               IFP_CONTRACT_SID,
               PS_CONTRACT_SID,
               RS_CONTRACT_ATTACHED_STATUS,
               RS_CONTRACT_ATTACHED_DATE,
               RS_TRANS_REF_ID,
               RS_TRANS_REF_NO,
               RS_TRANS_REF_NAME,
               REBATE_RULE_TYPE,
               REBATE_RULE_ASSOCIATION,
               REBATE_PLAN_LEVEL,
               INTEREST_BEARING_INDICATOR,
               INTEREST_BEARING_BASIS,
               REBATE_PROCESSING_TYPE,
               REBATE_FREQUENCY,
               PAYMENT_METHOD,
               PAYMENT_FREQUENCY,
               PAYMENT_TERMS,
               PAYMENT_GRACE_PERIOD,
               RS_CALENDAR,
               RS_VALIDATION_PROFILE,
               MAKE_PAYABLE_TO,
               ADDRESS_1,
               ADDRESS_2,
               CITY,
               STATE,
               ZIP_CODE,
               RS_ALIAS,
               FORMULA_METHOD_ID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               CALCULATION_TYPE,
               CALCULATION_LEVEL,
               CALCULATION_RULE,
               CALCULATION_RULE_LEVEL,
               EVALUATION_RULE_TYPE,
               EVALUATION_RULE_LEVEL,
               EVALUATION_RULE_OR_ASSOCIATION,
               DEDUCTION_INCLUSION,
               'A'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RS_CONTRACT_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_RS_CONTRACT_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_RS_CONTRACT_UPD]
ON [DBO].[RS_CONTRACT]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   INSERTED)
         AND EXISTS (SELECT *
                     FROM   DELETED)
        INSERT INTO HIST_RS_CONTRACT
                    (RS_CONTRACT_SID,
                     RS_MODEL_SID,
                     RS_ID,
                     RS_NO,
                     RS_NAME,
                     RS_TYPE,
                     REBATE_PROGRAM_TYPE,
                     RS_CATEGORY,
                     RS_STATUS,
                     RS_DESIGNATION,
                     RS_START_DATE,
                     RS_END_DATE,
                     RS_TRADE_CLASS,
                     PARENT_RS_ID,
                     PARENT_RS_NAME,
                     CONTRACT_MASTER_SID,
                     CFP_CONTRACT_SID,
                     IFP_CONTRACT_SID,
                     PS_CONTRACT_SID,
                     RS_CONTRACT_ATTACHED_STATUS,
                     RS_CONTRACT_ATTACHED_DATE,
                     RS_TRANS_REF_ID,
                     RS_TRANS_REF_NO,
                     RS_TRANS_REF_NAME,
                     REBATE_RULE_TYPE,
                     REBATE_RULE_ASSOCIATION,
                     REBATE_PLAN_LEVEL,
                     INTEREST_BEARING_INDICATOR,
                     INTEREST_BEARING_BASIS,
                     REBATE_PROCESSING_TYPE,
                     REBATE_FREQUENCY,
                     PAYMENT_METHOD,
                     PAYMENT_FREQUENCY,
                     PAYMENT_TERMS,
                     PAYMENT_GRACE_PERIOD,
                     RS_CALENDAR,
                     RS_VALIDATION_PROFILE,
                     MAKE_PAYABLE_TO,
                     ADDRESS_1,
                     ADDRESS_2,
                     CITY,
                     STATE,
                     ZIP_CODE,
                     RS_ALIAS,
                     FORMULA_METHOD_ID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     CALCULATION_TYPE,
                     CALCULATION_LEVEL,
                     CALCULATION_RULE,
                     CALCULATION_RULE_LEVEL,
                     EVALUATION_RULE_TYPE,
                     EVALUATION_RULE_LEVEL,
                     EVALUATION_RULE_OR_ASSOCIATION,
                     DEDUCTION_INCLUSION,
                     ACTION_FLAG)
        SELECT RS_CONTRACT_SID,
               RS_MODEL_SID,
               RS_ID,
               RS_NO,
               RS_NAME,
               RS_TYPE,
               REBATE_PROGRAM_TYPE,
               RS_CATEGORY,
               RS_STATUS,
               RS_DESIGNATION,
               RS_START_DATE,
               RS_END_DATE,
               RS_TRADE_CLASS,
               PARENT_RS_ID,
               PARENT_RS_NAME,
               CONTRACT_MASTER_SID,
               CFP_CONTRACT_SID,
               IFP_CONTRACT_SID,
               PS_CONTRACT_SID,
               RS_CONTRACT_ATTACHED_STATUS,
               RS_CONTRACT_ATTACHED_DATE,
               RS_TRANS_REF_ID,
               RS_TRANS_REF_NO,
               RS_TRANS_REF_NAME,
               REBATE_RULE_TYPE,
               REBATE_RULE_ASSOCIATION,
               REBATE_PLAN_LEVEL,
               INTEREST_BEARING_INDICATOR,
               INTEREST_BEARING_BASIS,
               REBATE_PROCESSING_TYPE,
               REBATE_FREQUENCY,
               PAYMENT_METHOD,
               PAYMENT_FREQUENCY,
               PAYMENT_TERMS,
               PAYMENT_GRACE_PERIOD,
               RS_CALENDAR,
               RS_VALIDATION_PROFILE,
               MAKE_PAYABLE_TO,
               ADDRESS_1,
               ADDRESS_2,
               CITY,
               STATE,
               ZIP_CODE,
               RS_ALIAS,
               FORMULA_METHOD_ID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               CALCULATION_TYPE,
               CALCULATION_LEVEL,
               CALCULATION_RULE,
               CALCULATION_RULE_LEVEL,
               EVALUATION_RULE_TYPE,
               EVALUATION_RULE_LEVEL,
               EVALUATION_RULE_OR_ASSOCIATION,
               DEDUCTION_INCLUSION,
               'C'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RS_CONTRACT_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_RS_CONTRACT_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_RS_CONTRACT_DEL]
ON [DBO].[RS_CONTRACT]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS (SELECT *
                 FROM   DELETED)
        INSERT INTO HIST_RS_CONTRACT
                    (RS_CONTRACT_SID,
                     RS_MODEL_SID,
                     RS_ID,
                     RS_NO,
                     RS_NAME,
                     RS_TYPE,
                     REBATE_PROGRAM_TYPE,
                     RS_CATEGORY,
                     RS_STATUS,
                     RS_DESIGNATION,
                     RS_START_DATE,
                     RS_END_DATE,
                     RS_TRADE_CLASS,
                     PARENT_RS_ID,
                     PARENT_RS_NAME,
                     CONTRACT_MASTER_SID,
                     CFP_CONTRACT_SID,
                     IFP_CONTRACT_SID,
                     PS_CONTRACT_SID,
                     RS_CONTRACT_ATTACHED_STATUS,
                     RS_CONTRACT_ATTACHED_DATE,
                     RS_TRANS_REF_ID,
                     RS_TRANS_REF_NO,
                     RS_TRANS_REF_NAME,
                     REBATE_RULE_TYPE,
                     REBATE_RULE_ASSOCIATION,
                     REBATE_PLAN_LEVEL,
                     INTEREST_BEARING_INDICATOR,
                     INTEREST_BEARING_BASIS,
                     REBATE_PROCESSING_TYPE,
                     REBATE_FREQUENCY,
                     PAYMENT_METHOD,
                     PAYMENT_FREQUENCY,
                     PAYMENT_TERMS,
                     PAYMENT_GRACE_PERIOD,
                     RS_CALENDAR,
                     RS_VALIDATION_PROFILE,
                     MAKE_PAYABLE_TO,
                     ADDRESS_1,
                     ADDRESS_2,
                     CITY,
                     STATE,
                     ZIP_CODE,
                     RS_ALIAS,
                     FORMULA_METHOD_ID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     CALCULATION_TYPE,
                     CALCULATION_LEVEL,
                     CALCULATION_RULE,
                     CALCULATION_RULE_LEVEL,
                     EVALUATION_RULE_TYPE,
                     EVALUATION_RULE_LEVEL,
                     EVALUATION_RULE_OR_ASSOCIATION,
                     DEDUCTION_INCLUSION,
                     ACTION_FLAG)
        SELECT RS_CONTRACT_SID,
               RS_MODEL_SID,
               RS_ID,
               RS_NO,
               RS_NAME,
               RS_TYPE,
               REBATE_PROGRAM_TYPE,
               RS_CATEGORY,
               RS_STATUS,
               RS_DESIGNATION,
               RS_START_DATE,
               RS_END_DATE,
               RS_TRADE_CLASS,
               PARENT_RS_ID,
               PARENT_RS_NAME,
               CONTRACT_MASTER_SID,
               CFP_CONTRACT_SID,
               IFP_CONTRACT_SID,
               PS_CONTRACT_SID,
               RS_CONTRACT_ATTACHED_STATUS,
               RS_CONTRACT_ATTACHED_DATE,
               RS_TRANS_REF_ID,
               RS_TRANS_REF_NO,
               RS_TRANS_REF_NAME,
               REBATE_RULE_TYPE,
               REBATE_RULE_ASSOCIATION,
               REBATE_PLAN_LEVEL,
               INTEREST_BEARING_INDICATOR,
               INTEREST_BEARING_BASIS,
               REBATE_PROCESSING_TYPE,
               REBATE_FREQUENCY,
               PAYMENT_METHOD,
               PAYMENT_FREQUENCY,
               PAYMENT_TERMS,
               PAYMENT_GRACE_PERIOD,
               RS_CALENDAR,
               RS_VALIDATION_PROFILE,
               MAKE_PAYABLE_TO,
               ADDRESS_1,
               ADDRESS_2,
               CITY,
               STATE,
               ZIP_CODE,
               RS_ALIAS,
               FORMULA_METHOD_ID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               CALCULATION_TYPE,
               CALCULATION_LEVEL,
               CALCULATION_RULE,
               CALCULATION_RULE_LEVEL,
               EVALUATION_RULE_TYPE,
               EVALUATION_RULE_LEVEL,
               EVALUATION_RULE_OR_ASSOCIATION,
               DEDUCTION_INCLUSION,
               'D'
        FROM   DELETED
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT_DETAILS'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[RS_CONTRACT_DETAILS]
        (
           RS_CONTRACT_DETAILS_SID     INT NOT NULL IDENTITY(1, 1),
           RS_CONTRACT_SID             INT NOT NULL,
           ITEM_MASTER_SID             INT NOT NULL,
           ITEM_REBATE_START_DATE      DATETIME NOT NULL,
           ITEM_REBATE_END_DATE        DATETIME NULL,
           FORMULA_ID                  INT NULL,
           REBATE_PLAN_MASTER_SID      INT NULL,
           RS_CONTRACT_ATTACHED_STATUS INT NULL,
           RS_CONTRACT_ATTACHED_DATE   DATETIME NULL,
           REBATE_AMOUNT               NUMERIC(22, 6) NULL,
           BUNDLE_NO                   VARCHAR(25) NULL,
           FORMULA_METHOD_ID           VARCHAR(50) NULL,
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
               WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'DEDUCTION_CALENDAR_MASTER_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ADD DEDUCTION_CALENDAR_MASTER_SID INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_SALES_FORMULA_MASTER_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ADD NET_SALES_FORMULA_MASTER_SID INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'FORMULA_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ADD FORMULA_TYPE NUMERIC(22, 6)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_SALES_RULE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ADD NET_SALES_RULE INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'EVALUATION_RULE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ADD EVALUATION_RULE INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'EVALUATION_RULE_BUNDLE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ADD EVALUATION_RULE_BUNDLE NUMERIC(22, 6)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'CALCULATION_RULE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ADD CALCULATION_RULE INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'CALCULATION_RULE_BUNDLE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ADD CALCULATION_RULE_BUNDLE NUMERIC(22, 6)
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'EVALUATION_RULE_BUNDLE')
  BEGIN
      DROP STATISTICS RS_CONTRACT_DETAILS.EVALUATION_RULE_BUNDLE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'EVALUATION_RULE_BUNDLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ALTER COLUMN EVALUATION_RULE_BUNDLE VARCHAR(100) NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CALCULATION_RULE_BUNDLE')
  BEGIN
      DROP STATISTICS RS_CONTRACT_DETAILS.CALCULATION_RULE_BUNDLE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'CALCULATION_RULE_BUNDLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ALTER COLUMN CALCULATION_RULE_BUNDLE VARCHAR(100) NULL
  END

GO
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'EVALUATION_RULE_BUNDLE')
  BEGIN
      DROP STATISTICS RS_CONTRACT_DETAILS.EVALUATION_RULE_BUNDLE
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'EVALUATION_RULE_BUNDLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ALTER COLUMN EVALUATION_RULE_BUNDLE varchar(100) NULL
  END
GO



IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CALCULATION_RULE_BUNDLE')
  BEGIN
      DROP STATISTICS RS_CONTRACT_DETAILS.CALCULATION_RULE_BUNDLE
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'RS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'CALCULATION_RULE_BUNDLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE RS_CONTRACT_DETAILS
        ALTER COLUMN CALCULATION_RULE_BUNDLE varchar(100) NULL
  END
GO


---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'RS_CONTRACT_DETAILS'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_RS_CONTRACT_DETAILS_RS_CONTRACT_DETAILS_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [DBO].[RS_CONTRACT_DETAILS]
    ADD CONSTRAINT PK_RS_CONTRACT_DETAILS_RS_CONTRACT_DETAILS_SID PRIMARY KEY(RS_CONTRACT_DETAILS_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'RS_CONTRACT_DETAILS')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = OBJECT_ID('RS_CONTRACT_DETAILS')
                            AND NAME = 'UQ_RS_CONTRACT_DETAILS_RS_CONTRACT_SID_ITEM_MASTER_SID_ITEM_REBATE_START_DATE')
        BEGIN
            ALTER TABLE RS_CONTRACT_DETAILS
              ADD CONSTRAINT UQ_RS_CONTRACT_DETAILS_RS_CONTRACT_SID_ITEM_MASTER_SID_ITEM_REBATE_START_DATE UNIQUE(RS_CONTRACT_SID, ITEM_MASTER_SID, ITEM_REBATE_START_DATE)
        END
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT_DETAILS'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_RS_CONTRACT_DETAILS]
        (
           RS_CONTRACT_DETAILS_SID     INT NOT NULL,
           RS_CONTRACT_SID             INT NOT NULL,
           ITEM_MASTER_SID             INT NOT NULL,
           ITEM_REBATE_START_DATE      DATETIME NOT NULL,
           ITEM_REBATE_END_DATE        DATETIME NULL,
           FORMULA_ID                  INT NULL,
           REBATE_PLAN_MASTER_SID      INT NULL,
           RS_CONTRACT_ATTACHED_STATUS INT NULL,
           RS_CONTRACT_ATTACHED_DATE   DATETIME NULL,
           REBATE_AMOUNT               NUMERIC(22, 6) NULL,
           BUNDLE_NO                   VARCHAR(25) NULL,
           FORMULA_METHOD_ID           VARCHAR(50) NULL,
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
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'DEDUCTION_CALENDAR_MASTER_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ADD DEDUCTION_CALENDAR_MASTER_SID INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_SALES_FORMULA_MASTER_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ADD NET_SALES_FORMULA_MASTER_SID INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'FORMULA_TYPE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ADD FORMULA_TYPE NUMERIC(22, 6)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'NET_SALES_RULE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ADD NET_SALES_RULE INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'EVALUATION_RULE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ADD EVALUATION_RULE INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'EVALUATION_RULE_BUNDLE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ADD EVALUATION_RULE_BUNDLE NUMERIC(22, 6)
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'CALCULATION_RULE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ADD CALCULATION_RULE INT
  END

GO

-------------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                      AND COLUMN_NAME = 'CALCULATION_RULE_BUNDLE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ADD CALCULATION_RULE_BUNDLE NUMERIC(22, 6)
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'EVALUATION_RULE_BUNDLE')
  BEGIN
      DROP STATISTICS HIST_RS_CONTRACT_DETAILS.EVALUATION_RULE_BUNDLE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'EVALUATION_RULE_BUNDLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ALTER COLUMN EVALUATION_RULE_BUNDLE VARCHAR(100) NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CALCULATION_RULE_BUNDLE')
  BEGIN
      DROP STATISTICS HIST_RS_CONTRACT_DETAILS.CALCULATION_RULE_BUNDLE
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'CALCULATION_RULE_BUNDLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ALTER COLUMN CALCULATION_RULE_BUNDLE VARCHAR(100) NULL
  END

GO
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'EVALUATION_RULE_BUNDLE')
  BEGIN
      DROP STATISTICS HIST_RS_CONTRACT_DETAILS.EVALUATION_RULE_BUNDLE
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'EVALUATION_RULE_BUNDLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ALTER COLUMN EVALUATION_RULE_BUNDLE varchar(100) NULL
  END
GO



IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT_DETAILS'
                  AND AUTO_CREATED = 0
                  AND NAME = 'CALCULATION_RULE_BUNDLE')
  BEGIN
      DROP STATISTICS HIST_RS_CONTRACT_DETAILS.CALCULATION_RULE_BUNDLE
  END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_RS_CONTRACT_DETAILS'
                  AND COLUMN_NAME = 'CALCULATION_RULE_BUNDLE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_RS_CONTRACT_DETAILS
        ALTER COLUMN CALCULATION_RULE_BUNDLE varchar(100) NULL
  END
GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_RS_CONTRACT_DETAILS')
                      AND NAME = 'DF_HIST_RS_CONTRACT_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_RS_CONTRACT_DETAILS]
        ADD CONSTRAINT [DF_HIST_RS_CONTRACT_DETAILS_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_RS_CONTRACT_DETAILS'--TABLE NAME
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

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'RS_CONTRACT_DETAILS'--TABLE NAME
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

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RS_CONTRACT_DETAILS_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_RS_CONTRACT_DETAILS_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_RS_CONTRACT_DETAILS_UPD]
ON [DBO].[RS_CONTRACT_DETAILS]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_RS_CONTRACT_DETAILS
                    (RS_CONTRACT_DETAILS_SID,
                     RS_CONTRACT_SID,
                     ITEM_MASTER_SID,
                     ITEM_REBATE_START_DATE,
                     ITEM_REBATE_END_DATE,
                     FORMULA_ID,
                     REBATE_PLAN_MASTER_SID,
                     RS_CONTRACT_ATTACHED_STATUS,
                     RS_CONTRACT_ATTACHED_DATE,
                     REBATE_AMOUNT,
                     BUNDLE_NO,
                     FORMULA_METHOD_ID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     DEDUCTION_CALENDAR_MASTER_SID,
                     NET_SALES_FORMULA_MASTER_SID,
                     FORMULA_TYPE,
                     NET_SALES_RULE,
                     EVALUATION_RULE,
                     EVALUATION_RULE_BUNDLE,
                     CALCULATION_RULE,
                     CALCULATION_RULE_BUNDLE,
                     ACTION_FLAG)
        SELECT RS_CONTRACT_DETAILS_SID,
               RS_CONTRACT_SID,
               ITEM_MASTER_SID,
               ITEM_REBATE_START_DATE,
               ITEM_REBATE_END_DATE,
               FORMULA_ID,
               REBATE_PLAN_MASTER_SID,
               RS_CONTRACT_ATTACHED_STATUS,
               RS_CONTRACT_ATTACHED_DATE,
               REBATE_AMOUNT,
               BUNDLE_NO,
               FORMULA_METHOD_ID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               DEDUCTION_CALENDAR_MASTER_SID,
               NET_SALES_FORMULA_MASTER_SID,
               FORMULA_TYPE,
               NET_SALES_RULE,
               EVALUATION_RULE,
               EVALUATION_RULE_BUNDLE,
               CALCULATION_RULE,
               CALCULATION_RULE_BUNDLE,
               'C'
        FROM   INSERTED
  END

GO

-----TRG_RS_CONTRACT_DETAILS_INS---
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RS_CONTRACT_DETAILS_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_RS_CONTRACT_DETAILS_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_RS_CONTRACT_DETAILS_INS]
ON [DBO].[RS_CONTRACT_DETAILS]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_RS_CONTRACT_DETAILS
                    (RS_CONTRACT_DETAILS_SID,
                     RS_CONTRACT_SID,
                     ITEM_MASTER_SID,
                     ITEM_REBATE_START_DATE,
                     ITEM_REBATE_END_DATE,
                     FORMULA_ID,
                     REBATE_PLAN_MASTER_SID,
                     RS_CONTRACT_ATTACHED_STATUS,
                     RS_CONTRACT_ATTACHED_DATE,
                     REBATE_AMOUNT,
                     BUNDLE_NO,
                     FORMULA_METHOD_ID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     DEDUCTION_CALENDAR_MASTER_SID,
                     NET_SALES_FORMULA_MASTER_SID,
                     FORMULA_TYPE,
                     NET_SALES_RULE,
                     EVALUATION_RULE,
                     EVALUATION_RULE_BUNDLE,
                     CALCULATION_RULE,
                     CALCULATION_RULE_BUNDLE,
                     ACTION_FLAG)
        SELECT RS_CONTRACT_DETAILS_SID,
               RS_CONTRACT_SID,
               ITEM_MASTER_SID,
               ITEM_REBATE_START_DATE,
               ITEM_REBATE_END_DATE,
               FORMULA_ID,
               REBATE_PLAN_MASTER_SID,
               RS_CONTRACT_ATTACHED_STATUS,
               RS_CONTRACT_ATTACHED_DATE,
               REBATE_AMOUNT,
               BUNDLE_NO,
               FORMULA_METHOD_ID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               DEDUCTION_CALENDAR_MASTER_SID,
               NET_SALES_FORMULA_MASTER_SID,
               FORMULA_TYPE,
               NET_SALES_RULE,
               EVALUATION_RULE,
               EVALUATION_RULE_BUNDLE,
               CALCULATION_RULE,
               CALCULATION_RULE_BUNDLE,
               'A'
        FROM   INSERTED
  END

GO

-----TRG_RS_CONTRACT_DETAILS_DEL---
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RS_CONTRACT_DETAILS_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_RS_CONTRACT_DETAILS_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_RS_CONTRACT_DETAILS_DEL]
ON [DBO].[RS_CONTRACT_DETAILS]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_RS_CONTRACT_DETAILS
                    (RS_CONTRACT_DETAILS_SID,
                     RS_CONTRACT_SID,
                     ITEM_MASTER_SID,
                     ITEM_REBATE_START_DATE,
                     ITEM_REBATE_END_DATE,
                     FORMULA_ID,
                     REBATE_PLAN_MASTER_SID,
                     RS_CONTRACT_ATTACHED_STATUS,
                     RS_CONTRACT_ATTACHED_DATE,
                     REBATE_AMOUNT,
                     BUNDLE_NO,
                     FORMULA_METHOD_ID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     SOURCE,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     DEDUCTION_CALENDAR_MASTER_SID,
                     NET_SALES_FORMULA_MASTER_SID,
                     FORMULA_TYPE,
                     NET_SALES_RULE,
                     EVALUATION_RULE,
                     EVALUATION_RULE_BUNDLE,
                     CALCULATION_RULE,
                     CALCULATION_RULE_BUNDLE,
                     ACTION_FLAG)
        SELECT RS_CONTRACT_DETAILS_SID,
               RS_CONTRACT_SID,
               ITEM_MASTER_SID,
               ITEM_REBATE_START_DATE,
               ITEM_REBATE_END_DATE,
               FORMULA_ID,
               REBATE_PLAN_MASTER_SID,
               RS_CONTRACT_ATTACHED_STATUS,
               RS_CONTRACT_ATTACHED_DATE,
               REBATE_AMOUNT,
               BUNDLE_NO,
               FORMULA_METHOD_ID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               SOURCE,
               CREATED_BY,
               CREATED_DATE,
               MODIFIED_BY,
               MODIFIED_DATE,
               DEDUCTION_CALENDAR_MASTER_SID,
               NET_SALES_FORMULA_MASTER_SID,
               FORMULA_TYPE,
               NET_SALES_RULE,
               EVALUATION_RULE,
               EVALUATION_RULE_BUNDLE,
               CALCULATION_RULE,
               CALCULATION_RULE_BUNDLE,
               'D'
        FROM   DELETED
  END

GO


IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RS_CONTRACT_DTLS_DYNAMIC_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_RS_CONTRACT_DTLS_DYNAMIC_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_RS_CONTRACT_DTLS_DYNAMIC_INS]
ON [dbo].[RS_CONTRACT_DETAILS]
AFTER INSERT
AS
  BEGIN
      SET NOCOUNT ON

      IF Object_id('tempdb..#TEMP_DEDUCTION_HIERARCHY') IS NOT NULL
        DROP TABLE #TEMP_DEDUCTION_HIERARCHY

      CREATE TABLE #TEMP_DEDUCTION_HIERARCHY
        (
           ITEM_MASTER_SID          INT,
           RS_CONTRACT_SID          INT,
           REBATE_SCHEDULE_CATEGORY INT,
           REBATE_SCHEDULE_TYPE     INT,
           REBATE_PROGRAM_TYPE      INT,
           UDC1                     INT,
           UDC2                     INT,
           UDC3                     INT,
           UDC4                     INT,
           UDC5                     INT,
           UDC6                     INT
        )

      INSERT INTO #TEMP_DEDUCTION_HIERARCHY
                  (ITEM_MASTER_SID,
                   RS_CONTRACT_SID,
                   REBATE_SCHEDULE_CATEGORY,
                   REBATE_SCHEDULE_TYPE,
                   REBATE_PROGRAM_TYPE,
                   UDC1,
                   UDC2,
                   UDC3,
                   UDC4,
                   UDC5,
                   UDC6)
      SELECT a.ITEM_MASTER_SID,
             A.RS_CONTRACT_SID,
             rs.RS_CATEGORY,
             rs.RS_TYPE,
             rs.REBATE_PROGRAM_TYPE,
             U.UDC1,
             U.UDC2,
             U.UDC3,
             U.UDC4,
             U.UDC5,
             U.UDC6
      FROM   INSERTED A
             JOIN RS_CONTRACT rs
               ON rs.RS_CONTRACT_SID = a.RS_CONTRACT_SID AND RS.INBOUND_STATUS<>'D'
             LEFT JOIN UDCS U
                    ON U.MASTER_SID = A.RS_CONTRACT_SID AND U.MASTER_TYPE='RS_CONTRACT'

      -------------------------Procedure Call ---------------------------------------
      IF @@ROWCOUNT > 0
        BEGIN
            BEGIN TRY
                BEGIN TRANSACTION

                EXEC Prc_deduction_hierarchy_dynamic_add --DEDUCTION
                COMMIT
            END TRY

            BEGIN CATCH
                IF @@TRANCOUNT <> 0
                  ROLLBACK
            END CATCH
        END
  END
GO



-----------------------------------------------RS_CONTRACT_DETAILS_FR------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'RS_CONTRACT_DETAILS_FR'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[RS_CONTRACT_DETAILS_FR]
        (
           RS_CONTRACT_DETAILS_FR_SID INT NOT NULL IDENTITY(1, 1),
           RS_CONTRACT_DETAILS_SID    INT NOT NULL,
           ITEM_MASTER_SID            INT NOT NULL,
           FORMULA_ID                 INT NULL,
           FORMULA_METHOD_ID          VARCHAR(50) NULL,
           INBOUND_STATUS             CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS         BIT NOT NULL,
           BATCH_ID                   VARCHAR(50) NULL,
           [SOURCE]                   VARCHAR(50) NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL
        )
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'RS_CONTRACT_DETAILS_FR'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_RS_CONTRACT_DETAILS_FR_RS_CONTRACT_DETAILS_FR_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [DBO].[RS_CONTRACT_DETAILS_FR]
    ADD CONSTRAINT PK_RS_CONTRACT_DETAILS_FR_RS_CONTRACT_DETAILS_FR_SID PRIMARY KEY(RS_CONTRACT_DETAILS_FR_SID)

GO

-------------------DEFAULT CONSTRAINT ----------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS_FR')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_FR_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS_FR]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_FR_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS_FR')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_FR_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS_FR]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_FR_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS_FR')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_FR_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS_FR]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_FR_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS_FR')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_FR_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS_FR]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_FR_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS_FR')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_FR_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS_FR]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_FR_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

-----------------------------------------HIST_RS_CONTRACT_DETAILS_FR-----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'HIST_RS_CONTRACT_DETAILS_FR'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[HIST_RS_CONTRACT_DETAILS_FR]
        (
           RS_CONTRACT_DETAILS_FR_SID INT NOT NULL,
           RS_CONTRACT_DETAILS_SID    INT NOT NULL,
           ITEM_MASTER_SID            INT NOT NULL,
           FORMULA_ID                 INT NULL,
           FORMULA_METHOD_ID          VARCHAR(50) NULL,
           INBOUND_STATUS             CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS         BIT NOT NULL,
           BATCH_ID                   VARCHAR(50) NULL,
           [SOURCE]                   VARCHAR(50) NULL,
           [CREATED_BY]               INT NOT NULL,
           [CREATED_DATE]             DATETIME NOT NULL,
           [MODIFIED_BY]              INT NOT NULL,
           [MODIFIED_DATE]            DATETIME NOT NULL,
           ACTION_FLAG                CHAR(1) NOT NULL,
           ACTION_DATE                DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.HIST_RS_CONTRACT_DETAILS_FR')
                      AND NAME = 'DF_HIST_RS_CONTRACT_DETAILS_FR_ACTION_DATE')
  BEGIN
      ALTER TABLE [DBO].[HIST_RS_CONTRACT_DETAILS_FR]
        ADD CONSTRAINT [DF_HIST_RS_CONTRACT_DETAILS_FR_ACTION_DATE] DEFAULT (GETDATE()) FOR ACTION_DATE
  END

GO

---------------------TRIGGERS--------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RS_CONTRACT_DETAILS_FR_UPD')
  BEGIN
      DROP TRIGGER DBO.TRG_RS_CONTRACT_DETAILS_FR_UPD
  END

GO

CREATE TRIGGER [DBO].[TRG_RS_CONTRACT_DETAILS_FR_UPD]
ON [DBO].[RS_CONTRACT_DETAILS_FR]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_RS_CONTRACT_DETAILS_FR
                    (RS_CONTRACT_DETAILS_FR_SID,
                     RS_CONTRACT_DETAILS_SID,
                     ITEM_MASTER_SID,
                     FORMULA_ID,
                     FORMULA_METHOD_ID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     [CREATED_BY],
                     [CREATED_DATE],
                     [MODIFIED_BY],
                     [MODIFIED_DATE],
                     ACTION_FLAG)
        SELECT RS_CONTRACT_DETAILS_FR_SID,
               RS_CONTRACT_DETAILS_SID,
               ITEM_MASTER_SID,
               FORMULA_ID,
               FORMULA_METHOD_ID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               [CREATED_BY],
               [CREATED_DATE],
               [MODIFIED_BY],
               [MODIFIED_DATE],
               'C'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RS_CONTRACT_DETAILS_FR_INS')
  BEGIN
      DROP TRIGGER DBO.TRG_RS_CONTRACT_DETAILS_FR_INS
  END

GO

CREATE TRIGGER [DBO].[TRG_RS_CONTRACT_DETAILS_FR_INS]
ON [DBO].[RS_CONTRACT_DETAILS_FR]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_RS_CONTRACT_DETAILS_FR
                    (RS_CONTRACT_DETAILS_FR_SID,
                     RS_CONTRACT_DETAILS_SID,
                     ITEM_MASTER_SID,
                     FORMULA_ID,
                     FORMULA_METHOD_ID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     [CREATED_BY],
                     [CREATED_DATE],
                     [MODIFIED_BY],
                     [MODIFIED_DATE],
                     ACTION_FLAG)
        SELECT RS_CONTRACT_DETAILS_FR_SID,
               RS_CONTRACT_DETAILS_SID,
               ITEM_MASTER_SID,
               FORMULA_ID,
               FORMULA_METHOD_ID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               [CREATED_BY],
               [CREATED_DATE],
               [MODIFIED_BY],
               [MODIFIED_DATE],
               'A'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [NAME] = N'TRG_RS_CONTRACT_DETAILS_FR_DEL')
  BEGIN
      DROP TRIGGER DBO.TRG_RS_CONTRACT_DETAILS_FR_DEL
  END

GO

CREATE TRIGGER [DBO].[TRG_RS_CONTRACT_DETAILS_FR_DEL]
ON [DBO].[RS_CONTRACT_DETAILS_FR]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_RS_CONTRACT_DETAILS_FR
                    (RS_CONTRACT_DETAILS_FR_SID,
                     RS_CONTRACT_DETAILS_SID,
                     ITEM_MASTER_SID,
                     FORMULA_ID,
                     FORMULA_METHOD_ID,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     [CREATED_BY],
                     [CREATED_DATE],
                     [MODIFIED_BY],
                     [MODIFIED_DATE],
                     ACTION_FLAG)
        SELECT RS_CONTRACT_DETAILS_FR_SID,
               RS_CONTRACT_DETAILS_SID,
               ITEM_MASTER_SID,
               FORMULA_ID,
               FORMULA_METHOD_ID,
               INBOUND_STATUS,
               RECORD_LOCK_STATUS,
               BATCH_ID,
               [SOURCE],
               [CREATED_BY],
               [CREATED_DATE],
               [MODIFIED_BY],
               [MODIFIED_DATE],
               'D'
        FROM   DELETED
  END

GO

-------------------------------------------------IMTD_RS_CONTRACT_DETAILS_FR----------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  OBJECT_NAME(OBJECT_ID) = 'IMTD_RS_CONTRACT_DETAILS_FR'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[IMTD_RS_CONTRACT_DETAILS_FR]
        (
           IMTD_RS_CONTRACT_DETAILS_FR_SID    INT NOT NULL IDENTITY(1, 1),
           IMTD_ITEM_PRICE_REBATE_DETAILS_SID INT,
           RS_CONTRACT_DETAILS_FR_SID         INT,
           RS_CONTRACT_DETAILS_SID            INT NULL,
           ITEM_MASTER_SID                    INT NULL,
           FORMULA_ID                         INT NULL,
           FORMULA_METHOD_ID                  VARCHAR(50) NULL,
           INBOUND_STATUS                     CHAR(1) NULL,
           RECORD_LOCK_STATUS                 BIT NULL,
           IMTD_CREATED_DATE                  DATETIME NULL,
           OPERATION                          CHAR(1),
           BATCH_ID                           VARCHAR(50) NULL,
           [SOURCE]                           VARCHAR(50) NULL,
           [CREATED_BY]                       INT NULL,
           [CREATED_DATE]                     DATETIME NULL,
           [MODIFIED_BY]                      INT NULL,
           [MODIFIED_DATE]                    DATETIME NULL,
           USERS_ID                           VARCHAR(50) NULL,
           SESSION_ID                         VARCHAR(100) NULL
        )
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'IMTD_RS_CONTRACT_DETAILS_FR'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_IMTD_RS_CONTRACT_DETAILS_FR_IMTD_RS_CONTRACT_DETAILS_FR_SID'
                     AND TYPE = 'PK')
  ALTER TABLE [DBO].[IMTD_RS_CONTRACT_DETAILS_FR]
    ADD CONSTRAINT PK_IMTD_RS_CONTRACT_DETAILS_FR_IMTD_RS_CONTRACT_DETAILS_FR_SID PRIMARY KEY(IMTD_RS_CONTRACT_DETAILS_FR_SID)

GO 
----------------------------------------------RS_CONTRACT_DETAILS_PENDING-----------------------------------------------
IF NOT EXISTS (
                                SELECT 'X'
                                FROM INFORMATION_SCHEMA.TABLES
                                WHERE TABLE_NAME = 'RS_CONTRACT_DETAILS_PENDING'
                                                AND TABLE_SCHEMA = 'DBO'
                                )
BEGIN
                CREATE TABLE [DBO].[RS_CONTRACT_DETAILS_PENDING] (
                                RS_CONTRACT_DETAILS_PENDING_SID INT IDENTITY(1,1) NOT NULL,
                RS_CONTRACT_SID INT NOT NULL,
                ITEM_MASTER_SID INT NOT NULL,
                ITEM_REBATE_START_DATE DATETIME NOT NULL,
                ITEM_REBATE_END_DATE DATETIME NULL,
                FORMULA_ID INT NULL,
                REBATE_PLAN_MASTER_SID INT NULL,
                RS_CONTRACT_ATTACHED_STATUS INT NULL,
                RS_CONTRACT_ATTACHED_DATE DATETIME NULL,
                REBATE_AMOUNT NUMERIC(22, 6) NULL,
                BUNDLE_NO VARCHAR(25) NULL,
                FORMULA_METHOD_ID VARCHAR(50) NULL,
                INBOUND_STATUS CHAR(1) NOT NULL,
                RECORD_LOCK_STATUS BIT NOT NULL,
                BATCH_ID VARCHAR(50) NULL,
                SOURCE VARCHAR(50) NULL,
                CREATED_BY INT NOT NULL,
                CREATED_DATE DATETIME NOT NULL,
                MODIFIED_BY INT NOT NULL,
                MODIFIED_DATE DATETIME NOT NULL,
                DEDUCTION_CALENDAR_MASTER_SID INT NULL,
                NET_SALES_FORMULA_MASTER_SID INT NULL,
                FORMULA_TYPE NUMERIC(22, 6) NULL,
                NET_SALES_RULE INT NULL,
                EVALUATION_RULE INT NULL,
                EVALUATION_RULE_BUNDLE VARCHAR(100) NULL,
                CALCULATION_RULE INT NULL,
                CALCULATION_RULE_BUNDLE VARCHAR(100) NULL,
  CHECK_RECORD   BIT
                                )
END
GO

----------------------PRIMARY KEY---------------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_RS_CONTRACT_DETAILS_PENDING_RS_CONTRACT_DETAILS_PENDING_SID'
                     AND TABLE_NAME = 'RS_CONTRACT_DETAILS_PENDING')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS_PENDING]
        ADD CONSTRAINT PK_RS_CONTRACT_DETAILS_PENDING_RS_CONTRACT_DETAILS_PENDING_SID PRIMARY KEY(RS_CONTRACT_DETAILS_PENDING_SID)
  END

GO

-------------------------   DEFAULT_CONSTRAINTS --------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS_PENDING')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_PENDING_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS_PENDING]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_PENDING_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS_PENDING')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_PENDING_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS_PENDING]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_PENDING_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS_PENDING')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_PENDING_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS_PENDING]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_PENDING_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.RS_CONTRACT_DETAILS_PENDING')
                      AND NAME = 'DF_RS_CONTRACT_DETAILS_PENDING_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[RS_CONTRACT_DETAILS_PENDING]
        ADD CONSTRAINT [DF_RS_CONTRACT_DETAILS_PENDING_MODIFIED_DATE] DEFAULT (GETDATE()) FOR MODIFIED_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'RS_CONTRACT_DETAILS_PENDING'--TABLE NAME
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
