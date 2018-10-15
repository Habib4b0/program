---------------------------------------------FORECASTING_VIEWS-----------------------------

IF NOT EXISTS (SELECT 'X'
			   FROM   SYS.TABLES
			   WHERE  Object_name(OBJECT_ID) = 'FORECASTING_VIEWS'
					  AND Schema_name(SCHEMA_ID) = 'dbo' )
BEGIN
CREATE TABLE FORECASTING_VIEWS
	(
		VIEW_ID             		NUMERIC(38, 0) IDENTITY(1,1) NOT NULL ,
		VIEW_NAME 					VARCHAR(100) NULL , 
        VIEW_TYPE 					VARCHAR(50) NULL , 
        CREATED_BY 					VARCHAR(50) NULL , 
        CREATED_DATE 				DATETIME NULL , 
        MODIFIED_BY 				VARCHAR(50) NULL , 
        MODIFIED_DATE 				DATETIME NULL , 
        VIEW_DATA					NVARCHAR(MAX)
	)
	
END

GO

-----------------------------------------CREATE INDEX FOR FORECASTING_VIEWS--------------------

IF NOT EXISTS
    (	SELECT	'X'
        FROM	SYS.INDEXES
        WHERE	OBJECT_NAME(object_id) = N'FORECASTING_VIEWS'
        AND		OBJECT_SCHEMA_NAME(object_id) = N'dbo'
        AND		[Name] = N'PK_FORECASTING_VIEWS_VIEW_ID' )
BEGIN
    ALTER TABLE [dbo].[FORECASTING_VIEWS]
        ADD  CONSTRAINT [PK_FORECASTING_VIEWS_VIEW_ID] PRIMARY KEY CLUSTERED ( [VIEW_ID] ASC ) 
        WITH (FillFactor = 80) ON [PRIMARY]
END
GO

-----------------------------------------CREATE FLAG COLUMN FOR FORECASTING_VIEWS--------------------
IF NOT  EXISTS 
	(	 SELECT 'X'
         FROM   INFORMATION_SCHEMA.COLUMNS
         WHERE  TABLE_NAME = 'FORECASTING_VIEWS'
         AND TABLE_SCHEMA = 'dbo' 
		 AND COLUMN_NAME = 'FLAG')
BEGIN
	ALTER TABLE dbo.FORECASTING_VIEWS 
	ADD FLAG int
END
GO