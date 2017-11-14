package com.stpl.gtn.gtn2o.ui.module.transaction.constants;

public enum GtnFrameworkTransactionInvalidTableName {
	GTS_FORECAST("IvldCustomerGtsForecast"), ACCRUAL_DETAILS("IvldAccrualInbound"), AVERAGE_SHELF_LIFE(
			"IvldAverageShelfLife"), GLOBAL_FILES_COMPANY_IDENTIFIER("IvldCompanyIdentifier"), SALES_FORECAST(
					"IvldForecastSales"), GTS_ACTUAL("IvldCustomerGtsActual"), SALES_ACTUAL(
							"IvldSalesMaster"), LOT_MASTER("IvldLotMaster"), CPI(
									"IvldCpi"), RETURNS("IvldReturns"), GLOBAL_FILES_COMPANY_MASTER(
											"IvldCompanyMaster"), GLOBAL_FILES_COMPANY_PARENT(
													"IvldCompanyParent"), GLOBAL_FILES_COMPANY_TRADE_CLASS(
															"IvldCompanyTradeClass"), GLOBAL_FILES_ITEM_MASTER(
																	"IvldItemMaster"), GLOBAL_FILES_ITEM_IDENTIFIER(
																			"IvldItemIdentifier"), GLOBAL_FILES_ITEM_PRICING(
																					"IvldItemPricing"), GL_BALANCE(
																							"IvldGlBalance"), GL_COST_CENTER(
																									"IvldGlCostCenter"), INVENTORY_WITHDRAWAL(
																											"VwIvldInventoryWdActualProjMas"), DEMAND(
																													"VwIvldAdjDemandForeActual"), SELECT_ONE(
																															"InvalidIntegration"), PAYMENTS(
																																	"VwIvldActualsMaster"), GLOBAL_FILES_ITEM_FAMILY_PLAN(
																																			"IvldIfp"), GLOBAL_FILES_COMPANY_FAMILY_PLAN(
																																					"IvldCfp"), CONTRACT_HEADER(
																																							"IvldContractHeader"), GLOBAL_FILES_PRICE_SCHEDULE(
																																									"IvldPriceSchedule"), GLOBAL_FILES_REBATE_PLAN(
																																											"IvldRebatePlan"), GLOBAL_FILES_REBATE_SCHEDULE(
																																													"IvldRebateSchedule"), RETURN_RATE_FORECAST(
																																															"IvldReturnRateForecast"),ITEM_UOM(
																																																	"IvldItemUom");

	private final String tableName;

	GtnFrameworkTransactionInvalidTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getTableName() {
		return tableName;
	}

}
