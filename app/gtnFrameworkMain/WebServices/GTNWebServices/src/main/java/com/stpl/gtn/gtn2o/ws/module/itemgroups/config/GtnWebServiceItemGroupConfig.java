package com.stpl.gtn.gtn2o.ws.module.itemgroups.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.itemgroups.constants.GtnWsItemGrpQueryContants;

public class GtnWebServiceItemGroupConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWebServiceItemGroupConfig.class);

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (searchQueryConfigMap == null) {
			searchQueryConfigMap = new HashMap<>();
			loadSearchQueryConfig();
		}
		return searchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.searchQueryConfigMap = searchQueryConfigMap;
	}

	public Map<String, GtnWsSearchQueryConfig> loadSearchQueryConfig() {

		try {

			GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
			GtnWsSearchQueryConfig gtnItemGroupSearchQueryConfig = new GtnWsSearchQueryConfig();

			gtnItemGroupSearchQueryConfig.setCountQuery(GtnWsItemGrpQueryContants.GTN_IFP_SEARCH_QUERY);
			gtnItemGroupSearchQueryConfig.setSearchQuery(gtnItemGroupSearchQueryConfig.getCountQuery());
			gtnItemGroupSearchQueryConfig
					.setCountQuerySelectClause(GtnWsItemGrpQueryContants.GTN_IFP_SEARCH_QUERY_SELECT);

			getColumnDetails(gtnItemGroupSearchQueryConfig);
			gtnItemGroupSearchQueryConfig
					.setWhereClauseList(Arrays.asList(GtnWsItemGrpQueryContants.GTN_IFP_SEARCH_QUERY_WHERE));
			searchQueryConfigMap.put("itemGrpSearchQuery", gtnItemGroupSearchQueryConfig);

			GtnWsSearchQueryConfig gtnWebServiceAuditSearchQueryConfig = new GtnWsSearchQueryConfig();
			getColumnDetails(gtnWebServiceAuditSearchQueryConfig);

			gtnWebServiceAuditSearchQueryConfig.setCountQuery(GtnWsItemGrpQueryContants.GTN_IFP_AUDIT_SEARCH_QUERY);
			gtnWebServiceAuditSearchQueryConfig.setSearchQuery(gtnWebServiceAuditSearchQueryConfig.getCountQuery());
			gtnWebServiceAuditSearchQueryConfig
					.setCountQuerySelectClause(GtnWsItemGrpQueryContants.GTN_IFP_AUDIT_SEARCH_QUERY_SELECT);

			searchQueryConfigMap.put("itemGrpAuditSearchQuery", gtnWebServiceAuditSearchQueryConfig);

			GtnWsSearchQueryConfig gtnWsAddTabAviableTableSearchQueryConfig = new GtnWsSearchQueryConfig();

			gtnWsAddTabAviableTableSearchQueryConfig
					.setCountQuery(GtnWsItemGrpQueryContants.GTN_IFP_AVAILABLE_SEARCH_QUERY);
			gtnWsAddTabAviableTableSearchQueryConfig
					.setSearchQuery(gtnWsAddTabAviableTableSearchQueryConfig.getCountQuery());
			gtnWsAddTabAviableTableSearchQueryConfig
					.setCountQuerySelectClause(GtnWsItemGrpQueryContants.GTN_IFP_AVAILABLE_SEARCH_QUERY_SELECT);

			Map<String, GtnWsColumnDetailsConfig> itemGroupFieldDetailsMapAddTab = new HashMap<>();
			itemGroupFieldDetailsMapAddTab.put("itemId", configProvider.getColumnStringConfig("ITEM_ID", "IM"));
			itemGroupFieldDetailsMapAddTab.put("itemNo", configProvider.getColumnStringConfig("ITEM_NO", "IM"));
			itemGroupFieldDetailsMapAddTab.put("iGrpInformationTabCustomerNo",
					configProvider.getColumnStringConfig("ITEM_NO", "IM"));
			itemGroupFieldDetailsMapAddTab.put("itemCode", configProvider.getColumnStringConfig("ITEM_CODE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("itemName", configProvider.getColumnStringConfig("ITEM_NAME", "IM"));
			itemGroupFieldDetailsMapAddTab.put("itemDesc", configProvider.getColumnStringConfig("ITEM_DESC", "IM"));
			itemGroupFieldDetailsMapAddTab.put("iGrpInformationIGrpDesc",
					configProvider.getColumnStringConfig("ITEM_DESC", "IM"));
			itemGroupFieldDetailsMapAddTab.put("itemStartDate",
					configProvider.getColumnDateConfig("ITEM_START_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("itemEndDate",
					configProvider.getColumnDateConfig("ITEM_END_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("itemStatus", configProvider.getColumnHelperConfig("ITEM_STATUS", "IM"));
			itemGroupFieldDetailsMapAddTab.put("therapeuticClass",
					configProvider.getColumnHelperConfig("THERAPEUTIC_CLASS", "IM"));
			itemGroupFieldDetailsMapAddTab.put("iGrpInformationTabTherapeuticClass",
					configProvider.getColumnIntegerConfig("THERAPEUTIC_CLASS", "IM"));
			itemGroupFieldDetailsMapAddTab.put("brand", configProvider.getColumnStringConfig("BRAND_NAME", "BM"));
			itemGroupFieldDetailsMapAddTab.put("iGrpInformationTabBrand",
					configProvider.getColumnIntegerConfig("BRAND_MASTER_SID", "BM"));
			itemGroupFieldDetailsMapAddTab.put("form", configProvider.getColumnHelperConfig("FORM", "IM"));
			itemGroupFieldDetailsMapAddTab.put("iGrpInformationTabForm",
					configProvider.getColumnIntegerConfig("FORM", "IM"));
			itemGroupFieldDetailsMapAddTab.put("strength", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "STRENGTH", "Item_Strength"));
			itemGroupFieldDetailsMapAddTab.put("iGrpInformationTabStrength", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "STRENGTH", "Item_Strength"));
			itemGroupFieldDetailsMapAddTab.put("packageSizeCode",
					configProvider.getColumnStringConfig("PACKAGE_SIZE_CODE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("packageSizeIntroDate",
					configProvider.getColumnDateConfig("PACKAGE_SIZE_INTRO_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("upps", configProvider.getColumnBigDecimalConfig("UPPS", "IM"));
			itemGroupFieldDetailsMapAddTab.put("manufacturerId",
					configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.MANUFACTURER_ID, "IM"));
			itemGroupFieldDetailsMapAddTab.put("manufacturerNo", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.MANUFACTURER_ID, "IM", "MANUFACTURER_NO"));
			itemGroupFieldDetailsMapAddTab.put("manufacturerName", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.MANUFACTURER_ID, "IM", "MANUFACTURER_NAME"));
			itemGroupFieldDetailsMapAddTab.put("labelerCode",
					configProvider.getColumnStringConfig("LABELER_CODE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("organizationKey",
					configProvider.getColumnStringConfig("ORGANIZATION_KEY", "IM"));
			itemGroupFieldDetailsMapAddTab.put("acquisitionDate",
					configProvider.getColumnDateConfig("ACQUISITION_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("authorizedGeneric",
					configProvider.getColumnStringConfig("AUTHORIZED_GENERIC", "IM"));
			itemGroupFieldDetailsMapAddTab.put("authorizedGenericStartDate",
					configProvider.getColumnDateConfig("AUTHORIZED_GENERIC_START_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("authorizedGenericEndDate",
					configProvider.getColumnDateConfig("AUTHORIZED_GENERIC_END_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("firstSaleDate",
					configProvider.getColumnDateConfig("FIRST_SALE_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("itemTypeIndicator",
					configProvider.getColumnHelperConfig("ITEM_TYPE_INDICATION", "IM"));
			itemGroupFieldDetailsMapAddTab.put("itemClass", configProvider.getColumnHelperConfig("ITEM_CLASS", "IM"));
			itemGroupFieldDetailsMapAddTab.put("itemType", configProvider.getColumnHelperConfig("ITEM_TYPE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("iGrpInformationTabCustomerType",
					configProvider.getColumnIntegerConfig("ITEM_TYPE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("marketTerminationDate",
					configProvider.getColumnDateConfig("MARKET_TERMINATION_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("newFormulationIndicator",
					configProvider.getColumnStringConfig("NEW_FORMULATION_INDICATOR", "IM"));
			itemGroupFieldDetailsMapAddTab.put("newFormulation",
					configProvider.getColumnStringConfig("NEW_FORMULATION", "IM"));
			itemGroupFieldDetailsMapAddTab.put("newFormulationStartDate",
					configProvider.getColumnDateConfig("NEW_FORMULATION_START_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("newFormulationEndDate",
					configProvider.getColumnDateConfig("NEW_FORMULATION_END_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("pediatricExclusiveIndicator",
					configProvider.getColumnStringConfig("PEDIATRIC_EXCLUSIVE_INDICATOR", "IM"));
			itemGroupFieldDetailsMapAddTab.put("pediatricExclusiveStartDate",
					configProvider.getColumnDateConfig("PEDIATRIC_EXCLUSIVE_START_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("pediatricExclusiveEndDate",
					configProvider.getColumnDateConfig("PEDIATRIC_EXCLUSIVE_END_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("clottingFactorIndicator",
					configProvider.getColumnStringConfig("CLOTTING_FACTOR_INDICATOR", "IM"));
			itemGroupFieldDetailsMapAddTab.put("clottingFactorStartDate",
					configProvider.getColumnDateConfig("CLOTTING_FACTOR_START_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("clottingFactorEndDate",
					configProvider.getColumnDateConfig("CLOTTING_FACTOR_END_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("primaryUom", configProvider.getColumnHelperConfig("PRIMARY_UOM", "IM"));
			itemGroupFieldDetailsMapAddTab.put("secondaryUom",
					configProvider.getColumnHelperConfig("SECONDARY_UOM", "IM"));
			itemGroupFieldDetailsMapAddTab.put("shelfLife", configProvider.getColumnStringConfig("SHELF_LIFE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("shelfLifeType",
					configProvider.getColumnHelperConfig("SHELF_LIFE_TYPE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("dualPricingIndicator",
					configProvider.getColumnStringConfig("DUAL_PRICING_INDICATOR", "IM"));
			itemGroupFieldDetailsMapAddTab.put("itemFamilyId",
					configProvider.getColumnStringConfig("ITEM_FAMILY_ID", "IM"));
			itemGroupFieldDetailsMapAddTab.put("udc1", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC1", "C_UDC1"));
			itemGroupFieldDetailsMapAddTab.put("udc2", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC2", "C_UDC2"));
			itemGroupFieldDetailsMapAddTab.put("udc3", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC3", "C_UDC3"));
			itemGroupFieldDetailsMapAddTab.put("udc4", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC4", "C_UDC4"));
			itemGroupFieldDetailsMapAddTab.put("udc5", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC5", "C_UDC5"));
			itemGroupFieldDetailsMapAddTab.put("udc6", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC6", "C_UDC6"));
			itemGroupFieldDetailsMapAddTab.put("acquiredAmp",
					configProvider.getColumnBigDecimalConfig("ACQUIRED_AMP", "IM"));
			itemGroupFieldDetailsMapAddTab.put("acquiredBamp",
					configProvider.getColumnBigDecimalConfig("ACQUIRED_BAMP", "IM"));
			itemGroupFieldDetailsMapAddTab.put("obraBamp", configProvider.getColumnBigDecimalConfig("OBRA_BAMP", "IM"));
			itemGroupFieldDetailsMapAddTab.put("dra", configProvider.getColumnBigDecimalConfig("DRA", "IM"));
			itemGroupFieldDetailsMapAddTab.put("dosesPerUnit",
					configProvider.getColumnStringConfig("DOSES_PER_UNIT", "IM"));
			itemGroupFieldDetailsMapAddTab.put("discontinuationDate",
					configProvider.getColumnDateConfig("DISCONTINUATION_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("lastLotExpirationDate",
					configProvider.getColumnDateConfig("LAST_LOT_EXPIRATION_DATE", "IM"));
			itemGroupFieldDetailsMapAddTab.put("ndc9", configProvider.getColumnStringConfig("NDC9", "IM"));
			itemGroupFieldDetailsMapAddTab.put("ndc8", configProvider.getColumnStringConfig("NDC8", "IM"));
			itemGroupFieldDetailsMapAddTab.put("displayBrand",
					configProvider.getColumnStringConfig("DISPLAY_BRAND", "BM"));
			itemGroupFieldDetailsMapAddTab.put("innovatorCode",
					configProvider.getColumnStringConfig("DISPLAY_BRAND", "BM", "INNOVATOR_CODE"));
			itemGroupFieldDetailsMapAddTab.put("baselineAmp",
					configProvider.getColumnBigDecimalConfig("BASELINE_AMP", "IM"));
			itemGroupFieldDetailsMapAddTab.put("baseYearCpi",
					configProvider.getColumnBigDecimalConfig("BASE_CPI", "IM"));

			GtnWsColumnDetailsConfig gtnWebServiceColumnDetailsConfig = new GtnWsColumnDetailsConfig();
			gtnWebServiceColumnDetailsConfig.setDbColumnName("ITEM_MASTER_SID");
			gtnWebServiceColumnDetailsConfig.setDataType(GtnFrameworkWebserviceConstant.INTEGER);
			gtnWebServiceColumnDetailsConfig.setTableAlias("IM");
			itemGroupFieldDetailsMapAddTab.put("itemMasterSid",
					configProvider.getColumnIntegerConfig("ITEM_MASTER_SID", "IM"));

			gtnWsAddTabAviableTableSearchQueryConfig.setFieldToColumnDetailsMap(itemGroupFieldDetailsMapAddTab);
			List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
			orderByClauseList.add(new GtnWebServiceOrderByCriteria("IM.ITEM_ID", "ASC"));
			gtnWsAddTabAviableTableSearchQueryConfig.setOrderByClause(orderByClauseList);
			gtnWsAddTabAviableTableSearchQueryConfig
					.setWhereClauseList(Arrays.asList(GtnWsItemGrpQueryContants.GTN_IFP_AVAILABLE_SEARCH_QUERY_WHERE));
			searchQueryConfigMap.put("cGrpAddTabSearchQuery", gtnWsAddTabAviableTableSearchQueryConfig);

			GtnWsSearchQueryConfig gtnWsAddTabselectedTableSearchQueryConfig = new GtnWsSearchQueryConfig(
					gtnWsAddTabAviableTableSearchQueryConfig);
			Map<String, GtnWsColumnDetailsConfig> parentFieldToColumnDetailsMapAddTab = new HashMap<>(
					itemGroupFieldDetailsMapAddTab);
			gtnWsAddTabselectedTableSearchQueryConfig
					.setSearchQuery(GtnWsItemGrpQueryContants.GTN_IFP_SELECTED_SEARCH_QUERY);

			gtnWsAddTabselectedTableSearchQueryConfig.setFieldToColumnDetailsMap(parentFieldToColumnDetailsMapAddTab);
			searchQueryConfigMap.put("itemGrpAddTabSelectedSearchQuery", gtnWsAddTabselectedTableSearchQueryConfig);
			gtnWsAddTabselectedTableSearchQueryConfig
					.setWhereClauseList(Arrays.asList(GtnWsItemGrpQueryContants.GTN_IFP_SELECTED_SEARCH_QUERY_WHERE));

		} catch (Exception ex) {
			logger.error("Error while cloning GtnWsSearchQueryConfig", ex);
		}
		return searchQueryConfigMap;
	}

	public void getColumnDetails(GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put("itemGroupSid", configProvider.getColumnIntegerConfig("ITEM_GROUP_SID", "IG"));
		fieldToColumnDetailsMap.put("itemGroupName", configProvider.getColumnStringConfig("ITEM_GROUP_NAME", "IG"));
		fieldToColumnDetailsMap.put("itemGroupDesc",
				configProvider.getColumnStringConfig("ITEM_GROUP_DESCRIPTION", "IG"));
		fieldToColumnDetailsMap.put("itemGroupNo", configProvider.getColumnStringConfig("ITEM_GROUP_NO", "IG"));
		fieldToColumnDetailsMap.put("versionNo", configProvider.getColumnIntegerConfig("VERSION_NO", "IG"));
		fieldToColumnDetailsMap.put("createdDate", configProvider.getColumnDateConfig("CREATED_DATE", "IG"));
		fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "IG"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnUserConfig("CREATED_BY", "IG"));
		fieldToColumnDetailsMap.put("iGrpInfoCompany",
				configProvider.getColumnIntegerConfig("COMPANY_MASTER_SID", "IG"));
		fieldToColumnDetailsMap.put("companyName", configProvider.getColumnStringConfig("COMPANY_NAME", "IM"));

		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("IG.ITEM_GROUP_NAME", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);
	}

}
