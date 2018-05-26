IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CUSTOM_CCP_SALES'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[CUSTOM_CCP_SALES]
        (
           HIERARCHY_NO             VARCHAR(8000) NOT NULL,
           CCP_DETAILS_SID          INT NOT NULL,
           LEVEL_NO                 INT NOT NULL,
           CUST_VIEW_MASTER_SID     INT NOT NULL,
           RELATIONSHIP_LEVEL_VALES INT NOT NULL,
           ROWID                    INT NOT NULL
        )
  END

GO

--IF NOT EXISTS (SELECT 1
--               FROM   SYS.KEY_CONSTRAINTS
--               WHERE  Object_name(PARENT_OBJECT_ID) = 'CUSTOM_CCP_SALES'
--                      AND Schema_name(SCHEMA_ID) = 'DBO'
--                      AND NAME = 'PK_CUSTOM_CCP_SALES_ROWID_CUST_VIEW_MASTER_SID_LEVEL_NO_CCP_DETAILS_SID'								  
--                      AND TYPE = 'PK')
--  BEGIN
--      ALTER TABLE CUSTOM_CCP_SALES
--        ADD CONSTRAINT PK_CUSTOM_CCP_SALES_ROWID_CUST_VIEW_MASTER_SID_LEVEL_NO_CCP_DETAILS_SID PRIMARY KEY (ROWID, CUST_VIEW_MASTER_SID, LEVEL_NO, CCP_DETAILS_SID)
--  END

--GO
IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'CUSTOM_CCP_SALES'
                      AND COLUMN_NAME = 'BUILD_TYPE')
  BEGIN
      ALTER TABLE CUSTOM_CCP_SALES
        ADD RS_CONTRACT_SID INT NOT NULL
  END

GO
--ALTER TABLE CUSTOM_CCP_SALES ADD RS_CONTRACT_SID INT NOT NULL

IF  EXISTS (
		SELECT 1
		FROM SYS.KEY_CONSTRAINTS
		WHERE Object_name(PARENT_OBJECT_ID) = 'CUSTOM_CCP_SALES'
			AND Schema_name(SCHEMA_ID) = 'DBO'
			AND NAME = 'PK_CUSTOM_CCP_SALES_ROWID_CUST_VIEW_MASTER_SID_LEVEL_NO_CCP_DETAILS_SID'
			AND TYPE = 'PK'
		)
BEGIN
	ALTER TABLE CUSTOM_CCP_SALES drop CONSTRAINT PK_CUSTOM_CCP_SALES_ROWID_CUST_VIEW_MASTER_SID_LEVEL_NO_CCP_DETAILS_SID 
END
GO



IF NOT EXISTS (
		SELECT 1
		FROM SYS.KEY_CONSTRAINTS
		WHERE Object_name(PARENT_OBJECT_ID) = 'CUSTOM_CCP_SALES'
			AND Schema_name(SCHEMA_ID) = 'DBO'
			AND NAME = 'PK_CUSTOM_CCP_SALES_ROWID_CUST_VIEW_MASTER_SID_LEVEL_NO_CCP_DETAILS_SID_RS_CONTRACT_SID'
			AND TYPE = 'PK'
		)
BEGIN
	ALTER TABLE CUSTOM_CCP_SALES ADD CONSTRAINT PK_CUSTOM_CCP_SALES_ROWID_CUST_VIEW_MASTER_SID_LEVEL_NO_CCP_DETAILS_SID_RS_CONTRACT_SID PRIMARY KEY (
		CUST_VIEW_MASTER_SID,
		ROWID,		
		LEVEL_NO,
		CCP_DETAILS_SID,
		RS_CONTRACT_SID
		)
END
GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'CUSTOM_CCPD_DISCOUNT'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[CUSTOM_CCPD_DISCOUNT]
        (
           HIERARCHY_NO             VARCHAR(8000) NOT NULL,
           CCP_DETAILS_SID          INT NOT NULL,
           RS_CONTRACT_SID          INT NOT NULL,
           LEVEL_NO                 INT NOT NULL,
           CUST_VIEW_MASTER_SID     INT NOT NULL,
           RELATIONSHIP_LEVEL_VALES INT NOT NULL,
           ROWID                    INT NOT NULL
        )
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   SYS.KEY_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'CUSTOM_CCPD_DISCOUNT'
                      AND Schema_name(SCHEMA_ID) = 'DBO'
                      AND NAME = 'PK_CUSTOM_CCPD_DISCOUNT_ROWID_CUST_VIEW_MASTER_SID_LEVEL_NO_CCP_DETAILS_SID_RS_CONTRACT_SID'
                      AND TYPE = 'PK')
  BEGIN
      ALTER TABLE CUSTOM_CCPD_DISCOUNT
        ADD CONSTRAINT PK_CUSTOM_CCPD_DISCOUNT_ROWID_CUST_VIEW_MASTER_SID_LEVEL_NO_CCP_DETAILS_SID_RS_CONTRACT_SID PRIMARY KEY (ROWID, CUST_VIEW_MASTER_SID, LEVEL_NO, CCP_DETAILS_SID, RS_CONTRACT_SID)
  END

GO

-------------------------------------------------------------- FOREIGN KEY------------------------------------------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.FOREIGN_KEYS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'CUSTOM_CCPD_DISCOUNT'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'FK_CUSTOM_CCPD_DISCOUNT_CUSTOM_VIEW_MASTER_CUST_VIEW_MASTER_SID'
                     AND TYPE = 'F')
  BEGIN
      ALTER TABLE CUSTOM_CCPD_DISCOUNT
        ADD CONSTRAINT FK_CUSTOM_CCPD_DISCOUNT_CUSTOM_VIEW_MASTER_CUST_VIEW_MASTER_SID FOREIGN KEY(CUST_VIEW_MASTER_SID) REFERENCES CUSTOM_VIEW_MASTER(CUSTOM_VIEW_MASTER_SID)
  END

GO 
