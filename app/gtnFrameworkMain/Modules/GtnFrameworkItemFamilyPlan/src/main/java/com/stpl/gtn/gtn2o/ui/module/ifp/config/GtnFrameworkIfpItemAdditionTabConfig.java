package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpClassContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;

public class GtnFrameworkIfpItemAdditionTabConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addItemAdditionTab(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.ITEM_ADDITION_TAB, false, null);
		gtnLayout.setTabComponent(true);
		gtnLayout.setSpacing(true);
		gtnLayout.setMargin(true);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig ifpItemAdditionLayout = getIfpItemAdditionLayout(
				GtnUIFrameworkLayoutType.CSS_LAYOUT, GtnUIFrameworkComponentType.LAYOUT, "addressTabLayout",
				GtnFrameworkCommonConstants.ITEM_ADDITION_TAB, true, componentList);
		ifpItemAdditionLayout.setSpacing(true);
		ifpItemAdditionLayout.setMargin(true);
		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		ifpItemAdditionLayout.setComponentStyle(styleList);
		ifpItemAdditionFields(componentList);
	}

	private void ifpItemAdditionFields(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_PANEL_LAYOUT, true,
				GtnFrameworkCommonConstants.ITEM_ADDITION_TAB);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);

		addIfpItemAdditionSearchLayout(componentList);
		addIfpItemAdditionDualListBoxLayout(componentList);
		addIfpItemAdditionDualListBoxLayoutOnView(componentList);
	}

	private void addIfpItemAdditionSearchLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = getIfpItemAdditionLayout(GtnUIFrameworkLayoutType.CSS_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT, GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_INFORMATION_LAYOUT,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_PANEL_LAYOUT, true, componentList);
		layoutConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12));

		addSearchFields(componentList);
		addSearchValue(componentList);
		addValueDropDown(componentList);
		addSearchButtonComponent(componentList);
	}

	private void addSearchFields(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = getIfpItemAdditionLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT, "ifpItemAdditionSearchFieldlayout",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_INFORMATION_LAYOUT, true, componentList);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));

		GtnUIFrameworkComponentConfig itemAdditionQualifierName = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_FIELD, true, "ifpItemAdditionSearchFieldlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		itemAdditionQualifierName.setAuthorizationIncluded(true);
		itemAdditionQualifierName.setComponentName("Search Field");
		componentList.add(itemAdditionQualifierName);

		GtnUIFrameworkComboBoxConfig itemAdditionQualifierNameConfig = new GtnUIFrameworkComboBoxConfig();

		itemAdditionQualifierNameConfig.setItemValues(Arrays.asList(GtnFrameworkCommonConstants.BRAND_IFP, "Form",
				"Item ID", GtnFrameworkCommonConstants.ITEM_NO_HEADER, GtnFrameworkCommonConstants.ITEM_NAME_HEADER,
				GtnFrameworkCommonConstants.ITEM_STATUS_HEADER, GtnFrameworkCommonConstants.STRENGTH_IFP,
				GtnFrameworkCommonConstants.THERAPEUTIC_CLASS_VAL, "UOM"));
		itemAdditionQualifierName.setGtnComboboxConfig(itemAdditionQualifierNameConfig);

		GtnUIFrameworkValidationConfig validationConfig = configProvider.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), false, null, null);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(
				GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_ITEM_ADDITION_SEARCH_VALUE_CHANGE_ACTION);
		actionConfigList.add(customAction);
		itemAdditionQualifierName.setGtnUIFrameWorkActionConfigList(actionConfigList);
		itemAdditionQualifierName.setGtnUIFrameworkValidationConfig(validationConfig);
	}

	private void addSearchValue(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = getIfpItemAdditionLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT, "ifpItemAdditionSearchValueLayout",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_INFORMATION_LAYOUT, true, componentList);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));

		GtnUIFrameworkComponentConfig itemIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_VALUE, true, "ifpItemAdditionSearchValueLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		itemIdentifierConfig.setAuthorizationIncluded(true);
		itemIdentifierConfig.setComponentName("Value");
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, true, true);
		itemIdentifierConfig.setGtnTextBoxConfig(textboxConfig);

		GtnUIFrameworkValidationConfig validationConfig = configProvider.getValidationConfig(
				Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY), false, null, null);
		itemIdentifierConfig.setGtnUIFrameworkValidationConfig(validationConfig);

		componentList.add(itemIdentifierConfig);

	}

	private void addValueDropDown(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = getIfpItemAdditionLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT,
				GtnUIFrameworkComponentType.LAYOUT, "ifpItemAdditionTabValueDropDownLayout",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_INFORMATION_LAYOUT, true, componentList);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		gtnLayout.setVisible(false);
		GtnUIFrameworkComponentConfig valueDdlb = configProvider.getUIFrameworkComponentConfig(
				"ifpItemAdditionTabValueDropDown", true, "ifpItemAdditionTabValueDropDownLayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		valueDdlb.setAuthorizationIncluded(true);
		valueDdlb.setComponentName("Value");
		componentList.add(valueDdlb);
		valueDdlb.setEnable(true);
		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		valueDdlb.setGtnComboboxConfig(companyTypeConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(
				GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_ITEM_ADDITION_SEARCH_DDLB_VALUE_CHANGE_ACTION);
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_VALUE));
		actionConfigList.add(customAction);
		valueDdlb.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig searchButtonConfig = creatingButtonConfig("ifpItemAdditiongtnSearch01", "Search",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_INFORMATION_LAYOUT, componentList);
		searchButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_FIELD,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_VALUE));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Search Criteria ");
		alertParamsList.add("Please enter Search Criteria");

		alertActionConfig.setActionParameterList(alertParamsList);
		validationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(alertActionConfig)));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig.addActionParameter("ifpleftResultTable");
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_FIELD,
						GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_VALUE));
		actionConfigList.add(loadDataTableActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addIfpItemAdditionDualListBoxLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		getIfpItemAdditionLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_PANEL_LAYOUT, true, componentList);
		ifpLeftResultLayout(componentList);
		ifpMoveButtons(componentList);
		ifpRightResultLayout(componentList);
	}

	private void addIfpItemAdditionDualListBoxLayoutOnView(List<GtnUIFrameworkComponentConfig> componentList) {
		getIfpItemAdditionLayout(GtnUIFrameworkLayoutType.CSS_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT_ON_VIEW,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_PANEL_LAYOUT, true, componentList);
		rightResultLayoutOnView(componentList);
	}

	private void ifpMoveButtons(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_BUTTONS_LAYOUT, true,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT);

		List<String> stylesList = new ArrayList<>();
		stylesList.add("gtnGrid-DualList-Buttons");
		layoutConfig.setComponentStyle(stylesList);
		componentList.add(layoutConfig);
		ifpMoveRight(componentList);
		ifpMoveLeft(componentList);
		ifpMoveAllRight(componentList);
		ifpMoveAllLeft(componentList);

	}

	private void ifpMoveRight(List<GtnUIFrameworkComponentConfig> componentList) {

		getIfpItemAdditionLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpItemAdditionmoverightButtonslayout",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_BUTTONS_LAYOUT, true, componentList);

		GtnUIFrameworkComponentConfig attachButtonConfig = creatingButtonConfig("ifpItemAdditionmoverightButtons",
				" > ", "ifpItemAdditionmoverightButtonslayout", componentList);
		attachButtonConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		attachButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_MOVE_RIGHT_ACTION);
		actionConfigList.add(customAction);

		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void ifpMoveLeft(List<GtnUIFrameworkComponentConfig> componentList) {
		getIfpItemAdditionLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpItemAdditionmoveLeftButtonslayout",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_BUTTONS_LAYOUT, true, componentList);
		GtnUIFrameworkComponentConfig attachButtonConfig = creatingButtonConfig("ifpItemAdditionmoveLeftButtons", " < ",
				"ifpItemAdditionmoveLeftButtonslayout", componentList);
		attachButtonConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		attachButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("ifprightResultTable");
		alertParamsList.add(GtnFrameworkCommonStringConstants.ERROR);
		alertParamsList.add("There are no items to remove");

		alertActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_MOVE_LEFT_ACTION);
		actionConfigList.add(customAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void ifpMoveAllRight(List<GtnUIFrameworkComponentConfig> componentList) {
		getIfpItemAdditionLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpItemAdditionmoveAllrightButtonslayout",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_BUTTONS_LAYOUT, true, componentList);
		GtnUIFrameworkComponentConfig attachButtonConfig = creatingButtonConfig("ifpItemAdditionmoveAllrightButtons",
				" >> ", "ifpItemAdditionmoveAllrightButtonslayout", componentList);
		attachButtonConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		attachButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_MOVEALL_RIGHT_ACTION);
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_FIELD,
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_SEARCH_VALUE));
		actionConfigList.add(customAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void ifpMoveAllLeft(List<GtnUIFrameworkComponentConfig> componentList) {

		getIfpItemAdditionLayout(GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifpItemAdditionmoveAllLeftButtonslayout",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITIONMOVE_BUTTONS_LAYOUT, true, componentList);
		GtnUIFrameworkComponentConfig attachButtonConfig = creatingButtonConfig("ifpItemAdditionmoveAllLeftButtons",
				" << ", "ifpItemAdditionmoveAllLeftButtonslayout", componentList);
		attachButtonConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_53);
		attachButtonConfig.setAuthorizationIncluded(true);

		// GtnFrameworkCFPMoveAllLeftAction
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_MOVEALL_LEFT_ACTION);
		actionConfigList.add(customAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void ifpLeftResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = getIfpItemAdditionLayout(
				GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT, "ifpleftResultLayout",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT, true, componentList);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_600);
		leftResultDataTable(componentList);
	}

	private void leftResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig leftSelectResultConfig = configProvider.getUIFrameworkComponentConfig(
				"ifpleftResultTable", true, "ifpleftResultLayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		leftSelectResultConfig.setAuthorizationIncluded(true);
		leftSelectResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		leftSelectResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		leftSelectResultConfig.setComponentStyle(tableStyleList);

		componentList.add(leftSelectResultConfig);

		GtnUIFrameworkPagedTableConfig leftSelectResults = configProvider.getPagedTableConfig(true, true,
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE + GtnWsIFamilyPlanContants.GTN_WS_IFP_SEARCH_SERVICE,
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE + GtnWsIFamilyPlanContants.GTN_WS_IFP_SEARCH_SERVICE,
				GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN, null);
		leftSelectResultConfig.setGtnPagedTableConfig(leftSelectResults);
		leftSelectResults.setEditable(false);
		leftSelectResults.setMultiSelect(true);

		leftSelectResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		leftSelectResults.setTableVisibleHeader(new String[] { GtnFrameworkCommonConstants.ITEM_NO_HEADER,
				GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkCommonConstants.ITEM_DESC_HEADER,
				GtnFrameworkCommonConstants.ITEM_STATUS_HEADER, "Form", GtnFrameworkCommonConstants.STRENGTH_IFP,
				GtnFrameworkCommonConstants.THERAPEUTIC_CLASS_VAL, GtnFrameworkCommonConstants.BRAND_IFP });
		leftSelectResults.setTableColumnMappingId(
				new Object[] { GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME,
						GtnFrameworkCommonConstants.ITEM_DESC, GtnFrameworkCommonConstants.ITEM_STATUS, "form",
						GtnFrameworkCommonConstants.STRENGTH, "therapeutic Class", GtnFrameworkCommonConstants.BRAND });
		leftSelectResults.setExtraColumn(new Object[] { GtnFrameworkCommonConstants.ITEM_MASTER_SID });
	}

	private void ifpRightResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = getIfpItemAdditionLayout(
				GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT, "ifprightResultLayout",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT, true, componentList);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_600);

		rightResultDataTable(componentList);
	}

	private void rightResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rightSelectResultConfig = configProvider.getUIFrameworkComponentConfig(
				"ifprightResultTable", true, "ifprightResultLayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		rightSelectResultConfig.setAuthorizationIncluded(true);
		rightSelectResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		rightSelectResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		rightSelectResultConfig.setComponentStyle(tableStyleList);

		componentList.add(rightSelectResultConfig);

		GtnUIFrameworkPagedTableConfig rightSelectResults = configProvider.getPagedTableConfig(true, true,
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_RIGHT_TABLE_SEARCH_SERVICE,
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_RIGHT_TABLE_SEARCH_SERVICE,
				GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN, null);
		rightSelectResultConfig.setGtnPagedTableConfig(rightSelectResults);
		rightSelectResults.setEditable(false);
		rightSelectResults.setFilterBar(true);
		rightSelectResults.setSelectable(true);
		rightSelectResults.setMultiSelect(true);

		rightSelectResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		rightSelectResults.setTableVisibleHeader(new String[] { GtnFrameworkCommonConstants.ITEM_NO_HEADER,
				GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkCommonConstants.ITEM_DESC_HEADER,
				GtnFrameworkCommonConstants.ITEM_STATUS_HEADER, "Form", GtnFrameworkCommonConstants.STRENGTH_IFP,
				GtnFrameworkCommonConstants.THERAPEUTIC_CLASS_VAL, GtnFrameworkCommonConstants.BRAND_IFP });
		rightSelectResults.setTableColumnMappingId(
				new Object[] { GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME,
						GtnFrameworkCommonConstants.ITEM_DESC, GtnFrameworkCommonConstants.ITEM_STATUS, "form",
						GtnFrameworkCommonConstants.STRENGTH, "therapeuticClass", GtnFrameworkCommonConstants.BRAND });
		rightSelectResults.setExtraColumn(new Object[] { GtnFrameworkCommonConstants.ITEM_MASTER_SID });
	}

	private void rightResultLayoutOnView(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = getIfpItemAdditionLayout(
				GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT, GtnUIFrameworkComponentType.LAYOUT,
				"ifprightResultLayoutOnView",
				GtnFrameworkCommonConstants.IFP_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT_ON_VIEW, true, componentList);
		layoutConfig.setComponentWidth("1586px");
		rightResultDataTableOnView(componentList);
	}

	private void rightResultDataTableOnView(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig resultConfig = configProvider.getUIFrameworkComponentConfig(
				"ifprightResultTableOnView", true, "ifprightResultLayoutOnView",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		resultConfig.setAuthorizationIncluded(true);
		resultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		resultConfig.setComponentWidth("1586px");
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		resultConfig.setComponentStyle(tableStyleList);

		componentList.add(resultConfig);

		GtnUIFrameworkPagedTableConfig results = configProvider.getPagedTableConfig(true, true,
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_RIGHT_TABLE_SEARCH_SERVICE,
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANY_ADDITION_RIGHT_TABLE_SEARCH_SERVICE,
				GtnFrameworkCommonConstants.ITEM_FAMILY_PLAN, null);
		resultConfig.setGtnPagedTableConfig(results);
		results.setEditable(false);
		results.setFilterBar(true);
		results.setSelectable(true);
		results.setMultiSelect(true);

		results.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		results.setTableVisibleHeader(new String[] { GtnFrameworkCommonConstants.ITEM_NO_HEADER,
				GtnFrameworkCommonConstants.ITEM_NAME_HEADER, GtnFrameworkCommonConstants.ITEM_DESC_HEADER,
				GtnFrameworkCommonConstants.ITEM_STATUS_HEADER, "Form", GtnFrameworkCommonConstants.STRENGTH_IFP,
				GtnFrameworkCommonConstants.THERAPEUTIC_CLASS_VAL, GtnFrameworkCommonConstants.BRAND_IFP });
		results.setTableColumnMappingId(
				new Object[] { GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonConstants.ITEM_NAME,
						GtnFrameworkCommonConstants.ITEM_DESC, GtnFrameworkCommonConstants.ITEM_STATUS, "form",
						GtnFrameworkCommonConstants.STRENGTH, "therapeuticClass", GtnFrameworkCommonConstants.BRAND });
		results.setExtraColumn(new Object[] { GtnFrameworkCommonConstants.ITEM_MASTER_SID });
	}

	private GtnUIFrameworkComponentConfig creatingButtonConfig(String componentId, String componentName,
			String parentComponentId, List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(componentId,
				true, parentComponentId, GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName(componentName);
		componentList.add(searchButtonConfig);
		return searchButtonConfig;

	}

	private GtnUIFrameworkComponentConfig getIfpItemAdditionLayout(GtnUIFrameworkLayoutType layoutType,
			GtnUIFrameworkComponentType componentType, String componentId, String parentComponentId, boolean hasParent,
			List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(layoutType);
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkComponentConfig(componentId, hasParent,
				parentComponentId, componentType);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		return gtnLayout;

	}

}
