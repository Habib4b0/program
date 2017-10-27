IF NOT EXISTS (SELECT 1
               FROM   SYS.TYPES A
               WHERE  NAME = 'TABLE_LIST'
                      AND IS_USER_DEFINED = 1)
  BEGIN
      CREATE TYPE [dbo].[TABLE_LIST] AS TABLE( [ORG_TABLE_NAME] [varchar](200) NULL )
  END

GO 
