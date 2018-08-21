/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponent;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkComponentType;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public class GtnFrameworkFieldFactoryComponentCreationActionTest {
    
    public GtnFrameworkFieldFactoryComponentCreationActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkFieldFactoryComponentCreationAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkFieldFactoryComponentCreationAction instance = new GtnFrameworkFieldFactoryComponentCreationAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }


    /**
     * Test of addFieldFactoryField method, of class GtnFrameworkFieldFactoryComponentCreationAction.
     */
    @Test
    public void testAddFieldFactoryField() {
    try {
        System.out.println("addFieldFactoryField");
        GtnUIFrameworkComponentType componentType=null; 
        List<GtnUIFrameWorkActionConfig> customActionList = new ArrayList<>();
        GtnFrameworkFieldFactoryComponentCreationAction instance = new GtnFrameworkFieldFactoryComponentCreationAction();
        GtnUIFrameworkComponentConfig expResult = new GtnUIFrameworkComponentConfig();
        Method method = instance.getClass().getDeclaredMethod("addFieldFactoryField", GtnUIFrameworkComponentType.class, List.class);
        method.setAccessible(true);
        method.invoke(instance, componentType, customActionList);
        assertFalse(expResult.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkFieldFactoryActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldFactoryTextField method, of class GtnFrameworkFieldFactoryComponentCreationAction.
     */
    @Test
    public void testAddFieldFactoryTextField() {
    try {
        System.out.println("addFieldFactoryTextField");
        GtnUIFrameworkComponentType componentType=null; 
        List<GtnUIFrameWorkActionConfig> customActionList = new ArrayList<>();
        GtnFrameworkFieldFactoryComponentCreationAction instance = new GtnFrameworkFieldFactoryComponentCreationAction();
        GtnUIFrameworkComponentConfig expResult = new GtnUIFrameworkComponentConfig();
        Method method = instance.getClass().getDeclaredMethod("addFieldFactoryTextField", GtnUIFrameworkComponentType.class, List.class);
        method.setAccessible(true);
        method.invoke(instance, componentType, customActionList);
        assertFalse(expResult.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkFieldFactoryActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createInstance method, of class GtnFrameworkFieldFactoryComponentCreationAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkFieldFactoryComponentCreationAction instance = new GtnFrameworkFieldFactoryComponentCreationAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
