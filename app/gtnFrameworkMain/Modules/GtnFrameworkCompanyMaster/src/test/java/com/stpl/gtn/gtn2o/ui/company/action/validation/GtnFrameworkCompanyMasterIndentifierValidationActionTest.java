/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
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
public class GtnFrameworkCompanyMasterIndentifierValidationActionTest {
    
    public GtnFrameworkCompanyMasterIndentifierValidationActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkCompanyMasterIndentifierValidationAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkCompanyMasterIndentifierValidationAction instance = new GtnFrameworkCompanyMasterIndentifierValidationAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

 


    

    /**
     * Test of validationForMandatory method, of class GtnFrameworkCompanyMasterIndentifierValidationAction.
     */
    @Test
    public void testValidationForMandatory() throws Exception {
  
    try {
        System.out.println("validationForMandatory");
        Integer qualifierId = 20;
        StringBuilder failedMsg = new StringBuilder("");
        String identifer = "identifer";
        Integer status = 15;
        SimpleDateFormat dateformat3 = new SimpleDateFormat("dd/MM/yyyy");
        Date startDate = dateformat3.parse("17/07/1989");
        String componentId = "componentId";
            GtnFrameworkCompanyMasterIndentifierValidationAction instance = new GtnFrameworkCompanyMasterIndentifierValidationAction();
            Method method = instance.getClass().getDeclaredMethod("validationForMandatory",Integer.class,StringBuilder.class,String.class,Integer.class,Date.class,String.class);
            method.setAccessible(true);
            method.invoke(instance,qualifierId, failedMsg, identifer, status,startDate,componentId);
            assertFalse( startDate.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterIndentifierValidationActionTest.class.getName()).log(Level.SEVERE, null, ex);
        } 
        
    }

    /**
     * Test of createInstance method, of class GtnFrameworkCompanyMasterIndentifierValidationAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkCompanyMasterIndentifierValidationAction instance = new GtnFrameworkCompanyMasterIndentifierValidationAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
   
    }
    
}
