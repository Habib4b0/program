package com.stpl.gtn.gtn2o.ws.periodconf.sqlservice;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class GtnWsPeriodConfSqlService {

	private GtnWsPeriodConfSqlService() {
		super();
	}

	@Autowired
	private volatile PropertiesFactoryBean sqlPropertyBean;

	public String getQuery(String queryId) throws IOException {

		return sqlPropertyBean.getObject().getProperty(queryId);
	}

}
