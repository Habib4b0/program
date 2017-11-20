package com.stpl.gtn.gtn2o.ws.service;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.service.sql.GtnWsSqlService;

public class GtnWsWorkFlowOpenViewService {

	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsWorkFlowOpenViewService.class);
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public GtnWsWorkFlowOpenViewService() {
		super();
	}

	public GtnWsWorkFlowOpenViewService(SessionFactory sessionFactory) {
		super();
		this.sessionFactory = sessionFactory;

	}

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(org.hibernate.SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String openviewSearchQuery() {
		logger.debug("In Workflow view Search Query");
		GtnWsSqlService gtnWsSqlService = new GtnWsSqlService();
		StringBuilder sql = new StringBuilder();
		sql.append(gtnWsSqlService.getQuery("getbpmOpenViewQuery"));
		sql.append("WHERE t.processInstanceId = ?");
		sql.append("order by t.id desc");
		return sql.toString();

	}
}
