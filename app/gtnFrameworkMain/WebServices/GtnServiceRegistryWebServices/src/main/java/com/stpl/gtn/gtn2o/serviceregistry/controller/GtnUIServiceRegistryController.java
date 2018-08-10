package com.stpl.gtn.gtn2o.serviceregistry.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.queryengine.GtnFrameworkQueryExecutorBean;
import com.stpl.dependency.queryengine.GtnWsQueryType;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import com.stpl.gtn.gtn2o.datatype.GtnFrameworkDataType;
import com.stpl.gtn.gtn2o.serviceregistry.constants.GtnWsServiceRegistryConstants;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnUIServiceRegistryService;
import com.stpl.gtn.gtn2o.serviceregistry.webservices.GtnWsServiceRegistrySqlService;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.response.serviceregistry.GtnServiceRegistryWSResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;

@RestController
@RequestMapping(value = "/gtnServiceRegistry")
public class GtnUIServiceRegistryController extends GtnServiceRegistryImplClass {

	public GtnUIServiceRegistryController() {
		super();
	}

	@Autowired
	private GtnUIServiceRegistryController gtnUIServiceRegistryController;

	@Autowired
	private GtnValidateWsServiceRegistryController gtnValidateWsServiceRegistryController;

	@Autowired
	private GtnUIServiceRegistryService gtnUIServiceRegistryService;

	@Autowired
	private GtnWsServiceRegistrySqlService gtnWsServiceRegistrySqlService;

	@RequestMapping(value = "/registerWebservices", method = RequestMethod.POST)
	public void registerWebServices(@RequestBody GtnUIFrameworkWebserviceRequest request) {

		GtnQueryEngineWebServiceRequest queryEngineWebServiceRequest = new GtnQueryEngineWebServiceRequest();
		GtnFrameworkQueryExecutorBean queryExecutorBean = new GtnFrameworkQueryExecutorBean();

		GtnWsServiceRegistryBean serviceRegistryBean = request.getGtnServiceRegistryWsRequest()
				.getGtnWsServiceRegistryBean();

		Object[] params = new Object[3];
		params[0] = serviceRegistryBean.getHostName();
		params[1] = serviceRegistryBean.getPort();
		params[2] = serviceRegistryBean.getRegisteredWebContext();

		GtnFrameworkDataType[] dataType = { GtnFrameworkDataType.STRING, GtnFrameworkDataType.STRING,
				GtnFrameworkDataType.STRING };

		String registerQuery = GtnWsServiceRegistryConstants.INSERT_QUERY;

		queryExecutorBean.setParams(params);
		queryExecutorBean.setDataType(dataType);
		queryExecutorBean.setSqlQuery(registerQuery);
		queryExecutorBean.setQueryType(GtnWsQueryType.INSERTORUPDATEWITHPARAMS);

		queryEngineWebServiceRequest.setQueryExecutorBean(queryExecutorBean);

		RestTemplate restTemplate = new RestTemplate();
		GtnQueryEngineWebServiceResponse response = restTemplate.postForObject(
				getWebServiceEndpointBasedOnModule("/executeQuery", "queryEngine"), queryEngineWebServiceRequest,
				GtnQueryEngineWebServiceResponse.class);
	}

	@RequestMapping(value = "/serviceRegistryUIControllerMappingWs", method = RequestMethod.POST)
	public GtnUIFrameworkWebserviceResponse serviceRegistryUIControllerMappingWs(
			@RequestBody GtnUIFrameworkWebserviceRequest request) {
		GtnFrameworkDependencyLogger logger = gtnUIServiceRegistryController
				.logInformation(GtnUIServiceRegistryController.class);
		logger.debug("inside serviceRegistryUIControllerMappingWs");

		GtnUIFrameworkWebserviceResponse response = new GtnUIFrameworkWebserviceResponse();
		GtnServiceRegistryWSResponse gtnServiceRegistryWSResponse = new GtnServiceRegistryWSResponse();

		gtnServiceRegistryWSResponse = gtnValidateWsServiceRegistryController
				.serviceRegistryControllerToValidateWs(request);

		if (gtnServiceRegistryWSResponse.getGtnWsServiceRegistryBean().isAuthorizaionService()
				&& gtnServiceRegistryWSResponse.getGtnWsServiceRegistryBean().isRegisteredService()) {
			response = gtnUIServiceRegistryService.serviceRegistryUIServiceCallingWs(request);
			response.setGtnServiceRegistryWSResponse(gtnServiceRegistryWSResponse);
			return response;
		}
		logger.error("inside service Registry : WebService is not registered or authorization failed");
		return response;
	}

	public String getWebServiceEndpoint(String url) {
		return GtnFrameworkPropertyManager.getProperty("gtn.webservices.endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservices.endPointServiceName") + url;

	}

	public String getWebServiceEndpointBasedOnModule(String url, String moduleName) {
		return GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointUrl")
				+ GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointServiceName")
				+ url;

	}
}
