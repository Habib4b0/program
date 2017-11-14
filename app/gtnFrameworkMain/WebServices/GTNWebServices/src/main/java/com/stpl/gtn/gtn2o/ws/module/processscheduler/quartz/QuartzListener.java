/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.quartz;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkCommonStringConstants;
import com.stpl.gtn.gtn2o.ws.constants.common.GtnFrameworkWebserviceConstant;
import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowProfile;
import com.stpl.gtn.gtn2o.ws.exception.GtnFrameworkGeneralException;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

/**
 *
 * @author
 */
public class QuartzListener {
	public QuartzListener() {
		/**
		 * empty constructor
		 */
	}

	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(QuartzListener.class);

	@Autowired
	private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;

	public GtnFrameworkSqlQueryEngine getGtnSqlQueryEngine() {
		return gtnSqlQueryEngine;
	}

	private Session session = getSessionFactory().openSession();
	private Transaction tx = session.beginTransaction();
	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private Scheduler scheduler = null;
	public static final String ACTION_JOB_DATA_MAP_KEY = "jobData";

	@SuppressWarnings("rawtypes")
	public void contextInitialized() throws GtnFrameworkGeneralException {
		try {
			scheduler = new StdSchedulerFactory().getScheduler();
			scheduler.start();

			Criteria criteria = session.createCriteria(WorkflowProfile.class);
			List employees = criteria.list();
			for (Object profile : employees) {
				createJob((WorkflowProfile) profile);
			}

			tx.commit();

		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception in contextInitialized", ex);
		}
	}

	private void createJob(WorkflowProfile profile) throws GtnFrameworkGeneralException {
		try {
			if ("Y".equals(String.valueOf(profile.getActiveFlag()))) {
				JobDataMap jobDataMap = new JobDataMap();
				jobDataMap.put(ACTION_JOB_DATA_MAP_KEY, profile);

				if ("Interval".equalsIgnoreCase(profile.getFrequency()) && profile.getStartHour() != 24
						&& profile.getStartMinutes() != 60) {
					if (profile.getStartHour() != 0 || profile.getStartMinutes() != 0) {

						JobDetail job1 = JobBuilder.newJob(QuartzJob.class).setJobData(jobDataMap)
								.withIdentity("job" + profile.getProcessSid(), GtnFrameworkWebserviceConstant.GROUP)
								.build();
						addJobinScheduler(profile, job1);
					}

				} else {
					JobDetail job1 = JobBuilder.newJob(QuartzJob.class).setJobData(jobDataMap)
							.withIdentity("job" + profile.getProcessSid(), GtnFrameworkWebserviceConstant.GROUP)
							.storeDurably().build();

					scheduler.addJob(job1, false);
					addJobinScheduler(profile, profile.getStartHour1(), profile.getStartMinutes1(), job1,
							GtnFrameworkCommonStringConstants.STRING_EMPTY);
					addJobinScheduler(profile, profile.getStartHour2(), profile.getStartMinutes2(), job1, "1");
					addJobinScheduler(profile, profile.getStartHour3(), profile.getStartMinutes3(), job1, "2");
				}
			}
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception in createJob ", ex);
		}
	}

	void addJobinScheduler(WorkflowProfile profile, Byte startHour, Byte startMinutes, JobDetail job1, String index)
			throws GtnFrameworkGeneralException {
		try {
			if (startHour != 24 && startMinutes != 60) {
				Trigger trigger2 = TriggerBuilder.newTrigger().forJob(job1)
						.withIdentity(GtnFrameworkWebserviceConstant.TRIGGER + index + profile.getProcessSid())
						.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(startHour,
								startMinutes == 24 ? 0 : startMinutes))
						.build();

				scheduler.scheduleJob(trigger2);
			}
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception in addJobinScheduler ", ex);
		}
	}

	void addJobinScheduler(WorkflowProfile profile, JobDetail job1) throws GtnFrameworkGeneralException {
		try {
			if (profile.getStartHour() < 1 && profile.getStartMinutes() > 0) {

				Trigger trigg = TriggerBuilder.newTrigger()
						.startAt(DateBuilder.futureDate(profile.getStartMinutes(), DateBuilder.IntervalUnit.MINUTE))
						.withSchedule(SimpleScheduleBuilder.simpleSchedule()
								.withIntervalInMinutes(profile.getStartMinutes()).repeatForever())
						.withIdentity(GtnFrameworkWebserviceConstant.TRIGGER + profile.getProcessSid(),
								GtnFrameworkWebserviceConstant.GROUP)
						.build();
				scheduler.scheduleJob(job1, trigg);

			} else {
				Trigger trigg;
				trigg = TriggerBuilder.newTrigger()
						.withIdentity(GtnFrameworkWebserviceConstant.TRIGGER + profile.getProcessSid(),
								GtnFrameworkWebserviceConstant.GROUP)
						.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInHours(profile.getStartHour())
								.withIntervalInMinutes(profile.getStartMinutes()).repeatForever())
						.build();

				scheduler.scheduleJob(job1, trigg);
			}
		} catch (Exception ex) {
			throw new GtnFrameworkGeneralException("Exception in addJobinSchedulers ", ex);
		}
	}

	public void deleteSchedule() {
		try {
			String time = "";
			String[] hrs = time.split("hrs");
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put(ACTION_JOB_DATA_MAP_KEY, "Delete");
			JobDetail job1 = JobBuilder.newJob(QuartzJob.class).setJobData(jobDataMap)
					.withIdentity("job" + "deleteJob", GtnFrameworkWebserviceConstant.GROUP).storeDurably().build();
			scheduler.addJob(job1, false);
			Trigger trigger2 = TriggerBuilder.newTrigger().forJob(job1)
					.withIdentity(GtnFrameworkWebserviceConstant.TRIGGER + "deleteTrigger")
					.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(Integer.parseInt(hrs[0]),
							Integer.parseInt(hrs[1])))
					.build();
			scheduler.scheduleJob(trigger2);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}
}
