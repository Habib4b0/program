package com.stpl.gtn.gtn2o.ws.module.processscheduler.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowProfile;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.constant.GtnWsProcessSchedulerConstant;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.GtnWsPSCffOutBoundService;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.GtnWsProcessSchedulerUpdateService;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsProcessSchedularServiceUtil;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util.GtnWsSchedulerSynchronizer;

public class GtnWsQuartzJob implements Job {

	public GtnWsQuartzJob() {
		super();
	}

	public static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(GtnWsQuartzJob.class);

	public static final String AUTOMATIC_SCHEDULER = " Automatic Scheduler ";

	@Autowired
	private GtnWsProcessSchedularServiceUtil gtnWsProcessSchedularServiceUtil;

	@Autowired
	private GtnWsProcessSchedulerUpdateService gtnWsProcessSchedulerUpdateService;
	
	@Autowired 
	private GtnWsPSCffOutBoundService gtnWsPSCffOutBoundService;

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		logger.info("Executing Quartz Job " + context.getJobDetail().getKey().getName());
		Object profileObj = context.getJobDetail().getJobDataMap().get(GtnWsQuartzListener.ACTION_JOB_DATA_MAP_KEY);

		if (profileObj instanceof WorkflowProfile) {
			WorkflowProfile profile = (WorkflowProfile) profileObj;
			if ("CFF_OUTBOUND_INTERFACE".equalsIgnoreCase(profile.getProcessName())) {
				executeCffOutBoundInterface(profile);
			} else {
				try {
					gtnWsProcessSchedularServiceUtil.runJob(GtnWsProcessSchedularServiceUtil.getFtpBundleValue(),
							profile.getScriptName());
				} catch (Exception e) {
					logger.error("error while running job  "+e.getMessage());
				}

				try {
					gtnWsProcessSchedulerUpdateService.updateLastRun(profile.getProcessSid(), true);
				} catch (Exception e) {
					logger.error("error while updating last run "+e.getMessage());
				}
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
			new GtnWsQuartzListener().printJobsForJobKey(context.getJobDetail().getKey());
		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	private void executeCffOutBoundInterface(WorkflowProfile profile) {
		try {
			GtnWsSchedulerSynchronizer process = GtnWsSchedulerSynchronizer.getInstance();
			process.lock();
			int i = 0;
			gtnWsProcessSchedularServiceUtil.schedulerInsert();
			gtnWsProcessSchedularServiceUtil.runJob(GtnWsProcessSchedularServiceUtil.getFtpBundleValue(), profile.getScriptName());
			gtnWsProcessSchedulerUpdateService.updateLastRun(profile.getProcessSid(), true);
			while (gtnWsProcessSchedularServiceUtil.existsQuery(GtnWsProcessSchedulerConstant.NUMBER_ONE,
					GtnWsProcessSchedulerConstant.NUMBER_ONE)) {
				// Waiting block for ETL to end
				Thread.sleep(GtnWsProcessSchedulerConstant.THREE_THOUSAND);
				i++;
				if (i == GtnWsProcessSchedulerConstant.HUNDRED) {
					logger.info("about to delete ST_CFF_OUT_BOUND_MASTER");
					gtnWsPSCffOutBoundService.deleteTempCffOutbound(Boolean.TRUE);
				}
			}
			process.unlock();
		} catch (Exception ex) {
			logger.error(ex.getMessage());
		}
		
	}

}
