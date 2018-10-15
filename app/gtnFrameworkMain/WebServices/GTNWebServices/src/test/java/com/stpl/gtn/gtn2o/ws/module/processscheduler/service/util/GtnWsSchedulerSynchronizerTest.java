/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.service.util;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
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
public class GtnWsSchedulerSynchronizerTest {
    
    public GtnWsSchedulerSynchronizerTest() {
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
    private GtnWsSchedulerSynchronizer gtnWsSchedulerSynchronizer;
    
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
     * Test of getInstance method, of class GtnWsSchedulerSynchronizer.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        GtnWsSchedulerSynchronizer result = gtnWsSchedulerSynchronizer.getInstance();
        assertFalse(result==null);
    }

    /**
     * Test of lock method, of class GtnWsSchedulerSynchronizer.
     */
    @Test
    public void testLock() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("lock");
        Constructor<GtnWsSchedulerSynchronizer> constructor = GtnWsSchedulerSynchronizer.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        GtnWsSchedulerSynchronizer secondOb = constructor.newInstance();
        secondOb.lock();
    }

    /**
     * Test of unlock method, of class GtnWsSchedulerSynchronizer.
     */
    @Test
    public void testUnlock() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("unlock");
        Constructor<GtnWsSchedulerSynchronizer> constructor = GtnWsSchedulerSynchronizer.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        GtnWsSchedulerSynchronizer secondOb = constructor.newInstance();
        try{
        secondOb.unlock();
        Assert.fail();
        } catch (IllegalMonitorStateException e) {			
	}
    }
    
}
