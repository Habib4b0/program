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

public class GtnFrameworkIFPPoupupConfig {
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig ifpPopupView = new GtnUIFrameworkViewConfig();
		ifpPopupView.setViewName(GtnFrameworkContractDashboardContants.CD_IFP_VIEW);
		ifpPopupView.setViewId(GtnFrameworkContractDashboardContants.CD_IFP_VIEW);
		addComponentList(ifpPopupView, ifpPopupView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		ifpPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		ifpPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return ifpPopupView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> ifpPopupComponentList = new ArrayList<>();
		view.setGtnComponentList(ifpPopupComponentList);
		addPopupMainPanel(ifpPopupComponentList, namspacePrefix);
	}

	private void addPopupMainPanel(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig.getPanelConfig(namspacePrefix + "PopUpMainPanel", false,
				null);
		ifpPopupComponentList.add(panel);
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getVerticalLayoutConfig(namspacePrefix + "PopUpMainLayout", true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		ifpPopupComponentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addSearchCriteriaPanel(ifpPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addButtonLayout(ifpPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addResultPanel(ifpPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
		addActionButtonLayout(ifpPopupComponentList, namspacePrefix, layoutConfig.getComponentId());

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		ifpPopupComponentList.add(panelConfig);
		addFieldLayout(ifpPopupComponentList, namspacePrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		ifpPopupComponentList.add(layoutConfig);
		addSelectButtonComponent(ifpPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
		addCloseButtonComponent(ifpPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig ifpPopupSelectBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		ifpPopupSelectBtnConfig.setComponentName("SELECT");
		ifpPopupSelectBtnConfig.setAuthorizationIncluded(true);
		ifpPopupComponentList.add(ifpPopupSelectBtnConfig);

		GtnUIFrameWorkActionConfig ifpPopupSearchValidationAction = new GtnUIFrameWorkActionConfig();
		ifpPopupSearchValidationAction.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		ifpPopupSearchValidationAction
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		ifpPopupSearchValidationAction.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig selectAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectAction.addActionParameter(GtnFrameworkPopupSelectAction.class.getName());
		selectAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		selectAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_IFP_VIEW);
		selectAction.addActionParameter(0);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_IFP_VIEW);

		ifpPopupSearchValidationAction.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		ifpPopupSearchValidationAction.addActionParameter(Arrays.asList(selectAction, closeAction));

		ifpPopupSelectBtnConfig.addGtnUIFrameWorkActionConfig(ifpPopupSearchValidationAction);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig ifpPopupCloseBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		ifpPopupCloseBtnConfig.setComponentName("CLOSE");
		ifpPopupCloseBtnConfig.setAuthorizationIncluded(true);
		ifpPopupComponentList.add(ifpPopupCloseBtnConfig);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_IFP_VIEW);
		ifpPopupCloseBtnConfig.addGtnUIFrameWorkActionConfig(closeAction);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		ifpPopupComponentList.add(layoutConfig);
		addSearchButtonComponent(ifpPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addResetButtonComponent(ifpPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		ifpPopupComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig ifpPopupSearchValidationAction = new GtnUIFrameWorkActionConfig();
		ifpPopupSearchValidationAction.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		ifpPopupSearchValidationAction.setFieldValues(componentIdList);
		ifpPopupSearchValidationAction.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig ifpPopupSearchFailureAction = new GtnUIFrameWorkActionConfig();
		ifpPopupSearchFailureAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		ifpPopupSearchFailureAction.addActionParameter("No Search Criteria ");
		ifpPopupSearchFailureAction.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig ifpPopupTableLoadAction = new GtnUIFrameWorkActionConfig();
		ifpPopupTableLoadAction.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		ifpPopupTableLoadAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		ifpPopupTableLoadAction.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig searchNoticationAction = new GtnUIFrameWorkActionConfig();
		searchNoticationAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNoticationAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);

		ifpPopupSearchValidationAction.addActionParameter(Arrays.asList(ifpPopupSearchFailureAction));
		ifpPopupSearchValidationAction
				.addActionParameter(Arrays.asList(ifpPopupTableLoadAction, searchNoticationAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(ifpPopupSearchValidationAction);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		ifpPopupComponentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig ifpPopupResetActionConfig = new GtnUIFrameWorkActionConfig();
		ifpPopupResetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		ifpPopupResetActionConfig.addActionParameter("Confirmation");
		ifpPopupResetActionConfig.addActionParameter(GtnFrameworkContractDashboardContants.RESET_CONSTANT);
		ifpPopupResetActionConfig.addActionParameter(componentIdList);
		ifpPopupResetActionConfig.addActionParameter(Arrays.asList("", "", "", null, null));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(ifpPopupResetActionConfig);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getCssLayoutConfig(namspacePrefix + "searchFieldLayout",
				true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		ifpPopupComponentList.add(gtnLayout);
		addFieldComponent(ifpPopupComponentList, namspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		ifpPopupComponentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig ifpPopupPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		ifpPopupPagedTableConfig.setEditable(false);
		ifpPopupPagedTableConfig.setFilterBar(true);
		ifpPopupPagedTableConfig.setSelectable(true);
		ifpPopupPagedTableConfig.setMultiSelect(false);
		ifpPopupPagedTableConfig.setPageLength(10);
		ifpPopupPagedTableConfig.setItemPerPage(10);
		ifpPopupPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		ifpPopupPagedTableConfig.setTableColumnDataType(GtnFrameworkContractDashboardContants.getIfpLookupColumnType());
		ifpPopupPagedTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getIfpLookupHeader());
		ifpPopupPagedTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getIfpLookupColumn());
		ifpPopupPagedTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_IFP_LOOKUP_TABLE_DATA);
		ifpPopupPagedTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_IFP_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(ifpPopupPagedTableConfig);
		ifpPopupComponentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addIfpId(ifpPopupComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addIfpNo(ifpPopupComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addIfpName(ifpPopupComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addIfpStatus(ifpPopupComponentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addIfpType(ifpPopupComponentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
	}

	private void addIfpType(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "IFPType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		ifpPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("IFP Type");
		ifpPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.IFP_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addIfpStatus(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "IFPStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		ifpPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("IFP Status");
		ifpPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addIfpId(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "IFPID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		ifpPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("IFP ID");
		ifpPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addIfpNo(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "IFPNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		ifpPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("IFP No");
		ifpPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addIfpName(List<GtnUIFrameworkComponentConfig> ifpPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "IFPName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		ifpPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("IFP Name");
		ifpPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
