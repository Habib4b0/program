package com.stpl.gtn.gtn2o.ui.module.ifp.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.module.ifp.action.GtnFramworkItemCheckAllAction;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpClassContants;
import com.stpl.gtn.gtn2o.ui.module.ifp.constants.GtnFrameworkIfpStringContants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.itemfamilyplan.constants.GtnWsIFamilyPlanContants;

public class GtnFrameworkIfpItemsTabConfig {

	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public void addItemsTab(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getVerticalLayoutConfig(
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB, false, GtnFrameworkCommonConstants.IFP_ITEMS_TAB);
		layoutConfig.setTabComponent(true);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);

		ifpItemsMassUpdatePanel(componentList);
		ifpItemsAddRecordPanel(componentList);
		ifpItemsResultPanel(componentList);
		ifpItemsExcelButtonComponent(componentList);
	}

	private void ifpItemsMassUpdatePanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getCssLayoutConfig(
				GtnFrameworkCommonConstants.IFP_ITEMSMASS_UPDATE_PANEL_LAYOUT, true,
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		layoutConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12));
		componentList.add(layoutConfig);
		ifpItemsTabInformationFields(componentList);

	}

	private void ifpItemsAddRecordPanel(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getUIFrameworkLayoutComponentConfig(
				"ifpItemsRecordPanel", true, GtnFrameworkCommonConstants.IFP_ITEMS_TAB,
				GtnUIFrameworkLayoutType.COL1_LAYOUT, GtnUIFrameworkComponentType.LAYOUT);
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_40);
		layoutConfig.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_1));
		componentList.add(layoutConfig);
		ifpItemsReordLayout(componentList);

	}

	private void ifpItemsReordLayout(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpItemsRecordTypelayout",
				true, "ifpItemsRecordPanel");
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_10,
				GtnFrameworkCssConstants.PADDING_LEFT10, GtnFrameworkCssConstants.IYGDSAYGKHYA));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig recordType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RECORD_TYPE, true, "ifpItemsRecordTypelayout",
				GtnUIFrameworkComponentType.OPTIONGROUP);
		recordType.setAuthorizationIncluded(true);
		recordType.setComponentName("Record:");
		recordType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		componentList.add(recordType);

		GtnUIFrameworkOptionGroupConfig reordTypeConfig = new GtnUIFrameworkOptionGroupConfig();
		reordTypeConfig.setValuesFromService(false);
		reordTypeConfig.setItemValues(Arrays.asList(GtnFrameworkCommonStringConstants.CURRENT,
				GtnFrameworkCommonStringConstants.HISTORY, GtnFrameworkCommonStringConstants.FUTURE));
		reordTypeConfig.setIsMultiSelect(true);
		reordTypeConfig.setDefaultSelection(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		recordType.setGtnUIFrameworkOptionGroupConfig(reordTypeConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_COMPANIES_RECORD_CHANGE_ACTION);
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RECORD_TYPE));
		actionConfigList.add(customAction);
		recordType.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void ifpItemsTabInformationFields(List<GtnUIFrameworkComponentConfig> componentList) {
		addIfpItemsMassCheck(componentList);
		addIfpItemsMassFields(componentList);
		addIfpItemsMassDateField(componentList);
		addIfpItemsMassDropDown(componentList);
		addIfpItemsPopulateButtons(componentList);
	}

	private void addIfpItemsMassCheck(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("ifpItemsmassupdatelayout",
				true, GtnFrameworkCommonConstants.IFP_ITEMSMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemQualifierName = configProvider.getUIFrameworkComponentConfig("ifpMassCheck",
				true, "ifpItemsmassupdatelayout", GtnUIFrameworkComponentType.OPTIONGROUP);
		itemQualifierName.setAuthorizationIncluded(true);
		itemQualifierName.setComponentName("Mass Update");
		itemQualifierName.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE));
		componentList.add(itemQualifierName);

		GtnUIFrameworkOptionGroupConfig ifpItemQualifierNameConfig = new GtnUIFrameworkOptionGroupConfig();
		ifpItemQualifierNameConfig.setValuesFromService(false);
		ifpItemQualifierNameConfig.setItemValues(
				Arrays.asList(GtnFrameworkCommonStringConstants.ENABLE, GtnFrameworkCommonStringConstants.DISABLE));
		ifpItemQualifierNameConfig.setDefaultSelection(GtnFrameworkCommonStringConstants.DISABLE);
		itemQualifierName.setGtnUIFrameworkOptionGroupConfig(ifpItemQualifierNameConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.MASSFIELD_ENABLEDISABLE_ACTION);
		customAction.setFieldValues(
				Arrays.asList(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_FIELD, "ifpItemsTabPopulateButton",
						"ifpItemsTabPopulateAllButton", GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_DATE_FEILD,
						GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_DROP_DOWN));
		actionConfigList.add(customAction);
		itemQualifierName.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addIfpItemsMassFields(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_FEILDLAYOUT, true,
				GtnFrameworkCommonConstants.IFP_ITEMSMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10));
		gtnLayout.setSpacing(true);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig itemType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_FIELD, true,
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_FEILDLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		itemType.setAuthorizationIncluded(true);
		itemType.setComponentName(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		itemType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		itemType.setEnable(false);
		componentList.add(itemType);

		GtnUIFrameworkComboBoxConfig ifpItemTypeConfig = new GtnUIFrameworkComboBoxConfig();
		ifpItemTypeConfig.setItemValues(Arrays.asList("IFP Start Date", "IFP End Date", "IFP Status"));
		itemType.setGtnComboboxConfig(ifpItemTypeConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(
				GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_COMPANIES_MASSFIELD_VALUE_CHANGE_ACTION);
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_DATE_FEILD,
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_DROP_DOWN));
		actionConfigList.add(customAction);
		itemType.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addIfpItemsMassDateField(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig ifpItemType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_DATE_FEILD, true,
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_FEILDLAYOUT, GtnUIFrameworkComponentType.DATEFIELD);
		ifpItemType.setAuthorizationIncluded(true);
		ifpItemType.setEnable(false);
		ifpItemType.setComponentName(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		ifpItemType.setVisible(false);
		componentList.add(ifpItemType);

	}

	private void addIfpItemsMassDropDown(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig ifpItemType = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_DROP_DOWN, true,
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_FEILDLAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
		ifpItemType.setAuthorizationIncluded(true);
		ifpItemType.setComponentName(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		componentList.add(ifpItemType);
		ifpItemType.setVisible(false);
		ifpItemType.setEnable(false);
		ifpItemType.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		GtnUIFrameworkComboBoxConfig ifpItemTypeConfig = new GtnUIFrameworkComboBoxConfig();
		ifpItemTypeConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
				+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		ifpItemTypeConfig.setComboBoxType("STATUS");

		ifpItemType.setGtnComboboxConfig(ifpItemTypeConfig);

	}

	private void addIfpItemsPopulateButtons(List<GtnUIFrameworkComponentConfig> componentList) {
		massUpdatePopulateButton(componentList);
		massUpdatePopulateAllButton(componentList);
	}

	private void massUpdatePopulateButton(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"ifpItemsTabPopulateButtonlayout", true, GtnFrameworkCommonConstants.IFP_ITEMSMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		componentList.add(gtnLayout);
		GtnUIFrameworkComponentConfig attachButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"ifpItemsTabPopulateButton", true, "ifpItemsTabPopulateButtonlayout",
				GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setAuthorizationIncluded(true);
		attachButtonConfig.setComponentName("Populate");
		attachButtonConfig.setAddToParent(true);
		attachButtonConfig.setEnable(false);
		componentList.add(attachButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_COMPANIES_TAB_POPULATE_ACTION);
		customAction.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.IFP_ITEMS_TAB_MASS_FIELD,
				"CFPCompanyAdditionSearchValue", GtnFrameworkCommonConstants.IFP_ITEMS_TAB_RECORD_TYPE));
		customAction.addActionParameter("populate");
		actionConfigList.add(customAction);
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void massUpdatePopulateAllButton(List<GtnUIFrameworkComponentConfig> componentList) {

		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				"ifpItemsTabPopulateAllButtonLayout", true,
				GtnFrameworkCommonConstants.IFP_ITEMSMASS_UPDATE_PANEL_LAYOUT);
		gtnLayout.setComponentStyle(Arrays.asList(GtnFrameworkCssConstants.BUTTON_CUSTOM_STYLE_1));
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig attachButtonConfig = new GtnUIFrameworkComponentConfig();
		attachButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		attachButtonConfig.setAuthorizationIncluded(true);
		attachButtonConfig.setComponentId("ifpItemsTabPopulateAllButton");
		attachButtonConfig.setComponentName("Populate All");
		attachButtonConfig.setAddToParent(true);
		attachButtonConfig.setEnable(false);
		attachButtonConfig.setParentComponentId("ifpItemsTabPopulateAllButtonLayout");
		componentList.add(attachButtonConfig);
		// GtnFrameworkCFPIfpItemsTabPopulateAction
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customAction.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_COMPANIES_TAB_POPULATE_ACTION);
		customAction.setFieldValues(Arrays.asList("CFPCompanyAdditionSearchField", "CFPCompanyAdditionSearchValue",
				"cfpCompaniesTabRecordType"));
		actionConfigList.add(customAction);
		customAction.addActionParameter("populateAll");
		attachButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void ifpItemsResultPanel(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig panel = configProvider.getPanelConfig("ifpItemsTabResultPanel", true,
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB);
		panel.setComponentName("Results");
		panel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(panel);
		ifpItemsResultLayout(componentList);
	}

	private void ifpItemsResultLayout(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig layoutConfig = configProvider.getHorizontalLayoutConfig("ifpItemsTabResultLayout",
				true, "ifpItemsTabResultPanel");
		layoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(layoutConfig);

		ifpItemsTabResultDataTable(componentList);
	}

	private void ifpItemsTabResultDataTable(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig attachResultConfig = configProvider.getUIFrameworkComponentConfig(
				"ifpItemsTabResultDataTable", true, "ifpItemsTabResultLayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		attachResultConfig.setAuthorizationIncluded(true);
		attachResultConfig.setComponentName("Results");
		attachResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		attachResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		attachResultConfig.setComponentStyle(tableStyleList);

		componentList.add(attachResultConfig);

		GtnUIFrameworkPagedTableConfig attachResults = configProvider.getPagedTableConfig(true, true,
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE,
				GtnWsIFamilyPlanContants.GTN_WS_IFP_SERVICE
						+ GtnWsIFamilyPlanContants.GTN_WS_IFP_COMPANIES_TAB_RESULT_TABLE_SEARCH_SERVICE,
				"ItemFamilyPlan", null);
		attachResultConfig.setGtnPagedTableConfig(attachResults);
		attachResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_BOOLEAN,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVAUTIL_DATE,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING,
				GtnFrameworkCommonConstants.JAVAUTIL_DATE, GtnFrameworkCommonConstants.JAVALANG_STRING });
		attachResults.setTableVisibleHeader(GtnFrameworkIfpStringContants.IFP_VISIBLE_HEADER
				.toArray(new String[GtnFrameworkIfpStringContants.IFP_VISIBLE_HEADER.size()]));
		attachResults.setTableColumnMappingId(GtnFrameworkIfpStringContants.IFP_VISIBLE_COLUMN.toArray());
		attachResults.setExtraColumn(new Object[] { "imtdCfpDetailsSid", "itemFamilyPlanStatusValue" });
		attachResults.setExtraColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVALANG_INTEGER,
				GtnFrameworkCommonConstants.JAVALANG_STRING });
		attachResults.setColumnCheckBoxId(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		attachResults.setEditable(true);
		attachResults.setInvisibleFilterPropertyIds(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
		attachResults.setEditableColumnList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID,
				"itemFamilyPlanStatus", "itemFamilyPlanStartDate", "itemFamilyPlanEndDate"));

		attachResults.setEditableField(createTableFieldFactoryComponents(attachResults.getEditableColumnList()));
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig checkAllAction = new GtnUIFrameWorkActionConfig();
		checkAllAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		checkAllAction.addActionParameter(GtnFramworkItemCheckAllAction.class.getName());
		actionConfigList.add(checkAllAction);
		attachResults.setColumnCheckActionConfigList(actionConfigList);
	}

	List<GtnUIFrameworkComponentConfig> createTableFieldFactoryComponents(List<String> propertyIds) {
		List<GtnUIFrameworkComponentConfig> editableFields = new ArrayList<>();
		for (String propertyId : propertyIds) {
			GtnUIFrameworkComponentConfig fieldConfig = new GtnUIFrameworkComponentConfig();
			GtnUIFrameworkComponentType gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.COMBOBOX;
			if (GtnFrameworkIfpStringContants.getDateFieldPropertiesList().contains(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.DATEFIELD;
			} else if ("checkRecordId".equals(propertyId)) {
				gtnUIFrameworkComponentType = GtnUIFrameworkComponentType.CHECKBOX;
			} else {
				GtnUIFrameworkComboBoxConfig ifpItemFamilyPlanStatusConfig = new GtnUIFrameworkComboBoxConfig();
				ifpItemFamilyPlanStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				ifpItemFamilyPlanStatusConfig.setComboBoxType("STATUS");
				fieldConfig.setGtnComboboxConfig(ifpItemFamilyPlanStatusConfig);
			}
			fieldConfig.setComponentType(gtnUIFrameworkComponentType);
			fieldConfig.setEnable(true);
			List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
			customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			customAction.addActionParameter(GtnFrameworkIfpClassContants.GTNFRAMEWORK_IFP_FIELD_FACTORY);
			actionConfigList.add(customAction);
			fieldConfig.setGtnUIFrameWorkValueChangeActionConfigList(actionConfigList);
			editableFields.add(fieldConfig);
		}

		return editableFields;
	}

	/**
	 *
	 * @param componentList
	 */
	private void ifpItemsExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig("gtnExcelButtonlayout", true,
				GtnFrameworkCommonConstants.IFP_ITEMS_TAB);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig excelButtonConfig = configProvider.getUIFrameworkComponentConfig(
				"ifpItemsExcelExport", true, "gtnExcelButtonlayout", GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = configProvider.getExcelBtnconfig("IFP Items",
				true, "ifpItemsTabResultDataTable", false);
		gtnUIFrameworkExcelButtonConfig.setHelperTableMapedPropertyIdList(Arrays.asList("itemFamilyPlanStatus"));
		gtnUIFrameworkExcelButtonConfig
				.setExcludeColumnsList(Arrays.asList(GtnFrameworkCommonConstants.CHECK_RECORD_ID));
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));

	}

}
