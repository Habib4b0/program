package com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkPopupSelectAction;
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

public class GtnFrameworkTPPoupupConfig {
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig tpPopUpView = commonConfig.getViewConfig(
				GtnFrameworkContractDashboardContants.CD_TP_VIEW, GtnFrameworkContractDashboardContants.CD_TP_VIEW,
				false);
		addComponentList(tpPopUpView, tpPopUpView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		tpPopUpView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		tpPopUpView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return tpPopUpView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig tpPopUpView, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> tpPopUpComponentList = new ArrayList<>();
		tpPopUpView.setGtnComponentList(tpPopUpComponentList);
		addPopupMainPanel(tpPopUpComponentList, namspacePrefix);
	}

	private void addPopupMainPanel(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig.getPanelConfig(namspacePrefix + "PopUpMainPanel", false,
				null);
		tpPopUpComponentList.add(panel);
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getVerticalLayoutConfig(namspacePrefix + "PopUpMainLayout", true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		tpPopUpComponentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addSearchCriteriaPanel(tpPopUpComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addButtonLayout(tpPopUpComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addResultPanel(tpPopUpComponentList, namspacePrefix, layoutConfig.getComponentId());
		addActionButtonLayout(tpPopUpComponentList, namspacePrefix, layoutConfig.getComponentId());

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		tpPopUpComponentList.add(panelConfig);
		addFieldLayout(tpPopUpComponentList, namspacePrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		tpPopUpComponentList.add(layoutConfig);
		addSelectButtonComponent(tpPopUpComponentList, namspacePrefix, layoutConfig.getComponentId());
		addCloseButtonComponent(tpPopUpComponentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig selectBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		selectBtnConfig.setComponentName("SELECT");
		selectBtnConfig.setAuthorizationIncluded(true);
		tpPopUpComponentList.add(selectBtnConfig);

		GtnUIFrameWorkActionConfig tpPopUpValidationAction = new GtnUIFrameWorkActionConfig();
		tpPopUpValidationAction.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		tpPopUpValidationAction
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		tpPopUpValidationAction.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig tpPopUpSelectAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tpPopUpSelectAction.addActionParameter(GtnFrameworkPopupSelectAction.class.getName());
		tpPopUpSelectAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		tpPopUpSelectAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_TP_VIEW);
		tpPopUpSelectAction.addActionParameter(5);

		GtnUIFrameWorkActionConfig tpPopUpCloseAction = new GtnUIFrameWorkActionConfig();
		tpPopUpCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		tpPopUpCloseAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_TP_VIEW);

		tpPopUpValidationAction.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		tpPopUpValidationAction.addActionParameter(Arrays.asList(tpPopUpSelectAction, tpPopUpCloseAction));

		selectBtnConfig.addGtnUIFrameWorkActionConfig(tpPopUpValidationAction);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig closeBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		closeBtnConfig.setComponentName("CLOSE");
		closeBtnConfig.setAuthorizationIncluded(true);
		tpPopUpComponentList.add(closeBtnConfig);

		GtnUIFrameWorkActionConfig tpPopUpCloseAction = new GtnUIFrameWorkActionConfig();
		tpPopUpCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		tpPopUpCloseAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_TP_VIEW);
		closeBtnConfig.addGtnUIFrameWorkActionConfig(tpPopUpCloseAction);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		tpPopUpComponentList.add(layoutConfig);
		addSearchButtonComponent(tpPopUpComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addResetButtonComponent(tpPopUpComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		tpPopUpComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig tpPopUpValidationAction = new GtnUIFrameWorkActionConfig();
		tpPopUpValidationAction.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		tpPopUpValidationAction.setFieldValues(componentIdList);
		tpPopUpValidationAction.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig tpPopUpFailureAction = new GtnUIFrameWorkActionConfig();
		tpPopUpFailureAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		tpPopUpFailureAction.addActionParameter("No Search Criteria ");
		tpPopUpFailureAction.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig tpPopUpTableLoadAction = new GtnUIFrameWorkActionConfig();
		tpPopUpTableLoadAction.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		tpPopUpTableLoadAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		tpPopUpTableLoadAction.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig searchNoticationAction = new GtnUIFrameWorkActionConfig();
		searchNoticationAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNoticationAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);

		tpPopUpValidationAction.addActionParameter(Arrays.asList(tpPopUpFailureAction));
		tpPopUpValidationAction.addActionParameter(Arrays.asList(tpPopUpTableLoadAction, searchNoticationAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(tpPopUpValidationAction);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig tpPopUpResetButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		tpPopUpResetButtonConfig.setComponentName("RESET");
		tpPopUpResetButtonConfig.setAuthorizationIncluded(true);
		tpPopUpComponentList.add(tpPopUpResetButtonConfig);

		GtnUIFrameWorkActionConfig tpPopUpResetAction = new GtnUIFrameWorkActionConfig();
		tpPopUpResetAction.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		tpPopUpResetAction.addActionParameter("Confirmation");
		tpPopUpResetAction.addActionParameter("Are you sure you want to reset the page to default/previous values  ?");
		tpPopUpResetAction.addActionParameter(componentIdList);
		tpPopUpResetAction.addActionParameter(Arrays.asList("", "", "", null, null));
		tpPopUpResetButtonConfig.addGtnUIFrameWorkActionConfig(tpPopUpResetAction);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getCssLayoutConfig(namspacePrefix + "searchFieldLayout",
				true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		tpPopUpComponentList.add(gtnLayout);
		addFieldComponent(tpPopUpComponentList, namspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		tpPopUpComponentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig tpPopUpTableConfig = new GtnUIFrameworkPagedTableConfig();
		tpPopUpTableConfig.setEditable(false);
		tpPopUpTableConfig.setFilterBar(true);
		tpPopUpTableConfig.setSelectable(true);
		tpPopUpTableConfig.setPageLength(10);
		tpPopUpTableConfig.setItemPerPage(10);
		tpPopUpTableConfig.setSinkItemPerPageWithPageLength(false);
		tpPopUpTableConfig.setTableColumnDataType(GtnFrameworkContractDashboardContants.getTpLookupColumnType());
		tpPopUpTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getTpLookupHeader());
		tpPopUpTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getTpLookupColumn());
		tpPopUpTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_TP_LOOKUP_TABLE_DATA);
		tpPopUpTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_TP_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(tpPopUpTableConfig);
		tpPopUpComponentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addCompanyId(tpPopUpComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCompanyNo(tpPopUpComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCompanyName(tpPopUpComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCompanyStatus(tpPopUpComponentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addCompanyType(tpPopUpComponentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
	}

	private void addCompanyType(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "CompanyType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		tpPopUpComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setComponentName("Company Type");
		componentConfig.setAuthorizationIncluded(true);
		tpPopUpComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.COMP_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCompanyStatus(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "CompanyStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		tpPopUpComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setComponentName("Company Status");
		componentConfig.setAuthorizationIncluded(true);
		tpPopUpComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCompanyId(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "CompanyID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		tpPopUpComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setComponentName("Company ID");
		componentConfig.setAuthorizationIncluded(true);
		tpPopUpComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "CompanyNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		tpPopUpComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setComponentName("Company No");
		componentConfig.setAuthorizationIncluded(true);
		tpPopUpComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> tpPopUpComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "CompanyName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		tpPopUpComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setComponentName("Company Name");
		componentConfig.setAuthorizationIncluded(true);
		tpPopUpComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
