package com.stpl.gtn.gtn2o.registry.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkForecastDateValueChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

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

}
