/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.config.popups.redemptionperiod;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.action.GtnFrameworkFieldResetAction;
import com.stpl.gtn.gtn2o.ui.config.abstractConfig.GTNFrameworkAbstractComponent;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
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
public class GtnFrameworkRedemptionPeriodLookup extends GTNFrameworkAbstractComponent {

    public GtnUIFrameworkViewConfig getRedemptionPeriod() {
        GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
        GtnUIFrameworkViewConfig redemptionPeriodLookupConfig = componentConfig.getViewConfig("redemptionperiodLookup", "V004", false);
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        redemptionPeriodLookupConfig.setGtnComponentList(componentList);
        addComponentList(redemptionPeriodLookupConfig, componentConfig);
        return redemptionPeriodLookupConfig;
    }

    private void addComponentList(GtnUIFrameworkViewConfig searchView, GtnFrameworkComponentConfigProvider componentConfig) {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        searchView.setGtnComponentList(componentList);
        addFieldLayout(componentList, componentConfig);
    }

    private void addFieldLayout(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig itemMasterSearchLayout = componentConfig.getGtnCssLayoutConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REDEMPTION_PERIOD_LAYOUT, false, null,
                GtnUIFrameworkLayoutType.VERTICAL_LAYOUT);
        itemMasterSearchLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterSearchLayout.setComponentHight(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterSearchLayout.setMargin(true);
        itemMasterSearchLayout.setSpacing(true);
        componentList.add(itemMasterSearchLayout);
        addComponentLayout(componentList, componentConfig);
        addButtonLayout(componentList, componentConfig);
    }

    private void addComponentLayout(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig itemMasterSearchLayout1 = componentConfig.getGtnCssLayoutConfig(
                GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REDEMPTION_PERIOD_LAYOUT1, true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REDEMPTION_PERIOD_LAYOUT,
                GtnUIFrameworkLayoutType.HORIZONTAL_LAYOUT);
        itemMasterSearchLayout1.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterSearchLayout1.setComponentHight(GtnFrameworkCssConstants.PERCENT_100);
        componentList.add(itemMasterSearchLayout1);
        addFieldComponent(componentList, componentConfig);
    }

    private void addFieldComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        addRedemptionPeriodFromDate(componentList, componentConfig);
        addRedemptionPeriodToDate(componentList, componentConfig);
    }

    private void addRedemptionPeriodFromDate(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "From Date";
        String objectComponentName = GtnFrameworkCommonConstants.FROM_DATE;
        getInlinePopupFieldComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REDEMPTION_PERIOD_LAYOUT1);
    }

    private void addRedemptionPeriodToDate(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        String componentName = "To Date";
        String objectComponentName = GtnFrameworkCommonConstants.TO_DATE;
        getInlinePopupFieldComponent(componentConfig, objectComponentName, componentList, componentName, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REDEMPTION_PERIOD_LAYOUT1);
    }

    private void addButtonLayout(List<GtnUIFrameworkComponentConfig> componentList,
            GtnFrameworkComponentConfigProvider componentConfig) {

        GtnUIFrameworkComponentConfig itemMasterButtonLayout = componentConfig
                .getCssLayoutConfig(GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REDEMPTION_PERIOD_BUTTONLAYOUT, true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REDEMPTION_PERIOD_LAYOUT);
        itemMasterButtonLayout.setComponentWidth(GtnFrameworkCssConstants.PERCENT_100);
        itemMasterButtonLayout.addComponentStyle(GtnFrameworkCssConstants.GTN_FRAMEWORK_COL_3);
        componentList.add(itemMasterButtonLayout);

        addCancelButtonComponent(componentList, componentConfig);
        addSaveButtonComponent(componentList, componentConfig);
        addUpdateButtonComponent(componentList, componentConfig);

    }

    private void addSaveButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig saveBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnSaveViewButtonlayout",
                true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REDEMPTION_PERIOD_BUTTONLAYOUT);
        componentList.add(saveBtnLayout);

        GtnUIFrameworkComponentConfig saveButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnSaveView01",
                true, saveBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        saveButtonConfig.setComponentName("SUBMIT");
        saveButtonConfig.setAuthorizationIncluded(true);
        componentList.add(saveButtonConfig);
        List<GtnUIFrameWorkActionConfig> selectActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig selectAction = new GtnUIFrameWorkActionConfig();
        selectAction.setActionType(GtnUIFrameworkActionType.POPUP_INLINE_SELECT_ACTION);
        selectAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.FROM_DATE, GtnFrameworkCommonConstants.TO_DATE));
        selectAction.addActionParameter(Arrays.asList(GtnFrameworkCommonConstants.FROM_DATE, GtnFrameworkCommonConstants.TO_DATE));
        selectAction.addActionParameter(GtnFrameworkCommonConstants.REDEMPTION_PERIOD);

        GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
        closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
        closeAction.addActionParameter("V004");

        selectActionConfigList.add(selectAction);

        selectActionConfigList.add(closeAction);
        saveButtonConfig.setGtnUIFrameWorkActionConfigList(selectActionConfigList);

    }

    private void addCancelButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig cancelBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnCancelButtonlayout",
                true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REDEMPTION_PERIOD_BUTTONLAYOUT);
        componentList.add(cancelBtnLayout);

        GtnUIFrameworkComponentConfig cancelButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnCancelButton",
                true, cancelBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        cancelButtonConfig.setComponentName("CLOSE");
        cancelButtonConfig.setAuthorizationIncluded(true);
        componentList.add(cancelButtonConfig);
        List<GtnUIFrameWorkActionConfig> canecelActionConfigList = new ArrayList<>();
        GtnUIFrameWorkActionConfig resetAction = new GtnUIFrameWorkActionConfig();
        resetAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
        resetAction.addActionParameter(GtnFrameworkFieldResetAction.class.getName());
        resetAction.addActionParameter(GtnFrameworkCommonConstants.REDEMPTION_PERIOD);
        canecelActionConfigList.add(resetAction);
        GtnUIFrameWorkActionConfig closeAction = new GtnUIFrameWorkActionConfig();
        closeAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
        closeAction.addActionParameter("V004");
        canecelActionConfigList.add(closeAction);
        cancelButtonConfig.setGtnUIFrameWorkActionConfigList(canecelActionConfigList);
    }

    private void addUpdateButtonComponent(List<GtnUIFrameworkComponentConfig> componentList, GtnFrameworkComponentConfigProvider componentConfig) {
        GtnUIFrameworkComponentConfig updateBtnLayout = componentConfig.getHorizontalLayoutConfig("gtnUpdateButtonlayout",
                true, GtnFrameworkCommonConstants.ADJUSTMENT_DETAILS_REDEMPTION_PERIOD_BUTTONLAYOUT);
        componentList.add(updateBtnLayout);

        GtnUIFrameworkComponentConfig updateButtonConfig = componentConfig.getUIFrameworkComponentConfig("gtnUpdateButton",
                true, updateBtnLayout.getComponentId(), GtnUIFrameworkComponentType.BUTTON);
        updateButtonConfig.setComponentName("RESET");
        updateButtonConfig.setAuthorizationIncluded(true);
        componentList.add(updateButtonConfig);
        List<GtnUIFrameWorkActionConfig> updateActionConfig = new ArrayList<>();
        updateButtonConfig.setGtnUIFrameWorkActionConfigList(updateActionConfig);
    }
}
