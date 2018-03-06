/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gcm.common;

import com.stpl.app.gcm.common.dao.CommonDao;
import com.stpl.app.gcm.common.dao.impl.CommonImpl;
import com.stpl.app.gcm.itemmanagement.itemabstract.queryutils.ItemQueries;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.app.gcm.util.xmlparser.SQlUtil;

/**
 *
 * @author mohamed.hameed
 */
public class AppDataUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ItemQueries.class);
    private static final CommonDao ITEMDAO = CommonImpl.getInstance();

    public static List getGroupList() {
        return new ArrayList();
    }

    public static List getAppData(List input, String queryName, String queryName2) {
        LOGGER.debug("Inside item get data");
        List list = new ArrayList();
        StringBuilder sql;
        if (queryName != null && !queryName.isEmpty()) {
            try {
                sql = new StringBuilder(SQlUtil.getQuery(queryName));
                if (queryName2 != null && !queryName2.equals(StringUtils.EMPTY)) {
                    sql.append(' ');
                    sql.append(SQlUtil.getQuery(queryName2));
                }
                for (Object temp : input) {
                    sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
                }
                list = (List<Object[]>) ITEMDAO.executeSelect(sql.toString());
            } catch (Exception ex) {
                LOGGER.error("",ex);
            }
        }

        LOGGER.debug("End of item get Data");
        return list;
    }

    public static Boolean dataUpdate(List input, String queryName) {
        LOGGER.debug("Inside Item Update");
        StringBuilder sql = new StringBuilder();
        try {
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
            Integer count = (Integer) ITEMDAO.executeUpdate(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        LOGGER.debug("End of Item Update");
        return Boolean.FALSE;
    }

    public static String getQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(SQlUtil.getQuery(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (Exception ex) {
            LOGGER.error("",ex);
        }
        return sql.toString();
    }

}
