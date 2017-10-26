/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.schedule;

import com.stpl.app.accountclose.logic.ProcessSchedulerLogic;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author hazi.s
 */
public class JobDetails implements Job {

    public void execute(JobExecutionContext jec) throws JobExecutionException {
        ProcessSchedulerLogic manualSchedule = new ProcessSchedulerLogic();
        manualSchedule.file();
    }

}
