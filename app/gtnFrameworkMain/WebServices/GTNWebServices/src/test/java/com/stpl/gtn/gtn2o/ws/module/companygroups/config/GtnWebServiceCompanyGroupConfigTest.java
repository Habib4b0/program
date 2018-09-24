package com.stpl.gtn.gtn2o.ws.module.companygroups.config;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.stpl.gtn.gtn2o.ws.config.GtnWsSearchQueryConfig;
import com.stpl.gtn.gtn2o.ws.module.itemgroups.config.GtnWebServiceItemGroupConfig;

public class GtnWebServiceCompanyGroupConfigTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
     * Test of getSearchQueryConfigMap method, of class GtnWebServiceItemGroupConfig.
     */
    @Test
    public void testGetSearchQueryConfigMap() {
        System.out.println("getSearchQueryConfigMap");
        GtnWebServiceCompanyGroupConfig instance = new GtnWebServiceCompanyGroupConfig();
        Map<String, GtnWsSearchQueryConfig> result = instance.getSearchQueryConfigMap();
    }

    /**
     * Test of setSearchQueryConfigMap method, of class GtnWebServiceItemGroupConfig.
     */
    @Test
    public void testSetSearchQueryConfigMap() {
        System.out.println("setSearchQueryConfigMap");
        Map<String, GtnWsSearchQueryConfig> searchQueryConfigMap = new HashMap<>();
        GtnWebServiceCompanyGroupConfig instance = new GtnWebServiceCompanyGroupConfig();
          instance.getSearchQueryConfigMap();
        instance.setSearchQueryConfigMap(searchQueryConfigMap);
    }

    /**
     * Test of loadSearchQueryConfig method, of class GtnWebServiceItemGroupConfig.
     */
    @Test
    public void testLoadSearchQueryConfig() {
        System.out.println("loadSearchQueryConfig");
        GtnWebServiceCompanyGroupConfig instance = new GtnWebServiceCompanyGroupConfig();
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
        GtnWebServiceCompanyGroupConfig instance = new GtnWebServiceCompanyGroupConfig();
        instance.getSearchQueryConfigMap();
    }
    
}
