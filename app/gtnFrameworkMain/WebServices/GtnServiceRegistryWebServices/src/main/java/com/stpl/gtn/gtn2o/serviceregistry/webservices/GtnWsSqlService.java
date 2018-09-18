package com.stpl.gtn.gtn2o.serviceregistry.webservices;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

import com.stpl.dependency.logger.GtnFrameworkDependencyLogger;

@Component
public class GtnWsSqlService {


	private GtnWsSqlService()
	{
		//empty
	}

	@Autowired
	private volatile PropertiesFactoryBean sqlPropertyBean;

	public String getQuery(String queryId) {
		
		final GtnFrameworkDependencyLogger gtnLogger = GtnFrameworkDependencyLogger
				.getGTNLogger(GtnWsSqlService.class);
		try {
			return Optional.ofNullable(sqlPropertyBean.getObject()).orElseThrow(IllegalArgumentException::new)
					.getProperty(queryId);
		} catch (Exception e) {
			gtnLogger.error("Error in Loading sql file ", e);
		}
		return "";
	}
}
