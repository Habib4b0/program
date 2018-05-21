/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.customview.constants.GtnFrameworkCVConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
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
import java.util.List;

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
        addComponentList(view, view.getViewId() + GtnFrameworkCommonStringConstants.UNDERSCORE);
        return view;
    }

    private void addComponentList(GtnUIFrameworkViewConfig view, String namspacePrefix) {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        view.setGtnComponentList(componentList);
        addCVMainPanel(componentList, namspacePrefix);
        addCVButtonLayout(componentList, namspacePrefix);
        addCVResultPanel(componentList, namspacePrefix);
        addCVActionButtonLayout(componentList, namspacePrefix);
    }

    private void addCVMainPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig cvPanel = gtnConfigFactory.getPanelConfig(namspacePrefix + GtnFrameworkCVConstants.CV_LANDING_MAIN_PANEL, false,
                null);
        componentList.add(cvPanel);
        addCVMainLayout(componentList, namspacePrefix);
    }

    private void addCVMainLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig cvMainLayout = gtnConfigFactory.getVerticalLayoutConfig(
                namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT, true, namspacePrefix + GtnFrameworkCVConstants.CV_LANDING_MAIN_PANEL);
        cvMainLayout.setComponentWidth(GtnFrameworkCssConstants.HUNDRED_PERCENTAGE);
        componentList.add(cvMainLayout);
        addLandingScreenPanel(componentList, namspacePrefix);
    }

    private void addLandingScreenPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig landingPanel = gtnConfigFactory.getPanelConfig(namspacePrefix + GtnFrameworkCVConstants.CV_LANDING_SCREEN_PANEL,
                true, namspacePrefix + GtnFrameworkCommonConstants.MAINLAYOUT);
        landingPanel.setComponentName("Custom Tree Build");
        landingPanel.setAuthorizationIncluded(true);

        componentList.add(landingPanel);
        addFieldLayout(componentList, namspacePrefix);
    }

    private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig fieldLayout = gtnConfigFactory.getCssLayoutConfig(
                namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT, true,
                namspacePrefix + GtnFrameworkCVConstants.CV_LANDING_SCREEN_PANEL);
        List<String> styleList = new ArrayList<>();
        styleList.add(GtnFrameworkCssConstants.GTN_GRID_SINGLE_IN_LAYOUT_3);
        fieldLayout.setComponentStyle(styleList);
        componentList.add(fieldLayout);
        addFieldComponent(componentList, namspacePrefix);
    }

    private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        addCustomViewName(componentList, namspacePrefix);
        addCustomViewDescription(componentList, namspacePrefix);
        addCustomViewType(componentList, namspacePrefix);
        addCustomerRelation(componentList, namspacePrefix);
        addProductRelation(componentList, namspacePrefix);
        addScreenName(componentList, namspacePrefix);
    }

    private void addCustomViewName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig viewNameLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_NAME_LAYOUT, true,
                namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(viewNameLayout);

        GtnUIFrameworkComponentConfig treeViewNameConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                namspacePrefix + GtnFrameworkCommonConstants.TREE_VIEW_NAME, true,
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_NAME_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
        treeViewNameConfig.setAuthorizationIncluded(true);
        treeViewNameConfig.setComponentName("Custom View Name");

        treeViewNameConfig.setAddToParent(true);

        componentList.add(treeViewNameConfig);
    }

    private void addCustomViewDescription(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig descLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_DESCRIPTION_LAYOUT, true,
                namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(descLayout);

        GtnUIFrameworkComponentConfig customViewDescConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_DESCRIPTION, true,
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_DESCRIPTION_LAYOUT, GtnUIFrameworkComponentType.TEXTBOX);
        customViewDescConfig.setAuthorizationIncluded(true);
        customViewDescConfig.setComponentName("Custom View Description");

        customViewDescConfig.setAddToParent(true);

        componentList.add(customViewDescConfig);
    }

    private void addCustomerRelation(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig customerRelationLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                namspacePrefix + GtnFrameworkCVConstants.CUSTOMER_RELATION_LAYOUT, true,
                namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(customerRelationLayout);

        GtnUIFrameworkComponentConfig customerRelationConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                namspacePrefix + GtnFrameworkCommonConstants.CUTOMER_RELATION, true,
                namspacePrefix + GtnFrameworkCVConstants.CUSTOMER_RELATION_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
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

    private void addProductRelation(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig prodRelationLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                namspacePrefix + GtnFrameworkCVConstants.PRODUCT_RELATION_LAYOUT, true,
                namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(prodRelationLayout);

        GtnUIFrameworkComponentConfig prodRelationConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                namspacePrefix + GtnFrameworkCommonConstants.PRODUCT_RELATION, true,
                namspacePrefix + GtnFrameworkCVConstants.PRODUCT_RELATION_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
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

    private void addCustomViewType(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig custViewTypeLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TYPE_LAYOUT, true,
                namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(custViewTypeLayout);

        GtnUIFrameworkComponentConfig customViewTypeOptionGroup = gtnConfigFactory.getUIFrameworkComponentConfig(
                namspacePrefix + GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE, true,
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_TYPE_LAYOUT, GtnUIFrameworkComponentType.OPTIONGROUP);
        customViewTypeOptionGroup.setAuthorizationIncluded(true);
        customViewTypeOptionGroup.setComponentName("Custom View For");

        GtnUIFrameworkOptionGroupConfig relationshipTypeConfig = new GtnUIFrameworkOptionGroupConfig();
        relationshipTypeConfig.setValuesFromService(false);
        relationshipTypeConfig.setItemValues(Arrays.asList("Sales", "Discount"));

        customViewTypeOptionGroup.setComponentStyle(Arrays.asList(new String[]{"horizontal"}));
        customViewTypeOptionGroup.setGtnUIFrameworkOptionGroupConfig(relationshipTypeConfig);

        componentList.add(customViewTypeOptionGroup);

    }

    private void addScreenName(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig screenNameLayout = gtnConfigFactory.getHorizontalLayoutConfig(
                namspacePrefix + GtnFrameworkCVConstants.SCREEN_NAME_LAYOUT, true,
                namspacePrefix + GtnFrameworkCommonConstants.SEARCH_CRITERIALAYOUT);
        componentList.add(screenNameLayout);

        GtnUIFrameworkComponentConfig screenNameConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_SCREEN_NAME, true,
                namspacePrefix + GtnFrameworkCVConstants.SCREEN_NAME_LAYOUT, GtnUIFrameworkComponentType.COMBOBOX);
        screenNameConfig.setAuthorizationIncluded(true);
        screenNameConfig.setComponentName("Screen Name");
        screenNameConfig.setEnable(false);

        screenNameConfig.setAddToParent(true);

        componentList.add(screenNameConfig);

        GtnUIFrameworkComboBoxConfig companyStatusConfig = new GtnUIFrameworkComboBoxConfig();
        companyStatusConfig.setLoadingUrl(GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
                + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
        companyStatusConfig.setComboBoxType(GtnFrameworkCommonConstants.PRODUCT_RELATION);
        screenNameConfig.setGtnComboboxConfig(companyStatusConfig);

//                List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
//		GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
//		customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
//		customAction.addActionParameter(GtnFrameworkCRValueChangeAction.class.getName());
//		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.RESULTLAYOUT);
//		customAction.addActionParameter(namspacePrefix + GtnFrameworkCommonConstants.PRODUCT_HIERARCHY_TABLE);
//		actionConfigList.add(customAction);
//		prodRelationConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
    }

    private void addCVButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig buttonLayout = gtnConfigFactory.getCssLayoutConfig(
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_BUTTONLAYOUT, false, null);
        buttonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        buttonLayout.getGtnLayoutConfig().setComponentColumnSize(12);
        componentList.add(buttonLayout);
        addSearchButtonComponent(componentList, namspacePrefix);
        addResetButtonComponent(componentList, namspacePrefix);
    }

    private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig searchButtonLayout = gtnConfigFactory.getHorizontalLayoutConfig(GtnFrameworkCVConstants.GTN_SEARCH_LAYOUT, true,
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_BUTTONLAYOUT);
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
                Arrays.asList(namspacePrefix + GtnFrameworkCommonConstants.TREE_VIEW_NAME,
                        namspacePrefix + GtnFrameworkCommonConstants.CUTOMER_RELATION,
                        namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_DESCRIPTION,
                        namspacePrefix + GtnFrameworkCommonConstants.PRODUCT_RELATION,
                        namspacePrefix + GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE));

        GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig();
        alertActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);

        List<Object> alertParamsList = new ArrayList<>();
        alertParamsList.add("Search Criteria ");
        alertParamsList.add(GtnFrameworkCVConstants.GTN_CUSTOM_VIEW_SEARCH_CRITERIA_VALIDATION);

        alertActionConfig.setActionParameterList(alertParamsList);
        validationActionConfig.setActionParameterList(
                Arrays.asList(new Object[]{GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig)}));
        searchActionConfigList.add(validationActionConfig);

