package com.stpl.gtn.gtn2o.ui.module.priceschedule.config;

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
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkModeType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnFrameworkPSdefaultValuesetAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameWorkPSLoadAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.validation.GtnUIFrameworkPSValidationActionIsRecordSelectedAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.constants.GtnFrameworkPSConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkPSSearchConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig searchView = configProvider.getViewConfig("priceScheduleSearchSearchView",
				"priceScheduleSearchSearchView", true);
		GtnUIFrameWorkActionConfig reloadHelperTableAction = new GtnUIFrameWorkActionConfig();
		reloadHelperTableAction.setActionType(GtnUIFrameworkActionType.RELOAD_HELPER_TABLE_ACTION);
		searchView.addViewAction(reloadHelperTableAction);
		addComponentList(searchView);
		return searchView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addSearchCriteriaPanel(componentList);
		addButtonLayout(componentList);
		addResultPanel(componentList);
		addActionButtonLayout(componentList);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchCriteriaPanel = configProvider.getPanelConfig("searchCriteriaPanel", false,
				null);
		searchCriteriaPanel.setComponentName("Search Criteria");
		searchCriteriaPanel.setAuthorizationIncluded(true);
		componentList.add(searchCriteriaPanel);
		addFieldLayout(componentList);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resultPanel = configProvider.getPanelConfig("resultPanel", false, null);
		resultPanel.setComponentName("Results");
		resultPanel.setAuthorizationIncluded(true);
		componentList.add(resultPanel);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psResultLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.RESULT_LAYOUT, true, "resultPanel");
		psResultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(psResultLayout);
		addPagedTableComponent(componentList);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psFieldLayoutLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT, true, "searchCriteriaPanel");
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		psFieldLayoutLayout.setComponentStyle(styleList);
		componentList.add(psFieldLayoutLayout);
		addFieldComponent(componentList);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT, false, null);
		psButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(psButtonLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addPriceScheduleId(componentList);
		addPriceScheduleNo(componentList);
		addPriceScheduleName(componentList);
		addPriceScheduleStatus(componentList);
		addPriceScheduleType(componentList);
		addItemId(componentList);
		addItemNo(componentList);
		addItemName(componentList);
	}

	private void addPriceScheduleId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleIdLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(priceScheduleIdLayout);

		GtnUIFrameworkComponentConfig priceScheduleId = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		priceScheduleId.setAuthorizationIncluded(true);
		priceScheduleId.setComponentName("Price Schedule ID");
		componentList.add(priceScheduleId);

		GtnUIFrameworkValidationConfig priceScheduleIdValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		priceScheduleIdValidationConfig.setConditionList(conditions);
		priceScheduleId.setGtnUIFrameworkValidationConfig(priceScheduleIdValidationConfig);
	}

	private void addPriceScheduleNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleNoLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(priceScheduleNoLayout);

		GtnUIFrameworkComponentConfig priceScheduleNo = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		priceScheduleNo.setAuthorizationIncluded(true);
		priceScheduleNo.setComponentName("Price Schedule No");

		GtnUIFrameworkValidationConfig priceScheduleNoValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		priceScheduleNoValidationConfig.setConditionList(conditions);
		priceScheduleNo.setGtnUIFrameworkValidationConfig(priceScheduleNoValidationConfig);

		componentList.add(priceScheduleNo);
	}

	private void addPriceScheduleName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig priceScheduleNameLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(priceScheduleNameLayout);

		GtnUIFrameworkComponentConfig priceScheduleName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		priceScheduleName.setAuthorizationIncluded(true);
		priceScheduleName.setComponentName("Price Schedule Name");

		GtnUIFrameworkValidationConfig priceScheduleNameValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		priceScheduleNameValidationConfig.setConditionList(conditions);
		priceScheduleName.setGtnUIFrameworkValidationConfig(priceScheduleNameValidationConfig);
		componentList.add(priceScheduleName);
	}

	private void addPriceScheduleStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleStatusLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(priceScheduleStatusLayout);

		GtnUIFrameworkComponentConfig priceScheduleStatus = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		priceScheduleStatus.setAuthorizationIncluded(true);
		priceScheduleStatus.setComponentName("Price Schedule Status");
		componentList.add(priceScheduleStatus);

		GtnUIFrameworkComboBoxConfig priceScheduleStatusConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		priceScheduleStatus.setGtnComboboxConfig(priceScheduleStatusConfig);

		GtnUIFrameworkValidationConfig priceScheduleStatusValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		priceScheduleStatusValidationConfig.setConditionList(conditions);
		priceScheduleStatus.setGtnUIFrameworkValidationConfig(priceScheduleStatusValidationConfig);
	}

	private void addPriceScheduleType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig priceScheduleTypeLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE_LAYOUT, true,
				GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(priceScheduleTypeLayout);

		GtnUIFrameworkComponentConfig priceScheduleType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		priceScheduleType.setAuthorizationIncluded(true);
		priceScheduleType.setComponentName("Price Schedule Type");
		componentList.add(priceScheduleType);

		GtnUIFrameworkComboBoxConfig priceScheduleTypeConfig = configProvider.getComboBoxConfig("PS_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		priceScheduleType.setGtnComboboxConfig(priceScheduleTypeConfig);

		GtnUIFrameworkValidationConfig priceScheduleTypeValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		priceScheduleTypeValidationConfig.setConditionList(conditions);
		priceScheduleType.setGtnUIFrameworkValidationConfig(priceScheduleTypeValidationConfig);
	}

	private void addItemId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig itemIdLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_ID_LAYOUT, true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(itemIdLayout);

		GtnUIFrameworkComponentConfig itemId = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_ID, true, GtnFrameworkCommonConstants.ITEM_ID_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		itemId.setAuthorizationIncluded(true);
		itemId.setComponentName("Item ID");

		GtnUIFrameworkTextBoxConfig itemIdConfig = configProvider.getTextBoxConfig(true, false, true);
		itemId.setGtnTextBoxConfig(itemIdConfig);

		GtnUIFrameworkValidationConfig itemIdValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		itemIdValidationConfig.setConditionList(conditions);
		itemId.setGtnUIFrameworkValidationConfig(itemIdValidationConfig);

		componentList.add(itemId);

	}

	private void addItemNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig itemNoLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_NO_LAYOUT, true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(itemNoLayout);

		GtnUIFrameworkComponentConfig itemNo = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_NO, true, GtnFrameworkCommonConstants.ITEM_NO_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		itemNo.setAuthorizationIncluded(true);
		itemNo.setComponentName("Item No");

		GtnUIFrameworkTextBoxConfig itemNoConfig = configProvider.getTextBoxConfig(true, false, true);
		itemNo.setGtnTextBoxConfig(itemNoConfig);

		GtnUIFrameworkValidationConfig itemNoValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		itemNoValidationConfig.setConditionList(conditions);
		itemNo.setGtnUIFrameworkValidationConfig(itemNoValidationConfig);

		componentList.add(itemNo);

	}

	private void addItemName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig itemNameLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.ITEM_NAME_LAYOUT, true, GtnFrameworkCommonConstants.SEARCH_CRITERIA_LAYOUT);
		componentList.add(itemNameLayout);

		GtnUIFrameworkComponentConfig itemName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.ITEM_NAME, true, GtnFrameworkCommonConstants.ITEM_NAME_LAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		itemName.setAuthorizationIncluded(true);
		itemName.setComponentName("Item Name");

		GtnUIFrameworkTextBoxConfig itemNameConfig = configProvider.getTextBoxConfig(true, true, true);
		itemName.setGtnTextBoxConfig(itemNameConfig);

		GtnUIFrameworkValidationConfig itemNameValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		itemNameValidationConfig.setConditionList(conditions);
		itemName.setGtnUIFrameworkValidationConfig(itemNameValidationConfig);

		componentList.add(itemName);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleSearch01", true, GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Search");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> searchButtonActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig validationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.ITEM_ID,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE, GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID, GtnFrameworkCommonConstants.ITEM_NO,
				GtnFrameworkCommonConstants.ITEM_NAME));

		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> onFailure = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add("Search Criteria ");
		alertParams.add("Please enter Search Criteria");
		alertActionConfig.setActionParameterList(alertParams);
		onFailure.add(alertActionConfig);
		validationActionConfig.addActionParameter(onFailure);
		searchButtonActionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.PS_SEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO, GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID,
				GtnFrameworkCommonConstants.ITEM_ID, GtnFrameworkCommonConstants.ITEM_NO,
				GtnFrameworkCommonConstants.ITEM_NAME));

		searchButtonActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.PS_SEARCH_RESULT_TABLE);
		searchButtonActionConfigList.add(notificationActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(searchButtonActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig psResetButton = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleReset01", true, GtnFrameworkCommonConstants.SEARCH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		psResetButton.setAuthorizationIncluded(true);
		psResetButton.setComponentName("Reset");
		componentList.add(psResetButton);

		List<GtnUIFrameWorkActionConfig> resetButtonActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add("Confirmation");
		params.add("Are you sure you want to reset the page to default/previous values?");
		Map<String, Object> psResetMap = new HashMap<>();
		psResetMap.put(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID, "");
		psResetMap.put(GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO, "");
		psResetMap.put(GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME, "");
		psResetMap.put(GtnFrameworkCommonConstants.ITEM_ID, "");
		psResetMap.put(GtnFrameworkCommonConstants.ITEM_NO, "");
		psResetMap.put(GtnFrameworkCommonConstants.ITEM_NAME, "");
		psResetMap.put(GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE, null);
		psResetMap.put(GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS, null);
		psResetMap.put(GtnFrameworkCommonConstants.PS_SEARCH_RESULT_TABLE, null);
		params.add(psResetMap);

		resetActionConfig.setActionParameterList(params);
		resetButtonActionConfigList.add(resetActionConfig);
		psResetButton.setGtnUIFrameWorkActionConfigList(resetButtonActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig psSearchResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PS_SEARCH_RESULT_TABLE, true, GtnFrameworkCommonConstants.RESULT_LAYOUT,
				GtnUIFrameworkComponentType.PAGEDTABLE);
		psSearchResultConfig.setAuthorizationIncluded(true);
		psSearchResultConfig.setComponentName("Results");
		psSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig psSearchResultValidationConfig = new GtnUIFrameworkValidationConfig();
		psSearchResultValidationConfig.setMinSize(1);
		psSearchResultConfig.setGtnUIFrameworkValidationConfig(psSearchResultValidationConfig);
		componentList.add(psSearchResultConfig);

		GtnUIFrameworkPagedTableConfig psSearchResultTable = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"priceSchedule", "priceScheduleSearch");
		psSearchResultTable.setEditable(false);
		psSearchResultTable.setSinkItemPerPageWithPageLength(false);
		psSearchResultTable.setItemPerPage(10);
		psSearchResultTable.setPageLength(10);
		psSearchResultTable.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class,
				String.class, Date.class, String.class, Date.class, String.class });
		psSearchResultTable.setTableColumnDataType(
				new Class<?>[] { String.class, String.class, String.class, String.class, String.class, String.class,
						String.class, Date.class, Date.class, String.class, String.class, String.class, String.class });
		psSearchResultTable.setTableVisibleHeader(new String[] { "System ID", "Price Schedule ID", "Price Schedule No",
				"Price Schedule Name", "Price Schedule Type", "Price Schedule Status", "Price Schedule Category",
				"Start Date", "End Date", "Price Schedule Designation", "Parent ID", "Parent Name", "Trade Class" });
		psSearchResultTable.setTableColumnMappingId(new Object[] { "systemId",
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID, GtnFrameworkCommonConstants.PRICE_SCHEDULE_NO,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME, GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS, "priceScheduleCategory", "startDate", "endDate",
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_DESIGNATION, "parentID", "parentName", "tradeClass" });
		psSearchResultTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> psLandingScreenCustomFilterConfigMap = new HashMap<>();
		GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		customFilterConfig.setPropertId(GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE);
		customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("ruleTypecustomFilterComboBox");
		customFilterComponentConfig.setComponentName("customFilterComboBox");
		GtnUIFrameworkComboBoxConfig ruleTypeConfig = configProvider.getComboBoxConfig("PS_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		customFilterComponentConfig.setGtnComboboxConfig(ruleTypeConfig);

		customFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig ruleCategoryCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		ruleCategoryCustomFilterConfig.setPropertId(GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS);
		ruleCategoryCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig ruleCategoryCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		ruleCategoryCustomFilterComponentConfig.setComponentId("ruleCategorycustomFilterComboBox");
		ruleCategoryCustomFilterComponentConfig.setComponentName("customFilterComboBox");

		GtnUIFrameworkComboBoxConfig ruleCategoryConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ruleCategoryCustomFilterComponentConfig.setGtnComboboxConfig(ruleCategoryConfig);
		ruleCategoryCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		ruleCategoryCustomFilterConfig.setGtnComponentConfig(ruleCategoryCustomFilterComponentConfig);

		psLandingScreenCustomFilterConfigMap.put(ruleCategoryCustomFilterConfig.getPropertId(),
				ruleCategoryCustomFilterConfig);

		psLandingScreenCustomFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		psSearchResultTable.setCustomFilterConfigMap(psLandingScreenCustomFilterConfigMap);
		psSearchResultTable.setDoubleClickEnable(true);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ADD_VIEW);
		actionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnUIFrameWorkPSLoadAction.class.getName());
		parameters.add(GtnFrameworkCommonConstants.PS_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(Boolean.TRUE);
		parameters.add(0);

		editActionConfig.setActionParameterList(parameters);
		actionConfigList.add(editActionConfig);
		psSearchResultTable.setGtnUIFrameWorkActionConfigList(actionConfigList);
		psSearchResultConfig.setGtnPagedTableConfig(psSearchResultTable);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psActionButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT, false, null);
		psActionButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(psActionButtonLayout);
		addAddButtonComponent(componentList);
		addEditButtonComponent(componentList);
		addViewButtonComponent(componentList);
		addExcelButtonComponent(componentList);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psAddButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleAddButton", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		psAddButtonConfig.setAuthorizationIncluded(true);
		psAddButtonConfig.setComponentName("ADD");
		componentList.add(psAddButtonConfig);

		List<GtnUIFrameWorkActionConfig> psAddButtonActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		GtnUIFrameWorkActionConfig setModeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.addActionParameter(GtnUIFrameworkModeType.ADD);
		psAddButtonActionConfigList.add(setModeActionConfig);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ADD_VIEW);
		psAddButtonActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);

		enableAction.setActionParameterList(Arrays.asList(GtnFrameworkPSConstants.getADD_ENABLE_FIELD()));
		psAddButtonActionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig setDefaultValueAction = new GtnUIFrameWorkActionConfig();
		setDefaultValueAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		setDefaultValueAction.addActionParameter(GtnFrameworkPSdefaultValuesetAction.class.getName());
		Object[] disableFieldArray = new Object[] { GtnFrameworkCommonConstants.CREATED_BY,
				GtnFrameworkCommonConstants.CREATED_DATE };
		Object[] disableValueArray = new Object[] { "", new Date() };

		setDefaultValueAction.addActionParameter(Arrays.asList(disableFieldArray));
		setDefaultValueAction.addActionParameter(Arrays.asList(disableValueArray));
		psAddButtonActionConfigList.add(setDefaultValueAction);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		

		disableAction.setActionParameterList(Arrays.asList(GtnFrameworkPSConstants.getADD_DISABLE_FIELD()));
		psAddButtonActionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] visibleFields = new String[] { GtnFrameworkCommonConstants.PRICE_SCHEDULE_ADD_VIEW_ADD_RESET_BUTTON,
				GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON };

		GtnUIFrameWorkActionConfig disVisibleAction = new GtnUIFrameWorkActionConfig();
		disVisibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		String[] disVisibleFields = new String[] {
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ADD_VIEW_A_ADD_DELETE_BUTTON };

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		changeCaptionActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON));
		changeCaptionActionConfig.addActionParameter(Arrays.asList("SAVE"));
		psAddButtonActionConfigList.add(changeCaptionActionConfig);

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(Boolean.TRUE);
		visibleParameters.add(Arrays.asList(visibleFields));

		List<Object> disVisibleParameters = new ArrayList<>();
		disVisibleParameters.add(Boolean.FALSE);
		disVisibleParameters.add(Arrays.asList(disVisibleFields));

		visibleAction.setActionParameterList(visibleParameters);
		psAddButtonActionConfigList.add(visibleAction);

		disVisibleAction.setActionParameterList(disVisibleParameters);
		psAddButtonActionConfigList.add(disVisibleAction);

		psAddButtonConfig.setGtnUIFrameWorkActionConfigList(psAddButtonActionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psEditButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"priceScheduleEditButton", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		psEditButtonConfig.setAuthorizationIncluded(true);
		psEditButtonConfig.setComponentName("EDIT");
		componentList.add(psEditButtonConfig);

		List<GtnUIFrameWorkActionConfig> psEditButtonActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.addActionParameter(GtnUIFrameworkModeType.EDIT);

		psEditButtonActionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck
				.addActionParameter(GtnUIFrameworkPSValidationActionIsRecordSelectedAction.class.getName());
		psEditButtonActionConfigList.add(customActionforLastOperatorCheck);

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ADD_VIEW);
		psEditButtonActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnUIFrameWorkPSLoadAction.class.getName());
		parameters.add(GtnFrameworkCommonConstants.PS_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(Boolean.TRUE);
		parameters.add(0);

		editActionConfig.setActionParameterList(parameters);
		psEditButtonActionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig changeCaptionActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CHANGE_CAPTION);
		changeCaptionActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.CDR_ADD_SAVE_BUTTON));
		changeCaptionActionConfig.addActionParameter(Arrays.asList("UPDATE"));
		psEditButtonActionConfigList.add(changeCaptionActionConfig);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		

		enableAction.setActionParameterList(Arrays.asList(GtnFrameworkPSConstants.getADD_EDIT_ENABLE_FIELD()));
		psEditButtonActionConfigList.add(enableAction);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		

		disableAction.setActionParameterList(Arrays.asList(GtnFrameworkPSConstants.getADD_EDIT_DISABLE_FIELD()));
		psEditButtonActionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(Boolean.TRUE);
		visibleParameters.add(Arrays.asList(GtnFrameworkPSConstants.getADD_EDIT_VISIBLE_FIELD()));

		visibleAction.setActionParameterList(visibleParameters);
		psEditButtonActionConfigList.add(visibleAction);

		psEditButtonConfig.setGtnUIFrameWorkActionConfigList(psEditButtonActionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psViewButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"cDRAddViewButton", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		psViewButtonConfig.setAuthorizationIncluded(true);
		psViewButtonConfig.setComponentName("VIEW");
		componentList.add(psViewButtonConfig);

		List<GtnUIFrameWorkActionConfig> psViewButtonActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig setModeActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.MODE_CHANGE);
		setModeActionConfig.addActionParameter(GtnUIFrameworkModeType.VIEW);

		psViewButtonActionConfigList.add(setModeActionConfig);

		GtnUIFrameWorkActionConfig customActionforLastOperatorCheck = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customActionforLastOperatorCheck
				.addActionParameter(GtnUIFrameworkPSValidationActionIsRecordSelectedAction.class.getName());
		psViewButtonActionConfigList.add(customActionforLastOperatorCheck);

		GtnUIFrameWorkActionConfig navigationActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ADD_VIEW);

		psViewButtonActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig editActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EDIT_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnUIFrameWorkPSLoadAction.class.getName());
		parameters.add(GtnFrameworkCommonConstants.PS_SEARCH_RESULT_TABLE);
		parameters.add("");
		parameters.add(Boolean.TRUE);
		parameters.add(0);

		editActionConfig.setActionParameterList(parameters);
		psViewButtonActionConfigList.add(editActionConfig);

		GtnUIFrameWorkActionConfig disableAction = new GtnUIFrameWorkActionConfig();
		disableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		

		disableAction.setActionParameterList(Arrays.asList(GtnFrameworkPSConstants.getADD_VIEW_DISABLE_FIELD()));
		psViewButtonActionConfigList.add(disableAction);

		GtnUIFrameWorkActionConfig visibleAction = new GtnUIFrameWorkActionConfig();
		visibleAction.setActionType(GtnUIFrameworkActionType.VISIBLE_ACTION);
		

		List<Object> visibleParameters = new ArrayList<>();
		visibleParameters.add(Boolean.FALSE);
		visibleParameters.add(Arrays.asList(GtnFrameworkPSConstants.getADD_VIEW_VISIBLE_FIELD()));

		visibleAction.setActionParameterList(visibleParameters);
		psViewButtonActionConfigList.add(visibleAction);

		psViewButtonConfig.setGtnUIFrameWorkActionConfigList(psViewButtonActionConfigList);

	}

	/**
	 * 
	 * @param componentList
	 */
	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psExcelButtonLayout = configProvider.getHorizontalLayoutConfig(
				"gtnExcelButtonlayout", true, GtnFrameworkCommonConstants.ACTION_BUTTON_LAYOUT);
		componentList.add(psExcelButtonLayout);

		GtnUIFrameworkComponentConfig psExcelButton = configProvider.getUIFrameworkComponentConfig(null, true,
				"gtnExcelButtonlayout", GtnUIFrameworkComponentType.EXCEL_BUTTON);
		psExcelButton.setAuthorizationIncluded(true);
		componentList.add(psExcelButton);
		GtnUIFrameworkExcelButtonConfig psExcelButtonConfig = configProvider.getExcelBtnconfig("PRICESCHEDULE", true,
				GtnFrameworkCommonConstants.PS_SEARCH_RESULT_TABLE, false);
		psExcelButtonConfig.setTitleNeeded(true);
		psExcelButton.setGtnUIFrameworkExcelButtonConfig(psExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(psExcelButtonConfig);
		psExcelButton.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

}
