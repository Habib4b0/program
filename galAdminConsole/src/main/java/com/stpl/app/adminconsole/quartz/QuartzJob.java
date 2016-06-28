/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.quartz;

import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.getFtpBundleValue;
import static com.stpl.app.adminconsole.quartz.QuartzListener.ACTION_JOB_DATA_MAP_KEY;
import com.stpl.app.adminconsole.util.SchedulerExportThread;
import com.stpl.app.model.WorkflowProfile;
import org.jboss.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author rohitvignesh.s
 */
public class QuartzJob implements Job {
    
    private static final Logger LOGGER = Logger.getLogger(QuartzJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        Object profileObj = context.getJobDetail().getJobDataMap().get(ACTION_JOB_DATA_MAP_KEY);
        if (profileObj instanceof WorkflowProfile) {
            WorkflowProfile profile = (WorkflowProfile) profileObj;
            ProcessSchedulerLogic logic = new ProcessSchedulerLogic();
            if ("RELATIONSHIP_BUILDER_OUTBOUND".equalsIgnoreCase(profile.getProcessName()) || 
                   "HIERARCHY_DEFINITION_OUTBOUND".equalsIgnoreCase(profile.getProcessName()) ||
                    "CFF_OUTBOUND_INTERFACE".equalsIgnoreCase(profile.getProcessName())) {
                try {
                    SchedulerExportThread expThread = new SchedulerExportThread(profile.getProcessName());
                    Thread thread = new Thread(expThread);
                    thread.start();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else {
                try {
                    logic.runJob(getFtpBundleValue(), profile.getScriptName());
                } catch (Exception e) {
                    LOGGER.error(e);
                }
            }
            try {
                logic.updateLastRun(profile.getProcessSid(), true);
            } catch (Exception e) {
                LOGGER.error(e);
            }

        }
    }

}

