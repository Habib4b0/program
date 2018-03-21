--------------------------------------------------- CCP_DETAILS --------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CCP_DETAILS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE CCP_DETAILS
        (
           CCP_DETAILS_SID     INT IDENTITY(1, 1),
           CONTRACT_MASTER_SID INT,
           COMPANY_MASTER_SID  INT,
           ITEM_MASTER_SID     INT
        )
  END
GO
---------------------PRIMARY KEY CONSTRAINT--------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'CCP_DETAILS'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_CCP_DETAILS_CCP_DETAILS_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[CCP_DETAILS]
        ADD CONSTRAINT PK_CCP_DETAILS_CCP_DETAILS_SID PRIMARY KEY(CCP_DETAILS_SID)
  END
GO
----------------------unique key-----------------------------------
IF EXISTS (SELECT NAME
           FROM   SYS.TABLES
           WHERE  NAME = 'CCP_DETAILS')
  BEGIN
      IF NOT EXISTS (SELECT 1
                     FROM   SYS.KEY_CONSTRAINTS
                     WHERE  TYPE_DESC = 'UNIQUE_CONSTRAINT'
                            AND PARENT_OBJECT_ID = Object_id('CCP_DETAILS')
                            AND NAME = 'UQ_CCP_DETAILS_CONTRACT_MASTER_SID_COMPANY_MASTER_SID_ITEM_MASTER_SID')
        BEGIN
            ALTER TABLE CCP_DETAILS
              ADD CONSTRAINT UQ_CCP_DETAILS_CONTRACT_MASTER_SID_COMPANY_MASTER_SID_ITEM_MASTER_SID UNIQUE(CONTRACT_MASTER_SID, COMPANY_MASTER_SID, ITEM_MASTER_SID)
        END
  END

GO


------------------------------------------------- CCP_MAP --------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CCP_MAP'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE CCP_MAP
        (
           CCP_MAP_SID            INT IDENTITY(1, 1),
           CCP_DETAILS_SID        INT,
           RELATIONSHIP_LEVEL_SID INT
        )
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'CCP_MAP'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_CCP_MAP_CCP_MAP_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[CCP_MAP]
        ADD CONSTRAINT PK_CCP_MAP_CCP_MAP_SID PRIMARY KEY(CCP_MAP_SID)
  END

GO

--------------------------------------------------------- PROJECTION_MASTER -----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'PROJECTION_MASTER'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
BEGIN
CREATE TABLE PROJECTION_MASTER
  (
     PROJECTION_MASTER_SID          INT IDENTITY(1, 1),
     PROJECTION_NAME                VARCHAR(100),
     PROJECTION_DESCRIPTION         VARCHAR(100),
     FORECASTING_TYPE               VARCHAR(50),
     FROM_DATE                      DATETIME NOT NULL,
     TO_DATE                        DATETIME NOT NULL,
	 DISCOUNT_TYPE				    INT,
     CUSTOMER_HIERARCHY_SID         INT,
     CUSTOMER_HIERARCHY_LEVEL       SMALLINT,
     CUSTOMER_HIER_VERSION_NO       INT,
     COMPANY_GROUP_SID              INT,
     BRAND_TYPE                     BIT,
     CUSTOMER_HIERARCHY_INNER_LEVEL SMALLINT,
     CUST_RELATIONSHIP_BUILDER_SID  INT,
     COMPANY_MASTER_SID             INT,
     PRODUCT_HIERARCHY_SID          INT,
     PRODUCT_HIERARCHY_LEVEL        SMALLINT,
     PRODUCT_HIER_VERSION_NO        INT,
     ITEM_GROUP_SID                 INT,
     PRODUCT_HIERARCHY_INNER_LEVEL  SMALLINT,
     PROD_RELATIONSHIP_BUILDER_SID  INT,
     SAVE_FLAG                      BIT NOT NULL,
     IS_APPROVED                    VARCHAR(2),
     CREATED_BY                     INT,
     CREATED_DATE                   DATETIME,
     MODIFIED_BY                    INT,
     MODIFIED_DATE                  DATETIME
  )

END 


GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'PROJECTION_MASTER'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_PROJECTION_MASTER_PROJECTION_MASTER_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[PROJECTION_MASTER]
        ADD CONSTRAINT PK_PROJECTION_MASTER_PROJECTION_MASTER_SID PRIMARY KEY(PROJECTION_MASTER_SID)
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  PARENT_OBJECT_ID = Object_id('PROJECTION_MASTER')
                      AND NAME = 'DF_PROJECTION_MASTER_SAVE_FLAG')
  BEGIN
      ALTER TABLE [DBO].[PROJECTION_MASTER]
        ADD CONSTRAINT [DF_PROJECTION_MASTER_SAVE_FLAG] DEFAULT (0) FOR SAVE_FLAG
  END

GO

-------------------- ALTER SCRIPT------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PROJECTION_MASTER'
                      AND COLUMN_NAME = 'DISCOUNT_TYPE')
  BEGIN
      ALTER TABLE [DBO].[PROJECTION_MASTER]
        ADD DISCOUNT_TYPE INT
  END

GO

-----------------------------column addition script-------------------------------------------
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PROJECTION_MASTER'
                      AND COLUMN_NAME = 'BUSINESS_UNIT'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PROJECTION_MASTER
        ADD  BUSINESS_UNIT INT 
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PROJECTION_MASTER'
                      AND COLUMN_NAME = 'FILE_MANAGEMENT_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PROJECTION_MASTER
        ADD FILE_MANAGEMENT_SID INT
  END

GO


IF  EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PROJECTION_MASTER'
                      AND COLUMN_NAME = 'FILE_MANAGEMENT_SID'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE PROJECTION_MASTER
        DROP COLUMN FILE_MANAGEMENT_SID 
  END
GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PROJECTION_MASTER'
                      AND COLUMN_NAME = 'DEDUCTION_HIERARCHY_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN

ALTER TABLE PROJECTION_MASTER 
ADD DEDUCTION_HIERARCHY_SID  INT
  END

GO


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PROJECTION_MASTER'
                      AND COLUMN_NAME = 'DED_RELATIONSHIP_BULDER_SID'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
     
ALTER TABLE PROJECTION_MASTER 
ADD DED_RELATIONSHIP_BULDER_SID  INT
  END

GO


--------------PROJECTION_DESCRIPTION DATA TYPE CHANGE FOR GAL-9275----------------------------------
IF EXISTS (SELECT 1
              FROM   INFORMATION_SCHEMA.COLUMNS
              WHERE  TABLE_NAME = 'PROJECTION_MASTER'
                     AND COLUMN_NAME = 'PROJECTION_DESCRIPTION'
                     AND TABLE_SCHEMA = 'DBO'
					 AND DATA_TYPE='VARCHAR'
					 AND CHARACTER_MAXIMUM_LENGTH=100)
  BEGIN
      ALTER TABLE [DBO].PROJECTION_MASTER
        ALTER COLUMN PROJECTION_DESCRIPTION VARCHAR(500)
  END

GO 
-----------------------------------added columns---------------------------------
IF NOT EXISTS (
              SELECT 1
              FROM INFORMATION_SCHEMA.COLUMNS
              WHERE TABLE_NAME = 'PROJECTION_MASTER'
                     AND COLUMN_NAME = 'PROJECTION_CUST_VERSION'
                     AND TABLE_SCHEMA = 'DBO'
              )
BEGIN
       ALTER TABLE PROJECTION_MASTER ADD PROJECTION_CUST_VERSION INT
END
GO
 
IF NOT EXISTS (
              SELECT 1
              FROM INFORMATION_SCHEMA.COLUMNS
              WHERE TABLE_NAME = 'PROJECTION_MASTER'
                     AND COLUMN_NAME = 'PROJECTION_PROD_VERSION'
                     AND TABLE_SCHEMA = 'DBO'
              )
BEGIN
       ALTER TABLE PROJECTION_MASTER ADD PROJECTION_PROD_VERSION INT
END
GO
 
 IF EXISTS (
              SELECT 1
              FROM INFORMATION_SCHEMA.COLUMNS
              WHERE TABLE_NAME = 'PROJECTION_MASTER'
                     AND COLUMN_NAME = 'PROJECTION_PROD_VERSION'
                     AND TABLE_SCHEMA = 'DBO'
                     AND IS_NULLABLE = 'NO'
              )
