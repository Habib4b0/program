IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.VIEWS
           WHERE  TABLE_NAME = 'vw_USER_TABLES'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      DROP VIEW [dbo].[vw_USER_TABLES]
  END
GO

CREATE VIEW [DBO].[vw_USER_TABLES]
AS
  SELECT     Row_number()
               OVER(
                 ORDER BY ST.TABLE_NAME) UNIQUE_ID,
             ST.TABLE_NAME,
             SC.COLUMN_NAME
  FROM       (SELECT OBJECT_ID,
                     NAME AS TABLE_NAME,
                     TYPE_DESC,
                     IS_MS_SHIPPED
              FROM   SYS.TABLES) ST
  INNER JOIN (SELECT OBJECT_ID,
                     NAME AS COLUMN_NAME
              FROM   SYS.COLUMNS) SC
          ON ST.OBJECT_ID = SC.OBJECT_ID
  WHERE      ST.TYPE_DESC = 'USER_TABLE'
             AND IS_MS_SHIPPED = 0
GO

