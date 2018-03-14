/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vinodhini.palanisamy
 */
public class GtnUIFrameworkConfirmedCopyAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
            // No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		Object value = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(1).toString())
				.getValueFromComponent();
		if (value == null) {
			GtnUIFrameWorkActionConfig alertActionConfig = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.INFO_ACTION);
			alertActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.ERROR);
			alertActionConfig.addActionParameter("No Relationship selected. Please select Relationship");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, alertActionConfig);
			return;
		}
		GtnWsRecordBean gtnWsRecordBean = (GtnWsRecordBean) value;
		GtnUIFrameWorkActionConfig confirmationActionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CONFIRMATION_ACTION);
		confirmationActionConfig.addActionParameter(parameters.get(18));
		confirmationActionConfig
				.addActionParameter(parameters.get(19) + " " + gtnWsRecordBean.getStringPropertyByIndex(0) + "?");
		List<Object> onSuccessActionConfigList = new ArrayList<>();

		GtnUIFrameWorkActionConfig customActionConfig = new GtnUIFrameWorkActionConfig();
		customActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		customActionConfig.addActionParameter(GtnUIFrameworkRelationshipCopyAction.class.getName());
		customActionConfig.addActionParameter(parameters.get(1));
		customActionConfig.addActionParameter(parameters.get(2));
		customActionConfig.addActionParameter(parameters.get(3));
		customActionConfig.addActionParameter(parameters.get(4));
		customActionConfig.addActionParameter(parameters.get(5));
		customActionConfig.addActionParameter(parameters.get(6));
		customActionConfig.addActionParameter(parameters.get(7));
		customActionConfig.addActionParameter(parameters.get(8));
		customActionConfig.addActionParameter(parameters.get(9));
		customActionConfig.addActionParameter(parameters.get(10));
		customActionConfig.addActionParameter(parameters.get(11));
		customActionConfig.addActionParameter(parameters.get(12));
		customActionConfig.addActionParameter(parameters.get(13));
		customActionConfig.addActionParameter(parameters.get(14));
		customActionConfig.addActionParameter(parameters.get(15));
		customActionConfig.addActionParameter(parameters.get(16));
		customActionConfig.addActionParameter(parameters.get(17));
		customActionConfig.addActionParameter(parameters.get(20));
		customActionConfig.addActionParameter(parameters.get(21));
		customActionConfig.addActionParameter(parameters.get(18));
		customActionConfig.addActionParameter(parameters.get(19));
		customActionConfig.addActionParameter(parameters.get(22));
		customActionConfig.addActionParameter(parameters.get(23));
		customActionConfig.addActionParameter(parameters.get(24));
		customActionConfig.addActionParameter(gtnUIFrameWorkActionConfig);
		onSuccessActionConfigList.add(customActionConfig);

		confirmationActionConfig.addActionParameter(onSuccessActionConfigList);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmationActionConfig);

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
