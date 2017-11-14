package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.constants.GtnFrameworkContractDashboardContants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkCompanyItemAdditionSearchAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

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
		String fieldId;
		if (GtnFrameworkContractDashboardContants.COMPANY_ADDITION.equals(mainParameters.get(2).toString())) {
			fieldId = mainParameters.get(3).toString();
		} else {
			String componentValue1 = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(mainParameters.get(2).toString())
					.getStringFromField();
			fieldId = mainParameters.get(3).toString() + componentValue1;
		}
		formulateAndExecuteAction(componentId, mainParameters, parameters, fieldId);
	}

	private void formulateAndExecuteAction(String componentId, List<Object> mainParameters, List<String> parameters,
			String fieldId) throws GtnFrameworkGeneralException {
		String componentValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(fieldId).getStringFromField();
		if (componentValue.isEmpty()) {
			componentValue = mainParameters.get(4).toString();
		}

		String valueId = "";
		componentValue = componentValue.replace(" ", "");
		for (int i = 0; i < parameters.size(); i++) {
			String parameter = parameters.get(i);
			if (parameter.endsWith(componentValue)) {
				valueId = parameter;
				break;
			}
		}
		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(Arrays.asList(fieldId, valueId));

		GtnUIFrameWorkActionConfig failActionConfig = new GtnUIFrameWorkActionConfig();
		failActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		failActionConfig.addActionParameter(GtnFrameworkErrorLabelAction.class.getName());
		failActionConfig.addActionParameter(mainParameters.get(5));
		List<String> fieldIds = new ArrayList<>(parameters);
		fieldIds.add(0, fieldId);
		failActionConfig.addActionParameter(fieldIds);
		failActionConfig.addActionParameter(mainParameters.get(6));

		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(mainParameters.get(7));
		loadDataTableActionConfig.setFieldValues(Arrays.asList(fieldId, valueId));

		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.AND);
		validationActionConfig.addActionParameter(Arrays.asList(failActionConfig));
		validationActionConfig.addActionParameter(Arrays.asList(failActionConfig, loadDataTableActionConfig));
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, validationActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
