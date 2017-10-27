-------------------------------------------------------------ARM_ACC_CONFIG--------------------------------------- 
IF NOT EXISTS (SELECT 'X' 
               FROM   INFORMATION_SCHEMA.TABLES 
               WHERE  TABLE_NAME = 'ARM_ACC_CONFIG' 
                      AND TABLE_SCHEMA = 'DBO') 
  BEGIN 
      CREATE TABLE ARM_ACC_CONFIG 
        (  
           GL_COMPANY_MASTER_SID INT NOT NULL, 
           BU_COMPANY_MASTER_SID INT NOT NULL, 
           ACCOUNT               VARCHAR(50) NOT NULL, 
           BRAND_MASTER_SID      INT NOT NULL, 
           COST_CENTER           VARCHAR(50) NULL, 
           CREATED_BY            INT NULL, 
           CREATED_DATE          DATETIME NULL, 
           MODIFIED_BY           INT NULL, 
           MODIFIED_DATE         DATETIME NULL,
		   SOURCE                VARCHAR(50) NULL
        ) 
  END 

GO 

IF NOT EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'ARM_ACC_CONFIG'
                  AND COLUMN_NAME = 'ARM_ACC_CONFIG_SID'
                  AND TABLE_SCHEMA = 'DBO'
				  )
  BEGIN
      ALTER TABLE ARM_ACC_CONFIG
       ADD  ARM_ACC_CONFIG_SID INT IDENTITY(1,1) NOT NULL
  END
--GAL-11498 Changes for Delete Button functionality
IF NOT EXISTS (SELECT 1 
               FROM   INFORMATION_SCHEMA.COLUMNS 
               WHERE  TABLE_NAME = 'ARM_ACC_CONFIG' 
                      AND COLUMN_NAME = 'DELETE_CHECK_RECORD' 
                      AND TABLE_SCHEMA = 'DBO') 
  BEGIN 
      ALTER TABLE ARM_ACC_CONFIG 
        ADD DELETE_CHECK_RECORD BIT NULL
  END 

GO


------ DEFAULT CONSTRAINT FOR ARM_ACC_CONFIG---------------------------- 
IF NOT EXISTS (SELECT 'X' 
               FROM   SYS.DEFAULT_CONSTRAINTS 
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ARM_ACC_CONFIG') 
                      AND NAME = 'DF_ARM_ACC_CONFIG_CREATED_BY') 
  BEGIN 
      ALTER TABLE [DBO].ARM_ACC_CONFIG 
        ADD CONSTRAINT DF_ARM_ACC_CONFIG_CREATED_BY DEFAULT (1) FOR CREATED_BY 
  END 

GO 

IF NOT EXISTS (SELECT 'X' 
               FROM   SYS.DEFAULT_CONSTRAINTS 
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ARM_ACC_CONFIG') 
                      AND NAME = 'DF_ARM_ACC_CONFIG_CREATED_DATE') 
  BEGIN 
      ALTER TABLE [DBO].ARM_ACC_CONFIG 
        ADD CONSTRAINT DF_ARM_ACC_CONFIG_CREATED_DATE DEFAULT (GETDATE()) FOR CREATED_DATE 
  END 

GO 

IF NOT EXISTS (SELECT 'X' 
               FROM   SYS.DEFAULT_CONSTRAINTS 
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ARM_ACC_CONFIG') 
                      AND NAME = 'DF_ARM_ACC_CONFIG_MODIFIED_BY') 
  BEGIN 
      ALTER TABLE [DBO].ARM_ACC_CONFIG 
        ADD CONSTRAINT DF_ARM_ACC_CONFIG_MODIFIED_BY DEFAULT (1) FOR MODIFIED_BY 
  END 

GO 

IF NOT EXISTS (SELECT 'X' 
               FROM   SYS.DEFAULT_CONSTRAINTS 
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ARM_ACC_CONFIG') 
                      AND NAME = 'DF_ARM_ACC_CONFIG_MODIFIED_DATE') 
  BEGIN 
      ALTER TABLE [DBO].ARM_ACC_CONFIG 
        ADD CONSTRAINT DF_ARM_ACC_CONFIG_MODIFIED_DATE DEFAULT (GETDATE()) FOR MODIFIED_DATE 
  END 

GO 

