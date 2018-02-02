package com.stpl.gtn.gtn2o.ws.module.udc.config;

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
import com.stpl.gtn.gtn2o.ws.module.udc.constants.GtnWsUdcQueryConstants;

public class GtnWsUdcConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> udcSearchQueryConfigMap = null;

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (udcSearchQueryConfigMap == null) {
			udcSearchQueryConfigMap = new HashMap<>();

			loadSearchQueryConfig();
		}
		return udcSearchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.udcSearchQueryConfigMap = searchQueryConfigMap;
	}

	private void loadSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWsSearchQueryConfig = new GtnWsSearchQueryConfig();

		gtnWsSearchQueryConfig.setCountQuery(GtnWsUdcQueryConstants.GTN_UDC_SEARCH_QUERY);
		gtnWsSearchQueryConfig.setSearchQuery(gtnWsSearchQueryConfig.getCountQuery());
		
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		Map<String, GtnWsColumnDetailsConfig> udcColumnDetailsMap = new HashMap<>();
		loadValues(udcColumnDetailsMap, configProvider);

		gtnWsSearchQueryConfig.setFieldToColumnDetailsMap(udcColumnDetailsMap);
		
		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("HT.DESCRIPTION" , "ASC"));
		gtnWsSearchQueryConfig.setOrderByClause(orderByClauseList);
		

		gtnWsSearchQueryConfig.setCountQuerySelectClause("Select count(distinct HT.HELPER_TABLE_SID)");

		udcSearchQueryConfigMap.put("SearchQuery", gtnWsSearchQueryConfig);
		
		GtnWsSearchQueryConfig gtnWsBrandSearchQueryConfig = new GtnWsSearchQueryConfig();

		gtnWsBrandSearchQueryConfig.setCountQuery(GtnWsUdcQueryConstants.GTN_UDC_BRANDSEARCH_QUERY);
		gtnWsBrandSearchQueryConfig.setSearchQuery(gtnWsBrandSearchQueryConfig.getCountQuery());
		
		loadBrandValues(udcColumnDetailsMap, configProvider);

		gtnWsBrandSearchQueryConfig.setFieldToColumnDetailsMap(udcColumnDetailsMap);
		
		List<GtnWebServiceOrderByCriteria> orderByClauseBrandList = new ArrayList<>();
		orderByClauseBrandList.add(new GtnWebServiceOrderByCriteria("BM.BRAND_ID", "ASC"));
		gtnWsBrandSearchQueryConfig.setOrderByClause(orderByClauseBrandList);
		gtnWsBrandSearchQueryConfig
				.setWhereClauseList(Arrays.asList(GtnWsUdcQueryConstants.GTN_UDC_BRANDSEARCH_WHERE_CLAUSE));

		gtnWsBrandSearchQueryConfig.setCountQuerySelectClause("Select count(distinct BM.BRAND_MASTER_SID)");

		udcSearchQueryConfigMap.put("BrandSearchQuery", gtnWsBrandSearchQueryConfig);
		
		GtnWsSearchQueryConfig gtnWsFileTypeSearchQueryConfig = new GtnWsSearchQueryConfig();

		gtnWsFileTypeSearchQueryConfig.setCountQuery(GtnWsUdcQueryConstants.GTN_UDC_FILETYPE_SEARCH_QUERY);
		gtnWsFileTypeSearchQueryConfig.setSearchQuery(gtnWsFileTypeSearchQueryConfig.getCountQuery());
		
		loadFileTypeValues(udcColumnDetailsMap, configProvider);

		gtnWsFileTypeSearchQueryConfig.setFieldToColumnDetailsMap(udcColumnDetailsMap);
		
		List<GtnWebServiceOrderByCriteria> orderByClauseListFileType = new ArrayList<>();
		orderByClauseListFileType.add(new GtnWebServiceOrderByCriteria("HT.DESCRIPTION" , "ASC"));
		gtnWsFileTypeSearchQueryConfig.setOrderByClause(orderByClauseListFileType);
		

		gtnWsFileTypeSearchQueryConfig.setCountQuerySelectClause("Select count(distinct HT.HELPER_TABLE_SID)");

		udcSearchQueryConfigMap.put("FileTypeSearchQuery", gtnWsFileTypeSearchQueryConfig);
	}

	private void loadFileTypeValues(Map<String, GtnWsColumnDetailsConfig> udcColumnDetailsMap,
			GtnWsSearchQueryConfigProvider configProvider) {
		udcColumnDetailsMap.put("description", configProvider.getColumnStringConfig("DESCRIPTION", "HT"));
		udcColumnDetailsMap.put("udcCategory", configProvider.getColumnStringConfig("LIST_NAME", "HT"));
		udcColumnDetailsMap.put("aliasName", configProvider.getColumnStringConfig("ALIAS_NAME", "HT"));
		udcColumnDetailsMap.put("systemId", configProvider.getColumnIntegerConfig("HELPER_TABLE_SID", "HT"));
		
	}

	private void loadValues(Map<String, GtnWsColumnDetailsConfig> udcColumnDetailsMap,
			GtnWsSearchQueryConfigProvider configProvider) {
		udcColumnDetailsMap.put("description", configProvider.getColumnStringConfig("DESCRIPTION", "HT"));
		udcColumnDetailsMap.put("udcCategory", configProvider.getColumnStringConfig("LIST_NAME", "HT"));
		udcColumnDetailsMap.put("systemId", configProvider.getColumnIntegerConfig("HELPER_TABLE_SID", "HT"));
	}

	private void loadBrandValues(Map<String, GtnWsColumnDetailsConfig> udcColumnDetailsMap,
			GtnWsSearchQueryConfigProvider configProvider) {
		udcColumnDetailsMap.put("brandId", configProvider.getColumnStringConfig("BRAND_ID", "BM"));
		udcColumnDetailsMap.put("brandName", configProvider.getColumnStringConfig("BRAND_NAME", "BM"));
		udcColumnDetailsMap.put("displayBrand", configProvider.getColumnStringConfig("DISPLAY_BRAND", "BM"));
		udcColumnDetailsMap.put("brandMasterSid", configProvider.getColumnIntegerConfig("BRAND_MASTER_SID", "BM"));
		udcColumnDetailsMap.put("category", configProvider.getColumnStringConfig("'BRAND'", ""));
	}

}
