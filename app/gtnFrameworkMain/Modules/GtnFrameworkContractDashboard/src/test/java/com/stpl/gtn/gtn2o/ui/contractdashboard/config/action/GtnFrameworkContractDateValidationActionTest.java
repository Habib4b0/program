/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
public class GtnFrameworkContractDateValidationActionTest {
    
    public GtnFrameworkContractDateValidationActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkContractDateValidationAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkContractDateValidationAction instance = new GtnFrameworkContractDateValidationAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }


    /**
     * Test of createInstance method, of class GtnFrameworkContractDateValidationAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkContractDateValidationAction instance = new GtnFrameworkContractDateValidationAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of getUpdatedDate method, of class GtnFrameworkContractDateValidationAction.
     */
    @Test
    public void testGetUpdatedDate() {

        try {
            System.out.println("getUpdatedDate");  
            Date date = new Date();
            GtnFrameworkContractDateValidationAction instance = new GtnFrameworkContractDateValidationAction();
            Method method = instance.getClass().getDeclaredMethod("getUpdatedDate",Date.class);
            method.setAccessible(true);
            method.invoke(instance, date);
            assertFalse( date.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkContractDateValidationActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    
  }
}
