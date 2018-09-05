/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.ifs.util.QueryUtil;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Abishek.Ram
 */
public class GroupFilter {

    private GroupFilter() {
        // GroupFilter
    }


    public static void initSalesMap(final SessionDTO session) {
        List<Object> initialList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(CommonLogic.getGroupQuery("ST_NM_SALES_PROJECTION_MASTER"), session.getCurrentTableNames()), null, null);
        session.setSalesgroupSet(generateStringSet(initialList));
    }

    public static Set generateStringSet(List<Object> initialList) {
        Set<String> groupset = new HashSet<>();
        for (Object object : initialList) {
            groupset.add(String.valueOf(object));
        }
        return groupset;
    }


    public static void initdiscountMap(final SessionDTO session) {
        List<Object> initialList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(CommonLogic.getGroupQuery("ST_NM_DISCOUNT_PROJ_MASTER"), session.getCurrentTableNames()), null, null);
        session.setDiscountgroupSet(generateStringSet(initialList));
    }

}
