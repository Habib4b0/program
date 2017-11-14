/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.deductioncalendar.config;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoader;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceOrderByCriteria;
import com.stpl.gtn.gtn2o.ws.config.GtnWsColumnDetailsConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfigProvider;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;

/**
 *
 * @author Mahesh.James
 */
public class GtnWebServiceDeductionCalendarConfig implements GtnWsSearchQueryConfigLoader {

	private static Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();

	static {
		loadSearchQueryConfig();
		loadItemSelectedSearchConfig();
		loadItemAvailableSearchConfig();
		loadCustomerSelectedSearchConfig();
		loadCustomerAvailableSearchConfig();
	}

	public static void loadSearchQueryConfig() {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		GtnWsSearchQueryConfig deductionCalendarSearchConfig = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> deductionCalendarSearchConfigMap = new HashMap<>();

		deductionCalendarSearchConfigMap.put("systemId",
				configProvider.getColumnStringConfig("DEDUCTION_CALENDAR_MASTER_SID", "DSM"));
		deductionCalendarSearchConfigMap.put("deductionCalendarNo",
				configProvider.getColumnStringConfig("DEDUCTION_CALENDAR_NO", "DSM"));
		deductionCalendarSearchConfigMap.put("deductionCalendarName",
				configProvider.getColumnStringConfig("DEDUCTION_CALENDAR_NAME", "DSM"));
		deductionCalendarSearchConfigMap.put("deductionCalendarDesc",
				configProvider.getColumnStringConfig("DEDUCTION_CALENDAR_DESC", "DSM"));
		deductionCalendarSearchConfigMap.put("category",
				configProvider.getColumnStringConfig("DESCRIPTION", "catHelper"));
		deductionCalendarSearchConfigMap.put("createdBy", configProvider.getColumnUserConfig("CREATED_BY", "DSM"));
		deductionCalendarSearchConfigMap.put("creationDatas",
				configProvider.getColumnStringConfig("CREATED_DATE", "DSM"));
		deductionCalendarSearchConfigMap.put("modifiedBy", configProvider.getColumnUserConfig("MODIFIED_BY", "DSM"));
		deductionCalendarSearchConfigMap.put("modifiedDatas",
				configProvider.getColumnStringConfig("MODIFIED_DATE", "DSM"));

		deductionCalendarSearchConfig.setFieldToColumnDetailsMap(deductionCalendarSearchConfigMap);
		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("DSM.DEDUCTION_CALENDAR_NAME", "ASC"));
		deductionCalendarSearchConfig.setOrderByClause(cDROrderByClauseList);

		deductionCalendarSearchConfig.setCountQuery(
				"  FROM DEDUCTION_CALENDAR_MASTER DSM LEFT JOIN HELPER_TABLE catHelper on catHelper.HELPER_TABLE_SID=DSM.CATEGORY ");

		deductionCalendarSearchConfig.setSearchQuery(
				"  FROM DEDUCTION_CALENDAR_MASTER DSM LEFT JOIN HELPER_TABLE catHelper on catHelper.HELPER_TABLE_SID=DSM.CATEGORY ");

