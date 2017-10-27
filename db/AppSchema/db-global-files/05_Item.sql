--------------------------------------ITEM_MASTER------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[ITEM_MASTER]
        (
           [ITEM_MASTER_SID]                INT IDENTITY(1, 1) NOT NULL,
           [ITEM_ID]                        VARCHAR(38) NOT NULL,
           [ITEM_NO]                        VARCHAR(50) NOT NULL,
           [ITEM_CODE]                      VARCHAR(25) NOT NULL,
           [ITEM_NAME]                      VARCHAR(100) NOT NULL,
           [BRAND_MASTER_SID]               INT NULL,
           [ITEM_DESC]                      VARCHAR(250) NULL,
           [PACKAGE_SIZE]                   VARCHAR(100) NULL,
           [PACKAGE_SIZE_INTRO_DATE]        DATETIME NULL,
           [UPPS]                           NUMERIC(22, 6) NULL,
           [ITEM_START_DATE]                DATETIME NULL,
           [ITEM_END_DATE]                  DATETIME NULL,
           [ITEM_STATUS]                    INT NOT NULL,
           [MANUFACTURER_ID]                VARCHAR(38) NULL,
           [LABELER_CODE]                   VARCHAR(25) NULL,
           [ORGANIZATION_KEY]               VARCHAR(50) NULL,
           [ACQUISITION_DATE]               DATETIME NULL,
           [AUTHORIZED_GENERIC]             VARCHAR(50) NULL,
           [AUTHORIZED_GENERIC_START_DATE]  DATETIME NULL,
           [AUTHORIZED_GENERIC_END_DATE]    DATETIME NULL,
           [FORM]                           INT NOT NULL,
           [STRENGTH]                       INT NOT NULL,
           [THERAPEUTIC_CLASS]              INT NULL,
           [FIRST_SALE_DATE]                DATETIME NULL,
           [ITEM_TYPE_INDICATION]           VARCHAR(50) NULL,
           [ITEM_CLASS]                     INT NULL,
           [ITEM_TYPE]                      INT NULL,
           [MARKET_TERMINATION_DATE]        DATETIME NULL,
           [NEW_FORMULATION_INDICATOR]      VARCHAR(50) NULL,
           [NEW_FORMULATION]                VARCHAR(38) NULL,
           [NEW_FORMULATION_START_DATE]     DATETIME NULL,
           [NEW_FORMULATION_END_DATE]       DATETIME NULL,
           [PEDIATRIC_EXCLUSIVE_INDICATOR]  VARCHAR(50) NULL,
           [PEDIATRIC_EXCLUSIVE_START_DATE] DATETIME NULL,
           [PEDIATRIC_EXCLUSIVE_END_DATE]   DATETIME NULL,
           [CLOTTING_FACTOR_INDICATOR]      VARCHAR(50) NULL,
           [CLOTTING_FACTOR_START_DATE]     DATETIME NULL,
           [CLOTTING_FACTOR_END_DATE]       DATETIME NULL,
           [PRIMARY_UOM]                    INT NULL,
           [SECONDARY_UOM]                  INT NULL,
           [SHELF_LIFE]                     VARCHAR(30) NULL,
           [SHELF_LIFE_TYPE]                INT NULL,
           [DUAL_PRICING_INDICATOR]         VARCHAR(30) NULL,
           [ITEM_FAMILY_ID]                 VARCHAR(38) NULL,
           [ACQUIRED_AMP]                   NUMERIC(22, 6) NULL,
           [ACQUIRED_BAMP]                  NUMERIC(22, 6) NULL,
           [OBRA_BAMP]                      NUMERIC(22, 6) NULL,
           [DRA]                            NUMERIC(22, 6) NULL,
           [DOSES_PER_UNIT]                 VARCHAR(25) NULL,
           [DISCONTINUATION_DATE]           DATETIME NULL,
           [LAST_LOT_EXPIRATION_DATE]       DATETIME NULL,
           [DIVESTITURE_DATE]               DATETIME NULL,
           [NON_FEDERAL_EXPIRATION_DATE]    DATETIME NULL,
           [NDC9]                           VARCHAR(25) NOT NULL,
           [NDC8]                           VARCHAR(25) NOT NULL,
           [ITEM_CATEGORY]                  INT NULL,
           [BASELINE_AMP]                   NUMERIC(22, 6) NULL,
		   ALT_BASELINE_AMP					NUMERIC(22, 6),
           [BASE_CPI_PERIOD]                DATETIME NULL,
           [BASE_CPI]                       NUMERIC(22, 6) NULL,
		   ALT_BASE_CPI						NUMERIC(22, 6),
           [PACKAGE_SIZE_CODE]              VARCHAR(25) NULL,
           INTERNAL_NOTES                   VARCHAR(4000) NULL,
           [INBOUND_STATUS]                 CHAR(1) NOT NULL,
           [RECORD_LOCK_STATUS]             BIT NOT NULL,
           [BATCH_ID]                       VARCHAR(50) NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL
        )
  END

GO

------------------------------ datatype changes -------------------------------
IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'PACKAGE_SIZE'
                  AND Object_name(object_id) = 'ITEM_MASTER')
  BEGIN
      DROP STATISTICS dbo.ITEM_MASTER.PACKAGE_SIZE 
  END 

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ITEM_MASTER'
                  AND COLUMN_NAME = 'PACKAGE_SIZE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE ITEM_MASTER
        ALTER COLUMN PACKAGE_SIZE INT
  END

GO

-------------------size changed-------------------------

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ITEM_MASTER'
                      AND COLUMN_NAME = 'NDC8'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR'
                      AND CHARACTER_MAXIMUM_LENGTH = 100)
BEGIN
  ALTER TABLE ITEM_MASTER
    ALTER COLUMN NDC8 VARCHAR(100) NOT NULL
END
GO
------------- drop statistics ----------------------

IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'ITEM_TYPE_INDICATION'
                  AND Object_name(object_id) = 'ITEM_MASTER')
  BEGIN
      DROP STATISTICS dbo.ITEM_MASTER.ITEM_TYPE_INDICATION 
  END 

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ITEM_MASTER'
                  AND COLUMN_NAME = 'ITEM_TYPE_INDICATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE ITEM_MASTER
        ALTER COLUMN ITEM_TYPE_INDICATION INT
  END

GO

---- Column Addition --------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ITEM_MASTER'
                      AND COLUMN_NAME = 'ALT_BASELINE_AMP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE ITEM_MASTER
        ADD ALT_BASELINE_AMP NUMERIC(22, 6) NULL
  END

GO 

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ITEM_MASTER'
                      AND COLUMN_NAME = 'ALT_BASE_CPI'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE ITEM_MASTER
        ADD ALT_BASE_CPI NUMERIC(22, 6) NULL
  END
