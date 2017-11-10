/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;
import com.vaadin.ui.Tree;

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
}
