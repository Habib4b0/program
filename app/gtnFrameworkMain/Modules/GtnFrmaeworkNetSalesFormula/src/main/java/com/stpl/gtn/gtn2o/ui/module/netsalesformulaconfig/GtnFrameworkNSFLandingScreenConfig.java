package com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig;

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
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUIFrameworkTableSearchCompletionAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkEnableDisableAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfCopyAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.GtnUiFrameworkNsfEditAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.action.confirmation.GtnUiFrameworkNsfDeleteConfirmationAction;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFComboBoxTypeConstants;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFConstants;
import com.stpl.gtn.gtn2o.ui.module.netsalesformulaconfig.util.GtnFrameworkNSFMessageConstants;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.netsales.constants.GtnWsNsfCommonConstants;

public class GtnFrameworkNSFLandingScreenConfig {

	private GtnFrameworkComponentConfigProvider componentConfigProvider = GtnFrameworkComponentConfigProvider
			.getInstance();

	public GtnUIFrameworkViewConfig getSearchView() {
		GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
		view.setViewName(GtnFrameworkNSFConstants.getNsfMainView());
		view.setViewId(GtnFrameworkNSFConstants.getNsfMainView());
		view.setDefaultView(true);
		addComponentList(view, view.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		return view;
	}

	private void addComponentList(GtnUIFrameworkViewConfig view, String viewId) {
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		view.setGtnComponentList(componentList);
		addSearchCriteriaPanel(componentList, viewId);
		addSearchButtonLayout(componentList, viewId);
		addResultPanel(componentList, viewId);
		addActionButtonLayout(componentList, viewId);

	}

	private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, String viewId) {

		GtnUIFrameworkComponentConfig nsfLandingScreenSearchCriteriaPanel = componentConfigProvider
				.getPanelConfig(viewId + "searchCriteriaPanel", false, null);
		nsfLandingScreenSearchCriteriaPanel.setComponentName("Search Criteria");
		nsfLandingScreenSearchCriteriaPanel.setAuthorizationIncluded(true);
		componentList.add(nsfLandingScreenSearchCriteriaPanel);
		addSearchCriteriaLayout(componentList, nsfLandingScreenSearchCriteriaPanel.getComponentId(), viewId);
	}

