package com.stpl.gtn.gtn2o.ws.module.workflowinbox.config;

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
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.constants.GtnWsWorkflowQueryContants;

public class GtnWebServiceArpWorkflowSearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (searchQueryConfigMap == null) {
			searchQueryConfigMap = new HashMap<>();
			loadArpSearchQueryConfig();
		}
		return searchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.searchQueryConfigMap = searchQueryConfigMap;
	}

	private Map<String, GtnWsSearchQueryConfig> loadArpSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnARPWorkFlowSearchQueryConfig = new GtnWsSearchQueryConfig();
		gtnARPWorkFlowSearchQueryConfig.setCountQuerySelectClause(GtnWsWorkflowQueryContants.GTN_WF_FORECASTING_COUNT);
		gtnARPWorkFlowSearchQueryConfig.setCountQuery(GtnWsWorkflowQueryContants.GTN_WF_ARP_COUNT_QUERY);
		gtnARPWorkFlowSearchQueryConfig.setSearchQuery(GtnWsWorkflowQueryContants.GTN_WF_ARP_SEARCH_QUERY);
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();

		GtnWebServiceReturnWorkflowSearchConfig.addWorkflowSearchQueryConfig(fieldToColumnDetailsMap);
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWebServiceWorkflowSearchConfig.addWfDateColumnsInMap(configProvider, fieldToColumnDetailsMap);
		gtnARPWorkFlowSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("WM.WORKFLOW_ID", "ASC"));
		gtnARPWorkFlowSearchQueryConfig.setOrderByClause(orderByClauseList);

		gtnARPWorkFlowSearchQueryConfig
				.setWhereClauseList(Arrays.asList(GtnWsWorkflowQueryContants.GTN_WF_ARP_SEARCH_QUERY_WHERE_CLAUSE));

		searchQueryConfigMap.put("workflowSearchQuery", gtnARPWorkFlowSearchQueryConfig);

		return searchQueryConfigMap;
	}

}
