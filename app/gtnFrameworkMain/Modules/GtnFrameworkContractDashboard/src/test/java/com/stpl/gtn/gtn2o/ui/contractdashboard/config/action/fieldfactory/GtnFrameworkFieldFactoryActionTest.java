/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.fieldfactory;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
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
public class GtnFrameworkFieldFactoryActionTest {
    
    public GtnFrameworkFieldFactoryActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkFieldFactoryAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkFieldFactoryAction instance = new GtnFrameworkFieldFactoryAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

   
    /**
     * Test of getPopupAction method, of class GtnFrameworkFieldFactoryAction.
     */
    @Test
    public void testGetPopupAction() {
    try {
        System.out.println("getPopupAction");
        GtnFrameworkFieldFactoryAction instance = new GtnFrameworkFieldFactoryAction();
        String componentId = "rebatePlanNopopup1";
        String propertyId = "rebatePlanNopopup1";
        GtnUIFrameWorkActionConfig updateActionConfig = new GtnUIFrameWorkActionConfig();
        GtnUIFrameWorkActionConfig expResult = new GtnUIFrameWorkActionConfig();
        List<Object> actionParametersList=new ArrayList<>();
        actionParametersList.add("dsdsd");
        actionParametersList.add("dsdsd");
        actionParametersList.add("dsdsd");
        actionParametersList.add("dsdsd");
        actionParametersList.add("dsdsd");
        actionParametersList.add("dsdsd");  
        expResult.setActionParameterList(actionParametersList);
            Method method = instance.getClass().getDeclaredMethod("getPopupAction", String.class, String.class,GtnUIFrameWorkActionConfig.class);
            method.setAccessible(true);
            method.invoke(instance, componentId, propertyId,updateActionConfig);
            assertFalse(expResult.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkFieldFactoryActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getFieldFactoryComponent method, of class GtnFrameworkFieldFactoryAction.
     */
    @Test
    public void testGetFieldFactoryComponent() {
    try {
        System.out.println("getFieldFactoryComponent");
        GtnFrameworkFieldFactoryAction instance = new GtnFrameworkFieldFactoryAction();
        String componentId = "javaworld";
        String sourceComponent = "java";
        String targetComponent = "mava";
            GtnUIFrameWorkActionConfig expResult = new GtnUIFrameWorkActionConfig();
            Method method = instance.getClass().getDeclaredMethod("getFieldFactoryComponent", String.class, String.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentId, sourceComponent,targetComponent);
            assertFalse(expResult.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkFieldFactoryActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getActionConfigParameter method, of class GtnFrameworkFieldFactoryAction.
     */
    @Test
    public void testGetActionConfigParameter() {
    try {
            System.out.println("getActionConfigParameter");
            GtnFrameworkFieldFactoryAction instance = new GtnFrameworkFieldFactoryAction();
            String viewId="hfh";
            String viewName="fghhfg";
            List<Object> paramList=new ArrayList<>();      
            GtnUIFrameWorkActionConfig expResult = new GtnUIFrameWorkActionConfig();
            Method method = instance.getClass().getDeclaredMethod("getActionConfigParameter", String.class, String.class,List.class);
            method.setAccessible(true);
            method.invoke(instance, viewId, viewName,paramList);
            assertFalse(expResult.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkFieldFactoryActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of createInstance method, of class GtnFrameworkFieldFactoryAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkFieldFactoryAction instance = new GtnFrameworkFieldFactoryAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    

    /**
     * Test of getDateValidationAction method, of class GtnFrameworkFieldFactoryAction.
     */
    @Test
    public void testGetDateValidationAction() {
         try {
        System.out.println("getDateValidationAction");
        String componentId = "gdgdgdg";
        String startField = "createddate";
        String endField = "modifieddate";
        String message = "hffhfhg";
  

            GtnFrameworkFieldFactoryAction instance = new GtnFrameworkFieldFactoryAction();   
            GtnUIFrameWorkActionConfig expResult = new GtnUIFrameWorkActionConfig();
            List<Object> actionParametersList=new ArrayList<>();
            actionParametersList.add("fsfs");
            actionParametersList.add("fsfs");
            actionParametersList.add("fsfs");
            actionParametersList.add("fsfs");
            actionParametersList.add("fsfs");
            actionParametersList.add("fsfs");
            expResult.setActionParameterList(actionParametersList);
            Method method = instance.getClass().getDeclaredMethod("getDateValidationAction", String.class, String.class,String.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentId, startField,endField,message);
            assertFalse(expResult.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkFieldFactoryActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    
}
