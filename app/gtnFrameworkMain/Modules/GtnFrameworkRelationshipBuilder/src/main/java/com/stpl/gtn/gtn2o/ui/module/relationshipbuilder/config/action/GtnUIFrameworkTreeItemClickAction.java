/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderConstants;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.relationshipbuilder.GtnWsRelationshipBuilderRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.relationshipbuilder.GtnWsRelationshipBuilderResponse;
import com.vaadin.v7.ui.Tree;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnUIFrameworkTreeItemClickAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {
	private final GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnUIFrameworkTreeItemClickAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		loadFilteredResultLayout((GtnWsRecordBean) gtnUIFrameWorkActionConfig.getEventParameter(),
				gtnUIFrameWorkActionConfig.getActionParameterList(), componentId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	private void loadFilteredResultLayout(GtnWsRecordBean levelBean, List<Object> parameters, String componentId)
			throws GtnFrameworkGeneralException {
		try {
			GtnUIFrameworkGlobalUI.addSessionProperty(parameters.get(5).toString(), levelBean);
			int levelNo = levelBean.getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal())
					+ 1;
			String tableId = parameters.get(4).toString();

			GtnUIFrameworkBaseComponent rbTreeBaseComponent = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(componentId);
			List<GtnWsRecordBean> treeNodeList = rbTreeBaseComponent.getChildNodes(levelBean);
			List<List<String>> parentInfoIdList = getParentIDInfo(levelBean, rbTreeBaseComponent, levelNo - 1);

			GtnUIFrameworkBaseComponent rbTableBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId);
			rbTableBaseComponent.getLogicFromPagedDataTable().addCurrentSearchCriteria(
					Arrays.asList(parameters.get(2).toString(), parameters.get(3).toString()), null);

			GtnWebServiceSearchCriteria treeNodeListCriteria = new GtnWebServiceSearchCriteria();
			treeNodeListCriteria.setFilterValue3(treeNodeList);
			rbTableBaseComponent.getLogicFromPagedDataTable().addCurrentSearchCriteria(treeNodeListCriteria);

			GtnWebServiceSearchCriteria levelNoCriteria = new GtnWebServiceSearchCriteria();
			levelNoCriteria.setFilterValue1(String.valueOf(levelNo));
			rbTableBaseComponent.getLogicFromPagedDataTable().addCurrentSearchCriteria(levelNoCriteria);

			GtnWebServiceSearchCriteria primaryIDListCriteria = new GtnWebServiceSearchCriteria();
			primaryIDListCriteria.setFilterValue3(parentInfoIdList.get(2));
			rbTableBaseComponent.getLogicFromPagedDataTable().addCurrentSearchCriteria(primaryIDListCriteria);

			GtnWebServiceSearchCriteria notInList = new GtnWebServiceSearchCriteria();
			notInList.setFilterValue3(getLinkedLevelNotInList(levelBean, rbTreeBaseComponent, parameters));
			rbTableBaseComponent.getLogicFromPagedDataTable().addCurrentSearchCriteria(notInList);

			rbTableBaseComponent.getLogicFromPagedDataTable().startSearchProcess(true);
		} catch (Exception e) {
			gtnLogger.error("Exception in loadFilteredResultLayout", e);
		}
	}

	private List<List<String>> getParentIDInfo(GtnWsRecordBean levelBean,
			GtnUIFrameworkBaseComponent rbTreeBaseComponent, int levelNo) {
		List<List<String>> idList = new ArrayList<>();
		Tree rbTree = (Tree) rbTreeBaseComponent.getComponentData().getCustomData();
		List<String> levelValues = new ArrayList<>();
		String levelValue = levelBean.getStringPropertyByIndex(0);
		levelValues.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		levelValues.add(levelValue);
		List<String> primarykeyColumnList = new ArrayList<>();
		primarykeyColumnList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		primarykeyColumnList
				.add(levelBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.PRIMARY_COLUMN.ordinal()));
		List<String> primarySIDList = new ArrayList<>();
		primarySIDList.add(GtnFrameworkCommonStringConstants.STRING_EMPTY);
		primarySIDList.add(levelBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal()));
		Object childItemId = levelBean;
		for (int i = 1; i < levelNo; i++) {
			GtnWsRecordBean parentItemId = (GtnWsRecordBean) rbTree.getParent(childItemId);
			levelValues.add(parentItemId.getStringPropertyByIndex(0));
			primarykeyColumnList.add(parentItemId
					.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.PRIMARY_COLUMN.ordinal()));
			primarySIDList.add(
					parentItemId.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal()));
			childItemId = parentItemId;
		}
		idList.add(levelValues);
		idList.add(primarykeyColumnList);
		idList.add(primarySIDList);
		return idList;
	}

	@SuppressWarnings("unchecked")
	private Set<String> getLinkedLevelNotInList(GtnWsRecordBean currentSelectedBean,
			GtnUIFrameworkBaseComponent rbTreeBaseComponent, List<Object> parameters) {
		Set<String> notInList = new HashSet<>();
		String currentLevelNo = currentSelectedBean
				.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal());
		if (isEligibleForAdding(Integer.parseInt(currentLevelNo), parameters) && !isFirstLevel(currentSelectedBean)
				&& isUserDefinedLevel(currentSelectedBean)) {
			Tree rbTree = (Tree) rbTreeBaseComponent.getComponentData().getCustomData();
			GtnWsRecordBean recentParentItemId = getRecentLinkedLevel(currentSelectedBean, rbTree);
			if (!isUserDefinedLevel(recentParentItemId)
					|| (isUserDefinedLevel(recentParentItemId) && !isFirstLevel(recentParentItemId))) {
				List<GtnWsRecordBean> childList = rbTreeBaseComponent.getChildNodes(recentParentItemId);
				List<GtnWsRecordBean> entireTreeNodeList = new ArrayList<>(rbTreeBaseComponent.getTreeNodes());
				addAllNotInConditionValues(childList, notInList, currentLevelNo, entireTreeNodeList);
			}
		}
		return notInList;
	}

	private GtnWsRecordBean getRecentLinkedLevel(GtnWsRecordBean newLevelBean, Tree relationBuilderTree) {
		if (isFirstLevel(newLevelBean)) {
			return newLevelBean;
		}
		GtnWsRecordBean parentItemId = (GtnWsRecordBean) relationBuilderTree.getParent(newLevelBean);
		if (isUserDefinedLevel(parentItemId)) {
			return getRecentLinkedLevel(parentItemId, relationBuilderTree);
		}

		return parentItemId;
	}

	@SuppressWarnings("unchecked")
	private void addAllNotInConditionValues(List<GtnWsRecordBean> childList, Set<String> notInList,
			String currentLevelNo, List<GtnWsRecordBean> entireTreeNodeList) {
		if (childList != null && !childList.isEmpty()) {
			int originalEndLeveNo = Integer.parseInt(currentLevelNo);
			for (GtnWsRecordBean childListBean : childList) {
				int currentChildLevelNo = Integer.parseInt(
						childListBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
				if (originalEndLeveNo == currentChildLevelNo) {
					setNotInValues(childListBean, entireTreeNodeList, notInList);
				} else {
					addAllNotInConditionValues(
							getCurrentChildList(entireTreeNodeList, childListBean,
									childListBean.getStringPropertyByIndex(
											GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal())),
							notInList, currentLevelNo, entireTreeNodeList);
				}
			}
		}
	}

	private void setNotInValues(GtnWsRecordBean childListBean, List<GtnWsRecordBean> entireTreeNodeList,
			Set<String> notInList) {
		@SuppressWarnings("unchecked")
		List<GtnWsRecordBean> subChildList = getCurrentChildList(entireTreeNodeList, childListBean,
				childListBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
		if (subChildList != null && !subChildList.isEmpty()) {
			for (GtnWsRecordBean subChildListBean : subChildList) {
				if (!isUserDefinedLevel(subChildListBean)) {
					notInList.add(subChildListBean
							.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal()));
				}
			}
		}

	}

	private List<GtnWsRecordBean> getCurrentChildList(List<GtnWsRecordBean> childList, GtnWsRecordBean bean,
			String currentLevelNo) {
		List<GtnWsRecordBean> returnList = new ArrayList<>();
		if (childList != null && !childList.isEmpty()) {
			int originalEndLeveNo = Integer.parseInt(currentLevelNo);
			for (GtnWsRecordBean gtnWsRecordBean : childList) {
				int currentChildLevelNo = Integer.parseInt(gtnWsRecordBean
						.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
				boolean isNodeVisited = (boolean) gtnWsRecordBean
						.getPropertyValueByIndex(GtnWsRelationshipBuilderKeyConstant.IS_NODE_VISITED.ordinal());
				if ( gtnWsRecordBean.getChildList() != null
						&& !gtnWsRecordBean.getChildList().isEmpty() && !isNodeVisited
						&& gtnWsRecordBean
								.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal())
								.equals(bean.getStringPropertyByIndex(
										GtnWsRelationshipBuilderKeyConstant.HIDDEN_ID.ordinal()))
						&& currentChildLevelNo == originalEndLeveNo) {
					GtnWsRecordBean.addProperties(GtnWsRelationshipBuilderKeyConstant.IS_NODE_VISITED.ordinal(), Boolean.TRUE,
							gtnWsRecordBean.getProperties());
					returnList.addAll(gtnWsRecordBean.getChildList());
				} else if (currentChildLevelNo != originalEndLeveNo && gtnWsRecordBean.getChildList() != null
						&& !gtnWsRecordBean.getChildList().isEmpty()) {
					returnList.addAll(getCurrentChildList(gtnWsRecordBean.getChildList(), bean, currentLevelNo));
				}
			}
		}
		return returnList;
	}

	private boolean isUserDefinedLevel(final GtnWsRecordBean levelBean) {
		return GtnFrameworkWebserviceConstant.USER_DEFINED.equals(levelBean
				.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_VALUES_REFERENCE.ordinal()));
	}

	private boolean isFirstLevel(final GtnWsRecordBean levelBean) {
		return GtnWsRelationshipBuilderConstants.NUMERIC_CONSTANT_ONE
				.equals(levelBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
	}

	private boolean isEligibleForAdding(int currentLevelNo, List<Object> parameters) {
		boolean returnFlag = false;
		try {
			int hierarchySid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(2).toString())
					.getIntegerFromField();
			int versionNo = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(3).toString())
					.getIntegerFromField();
			GtnUIFrameworkWebServiceClient eligibleForAddingWsClient = new GtnUIFrameworkWebServiceClient();
			GtnUIFrameworkWebserviceRequest eligibleForAddingRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsRelationshipBuilderRequest eligibleForAddingRbRequest = new GtnWsRelationshipBuilderRequest();
			eligibleForAddingRequest.setRelationshipBuilderRequest(eligibleForAddingRbRequest);
			eligibleForAddingRbRequest.setHierarchyDefSId(hierarchySid);
			eligibleForAddingRbRequest.setVersionNo(versionNo);
			GtnUIFrameworkWebserviceResponse eligibleForAddingResponse = eligibleForAddingWsClient.callGtnWebServiceUrl(
					GtnWsRelationshipBuilderConstants.GTN_RELATIONSHIP_BUILDER_SERVICE
							+ GtnWsRelationshipBuilderConstants.HIERARCHY_LEVELNAME_LIST,
					eligibleForAddingRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			GtnWsRelationshipBuilderResponse eligibleForAddingRbResponse = eligibleForAddingResponse
					.getGtnWsRelationshipBuilderResponse();
			List<String> hierarchyLevelNameList = eligibleForAddingRbResponse.getHierarchyLevelNameList();
			if (hierarchyLevelNameList != null && currentLevelNo != hierarchyLevelNameList.size() && !GtnFrameworkWebserviceConstant.USER_DEFINED
					.equals(hierarchyLevelNameList.get(currentLevelNo))) {
				returnFlag = true;
			}
		} catch (GtnFrameworkValidationFailedException e) {
			gtnLogger.error(" Error while checking Eligible For Adding not in list " + e);
		}
		return returnFlag;
	}

}
