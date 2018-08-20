package com.stpl.gtn.gtn2o.serviceregistry.webservices.test;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnValidateWsServiceRegistryService;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/GtnServiceRegistry-test.xml" })
public class GtnValidateWsServiceRegistryServiceTest {

	private GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger
			.getGTNLogger(GtnValidateWsServiceRegistryServiceTest.class);

	@Autowired
	private GtnValidateWsServiceRegistryService gtnValidateWsServiceRegistryServiceTest;

	@Test
	public void serviceRegistryServiceToValidateWsIsRegistered() throws GtnFrameworkGeneralException {
		GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();
		serviceRegistryBean.setRegisteredWebContext("/gtnServiceRegistry");
		
		boolean isRegistered = gtnValidateWsServiceRegistryServiceTest.serviceRegistryServiceToValidateWsIsRegistered(serviceRegistryBean);
		gtnLogger.info("WebService Registered result----->" + isRegistered);
	}
}
