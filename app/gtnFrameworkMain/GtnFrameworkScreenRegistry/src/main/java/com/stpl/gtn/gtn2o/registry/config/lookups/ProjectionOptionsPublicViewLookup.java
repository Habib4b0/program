/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.registry.config.lookups;

import com.stpl.gtn.gtn2o.registry.action.GtnForecastingDataSelectionLoadViewAction;
import com.stpl.gtn.gtn2o.registry.action.GtnSelectButtonEnableAction;
import com.stpl.gtn.gtn2o.registry.config.GtnUIFrameworkDataSelectionScreenConfig;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author anandh.karuppusamy
 */
public class ProjectionOptionsPublicViewLookup {
    	private GtnUIFrameworkDataSelectionScreenConfig PublicViewConfig = new GtnUIFrameworkDataSelectionScreenConfig();

	public GtnUIFrameworkViewConfig getPublicViewLookUpView(String namespace) {

		GtnUIFrameworkViewConfig PublicViewLookupView = new GtnUIFrameworkViewConfig();
		PublicViewLookupView.setViewName("Public View");
		PublicViewLookupView.setViewId(GtnFrameworkCommonConstants.PUBLIC_VIEW_SEARCH_LOOKUP_VIEW);
		PublicViewLookupView.setDefaultView(false);
		PublicViewLookupView.setResetAllowed(true);
		addPublicViewLookupComponentList(PublicViewLookupView, namespace);
		return PublicViewLookupView;
	}

