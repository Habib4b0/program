/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkAddAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkCVSearchNotification;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnFrameworkCustomViewEditAction;
import com.stpl.gtn.gtn2o.ui.customview.config.action.GtnUIFrameworkCVDeleteConfirmationAction;
import com.stpl.gtn.gtn2o.ui.customview.constants.GtnFrameworkCVConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
public class GtnFrameworkCVLandingScreenConfig {

    private final GtnFrameworkComponentConfigProvider gtnConfigFactory = GtnFrameworkComponentConfigProvider
            .getInstance();

    public GtnUIFrameworkViewConfig getSearchView() {
        GtnUIFrameworkViewConfig view = gtnConfigFactory.getViewConfig(
                GtnFrameworkCVConstants.CUSTOM_VIEW_LANDING_SCREEN,
                GtnFrameworkCVConstants.CUSTOM_VIEW_LANDING_SCREEN, true);
        addComponentList(view);
        return view;
    }

    private void addComponentList(GtnUIFrameworkViewConfig view) {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        view.setGtnComponentList(componentList);
        addCVMainPanel(componentList);
        addCVButtonLayout(componentList);
        addCVResultPanel(componentList);
        addCVActionButtonLayout(componentList);
    }

    private void addCVMainPanel(List<GtnUIFrameworkComponentConfig> componentList) {
        GtnUIFrameworkComponentConfig cvPanel = gtnConfigFactory.getPanelConfig( GtnFrameworkCVConstants.CV_LANDING_MAIN_PANEL, false,
                null);
        componentList.add(cvPanel);
        addCVMainLayout(componentList );
    }

    private void addCVMainLayout(List<GtnUIFrameworkComponentConfig> componentList) {
        GtnUIFrameworkComponentConfig cvMainLayout = gtnConfigFactory.getVerticalLayoutConfig(
                 GtnFrameworkCommonConstants.MAINLAYOUT, true,   GtnFrameworkCVConstants.CV_LANDING_MAIN_PANEL);
        cvMainLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
        componentList.add(cvMainLayout);
        addLandingScreenPanel(componentList );
    }

    private void addLandingScreenPanel(List<GtnUIFrameworkComponentConfig> componentList) {
        GtnUIFrameworkComponentConfig landingPanel = gtnConfigFactory.getPanelConfig( GtnFrameworkCVConstants.CV_LANDING_SCREEN_PANEL,
                true, GtnFrameworkCommonConstants.MAINLAYOUT);
        landingPanel.setComponentName("Custom Tree Build");
        landingPanel.setAuthorizationIncluded(true);

        componentList.add(landingPanel);
        addFieldLayout(componentList);
    }

