IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'AUDIT_INBOUND_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[AUDIT_INBOUND_INTERFACE]
        (
           [AUDIT_ID]                         NUMERIC(38, 0) NOT NULL,
           [APPLICATION_PROCESS]              VARCHAR(50) NULL,
           [FILE_NAME]                        VARCHAR(50) NULL,
           [SENT_RECORD_COUNT]                NUMERIC(38, 0) NULL,
           [RECEIVED_RECORD_COUNT]            NUMERIC(38, 0) NULL,
           [SENT_RECORD_AMOUNT]               NUMERIC(20, 6) NULL,
           [SENT_RECORD_AMOUNT_ATTRIBUTE]     VARCHAR(50) NULL,
           [RECEIVED_RECORD_AMOUNT]           NUMERIC(20, 6) NULL,
           [RECEIVED_RECORD_AMOUNT_ATTRIBUTE] VARCHAR(50) NULL,
           [ADD_CHG_DEL_INDICATOR]            VARCHAR(10) NULL,
           [CREATED_BY]                       VARCHAR(50) NULL,
           [CREATED_DATE]                     DATETIME NULL,
           [MODIFIED_BY]                      VARCHAR(50) NULL,
           [MODIFIED_DATE]                    DATETIME NULL,
           [BATCH_ID]                         VARCHAR(38) NOT NULL,
           [SOURCE]                           VARCHAR(50) NULL
        )
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'AUDIT_INBOUND_INTERFACE'
                      AND COLUMN_NAME = 'ADD_CHG_DEL_INDICATOR'
                      AND DATA_TYPE = 'VARCHAR'
                      AND IS_NULLABLE = 'NO')
  BEGIN
      ALTER TABLE AUDIT_INBOUND_INTERFACE
        ALTER COLUMN [ADD_CHG_DEL_INDICATOR] VARCHAR(10) NULL
  END
GO