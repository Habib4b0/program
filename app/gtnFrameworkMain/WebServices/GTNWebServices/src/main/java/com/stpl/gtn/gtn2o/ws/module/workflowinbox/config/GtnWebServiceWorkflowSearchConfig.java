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

public class GtnWebServiceWorkflowSearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWebServiceWorkflowSearchConfig.class);

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (searchQueryConfigMap == null) {
			searchQueryConfigMap = new HashMap<>();
			loadContractSearchQueryConfig();
		}
		return searchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.searchQueryConfigMap = searchQueryConfigMap;
	}

	public Map<String, GtnWsSearchQueryConfig> loadContractSearchQueryConfig() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		try {
			gtnWebServiceSearchQueryConfig.setCountQuerySelectClause(GtnWsWorkflowQueryContants.GTN_WF_CONTRACT_COUNT);
			gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsWorkflowQueryContants.GTN_WF_CONTRACT_COUNT_QUERY);
			gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsWorkflowQueryContants.GTN_WF_CONTRACT_SEARCH_QUERY);

			Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
			fieldToColumnDetailsMap.put("workflowId", configProvider.getColumnStringConfig("WORKFLOW_ID", "WM"));
			fieldToColumnDetailsMap.put("workflowName", configProvider.getColumnStringConfig("CONTRACT_NAME", "CM"));
			fieldToColumnDetailsMap.put("workflowDesc",
					configProvider.getColumnStringConfig("WORKFLOW_DESCRPTION", "WM"));			
			addWorkflowStatus(fieldToColumnDetailsMap);

			fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnUserConfig("CREATED_BY", "WM"));
			fieldToColumnDetailsMap.put("creationDate", configProvider.getColumnDateConfig("CREATED_DATE", "WM"));
			addWfDateColumnsInMap(configProvider, fieldToColumnDetailsMap);
			fieldToColumnDetailsMap.put("approvedBy", configProvider.getColumnUserConfig("APPROVED_BY", "WM"));

			addremainingColumns(fieldToColumnDetailsMap);

			gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

			List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
			orderByClauseList.add(new GtnWebServiceOrderByCriteria("WM.WORKFLOW_ID", "ASC"));
			gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);

			gtnWebServiceSearchQueryConfig.setWhereClauseList(
					Arrays.asList(GtnWsWorkflowQueryContants.GTN_WF_CONTRACT_SEARCH_QUERY_WHERE_CLAUSE));

			searchQueryConfigMap.put("workflowSearchQuery", gtnWebServiceSearchQueryConfig);

		} catch (Exception ex) {
			logger.error("error while cloning the GtnWsSearchQueryConfig");

		}
		return searchQueryConfigMap;
	}
	
	private static void addWorkflowStatus(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMapr) {
		GtnWsColumnDetailsConfig userColumnConfig = new GtnWsColumnDetailsConfig();
		userColumnConfig.setDbColumnName("WORKFLOW_STATUS_ID");
		userColumnConfig.setDataType(GtnFrameworkWebserviceConstant.HELPER);
		userColumnConfig.setTableAlias("WM");
		userColumnConfig.setHelperTableAliasName("WM");
		userColumnConfig.setHelperTableColumnName("WORKFLOW_STATUS_ID");
		fieldToColumnDetailsMapr.put("status", userColumnConfig);

	}

	public static void addWfDateColumnsInMap(GtnWsSearchQueryConfigProvider configProvider,
			Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		fieldToColumnDetailsMap.put( "createdFrom", configProvider.getColumnDateConfig("CREATED_DATE >", "WM"));
		fieldToColumnDetailsMap.put("approvedFrom", configProvider.getColumnDateConfig("APPROVED_DATE >", "WM"));
		fieldToColumnDetailsMap.put("approvedTo", configProvider.getColumnDateConfig("APPROVED_DATE <", "WM"));
		fieldToColumnDetailsMap.put("createdTo", configProvider.getColumnDateConfig("CREATED_DATE <", "WM"));

	}

	private void addremainingColumns(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "WM"));
		fieldToColumnDetailsMap.put("workflowMasterSystemId",
				configProvider.getColumnIntegerConfig("WORKFLOW_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("masterSid", configProvider.getColumnIntegerConfig("CONTRACT_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("contractStructure",
				configProvider.getColumnStringConfig("CONTRACT_STRUCTURE", "WM"));
		fieldToColumnDetailsMap.put("noOfApprovals", configProvider.getColumnIntegerConfig("NO_OF_APPROVAL", "WM"));
		fieldToColumnDetailsMap.put("approvalLevel", configProvider.getColumnIntegerConfig("APPROVAL_LEVEL", "WM"));
		fieldToColumnDetailsMap.put("approvedDate", configProvider.getColumnDateConfig("APPROVED_DATE", "WM"));
		fieldToColumnDetailsMap.put("workflowSid", configProvider.getColumnIntegerConfig("WORKFLOW_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("createdById", configProvider.getColumnIntegerConfig("CREATED_BY", "WM"));
		fieldToColumnDetailsMap.put("projectionMasterSid",
				configProvider.getColumnIntegerConfig("PROJECTION_MASTER_SID", "WM"));
	}

	}
