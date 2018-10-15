/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.quartz;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.print.attribute.HashAttributeSet;
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
public class GtnWsQuartzUtilTest {
    
    public GtnWsQuartzUtilTest() {
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
    private GtnWsQuartzUtil gtnWsQuartzUtil;

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
     * Test of calculateCronStringForInterval method, of class GtnWsQuartzUtil.
     */
    @Test
    public void testCalculateCronStringForInterval() {
        System.out.println("calculateCronStringForInterval");
        int interval = 155550;
        List<String> result = gtnWsQuartzUtil.calculateCronStringForInterval(interval);
        assertFalse(!result.isEmpty());
    }

    /**
     * Test of addToHourlyMap method, of class GtnWsQuartzUtil.
     */
    @Test
    public void testAddToHourlyMap() {
        System.out.println("addToHourlyMap");
        Map<Integer, List<Integer>> hourMap = new HashMap<>();
        int minuteInADay = 155550;
        gtnWsQuartzUtil.addToHourlyMap(hourMap, minuteInADay);
        assertFalse(minuteInADay==0);
    }

    /**
     * Test of concateHours method, of class GtnWsQuartzUtil.
     */
    @Test
    public void testConcateHours() {
        System.out.println("concateHours");
        List<Integer> hourlist = new ArrayList<>();
        String result = gtnWsQuartzUtil.concateHours(hourlist);
        assertFalse(!hourlist.isEmpty());
    }
    
    @Test
    public void testConcateHoursnull() {
        System.out.println("concateHours");
        List<Integer> hourlist = null;
        String result = gtnWsQuartzUtil.concateHours(hourlist);
        assertFalse(hourlist!=null);
    }
    
    @Test
    public void testConcateHoursloop() {
        System.out.println("concateHours");
        List<Integer> hourlist = new ArrayList<>();
        hourlist.add(2);
        String result = gtnWsQuartzUtil.concateHours(hourlist);
        assertFalse(hourlist.isEmpty());
    }
    
     @Test
    public void testConcateHoursloop2() {
        System.out.println("concateHours");
        List<Integer> hourlist = new ArrayList<>();
        hourlist.add(2);
        hourlist.add(3);
        String result = gtnWsQuartzUtil.concateHours(hourlist);
        assertFalse(hourlist.isEmpty());
    }

    /**
     * Test of getHour method, of class GtnWsQuartzUtil.
     */
    @Test
    public void testGetHour() {
        System.out.println("getHour");
        int minuteInADay = 155550;
        int result = gtnWsQuartzUtil.getHour(minuteInADay);
        assertFalse(minuteInADay==0);
    }

    /**
     * Test of addToMinutelyMap method, of class GtnWsQuartzUtil.
     */
    @Test
    public void testAddToMinutelyMap() {
        System.out.println("addToMinutelyMap");
        Map<Integer, List<Integer>> minutelyMap = new HashMap<>();
        int minuteInADay = 155550;
        gtnWsQuartzUtil.addToMinutelyMap(minutelyMap, minuteInADay);
        assertFalse(minuteInADay==0);
    }
    
}