package com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.validation.GtnUIFrameworkRsItemAdditionValidationAction;
import com.stpl.gtn.gtn2o.ui.module.rebatescheduleconfig.util.GtnFrameworkRSConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.complianceanddeductionrules.constants.GtnWsCDRContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkRSItemAdditionConfig {
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addItemAdditionTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.ITEM_ADDITION_TAB, false, null);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setTabComponent(true);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig cssGtnLayout = configProvider.getCssLayoutConfig("addressTabLayout", true,
				GtnFrameworkCommonConstants.ITEM_ADDITION_TAB);
		cssGtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(cssGtnLayout);
		itemAdditionFields(componentList);
	}

	private void itemAdditionFields(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_PANEL_LAYOUT, true,
				GtnFrameworkCommonConstants.ITEM_ADDITION_TAB);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		componentList.add(layoutConfig);

		addSearchLayout(componentList);
		addDualListBoxLayout(componentList);
	}

	private void addSearchLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT, true,
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_PANEL_LAYOUT);
		layoutConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10));

		layoutConfig.getGtnLayoutConfig().setSpacing(true);
		componentList.add(layoutConfig);
		identifierInformationFields(componentList);
	}

	private void identifierInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addOptionGroup(componentList);
		addSearchFields(componentList);
		addSearchValue(componentList);
		addDateField(componentList);
		addCategoryDropDown(componentList);
		addTypeDropDown(componentList);
		addStatusDropDown(componentList);
		addSearchButtonComponent(componentList);
	}

	private void addOptionGroup(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("RSItemmassupdatelayout",
				true, GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = configProvider.getUIFrameworkComponentConfig("RSMassCheck",
				true, "RSItemmassupdatelayout", GtnUIFrameworkComponentType.OPTIONGROUP);
		companyQualifierName.setAuthorizationIncluded(true);
		companyQualifierName.setComponentName(" ");

		componentList.add(companyQualifierName);

		GtnUIFrameworkOptionGroupConfig companyQualifierNameConfig = new GtnUIFrameworkOptionGroupConfig();
		companyQualifierNameConfig.setValuesFromService(false);
		companyQualifierNameConfig.setItemValues(Arrays.asList("Item", "IFP"));
		companyQualifierNameConfig.setEnable(false);
		companyQualifierName.setGtnUIFrameworkOptionGroupConfig(companyQualifierNameConfig);
		companyQualifierName
				.setComponentStyle(Arrays.asList(new String[] { GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE }));
	}

	private void addSearchFields(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"RSItemAdditionSearchFieldlayout", true, GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyQualifierName = configProvider.getUIFrameworkComponentConfig(
				"RSItemAdditionSearchField", true, "RSItemAdditionSearchFieldlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyQualifierName.setAuthorizationIncluded(true);
		companyQualifierName.setComponentName("Search Field");

		componentList.add(companyQualifierName);

		GtnUIFrameworkComboBoxConfig companyQualifierNameConfig = new GtnUIFrameworkComboBoxConfig();
		companyQualifierNameConfig.setItemValues(Arrays.asList(GtnFrameworkRSConstants.IFP_ID,
				GtnFrameworkCommonConstants.IFP_NO, GtnFrameworkCommonConstants.LABEL_IFP_NAME, "IFP STATUS",
				"IFP TYPE", "IFP START DATE", "IFP END DATE", "IFP CATEGORY"));
		companyQualifierName.setGtnComboboxConfig(companyQualifierNameConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customAction.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkRSConstants.FIELD_VALUE_CHANGE }));

		customAction.setFieldValues(Arrays.asList("rebateSetupTabMassDateFeild"));
		actionConfigList.add(customAction);
		companyQualifierName.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addSearchValue(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"RSItemAdditionSearchValueLayout", true, GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdentifierConfig = configProvider.getUIFrameworkComponentConfig(
				"RSItemAdditionSearchValueText", true, "RSItemAdditionSearchValueLayout",
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdentifierConfig.setAuthorizationIncluded(true);
		companyIdentifierConfig.setComponentName(GtnFrameworkCommonConstants.VALUE);
		GtnUIFrameworkTextBoxConfig textboxConfig = configProvider.getTextBoxConfig(true, true, true);
		companyIdentifierConfig.setGtnTextBoxConfig(textboxConfig);

		componentList.add(companyIdentifierConfig);

	}

	private void addDateField(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"RSItemAdditionSearchValueDatelayout", true,
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"RSItemAdditionSearchValueDate", true, "RSItemAdditionSearchValueDatelayout",
				GtnUIFrameworkComponentType.DATEFIELD);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName(GtnFrameworkCommonConstants.VALUE);
		companyType.setVisible(false);
		componentList.add(companyType);

	}

	private void addTypeDropDown(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"RSItemAdditionSearchValueTypeDropDownlayout", true,
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1, GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyType = configProvider.getUIFrameworkComponentConfig(
				"RSItemAdditionSearchValueTypeDropDown", true, "RSItemAdditionSearchValueTypeDropDownlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		companyType.setAuthorizationIncluded(true);
		companyType.setComponentName(GtnFrameworkCommonConstants.VALUE);
		componentList.add(companyType);
		companyType.setVisible(false);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("IFP_TYPE",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		companyType.setGtnComboboxConfig(companyTypeConfig);

	}

	private void addCategoryDropDown(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"RSItemAdditionSearchValueCategoryDropDownlayout", true,
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rsCategoryConfig = configProvider.getUIFrameworkComponentConfig(
				"RSItemAdditionSearchValueCategoryDropDown", true, "RSItemAdditionSearchValueCategoryDropDownlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		rsCategoryConfig.setAuthorizationIncluded(true);
		rsCategoryConfig.setComponentName(GtnFrameworkCommonConstants.VALUE);
		componentList.add(rsCategoryConfig);
		rsCategoryConfig.setVisible(false);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("IFP_CATEGORY",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		rsCategoryConfig.setGtnComboboxConfig(companyTypeConfig);

	}

	private void addStatusDropDown(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"RSItemAdditionSearchValueStatusDropDownlayout", true,
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_40,
				GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rsStatusConfigType = configProvider.getUIFrameworkComponentConfig(
				"RSItemAdditionSearchValueStatusDropDown", true, "RSItemAdditionSearchValueStatusDropDownlayout",
				GtnUIFrameworkComponentType.COMBOBOX);
		rsStatusConfigType.setAuthorizationIncluded(true);
		rsStatusConfigType.setComponentName(GtnFrameworkCommonConstants.VALUE);
		componentList.add(rsStatusConfigType);
		rsStatusConfigType.setVisible(false);

		GtnUIFrameworkComboBoxConfig companyTypeConfig = configProvider.getComboBoxConfig("STATUS",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);

		rsStatusConfigType.setGtnComboboxConfig(companyTypeConfig);

	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"rsItemAdditionSearchButtonlayout", true, GtnFrameworkRSConstants.RS_ITEM_ADDITION_INFORMATION_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_30));
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"RSItemAdditiongtnSearch01", true, "rsItemAdditionSearchButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName("Search");
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig searchAlertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		List<Object> alertParameterList = new ArrayList<>();
		alertParameterList.add(GtnUIFrameworkRsItemAdditionValidationAction.class.getName());
		alertParameterList.add(GtnFrameworkRSConstants.R_SLEFT_RESULT_TABLE);
		alertParameterList.add("");
		alertParameterList.add(GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_FIELD);		
		List<String> fieldIds=Arrays.asList(GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_VALUE_TEXT,GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_VALUE_STATUS_DROP_DOWN,GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_VALUE_TYPE_DROP_DOWN,
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_VALUE_DATE,GtnFrameworkRSConstants.RS_ITEM_ADDITION_SEARCH_VALUE_CATEGORY_DROP_DOWN);
		alertParameterList.add(fieldIds);
		searchAlertActionConfig.setActionParameterList(alertParameterList);
		
		actionConfigList.add(searchAlertActionConfig);
		
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION,GtnFrameworkRSConstants.R_SLEFT_RESULT_TABLE);

		loadDataTableActionConfig.setFieldValues(Arrays.asList("RSItemAdditionSearchField","RSItemAdditionSearchValueText"));
		actionConfigList.add(loadDataTableActionConfig);
		
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDualListBoxLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT, true,
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_PANEL_LAYOUT);
		componentList.add(layoutConfig);
		leftResultLayout(componentList);
		moveButtons(componentList);
		rightResultLayout(componentList);
	}

	private void moveButtons(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rsItemAdditionMoveLayoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkRSConstants.RS_ITEM_ADDITIONMOVE_BUTTONS_LAYOUT, true,
				GtnFrameworkRSConstants.RS_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT);
		rsItemAdditionMoveLayoutConfig.setSpacing(true);
		rsItemAdditionMoveLayoutConfig.setMargin(true);

		List<String> styles = new ArrayList<>();
		styles.add("gtnGrid-DualList-Buttons");
		rsItemAdditionMoveLayoutConfig.setComponentStyle(styles);
		rsItemAdditionMoveLayoutConfig.getGtnLayoutConfig().setMargin(true);
		rsItemAdditionMoveLayoutConfig.getGtnLayoutConfig().setSpacing(true);
		componentList.add(rsItemAdditionMoveLayoutConfig);
		moveRight(componentList);
		moveLeft(componentList);

	}

	private void moveRight(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"RSItemAdditionmoverightButtonLayout", true,
				GtnFrameworkRSConstants.RS_ITEM_ADDITIONMOVE_BUTTONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rsItemAdditionMoveRightButtonConfig = configProvider
				.getUIFrameworkComponentConfig("RSItemAdditionmoverightButtons", true,
						"RSItemAdditionmoverightButtonLayout", GtnUIFrameworkComponentType.BUTTON);
		rsItemAdditionMoveRightButtonConfig.setAuthorizationIncluded(true);
		rsItemAdditionMoveRightButtonConfig.setComponentName(" > ");
		rsItemAdditionMoveRightButtonConfig.setComponentWidth("53px");
		componentList.add(rsItemAdditionMoveRightButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnUIFrameworkRsItemAdditionValidationAction.class.getName());
		alertParamsList.add(GtnFrameworkRSConstants.R_SLEFT_RESULT_TABLE);
		alertParamsList.add("Please select the IFP to Add.");

		alertActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(alertActionConfig);

		GtnUIFrameWorkActionConfig customAction = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		customAction.setActionParameterList(Arrays.asList(new Object[] { GtnFrameworkRSConstants.ADD_DATA_TABLE }));
		actionConfigList.add(customAction);

		rsItemAdditionMoveRightButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void moveLeft(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"RSItemAdditionmoveLeftButtonsLayout", true,
				GtnFrameworkRSConstants.RS_ITEM_ADDITIONMOVE_BUTTONS_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig rsItemAdditionMoveLeftButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"RSItemAdditionmoveLeftButtons", true, "RSItemAdditionmoveLeftButtonsLayout",
				GtnUIFrameworkComponentType.BUTTON);
		rsItemAdditionMoveLeftButtonConfig.setAuthorizationIncluded(true);
		rsItemAdditionMoveLeftButtonConfig.setComponentName(" < ");
		rsItemAdditionMoveLeftButtonConfig.setComponentWidth("53px");
		componentList.add(rsItemAdditionMoveLeftButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig alertActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnUIFrameworkRsItemAdditionValidationAction.class.getName());
		alertParamsList.add(GtnFrameworkRSConstants.R_SRIGHT_RESULT_TABLE);
		alertParamsList.add("Please select a record to remove.");

		alertActionConfig.setActionParameterList(alertParamsList);
		actionConfigList.add(alertActionConfig);
		GtnUIFrameWorkActionConfig setDefaultActionConfig = configProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		List<Object> defaultFieldValueList = new ArrayList<>();
		List<String> defaultComponentIdListTemp = new ArrayList<>();
		List<Object> defaultComponentValueListTemp = new ArrayList<>();
		defaultComponentIdListTemp.add(GtnFrameworkRSConstants.R_SRIGHT_RESULT_TABLE);
		defaultComponentValueListTemp.add(null);
		defaultFieldValueList.add(defaultComponentIdListTemp);
		defaultFieldValueList.add(defaultComponentValueListTemp);
		setDefaultActionConfig.setActionParameterList(defaultFieldValueList);
		actionConfigList.add(setDefaultActionConfig);

		rsItemAdditionMoveLeftButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void leftResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig("RSleftResultLayout",
				true, GtnFrameworkRSConstants.RS_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_600);
		componentList.add(layoutConfig);

		leftResultDataTable(componentList);
	}

	private void leftResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rsItemAdditionleftResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.R_SLEFT_RESULT_TABLE, true, "RSleftResultLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		rsItemAdditionleftResultConfig.setAuthorizationIncluded(true);
		rsItemAdditionleftResultConfig.setComponentHight("400px");
		rsItemAdditionleftResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		rsItemAdditionleftResultConfig.setComponentStyle(tableStyle);

		componentList.add(rsItemAdditionleftResultConfig);

		GtnUIFrameworkPagedTableConfig rsItemAdditionleftResults = configProvider.getPagedTableConfig(true, true,
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_SEARCH_SERVICE,
				"/" + GtnWsCDRContants.PS_SERVICE + "/" + GtnWsCDRContants.PS_SEARCH_SERVICE,
				"priceScheduleItemAddition", "priceScheduleItemAddition");
		rsItemAdditionleftResultConfig.setGtnPagedTableConfig(rsItemAdditionleftResults);
		rsItemAdditionleftResults.setEditable(false);
		rsItemAdditionleftResults.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class,
				String.class, Date.class, String.class, String.class });
		rsItemAdditionleftResults.setTableVisibleHeader(
				new String[] { "IFP ID", "IFP No", "IFP Name", "IFP Status", "End Date", "IFP Type", "IFP Category" });
		rsItemAdditionleftResults.setTableColumnMappingId(
				new Object[] { "ifpId", "ifpNo", "ifpName", "ifpStatus", "ifpEndDate", "ifpType", "ifpCategory" });
		rsItemAdditionleftResults.setExtraColumn(new Object[] { "systemId" });
		rsItemAdditionleftResults.setExtraColumnDataType(new Class<?>[] { String.class });
		rsItemAdditionleftResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.PRICE_SCHEDULE);
	}

	private void rightResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig("RSrightResultLayout",
				true, GtnFrameworkRSConstants.RS_ITEM_ADDITION_DUAL_LIST_BOX_LAYOUT);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PIXEL_600);
		componentList.add(layoutConfig);

		rightResultDataTable(componentList);
	}

	private void rightResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig rsItemAdditionRightResultConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkRSConstants.R_SRIGHT_RESULT_TABLE, true, "RSrightResultLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		rsItemAdditionRightResultConfig.setAuthorizationIncluded(true);
		rsItemAdditionRightResultConfig.setComponentHight("400px");
		rsItemAdditionRightResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		rsItemAdditionRightResultConfig.setComponentStyle(tableStyle);

		componentList.add(rsItemAdditionRightResultConfig);

		GtnUIFrameworkPagedTableConfig rsItemAdditionRightResultResults = configProvider.getPagedTableConfig(true, true,
				"/RS/companyAdditionRightTableSearch", "/RS/companyAdditionRightTableSearch", "companyFamilyPlan", "");
		rsItemAdditionRightResultConfig.setGtnPagedTableConfig(rsItemAdditionRightResultResults);
		rsItemAdditionRightResultResults.setEditable(false);
		rsItemAdditionRightResultResults.setTableColumnDataType(new Class<?>[] { String.class, String.class,
				String.class, String.class, Date.class, String.class, String.class });
		rsItemAdditionRightResultResults.setTableVisibleHeader(
				new String[] { "IFP ID", "IFP No", "IFP Name", "IFP Status", "End Date", "IFP Type", "IFP Category" });
		rsItemAdditionRightResultResults.setTableColumnMappingId(
				new Object[] { "ifpId", "ifpNo", "ifpName", "ifpStatus", "ifpEndDate", "ifpType", "ifpCategory" });
		rsItemAdditionRightResultResults.setExtraColumn(new Object[] { "companyMasterSid" });
		rsItemAdditionRightResultResults.setExtraColumnDataType(new Class<?>[] { String.class });
	}
}
