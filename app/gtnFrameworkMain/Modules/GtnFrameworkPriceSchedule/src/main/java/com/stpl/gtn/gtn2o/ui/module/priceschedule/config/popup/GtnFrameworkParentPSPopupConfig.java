package com.stpl.gtn.gtn2o.ui.module.priceschedule.config.popup;

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
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkParentPSPopupConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig parentPSPopupView = configProvider.getViewConfig(
				GtnFrameworkCommonConstants.PARENT_PRICE_POP_UP_SEARCH_VIEW,
				GtnFrameworkCommonConstants.PARENT_PRICE_POP_UP_SEARCH_VIEW, false);
		addComponentList(parentPSPopupView);
		return parentPSPopupView;
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
		GtnUIFrameworkComponentConfig parentPSPopupSearchCriteriaLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.PARENT_PS_SEARCH_CRITERIA_LAYOUT, false, null);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		parentPSPopupSearchCriteriaLayout.setComponentStyle(styleList);
		componentList.add(parentPSPopupSearchCriteriaLayout);
		addFieldComponent(componentList);

	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		addPriceScheduleId(componentList);
		addPriceScheduleNo(componentList);
		addPriceScheduleName(componentList);
		addPriceScheduleType(componentList);
		addPriceScheduleStatus(componentList);
		addItemId(componentList);
		addItemNO(componentList);
		addItemName(componentList);

	}

	private void addPriceScheduleType(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupPriceScheduleTypeLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE_LAYOUT, true,
				GtnFrameworkCommonConstants.PARENT_PS_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentPSPopupPriceScheduleTypeLayout);

		GtnUIFrameworkComponentConfig parentPSPopupPriceScheduleType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_PRICE_SCHEDULE_TYPE1, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		parentPSPopupPriceScheduleType.setAuthorizationIncluded(true);
		parentPSPopupPriceScheduleType.setComponentName("Price Schedule Type");
		parentPSPopupPriceScheduleType.setComponentWsFieldId(GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE);
		componentList.add(parentPSPopupPriceScheduleType);

		GtnUIFrameworkComboBoxConfig parentPSPopupPriceScheduleTypeConfig = configProvider.getComboBoxConfig("PS_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		parentPSPopupPriceScheduleType.setGtnComboboxConfig(parentPSPopupPriceScheduleTypeConfig);

	}

	private void addPriceScheduleId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupPriceScheduleIdLayout = configProvider.getHorizontalLayoutConfig(
				"parentPSpriceScheduleIdLayout", true, GtnFrameworkCommonConstants.PARENT_PS_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentPSPopupPriceScheduleIdLayout);
		GtnUIFrameworkComponentConfig parentPSPopupPriceScheduleId = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_ID, true, "parentPSpriceScheduleIdLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		parentPSPopupPriceScheduleId.setAuthorizationIncluded(true);
		parentPSPopupPriceScheduleId.setComponentName("Price Schedule ID");
		parentPSPopupPriceScheduleId.setComponentWsFieldId(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID);
		componentList.add(parentPSPopupPriceScheduleId);
	}

	private void addPriceScheduleNo(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupPriceScheduleNoLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_NO_LAYOUT, true,
				GtnFrameworkCommonConstants.PARENT_PS_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentPSPopupPriceScheduleNoLayout);
		GtnUIFrameworkComponentConfig companyNoConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_NO1, true,
				GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_NO_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		companyNoConfig.setAuthorizationIncluded(true);
		companyNoConfig.setComponentName("Price Schedule No");
		companyNoConfig.setComponentWsFieldId("priceScheduleNo");
		componentList.add(companyNoConfig);
	}

	private void addPriceScheduleName(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig parentPSPopupPriceScheduleNameLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_NAME_LAYOUT, true,
				GtnFrameworkCommonConstants.PARENT_PS_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentPSPopupPriceScheduleNameLayout);

		GtnUIFrameworkComponentConfig parentPSPopupPriceScheduleName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_NAME1, true,
				GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_NAME_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		parentPSPopupPriceScheduleName.setComponentName("Price Schedule Name");
		parentPSPopupPriceScheduleName.setAuthorizationIncluded(true);
		parentPSPopupPriceScheduleName.setComponentWsFieldId(GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME);
		componentList.add(parentPSPopupPriceScheduleName);
	}

	private void addPriceScheduleStatus(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupPriceScheduleStatusLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PS_POPUP_PRICE_SCHEDULE_STATUS_LAYOUT, true,
				GtnFrameworkCommonConstants.PARENT_PS_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentPSPopupPriceScheduleStatusLayout);
		GtnUIFrameworkComponentConfig parentPSPopupPriceScheduleStatus = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS2, true,
				GtnFrameworkCommonConstants.PS_POPUP_PRICE_SCHEDULE_STATUS_LAYOUT,
				GtnUIFrameworkComponentType.COMBOBOX);
		parentPSPopupPriceScheduleStatus.setAuthorizationIncluded(true);
		parentPSPopupPriceScheduleStatus.setComponentName("Price Schedule Status");
		parentPSPopupPriceScheduleStatus.setComponentWsFieldId(GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS);
		componentList.add(parentPSPopupPriceScheduleStatus);
		GtnUIFrameworkComboBoxConfig parentPSPopupPriceScheduleStatusLayoutConfig = configProvider
				.getComboBoxConfig("STATUS", GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		parentPSPopupPriceScheduleStatus.setGtnComboboxConfig(parentPSPopupPriceScheduleStatusLayoutConfig);

	}

	private void addItemId(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupItemIdLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_ID_LAYOUT, true,
				GtnFrameworkCommonConstants.PARENT_PS_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentPSPopupItemIdLayout);

		GtnUIFrameworkComponentConfig parentPSPopupItemId = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_ID, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_ID_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		parentPSPopupItemId.setComponentName("Item ID");
		parentPSPopupItemId.setAuthorizationIncluded(true);
		parentPSPopupItemId.setComponentWsFieldId("itemId");
		componentList.add(parentPSPopupItemId);

	}

	private void addItemNO(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupItemNOLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_NO_LAYOUT, true,
				GtnFrameworkCommonConstants.PARENT_PS_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentPSPopupItemNOLayout);

		GtnUIFrameworkComponentConfig parentPSPopupItemNO = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_NO, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_NO_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		parentPSPopupItemNO.setComponentName("Item No");
		parentPSPopupItemNO.setAuthorizationIncluded(true);
		parentPSPopupItemNO.setComponentWsFieldId("itemNo");
		componentList.add(parentPSPopupItemNO);
	}

	private void addItemName(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupItemNameLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_NAME_LAYOUT, true,
				GtnFrameworkCommonConstants.PARENT_PS_SEARCH_CRITERIA_LAYOUT);
		componentList.add(parentPSPopupItemNameLayout);

		GtnUIFrameworkComponentConfig parentPSPopupItemName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_NAME, true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_NAME_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
		parentPSPopupItemName.setComponentName("Item Name");
		parentPSPopupItemName.setAuthorizationIncluded(true);
		parentPSPopupItemName.setComponentWsFieldId("itemName");
		componentList.add(parentPSPopupItemName);

	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupButtonLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.PARENT_PRICE_SEARCH_BUTTON_LAYOUT, false, null);
		parentPSPopupButtonLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(parentPSPopupButtonLayout);
		addSearchButtonComponent(componentList);
		addResetButtonComponent(componentList);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupSearchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"netSales01", true, GtnFrameworkCommonConstants.PARENT_PRICE_SEARCH_BUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		parentPSPopupSearchButtonConfig.setAuthorizationIncluded(true);
		parentPSPopupSearchButtonConfig.setComponentName("Search");
		componentList.add(parentPSPopupSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> parentPSPopupSearchActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		List<Object> actionParams = new ArrayList<>();
		actionParams.add(GtnFrameworkCommonConstants.PARENT_PRICE_RESULT_TABLE);
		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.PARENT_PRICE_SCHEDULE_TYPE1,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS2,
				GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_NAME1,
				GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_NO1,
				GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_ID,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_ID, GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_NO,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_NAME));
		parentPSPopupSearchActionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig.addActionParameter("psSearchResultTable");
		parentPSPopupSearchActionConfigList.add(notificationActionConfig);
		parentPSPopupSearchButtonConfig.setGtnUIFrameWorkActionConfigList(parentPSPopupSearchActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig parentPSPopupResetButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"complianceAndDeductionFormulasReset01", true,
				GtnFrameworkCommonConstants.PARENT_PRICE_SEARCH_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		parentPSPopupResetButtonConfig.setComponentName("Reset");
		parentPSPopupResetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(parentPSPopupResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> parentPSPopupResetParams = new ArrayList<>();
		parentPSPopupResetParams.add("Reset Confirmation");
		parentPSPopupResetParams.add("Are you sure you want to reset the values in the Search Criteria group box?");

		Map<String, Object> parentPSPopupResetMap = new HashMap<>();
		parentPSPopupResetMap.put(GtnFrameworkCommonConstants.PARENT_PRICE_SCHEDULE_TYPE1, null);
		parentPSPopupResetMap.put(GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS2, null);
		parentPSPopupResetMap.put(GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_NAME1, "");
		parentPSPopupResetMap.put(GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_NO1, "");
		parentPSPopupResetMap.put(GtnFrameworkCommonConstants.PARENT_PS_PRICE_SCHEDULE_ID, "");
		parentPSPopupResetMap.put(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_ID, "");
		parentPSPopupResetMap.put(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_NO, "");
		parentPSPopupResetMap.put(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_NAME, "");
		parentPSPopupResetMap.put(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ITEM_NAME, "");
		parentPSPopupResetMap.put(GtnFrameworkCommonConstants.PARENT_PRICE_RESULT_TABLE, null);
		parentPSPopupResetParams.add(parentPSPopupResetMap);

		resetActionConfig.setActionParameterList(parentPSPopupResetParams);
		actionConfigList.add(resetActionConfig);
		parentPSPopupResetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupResultPanel = configProvider.getPanelConfig("resultPanel", false,
				null);
		parentPSPopupResultPanel.setComponentName("Results");
		componentList.add(parentPSPopupResultPanel);
		addResultLayout(componentList);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupResultLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_RESULT_LAYOUT, true, "resultPanel");
		parentPSPopupResultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(parentPSPopupResultLayout);
		addPagedTableComponent(componentList);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig parentPSPopupResultTable = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.PARENT_PRICE_RESULT_TABLE, true,
				GtnFrameworkCommonConstants.NET_SALES_FORMULA_RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
		parentPSPopupResultTable.setAuthorizationIncluded(true);
		parentPSPopupResultTable.setComponentName("Results");
		parentPSPopupResultTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig parentPSPopupResultTableValConfig = new GtnUIFrameworkValidationConfig();
		parentPSPopupResultTableValConfig.setMinSize(1);
		parentPSPopupResultTable.setGtnUIFrameworkValidationConfig(parentPSPopupResultTableValConfig);

		componentList.add(parentPSPopupResultTable);

		GtnUIFrameworkPagedTableConfig parentPSPopupResultTableConfig = configProvider.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				"priceSchedule", "priceScheduleSearch");
		parentPSPopupResultTableConfig.setEditable(false);
		parentPSPopupResultTableConfig.setItemPerPage(10);
		parentPSPopupResultTableConfig.setPageLength(10);
		parentPSPopupResultTableConfig.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class,
				String.class, Date.class, String.class, Date.class, String.class });
		parentPSPopupResultTableConfig.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class,
				String.class, String.class, String.class, String.class, Date.class,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, String.class, String.class, String.class, String.class });
		parentPSPopupResultTableConfig.setTableVisibleHeader(
				new String[] { "System ID", "Price Schedule ID", "Price Schedule No", "Price Schedule Name",
						"Price Schedule Type", "Price Schedule Status", "Price Schedule Category", "Start Date",
						"End Date", "Price Schedule Designation", "Parent ID", "Parent Name", "Trade Class" });
		parentPSPopupResultTableConfig.setTableColumnMappingId(new Object[] { "systemId",
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID, "priceScheduleNo",
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME, GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS, "priceScheduleCategory", "startDate", "endDate",
				"priceScheduleDesignation", "parentID", "parentName", "tradeClass" });
		parentPSPopupResultTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE);

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> parentPSPopupCustomFilterConfigMap = new HashMap<>();

		GtnUIFrameworkPagedTableCustomFilterConfig parentPSPopupCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		parentPSPopupCustomFilterConfig.setPropertId(GtnFrameworkCommonConstants.PRICE_SCHEDULE_TYPE);
		parentPSPopupCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		customFilterComponentConfig.setComponentId("ruleTypecustomFilterComboBox");
		customFilterComponentConfig.setComponentName("customFilterComboBox");
		GtnUIFrameworkComboBoxConfig ruleTypeConfig = configProvider.getComboBoxConfig("PS_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		customFilterComponentConfig.setGtnComboboxConfig(ruleTypeConfig);

		customFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		parentPSPopupCustomFilterConfig.setGtnComponentConfig(customFilterComponentConfig);

		GtnUIFrameworkPagedTableCustomFilterConfig parentPSPopupRuleCategoryCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
		parentPSPopupRuleCategoryCustomFilterConfig.setPropertId(GtnFrameworkCommonConstants.PRICE_SCHEDULE_STATUS);
		parentPSPopupRuleCategoryCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
		GtnUIFrameworkComponentConfig parentPSPopupRuleCategoryCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
		parentPSPopupRuleCategoryCustomFilterComponentConfig.setComponentId("ruleCategorycustomFilterComboBox");
		parentPSPopupRuleCategoryCustomFilterComponentConfig.setComponentName("customFilterComboBox");

		GtnUIFrameworkComboBoxConfig parentPSPopupRuleCategoryConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		parentPSPopupRuleCategoryCustomFilterComponentConfig.setGtnComboboxConfig(parentPSPopupRuleCategoryConfig);
		parentPSPopupRuleCategoryCustomFilterComponentConfig.getGtnComboboxConfig().setIsFilterField(true);
		parentPSPopupRuleCategoryCustomFilterConfig
				.setGtnComponentConfig(parentPSPopupRuleCategoryCustomFilterComponentConfig);

		parentPSPopupCustomFilterConfigMap.put(parentPSPopupRuleCategoryCustomFilterConfig.getPropertId(),
				parentPSPopupRuleCategoryCustomFilterConfig);

		parentPSPopupCustomFilterConfigMap.put(parentPSPopupCustomFilterConfig.getPropertId(),
				parentPSPopupCustomFilterConfig);
		parentPSPopupResultTableConfig.setCustomFilterConfigMap(parentPSPopupCustomFilterConfigMap);
		parentPSPopupResultTable.setGtnPagedTableConfig(parentPSPopupResultTableConfig);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupActionBtnLayout = configProvider
				.getCssLayoutConfig(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ACTION_BUTTON_LAYOUT, false, null);
		parentPSPopupActionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(parentPSPopupActionBtnLayout);
		addSelectButtonComponent(componentList);
		addCloseButtonComponent(componentList);
	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupSelectBtn = configProvider.getUIFrameworkComponentConfig(
				"priceSchedulePopUpViewSelectButton", true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ACTION_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		parentPSPopupSelectBtn.setComponentName("SELECT");
		parentPSPopupSelectBtn.setAuthorizationIncluded(true);
		componentList.add(parentPSPopupSelectBtn);

		List<GtnUIFrameWorkActionConfig> parentPSPopupSelectBtnActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig parentPSPopupSelectAction = new GtnUIFrameWorkActionConfig();
		parentPSPopupSelectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(GtnFrameworkCommonConstants.PARENT_PRICE_RESULT_TABLE);
		actionParameter.add("parentPriceScheduleID");
		actionParameter.add(Arrays.asList(GtnFrameworkCommonConstants.PRICE_SCHEDULE_ID,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_NAME));
		actionParameter.add(Arrays.asList("parentPriceScheduleID", "parentPriceScheduleName"));
		parentPSPopupSelectAction.setActionParameterList(actionParameter);
		parentPSPopupSelectBtnActionConfigList.add(parentPSPopupSelectAction);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.PARENT_PRICE_POP_UP_SEARCH_VIEW);
		parentPSPopupSelectBtnActionConfigList.add(closeAction);

		parentPSPopupSelectBtn.setGtnUIFrameWorkActionConfigList(parentPSPopupSelectBtnActionConfigList);

	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig parentPSPopupCloseBtn = configProvider.getUIFrameworkComponentConfig(
				"priceSchedulePopUpViewCloseButton", true,
				GtnFrameworkCommonConstants.PRICE_SCHEDULE_ACTION_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
		parentPSPopupCloseBtn.setComponentName("CLOSE");
		parentPSPopupCloseBtn.setAuthorizationIncluded(true);
		componentList.add(parentPSPopupCloseBtn);

		List<GtnUIFrameWorkActionConfig> parentPSPopupCloseActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(GtnFrameworkCommonConstants.PARENT_PRICE_POP_UP_SEARCH_VIEW);
		parentPSPopupCloseActionConfigList.add(closeAction);

		parentPSPopupCloseBtn.setGtnUIFrameWorkActionConfigList(parentPSPopupCloseActionConfigList);

	}

}
