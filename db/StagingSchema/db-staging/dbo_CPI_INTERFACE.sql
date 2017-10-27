IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CPI_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE [dbo].[CPI_INTERFACE]
        (
           [CPI_INTERFACE_ID]      NUMERIC(38, 0) NOT NULL,
           [INDEX_ID]              NUMERIC(22, 0) NULL,
           [STATUS]                VARCHAR(20) NULL,
           [INDEX_TYPE]            VARCHAR(50) NULL,
           [EFFECTIVE_DATE]        DATE NULL,
           [INDEX_VALUE]           NUMERIC(22, 6) NULL,
           [SOURCE]                VARCHAR(50) NULL,
           [CREATED_BY]            VARCHAR(50) NULL,
           [CREATED_DATE]          DATE NULL,
           [MODIFIED_BY]           VARCHAR(50) NULL,
           [MODIFIED_DATE]         DATE NULL,
           [ADD_CHG_DEL_INDICATOR] VARCHAR(10) NOT NULL,
           [BATCH_ID]              VARCHAR(38) NOT NULL
        )
  END
GO