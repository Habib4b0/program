package com.stpl.gtn.gtn2o.ui.module.lookups;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GtnFrameworkReportProductHierarchyLookUp {

	public GtnUIFrameworkViewConfig getProdHierarchyLookUpView(String namespace) {
		GtnUIFrameworkViewConfig productHierarchyView = new GtnUIFrameworkViewConfig();
		productHierarchyView.setViewName("Product Hierarchy LookUp");
		productHierarchyView.setViewId("productHierarchyLookup");
		productHierarchyView.setDefaultView(false);
		addReportProductHierarchyLookUpComponentList(productHierarchyView, namespace);
		return productHierarchyView;
	}

	private void addReportProductHierarchyLookUpComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> productHierarchyLookupcomponentList = new ArrayList<>();
		view.setGtnComponentList(productHierarchyLookupcomponentList);
		addReportProductHierarchyLookUpRootLayout(productHierarchyLookupcomponentList, namespace);

	}

	private void addReportProductHierarchyLookUpRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProductHierarchyLookUpRootLayout = new GtnUIFrameworkComponentConfig();
		reportProductHierarchyLookUpRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProductHierarchyLookUpRootLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpRootLayout");
		reportProductHierarchyLookUpRootLayout.setAddToParent(false);
		reportProductHierarchyLookUpRootLayout.setSpacing(true);
		reportProductHierarchyLookUpRootLayout.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportProductHierarchyLookUpRootLayout.setGtnLayoutConfig(conf);
		componentList.add(reportProductHierarchyLookUpRootLayout);
		getReportProductHierarchyLookUpRootComponent(componentList, namespace);
	}

	public void getReportProductHierarchyLookUpRootComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProductHierarchyLookUpRootConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig reportProductHierarchyLookUpRootLayout = new GtnUIFrameworkLayoutConfig();
		reportProductHierarchyLookUpRootLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportProductHierarchyLookUpRootConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProductHierarchyLookUpRootConfig.setGtnLayoutConfig(reportProductHierarchyLookUpRootLayout);
		reportProductHierarchyLookUpRootConfig.setAddToParent(true);

		reportProductHierarchyLookUpRootConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProductHierarchyLookUpRootConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpRootLayout");
		reportProductHierarchyLookUpRootConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProductHierarchyLookUpRootConfig.setSpacing(true);
		reportProductHierarchyLookUpRootConfig.setMargin(true);
		componentList.add(reportProductHierarchyLookUpRootConfig);
		reportProductHierarchyLookUpSearchCriteriaPanel(componentList, namespace);
		productHierarchySearchAndResetButtonLayout(componentList, namespace);
		reportProductHierarchyLookUpResultsPanel(componentList, namespace);
		addreportProductHierarchyControlPopUpButtonLayout(componentList, namespace);
	}

	private void reportProductHierarchyLookUpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProductHierarchyLookUpSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		reportProductHierarchyLookUpSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportProductHierarchyLookUpSearchCriteriaPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpSearchCriteriaPanel");
		reportProductHierarchyLookUpSearchCriteriaPanel.setComponentName("Search Criteria");
		reportProductHierarchyLookUpSearchCriteriaPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProductHierarchyLookUpSearchCriteriaPanel.setMargin(true);
		reportProductHierarchyLookUpSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProductHierarchyLookUpSearchCriteriaPanel.setAddToParent(true);
		componentList.add(reportProductHierarchyLookUpSearchCriteriaPanel);
		reportProductHierarchyLookUpSearchCriteriaLayout(componentList, namespace);
	}

	private void reportProductHierarchyLookUpSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProductHierarchyLookUpSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		reportProductHierarchyLookUpSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProductHierarchyLookUpSearchCriteriaConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_LOOKUP_SEARCH_CRITERIA_LAYOUT);
		reportProductHierarchyLookUpSearchCriteriaConfig.setComponentName("Search Criteria");
		reportProductHierarchyLookUpSearchCriteriaConfig.setAddToParent(true);
		reportProductHierarchyLookUpSearchCriteriaConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProductHierarchyLookUpSearchCriteriaConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpSearchCriteriaPanel");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		reportProductHierarchyLookUpSearchCriteriaConfig.setGtnLayoutConfig(conf);
		reportProductHierarchyLookUpSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		reportProductHierarchyLookUpSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(reportProductHierarchyLookUpSearchCriteriaConfig);
		addHierarchyTypeOptionGroup(componentList, namespace);
		addHierarchyNameTextBox(componentList, namespace);
	}

	private void addHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		addHierarchyTypeOptionGroup.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		addHierarchyTypeOptionGroup.setComponentName("Hierarchy Type");
		addHierarchyTypeOptionGroup.setAddToParent(true);
		addHierarchyTypeOptionGroup.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_LOOKUP_SEARCH_CRITERIA_LAYOUT);
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
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME);
		addHierarchyNameTextBox.setComponentName("Hierarchy Name");
		addHierarchyNameTextBox.setAddToParent(true);
		addHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		addHierarchyNameTextBox.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_LOOKUP_SEARCH_CRITERIA_LAYOUT);
		componentList.add(addHierarchyNameTextBox);
	}

	private void productHierarchySearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig productHierarchySearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		productHierarchySearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productHierarchySearchAndResetLayout.setAddToParent(true);
		productHierarchySearchAndResetLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		productHierarchySearchAndResetLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		productHierarchySearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		productHierarchySearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(productHierarchySearchAndResetLayout);

		GtnUIFrameworkComponentConfig productHierarchySearchButton = new GtnUIFrameworkComponentConfig();
		productHierarchySearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		productHierarchySearchButton.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productHierarchySearchButton");
		productHierarchySearchButton.setComponentName("SEARCH");
		productHierarchySearchButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		productHierarchySearchButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);

		actionConfigList.add(loadDataTableActionConfig);
                productHierarchySearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
                
		componentList.add(productHierarchySearchButton);

		GtnUIFrameworkComponentConfig productHierarchyResetButton = new GtnUIFrameworkComponentConfig();
		productHierarchyResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		productHierarchyResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productHierarchyResetButton");
		productHierarchyResetButton.setComponentName("RESET");
		productHierarchyResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		productHierarchyResetButton.setAddToParent(true);

		componentList.add(productHierarchyResetButton);
	}

	private void reportProductHierarchyLookUpResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig resultPanel = new GtnUIFrameworkComponentConfig();
		resultPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		resultPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "resultsPanel");
		resultPanel.setComponentName("Results");
		resultPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		resultPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultPanel.setAddToParent(true);
		componentList.add(resultPanel);
		resultLayout(componentList, namespace);

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig resultsLayout = new GtnUIFrameworkComponentConfig();
		resultsLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		resultsLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "resultLayout");

		resultsLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "resultsPanel");
		resultsLayout.setAddToParent(true);
		resultsLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultsLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultsLayout.setGtnLayoutConfig(conf);
		componentList.add(resultsLayout);
		addProductHierarchyPagedTableComponent(componentList, namespace);
	}

	private void addProductHierarchyPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		searchResultConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "resultLayout");
		searchResultConfig.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		searchResultConfig.setComponentWidth("100%");
		searchResultConfig.setComponentStyle(tableStyle);

		componentList.add(searchResultConfig);
		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
