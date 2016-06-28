/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.galforecasting.queryUtils;

import com.stpl.app.galforecasting.dao.PPAProjectionDao;
import com.stpl.app.galforecasting.dao.impl.PPAProjectionDaoImpl;
import com.stpl.app.galforecasting.utils.xmlparser.SQlUtil;
import com.stpl.util.dao.orm.CustomSQLUtil;
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

    public static List getGroupList(int projectionId) {
        return new ArrayList();
    }

    public static List getPPAData(List input, String queryName, String quaryName2) {
        LOGGER.info("Inside getPPAData");
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
            list = (List<Object[]>) PPADAO.executeSelect(sql.toString());
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of getPPAData");
        return list;
    }

    public static Boolean PPAUpdate(List input, String queryName) {
        LOGGER.info("Inside PPA Update");
        StringBuilder sql = new StringBuilder();
        try {

            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            return (Boolean) PPADAO.executeUpdate(sql.toString());

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of PPA Update");
        return Boolean.FALSE;
    }

    public static String getQuary(List input, String queryName) {
        StringBuilder sql = new StringBuilder();
        try {
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql.toString();
    }

    public static Boolean ppaUpdate(List input, String queryName) {
        LOGGER.info("Inside PPA Update");
        StringBuilder sql = new StringBuilder();
        try {

            sql = new StringBuilder(QueryReader.getQuery(queryName));

            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

            return (Boolean) PPADAO.executeUpdate(sql.toString());

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of PPA Update");
        return Boolean.FALSE;
    }
    public static String getAppQuery(List input, String queryName) {
        LOGGER.info("Inside getAppData");
        StringBuilder sql = new StringBuilder();
        try {
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of getAppData");
        return sql.toString();
    }
}
