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
import com.stpl.gtn.gtn2o.ws.module.workflowinbox.constants.GtnWsWorkflowQueryContants;

public class GtnWebServiceWFCreatedandApprovedBySearchConfig implements GtnWsSearchQueryConfigLoader {

	private Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = null;

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (searchQueryConfigMap == null) {
			searchQueryConfigMap = new HashMap<>();
			loadWFCreatedandApprovedBySearchQueryConfig();
		}
		return searchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.searchQueryConfigMap = searchQueryConfigMap;
	}

	private Map<String, GtnWsSearchQueryConfig> loadWFCreatedandApprovedBySearchQueryConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		gtnWebServiceSearchQueryConfig.setCountQuerySelectClause(GtnWsWorkflowQueryContants.GTN_WF_CREATED_BY_COUNT);
		gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsWorkflowQueryContants.GTN_WF_CREATED_BY_COUNT_QUERY);
		gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsWorkflowQueryContants.GTN_WF_CREATED_BY_SEARCH_QUERY);
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put("viewNamePrivate", configProvider.getColumnStringConfig("VIEW_NAME", "WM"));
		fieldToColumnDetailsMap.put("viewTypePrivate", configProvider.getColumnStringConfig("VIEW_TYPE", "WM"));
		fieldToColumnDetailsMap.put("businessProcessprivate",
				configProvider.getColumnStringConfig("BUSINESS_PROCESS", "WM"));
		fieldToColumnDetailsMap.put("workflowIdPrivate", configProvider.getColumnStringConfig("WORKFLOW_ID", "WM"));
		fieldToColumnDetailsMap.put("workflowNamePrivate", configProvider.getColumnStringConfig("WORKFLOW_NAME", "WM"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnIntegerConfig("CREATED_BY", "WM"));
		fieldToColumnDetailsMap.put("workflowDescPrivate",
				configProvider.getColumnStringConfig("WORKFLOW_DESCRIPTION", "WM"));
		addremainingColumnsOne(fieldToColumnDetailsMap);
		addremainingColumnsTwo(fieldToColumnDetailsMap);
		addremainingColumnsThree(fieldToColumnDetailsMap);
		addremainingColumnsFour(fieldToColumnDetailsMap);

		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("WM.WORKFLOW_ID", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);

		searchQueryConfigMap.put("privateSearchQuery", gtnWebServiceSearchQueryConfig);

		return searchQueryConfigMap;
	}

	private static void addremainingColumnsOne(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("creationFromDatePrivate",
				configProvider.getColumnDateConfig("CREATION_FROM_DATE", "WM"));
		fieldToColumnDetailsMap.put("creationToDatePrivate",
				configProvider.getColumnDateConfig("CREATION_TO_DATE", "WM"));
		fieldToColumnDetailsMap.put("approvedDateFromPrivate",
				configProvider.getColumnDateConfig("APPROVED_DATE_FROM", "WM"));
		fieldToColumnDetailsMap.put("approvedDateToPrivate",
				configProvider.getColumnDateConfig("APPROVED_DATE_TO", "WM"));
		fieldToColumnDetailsMap.put("createdByPrivate", configProvider.getColumnIntegerConfig("WF_CREATED_BY", "WM"));
		fieldToColumnDetailsMap.put("approvedByPrivate", configProvider.getColumnIntegerConfig("APPROVED_BY", "WM"));
		fieldToColumnDetailsMap.put("contractIdPrivate", configProvider.getColumnStringConfig("CONTRACT_ID", "WM"));
		fieldToColumnDetailsMap.put("contractnoPrivate", configProvider.getColumnStringConfig("CONTRACT_NO", "WM"));
		fieldToColumnDetailsMap.put("contractnamePrivate", configProvider.getColumnStringConfig("CONTRACT_NAME", "WM"));
		fieldToColumnDetailsMap.put("contractTypePrivate",
				configProvider.getColumnIntegerConfig("CONTRACT_TYPE", "WM"));
	}

	private static void addremainingColumnsTwo(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("companyIdPrivate", configProvider.getColumnStringConfig("COMPANY_ID", "WM"));
		fieldToColumnDetailsMap.put("companyNoPrivate", configProvider.getColumnStringConfig("COMPANY_NO", "WM"));
		fieldToColumnDetailsMap.put("companyNamePrivate", configProvider.getColumnStringConfig("COMPANY_NAME", "WM"));
		fieldToColumnDetailsMap.put("itemIdPrivate", configProvider.getColumnStringConfig("ITEM_ID", "WM"));
		fieldToColumnDetailsMap.put("itemNoPrivate", configProvider.getColumnStringConfig("ITEM_NO", "WM"));
		fieldToColumnDetailsMap.put("itemNamePrivate", configProvider.getColumnStringConfig("ITEM_NAME", "WM"));
		fieldToColumnDetailsMap.put("deductionLevelPrivate",
				configProvider.getColumnStringConfig("DEDUCTION_LEVEL", "WM"));
		fieldToColumnDetailsMap.put("deductionValuePrivate",
				configProvider.getColumnIntegerConfig("DEDUCTION_VALUE", "WM"));
	}

	private static void addremainingColumnsThree(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("workFlowSidPrivate",
				configProvider.getColumnIntegerConfig("WORKFLOW_INBOX_SID", "WM"));
		fieldToColumnDetailsMap.put("glCompanyMasterSidPrivate",
				configProvider.getColumnIntegerConfig("GL_COMPANY_MASTER_SID", "WM"));
		fieldToColumnDetailsMap.put("businessUnitPrivate",
				configProvider.getColumnIntegerConfig("BUSINESS_UNIT", "WM"));
		fieldToColumnDetailsMap.put("workflowStatusPrivate",
				configProvider.getColumnIntegerConfig("WORKFLOW_STATUS", "WM"));
		fieldToColumnDetailsMap.put("brandIdPrivate", configProvider.getColumnStringConfig("BRAND_ID", "WM"));
		fieldToColumnDetailsMap.put("brandNamePrivate", configProvider.getColumnStringConfig("BRAND_NAME", "WM"));
		fieldToColumnDetailsMap.put("glDatePrivate", configProvider.getColumnDateConfig("GL_DATE", "WM"));
		fieldToColumnDetailsMap.put("deductionNoPrivate", configProvider.getColumnStringConfig("DEDUCTION_NO", "WM"));
		fieldToColumnDetailsMap.put("deductionNamePrivate",
				configProvider.getColumnStringConfig("DEDUCTION_NAME", "WM"));
	}

	private static void addremainingColumnsFour(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("adjustmentTypePrivate",
				configProvider.getColumnStringConfig("ADJUSTMENT_TYPE", "WM"));
		fieldToColumnDetailsMap.put("createdDatePrivate", configProvider.getColumnDateConfig("CREATED_DATE", "WM"));
		GtnWsColumnDetailsConfig intConfig = configProvider.getColumnStringConfig("CREATED_BY", "WM");
		intConfig.setDataType("Int");
		fieldToColumnDetailsMap.put("createdByPrivate", intConfig);
		fieldToColumnDetailsMap.put("modifiedDatePrivate", configProvider.getColumnDateConfig("MODIFIED_DATE", "WM"));
		fieldToColumnDetailsMap.put("modifiedByPrivate", configProvider.getColumnIntegerConfig("MODIFIED_BY", "WM"));
		fieldToColumnDetailsMap.put("businessUnitIdPrivate",
				configProvider.getColumnStringConfig("BUSINESSUNITID", "WM"));
		fieldToColumnDetailsMap.put("businessUnitNoPrivate",
				configProvider.getColumnStringConfig("BUSINESSUNITNO", "WM"));
		fieldToColumnDetailsMap.put("businessUnitNamePrivate",
				configProvider.getColumnStringConfig("BUSINESSUNITNAME", "WM"));
	}
}
