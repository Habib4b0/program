/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Asha.Ravi
 */
public class GtnUIFrameworkTransactionAlertAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<String> header = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(1);
		List<String> messageBody = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
		GtnUIFrameworkSetter frameworkSetter = new GtnUIFrameworkSetter();
		if ("inventoryLevel".equals(componentId) || "AdjDemandForecastTypeSid".equals(componentId)) {
			frameworkSetter.showMessageBox(componentId, GtnUIFrameworkActionType.WARNING_ACTION, header.get(1),
					messageBody.get(1));
		} else {
			frameworkSetter.showMessageBox(componentId, GtnUIFrameworkActionType.WARNING_ACTION, header.get(0),
					messageBody.get(0));
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
