----------------------------------------------------------- NM_PROJECTION_SELECTION ---------------------------------------------
IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'NM_PROJECTION_SELECTION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE [DBO].[NM_PROJECTION_SELECTION]
        (
           NM_PROJECTION_SELECTION_SID INT IDENTITY(1, 1) NOT NULL,
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
              WHERE  Object_name(PARENT_OBJECT_ID) = 'NM_PROJECTION_SELECTION'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_NM_PROJECTION_SELECTION_NM_PROJECTION_SELECTION_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE [dbo].[NM_PROJECTION_SELECTION]
        ADD CONSTRAINT PK_NM_PROJECTION_SELECTION_NM_PROJECTION_SELECTION_SID PRIMARY KEY(NM_PROJECTION_SELECTION_SID)
  END
GO 

--------------------------------
INSERT INTO NM_PROJECTION_SELECTION (
       PROJECTION_MASTER_SID
       ,SCREEN_NAME
       ,FIELD_NAME
       ,FIELD_VALUES
       )
SELECT DISTINCT pd.PROJECTION_MASTER_SID
       , 'Discount Projection'
       , 'DeductionLevelValue' FIELD_NAME
       ,STUFF((
                                  SELECT DISTINCT ', ' + CONVERT(VARCHAR(max), RS_CATEGORY)
                                  FROM RS_CONTRACT RC
                                  WHERE RC.RS_CONTRACT_SID = NDP.RS_CONTRACT_SID
                                  FOR XML PATH('')
                                         ,TYPE
                                  ).value('.', 'NVARCHAR(MAX)'), 1, 2, '')
              FIELD_VALUES
FROM PROJECTION_DETAILS PD
JOIN NM_DISCOUNT_PROJ_MASTER NDP ON NDP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
WHERE not EXISTS(SELECT 1 FROM  NM_PROJECTION_SELECTION NPS
              WHERE  NPS.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                     AND NPS.SCREEN_NAME = 'DISCOUNT PROJECTION'
                     AND NPS.FIELD_NAME = 'DEDUCTIONLEVELVALUE')
              

INSERT INTO NM_PROJECTION_SELECTION (
       PROJECTION_MASTER_SID
       ,SCREEN_NAME
       ,FIELD_NAME
       ,FIELD_VALUES
       )
SELECT DISTINCT pd.PROJECTION_MASTER_SID
       , 'Discount Projection'
       , 'DeductionLevel' FIELD_NAME
       ,1 as FIELD_VALUES
FROM PROJECTION_DETAILS PD
JOIN NM_DISCOUNT_PROJ_MASTER NDP ON NDP.PROJECTION_DETAILS_SID = PD.PROJECTION_DETAILS_SID
WHERE not EXISTS(SELECT 1 FROM  NM_PROJECTION_SELECTION NPS
              WHERE  NPS.PROJECTION_MASTER_SID = PD.PROJECTION_MASTER_SID
                     AND NPS.SCREEN_NAME = 'DISCOUNT PROJECTION'
                     AND NPS.FIELD_NAME = 'DeductionLevel' )
----------------------------------------------------------
------------------------------------------------------------- PERIOD ------------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'PERIOD'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE [dbo].[PERIOD]
        (
           PERIOD_SID    INT IDENTITY(1, 1) NOT NULL,
           [YEAR]        INT NOT NULL,
           [QUARTER]     INT NOT NULL,
           [MONTH]       INT NOT NULL,
           [SEMI_ANNUAL] INT NOT NULL,
           [PERIOD_DATE]   DATETIME NOT NULL
        )
  END
GO

------------------ PRIMARY KEY -----------------------------------------
IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_PERIOD_PERIOD_SID'
                     AND TABLE_NAME = 'PERIOD')
BEGIN
  ALTER TABLE PERIOD
    ADD CONSTRAINT PK_PERIOD_PERIOD_SID PRIMARY KEY(PERIOD_SID)
END
GO 

----------------------------------------------------- FORECASTING FORMULA ----------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'FORECASTING_FORMULA'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
     CREATE TABLE [DBO].[FORECASTING_FORMULA]
       (
          [FORECASTING_FORMULA_SID] [INT] IDENTITY(1, 1) NOT NULL,
          FORMULA_NO                VARCHAR(50) NULL,
          [FORMULA_NAME]            [VARCHAR](50) NOT NULL,
          [FORMULA]                 [VARCHAR](500) NOT NULL,
          FORMULA_TYPE              INT NULL,
          IS_ACTIVE                 BIT NULL,
          [CREATED_DATE]            [DATETIME] NOT NULL,
          MODIFIED_DATE             DATETIME
       ) 
     
      
  END

GO 

---------------Additional Columns for FORECASTING_FORMULA

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECASTING_FORMULA'
                      AND COLUMN_NAME = 'FORMULA_NO')
  BEGIN
      ALTER TABLE [DBO].[FORECASTING_FORMULA]
        ADD FORMULA_NO VARCHAR(50)
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECASTING_FORMULA'
                      AND COLUMN_NAME = 'FORMULA_TYPE')
  BEGIN
      ALTER TABLE [DBO].[FORECASTING_FORMULA]
        ADD  FORMULA_TYPE INT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECASTING_FORMULA'
                      AND COLUMN_NAME = 'IS_ACTIVE')
  BEGIN
      ALTER TABLE [DBO].[FORECASTING_FORMULA]
       ADD IS_ACTIVE BIT
  END

GO

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'FORECASTING_FORMULA'
                      AND COLUMN_NAME = 'MODIFIED_DATE')
  BEGIN
      ALTER TABLE [DBO].[FORECASTING_FORMULA]
       ADD MODIFIED_DATE DATETIME
  END

GO
--------------------------------- PRIMARY CONSTRAINT ----------------------------------
IF NOT EXISTS (SELECT 'X'
			   FROM   SYS.INDEXES
			   WHERE  OBJECT_NAME(OBJECT_ID) = N'FORECASTING_FORMULA'
				  AND OBJECT_SCHEMA_NAME(OBJECT_ID) = N'DBO'
				  AND [NAME] = N'PK_FORECASTING_FORMULA_FORECASTING_FORMULA_SID')
	BEGIN
		ALTER TABLE [DBO].[FORECASTING_FORMULA]
			ADD CONSTRAINT [PK_FORECASTING_FORMULA_FORECASTING_FORMULA_SID] PRIMARY KEY CLUSTERED ( [FORECASTING_FORMULA_SID] ASC )
	END
