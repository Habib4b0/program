/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.config.searchConfig.buttonConfig;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkTransactionRefreshBeanAction;
import com.stpl.gtn.gtn2o.ui.action.GtnUIFrameworkTransactionReprocessRemoveAction;
import com.stpl.gtn.gtn2o.ui.action.validation.GtnFrameworkTransactionReprocessRemoveValidation;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkAdjustmentDetailsStringConstants;
import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkAdjustmentDetailsWindowMessages;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnAdjustmentDetailsButtonConfig {

    public void addSearchButtonComponent(List<GtnUIFrameworkComponentConfig> componentList,
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
        searchValidationActionConfig.setFieldValues(Arrays.asList(GtnFrameworkCommonConstants.WORKFLOW_ID,
                GtnFrameworkCommonConstants.WORKFLOW_NAME,
                GtnFrameworkCommonConstants.ADJUSTMENT_TYPE,
                GtnFrameworkCommonConstants.CREATED_DATE,
                GtnFrameworkCommonConstants.ITEM_NO,
                GtnFrameworkCommonConstants.ITEM_NAME,
                GtnFrameworkCommonConstants.GL_DATE,
                GtnFrameworkCommonConstants.ORIGINAL_BATCH_ID,
                GtnFrameworkCommonConstants.BRAND_NAME,
                GtnFrameworkCommonConstants.REDEMPTION_PERIOD,
                GtnFrameworkCommonConstants.CUSTOMER_NO,
                GtnFrameworkCommonConstants.CUSTOMER_NAME,
                GtnFrameworkCommonConstants.TRANSACTION_LEVEL,
                GtnFrameworkCommonConstants.GL_COMPANY,
                GtnFrameworkCommonConstants.BUSINESS_UNIT,
                GtnFrameworkCommonConstants.DEDUCTION_LEVEL,
                GtnFrameworkCommonConstants.POSTING_INDICATOR,
                GtnFrameworkCommonConstants.ACCOUNT_CATEGORY,
                GtnFrameworkCommonConstants.ACCOUNT_TYPE,
                GtnFrameworkCommonConstants.ADJUSTMENT_LEVEL
        ));

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
        loadDataTableActionConfig.setFieldValues(Arrays.asList(
                GtnFrameworkCommonConstants.WORKFLOW_ID,
                GtnFrameworkCommonConstants.WORKFLOW_NAME,
                GtnFrameworkCommonConstants.ADJUSTMENT_TYPE,
                GtnFrameworkCommonConstants.CREATED_DATE,
                GtnFrameworkCommonConstants.ITEM_NO,
                GtnFrameworkCommonConstants.ITEM_NAME,
                GtnFrameworkCommonConstants.GL_DATE,
                GtnFrameworkCommonConstants.ORIGINAL_BATCH_ID,
                GtnFrameworkCommonConstants.BRAND_NAME,
                GtnFrameworkCommonConstants.REDEMPTION_PERIOD,
                GtnFrameworkCommonConstants.CUSTOMER_NAME,
                GtnFrameworkCommonConstants.CUSTOMER_NO));
        loadDataTableActionConfig.setFieldDescription(Arrays.asList(
                GtnFrameworkCommonConstants.TRANSACTION_LEVEL,
                GtnFrameworkCommonConstants.GL_COMPANY,
                GtnFrameworkCommonConstants.BUSINESS_UNIT,
                GtnFrameworkCommonConstants.DEDUCTION_LEVEL,
                GtnFrameworkCommonConstants.POSTING_INDICATOR,
                GtnFrameworkCommonConstants.ACCOUNT_CATEGORY,
                GtnFrameworkCommonConstants.ACCOUNT_TYPE,
                GtnFrameworkCommonConstants.ADJUSTMENT_LEVEL));
        searchActionConfigList.add(loadDataTableActionConfig);

        GtnUIFrameWorkActionConfig notificationActionConfig = componentConfig
                .getUIFrameworkActionConfig(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
        notificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_RESULT_TABLE);
        searchActionConfigList.add(notificationActionConfig);
        searchButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);

    }

    public void addSaveViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig searchButtonConfig = new GtnUIFrameworkComponentConfig();
        searchButtonConfig.setComponentType(GtnUIFrameworkComponentType.BUTTON);
        searchButtonConfig.setAuthorizationIncluded(true);
        searchButtonConfig.setComponentId("gtnSaveView01layout");
        searchButtonConfig.setComponentName("SAVE VIEW");
        searchButtonConfig.setParentComponentId(GtnFrameworkCommonConstants.ITEM_SEARCH_BUTTONLAYOUT);
        searchButtonConfig.setAddToParent(true);
        componentList.add(searchButtonConfig);
        GtnUIFrameWorkActionConfig navigationActionConfig = componentConfig.getUIFrameworkActionConfig(GtnUIFrameworkActionType.NAVIGATION_ACTION);
        navigationActionConfig.addActionParameter("V002");
        List<GtnUIFrameWorkActionConfig> saveViewActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig confi = new GtnUIFrameWorkActionConfig();
        confi.setActionType(GtnUIFrameworkActionType.POPUP_ACTION);
        confi.addActionParameter("V002");
        confi.addActionParameter("Save View");
        confi.addActionParameter("25%");
        confi.addActionParameter("18%");
        saveViewActionConfigList.add(confi);
        searchButtonConfig.setGtnUIFrameWorkActionConfigList(saveViewActionConfigList);
    }

    public void addResetButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig resetBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnReset01layout", true, GtnFrameworkCommonConstants.ITEM_SEARCH_BUTTONLAYOUT);
        componentList.add(resetBtnLayout);
        GtnUIFrameworkComponentConfig resetBtnConfig = componentConfig.getUIFrameworkComponentConfig("gtnReset01", true, resetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        resetBtnConfig.setComponentName("RESET");
        resetBtnConfig.setAuthorizationIncluded(true);
        componentList.add(resetBtnConfig);
        List<GtnUIFrameWorkActionConfig> resetBtnActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig resetActionConfig = componentConfig.getUIFrameworkActionConfig(GtnUIFrameworkActionType.RESET_ACTION);
        List<Object> paramsList = new ArrayList<>();
        resetActionConfig.addActionParameter(GtnFrameworkAdjustmentDetailsWindowMessages.GTN_ITEM_MASTER_VALIDATION_MSG_RESET_HEADER);
        resetActionConfig.addActionParameter(GtnFrameworkAdjustmentDetailsWindowMessages.GTN_ITEM_MASTER_VALIDATION_MSG_RESET);
        Map<String, Object> resetMap = new HashMap<>(50);
        resetMap.put(GtnFrameworkCommonConstants.TRANSACTION_LEVEL, "Reserve Details");
        resetMap.put(GtnFrameworkCommonConstants.WORKFLOW_ID, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.WORKFLOW_NAME, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.ADJUSTMENT_TYPE, null);
        resetMap.put(GtnFrameworkCommonConstants.CREATED_DATE, null);
        resetMap.put(GtnFrameworkCommonConstants.ITEM_NO, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.ITEM_NAME, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.GL_DATE, null);
        resetMap.put(GtnFrameworkCommonConstants.ORIGINAL_BATCH_ID, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.BRAND_NAME, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.REDEMPTION_PERIOD, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.CUSTOMER_NAME, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.CUSTOMER_NO, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.TRANSACTION_LEVEL, null);
        resetMap.put(GtnFrameworkCommonConstants.GL_COMPANY, null);
        resetMap.put(GtnFrameworkCommonConstants.BUSINESS_UNIT, null);
        resetMap.put(GtnFrameworkCommonConstants.DEDUCTION_LEVEL, null);
        resetMap.put(GtnFrameworkCommonConstants.POSTING_INDICATOR, null);
        resetMap.put(GtnFrameworkCommonConstants.ACCOUNT_CATEGORY, null);
        resetMap.put(GtnFrameworkCommonConstants.ACCOUNT_TYPE, null);
        resetMap.put(GtnFrameworkCommonConstants.ADJUSTMENT_LEVEL, null);
        resetActionConfig.addActionParameter(resetMap);
        resetActionConfig.addActionParameter(paramsList);
        resetBtnActionConfigList.add(resetActionConfig);
        resetBtnConfig.setGtnUIFrameWorkActionConfigList(resetBtnActionConfigList);
    }

    public void addExcelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig excelBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnExcellayout",
                true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_EXCEL_REPROCESS_BUTTON_LAYOUT);
        componentList.add(excelBtnLayout);

        GtnUIFrameworkComponentConfig excelButton = new GtnUIFrameworkComponentConfig();
        excelButton.setComponentType(GtnUIFrameworkComponentType.EXCEL_BUTTON);
        excelButton.setAuthorizationIncluded(true);
        excelButton.setAddToParent(true);
        excelButton.setParentComponentId("gtnExcellayout");
        componentList.add(excelButton);

        GtnUIFrameworkExcelButtonConfig cfpExcelButtonConfig = componentConfig.getExcelBtnconfig("Adjustment Details", true,
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_RESULT_TABLE, false);
        excelButton.setGtnUIFrameworkExcelButtonConfig(cfpExcelButtonConfig);
        GtnUIFrameWorkActionConfig excelAction = new GtnUIFrameWorkActionConfig();
        excelAction.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
        excelAction.addActionParameter(cfpExcelButtonConfig);
        excelButton.setGtnUIFrameWorkActionConfigList(Arrays.asList(excelAction));
    }

    public void addReprocessButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig resetBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnReprocesslayout", true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REPROCESS_BUTTON_LAYOUT);
        componentList.add(resetBtnLayout);
        GtnUIFrameworkComponentConfig reprocessBtnConfig = componentConfig.getUIFrameworkComponentConfig("gtnReprocess01", true, resetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        reprocessBtnConfig.setComponentName("REPROCESS");
        reprocessBtnConfig.setAuthorizationIncluded(true);
        componentList.add(reprocessBtnConfig);
        List<GtnUIFrameWorkActionConfig> gtnUIFrameworkReprocessConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig reprocessAlertAction = new GtnUIFrameWorkActionConfig();
        reprocessAlertAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        reprocessAlertAction.addActionParameter(GtnFrameworkTransactionReprocessRemoveValidation.class.getName());
        reprocessAlertAction.addActionParameter(GtnFrameworkAdjustmentDetailsStringConstants.REPROCESS);
        reprocessAlertAction.addActionParameter(GtnFrameworkAdjustmentDetailsStringConstants.RESULT_LAYOUT);
        reprocessAlertAction.addActionParameter(true);
        reprocessAlertAction.addActionParameter("Adjustment Details");
        gtnUIFrameworkReprocessConfigList.add(reprocessAlertAction);
        List<GtnUIFrameWorkActionConfig> reprocess = new ArrayList<>();
        Object resultpanelLayout = GtnFrameworkAdjustmentDetailsStringConstants.RESULT_LAYOUT;
        GtnUIFrameWorkActionConfig reprocessAction = new GtnUIFrameWorkActionConfig();
        boolean isOutbound = true;
        String wsReprocessURL = "/gtnTransactionOutbound/reprocess";
        List<String> stagingInsertColumns = new ArrayList<>();
        List<String> stagingUpdateColumns = new ArrayList<>();
        List<String> stagingUpdateColumnValues = new ArrayList<>();
        reprocessAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        reprocessAction.setActionParameterList(Arrays.asList(
                GtnUIFrameworkTransactionReprocessRemoveAction.class.getName(), GtnFrameworkAdjustmentDetailsStringConstants.REPROCESS,
                resultpanelLayout, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_RESULT_TABLE, wsReprocessURL,
                isOutbound, stagingInsertColumns, stagingUpdateColumns, stagingUpdateColumnValues));
        reprocess.add(reprocessAction);
        GtnUIFrameWorkActionConfig resetBeanAction = new GtnUIFrameWorkActionConfig();
        resetBeanAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        resetBeanAction.addActionParameter(GtnUIFrameworkTransactionRefreshBeanAction.class.getName());
        resetBeanAction.addActionParameter(GtnFrameworkAdjustmentDetailsStringConstants.RESULT_LAYOUT);
        resetBeanAction.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_RESULT_TABLE);
        resetBeanAction.addActionParameter(GtnFrameworkCommonConstants.CHECK_RECORD_ID);
        reprocess.add(resetBeanAction);

        GtnUIFrameWorkActionConfig reprocessConfirmationActionConfig = new GtnUIFrameWorkActionConfig();
        reprocessConfirmationActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
        List<Object> deleteConfirmationAlertParams = new ArrayList<>();
        deleteConfirmationAlertParams.add("Confirmation");
        deleteConfirmationAlertParams.add("Are you sure you want to Reprocess the selected record(s)  ?");
        GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
        loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);

        loadDataTableActionConfig.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SEARCH_RESULT_TABLE);
        loadDataTableActionConfig.setFieldValues(Arrays.asList(
                GtnFrameworkCommonConstants.WORKFLOW_ID,
                GtnFrameworkCommonConstants.WORKFLOW_NAME,
                GtnFrameworkCommonConstants.ADJUSTMENT_TYPE,
                GtnFrameworkCommonConstants.CREATED_DATE,
                GtnFrameworkCommonConstants.ITEM_NO,
                GtnFrameworkCommonConstants.ITEM_NAME,
                GtnFrameworkCommonConstants.GL_DATE,
                GtnFrameworkCommonConstants.ORIGINAL_BATCH_ID,
                GtnFrameworkCommonConstants.BRAND_NAME,
                GtnFrameworkCommonConstants.REDEMPTION_PERIOD,
                GtnFrameworkCommonConstants.CUSTOMER_NAME,
                GtnFrameworkCommonConstants.CUSTOMER_NO));
        loadDataTableActionConfig.setFieldDescription(Arrays.asList(
                GtnFrameworkCommonConstants.TRANSACTION_LEVEL,
                GtnFrameworkCommonConstants.GL_COMPANY,
                GtnFrameworkCommonConstants.BUSINESS_UNIT,
                GtnFrameworkCommonConstants.DEDUCTION_LEVEL,
                GtnFrameworkCommonConstants.POSTING_INDICATOR,
                GtnFrameworkCommonConstants.ACCOUNT_CATEGORY,
                GtnFrameworkCommonConstants.ACCOUNT_TYPE,
                GtnFrameworkCommonConstants.ADJUSTMENT_LEVEL));

        reprocess.add(loadDataTableActionConfig);
        deleteConfirmationAlertParams.add(reprocess);
        reprocessConfirmationActionConfig.setActionParameterList(deleteConfirmationAlertParams);
        gtnUIFrameworkReprocessConfigList.add(reprocessConfirmationActionConfig);
        reprocessBtnConfig.setGtnUIFrameWorkActionConfigList(gtnUIFrameworkReprocessConfigList);
    }

    public void addResetListViewButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig resetBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnReset02layout", true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REPROCESS_BUTTON_LAYOUT);
        componentList.add(resetBtnLayout);
        GtnUIFrameworkComponentConfig resetBtnConfig = componentConfig.getUIFrameworkComponentConfig("gtnReset01", true, resetBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        resetBtnConfig.setComponentName("RESET");
        resetBtnConfig.setAuthorizationIncluded(true);
        componentList.add(resetBtnConfig);
    }

}
