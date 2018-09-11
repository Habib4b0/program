package com.stpl.gtn.gtn2o.ui.module.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkSelectButtonEnableActionInHierarchyLookup;
import com.stpl.gtn.gtn2o.ui.action.GtnReportDataSelectionLoadViewAction;
import com.stpl.gtn.gtn2o.ui.config.GtnFrameworkReportLayoutsConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.textbox.GtnUIFrameworkTextBoxConfig;
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

public class GtnFrameworkReportPrivateViewSearchLookUp {

	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	private static final String[] columnPropertyIds = { "viewNameFilter", "createdDateFilter", "modifiedDateFilter",
			"createdByFilter" };
	private static final GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.DATEFIELDVAADIN8, GtnUIFrameworkComponentType.DATEFIELDVAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };

	public GtnUIFrameworkViewConfig getPrivateViewLookUpView(String namespace) {

		GtnUIFrameworkViewConfig privateViewSearchLookupView = new GtnUIFrameworkViewConfig();
		privateViewSearchLookupView.setViewName("Private View");
		privateViewSearchLookupView.setViewId(GtnFrameworkCommonConstants.PRIVATE_VIEW_SEARCH_LOOKUP_VIEW);
		privateViewSearchLookupView.setDefaultView(false);
		privateViewSearchLookupView.setResetAllowed(true);
		addPrivateViewSearchLookupComponentList(privateViewSearchLookupView, namespace);
		return privateViewSearchLookupView;
	}

	private void addPrivateViewSearchLookupComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> privateViewSearchLookupComponentList = new ArrayList<>();
		view.setGtnComponentList(privateViewSearchLookupComponentList);
		addPrivateViewSearchLookupRootLayout(privateViewSearchLookupComponentList, namespace);

	}

	private void addPrivateViewSearchLookupRootLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig privateViewSearchLookupRootLayout = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupRootLayout.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupRootLayout");
		privateViewSearchLookupRootLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privateViewSearchLookupRootLayout.setAddToParent(false);
		privateViewSearchLookupRootLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig privateViewSearchLookupRootConfig = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupRootConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		privateViewSearchLookupRootLayout.setGtnLayoutConfig(privateViewSearchLookupRootConfig);
		componentList.add(privateViewSearchLookupRootLayout);
		getPrivateViewSearchLookupRootComponentForPopUp(componentList, namespace);
	}

	public void getPrivateViewSearchLookupRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupRootComponentForPopUp = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig privateViewSearchLookupRootComponentConfig = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupRootComponentConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		privateViewSearchLookupRootComponentForPopUp.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupRootComponentForPopUp.setGtnLayoutConfig(privateViewSearchLookupRootComponentConfig);
		privateViewSearchLookupRootComponentForPopUp.setAddToParent(true);
		privateViewSearchLookupRootComponentForPopUp.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privateViewSearchLookupRootComponentForPopUp.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupRootComponentForPopUp.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupRootLayout");
		privateViewSearchLookupRootComponentForPopUp.setSpacing(true);
		privateViewSearchLookupRootComponentForPopUp.setMargin(true);
		componentList.add(privateViewSearchLookupRootComponentForPopUp);

		privateViewSearchLookupSearchCriteriaPanel(componentList, namespace);
		privateViewSearchLookupSearchAndResetButtonLayout(componentList, namespace);
		privateViewSearchLookupResultsPanel(componentList, namespace);
		addPrivateViewSearchLookupControlPopUpButtonLayout(componentList, namespace);
	}

	private void privateViewSearchLookupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		privateViewSearchLookupSearchCriteriaPanel.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchCriteriaPanel");
		privateViewSearchLookupSearchCriteriaPanel.setComponentName("Search Criteria");
		privateViewSearchLookupSearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privateViewSearchLookupSearchCriteriaPanel.setAddToParent(true);
		privateViewSearchLookupSearchCriteriaPanel.addComponentStyle("stpl-margin-left-10");
		privateViewSearchLookupSearchCriteriaPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(privateViewSearchLookupSearchCriteriaPanel);
		privateViewSearchLookupSearchCriteriaLayout(componentList, namespace);
	}

	private void privateViewSearchLookupSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig privateViewSearchLookupSearchCriteriaLayout = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupSearchCriteriaLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig privateViewSearchLookupSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupSearchCriteriaConfig.setComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchCriteriaLayout");
		privateViewSearchLookupSearchCriteriaConfig.setComponentName("Search Criteria");
		privateViewSearchLookupSearchCriteriaConfig.setAddToParent(true);
		privateViewSearchLookupSearchCriteriaConfig.setComponentWidth("90%");
		privateViewSearchLookupSearchCriteriaConfig.setComponentHight("50px");
		privateViewSearchLookupSearchCriteriaConfig.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchCriteriaPanel");
		privateViewSearchLookupSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		privateViewSearchLookupSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		privateViewSearchLookupSearchCriteriaConfig.setGtnLayoutConfig(privateViewSearchLookupSearchCriteriaLayout);
		componentList.add(privateViewSearchLookupSearchCriteriaConfig);
		addHierarchyNameTextBox(componentList, namespace);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig horizontalViewNameLayout = layoutsConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "horizontalViewNameLayout", namespace
						+ GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchCriteriaLayout");
		componentList.add(horizontalViewNameLayout);

		GtnUIFrameworkComponentConfig addHierarchyNameTextBox = new GtnUIFrameworkComponentConfig();
		addHierarchyNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		addHierarchyNameTextBox.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME);
		addHierarchyNameTextBox.setComponentName("View Name");
		addHierarchyNameTextBox.setAddToParent(true);
		addHierarchyNameTextBox.setParentComponentId(horizontalViewNameLayout.getComponentId());
		addHierarchyNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME);
		addHierarchyNameTextBox.setDefaultFocus(true);
		GtnUIFrameworkTextBoxConfig textBoxConfig = new GtnUIFrameworkTextBoxConfig();
		addHierarchyNameTextBox.setGtnTextBoxConfig(textBoxConfig);
		GtnUIFrameworkValidationConfig hierarchyNameValidationConfig = new GtnUIFrameworkValidationConfig();
		hierarchyNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		hierarchyNameValidationConfig.setAttachRegxValidatior(true);
		hierarchyNameValidationConfig.setFormatString(GtnFrameworkRegexStringConstants.ACCEPT_MIN_0_MAX_100_CHARACTER);
		hierarchyNameValidationConfig.setRegxValidationMessage("View Name Should be less than 100 Characters");
		addHierarchyNameTextBox.setGtnUIFrameworkValidationConfig(hierarchyNameValidationConfig);

		componentList.add(addHierarchyNameTextBox);

	}

	private void privateViewSearchLookupSearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupSearchAndResetLayout.setAddToParent(true);
		privateViewSearchLookupSearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupSearchAndResetLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		privateViewSearchLookupSearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(privateViewSearchLookupSearchAndResetLayout);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameworkComponentConfig privateViewSearchLookupSearchButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupSearchButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchButton");
		privateViewSearchLookupSearchButton.setComponentName("SEARCH");
		privateViewSearchLookupSearchButton
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		privateViewSearchLookupSearchButton.setAddToParent(true);

		componentList.add(privateViewSearchLookupSearchButton);

		GtnUIFrameWorkActionConfig privateViewValidationActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		privateViewValidationActionConfig.setFieldValues(Arrays.asList(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME));

		GtnUIFrameWorkActionConfig privateViewValidationAlertActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.ALERT_ACTION);
		privateViewValidationAlertActionConfig.addActionParameter(GtnFrameworkReportStringConstants.INVALID_SEARCH);
		privateViewValidationAlertActionConfig
				.addActionParameter(GtnFrameworkReportStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);

		privateViewValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(privateViewValidationAlertActionConfig)));
		actionConfigList.add(privateViewValidationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDataTableActionConfig
				.setActionParameterList(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE));
		loadDataTableActionConfig.setFieldValues(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME));

		actionConfigList.add(loadDataTableActionConfig);
		privateViewSearchLookupSearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig privateViewSearchLookupResetButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResetButton");
		privateViewSearchLookupResetButton.setComponentName("RESET");
		privateViewSearchLookupResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		privateViewSearchLookupResetButton.setAddToParent(true);

		GtnUIFrameWorkActionConfig privateViewResetActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.V8_RESET_ACTION);
		privateViewResetActionConfig.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		privateViewResetActionConfig.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_MESSAGE);
		privateViewResetActionConfig.addActionParameter(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE));
		privateViewResetActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY));
		privateViewSearchLookupResetButton.addGtnUIFrameWorkActionConfig(privateViewResetActionConfig);

		componentList.add(privateViewSearchLookupResetButton);
	}

	private void privateViewSearchLookupResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupResultsPanel = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		privateViewSearchLookupResultsPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultsPanel");
		privateViewSearchLookupResultsPanel.setComponentName("Results");
		privateViewSearchLookupResultsPanel.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privateViewSearchLookupResultsPanel.setAddToParent(true);
		privateViewSearchLookupResultsPanel.addComponentStyle("stpl-margin-left-10");
		privateViewSearchLookupResultsPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(privateViewSearchLookupResultsPanel);
		privateViewSearchLookupResultLayout(componentList, namespace);

	}

	private void privateViewSearchLookupResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig privateViewSearchLookupResultLayout = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig privateViewSearchLookupResultConfig = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupResultConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupResultConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultConfig");

		privateViewSearchLookupResultConfig.setGtnLayoutConfig(privateViewSearchLookupResultLayout);
		privateViewSearchLookupResultConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultsPanel");
		privateViewSearchLookupResultConfig.setAddToParent(true);
		privateViewSearchLookupResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privateViewSearchLookupResultConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(privateViewSearchLookupResultConfig);
		addPrivateViewPagedTableComponent(componentList, namespace);
	}

	private void addPrivateViewPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig privateViewPagedTableComponent = new GtnUIFrameworkComponentConfig();
		privateViewPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		privateViewPagedTableComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		privateViewPagedTableComponent.setComponentName("Results");
		privateViewPagedTableComponent.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultConfig");
		privateViewPagedTableComponent.setAddToParent(true);
		privateViewPagedTableComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		privateViewPagedTableComponent.setComponentStyle(tableStyle);
		privateViewPagedTableComponent.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		componentList.add(privateViewPagedTableComponent);
		GtnUIFrameworkPagedTableConfig privateViewPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		privateViewPagedTableConfig.setEditable(false);
		privateViewPagedTableConfig.setFilterBar(true);
		privateViewPagedTableConfig.setSelectable(true);
		privateViewPagedTableConfig.setPageLength(10);
		privateViewPagedTableConfig.setItemPerPage(10);
		privateViewPagedTableConfig.setPaginationOff(true);

		GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
		alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		alertAction.addActionParameter("Invalid Search");
		alertAction.addActionParameter("There are no Views that match the search criteria.  Please try again.");
		privateViewPagedTableConfig.setRecordTypeManageActionConfig(alertAction);
		privateViewPagedTableConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_PRIVATEVIEWLOOKUP_COUNT_SERVICE);
		privateViewPagedTableConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_PRIVATEVIEWLOOKUP_SERVICE);
		privateViewPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		privateViewPagedTableConfig.setColumnHeaders(Arrays.asList("View Name",
				GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER,
				GtnFrameworkCommonConstants.CREATED_BY_HEADER, "viewId", "viewData"));
		privateViewPagedTableConfig
				.setTableColumnMappingId(new Object[] { GtnFrameworkReportStringConstants.VIEW_NAME_FILTER,
						"createdDateFilter", "modifiedDateFilter", "createdByFilter" });
		privateViewPagedTableConfig.setQueryName("Private");

		privateViewPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());

		privateViewPagedTableConfig.setSelectionListener(true);

		List<GtnUIFrameWorkActionConfig> privateViewItemClickActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig privateViewItemClickActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		privateViewItemClickActionConfig
				.addActionParameter(GtnFrameworkSelectButtonEnableActionInHierarchyLookup.class.getName());
		privateViewItemClickActionConfig.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSelectButton");
		privateViewItemClickActionConfig.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		privateViewItemClickActionConfigList.add(privateViewItemClickActionConfig);
		privateViewPagedTableConfig.setItemClickActionConfigList(privateViewItemClickActionConfigList);

		GtnUIFrameWorkActionConfig recordNotFoundAlertActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.ALERT_ACTION);
		recordNotFoundAlertActionConfig.addActionParameter(GtnFrameworkReportStringConstants.INVALID_SEARCH);
		recordNotFoundAlertActionConfig
				.addActionParameter(GtnFrameworkReportStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);
		privateViewPagedTableConfig.setRecordTypeManageActionConfig(recordNotFoundAlertActionConfig);

		privateViewPagedTableComponent.setGtnPagedTableConfig(privateViewPagedTableConfig);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> privateViewCustomFilterConfigMap = new HashMap<>(
				columnPropertyIds.length);
		String[] custComboboxIds = new String[1];
		String[] custComboBoxType = new String[1];
		int startIndex = 0;
		for (int i = 0; i < columnPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig privateViewHierarchyFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			privateViewHierarchyFilterConfig.setPropertId(columnPropertyIds[i]);
			privateViewHierarchyFilterConfig.setGtnComponentType(componentType[i]);
			if ((startIndex < custComboboxIds.length) && columnPropertyIds[i].equals(custComboboxIds[startIndex])) {
				GtnUIFrameworkComponentConfig privateViewSearchFilterConfig = new GtnUIFrameworkComponentConfig();
				privateViewSearchFilterConfig.setComponentId("customFilterComboBox");
				privateViewSearchFilterConfig.setComponentName("customFilterComboBox");
				privateViewSearchFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				privateViewSearchFilterConfig.getGtnComboboxConfig().setComboBoxType(custComboBoxType[startIndex]);
				privateViewSearchFilterConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				privateViewHierarchyFilterConfig.setGtnComponentConfig(privateViewSearchFilterConfig);
				startIndex++;
			}
			privateViewCustomFilterConfigMap.put(privateViewHierarchyFilterConfig.getPropertId(),
					privateViewHierarchyFilterConfig);
		}
		return privateViewCustomFilterConfigMap;
	}

	private void addPrivateViewSearchLookupControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupControlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupControlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupControlPopUpLayout.setAddToParent(true);
		privateViewSearchLookupControlPopUpLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupControlPopUpLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		privateViewSearchLookupControlPopUpLayout.setSpacing(true);
		privateViewSearchLookupControlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(privateViewSearchLookupControlPopUpLayout);

		GtnUIFrameworkComponentConfig privateViewSearchLookupSelectButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupSelectButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSelectButton");
		privateViewSearchLookupSelectButton.setComponentName("SELECT");
		privateViewSearchLookupSelectButton.setEnable(false);
		privateViewSearchLookupSelectButton
				.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		privateViewSearchLookupSelectButton.setAddToParent(true);

		componentList.add(privateViewSearchLookupSelectButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig reportCustomerHierarchySelectAction = new GtnUIFrameWorkActionConfig();
		reportCustomerHierarchySelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		actionParameter.add(GtnFrameworkReportStringConstants.REPORT_PRIVATEVIEW_SEARCHLOOKUP);
		actionParameter.add(Arrays.asList("viewNameFilter"));
		actionParameter.add(Arrays.asList(GtnFrameworkReportStringConstants.REPORT_PRIVATEVIEW_SEARCHLOOKUP));
		reportCustomerHierarchySelectAction.setActionParameterList(actionParameter);
		actionConfigList.add(reportCustomerHierarchySelectAction);

		GtnUIFrameWorkActionConfig reportCustomHierarchyClosepopup = new GtnUIFrameWorkActionConfig();
		reportCustomHierarchyClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		reportCustomHierarchyClosepopup.addActionParameter(GtnFrameworkCommonConstants.PRIVATE_VIEW_SEARCH_LOOKUP_VIEW);
		actionConfigList.add(reportCustomHierarchyClosepopup);

		GtnUIFrameWorkActionConfig loadViewAction = new GtnUIFrameWorkActionConfig();
		loadViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadViewAction.addActionParameter(GtnReportDataSelectionLoadViewAction.class.getName());
		loadViewAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_PRIVATEVIEW_SEARCHLOOKUP);
		loadViewAction.addActionParameter("reportLandingScreen");
		actionConfigList.add(loadViewAction);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		enableAction.addActionParameter("reportLandingScreen_dsDeleteView");
		actionConfigList.add(enableAction);
		privateViewSearchLookupSelectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig privateViewSearchLookupCloseButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupCloseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupCloseButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupCloseButton");
		privateViewSearchLookupCloseButton.setComponentName("CLOSE");
		privateViewSearchLookupCloseButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		privateViewSearchLookupCloseButton.setAddToParent(true);
		componentList.add(privateViewSearchLookupCloseButton);
		privateViewSearchLookupCloseButton.addGtnUIFrameWorkActionConfig(reportCustomHierarchyClosepopup);

	}
}
