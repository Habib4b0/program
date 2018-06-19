---------------------------------------------REPORTING_VIEW_MASTER-----------------------------

IF NOT EXISTS (SELECT 'X'
			   FROM   SYS.TABLES
			   WHERE  Object_name(OBJECT_ID) = 'REPORTING_VIEW_MASTER'
					  AND Schema_name(SCHEMA_ID) = 'dbo' )
BEGIN
CREATE TABLE REPORTING_VIEW_MASTER
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

-----------------------------------------CREATE INDEX FOR REPORTING_VIEW_MASTER--------------------

IF NOT EXISTS
    (	SELECT	'X'
        FROM	SYS.INDEXES
        WHERE	OBJECT_NAME(object_id) = N'REPORTING_VIEW_MASTER'
        AND		OBJECT_SCHEMA_NAME(object_id) = N'dbo'
        AND		[Name] = N'PK_REPORTING_VIEW_MASTER_VIEW_ID' )
BEGIN
    ALTER TABLE [dbo].[REPORTING_VIEW_MASTER]
        ADD  CONSTRAINT [PK_REPORTING_VIEW_MASTER_VIEW_ID] PRIMARY KEY CLUSTERED ( [VIEW_ID] ASC ) 
        WITH (FillFactor = 80) ON [PRIMARY]
END
GO