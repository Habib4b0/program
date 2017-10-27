IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'AVERAGE_SHELF_LIFE_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[AVERAGE_SHELF_LIFE_INTERFACE]
        (
           [AVERAGE_SHELF_LIFE_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [ITEM_ID_TYPE]                    VARCHAR(10) NOT NULL,
           [ITEM_ID]                         VARCHAR(50) NOT NULL,
           [AVG_SHELF_LIFE]                  NUMERIC(20, 6) NOT NULL,
           [MODIFIED_BY]                     VARCHAR(50) NULL,
           [MODIFIED_DATE]                   DATETIME NULL,
           [CREATED_BY]                      VARCHAR(50) NULL,
           [CREATED_DATE]                    DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]           VARCHAR(10) NOT NULL,
           [BATCH_ID]                        VARCHAR(38) NOT NULL,
           [SOURCE]                          VARCHAR(50) NULL
        )
  END

GO