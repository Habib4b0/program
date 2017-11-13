package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

public class GtnFrameworkAliasTabLoadAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

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
		if (!processDataBean.isAliasLoaded()) {
			processDataBean.setAliasLoaded(true);
			processDataBean.setAliasTableId(parameters.get(1).toString());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
					.setVisible(!processDataBean.isViewMode());
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString())
					.setVisible(!processDataBean.isViewMode());
			loadAliasTable(processDataBean);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void loadAliasTable(GtnWsContractDashboardSessionBean processDataBean) throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent tableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(processDataBean.getAliasTableId());
		tableBaseComponent.removeAllGridItems();
		tableBaseComponent.addItemsToGrid(processDataBean.getContractAliasRecordList());
	}
}
