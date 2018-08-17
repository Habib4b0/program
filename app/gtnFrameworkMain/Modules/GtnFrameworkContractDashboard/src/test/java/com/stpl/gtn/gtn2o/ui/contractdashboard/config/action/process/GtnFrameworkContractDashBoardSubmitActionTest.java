/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.notestab.util.NotesDTO;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
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
public class GtnFrameworkContractDashBoardSubmitActionTest {
    
    public GtnFrameworkContractDashBoardSubmitActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkContractDashBoardSubmitAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkContractDashBoardSubmitAction instance = new GtnFrameworkContractDashBoardSubmitAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }


   

    /**
     * Test of createInstance method, of class GtnFrameworkContractDashBoardSubmitAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkContractDashBoardSubmitAction instance = new GtnFrameworkContractDashBoardSubmitAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    /**
     * Test of getValidateActionConfig method, of class GtnFrameworkContractDashBoardSubmitAction.
     */
    @Test
    public void testGetValidateActionConfig() {
        try {
        System.out.println("getValidateActionConfig");
        GtnWsContractDashboardSessionBean processDataBean = new GtnWsContractDashboardSessionBean();
        List<String> fieldList = new ArrayList<>();
        List<String> fieldMessageList = new ArrayList<>();
        GtnFrameworkContractDashBoardSubmitAction instance = new GtnFrameworkContractDashBoardSubmitAction();
        GtnUIFrameWorkActionConfig expResult = new GtnUIFrameWorkActionConfig();
        Method method = instance.getClass().getDeclaredMethod("getValidateActionConfig", GtnWsContractDashboardSessionBean.class, List.class,List.class);
        method.setAccessible(true);
        method.invoke(instance, processDataBean, fieldList,fieldMessageList);
        assertFalse(expResult.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkContractDashBoardSubmitActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
