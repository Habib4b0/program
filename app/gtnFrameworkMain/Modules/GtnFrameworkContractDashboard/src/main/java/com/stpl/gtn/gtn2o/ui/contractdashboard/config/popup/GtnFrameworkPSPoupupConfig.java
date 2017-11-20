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

public class GtnFrameworkPSPoupupConfig {
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig psPopupView = commonConfig.getViewConfig(
				GtnFrameworkContractDashboardContants.CD_PS_VIEW, GtnFrameworkContractDashboardContants.CD_PS_VIEW,
				false);
		addComponentList(psPopupView, psPopupView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		psPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		psPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return psPopupView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> psPopupViewComponentList = new ArrayList<>();
		view.setGtnComponentList(psPopupViewComponentList);
		addPopupMainPanel(psPopupViewComponentList, namspacePrefix);
	}

	private void addPopupMainPanel(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList,
			String namspacePrefix) {
		GtnUIFrameworkComponentConfig panel = commonConfig.getPanelConfig(namspacePrefix + "PopUpMainPanel", false,
				null);
		psPopupViewComponentList.add(panel);
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getVerticalLayoutConfig(namspacePrefix + "PopUpMainLayout", true, panel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		psPopupViewComponentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addSearchCriteriaPanel(psPopupViewComponentList, namspacePrefix, componentIdList,
				layoutConfig.getComponentId());
		addButtonLayout(psPopupViewComponentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addResultPanel(psPopupViewComponentList, namspacePrefix, layoutConfig.getComponentId());
		addActionButtonLayout(psPopupViewComponentList, namspacePrefix, layoutConfig.getComponentId());

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		psPopupViewComponentList.add(panelConfig);
		addFieldLayout(psPopupViewComponentList, namspacePrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		psPopupViewComponentList.add(layoutConfig);
		addSelectButtonComponent(psPopupViewComponentList, namspacePrefix, layoutConfig.getComponentId());
		addCloseButtonComponent(psPopupViewComponentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig selectBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		selectBtnConfig.setComponentName("SELECT");
		selectBtnConfig.setAuthorizationIncluded(true);
		psPopupViewComponentList.add(selectBtnConfig);

		GtnUIFrameWorkActionConfig psPopupValidationAction = new GtnUIFrameWorkActionConfig();
		psPopupValidationAction.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		psPopupValidationAction
				.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		psPopupValidationAction.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig selectAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectAction.addActionParameter(GtnFrameworkPopupSelectAction.class.getName());
		selectAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		selectAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_PS_VIEW);
		selectAction.addActionParameter(0);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_PS_VIEW);

		psPopupValidationAction.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		psPopupValidationAction.addActionParameter(Arrays.asList(selectAction, closeAction));

		selectBtnConfig.addGtnUIFrameWorkActionConfig(psPopupValidationAction);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig closeBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		closeBtnConfig.setComponentName("CLOSE");
		closeBtnConfig.setAuthorizationIncluded(true);
		psPopupViewComponentList.add(closeBtnConfig);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_PS_VIEW);
		closeBtnConfig.addGtnUIFrameWorkActionConfig(closeAction);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = commonConfig
				.getHorizontalLayoutConfig(namspacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		psPopupViewComponentList.add(layoutConfig);
		addSearchButtonComponent(psPopupViewComponentList, namspacePrefix, componentIdList,
				layoutConfig.getComponentId());
		addResetButtonComponent(psPopupViewComponentList, namspacePrefix, componentIdList,
				layoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		psPopupViewComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig psPopupValidationAction = new GtnUIFrameWorkActionConfig();
		psPopupValidationAction.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		psPopupValidationAction.setFieldValues(componentIdList);
		psPopupValidationAction.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig psPopupFailureAction = new GtnUIFrameWorkActionConfig();
		psPopupFailureAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		psPopupFailureAction.addActionParameter("No Search Criteria ");
		psPopupFailureAction.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig psPopupTableLoadAction = new GtnUIFrameWorkActionConfig();
		psPopupTableLoadAction.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		psPopupTableLoadAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		psPopupTableLoadAction.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig searchNoticationAction = new GtnUIFrameWorkActionConfig();
		searchNoticationAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNoticationAction.addActionParameter(namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);

		psPopupValidationAction.addActionParameter(Arrays.asList(psPopupFailureAction));
		psPopupValidationAction.addActionParameter(Arrays.asList(psPopupTableLoadAction, searchNoticationAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(psPopupValidationAction);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		psPopupViewComponentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig psPopupResetAction = new GtnUIFrameWorkActionConfig();
		psPopupResetAction.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		psPopupResetAction.addActionParameter("Confirmation");
		psPopupResetAction.addActionParameter("Are you sure you want to reset the page to default/previous values  ?");
		psPopupResetAction.addActionParameter(componentIdList);
		psPopupResetAction.addActionParameter(Arrays.asList("", "", "", null, null, "", "", ""));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(psPopupResetAction);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig.getCssLayoutConfig(namspacePrefix + "searchFieldLayout",
				true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		psPopupViewComponentList.add(gtnLayout);
		addFieldComponent(psPopupViewComponentList, namspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = commonConfig.getPanelConfig(componentId + "Panel", true, parent);
		psPopupViewComponentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig psPopupTableConfig = new GtnUIFrameworkPagedTableConfig();
		psPopupTableConfig.setEditable(false);
		psPopupTableConfig.setFilterBar(true);
		psPopupTableConfig.setSelectable(true);
		psPopupTableConfig.setPageLength(10);
		psPopupTableConfig.setItemPerPage(10);
		psPopupTableConfig.setSinkItemPerPageWithPageLength(false);
		psPopupTableConfig.setTableColumnDataType(GtnFrameworkContractDashboardContants.getPsLookupColumnType());
		psPopupTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getPsLookupHeader());
		psPopupTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getPsLookupColumn());
		psPopupTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_PS_LOOKUP_TABLE_DATA);
		psPopupTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_PS_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(psPopupTableConfig);
		psPopupViewComponentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addPsId(psPopupViewComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addPsNo(psPopupViewComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addPsName(psPopupViewComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addPsType(psPopupViewComponentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addPsStatus(psPopupViewComponentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addItemId(psPopupViewComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addItemNo(psPopupViewComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addItemName(psPopupViewComponentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);

	}

	private void addPsType(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "PSType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		psPopupViewComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule Type");
		psPopupViewComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.PS_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addPsStatus(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "PSStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		psPopupViewComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule Status");
		psPopupViewComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addPsId(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "PSID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		psPopupViewComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule ID");
		psPopupViewComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addPsNo(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "PSNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		psPopupViewComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule No");
		psPopupViewComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addPsName(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "PSName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		psPopupViewComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Price Schedule Name");
		psPopupViewComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addItemId(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "ItemID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		psPopupViewComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Item ID");
		psPopupViewComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addItemNo(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "ItemNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		psPopupViewComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Item No");
		psPopupViewComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addItemName(List<GtnUIFrameworkComponentConfig> psPopupViewComponentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "ItemName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		psPopupViewComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Item Name");
		psPopupViewComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
