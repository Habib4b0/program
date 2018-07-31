
IF NOT EXISTS (SELECT 'X' 
               FROM   INFORMATION_SCHEMA.TABLES 
               WHERE  TABLE_NAME = 'ARM_ACTUAL_ADJ_MASTER' 
                      AND TABLE_SCHEMA = 'DBO') 
  BEGIN 
      CREATE TABLE ARM_ACTUAL_ADJ_MASTER 
        ( 
           ARM_ACTUAL_ADJ_MASTER_SID INT IDENTITY(1, 1) NOT NULL, 
           CONTRACT_ID            VARCHAR(50) NOT NULL, 
           COMPANY_ID             VARCHAR(50) NOT NULL, 
           ITEM_ID                VARCHAR(50) NOT NULL, 
           GL_COMPANY             VARCHAR(50) NOT NULL, 
           DIVISION               VARCHAR(50) NOT NULL, 
           RS_CATEGORY            INT NOT NULL, 
           RS_TYPE                INT NOT NULL, 
           REBATE_PROGRAM_TYPE    INT NOT NULL, 
           ADJUSTMENT_TYPE        INT NOT NULL, 
           ACCOUNT_TYPE           INT NOT NULL, 
           ACCOUNT_CATEGORY       INT NOT NULL, 
           ACCOUNT                VARCHAR(50) NOT NULL, 
           DEBIT_INDICATOR        BIT NULL, 
           CREDIT_INDICATOR       BIT NULL, 
           REDEMPTION_DATE        DATETIME NOT NULL, 
           AMOUNT                 NUMERIC(22, 6) NULL 
        ) 
  END 

GO 

---------PRIMARY KEY CONSTRAINTS-------------------- 
IF NOT EXISTS(SELECT 1 
              FROM   SYS.KEY_CONSTRAINTS 
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'ARM_ACTUAL_ADJ_MASTER' 
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO' 
                     AND NAME = 'PK_ARM_ACTUAL_ADJ_MASTER_ARM_ACTUAL_ADJ_MASTER_SID' 
                     AND TYPE = 'PK') 
  BEGIN 
      ALTER TABLE ARM_ACTUAL_ADJ_MASTER 
        ADD CONSTRAINT PK_ARM_ACTUAL_ADJ_MASTER_ARM_ACTUAL_ADJ_MASTER_SID PRIMARY KEY(ARM_ACTUAL_ADJ_MASTER_SID)
  END 

GO 

DECLARE @SQL NVARCHAR(MAX) 
DECLARE @TABLENAME VARCHAR(100) 
DECLARE @STATSNAME VARCHAR(200) 
DECLARE @TABLENAME1 VARCHAR(100) 
DECLARE @SCHEMANAME VARCHAR(30) 
DECLARE @SCHEMANAME1 VARCHAR(30) 

SET @TABLENAME1 = 'ARM_ACTUAL_ADJ_MASTER'--TABLE NAME 
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

-------------------------------------------------------F.K'S FOR  ARM_HISTORY_MASTER-------------------------------------------------------------- 
IF NOT EXISTS(SELECT 1 
              FROM   SYS.FOREIGN_KEYS 
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'ARM_ACTUAL_ADJ_MASTER' 
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO' 
                     AND NAME = 'FK_ARM_ACTUAL_ADJ_MASTER_HELPER_TABLE_RS_CATEGORY' 
                     AND TYPE = 'F') 
  BEGIN 
      ALTER TABLE ARM_ACTUAL_ADJ_MASTER 
        ADD CONSTRAINT FK_ARM_ACTUAL_ADJ_MASTER_HELPER_TABLE_RS_CATEGORY FOREIGN KEY(RS_CATEGORY) REFERENCES HELPER_TABLE(HELPER_TABLE_SID)
  END 

GO 

IF NOT EXISTS(SELECT 1 
              FROM   SYS.FOREIGN_KEYS 
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'ARM_ACTUAL_ADJ_MASTER' 
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO' 
                     AND NAME = 'FK_ARM_ACTUAL_ADJ_MASTER_HELPER_TABLE_RS_TYPE' 
                     AND TYPE = 'F') 
  BEGIN 
      ALTER TABLE ARM_ACTUAL_ADJ_MASTER 
        ADD CONSTRAINT FK_ARM_ACTUAL_ADJ_MASTER_HELPER_TABLE_RS_TYPE FOREIGN KEY(RS_TYPE) REFERENCES HELPER_TABLE(HELPER_TABLE_SID)
  END 

