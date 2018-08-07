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
import com.stpl.gtn.gtn2o.ws.service.GtnWsCallEtlService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;

/**
 *
 * @author Deepak.kumar
 */
@Service()
@Scope(value = "singleton")
public class GtnWsProcessSchedulerUpdateService {
	public GtnWsProcessSchedulerUpdateService() {
		/**
		 * empty constructor
		 */
	}

	public static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsProcessSchedulerUpdateService.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;
	@Autowired
	private GtnWsCallEtlService gtnWsCallEtlService;
	@Autowired
	private GtnWsProcessSchedularServiceUtil gtnWsProcessSchedularServiceUtil;
	@Autowired
	private GtnWsSqlService gtnWsSqlService;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void runProcessScheduler(String scriptName,Integer processSid) {
		logger.info("----------------Starting run  Process Scheduler with process sid: "+processSid);
		gtnWsProcessSchedularServiceUtil.runJob(GtnWsProcessSchedularServiceUtil.getFtpBundleValue(), scriptName);
		logger.info("----------------ending run  Process Scheduler ");
		updateLastRun(processSid, false);
	}

	/*public static final String FTP_PROPERTIES_PATH = "conf/BPI Configuration/FTPConfiguration.properties";

	public static java.util.Properties getPropertyFile(String bpiPropLoc) {
		java.util.Properties prop = new java.util.Properties();
		try {
			FileInputStream fileIS = GtnFileNameUtils.getFileInputStream(bpiPropLoc);
			prop.load(fileIS);
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		return prop;

	}

	public boolean runShellScript(String scriptPath) {
		try {
			gtnWsCallEtlService.runShellScript(scriptPath);
		} catch (Exception ex) {
			return false;
		}
		return true;
	}

	public void runJob() {
		try {
			String jbossHome = System.getProperty("jboss.home.dir");
			if (!"null".equals(jbossHome)) {
				String[] ftppath = jbossHome.split("jboss-7.1.1");
				if (ftppath.length != 0) {
					logger.debug("Inside rubJob ");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}*/
	
	public void updateLastRun(Integer processId, boolean schedulerFlag) {
		logger.debug("Entering updateLastRun");
		/*Date curManualDate = new Date();
	    SimpleDateFormat format = new SimpleDateFormat("yyyy-M-dd hh:mm:ss");
	    String currentLastManualStringDate = format.format(curManualDate);*/
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
