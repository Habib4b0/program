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

public class GtnFrameworkNSRPoupupConfig {
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig nsrPopupView = commonConfig.getViewConfig(
				GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW,
				GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW, false);
		addComponentList(nsrPopupView, nsrPopupView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		nsrPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		nsrPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return nsrPopupView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> nsrPopupComponentList = new ArrayList<>();
		view.setGtnComponentList(nsrPopupComponentList);
		addPopupMainPanel(nsrPopupComponentList, namspacePrefix);
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkNSRPopupLoadAction.class.getName());
		customAction.addActionParameter(namspacePrefix + "RuleType");
		customAction.addActionParameter(view.getViewId());
		view.addViewAction(customAction);
	}

	private void addPopupMainPanel(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig.getPanelConfig(namspacePrefix + "PopUpMainPanel", false,
				null);
		nsrPopupComponentList.add(panel);
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getVerticalLayoutConfig(namspacePrefix + "PopUpMainLayout", true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		nsrPopupComponentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addSearchCriteriaPanel(nsrPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addButtonLayout(nsrPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addMainResultPanel(nsrPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
		addActionButtonLayout(nsrPopupComponentList, namspacePrefix, layoutConfig.getComponentId());

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Net Sales Rule Search");
		panelConfig.setAuthorizationIncluded(true);
		nsrPopupComponentList.add(panelConfig);
		addFieldLayout(nsrPopupComponentList, namspacePrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		nsrPopupComponentList.add(layoutConfig);
		addSelectButtonComponent(nsrPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
		addCloseButtonComponent(nsrPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
		addDetailsButtonComponent(nsrPopupComponentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig selectBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		selectBtnConfig.setAuthorizationIncluded(true);
		selectBtnConfig.setComponentName("SELECT");
		nsrPopupComponentList.add(selectBtnConfig);

		GtnUIFrameWorkActionConfig nsrPopupValidationActionConfig = new GtnUIFrameWorkActionConfig();
		nsrPopupValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		nsrPopupValidationActionConfig
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		nsrPopupValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig nsrPopupSelectAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		nsrPopupSelectAction.addActionParameter(GtnFrameworkPopupSelectAction.class.getName());
		nsrPopupSelectAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		nsrPopupSelectAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW);
		nsrPopupSelectAction.addActionParameter(4);

		GtnUIFrameWorkActionConfig nsrPopupCloseAction = new GtnUIFrameWorkActionConfig();
		nsrPopupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		nsrPopupCloseAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW);

		nsrPopupValidationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		nsrPopupValidationActionConfig.addActionParameter(Arrays.asList(nsrPopupSelectAction, nsrPopupCloseAction));

		selectBtnConfig.addGtnUIFrameWorkActionConfig(nsrPopupValidationActionConfig);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig closeBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		closeBtnConfig.setComponentName("CLOSE");
		nsrPopupComponentList.add(closeBtnConfig);

		GtnUIFrameWorkActionConfig nsrPopupCloseAction = new GtnUIFrameWorkActionConfig();
		nsrPopupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		nsrPopupCloseAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_NS_RULE_VIEW);
		closeBtnConfig.addGtnUIFrameWorkActionConfig(nsrPopupCloseAction);
	}

	private void addDetailsButtonComponent(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "detailsBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("DETAILS");
		searchButtonConfig.setAuthorizationIncluded(true);
		nsrPopupComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig nsrPopupValidationActionConfig = new GtnUIFrameWorkActionConfig();
		nsrPopupValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		nsrPopupValidationActionConfig
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		nsrPopupValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig nsrPopupTableLoadAction = new GtnUIFrameWorkActionConfig();
		nsrPopupTableLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		nsrPopupTableLoadAction.addActionParameter(GtnUIFrameworkLoadTableFromAnTableValueAction.class.getName());
		nsrPopupTableLoadAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		nsrPopupTableLoadAction.addActionParameter(4);
		nsrPopupTableLoadAction
				.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.DETAILS_RESULT_TABLE);

		nsrPopupValidationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		nsrPopupValidationActionConfig.addActionParameter(Arrays.asList(nsrPopupTableLoadAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(nsrPopupValidationActionConfig);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		nsrPopupComponentList.add(layoutConfig);
		addSearchButtonComponent(nsrPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addResetButtonComponent(nsrPopupComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		nsrPopupComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig nsrPopupValidationActionConfig = new GtnUIFrameWorkActionConfig();
		nsrPopupValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		nsrPopupValidationActionConfig.setFieldValues(componentIdList);
		nsrPopupValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig nsrPopupfailureAction = new GtnUIFrameWorkActionConfig();
		nsrPopupfailureAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		nsrPopupfailureAction.addActionParameter("No Search Criteria ");
		nsrPopupfailureAction.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig resetTableActionConfig = new GtnUIFrameWorkActionConfig();
		resetTableActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
		resetTableActionConfig.addActionParameter(
				Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.DETAILS_RESULT_TABLE));
		resetTableActionConfig.addActionParameter(Arrays.asList(new Object[] { null }));

		GtnUIFrameWorkActionConfig nsrPopupTableLoadAction = new GtnUIFrameWorkActionConfig();
		nsrPopupTableLoadAction.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		nsrPopupTableLoadAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		nsrPopupTableLoadAction.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig searchNoticationAction = new GtnUIFrameWorkActionConfig();
		searchNoticationAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNoticationAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);

		nsrPopupValidationActionConfig.addActionParameter(Arrays.asList(nsrPopupfailureAction));
		nsrPopupValidationActionConfig.addActionParameter(
				Arrays.asList(resetTableActionConfig, nsrPopupTableLoadAction, searchNoticationAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(nsrPopupValidationActionConfig);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig nsrPopupResetButton = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		nsrPopupResetButton.setComponentName("RESET");
		nsrPopupResetButton.setAuthorizationIncluded(true);
		nsrPopupComponentList.add(nsrPopupResetButton);

		GtnUIFrameWorkActionConfig nsrPopupResetAction = new GtnUIFrameWorkActionConfig();
		nsrPopupResetAction.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		nsrPopupResetAction.addActionParameter("Reset Confirmation");
		nsrPopupResetAction.addActionParameter(GtnFrameworkContractDashboardContants.RESETMSG);
		nsrPopupResetAction.addActionParameter(componentIdList);
		nsrPopupResetAction.addActionParameter(Arrays.asList(null, "", "", null));
		nsrPopupResetButton.addGtnUIFrameWorkActionConfig(nsrPopupResetAction);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getCssLayoutConfig(namspacePrefix + "searchFieldLayout",
				true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		nsrPopupComponentList.add(gtnLayout);
		addFieldComponent(nsrPopupComponentList, namspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addMainResultPanel(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(namspacePrefix + "resultPanel", true,
				parent);
		nsrPopupComponentList.add(panelConfig);
		GtnUIFrameworkComponentConfig gtnMainCssLayout = commonConfig
				.getCssLayoutConfig(namspacePrefix + "resultLayout", true, panelConfig.getComponentId());
		gtnMainCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		nsrPopupComponentList.add(gtnMainCssLayout);

		GtnUIFrameworkComponentConfig gtnLeftCssLayout = commonConfig
				.getCssLayoutConfig(namspacePrefix + "leftResultLayout", true, gtnMainCssLayout.getComponentId());
		gtnLeftCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		nsrPopupComponentList.add(gtnLeftCssLayout);
		GtnUIFrameworkComponentConfig gtnRightCssLayout = commonConfig
				.getCssLayoutConfig(namspacePrefix + "rightResultLayout", true, gtnMainCssLayout.getComponentId());
		gtnRightCssLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_6);
		nsrPopupComponentList.add(gtnRightCssLayout);
		addResultPanel(nsrPopupComponentList, namspacePrefix, gtnLeftCssLayout.getComponentId());
		addDetailResultPanel(nsrPopupComponentList, namspacePrefix, gtnRightCssLayout.getComponentId());
	}

	private void addDetailResultPanel(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkContractDashboardContants.DETAILS_RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		panelConfig.setComponentName("Rule Details");
		panelConfig.setAuthorizationIncluded(true);
		nsrPopupComponentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig nsrPopupTableConfig = new GtnUIFrameworkPagedTableConfig();
		nsrPopupTableConfig.setEditable(false);
		nsrPopupTableConfig.setFilterBar(true);
		nsrPopupTableConfig.setSelectable(false);
		nsrPopupTableConfig.setPageLength(5);
		nsrPopupTableConfig.setItemPerPage(5);
		nsrPopupTableConfig.setSinkItemPerPageWithPageLength(false);
		nsrPopupTableConfig
				.setTableColumnDataType(GtnFrameworkContractDashboardContants.getRuleDetailsLookupColumnType());
		nsrPopupTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getRuleDetailsLookupHeader());
		nsrPopupTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getRuleDetailsLookupColumn());
		nsrPopupTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RULE_DETAILS_LOOKUP_TABLE_DATA);
		nsrPopupTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RULE_DETAILS_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(nsrPopupTableConfig);
		nsrPopupComponentList.add(componentConfig);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		nsrPopupComponentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig nsrPopupTableConfig = new GtnUIFrameworkPagedTableConfig();
		nsrPopupTableConfig.setEditable(false);
		nsrPopupTableConfig.setFilterBar(true);
		nsrPopupTableConfig.setSelectable(true);
		nsrPopupTableConfig.setPageLength(5);
		nsrPopupTableConfig.setItemPerPage(5);
		nsrPopupTableConfig.setSinkItemPerPageWithPageLength(false);
		nsrPopupTableConfig.setTableColumnDataType(GtnFrameworkContractDashboardContants.getRuleLookupColumnType());
		nsrPopupTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getRuleLookupHeader());
		nsrPopupTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getRuleLookupColumn());
		nsrPopupTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RULES_LOOKUP_TABLE_DATA);
		nsrPopupTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RULES_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(nsrPopupTableConfig);
		nsrPopupComponentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addRuleType(nsrPopupComponentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addRuleNo(nsrPopupComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addRuleName(nsrPopupComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addRuleCategory(nsrPopupComponentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
	}

	private void addRuleType(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RuleType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		nsrPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setComponentName("Rule Type");
		componentConfig.setAuthorizationIncluded(true);
		nsrPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RULE_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRuleCategory(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RuleCategory";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		nsrPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Rule Category");
		nsrPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RULE_CATEGORY);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRuleNo(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RuleNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		nsrPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Rule No");
		nsrPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRuleName(List<GtnUIFrameworkComponentConfig> nsrPopupComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RuleName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		nsrPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Rule Name");
		nsrPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
