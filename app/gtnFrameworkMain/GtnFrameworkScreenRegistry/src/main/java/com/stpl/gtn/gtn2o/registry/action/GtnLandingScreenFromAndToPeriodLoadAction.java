package com.stpl.gtn.gtn2o.registry.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.registry.util.GtnFrameworkAlertUtil;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

public class GtnLandingScreenFromAndToPeriodLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnLandingScreenFromAndToPeriodLoadAction.class);

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
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		serviceRegistryRequest.setGtnWsServiceRegistryBean(serviceRegistryBean);

		request.setGtnServiceRegistryWsRequest(serviceRegistryRequest);
		request.setGtnWsGeneralRequest(generalRequest);

		GtnUIFrameworkWebserviceResponse response = client.callGtnWebServiceUrl(
				"/gtnServiceRegistry/serviceRegistryUIControllerMappingWs", "serviceRegistry", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (response == null) {
			GtnFrameworkAlertUtil alertAction = new GtnFrameworkAlertUtil();
			alertAction.throwAlertUtil("", actionParamList.get(2).toString());
		}

		List<String> fromPeriodItemValueList = new ArrayList<>(
				response.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList());
		List<Integer> fromPeriodItemCodeList = new ArrayList<>();
		for (String integer : response.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList()) {
			fromPeriodItemCodeList.add(Integer.valueOf(integer));
		}

		List<String> toPeriodItemValueList = new ArrayList<>();
		toPeriodItemValueList.add(fromPeriodItemValueList.get(fromPeriodItemValueList.size() - 1));

		List<Integer> toPeriodItemCodeList = new ArrayList<>();
		toPeriodItemCodeList.add(fromPeriodItemCodeList.get(fromPeriodItemCodeList.size() - 1));

		if (actionParamList.get(4).toString().contains("DataSelection")) {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(4).toString(), componentId)
					.loadItemsToCombobox(fromPeriodItemValueList, fromPeriodItemCodeList);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(5).toString(), componentId)
					.addAllItemsToComboBox(toPeriodItemValueList, toPeriodItemCodeList);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(4).toString(),componentId)
					.loadV8ComboBoxComponentValue(fromPeriodItemCodeList.get(0));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(5).toString(),componentId)
					.loadV8ComboBoxComponentValue(toPeriodItemCodeList.get(toPeriodItemCodeList.size() - 1));
		} else {
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(4).toString())
					.loadItemsToCombobox(fromPeriodItemValueList, fromPeriodItemCodeList);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(5).toString())
					.addAllItemsToComboBox(toPeriodItemValueList, toPeriodItemCodeList);
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(4).toString())
					.loadV8ComboBoxComponentValue(fromPeriodItemCodeList.get(0));
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamList.get(5).toString())
					.loadV8ComboBoxComponentValue(toPeriodItemCodeList.get(toPeriodItemCodeList.size() - 1));

		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return this;
	}
}
