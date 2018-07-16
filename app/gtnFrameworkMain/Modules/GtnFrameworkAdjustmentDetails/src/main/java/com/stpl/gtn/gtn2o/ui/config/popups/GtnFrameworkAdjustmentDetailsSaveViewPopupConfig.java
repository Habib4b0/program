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
        GtnUIFrameworkViewConfig privateView = componentConfig.getViewConfig("PrivateLookup", "V002", false);
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        privateView.setGtnComponentList(componentList);
        addComponentList(privateView, componentConfig);
        return privateView;
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
        saveViewActionConfig.add(saveView);
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
        List<GtnUIFrameWorkActionConfig> searchActionConfigList = new ArrayList<>();
        cancelButtonConfig.setGtnUIFrameWorkActionConfigList(searchActionConfigList);
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
        updateButtonConfig.setGtnUIFrameWorkActionConfigList(updateActionConfig);
    }
}