BEGIN
       
       ALTER TABLE PROJECTION_MASTER
 
       ALTER COLUMN PROJECTION_PROD_VERSION INT  NULL
END
GO

IF EXISTS (
              SELECT 1
              FROM INFORMATION_SCHEMA.COLUMNS
              WHERE TABLE_NAME = 'PROJECTION_MASTER'
                     AND COLUMN_NAME = 'PROJECTION_PROD_VERSION'
                     AND TABLE_SCHEMA = 'DBO'
                     AND IS_NULLABLE = 'YES'
              )
BEGIN
       
              UPDATE PM
              SET PM.PROJECTION_PROD_VERSION = RM.VERSION_NO
              FROM PROJECTION_MASTER PM
              INNER JOIN RELATIONSHIP_BUILDER RM
                     ON PM.PROD_RELATIONSHIP_BUILDER_SID = RM.RELATIONSHIP_BUILDER_SID
       
END
GO
 
IF EXISTS (
              SELECT 1
              FROM INFORMATION_SCHEMA.COLUMNS
              WHERE TABLE_NAME = 'PROJECTION_MASTER'
                     AND COLUMN_NAME = 'PROJECTION_CUST_VERSION'
                     AND TABLE_SCHEMA = 'DBO'
                     AND IS_NULLABLE = 'YES'
              )
BEGIN
 ALTER TABLE PROJECTION_MASTER
 
       ALTER COLUMN PROJECTION_CUST_VERSION INT  NULL
END

IF EXISTS (
              SELECT 1
              FROM INFORMATION_SCHEMA.COLUMNS
              WHERE TABLE_NAME = 'PROJECTION_MASTER'
                     AND COLUMN_NAME = 'PROJECTION_CUST_VERSION'
                     AND TABLE_SCHEMA = 'DBO'
                     AND IS_NULLABLE = 'YES'
              )
BEGIN
      
              UPDATE PM
              SET PM.PROJECTION_CUST_VERSION = RM.VERSION_NO
              FROM PROJECTION_MASTER PM
              INNER JOIN RELATIONSHIP_BUILDER RM
                     ON PM.CUST_RELATIONSHIP_BUILDER_SID = RM.RELATIONSHIP_BUILDER_SID
      
 
END
GO

-----------------------


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PROJECTION_MASTER'
                      AND COLUMN_NAME = 'PROJECTION_DED_VERSION'
					  AND TABLE_SCHEMA='DBO')
  BEGIN
      ALTER TABLE PROJECTION_MASTER
        ADD PROJECTION_DED_VERSION  INT  
  END
GO



IF EXISTS (
		SELECT 1
		FROM PROJECTION_MASTER
		WHERE PROJECTION_DED_VERSION IS NULL
		)
BEGIN
	UPDATE PM
	SET PROJECTION_DED_VERSION = RB.VERSION_NO
	FROM PROJECTION_MASTER PM
	INNER JOIN RELATIONSHIP_BUILDER RB
		ON PM.DED_RELATIONSHIP_BULDER_SID = RB.RELATIONSHIP_BUILDER_SID
	WHERE DEDUCTION_RELATION IS NOT NULL
		AND PROJECTION_DED_VERSION IS NULL
END
GO



---------------------------AGN-510 CR------------
IF NOT EXISTS (
              SELECT 1
              FROM INFORMATION_SCHEMA.COLUMNS
              WHERE TABLE_NAME = 'PROJECTION_MASTER'
                     AND COLUMN_NAME = 'FORECAST_ELIGIBLE_DATE'
                     AND TABLE_SCHEMA = 'DBO'
              )
BEGIN
       ALTER TABLE PROJECTION_MASTER ADD FORECAST_ELIGIBLE_DATE DATETIME
END
GO

