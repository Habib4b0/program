/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.search.sqlservice;

import com.stpl.dependency.serviceregistryabstract.GtnServiceRegistryImplClass;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.stereotype.Service;

/**
 *
 * @author anandh.karuppusamy
 */
@Service
public class GtnSearchwebServiceSqlService extends GtnServiceRegistryImplClass {

    private GtnSearchwebServiceSqlService() {
        super(GtnSearchwebServiceSqlService.class);
    }
    @Autowired
    private PropertiesFactoryBean sqlPropertyBean;

    public String getQuery(String queryId) {
        try {
            return Optional.ofNullable(sqlPropertyBean.getObject()).orElseThrow(IllegalArgumentException::new)
                    .getProperty(queryId);

        } catch (IOException e) {
            logger.error("Error in loading query" + e);
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
			logger.error("Exception in getQuery", ex);
		}
		return sql.toString();
	}

}
