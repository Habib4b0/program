IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.ROUTINES
           WHERE  ROUTINE_NAME = 'PRC_SNAP_DYNAMIC'
                  AND ROUTINE_SCHEMA = 'DBO')
  BEGIN
      DROP PROCEDURE [DBO].[PRC_SNAP_DYNAMIC]
  END

GO
CREATE PROCEDURE [dbo].[PRC_SNAP_DYNAMIC] (@TABLE varchar(100))
AS
/**********************************************************************************************************
** File Name  	:	PRC_SNAP_DYNAMIC.sql
** Procedure Name	:	PRC_SNAP_DYNAMIC
** Description		:	To dynamically create corresponding Snap tables of all the interface tables snap tables to maintain period wise in staging database.
** Input Parameters	:	@TABLE - Should receive the table name as input.
** Output Parameters:	NA
** Author Name		:	@Sai Praveen
** Creation Date	:	03/06/2017 - MM/DD/YYYY
** Called By		:   Integrated in ETL code so that when corresponding interface runs.
**********************************************************************************************************
** Change History
**********************************************************************************************************
** VER   Date      Ticket No         Author          Description 
** ---   --------  ---------        -------------    -----------------------------
** 1    03/06/2017 GAL-10255        @Sai Praveen     Snap tables to be maintained for period wise in staging database.
*********************************************************************************************************/
BEGIN

  -- VARIABLE DECLARATIONS
  DECLARE @SQL varchar(max)
  DECLARE @TABLE_NAME varchar(100)
  DECLARE @INTERFACE_TABLE_NAME varchar(max)
  DECLARE @VALUES varchar(max)
  DECLARE @VALUES1 varchar(max)

  -- VARIABLES INITIALIZATION
  SET @TABLE_NAME = CONCAT(@TABLE, '_', YEAR(GETDATE()), '_', CASE
    WHEN MONTH(GETDATE()) IN (1, 2, 3, 4, 5, 6) THEN 'SEMI_ANNUAL_1'
    ELSE 'SEMI_ANNUAL_2'
  END)

  IF  (@TABLE = 'SNAP_ADJUSTED_DEMAND_ACTUAL'   
    OR @TABLE = 'SNAP_ADJUSTED_DEMAND_FORECAST'
    OR @TABLE = 'SNAP_CUSTOMER_GTS_FORECAST'
    OR @TABLE = 'SNAP_INVENTORY_WD_ACTUAL_DT'
    OR @TABLE = 'SNAP_INVENTORY_WD_ACTUAL_MAS'
    OR @TABLE = 'SNAP_INVENTORY_WD_PROJ_DT'
    OR @TABLE = 'SNAP_INVENTORY_WD_PROJ_MAS')
	BEGIN
    SET @INTERFACE_TABLE_NAME = STUFF(CONCAT(@TABLE, '_INTF'), 1, 5, '')
    END
	ELSE IF (@TABLE = 'SNAP_CFP')
	SET @INTERFACE_TABLE_NAME = 'COMPANY_FAMILY_PLAN_INTERFACE'
	ELSE IF (@TABLE = 'SNAP_IFP')
	SET @INTERFACE_TABLE_NAME = 'ITEM_FAMILY_PLAN_INTERFACE'
	ELSE IF (@TABLE = 'SNAP_COMPANY_PARENT')
	SET @INTERFACE_TABLE_NAME = 'COMPANY_PARENT_DETAILS_INTERFACE'
    ELSE
    SET @INTERFACE_TABLE_NAME = STUFF(CONCAT(@TABLE, '_INTERFACE'), 1, 5, '')
 

  -- START POINT - EXTRACTING THE COLUMN NAMES IN A COMMA SEPERATED ACCORDING TO THE TABLE_NAME GIVEN AS INPUT.
  IF  (@TABLE = 'SNAP_CPI'
    OR @TABLE = 'SNAP_FORMULA_DETAILS'
    OR @TABLE = 'SNAP_INVENTORY_WD_PROJ_DT'
    OR @TABLE = 'SNAP_INVENTORY_WD_PROJ_MAS'
    OR @TABLE = 'SNAP_ITEM_HIERARCHY_DEFINITION'
    OR @TABLE = 'SNAP_LOT_MASTER'
    OR @TABLE = 'SNAP_MASTER_DATA_ATTRIBUTE'
    OR @TABLE = 'SNAP_RETURN_RESERVE'
    OR @TABLE = 'SNAP_ITEM_HIERARCHY'
    OR @TABLE = 'SNAP_GL_POSTING'
    OR @TABLE = 'SNAP_SALES_MASTER')
  BEGIN
    SET @VALUES = (SELECT
      STUFF((SELECT
        ', ' + COLUMN_NAME
      FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = @TABLE
      AND COLUMN_NAME NOT IN ('INTF_INSERTED_DATE')
      ORDER BY column_name
      FOR xml PATH (''))
      , 1, 2, ''))

    SET @VALUES1 = (SELECT
      STUFF((SELECT
        ', ' + COLUMN_NAME
      FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = @INTERFACE_TABLE_NAME
      ORDER BY column_name
      FOR xml PATH (''))
      , 1, 2, ''))
  END
  ELSE IF (@TABLE = 'SNAP_RETURNS')
  BEGIN
    SET @VALUES = (SELECT
      STUFF((SELECT
        ', ' + COLUMN_NAME
      FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = @TABLE
      AND COLUMN_NAME NOT IN ('INTF_INSERTED_DATE', 'CREATED_BY', 'CREATED_DATE', 'MODIFIED_BY', 'MODIFIED_DATE')
      ORDER BY column_name
      FOR xml PATH (''))
      , 1, 2, ''))

    SET @VALUES1 = (SELECT
      STUFF((SELECT
        ', ' + COLUMN_NAME
      FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = @INTERFACE_TABLE_NAME
      ORDER BY column_name
      FOR xml PATH (''))
      , 1, 2, ''))
  END
  ELSE IF (@TABLE = 'SNAP_FORECAST_SALES')
  BEGIN 
  SET @VALUES = 'ACTUAL_SALES_PERCENTAGE, ACTUAL_SALES_PERCENTAGE_MONTH, ADD_CHG_DEL_INDICATOR, BATCH_ID, BRAND, COUNTRY, CREATED_BY, CREATED_DATE, DOLLARS, FORECAST_DATE, FORECAST_MONTH, FORECAST_NAME, FORECAST_SALES_INTFID, FORECAST_START_DATE, FORECAST_VALUE, FORECAST_VALUE_TYPE, FORECAST_VER, FORECAST_YEAR, FORECASTED_SALES_PERCENT_MONTH, FORECASTED_SALES_PERCENTAGE, MODIFIED_BY, MODIFIED_DATE, NDC, PERCENTAGE_ESTIMATE, PERCENTAGE_ESTIMATE_YEAR, PRICE, PRODUCT, SEGMENT, SOURCE, UNITS'
  SET @VALUES1 = 'ACTUAL_SALES_PERCENTAGE, ACTUAL_SALES_PERCENTAGE_MONTH, ADD_CHG_DEL_INDICATOR, BATCH_ID, BRAND, COUNTRY, CREATED_BY, CREATED_DATE, DOLLARS, FORECAST_DATE, FORECAST_MONTH, FORECAST_NAME, FORECAST_INTERFACE_ID, FORECAST_START_DATE, FORECAST_VALUE, FORECAST_VALUE_TYPE, FORECAST_VER, FORECAST_YEAR, FORECASTED_SALES_PERCENTAGE_MONTH, FORECASTED_SALES_PERCENTAGE, MODIFIED_BY, MODIFIED_DATE, NDC, PERCENTAGE_ESTIMATE, PERCENTAGE_ESTIMATE_YEAR, PRICE, PRODUCT, SEGMENT, SOURCE, UNITS'
  END
  ELSE
  BEGIN
    SET @VALUES = (SELECT
      STUFF((SELECT
        ', ' + COLUMN_NAME
      FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = @TABLE
      AND COLUMN_NAME NOT IN ('INTF_INSERTED_DATE', 'STATUS')
      ORDER BY column_name
      FOR xml PATH (''))
      , 1, 2, ''))

    SET @VALUES1 = (SELECT
      STUFF((SELECT
        ', ' + COLUMN_NAME
      FROM INFORMATION_SCHEMA.COLUMNS
      WHERE TABLE_NAME = @INTERFACE_TABLE_NAME
      ORDER BY column_name
      FOR xml PATH (''))
      , 1, 2, ''))
  END
  -- END POINT - EXTRACTING THE COLUMN NAMES IN A COMMA SEPERATED ACCORDING TO THE TABLE_NAME GIVEN AS INPUT.

  -- START POINT - CREATE THE TABLE WITH NAMING CONVENTION TAKEN FROM VARIABLE @TABLE_NAME WITH COLUMNS FROM THE INPUT TABLE.
  IF NOT EXISTS (SELECT
      'X'
    FROM INFORMATION_SCHEMA.TABLES
    WHERE TABLE_NAME = @TABLE_NAME
    AND TABLE_SCHEMA = 'DBO')
  BEGIN

    SET @SQL = (SELECT
      'create table [' + @TABLE_NAME + '] (' + o.list + ')' + CASE
        WHEN tc.Constraint_Name IS NULL THEN ''
        ELSE 'ALTER TABLE ' + @TABLE_NAME + ' ADD CONSTRAINT ' + tc.Constraint_Name + ' PRIMARY KEY ' + ' (' + LEFT(j.List, LEN(j.List) - 1) + ')'
      END
    FROM sys.objects so
    CROSS APPLY (SELECT
      '  [' + column_name + '] ' +
      data_type + CASE data_type
        WHEN 'sql_variant' THEN ''
        WHEN 'text' THEN ''
        WHEN 'ntext' THEN ''
        WHEN 'xml' THEN ''
        WHEN 'decimal' THEN '(' + CAST(numeric_precision AS varchar(30)) + ', ' + CAST(numeric_scale AS varchar(30)) + ')'
        ELSE COALESCE('(' +
                           CASE
                             WHEN character_maximum_length = -1 THEN 'MAX'
                             ELSE CAST(character_maximum_length AS varchar(30))
                           END + ')', '')
      END + ' ' +
      CASE
        WHEN EXISTS (SELECT
            object_id
          FROM sys.columns
          WHERE OBJECT_NAME(object_id) = @TABLE
          AND name = column_name
          AND COLUMNPROPERTY(object_id, name, 'IsIdentity') = 1) THEN 'IDENTITY(' +
          CAST(IDENT_SEED(@TABLE) AS varchar(30)) + ',' +
          CAST(IDENT_INCR(@TABLE) AS varchar(30)) + ')'
        ELSE ''
      END + ' ' +
      (CASE
        WHEN IS_NULLABLE = 'No' THEN 'NOT '
        ELSE ''
      END) + 'NULL ' +
      CASE
        WHEN information_schema.columns.COLUMN_DEFAULT IS NOT NULL THEN 'DEFAULT ' + information_schema.columns.COLUMN_DEFAULT
        ELSE ''
      END + ', '

    FROM information_schema.columns
    WHERE table_name = @TABLE
    ORDER BY ordinal_position
    FOR xml PATH ('')) o (list)
    LEFT JOIN information_schema.table_constraints tc
      ON tc.Table_name = @TABLE
      AND tc.Constraint_Type = 'PRIMARY KEY'
    CROSS APPLY (SELECT
      '[' + Column_Name + '], '
    FROM information_schema.key_column_usage kcu
    WHERE kcu.Constraint_Name = tc.Constraint_Name
    ORDER BY ORDINAL_POSITION
    FOR xml PATH ('')) j (list)
    WHERE type = 'U'
    AND name NOT IN ('dtproperties')
    AND SO.name = @TABLE)

    -- End point - Create the table with naming convention taken from variable @TABLE_NAME with columns from the input table.

    EXEC (@SQL) -- Executing the above create table script

  END

  --PRINT @VALUES
  --PRINT @VALUES1

  -- Start point - Inserting the date from interface table to the table which is created in above step.
  DECLARE @SQL1 AS varchar(max) = 'SET NOCOUNT ON
 INSERT INTO ' + @TABLE_NAME + ' (' + @VALUES + ')
SELECT ' + @VALUES1 + ' FROM ' + @INTERFACE_TABLE_NAME + ''
  -- End point - Inserting the date from interface table to the table which is created in above step.

  EXEC (@SQL1)  -- Executing the above insert query
END



GO


