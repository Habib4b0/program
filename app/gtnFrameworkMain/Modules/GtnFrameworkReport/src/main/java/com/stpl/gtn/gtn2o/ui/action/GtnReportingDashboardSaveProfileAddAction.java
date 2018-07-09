package com.stpl.gtn.gtn2o.ui.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.ui.constants.GtnFrameworkReportStringConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.report.bean.GtnReportingDashboardSaveProfileLookupBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnReportingDashboardSaveProfileAddAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportingDashboardSaveProfileAddAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.info("loading configureParams");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		
		GtnReportingDashboardSaveProfileLookupBean reportingDashboardSaveProfileLookupBean = (GtnReportingDashboardSaveProfileLookupBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(1).toString(),componentId).getComponentData().getSharedPopupData();
		
		reportingDashboardSaveProfileLookupBean.setReportProfileviewName(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(2).toString(), componentId).getV8StringFromField()));
		
		reportingDashboardSaveProfileLookupBean.setReportProfileviewType(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(3).toString(), componentId).getV8StringFromField()));
		
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		
		GtnWsReportRequest reportRequest = new GtnWsReportRequest();
		reportRequest.setReportingDashboardSaveProfileLookupBean(reportingDashboardSaveProfileLookupBean);
		request.setGtnWsReportRequest(reportRequest);
		
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_SERVICE + GtnWsReportConstants.GTN_REPORT_PROFILE_SAVE_SERVICE, "report",
				request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnUIFrameWorkActionConfig reportSaveProfileActionConfig = new GtnUIFrameWorkActionConfig();
		reportSaveProfileActionConfig.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
		if (response.getGtnWsGeneralResponse().isSucess()) {

			GtnUIFrameWorkActionConfig reportSaveProfileClosePopupAction = new GtnUIFrameWorkActionConfig();
			reportSaveProfileClosePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
			reportSaveProfileClosePopupAction.addActionParameter(GtnFrameworkReportStringConstants.REPORT_DASHBOARD_SAVE_PROFILE_LOOKUP_VIEW_ID);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, reportSaveProfileClosePopupAction);
			
			reportSaveProfileActionConfig.addActionParameter("View Added Successfully");
			reportSaveProfileActionConfig.addActionParameter("You have successfully added " + reportingDashboardSaveProfileLookupBean.getReportProfileviewType() + " View "
					+ reportingDashboardSaveProfileLookupBean.getReportProfileviewName());
			
		} else {
			reportSaveProfileActionConfig.addActionParameter("Duplicate View Name");
			reportSaveProfileActionConfig.addActionParameter("The" + reportingDashboardSaveProfileLookupBean.getReportProfileviewType()
					+ "View name you have attempted to save is a duplicate of an existing view name."
					+ "Please enter a different view name");
		}
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, reportSaveProfileActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
