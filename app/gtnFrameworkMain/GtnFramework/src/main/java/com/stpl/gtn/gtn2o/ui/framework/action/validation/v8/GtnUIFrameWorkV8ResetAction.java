/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.framework.action.validation.v8;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gokulkumar.murugesan
 */
public class GtnUIFrameWorkV8ResetAction implements GtnUIFrameWorkAction {

	@Override
	public void doAction(String v8ComponentId, GtnUIFrameWorkActionConfig v8GtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> v8Params = (v8GtnUIFrameWorkActionConfig.getActionParameterList());
		String v8ResetMessageHeader = (String) v8Params.get(0);
		String v8ResetMessageBody = (String) v8Params.get(1);
		configureParams(v8GtnUIFrameWorkActionConfig);
		GtnUIFrameWorkActionConfig v8ConfirmActionConfig = new GtnUIFrameWorkActionConfig();
		v8ConfirmActionConfig.addActionParameter(v8ResetMessageHeader);
		v8ConfirmActionConfig.addActionParameter(v8ResetMessageBody);
		v8ConfirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		List<GtnUIFrameWorkActionConfig> v8SuccessActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig v8ResetActionConfig = new GtnUIFrameWorkActionConfig();
		v8ResetActionConfig.setActionType(GtnUIFrameworkActionType.V8_CONFIRMED_RESET_ACTION);
		v8ResetActionConfig.addActionParameter(v8Params.get(2));
		if (v8Params.size() > 3) {
			v8ResetActionConfig.addActionParameter(v8Params.get(3));
		}
		v8SuccessActionConfigList.add(v8ResetActionConfig);
		v8ConfirmActionConfig.addActionParameter(v8SuccessActionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(v8ComponentId, v8ConfirmActionConfig);

	}

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig v8GtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
