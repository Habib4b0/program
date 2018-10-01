package com.stpl.gtn.gtn2o.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stpl.dependency.queryengine.request.GtnQueryEngineWebServiceRequest;
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
import com.stpl.gtn.gtn2o.ws.interceptor.MyRequestWrapper;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.manager.GtnWsSecurityManager;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.GtnWsGeneralRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnUIFrameworkWebserviceResponse;

public class GtnWsSecurityAuthFilter implements Filter {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsSecurityAuthFilter.class);

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		return;
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {

		HttpServletRequest httpServletRequestOnServiceReg = (HttpServletRequest) servletRequest;

		if ((GtnFrameworkPropertyManager.getProperty("gtn.security.enabled") != null
				&& GtnFrameworkPropertyManager.getProperty("gtn.security.enabled").equalsIgnoreCase("no"))
				|| httpServletRequestOnServiceReg.getRequestURI().equals("/GTNWebServices/test")) {
			filterChain.doFilter(httpServletRequestOnServiceReg, servletResponse);
			return;
		}
		MyRequestWrapper wrapper = new MyRequestWrapper(httpServletRequestOnServiceReg);
		String requestBody = wrapper.getBody();
		ObjectMapper objectMapper = new ObjectMapper();

		if (!"".equals(requestBody)) {
			String path = ((HttpServletRequest) httpServletRequestOnServiceReg).getRequestURI();
			boolean result;
			if (path.contains("serviceRegistryWebservicesForRedirectToQueryEngine")) {
				result = doValidationForQueryRequest(objectMapper, requestBody);
			} else {
				result = doValidationForGeneralRequest(objectMapper, requestBody);
			}

			if (result) {
				filterChain.doFilter(wrapper, servletResponse);
				return;
			}
		}

		if (httpServletRequestOnServiceReg.getMethod().equals("GET")) {
			servletResponse.setContentType("text/html");
			servletResponse.getWriter().println(
					"<html><head><title>GtnWebservices</title></head><body><b>GTN Webservices : You are not authorised</b></body></html>");
			servletResponse.getWriter().close();
			return;
		}

		GtnUIFrameworkWebserviceResponse serviceRegReesponse = new GtnUIFrameworkWebserviceResponse();
		serviceRegReesponse.setResponseStatus("AUTH_FAILURE");
		String responseString = objectMapper.writeValueAsString(serviceRegReesponse);
		logger.info("Error response " + responseString);
		servletResponse.setContentType("application/json");
		servletResponse.getWriter().println(responseString);
		servletResponse.getWriter().close();
		return;

	}

	private boolean doValidationForQueryRequest(ObjectMapper objectMapper, String requestBody) {
		GtnQueryEngineWebServiceRequest queryEngineRequest = null;
		try {
			queryEngineRequest = objectMapper.readValue(requestBody,
					GtnQueryEngineWebServiceRequest.class);
		} catch (IOException e) {
			logger.error("Unable to read request body ", e);
		}
		GtnWsSecurityManager gtnWsSecurityManager = new GtnWsSecurityManager();
		boolean result;
		String resultString;
		if (queryEngineRequest == null) {
			result = false;
			resultString = "Token verification failed";
		} else {
			result = gtnWsSecurityManager.verifyToken(queryEngineRequest.getUserId(),
					queryEngineRequest.getSessionId(), queryEngineRequest.getToken());
			resultString = result ? "Token Verifed Sucessfully"
					: "  Token verification failed  : " + queryEngineRequest.getUserId();
		}
		logger.info(resultString);
		return result;
	}

	private boolean doValidationForGeneralRequest(ObjectMapper objectMapper, String requestBody) {
		GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserReqOnServiceReg = null;
		try {
			gtnUIFrameworkWebserReqOnServiceReg = objectMapper.readValue(requestBody,
					GtnUIFrameworkWebserviceRequest.class);
		} catch (IOException e) {
			logger.error("Unable to read request body ", e);
		}
		GtnWsSecurityManager secManagerOnServiceReg = new GtnWsSecurityManager();
		GtnWsGeneralRequest generalRequestOnServiceReg = gtnUIFrameworkWebserReqOnServiceReg.getGtnWsGeneralRequest();
		boolean result;
		String resultString;
		if (generalRequestOnServiceReg == null) {
			result = false;
			resultString = "Token verification failed";
		} else {
			result = secManagerOnServiceReg.verifyToken(generalRequestOnServiceReg.getUserId(),
					generalRequestOnServiceReg.getSessionId(), generalRequestOnServiceReg.getToken());
			resultString = result ? "Token Verifed Sucessfully"
					: "  Token verification failed  : " + generalRequestOnServiceReg.getUserId();
		}
		logger.info(resultString);
		return result;
	}

	@Override
	public void destroy() {
		return;

	}

}
