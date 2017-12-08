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

public class GtnWebServiceReturnWorkflowSearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (searchQueryConfigMap == null) {
			searchQueryConfigMap = new HashMap<>();
			loadReturnSearchQueryConfig();
		}
		return searchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.searchQueryConfigMap = searchQueryConfigMap;
	}

	private Map<String, GtnWsSearchQueryConfig> loadReturnSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWorkFlowReturnsSearchQueryConfig = new GtnWsSearchQueryConfig();
		gtnWorkFlowReturnsSearchQueryConfig.setCountQuerySelectClause(GtnWsWorkflowQueryContants.GTN_WF_RETURNS_COUNT);
		gtnWorkFlowReturnsSearchQueryConfig.setCountQuery(GtnWsWorkflowQueryContants.GTN_WF_RETURNS_COUNT_QUERY);
		gtnWorkFlowReturnsSearchQueryConfig.setSearchQuery(GtnWsWorkflowQueryContants.GTN_WF_RETURNS_SEARCH_QUERY);
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();

		addWorkflowSearchQueryConfig(fieldToColumnDetailsMap);
		gtnWorkFlowReturnsSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("WM.WORKFLOW_ID", "ASC"));
		gtnWorkFlowReturnsSearchQueryConfig.setOrderByClause(orderByClauseList);

		gtnWorkFlowReturnsSearchQueryConfig
				.setWhereClauseList(Arrays.asList(GtnWsWorkflowQueryContants.GTN_WF_RETURNS_SEARCH_QUERY_WHERE_CLAUSE));

		searchQueryConfigMap.put("workflowSearchQuery", gtnWorkFlowReturnsSearchQueryConfig);

		return searchQueryConfigMap;
	}

	public static void addWorkflowSearchQueryConfig(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("workflowId", configProvider.getColumnStringConfig("WORKFLOW_ID", "WM"));
		fieldToColumnDetailsMap.put("workflowName", configProvider.getColumnStringConfig("PROJECTION_NAME", "PM"));
		fieldToColumnDetailsMap.put("workflowDesc",
				configProvider.getColumnStringConfig("PROJECTION_DESCRIPTION", "PM"));
		fieldToColumnDetailsMap.put("status", configProvider.getColumnHelperConfig("WORKFLOW_STATUS_ID", "WM"));
		fieldToColumnDetailsMap.put("company",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "GL_COMp"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnUserConfig("CREATED_BY", "WM"));
		fieldToColumnDetailsMap.put("creationDate", configProvider.getColumnDateConfig("CREATED_DATE", "WM"));
		fieldToColumnDetailsMap.put("approvedBy", configProvider.getColumnUserConfig("APPROVED_BY", "WM"));
		addremainingColumnsOne(fieldToColumnDetailsMap);
		addremainingColumnsTwo(fieldToColumnDetailsMap);
		GtnWebServiceWorkflowSearchConfig.addWfDateColumnsInMap(configProvider, fieldToColumnDetailsMap);
	}

	private static void addremainingColumnsOne(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "WM"));
		fieldToColumnDetailsMap.put("workflowMasterSystemId",
				configProvider.getColumnIntegerConfig("WORKFLOW_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("masterSid", configProvider.getColumnIntegerConfig("PROJECTION_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("contractStructure",
				configProvider.getColumnStringConfig("CONTRACT_STRUCTURE", "WM"));
		fieldToColumnDetailsMap.put("noOfApprovals", configProvider.getColumnIntegerConfig("NO_OF_APPROVAL", "WM"));
		fieldToColumnDetailsMap.put("approvalLevel", configProvider.getColumnIntegerConfig("APPROVAL_LEVEL", "WM"));
		fieldToColumnDetailsMap.put("approvedDate", configProvider.getColumnDateConfig("APPROVED_DATE", "wm"));
		fieldToColumnDetailsMap.put("company",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "GL_COMp"));
	}

	private static void addremainingColumnsTwo(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("businessUnit",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "BU_COMp"));
		fieldToColumnDetailsMap.put("customerHierSid",
				configProvider.getColumnIntegerConfig("CUSTOMER_HIERARCHY_SID", "PM"));
		fieldToColumnDetailsMap.put("customerHierarchyLevel",
				configProvider.getColumnIntegerConfig("CUSTOMER_HIERARCHY_LEVEL", "PM"));
		fieldToColumnDetailsMap.put("custRelationshipBuilderSid",
				configProvider.getColumnIntegerConfig("CUST_RELATIONSHIP_BUILDER_SID", "PM"));
		fieldToColumnDetailsMap.put("productHierarchyLevel",
				configProvider.getColumnIntegerConfig("PRODUCT_HIERARCHY_LEVEL", "PM"));
		fieldToColumnDetailsMap.put("prodRelationshipBuilderSid",
				configProvider.getColumnIntegerConfig("PROD_RELATIONSHIP_BUILDER_SID", "PM"));
		fieldToColumnDetailsMap.put("workflowSid", configProvider.getColumnIntegerConfig("WORKFLOW_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("projectionMasterSid",
				configProvider.getColumnIntegerConfig("PROJECTION_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("createdById", configProvider.getColumnIntegerConfig("CREATED_BY", "WM"));
		fieldToColumnDetailsMap.put("productHierSid",
				configProvider.getColumnIntegerConfig("PRODUCT_HIERARCHY_SID", "PM"));
	}

}
