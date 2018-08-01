/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Karthik.Raja
 */
public class GtnFrameworkAddToTreeActionTest {
    
    public GtnFrameworkAddToTreeActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkAddToTreeAction.
     */
    @org.junit.Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkAddToTreeAction instance = new GtnFrameworkAddToTreeAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkAddToTreeAction.
     */
    @org.junit.Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkAddToTreeAction instance = new GtnFrameworkAddToTreeAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }


    @Test
    public void testCheckToProceed() {
            try {
        System.out.println("checkToProceed");
        Set<Integer> selectedValue = new HashSet<>();
        selectedValue.add(0);
        selectedValue.add(1);
        int levelNo = 1;
        GtnFrameworkAddToTreeAction instance = new GtnFrameworkAddToTreeAction();
        boolean expResult = false;
        Method method = instance.getClass().getDeclaredMethod("checkToProceed",Object.class,Integer.TYPE);
        method.setAccessible(true);
        Object result=method.invoke(instance, selectedValue,levelNo);
        assertEquals(expResult, (Boolean)result);
         } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkAddToTreeActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
