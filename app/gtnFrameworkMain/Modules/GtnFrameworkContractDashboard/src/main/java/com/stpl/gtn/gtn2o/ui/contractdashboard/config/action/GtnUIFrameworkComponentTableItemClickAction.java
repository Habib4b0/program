package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnUIFrameworkComponentTableItemClickAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

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
		if (!"1".equals(GtnWsContractDashboardContants.getComponentMappedValue(componentValue))) {
			GtnWsRecordBean bean = (GtnWsRecordBean) mainParameters.get(mainParameters.size() - 1);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(49))
					.setPropertyValue(bean.getStringPropertyByIndex(4));
			GtnUIFrameWorkActionConfig loadDataTableActionConfig = new GtnUIFrameWorkActionConfig();
			loadDataTableActionConfig.setActionType(GtnUIFrameworkActionType.LOAD_DATA_TABLE_ACTION);
			loadDataTableActionConfig.addActionParameter(parameters.get(48));
			loadDataTableActionConfig.setFieldValues(Arrays.asList(parameters.get(3), parameters.get(49)));
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, loadDataTableActionConfig);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
