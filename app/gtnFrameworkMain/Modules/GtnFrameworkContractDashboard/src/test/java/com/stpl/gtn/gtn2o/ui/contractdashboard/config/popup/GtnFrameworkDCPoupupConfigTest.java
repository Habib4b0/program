/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup;

import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
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
public class GtnFrameworkDCPoupupConfigTest {
    
    public GtnFrameworkDCPoupupConfigTest() {
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
     * Test of getView method, of class GtnFrameworkDCPoupupConfig.
     */
    @Test
    public void testGetView() {
        System.out.println("getView");
        GtnFrameworkDCPoupupConfig instance = new GtnFrameworkDCPoupupConfig();
        GtnUIFrameworkViewConfig result = instance.getView();
        assertFalse(result.getComponentStyle().isEmpty());
    }
    
}
