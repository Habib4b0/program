IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_HIERARCHY_DEFINITION_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[ITEM_HIERARCHY_DEFINITION_INTERFACE]
        (
           [ITEM_HIERARCHY_DEFINITION_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [MEMBER]                                 VARCHAR(100) NULL,
           [ALIAS]                                  VARCHAR(100) NULL,
           [BPI_LVL]                                NUMERIC(18, 0) NULL,
           [ADD_CHG_DEL_INDICATOR]                  VARCHAR(10) NOT NULL,
           [CREATED_BY]                             VARCHAR(50) NULL,
           [CREATED_DATE]                           DATETIME NULL,
           [MODIFIED_BY]                            VARCHAR(50) NULL,
           [MODIFIED_DATE]                          DATETIME NULL,
           [BATCH_ID]                               VARCHAR(38) NOT NULL,
           [SOURCE]                                 VARCHAR(50) NULL
        )
  END

GO