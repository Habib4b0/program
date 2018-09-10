/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.contractheader.config;

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
public class GtnUIFrameworkContractHeaderConfigTest {
    
    public GtnUIFrameworkContractHeaderConfigTest() {
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
     * Test of getContractHeaderrRootConfig method, of class GtnUIFrameworkContractHeaderConfig.
     */
    @Test
    public void testGetContractHeaderrRootConfig() {
        System.out.println("getContractHeaderrRootConfig");
        GtnUIFrameworkContractHeaderConfig instance = new GtnUIFrameworkContractHeaderConfig();
        GtnUIFrameworkRootConfig result = instance.getContractHeaderrRootConfig();
        assertFalse(result.getGtnViewConfigList().isEmpty());
    }
    
}
