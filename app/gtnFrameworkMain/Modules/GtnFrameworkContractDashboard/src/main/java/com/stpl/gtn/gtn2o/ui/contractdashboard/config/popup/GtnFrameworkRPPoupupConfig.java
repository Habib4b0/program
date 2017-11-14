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

public class GtnFrameworkRPPoupupConfig {
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig rpPopupView = commonConfig.getViewConfig(
				GtnFrameworkContractDashboardContants.CD_RP_NO_VIEW,
				GtnFrameworkContractDashboardContants.CD_RP_NO_VIEW, false);
		addComponentList(rpPopupView, rpPopupView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		rpPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		rpPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return rpPopupView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig rpPopupView, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> rpPopupComponentList = new ArrayList<>();
		rpPopupView.setGtnComponentList(rpPopupComponentList);
		addPopupMainPanel(rpPopupComponentList, namspacePrefix);
	}

	private void addPopupMainPanel(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig.getPanelConfig(namspacePrefix + "PopUpMainPanel", false,
				null);
		rpPopupComponentList.add(panel);
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getVerticalLayoutConfig(namspacePrefix + "PopUpMainLayout", true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		rpPopupComponentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addSearchCriteriaPanel(rpPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addButtonLayout(rpPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addResultPanel(rpPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
		addActionButtonLayout(rpPopupComponentList, namspacePrefix, layoutConfig.getComponentId());

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		rpPopupComponentList.add(panelConfig);
		addFieldLayout(rpPopupComponentList, namspacePrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		rpPopupComponentList.add(layoutConfig);
		addSelectButtonComponent(rpPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
		addCloseButtonComponent(rpPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> rpPopupComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig selectBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		selectBtnConfig.setAuthorizationIncluded(true);
		selectBtnConfig.setComponentName("SELECT");
		rpPopupComponentList.add(selectBtnConfig);

		GtnUIFrameWorkActionConfig rpPopupValidationAction = new GtnUIFrameWorkActionConfig();
		rpPopupValidationAction.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		rpPopupValidationAction
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		rpPopupValidationAction.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig rpPopupSelectAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		rpPopupSelectAction.addActionParameter(GtnFrameworkPopupSelectAction.class.getName());
		rpPopupSelectAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		rpPopupSelectAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_RP_NO_VIEW);
		rpPopupSelectAction.addActionParameter(14);

		GtnUIFrameWorkActionConfig rpPopupCloseAction = new GtnUIFrameWorkActionConfig();
		rpPopupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		rpPopupCloseAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_RP_NO_VIEW);

		rpPopupValidationAction.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		rpPopupValidationAction.addActionParameter(Arrays.asList(rpPopupSelectAction, rpPopupCloseAction));

		selectBtnConfig.addGtnUIFrameWorkActionConfig(rpPopupValidationAction);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> rpPopupComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig closeBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		closeBtnConfig.setComponentName("CLOSE");
		closeBtnConfig.setAuthorizationIncluded(true);
		rpPopupComponentList.add(closeBtnConfig);

		GtnUIFrameWorkActionConfig rpPopupCloseAction = new GtnUIFrameWorkActionConfig();
		rpPopupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		rpPopupCloseAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_RP_NO_VIEW);
		closeBtnConfig.addGtnUIFrameWorkActionConfig(rpPopupCloseAction);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		rpPopupComponentList.add(layoutConfig);
		addSearchButtonComponent(rpPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addResetButtonComponent(rpPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> rpPopupComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		rpPopupComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig rpPopupValidationAction = new GtnUIFrameWorkActionConfig();
		rpPopupValidationAction.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		rpPopupValidationAction.setFieldValues(componentIdList);
		rpPopupValidationAction.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig rpPopupFailureAction = new GtnUIFrameWorkActionConfig();
		rpPopupFailureAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		rpPopupFailureAction.addActionParameter("No Search Criteria ");
		rpPopupFailureAction.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		tableLoadActionConfig.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		tableLoadActionConfig.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig searchNoticationAction = new GtnUIFrameWorkActionConfig();
		searchNoticationAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNoticationAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);

		rpPopupValidationAction.addActionParameter(Arrays.asList(rpPopupFailureAction));
		rpPopupValidationAction.addActionParameter(Arrays.asList(tableLoadActionConfig, searchNoticationAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(rpPopupValidationAction);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> rpPopupComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		rpPopupComponentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig rpPopupResetActionConfig = new GtnUIFrameWorkActionConfig();
		rpPopupResetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		rpPopupResetActionConfig.addActionParameter("Confirmation");
		rpPopupResetActionConfig
				.addActionParameter("Are you sure you want to reset the page to default/previous values  ?");
		rpPopupResetActionConfig.addActionParameter(componentIdList);
		rpPopupResetActionConfig.addActionParameter(Arrays.asList("", "", "", null, null));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(rpPopupResetActionConfig);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getCssLayoutConfig(namspacePrefix + "searchFieldLayout",
				true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		rpPopupComponentList.add(gtnLayout);
		addFieldComponent(rpPopupComponentList, namspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		rpPopupComponentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig rpPopupTableConfig = new GtnUIFrameworkPagedTableConfig();
		rpPopupTableConfig.setEditable(false);
		rpPopupTableConfig.setFilterBar(true);
		rpPopupTableConfig.setSelectable(true);
		rpPopupTableConfig.setMultiSelect(false);
		rpPopupTableConfig.setPageLength(10);
		rpPopupTableConfig.setItemPerPage(10);
		rpPopupTableConfig.setSinkItemPerPageWithPageLength(false);
		rpPopupTableConfig.setTableColumnDataType(GtnFrameworkContractDashboardContants.getRpLookupColumnType());
		rpPopupTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getRpLookupHeader());
		rpPopupTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getRpLookupColumn());
		rpPopupTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RP_LOOKUP_TABLE_DATA);
		rpPopupTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RP_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(rpPopupTableConfig);
		rpPopupComponentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addRPId(rpPopupComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addRPNo(rpPopupComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addRPName(rpPopupComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addRPStatus(rpPopupComponentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addRPType(rpPopupComponentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
	}

	private void addRPType(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RPType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		rpPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Rebate Plan Type");
		rpPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.REBATE_PLAN_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRPStatus(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RPStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		rpPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Rebate Plan Status");
		rpPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRPId(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RPID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		rpPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Rebate Plan ID");
		rpPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRPNo(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RPNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		rpPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Rebate Plan No");
		rpPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRPName(List<GtnUIFrameworkComponentConfig> rpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RPName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		rpPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Rebate Plan Name");
		rpPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
