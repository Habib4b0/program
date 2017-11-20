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

		HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;

		if ((GtnFrameworkPropertyManager.getProperty("gtn.security.enabled") != null
				&& GtnFrameworkPropertyManager.getProperty("gtn.security.enabled").equalsIgnoreCase("no"))
				|| httpServletRequest.getRequestURI().equals("/GTNWebServices/test")) {
			filterChain.doFilter(httpServletRequest, servletResponse);
			return;
		}
		MyRequestWrapper wrapper = new MyRequestWrapper(httpServletRequest);
		String requestBody = wrapper.getBody();
		ObjectMapper objectMapper = new ObjectMapper();

		if ("".equals(requestBody) == false) {

			GtnUIFrameworkWebserviceRequest gtnUIFrameworkWebserviceRequest = null;

			try {

				gtnUIFrameworkWebserviceRequest = objectMapper.readValue(requestBody,
						GtnUIFrameworkWebserviceRequest.class);
				GtnWsSecurityManager gtnWsSecurityManager = new GtnWsSecurityManager();
				GtnWsGeneralRequest gtnWsGeneralRequest = gtnUIFrameworkWebserviceRequest.getGtnWsGeneralRequest();

				boolean result = gtnWsGeneralRequest == null ? false
						: gtnWsSecurityManager.verifyToken(gtnWsGeneralRequest.getUserId(),
								gtnWsGeneralRequest.getSessionId(), gtnWsGeneralRequest.getToken());

				String resultString = result ? "Token Verifed Successfully"
						: gtnWsGeneralRequest == null ? "  Token verification failed"
								: "  Token verification failed for User " + gtnWsGeneralRequest.getUserId();

				logger.info(resultString);
				if (result) {
					filterChain.doFilter(wrapper, servletResponse);
					return;
				}

			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}

		if (httpServletRequest.getMethod().equals("GET")) {
			servletResponse.setContentType("text/html");
			servletResponse.getWriter().println(
					"<html><head><title>GtnWebservices</title></head><body><b>GTN Webservices : You are not authorised</b></body></html>");
			servletResponse.getWriter().close();
			return;
		}

		GtnUIFrameworkWebserviceResponse wsresponse = new GtnUIFrameworkWebserviceResponse();
		wsresponse.setResponseStatus("AUTH_FAILURE");
		String responseString = objectMapper.writeValueAsString(wsresponse);
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
