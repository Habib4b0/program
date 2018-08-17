/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.action.tabload;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.components.GtnUIFrameworkDataRow;
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
public class GtnFrameworkContractInformationTabLoadActionTest {
    
    public GtnFrameworkContractInformationTabLoadActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkContractInformationTabLoadAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkContractInformationTabLoadAction instance = new GtnFrameworkContractInformationTabLoadAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }


    /**
     * Test of createInstance method, of class GtnFrameworkContractInformationTabLoadAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkContractInformationTabLoadAction instance = new GtnFrameworkContractInformationTabLoadAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }


    /**
     * Test of getCustomisedAliasTableRecord method, of class GtnFrameworkContractInformationTabLoadAction.
     */
    @Test
    public void testGetCustomisedAliasTableRecord() {
        try {
            System.out.println("getCustomisedAliasTableRecord");      
            List<GtnUIFrameworkDataRow> dataRowList = new ArrayList<>();
            List<Object> recordHeader = new ArrayList<>();
            GtnFrameworkContractInformationTabLoadAction instance = new GtnFrameworkContractInformationTabLoadAction();
            Method method = instance.getClass().getDeclaredMethod("getCustomisedAliasTableRecord",List.class,List.class);
            method.setAccessible(true);
            method.invoke(instance, dataRowList, recordHeader);
            assertFalse( recordHeader.toString().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkContractInformationTabLoadActionTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
