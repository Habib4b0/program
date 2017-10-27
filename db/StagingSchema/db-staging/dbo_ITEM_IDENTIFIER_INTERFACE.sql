IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_IDENTIFIER_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[ITEM_IDENTIFIER_INTERFACE]
        (
           [ITEM_IDENTIFIER_INTERFACE_ID]   NUMERIC(38, 0) NOT NULL,
           [ITEM_ID]                        VARCHAR(38) NOT NULL,
           [ITEM_NO]                        VARCHAR(50) NOT NULL,
           [ITEM_NAME]                      VARCHAR(100) NULL,
           [IDENTIFIER_CODE_QUALIFIER]      VARCHAR(25) NOT NULL,
           [IDENTIFIER_CODE_QUALIFIER_NAME] VARCHAR(100) NULL,
           [ITEM_IDENTIFIER]                VARCHAR(50) NOT NULL,
           [ITEM_STATUS]                    VARCHAR(20) NOT NULL,
           [START_DATE]                     DATETIME NOT NULL,
           [END_DATE]                       DATETIME NULL,
           [ENTITY_CODE]                    VARCHAR(30) NULL,
           [CREATED_BY]                     VARCHAR(50) NULL,
           [CREATED_DATE]                   DATETIME NULL,
           [MODIFIED_BY]                    VARCHAR(50) NULL,
           [MODIFIED_DATE]                  DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]          VARCHAR(10) NOT NULL,
           [BATCH_ID]                       VARCHAR(38) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL
        )
  END
GO 



