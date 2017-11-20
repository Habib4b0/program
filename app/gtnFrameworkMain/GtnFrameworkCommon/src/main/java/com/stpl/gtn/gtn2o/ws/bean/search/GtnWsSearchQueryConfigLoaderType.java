
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.bean.search;

import java.util.Map;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Karthikeyan.Subraman
 */
public enum GtnWsSearchQueryConfigLoaderType {

	COMPANY_MASTER("com.stpl.gtn.gtn2o.ws.module.companymaster.config.GtnWsCMasterConfig"), COMPANY_FAMILY_PLAN_SEARCH(
			"com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.config.GtnWebServiceCompanyFamilyPlanSearchConfig"), COMPANY_FAMILY_PLAN_ADDITION_SEARCH(
					"com.stpl.gtn.gtn2o.ws.module.companyfamilyplan.config.GtnWebServiceCompanyFamilyPlanAdditionSearchConfig"), ITEM_FAMILY_PLAN(
							"com.stpl.gtn.gtn2o.ws.module.itemfamilyplan.config.GtnWebServiceItemFamilyPlanConfig"), COMPANY_GROUPS(
									"com.stpl.gtn.gtn2o.ws.module.companygroups.config.GtnWebServiceCompanyGroupConfig"), ITEM_GROUPS(
											"com.stpl.gtn.gtn2o.ws.module.itemgroups.config.GtnWebServiceItemGroupConfig"), CDR(
													"com.stpl.gtn.gtn2o.ws.module.complianceAndDeductionRules.config.GtnWebServiceCDRConfig"), PRICE_SCHEDULE(
															"com.stpl.gtn.gtn2o.ws.module.priceschedule.config.GtnWebServicePriceScheduleConfig"), FORECAST_FORMULA(
																	"com.stpl.gtn.gtn2o.ws.module.complianceAndDeductionRules.config.GtnWebServiceFormulaConfig"), REBATE_SCHEDULE(
																			"com.stpl.gtn.gtn2o.ws.module.rebateschedule.config.GtnWebServiceRebateScheduleConfig"), POPUP_CONFIG(
																					"com.stpl.gtn.gtn2o.ws.module.complianceAndDeductionRules.config.GtnWebServicePopUpConfig"), DEDUCTION_CALENDAR(
																							"com.stpl.gtn.gtn2o.ws.module.deductioncalendar.config.GtnWebServiceDeductionCalendarConfig"), NET_SALES(
																									"com.stpl.gtn.gtn2o.ws.module.netsales.config.GtnWebServiceNetSalesFormulaconfig"), REBATE_PLAN(
																											"com.stpl.gtn.gtn2o.ws.module.rebateplan.config.GtnWebServiceRebatePlanConfig"), ITEM_MASTER(
																													"com.stpl.gtn.gtn2o.ws.module.itemaster.config.GtnWsItemMasterConfig"), CONTRACT_HEADER(
																															"com.stpl.gtn.gtn2o.ws.module.contractheader.config.GtnWsContractHeaderConfig"), COMPANY_FINANCIAL_HEADER(
																																	"com.stpl.gtn.gtn2o.ws.module.companymaster.config.GtnWsCMasterFinancialCloseConfig"), CONTRACT_WORKFLOW_SEARCH(
																																			"com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceWorkflowSearchConfig"), FORECASTING_WORKFLOW_SEARCH(
																																					"com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceForecastingWorkflowSearchConfig"), ARP_WORKFLOW_SEARCH(
																																							"com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceArpWorkflowSearchConfig"), RETURNS_WORKFLOW_SEARCH(
																																									"com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceReturnWorkflowSearchConfig"), ARM_WORKFLOW_SEARCH(
																																											"com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceArmWorkflowSearchConfig"), WF_PRIVATE_VIEW_SEARCH(
																																													"com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceWFPrivateViewSearchConfig"), WF_CREATED_BY_SEARCH(
																																															"com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceWFCreatedandApprovedBySearchConfig"), WF_HISTORYBUTTON_SEARCH(
																																																	"com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceHistoryWorkflowSearchConfig"), WF_ATTACHMENT_SEARCH(
																																																			"com.stpl.gtn.gtn2o.ws.module.workflowinbox.config.GtnWebServiceAttachmentWorkflowSearchConfig");
	private String className;
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWsSearchQueryConfigLoaderType.class);

	private GtnWsSearchQueryConfigLoaderType(String className) {
		this.className = className;

	}

	public Object returnSearchQueryConfigLoader(Map<String, Object> dynamicMap) {

		LOGGER.debug(className);
		return dynamicMap.get(className);
	}

	public String getClassName() {
		return className;
	}

}
