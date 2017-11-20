/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.companymaster.quartz;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

import com.stpl.gtn.gtn2o.ws.logger.GtnWSLogger;

public class STPLQuartzScheduler {

	private static STPLQuartzScheduler instance;
	private Scheduler scheduler;
	private static final GtnWSLogger LOGGER = GtnWSLogger.getGTNLogger(STPLQuartzScheduler.class);

	private STPLQuartzScheduler() {
	}

	public static synchronized STPLQuartzScheduler getIntance() {
		if (instance != null) {
			return instance;
		}
		instance = new STPLQuartzScheduler();
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
