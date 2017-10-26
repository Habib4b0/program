/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.gtnforecasting.logic;

import com.stpl.app.gtnforecasting.sessionutils.SessionDTO;
import com.stpl.app.gtnforecasting.utils.Constant;
import com.stpl.ifs.util.QueryUtil;
import com.stpl.portal.kernel.util.StringUtil;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import oracle.jrockit.jfr.tools.ConCatRepository;

/**
 *
 * @author Abishek.Ram
 */
public class GroupFilter {



    public static void initSalesMap(final SessionDTO session) {
        List<Object> initialList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(CommonLogic.getGroupQuery(session.getProjectionId(), Integer.valueOf(session.getSessionId()), Integer.valueOf(session.getUserId()), "ST_NM_SALES_PROJECTION_MASTER"), session.getCurrentTableNames()), null, null);
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
        List<Object> initialList = (List<Object>) CommonLogic.executeSelectQuery(QueryUtil.replaceTableNames(CommonLogic.getGroupQuery(session.getProjectionId(), Integer.valueOf(session.getSessionId()), Integer.valueOf(session.getUserId()), "ST_NM_DISCOUNT_PROJ_MASTER"), session.getCurrentTableNames()), null, null);
        session.setDiscountgroupSet(generateStringSet(initialList));
    }

}
