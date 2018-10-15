/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.List;

public class ForecastEligibilityDateValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<GtnUIFrameWorkActionConfig> levelactionConfigList = (List<GtnUIFrameWorkActionConfig>) gtnUIFrameWorkActionConfig
				.getActionParameterList().get(1);
		GtnUIFrameWorkActionConfig actionConfig = (GtnUIFrameWorkActionConfig) levelactionConfigList.get(0)
				.getActionParameterList().get(3);

		String hierarchyComponentId = (String) actionConfig.getActionParameterList().get(1);
		GtnUIFrameworkComponentData gtnUIFrameworkComponentData = null;

		if (GtnUIFrameworkGlobalUI.getVaadinBaseComponent(hierarchyComponentId).getComponent() != null) {
			gtnUIFrameworkComponentData = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(hierarchyComponentId, componentId).getComponentData();
		}

		if (gtnUIFrameworkComponentData != null && gtnUIFrameworkComponentData.getCustomData() != null) {

			GtnUIFrameworkActionExecutor.executeActionList(componentId, levelactionConfigList);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
