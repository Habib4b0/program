/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
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
public class GtnFrameworkSaveAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

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
		final GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		final GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
		request.setRelationshipBuilderRequest(rbRequest);
		Object relation = GtnUIFrameworkGlobalUI.getSessionProperty(parameters.get(11).toString());
		if (relation != null) {
			GtnWsRecordBean relationshipBean = (GtnWsRecordBean) relation;
			String mode = (String) GtnUIFrameworkGlobalUI.getSessionProperty("mode");
			if (mode.equals("Copy")) {
				relationshipBean.setPropertyValueByIndex(4, "1");
				relationshipBean.setPropertyValueByIndex(9, "0");
			}
			rbRequest.setMainNode(relationshipBean);
			GtnUIFrameWorkActionConfig rbRequestAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);
			rbRequestAction.addActionParameter(GtnUIFrameworkRBRequestAction.class.getName());
			rbRequestAction.addActionParameter(rbRequest);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rbRequestAction);
		}
		rbRequest.setUserId(Integer.valueOf(GtnUIFrameworkGlobalUI.getCurrentUser()));
		String relationshipName = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
				.getStringFromField();
		String relationshipDesc = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString())
				.getStringFromField();
		int hierarchySid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(4).toString())
				.getIntegerFromField();
		String relationshipType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(5).toString())
				.getStringFromField();
		int versionNo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(6).toString())
				.getIntegerFromField();
		Date startDate = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(7).toString())
				.getDateFromDateField();
		String buildType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(8).toString())
				.getStringFromField();
		rbRequest.setHierarchyDefSId(hierarchySid);
		rbRequest.setHierarchyVersionNo(versionNo);
		rbRequest.setRelationshipName(relationshipName);
		rbRequest.setRelationshipDescription(relationshipDesc);
		rbRequest.setRelationshipType(relationshipType);
		rbRequest.setBuildType(buildType);
		rbRequest.setStartDate(startDate);

		GtnUIFrameworkBaseComponent rbTreeBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(9).toString());
		if (rbTreeBaseComponent != null) {
			List<GtnWsRecordBean> treeNodeList = rbTreeBaseComponent.getTreeNodes();
			rbRequest.setRsTreeNodeList(treeNodeList);
		}

		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
						+ GtnWsRelationshipBuilderConstants.CHECK_SAVE_RELATIONSHIP,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsRelationshipBuilderResponse rbResponse = response.getGtnWsRelationshipBuilderResponse();

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(10).toString()).setComponentVisible(false);
		if (rbResponse.isSuccess()) {
			confirmSaveRelationship(componentId, parameters, relationshipName, rbRequest);
			return;
		}
		if (rbResponse.getMessageType().equals(GtnFrameworkCommonStringConstants.VALIDATION)) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(10).toString()).setComponentVisible(true);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(10).toString())
					.setPropertyValue(rbResponse.getMessage());
			return;
		}
		GtnUIFrameWorkActionConfig rbSaveAlertAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.INFO_ACTION);
		rbSaveAlertAction.addActionParameter(rbResponse.getMessageType());
		rbSaveAlertAction.addActionParameter(rbResponse.getMessage());
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rbSaveAlertAction);
	}

	private void confirmSaveRelationship(String componentId, List<Object> parameters, String relationshipName,
			GtnWsRelationshipBuilderRequest rbRequest) throws GtnFrameworkGeneralException {
		GtnUIFrameWorkAction confirmAction = GtnUIFrameworkActionType.CONFIRMATION_ACTION.getGtnUIFrameWorkAction();
		GtnUIFrameWorkActionConfig confirmActionConfig = new GtnUIFrameWorkActionConfig();
		confirmActionConfig.addActionParameter(GtnFrameworkCommonStringConstants.CONFIRMATION);
		confirmActionConfig.addActionParameter("Save record " + relationshipName + " ?");
		List<GtnUIFrameWorkActionConfig> successActionConfigList = new ArrayList<>();
		GtnUIFrameWorkActionConfig saveActionConfig = new GtnUIFrameWorkActionConfig();
		saveActionConfig.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
		saveActionConfig.addActionParameter(GtnFrameworkConfirmSaveAction.class.getName());
		saveActionConfig.addActionParameter(parameters.get(2).toString());
		saveActionConfig.addActionParameter(parameters.get(3).toString());
		saveActionConfig.addActionParameter(parameters.get(5).toString());
		saveActionConfig.addActionParameter(parameters.get(7).toString());
		saveActionConfig.addActionParameter(parameters.get(11).toString());
		saveActionConfig.addActionParameter(rbRequest);
		successActionConfigList.add(saveActionConfig);
		confirmActionConfig.addActionParameter(successActionConfigList);
		confirmAction.configureParams(confirmActionConfig);
		confirmAction.doAction(componentId, confirmActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
