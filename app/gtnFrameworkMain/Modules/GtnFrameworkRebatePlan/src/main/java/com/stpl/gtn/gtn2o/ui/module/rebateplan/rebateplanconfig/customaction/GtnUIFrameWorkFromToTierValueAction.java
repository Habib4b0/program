/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.rebateplan.rebateplanconfig.customaction;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Kalpana.Ramanana
 */
public class GtnUIFrameWorkFromToTierValueAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		String data = String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1));
		int size = (int) gtnUIFrameWorkActionConfig.getActionParameterList().get(2);
		List<String> components = (List<String>) gtnUIFrameWorkActionConfig.getActionParameterList().get(3);

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(components.get(0))
				.loadFieldValue((!"".equals(data) ? Double.valueOf(data) : ""));

		if (size == 1) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(components.get(0)).setEnable(true);

		}
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(components.get(1)).setEnable(true);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
