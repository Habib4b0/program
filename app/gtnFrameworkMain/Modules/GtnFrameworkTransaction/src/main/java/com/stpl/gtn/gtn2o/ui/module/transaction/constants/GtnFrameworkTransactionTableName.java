package com.stpl.gtn.gtn2o.ui.module.transaction.constants;

public enum GtnFrameworkTransactionTableName {
	GTS_FORECAST("VwCustomerGtsForecast"), ACCRUAL_DETAILS("AccrualMaster"), AVERAGE_SHELF_LIFE(
			"AverageShelfLifeMaster"), GLOBAL_FILES_COMPANY_IDENTIFIER("VwCompanyIdentifier"), SALES_FORECAST(
					"ForecastingMaster"), GTS_ACTUAL("CustomerGtsActual"), SALES_ACTUAL("SalesMaster"), LOT_MASTER(
							"LotMaster"), CPI("CpiIndexMaster"), RETURNS("ReturnsMaster"), GLOBAL_FILES_COMPANY_MASTER(
									"VwCompanyMaster"), GLOBAL_FILES_COMPANY_PARENT(
											"VwCompanyParentDetails"), GLOBAL_FILES_COMPANY_TRADE_CLASS(
													"VwCompanyTradeClass"), GLOBAL_FILES_ITEM_MASTER(
															"VwItemMaster"), GLOBAL_FILES_ITEM_IDENTIFIER(
																	"VwItemIdentifier"), GLOBAL_FILES_ITEM_PRICING(
																			"VwItemPricing"), GL_BALANCE(
																					"GlBalanceMaster"), GL_COST_CENTER(
																							"GlCostCenterMaster"), INVENTORY_WITHDRAWAL(
																									"VwInventoryWdActualProjMas"), DEMAND(
																											"VwAdjustDemandForecastAct"), INVALID_INTEGRATION(
																													"InvalidIntegration"), PAYMENTS(
																															"ActualsMaster"), GLOBAL_FILES_ITEM_FAMILY_PLAN(
																																	"IfpModel"), GLOBAL_FILES_COMPANY_FAMILY_PLAN(
																																			"CfpModel"), CONTRACT_HEADER(
																																					"VwContractHeader"), GLOBAL_FILES_PRICE_SCHEDULE(
																																							"PsModel"), GLOBAL_FILES_REBATE_PLAN(
																																									"RebatePlanMaster"), GLOBAL_FILES_REBATE_SCHEDULE(
																																											"VwRebateSchedule"), RETURN_RATE_FORECAST(
																																													"ReturnRateForecast"),ITEM_UOM(
																																															"ItemUom");

	private final String tableName;

	GtnFrameworkTransactionTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

}
