package org.r2.etl.common.util;

/**
 * Constant Interface for setting the constants.
 * 
 * @author stpl
 * 
 */
public final class Constants {

	/**
	 * The variable used for fail return.
	 */
	public static final String FAIL = "Fail";

	/**
	 * The variable used for success return.
	 */
	public static final String SUCCESS = "Success";

	/** The Constant MESSAGE_BODY. */
	public static final String MESSAGE_BODY = "Hi," + "\n\nPlease find attachment for the Error log." + "\n\nThanks,"
			+ "\nBPI Support";

	/** The Constant APP_KEY. */
	public static final String APP_KEY = "21232f297a57a5a743894a0e4a801fc3";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String MASTER_ETL_HISTORY = "HISTORICAL_LOAD_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String MASTER_ETL = "INCREMENTAL_LOAD_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String COMPANY_MASTER = "COMPANY_MASTER_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String COM_MASTER_INTF = "COMPANY_MASTER_INTERFACE_HISTORY";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String COMPANY_IDENT = "COMPANY_IDENTIFIER_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ITEM_MASTER = "ITEM_MASTER_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ITEM_IDENTIFIER = "ITEM_IDENTIFIER_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ITEM_PRICING = "ITEM_PRICING_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String CONTRACT_HEADER = "CONTRACT_HEADER_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */

