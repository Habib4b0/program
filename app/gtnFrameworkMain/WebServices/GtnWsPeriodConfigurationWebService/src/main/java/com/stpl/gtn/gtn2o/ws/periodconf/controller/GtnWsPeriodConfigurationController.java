package com.stpl.gtn.gtn2o.ws.periodconf.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.stpl.dependency.singleton.bean.GtnFrameworkSingletonObjectBean;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceComboBoxResponse;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

@RestController
@RequestMapping(value = "/gtnPeriodConfigurationController")
public class GtnWsPeriodConfigurationController{

	@RequestMapping(value = "/test", method = RequestMethod.GET)
	public boolean test() {
		return true;
	}

	GtnFrameworkSingletonObjectBean singletonObjectBean = GtnFrameworkSingletonObjectBean.getInstance();
	
	@RequestMapping(value = "/loadDate", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse loadDate(
			@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest) {
		GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();		
		GtnUIFrameworkWebserviceComboBoxResponse comboBoxResponse = new GtnUIFrameworkWebserviceComboBoxResponse();
		comboBoxResponse.setComboBoxList(singletonObjectBean.getPeriodConfigResultList());
		gtnUIFrameworkWebserviceResponse.setGtnUIFrameworkWebserviceComboBoxResponse(comboBoxResponse);

		return gtnUIFrameworkWebserviceResponse;
	}


	public String getWebServiceEndpointBasedOnModule(String url, String moduleName) {
		return GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointServiceName")
				+ url;

	}
	
}
