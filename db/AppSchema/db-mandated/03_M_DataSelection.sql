----------------------------------------------------------- M_PROJECTION_SELECTION ---------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'M_PROJECTION_SELECTION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[M_PROJECTION_SELECTION]
        (
           M_PROJECTION_SELECTION_SID INT IDENTITY(1, 1) NOT NULL,
           PROJECTION_MASTER_SID       INT NOT NULL,
           SCREEN_NAME                 VARCHAR(50) NOT NULL,
           FIELD_NAME                  VARCHAR(30) NOT NULL,
           FIELD_VALUES                VARCHAR(4000) NOT NULL
        )
  END
GO
---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'M_PROJECTION_SELECTION'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_M_PROJECTION_SELECTION_M_PROJECTION_SELECTION_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[M_PROJECTION_SELECTION]
        ADD CONSTRAINT PK_M_PROJECTION_SELECTION_M_PROJECTION_SELECTION_SID PRIMARY KEY(M_PROJECTION_SELECTION_SID)
  END
GO 