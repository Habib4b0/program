/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config;

import com.stpl.gtn.gtn2o.ui.framework.config.GtnUIFrameworkRootConfig;
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
public class GtnFrameworkRelationShipBuilderConfigTest {
    
    public GtnFrameworkRelationShipBuilderConfigTest() {
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
     * Test of getRelationShipBuilderRootConfig method, of class GtnFrameworkRelationShipBuilderConfig.
     */
    @Test
    public void testGetRelationShipBuilderRootConfig() {
        System.out.println("getRelationShipBuilderRootConfig");
        GtnFrameworkRelationShipBuilderConfig instance = new GtnFrameworkRelationShipBuilderConfig();
        GtnUIFrameworkRootConfig result = instance.getRelationShipBuilderRootConfig();
        assertFalse(result.getGtnViewConfigList().isEmpty());
    }

   
    
}
