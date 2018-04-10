IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE [dbo].[PRICE_SCHEDULE_INTERFACE]
        (
           [PRICE_SCHEDULE_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [PRICE_SCHEDULE_ID]           VARCHAR(38) NOT NULL,
           [PRICE_SCHEDULE_NO]           VARCHAR(50) NULL,
           [PRICE_SCHEDULE_NAME]         VARCHAR(100) NULL,
           [PRICE_SCHEDULE_TYPE]         VARCHAR(50) NULL,
           [PRICE_SCHEDULE_CATEGORY]     VARCHAR(50) NULL,
           [TRADE_CLASS]                 VARCHAR(50) NULL,
           [PRICE_SCHEDULE_DESIGNATION]  VARCHAR(50) NULL,
           [PARENT_PRICE_SCHEDULE_ID]    VARCHAR(50) NULL,
           [PARENT_PRICE_SCHEDULE_NAME]  VARCHAR(100) NULL,
           [PRICE_SCHEDULE_STATUS]       VARCHAR(20) NOT NULL,
           [PRICE_SCHEDULE_START_DATE]   DATETIME NOT NULL,
           [PRICE_SCHEDULE_END_DATE]     DATETIME NULL,
           [ITEM_ID]                     VARCHAR(38) NOT NULL,
           [IDENTIFIER_CODE_QUALIFIER]   VARCHAR(20) NULL,
           [ITEM_NO]                     VARCHAR(50) NULL,
           [ITEM_NAME]                   VARCHAR(100) NULL,
           [ATTACHED_STATUS]             VARCHAR(20) NOT NULL,
           [ATTACHED_DATE]               DATETIME NOT NULL,
           [CONTRACT_PRICE_START_DATE]   DATETIME NOT NULL,
           [CONTRACT_PRICE_END_DATE]     DATETIME NULL,
           [PRICE_TYPE]                  VARCHAR(50) NOT NULL,
           [CONTRACT_PRICE]              NUMERIC(20, 6) NULL,
           [PRICE_REVISION]              NUMERIC(20, 6) NULL,
           [REVISION_DATE]               DATETIME NULL,
           [PRICE_PROTECTION_START_DATE] DATETIME NULL,
           [PRICE_PROTECTION_END_DATE]   DATETIME NULL,
           [PRICE_TOLERANCE_TYPE]        VARCHAR(50) NULL,
           [PRICE_TOLERANCE]             NUMERIC(20, 6) NULL,
           [BASE_PRICE]                  NUMERIC(20, 6) NULL,
           [PRICE_TOLERANCE_FREQUENCY]   VARCHAR(50) NULL,
           [PRICE_TOLERANCE_INTERVAL]    NUMERIC(38, 0) NULL,
           [PRICE_PLAN_ID]               VARCHAR(38) NULL,
           [PRICE_PLAN_NAME]             VARCHAR(100) NULL,
           [SUGGESTED_PRICE]             NUMERIC(20, 6) NULL,
           [CONTRACT_ID]                 VARCHAR(50) NOT NULL,
           [COMPANY_FAMILY_PLAN_ID]      VARCHAR(50) NULL,
           [MEMBER_FAMILY_PLAN_ID]       VARCHAR(50) NULL,
           [ITEM_FAMILY_PLAN_ID]         VARCHAR(50) NOT NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [PRICE]                       NUMERIC(20, 6) NOT NULL
        )
  END
GO

IF EXISTS(SELECT 'X'
          FROM   INFORMATION_SCHEMA.COLUMNS
          WHERE  TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE'
                 AND TABLE_SCHEMA = 'dbo'
                 AND COLUMN_NAME = 'TRADE_CLASS'
                 AND DATA_TYPE = 'VARCHAR'
                 AND CHARACTER_MAXIMUM_LENGTH = 50)
  BEGIN
      ALTER TABLE [dbo].[PRICE_SCHEDULE_INTERFACE]
        ALTER COLUMN [TRADE_CLASS] VARCHAR(100)
  END
GO


IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'PRICE_SCHEDULE_ID'
                  AND TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE'
                  AND CHARACTER_MAXIMUM_LENGTH = '38')
  BEGIN
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ALTER COLUMN PRICE_SCHEDULE_ID VARCHAR(50) NOT NULL

