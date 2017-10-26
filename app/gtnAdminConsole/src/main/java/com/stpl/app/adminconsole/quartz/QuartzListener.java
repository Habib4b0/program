/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package com.stpl.app.adminconsole.quartz;

import static com.stpl.ifs.util.constants.GlobalConstants.getTimeConstant;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.quartz.CronScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;

/**
 *
 * @author rohitvignesh.s
 */
public class QuartzListener implements ServletContextListener {

	static Scheduler scheduler = null;
	static String ACTION_JOB_DATA_MAP_KEY = "jobData";
	private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(QuartzListener.class);
	static SimpleDateFormat sdf = new SimpleDateFormat(ConstantsUtils.DATE_FORMAT_TO_PARSE);
	private static final String USE_SIMPLE_SCHEDULE = "USESIMPLESCHEDULE";

	/**
	 * This method is used for initialising scheduling process. This method will
	 * be called from listner from web.xml Creates jobs ans triggers for ETL
	 * process
	 *
	 * @param servletContext
	 */
	@Override
	public void contextInitialized(ServletContextEvent servletContext) {
		System.out.println("Context Initialized");

		createQuartzScheduler();

	}

	public synchronized static void createQuartzScheduler() {
		List<WorkflowProfile> list = null;
		try {
			if (scheduler == null) {
				scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
			} else {
				scheduler.shutdown();
				scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
			}

			DynamicQuery query = DynamicQueryFactoryUtil.forClass(WorkflowProfile.class);
			try {
				list = WorkflowProfileLocalServiceUtil.dynamicQuery(query);

			} catch (SystemException ex) {
				LOGGER.error(ex);
			}
			// Setup the Job class and the Job group

			for (WorkflowProfile profile : list) {
				createJob(profile);
			}

			DeleteSchedule();

			printJobList();

		} catch (Exception e) {
			LOGGER.error(e, e);
		}
	}

	/*
	 * This method will shut down the sceduler
	 */
	@Override
	public void contextDestroyed(ServletContextEvent servletContext) {
		LOGGER.debug("Context Destroyed");
		try {
			scheduler.shutdown();
		} catch (SchedulerException e) {
			LOGGER.error(e);
		}
	}

