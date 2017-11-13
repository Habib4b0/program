package com.stpl.gtn.gtn2o.ws.module.workflowinbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowInboxBean;

@Service()
@Scope(value = "singleton")
public class GtnWsWorkflowPrivateDeleteService {
    public GtnWsWorkflowPrivateDeleteService(){
        /**
         * empty constructor
         */
    }
	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	public String getProductHierarchyDeleteQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnWsWorkflowInboxBean projMasterBean = gtnWsRequest.getGtnWSCommonWorkflowRequest().getGtnWorkflowInboxBean();
		StringBuilder sql = new StringBuilder();
		Integer workflowinboxSid = projMasterBean.getWorkflowinboxSid();
		sql.append(gtnWsSqlService.getQuery("getprivateDeleteQuery"));
		sql.append("WHERE WORKFLOW_INBOX_SID = " + workflowinboxSid);
		return sql.toString();
	}
}