GO

------------------------------- UNIQUE CONSTRAINT -------------------------------------
IF NOT EXISTS (SELECT 'X'
			   FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
			   WHERE  CONSTRAINT_TYPE = 'UNIQUE'
				  AND CONSTRAINT_NAME = 'UQ_FORECASTING_FORMULA_FORMULA_NAME'
				  AND TABLE_NAME = 'FORECASTING_FORMULA')
	BEGIN
		ALTER TABLE [DBO].[FORECASTING_FORMULA]
			ADD CONSTRAINT [UQ_FORECASTING_FORMULA_FORMULA_NAME] UNIQUE ([FORMULA_NAME] ASC)
	END
GO

------------------------------ DEFAULT CONSTRAINT ----------------------------
IF NOT EXISTS (SELECT 'X'
			   FROM   SYS.DEFAULT_CONSTRAINTS
			   WHERE  PARENT_OBJECT_ID = OBJECT_ID('DBO.FORECASTING_FORMULA')
				  AND NAME = 'DF_FORECASTING_FORMULA_CREATED_DATE')
	BEGIN
		ALTER TABLE [DBO].[FORECASTING_FORMULA]
			ADD CONSTRAINT [DF_FORECASTING_FORMULA_CREATED_DATE] DEFAULT (GETDATE()) FOR CREATED_DATE
	END
GO 

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.FORECASTING_FORMULA')
                      AND NAME = 'DF_FORECASTING_FORMULA_MODIFIED_DATE')
  BEGIN
      ALTER TABLE [dbo].[FORECASTING_FORMULA]
        ADD CONSTRAINT [DF_FORECASTING_FORMULA_MODIFIED_DATE] DEFAULT (Getdate()) FOR MODIFIED_DATE
  END

GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.FORECASTING_FORMULA')
                      AND NAME = 'DF_FORECASTING_FORMULA_IS_ACTIVE')
  BEGIN
      ALTER TABLE [dbo].[FORECASTING_FORMULA]
        ADD CONSTRAINT [DF_FORECASTING_FORMULA_IS_ACTIVE] DEFAULT (1) FOR IS_ACTIVE
  END

GO
------------------------------------------------------------------------- CUSTOM_VIEW_MASTER ------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CUSTOM_VIEW_MASTER'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE CUSTOM_VIEW_MASTER
        (
           CUSTOM_VIEW_MASTER_SID INT IDENTITY(1, 1),
           PROJECTION_MASTER_SID  INT,
           VIEW_NAME              VARCHAR(50),
           CREATED_BY             INT,
           CREATED_DATE           DATETIME,
           MODIFIED_BY            INT,
           MODIFIED_DATE          DATETIME
        )
  END
GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_name(object_id) = N'CUSTOM_VIEW_MASTER'
                      AND Object_schema_name(object_id) = N'dbo'
                      AND [Name] = N'PK_CUSTOM_VIEW_MASTER_CUSTOM_VIEW_MASTER_SID')
  BEGIN
      ALTER TABLE CUSTOM_VIEW_MASTER
        ADD CONSTRAINT PK_CUSTOM_VIEW_MASTER_CUSTOM_VIEW_MASTER_SID PRIMARY KEY (CUSTOM_VIEW_MASTER_SID)
  END 
GO

IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  TABLE_NAME = N'CUSTOM_VIEW_MASTER'
                  AND TABLE_SCHEMA = N'dbo'
                  AND COLUMN_NAME = 'VIEW_NAME'
                  AND DATA_TYPE = N'VARCHAR'
                  AND CHARACTER_MAXIMUM_LENGTH = '50')
  BEGIN
      ALTER TABLE [dbo].[CUSTOM_VIEW_MASTER]
        ALTER COLUMN VIEW_NAME VARCHAR(200) NULL
  END

GO 

------------------------------------------------------------ CUSTOM_VIEW_DETAILS ------------------------------------------------------- 

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CUSTOM_VIEW_DETAILS'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN
      CREATE TABLE CUSTOM_VIEW_DETAILS
        (
           CUSTOM_VIEW_DETAILS_SID INT IDENTITY(1, 1),
           CUSTOM_VIEW_MASTER_SID  INT,
           HIERARCHY_ID            INT,
           HIERARCHY_INDICATOR     CHAR(1),
           LEVEL_NO                INT
        )
  END
GO

IF NOT EXISTS (SELECT 'X'
               FROM   SYS.INDEXES
               WHERE  Object_name(object_id) = N'CUSTOM_VIEW_DETAILS'
                      AND Object_schema_name(object_id) = N'dbo'
                      AND [Name] = N'PK_CUSTOM_VIEW_DETAILS_CUSTOM_VIEW_DETAILS_SID')
  BEGIN
      ALTER TABLE CUSTOM_VIEW_DETAILS
        ADD CONSTRAINT PK_CUSTOM_VIEW_DETAILS_CUSTOM_VIEW_DETAILS_SID PRIMARY KEY (CUSTOM_VIEW_DETAILS_SID)
  END
GO

---------------------------------------------------------- FORECASTING_VIEW_MASTER -------------------------------------------

IF NOT EXISTS
    	(	SELECT	'X'
            FROM	INFORMATION_SCHEMA.TABLES 
            WHERE	TABLE_NAME = 'FORECASTING_VIEW_MASTER'
            AND		TABLE_SCHEMA = 'dbo')
BEGIN

CREATE TABLE [dbo].[FORECASTING_VIEW_MASTER]
    (
        [VIEW_ID] NUMERIC(38, 0) IDENTITY(1,1) NOT NULL , 
        [VIEW_NAME] VARCHAR(100) NULL , 
        [VIEW_TYPE] VARCHAR(50) NULL , 
        [CREATED_BY] VARCHAR(50) NULL , 
        [CREATED_DATE] DATETIME NULL , 
        [MODIFIED_BY] VARCHAR(50) NULL , 
        [MODIFIED_DATE] DATETIME NULL , 
        [PROJECTION_ID] NUMERIC(38, 0) NULL
    )
