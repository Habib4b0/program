package com.stpl.gtn.gtn2o.ws.report.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

@Component
public class GtnWsReportSqlService {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportSqlService.class);
	@Autowired
	private PropertiesFactoryBean sqlPropertyBean;

	public GtnWsReportSqlService() {
		super();
	}

	public String getQuery(String queryId) {
		try {
			return Optional.ofNullable(sqlPropertyBean.getObject()).orElseThrow(IllegalArgumentException::new)
					.getProperty(queryId);
		} catch (IOException e) {
			gtnLogger.error("Error in Loading sql file ", e);
		}
		return StringUtils.EMPTY;
	}

	public String getQuery(List input, String queryName) {
		StringBuilder sql = new StringBuilder();
		try {
			sql = new StringBuilder(getQuery(queryName));
			if (input != null) {
				for (Object temp : input) {
					if (sql.indexOf("?") != -1) {
						sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
					}
				}
			}

		} catch (Exception ex) {
			gtnLogger.error("Exception in getQuery", ex);
		}
		return sql.toString();
	}

}