	private void addPublicViewLookupComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> publicViewLookupComponentList = new ArrayList<>();
		view.setGtnComponentList(publicViewLookupComponentList);
		addPublicViewSearchLookupRootLayout(publicViewLookupComponentList, namespace);

	}

	private void addPublicViewSearchLookupRootLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig publicViewLookupRootLayout = new GtnUIFrameworkComponentConfig();
		publicViewLookupRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewLookupRootLayout.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.VIEW_LAYOUT_PUBLIC);
		publicViewLookupRootLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewLookupRootLayout.setAddToParent(false);
		publicViewLookupRootLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig publicViewSearchLookupRootConfig = new GtnUIFrameworkLayoutConfig();
		publicViewSearchLookupRootConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		publicViewLookupRootLayout.setGtnLayoutConfig(publicViewSearchLookupRootConfig);
		componentList.add(publicViewLookupRootLayout);
		getPublicViewSearchLookupRootComponentForPopUp(componentList, namespace);
	}

	public void getPublicViewSearchLookupRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicLookupRootComponentForPopUp=  new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig publicViewLookupRootComponentConfig = new GtnUIFrameworkLayoutConfig();
		publicViewLookupRootComponentConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		publicLookupRootComponentForPopUp.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicLookupRootComponentForPopUp.setGtnLayoutConfig(publicViewLookupRootComponentConfig);
		publicLookupRootComponentForPopUp.setAddToParent(true);
		publicLookupRootComponentForPopUp.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicLookupRootComponentForPopUp.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicLookupRootComponentForPopUp.setParentComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.VIEW_LAYOUT_PUBLIC);
		publicLookupRootComponentForPopUp.setSpacing(true);
		publicLookupRootComponentForPopUp.setMargin(true);
		componentList.add(publicLookupRootComponentForPopUp);

		PublicViewSearchLookupSearchCriteriaPanel(componentList, namespace);
		publicViewSearchLookupSearchAndResetButtonLayout(componentList, namespace);
		publicViewSearchLookupResultsPanel(componentList, namespace);
		addPublicViewSearchLookupControlPopUpButtonLayout(componentList, namespace);
	}

	private void PublicViewSearchLookupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewSearchLookupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		publicViewSearchLookupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		publicViewSearchLookupSearchCriteriaPanel.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.CRITERIA_PANEL_PUBLIC);
		publicViewSearchLookupSearchCriteriaPanel.setComponentName("Search Criteria");
		publicViewSearchLookupSearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewSearchLookupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewSearchLookupSearchCriteriaPanel.setAddToParent(true);
		publicViewSearchLookupSearchCriteriaPanel.addComponentStyle("stpl-margin-left-10");
		publicViewSearchLookupSearchCriteriaPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(publicViewSearchLookupSearchCriteriaPanel);
		PublicViewSearchLookupSearchCriteriaLayout(componentList, namespace);
	}

	private void PublicViewSearchLookupSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig publicViewSearchLookupSearchCriteriaLayout = new GtnUIFrameworkLayoutConfig();
		publicViewSearchLookupSearchCriteriaLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig publicViewSearchLookupSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		publicViewSearchLookupSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewSearchLookupSearchCriteriaConfig.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.CRITERIA_LAYOUT);
		publicViewSearchLookupSearchCriteriaConfig.setComponentName("Search Criteria");
		publicViewSearchLookupSearchCriteriaConfig.setAddToParent(true);
		publicViewSearchLookupSearchCriteriaConfig.setComponentWidth("90%");
		publicViewSearchLookupSearchCriteriaConfig.setComponentHight("50px");
		publicViewSearchLookupSearchCriteriaConfig.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.CRITERIA_PANEL_PUBLIC);
		publicViewSearchLookupSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		publicViewSearchLookupSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		publicViewSearchLookupSearchCriteriaConfig.setGtnLayoutConfig(publicViewSearchLookupSearchCriteriaLayout);
		componentList.add(publicViewSearchLookupSearchCriteriaConfig);
		addViewNamePublicTextBox(componentList, namespace);
	}

	private void addViewNamePublicTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig horizontalViewNameLayout = PublicViewConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "horizontalViewNameLayout", namespace
						+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.CRITERIA_LAYOUT);
		componentList.add(horizontalViewNameLayout);

		GtnUIFrameworkComponentConfig addViewNameTextBox = new GtnUIFrameworkComponentConfig();
		addViewNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		addViewNameTextBox.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_VIEW_NAME);
		addViewNameTextBox.setComponentName("View Name");
		addViewNameTextBox.setAddToParent(true);
		addViewNameTextBox.setParentComponentId(horizontalViewNameLayout.getComponentId());
		addViewNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.PUBLIC_VIEW_NAME);
		addViewNameTextBox.setDefaultFocus(true);
		GtnUIFrameworkTextBoxConfig textBoxConfig = new GtnUIFrameworkTextBoxConfig();
		addViewNameTextBox.setGtnTextBoxConfig(textBoxConfig);
		GtnUIFrameworkValidationConfig viewNameValidationConfig = new GtnUIFrameworkValidationConfig();
		viewNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		viewNameValidationConfig.setAttachRegxValidatior(true);
		viewNameValidationConfig.setFormatString(GtnFrameworkRegexStringConstants.ACCEPT_MIN_0_MAX_100_CHARACTER);
		viewNameValidationConfig.setRegxValidationMessage("View Name Should be less than 100 Characters");
		addViewNameTextBox.setGtnUIFrameworkValidationConfig(viewNameValidationConfig);

		componentList.add(addViewNameTextBox);

	}

	private void publicViewSearchLookupSearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewSearchLookupSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		publicViewSearchLookupSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewSearchLookupSearchAndResetLayout.setAddToParent(true);
		publicViewSearchLookupSearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewSearchLookupSearchAndResetLayout
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.PUBLIC_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		publicViewSearchLookupSearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		publicViewSearchLookupSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(publicViewSearchLookupSearchAndResetLayout);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		
		GtnUIFrameworkComponentConfig publicViewSearchLookupSearchButton = new GtnUIFrameworkComponentConfig();
		publicViewSearchLookupSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		publicViewSearchLookupSearchButton.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.SEARCH_BUTTON);
		publicViewSearchLookupSearchButton.setComponentName("SEARCH");
		publicViewSearchLookupSearchButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.PUBLIC_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		publicViewSearchLookupSearchButton.setAddToParent(true);
                
                List<GtnUIFrameWorkActionConfig> actionConfigListSearch = new ArrayList<>();
                GtnUIFrameWorkActionConfig loadDataSearchTableActionConfig = new GtnUIFrameWorkActionConfig();
                loadDataSearchTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
                loadDataSearchTableActionConfig.setActionParameterList(
                Arrays.asList(new Object[]{namespace+"_"+GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE}));
                loadDataSearchTableActionConfig.setFieldValues(
                        Arrays.asList(new String[]{namespace+"_"+GtnFrameworkCommonConstants.PUBLIC_VIEW_NAME}));
                actionConfigListSearch.add(loadDataSearchTableActionConfig);
                publicViewSearchLookupSearchButton.setGtnUIFrameWorkActionConfigList(actionConfigListSearch);

		componentList.add(publicViewSearchLookupSearchButton);
		
		GtnUIFrameWorkActionConfig publicViewValidationActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		publicViewValidationActionConfig.setFieldValues(Arrays.asList(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_VIEW_NAME));
		
		GtnUIFrameWorkActionConfig privateViewValidationAlertActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		privateViewValidationAlertActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.INVALID_SEARCH);
		privateViewValidationAlertActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);
		
		publicViewValidationActionConfig.setActionParameterList(Arrays.asList(GtnUIFrameworkValidationType.AND,Arrays.asList(privateViewValidationAlertActionConfig)));
		actionConfigList.add(publicViewValidationActionConfig);
		

		GtnUIFrameworkComponentConfig publicViewSearchLookupResetButton = new GtnUIFrameworkComponentConfig();
		publicViewSearchLookupResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		publicViewSearchLookupResetButton.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "publicViewSearchLookupResetButton");
		publicViewSearchLookupResetButton.setComponentName("RESET");
		publicViewSearchLookupResetButton.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.PUBLIC_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		publicViewSearchLookupResetButton.setAddToParent(true);
		
		GtnUIFrameWorkActionConfig publicViewResetActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.V8_RESET_ACTION);
		publicViewResetActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION);
		publicViewResetActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION_MESSAGE);
		publicViewResetActionConfig.addActionParameter(Arrays.asList(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_VIEW_NAME,namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE));
		publicViewResetActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,GtnFrameworkCommonStringConstants.STRING_EMPTY));
		publicViewSearchLookupResetButton.addGtnUIFrameWorkActionConfig(publicViewResetActionConfig);
		
		componentList.add(publicViewSearchLookupResetButton);
	}

	private void publicViewSearchLookupResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig publicViewSearchLookupResultsPanel = new GtnUIFrameworkComponentConfig();
		publicViewSearchLookupResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		publicViewSearchLookupResultsPanel.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.RESULTS_PANEL);
		publicViewSearchLookupResultsPanel.setComponentName("Results");
		publicViewSearchLookupResultsPanel.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		publicViewSearchLookupResultsPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewSearchLookupResultsPanel.setAddToParent(true);
		publicViewSearchLookupResultsPanel.addComponentStyle("stpl-margin-left-10");
		publicViewSearchLookupResultsPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(publicViewSearchLookupResultsPanel);
		publicViewSearchLookupResultLayout(componentList, namespace);

	}

	private void publicViewSearchLookupResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig publicViewSearchLookupResultLayout = new GtnUIFrameworkLayoutConfig();
		publicViewSearchLookupResultLayout.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);

		GtnUIFrameworkComponentConfig publicViewSearchLookupResultConfig = new GtnUIFrameworkComponentConfig();
		publicViewSearchLookupResultConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		publicViewSearchLookupResultConfig.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.RESULT_CONFIG);

		publicViewSearchLookupResultConfig.setGtnLayoutConfig(publicViewSearchLookupResultLayout);
		publicViewSearchLookupResultConfig.setParentComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.RESULTS_PANEL);
		publicViewSearchLookupResultConfig.setAddToParent(true);
		publicViewSearchLookupResultConfig.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		publicViewSearchLookupResultConfig.setComponentHight(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		componentList.add(publicViewSearchLookupResultConfig);
		addPublicViewPagedTableComponent(componentList, namespace);
	}

	private void addPublicViewPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig publicViewPagedTableComponent = new GtnUIFrameworkComponentConfig();
		publicViewPagedTableComponent.setComponentType(GtnUIFrameworkComponentType.PAGED_GRID);
		publicViewPagedTableComponent.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PUBLIC_SEARCH_RESULT_TABLE);
		publicViewPagedTableComponent.setComponentName("Results");
		publicViewPagedTableComponent.setParentComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.RESULT_CONFIG);
		publicViewPagedTableComponent.setAddToParent(true);
		publicViewPagedTableComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		publicViewPagedTableComponent.setComponentStyle(tableStyle);
		publicViewPagedTableComponent.setModuleName("serviceRegistry");
		componentList.add(publicViewPagedTableComponent);
		GtnUIFrameworkPagedTableConfig publicViewPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		publicViewPagedTableConfig.setEditable(false);
		publicViewPagedTableConfig.setFilterBar(true);
		publicViewPagedTableConfig.setSelectable(true);
                publicViewPagedTableConfig.setPaginationOff(true);
		
		GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
		alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		alertAction.addActionParameter("Invalid Search");
		alertAction.addActionParameter("There are no Views that match the search criteria.  Please try again.");
		publicViewPagedTableConfig.setRecordTypeManageActionConfig(alertAction);
		publicViewPagedTableConfig.setCountUrl("/gtnServiceRegistry/serviceRegistryUIControllerMappingWs");
		publicViewPagedTableConfig.setResultSetUrl("/gtnServiceRegistry/serviceRegistryUIControllerMappingWs");
                publicViewPagedTableConfig.setPagedTableWsUrl("/gtnSearch");
                publicViewPagedTableConfig.setRegisteredWebContext("/GtnSearchWebService");
                publicViewPagedTableConfig.setModuleName("generalSearch");
		publicViewPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE,GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING
				});
		publicViewPagedTableConfig.setColumnHeaders(Arrays.asList("View Name",
				"Description","Time Period:From",
				"Time Period:To", "Customer Hierarchy", "Customer Level","Customer Group","Company","Brand Type","Product Hierarchy","Product Level","Product Group","Created Date","Modified Date","Created By","Business Unit"));
		publicViewPagedTableConfig.setTableColumnMappingId(
				new Object[] { "viewName", "description", "fromDate","toDate","customerHierarchy","customerLevel","customerGroup","company","brandType","productHierarchy","productLevel","productGroup","createdDate","modifiedDate",
						"createdBy","businessUnit"});
		publicViewPagedTableConfig.setQueryName("privatePublic");
                List<String> additionalSearchCriteria = new ArrayList<>();
		additionalSearchCriteria.add("Public");
                additionalSearchCriteria.add("non mandated");
                publicViewPagedTableConfig.setAdditionalSearchCriteriaListValues(additionalSearchCriteria);

		publicViewPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		
		publicViewPagedTableConfig.setSelectionListener(true);
		
		List<GtnUIFrameWorkActionConfig> privateViewItemClickActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig privateViewItemClickActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		privateViewItemClickActionConfig.addActionParameter(GtnSelectButtonEnableAction.class.getName());
		privateViewItemClickActionConfig.addActionParameter(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "privateViewSearchLookupSelectButton");
		privateViewItemClickActionConfig.addActionParameter(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		privateViewItemClickActionConfigList.add(privateViewItemClickActionConfig);
		publicViewPagedTableConfig.setItemClickActionConfigList(privateViewItemClickActionConfigList);
		
		GtnUIFrameWorkActionConfig recordNotFoundAlertActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		recordNotFoundAlertActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.INVALID_SEARCH);
		recordNotFoundAlertActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);
		publicViewPagedTableConfig.setRecordTypeManageActionConfig(recordNotFoundAlertActionConfig);

		publicViewPagedTableComponent.setGtnPagedTableConfig(publicViewPagedTableConfig);

	}
	
	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		String[] columnPropertyIds = { "viewName", "description", "fromDate","toDate","customerHierarchy","customerLevel","customerGroup","company","brandType","productHierarchy","productLevel","productGroup","createdDate","modifiedDate","createdBy","businessUnit"};
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> privateViewCustomFilterConfigMap = new HashMap<>(
				columnPropertyIds.length);
		GtnUIFrameworkComponentType[] componentType = { GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8, GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,
				GtnUIFrameworkComponentType.TEXTBOX_VAADIN8,GtnUIFrameworkComponentType.TEXTBOX_VAADIN8};
		String[] custComboboxIds = new String[1];
		String[] custComboBoxType =  new String[1];
		int startIndex = 0;
		for (int i = 0; i < columnPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig privateViewHierarchyFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			privateViewHierarchyFilterConfig.setPropertId(columnPropertyIds[i]);
			privateViewHierarchyFilterConfig.setGtnComponentType(componentType[i]);
			if ((startIndex < custComboboxIds.length)
					&& columnPropertyIds[i].equals(custComboboxIds[startIndex])) {
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

	private void addPublicViewSearchLookupControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupControlPopUpLayout = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig layoutConf = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupControlPopUpLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupControlPopUpLayout.setAddToParent(true);
		privateViewSearchLookupControlPopUpLayout.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupControlPopUpLayout
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		privateViewSearchLookupControlPopUpLayout.setSpacing(true);
		privateViewSearchLookupControlPopUpLayout.setGtnLayoutConfig(layoutConf);
		componentList.add(privateViewSearchLookupControlPopUpLayout);

		GtnUIFrameworkComponentConfig privateViewSearchLookupSelectButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSelectButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupSelectButton.setComponentId(
				namespace +GtnFrameworkForecastingStringConstants.UNDERSCORE + "publicViewSearchLookupSelectButton");
		privateViewSearchLookupSelectButton.setComponentName("SELECT");
		privateViewSearchLookupSelectButton.setEnable(false);
		privateViewSearchLookupSelectButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		privateViewSearchLookupSelectButton.setAddToParent(true);

		componentList.add(privateViewSearchLookupSelectButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig reportCustomerHierarchySelectAction = new GtnUIFrameWorkActionConfig();
		reportCustomerHierarchySelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		actionParameter.add(GtnFrameworkForecastingStringConstants.PUBLIC_VIEW_SEARCH_LOOKUP);
		actionParameter.add(Arrays.asList("viewNameFilter"));
		actionParameter.add(Arrays.asList(GtnFrameworkForecastingStringConstants.PUBLIC_VIEW_SEARCH_LOOKUP));
		reportCustomerHierarchySelectAction.setActionParameterList(actionParameter);
		actionConfigList.add(reportCustomerHierarchySelectAction);

		GtnUIFrameWorkActionConfig reportCustomHierarchyClosepopup = new GtnUIFrameWorkActionConfig();
		reportCustomHierarchyClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		reportCustomHierarchyClosepopup.addActionParameter(GtnFrameworkCommonConstants.PUBLIC_VIEW_SEARCH_LOOKUP_VIEW);
		actionConfigList.add(reportCustomHierarchyClosepopup);

		GtnUIFrameWorkActionConfig loadViewAction = new GtnUIFrameWorkActionConfig();
		loadViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadViewAction.addActionParameter( GtnForecastingDataSelectionLoadViewAction.class.getName());
		loadViewAction.addActionParameter(GtnFrameworkCommonConstants.PUBLIC_VIEW_SEARCH_LOOKUP_VIEW);
		loadViewAction.addActionParameter("Commercial Forecasting");
		actionConfigList.add(loadViewAction);

		GtnUIFrameWorkActionConfig enableAction = new GtnUIFrameWorkActionConfig();
		enableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		enableAction.addActionParameter("reportLandingScreen_dsDeleteView");
		actionConfigList.add(enableAction);
		privateViewSearchLookupSelectButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig privateViewSearchLookupCloseButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupCloseButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupCloseButton.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "privateViewSearchLookupCloseButton");
		privateViewSearchLookupCloseButton.setComponentName("CLOSE");
		privateViewSearchLookupCloseButton.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		privateViewSearchLookupCloseButton.setAddToParent(true);
		componentList.add(privateViewSearchLookupCloseButton);
		privateViewSearchLookupCloseButton.addGtnUIFrameWorkActionConfig(reportCustomHierarchyClosepopup);

	}
    
}
