/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.customview.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.request.customview.GtnWsCustomViewRequest;
import com.stpl.gtn.gtn2o.ws.response.GtnWsCustomViewResponse;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Lokeshwari.Kumarasam
 */
@Service
@Scope(value = "singleton")
public class GtnWsCustomViewService {

    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

    public void checkCustomViewSave(GtnWsCustomViewRequest cvRequest,
            GtnWsCustomViewResponse cvResponse) throws GtnFrameworkGeneralException {
        try {
            cvResponse.setSuccess(true);
            if (cvRequest.getCvSysId() == 0 && checkDuplicateCustomViewName(cvRequest)) {
                cvResponse.setSuccess(false);
                cvResponse.setMessageType(GtnFrameworkCommonStringConstants.ERROR);
                cvResponse.setMessage("Entered Custom View Name already exists.");
                return;
            }
        } catch (Exception e) {
            cvResponse.setSuccess(false);
            throw new GtnFrameworkGeneralException("Exception in checkSaveRelationship", e);
        }

    }

    @SuppressWarnings("rawtypes")
    private boolean checkDuplicateCustomViewName(GtnWsCustomViewRequest cvRequest)
            throws GtnFrameworkGeneralException {
        int relationCount = 0;
        try {
            List<String> inputlist = new ArrayList<>();
            inputlist.add(cvRequest.getCustomViewName());
            List result = executeQuery(getQuery("getCustomViewNameDuplicateCheck"),inputlist);
            if (result != null && !result.isEmpty()) {
                relationCount = Integer.parseInt(String.valueOf(result.get(0)));
            }
        } catch (Exception e) {
            throw new GtnFrameworkGeneralException("Exception in checkDuplicateRelationshipName", e);
        }
        return relationCount != 0;
    }

    @SuppressWarnings("rawtypes")
    public List executeQuery(String sqlQuery, List paramList) throws GtnFrameworkGeneralException {
        return gtnSqlQueryEngine.executeSelectQuery(sqlQuery, paramList);
    }

    public String getQuery(String sqlId) {
        return gtnWsSqlService.getQuery(sqlId);
    }
}
