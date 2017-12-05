/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.constants;

/**
 *
 * @author Vinoth.Parthasarathy
 */
public class GtnTransactionUIConstants {

	private GtnTransactionUIConstants() {
		super();
	}

	public static final String TRANSACTION_JSON_PATH = "transaction_json/";

	public static final String JSON_TEXTBOX = "text";

	public static final String JSON_COMBOBOX = "combobox";

	public static final String JSON_DATEFIELD = "date";

	public static final String TRANSACTION_VIEW = "view";

	public static final String REMOVE = "Remove";

	public static final String REPROCESS = "Reprocess";

	public static final String ACTUALS = "Actuals";

	public static final String PROJECTIONS = "Projections";

	public static final String SUMMARY = "Summary";

	public static final String DETAILS = "Details";

	public static final String TRANSACTION_JSON_VIEW = "_View";

	public static final String DEMAND = "Demand";

	public static final String ADJUSTED_DEMAND = "Adjusted Demand";

	public static final String CHECK_RECORD = "checkRecord";

	public static final String VIEW_LAYOUT = "transactionViewLayout";

	public static final String COMPONENT_LAYOUT = "companyStatuslayout";

	public static final String SEARCH_TABLE_ID = "searchResultTable";

	public static final String SEARCH_CRITERIA_LAYOUT = "searchCriterialayout";

	public static final String RESULTS_PANEL_LAYOUT = "resultPanelLayout";

	public static final String DEMAND_TYPE_SID = "forecastTypeSid";
	
	public static final String SEARCH_BUTTON_LAYOUT = "searchButtonlayout";
	
	public static final String INVENTORY_TYPE = "inventoryType";

	public static final String INVENTORY_LEVEL = "inventoryLevel";
	

	public static final String REPROCESS_BUTTON_LAYOUT = "reprocessButtonlayout";
	
	private static final String[] VIEW_ENABLE = { "AccrualMaster", "ActualsMaster", "AuditMasterInbound",
			"CpiIndexMaster", "CustomerGtsActual", "ForecastingMaster", "GlBalanceMaster", "ReturnsMaster",
			"SalesMaster", "VwCompanyIdentifier", "VwCompanyMaster", "VwCompanyParentDetails", "VwCompanyTradeClass",
			"VwCustomerGtsForecast", "VwAdjustDemandForecastAct", "VwInventoryWdActualProjMas", "VwItemIdentifier",
			"VwItemMaster", "VwItemPricing", "IvldCustomerGtsForecast", "IvldCustomerGtsActual", "IvldForecastSales",
			"IvldSalesMaster", "IvldCpi", "IvldCompanyMaster", "IvldCompanyParent", "IvldCompanyTradeClass",
			"IvldItemMaster", "IvldItemIdentifier", "IvldItemPricing", "IvldGlBalance", "IvldCompanyIdentifier",
			"VwIvldInventoryWdActualProjMas", "IvldReturns", "IvldAccrualInbound", "IvldAccrualInbound", "IfpModel",
			"IvldIfp", "CfpModel", "IvldCfp", "VwContractHeader", "IvldContractHeader", "PsModel", "IvldPriceSchedule",
			"RebatePlanMaster", "IvldRebatePlan", "IvldRebateSchedule", "VwRebateSchedule", "ReturnRateForecast",
			"IvldReturnRateforecast" ,"ItemUom","IvldItemUom"};

	private static final String[] DATE_FORMAT = { "dd-MMM-yy", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd",
			"yyyy-MM-dd HH:mm:ss.S", "MM/dd/yyyy HH:mm:ss", "dd/MM/yyyy", "MMM dd yyyy hh:mm", "dd/MM/yy", "dd/MM/yyyy",
			"d/M/yy", "d/M/yyyy", "ddMMyy", "ddMMyyyy", "ddMMMyy", "ddMMMyyyy", "dd-MMM-yyyy", "dMMMyy", "dMMMyyyy",
			"d-MMM-yy", "d-MMM-yyyy", "d-MMMM-yy", "d-MMMM-yyyy", "yyMMdd", "yyyyMMdd", "yy/MM/dd", "yyyy/MM/dd",
			"MMddyy", "MMddyyyy", "MM/dd/yy", "MM/dd/yyyy", "MMM-dd-yy", "MMM-dd-yyyy", "yyyy-MM-dd",
			"MMM dd yyyy hh:mm aaa"};

	public static String[] getViewEnable() {
		return VIEW_ENABLE.clone();
	}

	public static String[] getDateFormat() {
		return DATE_FORMAT.clone();
	}

}
