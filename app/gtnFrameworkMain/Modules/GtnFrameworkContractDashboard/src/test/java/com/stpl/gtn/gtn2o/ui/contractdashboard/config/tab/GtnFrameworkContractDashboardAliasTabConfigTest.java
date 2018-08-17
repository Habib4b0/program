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
public class GtnFrameworkContractDashboardAliasTabConfigTest {

    public GtnFrameworkContractDashboardAliasTabConfigTest() {
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
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testGetTabConfig() {
        System.out.println("getTabConfig");
        String mainNamspacePrefix = "";
        GtnFrameworkContractDashboardAliasTabConfig instance = new GtnFrameworkContractDashboardAliasTabConfig();
        GtnUIFrameworkTabConfig result = instance.getTabConfig(mainNamspacePrefix);
        assertEquals(result.getTabCaption(), "Alias");
    }

    /**
     * Test of contractAliasInformationPanel method, of class
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testContractAliasInformationPanel() {
        String methodName = "contractAliasInformationPanel";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of contractAliasInformationLayout method, of class
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testContractAliasInformationLayout() {
        String methodName = "contractAliasInformationLayout";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addTradingPartnerNo method, of class
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testAddTradingPartnerNo() {
        String methodName = "addTradingPartnerNo";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractAliasNo method, of class
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testAddContractAliasNo() {
        String methodName = "addContractAliasNo";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addStartDate method, of class
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testAddStartDate() {
        String methodName = "addStartDate";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addEndDate method, of class
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testAddEndDate() {
        String methodName = "addEndDate";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractAliasType method, of class
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testAddContractAliasType() {
        String methodName = "addContractAliasType";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractAliasName method, of class
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testAddContractAliasName() {
        String methodName = "addContractAliasName";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of contractAliasAddButtonComponent method, of class
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testContractAliasAddButtonComponent() {
        String methodName = "contractAliasAddButtonComponent";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of contractAliasResultTable method, of class
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testContractAliasResultTable() {
        String methodName = "contractAliasResultTable";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of contractAliasRemoveButton method, of class
     * GtnFrameworkContractDashboardAliasTabConfig.
     */
    @Test
    public void testContractAliasRemoveButton() {
        String methodName = "contractAliasRemoveButton";
        GtnFrameworkContractDashboardMainConfig instance = new GtnFrameworkContractDashboardMainConfig();
        testComponentConfig(methodName, instance);
    }

    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkContractDashboardMainConfig gtnFrameworkContractMainConfig) {
        System.out.println(gtnFrameworkContractMainConfigMethodName);
        Method arr[] = gtnFrameworkContractMainConfig.getClass().getDeclaredMethods();
        Method method = null;
        for (Method met : arr) {
            if (gtnFrameworkContractMainConfigMethodName.equals(met.getName())) {
                method = met;
                break;
            }
        }
        int methodParams = method != null ? method.getParameterCount() : 0;
        if (methodParams != 0) {
            List<GtnUIFrameworkComponentConfig> componentList = getComponentList(method, gtnFrameworkContractMainConfig);
            assertFalse(componentList.isEmpty());
        }
    }

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkContractDashboardMainConfig gtnFrameworkContractMainConfig) throws SecurityException {
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
            method.invoke(gtnFrameworkContractMainConfig, obj);
        } catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnFrameworkContractDashboardMainConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }

}
