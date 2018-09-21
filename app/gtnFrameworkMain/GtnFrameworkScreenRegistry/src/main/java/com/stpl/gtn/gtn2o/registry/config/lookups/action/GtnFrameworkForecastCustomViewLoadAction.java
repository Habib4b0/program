/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.registry.config.lookups.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastInputBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastingsearch.GtnGeneralSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

import java.util.ArrayList;
import java.util.List;

public class GtnFrameworkForecastCustomViewLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkAction.super.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		String customerRelationValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial_Forecasting_customerSelectionRelationship")
				.getCaptionFromV8ComboBox();
		String customerRelationVersion = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("Commercial_Forecasting_customerRelationshipVersion").getCaptionFromV8ComboBox();
		String productRelationValue = GtnUIFrameworkGlobalUI.getVaadinBaseComponent("Commercial Forecasting_prodrelationship")
				.getCaptionFromV8ComboBox();
		String productRelationVersion = GtnUIFrameworkGlobalUI
				.getVaadinBaseComponent("Commercial_Forecasting_productRelationshipVersion").getCaptionFromV8ComboBox();
		GtnFrameworkForecastInputBean inputBean = new GtnFrameworkForecastInputBean();
		inputBean.setCustomerRelationSid(Integer.valueOf(customerRelationValue));
		inputBean.setProductRelationSid(Integer.valueOf(productRelationValue));
		inputBean.setCustomerRelationVersionNo(Integer.valueOf(customerRelationVersion));
		inputBean.setProductRelationVersionNo(Integer.valueOf(productRelationVersion));

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest serviceRegistryRequest = new GtnServiceRegistryWsRequest();
		GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();
		GtnGeneralSearchRequest searchRequest = new GtnGeneralSearchRequest();
		searchRequest.setInputBean(inputBean);

		serviceRegistryBean.setRegisteredWebContext("/GtnSearchWebService");
		serviceRegistryBean.setUrl("/gtnSearch");
		serviceRegistryBean.setModuleName("generalSearch");
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setUserId(GtnUIFrameworkGlobalUI.getCurrentUser());
		generalRequest.setSessionId(String.valueOf(GtnUIFrameworkGlobalUI.getSessionProperty("sessionId")));
		serviceRegistryRequest.setGtnWsServiceRegistryBean(serviceRegistryBean);

		GtnWsSearchRequest webserviceSearchRequest = new GtnWsSearchRequest();
		webserviceSearchRequest.setSearchQueryName(actionParamsList.get(5).toString());

		request.setGtnServiceRegistryWsRequest(serviceRegistryRequest);
		request.setGtnWsGeneralRequest(generalRequest);
		request.setGtnGeneralSearchRequest(searchRequest);
		request.setGtnWsSearchRequest(webserviceSearchRequest);

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebServiceClient().callGtnWebServiceUrl(
				"/gtnServiceRegistry/serviceRegistryUIControllerMappingWs", "serviceRegistry", request,
				GtnUIFrameworkGlobalUI.getGtnWsSecurityToken());
		if (response.getGtnUIFrameworkWebserviceComboBoxResponse() != null) {
			List<String> valueList = new ArrayList<>(
					response.getGtnUIFrameworkWebserviceComboBoxResponse().getItemValueList());
			List<String> responseIdList = new ArrayList<>(
					response.getGtnUIFrameworkWebserviceComboBoxResponse().getItemCodeList());
			List<Integer> idList = new ArrayList<>();
			for (String string : responseIdList) {
				idList.add(Integer.valueOf(string));
			}
			GtnUIFrameworkGlobalUI.getVaadinBaseComponent(actionParamsList.get(6).toString())
					.loadItemsToCombobox(valueList, idList);
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return GtnUIFrameWorkAction.super.createInstance();
	}

}
