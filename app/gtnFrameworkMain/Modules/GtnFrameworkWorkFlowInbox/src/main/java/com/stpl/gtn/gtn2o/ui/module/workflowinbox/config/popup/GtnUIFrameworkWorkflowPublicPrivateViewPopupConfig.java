package com.stpl.gtn.gtn2o.ui.module.workflowinbox.config.popup;

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
import com.stpl.gtn.gtn2o.ui.framework.component.layout.GtnUIFrameworkLayoutConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkConditionalValidationType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnFrameworkWorkflowPopulateFieldsAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnUIFrameworkConfirmedLookupDeleteButtonAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.action.GtnUIFrameworkUrlDeleteButtonAction;
import com.stpl.gtn.gtn2o.ui.module.workflowinbox.constants.GtnFrameworkWorkflowInboxClassConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnUIFrameworkWorkflowPublicPrivateViewPopupConfig {
	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkWorkflowPublicPrivateViewPopupConfig.class);
	private GtnFrameworkComponentConfigProvider configProvider = GtnFrameworkComponentConfigProvider.getInstance();

	public GtnUIFrameworkViewConfig getSearchView(String viewType) {
		GtnUIFrameworkViewConfig view = configProvider.getViewConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEW_SEARCHPOPUP,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEW_SEARCH_POPUP, false);
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addRootLayout(componentList, viewType);
		return view;
	}

	private void addRootLayout(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getVerticalLayoutConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEW_ROOTLAYOUT, false, null);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addFieldPanel(componentList, viewType);
		addButtonLayout(componentList, viewType);
		addResultPanel(componentList, viewType);
		addActionButtonLayout(componentList, viewType);
	}

	private void addFieldPanel(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHCRITERIA_PANEL, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEW_ROOTLAYOUT);
		panelConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.VIEW_SEARCH);
		panelConfig.setAuthorizationIncluded(true);
		componentList.add(panelConfig);
		addFieldLayout(componentList, viewType);
	}

	private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkLayoutConfig layout = new GtnUIFrameworkLayoutConfig();
		layout.setLayoutType(GtnUIFrameworkLayoutType.COL3_LAYOUT);
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getUIFrameworkComponentConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHCRITERIA_LAYOUT, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHCRITERIA_PANEL,
				GtnUIFrameworkComponentType.LAYOUT);
		gtnLayout.setGtnLayoutConfig(layout);
		componentList.add(gtnLayout);
		addFieldComponent(componentList, viewType);
	}

	private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		addViewName(componentList, viewType);
	}

	private void addViewName(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWVIEWNAMELAYOUT, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHCRITERIA_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig companyIdConfig = configProvider.getUIFrameworkComponentConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWVIEWNAME, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWVIEWNAMELAYOUT,
				GtnUIFrameworkComponentType.TEXTBOX);
		companyIdConfig.setAuthorizationIncluded(true);
		companyIdConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.VIEW_NAME);

		GtnUIFrameworkValidationConfig valConfig = configProvider
				.getValidationConfigForConditionList(Arrays.asList(GtnUIFrameworkConditionalValidationType.NOT_EMPTY));

		companyIdConfig.setGtnUIFrameworkValidationConfig(valConfig);

		componentList.add(companyIdConfig);
	}

	private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWBUTTON_LAYOUT, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEW_ROOTLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addSearchButtonComponent(componentList, viewType);
		addResetButtonComponent(componentList, viewType);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWGTNSEARCH_LAYOUT, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWBUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				GtnFrameworkWorkflowInboxClassConstants.VIEWGTNSEARCH, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWGTNSEARCH_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.SEARCH);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig
				.setFieldValues(Arrays.asList(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWVIEWNAME));

		GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
		alertActionConfig.setActionType(GtnUIFrameworkActionType.WARNING_ACTION);

		List<Object> alertParamsList = new ArrayList<>();
		alertParamsList.add(GtnFrameworkWorkflowInboxClassConstants.NO_SEARCH_CRITERIA);
		alertParamsList.add(GtnFrameworkWorkflowInboxClassConstants.PLEASE_ENTER_THE_CRITERIA);

		alertActionConfig.setActionParameterList(alertParamsList);

		validationActionConfig.setActionParameterList(
				Arrays.asList(GtnUIFrameworkValidationType.AND, Arrays.asList(alertActionConfig)));
		actionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		loadDataTableActionConfig
				.addActionParameter(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHRESULT_TABLE);
		loadDataTableActionConfig
				.setFieldValues(Arrays.asList(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWVIEWNAME));

		actionConfigList.add(loadDataTableActionConfig);

		GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
		notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		notificationActionConfig
				.addActionParameter(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHRESULT_TABLE);
		actionConfigList.add(notificationActionConfig);

		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkComponentConfig panelConfig = configProvider.getPanelConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWRESULT_PANEL, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEW_ROOTLAYOUT);
		panelConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.RESULTS);
		componentList.add(panelConfig);
		addResultLayout(componentList, viewType);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWRESULT_LAYOUT, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWRESULT_PANEL);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayout);
		addPagedTableComponent(componentList, viewType);
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {

		GtnUIFrameworkComponentConfig wfViewPopupResultConfig = configProvider.getUIFrameworkComponentConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHRESULT_TABLE, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWRESULT_LAYOUT,
				GtnUIFrameworkComponentType.PAGEDTABLE);
		wfViewPopupResultConfig.setAuthorizationIncluded(true);
		wfViewPopupResultConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.RESULTS);
		wfViewPopupResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		wfViewPopupResultConfig.setComponentHight(GtnFrameworkCssConstants.PIXEL_400);
		List<String> tableStyleList = new ArrayList<>();
		tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
		tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
		tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
		wfViewPopupResultConfig.setComponentStyle(tableStyleList);

		GtnUIFrameWorkActionConfig itemClickActionConfig = new GtnUIFrameWorkActionConfig();
		itemClickActionConfig.setActionType(GtnUIFrameworkActionType.ENABLE_ACTION);
		itemClickActionConfig.addActionParameter(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSELECTBUTTON);
		itemClickActionConfig.addActionParameter(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWDELETEBUTTON);
		wfViewPopupResultConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(itemClickActionConfig));

		componentList.add(wfViewPopupResultConfig);

		GtnUIFrameworkPagedTableConfig searchResults = new GtnUIFrameworkPagedTableConfig();
		searchResults.setEditable(false);
		searchResults.setFilterBar(true);
		searchResults.setSelectable(true);
		searchResults.setTableColumnDataType(new Class<?>[] { String.class, String.class, String.class, String.class,
				 String.class, String.class, String.class, Date.class, String.class, Date.class });
		searchResults.setTableVisibleHeader(new String[] { GtnFrameworkWorkflowInboxClassConstants.VIEW_NAME,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWID_NAME,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWCOMPONENT_NAME,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCRIPTION_NAME,
				GtnFrameworkWorkflowInboxClassConstants.COMPANY_NAME,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESS_UNIT_NAME,
				GtnFrameworkWorkflowInboxClassConstants.CREATEDBY_NAME,
				GtnFrameworkWorkflowInboxClassConstants.CREATION_DATE,
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDBY_NAME,
				GtnFrameworkWorkflowInboxClassConstants.APPROVED_DATE });
		searchResults.setExtraColumn(new Object[] { GtnFrameworkWorkflowInboxClassConstants.WORKFLOWSID,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTID, GtnFrameworkWorkflowInboxClassConstants.CONTRACTNO,
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNO, GtnFrameworkWorkflowInboxClassConstants.COMPANYNAME,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITID,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNO,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAME,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAME, GtnFrameworkWorkflowInboxClassConstants.ITEMNO,
				GtnFrameworkWorkflowInboxClassConstants.ITEMNAME,
				GtnFrameworkWorkflowInboxClassConstants.FORECASTDEDUCTIONVALUE,
				GtnFrameworkWorkflowInboxClassConstants.FORECASTDEDUCTIONLEVEL,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTTYPE, GtnFrameworkWorkflowInboxClassConstants.COMPANYID,
				GtnFrameworkWorkflowInboxClassConstants.ITEMID,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITIDRETURNS,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNORETURNS,
				GtnFrameworkWorkflowInboxClassConstants.ITEMNORETURNS,
				GtnFrameworkWorkflowInboxClassConstants.ITEMNAMERETURNS,
				GtnFrameworkWorkflowInboxClassConstants.ITEMIDRETURNS,
				GtnFrameworkWorkflowInboxClassConstants.ADJUSTMENTTYPE,
				GtnFrameworkWorkflowInboxClassConstants.COMPANYARM,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITARM,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTIDARM,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNOARM,
				GtnFrameworkWorkflowInboxClassConstants.BRANDIDARM,
				GtnFrameworkWorkflowInboxClassConstants.CONTRACTNAMEARM,
				GtnFrameworkWorkflowInboxClassConstants.ITEMNOARM, GtnFrameworkWorkflowInboxClassConstants.ITEMNAMEARM,
				GtnFrameworkWorkflowInboxClassConstants.BRANDNAMEARM, GtnFrameworkWorkflowInboxClassConstants.GLDATEARM,
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONLEVELARM,
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONVALUEARM,
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNOARM,
				GtnFrameworkWorkflowInboxClassConstants.DEDUCTIONNAMEARM,
				GtnFrameworkWorkflowInboxClassConstants.CREATEDTO_PRIVATE,
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDTO_PRIVATE,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAMERETURNS

		});
		searchResults.setTableColumnMappingId(new Object[] { GtnFrameworkWorkflowInboxClassConstants.VIEWNAMEPRIVATE,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWIDPRIVATE,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWNAMEPRIVATE,
				GtnFrameworkWorkflowInboxClassConstants.WORKFLOWDESCPRIVATE,
				GtnFrameworkWorkflowInboxClassConstants.COMPANYNAMEPRIVATE,
				GtnFrameworkWorkflowInboxClassConstants.BUSINESSUNITNAMEPRIVATE,
				GtnFrameworkWorkflowInboxClassConstants.CREATEDBYPRIVATE, "creationFromDatePrivate",
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDBYPRIVATE, "approvedDateFromPrivate" });
		searchResults.setCountUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setResultSetUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH);
		searchResults.setModuleName(GtnFrameworkWorkflowInboxClassConstants.WORKFLOWINBOX);
		searchResults.setQueryName(viewType + GtnFrameworkWorkflowInboxClassConstants.SEARCHQUERY);
		searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.WF_PRIVATE_VIEW_SEARCH);
		searchResults.setItemClickListener(true);
		searchResults.setCustomFilterConfigMap(getCustomFilterConfig());
		wfViewPopupResultConfig.setGtnPagedTableConfig(searchResults);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getCssLayoutConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWACTIONBUTTON_LAYOUT, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEW_ROOTLAYOUT);
		gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		gtnLayout.getGtnLayoutConfig().setComponentColumnSize(12);
		componentList.add(gtnLayout);
		addSelectButtonComponent(componentList, viewType);
		addCloseButtonComponent(componentList, viewType);
		addDeleteButtonComponent(componentList, viewType);
	}

	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWGTNRESET_LAYOUT, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWBUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWGTNRESET, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWGTNRESET_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.RESET_LOWERCASE);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> paramsList = new ArrayList<>();
		paramsList.add(GtnFrameworkWorkflowInboxClassConstants.GTN_PRIVATE_VIEW_VALIDATION_MSG_RESET_HEADER);
		paramsList.add(GtnFrameworkWorkflowInboxClassConstants.GTN_PRIVATE_VIEW_VALIDATION_MSG_RESET);

		Map<String, Object> resetMap = new HashMap<>();

		resetMap.put(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWVIEWNAME,
				GtnFrameworkCommonStringConstants.STRING_EMPTY);
		paramsList.add(resetMap);

		resetActionConfig.setActionParameterList(paramsList);
		actionConfigList.add(resetActionConfig);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addSelectButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSELECTBUTTON_LAYOUT, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWACTIONBUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSELECTBUTTON, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSELECTBUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.SELECT);
		searchButtonConfig.setAddToParent(true);
		searchButtonConfig.setEnable(false);
		componentList.add(searchButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
		selectAction.setActionType(GtnUIFrameworkActionType.POPUP_SELECT_ACTION);
		List<Object> actionParameterList = new ArrayList<>();
		actionParameterList.add(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHRESULT_TABLE);
		actionParameterList.add(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEW_SMALL);
		actionParameterList.add(Arrays.asList(GtnFrameworkWorkflowInboxClassConstants.VIEWNAMEPRIVATE));
		actionParameterList.add(Arrays.asList(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEW_SMALL));
		selectAction.setActionParameterList(actionParameterList);
		actionConfigList.add(selectAction);

		GtnUIFrameWorkActionConfig popActionConfig = new GtnUIFrameWorkActionConfig();
		popActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		popActionConfig.addActionParameter(GtnFrameworkWorkflowPopulateFieldsAction.class.getName());
		popActionConfig.addActionParameter(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHRESULT_TABLE);
		actionConfigList.add(popActionConfig);

		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEW_SEARCH_POPUP);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
	}

	private void addCloseButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWCLOSEBUTTON_LAYOUT, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWACTIONBUTTON_LAYOUT);
		componentList.add(gtnLayout);

		GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWCLOSEBUTTON, true,
				viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWCLOSEBUTTON_LAYOUT,
				GtnUIFrameworkComponentType.BUTTON);
		searchButtonConfig.setAuthorizationIncluded(true);
		searchButtonConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CLOSE);
		componentList.add(searchButtonConfig);
		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
		closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
		closeAction.addActionParameter(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEW_SEARCH_POPUP);
		actionConfigList.add(closeAction);
		searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String viewType) {
		try {
			GtnUIFrameworkComponentConfig gtnLayout = configProvider.getHorizontalLayoutConfig(
					viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWDELETEBUTTON_LAYOUT, true,
					viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWACTIONBUTTON_LAYOUT);
			componentList.add(gtnLayout);

			GtnUIFrameworkComponentConfig searchButtonConfig = configProvider.getUIFrameworkComponentConfig(
					viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWDELETEBUTTON, true,
					viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWDELETEBUTTON_LAYOUT,
					GtnUIFrameworkComponentType.BUTTON);
			searchButtonConfig.setAuthorizationIncluded(true);
			searchButtonConfig.setComponentName(GtnFrameworkWorkflowInboxClassConstants.DELETE);
			searchButtonConfig.setEnable(false);
			componentList.add(searchButtonConfig);

			List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
			GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
			deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			deleteActionConfig.addActionParameter(GtnUIFrameworkConfirmedLookupDeleteButtonAction.class.getName());
			deleteActionConfig.addActionParameter(viewType);
			actionConfigList.add(deleteActionConfig);
			searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

			List<GtnUIFrameWorkActionConfig> gtnUIFrameworkComponentDeleteConfigList = new ArrayList<>();

			List<GtnUIFrameWorkActionConfig> configList = new ArrayList<>();
			GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();

			GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();

			resetActionConfig.setActionType(GtnUIFrameworkActionType.SET_DEFAULT_ACTION);
			Map<String, Object> deleteResetMap = new HashMap<>();
			List<Object> deleteResetParams = new ArrayList<>();
			deleteResetMap.put(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHRESULT_TABLE, null);
			deleteResetParams.add(deleteResetMap);
			resetActionConfig.setActionParameterList(deleteResetParams);

			loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

			List<Object> actionParams = new ArrayList<>();
			actionParams.add(viewType + GtnFrameworkWorkflowInboxClassConstants.VIEWSEARCHRESULT_TABLE);
			loadDataTableActionConfig.setActionParameterList(actionParams);

			List<GtnUIFrameWorkActionConfig> actionConfigList1 = new ArrayList<>();
			GtnUIFrameWorkActionConfig deleteActionConfig1 = new GtnUIFrameWorkActionConfig();
			deleteActionConfig1.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			deleteActionConfig1.addActionParameter(GtnUIFrameworkUrlDeleteButtonAction.class.getName());
			actionConfigList1.add(deleteActionConfig1);
			searchButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList1);

			configList.add(resetActionConfig);
			configList.add(loadDataTableActionConfig);
			GtnUIFrameWorkActionConfig wfViewPopupDeleteConfig = new GtnUIFrameWorkActionConfig();
			wfViewPopupDeleteConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);

			List<Object> deleteConfirmationAlertParams = new ArrayList<>();
			deleteConfirmationAlertParams.add("Confirm Deletion");
			deleteConfirmationAlertParams.add("Are you sure you want to delete record?");

			deleteConfirmationAlertParams.add(configList);

			wfViewPopupDeleteConfig.setActionParameterList(deleteConfirmationAlertParams);

			gtnUIFrameworkComponentDeleteConfigList.add(wfViewPopupDeleteConfig);

			searchButtonConfig.setGtnUIFrameWorkActionConfigList(gtnUIFrameworkComponentDeleteConfigList);
		} catch (Exception ex) {
			gtnLogger.error(ex.getMessage(), ex);
		}
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
		String[] propertyIds = { GtnFrameworkWorkflowInboxClassConstants.CREATEDBYPRIVATE,
				GtnFrameworkWorkflowInboxClassConstants.APPROVEDBYPRIVATE };
		String[] listNameArray = { GtnFrameworkWorkflowInboxClassConstants.USERS,
				GtnFrameworkWorkflowInboxClassConstants.USERS };
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig wfViewPopupCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			wfViewPopupCustomFilterConfig.setPropertId(propertyIds[i]);
			wfViewPopupCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig wfViewPopupCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			wfViewPopupCustomFilterComponentConfig
					.setComponentId(GtnFrameworkWorkflowInboxClassConstants.CUSTOMFILTERCOMBOBOX);
			wfViewPopupCustomFilterComponentConfig
					.setComponentName(GtnFrameworkWorkflowInboxClassConstants.CUSTOMFILTERCOMBOBOX);
			wfViewPopupCustomFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			wfViewPopupCustomFilterComponentConfig.getGtnComboboxConfig().setComboBoxType(listNameArray[i]);
			wfViewPopupCustomFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			wfViewPopupCustomFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			wfViewPopupCustomFilterConfig.setGtnComponentConfig(wfViewPopupCustomFilterComponentConfig);
			customFilterConfigMap.put(wfViewPopupCustomFilterConfig.getPropertId(), wfViewPopupCustomFilterConfig);

		}
		return customFilterConfigMap;
	}

}
