package com.stpl.gtn.gtn2o.ws.module.itemaster.config;

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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.module.itemaster.constants.GtnWsItemMasterQueryContants;

public class GtnWsItemMasterConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> itemMasterSearchQueryConfigMap = null;

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (itemMasterSearchQueryConfigMap == null) {
			itemMasterSearchQueryConfigMap = new HashMap<>();

			loadSearchQueryConfig();
		}
		return itemMasterSearchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.itemMasterSearchQueryConfigMap = searchQueryConfigMap;
	}

	public Map<String, GtnWsSearchQueryConfig> loadSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();

		gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsItemMasterQueryContants.GTN_ITEM_MASTER_SEARCH_QUERY);
		gtnWebServiceSearchQueryConfig.setSearchQuery(gtnWebServiceSearchQueryConfig.getCountQuery());

		GtnWsSearchQueryConfig gtnWebServiceSearchNewFormulationConfig = new GtnWsSearchQueryConfig();

		gtnWebServiceSearchNewFormulationConfig
				.setCountQuery(GtnWsItemMasterQueryContants.GTN_ITEM_MASTER_SEARCH_QUERY_FORMULATION);
		gtnWebServiceSearchNewFormulationConfig.setSearchQuery(gtnWebServiceSearchNewFormulationConfig.getCountQuery());

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		Map<String, GtnWsColumnDetailsConfig> itemMasterColumnDetailsMap = new HashMap<>();
		loadValues(GtnFrameworkCommonStringConstants.STRING_EMPTY, itemMasterColumnDetailsMap, configProvider);
		loadValues("nfPopup", itemMasterColumnDetailsMap, configProvider);

		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(itemMasterColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("IM.ITEM_ID", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);
		gtnWebServiceSearchQueryConfig
				.setWhereClauseList(Arrays.asList(GtnWsItemMasterQueryContants.GTN_ITEM_MASTER_SEARCH_WHERE_CLAUSE));

		gtnWebServiceSearchQueryConfig.setCountQuerySelectClause("Select  count(distinct IM.ITEM_MASTER_SID) ");

		itemMasterSearchQueryConfigMap.put("SearchQuery", gtnWebServiceSearchQueryConfig);

		GtnWsSearchQueryConfig gtnWebServiceSearchImQualifierConfig = new GtnWsSearchQueryConfig();

		gtnWebServiceSearchImQualifierConfig
				.setCountQuery(GtnWsItemMasterQueryContants.GTN_ITEM_MASTER_QUALIFIER_SEARCH_QUERY);
		gtnWebServiceSearchImQualifierConfig.setSearchQuery(gtnWebServiceSearchImQualifierConfig.getCountQuery());

		Map<String, GtnWsColumnDetailsConfig> itemQualifierColumnDetailsMap = new HashMap<>();
		itemQualifierColumnDetailsMap.put("identifierQualifierSid",
				configProvider.getColumnIntegerConfig(GtnFrameworkCommonConstants.ITEM_QUALIFIER_SID, "IQ"));
		itemQualifierColumnDetailsMap.put("identifierCodeQualifier",
				configProvider.getColumnStringConfig("ITEM_QUALIFIER_VALUE", "IQ"));
		itemQualifierColumnDetailsMap.put("identifierCodeQualifierName",
				configProvider.getColumnStringConfig("ITEM_QUALIFIER_NAME", "IQ"));
		itemQualifierColumnDetailsMap.put("effective", configProvider.getColumnStringConfig("EFFECTIVE_DATES", "IQ"));
		itemQualifierColumnDetailsMap.put("entityCode",
				configProvider.getColumnStringConfig("SPECIFIC_ENTITY_CODE", "IQ"));
		itemQualifierColumnDetailsMap.put("notes", configProvider.getColumnStringConfig("NOTES", "IQ"));
		itemQualifierColumnDetailsMap.put("modifierBy", configProvider.getColumnUserConfig("MODIFIED_BY", "IQ"));
		itemQualifierColumnDetailsMap.put("modifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "IQ"));
		itemQualifierColumnDetailsMap.put("createdBy", configProvider.getColumnUserConfig("CREATED_BY", "IQ"));
		itemQualifierColumnDetailsMap.put("createdDate", configProvider.getColumnDateConfig("CREATED_DATE", "IQ"));
		itemQualifierColumnDetailsMap.put(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS,
				configProvider.getColumnBooleanConfig(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS_COLUMN, "IQ"));

		gtnWebServiceSearchImQualifierConfig.setFieldToColumnDetailsMap(itemQualifierColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> iMorderByClauseList = new ArrayList<>();
		iMorderByClauseList.add(new GtnWebServiceOrderByCriteria("IQ.ITEM_QUALIFIER_VALUE", "ASC"));
		gtnWebServiceSearchImQualifierConfig.setOrderByClause(iMorderByClauseList);
		gtnWebServiceSearchImQualifierConfig.setWhereClauseList(
				Arrays.asList(GtnWsItemMasterQueryContants.GTN_ITEM_MASTER_QUALIFIER_SEARCH_QUERY_WHERE));

		itemMasterSearchQueryConfigMap.put("imQualifierSearchQuery", gtnWebServiceSearchImQualifierConfig);

		GtnWsSearchQueryConfig gtnWebServiceSearchPricingQualifierConfig = new GtnWsSearchQueryConfig();

		gtnWebServiceSearchPricingQualifierConfig
				.setCountQuery(GtnWsItemMasterQueryContants.GTN_ITEM_MASTER_PRICING_SEARCH_QUERY);
		gtnWebServiceSearchPricingQualifierConfig
				.setSearchQuery(gtnWebServiceSearchPricingQualifierConfig.getCountQuery());

		Map<String, GtnWsColumnDetailsConfig> itemPricingColumnDetailsMap = new HashMap<>();
		itemPricingColumnDetailsMap.put("pricingQualifierSid",
				configProvider.getColumnIntegerConfig("ITEM_PRICING_QUALIFIER_SID", "IQ"));
		itemPricingColumnDetailsMap.put("pricingCodeQualifier",
				configProvider.getColumnStringConfig("PRICING_QUALIFIER", "IQ"));
		itemPricingColumnDetailsMap.put("pricingCodeQualifierName",
				configProvider.getColumnStringConfig("ITEM_PRICING_QUALIFIER_NAME", "IQ"));
		itemPricingColumnDetailsMap.put("effective", configProvider.getColumnStringConfig("EFFECTIVE_DATES", "IQ"));
		itemPricingColumnDetailsMap.put("entityCode",
				configProvider.getColumnStringConfig("SPECIFIC_ENTITY_CODE", "IQ"));
		itemPricingColumnDetailsMap.put("notes", configProvider.getColumnStringConfig("NOTES", "IQ"));
		itemPricingColumnDetailsMap.put("modifierBy", configProvider.getColumnUserConfig("MODIFIED_BY", "IQ"));
		itemPricingColumnDetailsMap.put("modifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "IQ"));
		itemPricingColumnDetailsMap.put("createdBy", configProvider.getColumnUserConfig("CREATED_BY", "IQ"));
		itemPricingColumnDetailsMap.put("createdDate", configProvider.getColumnDateConfig("CREATED_DATE", "IQ"));
		itemPricingColumnDetailsMap.put(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS,
				configProvider.getColumnBooleanConfig(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS_COLUMN, "IQ"));

		gtnWebServiceSearchPricingQualifierConfig.setFieldToColumnDetailsMap(itemPricingColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> pricingOrderByClauseList = new ArrayList<>();
		pricingOrderByClauseList.add(new GtnWebServiceOrderByCriteria("IQ.PRICING_QUALIFIER", "ASC"));
		gtnWebServiceSearchPricingQualifierConfig.setOrderByClause(pricingOrderByClauseList);
		gtnWebServiceSearchPricingQualifierConfig.setWhereClauseList(
				Arrays.asList(GtnWsItemMasterQueryContants.GTN_ITEM_MASTER_PRICING_SEARCH_QUERY_WHERE));

		itemMasterSearchQueryConfigMap.put("imPricnigQualifierSearchQuery", gtnWebServiceSearchPricingQualifierConfig);

		gtnWebServiceSearchNewFormulationConfig.setFieldToColumnDetailsMap(itemMasterColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> pricingPopUpOrderByClauseList = new ArrayList<>();
		pricingPopUpOrderByClauseList.add(new GtnWebServiceOrderByCriteria("IM.ITEM_ID", "ASC"));
		gtnWebServiceSearchNewFormulationConfig.setOrderByClause(pricingPopUpOrderByClauseList);
		gtnWebServiceSearchNewFormulationConfig.setWhereClauseList(
				Arrays.asList(GtnWsItemMasterQueryContants.GTN_ITEM_MASTER_NEW_FORMULATION_SEARCH_QUERY_WHERE));

		itemMasterSearchQueryConfigMap.put("imNewFormulationSearchQuery", gtnWebServiceSearchNewFormulationConfig);

		return itemMasterSearchQueryConfigMap;
	}

	public void loadValues(String popUpValue, Map<String, GtnWsColumnDetailsConfig> itemMasterColumnDetailsMap,
			GtnWsSearchQueryConfigProvider configProvider) {
		itemMasterColumnDetailsMap.put(popUpValue + "itemSystemID",
				configProvider.getColumnIntegerConfig("ITEM_MASTER_SID", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchItemId",
				configProvider.getColumnStringConfig("ITEM_ID", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchItemNo",
				configProvider.getColumnStringConfig("ITEM_NO", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchItemName",
				configProvider.getColumnStringConfig("ITEM_NAME", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchItemDesc",
				configProvider.getColumnStringConfig("ITEM_DESC", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchItemStatus",
				configProvider.getColumnHelperConfig("ITEM_STATUS", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchItemType",
				configProvider.getColumnHelperConfig("ITEM_TYPE", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS,
				configProvider.getColumnBooleanConfig(GtnFrameworkWebserviceConstant.RECORD_LOCK_STATUS_COLUMN, "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "therapeuticClass",
				configProvider.getColumnHelperConfig("THERAPEUTIC_CLASS", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "brand", configProvider.getColumnStringConfig("BRAND_NAME", "BM"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchNdc9",
				configProvider.getColumnStringConfig("ITEM_ID", "IM1"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchNdc8",
				configProvider.getColumnStringConfig("NDC8", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "form", configProvider.getColumnHelperConfig("FORM", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "strength", configProvider.getColumnHelperConfig("STRENGTH", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchBatchId",
				configProvider.getColumnStringConfig("BATCH_ID", "IM"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchQualifierName",
				configProvider.getColumnStringConfig("ITEM_QUALIFIER_SID", "IQ"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchItemIdentifier",
				configProvider.getColumnStringConfig("ITEM_IDENTIFIER_VALUE", "IQ"));
		itemMasterColumnDetailsMap.put(popUpValue + "IMasterSearchOrganizationKey",
				configProvider.getColumnStringConfig("COMPANY_ID", "CM"));
	}

}
