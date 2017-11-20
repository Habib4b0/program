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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.constants.GtnWsWorkflowQueryContants;

public class GtnWebServiceWFPrivateViewSearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> privateViewConfigMap = null;

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (privateViewConfigMap == null) {
			privateViewConfigMap = new HashMap<>();
			loadWFPrivateViewSearchQueryConfig();
			loadWFPublicViewSearchQueryConfig();
		}
		return privateViewConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.privateViewConfigMap = searchQueryConfigMap;
	}

	private Map<String, GtnWsSearchQueryConfig> loadWFPrivateViewSearchQueryConfig() {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		GtnWsSearchQueryConfig gtnWFPrivateViewSearchQueryConfig = new GtnWsSearchQueryConfig();
		gtnWFPrivateViewSearchQueryConfig
				.setCountQuerySelectClause(GtnWsWorkflowQueryContants.GTN_WF_PRIVATE_VIEW_COUNT);
		gtnWFPrivateViewSearchQueryConfig.setCountQuery(GtnWsWorkflowQueryContants.GTN_WF_PRIVATE_VIEW_COUNT_QUERY);
		gtnWFPrivateViewSearchQueryConfig.setSearchQuery(GtnWsWorkflowQueryContants.GTN_WF_PRIVATE_VIEW_SEARCH_QUERY);
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		addWorkFlowColumns(fieldToColumnDetailsMap);
		fieldToColumnDetailsMap.put("privateViewviewName",
				configProvider.getColumnStringConfig(GtnFrameworkCommonConstants.VIEW_NAME_CAPS, "WM"));

		gtnWFPrivateViewSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("WM.WORKFLOW_INBOX_SID", "ASC"));
		gtnWFPrivateViewSearchQueryConfig.setOrderByClause(orderByClauseList);

		gtnWFPrivateViewSearchQueryConfig.setWhereClauseList(
				Arrays.asList(GtnWsWorkflowQueryContants.GTN_WF_PRIVATE_VIEW_SEARCH_QUERY_WHERE_CLAUSE));

		privateViewConfigMap.put("privateSearchQuery", gtnWFPrivateViewSearchQueryConfig);

		return privateViewConfigMap;
	}

	public static void addWorkFlowColumns(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("viewNamePrivate",
				configProvider.getColumnStringConfig(GtnFrameworkCommonConstants.VIEW_NAME_CAPS, "WM"));
		fieldToColumnDetailsMap.put("viewTypePrivate", configProvider.getColumnStringConfig("VIEW_TYPE", "WM"));
		fieldToColumnDetailsMap.put("businessProcessprivate",
				configProvider.getColumnStringConfig("BUSINESS_PROCESS", "WM"));
		fieldToColumnDetailsMap.put("workflowIdPrivate", configProvider.getColumnStringConfig("WORKFLOW_ID", "WM"));
		fieldToColumnDetailsMap.put("workflowNamePrivate", configProvider.getColumnStringConfig("WORKFLOW_NAME", "WM"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnUserConfig("CREATED_BY", "WM"));
		fieldToColumnDetailsMap.put("workflowDescPrivate",
				configProvider.getColumnStringConfig("WORKFLOW_DESCRIPTION", "WM"));
		fieldToColumnDetailsMap.put("creationFromDatePrivate",
				configProvider.getColumnDateConfig("CREATION_FROM_DATE", "WM"));
		fieldToColumnDetailsMap.put("creationToDatePrivate",
				configProvider.getColumnDateConfig("CREATION_TO_DATE", "WM"));
		addremainingColumnsOne(fieldToColumnDetailsMap);
		addremainingColumnsTwo(fieldToColumnDetailsMap);
		addremainingColumnsThree(fieldToColumnDetailsMap);
		addremainingColumnsFour(fieldToColumnDetailsMap);
		addremainingColumnsFive(fieldToColumnDetailsMap);
		addremainingColumnsSix(fieldToColumnDetailsMap);
		addremainingColumnsSeven(fieldToColumnDetailsMap);
	}

	private static void addremainingColumnsOne(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("approvedDateFromPrivate",
				configProvider.getColumnDateConfig("APPROVED_DATE_FROM", "WM"));
		fieldToColumnDetailsMap.put("approvedDateToPrivate",
				configProvider.getColumnDateConfig("APPROVED_DATE_TO", "WM"));
		fieldToColumnDetailsMap.put("createdByPrivate", configProvider.getColumnUserConfig("WF_CREATED_BY", "WM"));
		fieldToColumnDetailsMap.put("approvedByPrivate", configProvider.getColumnUserConfig("APPROVED_BY", "WM"));
		fieldToColumnDetailsMap.put("contractIdPrivate",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_ID, "WM"));
		fieldToColumnDetailsMap.put("contractnoPrivate",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NO, "WM"));
		fieldToColumnDetailsMap.put("contractnamePrivate",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NAME, "WM"));
		fieldToColumnDetailsMap.put("contractTypePrivate", configProvider.getColumnStringConfig("CONTRACT_TYPE", "WM"));
	}

	private static void addremainingColumnsTwo(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("companyIdPrivate", configProvider.getColumnStringConfig("COMPANY_ID", "WM"));
		fieldToColumnDetailsMap.put("companyNoPrivate", configProvider.getColumnStringConfig("COMPANY_NO", "WM"));
		fieldToColumnDetailsMap.put("companyNamePrivate",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "WM"));
		fieldToColumnDetailsMap.put("itemIdPrivate",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_ID_COLUMN, "WM"));
		fieldToColumnDetailsMap.put("itemNoPrivate",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NO, "WM"));
		fieldToColumnDetailsMap.put("itemNamePrivate",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NAME, "WM"));
		fieldToColumnDetailsMap.put("deductionLevelPrivate",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DEDUCTION_LEVEL, "WM"));
		fieldToColumnDetailsMap.put("deductionValuePrivate",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DEDUCTION_VALUE, "WM"));
	}

	private static void addremainingColumnsThree(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("workFlowSidPrivate",
				configProvider.getColumnStringConfig("WORKFLOW_INBOX_SID", "WM"));
		fieldToColumnDetailsMap.put("glCompanyMasterSidPrivate",
				configProvider.getColumnStringConfig("GL_COMPANY_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("businessUnitPrivate", configProvider.getColumnStringConfig("BUSINESS_UNIT", "WM"));
		fieldToColumnDetailsMap.put("workflowStatusPrivate",
				configProvider.getColumnStringConfig("WORKFLOW_STATUS", "WM"));
		fieldToColumnDetailsMap.put("brandIdPrivate", configProvider.getColumnStringConfig("BRAND_ID", "WM"));
		fieldToColumnDetailsMap.put("brandNamePrivate", configProvider.getColumnStringConfig("BRAND_NAME", "WM"));
		fieldToColumnDetailsMap.put("glDatePrivate", configProvider.getColumnDateConfig("GL_DATE", "WM"));
		fieldToColumnDetailsMap.put("deductionNoPrivate", configProvider.getColumnStringConfig("DEDUCTION_NO", "WM"));
	}

	private static void addremainingColumnsFour(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("deductionNamePrivate",
				configProvider.getColumnStringConfig("DEDUCTION_NAME", "WM"));
		fieldToColumnDetailsMap.put("adjustmentTypePrivate",
				configProvider.getColumnStringConfig("ADJUSTMENT_TYPE", "WM"));
		fieldToColumnDetailsMap.put("createdDatePrivate", configProvider.getColumnDateConfig("CREATED_DATE", "WM"));
		fieldToColumnDetailsMap.put("modifiedDatePrivate", configProvider.getColumnDateConfig("MODIFIED_DATE", "WM"));
		fieldToColumnDetailsMap.put("modifiedByPrivate", configProvider.getColumnStringConfig("MODIFIED_BY", "WM"));
		fieldToColumnDetailsMap.put("businessUnitIdPrivate",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BUSINESSUNITID, "WM"));
		fieldToColumnDetailsMap.put("businessUnitNoPrivate",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BUSINESSUNITNO, "WM"));
		fieldToColumnDetailsMap.put("businessUnitNamePrivate",
				configProvider.getColumnStringConfig("BUSINESSUNITNAME", "WM"));
		fieldToColumnDetailsMap.put("workflowSid", configProvider.getColumnStringConfig("WORKFLOW_INBOX_SID", "WM"));
		fieldToColumnDetailsMap.put("contractId",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_ID, "WM"));
	}

	private static void addremainingColumnsFive(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("contractNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NO, "WM"));
		fieldToColumnDetailsMap.put("companyNo", configProvider.getColumnStringConfig("COMPANY_NO", "WM"));
		fieldToColumnDetailsMap.put("companyName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "WM"));
		fieldToColumnDetailsMap.put("businessUnitId",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BUSINESSUNITID, "WM"));
		fieldToColumnDetailsMap.put("businessUnitNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BUSINESSUNITNO, "WM"));
		fieldToColumnDetailsMap.put("businessUnitName", configProvider.getColumnStringConfig("BUSINESSUNITNAME", "WM"));
		fieldToColumnDetailsMap.put("contractName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NAME, "WM"));
		fieldToColumnDetailsMap.put("itemNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NO, "WM"));
		fieldToColumnDetailsMap.put("itemName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NAME, "WM"));
		fieldToColumnDetailsMap.put("forecastdeductionValue",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DEDUCTION_VALUE, "WM"));
	}

	private static void addremainingColumnsSix(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("forecastdeductionLevel",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DEDUCTION_LEVEL, "WM"));
		fieldToColumnDetailsMap.put("contractType", configProvider.getColumnStringConfig("CONTRACT_TYPE", "WM"));
		fieldToColumnDetailsMap.put("companyID", configProvider.getColumnStringConfig("COMPANY_ID", "WM"));
		fieldToColumnDetailsMap.put("itemId",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_ID_COLUMN, "WM"));
		fieldToColumnDetailsMap.put("businessUnitIdReturns",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BUSINESSUNITID, "WM"));
		fieldToColumnDetailsMap.put("businessUnitNoReturns",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BUSINESSUNITNO, "WM"));
		fieldToColumnDetailsMap.put("businessUnitNameReturns",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.BUSINESSUNITNAME, "WM"));
		fieldToColumnDetailsMap.put("itemNoReturns",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NO, "WM"));
		fieldToColumnDetailsMap.put("itemNameReturns",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NAME, "WM"));
		fieldToColumnDetailsMap.put("itemIdReturns",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_ID_COLUMN, "WM"));
	}

	private static void addremainingColumnsSeven(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("adjustmentType", configProvider.getColumnStringConfig("ADJUSTMENT_TYPE", "WM"));
		fieldToColumnDetailsMap.put("companyARM",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "WM"));
		fieldToColumnDetailsMap.put("businessUnitARM", configProvider.getColumnStringConfig("BUSINESS_UNIT", "WM"));
		fieldToColumnDetailsMap.put("contractIdArm",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_ID, "WM"));
		fieldToColumnDetailsMap.put("contractNoArm",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NO, "WM"));
		fieldToColumnDetailsMap.put("brandIdArm", configProvider.getColumnStringConfig("BRAND_ID", "WM"));
		fieldToColumnDetailsMap.put("contractNameArm",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NAME, "WM"));
		fieldToColumnDetailsMap.put("itemNoArm",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NO, "WM"));
		fieldToColumnDetailsMap.put("itemNameArm",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ITEM_NAME, "WM"));
		fieldToColumnDetailsMap.put("brandNameArm", configProvider.getColumnStringConfig("BRAND_NAME", "WM"));
		fieldToColumnDetailsMap.put("glDateArm", configProvider.getColumnDateConfig("GL_DATE", "WM"));
		fieldToColumnDetailsMap.put("deductionLevelArm",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DEDUCTION_LEVEL, "WM"));
		fieldToColumnDetailsMap.put("deductionValueArm",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DEDUCTION_VALUE, "WM"));
		fieldToColumnDetailsMap.put("deductionNoArm", configProvider.getColumnStringConfig("DEDUCTION_NO", "WM"));
		fieldToColumnDetailsMap.put("deductionNameArm", configProvider.getColumnStringConfig("DEDUCTION_NAME", "WM"));

	}

	private Map<String, GtnWsSearchQueryConfig> loadWFPublicViewSearchQueryConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWFPublicViewSearchQueryConfig = new GtnWsSearchQueryConfig();
		gtnWFPublicViewSearchQueryConfig.setCountQuerySelectClause(GtnWsWorkflowQueryContants.GTN_WF_PUBLIC_VIEW_COUNT);
		gtnWFPublicViewSearchQueryConfig.setCountQuery(GtnWsWorkflowQueryContants.GTN_WF_PUBLIC_VIEW_COUNT_QUERY);
		gtnWFPublicViewSearchQueryConfig.setSearchQuery(GtnWsWorkflowQueryContants.GTN_WF_PUBLIC_VIEW_SEARCH_QUERY);
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		addWorkFlowColumns(fieldToColumnDetailsMap);
		fieldToColumnDetailsMap.put("publicViewviewName",
				configProvider.getColumnStringConfig(GtnFrameworkCommonConstants.VIEW_NAME_CAPS, "WM"));
		gtnWFPublicViewSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("WM.WORKFLOW_INBOX_SID", "ASC"));
		gtnWFPublicViewSearchQueryConfig.setOrderByClause(orderByClauseList);

		gtnWFPublicViewSearchQueryConfig.setWhereClauseList(
				Arrays.asList(GtnWsWorkflowQueryContants.GTN_WF_PUBLIC_VIEW_SEARCH_QUERY_WHERE_CLAUSE));

		privateViewConfigMap.put("publicSearchQuery", gtnWFPublicViewSearchQueryConfig);

		return privateViewConfigMap;
	}

}