-------------------------------------------------------------- PROJECTION_DETAILS ------------------------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'PROJECTION_DETAILS'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE PROJECTION_DETAILS
        (
           PROJECTION_DETAILS_SID INT IDENTITY(1, 1),
           PROJECTION_MASTER_SID  INT,
           CCP_DETAILS_SID        INT
        )
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'PROJECTION_DETAILS'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_PROJECTION_DETAILS_PROJECTION_DETAILS_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[PROJECTION_DETAILS]
        ADD CONSTRAINT PK_PROJECTION_DETAILS_PROJECTION_DETAILS_SID PRIMARY KEY(PROJECTION_DETAILS_SID)
  END

GO

--------------------- TRIGGER ------------------------
---Keeping the drop script, if incase we missed to remove this trigger from any environment 

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_PROJECTION_DETAILS_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_PROJECTION_DETAILS_DEL
  END

GO

------------------------------------------------------------ PROJECTION_CUST_HIERARCHY -----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'PROJECTION_CUST_HIERARCHY'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE PROJECTION_CUST_HIERARCHY
        (
           PROJECTION_CUST_HIERARCHY_SID INT IDENTITY(1, 1),
           PROJECTION_MASTER_SID         INT,
           RELATIONSHIP_LEVEL_SID        INT
        )
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'PROJECTION_CUST_HIERARCHY'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_PROJECTION_CUST_HIERARCHY_PROJECTION_CUST_HIERARCHY_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[PROJECTION_CUST_HIERARCHY]
        ADD CONSTRAINT PK_PROJECTION_CUST_HIERARCHY_PROJECTION_CUST_HIERARCHY_SID PRIMARY KEY(PROJECTION_CUST_HIERARCHY_SID)
  END

GO

--------------------------------------------------------------------------- PROJECTION_PROD_HIERARCHY -----------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'PROJECTION_PROD_HIERARCHY'
                      AND Schema_name(SCHEMA_ID) = 'dbo')
  BEGIN
      CREATE TABLE PROJECTION_PROD_HIERARCHY
        (
           PROJECTION_PROD_HIERARCHY_SID INT IDENTITY(1, 1),
           PROJECTION_MASTER_SID         INT,
           RELATIONSHIP_LEVEL_SID        INT
        )
  END

GO

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'PROJECTION_PROD_HIERARCHY'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_PROJECTION_PROD_HIERARCHY_PROJECTION_PROD_HIERARCHY_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[PROJECTION_PROD_HIERARCHY]
        ADD CONSTRAINT PK_PROJECTION_PROD_HIERARCHY_PROJECTION_PROD_HIERARCHY_SID PRIMARY KEY(PROJECTION_PROD_HIERARCHY_SID)
  END

GO
----------------------------------------PROJECTION_FILE_DETAILS---------------------------------
IF NOT EXISTS (SELECT 'X' 
               FROM   INFORMATION_SCHEMA.TABLES 
               WHERE  TABLE_NAME = 'PROJECTION_FILE_DETAILS' 
                      AND TABLE_SCHEMA = 'DBO') 
  BEGIN 
      CREATE TABLE PROJECTION_FILE_DETAILS 
        ( 
           PROJECTION_MASTER_SID INT NOT NULL, 
           FILE_MANAGEMENT_SID   INT NOT NULL, 
           FILE_TYPE             INT NOT NULL 
        ) 
  END 

GO 
-----------cel-1385
IF EXISTS(SELECT 1 
              FROM   SYS.KEY_CONSTRAINTS 
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'PROJECTION_FILE_DETAILS' 
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO' 
                     AND NAME = 'PK_NM_PROJECTION_FILE_VERSION_PROJECTION_MASTER_SID_FILE_MANAGEMENT_SID_FILE_TYPE'
                     AND TYPE = 'PK') 
  BEGIN 
      ALTER TABLE [DBO].[PROJECTION_FILE_DETAILS] 
        DROP CONSTRAINT PK_NM_PROJECTION_FILE_VERSION_PROJECTION_MASTER_SID_FILE_MANAGEMENT_SID_FILE_TYPE  END 

GO 

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PROJECTION_FILE_DETAILS'
                      AND COLUMN_NAME = 'SCREEN_NAME'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PROJECTION_FILE_DETAILS
        ADD  SCREEN_NAME CHAR(1) 
  END

