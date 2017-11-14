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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkConfirmedDeleteButtonAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		Object value = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
				.getValueFromComponent();
		if (value == null) {
			GtnUIFrameWorkActionConfig rbDeleteAlertAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.INFO_ACTION);
			rbDeleteAlertAction.addActionParameter(parameters.get(2).toString());
			rbDeleteAlertAction.addActionParameter(parameters.get(3).toString());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rbDeleteAlertAction);
			return;
		}
		GtnWsRecordBean relationBean = (GtnWsRecordBean) value;
		GtnUIFrameWorkAction confirmAction = GtnUIFrameworkActionType.CONFIRMATION_ACTION.getGtnUIFrameWorkAction();
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig
				.addActionParameter(parameters.get(4).toString() + relationBean.getStringPropertyByIndex(0) + "?");
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig deleteActionConfig = new GtnUIFrameWorkActionConfig();
		deleteActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		deleteActionConfig.addActionParameter(GtnUIFrameworkDeleteButtonAction.class.getName());
		deleteActionConfig.addActionParameter(relationBean);
		deleteActionConfig.addActionParameter(parameters.get(1));
		successActionConfigList.add(deleteActionConfig);
		confirmActionConfig.addActionParameter(successActionConfigList);
		confirmAction.configureParams(confirmActionConfig);
		confirmAction.doAction(componentId, confirmActionConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
