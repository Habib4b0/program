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
import com.stpl.gtn.gtn2o.ui.module.udc.constants.GtnFrameworkUdcClassConstants;
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
		;
		componentList.add(udcCategoryLayout);
		addFieldComponent(componentList, componentConfig);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		addCategory(componentList, componentConfig);
		addValue(componentList, componentConfig);
		addADDButton(componentList, componentConfig);
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
		udcCategory.setGtnComboboxConfig(categoryConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = componentConfig
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.UDC_RESULT_TABLE);
		loadDataTableActionConfig.setFieldDescription(Arrays.asList(GtnFrameworkCommonConstants.UDC_CATEGORY));
		
		actionConfigList.add(loadDataTableActionConfig);
		udcCategory.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(udcCategory);

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
		GtnUIFrameworkComponentConfig addButtonLayout = componentConfig.getHorizontalLayoutConfig("addLayout", true,
				GtnFrameworkCommonConstants.UDC_CATEGORYLAYOUT);
		componentList.add(addButtonLayout);

		GtnUIFrameworkComponentConfig addButtonConfig = componentConfig.getUIFrameworkComponentConfig("ADD", true,
				addButtonLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setComponentName("ADD");
		addButtonConfig.setAuthorizationIncluded(true);
		
		
		List<GtnUIFrameWorkActionConfig> addActionConfigList = new ArrayList<>();
		
		GtnUIFrameWorkActionConfig validationActionConfig = componentConfig.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.UDC_VALUE));
		
		GtnUIFrameWorkActionConfig alertActionConfig = componentConfig.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("Error");
		alertParamsList.add("Space should not be allowed");
		
		alertActionConfig.setActionParameterList(alertParamsList);
		
		validationActionConfig.setActionParameterList(Arrays.asList(GtnUIFrameworkValidationType.AND,alertActionConfig));
		addActionConfigList.add(validationActionConfig);
		
		GtnUIFrameWorkActionConfig addActionConfig = componentConfig.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig.addActionParameter(GtnFrameworkUdcClassConstants.UDC_ADD_ACTION);
		
		addButtonConfig.setGtnUIFrameWorkActionConfigList(addActionConfigList);
		componentList.add(addButtonConfig);
	}

	private void addDELETEButton(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig deleteButtonLayout = componentConfig.getHorizontalLayoutConfig("deleteLayout",
				true, GtnFrameworkCommonConstants.UDC_CATEGORYLAYOUT);
		componentList.add(deleteButtonLayout);

		GtnUIFrameworkComponentConfig deleteButtonConfig = componentConfig.getUIFrameworkComponentConfig("DELETE", true,
				deleteButtonLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		deleteButtonConfig.setComponentName("DELETE");
		deleteButtonConfig.setAuthorizationIncluded(true);
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
				.getHorizontalLayoutConfig("CategoryResultsLayout", true, "categoryResultsPanel");
		resultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(resultTableLayout);

		addPagedTableComponent(componentList, componentConfig);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig categoryResultsConfig = componentConfig.getUIFrameworkComponentConfig(
				GtnFrameworkCommonConstants.UDC_RESULT_TABLE, true, "CategoryResultsLayout",
				GtnUIFrameworkComponentType.PAGEDTABLE);
		categoryResultsConfig.setAuthorizationIncluded(true);
		categoryResultsConfig.setComponentName("Results");
		categoryResultsConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

		GtnUIFrameworkPagedTableConfig searchResults = componentConfig.getPagedTableConfig(true, true,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH, "Udc Configuration",
				"SearchQuery");
		searchResults.setEditable(false);
		searchResults.setPageLength(5);
		searchResults.setItemPerPage(10);
		searchResults.setSinkItemPerPageWithPageLength(false);

		searchResults.setTableColumnDataType(GtnFrameworkUdcStringConstants.getUdcTableColumnType());
		searchResults.setTableColumnMappingId(GtnFrameworkUdcStringConstants.getUdcTableColumns());
		searchResults.setTableVisibleHeader(GtnFrameworkUdcStringConstants.getUdcTableHeader());
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
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider componentConfig) {
		GtnUIFrameworkComponentConfig excelButtonLayout = componentConfig.getHorizontalLayoutConfig(
				"gtnExcelButtonLayout", true, GtnFrameworkCommonConstants.UDC_EXCEL_BUTTONLAYOUT);
		excelButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		componentList.add(excelButtonLayout);

		GtnUIFrameworkComponentConfig excelButtonConfig = componentConfig.getUIFrameworkComponentConfig(null, true,
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
}
