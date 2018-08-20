package com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class SchedulerSynchronizer {
	/**
     * Initializing value
     */
    private static SchedulerSynchronizer process = null;
    /**
     * Initializing lock variable
     */
    private final Lock lock = new ReentrantLock();

    /**
     * This method is used for getting single instance for the current class
     * @param className
     * @return 
     */
    public static SchedulerSynchronizer getInstance() {
        if (process == null) {
            process = new SchedulerSynchronizer();
        }
        return process;
    }

    private SchedulerSynchronizer() {
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
