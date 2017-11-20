/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Yuvaraj.Rajangam
 */
public class GtnFrameworkItemMasterItemTypeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private GtnWSLogger gtnLogger = GtnWSLogger
            .getGTNLogger(GtnFrameworkItemMasterItemTypeAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        gtnLogger.debug("inside GtnFrameworkItemMasterItemTypeAction");
    }

    @Override
    public void doAction(String sourceComponentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        String itemType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabItemType").getCaptionFromComboBox();
        boolean isNDC9 = "NDC-9".equals(itemType);
        boolean isNDC11 = "NDC-11".equals(itemType);
        if (!isNDC11) {
            GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabNDC9").loadFieldValue(0);
        } 

        GtnUIFrameworkGlobalUI.getVaadinBaseComponent("ifpItemInformationTabValueDropDown").setVisible(isNDC11);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabNDC9Droplayout").setVisible(isNDC11);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabNDC9layout").setVisible(!isNDC11);
        GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabNDC9layout").setEnable(false);

        GtnUIFrameworkBaseComponent itemTypeCheck = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabNewFormulationIndicator", sourceComponentId);
        if (!isNDC9)  {
            itemTypeCheck.selectOptionGroupValue("No");
        }
        itemTypeCheck.selectOptionGroupValue("Yes", isNDC9);
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