//		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
//		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
//		loadDataTableActionConfig.addActionParameter(GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE);
//		loadDataTableActionConfig.setFieldValues(Arrays.asList(
//				new String[] { GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_ID,
//						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_NO,
//						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_NAME,
//						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_STATU,
//						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_CONTRACT_TYPE,
//						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_TRADE_CLASS,
//						GtnUIFrameworkContractHeaderStringContants.CONTRACT_HEADER_SEARCH_CRITERIA_TP_NO }));
//		actionConfigList.add(loadDataTableActionConfig);
        GtnUIFrameWorkActionConfig notificationActionConfig = new GtnUIFrameWorkActionConfig();
        notificationActionConfig.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
//		notificationActionConfig.addActionParameter(GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE);
        notificationActionConfig.addActionParameter(0);
        searchActionConfigList.add(notificationActionConfig);
        searchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

    }

    private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig resetLayout = gtnConfigFactory.getHorizontalLayoutConfig(GtnFrameworkCVConstants.GTN_RESET_LAYOUT, true,
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_BUTTONLAYOUT);
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
                new String[]{namspacePrefix + GtnFrameworkCommonConstants.TREE_VIEW_NAME,
                    namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_DESCRIPTION,
                    namspacePrefix + GtnFrameworkCommonConstants.CUTOMER_RELATION,
                    namspacePrefix + GtnFrameworkCommonConstants.PRODUCT_RELATION,
                    namspacePrefix + GtnFrameworkCommonConstants.CUSTOM_VIEW_TYPE
//						GtnUIFrameworkContractHeaderStringContants.SEARCH_RESULT_TABLE
                }));

        paramsList.add(Arrays.asList(new Object[]{GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
            null, null, GtnFrameworkCommonStringConstants.STRING_EMPTY, GtnFrameworkCommonStringConstants.STRING_EMPTY,
            null}));

        resetActionConfig.setActionParameterList(paramsList);
        resetActionConfigList.add(resetActionConfig);
        resetButtonConfig.setGtnUIFrameWorkActionConfigList(resetActionConfigList);

    }

    private void addCVResultPanel(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig resultPanelConfig = gtnConfigFactory.getPanelConfig(GtnFrameworkCVConstants.CUSTOM_SEARCH_RESULT_PANEL,
                false, null);
        resultPanelConfig.setComponentName("Results");
        componentList.add(resultPanelConfig);
        addResultLayout(componentList, namspacePrefix);
    }

    private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

        GtnUIFrameworkComponentConfig gtnLayout = gtnConfigFactory
                .getHorizontalLayoutConfig(GtnFrameworkCVConstants.CUSTOM_SEARCH_RESULT_LAYOUT, true, GtnFrameworkCVConstants.CUSTOM_SEARCH_RESULT_PANEL);
        gtnLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        componentList.add(gtnLayout);
        addPagedTableComponent(componentList, namspacePrefix);
    }

    private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {

        GtnUIFrameworkComponentConfig searchResultConfig = gtnConfigFactory.getUIFrameworkComponentConfig(
                namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_RESULT_TABLE, true,
                GtnFrameworkCVConstants.CUSTOM_SEARCH_RESULT_LAYOUT, GtnUIFrameworkComponentType.PAGEDTABLE);
        searchResultConfig.setAuthorizationIncluded(true);
        searchResultConfig.setComponentName("Results");
        searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        List<String> tableStyleList = new ArrayList<>();
        tableStyleList.add(GtnFrameworkCssConstants.FILTERBAR);
        tableStyleList.add(GtnFrameworkCssConstants.V_HAS_WIDTH);
        tableStyleList.add(GtnFrameworkCssConstants.V_TABLE_FILTERBAR);
        tableStyleList.add(GtnFrameworkCssConstants.TABLE_HEADER_NORMAL);
        searchResultConfig.setComponentStyle(tableStyleList);

        componentList.add(searchResultConfig);

        GtnUIFrameworkPagedTableConfig cvLandingScreenResultsTable = gtnConfigFactory.getPagedTableConfig(true, true,
                GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
                GtnWebServiceUrlConstants.GTN_COMMON_SEARCH_SERVICE + GtnWebServiceUrlConstants.GTN_COMMON_SEARCH,
                "contractHeader", "contractHeaderSearchQuery");
        cvLandingScreenResultsTable.setEditable(false);
        cvLandingScreenResultsTable.setItemPerPage(10);
        cvLandingScreenResultsTable.setSinkItemPerPageWithPageLength(false);
        cvLandingScreenResultsTable.setTableColumnDataType(new Class[]{String.class, String.class, String.class,
            String.class, String.class, String.class, Date.class, String.class, Date.class, String.class});
        cvLandingScreenResultsTable.setTableVisibleHeader(new String[]{"Custom View Name", "Custom View Description", "Custom View Type",
            "Screen Name", "Customer Relationship Name", "Product Relationship Name", "Created Date", "Created By", "Modified Date", "Modified By"});
        cvLandingScreenResultsTable.setTableColumnMappingId(new Object[]{"customViewName", "customViewDescription", "customViewType",
            "screenName", "customerRelationshipName", "productRelationshipName", "createdDate", "createdBy", "modifiedDate", "modifiedBy"});
        cvLandingScreenResultsTable.setExtraColumn(new Object[]{"customViewMasterSId"});
        cvLandingScreenResultsTable.setExtraColumnDataType(new Class[]{Integer.class});
        cvLandingScreenResultsTable.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.CONTRACT_HEADER);
        cvLandingScreenResultsTable.setDoubleClickEnable(true);
