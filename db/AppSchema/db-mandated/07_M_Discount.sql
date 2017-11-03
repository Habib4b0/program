------------------------------------------- M_DISCOUNT_PROJECTION -------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'M_DISCOUNT_PROJECTION'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[M_DISCOUNT_PROJECTION]
        (
           PROJECTION_DETAILS_SID INT NOT NULL,
           PERIOD_SID             INT NOT NULL,
           PROJECTION_SALES       NUMERIC(22, 6),
           PROJECTION_RATE        NUMERIC(22, 6),
		   PROJECTION_RPU  		  NUMERIC(22,6),
           [USER_ID]              INT NOT NULL,
           [SESSION_ID]           INT NOT NULL,
           LAST_MODIFIED_DATE     DATETIME NOT NULL,
		   SAVE_FLAG			  BIT NULL
        )
  END

GO
IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'M_DISCOUNT_PROJECTION'
			AND COLUMN_NAME = 'PROJECTION_RPU'
		)
BEGIN
	ALTER TABLE M_DISCOUNT_PROJECTION ADD PROJECTION_RPU  NUMERIC(22,6)
END
GO
---------------------PRIMARY KEY CONSTRAINT------------------------
IF EXISTS(SELECT 1
          FROM   M_DISCOUNT_PROJECTION
          WHERE  SAVE_FLAG IS NULL
                  OR SAVE_FLAG = 0)
  BEGIN
      DELETE FROM M_DISCOUNT_PROJECTION
      WHERE  SAVE_FLAG = 0
              OR SAVE_FLAG IS NULL
  END 

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
           WHERE  TABLE_NAME = 'M_DISCOUNT_PROJECTION'
                  AND CONSTRAINT_NAME = 'PK_M_DISCOUNT_PROJECTION_PROJECTION_DETAILS_SID_PERIOD_SID_USER_ID_SESSION_ID')
  BEGIN
      
ALTER TABLE M_DISCOUNT_PROJECTION DROP CONSTRAINT PK_M_DISCOUNT_PROJECTION_PROJECTION_DETAILS_SID_PERIOD_SID_USER_ID_SESSION_ID

  END 
ELSE 
BEGIN

IF NOT EXISTS(SELECT 1 FROM  INFORMATION_SCHEMA.TABLE_CONSTRAINTS
           WHERE  TABLE_NAME = 'M_DISCOUNT_PROJECTION'
                  AND CONSTRAINT_NAME = 'PK_M_DISCOUNT_PROJECTION_PROJECTION_DETAILS_SID_PERIOD_SID')
ALTER TABLE M_DISCOUNT_PROJECTION ADD CONSTRAINT PK_M_DISCOUNT_PROJECTION_PROJECTION_DETAILS_SID_PERIOD_SID PRIMARY KEY(PROJECTION_DETAILS_SID,PERIOD_SID)

END
GO

IF COL_LENGTH('M_DISCOUNT_PROJECTION', 'USER_ID') IS NOT NULL
  BEGIN
      ALTER TABLE M_DISCOUNT_PROJECTION
        DROP COLUMN [USER_ID]
  END
  GO
IF COL_LENGTH('M_DISCOUNT_PROJECTION', 'SESSION_ID') IS NOT NULL
  BEGIN
      ALTER TABLE M_DISCOUNT_PROJECTION
        DROP COLUMN SESSION_ID
  END
GO

IF  EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'M_DISCOUNT_PROJECTION'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_M_DISCOUNT_PROJECTION_LAST_MODIFIED_DATE')
  BEGIN
       ALTER TABLE M_DISCOUNT_PROJECTION DROP CONSTRAINT DF_M_DISCOUNT_PROJECTION_LAST_MODIFIED_DATE
  END
GO
IF COL_LENGTH('M_DISCOUNT_PROJECTION', 'LAST_MODIFIED_DATE') IS NOT NULL
  BEGIN
   

      ALTER TABLE M_DISCOUNT_PROJECTION
        DROP COLUMN LAST_MODIFIED_DATE
  END 

  GO


