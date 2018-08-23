package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.sqlservice;

import java.io.IOException;
import java.util.Optional;

import javax.annotation.PostConstruct;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Component
public class GtnWsHierarchyAndRelationshipSqlService extends GtnCommonWebServiceImplClass {

	private GtnWsHierarchyAndRelationshipSqlService() {
		super();
		initializeLogger();
	}

	@PostConstruct
	public void initializeLogger() {
		super.logInformation(GtnWsHierarchyAndRelationshipSqlService.class);
	}

	@Autowired
	private PropertiesFactoryBean sqlPropertyBean;

	public String getQuery(String queryId) {
		try {
			return Optional.ofNullable(sqlPropertyBean.getObject()).orElseThrow(IllegalArgumentException::new)
					.getProperty(queryId);
		} catch (IOException e) {
			logger.error("Error in Loading sql file ", e);
		}
		return StringUtils.EMPTY;
	}

	@Override
	public GtnUIFrameworkWebserviceRequest registerWs() {
		return null;
	}
}
