package com.stpl.gtn.gtn2o.registry.action;

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
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.newarch.GtnWsForecastNewArchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkForecastDataSelectionViewUpdateAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		return;
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnFrameworkForecastDataSelectionBean dataSelectionBean = (GtnFrameworkForecastDataSelectionBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(1).toString()).getComponentData().getSharedPopupData();
		dataSelectionBean.setViewName(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(2).toString(), componentId).getV8StringFromField()));
		dataSelectionBean.setViewType(String.valueOf(GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamsList.get(3).toString(), componentId).getV8StringFromField()));
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsForecastNewArchRequest forecastRequest = new GtnWsForecastNewArchRequest();
		forecastRequest.setDataSelectionBean(dataSelectionBean);
		request.setGtnWsForecastNewArchRequest(forecastRequest);
		GtnUIFrameworkWebserviceResponse response = callWebservice(request);
		GtnUIFrameWorkActionConfig infoAction = new GtnUIFrameWorkActionConfig();
		infoAction.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
		if (response.getGtnWsGeneralResponse().isSucess()) {
			infoAction.addActionParameter("Information");
			infoAction.addActionParameter("You have successfully updated " + dataSelectionBean.getViewType()
					+ " View " + dataSelectionBean.getViewName());
		} else {
			infoAction.addActionParameter("Cannot update public view");
			infoAction.addActionParameter("You cannot update " +dataSelectionBean.getViewType()+" View " + "("+dataSelectionBean.getViewName()+")"
					+ " because it was created by another user. \n" + "You can choose to save a new profile under a different profile name." );
		}
		GtnUIFrameworkActionExecutor.executeSingleAction(componentId, infoAction);
		
		if (response.getGtnWsGeneralResponse().isSucess()) {
			GtnUIFrameWorkActionConfig closePopupAction = new GtnUIFrameWorkActionConfig();
			closePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
			closePopupAction.addActionParameter("forecastDsSaveViewLookUp");
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, closePopupAction);
		}
	}

	public GtnUIFrameworkWebserviceResponse callWebservice(GtnUIFrameworkWebserviceRequest request) {
		return  new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				GtnWsReportConstants.GTN_REPORT_SERVICE + GtnWsReportConstants.GTN_WS_REPORT_UPDATEVIEW_SERVICE,
				"report", request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}

}

