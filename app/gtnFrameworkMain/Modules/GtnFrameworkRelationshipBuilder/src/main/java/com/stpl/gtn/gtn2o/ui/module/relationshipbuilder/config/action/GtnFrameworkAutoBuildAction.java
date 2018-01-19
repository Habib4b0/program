package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.ArrayList;
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
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;
import com.vaadin.v7.ui.Tree;

public class GtnFrameworkAutoBuildAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkAutoBuildAction.class);
	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		try {

			GtnUIFrameworkBaseComponent hierarchyName = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("RB001_hierarchyName");
			GtnUIFrameworkBaseComponent hierarchyVersionNo = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent("RB001_versionNo");

			GtnWsRecordBean treeSelectedBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getSessionProperty("selectedId");
			GtnWsRecordBean selectedRelationShip = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getSessionProperty(GtnFrameworkCommonConstants.SELECTED_RELATIONSHIP);
			GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
			GtnWsRelationshipBuilderRequest rbRequest = new GtnWsRelationshipBuilderRequest();
			request.setRelationshipBuilderRequest(rbRequest);
			rbRequest.setMainNode(selectedRelationShip);
			rbRequest.setHierarchyDefSId(Integer.valueOf(hierarchyName.getValueFromComponent() == null ? "0"
					: hierarchyName.getValueFromComponent().toString()));
			rbRequest.setHierarchyVersionNo(Integer.valueOf(hierarchyVersionNo.getValueFromComponent() == null ? "0"
					: hierarchyVersionNo.getValueFromComponent().toString()));

			GtnUIFrameWorkActionConfig rbRequestAction = new GtnUIFrameWorkActionConfig(
					GtnUIFrameworkActionType.CUSTOM_ACTION);
			rbRequestAction.addActionParameter(GtnUIFrameworkRBRequestAction.class.getName());
			rbRequestAction.addActionParameter(rbRequest);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, rbRequestAction);

			List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
			GtnUIFrameworkBaseComponent rbTreeBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(parameters.get(3).toString());
			rbRequest.setSelectedTreeBean(treeSelectedBean);
			List<List<String>> parentInfoIdList = getParentIDInfo(treeSelectedBean, rbTreeBaseComponent);
			rbRequest.setHiddenIdList(parentInfoIdList.get(2));
			rbRequest.setPrimarykeyColumnList(parentInfoIdList.get(1));
			List<GtnWsRecordBean> notInList = new ArrayList<>();
			rbRequest.setRsTreeNodeList(notInList);
			GtnUIFrameworkWebserviceResponse response = wsclient.callGtnWebServiceUrl(
					GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
							+ GtnWsRelationshipBuilderConstants.AUTOBUILDERELATIONSHIP,
					request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnWsRelationshipBuilderResponse rbResponse = response.getGtnWsRelationshipBuilderResponse();
			rbTreeBaseComponent
					.removeTreeItems(rbTreeBaseComponent.getChildNodes(treeSelectedBean)
							.toArray(new GtnWsRecordBean[rbTreeBaseComponent.getChildNodes(treeSelectedBean).size()]));
			rbTreeBaseComponent.loadTreeFromTreeNode(rbResponse.getRbTreeNodeList(), treeSelectedBean);
		} catch (Exception e) {
			gtnLogger.error("Exception in loadFilteredResultLayout", e);
		}

	}


	private List<List<String>> getParentIDInfo(GtnWsRecordBean selectedLevelBean,
			GtnUIFrameworkBaseComponent rbTreeBaseComponent) {
		int levelNo = selectedLevelBean.getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal());
		List<List<String>> finalResultList = new ArrayList<>();
		Tree rbTree = (Tree) rbTreeBaseComponent.getComponentData().getCustomData();
		List<String> levelValues = new ArrayList<>();
		String levelValue = selectedLevelBean.getStringPropertyByIndex(0);
		levelValues.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		levelValues.add(levelValue);
		List<String> primarykeyColumnList = new ArrayList<>();
		primarykeyColumnList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		primarykeyColumnList
				.add(selectedLevelBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.PRIMARY_COLUMN.ordinal()));
		List<String> primaryMasterSidList = new ArrayList<>();
		primaryMasterSidList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		primaryMasterSidList.add(selectedLevelBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal()));
		Object childItemId = selectedLevelBean;
		for (int i = 1; i < levelNo; i++) {
			GtnWsRecordBean parentItemId = (GtnWsRecordBean) rbTree.getParent(childItemId);
			if (parentItemId == null)
				continue;
			levelValues.add(parentItemId.getStringPropertyByIndex(0));
			primarykeyColumnList.add(parentItemId
					.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.PRIMARY_COLUMN.ordinal()));
			primaryMasterSidList.add(
					parentItemId.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal()));
			childItemId = parentItemId;
		}
		finalResultList.add(levelValues);
		finalResultList.add(primarykeyColumnList);
		finalResultList.add(primaryMasterSidList);
		return finalResultList;
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