GO 

IF NOT EXISTS(SELECT 1 
              FROM   SYS.FOREIGN_KEYS 
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'ARM_ACTUAL_ADJ_MASTER' 
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO' 
                     AND NAME = 'FK_ARM_ACTUAL_ADJ_MASTER_HELPER_TABLE_REBATE_PROGRAM_TYPE' 
                     AND TYPE = 'F') 
  BEGIN 
      ALTER TABLE ARM_ACTUAL_ADJ_MASTER 
        ADD CONSTRAINT FK_ARM_ACTUAL_ADJ_MASTER_HELPER_TABLE_REBATE_PROGRAM_TYPE FOREIGN KEY(REBATE_PROGRAM_TYPE) REFERENCES HELPER_TABLE(HELPER_TABLE_SID)
  END 

GO 

IF NOT EXISTS(SELECT 1 
              FROM   SYS.FOREIGN_KEYS 
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'ARM_ACTUAL_ADJ_MASTER' 
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO' 
                     AND NAME = 'FK_ARM_ACTUAL_ADJ_MASTER_ARM_ADJUSTMENT_CONFIG_ADJUSTMENT_TYPE'
                     AND TYPE = 'F') 
  BEGIN 
      ALTER TABLE ARM_ACTUAL_ADJ_MASTER 
        ADD CONSTRAINT FK_ARM_ACTUAL_ADJ_MASTER_ARM_ADJUSTMENT_CONFIG_ADJUSTMENT_TYPE FOREIGN KEY(ADJUSTMENT_TYPE) REFERENCES ARM_ADJUSTMENT_CONFIG(ARM_ADJUSTMENT_CONFIG_SID)
  END 

GO 

IF NOT EXISTS(SELECT 1 
              FROM   SYS.FOREIGN_KEYS 
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'ARM_ACTUAL_ADJ_MASTER' 
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO' 
                     AND NAME = 'FK_ARM_ACTUAL_ADJ_MASTER_HELPER_TABLE_ACCOUNT_TYPE' 
                     AND TYPE = 'F') 
  BEGIN 
      ALTER TABLE ARM_ACTUAL_ADJ_MASTER 
        ADD CONSTRAINT FK_ARM_ACTUAL_ADJ_MASTER_HELPER_TABLE_ACCOUNT_TYPE FOREIGN KEY(ACCOUNT_TYPE) REFERENCES HELPER_TABLE(HELPER_TABLE_SID)
  END 

GO 

IF NOT EXISTS(SELECT 1 
              FROM   SYS.FOREIGN_KEYS 
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'ARM_ACTUAL_ADJ_MASTER' 
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO' 
                     AND NAME = 'FK_ARM_ACTUAL_ADJ_MASTER_HELPER_TABLE_ACCOUNT_CATEGORY' 
                     AND TYPE = 'F') 
  BEGIN 
      ALTER TABLE ARM_ACTUAL_ADJ_MASTER 
        ADD CONSTRAINT FK_ARM_ACTUAL_ADJ_MASTER_HELPER_TABLE_ACCOUNT_CATEGORY FOREIGN KEY(ACCOUNT_CATEGORY) REFERENCES HELPER_TABLE(HELPER_TABLE_SID)
  END 

GO 

------------------------------GALUAT-821 Changes
IF NOT EXISTS (SELECT 'X' 
               FROM   INFORMATION_SCHEMA.TABLES 
               WHERE  TABLE_NAME = 'ARM_HIST_ADJ_RES_CON_MASTER' 
                      AND TABLE_SCHEMA = 'DBO') 

BEGIN

CREATE TABLE ARM_HIST_ADJ_RES_CON_MASTER
  (
     ARM_HIST_ADJ_RES_CON_MASTER_SID INT IDENTITY(1, 1) NOT NULL,
     GL_COMPANY_MASTER_SID           INT NOT NULL,
     BU_COMPANY_MASTER_SID           INT NOT NULL,
     RS_CATEGORY                     INT NOT NULL,
     RS_TYPE                         INT NOT NULL,
     REBATE_PROGRAM_TYPE             INT NOT NULL,
     CONFIGURATION_TYPE              BIT NOT NULL,     
     CREATED_BY                      INT NOT NULL,
     CREATED_DATE                    DATETIME NOT NULL,
     MODIFIED_BY                     INT NOT NULL,
     MODIFIED_DATE                   DATETIME NOT NULL,
     SOURCE                          VARCHAR(50)
  )

