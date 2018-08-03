/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.relationshipbuilder.config.action;

import com.stpl.gtn.gtn2o.ui.framework.engine.GtnUIFrameworkGlobalUI;
import java.lang.reflect.Constructor;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import static org.mockito.Mockito.times;
import org.powermock.api.mockito.PowerMockito;
import static org.powermock.api.mockito.PowerMockito.*;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

/**
 *
 * @author Karthik.Raja
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(GtnUIFrameworkGlobalUI.class)
public class GtnFrameworkValueChangeManagerTest {

    public GtnFrameworkValueChangeManagerTest() {
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
     * Test of isValueChangeAllowed method, of class
     * GtnFrameworkValueChangeManager.
     */
    @Test
    public void testIsValueChangeAllowed() {
        System.out.println("isValueChangeAllowed");
        PowerMockito.mockStatic(GtnUIFrameworkGlobalUI.class);
        boolean expResult = true;
        when(GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString())).thenReturn(expResult);

        GtnFrameworkValueChangeManager.setValueChangeAllowed(expResult);
        Boolean result = GtnFrameworkValueChangeManager.isValueChangeAllowed();

        verifyStatic(times(1));
        GtnUIFrameworkGlobalUI.getSessionProperty(Mockito.anyString());
        assertEquals(expResult, result);
    }

    @Test
    public void testSetValueChangeAllowed() {

        try {
            Constructor cons = GtnFrameworkValueChangeManager.class.getDeclaredConstructors()[0];
            cons.setAccessible(true);
            cons.newInstance();
        } catch (Exception e) {
            assertEquals(e.getCause().getMessage(), "Can't create Object for this class");
        }
    }

}
