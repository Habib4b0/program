package com.stpl.gtn.gtn2o.serviceregistry.webservices.test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.gtn.gtn2o.serviceregistry.constants.GtnWsServiceRegistryConstants;
import com.stpl.gtn.gtn2o.serviceregistry.controller.GtnUIServiceRegistryController;
import com.stpl.gtn.gtn2o.serviceregistry.controller.GtnValidateWsServiceRegistryController;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnServiceRegistryAuthorizationService;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnServiceRegistryRegisterWs;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnUIServiceRegistryService;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnValidateWsServiceRegistryService;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;


@Ignore
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/main/resources/GtnServiceRegistry-test.xml" })
public class GtnServiceRegistryRegisterWsTest {
	
	public GtnServiceRegistryRegisterWsTest()
	{
		//nothing is there
	}

	private GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger
			.getGTNLogger(GtnServiceRegistryRegisterWsTest.class);

	@Autowired
	private GtnServiceRegistryRegisterWs gtnServiceRegistryRegisterWs;
	
	@Autowired
	private GtnServiceRegistryAuthorizationService gtnServiceRegistryAuthorizationService;

	@Autowired
	private GtnUIServiceRegistryController gtnUIServiceRegistryController;
	@Autowired
	private GtnUIServiceRegistryService gtnUIServiceRegistryService;
	@Autowired
	private GtnValidateWsServiceRegistryService gtnValidateWsServiceRegistryServiceTest;
	@Autowired
	private GtnValidateWsServiceRegistryController gtnValidateWsServiceRegistryController;

	
	@Before
	public void propertyTest() {
		System.setProperty("com.stpl.gtnframework.base.path", "E:/GTN Server Setup/conf");

	}
	
	@Test
	public void serviceRegistryServiceToValidateWsIsRegistered1() throws GtnFrameworkGeneralException {
		GtnWsServiceRegistryBean serviceRegistryBean = new GtnWsServiceRegistryBean();
		serviceRegistryBean.setRegisteredWebContext("/GtnWsQueryEngineWebService");
		serviceRegistryBean.setModuleName("queryEngine");
		serviceRegistryBean.setAuthorizaionService(true);
		boolean isRegistered = gtnValidateWsServiceRegistryServiceTest.serviceRegistryServiceToValidateWsIsRegistered(serviceRegistryBean);
		assertTrue(isRegistered);
	}
	
	@Test
	public void serviceRegistryTest()
	{
		assertFalse(gtnServiceRegistryRegisterWs.registerWs()!=null);
	}
	@Test
	public void authorizationTest()
	{
		assertTrue(gtnServiceRegistryAuthorizationService.serviceRegistryServiceToAuthorizeWs());
	}
	
	@Test
	public void serviceRegistryWsTest()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();

