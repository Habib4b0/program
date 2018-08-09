package com.stpl.gtn.gtn2o.ws.periodconf.sqlservice;

import java.io.IOException;
import java.util.Optional;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;

@Component
public class GtnWsPeriodConfSqlService {

	private GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger
			.getGTNLogger(GtnWsPeriodConfSqlService.class);

	@Autowired
	private PropertiesFactoryBean sqlPropertyBean;

	public PropertiesFactoryBean getSqlPropertyBean() {
		return sqlPropertyBean;
	}

	public void setSqlPropertyBean(PropertiesFactoryBean sqlPropertyBean) {
		this.sqlPropertyBean = sqlPropertyBean;
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
}
