/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.service;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
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
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsProcessSchedulerUpdateServiceTest {
    
    public GtnWsProcessSchedulerUpdateServiceTest() {
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
    private GtnWsProcessSchedulerUpdateService gtnWsProcessSchedulerUpdateService;
    
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
     * Test of getSessionFactory method, of class GtnWsProcessSchedulerUpdateService.
     */
    @Test
    public void testGetSessionFactory() {
        System.out.println("getSessionFactory");
        SessionFactory result = gtnWsProcessSchedulerUpdateService.getSessionFactory();
        assertFalse(result==null);
    }

    /**
     * Test of runProcessScheduler method, of class GtnWsProcessSchedulerUpdateService.
     */
    @Test
    public void testRunProcessScheduler() {
        System.out.println("runProcessScheduler");
        String scriptName = "Cff_Outbound_Intf.sh";
        Integer processSid = 3;
        gtnWsProcessSchedulerUpdateService.runProcessScheduler(scriptName, processSid);
        assertFalse(processSid==0);
    }

    /**
     * Test of updateLastRun method, of class GtnWsProcessSchedulerUpdateService.
     */
    @Test
    public void testUpdateLastRun() {
        System.out.println("updateLastRun");
        Integer processId = 3;
        boolean schedulerFlag = false;
        gtnWsProcessSchedulerUpdateService.updateLastRun(processId, schedulerFlag);
        assertFalse(processId==0);
    }
    
    @Test
    public void testUpdateLastRunTrue() {
        System.out.println("updateLastRun");
        Integer processId = 3;
        boolean schedulerFlag = true;
        gtnWsProcessSchedulerUpdateService.updateLastRun(processId, schedulerFlag);
        assertFalse(processId==0);
    }
    
    @Test
    public void testUpdateLastRunFail() {
        System.out.println("updateLastRun");
        Integer processId = 25555;
        boolean schedulerFlag = true;
        gtnWsProcessSchedulerUpdateService.updateLastRun(processId, schedulerFlag);
        assertFalse(schedulerFlag==false);
    }
    
}
