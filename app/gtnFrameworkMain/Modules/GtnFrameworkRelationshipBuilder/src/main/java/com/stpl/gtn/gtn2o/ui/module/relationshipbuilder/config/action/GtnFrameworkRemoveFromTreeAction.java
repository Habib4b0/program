/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnWebServiceSearchCriteria;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkRemoveFromTreeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		removeTreeItems(gtnUIFrameWorkActionConfig, parameters.get(1).toString(), parameters.get(2).toString(),
				componentId);
	}

	private void removeTreeItems(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig, String treeId,
			String initialTableId, String componentId) throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent rbTreeBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(treeId);
		List<GtnWsRecordBean> returnList = rbTreeBaseComponent.removeParentAndChildTreeItems(initialTableId, false);
		if (returnList == null) {
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.INFO_ACTION,
					GtnFrameworkCommonStringConstants.ERROR, "Please select a node to remove");
			return;
		}
		if (returnList.isEmpty()) {
			GtnUIFrameworkGlobalUI.addSessionProperty("selectedId", null);
			rbTreeBaseComponent.setTableValue(null);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId)
					.setComponentVisible(!rbTreeBaseComponent.getItemsFromDataTable().isEmpty());
			loadResultLayout(gtnUIFrameWorkActionConfig);
			return;
		}
    }

	private void loadResultLayout(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		loadHierarchyLevels(parameters);
	}

	private void loadHierarchyLevels(List<Object> parameters) throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent rbTableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(5).toString());
		List<GtnWsRecordBean> treeNodeList = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(parameters.get(1).toString())
				.getAllTreeNodes();
		rbTableBaseComponent.getLogicFromPagedDataTable().addCurrentSearchCriteria(
				Arrays.asList(parameters.get(3).toString(), parameters.get(4).toString()), null);

		GtnWebServiceSearchCriteria treeNodeListCriteria = new GtnWebServiceSearchCriteria();
		treeNodeListCriteria.setFilterValue3(treeNodeList);
		rbTableBaseComponent.getLogicFromPagedDataTable().addCurrentSearchCriteria(treeNodeListCriteria);

		rbTableBaseComponent.getLogicFromPagedDataTable().startSearchProcess(true);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}