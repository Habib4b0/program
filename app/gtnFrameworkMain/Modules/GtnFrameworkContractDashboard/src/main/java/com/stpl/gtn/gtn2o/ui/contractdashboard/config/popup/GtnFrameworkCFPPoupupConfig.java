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

public class GtnFrameworkCFPPoupupConfig {
	private GtnFrameworkComponentConfigProvider commonConfig = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getView() {
		GtnUIFrameworkViewConfig cFPPopupView = commonConfig.getViewConfig(
				GtnFrameworkContractDashboardContants.CD_CFP_VIEW, GtnFrameworkContractDashboardContants.CD_CFP_VIEW,
				false);
		addComponentList(cFPPopupView, cFPPopupView.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		cFPPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		cFPPopupView.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		return cFPPopupView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig cFPPopupView, String cFPPopupNamspacePrefix) {
		List<GtnUIFrameworkComponentConfig> cFPPopupComponentList = new ArrayList<>();
		cFPPopupView.setGtnComponentList(cFPPopupComponentList);
		addPopupMainPanel(cFPPopupComponentList, cFPPopupNamspacePrefix);
	}

	private void addPopupMainPanel(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix) {
		GtnUIFrameworkComponentConfig cFPPopupMainPanel = commonConfig
				.getPanelConfig(cFPPopupNamspacePrefix + "PopUpMainPanel", false, null);
		cFPPopupComponentList.add(cFPPopupMainPanel);
		GtnUIFrameworkComponentConfig cFPPopupMainLayoutConfig = commonConfig.getVerticalLayoutConfig(
				cFPPopupNamspacePrefix + "PopUpMainLayout", true, cFPPopupMainPanel.getComponentId());
		cFPPopupMainLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cFPPopupComponentList.add(cFPPopupMainLayoutConfig);
		List<String> componentIdList = new ArrayList<>();
		addSearchCriteriaPanel(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList,
				cFPPopupMainLayoutConfig.getComponentId());
		addButtonLayout(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList,
				cFPPopupMainLayoutConfig.getComponentId());
		addResultPanel(cFPPopupComponentList, cFPPopupNamspacePrefix, cFPPopupMainLayoutConfig.getComponentId());
		addActionButtonLayout(cFPPopupComponentList, cFPPopupNamspacePrefix, cFPPopupMainLayoutConfig.getComponentId());

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig cFPPopupPanelConfig = commonConfig
				.getPanelConfig(cFPPopupNamspacePrefix + "SearchPanel", true, parent);
		cFPPopupPanelConfig.setComponentName("Search Criteria");
		cFPPopupPanelConfig.setAuthorizationIncluded(true);
		cFPPopupComponentList.add(cFPPopupPanelConfig);
		addFieldLayout(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList,
				cFPPopupPanelConfig.getComponentId());

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig cFPPopupActionButtonLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(cFPPopupNamspacePrefix + "actionButtonlayout", true, parent);
		cFPPopupActionButtonLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cFPPopupComponentList.add(cFPPopupActionButtonLayoutConfig);
		addSelectButtonComponent(cFPPopupComponentList, cFPPopupNamspacePrefix,
				cFPPopupActionButtonLayoutConfig.getComponentId());
		addCloseButtonComponent(cFPPopupComponentList, cFPPopupNamspacePrefix,
				cFPPopupActionButtonLayoutConfig.getComponentId());
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig selectBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				cFPPopupNamspacePrefix + "selectBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		selectBtnConfig.setComponentName("SELECT");
		selectBtnConfig.setAuthorizationIncluded(true);
		cFPPopupComponentList.add(selectBtnConfig);

		GtnUIFrameWorkActionConfig cFPPopupValidationActionConfig = new GtnUIFrameWorkActionConfig();
		cFPPopupValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		cFPPopupValidationActionConfig.setFieldValues(
				Arrays.asList(cFPPopupNamspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE));
		cFPPopupValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig cFPPopupselectAction = commonConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		cFPPopupselectAction.addActionParameter(GtnFrameworkPopupSelectAction.class.getName());
		cFPPopupselectAction
				.addActionParameter(cFPPopupNamspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		cFPPopupselectAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_CFP_VIEW);
		cFPPopupselectAction.addActionParameter(0);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_CFP_VIEW);

		cFPPopupValidationActionConfig.addActionParameter(new ArrayList<GtnUIFrameWorkActionConfig>());
		cFPPopupValidationActionConfig.addActionParameter(Arrays.asList(cFPPopupselectAction, closeAction));

		selectBtnConfig.addGtnUIFrameWorkActionConfig(cFPPopupValidationActionConfig);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix, String parent) {
		GtnUIFrameworkComponentConfig cFPPopupCloseBtnConfig = commonConfig.getUIFrameworkComponentConfig(
				cFPPopupNamspacePrefix + "closeBtn", true, parent, GtnUIFrameworkComponentType.BUTTON);
		cFPPopupCloseBtnConfig.setComponentName("CLOSE");
		cFPPopupCloseBtnConfig.setAuthorizationIncluded(true);
		cFPPopupComponentList.add(cFPPopupCloseBtnConfig);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkContractDashboardContants.CD_CFP_VIEW);
		cFPPopupCloseBtnConfig.addGtnUIFrameWorkActionConfig(closeAction);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig cFPPopupLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(cFPPopupNamspacePrefix + "searchButtonlayout", true, parent);
		cFPPopupLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		cFPPopupComponentList.add(cFPPopupLayoutConfig);
		addSearchButtonComponent(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList,
				cFPPopupLayoutConfig.getComponentId());
		addResetButtonComponent(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList,
				cFPPopupLayoutConfig.getComponentId());
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig searchButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cFPPopupNamspacePrefix + "gtnSearch", true, parent, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		cFPPopupComponentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig cFPPopupValidationActionConfig = new GtnUIFrameWorkActionConfig();
		cFPPopupValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		cFPPopupValidationActionConfig.setFieldValues(componentIdList);
		cFPPopupValidationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		GtnUIFrameWorkActionConfig cFPPopupFailureActionConfig = new GtnUIFrameWorkActionConfig();
		cFPPopupFailureActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		cFPPopupFailureActionConfig.addActionParameter("No Search Criteria ");
		cFPPopupFailureActionConfig.addActionParameter("Please enter Search Criteria");

		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		tableLoadActionConfig
				.addActionParameter(cFPPopupNamspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);
		tableLoadActionConfig.setFieldValues(componentIdList);

