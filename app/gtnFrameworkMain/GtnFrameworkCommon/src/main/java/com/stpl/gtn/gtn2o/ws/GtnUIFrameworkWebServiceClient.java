package com.stpl.gtn.gtn2o.ws;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.stpl.gtn.gtn2o.ws.bean.GtnWsSecurityToken;
import com.stpl.gtn.gtn2o.ws.constants.forecast.GtnFrameworkForecastConstantCommon;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.manager.GtnWsSecurityManager;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

import org.springframework.web.client.RestTemplate;

public class GtnUIFrameworkWebServiceClient {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnUIFrameworkWebServiceClient.class);

	public GtnUIFrameworkWebserviceResponse callGtnWebServiceUrl(String url, GtnUIFrameworkWebserviceRequest request,
			GtnWsSecurityToken securityToken) {
		logger.info("Entering callGtnWebServiceUrl method with url: " + url);
		try {
			RestTemplate restTemplate = getRestTemplate();
			updateRequestWithSecurityToken(request, securityToken);
			GtnUIFrameworkWebserviceResponse response = restTemplate.postForObject(getWebServiceEndpoint(url), request,
					GtnUIFrameworkWebserviceResponse.class);
			logger.info("Ending callGtnWebServiceUrl method with status " + response);
			return response;

		} catch (Exception e) {
			logger.error("Exception in web service call", e);
			return null;
		}
	}

	private RestTemplate getRestTemplate() {
		// SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
		// Proxy proxy = new Proxy(Type.HTTP, new InetSocketAddress("localhost", 9999));
		// requestFactory.setProxy(proxy);
		return new RestTemplate();
	}

	public GtnUIFrameworkWebserviceResponse callGtnWebServiceUrl(String url, String moduleName,
			GtnUIFrameworkWebserviceRequest request, GtnWsSecurityToken securityToken) {
		logger.info("Entering callGtnWebServiceUrl method with url: " + url);

		try {
			updateRequestWithSecurityToken(request, securityToken);

			if (moduleName == null) {
				return callGtnWebServiceUrl(url, request, securityToken);
			}

			if (moduleName.equals("")) {
				return callGtnWebServiceUrl(url, request, securityToken);
			}
			if (moduleName.isEmpty()) {
				return callGtnWebServiceUrl(url, request, securityToken);
			}

			RestTemplate restTemplate = getRestTemplate();
			GtnUIFrameworkWebserviceResponse response = restTemplate.postForObject(
					getWebServiceEndpointBasedOnModule(url, moduleName), request,
					GtnUIFrameworkWebserviceResponse.class);
			logger.info("Ending callGtnWebServiceUrl method with status " + response);
			return response;

		} catch (Exception e) {
            logger.error(e.getMessage());
            logger.info("Failed Url---------------------" + url);
            GtnUIFrameworkWebServiceClientCallOnFailure gtnWebServiceClientCallOnFailure = new GtnUIFrameworkWebServiceClientCallOnFailure();
            gtnWebServiceClientCallOnFailure.setUrl(url);
            gtnWebServiceClientCallOnFailure.setModuleName(moduleName);
            gtnWebServiceClientCallOnFailure.setRequest(request);
            gtnWebServiceClientCallOnFailure.setSecurityToken(securityToken);
            gtnWebServiceClientCallOnFailure.setWsClient(this);
            gtnWebServiceClientCallOnFailure.setStaticTime(staticTime);
            gtnWebServiceClientCallOnFailure.callGtnWebServiceUrlOnFailure();

			return null;
		}
	}

	public Map<String, Object> genreateParams(GtnUIFrameworkWebserviceRequest request) {
		if (request != null) {
			Field[] fields = request.getClass().getDeclaredFields();
			Map<String, Object> paramMap = new HashMap<>();
			for (Field field : fields) {
				field.setAccessible(true);
				try {
					paramMap.put(field.getName(), field.get(request));
				} catch (IllegalArgumentException | IllegalAccessException ex) {
					Logger.getLogger(GtnUIFrameworkWebServiceClient.class.getName()).log(Level.SEVERE, null, ex);
				}
			}
			return paramMap;
		}
		return Collections.emptyMap();
	}

	public static void main(String[] args) {

		GtnUIFrameworkWebserviceRequest request = new GtnUIFrameworkWebserviceRequest();
		GtnWsGeneralRequest generalRequest = new GtnWsGeneralRequest();
		generalRequest.setComboBoxType(GtnFrameworkForecastConstantCommon.COMPANY_IDENTIFIER);
		request.setGtnWsGeneralRequest(generalRequest);
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

	void updateRequestWithSecurityToken(GtnUIFrameworkWebserviceRequest request, GtnWsSecurityToken securityToken) {
		GtnWsGeneralRequest gtnWsGeneralRequest = request.getGtnWsGeneralRequest() == null ? new GtnWsGeneralRequest()
				: request.getGtnWsGeneralRequest();
		GtnWsSecurityManager gtnWsSecurityManager = new GtnWsSecurityManager();
		gtnWsGeneralRequest.setUserId(securityToken.getUserId());
		gtnWsGeneralRequest.setSessionId(securityToken.getSessionId());
		gtnWsGeneralRequest
				.setToken(gtnWsSecurityManager.createToken(securityToken.getUserId(), securityToken.getSessionId()));
		request.setGtnWsGeneralRequest(gtnWsGeneralRequest);
	}
}
