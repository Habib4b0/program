package com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkNSRPopupLoadAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkPopupSelectAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkLoadTableFromAnTableValueAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

public class GtnFrameworkFormulaNoPoupupConfig {
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig formulaNoPopUpView = commonConfig.getViewConfig(
				GtnFrameworkContractDashboardContants.CD_FORMULA_NO_VIEW,
				GtnFrameworkContractDashboardContants.CD_FORMULA_NO_VIEW, false);
		addComponentList(formulaNoPopUpView,
				formulaNoPopUpView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		formulaNoPopUpView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		formulaNoPopUpView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return formulaNoPopUpView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList = new ArrayList<>();
		view.setGtnComponentList(formulaNoPopUpComponentList);
		addPopupMainPanel(formulaNoPopUpComponentList, namspacePrefix);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkNSRPopupLoadAction.class.getName());
		customAction.addActionParameter(namspacePrefix + "FormulaType");
		customAction.addActionParameter(view.getViewId());
		view.addViewAction(customAction);
	}

	private void addPopupMainPanel(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig.getPanelConfig(namspacePrefix + "PopUpMainPanel", false,
				null);
		formulaNoPopUpComponentList.add(panel);
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getVerticalLayoutConfig(namspacePrefix + "PopUpMainLayout", true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		formulaNoPopUpComponentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addSearchCriteriaPanel(formulaNoPopUpComponentList, namspacePrefix, componentIdList,
				layoutConfig.getComponentId());
		addButtonLayout(formulaNoPopUpComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addMainResultPanel(formulaNoPopUpComponentList, namspacePrefix, layoutConfig.getComponentId());
		addActionButtonLayout(formulaNoPopUpComponentList, namspacePrefix, layoutConfig.getComponentId());

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		formulaNoPopUpComponentList.add(panelConfig);
		addFieldLayout(formulaNoPopUpComponentList, namspacePrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		formulaNoPopUpComponentList.add(layoutConfig);
		addSelectButtonComponent(formulaNoPopUpComponentList, namspacePrefix, layoutConfig.getComponentId());
		addCloseButtonComponent(formulaNoPopUpComponentList, namspacePrefix, layoutConfig.getComponentId());
		addDetailsButtonComponent(formulaNoPopUpComponentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig selectBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		selectBtnConfig.setComponentName("SELECT");
		selectBtnConfig.setAuthorizationIncluded(true);
		formulaNoPopUpComponentList.add(selectBtnConfig);

		GtnUIFrameWorkActionConfig formulaNoPopUpValidationActionConfig = new GtnUIFrameWorkActionConfig();
		formulaNoPopUpValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		formulaNoPopUpValidationActionConfig
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		formulaNoPopUpValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig formulaNoPopUpSelectAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		formulaNoPopUpSelectAction.addActionParameter(GtnFrameworkPopupSelectAction.class.getName());
		formulaNoPopUpSelectAction
				.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		formulaNoPopUpSelectAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_FORMULA_NO_VIEW);
		formulaNoPopUpSelectAction.addActionParameter(0);

		GtnUIFrameWorkActionConfig formulaNoPopUpCloseAction = new GtnUIFrameWorkActionConfig();
		formulaNoPopUpCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		formulaNoPopUpCloseAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_FORMULA_NO_VIEW);

		formulaNoPopUpValidationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		formulaNoPopUpValidationActionConfig
				.addActionParameter(Arrays.asList(formulaNoPopUpSelectAction, formulaNoPopUpCloseAction));

		selectBtnConfig.addGtnUIFrameWorkActionConfig(formulaNoPopUpValidationActionConfig);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig closeBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		closeBtnConfig.setComponentName("CLOSE");
		closeBtnConfig.setAuthorizationIncluded(true);
		formulaNoPopUpComponentList.add(closeBtnConfig);

		GtnUIFrameWorkActionConfig formulaNoPopUpCloseAction = new GtnUIFrameWorkActionConfig();
		formulaNoPopUpCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		formulaNoPopUpCloseAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_FORMULA_NO_VIEW);
		closeBtnConfig.addGtnUIFrameWorkActionConfig(formulaNoPopUpCloseAction);
	}

	private void addDetailsButtonComponent(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "detailsBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("DETAILS");
		searchButtonConfig.setAuthorizationIncluded(true);
		formulaNoPopUpComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig formulaNoPopUpValidationActionConfig = new GtnUIFrameWorkActionConfig();
		formulaNoPopUpValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		formulaNoPopUpValidationActionConfig
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		formulaNoPopUpValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig formulaNoPopUpTableLoadAction = new GtnUIFrameWorkActionConfig();
		formulaNoPopUpTableLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		formulaNoPopUpTableLoadAction.addActionParameter(GtnUIFrameworkLoadTableFromAnTableValueAction.class.getName());
		formulaNoPopUpTableLoadAction
				.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		formulaNoPopUpTableLoadAction.addActionParameter(4);
		formulaNoPopUpTableLoadAction
				.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.DETAILS_RESULT_TABLE);

		formulaNoPopUpValidationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		formulaNoPopUpValidationActionConfig.addActionParameter(Arrays.asList(formulaNoPopUpTableLoadAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(formulaNoPopUpValidationActionConfig);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		formulaNoPopUpComponentList.add(layoutConfig);
		addSearchButtonComponent(formulaNoPopUpComponentList, namspacePrefix, componentIdList,
				layoutConfig.getComponentId());
		addResetButtonComponent(formulaNoPopUpComponentList, namspacePrefix, componentIdList,
				layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		formulaNoPopUpComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig formulaNoPopUpValidationActionConfig = new GtnUIFrameWorkActionConfig();
		formulaNoPopUpValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		formulaNoPopUpValidationActionConfig.setFieldValues(componentIdList);
		formulaNoPopUpValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig formulaNoPopUpFailureActionConfig = new GtnUIFrameWorkActionConfig();
		formulaNoPopUpFailureActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		formulaNoPopUpFailureActionConfig.addActionParameter("No Search Criteria ");
		formulaNoPopUpFailureActionConfig.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig formulaNoPopUpResetTableAction = new GtnUIFrameWorkActionConfig();
		formulaNoPopUpResetTableAction.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
		formulaNoPopUpResetTableAction.addActionParameter(
				Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.DETAILS_RESULT_TABLE));
		formulaNoPopUpResetTableAction.addActionParameter(Arrays.asList(new Object[] { null }));

		GtnUIFrameWorkActionConfig formulaNoPopUpTableLoadAction = new GtnUIFrameWorkActionConfig();
		formulaNoPopUpTableLoadAction.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		formulaNoPopUpTableLoadAction
				.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		formulaNoPopUpTableLoadAction.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig searchNoticationAction = new GtnUIFrameWorkActionConfig();
		searchNoticationAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNoticationAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);

		formulaNoPopUpValidationActionConfig.addActionParameter(Arrays.asList(formulaNoPopUpFailureActionConfig));
		formulaNoPopUpValidationActionConfig.addActionParameter(
				Arrays.asList(formulaNoPopUpResetTableAction, formulaNoPopUpTableLoadAction, searchNoticationAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(formulaNoPopUpValidationActionConfig);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		formulaNoPopUpComponentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig formulaNoPopUpResetActionConfig = new GtnUIFrameWorkActionConfig();
		formulaNoPopUpResetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		formulaNoPopUpResetActionConfig.addActionParameter("Reset Confirmation");
		formulaNoPopUpResetActionConfig
				.addActionParameter("Are you sure you want to reset the values in the Search Criteria group box?");
		formulaNoPopUpResetActionConfig.addActionParameter(componentIdList);
		formulaNoPopUpResetActionConfig.addActionParameter(Arrays.asList(null, "", "", ""));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(formulaNoPopUpResetActionConfig);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getCssLayoutConfig(namspacePrefix + "searchFieldLayout",
				true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		formulaNoPopUpComponentList.add(gtnLayout);
		addFieldComponent(formulaNoPopUpComponentList, namspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addMainResultPanel(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(namspacePrefix + "resultPanel", true,
				parent);
		formulaNoPopUpComponentList.add(panelConfig);
		GtnUIFrameworkComponentConfig gtnMainCssLayout = commonConfig
				.getCssLayoutConfig(namspacePrefix + "resultLayout", true, panelConfig.getComponentId());
		gtnMainCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		formulaNoPopUpComponentList.add(gtnMainCssLayout);

		GtnUIFrameworkComponentConfig gtnLeftCssLayout = commonConfig
				.getCssLayoutConfig(namspacePrefix + "leftResultLayout", true, gtnMainCssLayout.getComponentId());
		gtnLeftCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		formulaNoPopUpComponentList.add(gtnLeftCssLayout);
		GtnUIFrameworkComponentConfig gtnRightCssLayout = commonConfig
				.getCssLayoutConfig(namspacePrefix + "rightResultLayout", true, gtnMainCssLayout.getComponentId());
		gtnRightCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		formulaNoPopUpComponentList.add(gtnRightCssLayout);
		addResultPanel(formulaNoPopUpComponentList, namspacePrefix, gtnLeftCssLayout.getComponentId());
		addDetailResultPanel(formulaNoPopUpComponentList, namspacePrefix, gtnRightCssLayout.getComponentId());
	}

	private void addDetailResultPanel(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList,
			String namspacePrefix, String parent) {
		String componentId = namspacePrefix + GtnFrameworkContractDashboardContants.DETAILS_RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		panelConfig.setComponentName("Formula Details");
		panelConfig.setAuthorizationIncluded(true);
		formulaNoPopUpComponentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig formulaNoPopUpTableConf = new GtnUIFrameworkPagedTableConfig();
		formulaNoPopUpTableConf.setEditable(false);
		formulaNoPopUpTableConf.setFilterBar(true);
		formulaNoPopUpTableConf.setSelectable(false);
		formulaNoPopUpTableConf.setPageLength(5);
		formulaNoPopUpTableConf.setItemPerPage(5);
		formulaNoPopUpTableConf.setSinkItemPerPageWithPageLength(false);
		formulaNoPopUpTableConf
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getFormulaDetailsLookupColumnType());
		formulaNoPopUpTableConf
				.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getFormulaDetailsLookupHeader());
		formulaNoPopUpTableConf
				.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getFormulaDetailsLookupColumn());
		formulaNoPopUpTableConf.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_FORMULA_LOOKUP_TABLE_DATA);
		formulaNoPopUpTableConf.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_FORMULA_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(formulaNoPopUpTableConf);
		formulaNoPopUpComponentList.add(componentConfig);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		panelConfig.setComponentName("Results");
		formulaNoPopUpComponentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig formulaNoPopUpTableConf = new GtnUIFrameworkPagedTableConfig();
		formulaNoPopUpTableConf.setEditable(false);
		formulaNoPopUpTableConf.setFilterBar(true);
		formulaNoPopUpTableConf.setSelectable(true);
		formulaNoPopUpTableConf.setPageLength(5);
		formulaNoPopUpTableConf.setItemPerPage(5);
		formulaNoPopUpTableConf.setSinkItemPerPageWithPageLength(false);
		formulaNoPopUpTableConf
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getFormulaLookupColumnType());
		formulaNoPopUpTableConf.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getFormulaLookupHeader());
		formulaNoPopUpTableConf.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getFormulaLookupColumn());
		formulaNoPopUpTableConf.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_FORMULA_LOOKUP_TABLE_DATA);
		formulaNoPopUpTableConf.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_FORMULA_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(formulaNoPopUpTableConf);
		formulaNoPopUpComponentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addFormulaType(formulaNoPopUpComponentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addFormulaNo(formulaNoPopUpComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addFormulaID(formulaNoPopUpComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addFormulaName(formulaNoPopUpComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
	}

	private void addFormulaType(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "FormulaType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		formulaNoPopUpComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Formula Type");
		formulaNoPopUpComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.FORMULA_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addFormulaNo(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "FormulaNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		formulaNoPopUpComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Formula No");
		formulaNoPopUpComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addFormulaID(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "FormulaID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		formulaNoPopUpComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Formula ID");
		formulaNoPopUpComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addFormulaName(List<GtnUIFrameworkComponentConfig> formulaNoPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "FormulaName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		formulaNoPopUpComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Formula Name");
		formulaNoPopUpComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
