/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.emailconfig.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
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
public class GtnFrameworkEmailServerTabConfigTest {
    
    public GtnFrameworkEmailServerTabConfigTest() {
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
     * Test of addEMailConfigurationTab method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddEMailConfigurationTab() {
        String methodName = "addEMailConfigurationTab";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPanel method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddPanel() {
        String methodName = "addPanel";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMainLayout method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddMainLayout() {
        String methodName = "addMainLayout";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldComponent method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddFieldComponent() {
        String methodName = "addFieldComponent";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSMTPNo method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddSMTPNo() {
        String methodName = "addSMTPNo";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHostName method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddHostName() {
        String methodName = "addHostName";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addEmailAddress method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddEmailAddress() {
        String methodName = "addEmailAddress";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPassword method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddPassword() {
        String methodName = "addPassword";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPortNumber method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddPortNumber() {
        String methodName = "addPortNumber";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addTestMailAddress method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddTestMailAddress() {
        String methodName = "addTestMailAddress";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSaveButtonLayout method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddSaveButtonLayout() {
        String methodName = "addSaveButtonLayout";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addBackButtonComponent method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddBackButtonComponent() {
        String methodName = "addBackButtonComponent";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance); 
    }

    /**
     * Test of addSaveButtonComponent method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddSaveButtonComponent() {
        String methodName = "addSaveButtonComponent";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance); 
    }

    /**
     * Test of addViewButtonComponent method, of class GtnFrameworkEmailServerTabConfig.
     */
    @Test
    public void testAddViewButtonComponent() {
        String methodName = "addViewButtonComponent";
        GtnFrameworkEmailServerTabConfig instance = new GtnFrameworkEmailServerTabConfig();
        testComponentConfig(methodName, instance); 
    }
    
     public void testComponentConfig(String gtnFrameworkContractDashboardProcessConfigMethodName, GtnFrameworkEmailServerTabConfig gtnFrameworkContractDashboardProcessConfig) {
        System.out.println(gtnFrameworkContractDashboardProcessConfigMethodName);
        Method arr[] = gtnFrameworkContractDashboardProcessConfig.getClass().getDeclaredMethods();
        Method method = null;
        for (Method met : arr) {
            if (gtnFrameworkContractDashboardProcessConfigMethodName.equals(met.getName())) {
                method = met;
                break;
            }
        }
        int methodParams = method != null ? method.getParameterCount() : 0;
        if (methodParams != 0) {
            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnFrameworkContractDashboardProcessConfig);
            assertFalse(componentList.isEmpty());
        }
    }

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkEmailServerTabConfig gtnFrameworkContractMainConfig) throws SecurityException {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        try {
            Class[] classArr = method.getParameterTypes();
            Object[] obj = new Object[method.getParameterTypes().length];
            obj[0] = componentList;
            for (int i = 1; i < obj.length; i++) {
                String name = classArr[i].getName();
                switch (classArr[i].getName()) {
                    case "java.lang.String":
                        obj[i] = "VALUE";
                        break;
                    case "java.util.List":
                        obj[i] = new ArrayList<>();
                        break;
                    case "com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider":
                        obj[i] = GtnFrameworkComponentConfigProvider.getInstance();
                        break;
                    default:
                        break;
                }
            }
            method.setAccessible(true);
            method.invoke(gtnFrameworkContractMainConfig, obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnFrameworkEmailServerTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
