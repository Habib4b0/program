package com.stpl.gtn.gtn2o.ui.action;

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
import com.stpl.gtn.gtn2o.ws.report.bean.GtnWsReportDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.report.GtnWsReportRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnReportDataSelectionViewAddAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnReportDataSelectionViewAddAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		logger.debug("configure params");
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig actionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParameterList = actionConfig.getActionParameterList();
		GtnWsReportDataSelectionBean reportDSBean = (GtnWsReportDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(1).toString()).getComponentData().getSharedPopupData();
		reportDSBean.setViewName(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId).getV8StringFromField()));
		reportDSBean.setViewType(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParameterList.get(3).toString(), componentId).getV8StringFromField()));
		GtnUIFrameworkWebserviceRequest webServiceRequest = new GtnUIFrameworkWebserviceRequest();
		GtnWsReportRequest gtnWsReportRequest = new GtnWsReportRequest();
                reportDSBean.setFlag(0);
		gtnWsReportRequest.setDataSelectionBean(reportDSBean);
		webServiceRequest.setGtnWsReportRequest(gtnWsReportRequest);
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_SERVICE + GtnWsReportConstants.GTN_REPORT_SAVEVIEW_SERVICE, "report",
				webServiceRequest, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		GtnUIFrameWorkActionConfig gtnUIFrameAlertWorkActionConfig = new GtnUIFrameWorkActionConfig();
		gtnUIFrameAlertWorkActionConfig.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
		if (response.getGtnWsGeneralResponse().isSucess()) {

			GtnUIFrameWorkActionConfig closePopupAction = new GtnUIFrameWorkActionConfig();
			closePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
			closePopupAction.addActionParameter("dsSaveViewLookUp");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, closePopupAction);
			
			gtnUIFrameAlertWorkActionConfig.addActionParameter("View Added Successfully");
			gtnUIFrameAlertWorkActionConfig.addActionParameter("You have successfully added " + reportDSBean.getViewType() + " View "
					+ reportDSBean.getViewName());
			
		} else {
			gtnUIFrameAlertWorkActionConfig.addActionParameter("Duplicate View Name");
			gtnUIFrameAlertWorkActionConfig.addActionParameter("The " + reportDSBean.getViewType().substring(0, 1).toLowerCase()+reportDSBean.getViewType().substring(1)
					+ " view name you have attempted to save is a duplicate of an existing view name."
					+ " Please enter a different view name.");
		}
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, gtnUIFrameAlertWorkActionConfig);
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}
