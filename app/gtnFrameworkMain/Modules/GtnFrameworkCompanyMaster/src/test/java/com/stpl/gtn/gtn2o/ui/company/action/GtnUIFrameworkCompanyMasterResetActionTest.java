/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ws.bean.GtnWsRecordBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyParentBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyTradeClassBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import java.lang.reflect.Method;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.asi.container.ExtContainer;
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
public class GtnUIFrameworkCompanyMasterResetActionTest {
    
    public GtnUIFrameworkCompanyMasterResetActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkCompanyMasterResetAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkCompanyMasterResetAction instance = new GtnUIFrameworkCompanyMasterResetAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }



    /**
     * Test of createInstance method, of class GtnUIFrameworkCompanyMasterResetAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkCompanyMasterResetAction instance = new GtnUIFrameworkCompanyMasterResetAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

    
}
