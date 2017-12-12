package com.stpl.gtn.gtn2o.ws.module.contractdashboard.logic;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.module.contractdashboard.controller.GtnWsContractDashboardController;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnSerachResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnWsContractDashboardCommonLogic {

	private Map<String, String> itemColumnNameMap = new HashMap<>();
	private Map<String, String> companyColumnNameMap = new HashMap<>();
	private Map<String, String> companyAdditionColumnNameMap = new HashMap<>();
	private Map<String, String> netSalesFormulaColumnNameMap = new HashMap<>();
	private Map<String, String> cfpColumnNameMap = new HashMap<>();
	private Map<String, String> ifpColumnNameMap = new HashMap<>();
	private Map<String, String> tpColumnNameMap = new HashMap<>();
	private Map<String, String> psColumnNameMap = new HashMap<>();
	private Map<String, String> rsColumnNameMap = new HashMap<>();
	private Map<String, String> ruleColumnNameMap = new HashMap<>();
	private Map<String, String> rpColumnNameMap = new HashMap<>();
	private Map<String, String> dcColumnNameMap = new HashMap<>();
	private static final String COMPANY_NO2 = "companyNo";
	private static final String COMPANY_NO = "COMPANY_NO";
	private static final String RECORD_TYPE = "recordType";
	private static final String IFP_IFP_NO = "ifp.IFP_NO";
	private static final String IFP_IFP_NAME = "ifp.IFP_NAME";
	private static final String IM_ITEM_NAME = "im.ITEM_NAME";
	private static final String COMPANY_ID = "COMPANY_ID";
	private static final String COMPANY_ID1 = "companyId";
	private static final String COMPANY_NAME = "COMPANY_NAME";
	private static final String COMPANY_NAME1 = "companyName";
	private static final String COMPANY_STATUS = "companyStatus";
	private static final String COMPANY_TYPE = "companyType";
	private static final String TRADE_CLASS = "tradeClass";
	private static final String NSF_NET_SALES_FORMULA_NAME = "NSF.NET_SALES_FORMULA_NAME";
	private static final String CM_COMPANY_ID = "cm.COMPANY_ID";
	private static final String CM_COMPANY_NO = "cm.COMPANY_NO";
	private static final String CM_COMPANY_NAME = "cm.COMPANY_NAME";

	public GtnWsContractDashboardCommonLogic() {
		addValueToMap();
		addCompanyColumns();
		addCompanyAdditionColumn();
		addNetSalesFormulaColumn();
		addCfpColumns();
		addIfpColumns();
		addTpColumns();
		addPsColumns();
		addRsColumns();
		addRuleColumns();
		addRpColumns();
		addDcColumns();
	}

	@SuppressWarnings("unchecked")
	public void setGtnSearchResponse(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse, GtnWsContractDashboardController controller,
			List<Object> inputlist, String queryName, String orderByQuery) throws GtnFrameworkGeneralException {
		List<Object[]> result = controller.executeQuery(controller.getQuery(inputlist, queryName) + orderByQuery);
		GtnSerachResponse gtnSerachResponse = new GtnSerachResponse();
		if (gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			gtnSerachResponse.setCount(Integer.valueOf(String.valueOf(result.get(0))));
		} else {
			GtnUIFrameworkDataTable gtnUIFrameworkDataTable = new GtnUIFrameworkDataTable();
			gtnUIFrameworkDataTable.addData(result);
			gtnSerachResponse.setResultSet(gtnUIFrameworkDataTable);
		}
		gtnResponse.setGtnSerachResponse(gtnSerachResponse);
	}

	public void setGtnSearchResponse(GtnUIFrameworkWebserviceRequest gtnWsRequest,
			GtnUIFrameworkWebserviceResponse gtnResponse, GtnWsContractDashboardController controller,
			List<Object> inputlist, String queryName) throws GtnFrameworkGeneralException {
		setGtnSearchResponse(gtnWsRequest, gtnResponse, controller, inputlist, queryName, "");
	}

	public void addInputItemSId(GtnUIFrameworkWebserviceRequest gtnWsRequest, List<Object> inputlist) {
		if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			addInputRecordType(gtnWsRequest, inputlist);
			String orderByColumn = inputlist.get(inputlist.size() - 3).toString();
			if (orderByColumn.startsWith("im")) {
				inputlist.set(inputlist.size() - 3, GtnFrameworkWebserviceConstant.IMTDITEM_MASTER_SID);
			}
		}
	}

	public void addInputRecordType(GtnUIFrameworkWebserviceRequest gtnWsRequest, List<Object> inputlist) {
		if (!gtnWsRequest.getGtnWsSearchRequest().isCount()) {
			String recordType = "";
			if (!gtnWsRequest.getGtnWsSearchRequest().getGtnWebServiceSearchCriteriaList().isEmpty()) {
				GtnWebServiceSearchCriteria searchCriteria = gtnWsRequest.getGtnWsSearchRequest()
						.getGtnWebServiceSearchCriteriaList().get(0);
				if (!searchCriteria.isFilter()) {
					recordType = searchCriteria.getFilterValue1().replace("[", "").replace("]", "");
				}
			}
			inputlist.add(0, recordType);
		}
	}

	public void addUserIdSessionId(GtnUIFrameworkWebserviceRequest gtnWsRequest, List<Object> inputlist) {
		inputlist.add(0, gtnWsRequest.getGtnWsGeneralRequest().getSessionId());
		inputlist.add(0, gtnWsRequest.getGtnWsGeneralRequest().getUserId());
	}

	public String getItemAdditionTabColumns(String property) {
		return itemColumnNameMap.get(property.trim());
	}

	public String getCompanyAdditionTabColumns(String property) {
		return companyAdditionColumnNameMap.get(property.trim());
	}

	public String getCompanyTabColumns(String property) {
		return companyColumnNameMap.get(property.trim());
	}

	public String getNetSalesFormulaColumns(String property) {
		return netSalesFormulaColumnNameMap.get(property.trim());
	}

	public String getCfpColumns(String property) {
		return cfpColumnNameMap.get(property.trim());
	}

	public String getIfpColumns(String property) {
		return ifpColumnNameMap.get(property.trim());
	}

	public String getPsColumns(String property) {
		return psColumnNameMap.get(property.trim());
	}

	public String getRsColumns(String property) {
		return rsColumnNameMap.get(property.trim());
	}

	public String getTpColumns(String property) {
		return tpColumnNameMap.get(property.trim());
	}

	public String getRuleColumns(String property) {
		return ruleColumnNameMap.get(property.trim());
	}

	public String getRpColumns(String property) {
		return rpColumnNameMap.get(property.trim());
	}

	public String getDcColumns(String property) {
		return dcColumnNameMap.get(property.trim());
	}

	private void addValueToMap() {
		itemColumnNameMap.put("IFP No1", IFP_IFP_NO);
		itemColumnNameMap.put("IFP Name1", IFP_IFP_NAME);
		itemColumnNameMap.put("Strength1", "im.STRENGTH");
		itemColumnNameMap.put("Therapeutic Class1", "im.THERAPEUTIC_CLASS");
		itemColumnNameMap.put("Form1", "im.FORM");
		itemColumnNameMap.put("Item Description1", "im.ITEM_DESC");
		itemColumnNameMap.put("itemDesc", "im.ITEM_DESC");
		itemColumnNameMap.put("Item Name1", IM_ITEM_NAME);
		itemColumnNameMap.put("itemName", IM_ITEM_NAME);
		itemColumnNameMap.put("Item No1", GtnFrameworkCommonConstants.IM_ITEM_NO);
		itemColumnNameMap.put(GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.IM_ITEM_NO);
		itemColumnNameMap.put("NDC 81", "im.ndc8");
		itemColumnNameMap.put("NDC 91", "im.ndc9");
		itemColumnNameMap.put("itemStatus", "hstatus.DESCRIPTION");
		itemColumnNameMap.put("form", "hform.DESCRIPTION");
		itemColumnNameMap.put("strength", "hstrength.DESCRIPTION");
		itemColumnNameMap.put("therapeuticClass", "htherapeutic.DESCRIPTION");
		itemColumnNameMap.put("brand", "bm.BRAND_NAME");
		itemColumnNameMap.put("packageSize", "htype.DESCRIPTION");
		itemColumnNameMap.put("checkRecordId", "CHECK_RECORD");
		itemColumnNameMap.put("ifpStatus", "ATTACHED_STATUS");
		itemColumnNameMap.put("IFP Status", "ATTACHED_STATUS");
		itemColumnNameMap.put("ifpStartDate", "START_DATE");
		itemColumnNameMap.put("IFP Start Date", "START_DATE");
		itemColumnNameMap.put("ifpEndDate", "END_DATE");
		itemColumnNameMap.put("IFP End Date", "END_DATE");
		itemColumnNameMap.put("attachedDate", "IMTD.MODIFIED_DATE");
		itemColumnNameMap.put("modifiedDate", "IMTD.MODIFIED_DATE");
		itemColumnNameMap.put("createdDate", "IMTD.CREATED_DATE");
		itemColumnNameMap.put("modifiedBy", "usr.firstName + ' ' + usr.middleName + ' ' + usr.lastName");
		itemColumnNameMap.put("createdBy", "usr1.firstName + ' ' + usr1.middleName + ' ' + usr1.lastName");
		itemColumnNameMap.put("source", "IMTD.\"SOURCE\"");
		itemColumnNameMap.put("Price Type", "PRICE_TYPE");
		itemColumnNameMap.put("PriceType", "PRICE_TYPE");
		itemColumnNameMap.put("Suggested Price", "SUGGESTED_PRICE");
		itemColumnNameMap.put("SuggestedPrice", "SUGGESTED_PRICE");
		itemColumnNameMap.put("Price", "IMTD.PRICE");
		itemColumnNameMap.put("Status", "PS_STATUS");
		itemColumnNameMap.put("CP End Date", "CONTRACT_PRICE_END_DATE");
		itemColumnNameMap.put("CPEndDate", "CONTRACT_PRICE_END_DATE");
		itemColumnNameMap.put("CP Start Date", "CONTRACT_PRICE_START_DATE");
		itemColumnNameMap.put("CPStartDate", "CONTRACT_PRICE_START_DATE");
		itemColumnNameMap.put("priceId", "IMTD.ITEM_ID");
		itemColumnNameMap.put("priceNo", "IMTD.ITEM_NO");
		itemColumnNameMap.put("priceName", "IMTD.ITEM_NAME");
		itemColumnNameMap.put("Price Protection Status", "PRICE_PROTECTION_STATUS");
		itemColumnNameMap.put("PriceProtectionStatus", "PRICE_PROTECTION_STATUS");
		itemColumnNameMap.put("Price Protection Start Date", "PRICE_PROTECTION_START_DATE");
		itemColumnNameMap.put("PriceProtectionStartDate", "PRICE_PROTECTION_START_DATE");
		itemColumnNameMap.put("Price Protection End Date", "PRICE_PROTECTION_END_DATE");
		itemColumnNameMap.put("PriceProtectionEndDate", "PRICE_PROTECTION_END_DATE");
		itemColumnNameMap.put("Measurement Price", GtnFrameworkCommonConstants.PRICE_PROTECTION_PRICE_TYPE);
		itemColumnNameMap.put("PriceProtectionPriceType", GtnFrameworkCommonConstants.PRICE_PROTECTION_PRICE_TYPE);
		itemColumnNameMap.put("nep", "NEP");
		itemColumnNameMap.put("NEP", "NEP");
		itemColumnNameMap.put("NEP Formula", "NEPF.NET_SALES_FORMULA_NAME");
		itemColumnNameMap.put("NEPFormulapopup", "NEPF.NET_SALES_FORMULA_NAME");
		itemColumnNameMap.put("Base Price Type", GtnFrameworkCommonConstants.BASE_PRICE_TYPE_CD);
		itemColumnNameMap.put("BasePriceType", GtnFrameworkCommonConstants.BASE_PRICE_TYPE_CD);
		itemColumnNameMap.put("BasePriceDdlb", GtnFrameworkCommonConstants.BASE_PRICE_DDLB);
		itemColumnNameMap.put("Price Type1", GtnFrameworkCommonConstants.BASE_PRICE_DDLB);
		itemColumnNameMap.put("BasePriceEntry", GtnFrameworkCommonConstants.BASE_PRICE_ENTRY);
		itemColumnNameMap.put("Manual1", GtnFrameworkCommonConstants.BASE_PRICE_ENTRY);
		itemColumnNameMap.put("BasePriceDate", GtnFrameworkCommonConstants.BASE_PRICE_DATE);
		itemColumnNameMap.put("Date1", GtnFrameworkCommonConstants.BASE_PRICE_DATE);
		itemColumnNameMap.put("netBasePrice", GtnFrameworkCommonConstants.NET_BASE_PRICE);
		itemColumnNameMap.put("netBasePriceFormulapopup", "NBPF.NET_SALES_FORMULA_NAME");
		itemColumnNameMap.put("netSubsequentPeriodPrice", GtnFrameworkCommonConstants.NET_SUBSEQUENT_PERIOD_PRICE);
		itemColumnNameMap.put("netSubsequentPriceFormulapopup", "NSPF.NET_SALES_FORMULA_NAME");
		itemColumnNameMap.put("subsequentPeriodPriceType", GtnFrameworkCommonConstants.SUBSEQUENT_PERIOD_PRICE_TYPE);
		itemColumnNameMap.put("Price Tolerance Frequency", "PRICE_TOLERANCE_FREQUENCY");
		itemColumnNameMap.put("PriceToleranceFrequency", "PRICE_TOLERANCE_FREQUENCY");
		itemColumnNameMap.put("Price Tolerance Interval", "PRICE_TOLERANCE_INTERVAL");
		itemColumnNameMap.put("PriceToleranceInterval", "PRICE_TOLERANCE_INTERVAL");
		itemColumnNameMap.put("Price Tolerance Type", "PRICE_TOLERANCE_TYPE");
		itemColumnNameMap.put("PriceToleranceType", "PRICE_TOLERANCE_TYPE");
		itemColumnNameMap.put("Max Incremental Change", "IMTD.MAX_INCREMENTAL_CHANGE");
		itemColumnNameMap.put("MaxIncrementalChange", "IMTD.MAX_INCREMENTAL_CHANGE");
		itemColumnNameMap.put("Price Tolerance", "PRICE_TOLERANCE");
		itemColumnNameMap.put("PriceTolerance", "PRICE_TOLERANCE");
		itemColumnNameMap.put("Reset Eligible", "RESET_ELIGIBLE");
		itemColumnNameMap.put("ResetEligible", "RESET_ELIGIBLE");
		itemColumnNameMap.put("Reset Type", "RESET_TYPE");
		itemColumnNameMap.put("ResetType", "RESET_TYPE");
		itemColumnNameMap.put("Reset Date", "RESET_DATE");
		itemColumnNameMap.put("ResetDate", "RESET_DATE");
		itemColumnNameMap.put("Reset Interval", "RESET_INTERVAL");
		itemColumnNameMap.put("ResetInterval", "RESET_INTERVAL");
		itemColumnNameMap.put("Reset Frequency", "RESET_FREQUENCY");
		itemColumnNameMap.put("ResetFrequency", "RESET_FREQUENCY");
		itemColumnNameMap.put("resetPriceType", GtnFrameworkCommonConstants.RESET_PRICE_TYPE);
		itemColumnNameMap.put("netResetPriceType", GtnFrameworkCommonConstants.NET_RESET_PRICE_TYPE);
		itemColumnNameMap.put("netResetPriceTypeFormulapopup", "NRPF.NET_SALES_FORMULA_NAME");
		itemColumnNameMap.put("Net Price Type", "NET_PRICE_TYPE");
		itemColumnNameMap.put("NetPriceType", "NET_PRICE_TYPE");
		itemColumnNameMap.put("Net Price Type Formula", "NPTF.NET_SALES_FORMULA_NAME");
		itemColumnNameMap.put("NetPriceTypeFormulapopup", "NPTF.NET_SALES_FORMULA_NAME");
		itemColumnNameMap.put("NEP Formula1", "NEP_FORMULA");
		itemColumnNameMap.put("NEPFormulapopup1", "NEP_FORMULA");
		itemColumnNameMap.put("netBasePriceFormulapopup1", GtnFrameworkCommonConstants.NET_BASE_PRICE_FORMULA_ID);
		itemColumnNameMap.put("netSubsequentPriceFormulapopup1", GtnFrameworkCommonConstants.NET_SUBSEQUENT_PRICE_FORMULA_ID);
		itemColumnNameMap.put("netResetPriceTypeFormulapopup1", GtnFrameworkCommonConstants.NET_RESET_PRICE_FORMULA_ID);
		itemColumnNameMap.put("Net Price Type Formula1", "IMTD.NET_PRICE_TYPE_FORMULA");
		itemColumnNameMap.put("NetPriceTypeFormulapopup1", GtnFrameworkCommonConstants.NET_PRICE_TYPE_FORMULA);
		itemColumnNameMap.put("RS Status", "RS_ATTACHED_STATUS");
		itemColumnNameMap.put("attachedStatus", "RS_ATTACHED_STATUS");
		itemColumnNameMap.put("Start Date", "ITEM_REBATE_START_DATE");
		itemColumnNameMap.put("rebateStartDate", "ITEM_REBATE_START_DATE");
		itemColumnNameMap.put("End Date", "ITEM_REBATE_END_DATE");
		itemColumnNameMap.put("rebateEndDate", "ITEM_REBATE_END_DATE");
		itemColumnNameMap.put("Bundle No", "IMTD.BUNDLE_NO");
		itemColumnNameMap.put("rebatePlanBundleNo", "IMTD.BUNDLE_NO");
		itemColumnNameMap.put("rebatePlanNopopup", "RPM.REBATE_PLAN_NO");
		itemColumnNameMap.put("Rebate PlanNo1", "IMTD.REBATE_PLAN_SYSTEM_ID");
		itemColumnNameMap.put("rebatePlanNopopup1", "IMTD.REBATE_PLAN_SYSTEM_ID");
		itemColumnNameMap.put("rebatePlanName", "RPM.REBATE_PLAN_NAME");
		itemColumnNameMap.put("deductionCalendarNopopup", "DC.DEDUCTION_CALENDAR_NO");
		itemColumnNameMap.put("Deduction Calendar No1", "IMTD.DEDUCTION_CALENDAR_MASTER_SID");
		itemColumnNameMap.put("deductionCalendarNopopup1", "IMTD.DEDUCTION_CALENDAR_MASTER_SID");
		itemColumnNameMap.put("deductionCalendarName", "DC.DEDUCTION_CALENDAR_NAME");
		itemColumnNameMap.put("formulaType", "IMTD.FORMULA_TYPE");
		itemColumnNameMap.put("formulaNopopup", "FF.FORMULA_NO");
		itemColumnNameMap.put("Formula No1", "IMTD.FORMULA_ID");
		itemColumnNameMap.put("formulaNopopup1", "IMTD.FORMULA_ID");
		itemColumnNameMap.put("formulaName", "FF.FORMULA_NAME");
		itemColumnNameMap.put("netSalesFormulaNopopup", "NSFM.NET_SALES_FORMULA_NO");
		itemColumnNameMap.put("Net Sales Formula1", "IMTD.NET_SALES_FORMULA_MASTER_SID");
		itemColumnNameMap.put("netSalesFormulaNopopup1", "IMTD.NET_SALES_FORMULA_MASTER_SID");
		itemColumnNameMap.put("netSalesRulepopup", "NET.RULE_NAME");
		itemColumnNameMap.put("Net Sales Rule1", "IMTD.NET_SALES_RULE");
		itemColumnNameMap.put("netSalesRulepopup1", "IMTD.NET_SALES_RULE");
		itemColumnNameMap.put("evaluationRulepopup", "EVA.RULE_NAME");
		itemColumnNameMap.put("Evaluation Rule1", "IMTD.EVALUATION_RULE");
		itemColumnNameMap.put("evaluationRulepopup1", "IMTD.EVALUATION_RULE");
		itemColumnNameMap.put("calculationRulepopup", "CAL.RULE_NAME");
		itemColumnNameMap.put("Calculation Rule1", "IMTD.CALCULATION_RULE");
		itemColumnNameMap.put("calculationRulepopup1", "IMTD.CALCULATION_RULE");
		itemColumnNameMap.put("Evaluation Rule Bundle", "EVALUATION_RULE_BUNDLE");
		itemColumnNameMap.put("evaluationRuleBundle", "EVALUATION_RULE_BUNDLE");
		itemColumnNameMap.put("Calculation Rule Bundle", "IMTD.CALCULATION_RULE_BUNDLE");
		itemColumnNameMap.put("calculationRuleBundle", "IMTD.CALCULATION_RULE_BUNDLE");
		itemColumnNameMap.put("Net Reset Price Formula1", GtnFrameworkCommonConstants.NET_RESET_PRICE_FORMULA_ID);
		itemColumnNameMap.put("NetResetPriceFormula1", GtnFrameworkCommonConstants.NET_RESET_PRICE_FORMULA_ID);
		itemColumnNameMap.put("Net Reset Price Type", GtnFrameworkCommonConstants.NET_RESET_PRICE_TYPE);
		itemColumnNameMap.put("NetResetPriceType1", GtnFrameworkCommonConstants.NET_RESET_PRICE_TYPE);
		itemColumnNameMap.put("Reset Price Type", GtnFrameworkCommonConstants.RESET_PRICE_TYPE);
		itemColumnNameMap.put("ResetPriceType", GtnFrameworkCommonConstants.RESET_PRICE_TYPE);
		itemColumnNameMap.put("Net Subsequent Period Price Formula1", GtnFrameworkCommonConstants.NET_SUBSEQUENT_PRICE_FORMULA_ID);
		itemColumnNameMap.put("NetSubsequentPeriodPriceFormula1", GtnFrameworkCommonConstants.NET_SUBSEQUENT_PRICE_FORMULA_ID);
		itemColumnNameMap.put("Net Subsequent Period Price", GtnFrameworkCommonConstants.NET_SUBSEQUENT_PERIOD_PRICE);
		itemColumnNameMap.put("NetSubsequentPeriodPrice1", GtnFrameworkCommonConstants.NET_SUBSEQUENT_PERIOD_PRICE);
		itemColumnNameMap.put("Subsequent Period Price Type", GtnFrameworkCommonConstants.SUBSEQUENT_PERIOD_PRICE_TYPE);
		itemColumnNameMap.put("SubsequentPeriodPriceType", GtnFrameworkCommonConstants.SUBSEQUENT_PERIOD_PRICE_TYPE);
		itemColumnNameMap.put("Net Baseline WAC Formula1", GtnFrameworkCommonConstants.NET_BASE_PRICE_FORMULA_ID);
		itemColumnNameMap.put("NetBaselineWACFormula1", GtnFrameworkCommonConstants.NET_BASE_PRICE_FORMULA_ID);
		itemColumnNameMap.put("Baseline Net WAC1", GtnFrameworkCommonConstants.NET_BASE_PRICE);
		itemColumnNameMap.put("Baseline Net WAC", GtnFrameworkCommonConstants.NET_BASE_PRICE);
		itemColumnNameMap.put("BaselineNetWAC1", GtnFrameworkCommonConstants.NET_BASE_PRICE);
		itemColumnNameMap.put("MeasurementPrice", GtnFrameworkCommonConstants.PRICE_PROTECTION_PRICE_TYPE);
		itemColumnNameMap.put("BasePriceTypeType", GtnFrameworkCommonConstants.BASE_PRICE_TYPE_CD);
		itemColumnNameMap.put("BasePriceTypeDate", GtnFrameworkCommonConstants.BASE_PRICE_DATE);
		itemColumnNameMap.put("BasePriceTypeEntry", GtnFrameworkCommonConstants.BASE_PRICE_ENTRY);
		itemColumnNameMap.put("BasePriceTypeDdlb", GtnFrameworkCommonConstants.BASE_PRICE_DDLB);
		itemColumnNameMap.put("NetPriceTypeFormula", GtnFrameworkCommonConstants.NET_PRICE_TYPE_FORMULA);
		
	}

	private void addCompanyColumns() {
		companyColumnNameMap.put("companyId1", COMPANY_ID);
		companyColumnNameMap.put(COMPANY_ID1, COMPANY_ID);
		companyColumnNameMap.put("companyName1", COMPANY_NAME);
		companyColumnNameMap.put(COMPANY_NAME1, COMPANY_NAME);
		companyColumnNameMap.put(COMPANY_STATUS, "cstatus");
		companyColumnNameMap.put(COMPANY_TYPE, "ctype");
		companyColumnNameMap.put(TRADE_CLASS, "COMPANY_TRADE_CLASS");
		companyColumnNameMap.put("companyCategory", "ccategory");
		companyColumnNameMap.put("companyGroup", "cgroup");
		companyColumnNameMap.put("companyNo1", COMPANY_NO);
		companyColumnNameMap.put(COMPANY_NO2, COMPANY_NO);
		companyColumnNameMap.put("companyStatus1", "hstatus.DESCRIPTION");
		companyColumnNameMap.put("companyType1", "htype.DESCRIPTION");
		companyColumnNameMap.put("tradeClass1", "htrade.DESCRIPTION");
		companyColumnNameMap.put("companyCategory1", "hcategory.DESCRIPTION");
		companyColumnNameMap.put("companyGroup1", "hgroup.DESCRIPTION");
	}

	private void addCompanyAdditionColumn() {

		companyAdditionColumnNameMap.put(COMPANY_ID1, COMPANY_ID);
		companyAdditionColumnNameMap.put("Company ID", COMPANY_ID);
		companyAdditionColumnNameMap.put(COMPANY_NO2, COMPANY_NO);
		companyAdditionColumnNameMap.put("Company No", COMPANY_NO);
		companyAdditionColumnNameMap.put(COMPANY_NAME1, COMPANY_NAME);
		companyAdditionColumnNameMap.put("Company Name", COMPANY_NAME);
		companyAdditionColumnNameMap.put(COMPANY_STATUS, "cstatus");
		companyAdditionColumnNameMap.put(COMPANY_TYPE, "ctype");
		companyAdditionColumnNameMap.put(TRADE_CLASS, "COMPANY_TRADE_CLASS");
		companyAdditionColumnNameMap.put("companyCategory", "ccategory");
		companyAdditionColumnNameMap.put("companyGroup", "cgroup");
		companyAdditionColumnNameMap.put("Company Type", "COMPANY_TYPE");
		companyAdditionColumnNameMap.put("Company Status", "COMPANY_STATUS");
		companyAdditionColumnNameMap.put("checkRecordId", "CHECK_RECORD");
		companyAdditionColumnNameMap.put("cfpStatus", "CFP_DETAILS_ATTACHED_STATUS");
		companyAdditionColumnNameMap.put("CFP Status", "CFP_DETAILS_ATTACHED_STATUS");
		companyAdditionColumnNameMap.put("cfpStartDate", "CFP_DETAILS_START_DATE");
		companyAdditionColumnNameMap.put("CFP Start Date", "CFP_DETAILS_START_DATE");
		companyAdditionColumnNameMap.put("cfpEndDate", "CFP_DETAILS_END_DATE");
		companyAdditionColumnNameMap.put("CFP End Date", "CFP_DETAILS_END_DATE");
		companyAdditionColumnNameMap.put("cfpAttachedDate", "CFP_DETAILS_ATTACHED_DATE");
		companyAdditionColumnNameMap.put("modifiedDate", "MODIFIED_DATE");
		companyAdditionColumnNameMap.put("createdDate", "CREATED_DATE");
		companyAdditionColumnNameMap.put("modifiedBy", "MODIFIED_BY");
		companyAdditionColumnNameMap.put(RECORD_TYPE, RECORD_TYPE);
		companyAdditionColumnNameMap.put("tradingPartnerContractNo", "tradeContact");
		companyAdditionColumnNameMap.put("createdBy", "CREATED_BY");
	}

	private void addNetSalesFormulaColumn() {

		netSalesFormulaColumnNameMap.put("formulaType", "hft.DESCRIPTION");
		netSalesFormulaColumnNameMap.put("CDNSFormulaView_FormulaID", "NSF.NET_SALES_FORMULA_ID");
		netSalesFormulaColumnNameMap.put("formulaId", "NSF.NET_SALES_FORMULA_ID");
		netSalesFormulaColumnNameMap.put("CDNSFormulaView_FormulaNo", "NSF.NET_SALES_FORMULA_NO");
		netSalesFormulaColumnNameMap.put("formulaNo", "NSF.NET_SALES_FORMULA_NO");
		netSalesFormulaColumnNameMap.put("CDNSFormulaView_FormulaName", NSF_NET_SALES_FORMULA_NAME);
		netSalesFormulaColumnNameMap.put("formulaName", NSF_NET_SALES_FORMULA_NAME);
		netSalesFormulaColumnNameMap.put(GtnFrameworkCommonConstants.CREATED_BY,
				GtnFrameworkWebserviceConstant.USRFIRST_NAME_USRMIDDLE_NAME_USRLAS);
		netSalesFormulaColumnNameMap.put(GtnFrameworkCommonConstants.CREATED_DATE, "NSF.CREATED_DATE");
		netSalesFormulaColumnNameMap.put(GtnFrameworkCommonConstants.MODIFIED_BY,
				GtnFrameworkWebserviceConstant.USR1FIRST_NAME_USR1MIDDLE_NAME_USR1);
		netSalesFormulaColumnNameMap.put(GtnFrameworkCommonConstants.MODIFIED_DATE, "NSF.MODIFIED_DATE");
		netSalesFormulaColumnNameMap.put("CDNSFormulaView_FormulaType", "NSF.NET_SALES_FORMULA_TYPE");
	}

	private void addCfpColumns() {

		cfpColumnNameMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, "cf.CFP_MODEL_SID");
		cfpColumnNameMap.put(GtnFrameworkWebserviceConstant.SYS_ID, GtnFrameworkWebserviceConstant.SYS_ID);
		cfpColumnNameMap.put("CDCFPView_CFPID", "cf.CFP_ID");
		cfpColumnNameMap.put("cfpId", "cf.CFP_ID");
		cfpColumnNameMap.put("CDCFPView_CFPNo", "cf.CFP_NO");
		cfpColumnNameMap.put("cfpNo", "cf.CFP_NO");
		cfpColumnNameMap.put("CDCFPView_CFPName", "cf.CFP_NAME");
		cfpColumnNameMap.put("cfpName", "cf.CFP_NAME");
		cfpColumnNameMap.put("CDCFPView_CompanyID", CM_COMPANY_ID);
		cfpColumnNameMap.put("CDCFPView_CompanyNo", CM_COMPANY_NO);
		cfpColumnNameMap.put("CDCFPView_CompanyName", CM_COMPANY_NAME);
		cfpColumnNameMap.put(GtnFrameworkCommonConstants.CREATED_BY,
				GtnFrameworkWebserviceConstant.USRFIRST_NAME_USRMIDDLE_NAME_USRLAS);
		cfpColumnNameMap.put(GtnFrameworkCommonConstants.CREATED_DATE, "cf.CREATED_DATE");
		cfpColumnNameMap.put(GtnFrameworkCommonConstants.MODIFIED_BY,
				GtnFrameworkWebserviceConstant.USR1FIRST_NAME_USR1MIDDLE_NAME_USR1);
		cfpColumnNameMap.put(GtnFrameworkCommonConstants.MODIFIED_DATE, "cf.MODIFIED_DATE");
		cfpColumnNameMap.put("CDCFPView_CFPType", "cf.CFP_TYPE");
		cfpColumnNameMap.put("CDCFPView_CFPStatus", "cf.CFP_STATUS");
		cfpColumnNameMap.put("cfpType", GtnFrameworkWebserviceConstant.HT_DESCRIPTION);
		cfpColumnNameMap.put("cfpStatus", GtnFrameworkWebserviceConstant.HST_DESCRIPTION);
		cfpColumnNameMap.put("cfpCategory", GtnFrameworkWebserviceConstant.HCT_DESCRIPTION);
		cfpColumnNameMap.put(GtnFrameworkCommonConstants.START_DATE, "cf.CFP_START_DATE");
		cfpColumnNameMap.put(GtnFrameworkCommonConstants.END_DATE, "cf.CFP_END_DATE");
		cfpColumnNameMap.put("cfpDesignation", GtnFrameworkWebserviceConstant.HD_DESCRIPTION);
		cfpColumnNameMap.put("parentCfpId", "pcf.CFP_ID");
		cfpColumnNameMap.put("parentCfpName", "pcf.CFP_NAME");
	}

	private void addIfpColumns() {

		ifpColumnNameMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, "ifp.IFP_MODEL_SID");
		ifpColumnNameMap.put(GtnFrameworkWebserviceConstant.SYS_ID, GtnFrameworkWebserviceConstant.SYS_ID);
		ifpColumnNameMap.put("CDIFPView_IFPID", "ifp.IFP_ID");
		ifpColumnNameMap.put("ifpId", "ifp.IFP_ID");
		ifpColumnNameMap.put("CDIFPView_IFPNo", IFP_IFP_NO);
		ifpColumnNameMap.put("ifpNo", IFP_IFP_NO);
		ifpColumnNameMap.put("CDIFPView_IFPName", IFP_IFP_NAME);
		ifpColumnNameMap.put("ifpName", IFP_IFP_NAME);
		ifpColumnNameMap.put(GtnFrameworkCommonConstants.CREATED_BY,
				GtnFrameworkWebserviceConstant.USRFIRST_NAME_USRMIDDLE_NAME_USRLAS);
		ifpColumnNameMap.put(GtnFrameworkCommonConstants.CREATED_DATE, "ifp.CREATED_DATE");
		ifpColumnNameMap.put("CDIFPView_IFPType", "ifp.IFP_TYPE");
		ifpColumnNameMap.put("CDIFPView_IFPStatus", "ifp.IFP_STATUS");
		ifpColumnNameMap.put("ifpType", GtnFrameworkWebserviceConstant.HT_DESCRIPTION);
		ifpColumnNameMap.put("ifpStatus", GtnFrameworkWebserviceConstant.HST_DESCRIPTION);
		ifpColumnNameMap.put("ifpCategory", GtnFrameworkWebserviceConstant.HCT_DESCRIPTION);
		ifpColumnNameMap.put(GtnFrameworkCommonConstants.START_DATE, "ifp.IFP_START_DATE");
		ifpColumnNameMap.put(GtnFrameworkCommonConstants.END_DATE, "ifp.IFP_END_DATE");
		ifpColumnNameMap.put("ifpDesignation", GtnFrameworkWebserviceConstant.HD_DESCRIPTION);
		ifpColumnNameMap.put("parentIfpId", "ifp.PARENT_IFP_ID");
		ifpColumnNameMap.put("parentIfpName", "ifp.PARENT_IFP_NAME");
	}

	private void addTpColumns() {

		tpColumnNameMap.put("CDTPView_CompanyID", CM_COMPANY_ID);
		tpColumnNameMap.put(COMPANY_ID1, CM_COMPANY_ID);
		tpColumnNameMap.put("CDTPView_CompanyNo", CM_COMPANY_NO);
		tpColumnNameMap.put("companyNo", CM_COMPANY_NO);
		tpColumnNameMap.put("CDTPView_CompanyName", CM_COMPANY_NAME);
		tpColumnNameMap.put(COMPANY_NAME1, CM_COMPANY_NAME);
		tpColumnNameMap.put("CDTPView_CompanyType", "cm.COMPANY_TYPE");
		tpColumnNameMap.put("CDTPView_CompanyStatus", "cm.COMPANY_STATUS");
		tpColumnNameMap.put(COMPANY_TYPE, GtnFrameworkWebserviceConstant.HT_DESCRIPTION);
		tpColumnNameMap.put(COMPANY_STATUS, GtnFrameworkWebserviceConstant.HST_DESCRIPTION);
	}

	private void addPsColumns() {

		psColumnNameMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, "ps.PS_MODEL_SID");
		psColumnNameMap.put(GtnFrameworkWebserviceConstant.SYS_ID, GtnFrameworkWebserviceConstant.SYS_ID);
		psColumnNameMap.put("CDPSView_PSID", "ps.PS_ID");
		psColumnNameMap.put("psId", "ps.PS_ID");
		psColumnNameMap.put("CDPSView_PSNo", "ps.PS_NO");
		psColumnNameMap.put("psNo", "ps.PS_NO");
		psColumnNameMap.put("CDPSView_PSName", "ps.PS_NAME");
		psColumnNameMap.put("psName", "ps.PS_NAME");
		psColumnNameMap.put("CDPSView_ItemID", "im.ITEM_ID");
		psColumnNameMap.put("CDPSView_ItemNo", "im.ITEM_NO");
		psColumnNameMap.put("CDPSView_ItemName", IM_ITEM_NAME);
		psColumnNameMap.put("CDPSView_PSType", "ps.PS_TYPE");
		psColumnNameMap.put("CDPSView_PSStatus", "ps.PS_STATUS");
		psColumnNameMap.put("psType", GtnFrameworkWebserviceConstant.HT_DESCRIPTION);
		psColumnNameMap.put("psStatus", GtnFrameworkWebserviceConstant.HST_DESCRIPTION);
		psColumnNameMap.put("psCategory", GtnFrameworkWebserviceConstant.HCT_DESCRIPTION);
		psColumnNameMap.put(TRADE_CLASS, "htrade.DESCRIPTION");
		psColumnNameMap.put(GtnFrameworkCommonConstants.START_DATE, "ps.PS_START_DATE");
		psColumnNameMap.put(GtnFrameworkCommonConstants.END_DATE, "ps.PS_END_DATE");
		psColumnNameMap.put("psDesignation", GtnFrameworkWebserviceConstant.HD_DESCRIPTION);
		psColumnNameMap.put("parentPsId", "ps.PARENT_PS_ID");
		psColumnNameMap.put("parentPsName", "ps.PARENT_PS_NAME");
	}

	private void addRsColumns() {

		rsColumnNameMap.put(GtnFrameworkCommonConstants.SYSTEM_ID, "rs.RS_MODEL_SID");
		rsColumnNameMap.put(GtnFrameworkWebserviceConstant.SYS_ID, GtnFrameworkWebserviceConstant.SYS_ID);
		rsColumnNameMap.put("CDRSView_RSID", "rs.RS_ID");
		rsColumnNameMap.put("rsId", "rs.RS_ID");
		rsColumnNameMap.put("CDRSView_RSNo", "rs.RS_NO");
		rsColumnNameMap.put("rsNo", "rs.RS_NO");
		rsColumnNameMap.put("CDRSView_RSName", "rs.RS_NAME");
		rsColumnNameMap.put("rsName", "rs.RS_NAME");
		rsColumnNameMap.put("CDRSView_ProgramType", "rs.REBATE_PROGRAM_TYPE");
		rsColumnNameMap.put("CDRSView_RSType", "rs.RS_TYPE");
		rsColumnNameMap.put("CDRSView_RSStatus", "rs.RS_STATUS");
		rsColumnNameMap.put("rsType", GtnFrameworkWebserviceConstant.HT_DESCRIPTION);
		rsColumnNameMap.put("rsStatus", GtnFrameworkWebserviceConstant.HST_DESCRIPTION);
		rsColumnNameMap.put("rsCategory", GtnFrameworkWebserviceConstant.HCT_DESCRIPTION);
		rsColumnNameMap.put(GtnFrameworkCommonConstants.START_DATE, "rs.RS_START_DATE");
		rsColumnNameMap.put(GtnFrameworkCommonConstants.END_DATE, "rs.RS_END_DATE");
		rsColumnNameMap.put("rsDesignation", GtnFrameworkWebserviceConstant.HD_DESCRIPTION);
		rsColumnNameMap.put("parentRsId", "rs.PARENT_RS_ID");
		rsColumnNameMap.put("parentRsName", "rs.PARENT_RS_NAME");
	}

	private void addRuleColumns() {

		ruleColumnNameMap.put("CDNSRuleView_RuleNo", "cdr.RULE_NO");
		ruleColumnNameMap.put("ruleNo", "cdr.RULE_NO");
		ruleColumnNameMap.put("CDNSRuleView_RuleName", "cdr.RULE_NAME");
		ruleColumnNameMap.put("ruleName", "cdr.RULE_NAME");
		ruleColumnNameMap.put("RSNSRuleView_RuleType", "cdr.RULE_TYPE");
		ruleColumnNameMap.put("CDNSRuleView_RuleType", "cdr.RULE_TYPE");
		ruleColumnNameMap.put("CDNSRuleView_RuleCategory", "cdr.RULE_CATEGORY");
		ruleColumnNameMap.put("ruleType", GtnFrameworkWebserviceConstant.HT_DESCRIPTION);
		ruleColumnNameMap.put("lineType", GtnFrameworkWebserviceConstant.HT_DESCRIPTION);
		ruleColumnNameMap.put("ruleCategory", GtnFrameworkWebserviceConstant.HCT_DESCRIPTION);
		ruleColumnNameMap.put("comparison", GtnFrameworkWebserviceConstant.HCT_DESCRIPTION);
		ruleColumnNameMap.put("itemGroupAsso", "higat.DESCRIPTION");
		ruleColumnNameMap.put("keyword", "hkt.DESCRIPTION");
		ruleColumnNameMap.put("keyOperator", "hot.DESCRIPTION");
		ruleColumnNameMap.put("compOperator", "hlot.DESCRIPTION");
		ruleColumnNameMap.put("value", "cdrd.\"VALUE\"");
	}

	private void addRpColumns() {

		rpColumnNameMap.put("CDRPNoView_RPID", "RP.REBATE_PLAN_ID");
		rpColumnNameMap.put("rebatePlanId", "RP.REBATE_PLAN_ID");
		rpColumnNameMap.put("CDRPNoView_RPNo", "RP.REBATE_PLAN_NO");
		rpColumnNameMap.put("rebatePlanNo", "RP.REBATE_PLAN_NO");
		rpColumnNameMap.put("CDRPNoView_RPName", "RP.REBATE_PLAN_NAME");
		rpColumnNameMap.put("rebatePlanName", "RP.REBATE_PLAN_NAME");
		rpColumnNameMap.put("CDRPNoView_RPType", "RP.REBATE_PLAN_TYPE");
		rpColumnNameMap.put("CDRPNoView_RPStatus", "RP.REBATE_PLAN_STATUS");
		rpColumnNameMap.put("rebatePlanType", "HT.DESCRIPTION");
		rpColumnNameMap.put("rebatePlanStatus", "HS.DESCRIPTION");
		rpColumnNameMap.put("rebateStructure", "HST.DESCRIPTION");
		rpColumnNameMap.put("rangeBasedOn", "HR.DESCRIPTION");
		rpColumnNameMap.put("netSalesFormula", NSF_NET_SALES_FORMULA_NAME);
		rpColumnNameMap.put("netSalesRule", "CDR.RULE_NAME");
		rpColumnNameMap.put("rebateBasedOn", "HRB.DESCRIPTION");
		rpColumnNameMap.put(GtnFrameworkCommonConstants.CREATED_DATE, "RP.CREATED_DATE");
		rpColumnNameMap.put(GtnFrameworkCommonConstants.CREATED_BY,
				GtnFrameworkWebserviceConstant.USR1FIRST_NAME_USR1MIDDLE_NAME_USR1);
		rpColumnNameMap.put(GtnFrameworkCommonConstants.MODIFIED_DATE, "RP.MODIFIED_DATE");
		rpColumnNameMap.put(GtnFrameworkCommonConstants.MODIFIED_BY,
				GtnFrameworkWebserviceConstant.USRFIRST_NAME_USRMIDDLE_NAME_USRLAS);
	}

	private void addDcColumns() {

		dcColumnNameMap.put("CDDCNoView_DCDesc", "DCM.DEDUCTION_CALENDAR_DESC");
		dcColumnNameMap.put("dcDesc", "DCM.DEDUCTION_CALENDAR_DESC");
		dcColumnNameMap.put("CDDCNoView_DCNo", "DCM.DEDUCTION_CALENDAR_NO");
		dcColumnNameMap.put("dcNo", "DCM.DEDUCTION_CALENDAR_NO");
		dcColumnNameMap.put("CDDCNoView_DCName", "DCM.DEDUCTION_CALENDAR_NAME");
		dcColumnNameMap.put("dcName", "DCM.DEDUCTION_CALENDAR_NAME");
		dcColumnNameMap.put("CDDCNoView_Category", "DCM.CATEGORY");
		dcColumnNameMap.put("category", "HC.DESCRIPTION");
		dcColumnNameMap.put(GtnFrameworkCommonConstants.CREATED_DATE, "DCM.CREATED_DATE");
		dcColumnNameMap.put(GtnFrameworkCommonConstants.CREATED_BY,
				GtnFrameworkWebserviceConstant.USR1FIRST_NAME_USR1MIDDLE_NAME_USR1);
		dcColumnNameMap.put(GtnFrameworkCommonConstants.MODIFIED_DATE, "DCM.MODIFIED_DATE");
		dcColumnNameMap.put(GtnFrameworkCommonConstants.MODIFIED_BY,
				GtnFrameworkWebserviceConstant.USRFIRST_NAME_USRMIDDLE_NAME_USRLAS);
	}
}
