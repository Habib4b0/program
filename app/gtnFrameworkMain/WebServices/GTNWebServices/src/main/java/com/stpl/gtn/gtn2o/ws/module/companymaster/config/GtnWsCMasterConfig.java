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
						+ " LEFT JOIN company_Master comp ON comp.company_Master_Sid = parent.PARENT_COMPANY_MASTER_SID "
						+ " LEFT JOIN company_Master comp1 ON comp1.company_Master_sid = parent.prior_Parent_Cmpy_Master_Sid "
						+ " LEFT JOIN COMPANY_IDENTIFIER CID on CID.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID \n"
						+ " LEFT JOIN COMPANY_QUALIFIER CQ ON CQ.COMPANY_QUALIFIER_SID = CID.COMPANY_QUALIFIER_SID"
						+ " LEFT JOIN HELPER_TABLE companyTypeHelper on cm.COMPANY_TYPE = companyTypeHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyStatusHelper on cm.COMPANY_STATUS = companyStatusHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyTradeClassHelper on trade.COMPANY_TRADE_CLASS= companyTradeClassHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyGroupHelper on cm.COMPANY_GROUP= companyGroupHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyCategoryHelper on cm.COMPANY_CATEGORY= companyCategoryHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyOrganizationHelper on cm.ORGANIZATION_KEY= companyOrganizationHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyFinancialHelper on cm.FINANCIAL_SYSTEM= companyFinancialHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc1Helper on udc.UDC1= companyUdc1Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc2Helper on udc.UDC2= companyUdc2Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc3Helper on udc.UDC3= companyUdc3Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc4Helper on udc.UDC4= companyUdc4Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc5Helper on udc.UDC5= companyUdc5Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc6Helper on udc.UDC6= companyUdc6Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyStateHelper on cm.STATE= companyStateHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyCountryHelper on cm.COUNTRY= companyCountryHelper.HELPER_TABLE_SID");
		gtnWebServiceSearchQueryConfig.setSearchQuery(
				" FROM company_Master cm LEFT JOIN company_Trade_Class trade ON cm.company_Master_Sid = trade.company_Master_Sid AND"
						+ " trade.inbound_Status <> 'D' LEFT JOIN company_Parent_Details parent ON cm.company_Master_Sid = "
						+ "parent.company_Master_Sid AND parent.inbound_Status <> 'D' LEFT JOIN udcs udc ON cm.company_Master_Sid ="
						+ "udc.master_Sid  AND udc.master_Type = 'COMPANY_MASTER' LEFT JOIN company_Master comp ON comp.company_Master_Sid = "
						+ "parent.PARENT_COMPANY_MASTER_SID"
						+ " LEFT JOIN company_Master comp1 ON comp1.company_Master_sid = parent.prior_Parent_Cmpy_Master_Sid"
						+ " LEFT JOIN COMPANY_IDENTIFIER CID on CID.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID "
						+ " LEFT JOIN COMPANY_QUALIFIER CQ ON CQ.COMPANY_QUALIFIER_SID = CID.COMPANY_QUALIFIER_SID"
						+ " LEFT JOIN HELPER_TABLE companyTypeHelper on cm.COMPANY_TYPE = companyTypeHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyStatusHelper on cm.COMPANY_STATUS = companyStatusHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyTradeClassHelper on trade.COMPANY_TRADE_CLASS = companyTradeClassHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyGroupHelper on cm.COMPANY_GROUP= companyGroupHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyCategoryHelper on cm.COMPANY_CATEGORY= companyCategoryHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyOrganizationHelper on cm.ORGANIZATION_KEY= companyOrganizationHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyFinancialHelper on cm.FINANCIAL_SYSTEM= companyFinancialHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc1Helper on udc.UDC1= companyUdc1Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc2Helper on udc.UDC2= companyUdc2Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc3Helper on udc.UDC3= companyUdc3Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc4Helper on udc.UDC4= companyUdc4Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc5Helper on udc.UDC5= companyUdc5Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyUdc6Helper on udc.UDC6= companyUdc6Helper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyStateHelper on cm.STATE= companyStateHelper.HELPER_TABLE_SID"
						+ " LEFT JOIN HELPER_TABLE companyCountryHelper on cm.COUNTRY= companyCountryHelper.HELPER_TABLE_SID");
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
		companyStatusColumnConfig.setHelperTableAliasName(GtnWsCMasterConstants.COMPANY_STATUS_HELPER);
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
		GtnWsColumnDetailsConfig stateColumnConfig = configProvider.getColumnHelperConfig("STATE", "cm");
		stateColumnConfig.setHelperTableAliasName("companyStateHelper");
		stateColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("state", stateColumnConfig);
		GtnWsColumnDetailsConfig financialSystemColumnConfig = configProvider.getColumnStringConfig("FINANCIAL_SYSTEM",
				"cm");
		financialSystemColumnConfig.setHelperTableAliasName("companyFinancialHelper");
		financialSystemColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("financialSystem", financialSystemColumnConfig);
		fieldToColumnDetailsMap.put("address1", configProvider.getColumnStringConfig("ADDRESS1", "cm"));
		fieldToColumnDetailsMap.put("address2", configProvider.getColumnStringConfig("ADDRESS2", "cm"));
		fieldToColumnDetailsMap.put("city", configProvider.getColumnStringConfig("CITY", "cm"));
		GtnWsColumnDetailsConfig companyGroupColumnConfig = configProvider.getColumnHelperConfig("COMPANY_GROUP", "cm");
		companyGroupColumnConfig.setHelperTableAliasName("companyGroupHelper");
		companyGroupColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("companyGroup", companyGroupColumnConfig);
		fieldToColumnDetailsMap.put("zipCode", configProvider.getColumnStringConfig("ZIP_CODE", "cm"));
		GtnWsColumnDetailsConfig countryColumnConfig = configProvider.getColumnHelperConfig("COUNTRY", "cm");
		countryColumnConfig.setHelperTableAliasName("companyCountryHelper");
		countryColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("country", countryColumnConfig);
		fieldToColumnDetailsMap.put("regionCode", configProvider.getColumnStringConfig("REGION_CODE", "cm"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnStringConfig("CREATED_BY", "cm"));
		fieldToColumnDetailsMap.put("createdDate", configProvider.getColumnStringConfig("CREATED_DATE", "cm"));
		fieldToColumnDetailsMap.put("modifiedBy", configProvider.getColumnStringConfig("MODIFIED_BY", "cm"));
		fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnStringConfig("MODIFIED_DATE", "cm"));
		fieldToColumnDetailsMap.put("batchId", configProvider.getColumnStringConfig("BATCH_ID", "cm"));
		fieldToColumnDetailsMap.put("lastUpdatedDate", configProvider.getColumnDateConfig("LAST_UPDATED_DATE", "cm"));
		fieldToColumnDetailsMap.put("companyStartDate", configProvider.getColumnDateConfig("COMPANY_START_DATE", "cm"));
		fieldToColumnDetailsMap.put("inboundStatus", configProvider.getColumnStringConfig("INBOUND_STATUS", "cm"));
		GtnWsColumnDetailsConfig companyCategoryColumnConfig = configProvider.getColumnHelperConfig("COMPANY_CATEGORY",
				"cm");
		companyCategoryColumnConfig.setHelperTableAliasName("companyCategoryHelper");
		companyCategoryColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("companyCategory", companyCategoryColumnConfig);
		GtnWsColumnDetailsConfig organizationColumnConfig = configProvider.getColumnHelperConfig("ORGANIZATION_KEY",
				"cm");
		organizationColumnConfig.setHelperTableAliasName("companyOrganizationHelper");
		organizationColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("organizationKey", organizationColumnConfig);
		GtnWsColumnDetailsConfig udc1ColumnConfig = configProvider.getColumnHelperConfig("UDC1", "udc");
		udc1ColumnConfig.setHelperTableAliasName("companyUdc1Helper");
		udc1ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc1", udc1ColumnConfig);
		GtnWsColumnDetailsConfig udc2ColumnConfig = configProvider.getColumnHelperConfig("UDC2", "udc");
		udc2ColumnConfig.setHelperTableAliasName("companyUdc2Helper");
		udc2ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc2", udc2ColumnConfig);
		GtnWsColumnDetailsConfig udc3ColumnConfig = configProvider.getColumnHelperConfig("UDC3", "udc");
		udc3ColumnConfig.setHelperTableAliasName("companyUdc3Helper");
		udc3ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc3", udc3ColumnConfig);
		GtnWsColumnDetailsConfig udc4ColumnConfig = configProvider.getColumnHelperConfig("UDC4", "udc");
		udc4ColumnConfig.setHelperTableAliasName("companyUdc4Helper");
		udc4ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc4", udc4ColumnConfig);
		GtnWsColumnDetailsConfig udc5ColumnConfig = configProvider.getColumnHelperConfig("UDC5", "udc");
		udc5ColumnConfig.setHelperTableAliasName("companyUdc5Helper");
		udc5ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc5", udc5ColumnConfig);
		GtnWsColumnDetailsConfig udc6ColumnConfig = configProvider.getColumnHelperConfig("UDC6", "udc");
		udc6ColumnConfig.setHelperTableAliasName("companyUdc6Helper");
		udc6ColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("udc6", udc6ColumnConfig);
		GtnWsColumnDetailsConfig companyTradeClassColumnConfig = configProvider
				.getColumnHelperConfig("COMPANY_TRADE_CLASS", GtnFrameworkWebserviceConstant.TRADE);
		companyTradeClassColumnConfig.setHelperTableAliasName("companyTradeClassHelper");
		companyTradeClassColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("companyTradeClass", companyTradeClassColumnConfig);
		GtnWsColumnDetailsConfig tradeClassColumnConfig = configProvider.getColumnHelperConfig("COMPANY_TRADE_CLASS",
				GtnFrameworkWebserviceConstant.TRADE);
		tradeClassColumnConfig.setHelperTableAliasName("companyTradeClassHelper");
		tradeClassColumnConfig.setHelperTableColumnName(GtnWsConstants.DESCRIPTION);
		fieldToColumnDetailsMap.put("tradeClass", tradeClassColumnConfig);
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
		fieldToColumnDetailsMap.put("companyType1",
				configProvider.getColumnStringConfig(GtnWsConstants.DESCRIPTION, "helperDesc"));
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
