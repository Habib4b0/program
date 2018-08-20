/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.tab;

import com.stpl.gtn.gtn2o.ui.contractdashboard.config.GtnFrameworkContractDashboardMainConfig;
import com.stpl.gtn.gtn2o.ui.contractdashboard.config.GtnFrameworkContractDashboardMainConfigTest;
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
public class GtnFrameworkContractDashboardCompanyAdditionTabConfigTest {

    public GtnFrameworkContractDashboardCompanyAdditionTabConfigTest() {
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
     * Test of getTabConfig method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testGetTabConfig() {
        System.out.println("getTabConfig");
        String mainNamspacePrefix = "";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        GtnUIFrameworkTabConfig result = instance.getTabConfig(mainNamspacePrefix);
        assertFalse(result.getTabCaption().isEmpty());
    }

    /**
     * Test of addEditTableLayout method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testAddEditTableLayout() {
        String methodName = "addEditTableLayout";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchFieldLayout method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testAddSearchFieldLayout() {
        String methodName = "addSearchFieldLayout";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchFields method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testAddSearchFields() {
        String methodName = "addSearchFields";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchValue method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testAddSearchValue() {
        String methodName = "addSearchValue";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchButtonComponent method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testAddSearchButtonComponent() {
        String methodName = "addSearchButtonComponent";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDualListBoxLayout method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testAddDualListBoxLayout() {
        String methodName = "addDualListBoxLayout";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveButtonsLayout method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testMoveButtonsLayout() {
        String methodName = "moveButtonsLayout";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveRight method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testMoveRight() {
        String methodName = "moveRight";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveLeft method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testMoveLeft() {
        String methodName = "moveLeft";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveAllRight method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testMoveAllRight() {
        String methodName = "moveAllRight";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of moveAllLeft method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testMoveAllLeft() {
        String methodName = "moveAllLeft";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of leftResultDataTable method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testLeftResultDataTable() {
        String methodName = "leftResultDataTable";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of rightResultDataTable method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testRightResultDataTable() {
        String methodName = "rightResultDataTable";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of viewResultDataTable method, of class
     * GtnFrameworkContractDashboardCompanyAdditionTabConfig.
     */
    @Test
    public void testViewResultDataTable() {
        String methodName = "viewResultDataTable";
        GtnFrameworkContractDashboardCompanyAdditionTabConfig instance = new GtnFrameworkContractDashboardCompanyAdditionTabConfig();
        testComponentConfig(methodName, instance);
    }

    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkContractDashboardCompanyAdditionTabConfig gtnFrameworkContractDashboardCompanyAdditionTabConfig) {
        System.out.println(gtnFrameworkContractMainConfigMethodName);
        Method arr[] = gtnFrameworkContractDashboardCompanyAdditionTabConfig.getClass().getDeclaredMethods();
        Method method = null;
        for (Method met : arr) {
            if (gtnFrameworkContractMainConfigMethodName.equals(met.getName())) {
                method = met;
                break;
            }
        }
        int methodParams = method != null ? method.getParameterCount() : 0;
        if (methodParams != 0) {
            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnFrameworkContractDashboardCompanyAdditionTabConfig);
            assertFalse(componentList.isEmpty());
        }
    }

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkContractDashboardCompanyAdditionTabConfig gtnFrameworkContractDashboardCompanyAdditionTabConfig) throws SecurityException {
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
            method.invoke(gtnFrameworkContractDashboardCompanyAdditionTabConfig, obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnFrameworkContractDashboardMainConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
}
