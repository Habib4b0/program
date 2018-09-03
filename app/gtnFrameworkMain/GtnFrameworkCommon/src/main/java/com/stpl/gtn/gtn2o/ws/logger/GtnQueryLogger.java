package com.stpl.gtn.gtn2o.ws.logger;

import com.stpl.gtn.gtn2o.ws.jmx.GtnWebServiceJmx;
import com.stpl.gtn.gtn2o.ws.jmx.GtnWebServiceJmxMetrics;

public class GtnQueryLogger {

	private GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnQueryLogger.class);

	public void entry() {
		return;

	}

	public void exit() {
		return;
	}

	public long startQueryLog(String query) {
		if (GtnWebServiceJmx.getInstance().isLogQueryFlag()) {
			logger.debug("Start executing query " + query);
		}
		return System.currentTimeMillis();
	}

	public void endQueryLog(Long startTime, String query) {
		long endTime = System.currentTimeMillis();
		long queryExecutionTime = endTime - startTime;

		GtnWebServiceJmxMetrics gtnWebServiceJmxMetrics = GtnWebServiceJmx.getInstance().getQueryExecutionTimeMap()
				.get(query);
		if (gtnWebServiceJmxMetrics == null) {
			gtnWebServiceJmxMetrics = new GtnWebServiceJmxMetrics();
		}
		gtnWebServiceJmxMetrics.setQueryCount(gtnWebServiceJmxMetrics.getQueryCount() + 1);
		gtnWebServiceJmxMetrics.setQueryLastExecutionTime(queryExecutionTime);
		gtnWebServiceJmxMetrics.setQueryTotalTime(gtnWebServiceJmxMetrics.getQueryTotalTime() + queryExecutionTime);
		GtnWebServiceJmx.getInstance().addQueryExecutionTime(query, gtnWebServiceJmxMetrics);
		if (GtnWebServiceJmx.getInstance().isLogQueryFlag()) {
			logger.debug(
					"Time taken for executing query: " + query.substring(0, 10) + " is " + queryExecutionTime + " ms.");
		}
	}
}
