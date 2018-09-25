/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.emailconfig.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.base.GtnUIFrameworkBaseComponent;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
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
public class GtnFrameworkEmailModuleLandingScreenConfigTest {
    
    public GtnFrameworkEmailModuleLandingScreenConfigTest() {
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
     * Test of getMainView method, of class GtnFrameworkEmailModuleLandingScreenConfig.
     */
    @Test
    public void testGetMainView() {
        System.out.println("getMainView");
        GtnFrameworkEmailModuleLandingScreenConfig instance = new GtnFrameworkEmailModuleLandingScreenConfig();
        GtnUIFrameworkViewConfig result = instance.getMainView();
        assertFalse(result==null);
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkEmailModuleLandingScreenConfig.
     */
    @Test
    public void testAddComponentList() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("addComponentList");
        GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
        Constructor<GtnFrameworkComponentConfigProvider> constructor = GtnFrameworkComponentConfigProvider.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        GtnFrameworkComponentConfigProvider secondOb = constructor.newInstance();
        GtnFrameworkEmailModuleLandingScreenConfig instance = new GtnFrameworkEmailModuleLandingScreenConfig();
        Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class,GtnFrameworkComponentConfigProvider.class);
        method.setAccessible(true);
        method.invoke(instance, view,secondOb); 
        assertFalse(view==null);
    }

    @Test
    public void testAddTabLayout() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("addTabLayout");
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        Constructor<GtnFrameworkComponentConfigProvider> constructor = GtnFrameworkComponentConfigProvider.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        GtnFrameworkComponentConfigProvider secondOb = constructor.newInstance();
        GtnFrameworkEmailModuleLandingScreenConfig instance = new GtnFrameworkEmailModuleLandingScreenConfig();
        Method method = instance.getClass().getDeclaredMethod("addTabLayout",List.class,GtnFrameworkComponentConfigProvider.class);
        method.setAccessible(true);
        method.invoke(instance, componentList,secondOb); 
        assertFalse(componentList.isEmpty());
    }

    /**
     * Test of addTabSheet method, of class GtnFrameworkEmailModuleLandingScreenConfig.
     */
    @Test
    public void testAddTabSheet() throws NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        System.out.println("addTabSheet");
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        GtnFrameworkComponentConfigProvider componentConfig = null;
        Constructor<GtnFrameworkComponentConfigProvider> constructor = GtnFrameworkComponentConfigProvider.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        GtnFrameworkComponentConfigProvider secondOb = constructor.newInstance();
        GtnFrameworkEmailModuleLandingScreenConfig instance = new GtnFrameworkEmailModuleLandingScreenConfig();
        Method method = instance.getClass().getDeclaredMethod("addTabSheet",List.class,GtnFrameworkComponentConfigProvider.class);
        method.setAccessible(true);
        method.invoke(instance, componentList,secondOb); 
        assertFalse(componentList.isEmpty());
    }   
   
}
