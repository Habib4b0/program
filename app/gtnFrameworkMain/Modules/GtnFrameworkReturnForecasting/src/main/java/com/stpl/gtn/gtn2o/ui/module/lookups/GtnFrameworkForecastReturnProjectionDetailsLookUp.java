package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.action.workflow.GtnFrameworkReturnsWorkflowInboxRefreshAction;
import com.stpl.gtn.gtn2o.ui.config.GtnForecastReturnsDataSelectionTabConfig;
import com.stpl.gtn.gtn2o.ui.config.GtnForecastReturnsProjectionTabConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsClassConstants;
import com.stpl.gtn.gtn2o.ui.constants.GtnForecastReturnsStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;

public class GtnFrameworkForecastReturnProjectionDetailsLookUp {

	public GtnUIFrameworkViewConfig getGtnForecastingReturnProjectionDetailsLookUpView(String namespace,
			String dataSelectionId) {

		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName("Projection Name ");
		view.setViewId(GtnFrameworkCommonConstants.GTN_FORECAST_RETURN_PROJECTION_DETAILS_POPUP);
		view.setDefaultView(false);
		view.setReplicable(true);
		addComponentList(view, namespace, dataSelectionId);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namespace, String dataSelectionId) {

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT);
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAddToParent(false);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		addTabLayout(componentList, namespace);
		addControlButtonLayout(componentList, namespace, dataSelectionId);

	}

	private void addTabLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId("projectionDetailsTabsheetLayout");
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAddToParent(true);
		layoutConfig.setParentComponentId(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);
		componentList.add(layoutConfig);

		addTabSheet(componentList, namespace);

	}

	private void addTabSheet(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig tabSheetConfig = new GtnUIFrameworkComponentConfig();
		tabSheetConfig.setComponentType(GtnUIFrameworkComponentType.TABSHEET);
		tabSheetConfig.setComponentId(GtnFrameworkCommonConstants.TAB_SHEET);
		tabSheetConfig.setComponentName("Tab Sheet");
		tabSheetConfig.setComponentWidth("100%");
		tabSheetConfig.setAddToParent(true);
		tabSheetConfig.setParentComponentId("projectionDetailsTabsheetLayout");
		tabSheetConfig.setSpacing(true);

		GtnUIFrameworkTabConfig dataSelection = new GtnUIFrameworkTabConfig();
		dataSelection
				.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dataSelectionRootLayout");
		dataSelection.setTabCaption("Data Selection");
		List<GtnUIFrameworkComponentConfig> dataSelectionTabConfigList = new ArrayList<>();
		new GtnForecastReturnsDataSelectionTabConfig().addDataSelectionTab(dataSelectionTabConfigList, namespace);
		dataSelection.setTabLayoutComponentConfigList(dataSelectionTabConfigList);

		GtnUIFrameworkTabConfig returnsProjection = new GtnUIFrameworkTabConfig();
		returnsProjection.setComponentId("returnsProjectionRootLayout");
		returnsProjection.setTabCaption("Returns Projection");
		List<GtnUIFrameworkComponentConfig> returnsTabConfigList = new ArrayList<>();
		returnsProjection.setTabLayoutComponentConfigList(returnsTabConfigList);
		new GtnForecastReturnsProjectionTabConfig().addReturnsProjectionTab(returnsTabConfigList);

		List<GtnUIFrameworkTabConfig> tabConfigList = new ArrayList<>();
		tabConfigList.add(dataSelection);
		tabConfigList.add(returnsProjection);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig();
		Object toPeriod = namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.TO_PERIOD;
		actionConfig.setActionParameterList(Arrays.asList(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_TAB_CHANGE_ACTION,
				GtnForecastReturnsStringConstants.PROJ_DETAILS_TABSHEET_MAIN_LAYOUT,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FORECAST_LEVEL,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD,
				toPeriod, GtnFrameworkCommonConstants.TAB_SHEET,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabPrevious",
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabNext", "returnsProjectionRootLayout",
				"returnsForecastTabSheet", GtnFrameworkCommonConstants.GTN_FORECAST_RETURN_PROJECTION_DETAILS_POPUP,
				"returnsProjectionGenerateBtn"));
		actionConfig.setFieldValues(Arrays.asList("projectionName", "dsTabPrivateViews", "privateViews",
				"dsTabPublicViews", "publicViews", GtnFrameworkCommonConstants.COMPANY,
				GtnFrameworkCommonConstants.COMPANY, GtnFrameworkCommonConstants.BUSINESS_UNIT,
				GtnFrameworkCommonConstants.BUSINESS_UNIT, "dsTabProjectionName", "dsTabProjectionDescription",
				"projectionDescription", "dsTabFromPeriod", GtnFrameworkCommonConstants.FROM_PERIOD, "dsTabToPeriod",
				GtnFrameworkCommonConstants.TO_PERIOD, "productHierarchy",
				GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX, GtnFrameworkCommonConstants.FORECAST_LEVEL,
				"forecastReturnslevelFilterDdlb", "massUpdateLevel", "forecastReturnslevelDdlb", "productGroup",
				"productLevelComboBox", GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP));
		actionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		actionConfigList.add(actionConfig);
		tabSheetConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		tabSheetConfig.setGtnTabSheetConfigList(tabConfigList);

		componentList.add(tabSheetConfig);
	}

	private void addControlButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace,
			String dataSelectionId) {

		GtnUIFrameworkComponentConfig layoutConfig = new GtnUIFrameworkComponentConfig();
		layoutConfig.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT);
		layoutConfig.setComponentWidth("100%");
		layoutConfig.setAddToParent(true);
		layoutConfig.setParentComponentId(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT);
		layoutConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setSpacing(true);
		layoutConfig.setMargin(true);

		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
		layoutConfig.setGtnLayoutConfig(layout);

		componentList.add(layoutConfig);

		GtnUIFrameworkComponentConfig saveButton = new GtnUIFrameworkComponentConfig();
		saveButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		saveButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabSave");
		saveButton.setComponentName("SAVE");
		saveButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT);
		saveButton.setAddToParent(true);

		componentList.add(saveButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		List<GtnUIFrameWorkActionConfig> configList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();

		customAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_SAVE_UPDATE_ACTION);
		customAction.addActionParameter("add");
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_NA,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_DE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_COMPANY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_BUSINESS_UNIT,
				GtnFrameworkCommonConstants.RETURNS,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_FROM_PERIOD,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_TO_PERIOD,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_HIERARCHY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_FORECAST_LEVEL,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_GROUP,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_LEVEL_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_RELATION_SHIP_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DUAL_LIST_BOX_COMP,
				GtnFrameworkCommonConstants.ACTUALS_PROJECTION_OPTION_GROUP,
				GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE));
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		configList.add(customAction);

		GtnUIFrameWorkActionConfig saveConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		saveConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);

		List<Object> saveConfirmationAlertParams = new ArrayList<>();
		saveConfirmationAlertParams.add("Save confirmation");
		saveConfirmationAlertParams.add("Save record?");

		saveConfirmationAlertParams.add(configList);

		saveConfirmationActionConfig.setActionParameterList(saveConfirmationAlertParams);

		actionConfigList.add(saveConfirmationActionConfig);
		saveButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig updateButton = new GtnUIFrameworkComponentConfig();
		updateButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		updateButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabUpdate");
		updateButton.setComponentName("UPDATE");
		updateButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT);
		updateButton.setAddToParent(true);

		componentList.add(updateButton);

		List<GtnUIFrameWorkActionConfig> updateConfigList = new ArrayList<>();
		List<GtnUIFrameWorkActionConfig> configListForUpdate = new ArrayList<>();
		GtnUIFrameWorkActionConfig updateAction = new GtnUIFrameWorkActionConfig();

		updateAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_SAVE_UPDATE_ACTION);
		updateAction.addActionParameter("edit");
		updateAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_NA,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_DE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_COMPANY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_BUSINESS_UNIT,
				GtnFrameworkCommonConstants.RETURNS,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_FROM_PERIOD,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_TO_PERIOD,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_HIERARCHY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_FORECAST_LEVEL,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_GROUP,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_LEVEL_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_RELATION_SHIP_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DUAL_LIST_BOX_COMP,
				GtnFrameworkCommonConstants.ACTUALS_PROJECTION_OPTION_GROUP,
				GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE));
		updateAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		configListForUpdate.add(updateAction);
		GtnUIFrameWorkActionConfig updateConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
		updateConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);

		List<Object> updateConfirmationAlertParams = new ArrayList<>();
		updateConfirmationAlertParams.add("Save confirmation");
		updateConfirmationAlertParams.add("Save record?");

		updateConfirmationAlertParams.add(configListForUpdate);

		updateConfirmationActionConfig.setActionParameterList(updateConfirmationAlertParams);

		updateConfigList.add(updateConfirmationActionConfig);
		updateButton.setGtnUIFrameWorkActionConfigList(updateConfigList);

		GtnUIFrameworkComponentConfig nextButton = new GtnUIFrameworkComponentConfig();
		nextButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		nextButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabNext");
		nextButton.setComponentName("NEXT");
		nextButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT);
		nextButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> nextConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig dsTabNextButtonActionConfig = new GtnUIFrameWorkActionConfig();
		Object generateBtn = "returnsProjectionGenerateBtn";
		dsTabNextButtonActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dsTabNextButtonActionConfig.setActionParameterList(Arrays.asList(
				GtnForecastReturnsClassConstants.RETURNS_FORECAST_DS_CHANGE_NEXT_BUTTON_ACTION,
				GtnForecastReturnsStringConstants.PROJ_DETAILS_TABSHEET_MAIN_LAYOUT,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FORECAST_LEVEL,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
						+ GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.FROM_PERIOD,
				namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + GtnFrameworkCommonConstants.TO_PERIOD,
				"returnsForecastTabSheet", GtnFrameworkCommonConstants.GTN_FORECAST_RETURN_PROJECTION_DETAILS_POPUP,
				generateBtn, GtnFrameworkCommonConstants.TAB_SHEET));

		dsTabNextButtonActionConfig.setFieldValues(Arrays.asList("projectionName", "dsTabPrivateViews", "privateViews",
				"dsTabPublicViews", "publicViews", GtnFrameworkCommonConstants.COMPANY,
				GtnFrameworkCommonConstants.COMPANY, GtnFrameworkCommonConstants.BUSINESS_UNIT,
				GtnFrameworkCommonConstants.BUSINESS_UNIT, "dsTabProjectionName", "dsTabProjectionDescription",
				"projectionDescription", "dsTabFromPeriod", GtnFrameworkCommonConstants.FROM_PERIOD, "dsTabToPeriod",
				GtnFrameworkCommonConstants.TO_PERIOD, "productHierarchy",
				GtnFrameworkCommonConstants.RELATION_SHIP_COMBOBOX, GtnFrameworkCommonConstants.FORECAST_LEVEL,
				"forecastReturnslevelFilterDdlb", "massUpdateLevel", "forecastReturnslevelDdlb", "productGroup",
				"productLevelComboBox", GtnFrameworkCommonConstants.DUAL_LIST_BOX_COMP));

		nextConfigList.add(dsTabNextButtonActionConfig);
		nextButton.setGtnUIFrameWorkActionConfigList(nextConfigList);
		componentList.add(nextButton);

		GtnUIFrameworkComponentConfig previousButton = new GtnUIFrameworkComponentConfig();
		previousButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		previousButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabPrevious");
		previousButton.setComponentName("PREVIOUS");
		previousButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT);
		previousButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> previousConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig previousAction = new GtnUIFrameWorkActionConfig();
		previousAction.addActionParameter(GtnFrameworkCommonConstants.TAB_SHEET);
		previousAction.addActionParameter("dataSelectionTabMainLayout");
		previousAction.setActionType(GtnUIFrameworkActionType.PREVIOUS_TAB_ACTION);
		previousConfigList.add(previousAction);
		previousButton.setGtnUIFrameWorkActionConfigList(previousConfigList);

		componentList.add(previousButton);

		GtnUIFrameworkComponentConfig closeButton = new GtnUIFrameworkComponentConfig();
		closeButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		closeButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabClose");
		closeButton.setComponentName("CLOSE");
		closeButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT);
		closeButton.setAddToParent(true);
		List<GtnUIFrameWorkActionConfig> actionConfigCloseList = new ArrayList<>();
		List<GtnUIFrameWorkActionConfig> closeActionList = new ArrayList<>();

		GtnUIFrameWorkActionConfig popupCloseAction = new GtnUIFrameWorkActionConfig();
		popupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		popupCloseAction.addActionParameter(GtnFrameworkCommonConstants.GTN_FORECAST_RETURN_PROJECTION_DETAILS_POPUP);
		GtnUIFrameWorkActionConfig windowCloseAction = new GtnUIFrameWorkActionConfig();
		windowCloseAction.setActionType(GtnUIFrameworkActionType.WINDOW_CLOSE_ACTION);

		GtnUIFrameWorkActionConfig gtnFileDeleteActionConfig = new GtnUIFrameWorkActionConfig();
		gtnFileDeleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnFileDeleteActionConfig
				.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_FILE_DELETE_ACTION);
		gtnFileDeleteActionConfig
				.addActionParameter(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT);

		GtnUIFrameWorkActionConfig gtnUiFramwworkActionConfig = new GtnUIFrameWorkActionConfig();
		Object resetButton = "dsReset";
		gtnUiFramwworkActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		gtnUiFramwworkActionConfig.setActionParameterList(
				Arrays.asList(GtnForecastReturnsClassConstants.RETURNS_FORECAST_DATA_SELECTION_RESET_ACTION,
						dataSelectionId, "profileOptionMode", GtnFrameworkCommonConstants.FROM_PERIOD,
						GtnFrameworkCommonConstants.TO_PERIOD, "dsGenerate", "dsSearch", resetButton, "dsSaveView",
						"dsDeleteView", "crudReset", "crudEdit", "crudView", "crudDelete"));
		closeActionList.add(popupCloseAction);
		closeActionList.add(gtnFileDeleteActionConfig);
		closeActionList.add(gtnUiFramwworkActionConfig);
		closeActionList.add(windowCloseAction);
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_CLOSE_VALIDATION_ACTION);
		closeAction.addActionParameter(closeActionList);
		closeAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		closeAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT));
		actionConfigCloseList.add(closeAction);
		closeButton.setGtnUIFrameWorkActionConfigList(actionConfigCloseList);

		componentList.add(closeButton);

		GtnUIFrameWorkActionConfig wffRefreshCustomAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		wffRefreshCustomAction.addActionParameter(GtnFrameworkReturnsWorkflowInboxRefreshAction.class.getName());

		GtnUIFrameworkComponentConfig submitButton = new GtnUIFrameworkComponentConfig();
		submitButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		submitButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabSubmit");
		submitButton.setComponentName("SUBMIT");
		submitButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT);
		submitButton.setAddToParent(true);

		componentList.add(submitButton);

		List<GtnUIFrameWorkActionConfig> submitButtonActionConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig saveAction = new GtnUIFrameWorkActionConfig();
		saveAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_SAVE_UPDATE_ACTION);
		saveAction.addActionParameter("submit");
		saveAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_NA,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_PROJECTION_DE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_COMPANY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_BUSINESS_UNIT,
				GtnFrameworkCommonConstants.RETURNS,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_FROM_PERIOD,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_TO_PERIOD,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_HIERARCHY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_BUSINESS_UNIT,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_GROUP,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_PRODUCT_LEVEL_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_RELATION_SHIP_COMBO,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DUAL_LIST_BOX_COMP,
				GtnFrameworkCommonConstants.ACTUALS_PROJECTION_OPTION_GROUP,
				GtnFrameworkCommonConstants.PROJECTION_PERIOD_ORDER_OPTION_GROUP,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_HISTORY,
				GtnFrameworkCommonConstants.FORECAST_RETURNS_FREQUENCY,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_SAVE,
				GtnFrameworkCommonConstants.RETURNS_FORECAST_TAB_SHEET_DS_TAB_UPDATE));
		saveAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		submitButtonActionConfig.add(saveAction);

		GtnUIFrameWorkActionConfig submitCustomAction = new GtnUIFrameWorkActionConfig();
		submitCustomAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_SUBMIT_ACTION);
		submitCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		submitCustomAction
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT,
						GtnFrameworkCommonConstants.GTN_FORECAST_RETURN_PROJECTION_DETAILS_POPUP));
		submitButtonActionConfig.add(submitCustomAction);
		submitButtonActionConfig.add(wffRefreshCustomAction);
		submitButton.setGtnUIFrameWorkActionConfigList(submitButtonActionConfig);

		GtnUIFrameworkComponentConfig approveButton = new GtnUIFrameworkComponentConfig();
		approveButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		approveButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabApprove");
		approveButton.setComponentName("APPROVE");
		approveButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT);
		approveButton.setAddToParent(true);
		approveButton.setVisible(false);
		componentList.add(approveButton);

		List<GtnUIFrameWorkActionConfig> approveButtonActionConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig approveCustomAction = new GtnUIFrameWorkActionConfig();
		approveCustomAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_WORKFLOW_ACTION);
		approveCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		approveCustomAction.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT, "Approve"));
		approveButtonActionConfig.add(approveCustomAction);
		approveButtonActionConfig.add(wffRefreshCustomAction);
		approveButton.setGtnUIFrameWorkActionConfigList(approveButtonActionConfig);

		GtnUIFrameworkComponentConfig rejectButton = new GtnUIFrameworkComponentConfig();
		rejectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		rejectButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabReject");
		rejectButton.setComponentName("REJECT");
		rejectButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT);
		rejectButton.setAddToParent(true);
		rejectButton.setVisible(false);
		componentList.add(rejectButton);

		List<GtnUIFrameWorkActionConfig> rejectButtonActionConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig rejectCustomAction = new GtnUIFrameWorkActionConfig();
		rejectCustomAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_WORKFLOW_ACTION);
		rejectCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		rejectCustomAction.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT, "Reject"));
		rejectButtonActionConfig.add(rejectCustomAction);
		rejectButtonActionConfig.add(wffRefreshCustomAction);
		rejectButton.setGtnUIFrameWorkActionConfigList(rejectButtonActionConfig);

		GtnUIFrameworkComponentConfig cancelButton = new GtnUIFrameworkComponentConfig();
		cancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cancelButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabCancel");
		cancelButton.setComponentName("CANCEL");
		cancelButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT);
		cancelButton.setAddToParent(true);
		cancelButton.setVisible(false);
		componentList.add(cancelButton);

		List<GtnUIFrameWorkActionConfig> cancelButtonActionConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig cancelCustomAction = new GtnUIFrameWorkActionConfig();
		cancelCustomAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_WORKFLOW_ACTION);
		cancelCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		cancelCustomAction.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT, "Cancel"));
		cancelButtonActionConfig.add(cancelCustomAction);
		cancelButtonActionConfig.add(wffRefreshCustomAction);
		cancelButton.setGtnUIFrameWorkActionConfigList(cancelButtonActionConfig);

		GtnUIFrameworkComponentConfig withdrawButton = new GtnUIFrameworkComponentConfig();
		withdrawButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		withdrawButton.setComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE + "dsTabWithdraw");
		withdrawButton.setComponentName("WITHDRAW");
		withdrawButton.setParentComponentId(namespace + GtnFrameworkForecastConstantCommon.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_BUTTON_LAYOUT);
		withdrawButton.setAddToParent(true);
		withdrawButton.setVisible(false);
		componentList.add(withdrawButton);

		List<GtnUIFrameWorkActionConfig> withdrawButtonActionConfig = new ArrayList<>();
		GtnUIFrameWorkActionConfig withdrawCustomAction = new GtnUIFrameWorkActionConfig();
		withdrawCustomAction.addActionParameter(GtnForecastReturnsClassConstants.RETURNS_FORECAST_WORKFLOW_ACTION);
		withdrawCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);

		withdrawCustomAction.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.PROJECTION_DETAILS_TABSHEET_MAIN_LAYOUT, "Withdraw"));
		withdrawButtonActionConfig.add(withdrawCustomAction);
		withdrawButtonActionConfig.add(wffRefreshCustomAction);
		withdrawButton.setGtnUIFrameWorkActionConfigList(withdrawButtonActionConfig);

	}
}
