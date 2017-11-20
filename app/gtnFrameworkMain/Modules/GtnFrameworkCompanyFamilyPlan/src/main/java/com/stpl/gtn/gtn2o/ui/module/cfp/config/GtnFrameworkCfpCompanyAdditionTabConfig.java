package com.stpl.gtn.gtn2o.ui.module.cfp.config;

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
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.cfp.contants.GtnFrameworkCfpClassContants;
import com.stpl.gtn.gtn2o.ws.companyfamilyplan.constants.GtnWsCFamilyPlanContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkCfpCompanyAdditionTabConfig {

	public void addCompanyAdditionTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkComponentConfig companyAdditionMainLayout = componentConfig.getRootLayoutConfig(
				GtnFrameworkCommonConstants.COMPANY_ADDITION_TAB, GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, true);
		companyAdditionMainLayout.setSpacing(true);
		companyAdditionMainLayout.setMargin(true);
		componentList.add(companyAdditionMainLayout);

		GtnUIFrameworkComponentConfig companyAdditionMainCSSLayout = componentConfig
				.getCssLayoutConfig("addressTabLayout", true, GtnFrameworkCommonConstants.COMPANY_ADDITION_TAB);
		companyAdditionMainCSSLayout.setSpacing(true);
		companyAdditionMainCSSLayout.setMargin(true);
		companyAdditionMainCSSLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(companyAdditionMainCSSLayout);

		companyAdditionFields(componentList, componentConfig);
	}

	private void companyAdditionFields(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig panelLayoutConfig = componentConfig.getVerticalLayoutConfig(
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_PANEL_LAYOUT, true,
				GtnFrameworkCommonConstants.COMPANY_ADDITION_TAB);
		panelLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(panelLayoutConfig);
		addSearchLayout(componentList, componentConfig);
		addDualListBoxLayout(componentList, componentConfig);
		addDualListBoxLayoutOnView(componentList, componentConfig);
	}

	private void addSearchLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig infoLayoutConfig = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT, true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_PANEL_LAYOUT);
		infoLayoutConfig.setSpacing(true);
		infoLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(infoLayoutConfig);
		identifierInformationFields(componentList, componentConfig);
	}

	private void identifierInformationFields(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addSearchFields(componentList, componentConfig);
		addSearchValue(componentList, componentConfig);
		addValueDropDown(componentList, componentConfig);
		addSearchButtonComponent(componentList, componentConfig);
	}

	private void addSearchFields(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig searchFieldLayout = componentConfig.getHorizontalLayoutConfig(
				"CFPCompanyAdditionSearchFieldlayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT);
		searchFieldLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(searchFieldLayout);

		GtnUIFrameworkComponentConfig searchField = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_FIELD, true,
				"CFPCompanyAdditionSearchFieldlayout", GtnUIFrameworkComponentType.COMBOBOX);
		searchField.setComponentName("Search Field");
		searchField.setAuthorizationIncluded(true);
		componentList.add(searchField);

		GtnUIFrameworkComboBoxConfig searchFieldConfig = new GtnUIFrameworkComboBoxConfig();
		searchFieldConfig.setItemValues(Arrays.asList(GtnFrameworkCommonConstants.LABEL_COMPANY_NAME,
				GtnFrameworkCommonConstants.LABEL_COMPANY_NO, GtnFrameworkCommonConstants.LABEL_COMPANY_STATUS,
				GtnFrameworkCommonConstants.LABEL_COMPANY_TYPE));
		searchField.setGtnComboboxConfig(searchFieldConfig);

		GtnUIFrameworkValidationConfig searchFieldValidationConfig = new GtnUIFrameworkValidationConfig();
		searchFieldValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		searchField.setGtnUIFrameworkValidationConfig(searchFieldValidationConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(
				GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_ITEM_ADDITION_SEARCH_VALUE_CHANGE_ACTION);
		actionConfigList.add(customAction);
		searchField.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSearchValue(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig searchValueLayout = componentConfig.getHorizontalLayoutConfig(
				"CFPCompanyAdditionSearchValueLayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT);
		searchValueLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(searchValueLayout);

		GtnUIFrameworkComponentConfig searchValue = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_VALUE, true,
				"CFPCompanyAdditionSearchValueLayout", GtnUIFrameworkComponentType.TEXTBOX);
		searchValue.setComponentName("Value");
		searchValue.setAuthorizationIncluded(true);

		GtnUIFrameworkTextBoxConfig searchValueConfig = componentConfig.getTextBoxConfig(true, true, true);
		searchValue.setGtnTextBoxConfig(searchValueConfig);

		GtnUIFrameworkValidationConfig searchValueValidationConfig = new GtnUIFrameworkValidationConfig();
		searchValueValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));

		searchValue.setGtnUIFrameworkValidationConfig(searchValueValidationConfig);

		componentList.add(searchValue);

	}

	private void addValueDropDown(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig valueDdlbLayout = componentConfig.getHorizontalLayoutConfig(
				"cfpCompanyAdditionTabValueDropDownLayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT);
		valueDdlbLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		valueDdlbLayout.setVisible(false);
		componentList.add(valueDdlbLayout);

		GtnUIFrameworkComponentConfig valueDdlb = componentConfig.getUIFrameworkComponentConfig(
				"cfpCompanyAdditionTabValueDropDown", true, "cfpCompanyAdditionTabValueDropDownLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		valueDdlb.setComponentName("Value");
		valueDdlb.setAuthorizationIncluded(true);
		componentList.add(valueDdlb);
		valueDdlb.setEnable(true);

		GtnUIFrameworkComboBoxConfig valueDdlbConfig = componentConfig.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		valueDdlb.setGtnComboboxConfig(valueDdlbConfig);

		List<GtnUIFrameWorkActionConfig> valueChangeActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(
				GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_ITEM_ADDITION_SEARCH_DDLB_VALUE_CHANGE_ACTION);
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_VALUE));
		valueChangeActionConfigList.add(customAction);
		valueDdlb.setGtnUIFrameWorkActionConfigList(valueChangeActionConfigList);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"cfpCompanyAdditiongtnSearch01", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_INFORMATION_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setComponentName("Search");
		searchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_FIELD,
						GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_VALUE));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParamsList);
		Object validationType = GtnUIFrameworkValidationType.AND;
		validationActionConfig.setActionParameterList(Arrays.asList(validationType, Arrays.asList(alertActionConfig)));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig.addActionParameter("CFPleftResultTable");
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_FIELD,
						GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_VALUE));
		actionConfigList.add(loadDataTableActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDualListBoxLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig dualListLayoutConfig = componentConfig.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT, true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_PANEL_LAYOUT);
		componentList.add(dualListLayoutConfig);
		leftResultLayout(componentList, componentConfig);
		moveButtons(componentList, componentConfig);
		rightResultLayout(componentList, componentConfig);
	}

	private void moveButtons(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig moveBtnLayoutConfig = componentConfig.getCssLayoutConfig(
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_BUTTONS_LAYOUT, true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT);
		moveBtnLayoutConfig.setSpacing(true);
		moveBtnLayoutConfig.setMargin(true);
		moveBtnLayoutConfig.setComponentStyle(Arrays.asList("gtnGrid-DualList-Buttons"));
		componentList.add(moveBtnLayoutConfig);
		moveRight(componentList, componentConfig);
		moveLeft(componentList, componentConfig);
		moveAllRight(componentList, componentConfig);
		moveAllLeft(componentList, componentConfig);
	}

	private void moveRight(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig moveRightLayout = componentConfig.getHorizontalLayoutConfig(
				"CFPcompanyAdditionmoverightButtonsLayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_BUTTONS_LAYOUT);
		componentList.add(moveRightLayout);

		GtnUIFrameworkComponentConfig moveRightBtnConfig = componentConfig.getUIFrameworkComponentConfig(
				"CFPcompanyAdditionmoverightButtons", true, "CFPcompanyAdditionmoverightButtonsLayout",
				GtnUIFrameworkComponentType.BUTTON);
		moveRightBtnConfig.setComponentName(" > ");
		moveRightBtnConfig.setAuthorizationIncluded(true);
		moveRightBtnConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		componentList.add(moveRightBtnConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_MOVE_RIGHT_ACTION);
		actionConfigList.add(customAction);

		moveRightBtnConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void moveLeft(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig moveLeftBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"CFPcompanyAdditionmoveLeftButtonsLayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_BUTTONS_LAYOUT);
		componentList.add(moveLeftBtnLayout);

		GtnUIFrameworkComponentConfig moveLeftBtnConfig = componentConfig.getUIFrameworkComponentConfig(
				"CFPcompanyAdditionmoveLeftButtons", true, "CFPcompanyAdditionmoveLeftButtonsLayout",
				GtnUIFrameworkComponentType.BUTTON);
		moveLeftBtnConfig.setComponentName(" < ");
		moveLeftBtnConfig.setAuthorizationIncluded(true);
		moveLeftBtnConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		componentList.add(moveLeftBtnConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_MOVE_LEFT_ACTION);
		actionConfigList.add(customAction);
		moveLeftBtnConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void moveAllRight(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig moveAllRightBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"CFPcompanyAdditionmoveAllrightButtonsLayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_BUTTONS_LAYOUT);
		componentList.add(moveAllRightBtnLayout);

		GtnUIFrameworkComponentConfig moveAllRightBtnConfig = componentConfig.getUIFrameworkComponentConfig(
				"CFPcompanyAdditionmoveAllrightButtons", true, "CFPcompanyAdditionmoveAllrightButtonsLayout",
				GtnUIFrameworkComponentType.BUTTON);
		moveAllRightBtnConfig.setComponentName(" >> ");
		moveAllRightBtnConfig.setAuthorizationIncluded(true);
		moveAllRightBtnConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		componentList.add(moveAllRightBtnConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_MOVEALL_RIGHT_ACTION);
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_FIELD,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_SEARCH_VALUE));
		actionConfigList.add(customAction);
		moveAllRightBtnConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void moveAllLeft(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig moveAllLeftBtnLayout = componentConfig.getHorizontalLayoutConfig(
				"CFPcompanyAdditionmoveAllLeftButtonsLayout", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_MOVE_BUTTONS_LAYOUT);
		componentList.add(moveAllLeftBtnLayout);

		GtnUIFrameworkComponentConfig moveAllLeftBtn = componentConfig.getUIFrameworkComponentConfig(
				"CFPcompanyAdditionmoveAllLeftButtons", true, "CFPcompanyAdditionmoveAllLeftButtonsLayout",
				GtnUIFrameworkComponentType.BUTTON);
		moveAllLeftBtn.setComponentName(" << ");
		moveAllLeftBtn.setAuthorizationIncluded(true);
		moveAllLeftBtn.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		componentList.add(moveAllLeftBtn);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkCfpClassContants.GTNFRAMEWORK_CFP_MOVEALL_LEFT_ACTION);
		actionConfigList.add(customAction);
		moveAllLeftBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void leftResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig leftTableLayoutConfig = componentConfig.getHorizontalLayoutConfig(
				"CFPleftResultLayout", true, GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT);
		leftTableLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_600);
		componentList.add(leftTableLayoutConfig);

		leftResultDataTable(componentList, componentConfig);
	}

	private void leftResultDataTable(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig leftTableLayoutConfig = componentConfig.getUIFrameworkComponentConfig(
				"CFPleftResultTable", true, "CFPleftResultLayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		leftTableLayoutConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		leftTableLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		leftTableLayoutConfig.setAuthorizationIncluded(true);
		componentList.add(leftTableLayoutConfig);

		GtnUIFrameworkPagedTableConfig leftResultsTable = componentConfig.getPagedTableConfig(true, true,
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE + GtnWsCFamilyPlanContants.GTN_WS_CFP_SEARCH_SERVICE,
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE + GtnWsCFamilyPlanContants.GTN_WS_CFP_SEARCH_SERVICE,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN, "");
		leftTableLayoutConfig.setGtnPagedTableConfig(leftResultsTable);
		leftResultsTable.setEditable(false);
		leftResultsTable.setMultiSelect(true);
		leftResultsTable.setTableColumnDataType(new Class[] { String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class });
		leftResultsTable.setTableVisibleHeader(new String[] { GtnFrameworkCommonConstants.LABEL_COMPANY_ID,
				GtnFrameworkCommonConstants.LABEL_COMPANY_NO, GtnFrameworkCommonConstants.LABEL_COMPANY_NAME,
				GtnFrameworkCommonConstants.LABEL_COMPANY_STATUS, GtnFrameworkCommonConstants.LABEL_COMPANY_TYPE,
				GtnFrameworkCommonConstants.LABEL_COMPANY_TRADE_CLASS,
				GtnFrameworkCommonConstants.LABEL_COMPANY_CATEGORY, GtnFrameworkCommonConstants.LABEL_COMPANY_GROUP });
		leftResultsTable.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_STATUS, GtnFrameworkCommonConstants.PROPERTY_COMPANY_TYPE,
				GtnFrameworkCommonConstants.TRADE_CLASS, GtnFrameworkCommonConstants.PROPERTY_COMPANY_CATEGORY,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_GROUP });
		leftResultsTable.setExtraColumn(new Object[] { GtnFrameworkCommonConstants.COMPANY_MASTER_SID });
		leftResultsTable.setCustomFilterConfigMap(getCustomFilterConfig());
	}

	private void rightResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig rightTableConfig = componentConfig.getHorizontalLayoutConfig(
				"CFPrightResultLayout", true, GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_DUAL_LIST_BOX_LAYOUT);
		rightTableConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_600);
		componentList.add(rightTableConfig);

		rightResultDataTable(componentList, componentConfig);
	}

	private void rightResultDataTable(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig rightResultLayoutConfig = componentConfig.getUIFrameworkComponentConfig(
				"CFPrightResultTable", true, "CFPrightResultLayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		rightResultLayoutConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		rightResultLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		rightResultLayoutConfig.setAuthorizationIncluded(true);
		componentList.add(rightResultLayoutConfig);

		GtnUIFrameworkPagedTableConfig rightResultsTable = componentConfig.getPagedTableConfig(true, true,
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
						+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_RIGHT_TABLE_SEARCH_SERVICE,
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
						+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_RIGHT_TABLE_SEARCH_SERVICE,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN, "");
		rightResultLayoutConfig.setGtnPagedTableConfig(rightResultsTable);
		rightResultsTable.setEditable(false);
		rightResultsTable.setMultiSelect(true);
		rightResultsTable.setTableColumnDataType(new Class[] { String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class });
		rightResultsTable.setTableVisibleHeader(new String[] { GtnFrameworkCommonConstants.LABEL_COMPANY_ID,
				GtnFrameworkCommonConstants.LABEL_COMPANY_NO, GtnFrameworkCommonConstants.LABEL_COMPANY_NAME,
				GtnFrameworkCommonConstants.LABEL_COMPANY_STATUS, GtnFrameworkCommonConstants.LABEL_COMPANY_TYPE,
				GtnFrameworkCommonConstants.LABEL_COMPANY_TRADE_CLASS,
				GtnFrameworkCommonConstants.LABEL_COMPANY_CATEGORY, GtnFrameworkCommonConstants.LABEL_COMPANY_GROUP });
		rightResultsTable.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_STATUS, GtnFrameworkCommonConstants.PROPERTY_COMPANY_TYPE,
				GtnFrameworkCommonConstants.TRADE_CLASS, GtnFrameworkCommonConstants.PROPERTY_COMPANY_CATEGORY,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_GROUP });
		rightResultsTable.setExtraColumn(new Object[] { GtnFrameworkCommonConstants.COMPANY_MASTER_SID });
		rightResultsTable.setCustomFilterConfigMap(getCustomFilterConfig());
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { GtnFrameworkCommonConstants.PROPERTY_COMPANY_STATUS,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_TYPE, GtnFrameworkCommonConstants.TRADE_CLASS,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_CATEGORY,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_GROUP };
		String[] listNameList = { "STATUS", "COMPANY_TYPE", "COMPANY_TRADE_CLASS", "COMPANY_CATEGORY",
				"COMPANY_GROUP" };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			customFilterConfig.setPropertId(propertyIds[i]);
			customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			customFilterComponentConfig.setComponentId("customFilterComboBox");
			customFilterComponentConfig.setComponentName("customFilterComboBox");
			customFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			customFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(listNameList[i]);
			customFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			customFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
			customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);

		}
		return customFilterConfigMap;
	}

	private void addDualListBoxLayoutOnView(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {

		GtnUIFrameworkComponentConfig viewResultLayoutConfig = componentConfig.getCssLayoutConfig(
				"cfpCompanyAdditionDualListBoxLayoutOnView", true,
				GtnFrameworkCommonConstants.CFP_COMPANY_ADDITION_PANEL_LAYOUT);
		componentList.add(viewResultLayoutConfig);

		rightResultLayoutOnView(componentList, componentConfig);
	}

	private void rightResultLayoutOnView(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig viewLayoutConfig = componentConfig.getHorizontalLayoutConfig(
				"cfprightResultLayoutOnView", true, "cfpCompanyAdditionDualListBoxLayoutOnView");
		viewLayoutConfig.setComponentWidth("1586px");
		componentList.add(viewLayoutConfig);

		rightResultDataTableOnView(componentList, componentConfig);
	}

	private void rightResultDataTableOnView(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig viewResultConfig = componentConfig.getUIFrameworkComponentConfig(
				"cfprightResultTableOnView", true, "cfprightResultLayoutOnView",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		viewResultConfig.setAuthorizationIncluded(true);
		viewResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		viewResultConfig.setComponentWidth("1586px");
		componentList.add(viewResultConfig);

		GtnUIFrameworkPagedTableConfig resultsTable = componentConfig.getPagedTableConfig(true, true,
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
						+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_RIGHT_TABLE_SEARCH_SERVICE,
				GtnWsCFamilyPlanContants.GTN_WS_CFP_SERVICE
						+ GtnWsCFamilyPlanContants.GTN_WS_CFP_COMPANY_ADDITION_RIGHT_TABLE_SEARCH_SERVICE,
				GtnFrameworkCommonConstants.COMPANY_FAMILY_PLAN, "");
		viewResultConfig.setGtnPagedTableConfig(resultsTable);
		resultsTable.setEditable(false);
		resultsTable.setMultiSelect(true);
		resultsTable.setTableColumnDataType(new Class[] { String.class, String.class, String.class, String.class,
				String.class, String.class, String.class, String.class });
		resultsTable.setTableVisibleHeader(new String[] { GtnFrameworkCommonConstants.LABEL_COMPANY_ID,
				GtnFrameworkCommonConstants.LABEL_COMPANY_NO, GtnFrameworkCommonConstants.LABEL_COMPANY_NAME,
				GtnFrameworkCommonConstants.LABEL_COMPANY_STATUS, GtnFrameworkCommonConstants.LABEL_COMPANY_TYPE,
				GtnFrameworkCommonConstants.LABEL_COMPANY_TRADE_CLASS,
				GtnFrameworkCommonConstants.LABEL_COMPANY_CATEGORY, GtnFrameworkCommonConstants.LABEL_COMPANY_GROUP });
		resultsTable.setTableColumnMappingId(new Object[] { GtnFrameworkCommonConstants.PROPERTY_COMPANY_ID,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_NO, GtnFrameworkCommonConstants.PROPERTY_COMPANY_NAME,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_STATUS, GtnFrameworkCommonConstants.PROPERTY_COMPANY_TYPE,
				GtnFrameworkCommonConstants.TRADE_CLASS, GtnFrameworkCommonConstants.PROPERTY_COMPANY_CATEGORY,
				GtnFrameworkCommonConstants.PROPERTY_COMPANY_GROUP });
		resultsTable.setExtraColumn(new Object[] { GtnFrameworkCommonConstants.COMPANY_MASTER_SID });
		resultsTable.setCustomFilterConfigMap(getCustomFilterConfig());
	}
}