GO 
----- ends here
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.ITEM_MASTER')
                      AND NAME = 'DF_ITEM_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[ITEM_MASTER]
        ADD CONSTRAINT [DF_ITEM_MASTER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO


IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_name(object_id) = N'ITEM_MASTER'
                      AND Object_schema_name(object_id) = N'dbo'
                      AND [Name] = N'PK_ITEM_MASTER_ITEM_SID')
  BEGIN
      ALTER TABLE [dbo].[ITEM_MASTER]
        ADD CONSTRAINT [PK_ITEM_MASTER_ITEM_SID] PRIMARY KEY CLUSTERED ( [ITEM_MASTER_SID] ASC ) WITH (FILLFACTOR = 80) ON [PRIMARY]
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.ITEM_MASTER')
                      AND NAME = 'DF_ITEM_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[ITEM_MASTER]
        ADD CONSTRAINT [DF_ITEM_MASTER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.ITEM_MASTER')
                      AND NAME = 'DF_ITEM_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[ITEM_MASTER]
        ADD CONSTRAINT [DF_ITEM_MASTER_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.ITEM_MASTER')
                      AND NAME = 'DF_ITEM_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[ITEM_MASTER]
        ADD CONSTRAINT [DF_ITEM_MASTER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.ITEM_MASTER')
                      AND NAME = 'DF_ITEM_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[ITEM_MASTER]
        ADD CONSTRAINT [DF_ITEM_MASTER_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO
--GAL-5745 CHANGES
IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'ORGANIZATION_KEY'
                  AND Object_name(OBJECT_ID) = 'ITEM_MASTER')
  BEGIN
      DROP STATISTICS DBO.ITEM_MASTER.ORGANIZATION_KEY
  END 
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'ORGANIZATION_KEY'
                  AND TABLE_NAME = 'ITEM_MASTER'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE ITEM_MASTER
        ALTER COLUMN ORGANIZATION_KEY INT NOT NULL
  END 
GO

IF NOT EXISTS(SELECT 1
              FROM   SYS.FOREIGN_KEYS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'ITEM_MASTER'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'FK_ITEM_MASTER_COMPANY_MASTER_ORGANIZATION_KEY'
                     AND TYPE = 'F')
  BEGIN
      ALTER TABLE ITEM_MASTER
        ADD CONSTRAINT FK_ITEM_MASTER_COMPANY_MASTER_ORGANIZATION_KEY  FOREIGN KEY(ORGANIZATION_KEY) REFERENCES COMPANY_MASTER(COMPANY_MASTER_SID)
  END

GO
---------------------------------------UNIQUE_CONSTRAINT------------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'ITEM_MASTER') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('ITEM_MASTER')
                      AND NAME = 'UQ_ITEM_MASTER_ITEM_ID')
  BEGIN
      ALTER TABLE ITEM_MASTER
        ADD CONSTRAINT UQ_ITEM_MASTER_ITEM_ID UNIQUE(ITEM_ID)
  END
END
GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ITEM_MASTER'--TABLE NAME
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

--------------------------------------HIST_ITEM_MASTER----------------------------------


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ITEM_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_ITEM_MASTER]
        (
           [ITEM_MASTER_SID]                INT NOT NULL,
           [ITEM_ID]                        VARCHAR(38) NOT NULL,
           [ITEM_NO]                        VARCHAR(50) NOT NULL,
           [ITEM_CODE]                      VARCHAR(25) NOT NULL,
           [ITEM_NAME]                      VARCHAR(100) NOT NULL,
           [BRAND_MASTER_SID]               INT NULL,
           [ITEM_DESC]                      VARCHAR(250) NULL,
           [PACKAGE_SIZE]                   VARCHAR(100) NULL,
           [PACKAGE_SIZE_INTRO_DATE]        DATETIME NULL,
           [UPPS]                           NUMERIC(22, 6) NULL,
           [ITEM_START_DATE]                DATETIME NULL,
           [ITEM_END_DATE]                  DATETIME NULL,
           [ITEM_STATUS]                    INT NOT NULL,
           [MANUFACTURER_ID]                VARCHAR(38) NULL,
           [LABELER_CODE]                   VARCHAR(25) NULL,
           [ORGANIZATION_KEY]               VARCHAR(50) NULL,
           [ACQUISITION_DATE]               DATETIME NULL,
           [AUTHORIZED_GENERIC]             VARCHAR(50) NULL,
           [AUTHORIZED_GENERIC_START_DATE]  DATETIME NULL,
           [AUTHORIZED_GENERIC_END_DATE]    DATETIME NULL,
           [FORM]                           INT NOT NULL,
           [STRENGTH]                       INT NOT NULL,
           [THERAPEUTIC_CLASS]              INT NULL,
           [FIRST_SALE_DATE]                DATETIME NULL,
           [ITEM_TYPE_INDICATION]           VARCHAR(50) NULL,
           [ITEM_CLASS]                     INT NULL,
           [ITEM_TYPE]                      INT NULL,
           [MARKET_TERMINATION_DATE]        DATETIME NULL,
           [NEW_FORMULATION_INDICATOR]      VARCHAR(50) NULL,
           [NEW_FORMULATION]                VARCHAR(38) NULL,
           [NEW_FORMULATION_START_DATE]     DATETIME NULL,
           [NEW_FORMULATION_END_DATE]       DATETIME NULL,
           [PEDIATRIC_EXCLUSIVE_INDICATOR]  VARCHAR(50) NULL,
           [PEDIATRIC_EXCLUSIVE_START_DATE] DATETIME NULL,
           [PEDIATRIC_EXCLUSIVE_END_DATE]   DATETIME NULL,
           [CLOTTING_FACTOR_INDICATOR]      VARCHAR(50) NULL,
           [CLOTTING_FACTOR_START_DATE]     DATETIME NULL,
           [CLOTTING_FACTOR_END_DATE]       DATETIME NULL,
           [PRIMARY_UOM]                    INT NULL,
           [SECONDARY_UOM]                  INT NULL,
           [SHELF_LIFE]                     VARCHAR(30) NULL,
           [SHELF_LIFE_TYPE]                INT NULL,
           [DUAL_PRICING_INDICATOR]         VARCHAR(30) NULL,
           [ITEM_FAMILY_ID]                 VARCHAR(38) NULL,
           [ACQUIRED_AMP]                   NUMERIC(22, 6) NULL,
           [ACQUIRED_BAMP]                  NUMERIC(22, 6) NULL,
           [OBRA_BAMP]                      NUMERIC(22, 6) NULL,
           [DRA]                            NUMERIC(22, 6) NULL,
           [DOSES_PER_UNIT]                 VARCHAR(25) NULL,
           [DISCONTINUATION_DATE]           DATETIME NULL,
           [LAST_LOT_EXPIRATION_DATE]       DATETIME NULL,
           [DIVESTITURE_DATE]               DATETIME NULL,
           [NON_FEDERAL_EXPIRATION_DATE]    DATETIME NULL,
           [NDC9]                           VARCHAR(25) NOT NULL,
           [NDC8]                           VARCHAR(25) NOT NULL,
           [ITEM_CATEGORY]                  INT NULL,
           [BASELINE_AMP]                   NUMERIC(22, 6) NULL,
		   ALT_BASELINE_AMP				    NUMERIC(22, 6) NULL,
           [BASE_CPI_PERIOD]                DATETIME NULL,
           [BASE_CPI]                       NUMERIC(22, 6) NULL,
		   ALT_BASE_CPI						NUMERIC(22, 6) NULL,
           [PACKAGE_SIZE_CODE]              VARCHAR(25) NULL,
           INTERNAL_NOTES                   VARCHAR(4000) NULL,
           [INBOUND_STATUS]                 CHAR(1) NOT NULL,
           [RECORD_LOCK_STATUS]             BIT NOT NULL,
           [BATCH_ID]                       VARCHAR(50) NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [CREATED_BY]                     INT NOT NULL,
           [CREATED_DATE]                   DATETIME NOT NULL,
           [MODIFIED_BY]                    INT NOT NULL,
           [MODIFIED_DATE]                  DATETIME NOT NULL,
           ACTION_DATE                      DATETIME NOT NULL,
           ACTION_FLAG                      CHAR(1) NOT NULL
        )
  END

GO

--------------------------- datatype change ---------------------------

IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'PACKAGE_SIZE'
                  AND Object_name(object_id) = 'HIST_ITEM_MASTER')
  BEGIN
      DROP STATISTICS dbo.HIST_ITEM_MASTER.PACKAGE_SIZE 
  END 

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ITEM_MASTER'
                  AND COLUMN_NAME = 'PACKAGE_SIZE'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ITEM_MASTER
        ALTER COLUMN PACKAGE_SIZE INT
  END

GO
-----------------size changes----------------------------

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ITEM_MASTER'
                      AND COLUMN_NAME = 'NDC8'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR'
                      AND CHARACTER_MAXIMUM_LENGTH = 100)
BEGIN
  ALTER TABLE HIST_ITEM_MASTER
    ALTER COLUMN NDC8 VARCHAR(100) NOT NULL
END
GO

----------------- drop statistics ----------------------

IF EXISTS (SELECT *
           FROM   SYS.stats
           WHERE  NAME = 'ITEM_TYPE_INDICATION'
                  AND Object_name(object_id) = 'HIST_ITEM_MASTER')
  BEGIN
      DROP STATISTICS dbo.HIST_ITEM_MASTER.ITEM_TYPE_INDICATION 
  END 

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ITEM_MASTER'
                  AND COLUMN_NAME = 'ITEM_TYPE_INDICATION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ITEM_MASTER
        ALTER COLUMN ITEM_TYPE_INDICATION INT
  END

GO


---- Column Addition --------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ITEM_MASTER'
                      AND COLUMN_NAME = 'ALT_BASELINE_AMP'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ITEM_MASTER
        ADD ALT_BASELINE_AMP NUMERIC(22, 6) NULL
  END

GO 

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ITEM_MASTER'
                      AND COLUMN_NAME = 'ALT_BASE_CPI'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ITEM_MASTER
        ADD ALT_BASE_CPI NUMERIC(22, 6) NULL
  END
