package com.stpl.gtn.gtn2o.serviceregistry.controller;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.queryengine.bean.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.serviceregistry.constants.GtnWsServiceRegistryConstants;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnServiceRegistryRegisterWs;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnUIServiceRegistryService;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.forecastnewarch.GtnFrameworkForecastDataSelectionBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.serviceregistry.GtnServiceRegistryWSResponse;

@RestController
@RequestMapping(value = "/gtnServiceRegistry")
public class GtnUIServiceRegistryController extends GtnCommonWebServiceImplClass {

	public GtnUIServiceRegistryController() {
		super(GtnUIServiceRegistryController.class);
	}
	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private GtnValidateWsServiceRegistryController gtnValidateWsServiceRegistryController;

	@Autowired
	private GtnUIServiceRegistryService gtnUIServiceRegistryService;

	@Autowired
	private GtnServiceRegistryRegisterWs gtnServiceRegistryRegisterWs;

	@RequestMapping(value = "/registerWebservices", method = RequestMethod.POST)
	public void registerWebServices(@RequestBody GtnUIFrameworkWebserviceRequest request) {

		logger.debug("inside registerWebservices");
		long startTime = System.currentTimeMillis();
		Date currentStartTime = new Date(startTime);
		logger.debug("Strating Time to register WS:"
				+ new SimpleDateFormat(GtnWsServiceRegistryConstants.TIME).format(currentStartTime));
		logger.info("Webservice Url:"
				+ request.getGtnServiceRegistryWsRequest().getGtnWsServiceRegistryBean().getWebserviceEndPointUrl());
		logger.info("Webservice Registered Context:"
				+ request.getGtnServiceRegistryWsRequest().getGtnWsServiceRegistryBean().getRegisteredWebContext());

		gtnServiceRegistryRegisterWs.serviceRegistryRegisterWebServices(request);

		logger.info("webservices registered");
		logger.debug("End Time for registering WS:" + new SimpleDateFormat(GtnWsServiceRegistryConstants.TIME)
				.format(new Date(System.currentTimeMillis())));
		logger.info(
				"Total time for executing Registration:" + (double) (System.currentTimeMillis() - startTime) / (1000));
	}

	@RequestMapping(value = "/serviceRegistryUIControllerMappingWs", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse serviceRegistryUIControllerMappingWs(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		long currentStartTime = System.currentTimeMillis();
		logger.debug("inside serviceRegistryUIControllerMappingWs");
		logger.debug("Start Time to execute the request from UI: "
				+ new SimpleDateFormat(GtnWsServiceRegistryConstants.TIME).format(currentStartTime));
		logger.info("UserId:" + request.getGtnWsGeneralRequest().getUserId());
		logger.info("SessionId:" + request.getGtnWsGeneralRequest().getSessionId());

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();

		GtnServiceRegistryWSResponse gtnServiceRegistryWSResponse = gtnValidateWsServiceRegistryController
				.serviceRegistryControllerToValidateWs(request);

		logger.debug("Is Webservice Registered:"
				+ gtnServiceRegistryWSResponse.getGtnWsServiceRegistryBean().isRegisteredService());
		logger.debug("Is Webservice Authorized:"
				+ gtnServiceRegistryWSResponse.getGtnWsServiceRegistryBean().isAuthorizaionService());
		if (gtnServiceRegistryWSResponse.getGtnWsServiceRegistryBean().isAuthorizaionService()
				&& gtnServiceRegistryWSResponse.getGtnWsServiceRegistryBean().isRegisteredService()) {
			response = gtnUIServiceRegistryService.serviceRegistryUIServiceCallingWs(request);
			response.setGtnServiceRegistryWSResponse(gtnServiceRegistryWSResponse);
			logger.debug("End Time of getting response in service registry:" + System.currentTimeMillis());
			logger.info("Total time for executing Request:"
					+ (double) (System.currentTimeMillis() - currentStartTime) / (1000) + " secs");
			return response;
		}
		logger.error("inside service Registry : WebService is not registered or authorization failed");
		return response;
	}

	@RequestMapping(value = "/serviceRegistryWebservicesForRedirectToQueryEngine", method = RequestMethod.POST)
	public GtnQueryEngineWebServiceResponse registerWebservicesForRedirectToQueryEngine(
			@RequestBody GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest) {
		GtnCommonWebServiceImplClass webServiceImpl = new GtnUIServiceRegistryController();
		return webServiceImpl.callQueryEngineWithoutSecurityToken("/executeQuery", gtnQueryEngineWebServiceRequest);

	}

	public String getWebServiceEndpoint(String url) {
		return GtnFrameworkPropertyManager.getProperty("gtn.webservices.endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservices.endPointServiceName") + url;

	}

	

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		return null;
	}
	@RequestMapping(value = "/deleteView", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse deleteView(@RequestBody GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest)
	{
		logger.info("inside delete view method");
		String deleteViewQuery = "deleteView";
	GtnFrameworkForecastDataSelectionBean bean = gtnUIFrameworkWebserviceRequest.getGtnWsForecastNewArchRequest().getDataSelectionBean();
	GtnQueryEngineWebServiceResponse response = callQueryEngineforDeleteView(createQuery(readProperty(deleteViewQuery),bean));
	GtnUIFrameworkWebserviceResponse gtnUIFrameworkWebserviceResponse = new GtnUIFrameworkWebserviceResponse();
	int count = response.getQueryResponseBean().getResultInteger();
	GtnFrameworkForecastDataSelectionBean bean1 = new GtnFrameworkForecastDataSelectionBean();
	bean1.setResultCount(count);
	gtnUIFrameworkWebserviceResponse.setGtnFrameworkForecastDataSelectionBean(bean1);
		return gtnUIFrameworkWebserviceResponse;
	}
	private GtnQueryEngineWebServiceResponse callQueryEngineforDeleteView(GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest)
	{
		
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate.postForObject(getWebServiceEndpointBasedOnModule("/gtnServiceRegistry/serviceRegistryWebservicesForRedirectToQueryEngine"
					,"serviceRegistry"),gtnQueryEngineWebServiceRequest,
				GtnQueryEngineWebServiceResponse.class);
		
	}

	private GtnQueryEngineWebServiceRequest createQuery(String query, GtnFrameworkForecastDataSelectionBean bean) {
		
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();
		queryExecutorBean.setSqlQuery(query);
		queryExecutorBean.setQueryType("INSERTORUPDATEWITHPARAMS");
		Object[] params= {bean.getViewId(),bean.getUserId()};
		GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.INTEGER,GtnFrameworkDataType.STRING  };
		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(dataType);
		GtnQueryEngineWebServiceRequest gtnQueryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		gtnQueryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);
		return gtnQueryEngineWebServiceRequest;
	}

	private String readProperty(String deleteViewQuery) {
		String query = gtnWsSqlService.getQuery(deleteViewQuery);
		logger.info("inside delete view method query------->" + query);
		return query;
		

	}
	public String getWebServiceEndpointBasedOnModule(String url, String moduleName) {
		
		return GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointUrl")
		+ GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointServiceName")
		+ url;

}
}
