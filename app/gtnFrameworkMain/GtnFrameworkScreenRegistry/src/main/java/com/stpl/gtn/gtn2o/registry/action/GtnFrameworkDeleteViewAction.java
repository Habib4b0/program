package com.stpl.gtn.gtn2o.registry.action;

import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
import java.util.Arrays;
import java.util.List;

import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.action.executor.GtnUIFrameworkActionExecutor;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
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
		
		int viewId =recordBean.getIntegerPropertyByIndex(16);
		
		GtnFrameworkForecastDataSelectionBean gtnFrameworkForecastDataSelectionBean = new GtnFrameworkForecastDataSelectionBean();
		gtnFrameworkForecastDataSelectionBean.setViewId(viewId);
		gtnFrameworkForecastDataSelectionBean.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
	
		
		GtnWsForecastNewArchRequest gtnWsForecastNewArchRequest = new GtnWsForecastNewArchRequest();
		gtnWsForecastNewArchRequest.setDataSelectionBean(gtnFrameworkForecastDataSelectionBean);
		
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		request.setGtnWsForecastNewArchRequest(gtnWsForecastNewArchRequest);
		request.setGtnWsGeneralRequest(generalRequest);
		
		GtnUIFrameworkWebserviceResponse response = callWebService(request);

		logger.info("Number of rows affected-------->" + response.getGtnFrameworkForecastDataSelectionBean().getResultCount());
		
		if(response.getGtnFrameworkForecastDataSelectionBean().getResultCount() == 1)
		{
			GtnUIFrameWorkActionConfig resetAction = new GtnUIFrameWorkActionConfig();
			resetAction.setActionType(GtnUIFrameworkActionType.CUSTOM_ACTION);
			resetAction.setActionParameterList(
					
					Arrays.asList(
						
							GtnFrameworkScreenRegistryResetAction.class.getName(),
							"Commercial Forecasting_privateViewLookup",
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING + "_" + GtnFrameworkScreenRegisteryConstants.ADD_COMPANY_COMBOX_ID,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING + "_" + GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_NAME,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING + "_" + GtnFrameworkScreenRegisteryConstants.ADD_BUSINESS_UNIT_COMPONENT_ID,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING + "_" + GtnFrameworkForecastingStringConstants.FORECAST_PROJECTION_DESCRIPTION,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING + "_" + GtnFrameworkForecastingStringConstants.PUBLIC_VIEW,
							GtnFrameworkScreenRegisteryConstants.FORECAST_CUST_HIER_LOOKUP_CONTROL_POP,
							GtnFrameworkCommonConstants.SCREEN_REGISTRY_CF_CUST_SEL_REL,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTOMERSELECTIONFORECASTLEVEL,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTGROUP,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTOMERDUALLISTBOX,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODHIERNAME,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODRELATIONSHIP,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODFORECASTLEVEL,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODUCTDUALLISTBOX, GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODUCTGROUP,
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING + "_" + GtnFrameworkForecastingStringConstants.PROFILE_MODE

					));
			
			GtnUIFrameworkActionExecutor.executeSingleAction(componentId, resetAction);
		}
	}

	public GtnUIFrameworkWebserviceResponse callWebService(GtnUIFrameworkWebserviceRequest request) {
		return  new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl
			("/deleteView",GtnFrameworkForecastingStringConstants.GENERAL_SEARCH,
						request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
	}

	
}
