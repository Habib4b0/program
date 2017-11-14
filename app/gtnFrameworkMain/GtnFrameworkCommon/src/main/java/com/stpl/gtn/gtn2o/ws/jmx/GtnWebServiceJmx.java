/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.jmx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonConstants;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author Nadhiya.Jayaram
 */
public class GtnWebServiceJmx {

	private final Map<String, GtnWebServiceJmxMetrics> urlExecutionTimeMap = new HashMap<>();
	private final Map<String, GtnWebServiceJmxMetrics> queryExecutionTimeMap = new HashMap<>();

	private boolean enableJmx = true;
	private boolean logQueryFlag = false;
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnWebServiceJmx.class);

	private GtnWebServiceJmx() {
	}

	private static final GtnWebServiceJmx INSTANCE = new GtnWebServiceJmx();

	public static GtnWebServiceJmx getInstance() {
		return INSTANCE;
	}

	public String returnUrlAvgTime(String url) {
		GtnWebServiceJmxMetrics jmxMetrics = urlExecutionTimeMap.get(url);
		long avgExeTime = jmxMetrics.getTotalTime() / jmxMetrics.getCount();
		return ((avgExeTime) / 1000 + GtnFrameworkCommonConstants.SEC_AND + (avgExeTime) % 1000
				+ GtnFrameworkCommonConstants.MILLISEC);
	}

	public int returnUrlCount(String url) {
		GtnWebServiceJmxMetrics jmxMetrics = urlExecutionTimeMap.get(url);
		return jmxMetrics.getCount();
	}

	public String returnLastExecutionTimeForUrl(String url) {
		GtnWebServiceJmxMetrics jmxMetrics = urlExecutionTimeMap.get(url);
		long lastExeTime = jmxMetrics.getLastExecutionTime();
		return ((lastExeTime) / 1000 + GtnFrameworkCommonConstants.SEC_AND + (lastExeTime) % 1000
				+ GtnFrameworkCommonConstants.MILLISEC);

	}

	public List<String> getAllUrls() {
		List<String> list = new ArrayList<>();
		for (Map.Entry<String, GtnWebServiceJmxMetrics> entry : urlExecutionTimeMap.entrySet()) {
			list.add(entry.getKey() + "\n");
		}
		return list;
	}

	public List<String> returnAllQuerysWithTime() {
		List<String> list = new ArrayList<>();
		for (Map.Entry<String, GtnWebServiceJmxMetrics> entry : queryExecutionTimeMap.entrySet()) {
			list.add(entry.getKey() + "\n");
		}
		return list;
	}

	public String returnQueryExecutionTime(String query) {
		long queryExecutionTime = 0L;
		try {
			GtnWebServiceJmxMetrics jmxMetrics = queryExecutionTimeMap.get(query);
			queryExecutionTime = jmxMetrics.getQueryLastExecutionTime();
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		return ((queryExecutionTime) / 1000 + GtnFrameworkCommonConstants.SEC_AND + (queryExecutionTime) % 1000
				+ GtnFrameworkCommonConstants.MILLISEC);
	}

	public long returnQueryCount(String query) {
		GtnWebServiceJmxMetrics jmxMetrics = queryExecutionTimeMap.get(query);
		return jmxMetrics.getQueryCount();
	}

	public String returnQueryAverageTime(String query) {
		GtnWebServiceJmxMetrics jmxMetrics = queryExecutionTimeMap.get(query);
		long avgExeTime = jmxMetrics.getQueryTotalTime() / jmxMetrics.getQueryCount();
		return ((avgExeTime) / 1000 + GtnFrameworkCommonConstants.SEC_AND + (avgExeTime) % 1000
				+ GtnFrameworkCommonConstants.MILLISEC);
	}

	public boolean isEnableJmx() {
		return enableJmx;
	}

	public void setEnableJmx(boolean enableJmx) {
		this.enableJmx = enableJmx;
	}

	public boolean isLogQueryFlag() {
		return logQueryFlag;
	}

	public void setLogQueryFlag(boolean logQueryFlag) {
		this.logQueryFlag = logQueryFlag;
	}

	public Map<String, GtnWebServiceJmxMetrics> getUrlExecutionTimeMap() {
		return Collections.unmodifiableMap(urlExecutionTimeMap);
	}

	public void addUrlExecutionTime(String url, GtnWebServiceJmxMetrics jmx) {
		urlExecutionTimeMap.put(url, jmx);
	}

	public Map<String, GtnWebServiceJmxMetrics> getQueryExecutionTimeMap() {
		return Collections.unmodifiableMap(queryExecutionTimeMap);
	}

	public void addQueryExecutionTime(String query, GtnWebServiceJmxMetrics gtnWebServiceJmxMetrics) {
		queryExecutionTimeMap.put(query.trim(), gtnWebServiceJmxMetrics);
	}
}
