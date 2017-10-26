/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.util.service.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 *
 * @author Karthikeyan.Subraman
 */
public class ThreadPool {

    /**
     * The Object is Current class object Which is created as Singleton object
     */
    private static ThreadPool pool = null;
    /**
     * Creates a thread pool that creates new threads as needed, but will reuse
     * previously constructed threads when they are available. These pools will
     * typically improve the performance of programs that execute many
     * short-lived asynchronous tasks. Calls to execute will reuse previously
     * constructed threads if available. If no existing thread is available, a
     * new thread will be created and added to the pool. Threads that have not
     * been used for sixty seconds are terminated and removed from the cache.
     * Thus, a pool that remains idle for long enough will not consume any
     * resources. Note that pools with similar properties but different details
     * (for example, timeout parameters) may be created using ThreadPoolExecutor
     * constructors.
     */
    private ExecutorService service = null;

    private ThreadPool() {
        if (service == null) {
            service = Executors.newCachedThreadPool();
        }
    }

    /**
     * the method returns the singleton object the class ThreadPool and holds
     * the CachedThreadPool
     *
     * @return
     */
    public static ThreadPool getInstance() {
        if (pool == null) {
            pool = new ThreadPool();
        }
        return pool;

    }

    /**
     * returns the object of the class ExecutorService
     * @return
     */
    public ExecutorService getService() {
        return service;
    }


}
