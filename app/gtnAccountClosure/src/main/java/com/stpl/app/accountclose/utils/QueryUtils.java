/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.utils;

import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.CommonDao;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.app.accountclose.dao.impl.CommonDaoImpl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class QueryUtils {

    /**
     * Custom query to select records from data base
     *
     * @param queryName - Framed SQL Query
     * @return List<Object[]> result list
     */
    final static CommonDao dao = CommonDaoImpl.getInstance();
    static BaseRateDAO BASE_RATE_DAO = new BaseRateDAOImpl();
    /**
	 * The Constant LOGGER.
	 */
	private static final Logger LOGGER = Logger.getLogger(QueryUtils.class);

    public static Object executeSelectQuery(List input, String queryName) {
        List<Object[]> returnList = new ArrayList<>();
        try {
            if (!queryName.isEmpty()) {
                returnList = (List<Object[]>) dao.executeSelectQuery(input, queryName, null);
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return returnList;
    }

    public static Boolean executeUpdateQuery(List input, String queryName) {
        try {
            StringBuilder sql = new StringBuilder();
            sql = new StringBuilder(dao.getQuery(input, queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            int count = BASE_RATE_DAO.executeUpdateQuery(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return Boolean.FALSE;
    }
    public static Boolean executeUpdateQuery(Map<String, String> input, String queryName) {
        try {
            int count = BASE_RATE_DAO.executeUpdateQuery(replacedQuery(input, queryName));
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
        }
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return Boolean.FALSE;
    }
        public static Object executeSelectQuery(Map<String, String> input, String queryName) {
        List<Object[]> returnList = new ArrayList<Object[]>();
        try {
            returnList = (List<Object[]>) BASE_RATE_DAO.executeSelectQuery(replacedQuery(input, queryName));
        } catch (Exception e) {
            LOGGER.error(e);
        }
        return returnList;
    }
  public static String replacedQuery(Map<String, String> input, String queryName) {
        StringBuilder queryString = new StringBuilder();
        try {
            queryString = new StringBuilder(dao.getQuery(new ArrayList(), queryName));
            if (input != null) {
                for (Map.Entry<String, String> entry : input.entrySet()) {
                    final String string = entry.getKey();
                    final String string1 = entry.getValue();
                    while (queryString.toString().contains(string)) {
                        queryString.replace(queryString.indexOf(string), queryString.indexOf(string) + string.length(), String.valueOf(string1));
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.error(e);
        }
        return queryString.toString();
    }

}