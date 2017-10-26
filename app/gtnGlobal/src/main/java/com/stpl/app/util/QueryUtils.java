/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;

import com.stpl.app.global.common.dto.SessionDTO;
import com.stpl.app.global.dao.CommonDao;
import com.stpl.app.global.dao.impl.CommonDaoImpl;
import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.util.xmlparser.SQLUtil;
import com.stpl.ifs.util.QueryUtil;
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

    public static List getGroupList() {
        return new ArrayList();
    }

    public static List querySelect(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside item get data");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        LOGGER.debug("queryName - - >> " + queryName);
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(CustomSQLUtil.get(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(CustomSQLUtil.get(quaryName2));
                }
                LOGGER.debug("Input -- >> " + input.size() + " Are --- >> " + input);
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                LOGGER.debug("sql-->>" + sql);
                list = (List<Object[]>) ITEMDAO.executeSelect(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.debug("End of item get Data");
        return list;
    }

    public static Boolean queryUpdate(List input, String queryName) {
        LOGGER.debug("Inside Item Update");
        StringBuilder sql = new StringBuilder();
        try {
            LOGGER.debug("queryName - - >>" + queryName);
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

            LOGGER.debug("sql-->>" + sql);
            Integer count = (Integer) ITEMDAO.executeUpdate(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of Item Update");
        return Boolean.FALSE;
    }

    public static String getQuery(List input, String queryName) {
        StringBuilder sql = new StringBuilder();
        LOGGER.debug("QueryName "+queryName);
        try {
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
         if(input!=null){
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
      }
        } catch (Exception ex) {
            LOGGER.debug("Iniside Exception Query "+sql);
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
        LOGGER.debug("Inside Item Bulk Update");
        try {
            LOGGER.debug("query - - >>" + queryName);
            Integer count = (Integer) ITEMDAO.executeUpdate(queryName);
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of Bulk Item Update");
        return Boolean.FALSE;
    }
    
    
    /**
     * To create temp tables dynamically. It will return the tables created with
     * the user id and session id
     *
     * @param session
     */
    public static void createTempTables(SessionDTO session) {
        List<Object> createdTablesList = HelperTableLocalServiceUtil.executeSelectQuery(QueryUtil.buildDynamicTempTableCreationQueryForGlobal(session.getScreenName(), session.getUserId(), session.getUiSessionId()));
        for (int i = 0; i < createdTablesList.size(); i++) {
            Object[] ob = (Object[]) createdTablesList.get(i);
            session.addCurrentTableNames(ob[0].toString(), ob[1].toString());
        }
    }
    public static Boolean itemUpdate(List input, String queryName) {
        StringBuilder sql = new StringBuilder();
        try {
            if (!queryName.isEmpty()) {
                sql = new StringBuilder(SQLUtil.getQuery(queryName));
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                Integer count = (Integer) HelperTableLocalServiceUtil.executeUpdateQueryCount(sql.toString());
                if (count > 0) {
                    return Boolean.TRUE;
                } else {
                    return Boolean.FALSE;
                }
            }
        } catch (Exception ex) {
            LOGGER.error("Error :" + ex + "  Becoz of the query :" + sql.toString());
        }
        return Boolean.FALSE;
    }
}