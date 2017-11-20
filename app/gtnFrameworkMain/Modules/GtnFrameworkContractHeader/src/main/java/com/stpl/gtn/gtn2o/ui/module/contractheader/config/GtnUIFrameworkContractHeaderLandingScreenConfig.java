package com.stpl.gtn.gtn2o.ui.module.contractheader.config;

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
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderAddAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.action.GtnUIFrameworkContractHeaderEditAction;
import com.stpl.gtn.gtn2o.ui.module.contractheader.constants.GtnUIFrameworkContractHeaderStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkContractHeaderLandingScreenConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig("Search View", "V001", true);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addCHSearchCriteriaPanel(componentList);
		addCHButtonLayout(componentList);
		addCHResultPanel(componentList);
		addCHActionButtonLayout(componentList);

	}

	private void addCHSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("contractHeaderSearchCriteriaPanel", false,
				null);
		panel.setComponentName("Search Criteria");
		panel.setAuthorizationIncluded(true);

		componentList.add(panel);
		addFieldLayout(componentList);
	}

	private void addCHResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig("ContractHeaderSearchResultPanel",
				false, null);
		panelConfig.setComponentName("Results");
		componentList.add(panelConfig);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getHorizontalLayoutConfig("ContractHeaderSearchResultlayout", true, "ContractHeaderSearchResultPanel");
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getVerticalLayoutConfig(
				"contractHeaderSearchCriteriaVerticallayout", true, "contractHeaderSearchCriteriaPanel");
		gtnLayout.setMargin(true);
		gtnLayout.setSpacing(true);
		gtnLayout.getGtnLayoutConfig().setMargin(true);
		gtnLayout.getGtnLayoutConfig().setSpacing(true);
		componentList.add(gtnLayout);
		addFieldCSSLayout(componentList);
	}

	private void addFieldCSSLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkLayoutComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIALAYOUT, true,
				"contractHeaderSearchCriteriaVerticallayout", GtnUIFrameworkLayoutType.COL3_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(gtnLayout);
		addFieldComponent(componentList);
	}

	private void addCHButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_BUTTONLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addContractHeaderId(componentList);
		addContractHeaderNo(componentList);
		addContractHeaderName(componentList);
		addContractHeaderStatus(componentList);
		addContractHeaderType(componentList);
		addTradeClass(componentList);
		addContractHeaderTPNo(componentList);
	}

	private void addContractHeaderId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderSearchCriteriaContractIdLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_ID, true,
				"contractHeaderSearchCriteriaContractIdLayout", GtnUIFrameworkComponentType.TEXTBOX);
		contractHeaderIdConfig.setAuthorizationIncluded(true);
		contractHeaderIdConfig.setComponentName("Contract ID");

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		contractHeaderIdConfig.setGtnUIFrameworkValidationConfig(valConfig);

		componentList.add(contractHeaderIdConfig);
	}

	private void addContractHeaderNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderSearchCriteriaContractNoLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_NO, true,
				"contractHeaderSearchCriteriaContractNoLayout", GtnUIFrameworkComponentType.TEXTBOX);
		contractHeaderNoConfig.setAuthorizationIncluded(true);
		contractHeaderNoConfig.setComponentName("Contract No");

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		valConfig.setMaxLength(50);
		contractHeaderNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentList.add(contractHeaderNoConfig);
	}

	private void addContractHeaderName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderSearchCriteriaContractNameLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_NAME, true,
				"contractHeaderSearchCriteriaContractNameLayout", GtnUIFrameworkComponentType.TEXTBOX);
		contractHeaderNameConfig.setAuthorizationIncluded(true);
		contractHeaderNameConfig.setComponentName("Contract Name");

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		contractHeaderNameConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentList.add(contractHeaderNameConfig);
	}

	private void addContractHeaderStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderSearchCriteriaContractStatusLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderStatus = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_STATU, true,
				"contractHeaderSearchCriteriaContractStatusLayout", GtnUIFrameworkComponentType.COMBOBOX);
		contractHeaderStatus.setAuthorizationIncluded(true);
		contractHeaderStatus.setComponentName("Contract Status");

		componentList.add(contractHeaderStatus);
		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		contractHeaderStatus.setGtnUIFrameworkValidationConfig(valConfig);

		GtnUIFrameworkComboBoxConfig contractHeaderStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		contractHeaderStatus.setGtnComboboxConfig(contractHeaderStatusConfig);
	}

	private void addContractHeaderType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderSearchCriteriaContractTypeLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderType = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_TYPE, true,
				"contractHeaderSearchCriteriaContractTypeLayout", GtnUIFrameworkComponentType.COMBOBOX);
		contractHeaderType.setAuthorizationIncluded(true);
		contractHeaderType.setComponentName("Contract Type");

		componentList.add(contractHeaderType);
		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		contractHeaderType.setGtnUIFrameworkValidationConfig(valConfig);
		GtnUIFrameworkComboBoxConfig contractHeaderTypeConfig = configProvider.getComboBoxConfig("CONTRACT_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		contractHeaderType.setGtnComboboxConfig(contractHeaderTypeConfig);
	}

	private void addTradeClass(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderSearchCriteriaTradeClassLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_TRADE_CLASS, true,
				"contractHeaderSearchCriteriaTradeClassLayout", GtnUIFrameworkComponentType.TEXTBOX);
		contractHeaderNoConfig.setAuthorizationIncluded(true);
		contractHeaderNoConfig.setComponentName("Trade Class");

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		contractHeaderNoConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentList.add(contractHeaderNoConfig);
	}

	private void addContractHeaderTPNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"contractHeaderSearchCriteriaTPnoLayout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIALAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig contractHeaderIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_TP_NO, true,
				"contractHeaderSearchCriteriaTPnoLayout", GtnUIFrameworkComponentType.POPUPTEXTFIELD);
		contractHeaderIdentifierConfig.setAuthorizationIncluded(true);
		contractHeaderIdentifierConfig.setComponentName("Trading Partner");

		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, false, true);
		textboxConfig.setReadOnly(true);
		contractHeaderIdentifierConfig.setGtnTextBoxConfig(textboxConfig);
		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		contractHeaderIdentifierConfig.setGtnUIFrameworkValidationConfig(valConfig);
		componentList.add(contractHeaderIdentifierConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig popupActionConfig = new GtnUIFrameWorkActionConfig();
		popupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
		popupActionConfig.addActionParameter("landingScreenTpView");
		popupActionConfig.addActionParameter("Trading Partner Look Up");
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		popupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
		actionConfigList.add(popupActionConfig);
		contractHeaderIdentifierConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnSearch01Layout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnSearch01",
				true, "gtnSearch01Layout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("SEARCH");

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(
				Arrays.asList(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_ID,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_NO,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_NAME,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_STATU,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_TYPE,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_TRADE_CLASS,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_TP_NO));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_SEARCH_CRITERIA_VALIDATION);

		alertActionConfig.setActionParameterList(alertParamsList);
		validationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig) }));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig.addActionParameter(GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(
				new String[] { GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_ID,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_NO,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_NAME,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_STATU,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_TYPE,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_TRADE_CLASS,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_TP_NO }));

		actionConfigList.add(loadDataTableActionConfig);
		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE);
		notificationActionConfig.addActionParameter(0);
		actionConfigList.add(notificationActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnReset01Layout", true,
				GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_BUTTONLAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnReset01",
				true, "gtnReset01Layout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("RESET");

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		List<Object> paramsList = new ArrayList<>();
		paramsList.add("Confirmation");
		paramsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTRACT_HEADER_SEARCH_CRITERIA_RESET_VALIDATION);
		paramsList.add(Arrays.asList(
				new String[] { GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_ID,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_NO,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_NAME,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_STATU,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_TYPE,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_TRADE_CLASS,
						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_TP_NO,
						GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE }));

		paramsList.add(Arrays.asList(new Object[] { GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				null }));

		resetActionConfig.setActionParameterList(paramsList);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE, true,
				"ContractHeaderSearchResultlayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		searchResultConfig.setComponentStyle(tableStyleList);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig chLandingScreenResultsTable = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"contractHeader", "contractHeaderSearchQuery");
		chLandingScreenResultsTable.setEditable(false);
		chLandingScreenResultsTable.setItemPerPage(10);
		chLandingScreenResultsTable.setSinkItemPerPageWithPageLength(false);
		chLandingScreenResultsTable.setTableColumnDataType(new Class[] { String.class, String.class, String.class,
				String.class, String.class, String.class, String.class });
		chLandingScreenResultsTable.setTableVisibleHeader(new String[] { "Contract ID", "Contract No", "Contract Name",
				"Contract Status", "Contract Type", "Trade Class", "Trading Partner" });
		chLandingScreenResultsTable.setTableColumnMappingId(new Object[] { "contractId", "contractNo", "contractName",
				"contractStatus", "contractType", "tradeClass", "tradingPartnerName" });
		chLandingScreenResultsTable.setExtraColumn(new Object[] { "contractMasterSId" });
		chLandingScreenResultsTable.setExtraColumnDataType(new Class[] { Integer.class });
		chLandingScreenResultsTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.CONTRACT_HEADER);
		chLandingScreenResultsTable.setDoubleClickEnable(true);
		chLandingScreenResultsTable.setCustomFilterConfigMap(getCustomFilterConfig());
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig chTableClickNavigationActionConfig = new GtnUIFrameWorkActionConfig();
		chTableClickNavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		chTableClickNavigationActionConfig.addActionParameter("V002");
		actionConfigList.add(chTableClickNavigationActionConfig);

		GtnUIFrameWorkActionConfig chTableClickEditActionConfig = new GtnUIFrameWorkActionConfig();
		chTableClickEditActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		chTableClickEditActionConfig.addActionParameter(GtnUIFrameworkContractHeaderEditAction.class.getName());
		chTableClickEditActionConfig.addActionParameter(GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE);
		chTableClickEditActionConfig.addActionParameter(true);
		actionConfigList.add(chTableClickEditActionConfig);
		chLandingScreenResultsTable.setGtnUIFrameWorkActionConfigList(actionConfigList);
		searchResultConfig.setGtnPagedTableConfig(chLandingScreenResultsTable);
	}

	private void addCHActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnUIFrameworkContractHeaderStringContants.ACTION_BUTTON_LAYOUT, false, null);

		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addAddButtonComponent(componentList);
		addEditButtonComponent(componentList);
		addViewButtonComponent(componentList);
		addExcelButtonComponent(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnAddButtonLayout", true,
				GtnUIFrameworkContractHeaderStringContants.ACTION_BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnAddButton",
				true, "gtnAddButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Add");

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig customCommonValidationAction = new GtnUIFrameWorkActionConfig();
		customCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customCommonValidationAction.addActionParameter(GtnUIFrameworkContractHeaderAddAction.class.getName());
		actionConfigList.add(customCommonValidationAction);

		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_SHEET);
		tabAction.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CONTACT_HEADER_TAB);
		actionConfigList.add(tabAction);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnEditButtonLayout", true,
				GtnUIFrameworkContractHeaderStringContants.ACTION_BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig chEditButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnEditButton",
				true, "gtnEditButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		chEditButtonConfig.setAuthorizationIncluded(true);
		chEditButtonConfig.setComponentName("Edit");

		componentList.add(chEditButtonConfig);

		List<GtnUIFrameWorkActionConfig> chEditActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE);
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTACT_HEADER_VALIDATION_MSG_EDIT_HEADER);
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTACT_HEADER_VALIDATION_MSG_EDIT);

		alertActionConfig.setActionParameterList(alertParamsList);
		chEditActionConfigList.add(alertActionConfig);
		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		chEditActionConfigList.add(navigationActionConfig);

		/**
		 * Loading in edit mode
		 */
		GtnUIFrameWorkActionConfig chEditActionConfig = new GtnUIFrameWorkActionConfig();
		chEditActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		chEditActionConfig.addActionParameter(GtnUIFrameworkContractHeaderEditAction.class.getName());
		chEditActionConfig.addActionParameter(GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE);
		chEditActionConfig.addActionParameter(true);
		chEditActionConfigList.add(chEditActionConfig);

		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_SHEET);
		tabAction.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CONTACT_HEADER_TAB);
		chEditActionConfigList.add(tabAction);

		chEditButtonConfig.setGtnUIFrameWorkActionConfigList(chEditActionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnViewButtonLayout", true,
				GtnUIFrameworkContractHeaderStringContants.ACTION_BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig chViewButtonConfig = configProvider.getUIFrameworkComponentConfig("gtnViewButton",
				true, "gtnViewButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		chViewButtonConfig.setAuthorizationIncluded(true);
		chViewButtonConfig.setComponentName("View");

		componentList.add(chViewButtonConfig);

		List<GtnUIFrameWorkActionConfig> chViewActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkCommonStringConstants.ERROR);
		alertParamsList.add(GtnUIFrameworkContractHeaderStringContants.GTN_CONTACT_HEADER_VALIDATION_MSG_VIEW);

		alertActionConfig.setActionParameterList(alertParamsList);
		chViewActionConfigList.add(alertActionConfig);
		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		chViewActionConfigList.add(navigationActionConfig);

		/**
		 * Loading in view mode
		 */
		GtnUIFrameWorkActionConfig chViewActionConfig = new GtnUIFrameWorkActionConfig();
		chViewActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		chViewActionConfig.addActionParameter(GtnUIFrameworkContractHeaderEditAction.class.getName());
		chViewActionConfig.addActionParameter(GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE);
		chViewActionConfig.addActionParameter(false);
		chViewActionConfigList.add(chViewActionConfig);

		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_TAB_SHEET);
		tabAction.addActionParameter(GtnUIFrameworkContractHeaderStringContants.CONTACT_HEADER_TAB);
		chViewActionConfigList.add(tabAction);

		chViewButtonConfig.setGtnUIFrameWorkActionConfigList(chViewActionConfigList);

	}

	/**
	 * 
	 * @param componentList
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnExcelButtonlayout", true,
				GtnUIFrameworkContractHeaderStringContants.ACTION_BUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"cHLandigScreenExcelButton", true, "gtnExcelButtonlayout", GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = configProvider.getExcelBtnconfig(
				"Contract Header", true, GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE, false);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { "contractStatus", "contractType", "tradeClass" };
		String[] listNameArray = { "STATUS", "CONTRACT_TYPE", "CONTRACT_TRADE_CLASS" };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			customFilterConfig.setPropertId(propertyIds[i]);
			customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig chLandigCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			chLandigCustomFilterComponentConfig.setComponentId("customFilterComboBox");
			chLandigCustomFilterComponentConfig.setComponentName("customFilterComboBox");
			chLandigCustomFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			chLandigCustomFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(listNameArray[i]);
			chLandigCustomFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			chLandigCustomFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customFilterConfig.setGtnComponentConfig(chLandigCustomFilterComponentConfig);
			customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);

		}
		return customFilterConfigMap;
	}

}