		GtnWsServiceRegistryBean webServiceRegistryBean = new GtnWsServiceRegistryBean();
		webServiceRegistryBean.setWebserviceEndPointUrl(GtnWsServiceRegistryConstants.PORT);
		webServiceRegistryBean.setRegisteredWebContext(GtnWsServiceRegistryConstants.PERIOD_CONF);
		gtnLogger.info(GtnWsServiceRegistryConstants.REGISTER_WEBSERVICE + webServiceRegistryBean.getRegisteredWebContext());
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegistryBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		gtnUIServiceRegistryController.registerWebServices(request);		
	}
	
	@Test
	public void serviceRegistryValidate()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();

		GtnWsServiceRegistryBean webServiceRegistryBean = new GtnWsServiceRegistryBean();
		webServiceRegistryBean.setWebserviceEndPointUrl(GtnWsServiceRegistryConstants.PORT);
		webServiceRegistryBean.setRegisteredWebContext(GtnWsServiceRegistryConstants.PERIOD_CONF);
		gtnLogger.info(GtnWsServiceRegistryConstants.REGISTER_WEBSERVICE + webServiceRegistryBean.getRegisteredWebContext());
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegistryBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		

		GtnWsGeneralRequest  gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setSessionId(GtnWsServiceRegistryConstants.SESSION_ID);
		gtnWsGeneralRequest.setUserId(GtnWsServiceRegistryConstants.USER_ID);
		
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		assertFalse(gtnValidateWsServiceRegistryController.serviceRegistryControllerToValidateWs(request)==null);
	}
	@Test
	public void test1()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();

		GtnWsServiceRegistryBean webServiceRegistryBean = new GtnWsServiceRegistryBean();
		webServiceRegistryBean.setWebserviceEndPointUrl(GtnWsServiceRegistryConstants.PORT);
		webServiceRegistryBean.setRegisteredWebContext(GtnWsServiceRegistryConstants.PERIOD_CONF);
		webServiceRegistryBean.setUrl(GtnWsServiceRegistryConstants.LOAD_DATE_URL);
		webServiceRegistryBean.setModuleName("periodConfiguration");
		gtnLogger.info(GtnWsServiceRegistryConstants.REGISTER_WEBSERVICE+ webServiceRegistryBean.getRegisteredWebContext());
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegistryBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		

		GtnWsGeneralRequest  gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setSessionId(GtnWsServiceRegistryConstants.SESSION_ID);
		gtnWsGeneralRequest.setUserId(GtnWsServiceRegistryConstants.USER_ID);
		
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		assertTrue(gtnUIServiceRegistryService.serviceRegistryUIServiceCallingWs(request)!=null);
	}
	@Test
	public void queryEngineRedirectTest()
	{
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setQueryType("Select");
		String sqlQuery = "select * from company_master";
		queryExecutorBean.setSqlQuery(sqlQuery);
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		assertFalse(gtnUIServiceRegistryController.registerWebservicesForRedirectToQueryEngine(gtnQueryEngineWebServiceRequest)==null);
	}
	
	

	@Test
	public void serviceRegistryForUIMappingWs()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();
		
		GtnWsServiceRegistryBean webServiceRegistryBean = new GtnWsServiceRegistryBean();
		webServiceRegistryBean.setWebserviceEndPointUrl(GtnWsServiceRegistryConstants.PORT);
		webServiceRegistryBean.setRegisteredWebContext(GtnWsServiceRegistryConstants.PERIOD_CONF);
		webServiceRegistryBean.setUrl(GtnWsServiceRegistryConstants.LOAD_DATE_URL);
		webServiceRegistryBean.setModuleName("periodConfiguration");
		gtnLogger.info(GtnWsServiceRegistryConstants.REGISTER_WEBSERVICE + webServiceRegistryBean.getRegisteredWebContext());
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegistryBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
	
		GtnWsGeneralRequest  gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setSessionId(GtnWsServiceRegistryConstants.SESSION_ID);
		gtnWsGeneralRequest.setUserId(GtnWsServiceRegistryConstants.USER_ID);
		
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = new GtnUIFrameworkWebserviceRequest();
		gtnUIFrameworkWebserviceRequest.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegistryBean);
		gtnUIFrameworkWebserviceRequest.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		
		
		assertFalse( gtnUIServiceRegistryController.serviceRegistryUIControllerMappingWs(gtnUIFrameworkWebserviceRequest)==null);
		 
	}
	@Ignore
	@Test
	public void exceptionTest()
	{
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();

		GtnWsServiceRegistryBean webServiceRegistryBean = new GtnWsServiceRegistryBean();
		webServiceRegistryBean.setWebserviceEndPointUrl(GtnWsServiceRegistryConstants.PORT);
		webServiceRegistryBean.setRegisteredWebContext(GtnWsServiceRegistryConstants.PERIOD_CONF);
		webServiceRegistryBean.setUrl(GtnWsServiceRegistryConstants.LOAD_DATE_URL);
		gtnLogger.info(GtnWsServiceRegistryConstants.REGISTER_WEBSERVICE + webServiceRegistryBean.getRegisteredWebContext());
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegistryBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		

		GtnWsGeneralRequest  gtnWsGeneralRequest = new GtnWsGeneralRequest();
		gtnWsGeneralRequest.setSessionId(GtnWsServiceRegistryConstants.SESSION_ID);
		gtnWsGeneralRequest.setUserId(GtnWsServiceRegistryConstants.USER_ID);
		
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
		
		assertFalse(gtnValidateWsServiceRegistryController.serviceRegistryControllerToValidateWs(request)==null);
	}
	
	@Test
	public void urlTest()
	{
		String url = "/gtnPeriodConfigurationController/loadDate";
		assertFalse(gtnUIServiceRegistryController.getWebServiceEndpoint(url)==null);
		assertTrue(gtnUIServiceRegistryController.registerWs()==null);
		assertTrue(gtnValidateWsServiceRegistryServiceTest.registerWs()==null);
	}
}