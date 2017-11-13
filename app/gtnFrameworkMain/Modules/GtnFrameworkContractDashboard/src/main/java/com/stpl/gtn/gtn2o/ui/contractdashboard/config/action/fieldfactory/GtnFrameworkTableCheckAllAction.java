/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.GtnFrameworkSessionManagerAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.fieldfactory.GtnUIFrameworkActionParameter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkTableCheckAllAction implements GtnUIFrameWorkAction,GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent baseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId);
		GtnUIFrameworkComponentData componentData = baseComponent.getComponentData();
		GtnUIFrameworkActionParameter actionParameter = componentData.getActionParameter();
		if (actionParameter != null) {
			Object value = "true".equalsIgnoreCase(String.valueOf(actionParameter.getCurrentValue())) ? "1" : "0";
			List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnUIFrameWorkActionConfig updateActionConfig = new GtnUIFrameWorkActionConfig();
			updateActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			updateActionConfig.addActionParameter(GtnFrameworkFieldFactoryValueUpdateAction.class.getName());
			updateActionConfig.addActionParameter(parameters.get(1));
			updateActionConfig.addActionParameter(0);
			updateActionConfig.addActionParameter(actionParameter.getPropertyId());
			updateActionConfig.addActionParameter(value);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, updateActionConfig);
			refreshTable(baseComponent, actionParameter.getPropertyId(), actionParameter.getCurrentValue());
		}
	}

	private void refreshTable(GtnUIFrameworkBaseComponent baseComponent, String propertyId, Object value)
			throws GtnFrameworkValidationFailedException {
		GtnFrameworkSessionManagerAction.getDashboardSessionBean(baseComponent.getComponentId())
				.setNeedOperation(false);
		List<GtnWsRecordBean> tableItemIds = baseComponent.getItemsFromDataTable();
		for (GtnWsRecordBean tableItemId : tableItemIds) {
			tableItemId.addProperties(propertyId, value);
			baseComponent.setTableColumnValue(tableItemId, propertyId, value);
		}
		baseComponent.setTableRefresh(true);
		GtnFrameworkSessionManagerAction.getDashboardSessionBean(baseComponent.getComponentId()).setNeedOperation(true);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
