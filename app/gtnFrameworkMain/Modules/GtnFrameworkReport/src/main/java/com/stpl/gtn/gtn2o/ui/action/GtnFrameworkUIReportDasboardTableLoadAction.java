/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.component.grid.component.PagedTreeGrid;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.engine.data.GtnUIFrameworkComponentData;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

/**
 *
 * @author Karthik.Raja
 */
public class GtnFrameworkUIReportDasboardTableLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {

		List<Object> params = (gtnUIFrameWorkActionConfig.getActionParameterList());
		GtnUIFrameworkComponentData componentData = GtnUIFrameworkGlobalUI
				.getVaadinComponentData((String) params.get(1), componentId);
		PagedTreeGrid grid = (PagedTreeGrid) componentData.getCustomData();

		GtnUIFrameworkWebServiceClient wsclient = new GtnUIFrameworkWebServiceClient();
		GtnUIFrameworkWebserviceRequest serviceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		serviceRequest.setGtnWsReportRequest(reportRequest);
		reportRequest.setGtnWsReportDashboardBean(grid.getTableConfig().getGtnWsReportDashboardBean());
		wsclient.callGtnWebServiceUrl(GtnWsReportConstants.GTN_REPORT_DASHBOARD_GENERATE_REPORT_CALCULATION_INSERT,
				GtnFrameworkCommonStringConstants.REPORT_MODULE_NAME, serviceRequest,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());

		grid.initializeGrid();
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
