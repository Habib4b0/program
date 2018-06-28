/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.customview.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;


public class GtnFrameworkAddAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
   private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnFrameworkAddAction.class);
    @Override
    public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
      // No Need to Implement. Its an unused method.
    }

    @Override
    public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig) throws GtnFrameworkGeneralException {
        try
        {
        GtnUIFrameworkGlobalUI.addSessionProperty("mode", "Add");
        GtnUIFrameworkGlobalUI.addSessionProperty("customSid", 0);
        GtnUIFrameworkGlobalUI.addSessionProperty("customViewBean", "");
        }
        catch(Exception e)
        {
          LOGGER.error("message",e);
        }
    }

    @Override
    public GtnUIFrameWorkAction createInstance() {
        return this;
    }
    
}
