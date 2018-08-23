package com.stpl.gtn.gtn2o.ws.module.processscheduler.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowProfile;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.GtnWsProcessSchedulerUpdateService;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsProcessSchedularServiceUtil;

public class QuartzJob implements Job {
	
	public QuartzJob() {
		/*
		 * no need to implement
		 */
	}

	public static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(QuartzJob.class);

	public static final String AUTOMATIC_SCHEDULER = " Automatic Scheduler ";
	
	@Autowired
	private GtnWsProcessSchedularServiceUtil gtnWsProcessSchedularServiceUtil;
	
	@Autowired 
	private GtnWsProcessSchedulerUpdateService gtnWsProcessSchedulerUpdateService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		logger.info("Executing Quartz Job " + context.getJobDetail().getKey().getName());
		Object profileObj = context.getJobDetail().getJobDataMap().get(QuartzListener.ACTION_JOB_DATA_MAP_KEY);
		
		if (profileObj instanceof WorkflowProfile) {
			WorkflowProfile profile = (WorkflowProfile) profileObj;
			
				try {
					gtnWsProcessSchedularServiceUtil.runJob(GtnWsProcessSchedularServiceUtil.getFtpBundleValue(), profile.getScriptName());
				} catch (Exception e) {
					logger.error(e.getMessage());
				}
			
			try {
				gtnWsProcessSchedulerUpdateService.updateLastRun(profile.getProcessSid(), true);
			} catch (Exception e) {
				logger.error(e.getMessage());
			}

		} else {
			logger.info("Entering delete Job");
			try {
				// To delete the entire forecast tables
				gtnWsProcessSchedularServiceUtil.deleteUnsavedProjections("ForecastUnsavedProjectionDelete");
				// To drop temp tables created dynamically using userId and sessionId
				gtnWsProcessSchedularServiceUtil.deleteUnsavedProjections("ForecastTempTableDrop");
				logger.info("Ending delete Job");
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
		try {
			logger.info("Ending Quartz Job " + context.getJobDetail().getKey().getName() + " Next Fire Time ");
			new QuartzListener().printJobsForJobKey(context.getJobDetail().getKey());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

}
