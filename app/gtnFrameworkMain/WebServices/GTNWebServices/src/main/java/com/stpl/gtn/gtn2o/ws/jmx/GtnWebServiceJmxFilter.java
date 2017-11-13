/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.jmx;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author Nadhiya.Jayaram
 */
public class GtnWebServiceJmxFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		return;

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		Long startTime = System.currentTimeMillis();
		chain.doFilter(request, response);
		Long endTime = System.currentTimeMillis();
		if (GtnWebServiceJmx.getInstance().isEnableJmx()) {
			calculateJmxMetrics(startTime, endTime, httpServletRequest.getRequestURI());
		}

	}

	@Override
	public void destroy() {
		return;

	}

	public void calculateJmxMetrics(long startTime, long endTime, String url) {
		long executionTime = endTime - startTime;

		GtnWebServiceJmxMetrics jmx = GtnWebServiceJmx.getInstance().getUrlExecutionTimeMap().get(url);
		if (jmx == null) {
			jmx = new GtnWebServiceJmxMetrics();
		}
		jmx.setCount(jmx.getCount() + 1);
		jmx.setLastExecutionTime(executionTime);
		jmx.setTotalTime(jmx.getTotalTime() + executionTime);

		GtnWebServiceJmx.getInstance().addUrlExecutionTime(url, jmx);
	}

}
