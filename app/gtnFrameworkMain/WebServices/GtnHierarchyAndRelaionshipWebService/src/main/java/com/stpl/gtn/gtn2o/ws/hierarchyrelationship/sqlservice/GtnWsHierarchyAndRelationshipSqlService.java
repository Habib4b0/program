package com.stpl.gtn.gtn2o.ws.hierarchyrelationship.sqlservice;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Service;

import com.stpl.dependency.webservice.GtnCommonWebServiceImplClass;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;

@Service
public class GtnWsHierarchyAndRelationshipSqlService extends GtnCommonWebServiceImplClass {

	private GtnWsHierarchyAndRelationshipSqlService() {
		super(GtnWsHierarchyAndRelationshipSqlService.class);
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
			logger.error("Exception in getQuery", ex);
		}
		return sql.toString();
	}

	@SuppressWarnings("rawtypes")
	public String getReplacedQuery(List input, String query) {
		StringBuilder sql = new StringBuilder();
		try {
			sql = new StringBuilder(query);
			if (input != null) {
				for (Object temp : input) {
					if (sql.indexOf("?") != -1) {
						sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
					}
				}
			}

		} catch (Exception ex) {
			logger.error("Exception in getReplacedQuery", ex);
		}
		return sql.toString();
	}

    @Override
    public void initCallOnFailure() {
        // Default Method
}

}
