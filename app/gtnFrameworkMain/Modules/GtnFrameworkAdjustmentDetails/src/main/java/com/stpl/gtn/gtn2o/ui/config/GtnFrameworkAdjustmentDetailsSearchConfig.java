/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkDeductionLevelValueChange;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkTransactionDetailsValueChange;
import com.stpl.gtn.gtn2o.ui.config.abstractConfig.GTNFrameworkAbstractComponent;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkAdjustmentDetailsStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkComboBoxConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.GtnUIFrameworkPagedTableConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ui.pojo.ComponentPojo;
import com.stpl.gtn.gtn2o.ws.bean.search.GtnWsSearchQueryConfigLoaderType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkAdjustmentDetailsSearchConfig extends GTNFrameworkAbstractComponent {

    public GtnUIFrameworkViewConfig getSearchView() {
        GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
        GtnUIFrameworkViewConfig searchView = componentConfig.getViewConfig("Search View", "V001", true);
        GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
        customAction.setActionType(GtnUIFrameworkActionType.RELOAD_HELPER_TABLE_ACTION);
        searchView.addViewAction(customAction);
        addComponentList(searchView, componentConfig);
        return searchView;
    }

    private void addComponentList(GtnUIFrameworkViewConfig view, GtnFrameworkComponentConfigProvider componentConfig) {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        view.setGtnComponentList(componentList);
        addSearchCriteriaPanel(componentList, componentConfig);
        addSearchCriteriaDetailsPanel(componentList, componentConfig);
        addButtonLayout(componentList, componentConfig);
        addResultPanel(componentList, componentConfig);
//        addActionButtonLayout(componentList, componentConfig);

    }

    private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig itemMasterSearchPanel = componentConfig.getPanelConfig("searchCriteriaSummaryPanel",
                false, null);
        itemMasterSearchPanel.setComponentName("Search Criteria Summary");
        itemMasterSearchPanel.setAuthorizationIncluded(true);
        componentList.add(itemMasterSearchPanel);
        addFieldLayout(componentList, componentConfig);
    }

    private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig itemMasterSearchLayout = componentConfig.getGtnCssLayoutConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_SUMMARY_LAYOUT, true, "searchCriteriaSummaryPanel",
                GtnUIFrameworkLayoutType.COL4_LAYOUT);
        componentList.add(itemMasterSearchLayout);
        addFieldComponent(componentList, componentConfig);
    }

    private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {
        addPublicView(componentList, componentConfig);
        addAdjustmentType(componentList, componentConfig);
        addGlCompany(componentList, componentConfig);
        addWorkFlowID(componentList, componentConfig);
        addPrivateView(componentList, componentConfig);
        addTransactionLevel(componentList, componentConfig);
        addBusinessUnit(componentList, componentConfig);
        addWorkFlowName(componentList, componentConfig);
    }

    private void addPublicView(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig systemIdLayout = componentConfig.getHorizontalLayoutConfig(
                "publicViewLayout", true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_SUMMARY_LAYOUT);
        componentList.add(systemIdLayout);

        GtnUIFrameworkComponentConfig publicViewConfig = componentConfig.getUIFrameworkComponentConfig(
                GtnFrameworkCommonConstants.PUBLIC_VIEWS, true, systemIdLayout.getComponentId(),
                GtnUIFrameworkComponentType.POPUPTEXTFIELD);
        publicViewConfig.setAuthorizationIncluded(true);
        publicViewConfig.setComponentName("Public View");
        componentList.add(publicViewConfig);
    }

    private void addPrivateView(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig systemIdLayout = componentConfig.getHorizontalLayoutConfig(
                "privateViewLayout", true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_SUMMARY_LAYOUT);
        componentList.add(systemIdLayout);

        GtnUIFrameworkComponentConfig privateViewConfig = componentConfig.getUIFrameworkComponentConfig(
                GtnFrameworkCommonConstants.PRIVATE_VIEWS, true, systemIdLayout.getComponentId(),
                GtnUIFrameworkComponentType.POPUPTEXTFIELD);
        privateViewConfig.setAuthorizationIncluded(true);
        privateViewConfig.setComponentName("Private View");
        componentList.add(privateViewConfig);
    }

    private void addAdjustmentType(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        ComponentPojo componentPojo = new ComponentPojo("Adjustment Type", GtnFrameworkCommonConstants.ADJUSTMENT_TYPE, "ARM_TRX_METHDOLOGY", GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_SUMMARY_LAYOUT);
        getComboBoxComponent(componentConfig, componentList, componentPojo);
    }

    private void addGlCompany(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        ComponentPojo componentPojo = new ComponentPojo("GL Company", GtnFrameworkCommonConstants.GL_COMPANY, "company", GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_SUMMARY_LAYOUT);
        GtnUIFrameworkComponentConfig compConfig = getComboBoxComponent(componentConfig, componentList, componentPojo);
        List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
        customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        customAction.addActionParameter(GtnFrameworkDeductionLevelValueChange.class.getName());
        customAction.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_VALUE);
        actionConfigList.add(customAction);
        compConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);
    }

    private void addBusinessUnit(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        ComponentPojo componentPojo = new ComponentPojo("Business Unit", GtnFrameworkCommonConstants.BUSINESS_UNIT, "Business_Unit", GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_SUMMARY_LAYOUT);
        getComboBoxComponent(componentConfig, componentList, componentPojo);
    }

    private void addWorkFlowID(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Workflow ID";
        String objectComponentName = GtnFrameworkCommonConstants.WORKFLOW_ID;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_SUMMARY_LAYOUT);
    }

    private void addTransactionLevel(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Transaction Level";
        String objectComponentName = GtnFrameworkCommonConstants.TRANSACTION_LEVEL;
        List transactionLevels = new ArrayList();
        transactionLevels.add("Reserve Details");
        transactionLevels.add("GTN Details");
        GtnUIFrameworkComponentConfig transactionLevelConfig = getComboBoxComponent(componentConfig, objectComponentName, componentList, componentName, transactionLevels, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_SUMMARY_LAYOUT);
        List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
        customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        customAction.addActionParameter(GtnFrameworkTransactionDetailsValueChange.class.getName());
        customAction.addActionParameter(GtnFrameworkCommonConstants.TRANSACTION_LEVEL);
        customAction.addActionParameter(GtnFrameworkCommonConstants.CUSTOMER_NO);
        customAction.addActionParameter(GtnFrameworkCommonConstants.ITEM_NO);
        customAction.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_LEVEL);
        customAction.addActionParameter(GtnFrameworkCommonConstants.ITEM_NAME);
        customAction.addActionParameter(GtnFrameworkCommonConstants.CUSTOMER_NAME);
        customAction.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_VALUE);
        actionConfigList.add(customAction);
        transactionLevelConfig.setGtnUIFrameWorkActionConfigList(actionConfigList);

    }

    private void addWorkFlowName(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Workflow Name";
        String objectComponentName = GtnFrameworkCommonConstants.WORKFLOW_NAME;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_SUMMARY_LAYOUT);
    }

    private void addSearchCriteriaDetailsPanel(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig searchCriteriaDetailsPanelConfig = componentConfig.getPanelConfig("searchCriteriaDetailsPanel",
                false, null);
        searchCriteriaDetailsPanelConfig.setComponentName("Search Criteria Details");
        searchCriteriaDetailsPanelConfig.setAuthorizationIncluded(true);
        componentList.add(searchCriteriaDetailsPanelConfig);
        addDetailsFieldLayout(componentList, componentConfig);
    }

    private void addDetailsFieldLayout(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig itemMasterSearchLayout = componentConfig.getGtnCssLayoutConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT, true, "searchCriteriaDetailsPanel",
                GtnUIFrameworkLayoutType.COL4_LAYOUT);
        componentList.add(itemMasterSearchLayout);
        addDetailsFieldComponent(componentList, componentConfig);
    }

    private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig itemMasterButtonLayout = componentConfig
                .getCssLayoutConfig(GtnFrameworkCommonConstants.ITEM_SEARCH_BUTTONLAYOUT, false, null);
        itemMasterButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_12);
        componentList.add(itemMasterButtonLayout);

        addSearchButtonComponent(componentList, componentConfig);
        addSaveViewButtonComponent(componentList, componentConfig);
        addResetButtonComponent(componentList, componentConfig);

    }

    private void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig searchBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnSearch01layout",
                true, GtnFrameworkCommonConstants.ITEM_SEARCH_BUTTONLAYOUT);
        componentList.add(searchBtnLayout);

        GtnUIFrameworkComponentConfig searchButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnSearch01",
                true, searchBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        searchButtonConfig.setComponentName("SEARCH");
        searchButtonConfig.setAuthorizationIncluded(true);
        componentList.add(searchButtonConfig);

        List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();

        GtnUIFrameWorkActionConfig searchValidationActionConfig = componentConfig
                .getUIFrameworkActionConfig(GtnUIFrameworkActionType.VALIDATION_ACTION);
        searchValidationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.ADJUSTMENT_TYPE,
                GtnFrameworkCommonConstants.GL_COMPANY,
                GtnFrameworkCommonConstants.WORKFLOW_ID,
                GtnFrameworkCommonConstants.BUSINESS_UNIT,
                GtnFrameworkCommonConstants.WORKFLOW_NAME,
                GtnFrameworkCommonConstants.ORIGINAL_BATCH_ID,
                GtnFrameworkCommonConstants.BRAND_NAME));

        GtnUIFrameWorkActionConfig alertActionConfig = componentConfig
                .getUIFrameworkActionConfig(GtnUIFrameworkActionType.ALERT_ACTION);

        List<Object> alertParamsList = new ArrayList<>();
        alertParamsList.add("Search Criteria ");
        alertParamsList.add("Please enter Search Criteria");

        alertActionConfig.setActionParameterList(alertParamsList);
        searchValidationActionConfig.setActionParameterList(
                Arrays.asList(GtnUIFrameworkValidationType.OR, Arrays.asList(alertActionConfig)));
        searchActionConfigList.add(searchValidationActionConfig);

        GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
        loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

        loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_RESULT_TABLE);
        loadDataTableActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.ADJUSTMENT_TYPE,
                GtnFrameworkCommonConstants.GL_COMPANY,
                GtnFrameworkCommonConstants.WORKFLOW_ID,
                GtnFrameworkCommonConstants.BUSINESS_UNIT,
                GtnFrameworkCommonConstants.WORKFLOW_NAME,
                GtnFrameworkCommonConstants.ORIGINAL_BATCH_ID,
                GtnFrameworkCommonConstants.BRAND_NAME));