	public static final String CONT_MAS_INTF = "CONTRACT_MASTER_INTERFACE_HISTORY";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String CFP = "COMPANY_FAMILYPLAN_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ITEM_FAMILYPLAN = "ITEM_FAMILYPLAN_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String PRICE_SCHEDULE = "PRICE_SCHEDULE_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String REBATE_SCHEDULE = "REBATE_SCHEDULE_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String REBATE_PLAN = "REBATE_PLAN_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ACTUAL_MASTER = "ACTUAL_MASTER_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ITEM_HIERARCHY = "ITEM_HIERARCHY_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String SALES_MASTER = "SALES_MASTER_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String FORE_MASTER = "FORECASTING_MASTER_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String GL_BALANCE = "GL_BALANCE_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String CPI_INDEX = "CPI_INDEX_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String FORMULA_DETAILS = "FORMULA_DETAILS_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String FORMULA_DET_INTF = "FORMULA_DETAILS_INTERFACE_HISTORY";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String MASTER_DATA = "MASTER_DATA_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ITEM_HIE_DEF = "ITEM_HIERARCHY_DEFINITION_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String BEST_PRICE = "BEST_PRICE_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String AVERAGE_SHELF = "AVERAGE_SHELF_LIFE_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String GL_COST_CENTER = "GL_COST_CENTER_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String LOT_MASTER = "LOT_MASTER_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String COMPANY_PARENT = "COMPANY_PARENT_DETAILS_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String COMPANY_TRADE = "COMPANY_TRADE_CLASS_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String COMPANY_PARENT_HIS = "COMPANY_PARENT_INTERFACE_HISTORY";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String COMPANY_TRADE_HIS = "COMPANY_TRADECLASS_INTERFACE_HISTORY";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ACCRUAL_MASTER = "ACCRUAL_MASTER_INBOUND";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ACCRUAL_OUTBOUND = "ACCRUAL_DETAIL_OUTBOUND";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String GL_POSTING = "GL_POSTING_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String DEMAND_ACTUAL = "DEMAND_ACTUAL_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String DEMAND_FORECAST = "DEMAND_FORECAST_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String INVENTORY_ACTUAL_DETAIL = "INVENTORY_WITHDRAWAL_ACTUAL_DETAIL_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String INVENTORY_ACTUAL_SUMMARY = "INVENTORY_WITHDRAWAL_ACTUAL_SUMMARY_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String INVENTORY_PROJECTED_DETAIL = "INVENTORY_WITHDRAWAL_PROJECTED_DETAIL_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String INVENTORY_PROJECTED_SUMMARY = "INVENTORY_WITHDRAWAL_PROJECTED_SUMMARY_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String RETURNS_INTERFACE = "RETURNS_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ADJUSTED_DEMAND_ACTUAL_INTERFACE = "ADJUSTED_DEMAND_ACTUAL_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ADJUSTED_DEMAND_FORECASTINTERFACE = "ADJUSTED_DEMAND_FORECASTINTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String CUSTOMER_GTS_ACTUAL_INTERFACE = "CUSTOMER_GTS_ACTUAL_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String CUSTOMER_GTS_FORECAST_INTERFACE = "CUSTOMER_GTS_FORECAST_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String RETURN_RESERVE_INTERFACE = "RETURN_RESERVE_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String CFF_OUTBOUND_INTERFACE = "CFF_OUTBOUND_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ARP_OUTBOUND_INTERFACE = "ARP_OUTBOUND_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE = "ADJUSTMENT_GTN_DETAIL_OUTBOUND_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE = "ADJUSTMENT_RESERVE_DETAIL_OUTBOUND_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ITEM_UOM_INTERFACE = "ITEM_UOM_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String RETURN_RATE_FORECAST_INTERFACE = "RETURN_RATE_FORECAST_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String TESTING_INTERFACE = "TESTING_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ACTUALS_CHARGEBACK_INTERFACE = "ACTUALS_CHARGEBACK_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ACTUALS_MEDICAID_INTERFACE = "ACTUALS_MEDICAID_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ACTUALS_REBATE_INTERFACE = "ACTUALS_REBATE_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String ACTUALS_COUPON_INTERFACE = "ACTUALS_COUPON_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String PRODUCT_PROD_HIERARCHY_INTERFACE = "PRODUCT_PROD_HIERARCHY_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String PRODUCT_PROD_BW_INTERFACE = "PRODUCT_PROD_BW_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String COMPANY_BUID_INTERFACE = "COMPANY_BUID_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String COMPANY_BUNIT_INTERFACE = "COMPANY_BUNIT_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String COMPANY_BUTYPE_INTERFACE = "COMPANY_BUTYPE_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String PRODUCT_PRODID_INTERFACE = "PRODUCT_PRODID_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String PRODUCT_PROD_INTERFACE = "PRODUCT_PROD_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String PRICING_INTERFACE = "PRICING_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String CONTRACT_CONT_INTERFACE = "CONTRACT_CONT_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String CONTRACT_CPGRP_INTERFACE = "CONTRACT_CPGRP_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String CONTRACT_CPPPO_INTERFACE = "CONTRACT_CPPPO_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String CONTRACT_CPPT_INTERFACE = "CONTRACT_CPPT_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String CONTRACT_CPGRP_PGMMKT_INTERFACE = "CONTRACT_CPGRP_PGMMKT_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String CONTRACT_PRGMMKT_INTERFACE = "CONTRACT_PRGMMKT_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String PSTG_SALES_MASTER_INTERFACE = "PSTG_SALES_MASTER_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String PSTG_CUSTOMER_GTS_ACTUAL_INTERFACE = "PSTG_CUSTOMER_GTS_ACTUAL_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String PSTG_CUSTOMER_GTS_FORECAST_INTERFACE = "PSTG_CUSTOMER_GTS_FORECAST_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String PSTG_RETURNS_INTERFACE = "PSTG_RETURNS_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String CONSUMER_PRICE_INDEX_INTERFACE = "CONSUMER_PRICE_INDEX_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String PSTG_GL_COST_CENTER_INTERFACE = "PSTG_GL_COST_CENTER_INTERFACE";
	/**
	 * variable for the ETLInterface.
	 */
	public static final String UNIT_OF_MEASURE_INTERFACE = "UNIT_OF_MEASURE_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String ACCRUALS_INTERFACE = "ACCRUALS_INTERFACE";

	/**
	 * variable for the ETLInterface.
	 */
	public static final String PSTG_SALES_FORECAST_INTERFACE = "PSTG_SALES_FORECAST_INTERFACE";

	private Constants() {
	}
}
