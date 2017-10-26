/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util.service;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author Jayaram
 */
public class AdjustmentReserveSchedulerSynchronizer {

    /**
     * Initializing value
     */
    private static AdjustmentReserveSchedulerSynchronizer process = null;
    /**
     * Initializing lock variable
     */
    private final Lock lock = new ReentrantLock();

    /**
     * This method is used for getting single instance for the current class
     * @param className
     * @return 
     */
    public static AdjustmentReserveSchedulerSynchronizer getInstance(String className) {
        if (process == null) {
            process = new AdjustmentReserveSchedulerSynchronizer();
        }
        return process;
    }

    private AdjustmentReserveSchedulerSynchronizer() {
    }

    /**
     * Once the lock() is invoked, it makes this class unavailable to access 
     * except for the current accessing thread
     */
    public void lock() {
        lock.lock();
    }

    /**
     * Unlock() method will unlock the locked process
     * Once this method is invoked then the process which is waiting to access this class can access it.
     */
    public void unlock() {
        lock.unlock();
    }

}
