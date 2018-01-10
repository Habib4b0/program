/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import com.stpl.app.adminconsole.dao.CommonDAO;
import com.stpl.app.adminconsole.dao.impl.CommonDAOImpl;
import com.stpl.app.adminconsole.util.xmlparser.SQlUtil;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author ahalya
 */
public class CommonQueryUtils {
       /**
     * Custom query to select records from data base
     *
     * @param queryName - Framed SQL Query
     * @return List<Object[]> result list
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CommonQueryUtils.class);
    static CommonDAO dao = new CommonDAOImpl();
 
    public static Object executeSelectQuery(List input, String queryName) {
        List<Object[]> returnList = new ArrayList<>();
        try {
            if (!queryName.isEmpty()) {
                returnList = (List<Object[]>) dao.executeSelectQuery(input, queryName, null);
            }
        } catch (Exception e) {
             LOGGER.error(e.getMessage());
        }
        return returnList;
    }
    public static List getAppData(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside getPPAData");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        try {
          
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                sql.append(" ");
                sql.append(SQlUtil.getQuery(quaryName2));
            }

            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            list = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(queryName);
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("End of getPPAData");
        return list;
    }

    public static Boolean updateAppData(List input, String queryName) {
        LOGGER.debug("Inside updateAppData");
        StringBuilder sql = new StringBuilder();
        try {

            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            int count = (Integer) HelperTableLocalServiceUtil.executeUpdateQueryCount(sql.toString());
            return count > 0 ? Boolean.TRUE : Boolean.FALSE;
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("End of updateAppData");
        return Boolean.FALSE;
    }
    public static Boolean updateDataFromMap(final Map<String, String> input, final String queryName) {
        StringBuilder queryString = new StringBuilder(SQlUtil.getQuery(queryName));
        if (input != null) {
            for (Map.Entry<String, String> entry : input.entrySet()) {
                final String string = entry.getKey();
                final String string1 = entry.getValue();
                while (queryString.toString().contains(string)) {
                    queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                }
            }
        }
        int count = (Integer) HelperTableLocalServiceUtil.executeUpdateQueryCount(queryString.toString());
        return count > 0 ? Boolean.TRUE : Boolean.FALSE;
    }

}
