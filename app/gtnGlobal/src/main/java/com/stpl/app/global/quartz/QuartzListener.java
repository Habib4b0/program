/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.quartz;

import com.stpl.app.global.company.dto.FinancialCloseDTO;
import com.stpl.app.global.company.logic.FinancialCloseLogic;
import com.stpl.app.global.company.util.CommonUtils;
import com.stpl.app.global.company.util.QueryUtils;
import com.stpl.app.util.HelperDTO;
import com.stpl.ifs.ui.util.NumericConstants;
import com.stpl.portal.kernel.exception.PortalException;
import com.stpl.portal.kernel.exception.SystemException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.lang.StringUtils;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
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
        LOGGER.debug("Context Initialized - - ");

        try {
            scheduler = STPLQuartzScheduler.getIntance().getScheduler();
            scheduler.start();
            // Setup the Job class and the Job group
            scheduleCompanyFinancialClose();
        } catch (SchedulerException e) {
            LOGGER.error(e);
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

    /**
     * Method to schedule scheduler for company master
     */
    public static void scheduleCompanyFinancialClose() {
        try {
            scheduler = STPLQuartzScheduler.getIntance().getScheduler();
            scheduler.start();
            List<FinancialCloseDTO> list = getCustomziedDTO(QueryUtils.getAppData(new ArrayList(), "getCompanyAuto", null));
            for (FinancialCloseDTO financialCloseDTO : list) {
                killJob(String.valueOf(financialCloseDTO.getCompanyMasterSid()));
                Calendar cal = getTime(financialCloseDTO, Calendar.getInstance().get(Calendar.MONTH) + 1);
                Date dateForquartz = cal.getTime();
                dateForquartz.setHours(cal.get(Calendar.HOUR_OF_DAY));
                dateForquartz.setMinutes(cal.get(Calendar.MINUTE));
                financialCloseDTO.setMonth(String.valueOf(cal.get(Calendar.MONTH)));
                financialCloseDTO.setYear(String.valueOf(cal.get(Calendar.YEAR)));
                JobDataMap jobDataMap = new JobDataMap();
                jobDataMap.put(String.valueOf(financialCloseDTO.getCompanyMasterSid()), financialCloseDTO);

                JobDetail job1 = JobBuilder
                        .newJob(QuartzJob.class)
                        .setJobData(jobDataMap)
                        .withIdentity(String.valueOf(financialCloseDTO.getCompanyMasterSid()))
                        .storeDurably()
                        .build();
                scheduler.addJob(job1, false);
                Trigger trigg = TriggerBuilder
                        .newTrigger()
                        .forJob(job1)
                        .withIdentity(String.valueOf(financialCloseDTO.getCompanyMasterSid()))
                        .startAt(dateForquartz)
                        //                        .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        //                                .withIntervalInHours(Integer.valueOf(financialCloseDTO.getHourDdlb())).withIntervalInMinutes(Integer.valueOf(financialCloseDTO.getMinuteValue()))
                        //                                .repeatForever())
                        .build();
                scheduler.scheduleJob(trigg);
            }
        } catch (Exception ex) {
            LOGGER.error(ex);
        }
    }

    /**
     * Getting company informations from temp table
     *
     * @param list
     * @return List<FinancialCloseDTO>
     */
    public static List<FinancialCloseDTO> getCustomziedDTO(List<Object[]> list) {
        List<FinancialCloseDTO> finalList = new ArrayList<>();
        for (Object[] obj : list) {
            try {
                FinancialCloseDTO dto = new FinancialCloseDTO();
                HelperDTO helperDto = new HelperDTO();
                dto.setCompanyMasterSid(obj[0] != null ? Integer.valueOf(String.valueOf(obj[0])) : 0);
                dto.setBusinessDayDdlb(obj[1] != null ? String.valueOf(obj[1]) : StringUtils.EMPTY);
                dto.setHourDdlb(obj[NumericConstants.TWO] != null ? String.valueOf(obj[NumericConstants.TWO]) : StringUtils.EMPTY);
                dto.setMinuteValue(obj[NumericConstants.THREE] != null ? String.valueOf(obj[NumericConstants.THREE]) : StringUtils.EMPTY);
                dto.setCalenderDdlb(obj[NumericConstants.FOUR] != null ? Integer.valueOf(String.valueOf(obj[NumericConstants.FOUR])) : 0);
                helperDto.setDescription(CommonUtils.loadDescription(Integer.valueOf(String.valueOf(obj[NumericConstants.THREE]))));
                dto.setMinuteDdlb(helperDto);
                dto.setCreatedBy(obj[NumericConstants.FIVE] != null ? String.valueOf(obj[NumericConstants.FIVE]) : StringUtils.EMPTY);
                finalList.add(dto);
            } catch (PortalException ex) {
                Logger.getLogger(QuartzListener.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SystemException ex) {
                Logger.getLogger(QuartzListener.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return finalList;
    }

    /**
     * This is the method to kill Jobs whenever used do automatic save
     *
     */
    public static void killJob(String companyMasterSid) {
        try {
            JobKey jobKey = new JobKey(companyMasterSid);
            TriggerKey trigger = new TriggerKey(companyMasterSid);
            if (scheduler != null) {
                scheduler.deleteJob(jobKey);
                scheduler.unscheduleJob(trigger);
            }
        } catch (SchedulerException ex) {
            LOGGER.error(ex);
        }
    }


    public static Calendar getTime(FinancialCloseDTO dto, int month) {
        SimpleDateFormat form = new SimpleDateFormat("EEEE");
        List currMonthHolidays = FinancialCloseLogic.holidaysForCurrentMonth(dto.getCalenderDdlb(), month);
        List<Object> noOfDaysInMonth = new ArrayList();
            for (int i = 1; i <=NumericConstants.THIRTY_ONE ; i++) {
               noOfDaysInMonth.add(i);
            }
        noOfDaysInMonth.removeAll(currMonthHolidays); 
        int workingday = Integer.valueOf(String.valueOf(noOfDaysInMonth.get(Integer.valueOf(dto.getBusinessDayDdlb())-1)));        
        Calendar calendar = Calendar.getInstance();
        
        Calendar date;
        if(workingday >= calendar.get(Calendar.DATE)){
            date = FinancialCloseLogic.findScheduleDate(Integer.valueOf(dto.getBusinessDayDdlb()), currMonthHolidays, form, NumericConstants.FIVE);
        } else {
            date = FinancialCloseLogic.findScheduleDate(Integer.valueOf(dto.getBusinessDayDdlb()), FinancialCloseLogic.holidaysForCurrentMonth(dto.getCalenderDdlb(), month+1),form, NumericConstants.FIVE);
        }     
        date.set(Calendar.HOUR_OF_DAY, Integer.valueOf(dto.getHourDdlb())); //Changed to work in 24 hour, hour selection
        date.set(Calendar.MINUTE, Integer.valueOf(dto.getMinuteDdlb().getDescription()));
        LOGGER.debug("date.getTime() : "+date.getTime());
        return date;
    }
}