END
GO

IF NOT EXISTS
    (	SELECT	'X'
        FROM	SYS.INDEXES
        WHERE	OBJECT_NAME(object_id) = N'FORECASTING_VIEW_MASTER'
        AND		OBJECT_SCHEMA_NAME(object_id) = N'dbo'
        AND		[Name] = N'PK_FORECASTING_VIEW_MASTER_VIEW_ID' )
BEGIN
    ALTER TABLE [dbo].[FORECASTING_VIEW_MASTER]
        ADD  CONSTRAINT [PK_FORECASTING_VIEW_MASTER_VIEW_ID] PRIMARY KEY CLUSTERED ( [VIEW_ID] ASC ) 
        WITH (FillFactor = 80) ON [PRIMARY]
END
GO

-------------------------------------------------------------- ADDITIONAL_NOTES --------------------------------------

IF NOT EXISTS
    	(	SELECT	'X'
            FROM	INFORMATION_SCHEMA.TABLES 
            WHERE	TABLE_NAME = 'ADDITIONAL_NOTES'
            AND		TABLE_SCHEMA = 'dbo')
BEGIN

CREATE TABLE [dbo].[ADDITIONAL_NOTES]
    (
        [ADDITIONAL_NOTES_ID] NUMERIC(38, 0) IDENTITY(1,1) NOT NULL , 
        [CREATED_BY] VARCHAR(50) NULL , 
        [CREATED_DATE] DATETIME NULL , 
        [FORECAST_TYPE] VARCHAR(50) NULL , 
        [NOTES] VARCHAR(5000) NULL , 
        [PROJECTION_ID] NUMERIC(38, 0) NULL
    )
END
GO

IF NOT EXISTS
    (	SELECT	'X'
        FROM	SYS.INDEXES
        WHERE	OBJECT_NAME(object_id) = N'ADDITIONAL_NOTES'
        AND		OBJECT_SCHEMA_NAME(object_id) = N'dbo'
        AND		[Name] = N'PK_ADDITIONAL_NOTES_ADDITIONAL_NOTES_ID' )
BEGIN
    ALTER TABLE [dbo].[ADDITIONAL_NOTES]
        ADD  CONSTRAINT [PK_ADDITIONAL_NOTES_ADDITIONAL_NOTES_ID] PRIMARY KEY CLUSTERED ( [ADDITIONAL_NOTES_ID] ASC ) 
        WITH (FillFactor = 80) ON [PRIMARY]
END
GO

IF NOT EXISTS (SELECT 1 
               FROM   INFORMATION_SCHEMA.COLUMNS 
               WHERE  TABLE_NAME = 'ADDITIONAL_NOTES' 
                      AND COLUMN_NAME = 'CURRENCY') 
  BEGIN 
      ALTER TABLE ADDITIONAL_NOTES 
        ADD CURRENCY INT NULL 
  END 

GO 
----------------------------------------------------------- DOC_DETAILS -----------------------------------------------

IF NOT EXISTS
    	(	SELECT	'X'
            FROM	INFORMATION_SCHEMA.TABLES 
            WHERE	TABLE_NAME = 'DOC_DETAILS'
            AND		TABLE_SCHEMA = 'dbo')
BEGIN

CREATE TABLE [dbo].[DOC_DETAILS]
    (
        [DOC_DETAILS_ID] NUMERIC(38, 0) IDENTITY(1,1) NOT NULL , 
        [FILE_NAME] VARCHAR(260) NULL , 
        [FILE_SIZE] VARCHAR(10) NULL , 
        [FILE_TYPE] VARCHAR(10) NULL , 
        [FORECAST_TYPE] VARCHAR(50) NULL , 
        [UPLOADED_BY] VARCHAR(50) NULL , 
        [UPLOADED_DATE] DATETIME NULL , 
        [PROJECTION_ID] NUMERIC(38, 0) NULL
    )
END
GO

IF NOT EXISTS
    (	SELECT	'X'
        FROM	SYS.INDEXES
        WHERE	OBJECT_NAME(object_id) = N'DOC_DETAILS'
        AND		OBJECT_SCHEMA_NAME(object_id) = N'dbo'
        AND		[Name] = N'PK_DOC_DETAILS_DOC_DETAILS_ID' )
BEGIN
    ALTER TABLE [dbo].[DOC_DETAILS]
        ADD  CONSTRAINT [PK_DOC_DETAILS_DOC_DETAILS_ID] PRIMARY KEY CLUSTERED ( [DOC_DETAILS_ID] ASC ) 
        WITH (FillFactor = 80) ON [PRIMARY]
END
GO

--------------------------------- ERRORLOG ----------------------------------------

IF NOT EXISTS
    	(	SELECT	'X'
            FROM	INFORMATION_SCHEMA.TABLES 
            WHERE	TABLE_NAME = 'ErrorLog'
            AND		TABLE_SCHEMA = 'dbo')
BEGIN

CREATE TABLE [dbo].[ErrorLog](
	[ErrorID] [int] IDENTITY(1,1) NOT NULL,
	[ErrorNumber] [int] NULL,
	[ErrorState] [int] NULL,
	[ErrorSeverity] [int] NULL,
	[ErrorLine] [int] NULL,
	[ErrorProc] [varchar](max) NULL,
	[ErrorMsg] [varchar](max) NULL,
	[UserName] [varchar](max) NULL,
	[HostName] [varchar](max) NULL,
	[ErrorDate] [datetime] NULL
)

END

GO


IF NOT EXISTS
    (	SELECT	'X'
        FROM	SYS.INDEXES
        WHERE	OBJECT_NAME(object_id) = N'ErrorLog'
        AND		OBJECT_SCHEMA_NAME(object_id) = N'dbo'
        AND		[Name] = N'PK_ErrorLog_ErrorID' )
