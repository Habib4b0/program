 IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_UOM_INTERFACE'---1
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[ITEM_UOM_INTERFACE]
        (          
		   [ITEM_UOM_CONVERSION_ID]            NUMERIC(22,6) NOT NULL,             
           [ITEM_ID]                           VARCHAR(38) NOT NULL,
           [ITEM_NO]                           VARCHAR(50) NULL,
           [ITEM_NAME]                         VARCHAR(100) NULL,
           [PRIMARY_UOM_BASELINE_FACTOR]       NUMERIC(22,6) NOT NULL,
           [PRIMARY_UOM_CODE]                  VARCHAR(20) NOT NULL,
           [PRIMARY_UOM_NAME]                  VARCHAR(20) NOT NULL,
           [SECONDARY_UOM_CONVERSION_FACTOR]   NUMERIC(22,6) NOT NULL,
           [SECONDARY_UOM_CODE]                VARCHAR(20) NOT NULL,
           [SECONDARY_UOM_NAME]                VARCHAR(20) NOT NULL,
           [STATUS]                            VARCHAR(20) NOT NULL,
           [UPLOAD_DATE]                       DATETIME NOT NULL,
           [MODIFIED_BY]                       VARCHAR(50) NULL,
           [MODIFIED_DATE]                     DATETIME NULL,
           [CREATED_BY]                        VARCHAR(38) NULL,
           [CREATED_DATE]                      DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]             VARCHAR(10) NOT NULL,
           [BATCH_ID]					       VARCHAR(38) NOT NULL,
           [SOURCE]                      	   VARCHAR(50) NULL
     )
  END

 GO

 
  

  IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ITEM_UOM_INTERFACE'
                      AND COLUMN_NAME = 'ITEM_UOM_CONVERSION_ID'
                      AND TABLE_SCHEMA = 'DBO' )
  BEGIN
      ALTER TABLE ITEM_UOM_INTERFACE
        ALTER COLUMN  ITEM_UOM_CONVERSION_ID  [NUMERIC](38, 0)  NOT NULL
  END
 GO
 