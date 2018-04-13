package com.stpl.gtn.gtn2o.ui.module.cfp.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpClassContants;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkCfpLandingScreenConfig {

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig view = componentConfig.getViewConfig("Search View", "V001", true);
		addComponentList(view, componentConfig);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addSearchCriteriaPanel(componentList, componentConfig);
		addButtonLayout(componentList, componentConfig);
		addResultPanel(componentList, componentConfig);
		addActionButtonLayout(componentList, componentConfig);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig ifpsearchCriteriaPanel = componentConfig.getPanelConfig("ifpsearchCriteriaPanel",
				false, null);
		ifpsearchCriteriaPanel.setComponentName("Search Criteria");
		ifpsearchCriteriaPanel.setAuthorizationIncluded(true);
		componentList.add(ifpsearchCriteriaPanel);
		addFieldLayout(componentList, componentConfig);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultPanelConfig = componentConfig.getPanelConfig("resultPanel", false, null);
		resultPanelConfig.setComponentName("Results");
		componentList.add(resultPanelConfig);
		addResultLayout(componentList, componentConfig);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultLayout = componentConfig.getHorizontalLayoutConfig("resultlayout", true,
				"resultPanel");
		resultLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(resultLayout);
		addPagedTableComponent(componentList, componentConfig);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig searchCriteriaLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT, true, "ifpsearchCriteriaPanel");
		searchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(searchCriteriaLayout);
		addFieldComponent(componentList, componentConfig);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig searchBtnLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT, false, null);
		searchBtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		searchBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		searchBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(searchBtnLayout);
		addSearchButtonComponent(componentList, componentConfig);
		addResetButtonComponent(componentList, componentConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addCFPId(componentList, componentConfig);
		addCFPNo(componentList, componentConfig);
		addCFPName(componentList, componentConfig);
		addCFPType(componentList, componentConfig);
		addCFPStatus(componentList, componentConfig);
		addCompanyId(componentList, componentConfig);
		addCompanyNo(componentList, componentConfig);
		addCompanyName(componentList, componentConfig);
	}

	private void addCFPId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpIdLayout = componentConfig.getHorizontalLayoutConfig("cfpIdlayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		cfpIdLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(cfpIdLayout);

		GtnUIFrameworkComponentConfig cfpId = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_ID, true, "cfpIdlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		cfpId.setAuthorizationIncluded(true);
		cfpId.setComponentName("CFP ID");

		GtnUIFrameworkValidationConfig cfpIdValConfig = new GtnUIFrameworkValidationConfig();
		cfpIdValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cfpId.setGtnUIFrameworkValidationConfig(cfpIdValConfig);

		componentList.add(cfpId);
	}

	private void addCFPNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpNoLayout = componentConfig.getHorizontalLayoutConfig("cfpNolayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(cfpNoLayout);

		GtnUIFrameworkComponentConfig cfpNo = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NO, true, "cfpNolayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		cfpNo.setAuthorizationIncluded(true);
		cfpNo.setComponentName("CFP NO");

		GtnUIFrameworkValidationConfig cfpNoValConfig = new GtnUIFrameworkValidationConfig();
		cfpNoValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cfpNo.setGtnUIFrameworkValidationConfig(cfpNoValConfig);
		componentList.add(cfpNo);
	}

	private void addCFPName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig cfpNameLayout = componentConfig.getHorizontalLayoutConfig("cfpNamelayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(cfpNameLayout);

		GtnUIFrameworkComponentConfig cfpNameConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NAME, true, "cfpNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		cfpNameConfig.setAuthorizationIncluded(true);
		cfpNameConfig.setComponentName("CFP Name");

		GtnUIFrameworkValidationConfig cfpNameValConfig = new GtnUIFrameworkValidationConfig();
		cfpNameValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		cfpNameConfig.setGtnUIFrameworkValidationConfig(cfpNameValConfig);
		componentList.add(cfpNameConfig);
	}

	private void addCFPStatus(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpStatusLayout = componentConfig.getHorizontalLayoutConfig("cfpStatuslayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(cfpStatusLayout);

		GtnUIFrameworkComponentConfig cfpStatus = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_STATUS, true, "cfpStatuslayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		cfpStatus.setComponentName("CFP Status");
		cfpStatus.setAuthorizationIncluded(true);
		componentList.add(cfpStatus);

		GtnUIFrameworkComboBoxConfig cfpStatusConfig = componentConfig.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig cfpStatusValConfig = new GtnUIFrameworkValidationConfig();
		cfpStatusValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cfpStatus.setGtnUIFrameworkValidationConfig(cfpStatusValConfig);
		cfpStatus.setGtnComboboxConfig(cfpStatusConfig);
	}

	private void addCFPType(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig cfpTypeLayout = componentConfig.getHorizontalLayoutConfig("cfpTypelayout", true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(cfpTypeLayout);

		GtnUIFrameworkComponentConfig cfpType = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_TYPE, true, "cfpTypelayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		cfpType.setAuthorizationIncluded(true);
		cfpType.setComponentName("CFP Type");
		componentList.add(cfpType);

		GtnUIFrameworkComboBoxConfig cfpTypeConfig = componentConfig.getComboBoxConfig("CFP_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		GtnUIFrameworkValidationConfig cfpTypeValConfig = new GtnUIFrameworkValidationConfig();
		cfpTypeValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		cfpType.setGtnUIFrameworkValidationConfig(cfpTypeValConfig);
		cfpType.setGtnComboboxConfig(cfpTypeConfig);
	}

	private void addCompanyId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig companyIdLayout = componentConfig.getHorizontalLayoutConfig("companyIdlayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(companyIdLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID, true, "companyIdlayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName("Company ID");
		companyIdConfig.setAuthorizationIncluded(true);
		componentList.add(companyIdConfig);

		GtnUIFrameworkValidationConfig companyIdValConfig = new GtnUIFrameworkValidationConfig();
		companyIdValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdConfig.setGtnUIFrameworkValidationConfig(companyIdValConfig);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig companyNoLayout = componentConfig.getHorizontalLayoutConfig("companyNolayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(companyNoLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO, true, "companyNolayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentName("Company NO");
		companyNoConfig.setAuthorizationIncluded(true);

		componentList.add(companyNoConfig);
		GtnUIFrameworkValidationConfig companyNoValConfig = new GtnUIFrameworkValidationConfig();
		companyNoValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(companyNoValConfig);
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig companyNameLayout = componentConfig.getHorizontalLayoutConfig("companyNamelayout",
				true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(companyNameLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME, true, "companyNamelayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setComponentName("Company Name");
		companyNameConfig.setAuthorizationIncluded(true);
		componentList.add(companyNameConfig);

		GtnUIFrameworkValidationConfig companyNameValConfig = new GtnUIFrameworkValidationConfig();
		companyNameValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNameConfig.setGtnUIFrameworkValidationConfig(companyNameValConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig searchBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnSearch01Layout",
				true, GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(searchBtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnSearch01",
				true, "gtnSearch01Layout", GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Search");
		searchButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> searchButtonActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_ID,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NO,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NAME,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_STATUS,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_TYPE, GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParamsList);
		Object validationType = GtnUIFrameworkValidationType.OR;
		validationActionConfig.setActionParameterList(Arrays.asList(validationType, Arrays.asList(alertActionConfig)));
		searchButtonActionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_ID,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NO,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NAME,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_STATUS,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_TYPE, GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME));

		searchButtonActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		searchButtonActionConfigList.add(notificationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(searchButtonActionConfigList);
		componentList.add(searchButtonConfig);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resetLayout = componentConfig.getHorizontalLayoutConfig("gtnReset01Layout", true,
				GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT);
		componentList.add(resetLayout);

		GtnUIFrameworkComponentConfig resetBtn = componentConfig.getUIFrameworkComponentConfig("gtnReset01", true,
				"gtnReset01Layout", GtnUIFrameworkComponentType.BUTTON);
		resetBtn.setAuthorizationIncluded(true);
		resetBtn.setComponentName("Reset");
		componentList.add(resetBtn);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkCfpStringContants.GTN_CFP_CONFIRMATION_MSG_RESET_HEADER);
		params.add(GtnFrameworkCfpStringContants.GTN_CFP_LANDING_SCREEN_RESET_CONFIRMATION_MSG_RESET);

		params.add(Arrays.asList(GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_ID,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NO,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NAME,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_STATUS,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_TYPE, GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME,
				GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE));
		params.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null,
				null, GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, null));

		resetActionConfig.setActionParameterList(params);
		resetActionConfigList.add(resetActionConfig);
		resetBtn.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig searchResultConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE, true, "resultlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setAuthorizationIncluded(true);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = componentConfig.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"companyFamilyPlan", "cfpSearchQuery");
		searchResults.setEditable(false);
		searchResults.setTableColumnDataType(new Class[] { Integer.class, String.class, String.class, String.class,
				String.class, String.class, String.class, Date.class, Date.class, String.class, String.class,
				String.class, Date.class, String.class, String.class, Date.class });

		searchResults.setTableVisibleHeader(new String[] { "System ID", " CFP ID", " CFP No", "CFP Name", "CFP Type",
				"CFP Status", "CFP Category", "Start Date", " End Date", "CFP Designation", "Parent ID", "Parent No",
				"Modified Date", "Modified By", "Created By", "Created Date" });
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_SYSTEM_ID,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_ID, GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NO,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_NAME,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_TYPE,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_STATUS, "companyFamilyPlanCategory",
				"companyFamilyPlanStartDate", "companyFamilyPlanEndDate", "companyFamilyPlanDesignation",
				"parentCompanyFamilyPlanId", "parentCompanyFamilyPlanName", "cfpmodifiedDate", GtnFrameworkCfpStringContants.CFP_MODIFIED_BY,
				GtnFrameworkCfpStringContants.CFP_CREATED_BY, "cfpcreatedDate" });
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_FAMILY_PLAN_SEARCH);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
		searchResults.setSinkItemPerPageWithPageLength(false);
		searchResults.setDoubleClickEnable(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		actionConfigList.add(navigationActionConfig);

		editActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_EDIT_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_SYSTEM_ID);
		editActionConfig.addActionParameter(Boolean.TRUE);
		actionConfigList.add(editActionConfig);

		searchResults.setGtnUIFrameWorkActionConfigList(actionConfigList);
		searchResults.setCustomFilterConfigMap(getCustomFilterConfig(componentConfig));
		searchResultConfig.setGtnPagedTableConfig(searchResults);

	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig actionBtnLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT, false, null);
		actionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(actionBtnLayout);
		addAddButtonComponent(componentList, componentConfig);
		addEditButtonComponent(componentList, componentConfig);
		addViewButtonComponent(componentList, componentConfig);
		addExcelButtonComponent(componentList, componentConfig);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig addBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnAddButtonlayout",
				true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(addBtnLayout);

		GtnUIFrameworkComponentConfig addBtn = componentConfig.getUIFrameworkComponentConfig("gtnAddButton", true,
				"gtnAddButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		addBtn.setAuthorizationIncluded(true);
		addBtn.setComponentName("Add");
		componentList.add(addBtn);

		List<GtnUIFrameWorkActionConfig> addBtnActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig addNavigationActionConfig = new GtnUIFrameWorkActionConfig();
		addNavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		addNavigationActionConfig.addActionParameter("V002");
		addBtnActionConfigList.add(addNavigationActionConfig);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		visibleAction.addActionParameter(Boolean.TRUE);
		visibleAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.CFP_ADD_RESET_BUTTON,
				GtnFrameworkCommonConstants.CFP_ADD_SAVE_BUTTON,
				GtnFrameworkCommonConstants.CFP_COMPANIES_MASS_UPDATE_PANEL_LAYOUT,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT));
		addBtnActionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig visibleAction1 = new GtnUIFrameWorkActionConfig();
		visibleAction1.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		visibleAction1.addActionParameter(Boolean.FALSE);
		visibleAction1.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.CFP_ADD_DELETE_BUTTON,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT_ON_VIEW));
		addBtnActionConfigList.add(visibleAction1);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);

		List<String> enableList = Arrays.asList(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_NO,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_NAME,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_STATUS,
				GtnFrameworkCommonConstants.CFP_INFORMATION_CFP_START_DATE,
				GtnFrameworkCommonConstants.CFP_INFORMATION_CFP_END_DATE,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_TYPE,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_CATEGORY,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_TRADE_CLASS,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_DESIGNATION,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_SALES_INCLUSION, "cfpInformationTabCreatedBy",
				"cfpInformationCreatedDate", "cfpInformationTabModifiedBy", "cfpInformationModifiedDate",
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_FIELD,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_VALUE,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_GTN_SEARCH_01,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_RIGHT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_ALL_RIGHT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_ALL_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANIES_MASS_UPDATE_PANEL_LAYOUT,
				GtnFrameworkCommonConstants.CFP_COMPANIES_RECORD_TYPE_LAYOUT);
		enableAction.addActionParameter(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_ID);
		enableAction.addActionParameter(enableList);
		addBtnActionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_ADD_ACTION);
		addBtnActionConfigList.add(customAction);

		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.CFP_TAB_SHEET);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB);
		addBtnActionConfigList.add(tabAction);

		addBtn.setGtnUIFrameWorkActionConfigList(addBtnActionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig editBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnEditButtonlayout",
				true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(editBtnLayout);

		GtnUIFrameworkComponentConfig editBtnConfig = componentConfig.getUIFrameworkComponentConfig("gtnEditButton",
				true, "gtnEditButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		editBtnConfig.setComponentName("Edit");
		editBtnConfig.setAuthorizationIncluded(true);
		componentList.add(editBtnConfig);

		List<GtnUIFrameWorkActionConfig> editActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_EDIT_HEADER);
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_EDIT);

		alertActionConfig.setActionParameterList(alertParamsList);
		editActionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		editActionConfigList.add(navigationActionConfig);
		/**
		 * Loading in Edit mode
		 */
		GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();
		editActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_EDIT_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_SYSTEM_ID);
		editActionConfig.addActionParameter(Boolean.TRUE);
		editActionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		visibleAction.addActionParameter(Boolean.TRUE);
		visibleAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.CFP_ADD_RESET_BUTTON,
				GtnFrameworkCommonConstants.CFP_ADD_SAVE_BUTTON, GtnFrameworkCommonConstants.CFP_ADD_DELETE_BUTTON,
				GtnFrameworkCommonConstants.CFP_COMPANIES_MASS_UPDATE_PANEL_LAYOUT,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT));
		editActionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig visibleAction1 = new GtnUIFrameWorkActionConfig();
		visibleAction1.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		visibleAction1.addActionParameter(Boolean.FALSE);
		visibleAction1.addActionParameter(
				Arrays.asList(GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT_ON_VIEW));
		editActionConfigList.add(visibleAction1);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);

		List<String> enableList = Arrays.asList(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_NO,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_NAME,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_STATUS,
				GtnFrameworkCommonConstants.CFP_INFORMATION_CFP_START_DATE,
				GtnFrameworkCommonConstants.CFP_INFORMATION_CFP_END_DATE,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_TYPE,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_CATEGORY,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_TRADE_CLASS,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_DESIGNATION,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_SALES_INCLUSION,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_FIELD,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_VALUE,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_GTN_SEARCH_01,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_RIGHT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_ALL_RIGHT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_ALL_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANIES_MASS_UPDATE_PANEL_LAYOUT,
				GtnFrameworkCommonConstants.CFP_COMPANIES_RECORD_TYPE_LAYOUT);
		enableAction.addActionParameter(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_ID);
		enableAction.addActionParameter(enableList);
		editActionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.CFP_TAB_SHEET);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB);
		editActionConfigList.add(tabAction);

		editBtnConfig.setGtnUIFrameWorkActionConfigList(editActionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig viewLayout = componentConfig.getHorizontalLayoutConfig("gtnViewButtonlayout",
				true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(viewLayout);

		GtnUIFrameworkComponentConfig viewBtn = componentConfig.getUIFrameworkComponentConfig("gtnViewButton", true,
				"gtnViewButtonlayout", GtnUIFrameworkComponentType.BUTTON);
		viewBtn.setAuthorizationIncluded(true);
		viewBtn.setComponentName("View");
		componentList.add(viewBtn);

		List<GtnUIFrameWorkActionConfig> viewBtnActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_VIEW_HEADER);
		alertParamsList.add(GtnFrameworkCfpStringContants.GTN_CFP_VALIDATION_MSG_VIEW);

		alertActionConfig.setActionParameterList(alertParamsList);
		viewBtnActionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		viewBtnActionConfigList.add(navigationActionConfig);
		/**
		 * Loading in Edit mode
		 */
		GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();
		editActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_EDIT_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN_SYSTEM_ID);
		editActionConfig.addActionParameter(Boolean.FALSE);
		viewBtnActionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);

		List<String> enableList = Arrays.asList(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_NO,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_NAME,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_STATUS,
				GtnFrameworkCommonConstants.CFP_INFORMATION_CFP_START_DATE,
				GtnFrameworkCommonConstants.CFP_INFORMATION_CFP_END_DATE,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_TYPE,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_CATEGORY,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_TRADE_CLASS,
				GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_DESIGNATION, "cfpInformationTabParentCFPId",
				"cfpInformationTabParentCFPName", GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_SALES_INCLUSION,
				"cfpInformationTabCreatedBy", "cfpInformationCreatedDate", "cfpInformationTabModifiedBy",
				"cfpInformationModifiedDate", GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_FIELD,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_VALUE,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_GTN_SEARCH_01,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_RIGHT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_ALL_RIGHT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_ALL_LEFT_BUTTONS,
				GtnFrameworkCommonConstants.CFP_COMPANIES_MASS_UPDATE_PANEL_LAYOUT,
				GtnFrameworkCommonConstants.CFP_COMPANIES_RECORD_TYPE_LAYOUT);
		disableAction.addActionParameter(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB_CFP_ID);
		disableAction.addActionParameter(enableList);

		viewBtnActionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		visibleAction.addActionParameter(Boolean.FALSE);
		visibleAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.CFP_ADD_RESET_BUTTON,
				GtnFrameworkCommonConstants.CFP_ADD_SAVE_BUTTON, GtnFrameworkCommonConstants.CFP_ADD_DELETE_BUTTON,
				GtnFrameworkCommonConstants.CFP_COMPANIES_MASS_UPDATE_PANEL_LAYOUT,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT));
		viewBtnActionConfigList.add(visibleAction);

		GtnUIFrameWorkActionConfig visibleAction1 = new GtnUIFrameWorkActionConfig();
		visibleAction1.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		visibleAction1.addActionParameter(Boolean.TRUE);
		visibleAction1.addActionParameter(
				Arrays.asList(GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT_ON_VIEW));
		viewBtnActionConfigList.add(visibleAction1);

		GtnUIFrameWorkActionConfig tabAction = new GtnUIFrameWorkActionConfig();
		tabAction.setActionType(GtnUIFrameworkActionType.CHANGE_TAB_ACTION);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.CFP_TAB_SHEET);
		tabAction.addActionParameter(GtnFrameworkCommonConstants.CFP_INFORMATION_TAB);
		viewBtnActionConfigList.add(tabAction);

		viewBtn.setGtnUIFrameWorkActionConfigList(viewBtnActionConfigList);

	}

	/**
	 * 
	 * @param componentList
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig excelBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnExcelButtonlayout",
				true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(excelBtnLayout);

		GtnUIFrameworkComponentConfig excelButton = componentConfig.getUIFrameworkComponentConfig(null, true,
				excelBtnLayout.getComponentId(), GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButton.setAuthorizationIncluded(true);
		componentList.add(excelButton);

		GtnUIFrameworkExcelButtonConfig excelButtonConfig = componentConfig.getExcelBtnconfig("Company Family Plan",
				true, GtnFrameworkCommonConstants.SEARCH_RESULT_TABLE, false);
		excelButtonConfig.setTitleNeeded(true);
		excelButton.setGtnUIFrameworkExcelButtonConfig(excelButtonConfig);

		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(excelButtonConfig);
		excelButton.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig(
			GtnFrameworkComponentConfigProvider componentConfig) {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = GtnFrameworkCfpStringContants.getLandingScreenpropertyIds();
		String[] listNameArray = GtnFrameworkCfpStringContants.getLandingScreenListNameArray();
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig cfpCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			cfpCustomFilterConfig.setPropertId(propertyIds[i]);
			GtnUIFrameworkComboBoxConfig cfpCustomFilter ;
			if(propertyIds[i].equals(GtnFrameworkCfpStringContants.CFP_CREATED_BY) || propertyIds[i].equals(GtnFrameworkCfpStringContants.CFP_MODIFIED_BY) )
			{
			 cfpCustomFilter = componentConfig.getComboBoxConfig(listNameArray[i],
						GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_USER_COMBO_BOX);	
			}
			else{
			 cfpCustomFilter = componentConfig.getComboBoxConfig(listNameArray[i],
					GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		}
			cfpCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			customFilterComponentConfig.setComponentId("customFilterComboBox");
			customFilterComponentConfig.setComponentName("customFilterComboBox");
			cfpCustomFilter.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			customFilterComponentConfig.setGtnComboboxConfig(cfpCustomFilter);
			cfpCustomFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
			customFilterConfigMap.put(cfpCustomFilterConfig.getPropertId(), cfpCustomFilterConfig);

		}
		return customFilterConfigMap;
	}
}
