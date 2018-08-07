/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyParentBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyTradeClassBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterCompanyUdcInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterFinancialCloseBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterIdentifierInfoBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.GtnCMasterInformationBean;
import com.stpl.gtn.gtn2o.ws.companymaster.bean.NotesTabBean;
import com.stpl.gtn.gtn2o.ws.request.GtnUIFrameworkWebserviceRequest;
import com.stpl.gtn.gtn2o.ws.request.cmrequest.GtnCMasterRequest;
import java.lang.reflect.Method;
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
public class GtnUIFrameworkCompanyMasterSaveActionTest {
    
    public GtnUIFrameworkCompanyMasterSaveActionTest() {
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
     * Test of configureParams method, of class GtnUIFrameworkCompanyMasterSaveAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnUIFrameworkCompanyMasterSaveAction instance = new GtnUIFrameworkCompanyMasterSaveAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    

    /**
     * Test of createInstance method, of class GtnUIFrameworkCompanyMasterSaveAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnUIFrameworkCompanyMasterSaveAction instance = new GtnUIFrameworkCompanyMasterSaveAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }

   

    
}
