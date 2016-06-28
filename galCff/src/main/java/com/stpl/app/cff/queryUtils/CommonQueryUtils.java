/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.queryUtils;

import com.stpl.app.cff.dao.CFFDAO;
import com.stpl.app.cff.dao.impl.CFFDAOImpl;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class CommonQueryUtils {

    private static final Logger LOGGER = Logger.getLogger(CommonQueryUtils.class);
    static CFFDAO DAO = CFFDAOImpl.getInstance();

    public static List getCFFData(List input, String queryName, String quaryName2) {
        LOGGER.info("Inside  get data");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(CustomSQLUtil.get(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(CustomSQLUtil.get(quaryName2));
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                list = (List<Object[]>) DAO.executeSelectQuery(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.info("End of get Data");
        return list;
    }

    public static Boolean cffUpdate(List input, String queryName) {
        LOGGER.info("Inside CFF Update");
        StringBuilder sql = new StringBuilder();
        try {
            int i = 0;
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                i++;
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

            Integer count = (Integer) DAO.executeUpdateQuery(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of CFF Update");
        return Boolean.FALSE;
    }

    public static String getQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            if (input.size() > 0) {
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql.toString();
    }
    public static String getQuery1(String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql.toString();
    }

    public static List getAppData(List input, String queryName, String quaryName2) {
        LOGGER.info("Inside item get data");
        List list = new ArrayList();
        StringBuilder sql = new StringBuilder();
        LOGGER.info("queryName - - >> " + queryName);
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
                list = (List<Object[]>) DAO.executeSelectQuery(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.info("End of item get Data");
        return list;
    }

    public static Boolean updateAppData(List input, String queryName) {
        LOGGER.info("Inside Item Update");
        StringBuilder sql = new StringBuilder();
        try {
            LOGGER.info("queryName - - >>" + queryName);
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            LOGGER.info("Input-->>" + input);
            LOGGER.info("Sql-->>" + sql);
            Integer count = (Integer) DAO.executeUpdateQuery(sql.toString());
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
