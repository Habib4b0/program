IF NOT EXISTS (SELECT 'X'
               FROM   SYS.TABLES
               WHERE  Object_name(OBJECT_ID) = 'STATUS_TABLE'
                      AND Schema_name(SCHEMA_ID) = 'DBO')
  BEGIN
      CREATE TABLE [dbo].[STATUS_TABLE]
        (
           [SCREEN_NAME] [VARCHAR](100) NOT NULL,
           [VIEW_NAME]   [VARCHAR](50) NOT NULL,
           [FLAG]        [CHAR](2) NULL
        )
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'SCREEN_NAME'
                  AND Object_name(OBJECT_ID) = 'STATUS_TABLE')
  BEGIN
      DROP STATISTICS DBO.STATUS_TABLE.SCREEN_NAME
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'SCREEN_NAME'
                  AND TABLE_NAME = 'STATUS_TABLE'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE STATUS_TABLE
        ALTER COLUMN SCREEN_NAME VARCHAR(100) NOT NULL
  END

GO

IF EXISTS (SELECT 1
           FROM   SYS.STATS
           WHERE  NAME = 'VIEW_NAME'
                  AND Object_name(OBJECT_ID) = 'STATUS_TABLE')
  BEGIN
      DROP STATISTICS DBO.STATUS_TABLE.VIEW_NAME
  END

GO

IF EXISTS (SELECT 1
           FROM   INFORMATION_SCHEMA.COLUMNS
           WHERE  COLUMN_NAME = 'VIEW_NAME'
                  AND TABLE_NAME = 'STATUS_TABLE'
                  AND DATA_TYPE = 'VARCHAR')
  BEGIN
      ALTER TABLE STATUS_TABLE
        ALTER COLUMN VIEW_NAME VARCHAR(100) NOT NULL
  END

GO

IF NOT EXISTS(SELECT 'X'
              FROM   INFORMATION_SCHEMA.TABLE_CONSTRAINTS
              WHERE  CONSTRAINT_NAME = 'PK_STATUS_TABLE_SCREEN_NAME_VIEW_NAME'
                     AND TABLE_NAME = 'STATUS_TABLE')
  BEGIN
      ALTER TABLE [DBO].[STATUS_TABLE]
        ADD CONSTRAINT PK_STATUS_TABLE_SCREEN_NAME_VIEW_NAME PRIMARY KEY(SCREEN_NAME, VIEW_NAME)
  END

GO

INSERT INTO STATUS_TABLE
            ([SCREEN_NAME],
             [VIEW_NAME],
             [FLAG])
SELECT [SCREEN_NAME],
       [VIEW_NAME],
       [FLAG]
FROM   (VALUES ('DISCOUNT',
       'CUSTOM',
       NULL),
               ('DISCOUNT',
       'CUSTOMER',
       NULL),
               ('DISCOUNT',
       'PRC_CONTRACT_DETAILS_REBATE',
       NULL),
               ('DISCOUNT',
       'PRC_NM_MASTER_INSERT',
       NULL),
               ('DISCOUNT',
       'PRC_NM_PROJECTION_INSERT',
       NULL),
               ('DISCOUNT',
       'PRODUCT',
       NULL),
               ('FORECASTING',
       'SAVE_VIEW',
       NULL),
               ('SALES',
       'CUSTOM',
       NULL),
               ('SALES',
       'CUSTOMER',
       NULL),
               ('SALES',
       'PRC_NM_MASTER_INSERT',
       NULL),
               ('SALES',
       'PRC_NM_PROJECTION_INSERT',
       NULL),
               ('SALES',
       'PRODUCT',
       NULL),
	     ('VARIANCE',
       'CUSTOMER',
       NULL),
	 ('VARIANCE',
       'PRODUCT',
       NULL),
	    ('VARIANCE',
       'CUSTOM',
       NULL),('CFF_EXCEL','CUSTOM',null)
,('CFF_EXCEL','CUSTOMER',null)
,('CFF_EXCEL','PRODUCT',null)
	  ) A([SCREEN_NAME], [VIEW_NAME], [FLAG])
WHERE  NOT EXISTS (SELECT 1
                   FROM   STATUS_TABLE B
                   WHERE  A.SCREEN_NAME = B.SCREEN_NAME
                          AND A.VIEW_NAME = B.VIEW_NAME);
GO 

