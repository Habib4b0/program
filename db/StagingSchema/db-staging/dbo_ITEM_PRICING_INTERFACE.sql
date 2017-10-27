IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_PRICING_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE [dbo].[ITEM_PRICING_INTERFACE]
        (
           [ITEM_PRICING_INTERFACE_ID]   NUMERIC(38, 0) NOT NULL,
           [ITEM_ID]                     VARCHAR(38) NOT NULL,
           [ITEM_NO]                     VARCHAR(50) NOT NULL,
           [ITEM_NAME]                   VARCHAR(100) NULL,
           [ITEM_UOM]                    VARCHAR(20) NOT NULL,
           [PRICING_CODE_QUALIFIER]      VARCHAR(25) NOT NULL,
           [PRICING_CODE_QUALIFIER_NAME] VARCHAR(100) NULL,
           [ITEM_PRICE]                  NUMERIC(20, 6) NOT NULL,
           [PRICING_CODE_STATUS]         VARCHAR(20) NOT NULL,
           [START_DATE]                  DATETIME NOT NULL,
           [END_DATE]                    DATETIME NULL,
           [ENTITY_CODE]                 VARCHAR(30) NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL
        )
  END
GO
-----------cel-1445,cel-1611
IF EXISTS (
		SELECT 1
		FROM information_schema.columns
		WHERE table_name = 'ITEM_PRICING_INTERFACE'
			AND column_name = 'ITEM_PRICE_PRECISION'
			AND table_schema = 'DBO'
		)
BEGIN
	ALTER TABLE ITEM_PRICING_INTERFACE DROP COLUMN ITEM_PRICE_PRECISION
END
	GO
-----------cel-1445,cel-1611	