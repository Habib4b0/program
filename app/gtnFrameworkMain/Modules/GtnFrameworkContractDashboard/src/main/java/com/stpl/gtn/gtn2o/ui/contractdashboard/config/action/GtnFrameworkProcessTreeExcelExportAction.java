package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.component.excelbutton.GtnUIFrameworkExcelButtonConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.contractdashboard.constants.GtnWsContractDashboardContants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkProcessTreeExcelExportAction implements GtnUIFrameWorkAction ,GtnUIFrameworkDynamicClass{

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		// No Need to Implement. Its an unused method.
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> parameters = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsSearchRequest sRequest = new GtnWsSearchRequest();
		GtnWsGeneralRequest generalWSRequest = new GtnWsGeneralRequest();
		request.setGtnWsSearchRequest(sRequest);
		GtnUIFrameworkBaseComponent treeTableBaseComponent = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(parameters.get(1).toString());
		generalWSRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		sRequest.setGtnWebServiceSearchCriteriaList(treeTableBaseComponent.getPagedTableSearchCriteriaList());
		sRequest.setSearchColumnNameList(treeTableBaseComponent.getTableRecordHeader());
		request.setGtnWsGeneralRequest(generalWSRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsContractDashboardContants.GTN_CONTRACT_DASHBOARD_SERVICE
						+ GtnWsContractDashboardContants.GET_CONTRACT_DASHBOARD_PROCESS_TABLE_EXCEL_DATA,
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnUIFrameworkExcelButtonConfig gtnExcelButtonConfig = new GtnUIFrameworkExcelButtonConfig();
		gtnExcelButtonConfig.setExportFileName("Contract Dashboard");
		gtnExcelButtonConfig.setExportFromTable(true);
		gtnExcelButtonConfig.setIsTreeTable(true);
		gtnExcelButtonConfig.setIsNewTreeTable(true);
		gtnExcelButtonConfig.setExportList(response.getGtnWsExcelResponse().getResultBeanList());
		gtnExcelButtonConfig.setExportTableId(parameters.get(1).toString());
		GtnUIFrameWorkActionConfig excelActionConfig = new GtnUIFrameWorkActionConfig();
		excelActionConfig.setActionType(GtnUIFrameworkActionType.EXCEL_EXPORT_ACTION);
		excelActionConfig.addActionParameter(gtnExcelButtonConfig);
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, excelActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
