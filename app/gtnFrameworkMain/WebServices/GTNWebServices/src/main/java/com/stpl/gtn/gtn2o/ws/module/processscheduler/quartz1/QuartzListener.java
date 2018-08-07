package com.stpl.gtn.gtn2o.ws.module.processscheduler.quartz1;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowProfile;
import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.constant.ProcessSchedulerConstant;

/**
 *
 * @author Deepak.Kumar
 */

@Service()
@Scope(value = "singleton")
public class QuartzListener {

	public static final GtnWSLogger logger = GtnWSLogger.getGTNLogger(QuartzListener.class);

	@Autowired
	private org.hibernate.SessionFactory sessionFactory;

	public org.hibernate.SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	private Scheduler scheduler = null;
	public static final String ACTION_JOB_DATA_MAP_KEY = "jobData";
	private static final String GROUP = "Group";
	public static final String JOB = "job";
	public static final String TRIGGER = "trigger";

	@SuppressWarnings("unchecked")
	public synchronized void createQuartzScheduler() {
		logger.info(" executing createQuartzScheduler() ");
		List<WorkflowProfile> workFlowProfileList = null;
		try {
			if (scheduler == null) {
				scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
			} else {
				scheduler.shutdown();
				scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
			}

			try (Session quartzJobSchedularSession = getSessionFactory().openSession()) {
				Transaction quartzJobSchedularTransaction = quartzJobSchedularSession.beginTransaction();
				quartzJobSchedularTransaction.begin();
				Criteria criteria = quartzJobSchedularSession.createCriteria(WorkflowProfile.class);
				workFlowProfileList = criteria.list();
				quartzJobSchedularTransaction.commit();
				logger.info("no of records: " + workFlowProfileList.size());

			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
			// Setup the Job class and the Job group

			for (WorkflowProfile profile : workFlowProfileList) {
				createJob(profile);
			}

			DeleteSchedule();

			printJobList();

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void createJob(WorkflowProfile profile) {
		logger.info(" executing create job method---------");
		try {
			if (!"Y".equals(String.valueOf(profile.getActiveFlag()))) {
				return;
			}
			if ((profile.getStartDate() == null) && (profile.getEndDate() == null)) {
				return;
			}
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put(ACTION_JOB_DATA_MAP_KEY, profile);
			JobDetail job = JobBuilder.newJob(QuartzJob.class).setJobData(jobDataMap)
					.withIdentity(generateJobName(profile), GROUP).build();

			List<String> cronStringList = new ArrayList<>();
			generateCronStringTime(profile, cronStringList);
			generateCronStringInterval(profile, cronStringList);

			if (cronStringList.isEmpty()) {
				logger.info("No triggers set for job " + profile.getProcessDisplayName());
			}

			int i = 0;
			for (String cronString : cronStringList) {

				if (i == 0) {
					try {
						Trigger trigger = getTriggerBuilderWithDate(profile, i + 1)
								.withSchedule(CronScheduleBuilder.cronSchedule(cronString)).build();
						logger.info("- " + profile.getProcessDisplayName() + " Scheduling trigger " + cronString);
						scheduler.scheduleJob(job, trigger);
						i++;
					} catch (Exception e) {
						logger.info("'" + e);
					}
				} else {
					try {
						Trigger trigger = getTriggerBuilderWithDate(profile, i + 1)
								.withSchedule(CronScheduleBuilder.cronSchedule(cronString)).build();
						logger.info("- " + profile.getProcessDisplayName() + " Scheduling trigger " + cronString);
						scheduler.scheduleJob(trigger);
					} catch (Exception e) {
						logger.info("" + e);
					}
					i++;
				}

			}

			if (i == 0) {
				logger.info("No triggers set for job " + profile.getProcessDisplayName());
			}

		} catch (Exception e) {
			logger.error(e.getMessage());
		}

	}

	public static String generateJobName(WorkflowProfile profile) {
		return JOB + profile.getProcessSid() + "_" + profile.getProcessDisplayName();
	}

	public static void generateCronStringTime(WorkflowProfile profile, List<String> cronStringList) {

		if ("Time".equalsIgnoreCase(profile.getFrequency())) {
			if (profile.getStartHour1() != ProcessSchedulerConstant.TWENTY_FOUR
					&& profile.getStartMinutes1() != ProcessSchedulerConstant.SIXTY) {
				String str = getCronString(profile.getStartMinutes1(), profile.getStartHour1());

				cronStringList.add(str);

			}
			if (profile.getStartHour2() != ProcessSchedulerConstant.TWENTY_FOUR
					&& profile.getStartMinutes2() != ProcessSchedulerConstant.SIXTY) {
				String str = getCronString(profile.getStartMinutes2(), profile.getStartHour2());

				cronStringList.add(str);
			}
			if (profile.getStartHour3() != ProcessSchedulerConstant.TWENTY_FOUR
					&& profile.getStartMinutes3() != ProcessSchedulerConstant.SIXTY) {
				String str = getCronString(profile.getStartMinutes3(), profile.getStartHour3());

				cronStringList.add(str);

			}

		}
	}

	public static String getCronString(int minutes, int hour) {
		return "0 " + minutes + " " + hour + " * * ? * ";
	}

	public static void generateCronStringInterval(WorkflowProfile profile, List<String> cronStringList) {

		String cronString = null;
		if (!"Interval".equalsIgnoreCase(profile.getFrequency())) {
			return;
		}

		if (profile.getStartHour() == ProcessSchedulerConstant.TWENTY_FOUR
				&& profile.getStartMinutes() == ProcessSchedulerConstant.SIXTY) {
			return;
		}

		if (profile.getStartHour() == ProcessSchedulerConstant.ZERO
				&& profile.getStartMinutes() == ProcessSchedulerConstant.ZERO) {
			cronString = "0 0 0 * * ? * ";
			cronStringList.add(cronString);
			return;
		}

		if (profile.getStartMinutes() == ProcessSchedulerConstant.FOURTY_FIVE) {
			cronStringList.add("0 0,45 0,3,6,9,12,15,18,21 * * ? *");
			cronStringList.add("0 15 2,5,8,11,14,17,20,23 * * ? *");
			cronStringList.add("0 30 1,4,7,10,13,16,19,22 * * ? *");
			return;
		}

		if ((profile.getStartMinutes() > ProcessSchedulerConstant.ZERO)
				&& (profile.getStartHour() > ProcessSchedulerConstant.ZERO)) {
			if (profile.getStartHour() == ProcessSchedulerConstant.TWENTY_FOUR) {
				return;
			}
			List<String> generatedCronList = QuartzUtil.calculateCronStringForInterval(
					profile.getStartHour() * ProcessSchedulerConstant.SIXTY + profile.getStartMinutes());
			cronStringList.addAll(generatedCronList);
			return;
		}

		if (profile.getStartMinutes() > ProcessSchedulerConstant.ZERO) {
			cronString = "0 0/" + profile.getStartMinutes() + " * * * ? * ";
			cronStringList.add(cronString);
			return;
		}

		if (profile.getStartHour() > ProcessSchedulerConstant.ZERO) {
			cronString = "0 0 0/" + profile.getStartHour() + "  * * ? * ";
			cronStringList.add(cronString);
			return;
		}

	}

	public TriggerBuilder<Trigger> getTriggerBuilderWithDate(WorkflowProfile profile, int triggerNo)
			throws SchedulerException {
		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
		triggerBuilder.withIdentity(
				TRIGGER + triggerNo + "_" + profile.getProcessSid() + "_" + profile.getProcessDisplayName(), GROUP);

		if (triggerNo > 1) {
			triggerBuilder.forJob(getJobForJobKey(profile));
		}
		if (profile.getEndDate() == null && profile.getStartDate() == null) {
			return triggerBuilder;
		}
		Date startDate = getStartDate(profile.getStartDate());
		if (startDate.after(profile.getEndDate())) {
			return triggerBuilder;
		}
		if (profile.getStartDate() != null) {
			triggerBuilder.startAt(startDate);
		}

		if (profile.getEndDate() != null) {
			triggerBuilder.endAt(profile.getEndDate());
		}

		return triggerBuilder;
	}

	public synchronized JobKey getJobForJobKey(WorkflowProfile profile) throws SchedulerException {
		for (String groupName : scheduler.getJobGroupNames()) {

			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

				if (jobKey.getName().equals(generateJobName(profile))) {
					return jobKey;
				}

			}

		}
		return null;

	}

	public static Date getStartDate(Date inputDate) {
		Date current = new Date();
		if (inputDate.after(current) || inputDate.equals(current)) {
			return inputDate;
		}
		return DateBuilder.futureDate(1, IntervalUnit.MINUTE);

	}

	public void DeleteSchedule() {
		try {
			String time = "5hrs26";
			logger.info("time= " + time);
			String[] hrs = time.split("hrs");
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put(ACTION_JOB_DATA_MAP_KEY, "Delete");
			JobDetail job1 = JobBuilder.newJob(QuartzJob.class).setJobData(jobDataMap)
					.withIdentity(JOB + "deleteJob", GROUP).storeDurably().build();
			logger.info("hrs[0]= " + hrs[0] + "hrs[1]= " + hrs[1]);
			scheduler.addJob(job1, false);
			Trigger trigger2 = TriggerBuilder.newTrigger().forJob(job1).withIdentity(TRIGGER + "deleteTrigger")
					.withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(Integer.parseInt(hrs[0]),
							Integer.parseInt(hrs[1])))
					.build();
			scheduler.scheduleJob(trigger2);

		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}

	public void printJobList() {
		StringBuilder printStr = new StringBuilder("--Start Printing Jobs--\n");

		try {
			for (String groupName : scheduler.getJobGroupNames()) {

				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					// get job's trigger
					@SuppressWarnings("unchecked")
					List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);

					for (Trigger trigger : triggers) {
						Date nextFireTime = trigger.getNextFireTime();
						printStr.append("[jobName] : ").append(jobName).append(" [groupName] : ").append(jobGroup)
								.append(" - ").append(nextFireTime).append(" - First Fire time -")
								.append(trigger.getStartTime()).append(" -Final Fire Time- ")
								.append(trigger.getEndTime());
						printStr.append('\n');
					}

				}
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		printStr.append("--End printing Jobs--\n");
		logger.info(printStr.toString());
	}

	public synchronized void printJobsForJobKey(JobKey jobKeyToSearch) throws SchedulerException {
		try {
			for (String groupName : scheduler.getJobGroupNames()) {

				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					if (jobName.equals(jobKeyToSearch.getName())) {
						// get job's trigger
						@SuppressWarnings("unchecked")
						List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);

						for (Trigger trigger : triggers) {
							Date nextFireTime = trigger.getNextFireTime();
							System.out.println("[jobName] : " + jobName + " [groupName] : " + jobGroup + " - "
									+ nextFireTime + " - First Fire time -" + trigger.getStartTime()
									+ " -Final Fire Time- " + trigger.getEndTime());
						}
					}

				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
	}
}
