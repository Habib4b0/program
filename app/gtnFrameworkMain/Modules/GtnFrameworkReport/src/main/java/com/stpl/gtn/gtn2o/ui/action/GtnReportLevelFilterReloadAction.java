package com.stpl.gtn.gtn2o.ui.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsHierarchyType;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDashboardBean;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import java.util.ArrayList;
import java.util.List;

public class GtnReportLevelFilterReloadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		String sourceComponentId = GtnUIFrameworkGlobalUI.getVaadinViewComponentData(componentId).getViewId();
		GtnWsReportDataSelectionBean dataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId).getComponentData().getSharedPopupData();
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		reportRequest.setDataSelectionBean(dataSelectionBean);
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsGeneralRequest(generalRequest);
		request.setGtnWsReportRequest(reportRequest);
		GtnWsReportDashboardBean dashboardBean = new GtnWsReportDashboardBean();
		dashboardBean.setHierarchyType((GtnWsHierarchyType) gtnUIFrameWorkActionConfig.getActionParameterList().get(2));
		reportRequest.setGtnWsReportDashboardBean(dashboardBean);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_FILTER_SERVICE
						+ GtnWsReportConstants.GTN_WS_REPORT_CUST_PRODLEVEL_LOAD_SERVICE,
				"report", request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = response
				.getGtnUIFrameworkWebserviceComboBoxResponse();
                List<String> itemValueList = new ArrayList<>(comboBoxResponse.getItemValueList());
                List<String> itemCodeList = new ArrayList<>(comboBoxResponse.getItemCodeList());

		GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId + "_"
						+ String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)))
				.addAllItemsToComboBox(itemValueList, itemCodeList);
                GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(sourceComponentId + "_"
						+ String.valueOf(gtnUIFrameWorkActionConfig.getActionParameterList().get(1)))
				.loadV8ComboBoxComponentValue(itemCodeList.get(0));
                
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}