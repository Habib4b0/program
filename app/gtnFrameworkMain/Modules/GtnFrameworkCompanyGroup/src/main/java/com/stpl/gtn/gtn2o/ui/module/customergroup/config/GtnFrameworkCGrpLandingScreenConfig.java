package com.stpl.gtn.gtn2o.ui.module.customergroup.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.customergroup.action.validation.GtnFrameworkCGrpDeleteValidationAction;
import com.stpl.gtn.gtn2o.ui.module.customergroup.constants.GtnFrameworkCGrpClassContants;
import com.stpl.gtn.gtn2o.ui.module.customergroup.constants.GtnFrameworkCGrpStringContants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;

public class GtnFrameworkCGrpLandingScreenConfig {

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnFrameworkComponentConfigProvider companyGrpComponentProvider = GtnFrameworkComponentConfigProvider
				.getInstance();
		GtnUIFrameworkViewConfig companyGroupSearchView = companyGrpComponentProvider.getViewConfig("Search View",
				"V001", true);
		addComponentList(companyGroupSearchView, companyGrpComponentProvider);
		return companyGroupSearchView;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {
		List<GtnUIFrameworkComponentConfig> cmpnyGrpComponentList = new ArrayList<>();
		view.setGtnComponentList(cmpnyGrpComponentList);
		addSearchCriteriaPanel(cmpnyGrpComponentList, companyGrpComponentProvider);
		addResultPanel(cmpnyGrpComponentList, companyGrpComponentProvider);
		addActionButtonLayout(cmpnyGrpComponentList, companyGrpComponentProvider);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig customerGroupSearchPanel = companyGrpComponentProvider
				.getPanelConfig(GtnFrameworkCGrpStringContants.C_GRPSEARCH_CRITERIA_PANEL, false, null);
		customerGroupSearchPanel.setComponentName("Search Criteria");
		customerGroupSearchPanel.setAuthorizationIncluded(true);
		customerGroupSearchPanel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(customerGroupSearchPanel);
		addFieldLayout(componentList, companyGrpComponentProvider);
		addButtonLayout(componentList, companyGrpComponentProvider);
	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {
		GtnUIFrameworkComponentConfig searchResultPanelConfig = companyGrpComponentProvider
				.getPanelConfig("cGrpResultPanel", false, null);
		searchResultPanelConfig.setComponentName("Results");
		searchResultPanelConfig.setAuthorizationIncluded(true);
		componentList.add(searchResultPanelConfig);

		addResultLayout(componentList, companyGrpComponentProvider);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {
		GtnUIFrameworkComponentConfig searchResultTableLayout = companyGrpComponentProvider
				.getHorizontalLayoutConfig("cGrpResultlayout", true, "cGrpResultPanel");

		searchResultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(searchResultTableLayout);

		addPagedTableComponent(componentList, companyGrpComponentProvider);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {
		GtnUIFrameworkComponentConfig customerGroupSearchLayout = companyGrpComponentProvider.getGtnCssLayoutConfig(
				GtnFrameworkCGrpStringContants.C_GRP_SEARCH_CRITERIALAYOUT, true,
				GtnFrameworkCGrpStringContants.C_GRPSEARCH_CRITERIA_PANEL, GtnUIFrameworkLayoutType.CSS_LAYOUT);

		List<String> styleList = new ArrayList<>();
		styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		customerGroupSearchLayout.setComponentStyle(styleList);
		componentList.add(customerGroupSearchLayout);
		addFieldComponent(componentList, companyGrpComponentProvider);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig cGroupSearchCriteriaButtonLayout = companyGrpComponentProvider
				.getCssLayoutConfig(GtnFrameworkCGrpStringContants.C_GRP_SEARCH_CRITERIALAYOUT, false, null);
		cGroupSearchCriteriaButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cGroupSearchCriteriaButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		cGroupSearchCriteriaButtonLayout.setSpacing(true);
		cGroupSearchCriteriaButtonLayout.setMargin(true);
		componentList.add(cGroupSearchCriteriaButtonLayout);

		addSearchButtonComponent(componentList, companyGrpComponentProvider);
		addAuditSearchButtonComponent(componentList, companyGrpComponentProvider);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		addCGrpName(componentList, companyGrpComponentProvider);
		addCGrpNo(componentList, companyGrpComponentProvider);
		addCGrpDesc(componentList, companyGrpComponentProvider);
	}

	private void addCGrpName(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig customerGrpNamelayout = companyGrpComponentProvider.getHorizontalLayoutConfig(
				"cGrpNamelayout", true, GtnFrameworkCGrpStringContants.C_GRP_SEARCH_CRITERIALAYOUT);
		componentList.add(customerGrpNamelayout);

		GtnUIFrameworkComponentConfig customerGroupNameConfig = companyGrpComponentProvider
				.getUIFrameworkComponentConfig(GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NAME, true,
						customerGrpNamelayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		customerGroupNameConfig.setAuthorizationIncluded(true);
		customerGroupNameConfig.setComponentName("Customer Group Name");

		GtnUIFrameworkValidationConfig customerGrpNameValidationConfig = new GtnUIFrameworkValidationConfig();
		customerGrpNameValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		customerGroupNameConfig.setGtnUIFrameworkValidationConfig(customerGrpNameValidationConfig);
		componentList.add(customerGroupNameConfig);
	}

	private void addCGrpNo(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig customerGrpNolayout = companyGrpComponentProvider.getHorizontalLayoutConfig(
				"cGrpNolayout", true, GtnFrameworkCGrpStringContants.C_GRP_SEARCH_CRITERIALAYOUT);
		componentList.add(customerGrpNolayout);

		GtnUIFrameworkComponentConfig customerGroupNoConfig = companyGrpComponentProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NO, true, customerGrpNolayout.getComponentId(),
				GtnUIFrameworkComponentType.TEXTBOX);
		customerGroupNoConfig.setAuthorizationIncluded(true);
		customerGroupNoConfig.setComponentName("Customer Group No");

		GtnUIFrameworkValidationConfig customerNoValidationConfig = new GtnUIFrameworkValidationConfig();
		customerNoValidationConfig.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		customerGroupNoConfig.setGtnUIFrameworkValidationConfig(customerNoValidationConfig);

		componentList.add(customerGroupNoConfig);
	}

	private void addCGrpDesc(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig customerGrpDesclayout = companyGrpComponentProvider.getHorizontalLayoutConfig(
				"cGrpDesclayout", true, GtnFrameworkCGrpStringContants.C_GRP_SEARCH_CRITERIALAYOUT);
		componentList.add(customerGrpDesclayout);

		GtnUIFrameworkComponentConfig customerGroupDescConfig = companyGrpComponentProvider
				.getUIFrameworkComponentConfig(GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_DESC, true,
						customerGrpDesclayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		customerGroupDescConfig.setAuthorizationIncluded(true);
		customerGroupDescConfig.setComponentName("Customer Group Description");

		GtnUIFrameworkValidationConfig customerGrpDescValidationConfig = new GtnUIFrameworkValidationConfig();
		customerGrpDescValidationConfig
				.setConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));
		customerGroupDescConfig.setGtnUIFrameworkValidationConfig(customerGrpDescValidationConfig);

		componentList.add(customerGroupDescConfig);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig cGtpSearchBtnLayout = companyGrpComponentProvider.getHorizontalLayoutConfig(
				"cGrpGtnSearch01layout", true, GtnFrameworkCGrpStringContants.C_GRP_SEARCH_CRITERIALAYOUT);
		componentList.add(cGtpSearchBtnLayout);

		GtnUIFrameworkComponentConfig cGrpSearchButtonConfig = companyGrpComponentProvider
				.getUIFrameworkComponentConfig("cGrpGtnSearch01", true, cGtpSearchBtnLayout.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		cGrpSearchButtonConfig.setComponentName("SEARCH");
		cGrpSearchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(cGrpSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig srchBtnValidationActionConfig = new GtnUIFrameWorkActionConfig();
		srchBtnValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		srchBtnValidationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NAME,
				GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NO, GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_DESC));

		GtnUIFrameWorkActionConfig searchAlertActionConfig = new GtnUIFrameWorkActionConfig();
		searchAlertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_SEARCH_ERROR);
		alertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_NO_SEARCH_FOUND);

		searchAlertActionConfig.setActionParameterList(alertParamsList);
		Object errorMsg = GtnUIFrameworkValidationType.OR;
		srchBtnValidationActionConfig
				.setActionParameterList(Arrays.asList(errorMsg, Arrays.asList(searchAlertActionConfig)));
		searchActionConfigList.add(srchBtnValidationActionConfig);

		GtnUIFrameWorkActionConfig loadSearchTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadSearchTableActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		loadSearchTableActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_TABLE_LOAD_ACTION);
		loadSearchTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NAME,
				GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_DESC, GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NO));
		searchActionConfigList.add(loadSearchTableActionConfig);

		GtnUIFrameWorkActionConfig srchNotificationActionConfig = new GtnUIFrameWorkActionConfig();
		srchNotificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		srchNotificationActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE);
		searchActionConfigList.add(srchNotificationActionConfig);
		// To Enable buttons (Edit, Delete, Copy) while search is clicked
		GtnUIFrameWorkActionConfig componentDisableAction = new GtnUIFrameWorkActionConfig();
		componentDisableAction.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		Object[] disableField = new String[] { GtnFrameworkCGrpStringContants.C_GRPGTN_EDIT_BUTTON,
				GtnFrameworkCGrpStringContants.C_GRP_GTN_COPY_BUTTON,
				GtnFrameworkCGrpStringContants.C_GRP_GTN_DELETE_BUTTON };

		componentDisableAction.setActionParameterList(Arrays.asList(disableField));
		searchActionConfigList.add(componentDisableAction);
		cGrpSearchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}

	private void addAuditSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig cGtpAuditSearchBtnLayout = companyGrpComponentProvider.getHorizontalLayoutConfig(
				"gtnAudictSearch01layout", true, GtnFrameworkCGrpStringContants.C_GRP_SEARCH_CRITERIALAYOUT);
		componentList.add(cGtpAuditSearchBtnLayout);

		GtnUIFrameworkComponentConfig cGrpAuditSearchButtonConfig = companyGrpComponentProvider
				.getUIFrameworkComponentConfig("gtnAudictSearch01", true, cGtpAuditSearchBtnLayout.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		cGrpAuditSearchButtonConfig.setComponentName("AUDIT SEARCH");
		cGrpAuditSearchButtonConfig.setAuthorizationIncluded(true);
		componentList.add(cGrpAuditSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> auditActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig auditBtnValidationActionConfig = new GtnUIFrameWorkActionConfig();
		auditBtnValidationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		auditBtnValidationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NAME,
				GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NO, GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_DESC));

		GtnUIFrameWorkActionConfig auditBtnAlertActionConfig = new GtnUIFrameWorkActionConfig();
		auditBtnAlertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

		List<Object> auditAlertParamsList = new ArrayList<>();
		auditAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_SEARCH_ERROR);
		auditAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_NO_SEARCH_FOUND);

		auditBtnAlertActionConfig.setActionParameterList(auditAlertParamsList);
		Object validationType = GtnUIFrameworkValidationType.OR;
		auditBtnValidationActionConfig
				.setActionParameterList(Arrays.asList(validationType, Arrays.asList(auditBtnAlertActionConfig)));
		auditActionConfigList.add(auditBtnValidationActionConfig);

		GtnUIFrameWorkActionConfig auditSearchTableActionConfig = new GtnUIFrameWorkActionConfig();
		auditSearchTableActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		auditSearchTableActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_TABLE_LOAD_ACTION);
		auditSearchTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NAME,
				GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_DESC, GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NO));
		auditActionConfigList.add(auditSearchTableActionConfig);

		GtnUIFrameWorkActionConfig auditBtnNoticationActionConfig = new GtnUIFrameWorkActionConfig();
		auditBtnNoticationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		auditBtnNoticationActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE);
		auditActionConfigList.add(auditBtnNoticationActionConfig);
		// To Disable buttons (Edit, Delete, Copy) while Audit search is clicked
		GtnUIFrameWorkActionConfig auditBtnDisableAction = new GtnUIFrameWorkActionConfig();
		auditBtnDisableAction.setActionType(GtnUIFrameworkActionType.DISABLE_ACTION);
		Object[] disableField = new String[] { GtnFrameworkCGrpStringContants.C_GRPGTN_EDIT_BUTTON,
				GtnFrameworkCGrpStringContants.C_GRP_GTN_COPY_BUTTON,
				GtnFrameworkCGrpStringContants.C_GRP_GTN_DELETE_BUTTON };

		auditBtnDisableAction.setActionParameterList(Arrays.asList(disableField));
		auditActionConfigList.add(auditBtnDisableAction);
		cGrpAuditSearchButtonConfig.setGtnUIFrameWorkActionConfigList(auditActionConfigList);

	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig costmerGroupSearchResultConfig = companyGrpComponentProvider
				.getUIFrameworkComponentConfig(GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE, true,
						"cGrpResultlayout", GtnUIFrameworkComponentType.PAGEDTABLE);
		costmerGroupSearchResultConfig.setAuthorizationIncluded(true);
		costmerGroupSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(costmerGroupSearchResultConfig);

		GtnUIFrameworkPagedTableConfig cGrpsearchResultsConfig = new GtnUIFrameworkPagedTableConfig();
		cGrpsearchResultsConfig.setEditable(false);
		cGrpsearchResultsConfig.setFilterBar(true);
		cGrpsearchResultsConfig.setSelectable(true);
		cGrpsearchResultsConfig.setSinkItemPerPageWithPageLength(false);
		cGrpsearchResultsConfig.setPageLength(10);
		cGrpsearchResultsConfig.setTableColumnDataType(new Class[] { String.class, String.class, String.class,
				Integer.class, Date.class, Date.class, String.class, });

		cGrpsearchResultsConfig
				.setTableVisibleHeader(new String[] { "Customer Group Name", "Customer Group Description",
						"Customer Group No", "Version No", "Creation Date", "Modified Date", "Created By" });
		cGrpsearchResultsConfig.setTableColumnMappingId(new Object[] {
				GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NAME, GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_DESC,
				GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NO, "versionNo", "createdDate", "modifiedDate",
				"createdBy" });
		cGrpsearchResultsConfig.setExtraColumn(new Object[] { GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_SID });
		cGrpsearchResultsConfig.setExtraColumnDataType(new Class[] { Integer.class });
		cGrpsearchResultsConfig.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		cGrpsearchResultsConfig.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		cGrpsearchResultsConfig.setModuleName("companyGroupsMaster");
		cGrpsearchResultsConfig.setQueryName("cGrpSearchQuery");
		cGrpsearchResultsConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.COMPANY_GROUPS);
		cGrpsearchResultsConfig.setCustomFilterConfigMap(getCustomFilterConfig());

		cGrpsearchResultsConfig.setDoubleClickEnable(true);
		List<GtnUIFrameWorkActionConfig> searchTableActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig tempclearActionConfig = new GtnUIFrameWorkActionConfig();
		tempclearActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tempclearActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_TEMP_TABLE_CLEAR_ACTION);
		searchTableActionConfigList.add(tempclearActionConfig);

		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter("V002");
		searchTableActionConfigList.add(navigationActionConfig);

		/**
		 * Loading in Edit mode
		 */
		GtnUIFrameWorkActionConfig doubleActionActionConfig = new GtnUIFrameWorkActionConfig();
		doubleActionActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		List<Object> parameters = new ArrayList<>();
		parameters.add(GtnFrameworkCGrpClassContants.COMPANY_GRP_EDIT_ACTION);
		parameters.add(GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE);
		parameters.add(GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_SID);
		parameters.add(Boolean.TRUE);
		doubleActionActionConfig.setActionParameterList(parameters);
		searchTableActionConfigList.add(doubleActionActionConfig);
		cGrpsearchResultsConfig.setGtnUIFrameWorkActionConfigList(searchTableActionConfigList);
		costmerGroupSearchResultConfig.setGtnPagedTableConfig(cGrpsearchResultsConfig);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig cGrpactionBtnLayout = companyGrpComponentProvider
				.getCssLayoutConfig(GtnFrameworkCGrpStringContants.C_GRP_ACTION_BUTTONLAYOUT, false, null);
		cGrpactionBtnLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		cGrpactionBtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		cGrpactionBtnLayout.setSpacing(true);
		cGrpactionBtnLayout.setMargin(true);

		componentList.add(cGrpactionBtnLayout);
		addResetButtonComponent(componentList, companyGrpComponentProvider);
		addAddButtonComponent(componentList, companyGrpComponentProvider);
		addEditButtonComponent(componentList, companyGrpComponentProvider);
		addViewButtonComponent(componentList, companyGrpComponentProvider);
		addCopyButtonComponent(componentList, companyGrpComponentProvider);
		addDeleteButtonComponent(componentList, companyGrpComponentProvider);
		addExcelButtonComponent(componentList, companyGrpComponentProvider);
	}

	private void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig itemGrpExcelBtnLayout = companyGrpComponentProvider.getHorizontalLayoutConfig(
				"cGrpgtnExcelButtonlayout", true, GtnFrameworkCGrpStringContants.C_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(itemGrpExcelBtnLayout);

		GtnUIFrameworkComponentConfig excelButtonConfig = companyGrpComponentProvider.getUIFrameworkComponentConfig(
				"resultTableExcelBtn", true, itemGrpExcelBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.EXCEL_BUTTON);
		excelButtonConfig.setAuthorizationIncluded(true);

		GtnUIFrameworkExcelButtonConfig cGrpExcelButtonInputConfig = new GtnUIFrameworkExcelButtonConfig();
		cGrpExcelButtonInputConfig.setIsTreeTable(false);
		cGrpExcelButtonInputConfig.setExportFileName("Customer Group");
		cGrpExcelButtonInputConfig.setTitleNeeded(true);
		cGrpExcelButtonInputConfig.setExportFromTable(true);
		cGrpExcelButtonInputConfig.setExportTableId(GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE);

		GtnUIFrameWorkActionConfig cGrpResultTableExcelAction = new GtnUIFrameWorkActionConfig();
		cGrpResultTableExcelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		cGrpResultTableExcelAction.addActionParameter(cGrpExcelButtonInputConfig);

		excelButtonConfig.setGtnUIFrameworkExcelButtonConfig(cGrpExcelButtonInputConfig);
		excelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(cGrpResultTableExcelAction));
		componentList.add(excelButtonConfig);

	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig customerGrpAddBtnLayout = companyGrpComponentProvider.getHorizontalLayoutConfig(
				"cGrpgtnAddButtonlayout", true, GtnFrameworkCGrpStringContants.C_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(customerGrpAddBtnLayout);

		GtnUIFrameworkComponentConfig addButtonConfig = companyGrpComponentProvider.getUIFrameworkComponentConfig(
				"cGrpgtnAddButton", true, customerGrpAddBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		addButtonConfig.setAuthorizationIncluded(true);
		addButtonConfig.setComponentName("ADD");
		componentList.add(addButtonConfig);

		List<GtnUIFrameWorkActionConfig> addBtnActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig tempclearActionConfig = new GtnUIFrameWorkActionConfig();
		tempclearActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tempclearActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_TEMP_TABLE_CLEAR_ACTION);
		addBtnActionConfigList.add(tempclearActionConfig);
		GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
		navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		navigationActionConfig.addActionParameter("V002");

		addBtnActionConfigList.add(navigationActionConfig);

		GtnUIFrameWorkActionConfig addActionConfig = new GtnUIFrameWorkActionConfig();
		addActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		addActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_ADD_ACTION);

		addBtnActionConfigList.add(addActionConfig);
		addButtonConfig.setGtnUIFrameWorkActionConfigList(addBtnActionConfigList);
	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig customerGrpEditBtnLayout = companyGrpComponentProvider.getHorizontalLayoutConfig(
				"cGrpgtnEditButtonlayout", true, GtnFrameworkCGrpStringContants.C_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(customerGrpEditBtnLayout);

		GtnUIFrameworkComponentConfig editButtonConfig = companyGrpComponentProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCGrpStringContants.C_GRPGTN_EDIT_BUTTON, true, customerGrpEditBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		editButtonConfig.setAuthorizationIncluded(true);
		editButtonConfig.setComponentName("EDIT");
		componentList.add(editButtonConfig);

		List<GtnUIFrameWorkActionConfig> editActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig editAlertActionConfig = new GtnUIFrameWorkActionConfig();
		editAlertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> editAlertParamsList = new ArrayList<>();
		editAlertParamsList.add(GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE);
		editAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_EDIT_MSG_HEADER);
		editAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_EDIT_ERROR_001);

		editAlertActionConfig.setActionParameterList(editAlertParamsList);
		editActionConfigList.add(editAlertActionConfig);

		GtnUIFrameWorkActionConfig tempclearActionConfig = new GtnUIFrameWorkActionConfig();
		tempclearActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tempclearActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_TEMP_TABLE_CLEAR_ACTION);
		editActionConfigList.add(tempclearActionConfig);

		GtnUIFrameWorkActionConfig editNavigationActionConfig = new GtnUIFrameWorkActionConfig();
		editNavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		editNavigationActionConfig.addActionParameter("V002");
		editActionConfigList.add(editNavigationActionConfig);

		GtnUIFrameWorkActionConfig editBtnActionConfig = new GtnUIFrameWorkActionConfig();
		editBtnActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editBtnActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_EDIT_ACTION);
		editBtnActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE);
		editBtnActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_SID);
		editBtnActionConfig.addActionParameter(Boolean.TRUE);
		editActionConfigList.add(editBtnActionConfig);
		editButtonConfig.setGtnUIFrameWorkActionConfigList(editActionConfigList);
	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig customerGrpViewBtnLayout = companyGrpComponentProvider.getHorizontalLayoutConfig(
				"cGrpGtnViewButtonlayout", true, GtnFrameworkCGrpStringContants.C_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(customerGrpViewBtnLayout);

		GtnUIFrameworkComponentConfig viewButtonConfig = companyGrpComponentProvider.getUIFrameworkComponentConfig(
				"cGrpGtnViewButton", true, customerGrpViewBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		viewButtonConfig.setAuthorizationIncluded(true);
		viewButtonConfig.setComponentName("VIEW");
		componentList.add(viewButtonConfig);

		List<GtnUIFrameWorkActionConfig> viewActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig viewAlertActionConfig = new GtnUIFrameWorkActionConfig();
		viewAlertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> viewAlertParamsList = new ArrayList<>();
		viewAlertParamsList.add(GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE);
		viewAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_VIEW_MSG_HEADER);
		viewAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_EDIT_VIEW_001);

		viewAlertActionConfig.setActionParameterList(viewAlertParamsList);
		viewActionConfigList.add(viewAlertActionConfig);
		GtnUIFrameWorkActionConfig tempclearActionConfig = new GtnUIFrameWorkActionConfig();
		tempclearActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tempclearActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_TEMP_TABLE_CLEAR_ACTION);
		viewActionConfigList.add(tempclearActionConfig);

		GtnUIFrameWorkActionConfig viewNavigationActionConfig = new GtnUIFrameWorkActionConfig();
		viewNavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		viewNavigationActionConfig.addActionParameter("V002");
		viewActionConfigList.add(viewNavigationActionConfig);
		GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();
		editActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_EDIT_ACTION);
		editActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE);
		editActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_SID);
		editActionConfig.addActionParameter(Boolean.FALSE);
		viewActionConfigList.add(editActionConfig);
		viewButtonConfig.setGtnUIFrameWorkActionConfigList(viewActionConfigList);
	}

	private void addCopyButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig customerGrpCopyBtnLayout = companyGrpComponentProvider.getHorizontalLayoutConfig(
				"cGrpGtnCopyButtonlayout", true, GtnFrameworkCGrpStringContants.C_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(customerGrpCopyBtnLayout);

		GtnUIFrameworkComponentConfig copyButtonConfig = companyGrpComponentProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCGrpStringContants.C_GRP_GTN_COPY_BUTTON, true, customerGrpCopyBtnLayout.getComponentId(),
				GtnUIFrameworkComponentType.BUTTON);
		copyButtonConfig.setComponentName("COPY");
		copyButtonConfig.setAuthorizationIncluded(true);
		componentList.add(copyButtonConfig);

		List<GtnUIFrameWorkActionConfig> copyActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig copyAlertActionConfig = new GtnUIFrameWorkActionConfig();
		copyAlertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);

		List<Object> copyAlertParamsList = new ArrayList<>();
		copyAlertParamsList.add(GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE);
		copyAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_EDIT_ERROR);
		copyAlertParamsList.add(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_COPY_ERROR);

		copyAlertActionConfig.setActionParameterList(copyAlertParamsList);
		copyActionConfigList.add(copyAlertActionConfig);
		GtnUIFrameWorkActionConfig tempclearActionConfig = new GtnUIFrameWorkActionConfig();
		tempclearActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tempclearActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_TEMP_TABLE_CLEAR_ACTION);
		copyActionConfigList.add(tempclearActionConfig);

		GtnUIFrameWorkActionConfig copyNavigationActionConfig = new GtnUIFrameWorkActionConfig();
		copyNavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		copyNavigationActionConfig.addActionParameter("V002");
		copyActionConfigList.add(copyNavigationActionConfig);

		GtnUIFrameWorkActionConfig copyActionConfig = new GtnUIFrameWorkActionConfig();
		copyActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		copyActionConfig.addActionParameter(GtnFrameworkCGrpClassContants.COMPANY_GRP_COPY_ACTION);
		copyActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE);
		copyActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_SID);
		copyActionConfig.addActionParameter(Boolean.TRUE);
		copyActionConfigList.add(copyActionConfig);
		copyButtonConfig.setGtnUIFrameWorkActionConfigList(copyActionConfigList);
	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {

		GtnUIFrameworkComponentConfig customerGrpDeleteBtnLayout = companyGrpComponentProvider
				.getHorizontalLayoutConfig("cGrpGtnDeleteButtonlayout", true,
						GtnFrameworkCGrpStringContants.C_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(customerGrpDeleteBtnLayout);

		GtnUIFrameworkComponentConfig deleteButtonConfig = companyGrpComponentProvider.getUIFrameworkComponentConfig(
				GtnFrameworkCGrpStringContants.C_GRP_GTN_DELETE_BUTTON, true,
				customerGrpDeleteBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		deleteButtonConfig.setComponentName("DELETE");
		deleteButtonConfig.setAuthorizationIncluded(true);
		componentList.add(deleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> deleteActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NAME,
				GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NO, GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_DESC));
		deleteActionConfig.addActionParameter(GtnFrameworkCGrpDeleteValidationAction.class.getName());
		deleteActionConfigList.add(deleteActionConfig);
		deleteButtonConfig.setGtnUIFrameWorkActionConfigList(deleteActionConfigList);

	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
			GtnFrameworkComponentConfigProvider companyGrpComponentProvider) {
		GtnUIFrameworkComponentConfig customerGrpResetBtnLayout = companyGrpComponentProvider.getHorizontalLayoutConfig(
				"gtnReset01layout", true, GtnFrameworkCGrpStringContants.C_GRP_ACTION_BUTTONLAYOUT);
		componentList.add(customerGrpResetBtnLayout);

		GtnUIFrameworkComponentConfig resetButtonConfig = companyGrpComponentProvider.getUIFrameworkComponentConfig(
				"gtnReset01", true, customerGrpResetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		resetButtonConfig.setComponentName("RESET");
		resetButtonConfig.setAuthorizationIncluded(true);
		componentList.add(resetButtonConfig);

		List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		resetActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_RESET_HEADER);
		resetActionConfig.addActionParameter(GtnFrameworkCGrpStringContants.CUSTOMER_GRP_VALIDATION_MSG_RESET_ERROR);
		List<String> resetComponentIdList = new ArrayList<>();
		String[] componentIdArray = { GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NAME,
				GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_NO, GtnFrameworkCGrpStringContants.CUSTOMER_GROUP_DESC,
				GtnFrameworkCGrpStringContants.C_GRPSEARCH_RESULT_TABLE };
		resetComponentIdList.addAll(Arrays.asList(componentIdArray));

		List<Object> resetComponentValueList = new ArrayList<>();

		Object[] resetComponentValueArray = { GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY, null };
		resetComponentValueList.addAll(Arrays.asList(resetComponentValueArray));

		resetActionConfig.addActionParameter(resetComponentIdList);
		resetActionConfig.addActionParameter(resetComponentValueList);

		resetActionConfigList.add(resetActionConfig);
		resetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> cGrpcustomFilterConfigMap = new HashMap<>();
		String[] propertyIds = { "createdBy" };
		String[] listNameArray = { "USERS" };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig cGrpCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			cGrpCustomFilterConfig.setPropertId(propertyIds[i]);
			cGrpCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig cGrpCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			cGrpCustomFilterComponentConfig.setComponentId("customFilterComboBox");
			cGrpCustomFilterComponentConfig.setComponentName("customFilterComboBox");
			cGrpCustomFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			cGrpCustomFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(listNameArray[i]);
			cGrpCustomFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			cGrpCustomFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			cGrpCustomFilterConfig.setGtnComponentConfig(cGrpCustomFilterComponentConfig);
			cGrpcustomFilterConfigMap.put(cGrpCustomFilterConfig.getPropertId(), cGrpCustomFilterConfig);

		}
		return cGrpcustomFilterConfigMap;
	}
}
