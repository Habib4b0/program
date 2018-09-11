/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ws.module.contractheader.config;

import com.stpl.gtn.gtn2o.queryengine.engine.GtnFrameworkSqlQueryEngine;
import com.stpl.gtn.gtn2o.ws.config.GtnWsAllListConfig;
import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.module.automaticrelationship.service.GtnFrameworkAutomaticService;
import com.stpl.gtn.gtn2o.ws.module.contractheader.service.GtnWsContractHeaderValidationService;
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
public class GtnWsContractHeaderConfigTest {
    
    public GtnWsContractHeaderConfigTest() {
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
    private GtnWsContractHeaderConfig gtnWsContractHeaderConfig;
    
    @Autowired
    private GtnFrameworkAutomaticService automaticRelationService;
    
    @Autowired
    private GtnWsSqlService gtnWsSqlService;
    
    @Autowired
    private GtnFrameworkSqlQueryEngine gtnSqlQueryEngine;
    
    @Autowired
    private GtnWsAllListConfig gtnWebServiceAllListConfig;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    
    
    
    	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    /**
     * Test of getSearchQueryConfigMap method, of class GtnWsContractHeaderConfig.
     */
    @Test
    public void testGetSearchQueryConfigMap() {
        System.out.println("getSearchQueryConfigMap");
        Map<String, GtnWsSearchQueryConfig> result = gtnWsContractHeaderConfig.getSearchQueryConfigMap();
        assertFalse(result==null);
    }

    /**
     * Test of setSearchQueryConfigMap method, of class GtnWsContractHeaderConfig.
     */
    @Test
    public void testSetSearchQueryConfigMap() {
        System.out.println("setSearchQueryConfigMap");
        Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();
        gtnWsContractHeaderConfig.setSearchQueryConfigMap(searchQueryConfigMap);
    }
    
}
