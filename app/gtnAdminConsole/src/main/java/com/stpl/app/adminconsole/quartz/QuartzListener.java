/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
package com.stpl.app.adminconsole.quartz;

import static com.stpl.ifs.util.constants.GlobalConstants.getTimeConstant;

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
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import com.stpl.ifs.ui.util.NumericConstants;
import com.liferay.portal.kernel.dao.orm.DynamicQuery;

/**
 *
 * @author rohitvignesh.s
 */
public class QuartzListener implements ServletContextListener {

	private static Scheduler scheduler = null;
	protected static String ACTION_JOB_DATA_MAP_KEY = "jobData";
	private static final Logger LOGGER = LoggerFactory.getLogger(QuartzListener.class);


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

	public static synchronized void createQuartzScheduler() {
		try {
			if (scheduler == null) {
				scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
			} else {
				scheduler.shutdown();
				scheduler = new StdSchedulerFactory().getScheduler();
				scheduler.start();
			}

			DynamicQuery query = WorkflowProfileLocalServiceUtil.dynamicQuery();
			List<WorkflowProfile> list = WorkflowProfileLocalServiceUtil.dynamicQuery(query);

			// Setup the Job class and the Job group

			for (WorkflowProfile profile : list) {
				createJob(profile);
			}

			deleteSchedule();

			printJobList();

		} catch (Exception e) {
                    LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
		}

	}

	public static void createJob(WorkflowProfile profile) {
		try {
			if (!"Y".equals(profile.getActiveFlag())) {
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
				System.out.println("No triggers set for job " + profile.getProcessDisplayName());
			}

			int i = 0;
			for (String cronString : cronStringList) {

				if (i == 0) {
					try {
						Trigger trigger = getTriggerBuilderWithDate(profile, i + 1)
								.withSchedule(CronScheduleBuilder.cronSchedule(cronString)).build();
						System.out
								.println("- " + profile.getProcessDisplayName() + " Scheduling trigger " + cronString);
						scheduler.scheduleJob(job, trigger);
						i++;
					} catch (Exception e) {
						System.out.println(e);
					}
				} else {
					try {
						Trigger trigger = getTriggerBuilderWithDate(profile, i + 1)
								.withSchedule(CronScheduleBuilder.cronSchedule(cronString)).build();
						System.out
								.println("- " + profile.getProcessDisplayName() + " Scheduling trigger " + cronString);
						scheduler.scheduleJob(trigger);
					} catch (Exception e) {
						System.out.println(e);
					}
					i++;
				}

			}

			if (i == 0) {
				System.out.println("No triggers set for job " + profile.getProcessDisplayName());
			}

		} catch (Exception e) {
                    LOGGER.error(e.getMessage());
		}

	}
    public static final String GROUP = "Group";

	public static String generateJobName(WorkflowProfile profile) {
		return ConstantsUtils.JOB + profile.getProcessSid() + "_" + profile.getProcessDisplayName();
	}

	public static void generateCronStringInterval(WorkflowProfile profile, List<String> cronStringList) {

		String cronString = null;
		if (!"Interval".equalsIgnoreCase(profile.getFrequency())) {
			return;
		}

		if (profile.getStartHour() == NumericConstants.TWENTY_FOUR
				&& profile.getStartMinutes() == NumericConstants.SIXTY) {
			return;
		}

		if (profile.getStartHour() == 0 && profile.getStartMinutes() == 0) {
			cronString = "0 0 0 * * ? * ";
			cronStringList.add(cronString);
			return;
		}

		if (profile.getStartMinutes() == 45) {
			cronStringList.add("0 0,45 0,3,6,9,12,15,18,21 * * ? *");
			cronStringList.add("0 15 2,5,8,11,14,17,20,23 * * ? *");
			cronStringList.add("0 30 1,4,7,10,13,16,19,22 * * ? *");
			return;
		}

		if ((profile.getStartMinutes() > 0) && (profile.getStartHour() > 0)) {
			if (profile.getStartHour() == NumericConstants.TWENTY_FOUR) {
				return;
			}
			List<String> generatedCronList = QuartzUtil
					.calculateCronStringForInterval(profile.getStartHour() * 60 + profile.getStartMinutes());
			cronStringList.addAll(generatedCronList);
			return;
		}

		if (profile.getStartMinutes() > 0) {
			cronString = "0 0/" + profile.getStartMinutes() + " * * * ? * ";
			cronStringList.add(cronString);
			return;
		}

		if (profile.getStartHour() > 0) {
			cronString = "0 0 0/" + profile.getStartHour() + "  * * ? * ";
			cronStringList.add(cronString);
			return;
		}

	}

