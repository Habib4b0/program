/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.logic;

import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.hameed
 */
public class CommonQueryLogic {

    private static final Logger LOGGER = Logger.getLogger(CommonQueryLogic.class);
    final static BaseRateDAO dao = new BaseRateDAOImpl();

    public static List getGroupList(int projectionId) {
        return new ArrayList();
    }

    public static List getItemData(List input, String queryName, String quaryName2) {
        LOGGER.info("Inside getData");
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
            list = (List<Object[]>) dao.executeSelectQuery(sql.toString());

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of getPPAData");
        return list;
    }

    public static Integer itemUpdate(List input, String queryName) {
        LOGGER.info("Inside PPA Update");
        StringBuilder sql = new StringBuilder();
        try {

            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
          
           
            return (Integer) dao.executeUpdateQuery(sql.toString());

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.info("End of PPA Update");
        return 0;
    }

    public static String getQuery(List input, String queryName) {
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
}
