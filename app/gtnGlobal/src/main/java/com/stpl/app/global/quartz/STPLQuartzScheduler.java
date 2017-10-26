/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.global.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 *
 * @author Mohamed.Shahul
 */
public class STPLQuartzScheduler {
    

    private static STPLQuartzScheduler instance;
    private Scheduler scheduler;
    private static final org.jboss.logging.Logger LOGGER = org.jboss.logging.Logger.getLogger(STPLQuartzScheduler.class);

    private STPLQuartzScheduler() {
    }

    public static STPLQuartzScheduler getIntance() {
        if (instance == null) {
            instance = new STPLQuartzScheduler();
        }
        return instance;
    }

    public Scheduler getScheduler() {
        if (scheduler == null) {
            try {
                scheduler = new StdSchedulerFactory().getScheduler();
            } catch (SchedulerException ex) {
                LOGGER.error("Error While Creating scheduler :" + ex + " Please recreate :");
            }
        }
        return scheduler;
    }
}
