IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'LOT_MASTER_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE [dbo].[LOT_MASTER_INTERFACE]
        (
           [LOT_MASTER_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [ITEM_ID]                 VARCHAR(50) NOT NULL,
           [LOT_NO]                  VARCHAR(30) NOT NULL,
           [LAST_LOT_SOLD_DATE]      DATETIME NOT NULL,
           [MODIFIED_BY]             VARCHAR(50) NULL,
           [MODIFIED_DATE]           DATETIME NULL,
           [CREATED_BY]              VARCHAR(50) NULL,
           [CREATED_DATE]            DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]   VARCHAR(10) NOT NULL,
           [BATCH_ID]                VARCHAR(38) NOT NULL,
           [SOURCE]                  VARCHAR(50) NULL,
           [LOT_EXPIRATION_DATE]     DATETIME NULL
        )
  END
GO