------ Default Constraints for GAL-11498
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('DBO.ARM_ACC_CONFIG')
                      AND NAME = 'DF_ARM_ACC_CONFIG_DELETE_CHECK_RECORD')
  BEGIN
      ALTER TABLE [DBO].ARM_ACC_CONFIG
        ADD CONSTRAINT DF_ARM_ACC_CONFIG_DELETE_CHECK_RECORD DEFAULT (0) FOR DELETE_CHECK_RECORD
  END

GO


---------UNIQUE KEY CONSTRAINTS-------------------- 
IF NOT EXISTS (SELECT 'X' 
               FROM   SYS.KEY_CONSTRAINTS 
               WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.ARM_ACC_CONFIG') 
                      AND NAME = 'UQ_ARM_ACC_CONFIG_GL_COMPANY_MASTER_SID_BU_COMPANY_MASTER_SID_ACCOUNT_BRAND_MASTER_SID_COST_CENTER'
                      AND TYPE = 'UQ') 
  BEGIN 
      ALTER TABLE [DBO].ARM_ACC_CONFIG 
        ADD CONSTRAINT UQ_ARM_ACC_CONFIG_GL_COMPANY_MASTER_SID_BU_COMPANY_MASTER_SID_ACCOUNT_BRAND_MASTER_SID_COST_CENTER UNIQUE (GL_COMPANY_MASTER_SID, BU_COMPANY_MASTER_SID, ACCOUNT, BRAND_MASTER_SID, COST_CENTER)
  END 

GO 

DECLARE @SQL NVARCHAR(MAX) 
DECLARE @TABLENAME VARCHAR(100) 
DECLARE @STATSNAME VARCHAR(200) 
DECLARE @TABLENAME1 VARCHAR(100) 
DECLARE @SCHEMANAME VARCHAR(30) 
DECLARE @SCHEMANAME1 VARCHAR(30) 

SET @TABLENAME1 = 'ARM_ACC_CONFIG'--TABLE NAME 
SET @SCHEMANAME1 ='DBO' -- SCHEMA NAME 

IF EXISTS (SELECT 'X' 
           FROM   SYS.STATS S 
                  JOIN SYS.TABLES T 
                    ON S.OBJECT_ID = T.OBJECT_ID 
           WHERE  AUTO_CREATED = 1 
                  AND NOT EXISTS (SELECT 1 
                                  FROM   SYS.INDEXES 
                                  WHERE  S.NAME = NAME) 
                  AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME 
                  AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1) 
  BEGIN 
      DECLARE CUR CURSOR STATIC FOR 
        SELECT OBJECT_NAME(S.OBJECT_ID) AS 'TABLENAME', 
               S.NAME                   AS 'STATSNAME', 
               SCHEMA_NAME(T.SCHEMA_ID) AS 'SCHEMA_NAME' 
        FROM   SYS.STATS S 
               JOIN SYS.TABLES T 
                 ON S.OBJECT_ID = T.OBJECT_ID 
        WHERE  AUTO_CREATED = 1 
               AND NOT EXISTS (SELECT 1 
                               FROM   SYS.INDEXES 
                               WHERE  S.NAME = NAME) 
               AND OBJECT_NAME(S.OBJECT_ID) = @TABLENAME1 -- TABLE NAME 
               AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1 

      OPEN CUR 

      FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME 

      WHILE @@FETCH_STATUS = 0 
        BEGIN 
            SET @SQL = 'DROP STATISTICS ' + QUOTENAME(@SCHEMANAME) 
                       + '.' + QUOTENAME(@TABLENAME) + '.' 
                       + QUOTENAME(@STATSNAME) 

            --PRINT @SQL 
            EXEC SP_EXECUTESQL 
              @SQL 

            FETCH NEXT FROM CUR INTO @TABLENAME, @STATSNAME, @SCHEMANAME 
        END 

      CLOSE CUR 

      DEALLOCATE CUR 
  END 

