package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
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
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkCustomPopupSelectAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkLoadRuleDetailsAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.action.GtnFrameworkNetSaleRulePopupLoadAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

public class GtnFrameworkNetSaleRulePopupConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(GtnFrameworkRSConstants.RS_NS_RULE_VIEW,
				GtnFrameworkRSConstants.RS_NS_RULE_VIEW, false);
		addComponentList(view, view.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		view.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		view.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addPopupMainPanel(componentList, namspacePrefix);
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkNetSaleRulePopupLoadAction.class.getName());
		customAction.addActionParameter(namspacePrefix + "RuleType");
		customAction.addActionParameter(view.getViewId());
		view.addViewAction(customAction);
	}

	private void addPopupMainPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig(namspacePrefix + "PopUpMainPanel", false,
				null);
		componentList.add(panel);
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getVerticalLayoutConfig(namspacePrefix + "PopUpMainLayout", true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addSearchCriteriaPanel(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addButtonLayout(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addMainResultPanel(componentList, namspacePrefix, layoutConfig.getComponentId());
		addActionButtonLayout(componentList, namspacePrefix, layoutConfig.getComponentId());

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Net Sales Rule Search");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addFieldLayout(componentList, namspacePrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getHorizontalLayoutConfig(namspacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);
		addSelectButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
		addCloseButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
		addDetailsButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig nsrSelectBtnConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		nsrSelectBtnConfig.setComponentName("SELECT");
		nsrSelectBtnConfig.setAuthorizationIncluded(true);
		componentList.add(nsrSelectBtnConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkCommonConstants.RESULT_TABLE));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig nsrSelectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		nsrSelectAction.addActionParameter(GtnFrameworkCustomPopupSelectAction.class.getName());
		nsrSelectAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RESULT_TABLE);
		nsrSelectAction.addActionParameter(GtnFrameworkRSConstants.RS_NS_RULE_VIEW);
		nsrSelectAction.addActionParameter(4);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkRSConstants.RS_NS_RULE_VIEW);

		validationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		validationActionConfig.addActionParameter(Arrays.asList(nsrSelectAction, closeAction));

		nsrSelectBtnConfig.addGtnUIFrameWorkActionConfig(validationActionConfig);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig closeBtnConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		closeBtnConfig.setComponentName("CLOSE");
		closeBtnConfig.setAuthorizationIncluded(true);
		componentList.add(closeBtnConfig);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkRSConstants.RS_NS_RULE_VIEW);
		closeBtnConfig.addGtnUIFrameWorkActionConfig(closeAction);
	}

	private void addDetailsButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "detailsBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("DETAILS");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkCommonConstants.RESULT_TABLE));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig tableLoadActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableLoadActionConfig.addActionParameter(GtnFrameworkLoadRuleDetailsAction.class.getName());
		tableLoadActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RESULT_TABLE);
		tableLoadActionConfig.addActionParameter(4);
		tableLoadActionConfig.addActionParameter(namspacePrefix + GtnFrameworkRSConstants.DETAILS_RESULT_TABLE);

		validationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		validationActionConfig.addActionParameter(Arrays.asList(tableLoadActionConfig));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(validationActionConfig);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getHorizontalLayoutConfig(namspacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);
		addSearchButtonComponent(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addResetButtonComponent(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(componentIdList);
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig failureActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		failureActionConfig.addActionParameter("No Search Criteria ");
		failureActionConfig.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig resetTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
		resetTableActionConfig
				.addActionParameter(Arrays.asList(namspacePrefix + GtnFrameworkRSConstants.DETAILS_RESULT_TABLE));
		resetTableActionConfig.addActionParameter(Arrays.asList(new Object[] { null }));

		GtnUIFrameWorkActionConfig tableLoadActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		tableLoadActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RESULT_TABLE);
		tableLoadActionConfig.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig notificationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(" Search Completed ");
		notificationActionConfig.addActionParameter("");

		validationActionConfig.addActionParameter(Arrays.asList(failureActionConfig));
		validationActionConfig.addActionParameter(
				Arrays.asList(resetTableActionConfig, tableLoadActionConfig, notificationActionConfig));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(validationActionConfig);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.addActionParameter("Reset Confirmation");
		resetActionConfig
				.addActionParameter("Are you sure you want to reset the values in the Search Criteria group box?");
		resetActionConfig.addActionParameter(componentIdList);
		resetActionConfig.addActionParameter(Arrays.asList(null, "", "", null));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(resetActionConfig);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(namspacePrefix + "searchFieldLayout", true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(gtnLayout);
		addFieldComponent(componentList, namspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addMainResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(namspacePrefix + "resultPanel", true,
				parent);
		componentList.add(panelConfig);
		GtnUIFrameworkComponentConfig gtnMainCssLayout = configProvider
				.getCssLayoutConfig(namspacePrefix + "resultLayout", true, panelConfig.getComponentId());
		gtnMainCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(gtnMainCssLayout);

		GtnUIFrameworkComponentConfig gtnLeftCssLayout = configProvider
				.getCssLayoutConfig(namspacePrefix + "leftResultLayout", true, gtnMainCssLayout.getComponentId());
		gtnLeftCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(gtnLeftCssLayout);
		GtnUIFrameworkComponentConfig gtnRightCssLayout = configProvider
				.getCssLayoutConfig(namspacePrefix + "rightResultLayout", true, gtnMainCssLayout.getComponentId());
		gtnRightCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		componentList.add(gtnRightCssLayout);
		addResultPanel(componentList, namspacePrefix, gtnLeftCssLayout.getComponentId());
		addDetailResultPanel(componentList, namspacePrefix, gtnRightCssLayout.getComponentId());
	}

	private void addDetailResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkRSConstants.DETAILS_RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(componentId + "Panel", true, parent);
		panelConfig.setComponentName("Rule Details");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig pagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		pagedTableConfig.setEditable(false);
		pagedTableConfig.setFilterBar(true);
		pagedTableConfig.setSelectable(false);
		pagedTableConfig.setPageLength(5);
		pagedTableConfig.setItemPerPage(5);
		pagedTableConfig.setSinkItemPerPageWithPageLength(false);
		pagedTableConfig.setTableColumnDataType(GtnFrameworkRSConstants.getRuleDetailsLookUpColumnType());
		pagedTableConfig.setTableVisibleHeader(GtnFrameworkRSConstants.getRuleDetailsLookUpHeader());
		pagedTableConfig.setTableColumnMappingId(GtnFrameworkRSConstants.getRuleDetailsLookUpColumn());
		pagedTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RULE_DETAILS_LOOKUP_TABLE_DATA);
		pagedTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RULE_DETAILS_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(pagedTableConfig);
		componentList.add(componentConfig);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkCommonConstants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(componentId + "Panel", true, parent);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig pagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		pagedTableConfig.setEditable(false);
		pagedTableConfig.setFilterBar(true);
		pagedTableConfig.setSelectable(true);
		pagedTableConfig.setPageLength(5);
		pagedTableConfig.setItemPerPage(5);
		pagedTableConfig.setSinkItemPerPageWithPageLength(false);
		pagedTableConfig.setTableColumnDataType(GtnFrameworkRSConstants.getruleLookUpColumnType());
		pagedTableConfig.setTableVisibleHeader(GtnFrameworkRSConstants.getRuleLookUpHeader());
		pagedTableConfig.setTableColumnMappingId(GtnFrameworkRSConstants.getRuleLookUpColumn());
		pagedTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RULES_LOOKUP_TABLE_DATA);
		pagedTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RULES_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(pagedTableConfig);
		componentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addRuleType(componentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addRuleNo(componentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addRuleName(componentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addRuleCategory(componentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
	}

	private void addRuleType(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RuleType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setComponentName("Rule Type");
		componentConfig.setAuthorizationIncluded(true);
		componentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RULE_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRuleCategory(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RuleCategory";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setComponentName("Rule Category");
		componentConfig.setAuthorizationIncluded(true);
		componentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RULE_CATEGORY);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRuleNo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RuleNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setComponentName("Rule No");
		componentConfig.setAuthorizationIncluded(true);
		componentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRuleName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RuleName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setComponentName("Rule Name");
		componentConfig.setAuthorizationIncluded(true);
		componentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
