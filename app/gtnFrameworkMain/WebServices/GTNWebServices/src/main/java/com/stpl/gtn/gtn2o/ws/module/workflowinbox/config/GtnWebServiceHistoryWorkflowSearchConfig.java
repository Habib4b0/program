package com.stpl.gtn.gtn2o.ws.module.workflowinbox.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.constants.GtnWsWorkflowQueryContants;

public class GtnWebServiceHistoryWorkflowSearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWebServiceHistoryWorkflowSearchConfig.class);

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (searchQueryConfigMap == null) {
			searchQueryConfigMap = new HashMap<>();
			loadHistoryWorkflowSearchQueryConfig();
		}
		return searchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.searchQueryConfigMap = searchQueryConfigMap;
	}

	public Map<String, GtnWsSearchQueryConfig> loadHistoryWorkflowSearchQueryConfig() {
		GtnWsSearchQueryConfig gtnHistorySearchQueryConfig = new GtnWsSearchQueryConfig();
		try {
			gtnHistorySearchQueryConfig.setCountQuerySelectClause(GtnWsWorkflowQueryContants.GTN_WF_HISTORY_COUNT);
			gtnHistorySearchQueryConfig.setCountQuery(GtnWsWorkflowQueryContants.GTN_WF_HISTORY_COUNT_QUERY);
			gtnHistorySearchQueryConfig.setSearchQuery(GtnWsWorkflowQueryContants.GTN_WF_HISTORY_SEARCH_QUERY);

			GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
			Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
			fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "HWM"));
			fieldToColumnDetailsMap.put("modifiedBy", configProvider.getColumnUserConfig("MODIFIED_BY", "HWM"));
			fieldToColumnDetailsMap.put("status", configProvider.getColumnStringConfig("DESCRIPTION", "HT_STATUS"));
			fieldToColumnDetailsMap.put("notes", configProvider.getColumnStringConfig("NOTES", "HWM"));
			fieldToColumnDetailsMap.put("attachmentLink", configProvider.getColumnStringConfig("FILE_NAME", "HWM"));
			fieldToColumnDetailsMap.put("workflowSid",
					configProvider.getColumnIntegerConfig("WORKFLOW_MASTER_SID", "HWM"));
			fieldToColumnDetailsMap.put("fileName", configProvider.getColumnStringConfig("FILE_NAME", "HWM"));

			gtnHistorySearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

			List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
			orderByClauseList.add(new GtnWebServiceOrderByCriteria("HWM.MODIFIED_DATE", "ASC"));
			gtnHistorySearchQueryConfig.setOrderByClause(orderByClauseList);

			searchQueryConfigMap.put("historySearchQuery", gtnHistorySearchQueryConfig);

		} catch (Exception ex) {
			logger.error("error while cloning the GtnWsSearchQueryConfig");

		}
		return searchQueryConfigMap;
	}

}
