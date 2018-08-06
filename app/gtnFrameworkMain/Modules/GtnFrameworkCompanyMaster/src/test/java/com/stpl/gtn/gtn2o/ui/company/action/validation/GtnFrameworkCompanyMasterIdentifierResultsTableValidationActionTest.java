/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
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
public class GtnFrameworkCompanyMasterIdentifierResultsTableValidationActionTest {
    
    public GtnFrameworkCompanyMasterIdentifierResultsTableValidationActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkCompanyMasterIdentifierResultsTableValidationAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkCompanyMasterIdentifierResultsTableValidationAction instance = new GtnFrameworkCompanyMasterIdentifierResultsTableValidationAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }



    /**
     * Test of createInstance method, of class GtnFrameworkCompanyMasterIdentifierResultsTableValidationAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkCompanyMasterIdentifierResultsTableValidationAction instance = new GtnFrameworkCompanyMasterIdentifierResultsTableValidationAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);

    }
    
}