//		chLandingScreenResultsTable.setCustomFilterConfigMap(getCustomFilterConfig());
//        List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

//		GtnUIFrameWorkActionConfig chTableClickNavigationActionConfig = new GtnUIFrameWorkActionConfig();
//		chTableClickNavigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
//		chTableClickNavigationActionConfig.addActionParameter("V002");
//		actionConfigList.add(chTableClickNavigationActionConfig);
//		GtnUIFrameWorkActionConfig chTableClickEditActionConfig = new GtnUIFrameWorkActionConfig();
//		chTableClickEditActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
//		chTableClickEditActionConfig.addActionParameter(GtnUIFrameworkContractHeaderEditAction.class.getName());
//		chTableClickEditActionConfig.addActionParameter(namspacePrefix + GtnFrameworkCVConstants.CUSTOM_VIEW_SEARCH_RESULT_TABLE);
//		chTableClickEditActionConfig.addActionParameter(Boolean.TRUE);
//		actionConfigList.add(chTableClickEditActionConfig);
//		chLandingScreenResultsTable.setGtnUIFrameWorkActionConfigList(actionConfigList);
        searchResultConfig.setGtnPagedTableConfig(cvLandingScreenResultsTable);
    }

    private void addCVActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig actionButtonLayout = gtnConfigFactory
                .getCssLayoutConfig(GtnFrameworkCVConstants.ACTION_BUTTON_LAYOUT, false, null);

        actionButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        actionButtonLayout.getGtnLayoutConfig().setComponentColumnSize(12);
        componentList.add(actionButtonLayout);
        addAddButtonComponent(componentList, namspacePrefix);
