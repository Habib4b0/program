IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.VIEWS
           WHERE  TABLE_NAME = 'VW_CUSTOMER_GTS_FORECAST'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      DROP VIEW [dbo].[VW_CUSTOMER_GTS_FORECAST]
  END

GO

CREATE VIEW [dbo].[VW_CUSTOMER_GTS_FORECAST]
AS
  SELECT    CUSTOMER_GTS_FORECAST_SID,
            FORECAST_YEAR,
            FORECAST_MONTH,
            CGT.ITEM_ID,
            CGT.COMPANY_ID,
            UNITS,
            PRICE_TYPE,
            PRICE,
            SALES_AMOUNT,
            SALES_INCLUSION,
            DEDUCTION_ID,
            HT.DESCRIPTION AS DEDUCTION_CATEGORY,
            HT1.DESCRIPTION AS DEDUCTION_TYPE,
            HT2.DESCRIPTION AS DEDUCTION_PROGRAM_TYPE,
            ADJUSTMENT_CODE,
            DEDUCTION_RATE,
            DEDUCTION_AMOUNT,
            DEDUCTION_INCLUSION,
            FORECAST_VALUE_TYPE,
            BRAND,
            SEGMENT,
            ADD_CHG_DEL_INDICATOR,
            UDC1,
            UDC2,
            UDC3,
            UDC4,
            UDC5,
            UDC6,
            CGT.CREATED_BY,
            CGT.CREATED_DATE,
            CGT.MODIFIED_BY,
            CGT.MODIFIED_DATE,
            CGT.BATCH_ID,
            CGT.SOURCE,
            FORECAST_VER,
            CGT.COUNTRY,
            FORECAST_NAME,
            FORECAST_DATE,
            CUSTOMER_GTS_FORECAST_INTF_ID
  FROM      CUSTOMER_GTS_FORECAST CGT
  LEFT JOIN ITEM_MASTER IM ON IM.ITEM_ID = CGT.ITEM_ID
  LEFT JOIN BRAND_MASTER BM ON BM.BRAND_NAME = CGT.BRAND
  LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_ID = CGT.COMPANY_ID
  LEFT JOIN UDCS U ON U.MASTER_SID = CGT.CUSTOMER_GTS_FORECAST_SID
   AND U.master_type = 'CUSTOMER_GTS_FORECAST'
   LEFT JOIN HELPER_TABLE HT ON HT.HELPER_TABLE_SID = CGT.DEDUCTION_CATEGORY
   LEFT JOIN HELPER_TABLE HT1 ON HT1.HELPER_TABLE_SID = CGT.DEDUCTION_TYPE
   LEFT JOIN HELPER_TABLE HT2 ON HT2.HELPER_TABLE_SID = CGT.DEDUCTION_PROGRAM_TYPE

GO