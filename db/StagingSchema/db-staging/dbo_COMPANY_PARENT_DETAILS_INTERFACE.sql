IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'COMPANY_PARENT_DETAILS_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[COMPANY_PARENT_DETAILS_INTERFACE]
        (
           [PARENT_DETAILS_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [COMPANY_ID]                  VARCHAR(38) NOT NULL,
           [PARENT_COMPANY_ID]           VARCHAR(50) NOT NULL,
           [PARENT_START_DATE]           DATETIME NOT NULL,
           [PARENT_END_DATE]             DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [PRIOR_PARENT_COMPANY_ID]     VARCHAR(50) NULL,
           [PRIOR_PARENT_START_DATE]     DATETIME NULL,
           [LAST_UPDATED_DATE]           DATETIME NULL
        )
  END
GO 

