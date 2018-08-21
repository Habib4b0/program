/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.service;

import java.util.Date;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowProfile;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsProcessSchedularServiceUtil;

/**
 *
 * @author Deepak.kumar
 */
@Service()
@Scope(value = "singleton")
public class GtnWsProcessSchedulerUpdateService {
	public GtnWsProcessSchedulerUpdateService() {
		super();
	}

	public static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessSchedulerUpdateService.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;
	
	@Autowired
	private GtnWsProcessSchedularServiceUtil gtnWsProcessSchedularServiceUtil;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void runProcessScheduler(String scriptName,Integer processSid) {
		logger.info("----------------Starting run  Process Scheduler with process sid: "+processSid);
		gtnWsProcessSchedularServiceUtil.runJob(GtnWsProcessSchedularServiceUtil.getFtpBundleValue(), scriptName);
		logger.info("----------------ending run  Process Scheduler , Executing last run()");
		updateLastRun(processSid, false);
	}

	
	
	public void updateLastRun(Integer processId, boolean schedulerFlag) {
		logger.debug("Entering updateLastRun");
		
		if (processId != 0) {

			try (Session updateLastRunSession = getSessionFactory().openSession()) {
				Transaction updateLastRunTransaction = updateLastRunSession.beginTransaction();
				updateLastRunTransaction.begin();
				WorkflowProfile workflowProfile = updateLastRunSession.load(WorkflowProfile.class,processId);
				logger.info("ProcessName-> " + workflowProfile.getProcessName() + "Processsid-> " + workflowProfile.getProcessSid());		
				if(!schedulerFlag) {
					workflowProfile.setManualLastRun(new Date());
				}
				else {
					workflowProfile.setScheduleLastRun(new Date());
				}
				updateLastRunSession.update(workflowProfile);
				updateLastRunTransaction.commit();
				
				
			} catch(Exception exp) {
				logger.info("exception : "+exp);
			}
			logger.info("ends updateLastRun");
		}
	}

}
