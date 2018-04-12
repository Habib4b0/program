/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author mekalai.madhappa
 */
public class GtnUIFrameWorkDefaultResetValueAction implements GtnUIFrameWorkAction,GtnUIFrameworkDynamicClass {
    @Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            List<Object> resetActionParams = (gtnUIFrameWorkActionConfig.getActionParameterList());
		String resetMsgHeader = (String) resetActionParams.get(1);
		String resetMsgBody = (String) resetActionParams.get(2);

		configureParams(gtnUIFrameWorkActionConfig);
		GtnUIFrameWorkActionConfig resetConfirmActionConfig = new GtnUIFrameWorkActionConfig();
		resetConfirmActionConfig.addActionParameter(resetMsgHeader);
		resetConfirmActionConfig.addActionParameter(resetMsgBody);
		resetConfirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<GtnUIFrameWorkActionConfig> successActionConList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
		resetActionConfig.addActionParameter(resetActionParams.get(3));
		if (resetActionParams.size() > 4) {
			resetActionConfig.addActionParameter(resetActionParams.get(4));
		}
                
            GtnUIFrameWorkActionConfig resetForecastPeriodActionConfig = new GtnUIFrameWorkActionConfig();
                resetForecastPeriodActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
                resetForecastPeriodActionConfig.addActionParameter(GtnUIFrameWorkDefaultForecastResetValueAction.class.getName());
		successActionConList.add(resetActionConfig);
                successActionConList.add(resetForecastPeriodActionConfig);
		resetConfirmActionConfig.addActionParameter(successActionConList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetConfirmActionConfig);
                                
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
    
}
