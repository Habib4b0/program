package com.stpl.gtn.gtn2o.config.commonpopup;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.config.commonpopup.commonaction.GtnUIFrameworkItemClickEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnUIFrameworkParentCompanyPopupConfig {

	public GtnUIFrameworkViewConfig getSearchView(String sufix) {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig parentCompanyView = componentConfig.getViewConfig("Parent Company",
				sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_VIEW, false);

		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		parentCompanyView.setGtnComponentList(componentList);
		addRootLayout(componentList, sufix, componentConfig);

		return parentCompanyView;
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String sufix,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig mainRootlayout = componentConfig
				.getVerticalLayoutConfig(GtnFrameworkCommonConstants.PARENT_COMPANY_ROOTLAYOUT, false, null);
		mainRootlayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(mainRootlayout);
		addFieldPanel(componentList, sufix, componentConfig);
		addButtonLayout(componentList, sufix, componentConfig);
		addResultPanel(componentList, sufix, componentConfig);
		addActionButtonLayout(componentList, sufix, componentConfig);
	}

	private void addFieldPanel(List<GtnUIFrameworkComponentConfig> componentList, String sufix,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig mainPanelConfig = componentConfig.getPanelConfig(
				"parentCompanySearchCriteriaPanel", true, GtnFrameworkCommonConstants.PARENT_COMPANY_ROOTLAYOUT);
		mainPanelConfig.setComponentName("Search Criteria");
		componentList.add(mainPanelConfig);
		addFieldLayout(componentList, componentConfig, sufix);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig, String sufix) {
		GtnUIFrameworkComponentConfig mainSearchLayout = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIALAYOUT, true,
				"parentCompanySearchCriteriaPanel", GtnUIFrameworkLayoutType.COL3_LAYOUT);
		componentList.add(mainSearchLayout);
		addFieldComponent(componentList, sufix, componentConfig);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String sufix,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultPanelConfig = componentConfig.getPanelConfig("parentCompanyresultPanel",
				true, GtnFrameworkCommonConstants.PARENT_COMPANY_ROOTLAYOUT);
		resultPanelConfig.setComponentName("Results");
		resultPanelConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_1250);
		componentList.add(resultPanelConfig);
		addResultLayout(componentList, sufix, componentConfig);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String sufix,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultTableLayout = componentConfig
				.getHorizontalLayoutConfig("parentCompanyresultlayout", true, "parentCompanyresultPanel");
		resultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(resultTableLayout);
		addPagedTableComponent(componentList, componentConfig, sufix);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String sufix,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig buttonLayout = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.PARENT_COMPANYSEARCH_BUTTONLAYOUT, true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_ROOTLAYOUT);
		buttonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		buttonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(buttonLayout);
		addSearchButtonComponent(componentList, componentConfig, sufix);
		addResetButtonComponent(componentList, componentConfig, sufix);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String sufix,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addCompanyId(componentList, componentConfig, sufix);
		addCompanyNo(componentList, componentConfig, sufix);
		addCompanyName(componentList, componentConfig, sufix);
		addCompanyStatus(componentList, componentConfig, sufix);
		addCompanyType(componentList, componentConfig, sufix);
	}

	private void addCompanyId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig, String sufix) {
		GtnUIFrameworkComponentConfig companyIdLayout = componentConfig.getHorizontalLayoutConfig(
				sufix + "parentCompanySearchcompanyIdlayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIALAYOUT);
		componentList.add(companyIdLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = componentConfig.getUIFrameworkComponentConfig(
				sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_ID, true,
				companyIdLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setComponentName("Company ID");

		GtnUIFrameworkValidationConfig companyIdValConfig = new GtnUIFrameworkValidationConfig();
		companyIdValConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyIdConfig.setGtnUIFrameworkValidationConfig(companyIdValConfig);

		componentList.add(companyIdConfig);
	}

	private void addCompanyNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig, String sufix) {
		GtnUIFrameworkComponentConfig companyNoLayout = componentConfig.getHorizontalLayoutConfig(
				sufix + "parentCompanySearchcompanyNolayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIALAYOUT);
		componentList.add(companyNoLayout);

		GtnUIFrameworkComponentConfig companyNoConfig = componentConfig.getUIFrameworkComponentConfig(
				sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_NO, true,
				companyNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setComponentName("Company NO");

		GtnUIFrameworkValidationConfig companyNoValidationConfig = new GtnUIFrameworkValidationConfig();
		companyNoValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNoConfig.setGtnUIFrameworkValidationConfig(companyNoValidationConfig);
		componentList.add(companyNoConfig);
	}

	private void addCompanyName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig, String sufix) {

		GtnUIFrameworkComponentConfig companyNameLayout = componentConfig.getHorizontalLayoutConfig(
				sufix + "parentCompanySearchcompanyNamelayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIALAYOUT);
		componentList.add(companyNameLayout);

		GtnUIFrameworkComponentConfig companyNameConfig = componentConfig.getUIFrameworkComponentConfig(
				sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_NAME, true,
				companyNameLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		companyNameConfig.setComponentName("Company Name");
		componentList.add(companyNameConfig);

		GtnUIFrameworkValidationConfig companyNameValidationConfig = new GtnUIFrameworkValidationConfig();
		companyNameValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		companyNameConfig.setGtnUIFrameworkValidationConfig(companyNameValidationConfig);
	}

	private void addCompanyStatus(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig, String sufix) {
		GtnUIFrameworkComponentConfig companyStatusLayout = componentConfig.getHorizontalLayoutConfig(
				sufix + "parentCompanySearchcompanyStatuslayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIALAYOUT);
		componentList.add(companyStatusLayout);

		GtnUIFrameworkComponentConfig companyStatus = componentConfig.getUIFrameworkComponentConfig(
				sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_STATUS, true,
				companyStatusLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		companyStatus.setComponentName("Company Status");

		componentList.add(companyStatus);

		GtnUIFrameworkComboBoxConfig companyStatusConfig = componentConfig.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyStatus.setGtnComboboxConfig(companyStatusConfig);

		GtnUIFrameworkValidationConfig companyStatusValidationConfig = new GtnUIFrameworkValidationConfig();
		companyStatusValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyStatus.setGtnUIFrameworkValidationConfig(companyStatusValidationConfig);
	}

	private void addCompanyType(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig, String sufix) {
		GtnUIFrameworkComponentConfig companyTypeLayout = componentConfig.getHorizontalLayoutConfig(
				sufix + "parentCompanySearchcompanyTypelayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCH_CRITERIALAYOUT);
		componentList.add(companyTypeLayout);

		GtnUIFrameworkComponentConfig companyType = componentConfig.getUIFrameworkComponentConfig(
				sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_TYPE, true,
				companyTypeLayout.getComponentId(), GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setComponentName("Company Type");
		componentList.add(companyType);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = componentConfig.getComboBoxConfig("COMPANY_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		companyType.setGtnComboboxConfig(companyTypeConfig);

		GtnUIFrameworkValidationConfig companytypeValidationConfig = new GtnUIFrameworkValidationConfig();
		companytypeValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		companyType.setGtnUIFrameworkValidationConfig(companytypeValidationConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig, String sufix) {
		GtnUIFrameworkComponentConfig searchBtnLayout = componentConfig.getHorizontalLayoutConfig(
				sufix + "parentCompanygtnSearch01layout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANYSEARCH_BUTTONLAYOUT);
		componentList.add(searchBtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"parentCompanygtnSearch01", true, searchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Search");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig
				.setFieldValues(Arrays.asList(sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_ID,
						sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_NO,
						sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_NAME,
						sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_STATUS,
						sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_TYPE));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParamsList);
		validationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig)));
		searchActionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParamsList = new ArrayList<>();
		actionParamsList.add(sufix + GtnFrameworkCommonConstants.PARENT_COMPANYSEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setActionParameterList(actionParamsList);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_ID,
						sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_NO,
						sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_NAME,
						sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_STATUS,
						sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_TYPE));

		searchActionConfigList.add(loadDataTableActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig, String sufix) {
		GtnUIFrameworkComponentConfig resetBtnLayout = componentConfig.getHorizontalLayoutConfig(
				sufix + "parentCompanygtnReset01layout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANYSEARCH_BUTTONLAYOUT);
		componentList.add(resetBtnLayout);

		GtnUIFrameworkComponentConfig resetButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"parentCompanygtnReset01", true, resetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("Reset");
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> paramsList = new ArrayList<>();
		paramsList.add("Reset Confirmation");
		paramsList.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> resetMap = new HashMap<>();
		resetMap.put(sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_ID,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_NO,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_NAME,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		resetMap.put(sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_STATUS, null);
		resetMap.put(sufix + GtnFrameworkCommonConstants.PARENT_COMPANY_SEARCHCOMPANY_TYPE, null);

		paramsList.add(resetMap);

		resetActionConfig.setActionParameterList(paramsList);
		resetActionConfigList.add(resetActionConfig);
		resetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig, String sufix) {

		GtnUIFrameworkComponentConfig searchResultConfig = componentConfig.getUIFrameworkComponentConfig(
				sufix + GtnFrameworkCommonConstants.PARENT_COMPANYSEARCH_RESULT_TABLE, true,
				"parentCompanyresultlayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		searchResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		GtnUIFrameWorkActionConfig itemClickActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		itemClickActionConfig.addActionParameter(GtnUIFrameworkItemClickEnableDisableAction.class.getName());
		itemClickActionConfig.addActionParameter(sufix + GtnFrameworkCommonConstants.PARENT_COMPANYSEARCH_RESULT_TABLE);
		itemClickActionConfig.addActionParameter(sufix + "parentCompanySelectButton");
		searchResultConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(itemClickActionConfig));
		componentList.add(searchResultConfig);

		GtnUIFrameworkPagedTableConfig companySearchResults = componentConfig.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"companyMaster", "SearchQuery");
		companySearchResults.setEditable(false);
		companySearchResults.setItemClickListener(true);
		companySearchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING });
		companySearchResults.setTableVisibleHeader(
				new String[] { "Company ID", "Company No", "Company Name", "Company Status", "Company Type" });
		companySearchResults.setExtraColumn(new Object[] { "companyMasterSid" });
		companySearchResults.setTableColumnMappingId(
				new Object[] { "companyId", "companyNo", "companyName", "companyStatus", "companyType" });
		companySearchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_MASTER);
		searchResultConfig.setGtnPagedTableConfig(companySearchResults);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String sufix,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig actionBtnLayout = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.PARENT_COMPANYACTION_BUTTONLAYOUT, true,
				GtnFrameworkCommonConstants.PARENT_COMPANY_ROOTLAYOUT);
		actionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(actionBtnLayout);
		addSelectButtonComponent(componentList, sufix, componentConfig);
		addCloseButtonComponent(componentList, sufix, componentConfig);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String sufix,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig selectLayout = componentConfig.getHorizontalLayoutConfig(
				sufix + "parentCompanySelectButtonlayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANYACTION_BUTTONLAYOUT);
		componentList.add(selectLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				sufix + "parentCompanySelectButton", true, selectLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Select");
		searchButtonConfig.setEnable(false);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList.add(sufix + GtnFrameworkCommonConstants.PARENT_COMPANYSEARCH_RESULT_TABLE);
		actionParameterList.add(sufix + "EntityCodeNo");
		actionParameterList.add(Arrays.asList("companyNo", "companyName"));
		actionParameterList.add(Arrays.asList(sufix + "EntityCodeNo", sufix + "EntityCodeName"));
		selectAction.setActionParameterList(actionParameterList);
		selectActionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.PARENT_COMPANY_VIEW);
		selectActionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String sufix,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig closeLayout = componentConfig.getHorizontalLayoutConfig(
				sufix + "parentCompanyCloseButtonlayout", true,
				GtnFrameworkCommonConstants.PARENT_COMPANYACTION_BUTTONLAYOUT);
		componentList.add(closeLayout);

		GtnUIFrameworkComponentConfig closeButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				sufix + "parentCompanyCloseButton", true, closeLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		closeButtonConfig.setComponentName("Close");
		componentList.add(closeButtonConfig);

		List<GtnUIFrameWorkActionConfig> closeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.PARENT_COMPANY_VIEW);
		closeActionConfigList.add(closeAction);
		closeButtonConfig.setGtnUIFrameWorkActionConfigList(closeActionConfigList);

	}
}
