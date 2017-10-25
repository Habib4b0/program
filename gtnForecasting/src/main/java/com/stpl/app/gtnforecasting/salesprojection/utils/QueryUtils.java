/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.salesprojection.utils;

import com.stpl.util.dao.orm.CustomSQLUtil;

/**
 *
 * @author sooriya.lakshmanan
 */
public class QueryUtils {

    public static String addDeclareQueryJoin(final boolean isCustomHierarchy, final boolean isUpdate) {
        if (isUpdate) {
            if (isCustomHierarchy) {
                return CustomSQLUtil.get("declareCustomCcpUpdate");
            } else {
                return CustomSQLUtil.get("declareCcpUpdate");
            }
        } else {
            return CustomSQLUtil.get("declareCcp");
        }
    }

    public static String addCcpJoinQuery(boolean isCustomHierarchy, final boolean isUpdate) {
        if (isUpdate) {
            if (isCustomHierarchy) {
                return CustomSQLUtil.get("customUpdateJoin");
            }
            return CustomSQLUtil.get("updateJoin");
        } else {
            if (isCustomHierarchy) {
                return CustomSQLUtil.get("customJoin");
            }
            return CustomSQLUtil.get("generateJoin");
        }
    }

    public static String getTherapJoin(final String theraeutic) {
        return "JOIN HELPER_TABLE TPC ON TPC.HELPER_TABLE_SID=IM.THERAPEUTIC_CLASS AND TPC.DESCRIPTION like '" + theraeutic + "' AND TPC.LIST_NAME = 'ITEM_THERP_CLASS'";
    }

}