BEGIN
    ALTER TABLE [dbo].[ErrorLog]
        ADD  CONSTRAINT [PK_ErrorLog_ErrorID] PRIMARY KEY CLUSTERED ( [ErrorID] ) 
        WITH (FillFactor = 80) ON [PRIMARY]
END


GO


IF NOT EXISTS (SELECT 'X'
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  parent_object_id = Object_id('dbo.ErrorLog')
                      AND NAME = 'DF_ErrorLog_ErrorDate')
  BEGIN
      ALTER TABLE [dbo].[ErrorLog]
        ADD CONSTRAINT [DF_ErrorLog_ErrorDate] DEFAULT (getdate()) FOR [ErrorDate]
  END

GO


----------------------------------INSERT SCRIPT FOR PERIOD TABLE STARTS HERE-------------------------------------------------------------

IF NOT EXISTS(SELECT 1
              FROM   PERIOD) 
  BEGIN

  -------------------------------------------------------Date from 1965 to 264(Actuals=50years;Projection=50 years)----------------------
      DECLARE @start_date DATE = '1965-01-01';

      WITH e1(n)
           AS (SELECT 1
               UNION ALL
               SELECT 1
               UNION ALL
               SELECT 1
               UNION ALL
               SELECT 1
               UNION ALL
               SELECT 1
               UNION ALL
               SELECT 1
               UNION ALL
               SELECT 1
               UNION ALL
               SELECT 1
               UNION ALL
               SELECT 1
               UNION ALL
               SELECT 1), -- 10
           e2(n)
           AS (SELECT 1
               FROM   e1
                      CROSS JOIN e1 AS b), -- 10*10
           e3(n)
           AS (SELECT 1
               FROM   e2
                      CROSS JOIN e2 AS b), -- 100*100
           e4(n)
           AS (SELECT 1
               FROM   e3
                      CROSS JOIN (SELECT TOP 5 n
                                  FROM   e1) AS b) -- 5*10000
      INSERT INTO period
                  ([year],
                   [month],
                   [quarter],
                   [SEMI_ANNUAL],
                   [PERIOD_DATE])
      SELECT Year(Dateadd(month, n, @start_date))         [Year],
             Month(Dateadd(month, n, @start_date))        [Month],
             Datepart(QQ, Dateadd(month, n, @start_date)) [Quarter],
             CASE
               WHEN ( Datepart(QQ, Dateadd(month, n, @start_date)) ) IN ( 1, 2 ) THEN 1
               ELSE 2
             END                                          AS [SEMI_ANNUAL],
             Dateadd(month, n, @start_date)               [Date]
      FROM   (SELECT n = Row_number()
                           OVER (
                             ORDER BY n) - 1
              FROM   e4) a
      WHERE  n <= 1200 - 1
      ORDER  BY n;
  END


  GO
  ----------------------------------INSERT SCRIPT FOR PERIOD TABLE ENDS HERE-------------------------------------------------------------


  ----------------------------------FORECASTING_FORMULA_INSERT STARTS HERE---------------------------------------------------------------

  IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PPA Net WAC' AND FORMULA = 'Pbasis WAC - Discount per units') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('PPA Net WAC','Pbasis WAC - Discount per units')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PPA Net MAP' AND FORMULA = 'MAP - Discount per units*(MAP/Pbasis WAC)') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('PPA Net MAP','MAP - Discount per units*(MAP/Pbasis WAC)')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PPA' AND FORMULA = 'CASE WHEN NET_WAC > NET_MAP THEN NET_WAC - NET_MAP ELSE 0 END') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('PPA','CASE WHEN NET_WAC > NET_MAP THEN NET_WAC - NET_MAP ELSE 0 END')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PPA RATE' AND FORMULA = '100*PPA*SALES_UNITS/SALES_AMOUNT') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('PPA RATE','100*PPA*SALES_UNITS/SALES_AMOUNT')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PPA SALES' AND FORMULA = 'PPA') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('PPA SALES','PPA')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PPA DISCOUNT_DOLLAR' AND FORMULA = 'PPA*SALES_UNITS') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('PPA DISCOUNT_DOLLAR','PPA*SALES_UNITS')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PPA DISCOUNT_UNITS' AND FORMULA = 'SALES_UNITS') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('PPA DISCOUNT_UNITS','SALES_UNITS')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='% of Business' AND FORMULA = '(LAST_ACTUALS/QUARTERLY_GTS)*100') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('% of Business','(LAST_ACTUALS/QUARTERLY_GTS)*100')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='HISTORICAL % OF BUSINESS' AND FORMULA = 'MONTHLY_ACTUAL/BASELINE_PERIODS_ACTUAL') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('HISTORICAL % OF BUSINESS','MONTHLY_ACTUAL/BASELINE_PERIODS_ACTUAL')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='FORECAST % OF BUSINESS' AND FORMULA = 'MONTHLY_PROJECTION/BASELINE_PERIODS_PROJECTION') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('FORECAST % OF BUSINESS','MONTHLY_PROJECTION/BASELINE_PERIODS_PROJECTION')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='Single_Average_Methodology' AND FORMULA = 'Selected_Period_Actuals/Number_Of_Selected_Period') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('Single_Average_Methodology','Selected_Period_Actuals/Number_Of_Selected_Period')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PPA MAP' AND FORMULA = 'WAC*(1 + (Effective Price CAP/100))') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('PPA MAP','WAC*(1 + (Effective Price CAP/100))')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PPA-Actuals_Map' AND FORMULA = 'CONTRACT_PRICE * POWER((1+(ACTUAL_PRICE/100)),Quarter)') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('PPA-Actuals_Map','CONTRACT_PRICE * POWER((1+(ACTUAL_PRICE/100)),Quarter)')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PPA-Map_Projection' AND FORMULA = 'CONTRACT_PRICE * POWER((1+(PRICE_CAP/100)),Year)') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('PPA-Map_Projection','CONTRACT_PRICE * POWER((1+(PRICE_CAP/100)),Year)')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='Sales_Projection' AND FORMULA = 'BASELINE*(1+ACCOUNT_GROWTH/100)*(1+PRODUCT_GROWTH/100)') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('Sales_Projection','BASELINE*(1+ACCOUNT_GROWTH/100)*(1+PRODUCT_GROWTH/100)')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='Sales_based_sales' AND FORMULA = 'PROJECTED_SALES * (1 + WAC_INCREASE)') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('Sales_based_sales','PROJECTED_SALES * (1 + WAC_INCREASE)')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='Sales_based_units' AND FORMULA = 'Projected_Sales/WAC_price') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('Sales_based_units','Projected_Sales/WAC_price')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='Units_based_Sales' AND FORMULA = 'Projected_Units*WAC_Price') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('Units_based_Sales','Projected_Units*WAC_Price')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='Discount without netting' AND FORMULA = '(SALES_AMOUNT*DISCOUNT_RATE)/100') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('Discount without netting','(SALES_AMOUNT*DISCOUNT_RATE)/100')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='Discount with netting' AND FORMULA = '((SALES_AMOUNT-OFF_INVOICE)*DISCOUNT_RATE)/100') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('Discount with netting','((SALES_AMOUNT-OFF_INVOICE)*DISCOUNT_RATE)/100')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='WAC_Increase' AND FORMULA = '(Month_WAC - Quarter_WAC)/Quarter_WAC') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('WAC_Increase','(Month_WAC - Quarter_WAC)/Quarter_WAC')
