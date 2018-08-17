/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
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
public class GtnFrameworkAddToTreeCommonActionTest {
    
    public GtnFrameworkAddToTreeCommonActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkAddToTreeCommonAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkAddToTreeCommonAction instance = new GtnFrameworkAddToTreeCommonAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of isDuplicate method, of class GtnFrameworkAddToTreeCommonAction.
     */
    @Test
    public void testIsDuplicate() {  
           try {
            System.out.println("isDuplicate");
            GtnFrameworkAddToTreeCommonAction instance = new GtnFrameworkAddToTreeCommonAction();
            List<GtnWsRecordBean> nodeList = new ArrayList<>();
            GtnWsRecordBean tableBean = new GtnWsRecordBean();
            Method method = instance.getClass().getDeclaredMethod("isDuplicate",List.class,GtnWsRecordBean.class);
            method.setAccessible(true);
            method.invoke(instance, nodeList,tableBean);
            assertFalse( nodeList.size()!=0);
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkAddToTreeCommonActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
  
    }

    /**
     * Test of getReplacedString method, of class GtnFrameworkAddToTreeCommonAction.
     */
    @Test
    public void testGetReplacedString() {
        System.out.println("getReplacedString");
        String[] value = {"gsd?ss","gdfhbd"};
        String expResult = "gsdgdfhbdss";
        String result = GtnFrameworkAddToTreeCommonAction.getReplacedString(value);
        assertEquals(expResult, result);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkAddToTreeCommonAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkAddToTreeCommonAction instance = new GtnFrameworkAddToTreeCommonAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }
    
}
