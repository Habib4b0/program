package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkValidationFailedException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.vaadin.data.TreeData;
import com.vaadin.data.provider.ListDataProvider;
import com.vaadin.ui.Grid;
import com.vaadin.ui.TreeGrid;

public class GtnFrameworkUICustomVariableGridLoadAction
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
		String variableGridId = (String) parameterList.get(2);

		String rowType = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(variableTypeId, componentId)
				.getV8StringFromField();
		GtnUIFrameworkDataTable dataTable = new GtnUIFrameworkDataTable();
		GtnUIFrameworkWebserviceComboBoxResponse response;
		if (rowType.equals("Expandable")) {
			response = getHelperValues("REPORT_VARIABLES");
		} else {
			response = getHelperValues("REPORT_VARIABLES_STATIC");
		}

		List<String> itemIdlist = response.getItemCodeList();
		List<String> itemValueList = response.getItemValueList();
		List<Object[]> selectedVariables = new ArrayList<>(itemIdlist.size());
		for (int i = 0; i < itemIdlist.size(); i++) {
			Object[] dataArray = new Object[6];
			dataArray[0] = itemValueList.get(i);
			int index = GtnWsReportVariablesType.fromString(itemValueList.get(i)).ordinal();
			dataArray[1] = index;
			dataArray[2] = (65 + index);
			dataArray[3] = GtnWsHierarchyType.VARIABLES.toString();
			dataArray[4] = itemIdlist.get(i);
			if (rowType.equals("Expandable")) {
				dataArray[4] = itemIdlist.get(i);
				response = getHelperValues("REPORT_VARIABLES_STATIC");
				List<Object[]> selectedVariablesValues = new ArrayList<>(response.getItemCodeList().size());
				for (int j = 0; j < response.getItemCodeList().size(); j++) {
					Object[] subDataArray = new Object[6];
					subDataArray[0] = response.getItemValueList().get(j);
					index = GtnWsReportVariablesType.fromString(response.getItemValueList().get(j)).ordinal();
					subDataArray[1] = index;
					subDataArray[2] = (65 + index);
					subDataArray[3] = GtnWsHierarchyType.VARIABLES.toString();
					subDataArray[4] = response.getItemCodeList().get(j);
					selectedVariablesValues.add(subDataArray);
				}
				dataArray[5] = selectedVariablesValues;
			}
			selectedVariables.add(dataArray);
		}
		Collections.sort(selectedVariables, (ob1, ob2) -> {
			int object1 = (int) ob1[1];
			int object2 = (int) ob2[1];
			if (object1 > object2) {
				return 1;
			} else if (object1 < object2) {
				return -1;
			} else {
				return 0;
			}
		});
		dataTable.addData(selectedVariables);
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.GRID_STATIC_LOAD_ACTION);
		actionConfig.addActionParameter(variableGridId);
		actionConfig.addActionParameter(dataTable);
		GtnUIFrameworkActionExecutor.executeSingleAction(variableGridId, actionConfig);
		clearTreeTable(componentId, parameterList);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

	public GtnUIFrameworkWebserviceComboBoxResponse getHelperValues(String type) {
		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		generalWSRequest.setComboBoxType(type);

		request.setGtnWsGeneralRequest(generalWSRequest);
		return wsclient.callGtnWebServiceUrl(
				GtnWebServiceUrlConstants.GTN_COMMON_GENERAL_SERVICE
						+ GtnWebServiceUrlConstants.GTN_COMMON_LOAD_COMBO_BOX,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken()).getGtnUIFrameworkWebserviceComboBoxResponse();

	}

	private void clearTreeTable(String componentId, List<Object> parameterList)
			throws GtnFrameworkValidationFailedException {
		boolean action = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(componentId).getComponentConfig()
				.isUserOriginatedFlag();
		if (action) {
			addNodeBackToTree(componentId, parameterList);
			TreeGrid<GtnWsRecordBean> rightGrid = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(String.valueOf(parameterList.get(3)), componentId).getTreeGrid();
			Optional.ofNullable(rightGrid).ifPresent(grid -> {
				for (GtnWsRecordBean bean : grid.getSelectedItems()) {
					grid.deselect(bean);
				}
				grid.getTreeData().clear();
				grid.getDataProvider().refreshAll();
				grid.markAsDirty();
			});
		}
	}

	private void addNodeBackToTree(String componentId, List<Object> parameterList)
			throws GtnFrameworkValidationFailedException {
		TreeGrid<GtnWsRecordBean> rightGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(parameterList.get(3)), componentId).getTreeGrid();
		getAllTreeNodes(rightGrid.getTreeData(), rightGrid.getTreeData().getRootItems(), parameterList, componentId);
		leftTablesSortOperation(parameterList, componentId);
	}

	private void addToLeftGrid(Grid<GtnWsRecordBean> leftGrid, GtnWsRecordBean removedBean) {
		((ListDataProvider<GtnWsRecordBean>) leftGrid.getDataProvider()).getItems().add(removedBean);
		leftGrid.getDataProvider().refreshAll();
	}

	private void getAllTreeNodes(TreeData<GtnWsRecordBean> treeData, List<GtnWsRecordBean> parentItems,
			List<Object> parameterList, String componentId) throws GtnFrameworkValidationFailedException {
		if (parentItems != null && !parentItems.isEmpty()) {
			for (GtnWsRecordBean gtnWsRecordBean : parentItems) {
				char indicator = gtnWsRecordBean.getStringPropertyByIndex(3).toUpperCase().charAt(0);
				Grid<GtnWsRecordBean> leftGrid = null;
				switch (indicator) {
				case 'C':
					leftGrid = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(String.valueOf(parameterList.get(4)), componentId).getGrid();
					break;
				case 'P':
					leftGrid = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(String.valueOf(parameterList.get(5)), componentId).getGrid();
					break;
				case 'D':
					leftGrid = GtnUIFrameworkGlobalUI
							.getVaadinBaseComponent(String.valueOf(parameterList.get(6)), componentId).getGrid();
					break;
				default:
					break;
				}
				if (leftGrid != null) {
					addToLeftGrid(leftGrid, gtnWsRecordBean);
				}
				getAllTreeNodes(treeData, treeData.getChildren(gtnWsRecordBean), parameterList, componentId);
			}
		}
	}

	private void leftTablesSortOperation(List<Object> parameterList, String componentId) {
		Grid<GtnWsRecordBean> leftGrid = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(String.valueOf(parameterList.get(4)), componentId).getGrid();
		sortLeftTableData(
				(List<GtnWsRecordBean>) ((ListDataProvider<GtnWsRecordBean>) leftGrid.getDataProvider()).getItems());
		leftGrid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(parameterList.get(5)), componentId)
				.getGrid();
		sortLeftTableData(
				(List<GtnWsRecordBean>) ((ListDataProvider<GtnWsRecordBean>) leftGrid.getDataProvider()).getItems());
		leftGrid = GtnUIFrameworkGlobalUI.getVaadinBaseComponent(String.valueOf(parameterList.get(6)), componentId)
				.getGrid();
		sortLeftTableData(
				(List<GtnWsRecordBean>) ((ListDataProvider<GtnWsRecordBean>) leftGrid.getDataProvider()).getItems());
	}

	private void sortLeftTableData(List<GtnWsRecordBean> items) {
		Collections.sort(items, (comparatorObj1, comparatorObj2) -> comparatorObj1.getIntegerPropertyByIndex(2)
				- comparatorObj2.getIntegerPropertyByIndex(2));
	}

}
