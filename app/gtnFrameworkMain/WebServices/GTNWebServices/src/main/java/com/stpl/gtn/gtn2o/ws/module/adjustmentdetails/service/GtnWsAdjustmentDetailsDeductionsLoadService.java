/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.adjustmentdetails.service;

import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(value = "singleton")
public class GtnWsAdjustmentDetailsDeductionsLoadService {

    @Autowired
    private GtnWsSqlService gtnWsSqlService;

    public GtnWsAdjustmentDetailsDeductionsLoadService() {
        super();
    }

    public String getDeductionValue(String deductionLevel) {
        String sqlQuery = gtnWsSqlService.getQuery("getDeductionAvailableLevels");
        Map<String, String[]> map = getLevelMap();
        String[] select = map.get(deductionLevel);
        sqlQuery = sqlQuery.replace("@PARAMETERS", select[0]);
        if ("UDC".equalsIgnoreCase(select[1])) {
            sqlQuery = sqlQuery.replace("--@UDCS", " AND " + select[2]);
        }
        sqlQuery = sqlQuery + select[1];
        return sqlQuery;

    }

    private Map<String, String[]> getLevelMap() {
        final int UDCSTART = 2;
        final int UDCEND = 7;
        final int DESCSTART = 5;
        Map<String, String[]> levelMap = new HashMap<>();
        levelMap.put("Deduction Category", new String[]{"A.RS_CATEGORY,H1.DESCRIPTION AS CATEGORY", "CATEGORY"});
        levelMap.put("Deduction type", new String[]{"A.RS_TYPE,H2.DESCRIPTION AS TYPE", "TYPE"});
        levelMap.put("Deduction program", new String[]{"A.REBATE_PROGRAM_TYPE,H3.DESCRIPTION AS PROGRAM_TYPE", "PROGRAM_TYPE"});
        for (int i = UDCSTART, j = DESCSTART; i < UDCEND; i++, j++) {
            levelMap.put("Deduction Category " + i, new String[]{"U.UDC" + i + ",H" + j + ".DESCRIPTION AS UDC", "UDC", "UDC" + i});
        }
        levelMap.put("Deduction", new String[]{"A.RS_MODEL_SID,A.RS_ID + '- ' + A.RS_NAME AS RS_MODEL_ID", "RS_MODEL_ID"});
        return levelMap;
    }
}
