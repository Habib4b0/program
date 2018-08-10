package com.stpl.gtn.gtn2o.registry.action;

import java.util.List;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

public class GtnLandingScreenFromAndToPeriodLoadAction
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
            
            GtnUIFrameworkWebServiceClient client = new GtnUIFrameworkWebServiceClient();
            GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
            GtnServiceRegistryWsRequest serviceRegistryRequest = new GtnServiceRegistryWsRequest();
            GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();
            
            serviceRegistryBean.setRegisteredWebContext(actionParamList.get(1).toString());
            serviceRegistryBean.setUrl(actionParamList.get(2).toString());
            serviceRegistryBean.setModuleName(actionParamList.get(3).toString());
            
            serviceRegistryRequest.setGtnWsServiceRegistryBean(serviceRegistryBean);
            
            request.setGtnServiceRegistryWsRequest(serviceRegistryRequest);
            
            GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl("/gtnServiceRegistry/serviceRegistryUIControllerMappingWs", "serviceRegistry", request, GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
            
            GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(1).toString());
	}
	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
