/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.setter.GtnUIFrameworkSetter;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkRemoveFromTreeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkBaseComponent cdTreeBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		GtnUIFrameworkSetter setter = new GtnUIFrameworkSetter();
		if (cdTreeBaseComponent.getItemsFromDataTable().isEmpty()) {
			setter.showMessageBox(componentId, GtnUIFrameworkActionType.WARNING_ACTION, parameters.get(2).toString(),
					parameters.get(3).toString());
			return;
		}
		Object selectedvalue = cdTreeBaseComponent.getValueFromComponent();
		if (selectedvalue == null) {
			setter.showMessageBox(componentId, GtnUIFrameworkActionType.WARNING_ACTION, parameters.get(4).toString(),
					parameters.get(5).toString());
			return;
		}
		if (cdTreeBaseComponent.hasChildrenOfTreeItem(selectedvalue)) {
			GtnWsRecordBean bean = (GtnWsRecordBean) selectedvalue;
			setter.showMessageBox(componentId, GtnUIFrameworkActionType.WARNING_ACTION, parameters.get(2).toString(),
					parameters.get(6).toString() + (bean.getStringPropertyByIndex(0)));
			return;
		}
		confirmRemoveFromTree(componentId, parameters);
	}

	private void confirmRemoveFromTree(String componentId, List<Object> parameters)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkActionConfig removeFromTreeConfirmActionConfig = new GtnUIFrameWorkActionConfig();
		removeFromTreeConfirmActionConfig.setActionType(GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		removeFromTreeConfirmActionConfig.addActionParameter(parameters.get(7));
		removeFromTreeConfirmActionConfig.addActionParameter(parameters.get(8));
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig removeActionConfig = new GtnUIFrameWorkActionConfig();
		removeActionConfig.setActionType(GtnUIFrameworkActionType.REMOVE_ACTION);
		removeActionConfig.addActionParameter(parameters.get(1));
		successActionConfigList.add(removeActionConfig);
		removeFromTreeConfirmActionConfig.addActionParameter(successActionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, removeFromTreeConfirmActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