GO
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='GTS % of Business' AND FORMULA = '(Monthly_GTS/Quarterly_GTS)*100') 
INSERT INTO FORECASTING_FORMULA(FORMULA_NAME,FORMULA) VALUES('GTS % of Business','(Monthly_GTS/Quarterly_GTS)*100')
GO


IF NOT EXISTS (
		SELECT 1
		FROM [FORECASTING_FORMULA]
		WHERE [FORMULA_NO] IS NULL
			AND [FORMULA_NAME] = 'WAC_Incr'
			AND [FORMULA] = '(current_WAC - previous_WAC)/nullif(previous_WAC,0)'
		)
BEGIN
	INSERT INTO [FORECASTING_FORMULA] (
		[FORMULA_NO]
		,[FORMULA_NAME]
		,[FORMULA]
		,[IS_ACTIVE]
		)
	VALUES (
		NULL
		,'WAC_Incr'
		,'(current_WAC - previous_WAC)/nullif(previous_WAC,0)'
		,1
		)
END
GO

IF NOT EXISTS (
		SELECT 1
		FROM [FORECASTING_FORMULA]
		WHERE [FORMULA_NO] IS NULL
			AND [FORMULA_NAME] = 'SALES_FACTOR'
			AND [FORMULA] = ' ( 1 + ACCOUNT_GROWTH / 100.0 ) * ( 1 + PRODUCT_GROWTH / 100.0 ) * ( 1 + WAC_INCREASE )'
		)
BEGIN
	INSERT INTO [FORECASTING_FORMULA] (
		[FORMULA_NO]
		,[FORMULA_NAME]
		,[FORMULA]
		,[IS_ACTIVE]
		)
	VALUES (
		NULL
		,'SALES_FACTOR'
		,' ( 1 + ACCOUNT_GROWTH / 100.0 ) * ( 1 + PRODUCT_GROWTH / 100.0 ) * ( 1 + WAC_INCREASE )'
		,1
		)
END
GO

IF NOT EXISTS (
		SELECT 1
		FROM [FORECASTING_FORMULA]
		WHERE [FORMULA_NO] IS NULL
			AND [FORMULA_NAME] = 'UNITS_FACTOR'
			AND [FORMULA] = ' ( 1 + ACCOUNT_GROWTH / 100.0 ) * ( 1 + PRODUCT_GROWTH / 100.0 )'
		)
BEGIN
	INSERT INTO [FORECASTING_FORMULA] (
		[FORMULA_NO]
		,[FORMULA_NAME]
		,[FORMULA]
		,[IS_ACTIVE]
		)
	VALUES (
		NULL
		,'UNITS_FACTOR'
		,' ( 1 + ACCOUNT_GROWTH / 100.0 ) * ( 1 + PRODUCT_GROWTH / 100.0 )'
		,1
		)
END
GO

IF NOT EXISTS (
		SELECT 1
		FROM [FORECASTING_FORMULA]
		WHERE [FORMULA_NO] IS NULL
			AND [FORMULA_NAME] = 'SALES_UNITS_PROJECTION'
			AND [FORMULA] = 'BASELINE * PROJECTED_SALES_UNITS'
		)
BEGIN
	INSERT INTO [FORECASTING_FORMULA] (
		[FORMULA_NO]
		,[FORMULA_NAME]
		,[FORMULA]
		,[IS_ACTIVE]
		)
	VALUES (
		NULL
		,'SALES_UNITS_PROJECTION'
		,'BASELINE * PROJECTED_SALES_UNITS'
		,1
		)
END

GO

IF NOT EXISTS (
		SELECT 1
		FROM [FORECASTING_FORMULA]
		WHERE [FORMULA_NO] IS NULL
			AND [FORMULA_NAME] = 'MONTHLY_PROJECTED_SALES'
			AND [FORMULA] = 'PROJECTED_SALES * MONTHLY_PER_BUSINESS'
		)
BEGIN
	INSERT INTO [FORECASTING_FORMULA] (
		[FORMULA_NO]
		,[FORMULA_NAME]
		,[FORMULA]
		,[IS_ACTIVE]
		)
	VALUES (
		NULL
		,'MONTHLY_PROJECTED_SALES'
		,'PROJECTED_SALES * MONTHLY_PER_BUSINESS'
		,1
		)
END
GO


GO

IF NOT EXISTS (
		SELECT 1
		FROM [FORECASTING_FORMULA]
		WHERE [FORMULA_NO] IS NULL
			AND [FORMULA_NAME] = 'MONTHLY_PROJECTED_UNITS'
			AND [FORMULA] = 'PROJECTED_UNITS * MONTHLY_PER_BUSINESS'
		)
