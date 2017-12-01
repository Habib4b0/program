/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.queryUtils;

import com.stpl.app.gtnforecasting.dao.PPAProjectionDao;
import com.stpl.app.gtnforecasting.dao.impl.PPAProjectionDaoImpl;
import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;
import com.stpl.ifs.ui.util.GtnSmallHashMap;
import com.stpl.ifs.util.QueryUtil;
import com.liferay.portal.dao.orm.custom.sql.CustomSQLUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class PPAQuerys {

    private static final Logger LOGGER = Logger.getLogger(PPAQuerys.class);
    final static PPAProjectionDao PPADAO = new PPAProjectionDaoImpl();
    private static GtnSmallHashMap replaceTableName = new GtnSmallHashMap();
    public static List getGroupList() {
        return new ArrayList();
    }
    
    /**
     * To set the table name to be replaced dynamically
     * 
     * @param replaceTableNames 
     */
    public static void setTableName(final GtnSmallHashMap replaceTableNames) {
        replaceTableName = replaceTableNames;
    }

    
    public static List getPPAData(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside  getPPAData");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        try {
          
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                sql.append(" ");
                sql.append(CustomSQLUtil.get(quaryName2));
            }

            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            list = (List<Object[]>) PPADAO.executeSelect(QueryUtil.replaceTableNames(sql.toString(), replaceTableName));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of  getPPAData");
        return list;
    }

    /**
     * 
     * @param input
     * @param queryName
     * @param queryToAppend
     * @return 
     */
    public static List getPPADataFromDB(List input, String queryName, String queryToAppend) {
        LOGGER.debug("Inside getPPAData");
        List list = new ArrayList();

        try {
            StringBuilder sql = new StringBuilder();

            if (queryToAppend != null && !queryToAppend.equals(StringUtils.EMPTY)) {
                sql.append(queryToAppend);
            }
            sql.append(SQlUtil.getQuery(queryName));

            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            
            list = (List<Object[]>) PPADAO.executeSelect(QueryUtil.replaceTableNames(sql.toString(), replaceTableName));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of getPPAData");
        return list;
    }
    
    public static Boolean PPAUpdate(List input, String queryName) {
        LOGGER.debug("Inside PPA Update");
        StringBuilder sql = new StringBuilder();
        try {

            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            return (Boolean) PPADAO.executeUpdate(QueryUtil.replaceTableNames(sql.toString(), replaceTableName));

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of PPA Update");
        return Boolean.FALSE;
    }

    public static String getQuery(List input, String queryName) {
        StringBuilder sql = new StringBuilder();
        try {
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return QueryUtil.replaceTableNames(sql.toString(), replaceTableName);
    }

    public static Boolean ppaUpdate(List input, String queryName) {
        LOGGER.debug("Inside PPA Update");
        StringBuilder sql = new StringBuilder();
        try {

            sql = new StringBuilder(QueryReader.getQuery(queryName));

            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

            return (Boolean) PPADAO.executeUpdate(QueryUtil.replaceTableNames(sql.toString(), replaceTableName));

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of PPA Update");
        return Boolean.FALSE;
    }
    public static String getAppQuery(List input, String queryName) {
        LOGGER.debug("Inside getAppData");
        StringBuilder sql = new StringBuilder();
        try {
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of getAppData");
        return sql.toString();
    }
    public static List getPPAAppData(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside getPPAData");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        try {
          
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                sql.append(" ");
                sql.append(CustomSQLUtil.get(quaryName2));
            }

            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            list = (List<Object[]>) PPADAO.executeSelect(QueryUtil.replaceTableNames(sql.toString(), replaceTableName));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of getPPAData");
        return list;
    }
}
