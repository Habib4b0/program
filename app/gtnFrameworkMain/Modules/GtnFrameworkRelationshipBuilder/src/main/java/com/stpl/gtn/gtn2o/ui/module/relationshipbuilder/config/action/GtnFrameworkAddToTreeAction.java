/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import java.util.List;
import java.util.Set;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.relationshipbuilder.constants.GtnWsRelationshipBuilderKeyConstant;

/**
 *
 * @author Abhiram.Giri
 */
public class GtnFrameworkAddToTreeAction implements GtnUIFrameWorkAction, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();

		addSingleItem(componentId, parameters.get(2).toString(), parameters.get(3).toString(),
				parameters.get(4).toString());

	}

	private void addSingleItem(String componentId, String tableId, String treeId, String removeBtnId)
			throws GtnFrameworkGeneralException {
		GtnUIFrameworkBaseComponent rbTreeBaseComponent = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(treeId);
		Object treeSelectedBean = GtnUIFrameworkGlobalUI.getSessionProperty("selectedId");
		if (!rbTreeBaseComponent.getItemsFromDataTable().isEmpty() && treeSelectedBean == null) {
			GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.INFO_ACTION,
					GtnFrameworkCommonStringConstants.ERROR, "Please select the parent node ");
			return;
		}
		int levelNo = 0;
		if (treeSelectedBean == null) {
			levelNo = 1;
		} else {
			levelNo = ((GtnWsRecordBean) treeSelectedBean)
					.getIntegerPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()) + 1;
		}

		GtnUIFrameworkBaseComponent table = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(tableId);

		doTotalLevelOperation(componentId, rbTreeBaseComponent, table, treeSelectedBean, levelNo, removeBtnId);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	@SuppressWarnings("unchecked")
	private void doTotalLevelOperation(String componentId, GtnUIFrameworkBaseComponent rbTreeBaseComponent,
			GtnUIFrameworkBaseComponent table, Object treeSelectedBean, int levelNo, String removeBtnId)
			throws GtnFrameworkGeneralException {

		Object selectedValue = table.getValueFromComponent();
		if (checkToProceed(selectedValue, levelNo)) {
			loadAdditionProperty((Set<GtnWsRecordBean>) selectedValue);
			if (rbTreeBaseComponent.getItemsFromDataTable().isEmpty() && levelNo == 1) {
				table.removeItemsFromMultiSelectDataTable();
				rbTreeBaseComponent.addItemsToTreeDataTable(treeSelectedBean, (Set<?>) selectedValue, true);
				table.setTableValue(null);
				GtnUIFrameworkGlobalUI.getVaadinComponent(removeBtnId)
						.setVisible(!rbTreeBaseComponent.getItemsFromDataTable().isEmpty());
				return;
			}

			int treeLevelNo = Integer
					.parseInt(String.valueOf(((GtnWsRecordBean) treeSelectedBean).getPropertyValueByIndex(1)));
			if (levelNo != treeLevelNo + 1) {
				GtnUIFrameworkGlobalUI.showMessageBox(componentId, GtnUIFrameworkActionType.INFO_ACTION,
						GtnFrameworkCommonStringConstants.HALT,
						"You have selected the incorrect parent node.Please select the correct parent node. ");
				return;
			}
			table.removeItemsFromMultiSelectDataTable();
			rbTreeBaseComponent.addItemsToTreeDataTable(treeSelectedBean, (Set<?>) selectedValue, true);
			rbTreeBaseComponent.expandTreeItem(treeSelectedBean);
			table.setTableValue(null);
			GtnUIFrameworkGlobalUI.getVaadinComponent(removeBtnId)
					.setVisible(!rbTreeBaseComponent.getItemsFromDataTable().isEmpty());
		}
	}

	private void loadAdditionProperty(Set<GtnWsRecordBean> selectedValue) {
		for (GtnWsRecordBean gtnWsRecordBean : selectedValue) {
			gtnWsRecordBean.addAdditionalProperty(
					gtnWsRecordBean.getStringPropertyByIndex(GtnWsRelationshipBuilderKeyConstant.LEVEL_NO.ordinal()));
		}

	}

	private boolean checkToProceed(Object selectedValue, int levelNo) {
		return !(selectedValue == null || ((Set<?>) selectedValue).isEmpty()
				|| (levelNo == 1 && ((Set<?>) selectedValue).size() > 1));
	}

}
