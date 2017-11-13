package com.stpl.gtn.gtn2o.ui.company.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.company.constants.GtnFrameworkCompanyStringContants;
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
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnWsNumericConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkCMParentCompanyPopup {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig("Parent Company",
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_VIEW, false);
		addComponentList(view);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addFieldLayout(componentList);
		addButtonLayout(componentList);
		addResultPanel(componentList);
		addActionButtonLayout(componentList);

	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_LAYOUT, false, null,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addFieldComponent(componentList);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig("parentCompanyresultPanel", false,
				null);
		panelConfig.setComponentName("Results");
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("parentCompanyresultlayout",
				true, "parentCompanyresultPanel");
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth("100%");
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_BTN_LAYOUT, false, null);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentWidth("100%");
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addCompanyId(componentList);
		addCompanyNo(componentList);
		addCompanyName(componentList);
		addCompanyStatus(componentList);
		addCompanyType(componentList);
	}

	private void addCompanyId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"parentCompanySearchcompanyIdlayout", true,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_ID, true,
				"parentCompanySearchcompanyIdlayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName("Company ID");
		companyIdConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkValidationConfig valConfigForCompanyId = new GtnUIFrameworkValidationConfig();
		valConfigForCompanyId.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdConfig.setGtnUIFrameworkValidationConfig(valConfigForCompanyId);
		componentList.add(companyIdConfig);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"parentCompanySearchcompanyNolayout", true,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_NO, true,
				"parentCompanySearchcompanyNolayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentName("Company No");
		companyNoConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkValidationConfig valConfigForCompanyNo = new GtnUIFrameworkValidationConfig();
		valConfigForCompanyNo.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(valConfigForCompanyNo);

		componentList.add(companyNoConfig);
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"parentCompanySearchcompanyNamelayout", true,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_NAME, true,
				"parentCompanySearchcompanyNamelayout", GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setComponentName("Company Name");
		companyNameConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkValidationConfig valConfigForCompanyName = new GtnUIFrameworkValidationConfig();
		valConfigForCompanyName.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNameConfig.setGtnUIFrameworkValidationConfig(valConfigForCompanyName);

		componentList.add(companyNameConfig);
	}

	private void addCompanyStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"parentCompanySearchcompanyStatuslayout", true,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyStatus = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_STATUS, true,
				"parentCompanySearchcompanyStatuslayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentName("Company Status");
		companyStatus.setAuthorizationIncluded(true);

		GtnUIFrameworkValidationConfig valConfigForCompanyStatus = new GtnUIFrameworkValidationConfig();
		valConfigForCompanyStatus.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(valConfigForCompanyStatus);

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);
	}

	private void addCompanyType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"parentCompanySearchcompanyTypelayout", true,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_TYPE, true,
				"parentCompanySearchcompanyTypelayout", GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Company Type");
		companyType.setAuthorizationIncluded(true);

		GtnUIFrameworkValidationConfig valConfigForCompanyType = new GtnUIFrameworkValidationConfig();
		valConfigForCompanyType.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(valConfigForCompanyType);

		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("COMPANY_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"parentCompanygtnSearch01", true, GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_BTN_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Search");
		searchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_ID,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_NO,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_NAME,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_STATUS,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_TYPE));

		GtnUIFrameWorkActionConfig alertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.WARNING_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParamsList);
		Object validationType = GtnUIFrameworkValidationType.OR;
		validationActionConfig.setActionParameterList(Arrays.asList(validationType, Arrays.asList(alertActionConfig)));
		actionConfigList.add(validationActionConfig);

		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCompanyStringContants.PARENT_COMPANY_RESULT_TABLE_POP_UP);
		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_ID,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_NO,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_NAME,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_STATUS,
						GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_TYPE));

		actionConfigList.add(loadDataTableActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"parentCompanygtnReset01", true, GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_BTN_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Reset");
		searchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Reset Confirmation");
		params.add("Are you sure you want to reset the values in the Search Criteria group box?");

		params.add(Arrays.asList(GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_ID,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_NO,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_NAME,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_TYPE,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_SEARCH_COMPANY_STATUS));
		params.add(Arrays.asList(GtnFrameworkCompanyStringContants.STRING_EMPTY,
				GtnFrameworkCompanyStringContants.STRING_EMPTY, GtnFrameworkCompanyStringContants.STRING_EMPTY, null,
				null));

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_RESULT_TABLE_POP_UP, true, "parentCompanyresultlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setAuthorizationIncluded(true);

		searchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_350);
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"companyMaster", "SearchQuery");
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setSinkItemPerPageWithPageLength(false);
		searchResults.setTableColumnDataType(
				new Class[] { String.class, String.class, String.class, String.class, String.class });
		searchResults.setTableVisibleHeader(
				new String[] { "Company ID", "Company No", "Company Name", "Company Status", "Company Type" });
		searchResults.setTableColumnMappingId(
				new Object[] { "companyId", "companyNo", "companyName", "companyStatus", "companyType" });
		searchResults.setExtraColumn(new Object[] { "companyMasterSid" });
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCompanyStringContants.PARENT_COMPANY_ACTION_BUTTON_LAYOUT, false, null);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setAddToParent(false);
		gtnLayout.setComponentWidth("100%");
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(GtnWsNumericConstants.TWELVE);
		componentList.add(gtnLayout);
		addAddButtonComponent(componentList);
		addEditButtonComponent(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"parentCompanySelectButton", true,
				GtnFrameworkCompanyStringContants.PARENT_COMPANY_ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Select");
		searchButtonConfig.setAuthorizationIncluded(true);

		searchButtonConfig.addDependentComponent("tradeClass");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCompanyStringContants.PARENT_COMPANY_RESULT_TABLE_POP_UP);
		actionParameter.add("parentCompanyNo");
		actionParameter.add(Arrays.asList("companyNo", "companyName", "5"));
		actionParameter.add(Arrays.asList("parentCompanyNo", "parentCompanyName", "parentCompanySid"));
		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCompanyStringContants.PARENT_COMPANY_VIEW);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"parentCompanyCloseButton", true, GtnFrameworkCompanyStringContants.PARENT_COMPANY_ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Close");
		searchButtonConfig.setAuthorizationIncluded(true);

		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCompanyStringContants.PARENT_COMPANY_VIEW);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}
}