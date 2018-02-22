/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.cff.queryUtils;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.stpl.app.cff.dao.CFFDAO;
import com.stpl.app.cff.dao.impl.CFFDAOImpl;
import com.stpl.app.cff.util.xmlparser.SQlUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author mohamed.hameed
 */
public class CommonQueryUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonQueryUtils.class);
    private static final CFFDAO DAO = CFFDAOImpl.getInstance();

    public static String getQuery( String query,List input) {
        StringBuilder sql =new StringBuilder();
        try {
            sql = new StringBuilder(query);
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return sql.toString();
    }
    public static String getQuery1(String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
        } catch (final Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return sql.toString();
    }

    public static List getAppData(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside item get data");
        List list = new ArrayList();
        StringBuilder sql;
        LOGGER.debug("queryName - - >> {} ", queryName);
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(SQlUtil.getQuery(quaryName2));
                }
                for (final Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                list = (List<Object[]>) DAO.executeSelectQuery(sql.toString());
            } catch (final PortalException | SystemException ex) {
                LOGGER.error(ex.getMessage());
            }
        }

        LOGGER.debug("End of item get Data");
        return list;
    }

    public static Boolean updateAppData(List input, String queryName) {
        LOGGER.debug("Inside Item Update");
        StringBuilder sql = new StringBuilder();
        try {
            LOGGER.debug("queryName - - >> {}", queryName);
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (final Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            LOGGER.debug("Input-->> {}", input);
            LOGGER.debug("Sql-->> {}", sql);
            final Integer count = (Integer) DAO.executeUpdateQuery(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (final PortalException | SystemException ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.debug("End of Item Update");
        return Boolean.FALSE;
    }

    public static String getAppQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (final Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (final Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return sql.toString();
    }

    public static StringBuilder getSqlQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (final Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (final Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return sql;
    }

    public static String getAppDataQuery(List input, String queryName, String quaryName2) {
        LOGGER.debug("Inside item get data");
        StringBuilder sql = null;
        LOGGER.debug("queryName - - >> {} ", queryName);
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (quaryName2 != null && !quaryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(SQlUtil.getQuery(quaryName2));
                }
                for (final Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
            } catch (final Exception ex) {
                LOGGER.error(ex.getMessage());
            }
        }

        LOGGER.debug("End of item get Data");
        return sql.toString();
    }
}
