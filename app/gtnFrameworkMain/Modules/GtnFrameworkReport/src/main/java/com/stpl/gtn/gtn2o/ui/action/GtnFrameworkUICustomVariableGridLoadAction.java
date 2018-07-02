package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataTable;
import com.stpl.gtn.gtn2o.ws.constants.url.GtnWebServiceUrlConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;

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
		List<Object[]> selectedVariables = new ArrayList<>();
		for (int i = 0; i < itemIdlist.size(); i++) {
			Object[] dataArray = new Object[6];
			dataArray[0] = itemValueList.get(i);
			dataArray[1] = i;
			dataArray[2] = (65 + i);
			dataArray[3] = GtnWsHierarchyType.VARIABLES.toString();
			dataArray[4] = itemIdlist.get(i);
			if (rowType.equals("Expandable")) {
				dataArray[4] = itemIdlist.get(i);
				response = getHelperValues("REPORT_VARIABLES_STATIC");
				List<Object[]> selectedVariablesValues = new ArrayList<>();
				for (int j = 0; j < response.getItemCodeList().size(); j++) {
					Object[] subDataArray = new Object[6];
					subDataArray[0] = response.getItemValueList().get(j);
					subDataArray[1] = j;
					subDataArray[2] = (65 + j);
					subDataArray[3] = GtnWsHierarchyType.VARIABLES.toString();
					subDataArray[4] = response.getItemCodeList().get(j);
					selectedVariablesValues.add(subDataArray);
				}
				dataArray[5] = selectedVariablesValues;
			}
			selectedVariables.add(dataArray);
		}

		dataTable.addData(selectedVariables);
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
}
