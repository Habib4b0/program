package com.stpl.gtn.gtn2o.ws.module.companymaster.config;

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
import com.stpl.gtn.gtn2o.ws.module.companymaster.constants.GtnWsCMasterConstants;
import com.stpl.gtn.gtn2o.ws.util.GtnWsConstants;

public class GtnWsCMasterConfig implements GtnWsSearchQueryConfigLoader {

	private static Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();

	static {
		loadSearchQueryConfig();
	}

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		return searchQueryConfigMap;
	}

	public static void setSearchQueryConfigMap(Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap) {
		GtnWsCMasterConfig.searchQueryConfigMap = searchQueryConfigMap;
	}

	public static Map<String, GtnWsSearchQueryConfig> loadSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();

		gtnWebServiceSearchQueryConfig.setCountQuery(
				" FROM company_Master cm LEFT JOIN company_Trade_Class trade ON cm.company_Master_Sid = trade.company_Master_Sid "
						+ "AND trade.inbound_Status <> 'D' LEFT JOIN company_Parent_Details parent ON cm.company_Master_Sid = "
						+ "parent.company_Master_Sid AND parent.inbound_Status <> 'D' LEFT JOIN udcs udc ON cm.company_Master_Sid = "
						+ "udc.master_Sid  AND udc.master_Type = 'COMPANY_MASTER'"
						+ " LEFT JOIN company_Master comp ON comp.company_Master_Sid = parent.Company_Master_Sid "
						+ " LEFT JOIN company_Master comp1 ON comp1.company_Master_sid = parent.prior_Parent_Cmpy_Master_Sid "
						+ " LEFT JOIN COMPANY_IDENTIFIER CID on CID.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID \n"
						+ " LEFT JOIN COMPANY_QUALIFIER CQ ON CQ.COMPANY_QUALIFIER_SID = CID.COMPANY_QUALIFIER_SID"
						+ " LEFT JOIN HELPER_TABLE companyTypeHelper on cm.COMPANY_TYPE = companyTypeHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyStatusHelper on cm.COMPANY_STATUS = companyStatusHelper.HELPER_TABLE_SID");
		gtnWebServiceSearchQueryConfig.setSearchQuery(
				" FROM company_Master cm LEFT JOIN company_Trade_Class trade ON cm.company_Master_Sid = trade.company_Master_Sid AND"
						+ " trade.inbound_Status <> 'D' LEFT JOIN company_Parent_Details parent ON cm.company_Master_Sid = "
						+ "parent.company_Master_Sid AND parent.inbound_Status <> 'D' LEFT JOIN udcs udc ON cm.company_Master_Sid ="
						+ "udc.master_Sid  AND udc.master_Type = 'COMPANY_MASTER' LEFT JOIN company_Master comp ON comp.company_Master_Sid = "
						+ "parent.Company_Master_Sid"
						+ " LEFT JOIN company_Master comp1 ON comp1.company_Master_sid = parent.prior_Parent_Cmpy_Master_Sid"
						+ " LEFT JOIN COMPANY_IDENTIFIER CID on CID.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID "
						+ " LEFT JOIN COMPANY_QUALIFIER CQ ON CQ.COMPANY_QUALIFIER_SID = CID.COMPANY_QUALIFIER_SID"
						+ " LEFT JOIN HELPER_TABLE companyTypeHelper on cm.COMPANY_TYPE=companyTypeHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyStatusHelper on cm.COMPANY_STATUS = companyStatusHelper.HELPER_TABLE_SID");

		gtnWebServiceSearchQueryConfig.setCountAliasAtEnd("A");
		gtnWebServiceSearchQueryConfig.setCountQuerySelectClause(GtnWsCMasterConstants.QUERY_COUNT);

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put("companyMasterSid",
				configProvider.getColumnStringConfig("COMPANY_MASTER_SID", "cm"));
		GtnWsColumnDetailsConfig companyIdColumnConfig = configProvider.getColumnStringConfig("COMPANY_ID", "cm");
		fieldToColumnDetailsMap.put("companyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("parentCompanySearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("itemIdentifierparentCompanySearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("aliasTabTpSearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("itemPricingparentCompanySearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabCompanyNameSearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabTpSearchcompanyId", companyIdColumnConfig);
		fieldToColumnDetailsMap.put("landingScreenTpSearchcompanyId", companyIdColumnConfig);
		GtnWsColumnDetailsConfig companyNoColumnConfig = configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "cm");
		fieldToColumnDetailsMap.put("companyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("itemIdentifierparentCompanySearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("parentCompanySearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("itemPricingparentCompanySearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("aliasTabTpSearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabCompanyNameSearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabTpSearchcompanyNo", companyNoColumnConfig);
		fieldToColumnDetailsMap.put("landingScreenTpSearchcompanyNo", companyNoColumnConfig);
		GtnWsColumnDetailsConfig companyNameColumnConfig = configProvider.getColumnStringConfig("COMPANY_NAME", "cm");
		fieldToColumnDetailsMap.put("companyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("parentCompanySearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("itemIdentifierparentCompanySearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("itemPricingparentCompanySearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("aliasTabTpSearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabCompanyNameSearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabTpSearchcompanyName", companyNameColumnConfig);
		fieldToColumnDetailsMap.put("landingScreenTpSearchcompanyName", companyNameColumnConfig);
		GtnWsColumnDetailsConfig companyTypeColumnConfig = configProvider.getColumnHelperConfig("COMPANY_TYPE", "cm");
		companyTypeColumnConfig.setHelperTableAliasName("companyTypeHelper");
		companyTypeColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("companyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("parentCompanySearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("itemIdentifierparentCompanySearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("itemPricingparentCompanySearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("aliasTabTpSearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabCompanyNameSearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabTpSearchcompanyType", companyTypeColumnConfig);
		fieldToColumnDetailsMap.put("landingScreenTpSearchcompanyType", companyTypeColumnConfig);
		GtnWsColumnDetailsConfig companyStatusColumnConfig = configProvider.getColumnHelperConfig("COMPANY_STATUS",
				"cm");
		companyStatusColumnConfig.setHelperTableAliasName("companyStatusHelper");
		companyStatusColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("companyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("parentCompanySearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("itemIdentifierparentCompanySearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("itemPricingparentCompanySearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("aliasTabTpSearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabCompanyNameSearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("contractHeaderTabTpSearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("landingScreenTpSearchcompanyStatus", companyStatusColumnConfig);
		fieldToColumnDetailsMap.put("recordLockStatus",
				configProvider.getColumnBooleanConfig("RECORD_LOCK_STATUS", "cm"));
		fieldToColumnDetailsMap.put("lives", configProvider.getColumnStringConfig("LIVES", "cm"));
		fieldToColumnDetailsMap.put("companyEndDate", configProvider.getColumnDateConfig("COMPANY_END_DATE", "cm"));
		fieldToColumnDetailsMap.put("companyGroup", configProvider.getColumnHelperConfig("COMPANY_GROUP", "cm"));
		fieldToColumnDetailsMap.put("financialSystem", configProvider.getColumnStringConfig("FINANCIAL_SYSTEM", "cm"));
		fieldToColumnDetailsMap.put("address1", configProvider.getColumnStringConfig("ADDRESS1", "cm"));
		fieldToColumnDetailsMap.put("address2", configProvider.getColumnStringConfig("ADDRESS2", "cm"));
		fieldToColumnDetailsMap.put("city", configProvider.getColumnStringConfig("CITY", "cm"));
		fieldToColumnDetailsMap.put("state", configProvider.getColumnHelperConfig("STATE", "cm"));
		fieldToColumnDetailsMap.put("zipCode", configProvider.getColumnStringConfig("ZIP_CODE", "cm"));
		fieldToColumnDetailsMap.put("country", configProvider.getColumnHelperConfig("COUNTRY", "cm"));
		fieldToColumnDetailsMap.put("regionCode", configProvider.getColumnStringConfig("REGION_CODE", "cm"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnStringConfig("CREATED_BY", "cm"));
		fieldToColumnDetailsMap.put("createdDate", configProvider.getColumnStringConfig("CREATED_DATE", "cm"));
		fieldToColumnDetailsMap.put("modifiedBy", configProvider.getColumnStringConfig("MODIFIED_BY", "cm"));
		fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnStringConfig("MODIFIED_DATE", "cm"));
		fieldToColumnDetailsMap.put("batchId", configProvider.getColumnStringConfig("BATCH_ID", "cm"));
		fieldToColumnDetailsMap.put("lastUpdatedDate", configProvider.getColumnDateConfig("LAST_UPDATED_DATE", "cm"));
		fieldToColumnDetailsMap.put("companyStartDate", configProvider.getColumnDateConfig("COMPANY_START_DATE", "cm"));
		fieldToColumnDetailsMap.put("inboundStatus", configProvider.getColumnStringConfig("INBOUND_STATUS", "cm"));
		fieldToColumnDetailsMap.put("companyCategory", configProvider.getColumnHelperConfig("COMPANY_CATEGORY", "cm"));
		fieldToColumnDetailsMap.put("organizationKey", configProvider.getColumnHelperConfig("ORGANIZATION_KEY", "cm"));
		fieldToColumnDetailsMap.put("udc1", configProvider.getColumnHelperConfig("UDC1", "udc"));
		fieldToColumnDetailsMap.put("udc2", configProvider.getColumnHelperConfig("UDC2", "udc"));
		fieldToColumnDetailsMap.put("udc3", configProvider.getColumnHelperConfig("UDC3", "udc"));
		fieldToColumnDetailsMap.put("udc4", configProvider.getColumnHelperConfig("UDC4", "udc"));
		fieldToColumnDetailsMap.put("udc5", configProvider.getColumnHelperConfig("UDC5", "udc"));
		fieldToColumnDetailsMap.put("udc6", configProvider.getColumnHelperConfig("UDC6", "udc"));
		fieldToColumnDetailsMap.put("companyTradeClass",
				configProvider.getColumnHelperConfig("COMPANY_TRADE_CLASS", GtnFrameworkWebserviceConstant.TRADE));
		fieldToColumnDetailsMap.put("tradeClass",
				configProvider.getColumnHelperConfig("COMPANY_TRADE_CLASS", GtnFrameworkWebserviceConstant.TRADE));
		fieldToColumnDetailsMap.put("tradeClassStartDate",
				configProvider.getColumnDateConfig("TRADE_CLASS_START_DATE", GtnFrameworkWebserviceConstant.TRADE));
		fieldToColumnDetailsMap.put("tradeClassEndDate",
				configProvider.getColumnDateConfig("TRADE_CLASS_END_DATE", GtnFrameworkWebserviceConstant.TRADE));
		fieldToColumnDetailsMap.put("parentCompanyMasterSid", configProvider
				.getColumnStringConfig("PARENT_COMPANY_MASTER_SID", GtnFrameworkWebserviceConstant.PARENT));
		fieldToColumnDetailsMap.put("parentStartDate",
				configProvider.getColumnDateConfig("PARENT_START_DATE", GtnFrameworkWebserviceConstant.PARENT));
		fieldToColumnDetailsMap.put("parentEndDate",
				configProvider.getColumnDateConfig("PARENT_END_DATE", GtnFrameworkWebserviceConstant.PARENT));
		fieldToColumnDetailsMap.put("parentCompanyNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "comp", "comp"));
		fieldToColumnDetailsMap.put("priorParentCompanyNo", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "comp1", "priorCompanyNo"));
		fieldToColumnDetailsMap.put("priorParentStartDate",
				configProvider.getColumnDateConfig("PRIOR_PARENT_START_DATE", GtnFrameworkWebserviceConstant.PARENT));
		fieldToColumnDetailsMap.put("companyIdentifier",
				configProvider.getColumnStringConfig("COMPANY_IDENTIFIER_VALUE", "CID"));
		fieldToColumnDetailsMap.put("companyQualifierName",
				configProvider.getColumnStringConfig("COMPANY_QUALIFIER_SID", "CQ"));
		fieldToColumnDetailsMap.put("companyType1", configProvider.getColumnStringConfig(GtnWsConstants.DESCRIPTION, "helperDesc"));
		fieldToColumnDetailsMap.put("companyStatus1",
				configProvider.getColumnStringConfig(GtnWsConstants.DESCRIPTION, "helperDescription"));
		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> orderByClauseList = new ArrayList<>();
		orderByClauseList.add(new GtnWebServiceOrderByCriteria("cm.company_Master_Sid", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(orderByClauseList);
		gtnWebServiceSearchQueryConfig.setWhereClauseList(Arrays.asList(" cm.inbound_Status <> 'D' "));
		gtnWebServiceSearchQueryConfig.addWhereClauseLeftPart(" cm.inbound_Status ");

		searchQueryConfigMap.put("SearchQuery", gtnWebServiceSearchQueryConfig);

		// Parent Look up Search
		GtnWsSearchQueryConfig gtncompanyParentlookupSearchQueryConfig = new GtnWsSearchQueryConfig();
		List<GtnWebServiceOrderByCriteria> parentLookupOrderByClauseList = new ArrayList<>();
		parentLookupOrderByClauseList.add(new GtnWebServiceOrderByCriteria("cm.company_Id", "ASC"));
		parentLookupOrderByClauseList.add(new GtnWebServiceOrderByCriteria("cm.company_No", "ASC"));
		gtncompanyParentlookupSearchQueryConfig.setOrderByClause(parentLookupOrderByClauseList);
		gtncompanyParentlookupSearchQueryConfig.setWhereClauseList(Arrays.asList(" cm.inbound_Status <> 'D' "));
		// select distinct
		gtncompanyParentlookupSearchQueryConfig.setParentSearchWhereClause("cm.COMPANY_MASTER_SID <> 'parent?id'");

		gtncompanyParentlookupSearchQueryConfig.setSearchQuery(" FROM Company_Master cm ");
		searchQueryConfigMap.put("companyParentlookupQuery", gtncompanyParentlookupSearchQueryConfig);
		// Parent Look UP Search end

		gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();

		gtnWebServiceSearchQueryConfig
				.setSearchQuery("select company_Qualifier_Sid,company_Qualifier_Name from dbo.Company_Qualifier ");
		gtnWebServiceSearchQueryConfig.setCountQuery("select count(1) from dbo.Company_Qualifier ");
		gtnWebServiceSearchQueryConfig.setWhereClauseList(Arrays.asList(" inbound_Status <> 'D' "));

		List<GtnWebServiceOrderByCriteria> orderByQualifierClauseList = new ArrayList<>();
		orderByQualifierClauseList.add(new GtnWebServiceOrderByCriteria("company_Qualifier_Name", "ASC"));

		gtnWebServiceSearchQueryConfig.setOrderByClause(orderByQualifierClauseList);

		searchQueryConfigMap.put("CompanyIdentifierDdlb", gtnWebServiceSearchQueryConfig);

		// Company Identifier Edit list Look up Data Load Starts
		GtnWsSearchQueryConfig gtnWebServiceSearchCmQualifierConfig = new GtnWsSearchQueryConfig();
		gtnWebServiceSearchCmQualifierConfig.setCountQuery("from COMPANY_QUALIFIER CQ");
		gtnWebServiceSearchCmQualifierConfig.setSearchQuery("from COMPANY_QUALIFIER CQ");
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsCmMap = new HashMap<>();
		fieldToColumnDetailsCmMap.put("identifierQualifierSid",
				configProvider.getColumnIntegerConfig("COMPANY_QUALIFIER_SID", "CQ"));
		fieldToColumnDetailsCmMap.put("identifierCodeQualifier",
				configProvider.getColumnStringConfig("COMPANY_QUALIFIER_VALUE", "CQ"));
		fieldToColumnDetailsCmMap.put("identifierCodeQualifierName",
				configProvider.getColumnStringConfig("COMPANY_QUALIFIER_NAME", "CQ"));
		fieldToColumnDetailsCmMap.put("effectiveDates", configProvider.getColumnStringConfig("EFFECTIVE_DATES", "CQ"));
		fieldToColumnDetailsCmMap.put("notes", configProvider.getColumnStringConfig("NOTES", "CQ"));
		fieldToColumnDetailsCmMap.put("modifiedBy", configProvider.getColumnUserConfig("MODIFIED_BY", "CQ"));
		fieldToColumnDetailsCmMap.put("modifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "CQ"));
		fieldToColumnDetailsCmMap.put("createdBy", configProvider.getColumnUserConfig("CREATED_BY", "CQ"));
		fieldToColumnDetailsCmMap.put("createdDate", configProvider.getColumnDateConfig("CREATED_DATE", "CQ"));
		fieldToColumnDetailsCmMap.put("recordLockStatus",
				configProvider.getColumnStringConfig("RECORD_LOCK_STATUS", "CQ"));

		gtnWebServiceSearchCmQualifierConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsCmMap);

		List<GtnWebServiceOrderByCriteria> iMorderByClauseList = new ArrayList<>();
		iMorderByClauseList.add(new GtnWebServiceOrderByCriteria("CQ.COMPANY_QUALIFIER_VALUE", "ASC"));
		gtnWebServiceSearchCmQualifierConfig.setOrderByClause(iMorderByClauseList);
		gtnWebServiceSearchCmQualifierConfig.setWhereClauseList(Arrays.asList("CQ.INBOUND_STATUS <> 'D'"));

		searchQueryConfigMap.put("qualifiereditListQuery", gtnWebServiceSearchCmQualifierConfig);
		// Company Identifier Edit list Look up Data Load Ends
		return searchQueryConfigMap;
	}

}
