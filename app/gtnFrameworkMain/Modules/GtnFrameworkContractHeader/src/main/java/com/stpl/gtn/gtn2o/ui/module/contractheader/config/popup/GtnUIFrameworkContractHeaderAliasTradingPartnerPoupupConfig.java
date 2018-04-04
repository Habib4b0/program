package com.stpl.gtn.gtn2o.ui.module.contractheader.config.popup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderPopupTableItemClickAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView(String tabName) {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(tabName + "Parent Company",
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_VIEW, false);
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		view.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP);
		view.addComponentStyle(GtnFrameworkCssConstants.BOOTSTRAP_BB);
		addRootLayout(componentList, tabName);
		return view;
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getVerticalLayoutConfig(
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_ROOTLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addFieldPanel(componentList, tabName);
		addButtonLayout(componentList, tabName);
		addResultPanel(componentList, tabName);
		addActionButtonLayout(componentList, tabName);
	}

	private void addFieldPanel(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(tabName + "TpSearchCriteriaPanel",
				true, tabName + GtnUIFrameworkContractHeaderStringContants.TP_ROOTLAYOUT);
		panelConfig.setComponentName("Search Criteria");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addFieldLayout(componentList, tabName);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCH_CRITERIALAYOUT, true,
				tabName + "TpSearchCriteriaPanel");
		gtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT);
		componentList.add(gtnLayout);
		addFieldComponent(componentList, tabName);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(tabName + "TpresultPanel", true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_ROOTLAYOUT);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		panelConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		panelConfig.setComponentType(GtnUIFrameworkComponentType.PANEL);
		componentList.add(panelConfig);
		addPagedTableComponent(componentList, tabName);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				tabName + GtnUIFrameworkContractHeaderStringContants.TPSEARCH_BUTTONLAYOUT, true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_ROOTLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList, tabName);
		addResetButtonComponent(componentList, tabName);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		addCompanyId(componentList, tabName);
		addCompanyNo(componentList, tabName);
		addCompanyName(componentList, tabName);
		addCompanyStatus(componentList, tabName);
		addCompanyType(componentList, tabName);
	}

	private void addCompanyId(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + "TpSearchcompanyIdlayout", true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_ID, true,
				tabName + "TpSearchcompanyIdlayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName("Company ID");

		GtnUIFrameworkValidationConfig validationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(companyIdConfig);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + "TpSearchcompanyNolayout", true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_NO, true,
				tabName + "TpSearchcompanyNolayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName(GtnUIFrameworkContractHeaderStringContants.COMPANY_NO);
		GtnUIFrameworkValidationConfig validationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		validationConfig.setMaxLength(5);
		companyNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(companyNoConfig);
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + "TpSearchcompanyNamelayout", true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_NAME, true,
				tabName + "TpSearchcompanyNamelayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setAuthorizationIncluded(true);
		companyNameConfig.setComponentName("Company Name");
		companyNameConfig.setAddToParent(true);

		componentList.add(companyNameConfig);
		GtnUIFrameworkValidationConfig validationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNameConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addCompanyStatus(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + "TpSearchcompanyStatuslayout", true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_STATUS, true,
				tabName + "TpSearchcompanyStatuslayout", GtnUIFrameworkComponentType.COMBOBOX);
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

	private void addCompanyType(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + "TpSearchcompanyTypelayout", true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_TYPE, true,
				tabName + "TpSearchcompanyTypelayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName("Company Type");
		companyType.setAddToParent(true);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMPANY_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);

		GtnUIFrameworkValidationConfig validationConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + "TpgtnSearch01layout", true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TPSEARCH_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + "TpgtnSearch01", true, tabName + "TpgtnSearch01layout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Search");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(
				Arrays.asList(new String[] { tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_ID,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_NO,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_NAME,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_STATUS,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_TYPE }));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> cdAliasTpPopupAlertParamsList = new ArrayList<>();
		cdAliasTpPopupAlertParamsList.add("Search Criteria ");
		cdAliasTpPopupAlertParamsList
				.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_SEARCH_CRITERIA_VALIDATION);

		alertActionConfig.setActionParameterList(cdAliasTpPopupAlertParamsList);
		validationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig) }));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig cdAliasTpPopupLoadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		cdAliasTpPopupLoadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParamsList = new ArrayList<>();
		actionParamsList.add(tabName + GtnUIFrameworkContractHeaderStringContants.TPSEARCH_RESULT_TABLE);
		cdAliasTpPopupLoadDataTableActionConfig.setActionParameterList(actionParamsList);
		cdAliasTpPopupLoadDataTableActionConfig.setFieldValues(
				Arrays.asList(new String[] { tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_ID,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_NO,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_NAME,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_STATUS,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_TYPE }));

		actionConfigList.add(cdAliasTpPopupLoadDataTableActionConfig);
		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig
				.addActionParameter(tabName + GtnUIFrameworkContractHeaderStringContants.TPSEARCH_RESULT_TABLE);
		notificationActionConfig.addActionParameter(0);
		actionConfigList.add(notificationActionConfig);
		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.DISABLE_ACTION);
		disableAction.addActionParameter(GtnUIFrameworkContractHeaderStringContants.TP_SELECT_BUTTON);
		actionConfigList.add(disableAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + "TpgtnReset01layout", true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TPSEARCH_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + "TpgtnReset01", true, tabName + "TpgtnReset01layout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Reset");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> paramsList = new ArrayList<>();
		paramsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_CONFIRMATION_MSG_RESET_HEADER);
		paramsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_SEARCH_CRITERIA_RESET_VALIDATION);
		paramsList.add(
				Arrays.asList(new String[] { tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_ID,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_NO,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_NAME,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_STATUS,
						tabName + GtnUIFrameworkContractHeaderStringContants.TP_SEARCHCOMPANY_TYPE,
						tabName + GtnUIFrameworkContractHeaderStringContants.TPSEARCH_RESULT_TABLE }));

		paramsList.add(Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, null }));
		resetActionConfig.setActionParameterList(paramsList);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {

		GtnUIFrameworkComponentConfig cdAliasTpPopupResultConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnUIFrameworkContractHeaderStringContants.TPSEARCH_RESULT_TABLE, true,
				tabName + "TpresultPanel", GtnUIFrameworkComponentType.PAGEDTABLE);
		cdAliasTpPopupResultConfig.setAuthorizationIncluded(true);
		cdAliasTpPopupResultConfig.setComponentName("Results");
		cdAliasTpPopupResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		cdAliasTpPopupResultConfig.setComponentStyle(tableStyleList);

		componentList.add(cdAliasTpPopupResultConfig);

		GtnUIFrameworkPagedTableConfig cdAliasTpPopupResults = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"companyMaster", "SearchQuery");
		cdAliasTpPopupResults.setEditable(false);
		cdAliasTpPopupResults.setPageLength(10);
		cdAliasTpPopupResults.setItemPerPage(10);
		cdAliasTpPopupResults.setSinkItemPerPageWithPageLength(false);
		cdAliasTpPopupResults.setTableColumnDataType(
				new Class[] { String.class, String.class, String.class, String.class, String.class });
		cdAliasTpPopupResults.setTableVisibleHeader(
				new String[] { "Company ID", "Company No", "Company Name", "Company Status", "Company Type" });
		cdAliasTpPopupResults.setExtraColumn(new Object[] { "companyMasterSid" });
		cdAliasTpPopupResults.setTableColumnMappingId(
				new Object[] { "companyId", "companyNo", "companyName", "companyStatus", "companyType" });
		cdAliasTpPopupResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
		cdAliasTpPopupResults.setCustomFilterConfigMap(getCustomFilterConfig());
		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		enableAction.addActionParameter(GtnUIFrameworkContractHeaderPopupTableItemClickAction.class.getName());
		enableAction.addActionParameter(tabName + GtnUIFrameworkContractHeaderStringContants.TP_SELECT_BUTTON);
		cdAliasTpPopupResultConfig.setGtnUIFrameWorkValueChangeActionConfigList(Arrays.asList(enableAction));
		cdAliasTpPopupResultConfig.setGtnPagedTableConfig(cdAliasTpPopupResults);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				tabName + GtnUIFrameworkContractHeaderStringContants.TPACTION_BUTTONLAYOUT, true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_ROOTLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addSelectButtonComponent(componentList, tabName);
		addCloseButtonComponent(componentList, tabName);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + "TpSelectButtonlayout", true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TPACTION_BUTTONLAYOUT);
		gtnLayout.setAddToParent(true);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + GtnUIFrameworkContractHeaderStringContants.TP_SELECT_BUTTON, true,
				tabName + "TpSelectButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Select");
		searchButtonConfig.setEnable(false);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList.add(tabName + GtnUIFrameworkContractHeaderStringContants.TPSEARCH_RESULT_TABLE);
		actionParameterList.add(tabName + "TradingPartner");
		actionParameterList.add(Arrays.asList(new String[] { "companyNo" }));
		actionParameterList.add(Arrays.asList(new String[] { tabName + "TradingPartner" }));
		selectAction.setActionParameterList(actionParameterList);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(tabName + GtnUIFrameworkContractHeaderStringContants.TP_VIEW);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String tabName) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				tabName + "TpCloseButtonlayout", true,
				tabName + GtnUIFrameworkContractHeaderStringContants.TPACTION_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				tabName + "TpCloseButton", true, tabName + "TpCloseButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Close");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(tabName + GtnUIFrameworkContractHeaderStringContants.TP_VIEW);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	public static Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { "companyStatus", "companyType" };
		String[] listNameArray = { "STATUS", "COMPANY_TYPE" };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig cdAliasTpPopupFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			cdAliasTpPopupFilterConfig.setPropertId(propertyIds[i]);
			cdAliasTpPopupFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig cdAliasTpPopupFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			cdAliasTpPopupFilterComponentConfig.setComponentId("customFilterComboBox");
			cdAliasTpPopupFilterComponentConfig.setComponentName("customFilterComboBox");
			cdAliasTpPopupFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			cdAliasTpPopupFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(listNameArray[i]);
			cdAliasTpPopupFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			cdAliasTpPopupFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			cdAliasTpPopupFilterConfig.setGtnComponentConfig(cdAliasTpPopupFilterComponentConfig);
			customFilterConfigMap.put(cdAliasTpPopupFilterConfig.getPropertId(), cdAliasTpPopupFilterConfig);

		}
		return customFilterConfigMap;
	}

}
