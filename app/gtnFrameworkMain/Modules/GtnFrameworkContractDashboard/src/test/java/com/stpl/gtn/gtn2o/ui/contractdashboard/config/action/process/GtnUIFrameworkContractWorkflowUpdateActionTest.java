/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import java.util.Properties;
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
public class GtnUIFrameworkContractWorkflowUpdateActionTest {
    
    public GtnUIFrameworkContractWorkflowUpdateActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkContractWorkflowUpdateAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkContractWorkflowUpdateAction instance = new GtnUIFrameworkContractWorkflowUpdateAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of getPropertyFile method, of class GtnUIFrameworkContractWorkflowUpdateAction.
     */
    @Test
    public void testGetPropertyFile() {
        System.out.println("getPropertyFile");
        String bpiPropLoc = "";
        GtnUIFrameworkContractWorkflowUpdateAction instance = new GtnUIFrameworkContractWorkflowUpdateAction();
        Properties expResult = new Properties();
        Properties result = instance.getPropertyFile(bpiPropLoc);
        assertEquals(expResult.size(), result.size());
    }

    /**
     * Test of createInstance method, of class GtnUIFrameworkContractWorkflowUpdateAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkContractWorkflowUpdateAction instance = new GtnUIFrameworkContractWorkflowUpdateAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
