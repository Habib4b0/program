IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_MASTER_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE [dbo].[ITEM_MASTER_INTERFACE]
        (
           [ITEM_MASTER_INTERFACE_ID]       NUMERIC(38, 0) NOT NULL,
           [ITEM_ID]                        VARCHAR(38) NOT NULL,
           [ITEM_NO]                        VARCHAR(50) NOT NULL,
           [ITEM_CODE]                      VARCHAR(25) NOT NULL,
           [ITEM_NAME]                      VARCHAR(100) NOT NULL,
           [ITEM_DESC]                      VARCHAR(250) NULL,
           [PACKAGE_SIZE]                   VARCHAR(100) NULL,
           [PACKAGE_SIZE_CODE]              VARCHAR(25) NULL,
           [PACKAGE_SIZE_INTRO_DATE]        DATETIME NULL,
           [UPPS]                           NUMERIC(22, 6) NULL,
           [ITEM_START_DATE]                DATETIME NULL,
           [ITEM_END_DATE]                  DATETIME NULL,
           [ITEM_STATUS]                    VARCHAR(20) NOT NULL,
           [MANUFACTURER_ID]                VARCHAR(38) NULL,
           [MANUFACTURER_NO]                VARCHAR(50) NULL,
           [MANUFACTURER_NAME]              VARCHAR(100) NULL,
           [LABELER_CODE]                   VARCHAR(25) NULL,
           [ORGANIZATION_KEY]               VARCHAR(50) NULL,
           [ACQUISITION_DATE]               DATETIME NULL,
           [AUTHORIZED_GENERIC]             VARCHAR(50) NULL,
           [AUTHORIZED_GENERIC_START_DATE]  DATETIME NULL,
           [AUTHORIZED_GENERIC_END_DATE]    DATETIME NULL,
           [BRAND]                          VARCHAR(30) NULL,
           [FORM]                           VARCHAR(50) NOT NULL,
           [STRENGTH]                       VARCHAR(100) NOT NULL,
           [THERAPEUTIC_CLASS]              VARCHAR(50) NULL,
           [FIRST_SALE_DATE]                DATETIME NULL,
           [ITEM_TYPE_INDICATION]           VARCHAR(50) NULL,
           [ITEM_CLASS]                     VARCHAR(50) NULL,
           [ITEM_TYPE]                      VARCHAR(50) NULL,
           [MARKET_TERMINATION_DATE]        DATETIME NULL,
           [NEW_FORMULATION_INDICATOR]      VARCHAR(50) NULL,
           [NEW_FORMULATION]                VARCHAR(38) NULL,
           [NEW_FORMULATION_START_DATE]     DATETIME NULL,
           [NEW_FORMULATION_END_DATE]       DATETIME NULL,
           [PEDIATRIC_EXCLUSIVE_INDICATOR]  VARCHAR(50) NULL,
           [PEDIATRIC_EXCLUSIVE_START_DATE] DATETIME NULL,
           [PEDIATRIC_EXCLUSIVE_END_DATE]   DATETIME NULL,
           [CLOTTING_FACTOR_INDICATOR]      VARCHAR(50) NULL,
           [CLOTTING_FACTOR_START_DATE]     DATETIME NULL,
           [CLOTTING_FACTOR_END_DATE]       DATETIME NULL,
           [PRIMARY_UOM]                    VARCHAR(20) NULL,
           [SECONDARY_UOM]                  VARCHAR(20) NULL,
           [SHELF_LIFE]                     VARCHAR(30) NULL,
           [SHELF_LIFE_TYPE]                VARCHAR(30) NULL,
           [DUAL_PRICING_INDICATOR]         VARCHAR(30) NULL,
           [ITEM_FAMILY_ID]                 VARCHAR(38) NULL,
           [UDC1]                           VARCHAR(100) NULL,
           [UDC2]                           VARCHAR(100) NULL,
           [UDC3]                           VARCHAR(100) NULL,
           [UDC4]                           VARCHAR(100) NULL,
           [UDC5]                           VARCHAR(100) NULL,
           [UDC6]                           VARCHAR(100) NULL,
           [ACQUIRED_AMP]                   NUMERIC(20, 6) NULL,
           [ACQUIRED_BAMP]                  NUMERIC(20, 6) NULL,
           [OBRA_BAMP]                      NUMERIC(20, 6) NULL,
           [DRA]                            NUMERIC(20, 0) NULL,
           [ADD_CHG_DEL_INDICATOR]          VARCHAR(10) NOT NULL,
           [CREATED_BY]                     VARCHAR(50) NULL,
           [CREATED_DATE]                   DATETIME NULL,
           [MODIFIED_BY]                    VARCHAR(50) NULL,
           [MODIFIED_DATE]                  DATETIME NULL,
           [BATCH_ID]                       VARCHAR(38) NOT NULL,
           [SOURCE]                         VARCHAR(50) NULL,
           [DOSES_PER_UNIT]                 VARCHAR(25) NULL,
           [DISCONTINUATION_DATE]           DATETIME NULL,
           [LAST_LOT_EXPIRATION_DATE]       DATETIME NULL,
           [DIVESTITURE_DATE]               DATETIME NULL,
           [NON_FEDERAL_EXPIRATION_DATE]    DATETIME NULL,
           [NDC9]                           VARCHAR(25) NOT NULL,
           [NDC8]                           VARCHAR(25) NOT NULL,
           [DISPLAY_BRAND]                  VARCHAR(30) NULL,
           [BRAND_ID]                       VARCHAR(25) NOT NULL,
           [ITEM_CATEGORY]                  VARCHAR(10) NULL,
           [BASELINE_AMP]                   NUMERIC(20, 6) NULL,
           [BASE_CPI_PERIOD]                DATETIME NULL,
           [BASE_CPI]                       NUMERIC(20, 6) NULL
        )
  END
GO 


IF NOT EXISTS (SELECT 1
               FROM   INFORMATION_SCHEMA.COLUMNS
               WHERE  TABLE_NAME = 'ITEM_MASTER_INTERFACE'
                      AND COLUMN_NAME = 'NDC8'
                      AND TABLE_SCHEMA = 'DBO'
                      AND DATA_TYPE = 'VARCHAR'
                      AND CHARACTER_MAXIMUM_LENGTH = 100)

BEGIN
  ALTER TABLE ITEM_MASTER_INTERFACE
    ALTER COLUMN NDC8 VARCHAR(100) NOT NULL
END
GO
---------cel-1445,cel-1611
IF EXISTS (
		SELECT 1
		FROM information_schema.columns
		WHERE table_name = 'ITEM_MASTER_INTERFACE'
			AND column_name = 'BASE_CPI_PRECISION'
			AND table_schema = 'DBO'
		)
BEGIN
	ALTER TABLE ITEM_MASTER_INTERFACE DROP COLUMN BASE_CPI_PRECISION 
END
	GO
IF EXISTS (
		SELECT 1
		FROM information_schema.columns
		WHERE table_name = 'ITEM_MASTER_INTERFACE'
			AND column_name = 'BASELINE_AMP_PRECISION'
			AND table_schema = 'DBO'
		)
BEGIN
	ALTER TABLE ITEM_MASTER_INTERFACE DROP COLUMN BASELINE_AMP_PRECISION
END
	GO
---------cel-1445,cel-1611	