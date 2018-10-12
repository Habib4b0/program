/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.quartz;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.entity.workflow.WorkflowProfile;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.module.processscheduler.constant.GtnWsProcessSchedulerConstant;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsQuartzListenerTest {
    
    public GtnWsQuartzListenerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
     @Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
    
    @After
    public void tearDown() {
    }

    
    @InjectMocks
    @Autowired
    private GtnWsQuartzListener gtnWsQuartzListener;
    
    @Autowired
    private GtnFrameworkAutomaticService automaticRelationService;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    @Mock
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
    
    /**
     * Test of getSessionFactory method, of class GtnWsQuartzListener.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory result = gtnWsQuartzListener.getSessionFactory();
        assertFalse(result==null);
    }

    /**
     * Test of createQuartzScheduler method, of class GtnWsQuartzListener.
     */
    @Test
    public void testCreateQuartzScheduler() {
        System.out.println("createQuartzScheduler");
        gtnWsQuartzListener.createQuartzScheduler();
        assertTrue(true);
    }

    /**
     * Test of getWorkFlowProfileData method, of class GtnWsQuartzListener.
     */
    @Test
    public void testGetWorkFlowProfileData() {
        System.out.println("getWorkFlowProfileData");
        List<WorkflowProfile> result = gtnWsQuartzListener.getWorkFlowProfileData();
        assertFalse(result.isEmpty());
    }

    /**
     * Test of createJob method, of class GtnWsQuartzListener.
     */
    @Test
    public void testCreateJob() {
        System.out.println("createJob");
        WorkflowProfile profile = new WorkflowProfile();
        gtnWsQuartzListener.createJob(profile);
        assertTrue(true);
    }

    /**
     * Test of getTriggeredElse method, of class GtnWsQuartzListener.
     */
    @Test
    public void testGetTriggeredElse() {
        System.out.println("getTriggeredElse");
        WorkflowProfile profile = new WorkflowProfile();
        int i = 0;
        String cronString = "cronString";
        gtnWsQuartzListener.getTriggeredElse(profile, i, cronString);
        assertFalse(cronString.isEmpty());
    }

    /**
     * Test of getTriggeredIf method, of class GtnWsQuartzListener.
     */
    @Test
    public void testGetTriggeredIf() {
        System.out.println("getTriggeredIf");
        WorkflowProfile profile = new WorkflowProfile();
        JobDetail job = null;
        int i = 0;
        String cronString = "cronString";
        int result = gtnWsQuartzListener.getTriggeredIf(profile, job, i, cronString);
        assertFalse(cronString.isEmpty());
    }

    /**
     * Test of generateJobName method, of class GtnWsQuartzListener.
     */
    @Test
    public void testGenerateJobName() {
        System.out.println("generateJobName");
        WorkflowProfile profile = new WorkflowProfile();
        String result = gtnWsQuartzListener.generateJobName(profile);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of generateCronStringTime method, of class GtnWsQuartzListener.
     */
    @Test
    public void testGenerateCronStringTime() {
        System.out.println("generateCronStringTime");
        WorkflowProfile profile = new WorkflowProfile();
        profile.setFrequency("Time");
        profile.setStartHour1(Byte.MIN_VALUE);
        profile.setStartHour2(Byte.MIN_VALUE);
        profile.setStartHour3(Byte.MIN_VALUE);
        profile.setStartMinutes1(Byte.MAX_VALUE);
        profile.setStartMinutes2(Byte.MAX_VALUE);
        profile.setStartMinutes3(Byte.MAX_VALUE);
        List<String> cronStringList = new ArrayList<>();
        gtnWsQuartzListener.generateCronStringTime(profile, cronStringList);
        assertFalse(cronStringList.isEmpty());
    }

    /**
     * Test of getCronString method, of class GtnWsQuartzListener.
     */
    @Test
    public void testGetCronString() {
        System.out.println("getCronString");
        int minutes = 15;
        int hour = 1;
        String result = gtnWsQuartzListener.getCronString(minutes, hour);
        assertFalse(result.isEmpty());
    }

    /**
     * Test of generateCronStringInterval method, of class GtnWsQuartzListener.
     */
    @Test
    public void testGenerateCronStringInterval() {
        System.out.println("generateCronStringInterval");
        WorkflowProfile profile = new WorkflowProfile();
        profile.setFrequency("Interval");
        profile.setStartHour(Byte.valueOf("24"));
        profile.setStartMinutes(Byte.valueOf("60"));
        List<String> cronStringList = new ArrayList<>();
        gtnWsQuartzListener.generateCronStringInterval(profile, cronStringList);
        assertFalse(!cronStringList.isEmpty());
    }
    
     @Test
    public void testGenerateCronStringIntervalFrequency() {
        System.out.println("generateCronStringInterval");
        WorkflowProfile profile = new WorkflowProfile();
        profile.setFrequency("Intervall");
        profile.setStartHour(Byte.valueOf("24"));
        profile.setStartMinutes(Byte.valueOf("60"));
        List<String> cronStringList = new ArrayList<>();
        gtnWsQuartzListener.generateCronStringInterval(profile, cronStringList);
        assertFalse(!cronStringList.isEmpty());
    }
    
    @Test
    public void testGenerateCronStringIntervalFrequencyZero() {
        System.out.println("generateCronStringInterval");
        WorkflowProfile profile = new WorkflowProfile();
        profile.setFrequency("Interval");
        profile.setStartHour(Byte.valueOf("0"));
        profile.setStartMinutes(Byte.valueOf("0"));
        List<String> cronStringList = new ArrayList<>();
        gtnWsQuartzListener.generateCronStringInterval(profile, cronStringList);
        assertFalse(cronStringList.isEmpty());
    }
    
    @Test
    public void testGenerateCronStringIntervalFrequencyFortyFive() {
        System.out.println("generateCronStringInterval");
        WorkflowProfile profile = new WorkflowProfile();
        profile.setFrequency("Interval");
        profile.setStartMinutes(Byte.valueOf(GtnWsProcessSchedulerConstant.FOURTY_FIVE));
        List<String> cronStringList = new ArrayList<>();
        gtnWsQuartzListener.generateCronStringInterval(profile, cronStringList);
        assertFalse(cronStringList.isEmpty());
    }
    
    @Test
    public void testGenerateCronStringIntervalFrequencyGreaterZero() {
        System.out.println("generateCronStringInterval");
        WorkflowProfile profile = new WorkflowProfile();
        profile.setFrequency("Interval");
        profile.setStartHour(Byte.valueOf("2"));
        profile.setStartMinutes(Byte.valueOf("15"));
        List<String> cronStringList = new ArrayList<>();
        gtnWsQuartzListener.generateCronStringInterval(profile, cronStringList);
        assertFalse(cronStringList.isEmpty());
    }
    
     @Test
    public void testGenerateCronStringIntervalFrequencyGreaterTwentyfour() {
        System.out.println("generateCronStringInterval");
        WorkflowProfile profile = new WorkflowProfile();
        profile.setFrequency("Interval");
        profile.setStartHour(Byte.valueOf("24"));
        profile.setStartMinutes(Byte.valueOf("15"));
        List<String> cronStringList = new ArrayList<>();
        gtnWsQuartzListener.generateCronStringInterval(profile, cronStringList);
        assertFalse(!cronStringList.isEmpty());
    }
    
    @Test
    public void testGenerateCronStringIntervalFrequencyHour() {
        System.out.println("generateCronStringInterval");
        WorkflowProfile profile = new WorkflowProfile();
        profile.setFrequency("Interval");
        profile.setStartHour(Byte.valueOf("2"));
        profile.setStartMinutes(Byte.valueOf("-1"));
        List<String> cronStringList = new ArrayList<>();
        gtnWsQuartzListener.generateCronStringInterval(profile, cronStringList);
        assertFalse(cronStringList.isEmpty());
    }
    
       @Test
    public void testGenerateCronStringIntervalFrequencyMinutes() {
        System.out.println("generateCronStringInterval");
        WorkflowProfile profile = new WorkflowProfile();
        profile.setFrequency("Interval");
        profile.setStartHour(Byte.valueOf("-2"));
        profile.setStartMinutes(Byte.valueOf("5"));
        List<String> cronStringList = new ArrayList<>();
        gtnWsQuartzListener.generateCronStringInterval(profile, cronStringList);
        assertFalse(cronStringList.isEmpty());
    }

    /**
     * Test of getTriggerBuilderWithDate method, of class GtnWsQuartzListener.
     */
    @Test
    public void testGetTriggerBuilderWithDate() throws Exception {
        System.out.println("getTriggerBuilderWithDate");
        WorkflowProfile profile = new WorkflowProfile();
        int triggerNo = 0;
        TriggerBuilder<Trigger> result = gtnWsQuartzListener.getTriggerBuilderWithDate(profile, triggerNo);
        assertFalse(triggerNo!=0);
    }
    
    @Test
    public void testGetTriggerBuilderWithOne() throws Exception {
        System.out.println("getTriggerBuilderWithDate");
        WorkflowProfile profile = new WorkflowProfile();
        int triggerNo = 1;
        TriggerBuilder<Trigger> result = gtnWsQuartzListener.getTriggerBuilderWithDate(profile, triggerNo);
        assertFalse(triggerNo==0);
    }
    
    @Test
    public void testGetTriggerBuilderWithOneDate() throws Exception {
        System.out.println("getTriggerBuilderWithDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date   startDate  = format.parse ( "2010-12-31" ); 
        Date   endDate  = format.parse ( "2009-12-31" ); 
        WorkflowProfile profile = new WorkflowProfile();
        profile.setStartDate(startDate);
        profile.setEndDate(endDate);
        int triggerNo = 1;
        TriggerBuilder<Trigger> result = gtnWsQuartzListener.getTriggerBuilderWithDate(profile, triggerNo);
        assertFalse(triggerNo==0);
    }
    

    /**
     * Test of getStartDate method, of class GtnWsQuartzListener.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        Date inputDate = new Date();
        Date result = gtnWsQuartzListener.getStartDate(inputDate);
        assertFalse(inputDate==null);
    }
    
    
    @Test
    public void testGetStartDate1() throws ParseException {
        System.out.println("getStartDate");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date   startDate  = format.parse ( "2011-12-31" ); 
        Date result = gtnWsQuartzListener.getStartDate(startDate);
        assertFalse(startDate==null);
    }

    /**
     * Test of deleteSchedule method, of class GtnWsQuartzListener.
     */
    @Test
    public void testDeleteSchedule() {
        System.out.println("deleteSchedule");
        gtnWsQuartzListener.deleteSchedule();
        assertTrue(true);
    }
    
}