END
GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE'
                  AND COLUMN_NAME = 'PRICE_SCHEDULE_NO'
                  AND IS_NULLABLE = 'YES')
  BEGIN
	  UPDATE PRICE_SCHEDULE_INTERFACE SET PRICE_SCHEDULE_NO = 0 WHERE PRICE_SCHEDULE_NO IS NULL
     
  END 
  GO

 IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE'
                  AND COLUMN_NAME = 'PRICE_SCHEDULE_NO'
                  AND IS_NULLABLE = 'YES')
  BEGIN
	  
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ALTER COLUMN PRICE_SCHEDULE_NO VARCHAR(50) NOT NULL
  END 
  GO
  
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE'
                  AND COLUMN_NAME = 'PRICE_SCHEDULE_NAME'
                  AND IS_NULLABLE = 'YES')
  BEGIN
	  UPDATE PRICE_SCHEDULE_INTERFACE SET PRICE_SCHEDULE_NAME = 0 WHERE PRICE_SCHEDULE_NAME IS NULL
     
  END
  GO
  
  IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE'
                  AND COLUMN_NAME = 'PRICE_SCHEDULE_NAME'
                  AND IS_NULLABLE = 'YES')
  BEGIN
	
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ALTER COLUMN PRICE_SCHEDULE_NAME VARCHAR(100) NOT NULL
  END
  GO
 
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'INTERNAL_NOTES' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD INTERNAL_NOTES VARCHAR(100) NULL
END
  GO
  
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'PRICE_PROTECTION_STATUS' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD PRICE_PROTECTION_STATUS VARCHAR(50) NULL
END
  GO
  
IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'PRICE_PROTECTION_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD PRICE_PROTECTION_PRICE_TYPE VARCHAR(50) NULL
END
  GO
  
  IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'NEP' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD NEP NUMERIC(22,6) NULL
END
  GO
    IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'NEP_FORMULA' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD NEP_FORMULA VARCHAR(100) NULL
END
  GO
  
      IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'BASE_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD BASE_PRICE_TYPE VARCHAR(50) NULL
END
  GO
      IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'NET_BASE_PRICE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD NET_BASE_PRICE NUMERIC(22,6) NULL
END
  GO
    IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'NET_BASE_PRICE_FORMULA' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD NET_BASE_PRICE_FORMULA VARCHAR(100) NULL
END
  GO
  
   IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'SUBSEQUENT_PERIOD_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD SUBSEQUENT_PERIOD_PRICE_TYPE VARCHAR(50) NULL
END
  GO
   IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'NET_SUBSEQUENT_PERIOD_PRICE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD NET_SUBSEQUENT_PERIOD_PRICE VARCHAR(50) NULL
END
  GO
  
     IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'NET_SUBSEQUENT_PERIOD_PRICE_FORMULA' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD NET_SUBSEQUENT_PERIOD_PRICE_FORMULA VARCHAR(50) NULL
END
  GO
  
       IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'MAX_INCREMENTAL_CHANGE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD MAX_INCREMENTAL_CHANGE NUMERIC(22,6) NULL
END
  GO
  
         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'RESET_ELIGIBLE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD RESET_ELIGIBLE VARCHAR(10) NULL
END
  GO
      IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'RESET_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD RESET_TYPE VARCHAR(50) NULL
END
  GO
  
  IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'RESET_DATE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD RESET_DATE DATETIME NULL
END
  GO
  
        IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'RESET_INTERVAL' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD RESET_INTERVAL NUMERIC(22,6) NULL
END
  GO
  
        IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'RESET_FREQUENCY' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD RESET_FREQUENCY VARCHAR(50) NULL
END
  GO
  

  
  
         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'RESET_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD RESET_PRICE_TYPE VARCHAR(50) NULL
END
  GO
         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'NET_RESET_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD NET_RESET_PRICE_TYPE VARCHAR(50) NULL
END
  GO
         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'NET_RESET_PRICE_FORMULA' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD NET_RESET_PRICE_FORMULA VARCHAR(100) NULL
END
  GO
         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'NET_PRICE_TYPE' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD NET_PRICE_TYPE VARCHAR(50) NULL
END
  GO
         IF NOT EXISTS(
    SELECT 1 FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE' AND COLUMN_NAME  = 'NET_PRICE_TYPE_FORMULA' AND TABLE_SCHEMA = 'DBO')
    BEGIN
                
      ALTER TABLE PRICE_SCHEDULE_INTERFACE
        ADD NET_PRICE_TYPE_FORMULA VARCHAR(100) NULL
END
  GO 
 -------------------------AGN-909---------------------------------- 
 
  IF EXISTS (
		SELECT 1
		FROM INFORMATION_SCHEMA.COLUMNS
		WHERE TABLE_NAME = 'PRICE_SCHEDULE_INTERFACE'
			AND COLUMN_NAME = 'NET_BASE_PRICE'
			AND DATA_TYPE = 'NUMERIC'
			AND TABLE_SCHEMA = 'DBO'
		)
BEGIN
	ALTER TABLE PRICE_SCHEDULE_INTERFACE

	ALTER COLUMN NET_BASE_PRICE VARCHAR(13) NULL
END
GO
-------------------------AGN-909 ends---------------------------------- 

  