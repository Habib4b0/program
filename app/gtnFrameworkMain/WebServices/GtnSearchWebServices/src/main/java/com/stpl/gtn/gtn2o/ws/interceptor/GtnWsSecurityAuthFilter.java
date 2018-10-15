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
import com.stpl.gtn.gtn2o.ws.GtnFrameworkPropertyManager;
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

		HttpServletRequest httpServletRequestOnSearch = (HttpServletRequest) servletRequest;

		if ((GtnFrameworkPropertyManager.getProperty("gtn.security.enabled") != null
				&& GtnFrameworkPropertyManager.getProperty("gtn.security.enabled").equalsIgnoreCase("no"))
				|| httpServletRequestOnSearch.getRequestURI().equals("/GTNWebServices/test")) {
			filterChain.doFilter(httpServletRequestOnSearch, servletResponse);
			return;
		}
		MyRequestWrapper wrapper = new MyRequestWrapper(httpServletRequestOnSearch);
		String requestBodyOnSearch = wrapper.getBody();
		ObjectMapper objectMapper = new ObjectMapper();

		if (!"".equals(requestBodyOnSearch)) {

			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = null;
			gtnUIFrameworkWebserviceRequest = objectMapper.readValue(requestBodyOnSearch,
					GtnUIFrameworkWebserviceRequest.class);
			GtnWsSecurityManager gtnWsSecurityManagerOnSearch = new GtnWsSecurityManager();
			GtnWsGeneralRequest gtnWsGeneralRequestOnSearch = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();

			boolean result;

			String resultString;
			if (gtnWsGeneralRequestOnSearch == null) {
				result = false;
				resultString = "Token verification failed";
			} else {
				result = gtnWsSecurityManagerOnSearch.verifyToken(gtnWsGeneralRequestOnSearch.getUserId(),
						gtnWsGeneralRequestOnSearch.getSessionId(), gtnWsGeneralRequestOnSearch.getToken());
				resultString = result ? "Token Verifed Sucessfully"
						: "  Token verification failed  : " + gtnWsGeneralRequestOnSearch.getUserId();
			}

			logger.info(resultString);
			if (result) {
				filterChain.doFilter(wrapper, servletResponse);
				return;
			}
		}

		if (httpServletRequestOnSearch.getMethod().equals("GET")) {
			servletResponse.setContentType("text/html");
			servletResponse.getWriter().println(
					"<html><head><title>GtnWebservices</title></head><body><b>GTN Webservices : You are not authorised</b></body></html>");
			servletResponse.getWriter().close();
			return;
		}

		GtnUIFrameworkWebserviceResponse wsresponseOnSearch = new GtnUIFrameworkWebserviceResponse();
		wsresponseOnSearch.setResponseStatus("AUTH_FAILURE");
		String responseString = objectMapper.writeValueAsString(wsresponseOnSearch);
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
