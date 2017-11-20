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
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.constants.GtnWsWorkflowQueryContants;

public class GtnWebServiceArmWorkflowSearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (searchQueryConfigMap == null) {
			searchQueryConfigMap = new HashMap<>();
			loadArmSearchQueryConfig();
		}
		return searchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.searchQueryConfigMap = searchQueryConfigMap;
	}

	private Map<String, GtnWsSearchQueryConfig> loadArmSearchQueryConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		gtnWebServiceSearchQueryConfig.setCountQuerySelectClause(GtnWsWorkflowQueryContants.GTN_WF_ARM_COUNT);
		gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsWorkflowQueryContants.GTN_WF_ARM_COUNT_QUERY);
		gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsWorkflowQueryContants.GTN_WF_ARM_SEARCH_QUERY);
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put("workflowId", configProvider.getColumnStringConfig("WORKFLOW_ID", "WM"));
		fieldToColumnDetailsMap.put("workflowName",
				configProvider.getColumnStringConfig("PROJECTION_DESCRIPTION", "PM"));
		fieldToColumnDetailsMap.put("workflowDesc",
				configProvider.getColumnStringConfig("PROJECTION_DESCRIPTION", "PM"));
		fieldToColumnDetailsMap.put("workflowStatusArm",
				configProvider.getColumnHelperConfig("WORKFLOW_STATUS_ID", "WM"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnUserConfig("CREATED_BY", "WM"));
		fieldToColumnDetailsMap.put("creationDate", configProvider.getColumnDateConfig("CREATED_DATE", "WM"));
		addremainingColumnsOne(fieldToColumnDetailsMap);
		addremainingColumnsTwo(fieldToColumnDetailsMap);
		GtnWebServiceWorkflowSearchConfig.addWfDateColumnsInMap(configProvider, fieldToColumnDetailsMap);
		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("WM.WORKFLOW_ID", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);

		gtnWebServiceSearchQueryConfig
				.setWhereClauseList(Arrays.asList(GtnWsWorkflowQueryContants.GTN_WF_ARM_SEARCH_QUERY_WHERE_CLAUSE));

		searchQueryConfigMap.put("workflowSearchQuery", gtnWebServiceSearchQueryConfig);

		return searchQueryConfigMap;
	}

	private static void addremainingColumnsOne(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("approvedBy", configProvider.getColumnUserConfig("APPROVED_BY", "WM"));
		fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "WM"));
		fieldToColumnDetailsMap.put("workflowMasterSystemId",
				configProvider.getColumnIntegerConfig("WORKFLOW_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("masterSid", configProvider.getColumnIntegerConfig("PROJECTION_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("contractStructure",
				configProvider.getColumnStringConfig("CONTRACT_STRUCTURE", "WM"));
		fieldToColumnDetailsMap.put("noOfApprovals", configProvider.getColumnIntegerConfig("NO_OF_APPROVAL", "WM"));
		fieldToColumnDetailsMap.put("approvalLevel", configProvider.getColumnIntegerConfig("APPROVAL_LEVEL", "WM"));
		fieldToColumnDetailsMap.put("approvedDate", configProvider.getColumnDateConfig("APPROVED_DATE", "WM"));
	}

	private static void addremainingColumnsTwo(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("companyARM", configProvider.getColumnStringConfig("COMPANY_NAME", "GL_COMp"));
		fieldToColumnDetailsMap.put("businessUnitARM", configProvider.getColumnStringConfig("COMPANY_NAME", "BU_COMp"));
		fieldToColumnDetailsMap.put("adjustmentType",
				configProvider.getColumnStringConfig("TRANSACTION_NAME", "ADJ_CONF"));
		fieldToColumnDetailsMap.put("adjustmentTypeName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "TRANX_NAME"));
		fieldToColumnDetailsMap.put("configurationType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "CONF_NAME"));
		fieldToColumnDetailsMap.put("workflowSid", configProvider.getColumnIntegerConfig("WORKFLOW_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("projectionMasterSid",
				configProvider.getColumnIntegerConfig("PROJECTION_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("createdById", configProvider.getColumnIntegerConfig("CREATED_BY", "WM"));
	}
}
