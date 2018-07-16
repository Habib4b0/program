/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.config.searchConfig.buttonConfig;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
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
        loadDataTableActionConfig.setFieldValues(Arrays.asList(
                GtnFrameworkCommonConstants.WORKFLOW_ID,
                GtnFrameworkCommonConstants.WORKFLOW_NAME,
                GtnFrameworkCommonConstants.ITEM_NO,
                GtnFrameworkCommonConstants.ITEM_NAME,
                GtnFrameworkCommonConstants.GL_DATE,
                GtnFrameworkCommonConstants.ORIGINAL_BATCH_ID,
                GtnFrameworkCommonConstants.BRAND_NAME,
                GtnFrameworkCommonConstants.REDEMPTION_PERIOD,
                GtnFrameworkCommonConstants.CUSTOMER_NO));
        loadDataTableActionConfig.setFieldDescription(Arrays.asList(
                GtnFrameworkCommonConstants.TRANSACTION_LEVEL,
                GtnFrameworkCommonConstants.ADJUSTMENT_TYPE,
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
        notificationActionConfig.addActionParameter(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE);
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
//                resetActionConfig
//                        .addActionParameter(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_RESET_HEADER);
//                resetActionConfig.addActionParameter(GtnFrameworkItemMasterStringContants.GTN_ITEM_MASTER_VALIDATION_MSG_RESET);
        Map<String, Object> resetMap = new HashMap<>(50);
        resetMap.put(GtnFrameworkCommonConstants.ITEM_SYSTEM_ID, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_ID, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NO, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_NAME, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_DESC, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_STATUS, null);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_TYPE, null);
        resetMap.put(GtnFrameworkCommonConstants.THERAPEUTIC_CLASS, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC9, null);
        resetMap.put("form", null);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_QUALIFIER_NAME, null);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_ITEM_IDENTIFIER, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.BRAND, null);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_NDC8, null);
        resetMap.put(GtnFrameworkCommonConstants.STRENGTH, null);
        resetMap.put(GtnFrameworkCommonConstants.I_MASTER_SEARCH_BATCH_ID, GtnFrameworkCommonStringConstants.STRING_EMPTY);
        resetMap.put(GtnFrameworkCommonConstants.ITEM_MASTERSEARCH_RESULT_TABLE, null);
        resetActionConfig.addActionParameter(resetMap);
        resetActionConfig.addActionParameter(paramsList);
        resetBtnActionConfigList.add(resetActionConfig);
        resetBtnConfig.setGtnUIFrameWorkActionConfigList(resetBtnActionConfigList);
    }

}
