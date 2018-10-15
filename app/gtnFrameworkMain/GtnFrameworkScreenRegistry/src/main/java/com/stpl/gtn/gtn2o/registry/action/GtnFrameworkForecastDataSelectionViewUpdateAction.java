package com.stpl.gtn.gtn2o.registry.action;

import java.util.List;

import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
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
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.newarch.GtnWsForecastNewArchRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

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

		GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
        GtnServiceRegistryWsRequest serviceRegistryRequest = new GtnServiceRegistryWsRequest();
        GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();

        serviceRegistryBean.setRegisteredWebContext("/GtnSearchWebService");
        serviceRegistryBean.setUrl("/gtnForecastUpdateView");
        serviceRegistryBean.setModuleName(GtnFrameworkForecastingStringConstants.GENERAL_SEARCH);
        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
        generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
        generalRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
        serviceRegistryRequest.setGtnWsServiceRegistryBean(serviceRegistryBean);

        request.setGtnServiceRegistryWsRequest(serviceRegistryRequest);
        request.setGtnWsGeneralRequest(generalRequest);

        GtnUIFrameworkWebserviceResponse response = callWebservice(request, client);
        String viewValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial_Forecasting_saveViewName", componentId)
				.getV8StringFromField();

		GtnUIFrameWorkActionConfig infoAction = new GtnUIFrameWorkActionConfig();
		infoAction.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
		if (response.getGtnWsGeneralResponse().isSucess()) {
			if(!viewValue.equals(dataSelectionBean.getPrivateViewName()) || !viewValue.equals(dataSelectionBean.getPublicViewName()))
			{
				infoAction.addActionParameter("Information");
				infoAction.addActionParameter("You cannot update ViewName");
			}
			else {
			infoAction.addActionParameter("Information");
			infoAction.addActionParameter("You have successfully updated " + dataSelectionBean.getViewType()
					+ " View " + dataSelectionBean.getViewName());
			}
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

	public GtnUIFrameworkWebserviceResponse callWebservice(GtnUIFrameworkWebserviceRequest request,
			GtnUIFrameworkWebServiceClient client) {
		return client.callGtnWebServiceUrl(
                GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY_URL, GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY, request,
                GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		
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

