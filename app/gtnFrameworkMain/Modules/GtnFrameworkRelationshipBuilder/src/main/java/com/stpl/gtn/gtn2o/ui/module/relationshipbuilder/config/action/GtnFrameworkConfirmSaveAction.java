/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkConfirmSaveAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkConfirmSaveAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		saveRelationship(componentId, parameters);
	}

	private void saveRelationship(String componentId, List<Object> parameters) throws GtnFrameworkGeneralException {
		try {
			final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			request.setRelationshipBuilderRequest((GtnWsRelationshipBuilderRequest) parameters.get(6));
			GtnUIFrameworkWebserviceResponse newResponse = wsclient.callGtnWebServiceUrl(
					GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
							+ GtnWsRelationshipBuilderConstants.SAVE_RELATIONSHIP,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnWsRelationshipBuilderResponse rbNewResponse = newResponse.getGtnWsRelationshipBuilderResponse();
			if (rbNewResponse.isSuccess()) {
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString()).setComponentEnable(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString()).setComponentEnable(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString()).setComponentEnable(false);
				GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString()).setComponentEnable(false);
				rbNewResponse.getMainNode().getProperties().set(5,
						new Date((Long) rbNewResponse.getMainNode().getProperties().get(5)));
				rbNewResponse.getMainNode().getProperties().set(6,
						new Date((Long) rbNewResponse.getMainNode().getProperties().get(6)));
				rbNewResponse.getMainNode().getProperties().set(7,
						new Date((Long) rbNewResponse.getMainNode().getProperties().get(7)));
				GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(5).toString(), rbNewResponse.getMainNode());
				GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
				confirmActionConfig.setActionType(GtnUIFrameworkActionType.NOTIFICATION_ACTION);
				confirmActionConfig.addActionParameter(rbNewResponse.getMessage());
				confirmActionConfig.addActionParameter(null);
				confirmActionConfig.addActionParameter(-1);
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, confirmActionConfig);
			}
		} catch (Exception e) {
			gtnLogger.error("Exception in saveRelationship", e);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
