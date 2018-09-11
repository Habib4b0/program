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

public class GtnFrameworkReportDataSelectionPrivateViewLookUp {

	private GtnFrameworkReportLayoutsConfig layoutsConfig = new GtnFrameworkReportLayoutsConfig();

	private static final String[] columnPropertyIds = { GtnFrameworkReportStringConstants.VIEW_NAME_FILTER,
			"createdDateFilter", "modifiedDateFilter", "createdByFilter" };
	private static final GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
			GtnUIFrameworkComponentType.DATEFIELDVAADIN8, GtnUIFrameworkComponentType.DATEFIELDVAADIN8,
			GtnUIFrameworkComponentType.TEXTBOX_VAADIN8 };

	public GtnUIFrameworkViewConfig getDsPrivateViewLookUpView(String namespace) {

		GtnUIFrameworkViewConfig dsprivateViewLookupView = new GtnUIFrameworkViewConfig();
		dsprivateViewLookupView.setViewName("Private View");
		dsprivateViewLookupView.setViewId(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PRIVATEVIEW);
		dsprivateViewLookupView.setDefaultView(false);
		dsprivateViewLookupView.setResetAllowed(true);
		addPrivateViewSearchLookupComponentList(dsprivateViewLookupView, namespace);
		return dsprivateViewLookupView;
	}

	private void addPrivateViewSearchLookupComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> dsPrivateViewComponentList = new ArrayList<>();
		view.setGtnComponentList(dsPrivateViewComponentList);
		addPrivateViewSearchLookupRootLayout(dsPrivateViewComponentList, namespace);

	}

	private void addPrivateViewSearchLookupRootLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig dsprivateViewRootLayout = new GtnUIFrameworkComponentConfig();
		dsprivateViewRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsprivateViewRootLayout.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupRootLayout");
		dsprivateViewRootLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dsprivateViewRootLayout.setAddToParent(false);
		dsprivateViewRootLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig privateViewSearchLookupRootConfig = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupRootConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		dsprivateViewRootLayout.setGtnLayoutConfig(privateViewSearchLookupRootConfig);
		componentList.add(dsprivateViewRootLayout);
		getPrivateViewSearchLookupRootComponentForPopUp(componentList, namespace);
	}

	public void getPrivateViewSearchLookupRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsPrivateViewRootComponents = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig privateViewSearchLookupRootComponentConfig = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupRootComponentConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		dsPrivateViewRootComponents.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsPrivateViewRootComponents.setGtnLayoutConfig(privateViewSearchLookupRootComponentConfig);
		dsPrivateViewRootComponents.setAddToParent(true);
		dsPrivateViewRootComponents.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dsPrivateViewRootComponents.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		dsPrivateViewRootComponents.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupRootLayout");
		dsPrivateViewRootComponents.setSpacing(true);
		dsPrivateViewRootComponents.setMargin(true);
		componentList.add(dsPrivateViewRootComponents);

		privateViewSearchLookupSearchCriteriaPanel(componentList, namespace);
		privateViewSearchLookupSearchAndResetButtonLayout(componentList, namespace);
		privateViewSearchLookupResultsPanel(componentList, namespace);
		addPrivateViewSearchLookupControlPopUpButtonLayout(componentList, namespace);
	}

	private void privateViewSearchLookupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsPrivateViewSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		dsPrivateViewSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		dsPrivateViewSearchCriteriaPanel.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "privateViewSearchLookupSearchCriteriaPanel");
		dsPrivateViewSearchCriteriaPanel.setComponentName("Search Criteria");
		dsPrivateViewSearchCriteriaPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		dsPrivateViewSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dsPrivateViewSearchCriteriaPanel.setAddToParent(true);
		componentList.add(dsPrivateViewSearchCriteriaPanel);
		privateViewSearchLookupSearchCriteriaLayout(componentList, namespace);
	}

	private void privateViewSearchLookupSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig dsPrivateViewSearchCriteriaLayout = new GtnUIFrameworkLayoutConfig();
		dsPrivateViewSearchCriteriaLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig dsPrivateViewSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		dsPrivateViewSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsPrivateViewSearchCriteriaConfig.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "privateViewSearchLookupSearchCriteriaLayout");
		dsPrivateViewSearchCriteriaConfig.setComponentName("Search Criteria");
		dsPrivateViewSearchCriteriaConfig.setAddToParent(true);
		dsPrivateViewSearchCriteriaConfig.setComponentWidth("90%");
		dsPrivateViewSearchCriteriaConfig.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ "privateViewSearchLookupSearchCriteriaPanel");
		dsPrivateViewSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		dsPrivateViewSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		dsPrivateViewSearchCriteriaConfig.setGtnLayoutConfig(dsPrivateViewSearchCriteriaLayout);
		componentList.add(dsPrivateViewSearchCriteriaConfig);
		addHierarchyNameTextBox(componentList, namespace);
	}

	private void addHierarchyNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig privateViewNameLayout = layoutsConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "horizontalViewNameLayout", namespace
						+ GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchCriteriaLayout");
		componentList.add(privateViewNameLayout);

		GtnUIFrameworkComponentConfig privateViewName = new GtnUIFrameworkComponentConfig();
		privateViewName.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		privateViewName.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME);
		privateViewName.setComponentName("View Name");
		privateViewName.setAddToParent(true);
		privateViewName.setParentComponentId(privateViewNameLayout.getComponentId());
		privateViewName.setComponentWsFieldId(GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME);
		privateViewName.setDefaultFocus(true);
		GtnUIFrameworkTextBoxConfig textBoxConfig = new GtnUIFrameworkTextBoxConfig();
		privateViewName.setGtnTextBoxConfig(textBoxConfig);
		GtnUIFrameworkValidationConfig hierarchyNameValidationConfig = new GtnUIFrameworkValidationConfig();
		hierarchyNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		hierarchyNameValidationConfig.setAttachRegxValidatior(true);
		hierarchyNameValidationConfig.setFormatString(GtnFrameworkRegexStringConstants.ACCEPT_MIN_0_MAX_100_CHARACTER);
		hierarchyNameValidationConfig.setRegxValidationMessage("View Name Should be less than 100 Characters");
		privateViewName.setGtnUIFrameworkValidationConfig(hierarchyNameValidationConfig);

		componentList.add(privateViewName);

	}

	private void privateViewSearchLookupSearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsPrivateViewSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		dsPrivateViewSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsPrivateViewSearchAndResetLayout.setAddToParent(true);
		dsPrivateViewSearchAndResetLayout.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		dsPrivateViewSearchAndResetLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		dsPrivateViewSearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		dsPrivateViewSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(dsPrivateViewSearchAndResetLayout);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameworkComponentConfig dsPrivateViewSearchButton = new GtnUIFrameworkComponentConfig();
		dsPrivateViewSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		dsPrivateViewSearchButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSearchButton");
		dsPrivateViewSearchButton.setComponentName("SEARCH");
		dsPrivateViewSearchButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		dsPrivateViewSearchButton.setAddToParent(true);

		GtnUIFrameWorkActionConfig dsPrivateViewValidationActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		dsPrivateViewValidationActionConfig.setFieldValues(Arrays.asList(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME));

		GtnUIFrameWorkActionConfig dsPrivateViewValidationAlertActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.ALERT_ACTION);
		dsPrivateViewValidationAlertActionConfig.addActionParameter(GtnFrameworkReportStringConstants.INVALID_SEARCH);
		dsPrivateViewValidationAlertActionConfig
				.addActionParameter(GtnFrameworkReportStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);

		dsPrivateViewValidationActionConfig.setActionParameterList(Arrays.asList(GtnUIFrameworkValidationType.AND,
				Arrays.asList(dsPrivateViewValidationAlertActionConfig)));
		actionConfigList.add(dsPrivateViewValidationActionConfig);

		componentList.add(dsPrivateViewSearchButton);

		GtnUIFrameWorkActionConfig loadDataGridActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataGridActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDataGridActionConfig
				.setActionParameterList(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE));
		loadDataGridActionConfig.setFieldValues(Arrays.asList(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME));
		actionConfigList.add(loadDataGridActionConfig);
		dsPrivateViewSearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig dsPrivateViewResetButton = new GtnUIFrameworkComponentConfig();
		dsPrivateViewResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		dsPrivateViewResetButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResetButton");
		dsPrivateViewResetButton.setComponentName("RESET");
		dsPrivateViewResetButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkReportStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		dsPrivateViewResetButton.setAddToParent(true);

		GtnUIFrameWorkActionConfig dsPrivateViewResetActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.V8_RESET_ACTION);
		dsPrivateViewResetActionConfig.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION);
		dsPrivateViewResetActionConfig.addActionParameter(GtnFrameworkReportStringConstants.RESET_CONFIRMATION_MESSAGE);
		dsPrivateViewResetActionConfig.addActionParameter(Arrays.asList(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME,
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE));
		dsPrivateViewResetActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY));
		dsPrivateViewResetButton.addGtnUIFrameWorkActionConfig(dsPrivateViewResetActionConfig);

		componentList.add(dsPrivateViewResetButton);
	}

	private void privateViewSearchLookupResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsPrivateViewSearchResultsPanel = new GtnUIFrameworkComponentConfig();
		dsPrivateViewSearchResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		dsPrivateViewSearchResultsPanel.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultsPanel");
		dsPrivateViewSearchResultsPanel.setComponentName("Results");
		dsPrivateViewSearchResultsPanel.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		dsPrivateViewSearchResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dsPrivateViewSearchResultsPanel.setAddToParent(true);
		componentList.add(dsPrivateViewSearchResultsPanel);
		privateViewSearchLookupResultLayout(componentList, namespace);

	}

	private void privateViewSearchLookupResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig dsPrivateViewSearchResultLayout = new GtnUIFrameworkLayoutConfig();
		dsPrivateViewSearchResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig dsPrivateViewSearchResultConfig = new GtnUIFrameworkComponentConfig();
		dsPrivateViewSearchResultConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsPrivateViewSearchResultConfig.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultConfig");

		dsPrivateViewSearchResultConfig.setGtnLayoutConfig(dsPrivateViewSearchResultLayout);
		dsPrivateViewSearchResultConfig.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultsPanel");
		dsPrivateViewSearchResultConfig.setAddToParent(true);
		dsPrivateViewSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		dsPrivateViewSearchResultConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(dsPrivateViewSearchResultConfig);
		addPrivateViewPagedTableComponent(componentList, namespace);
	}

	private void addPrivateViewPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig dsPrivateViewPagedGridComponent = new GtnUIFrameworkComponentConfig();
		dsPrivateViewPagedGridComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		dsPrivateViewPagedGridComponent.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		dsPrivateViewPagedGridComponent.setComponentName("Results");
		dsPrivateViewPagedGridComponent.setParentComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupResultConfig");
		dsPrivateViewPagedGridComponent.setAddToParent(true);
		dsPrivateViewPagedGridComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> gridStyle = new ArrayList<>();
		gridStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		gridStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		gridStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		gridStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		dsPrivateViewPagedGridComponent.setComponentStyle(gridStyle);
		dsPrivateViewPagedGridComponent.setModuleName(GtnFrameworkReportStringConstants.REPORT);
		componentList.add(dsPrivateViewPagedGridComponent);
		GtnUIFrameworkPagedTableConfig dsPrivateViewPagedGridConfig = new GtnUIFrameworkPagedTableConfig();
		dsPrivateViewPagedGridConfig.setEditable(false);
		dsPrivateViewPagedGridConfig.setFilterBar(true);
		dsPrivateViewPagedGridConfig.setSelectable(true);
		dsPrivateViewPagedGridConfig.setPageLength(10);
		dsPrivateViewPagedGridConfig.setItemPerPage(10);

		GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
		alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		alertAction.addActionParameter("Invalid Search");
		alertAction.addActionParameter("There are no Views that match the search criteria.  Please try again.");
		dsPrivateViewPagedGridConfig.setRecordTypeManageActionConfig(alertAction);

		dsPrivateViewPagedGridConfig.setCountUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_PRIVATEVIEWLOOKUP_SERVICE);
		dsPrivateViewPagedGridConfig.setResultSetUrl(GtnWsReportConstants.GTN_REPORT_SERVICE
				+ GtnWsReportConstants.GTN_REPORT_LOAD_PRIVATEVIEWLOOKUP_SERVICE);
		dsPrivateViewPagedGridConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE, GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING });
		dsPrivateViewPagedGridConfig.setColumnHeaders(Arrays.asList("View Name",
				GtnFrameworkCommonConstants.CREATED_DATE_HEADER, GtnFrameworkCommonConstants.MODIFIED_DATE_HEADER,
				GtnFrameworkCommonConstants.CREATED_BY_HEADER, "viewId", "viewData"));
		dsPrivateViewPagedGridConfig
				.setTableColumnMappingId(new Object[] { GtnFrameworkReportStringConstants.VIEW_NAME_FILTER,
						"createdDateFilter", "modifiedDateFilter", "createdByFilter" });
		dsPrivateViewPagedGridConfig.setQueryName("Private");
		dsPrivateViewPagedGridConfig.setCustomFilterConfigMap(getCustomFilterConfig());

		dsPrivateViewPagedGridConfig.setSelectionListener(true);

		List<GtnUIFrameWorkActionConfig> dsPrivateViewItemClickActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig dsPrivateViewItemClickActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		dsPrivateViewItemClickActionConfig
				.addActionParameter(GtnFrameworkSelectButtonEnableActionInHierarchyLookup.class.getName());
		dsPrivateViewItemClickActionConfig.addActionParameter(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSelectButton");
		dsPrivateViewItemClickActionConfig.addActionParameter(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		dsPrivateViewItemClickActionConfigList.add(dsPrivateViewItemClickActionConfig);
		dsPrivateViewPagedGridConfig.setItemClickActionConfigList(dsPrivateViewItemClickActionConfigList);

		GtnUIFrameWorkActionConfig dsPrivateViewRecordNotFoundAlertActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.ALERT_ACTION);
		dsPrivateViewRecordNotFoundAlertActionConfig
				.addActionParameter(GtnFrameworkReportStringConstants.INVALID_SEARCH);
		dsPrivateViewRecordNotFoundAlertActionConfig
				.addActionParameter(GtnFrameworkReportStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);
		dsPrivateViewPagedGridConfig.setRecordTypeManageActionConfig(dsPrivateViewRecordNotFoundAlertActionConfig);

		dsPrivateViewPagedGridComponent.setGtnPagedTableConfig(dsPrivateViewPagedGridConfig);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> dsPrivateViewCustomFilterConfigMap = new HashMap<>(
				columnPropertyIds.length);
		String[] custComboboxIds = new String[1];
		String[] custComboBoxType = new String[1];
		int startIndex = 0;
		for (int i = 0; i < columnPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig dsPrivateViewHierarchyFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			dsPrivateViewHierarchyFilterConfig.setPropertId(columnPropertyIds[i]);
			dsPrivateViewHierarchyFilterConfig.setGtnComponentType(componentType[i]);
			if ((startIndex < custComboboxIds.length) && columnPropertyIds[i].equals(custComboboxIds[startIndex])) {
				GtnUIFrameworkComponentConfig dsPrivateViewSearchFilterConfig = new GtnUIFrameworkComponentConfig();
				dsPrivateViewSearchFilterConfig.setComponentId("customFilterComboBox");
				dsPrivateViewSearchFilterConfig.setComponentName("customFilterComboBox");
				dsPrivateViewSearchFilterConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
				dsPrivateViewSearchFilterConfig.getGtnComboboxConfig().setComboBoxType(custComboBoxType[startIndex]);
				dsPrivateViewSearchFilterConfig.getGtnComboboxConfig()
						.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
				dsPrivateViewHierarchyFilterConfig.setGtnComponentConfig(dsPrivateViewSearchFilterConfig);
				startIndex++;
			}
			dsPrivateViewCustomFilterConfigMap.put(dsPrivateViewHierarchyFilterConfig.getPropertId(),
					dsPrivateViewHierarchyFilterConfig);
		}
		return dsPrivateViewCustomFilterConfigMap;
	}

	private void addPrivateViewSearchLookupControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig dsPrivateViewSearchControlButtonLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		dsPrivateViewSearchControlButtonLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		dsPrivateViewSearchControlButtonLayout.setAddToParent(true);
		dsPrivateViewSearchControlButtonLayout.setParentComponentId(namespace
				+ GtnFrameworkReportStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		dsPrivateViewSearchControlButtonLayout.setComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		dsPrivateViewSearchControlButtonLayout.setSpacing(true);
		dsPrivateViewSearchControlButtonLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(dsPrivateViewSearchControlButtonLayout);

		GtnUIFrameworkComponentConfig dsPrivateViewSelectButton = new GtnUIFrameworkComponentConfig();
		dsPrivateViewSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		dsPrivateViewSelectButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupSelectButton");
		dsPrivateViewSelectButton.setComponentName("SELECT");
		dsPrivateViewSelectButton.setEnable(false);
		dsPrivateViewSelectButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		dsPrivateViewSelectButton.setAddToParent(true);

		componentList.add(dsPrivateViewSelectButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig privateViewSelectAction = new GtnUIFrameWorkActionConfig();
		privateViewSelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		actionParameter.add(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PRIVATEVIEW_SEARCHLOOKUP);
		actionParameter.add(Arrays.asList(GtnFrameworkReportStringConstants.VIEW_NAME_FILTER));
		actionParameter
				.add(Arrays.asList(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PRIVATEVIEW_SEARCHLOOKUP));
		privateViewSelectAction.setActionParameterList(actionParameter);
		actionConfigList.add(privateViewSelectAction);

		GtnUIFrameWorkActionConfig privateViewPopupCloseAction = new GtnUIFrameWorkActionConfig();
		privateViewPopupCloseAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		privateViewPopupCloseAction
				.addActionParameter(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PRIVATEVIEW);
		actionConfigList.add(privateViewPopupCloseAction);

		GtnUIFrameWorkActionConfig dsPrivateViewloadAction = new GtnUIFrameWorkActionConfig();
		dsPrivateViewloadAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		dsPrivateViewloadAction.addActionParameter(GtnReportDataSelectionLoadViewAction.class.getName());
		dsPrivateViewloadAction
				.addActionParameter(GtnFrameworkReportStringConstants.REPORT_DATASELECTION_PRIVATEVIEW_SEARCHLOOKUP);
		dsPrivateViewloadAction.addActionParameter("dataSelectionTab");
		actionConfigList.add(dsPrivateViewloadAction);
		dsPrivateViewSelectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig dsPrivateViewSearchCloseButton = new GtnUIFrameworkComponentConfig();
		dsPrivateViewSearchCloseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		dsPrivateViewSearchCloseButton.setComponentId(
				namespace + GtnFrameworkReportStringConstants.UNDERSCORE + "privateViewSearchLookupCloseButton");
		dsPrivateViewSearchCloseButton.setComponentName("CLOSE");
		dsPrivateViewSearchCloseButton.setParentComponentId(namespace + GtnFrameworkReportStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		dsPrivateViewSearchCloseButton.setAddToParent(true);
		componentList.add(dsPrivateViewSearchCloseButton);
		dsPrivateViewSearchCloseButton.addGtnUIFrameWorkActionConfig(privateViewPopupCloseAction);

	}
}