DECLARE @STATS NVARCHAR(MAX) 
DECLARE CUR1 CURSOR STATIC FOR 
  SELECT 'CREATE STATISTICS ' + QUOTENAME(C.NAME) 
         + ' ON ' + QUOTENAME(SCHEMA_NAME(SCHEMA_ID)) 
         + '.' + QUOTENAME(T.NAME) + ' (' 
         + QUOTENAME(C.NAME) + ') WITH FULLSCAN' 
  FROM   SYS.TABLES T 
         JOIN SYS.COLUMNS C 
           ON T.OBJECT_ID = C.OBJECT_ID 
  WHERE  NOT EXISTS (SELECT 1 
                     FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS TC 
                            INNER JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE CC 
                                    ON TC.CONSTRAINT_NAME = CC.CONSTRAINT_NAME 
                     WHERE  CC.TABLE_NAME = T.NAME 
                            AND CC.TABLE_SCHEMA = SCHEMA_NAME(SCHEMA_ID) 
                            AND C.NAME = COLUMN_NAME) 
         AND NOT EXISTS (SELECT 1 
                         FROM   SYS.STATS S 
                         WHERE  S.OBJECT_ID = C.OBJECT_ID 
                                AND S.NAME = C.NAME) 
         AND T.NAME = @TABLENAME1 -- TABLE NAME 
         AND SCHEMA_NAME(SCHEMA_ID) = @SCHEMANAME1 
  ORDER  BY T.NAME 

OPEN CUR1 

FETCH NEXT FROM CUR1 INTO @STATS 

WHILE @@FETCH_STATUS = 0 
  BEGIN 
      --PRINT @STATS 
      EXEC SP_EXECUTESQL 
        @STATS 

      FETCH NEXT FROM CUR1 INTO @STATS 
  END 

CLOSE CUR1 

DEALLOCATE CUR1 

GO 

-------------------------------------------------------------HIST_ARM_ACC_CONFIG--------------------------------------- 
IF NOT EXISTS (SELECT 'X' 
               FROM   INFORMATION_SCHEMA.TABLES 
               WHERE  TABLE_NAME = 'HIST_ARM_ACC_CONFIG' 
                      AND TABLE_SCHEMA = 'DBO') 
  BEGIN 
      CREATE TABLE HIST_ARM_ACC_CONFIG 
        ( 
           GL_COMPANY_MASTER_SID INT NOT NULL, 
           BU_COMPANY_MASTER_SID INT NOT NULL, 
           ACCOUNT               VARCHAR(50) NOT NULL, 
           BRAND_MASTER_SID      INT NOT NULL, 
           COST_CENTER           VARCHAR(50) NULL, 
           CREATED_BY            INT NULL, 
           CREATED_DATE          DATETIME NULL, 
           MODIFIED_BY           INT NULL, 
           MODIFIED_DATE         DATETIME NULL, 
           ACTION_FLAG           CHAR(1) NOT NULL, 
           ACTION_DATE           DATETIME NOT NULL,
		   SOURCE                VARCHAR(50) NULL
        ) 
  END 

GO 

IF NOT EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ARM_ACC_CONFIG'
                  AND COLUMN_NAME = 'ARM_ACC_CONFIG_SID'
                  AND TABLE_SCHEMA = 'DBO'
				  )
  BEGIN
      ALTER TABLE HIST_ARM_ACC_CONFIG
       ADD  ARM_ACC_CONFIG_SID INT  NOT NULL
  END

--GAL-11498 Changes
IF NOT EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = 'HIST_ARM_ACC_CONFIG'
                  AND COLUMN_NAME = 'DELETE_CHECK_RECORD'
                  AND TABLE_SCHEMA = 'DBO'
				  )
  BEGIN
      ALTER TABLE HIST_ARM_ACC_CONFIG
      ADD DELETE_CHECK_RECORD BIT NULL
  END

IF EXISTS (SELECT 'X' 
           FROM   SYS.TRIGGERS 
           WHERE  [NAME] = N'TRG_ARM_ACC_CONFIG_UPD') 
  BEGIN 
      DROP TRIGGER DBO.TRG_ARM_ACC_CONFIG_UPD 
  END 

GO 