GO
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PROJECTION_FILE_DETAILS'
                      AND COLUMN_NAME = 'FLAG'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PROJECTION_FILE_DETAILS
        ADD  FLAG BIT
  END

GO

---------HIST_PROJECTION_FILE_DETAILS
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'HIST_PROJECTION_FILE_DETAILS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN

CREATE TABLE [dbo].[HIST_PROJECTION_FILE_DETAILS](
	[PROJECTION_MASTER_SID] [int] NOT NULL,
	[FILE_TYPE] [int] NOT NULL,
	[FILE_MANAGEMENT_SID] [int] NULL,
	[SCREEN_NAME] [char](1) NULL,
	[FLAG] [bit] NULL,
	[ACTION_DATE] [datetime] NOT NULL,
	[ACTION_FLAG] [char](1) NOT NULL
) 

  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.HIST_PROJECTION_FILE_DETAILS')
                      AND NAME = 'DF_HIST_PROJECTION_FILE_DETAILS_ACTION_DATE')
  BEGIN
      ALTER TABLE [dbo].HIST_PROJECTION_FILE_DETAILS
        ADD CONSTRAINT DF_HIST_PROJECTION_FILE_DETAILS_ACTION_DATE DEFAULT (Getdate()) FOR ACTION_DATE
  END

GO


--------------------------------------PROJECTION_FILE_DETAILS TRIGGER---------------------------------------
IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_PROJECTION_FILE_DETAILS_INS')
  BEGIN
      DROP TRIGGER dbo.TRG_PROJECTION_FILE_DETAILS_INS
  END

GO

CREATE TRIGGER [dbo].[TRG_PROJECTION_FILE_DETAILS_INS]
ON [dbo].PROJECTION_FILE_DETAILS
AFTER INSERT
AS
  BEGIN
      SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
        INSERT INTO HIST_PROJECTION_FILE_DETAILS
                    (PROJECTION_MASTER_SID,FILE_TYPE,FILE_MANAGEMENT_SID,SCREEN_NAME,FLAG,ACTION_FLAG)
        SELECT PROJECTION_MASTER_SID,FILE_TYPE,FILE_MANAGEMENT_SID,SCREEN_NAME,FLAG,'A'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_PROJECTION_FILE_DETAILS_UPD')
  BEGIN
      DROP TRIGGER dbo.TRG_PROJECTION_FILE_DETAILS_UPD
  END
GO

CREATE TRIGGER [dbo].[TRG_PROJECTION_FILE_DETAILS_UPD]
ON [dbo].[PROJECTION_FILE_DETAILS]
AFTER UPDATE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   INSERTED)
         AND EXISTS(SELECT *
                    FROM   DELETED)
        INSERT INTO HIST_PROJECTION_FILE_DETAILS
                    (PROJECTION_MASTER_SID,FILE_TYPE,FILE_MANAGEMENT_SID,SCREEN_NAME,FLAG,ACTION_FLAG)
        SELECT PROJECTION_MASTER_SID,FILE_TYPE,FILE_MANAGEMENT_SID,SCREEN_NAME,FLAG,'C'
        FROM   INSERTED
  END

GO

IF EXISTS (SELECT 'X'
           FROM   SYS.TRIGGERS
           WHERE  [Name] = N'TRG_PROJECTION_FILE_DETAILS_DEL')
  BEGIN
      DROP TRIGGER dbo.TRG_PROJECTION_FILE_DETAILS_DEL
  END

GO

CREATE TRIGGER [dbo].[TRG_PROJECTION_FILE_DETAILS_DEL]
ON [dbo].[PROJECTION_FILE_DETAILS]
AFTER DELETE
AS
  BEGIN
  SET NOCOUNT ON
      IF EXISTS(SELECT *
                FROM   DELETED)
        INSERT INTO HIST_PROJECTION_FILE_DETAILS
                    (PROJECTION_MASTER_SID,FILE_TYPE,FILE_MANAGEMENT_SID,SCREEN_NAME,FLAG,ACTION_FLAG)
        SELECT PROJECTION_MASTER_SID,FILE_TYPE,FILE_MANAGEMENT_SID,SCREEN_NAME,FLAG,'D'
        FROM   DELETED
  END

GO