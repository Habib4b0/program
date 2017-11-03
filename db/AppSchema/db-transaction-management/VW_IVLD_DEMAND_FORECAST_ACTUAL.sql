IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.VIEWS
           WHERE  TABLE_NAME = 'VW_IVLD_DEMAND_FORECAST_ACTUAL'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      DROP VIEW [dbo].[VW_IVLD_DEMAND_FORECAST_ACTUAL]
  END

GO

CREATE VIEW [dbo].[VW_IVLD_DEMAND_FORECAST_ACTUAL]

	AS
 
SELECT DF.DEMAND_FORECAST_INTERFACE_ID AS DEMAND_INT_SID,
         DF.IVLD_DEMAND_FORECAST_SID  AS IVLD_DEMAND_ACTUAL_FORECAST_SID,
         DF.FORECAST_NAME,
         DF.FORECAST_VER,
         DF.FORECAST_TYPE,
         IM.ITEM_ID,
         IM.ITEM_NAME,
         BM.BRAND_ID,
         BM.BRAND_NAME,
         DF.SEGMENT,
         DF.FORECAST_YEAR,
         DF.FORECAST_MONTH,
         DF.MARKET_SIZE_UNITS,
         DF.MARKET_SHARE_RATIO,
         DF.MARKET_SHARE_UNITS,
         DF.UNCAPTURED_UNITS,
         DF.UNCAPTURED_UNITS_RATIO,
         DF.TOTAL_DEMAND_UNITS,
         DF.TOTAL_DEMAND_AMOUNT,
         DF.INVENTORY_UNIT_CHANGE,
         DF.NET_SALES_PRICE,
         DF.NET_SALES_AMOUNT,
         DF.GROSS_UNITS,
         DF.GROSS_PRICE,
         DF.GROSS_AMOUNT,
         DF.BATCH_ID,
         DF.SOURCE,
         DF.COUNTRY,
         DF.ORGANIZATION_KEY,
         CM.COMPANY_NO                BUSINESS_UNIT_NO,
         CM.COMPANY_NAME              BUSINESS_UNIT_NAME,
         NULL                         AS IS_ACTIVE,
         1                            AS IS_FORECAST,
         DF.ADD_CHG_DEL_INDICATOR,
         DF.REASON_FOR_FAILURE,
         DF.ERROR_CODE,
         DF.ERROR_FIELD,
         DF.REPROCESSED_FLAG,
		 DF.CHECK_RECORD,
		 (select HELPER_TABLE_SID from HELPER_TABLE where list_name ='FORECAST_TYPE' and description='projections') as FORECAST_TYPE_SID
  FROM   dbo.IVLD_DEMAND_FORECAST DF
         LEFT JOIN dbo.ITEM_MASTER IM
                ON IM.ITEM_ID = DF.ITEM_ID
         LEFT JOIN dbo.BRAND_MASTER BM
                ON BM.BRAND_ID = DF.BRAND_ID
         LEFT JOIN dbo.COMPANY_MASTER CM
                ON CM.COMPANY_NO = DF.ORGANIZATION_KEY
  UNION ALL
  SELECT DA.DEMAND_ACTUAL_INTERFACE_ID,
         DA.IVLD_DEMAND_ACTUAL_SID,
         NULL            FORECAST_NAME,
         NULL            FORECAST_VER,
         DA.FORECAST_TYPE,
         IM.ITEM_ID,
         IM.ITEM_NAME,
         BM.BRAND_ID,
         BM.BRAND_NAME,
         DA.SEGMENT,
         DA.FORECAST_YEAR,
         DA.FORECAST_MONTH,
         DA.MARKET_SIZE_UNITS,
         DA.MARKET_SHARE_RATIO,
         DA.MARKET_SHARE_UNITS,
         NULL            UNCAPTURED_UNITS,
         NULL            UNCAPTURED_UNITS_RATIO,
         DA.TOTAL_DEMAND_UNITS,
         DA.TOTAL_DEMAND_AMOUNT,
         NULL            AS INVENTORY_UNIT_CHANGE,
         DA.GROSS_UNITS,
         DA.GROSS_PRICE,
         DA.GROSS_AMOUNT,
         DA.NET_SALES_PRICE,
         DA.NET_SALES_AMOUNT,
         DA.BATCH_ID,
         DA.SOURCE,
         DA.COUNTRY,
         DA.ORGANIZATION_KEY,
         CM.COMPANY_NO   BUSINESS_UNIT_NO,
         CM.COMPANY_NAME BUSINESS_UNIT_NAME,
         CASE
           WHEN EXISTS (SELECT NDC
                               FROM   FORECASTING_MASTER FM
                                      JOIN (SELECT TOP 1 FT.FORECAST_NAME,
                                                         FT.[VERSION]
                                            FROM   FILE_MANAGEMENT FT
                                            WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE())
                                                     AND FT.FROM_PERIOD IS NOT NULL )
                                                   AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE())
                                                          OR FT.TO_PERIOD IS NULL )
                                            ORDER  BY FT.FROM_PERIOD DESC) F
                                        ON FM.FORECAST_NAME = F.FORECAST_NAME
                                           AND FM.FORECAST_VER IN ( F.[VERSION], FLOOR(F.[VERSION]) )WHERE FM.NDC=IM.ITEM_ID) THEN 1
           ELSE 0
         END             IS_ACTIVE,
         0               AS IS_FORECAST,
         DA.ADD_CHG_DEL_INDICATOR,
         DA.REASON_FOR_FAILURE,
         DA.ERROR_CODE,
         DA.ERROR_FIELD,
         DA.REPROCESSED_FLAG,
		 DA.CHECK_RECORD,
		 (select HELPER_TABLE_SID from HELPER_TABLE where list_name ='FORECAST_TYPE' and description='Actuals') as FORECAST_TYPE_SID
  FROM   dbo.IVLD_DEMAND_ACTUAL DA
         LEFT JOIN dbo.ITEM_MASTER IM
                ON IM.ITEM_ID = DA.ITEM_ID
         LEFT JOIN dbo.BRAND_MASTER BM
                ON BM.BRAND_ID = DA.BRAND_ID
         LEFT JOIN dbo.COMPANY_MASTER CM
                ON CM.COMPANY_NO = DA.ORGANIZATION_KEY
GO 