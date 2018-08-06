/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.config;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hazihabibullah.Syed
 */
public class GtnUIFrameworkCompanyMasterConfigTest {
    
    public GtnUIFrameworkCompanyMasterConfigTest() {
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
     * Test of getCompanyMasterRootConfig method, of class GtnUIFrameworkCompanyMasterConfig.
     */
    @Test
    public void testGetCompanyMasterRootConfig() {
        System.out.println("getCompanyMasterRootConfig");
        GtnUIFrameworkCompanyMasterConfig instance = new GtnUIFrameworkCompanyMasterConfig();
        GtnUIFrameworkRootConfig result = instance.getCompanyMasterRootConfig();
        assertFalse(result.getGtnViewConfigList().isEmpty());
    }
    
}
