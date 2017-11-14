package com.stpl.gtn.gtn2o.ws.module.workflowinbox.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import com.stpl.gtn.gtn2o.ws.workflow.bean.GtnWsWorkflowInboxBean;

@Service()
@Scope(value = "singleton")
public class GtnWsWorkflowOpenViewService {
	public GtnWsWorkflowOpenViewService() {
		/**
		 * empty constructor
		 */
	}

	@Autowired
	private GtnWsSqlService gtnWsSqlService;
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String openviewSearchQuery(GtnUIFrameworkWebserviceRequest gtnWsRequest) {
		GtnWsWorkflowInboxBean projMasterBean = gtnWsRequest.getGtnWSCommonWorkflowRequest().getGtnWorkflowInboxBean();

		StringBuilder sql = new StringBuilder();
		String workflowId = projMasterBean.getWorkflowId();
		Integer projectionmasterSid = projMasterBean.getProjectionMasterSid();
		sql.append(gtnWsSqlService.getQuery("getOpenViewQuery"));

		if (workflowId != null && (workflowId.startsWith("BR") || workflowId.startsWith("FD"))) {
			sql.append("WHERE WPI.ACC_CLOSURE_MASTER_SID = " + projectionmasterSid);
		} else if (workflowId != null && workflowId.startsWith("CF")) {
			sql.append("WHERE WPI.CONTRACT_MASTER_SID = " + projectionmasterSid + " AND WPI.CONTRACT_STRUCTURE = "
					+ projectionmasterSid);

		} else {
			sql.append("WHERE WPI.PROJECTION_MASTER_SID = " + projectionmasterSid);
		}

		return sql.toString();

	}
}