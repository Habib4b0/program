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

public class GtnFrameworkReportPublicViewSearchLookUp {

	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	private static final String[] columnPropertyIds = { "viewNameFilter", "createdDateFilter", "modifiedDateFilter",
			"createdByFilter" };
	private static final GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.DATEFIELDVAADIN8, GtnUIFrameworkComponentType.DATEFIELDVAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };

	public GtnUIFrameworkViewConfig getPublicViewLookUpView(String namespace) {
		GtnUIFrameworkViewConfig publicViewLookUpView = new GtnUIFrameworkViewConfig();
		publicViewLookUpView.setViewName("Public View");
		publicViewLookUpView.setViewId(GtnFrameworkCommonConstants.PUBLIC_VIEW_SEARCH_LOOKUP_VIEW);
		publicViewLookUpView.setDefaultView(false);
		publicViewLookUpView.setResetAllowed(true);
		addPublicViewLookUpComponentList(publicViewLookUpView, namespace);
		return publicViewLookUpView;
	}

	private void addPublicViewLookUpComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addPublicViewLookUpRootLayout(componentList, namespace);

	}

	private void addPublicViewLookUpRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpLayout = new GtnUIFrameworkComponentConfig();
		publicViewLookUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookUpLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpLayout");
		publicViewLookUpLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookUpLayout.setAddToParent(false);
		publicViewLookUpLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig publicViewLookUpConfig = new GtnUIFrameworkLayoutConfig();
		publicViewLookUpConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		publicViewLookUpLayout.setGtnLayoutConfig(publicViewLookUpConfig);
		componentList.add(publicViewLookUpLayout);
		getpublicViewLookUpComponentForPopUp(componentList, namespace);
	}

	public void getpublicViewLookUpComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpLayoutgtnLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig publicViewLookUpgtnLayoutConfig = new GtnUIFrameworkLayoutConfig();
		publicViewLookUpgtnLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		publicViewLookUpLayoutgtnLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookUpLayoutgtnLayout.setGtnLayoutConfig(publicViewLookUpgtnLayoutConfig);
		publicViewLookUpLayoutgtnLayout.setAddToParent(true);
		publicViewLookUpLayoutgtnLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookUpLayoutgtnLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewLookUpLayoutgtnLayout.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpLayout");
		publicViewLookUpLayoutgtnLayout.setSpacing(true);
		publicViewLookUpLayoutgtnLayout.setMargin(true);
		componentList.add(publicViewLookUpLayoutgtnLayout);

		addPublicViewLookUpSearchCriteriaPanel(componentList, namespace);
		addPublicViewLookUpSearchAndResetButtonLayout(componentList, namespace);
		addPublicViewLookUpResultsPanel(componentList, namespace);
		addPublicViewLookupControlPopUpButtonLayout(componentList, namespace);
	}

	private void addPublicViewLookUpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		publicViewLookUpSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		publicViewLookUpSearchCriteriaPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaPanel");
		publicViewLookUpSearchCriteriaPanel.setComponentName("Search Criteria");
		publicViewLookUpSearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewLookUpSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookUpSearchCriteriaPanel.setAddToParent(true);
		publicViewLookUpSearchCriteriaPanel.addComponentStyle("stpl-margin-left-10");
		publicViewLookUpSearchCriteriaPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(publicViewLookUpSearchCriteriaPanel);
		publicViewLookUpSearchCriteriaLayout(componentList, namespace);
	}

	private void publicViewLookUpSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig publicViewLookUpSearchCriteriaLayout = new GtnUIFrameworkLayoutConfig();
		publicViewLookUpSearchCriteriaLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig publicViewLookUpSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		publicViewLookUpSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookUpSearchCriteriaConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaConfig");
		publicViewLookUpSearchCriteriaConfig.setComponentName("Search Criteria");
		publicViewLookUpSearchCriteriaConfig.setAddToParent(true);
		publicViewLookUpSearchCriteriaConfig.setComponentWidth("90%");
		publicViewLookUpSearchCriteriaConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaPanel");
		publicViewLookUpSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		publicViewLookUpSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		publicViewLookUpSearchCriteriaConfig.setGtnLayoutConfig(publicViewLookUpSearchCriteriaLayout);
		componentList.add(publicViewLookUpSearchCriteriaConfig);
		addPublicViewLookUpViewNameTextBox(componentList, namespace);
	}

	private void addPublicViewLookUpViewNameTextBox(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpViewNameLayout = layoutsConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpViewNameLayout",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaConfig");
		componentList.add(publicViewLookUpViewNameLayout);

		GtnUIFrameworkComponentConfig publicViewLookUpViewNameTextBox = new GtnUIFrameworkComponentConfig();
		publicViewLookUpViewNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		publicViewLookUpViewNameTextBox.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOK_UP_VIEW_NAME_TEXT_BOX);
		publicViewLookUpViewNameTextBox.setComponentName("View Name");
		publicViewLookUpViewNameTextBox.setAddToParent(true);
		publicViewLookUpViewNameTextBox.setParentComponentId(publicViewLookUpViewNameLayout.getComponentId());
		publicViewLookUpViewNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.PUBLIC_VIEW_NAME);
		publicViewLookUpViewNameTextBox.setDefaultFocus(true);
		GtnUIFrameworkValidationConfig hierarchyNameValidationConfig = new GtnUIFrameworkValidationConfig();
		hierarchyNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		hierarchyNameValidationConfig.setAttachRegxValidatior(true);
		hierarchyNameValidationConfig.setFormatString(GtnFrameworkRegexStringConstants.ACCEPT_MIN_0_MAX_100_CHARACTER);
		hierarchyNameValidationConfig.setRegxValidationMessage("View Name Should be less than 100 Characters");
		publicViewLookUpViewNameTextBox.setGtnUIFrameworkValidationConfig(hierarchyNameValidationConfig);

		componentList.add(publicViewLookUpViewNameTextBox);

	}

	private void addPublicViewLookUpSearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		publicViewLookUpSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookUpSearchAndResetLayout.setAddToParent(true);
		publicViewLookUpSearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewLookUpSearchAndResetLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		publicViewLookUpSearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		publicViewLookUpSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(publicViewLookUpSearchAndResetLayout);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameworkComponentConfig publicViewLookUpSearchButton = new GtnUIFrameworkComponentConfig();
		publicViewLookUpSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		publicViewLookUpSearchButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewSearchButton");
		publicViewLookUpSearchButton.setComponentName("SEARCH");
		publicViewLookUpSearchButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		publicViewLookUpSearchButton.setAddToParent(true);

		GtnUIFrameWorkActionConfig publicViewValidationActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		publicViewValidationActionConfig
				.setFieldValues(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOK_UP_VIEW_NAME_TEXT_BOX));

		GtnUIFrameWorkActionConfig publicViewValidationAlertActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.ALERT_ACTION);
		publicViewValidationAlertActionConfig.addActionParameter(GtnFrameworkReportStringConstants.INVALID_SEARCH);
		publicViewValidationAlertActionConfig
				.addActionParameter(GtnFrameworkReportStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);

		publicViewValidationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(publicViewValidationAlertActionConfig)));
		actionConfigList.add(publicViewValidationActionConfig);

		componentList.add(publicViewLookUpSearchButton);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDataTableActionConfig
				.setActionParameterList(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE));
		loadDataTableActionConfig.setFieldValues(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOK_UP_VIEW_NAME_TEXT_BOX));
		actionConfigList.add(loadDataTableActionConfig);
		publicViewLookUpSearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig publicViewLookUpResetButton = new GtnUIFrameworkComponentConfig();
		publicViewLookUpResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		publicViewLookUpResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewResetCriteriaButton");
		publicViewLookUpResetButton.setComponentName("RESET");
		publicViewLookUpResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		publicViewLookUpResetButton.setAddToParent(true);

		GtnUIFrameWorkActionConfig publicViewResetActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.V8_RESET_ACTION);
		publicViewResetActionConfig.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		publicViewResetActionConfig.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_MESSAGE);
		publicViewResetActionConfig.addActionParameter(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOK_UP_VIEW_NAME_TEXT_BOX,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE));
		publicViewResetActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY));
		publicViewLookUpResetButton.addGtnUIFrameWorkActionConfig(publicViewResetActionConfig);

		componentList.add(publicViewLookUpResetButton);
	}

	private void addPublicViewLookUpResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookUpResultsPanel = new GtnUIFrameworkComponentConfig();
		publicViewLookUpResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		publicViewLookUpResultsPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultsPanel");
		publicViewLookUpResultsPanel.setComponentName("Results");
		publicViewLookUpResultsPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewLookUpResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookUpResultsPanel.setAddToParent(true);
		publicViewLookUpResultsPanel.addComponentStyle("stpl-margin-left-10");
		publicViewLookUpResultsPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(publicViewLookUpResultsPanel);
		publicViewLookUpResultLayout(componentList, namespace);

	}

	private void publicViewLookUpResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig publicViewLookUpResultLayout = new GtnUIFrameworkLayoutConfig();
		publicViewLookUpResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig publicViewLookUpResultConfig = new GtnUIFrameworkComponentConfig();
		publicViewLookUpResultConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookUpResultConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultConfig");

		publicViewLookUpResultConfig.setGtnLayoutConfig(publicViewLookUpResultLayout);
		publicViewLookUpResultConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultsPanel");
		publicViewLookUpResultConfig.setAddToParent(true);
		publicViewLookUpResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookUpResultConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(publicViewLookUpResultConfig);
		addPublicViewPagedTableComponent(componentList, namespace);
	}

	private void addPublicViewPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig publicViewPagedTableComponent = new GtnUIFrameworkComponentConfig();
		publicViewPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		publicViewPagedTableComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE);
		publicViewPagedTableComponent.setComponentName("Results");
		publicViewPagedTableComponent.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultConfig");
		publicViewPagedTableComponent.setAddToParent(true);
		publicViewPagedTableComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		publicViewPagedTableComponent.setComponentStyle(tableStyle);
		publicViewPagedTableComponent.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		componentList.add(publicViewPagedTableComponent);
		GtnUIFrameworkPagedTableConfig publicViewPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		publicViewPagedTableConfig.setEditable(false);
		publicViewPagedTableConfig.setFilterBar(true);
		publicViewPagedTableConfig.setSelectable(true);
		publicViewPagedTableConfig.setPageLength(10);
		publicViewPagedTableConfig.setItemPerPage(10);
		publicViewPagedTableConfig.setPaginationOff(true);

		GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
		alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		alertAction.addActionParameter("Invalid Search");
		alertAction.addActionParameter("There are no Views that match the search criteria.  Please try again.");
		publicViewPagedTableConfig.setRecordTypeManageActionConfig(alertAction);

		publicViewPagedTableConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_PUBLICVIEWLOOKUP_COUNT_SERVICE);
		publicViewPagedTableConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_PUBLICVIEWLOOKUP_SERVICE);
		publicViewPagedTableConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		publicViewPagedTableConfig.setColumnHeaders(Arrays.asList("View Name",
				GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER,
				GtnFrameworkCommonConstants.CREATED_BY_HEADER, "viewId", "viewData"));
		publicViewPagedTableConfig
				.setTableColumnMappingId(new Object[] { GtnFrameworkReportStringConstants.VIEW_NAME_FILTER,
						"createdDateFilter", "modifiedDateFilter", "createdByFilter" });
		publicViewPagedTableConfig.setQueryName("Public");
		publicViewPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());

		publicViewPagedTableConfig.setSelectionListener(true);

		List<GtnUIFrameWorkActionConfig> publicViewItemClickActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig publicViewItemClickActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		publicViewItemClickActionConfig
				.addActionParameter(GtnFrameworkSelectButtonEnableActionInHierarchyLookup.class.getName());
		publicViewItemClickActionConfig.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookupcontrolPopUpSelectButton");
		publicViewItemClickActionConfig.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE);
		publicViewItemClickActionConfigList.add(publicViewItemClickActionConfig);
		publicViewPagedTableConfig.setItemClickActionConfigList(publicViewItemClickActionConfigList);

		GtnUIFrameWorkActionConfig publicViewRecordNotFoundAlertActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.ALERT_ACTION);
		publicViewRecordNotFoundAlertActionConfig.addActionParameter(GtnFrameworkReportStringConstants.INVALID_SEARCH);
		publicViewRecordNotFoundAlertActionConfig
				.addActionParameter(GtnFrameworkReportStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);
		publicViewPagedTableConfig.setRecordTypeManageActionConfig(publicViewRecordNotFoundAlertActionConfig);

		publicViewPagedTableComponent.setGtnPagedTableConfig(publicViewPagedTableConfig);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> publicViewCustomFilterConfigMap = new HashMap<>(
				columnPropertyIds.length);
		String[] custComboboxIds = new String[1];
		String[] custComboBoxType = new String[1];
		int startIndex = 0;
		for (int i = 0; i < columnPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig publicViewHierarchyFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			publicViewHierarchyFilterConfig.setPropertId(columnPropertyIds[i]);
			publicViewHierarchyFilterConfig.setGtnComponentType(componentType[i]);
			if ((startIndex < custComboboxIds.length) && columnPropertyIds[i].equals(custComboboxIds[startIndex])) {
				GtnUIFrameworkComponentConfig publicViewSearchFilterConfig = new GtnUIFrameworkComponentConfig();
				publicViewSearchFilterConfig.setComponentId("customFilterComboBox");
				publicViewSearchFilterConfig.setComponentName("customFilterComboBox");
				publicViewSearchFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				publicViewSearchFilterConfig.getGtnComboboxConfig().setComboBoxType(custComboBoxType[startIndex]);
				publicViewSearchFilterConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				publicViewHierarchyFilterConfig.setGtnComponentConfig(publicViewSearchFilterConfig);
				startIndex++;
			}
			publicViewCustomFilterConfigMap.put(publicViewHierarchyFilterConfig.getPropertId(),
					publicViewHierarchyFilterConfig);
		}
		return publicViewCustomFilterConfigMap;
	}

	private void addPublicViewLookupControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewLookupcontrolPopUpConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig publicViewLookupControlLayout = new GtnUIFrameworkLayoutConfig();
		publicViewLookupcontrolPopUpConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookupcontrolPopUpConfig.setAddToParent(true);
		publicViewLookupcontrolPopUpConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewLookupcontrolPopUpConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		publicViewLookupcontrolPopUpConfig.setSpacing(true);
		publicViewLookupcontrolPopUpConfig.setGtnLayoutConfig(publicViewLookupControlLayout);
		componentList.add(publicViewLookupcontrolPopUpConfig);

		GtnUIFrameworkComponentConfig publicViewLookupcontrolPopUpSelectButton = new GtnUIFrameworkComponentConfig();
		publicViewLookupcontrolPopUpSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		publicViewLookupcontrolPopUpSelectButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookupcontrolPopUpSelectButton");
		publicViewLookupcontrolPopUpSelectButton.setComponentName("SELECT");
		publicViewLookupcontrolPopUpSelectButton.setEnable(false);
		publicViewLookupcontrolPopUpSelectButton.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "controlPopUpButtonLayout");
		publicViewLookupcontrolPopUpSelectButton.setAddToParent(true);
		componentList.add(publicViewLookupcontrolPopUpSelectButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig reportCustomerHierarchySelectAction = new GtnUIFrameWorkActionConfig();
		reportCustomerHierarchySelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE);
		actionParameter.add(GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_PUBLIC_VIEWS);
		actionParameter.add(Arrays.asList("viewNameFilter"));
		actionParameter.add(Arrays.asList(GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_PUBLIC_VIEWS));
		reportCustomerHierarchySelectAction.setActionParameterList(actionParameter);
		actionConfigList.add(reportCustomerHierarchySelectAction);

		GtnUIFrameWorkActionConfig reportCustomHierarchyClosepopup = new GtnUIFrameWorkActionConfig();
		reportCustomHierarchyClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		reportCustomHierarchyClosepopup.addActionParameter(GtnFrameworkCommonConstants.PUBLIC_VIEW_SEARCH_LOOKUP_VIEW);
		actionConfigList.add(reportCustomHierarchyClosepopup);

		GtnUIFrameWorkActionConfig loadViewAction = new GtnUIFrameWorkActionConfig();
		loadViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadViewAction.addActionParameter(GtnReportDataSelectionLoadViewAction.class.getName());
		loadViewAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_LANDING_SCREEN_PUBLIC_VIEWS);
		loadViewAction.addActionParameter("reportLandingScreen");
		actionConfigList.add(loadViewAction);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		enableAction.addActionParameter("reportLandingScreen_dsDeleteView");
		actionConfigList.add(enableAction);
		publicViewLookupcontrolPopUpSelectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig publicViewLookupcontrolPopUpCloseButton = new GtnUIFrameworkComponentConfig();
		publicViewLookupcontrolPopUpCloseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		publicViewLookupcontrolPopUpCloseButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookupcontrolPopUpCloseButton");
		publicViewLookupcontrolPopUpCloseButton.setComponentName("CLOSE");
		publicViewLookupcontrolPopUpCloseButton.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "controlPopUpButtonLayout");
		publicViewLookupcontrolPopUpCloseButton.setAddToParent(true);

		componentList.add(publicViewLookupcontrolPopUpCloseButton);
		publicViewLookupcontrolPopUpCloseButton.addGtnUIFrameWorkActionConfig(reportCustomHierarchyClosepopup);
	}
}
