IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.VIEWS
           WHERE  TABLE_NAME = 'VW_INVENTORY_WD_ACTUAL_PROJ_MAS'
                  AND TABLE_SCHEMA = 'DBO')
  BEGIN
      DROP VIEW [DBO].[VW_INVENTORY_WD_ACTUAL_PROJ_MAS]
  END

GO

CREATE VIEW [dbo].[VW_INVENTORY_WD_ACTUAL_PROJ_MAS]
AS
  SELECT IWA.INVENTORY_WD_ACTUAL_MAS_SID AS INVENTORY_WD_ACTUAL_PROJ_MAS_SID,
         IWA.YEAR,
         IWA.MONTH,
         IWA.DAY,
         IWA.WEEK,
         COMPANY_ID,
         COMPANY_NAME,
         IM.ITEM_ID,
         IM.ITEM_NAME,
         IWA.UNITS_WITHDRAWN,
         IWA.AMOUNT_WITHDRAWN,
         IWA.UNITS_ON_HAND,
         IWA.AMOUNT_ON_HAND,
         IWA.QUANTITY_ON_ORDER,
         IWA.AMOUNT_ON_ORDER,
         IWA.QUANTITY_RECEIVED,
         IWA.AMOUNT_RECEIVED,
         PRICE = NULL,
         IWA.CREATED_BY,
         IWA.CREATED_DATE,
         IWA.MODIFIED_BY,
         IWA.MODIFIED_DATE,
         ADD_CHG_DEL_INDICATOR = IWA.INBOUND_STATUS,
         IWA.BATCH_ID,
         IWA.SOURCE,
         FORECAST_NAME = NULL,
         FORECAST_VER = NULL,
         IWA.COUNTRY,
         IWA.ORGANIZATION_KEY,
         0                               AS IS_FORECAST,
         1                               AS IS_MASTER
  FROM   INVENTORY_WD_ACTUAL_MAS IWA
         LEFT JOIN COMPANY_MASTER CM
                ON CM.COMPANY_ID = IWA.ORGANIZATION_KEY
         LEFT JOIN ITEM_MASTER IM
                ON IM.ITEM_MASTER_SID = IWA.ITEM_MASTER_SID
  UNION ALL
  SELECT IWP.INVENTORY_WD_PROJ_MAS_SID,
         IWP.YEAR,
         IWP.MONTH,
         IWP.DAY,
         IWP.WEEK,
         CM.COMPANY_ID,
         CM.COMPANY_NAME,
         IM.ITEM_ID,
         IM.ITEM_NAME,
         IWP.UNITS_WITHDRAWN,
         IWP.AMOUNT_WITHDRAWN,
         UNITS_ON_HAND = NULL,
         AMOUNT_ON_HAND = NULL,
         QUANTITY_ON_ORDER = NULL,
         AMOUNT_ON_ORDER = NULL,
         QUANTITY_RECEIVED = NULL,
         AMOUNT_RECEIVED = NULL,
         IWP.PRICE,
         IWP.CREATED_BY,
         IWP.CREATED_DATE,
         IWP.MODIFIED_BY,
         IWP.MODIFIED_DATE,
         ADD_CHG_DEL_INDICATOR = IWP.INBOUND_STATUS,
         IWP.BATCH_ID,
         IWP.SOURCE,
         IWP.FORECAST_NAME,
         IWP.FORECAST_VER,
         IWP.COUNTRY,
         IWP.ORGANIZATION_KEY,
         1 AS IS_FORECAST,
         1 AS IS_MASTER
  FROM   INVENTORY_WD_PROJ_MAS IWP
         LEFT JOIN COMPANY_MASTER CM
                ON CM.COMPANY_ID = IWP.ORGANIZATION_KEY
         LEFT JOIN ITEM_MASTER IM
                ON IM.ITEM_MASTER_SID = IWP.ITEM_MASTER_SID
  UNION ALL
  SELECT IWA.INVENTORY_WD_ACTUAL_DT_SID AS INVENTORY_WD_ACTUAL_PROJ_MAS_SID,
         IWA.YEAR,
         IWA.MONTH,
         IWA.DAY,
         IWA.WEEK,
         IWA.COMPANY_ID,
         COMPANY_NAME,
         IM.ITEM_ID,
         IM.ITEM_NAME,
         IWA.UNITS_WITHDRAWN,
         IWA.AMOUNT_WITHDRAWN,
         IWA.UNITS_ON_HAND,
         IWA.AMOUNT_ON_HAND,
         IWA.QUANTITY_ON_ORDER,
         IWA.AMOUNT_ON_ORDER,
         IWA.QUANTITY_RECEIVED,
         IWA.AMOUNT_RECEIVED,
         PRICE = NULL,
         IWA.CREATED_BY,
         IWA.CREATED_DATE,
         IWA.MODIFIED_BY,
         IWA.MODIFIED_DATE,
         ADD_CHG_DEL_INDICATOR = IWA.INBOUND_STATUS,
         IWA.BATCH_ID,
         IWA.SOURCE,
         FORECAST_NAME = NULL,
         FORECAST_VER = NULL,
         IWA.COUNTRY,
         IWA.ORGANIZATION_KEY,
         0                              AS IS_FORECAST,
         0                              AS IS_MASTER
  FROM   [dbo].[INVENTORY_WD_ACTUAL_DT] IWA
         LEFT JOIN COMPANY_MASTER CM
                ON CM.COMPANY_ID = IWA.COMPANY_ID
         LEFT JOIN ITEM_MASTER IM
                ON IM.ITEM_MASTER_SID = IWA.ITEM_MASTER_SID
  UNION ALL
  SELECT IWP.INVENTORY_WD_PROJ_DT_SID,
         IWP.YEAR,
         IWP.MONTH,
         IWP.DAY,
         IWP.WEEK,
         CM.COMPANY_ID,
         CM.COMPANY_NAME,
         IM.ITEM_ID,
         IM.ITEM_NAME,
         IWP.UNITS_WITHDRAWN,
         IWP.AMOUNT_WITHDRAWN,
         UNITS_ON_HAND = NULL,
         AMOUNT_ON_HAND = NULL,
         QUANTITY_ON_ORDER = NULL,
         AMOUNT_ON_ORDER = NULL,
         QUANTITY_RECEIVED = NULL,
         AMOUNT_RECEIVED = NULL,
         IWP.PRICE,
         IWP.CREATED_BY,
         IWP.CREATED_DATE,
         IWP.MODIFIED_BY,
         IWP.MODIFIED_DATE,
         ADD_CHG_DEL_INDICATOR = IWP.INBOUND_STATUS,
         IWP.BATCH_ID,
         IWP.SOURCE,
         IWP.FORECAST_NAME,
         IWP.FORECAST_VER,
         IWP.COUNTRY,
         IWP.ORGANIZATION_KEY,
         1 AS IS_FORECAST,
         0 AS IS_MASTER
  FROM   INVENTORY_WD_PROJ_DT IWP

         LEFT JOIN COMPANY_MASTER CM
                ON CM.COMPANY_ID = IWP.COMPANY_ID
         LEFT JOIN ITEM_MASTER IM
                ON IM.ITEM_MASTER_SID = IWP.ITEM_MASTER_SID



GO


