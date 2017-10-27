IF EXISTS (SELECT
    'X'
  FROM INFORMATION_SCHEMA.VIEWS
  WHERE TABLE_NAME = 'VW_HELPER_LIST'
  AND TABLE_SCHEMA = 'dbo')
BEGIN
  DROP VIEW [dbo].[VW_HELPER_LIST]
END
GO

CREATE VIEW [dbo].[VW_HELPER_LIST]
AS
SELECT
  ROW_NUMBER() OVER (
  ORDER BY ACTUAL_TABLE_NAME
  ) UNIQUE_ID,
  b.ACTUAL_TABLE_NAME,
  ACTUAL_COLUMN_NAME,
  REFERENCE_TABLE_NAME,
  REFERENCE_COLUMN_NAME,
  MAPPING_COLUMN_NAME,
  DESC_COLUMN_NAME,
  PRIMARY_KEY_COLUMN_NAME,
  LIST_NAME
FROM (SELECT DISTINCT
  A.ACTUAL_TABLE_NAME,
  A.ACTUAL_COLUMN_NAME,
  A.REFERENCE_TABLE_NAME,
  A.REFERENCE_COLUMN_NAME,
  A.MAPPING_COLUMN_NAME,
  A.DESC_COLUMN_NAME,
  CASE
    WHEN A.ACTUAL_COLUMN_NAME = 'RECORD_LOCK_STATUS' THEN NULL
    WHEN A.ACTUAL_COLUMN_NAME = 'INBOUND_STATUS' THEN NULL
    WHEN A.ACTUAL_COLUMN_NAME = 'AUTHORITIES' THEN NULL
    WHEN A.ACTUAL_COLUMN_NAME LIKE '%STATUS%' THEN 'STATUS'
    WHEN A.ACTUAL_TABLE_NAME = 'CFP_MODEL' AND
      A.ACTUAL_COLUMN_NAME = 'CFP_DESIGNATION' THEN 'CFP_DESIGNATION'
    WHEN A.ACTUAL_TABLE_NAME = 'CFP_CONTRACT_DETAILS' AND
      A.ACTUAL_COLUMN_NAME = 'TRADE_CLASS' THEN 'CFP_TRADE_CLASS'
    WHEN A.ACTUAL_TABLE_NAME = 'COMPANY_TRADE_CLASS' AND
      A.ACTUAL_COLUMN_NAME = 'PRIOR_TRADE_CLASS' THEN 'COMPANY_TRADE_CLASS'
    WHEN A.ACTUAL_TABLE_NAME = 'ITEM_MASTER' AND
      A.ACTUAL_COLUMN_NAME = 'PRIMARY_UOM' THEN 'UOM'
    WHEN A.ACTUAL_TABLE_NAME = 'ITEM_MASTER' AND
      A.ACTUAL_COLUMN_NAME = 'SECONDARY_UOM' THEN 'UOM'
    WHEN A.ACTUAL_TABLE_NAME = 'ITEM_PRICING' AND
      A.ACTUAL_COLUMN_NAME = 'ITEM_UOM' THEN 'UOM'
    WHEN A.ACTUAL_TABLE_NAME = 'PS_DETAILS' AND
      A.ACTUAL_COLUMN_NAME = 'RESET_FREQUENCY' THEN 'PRICE_TOLERANCE_FREQUENCY'
    WHEN A.ACTUAL_TABLE_NAME = 'PS_DETAILS' AND
      A.ACTUAL_COLUMN_NAME = 'RESET_INTERVAL' THEN 'PRICE_TOLERANCE_INTERVAL'
    WHEN A.ACTUAL_TABLE_NAME = 'WORKFLOW_MASTER' AND
      A.ACTUAL_COLUMN_NAME = 'WORKFLOW_STATUS_ID' THEN 'WorkFlowStatus'
    ELSE ht.list_name
  END LIST_NAME,
  CU.COLUMN_NAME PRIMARY_KEY_COLUMN_NAME
FROM (SELECT
  C.TABLE_NAME ACTUAL_TABLE_NAME,
  C.COLUMN_NAME ACTUAL_COLUMN_NAME,
  CASE
    WHEN CU.REFERENCED_TABLE IS NULL THEN C.TABLE_NAME
    ELSE CU.REFERENCED_TABLE
  END REFERENCE_TABLE_NAME,
  CASE
    WHEN CU.REFERENCED_COLUMN = 'HELPER_TABLE_SID' THEN 'DESCRIPTION'
    WHEN CU.REFERENCED_COLUMN IS NULL THEN C.COLUMN_NAME
    ELSE CU.REFERENCED_COLUMN
  END REFERENCE_COLUMN_NAME,
  CASE
    WHEN CU.REFERENCED_COLUMN IS NOT NULL THEN CU.REFERENCED_COLUMN
    ELSE CU.REFERENCED_COLUMN
  END MAPPING_COLUMN_NAME,
  CASE
    WHEN CU.REFERENCED_COLUMN LIKE '%_SID' THEN ISNULL((SELECT
        column_name
      FROM INFORMATION_SCHEMA.COLUMNS
      WHERE table_name = CU.REFERENCED_TABLE
      AND column_name LIKE '%_NO'
      AND ordinal_position < 5), (SELECT
        column_name
      FROM INFORMATION_SCHEMA.COLUMNS
      WHERE table_name = CU.REFERENCED_TABLE
      AND column_name LIKE '%_NAME'
      AND ordinal_position <= 3
      AND COLUMN_NAME <> 'list_name')
      )
	ELSE NULL
  END DESC_COLUMN_NAME
FROM INFORMATION_SCHEMA.columns C
LEFT OUTER JOIN (SELECT
  TAB1.NAME AS [MAIN_TABLE],
  COL1.NAME AS [MAIN_COLUMN],
  TAB2.NAME AS [REFERENCED_TABLE],
  COL2.NAME AS [REFERENCED_COLUMN]
FROM SYS.FOREIGN_KEY_COLUMNS fkc
INNER JOIN SYS.OBJECTS obj
  ON obj.object_id = fkc.constraint_object_id
INNER JOIN SYS.TABLES tab1
  ON tab1.object_id = fkc.parent_object_id
INNER JOIN SYS.SCHEMAS sch
  ON tab1.schema_id = sch.schema_id
INNER JOIN SYS.COLUMNS col1
  ON col1.column_id = fkc.parent_column_id
  AND col1.object_id = tab1.object_id
INNER JOIN sys.tables tab2
  ON tab2.object_id = fkc.referenced_object_id
INNER JOIN sys.columns col2
  ON col2.column_id = fkc.referenced_column_id
  AND col2.object_id = tab2.object_id) cu
  ON C.TABLE_NAME = CU.MAIN_TABLE
  AND C.COLUMN_NAME = CU.MAIN_COLUMN
WHERE C.TABLE_NAME NOT LIKE 'SNAP_%'
AND C.TABLE_NAME NOT LIKE 'STAG_%'
AND C.TABLE_NAME NOT LIKE 'IVLD_%'
AND C.TABLE_NAME NOT LIKE 'ST_%'
AND C.TABLE_NAME NOT LIKE 'HIST_%'
AND C.TABLE_NAME NOT LIKE 'IMTD_%'
AND C.TABLE_NAME NOT LIKE '%_INBOUND%'
AND C.TABLE_NAME NOT LIKE '%_OUTBOUND%'
AND C.TABLE_NAME NOT LIKE 'vw_%'
AND C.TABLE_NAME NOT LIKE 'NM_%'
AND C.TABLE_NAME NOT LIKE 'M_%'
AND C.TABLE_NAME NOT LIKE 'CH_%'
AND C.TABLE_NAME NOT LIKE 'NATIONAL%'
AND C.TABLE_NAME NOT LIKE 'PHS_%'
AND C.TABLE_NAME NOT LIKE 'FCP_%'
AND C.TABLE_NAME NOT LIKE 'HIERARCHY_%'
AND C.TABLE_NAME NOT LIKE 'NA_%'
AND C.TABLE_NAME NOT LIKE 'PROJECTION_%'
AND C.TABLE_NAME NOT LIKE 'RELATIONSHIP_%'
AND C.TABLE_NAME NOT LIKE 'CCP_MAP%'
AND C.TABLE_NAME NOT LIKE 'CUSTOM_%'
AND C.TABLE_NAME NOT LIKE 'WORKFLOW_%'
AND C.TABLE_NAME NOT LIKE 'NEW_NDC%'
AND C.TABLE_NAME NOT LIKE 'CFF_%'
AND C.TABLE_NAME NOT LIKE '%_FR'
AND C.TABLE_NAME NOT LIKE 'ACC_%'
AND C.TABLE_NAME NOT LIKE 'AC_%'
AND C.TABLE_NAME NOT LIKE 'TRANSACTION_MODULE%') a
LEFT OUTER JOIN dbo.helper_table ht
  ON a.ACTUAL_COLUMN_NAME = ht.list_name
JOIN INFORMATION_SCHEMA.CONSTRAINT_COLUMN_USAGE cu
  ON a.ACTUAL_TABLE_NAME = cu.TABLE_NAME
WHERE cu.constraint_name LIKE '%PK%') b