package com.stpl.gtn.gtn2o.ws.periodconf.sqlservice;

import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;

@Component
public class GtnWsPeriodConfSqlService {

	private GtnWsPeriodConfSqlService() {
		super();
	}

	@Autowired
	private volatile PropertiesFactoryBean sqlPropertyBean;

	public String getQuery(String queryId) {

		final GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger
				.getGTNLogger(GtnWsPeriodConfSqlService.class);
		try {
			return Optional.ofNullable(sqlPropertyBean.getObject()).orElseThrow(IllegalArgumentException::new)
					.getProperty(queryId);
		} catch (Exception e) {
			gtnLogger.error("Error in Loading sql file ", e);
		}
		return StringUtils.EMPTY;
	}
}
