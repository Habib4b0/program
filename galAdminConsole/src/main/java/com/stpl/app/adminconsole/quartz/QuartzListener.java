/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.quartz;

import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.service.WorkflowProfileLocalServiceUtil;
import com.stpl.portal.kernel.dao.orm.DynamicQuery;
import com.stpl.portal.kernel.dao.orm.DynamicQueryFactoryUtil;
import com.stpl.portal.kernel.exception.SystemException;
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

/**
 *
 * @author rohitvignesh.s
 */
public class QuartzListener implements ServletContextListener {

    static Scheduler scheduler = null;
    static String ACTION_JOB_DATA_MAP_KEY = "jobData";
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(QuartzListener.class);

    /**
     * This method is used for initialising scheduling process. This method will
     * be called from listner from web.xml Creates jobs ans triggers for ETL
     * process
     *
     * @param servletContext
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        LOGGER.info("Context Initialized");
        List<WorkflowProfile> list = null;

        try {
            scheduler = new StdSchedulerFactory().getScheduler();
            scheduler.start();
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
        } catch (SchedulerException e) {
            LOGGER.error(e);
        }
    }
    /*
     * This method will shut down the sceduler
     */

    @Override
    public void contextDestroyed(ServletContextEvent servletContext) {
        LOGGER.info("Context Destroyed");
        try {
            scheduler.shutdown();
        } catch (SchedulerException e) {
            LOGGER.error(e);
        }
    }

