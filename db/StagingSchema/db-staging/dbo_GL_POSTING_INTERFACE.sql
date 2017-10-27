IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'GL_POSTING_INTERFACE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [dbo].[GL_POSTING_INTERFACE]
        (
           [GL_POSTING_INTERFACE_ID] [INT] NOT NULL,
           [ACCRUAL_ID]              [VARCHAR](50) NOT NULL,
           [STATUS]                  [CHAR](1) NOT NULL,
           [BATCH_ID]                [VARCHAR](50) NOT NULL,
           [SOURCE]                  [VARCHAR](50) NULL,
           [CREATED_BY]              [VARCHAR](50) NULL,
           [CREATED_DATE]            [DATETIME] NULL,
           [MODIFIED_BY]             [VARCHAR](50) NULL,
           [MODIFIED_DATE]           [DATETIME] NULL,
           [ADD_CHG_DEL_INDICATOR]   [VARCHAR](10) NOT NULL
        )
  END

GO