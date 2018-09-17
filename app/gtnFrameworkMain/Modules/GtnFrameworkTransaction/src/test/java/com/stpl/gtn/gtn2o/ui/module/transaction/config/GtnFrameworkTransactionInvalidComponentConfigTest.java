/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.config;

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
public class GtnFrameworkTransactionInvalidComponentConfigTest {
    
    public GtnFrameworkTransactionInvalidComponentConfigTest() {
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
     * Test of getComponentsForModules method, of class GtnFrameworkTransactionInvalidComponentConfig.
     */
    @Test
    public void testGetComponentsForModules() {
        String methodName = "getComponentsForModules";
        GtnFrameworkTransactionInvalidComponentConfig instance = new GtnFrameworkTransactionInvalidComponentConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldComponentForInvalidIntegration method, of class GtnFrameworkTransactionInvalidComponentConfig.
     */
    @Test
    public void testAddFieldComponentForInvalidIntegration() {
        String methodName = "addFieldComponentForInvalidIntegration";
        GtnFrameworkTransactionInvalidComponentConfig instance = new GtnFrameworkTransactionInvalidComponentConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMainLayoutForInvalidId method, of class GtnFrameworkTransactionInvalidComponentConfig.
     */
    @Test
    public void testAddMainLayoutForInvalidId() {
        String methodName = "addMainLayoutForInvalidId";
        GtnFrameworkTransactionInvalidComponentConfig instance = new GtnFrameworkTransactionInvalidComponentConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInvalidFromDate method, of class GtnFrameworkTransactionInvalidComponentConfig.
     */
    @Test
    public void testAddInvalidFromDate() {
        String methodName = "addInvalidFromDate";
        GtnFrameworkTransactionInvalidComponentConfig instance = new GtnFrameworkTransactionInvalidComponentConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInvalidIdTextBox method, of class GtnFrameworkTransactionInvalidComponentConfig.
     */
    @Test
    public void testAddInvalidIdTextBox() {
        String methodName = "addInvalidIdTextBox";
        GtnFrameworkTransactionInvalidComponentConfig instance = new GtnFrameworkTransactionInvalidComponentConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInvalidTableComboBox method, of class GtnFrameworkTransactionInvalidComponentConfig.
     */
    @Test
    public void testAddInvalidTableComboBox() {
        String methodName = "addInvalidTableComboBox";
        GtnFrameworkTransactionInvalidComponentConfig instance = new GtnFrameworkTransactionInvalidComponentConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addInvalidToDate method, of class GtnFrameworkTransactionInvalidComponentConfig.
     */
    @Test
    public void testAddInvalidToDate() {
        String methodName = "addInvalidToDate";
        GtnFrameworkTransactionInvalidComponentConfig instance = new GtnFrameworkTransactionInvalidComponentConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getMainLayoutForIdAndFromDate method, of class GtnFrameworkTransactionInvalidComponentConfig.
     */
    @Test
    public void testGetMainLayoutForIdAndFromDate() {
        String methodName = "getMainLayoutForIdAndFromDate";
        GtnFrameworkTransactionInvalidComponentConfig instance = new GtnFrameworkTransactionInvalidComponentConfig();
        testComponentConfig(methodName, instance);
    }
    
    public void testComponentConfig(String gtnFrameworkContractDashboardProcessConfigMethodName, GtnFrameworkTransactionInvalidComponentConfig gtnFrameworkContractDashboardProcessConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkTransactionInvalidComponentConfig gtnFrameworkContractMainConfig) throws SecurityException {
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
                    default:
                        break;
                }
            }
            method.setAccessible(true);
            method.invoke(gtnFrameworkContractMainConfig, obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnFrameworkTransactionInvalidComponentConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
