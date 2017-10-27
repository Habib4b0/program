IF NOT EXISTS (SELECT 'X'
               FROM   INFORMATION_SCHEMA.TABLES
               WHERE  TABLE_NAME = 'ITEM_HIERARCHY_INTERFACE'
                      AND TABLE_SCHEMA = 'dbo')
  BEGIN

      CREATE TABLE [dbo].[ITEM_HIERARCHY_INTERFACE]
        (
           [ITEM_HIERARCHY_INTERFACE_ID] NUMERIC(38, 0) NOT NULL,
           [LEVEL0_TAG]                  VARCHAR(100) NULL,
           [LEVEL0]                      VARCHAR(100) NULL,
           [LEVEL0_ALIAS]                VARCHAR(100) NULL,
           [LEVEL1]                      VARCHAR(100) NULL,
           [LEVEL1_ALIAS]                VARCHAR(100) NULL,
           [LEVEL2]                      VARCHAR(100) NULL,
           [LEVEL2_ALIAS]                VARCHAR(100) NULL,
           [LEVEL3]                      VARCHAR(100) NULL,
           [LEVEL3_ALIAS]                VARCHAR(100) NULL,
           [LEVEL4]                      VARCHAR(100) NULL,
           [LEVEL4_ALIAS]                VARCHAR(100) NULL,
           [LEVEL5]                      VARCHAR(100) NULL,
           [LEVEL5_ALIAS]                VARCHAR(100) NULL,
           [LEVEL6]                      VARCHAR(100) NULL,
           [LEVEL6_ALIAS]                VARCHAR(100) NULL,
           [LEVEL7]                      VARCHAR(100) NULL,
           [LEVEL7_ALIAS]                VARCHAR(100) NULL,
           [LEVEL8]                      VARCHAR(100) NULL,
           [LEVEL8_ALIAS]                VARCHAR(100) NULL,
           [LEVEL9]                      VARCHAR(100) NULL,
           [LEVEL9_ALIAS]                VARCHAR(100) NULL,
           [LEVEL10]                     VARCHAR(100) NULL,
           [LEVEL10_ALIAS]               VARCHAR(100) NULL,
           [LEVEL11]                     VARCHAR(100) NULL,
           [LEVEL11_ALIAS]               VARCHAR(100) NULL,
           [LEVEL12]                     VARCHAR(100) NULL,
           [LEVEL12_ALIAS]               VARCHAR(100) NULL,
           [LEVEL13]                     VARCHAR(100) NULL,
           [LEVEL13_ALIAS]               VARCHAR(100) NULL,
           [LEVEL14]                     VARCHAR(100) NULL,
           [LEVEL14_ALIAS]               VARCHAR(100) NULL,
           [LEVEL15]                     VARCHAR(100) NULL,
           [LEVEL15_ALIAS]               VARCHAR(100) NULL,
           [LEVEL16]                     VARCHAR(100) NULL,
           [LEVEL16_ALIAS]               VARCHAR(100) NULL,
           [LEVEL17]                     VARCHAR(100) NULL,
           [LEVEL17_ALIAS]               VARCHAR(100) NULL,
           [LEVEL18]                     VARCHAR(100) NULL,
           [LEVEL18_ALIAS]               VARCHAR(100) NULL,
           [LEVEL19]                     VARCHAR(100) NULL,
           [LEVEL19_ALIAS]               VARCHAR(100) NULL,
           [LEVEL20]                     VARCHAR(100) NULL,
           [LEVEL20_ALIAS]               VARCHAR(100) NULL,
           [ADD_CHG_DEL_INDICATOR]       VARCHAR(10) NOT NULL,
           [CREATED_BY]                  VARCHAR(50) NULL,
           [CREATED_DATE]                DATETIME NULL,
           [MODIFIED_BY]                 VARCHAR(50) NULL,
           [MODIFIED_DATE]               DATETIME NULL,
           [BATCH_ID]                    VARCHAR(38) NOT NULL,
           [SOURCE]                      VARCHAR(50) NULL,
           [GTN_BRAND]                   VARCHAR(100) NULL,
           [GTN_THERAPEUTIC_CLASS]       VARCHAR(100) NULL,
           [GTN_COMPANY]                 VARCHAR(100) NOT NULL
        )
  END
GO 



