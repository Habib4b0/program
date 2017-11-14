/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.GtnFrameworkRelationshipBuilderResultLayoutConfig;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
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
public class GtnUIFrameworkViewButtonAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkViewButtonAction.class);

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
			GtnWsRecordBean relationshipBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getSessionProperty(parameters.get(4).toString());
			int index = (Integer) parameters.get(2);
			int versionNo = relationshipBean.getIntegerPropertyByIndex(index + 3);
			GtnFrameworkValueChangeManager.setValueChangeAllowed(false);
			String namespaceprefix = parameters.get(7).toString();
			boolean view = Boolean.parseBoolean(parameters.get(21).toString());
			setInitialValue(parameters, namespaceprefix, relationshipBean, index, view);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(3).toString())
					.loadDefaultCombobox(versionNo, versionNo);
			loadResultLayout(componentId, parameters, namespaceprefix, relationshipBean, view);
			GtnFrameworkValueChangeManager.setValueChangeAllowed(true);
		} catch (Exception e) {
			gtnLogger.error("Exception in GtnUIFrameworkViewButtonAction", e);
		}
	}

	private void loadResultLayout(String componentId, List<Object> parameters, String namespaceprefix,
			GtnWsRecordBean relationshipBean, boolean view) throws GtnFrameworkGeneralException {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
		request.setRelationshipBuilderRequest(rbRequest);
		rbRequest.setMainNode(relationshipBean);

		GtnUIFrameWorkActionConfig rbRequestAction = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.CUSTOM_ACTION);
		rbRequestAction.addActionParameter(GtnUIFrameworkRBRequestAction.class.getName());
		rbRequestAction.addActionParameter(rbRequest);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rbRequestAction);
		GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
				GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
						+ GtnWsRelationshipBuilderConstants.LOAD_RELATIONSHIP,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		GtnWsRelationshipBuilderResponse rbResponse = response.getGtnWsRelationshipBuilderResponse();
		GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(5).toString(), null);

		GtnFrameworkRelationshipBuilderResultLayoutConfig resultLayoutConfig = new GtnFrameworkRelationshipBuilderResultLayoutConfig();
		List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
		resultLayoutConfig.addResultLayout(componentList, namespaceprefix, view);
		GtnUIFrameworkGlobalUI.addChildComponent(namespaceprefix + parameters.get(8).toString(), componentList);

		loadHierarchyLevels(namespaceprefix, parameters, view);
		GtnUIFrameworkBaseComponent rbTreeBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(namespaceprefix + parameters.get(9).toString());
		rbTreeBaseComponent.loadTreeFromTreeNode(rbResponse.getRbTreeNodeList());
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(24).toString())
				.setComponentVisible(!rbTreeBaseComponent.getItemsFromDataTable().isEmpty());
	}

	private void setInitialValue(List<Object> parameters, String namespaceprefix, GtnWsRecordBean relationshipBean,
			int index, boolean view) {
		try {
			String relationshipType = String.valueOf(relationshipBean.getProperties().get(2));
			int hierarchySId = Integer.parseInt(String.valueOf(relationshipBean.getProperties().get(index + 1)));
			String builderType = String.valueOf(relationshipBean.getProperties().get(index + 2));
			String relationshipName = String.valueOf(relationshipBean.getProperties().get(0));
			String relationshipDescription = String.valueOf(relationshipBean.getProperties().get(1));
			Date startDate = (Date) relationshipBean.getProperties().get(5);

			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(10).toString())
					.loadComboBoxComponentValue(hierarchySId);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(11).toString())
					.loadFieldValue(relationshipType);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(12).toString())
					.loadFieldValue(builderType);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(13).toString())
					.loadDateValue(relationshipName);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(14).toString())
					.loadDateValue(relationshipDescription);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(15).toString())
					.loadDateValue(startDate);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(13).toString())
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(14).toString())
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(15).toString())
					.setComponentEnable(false);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(10).toString())
					.setComponentEnable(!view);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(11).toString())
					.setComponentEnable(!view);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(3).toString())
					.setComponentEnable(!view);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(12).toString())
					.setComponentEnable(!view);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(22).toString())
					.setComponentEnable(!view);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + parameters.get(23).toString())
					.setComponentEnable(!view);
		} catch (Exception e) {
			gtnLogger.error("Exception in setInitialValue", e);
		}
	}

	private void loadHierarchyLevels(String namespaceprefix, List<Object> parameters, boolean view)
			throws GtnFrameworkGeneralException {
		if (!view) {
			GtnUIFrameworkBaseComponent rbTableBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(namespaceprefix + parameters.get(16).toString());
			rbTableBaseComponent.getLogicFromPagedDataTable().startSearchProcess(
					Arrays.asList((namespaceprefix + "hierarchyName"), (namespaceprefix + "versionNo")), true);
		}
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(namespaceprefix + "availableHierarchyCssLayout")
				.setVisible(!view);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