//		searchResults.setEditable(false);
//		searchResults.setFilterBar(true);
//		searchResults.setSelectable(false);
//		searchResults.setPageLength(10);
//		searchResults.setItemPerPage(10);
//		searchResults.setSelectable(true);
//		searchResults.setSinkItemPerPageWithPageLength(false);
//		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING,
//				GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
//				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE });
		searchResults.setTableVisibleHeader(new String[] { "HierarchyName", "Highest Level", "Lowest Level",
				GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER });
		searchResults.setTableColumnMappingId(
				new Object[] { "hierName", "highestLevel", "lowestLevel", "createdDate", "modifiedDate" });
                searchResults.setCountQuery(Query.getCountProductHierarchy);
                searchResults.setDataQuery(Query.getDataProductHierarchy);
                searchResults.setCountQueryInputs(new String[]{
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.HIERARCHY_NAME,namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_TYPE});
                searchResults.setDataQueryInputs(new String[]{
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.HIERARCHY_NAME,namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_TYPE});

		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addreportProductHierarchyControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig controlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		controlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlPopUpLayout.setAddToParent(true);
		controlPopUpLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		controlPopUpLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		controlPopUpLayout.setSpacing(true);
		controlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(controlPopUpLayout);

		GtnUIFrameworkComponentConfig selectButton = new GtnUIFrameworkComponentConfig();
		selectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		selectButton.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SELECT_BUTTON);
		selectButton.setComponentName("SELECT");
		selectButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		selectButton.setAddToParent(true);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		actionParameter.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "producthierarchy");
		actionParameter.add(Arrays.asList("hierName"));
		actionParameter
				.add(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "producthierarchy"));

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);
		selectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(selectButton);

		GtnUIFrameworkComponentConfig cancelButton = new GtnUIFrameworkComponentConfig();
		cancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		cancelButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productHierarchyCancelButton");
		cancelButton.setComponentName("CANCEL");
		cancelButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		cancelButton.setAddToParent(true);

		componentList.add(cancelButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productHierarchyResetControlButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		resetButton.setAddToParent(true);

		componentList.add(resetButton);
	}
}