BEGIN
	INSERT INTO [FORECASTING_FORMULA] (
		[FORMULA_NO]
		,[FORMULA_NAME]
		,[FORMULA]
		,[IS_ACTIVE]
		)
	VALUES (
		NULL
		,'MONTHLY_PROJECTED_UNITS'
		,'PROJECTED_UNITS * MONTHLY_PER_BUSINESS'
		,1
		)
END
GO



--------------------------------- Supplemental Formula starts here --------------------------------------------------------------------

WHILE 1 = 1 
BEGIN 

DECLARE @FM_TYPE INT = (SELECT HELPER_TABLE_SID FROM HELPER_TABLE WHERE LIST_NAME = 'FORMULA_TYPE' and DESCRIPTION = 'Supplemental')

IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='Chargeback') 

      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -1',
                   'Chargeback',
                   'WAC-CARS/MA Contract Unit Price 1',
                   @FM_TYPE )

IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='WACPEM') 
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -2',
                   'WACPEM',
                   '(((WAC * CARS/MA Contract Rebate % 1) - URA) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				 
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='AMPP')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -3',
                   'AMPP',
                   '((AMP * CARS/MA Contract Rebate % 1) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='AMPPM')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -5',
                   'AMPPM',
                   '(((AMP * CARS/MA Contract Rebate % 1) -  URA) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				 
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PCTAMPMC')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -6',
                   'PCTAMPMC',
                   '(CASE WHEN (URA >= (CARS/MA Contract Rebate % 1 * AMP)) THEN
0
ELSE
(((CARS/MA Contract Rebate % 1 * AMP) - URA) * (HCFA Units per Package * Forecasted Package Units))
END)',
                   @FM_TYPE )
				 
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='WACPM')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -8',
                   'WACPM',
                   '(((WAC * CARS/MA Contract Rebate % 1) - URA) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				 
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='AWPPM')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -9',
                   'AWPPM',
                   '(((AWP  * CARS/MA Contract Rebate % 1) - URA ) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				  
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='AWPP')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -12',
                   'AWPP',
                   '((AWP * CARS/MA Contract Rebate % 1) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				  
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='EQWACP')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -13',
                   'EQWACP',
                   '((WAC * CARS/MA Contract Rebate % 1) * (HCFA Units per Package *  Forecasted Package Units))',
                   @FM_TYPE )
				  
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='WACFCM')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Mandated Supplemental$ - 15',
                   'WACFCM',
                   '((WAC  - URA - CARS/MA Contract Unit Price 1) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				   
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='EQWACFCM')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -16',
                   'EQWACFCM',
                   '((WAC  - URA - CARS/MA Contract Unit Price 1) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				  
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='AWPFCM')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -17',
                   'AWPFCM',
                   '((AWP  - URA - CARS/MA Contract Unit Price 1) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				  
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='EQAWPFCM')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -18',
                   'EQAWPFCM',
                   '((AWP  - URA - CARS/MA Contract Unit Price 1) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				  
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='AWP-PFCM')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -19',
                   'AWP-PFCM',
                   '(((AWP * CARS/MA Contract Rebate % 1)  - URA - CARS/MA Contract Unit Price 1) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				  
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='AMPFCM')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -20',
                   'AMPFCM',
                   '((AMP  - URA - CARS/MA Contract Unit Price 1) * (HCFA Units per Package * Forecasted Package Units))',
                   @FM_TYPE )
				  
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='FC')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -27',
                   'FC',
                   '(CARS/MA Contract Unit Price 1 * HCFA Units per Package * Forecasted Package Units)',
                   @FM_TYPE )
				  
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='PCTAMPMI')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -29',
                   'PCTAMPMI',
                   '(CASE WHEN (((CARS/MA Contract Rebate % 1 * AMP) + URA) >= (CARS/MA Contract Rebate % 2 * AMP)) THEN
((CARS/MA Contract Rebate % 1  * AMP) * (HCFA Units per Package * Forecasted Package Units))
ELSE
(((CARS/MA Contract Rebate % 2 * AMP) - URA) * (HCFA Units per Package * Forecasted Package Units))
END)',
                   @FM_TYPE )
				  
IF NOT EXISTS(SELECT 1 FROM FORECASTING_FORMULA WHERE FORMULA_NAME ='WAC-PFCM')
      INSERT INTO dbo.FORECASTING_FORMULA
                  (FORMULA_NO,
                   FORMULA_NAME,
                   FORMULA,
                   FORMULA_TYPE)
      VALUES      ('Forecast Supplemental Rebate $ -30',
                   'WAC-PFCM',
                   '(CASE WHEN (((WAC * CARS/MA Contract Rebate % 1) - URA) - CARS/MA Contract Unit Price 1) > 0 THEN
((((WAC * CARS/MA Contract Rebate %1 ) - URA) - CARS/MA Contract Unit Price 1) * (HCFA Units per Package * Forecasted Package Units))
ELSE
((AMP * .01) * (HCFA Units per Package * Forecasted Package Units))
END)',
                   @FM_TYPE )
				   

 BREAK;
 END

--------------------------------- Supplemental Formula end here --------------------------------------------------------------------
----------------------------------FORECASTING_FORMULA_INSERT ENDS HERE---------------------------------------------------------------

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'PROJECTION_USER_SESSION'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE PROJECTION_USER_SESSION
        (
           PROJECTION_MASTER_SID INT NOT NULL,
           USER_ID               INT NOT NULL,
           SESSION_ID            INT NOT NULL,
           LAST_MODIFIED_DATE    DATETIME NOT NULL
        )
  END

IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'PROJECTION_USER_SESSION'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_PROJECTION_USER_SESSION_PROJECTION_MASTER_SID_USER_ID_SESSION_ID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE PROJECTION_USER_SESSION
        ADD CONSTRAINT PK_PROJECTION_USER_SESSION_PROJECTION_MASTER_SID_USER_ID_SESSION_ID PRIMARY KEY(PROJECTION_MASTER_SID, USER_ID, SESSION_ID)
  END

