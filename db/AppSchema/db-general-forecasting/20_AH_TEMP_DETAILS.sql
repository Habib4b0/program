IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'AH_TEMP_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[AH_TEMP_DETAILS]
        (
           AH_TEMP_DETAILS_SID  INT IDENTITY(1, 1) NOT NULL,
           COMPONENT_MASTER_SID INT NULL,
           COMPONENT_NAME       VARCHAR(50),
           COMPONENT_NO         VARCHAR(50),
           COMPONENT_TYPE       VARCHAR(50),
           COMPANY_NAME         VARCHAR(50),
           COMPANY_NO           VARCHAR(50),
           COMPANY_SID          INT,
           CONTRACT_HOLDER      VARCHAR(50),
           ITEM_MASTER_SID      INT,
           ITEM_ID              VARCHAR(50),
           ITEM_NAME            VARCHAR(50),
           ITEM_NO              VARCHAR(50),
           BRAND_NAME           VARCHAR(50),
           THERAPUTIC_CLASS     VARCHAR(50),
           BUSINESS_UNIT_NO     VARCHAR(50),
           BUSINESS_UNIT_NAME   VARCHAR(50),
           ITEM_IDENTIFIER      VARCHAR(50),
           ITEM_IDENTIFIER_TYPE VARCHAR(50),
           SCREEN_NAME          VARCHAR(50),
           CHECK_RECORD         BIT,
           USER_ID              VARCHAR(50),
           SESSION_ID           VARCHAR(50),
           CREATED_BY           VARCHAR(50),
           CREATED_DATE         DATETIME
        )
  END

GO

------------------------------ Primary Key
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'AH_TEMP_DETAILS'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_AH_TEMP_DETAILS_AH_TEMP_DETAILS_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [DBO].[AH_TEMP_DETAILS]
        ADD CONSTRAINT PK_AH_TEMP_DETAILS_AH_TEMP_DETAILS_SID PRIMARY KEY (AH_TEMP_DETAILS_SID)
  END 

GO
------------------------------ column addition 
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'AH_TEMP_DETAILS'
                      AND COLUMN_NAME = 'SELECTED_CHECK_RECORD'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE AH_TEMP_DETAILS
        ADD SELECTED_CHECK_RECORD BIT
  END

GO  