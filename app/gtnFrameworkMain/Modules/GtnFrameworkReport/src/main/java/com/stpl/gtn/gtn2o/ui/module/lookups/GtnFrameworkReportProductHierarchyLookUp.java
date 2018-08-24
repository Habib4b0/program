package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkSelectButtonEnableActionInHierarchyLookup;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkRegexStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportProductHierarchyLookUp {

	public GtnUIFrameworkViewConfig getProdHierarchyLookUpView(String namespace) {
		GtnUIFrameworkViewConfig productHierarchyView = new GtnUIFrameworkViewConfig();
		productHierarchyView.setViewName("Product Hierarchy LookUp");
		productHierarchyView.setViewId("reportLandingScreen_productHierarchyLookup");
		productHierarchyView.setDefaultView(false);
		productHierarchyView.setResetAllowed(true);
		addReportProductHierarchyLookUpComponentList(productHierarchyView, namespace);
		return productHierarchyView;
	}

	private void addReportProductHierarchyLookUpComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> productHierarchyLookupcomponentList = new ArrayList<>();
		view.setGtnComponentList(productHierarchyLookupcomponentList);
		addReportProductHierarchyLookUpRootLayout(productHierarchyLookupcomponentList, namespace);

	}

	private void addReportProductHierarchyLookUpRootLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportProductHierarchyLookUpRootLayout = new GtnUIFrameworkComponentConfig();
		reportProductHierarchyLookUpRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProductHierarchyLookUpRootLayout.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpRootLayout");
		reportProductHierarchyLookUpRootLayout.setAddToParent(false);
		reportProductHierarchyLookUpRootLayout.setSpacing(true);
		reportProductHierarchyLookUpRootLayout.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportProductHierarchyLookUpRootLayout.setGtnLayoutConfig(conf);
		componentList.add(reportProductHierarchyLookUpRootLayout);
		getReportProductHierarchyLookUpRootComponent(componentList, namespace);
	}

	public void getReportProductHierarchyLookUpRootComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportProductHierarchyLookUpRootConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig reportProductHierarchyLookUpRootLayout = new GtnUIFrameworkLayoutConfig();
		reportProductHierarchyLookUpRootLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportProductHierarchyLookUpRootConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProductHierarchyLookUpRootConfig.setGtnLayoutConfig(reportProductHierarchyLookUpRootLayout);
		reportProductHierarchyLookUpRootConfig.setAddToParent(true);

		reportProductHierarchyLookUpRootConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProductHierarchyLookUpRootConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpRootLayout");
		reportProductHierarchyLookUpRootConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProductHierarchyLookUpRootConfig.setSpacing(true);
		reportProductHierarchyLookUpRootConfig.setMargin(true);
		componentList.add(reportProductHierarchyLookUpRootConfig);
		reportProductHierarchyLookUpSearchCriteriaPanel(componentList, namespace);
		productHierarchySearchAndResetButtonLayout(componentList, namespace);
		reportProductHierarchyLookUpResultsPanel(componentList, namespace);
		addreportProductHierarchyControlPopUpButtonLayout(componentList, namespace);
	}

	private void reportProductHierarchyLookUpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportProductHierarchyLookUpSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		reportProductHierarchyLookUpSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportProductHierarchyLookUpSearchCriteriaPanel.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpSearchCriteriaPanel");
		reportProductHierarchyLookUpSearchCriteriaPanel.setComponentName("Search Criteria");
		reportProductHierarchyLookUpSearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProductHierarchyLookUpSearchCriteriaPanel.setMargin(true);
		reportProductHierarchyLookUpSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProductHierarchyLookUpSearchCriteriaPanel.setAddToParent(true);
		reportProductHierarchyLookUpSearchCriteriaPanel.addComponentStyle("stpl-margin-left-10");
		reportProductHierarchyLookUpSearchCriteriaPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(reportProductHierarchyLookUpSearchCriteriaPanel);
		reportProductHierarchyLookUpSearchCriteriaLayout(componentList, namespace);
	}

	private void reportProductHierarchyLookUpSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportProductHierarchyLookUpSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		reportProductHierarchyLookUpSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProductHierarchyLookUpSearchCriteriaConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_LOOKUP_SEARCH_CRITERIA_LAYOUT);
		reportProductHierarchyLookUpSearchCriteriaConfig.setComponentName("Search Criteria");
		reportProductHierarchyLookUpSearchCriteriaConfig.setAddToParent(true);
		reportProductHierarchyLookUpSearchCriteriaConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProductHierarchyLookUpSearchCriteriaConfig.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpSearchCriteriaPanel");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		reportProductHierarchyLookUpSearchCriteriaConfig.setGtnLayoutConfig(conf);
		reportProductHierarchyLookUpSearchCriteriaConfig
				.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		reportProductHierarchyLookUpSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(reportProductHierarchyLookUpSearchCriteriaConfig);
		addHierarchyTypeOptionGroup(componentList, namespace);
		addHierarchyNameTextBox(componentList, namespace);
	}

	private void addHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		
		GtnUIFrameworkComponentConfig addReportProductHierarchyTypeOptionGroupLayout = new GtnUIFrameworkComponentConfig();
		addReportProductHierarchyTypeOptionGroupLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		addReportProductHierarchyTypeOptionGroupLayout
		.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "reportProductHierarchyTypeOptionGroupVerticalLayout");
		addReportProductHierarchyTypeOptionGroupLayout.addComponentStyle("stpl-margin-left-25");
		addReportProductHierarchyTypeOptionGroupLayout.setAddToParent(true);
		addReportProductHierarchyTypeOptionGroupLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_LOOKUP_SEARCH_CRITERIA_LAYOUT);
		
		GtnUIFrameworkLayoutConfig reportProductHierarchyTypeOptionGroupainLayout = new GtnUIFrameworkLayoutConfig();
		reportProductHierarchyTypeOptionGroupainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		addReportProductHierarchyTypeOptionGroupLayout.setGtnLayoutConfig(reportProductHierarchyTypeOptionGroupainLayout);
		
		GtnUIFrameworkComponentConfig addHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		addHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		addHierarchyTypeOptionGroup.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		addHierarchyTypeOptionGroup.setComponentName("Hierarchy Type:  ");
		addHierarchyTypeOptionGroup.setAddToParent(true);
		addHierarchyTypeOptionGroup.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "reportProductHierarchyTypeOptionGroupVerticalLayout");
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList("Primary", "Secondary"));
		comboConfig.setValuesFromService(false);
		addHierarchyTypeOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		addHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(addReportProductHierarchyTypeOptionGroupLayout);
		componentList.add(addHierarchyTypeOptionGroup);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		
		GtnUIFrameworkComponentConfig addReportProductHierarchyNameTextBoxLayout = new GtnUIFrameworkComponentConfig();
		addReportProductHierarchyNameTextBoxLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		addReportProductHierarchyNameTextBoxLayout
		.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "reportProductHierarchyNameTextBoxVerticalLayout");
		addReportProductHierarchyNameTextBoxLayout.setAddToParent(true);
		addReportProductHierarchyNameTextBoxLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_LOOKUP_SEARCH_CRITERIA_LAYOUT);
		
		GtnUIFrameworkLayoutConfig reportLandingScreenProductHierarchyNameMainLayout = new GtnUIFrameworkLayoutConfig();
		reportLandingScreenProductHierarchyNameMainLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		addReportProductHierarchyNameTextBoxLayout.setGtnLayoutConfig(reportLandingScreenProductHierarchyNameMainLayout);
		
		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		addHierarchyNameTextBox.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRODUCT_HIERARCHY_NAME);
		addHierarchyNameTextBox.setComponentName("Hierarchy Name:  ");
		addHierarchyNameTextBox.addComponentStyle("stpl-margin-left-25");
		addHierarchyNameTextBox.setComponentHight("100%");
		addHierarchyNameTextBox.setDefaultFocus(true);
		addHierarchyNameTextBox.setAddToParent(true);
		addHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		addHierarchyNameTextBox.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "reportProductHierarchyNameTextBoxVerticalLayout");

		GtnUIFrameworkValidationConfig hierarchyNameValidationConfig = new GtnUIFrameworkValidationConfig();
		hierarchyNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		hierarchyNameValidationConfig.setAttachRegxValidatior(true);
		hierarchyNameValidationConfig.setFormatString(GtnFrameworkRegexStringConstants.ACCEPT_MIN_1_MAX_200_CHARACTER);
		hierarchyNameValidationConfig.setRegxValidationMessage("Hierarchy Name Should be less than 200 Characters");
		addHierarchyNameTextBox.setGtnUIFrameworkValidationConfig(hierarchyNameValidationConfig);
		componentList.add(addReportProductHierarchyNameTextBoxLayout);
		componentList.add(addHierarchyNameTextBox);
	}

	private void productHierarchySearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig productHierarchySearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		productHierarchySearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productHierarchySearchAndResetLayout.setAddToParent(true);
		productHierarchySearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		productHierarchySearchAndResetLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		productHierarchySearchAndResetLayout.setSpacing(true);
		productHierarchySearchAndResetLayout.setMargin(true);

		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		productHierarchySearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(productHierarchySearchAndResetLayout);

		GtnUIFrameworkComponentConfig productHierarchySearchButton = new GtnUIFrameworkComponentConfig();
		productHierarchySearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		productHierarchySearchButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "landingScreenProductHierarchySearchButton");
		productHierarchySearchButton.setComponentName("SEARCH");
		productHierarchySearchButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		productHierarchySearchButton.addComponentStyle(GtnFrameworkReportStringConstants.BUTTON_CUSTOM_STYLE_FOR_LESS_SPACE_BETWEEN_BUTTONS);	
		productHierarchySearchButton.setCustomReference(GtnFrameworkReportStringConstants.DO_NOT_ADD_BUTTON_CUSTOM_STYLE);
		
		productHierarchySearchButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);

		validationActionConfig
				.setFieldValues(Arrays.asList("reportProductHierarchyLookUp_landingScreenProductHierName"));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add("No Results Found");
		alertParamsList.add("There are no Hierarchies that match the search criteria.");

		alertActionConfig.setActionParameterList(alertParamsList);
		Object validationType = GtnUIFrameworkValidationType.OR;
		validationActionConfig.setActionParameterList(Arrays.asList(validationType, Arrays.asList(alertActionConfig)));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(new String[] {
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkCommonConstants.HIERARCHY_TYPE,
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkReportStringConstants.PRODUCT_HIERARCHY_NAME }));
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

		List<GtnUIFrameWorkActionConfig> productHierarchyResetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig productHierarchyResetActionConfig = new GtnUIFrameWorkActionConfig();
		productHierarchyResetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);

		List<Object> productHierarchyParams = new ArrayList<>();
		productHierarchyParams.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		productHierarchyParams.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_MESSAGE);
		productHierarchyParams.add(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.HIER_TYPE,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PRODUCT_HIERARCHY_NAME));
		productHierarchyParams
				.add(Arrays.asList(new Object[] { "Primary", GtnFrameworkCommonStringConstants.STRING_EMPTY }));
		productHierarchyResetActionConfig.setActionParameterList(productHierarchyParams);
		productHierarchyResetActionConfigList.add(productHierarchyResetActionConfig);
		productHierarchyResetButton.setGtnUIFrameWorkActionConfigList(productHierarchyResetActionConfigList);

		componentList.add(productHierarchyResetButton);
	}

	private void reportProductHierarchyLookUpResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig resultPanel = new GtnUIFrameworkComponentConfig();
		resultPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		resultPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "resultsPanel");
		resultPanel.setComponentName("RESULTS");
		resultPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		resultPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultPanel.setAddToParent(true);
		resultPanel.addComponentStyle("stpl-margin-left-10");
		resultPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(resultPanel);
		addProductHierarchyPagedTableComponent(componentList, namespace);

	}

	private void addProductHierarchyPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig searchResultConfig = new GtnUIFrameworkComponentConfig();
		searchResultConfig.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		searchResultConfig.setMargin(false);
		searchResultConfig.setSpacing(false);
		searchResultConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		searchResultConfig.setComponentName("Results");
		searchResultConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "resultsPanel");
		searchResultConfig.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		searchResultConfig.setComponentWidth("100%");
		searchResultConfig.setComponentStyle(tableStyle);
		searchResultConfig.setModuleName("report");
		searchResultConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_BOTTOM_14);

		componentList.add(searchResultConfig);
		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();

		searchResults.setPaginationOff(true);
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setPageLength(10);
		searchResults.setItemPerPage(10);
		searchResults.setSelectable(true);
		searchResults.setSinkItemPerPageWithPageLength(false);
		
		GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
		alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		alertAction.addActionParameter("No Results Found");
		alertAction.addActionParameter("There are no Hierarchies that match the search criteria.");
		searchResults.setRecordTypeManageActionConfig(alertAction);

		searchResults.setColumnHeaders(Arrays.asList("HierarchyName", "Highest Level", "Lowest Level",
				GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER));
		searchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE });
		searchResults.setTableColumnMappingId(GtnFrameworkReportStringConstants.getReportProductFilterPropertyId());

		searchResults.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_PRODUCTHIERARCHY_SEARCHSERVICE);
		searchResults.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_PRODUCTHIERARCHY_SEARCHSERVICE);
		searchResults.setCustomFilterConfigMap(getCustomFilterConfig());
		searchResults.setSelectionListener(true);

		List<GtnUIFrameWorkActionConfig> itemClickActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectButtonEnableActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		selectButtonEnableActionConfig
				.addActionParameter(GtnFrameworkSelectButtonEnableActionInHierarchyLookup.class.getName());
		selectButtonEnableActionConfig.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE +GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SELECT_BUTTON);
		selectButtonEnableActionConfig.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		itemClickActionConfigList.add(selectButtonEnableActionConfig);
		searchResults.setItemClickActionConfigList(itemClickActionConfigList);
		searchResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		String[] columnPropertyIds = GtnFrameworkReportStringConstants.getReportProductFilterPropertyId();
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> productHierarchyustomFilterConfigMap = new HashMap<>(
				columnPropertyIds.length);
		GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.DATEFIELDVAADIN8, GtnUIFrameworkComponentType.DATEFIELDVAADIN8 };
		String[] productComboboxIds = new String[1];
		String[] productComboBoxType =  new String[1];
		int startIndex = 0;
		for (int i = 0; i < columnPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig prodHierarchyFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			prodHierarchyFilterConfig.setPropertId(columnPropertyIds[i]);
			prodHierarchyFilterConfig.setGtnComponentType(componentType[i]);
			if ((startIndex < productComboboxIds.length)
					&& columnPropertyIds[i].equals(productComboboxIds[startIndex])) {
				GtnUIFrameworkComponentConfig prodHierarchySearchFilterConfig = new GtnUIFrameworkComponentConfig();
				prodHierarchySearchFilterConfig.setComponentId("customFilterComboBox");
				prodHierarchySearchFilterConfig.setComponentName("customFilterComboBox");
				prodHierarchySearchFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				prodHierarchySearchFilterConfig.getGtnComboboxConfig().setComboBoxType(productComboBoxType[startIndex]);
				prodHierarchySearchFilterConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				prodHierarchyFilterConfig.setGtnComponentConfig(prodHierarchySearchFilterConfig);
				startIndex++;
			}
			productHierarchyustomFilterConfigMap.put(prodHierarchyFilterConfig.getPropertId(),
					prodHierarchyFilterConfig);
		}
		return productHierarchyustomFilterConfigMap;
	}

	private void addreportProductHierarchyControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig controlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		controlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		controlPopUpLayout.setAddToParent(true);
		controlPopUpLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		controlPopUpLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
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
		selectButton.addDependentComponent("reportLandingScreen_relationship");
		selectButton.setEnable(false);
		selectButton.addComponentStyle(GtnFrameworkReportStringConstants.BUTTON_CUSTOM_STYLE_FOR_LESS_SPACE_BETWEEN_BUTTONS);
		selectButton.setCustomReference(GtnFrameworkReportStringConstants.DO_NOT_ADD_BUTTON_CUSTOM_STYLE);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		actionParameter.add("reportLandingScreen_producthierarchy");
		actionParameter.add(Arrays.asList(GtnFrameworkReportStringConstants.HIER_NAME));
		actionParameter.add(Arrays.asList("reportLandingScreen_producthierarchy"));

		selectAction.setActionParameterList(actionParameter);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig reportProductHierarchyClosepopup = new GtnUIFrameWorkActionConfig();
		reportProductHierarchyClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		reportProductHierarchyClosepopup.addActionParameter("reportLandingScreen_productHierarchyLookup");
		actionConfigList.add(reportProductHierarchyClosepopup);

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
		cancelButton.addComponentStyle(GtnFrameworkReportStringConstants.BUTTON_CUSTOM_STYLE_FOR_LESS_SPACE_BETWEEN_BUTTONS);
		cancelButton.setCustomReference(GtnFrameworkReportStringConstants.DO_NOT_ADD_BUTTON_CUSTOM_STYLE);
		cancelButton.addGtnUIFrameWorkActionConfig(reportProductHierarchyClosepopup);
		componentList.add(cancelButton);

		GtnUIFrameworkComponentConfig resetButton = new GtnUIFrameworkComponentConfig();
		resetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		resetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productHierarchyResetControlButton");
		resetButton.setComponentName("RESET");
		resetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		resetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> productHierarchyConfList = new ArrayList<>();
		GtnUIFrameWorkActionConfig productHierarchyResetActionConfig = new GtnUIFrameWorkActionConfig();
		productHierarchyResetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);

		List<Object> productHierarchyParamList = new ArrayList<>();
		productHierarchyParamList.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		productHierarchyParamList.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_TABLE_MESSAGE);

		productHierarchyParamList.add(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE));
		productHierarchyParamList.add(Arrays.asList(new Object[] { "" }));

		productHierarchyResetActionConfig.setActionParameterList(productHierarchyParamList);
		productHierarchyConfList.add(productHierarchyResetActionConfig);
		resetButton.setGtnUIFrameWorkActionConfigList(productHierarchyConfList);

		componentList.add(resetButton);
	}
}
