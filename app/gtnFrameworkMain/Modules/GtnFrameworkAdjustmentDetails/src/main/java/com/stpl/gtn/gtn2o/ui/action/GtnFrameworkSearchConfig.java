/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import java.util.List;

/**
 *
 * @author Sathya.Seelan
 */
public class GtnFrameworkSearchConfig implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

    private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkDeductionLevelValueChange.class);

    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        // No Need to Implement. Its an unused method.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
        Object obj = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString()).getValueFromComponent();
        gtnLogger.info(obj.toString());
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }

}
