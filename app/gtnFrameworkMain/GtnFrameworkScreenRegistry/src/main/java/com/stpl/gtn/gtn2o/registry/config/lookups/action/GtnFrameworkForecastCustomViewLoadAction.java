/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.registry.config.lookups.action;

import java.util.ArrayList;
import java.util.List;

import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkForecastingStringConstants;
import com.stpl.gtn.gtn2o.registry.constants.GtnFrameworkScreenRegisteryConstants;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameworkActionShareable;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkDynamicClass;
import com.stpl.gtn.gtn2o.ws.GtnUIFrameworkWebServiceClient;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastInputBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.forecastingsearch.GtnGeneralSearchRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

public class GtnFrameworkForecastCustomViewLoadAction
		implements GtnUIFrameWorkAction, GtnUIFrameworkActionShareable, GtnUIFrameworkDynamicClass {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnFrameworkForecastCustomViewLoadAction.class);

	@Override
	public void configureParams(GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		GtnUIFrameWorkAction.super.configureParams(gtnUIFrameWorkActionConfig);
	}

	@Override
	public void doAction(String componentId, GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig)
			throws GtnFrameworkGeneralException {
		List<Object> actionParamsList = gtnUIFrameWorkActionConfig.getActionParameterList();
		try {
			String customerRelationValue = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(GtnFrameworkCommonConstants.SCREEN_REGISTRY_CF_CUST_SEL_REL)
					.getCaptionFromV8ComboBox();
			String customerRelationVersion = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_CUSTRELATIONVERSION)
					.getCaptionFromV8ComboBox();
			String productRelationValue = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODRELATIONSHIP)
					.getCaptionFromV8ComboBox();
			String productRelationVersion = GtnUIFrameworkGlobalUI
					.getVaadinBaseComponent(
							GtnFrameworkForecastingStringConstants.COMMERCIAL_FORECASTING_PRODRELATIONVERSION)
					.getCaptionFromV8ComboBox();
			if (customerRelationValue != "" || !customerRelationValue.isEmpty() && productRelationValue != ""
					|| !productRelationValue.isEmpty()) {
				GtnFrameworkForecastInputBean inputBean = new GtnFrameworkForecastInputBean();
				inputBean.setCustomerRelationSid(Integer.parseInt(customerRelationValue));
				inputBean.setProductRelationSid(Integer.parseInt(productRelationValue));
				inputBean.setCustomerRelationVersionNo(Integer.parseInt(customerRelationVersion));
				inputBean.setProductRelationVersionNo(Integer.parseInt(productRelationVersion));

				GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
				GtnServiceRegistryWsRequest serviceRegistryRequest = new GtnServiceRegistryWsRequest();
				GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();
				GtnGeneralSearchRequest searchRequest = new GtnGeneralSearchRequest();
				searchRequest.setInputBean(inputBean);

				serviceRegistryBean.setRegisteredWebContext("/GtnSearchWebService");
				serviceRegistryBean.setUrl(GtnFrameworkScreenRegisteryConstants.SEARCH_RESULTS_URL);
				serviceRegistryBean.setModuleName(GtnFrameworkForecastingStringConstants.GENERAL_SEARCH);
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
						GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY_URL,
						GtnFrameworkScreenRegisteryConstants.SERVICE_REGISTRY, request,
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
		
		} catch (GtnFrameworkGeneralException ex) {
			gtnLogger.error(ex.getMessage());
		}
	}

	@Override
	public GtnUIFrameWorkAction createInstance() {
		return GtnUIFrameWorkAction.super.createInstance();
	}

}