	public static void generateCronStringTime(WorkflowProfile profile, List<String> cronStringList) {

		if ("Time".equalsIgnoreCase(profile.getFrequency())) {
			if (profile.getStartHour1() != NumericConstants.TWENTY_FOUR
					&& profile.getStartMinutes1() != NumericConstants.SIXTY) {
				String str = getCronString(profile.getStartMinutes1(), profile.getStartHour1());

				cronStringList.add(str);

			}
			if (profile.getStartHour2() != NumericConstants.TWENTY_FOUR
					&& profile.getStartMinutes2() != NumericConstants.SIXTY) {
				String str = getCronString(profile.getStartMinutes2(), profile.getStartHour2());

				cronStringList.add(str);
			}
			if (profile.getStartHour3() != NumericConstants.TWENTY_FOUR
					&& profile.getStartMinutes3() != NumericConstants.SIXTY) {
				String str = getCronString(profile.getStartMinutes3(), profile.getStartHour3());

				cronStringList.add(str);

			}

		}
	}

	public static String getCronString(int minutes, int hour) {
		return "0 " + minutes + " " + hour + " * * ? * ";
	}

	public static TriggerBuilder<Trigger> getTriggerBuilderWithDate(WorkflowProfile profile, int triggerNo)
			throws SchedulerException {
		TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger();
		triggerBuilder.withIdentity(ConstantsUtils.TRIGGER + triggerNo + "_" + profile.getProcessSid() + "_"
				+ profile.getProcessDisplayName(), GROUP);

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

	public static Date getStartDate(Date inputDate) {
		Date current = new Date();
		if (inputDate.compareTo(current) >=0) {
			return inputDate;
		}
		return DateBuilder.futureDate(1, IntervalUnit.MINUTE);

	}

	public static void printJobList() {
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
						printStr.append("[jobName] : " ).append( jobName ).append( " [groupName] : " )
                                                        .append( jobGroup ).append( " - " ).append( nextFireTime ).append( " - First Fire time -" )
                                                        .append( trigger.getStartTime() ).append( " -Final Fire Time- ").append( trigger.getEndTime());
						printStr.append('\n');
					}

				}
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
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
			LOGGER.error(e.getMessage());
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

	public static void deleteSchedule() {
		try {
			String time = getTimeConstant();
			LOGGER.debug("time= {}" , time);
			String[] hrs = time.split("hrs");
			JobDataMap jobDataMap = new JobDataMap();
			jobDataMap.put(ACTION_JOB_DATA_MAP_KEY, "Delete");
			JobDetail job1 = JobBuilder.newJob(QuartzJob.class).setJobData(jobDataMap)
					.withIdentity(ConstantsUtils.JOB + "deleteJob", GROUP).storeDurably().build();
			LOGGER.debug("hrs[0]= {}, hrs[1]= {}" , hrs[0], hrs[1]);
			scheduler.addJob(job1, false);
			Trigger trigger2 = TriggerBuilder.newTrigger().forJob(job1)
					.withIdentity(ConstantsUtils.TRIGGER + "deleteTrigger").withSchedule(CronScheduleBuilder
							.dailyAtHourAndMinute(Integer.parseInt(hrs[0]), Integer.parseInt(hrs[1])))
					.build();
			scheduler.scheduleJob(trigger2);

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

}
