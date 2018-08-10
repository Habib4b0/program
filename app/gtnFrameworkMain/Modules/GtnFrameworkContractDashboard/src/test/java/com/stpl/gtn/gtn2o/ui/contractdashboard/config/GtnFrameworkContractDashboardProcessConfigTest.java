/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.tabsheet.GtnUIFrameworkTabConfig;
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
public class GtnFrameworkContractDashboardProcessConfigTest {

    public GtnFrameworkContractDashboardProcessConfigTest() {
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
     * Test of addContractDashboardProcessPanel method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddContractDashboardProcessPanel() {
        String methodName = "addContractDashboardProcessPanel";
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractDashboardContractInfoPanel method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddContractDashboardContractInfoPanel() {
        String methodName = "addContractDashboardContractInfoPanel";
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addContractInfoFieldLayout method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddContractInfoFieldLayout() {
        String methodName = "addContractInfoFieldLayout";
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addContractInfoFieldComponent method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddContractInfoFieldComponent() {
        String methodName = "addContractInfoFieldComponent";

        GtnFrameworkComponentConfigProvider commonConfig = null;
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addContractInfoContractId method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddContractInfoContractId() {
        String methodName = "addContractInfoContractId";

        GtnFrameworkComponentConfigProvider commonConfig = null;
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addContractInfoContractNo method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddContractInfoContractNo() {
        String methodName = "addContractInfoContractNo";

        GtnFrameworkComponentConfigProvider commonConfig = null;
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addContractInfoContractName method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddContractInfoContractName() {
        String methodName = "addContractInfoContractName";

        GtnFrameworkComponentConfigProvider commonConfig = null;
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractInfoContractType method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddContractInfoContractType() {
        String methodName = "addContractInfoContractType";

        GtnFrameworkComponentConfigProvider commonConfig = null;
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addContractDashboardProcessLayout method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddContractDashboardProcessLayout() {
        String methodName = "addContractDashboardProcessLayout";

        String parent = "";
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addErrorDisplay method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddErrorDisplay() {
        String methodName = "addErrorDisplay";
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addProcessTabSheetComponent method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddProcessTabSheetComponent() {
        String methodName = "addProcessTabSheetComponent";
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addProcessTab method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddProcessTab() {
        String methodName = "addProcessTab";

        List<GtnUIFrameworkTabConfig> tabConfigList = null;
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of getNotesTab method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testGetNotesTab() {
        try {
            System.out.println("getNotesTab");
            GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
            Method method = instance.getClass().getDeclaredMethod("getNotesTab");
            method.setAccessible(true);
            GtnUIFrameworkTabConfig tabConfig = (GtnUIFrameworkTabConfig) method.invoke(instance);
            assertEquals(tabConfig.getTabCaption(), "Notes");
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex) {
            Logger.getLogger(GtnFrameworkContractDashboardProcessConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of addSubmitButtonLayout method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddSubmitButtonLayout() {
        String methodName = "addSubmitButtonLayout";

        String parent = "";
        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addBackButtonComponent method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddBackButtonComponent() {
        String methodName = "addBackButtonComponent";

        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addCloseButtonComponent method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddCloseButtonComponent() {
        String methodName = "addCloseButtonComponent";

        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addSubmitButtonComponent method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddSubmitButtonComponent() {
        String methodName = "addSubmitButtonComponent";

        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addWithdrowButtonComponent method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddWithdrowButtonComponent() {
        String methodName = "addWithdrowButtonComponent";

        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addApproveButtonComponent method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddApproveButtonComponent() {
        String methodName = "addApproveButtonComponent";

        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addRejectButtonComponent method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddRejectButtonComponent() {
        String methodName = "addRejectButtonComponent";

        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addCancelButtonComponent method, of class
     * GtnFrameworkContractDashboardProcessConfig.
     */
    @Test
    public void testAddCancelButtonComponent() {
        String methodName = "addCancelButtonComponent";

        GtnFrameworkContractDashboardProcessConfig instance = new GtnFrameworkContractDashboardProcessConfig();
        testComponentConfig(methodName, instance);

    }

    public void testComponentConfig(String gtnFrameworkContractDashboardProcessConfigMethodName, GtnFrameworkContractDashboardProcessConfig gtnFrameworkContractDashboardProcessConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkContractDashboardProcessConfig gtnFrameworkContractMainConfig) throws SecurityException {
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
            Logger.getLogger(GtnFrameworkContractDashboardProcessConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }

}