//        loadDataTableActionConfig.setFieldDescription(Arrays.asList(GtnFrameworkCommonConstants.BRAND,
//                GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8, GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9));
        searchActionConfigList.add(loadDataTableActionConfig);

        GtnUIFrameWorkActionConfig notificationActionConfig = componentConfig
                .getUIFrameworkActionConfig(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
        notificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
        searchActionConfigList.add(notificationActionConfig);

        searchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

    }

    private void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig resetBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnReset01layout",
                true, GtnFrameworkCommonConstants.ITEM_SEARCH_BUTTONLAYOUT);
        componentList.add(resetBtnLayout);

        GtnUIFrameworkComponentConfig resetBtnConfig = componentConfig.getUIFrameworkComponentConfig("gtnReset01", true,
                resetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        resetBtnConfig.setComponentName("RESET");
        resetBtnConfig.setAuthorizationIncluded(true);
        componentList.add(resetBtnConfig);

        List<GtnUIFrameWorkActionConfig> resetBtnActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig resetActionConfig = componentConfig
                .getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);

        List<Object> paramsList = new ArrayList<>();
//        resetActionConfig
//                .addActionParameter(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_RESET_HEADER);
//        resetActionConfig.addActionParameter(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_RESET);

        Map<String, Object> resetMap = new HashMap<>(50);
        resetMap.put(GtnFrameworkCommonConstants.ITEM_SYSTEM_ID, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID,
                GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO,
                GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME,
                GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC,
                GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS, null);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE, null);
        resetMap.put(GtnFrameworkCommonConstants.THERAPEUTIC_CLASS, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9, null);
        resetMap.put("form", null);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME, null);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER,
                GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.BRAND, null);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8, null);
        resetMap.put(GtnFrameworkCommonConstants.STRENGTH, null);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID,
                GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE, null);
        resetActionConfig.addActionParameter(resetMap);

        resetActionConfig.addActionParameter(paramsList);
        resetBtnActionConfigList.add(resetActionConfig);
        resetBtnConfig.setGtnUIFrameWorkActionConfigList(resetBtnActionConfigList);

    }

    private void addDetailsFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        addCustomerNo(componentList, componentConfig);
        addItemNo(componentList, componentConfig);
        addDeductionLevel(componentList, componentConfig);
        addCreatedDate(componentList, componentConfig);
        addCustomerName(componentList, componentConfig);
        addItemName(componentList, componentConfig);
        addDeductionValue(componentList, componentConfig);
        addGlDate(componentList, componentConfig);
        addOriginalBatchId(componentList, componentConfig);
        addBrandName(componentList, componentConfig);
        addRedemptionPeriod(componentList, componentConfig);
        addPostingIndicator(componentList, componentConfig);
        addAccountCategory(componentList, componentConfig);
        addAccountType(componentList, componentConfig);
        addAdjustmentLevel(componentList, componentConfig);
        addAccount(componentList, componentConfig);
    }

    private void addCustomerNo(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Customer No";
        String objectComponentName = GtnFrameworkCommonConstants.CUSTOMER_NO;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addCustomerName(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Customer Name";
        String objectComponentName = GtnFrameworkCommonConstants.CUSTOMER_NAME;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addItemNo(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Item No";
        String objectComponentName = GtnFrameworkCommonConstants.ITEM_NO;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addItemName(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Item Name";
        String objectComponentName = GtnFrameworkCommonConstants.ITEM_NAME;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addDeductionLevel(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        ComponentPojo componentPojo = new ComponentPojo("Deduction Level", GtnFrameworkCommonConstants.DEDUCTION_LEVEL, "DEDUCTION_LEVELS", GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
        componentPojo.setActionConfigList(getDeductionLevelActionConfig());
        GtnUIFrameworkComponentConfig compConfig = getComboBoxComponent(componentConfig, componentList, componentPojo);
        compConfig.setGtnUIFrameWorkActionConfigList(componentPojo.getActionConfigList());
    }

    private void addCreatedDate(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Created Date";
        String objectComponentName = GtnFrameworkCommonConstants.CREATED_DATE;
        getPopupFieldComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addDeductionValue(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Deduction Value";
        String objectComponentName = GtnFrameworkCommonConstants.DEDUCTION_VALUE;
        getMenuBarComponent(componentConfig, objectComponentName, componentList, componentName, "DEDUCTION_LEVELS", GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);

    }

    private void addGlDate(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "GL Date";
        String objectComponentName = GtnFrameworkCommonConstants.GL_DATE;
        getPopupFieldComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addOriginalBatchId(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Original Batch ID";
        String objectComponentName = GtnFrameworkCommonConstants.ORIGINAL_BATCH_ID;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addBrandName(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Brand Name";
        String objectComponentName = GtnFrameworkCommonConstants.BRAND_NAME;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addRedemptionPeriod(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "RedemptionPeriod";
        String objectComponentName = GtnFrameworkCommonConstants.REDEMPTION_PERIOD;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addAccount(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Account";
        String objectComponentName = GtnFrameworkCommonConstants.ACCOUNT;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addPostingIndicator(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Posting Indicator";
        String objectComponentName = GtnFrameworkCommonConstants.POSTING_INDICATOR;
        List posInd = new ArrayList<>();
        posInd.add("N");
        posInd.add("Y");
        getComboBoxComponent(componentConfig, objectComponentName, componentList, componentName, posInd, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addAccountCategory(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Account Category";
        String objectComponentName = GtnFrameworkCommonConstants.ACCOUNT_CATEGORY;
        List accCategory = new ArrayList<>();
        accCategory.add("Balance Sheet");
        accCategory.add("P&L");
        getComboBoxComponent(componentConfig, objectComponentName, componentList, componentName, accCategory, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addAccountType(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Account Type";
        String objectComponentName = GtnFrameworkCommonConstants.ACCOUNT_TYPE;
        List accCategory = new ArrayList<>();
        accCategory.add("Expense");
        accCategory.add("Liability");
        getComboBoxComponent(componentConfig, objectComponentName, componentList, componentName, accCategory, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addAdjustmentLevel(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Adjustment Level";
        String objectComponentName = GtnFrameworkCommonConstants.ADJUSTMENT_LEVEL;
        List adjLevels = new ArrayList<>();
        adjLevels.add("Item");
        getComboBoxComponent(componentConfig, objectComponentName, componentList, componentName, adjLevels, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
    }

    private void addResultPanel(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig itemMasterResultPanelConfig = componentConfig.getPanelConfig("ResultPanel",
                false, null);
        itemMasterResultPanelConfig.setComponentName("Results");
        itemMasterResultPanelConfig.setAuthorizationIncluded(true);
        componentList.add(itemMasterResultPanelConfig);

        addResultLayout(componentList, componentConfig);
    }

    private void addResultLayout(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig resultTableLayout = componentConfig.getHorizontalLayoutConfig("Resultlayout",
                true, "ResultPanel");
        resultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        componentList.add(resultTableLayout);

        addPagedTableComponent(componentList, componentConfig);
    }

    private void addPagedTableComponent(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig searchResultConfig = componentConfig.getUIFrameworkComponentConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_RESULT_TABLE, true, "Resultlayout",
                GtnUIFrameworkComponentType.PAGEDTABLE);
        searchResultConfig.setAuthorizationIncluded(true);
        searchResultConfig.setComponentName("Results");
        searchResultConfig.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);

        componentList.add(searchResultConfig);

        GtnUIFrameworkPagedTableConfig searchResults = componentConfig.getPagedTableConfig(true, true,
                GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_CONTROLLER + GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_TABLE_LOAD_SERVICE,
                GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_DEDUCTION_VALUE_CONTROLLER + GtnWebServiceUrlConstants.GTN_ADJUSTMENT_DETAILS_TABLE_LOAD_SERVICE,
                "itemMaster", "SearchQuery");
        searchResults.setSinkItemPerPageWithPageLength(false);
        searchResults.setTableColumnDataType(new Class<?>[]{GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING, GtnFrameworkCommonConstants.JAVALANG_STRING,
            GtnFrameworkCommonConstants.JAVALANG_STRING});
        searchResults.setTableVisibleHeader(
                new String[]{"Company", "Division", "Cost Center", "Account", "Brand", "Project", "Future 1",
                    "Future 2", "Debit", "Credit", "Line Description", "Balance Type", "Database", "Data Access Set", "Chart of Accounts",
                    "Ledger", "Category", "Source", "Currency", "Accounting Date", "Batch Name", "Journal Name", "Journal Description",
                    "Reverse Journal", "Reversal Period Date", "Business Unit", "Adjustment Type", "Adjustment Level", "Account Category",
                    "Account Type", "Account Description", "Account Indicator", "UDC 1", "UDC 2", "UDC 3", "UDC 4", "UDC 5", "UDC 6",
                    "Redemption Period", "Calculation Period", "Posting Indicator", "Workflow ID", "Workflow Name", "Workflow Created By",
                    "Workflow Created Date", "Workflow Approved By", "Workflow Approved  Date", "Batch ID", "Original Batch ID", "Adjustment Reserve Detail SID "});
        searchResults.setTableColumnMappingId(new Object[]{GtnFrameworkCommonConstants.RESERVE_DETAILS_COMPANY,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_DIVISION, GtnFrameworkCommonConstants.RESERVE_DETAILS_COST_CENTER,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNT, GtnFrameworkCommonConstants.RESERVE_DETAILS_BRAND,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_PROJECT, GtnFrameworkCommonConstants.RESERVE_DETAILS_FUTURE_1,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_FUTURE_2, GtnFrameworkCommonConstants.RESERVE_DETAILS_DEBIT,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_CREDIT, GtnFrameworkCommonConstants.RESERVE_DETAILS_LINE_DESCRIPTION,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_BALANCE_TYPE, GtnFrameworkCommonConstants.RESERVE_DETAILS_DATABASE,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_DATA_ACCESS_SET, GtnFrameworkCommonConstants.RESERVE_DETAILS_CHART_OF_ACCOUNTS,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_LEDGER, GtnFrameworkCommonConstants.RESERVE_DETAILS_CATEGORY,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_SOURCE, GtnFrameworkCommonConstants.RESERVE_DETAILS_CURRENCY,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNTING_DATE, GtnFrameworkCommonConstants.RESERVE_DETAILS_BATCH_NAME,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_JOURNAL_NAME, GtnFrameworkCommonConstants.RESERVE_DETAILS_JOURNAL_DESCRIPTION,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_REVERSE_JOURNAL, GtnFrameworkCommonConstants.RESERVE_DETAILS_REVERSAL_PERIOD_DATE,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_BUSINESS_UNIT, GtnFrameworkCommonConstants.RESERVE_DETAILS_ADJUSTMENT_TYPE,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_ADJUSTMENT_LEVEL, GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNT_CATEGORY,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNT_TYPE, GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNT_DESCRIPTION,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_ACCOUNT_INDICATOR, GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_1,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_2, GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_3,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_4, GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_5,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_UDC_6, GtnFrameworkCommonConstants.RESERVE_DETAILS_REDEMPTION_PERIOD,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_CALCULATION_PERIOD, GtnFrameworkCommonConstants.RESERVE_DETAILS_POSTING_INDICATOR,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_ID, GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_NAME,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_CREATED_BY, GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_CREATED_DATE,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_APPROVED_BY, GtnFrameworkCommonConstants.RESERVE_DETAILS_WORKFLOW_APPROVED__DATE,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_BATCH_ID, GtnFrameworkCommonConstants.RESERVE_DETAILS_ORIGINAL_BATCH_ID,
            GtnFrameworkCommonConstants.RESERVE_DETAILS_ADJUSTMENT_RESERVE_DETAIL_SID});

        searchResults.setSearchQueryConfigLoaderType(GtnWsSearchQueryConfigLoaderType.ADJUSTMENT_DETAILS);

        searchResults.setCustomFilterConfigMap(getCustomFilterConfig(componentConfig));
        searchResults.setDoubleClickEnable(true);
        List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();

        GtnUIFrameWorkActionConfig navigationActionConfig = componentConfig
                .getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
        navigationActionConfig.addActionParameter("V002");
        actionConfigList.add(navigationActionConfig);

        searchResults.setGtnUIFrameWorkActionConfigList(actionConfigList);
        searchResultConfig.setGtnPagedTableConfig(searchResults);
    }

    private Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> getCustomFilterConfig(
            GtnFrameworkComponentConfigProvider componentConfig) {
        Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
        String[] propertyIds = GtnFrameworkAdjustmentDetailsStringConstants.getCustomFilterPropertyIds();
        String[] listNameArray = GtnFrameworkAdjustmentDetailsStringConstants.getCustomFilterListNameArray();
        for (int i = 0; i < propertyIds.length; i++) {
            GtnUIFrameworkPagedTableCustomFilterConfig customFilterConfig = new GtnUIFrameworkPagedTableCustomFilterConfig();
            GtnUIFrameworkComponentConfig customFilterComponentConfig = new GtnUIFrameworkComponentConfig();
            GtnUIFrameworkComboBoxConfig filterConfig = componentConfig.getComboBoxConfig(listNameArray[i],
                    GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
                    + GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX);
            customFilterComponentConfig.setComponentId("customFilterComboBox");
            customFilterComponentConfig.setComponentName("customFilterComboBox");
            filterConfig.setDefaultValue(GtnFrameworkCommonStringConstants.SHOW_ALL);
            customFilterComponentConfig.setGtnComboboxConfig(filterConfig);
            customFilterConfig.setGtnComponentConfig(customFilterComponentConfig);
            customFilterConfig.setPropertId(propertyIds[i]);
            customFilterConfig.setGtnComponentType(GtnUIFrameworkComponentType.COMBOBOX);

            customFilterConfigMap.put(customFilterConfig.getPropertId(), customFilterConfig);

        }
        return customFilterConfigMap;
    }

    private void addSaveViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {

    }

    private List<GtnUIFrameWorkActionConfig> getDeductionLevelActionConfig() {
        List<GtnUIFrameWorkActionConfig> actionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig customAction = new GtnUIFrameWorkActionConfig();
        customAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        customAction.addActionParameter(GtnFrameworkDeductionLevelValueChange.class.getName());
        customAction.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_LEVEL);
        customAction.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_VALUE);
        actionConfigList.add(customAction);
        return actionConfigList;
    }

}
