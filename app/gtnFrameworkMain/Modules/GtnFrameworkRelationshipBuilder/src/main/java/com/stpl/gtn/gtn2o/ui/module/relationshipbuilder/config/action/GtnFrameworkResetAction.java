/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkResetAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		Object value = GtnUIFrameworkGlobalUI.getSessionProperty(parameters.get(4).toString());
		if (value == null) {
			GtnFrameworkValueChangeManager.setValueChangeAllowed(false);
			String namespaceprefix = parameters.get(7).toString();
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + String.valueOf(parameters.get(3)))
					.loadDefaultCombobox(0, 0);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + String.valueOf(parameters.get(10)))
					.setPropertyValue(null);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + String.valueOf(parameters.get(11)))
					.setPropertyValue("Primary");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + String.valueOf(parameters.get(12)))
					.setPropertyValue("Manual");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + String.valueOf(parameters.get(13)))
					.setPropertyValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + String.valueOf(parameters.get(14)))
					.setPropertyValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + String.valueOf(parameters.get(15)))
					.setPropertyValue(null);
			List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
			GtnUIFrameworkGlobalUI.addChildComponent(namespaceprefix + String.valueOf(parameters.get(8)), componentList);
			GtnFrameworkValueChangeManager.setValueChangeAllowed(true);
			return;
		}
		GtnWsRecordBean relationshipBean = (GtnWsRecordBean) value;
		int index = (Integer) parameters.get(2);
		int versionNo = relationshipBean.getIntegerPropertyByIndex(index + 3);
		GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(3).toString(), versionNo);
		GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(4).toString(), relationshipBean);
		GtnUIFrameWorkActionConfig logicActionConfig = new GtnUIFrameWorkActionConfig();
		List<Object> actionList = new ArrayList<>(parameters);
		actionList.set(0, GtnUIFrameworkViewButtonAction.class.getName());
		logicActionConfig.setActionParameterList(actionList);
		GtnUIFrameworkActionExecutor.executeCustomAction(componentId, logicActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}
}