		searchQueryConfigMap.put("deductionCalendarSearch", deductionCalendarSearchConfig);

	}

	public static void loadCustomerAvailableSearchConfig() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMapAddTab = new HashMap<>();

		addCompayColumnDetails(fieldToColumnDetailsMapAddTab);

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMapAddTab);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("CM.COMPANY_NO", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(
				"  FROM   COMPANY_MASTER CM\n" + "                    LEFT JOIN COMPANY_TRADE_CLASS CTS\n"
						+ "                      ON CTS.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n"
						+ "                    LEFT JOIN HELPER_TABLE HT_CTS\n"
						+ "                           ON HT_CTS.HELPER_TABLE_SID = CTS.COMPANY_TRADE_CLASS\n"
						+ "                    LEFT JOIN HELPER_TABLE HT_CT\n"
						+ "                           ON HT_CT.HELPER_TABLE_SID = CM.COMPANY_TYPE\n"
						+ "                    LEFT JOIN HELPER_TABLE HT_CS\n"
						+ "                           ON HT_CS.HELPER_TABLE_SID = CM.COMPANY_STATUS\n"
						+ "                    LEFT JOIN HELPER_TABLE HT_ORG\n"
						+ "                           ON HT_ORG.HELPER_TABLE_SID = CM.ORGANIZATION_KEY\n"
						+ "                    LEFT JOIN HELPER_TABLE HT_CG\n"
						+ "                           ON HT_CG.HELPER_TABLE_SID = CM.COMPANY_GROUP\n"
						+ "                    LEFT JOIN UDCS UDC\n"
						+ "                           ON UDC.MASTER_SID = CM.COMPANY_MASTER_SID\n"
						+ "                              AND UDC.MASTER_TYPE LIKE 'COMPANY_MASTER'\n"
						+ "                    LEFT JOIN COMPANY_PARENT_DETAILS CPD\n"
						+ "                      ON CPD.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n"
						+ "                    LEFT JOIN COMPANY_MASTER CM1\n"
						+ "                      ON CPD.PARENT_COMPANY_MASTER_SID = CM1.COMPANY_MASTER_SID\n"
						+ "                    LEFT JOIN COMPANY_MASTER CM2\n"
						+ "                           ON CPD.PRIOR_PARENT_CMPY_MASTER_SID = CM2.COMPANY_MASTER_SID");

		gtnWebServiceSearchQueryContext.setSearchQuery(
				"   FROM   COMPANY_MASTER CM\n" + "                    LEFT JOIN COMPANY_TRADE_CLASS CTS\n"
						+ "                      ON CTS.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n"
						+ "                    LEFT JOIN HELPER_TABLE HT_CTS\n"
						+ "                           ON HT_CTS.HELPER_TABLE_SID = CTS.COMPANY_TRADE_CLASS\n"
						+ "                    LEFT JOIN HELPER_TABLE HT_CT\n"
						+ "                           ON HT_CT.HELPER_TABLE_SID = CM.COMPANY_TYPE\n"
						+ "                    LEFT JOIN HELPER_TABLE HT_CS\n"
						+ "                           ON HT_CS.HELPER_TABLE_SID = CM.COMPANY_STATUS\n"
						+ "                    LEFT JOIN HELPER_TABLE HT_ORG\n"
						+ "                           ON HT_ORG.HELPER_TABLE_SID = CM.ORGANIZATION_KEY\n"
						+ "                    LEFT JOIN HELPER_TABLE HT_CG\n"
						+ "                           ON HT_CG.HELPER_TABLE_SID = CM.COMPANY_GROUP\n"
						+ "                    LEFT JOIN UDCS UDC\n"
						+ "                           ON UDC.MASTER_SID = CM.COMPANY_MASTER_SID\n"
						+ "                              AND UDC.MASTER_TYPE LIKE 'COMPANY_MASTER'\n"
						+ "                    LEFT JOIN COMPANY_PARENT_DETAILS CPD\n"
						+ "                      ON CPD.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n"
						+ "                    LEFT JOIN COMPANY_MASTER CM1\n"
						+ "                      ON CPD.PARENT_COMPANY_MASTER_SID = CM1.COMPANY_MASTER_SID\n"
						+ "                    LEFT JOIN COMPANY_MASTER CM2\n"
						+ "                           ON CPD.PRIOR_PARENT_CMPY_MASTER_SID = CM2.COMPANY_MASTER_SID \n");

		searchQueryConfigMap.put("dcAvailableCustomer", gtnWebServiceSearchQueryContext);

	}

	public static void loadCustomerSelectedSearchConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMapAddTab = new HashMap<>();
		addCompayColumnDetails(fieldToColumnDetailsMapAddTab);

		fieldToColumnDetailsMapAddTab.put("userId", configProvider.getColumnStringConfig("USER_ID", "ST"));
		fieldToColumnDetailsMapAddTab.put("sessionId", configProvider.getColumnStringConfig("SESSION_ID", "ST"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMapAddTab);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("CM.COMPANY_NO", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery("  FROM   ST_DEDUCTION_CALENDAR_COMPANY ST\n"
				+ "            JOIN COMPANY_MASTER CM ON ST.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID \n"
				+ "            LEFT JOIN COMPANY_TRADE_CLASS CTS ON CTS.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n"
				+ "             LEFT JOIN HELPER_TABLE HT_CTS ON HT_CTS.HELPER_TABLE_SID = CTS.COMPANY_TRADE_CLASS\n"
				+ "            LEFT JOIN HELPER_TABLE HT_CT ON HT_CT.HELPER_TABLE_SID = CM.COMPANY_TYPE \n"
				+ "            LEFT JOIN HELPER_TABLE HT_CS ON HT_CS.HELPER_TABLE_SID = CM.COMPANY_STATUS \n"
				+ "            LEFT JOIN HELPER_TABLE HT_ORG ON HT_ORG.HELPER_TABLE_SID = CM.ORGANIZATION_KEY \n"
				+ "            LEFT JOIN HELPER_TABLE HT_CG ON HT_CG.HELPER_TABLE_SID = CM.COMPANY_GROUP \n"
				+ "            LEFT JOIN UDCS UDC ON UDC.MASTER_SID = CM.COMPANY_MASTER_SID AND UDC.MASTER_TYPE LIKE 'COMPANY_MASTER' \n"
				+ "            LEFT JOIN COMPANY_PARENT_DETAILS CPD ON CPD.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID \n"
				+ "            LEFT JOIN COMPANY_MASTER CM1 ON CPD.PARENT_COMPANY_MASTER_SID = CM1.COMPANY_MASTER_SID \n"
				+ "            LEFT JOIN COMPANY_MASTER CM2 ON CPD.PRIOR_PARENT_CMPY_MASTER_SID = CM2.COMPANY_MASTER_SID  ");

		gtnWebServiceSearchQueryContext.setSearchQuery("   FROM   ST_DEDUCTION_CALENDAR_COMPANY ST\n"
				+ "            JOIN COMPANY_MASTER CM ON ST.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID \n"
				+ "            LEFT JOIN COMPANY_TRADE_CLASS CTS ON CTS.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID\n"
				+ "             LEFT JOIN HELPER_TABLE HT_CTS ON HT_CTS.HELPER_TABLE_SID = CTS.COMPANY_TRADE_CLASS\n"
				+ "            LEFT JOIN HELPER_TABLE HT_CT ON HT_CT.HELPER_TABLE_SID = CM.COMPANY_TYPE \n"
				+ "            LEFT JOIN HELPER_TABLE HT_CS ON HT_CS.HELPER_TABLE_SID = CM.COMPANY_STATUS \n"
				+ "            LEFT JOIN HELPER_TABLE HT_ORG ON HT_ORG.HELPER_TABLE_SID = CM.ORGANIZATION_KEY \n"
				+ "            LEFT JOIN HELPER_TABLE HT_CG ON HT_CG.HELPER_TABLE_SID = CM.COMPANY_GROUP \n"
				+ "            LEFT JOIN UDCS UDC ON UDC.MASTER_SID = CM.COMPANY_MASTER_SID AND UDC.MASTER_TYPE LIKE 'COMPANY_MASTER' \n"
				+ "            LEFT JOIN COMPANY_PARENT_DETAILS CPD ON CPD.COMPANY_MASTER_SID = CM.COMPANY_MASTER_SID \n"
				+ "            LEFT JOIN COMPANY_MASTER CM1 ON CPD.PARENT_COMPANY_MASTER_SID = CM1.COMPANY_MASTER_SID \n"
				+ "            LEFT JOIN COMPANY_MASTER CM2 ON CPD.PRIOR_PARENT_CMPY_MASTER_SID = CM2.COMPANY_MASTER_SID   \n");

		searchQueryConfigMap.put("dcSelectedCustomer", gtnWebServiceSearchQueryContext);

	}

	private static void addCompayColumnDetails(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMapAddTab) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMapAddTab.put(GtnFrameworkWebserviceConstant.ORGANIZATION_KEY_COLUMN,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ORGANIZATION_KEY_COLUMN, "CM"));
		fieldToColumnDetailsMapAddTab.put(GtnFrameworkWebserviceConstant.CUSTOMER_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_ID, "CM"));
		fieldToColumnDetailsMapAddTab.put(GtnFrameworkWebserviceConstant.CUSTOMER_NO,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "CM"));
		fieldToColumnDetailsMapAddTab.put(GtnFrameworkWebserviceConstant.CUSTOMER_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "CM"));

		fieldToColumnDetailsMapAddTab.put("tradeClass", configProvider.getColumnStringConfig("HELPER_TABLE_SID",
				"HT_CTS", "C_TRADE_CLASS", GtnFrameworkWebserviceConstant.DESCRIPTION));

		fieldToColumnDetailsMapAddTab.put("tradeClassStartDate",
				configProvider.getColumnDateConfig("TRADE_CLASS_START_DATE", "CTS"));
		fieldToColumnDetailsMapAddTab.put("tradeClassEndDate",
				configProvider.getColumnDateConfig("TRADE_CLASS_END_DATE", "CTS"));
		fieldToColumnDetailsMapAddTab.put("customerType", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "HT_CT", "CUSTOMER_TYPE"));
		fieldToColumnDetailsMapAddTab.put("customerStatus", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "HT_CS", "CUSTOMER_STATUS"));
		fieldToColumnDetailsMapAddTab.put("lives", configProvider.getColumnStringConfig("LIVES", "CM"));
		fieldToColumnDetailsMapAddTab.put("customerEndDate",
				configProvider.getColumnDateConfig("COMPANY_END_DATE", "CM"));
		fieldToColumnDetailsMapAddTab.put("customerGroup", configProvider.getColumnStringConfig("COMPANY_GROUP", "CM"));
		fieldToColumnDetailsMapAddTab.put("financialSystem",
				configProvider.getColumnStringConfig("FINANCIAL_SYSTEM", "CM"));
		fieldToColumnDetailsMapAddTab.put("address1", configProvider.getColumnStringConfig("ADDRESS1", "CM"));
		fieldToColumnDetailsMapAddTab.put("address2", configProvider.getColumnStringConfig("ADDRESS2", "CM"));
		fieldToColumnDetailsMapAddTab.put("city", configProvider.getColumnStringConfig("CITY", "CM"));
		fieldToColumnDetailsMapAddTab.put("state",
				configProvider.getColumnStringConfig("STATE", "CM", "CUSTOMER_STATE"));
		fieldToColumnDetailsMapAddTab.put("zipCode", configProvider.getColumnStringConfig("ZIP_CODE", "CM"));
		fieldToColumnDetailsMapAddTab.put("country", configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.COUNTRY, "CM", GtnFrameworkWebserviceConstant.COUNTRY));
		fieldToColumnDetailsMapAddTab.put("regionCode", configProvider.getColumnStringConfig("REGION_CODE", "CM"));
		fieldToColumnDetailsMapAddTab.put("parentCustomerNo", configProvider
				.getColumnIntegerConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "CM1", "PARENT_CUSTOMER_NO"));
		fieldToColumnDetailsMapAddTab.put("parentStartDate",
				configProvider.getColumnDateConfig("PARENT_START_DATE", "CPD"));
		fieldToColumnDetailsMapAddTab.put("parentEndDate",
				configProvider.getColumnDateConfig("PARENT_END_DATE", "CPD"));
		fieldToColumnDetailsMapAddTab.put("customerStartDate",
				configProvider.getColumnDateConfig("COMPANY_START_DATE", "CM"));
		fieldToColumnDetailsMapAddTab.put("priorParentStartDate",
				configProvider.getColumnDateConfig("PRIOR_PARENT_START_DATE", "CPD"));
		fieldToColumnDetailsMapAddTab.put("priorParentCustomerNo", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "CM2", "PRIOR_PARENT_CUSTOMER_NO"));

		fieldToColumnDetailsMapAddTab.put("udc1",
				configProvider.getColumnStringConfig("UDC1", "UDC", GtnFrameworkWebserviceConstant.C_UD_C1));
		fieldToColumnDetailsMapAddTab.put("udc2",
				configProvider.getColumnStringConfig("UDC2", "UDC", GtnFrameworkWebserviceConstant.C_UD_C2));
		fieldToColumnDetailsMapAddTab.put("udc3",
				configProvider.getColumnStringConfig("UDC3", "UDC", GtnFrameworkWebserviceConstant.C_UD_C3));
		fieldToColumnDetailsMapAddTab.put("udc4",
				configProvider.getColumnStringConfig("UDC4", "UDC", GtnFrameworkWebserviceConstant.C_UD_C4));
		fieldToColumnDetailsMapAddTab.put("udc5",
				configProvider.getColumnStringConfig("UDC5", "UDC", GtnFrameworkWebserviceConstant.C_UD_C5));
		fieldToColumnDetailsMapAddTab.put("udc6",
				configProvider.getColumnStringConfig("UDC6", "UDC", GtnFrameworkWebserviceConstant.C_UD_C6));
		fieldToColumnDetailsMapAddTab.put("companyMasterSid",
				configProvider.getColumnIntegerConfig("COMPANY_MASTER_SID", "CM"));
		fieldToColumnDetailsMapAddTab.put("companyTradeClass",
				configProvider.getColumnIntegerConfig("COMPANY_TRADE_CLASS_SID", "CTS"));
		fieldToColumnDetailsMapAddTab.put("parentCompanyMasterSid",
				configProvider.getColumnIntegerConfig("COMPANY_PARENT_DETAILS_SID", "CPD"));
	}

	private static void addItemColumnDetails(Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMapAddTab) {
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		fieldToColumnDetailsMapAddTab.put("itemId", configProvider.getColumnStringConfig("ITEM_ID", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemNo", configProvider.getColumnStringConfig("ITEM_NO", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemCode", configProvider.getColumnStringConfig("ITEM_CODE", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemName", configProvider.getColumnStringConfig("ITEM_NAME", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemDesc", configProvider.getColumnStringConfig("ITEM_DESC", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemStartDate", configProvider.getColumnDateConfig("ITEM_START_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemEndDate", configProvider.getColumnDateConfig("ITEM_END_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemStatus", configProvider.getColumnHelperConfig("ITEM_STATUS", "IM"));
		fieldToColumnDetailsMapAddTab.put("therapeuticClass",
				configProvider.getColumnHelperConfig("THERAPEUTIC_CLASS", "IM"));
		fieldToColumnDetailsMapAddTab.put("brand", configProvider.getColumnStringConfig("BRAND_NAME", "BM"));
		fieldToColumnDetailsMapAddTab.put(GtnFrameworkWebserviceConstant.CUSTOMER_ID,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_ID, "CM"));
		fieldToColumnDetailsMapAddTab.put(GtnFrameworkWebserviceConstant.CUSTOMER_NO,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NO, "CM"));
		fieldToColumnDetailsMapAddTab.put(GtnFrameworkWebserviceConstant.CUSTOMER_NAME,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.COMPANY_NAME, "CM"));
		fieldToColumnDetailsMapAddTab.put("form", configProvider.getColumnHelperConfig("FORM", "IM"));
		fieldToColumnDetailsMapAddTab.put("strength", configProvider.getColumnHelperConfig("STRENGTH", "IM"));
		fieldToColumnDetailsMapAddTab.put("packageSizeCode",
				configProvider.getColumnStringConfig("PACKAGE_SIZE_CODE", "IM"));
		fieldToColumnDetailsMapAddTab.put("packageSizeIntroDate",
				configProvider.getColumnDateConfig("PACKAGE_SIZE_INTRO_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("upps", configProvider.getColumnBigDecimalConfig("UPPS", "IM"));
		fieldToColumnDetailsMapAddTab.put("manufacturerId",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.MANUFACTURER_ID, "IM"));
		fieldToColumnDetailsMapAddTab.put("manufacturerNo", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.MANUFACTURER_ID, "IM", "MANUFACTURER_NO"));
		fieldToColumnDetailsMapAddTab.put("manufacturerName", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.MANUFACTURER_ID, "IM", "MANUFACTURER_NAME"));
		fieldToColumnDetailsMapAddTab.put("labelerCode", configProvider.getColumnStringConfig("LABELER_CODE", "IM"));
		fieldToColumnDetailsMapAddTab.put(GtnFrameworkWebserviceConstant.ORGANIZATION_KEY_COLUMN,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.ORGANIZATION_KEY_COLUMN, "IM"));
		fieldToColumnDetailsMapAddTab.put("acquisitionDate",
				configProvider.getColumnDateConfig("ACQUISITION_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("authorizedGeneric",
				configProvider.getColumnStringConfig("AUTHORIZED_GENERIC", "IM"));
		fieldToColumnDetailsMapAddTab.put("authorizedGenericStartDate",
				configProvider.getColumnDateConfig("AUTHORIZED_GENERIC_START_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("authorizedGenericEndDate",
				configProvider.getColumnDateConfig("AUTHORIZED_GENERIC_END_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("firstSaleDate", configProvider.getColumnDateConfig("FIRST_SALE_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemTypeIndicator",
				configProvider.getColumnHelperConfig("ITEM_TYPE_INDICATION", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemClass", configProvider.getColumnHelperConfig("ITEM_CLASS", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemType", configProvider.getColumnHelperConfig("ITEM_TYPE", "IM"));
		fieldToColumnDetailsMapAddTab.put("marketTerminationDate",
				configProvider.getColumnDateConfig("MARKET_TERMINATION_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("newFormulationIndicator",
				configProvider.getColumnStringConfig("NEW_FORMULATION_INDICATOR", "IM"));
		fieldToColumnDetailsMapAddTab.put("newFormulation",
				configProvider.getColumnStringConfig("NEW_FORMULATION", "IM"));
		fieldToColumnDetailsMapAddTab.put("newFormulationStartDate",
				configProvider.getColumnDateConfig("NEW_FORMULATION_START_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("newFormulationEndDate",
				configProvider.getColumnDateConfig("NEW_FORMULATION_END_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("pediatricExclusiveIndicator",
				configProvider.getColumnStringConfig("PEDIATRIC_EXCLUSIVE_INDICATOR", "IM"));
		fieldToColumnDetailsMapAddTab.put("pediatricExclusiveStartDate",
				configProvider.getColumnDateConfig("PEDIATRIC_EXCLUSIVE_START_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("pediatricExclusiveEndDate",
				configProvider.getColumnDateConfig("PEDIATRIC_EXCLUSIVE_END_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("clottingFactorIndicator",
				configProvider.getColumnStringConfig("CLOTTING_FACTOR_INDICATOR", "IM"));
		fieldToColumnDetailsMapAddTab.put("clottingFactorStartDate",
				configProvider.getColumnDateConfig("CLOTTING_FACTOR_START_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("clottingFactorEndDate",
				configProvider.getColumnDateConfig("CLOTTING_FACTOR_END_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("primaryUom", configProvider.getColumnDateConfig("PRIMARY_UOM", "IM"));
		fieldToColumnDetailsMapAddTab.put("secondaryUom", configProvider.getColumnDateConfig("SECONDARY_UOM", "IM"));
		fieldToColumnDetailsMapAddTab.put("shelfLife", configProvider.getColumnStringConfig("SHELF_LIFE", "IM"));
		fieldToColumnDetailsMapAddTab.put("shelfLifeType",
				configProvider.getColumnHelperConfig("SHELF_LIFE_TYPE", "IM"));
		fieldToColumnDetailsMapAddTab.put("dualPricingIndicator",
				configProvider.getColumnStringConfig("DUAL_PRICING_INDICATOR", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemFamilyId", configProvider.getColumnStringConfig("ITEM_FAMILY_ID", "IM"));
		fieldToColumnDetailsMapAddTab.put("udc1",
				configProvider.getColumnStringConfig("UDC1", "UDCS", GtnFrameworkWebserviceConstant.C_UD_C1));
		fieldToColumnDetailsMapAddTab.put("udc2",
				configProvider.getColumnStringConfig("UDC2", "UDCS", GtnFrameworkWebserviceConstant.C_UD_C2));
		fieldToColumnDetailsMapAddTab.put("udc3",
				configProvider.getColumnStringConfig("UDC3", "UDCS", GtnFrameworkWebserviceConstant.C_UD_C3));
		fieldToColumnDetailsMapAddTab.put("udc4",
				configProvider.getColumnStringConfig("UDC4", "UDCS", GtnFrameworkWebserviceConstant.C_UD_C4));
		fieldToColumnDetailsMapAddTab.put("udc5",
				configProvider.getColumnStringConfig("UDC5", "UDCS", GtnFrameworkWebserviceConstant.C_UD_C5));
		fieldToColumnDetailsMapAddTab.put("udc6",
				configProvider.getColumnStringConfig("UDC6", "UDCS", GtnFrameworkWebserviceConstant.C_UD_C6));
		fieldToColumnDetailsMapAddTab.put("acquiredAmp",
				configProvider.getColumnBigDecimalConfig("ACQUIRED_AMP", "IM"));
		fieldToColumnDetailsMapAddTab.put("acquiredBamp",
				configProvider.getColumnBigDecimalConfig("ACQUIRED_BAMP", "IM"));
		fieldToColumnDetailsMapAddTab.put("obraBamp", configProvider.getColumnBigDecimalConfig("OBRA_BAMP", "IM"));
		fieldToColumnDetailsMapAddTab.put("dra", configProvider.getColumnBigDecimalConfig("DRA", "IM"));
		fieldToColumnDetailsMapAddTab.put("dosesPerUnit", configProvider.getColumnStringConfig("DOSES_PER_UNIT", "IM"));
		fieldToColumnDetailsMapAddTab.put("discontinuationDate",
				configProvider.getColumnDateConfig("DISCONTINUATION_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("lastLotExpirationDate",
				configProvider.getColumnDateConfig("LAST_LOT_EXPIRATION_DATE", "IM"));
		fieldToColumnDetailsMapAddTab.put("ndc9", configProvider.getColumnStringConfig("NDC9", "IM"));
		fieldToColumnDetailsMapAddTab.put("ndc8", configProvider.getColumnStringConfig("NDC8", "IM"));
		fieldToColumnDetailsMapAddTab.put("displayBrand",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DISPLAY_BRAND, "BM"));
		fieldToColumnDetailsMapAddTab.put("innovatorCode", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.DISPLAY_BRAND, "BM", "INNOVATOR_CODE"));
		fieldToColumnDetailsMapAddTab.put("baselineAmp",
				configProvider.getColumnBigDecimalConfig("BASELINE_AMP", "IM"));
		fieldToColumnDetailsMapAddTab.put("baseYearCpi", configProvider.getColumnBigDecimalConfig("BASE_CPI", "IM"));
		fieldToColumnDetailsMapAddTab.put("itemMasterSid",
				configProvider.getColumnIntegerConfig("ITEM_MASTER_SID", "IM"));
	}

	public static void loadItemAvailableSearchConfig() {

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMapAddTab = new HashMap<>();
		addItemColumnDetails(fieldToColumnDetailsMapAddTab);

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMapAddTab);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("IM.ITEM_NO", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(
				"  FROM ITEM_MASTER IM \n" + GtnFrameworkWebserviceConstant.LEFT_JOIN_COMPANY_MASTER_CM_ON_CMCOMPANY
						+ GtnFrameworkWebserviceConstant.LEFT_JOIN_UDCS_UDCS_ON_UDCSMASTER_SIDIMIT
						+ "LEFT join dbo.BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID ");

		gtnWebServiceSearchQueryContext.setSearchQuery(
				"   FROM ITEM_MASTER IM \n" + GtnFrameworkWebserviceConstant.LEFT_JOIN_COMPANY_MASTER_CM_ON_CMCOMPANY
						+ GtnFrameworkWebserviceConstant.LEFT_JOIN_UDCS_UDCS_ON_UDCSMASTER_SIDIMIT
						+ "LEFT join dbo.BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID   \n");

		searchQueryConfigMap.put("dcAvailableItem", gtnWebServiceSearchQueryContext);

	}

	public static void loadItemSelectedSearchConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();
		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMapAddTab = new HashMap<>();
		addItemColumnDetails(fieldToColumnDetailsMapAddTab);

		fieldToColumnDetailsMapAddTab.put("userId", configProvider.getColumnStringConfig("USER_ID", "ST"));
		fieldToColumnDetailsMapAddTab.put("sessionId", configProvider.getColumnStringConfig("SESSION_ID", "ST"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMapAddTab);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("IM.ITEM_NO", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery("  FROM ST_DEDUCTION_CALENDAR_ITEM ST "
				+ "JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID=ST.ITEM_MASTER_SID \n"
				+ GtnFrameworkWebserviceConstant.LEFT_JOIN_COMPANY_MASTER_CM_ON_CMCOMPANY
				+ GtnFrameworkWebserviceConstant.LEFT_JOIN_UDCS_UDCS_ON_UDCSMASTER_SIDIMIT
				+ "LEFT join dbo.BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID ");

		gtnWebServiceSearchQueryContext.setSearchQuery("    FROM ST_DEDUCTION_CALENDAR_ITEM ST "
				+ "JOIN ITEM_MASTER IM ON IM.ITEM_MASTER_SID=ST.ITEM_MASTER_SID \n"
				+ GtnFrameworkWebserviceConstant.LEFT_JOIN_COMPANY_MASTER_CM_ON_CMCOMPANY
				+ GtnFrameworkWebserviceConstant.LEFT_JOIN_UDCS_UDCS_ON_UDCSMASTER_SIDIMIT
				+ "LEFT join dbo.BRAND_MASTER BM ON BM.BRAND_MASTER_SID=IM.BRAND_MASTER_SID   \n");

		searchQueryConfigMap.put("dcSelectedItem", gtnWebServiceSearchQueryContext);

	}

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		return searchQueryConfigMap;
	}
}
