/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.transactional.common.util.xmlparser;

import com.stpl.app.serviceUtils.ConstantsUtils;
import com.stpl.util.dao.orm.CustomSQLUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;

/**
 *
 * @author srithar
 */
public class QueryAPP {

    private static final Logger LOGGER = Logger.getLogger(QueryAPP.class);
    final static CommonDAO ITEMDAO = CommonImpl.getInstance();
    final static SimpleDateFormat dateFormat = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT);

    public static List getGroupList() {
        return new ArrayList();
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
            LOGGER.error(ex);
        }
        return sql.toString();
    }

    /**
     * To get query from App
     *
     * @param input
     * @param queryName
     * @return
     */
    public static String getAppQuery(Map<String, Object> input, String queryName) {
       String sql=StringUtils.EMPTY;
        try {
             sql = SQlUtil.getQuery(queryName);
            if(input !=null){
            for (String key : input.keySet()) {
                sql = sql.replace(key, String.valueOf(input.get(key)));
            }
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return sql;
    }

}
