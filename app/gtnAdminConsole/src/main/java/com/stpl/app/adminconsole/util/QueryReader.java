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
import java.util.ResourceBundle;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kasiammal.m
 */
public class QueryReader {

    private static ResourceBundle queryNameBundle = ResourceBundle.getBundle("properties.GlPosting-Queries");
    private static final Logger LOGGER = LoggerFactory.getLogger(QueryReader.class);
    private final static CommonDAO dao = new CommonDAOImpl();

    public static String getQuery(String key) {
        return queryNameBundle.getString(key);
    }

    public static Object executeSelectQuery(Map<String, Object> input, String query) {
        List<Object[]> returnList = new ArrayList<>();
        try {
            StringBuilder queryString;
            if (query != null && !query.isEmpty()) {
                queryString = new StringBuilder(query);
                if (input != null) {
                    for (Map.Entry<String, Object> entry : input.entrySet()) {
                        final String string = entry.getKey();
                        final Object string1 = entry.getValue();
                        while (queryString.toString().contains(string)) {
                            queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                        }
                    }

                }
                returnList = (List<Object[]>) dao.executeSelectQuery(queryString.toString(), null, null);
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
        }
        return returnList;
    }

    public static List getAppData(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside getAppData");
        List list = new ArrayList();
        StringBuilder sql;
        if (queryName != null && !queryName.isEmpty()) {
            try {
                    sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(SQlUtil.getQuery(quaryName2));
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                LOGGER.debug("queryName = " + queryName);
                LOGGER.debug("sql = " + sql);
                list = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
            } catch (Exception ex) {
               LOGGER.error(ex.getMessage());
            }
        }

        LOGGER.debug("End of getAppData");
        return list;
    }

    public static Boolean dataUpdate(List input, String queryName) {
        LOGGER.debug("Inside dataUpdate");
        StringBuilder sql = new StringBuilder();
        try {
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            LOGGER.debug("queryName = = " + queryName);
            LOGGER.debug("sql = = " + sql);
            Integer count = (Integer) HelperTableLocalServiceUtil.executeUpdateQueryCount(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("End of dataUpdate");
        return Boolean.FALSE;
    }

    public static String getQuery(List input, String queryName) {
        try {
            StringBuilder sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            LOGGER.debug("queryName = = " + queryName);
            LOGGER.debug("sql = = " + sql);
            return sql.toString();
        } catch (Exception ex) {
           LOGGER.error(ex.getMessage());
        }
        return StringUtils.EMPTY;
    }
    
    

}
