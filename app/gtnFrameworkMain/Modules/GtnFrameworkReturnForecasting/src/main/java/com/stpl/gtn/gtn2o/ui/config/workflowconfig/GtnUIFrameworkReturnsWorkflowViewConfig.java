/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.config.workflowconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkForecastReturnConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnUIFrameworkReturnsWorkflowViewConfig {

	public GtnUIFrameworkRootConfig getWorkfolwView(Map<String, String> infoMap) throws GtnFrameworkGeneralException {
		GtnUIFrameworkRootConfig rootConfig = new GtnUIFrameworkRootConfig();
		GtnUIFrameworkViewConfig workflowView = new GtnFrameworkForecastReturnConfig()
				.getGtnForecastReturnRootConfigForWorkflow();
		workflowView.setDefaultView(true);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		actionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnForecastReturnsClassConstants.RETURNS_FORECAST_WORKFLOW_TAB_LOAD_ACTION,
						"returns", "Product", GtnFrameworkForecastConstantCommon.EDIT_MODE, "projectionIdFromWorkflow",
						"workflowId", "workflowStatus", "userType", "noOfApprovals", "approvalLevel", infoMap }));
		actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfig.setFieldValues(Arrays.asList(new String[] { "gtnForecastReturnProjectionDetailsPopup",
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE,
				"returnsForecastTabSheet_dsTabProjectionName", "returnsForecastTabSheet_dsTabProjectionDescription",
				"returnsForecastTabSheet_dsTabFromPeriod", "returnsForecastTabSheet_productHierarchy",
				"returnsForecastTabSheet_company", "returnsForecastTabSheet_businessUnit",
				"returnsForecastTabSheet_relationShipCombobox", "returnsForecastTabSheet_forecastLevel",
				"forecastReturnslevelFilterDdlb", "forecastReturnslevelDdlb", "massUpdateLevel",
				"returnsForecastTabSheet_productLevelComboBox", "returnsForecastTabSheet_dsTabToPeriod",
				"forecastReturnsFrequency", "forecastReturnsHistory", "actualsProjectionOptionGroup",
				"projectionPeriodOrderOptionGroup", "returnsForecastTabSheet_dualListBoxComp", "resultTable",
				"projectionDetailsTabsheetMainLayout", "returnsProjectionGenerateBtn" }));
		actionConfigList.add(actionConfig);

		GtnUIFrameWorkActionConfig dualListBoxRightTableLoadAction = new GtnUIFrameWorkActionConfig();
		dualListBoxRightTableLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList
				.add(GtnForecastReturnsClassConstants.RETURNS_FORECAST_DUAL_LIST_BOX_RIGHT_TABLE_LOAD_ACTION);
		actionParameterList.add("workflow");
		actionParameterList.add("returnsForecastMainScreenDataSelection");
		actionParameterList.add("returnsForecastTabSheet");
		dualListBoxRightTableLoadAction.setActionParameterList(actionParameterList);
		actionConfigList.add(dualListBoxRightTableLoadAction);

		GtnUIFrameWorkActionConfig enableActionConfig = new GtnUIFrameWorkActionConfig();
		enableActionConfig.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		enableActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE }));
		actionConfigList.add(enableActionConfig);
		GtnUIFrameWorkActionConfig visibleActionConfig = new GtnUIFrameWorkActionConfig();
		visibleActionConfig
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_WORKFLOW_BUTTON_ACTION);
		visibleActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		visibleActionConfig.addActionParameter(
				Arrays.asList(new String[] { GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SUBMIT,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_APPROVE,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_REJECT,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_CANCEL }));
		visibleActionConfig.addActionParameter(
				Arrays.asList(new String[] { GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_WITHDRAW,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_APPROVE,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_REJECT,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_CANCEL }));
		visibleActionConfig.addActionParameter(
				Arrays.asList(new String[] { GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_WITHDRAW,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_APPROVE,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_REJECT,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_CANCEL,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SUBMIT }));
		visibleActionConfig.addActionParameter(
				Arrays.asList(new String[] { GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_WITHDRAW,
						GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SUBMIT }));
		visibleActionConfig.addActionParameter(Arrays.asList(new String[] {
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
				"returnsForecastTabSheet_dataSelectionTabMainLayout", "forecastTabCssLayout", "massUpdateCssLayout" }));
		visibleActionConfig.addActionParameter("workflowStatus");
		visibleActionConfig.addActionParameter("userType");
		visibleActionConfig.addActionParameter(infoMap);
		actionConfigList.add(visibleActionConfig);
		GtnUIFrameWorkActionConfig tabChange = new GtnUIFrameWorkActionConfig();
		tabChange.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabChange.setActionParameterList(Arrays.asList(new Object[] { "tabSheet", "returnsProjectionRootLayout" }));
		actionConfigList.add(tabChange);
		workflowView.setViewActionList(actionConfigList);
		rootConfig.setGtnViewConfigList(new ArrayList<GtnUIFrameworkViewConfig>());
		rootConfig.getGtnViewConfigList().add(workflowView);

		return rootConfig;
	}
}
