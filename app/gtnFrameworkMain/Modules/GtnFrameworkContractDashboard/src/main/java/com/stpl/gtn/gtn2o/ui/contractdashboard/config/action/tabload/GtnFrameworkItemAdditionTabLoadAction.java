package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnUIFrameworkWrapLoadTableAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkItemAdditionTabLoadAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsContractDashboardSessionBean processDataBean = GtnFrameworkSessionManagerAction
				.getDashboardSessionBean(componentId);
		if (!processDataBean.isItemAddLoaded()) {
			processDataBean.setItemAddLoaded(true);
			processDataBean.setItemAddTableLoad(true);
			processDataBean.setItemAdditionRightTableId(parameters.get(3).toString());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
					.setVisible(processDataBean.isViewMode());
		}
		if (processDataBean.isItemAddTableLoad()) {
			processDataBean.setItemAddTableLoad(false);
			loadTable(componentId, processDataBean.getItemAdditionRightTableId());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void loadTable(String componentId, Object tableId) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig tableLoadActionConfig = new GtnUIFrameWorkActionConfig();
		tableLoadActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		tableLoadActionConfig.addActionParameter(GtnUIFrameworkWrapLoadTableAction.class.getName());
		tableLoadActionConfig.addActionParameter(tableId);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, tableLoadActionConfig);
	}
}
