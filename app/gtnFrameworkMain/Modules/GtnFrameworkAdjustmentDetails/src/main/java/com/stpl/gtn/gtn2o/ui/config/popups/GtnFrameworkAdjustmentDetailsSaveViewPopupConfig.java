/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.config.popups;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkAdjustmentDetailsSaveViewAction;
import com.stpl.gtn.gtn2o.ui.config.abstractConfig.GTNFrameworkAbstractComponent;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.combo.GtnUIFrameworkOptionGroupConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkLayoutType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.css.GtnFrameworkCssConstants;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkAdjustmentDetailsSaveViewPopupConfig extends GTNFrameworkAbstractComponent {

    public GtnUIFrameworkViewConfig getSaveView() {
        GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
        GtnUIFrameworkViewConfig saveView = componentConfig.getViewConfig("SaveViewLookup", "V002", false);
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        saveView.setGtnComponentList(componentList);
        addComponentList(saveView, componentConfig);
        return saveView;
    }

    private void addComponentList(GtnUIFrameworkViewConfig searchView, GtnFrameworkComponentConfigProvider componentConfig) {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        searchView.setGtnComponentList(componentList);
        addSearchCriteriaPanel(componentList, componentConfig);

    }

    private void addSearchCriteriaPanel(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig itemMasterSearchPanel = componentConfig.getPanelConfig("viewPanel",
                false, null);
        itemMasterSearchPanel.setComponentName("");
        itemMasterSearchPanel.setAuthorizationIncluded(true);
        itemMasterSearchPanel.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterSearchPanel.setComponentHight(GtnFrameworkCssConstants.PERCENT_100);
        componentList.add(itemMasterSearchPanel);
        addFieldLayout(componentList, componentConfig);
    }

    private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig itemMasterSearchLayout = componentConfig.getGtnCssLayoutConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SAVE_VIEW_LAYOUT, true, "viewPanel",
                GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
        itemMasterSearchLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterSearchLayout.setComponentHight(GtnFrameworkCssConstants.PERCENT_100);
        componentList.add(itemMasterSearchLayout);
        addFieldComponent(componentList, componentConfig);
    }

    private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        addViewType(componentList, componentConfig);
        addViewName(componentList, componentConfig);
        addButtonLayout(componentList, componentConfig);
    }

    private void addViewName(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "View Name";
        String objectComponentName = GtnFrameworkCommonConstants.SAVE_VIEW_VIEW_NAME;
        getTextBoxComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SAVE_VIEW_LAYOUT);
    }

    private void addViewType(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentId = GtnFrameworkCommonConstants.SAVE_VIEW_VIEW_TYPE;
        GtnUIFrameworkComponentConfig gtnLayoutConfig = componentConfig
                .getHorizontalLayoutConfig("saveViewTypeLayout", true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SAVE_VIEW_LAYOUT);
        gtnLayoutConfig.addComponentStyle(GtnFrameworkCssConstants.GRID_SINGLE_IN_LAYOUT);
        componentList.add(gtnLayoutConfig);

        GtnUIFrameworkComponentConfig searchTypeConfig = componentConfig.getUIFrameworkComponentConfig(componentId, true,
                gtnLayoutConfig.getComponentId(), GtnUIFrameworkComponentType.OPTIONGROUP);
        searchTypeConfig.setAuthorizationIncluded(true);
        searchTypeConfig.setComponentName("Search Type");
        searchTypeConfig.addComponentStyle(GtnFrameworkCssConstants.HORIZONTAL_LOWER_CASE);
        componentList.add(searchTypeConfig);

        GtnUIFrameworkOptionGroupConfig searchTypeOptionConfig = new GtnUIFrameworkOptionGroupConfig();
        searchTypeOptionConfig.setValuesFromService(false);
        searchTypeOptionConfig.setItemValues(
                Arrays.asList("Private", "public"));
        searchTypeOptionConfig.setDefaultSelection("Private");
        searchTypeConfig.setGtnUIFrameworkOptionGroupConfig(searchTypeOptionConfig);
    }

    private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig itemMasterButtonLayout = componentConfig
                .getCssLayoutConfig(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SAVE_VIEW_BUTTONLAYOUT, true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SAVE_VIEW_LAYOUT);
        itemMasterButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_3);
        componentList.add(itemMasterButtonLayout);

        addCancelButtonComponent(componentList, componentConfig);
        addSaveButtonComponent(componentList, componentConfig);
        addUpdateButtonComponent(componentList, componentConfig);

    }

    private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig saveBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnSaveViewButtonlayout",
                true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SAVE_VIEW_BUTTONLAYOUT);
        componentList.add(saveBtnLayout);

        GtnUIFrameworkComponentConfig saveButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnSaveView01",
                true, saveBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        saveButtonConfig.setComponentName("SAVE");
        saveButtonConfig.setAuthorizationIncluded(true);
        componentList.add(saveButtonConfig);
        List<GtnUIFrameWorkActionConfig> saveViewActionConfig = new ArrayList<>();
        GtnUIFrameWorkActionConfig saveView = new GtnUIFrameWorkActionConfig();
        saveView.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        saveView.addActionParameter(GtnFrameworkAdjustmentDetailsSaveViewAction.class.getName());
        saveView.addActionParameter(GtnFrameworkCommonConstants.SAVE_VIEW_VIEW_NAME);
        saveView.addActionParameter(GtnFrameworkCommonConstants.SAVE_VIEW_VIEW_TYPE);
        saveView.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_TYPE);
        saveView.addActionParameter(GtnFrameworkCommonConstants.GL_COMPANY);
        saveView.addActionParameter(GtnFrameworkCommonConstants.WORKFLOW_ID);

        saveView.addActionParameter(GtnFrameworkCommonConstants.TRANSACTION_LEVEL);
        saveView.addActionParameter(GtnFrameworkCommonConstants.BUSINESS_UNIT);
        saveView.addActionParameter(GtnFrameworkCommonConstants.WORKFLOW_NAME);

        saveView.addActionParameter(GtnFrameworkCommonConstants.CUSTOMER_NO);
        saveView.addActionParameter(GtnFrameworkCommonConstants.ITEM_NO);
        saveView.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_LEVEL);
        saveView.addActionParameter(GtnFrameworkCommonConstants.CREATED_DATE);

        saveView.addActionParameter(GtnFrameworkCommonConstants.CUSTOMER_NAME);
        saveView.addActionParameter(GtnFrameworkCommonConstants.ITEM_NAME);
        saveView.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_VALUE);
        saveView.addActionParameter(GtnFrameworkCommonConstants.GL_DATE);

        saveView.addActionParameter(GtnFrameworkCommonConstants.ORIGINAL_BATCH_ID);
        saveView.addActionParameter(GtnFrameworkCommonConstants.BRAND_NAME);
        saveView.addActionParameter(GtnFrameworkCommonConstants.FROM_DATE);
        saveView.addActionParameter(GtnFrameworkCommonConstants.TO_DATE);
        saveView.addActionParameter(GtnFrameworkCommonConstants.POSTING_INDICATOR);

        saveView.addActionParameter(GtnFrameworkCommonConstants.ACCOUNT_CATEGORY);
        saveView.addActionParameter(GtnFrameworkCommonConstants.ACCOUNT_TYPE);
        saveView.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_LEVEL);
        saveView.addActionParameter(GtnFrameworkCommonConstants.ACCOUNT);
        saveViewActionConfig.add(saveView);
        GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
        closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
        closeAction.addActionParameter("V002");
        saveViewActionConfig.add(closeAction);
        saveButtonConfig.setGtnUIFrameWorkActionConfigList(saveViewActionConfig);

    }

    private void addCancelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig cancelBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnCancelButtonlayout",
                true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SAVE_VIEW_BUTTONLAYOUT);
        componentList.add(cancelBtnLayout);

        GtnUIFrameworkComponentConfig cancelButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnCancelButton",
                true, cancelBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        cancelButtonConfig.setComponentName("CANCEL");
        cancelButtonConfig.setAuthorizationIncluded(true);
        componentList.add(cancelButtonConfig);
        List<GtnUIFrameWorkActionConfig> cancelActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
        closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
        closeAction.addActionParameter("V002");
        cancelActionConfigList.add(closeAction);
        cancelButtonConfig.setGtnUIFrameWorkActionConfigList(cancelActionConfigList);
    }

    private void addUpdateButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig updateBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnUpdateButtonlayout",
                true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_SAVE_VIEW_BUTTONLAYOUT);
        componentList.add(updateBtnLayout);

        GtnUIFrameworkComponentConfig updateButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnUpdateButton",
                true, updateBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        updateButtonConfig.setComponentName("UPDATE");
        updateButtonConfig.setAuthorizationIncluded(true);
        componentList.add(updateButtonConfig);
        List<GtnUIFrameWorkActionConfig> updateActionConfig = new ArrayList<>();
        GtnUIFrameWorkActionConfig updateView = new GtnUIFrameWorkActionConfig();
        updateView.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        updateView.addActionParameter(GtnFrameworkAdjustmentDetailsSaveViewAction.class.getName());
        updateView.addActionParameter(GtnFrameworkCommonConstants.SAVE_VIEW_VIEW_NAME);
        updateView.addActionParameter(GtnFrameworkCommonConstants.SAVE_VIEW_VIEW_TYPE);
        updateView.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_TYPE);
        updateView.addActionParameter(GtnFrameworkCommonConstants.GL_COMPANY);
        updateView.addActionParameter(GtnFrameworkCommonConstants.WORKFLOW_ID);

        updateView.addActionParameter(GtnFrameworkCommonConstants.TRANSACTION_LEVEL);
        updateView.addActionParameter(GtnFrameworkCommonConstants.BUSINESS_UNIT);
        updateView.addActionParameter(GtnFrameworkCommonConstants.WORKFLOW_NAME);

        updateView.addActionParameter(GtnFrameworkCommonConstants.CUSTOMER_NO);
        updateView.addActionParameter(GtnFrameworkCommonConstants.ITEM_NO);
        updateView.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_LEVEL);
        updateView.addActionParameter(GtnFrameworkCommonConstants.CREATED_DATE);

        updateView.addActionParameter(GtnFrameworkCommonConstants.CUSTOMER_NAME);
        updateView.addActionParameter(GtnFrameworkCommonConstants.ITEM_NAME);
        updateView.addActionParameter(GtnFrameworkCommonConstants.DEDUCTION_VALUE);
        updateView.addActionParameter(GtnFrameworkCommonConstants.GL_DATE);

        updateView.addActionParameter(GtnFrameworkCommonConstants.ORIGINAL_BATCH_ID);
        updateView.addActionParameter(GtnFrameworkCommonConstants.BRAND_NAME);
        updateView.addActionParameter(GtnFrameworkCommonConstants.FROM_DATE);
        updateView.addActionParameter(GtnFrameworkCommonConstants.TO_DATE);
        updateView.addActionParameter(GtnFrameworkCommonConstants.POSTING_INDICATOR);

        updateView.addActionParameter(GtnFrameworkCommonConstants.ACCOUNT_CATEGORY);
        updateView.addActionParameter(GtnFrameworkCommonConstants.ACCOUNT_TYPE);
        updateView.addActionParameter(GtnFrameworkCommonConstants.ADJUSTMENT_LEVEL);
        updateView.addActionParameter(GtnFrameworkCommonConstants.ACCOUNT);
        updateActionConfig.add(updateView);
        GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
        closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
        closeAction.addActionParameter("V002");
        updateActionConfig.add(closeAction);
        updateButtonConfig.setGtnUIFrameWorkActionConfigList(updateActionConfig);
    }
}
