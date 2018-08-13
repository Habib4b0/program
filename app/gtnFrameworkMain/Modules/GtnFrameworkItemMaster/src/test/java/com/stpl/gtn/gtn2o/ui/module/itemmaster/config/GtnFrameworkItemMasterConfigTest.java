/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.itemmaster.config;

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
public class GtnFrameworkItemMasterConfigTest {
    
    public GtnFrameworkItemMasterConfigTest() {
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
     * Test of getItemMasterRootConfig method, of class GtnFrameworkItemMasterConfig.
     */
    @Test
    public void testGetItemMasterRootConfig() {
          System.out.println("getItemMasterRootConfig");
          GtnFrameworkItemMasterConfig instance = new GtnFrameworkItemMasterConfig();
          GtnUIFrameworkRootConfig result = instance.getItemMasterRootConfig();
          assertFalse(result.getGtnViewConfigList().isEmpty());
    }
    
}
