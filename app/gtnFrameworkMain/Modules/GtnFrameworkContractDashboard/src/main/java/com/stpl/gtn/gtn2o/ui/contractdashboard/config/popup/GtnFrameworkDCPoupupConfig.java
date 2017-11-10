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

public class GtnFrameworkDCPoupupConfig {
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig dCPopupView = new GtnUIFrameworkViewConfig();
		dCPopupView.setViewName(GtnFrameworkContractDashboardContants.CD_DC_NO_VIEW);
		dCPopupView.setViewId(GtnFrameworkContractDashboardContants.CD_DC_NO_VIEW);
		addComponentList(dCPopupView, dCPopupView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		dCPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		dCPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return dCPopupView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String dCPopupNamespacePrefix) {
		List<GtnUIFrameworkComponentConfig> dCPopupComponentList = new ArrayList<>();
		view.setGtnComponentList(dCPopupComponentList);
		addDCPoupupMainPanel(dCPopupComponentList, dCPopupNamespacePrefix);
	}

	private void addDCPoupupMainPanel(List<GtnUIFrameworkComponentConfig> dCPopupComponentList,
			String dCPopupNamespacePrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig.getPanelConfig(dCPopupNamespacePrefix + "PopUpMainPanel",
				false, null);
		dCPopupComponentList.add(panel);
		GtnUIFrameworkComponentConfig dCPopupLayoutConfig = commonConfig
				.getVerticalLayoutConfig(dCPopupNamespacePrefix + "PopUpMainLayout", true, panel.getComponentId());
		dCPopupLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dCPopupComponentList.add(dCPopupLayoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addDCPoupupSearchCriteriaPanel(dCPopupComponentList, dCPopupNamespacePrefix, componentIdList,
				dCPopupLayoutConfig.getComponentId());
		addDCPoupupButtonLayout(dCPopupComponentList, dCPopupNamespacePrefix, componentIdList,
				dCPopupLayoutConfig.getComponentId());
		addResultPanel(dCPopupComponentList, dCPopupNamespacePrefix, dCPopupLayoutConfig.getComponentId());
		addDCPoupupActionButtonLayout(dCPopupComponentList, dCPopupNamespacePrefix,
				dCPopupLayoutConfig.getComponentId());
	}

	private void addDCPoupupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> dCPopupComponentList,
			String dCPopupNamespacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(dCPopupNamespacePrefix + "SearchPanel",
				true, parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		dCPopupComponentList.add(panelConfig);
		addFieldLayout(dCPopupComponentList, dCPopupNamespacePrefix, componentIdList, panelConfig.getComponentId());
	}

	private void addDCPoupupActionButtonLayout(List<GtnUIFrameworkComponentConfig> dCPopupComponentList,
			String dCPopupNamespacePrefix, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(dCPopupNamespacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dCPopupComponentList.add(layoutConfig);
		addDCPoupupSelectButtonComponent(dCPopupComponentList, dCPopupNamespacePrefix, layoutConfig.getComponentId());
		addDCPoupupCloseButtonComponent(dCPopupComponentList, dCPopupNamespacePrefix, layoutConfig.getComponentId());
	}

	private void addDCPoupupSelectButtonComponent(List<GtnUIFrameworkComponentConfig> dCPopupComponentList,
			String dCPopupNamespacePrefix, String parent) {
		GtnUIFrameworkComponentConfig dCPoupupSelectBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				dCPopupNamespacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		dCPoupupSelectBtnConfig.setComponentName("SELECT");
		dCPoupupSelectBtnConfig.setAuthorizationIncluded(true);
		dCPopupComponentList.add(dCPoupupSelectBtnConfig);

		GtnUIFrameWorkActionConfig dCPopupValidationActionConfig = new GtnUIFrameWorkActionConfig();
		dCPopupValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		dCPopupValidationActionConfig.setFieldValues(
				Arrays.asList(dCPopupNamespacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		dCPopupValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig dCPopupSelectAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dCPopupSelectAction.addActionParameter(GtnFrameworkPopupSelectAction.class.getName());
		dCPopupSelectAction
				.addActionParameter(dCPopupNamespacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		dCPopupSelectAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_DC_NO_VIEW);
		dCPopupSelectAction.addActionParameter(8);

		GtnUIFrameWorkActionConfig dCPopupCloseAction = new GtnUIFrameWorkActionConfig();
		dCPopupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		dCPopupCloseAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_DC_NO_VIEW);

		dCPopupValidationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		dCPopupValidationActionConfig.addActionParameter(Arrays.asList(dCPopupSelectAction, dCPopupCloseAction));

		dCPoupupSelectBtnConfig.addGtnUIFrameWorkActionConfig(dCPopupValidationActionConfig);
	}

	private void addDCPoupupCloseButtonComponent(List<GtnUIFrameworkComponentConfig> dCPopupComponentList,
			String dCPopupNamespacePrefix, String parent) {
		GtnUIFrameworkComponentConfig closeBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				dCPopupNamespacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		closeBtnConfig.setComponentName("CLOSE");
		closeBtnConfig.setAuthorizationIncluded(true);
		dCPopupComponentList.add(closeBtnConfig);

		GtnUIFrameWorkActionConfig dCPopupCloseAction = new GtnUIFrameWorkActionConfig();
		dCPopupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		dCPopupCloseAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_DC_NO_VIEW);
		closeBtnConfig.addGtnUIFrameWorkActionConfig(dCPopupCloseAction);
	}

	private void addDCPoupupButtonLayout(List<GtnUIFrameworkComponentConfig> dCPopupComponentList,
			String dCPopupNamespacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(dCPopupNamespacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dCPopupComponentList.add(layoutConfig);
		addSearchButtonComponent(dCPopupComponentList, dCPopupNamespacePrefix, componentIdList,
				layoutConfig.getComponentId());
		addResetButtonComponent(dCPopupComponentList, dCPopupNamespacePrefix, componentIdList,
				layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> dCPopupComponentList,
			String dCPopupNamespacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				dCPopupNamespacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		dCPopupComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig dCPopupValidationActionConfig = new GtnUIFrameWorkActionConfig();
		dCPopupValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		dCPopupValidationActionConfig.setFieldValues(componentIdList);
		dCPopupValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig dCPopupFailureActionConfig = new GtnUIFrameWorkActionConfig();
		dCPopupFailureActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		dCPopupFailureActionConfig.addActionParameter("No Search Criteria ");
		dCPopupFailureActionConfig.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		tableLoadActionConfig
				.addActionParameter(dCPopupNamespacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		tableLoadActionConfig.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig searchNoticationAction = new GtnUIFrameWorkActionConfig();
		searchNoticationAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNoticationAction
				.addActionParameter(dCPopupNamespacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);

		dCPopupValidationActionConfig.addActionParameter(Arrays.asList(dCPopupFailureActionConfig));
		dCPopupValidationActionConfig.addActionParameter(Arrays.asList(tableLoadActionConfig, searchNoticationAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(dCPopupValidationActionConfig);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> dCPopupComponentList,
			String dCPopupNamespacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				dCPopupNamespacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		dCPopupComponentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.addActionParameter("Confirmation");
		resetActionConfig.addActionParameter("Are you sure you want to reset the page to default/previous values  ?");
		resetActionConfig.addActionParameter(componentIdList);
		resetActionConfig.addActionParameter(Arrays.asList("", "", "", null));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(resetActionConfig);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> dCPopupComponentList, String dCPopupNamespacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig
				.getCssLayoutConfig(dCPopupNamespacePrefix + "searchFieldLayout", true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		dCPopupComponentList.add(gtnLayout);
		addFieldComponent(dCPopupComponentList, dCPopupNamespacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> dCPopupComponentList, String dCPopupNamespacePrefix,
			String parent) {
		String componentId = dCPopupNamespacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		dCPopupComponentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig dCPoupupTableConfig = new GtnUIFrameworkPagedTableConfig();
		dCPoupupTableConfig.setEditable(false);
		dCPoupupTableConfig.setFilterBar(true);
		dCPoupupTableConfig.setSelectable(true);
		dCPoupupTableConfig.setMultiSelect(false);
		dCPoupupTableConfig.setPageLength(10);
		dCPoupupTableConfig.setItemPerPage(10);
		dCPoupupTableConfig.setSinkItemPerPageWithPageLength(false);
		dCPoupupTableConfig.setTableColumnDataType(GtnFrameworkContractDashboardContants.getDcLookupColumnType());
		dCPoupupTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getDcLookupHeader());
		dCPoupupTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getDcLookupColumn());
		dCPoupupTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_DC_LOOKUP_TABLE_DATA);
		dCPoupupTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_DC_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(dCPoupupTableConfig);
		dCPopupComponentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> dCPopupComponentList,
			String dCPopupNamespacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addDCNo(dCPopupComponentList, dCPopupNamespacePrefix, componentIdList, parent, emptyValidationConfig);
		addDCName(dCPopupComponentList, dCPopupNamespacePrefix, componentIdList, parent, emptyValidationConfig);
		addDCDesc(dCPopupComponentList, dCPopupNamespacePrefix, componentIdList, parent, emptyValidationConfig);
		addCategory(dCPopupComponentList, dCPopupNamespacePrefix, componentIdList, parent, nullValidationConfig);
	}

	private void addCategory(List<GtnUIFrameworkComponentConfig> dCPopupComponentList, String dCPopupNamespacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = dCPopupNamespacePrefix + "Category";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		dCPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Category");
		dCPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.DEDUCTION_CALENDAR_CATEGORY);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addDCDesc(List<GtnUIFrameworkComponentConfig> dCPopupComponentList, String dCPopupNamespacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = dCPopupNamespacePrefix + "DCDesc";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		dCPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Deduction Calendar Desc");
		dCPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addDCNo(List<GtnUIFrameworkComponentConfig> dCPopupComponentList, String dCPopupNamespacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = dCPopupNamespacePrefix + "DCNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		dCPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Deduction Calendar No");
		dCPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addDCName(List<GtnUIFrameworkComponentConfig> dCPopupComponentList, String dCPopupNamespacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = dCPopupNamespacePrefix + "DCName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		dCPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Deduction Calendar Name");
		dCPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
