package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkUICustomVariablePositionChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String variableTypeId = (String) parameterList.get(1);
		String variableTypeGridId = (String) parameterList.get(2);
		String selectedType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getV8StringFromField();
		boolean isNeedToBeEnabled = selectedType.equals("Rows");

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(variableTypeId, componentId)
				.setComponentEnable(isNeedToBeEnabled);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(variableTypeGridId, componentId)
				.setComponentEnable(isNeedToBeEnabled);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
