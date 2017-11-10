package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkComponentValueChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		List<String> inputParameters = (List<String>) actionParameters.get(1);
		String componentValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(inputParameters.get(3))
				.getStringFromField();
		String searchType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(inputParameters.get(4)).getStringFromField();
		String compare = GtnFrameworkCommonStringConstants.UNDERSCORE
				+ GtnWsContractDashboardContants.getComponentMappedValue(inputParameters.get(0))
				+ GtnWsContractDashboardContants.getComponentMappedValue(componentValue)
				+ GtnWsContractDashboardContants.getComponentMappedValue(searchType)
				+ GtnFrameworkCommonStringConstants.UNDERSCORE;
		for (int pameterindex = 5; pameterindex < inputParameters.size(); pameterindex++) {
			String compId = inputParameters.get(pameterindex);
			if (compId.contains("Table") || compId.contains("hiddenID")) {
				continue;
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(compId + inputParameters.get(2))
					.setComponentVisible(compId.contains(compare));
		}
		if (componentId.equals(actionParameters.get(2))) {
			GtnUIFrameWorkActionConfig resetFieldActionConfig = new GtnUIFrameWorkActionConfig();
			resetFieldActionConfig.addActionParameter(GtnUIFrameworkComponentResetAction.class.getName());
			resetFieldActionConfig.addActionParameter(inputParameters);
			resetFieldActionConfig.addActionParameter(actionParameters.get(3));
			resetFieldActionConfig.addActionParameter(actionParameters.get(4));
			GtnUIFrameworkActionExecutor.executeCustomAction(componentId, resetFieldActionConfig);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
