/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.utils;

import com.stpl.app.gtnforecasting.utils.xmlparser.SQlUtil;

/**
 *
 * @author sooriya.lakshmanan
 */
public class QueryUtils {
    private QueryUtils() {
        // QueryUtils
    }

    public static String addDeclareQueryJoin(final boolean isCustomHierarchy, final boolean isUpdate) {
        if (isUpdate) {
            if (isCustomHierarchy) {
                return SQlUtil.getQuery(QueryUtils.class,"declareCustomCcpUpdate");
            } else {
                return SQlUtil.getQuery(QueryUtils.class,"declareCcpUpdate");
            }
        } else {
            return SQlUtil.getQuery(QueryUtils.class,"declareCcp");
        }
    }

    public static String addCcpJoinQuery(boolean isCustomHierarchy, final boolean isUpdate) {
        if (isUpdate) {
            if (isCustomHierarchy) {
                return SQlUtil.getQuery(QueryUtils.class,"customUpdateJoin");
            }
            return SQlUtil.getQuery(QueryUtils.class,"updateJoin");
        } else {
            if (isCustomHierarchy) {
                return SQlUtil.getQuery(QueryUtils.class,"customJoin");
            }
            return SQlUtil.getQuery(QueryUtils.class,"generateJoin");
        }
    }

    public static String getTherapJoin(final String theraeutic) {
        return "JOIN HELPER_TABLE TPC ON TPC.HELPER_TABLE_SID=IM.THERAPEUTIC_CLASS AND TPC.DESCRIPTION like '" + theraeutic + "' AND TPC.LIST_NAME = 'ITEM_THERP_CLASS'";
    }

}