------------------------------------------- M_ACTUAL_DISCOUNT -------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'M_ACTUAL_DISCOUNT'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[M_ACTUAL_DISCOUNT]
        (
           PROJECTION_DETAILS_SID INT NOT NULL,
           PERIOD_SID             INT NOT NULL,
           ACTUAL_SALES           NUMERIC(22, 6),
           ACTUAL_RATE            NUMERIC(22, 6),
		   ACTUAL_RPU  			  NUMERIC(22,6),
		   [USER_ID]              INT NOT NULL,
           [SESSION_ID]           INT NOT NULL,
           LAST_MODIFIED_DATE     DATETIME NOT NULL,
		   SAVE_FLAG			  BIT NULL
        )
  END

GO
IF NOT EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'M_ACTUAL_DISCOUNT'
			AND COLUMN_NAME = 'ACTUAL_RPU'
		)
BEGIN
	ALTER TABLE M_ACTUAL_DISCOUNT ADD ACTUAL_RPU  NUMERIC(22,6)
END
GO
---------------------PRIMARY KEY CONSTRAINT------------------------
IF EXISTS(SELECT 1
          FROM   M_ACTUAL_DISCOUNT
          WHERE  SAVE_FLAG IS NULL
                  OR SAVE_FLAG = 0)
  BEGIN
      DELETE FROM M_ACTUAL_DISCOUNT
       WHERE  SAVE_FLAG = 0
              OR SAVE_FLAG IS NULL
  END
  GO
IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
           WHERE  TABLE_NAME = 'M_ACTUAL_DISCOUNT'
                  AND CONSTRAINT_NAME = 'PK_M_ACTUAL_DISCOUNT_PROJECTION_DETAILS_SID_PERIOD_SID_USER_ID_SESSION_ID')
  BEGIN
      ALTER TABLE M_ACTUAL_DISCOUNT
        DROP CONSTRAINT PK_M_ACTUAL_DISCOUNT_PROJECTION_DETAILS_SID_PERIOD_SID_USER_ID_SESSION_ID
  END 
ELSE 
BEGIN

IF NOT EXISTS(SELECT 1 FROM  INFORMATION_SCHEMA.TABLE_CONSTRAINTS
           WHERE  TABLE_NAME = 'M_ACTUAL_DISCOUNT'
                  AND CONSTRAINT_NAME = 'PK_M_ACTUAL_DISCOUNT_PROJECTION_DETAILS_SID_PERIOD_SID')
ALTER TABLE M_ACTUAL_DISCOUNT ADD CONSTRAINT PK_M_ACTUAL_DISCOUNT_PROJECTION_DETAILS_SID_PERIOD_SID PRIMARY KEY(PROJECTION_DETAILS_SID,PERIOD_SID)

END


GO

---------------------DEFAULT_CONSTRAINTS------------------------

IF COL_LENGTH('M_ACTUAL_DISCOUNT', 'USER_ID') IS NOT NULL
  BEGIN
      ALTER TABLE M_ACTUAL_DISCOUNT
        DROP COLUMN [USER_ID]
  END
  GO
IF COL_LENGTH('M_ACTUAL_DISCOUNT', 'SESSION_ID') IS NOT NULL
  BEGIN
      ALTER TABLE M_ACTUAL_DISCOUNT
        DROP COLUMN SESSION_ID
  END
  GO
IF  EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'M_ACTUAL_DISCOUNT'
                      AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                      AND NAME = 'DF_M_ACTUAL_DISCOUNT_LAST_MODIFIED_DATE')
  BEGIN
       ALTER TABLE M_ACTUAL_DISCOUNT DROP CONSTRAINT DF_M_ACTUAL_DISCOUNT_LAST_MODIFIED_DATE
  END

GO

IF COL_LENGTH('M_ACTUAL_DISCOUNT', 'LAST_MODIFIED_DATE') IS NOT NULL
  BEGIN
 

      ALTER TABLE M_ACTUAL_DISCOUNT
        DROP COLUMN LAST_MODIFIED_DATE
  END 

GO