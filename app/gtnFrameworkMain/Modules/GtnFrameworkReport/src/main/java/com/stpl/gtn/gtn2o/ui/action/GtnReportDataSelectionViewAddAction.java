package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnReportDataSelectionViewAddAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnReportDataSelectionViewAddAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsReportDataSelectionBean reportDataSelectionBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(1).toString()).getComponentData().getSharedPopupData();
		reportDataSelectionBean.setViewName(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(2).toString(), componentId).getV8StringFromField()));
		reportDataSelectionBean.setViewType(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(3).toString(), componentId).getV8StringFromField()));
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		reportRequest.setDataSelectionBean(reportDataSelectionBean);
		request.setGtnWsReportRequest(reportRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_SERVICE + GtnWsReportConstants.GTN_REPORT_SAVEVIEW_SERVICE, "report",
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
