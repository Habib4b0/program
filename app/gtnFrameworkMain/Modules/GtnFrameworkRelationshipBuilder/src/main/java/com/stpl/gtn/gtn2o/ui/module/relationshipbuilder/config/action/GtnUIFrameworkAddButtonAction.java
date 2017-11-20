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
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkAddButtonAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkAddButtonAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {
			List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(1).toString(), null);
			GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(2).toString(), null);
			GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(3).toString(), null);
			GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(4).toString(), null);
			GtnFrameworkValueChangeManager.setValueChangeAllowed(false);
			GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
			navigationActionConfig.addActionParameter(parameters.get(15).toString());
			GtnUIFrameWorkAction navigationAction = GtnUIFrameworkActionType.NAVIGATION_ACTION
					.getGtnUIFrameWorkAction();
			navigationAction.configureParams(navigationActionConfig);
			navigationAction.doAction(componentId, navigationActionConfig);

			String namespaceprefix = parameters.get(5).toString();
			GtnUIFrameworkBaseComponent comp = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(namespaceprefix + parameters.get(1).toString());
			comp.loadDefaultCombobox(0, 0);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(7).toString())
					.setPropertyValue(null);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(8).toString())
					.setPropertyValue("Primary");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(9).toString())
					.setPropertyValue("Manual");
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(10).toString())
					.setPropertyValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(11).toString())
					.setPropertyValue(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(12).toString())
					.setPropertyValue(null);
			List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
			GtnUIFrameworkGlobalUI.addChildComponent(namespaceprefix + parameters.get(6).toString(), componentList);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(1).toString())
					.setComponentEnable(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(7).toString())
					.setComponentEnable(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(8).toString())
					.setComponentEnable(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(9).toString())
					.setComponentEnable(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(10).toString())
					.setComponentEnable(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(11).toString())
					.setComponentEnable(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(12).toString())
					.setComponentEnable(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(13).toString())
					.setComponentEnable(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(14).toString())
					.setComponentEnable(true);
			GtnFrameworkValueChangeManager.setValueChangeAllowed(true);
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnUIFrameworkAddButtonAction", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkAddButtonAction();
	}

}
