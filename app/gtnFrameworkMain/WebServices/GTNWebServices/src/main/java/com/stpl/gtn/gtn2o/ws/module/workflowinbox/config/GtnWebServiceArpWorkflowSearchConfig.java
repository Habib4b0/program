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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.constants.GtnWsWorkflowQueryContants;

public class GtnWebServiceArpWorkflowSearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;
        private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWebServiceArpWorkflowSearchConfig.class);
	
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
		gtnARPWorkFlowSearchQueryConfig.setCountQuerySelectClause(GtnWsWorkflowQueryContants.GTN_WF_ARP_COUNT);
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
                detailsSearchFieldArpWorkflowInbox(fieldToColumnDetailsMap);
		return searchQueryConfigMap;
	}
        
        private void detailsSearchFieldArpWorkflowInbox(Map<String, GtnWsColumnDetailsConfig> columnDetailsConfig) {
            GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
            columnDetailsConfig.put("contractId",configProvider.getColumnStringConfig("CONTRACT_ID", "CNM"));
            columnDetailsConfig.put("contractNo",configProvider.getColumnStringConfig("CONTRACT_NO", "CNM"));
            columnDetailsConfig.put("contractName",configProvider.getColumnStringConfig("CONTRACT_NAME", "CNM"));
            columnDetailsConfig.put("contractType",configProvider.getColumnStringConfig("CONTRACT_TYPE", "CNM"));
            
            columnDetailsConfig.put("companyID",configProvider.getColumnStringConfig("COMPANY_ID", "GL_COMp"));
            columnDetailsConfig.put("companyNo",configProvider.getColumnStringConfig("COMPANY_NO", "GL_COMp"));
            columnDetailsConfig.put("companyName",configProvider.getColumnStringConfig("COMPANY_NAME", "GL_COMp"));
            
            columnDetailsConfig.put("businessUnitId",configProvider.getColumnStringConfig("COMPANY_ID", "BU_COMp"));
            columnDetailsConfig.put("businessUnitNo",configProvider.getColumnStringConfig("COMPANY_NO", "BU_COMp"));
            columnDetailsConfig.put("businessUnitName",configProvider.getColumnStringConfig("COMPANY_NAME", "BU_COMp"));
            
            columnDetailsConfig.put("itemId",configProvider.getColumnStringConfig("ITEM_ID", "IM"));
            columnDetailsConfig.put("itemNo",configProvider.getColumnStringConfig("ITEM_NO", "IM"));
            columnDetailsConfig.put("itemName",configProvider.getColumnStringConfig("ITEM_NAME", "IM"));
            
            columnDetailsConfig.put("forecastdeductionLevel",configProvider.getColumnStringConfig("FIELD_NAME", "APS"));
            columnDetailsConfig.put("forecastdeductionValue",configProvider.getColumnStringConfig("FIELD_VALUES", "APS"));   
        }
}
