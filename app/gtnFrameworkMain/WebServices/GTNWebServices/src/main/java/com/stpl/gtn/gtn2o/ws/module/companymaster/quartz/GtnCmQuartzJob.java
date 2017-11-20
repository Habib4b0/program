/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.companymaster.quartz;

import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterFinancialCloseBean;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class GtnCmQuartzJob implements Job {
	public GtnCmQuartzJob() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(GtnCmQuartzJob.class);

	@Autowired
	private GtnCmQuartzListener quartzListener;

	public GtnCmQuartzListener getQuartzListener() {
		return quartzListener;
	}

	public void setQuartzListener(GtnCmQuartzListener quartzListener) {
		this.quartzListener = quartzListener;
	}

	@Override
	public void execute(JobExecutionContext jec) throws JobExecutionException {
		String jobKey = String.valueOf(jec.getJobDetail().getKey());
		String[] keyArray = jobKey.split("[.]");
		LOGGER.info(
				"*********************Scheduler Started Excuting *********************************************************");
		LOGGER.info("keyArray - - " + keyArray[1]);
		LOGGER.info("Scheduled Time  - - " + jec.getFireTime());
		Scheduler scheduler = null;
		for (Map.Entry<String, Object> object : jec.getMergedJobDataMap().entrySet()) {
			String jobDataKey = object.getKey();
			Object jobDataValue = object.getValue();
			GtnCMasterFinancialCloseBean financialCloseDTO = (GtnCMasterFinancialCloseBean) jobDataValue;
			if (keyArray[1].equals(jobDataKey)) {
				try {
					scheduler = jec.getScheduler();
					quartzListener.updateCompany(financialCloseDTO, financialCloseDTO.getCreatedBy());
					scheduleUpdateAgain(scheduler, (GtnCMasterFinancialCloseBean) jobDataValue);
				} catch (Exception ex) {
					LOGGER.error(ex.getMessage());
				}
			}
		}
		LOGGER.info("*********************Scheduler Ended *********************************************************");
	}

	/**
	 * This is the company information which we will update to main table
	 *
	 * @param dto
	 * @param userId
	 */
	/**
	 * Schedule The time again for next month in a same company
	 *
	 * @param scheduler
	 * @param dto
	 * @throws Exception
	 */
	private void scheduleUpdateAgain(Scheduler scheduler, final GtnCMasterFinancialCloseBean dto)
			throws SchedulerException {
		Calendar calendar = quartzListener.getTime(dto, Calendar.getInstance().get(Calendar.MONTH) + 2);
		JobDataMap jobDataMap = new JobDataMap();
		Date dateForquartz = calendar.getTime();
		jobDataMap.put(String.valueOf(dto.getCompanyMasterSid()), dto);
		JobDetail job1 = JobBuilder.newJob(GtnCmQuartzJob.class).setJobData(jobDataMap)
				.withIdentity(String.valueOf(dto.getCompanyMasterSid()), String.valueOf(dto.getCompanyMasterSid()))
				.storeDurably().build();
		scheduler.addJob(job1, false);
		Trigger trigg = TriggerBuilder.newTrigger().forJob(job1)
				.withIdentity(String.valueOf(dto.getCompanyMasterSid()), String.valueOf(dto.getCompanyMasterSid()))
				.startAt(dateForquartz)
				// .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(time)
				// //
				// .withIntervalInHours(Integer.valueOf(1)).withIntervalInMinutes(Integer.valueOf(0))
				// .repeatForever()
				// )
				// .withIntervalInHours(Integer.valueOf(1)).withIntervalInMinutes(Integer.valueOf(0))
				.build();
		scheduler.start();
		scheduler.scheduleJob(trigg);
		LOGGER.info(
				"********************* SCHEDULED FOR NEXT MONTH ********************************************************* at"
						+ dateForquartz);
	}

	/**
	 * is a method to kill Job which is already defined
	 *
	 * @param scheduler
	 * @param jobkey
	 * @param trigger
	 */
	public void killJobForCurrentKey(Scheduler scheduler, JobKey jobkey, TriggerKey trigger) {
		try {
			scheduler.deleteJob(jobkey);
			scheduler.unscheduleJob(trigger);
		} catch (SchedulerException ex) {
			LOGGER.error(ex.getMessage());
		}
	}
}
