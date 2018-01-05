/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.workflowinbox.service;

import com.stpl.gtn.gtn2o.ws.module.workflowinbox.constants.GtnWsWorkflowQueryContants;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowInboxBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

/**
 *
 * @author Hazihabibullah.Syed
 */
@Service()
@Scope(value = "singleton")
public class GtnWsGcmWorkflowService {

    public GtnWsGcmWorkflowService() {
        /**
         * empty constructor
         */
    }
    @Autowired
    private GtnWsSqlService gtnWsSqlService;

    public String getWorkflowGcmQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
        GtnWsWorkflowInboxBean projMasterBean = gtnWsRequest.getGtnWSCommonWorkflowRequest().getGtnWorkflowInboxBean();
        StringBuilder sql = new StringBuilder();
        Integer projectionMasterSid = projMasterBean.getProjectionMasterSid();
        sql.append(gtnWsSqlService.getQuery(GtnWsWorkflowQueryContants.GETGCMWFQUERY));
        sql.append(GtnWsWorkflowQueryContants.WHERE_PROJECTIONID).append(projectionMasterSid);
        return sql.toString();
    }
}
