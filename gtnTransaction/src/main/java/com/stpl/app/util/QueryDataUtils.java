
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util;

import com.stpl.app.service.HelperTableLocalServiceUtil;
import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.app.transactional.common.util.xmlparser.SQlUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author mohamed.shahul
 */
public class QueryDataUtils {

    private static final Logger LOGGER = Logger.getLogger(QueryDataUtils.class);
    final static SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);


    public static List getAppData(List input, String queryName, String queryName2) {
        LOGGER.debug("Inside getAppData");
        List list = new ArrayList();
        StringBuilder sql;
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (queryName2 != null && !queryName2.equals(StringUtils.EMPTY)) {
                    sql.append(" ");
                    sql.append(SQlUtil.getQuery(queryName2));
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                list = (List<Object[]>) HelperTableLocalServiceUtil.executeSelectQuery(sql.toString());
            } catch (Exception ex) {
                LOGGER.error(ex);
            }
        }

        LOGGER.debug("End of getAppData");
        return list;
    }

    /**
     * This method is used for Updating data in to table.
     *
     * @param input
     * @param queryName
     * @return updated or not
     */
    public static Boolean updateAppData(List input, String queryName) {
        LOGGER.debug("Inside updateAppData");
        StringBuilder sql = new StringBuilder();
        try {
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            Integer count = (Integer) HelperTableLocalServiceUtil.executeUpdateQueryCount(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        LOGGER.debug("End of updateAppData");
        return Boolean.FALSE;
    }

    public static String getQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
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
