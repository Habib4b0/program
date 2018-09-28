/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.processmonitor.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.action.GtnUIFrameWorkActionConfig;
import com.stpl.gtn.gtn2o.ui.framework.type.GtnUIFrameworkActionType;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
public class GtnFrameworProcessMonitorLayoutsConfigTest {
    
    public GtnFrameworProcessMonitorLayoutsConfigTest() {
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
     * Test of getInstance method, of class GtnFrameworProcessMonitorLayoutsConfig.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        GtnFrameworProcessMonitorLayoutsConfig result = GtnFrameworProcessMonitorLayoutsConfig.getInstance();
        assertFalse(result==null);
        
    }

    /**
     * Test of getUIFrameworkActionConfig method, of class GtnFrameworProcessMonitorLayoutsConfig.
     */
    @Test
    public void testGetUIFrameworkActionConfig() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("getUIFrameworkActionConfig");
        GtnUIFrameworkActionType type = GtnUIFrameworkActionType.ALERT_ACTION;
        
        Constructor<GtnFrameworProcessMonitorLayoutsConfig> constructor = GtnFrameworProcessMonitorLayoutsConfig.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        GtnFrameworProcessMonitorLayoutsConfig secondOb = constructor.newInstance();
        
        Method method = secondOb.getClass().getDeclaredMethod("getUIFrameworkActionConfig",GtnUIFrameworkActionType.class);
        method.setAccessible(true);
        method.invoke(secondOb, type); 
        assertFalse(type==null);
    }
    
}
