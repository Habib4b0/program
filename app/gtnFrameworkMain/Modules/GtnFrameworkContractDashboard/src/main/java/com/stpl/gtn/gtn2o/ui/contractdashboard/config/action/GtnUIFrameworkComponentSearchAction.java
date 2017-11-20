package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkValidationType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkComponentSearchAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

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
		String searchType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4)).getStringFromField();
		String compare = GtnFrameworkCommonStringConstants.UNDERSCORE
				+ GtnWsContractDashboardContants.getComponentMappedValue(parameters.get(0))
				+ GtnWsContractDashboardContants.getComponentMappedValue(componentValue)
				+ GtnWsContractDashboardContants.getComponentMappedValue(searchType)
				+ GtnFrameworkCommonStringConstants.UNDERSCORE;
		String tableId = "";
		List<String> fieldIds = new ArrayList<>();
		for (int pameterindex = 5; pameterindex < parameters.size(); pameterindex++) {
			String compId = parameters.get(pameterindex);
			if (compId.contains("Table")) {
				tableId = compId;
				break;
			}
			if (compId.contains(compare) && !compId.endsWith("SupID")) {
				fieldIds.add(compId);
			}
		}
		GtnUIFrameWorkActionConfig validationActionConfig = new GtnUIFrameWorkActionConfig();
		validationActionConfig.setActionType(GtnUIFrameworkActionType.VALIDATION_ACTION);
		validationActionConfig.setFieldValues(fieldIds);

		GtnUIFrameWorkActionConfig failActionConfig = new GtnUIFrameWorkActionConfig();
		failActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
		failActionConfig.addActionParameter(GtnWsContractDashboardContants.SEARCH_ERROR);
		failActionConfig.addActionParameter(GtnWsContractDashboardContants.SEARCH_MESSAGE);

		List<String> newfieldIds = new ArrayList<>(fieldIds);
		newfieldIds.add(0, parameters.get(3));
		String namespacePrefix = tableId.split("_")[0];
		// namspacePrefix "ComponentDetailsTable"

		List<GtnUIFrameWorkActionConfig> successActionList = new ArrayList<>();
		GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
		loadDataTableActionConfig.setActionType(componentId.startsWith(parameters.get(0))
				? GtnUIFrameworkActionType.LOAD_TREE_TABLE_ACTION : GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(tableId);
		loadDataTableActionConfig.setFieldValues(newfieldIds);

		validationActionConfig.addActionParameter(GtnUIFrameworkValidationType.OR);
		validationActionConfig.addActionParameter(Arrays.asList(failActionConfig));

		GtnUIFrameWorkActionConfig searchNoticationAction = new GtnUIFrameWorkActionConfig();
		searchNoticationAction.setActionType(GtnUIFrameworkActionType.SEARCH_COMPLETED_NOTIFICATION_ACTION);
		searchNoticationAction.addActionParameter(tableId);

		loadDataTableActionConfig.setActionType(componentId.startsWith(parameters.get(0))
				? GtnUIFrameworkActionType.LOAD_TREE_TABLE_ACTION : GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
		loadDataTableActionConfig.addActionParameter(tableId);
		loadDataTableActionConfig.setFieldValues(newfieldIds);

		successActionList.add(loadDataTableActionConfig);
		successActionList.add(searchNoticationAction);

		if ("CDMainView".equals(namespacePrefix)) {
			GtnUIFrameworkBaseComponent hiddenComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(namespacePrefix + "_hiddenID", componentId);
			if (hiddenComponent != null) {
				hiddenComponent.setPropertyValue(null);
			}

			GtnUIFrameWorkActionConfig reLoadComponentDataTableActionConfig = new GtnUIFrameWorkActionConfig();
			reLoadComponentDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
			reLoadComponentDataTableActionConfig.addActionParameter(namespacePrefix + "_ComponentDetailsTable");
			successActionList.add(reLoadComponentDataTableActionConfig);
		}

		validationActionConfig.addActionParameter(successActionList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, validationActionConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
