/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.companymaster.quartz;

import org.hibernate.SessionFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnCmdeleteJob implements Job {
	private final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnCmdeleteJob.class);

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		SessionFactory sessionFactory = (SessionFactory) jec.getJobDetail().getJobDataMap().get("Session");
		String stringQuery = "DELETE IMTD_COMPANY_FINANCIAL_CLOSE WHERE CREATED_DATE<=DATEADD(hh, -5, GETDATE())";
		GtnFrameworkSqlQueryEngine sqlQueryEngine = new GtnFrameworkSqlQueryEngine(sessionFactory);
		try {
			sqlQueryEngine.executeInsertOrUpdateQuery(stringQuery);
		} catch (GtnFrameworkGeneralException e) {
			logger.error("Exception while running job");
		}
	}
}
