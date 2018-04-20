package com.stpl.gtn.gtn2o.ws.service;

import java.io.IOException;
import java.util.Optional;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

@Component
public class GtnWsReportSqlService {

	private GtnWSLogger gtnLogger = GtnWSLogger.getGTNLogger(GtnWsReportSqlService.class);
	@Autowired
	private PropertiesFactoryBean sqlPropertyBean;

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
