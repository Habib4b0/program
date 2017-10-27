IF EXISTS (SELECT 'X'
           FROM   INFORMATION_SCHEMA.VIEWS
           WHERE  TABLE_NAME = 'VW_ITEM_MASTER'
                  AND TABLE_SCHEMA = 'dbo')
  BEGIN
      DROP VIEW [dbo].VW_ITEM_MASTER
  END
GO
CREATE VIEW [dbo].[VW_ITEM_MASTER]
AS
SELECT ITEM_MASTER_SID
	,IM.ITEM_ID
	,IM.ITEM_NO
	,IM.ITEM_NAME
	,IM.ITEM_DESC
	,H1.DESCRIPTION FORM
	,H2.DESCRIPTION STRENGTH
	,BM.BRAND_ID
	,BM.BRAND_NAME AS BRAND
	,IM.ORGANIZATION_KEY
	,IM.LABELER_CODE
	,IM.NDC8 NDC8
	,IM.NDC9 NDC9
	,H3.DESCRIPTION ITEM_CATEGORY
	,H10.DESCRIPTION THERAPEUTIC_CLASS
	,H11.DESCRIPTION ITEM_TYPE
	,IM.BATCH_ID
	,IM.ITEM_START_DATE
	,H12.DESCRIPTION ITEM_STATUS
	,IM.ITEM_END_DATE
	,IM.ITEM_CODE
	,H4.DESCRIPTION ITEM_CLASS
	,H6.DESCRIPTION PACKAGE_SIZE
	,IM.PACKAGE_SIZE_CODE
	,IM.PACKAGE_SIZE_INTRO_DATE
	,IM.UPPS
	,CM.COMPANY_ID MANUFACTURER_ID
	,CM.COMPANY_NO MANUFACTURER_NO
	,CM.COMPANY_NAME MANUFACTURER_NAME
	,IM.ACQUISITION_DATE
	,IM.AUTHORIZED_GENERIC
	,IM.AUTHORIZED_GENERIC_START_DATE
	,IM.AUTHORIZED_GENERIC_END_DATE
	,IM.FIRST_SALE_DATE
	,H5.DESCRIPTION ITEM_TYPE_INDICATION
	,IM.MARKET_TERMINATION_DATE
	,IM.NEW_FORMULATION_INDICATOR
	,IM.NEW_FORMULATION
	,IM.NEW_FORMULATION_START_DATE
	,IM.NEW_FORMULATION_END_DATE
	,IM.PEDIATRIC_EXCLUSIVE_INDICATOR
	,IM.PEDIATRIC_EXCLUSIVE_START_DATE
	,IM.PEDIATRIC_EXCLUSIVE_END_DATE
	,IM.CLOTTING_FACTOR_INDICATOR
	,IM.CLOTTING_FACTOR_START_DATE
	,IM.CLOTTING_FACTOR_END_DATE
	,H7.DESCRIPTION PRIMARY_UOM
	,H8.DESCRIPTION SECONDARY_UOM
	,IM.SHELF_LIFE
	,H9.DESCRIPTION SHELF_LIFE_TYPE
	,IM.DUAL_PRICING_INDICATOR
	,IM.ITEM_FAMILY_ID
	,HT3.DESCRIPTION UDC1
	,HT4.DESCRIPTION UDC2
	,HT5.DESCRIPTION UDC3
	,HT6.DESCRIPTION UDC4
	,HT7.DESCRIPTION UDC5
	,HT8.DESCRIPTION UDC6
	,IM.ACQUIRED_AMP
	,IM.ACQUIRED_BAMP
	,IM.OBRA_BAMP
	,IM.DRA
	,IM.DOSES_PER_UNIT
	,IM.DISCONTINUATION_DATE
	,IM.LAST_LOT_EXPIRATION_DATE
	,IM.DIVESTITURE_DATE
	--,IM.LAST_LOT_EXPIRATION_DATE	LASTLOTEXPIRATIONDATE
	,BM.DISPLAY_BRAND
	,IM.NON_FEDERAL_EXPIRATION_DATE
	,IM.BASELINE_AMP
	,IM.BASE_CPI_PERIOD
	,IM.BASE_CPI
	,IM.INBOUND_STATUS ADD_CHG_DEL_INDICATOR
	,IM.CREATED_BY
	,IM.CREATED_DATE
	,IM.MODIFIED_BY
	,IM.MODIFIED_DATE
	,IM.SOURCE
	,IM.BASE_CPI_PRECISION
	,IM.BASELINE_AMP_PRECISION
FROM ITEM_MASTER IM
LEFT JOIN BRAND_MASTER BM ON BM.BRAND_MASTER_SID = IM.BRAND_MASTER_SID
	AND BM.INBOUND_STATUS <> 'D'
LEFT JOIN COMPANY_MASTER CM ON CM.COMPANY_MASTER_SID = IM.MANUFACTURER_ID
	AND CM.INBOUND_STATUS <> 'D'
LEFT JOIN UDCS U ON U.MASTER_SID = IM.ITEM_MASTER_SID
	AND U.MASTER_TYPE = 'ITEM_MASTER'
LEFT JOIN HELPER_TABLE H1 ON H1.HELPER_TABLE_SID = IM.FORM
	AND H1.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE H2 ON H2.HELPER_TABLE_SID = IM.STRENGTH
	AND H2.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE H3 ON H3.HELPER_TABLE_SID = IM.ITEM_CATEGORY
	AND H3.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE H4 ON H4.HELPER_TABLE_SID = IM.ITEM_CLASS
	AND H4.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE H5 ON H5.HELPER_TABLE_SID = IM.ITEM_TYPE_INDICATION
	AND H5.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE H6 ON H6.HELPER_TABLE_SID = IM.PACKAGE_SIZE
	AND H6.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE H7 ON H7.HELPER_TABLE_SID = IM.PRIMARY_UOM
	AND H7.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE H8 ON H8.HELPER_TABLE_SID = IM.SECONDARY_UOM
	AND H8.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE H9 ON H9.HELPER_TABLE_SID = IM.SHELF_LIFE_TYPE
	AND H9.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE H10 ON H10.HELPER_TABLE_SID = IM.THERAPEUTIC_CLASS
	AND H10.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE H11 ON H11.HELPER_TABLE_SID = IM.ITEM_TYPE
	AND H11.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE H12 ON H12.HELPER_TABLE_SID = IM.ITEM_STATUS
	AND H12.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE HT3 ON HT3.HELPER_TABLE_SID = U.UDC1
	AND HT3.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE HT4 ON HT4.HELPER_TABLE_SID = U.UDC2
	AND HT4.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE HT5 ON HT5.HELPER_TABLE_SID = U.UDC3
	AND HT5.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE HT6 ON HT6.HELPER_TABLE_SID = U.UDC4
	AND HT6.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE HT7 ON HT7.HELPER_TABLE_SID = U.UDC5
	AND HT7.HELPER_TABLE_SID <> 0
LEFT JOIN HELPER_TABLE HT8 ON HT8.HELPER_TABLE_SID = U.UDC6
	AND HT8.HELPER_TABLE_SID <> 0
WHERE IM.INBOUND_STATUS <> 'D'
GO


