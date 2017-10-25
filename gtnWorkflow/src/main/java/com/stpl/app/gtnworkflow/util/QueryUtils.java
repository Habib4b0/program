/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnworkflow.util;

import com.stpl.app.gtnworkflow.util.xmlparser.SQlUtil;
import com.stpl.app.gtnworkflow.dao.WorkFlowDAO;
import com.stpl.app.gtnworkflow.dao.impl.WorkFlowImpl;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author manikandaprabu.g
 */
public class QueryUtils {

    private static final Logger LOGGER = Logger.getLogger(QueryUtils.class);
    static WorkFlowDAO DAO = WorkFlowImpl.getInstance();

    /**
     * To get data from service
     *
     * @param input
     * @param queryName
     * @param queryName2
     * @return
     */
    public static List getServiceData(List input, String queryName, String queryName2) {
        LOGGER.debug("Inside getServiceData");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        if (queryName != null && !queryName.isEmpty()) {
            try {
                LOGGER.debug(CommonUtils.QUERY_NAME + queryName + " List size" + input.size());
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (queryName2 != null && !queryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(SQlUtil.getQuery(queryName2));
                }
                LOGGER.debug(" sql before replace " + sql);
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                LOGGER.debug("sql" +sql);
                list = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.debug("End of getServiceData");
        return list;
    }

    /**
     * To update Data using service
     *
     * @param input
     * @param queryName
     * @return
     */
    public static Boolean updateServiceData(List input, String queryName) {
        LOGGER.debug("Inside updateData");
        StringBuilder sql = new StringBuilder();
        try {
            LOGGER.debug(CommonUtils.QUERY_NAME + queryName);
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            LOGGER.debug("sql" +sql);
            Integer count = (Integer) DAO.executeUpdate(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of updateData");
        return Boolean.FALSE;
    }

    /**
     * To get query from service
     *
     * @param input
     * @param queryName
     * @return
     */
    public static String getQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql.toString();
    }

    /**
     * To get data from App
     *
     * @param input
     * @param queryName
     * @param quaryName2
     * @return
     */
    public static List getAppData(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside getAppData");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        LOGGER.debug("queryName - - >> " + queryName);
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(SQlUtil.getQuery(queryName));
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                LOGGER.debug("sql ==================== " + sql);
                list = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.debug("End getAppData");
        return list;
    }

    /**
     * To update data using App
     *
     * @param input
     * @param queryName
     * @return
     */
    public static Boolean updateAppData(List input, String queryName) {
        LOGGER.debug("Inside updateAppData");
        StringBuilder sql = new StringBuilder();
        try {
            LOGGER.debug(CommonUtils.QUERY_NAME + queryName);
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

            Integer count = (Integer) DAO.executeUpdate(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End updateAppData");
        return Boolean.FALSE;
    }

    /**
     * To get query from App
     *
     * @param input
     * @param queryName
     * @return
     */
    public static String getAppQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql.toString();
    }
}
