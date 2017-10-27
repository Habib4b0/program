IF EXISTS (SELECT
    'X'
  FROM INFORMATION_SCHEMA.VIEWS
  WHERE TABLE_NAME = 'VW_ACTUALS_MASTER'
  AND TABLE_SCHEMA = 'DBO')
BEGIN
  DROP VIEW [DBO].[VW_ACTUALS_MASTER]
END
GO

CREATE VIEW [DBO].[VW_ACTUALS_MASTER]
AS
SELECT
  AM.ACTUALS_MASTER_SID,
  AM.ACTUAL_ID,
  AM.CONTRACT_ID,
  AM.UPLOAD_DATE,
  AM.PROVISION_ID,
  AM.ACCRUAL_ACTUAL_START_DATE,
  AM.ACCRUAL_ACTUAL_END_DATE,
  AM.ITEM_ID,
  AM.ITEM_IDENTIFIER_CODE_QUALIFIER,
  AM.ITEM_NO,
  IM.ITEM_NAME,
  AM.COMPANY_MASTER_SID,
  CM.COMPANY_NO,
  CM.COMPANY_NAME,
  AM.CONTRACT_MASTER_SID,
  CON.CONTRACT_NO,
  CON.[CONTRACT_NAME],
  AM.ITEM_MASTER_SID,
  AM.SETTLEMENT_METHOD_NO,
  AM.CASH_PAID_DATE,
  AM.AMOUNT,
  AM.QUANTITY,
  AM.QUANTITY_INCLUSION,
  AM.SETTLEMENT_NO,
  AM.INVOICE_LINE_NO,
  AM.ACCOUNT_ID,
  AM.ACCT_IDENTIFIER_CODE_QUALIFIER,
  AM.ACCOUNT_NO,
  AM.ACCOUNT_NAME,
  AM.ANALYSIS_CODE,
  AM.IS_ACTIVE,
  AM.COM_DIV_MKT_BRAND_PROD_KEY,
  AM.PARENTCOM_DIVMKT_BRAND_PRODKEY,
  AM.PRICE_ADJUSTMENT_NAME,
  AM.PRICE,
  AM.RECORD_SEQUENCE,
  AM.SENT_OUT,
  AM.ACCRUAL_PROCESSED,
  AM.DIVISION_ID,
  AM.MARKET_ID,
  AM.BRAND_ID,
  AM.CLAIM_INDICATOR,
  AM.SALES_AMOUNT,
  AM.ORGANIZATION_KEY,
  AM.MANDATED_DISCOUNT_AMOUNT,
  AM.PROVISION_CLAIM_INDICATOR,
  AM.PROGRAM_STATE_CODE,
  AM.DISPENSED_DATE,
  AM.INBOUND_STATUS,
  AM.RECORD_LOCK_STATUS,
  AM.BATCH_ID,
  AM.SOURCE,
  AM.CREATED_BY,
  AM.CREATED_DATE,
  AM.MODIFIED_BY,
  AM.MODIFIED_DATE
FROM dbo.ACTUALS_MASTER AM
JOIN dbo.ITEM_MASTER IM
  ON IM.ITEM_MASTER_SID = AM.ITEM_MASTER_SID
JOIN dbo.COMPANY_MASTER CM
  ON CM.COMPANY_MASTER_SID = AM.COMPANY_MASTER_SID
JOIN dbo.CONTRACT_MASTER CON
  ON CON.CONTRACT_MASTER_SID = AM.CONTRACT_MASTER_SID
GO