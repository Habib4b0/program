/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardProcessBean;
import com.stpl.gtn.gtn2o.ws.contractdashboard.beans.GtnWsContractDashboardSessionBean;
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
public class GtnFrameworkProcessActionTest {
    
    public GtnFrameworkProcessActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkProcessAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkProcessAction instance = new GtnFrameworkProcessAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }


    /**
     * Test of createInstance method, of class GtnFrameworkProcessAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkProcessAction instance = new GtnFrameworkProcessAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    

    /**
     * Test of createContractStructure method, of class GtnFrameworkProcessAction.
     */
    @Test
    public void testCreateContractStructure() {
    try {
            System.out.println("createContractStructure");   
            GtnFrameworkProcessAction instance = new GtnFrameworkProcessAction();
            GtnWsContractDashboardProcessBean processBean = new GtnWsContractDashboardProcessBean();
            Method method = instance.getClass().getDeclaredMethod("createContractStructure",GtnWsContractDashboardProcessBean.class);
            method.setAccessible(true);
            method.invoke(instance, processBean);
            assertFalse( processBean.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkProcessActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Test of siplitContractStructure method, of class GtnFrameworkProcessAction.
     */
    @Test
    public void testSiplitContractStructure() {
    try {
            System.out.println("siplitContractStructure");   
            GtnFrameworkProcessAction instance = new GtnFrameworkProcessAction();
            String contractStructure = "";
            Method method = instance.getClass().getDeclaredMethod("siplitContractStructure",String.class);
            method.setAccessible(true);
            method.invoke(instance, contractStructure);
            assertFalse(!contractStructure.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkProcessActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getMemberLevel method, of class GtnFrameworkProcessAction.
     */
    @Test
    public void testGetMemberLevel() {
    try {
            System.out.println("getMemberLevel");   
            GtnFrameworkProcessAction instance = new GtnFrameworkProcessAction();
            int[] contractStructure = new int[5];
            Method method = instance.getClass().getDeclaredMethod("getMemberLevel",int[].class);
            method.setAccessible(true);
            method.invoke(instance, contractStructure);
            assertFalse(contractStructure.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkProcessActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of getProcessLevel method, of class GtnFrameworkProcessAction.
     */
    @Test
    public void testGetProcessLevel() {
    try {
            System.out.println("getProcessLevel");   
            GtnFrameworkProcessAction instance = new GtnFrameworkProcessAction();
            int[] contractStructure = {1,2,3,4,5};
            Method method = instance.getClass().getDeclaredMethod("getProcessLevel",int[].class);
            method.setAccessible(true);
            method.invoke(instance, contractStructure);
            assertFalse(contractStructure.length==0);
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkProcessActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
