----------------------------------------------------------------- NM_ASSUMPTIONS --------------------------------------------- 
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'NM_ASSUMPTIONS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[NM_ASSUMPTIONS]
        (
           [NM_ASSUMPTIONS_SID]               [INT] IDENTITY(1, 1) NOT NULL,
           [PROJECTION_DETAILS_SID]           [INT] NULL,
           [PROJECTION_PERIOD]                [INT] NULL,
           [SEGMENT]                          [VARCHAR](50) NULL,
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
           [COMMENTARY]                       [VARCHAR](200) NULL,
           [CAM_ID]                           [INT] NULL,
           [PARENT]                           [BIT] NULL,
        )
  END
GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'NM_ASSUMPTIONS'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_NM_ASSUMPTIONS_NM_ASSUMPTIONS_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[NM_ASSUMPTIONS]
        ADD CONSTRAINT PK_NM_ASSUMPTIONS_NM_ASSUMPTIONS_SID PRIMARY KEY(NM_ASSUMPTIONS_SID)
  END

GO

----------------------------------------------------------- ST_NM_ASSUMPTIONS -----------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ST_NM_ASSUMPTIONS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ST_NM_ASSUMPTIONS]
        (
           [ST_NM_ASSUMPTIONS_SID]            [UNIQUEIDENTIFIER] NOT NULL,
           [NM_ASSUMPTIONS_SID]               [INT] NOT NULL,
           [PROJECTION_DETAILS_SID]           [INT] NOT NULL,
           [PROJECTION_PERIOD]                [INT] NOT NULL,
           [SEGMENT]                          [VARCHAR](50) NULL,
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
           [COMMENTARY]                       [VARCHAR](200) NULL,
           [CAM_ID]                           [INT] NULL,
           [PARENT]                           [BIT] NULL,
           [IS_CHECKED]                       [BIT] NULL,
           [USER_ID]                          [INT] NOT NULL,
           [SESSION_ID]                       [INT] NOT NULL,
           [LAST_MODIFIED_DATE]               [DATETIME] NOT NULL
        )
  END
GO

IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'ST_NM_ASSUMPTIONS'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_ST_NM_ASSUMPTIONS_ST_NM_ASSUMPTIONS_SID_NM_ASSUMPTIONS_SID_PROJECTION_DETAILS_SID_PROJECTION_PERIOD_USER_ID_SESSION_ID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[ST_NM_ASSUMPTIONS]
        ADD CONSTRAINT PK_ST_NM_ASSUMPTIONS_ST_NM_ASSUMPTIONS_SID_NM_ASSUMPTIONS_SID_PROJECTION_DETAILS_SID_PROJECTION_PERIOD_USER_ID_SESSION_ID PRIMARY KEY(ST_NM_ASSUMPTIONS_SID, NM_ASSUMPTIONS_SID, PROJECTION_DETAILS_SID, PROJECTION_PERIOD, USER_ID, SESSION_ID)
  END

GO

------------------ DEFAULT CONSTRAINT -----------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'ST_NM_ASSUMPTIONS'
                      AND scHema_Name(SCHEMA_ID) = 'dbo'
                      AND NAME = 'DF_ST_NM_ASSUMPTIONS_LAST_MODIFIED_DATE')
  BEGIN
      ALTER TABLE ST_NM_ASSUMPTIONS
        ADD CONSTRAINT DF_ST_NM_ASSUMPTIONS_LAST_MODIFIED_DATE DEFAULT (getdate()) FOR LAST_MODIFIED_DATE
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_Name(PARENT_OBJECT_ID) = 'ST_NM_ASSUMPTIONS'
                      AND scHema_Name(SCHEMA_ID) = 'dbo'
                      AND NAME = 'DF_ST_NM_ASSUMPTIONS_ST_NM_ASSUMPTIONS_SID')
  BEGIN
      ALTER TABLE ST_NM_ASSUMPTIONS
        ADD CONSTRAINT DF_ST_NM_ASSUMPTIONS_ST_NM_ASSUMPTIONS_SID DEFAULT NEWSEQUENTIALID() FOR ST_NM_ASSUMPTIONS_SID
  END

GO