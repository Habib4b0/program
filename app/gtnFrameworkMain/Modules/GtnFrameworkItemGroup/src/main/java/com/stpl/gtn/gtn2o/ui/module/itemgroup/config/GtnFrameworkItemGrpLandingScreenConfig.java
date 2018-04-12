package com.stpl.gtn.gtn2o.ui.module.itemgroup.config;

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
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.action.validation.GtnFrameworkItemGrpDeleteValidationAction;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.constants.GtnFrameworkItemGrpClassContants;
import com.stpl.gtn.gtn2o.ui.module.itemgroup.constants.GtnFrameworkItemGrpStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkItemGrpLandingScreenConfig {

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig itemGroupSearchView = componentConfig.getViewConfig("Search View", "V001", true);
		addComponentList(itemGroupSearchView, componentConfig);
		return itemGroupSearchView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> itemGroupComponentList = new ArrayList<>();
		view.setGtnComponentList(itemGroupComponentList);
		addSearchCriteriaPanel(itemGroupComponentList, componentConfig);
		addResultPanel(itemGroupComponentList, componentConfig);
		addActionButtonLayout(itemGroupComponentList, componentConfig);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig itemGroupSearchPanel = componentConfig
				.getPanelConfig(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_CRITERIA_PANEL, false, null);
		itemGroupSearchPanel.setComponentName("Search Criteria");
		itemGroupSearchPanel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(itemGroupSearchPanel);
		addFieldLayout(componentList, componentConfig);
		addButtonLayout(componentList, componentConfig);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultPanelConfig = componentConfig.getPanelConfig("itemGrpResultPanel", false,
				null);
		resultPanelConfig.setAuthorizationIncluded(true);
		resultPanelConfig.setComponentName("Results");
		componentList.add(resultPanelConfig);
		addResultLayout(componentList, componentConfig);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultTableLayout = componentConfig
				.getHorizontalLayoutConfig("itemGrpResultlayout", true, "itemGrpResultPanel");

		resultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(resultTableLayout);
		addPagedTableComponent(componentList, componentConfig);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig itemGroupSearchLayout = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_GRP_SEARCH_CRITERIALAYOUT, true,
				GtnFrameworkCommonConstants.ITEM_GRPSEARCH_CRITERIA_PANEL, GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		itemGroupSearchLayout.setComponentStyle(styleList);
		componentList.add(itemGroupSearchLayout);
		addFieldComponent(componentList, componentConfig);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig iGroupSearchCriteriaButtonLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ITEM_GRP_SEARCH_CRITERIALAYOUT, false, null);
		iGroupSearchCriteriaButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		iGroupSearchCriteriaButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		iGroupSearchCriteriaButtonLayout.setSpacing(true);
		iGroupSearchCriteriaButtonLayout.setMargin(true);
		componentList.add(iGroupSearchCriteriaButtonLayout);

		addSearchButtonComponent(componentList, componentConfig);
		addAuditSearchButtonComponent(componentList, componentConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		addItemGrpName(componentList, componentConfig);
		addItemGrpNo(componentList, componentConfig);
		addItemGrpDesc(componentList, componentConfig);
		addItemGrpCompany(componentList, componentConfig);
	}

	private void addItemGrpName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemGrpNamelayout = componentConfig.getHorizontalLayoutConfig("itemGrpNamelayout",
				true, GtnFrameworkCommonConstants.ITEM_GRP_SEARCH_CRITERIALAYOUT);
		componentList.add(itemGrpNamelayout);

		GtnUIFrameworkComponentConfig itemGroupNameConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_GROUP_NAME, true, itemGrpNamelayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemGroupNameConfig.setAuthorizationIncluded(true);
		itemGroupNameConfig.setComponentName("Item Group Name");

		GtnUIFrameworkValidationConfig itemGroupNameValidationConfig = new GtnUIFrameworkValidationConfig();
		itemGroupNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		itemGroupNameConfig.setGtnUIFrameworkValidationConfig(itemGroupNameValidationConfig);

		componentList.add(itemGroupNameConfig);
	}

	private void addItemGrpNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemGrpNolayout = componentConfig.getHorizontalLayoutConfig("itemGrpNolayout",
				true, GtnFrameworkCommonConstants.ITEM_GRP_SEARCH_CRITERIALAYOUT);
		componentList.add(itemGrpNolayout);

		GtnUIFrameworkComponentConfig itemGrpNoConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_GROUP_NO, true, itemGrpNolayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemGrpNoConfig.setAuthorizationIncluded(true);
		itemGrpNoConfig.setComponentName("Item Group No");

		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setMaxLength(100);
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		itemGrpNoConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(itemGrpNoConfig);
	}

	private void addItemGrpDesc(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig itemGrpDesclayout = componentConfig.getHorizontalLayoutConfig("itemGrpDesclayout",
				true, GtnFrameworkCommonConstants.ITEM_GRP_SEARCH_CRITERIALAYOUT);
		componentList.add(itemGrpDesclayout);

		GtnUIFrameworkComponentConfig itemGrpDescConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_GROUP_DESC, true, itemGrpDesclayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		itemGrpDescConfig.setAuthorizationIncluded(true);
		itemGrpDescConfig.setComponentName("Item Group Description");

		GtnUIFrameworkValidationConfig itemGrpDescValidationConfig = new GtnUIFrameworkValidationConfig();
		itemGrpDescValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		itemGrpDescConfig.setGtnUIFrameworkValidationConfig(itemGrpDescValidationConfig);

		componentList.add(itemGrpDescConfig);
	}

	private void addItemGrpCompany(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemGrpCompanylayout = componentConfig.getHorizontalLayoutConfig(
				"iGrpCompanylayout", true, GtnFrameworkCommonConstants.ITEM_GRP_SEARCH_CRITERIALAYOUT);
		componentList.add(itemGrpCompanylayout);

		GtnUIFrameworkComponentConfig itemGrpCompanyConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.I_GRP_INFO_COMPANY, true, itemGrpCompanylayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		itemGrpCompanyConfig.setAuthorizationIncluded(true);
		itemGrpCompanyConfig.setComponentName("Company");
		componentList.add(itemGrpCompanyConfig);

		GtnUIFrameworkComboBoxConfig itemGrpCompanyComboConfig = componentConfig.getComboBoxConfig(
				GtnFrameworkForecastConstantCommon.COMPANY_MASTER_GLCOMP,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		itemGrpCompanyConfig.setGtnComboboxConfig(itemGrpCompanyComboConfig);
		GtnUIFrameworkValidationConfig validationConfig = new GtnUIFrameworkValidationConfig();
		validationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		itemGrpCompanyConfig.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig searchBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"itemGrpGtnSearch01layout", true, GtnFrameworkCommonConstants.ITEM_GRP_SEARCH_CRITERIALAYOUT);
		componentList.add(searchBtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemGrpGtnSearch01", true, searchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("SEARCH");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig searchValidationActionConfig = new GtnUIFrameWorkActionConfig();
		searchValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		searchValidationActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.ITEM_GROUP_NAME, GtnFrameworkCommonConstants.ITEM_GROUP_NO,
						GtnFrameworkCommonConstants.ITEM_GROUP_DESC, GtnFrameworkCommonConstants.I_GRP_INFO_COMPANY));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();

		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_SEARCH_ERROR);
		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_NO_SEARCH_FOUND);

		alertActionConfig.setActionParameterList(alertParamsList);
		searchValidationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig) }));
		actionConfigList.add(searchValidationActionConfig);

		GtnUIFrameWorkActionConfig resultTableActionConfig = new GtnUIFrameWorkActionConfig();
		resultTableActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		resultTableActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_TABLE_LOAD_ACTION);
		resultTableActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.ITEM_GROUP_NAME, GtnFrameworkCommonConstants.ITEM_GROUP_DESC,
						GtnFrameworkCommonConstants.ITEM_GROUP_NO, GtnFrameworkCommonConstants.I_GRP_INFO_COMPANY));
		actionConfigList.add(resultTableActionConfig);

		GtnUIFrameWorkActionConfig searchNotificationActionConfig = new GtnUIFrameWorkActionConfig();
		searchNotificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNotificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE);
		actionConfigList.add(searchNotificationActionConfig);
		// To Enable buttons (Edit, Delete, Copy) while Audit search is clicked
		GtnUIFrameWorkActionConfig searchDisableAction = new GtnUIFrameWorkActionConfig();
		searchDisableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		

		searchDisableAction.setActionParameterList(Arrays.asList(GtnFrameworkItemGrpStringContants.getADD_SEARCH_DISABLE_FIELD()));
		actionConfigList.add(searchDisableAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addAuditSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig auditSearchBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"gtnAudictSearch01layout", true, GtnFrameworkCommonConstants.ITEM_GRP_SEARCH_CRITERIALAYOUT);
		componentList.add(auditSearchBtnLayout);

		GtnUIFrameworkComponentConfig auditSearchButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"gtnAudictSearch01", true, auditSearchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		auditSearchButtonConfig.setComponentName("AUDIT SEARCH");
		auditSearchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(auditSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig auditSearchValidationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		auditSearchValidationActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.ITEM_GROUP_NAME, GtnFrameworkCommonConstants.ITEM_GROUP_NO,
						GtnFrameworkCommonConstants.ITEM_GROUP_DESC, GtnFrameworkCommonConstants.I_GRP_INFO_COMPANY));

		GtnUIFrameWorkActionConfig alertActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_SEARCH_ERROR);
		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_NO_SEARCH_FOUND);

		alertActionConfig.setActionParameterList(alertParamsList);
		auditSearchValidationActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig) }));
		actionConfigList.add(auditSearchValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadDataTableActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_TABLE_LOAD_ACTION);
		loadDataTableActionConfig.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.ITEM_GROUP_NAME, GtnFrameworkCommonConstants.ITEM_GROUP_DESC,
						GtnFrameworkCommonConstants.ITEM_GROUP_NO, GtnFrameworkCommonConstants.I_GRP_INFO_COMPANY));
		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig auditSearchNotificationActionConfig = new GtnUIFrameWorkActionConfig();
		auditSearchNotificationActionConfig
				.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		auditSearchNotificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE);
		actionConfigList.add(auditSearchNotificationActionConfig);
		// To Disable buttons (Edit, Delete, Copy) while Audit search is clicked
		GtnUIFrameWorkActionConfig auditSearchDisableAction = new GtnUIFrameWorkActionConfig();
		auditSearchDisableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		auditSearchDisableAction.setActionParameterList(Arrays.asList(GtnFrameworkItemGrpStringContants.getAUDIT_SEARCH_DISABLE_FIELD()));
		actionConfigList.add(auditSearchDisableAction);
		auditSearchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemGroupSearchResultConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE, true, "itemGrpResultlayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		itemGroupSearchResultConfig.setAuthorizationIncluded(true);
		itemGroupSearchResultConfig.setComponentName("Results");
		itemGroupSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		componentList.add(itemGroupSearchResultConfig);

		GtnUIFrameworkPagedTableConfig searchResultsTableConfig = new GtnUIFrameworkPagedTableConfig();
		searchResultsTableConfig.setEditable(false);
		searchResultsTableConfig.setFilterBar(true);
		searchResultsTableConfig.setSelectable(true);
		searchResultsTableConfig.setSinkItemPerPageWithPageLength(false);
		searchResultsTableConfig.setPageLength(10);
		searchResultsTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING });

		searchResultsTableConfig.setTableVisibleHeader(new String[] { "Item Group Name", "Item Group Description",
				"Item Group No", "Company", "Version No", "Creation Date", "Modified Date", "Created By" });
		searchResultsTableConfig.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.ITEM_GROUP_NAME,
				GtnFrameworkCommonConstants.ITEM_GROUP_DESC, GtnFrameworkCommonConstants.ITEM_GROUP_NO, "companyName",
				"versionNo", "createdDate", "modifiedDate", "createdBy" });
		searchResultsTableConfig.setExtraColumn(new Object[] { GtnFrameworkCommonConstants.ITEM_GROUP_SID });
		searchResultsTableConfig
				.setExtraColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_INTEGER });
		searchResultsTableConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResultsTableConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResultsTableConfig.setModuleName("itemGroupsMaster");
		searchResultsTableConfig.setQueryName("itemGrpSearchQuery");
		searchResultsTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.ITEM_GROUPS);
		searchResultsTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());

		searchResultsTableConfig.setDoubleClickEnable(true);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		actionConfigList.add(navigationActionConfig);

		/**
		 * Loading in Edit mode
		 */
		GtnUIFrameWorkActionConfig doubleClickActionConfig = new GtnUIFrameWorkActionConfig();
		doubleClickActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> actionParameters = new ArrayList<>();
		actionParameters.add(GtnFrameworkItemGrpClassContants.ITEM_GRP_EDIT_ACTION);
		actionParameters.add(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE);
		actionParameters.add(GtnFrameworkCommonConstants.ITEM_GROUP_SID);
		actionParameters.add(Boolean.TRUE);
		doubleClickActionConfig.setActionParameterList(actionParameters);
		actionConfigList.add(doubleClickActionConfig);
		searchResultsTableConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
		itemGroupSearchResultConfig.setGtnPagedTableConfig(searchResultsTableConfig);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemGrpactionBtnLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ITEM_GRP_ACTION_BUTTONLAYOUT, false, null);
		itemGrpactionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		itemGrpactionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		itemGrpactionBtnLayout.setSpacing(true);
		itemGrpactionBtnLayout.setMargin(true);

		componentList.add(itemGrpactionBtnLayout);
		addResetButtonComponent(componentList, componentConfig);
		addAddButtonComponent(componentList, componentConfig);
		addEditButtonComponent(componentList, componentConfig);
		addViewButtonComponent(componentList, componentConfig);
		addCopyButtonComponent(componentList, componentConfig);
		addDeleteButtonComponent(componentList, componentConfig);
		addExcelButtonComponent(componentList, componentConfig);
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig itemGrpExcelBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"itemGrpgtnExcelButtonlayout", true, GtnFrameworkCommonConstants.ITEM_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpExcelBtnLayout);

		GtnUIFrameworkComponentConfig excelButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemGrpresultTableExcelBtn", true, itemGrpExcelBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonInput = new GtnUIFrameworkExcelButtonConfig();
		gtnUIFrameworkExcelButtonInput.setIsTreeTable(false);
		gtnUIFrameworkExcelButtonInput.setExportFileName("Item Group");
		gtnUIFrameworkExcelButtonInput.setTitleNeeded(true);
		gtnUIFrameworkExcelButtonInput.setExportFromTable(true);
		gtnUIFrameworkExcelButtonInput.setExportTableId(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE);

		GtnUIFrameWorkActionConfig resultTableExcelAction = new GtnUIFrameWorkActionConfig();
		resultTableExcelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		resultTableExcelAction.addActionParameter(gtnUIFrameworkExcelButtonInput);

		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonInput);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(resultTableExcelAction));
		componentList.add(excelButtonConfig);

	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemGrpAddBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"itemGrpgtnAddButtonlayout", true, GtnFrameworkCommonConstants.ITEM_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpAddBtnLayout);

		GtnUIFrameworkComponentConfig addButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemGrpgtnAddButton", true, itemGrpAddBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setAuthorizationIncluded(true);
		addButtonConfig.setComponentName("ADD");
		componentList.add(addButtonConfig);

		List<GtnUIFrameWorkActionConfig> addActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.addActionParameter("V002");

		addActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();
		editActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_ADD_ACTION);

		addActionConfigList.add(editActionConfig);
		addButtonConfig.setGtnUIFrameWorkActionConfigList(addActionConfigList);
	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemGrpEditBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"itemGrpgtnEditButtonlayout", true, GtnFrameworkCommonConstants.ITEM_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpEditBtnLayout);

		GtnUIFrameworkComponentConfig editButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_GRPGTN_EDIT_BUTTON, true, itemGrpEditBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		editButtonConfig.setAuthorizationIncluded(true);
		editButtonConfig.setComponentName("EDIT");
		componentList.add(editButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_MSG_EDIT_ERROR_HEADER);
		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_ALERT_MSG);

		alertActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		actionConfigList.add(navigationActionConfig);

		/**
		 * Loading in Edit mode
		 */
		GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();
		editActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_EDIT_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE);
		editActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_GROUP_SID);
		editActionConfig.addActionParameter(Boolean.TRUE);
		actionConfigList.add(editActionConfig);
		editButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemGrpViewBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"itemGrpGtnViewButtonlayout", true, GtnFrameworkCommonConstants.ITEM_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpViewBtnLayout);

		GtnUIFrameworkComponentConfig viewButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"itemGrpGtnViewButton", true, itemGrpViewBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		viewButtonConfig.setAuthorizationIncluded(true);
		viewButtonConfig.setComponentName("VIEW");
		componentList.add(viewButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_MSG_ERROR_HEADER);
		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_ALERT_MSG);

		alertActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig viewNavigationActionConfig = new GtnUIFrameWorkActionConfig();
		viewNavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		viewNavigationActionConfig.addActionParameter("V002");
		actionConfigList.add(viewNavigationActionConfig);

		GtnUIFrameWorkActionConfig viewActionConfig = new GtnUIFrameWorkActionConfig();
		viewActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		viewActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_EDIT_ACTION);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE);
		viewActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_GROUP_SID);
		viewActionConfig.addActionParameter(Boolean.FALSE);
		actionConfigList.add(viewActionConfig);
		viewButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addCopyButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemGrpCopyBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"itemGrpGtnCopyButtonlayout", true, GtnFrameworkCommonConstants.ITEM_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpCopyBtnLayout);

		GtnUIFrameworkComponentConfig copyButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_GRP_GTN_COPY_BUTTON, true, itemGrpCopyBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		copyButtonConfig.setAuthorizationIncluded(true);
		copyButtonConfig.setComponentName("COPY");
		componentList.add(copyButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE);
		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_MSG_ERROR_HEADER);
		alertParamsList.add(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_VALIDATION_MSG_COPY);

		alertActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig copyActionConfig = new GtnUIFrameWorkActionConfig();
		copyActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		copyActionConfig.addActionParameter(GtnFrameworkItemGrpClassContants.ITEM_GRP_COPY_ACTION);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE);
		copyActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_GROUP_SID);
		copyActionConfig.addActionParameter(Boolean.TRUE);
		actionConfigList.add(copyActionConfig);
		copyButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemGrpDeleteBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"itemGrpGtnDeleteButtonlayout", true, GtnFrameworkCommonConstants.ITEM_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpDeleteBtnLayout);

		GtnUIFrameworkComponentConfig deleteButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_GRP_GTN_DELETE_BUTTON, true, itemGrpDeleteBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		deleteButtonConfig.setAuthorizationIncluded(true);
		deleteButtonConfig.setComponentName("DELETE");
		componentList.add(deleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.ITEM_GROUP_NAME, GtnFrameworkCommonConstants.ITEM_GROUP_NO,
						GtnFrameworkCommonConstants.ITEM_GROUP_DESC, GtnFrameworkCommonConstants.I_GRP_INFO_COMPANY));
		deleteActionConfig.addActionParameter(GtnFrameworkItemGrpDeleteValidationAction.class.getName());
		actionConfigList.add(deleteActionConfig);
		deleteButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig itemGrpResetBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"gtnReset01layout", true, GtnFrameworkCommonConstants.ITEM_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpResetBtnLayout);

		GtnUIFrameworkComponentConfig resetButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnReset01",
				true, itemGrpResetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		resetActionConfig
				.addActionParameter(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_CONFIRMATION_MSG_RESET_HEADER);
		resetActionConfig.addActionParameter(GtnFrameworkItemGrpStringContants.GTN_ITEM_GRP_CONFIRMATION_RESET_MSG);

		String[] resetIdArray = { GtnFrameworkCommonConstants.ITEM_GROUP_NAME,
				GtnFrameworkCommonConstants.ITEM_GROUP_NO, GtnFrameworkCommonConstants.ITEM_GROUP_DESC,
				GtnFrameworkCommonConstants.I_GRP_INFO_COMPANY,
				GtnFrameworkCommonConstants.ITEM_GRPSEARCH_RESULT_TABLE };
		List<String> resetIdList = Arrays.asList(resetIdArray);

		List<Object> resetValueList = Arrays.asList(GtnFrameworkItemGrpStringContants.getRESET_VALUE_ARRAY());

		resetActionConfig.addActionParameter(resetIdList);
		resetActionConfig.addActionParameter(resetValueList);

		actionConfigList.add(resetActionConfig);
		resetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> iGrpFilterConfigMap = new HashMap<>();
		String[] propertyIds = { "createdBy" };
		String[] listNameArray = { "USERS" };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig iGrpSrchTableCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			iGrpSrchTableCustomFilterConfig.setPropertId(propertyIds[i]);
			iGrpSrchTableCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig iGrpSrchTableFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			iGrpSrchTableFilterComponentConfig.setComponentId("customFilterComboBox");
			iGrpSrchTableFilterComponentConfig.setComponentName("customFilterComboBox");
			iGrpSrchTableFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			iGrpSrchTableFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(listNameArray[i]);
			iGrpSrchTableFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			iGrpSrchTableFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			iGrpSrchTableCustomFilterConfig.setGtnComponentConfig(iGrpSrchTableFilterComponentConfig);
			iGrpFilterConfigMap.put(iGrpSrchTableCustomFilterConfig.getPropertId(), iGrpSrchTableCustomFilterConfig);

		}
		return iGrpFilterConfigMap;
	}
}
