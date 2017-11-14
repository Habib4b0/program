package com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.config;

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
import com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.constants.GtnWsCfpQueryContants;

public class GtnWebServiceCompanyFamilyPlanAdditionSearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (searchQueryConfigMap == null) {
			searchQueryConfigMap = new HashMap<>();
			loadCompanyAdditionSearchQueryConfig();
		}
		return searchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.searchQueryConfigMap = searchQueryConfigMap;
	}

	private Map<String, GtnWsSearchQueryConfig> loadCompanyAdditionSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsCfpQueryContants.GTN_CFP_ADDITION_SEARCH_QUERY);
		gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsCfpQueryContants.GTN_CFP_ADDITION_SEARCH_QUERY);
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put("companyMasterSid",
				configProvider.getColumnIntegerConfig("COMPANY_MASTER_SID", "cm"));
		fieldToColumnDetailsMap.put("companyId", configProvider.getColumnStringConfig("COMPANY_ID", "cm"));
		fieldToColumnDetailsMap.put("companyNo", configProvider.getColumnStringConfig("COMPANY_NO", "cm"));
		fieldToColumnDetailsMap.put("companyName", configProvider.getColumnStringConfig("COMPANY_NAME", "cm"));
		fieldToColumnDetailsMap.put("companyType", configProvider.getColumnHelperConfig("COMPANY_TYPE", "cm"));
		fieldToColumnDetailsMap.put("companyStatus", configProvider.getColumnHelperConfig("COMPANY_STATUS", "cm"));
		fieldToColumnDetailsMap.put("companyCategory", configProvider.getColumnHelperConfig("COMPANY_CATEGORY", "CM"));
		fieldToColumnDetailsMap.put("companyGroup", configProvider.getColumnHelperConfig("COMPANY_GROUP", "CM"));
		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("CM.COMPANY_ID", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);

		gtnWebServiceSearchQueryConfig
				.setWhereClauseList(Arrays.asList(GtnWsCfpQueryContants.GTN_CFP_ADDITION_SEARCH_QUERY_WHERE_CLAUSE));

		searchQueryConfigMap.put("cfpCompanyAdditionSearchQuery", gtnWebServiceSearchQueryConfig);

		return searchQueryConfigMap;
	}

}
