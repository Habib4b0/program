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
        Map<String, String[]> levelMap = new HashMap<>();
        levelMap.put("Deduction Category", new String[]{"A.RS_CATEGORY,H1.DESCRIPTION AS CATEGORY", "CATEGORY"});
        levelMap.put("Deduction type", new String[]{"A.RS_TYPE,H2.DESCRIPTION AS TYPE", "TYPE"});
        levelMap.put("Deduction program", new String[]{"A.REBATE_PROGRAM_TYPE,H3.DESCRIPTION AS PROGRAM_TYPE", "PROGRAM_TYPE"});
        levelMap.put("Deduction Category 2", new String[]{"U.UDC2,H5.DESCRIPTION AS UDC", "UDC", "UDC2"});
        levelMap.put("Deduction Category 3", new String[]{"U.UDC3,H6.DESCRIPTION AS UDC", "UDC", "UDC3"});
        levelMap.put("Deduction Category 4", new String[]{"U.UDC4,H7.DESCRIPTION AS UDC", "UDC", "UDC4"});
        levelMap.put("Deduction Category 5", new String[]{"U.UDC5,H8.DESCRIPTION AS UDC", "UDC", "UDC5"});
        levelMap.put("Deduction Category 6", new String[]{"U.UDC6,H9.DESCRIPTION AS UDC", "UDC", "UDC6"});
        levelMap.put("Deduction", new String[]{"A.RS_MODEL_SID,A.RS_ID + '- ' + A.RS_NAME AS RS_MODEL_ID", "RS_MODEL_ID"});
        return levelMap;
    }
}
