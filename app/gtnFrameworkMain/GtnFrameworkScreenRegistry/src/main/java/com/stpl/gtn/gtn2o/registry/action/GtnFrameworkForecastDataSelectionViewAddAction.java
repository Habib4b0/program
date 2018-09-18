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
import com.stpl.gtn.gtn2o.ws.forecast.constants.GtnWsForecastConstants;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

import com.stpl.gtn.gtn2o.ws.report.constants.GtnWsReportConstants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.newarch.GtnWsForecastNewArchRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

public class GtnFrameworkForecastDataSelectionViewAddAction 




	implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

		private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastDataSelectionViewAddAction.class);

		@Override
		public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
				throws GtnFrameworkGeneralException {
			logger.debug("configure params");
		}

		@Override
		public void doAction(String componentId, GtnUIFrameWorkActionConfig actionConfig)
				throws GtnFrameworkGeneralException {
			List<Object> actionParameterList = actionConfig.getActionParameterList();
			GtnFrameworkForecastDataSelectionBean forecastDSBean = (GtnFrameworkForecastDataSelectionBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParameterList.get(1).toString()).getComponentData().getSharedPopupData();
			forecastDSBean.setViewName(String.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParameterList.get(2).toString(), componentId).getV8StringFromField()));
			forecastDSBean.setViewType(String.valueOf(GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParameterList.get(3).toString(), componentId).getV8StringFromField()));
			GtnUIFrameworkWebserviceRequest webServiceRequest = new GtnUIFrameworkWebserviceRequest();
			GtnWsForecastNewArchRequest gtnWsForecastNewArchRequest = new GtnWsForecastNewArchRequest();
			gtnWsForecastNewArchRequest.setDataSelectionBean(forecastDSBean);
			webServiceRequest.setGtnWsForecastNewArchRequest(gtnWsForecastNewArchRequest);
			 GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
		        GtnServiceRegistryWsRequest serviceRegistryRequest = new GtnServiceRegistryWsRequest();
		        GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();

		        serviceRegistryBean.setRegisteredWebContext("/GtnSearchWebService");
		        serviceRegistryBean.setUrl("/gtnForecastSaveView");
		        serviceRegistryBean.setModuleName("generalSearch");
		        GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		        generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		        generalRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		        serviceRegistryRequest.setGtnWsServiceRegistryBean(serviceRegistryBean);

		        webServiceRequest.setGtnServiceRegistryWsRequest(serviceRegistryRequest);
		        webServiceRequest.setGtnWsGeneralRequest(generalRequest);

		        GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
		                "/gtnServiceRegistry/serviceRegistryUIControllerMappingWs", "serviceRegistry", webServiceRequest,
		                GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
			
			GtnUIFrameWorkActionConfig gtnUIFrameAlertWorkActionConfig = new GtnUIFrameWorkActionConfig();
			gtnUIFrameAlertWorkActionConfig.setActionType(GtnUIFrameworkActionType.INFO_ACTION);
			if (response.getGtnWsGeneralResponse().isSucess()) {

				GtnUIFrameWorkActionConfig closePopupAction = new GtnUIFrameWorkActionConfig();
				closePopupAction.setActionType(GtnUIFrameworkActionType.POPUP_CLOSE_ACTION);
				closePopupAction.addActionParameter("forecastDsSaveViewLookUp");
				GtnUIFrameworkActionExecutor.executeSingleAction(componentId, closePopupAction);
				
				gtnUIFrameAlertWorkActionConfig.addActionParameter("View Added Successfully");
				gtnUIFrameAlertWorkActionConfig.addActionParameter("You have successfully added " + forecastDSBean.getViewType() + " View "
						+ forecastDSBean.getViewName());
				
			} else {
				gtnUIFrameAlertWorkActionConfig.addActionParameter("Duplicate View Name");
				gtnUIFrameAlertWorkActionConfig.addActionParameter("The " + forecastDSBean.getViewType().substring(0, 1).toLowerCase()+forecastDSBean.getViewType().substring(1)
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