IF NOT EXISTS(SELECT 1
              FROM   SYS.FOREIGN_KEYS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'PROJECTION_USER_SESSION'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'FK_PROJECTION_USER_SESSION_PROJECTION_MASTER_PROJECTION_MASTER_SID'
                     AND TYPE = 'F')
  BEGIN
      ALTER TABLE PROJECTION_USER_SESSION
        ADD CONSTRAINT FK_PROJECTION_USER_SESSION_PROJECTION_MASTER_PROJECTION_MASTER_SID FOREIGN KEY(PROJECTION_MASTER_SID) REFERENCES PROJECTION_MASTER(PROJECTION_MASTER_SID)
  END

IF NOT EXISTS (SELECT 1
               FROM   SYS.DEFAULT_CONSTRAINTS
               WHERE  Object_name(PARENT_OBJECT_ID) = 'PROJECTION_USER_SESSION'
                      AND Schema_name(SCHEMA_ID) = 'dbo'
                      AND NAME = 'DF_PROJECTION_USER_SESSION_LAST_MODIFIED_DATE')
  BEGIN
      ALTER TABLE PROJECTION_USER_SESSION
        ADD CONSTRAINT DF_PROJECTION_USER_SESSION_LAST_MODIFIED_DATE DEFAULT (Getdate()) FOR LAST_MODIFIED_DATE
  END

IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'PRODUCT_FILE'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE PRODUCT_FILE
        (
           ITEM_MASTER_SID                INT NOT NULL,
           PERIOD_SID                     INT NOT NULL,
           EXFACTORY_ACTUAL_SALES         NUMERIC(22, 6),
           EXFACTORY_ACTUAL_UNITS         NUMERIC(22, 6),
           EXFACTORY_FORECAST_SALES       NUMERIC(22, 6),
           EXFACTORY_FORECAST_UNITS       NUMERIC(22, 6),
           DEMAND_ACTUAL_SALES            NUMERIC(22, 6),
           DEMAND_ACTUAL_UNITS            NUMERIC(22, 6),
           DEMAND_FORECAST_SALES          NUMERIC(22, 6),
           DEMAND_FORECAST_UNITS          NUMERIC(22, 6),
           ADJUSTED_DEMAND_ACTUAL_SALES   NUMERIC(22, 6),
           ADJUSTED_DEMAND_ACTUAL_UNITS   NUMERIC(22, 6),
           ADJUSTED_DEMAND_FORECAST_SALES NUMERIC(22, 6),
           ADJUSTED_DEMAND_FORECAST_UNITS NUMERIC(22, 6),
           INVENTORY_ACTUAL_SALES         NUMERIC(22, 6),
           INVENTORY_ACTUAL_UNITS         NUMERIC(22, 6),
           INVENTORY_FORECAST_SALES       NUMERIC(22, 6),
           INVENTORY_FORECAST_UNITS       NUMERIC(22, 6),
           ITEM_PRICE                     NUMERIC(22, 6)
        )
  END

---------------------PRIMARY KEY CONSTRAINT------------------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.KEY_CONSTRAINTS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'PRODUCT_FILE'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'PK_PRODUCT_FILE_ITEM_MASTER_SID_PERIOD_SID'
                     AND TYPE = 'PK')
  BEGIN
      ALTER TABLE PRODUCT_FILE
        ADD CONSTRAINT PK_PRODUCT_FILE_ITEM_MASTER_SID_PERIOD_SID PRIMARY KEY(ITEM_MASTER_SID, PERIOD_SID)
  END

------------
IF NOT EXISTS(SELECT 1
              FROM   SYS.FOREIGN_KEYS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'PRODUCT_FILE'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'FK_PRODUCT_FILE_ITEM_MASTER_ITEM_MASTER_SID'
                     AND TYPE = 'F')
  BEGIN
      ALTER TABLE PRODUCT_FILE
        ADD CONSTRAINT FK_PRODUCT_FILE_ITEM_MASTER_ITEM_MASTER_SID FOREIGN KEY(ITEM_MASTER_SID) REFERENCES ITEM_MASTER(ITEM_MASTER_SID)
  END

IF NOT EXISTS(SELECT 1
              FROM   SYS.FOREIGN_KEYS
              WHERE  Object_name(PARENT_OBJECT_ID) = 'PRODUCT_FILE'
                     AND Schema_name(SCHEMA_ID) = 'DBO'
                     AND NAME = 'FK_PRODUCT_FILE_PERIOD_PERIOD_SID'
                     AND TYPE = 'F')
  BEGIN
      ALTER TABLE PRODUCT_FILE
        ADD CONSTRAINT FK_PRODUCT_FILE_PERIOD_PERIOD_SID FOREIGN KEY(PERIOD_SID) REFERENCES PERIOD(PERIOD_SID)
  END 

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PRODUCT_FILE'
                      AND COLUMN_NAME = 'EXFACTORY_CUST_ACTUAL_SALES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PRODUCT_FILE ADD EXFACTORY_CUST_ACTUAL_SALES NUMERIC(22, 6) NULL;
  END

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PRODUCT_FILE'
                      AND COLUMN_NAME = 'EXFACTORY_CUST_ACTUAL_UNITS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PRODUCT_FILE ADD EXFACTORY_CUST_ACTUAL_UNITS NUMERIC(22, 6) NULL;
  END

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PRODUCT_FILE'
                      AND COLUMN_NAME = 'EXFACTORY_CUST_FORECAST_SALES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PRODUCT_FILE ADD EXFACTORY_CUST_FORECAST_SALES NUMERIC(22, 6) NULL;
  END

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PRODUCT_FILE'
                      AND COLUMN_NAME = 'EXFACTORY_CUST_FORECAST_UNITS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PRODUCT_FILE ADD EXFACTORY_CUST_FORECAST_UNITS NUMERIC(22, 6) NULL;
  END

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PRODUCT_FILE'
                      AND COLUMN_NAME = 'INVENTORY_CUST_ACTUAL_SALES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PRODUCT_FILE ADD INVENTORY_CUST_ACTUAL_SALES NUMERIC(22, 6) NULL;
  END

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PRODUCT_FILE'
                      AND COLUMN_NAME = 'INVENTORY_CUST_ACTUAL_UNITS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PRODUCT_FILE ADD INVENTORY_CUST_ACTUAL_UNITS NUMERIC(22, 6) NULL;
  END

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PRODUCT_FILE'
                      AND COLUMN_NAME = 'INVENTORY_CUST_FORECAST_SALES'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PRODUCT_FILE ADD INVENTORY_CUST_FORECAST_SALES NUMERIC(22, 6) NULL;
  END

IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'PRODUCT_FILE'
                      AND COLUMN_NAME = 'INVENTORY_CUST_FORECAST_UNITS'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      ALTER TABLE PRODUCT_FILE ADD INVENTORY_CUST_FORECAST_UNITS NUMERIC(22, 6) NULL;
  END 


---------------------------GAL-8841(Missing Script) Overrided with Go changes 
  IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'CCP_HIERARCHY'
                      AND TABLE_SCHEMA = 'DBO')
  BEGIN
      CREATE TABLE CCP_HIERARCHY
        (
           CCP_DETAILS_SID   INT         NULL,
           CUST_HIERARCHY_NO VARCHAR(50) NULL,
           PROD_HIERARCHY_NO VARCHAR(50) NULL
        )
  END 

IF NOT EXISTS(SELECT 1
              FROM   SYS.FOREIGN_KEYS
              WHERE  OBJECT_NAME(PARENT_OBJECT_ID) = 'CCP_HIERARCHY'
                     AND SCHEMA_NAME(SCHEMA_ID) = 'DBO'
                     AND NAME = 'FK_CCP_HIERARCHY_CCP_DETAILS_CCP_DETAILS_SID'
                     AND TYPE = 'F')
  BEGIN
      ALTER TABLE CCP_HIERARCHY
        ADD CONSTRAINT FK_CCP_HIERARCHY_CCP_DETAILS_CCP_DETAILS_SID FOREIGN KEY(CCP_DETAILS_SID) REFERENCES CCP_DETAILS (CCP_DETAILS_SID)
  END 





IF NOT EXISTS (SELECT 1
               FROM   SYS.INDEXES
               WHERE  OBJECT_ID = OBJECT_ID('CCP_HIERARCHY')
                      AND NAME = 'NIX_CCP_HIERARCHY_CUST_HIERARCHY_NO_INCLUDE_CCP_DETAILS_SID')
  CREATE NONCLUSTERED INDEX NIX_CCP_HIERARCHY_CUST_HIERARCHY_NO_INCLUDE_CCP_DETAILS_SID
    ON CCP_HIERARCHY ( CUST_HIERARCHY_NO  )
    INCLUDE ( [CCP_DETAILS_SID])

GO 



IF NOT EXISTS (SELECT 1
               FROM   SYS.INDEXES
               WHERE  OBJECT_ID = OBJECT_ID('CCP_HIERARCHY')
                      AND NAME = 'NIX_CCP_HIERARCHY_PROD_HIERARCHY_NO_INCLUDE_CCP_DETAILS_SID')
  CREATE NONCLUSTERED INDEX NIX_CCP_HIERARCHY_PROD_HIERARCHY_NO_INCLUDE_CCP_DETAILS_SID
    ON CCP_HIERARCHY( CUST_HIERARCHY_NO )
    INCLUDE ( [CCP_DETAILS_SID])

GO

---------------------------------------------- one time insertion 

IF NOT EXISTS (SELECT 1 FROM ACTUALS_DETAILS)
BEGIN

INSERT INTO ACTUALS_DETAILS
(
 [PERIOD_SID]         
 ,[RS_MODEL_SID]      
 ,[CCP_DETAILS_SID]   
 ,[SALES]             
 ,[QUANTITY]          
 ,[DISCOUNT]          
 ,[QUANTITY_INCLUSION]
 ,[ACTUAL_ID]  
)


SELECT PERIOD_SID, 
	   RM.RS_MODEL_SID, 
       CCP.CCP_DETAILS_SID ,
       ISNULL((SALES), 0)               AS SALES, 
       ISNULL((QUANTITY), 0)            AS QUANTITY, 
       ISNULL((DISCOUNT), 0)            AS DISCOUNT, 
       ISNULL((QUANTITY_INCLUSION), 'Y')AS QUANTITY_INCLUSION,
       ACTUAL_ID
      FROM   (SELECT CONTRACT_MASTER_SID, 
                     COMPANY_MASTER_SID, 
                     ITEM_MASTER_SID, 
                     PROVISION_ID, 
                     PERIOD_SID, 
                     YEAR, 
                     MONTH, 
                     QUARTER, 
                     PERIOD_DATE, 
                     SALES, 
                     QUANTITY, 
                     DISCOUNT, 
                     QUANTITY_INCLUSION ,B.ACTUAL_ID
              FROM   PERIOD A 
                     JOIN (SELECT PROVISION_ID, 
                                  ACCRUAL_ACTUAL_START_DATE   START_DATE, 
                                  ACCRUAL_ACTUAL_END_DATE     END_DATE, 
                                  QUANTITY_INCLUSION, 
                                  A1.CONTRACT_MASTER_SID, 
                                  A1.COMPANY_MASTER_SID, 
                                  A1.ITEM_MASTER_SID, 
                                  (SALES_AMOUNT) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                                        + 1 ) SALES, 
                                  (QUANTITY) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                                    + 1 )     QUANTITY, 
                                  (AMOUNT) / ( DATEDIFF(MM, ACCRUAL_ACTUAL_START_DATE, ACCRUAL_ACTUAL_END_DATE)
                                                  + 1 )       DISCOUNT 
												  ,A1.ACTUAL_ID
                           FROM   ACTUALS_MASTER A1) B 
                       ON A.PERIOD_DATE BETWEEN B.START_DATE AND B.END_DATE)A 
             JOIN RS_MODEL RM 
               ON RM.RS_ID = A.PROVISION_ID 
             JOIN CCP_DETAILS CCP 
               ON CCP.COMPANY_MASTER_SID = A.COMPANY_MASTER_SID 
                  AND CCP.ITEM_MASTER_SID = A.ITEM_MASTER_SID 
                  AND CCP.CONTRACT_MASTER_SID = A.CONTRACT_MASTER_SID 
END
GO