    private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList) {
        GtnUIFrameworkComponentConfig fieldLayout = gtnConfigFactory.getCssLayoutConfig(
                 GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true,
                 GtnFrameworkCVConstants.CV_LANDING_SCREEN_PANEL);
        List<String> styleList = new ArrayList<>();
        styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
        fieldLayout.setComponentStyle(styleList);
        componentList.add(fieldLayout);
        addFieldComponent(componentList );
    }

    private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList ) {
        addCustomViewName(componentList );
        addCustomViewDescription(componentList);
        addCustomViewType(componentList );
        addCustomerRelation(componentList );
        addProductRelation(componentList );
        addScreenName(componentList );
    }

    private void addCustomViewName(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig viewNameLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                 GtnFrameworkCVConstants.CUSTOM_VIEW_NAME_LAYOUT, true,
                 GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(viewNameLayout);

        GtnUIFrameworkComponentConfig treeViewNameConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                 GtnFrameworkCommonConstants.TREE_VIEW_NAME, true,
                 GtnFrameworkCVConstants.CUSTOM_VIEW_NAME_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
        treeViewNameConfig.setAuthorizationIncluded(true);
        treeViewNameConfig.setComponentName("Custom View Name");

        treeViewNameConfig.setAddToParent(true);

        componentList.add(treeViewNameConfig);
    }

    private void addCustomViewDescription(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig descLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                  GtnFrameworkCVConstants.CUSTOM_VIEW_DESCRIPTION_LAYOUT, true,
                  GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(descLayout);

        GtnUIFrameworkComponentConfig customViewDescConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                  GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION, true,
                  GtnFrameworkCVConstants.CUSTOM_VIEW_DESCRIPTION_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
        customViewDescConfig.setAuthorizationIncluded(true);
        customViewDescConfig.setComponentName("Custom View Description");

        customViewDescConfig.setAddToParent(true);

        componentList.add(customViewDescConfig);
    }

    private void addCustomerRelation(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig customerRelationLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                  GtnFrameworkCVConstants.CUSTOMER_RELATION_LAYOUT, true,
                  GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(customerRelationLayout);

        GtnUIFrameworkComponentConfig customerRelationConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                  GtnFrameworkCommonConstants.CUTOMER_RELATION, true,
                  GtnFrameworkCVConstants.CUSTOMER_RELATION_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
        customerRelationConfig.setAuthorizationIncluded(true);
        customerRelationConfig.setComponentName("Customer Relation");

        customerRelationConfig.setAddToParent(true);

        componentList.add(customerRelationConfig);

        GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
        companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
                + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
        companyStatusConfig.setComboBoxType(GtnFrameworkCommonConstants.CUTOMER_RELATION);
        customerRelationConfig.setGtnComboboxConfig(companyStatusConfig);

    }

    private void addProductRelation(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig prodRelationLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                  GtnFrameworkCVConstants.PRODUCT_RELATION_LAYOUT, true,
                  GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(prodRelationLayout);

        GtnUIFrameworkComponentConfig prodRelationConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                  GtnFrameworkCommonConstants.PRODUCT_RELATION, true,
                  GtnFrameworkCVConstants.PRODUCT_RELATION_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
        prodRelationConfig.setAuthorizationIncluded(true);
        prodRelationConfig.setComponentName("Product Relation");

        prodRelationConfig.setAddToParent(true);

        componentList.add(prodRelationConfig);

        GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
        companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
                + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
        companyStatusConfig.setComboBoxType(GtnFrameworkCommonConstants.PRODUCT_RELATION);
        prodRelationConfig.setGtnComboboxConfig(companyStatusConfig);

    }

    private void addCustomViewType(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig custViewTypeLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                  GtnFrameworkCVConstants.CUSTOM_VIEW_TYPE_LAYOUT, true,
                  GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(custViewTypeLayout);

        GtnUIFrameworkComponentConfig customViewTypeOptionGroup = gtnConfigFactory.getUIFrameworkComponentConfig(
                  GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE, true,
                  GtnFrameworkCVConstants.CUSTOM_VIEW_TYPE_LAYOUT, GtnUIFrameworkComponentType.OPTIONGROUP);
        customViewTypeOptionGroup.setAuthorizationIncluded(true);
        customViewTypeOptionGroup.setComponentName("Custom View For");

        GtnUIFrameworkOptionGroupConfig relationshipTypeConfig = new GtnUIFrameworkOptionGroupConfig();
        relationshipTypeConfig.setValuesFromService(false);
        relationshipTypeConfig.setItemValues(Arrays.asList("Sales", "Discount"));

        customViewTypeOptionGroup.setComponentStyle(Arrays.asList("horizontal"));
        customViewTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(relationshipTypeConfig);

        componentList.add(customViewTypeOptionGroup);

    }

    private void addScreenName(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig screenNameLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                  GtnFrameworkCVConstants.SCREEN_NAME_LAYOUT, true,
                  GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(screenNameLayout);

        GtnUIFrameworkComponentConfig screenNameConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                  GtnFrameworkCVConstants.CUSTOM_VIEW_SCREEN_NAME, true,
                  GtnFrameworkCVConstants.SCREEN_NAME_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
        screenNameConfig.setAuthorizationIncluded(true);
        screenNameConfig.setComponentName("Screen Name");
        screenNameConfig.setEnable(false);

        screenNameConfig.setAddToParent(true);

        componentList.add(screenNameConfig);

        GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
        companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
                + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
        companyStatusConfig.setComboBoxType(GtnFrameworkCommonConstants.CV_MODULE_TYPE);
        companyStatusConfig.setHasDefaultValue(true);
	companyStatusConfig.setDefaultDesc("Forecasting");
        screenNameConfig.setGtnComboboxConfig(companyStatusConfig);

    }

    private void addCVButtonLayout(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig buttonLayout = gtnConfigFactory.getCssLayoutConfig(
                  GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_BUTTONLAYOUT, false, null);
        buttonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        buttonLayout.getGtnLayoutConfig().setComponentColumnSize(12);
        componentList.add(buttonLayout);
        addSearchButtonComponent(componentList );
        addResetButtonComponent(componentList );
    }

    private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig searchButtonLayout = gtnConfigFactory.getHorizontalLayoutConfig(GtnFrameworkCVConstants.GTN_SEARCH_LAYOUT, true,
                  GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_BUTTONLAYOUT);
        componentList.add(searchButtonLayout);

        GtnUIFrameworkComponentConfig searchButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig("gtnSearch01",
                true, GtnFrameworkCVConstants.GTN_SEARCH_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
        searchButtonConfig.setAuthorizationIncluded(true);
        searchButtonConfig.setAddToParent(true);
        searchButtonConfig.setComponentName("SEARCH");

        componentList.add(searchButtonConfig);

        List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

        GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
        validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);

        validationActionConfig.setFieldValues(
                Arrays.asList(  GtnFrameworkCommonConstants.TREE_VIEW_NAME,
                          GtnFrameworkCommonConstants.CUTOMER_RELATION,
                          GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION,
                          GtnFrameworkCommonConstants.PRODUCT_RELATION,
                          GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE));

        GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
        alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

        List<Object> alertParamsList = new ArrayList<>();
        alertParamsList.add("Search Criteria ");
        alertParamsList.add(GtnFrameworkCVConstants.GTN_CUSTOM_VIEW_SEARCH_CRITERIA_VALIDATION);

        alertActionConfig.setActionParameterList(alertParamsList);
        validationActionConfig.setActionParameterList(
                Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig)));
        searchActionConfigList.add(validationActionConfig);
        
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(  GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_RESULT_TABLE);
		loadDataTableActionConfig.setFieldValues(Arrays.asList(
				              GtnFrameworkCommonConstants.TREE_VIEW_NAME,
                      GtnFrameworkCommonConstants.CUTOMER_RELATION,
                      GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION,
                      GtnFrameworkCommonConstants.PRODUCT_RELATION,
                      GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE));
	searchActionConfigList.add(loadDataTableActionConfig);
        GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
        notificationActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
	notificationActionConfig.addActionParameter(  GtnFrameworkCVSearchNotification.class.getName());
	notificationActionConfig.addActionParameter(  GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_RESULT_TABLE);
        searchActionConfigList.add(notificationActionConfig);
        searchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

    }

    private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig resetLayout = gtnConfigFactory.getHorizontalLayoutConfig(GtnFrameworkCVConstants.GTN_RESET_LAYOUT, true,
                  GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_BUTTONLAYOUT);
        componentList.add(resetLayout);

        GtnUIFrameworkComponentConfig resetButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig("gtnReset01",
                true, GtnFrameworkCVConstants.GTN_RESET_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
        resetButtonConfig.setAuthorizationIncluded(true);
        resetButtonConfig.setAddToParent(true);
        resetButtonConfig.setComponentName("RESET");

        componentList.add(resetButtonConfig);

        List<GtnUIFrameWorkActionConfig> resetActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
        resetActionConfig.setActionType(GtnUIFrameworkActionType.RESET_ACTION);
        List<Object> paramsList = new ArrayList<>();
        paramsList.add("Confirmation");
        paramsList.add(GtnFrameworkCVConstants.GTN_CUSTOM_VIEW_SEARCH_CRITERIA_RESET_VALIDATION);
        paramsList.add(Arrays.asList(
                 GtnFrameworkCommonConstants.TREE_VIEW_NAME,
                      GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION,
                      GtnFrameworkCommonConstants.CUTOMER_RELATION,
                      GtnFrameworkCommonConstants.PRODUCT_RELATION,
                      GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE, GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_RESULT_TABLE
                ));

        paramsList.add(Arrays.asList(GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
            null, null, "Sales", GtnFrameworkCommonStringConstants.STRING_EMPTY,
            null));

        resetActionConfig.setActionParameterList(paramsList);
        resetActionConfigList.add(resetActionConfig);
        resetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

    }

    private void addCVResultPanel(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig resultPanelConfig = gtnConfigFactory.getPanelConfig(GtnFrameworkCVConstants.CUSTOM_SEARCH_RESULT_PANEL,
                false, null);
        resultPanelConfig.setComponentName("Results");
        componentList.add(resultPanelConfig);
        addResultLayout(componentList);
    }

    private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList ) {

        GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory
                .getHorizontalLayoutConfig(GtnFrameworkCVConstants.CUSTOM_SEARCH_RESULT_LAYOUT, true, GtnFrameworkCVConstants.CUSTOM_SEARCH_RESULT_PANEL);
        gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        componentList.add(gtnLayout);
        addPagedTableComponent(componentList);
    }

    private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList ) {

        GtnUIFrameworkComponentConfig customViewSearchResultConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                 GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_RESULT_TABLE, true,
                GtnFrameworkCVConstants.CUSTOM_SEARCH_RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
        customViewSearchResultConfig.setAuthorizationIncluded(true);
        customViewSearchResultConfig.setComponentName("Results");
        customViewSearchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        List<String> customViewTableStyleList = new ArrayList<>();
        customViewTableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
        customViewTableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
        customViewTableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
        customViewTableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
        customViewSearchResultConfig.setComponentStyle(customViewTableStyleList);

        componentList.add(customViewSearchResultConfig);

        GtnUIFrameworkPagedTableConfig cvLandingScreenResultsTable = gtnConfigFactory.getPagedTableConfig(true, true,
                GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
                GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
                "customView", "customViewSearchQuery");
        cvLandingScreenResultsTable.setEditable(false);
        cvLandingScreenResultsTable.setItemPerPage(10);
        cvLandingScreenResultsTable.setSinkItemPerPageWithPageLength(false);
        cvLandingScreenResultsTable.setTableColumnDataType(new Class[]{String.class, String.class, String.class,
            String.class, String.class, String.class, Date.class, String.class, Date.class, String.class});
        cvLandingScreenResultsTable.setTableVisibleHeader(new String[]{"Custom View Name", "Custom View Description", "Custom View Type",
            "Screen Name", "Customer Relationship Name", "Product Relationship Name", "Created Date", "Created By", "Modified Date", "Modified By"});
        cvLandingScreenResultsTable.setTableColumnMappingId(new Object[]{GtnFrameworkCommonConstants.TREE_VIEW_NAME, GtnFrameworkCommonConstants.CUSTOM_VIEW_DESCRIPTION, GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE,
           GtnFrameworkCommonConstants.CUSTOM_VIEW_SCREEN_NAME, GtnFrameworkCommonConstants.CUTOMER_RELATION, GtnFrameworkCommonConstants.PRODUCT_RELATION , "createdDate", "createdBy", "modifiedDate", "modifiedBy"});
        cvLandingScreenResultsTable.setExtraColumn(new Object[]{"customViewMasterSId",GtnFrameworkCommonConstants.CUTOMER_RELATION_SID,GtnFrameworkCommonConstants.PRODUCT_RELATION_SID});
        cvLandingScreenResultsTable.setExtraColumnDataType(new Class[]{Integer.class,Integer.class,Integer.class});
        cvLandingScreenResultsTable.setCustomFilterConfigMap(getCVCustomFilterConfig());
        cvLandingScreenResultsTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.CUSTOM_SEARCH_CONFIG);
        cvLandingScreenResultsTable.setDoubleClickEnable(true);
        customViewSearchResultConfig.setGtnPagedTableConfig(cvLandingScreenResultsTable);
    }

    private void addCVActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig actionButtonLayout = gtnConfigFactory
                .getCssLayoutConfig(GtnFrameworkCVConstants.ACTION_BUTTON_LAYOUT, false, null);

        actionButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        actionButtonLayout.getGtnLayoutConfig().setComponentColumnSize(12);
        componentList.add(actionButtonLayout);
        addAddButtonComponent(componentList );
	addEditButtonComponent(componentList );
	addViewButtonComponent(componentList );
	addDeleteButtonComponent(componentList );
    }

    private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig cvAddButtonLayout = gtnConfigFactory.getHorizontalLayoutConfig(GtnFrameworkCVConstants.GTN_ADD_BUTTON_LAYOUT, true,
                GtnFrameworkCVConstants.ACTION_BUTTON_LAYOUT);
        componentList.add(cvAddButtonLayout);

        GtnUIFrameworkComponentConfig addButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(  "gtnAddButton",
                true, GtnFrameworkCVConstants.GTN_ADD_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
        addButtonConfig.setAuthorizationIncluded(true);
        addButtonConfig.setComponentName("Add");

        componentList.add(addButtonConfig);

        List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

        GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
        navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
        navigationActionConfig.addActionParameter("V002");
        actionConfigList.add(navigationActionConfig);
        
        GtnUIFrameWorkActionConfig sessionResetAction = new GtnUIFrameWorkActionConfig();
        sessionResetAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        sessionResetAction.addActionParameter(GtnFrameworkAddAction.class.getName());
        actionConfigList.add(sessionResetAction);

        addButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

    }
    private void addEditButtonComponent(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig cvEditButtonLayout = gtnConfigFactory.getHorizontalLayoutConfig(GtnFrameworkCVConstants.GTN_EDIT_BUTTON_LAYOUT, true,
                GtnFrameworkCVConstants.ACTION_BUTTON_LAYOUT);
        componentList.add(cvEditButtonLayout);

        GtnUIFrameworkComponentConfig editButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(  "gtnEditButton",
                true, GtnFrameworkCVConstants.GTN_EDIT_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
        editButtonConfig.setAuthorizationIncluded(true);
        editButtonConfig.setComponentName("Edit");
  
        componentList.add(editButtonConfig);

        List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig editActionConfig = new GtnUIFrameWorkActionConfig();
        editActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
	editActionConfig.addActionParameter(GtnFrameworkCustomViewEditAction.class.getName());
	editActionConfig.addActionParameter(GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_RESULT_TABLE);
	editActionConfig.addActionParameter("EDIT");
	editActionConfig.addActionParameter(GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
        actionConfigList.add(editActionConfig);
        editButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

    }
    private void addViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig cvViewButtonLayout = gtnConfigFactory.getHorizontalLayoutConfig(GtnFrameworkCVConstants.GTN_VIEW_BUTTON_LAYOUT, true,
                GtnFrameworkCVConstants.ACTION_BUTTON_LAYOUT);
        componentList.add(cvViewButtonLayout);

        GtnUIFrameworkComponentConfig viewButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(  "gtnViewButton",
                true, GtnFrameworkCVConstants.GTN_VIEW_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
        viewButtonConfig.setAuthorizationIncluded(true);
        viewButtonConfig.setComponentName("View");

        componentList.add(viewButtonConfig);

        List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

	GtnUIFrameWorkActionConfig viewActionConfig = new GtnUIFrameWorkActionConfig();
        viewActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
	viewActionConfig.addActionParameter(GtnFrameworkCustomViewEditAction.class.getName());
	viewActionConfig.addActionParameter(GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_RESULT_TABLE);
	viewActionConfig.addActionParameter("VIEW");
        viewActionConfig.addActionParameter(GtnFrameworkCVConstants.CUSTOM_VIEW_TREE);
        actionConfigList.add(viewActionConfig);

        viewButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

    }
    private void addDeleteButtonComponent(List<GtnUIFrameworkComponentConfig> componentList ) {
        GtnUIFrameworkComponentConfig cvDeleteButtonLayout = gtnConfigFactory.getHorizontalLayoutConfig(GtnFrameworkCVConstants.GTN_VIEW_BUTTON_LAYOUT, true,
                GtnFrameworkCVConstants.ACTION_BUTTON_LAYOUT);
        componentList.add(cvDeleteButtonLayout);

        GtnUIFrameworkComponentConfig deleteButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(  "gtnDeleteButton",
                true, GtnFrameworkCVConstants.GTN_VIEW_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
        deleteButtonConfig.setAuthorizationIncluded(true);
        deleteButtonConfig.setVisible(true);
        deleteButtonConfig.setComponentName("Delete");

        componentList.add(deleteButtonConfig);

        List<GtnUIFrameWorkActionConfig> actionDeleteConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnUIFrameworkCVDeleteConfirmationAction.class.getName());
		deleteActionConfig.addActionParameter(GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_RESULT_TABLE);
		deleteActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
		deleteActionConfig.addActionParameter("No Record has been selected.  Please select a Record and try again.");
		deleteActionConfig.addActionParameter("Are you sure you want to delete record ");
		actionDeleteConfigList.add(deleteActionConfig);

        deleteButtonConfig.setGtnUIFrameWorkActionConfigList(actionDeleteConfigList);

    }
    private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCVCustomFilterConfig() {
        
		Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customViewFilterConfigMap = new HashMap<>();
		String[] propertyIds = GtnFrameworkCVConstants.getCvCustomPropertyIds();
		String[] listNameArray = GtnFrameworkCVConstants.getCvListNameArrays();
		for (int i = 0; i < propertyIds.length; i++) {
			GtnUIFrameworkPagedTableCustomFilterConfig cvCustomFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
			cvCustomFilterConfig.setPropertId(propertyIds[i]);
			cvCustomFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);
			GtnUIFrameworkComponentConfig cvCustomFilterComponentConfig = new GtnUIFrameworkComponentConfig();
			cvCustomFilterComponentConfig.setComponentId("customFilterComboBox");
			cvCustomFilterComponentConfig.setComponentName("customFilterComboBox");
			cvCustomFilterComponentConfig.setGtnComboboxConfig(gtnConfigFactory.getComboBoxConfig(listNameArray[i],
					GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
							+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX));
			cvCustomFilterComponentConfig.getGtnComboboxConfig()
					.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
			cvCustomFilterConfig.setGtnComponentConfig(cvCustomFilterComponentConfig);
			customViewFilterConfigMap.put(cvCustomFilterConfig.getPropertId(), cvCustomFilterConfig);

		}
		return customViewFilterConfigMap;
	}
}