		GtnUIFrameWorkActionConfig searchNoticationAction = new GtnUIFrameWorkActionConfig();
		searchNoticationAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNoticationAction
				.addActionParameter(cFPPopupNamspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE);

		cFPPopupValidationActionConfig.addActionParameter(Arrays.asList(cFPPopupFailureActionConfig));
		cFPPopupValidationActionConfig.addActionParameter(Arrays.asList(tableLoadActionConfig, searchNoticationAction));

		searchButtonConfig.addGtnUIFrameWorkActionConfig(cFPPopupValidationActionConfig);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig resetButtonConfig = commonConfig.getUIFrameworkComponentConfig(
				cFPPopupNamspacePrefix + "gtnReset", true, parent, GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		cFPPopupComponentList.add(resetButtonConfig);

		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.addActionParameter("Confirmation");
		resetActionConfig.addActionParameter("Are you sure you want to reset the page to default/previous values  ?");
		resetActionConfig.addActionParameter(componentIdList);
		resetActionConfig.addActionParameter(Arrays.asList("", "", "", null, null, "", "", ""));
		resetButtonConfig.addGtnUIFrameWorkActionConfig(resetActionConfig);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkComponentConfig gtnLayout = commonConfig
				.getCssLayoutConfig(cFPPopupNamspacePrefix + "searchFieldLayout", true, parent);
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		cFPPopupComponentList.add(gtnLayout);
		addFieldComponent(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList, gtnLayout.getComponentId());
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix, String parent) {
		String componentId = cFPPopupNamspacePrefix + GtnFrameworkContractDashboardContants.RESULT_TABLE;

		GtnUIFrameworkComponentConfig cFPPopupPanelConfig = commonConfig.getPanelConfig(componentId + "Panel", true,
				parent);
		cFPPopupComponentList.add(cFPPopupPanelConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				cFPPopupPanelConfig.getComponentId(), GtnUIFrameworkComponentType.PAGEDTABLE);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		GtnUIFrameworkPagedTableConfig cFPPopupPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		cFPPopupPagedTableConfig.setEditable(false);
		cFPPopupPagedTableConfig.setFilterBar(true);
		cFPPopupPagedTableConfig.setSelectable(true);
		cFPPopupPagedTableConfig.setPageLength(10);
		cFPPopupPagedTableConfig.setItemPerPage(10);
		cFPPopupPagedTableConfig.setSinkItemPerPageWithPageLength(false);
		cFPPopupPagedTableConfig.setTableColumnDataType(GtnFrameworkContractDashboardContants.getCfpLookupColumnType());
		cFPPopupPagedTableConfig.setTableVisibleHeader(GtnFrameworkContractDashboardContants.getCfpLookupHeader());
		cFPPopupPagedTableConfig.setTableColumnMappingId(GtnFrameworkContractDashboardContants.getCfpLookupColumn());
		cFPPopupPagedTableConfig.setCountUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CFP_LOOKUP_TABLE_DATA);
		cFPPopupPagedTableConfig.setResultSetUrl(GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
				+ GtnWsContractDashboardContants.GET_CFP_LOOKUP_TABLE_DATA);
		componentConfig.setGtnPagedTableConfig(cFPPopupPagedTableConfig);
		cFPPopupComponentList.add(componentConfig);
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		componentConfig.setGtnUIFrameworkValidationConfig(nullValidationConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix, List<String> componentIdList, String parent) {
		GtnUIFrameworkValidationConfig emptyValidationConfig = new GtnUIFrameworkValidationConfig();
		emptyValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		GtnUIFrameworkValidationConfig nullValidationConfig = new GtnUIFrameworkValidationConfig();
		nullValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		addCfpId(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCfpNo(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCfpName(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCfpType(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList, parent, nullValidationConfig);
		addCfpStatus(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList, parent, nullValidationConfig);
		addCompanyId(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCompanyNo(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList, parent, emptyValidationConfig);
		addCompanyName(cFPPopupComponentList, cFPPopupNamspacePrefix, componentIdList, parent, emptyValidationConfig);

	}

	private void addCfpType(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList, String cFPPopupNamspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = cFPPopupNamspacePrefix + "CFPType";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cFPPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Company Family Plan Type");
		cFPPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.CFP_TYPE);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCfpStatus(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList, String cFPPopupNamspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = cFPPopupNamspacePrefix + "CFPStatus";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cFPPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Company Family Plan Status");
		cFPPopupComponentList.add(componentConfig);

		GtnUIFrameworkComboBoxConfig comboBoxConfig = new GtnUIFrameworkComboBoxConfig();
		comboBoxConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		comboBoxConfig.setComboBoxType(GtnWsContractDashboardContants.STATUS);
		componentConfig.setGtnComboboxConfig(comboBoxConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCfpId(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList, String cFPPopupNamspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = cFPPopupNamspacePrefix + "CFPID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cFPPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Company Family Plan ID");
		cFPPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCfpNo(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList, String cFPPopupNamspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = cFPPopupNamspacePrefix + "CFPNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cFPPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Company Family Plan No");
		cFPPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCfpName(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList, String cFPPopupNamspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = cFPPopupNamspacePrefix + "CFPName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cFPPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Company Family Plan Name");
		cFPPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCompanyId(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList, String cFPPopupNamspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = cFPPopupNamspacePrefix + "CompanyID";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cFPPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Company ID");
		cFPPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList, String cFPPopupNamspacePrefix,
			List<String> componentIdList, String parent, GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = cFPPopupNamspacePrefix + "CompanyNo";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cFPPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Company No");
		cFPPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> cFPPopupComponentList,
			String cFPPopupNamspacePrefix, List<String> componentIdList, String parent,
			GtnUIFrameworkValidationConfig validationConfig) {
		String componentId = cFPPopupNamspacePrefix + "CompanyName";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = commonConfig
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parent);
		cFPPopupComponentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig componentConfig = commonConfig.getUIFrameworkComponentConfig(componentId, true,
				gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		componentConfig.setAuthorizationIncluded(true);
		componentConfig.setComponentName("Company Name");
		cFPPopupComponentList.add(componentConfig);
		componentIdList.add(componentConfig.getComponentId());
		componentConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}
}
