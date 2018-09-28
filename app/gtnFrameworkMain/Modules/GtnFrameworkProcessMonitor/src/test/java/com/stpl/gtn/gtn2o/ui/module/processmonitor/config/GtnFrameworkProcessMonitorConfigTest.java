/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.processmonitor.config;

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
public class GtnFrameworkProcessMonitorConfigTest {
    
    public GtnFrameworkProcessMonitorConfigTest() {
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
     * Test of getProcessMonitorRootConfig method, of class GtnFrameworkProcessMonitorConfig.
     */
    @Test
    public void testGetProcessMonitorRootConfig() {
        System.out.println("getEmailRootConfig");
        GtnFrameworkProcessMonitorConfig instance = new GtnFrameworkProcessMonitorConfig();
        GtnUIFrameworkRootConfig result = instance.getProcessMonitorRootConfig();
        assertFalse(result.getGtnViewConfigList().isEmpty());
    }
    
}