GO 
---- ends here -----------

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_ITEM_MASTER')
                      AND NAME = 'DF_HIST_ITEM_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_ITEM_MASTER]
        ADD CONSTRAINT [DF_HIST_ITEM_MASTER_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ITEM_MASTER'--TABLE NAME
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


-------------------------------------ITEM_MASTER TRIGGER--------------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_ITEM_MASTER_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_MASTER_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_MASTER_UPD]
ON [dbo].[ITEM_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_ITEM_MASTER
                    (ITEM_MASTER_SID,
                     ITEM_ID,
                     ITEM_NO,
                     ITEM_CODE,
                     ITEM_NAME,
                     BRAND_MASTER_SID,
                     ITEM_DESC,
                     PACKAGE_SIZE,
                     PACKAGE_SIZE_INTRO_DATE,
                     UPPS,
                     ITEM_START_DATE,
                     ITEM_END_DATE,
                     ITEM_STATUS,
                     MANUFACTURER_ID,
                     LABELER_CODE,
                     ORGANIZATION_KEY,
                     ACQUISITION_DATE,
                     AUTHORIZED_GENERIC,
                     AUTHORIZED_GENERIC_START_DATE,
                     AUTHORIZED_GENERIC_END_DATE,
                     FORM,
                     STRENGTH,
                     THERAPEUTIC_CLASS,
                     FIRST_SALE_DATE,
                     ITEM_TYPE_INDICATION,
                     ITEM_CLASS,
                     ITEM_TYPE,
                     MARKET_TERMINATION_DATE,
                     NEW_FORMULATION_INDICATOR,
                     NEW_FORMULATION,
                     NEW_FORMULATION_START_DATE,
                     NEW_FORMULATION_END_DATE,
                     PEDIATRIC_EXCLUSIVE_INDICATOR,
                     PEDIATRIC_EXCLUSIVE_START_DATE,
                     PEDIATRIC_EXCLUSIVE_END_DATE,
                     CLOTTING_FACTOR_INDICATOR,
                     CLOTTING_FACTOR_START_DATE,
                     CLOTTING_FACTOR_END_DATE,
                     PRIMARY_UOM,
                     SECONDARY_UOM,
                     SHELF_LIFE,
                     SHELF_LIFE_TYPE,
                     DUAL_PRICING_INDICATOR,
                     ITEM_FAMILY_ID,
                     ACQUIRED_AMP,
                     ACQUIRED_BAMP,
                     OBRA_BAMP,
                     DRA,
                     DOSES_PER_UNIT,
                     DISCONTINUATION_DATE,
                     LAST_LOT_EXPIRATION_DATE,
                     DIVESTITURE_DATE,
                     NON_FEDERAL_EXPIRATION_DATE,
                     NDC9,
                     NDC8,
                     ITEM_CATEGORY,
                     BASELINE_AMP,
					 ALT_BASELINE_AMP,
                     BASE_CPI_PERIOD,
                     BASE_CPI,
					 ALT_BASE_CPI,
                     PACKAGE_SIZE_CODE,
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
        SELECT ITEM_MASTER_SID,
               ITEM_ID,
               ITEM_NO,
               ITEM_CODE,
               ITEM_NAME,
               BRAND_MASTER_SID,
               ITEM_DESC,
               PACKAGE_SIZE,
               PACKAGE_SIZE_INTRO_DATE,
               UPPS,
               ITEM_START_DATE,
               ITEM_END_DATE,
               ITEM_STATUS,
               MANUFACTURER_ID,
               LABELER_CODE,
               ORGANIZATION_KEY,
               ACQUISITION_DATE,
               AUTHORIZED_GENERIC,
               AUTHORIZED_GENERIC_START_DATE,
               AUTHORIZED_GENERIC_END_DATE,
               FORM,
               STRENGTH,
               THERAPEUTIC_CLASS,
               FIRST_SALE_DATE,
               ITEM_TYPE_INDICATION,
               ITEM_CLASS,
               ITEM_TYPE,
               MARKET_TERMINATION_DATE,
               NEW_FORMULATION_INDICATOR,
               NEW_FORMULATION,
               NEW_FORMULATION_START_DATE,
               NEW_FORMULATION_END_DATE,
               PEDIATRIC_EXCLUSIVE_INDICATOR,
               PEDIATRIC_EXCLUSIVE_START_DATE,
               PEDIATRIC_EXCLUSIVE_END_DATE,
               CLOTTING_FACTOR_INDICATOR,
               CLOTTING_FACTOR_START_DATE,
               CLOTTING_FACTOR_END_DATE,
               PRIMARY_UOM,
               SECONDARY_UOM,
               SHELF_LIFE,
               SHELF_LIFE_TYPE,
               DUAL_PRICING_INDICATOR,
               ITEM_FAMILY_ID,
               ACQUIRED_AMP,
               ACQUIRED_BAMP,
               OBRA_BAMP,
               DRA,
               DOSES_PER_UNIT,
               DISCONTINUATION_DATE,
               LAST_LOT_EXPIRATION_DATE,
               DIVESTITURE_DATE,
               NON_FEDERAL_EXPIRATION_DATE,
               NDC9,
               NDC8,
               ITEM_CATEGORY,
               BASELINE_AMP,
			   ALT_BASELINE_AMP,
               BASE_CPI_PERIOD,
               BASE_CPI,
			   ALT_BASE_CPI,
               PACKAGE_SIZE_CODE,
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
           WHERE  [Name] = N'TRG_ITEM_MASTER_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_MASTER_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_MASTER_INS]
ON [dbo].[ITEM_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_ITEM_MASTER
                    (ITEM_MASTER_SID,
                     ITEM_ID,
                     ITEM_NO,
                     ITEM_CODE,
                     ITEM_NAME,
                     BRAND_MASTER_SID,
                     ITEM_DESC,
                     PACKAGE_SIZE,
                     PACKAGE_SIZE_INTRO_DATE,
                     UPPS,
                     ITEM_START_DATE,
                     ITEM_END_DATE,
                     ITEM_STATUS,
                     MANUFACTURER_ID,
                     LABELER_CODE,
                     ORGANIZATION_KEY,
                     ACQUISITION_DATE,
                     AUTHORIZED_GENERIC,
                     AUTHORIZED_GENERIC_START_DATE,
                     AUTHORIZED_GENERIC_END_DATE,
                     FORM,
                     STRENGTH,
                     THERAPEUTIC_CLASS,
                     FIRST_SALE_DATE,
                     ITEM_TYPE_INDICATION,
                     ITEM_CLASS,
                     ITEM_TYPE,
                     MARKET_TERMINATION_DATE,
                     NEW_FORMULATION_INDICATOR,
                     NEW_FORMULATION,
                     NEW_FORMULATION_START_DATE,
                     NEW_FORMULATION_END_DATE,
                     PEDIATRIC_EXCLUSIVE_INDICATOR,
                     PEDIATRIC_EXCLUSIVE_START_DATE,
                     PEDIATRIC_EXCLUSIVE_END_DATE,
                     CLOTTING_FACTOR_INDICATOR,
                     CLOTTING_FACTOR_START_DATE,
                     CLOTTING_FACTOR_END_DATE,
                     PRIMARY_UOM,
                     SECONDARY_UOM,
                     SHELF_LIFE,
                     SHELF_LIFE_TYPE,
                     DUAL_PRICING_INDICATOR,
                     ITEM_FAMILY_ID,
                     ACQUIRED_AMP,
                     ACQUIRED_BAMP,
                     OBRA_BAMP,
                     DRA,
                     DOSES_PER_UNIT,
                     DISCONTINUATION_DATE,
                     LAST_LOT_EXPIRATION_DATE,
                     DIVESTITURE_DATE,
                     NON_FEDERAL_EXPIRATION_DATE,
                     NDC9,
                     NDC8,
                     ITEM_CATEGORY,
                     BASELINE_AMP,
					 ALT_BASELINE_AMP,
                     BASE_CPI_PERIOD,
                     BASE_CPI,
					 ALT_BASE_CPI,
                     PACKAGE_SIZE_CODE,
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
        SELECT ITEM_MASTER_SID,
               ITEM_ID,
               ITEM_NO,
               ITEM_CODE,
               ITEM_NAME,
               BRAND_MASTER_SID,
               ITEM_DESC,
               PACKAGE_SIZE,
               PACKAGE_SIZE_INTRO_DATE,
               UPPS,
               ITEM_START_DATE,
               ITEM_END_DATE,
               ITEM_STATUS,
               MANUFACTURER_ID,
               LABELER_CODE,
               ORGANIZATION_KEY,
               ACQUISITION_DATE,
               AUTHORIZED_GENERIC,
               AUTHORIZED_GENERIC_START_DATE,
               AUTHORIZED_GENERIC_END_DATE,
               FORM,
               STRENGTH,
               THERAPEUTIC_CLASS,
               FIRST_SALE_DATE,
               ITEM_TYPE_INDICATION,
               ITEM_CLASS,
               ITEM_TYPE,
               MARKET_TERMINATION_DATE,
               NEW_FORMULATION_INDICATOR,
               NEW_FORMULATION,
               NEW_FORMULATION_START_DATE,
               NEW_FORMULATION_END_DATE,
               PEDIATRIC_EXCLUSIVE_INDICATOR,
               PEDIATRIC_EXCLUSIVE_START_DATE,
               PEDIATRIC_EXCLUSIVE_END_DATE,
               CLOTTING_FACTOR_INDICATOR,
               CLOTTING_FACTOR_START_DATE,
               CLOTTING_FACTOR_END_DATE,
               PRIMARY_UOM,
               SECONDARY_UOM,
               SHELF_LIFE,
               SHELF_LIFE_TYPE,
               DUAL_PRICING_INDICATOR,
               ITEM_FAMILY_ID,
               ACQUIRED_AMP,
               ACQUIRED_BAMP,
               OBRA_BAMP,
               DRA,
               DOSES_PER_UNIT,
               DISCONTINUATION_DATE,
               LAST_LOT_EXPIRATION_DATE,
               DIVESTITURE_DATE,
               NON_FEDERAL_EXPIRATION_DATE,
               NDC9,
               NDC8,
               ITEM_CATEGORY,
               BASELINE_AMP,
			   ALT_BASELINE_AMP,
               BASE_CPI_PERIOD,
               BASE_CPI,
			   ALT_BASE_CPI,
               PACKAGE_SIZE_CODE,
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
           WHERE  [Name] = N'TRG_ITEM_MASTER_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_MASTER_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_MASTER_DEL]
ON [dbo].[ITEM_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_ITEM_MASTER
                    (ITEM_MASTER_SID,
                     ITEM_ID,
                     ITEM_NO,
                     ITEM_CODE,
                     ITEM_NAME,
                     BRAND_MASTER_SID,
                     ITEM_DESC,
                     PACKAGE_SIZE,
                     PACKAGE_SIZE_INTRO_DATE,
                     UPPS,
                     ITEM_START_DATE,
                     ITEM_END_DATE,
                     ITEM_STATUS,
                     MANUFACTURER_ID,
                     LABELER_CODE,
                     ORGANIZATION_KEY,
                     ACQUISITION_DATE,
                     AUTHORIZED_GENERIC,
                     AUTHORIZED_GENERIC_START_DATE,
                     AUTHORIZED_GENERIC_END_DATE,
                     FORM,
                     STRENGTH,
                     THERAPEUTIC_CLASS,
                     FIRST_SALE_DATE,
                     ITEM_TYPE_INDICATION,
                     ITEM_CLASS,
                     ITEM_TYPE,
                     MARKET_TERMINATION_DATE,
                     NEW_FORMULATION_INDICATOR,
                     NEW_FORMULATION,
                     NEW_FORMULATION_START_DATE,
                     NEW_FORMULATION_END_DATE,
                     PEDIATRIC_EXCLUSIVE_INDICATOR,
                     PEDIATRIC_EXCLUSIVE_START_DATE,
                     PEDIATRIC_EXCLUSIVE_END_DATE,
                     CLOTTING_FACTOR_INDICATOR,
                     CLOTTING_FACTOR_START_DATE,
                     CLOTTING_FACTOR_END_DATE,
                     PRIMARY_UOM,
                     SECONDARY_UOM,
                     SHELF_LIFE,
                     SHELF_LIFE_TYPE,
                     DUAL_PRICING_INDICATOR,
                     ITEM_FAMILY_ID,
                     ACQUIRED_AMP,
                     ACQUIRED_BAMP,
                     OBRA_BAMP,
                     DRA,
                     DOSES_PER_UNIT,
                     DISCONTINUATION_DATE,
                     LAST_LOT_EXPIRATION_DATE,
                     DIVESTITURE_DATE,
                     NON_FEDERAL_EXPIRATION_DATE,
                     NDC9,
                     NDC8,
                     ITEM_CATEGORY,
                     BASELINE_AMP,
					 ALT_BASELINE_AMP,
                     BASE_CPI_PERIOD,
                     BASE_CPI,
					 ALT_BASE_CPI,
                     PACKAGE_SIZE_CODE,
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
        SELECT ITEM_MASTER_SID,
               ITEM_ID,
               ITEM_NO,
               ITEM_CODE,
               ITEM_NAME,
               BRAND_MASTER_SID,
               ITEM_DESC,
               PACKAGE_SIZE,
               PACKAGE_SIZE_INTRO_DATE,
               UPPS,
               ITEM_START_DATE,
               ITEM_END_DATE,
               ITEM_STATUS,
               MANUFACTURER_ID,
               LABELER_CODE,
               ORGANIZATION_KEY,
               ACQUISITION_DATE,
               AUTHORIZED_GENERIC,
               AUTHORIZED_GENERIC_START_DATE,
               AUTHORIZED_GENERIC_END_DATE,
               FORM,
               STRENGTH,
               THERAPEUTIC_CLASS,
               FIRST_SALE_DATE,
               ITEM_TYPE_INDICATION,
               ITEM_CLASS,
               ITEM_TYPE,
               MARKET_TERMINATION_DATE,
               NEW_FORMULATION_INDICATOR,
               NEW_FORMULATION,
               NEW_FORMULATION_START_DATE,
               NEW_FORMULATION_END_DATE,
               PEDIATRIC_EXCLUSIVE_INDICATOR,
               PEDIATRIC_EXCLUSIVE_START_DATE,
               PEDIATRIC_EXCLUSIVE_END_DATE,
               CLOTTING_FACTOR_INDICATOR,
               CLOTTING_FACTOR_START_DATE,
               CLOTTING_FACTOR_END_DATE,
               PRIMARY_UOM,
               SECONDARY_UOM,
               SHELF_LIFE,
               SHELF_LIFE_TYPE,
               DUAL_PRICING_INDICATOR,
               ITEM_FAMILY_ID,
               ACQUIRED_AMP,
               ACQUIRED_BAMP,
               OBRA_BAMP,
               DRA,
               DOSES_PER_UNIT,
               DISCONTINUATION_DATE,
               LAST_LOT_EXPIRATION_DATE,
               DIVESTITURE_DATE,
               NON_FEDERAL_EXPIRATION_DATE,
               NDC9,
               NDC8,
               ITEM_CATEGORY,
               BASELINE_AMP,
			   ALT_BASELINE_AMP,
               BASE_CPI_PERIOD,
               BASE_CPI,
			   ALT_BASE_CPI,
               PACKAGE_SIZE_CODE,
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

-------------------------------ITEM_QUALIFIER--------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_QUALIFIER'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[ITEM_QUALIFIER]
        (
           [ITEM_QUALIFIER_SID]   INT IDENTITY(1, 1) NOT NULL,
           [ITEM_QUALIFIER_VALUE] VARCHAR(25) NOT NULL,
           [ITEM_QUALIFIER_NAME]  VARCHAR(100) NULL,
           EFFECTIVE_DATES        VARCHAR(10),
           SPECIFIC_ENTITY_CODE   VARCHAR(10),
           NOTES                  VARCHAR(1000),
           INBOUND_STATUS         CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS     BIT NOT NULL,
           BATCH_ID               VARCHAR(50) NULL,
           [SOURCE]               VARCHAR(50) NULL,
           [CREATED_BY]           INT NOT NULL,
           [CREATED_DATE]         DATETIME NOT NULL,
           [MODIFIED_BY]          INT NOT NULL,
           [MODIFIED_DATE]        DATETIME NOT NULL
        )
  END

GO

-------------------------- Column Addition ------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ITEM_QUALIFIER'
                      AND COLUMN_NAME = 'EFFECTIVE_DATES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE ITEM_QUALIFIER
        ADD EFFECTIVE_DATES VARCHAR(10)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ITEM_QUALIFIER'
                      AND COLUMN_NAME = 'SPECIFIC_ENTITY_CODE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE ITEM_QUALIFIER
        ADD SPECIFIC_ENTITY_CODE VARCHAR(10)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ITEM_QUALIFIER'
                      AND COLUMN_NAME = 'NOTES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE ITEM_QUALIFIER
        ADD NOTES VARCHAR(1000)
  END

GO
----------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_Name(object_id) = N'ITEM_QUALIFIER'
                  AND Object_scHema_Name(object_id) = N'dbo'
                  AND [Name] = N'PK_ITEM_QUALIFIER_ITEM_QUALIFIER_SID')
  BEGIN
      ALTER TABLE [dbo].[ITEM_QUALIFIER]
        ADD CONSTRAINT [PK_ITEM_QUALIFIER_ITEM_QUALIFIER_SID] PRIMARY KEY CLUSTERED ( ITEM_QUALIFIER_SID ASC ) WITH (FILLFACTOR = 80) ON [PRIMARY]
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_QUALIFIER')
                  AND NAME = 'DF_ITEM_QUALIFIER_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[ITEM_QUALIFIER]
        ADD CONSTRAINT [DF_ITEM_QUALIFIER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_QUALIFIER')
                  AND NAME = 'DF_ITEM_QUALIFIER_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[ITEM_QUALIFIER]
        ADD CONSTRAINT [DF_ITEM_QUALIFIER_CREATED_DATE] DEFAULT (GetDate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_QUALIFIER')
                  AND NAME = 'DF_ITEM_QUALIFIER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[ITEM_QUALIFIER]
        ADD CONSTRAINT [DF_ITEM_QUALIFIER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_QUALIFIER')
                  AND NAME = 'DF_ITEM_QUALIFIER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[ITEM_QUALIFIER]
        ADD CONSTRAINT [DF_ITEM_QUALIFIER_MODIFIED_DATE] DEFAULT (GetDate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_QUALIFIER')
                  AND NAME = 'DF_ITEM_QUALIFIER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[ITEM_QUALIFIER]
        ADD CONSTRAINT [DF_ITEM_QUALIFIER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

-----------------------------------UNIQUE_CONSTRAINT---------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'ITEM_QUALIFIER') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('ITEM_QUALIFIER')
                      AND NAME = 'UQ_ITEM_QUALIFIER_ITEM_QUALIFIER_VALUE')
  BEGIN
      ALTER TABLE ITEM_QUALIFIER
        ADD CONSTRAINT UQ_ITEM_QUALIFIER_ITEM_QUALIFIER_VALUE UNIQUE(ITEM_QUALIFIER_VALUE)
  END
END
GO



DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ITEM_QUALIFIER'--TABLE NAME
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

------------------------------------HIST_ITEM_QUALIFIER-----------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ITEM_QUALIFIER'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_ITEM_QUALIFIER]
        (
           [ITEM_QUALIFIER_SID]   INT NOT NULL,
           [ITEM_QUALIFIER_VALUE] VARCHAR(25) NOT NULL,
           [ITEM_QUALIFIER_NAME]  VARCHAR(100) NULL,
		   EFFECTIVE_DATES        VARCHAR(10),
		   SPECIFIC_ENTITY_CODE   VARCHAR(10),
		   NOTES                  VARCHAR(1000),
           INBOUND_STATUS         CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS     BIT NOT NULL,
           BATCH_ID               VARCHAR(50) NULL,
           [SOURCE]               VARCHAR(50) NULL,
           [CREATED_BY]           INT NOT NULL,
           [CREATED_DATE]         DATETIME NOT NULL,
           [MODIFIED_BY]          INT NOT NULL,
           [MODIFIED_DATE]        DATETIME NOT NULL,
           ACTION_DATE            DATETIME NOT NULL,
           ACTION_FLAG            CHAR(1) NOT NULL
        )
  END

GO

-------------------------- Column Addition ------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ITEM_QUALIFIER'
                      AND COLUMN_NAME = 'EFFECTIVE_DATES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ITEM_QUALIFIER
        ADD EFFECTIVE_DATES VARCHAR(10)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ITEM_QUALIFIER'
                      AND COLUMN_NAME = 'SPECIFIC_ENTITY_CODE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ITEM_QUALIFIER
        ADD SPECIFIC_ENTITY_CODE VARCHAR(10)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ITEM_QUALIFIER'
                      AND COLUMN_NAME = 'NOTES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ITEM_QUALIFIER
        ADD NOTES VARCHAR(1000)
  END

GO

---------------------------------------- 

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.HIST_ITEM_QUALIFIER')
                  AND NAME = 'DF_HIST_ITEM_QUALIFIER_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_ITEM_QUALIFIER]
        ADD CONSTRAINT [DF_HIST_ITEM_QUALIFIER_ACTION_DATE] DEFAULT (GetDate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ITEM_QUALIFIER'--TABLE NAME
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

----------------------------------ITEM_QUALIFIER TRIGGER---------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_ITEM_QUALIFIER_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_QUALIFIER_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_QUALIFIER_UPD]
ON [dbo].[ITEM_QUALIFIER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_ITEM_QUALIFIER
                    (ITEM_QUALIFIER_SID,
                     ITEM_QUALIFIER_VALUE,
                     ITEM_QUALIFIER_NAME,
					 EFFECTIVE_DATES,
					 SPECIFIC_ENTITY_CODE,
					 NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_QUALIFIER_SID,
               ITEM_QUALIFIER_VALUE,
               ITEM_QUALIFIER_NAME,
			   EFFECTIVE_DATES,
			   SPECIFIC_ENTITY_CODE,
			   NOTES,
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
           WHERE  [Name] = N'TRG_ITEM_QUALIFIER_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_QUALIFIER_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_QUALIFIER_INS]
ON [dbo].[ITEM_QUALIFIER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_ITEM_QUALIFIER
                    (ITEM_QUALIFIER_SID,
                     ITEM_QUALIFIER_VALUE,
                     ITEM_QUALIFIER_NAME,
					 EFFECTIVE_DATES,
					 SPECIFIC_ENTITY_CODE,
					 NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_QUALIFIER_SID,
               ITEM_QUALIFIER_VALUE,
               ITEM_QUALIFIER_NAME,
			   EFFECTIVE_DATES,
			   SPECIFIC_ENTITY_CODE,
			   NOTES,
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
           WHERE  [Name] = N'TRG_ITEM_QUALIFIER_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_QUALIFIER_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_QUALIFIER_DEL]
ON [dbo].[ITEM_QUALIFIER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_ITEM_QUALIFIER
                    (ITEM_QUALIFIER_SID,
                     ITEM_QUALIFIER_VALUE,
                     ITEM_QUALIFIER_NAME,
					 EFFECTIVE_DATES,
					 SPECIFIC_ENTITY_CODE,
					 NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_QUALIFIER_SID,
               ITEM_QUALIFIER_VALUE,
               ITEM_QUALIFIER_NAME,
			   EFFECTIVE_DATES,
			   SPECIFIC_ENTITY_CODE,
			   NOTES,
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



--------------------------------------ITEM_IDENTIFIER-----------------------------------------


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_IDENTIFIER'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[ITEM_IDENTIFIER]
        (
           [ITEM_IDENTIFIER_SID]   INT IDENTITY(1, 1) NOT NULL,
           [ITEM_MASTER_SID]       INT NOT NULL,
           [ITEM_IDENTIFIER_VALUE] VARCHAR(50) NOT NULL,
           [IDENTIFIER_STATUS]     INT NOT NULL,
           [ITEM_QUALIFIER_SID]    INT NOT NULL,
           [START_DATE]            DATETIME NOT NULL,
           [END_DATE]              DATETIME NULL,
           [ENTITY_CODE]           VARCHAR(30) NULL,
           INBOUND_STATUS          CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS      BIT NOT NULL,
           BATCH_ID                VARCHAR(50) NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [CREATED_BY]            INT NOT NULL,
           [CREATED_DATE]          DATETIME NOT NULL,
           [MODIFIED_BY]           INT NOT NULL,
           [MODIFIED_DATE]         DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_IDENTIFIER')
                  AND NAME = 'DF_ITEM_IDENTIFIER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[ITEM_IDENTIFIER]
        ADD CONSTRAINT [DF_ITEM_IDENTIFIER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO


IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_Name(object_id) = N'ITEM_IDENTIFIER'
                  AND Object_scHema_Name(object_id) = N'dbo'
                  AND [Name] = N'PK_ITEM_IDENTIFIER_ITEM_IDENTIFIER_SID')
  BEGIN
      ALTER TABLE [dbo].[ITEM_IDENTIFIER]
        ADD CONSTRAINT [PK_ITEM_IDENTIFIER_ITEM_IDENTIFIER_SID] PRIMARY KEY CLUSTERED ( [ITEM_IDENTIFIER_SID] ASC ) WITH (FILLFACTOR = 80) ON [PRIMARY]
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_Name(object_id) = N'ITEM_IDENTIFIER'
                  AND Object_scHema_Name(object_id) = N'dbo'
                  AND [Name] = N'IX_ITEM_IDENTIFIER_ITEM_IDENTIFIER_VALUE_IDENTIFIER_STATUS_ITEM_QUALIFIER_SID_START_DATE')
  BEGIN
      CREATE NONCLUSTERED INDEX IX_ITEM_IDENTIFIER_ITEM_IDENTIFIER_VALUE_IDENTIFIER_STATUS_ITEM_QUALIFIER_SID_START_DATE
        ON [dbo].[ITEM_IDENTIFIER] ( [ITEM_IDENTIFIER_VALUE] ASC, [IDENTIFIER_STATUS] ASC, [ITEM_QUALIFIER_SID] ASC, [START_DATE] ASC )
        WITH (FILLFACTOR = 80) ON [PRIMARY]
  END

  GO
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_Name(object_id) = N'ITEM_IDENTIFIER'
                  AND Object_scHema_Name(object_id) = N'dbo'
                  AND [Name] = N'IX_ITEM_IDENTIFIER_ITEM_IDENTIFIER_SID')
  BEGIN
      CREATE NONCLUSTERED INDEX [IX_ITEM_IDENTIFIER_ITEM_IDENTIFIER_SID]
        ON [dbo].[ITEM_IDENTIFIER] ( ITEM_IDENTIFIER_SID ASC )
        WITH (FILLFACTOR = 80) ON [PRIMARY]
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_IDENTIFIER')
                  AND NAME = 'DF_ITEM_IDENTIFIER_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[ITEM_IDENTIFIER]
        ADD CONSTRAINT [DF_ITEM_IDENTIFIER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_IDENTIFIER')
                  AND NAME = 'DF_ITEM_IDENTIFIER_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[ITEM_IDENTIFIER]
        ADD CONSTRAINT [DF_ITEM_IDENTIFIER_CREATED_DATE] DEFAULT (GetDate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_IDENTIFIER')
                  AND NAME = 'DF_ITEM_IDENTIFIER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[ITEM_IDENTIFIER]
        ADD CONSTRAINT [DF_ITEM_IDENTIFIER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_IDENTIFIER')
                  AND NAME = 'DF_ITEM_IDENTIFIER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[ITEM_IDENTIFIER]
        ADD CONSTRAINT [DF_ITEM_IDENTIFIER_MODIFIED_DATE] DEFAULT (GetDate()) FOR MODIFIED_DATE
  END

GO

---------------------------------UNIQUE_CONSTRAINT--------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'ITEM_IDENTIFIER') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('ITEM_IDENTIFIER')
                      AND NAME = 'UQ_ITEM_IDENTIFIER_ITEM_IDENTIFIER_VALUE_ITEM_QUALIFIER_SID_START_DATE_ITEM_MASTER_SID')
  BEGIN
      ALTER TABLE ITEM_IDENTIFIER
        ADD CONSTRAINT UQ_ITEM_IDENTIFIER_ITEM_IDENTIFIER_VALUE_ITEM_QUALIFIER_SID_START_DATE_ITEM_MASTER_SID UNIQUE(ITEM_IDENTIFIER_VALUE,ITEM_QUALIFIER_SID,START_DATE,ITEM_MASTER_SID)
  END
END
GO



DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ITEM_IDENTIFIER'--TABLE NAME
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

------------------------------------HIST_ITEM_IDENTIFIER---------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ITEM_IDENTIFIER'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_ITEM_IDENTIFIER]
        (
           [ITEM_IDENTIFIER_SID]   INT NOT NULL,
           [ITEM_MASTER_SID]       INT NOT NULL,
           [ITEM_IDENTIFIER_VALUE] VARCHAR(50) NOT NULL,
           [IDENTIFIER_STATUS]     INT NOT NULL,
           [ITEM_QUALIFIER_SID]    INT NOT NULL,
           [START_DATE]            DATETIME NOT NULL,
           [END_DATE]              DATETIME NULL,
           [ENTITY_CODE]           VARCHAR(30) NULL,
           INBOUND_STATUS          CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS      BIT NOT NULL,
           BATCH_ID                VARCHAR(50) NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [CREATED_BY]            INT NOT NULL,
           [CREATED_DATE]          DATETIME NOT NULL,
           [MODIFIED_BY]           INT NOT NULL,
           [MODIFIED_DATE]         DATETIME NOT NULL,
           ACTION_DATE             DATETIME NOT NULL,
           ACTION_FLAG             CHAR(1) NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.HIST_ITEM_IDENTIFIER')
                  AND NAME = 'DF_HIST_ITEM_IDENTIFIER_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_ITEM_IDENTIFIER]
        ADD CONSTRAINT [DF_HIST_ITEM_IDENTIFIER_ACTION_DATE] DEFAULT (GetDate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ITEM_IDENTIFIER'--TABLE NAME
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



----------------------------------ITEM_IDENTIFIER TRIGGER-----------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_ITEM_IDENTIFIER_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_IDENTIFIER_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_IDENTIFIER_UPD]
ON [dbo].[ITEM_IDENTIFIER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_ITEM_IDENTIFIER
                    (ITEM_IDENTIFIER_SID,
                     ITEM_MASTER_SID,
                     ITEM_IDENTIFIER_VALUE,
                     IDENTIFIER_STATUS,
                     ITEM_QUALIFIER_SID,
                     START_DATE,
                     END_DATE,
                     ENTITY_CODE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_IDENTIFIER_SID,
               ITEM_MASTER_SID,
               ITEM_IDENTIFIER_VALUE,
               IDENTIFIER_STATUS,
               ITEM_QUALIFIER_SID,
               START_DATE,
               END_DATE,
               ENTITY_CODE,
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
           WHERE  [Name] = N'TRG_ITEM_IDENTIFIER_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_IDENTIFIER_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_IDENTIFIER_INS]
ON [dbo].[ITEM_IDENTIFIER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_ITEM_IDENTIFIER
                    (ITEM_IDENTIFIER_SID,
                     ITEM_MASTER_SID,
                     ITEM_IDENTIFIER_VALUE,
                     IDENTIFIER_STATUS,
                     ITEM_QUALIFIER_SID,
                     START_DATE,
                     END_DATE,
                     ENTITY_CODE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_IDENTIFIER_SID,
               ITEM_MASTER_SID,
               ITEM_IDENTIFIER_VALUE,
               IDENTIFIER_STATUS,
               ITEM_QUALIFIER_SID,
               START_DATE,
               END_DATE,
               ENTITY_CODE,
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
           WHERE  [Name] = N'TRG_ITEM_IDENTIFIER_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_IDENTIFIER_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_IDENTIFIER_DEL]
ON [dbo].[ITEM_IDENTIFIER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_ITEM_IDENTIFIER
                    (ITEM_IDENTIFIER_SID,
                     ITEM_MASTER_SID,
                     ITEM_IDENTIFIER_VALUE,
                     IDENTIFIER_STATUS,
                     ITEM_QUALIFIER_SID,
                     START_DATE,
                     END_DATE,
                     ENTITY_CODE,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_IDENTIFIER_SID,
               ITEM_MASTER_SID,
               ITEM_IDENTIFIER_VALUE,
               IDENTIFIER_STATUS,
               ITEM_QUALIFIER_SID,
               START_DATE,
               END_DATE,
               ENTITY_CODE,
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



-------------------------------------ITEM_PRICING_QUALIFIER---------------------------------


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_PRICING_QUALIFIER'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[ITEM_PRICING_QUALIFIER]
        (
           [ITEM_PRICING_QUALIFIER_SID]  INT IDENTITY(1, 1) NOT NULL,
           [PRICING_QUALIFIER]           VARCHAR(25) NOT NULL,
           [ITEM_PRICING_QUALIFIER_NAME] VARCHAR(100) NULL,
		   EFFECTIVE_DATES               VARCHAR(10),
		   SPECIFIC_ENTITY_CODE          VARCHAR(10),
		   NOTES                         VARCHAR(1000),
           INBOUND_STATUS                CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS            BIT NOT NULL,
           BATCH_ID                      VARCHAR(50) NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [CREATED_BY]                  INT NOT NULL,
           [CREATED_DATE]                DATETIME NOT NULL,
           [MODIFIED_BY]                 INT NOT NULL,
           [MODIFIED_DATE]               DATETIME NOT NULL
        )
  END

GO

-------------------------- Column Addition ------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ITEM_PRICING_QUALIFIER'
                      AND COLUMN_NAME = 'EFFECTIVE_DATES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE ITEM_PRICING_QUALIFIER
        ADD EFFECTIVE_DATES VARCHAR(10)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ITEM_PRICING_QUALIFIER'
                      AND COLUMN_NAME = 'SPECIFIC_ENTITY_CODE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE ITEM_PRICING_QUALIFIER
        ADD SPECIFIC_ENTITY_CODE VARCHAR(10)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ITEM_PRICING_QUALIFIER'
                      AND COLUMN_NAME = 'NOTES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE ITEM_PRICING_QUALIFIER
        ADD NOTES VARCHAR(1000)
  END

GO

----------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_PRICING_QUALIFIER')
                  AND NAME = 'DF_ITEM_PRICING_QUALIFIER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[ITEM_PRICING_QUALIFIER]
        ADD CONSTRAINT [DF_ITEM_PRICING_QUALIFIER_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_Name(object_id) = N'ITEM_PRICING_QUALIFIER'
                  AND Object_scHema_Name(object_id) = N'dbo'
                  AND [Name] = N'PK_ITEM_PRICING_QUALIFIER_ITEM_PRICING_QUALIFIER_SID')
  BEGIN
      ALTER TABLE [dbo].[ITEM_PRICING_QUALIFIER]
        ADD CONSTRAINT [PK_ITEM_PRICING_QUALIFIER_ITEM_PRICING_QUALIFIER_SID] PRIMARY KEY CLUSTERED ( [ITEM_PRICING_QUALIFIER_SID] ASC ) WITH (FILLFACTOR = 80) ON [PRIMARY]
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_PRICING_QUALIFIER')
                  AND NAME = 'DF_ITEM_PRICING_QUALIFIER_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[ITEM_PRICING_QUALIFIER]
        ADD CONSTRAINT [DF_ITEM_PRICING_QUALIFIER_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_PRICING_QUALIFIER')
                  AND NAME = 'DF_ITEM_PRICING_QUALIFIER_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[ITEM_PRICING_QUALIFIER]
        ADD CONSTRAINT [DF_ITEM_PRICING_QUALIFIER_CREATED_DATE] DEFAULT (GetDate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_PRICING_QUALIFIER')
                  AND NAME = 'DF_ITEM_PRICING_QUALIFIER_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[ITEM_PRICING_QUALIFIER]
        ADD CONSTRAINT [DF_ITEM_PRICING_QUALIFIER_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.ITEM_PRICING_QUALIFIER')
                  AND NAME = 'DF_ITEM_PRICING_QUALIFIER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[ITEM_PRICING_QUALIFIER]
        ADD CONSTRAINT [DF_ITEM_PRICING_QUALIFIER_MODIFIED_DATE] DEFAULT (GetDate()) FOR MODIFIED_DATE
  END

GO

-----------------------------------UNIQUE_CONSTRAINT-------------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'ITEM_PRICING_QUALIFIER') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('ITEM_PRICING_QUALIFIER')
                      AND NAME = 'UQ_ITEM_PRICING_QUALIFIER_PRICING_QUALIFIER')
  BEGIN
      ALTER TABLE ITEM_PRICING_QUALIFIER
        ADD CONSTRAINT UQ_ITEM_PRICING_QUALIFIER_PRICING_QUALIFIER UNIQUE(PRICING_QUALIFIER)
  END
END
GO

--------------------------------------------price type insert scripts regarding PPA starts here-----------------------------------------------

IF NOT EXISTS (
		SELECT 1
		FROM ITEM_PRICING_QUALIFIER
		WHERE [PRICING_QUALIFIER] = 'Prior Period NEP'
			AND ITEM_PRICING_QUALIFIER_NAME = 'Prior Period NEP'
			AND INBOUND_STATUS = 'A'
			AND RECORD_LOCK_STATUS = 1
		)
BEGIN
	INSERT INTO ITEM_PRICING_QUALIFIER (
		[PRICING_QUALIFIER]
		,[ITEM_PRICING_QUALIFIER_NAME]
		,INBOUND_STATUS
		,RECORD_LOCK_STATUS
		)
	VALUES (
		'Prior Period NEP'
		,'Prior Period NEP'
		,'A'
		,1
		)
END
GO

IF NOT EXISTS (
		SELECT 1
		FROM ITEM_PRICING_QUALIFIER
		WHERE [PRICING_QUALIFIER] = 'Prior Period WAC'
			AND ITEM_PRICING_QUALIFIER_NAME = 'Prior Period WAC'
			AND INBOUND_STATUS = 'A'
			AND RECORD_LOCK_STATUS = 1
		)
BEGIN
	INSERT INTO ITEM_PRICING_QUALIFIER (
		[PRICING_QUALIFIER]
		,[ITEM_PRICING_QUALIFIER_NAME]
		,INBOUND_STATUS
		,RECORD_LOCK_STATUS
		)
	VALUES (
		'Prior Period WAC'
		,'Prior Period WAC'
		,'A'
		,1
		)
END
GO

IF NOT EXISTS (
		SELECT 1
		FROM ITEM_PRICING_QUALIFIER
		WHERE [PRICING_QUALIFIER] = 'Prior Period Net WAC'
			AND ITEM_PRICING_QUALIFIER_NAME = 'Prior Period Net WAC'
			AND INBOUND_STATUS = 'A'
			AND RECORD_LOCK_STATUS = 1
		)
BEGIN
	INSERT INTO ITEM_PRICING_QUALIFIER (
		[PRICING_QUALIFIER]
		,[ITEM_PRICING_QUALIFIER_NAME]
		,INBOUND_STATUS
		,RECORD_LOCK_STATUS
		)
	VALUES (
		'Prior Period Net WAC'
		,'Prior Period Net WAC'
		,'A'
		,1
		)
END
GO

--------------------------------------------price type insert scripts regarding PPA ends here-----------------------------------------------

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ITEM_PRICING_QUALIFIER'--TABLE NAME
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


---------------------------------------HIST_ITEM_PRICING_QUALIFIER-------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ITEM_PRICING_QUALIFIER'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_ITEM_PRICING_QUALIFIER]
        (
           [ITEM_PRICING_QUALIFIER_SID]  INT NOT NULL,
           [PRICING_QUALIFIER]           VARCHAR(25) NOT NULL,
           [ITEM_PRICING_QUALIFIER_NAME] VARCHAR(100) NULL,
		   EFFECTIVE_DATES               VARCHAR(10),
		   SPECIFIC_ENTITY_CODE          VARCHAR(10),
		   NOTES                         VARCHAR(1000),
           INBOUND_STATUS                CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS            BIT NOT NULL,
           BATCH_ID                      VARCHAR(50) NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [CREATED_BY]                  INT NOT NULL,
           [CREATED_DATE]                DATETIME NOT NULL,
           [MODIFIED_BY]                 INT NOT NULL,
           [MODIFIED_DATE]               DATETIME NOT NULL,
           ACTION_DATE                   DATETIME NOT NULL,
           ACTION_FLAG                   CHAR(1) NOT NULL
        )
  END

GO

-------------------------- Column Addition ------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ITEM_PRICING_QUALIFIER'
                      AND COLUMN_NAME = 'EFFECTIVE_DATES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ITEM_PRICING_QUALIFIER
        ADD EFFECTIVE_DATES VARCHAR(10)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ITEM_PRICING_QUALIFIER'
                      AND COLUMN_NAME = 'SPECIFIC_ENTITY_CODE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ITEM_PRICING_QUALIFIER
        ADD SPECIFIC_ENTITY_CODE VARCHAR(10)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'HIST_ITEM_PRICING_QUALIFIER'
                      AND COLUMN_NAME = 'NOTES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE HIST_ITEM_PRICING_QUALIFIER
        ADD NOTES VARCHAR(1000)
  END

GO

----------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_Id('dbo.HIST_ITEM_PRICING_QUALIFIER')
                  AND NAME = 'DF_HIST_ITEM_PRICING_QUALIFIER_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_ITEM_PRICING_QUALIFIER]
        ADD CONSTRAINT [DF_HIST_ITEM_PRICING_QUALIFIER_ACTION_DATE] DEFAULT (GetDate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ITEM_PRICING_QUALIFIER'--TABLE NAME
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


-----------------------------------ITEM_PRICING_QUALIFIER TRIGGER-------------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_ITEM_PRICING_QUALIFIER_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_PRICING_QUALIFIER_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_PRICING_QUALIFIER_UPD]
ON [dbo].[ITEM_PRICING_QUALIFIER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_ITEM_PRICING_QUALIFIER
                    (ITEM_PRICING_QUALIFIER_SID,
                     PRICING_QUALIFIER,
                     ITEM_PRICING_QUALIFIER_NAME,
					 EFFECTIVE_DATES,
					 SPECIFIC_ENTITY_CODE,
					 NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_PRICING_QUALIFIER_SID,
               PRICING_QUALIFIER,
               ITEM_PRICING_QUALIFIER_NAME,
			   EFFECTIVE_DATES,
			   SPECIFIC_ENTITY_CODE,
			   NOTES,
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
           WHERE  [Name] = N'TRG_ITEM_PRICING_QUALIFIER_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_PRICING_QUALIFIER_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_PRICING_QUALIFIER_INS]
ON [dbo].[ITEM_PRICING_QUALIFIER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_ITEM_PRICING_QUALIFIER
                    (ITEM_PRICING_QUALIFIER_SID,
                     PRICING_QUALIFIER,
                     ITEM_PRICING_QUALIFIER_NAME,
					 EFFECTIVE_DATES,
					 SPECIFIC_ENTITY_CODE,
					 NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_PRICING_QUALIFIER_SID,
               PRICING_QUALIFIER,
               ITEM_PRICING_QUALIFIER_NAME,
			   EFFECTIVE_DATES,
			   SPECIFIC_ENTITY_CODE,
			   NOTES,
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
           WHERE  [Name] = N'TRG_ITEM_PRICING_QUALIFIER_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_PRICING_QUALIFIER_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_PRICING_QUALIFIER_DEL]
ON [dbo].[ITEM_PRICING_QUALIFIER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_ITEM_PRICING_QUALIFIER
                    (ITEM_PRICING_QUALIFIER_SID,
                     PRICING_QUALIFIER,
                     ITEM_PRICING_QUALIFIER_NAME,
					 EFFECTIVE_DATES,
					 SPECIFIC_ENTITY_CODE,
					 NOTES,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_PRICING_QUALIFIER_SID,
               PRICING_QUALIFIER,
               ITEM_PRICING_QUALIFIER_NAME,
			   EFFECTIVE_DATES,
			   SPECIFIC_ENTITY_CODE,
			   NOTES,
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


---------------------------------------------ITEM_PRICING--------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_PRICING'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[ITEM_PRICING]
        (
           [ITEM_PRICING_SID]           INT IDENTITY(1, 1) NOT NULL,
           [ITEM_MASTER_SID]            INT NOT NULL,
           [ITEM_PRICING_QUALIFIER_SID] INT NOT NULL,
           [ITEM_UOM]                   INT NOT NULL,
           [ITEM_PRICE]                 NUMERIC(22, 6) NULL,
           [PRICING_CODE_STATUS]        INT NOT NULL,
           [ENTITY_CODE]                VARCHAR(30) NULL,
           [START_DATE]                 DATETIME NOT NULL,
           [END_DATE]                   DATETIME NULL,
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

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_ITEM_PRICING_ITEM_PRICING_SID'
                     AND TABLE_NAME = 'ITEM_PRICING')
  BEGIN
      ALTER TABLE ITEM_PRICING
        ADD CONSTRAINT PK_ITEM_PRICING_ITEM_PRICING_SID PRIMARY KEY(ITEM_PRICING_SID)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.ITEM_PRICING')
                      AND NAME = 'DF_ITEM_PRICING_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE [dbo].[ITEM_PRICING]
        ADD CONSTRAINT [DF_ITEM_PRICING_RECORD_LOCK_STATUS] DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.ITEM_PRICING')
                      AND NAME = 'DF_ITEM_PRICING_CREATED_BY')
  BEGIN
      ALTER TABLE [dbo].[ITEM_PRICING]
        ADD CONSTRAINT [DF_ITEM_PRICING_CREATED_BY] DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.ITEM_PRICING')
                      AND NAME = 'DF_ITEM_PRICING_CREATED_DATE')
  BEGIN
      ALTER TABLE [dbo].[ITEM_PRICING]
        ADD CONSTRAINT [DF_ITEM_PRICING_CREATED_DATE] DEFAULT (Getdate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.ITEM_PRICING')
                      AND NAME = 'DF_ITEM_PRICING_MODIFIED_BY')
  BEGIN
      ALTER TABLE [dbo].[ITEM_PRICING]
        ADD CONSTRAINT [DF_ITEM_PRICING_MODIFIED_BY] DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.ITEM_PRICING')
                      AND NAME = 'DF_ITEM_PRICING_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[ITEM_PRICING]
        ADD CONSTRAINT [DF_ITEM_PRICING_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

---------------------------------------------UNIQUE_CONSTRAINT-----------------------------------------

IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'ITEM_PRICING') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('ITEM_PRICING')
                      AND NAME = 'UQ_ITEM_PRICING_ITEM_MASTER_SID_ITEM_PRICING_QUALIFIER_SID_ITEM_UOM_PRICING_CODE_STATUS_START_DATE_SOURCE')
  BEGIN
      ALTER TABLE ITEM_PRICING
        ADD CONSTRAINT UQ_ITEM_PRICING_ITEM_MASTER_SID_ITEM_PRICING_QUALIFIER_SID_ITEM_UOM_PRICING_CODE_STATUS_START_DATE_SOURCE UNIQUE(ITEM_MASTER_SID,ITEM_PRICING_QUALIFIER_SID,ITEM_UOM,PRICING_CODE_STATUS,START_DATE,SOURCE)
  END
END
GO


DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'ITEM_PRICING'--TABLE NAME
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

-----------------------------------------------------HIST_ITEM_PRICING---------------------------------------------


IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_ITEM_PRICING'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_ITEM_PRICING]
        (
           [ITEM_PRICING_SID]           INT NOT NULL,
           [ITEM_MASTER_SID]            INT NOT NULL,
           [ITEM_PRICING_QUALIFIER_SID] INT NOT NULL,
           [ITEM_UOM]                   INT NOT NULL,
           [ITEM_PRICE]                 NUMERIC(22, 6) NULL,
           [PRICING_CODE_STATUS]        INT NOT NULL,
           [ENTITY_CODE]                VARCHAR(30) NULL,
           [START_DATE]                 DATETIME NOT NULL,
           [END_DATE]                   DATETIME NULL,
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
               WHERE  parent_object_id = Object_id('dbo.HIST_ITEM_PRICING')
                      AND NAME = 'DF_HIST_ITEM_PRICING_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].[HIST_ITEM_PRICING]
        ADD CONSTRAINT [DF_HIST_ITEM_PRICING_ACTION_DATE] DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_ITEM_PRICING'--TABLE NAME
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


--------------------------------------------------ITEM_PRICING TRIGGER---------------------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_ITEM_PRICING_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_PRICING_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_PRICING_UPD]
ON [dbo].[ITEM_PRICING]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_ITEM_PRICING
                    (ITEM_PRICING_SID,
                     ITEM_MASTER_SID,
                     ITEM_PRICING_QUALIFIER_SID,
                     ITEM_UOM,
                     ITEM_PRICE,
                     PRICING_CODE_STATUS,
                     ENTITY_CODE,
                     START_DATE,
                     END_DATE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_PRICING_SID,
               ITEM_MASTER_SID,
               ITEM_PRICING_QUALIFIER_SID,
               ITEM_UOM,
               ITEM_PRICE,
               PRICING_CODE_STATUS,
               ENTITY_CODE,
               START_DATE,
               END_DATE,
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
           WHERE  [Name] = N'TRG_ITEM_PRICING_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_PRICING_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_PRICING_INS]
ON [dbo].[ITEM_PRICING]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_ITEM_PRICING
                    (ITEM_PRICING_SID,
                     ITEM_MASTER_SID,
                     ITEM_PRICING_QUALIFIER_SID,
                     ITEM_UOM,
                     ITEM_PRICE,
                     PRICING_CODE_STATUS,
                     ENTITY_CODE,
                     START_DATE,
                     END_DATE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_PRICING_SID,
               ITEM_MASTER_SID,
               ITEM_PRICING_QUALIFIER_SID,
               ITEM_UOM,
               ITEM_PRICE,
               PRICING_CODE_STATUS,
               ENTITY_CODE,
               START_DATE,
               END_DATE,
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
           WHERE  [Name] = N'TRG_ITEM_PRICING_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_ITEM_PRICING_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_ITEM_PRICING_DEL]
ON [dbo].[ITEM_PRICING]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_ITEM_PRICING
                    (ITEM_PRICING_SID,
                     ITEM_MASTER_SID,
                     ITEM_PRICING_QUALIFIER_SID,
                     ITEM_UOM,
                     ITEM_PRICE,
                     PRICING_CODE_STATUS,
                     ENTITY_CODE,
                     START_DATE,
                     END_DATE,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
                     [SOURCE],
                     INBOUND_STATUS,
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT ITEM_PRICING_SID,
               ITEM_MASTER_SID,
               ITEM_PRICING_QUALIFIER_SID,
               ITEM_UOM,
               ITEM_PRICE,
               PRICING_CODE_STATUS,
               ENTITY_CODE,
               START_DATE,
               END_DATE,
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

-------------------------------------BRAND_MASTER-------------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'BRAND_MASTER'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[BRAND_MASTER]
        (
           [BRAND_MASTER_SID] INT IDENTITY(1, 1) NOT NULL,
           [BRAND_ID]         VARCHAR(25) NOT NULL,
           [BRAND_NAME]       VARCHAR(100) NULL,
           [DISPLAY_BRAND]    VARCHAR(100) NULL,
           [BRAND_DESC]       VARCHAR(100) NULL,
           INBOUND_STATUS     CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS BIT NOT NULL,
           BATCH_ID           VARCHAR(50) NULL,
           [SOURCE]           VARCHAR(50) NULL,
           [CREATED_BY]       INT NOT NULL,
           [CREATED_DATE]     DATETIME NOT NULL,
           [MODIFIED_BY]      INT NOT NULL,
           [MODIFIED_DATE]    DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_BRAND_MASTER_BRAND_MASTER_SID'
                 AND TABLE_NAME = 'BRAND_MASTER')
BEGIN
  ALTER TABLE BRAND_MASTER
    ADD CONSTRAINT PK_BRAND_MASTER_BRAND_MASTER_SID PRIMARY KEY(BRAND_MASTER_SID)
END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'BRAND_MASTER'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_BRAND_MASTER_RECORD_LOCK_STATUS')
  BEGIN
      ALTER TABLE BRAND_MASTER
        ADD CONSTRAINT DF_BRAND_MASTER_RECORD_LOCK_STATUS DEFAULT (1) FOR RECORD_LOCK_STATUS
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'BRAND_MASTER'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_BRAND_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE BRAND_MASTER
        ADD CONSTRAINT DF_BRAND_MASTER_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'BRAND_MASTER'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_BRAND_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE BRAND_MASTER
        ADD CONSTRAINT DF_BRAND_MASTER_CREATED_DATE DEFAULT (GetDate()) FOR CREATED_DATE
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'BRAND_MASTER'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_BRAND_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE BRAND_MASTER
        ADD CONSTRAINT DF_BRAND_MASTER_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'BRAND_MASTER'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_BRAND_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE BRAND_MASTER
        ADD CONSTRAINT DF_BRAND_MASTER_MODIFIED_DATE DEFAULT (GetDate()) FOR MODIFIED_DATE
  END

GO

---------------------------------------UNIQUE_CONSTRAINT----------------------------------------


IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'BRAND_MASTER') 
BEGIN
	IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                      AND PARENT_OBJECT_ID = Object_id('BRAND_MASTER')
                      AND NAME = 'UQ_BRAND_MASTER_BRAND_ID')
  BEGIN
      ALTER TABLE BRAND_MASTER
        ADD CONSTRAINT UQ_BRAND_MASTER_BRAND_ID UNIQUE(BRAND_ID)
  END
END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'BRAND_MASTER'--TABLE NAME
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


----------------------------------------HIST_BRAND_MASTER-------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_BRAND_MASTER'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[HIST_BRAND_MASTER]
        (
           [BRAND_MASTER_SID] INT NOT NULL,
           [BRAND_ID]         VARCHAR(25) NOT NULL,
           [BRAND_NAME]       VARCHAR(100) NULL,
           [DISPLAY_BRAND]    VARCHAR(100) NULL,
           [BRAND_DESC]       VARCHAR(100) NULL,
           INBOUND_STATUS     CHAR(1) NOT NULL,
           RECORD_LOCK_STATUS BIT NOT NULL,
           BATCH_ID           VARCHAR(50) NULL,
           [SOURCE]           VARCHAR(50) NULL,
           [CREATED_BY]       INT NOT NULL,
           [CREATED_DATE]     DATETIME NOT NULL,
           [MODIFIED_BY]      INT NOT NULL,
           [MODIFIED_DATE]    DATETIME NOT NULL,
           ACTION_FLAG        CHAR(1) NOT NULL,
           ACTION_DATE        DATETIME NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT *
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'HIST_BRAND_MASTER'
                  AND scHema_Name(SCHEMA_ID) = 'DBO'
                  AND NAME = 'DF_HIST_BRAND_MASTER_ACTION_DATE')
  BEGIN
      ALTER TABLE HIST_BRAND_MASTER
        ADD CONSTRAINT DF_HIST_BRAND_MASTER_ACTION_DATE DEFAULT (GetDate()) FOR ACTION_DATE
  END
GO

DECLARE @SQL NVARCHAR(MAX)
DECLARE @TABLENAME VARCHAR(100)
DECLARE @STATSNAME VARCHAR(200)
DECLARE @TABLENAME1 VARCHAR(100)
DECLARE @SCHEMANAME VARCHAR(30)
DECLARE @SCHEMANAME1 VARCHAR(30)

SET @TABLENAME1 = 'HIST_BRAND_MASTER'--TABLE NAME
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


------------------------------------------BRAND_MASTER TRIGGER--------------------------------------------

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_BRAND_MASTER_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_BRAND_MASTER_UPD
  END

GO

CREATE TRIGGER [dbo].[TRG_BRAND_MASTER_UPD]
ON [dbo].[BRAND_MASTER]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_BRAND_MASTER
                    (BRAND_MASTER_SID,
                     BRAND_ID,
                     BRAND_NAME,
                     DISPLAY_BRAND,
                     BRAND_DESC,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
					 [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT BRAND_MASTER_SID,
               BRAND_ID,
               BRAND_NAME,
               DISPLAY_BRAND,
               BRAND_DESC,
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
           WHERE  [Name] = N'TRG_BRAND_MASTER_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_BRAND_MASTER_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_BRAND_MASTER_INS]
ON [dbo].[BRAND_MASTER]
AFTER INSERT
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_BRAND_MASTER
                    (BRAND_MASTER_SID,
                     BRAND_ID,
                     BRAND_NAME,
                     DISPLAY_BRAND,
                     BRAND_DESC,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
					 [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT BRAND_MASTER_SID,
               BRAND_ID,
               BRAND_NAME,
               DISPLAY_BRAND,
               BRAND_DESC,
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
           WHERE  [Name] = N'TRG_BRAND_MASTER_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_BRAND_MASTER_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_BRAND_MASTER_DEL]
ON [dbo].[BRAND_MASTER]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_BRAND_MASTER
                    (BRAND_MASTER_SID,
                     BRAND_ID,
                     BRAND_NAME,
                     DISPLAY_BRAND,
                     BRAND_DESC,
                     INBOUND_STATUS,
                     RECORD_LOCK_STATUS,
                     BATCH_ID,
					 [SOURCE],
                     CREATED_BY,
                     CREATED_DATE,
                     MODIFIED_BY,
                     MODIFIED_DATE,
                     ACTION_FLAG)
        SELECT BRAND_MASTER_SID,
               BRAND_ID,
               BRAND_NAME,
               DISPLAY_BRAND,
               BRAND_DESC,
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

----IMTD_COMPANY_GROUP_DETAILS0----------



	IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'IMTD_COMPANY_GROUP_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].IMTD_COMPANY_GROUP_DETAILS
        (
COMPANY_GROUP_SID	INT	
,COMPANY_MASTER_SID	INT	NOT NULL
,COMPANY_TRADECLASS_SID	INT	
,COMPANY_PARENT_DETAILS_SID	INT	
,CREATED_BY	INT	NOT NULL
,CREATED_DATE	DATETIME	NOT NULL
,MODIFIED_BY	INT	
,MODIFIED_DATE	DATETIME	
,VERSION_NO	INT	NOT NULL
,IMTD_CREATED_DATE	DATETIME	NOT NULL
,USERS_ID	VARCHAR(50)	NOT NULL
,SESSION_ID	VARCHAR(50)	NOT NULL
)
END
GO
-----IMTD_ITEM_GROUP_DETAILS----------
	IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'IMTD_ITEM_GROUP_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].IMTD_ITEM_GROUP_DETAILS
        (

ITEM_GROUP_SID	INT	
,ITEM_MASTER_SID	INT	NOT NULL
,CREATED_BY	INT	NOT NULL
,CREATED_DATE	DATETIME	NOT NULL
,MODIFIED_BY	INT	
,MODIFIED_DATE	DATETIME	
,VERSION_NO	INT	NOT NULL
,IMTD_CREATED_DATE	DATETIME	NOT NULL
,USERS_ID	VARCHAR(50)	NOT NULL
,SESSION_ID	VARCHAR(50)	NOT NULL
)
END
GO