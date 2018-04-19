package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkReportPrivateViewSearchLookUp {

	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	public GtnUIFrameworkViewConfig getPrivateViewLookUpView(String namespace) {

		GtnUIFrameworkViewConfig privateViewSearchLookupView = new GtnUIFrameworkViewConfig();
		privateViewSearchLookupView.setViewName("Private View");
		privateViewSearchLookupView.setViewId(GtnFrameworkCommonConstants.PRIVATE_VIEW_SEARCH_LOOKUP_VIEW);
		privateViewSearchLookupView.setDefaultView(false);
		addPrivateViewSearchLookupComponentList(privateViewSearchLookupView, namespace);
		return privateViewSearchLookupView;
	}

	private void addPrivateViewSearchLookupComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> privateViewSearchLookupComponentList = new ArrayList<>();
		view.setGtnComponentList(privateViewSearchLookupComponentList);
		addPrivateViewSearchLookupRootLayout(privateViewSearchLookupComponentList, namespace);

	}

	private void addPrivateViewSearchLookupRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {

		GtnUIFrameworkComponentConfig privateViewSearchLookupRootLayout = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupRootLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupRootLayout");
		privateViewSearchLookupRootLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privateViewSearchLookupRootLayout.setAddToParent(false);
		privateViewSearchLookupRootLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig privateViewSearchLookupRootConfig = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupRootConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		privateViewSearchLookupRootLayout.setGtnLayoutConfig(privateViewSearchLookupRootConfig);
		componentList.add(privateViewSearchLookupRootLayout);
		getPrivateViewSearchLookupRootComponentForPopUp(componentList, namespace);
	}

	public void getPrivateViewSearchLookupRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupRootComponentForPopUp = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig privateViewSearchLookupRootComponentConfig = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupRootComponentConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		privateViewSearchLookupRootComponentForPopUp.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupRootComponentForPopUp.setGtnLayoutConfig(privateViewSearchLookupRootComponentConfig);
		privateViewSearchLookupRootComponentForPopUp.setAddToParent(true);
		privateViewSearchLookupRootComponentForPopUp.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privateViewSearchLookupRootComponentForPopUp.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupRootComponentForPopUp.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupRootLayout");
		privateViewSearchLookupRootComponentForPopUp.setSpacing(true);
		privateViewSearchLookupRootComponentForPopUp.setMargin(true);
		componentList.add(privateViewSearchLookupRootComponentForPopUp);
		
		privateViewSearchLookupSearchCriteriaPanel(componentList, namespace);
		privateViewSearchLookupSearchAndResetButtonLayout(componentList, namespace);
		privateViewSearchLookupResultsPanel(componentList, namespace);
		addPrivateViewSearchLookupControlPopUpButtonLayout(componentList, namespace);
	}

	private void privateViewSearchLookupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		privateViewSearchLookupSearchCriteriaPanel
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchCriteriaPanel");
		privateViewSearchLookupSearchCriteriaPanel.setComponentName("Search Criteria");
		privateViewSearchLookupSearchCriteriaPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privateViewSearchLookupSearchCriteriaPanel.setAddToParent(true);
		componentList.add(privateViewSearchLookupSearchCriteriaPanel);
		privateViewSearchLookupSearchCriteriaLayout(componentList, namespace);
	}

	private void privateViewSearchLookupSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig privateViewSearchLookupSearchCriteriaLayout = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupSearchCriteriaLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig privateViewSearchLookupSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupSearchCriteriaConfig
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchCriteriaLayout");
		privateViewSearchLookupSearchCriteriaConfig.setComponentName("Search Criteria");
		privateViewSearchLookupSearchCriteriaConfig.setAddToParent(true);
		privateViewSearchLookupSearchCriteriaConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privateViewSearchLookupSearchCriteriaConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchCriteriaPanel");
		privateViewSearchLookupSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		privateViewSearchLookupSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		privateViewSearchLookupSearchCriteriaConfig.setGtnLayoutConfig(privateViewSearchLookupSearchCriteriaLayout);
		componentList.add(privateViewSearchLookupSearchCriteriaConfig);
		addHierarchyNameTextBox(componentList, namespace);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig horizontalViewNameLayout = layoutsConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "horizontalViewNameLayout",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchCriteriaLayout");
		componentList.add(horizontalViewNameLayout);

		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		addHierarchyNameTextBox.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME);
		addHierarchyNameTextBox.setComponentName("View Name");
		addHierarchyNameTextBox.setAddToParent(true);
		addHierarchyNameTextBox.setParentComponentId(horizontalViewNameLayout.getComponentId());
		addHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME);

		GtnUIFrameworkValidationConfig valConfigForViewName = new GtnUIFrameworkValidationConfig();
		valConfigForViewName.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		addHierarchyNameTextBox.setGtnUIFrameworkValidationConfig(valConfigForViewName);

		componentList.add(addHierarchyNameTextBox);

	}

	private void privateViewSearchLookupSearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupSearchAndResetLayout.setAddToParent(true);
		privateViewSearchLookupSearchAndResetLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupSearchAndResetLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		privateViewSearchLookupSearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(privateViewSearchLookupSearchAndResetLayout);

		GtnUIFrameworkComponentConfig privateViewSearchLookupSearchButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupSearchButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchButton");
		privateViewSearchLookupSearchButton.setComponentName("SEARCH");
		privateViewSearchLookupSearchButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		privateViewSearchLookupSearchButton.setAddToParent(true);

		componentList.add(privateViewSearchLookupSearchButton);

		GtnUIFrameworkComponentConfig privateViewSearchLookupResetButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResetButton");
		privateViewSearchLookupResetButton.setComponentName("RESET");
		privateViewSearchLookupResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		privateViewSearchLookupResetButton.setAddToParent(true);
		componentList.add(privateViewSearchLookupResetButton);
	}

	private void privateViewSearchLookupResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupResultsPanel = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		privateViewSearchLookupResultsPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultsPanel");
		privateViewSearchLookupResultsPanel.setComponentName("Results");
		privateViewSearchLookupResultsPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privateViewSearchLookupResultsPanel.setAddToParent(true);
		componentList.add(privateViewSearchLookupResultsPanel);
		privateViewSearchLookupResultLayout(componentList, namespace);

	}

	private void privateViewSearchLookupResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig privateViewSearchLookupResultLayout = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig privateViewSearchLookupResultConfig = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupResultConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupResultConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultConfig");

		privateViewSearchLookupResultConfig.setGtnLayoutConfig(privateViewSearchLookupResultLayout);
		privateViewSearchLookupResultConfig
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultsPanel");
		privateViewSearchLookupResultConfig.setAddToParent(true);
		privateViewSearchLookupResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privateViewSearchLookupResultConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(privateViewSearchLookupResultConfig);
		addPrivateViewPagedTableComponent(componentList, namespace);
	}

	private void addPrivateViewPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig privateViewPagedTableComponent = new GtnUIFrameworkComponentConfig();
		privateViewPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		privateViewPagedTableComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		privateViewPagedTableComponent.setComponentName("Results");
		privateViewPagedTableComponent
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultConfig");
		privateViewPagedTableComponent.setAddToParent(true);
		privateViewPagedTableComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		privateViewPagedTableComponent.setComponentStyle(tableStyle);

		componentList.add(privateViewPagedTableComponent);
		GtnUIFrameworkPagedTableConfig privateViewPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		privateViewPagedTableConfig.setEditable(false);
		privateViewPagedTableConfig.setFilterBar(true);
		privateViewPagedTableConfig.setSelectable(true);
		privateViewPagedTableConfig.setPageLength(10);
		privateViewPagedTableConfig.setItemPerPage(10);

		privateViewPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		privateViewPagedTableConfig.setTableVisibleHeader(new String[] { "View Name", "Description", "Time Period:From",
				"Time Period:To", "Company", GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_HEADER,
				GtnFrameworkCommonConstants.PRODUCT_LEVEL_HEADER, "Product Group",
				GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER,
				GtnFrameworkCommonConstants.CREATED_BY_HEADER, GtnFrameworkCommonConstants.BUSINESS_UNIT_HEADER });
		privateViewPagedTableConfig.setTableColumnMappingId(
				new Object[] { "viewNameFilter", "viewDescriptionFilter", "fromTimePeriodFilter", "toTimePeriodFilter",
						"companyFilter", "productHierarchyFilter", "productLevelFilter", "productGroupFilter",
						"createdDateFilter", "modifiedByFilter", "createdByFilter", "businessUnitFilter" });

		privateViewPagedTableComponent.setGtnPagedTableConfig(privateViewPagedTableConfig);

	}

	private void addPrivateViewSearchLookupControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupControlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupControlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupControlPopUpLayout.setAddToParent(true);
		privateViewSearchLookupControlPopUpLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupControlPopUpLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		privateViewSearchLookupControlPopUpLayout.setSpacing(true);
		privateViewSearchLookupControlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(privateViewSearchLookupControlPopUpLayout);

		GtnUIFrameworkComponentConfig privateViewSearchLookupSelectButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupSelectButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSelectButton");
		privateViewSearchLookupSelectButton.setComponentName("SELECT");
		privateViewSearchLookupSelectButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		privateViewSearchLookupSelectButton.setAddToParent(true);

		componentList.add(privateViewSearchLookupSelectButton);

		GtnUIFrameworkComponentConfig privateViewSearchLookupCloseButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupCloseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupCloseButton.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupCloseButton");
		privateViewSearchLookupCloseButton.setComponentName("CLOSE");
		privateViewSearchLookupCloseButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		privateViewSearchLookupCloseButton.setAddToParent(true);
		componentList.add(privateViewSearchLookupCloseButton);

	}
}
