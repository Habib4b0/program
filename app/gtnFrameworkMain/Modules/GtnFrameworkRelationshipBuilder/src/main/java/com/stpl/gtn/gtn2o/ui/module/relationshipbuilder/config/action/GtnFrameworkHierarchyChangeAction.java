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
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
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
public class GtnFrameworkHierarchyChangeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		if (GtnFrameworkValueChangeManager.isValueChangeAllowed()) {
			int hierarchySid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getIntegerFromField();
			List<Integer> versionNoList = new ArrayList<>();
			if (hierarchySid != 0) {
				versionNoList = getVersion(hierarchySid);
			}
			GtnFrameworkValueChangeManager.setValueChangeAllowed(false);
			GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(gtnUIFrameWorkActionConfig.getActionParameterList().get(2).toString())
					.loadDefaultComboboxAll(versionNoList, 0);
			GtnFrameworkValueChangeManager.setValueChangeAllowed(true);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private List<Integer> getVersion(int hierarchySid) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
		request.setRelationshipBuilderRequest(rbRequest);
		rbRequest.setHierarchyDefSId(hierarchySid);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
						+ GtnWsRelationshipBuilderConstants.GET_VERSION_NO,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnWsRelationshipBuilderResponse rbResponse = response.getGtnWsRelationshipBuilderResponse();
		return rbResponse.getHierarchyVersionNo();
	}

}
