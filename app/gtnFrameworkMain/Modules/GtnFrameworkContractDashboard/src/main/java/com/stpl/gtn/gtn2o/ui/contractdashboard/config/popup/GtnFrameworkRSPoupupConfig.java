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

public class GtnFrameworkRSPoupupConfig {
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig rsPopupView = commonConfig.getViewConfig(
				GtnFrameworkContractDashboardContants.CD_RS_VIEW, GtnFrameworkContractDashboardContants.CD_RS_VIEW,
				false);
		addComponentList(rsPopupView, rsPopupView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		rsPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		rsPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return rsPopupView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig rsPopupView, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> rsPopupComponentList = new ArrayList<>();
		rsPopupView.setGtnComponentList(rsPopupComponentList);
		addPopupMainPanel(rsPopupComponentList, namspacePrefix);
	}

	private void addPopupMainPanel(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig.getPanelConfig(namspacePrefix + "PopUpMainPanel", false,
				null);
		rsPopupComponentList.add(panel);
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getVerticalLayoutConfig(namspacePrefix + "PopUpMainLayout", true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		rsPopupComponentList.add(layoutConfig);
		List<String> rsPopupComponentIdList = new ArrayList<>();
		addSearchCriteriaPanel(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList,
				layoutConfig.getComponentId());
		addButtonLayout(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList, layoutConfig.getComponentId());
		addResultPanel(rsPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
		addActionButtonLayout(rsPopupComponentList, namspacePrefix, layoutConfig.getComponentId());

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			List<String> rsPopupComponentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		rsPopupComponentList.add(panelConfig);
		addFieldLayout(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList, panelConfig.getComponentId());

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		rsPopupComponentList.add(layoutConfig);
		addSelectButtonComponent(rsPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
		addCloseButtonComponent(rsPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> rsPopupComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig selectBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		selectBtnConfig.setComponentName("SELECT");
		selectBtnConfig.setAuthorizationIncluded(true);
		rsPopupComponentList.add(selectBtnConfig);

		GtnUIFrameWorkActionConfig rsPopupValidationAction = new GtnUIFrameWorkActionConfig();
		rsPopupValidationAction.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		rsPopupValidationAction
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		rsPopupValidationAction.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig selectAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectAction.addActionParameter(GtnFrameworkPopupSelectAction.class.getName());
		selectAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		selectAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_RS_VIEW);
		selectAction.addActionParameter(0);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_RS_VIEW);

		rsPopupValidationAction.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		rsPopupValidationAction.addActionParameter(Arrays.asList(selectAction, closeAction));

		selectBtnConfig.addGtnUIFrameWorkActionConfig(rsPopupValidationAction);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> rsPopupComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig closeBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		closeBtnConfig.setComponentName("CLOSE");
		closeBtnConfig.setAuthorizationIncluded(true);
		rsPopupComponentList.add(closeBtnConfig);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_RS_VIEW);
		closeBtnConfig.addGtnUIFrameWorkActionConfig(closeAction);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			List<String> rsPopupComponentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		rsPopupComponentList.add(layoutConfig);
		addSearchButtonComponent(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList,
				layoutConfig.getComponentId());
		addResetButtonComponent(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList,
				layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> rsPopupComponentList,
			String namspacePrefix, List<String> rsPopupComponentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		rsPopupComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig rsPopupValidationAction = new GtnUIFrameWorkActionConfig();
		rsPopupValidationAction.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		rsPopupValidationAction.setFieldValues(rsPopupComponentIdList);
		rsPopupValidationAction.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig rsPopupFailureAction = new GtnUIFrameWorkActionConfig();
		rsPopupFailureAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		rsPopupFailureAction.addActionParameter("No Search Criteria ");
		rsPopupFailureAction.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig rsPopupTableLoadAction = new GtnUIFrameWorkActionConfig();
		rsPopupTableLoadAction.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		rsPopupTableLoadAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		rsPopupTableLoadAction.setFieldValues(rsPopupComponentIdList);

		GtnUIFrameWorkActionConfig searchNoticationAction = new GtnUIFrameWorkActionConfig();
		searchNoticationAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNoticationAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);

		rsPopupValidationAction.addActionParameter(Arrays.asList(rsPopupFailureAction));
		rsPopupValidationAction.addActionParameter(Arrays.asList(rsPopupTableLoadAction, searchNoticationAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(rsPopupValidationAction);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> rsPopupComponentList,
			String namspacePrefix, List<String> rsPopupComponentIdList, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		rsPopupComponentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig rsPopupResetAction = new GtnUIFrameWorkActionConfig();
		rsPopupResetAction.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		rsPopupResetAction.addActionParameter("Confirmation");
		rsPopupResetAction.addActionParameter("Are you sure you want to reset the page to default/previous values  ?");
		rsPopupResetAction.addActionParameter(rsPopupComponentIdList);
		rsPopupResetAction.addActionParameter(Arrays.asList("", "", "", null, null, null));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(rsPopupResetAction);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			List<String> rsPopupComponentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getCssLayoutConfig(namspacePrefix + "searchFieldLayout",
				true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		rsPopupComponentList.add(gtnLayout);
		addFieldComponent(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList, gtnLayout.getComponentId());
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		rsPopupComponentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig rsPopupTableConfig = new GtnUIFrameworkPagedTableConfig();
		rsPopupTableConfig.setEditable(false);
		rsPopupTableConfig.setFilterBar(true);
		rsPopupTableConfig.setSelectable(true);
		rsPopupTableConfig.setMultiSelect(false);
		rsPopupTableConfig.setPageLength(10);
		rsPopupTableConfig.setItemPerPage(10);
		rsPopupTableConfig.setSinkItemPerPageWithPageLength(false);
		rsPopupTableConfig.setTableColumnDataType(GtnFrameworkContractDashboardContants.getRsLookupColumnType());
		rsPopupTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getRsLookupHeader());
		rsPopupTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getRsLookupColumn());
		rsPopupTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RS_LOOKUP_TABLE_DATA);
		rsPopupTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RS_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(rsPopupTableConfig);
		rsPopupComponentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			List<String> rsPopupComponentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addRsId(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList, parent, emptyValidationConfig);
		addRsNo(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList, parent, emptyValidationConfig);
		addRsName(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList, parent, emptyValidationConfig);
		addRsStatus(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList, parent, nullValidationConfig);
		addRsType(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList, parent, nullValidationConfig);
		addProgramType(rsPopupComponentList, namspacePrefix, rsPopupComponentIdList, parent, nullValidationConfig);
	}

	private void addRsType(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			List<String> rsPopupComponentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RSType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		rsPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setComponentName("Rebate Schedule Type");
		componentConfig.setAuthorizationIncluded(true);
		rsPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		rsPopupComponentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRsStatus(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			List<String> rsPopupComponentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RSStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		rsPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setComponentName("Rebate Schedule Status");
		componentConfig.setAuthorizationIncluded(true);
		rsPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		rsPopupComponentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addProgramType(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			List<String> rsPopupComponentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "ProgramType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		rsPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setComponentName("Program Type");
		componentConfig.setAuthorizationIncluded(true);
		rsPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.REBATE_PROGRAM_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		rsPopupComponentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRsId(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			List<String> rsPopupComponentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RSID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		rsPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setComponentName("Rebate Schedule ID");
		componentConfig.setAuthorizationIncluded(true);
		rsPopupComponentList.add(componentConfig);
		rsPopupComponentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRsNo(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			List<String> rsPopupComponentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RSNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		rsPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setComponentName("Rebate Schedule No");
		rsPopupComponentList.add(componentConfig);
		rsPopupComponentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRsName(List<GtnUIFrameworkComponentConfig> rsPopupComponentList, String namspacePrefix,
			List<String> rsPopupComponentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RSName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		rsPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setComponentName("Rebate Schedule Name");
		rsPopupComponentList.add(componentConfig);
		rsPopupComponentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