	private void addSearchCriteriaLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		GtnUIFrameworkComponentConfig nsfLandingScreenSearchCriteriaLayout = componentConfigProvider
				.getCssLayoutConfig(viewId + "searchCriteriaMainlayout", true, parentId);
		nsfLandingScreenSearchCriteriaLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
		componentList.add(nsfLandingScreenSearchCriteriaLayout);
		addSearchFieldComponents(componentList, nsfLandingScreenSearchCriteriaLayout.getComponentId(), viewId);
	}

	private void addSearchFieldComponents(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {
		GtnUIFrameworkComponentConfig nsfLandingScreenSearchFieldLayout = componentConfigProvider
				.getCssLayoutConfig(viewId + "searchCriteriaFieldlayout", true, parentId);
		nsfLandingScreenSearchFieldLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
		componentList.add(nsfLandingScreenSearchFieldLayout);

		addFormulaType(componentList, nsfLandingScreenSearchFieldLayout.getComponentId(), viewId);
		addFormulaId(componentList, nsfLandingScreenSearchFieldLayout.getComponentId(), viewId);
		addFormulaNo(componentList, nsfLandingScreenSearchFieldLayout.getComponentId(), viewId);
		addFormulaName(componentList, nsfLandingScreenSearchFieldLayout.getComponentId(), viewId);

	}

	private void addFormulaType(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {
		String componentId = viewId + GtnFrameworkCommonConstants.FORMULA_TYPE;
		GtnUIFrameworkComponentConfig nsfLandingScreenFormulaTypeLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(nsfLandingScreenFormulaTypeLayout);

		GtnUIFrameworkComponentConfig netSalesFormulaType = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, nsfLandingScreenFormulaTypeLayout.getComponentId(),
				GtnUIFrameworkComponentType.COMBOBOX);
		netSalesFormulaType.setAuthorizationIncluded(true);
		netSalesFormulaType.setComponentName("Net Sales Formula Type:");
		componentList.add(netSalesFormulaType);

		GtnUIFrameworkComboBoxConfig netSalesFormulaTypeConfig = componentConfigProvider.getComboBoxConfig(
				GtnFrameworkNSFComboBoxTypeConstants.NS_FORMULA_TYPE,
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_NULL);
		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		netSalesFormulaType.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);
		netSalesFormulaType.setGtnComboboxConfig(netSalesFormulaTypeConfig);
	}

	private void addFormulaId(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.FORMULA_ID;
		GtnUIFrameworkComponentConfig netSalesFormulaIdConfigLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(netSalesFormulaIdConfigLayout);

		GtnUIFrameworkComponentConfig netSalesFormulaIdConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, netSalesFormulaIdConfigLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		netSalesFormulaIdConfig.setComponentName("Net Sales Formula ID:");
		netSalesFormulaIdConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> netSalesFormulaIdConditions = new ArrayList<>();
		netSalesFormulaIdConditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(netSalesFormulaIdConditions);
		netSalesFormulaIdConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(netSalesFormulaIdConfig);
	}

	private void addFormulaNo(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.FORMULA_NO;
		GtnUIFrameworkComponentConfig netSalesFormulaNoLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(netSalesFormulaNoLayout);

		GtnUIFrameworkComponentConfig netSalesFormulaNo = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, netSalesFormulaNoLayout.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		netSalesFormulaNo.setComponentName("Net Sales Formula No:");
		netSalesFormulaNo.setAuthorizationIncluded(true);

		GtnUIFrameworkValidationConfig netSalesFormulaNoValidationConfig = new GtnUIFrameworkValidationConfig();
		List<GtnUIFrameworkConditionalValidationType> netSalesFormulaNoConditions = new ArrayList<>();
		netSalesFormulaNoConditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);
		netSalesFormulaNoValidationConfig.setConditionList(netSalesFormulaNoConditions);
		netSalesFormulaNo.setGtnUIFrameworkValidationConfig(netSalesFormulaNoValidationConfig);
		componentList.add(netSalesFormulaNo);
	}

	private void addFormulaName(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {

		String componentId = viewId + GtnFrameworkCommonConstants.FORMULA_NAME;
		GtnUIFrameworkComponentConfig netSalesFormulaName = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(netSalesFormulaName);

		GtnUIFrameworkComponentConfig netSalesFormulaNameConfig = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, netSalesFormulaName.getComponentId(), GtnUIFrameworkComponentType.TEXTBOX);
		netSalesFormulaNameConfig.setComponentName("Net Sales Formula Name:");
		netSalesFormulaNameConfig.setAuthorizationIncluded(true);
		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();

		List<GtnUIFrameworkConditionalValidationType> conditions = new ArrayList<>();
		conditions.add(GtnUIFrameworkConditionalValidationType.NOT_EMPTY);

		gtnUIFrameworkValidationConfig.setConditionList(conditions);
		netSalesFormulaNameConfig.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(netSalesFormulaNameConfig);

	}

	private void addSearchButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String viewId) {
		String searchButtonLayoutId = viewId + "searchButtonlayout";
		GtnUIFrameworkComponentConfig searchCssLayoutConfig = componentConfigProvider
				.getCssLayoutConfig(searchButtonLayoutId, false, null);
		componentList.add(searchCssLayoutConfig);

		addSearchButtonComponent(componentList, searchCssLayoutConfig.getComponentId(), viewId);
		addResetButtonComponent(componentList, searchCssLayoutConfig.getComponentId(), viewId);
	}

	private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "landingScreenSearchButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig landingScreenSearchButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		landingScreenSearchButtonConfig.setAuthorizationIncluded(true);
		landingScreenSearchButtonConfig.setComponentName("SEARCH");
		componentList.add(landingScreenSearchButtonConfig);

		List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig validationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);

		validationActionConfig.setFieldValues(Arrays.asList(viewId + GtnFrameworkCommonConstants.FORMULA_TYPE,
				viewId + GtnFrameworkCommonConstants.FORMULA_ID, viewId + GtnFrameworkCommonConstants.FORMULA_NO,
				viewId + GtnFrameworkCommonConstants.FORMULA_NAME));
		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);

		List<GtnUIFrameWorkActionConfig> onFailureList = new ArrayList<>();

		GtnUIFrameWorkActionConfig alertActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);
		List<Object> alertParams = new ArrayList<>();
		alertParams.add(GtnFrameworkNSFConstants.getNoSearchCriteria());
		alertParams.add(GtnFrameworkNSFConstants.getPleaseEnterSearchCriteria());

		alertActionConfig.setActionParameterList(alertParams);
		onFailureList.add(alertActionConfig);
		validationActionConfig.addActionParameter(onFailureList);
		searchActionConfigList.add(validationActionConfig);

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

		List<Object> actionParams = new ArrayList<>();
		actionParams.add(viewId + GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);

		loadDataTableActionConfig.setActionParameterList(actionParams);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(viewId + GtnFrameworkCommonConstants.FORMULA_TYPE,
				viewId + GtnFrameworkCommonConstants.FORMULA_ID, viewId + GtnFrameworkCommonConstants.FORMULA_NO,
				viewId + GtnFrameworkCommonConstants.FORMULA_NAME));

		searchActionConfigList.add(loadDataTableActionConfig);
        
		GtnUIFrameWorkActionConfig notificationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.CUSTOM_ACTION);
		notificationActionConfig.addActionParameter(GtnUIFrameworkTableSearchCompletionAction.class.getName());
		notificationActionConfig.addActionParameter(viewId + GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		notificationActionConfig.addActionParameter(" Error ");
		notificationActionConfig.addActionParameter(" There are no records that match the search criteria. ");
		searchActionConfigList.add(notificationActionConfig);
        
        landingScreenSearchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

	}
	private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "landingScreenResetButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig landingScreenResetButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		landingScreenResetButtonConfig.setAuthorizationIncluded(true);
		landingScreenResetButtonConfig.setComponentName("RESET");
		componentList.add(landingScreenResetButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> params = new ArrayList<>();
		params.add(GtnFrameworkNSFConstants.getResetConfirmation());
		params.add(GtnFrameworkNSFConstants.getResetConfirmationMsg());

		params.add(Arrays.asList(viewId + GtnFrameworkCommonConstants.FORMULA_TYPE,
				viewId + GtnFrameworkCommonConstants.FORMULA_ID, viewId + GtnFrameworkCommonConstants.FORMULA_NO,
				viewId + GtnFrameworkCommonConstants.FORMULA_NAME));
		Object defaultValue = null;
		params.add(Arrays.asList(defaultValue, GtnFrameworkCommonStringConstants.STRING_EMPTY,
				GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY));

		resetActionConfig.setActionParameterList(params);
		actionConfigList.add(resetActionConfig);
		landingScreenResetButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String viewId) {
		GtnUIFrameworkComponentConfig resultPanelConfig = componentConfigProvider.getPanelConfig(viewId + "resultPanel",
				false, null);
		resultPanelConfig.setComponentName("Results");
		resultPanelConfig.setAuthorizationIncluded(true);
		componentList.add(resultPanelConfig);
		addResultLayout(componentList, resultPanelConfig.getComponentId(), viewId);
	}

	private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {
		String componentId = viewId + GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE;
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		gtnLayoutConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
		componentList.add(gtnLayoutConfig);

		addPagedTableComponent(componentList, componentId, gtnLayoutConfig.getComponentId());
	}

	private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String componentId,
			String parentId) {

		GtnUIFrameworkComponentConfig landingScreenSearchResultTable = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, parentId, GtnUIFrameworkComponentType.PAGEDTABLE);
		landingScreenSearchResultTable.setAuthorizationIncluded(true);

		landingScreenSearchResultTable.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);

		GtnUIFrameworkValidationConfig gtnUIFrameworkValidationConfig = new GtnUIFrameworkValidationConfig();
		gtnUIFrameworkValidationConfig.setMinSize(1);
		landingScreenSearchResultTable.setGtnUIFrameworkValidationConfig(gtnUIFrameworkValidationConfig);

		componentList.add(landingScreenSearchResultTable);

		GtnUIFrameworkPagedTableConfig landingScreenSearchResultTableConfig = componentConfigProvider
				.getPagedTableConfig(true, true,
						GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
						GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE
								+ GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
						"netSalesRuleSearch", GtnWsNsfCommonConstants.GTN_NSF_LANDING_SEARCH_QUERY_NAME);
		landingScreenSearchResultTableConfig.setEditable(false);
		landingScreenSearchResultTableConfig.setItemPerPage(10);
		landingScreenSearchResultTableConfig.setPageLength(10);
		landingScreenSearchResultTableConfig.setSinkItemPerPageWithPageLength(false);
		landingScreenSearchResultTableConfig.setTableColumnDataType(new Class<?>[] { String.class, String.class,
				String.class, String.class, Date.class, String.class, Date.class, String.class });
		landingScreenSearchResultTableConfig
				.setTableVisibleHeader(GtnFrameworkNSFConstants.getNsfLandingScreenResultTableVisibleHeaders());
		landingScreenSearchResultTableConfig
				.setTableColumnMappingId(GtnFrameworkNSFConstants.getNsfLandingScreenResultTableVisibleColumns());
		landingScreenSearchResultTableConfig.setExtraColumn(new Object[] { GtnFrameworkNSFConstants.getSystemid() });
		landingScreenSearchResultTableConfig.setExtraColumnDataType(new Class<?>[] { Integer.class });
		landingScreenSearchResultTableConfig.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.NET_SALES);
		landingScreenSearchResultTableConfig.setCustomFilterConfigMap(getLandingScreenCustomFilterConfig());
		landingScreenSearchResultTable.setGtnPagedTableConfig(landingScreenSearchResultTableConfig);
	}

	private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String viewId) {
		String crudButtonLayout = viewId + "crudButtonlayout";
		GtnUIFrameworkComponentConfig crudCssLayoutConfig = componentConfigProvider.getCssLayoutConfig(crudButtonLayout,
				false, null);
		componentList.add(crudCssLayoutConfig);

		addAddButtonComponent(componentList, crudCssLayoutConfig.getComponentId(), viewId);
		addEditButtonComponent(componentList, crudCssLayoutConfig.getComponentId(), viewId);
		addViewButtonComponent(componentList, crudCssLayoutConfig.getComponentId(), viewId);
		addDeleteButtonComponent(componentList, crudCssLayoutConfig.getComponentId(), viewId);
		addCopyButtonComponent(componentList, crudCssLayoutConfig.getComponentId(), viewId);
		addTableResetButtonComponent(componentList, crudCssLayoutConfig.getComponentId(), viewId);
		addExcelButton(componentList, crudCssLayoutConfig.getComponentId(), viewId);
	}

	private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "landingScreenAddButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig landingScreenAddButtonButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		landingScreenAddButtonButtonConfig.setAuthorizationIncluded(true);
		landingScreenAddButtonButtonConfig.setComponentName("ADD");
		componentList.add(landingScreenAddButtonButtonConfig);

		List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig navigationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		navigationActionConfig.addActionParameter(GtnFrameworkNSFConstants.getNsfAddView());
		actionConfigList.add(navigationActionConfig);
		GtnUIFrameWorkActionConfig enableDisableAction = new GtnUIFrameWorkActionConfig();
		enableDisableAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		enableDisableAction.addActionParameter(GtnUiFrameworkEnableDisableAction.class.getName());
		enableDisableAction.addActionParameter(
				GtnFrameworkNSFConstants.getNsfAddView() + GtnFrameworkCommonStringConstants.UNDERSCORE);
		enableDisableAction.addActionParameter(Boolean.TRUE);
		enableDisableAction.addActionParameter(Boolean.TRUE);
		actionConfigList.add(enableDisableAction);
		landingScreenAddButtonButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

	}

	private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "landingScreenEditButton";
		GtnUIFrameworkComponentConfig landingScreenEditButtonLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(landingScreenEditButtonLayout);

		GtnUIFrameworkComponentConfig landingScreenEditButtonButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, landingScreenEditButtonLayout.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		landingScreenEditButtonButtonConfig.setAuthorizationIncluded(true);
		landingScreenEditButtonButtonConfig.setComponentName("EDIT");
		componentList.add(landingScreenEditButtonButtonConfig);

		List<GtnUIFrameWorkActionConfig> landingScreenEditActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig landingScreenEditAlertActionConfig = new GtnUIFrameWorkActionConfig();
		landingScreenEditAlertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);
		List<Object> landingScreenEditAlertParamsList = new ArrayList<>();
		landingScreenEditAlertParamsList.add(viewId + GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		landingScreenEditAlertParamsList.add(GtnFrameworkNSFMessageConstants.GTN_NSF_CRUD_BTN_MSG_HEADER);
		landingScreenEditAlertParamsList.add(GtnFrameworkNSFMessageConstants.GTN_NSF_CRUD_BTN_MSG_BODY + "edit.");
		landingScreenEditAlertActionConfig.setActionParameterList(landingScreenEditAlertParamsList);
		landingScreenEditActionConfigList.add(landingScreenEditAlertActionConfig);

		GtnUIFrameWorkActionConfig landingScreenEditNavigationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		landingScreenEditNavigationActionConfig.addActionParameter(GtnFrameworkNSFConstants.getNsfAddView());
		landingScreenEditActionConfigList.add(landingScreenEditNavigationActionConfig);

		GtnUIFrameWorkActionConfig landingScreenEditCustomAction = new GtnUIFrameWorkActionConfig();
		landingScreenEditCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		landingScreenEditCustomAction.addActionParameter(GtnUiFrameworkNsfEditAction.class.getName());
		landingScreenEditCustomAction.addActionParameter(viewId);
		landingScreenEditCustomAction.addActionParameter(GtnFrameworkNSFConstants.getNsfAddView());
		landingScreenEditCustomAction.addActionParameter("edit");
		landingScreenEditActionConfigList.add(landingScreenEditCustomAction);

		landingScreenEditButtonButtonConfig.setGtnUIFrameWorkActionConfigList(landingScreenEditActionConfigList);

	}

	private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "landingScreenViewButton";
		GtnUIFrameworkComponentConfig landingScreenViewButtonLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(landingScreenViewButtonLayout);

		GtnUIFrameworkComponentConfig landingScreenViewButton = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, landingScreenViewButtonLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		landingScreenViewButton.setComponentName("VIEW");
		landingScreenViewButton.setAuthorizationIncluded(true);
		componentList.add(landingScreenViewButton);

		List<GtnUIFrameWorkActionConfig> landingScreenViewButtonActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig landingScreenViewButtonAlertActionConfig = new GtnUIFrameWorkActionConfig();
		landingScreenViewButtonAlertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);
		List<Object> landingScreenViewButtonAlertParamsList = new ArrayList<>();
		landingScreenViewButtonAlertParamsList.add(viewId + GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		landingScreenViewButtonAlertParamsList
				.add(GtnFrameworkNSFMessageConstants.GTN_NSF_CRUD_BTN_MSG_HEADER);
		landingScreenViewButtonAlertParamsList.add(GtnFrameworkNSFMessageConstants.GTN_NSF_CRUD_BTN_MSG_BODY + "view.");
		landingScreenViewButtonAlertActionConfig.setActionParameterList(landingScreenViewButtonAlertParamsList);
		landingScreenViewButtonActionConfigList.add(landingScreenViewButtonAlertActionConfig);

		GtnUIFrameWorkActionConfig landingScreenViewButtonNavigationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);

		landingScreenViewButtonNavigationActionConfig.addActionParameter(GtnFrameworkNSFConstants.getNsfAddView());
		landingScreenViewButtonActionConfigList.add(landingScreenViewButtonNavigationActionConfig);

		GtnUIFrameWorkActionConfig landingScreenViewButtonCustomAction = new GtnUIFrameWorkActionConfig();
		landingScreenViewButtonCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		landingScreenViewButtonCustomAction.addActionParameter(GtnUiFrameworkNsfEditAction.class.getName());
		landingScreenViewButtonCustomAction.addActionParameter(viewId);
		landingScreenViewButtonCustomAction.addActionParameter(GtnFrameworkNSFConstants.getNsfAddView());
		landingScreenViewButtonCustomAction.addActionParameter("view");
		landingScreenViewButtonActionConfigList.add(landingScreenViewButtonCustomAction);

		landingScreenViewButton.setGtnUIFrameWorkActionConfigList(landingScreenViewButtonActionConfigList);

	}

	private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "landingScreenDeleteButton";
		GtnUIFrameworkComponentConfig landingScreenDeleteButtonLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(landingScreenDeleteButtonLayout);

		GtnUIFrameworkComponentConfig landingScreenDeleteButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, landingScreenDeleteButtonLayout.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		landingScreenDeleteButtonConfig.setAuthorizationIncluded(true);
		landingScreenDeleteButtonConfig.setComponentName("DELETE");
		componentList.add(landingScreenDeleteButtonConfig);

		List<GtnUIFrameWorkActionConfig> landingScreenDeleteActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig landingScreenDeleteAlertActionConfig = new GtnUIFrameWorkActionConfig();
		landingScreenDeleteAlertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);
		List<Object> landingScreenDeleteAlertParamsList = new ArrayList<>();
		landingScreenDeleteAlertParamsList.add(viewId + GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		landingScreenDeleteAlertParamsList.add(GtnFrameworkNSFMessageConstants.GTN_NSF_CRUD_BTN_MSG_HEADER);
		landingScreenDeleteAlertParamsList.add(GtnFrameworkNSFMessageConstants.GTN_NSF_CRUD_BTN_MSG_BODY + "delete.");
		landingScreenDeleteAlertActionConfig.setActionParameterList(landingScreenDeleteAlertParamsList);
		landingScreenDeleteActionConfigList.add(landingScreenDeleteAlertActionConfig);

		GtnUIFrameWorkActionConfig landingScreenDeleteCustomAction = new GtnUIFrameWorkActionConfig();
		landingScreenDeleteCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		landingScreenDeleteCustomAction.addActionParameter(GtnUiFrameworkNsfDeleteConfirmationAction.class.getName());
		landingScreenDeleteCustomAction.addActionParameter(viewId);
		landingScreenDeleteActionConfigList.add(landingScreenDeleteCustomAction);
		landingScreenDeleteButtonConfig.setGtnUIFrameWorkActionConfigList(landingScreenDeleteActionConfigList);

	}

	private void addCopyButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "landingScreenCopyButton";
		GtnUIFrameworkComponentConfig landingScreenCopyButtonLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(landingScreenCopyButtonLayout);

		GtnUIFrameworkComponentConfig landingScreenCopyButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, landingScreenCopyButtonLayout.getComponentId(),
						GtnUIFrameworkComponentType.BUTTON);
		landingScreenCopyButtonConfig.setAuthorizationIncluded(true);
		landingScreenCopyButtonConfig.setComponentName("COPY");
		componentList.add(landingScreenCopyButtonConfig);

		List<GtnUIFrameWorkActionConfig> landingScreenCopyButtonActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig landingScreenCopyButtonAlertActionConfig = new GtnUIFrameWorkActionConfig();
		landingScreenCopyButtonAlertActionConfig.setActionType(GtnUIFrameworkActionType.TABLE_ALERT_ACTION);
		List<Object> landingScreenCopyButtonAlertParamsList = new ArrayList<>();
		landingScreenCopyButtonAlertParamsList.add(viewId + GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE);
		landingScreenCopyButtonAlertParamsList
				.add(GtnFrameworkNSFMessageConstants.GTN_NSF_CRUD_BTN_MSG_HEADER);
		landingScreenCopyButtonAlertParamsList.add(GtnFrameworkNSFMessageConstants.GTN_NSF_CRUD_BTN_MSG_BODY + "copy.");
		landingScreenCopyButtonAlertActionConfig.setActionParameterList(landingScreenCopyButtonAlertParamsList);
		landingScreenCopyButtonActionConfigList.add(landingScreenCopyButtonAlertActionConfig);

		GtnUIFrameWorkActionConfig landingScreenCopyButtonNavigationActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
		landingScreenCopyButtonNavigationActionConfig.addActionParameter(GtnFrameworkNSFConstants.getNsfAddView());

		landingScreenCopyButtonActionConfigList.add(landingScreenCopyButtonNavigationActionConfig);

		GtnUIFrameWorkActionConfig landingScreenCopyButtonCustomAction = new GtnUIFrameWorkActionConfig();
		landingScreenCopyButtonCustomAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		landingScreenCopyButtonCustomAction.addActionParameter(GtnUiFrameworkNsfCopyAction.class.getName());
		landingScreenCopyButtonCustomAction.addActionParameter(viewId);
		landingScreenCopyButtonCustomAction.addActionParameter(GtnFrameworkNSFConstants.getNsfAddView());
		landingScreenCopyButtonActionConfigList.add(landingScreenCopyButtonCustomAction);

		landingScreenCopyButtonConfig.setGtnUIFrameWorkActionConfigList(landingScreenCopyButtonActionConfigList);

	}

	private void addTableResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String parentId,
			String viewId) {

		String componentId = viewId + "landingScreenResetButton";
		GtnUIFrameworkComponentConfig landingScreenResetButtonLayout = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(landingScreenResetButtonLayout);

		GtnUIFrameworkComponentConfig landingScreenResetButton = componentConfigProvider.getUIFrameworkComponentConfig(
				componentId, true, landingScreenResetButtonLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
		landingScreenResetButton.setComponentName("RESET");
		landingScreenResetButton.setAuthorizationIncluded(true);
		componentList.add(landingScreenResetButton);

		List<GtnUIFrameWorkActionConfig> landingScreenResetButtonActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig landingScreenResetButtonResetActionConfig = componentConfigProvider
				.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
		landingScreenResetButtonResetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);

		List<Object> landingScreenResetButtonResetParamList = new ArrayList<>();
		landingScreenResetButtonResetParamList.add("Confirmation");
		landingScreenResetButtonResetParamList
				.add("Are you sure you want to Reset the values in the Results list view?");
		landingScreenResetButtonResetParamList
				.add(Arrays.asList(viewId + GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE));
		Object tableDefaultValue = null;
		landingScreenResetButtonResetParamList.add(Arrays.asList(tableDefaultValue));
		landingScreenResetButtonResetActionConfig.setActionParameterList(landingScreenResetButtonResetParamList);
		landingScreenResetButtonActionConfigList.add(landingScreenResetButtonResetActionConfig);
		landingScreenResetButton.setGtnUIFrameWorkActionConfigList(landingScreenResetButtonActionConfigList);
	}

	private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getLandingScreenCustomFilterConfig() {
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> landingScreenCustomFilterConfigMap = new HashMap<>();
		String[] landingScreenPropertyIds = { GtnFrameworkCommonConstants.FORMULA_TYPE, "createdBy", "modifiedBy" };
		String[] landingScreenListNameArray = { "NS_FORMULA_TYPE", "USERS", "USERS" };
		for (int i = 0; i < landingScreenPropertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig landingScreenCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			landingScreenCustomFilterConfig.setPropertId(landingScreenPropertyIds[i]);
			landingScreenCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig landingScreenCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			landingScreenCustomFilterComponentConfig.setComponentId("customFilterComboBox");
			landingScreenCustomFilterComponentConfig.setComponentName("customFilterComboBox");
			landingScreenCustomFilterComponentConfig.setGtnComboboxConfig(new GtnUIFrameworkComboBoxConfig());
			landingScreenCustomFilterComponentConfig.getGtnComboboxConfig()
					.setComboBoxType(landingScreenListNameArray[i]);
			landingScreenCustomFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			landingScreenCustomFilterComponentConfig.getGtnComboboxConfig()
					.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
			landingScreenCustomFilterConfig.setGtnComponentConfig(landingScreenCustomFilterComponentConfig);
			landingScreenCustomFilterConfigMap.put(landingScreenCustomFilterConfig.getPropertId(),
					landingScreenCustomFilterConfig);

		}
		return landingScreenCustomFilterConfigMap;
	}

	private void addExcelButton(List<GtnUIFrameworkComponentConfig> componentList, String parentId, String viewId) {
		String componentId = viewId + "netSalesSearchResultTableExcelButton";
		GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfigProvider
				.getHorizontalLayoutConfig(componentId + GtnFrameworkCommonStringConstants.LAYOUT, true, parentId);
		componentList.add(gtnLayoutConfig);

		GtnUIFrameworkComponentConfig netSalesSearchResultTableExcelButtonConfig = componentConfigProvider
				.getUIFrameworkComponentConfig(componentId, true, gtnLayoutConfig.getComponentId(),
						GtnUIFrameworkComponentType.EXCEL_BUTTON);
		componentList.add(netSalesSearchResultTableExcelButtonConfig);
		GtnUIFrameworkExcelButtonConfig gtnUIFrameworkExcelButtonConfig = componentConfigProvider.getExcelBtnconfig(
				"NETSALESFORMULA", true, viewId + GtnFrameworkCommonConstants.NET_SALES_SEARCH_RESULT_TABLE, false);
		netSalesSearchResultTableExcelButtonConfig.setGtnUIFrameworkExcelButtonConfig(gtnUIFrameworkExcelButtonConfig);
		GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
		excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_CSV_ACTION);
		excelAction.addActionParameter(gtnUIFrameworkExcelButtonConfig);
		netSalesSearchResultTableExcelButtonConfig.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
	}

}
