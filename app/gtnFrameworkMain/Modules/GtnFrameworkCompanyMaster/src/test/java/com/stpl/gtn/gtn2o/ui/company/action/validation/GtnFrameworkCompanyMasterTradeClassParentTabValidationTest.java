/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class GtnFrameworkCompanyMasterTradeClassParentTabValidationTest {
    
    public GtnFrameworkCompanyMasterTradeClassParentTabValidationTest() {
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
     * Test of configureParams method, of class GtnFrameworkCompanyMasterTradeClassParentTabValidation.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkCompanyMasterTradeClassParentTabValidation instance = new GtnFrameworkCompanyMasterTradeClassParentTabValidation();
        instance.configureParams(gtnUIFrameWorkActionConfig);

    }



    /**
     * Test of createInstance method, of class GtnFrameworkCompanyMasterTradeClassParentTabValidation.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkCompanyMasterTradeClassParentTabValidation instance = new GtnFrameworkCompanyMasterTradeClassParentTabValidation();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);

    }

    /**
     * Test of checkDateConditionsEqualOrGreater method, of class GtnFrameworkCompanyMasterTradeClassParentTabValidation.
     */
    @Test
    public void testCheckDateConditionsEqualOrGreater() throws Exception {
//        System.out.println("checkDateConditionsEqualOrGreater");
//        Date startDate = null;
//        Date endDate = null;
//        String componentId = "";
//        String equalMsg = "";
//        String lessThanMsg = "";
//        GtnFrameworkCompanyMasterTradeClassParentTabValidation instance = new GtnFrameworkCompanyMasterTradeClassParentTabValidation();
//        instance.checkDateConditionsEqualOrGreater(startDate, endDate, componentId, equalMsg, lessThanMsg);

     try {
        System.out.println("checkDateConditionsEqualOrGreater");
        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = dateformat3.parse("17/07/1989");
        Date endDate = dateformat3.parse("15/10/2007");
        String componentId = "component";
        String equalMsg = "equalMsg";
        String lessThanMsg = "lessThanMsg";
            GtnFrameworkCMCommonValidationAction instance = new GtnFrameworkCMCommonValidationAction();
            Method method = instance.getClass().getDeclaredMethod("checkDateConditionsEqualOrGreater",Date.class,Date.class,String.class,String.class,
			String.class);
            method.setAccessible(true);
            method.invoke(instance, startDate,endDate,componentId,equalMsg,lessThanMsg);
            assertFalse( startDate.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterTradeClassParentTabValidationTest.class.getName()).log(Level.SEVERE, null, ex);
        }   

    }
    
}
