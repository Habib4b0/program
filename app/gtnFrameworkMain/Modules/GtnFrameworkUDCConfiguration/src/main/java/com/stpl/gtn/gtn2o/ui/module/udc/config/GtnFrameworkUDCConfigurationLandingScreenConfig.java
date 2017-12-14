package com.stpl.gtn.gtn2o.ui.module.udc.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.udc.action.GtnFrameworkUdcAddAction;
import com.stpl.gtn.gtn2o.ui.module.udc.action.GtnFrameworkUdcDeleteAction;
import com.stpl.gtn.gtn2o.ui.module.udc.action.GtnFrameworkUdcSearchAction;
import com.stpl.gtn.gtn2o.ui.module.udc.constants.GtnFrameworkUdcStringConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkUDCConfigurationLandingScreenConfig {

	

	public GtnUIFrameworkViewConfig getUDCView() {
		GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
		GtnUIFrameworkViewConfig udcConfigurationView = componentConfig.getViewConfig("Udc View", "UDC001", true);
		addComponentList(udcConfigurationView, componentConfig);
		return udcConfigurationView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, GtnFrameworkComponentConfigProvider componentConfig) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addUdcConfigurationMainLayout(componentList, componentConfig);
	}

	private void addUdcConfigurationMainLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addCategoryPanel(componentList, componentConfig);
		addResultsPanel(componentList, componentConfig);
		addExcelButtonLayout(componentList, componentConfig);
	}


	private void addCategoryPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig categoryPanel = componentConfig.getPanelConfig("categoryPanel", false, null);
		categoryPanel.setComponentName("Category Options");
		categoryPanel.setAuthorizationIncluded(true);
		componentList.add(categoryPanel);

		addFieldLayout(componentList, componentConfig);
	}


	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig udcCategoryLayout = componentConfig.getGtnCssLayoutConfig(
				GtnFrameworkCommonConstants.UDC_CATEGORYLAYOUT, true, "categoryPanel",
				GtnUIFrameworkLayoutType.COL3_LAYOUT);
		udcCategoryLayout.setVisible(true);
		componentList.add(udcCategoryLayout);
		addFieldComponent(componentList, componentConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addCategory(componentList, componentConfig);
		addBrandFieldComponent(componentList, componentConfig);
		addValue(componentList, componentConfig);
		addADDButton(componentList, componentConfig);
		addBrandAddButton(componentList, componentConfig);
		addDELETEButton(componentList, componentConfig);
	}

	private void addCategory(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig categoryLayout = componentConfig.getHorizontalLayoutConfig("categoryLayout", true,
				GtnFrameworkCommonConstants.UDC_CATEGORYLAYOUT);
		componentList.add(categoryLayout);

		GtnUIFrameworkComponentConfig udcCategory = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.UDC_CATEGORY, true, categoryLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		udcCategory.setAuthorizationIncluded(true);
		udcCategory.setComponentName("Category");

		GtnUIFrameworkValidationConfig categoryValidationConfig = new GtnUIFrameworkValidationConfig();
		categoryValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_NULL));
		udcCategory.setGtnUIFrameworkValidationConfig(categoryValidationConfig);

		GtnUIFrameworkComboBoxConfig categoryConfig = componentConfig.getComboBoxConfig("CategoryName",
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		categoryConfig.setItemValues(Arrays.asList("BRAND", "CategoryName","ALL"));
		udcCategory.setGtnComboboxConfig(categoryConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionConfig.addActionParameter(GtnFrameworkUdcSearchAction.class.getName());
		actionConfigList.add(customActionConfig);

		udcCategory.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(udcCategory);

	}

	private void addBrandFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig brandLayout = componentConfig
				.getGtnCssLayoutConfig(GtnFrameworkCommonConstants.UDC_CATEGORY_BRANDLAYOUT, true,
						GtnFrameworkCommonConstants.UDC_CATEGORYLAYOUT,
						GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		brandLayout.setVisible(false);
		componentList.add(brandLayout);
		addBrandId(componentList, componentConfig);
		addBrandName(componentList, componentConfig);
		addDisplayBrand(componentList, componentConfig);
	}

	private void addBrandId(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig brandIdLayout = componentConfig.getHorizontalLayoutConfig("brandIdLayout", true,
				GtnFrameworkCommonConstants.UDC_CATEGORY_BRANDLAYOUT);
		componentList.add(brandIdLayout);

		GtnUIFrameworkComponentConfig brandId = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.UDC_BRAND_ID, true,
				brandIdLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		brandId.setAuthorizationIncluded(true);
		brandId.setComponentName("Brand ID");

		GtnUIFrameworkTextBoxConfig brandIdValueMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		brandIdValueMaxLengthConfig.setMaximumLength(50);
		brandId.setGtnTextBoxConfig(brandIdValueMaxLengthConfig);

		GtnUIFrameworkValidationConfig brandIdValueValidationConfig = new GtnUIFrameworkValidationConfig();
		brandIdValueValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		brandId.setGtnUIFrameworkValidationConfig(brandIdValueValidationConfig);
		componentList.add(brandId);
	}

	private void addBrandName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig brandNameLayout = componentConfig.getHorizontalLayoutConfig("brandNameLayout",
				true, GtnFrameworkCommonConstants.UDC_CATEGORY_BRANDLAYOUT);
		componentList.add(brandNameLayout);

		GtnUIFrameworkComponentConfig brandName = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.UDC_BRAND_NAME, true,
				brandNameLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		brandName.setAuthorizationIncluded(true);
		brandName.setComponentName("Brand Name");

		GtnUIFrameworkTextBoxConfig brandNamevalueMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		brandNamevalueMaxLengthConfig.setMaximumLength(50);
		brandName.setGtnTextBoxConfig(brandNamevalueMaxLengthConfig);

		GtnUIFrameworkValidationConfig brandNameValueValidationConfig = new GtnUIFrameworkValidationConfig();
		brandNameValueValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		brandName.setGtnUIFrameworkValidationConfig(brandNameValueValidationConfig);
		componentList.add(brandName);
	}

	private void addDisplayBrand(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig displayBrandLayout = componentConfig.getHorizontalLayoutConfig(
				"displayBrandLayout", true, GtnFrameworkCommonConstants.UDC_CATEGORY_BRANDLAYOUT);
		componentList.add(displayBrandLayout);

		GtnUIFrameworkComponentConfig displayBrand = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.UDC_DISPLAY_BRAND, true,
				displayBrandLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		displayBrand.setAuthorizationIncluded(true);
		displayBrand.setComponentName("Display Brand");

		GtnUIFrameworkTextBoxConfig displayBrandValueMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		displayBrandValueMaxLengthConfig.setMaximumLength(50);
		displayBrand.setGtnTextBoxConfig(displayBrandValueMaxLengthConfig);

		GtnUIFrameworkValidationConfig displayBrandValueValidationConfig = new GtnUIFrameworkValidationConfig();
		displayBrandValueValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		displayBrand.setGtnUIFrameworkValidationConfig(displayBrandValueValidationConfig);
		componentList.add(displayBrand);
	}

	private void addValue(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig valueLayout = componentConfig.getHorizontalLayoutConfig("valueLayout", true,
				GtnFrameworkCommonConstants.UDC_CATEGORYLAYOUT);
		componentList.add(valueLayout);

		GtnUIFrameworkComponentConfig valueConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.UDC_VALUE, true, valueLayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		valueConfig.setAuthorizationIncluded(true);
		valueConfig.setComponentName("Value");

		GtnUIFrameworkTextBoxConfig valueMaxLengthConfig = new GtnUIFrameworkTextBoxConfig();
		valueMaxLengthConfig.setMaximumLength(50);
		valueConfig.setGtnTextBoxConfig(valueMaxLengthConfig);

		GtnUIFrameworkValidationConfig valueValidationConfig = new GtnUIFrameworkValidationConfig();
		valueValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		valueConfig.setGtnUIFrameworkValidationConfig(valueValidationConfig);
		componentList.add(valueConfig);
	}

	private void addADDButton(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig addButtonLayout = componentConfig.getCssLayoutConfig("addLayout", true,
				GtnFrameworkCommonConstants.UDC_CATEGORYLAYOUT);
		addButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(addButtonLayout);

		GtnUIFrameworkComponentConfig addBrandButtonConfig = componentConfig.getUIFrameworkComponentConfig("ADD", true,
				addButtonLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addBrandButtonConfig.setComponentName("ADD");
		addBrandButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> addBrandActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig brandValidationActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		brandValidationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.UDC_VALUE));

		GtnUIFrameWorkActionConfig brandAlertActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> brandAlertParamsList = new ArrayList<>();
		brandAlertParamsList.add("Error");
		brandAlertParamsList.add("Space should not be allowed");

		brandAlertActionConfig.setActionParameterList(brandAlertParamsList);

		brandValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(brandAlertActionConfig)));
		addBrandActionConfigList.add(brandValidationActionConfig);

		GtnUIFrameWorkActionConfig brandAddActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		brandAddActionConfig.addActionParameter(GtnFrameworkUdcAddAction.class.getName());
		addBrandActionConfigList.add(brandAddActionConfig);

		GtnUIFrameWorkActionConfig brandValuedefaultActionConfig = new GtnUIFrameWorkActionConfig();
		brandValuedefaultActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		brandValuedefaultActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.UDC_VALUE));
		brandValuedefaultActionConfig.addActionParameter(Arrays.asList(""));
		addBrandActionConfigList.add(brandValuedefaultActionConfig);

		GtnUIFrameWorkActionConfig brandLoadDataTableActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		brandLoadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.UDC_RESULT_TABLE);
		brandLoadDataTableActionConfig.setFieldDescription(Arrays.asList(GtnFrameworkCommonConstants.UDC_CATEGORY));
		addBrandActionConfigList.add(brandLoadDataTableActionConfig);

		addBrandButtonConfig.setGtnUIFrameWorkActionConfigList(addBrandActionConfigList);
		componentList.add(addBrandButtonConfig);
	}

	private void addBrandAddButton(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig addBrandLayout = componentConfig.getCssLayoutConfig("addBrandLayout", true,
				GtnFrameworkCommonConstants.UDC_CATEGORYLAYOUT);
		addBrandLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		addBrandLayout.setVisible(false);
		componentList.add(addBrandLayout);

		GtnUIFrameworkComponentConfig addBrandButtonConfig = componentConfig.getUIFrameworkComponentConfig("ADDBRAND",
				true,
				addBrandLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addBrandButtonConfig.setComponentName("ADD");
		addBrandButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> addBrandActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationBrandActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationBrandActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.UDC_BRAND_ID,
				GtnFrameworkCommonConstants.UDC_BRAND_NAME, GtnFrameworkCommonConstants.UDC_DISPLAY_BRAND));

		GtnUIFrameWorkActionConfig alertBrandActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Error");
		alertParamsList.add("Space should not be allowed");

		alertBrandActionConfig.setActionParameterList(alertParamsList);

		validationBrandActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(alertBrandActionConfig)));
		addBrandActionConfigList.add(validationBrandActionConfig);

		GtnUIFrameWorkActionConfig addActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig.addActionParameter(GtnFrameworkUdcAddAction.class.getName());
		addBrandActionConfigList.add(addActionConfig);

		GtnUIFrameWorkActionConfig defaultActionConfig = new GtnUIFrameWorkActionConfig();
		defaultActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
		defaultActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.UDC_BRAND_ID,
				GtnFrameworkCommonConstants.UDC_BRAND_NAME, GtnFrameworkCommonConstants.UDC_DISPLAY_BRAND));
		defaultActionConfig.addActionParameter(Arrays.asList("", "", ""));
		addBrandActionConfigList.add(defaultActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE);
		addBrandActionConfigList.add(loadDataTableActionConfig);

		addBrandButtonConfig.setGtnUIFrameWorkActionConfigList(addBrandActionConfigList);
		componentList.add(addBrandButtonConfig);

	}

	private void addDELETEButton(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig deleteButtonLayout = componentConfig.getCssLayoutConfig("deleteLayout",
				true, GtnFrameworkCommonConstants.UDC_CATEGORYLAYOUT);
		deleteButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_2);
		componentList.add(deleteButtonLayout);

		GtnUIFrameworkComponentConfig deleteButtonConfig = componentConfig.getUIFrameworkComponentConfig("DELETE", true,
				deleteButtonLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		deleteButtonConfig.setComponentName("DELETE");
		deleteButtonConfig.setAuthorizationIncluded(true);

		List<GtnUIFrameWorkActionConfig> deleteActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnFrameworkUdcDeleteAction.class.getName());
		deleteActionConfigList.add(deleteActionConfig);

		deleteButtonConfig.setGtnUIFrameWorkActionConfigList(deleteActionConfigList);
		componentList.add(deleteButtonConfig);
	}

	private void addResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultsPanelConfig = componentConfig.getPanelConfig("categoryResultsPanel", false,
				null);
		resultsPanelConfig.setComponentName("Results");
		resultsPanelConfig.setAuthorizationIncluded(true);
		componentList.add(resultsPanelConfig);

		addResultsLayout(componentList, componentConfig);
	}

	private void addResultsLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig resultTableLayout = componentConfig
				.getHorizontalLayoutConfig(GtnFrameworkCommonConstants.UDC_RESULTS_LAYOUT, true,
						"categoryResultsPanel");
		resultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(resultTableLayout);

		addPagedTableComponent(componentList, componentConfig);
		addBrandPagedTableComponent(componentList, componentConfig);
		
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig categoryResultsConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.UDC_RESULT_TABLE, true, GtnFrameworkCommonConstants.UDC_RESULTS_LAYOUT,
				GtnUIFrameworkComponentType.PAGEDTABLE);
		categoryResultsConfig.setAuthorizationIncluded(true);
		categoryResultsConfig.setComponentName("udcResults");
		categoryResultsConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		GtnUIFrameworkPagedTableConfig searchResults = componentConfig.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,GtnFrameworkCommonConstants.UDC_CONFIGURATION,
				"SearchQuery");
		searchResults.setEditable(false);
		searchResults.setPageLength(5);
		searchResults.setItemPerPage(10);
		searchResults.setSinkItemPerPageWithPageLength(false);

		searchResults.setTableColumnDataType(GtnFrameworkUdcStringConstants.getUdcTableColumnType());
		searchResults.setTableColumnMappingId(GtnFrameworkUdcStringConstants.getUdcTableColumns());
		searchResults.setTableVisibleHeader(GtnFrameworkUdcStringConstants.getUdcTableHeader());

		searchResults.setExtraColumn(new Object[] { "systemId" });
		searchResults.setExtraColumnDataType(new Class[] { Integer.class });
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.UDC_CONFIGURATION);
		categoryResultsConfig.setGtnPagedTableConfig(searchResults);
		componentList.add(categoryResultsConfig);
	}
	
	private void addBrandPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig categoryResultsConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE, true,
				GtnFrameworkCommonConstants.UDC_RESULTS_LAYOUT,
				GtnUIFrameworkComponentType.PAGEDTABLE);
		categoryResultsConfig.setAuthorizationIncluded(true);
		categoryResultsConfig.setComponentName("brandUdcResults");
		categoryResultsConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		categoryResultsConfig.setVisible(false);

		GtnUIFrameworkPagedTableConfig searchResults = componentConfig.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnFrameworkCommonConstants.UDC_CONFIGURATION, "BrandSearchQuery");
		searchResults.setEditable(false);
		searchResults.setPageLength(5);
		searchResults.setItemPerPage(10);
		searchResults.setSinkItemPerPageWithPageLength(false);

		searchResults.setTableColumnDataType(GtnFrameworkUdcStringConstants.getUdcBrandTableColumnType());
		searchResults.setTableColumnMappingId(GtnFrameworkUdcStringConstants.getUdcBrandTableColumns());
		searchResults.setTableVisibleHeader(GtnFrameworkUdcStringConstants.getUdcBrandTableColumnHeader());

		searchResults.setExtraColumn(new Object[] { "brandMasterSid" });
		searchResults.setExtraColumnDataType(new Class[] { Integer.class });
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.UDC_CONFIGURATION);
		categoryResultsConfig.setGtnPagedTableConfig(searchResults);
		componentList.add(categoryResultsConfig);
	}

	private void addExcelButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig buttonLayout = componentConfig
				.getCssLayoutConfig(GtnFrameworkCommonConstants.UDC_EXCEL_BUTTONLAYOUT, false, null);
		buttonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_1);
		buttonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(buttonLayout);

		addExcelButtonComponent(componentList, componentConfig);
		addBrandExcelButtonComponent(componentList, componentConfig);
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig excelButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"gtnExcelButtonLayout", true, GtnFrameworkCommonConstants.UDC_EXCEL_BUTTONLAYOUT);
		excelButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(excelButtonLayout);

		GtnUIFrameworkComponentConfig excelButtonConfig = componentConfig.getUIFrameworkComponentConfig("excelExport",
				true,
				excelButtonLayout.getComponentId(), GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(excelButtonConfig);

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = componentConfig
				.getExcelBtnconfig("UDC", true, GtnFrameworkCommonConstants.UDC_RESULT_TABLE, false);
		gtnUIFrameworkExcelButtonConfig.setTitleNeeded(true);
		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		
		GtnUIFrameWorkActionConfig excelAction = componentConfig.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

	private void addBrandExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig brandExcelButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"gtnBrandExcelButtonLayout", true, GtnFrameworkCommonConstants.UDC_EXCEL_BUTTONLAYOUT);
		brandExcelButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		brandExcelButtonLayout.setVisible(false);
		componentList.add(brandExcelButtonLayout);

		GtnUIFrameworkComponentConfig brandexcelButtonConfig = componentConfig.getUIFrameworkComponentConfig(
				"brandExcelExport", true,
				brandExcelButtonLayout.getComponentId(), GtnUIFrameworkComponentType.EXCEL_BUTTON);
		brandexcelButtonConfig.setAuthorizationIncluded(true);
		componentList.add(brandexcelButtonConfig);

		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkBrandExcelButtonConfig = componentConfig.getExcelBtnconfig("UDC",
				true, GtnFrameworkCommonConstants.UDC_BRAND_RESULT_TABLE, false);
		gtnUIFrameworkBrandExcelButtonConfig.setTitleNeeded(true);
		brandexcelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkBrandExcelButtonConfig);

		GtnUIFrameWorkActionConfig excelAction = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkBrandExcelButtonConfig);
		brandexcelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}
}