END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
               WHERE  CONSTRAINT_NAME = 'PK_ARM_HIST_ADJ_RES_CON_MASTER_ARM_HIST_ADJ_RES_CON_MASTER_SID'
                      AND TABLE_NAME = 'ARM_HIST_ADJ_RES_CON_MASTER')
  BEGIN
      ALTER TABLE ARM_HIST_ADJ_RES_CON_MASTER
        ADD CONSTRAINT PK_ARM_HIST_ADJ_RES_CON_MASTER_ARM_HIST_ADJ_RES_CON_MASTER_SID PRIMARY KEY (ARM_HIST_ADJ_RES_CON_MASTER_SID)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ARM_HIST_ADJ_RES_CON_MASTER')
                      AND NAME = 'UQ_ARM_HIST_ADJ_RES_CON_MASTER_GL_COMP_SID_BU_SID_RS_CATEGORY_RS_TYPE_REBATE_PGRM_TYPE_CONFIG_TYPE'
                      AND TYPE = 'UQ')
  BEGIN
      ALTER TABLE [DBO].ARM_HIST_ADJ_RES_CON_MASTER
        ADD CONSTRAINT [UQ_ARM_HIST_ADJ_RES_CON_MASTER_GL_COMP_SID_BU_SID_RS_CATEGORY_RS_TYPE_REBATE_PGRM_TYPE_CONFIG_TYPE] UNIQUE (GL_COMPANY_MASTER_SID, BU_COMPANY_MASTER_SID, RS_CATEGORY, RS_TYPE, REBATE_PROGRAM_TYPE, CONFIGURATION_TYPE)
  END

GO

