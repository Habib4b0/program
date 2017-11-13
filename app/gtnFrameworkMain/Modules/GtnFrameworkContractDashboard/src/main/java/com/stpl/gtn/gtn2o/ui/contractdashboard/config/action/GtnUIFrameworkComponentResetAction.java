package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkComponentResetAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> mainParameters = gtnUIFrameWorkActionConfig.getActionParameterList();

		List<String> parameters = (List<String>) mainParameters.get(1);
		String componentValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3)).getStringFromField();
		String compare = GtnFrameworkCommonStringConstants.UNDERSCORE
				+ GtnWsContractDashboardContants.getComponentMappedValue(parameters.get(0))
				+ GtnWsContractDashboardContants.getComponentMappedValue(componentValue)
				+ GtnWsContractDashboardContants.getComponentMappedValue(mainParameters.get(2).toString())
				+ GtnFrameworkCommonStringConstants.UNDERSCORE;
		String compare1 = GtnFrameworkCommonStringConstants.UNDERSCORE
				+ GtnWsContractDashboardContants.getComponentMappedValue(parameters.get(0))
				+ GtnWsContractDashboardContants.getComponentMappedValue(componentValue)
				+ GtnWsContractDashboardContants.getComponentMappedValue(mainParameters.get(3).toString())
				+ GtnFrameworkCommonStringConstants.UNDERSCORE;
		List<String> fieldIds = new ArrayList<>();
		List<Object> resetValues = new ArrayList<>();
		for (int pameterindex = 5; pameterindex < parameters.size(); pameterindex++) {
			String compId = parameters.get(pameterindex);
			if (compId.contains("Table")) {
				fieldIds.add(compId);
				resetValues.add(null);
				break;
			}
			if ((compId.contains(compare) || compId.contains(compare1)) && !compId.endsWith("SupID")) {
				fieldIds.add(compId);
				resetValues.add(compId.contains(GtnWsContractDashboardContants.TEXT)
						? GtnFrameworkCommonStringConstants.STRING_EMPTY : null);
			}
		}
		if (!componentId.startsWith(parameters.get(0))) {
			fieldIds.add(parameters.get(48));
			resetValues.add(null);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(46)).setTableColumnHeaders(
					GtnFrameworkContractDashboardContants.getComponentHeaderMappedValue(componentValue));
			GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(48));
			tableBaseComponent.setTableColumns(
					GtnFrameworkContractDashboardContants.getComponentDetailsColumnMappedValue(componentValue));
			tableBaseComponent.setTableColumnHeaders(
					GtnFrameworkContractDashboardContants.getComponentDetailsHeaderMappedValue(componentValue));
		}
		GtnUIFrameWorkActionConfig resetActionConfig = new GtnUIFrameWorkActionConfig();
		resetActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMED_RESET_ACTION);
		resetActionConfig.addActionParameter(fieldIds);
		resetActionConfig.addActionParameter(resetValues);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
