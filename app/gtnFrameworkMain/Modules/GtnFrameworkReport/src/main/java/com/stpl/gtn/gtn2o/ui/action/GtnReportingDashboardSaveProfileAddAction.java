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
		if (response.getGtnWsGeneralResponse().isSucess()) {

			GtnUIFrameWorkActionConfig closePopupAction = new GtnUIFrameWorkActionConfig();
			closePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
			closePopupAction.addActionParameter(actionParamsList.get(1).toString());
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, closePopupAction);

			GtnUIFrameWorkActionConfig gtnUIFrameAlertWorkActionConfig = new GtnUIFrameWorkActionConfig();
			gtnUIFrameAlertWorkActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertMsgList = new ArrayList<>(2);
			alertMsgList.add("View Added Successfully");
			alertMsgList.add("You have successfully added" + reportingDashboardSaveProfileLookupBean.getReportProfileviewType() + "view"
					+ reportingDashboardSaveProfileLookupBean.getReportProfileviewName());
			gtnUIFrameAlertWorkActionConfig.setActionParameterList(alertMsgList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, gtnUIFrameAlertWorkActionConfig);
			return;
		} else {
			GtnUIFrameWorkActionConfig gtnUIFrameAlertWorkActionConfig = new GtnUIFrameWorkActionConfig();
			gtnUIFrameAlertWorkActionConfig.setActionType(GtnUIFrameworkActionType.ALERT_ACTION);
			List<Object> alertMsgList = new ArrayList<>(2);
			alertMsgList.add("Duplicate View Name");
			alertMsgList.add("The" + reportingDashboardSaveProfileLookupBean.getReportProfileviewType()
					+ "View name you have attempted to save is a duplicate of an existing view name."
					+ "Please enter a different view name");
			gtnUIFrameAlertWorkActionConfig.setActionParameterList(alertMsgList);
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, gtnUIFrameAlertWorkActionConfig);
			return;
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
