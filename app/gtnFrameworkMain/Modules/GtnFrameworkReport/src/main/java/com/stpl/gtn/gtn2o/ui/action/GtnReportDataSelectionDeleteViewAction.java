package com.stpl.gtn.gtn2o.ui.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;

public class GtnReportDataSelectionDeleteViewAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(1).toString()).getComponentData().getCustomData();
		Integer viewId = (Integer) recordBean.getPropertyValueByIndex(4);
		GtnWsReportDataSelectionBean dataSelectionBean = new GtnWsReportDataSelectionBean();
		dataSelectionBean.setViewId(viewId);
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		reportRequest.setDataSelectionBean(dataSelectionBean);
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsGeneralRequest(generalRequest);
		request.setGtnWsReportRequest(reportRequest);
		new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_SERVICE + GtnWsReportConstants.GTN_REPORRT_DELETEVIEW_SERVICE, "report",
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
