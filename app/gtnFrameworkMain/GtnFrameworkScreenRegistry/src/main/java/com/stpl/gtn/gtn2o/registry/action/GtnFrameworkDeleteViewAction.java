package com.stpl.gtn.gtn2o.registry.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.forecast.newarch.GtnWsForecastNewArchRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnFrameworkDeleteViewAction 
implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass
																							{
	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnFrameworkDeleteViewAction.class);
	
	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		
		List<Object> actionParamList = gtnUIFrameWorkActionConfig.getActionParameterList();
		GtnWsRecordBean recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent(actionParamList.get(1).toString()).getComponentData().getCustomData();
		if (recordBean == null) {
			recordBean = (GtnWsRecordBean) GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(actionParamList.get(2).toString()).getComponentData().getCustomData();
		}
		
		Integer viewId = (Integer) recordBean.getPropertyValueByIndex(4);
		GtnFrameworkForecastDataSelectionBean gtnFrameworkForecastDataSelectionBean = new GtnFrameworkForecastDataSelectionBean();
		gtnFrameworkForecastDataSelectionBean.setViewId(viewId);
		logger.info("View id" + viewId +"user id " + recordBean);
		
		GtnWsForecastNewArchRequest gtnWsForecastNewArchRequest = new GtnWsForecastNewArchRequest();
		gtnWsForecastNewArchRequest.setDataSelectionBean(gtnFrameworkForecastDataSelectionBean);
		
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastNewArchRequest(gtnWsForecastNewArchRequest);
		request.setGtnWsGeneralRequest(generalRequest);
		
		GtnUIFrameworkWebserviceResponse response =  new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl
				(
						"/gtnServiceRegistry/serviceRegistryWebservicesForRedirectToQueryEngine","deleteView",
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken()
						);


	}

	
}
