IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'GL_BALANCE_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE [dbo].[GL_BALANCE_INTERFACE]
        (
           [GL_BALANCE_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [ACCOUNT_ID]              VARCHAR(100) NOT NULL,
           [ACCOUNT_NO]              VARCHAR(100) NOT NULL,
           [BALANCE]                 NUMERIC(20, 6) NOT NULL,
           [UPLOAD_DATE]             DATETIME NOT NULL,
           [IS_ACTIVE]               CHAR(10) NULL,
           [INSERTED_DATE]           DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]   VARCHAR(10) NOT NULL,
           [CREATED_BY]              VARCHAR(50) NULL,
           [CREATED_DATE]            DATETIME NULL,
           [MODIFIED_BY]             VARCHAR(50) NULL,
           [MODIFIED_DATE]           DATETIME NULL,
           [BATCH_ID]                VARCHAR(38) NOT NULL,
           [SOURCE]                  VARCHAR(50) NULL,
           [YEAR]                    VARCHAR(10) NULL,
           [PERIOD]                  VARCHAR(2) NULL,
           [CLOSE_INDICATOR]         CHAR(10) NULL
        )
  END
GO 
