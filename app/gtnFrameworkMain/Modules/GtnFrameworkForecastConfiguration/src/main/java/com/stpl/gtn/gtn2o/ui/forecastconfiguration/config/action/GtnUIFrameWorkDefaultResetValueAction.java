/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.forecastconfiguration.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
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
    private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameWorkDefaultResetValueAction.class);
    @Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            List<Object> params = (gtnUIFrameWorkActionConfig.getActionParameterList());
		String resetMessageHeader = (String) params.get(1);
		String resetMessageBody = (String) params.get(2);
                List<Object> valueid = (List<Object>) params.get(3);
//                gtnLogger.info("valueid----------------------"+valueid.get(10));

		configureParams(gtnUIFrameWorkActionConfig);
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.addActionParameter(resetMessageHeader);
		confirmActionConfig.addActionParameter(resetMessageBody);
		confirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
		resetActionConfig.addActionParameter(params.get(3));
		if (params.size() > 4) {
			resetActionConfig.addActionParameter(params.get(4));
		}
                
//                if("FCView_forecastPeriod".equals(valueid.get(10))){
                    
//                                throw new GtnFrameworkValidationFailedException("IsRecordSelected  validation Failed");
//                }
            GtnUIFrameWorkActionConfig resetForecastPeriodActionConfig = new GtnUIFrameWorkActionConfig();
                resetForecastPeriodActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
                resetForecastPeriodActionConfig.addActionParameter(GtnUIFrameWorkDefaultForecastResetValueAction.class.getName());
		successActionConfigList.add(resetActionConfig);
                successActionConfigList.add(resetForecastPeriodActionConfig);
		confirmActionConfig.addActionParameter(successActionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);
                                
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
    
}
