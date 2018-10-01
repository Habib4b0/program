package com.stpl.gtn.gtn2o.ws.interceptor;

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
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.manager.GtnWsSecurityManager;
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

		HttpServletRequest httpReqOnQueryEngine = (HttpServletRequest) servletRequest;

		if ((GtnFrameworkPropertyManager.getProperty("gtn.security.enabled") != null
				&& GtnFrameworkPropertyManager.getProperty("gtn.security.enabled").equalsIgnoreCase("no"))
				|| httpReqOnQueryEngine.getRequestURI().equals("/GTNWebServices/test")) {
			filterChain.doFilter(httpReqOnQueryEngine, servletResponse);
			return;
		}
		MyRequestWrapper wrapper = new MyRequestWrapper(httpReqOnQueryEngine);
		String requestBodyOnQueryEngine = wrapper.getBody();
		ObjectMapper objectMapper = new ObjectMapper();

		if (!"".equals(requestBodyOnQueryEngine)) {

			GtnQueryEngineWebServiceRequest gtnUIFrameworkWebserviceRequestOnQueryEng = null;
			gtnUIFrameworkWebserviceRequestOnQueryEng = objectMapper.readValue(requestBodyOnQueryEngine,
			GtnQueryEngineWebServiceRequest.class);
			GtnWsSecurityManager gtnWsSecurityManager = new GtnWsSecurityManager();
			

			boolean result;

			String resultString;
			if (gtnUIFrameworkWebserviceRequestOnQueryEng == null) {
				result = false;
				resultString = "Token verification failed";
			} else {
				result = gtnWsSecurityManager.verifyToken(gtnUIFrameworkWebserviceRequestOnQueryEng.getUserId(),
				gtnUIFrameworkWebserviceRequestOnQueryEng.getSessionId(), gtnUIFrameworkWebserviceRequestOnQueryEng.getToken());
				resultString = result ? "Token Verifed Sucessfully"
						: "  Token verification failed  : " + gtnUIFrameworkWebserviceRequestOnQueryEng.getUserId();
			}

			logger.info(resultString);
			if (result) {
				filterChain.doFilter(wrapper, servletResponse);
				return;
			}
		}

		if (httpReqOnQueryEngine.getMethod().equals("GET")) {
			servletResponse.setContentType("text/html");
			servletResponse.getWriter().println(
					"<html><head><title>GtnWebservices</title></head><body><b>GTN Webservices : You are not authorised</b></body></html>");
			servletResponse.getWriter().close();
			return;
		}

		GtnUIFrameworkWebserviceResponse wsresponseOnQueryEngine = new GtnUIFrameworkWebserviceResponse();
		wsresponseOnQueryEngine.setResponseStatus("AUTH_FAILURE");
		String responseString = objectMapper.writeValueAsString(wsresponseOnQueryEngine);
		logger.info("Error response " + responseString);
		servletResponse.setContentType("application/json");
		servletResponse.getWriter().println(responseString);
		servletResponse.getWriter().close();
		return;

	}

	@Override
	public void destroy() {
		return;

	}

}