    /**
     * This method is used to reschedule the jobs is called when profile is
     * updated in process scheduler UI Removes/Updates/Adds new triggers
     * Removes/Updates/Adds new jobs
     *
     * @param profile obj
     */
    public static void reScheduleJobs(WorkflowProfile profile) {
        LOGGER.info("Context Destroyed");
        try {
            if (profile != null) {
                boolean jobPresent = false;
                boolean triggerPre = false;
                boolean triggerPre1 = false;
                boolean triggerPre2 = false;
                JobKey jobKeyPresent = null;
                for (String groupName : scheduler.getJobGroupNames()) {
                    for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
                        if ((ConstantsUtils.JOB + profile.getProcessSid()).equals(scheduler.getJobDetail(jobKey).getKey().getName())) {
                            jobPresent = true;
                            jobKeyPresent = jobKey;
                            scheduler.getJobDetail(jobKey).getJobDataMap().clear();
                            scheduler.getJobDetail(jobKey).getJobDataMap().put(ACTION_JOB_DATA_MAP_KEY, profile);
                        }
                    }
                }
                if (jobPresent && jobKeyPresent != null) {
                    if (!"Y".equals(profile.getActiveFlag())) {
                        scheduler.deleteJob(jobKeyPresent);
                    } else {
                        List<Trigger> triggersList = (List<Trigger>) scheduler.getTriggersOfJob(jobKeyPresent);
                        for (Trigger trigger : triggersList) {
                            if (((ConstantsUtils.TRIGGER + profile.getProcessSid()).equals(trigger.getKey().getName())) || ((ConstantsUtils.TRIGGER + "1" + profile.getProcessSid()).equals(trigger.getKey().getName())) || ((ConstantsUtils.TRIGGER + "2" + profile.getProcessSid()).equals(trigger.getKey().getName()))) {
                                if (!ConstantsUtils.INTERVAL.equalsIgnoreCase(profile.getFrequency())) {
                                    if (profile.getStartHour1() == 24 && profile.getStartMinutes1() == 60 && !ConstantsUtils.INTERVAL.equalsIgnoreCase(profile.getFrequency())) {
                                        removeTrigger(trigger);
                                    }
                                } else if (ConstantsUtils.INTERVAL.equalsIgnoreCase(profile.getFrequency())) {
                                    removeTrigger(trigger);
                                } else {
                                    updateTrigger(trigger, profile);
                                }
                                triggerPre = true;
                            }
                            if (!ConstantsUtils.INTERVAL.equalsIgnoreCase(profile.getFrequency())) {
                                if ((ConstantsUtils.TRIGGER + profile.getProcessSid()).equals(trigger.getKey().getName())) {
                                    if (profile.getStartHour1() == 24) {
                                        removeTrigger(trigger);
                                    } else {
                                        updateTrigger(trigger, profile);
                                        triggerPre1 = true;
                                    }
                                }

                                if ((ConstantsUtils.TRIGGER + "1" + profile.getProcessSid()).equals(trigger.getKey().getName())) {
                                    if (profile.getStartHour2() == 24) {
                                        removeTrigger(trigger);
                                    } else {
                                        updateTrigger(trigger, profile);
                                        triggerPre1 = true;
                                    }
                                }
                                if ((ConstantsUtils.TRIGGER + "2" + profile.getProcessSid()).equals(trigger.getKey().getName())) {
                                    if (profile.getStartHour3() == 24) {
                                        removeTrigger(trigger);
                                    } else {
                                        updateTrigger(trigger, profile);
                                        triggerPre2 = true;
                                    }

                                }
                            } else {

                                if (ConstantsUtils.INTERVAL.equalsIgnoreCase(profile.getFrequency())) {
                                    if (profile.getStartHour() == 0 && profile.getStartMinutes() == 0) {
                                        removeTrigger(trigger);
                                    }
                                }
                                if (((ConstantsUtils.TRIGGER + profile.getProcessSid()).equals(trigger.getKey().getName())) || ((ConstantsUtils.TRIGGER + "1" + profile.getProcessSid()).equals(trigger.getKey().getName())) || ((ConstantsUtils.TRIGGER + "2" + profile.getProcessSid()).equals(trigger.getKey().getName()))) {
                                    removeTrigger(trigger);
                                }
                            }
                        }
                        if ((!triggerPre && profile.getStartHour1() != 24 && profile.getStartMinutes1() != 60) || (!triggerPre1 && profile.getStartHour2() != 24 && profile.getStartMinutes2() != 60) || (!triggerPre2 && profile.getStartHour3() != 24 && profile.getStartMinutes3() != 60) || (!triggerPre && profile.getStartHour() != 24) && profile.getStartMinutes() != 60) {
                            addTriggerToExistingJob(jobKeyPresent, profile, triggerPre, triggerPre1, triggerPre2);

                        }
                    }

                } else {
                    if ("Y".equals(profile.getActiveFlag())) {
                        List<Trigger> triggersList = (List<Trigger>) scheduler.getTriggersOfJob(jobKeyPresent);
                        for (Trigger trigger : triggersList) {
                            removeTrigger(trigger);
                        }
                        createJob(profile);

                    }
                }

            }
        } catch (SchedulerException e) {
            LOGGER.error(e);
        }

    }

    /**
     * This updates the trigger of the job already registed
     *
     * @param oldTrigger
     * @param profile
     */
    private static void updateTrigger(Trigger oldTrigger, WorkflowProfile profile) {
        if (ConstantsUtils.INTERVAL.equalsIgnoreCase(profile.getFrequency())) {
             if (profile.getStartHour() != 0 || profile.getStartMinutes() != 0)
             {   
            SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                    .withIntervalInMinutes(profile.getStartMinutes() == 60 ? 0 : profile.getStartMinutes());
            if (profile.getStartHour() != 0 && profile.getStartMinutes() != 0) {
               
                scheduleBuilder.withIntervalInHours(profile.getStartHour());
                
            }
            scheduleBuilder.repeatForever();
            Trigger newTrigger = TriggerBuilder
                    .newTrigger()
                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInHours(profile.getStartHour()).withIntervalInMinutes(profile.getStartMinutes())
                                    .repeatForever())
                    .build();
            try {
                scheduler.rescheduleJob(oldTrigger.getKey(), newTrigger);
            } catch (SchedulerException ex) {
                LOGGER.error(ex);
            }
        }} else {

            if ((ConstantsUtils.TRIGGER + profile.getProcessSid()).equals(oldTrigger.getKey().getName())) {
                Trigger trig1 = TriggerBuilder.newTrigger()
                        .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(profile.getStartHour1(), profile.getStartMinutes1() == 60 ? 0 : profile.getStartMinutes1()))
                        .build();
                try {
                    scheduler.rescheduleJob(oldTrigger.getKey(), trig1);
                } catch (SchedulerException ex) {
                    LOGGER.error(ex);
                }
            }
            if ((ConstantsUtils.TRIGGER + "1" + profile.getProcessSid()).equals(oldTrigger.getKey().getName())) {
                Trigger trig2 = TriggerBuilder.newTrigger()
                        .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(profile.getStartHour2(), profile.getStartMinutes2() == 60 ? 0 : profile.getStartMinutes2()))
                        .build();
                try {
                    scheduler.rescheduleJob(oldTrigger.getKey(), trig2);
                } catch (SchedulerException ex) {
                    LOGGER.error(ex);
                }
            }
            if ((ConstantsUtils.TRIGGER + "2" + profile.getProcessSid()).equals(oldTrigger.getKey().getName())) {
                Trigger trig = TriggerBuilder.newTrigger()
                        .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(profile.getStartHour3(), profile.getStartMinutes3() == 60 ? 0 : profile.getStartMinutes3()))
                        .build();
                try {

                    scheduler.rescheduleJob(oldTrigger.getKey(), trig);
                } catch (SchedulerException ex) {
                    LOGGER.error(ex);
                }
            }
        }
    }

    /**
     * This method is used to addd triggers to already registered jobs
     *
     * @param jobKeyPresent job key of job which is already registered in
     * sceduler
     * @param profile obj
     * @param triggerPre start hour1 trigger
     * @param triggerPre1 start hour2 trigger
     * @param triggerPre2 start hour 3 trigger
     */
    private static void addTriggerToExistingJob(JobKey jobKeyPresent, WorkflowProfile profile, boolean triggerPre, boolean triggerPre1, boolean triggerPre2) {
        if (!ConstantsUtils.INTERVAL.equalsIgnoreCase(profile.getFrequency())) {
            if (!triggerPre && profile.getStartHour1() != 24) {
                try {
                    Trigger trigger2 = TriggerBuilder.newTrigger()
                            .forJob(scheduler.getJobDetail(jobKeyPresent))
                            .withIdentity(ConstantsUtils.TRIGGER + profile.getProcessSid())
                            .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(profile.getStartHour1(), profile.getStartMinutes1() == 24 ? 0 : profile.getStartMinutes1()))
                            .build();
                    scheduler.scheduleJob(trigger2);
                } catch (SchedulerException ex) {
                    LOGGER.error(ex);
                }
            }
            if (!triggerPre1 && profile.getStartHour2() != 24) {
                try {
                    Trigger trigger2 = TriggerBuilder.newTrigger()
                            .forJob(scheduler.getJobDetail(jobKeyPresent))
                            .withIdentity(ConstantsUtils.TRIGGER + "1" + profile.getProcessSid())
                            .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(profile.getStartHour2(), profile.getStartMinutes2() == 24 ? 0 : profile.getStartMinutes2()))
                            .build();
                    scheduler.scheduleJob(trigger2);
                } catch (SchedulerException ex) {
                    LOGGER.error(ex);
                }
            }
            if (!triggerPre2 && profile.getStartHour3() != 24) {
                try {
                    Trigger trigger2 = TriggerBuilder.newTrigger()
                            .forJob(scheduler.getJobDetail(jobKeyPresent))
                            .withIdentity(ConstantsUtils.TRIGGER + "2" + profile.getProcessSid())
                            .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(profile.getStartHour3(), profile.getStartMinutes3() == 24 ? 0 : profile.getStartMinutes3()))
                            .build();
                    scheduler.scheduleJob(trigger2);
                } catch (SchedulerException ex) {
                    LOGGER.error(ex);
                }
            }

        } else {
            if (!triggerPre && profile.getStartHour() != 24) {
                if (profile.getStartHour() != 0 || profile.getStartMinutes() != 0) {

                    if (profile.getStartHour() < 1 && profile.getStartMinutes() > 0) {
                        try {
                            Trigger trigg = TriggerBuilder
                                    .newTrigger()
                                    .forJob(scheduler.getJobDetail(jobKeyPresent))
                                    .startAt(DateBuilder.futureDate(profile.getStartMinutes(), IntervalUnit.MINUTE))
                                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(profile.getStartMinutes()).repeatForever())
                                    .withIdentity(ConstantsUtils.TRIGGER + profile.getProcessSid(), "Group")
                                    .build();
                            scheduler.scheduleJob(trigg);
                        } catch (SchedulerException ex) {
                            LOGGER.error(ex);
                        }

                    } else {
                        try {
                            Trigger trigg = TriggerBuilder
                                    .newTrigger()
                                    .forJob(scheduler.getJobDetail(jobKeyPresent))
                                    .withIdentity(ConstantsUtils.TRIGGER + profile.getProcessSid(), "Group")
                                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInHours(profile.getStartHour()).withIntervalInMinutes(profile.getStartMinutes())
                                    .repeatForever())
                                    .build();
                            scheduler.scheduleJob(trigg);
                        } catch (SchedulerException ex) {
                            LOGGER.error(ex);
                        }

                    }

                }
            }
        }

    }

    /**
     * This method is used to de attach the trigger from job but does not delete
     * job from scheduler. Job will be present in scheduler but trigger will be
     * removed from scheduler
     *
     * @param trigger trigger which should be de attached from Specific job
     */
    private static void removeTrigger(Trigger trigger) {
        try {
            scheduler.unscheduleJob(trigger.getKey());
        } catch (SchedulerException ex) {
            LOGGER.error(ex);
        }

    }

    /**
     * This method is used to create job for each profile object i.e., interface
     * creates interval/specific time job based on frequency
     *
     * @param profile object from workflow profile table
     */
    private static void createJob(WorkflowProfile profile) {
        try {
            if ("Y".equals(profile.getActiveFlag())) {
                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put(ACTION_JOB_DATA_MAP_KEY, profile);
                if ("Interval".equalsIgnoreCase(profile.getFrequency()) && profile.getStartHour() != 24 && profile.getStartMinutes() != 60) {
                    if (profile.getStartHour() != 0 || profile.getStartMinutes() != 0) {
                        JobDetail job = JobBuilder
                                .newJob(QuartzJob.class)
                                .setJobData(jobDataMap)
                                .withIdentity(ConstantsUtils.JOB + profile.getProcessSid(), "Group")
                                .build();

                        if (profile.getStartHour() < 1 && profile.getStartMinutes() > 0) {


                            Trigger trigg = TriggerBuilder
                                    .newTrigger()
                                    .startAt(DateBuilder.futureDate(profile.getStartMinutes(), IntervalUnit.MINUTE))
                                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMinutes(profile.getStartMinutes()).repeatForever())
                                    .withIdentity(ConstantsUtils.TRIGGER + profile.getProcessSid(), "Group")
                                    .build();
                            scheduler.scheduleJob(job, trigg);


                        } else {
                            Trigger trigg;
                            trigg = TriggerBuilder
                                    .newTrigger()
                                    .withIdentity(ConstantsUtils.TRIGGER + profile.getProcessSid(), "Group")
                                    .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                                    .withIntervalInHours(profile.getStartHour())
                                    .withIntervalInMinutes(profile.getStartMinutes())
                                    .repeatForever())
                                    .build();

                            // Setup the Job and Trigger with Scheduler & schedule jobs
                            scheduler.scheduleJob(job, trigg);
                        }
                    }
                } else {
                    JobDetail job1 = JobBuilder
                            .newJob(QuartzJob.class)
                            .setJobData(jobDataMap)
                            .withIdentity(ConstantsUtils.JOB + profile.getProcessSid(), "Group")
                            .storeDurably()
                            .build();

                    scheduler.addJob(job1, false);
                    if (profile.getStartHour1() != 24 && profile.getStartMinutes1() != 60) {
                        Trigger trigger2 = TriggerBuilder.newTrigger()
                                .forJob(job1)
                                .withIdentity(ConstantsUtils.TRIGGER + profile.getProcessSid())
                                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(profile.getStartHour1(), profile.getStartMinutes1() == 24 ? 0 : profile.getStartMinutes1()))
                                .build();
                        scheduler.scheduleJob(trigger2);
                    }
                    if (profile.getStartHour2() != 24 && profile.getStartMinutes2() != 60) {
                        Trigger trigger3 = TriggerBuilder.newTrigger()
                                .forJob(job1)
                                .withIdentity(ConstantsUtils.TRIGGER + "1" + profile.getProcessSid())
                                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(profile.getStartHour2(), profile.getStartMinutes2() == 24 ? 0 : profile.getStartMinutes2()))
                                .build();
                        scheduler.scheduleJob(trigger3);
                    }
                    if (profile.getStartHour3() != 24 && profile.getStartMinutes3() != 60) {
                        Trigger trigger4 = TriggerBuilder.newTrigger()
                                .forJob(job1)
                                .withIdentity(ConstantsUtils.TRIGGER + "2" + profile.getProcessSid())
                                .withSchedule(CronScheduleBuilder.dailyAtHourAndMinute(profile.getStartHour3(), profile.getStartMinutes3() == 24 ? 0 : profile.getStartMinutes3()))
                                .build();
                        scheduler.scheduleJob(trigger4);
                    }
                }

            }
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