---default
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ARM_HIST_ADJ_RES_CON_MASTER')
                      AND NAME = 'DF_ARM_HIST_ADJ_RES_CON_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[ARM_HIST_ADJ_RES_CON_MASTER]
        ADD CONSTRAINT [DF_ARM_HIST_ADJ_RES_CON_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ARM_HIST_ADJ_RES_CON_MASTER')
                      AND NAME = 'DF_ARM_HIST_ADJ_RES_CON_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ARM_HIST_ADJ_RES_CON_MASTER]
        ADD CONSTRAINT [DF_ARM_HIST_ADJ_RES_CON_MASTER_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ARM_HIST_ADJ_RES_CON_MASTER')
                      AND NAME = 'DF_ARM_HIST_ADJ_RES_CON_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[ARM_HIST_ADJ_RES_CON_MASTER]
        ADD CONSTRAINT [DF_ARM_HIST_ADJ_RES_CON_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ARM_HIST_ADJ_RES_CON_MASTER')
                      AND NAME = 'DF_ARM_HIST_ADJ_RES_CON_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ARM_HIST_ADJ_RES_CON_MASTER]
        ADD CONSTRAINT [DF_ARM_HIST_ADJ_RES_CON_MASTER_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

/*
IF NOT EXISTS(SELECT 1
              FROM   SYS.FOREIGN_KEYS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'ARM_HIST_ADJ_RES_CON_MASTER'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'FK_ARM_HIST_ADJ_RES_CON_MASTER_HELPER_TABLE_CONFIGURATION_TYPE'
                     AND TYPE = 'F')
  BEGIN
      ALTER TABLE ARM_HIST_ADJ_RES_CON_MASTER
        ADD CONSTRAINT FK_ARM_HIST_ADJ_RES_CON_MASTER_HELPER_TABLE_CONFIGURATION_TYPE FOREIGN KEY(CONFIGURATION_TYPE) REFERENCES HELPER_TABLE(HELPER_TABLE_SID)
  END

GO*/
IF NOT EXISTS (SELECT 'X' 
               FROM   INFORMATION_SCHEMA.TABLES 
               WHERE  TABLE_NAME = 'ARM_HIST_ADJ_RES_CON_DETAILS' 
                      AND TABLE_SCHEMA = 'DBO') 
BEGIN

CREATE TABLE ARM_HIST_ADJ_RES_CON_DETAILS
  (
     ARM_HIST_ADJ_RES_CON_DETAILS_SID INT IDENTITY(1, 1) NOT NULL,
     ARM_HIST_ADJ_RES_CON_MASTER_SID  INT NOT NULL,
     CCP_DETAILS_SID                  INT NOT NULL,
     BRAND_MASTER_SID                 INT NULL,
     RS_MODEL_SID                     INT NOT NULL,
	 GL_IMPACT_DATE                   INT,
     CALCULATION_PERIOD_SID           INT NOT NULL,
     ADJUSTMENT_TYPE                  VARCHAR(200) NOT NULL,
     ACCOUNT_CATEGORY                 INT NULL,
     ACCOUNT_TYPE                     INT NULL,
     ACCOUNT                          VARCHAR(200) NOT NULL,
     ACCOUNT_DESC                     VARCHAR(200) NULL,
     ACCOUNT_INDICATOR                INT NULL,
     COST_CENTER                      VARCHAR(200) NULL,
     PROJECT                          VARCHAR(200) NOT NULL,
     FUTURE_1                         VARCHAR(200) NOT NULL,
     FUTURE_2                         VARCHAR(200) NOT NULL,
     UDC_1                            INT NULL,
     UDC_2                            INT NULL,
     UDC_3                            INT NULL,
     UDC_4                            INT NULL,
     UDC_5                            INT NULL,
     UDC_6                            INT NULL,
     DEDUCTION_AMOUNT                 NUMERIC(22, 6) NULL,
     BALANCE_TYPE                     VARCHAR(200) NULL,
     [DATABASE]                       VARCHAR(200) NULL,
     DATA_ACCESS_SET                  VARCHAR(200) NULL,
     CHART_OF_ACCOUNTS                VARCHAR(200) NULL,
     LEDGER                           VARCHAR(200) NULL,
     CATEGORY                         VARCHAR(200) NULL,
     CURRENCY                         INT NULL,
     JOURNAL_NAME                     VARCHAR(200) NULL,
     JOURNAL_DESCRIPTION              VARCHAR(200) NULL,
     REVERSE_JOURNAL                  VARCHAR(200) NULL,
     REVERSAL_PERIOD_DATE             INT NULL,
     LINE_DESCRIPTION                 VARCHAR(200) NULL,
     ADJUSTMENT_LEVEL                 INT NULL,
     CREDIT                            NUMERIC(22, 6) NULL,
     DEBIT                             NUMERIC(22, 6) NULL,
     DIVISION                         VARCHAR(50) NULL,
     REPORT_INDICATOR                 BIT NULL,
     METHODOLOGY                      INT NOT NULL,
     REDEMPTION_PERIOD                INT NOT NULL,
     SOURCE                           VARCHAR(200) NULL,
     CREATED_BY                       INT NULL,
     CREATED_DATE                     DATETIME NULL,
     MODIFIED_BY                      INT NULL,
     MODIFIED_DATE                    DATETIME NULL
  )

END

GO
-------------------------CONVERT TO NULLABLE COLUMNS
IF EXISTS(SELECT 1
          FROM   SYS.STATS
          WHERE  Object_name(OBJECT_ID) = 'ARM_HIST_ADJ_RES_CON_DETAILS'
                 AND NAME = 'PROJECT')
  BEGIN
      DROP STATISTICS ARM_HIST_ADJ_RES_CON_DETAILS.PROJECT
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ARM_HIST_ADJ_RES_CON_DETAILS'
                  AND COLUMN_NAME = 'PROJECT'
                  AND TABLE_SCHEMA = 'DBO'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE ARM_HIST_ADJ_RES_CON_DETAILS
        ALTER COLUMN PROJECT VARCHAR(200) NULL
  END

GO

IF EXISTS(SELECT 1
          FROM   SYS.STATS
          WHERE  Object_name(OBJECT_ID) = 'ARM_HIST_ADJ_RES_CON_DETAILS'
                 AND NAME = 'FUTURE_1')
  BEGIN
      DROP STATISTICS ARM_HIST_ADJ_RES_CON_DETAILS.FUTURE_1
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ARM_HIST_ADJ_RES_CON_DETAILS'
                  AND COLUMN_NAME = 'FUTURE_1'
                  AND TABLE_SCHEMA = 'DBO'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE ARM_HIST_ADJ_RES_CON_DETAILS
        ALTER COLUMN FUTURE_1 VARCHAR(200) NULL
  END

GO

IF EXISTS(SELECT 1
          FROM   SYS.STATS
          WHERE  Object_name(OBJECT_ID) = 'ARM_HIST_ADJ_RES_CON_DETAILS'
                 AND NAME = 'FUTURE_2')
  BEGIN
      DROP STATISTICS ARM_HIST_ADJ_RES_CON_DETAILS.FUTURE_2
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ARM_HIST_ADJ_RES_CON_DETAILS'
                  AND COLUMN_NAME = 'FUTURE_2'
                  AND TABLE_SCHEMA = 'DBO'
                  AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE ARM_HIST_ADJ_RES_CON_DETAILS
        ALTER COLUMN FUTURE_2 VARCHAR(200) NULL
  END

GO 

-----------------------------------
------ DEFAULT CONSTRAINT
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ARM_HIST_ADJ_RES_CON_DETAILS')
                      AND NAME = 'DF_ARM_HIST_ADJ_RES_CON_DETAILS_CREATED_BY')
  BEGIN
      ALTER TABLE [DBO].[ARM_HIST_ADJ_RES_CON_DETAILS]
        ADD CONSTRAINT [DF_ARM_HIST_ADJ_RES_CON_DETAILS_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ARM_HIST_ADJ_RES_CON_DETAILS')
                      AND NAME = 'DF_ARM_HIST_ADJ_RES_CON_DETAILS_CREATED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ARM_HIST_ADJ_RES_CON_DETAILS]
        ADD CONSTRAINT [DF_ARM_HIST_ADJ_RES_CON_DETAILS_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ARM_HIST_ADJ_RES_CON_DETAILS')
                      AND NAME = 'DF_ARM_HIST_ADJ_RES_CON_DETAILS_MODIFIED_BY')
  BEGIN
      ALTER TABLE [DBO].[ARM_HIST_ADJ_RES_CON_DETAILS]
        ADD CONSTRAINT [DF_ARM_HIST_ADJ_RES_CON_DETAILS_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ARM_HIST_ADJ_RES_CON_DETAILS')
                      AND NAME = 'DF_ARM_HIST_ADJ_RES_CON_DETAILS_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[ARM_HIST_ADJ_RES_CON_DETAILS]
        ADD CONSTRAINT [DF_ARM_HIST_ADJ_RES_CON_DETAILS_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X' 
               FROM   INFORMATION_SCHEMA.TABLES 
               WHERE  TABLE_NAME = 'ARM_HIST_ADJUSTMENTS' 
                      AND TABLE_SCHEMA = 'DBO') 

BEGIN

CREATE TABLE ARM_HIST_ADJUSTMENTS
  (
     ARM_HIST_ADJ_RES_CON_MASTER_SID INT,
     CCP_DETAILS_SID                 INT,
     RS_MODEL_SID                    INT,
     CALCULATION_PERIOD_SID          INT,
     ADJUSTMENT_TYPE                 INT,
     METHODOLOGY                     INT,
     REDEMPTION_PERIOD               INT,
     ACCOUNT                         VARCHAR(100),
     DEBIT                           NUMERIC(22, 6),
     CREDIT                          NUMERIC(22, 6),
     DEDUCTION_AMOUNT                NUMERIC(22, 6),
	 Source							 numeric(22,6),
	 GL_PERIOD_SID                   INT
  ) 

END 
GO

IF NOT EXISTS (SELECT 1 
               FROM   INFORMATION_SCHEMA.COLUMNS 
               WHERE  TABLE_NAME = 'ARM_HIST_ADJUSTMENTS' 
                      AND COLUMN_NAME = 'GL_PERIOD_SID' 
                      AND TABLE_SCHEMA = 'DBO') 
  BEGIN 
      ALTER TABLE ARM_HIST_ADJUSTMENTS 
        ADD GL_PERIOD_SID INT
  END 

GO

---ADJUSTMENT_TYPE DATA Type change
IF  EXISTS (SELECT 1 
               FROM   INFORMATION_SCHEMA.COLUMNS 
               WHERE  TABLE_NAME = 'ARM_HIST_ADJUSTMENTS' 
                      AND COLUMN_NAME = 'ADJUSTMENT_TYPE' 
                      AND TABLE_SCHEMA = 'DBO'
					  AND DATA_TYPE='varchar') 
  BEGIN 
      ALTER TABLE  ARM_HIST_ADJUSTMENTS
       ALTER COLUMN ADJUSTMENT_TYPE INT
  END 

GO