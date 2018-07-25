/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.config.searchConfig;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkDeductionLevelValueChange;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkTransactionDetailsValueChange;
import com.stpl.gtn.gtn2o.ui.config.abstractConfig.GTNFrameworkAbstractComponent;
import com.stpl.gtn.gtn2o.ui.config.searchConfig.buttonConfig.GtnAdjustmentDetailsButtonConfig;
import com.stpl.gtn.gtn2o.ui.config.searchConfig.tableConfig.GtnAdjustmentDetailsResultsTableConfig;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkAdjustmentDetailsStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ui.pojo.ComponentPojo;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkAdjustmentDetailsSearchConfig extends GTNFrameworkAbstractComponent {

    private final GtnAdjustmentDetailsButtonConfig buttonConfig = new GtnAdjustmentDetailsButtonConfig();
    private final GtnAdjustmentDetailsResultsTableConfig tableConfig = new GtnAdjustmentDetailsResultsTableConfig();

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
        addActionButtonLayout(componentList, componentConfig);
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

        List<GtnUIFrameWorkActionConfig> parentCFPIdActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig parentCFPIdPopupActionConfig = new GtnUIFrameWorkActionConfig();
        parentCFPIdPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
        parentCFPIdPopupActionConfig.addActionParameter("V003");
        parentCFPIdPopupActionConfig.addActionParameter("Public View");
        parentCFPIdPopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
        parentCFPIdPopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_70);
        parentCFPIdActionConfigList.add(parentCFPIdPopupActionConfig);
        publicViewConfig.setGtnUIFrameWorkActionConfigList(parentCFPIdActionConfigList);

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
        transactionLevels.add("GTN Details");
        transactionLevels.add("Reserve Details");
        GtnUIFrameworkComponentConfig transactionLevelConfig = getComboBoxComponent(componentConfig, objectComponentName, componentList, componentName, transactionLevels, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_SUMMARY_LAYOUT, true);
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
        customAction.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_VALUE);
        customAction.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_RESULT_TABLE);
        actionConfigList.add(customAction);
        GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
        loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
        loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_RESULT_TABLE);
        actionConfigList.add(loadDataTableActionConfig);
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

        buttonConfig.addSearchButtonComponent(componentList, componentConfig);
        buttonConfig.addSaveViewButtonComponent(componentList, componentConfig);
        buttonConfig.addResetButtonComponent(componentList, componentConfig);

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
        String componentName = "Redemption Period";
        String objectComponentName = GtnFrameworkCommonConstants.REDEMPTION_PERIOD;
        GtnUIFrameworkComponentConfig systemIdLayout = componentConfig.getHorizontalLayoutConfig(
                objectComponentName + "Layout", true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT);
        componentList.add(systemIdLayout);

        GtnUIFrameworkComponentConfig redemptionPeriodConfig = componentConfig.getUIFrameworkComponentConfig(
                objectComponentName, true, systemIdLayout.getComponentId(),
                GtnUIFrameworkComponentType.POPUPTEXTFIELD);
        redemptionPeriodConfig.setAuthorizationIncluded(true);
        redemptionPeriodConfig.setComponentName(componentName);
        componentList.add(redemptionPeriodConfig);

        List<GtnUIFrameWorkActionConfig> parentCFPIdActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig parentCFPIdPopupActionConfig = new GtnUIFrameWorkActionConfig();
        parentCFPIdPopupActionConfig.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
        parentCFPIdPopupActionConfig.addActionParameter("V004");
        parentCFPIdPopupActionConfig.addActionParameter(componentName);
        parentCFPIdPopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_40);
        parentCFPIdPopupActionConfig.addActionParameter(GtnFrameworkCssConstants.PERCENT_20);
        parentCFPIdActionConfigList.add(parentCFPIdPopupActionConfig);
        redemptionPeriodConfig.setGtnUIFrameWorkActionConfigList(parentCFPIdActionConfigList);
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
        getComboBoxComponent(componentConfig, objectComponentName, componentList, componentName, posInd, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT, false);
    }

    private void addAccountCategory(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Account Category";
        String objectComponentName = GtnFrameworkCommonConstants.ACCOUNT_CATEGORY;
        List accCategory = new ArrayList<>();
        accCategory.add("Balance Sheet");
        accCategory.add("P&L");
        getComboBoxComponent(componentConfig, objectComponentName, componentList, componentName, accCategory, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT, false);
    }

    private void addAccountType(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Account Type";
        String objectComponentName = GtnFrameworkCommonConstants.ACCOUNT_TYPE;
        List accCategory = new ArrayList<>();
        accCategory.add("Expense");
        accCategory.add("Liability");
        getComboBoxComponent(componentConfig, objectComponentName, componentList, componentName, accCategory, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT, false);
    }

    private void addAdjustmentLevel(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "Adjustment Level";
        String objectComponentName = GtnFrameworkCommonConstants.ADJUSTMENT_LEVEL;
        List adjLevels = new ArrayList<>();
        adjLevels.add("Item");
        getComboBoxComponent(componentConfig, objectComponentName, componentList, componentName, adjLevels, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_CRITERIA_DETAILS_LAYOUT, false);
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

        GtnUIFrameworkComponentConfig resultTableLayout = componentConfig.getHorizontalLayoutConfig(GtnFrameworkAdjustmentDetailsStringConstants.RESULT_LAYOUT,
                true, "ResultPanel");
        resultTableLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        componentList.add(resultTableLayout);

        tableConfig.addPagedTableComponent(componentList, componentConfig);
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

    private void addActionButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig itemMasterButtonLayout = componentConfig.getGtnCssLayoutConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_EXCEL_REPROCESS_BUTTON_LAYOUT, false, null,
                GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
        itemMasterButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        componentList.add(itemMasterButtonLayout);

        buttonConfig.addExcelButtonComponent(componentList, componentConfig);
        reprocessButtonLayout(componentList, componentConfig);
    }

    private void reprocessButtonLayout(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig itemMasterButtonLayout = componentConfig.getGtnCssLayoutConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REPROCESS_BUTTON_LAYOUT, true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_EXCEL_REPROCESS_BUTTON_LAYOUT,
                GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
        itemMasterButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        componentList.add(itemMasterButtonLayout);
        buttonConfig.addResetListViewButtonComponent(componentList, componentConfig);
        buttonConfig.addReprocessButtonComponent(componentList, componentConfig);
    }

}
