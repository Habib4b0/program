/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.transaction.config;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
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
public class GtnFrameworkTransactionSearchTemplateConfigTest {
    
    public GtnFrameworkTransactionSearchTemplateConfigTest() {
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
     * Test of getSearchView method, of class GtnFrameworkTransactionSearchTemplateConfig.
     */
    @Test
    public void testGetSearchView() {
        System.out.println("getSearchView");
        boolean isInvalid=false;
        GtnFrameworkTransactionSearchTemplateConfig instance = new GtnFrameworkTransactionSearchTemplateConfig(isInvalid);
        GtnUIFrameworkViewConfig result = instance.getSearchView();
        assertFalse(result.getGtnComponentList().isEmpty());
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkTransactionSearchTemplateConfig.
     */
    @Test
    public void testAddComponentList() {
    try {
            System.out.println("addComponentList");
            boolean isInvalid=false;
            GtnFrameworkTransactionSearchTemplateConfig instance = new GtnFrameworkTransactionSearchTemplateConfig(isInvalid);
            GtnUIFrameworkViewConfig cFPPopupView=new GtnUIFrameworkViewConfig();
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class);
            method.setAccessible(true);
            method.invoke(instance, cFPPopupView);
            assertFalse( cFPPopupView.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkTransactionSearchTemplateConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

    /**
     * Test of addSearchCriteriaPanel method, of class GtnFrameworkTransactionSearchTemplateConfig.
     */
    @Test
    public void testAddSearchCriteriaPanel() {
        String methodName = "addSearchCriteriaPanel";
        boolean isInvalid=false;
        GtnFrameworkTransactionSearchTemplateConfig instance = new GtnFrameworkTransactionSearchTemplateConfig(isInvalid);
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResultPanel method, of class GtnFrameworkTransactionSearchTemplateConfig.
     */
    @Test
    public void testAddResultPanel() {
        String methodName = "addResultPanel";
        boolean isInvalid=false;
        GtnFrameworkTransactionSearchTemplateConfig instance = new GtnFrameworkTransactionSearchTemplateConfig(isInvalid);
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResultLayout method, of class GtnFrameworkTransactionSearchTemplateConfig.
     */
    @Test
    public void testAddResultLayout() {
        String methodName = "addResultLayout";
        boolean isInvalid=false;
        GtnFrameworkTransactionSearchTemplateConfig instance = new GtnFrameworkTransactionSearchTemplateConfig(isInvalid);
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldLayout method, of class GtnFrameworkTransactionSearchTemplateConfig.
     */
    @Test
    public void testAddFieldLayout() {
        String methodName = "addFieldLayout";
        boolean isInvalid=false;
        GtnFrameworkTransactionSearchTemplateConfig instance = new GtnFrameworkTransactionSearchTemplateConfig(isInvalid);
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addButtonLayout method, of class GtnFrameworkTransactionSearchTemplateConfig.
     */
    @Test
    public void testAddButtonLayout() {
        String methodName = "addButtonLayout";
        boolean isInvalid=false;
        GtnFrameworkTransactionSearchTemplateConfig instance = new GtnFrameworkTransactionSearchTemplateConfig(isInvalid);
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addExcelButton method, of class GtnFrameworkTransactionSearchTemplateConfig.
     */
    @Test
    public void testAddExcelButton() {
        String methodName = "addExcelButton";
        boolean isInvalid=false;
        GtnFrameworkTransactionSearchTemplateConfig instance = new GtnFrameworkTransactionSearchTemplateConfig(isInvalid);
        testComponentConfig(methodName, instance);
    }
    
    public void testComponentConfig(String gtnFrameworkContractDashboardProcessConfigMethodName, GtnFrameworkTransactionSearchTemplateConfig gtnFrameworkContractDashboardProcessConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkTransactionSearchTemplateConfig gtnFrameworkContractMainConfig) throws SecurityException {
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
