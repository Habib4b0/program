-----------------------NATIONAL ASSUMPTIONS DATA SELECTION TABLES---------------------------
--------------------------NA_PROJ_MASTER------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'NA_PROJ_MASTER'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE NA_PROJ_MASTER
        (
           NA_PROJ_MASTER_SID INT NOT NULL IDENTITY(1, 1),
           NA_PROJ_NAME       VARCHAR(100) NULL,
           COMPANY_MASTER_SID INT NULL,
           ITEM_GROUP_SID     INT NULL,
           THERAPEUTIC_CLASS  INT NULL,
           CREATED_BY         INT NOT NULL,
           CREATED_DATE       DATETIME NOT NULL,
           MODIFIED_BY        INT NOT NULL,
           MODIFIED_DATE      DATETIME NOT NULL,
		   SAVE_FLAG		  BIT NULL
        )
  END
GO

-----------------column addition ------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'NA_PROJ_MASTER'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE NA_PROJ_MASTER
        ADD  BUSINESS_UNIT INT 
  END

GO
---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'NA_PROJ_MASTER'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_NA_PROJ_MASTER_NA_PROJ_MASTER_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].[NA_PROJ_MASTER]
        ADD CONSTRAINT PK_NA_PROJ_MASTER_NA_PROJ_MASTER_SID PRIMARY KEY(NA_PROJ_MASTER_SID)
  END

GO

---------------------DEFAULT CONSTRAINTS------------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'NA_PROJ_MASTER'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_NA_PROJ_MASTER_CREATED_BY')
  BEGIN
      ALTER TABLE NA_PROJ_MASTER
        ADD CONSTRAINT DF_NA_PROJ_MASTER_CREATED_BY DEFAULT (1) FOR CREATED_BY
  END

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'NA_PROJ_MASTER'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_NA_PROJ_MASTER_CREATED_DATE')
  BEGIN
      ALTER TABLE NA_PROJ_MASTER
        ADD CONSTRAINT DF_NA_PROJ_MASTER_CREATED_DATE DEFAULT (Getdate()) FOR CREATED_DATE
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'NA_PROJ_MASTER'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_NA_PROJ_MASTER_MODIFIED_BY')
  BEGIN
      ALTER TABLE NA_PROJ_MASTER
        ADD CONSTRAINT DF_NA_PROJ_MASTER_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY
  END

  GO
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'NA_PROJ_MASTER'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_NA_PROJ_MASTER_MODIFIED_DATE')
  BEGIN
      ALTER TABLE NA_PROJ_MASTER
        ADD CONSTRAINT DF_NA_PROJ_MASTER_MODIFIED_DATE DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

-----------------------------NA_PROJ_DETAILS---------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'NA_PROJ_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE NA_PROJ_DETAILS
        (
           NA_PROJ_DETAILS_SID INT NOT NULL IDENTITY(1, 1),
           NA_PROJ_MASTER_SID  INT NOT NULL,
           ITEM_MASTER_SID     INT NOT NULL
        )
  END

GO

------------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'NA_PROJ_DETAILS'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_NA_PROJ_DETAILS_NA_PROJ_DETAILS_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].[NA_PROJ_DETAILS]
        ADD CONSTRAINT PK_NA_PROJ_DETAILS_NA_PROJ_DETAILS_SID PRIMARY KEY(NA_PROJ_DETAILS_SID)
  END

GO

-----------------------------NA_PROJECTION_SELECTION---------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'NA_PROJECTION_SELECTION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [dbo].[NA_PROJECTION_SELECTION]
        (
           [NA_PROJECTION_SELECTION_SID] [INT] IDENTITY(1, 1) NOT NULL,
           [NA_PROJ_MASTER_SID]          [INT] NOT NULL,
           [SCREEN_NAME]                 [VARCHAR](50) NOT NULL,
           [FIELD_NAME]                  [VARCHAR](30) NOT NULL,
           [FIELD_VALUES]                [VARCHAR](4000) NOT NULL
        )
  END

GO

------------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'NA_PROJECTION_SELECTION'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_NA_PROJECTION_SELECTION_NA_PROJECTION_SELECTION_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].[NA_PROJECTION_SELECTION]
        ADD CONSTRAINT PK_NA_PROJECTION_SELECTION_NA_PROJECTION_SELECTION_SID PRIMARY KEY(NA_PROJECTION_SELECTION_SID)
  END 
GO
