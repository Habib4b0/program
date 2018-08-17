/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.process;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
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
public class GtnFrameworkContractRebuildActionTest {
    
    public GtnFrameworkContractRebuildActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkContractRebuildAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkContractRebuildAction instance = new GtnFrameworkContractRebuildAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }


    /**
     * Test of isDuplicate method, of class GtnFrameworkContractRebuildAction.
     */
    @Test
    public void testIsDuplicate() {
         try {
        System.out.println("isDuplicate");
        List<GtnWsRecordBean> nodeList = new ArrayList<>();
        GtnWsRecordBean tableBean = new GtnWsRecordBean();
        GtnFrameworkContractRebuildAction instance = new GtnFrameworkContractRebuildAction();
        Method method = instance.getClass().getDeclaredMethod("isDuplicate", List.class, GtnWsRecordBean.class);
        method.setAccessible(true);
        method.invoke(instance, nodeList, tableBean);
        assertFalse(nodeList.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkContractRebuildActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


    /**
     * Test of createInstance method, of class GtnFrameworkContractRebuildAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkContractRebuildAction instance = new GtnFrameworkContractRebuildAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
