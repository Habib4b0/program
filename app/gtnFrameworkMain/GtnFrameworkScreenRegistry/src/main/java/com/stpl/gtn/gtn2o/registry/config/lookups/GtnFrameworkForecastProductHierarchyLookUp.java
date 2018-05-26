package com.stpl.gtn.gtn2o.registry.config.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkForecastProductHierarchyLookUp {
	
	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastProductHierarchyLookUp.class);

	public GtnUIFrameworkViewConfig getProdHierarchyLookUpView(String namespace) {
		logger.info("********** entered into the GtnFrameworkForecastProductHierarchyLookUp");
		GtnUIFrameworkViewConfig productHierarchyView = new GtnUIFrameworkViewConfig();
		productHierarchyView.setViewName("Forecast Product Hierarchy LookUp");
		productHierarchyView.setViewId("forecastLandingScreen_productHierarchyLookup");
		productHierarchyView.setDefaultView(false);
		addForecastProductHierarchyLookUpComponentList(productHierarchyView, namespace);
		return productHierarchyView;
	}

	private void addForecastProductHierarchyLookUpComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> productHierarchyLookupcomponentList = new ArrayList<>();
		view.setGtnComponentList(productHierarchyLookupcomponentList);
		addForecastProductHierarchyLookUpRootLayout(productHierarchyLookupcomponentList, namespace);

	}

	private void addForecastProductHierarchyLookUpRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig forecastProductHierarchyLookUpRootLayout = new GtnUIFrameworkComponentConfig();
		forecastProductHierarchyLookUpRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastProductHierarchyLookUpRootLayout.setComponentId(namespace + "_" + "forecastProductHierarchyLookUpRootLayout");
		forecastProductHierarchyLookUpRootLayout.setAddToParent(false);
		forecastProductHierarchyLookUpRootLayout.setSpacing(true);
		forecastProductHierarchyLookUpRootLayout.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		forecastProductHierarchyLookUpRootLayout.setGtnLayoutConfig(conf);
		componentList.add(forecastProductHierarchyLookUpRootLayout);
		getForecastProductHierarchyLookUpRootComponent(componentList, namespace);
	}

	public void getForecastProductHierarchyLookUpRootComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig forecastProductHierarchyLookUpRootConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig forecastProductHierarchyLookUpRootLayout = new GtnUIFrameworkLayoutConfig();
		forecastProductHierarchyLookUpRootLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		forecastProductHierarchyLookUpRootConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastProductHierarchyLookUpRootConfig.setGtnLayoutConfig(forecastProductHierarchyLookUpRootLayout);
		forecastProductHierarchyLookUpRootConfig.setAddToParent(true);

		forecastProductHierarchyLookUpRootConfig.setComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastProductHierarchyLookUpRootConfig.setParentComponentId(namespace + "_" + "forecastProductHierarchyLookUpRootLayout");
		forecastProductHierarchyLookUpRootConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastProductHierarchyLookUpRootConfig.setSpacing(true);
		forecastProductHierarchyLookUpRootConfig.setMargin(true);
		componentList.add(forecastProductHierarchyLookUpRootConfig);
		forecastProductHierarchyLookUpSearchCriteriaPanel(componentList, namespace);
		forecastProductHierarchySearchAndResetButtonLayout(componentList, namespace);
		forecastProductHierarchyLookUpResultsPanel(componentList, namespace);
		addForecastProductHierarchyControlPopUpButtonLayout(componentList, namespace);
	}

	private void forecastProductHierarchyLookUpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig forecastProductHierarchyLookUpSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		forecastProductHierarchyLookUpSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		forecastProductHierarchyLookUpSearchCriteriaPanel
				.setComponentId(namespace + "_" + "forecastProductHierarchyLookUpSearchCriteriaPanel");
		forecastProductHierarchyLookUpSearchCriteriaPanel.setComponentName("Search Criteria");
		forecastProductHierarchyLookUpSearchCriteriaPanel.setParentComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		forecastProductHierarchyLookUpSearchCriteriaPanel.setMargin(true);
		forecastProductHierarchyLookUpSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastProductHierarchyLookUpSearchCriteriaPanel.setAddToParent(true);
		componentList.add(forecastProductHierarchyLookUpSearchCriteriaPanel);
		forecastProductHierarchyLookUpSearchCriteriaLayout(componentList, namespace);
	}

	private void forecastProductHierarchyLookUpSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig forecastProductHierarchyLookUpSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		forecastProductHierarchyLookUpSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		forecastProductHierarchyLookUpSearchCriteriaConfig.setComponentId(
				namespace + "_" + "forecastProductHierarchyLookupSearchCriteriaLayout");
		forecastProductHierarchyLookUpSearchCriteriaConfig.setComponentName("Search Criteria");
		forecastProductHierarchyLookUpSearchCriteriaConfig.setAddToParent(true);
		forecastProductHierarchyLookUpSearchCriteriaConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		forecastProductHierarchyLookUpSearchCriteriaConfig
				.setParentComponentId(namespace + "_" + "forecastProductHierarchyLookUpSearchCriteriaPanel");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		forecastProductHierarchyLookUpSearchCriteriaConfig.setGtnLayoutConfig(conf);
		forecastProductHierarchyLookUpSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		forecastProductHierarchyLookUpSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(forecastProductHierarchyLookUpSearchCriteriaConfig);
		addHierarchyTypeOptionGroup(componentList, namespace);
		addHierarchyNameTextBox(componentList, namespace);
	}

	private void addHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		addHierarchyTypeOptionGroup.setComponentId(
				namespace + "_" + GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		addHierarchyTypeOptionGroup.setComponentName("Hierarchy Type");
		addHierarchyTypeOptionGroup.setAddToParent(true);
		addHierarchyTypeOptionGroup.setParentComponentId(
				namespace + "_" + "forecastProductHierarchyLookupSearchCriteriaLayout");
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList("Primary", "Secondary"));
		comboConfig.setValuesFromService(false);
		addHierarchyTypeOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		addHierarchyTypeOptionGroup.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		addHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		addHierarchyNameTextBox.setComponentId(
				namespace + "_" + GtnFrameworkCommonConstants.HIERARCHY_NAME);
		addHierarchyNameTextBox.setComponentName("Hierarchy Name");
		addHierarchyNameTextBox.setAddToParent(true);
		addHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		addHierarchyNameTextBox.setParentComponentId(
				namespace + "_" + "forecastProductHierarchyLookupSearchCriteriaLayout");
		componentList.add(addHierarchyNameTextBox);
	}

	private void forecastProductHierarchySearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig productHierarchySearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		productHierarchySearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productHierarchySearchAndResetLayout.setAddToParent(true);
		productHierarchySearchAndResetLayout.setParentComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		productHierarchySearchAndResetLayout.setComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		productHierarchySearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		productHierarchySearchAndResetLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(productHierarchySearchAndResetLayout);

		GtnUIFrameworkComponentConfig productHierarchySearchButton = new GtnUIFrameworkComponentConfig();
		productHierarchySearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		productHierarchySearchButton.setComponentId(namespace + "_" + "productHierarchySearchButton");
		productHierarchySearchButton.setComponentName("SEARCH");
		productHierarchySearchButton.setParentComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		productHierarchySearchButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(namespace + "_"
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(new String[] {
						namespace + "_"
								+ GtnFrameworkCommonConstants.HIERARCHY_TYPE,
						namespace + "_"
								+ GtnFrameworkCommonConstants.HIERARCHY_NAME }));
		loadDataTableActionConfig.setActionParameterList(actionParams);

		actionConfigList.add(loadDataTableActionConfig);
                productHierarchySearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
                
		componentList.add(productHierarchySearchButton);

		GtnUIFrameworkComponentConfig productHierarchyResetButton = new GtnUIFrameworkComponentConfig();
		productHierarchyResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		productHierarchyResetButton.setComponentId(
				namespace + "_" + "productHierarchyResetButton");
		productHierarchyResetButton.setComponentName("RESET");
		productHierarchyResetButton.setParentComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		productHierarchyResetButton.setAddToParent(true);

		componentList.add(productHierarchyResetButton);
	}

	private void forecastProductHierarchyLookUpResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig resultPanel = new GtnUIFrameworkComponentConfig();
		resultPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		resultPanel.setComponentId(namespace + "_" + "resultsPanel");
		resultPanel.setComponentName("Results");
		resultPanel.setParentComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		resultPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultPanel.setAddToParent(true);
		componentList.add(resultPanel);
		forecastProductHierarchyResultLayout(componentList, namespace);

	}

	private void forecastProductHierarchyResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig resultsLayout = new GtnUIFrameworkComponentConfig();
		resultsLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		resultsLayout.setComponentId(namespace + "_" + "resultLayout");

		resultsLayout.setParentComponentId(namespace + "_" + "resultsPanel");
		resultsLayout.setAddToParent(true);
		resultsLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultsLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultsLayout.setGtnLayoutConfig(conf);
		componentList.add(resultsLayout);
		addForecastProductHierarchyPagedTableComponent(componentList, namespace);
	}

	private void addForecastProductHierarchyPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		searchResultConfig.setComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig
				.setParentComponentId(namespace + "_" + "resultLayout");
		searchResultConfig.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		searchResultConfig.setComponentWidth("100%");
		searchResultConfig.setComponentHight("450px");
		searchResultConfig.setComponentStyle(tableStyle);
		searchResultConfig.setModuleName("report");
		componentList.add(searchResultConfig);
		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();

		searchResults.setColumnHeaders( Arrays.asList("HierarchyName", "Highest Level", "Lowest Level",
				GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER));
		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE });
		searchResults.setTableColumnMappingId(
				new Object[] { "hierachyName", "highestLevel", "lowestLevel", "createdDate", "modifiedDate" });
		
		searchResults.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE     // need to change
				+ GtnWsReportConstants.GTN_REPORT_PRODUCTHIERARCHY_SEARCHSERVICE);
		searchResults.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_PRODUCTHIERARCHY_SEARCHSERVICE);
		searchResults.setCustomFilterConfigMap(getCustomFilterConfig());
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { "hierachyName", "highestLevel", "lowestLevel", "createdDate", "modifiedDate"  };
		GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.COMBOBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.DATEFIELDVAADIN8 };
		String[] comboboxIds={"highestLevel"};
		String[] comboboxType={"STATUS"};
		int comboboxStart=0;
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig pagedTableCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			pagedTableCustomFilterConfig.setPropertId(propertyIds[i]);
			pagedTableCustomFilterConfig.setGtnComponentType(componentType[i]);
			if((comboboxStart<comboboxIds.length) && propertyIds[i].equals(comboboxIds[comboboxStart])){
				GtnUIFrameworkComponentConfig companyMasterSearchFilterComponentConfig = new GtnUIFrameworkComponentConfig();
				companyMasterSearchFilterComponentConfig.setComponentId("customFilterComboBox");
				companyMasterSearchFilterComponentConfig.setComponentName("customFilterComboBox");
				companyMasterSearchFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				companyMasterSearchFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(comboboxType[comboboxStart]);
				companyMasterSearchFilterComponentConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				pagedTableCustomFilterConfig.setGtnComponentConfig(companyMasterSearchFilterComponentConfig);
				comboboxStart++;
			}
			customFilterConfigMap.put(pagedTableCustomFilterConfig.getPropertId(), pagedTableCustomFilterConfig);
		}
		return customFilterConfigMap;
	}

	private void addForecastProductHierarchyControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig controlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		controlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlPopUpLayout.setAddToParent(true);
		controlPopUpLayout.setParentComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		controlPopUpLayout.setComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		controlPopUpLayout.setSpacing(true);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig selectButton = new GtnUIFrameworkComponentConfig();
		selectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButton.setComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SELECT_BUTTON);
		selectButton.setComponentName("SELECT");
		selectButton.setParentComponentId(namespace +"_"
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		selectButton.setAddToParent(true);
		selectButton.addDependentComponent("reportLandingScreen_relationship"); // need to change
		selectButton.addDependentComponent("reportLandingScreen_level");		// need to change
                List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + "_"
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		actionParameter.add("reportLandingScreen_producthierarchy");		// need to change
		actionParameter.add(Arrays.asList("hierachyName"));
		actionParameter
				.add(Arrays.asList("reportLandingScreen_producthierarchy"));		// need to change

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);
		
		GtnUIFrameWorkActionConfig forecastProductHierarchyClosepopup = new GtnUIFrameWorkActionConfig();		
		forecastProductHierarchyClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		forecastProductHierarchyClosepopup.addActionParameter("productHierarchyLookup");
		actionConfigList.add(forecastProductHierarchyClosepopup);
		
		selectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(selectButton);

		GtnUIFrameworkComponentConfig cancelButton = new GtnUIFrameworkComponentConfig();
		cancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cancelButton.setComponentId(
				namespace + "_" + "productHierarchyCancelButton");
		cancelButton.setComponentName("CANCEL");
		cancelButton.setParentComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		cancelButton.setAddToParent(true);

		componentList.add(cancelButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(
				namespace + "_" + "productHierarchyResetControlButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(namespace + "_"
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		resetButton.setAddToParent(true);

		componentList.add(resetButton);
	}
}
