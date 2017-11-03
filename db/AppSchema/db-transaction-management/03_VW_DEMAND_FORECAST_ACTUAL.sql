IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.VIEWS
           WHERE  TABLE_NAME = 'VW_DEMAND_FORECAST_ACTUAL'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      DROP VIEW [DBO].[VW_DEMAND_FORECAST_ACTUAL]
  END

GO

CREATE VIEW [dbo].[VW_DEMAND_FORECAST_ACTUAL]
AS 
  SELECT ROW_NUMBER()
           OVER(
             ORDER BY IS_FORECAST, DEMAND_FORECAST_ACTUAL_SID)AS DEMAND_ID,
         DEMAND_FORECAST_ACTUAL_SID,
         FORECAST_NAME,
         FORECAST_VER,
         FORECAST_TYPE,
         ITEM_ID,
         ITEM_NAME,
         BRAND_ID,
         BRAND_NAME,
         SEGMENT,
         FORECAST_YEAR,
         FORECAST_MONTH,
         MARKET_SIZE_UNITS,
         MARKET_SHARE_RATIO,
         MARKET_SHARE_UNITS,
         UNCAPTURED_UNITS,
         UNCAPTURED_UNITS_RATIO,
         TOTAL_DEMAND_UNITS,
         TOTAL_DEMAND_AMOUNT,
         INVENTORY_UNIT_CHANGE,
         NET_SALES_PRICE,
         NET_SALES_AMOUNT,
         GROSS_UNITS,
         GROSS_PRICE,
         GROSS_AMOUNT,
         BATCH_ID,
         SOURCE,
         COUNTRY,
         ORGANIZATION_KEY,
         BUSINESS_UNIT_NO,
         BUSINESS_UNIT_NAME,
         IS_ACTIVE,
         IS_FORECAST
		 ,FORECAST_TYPE_SID
  FROM  (SELECT DF.DEMAND_FORECAST_SID AS DEMAND_FORECAST_ACTUAL_SID,
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
                CM.COMPANY_NO          BUSINESS_UNIT_NO,
                CM.COMPANY_NAME        BUSINESS_UNIT_NAME,
                NULL                   AS IS_ACTIVE,
                1                      AS IS_FORECAST,
			 (select HELPER_TABLE_SID from HELPER_TABLE where list_name ='FORECAST_TYPE' and description='projections') as FORECAST_TYPE_SID
         FROM   dbo.DEMAND_FORECAST DF
                LEFT JOIN dbo.ITEM_MASTER IM
                       ON IM.ITEM_MASTER_SID = DF.ITEM_MASTER_SID
                LEFT JOIN dbo.BRAND_MASTER BM
                       ON BM.BRAND_MASTER_SID = DF.BRAND_MASTER_SID
                LEFT JOIN dbo.COMPANY_MASTER CM
                       ON CM.COMPANY_ID = DF.ORGANIZATION_KEY
         UNION ALL
         SELECT DA.DEMAND_ACTUAL_SID,
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
                DA.NET_SALES_PRICE,
                DA.NET_SALES_AMOUNT,
                DA.GROSS_UNITS,
                DA.GROSS_PRICE,
                DA.GROSS_AMOUNT,
                DA.BATCH_ID,
                DA.SOURCE,
                DA.COUNTRY,
                DA.ORGANIZATION_KEY,
                CM.COMPANY_NO   BUSINESS_UNIT_NO,
                CM.COMPANY_NAME BUSINESS_UNIT_NAME,
                CASE
                  WHEN EXISTS (SELECT FM.ITEM_ID
                                      FROM   DEMAND_FORECAST FM
                                             JOIN (SELECT TOP 1 FT.FORECAST_NAME,
                                                                FT.[VERSION]
                                                   FROM   FILE_MANAGEMENT FT
                                                          JOIN HELPER_TABLE HT
                                                            ON HT.HELPER_TABLE_SID = FT.FILE_TYPE
                                                   WHERE  ( CONVERT(DATE, FT.FROM_PERIOD) <= CONVERT(DATE, GETDATE())
                                                            AND FT.FROM_PERIOD IS NOT NULL )
                                                          AND ( CONVERT(DATE, FT.TO_PERIOD) >= CONVERT(DATE, GETDATE())
                                                                 OR FT.TO_PERIOD IS NULL )
                                                          AND HT.DESCRIPTION = 'DEMAND'
                                                   ORDER  BY FT.FROM_PERIOD DESC) F
                                               ON FM.FORECAST_NAME = F.FORECAST_NAME
                                                  AND FM.FORECAST_VER IN ( F.[VERSION], FLOOR(F.[VERSION]) ) WHERE FM.ITEM_ID=IM.ITEM_ID) THEN 1
                  ELSE 0
                END             IS_ACTIVE,
                0               AS IS_FORECAST,
				(select HELPER_TABLE_SID from HELPER_TABLE where list_name ='FORECAST_TYPE' and description='Actuals') as FORECAST_TYPE_SID
         FROM   dbo.DEMAND_ACTUAL DA
                LEFT JOIN dbo.ITEM_MASTER IM
                       ON IM.ITEM_MASTER_SID = DA.ITEM_MASTER_SID
                LEFT JOIN dbo.BRAND_MASTER BM
                       ON BM.BRAND_MASTER_SID = DA.BRAND_MASTER_SID
                LEFT JOIN dbo.COMPANY_MASTER CM
                       ON CM.COMPANY_ID = DA.ORGANIZATION_KEY)T

GO 
