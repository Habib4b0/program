/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.action.workflow;

import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Nimisha.Rakesh
 */
public class GtnFrameworkForecastWorkflowButtonAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@SuppressWarnings("unchecked")
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParametersList = gtnUIFrameWorkActionConfig.getActionParameterList();

		Map<String, String> projectionDetails = (Map<String, String>) actionParametersList.get(8);

		String workflowStatus = projectionDetails.get(String.valueOf(actionParametersList.get(6)));
		String userType = projectionDetails.get(String.valueOf(actionParametersList.get(7)));

		if (GtnFrameworkCommonStringConstants.CREATOR.equalsIgnoreCase(userType)
				&& GtnFrameworkCommonStringConstants.PENDNG.equalsIgnoreCase(workflowStatus)) {
			GtnUIFrameworkGlobalUI.setEnableFlagForComponent(false, (List<String>) actionParametersList.get(5),
					componentId);
			GtnUIFrameworkGlobalUI.setVisibleFlagForComponent(false, (List<String>) actionParametersList.get(1),
					componentId);
		} else if (GtnFrameworkCommonStringConstants.REJECTED.equalsIgnoreCase(workflowStatus)
				|| GtnFrameworkCommonStringConstants.WITHDRAWN.equalsIgnoreCase(workflowStatus)) {
			GtnUIFrameworkGlobalUI.setVisibleFlagForComponent(false, (List<String>) actionParametersList.get(2),
					componentId);
		} else if (GtnFrameworkCommonStringConstants.CANCELLED.equalsIgnoreCase(workflowStatus)
				|| GtnFrameworkCommonStringConstants.APPROVED.equalsIgnoreCase(workflowStatus)) {
			GtnUIFrameworkGlobalUI.setEnableFlagForComponent(false, (List<String>) actionParametersList.get(5),
					componentId);
			GtnUIFrameworkGlobalUI.setVisibleFlagForComponent(false, (List<String>) actionParametersList.get(3),
					componentId);
		} else if (GtnFrameworkCommonStringConstants.APPROVER.equalsIgnoreCase(userType)
				&& GtnFrameworkCommonStringConstants.PENDNG.equalsIgnoreCase(workflowStatus)) {
			GtnUIFrameworkGlobalUI.setEnableFlagForComponent(false, (List<String>) actionParametersList.get(5),
					componentId);
			GtnUIFrameworkGlobalUI.setVisibleFlagForComponent(false, (List<String>) actionParametersList.get(4),
					componentId);
		}

	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