	public static void updateJob(WorkflowProfile profile) {
		try {
			JobKey jobKeyToUpdate = getJobForJobKey(profile);
			if (jobKeyToUpdate != null) {
				System.out.println("Deleting job " + generateJobName(profile));
				scheduler.deleteJob(jobKeyToUpdate);
			}
			createJob(profile);
			printJobList();

		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	public static void createJob(WorkflowProfile profile) {
		try {
			if (!"Y".equals(profile.getActiveFlag())) {
				return;
			}
			if ( ( profile.getStartDate() == null) && (profile.getEndDate() == null)) {
				return;
			}			
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put(ACTION_JOB_DATA_MAP_KEY, profile);
			JobDetail job = JobBuilder.newJob(QuartzJob.class).setJobData(jobDataMap)
					.withIdentity(generateJobName(profile), "Group").build();
			String cronStringInterval = generateCronStringInterval(profile);
			if (cronStringInterval != null) {

				if (cronStringInterval.equals(USE_SIMPLE_SCHEDULE)) {

					Trigger trigger = createSimpleScheduleJobTrigger(profile);
					scheduler.scheduleJob(job, trigger);
					return;
				}
				Trigger trigger = getTriggerBuilderWithDate(profile, 1)
						.withSchedule(CronScheduleBuilder.cronSchedule(cronStringInterval)).build();
				scheduler.scheduleJob(job, trigger);
				return;

			}

			List<String> cronStringList = generateCronStringTime(profile);
			if (cronStringList == null) {
				return;
			}
			int i = 0;
			for (String cronString : cronStringList) {

				if (i == 0) {
					Trigger trigger = getTriggerBuilderWithDate(profile, 1)
							.withSchedule(CronScheduleBuilder.cronSchedule(cronString)).build();
					System.out.println("Scheduling trigger " + cronString);
					scheduler.scheduleJob(job, trigger);
				} else {
					Trigger trigger = getTriggerBuilderWithDate(profile, i + 1)
							.withSchedule(CronScheduleBuilder.cronSchedule(cronString)).build();
					System.out.println("Scheduling trigger " + cronString);
					scheduler.scheduleJob(trigger);
				}
				i++;

			}
			return;
		} catch (Exception e) {
			LOGGER.error(e);
		}

	}

	public static Trigger createSimpleScheduleJobTrigger(WorkflowProfile profile) throws SchedulerException {

		int intervalInMins = profile.getStartHour() * 60 + profile.getStartMinutes();
		Trigger trigger = null;
		if (profile.getStartDate() == null) {
			trigger = getTriggerBuilderWithDate(profile, 1)
					.startAt(DateBuilder.futureDate(intervalInMins, IntervalUnit.MINUTE))
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(intervalInMins)
							.repeatForever())
					.build();
		} else {
			trigger = getTriggerBuilderWithDate(profile, 1).withSchedule(
					SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(intervalInMins).repeatForever())
					.build();
		}

		return trigger;
	}

	public static void printJobList() {
		StringBuilder printStr = new StringBuilder("--Start Printing Jobs--\n");

		try {
			for (String groupName : scheduler.getJobGroupNames()) {

				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					// get job's trigger
					List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);

					for (Trigger trigger : triggers) {
						Date nextFireTime = trigger.getNextFireTime();
						printStr.append("[jobName] : " + jobName + " [groupName] : " + jobGroup + " - " + nextFireTime
								+ " - First Fire time -" + trigger.getStartTime() + " -Final Fire Time- "
								+ trigger.getFinalFireTime());
						printStr.append("\n");
					}

				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
		}
		printStr.append("--End printing Jobs--\n");
		System.out.println(printStr.toString());
	}

	public static synchronized void printJobsForJobKey(JobKey jobKeyToSearch) throws SchedulerException {
		try {
			for (String groupName : scheduler.getJobGroupNames()) {

				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();

					if (jobName.equals(jobKeyToSearch.getName())) {
						// get job's trigger
						List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);

						for (Trigger trigger : triggers) {
							Date nextFireTime = trigger.getNextFireTime();
							System.out.println("[jobName] : " + jobName + " [groupName] : " + jobGroup + " - "
									+ nextFireTime + " - First Fire time -" + trigger.getStartTime()
									+ " -Final Fire Time- " + trigger.getFinalFireTime());
						}
					}

				}
			}
		} catch (Exception e) {
			LOGGER.error(e);
			e.printStackTrace();
		}
	}

	public static synchronized JobKey getJobForJobKey(WorkflowProfile profile) throws SchedulerException {
		for (String groupName : scheduler.getJobGroupNames()) {

			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {

				if (jobKey.getName().equals(generateJobName(profile))) {
					return jobKey;
				}

			}

		}
		return null;

	}

	public static String generateJobName(WorkflowProfile profile) {
		return ConstantsUtils.JOB + profile.getProcessSid() + "_" + profile.getProcessDisplayName();
	}

	public static TriggerBuilder<Trigger> getTriggerBuilderWithDate(WorkflowProfile profile, int triggerNo)
			throws SchedulerException {
		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
		triggerBuilder.withIdentity(ConstantsUtils.TRIGGER + triggerNo + "_" + profile.getProcessSid() + "_"
				+ profile.getProcessDisplayName(), "Group");

		if (triggerNo > 1) {
			triggerBuilder.forJob(getJobForJobKey(profile));
		}
		if (profile.getEndDate() == null && profile.getStartDate() == null) {
			return triggerBuilder;
		}
                Date startDate  = getStartDate(profile.getStartDate());
                if(startDate.after(profile.getEndDate())){
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

	public static Date getStartDate(Date inputDate) {
		Date current = new Date();
		if (inputDate.after(current) || inputDate.equals(current)) {
			return inputDate;
		}
		return DateBuilder.futureDate(1, IntervalUnit.MINUTE);

	}

	public static String generateCronStringInterval(WorkflowProfile profile) {

		String cronString = null;
		if (!"Interval".equalsIgnoreCase(profile.getFrequency())) {
			return null;
		}
		if (profile.getStartHour() == 0 && profile.getStartMinutes() == 0) {
			return null;
		}
		if (profile.getStartHour() == NumericConstants.TWENTY_FOUR
				&& profile.getStartMinutes() == NumericConstants.SIXTY) {
			return null;
		}

		if (profile.getStartHour() > 0 && profile.getStartMinutes() > 0) {
			return USE_SIMPLE_SCHEDULE;
		}

		if (profile.getStartMinutes() > 0) {
			cronString = "0 0/" + profile.getStartMinutes() + " * * * ? * ";
		}

		if (profile.getStartHour() > 0) {
			cronString = "0 0 0/" + profile.getStartHour() + "  * * ? * ";
		}

		return cronString;
	}

	public static List<String> generateCronStringTime(WorkflowProfile profile) {

		List<String> cronStringList = null;
		if ("Time".equalsIgnoreCase(profile.getFrequency())) {
			if (profile.getStartHour1() != NumericConstants.TWENTY_FOUR
					&& profile.getStartMinutes1() != NumericConstants.SIXTY) {
				String str = getCronString(profile.getStartMinutes1(), profile.getStartHour1());
				if (cronStringList == null) {
					cronStringList = new ArrayList<String>();
				}
				System.out.println("Tirgger1 " + str);
				cronStringList.add(str);

			}
			if (profile.getStartHour2() != NumericConstants.TWENTY_FOUR
					&& profile.getStartMinutes2() != NumericConstants.SIXTY) {
				String str = getCronString(profile.getStartMinutes2(), profile.getStartHour2());
				if (cronStringList == null) {
					cronStringList = new ArrayList<String>();
				}
				System.out.println("Tirgger2 " + str);
				cronStringList.add(str);

			}
			if (profile.getStartHour3() != NumericConstants.TWENTY_FOUR
					&& profile.getStartMinutes3() != NumericConstants.SIXTY) {
				String str = getCronString(profile.getStartMinutes3(), profile.getStartHour3());
				if (cronStringList == null) {
					cronStringList = new ArrayList<String>();
				}
				System.out.println("Tirgger3 " + str);
				cronStringList.add(str);

			}

		}
		return cronStringList;
	}

	public static String getCronString(int minutes, int hour) {
		return "0 " + minutes + " " + hour + " * * ? * ";
	}

	public static void DeleteSchedule() {
		try {
			String time = getTimeConstant();
			LOGGER.debug("time" + time);
			String[] hrs = time.split("hrs");
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put(ACTION_JOB_DATA_MAP_KEY, "Delete");
			JobDetail job1 = JobBuilder.newJob(QuartzJob.class).setJobData(jobDataMap)
					.withIdentity(ConstantsUtils.JOB + "deleteJob", "Group").storeDurably().build();
			LOGGER.debug(hrs[0] + "hrs" + hrs[1]);
			scheduler.addJob(job1, false);
			Trigger trigger2 = TriggerBuilder.newTrigger().forJob(job1)
					.withIdentity(ConstantsUtils.TRIGGER + "deleteTrigger").withSchedule(CronScheduleBuilder
							.dailyAtHourAndMinute(Integer.parseInt(hrs[0]), Integer.parseInt(hrs[1])))
					.build();
			scheduler.scheduleJob(trigger2);

		} catch (Exception e) {
			LOGGER.error(e);
		}
	}

}
