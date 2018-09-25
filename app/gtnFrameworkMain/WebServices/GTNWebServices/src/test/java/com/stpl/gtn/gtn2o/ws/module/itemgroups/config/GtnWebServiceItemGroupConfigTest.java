/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ws.module.itemgroups.config;

import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Karthik.Raja
 */
public class GtnWebServiceItemGroupConfigTest {
    
    public GtnWebServiceItemGroupConfigTest() {
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

    /**
     * Test of getSearchQueryConfigMap method, of class GtnWebServiceItemGroupConfig.
     */
    @Test
    public void testGetSearchQueryConfigMap() {
        System.out.println("getSearchQueryConfigMap");
        GtnWebServiceItemGroupConfig instance = new GtnWebServiceItemGroupConfig();
        Map<String, GtnWsSearchQueryConfig> result = instance.getSearchQueryConfigMap();
    }

    /**
     * Test of setSearchQueryConfigMap method, of class GtnWebServiceItemGroupConfig.
     */
    @Test
    public void testSetSearchQueryConfigMap() {
        System.out.println("setSearchQueryConfigMap");
        Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();
        GtnWebServiceItemGroupConfig instance = new GtnWebServiceItemGroupConfig();
          instance.getSearchQueryConfigMap();
        instance.setSearchQueryConfigMap(searchQueryConfigMap);
    }

    /**
     * Test of loadSearchQueryConfig method, of class GtnWebServiceItemGroupConfig.
     */
    @Test
    public void testLoadSearchQueryConfig() {
        System.out.println("loadSearchQueryConfig");
        GtnWebServiceItemGroupConfig instance = new GtnWebServiceItemGroupConfig();
          instance.getSearchQueryConfigMap();
        Map<String, GtnWsSearchQueryConfig> result = instance.loadSearchQueryConfig();
        assertNotNull( result);
    }

    /**
     * Test of getColumnDetails method, of class GtnWebServiceItemGroupConfig.
     */
    @Test
    public void testGetColumnDetails() {
        System.out.println("getColumnDetails");
        GtnWsSearchQueryConfig gtnWebServiceSearchQueryConfig = new GtnWsSearchQueryConfig();
        GtnWebServiceItemGroupConfig instance = new GtnWebServiceItemGroupConfig();
        instance.getSearchQueryConfigMap();
        instance.getColumnDetails(gtnWebServiceSearchQueryConfig);
    }
    
}
