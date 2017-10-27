----------------------------------------------------------------- M_ASSUMPTIONS --------------------------------------------- 
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'M_ASSUMPTIONS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[M_ASSUMPTIONS]
        (
           [M_ASSUMPTIONS_SID]                [INT] IDENTITY(1, 1) NOT NULL,
           [PROJECTION_DETAILS_SID]           [INT] NULL,
           [PROJECTION_PERIOD]                [INT] NULL,
           [GROSS_SALES_PRIOR]                [NUMERIC](22, 6) NULL,
           [GROSS_SALES_PROJECTED]            [NUMERIC](22, 6) NULL,
           [GROSS_SALES_PERCENT_CHANGE]       [NUMERIC](22, 6) NULL,
           [TOTAL_DISCOUNT_PERCENT_PRIOR]     [NUMERIC](22, 6) NULL,
           [TOTAL_DISCOUNT_PERCENT_PROJECTED] [NUMERIC](22, 6) NULL,
           [TOTAL_DISCOUNT_PERCENT_CHANGE]    [NUMERIC](22, 6) NULL,
           [NET_SALES_PRIOR]                  [NUMERIC](22, 6) NULL,
           [NET_SALES_PROJECTED]              [NUMERIC](22, 6) NULL,
           [NET_SALES_PERCENT_CHANGE]         [NUMERIC](22, 6) NULL,
           [REASON_CODES]                     [VARCHAR](50) NULL,
           [COMMENTARY]                       [VARCHAR](500) NULL,
           [CAM_ID]                           [INT] NULL,
           [PARENT]                           [BIT] NULL,
        )
  END
GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'M_ASSUMPTIONS'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_M_ASSUMPTIONS_M_ASSUMPTIONS_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[M_ASSUMPTIONS]
        ADD CONSTRAINT PK_M_ASSUMPTIONS_M_ASSUMPTIONS_SID PRIMARY KEY(M_ASSUMPTIONS_SID)
  END

GO

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'M_ASSUMPTIONS'
                     AND TABLE_SCHEMA = 'dbo'
                     AND COLUMN_NAME = 'PROJ_YEAR')
  BEGIN
      ALTER TABLE [dbo].[M_ASSUMPTIONS]
        ADD [PROJ_YEAR] INT NOT NULL
  END

GO 

----------------------------------------------------------- ST_M_ASSUMPTIONS -----------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ST_M_ASSUMPTIONS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ST_M_ASSUMPTIONS]
        (
           [ST_M_ASSUMPTIONS_SID]             [UNIQUEIDENTIFIER] NOT NULL,
           [M_ASSUMPTIONS_SID]                [INT] NOT NULL,
           [PROJECTION_DETAILS_SID]           [INT] NOT NULL,
           [PROJECTION_PERIOD]                [INT] NOT NULL,
           [GROSS_SALES_PRIOR]                [NUMERIC](22, 6) NULL,
           [GROSS_SALES_PROJECTED]            [NUMERIC](22, 6) NULL,
           [GROSS_SALES_PERCENT_CHANGE]       [NUMERIC](22, 6) NULL,
           [TOTAL_DISCOUNT_PERCENT_PRIOR]     [NUMERIC](22, 6) NULL,
           [TOTAL_DISCOUNT_PERCENT_PROJECTED] [NUMERIC](22, 6) NULL,
           [TOTAL_DISCOUNT_PERCENT_CHANGE]    [NUMERIC](22, 6) NULL,
           [NET_SALES_PRIOR]                  [NUMERIC](22, 6) NULL,
           [NET_SALES_PROJECTED]              [NUMERIC](22, 6) NULL,
           [NET_SALES_PERCENT_CHANGE]         [NUMERIC](22, 6) NULL,
           [REASON_CODES]                     [VARCHAR](50) NULL,
           [COMMENTARY]                       [VARCHAR](500) NULL,
           [CAM_ID]                           [INT] NULL,
           [PARENT]                           [BIT] NULL,
           [IS_CHECKED]                       [BIT] NULL,
           [USER_ID]                          [INT] NOT NULL,
           [SESSION_ID]                       [INT] NOT NULL,
           [LAST_MODIFIED_DATE]               [DATETIME] NOT NULL
        )
  END
GO

----------------------- Column Addition --------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'ST_M_ASSUMPTIONS'
                     AND TABLE_SCHEMA = 'dbo'
                     AND COLUMN_NAME = 'PROJ_YEAR')
  BEGIN
      ALTER TABLE [dbo].[ST_M_ASSUMPTIONS]
        ADD [PROJ_YEAR] INT NOT NULL
  END
GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'ST_M_ASSUMPTIONS'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_ST_M_ASSUMPTIONS_ST_M_ASSUMPTIONS_SID_M_ASSUMPTIONS_SID_PROJECTION_DETAILS_SID_PROJECTION_PERIOD_PROJ_YEAR_USER_ID_SESSION_ID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[ST_M_ASSUMPTIONS]
        ADD CONSTRAINT PK_ST_M_ASSUMPTIONS_ST_M_ASSUMPTIONS_SID_M_ASSUMPTIONS_SID_PROJECTION_DETAILS_SID_PROJECTION_PERIOD_PROJ_YEAR_USER_ID_SESSION_ID PRIMARY KEY(ST_M_ASSUMPTIONS_SID, M_ASSUMPTIONS_SID, PROJECTION_DETAILS_SID, PROJECTION_PERIOD,PROJ_YEAR,USER_ID, SESSION_ID)
  END

GO

------------------ DEFAULT CONSTRAINT -----------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'ST_M_ASSUMPTIONS'
                      AND scHema_Name(SCHEMA_ID) = 'dbo'
                      AND NAME = 'DF_ST_M_ASSUMPTIONS_LAST_MODIFIED_DATE')
  BEGIN
      ALTER TABLE ST_M_ASSUMPTIONS
        ADD CONSTRAINT DF_ST_M_ASSUMPTIONS_LAST_MODIFIED_DATE DEFAULT (getdate()) FOR LAST_MODIFIED_DATE
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'ST_M_ASSUMPTIONS'
                      AND scHema_Name(SCHEMA_ID) = 'dbo'
                      AND NAME = 'DF_ST_M_ASSUMPTIONS_ST_M_ASSUMPTIONS_SID')
  BEGIN
      ALTER TABLE ST_M_ASSUMPTIONS
        ADD CONSTRAINT DF_ST_M_ASSUMPTIONS_ST_M_ASSUMPTIONS_SID DEFAULT NEWSEQUENTIALID() FOR ST_M_ASSUMPTIONS_SID
  END

GO