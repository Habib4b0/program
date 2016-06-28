/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;

import com.stpl.app.global.dao.CommonDao;
import com.stpl.app.global.dao.impl.CommonDaoImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class QueryUtils {

    private static final Logger LOGGER = Logger.getLogger(QueryUtils.class);
    final static CommonDao ITEMDAO = CommonDaoImpl.getInstance();

    public static List getGroupList(int projectionId) {
        return new ArrayList();
    }

    public static List querySelect(List input, String queryName, String quaryName2) {
        LOGGER.info("Inside item get data");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        LOGGER.info("queryName - - >> " + queryName);
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(CustomSQLUtil.get(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(CustomSQLUtil.get(quaryName2));
                }
                LOGGER.info("Input -- >> " + input.size() + " Are --- >> " + input);
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                LOGGER.info("sql-->>" + sql);
                list = (List<Object[]>) ITEMDAO.executeSelect(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.info("End of item get Data");
        return list;
    }

    public static Boolean queryUpdate(List input, String queryName) {
        LOGGER.info("Inside Item Update");
        StringBuilder sql = new StringBuilder();
        try {
            LOGGER.info("queryName - - >>" + queryName);
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

            LOGGER.info("sql-->>" + sql);
            Integer count = (Integer) ITEMDAO.executeUpdate(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of Item Update");
        return Boolean.FALSE;
    }

    public static String getQuery(List input, String queryName) {
        StringBuilder sql = new StringBuilder();
        LOGGER.info("Inside 222222222222222222222222222222222222222 queryName "+queryName);
        try {
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
         if(input!=null){
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
      }
        } catch (Exception ex) {
            LOGGER.info("Iniside Exce 111111111111111111111 ");
            LOGGER.error(ex);
        }
        return sql.toString();
    }

    public static String querySelect(List input, String queryName) {
        StringBuilder sql = new StringBuilder();
        try {
            sql = new StringBuilder(queryName);
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql.toString();
    }

    public static Boolean queryBulkUpdate(String queryName) {
        LOGGER.info("Inside Item Bulk Update");
        try {
            LOGGER.info("query - - >>" + queryName);
            Integer count = (Integer) ITEMDAO.executeUpdate(queryName);
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of Bulk Item Update");
        return Boolean.FALSE;
    }
}
