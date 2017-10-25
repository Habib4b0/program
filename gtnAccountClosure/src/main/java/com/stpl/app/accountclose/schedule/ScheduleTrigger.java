/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.schedule;

import com.stpl.app.accountclose.dao.BaseRateDAO;
import com.stpl.app.accountclose.dao.impl.BaseRateDAOImpl;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import org.apache.commons.lang.StringUtils;
import org.jboss.logging.Logger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author hazi.s
 */
public class ScheduleTrigger {

    BaseRateDAO dao = new BaseRateDAOImpl();
    private static final Logger LOGGER = Logger.getLogger(ScheduleTrigger.class);

    public void scheduleTrigger() {
        try {
            JobDetail job = JobBuilder.newJob(JobDetails.class)
                    .withIdentity("dummyJobName", "group1").build();
            JobDetail job2 = JobBuilder.newJob(JobDetails.class)
                    .withIdentity("dummyJobName2", "group2").build();
            JobDetail job3 = JobBuilder.newJob(JobDetails.class)
                    .withIdentity("dummyJobName3", "group3").build();
            TimeZone.setDefault(TimeZone.getTimeZone("IST"));
            List input = getWorkFlowProfileTime(StringUtils.EMPTY);
            Date date = setTimeForTrigger(Integer.valueOf(String.valueOf(input.get(0))), Integer.valueOf(String.valueOf(input.get(1))));
            Date date2 = setTimeForTrigger(Integer.valueOf(String.valueOf(input.get(2))), Integer.valueOf(String.valueOf(input.get(3))));
            Date date3 = setTimeForTrigger(Integer.valueOf(String.valueOf(input.get(4))), Integer.valueOf(String.valueOf(input.get(5))));
            Trigger triggerone = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName", "group1").startAt(date).build();
            Trigger triggertwo = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName2", "group2").startAt(date2).build();
            Trigger triggerthree = TriggerBuilder.newTrigger().withIdentity("dummyTriggerName3", "group3").startAt(date3).build();
            Scheduler schedulerone = new StdSchedulerFactory().getScheduler();
            schedulerone.start();
            schedulerone.scheduleJob(job, triggerone);
            Scheduler schedulertwo = new StdSchedulerFactory().getScheduler();
            schedulertwo.start();
            schedulertwo.scheduleJob(job2, triggertwo);
            Scheduler schedulerthree = new StdSchedulerFactory().getScheduler();
            schedulerthree.start();
            schedulerthree.scheduleJob(job3, triggerthree);
        } catch (Exception ex) {
            LOGGER.error(ex);
        }

    }

    private Date setTimeForTrigger(Integer hours, Integer minutes) {
        Date run = new Date();
        run.setHours(hours);
        run.setMinutes(minutes);
        run.setSeconds(0);
        return run;
    }

    private List getWorkFlowProfileTime(String processName) {
        List list = new ArrayList();
        try {
            processName = "COMPANY_MASTER_INTERFACE";
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT START_HOUR1,START_MINUTES1,").append("START_HOUR2,START_MINUTES2,START_HOUR3,START_MINUTES3")
                    .append(" FROM   WORKFLOW_PROFILE WHERE PROCESS_NAME='").append(processName).append("'");
            List<Object[]> object = (List<Object[]>) dao.executeSelectQuery(sql.toString());
            if (!object.isEmpty()) {
                for (int i = 0; i < object.size(); i++) {
                    Object[] workRunTime = object.get(i);
                    list.add(workRunTime[0] == null ? 0 : workRunTime[0]);
                    list.add(workRunTime[1] == null ? 0 : workRunTime[1]);
                    list.add(workRunTime[2] == null ? 0 : workRunTime[2]);
                    list.add(workRunTime[3] == null ? 0 : workRunTime[3]);
                    list.add(workRunTime[4] == null ? 0 : workRunTime[4]);
                    list.add(workRunTime[5] == null ? 0 : workRunTime[5]);
                }
            }
            return list;
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
        return null;
    }
}
