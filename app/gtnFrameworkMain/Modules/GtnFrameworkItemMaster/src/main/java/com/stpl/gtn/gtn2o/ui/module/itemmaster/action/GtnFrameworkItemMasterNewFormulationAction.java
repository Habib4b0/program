/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.itemmaster.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.module.itemmaster.action.validation.GtnFrameworkItemMasterEditListIndentifierValidationAction;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Yuvaraj.Rajangam
 */
public class GtnFrameworkItemMasterNewFormulationAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private GtnWSLogger gtnLogger = GtnWSLogger
            .getGTNLogger(GtnFrameworkItemMasterEditListIndentifierValidationAction.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {
        gtnLogger.debug("inside GtnFrameworkItemMasterNewFormulationAction");
    }

    @Override
    public void doAction(String sourceComponentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
            throws GtnFrameworkGeneralException {

        String value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("itemInformationTabNewFormulationIndicator")
                .getStringFromField();

        boolean isEnable = "Yes".equals(value);

        List<Object> actionParameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
        @SuppressWarnings("unchecked")
        List<String> componentIds = (List<String>) actionParameterList.get(1);
        for (String componentId : componentIds) {

            GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).setComponentEnable(isEnable);
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
