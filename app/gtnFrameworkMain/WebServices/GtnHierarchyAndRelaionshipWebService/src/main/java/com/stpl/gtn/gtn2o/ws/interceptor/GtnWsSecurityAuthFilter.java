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

		HttpServletRequest httpServletRequestOnHier = (HttpServletRequest) servletRequest;

		if ((GtnFrameworkPropertyManager.getProperty("gtn.security.enabled") != null
				&& GtnFrameworkPropertyManager.getProperty("gtn.security.enabled").equalsIgnoreCase("no"))
				|| httpServletRequestOnHier.getRequestURI().equals("/GTNWebServices/test")) {
			filterChain.doFilter(httpServletRequestOnHier, servletResponse);
			return;
		}
		MyRequestWrapper wrapper = new MyRequestWrapper(httpServletRequestOnHier);
		String requestBodyOnHier = wrapper.getBody();
		ObjectMapper objectMapper = new ObjectMapper();

		if (!"".equals(requestBodyOnHier)) {

			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequestOnHier = null;
			gtnUIFrameworkWebserviceRequestOnHier = objectMapper.readValue(requestBodyOnHier,
					GtnUIFrameworkWebserviceRequest.class);
			GtnWsSecurityManager gtnWsSecurityManagerOnHier = new GtnWsSecurityManager();
			GtnWsGeneralRequest gtnWsGeneralRequest = gtnUIFrameworkWebserviceRequestOnHier.getGtnWsGeneralRequest();

			boolean result;

			String resultString;
			if (gtnWsGeneralRequest == null) {
				result = false;
				resultString = "Token verification failed";
			} else {
				result = gtnWsSecurityManagerOnHier.verifyToken(gtnWsGeneralRequest.getUserId(),
						gtnWsGeneralRequest.getSessionId(), gtnWsGeneralRequest.getToken());
				resultString = result ? "Token Verifed Sucessfully"
						: "  Token verification failed  : " + gtnWsGeneralRequest.getUserId();
			}

			logger.info(resultString);
			if (result) {
				filterChain.doFilter(wrapper, servletResponse);
				return;
			}
		}

		if (httpServletRequestOnHier.getMethod().equals("GET")) {
			servletResponse.setContentType("text/html");
			servletResponse.getWriter().println(
					"<html><head><title>GtnWebservices</title></head><body><b>GTN Webservices : You are not authorised</b></body></html>");
			servletResponse.getWriter().close();
			return;
		}

		GtnUIFrameworkWebserviceResponse wsresponseOnHier = new GtnUIFrameworkWebserviceResponse();
		wsresponseOnHier.setResponseStatus("AUTH_FAILURE");
		String responseString = objectMapper.writeValueAsString(wsresponseOnHier);
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
