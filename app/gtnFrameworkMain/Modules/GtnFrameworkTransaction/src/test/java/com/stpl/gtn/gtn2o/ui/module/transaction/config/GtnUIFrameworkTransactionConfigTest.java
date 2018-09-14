/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.config;

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
public class GtnUIFrameworkTransactionConfigTest {
    
    public GtnUIFrameworkTransactionConfigTest() {
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
     * Test of getMainTransactionRootConfig method, of class GtnUIFrameworkTransactionConfig.
     */
    @Test
    public void testGetMainTransactionRootConfig() {
        System.out.println("getMainTransactionRootConfig");
        boolean isInvalid=false;
        GtnUIFrameworkTransactionConfig instance = new GtnUIFrameworkTransactionConfig();
        GtnUIFrameworkRootConfig result = instance.getMainTransactionRootConfig(isInvalid);
        assertFalse(result.getGtnViewConfigList().isEmpty());
    }

    /**
     * Test of generateTempleteForTransaction method, of class GtnUIFrameworkTransactionConfig.
     */
    @Test
    public void testGenerateTempleteForTransaction() {
        System.out.println("generateTempleteForTransaction");
        boolean isInvalid=false;
        GtnUIFrameworkTransactionConfig instance = new GtnUIFrameworkTransactionConfig();
        GtnUIFrameworkRootConfig result = instance.getMainTransactionRootConfig(isInvalid);
        assertFalse(result.getGtnViewConfigList().isEmpty());
    }
    
}
