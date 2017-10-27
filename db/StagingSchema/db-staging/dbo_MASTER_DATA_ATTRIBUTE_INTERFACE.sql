IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'MASTER_DATA_ATTRIBUTE_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[MASTER_DATA_ATTRIBUTE_INTERFACE]
        (
           [DATA_ATTRIBUTE_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [MASTER_TYPE]                 VARCHAR(100) NOT NULL,
           [MASTER_ID]                   VARCHAR(100) NOT NULL,
           [MASTER_ATTRIBUTE]            VARCHAR(100) NOT NULL,
           [COLUMN_1]                    VARCHAR(100) NOT NULL,
           [COLUMN_2]                    VARCHAR(100) NULL,
           [COLUMN_3]                    VARCHAR(100) NULL,
           [COLUMN_4]                    VARCHAR(100) NULL,
           [COLUMN_5]                    VARCHAR(100) NULL,
           [COLUMN_6]                    VARCHAR(100) NULL,
           [COLUMN_7]                    VARCHAR(100) NULL,
           [COLUMN_8]                    VARCHAR(100) NULL,
           [COLUMN_9]                    VARCHAR(100) NULL,
           [COLUMN_10]                   VARCHAR(100) NULL,
           [COLUMN_11]                   VARCHAR(100) NULL,
           [COLUMN_12]                   VARCHAR(100) NULL,
           [COLUMN_13]                   VARCHAR(100) NULL,
           [COLUMN_14]                   VARCHAR(100) NULL,
           [COLUMN_15]                   VARCHAR(100) NULL,
           [COLUMN_16]                   VARCHAR(100) NULL,
           [COLUMN_17]                   VARCHAR(100) NULL,
           [COLUMN_18]                   VARCHAR(100) NULL,
           [COLUMN_19]                   VARCHAR(100) NULL,
           [COLUMN_20]                   VARCHAR(100) NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL
        )
  END

GO