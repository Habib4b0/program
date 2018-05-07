package com.stpl.gtn.gtn2o.ui.action;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.vaadin.addons.ComboBoxMultiselect;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkConfigMap;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportVariablesType;

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
		if (rowType.equals("Expandable")) {
			Object[] dataArray = new Object[3];
			dataArray[0] = GtnWsReportVariablesType.VARIABLES.toString();
			dataArray[1] = 1;
			dataArray[2] = GtnWsHierarchyType.VARIABLES.toString();
			dataTable.addData(Arrays.<Object[]>asList(dataArray));
		} else {
			String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getParentViewId();
			String id = sourceComponentId + "_" + "reportingDashboardTab_displaySelectionTabVariable";
			ComboBoxMultiselect multiSelect = (ComboBoxMultiselect<?>) GtnUIFrameworkGlobalUI.getVaadinBaseComponent(id)
					.getComponent();
			List<Object[]> selectedVariables = (List<Object[]>) multiSelect.getSelectedItems().stream().map((value) -> {
				Object[] dataArray = new Object[3];
				dataArray[0] = value.toString();
				dataArray[1] = 1;
				dataArray[2] = GtnWsHierarchyType.VARIABLES.toString();
				return dataArray;
			}).collect(Collectors.toList());
			dataTable.addData(selectedVariables);

		}
		GtnUIFrameWorkActionConfig actionConfig = new GtnUIFrameWorkActionConfig(
				GtnUIFrameworkActionType.GRID_STATIC_LOAD_ACTION);
		actionConfig.addActionParameter(variableGridId);
		actionConfig.addActionParameter(dataTable);
		GtnUIFrameworkActionExecutor.executeSingleAction(variableGridId, actionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {

		return this;
	}

}
