package com.stpl.gtn.gtn2o.serviceregistry.webservices;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Component;

@Component
public class GtnWsServiceRegistrySqlService {

//	@Autowired
//	private GtnWsServiceRegistrySqlService gtnWsServiceRegistrySqlService;

	/*@Autowired
	private PropertiesFactoryBean sqlPropertyBean;
*/
	PropertiesFactoryBean sqlPropertyBean = new PropertiesFactoryBean();
	public String getQuery(String queryId) {
		
		try {
			Properties ob=sqlPropertyBean.getObject();
			return Optional.ofNullable(sqlPropertyBean.getObject()).orElseThrow(IllegalArgumentException::new)
					.getProperty(queryId);
		} catch (IOException e) {
			//gtnLogger.error("Error in Loading sql file ", e);
		}
		return "";
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
			//gtnLogger.error("Exception in getQuery", ex);
		}
		return sql.toString();
	}

}
