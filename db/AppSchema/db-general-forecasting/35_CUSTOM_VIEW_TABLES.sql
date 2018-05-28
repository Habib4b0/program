IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CUST_VIEW_MASTER'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[CUST_VIEW_MASTER]
        (
           CUST_VIEW_MASTER_SID      INT NOT NULL IDENTITY(1, 1),
           CUST_VIEW_NAME            VARCHAR(100) NULL,
           CUST_VIEW_DESCRIPTION     VARCHAR(100) NULL,
           CUST_VIEW_TYPE            VARCHAR(100) NULL,
           CUSTOMER_RELATIONSHIP_SID INT NULL,
           PRODUCT_RELATIONSHIP_SID  INT NOT NULL,
           CREATED_BY                INT NULL,
           CREATED_DATE              DATETIME NULL,
           MODIFIED_BY               INT NULL,
           MODIFIED_DATE             DATETIME NULL,
           SCREEN_NAME               VARCHAR(100) NULL
        )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CUST_VIEW_MASTER'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'PK_CUST_VIEW_MASTER_CUST_VIEW_MASTER_SID'
                      AND TYPE = 'PK')
  BEGIN
      ALTER TABLE CUST_VIEW_MASTER
        ADD CONSTRAINT PK_CUST_VIEW_MASTER_CUST_VIEW_MASTER_SID PRIMARY KEY (CUST_VIEW_MASTER_SID)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CUST_VIEW_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[CUST_VIEW_DETAILS]
        (
           CUSTOM_VIEW_DETAILS_SID INT NOT NULL IDENTITY(1, 1),
           CUSTOM_VIEW_MASTER_SID  INT,
           HIERARCHY_ID            INT,
           HIERARCHY_INDICATOR     CHAR(1),
           LEVEL_NO                INT
        )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CUST_VIEW_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'PK_CUST_VIEW_DETAILS_CUSTOM_VIEW_DETAILS_SID'
                      AND TYPE = 'PK')
  BEGIN
      ALTER TABLE CUST_VIEW_DETAILS
        ADD CONSTRAINT PK_CUST_VIEW_DETAILS_CUSTOM_VIEW_DETAILS_SID PRIMARY KEY (CUSTOM_VIEW_DETAILS_SID)
  END

GO 
