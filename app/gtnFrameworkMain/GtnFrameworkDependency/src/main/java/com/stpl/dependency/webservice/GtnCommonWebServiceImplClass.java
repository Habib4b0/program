package com.stpl.dependency.webservice;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.concurrency.GtnWebserviceFailureRunnable;
import com.stpl.dependency.webservice.constants.GtnWsFrameworkDependencyConstatnts;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.manager.GtnWsSecurityManager;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.request.serviceregistry.GtnServiceRegistryWsRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;
import com.stpl.gtn.gtn2o.ws.serviceregistry.bean.GtnWsServiceRegistryBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.springframework.web.client.RestTemplate;

public abstract class GtnCommonWebServiceImplClass {

    public GtnFrameworkDependencyLogger logger;
    private final long staticTime = System.currentTimeMillis();
    private final ExecutorService service = Executors.newCachedThreadPool();

    public GtnCommonWebServiceImplClass(Class<?> className) {
        logger = GtnFrameworkDependencyLogger.getGTNLogger(className);
    }

    public String readPropertyFile(String propertyFile) {
        return "readPropertyFile:" + propertyFile;
    }


    public GtnQueryEngineWebServiceResponse callQueryEngine(String url, GtnQueryEngineWebServiceRequest request,
            GtnWsSecurityToken securityToken) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            addSecurityToken(request);
            GtnQueryEngineWebServiceResponse webServiceResponse = restTemplate.postForObject(
                    getWebServiceEndpointBasedOnModule(url, "queryEngine"), request,
                    GtnQueryEngineWebServiceResponse.class);
            return webServiceResponse;
        } catch (Exception e) {
            logger.error("Exception in web service call", e);
            return null;
        }

    }

    public GtnQueryEngineWebServiceResponse callQueryEngineWithoutSecurityToken(String url,
            GtnQueryEngineWebServiceRequest request) {
        try {

            RestTemplate restTemplate = new RestTemplate();
            addSecurityToken(request);
            GtnQueryEngineWebServiceResponse webServiceResponse = restTemplate.postForObject(
                    getWebServiceEndpointBasedOnModule(url, "queryEngine"), request,
                    GtnQueryEngineWebServiceResponse.class);
            return webServiceResponse;
        } catch (Exception e) {
            logger.error("Exception in web service call", e);
            return null;
        }

    }

    public String getWebServiceEndpointBasedOnModule(String url, String moduleName) {
        return GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointUrl")
                + GtnFrameworkPropertyManager.getProperty("gtn.webservices." + moduleName + ".endPointServiceName")
                + url;

    }

    public void addSecurityToken(GtnUIFrameworkWebserviceRequest request) {
        GtnWsGeneralRequest gtnWsGeneralRequest = request.getGtnWsGeneralRequest() == null ? new GtnWsGeneralRequest()
                : request.getGtnWsGeneralRequest();
        gtnWsGeneralRequest.setUserId("0");
        gtnWsGeneralRequest.setSessionId("0");
        GtnWsSecurityManager gtnWsSecurityManager = new GtnWsSecurityManager();
        gtnWsGeneralRequest.setToken(gtnWsSecurityManager.createToken("0", "0"));
        request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
    }

    public void addSecurityToken(GtnQueryEngineWebServiceRequest request) {
        request.setUserId("0");
        request.setSessionId("0");
        GtnWsSecurityManager gtnWsSecurityManager = new GtnWsSecurityManager();
        request.setToken(gtnWsSecurityManager.createToken("0", "0"));

    }

    public abstract void initCallOnFailure();

    public void callServiceRegistry(GtnUIFrameworkWebserviceRequest request) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.postForObject(
                    getWebServiceEndpointBasedOnModule(GtnWsFrameworkDependencyConstatnts.GTN_SERVICEREGISTTRY_REGISTERWEBSERVICE, GtnWsFrameworkDependencyConstatnts.GTN_SERVICEREGISTTRY),
                    request, GtnUIFrameworkWebserviceResponse.class);
            service.shutdown();
        } catch (Exception e) {
            logger.error("Exception in web service call", e);
            if (e.getMessage().contains("404 Not Found")) {
                logger.error("Exception in searchWebservice" + e.getMessage());
                GtnWebserviceFailureRunnable call = new GtnWebserviceFailureRunnable();
                service.submit(call.createRunnable(this, staticTime));
            }
        }

    }
    
    
    public GtnQueryEngineWebServiceResponse callServiceRegistryRedirectForQueryEngine(GtnQueryEngineWebServiceRequest request) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            addSecurityToken(request);
            GtnQueryEngineWebServiceResponse webServiceResponse = restTemplate.postForObject(
                    getWebServiceEndpointBasedOnModule(GtnWsFrameworkDependencyConstatnts.GTN_SERVICEREGISTTRY_REDIRECTTOQUERYENGINE, GtnWsFrameworkDependencyConstatnts.GTN_SERVICEREGISTTRY), request,
                    GtnQueryEngineWebServiceResponse.class);
            return webServiceResponse;
        } catch (Exception e) {
            logger.error("Exception in web service call", e);
            return null;
        }

    }
    
    
    public GtnUIFrameworkWebserviceRequest registerWs() {
		logger.info("Building request to register Webservice in Service Registry");
		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnServiceRegistryWsRequest gtnServiceRegistryWsRequest = new GtnServiceRegistryWsRequest();

		GtnWsServiceRegistryBean webServiceRegistryBean = new GtnWsServiceRegistryBean();
		getEndPointServiceURL(webServiceRegistryBean);
		logger.info("Webservice to Register: " + webServiceRegistryBean.getRegisteredWebContext());
		gtnServiceRegistryWsRequest.setGtnWsServiceRegistryBean(webServiceRegistryBean);
		request.setGtnServiceRegistryWsRequest(gtnServiceRegistryWsRequest);
		addSecurityToken(request);
		return request;
	}
    
    
    public abstract void getEndPointServiceURL(GtnWsServiceRegistryBean webServiceRegistryBean);

}
