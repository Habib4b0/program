package com.stpl.gtn.gtn2o.ui.module.contractheader.config.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkContractHeaderCompanyNamerPoupupConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig("Parent Company",
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_VIEW, false);
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRootLayout(componentList);
		return view;
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getVerticalLayoutConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_ROOTLAYOUT, false, null);
		componentList.add(gtnLayout);
		addFieldPanel(componentList);
		addButtonLayout(componentList);
		addResultPanel(componentList);
		addActionButtonLayout(componentList);
	}

	private void addFieldPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(
				"contractHeaderTabCompanyNameSearchCriteriaPanel", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_ROOTLAYOUT);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addFieldLayout(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkLayoutComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCH_CRITERI, true,
				"contractHeaderTabCompanyNameSearchCriteriaPanel", GtnUIFrameworkLayoutType.COL3_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(gtnLayout);
		addFieldComponent(componentList);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(
				"contractHeaderTabCompanyNameresultPanel", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_ROOTLAYOUT);
		panelConfig.setComponentName("Results");
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_1250);
		componentList.add(panelConfig);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabCompanyNameresultlayout", true, "contractHeaderTabCompanyNameresultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAMESEARCH_BUTTONL, true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_ROOTLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addCdAliasCompanyPopupSearchButtonComponent(componentList);
		addCdAliasCompanyPopupResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addCdAliasCompanyPopupCompanyId(componentList);
		addCdAliasCompanyPopupCompanyNo(componentList);
		addCdAliasCompanyPopupCompanyName(componentList);
		addCdAliasCompanyPopupCompanyStatus(componentList);
		addCdAliasCompanyPopupCompanyType(componentList);
	}

	private void addCdAliasCompanyPopupCompanyId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabCompanyNameSearchcompanyIdlayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCH_CRITERI);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY2, true,
				"contractHeaderTabCompanyNameSearchcompanyIdlayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Company ID");

		GtnUIFrameworkValidationConfig validationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(companyIdConfig);
	}

	private void addCdAliasCompanyPopupCompanyNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabCompanyNameSearchcompanyNolayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCH_CRITERI);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY, true,
				"contractHeaderTabCompanyNameSearchcompanyNolayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName(GtnUIFrameworkContractHeaderStringContants.COMPANY_NO);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMaxLength(5);
		companyNoConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		GtnUIFrameworkValidationConfig validationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);
		componentList.add(companyNoConfig);
	}

	private void addCdAliasCompanyPopupCompanyName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabCompanyNameSearchcompanyNamelayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCH_CRITERI);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY1, true,
				"contractHeaderTabCompanyNameSearchcompanyNamelayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentName("Company Name");

		componentList.add(companyNameConfig);
		GtnUIFrameworkValidationConfig validationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNameConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCdAliasCompanyPopupCompanyStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabCompanyNameSearchcompanyStatuslayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCH_CRITERI);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY3, true,
				"contractHeaderTabCompanyNameSearchcompanyStatuslayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setAuthorizationIncluded(true);
		companyStatus.setComponentName("Company Status");

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig validationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCdAliasCompanyPopupCompanyType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY4, true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCH_CRITERI);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY, true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY4,
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Company Type");

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMPANY_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);

		GtnUIFrameworkValidationConfig validationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCdAliasCompanyPopupSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabCompanyNamegtnSearch01layout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAMESEARCH_BUTTONL);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig cdAliasCompanyPopupSearchButtonConfig = configProvider
				.getUIFrameworkComponentConfig("contractHeaderTabCompanyNamegtnSearch01", true,
						"contractHeaderTabCompanyNamegtnSearch01layout", GtnUIFrameworkComponentType.BUTTON);
		cdAliasCompanyPopupSearchButtonConfig.setAuthorizationIncluded(true);
		cdAliasCompanyPopupSearchButtonConfig.setComponentName("Search");

		componentList.add(cdAliasCompanyPopupSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig cdAliasCompanyPopupSearchValidationActionConfig = new GtnUIFrameWorkActionConfig();
		cdAliasCompanyPopupSearchValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		cdAliasCompanyPopupSearchValidationActionConfig.setFieldValues(Arrays.asList(new String[] {
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY2,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY1,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY3,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY }));

		GtnUIFrameWorkActionConfig cdAliasCompanyPopupSearchAlertActionConfig = new GtnUIFrameWorkActionConfig();
		cdAliasCompanyPopupSearchAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_SEARCH_CRITERIA_VALIDATION);

		cdAliasCompanyPopupSearchAlertActionConfig.setActionParameterList(alertParamsList);
		cdAliasCompanyPopupSearchValidationActionConfig.setActionParameterList(Arrays.asList(new Object[] {
				GtnUIFrameworkValidationType.OR, Arrays.asList(cdAliasCompanyPopupSearchAlertActionConfig) }));
		actionConfigList.add(cdAliasCompanyPopupSearchValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParamsList = new ArrayList<>();
		actionParamsList
				.add(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAMESEARCH_RESULT_T);
		loadDataTableActionConfig.setActionParameterList(actionParamsList);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] {
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY2,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY1,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY3,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY }));

		actionConfigList.add(loadDataTableActionConfig);
		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAMESEARCH_RESULT_T);
		notificationActionConfig.addActionParameter(0);
		actionConfigList.add(notificationActionConfig);
		cdAliasCompanyPopupSearchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addCdAliasCompanyPopupResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabCompanyNamegtnReset01layout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAMESEARCH_BUTTONL);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig cdAliasCompanyPopupResetButtonConfig = configProvider
				.getUIFrameworkComponentConfig("contractHeaderTabCompanyNamegtnReset01", true,
						"contractHeaderTabCompanyNamegtnReset01layout", GtnUIFrameworkComponentType.BUTTON);
		cdAliasCompanyPopupResetButtonConfig.setAuthorizationIncluded(true);
		cdAliasCompanyPopupResetButtonConfig.setComponentName("Reset");

		componentList.add(cdAliasCompanyPopupResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> cdAliasCompanyPopupResetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> paramsList = new ArrayList<>();
		paramsList.add("Reset Confirmation");
		paramsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_SEARCH_CRITERIA_RESET_VALIDATION);
		paramsList.add(Arrays.asList(new String[] {
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY2,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY1,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY3,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_SEARCHCOMPANY,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAMESEARCH_RESULT_T }));

		paramsList.add(Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, null }));
		resetActionConfig.setActionParameterList(paramsList);
		cdAliasCompanyPopupResetActionConfigList.add(resetActionConfig);
		cdAliasCompanyPopupResetButtonConfig
				.setGtnUIFrameWorkActionConfigList(cdAliasCompanyPopupResetActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig cdAliasCompanyPopupResultsTableConfig = configProvider
				.getUIFrameworkComponentConfig(
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAMESEARCH_RESULT_T,
						true, "contractHeaderTabCompanyNameresultlayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		cdAliasCompanyPopupResultsTableConfig.setAuthorizationIncluded(true);
		cdAliasCompanyPopupResultsTableConfig.setComponentName("Results");
		cdAliasCompanyPopupResultsTableConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cdAliasCompanyPopupResultsTableConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		cdAliasCompanyPopupResultsTableConfig.setComponentStyle(tableStyleList);

		componentList.add(cdAliasCompanyPopupResultsTableConfig);

		GtnUIFrameworkPagedTableConfig cdAliasCompanyPopupResultsTable = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"companyMaster", "SearchQuery");
		cdAliasCompanyPopupResultsTable.setEditable(false);
		cdAliasCompanyPopupResultsTable.setTableColumnDataType(
				new Class[] { String.class, String.class, String.class, String.class, String.class });
		cdAliasCompanyPopupResultsTable.setTableVisibleHeader(
				new String[] { "Company ID", "Company No", "Company Name", "Company Status", "Company Type" });
		cdAliasCompanyPopupResultsTable.setExtraColumn(new Object[] { "companyMasterSid" });
		cdAliasCompanyPopupResultsTable.setTableColumnMappingId(
				new Object[] { "companyId", "companyNo", "companyName", "companyStatus", "companyType" });
		cdAliasCompanyPopupResultsTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
		cdAliasCompanyPopupResultsTableConfig.setGtnPagedTableConfig(cdAliasCompanyPopupResultsTable);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAMEACTION_BUTTONL, true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_ROOTLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabCompanyNameSelectButtonlayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAMEACTION_BUTTONL);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabCompanyNameSelectButton", true, "contractHeaderTabCompanyNameSelectButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Select");

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList
				.add(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAMESEARCH_RESULT_T);
		actionParameterList.add("contractHeaderTabCompanyName");
		actionParameterList.add(Arrays.asList(new String[] { "companyNo" }));
		actionParameterList.add(Arrays
				.asList(new String[] { GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME }));
		selectAction.setActionParameterList(actionParameterList);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction
				.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_VIEW);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(searchButtonConfig);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderTabCompanyNameCloseButtonlayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAMEACTION_BUTTONL);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"contractHeaderTabCompanyNameCloseButton", true, "contractHeaderTabCompanyNameCloseButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Close");

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction
				.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_COMPANY_NAME_VIEW);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}
}
