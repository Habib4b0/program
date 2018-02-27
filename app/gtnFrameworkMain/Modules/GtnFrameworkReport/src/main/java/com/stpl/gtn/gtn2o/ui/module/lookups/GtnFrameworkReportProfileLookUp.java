package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;

public class GtnFrameworkReportProfileLookUp {

	public GtnUIFrameworkViewConfig getReportProfileLookupView(String namespace) {
		GtnUIFrameworkViewConfig reportProfileLookUpView = new GtnUIFrameworkViewConfig();
		reportProfileLookUpView.setViewName("Report Profile Lookup");
		reportProfileLookUpView.setViewId("reportProfileLookupView");
		reportProfileLookUpView.setDefaultView(false);
		addReportProfileLookUpComponentList(reportProfileLookUpView, namespace);
		return reportProfileLookUpView;
	}

	private void addReportProfileLookUpComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> reportProfileLookUpComponentList = new ArrayList<>();
		view.setGtnComponentList(reportProfileLookUpComponentList);
		addReportProfileLookUpRootLayout(reportProfileLookUpComponentList, namespace);

	}

	private void addReportProfileLookUpRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProfileLookUpRootLayout = new GtnUIFrameworkComponentConfig();
		reportProfileLookUpRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProfileLookUpRootLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileLookUpRootLayout");
		reportProfileLookUpRootLayout.setAddToParent(false);
		reportProfileLookUpRootLayout.setSpacing(Boolean.TRUE);
		reportProfileLookUpRootLayout.setComponentWidth("100%");
		GtnUIFrameworkLayoutConfig reportProfileLookUpRootConfig = new GtnUIFrameworkLayoutConfig();
		reportProfileLookUpRootConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportProfileLookUpRootLayout.setGtnLayoutConfig(reportProfileLookUpRootConfig);
		componentList.add(reportProfileLookUpRootLayout);
		getReportProfileLookUpRootComponentForPopUp(componentList, namespace);
	}

	public void getReportProfileLookUpRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProfileLookUpRootComponentForPopUp = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig reportProfileLookUpRootComponentForPopupConfig = new GtnUIFrameworkLayoutConfig();
		reportProfileLookUpRootComponentForPopupConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		reportProfileLookUpRootComponentForPopUp.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProfileLookUpRootComponentForPopUp.setGtnLayoutConfig(reportProfileLookUpRootComponentForPopupConfig);
		reportProfileLookUpRootComponentForPopUp.setAddToParent(true);

		reportProfileLookUpRootComponentForPopUp.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProfileLookUpRootComponentForPopUp.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileLookUpRootLayout");
		reportProfileLookUpRootComponentForPopUp.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProfileLookUpRootComponentForPopUp.setSpacing(Boolean.TRUE);
		reportProfileLookUpRootComponentForPopUp.setMargin(Boolean.TRUE);
		componentList.add(reportProfileLookUpRootComponentForPopUp);

		addReportViewSearchCriteriaPanel(componentList, namespace);
		addSearchAndResetButtonLayout(componentList, namespace);
		resultsPanel(componentList, namespace);
		addControlPopUpButtonLayout(componentList, namespace);
	}

	private void addReportViewSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportViewSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		reportViewSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportViewSearchCriteriaPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportViewSearchCriteriaPanel");
		reportViewSearchCriteriaPanel.setComponentName("Report View Search");
		reportViewSearchCriteriaPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportViewSearchCriteriaPanel.setMargin(Boolean.TRUE);
		reportViewSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportViewSearchCriteriaPanel.setAddToParent(true);
		componentList.add(reportViewSearchCriteriaPanel);
		reportViewSearchCriteriaLayout(componentList, namespace);
	}

	private void reportViewSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportViewSearchCriteriaLayout = new GtnUIFrameworkComponentConfig();
		reportViewSearchCriteriaLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportViewSearchCriteriaLayout.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_VIEW_SEARCH_CRITERIA_LAYOUT);
		reportViewSearchCriteriaLayout.setComponentName("Search Criteria");
		reportViewSearchCriteriaLayout.setAddToParent(true);
		reportViewSearchCriteriaLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportViewSearchCriteriaLayout.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportViewSearchCriteriaPanel");
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		reportViewSearchCriteriaLayout.setGtnLayoutConfig(conf);
		reportViewSearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		reportViewSearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		componentList.add(reportViewSearchCriteriaLayout);

		addViewTypeOptionGroup(componentList, namespace);
		addViewNameTextBox(componentList, namespace);
	}

	private void addViewTypeOptionGroup(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig viewTypeOptionGroupConfig = new GtnUIFrameworkComponentConfig();
		viewTypeOptionGroupConfig.setComponentType(GtnUIFrameworkComponentType.OPTIONGROUP);
		viewTypeOptionGroupConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "viewType");
		viewTypeOptionGroupConfig.setComponentName("View Type");
		viewTypeOptionGroupConfig.setAddToParent(true);
		viewTypeOptionGroupConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_VIEW_SEARCH_CRITERIA_LAYOUT);
		GtnUIFrameworkOptionGroupConfig viewTypeOptionGroupLoadConfig = new GtnUIFrameworkOptionGroupConfig();
		viewTypeOptionGroupLoadConfig.setItemValues(Arrays.asList("Public", "Private"));
		viewTypeOptionGroupLoadConfig.setValuesFromService(Boolean.FALSE);

		viewTypeOptionGroupConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
		viewTypeOptionGroupConfig.setGtnUIFrameworkOptionGroupConfig(viewTypeOptionGroupLoadConfig);
		componentList.add(viewTypeOptionGroupConfig);
	}

	private void addViewNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig viewNameTextBoxConfig = new GtnUIFrameworkComponentConfig();
		viewNameTextBoxConfig.setComponentType(GtnUIFrameworkComponentType.TEXTBOX);
		viewNameTextBoxConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "viewName");
		viewNameTextBoxConfig.setComponentName("View Name");
		viewNameTextBoxConfig.setAddToParent(true);
		viewNameTextBoxConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_VIEW_SEARCH_CRITERIA_LAYOUT);
		componentList.add(viewNameTextBoxConfig);
	}

	private void addSearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProfileSearchAndResetButtonLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		reportProfileSearchAndResetButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProfileSearchAndResetButtonLayout.setAddToParent(true);
		reportProfileSearchAndResetButtonLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProfileSearchAndResetButtonLayout.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PROFILE_SEARCH_AND_RESET_BUTTON_LAYOUT);
		reportProfileSearchAndResetButtonLayout.setSpacing(Boolean.TRUE);
		reportProfileSearchAndResetButtonLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(reportProfileSearchAndResetButtonLayout);

		GtnUIFrameworkComponentConfig reportProfileSearchButton = new GtnUIFrameworkComponentConfig();
		reportProfileSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportProfileSearchButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileSearchButton");
		reportProfileSearchButton.setComponentName("SEARCH");
		reportProfileSearchButton.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PROFILE_SEARCH_AND_RESET_BUTTON_LAYOUT);
		reportProfileSearchButton.setAddToParent(true);

		componentList.add(reportProfileSearchButton);

		GtnUIFrameworkComponentConfig reportProfileResetButton = new GtnUIFrameworkComponentConfig();
		reportProfileResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportProfileResetButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileResetButton");
		reportProfileResetButton.setComponentName("RESET");
		reportProfileResetButton.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PROFILE_SEARCH_AND_RESET_BUTTON_LAYOUT);
		reportProfileResetButton.setAddToParent(true);

		componentList.add(reportProfileResetButton);
	}

	private void resultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProfileResultPanel = new GtnUIFrameworkComponentConfig();
		reportProfileResultPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		reportProfileResultPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileResultPanel");
		reportProfileResultPanel.setComponentName("Results");
		reportProfileResultPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProfileResultPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProfileResultPanel.setAddToParent(true);
		componentList.add(reportProfileResultPanel);
		resultLayout(componentList, namespace);

	}

	private void resultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		conf.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig reportProfileResultLayout = new GtnUIFrameworkComponentConfig();
		reportProfileResultLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProfileResultLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileResultLayout");

		reportProfileResultLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileResultPanel");
		reportProfileResultLayout.setAddToParent(true);
		reportProfileResultLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProfileResultLayout.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		reportProfileResultLayout.setGtnLayoutConfig(conf);
		componentList.add(reportProfileResultLayout);
		addReportProfilePagedTableComponent(componentList, namespace);
	}

	private void addReportProfilePagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig reportProfilePagedTableComponent = new GtnUIFrameworkComponentConfig();
		reportProfilePagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGEDTABLE);
		reportProfilePagedTableComponent.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfilePagedTableComponent");
		reportProfilePagedTableComponent.setComponentName("Results");
		reportProfilePagedTableComponent
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileResultLayout");
		reportProfilePagedTableComponent.setAddToParent(true);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add("filterbar");
		tableStyle.add("v-has-width");
		tableStyle.add("v-table-filterbar");
		tableStyle.add("table-header-normal");
		reportProfilePagedTableComponent.setComponentWidth("100%");
		reportProfilePagedTableComponent.setComponentStyle(tableStyle);

		componentList.add(reportProfilePagedTableComponent);

		GtnUIFrameworkPagedTableConfig reportProfilePagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		reportProfilePagedTableConfig.setEditable(false);
		reportProfilePagedTableConfig.setFilterBar(true);
		reportProfilePagedTableConfig.setPageLength(10);
		reportProfilePagedTableConfig.setItemPerPage(10);
		reportProfilePagedTableConfig.setSelectable(true);
		reportProfilePagedTableConfig.setSinkItemPerPageWithPageLength(false);

		reportProfilePagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_LANG_STRING });

		reportProfilePagedTableConfig
				.setTableVisibleHeader(new String[] { "View Name", "Created Date", "Modified Date", "Created By" });
		reportProfilePagedTableConfig
				.setTableColumnMappingId(new Object[] { "viewName", "createdDate", "modifiedDate", "createdBy" });

		reportProfilePagedTableComponent.setGtnPagedTableConfig(reportProfilePagedTableConfig);
	}

	private void addControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig reportProfileControlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		reportProfileControlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		reportProfileControlPopUpLayout.setAddToParent(true);
		reportProfileControlPopUpLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		reportProfileControlPopUpLayout.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PROFILE_CONTROL_POPUP_BUTTON_LAYOUT);
		reportProfileControlPopUpLayout.setSpacing(Boolean.TRUE);
		reportProfileControlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(reportProfileControlPopUpLayout);

		GtnUIFrameworkComponentConfig reportProfileSelectButton = new GtnUIFrameworkComponentConfig();
		reportProfileSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportProfileSelectButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileSelectButton");
		reportProfileSelectButton.setComponentName("SELECT");
		reportProfileSelectButton.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PROFILE_CONTROL_POPUP_BUTTON_LAYOUT);
		reportProfileSelectButton.setAddToParent(true);

		componentList.add(reportProfileSelectButton);

		GtnUIFrameworkComponentConfig reportProfileCancelButton = new GtnUIFrameworkComponentConfig();
		reportProfileCancelButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportProfileCancelButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileCancelButton");
		reportProfileCancelButton.setComponentName("DELETE");
		reportProfileCancelButton.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PROFILE_CONTROL_POPUP_BUTTON_LAYOUT);
		reportProfileCancelButton.setAddToParent(true);

		componentList.add(reportProfileCancelButton);

		GtnUIFrameworkComponentConfig reportProfileResetButton = new GtnUIFrameworkComponentConfig();
		reportProfileResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		reportProfileResetButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "reportProfileResetButton");
		reportProfileResetButton.setComponentName("CLOSE");
		reportProfileResetButton.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkReportStringConstants.REPORT_PROFILE_CONTROL_POPUP_BUTTON_LAYOUT);
		reportProfileResetButton.setAddToParent(true);

		componentList.add(reportProfileResetButton);
	}
}
