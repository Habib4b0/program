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
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkDeleteButtonAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
		request.setRelationshipBuilderRequest(rbRequest);
		rbRequest.setMainNode((GtnWsRecordBean) parameters.get(1));

		GtnUIFrameWorkActionConfig rbRequestAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		rbRequestAction.addActionParameter(GtnUIFrameworkRBRequestAction.class.getName());
		rbRequestAction.addActionParameter(rbRequest);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rbRequestAction);

		GtnUIFrameworkWebserviceResponse newResponse = wsclient.callGtnWebServiceUrl(
				GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
						+ GtnWsRelationshipBuilderConstants.DELETE_RELATIONSHIP,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsRelationshipBuilderResponse rbNewResponse = newResponse.getGtnWsRelationshipBuilderResponse();
		if (rbNewResponse.isSuccess()) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
					.removeItemFromDataTable(parameters.get(1));
			GtnUIFrameWorkActionConfig deleteNotifAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.NOTIFICATION_ACTION);
			deleteNotifAction.addActionParameter(GtnFrameworkCommonStringConstants.STRING_EMPTY);
			deleteNotifAction.addActionParameter(rbNewResponse.getMessage());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, deleteNotifAction);
			return;
		}
		GtnUIFrameWorkActionConfig rbDeleteSuccessAlertAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.ALERT_ACTION);
		rbDeleteSuccessAlertAction.addActionParameter(rbNewResponse.getMessageType());
		rbDeleteSuccessAlertAction.addActionParameter(rbNewResponse.getMessage());
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rbDeleteSuccessAlertAction);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
