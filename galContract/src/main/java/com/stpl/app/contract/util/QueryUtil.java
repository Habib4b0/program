/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.contract.util;

import com.stpl.app.contract.dao.CommonDAO;
import com.stpl.app.contract.dao.impl.CommonImpl;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class QueryUtil {

    private static final Logger LOGGER = Logger.getLogger(QueryUtil.class);
    final static CommonDAO ITEMDAO = CommonImpl.getInstance();
    final static SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);

    public static List getGroupList(int projectionId) {
        return new ArrayList();
    }

    public static List getItemData(List input, String queryName, String quaryName2) {
        LOGGER.info("Inside item get data");
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
               
                list = (List<Object[]>) ITEMDAO.executeSelect(sql.toString());
            } catch (Exception ex) {
                ex.printStackTrace();
                LOGGER.error(ex.getMessage());
            }
        }

        LOGGER.info("End of item get Data");
        return list;
    }

    public static Boolean itemUpdate(List input, String queryName) {
        LOGGER.info("Inside Item Update");
        StringBuilder sql = new StringBuilder();
        try {
            int i = 0;

            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                i++;
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }
    
            Integer count = (Integer) ITEMDAO.executeUpdate(sql.toString());
            if (count > 0) {
                return Boolean.TRUE;
            } else {
                return Boolean.FALSE;
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        LOGGER.info("End of Item Update");
        return Boolean.FALSE;
    }

    public static String getQuery(List input, String queryName) {
        StringBuilder sql = null;
        try {
            sql = new StringBuilder();
            sql = new StringBuilder(CustomSQLUtil.get(queryName));
            for (Object temp : input) {
                sql.replace(sql.indexOf("?"), sql.indexOf("?") + 1, String.valueOf(temp));
            }

        } catch (Exception ex) {
            LOGGER.error(ex.getMessage());
        }
        return sql.toString();
    }


   
}