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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author rohitvignesh.s
 */
public class QuartzJob implements Job {

    private static final Logger LOGGER = LoggerFactory.getLogger(QuartzJob.class);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        LOGGER.info("Executing Quartz Job {} ",context.getJobDetail().getKey().getName());
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
                   LOGGER.error(ex.getMessage());
                }
            } else if ("CFF_OUTBOUND_INTERFACE".equalsIgnoreCase(profile.getProcessName())) {
                try {
                    SchedulerSynchronizer process = SchedulerSynchronizer.getInstance(AUTOMATIC_SCHEDULER);
                    process.lock();
                    int i = 0;
                    logic.schedulerInsert();
                    logic.runJob(getFtpBundleValue(), profile.getScriptName());
                    logic.updateLastRun(profile.getProcessSid(), true);
                    while (logic.existsQuery(ConstantsUtils.NUMBER_ONE, ConstantsUtils.NUMBER_ONE)) {
                        // Waiting block for ETL to end
                        Thread.sleep(NumericConstants.THREE_THOUSAND);
                        i++;
                        if (i == NumericConstants.HUNDRED) {
                            logic.deleteTempCffOutbound(null, Boolean.TRUE);
                        }
                    }
                    process.unlock();
                } catch (Exception ex) {
                   LOGGER.error(ex.getMessage());
                }
           } else if ("ACCRUAL_RATE_PROJECTION_INTERFACE".equalsIgnoreCase(profile.getProcessName())) {
            try {
                ArmSchedulerSynchronizer arpProcess = ArmSchedulerSynchronizer.getInstance(AUTOMATIC_SCHEDULER);
                arpProcess.lock();
                    logic.schedulerInsertForArp(ConstantsUtils.NUMBER_ONE, ConstantsUtils.NUMBER_ONE);
                logic.runJob(getFtpBundleValue(), profile.getScriptName());
                logic.updateLastRun(profile.getProcessSid(), true);
                arpProcess.unlock();
            } catch (Exception ex) {
               LOGGER.error(ex.getMessage());
            }
            } else if (ConstantsUtils.ADJ_RESERVER_DETAIL_INTERFACE.equals(profile.getProcessName()) || ConstantsUtils.ADJ_GTN_DETAIL_INTERFACE.equals(profile.getProcessName())) {
            try {
                ArmSchedulerSynchronizer adjOutboundProcess = ArmSchedulerSynchronizer.getInstance(AUTOMATIC_SCHEDULER);
                adjOutboundProcess.lock();
                    logic.updateCheckRecord(profile.getProcessName(), ConstantsUtils.NUMBER_ONE, ConstantsUtils.NUMBER_ONE);
                logic.runJob(getFtpBundleValue(), profile.getScriptName());
                logic.updateLastRun(profile.getProcessSid(), true);
                adjOutboundProcess.unlock();
            } catch (Exception ex) {
               LOGGER.error(ex.getMessage());
            }
        } else {
            try {
                    logic.runJob(getFtpBundleValue(), profile.getScriptName());
                } catch (Exception e) {
                    LOGGER.error(e.getMessage());
                }
            }
            try {
                logic.updateLastRun(profile.getProcessSid(), true);
            } catch (Exception e) {
                LOGGER.error(e.getMessage());
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
                LOGGER.error(e.getMessage());
            }
        }
        try {
        	LOGGER.info("Ending Quartz Job {}",context.getJobDetail().getKey().getName());
            QuartzListener.printJobsForJobKey(context.getJobDetail().getKey());
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
    }
}
    public static final String AUTOMATIC_SCHEDULER = " Automatic Scheduler ";
}
