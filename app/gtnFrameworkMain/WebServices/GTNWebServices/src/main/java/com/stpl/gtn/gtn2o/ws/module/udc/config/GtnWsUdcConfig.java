package com.stpl.gtn.gtn2o.ws.module.udc.config;

import java.util.ArrayList;
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

		System.out.println("************");
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
	}

	private void loadValues(Map<String, GtnWsColumnDetailsConfig> udcColumnDetailsMap,
			GtnWsSearchQueryConfigProvider configProvider) {
		udcColumnDetailsMap.put("description",
				configProvider.getColumnStringConfig("DESCRIPTION", "HT"));
		udcColumnDetailsMap.put("udcCategory", configProvider.getColumnStringConfig("LIST_NAME", "HT"));
	}

}
