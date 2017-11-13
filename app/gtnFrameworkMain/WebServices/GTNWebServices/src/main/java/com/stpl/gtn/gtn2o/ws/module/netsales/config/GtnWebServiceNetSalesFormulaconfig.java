/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.netsales.config;

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
import com.stpl.gtn.gtn2o.ws.module.netsales.constants.GtnWsNsfQueryConstants;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfCommonConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnWebServiceNetSalesFormulaconfig implements GtnWsSearchQueryConfigLoader {

	private static Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();

	static {
		loadSearchQueryConfig();
		loadContractSearchQueryConfig();
		loadAvailableCustomersSearchQueryConfig();
		loadSalesBaisSelectedCustomersQueryConfig();

		loadAvailableDeductionRSQueryConfig();
		loadSelectedDeductionRSQueryConfig();

		loadAvailableDeductionContractQueryConfig();
		loadSelectedDeductionContractQueryConfig();
	}

	public static void loadSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnIntegerConfig("NET_SALES_FORMULA_MASTER_SID", "NSFM"));
		fieldToColumnDetailsMap.put("netSalesFormulaMainView_formulaId",
				configProvider.getColumnStringConfig("NET_SALES_FORMULA_ID", "NSFM"));
		fieldToColumnDetailsMap.put("netSalesFormulaMainView_formulaNo",
				configProvider.getColumnStringConfig("NET_SALES_FORMULA_NO", "NSFM"));
		fieldToColumnDetailsMap.put("netSalesFormulaMainView_formulaName",
				configProvider.getColumnStringConfig("NET_SALES_FORMULA_NAME", "NSFM"));
		fieldToColumnDetailsMap.put("formulaId", configProvider.getColumnStringConfig("NET_SALES_FORMULA_ID", "NSFM"));
		fieldToColumnDetailsMap.put("formulaNo", configProvider.getColumnStringConfig("NET_SALES_FORMULA_NO", "NSFM"));
		fieldToColumnDetailsMap.put("formulaName",
				configProvider.getColumnStringConfig("NET_SALES_FORMULA_NAME", "NSFM"));
		fieldToColumnDetailsMap.put("netSalesFormulaMainView_formulaType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.FORMULA_TYPE, GtnFrameworkWebserviceConstant.FORMULA_TYPE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("formulaType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.FORMULA_TYPE, GtnFrameworkWebserviceConstant.FORMULA_TYPE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("creationDate", configProvider.getColumnDateConfig("CREATED_DATE", "NSFM"));
		fieldToColumnDetailsMap.put("createdBy", configProvider.getColumnUserConfig("CREATED_BY", "NSFM"));
		fieldToColumnDetailsMap.put("modifiedDate", configProvider.getColumnDateConfig("MODIFIED_DATE", "NSFM"));
		fieldToColumnDetailsMap.put("modifiedBy", configProvider.getColumnUserConfig("MODIFIED_BY", "NSFM"));

		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("NSFM.NET_SALES_FORMULA_ID", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(cDROrderByClauseList);
		gtnWebServiceSearchQueryConfig
				.setWhereClauseList(Arrays.asList(GtnWsNsfQueryConstants.GTN_NSF_LANDING_SEARCH_QUERY_WHERE_CLAUSE));
		gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsNsfQueryConstants.GTN_NSF_LANDING_COUNT_QUERY);

		gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsNsfQueryConstants.GTN_NSF_LANDING_SEARCH_QUERY);
		gtnWebServiceSearchQueryConfig
				.setCountQuerySelectClause(GtnWsNsfQueryConstants.GTN_NSF_LANDING_COUNT_SELECT_CLAUSE);

		searchQueryConfigMap.put(GtnWsNsfCommonConstants.GTN_NSF_LANDING_SEARCH_QUERY_NAME,
				gtnWebServiceSearchQueryConfig);

	}

	public static void loadContractSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_MASTER_SID, "CM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_NO_PROPERTYID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NO, "CM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_contractNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NO, "CM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_NAME_PROPERTYID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NAME, "CM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_contractName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NAME, "CM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.MARKET_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H",
						GtnFrameworkWebserviceConstant.CONTRACT_TYPE, GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_marketType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H",
						GtnFrameworkWebserviceConstant.CONTRACT_TYPE, GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CFP_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NAME, "CFP"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_cfpName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NAME, "CFP"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CFP_NO,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NO, "CFPM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_cfpNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NO, "CFPM"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.IFP_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.IFP_NAME, "IFP"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_ifpName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.IFP_NAME, "IFP"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.IFP_NUMBER,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.IFP_NO, "IFPM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_ifpNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.IFP_NO, "IFPM"));
		fieldToColumnDetailsMap.put("companyNo", configProvider.getColumnStringConfig("COMPANY_NO", "COM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_companyNo",
				configProvider.getColumnStringConfig("COMPANY_NO", "COM"));
		fieldToColumnDetailsMap.put("companyName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "COM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_companyName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "COM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_HOLDER, configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "CON_HOL", "CONTRACT_HOLDER"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_contractHolder", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "CON_HOL", "CONTRACT_HOLDER"));
		fieldToColumnDetailsMap.put("itemNo", configProvider.getColumnStringConfig("ITEM_NO", "IM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_itemNo",
				configProvider.getColumnStringConfig("ITEM_NO", "IM"));
		fieldToColumnDetailsMap.put("itemName", configProvider.getColumnStringConfig("ITEM_NAME", "IM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_itemName",
				configProvider.getColumnStringConfig("ITEM_NAME", "IM"));
		fieldToColumnDetailsMap.put("psNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.PS_NO, "PSM"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.PS_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.PS_NAME, "PS"));
		fieldToColumnDetailsMap.put("rsNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_NO, "RS"));
		fieldToColumnDetailsMap.put("rsName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_NAME, "RS"));

		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList
				.add(new GtnWebServiceOrderByCriteria(GtnFrameworkWebserviceConstant.CMCONTRACT_NAME, "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(cDROrderByClauseList);
		gtnWebServiceSearchQueryConfig.setCountQuerySelectClause(
				GtnWsNsfQueryConstants.GTN_NSF_AVAILABLE_CONTRACTS_COUNT_QUERY_SELECT_CLAUSE);

		gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsNsfQueryConstants.GTN_NSF_AVAILABLE_CONTRACTS_COUNT_QUERY);

		gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsNsfQueryConstants.GTN_NSF_AVAILABLE_CONTRACTS_SEARCH_QUERY);
		gtnWebServiceSearchQueryConfig.setWhereClauseList(
				Arrays.asList(GtnWsNsfQueryConstants.GTN_NSF_AVAILABLE_CONTRACTS_SEARCH_QUERY_WHERE_CLAUSE));
		gtnWebServiceSearchQueryConfig.setCountAliasAtEnd(" A");

		searchQueryConfigMap.put(GtnWsNsfCommonConstants.GTN_NSF_AVAILABLE_CONTRACTS_SEARCH_QUERY_NAME,
				gtnWebServiceSearchQueryConfig);

	}

	public static void loadAvailableCustomersSearchQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.CONTRACT_MASTER_SID, "A"));
		fieldToColumnDetailsMap.put("customerNo", configProvider.getColumnStringConfig("CUSTOMER_NAME", "A"));
		fieldToColumnDetailsMap.put("customerName", configProvider.getColumnStringConfig("CUSTOMER_NO", "A"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_NO_PROPERTYID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NO, "A"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_NAME_PROPERTYID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NAME, "A"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CFP_NO,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NO, "A"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CFP_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NAME, "A"));
		fieldToColumnDetailsMap.put("rn", configProvider.getColumnStringConfig("RN", "A"));
		fieldToColumnDetailsMap.put("cfpContractDetailsSid",
				configProvider.getColumnStringConfig("CFP_CONTRACT_DETAILS_SID", "A"));

		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("A.CUSTOMER_NAME", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsNsfQueryConstants.GTN_NSF_AVAILABLE_CUSTOMER_COUNT_QUERY);

		gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsNsfQueryConstants.GTN_NSF_AVAILABLE_CUSTOMER_COUNT_QUERY);

		searchQueryConfigMap.put(GtnWsNsfCommonConstants.GTN_NSF_AVAILABLE_CUSTOMERS_SEARCH_QUERY_NAME,
				gtnWebServiceSearchQueryConfig);

	}

	public static void loadSalesBaisSelectedCustomersQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, configProvider
				.getColumnIntegerConfig("IMTD_SALES_BASIS_DETAILS_SID", GtnFrameworkWebserviceConstant.IMSBD));
		fieldToColumnDetailsMap.put("customerNo",
				configProvider.getColumnStringConfig("CUSTOMER_NAME", GtnFrameworkWebserviceConstant.IMSBD));
		fieldToColumnDetailsMap.put("customerName",
				configProvider.getColumnStringConfig("CUSTOMER_NO", GtnFrameworkWebserviceConstant.IMSBD));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_NO_PROPERTYID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NO,
						GtnFrameworkWebserviceConstant.IMSBD));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_NAME_PROPERTYID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NAME,
						GtnFrameworkWebserviceConstant.IMSBD));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CFP_NO, configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NO, GtnFrameworkWebserviceConstant.IMSBD));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CFP_NAME, configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NAME, GtnFrameworkWebserviceConstant.IMSBD));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CHECK_RECORD_ID, configProvider.getColumnBooleanConfig(
				GtnFrameworkWebserviceConstant.CHECK_RECORD_COLUMN, GtnFrameworkWebserviceConstant.IMSBD));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.RULE_SID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.CDR_MODEL_SID, GtnFrameworkWebserviceConstant.IMSBD));
		fieldToColumnDetailsMap.put("ruleNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RULE_NO, "CDR"));
		fieldToColumnDetailsMap.put("ruleName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RULE_NAME, "CDR"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.USER_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.USERS_SID_COLUMN, GtnFrameworkWebserviceConstant.IMSBD));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SESSION_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.SESSION_ID, GtnFrameworkWebserviceConstant.IMSBD));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("IMSBD.CUSTOMER_NAME", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(GtnWsNsfQueryConstants.GTN_NSF_SELECTED_CUSTOMERS_COUNT_QUERY);

		gtnWebServiceSearchQueryContext.setSearchQuery(GtnWsNsfQueryConstants.GTN_NSF_SELECTED_CUSTOMERS_SEARCH_QUERY);

		searchQueryConfigMap.put(GtnWsNsfCommonConstants.GTN_NSF_SELECTED_CUSTOMERS_SEARCH_QUERY_NAME,
				gtnWebServiceSearchQueryContext);

	}

	public static void loadAvailableDeductionRSQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnIntegerConfig("RS_MODEL_SID", "RSM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_SUB_TYPE_SYS_ID,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE, "RSM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_CATEGORY_SYS_ID,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.RS_CATEGORY, "RSM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_TYPE_SYS_ID,
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.RS_TYPE, "RSM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_CATEGORY,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_CATEGORY, GtnFrameworkWebserviceConstant.RS_CATEGORY_DES,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put(GtnWsNsfQueryConstants.QUERY_MAPPING_DEDUCTION_CATEGORY_COMP_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_CATEGORY, GtnFrameworkWebserviceConstant.RS_CATEGORY_DES,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_SUB_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE,
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE_DES,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabDeductionSubType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE,
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE_DES,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_TYPE, GtnFrameworkWebserviceConstant.RS_TYPE_DESC,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabDeductionType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_TYPE, GtnFrameworkWebserviceConstant.RS_TYPE_DESC,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));

		gtnWebServiceSearchQueryConfig.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("RS_TYPE.DESCRIPTION", "ASC"));
		gtnWebServiceSearchQueryConfig.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryConfig.setCountQuery(GtnWsNsfQueryConstants.GTN_NSF_AVAILABLE_DEDUCTION_COUNT_QUERY);

		gtnWebServiceSearchQueryConfig.setSearchQuery(GtnWsNsfQueryConstants.GTN_NSF_AVAILABLE_DEDUCTION_SEARCH_QUERY);

		gtnWebServiceSearchQueryConfig
				.setCountQuerySelectClause(GtnWsNsfQueryConstants.GTN_NSF_AVAILABLE_DEDUCTION_COUNT_SELECT_CLAUSE);

		searchQueryConfigMap.put(GtnWsNsfCommonConstants.GTN_NSF_AVAILABLE_DEDUCTIONS_SEARCH_QUERY_NAME,
				gtnWebServiceSearchQueryConfig);

	}

	public static void loadSelectedDeductionRSQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnIntegerConfig("IMTD_DEDUCTION_DETAILS_SID", "IMDD"));

		GtnWsColumnDetailsConfig indicatorColumnDetailsConfig = new GtnWsColumnDetailsConfig("INDICATOR",
				GtnFrameworkWebserviceConstant.STRING, "IMDD");
		indicatorColumnDetailsConfig.setWhereClauseColumn(GtnFrameworkWebserviceConstant.INDICATOR);
		fieldToColumnDetailsMap.put("indicator", indicatorColumnDetailsConfig);
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.RULE_SID,
				configProvider.getColumnBooleanConfig(GtnFrameworkWebserviceConstant.CDR_MODEL_SID, "IMDD"));
		fieldToColumnDetailsMap.put("netSalesRuleNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RULE_NO, "CDRM"));
		fieldToColumnDetailsMap.put("netSalesRuleName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RULE_NAME, "CDRM"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
				configProvider.getColumnBooleanConfig(GtnFrameworkWebserviceConstant.CHECK_RECORD_COLUMN, "IMDD"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.USER_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.USERS_SID_COLUMN, "IMDD"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SESSION_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.SESSION_ID, "IMDD"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_CATEGORY,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_CATEGORY, GtnFrameworkWebserviceConstant.RS_CATEGORY_DES,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_SUB_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE,
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE_DES,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_TYPE, GtnFrameworkWebserviceConstant.RS_TYPE_DESC,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("RS_TYPE.DESCRIPTION", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(GtnWsNsfQueryConstants.GTN_NSF_SELECTED_DEDUCTION_COUNT_QUERY);

		gtnWebServiceSearchQueryContext.setSearchQuery(GtnWsNsfQueryConstants.GTN_NSF_SELECTED_DEDUCTION_SEARCH_QUERY);

		searchQueryConfigMap.put(GtnWsNsfCommonConstants.GTN_NSF_SELECTED_DEDUCTIONS_SEARCH_QUERY_NAME,
				gtnWebServiceSearchQueryContext);

	}

	public static void loadAvailableDeductionContractQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put("contractSystemId",
				configProvider.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.CONTRACT_MASTER_SID, "CM"));
		fieldToColumnDetailsMap.put("rsContractSystemId",
				configProvider.getColumnIntegerConfig("RS_CONTRACT_SID", "RSC"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_NO_PROPERTYID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NO, "CM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabcontractNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NO, "CM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_NAME_PROPERTYID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NAME, "CM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabcontractName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NAME, "CM"));
		fieldToColumnDetailsMap.put("deductionNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_NO, "RSC"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabDeductionNumber",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_NO, "RSC"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_CATEGORY_SYS_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE, "RSC"));
		fieldToColumnDetailsMap.put(GtnWsNsfQueryConstants.QUERY_MAPPING_DEDUCTION_CATEGORY_COMP_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE, "RSC"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_SUB_TYPE_SYS_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_CATEGORY, "RSC"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_TYPE_SYS_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_TYPE, "RSC"));
		fieldToColumnDetailsMap.put("deductionName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_NAME, "RSC"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabDeductionName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_NAME, "RSC"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_TYPE, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.RS_TYPE, "RSC", GtnFrameworkWebserviceConstant.RS_TYPE_DESC));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabDeductionType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_TYPE, "RSC",
						GtnFrameworkWebserviceConstant.RS_TYPE_DESC));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_SUB_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE, "RSC",
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE_DESC));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabDeductionSubType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE, "RSC",
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE_DESC));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_CATEGORY,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_CATEGORY, "RSC",
						GtnFrameworkWebserviceConstant.RS_CATEGORY_DESC));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabDeductionCategory",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_CATEGORY, "RSC",
						GtnFrameworkWebserviceConstant.RS_CATEGORY_DESC));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_CATEGORY,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_CATEGORY, GtnFrameworkWebserviceConstant.RS_CATEGORY_DES,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_SUB_TYPE,

				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE,
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE_DES,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_TYPE, GtnFrameworkWebserviceConstant.RS_TYPE_DESC,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.MARKET_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H",
						GtnFrameworkWebserviceConstant.CONTRACT_TYPE, GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabmarketType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H",
						GtnFrameworkWebserviceConstant.CONTRACT_TYPE, GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("startDate", configProvider.getColumnDateConfig("START_DATE", "CM"));
		fieldToColumnDetailsMap.put("endDate", configProvider.getColumnDateConfig("END_DATE", "CM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_HOLDER,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "COMP"));

		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabcontractHolder",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "COMP"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CFP_NO,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NO, "CFPM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabcfpNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NO, "CFPM"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CFP_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NAME, "CFPM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabcfpName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NAME, "CFPM"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.IFP_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.IFP_NAME, "IFPM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabifpName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.IFP_NAME, "IFPM"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.IFP_NUMBER,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.IFP_NO, "IFPM"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabifpNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.IFP_NO, "IFPM"));
		fieldToColumnDetailsMap.put("psNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.PS_NO, "PS"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabPsNumber",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.PS_NO, "PS"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.PS_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.PS_NAME, "PS"));
		fieldToColumnDetailsMap.put("netSalesFormulaAddView_deductionsTabPsName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.PS_NAME, "PS"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList
				.add(new GtnWebServiceOrderByCriteria(GtnFrameworkWebserviceConstant.CMCONTRACT_NAME, "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(
				GtnWsNsfQueryConstants.GTN_NSF_FORMULA_TYPE_CONTRACT_AVAILABLE_DEDUCTION_COUNT_QUERY + "");

		gtnWebServiceSearchQueryContext.setSearchQuery(
				GtnWsNsfQueryConstants.GTN_NSF_FORMULATYPE_CONTRACT_AVAILABLE_DEDUCTION_SEARCH_QUERY + "");

		searchQueryConfigMap.put(GtnWsNsfCommonConstants.GTN_NSF_FORMULA_TYPE_CONTRACT_SEARCH_QUERY,
				gtnWebServiceSearchQueryContext);

	}

	public static void loadSelectedDeductionContractQueryConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnIntegerConfig("IMTD_DEDUCTION_DETAILS_SID", "IMD"));
		fieldToColumnDetailsMap.put("indicator", configProvider.getColumnStringConfig("INDICATOR", "IMD"));
		fieldToColumnDetailsMap.put("contractSystemId",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_MASTER_SID, "CM"));
		fieldToColumnDetailsMap.put("rsContractSystemId",
				configProvider.getColumnStringConfig("RS_CONTRACT_SID", "RSC"));

		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_NO_PROPERTYID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NO, "CM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_NAME_PROPERTYID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CONTRACT_NAME, "CM"));
		fieldToColumnDetailsMap.put("deductionNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_NO, "RSC"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_CATEGORY_SYS_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE, "RSC"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_SUB_TYPE_SYS_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_CATEGORY, "RSC"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_TYPE_SYS_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_TYPE, "RSC"));
		fieldToColumnDetailsMap.put("deductionName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_NAME, "RSC"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_TYPE, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.RS_TYPE, "RSC", GtnFrameworkWebserviceConstant.RS_TYPE_DESC));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_SUB_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE, "RSC",
						"REBATE_PROGRAM_TYPE_DESC"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_CATEGORY, configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_CATEGORY, "RSC", "RS_CATEGORY_DESC"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_CATEGORY,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_CATEGORY, GtnFrameworkWebserviceConstant.RS_CATEGORY_DES,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_SUB_TYPE,

				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE,
						GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE_DES,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));

		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.DEDUCTION_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_TYPE, GtnFrameworkWebserviceConstant.RS_TYPE_DESC,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.MARKET_TYPE,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "H",
						GtnFrameworkWebserviceConstant.CONTRACT_TYPE, GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("startDate", configProvider.getColumnDateConfig("START_DATE", "CM"));
		fieldToColumnDetailsMap.put("endDate", configProvider.getColumnDateConfig("END_DATE", "CM"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.CONTRACT_HOLDER,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "COMP"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CFP_NO,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NO, "CFPM"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CFP_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.CFP_NAME, "CFPM"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.IFP_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.IFP_NAME, "IFPM"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.IFP_NUMBER,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.IFP_NO, "IFPM"));
		fieldToColumnDetailsMap.put("psNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.PS_NO, "PS"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.PS_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.PS_NAME, "PS"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.RULE_SID,
				configProvider.getColumnBooleanConfig(GtnFrameworkWebserviceConstant.CDR_MODEL_SID, "IMD"));
		fieldToColumnDetailsMap.put("ruleNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RULE_NO, "NS"));
		fieldToColumnDetailsMap.put("ruleName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RULE_NAME, "NS"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
				configProvider.getColumnBooleanConfig(GtnFrameworkWebserviceConstant.CHECK_RECORD_COLUMN, "IMD"));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.USER_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.USERS_SID_COLUMN, "IMD"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SESSION_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.SESSION_ID, "IMD"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList
				.add(new GtnWebServiceOrderByCriteria(GtnFrameworkWebserviceConstant.CMCONTRACT_NAME, "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(
				GtnWsNsfQueryConstants.GTN_NSF_FORMULATYPE_CONTRACT_SELECTED_DEDUCTION_COUNT_QUERY + " ");

		gtnWebServiceSearchQueryContext.setSearchQuery(
				GtnWsNsfQueryConstants.GTN_NSF_FORMULATYPE_CONTRACT_SELECTED_DEDUCTION_SEARCH_QUERY + " ");

		searchQueryConfigMap.put(GtnWsNsfCommonConstants.GTN_NSF_FORMULA_TYPE_CONTRACT_SELECTED_DEDUCTION_QUERY,
				gtnWebServiceSearchQueryContext);

	}

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		return searchQueryConfigMap;
	}

}
