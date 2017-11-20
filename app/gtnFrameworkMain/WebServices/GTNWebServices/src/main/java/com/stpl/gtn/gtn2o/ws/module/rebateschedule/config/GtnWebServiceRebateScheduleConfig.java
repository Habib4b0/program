/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.rebateschedule.config;

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
import com.stpl.gtn.gtn2o.ws.module.rebateschedule.constants.GtnWsRsQueryConstants;

/**
 *
 * @author Mahesh.James
 */
public class GtnWebServiceRebateScheduleConfig implements GtnWsSearchQueryConfigLoader {

	private static Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();

	static {
		loadSearchQueryConfig();
		loadRSRebateSetUpTabQueryConfig();
		loadRSDeductionCaledarMapping();
		loadRSForecastingFormulaMapping();
		loadRSNetSalesFormulaMapping();
		loadRSEvaluationRuleMapping();
		loadRSNetSalesRuleMapping();
		loadRSCalculationRuleMapping();
		loadRSRebatePlanMapping();
	}

	public static void loadSearchQueryConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnStringConfig("RS_MODEL_SID", "RSM"));
		fieldToColumnDetailsMap.put("rebateScheduleId", configProvider.getColumnStringConfig("RS_ID", "RSM"));
		fieldToColumnDetailsMap.put("pouUpRebateScheduleId", configProvider.getColumnStringConfig("RS_ID", "RSM"));
		fieldToColumnDetailsMap.put("rebateScheduleNo", configProvider.getColumnStringConfig("RS_NO", "RSM"));
		fieldToColumnDetailsMap.put("pouUpRebateScheduleNo", configProvider.getColumnStringConfig("RS_NO", "RSM"));
		fieldToColumnDetailsMap.put("rebateScheduleName", configProvider.getColumnStringConfig("RS_NAME", "RSM"));
		fieldToColumnDetailsMap.put("pouUpRebateScheduleName", configProvider.getColumnStringConfig("RS_NAME", "RSM"));
		fieldToColumnDetailsMap.put("rebateScheduleType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_TYPE, GtnFrameworkWebserviceConstant.RS_TYPE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("pouUpRebateScheduleType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_TYPE, GtnFrameworkWebserviceConstant.RS_TYPE,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateScheduleStatus",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_STATUS, GtnFrameworkWebserviceConstant.RS_STATUS,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("pouUpRebateScheduleStatus",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_STATUS, GtnFrameworkWebserviceConstant.RS_STATUS,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateScheduleProgramType", configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.DESCRIPTION, GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE,
				GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE, GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateProgramType", configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.DESCRIPTION, GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE,
				GtnFrameworkWebserviceConstant.REBATE_PROGRAM_TYPE, GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateScheduleCategory",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_CATEGORY, GtnFrameworkWebserviceConstant.RS_CATEGORY,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("pouUpRebateScheduleCategory",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						GtnFrameworkWebserviceConstant.RS_CATEGORY, GtnFrameworkWebserviceConstant.RS_CATEGORY,
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateScheduleTradeClass",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "RS_TRADE_CLASS",
						"RS_TRADE_CLASS", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateScheduleAliasId", configProvider.getColumnStringConfig("RS_ALIAS", "RSM"));
		fieldToColumnDetailsMap.put("rebateScheduleFrequency",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "REBATE_FREQUENCY",
						"REBATE_FREQUENCY", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateCalendar",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "RS_CALENDAR",
						"RS_CALENDAR", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateCalculationType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "CALCULATION_TYPE",
						"CALCULATION_TYPE", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateCalculationLevel",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "CALCULATION_LEVEL",
						"CALCULATION_LEVEL", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateRuleType",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "REBATE_RULE_TYPE",
						"REBATE_RULE_TYPE", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateRuleAssociation",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						"REBATE_RULE_ASSOCIATION", "REBATE_RULE_ASSOCIATION",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebatePaymentLevel",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "PAYMENT_LEVEL",
						"PAYMENT_LEVEL", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("interestBearingIndicator",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						"INTEREST_BEARING_INDICATOR", "INTEREST_BEARING_INDICATOR",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("interestBearingBasis",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION,
						"INTEREST_BEARING_BASIS", "INTEREST_BEARING_BASIS",
						GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("rebateScheduleDesignation",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, "RS_DESIGNATION",
						"RS_DESIGNATION", GtnFrameworkWebserviceConstant.HELPER_TABLE_SID));
		fieldToColumnDetailsMap.put("paymentGracePeriod",
				configProvider.getColumnStringConfig("PAYMENT_GRACE_PERIOD", "RSM"));
		fieldToColumnDetailsMap.put("parentRebateScheduleID",
				configProvider.getColumnStringConfig("PARENT_RS_ID", "RSM"));
		fieldToColumnDetailsMap.put("parentRebateScheduleName",
				configProvider.getColumnStringConfig("PARENT_RS_NAME", "RSM"));
		fieldToColumnDetailsMap.put("rSTransactionReferenceID",
				configProvider.getColumnStringConfig("RS_TRANS_REF_ID", "RSM"));
		fieldToColumnDetailsMap.put("rSTransactionReferenceName",
				configProvider.getColumnStringConfig("RS_TRANS_REF_NAME", "RSM"));
		fieldToColumnDetailsMap.put("rsUDC1", configProvider.getColumnStringConfig("UDC1", "UDCS"));
		fieldToColumnDetailsMap.put("rsUDC2", configProvider.getColumnStringConfig("UDC2", "UDCS"));
		fieldToColumnDetailsMap.put("rsUDC3", configProvider.getColumnStringConfig("UDC3", "UDCS"));
		fieldToColumnDetailsMap.put("rsUDC4", configProvider.getColumnStringConfig("UDC4", "UDCS"));
		fieldToColumnDetailsMap.put("rsUDC5", configProvider.getColumnStringConfig("UDC5", "UDCS"));
		fieldToColumnDetailsMap.put("rsUDC6", configProvider.getColumnStringConfig("UDC6", "UDCS"));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		// Financial Close Table Load Data Starts
		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("RSM.RS_ID", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(GtnWsRsQueryConstants.GTN_RS_COUNT_QUERY);

		gtnWebServiceSearchQueryContext.setSearchQuery(GtnWsRsQueryConstants.GTN_RS_SEARCH_QUERY);
		gtnWebServiceSearchQueryContext
				.setWhereClauseList(Arrays.asList(GtnWsRsQueryConstants.GTN_RS_DEFAULT_WHERE_CLAUSE));

		searchQueryConfigMap.put("rebateScheduleSearch", gtnWebServiceSearchQueryContext);

	}

	public static void loadRSRebateSetUpTabQueryConfig() {

		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put("createdBy",
				configProvider.getColumnUserConfig("CREATED_BY", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("checkRecordId",
				configProvider.getColumnBooleanConfig("CHECK_RECORD", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("itemNo", configProvider.getColumnStringConfig("ITEM_NO", "IM"));
		fieldToColumnDetailsMap.put("itemName", configProvider.getColumnStringConfig("ITEM_NAME", "IM"));
		fieldToColumnDetailsMap.put("rsStartDate",
				configProvider.getColumnDateConfig("ITEM_REBATE_START_DATE", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("rsEndDate",
				configProvider.getColumnDateConfig("ITEM_REBATE_END_DATE", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("deductionName", configProvider.getColumnStringConfig("DEDUCTION_CALENDAR_NAME",
				GtnFrameworkWebserviceConstant.DEDUCTION_CALENDAR));
		fieldToColumnDetailsMap.put("deductionNo", configProvider.getColumnStringConfig("DEDUCTION_CALENDAR_NO",
				GtnFrameworkWebserviceConstant.DEDUCTION_CALENDAR));
		fieldToColumnDetailsMap.put("deductionSysId", configProvider
				.getColumnStringConfig("DEDUCTION_CALENDAR_MASTER_SID", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("evaluationRuleName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RULE_NAME,
						GtnFrameworkWebserviceConstant.EVALUATION_RULE, GtnFrameworkWebserviceConstant.EVA_RULE_NAME));
		fieldToColumnDetailsMap.put("evaluationRuleBundle", configProvider
				.getColumnStringConfig("EVALUATION_RULE_BUNDLE", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("evaluationRuleSysId", configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.EVALUATION_RULE, GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("calculationRuleName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RULE_NAME,
						GtnFrameworkWebserviceConstant.CALCULATION_RULE, "CA_RULE_NAME"));
		fieldToColumnDetailsMap.put("calculationRuleBundle", configProvider
				.getColumnStringConfig("CALCULATION_RULE_BUNDLE", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("calculationRuleSysId", configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.CALCULATION_RULE, GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("attachedDate",
				configProvider.getColumnDateConfig("CREATED_DATE", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("netSalesRuleName", configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.RULE_NAME, "NETSALES_RULE", "NS_RULE_NAME"));
		fieldToColumnDetailsMap.put("netSalesRuleSysId",
				configProvider.getColumnStringConfig("NET_SALES_RULE", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("netSalesFormulaName", configProvider
				.getColumnStringConfig("RS_DETAILS_NET_SALES_FORMULA_NAME", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("netSalesFormulaSysId", configProvider
				.getColumnStringConfig("NET_SALES_FORMULA_MASTER_SID", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("formulaSysId",
				configProvider.getColumnStringConfig("RS_DETAILS_FORMULA_ID", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("formulaType", configProvider.getColumnStringConfig("RS_DETAILS_FORMULA_TYPE",
				GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("formulaNo",
				configProvider.getColumnStringConfig("RS_DETAILS_FORMULA_NO", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.FORMULA_NAME, configProvider
				.getColumnStringConfig("RS_DETAILS_FORMULA_NAME", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("rebatePlanNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_DETAILS_REBATE_PLAN_NAME,
						GtnFrameworkWebserviceConstant.IMTD_RSD, "REBATE_PLAN_NO"));
		fieldToColumnDetailsMap.put("rebatePlanName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_DETAILS_REBATE_PLAN_NAME,
						GtnFrameworkWebserviceConstant.IMTD_RSD, GtnFrameworkWebserviceConstant.REBATE_PLAN_NAME));
		fieldToColumnDetailsMap.put("rebatePlanSysId", configProvider.getColumnStringConfig("REBATE_PLAN_MASTER_SID",
				GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("rsBundleNo",
				configProvider.getColumnStringConfig("RS_DETAILS_BUNDLE_NO", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("rsStatus", configProvider.getColumnIntegerConfig("RS_DETAILS_ATTACHED_STATUS",
				GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnStringConfig("IMTD_RS_DETAILS_SID", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("userId",
				configProvider.getColumnStringConfig("USERS_SID", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("sessionId",
				configProvider.getColumnStringConfig("SESSION_ID", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("description",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.DESCRIPTION, GtnFrameworkWebserviceConstant.HT));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);

		List<GtnWebServiceOrderByCriteria> cDROrderByClauseList = new ArrayList<>();
		cDROrderByClauseList.add(new GtnWebServiceOrderByCriteria("IM.ITEM_NAME", "ASC"));
		gtnWebServiceSearchQueryContext.setOrderByClause(cDROrderByClauseList);

		gtnWebServiceSearchQueryContext.setCountQuery(" from dbo.IMTD_RS_DETAILS IMTD_RSD\n"
				+ "LEFT JOIN dbo.DEDUCTION_CALENDAR_MASTER DEDUCTION_CALENDAR ON DEDUCTION_CALENDAR.DEDUCTION_CALENDAR_MASTER_SID=IMTD_RSD.DEDUCTION_CALENDAR_MASTER_SID\n"
				+ "LEFT JOIN dbo.CDR_MODEL EVALUATION_RULE ON EVALUATION_RULE.CDR_MODEL_SID=IMTD_RSD.EVALUATION_RULE\n"
				+ "LEFT JOIN dbo.CDR_MODEL CALCULATION_RULE ON CALCULATION_RULE.CDR_MODEL_SID=IMTD_RSD.CALCULATION_RULE\n"
				+ "LEFT JOIN dbo.CDR_MODEL NETSALES_RULE ON NETSALES_RULE.CDR_MODEL_SID=IMTD_RSD.NET_SALES_RULE\n"
				+ "LEFT JOIN dbo.NET_SALES_FORMULA_MASTER NET_SALES_FORMULA ON NET_SALES_FORMULA.NET_SALES_FORMULA_MASTER_SID=IMTD_RSD.NET_SALES_FORMULA_MASTER_SID\n"
				+ "LEFT JOIN dbo.FORECASTING_FORMULA FORMULA ON FORMULA.FORECASTING_FORMULA_SID=IMTD_RSD.RS_DETAILS_FORMULA_ID "
				+ "LEFT JOIN dbo.ITEM_MASTER IM ON IMTD_RSD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID "
                                + "LEFT JOIN HELPER_TABLE HT on HT.HELPER_TABLE_SID=IMTD_RSD.RS_DETAILS_ATTACHED_STATUS\n");

		gtnWebServiceSearchQueryContext.setSearchQuery("  from dbo.IMTD_RS_DETAILS IMTD_RSD\n"
				+ "LEFT JOIN dbo.DEDUCTION_CALENDAR_MASTER DEDUCTION_CALENDAR ON DEDUCTION_CALENDAR.DEDUCTION_CALENDAR_MASTER_SID=IMTD_RSD.DEDUCTION_CALENDAR_MASTER_SID\n"
				+ "LEFT JOIN dbo.CDR_MODEL EVALUATION_RULE ON EVALUATION_RULE.CDR_MODEL_SID=IMTD_RSD.EVALUATION_RULE\n"
				+ "LEFT JOIN dbo.CDR_MODEL CALCULATION_RULE ON CALCULATION_RULE.CDR_MODEL_SID=IMTD_RSD.CALCULATION_RULE\n"
				+ "LEFT JOIN dbo.CDR_MODEL NETSALES_RULE ON NETSALES_RULE.CDR_MODEL_SID=IMTD_RSD.NET_SALES_RULE\n"
				+ "LEFT JOIN dbo.NET_SALES_FORMULA_MASTER NET_SALES_FORMULA ON NET_SALES_FORMULA.NET_SALES_FORMULA_MASTER_SID=IMTD_RSD.NET_SALES_FORMULA_MASTER_SID\n"
				+ "LEFT JOIN dbo.FORECASTING_FORMULA FORMULA ON FORMULA.FORECASTING_FORMULA_SID=IMTD_RSD.RS_DETAILS_FORMULA_ID "
				+ "LEFT JOIN dbo.ITEM_MASTER IM ON IMTD_RSD.ITEM_MASTER_SID=IM.ITEM_MASTER_SID "
                                + "LEFT JOIN HELPER_TABLE HT on HT.HELPER_TABLE_SID=IMTD_RSD.RS_DETAILS_ATTACHED_STATUS\n");

		searchQueryConfigMap.put("rebateScheduleRebateSetup", gtnWebServiceSearchQueryContext);

	}

	public static void loadRSDeductionCaledarMapping() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put("deductionCalendarName", configProvider
				.getColumnStringConfig("DEDUCTION_CALENDAR_NAME", GtnFrameworkWebserviceConstant.DEDUCTION_CALENDAR));
		fieldToColumnDetailsMap.put("deductionCalendarNo", configProvider.getColumnStringConfig("DEDUCTION_CALENDAR_NO",
				GtnFrameworkWebserviceConstant.DEDUCTION_CALENDAR));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, configProvider
				.getColumnStringConfig("DEDUCTION_CALENDAR_MASTER_SID", GtnFrameworkWebserviceConstant.IMTD_RSD));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);
		searchQueryConfigMap.put("Deduction Calendar No", gtnWebServiceSearchQueryContext);
	}

	public static void loadRSForecastingFormulaMapping() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnStringConfig("RS_DETAILS_FORMULA_ID", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("formulaType", configProvider.getColumnStringConfig("RS_DETAILS_FORMULA_TYPE",
				GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("formulaNo",
				configProvider.getColumnStringConfig("RS_DETAILS_FORMULA_NO", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.FORMULA_NAME, configProvider
				.getColumnStringConfig("RS_DETAILS_FORMULA_NAME", GtnFrameworkWebserviceConstant.IMTD_RSD));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);
		searchQueryConfigMap.put("Formula No", gtnWebServiceSearchQueryContext);
	}

	public static void loadRSRebatePlanMapping() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put("rebatePlanNo",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_DETAILS_REBATE_PLAN_NAME,
						GtnFrameworkWebserviceConstant.IMTD_RSD, "REBATE_PLAN_NO"));
		fieldToColumnDetailsMap.put("rebatePlanName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_DETAILS_REBATE_PLAN_NAME,
						GtnFrameworkWebserviceConstant.IMTD_RSD, GtnFrameworkWebserviceConstant.REBATE_PLAN_NAME));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, configProvider
				.getColumnStringConfig("REBATE_PLAN_MASTER_SID", GtnFrameworkWebserviceConstant.IMTD_RSD));
		fieldToColumnDetailsMap.put("secondaryRebatePlanName",
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RS_DETAILS_REBATE_PLAN_NAME,
						GtnFrameworkWebserviceConstant.IMTD_RSD, GtnFrameworkWebserviceConstant.REBATE_PLAN_NAME));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);
		searchQueryConfigMap.put("Rebate Plan No", gtnWebServiceSearchQueryContext);
	}

	public static void loadRSNetSalesFormulaMapping() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.FORMULA_NAME,
				configProvider.getColumnStringConfig("RS_DETAILS_NET_SALES_FORMULA_NAME", "NET_SALES_FORMULA"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, configProvider
				.getColumnStringConfig("NET_SALES_FORMULA_MASTER_SID", GtnFrameworkWebserviceConstant.IMTD_RSD));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);
		searchQueryConfigMap.put("Net Sales Formula", gtnWebServiceSearchQueryContext);
	}

	public static void loadRSNetSalesRuleMapping() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.RULE_NAME_PROPERTY, configProvider
				.getColumnStringConfig(GtnFrameworkWebserviceConstant.RULE_NAME, "NETSALES_RULE", "NS_RULE_NAME"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID,
				configProvider.getColumnStringConfig("NET_SALES_RULE", GtnFrameworkWebserviceConstant.IMTD_RSD));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);
		searchQueryConfigMap.put("Net Sales Rule", gtnWebServiceSearchQueryContext);
	}

	public static void loadRSEvaluationRuleMapping() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.RULE_NAME_PROPERTY,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.EVA_RULE_NAME, GtnFrameworkWebserviceConstant.EVALUATION_RULE,
						GtnFrameworkWebserviceConstant.EVA_RULE_NAME));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.EVALUATION_RULE, GtnFrameworkWebserviceConstant.IMTD_RSD));

		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);
		searchQueryConfigMap.put("Evaluation Rule", gtnWebServiceSearchQueryContext);
	}

	public static void loadRSCalculationRuleMapping() {
		GtnWsSearchQueryConfig gtnWebServiceSearchQueryContext = new GtnWsSearchQueryConfig();
		GtnWsSearchQueryConfigProvider configProvider = GtnWsSearchQueryConfigProvider.getInstance();

		Map<String, GtnWsColumnDetailsConfig> fieldToColumnDetailsMap = new HashMap<>();
		fieldToColumnDetailsMap.put(GtnFrameworkWebserviceConstant.RULE_NAME_PROPERTY,
				configProvider.getColumnStringConfig(GtnFrameworkWebserviceConstant.RULE_NAME,
						GtnFrameworkWebserviceConstant.CALCULATION_RULE, "CA_RULE_NAME"));
		fieldToColumnDetailsMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, configProvider.getColumnStringConfig(
				GtnFrameworkWebserviceConstant.CALCULATION_RULE, GtnFrameworkWebserviceConstant.IMTD_RSD));
		gtnWebServiceSearchQueryContext.setFieldToColumnDetailsMap(fieldToColumnDetailsMap);
		searchQueryConfigMap.put("Calculation Rule", gtnWebServiceSearchQueryContext);
	}

	@Override
	public Map<String, GtnWsSearchQueryConfig> getSearchQueryConfigMap() {
		return searchQueryConfigMap;
	}
}
