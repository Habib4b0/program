/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.action.validation;

import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkAction;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import java.util.ArrayList;
import java.util.List;
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
public class GtnFrameworkFinancialCloseValidationActionTest {
    
    public GtnFrameworkFinancialCloseValidationActionTest() {
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
     * Test of configureParams method, of class GtnFrameworkFinancialCloseValidationAction.
     */
    @Test
    public void testConfigureParams() throws Exception {
        System.out.println("configureParams");
        GtnUIFrameWorkActionConfig gtnUIFrameWorkActionConfig = null;
        GtnFrameworkFinancialCloseValidationAction instance = new GtnFrameworkFinancialCloseValidationAction();
        instance.configureParams(gtnUIFrameWorkActionConfig);
    }

    /**
     * Test of createInstance method, of class GtnFrameworkFinancialCloseValidationAction.
     */
    @Test
    public void testCreateInstance() {
        System.out.println("createInstance");
        GtnFrameworkFinancialCloseValidationAction instance = new GtnFrameworkFinancialCloseValidationAction();
        GtnUIFrameWorkAction result = instance.createInstance();
        assertEquals(instance, result);
    }


    /**
     * Test of isListContainsEmptyElements method, of class GtnFrameworkFinancialCloseValidationAction.
     */
    @Test
    public void testIsListContainsEmptyElements() {
        System.out.println("isListContainsEmptyElements");
        List<String> list = new ArrayList<>();
        list.add("");
        GtnFrameworkFinancialCloseValidationAction instance = new GtnFrameworkFinancialCloseValidationAction();
        boolean expResult = true;
        boolean result = instance.isListContainsEmptyElements(list);
        assertEquals(expResult, result);
    }
    
}
