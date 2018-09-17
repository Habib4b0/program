/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.dynamicclasses;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.mockito.Mockito.times;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Hazihabibullah.Syed
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(GtnUIFrameworkGlobalUI.class)
public class GtnUIFrameworkTransactionDynamicClassFillerTest {
    
    public GtnUIFrameworkTransactionDynamicClassFillerTest() {
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
     * Test of addDynamicObject method, of class GtnUIFrameworkTransactionDynamicClassFiller.
     */
    @Test
    public void testAddDynamicObject() {
        System.out.println("addDynamicObject");
        GtnUIFrameworkTransactionDynamicClassFiller instance = new GtnUIFrameworkTransactionDynamicClassFiller();
        
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
       
        instance.addDynamicObject();
   
        verifyStatic(times(16));
    }
    
}
