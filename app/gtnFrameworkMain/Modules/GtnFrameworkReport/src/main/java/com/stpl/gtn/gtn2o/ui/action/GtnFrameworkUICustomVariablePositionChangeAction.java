package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkUICustomVariablePositionChangeAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;

	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameterList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String variableTypeId = (String) parameterList.get(1);
		String variableTypeGridId = (String) parameterList.get(2);
		String variableTypeAddButtonId = (String) parameterList.get(4);
		String variableTypeRemoveButtonId = (String) parameterList.get(5);
		String selectedType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getV8StringFromField();
		boolean isNeedToBeEnabled = selectedType.equals("Rows");

		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(variableTypeId, componentId)
				.setComponentEnable(isNeedToBeEnabled);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(variableTypeGridId, componentId)
				.setComponentEnable(isNeedToBeEnabled);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(variableTypeAddButtonId, componentId)
				.setComponentEnable(isNeedToBeEnabled);
		GtnUIFrameworkGlobalUI.getVaadinBaseComponent(variableTypeRemoveButtonId, componentId)
				.setComponentEnable(isNeedToBeEnabled);

		clearTreeTable(componentId, parameterList);
	}

	private void clearTreeTable(String componentId, List<Object> paramList)
			throws GtnFrameworkValidationFailedException {
		boolean action = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getComponentConfig()
				.isUserOriginatedFlag();
		if (action) {
			addNodeBackToTree(componentId, paramList);
			TreeGrid<GtnWsRecordBean> rightGrid = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(paramList.get(3)), componentId).getTreeGrid();
			Optional.ofNullable(rightGrid).ifPresent(treeGrid -> {
				for (GtnWsRecordBean bean : treeGrid.getSelectedItems()) {
					treeGrid.deselect(bean);
				}
				treeGrid.getTreeData().clear();
				treeGrid.getDataProvider().refreshAll();
				treeGrid.markAsDirty();
			});
		}

	}

	private void addNodeBackToTree(String componentId, List<Object> parameterList)
			throws GtnFrameworkValidationFailedException {
		TreeGrid<GtnWsRecordBean> rightTreeGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(parameterList.get(3)), componentId).getTreeGrid();
		getAllTreeNodes(rightTreeGrid.getTreeData().getRootItems(), componentId, rightTreeGrid.getTreeData(),
				parameterList);
	}

	private void addToLeftGrid(Grid<GtnWsRecordBean> leftTableGrid, GtnWsRecordBean removedBean) {
		((ListDataProvider<GtnWsRecordBean>) leftTableGrid.getDataProvider()).getItems().add(removedBean);
		leftTableGrid.getDataProvider().refreshAll();
	}

	private void getAllTreeNodes(List<GtnWsRecordBean> parentItems, String componentId,
			TreeData<GtnWsRecordBean> treeData, List<Object> parameterList)
			throws GtnFrameworkValidationFailedException {
		if (parentItems != null && !parentItems.isEmpty()) {
			for (GtnWsRecordBean bean : parentItems) {
				char indicator = bean.getStringPropertyByIndex(3).toUpperCase().charAt(0);
				Grid<GtnWsRecordBean> leftGrid = null;
				switch (indicator) {
				case 'D':
					leftGrid = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(String.valueOf(parameterList.get(8)), componentId).getGrid();
					break;
				case 'P':
					leftGrid = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(String.valueOf(parameterList.get(7)), componentId).getGrid();
					break;
				case 'V':
					leftGrid = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(String.valueOf(parameterList.get(9)), componentId).getGrid();
					break;
				case 'C':
					leftGrid = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(String.valueOf(parameterList.get(6)), componentId).getGrid();
					break;
				default:
					break;
				}
				if (leftGrid != null) {
					addToLeftGrid(leftGrid, bean);
				}
				getAllTreeNodes(treeData.getChildren(bean), componentId, treeData, parameterList);
			}
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
