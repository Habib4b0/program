package com.stpl.gtn.gtn2o.ui.module.priceschedule.config;

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
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.GtnUIFrameWorkAddDataTableAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.action.validation.GtnFramworkItemAdditionSelectValidateAction;
import com.stpl.gtn.gtn2o.ui.module.priceschedule.constants.GtnFrameworkPSConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkPSItemAdditionTabConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addItemAdditionTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig itemAdditionMainLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.ITEM_ADDITION_TAB, false, null);
		itemAdditionMainLayout.setTabComponent(true);
		componentList.add(itemAdditionMainLayout);

		GtnUIFrameworkComponentConfig itemAdditionMainCssLayout = configProvider.getCssLayoutConfig("addressTabLayout",
				true, GtnFrameworkCommonConstants.ITEM_ADDITION_TAB);
		componentList.add(itemAdditionMainCssLayout);

		itemAdditionFields(componentList);
	}

	private void itemAdditionFields(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig itemAdditionFieldsLayoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_PANEL_LAYOUT, true,
				GtnFrameworkCommonConstants.ITEM_ADDITION_TAB);
		itemAdditionFieldsLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(itemAdditionFieldsLayoutConfig);

		addSearchLayout(componentList);
		addDualListBoxLayout(componentList);
	}

	private void addSearchLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchLayoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_FIELD_SEARCH_LAYOUT, true,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_PANEL_LAYOUT);
		searchLayoutConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10));
		componentList.add(searchLayoutConfig);

		identifierInformationFields(componentList);
		addSearchButtonComponent(componentList);
	}

	private void identifierInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addOptionGroup(componentList);
		addSearchFields(componentList);
		addSearchValue(componentList);

	}

	private void addOptionGroup(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig psItemAdditionMassUpdateLayout = configProvider.getHorizontalLayoutConfig(
				"psItemAdditionmassupdatelayout", true,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_FIELD_SEARCH_LAYOUT);
		psItemAdditionMassUpdateLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(psItemAdditionMassUpdateLayout);

		GtnUIFrameworkComponentConfig itemOrIfpOption = configProvider.getUIFrameworkComponentConfig("itemOrIfpOption",
				true, "psItemAdditionmassupdatelayout", GtnUIFrameworkComponentType.OPTIONGROUP);
        itemOrIfpOption.setComponentName("Search Type");
		itemOrIfpOption.setAuthorizationIncluded(true);
		componentList.add(itemOrIfpOption);

		GtnUIFrameworkOptionGroupConfig itemOrIfpOptionConfig = configProvider
				.getOptionGroupConfig(Arrays.asList("Item", "IFP"), "IFP", false);
		itemOrIfpOptionConfig.setEnable(false);
		itemOrIfpOption.setGtnUIFrameworkOptionGroupConfig(itemOrIfpOptionConfig);
		itemOrIfpOption.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
	}

	private void addSearchFields(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psItemAdditionSearchFieldlayout = configProvider.getHorizontalLayoutConfig(
				"psItemAdditionSearchFieldlayout", true,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_FIELD_SEARCH_LAYOUT);
		psItemAdditionSearchFieldlayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(psItemAdditionSearchFieldlayout);

		GtnUIFrameworkComponentConfig psItemAdditionSearchField = configProvider.getUIFrameworkComponentConfig(
				"psItemAdditionSearchField", true, "psItemAdditionSearchFieldlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		psItemAdditionSearchField.setAuthorizationIncluded(true);
		psItemAdditionSearchField.setComponentName("Search Field");
		componentList.add(psItemAdditionSearchField);

		GtnUIFrameworkComboBoxConfig psItemAdditionSearchFieldConfig = new GtnUIFrameworkComboBoxConfig();
		psItemAdditionSearchFieldConfig.setItemValues(
				Arrays.asList(GtnFrameworkCommonConstants.LABEL_IFP_NAME, GtnFrameworkCommonConstants.IFP_NO));
		psItemAdditionSearchField.setGtnComboboxConfig(psItemAdditionSearchFieldConfig);
	}

	private void addSearchValue(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig psItemAdditionSearchValueLayout = configProvider.getHorizontalLayoutConfig(
				"psItemAdditionSearchValueLayout", true,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_FIELD_SEARCH_LAYOUT);
		psItemAdditionSearchValueLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(psItemAdditionSearchValueLayout);

		GtnUIFrameworkComponentConfig psItemAdditionSearchValue = configProvider.getUIFrameworkComponentConfig(
				"psItemAdditionSearchValue", true, "psItemAdditionSearchValueLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		psItemAdditionSearchValue.setComponentName("Value");

		GtnUIFrameworkTextBoxConfig psItemAdditionSearchValueConfig = configProvider.getTextBoxConfig(true, true, true);
		psItemAdditionSearchValue.setGtnTextBoxConfig(psItemAdditionSearchValueConfig);

		componentList.add(psItemAdditionSearchValue);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonLayout = configProvider.getHorizontalLayoutConfig(
				"cfpCompanyAdditiongtnSearch01cssLayout", true,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_FIELD_SEARCH_LAYOUT);
		searchButtonLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		componentList.add(searchButtonLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"cfpCompanyAdditiongtnSearch01", true, "cfpCompanyAdditiongtnSearch01cssLayout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Search");
		componentList.add(searchButtonConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.CFP_LEFT_RESULT_TABLE);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList("psItemAdditionSearchField", "psItemAdditionSearchValue"));
		searchButtonConfig.addGtnUIFrameWorkActionConfig(loadDataTableActionConfig);

	}

	private void addDualListBoxLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig dualListBoxLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT, true,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_PANEL_LAYOUT);
		componentList.add(dualListBoxLayout);

		leftResultLayout(componentList);
		moveButtons(componentList);
		rightResultLayout(componentList);
	}

	private void moveButtons(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig moveButtonsLayoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_MOVE_BUTTONS_LAYOUT, true,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT);
		moveButtonsLayoutConfig.setSpacing(true);
		moveButtonsLayoutConfig.setMargin(true);
		moveButtonsLayoutConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_DUAL_LIST_BOX_BUTTON));
		componentList.add(moveButtonsLayoutConfig);

		moveRight(componentList);
		moveLeft(componentList);
	}

	private void moveRight(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psItemAdditionMoveRightButtonLayout = configProvider.getHorizontalLayoutConfig(
				"psItemAdditionmoverightButtonLayout", true,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_MOVE_BUTTONS_LAYOUT);
		componentList.add(psItemAdditionMoveRightButtonLayout);

		GtnUIFrameworkComponentConfig psItemAdditionMoveRight = configProvider.getUIFrameworkComponentConfig(
				"psItemAdditionmoverightButtons", true, "psItemAdditionmoverightButtonLayout",
				GtnUIFrameworkComponentType.BUTTON);
		psItemAdditionMoveRight.setAuthorizationIncluded(true);
		psItemAdditionMoveRight.setComponentName(" > ");
		psItemAdditionMoveRight.setComponentWidth("53px");
		componentList.add(psItemAdditionMoveRight);

		GtnUIFrameWorkActionConfig moveRightAlertActionConfig = new GtnUIFrameWorkActionConfig();
		moveRightAlertActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFramworkItemAdditionSelectValidateAction.class.getName());
		alertParamsList.add(GtnFrameworkCommonConstants.CFP_LEFT_RESULT_TABLE);
		alertParamsList.add("Please select the IFP to Add.");
		moveRightAlertActionConfig.setActionParameterList(alertParamsList);
		psItemAdditionMoveRight.addGtnUIFrameWorkActionConfig(moveRightAlertActionConfig);

		GtnUIFrameWorkActionConfig moveRightCustomAction = new GtnUIFrameWorkActionConfig();
		moveRightCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		moveRightCustomAction.addActionParameter(GtnUIFrameWorkAddDataTableAction.class.getName());
		psItemAdditionMoveRight.addGtnUIFrameWorkActionConfig(moveRightCustomAction);
	}

	private void moveLeft(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psItemAdditionMoveLeftButtonsLayout = configProvider.getHorizontalLayoutConfig(
				"psItemAdditionmoveLeftButtonsLayout", true,
				GtnFrameworkCommonConstants.PS_ITEM_ADDITION_MOVE_BUTTONS_LAYOUT);
		componentList.add(psItemAdditionMoveLeftButtonsLayout);

		GtnUIFrameworkComponentConfig psItemAdditionMoveLeftButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"psItemAdditionmoveLeftButtons", true, "psItemAdditionmoveLeftButtonsLayout",
				GtnUIFrameworkComponentType.BUTTON);
		psItemAdditionMoveLeftButtonConfig.setComponentName(" < ");
		psItemAdditionMoveLeftButtonConfig.setAuthorizationIncluded(true);
		psItemAdditionMoveLeftButtonConfig.setComponentWidth("53px");
		componentList.add(psItemAdditionMoveLeftButtonConfig);
		
		GtnUIFrameWorkActionConfig moveLeftAlertActionConfig = new GtnUIFrameWorkActionConfig();
		moveLeftAlertActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> alertParameterList = new ArrayList<>();
		alertParameterList.add(GtnFramworkItemAdditionSelectValidateAction.class.getName());
		alertParameterList.add(GtnFrameworkCommonConstants.CFP_RIGHT_RESULT_TABLE);
		alertParameterList.add("Please select a record to remove.");
		moveLeftAlertActionConfig.setActionParameterList(alertParameterList);
		
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig psItemAdditionMoveLeftButtonDefaultActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		Map<String, Object> defaultDataMap = new HashMap<>();
		defaultDataMap.put("CFPrightResultTable", null);
		psItemAdditionMoveLeftButtonDefaultActionConfig.addActionParameter(defaultDataMap);
		
		actionConfigList.add(moveLeftAlertActionConfig);
		actionConfigList.add(psItemAdditionMoveLeftButtonDefaultActionConfig);

		psItemAdditionMoveLeftButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void leftResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psleftResultLayout = configProvider.getHorizontalLayoutConfig(
				"CFPleftResultLayout", true, GtnFrameworkCommonConstants.PS_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT);
		psleftResultLayout.setComponentWidth("600px");
		componentList.add(psleftResultLayout);

		leftResultDataTable(componentList);
	}

	private void leftResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psLeftResultTableConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.CFP_LEFT_RESULT_TABLE, true, "CFPleftResultLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		psLeftResultTableConfig.setAuthorizationIncluded(true);
		psLeftResultTableConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		psLeftResultTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(psLeftResultTableConfig);

		GtnUIFrameworkPagedTableConfig psleftResultTable = configProvider.getPagedTableConfig(true,
				true, "/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_SEARCH_SERVICE,
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_SEARCH_SERVICE,
				"priceScheduleItemAddition", "priceScheduleItemAddition");
		psLeftResultTableConfig.setGtnPagedTableConfig(psleftResultTable);
		psleftResultTable.setEditable(false);
		psleftResultTable.setTableColumnDataType(
				new Class<?>[] { String.class, String.class, String.class, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVAUTIL_DATE,GtnFrameworkCommonConstants.JAVAUTIL_DATE, String.class, String.class });
		psleftResultTable.setTableVisibleHeader(new String[] { "IFP ID", GtnFrameworkCommonConstants.IFP_NO,
				GtnFrameworkCommonConstants.LABEL_IFP_NAME, "IFP Status","Start Date", "End Date", "IFP Type", "IFP Category" });
		psleftResultTable.setTableColumnMappingId(new Object[] { "ifpId", "ifpNo", "ifpName",
				GtnFrameworkCommonConstants.IFP_STATUS,"IFP_START_DATE", "ifpEndDate", GtnFrameworkCommonConstants.PROPERTY_IFP_TYPE,
				GtnFrameworkCommonConstants.PROPERTY_IFP_CATEGORY, });
		psleftResultTable.setExtraColumn(new Object[] { "systemId" });
		psleftResultTable.setExtraColumnDataType(new Class<?>[] { String.class });
		psleftResultTable.setCustomFilterConfigMap(getCustomFilterMap());
		psleftResultTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE);
	}

	private void rightResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psRightResultLayout = configProvider.getHorizontalLayoutConfig(
				"CFPrightResultLayout", true, GtnFrameworkCommonConstants.PS_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT);
		psRightResultLayout.setComponentWidth(GtnFrameworkCssConstants.PIXEL_600);
		componentList.add(psRightResultLayout);

		rightResultDataTable(componentList);
	}

	private void rightResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig psRightResultTableConfig = configProvider.getUIFrameworkComponentConfig(
				"CFPrightResultTable", true, "CFPrightResultLayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		psRightResultTableConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkValidationConfig psRightResultTableValidationConfig = new GtnUIFrameworkValidationConfig();
		psRightResultTableValidationConfig.setMinSize(1);
		psRightResultTableConfig.setGtnUIFrameworkValidationConfig(psRightResultTableValidationConfig);
		psRightResultTableConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		psRightResultTableConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(psRightResultTableConfig);

		GtnUIFrameworkPagedTableConfig psRightResultTable = configProvider.getPagedTableConfig(true, true,
				"/cfp/companyAdditionRightTableSearch", "/cfp/companyAdditionRightTableSearch", "companyFamilyPlan",
				"");
		psRightResultTableConfig.setGtnPagedTableConfig(psRightResultTable);
		psRightResultTable.setEditable(false);
		psRightResultTable.setSortingEnable(false);
		psRightResultTable.setTableColumnDataType(
				new Class<?>[] { String.class, String.class, String.class, GtnFrameworkCommonConstants.JAVALANG_STRING,
						GtnFrameworkCommonConstants.JAVAUTIL_DATE,GtnFrameworkCommonConstants.JAVAUTIL_DATE, String.class, String.class });
		psRightResultTable.setTableVisibleHeader(new String[] { "IFP ID", GtnFrameworkCommonConstants.IFP_NO,
				GtnFrameworkCommonConstants.LABEL_IFP_NAME, "IFP Status","Start Date", "End Date", "IFP Type", "IFP Category" });
		psRightResultTable.setTableColumnMappingId(new Object[] { "ifpId", "ifpNo", "ifpName",
				GtnFrameworkCommonConstants.IFP_STATUS,"IFP_START_DATE", "ifpEndDate", GtnFrameworkCommonConstants.PROPERTY_IFP_TYPE,
				GtnFrameworkCommonConstants.PROPERTY_IFP_CATEGORY });
		psRightResultTable.setExtraColumn(new Object[] { "companyMasterSid" });
		psRightResultTable.setExtraColumnDataType(new Class<?>[] { Integer.class });
		psRightResultTable.setCustomFilterConfigMap(getCustomFilterMap());
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterMap() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> psCustomFilterConfigMap = new HashMap<>();
		String[] propertyIds = GtnFrameworkPSConstants.getCustomFilterPropertyIds();
		String[] customfilterListNameArray = GtnFrameworkPSConstants.getCustomFilterListName();
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			customFilterConfig.setPropertId(propertyIds[i]);
			customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig psCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			psCustomFilterComponentConfig.setComponentId("customFilterComboBox");
			psCustomFilterComponentConfig.setComponentName("customFilterComboBox");
			psCustomFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			psCustomFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(customfilterListNameArray[i]);
			psCustomFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			psCustomFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customFilterConfig.setGtnComponentConfig(psCustomFilterComponentConfig);
			psCustomFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);
		}
		return psCustomFilterConfigMap;
	}
}
