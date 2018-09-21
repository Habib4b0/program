package com.stpl.gtn.gtn2o.ws.search.implementation;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastInputBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.search.searchinterface.SearchInterface;

public class CustomViewSearch implements SearchInterface {

	@Override
	public GtnUIFrameworkWebserviceResponse getSearch(GtnUIFrameworkWebserviceRequest gtnUiFrameworkWebservicerequest,
			String query) {
		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnFrameworkForecastInputBean inputBean = gtnUiFrameworkWebservicerequest.getGtnGeneralSearchRequest()
				.getInputBean();
		Object[] params = { inputBean.getCustomerRelationSid(), inputBean.getProductRelationSid(),
				inputBean.getCustomerRelationVersionNo(), inputBean.getProductRelationVersionNo() };
		GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER,
				GtnFrameworkDataType.INTEGER, GtnFrameworkDataType.INTEGER };
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("SELECTWITHPARAMS");
		queryExecutorBean.setSqlQuery(query);
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(dataType);
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		RestTemplate restTemplate = new RestTemplate();
		GtnQueryEngineWebServiceResponse queryResponse = restTemplate.postForObject(
				getWebServiceEndpointBasedOnModule(
						"/gtnServiceRegistry/serviceRegistryWebservicesForRedirectToQueryEngine", "serviceRegistry"),
				gtnQueryEngineWebServiceRequest, GtnQueryEngineWebServiceResponse.class);
		List<Object[]> resultList = queryResponse.getQueryResponseBean().getResultList();
		GtnUIFrameworkWebserviceComboBoxResponse comboxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		comboxResponse.setComboBoxList(resultList);
		response.setGtnUIFrameworkWebserviceComboBoxResponse(comboxResponse);
		return response;
	}

	public String getWebServiceEndpointBasedOnModule(String url, String moduleName) {
		return GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointServiceName")
				+ url;
	}

}
