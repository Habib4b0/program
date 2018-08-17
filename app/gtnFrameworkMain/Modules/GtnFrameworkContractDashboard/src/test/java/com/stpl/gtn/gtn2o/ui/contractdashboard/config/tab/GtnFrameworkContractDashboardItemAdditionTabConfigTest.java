/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
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
public class GtnFrameworkContractDashboardItemAdditionTabConfigTest {
    
    public GtnFrameworkContractDashboardItemAdditionTabConfigTest() {
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
     * Test of getTabConfig method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testGetTabConfig() {
        System.out.println("getTabConfig");
        String mainNamspacePrefix = "";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        GtnUIFrameworkTabConfig result = instance.getTabConfig(mainNamspacePrefix);
        assertFalse(result.getTabCaption().isEmpty());
    }

    /**
     * Test of addEditTableLayout method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testAddEditTableLayout() {
        String methodName = "addEditTableLayout";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchFieldLayout method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testAddSearchFieldLayout() {
        String methodName = "addSearchFieldLayout";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchType method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testAddSearchType() {
        String methodName = "addSearchType";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchFields method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testAddSearchFields() {
        String methodName = "addSearchFields";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchValue method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testAddSearchValue() {
        String methodName = "addSearchValue";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchButtonComponent method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testAddSearchButtonComponent() {
        String methodName = "addSearchButtonComponent";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDualListBoxLayout method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testAddDualListBoxLayout() {
        String methodName = "addDualListBoxLayout";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveButtonsLayout method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testMoveButtonsLayout() {
        String methodName = "moveButtonsLayout";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveRight method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testMoveRight() {
        String methodName = "moveRight";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveLeft method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testMoveLeft() {
        String methodName = "moveLeft";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveAllRight method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testMoveAllRight() {
        String methodName = "moveAllRight";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveAllLeft method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testMoveAllLeft() {
        String methodName = "moveAllLeft";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of leftResultDataTable method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testLeftResultDataTable() {
        String methodName = "leftResultDataTable";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of rightResultDataTable method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testRightResultDataTable() {
        String methodName = "rightResultDataTable";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of viewResultDataTable method, of class GtnFrameworkContractDashboardItemAdditionTabConfig.
     */
    @Test
    public void testViewResultDataTable() {
        String methodName = "viewResultDataTable";
        GtnFrameworkContractDashboardItemAdditionTabConfig instance = new GtnFrameworkContractDashboardItemAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkContractDashboardItemAdditionTabConfig gtnFrameworkContractDashboardInfomationTabConfig) {
        System.out.println(gtnFrameworkContractMainConfigMethodName);
        Method arr[] = gtnFrameworkContractDashboardInfomationTabConfig.getClass().getDeclaredMethods();
        Method method = null;
        for (Method met : arr) {
            if (gtnFrameworkContractMainConfigMethodName.equals(met.getName())) {
                method = met;
                break;
            }
        }
        int methodParams = method != null ? method.getParameterCount() : 0;
        if (methodParams != 0) {
            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnFrameworkContractDashboardInfomationTabConfig);
            assertFalse(componentList.isEmpty());
        }
    }

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkContractDashboardItemAdditionTabConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
        List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
        try {
            Class[] classArr = method.getParameterTypes();
            Object[] obj = new Object[method.getParameterTypes().length];
            obj[0] = componentList;
            for (int i = 1; i < obj.length; i++) {
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
            method.invoke(gtnFrameworkContractDashboardInfomationTabConfig, obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnFrameworkContractDashboardInfomationTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
