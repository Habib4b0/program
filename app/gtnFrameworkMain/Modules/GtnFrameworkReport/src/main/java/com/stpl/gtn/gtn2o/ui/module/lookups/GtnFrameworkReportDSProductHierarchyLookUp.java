package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;

public class GtnFrameworkReportDSProductHierarchyLookUp {

	public GtnUIFrameworkViewConfig getProdHierarchyLookUpView(String namespace) {
		GtnUIFrameworkViewConfig dataSelectionProductHierarchyView = new GtnUIFrameworkViewConfig();
		dataSelectionProductHierarchyView.setViewName("Product Hierarchy LookUp");
		dataSelectionProductHierarchyView.setViewId("dataSelectionTab_productHierarchyLookup");
		dataSelectionProductHierarchyView.setDefaultView(false);
		addReportProductHierarchyLookUpComponentList(dataSelectionProductHierarchyView, namespace);
		return dataSelectionProductHierarchyView;
	}

	private void addReportProductHierarchyLookUpComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> dsProductHierarchyLookupcomponentList = new ArrayList<>();
		view.setGtnComponentList(dsProductHierarchyLookupcomponentList);
		addReportProductHierarchyLookUpRootLayout(dsProductHierarchyLookupcomponentList, namespace);

	}

	private void addReportProductHierarchyLookUpRootLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsProductHierarchyLookUpRootLayout = new GtnUIFrameworkComponentConfig();
		reportDsProductHierarchyLookUpRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDsProductHierarchyLookUpRootLayout.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpRootLayout");
		reportDsProductHierarchyLookUpRootLayout.setAddToParent(false);
		reportDsProductHierarchyLookUpRootLayout.setSpacing(true);
		reportDsProductHierarchyLookUpRootLayout.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportDsProductHierarchyLookUpRootLayout.setGtnLayoutConfig(conf);
		componentList.add(reportDsProductHierarchyLookUpRootLayout);
		getReportProductHierarchyLookUpRootComponent(componentList, namespace);
	}

	public void getReportProductHierarchyLookUpRootComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsProductHierarchyLookUpRootConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig reportDsProductHierarchyLookUpRootLayout = new GtnUIFrameworkLayoutConfig();
		reportDsProductHierarchyLookUpRootLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportDsProductHierarchyLookUpRootConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDsProductHierarchyLookUpRootConfig.setGtnLayoutConfig(reportDsProductHierarchyLookUpRootLayout);
		reportDsProductHierarchyLookUpRootConfig.setAddToParent(true);

		reportDsProductHierarchyLookUpRootConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportDsProductHierarchyLookUpRootConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpRootLayout");
		reportDsProductHierarchyLookUpRootConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDsProductHierarchyLookUpRootConfig.setSpacing(true);
		reportDsProductHierarchyLookUpRootConfig.setMargin(true);
		componentList.add(reportDsProductHierarchyLookUpRootConfig);
		reportProductHierarchyLookUpSearchCriteriaPanel(componentList, namespace);
		productHierarchySearchAndResetButtonLayout(componentList, namespace);
		reportProductHierarchyLookUpResultsPanel(componentList, namespace);
		addreportProductHierarchyControlPopUpButtonLayout(componentList, namespace);
	}

	private void reportProductHierarchyLookUpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsProductHierarchyLookUpSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		reportDsProductHierarchyLookUpSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportDsProductHierarchyLookUpSearchCriteriaPanel.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpSearchCriteriaPanel");
		reportDsProductHierarchyLookUpSearchCriteriaPanel.setComponentName("Search Criteria");
		reportDsProductHierarchyLookUpSearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportDsProductHierarchyLookUpSearchCriteriaPanel.setMargin(true);
		reportDsProductHierarchyLookUpSearchCriteriaPanel
				.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDsProductHierarchyLookUpSearchCriteriaPanel.setAddToParent(true);
		componentList.add(reportDsProductHierarchyLookUpSearchCriteriaPanel);
		reportProductHierarchyLookUpSearchCriteriaLayout(componentList, namespace);
	}

	private void reportProductHierarchyLookUpSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportDsProductHierarchyLookUpSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		reportDsProductHierarchyLookUpSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportDsProductHierarchyLookUpSearchCriteriaConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_LOOKUP_SEARCH_CRITERIA_LAYOUT);
		reportDsProductHierarchyLookUpSearchCriteriaConfig.setComponentName("Search Criteria");
		reportDsProductHierarchyLookUpSearchCriteriaConfig.setAddToParent(true);
		reportDsProductHierarchyLookUpSearchCriteriaConfig
				.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportDsProductHierarchyLookUpSearchCriteriaConfig.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "reportProductHierarchyLookUpSearchCriteriaPanel");
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		reportDsProductHierarchyLookUpSearchCriteriaConfig.setGtnLayoutConfig(layoutConf);
		reportDsProductHierarchyLookUpSearchCriteriaConfig
				.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		reportDsProductHierarchyLookUpSearchCriteriaConfig
				.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(reportDsProductHierarchyLookUpSearchCriteriaConfig);
		addHierarchyTypeOptionGroup(componentList, namespace);
		addHierarchyNameTextBox(componentList, namespace);
	}

	private void addHierarchyTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig productHierarchyTypeOptionGroup = new GtnUIFrameworkComponentConfig();
		productHierarchyTypeOptionGroup.setComponentType(GtnUIFrameworkComponentType.RADIOBUTTON_VAADIN8);
		productHierarchyTypeOptionGroup.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		productHierarchyTypeOptionGroup.setComponentName("Hierarchy Type");
		productHierarchyTypeOptionGroup.setAddToParent(true);
		productHierarchyTypeOptionGroup.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_LOOKUP_SEARCH_CRITERIA_LAYOUT);
		GtnUIFrameworkOptionGroupConfig comboConfig = new GtnUIFrameworkOptionGroupConfig();
		comboConfig.setItemValues(Arrays.asList("Primary", "Secondary"));
		comboConfig.setValuesFromService(false);
		productHierarchyTypeOptionGroup.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_TYPE);
		productHierarchyTypeOptionGroup.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		productHierarchyTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(comboConfig);
		componentList.add(productHierarchyTypeOptionGroup);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig productHierarchyName = new GtnUIFrameworkComponentConfig();
		productHierarchyName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		productHierarchyName.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME);
		productHierarchyName.setComponentName("Hierarchy Name");
		productHierarchyName.setAddToParent(true);
		productHierarchyName.setComponentWsFieldId(GtnFrameworkCommonConstants.HIERARCHY_NAME);
		productHierarchyName.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_LOOKUP_SEARCH_CRITERIA_LAYOUT);
		componentList.add(productHierarchyName);

		GtnUIFrameworkValidationConfig productHierarchyNameValidationConfig = new GtnUIFrameworkValidationConfig();
		productHierarchyNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		productHierarchyName.setGtnUIFrameworkValidationConfig(productHierarchyNameValidationConfig);

		componentList.add(productHierarchyName);
	}

	private void productHierarchySearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig productDsHierarchySearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		productDsHierarchySearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		productDsHierarchySearchAndResetLayout.setAddToParent(true);
		productDsHierarchySearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		productDsHierarchySearchAndResetLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		productDsHierarchySearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		productDsHierarchySearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(productDsHierarchySearchAndResetLayout);

		GtnUIFrameworkComponentConfig dsProductHierarchySearchButton = new GtnUIFrameworkComponentConfig();
		dsProductHierarchySearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		dsProductHierarchySearchButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productHierarchySearchButton");
		dsProductHierarchySearchButton.setComponentName("SEARCH");
		dsProductHierarchySearchButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		dsProductHierarchySearchButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig dsProductValidationActionConfig = new GtnUIFrameWorkActionConfig();
		dsProductValidationActionConfig.setActionType(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);

		dsProductValidationActionConfig.setFieldValues(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME));

		GtnUIFrameWorkActionConfig productTableLoadConfig = new GtnUIFrameWorkActionConfig();
		productTableLoadConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);

		GtnUIFrameWorkActionConfig dsProductAlertActionConfig = new GtnUIFrameWorkActionConfig();
		dsProductAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> dsProductAlertParamsList = new ArrayList<>();
		dsProductAlertParamsList.add("No Results Found");
		dsProductAlertParamsList.add("There are no Hierarchies that match the search criteria.");

		dsProductAlertActionConfig.setActionParameterList(dsProductAlertParamsList);
		Object dsProductValidationType = GtnUIFrameworkValidationType.OR;
		dsProductValidationActionConfig.setActionParameterList(
				Arrays.asList(dsProductValidationType, Arrays.asList(dsProductAlertActionConfig)));
		actionConfigList.add(dsProductValidationActionConfig);

		productTableLoadConfig
				.setFieldValues(Arrays.asList(new String[] {
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkCommonConstants.HIERARCHY_TYPE,
						namespace + GtnFrameworkReportStringConstants.UNDERSCORE
								+ GtnFrameworkCommonConstants.HIERARCHY_NAME }));

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);

		productTableLoadConfig.setActionParameterList(actionParams);

		actionConfigList.add(productTableLoadConfig);
		dsProductHierarchySearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		componentList.add(dsProductHierarchySearchButton);

		GtnUIFrameworkComponentConfig dsProductHierarchyResetButton = new GtnUIFrameworkComponentConfig();
		dsProductHierarchyResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		dsProductHierarchyResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productHierarchyResetButton");
		dsProductHierarchyResetButton.setComponentName("RESET");
		dsProductHierarchyResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.SEARCH_AND_RESET_BUTTON_LAYOUT);
		dsProductHierarchyResetButton.setAddToParent(true);

		componentList.add(dsProductHierarchyResetButton);
		List<GtnUIFrameWorkActionConfig> dsProductHierarchyResetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig dsProductHierarchyResetActionConfig = new GtnUIFrameWorkActionConfig();
		dsProductHierarchyResetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);

		List<Object> dsProductHierarchyParams = new ArrayList<>();
		dsProductHierarchyParams.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		dsProductHierarchyParams.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_MESSAGE);
		dsProductHierarchyParams.add(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.HIER_TYPE,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.HIERARCHY_NAME));
		dsProductHierarchyParams
				.add(Arrays.asList(new Object[] { "Primary", GtnFrameworkCommonStringConstants.STRING_EMPTY }));
		dsProductHierarchyResetActionConfig.setActionParameterList(dsProductHierarchyParams);
		dsProductHierarchyResetActionConfigList.add(dsProductHierarchyResetActionConfig);
		dsProductHierarchyResetButton.setGtnUIFrameWorkActionConfigList(dsProductHierarchyResetActionConfigList);

	}

	private void reportProductHierarchyLookUpResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig searchResultsPanel = new GtnUIFrameworkComponentConfig();
		searchResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		searchResultsPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "resultsPanel");
		searchResultsPanel.setComponentName("Results");
		searchResultsPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		searchResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		searchResultsPanel.setAddToParent(true);
		componentList.add(searchResultsPanel);
		resultLayout(componentList, namespace);

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig searchResultLayout = new GtnUIFrameworkLayoutConfig();
		searchResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig resultsInnerLayout = new GtnUIFrameworkComponentConfig();
		resultsInnerLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		resultsInnerLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "resultLayout");

		resultsInnerLayout
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "resultsPanel");
		resultsInnerLayout.setAddToParent(true);
		resultsInnerLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultsInnerLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		resultsInnerLayout.setGtnLayoutConfig(searchResultLayout);
		componentList.add(resultsInnerLayout);
		addProductHierarchyPagedTableComponent(componentList, namespace);
	}

	private void addProductHierarchyPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig searchResultsTableConfig = new GtnUIFrameworkComponentConfig();
		searchResultsTableConfig.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		searchResultsTableConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE);
		searchResultsTableConfig.setComponentName("Results");
		searchResultsTableConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "resultLayout");
		searchResultsTableConfig.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		searchResultsTableConfig.setComponentWidth("100%");
		searchResultsTableConfig.setComponentHight("450px");
		searchResultsTableConfig.setComponentStyle(tableStyle);
		searchResultsTableConfig.setModuleName("report");
		componentList.add(searchResultsTableConfig);
		GtnUIFrameworkPagedTableConfig productSearchResults = new GtnUIFrameworkPagedTableConfig();

		productSearchResults.setColumnHeaders(Arrays.asList("HierarchyName", "Highest Level", "Lowest Level",
				GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER));
		productSearchResults.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE });
		productSearchResults.setTableColumnMappingId(new Object[] { GtnFrameworkReportStringConstants.HIER_NAME,
				GtnFrameworkReportStringConstants.HIGHEST_LEVEL, "lowestLevel", "createdDate", "modifiedDate" });

		productSearchResults.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_PRODUCTHIERARCHY_SEARCHSERVICE);
		productSearchResults.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_PRODUCTHIERARCHY_SEARCHSERVICE);
		productSearchResults.setCustomFilterConfigMap(getCustomFilterConfig());
		searchResultsTableConfig.setGtnPagedTableConfig(productSearchResults);
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
		GtnUIFrameworkComponentConfig actionButtonLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig actionButtonLayoutConfig = new GtnUIFrameworkLayoutConfig();
		actionButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		actionButtonLayout.setAddToParent(true);
		actionButtonLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		actionButtonLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		actionButtonLayout.setSpacing(true);
		actionButtonLayout.setGtnLayoutConfig(actionButtonLayoutConfig);
		componentList.add(actionButtonLayout);

		GtnUIFrameworkComponentConfig productSelectBtn = new GtnUIFrameworkComponentConfig();
		productSelectBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		productSelectBtn.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_SELECT_BUTTON);
		productSelectBtn.setComponentName("SELECT");
		productSelectBtn.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		productSelectBtn.setAddToParent(true);
		productSelectBtn.addDependentComponent("dataSelectionTab_relationship");
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig productSelectAction = new GtnUIFrameWorkActionConfig();
		productSelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add("reportDsProductHierarchyLookup_productHierarchySearchResultTable");
		actionParameter.add("dataSelectionTab_producthierarchy");
		actionParameter.add(Arrays.asList(GtnFrameworkReportStringConstants.HIER_NAME));
		actionParameter.add(Arrays.asList("dataSelectionTab_producthierarchy"));

		productSelectAction.setActionParameterList(actionParameter);
		actionConfigList.add(productSelectAction);

		GtnUIFrameWorkActionConfig reportDsProductHierarchyClosepopup = new GtnUIFrameWorkActionConfig();
		reportDsProductHierarchyClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		reportDsProductHierarchyClosepopup.addActionParameter("productHierarchyLookup");
		actionConfigList.add(reportDsProductHierarchyClosepopup);

		productSelectBtn.setGtnUIFrameWorkActionConfigList(actionConfigList);
		componentList.add(productSelectBtn);

		GtnUIFrameworkComponentConfig productCancelBtn = new GtnUIFrameworkComponentConfig();
		productCancelBtn.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		productCancelBtn.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productHierarchyCancelButton");
		productCancelBtn.setComponentName("CANCEL");
		productCancelBtn.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		productCancelBtn.setAddToParent(true);

		componentList.add(productCancelBtn);

		GtnUIFrameworkComponentConfig dsResetButton = new GtnUIFrameworkComponentConfig();
		dsResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		dsResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "productHierarchyResetControlButton");
		dsResetButton.setComponentName("RESET");
		dsResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		dsResetButton.setAddToParent(true);

		List<GtnUIFrameWorkActionConfig> dsProductHierarchyConfList = new ArrayList<>();
		GtnUIFrameWorkActionConfig dsProductHierarchyResetActionConfig = new GtnUIFrameWorkActionConfig();
		dsProductHierarchyResetActionConfig.setActionType(GtnUIFrameworkActionType.V8_RESET_ACTION);

		List<Object> dsProductHierarchyParamList = new ArrayList<>();
		dsProductHierarchyParamList.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		dsProductHierarchyParamList.add(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_TABLE_MESSAGE);

		dsProductHierarchyParamList.add(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.REPORT_PRODUCT_HIERARCHY_SEARCH_RESULT_TABLE));
		dsProductHierarchyParamList.add(Arrays.asList(new Object[] { "" }));

		dsProductHierarchyResetActionConfig.setActionParameterList(dsProductHierarchyParamList);
		dsProductHierarchyConfList.add(dsProductHierarchyResetActionConfig);
		dsResetButton.setGtnUIFrameWorkActionConfigList(dsProductHierarchyConfList);

		componentList.add(dsResetButton);
	}
}