//		addEditButtonComponent(componentList, namspacePrefix);
    }

    private void addAddButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, String namspacePrefix) {
        GtnUIFrameworkComponentConfig cvAddButtonLayout = gtnConfigFactory.getHorizontalLayoutConfig(GtnFrameworkCVConstants.GTN_ADD_BUTTON_LAYOUT, true,
                GtnFrameworkCVConstants.ACTION_BUTTON_LAYOUT);
        componentList.add(cvAddButtonLayout);

        GtnUIFrameworkComponentConfig addButtonConfig = gtnConfigFactory.getUIFrameworkComponentConfig(namspacePrefix + "gtnAddButton",
                true, GtnFrameworkCVConstants.GTN_ADD_BUTTON_LAYOUT, GtnUIFrameworkComponentType.BUTTON);
        addButtonConfig.setAuthorizationIncluded(true);
        addButtonConfig.setComponentName("Add");

        componentList.add(addButtonConfig);

        List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

        GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
        navigationActionConfig.setActionType(GtnUIFrameworkActionType.NAVIGATION_ACTION);
        navigationActionConfig.addActionParameter("V002");
        actionConfigList.add(navigationActionConfig);

//		GtnUIFrameWorkActionConfig customCommonValidationAction = new GtnUIFrameWorkActionConfig();
//		customCommonValidationAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
//		customCommonValidationAction.addActionParameter(GtnUIFrameworkContractHeaderAddAction.class.getName());
//		actionConfigList.add(customCommonValidationAction);
        addButtonConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

    }
}
