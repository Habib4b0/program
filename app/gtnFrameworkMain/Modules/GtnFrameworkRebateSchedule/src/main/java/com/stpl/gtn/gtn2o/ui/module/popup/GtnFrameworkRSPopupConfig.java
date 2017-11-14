package com.stpl.gtn.gtn2o.ui.module.popup;

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
import com.stpl.gtn.gtn2o.ui.module.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;

public class GtnFrameworkRSPopupConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(GtnFrameworkRSConstants.RS_POPUP_VIEW,
				GtnFrameworkRSConstants.RS_POPUP_VIEW, false);
		addRsPopupComponentList(view, view.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		view.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		view.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return view;
	}

	private void addRsPopupComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
		List<GtnUIFrameworkComponentConfig> rsPopupcomponentList = new ArrayList<>();
		view.setGtnComponentList(rsPopupcomponentList);
		addRsPopupMainPanel(rsPopupcomponentList, namspacePrefix);
	}

	private void addRsPopupMainPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
		GtnUIFrameworkComponentConfig popUpMainLayoutpanel = configProvider
				.getPanelConfig(namspacePrefix + "PopUpMainPanel", false, null);
		componentList.add(popUpMainLayoutpanel);
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				namspacePrefix + "PopUpMainLayout", true, popUpMainLayoutpanel.getComponentId());
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addRsPopupSearchCriteriaPanel(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addRsPopupButtonLayout(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addRsPopupResultPanel(componentList, namspacePrefix, layoutConfig.getComponentId());
		addRsPopupActionButtonLayout(componentList, namspacePrefix, layoutConfig.getComponentId());

	}

	private void addRsPopupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(namspacePrefix + "SearchPanel", true,
				parent);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addRsPopupFieldLayout(componentList, namspacePrefix, componentIdList, panelConfig.getComponentId());

	}

	private void addRsPopupActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getHorizontalLayoutConfig(namspacePrefix + "actionButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);
		addRsPopupSelectButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
		addRsPopupCloseButtonComponent(componentList, namspacePrefix, layoutConfig.getComponentId());
	}

	private void addRsPopupSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig rsPopupSelectBtnConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		rsPopupSelectBtnConfig.setComponentName("SELECT");
		rsPopupSelectBtnConfig.setAuthorizationIncluded(true);
		componentList.add(rsPopupSelectBtnConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(namspacePrefix + GtnFrameworkCommonConstants.RESULT_TABLE));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig rsPopupSelectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		rsPopupSelectAction.addActionParameter(GtnFrameworkCustomPopupSelectAction.class.getName());
		rsPopupSelectAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RESULT_TABLE);
		rsPopupSelectAction.addActionParameter(GtnFrameworkRSConstants.RS_POPUP_VIEW);
		rsPopupSelectAction.addActionParameter(0);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkRSConstants.RS_POPUP_VIEW);

		validationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		validationActionConfig.addActionParameter(Arrays.asList(rsPopupSelectAction, closeAction));

		rsPopupSelectBtnConfig.addGtnUIFrameWorkActionConfig(validationActionConfig);
	}

	private void addRsPopupCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig closeBtnConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		closeBtnConfig.setComponentName("CLOSE");
		closeBtnConfig.setAuthorizationIncluded(true);
		componentList.add(closeBtnConfig);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkRSConstants.RS_POPUP_VIEW);
		closeBtnConfig.addGtnUIFrameWorkActionConfig(closeAction);
	}

	private void addRsPopupButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider
				.getHorizontalLayoutConfig(namspacePrefix + "searchButtonlayout", true, parent);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(layoutConfig);
		addRsPopupSearchButtonComponent(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
		addRsPopupResetButtonComponent(componentList, namspacePrefix, componentIdList, layoutConfig.getComponentId());
	}

	private void addRsPopupSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig rsPopupSearchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		rsPopupSearchButtonConfig.setComponentName("SEARCH");
		rsPopupSearchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(rsPopupSearchButtonConfig);

		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(componentIdList);
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig failureActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		failureActionConfig.addActionParameter("No Search Criteria ");
		failureActionConfig.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig tableLoadActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		tableLoadActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RESULT_TABLE);
		tableLoadActionConfig.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig notificationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(" Search Completed ");
		notificationActionConfig.addActionParameter("");

		validationActionConfig.addActionParameter(Arrays.asList(failureActionConfig));
		validationActionConfig.addActionParameter(Arrays.asList(tableLoadActionConfig, notificationActionConfig));

		rsPopupSearchButtonConfig.addGtnUIFrameWorkActionConfig(validationActionConfig);
	}

	private void addRsPopupResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				namspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig rsPopupresetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		rsPopupresetActionConfig.addActionParameter("Confirmation");
		rsPopupresetActionConfig
				.addActionParameter("Are you sure you want to reset the page to default/previous values  ?");
		rsPopupresetActionConfig.addActionParameter(componentIdList);
		rsPopupresetActionConfig.addActionParameter(Arrays.asList("", "", "", null, null, null));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(rsPopupresetActionConfig);
	}

	private void addRsPopupFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(namspacePrefix + "searchFieldLayout", true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(gtnLayout);
		addRsPopupFieldComponent(componentList, namspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addRsPopupResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			String parent) {
		String componentId = namspacePrefix + GtnFrameworkCommonConstants.RESULT_TABLE;
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(componentId + "Panel", true, parent);
		componentList.add(panelConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				panelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig pagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		pagedTableConfig.setEditable(false);
		pagedTableConfig.setFilterBar(true);
		pagedTableConfig.setSelectable(true);
		pagedTableConfig.setMultiSelect(false);
		pagedTableConfig.setPageLength(10);
		pagedTableConfig.setItemPerPage(10);
		pagedTableConfig.setSinkItemPerPageWithPageLength(false);
		pagedTableConfig.setTableColumnDataType(GtnFrameworkRSConstants.getRsLookUpColumnType());
		pagedTableConfig.setTableVisibleHeader(GtnFrameworkRSConstants.getRsLookUpHeader());
		pagedTableConfig.setTableColumnMappingId(GtnFrameworkRSConstants.getRsLookUpColumn());
		pagedTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RS_LOOKUP_TABLE_DATA);
		pagedTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_RS_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(pagedTableConfig);
		componentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addRsPopupFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addRsPopupRsId(componentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addRsPopupRsNo(componentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addRsPopupRsName(componentList, namspacePrefix, componentIdList, parent, emptyValidationConfig);
		addRsPopupRsStatus(componentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addRsPopupRsType(componentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
		addRsPopupProgramType(componentList, namspacePrefix, componentIdList, parent, nullValidationConfig);
	}

	private void addRsPopupRsType(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RSType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Rebate Schedule Type");
		componentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.RS_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRsPopupRsStatus(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RSStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Rebate Schedule Status");
		componentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRsPopupProgramType(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "ProgramType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = configProvider.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Program Type");
		componentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.REBATE_PROGRAM_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRsPopupRsId(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RSID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rsIdcomponentConfig = configProvider.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		rsIdcomponentConfig.setAuthorizationIncluded(true);
		rsIdcomponentConfig.setComponentName("Rebate Schedule ID");
		componentList.add(rsIdcomponentConfig);
		componentIdList.add(rsIdcomponentConfig.getComponentId());
		rsIdcomponentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRsPopupRsNo(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RSNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rsNocomponentConfig = configProvider.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		rsNocomponentConfig.setAuthorizationIncluded(true);
		rsNocomponentConfig.setComponentName("Rebate Schedule No");
		componentList.add(rsNocomponentConfig);
		componentIdList.add(rsNocomponentConfig.getComponentId());
		rsNocomponentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addRsPopupRsName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = namspacePrefix + "RSName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = configProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig rsNamecomponentConfig = configProvider.getUIFrameworkComponentConfig(componentId,
				true, gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		rsNamecomponentConfig.setAuthorizationIncluded(true);
		rsNamecomponentConfig.setComponentName("Rebate Schedule Name");
		componentList.add(rsNamecomponentConfig);
		componentIdList.add(rsNamecomponentConfig.getComponentId());
		rsNamecomponentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
