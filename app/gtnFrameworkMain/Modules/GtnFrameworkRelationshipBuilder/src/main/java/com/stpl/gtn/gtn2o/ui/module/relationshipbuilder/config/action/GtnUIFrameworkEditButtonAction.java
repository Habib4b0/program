/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkEditButtonAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkEditButtonAction.class);

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
			Object value = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString())
					.getValueFromComponent();
			if (value == null) {
				GtnUIFrameWorkActionConfig rbEditAlertAction = new GtnUIFrameWorkActionConfig(
						GtnUIFrameworkActionType.ALERT_ACTION);
				rbEditAlertAction.addActionParameter(parameters.get(18).toString());
				rbEditAlertAction.addActionParameter(parameters.get(19).toString());
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rbEditAlertAction);
				return;
			}
			GtnFrameworkValueChangeManager.setValueChangeAllowed(false);
			GtnUIFrameWorkActionConfig navigationActionConfig = new GtnUIFrameWorkActionConfig();
            navigationActionConfig.addActionParameter(parameters.get(20).toString());
			GtnUIFrameWorkAction navigationAction = GtnUIFrameworkActionType.NAVIGATION_ACTION
					.getGtnUIFrameWorkAction();
			navigationAction.configureParams(navigationActionConfig);
			navigationAction.doAction(componentId, navigationActionConfig);
			GtnWsRecordBean relationshipBean = (GtnWsRecordBean) value;
			int index = (Integer) parameters.get(2);
			int versionNo = relationshipBean.getIntegerPropertyByIndex(index + 3);
			GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(3).toString(), versionNo);
			GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(4).toString(), relationshipBean);
            GtnUIFrameworkGlobalUI.addSessionProperty("mode", "Edit");
			GtnUIFrameWorkAction logicAction = new GtnUIFrameworkViewButtonAction();
			logicAction.configureParams(gtnUIFrameWorkActionConfig);
			logicAction.doAction(componentId, gtnUIFrameWorkActionConfig);
			GtnFrameworkValueChangeManager.setValueChangeAllowed(true);
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnUIFrameworkEditButtonAction", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return new GtnUIFrameworkEditButtonAction();
	}

}
