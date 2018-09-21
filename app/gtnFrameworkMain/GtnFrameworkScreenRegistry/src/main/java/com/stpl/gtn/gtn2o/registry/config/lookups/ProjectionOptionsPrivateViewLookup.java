package com.stpl.gtn.gtn2o.registry.config.lookups;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class ProjectionOptionsPrivateViewLookup 

{
	private GtnUIFrameworkDataSelectionScreenConfig privatePublicViewConfig = new GtnUIFrameworkDataSelectionScreenConfig();

	public GtnUIFrameworkViewConfig getPrivateViewLookUpView(String namespace) {

		GtnUIFrameworkViewConfig privatePublicViewLookupView = new GtnUIFrameworkViewConfig();
		privatePublicViewLookupView.setViewName(namespace+" "+"View");
		privatePublicViewLookupView.setViewId(namespace+"_"+"lookup");
		privatePublicViewLookupView.setDefaultView(false);
		privatePublicViewLookupView.setResetAllowed(true);
		addPublicPrivateViewLookupComponentList(privatePublicViewLookupView, namespace);
		return privatePublicViewLookupView;
	}

	private void addPublicPrivateViewLookupComponentList(GtnUIFrameworkViewConfig view, String namespace) {

		List<GtnUIFrameworkComponentConfig> privatePublicViewLookupComponentList = new ArrayList<>();
		view.setGtnComponentList(privatePublicViewLookupComponentList);
		addPrivatePublicViewSearchLookupRootLayout(privatePublicViewLookupComponentList, namespace);

	}

	private void addPrivatePublicViewSearchLookupRootLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {

		GtnUIFrameworkComponentConfig privatePublicViewLookupRootLayout = new GtnUIFrameworkComponentConfig();
		privatePublicViewLookupRootLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privatePublicViewLookupRootLayout.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.VIEW_LAYOUT);
		privatePublicViewLookupRootLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privatePublicViewLookupRootLayout.setAddToParent(false);
		privatePublicViewLookupRootLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig privatePublicViewSearchLookupRootConfig = new GtnUIFrameworkLayoutConfig();
		privatePublicViewSearchLookupRootConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		privatePublicViewLookupRootLayout.setGtnLayoutConfig(privatePublicViewSearchLookupRootConfig);
		componentList.add(privatePublicViewLookupRootLayout);
		getPrivatePublicViewSearchLookupRootComponentForPopUp(componentList, namespace);
	}

	public void getPrivatePublicViewSearchLookupRootComponentForPopUp(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig privatePublicLookupRootComponentForPopUp = new GtnUIFrameworkComponentConfig();
		GtnUIFrameworkLayoutConfig privatePublicViewLookupRootComponentConfig = new GtnUIFrameworkLayoutConfig();
		privatePublicViewLookupRootComponentConfig.setLayoutType(GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
		privatePublicLookupRootComponentForPopUp.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privatePublicLookupRootComponentForPopUp.setGtnLayoutConfig(privatePublicViewLookupRootComponentConfig);
		privatePublicLookupRootComponentForPopUp.setAddToParent(true);
		privatePublicLookupRootComponentForPopUp.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privatePublicLookupRootComponentForPopUp.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privatePublicLookupRootComponentForPopUp.setParentComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.VIEW_LAYOUT);
		privatePublicLookupRootComponentForPopUp.setSpacing(true);
		privatePublicLookupRootComponentForPopUp.setMargin(true);
		componentList.add(privatePublicLookupRootComponentForPopUp);

		privatePublicViewSearchLookupSearchCriteriaPanel(componentList, namespace);
		privateViewSearchLookupSearchAndResetButtonLayout(componentList, namespace);
		privateViewSearchLookupResultsPanel(componentList, namespace);
		addPrivateViewSearchLookupControlPopUpButtonLayout(componentList, namespace);
	}

	private void privatePublicViewSearchLookupSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig privatePublicViewSearchLookupSearchCriteriaPanel = new GtnUIFrameworkComponentConfig();
		privatePublicViewSearchLookupSearchCriteriaPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		privatePublicViewSearchLookupSearchCriteriaPanel.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.CRITERIA_PANEL);
		privatePublicViewSearchLookupSearchCriteriaPanel.setComponentName("Search Criteria");
		privatePublicViewSearchLookupSearchCriteriaPanel.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privatePublicViewSearchLookupSearchCriteriaPanel.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		privatePublicViewSearchLookupSearchCriteriaPanel.setAddToParent(true);
		privatePublicViewSearchLookupSearchCriteriaPanel.addComponentStyle("stpl-margin-left-10");
		privatePublicViewSearchLookupSearchCriteriaPanel.addComponentStyle("stpl-margin-top-11");
		componentList.add(privatePublicViewSearchLookupSearchCriteriaPanel);
		privatePublicViewSearchLookupSearchCriteriaLayout(componentList, namespace);
	}

	private void privatePublicViewSearchLookupSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkLayoutConfig privatePublicViewSearchLookupSearchCriteriaLayout = new GtnUIFrameworkLayoutConfig();
		privatePublicViewSearchLookupSearchCriteriaLayout.setLayoutType(GtnUIFrameworkLayoutType.CSS_LAYOUT);
		GtnUIFrameworkComponentConfig privatePublicViewSearchLookupSearchCriteriaConfig = new GtnUIFrameworkComponentConfig();
		privatePublicViewSearchLookupSearchCriteriaConfig.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privatePublicViewSearchLookupSearchCriteriaConfig.setComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.CRITERIA_LAYOUT);
		privatePublicViewSearchLookupSearchCriteriaConfig.setComponentName("Search Criteria");
		privatePublicViewSearchLookupSearchCriteriaConfig.setAddToParent(true);
		privatePublicViewSearchLookupSearchCriteriaConfig.setComponentWidth("90%");
		privatePublicViewSearchLookupSearchCriteriaConfig.setComponentHight("50px");
		privatePublicViewSearchLookupSearchCriteriaConfig.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.CRITERIA_PANEL);
		privatePublicViewSearchLookupSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.POPUP_TEXTBOX_STYLE);
		privatePublicViewSearchLookupSearchCriteriaConfig.addComponentStyle(GtnFrameworkCssConstants.STPL_MARGIN_TOP_10);
		privatePublicViewSearchLookupSearchCriteriaConfig.setGtnLayoutConfig(privatePublicViewSearchLookupSearchCriteriaLayout);
		componentList.add(privatePublicViewSearchLookupSearchCriteriaConfig);
		addViewNameTextBox(componentList, namespace);
	}

	private void addViewNameTextBox(List<GtnUIFrameworkComponentConfig> componentList, String namespace) {
		GtnUIFrameworkComponentConfig horizontalViewNameLayout = privatePublicViewConfig.getHorizontalLayoutConfig(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "horizontalViewNameLayout", namespace
						+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkForecastingStringConstants.CRITERIA_LAYOUT);
		componentList.add(horizontalViewNameLayout);

		GtnUIFrameworkComponentConfig addViewNameTextBox = new GtnUIFrameworkComponentConfig();
		addViewNameTextBox.setComponentType(GtnUIFrameworkComponentType.TEXTBOX_VAADIN8);
		addViewNameTextBox.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME);
		addViewNameTextBox.setComponentName("View Name");
		addViewNameTextBox.setAddToParent(true);
		addViewNameTextBox.setParentComponentId(horizontalViewNameLayout.getComponentId());
		addViewNameTextBox.setComponentWsFieldId(GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME);
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

	private void privateViewSearchLookupSearchAndResetButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupSearchAndResetLayout = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSearchAndResetLayout.setComponentType(GtnUIFrameworkComponentType.LAYOUT);
		privateViewSearchLookupSearchAndResetLayout.setAddToParent(true);
		privateViewSearchLookupSearchAndResetLayout.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
		privateViewSearchLookupSearchAndResetLayout
				.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		privateViewSearchLookupSearchAndResetLayout.setSpacing(true);
		GtnUIFrameworkLayoutConfig conf = new GtnUIFrameworkLayoutConfig();
		privateViewSearchLookupSearchAndResetLayout.setGtnLayoutConfig(conf);
		componentList.add(privateViewSearchLookupSearchAndResetLayout);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		
		GtnUIFrameworkComponentConfig privateViewSearchLookupSearchButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupSearchButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupSearchButton.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.SEARCH_BUTTON);
		privateViewSearchLookupSearchButton.setComponentName("SEARCH");
		privateViewSearchLookupSearchButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkForecastingStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		privateViewSearchLookupSearchButton.setAddToParent(true);
                
                List<GtnUIFrameWorkActionConfig> actionConfigListSearch = new ArrayList<>();
                GtnUIFrameWorkActionConfig loadDataSearchTableActionConfig = new GtnUIFrameWorkActionConfig();
                loadDataSearchTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
                loadDataSearchTableActionConfig.setActionParameterList(
                Arrays.asList(new Object[]{namespace+"_"+GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE}));
                loadDataSearchTableActionConfig.setFieldValues(
                        Arrays.asList(new String[]{namespace+"_"+GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME}));
                actionConfigListSearch.add(loadDataSearchTableActionConfig);
                privateViewSearchLookupSearchButton.setGtnUIFrameWorkActionConfigList(actionConfigListSearch);

		componentList.add(privateViewSearchLookupSearchButton);
		
		GtnUIFrameWorkActionConfig privateViewValidationActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.V8_VALIDATION_ACTION);
		privateViewValidationActionConfig.setFieldValues(Arrays.asList(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME));
		
		GtnUIFrameWorkActionConfig privateViewValidationAlertActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		privateViewValidationAlertActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.INVALID_SEARCH);
		privateViewValidationAlertActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);
		
		privateViewValidationActionConfig.setActionParameterList(Arrays.asList(GtnUIFrameworkValidationType.AND,Arrays.asList(privateViewValidationAlertActionConfig)));
		actionConfigList.add(privateViewValidationActionConfig);
		
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_GRID_ACTION);
		loadDataTableActionConfig.setActionParameterList(
				Arrays.asList(new Object[] { namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE }));
		loadDataTableActionConfig.setFieldValues(Arrays.asList(new String[] { namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME }));
		
		actionConfigList.add(loadDataTableActionConfig);
		privateViewSearchLookupSearchButton.setGtnUIFrameWorkActionConfigList(actionConfigList);

		GtnUIFrameworkComponentConfig privateViewSearchLookupResetButton = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupResetButton.setComponentType(GtnUIFrameworkComponentType.BUTTON);
		privateViewSearchLookupResetButton.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "privateViewSearchLookupResetButton");
		privateViewSearchLookupResetButton.setComponentName("RESET");
		privateViewSearchLookupResetButton.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkForecastingStringConstants.PRIVATE_VIEW_SEARCH_LOOKUP_SEARCH_AND_RESET_LAYOUT);
		privateViewSearchLookupResetButton.setAddToParent(true);
		
		GtnUIFrameWorkActionConfig privateViewResetActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.V8_RESET_ACTION);
		privateViewResetActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION);
		privateViewResetActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.RESET_CONFIRMATION_MESSAGE);
		privateViewResetActionConfig.addActionParameter(Arrays.asList(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_VIEW_NAME,namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE));
		privateViewResetActionConfig.addActionParameter(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY,GtnFrameworkCommonStringConstants.STRING_EMPTY));
		privateViewSearchLookupResetButton.addGtnUIFrameWorkActionConfig(privateViewResetActionConfig);
		
		componentList.add(privateViewSearchLookupResetButton);
	}

	private void privateViewSearchLookupResultsPanel(List<GtnUIFrameworkComponentConfig> componentList,
			String namespace) {
		GtnUIFrameworkComponentConfig privateViewSearchLookupResultsPanel = new GtnUIFrameworkComponentConfig();
		privateViewSearchLookupResultsPanel.setComponentType(GtnUIFrameworkComponentType.PANEL);
		privateViewSearchLookupResultsPanel.setComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.RESULTS_PANEL);
		privateViewSearchLookupResultsPanel.setComponentName("Results");
		privateViewSearchLookupResultsPanel.setParentComponentId(namespace
				+ GtnFrameworkForecastingStringConstants.UNDERSCORE + GtnFrameworkCommonConstants.ROOT_VERTICAL_LAYOUT);
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
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.RESULT_CONFIG);

		privateViewSearchLookupResultConfig.setGtnLayoutConfig(privateViewSearchLookupResultLayout);
		privateViewSearchLookupResultConfig.setParentComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.RESULTS_PANEL);
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
		privateViewPagedTableComponent.setComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		privateViewPagedTableComponent.setComponentName("Results");
		privateViewPagedTableComponent.setParentComponentId(
				namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE +GtnFrameworkForecastingStringConstants.RESULT_CONFIG);
		privateViewPagedTableComponent.setAddToParent(true);
		privateViewPagedTableComponent.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
		List<String> tableStyle = new ArrayList<>();
		tableStyle.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyle.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyle.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		privateViewPagedTableComponent.setComponentStyle(tableStyle);
		privateViewPagedTableComponent.setModuleName("serviceRegistry");
		componentList.add(privateViewPagedTableComponent);
		GtnUIFrameworkPagedTableConfig privateViewPagedTableConfig = new GtnUIFrameworkPagedTableConfig();
		privateViewPagedTableConfig.setEditable(false);
		privateViewPagedTableConfig.setFilterBar(true);
		privateViewPagedTableConfig.setSelectable(true);
		privateViewPagedTableConfig.setPaginationOff(true);
		
		GtnUIFrameWorkActionConfig alertAction = new GtnUIFrameWorkActionConfig();
		alertAction.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		alertAction.addActionParameter("Invalid Search");
		alertAction.addActionParameter("There are no Views that match the search criteria.  Please try again.");
		privateViewPagedTableConfig.setRecordTypeManageActionConfig(alertAction);
		privateViewPagedTableConfig.setCountUrl("/gtnServiceRegistry/serviceRegistryUIControllerMappingWs");
		privateViewPagedTableConfig.setResultSetUrl("/gtnServiceRegistry/serviceRegistryUIControllerMappingWs");
                privateViewPagedTableConfig.setPagedTableWsUrl("/gtnSearch");
                privateViewPagedTableConfig.setRegisteredWebContext("/GtnSearchWebService");
                privateViewPagedTableConfig.setModuleName("generalSearch");
		privateViewPagedTableConfig.setTableColumnDataType(new Class<?>[] {
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_INTEGER,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_LANG_INTEGER, GtnFrameworkCommonConstants.JAVA_LANG_STRING,
				GtnFrameworkCommonConstants.JAVA_UTIL_DATE,GtnFrameworkCommonConstants.JAVA_UTIL_DATE,
				GtnFrameworkCommonConstants.JAVA_LANG_STRING, GtnFrameworkCommonConstants.JAVA_LANG_STRING
				});
		privateViewPagedTableConfig.setColumnHeaders(Arrays.asList("View Name",
				"Description","Time Period:From",
				"Time Period:To", "Customer Hierarchy", "Customer Level","Customer Group","Company","Brand Type","Product Hierarchy","Product Level","Product Group","Created Date","Modified Date","Created By","Business Unit"));
		privateViewPagedTableConfig.setTableColumnMappingId(
				new Object[] { "viewName", "description", "fromDate","toDate","customerHierarchy","customerLevel","customerGroup","company","brandType","productHierarchy","productLevel","productGroup","createdDate","modifiedDate",
						"createdBy","businessUnit"});
		privateViewPagedTableConfig.setQueryName("privatePublic");
                List<String> additionalSearchCriteria = new ArrayList<>();
		additionalSearchCriteria.add(namespace);
                additionalSearchCriteria.add("non mandated");
                privateViewPagedTableConfig.setAdditionalSearchCriteriaListValues(additionalSearchCriteria);

		privateViewPagedTableConfig.setCustomFilterConfigMap(getCustomFilterConfig());
		
		privateViewPagedTableConfig.setSelectionListener(true);
	
		
		List<GtnUIFrameWorkActionConfig> privateViewItemClickActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig privateViewItemClickActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		privateViewItemClickActionConfig.addActionParameter(GtnSelectButtonEnableAction.class.getName());
		privateViewItemClickActionConfig.addActionParameter(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE + "privateViewSearchLookupSelectButton");
		privateViewItemClickActionConfig.addActionParameter(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		privateViewItemClickActionConfigList.add(privateViewItemClickActionConfig);
		privateViewPagedTableConfig.setItemClickActionConfigList(privateViewItemClickActionConfigList);
		
		GtnUIFrameWorkActionConfig recordNotFoundAlertActionConfig = new GtnUIFrameWorkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		recordNotFoundAlertActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.INVALID_SEARCH);
		recordNotFoundAlertActionConfig.addActionParameter(GtnFrameworkForecastingStringConstants.VIEW_INVALID_SEARCH_MESSAGE_BODY);
		privateViewPagedTableConfig.setRecordTypeManageActionConfig(recordNotFoundAlertActionConfig);

		privateViewPagedTableComponent.setGtnPagedTableConfig(privateViewPagedTableConfig);

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

	private void addPrivateViewSearchLookupControlPopUpButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
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
				namespace +GtnFrameworkForecastingStringConstants.UNDERSCORE + "privateViewSearchLookupSelectButton");
		privateViewSearchLookupSelectButton.setComponentName("SELECT");
		privateViewSearchLookupSelectButton.setEnable(false);
		privateViewSearchLookupSelectButton
				.setParentComponentId(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
						+ GtnFrameworkCommonConstants.CONTROL_POP_UP_BUTTON_LAYOUT);
		privateViewSearchLookupSelectButton.setAddToParent(true);

		componentList.add(privateViewSearchLookupSelectButton);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig privateViewSelectAction = new GtnUIFrameWorkActionConfig();
		privateViewSelectAction.setActionType(GtnUIFrameworkActionType.V8_POP_UP_SELECT_ACTION);
		List<Object> actionParameter = new ArrayList<>();
		actionParameter.add(namespace + GtnFrameworkForecastingStringConstants.UNDERSCORE
				+ GtnFrameworkCommonConstants.PRIVATE_SEARCH_RESULT_TABLE);
		actionParameter.add(namespace+"_"+"lookup");
		actionParameter.add(Arrays.asList("viewName"));
		actionParameter.add(Arrays.asList("Commercial Forecasting_privateViewLookup"));
		privateViewSelectAction.setActionParameterList(actionParameter);
		actionConfigList.add(privateViewSelectAction);

		GtnUIFrameWorkActionConfig privateViewClosepopup = new GtnUIFrameWorkActionConfig();
		privateViewClosepopup.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		privateViewClosepopup.addActionParameter(GtnFrameworkCommonConstants.PRIVATE_VIEW_SEARCH_LOOKUP_VIEW);
		actionConfigList.add(privateViewClosepopup);

		GtnUIFrameWorkActionConfig loadViewAction = new GtnUIFrameWorkActionConfig();
		loadViewAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadViewAction.addActionParameter( GtnForecastingDataSelectionLoadViewAction.class.getName());
		loadViewAction.addActionParameter("Commercial Forecasting_privateViewLookup");
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
		privateViewSearchLookupCloseButton.addGtnUIFrameWorkActionConfig(privateViewClosepopup);

	}
	
}
