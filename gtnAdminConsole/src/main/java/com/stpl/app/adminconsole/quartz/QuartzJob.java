/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.quartz;

import com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic;
import static com.stpl.app.adminconsole.processscheduler.logic.ProcessSchedulerLogic.getFtpBundleValue;
import static com.stpl.app.adminconsole.quartz.QuartzListener.ACTION_JOB_DATA_MAP_KEY;
import com.stpl.app.adminconsole.util.ConstantsUtils;
import com.stpl.app.adminconsole.util.SchedulerExportThread;
import com.stpl.app.model.WorkflowProfile;
import com.stpl.app.util.service.ArmSchedulerSynchronizer;
import com.stpl.app.util.service.SchedulerSynchronizer;
import com.stpl.ifs.ui.util.NumericConstants;
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
    private final String one = "1";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Executing Quartz Job "+context.getJobDetail().getKey().getName());
        Object profileObj = context.getJobDetail().getJobDataMap().get(ACTION_JOB_DATA_MAP_KEY);
        ProcessSchedulerLogic logic = new ProcessSchedulerLogic();
        if (profileObj instanceof WorkflowProfile) {
            WorkflowProfile profile = (WorkflowProfile) profileObj;
            if ("RELATIONSHIP_BUILDER_OUTBOUND".equalsIgnoreCase(profile.getProcessName())
                    || "HIERARCHY_DEFINITION_OUTBOUND".equalsIgnoreCase(profile.getProcessName())) {
                try {
                    SchedulerExportThread expThread = new SchedulerExportThread(profile.getProcessName());
                    Thread thread = new Thread(expThread);
                    thread.start();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("CFF_OUTBOUND_INTERFACE".equalsIgnoreCase(profile.getProcessName())) {
                try {
                    SchedulerSynchronizer process = SchedulerSynchronizer.getInstance(" Automatic Scheduler ");
                    process.lock();
                    int i = 0;
                    new ProcessSchedulerLogic().schedulerInsert();
                    logic.runJob(getFtpBundleValue(), profile.getScriptName());
                    logic.updateLastRun(profile.getProcessSid(), true);
                    while (logic.existsQuery(one, one)) {
                        // Waiting block for ETL to end
                        Thread.sleep(NumericConstants.THREE_THOUSAND);
                        i++;
                        if (i == NumericConstants.HUNDRED) {
                            new ProcessSchedulerLogic().deleteTempCffOutbound(null, Boolean.TRUE);
                        }
                    }
                    process.unlock();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if ("ACCRUAL_RATE_PROJECTION_INTERFACE".equalsIgnoreCase(profile.getProcessName())) {
                try {
                    ArmSchedulerSynchronizer arpProcess = ArmSchedulerSynchronizer.getInstance(" Automatic Scheduler ");
                    arpProcess.lock();
                    new ProcessSchedulerLogic().schedulerInsertForArp(one, one);
                    logic.runJob(getFtpBundleValue(), profile.getScriptName());
                    logic.updateLastRun(profile.getProcessSid(), true);
                    arpProcess.unlock();
                } catch (Exception ex) {
                    LOGGER.error(ex);
                }
            } else if (ConstantsUtils.ADJ_RESERVER_DETAIL_INTERFACE.equals(profile.getProcessName()) || ConstantsUtils.ADJ_GTN_DETAIL_INTERFACE.equals(profile.getProcessName())) {
                try {
                    ArmSchedulerSynchronizer adjOutboundProcess = ArmSchedulerSynchronizer.getInstance(" Automatic Scheduler ");
                    adjOutboundProcess.lock();
                    new ProcessSchedulerLogic().updateCheckRecord(profile.getProcessName(), one, one);
                    logic.runJob(getFtpBundleValue(), profile.getScriptName());
                    logic.updateLastRun(profile.getProcessSid(), true);
                    adjOutboundProcess.unlock();
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

        } else {
            LOGGER.debug("Entering delete Job");
            try {
                //To delete the entire forecast tables    
                logic.deleteUnsavedProjections("ForecastUnsavedProjectionDelete");
                //To drop temp tables created dynamically using userId and sessionId    
                logic.deleteUnsavedProjections("ForecastTempTableDrop");
                LOGGER.debug("Ending delete Job");
            } catch (Exception e) {
                LOGGER.error(e);
            }
        }
        try {
        	System.out.println("Ending Quartz Job "+context.getJobDetail().getKey().getName() + " Next Fire Time ");
            QuartzListener.printJobsForJobKey(context.getJobDetail().getKey());
        } catch (Exception e) {
            LOGGER.error(e);
        }
    }
}
