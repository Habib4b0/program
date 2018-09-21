package com.stpl.dependency.webservice;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.dependency.queryengine.response.GtnQueryEngineWebServiceResponse;
import com.stpl.dependency.webservice.constants.GtnWsFrameworkDependencyConstatnts;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.manager.GtnWsSecurityManager;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

import org.springframework.web.client.RestTemplate;

public abstract class GtnCommonWebServiceImplClass {

    public GtnFrameworkDependencyLogger logger;

    public GtnCommonWebServiceImplClass(Class<?> className) {
        logger = GtnFrameworkDependencyLogger.getGTNLogger(className);
    }

    public String readPropertyFile(String propertyFile) {
        return "readPropertyFile:" + propertyFile;
    }

    public abstract GtnUIFrameworkWebserviceRequest registerWs();

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
        } catch (Exception e) {
            logger.error("Exception in web service call", e);
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

}
