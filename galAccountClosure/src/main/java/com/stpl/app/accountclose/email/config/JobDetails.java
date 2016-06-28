/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.app.accountclose.email.config;
import org.jboss.logging.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author alok.v
 */
public class JobDetails implements Job {
    final Emailer mail = new Emailer();
    private static final Logger LOGGER = Logger.getLogger(JobDetails.class);
      @Override
    public void execute(JobExecutionContext jec) throws JobExecutionException {
        String status = mail.testMail("alokkumar.vishwakarma@sysbiz.com", "Test Mail", "Test Mail","Yes");
        LOGGER.info("status ************** " + status);

    }
}
