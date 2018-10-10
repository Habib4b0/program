/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.processscheduler.config;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.service.GtnWsSqlService;
import java.util.HashMap;
import java.util.Map;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:src/test/resources/AutomaticContext.xml" })
public class GtnWsCffOutBoundSearchConfigTest {
    
    public GtnWsCffOutBoundSearchConfigTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
    
    @Autowired
    private GtnWsCffOutBoundSearchConfig gtnWsCffOutBoundSearchConfig;
    
    @Autowired
    private GtnFrameworkAutomaticService automaticRelationService;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
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
     * Test of getSearchQueryConfigMap method, of class GtnWsCffOutBoundSearchConfig.
     */
    @Test
    public void testGetSearchQueryConfigMap() {
        System.out.println("getSearchQueryConfigMap");
        Map<String, GtnWsSearchQueryConfig> result = gtnWsCffOutBoundSearchConfig.getSearchQueryConfigMap();
        assertFalse(result==null);      
    }

    /**
     * Test of setSearchQueryConfigMap method, of class GtnWsCffOutBoundSearchConfig.
     */
    @Test
    public void testSetSearchQueryConfigMap() {
        System.out.println("setSearchQueryConfigMap");
        Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();
        gtnWsCffOutBoundSearchConfig.setSearchQueryConfigMap(searchQueryConfigMap);
        assertFalse(!searchQueryConfigMap.isEmpty());      
    }
    
}