CREATE TRIGGER [DBO].TRG_ARM_ACC_CONFIG_UPD 
ON [DBO].ARM_ACC_CONFIG 
AFTER UPDATE 
AS 
  BEGIN 
  SET NOCOUNT ON
      IF EXISTS(SELECT * 
                FROM   INSERTED) 
         AND EXISTS(SELECT * 
                    FROM   DELETED) 
        INSERT INTO HIST_ARM_ACC_CONFIG 
                    (
					ARM_ACC_CONFIG_SID,
					 GL_COMPANY_MASTER_SID, 
                     BU_COMPANY_MASTER_SID, 
                     ACCOUNT, 
                     BRAND_MASTER_SID, 
                     COST_CENTER, 
                     CREATED_BY, 
                     CREATED_DATE, 
                     MODIFIED_BY, 
                     MODIFIED_DATE, 
                     ACTION_FLAG, 
                     ACTION_DATE,
					 SOURCE,
					 DELETE_CHECK_RECORD) 
        SELECT ARM_ACC_CONFIG_SID,
		       GL_COMPANY_MASTER_SID, 
               BU_COMPANY_MASTER_SID, 
               ACCOUNT, 
               BRAND_MASTER_SID, 
               COST_CENTER, 
               CREATED_BY, 
               CREATED_DATE, 
               MODIFIED_BY, 
               MODIFIED_DATE, 
               'C', 
               GETDATE(),
			   SOURCE,
			   DELETE_CHECK_RECORD
        FROM   INSERTED 
  END 

GO 

IF EXISTS (SELECT 'X' 
           FROM   SYS.TRIGGERS 
           WHERE  [NAME] = N'TRG_ARM_ACC_CONFIG_INS') 
  BEGIN 
      DROP TRIGGER DBO.TRG_ARM_ACC_CONFIG_INS 
  END 

GO 

CREATE TRIGGER [DBO].TRG_ARM_ACC_CONFIG_INS 
ON [DBO].ARM_ACC_CONFIG 
AFTER INSERT 
AS 
  BEGIN 
  SET NOCOUNT ON
      IF EXISTS(SELECT * 
                FROM   INSERTED) 
        INSERT INTO HIST_ARM_ACC_CONFIG 
                    (
					 ARM_ACC_CONFIG_SID,
					 GL_COMPANY_MASTER_SID, 
                     BU_COMPANY_MASTER_SID, 
                     ACCOUNT, 
                     BRAND_MASTER_SID, 
                     COST_CENTER, 
                     CREATED_BY, 
                     CREATED_DATE, 
                     MODIFIED_BY, 
                     MODIFIED_DATE, 
                     ACTION_FLAG, 
                     ACTION_DATE,
					 SOURCE,
					 DELETE_CHECK_RECORD) 
        SELECT ARM_ACC_CONFIG_SID,
		       GL_COMPANY_MASTER_SID, 
               BU_COMPANY_MASTER_SID, 
               ACCOUNT, 
               BRAND_MASTER_SID, 
               COST_CENTER, 
               CREATED_BY, 
               CREATED_DATE, 
               MODIFIED_BY, 
               MODIFIED_DATE, 
               'A', 
               GETDATE(),
			   SOURCE,
			   DELETE_CHECK_RECORD
        FROM   INSERTED 
  END 

GO 

IF EXISTS (SELECT 'X' 
           FROM   SYS.TRIGGERS 
           WHERE  [NAME] = N'TRG_ARM_ACC_CONFIG_DEL') 
  BEGIN 
      DROP TRIGGER DBO.TRG_ARM_ACC_CONFIG_DEL 
  END 

GO 

CREATE TRIGGER [DBO].TRG_ARM_ACC_CONFIG_DEL 
ON [DBO].ARM_ACC_CONFIG 
AFTER DELETE 
AS 
  BEGIN 
  SET NOCOUNT ON
      IF EXISTS(SELECT * 
                FROM   DELETED) 
        INSERT INTO HIST_ARM_ACC_CONFIG 
                    (
					 ARM_ACC_CONFIG_SID,
					 GL_COMPANY_MASTER_SID, 
                     BU_COMPANY_MASTER_SID, 
                     ACCOUNT, 
                     BRAND_MASTER_SID, 
                     COST_CENTER, 
                     CREATED_BY, 
                     CREATED_DATE, 
                     MODIFIED_BY, 
                     MODIFIED_DATE, 
                     ACTION_FLAG, 
                     ACTION_DATE,
					 SOURCE,
					 DELETE_CHECK_RECORD) 
        SELECT ARM_ACC_CONFIG_SID,
		       GL_COMPANY_MASTER_SID, 
               BU_COMPANY_MASTER_SID, 
               ACCOUNT, 
               BRAND_MASTER_SID, 
               COST_CENTER, 
               CREATED_BY, 
               CREATED_DATE, 
               MODIFIED_BY, 
               MODIFIED_DATE, 
               'D', 
               GETDATE(),
			   SOURCE,
			   DELETE_CHECK_RECORD
        FROM   DELETED 
  END 

GO 