package com.stpl.gtn.gtn2o.serviceregistry.webservices.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnServiceRegistryRegisterWs;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/GtnServiceRegistry-test.xml" })
public class GtnServiceRegistryRegisterWsTest {

	private GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger
			.getGTNLogger(GtnServiceRegistryRegisterWsTest.class);

	@Autowired
	private GtnServiceRegistryRegisterWs gtnServiceRegistryRegisterWs;

	@Test
	public void serviceRegistryServiceToValidateWsIsRegistered() throws GtnFrameworkGeneralException {

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest serviceRegistryWsRequest = new GtnServiceRegistryWsRequest();
		GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();
		
		serviceRegistryBean.setWebserviceEndPointUrl("http://localhost:8092");
		serviceRegistryBean.setRegisteredWebContext("/GtnWsPeriodConfigurationWebService");
		serviceRegistryWsRequest.setGtnWsServiceRegistryBean(serviceRegistryBean);
		request.setGtnServiceRegistryWsRequest(serviceRegistryWsRequest);
		
		gtnServiceRegistryRegisterWs.serviceRegistryRegisterWebServices(request);
		gtnLogger.info("----->WebService Registered Successfully");
	}
}