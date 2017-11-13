/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowProfile;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.companymaster.quartz.GtnCmQuartzJob;
import com.stpl.gtn.gtn2o.ws.module.companymaster.quartz.GtnCmQuartzListener;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.service.GtnWsProcessSchedulerUpdateService;

/**
 *
 * @author
 */
public class QuartzJob implements Job {
	public QuartzJob() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnCmQuartzJob.class);
	@Autowired
	private GtnWsProcessSchedulerUpdateService psSaveWebservice;

	@Autowired
	private GtnCmQuartzListener quartzListener;

	public GtnCmQuartzListener getQuartzListener() {
		return quartzListener;
	}

	public void setQuartzListener(GtnCmQuartzListener quartzListener) {
		this.quartzListener = quartzListener;
	}

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {

		Object profileObj = context.getJobDetail().getJobDataMap().get(QuartzListener.ACTION_JOB_DATA_MAP_KEY);
		if (profileObj instanceof WorkflowProfile) {
			try {
				psSaveWebservice.runJob();
			} catch (Exception e) {
				LOGGER.error(e.getMessage());

			}
		}
	}
}
