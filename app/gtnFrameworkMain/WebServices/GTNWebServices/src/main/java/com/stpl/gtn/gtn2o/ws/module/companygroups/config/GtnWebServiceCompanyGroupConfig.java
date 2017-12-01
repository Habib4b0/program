package com.stpl.gtn.gtn2o.ws.module.companygroups.config;

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
import com.stpl.gtn.gtn2o.ws.module.companygroups.constants.GtnWsCGrpQueryContants;

public class GtnWebServiceCompanyGroupConfig implements GtnWsSearchQueryConfigLoader {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWebServiceCompanyGroupConfig.class);
	private Map<String, GtnWsSearchQueryConfig> companyGroupSearchQueryConfigMap = null;

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		if (companyGroupSearchQueryConfigMap == null) {
			companyGroupSearchQueryConfigMap = new HashMap<>();
			loadSearchQueryConfig();
		}
		return companyGroupSearchQueryConfigMap;
	}

	public void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		this.companyGroupSearchQueryConfigMap = searchQueryConfigMap;
	}

	public Map<String, GtnWsSearchQueryConfig> loadSearchQueryConfig() {

		try {

			GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();

			gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsCGrpQueryContants.GTN_CGRP_SEARCH_QUERY);
			gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsCGrpQueryContants.GTN_CGRP_SEARCH_QUERY);
			gtnWebServiceSearchQueryConfig.setCountQuerySelectClause(GtnWsCGrpQueryContants.GTN_CGRP_COUNT_QUERY);

			getWhereConditionForSearch(gtnWebServiceSearchQueryConfig);

			companyGroupSearchQueryConfigMap.put("cGrpSearchQuery", gtnWebServiceSearchQueryConfig);

			GtnWsSearchQueryConfig gtnWebServiceAuditSearchQueryConfig = new GtnWsSearchQueryConfig();

			gtnWebServiceAuditSearchQueryConfig.setCountQuery(GtnWsCGrpQueryContants.GTN_CGRP_AUDIT_SEARCH_QUERY);
			gtnWebServiceAuditSearchQueryConfig.setSearchQuery(GtnWsCGrpQueryContants.GTN_CGRP_AUDIT_SEARCH_QUERY);
			gtnWebServiceAuditSearchQueryConfig
					.setCountQuerySelectClause(GtnWsCGrpQueryContants.GTN_AUDIT_CGRP_COUNT_QUERY);

			getWhereConditionForSearch(gtnWebServiceAuditSearchQueryConfig);
			companyGroupSearchQueryConfigMap.put("cGrpAuditSearchQuery", gtnWebServiceAuditSearchQueryConfig);

			GtnWsSearchQueryConfig gtnWsAddTabAviableTableSearchQueryConfig = new GtnWsSearchQueryConfig();

			gtnWsAddTabAviableTableSearchQueryConfig
					.setCountQuery(GtnWsCGrpQueryContants.GTN_CGRP_AVAILABLE_TABLE_SEARCH_QUERY);
			gtnWsAddTabAviableTableSearchQueryConfig
					.setSearchQuery(gtnWsAddTabAviableTableSearchQueryConfig.getCountQuery());
			getCommonConditionForSearch(gtnWsAddTabAviableTableSearchQueryConfig, false);
			companyGroupSearchQueryConfigMap.put("cGrpAddTabSearchQuery", gtnWsAddTabAviableTableSearchQueryConfig);

			GtnWsSearchQueryConfig gtnWsAddTabselectedTableSearchQueryConfig = new GtnWsSearchQueryConfig();
			gtnWsAddTabselectedTableSearchQueryConfig
					.setCountQuery(GtnWsCGrpQueryContants.GTN_CGRP_SELECTED_TABLE_SEARCH_QUERY);
			gtnWsAddTabselectedTableSearchQueryConfig
					.setSearchQuery(gtnWsAddTabselectedTableSearchQueryConfig.getCountQuery());
			gtnWsAddTabselectedTableSearchQueryConfig
					.setCountQuerySelectClause(GtnWsCGrpQueryContants.GTN_CGRP_SELECTED_TABLE_SELECTED_CLAUSE);
			getCommonConditionForSearch(gtnWsAddTabselectedTableSearchQueryConfig, true);
			companyGroupSearchQueryConfigMap.put("cGrpAddTabSelectedSearchQuery",
					gtnWsAddTabselectedTableSearchQueryConfig);
			gtnWsAddTabselectedTableSearchQueryConfig
					.setWhereClauseList(Arrays.asList(GtnWsCGrpQueryContants.GTN_CGRP_SELECTED_TABLE_WHERE_CLAUSE));

		} catch (Exception ex) {
			logger.error("Error while cloning GtnWsSearchQueryConfig", ex);
		}
		return companyGroupSearchQueryConfigMap;
	}

	private void getWhereConditionForSearch(GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put("customerGroupSid",
				configProvider.getColumnIntegerConfig("COMPANY_GROUP_SID", "CG"));
		fieldToColumnDetailsMap.put("customerGroupName",
				configProvider.getColumnStringConfig("COMPANY_GROUP_NAME", "CG"));
		fieldToColumnDetailsMap.put("customerGroupDesc",
				configProvider.getColumnStringConfig("COMPANY_GROUP_DESCRIPTION", "CG"));
		fieldToColumnDetailsMap.put("customerGroupNo", configProvider.getColumnStringConfig("COMPANY_GROUP_NO", "CG"));
		fieldToColumnDetailsMap.put("versionNo", configProvider.getColumnIntegerConfig("VERSION_NO", "CG"));
		fieldToColumnDetailsMap.put("createdDate", configProvider.getColumnDateConfig("CREATED_DATE", "CG"));
		fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "CG"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnUserConfig("CREATED_BY", "CG"));

		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("CG.COMPANY_GROUP_NAME", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);

	}

	private void getCommonConditionForSearch(GtnWsSearchQueryConfig gtnWsAddTabAviableTableSearchQueryConfig,
			boolean isSelected) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMapAddTab = new HashMap<>();
		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		fieldToColumnDetailsMapAddTab.put("organizationKey", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "HT", "ORGANIZATION_KEY"));
		fieldToColumnDetailsMapAddTab.put("customerId", configProvider.getColumnStringConfig("COMPANY_ID", "CM"));
		fieldToColumnDetailsMapAddTab.put("customerNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "CM"));
		fieldToColumnDetailsMapAddTab.put("cGrpInformationTabCustomerNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "CM"));
		fieldToColumnDetailsMapAddTab.put("customerName", configProvider.getColumnStringConfig("COMPANY_NAME", "CM"));
		fieldToColumnDetailsMapAddTab.put("cGrpInformationTabCustomerName",
				configProvider.getColumnStringConfig("COMPANY_NAME", "CM"));
		fieldToColumnDetailsMapAddTab.put("tradeClass", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "CTC", "C_TRADE_CLASS"));
		fieldToColumnDetailsMapAddTab.put("cGrpInformationTabTradeClass",
				configProvider.getColumnStringConfig("COMPANY_TRADE_CLASS", "CTC"));
		fieldToColumnDetailsMapAddTab.put("tradeClassStartDate",
				configProvider.getColumnDateConfig("TRADE_CLASS_START_DATE", "CTC"));
		fieldToColumnDetailsMapAddTab.put("tradeClassEndDate",
				configProvider.getColumnDateConfig("TRADE_CLASS_END_DATE", "CTC"));
		fieldToColumnDetailsMapAddTab.put("customerType", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "HT_CT", "CUSTOMER_TYPE"));
		fieldToColumnDetailsMapAddTab.put("cGrpInformationTabCustomerType",
				configProvider.getColumnStringConfig("COMPANY_TYPE", "CM"));
		fieldToColumnDetailsMapAddTab.put("customerStatus", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "HT_CS", "CUSTOMER_STATUS"));
		fieldToColumnDetailsMapAddTab.put("cGrpInformationTabCustomerStatus",
				configProvider.getColumnStringConfig("COMPANY_STATUS", "CM"));
		fieldToColumnDetailsMapAddTab.put("lives", configProvider.getColumnStringConfig("LIVES", "CM"));
		fieldToColumnDetailsMapAddTab.put("customerEndDate",
				configProvider.getColumnDateConfig("COMPANY_END_DATE", "CM"));
		fieldToColumnDetailsMapAddTab.put("customerGroup", configProvider.getColumnStringConfig("COMPANY_GROUP", "CM"));
		fieldToColumnDetailsMapAddTab.put("financialSystem",
				configProvider.getColumnStringConfig("FINANCIAL_SYSTEM", "CM"));
		fieldToColumnDetailsMapAddTab.put("address1", configProvider.getColumnStringConfig("ADDRESS1", "CM"));
		fieldToColumnDetailsMapAddTab.put("address2", configProvider.getColumnStringConfig("ADDRESS2", "CM"));
		fieldToColumnDetailsMapAddTab.put("city", configProvider.getColumnStringConfig("CITY", "CM"));
		fieldToColumnDetailsMapAddTab.put("cGrpInformationTabCity", configProvider.getColumnStringConfig("CITY", "CM"));
		fieldToColumnDetailsMapAddTab.put("state", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "HT_ST", "CUSTOMER_STATE"));
		fieldToColumnDetailsMapAddTab.put("cGrpInformationTabState",
				configProvider.getColumnIntegerConfig("STATE", "CM"));
		fieldToColumnDetailsMapAddTab.put("zipCode", configProvider.getColumnStringConfig("ZIP_CODE", "CM"));
		fieldToColumnDetailsMapAddTab.put("cGrpInformationTabZipcode",
				configProvider.getColumnStringConfig("ZIP_CODE", "CM"));
		fieldToColumnDetailsMapAddTab.put("country",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "HT_CN", "COUNTRY"));
		fieldToColumnDetailsMapAddTab.put("regionCode", configProvider.getColumnStringConfig("REGION_CODE", "CM"));
		fieldToColumnDetailsMapAddTab.put("parentCustomerNo", configProvider
				.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "PACM", "PARENT_CUSTOMER_NO"));
		fieldToColumnDetailsMapAddTab.put("parentStartDate",
				configProvider.getColumnDateConfig("PARENT_START_DATE", "CPD"));
		fieldToColumnDetailsMapAddTab.put("parentEndDate",
				configProvider.getColumnDateConfig("PARENT_END_DATE", "CPD"));
		fieldToColumnDetailsMapAddTab.put("customerStartDate",
				configProvider.getColumnDateConfig("COMPANY_START_DATE", "CM"));
		fieldToColumnDetailsMapAddTab.put("priorParentStartDate",
				configProvider.getColumnDateConfig("PRIOR_PARENT_START_DATE", "CPD"));
		fieldToColumnDetailsMapAddTab.put("priorParentCustomerNo", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "PCM", "PRIOR_PARENT_CUSTOMER_NO"));
		fieldToColumnDetailsMapAddTab.put("udc1",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC1", "C_UDC1"));
		fieldToColumnDetailsMapAddTab.put("udc2",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC2", "C_UDC2"));
		fieldToColumnDetailsMapAddTab.put("udc3",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC3", "C_UDC3"));
		fieldToColumnDetailsMapAddTab.put("udc4",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC4", "C_UDC4"));
		fieldToColumnDetailsMapAddTab.put("udc5",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC5", "C_UDC5"));
		fieldToColumnDetailsMapAddTab.put("udc6",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H_UDC6", "C_UDC6"));

		if (isSelected) {
			fieldToColumnDetailsMapAddTab.put("tradeClass", configProvider
					.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "HT_TC", "C_TRADE_CLASS"));
		}
		fieldToColumnDetailsMapAddTab.put("companyMasterSid",
				configProvider.getColumnIntegerConfig("COMPANY_MASTER_SID", "CM"));
		fieldToColumnDetailsMapAddTab.put("companyTradeClass",
				configProvider.getColumnIntegerConfig("COMPANY_TRADE_CLASS_SID", "CTC"));
		fieldToColumnDetailsMapAddTab.put("parentCompanyMasterSid",
				configProvider.getColumnIntegerConfig("COMPANY_PARENT_DETAILS_SID", "CPD"));

		gtnWsAddTabAviableTableSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMapAddTab);
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("CM.COMPANY_ID", "ASC"));
		gtnWsAddTabAviableTableSearchQueryConfig.setOrderByClause(orderByClauseList);
		gtnWsAddTabAviableTableSearchQueryConfig.setWhereClauseList(Arrays.asList("  CM.INBOUND_STATUS <> 'D' "));

	}

}
