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

public class GtnFrameworkReportDataSelectionPublicViewSearchLookUp {

	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	private static final String[] columnPropertyIds = { GtnFrameworkReportStringConstants.VIEW_NAME_FILTER,
			"createdDateFilter", "modifiedDateFilter", "createdByFilter" };
	private static final GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.DATEFIELDVAADIN8, GtnUIFrameworkComponentType.DATEFIELDVAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };

	public GtnUIFrameworkViewConfig getPublicViewLookUpView(String namespace) {
		GtnUIFrameworkViewConfig dsPublicViewLookUpView = new GtnUIFrameworkViewConfig();
		dsPublicViewLookUpView.setViewName("Public View");
		dsPublicViewLookUpView.setViewId(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PUBLICVIEW);
		dsPublicViewLookUpView.setDefaultView(false);
		dsPublicViewLookUpView.setResetAllowed(true);
		addPublicViewLookUpComponentList(dsPublicViewLookUpView, namespace);
		return dsPublicViewLookUpView;
	}

	private void addPublicViewLookUpComponentList(GtnUIFrameworkViewConfig view, String namespace) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addPublicViewLookUpRootLayout(componentList, namespace);

	}

	private void addPublicViewLookUpRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig dsPublicViewLookUpLayout = new GtnUIFrameworkComponentConfig();
		dsPublicViewLookUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsPublicViewLookUpLayout
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpLayout");
		dsPublicViewLookUpLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dsPublicViewLookUpLayout.setAddToParent(false);
		dsPublicViewLookUpLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig dsPublicViewLookUpConfig = new GtnUIFrameworkLayoutConfig();
		dsPublicViewLookUpConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		dsPublicViewLookUpLayout.setGtnLayoutConfig(dsPublicViewLookUpConfig);
		componentList.add(dsPublicViewLookUpLayout);
		getpublicViewLookUpComponentForPopUp(componentList, namespace);
	}

	public void getpublicViewLookUpComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsPublicViewLookUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig dsPublicViewLookupLayoutConfig = new GtnUIFrameworkLayoutConfig();
		dsPublicViewLookupLayoutConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		dsPublicViewLookUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsPublicViewLookUpLayout.setGtnLayoutConfig(dsPublicViewLookupLayoutConfig);
		dsPublicViewLookUpLayout.setAddToParent(true);
		dsPublicViewLookUpLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dsPublicViewLookUpLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		dsPublicViewLookUpLayout.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpLayout");
		dsPublicViewLookUpLayout.setSpacing(true);
		dsPublicViewLookUpLayout.setMargin(true);
		componentList.add(dsPublicViewLookUpLayout);

		addPublicViewLookUpSearchCriteriaPanel(componentList, namespace);
		addPublicViewLookUpSearchAndResetButtonLayout(componentList, namespace);
		addPublicViewLookUpResultsPanel(componentList, namespace);
		addPublicViewLookupControlPopUpButtonLayout(componentList, namespace);
	}

	private void addPublicViewLookUpSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsPublicViewLookUpSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		dsPublicViewLookUpSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		dsPublicViewLookUpSearchCriteriaPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaPanel");
		dsPublicViewLookUpSearchCriteriaPanel.setComponentName("Search Criteria");
		dsPublicViewLookUpSearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		dsPublicViewLookUpSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dsPublicViewLookUpSearchCriteriaPanel.setAddToParent(true);
		componentList.add(dsPublicViewLookUpSearchCriteriaPanel);
		publicViewLookUpSearchCriteriaLayout(componentList, namespace);
	}

	private void publicViewLookUpSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig dsPublicViewLookupSearchCriteriaLayout = new GtnUIFrameworkLayoutConfig();
		dsPublicViewLookupSearchCriteriaLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig dsPublicViewLookupSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		dsPublicViewLookupSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsPublicViewLookupSearchCriteriaConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaConfig");
		dsPublicViewLookupSearchCriteriaConfig.setComponentName("Search Criteria");
		dsPublicViewLookupSearchCriteriaConfig.setAddToParent(true);
		dsPublicViewLookupSearchCriteriaConfig.setComponentWidth("90%");
		dsPublicViewLookupSearchCriteriaConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaPanel");
		dsPublicViewLookupSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		dsPublicViewLookupSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		dsPublicViewLookupSearchCriteriaConfig.setGtnLayoutConfig(dsPublicViewLookupSearchCriteriaLayout);
		componentList.add(dsPublicViewLookupSearchCriteriaConfig);
		addPublicViewLookUpViewNameTextBox(componentList, namespace);
	}

	private void addPublicViewLookUpViewNameTextBox(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsPublicViewViewNameLayout = layoutsConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpViewNameLayout",
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpSearchCriteriaConfig");
		componentList.add(dsPublicViewViewNameLayout);

		GtnUIFrameworkComponentConfig dsPublicViewName = new GtnUIFrameworkComponentConfig();
		dsPublicViewName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		dsPublicViewName.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOK_UP_VIEW_NAME_TEXT_BOX);
		dsPublicViewName.setComponentName("View Name");
		dsPublicViewName.setAddToParent(true);
		dsPublicViewName.setParentComponentId(dsPublicViewViewNameLayout.getComponentId());
		dsPublicViewName.setComponentWsFieldId(GtnFrameworkCommonConstants.PUBLIC_VIEW_NAME);
		dsPublicViewName.setDefaultFocus(true);
		GtnUIFrameworkTextBoxConfig textBoxConfig = new GtnUIFrameworkTextBoxConfig();
		dsPublicViewName.setGtnTextBoxConfig(textBoxConfig);
		GtnUIFrameworkValidationConfig hierarchyNameValidationConfig = new GtnUIFrameworkValidationConfig();
		hierarchyNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		hierarchyNameValidationConfig.setAttachRegxValidatior(true);
		hierarchyNameValidationConfig.setFormatString(GtnFrameworkRegexStringConstants.ACCEPT_MIN_0_MAX_100_CHARACTER);
		hierarchyNameValidationConfig.setRegxValidationMessage("View Name Should be less than 100 Characters");
		dsPublicViewName.setGtnUIFrameworkValidationConfig(hierarchyNameValidationConfig);

		componentList.add(dsPublicViewName);

	}

	private void addPublicViewLookUpSearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsPublicViewLookupSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		dsPublicViewLookupSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsPublicViewLookupSearchAndResetLayout.setAddToParent(true);
		dsPublicViewLookupSearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		dsPublicViewLookupSearchAndResetLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		dsPublicViewLookupSearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		dsPublicViewLookupSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(dsPublicViewLookupSearchAndResetLayout);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameworkComponentConfig dsPublicViewSearchButton = new GtnUIFrameworkComponentConfig();
		dsPublicViewSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		dsPublicViewSearchButton
				.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewSearchButton");
		dsPublicViewSearchButton.setComponentName("SEARCH");
		dsPublicViewSearchButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		dsPublicViewSearchButton.setAddToParent(true);

		GtnUIFrameWorkActionConfig dsPublicViewValidationActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		dsPublicViewValidationActionConfig
				.setFieldValues(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOK_UP_VIEW_NAME_TEXT_BOX));

		GtnUIFrameWorkActionConfig dsPublicViewValidationAlertActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.ALERT_ACTION);
		dsPublicViewValidationAlertActionConfig.addActionParameter(GtnFrameworkReportStringConstants.INVALID_SEARCH);
		dsPublicViewValidationAlertActionConfig
				.addActionParameter(GtnFrameworkReportStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);

		dsPublicViewValidationActionConfig.setActionParameterList(Arrays.asList(GtnUIFrameworkValidationType.AND,
				Arrays.asList(dsPublicViewValidationAlertActionConfig)));
		actionConfigList.add(dsPublicViewValidationActionConfig);

		componentList.add(dsPublicViewSearchButton);

		GtnUIFrameWorkActionConfig publicViewResultloadDataGridActionConfig = new GtnUIFrameWorkActionConfig();
		publicViewResultloadDataGridActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		publicViewResultloadDataGridActionConfig
				.setActionParameterList(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE));
		publicViewResultloadDataGridActionConfig
				.setFieldValues(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOK_UP_VIEW_NAME_TEXT_BOX));
		actionConfigList.add(publicViewResultloadDataGridActionConfig);
		dsPublicViewSearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig dsPublicViewResetButton = new GtnUIFrameworkComponentConfig();
		dsPublicViewResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		dsPublicViewResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewResetCriteriaButton");
		dsPublicViewResetButton.setComponentName("RESET");
		dsPublicViewResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		dsPublicViewResetButton.setAddToParent(true);

		GtnUIFrameWorkActionConfig dsPublicViewResetActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.V8_RESET_ACTION);
		dsPublicViewResetActionConfig.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		dsPublicViewResetActionConfig.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_MESSAGE);
		dsPublicViewResetActionConfig.addActionParameter(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkReportStringConstants.PUBLIC_VIEW_LOOK_UP_VIEW_NAME_TEXT_BOX,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE));
		dsPublicViewResetActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY));
		dsPublicViewResetButton.addGtnUIFrameWorkActionConfig(dsPublicViewResetActionConfig);

		componentList.add(dsPublicViewResetButton);
	}

	private void addPublicViewLookUpResultsPanel(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig dsPublicViewResultsPanel = new GtnUIFrameworkComponentConfig();
		dsPublicViewResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		dsPublicViewResultsPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultsPanel");
		dsPublicViewResultsPanel.setComponentName("Results");
		dsPublicViewResultsPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		dsPublicViewResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dsPublicViewResultsPanel.setAddToParent(true);
		componentList.add(dsPublicViewResultsPanel);
		publicViewLookUpResultLayout(componentList, namespace);

	}

	private void publicViewLookUpResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkLayoutConfig dsPublicViewResultLayout = new GtnUIFrameworkLayoutConfig();
		dsPublicViewResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig dsPublicViewResultConfig = new GtnUIFrameworkComponentConfig();
		dsPublicViewResultConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsPublicViewResultConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultConfig");

		dsPublicViewResultConfig.setGtnLayoutConfig(dsPublicViewResultLayout);
		dsPublicViewResultConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultsPanel");
		dsPublicViewResultConfig.setAddToParent(true);
		dsPublicViewResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dsPublicViewResultConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(dsPublicViewResultConfig);
		addPublicViewPagedTableComponent(componentList, namespace);
	}

	private void addPublicViewPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig dsPublicViewPagedGridComponent = new GtnUIFrameworkComponentConfig();
		dsPublicViewPagedGridComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		dsPublicViewPagedGridComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE);
		dsPublicViewPagedGridComponent.setComponentName("Results");
		dsPublicViewPagedGridComponent.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookUpResultConfig");
		dsPublicViewPagedGridComponent.setAddToParent(true);
		dsPublicViewPagedGridComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> publicViewGridStyle = new ArrayList<>();
		publicViewGridStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		publicViewGridStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		publicViewGridStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		publicViewGridStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		dsPublicViewPagedGridComponent.setComponentStyle(publicViewGridStyle);
		dsPublicViewPagedGridComponent.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		componentList.add(dsPublicViewPagedGridComponent);
		GtnUIFrameworkPagedTableConfig publicViewPagedGridConfig = new GtnUIFrameworkPagedTableConfig();
		publicViewPagedGridConfig.setEditable(false);
		publicViewPagedGridConfig.setFilterBar(true);
		publicViewPagedGridConfig.setSelectable(true);
		publicViewPagedGridConfig.setPageLength(10);
		publicViewPagedGridConfig.setItemPerPage(10);

		GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
		alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		alertAction.addActionParameter("Invalid Search");
		alertAction.addActionParameter("There are no Views that match the search criteria.  Please try again.");
		publicViewPagedGridConfig.setRecordTypeManageActionConfig(alertAction);

		publicViewPagedGridConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_PUBLICVIEWLOOKUP_SERVICE);
		publicViewPagedGridConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_PUBLICVIEWLOOKUP_SERVICE);
		publicViewPagedGridConfig.setTableColumnDataType(new Class<?>[] { GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		publicViewPagedGridConfig.setColumnHeaders(Arrays.asList("View Name",
				GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER,
				GtnFrameworkCommonConstants.CREATED_BY_HEADER, "viewId", "viewData"));
		publicViewPagedGridConfig
				.setTableColumnMappingId(new Object[] { GtnFrameworkReportStringConstants.VIEW_NAME_FILTER,
						"createdDateFilter", "modifiedDateFilter", "createdByFilter" });
		publicViewPagedGridConfig.setQueryName("Public");
		publicViewPagedGridConfig.setCustomFilterConfigMap(getCustomFilterConfig());

		publicViewPagedGridConfig.setSelectionListener(true);

		List<GtnUIFrameWorkActionConfig> dsPublicViewItemClickActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig dsPublicViewItemClickActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		dsPublicViewItemClickActionConfig
				.addActionParameter(GtnFrameworkSelectButtonEnableActionInHierarchyLookup.class.getName());
		dsPublicViewItemClickActionConfig.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookupcontrolPopUpSelectButton");
		dsPublicViewItemClickActionConfig.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE);
		dsPublicViewItemClickActionConfigList.add(dsPublicViewItemClickActionConfig);
		publicViewPagedGridConfig.setItemClickActionConfigList(dsPublicViewItemClickActionConfigList);

		GtnUIFrameWorkActionConfig dsPublicViewRecordNotFoundAlertActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.ALERT_ACTION);
		dsPublicViewRecordNotFoundAlertActionConfig
				.addActionParameter(GtnFrameworkReportStringConstants.INVALID_SEARCH);
		dsPublicViewRecordNotFoundAlertActionConfig
				.addActionParameter(GtnFrameworkReportStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);
		publicViewPagedGridConfig.setRecordTypeManageActionConfig(dsPublicViewRecordNotFoundAlertActionConfig);

		dsPublicViewPagedGridComponent.setGtnPagedTableConfig(publicViewPagedGridConfig);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {

		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> dsPublicViewCustomFilterConfigMap = new HashMap<>(
				columnPropertyIds.length);
		String[] custComboboxIds = new String[1];
		String[] custComboBoxType = new String[1];
		int startIndex = 0;
		for (int i = 0; i < columnPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig dsPublicViewHierarchyFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			dsPublicViewHierarchyFilterConfig.setPropertId(columnPropertyIds[i]);
			dsPublicViewHierarchyFilterConfig.setGtnComponentType(componentType[i]);
			if ((startIndex < custComboboxIds.length) && columnPropertyIds[i].equals(custComboboxIds[startIndex])) {
				GtnUIFrameworkComponentConfig dsPublicViewSearchFilterConfig = new GtnUIFrameworkComponentConfig();
				dsPublicViewSearchFilterConfig.setComponentId("customFilterComboBox");
				dsPublicViewSearchFilterConfig.setComponentName("customFilterComboBox");
				dsPublicViewSearchFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				dsPublicViewSearchFilterConfig.getGtnComboboxConfig().setComboBoxType(custComboBoxType[startIndex]);
				dsPublicViewSearchFilterConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				dsPublicViewHierarchyFilterConfig.setGtnComponentConfig(dsPublicViewSearchFilterConfig);
				startIndex++;
			}
			dsPublicViewCustomFilterConfigMap.put(dsPublicViewHierarchyFilterConfig.getPropertId(),
					dsPublicViewHierarchyFilterConfig);
		}
		return dsPublicViewCustomFilterConfigMap;
	}

	private void addPublicViewLookupControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsPublicViewLookupControlButtonConfig = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig publicViewLookupControlLayout = new GtnUIFrameworkLayoutConfig();
		dsPublicViewLookupControlButtonConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsPublicViewLookupControlButtonConfig.setAddToParent(true);
		dsPublicViewLookupControlButtonConfig.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		dsPublicViewLookupControlButtonConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		dsPublicViewLookupControlButtonConfig.setSpacing(true);
		dsPublicViewLookupControlButtonConfig.setGtnLayoutConfig(publicViewLookupControlLayout);
		componentList.add(dsPublicViewLookupControlButtonConfig);

		GtnUIFrameworkComponentConfig dsPublicViewSelectButton = new GtnUIFrameworkComponentConfig();
		dsPublicViewSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		dsPublicViewSelectButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookupcontrolPopUpSelectButton");
		dsPublicViewSelectButton.setComponentName("SELECT");
		dsPublicViewSelectButton.setEnable(false);
		dsPublicViewSelectButton.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "controlPopUpButtonLayout");
		dsPublicViewSelectButton.setAddToParent(true);
		componentList.add(dsPublicViewSelectButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig publicViewSelectAction = new GtnUIFrameWorkActionConfig();
		publicViewSelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE);
		actionParameter.add(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PUBLICVIEW_SEARCHLOOKUP);
		actionParameter.add(Arrays.asList(GtnFrameworkReportStringConstants.VIEW_NAME_FILTER));
		actionParameter
				.add(Arrays.asList(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PUBLICVIEW_SEARCHLOOKUP));
		publicViewSelectAction.setActionParameterList(actionParameter);
		actionConfigList.add(publicViewSelectAction);

		GtnUIFrameWorkActionConfig dsPublicViewCloseAction = new GtnUIFrameWorkActionConfig();
		dsPublicViewCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		dsPublicViewCloseAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PUBLICVIEW);
		actionConfigList.add(dsPublicViewCloseAction);

		GtnUIFrameWorkActionConfig dsPublicViewLoadAction = new GtnUIFrameWorkActionConfig();
		dsPublicViewLoadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dsPublicViewLoadAction.addActionParameter(GtnReportDataSelectionLoadViewAction.class.getName());
		dsPublicViewLoadAction
				.addActionParameter(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PUBLICVIEW_SEARCHLOOKUP);
		dsPublicViewLoadAction.addActionParameter("dataSelectionTab");
		actionConfigList.add(dsPublicViewLoadAction);
		dsPublicViewSelectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig dsPublicViewCloseButton = new GtnUIFrameworkComponentConfig();
		dsPublicViewCloseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		dsPublicViewCloseButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "publicViewLookupcontrolPopUpCloseButton");
		dsPublicViewCloseButton.setComponentName("CLOSE");
		dsPublicViewCloseButton.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "controlPopUpButtonLayout");
		dsPublicViewCloseButton.setAddToParent(true);

		componentList.add(dsPublicViewCloseButton);
		dsPublicViewCloseButton.addGtnUIFrameWorkActionConfig(dsPublicViewCloseAction);
	}
}
