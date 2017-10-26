/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.quartz;

import com.stpl.app.global.company.dto.FinancialCloseDTO;
import com.stpl.app.global.company.logic.FinancialCloseLogic;
import com.stpl.app.global.company.util.QueryUtils;
import com.stpl.app.util.ConstantsUtils;
import com.stpl.ifs.ui.util.NumericConstants;
import com.vaadin.server.VaadinSession;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import org.jboss.logging.Logger;
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

/**
 *
 * @author rohitvignesh.s
 */
public class QuartzJob implements Job {

    FinancialCloseLogic logic = new FinancialCloseLogic();
    public static final Logger LOGGER = Logger.getLogger(QuartzJob.class);

    @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
//        String userId = (String) VaadinSession.getCurrent().getAttribute(ConstantsUtils.USER_ID); //Commented to avoid NullPointerException and took userId from dto.
        String jobKey = String.valueOf(jec.getJobDetail().getKey());
        String[] keyArray = jobKey.split("[.]");
        LOGGER.debug("Scheduler Started Excuting:");
        LOGGER.debug("keyArray - - " + keyArray[1]);
        LOGGER.debug("Scheduled Time  - - " + jec.getFireTime());
        Scheduler scheduler = null;
        for (Map.Entry<String, Object> object : jec.getMergedJobDataMap().entrySet()) {
            String jobDataKey = object.getKey();
            Object jobDataValue = object.getValue();
            FinancialCloseDTO financialCloseDTO = (FinancialCloseDTO) jobDataValue;
            if (keyArray[1].equals(jobDataKey)) {
                try {
                    scheduler = jec.getScheduler();
                    updateCompany(financialCloseDTO, financialCloseDTO.getCreatedBy());
                    scheduleUpdateAgain(scheduler, (FinancialCloseDTO) jobDataValue);
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            }
        }
        LOGGER.debug("Scheduler Ended ");
    }

    /**
     * This is the company information which we will update to main table
     *
     * @param dto
     * @param userId
     */
    private void updateCompany(final FinancialCloseDTO dto, final String userId) {
        List input = new ArrayList();
        input.add(userId);
        input.add(dto.getCalenderDdlb());
        input.add(dto.getMonth());
        input.add(dto.getYear());
        input.add(dto.getCompanyMasterSid());
        QueryUtils.updateAppData(input, "updateCompanyFinancial");
    }

    /**
     * Schedule The time again for next month in a same company
     *
     * @param scheduler
     * @param dto
     * @throws Exception
     */
    private void scheduleUpdateAgain(Scheduler scheduler, final FinancialCloseDTO dto) throws SchedulerException {

        Calendar calendar = QuartzListener.getTime(dto, Calendar.getInstance().get(Calendar.MONTH) + NumericConstants.TWO);
        JobDataMap jobDataMap = new JobDataMap();
        Date dateForquartz = calendar.getTime();
        dateForquartz.setHours(calendar.get(Calendar.HOUR_OF_DAY));
        dateForquartz.setMinutes(calendar.get(Calendar.MINUTE));
        jobDataMap.put(String.valueOf(dto.getCompanyMasterSid()), dto);
        JobDetail job1 = JobBuilder.newJob(QuartzJob.class).setJobData(jobDataMap).withIdentity(String.valueOf(dto.getCompanyMasterSid()), String.valueOf(dto.getCompanyMasterSid())).storeDurably()
                .build();
        scheduler.addJob(job1, false);
        Trigger trigg = TriggerBuilder
                .newTrigger()
                .forJob(job1)
                .withIdentity(String.valueOf(dto.getCompanyMasterSid()), String.valueOf(dto.getCompanyMasterSid()))
                .startAt(dateForquartz)
                //                    .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(time)
                //                            //                                            .withIntervalInHours(Integer.valueOf(1)).withIntervalInMinutes(Integer.valueOf(0))
                //                            .repeatForever()
                //                    )
                //                        .withIntervalInHours(Integer.valueOf(1)).withIntervalInMinutes(Integer.valueOf(0))
                .build();
        scheduler.start();
        scheduler.scheduleJob(trigg);
        LOGGER.debug("********************* SCHEDULED FOR NEXT MONTH ********************************************************* at" + dateForquartz);
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
            LOGGER.error(ex);
        }
    }
}
