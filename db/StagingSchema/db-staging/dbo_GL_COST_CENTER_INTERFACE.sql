IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'GL_COST_CENTER_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[GL_COST_CENTER_INTERFACE]
        (
           [GL_COST_CENTER_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [COMPANY_COST_CENTER]         VARCHAR(8) NOT NULL,
           [COMPANY_CODE]                VARCHAR(5) NOT NULL,
           [NDC8]                        VARCHAR(25) NOT NULL,
           [UPLOAD_DATE]                 DATETIME NOT NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL
        )
  END

GO 


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'GL_COST_CENTER_INTERFACE'
                      AND COLUMN_NAME = 'NDC8'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR'
                      AND CHARACTER_MAXIMUM_LENGTH = 100)
BEGIN
  ALTER TABLE GL_COST_CENTER_INTERFACE
    ALTER COLUMN NDC8 VARCHAR(100) NOT NULL
END
GO