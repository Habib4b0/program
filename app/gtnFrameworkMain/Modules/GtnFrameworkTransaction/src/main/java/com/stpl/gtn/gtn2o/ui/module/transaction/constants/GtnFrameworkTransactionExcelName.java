package com.stpl.gtn.gtn2o.ui.module.transaction.constants;

public enum GtnFrameworkTransactionExcelName {
	LOTMASTER("Lot Master"), CPIINDEXMASTER("Consumer Price Index"), GLCOSTCENTERMASTER(
			"GL Cost Center"), AVERAGESHELFLIFEMASTER("Average Shelf Life"), GLBALANCEMASTER(
					"GL Balance Master"), AUDITMASTERINBOUND("Audit Inbound"), SALESMASTER(
							"Sales Actual"), FORECASTINGMASTER("Sales Forecast"), CUSTOMERGTSACTUAL(
									"GTS Actuals"), VWCUSTOMERGTSFORECAST("GTS Foreacst"), VWCOMPANYTRADECLASS(
											"Global Files Company Trade Class"), VWRETURNRESERVE(
													"Return Reserve"), RETURNSMASTER("Returns"), VWCOMPANYPARENTDETAILS(
															"Global Files Company Parent"), VWCOMPANYIDENTIFIER(
																	"Global Files Company Identifier"), VWITEMIDENTIFIER(
																			"Global Files Item Identifier"), VWITEMPRICING(
																					"Global Files Item Pricing"), ACCRUALMASTER(
																							"Acural Master"), VWADJUSTDEMANDFORECASTACT(
																									"Demand"), ACTUALSMASTER(
																											"Payments"), VWCOMPANYMASTER(
																													"Global Files Comapny Master"), VWINVENTORYWDACTUALPROJMAS(
																															"Inventory Withdrawals"), VWITEMMASTER(
																																	"Global Files Item Master"), ACCRUALDETAILS(
																																			"Accural Details"), INVALIDINTEGRATION(
																																					""), IFPMODEL(
																																							"Global Files Item Family Plan"), CFPMODEL(
																																									"Global Files Item Family Plan"), VWCONTRACTHEADER(
																																											"Contract Header"), PSMODEL(
																																													"Global Files Price Shedule"), REBATEPLANMASTER(
																																															"Global Files Rebate Plan"), VWREBATESCHEDULE(
																																																	"Global Files Rebate Schedule"), RETURNRATEFORECAST(
																																																			"Return Rate Forecast"),ITEMUOM(
																																																					"Item Uom");
	private final String excelName;

	GtnFrameworkTransactionExcelName(String excelName) {
		this.excelName = excelName;
	}

	public String getTableName() {
		return excelName;
	}

}
