/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import java.util.List;
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
public class GtnFrameworkHierarchyChangeActionTest {
    
    public GtnFrameworkHierarchyChangeActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkHierarchyChangeAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkHierarchyChangeAction instance = new GtnFrameworkHierarchyChangeAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }



    /**
     * Test of getVersion method, of class GtnFrameworkHierarchyChangeAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("testCreateInstance");
        GtnFrameworkHierarchyChangeAction instance = new GtnFrameworkHierarchyChangeAction();
        GtnFrameworkHierarchyChangeAction result = (GtnFrameworkHierarchyChangeAction) instance.createInstance();
        assertEquals(result,instance);
    }


  
    
}
