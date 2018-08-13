package com.stpl.gtn.gtn2o.ws.periodconf.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.periodconf.service.GtnWsPeriodConfigurationService;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@RestController
@RequestMapping(value = "/gtnPeriodConfigurationController")
public class GtnWsPeriodConfigurationController{
	
	@Autowired
	private GtnWsPeriodConfigurationService gtnWsPeriodConfigurationService;

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public boolean test() {
		return true;
	}

	public void init() {
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();
		GtnWsServiceRegistryBean webServiceRegisterBean = new GtnWsServiceRegistryBean();
		
		webServiceRegisterBean.setHostName("http://localhost");
		webServiceRegisterBean.setPort("8092");
		webServiceRegisterBean.setRegisteredWebContext("/GtnWsPeriodConfigurationWebService");
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegisterBean);
		
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		
		RestTemplate restTemplate = new RestTemplate();
		 restTemplate.postForObject(
				getWebServiceEndpointBasedOnModule("/gtnServiceRegistry/registerWebservices", "serviceRegistry"), request,
				GtnUIFrameworkWebserviceResponse.class);
	}
	
	@RequestMapping(value = "/loadDate", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadDate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
		GtnWsGeneralRequest generalRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();
		List<Object[]> resultList = gtnWsPeriodConfigurationService.loadDate(generalRequest);

		
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		comboBoxResponse.setComboBoxList(resultList);
		gtnUIFrameworkWebserviceResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);

		return gtnUIFrameworkWebserviceResponse;
	}


	public String getWebServiceEndpointBasedOnModule(String url, String moduleName) {
		return GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointServiceName")
				+ url;

	}
	
}
