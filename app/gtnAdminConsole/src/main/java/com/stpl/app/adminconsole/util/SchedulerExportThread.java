/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.adminconsole.util;

import com.stpl.app.adminconsole.processscheduler.ui.form.OutboundProcess;

/**
 *
 * @author maheshj
 */
public class SchedulerExportThread implements Runnable {

    private String processName = "";

    public SchedulerExportThread(String processName) {
        this.processName = processName;

    }

    @Override
    public void run() {
        OutboundProcess process = new OutboundProcess();
        process.getAutomatedOutbound(processName);
    }

}
