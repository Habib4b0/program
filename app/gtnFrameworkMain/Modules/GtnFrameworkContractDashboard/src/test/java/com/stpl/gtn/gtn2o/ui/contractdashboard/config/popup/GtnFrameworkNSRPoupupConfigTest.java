/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.contractdashboard.config.popup;

import com.stpl.gtn.gtn2o.ui.framework.action.validation.GtnUIFrameworkValidationConfig;
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
public class GtnFrameworkNSRPoupupConfigTest {
    
    public GtnFrameworkNSRPoupupConfigTest() {
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
     * Test of getView method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testGetView() {
        System.out.println("getView");
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        GtnUIFrameworkViewConfig result = instance.getView();
        assertFalse(result.getComponentStyle().isEmpty());
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddComponentList() {
    try {
            System.out.println("addComponentList");
            GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
            GtnUIFrameworkViewConfig cFPPopupView=new GtnUIFrameworkViewConfig();
            String cFPPopupNamspacePrefix="";
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, cFPPopupView,cFPPopupNamspacePrefix);
            assertFalse( cFPPopupView.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkNSRPoupupConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addPopupMainPanel method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddPopupMainPanel() {
        String methodName = "addPopupMainPanel";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchCriteriaPanel method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddSearchCriteriaPanel() {
        String methodName = "addSearchCriteriaPanel";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addActionButtonLayout method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddActionButtonLayout() {
        String methodName = "addActionButtonLayout";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSelectButtonComponent method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddSelectButtonComponent() {
        String methodName = "addSelectButtonComponent";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCloseButtonComponent method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddCloseButtonComponent() {
        String methodName = "addCloseButtonComponent";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDetailsButtonComponent method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddDetailsButtonComponent() {
        String methodName = "addDetailsButtonComponent";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addButtonLayout method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddButtonLayout() {
        String methodName = "addButtonLayout";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchButtonComponent method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddSearchButtonComponent() {
        String methodName = "addSearchButtonComponent";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResetButtonComponent method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
        String methodName = "addResetButtonComponent";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldLayout method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddFieldLayout() {
        String methodName = "addFieldLayout";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMainResultPanel method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddMainResultPanel() {
        String methodName = "addMainResultPanel";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDetailResultPanel method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddDetailResultPanel() {
        String methodName = "addDetailResultPanel";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResultPanel method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddResultPanel() {
        String methodName = "addResultPanel";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldComponent method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddFieldComponent() {
        String methodName = "addFieldComponent";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRuleType method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddRuleType() {
        String methodName = "addRuleType";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRuleCategory method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddRuleCategory() {
        String methodName = "addRuleCategory";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRuleNo method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddRuleNo() {
        String methodName = "addRuleNo";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRuleName method, of class GtnFrameworkNSRPoupupConfig.
     */
    @Test
    public void testAddRuleName() {
        String methodName = "addRuleName";
        GtnFrameworkNSRPoupupConfig instance = new GtnFrameworkNSRPoupupConfig();
        testComponentConfig(methodName, instance);
    }
    
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkNSRPoupupConfig gtnFrameworkContractDashboardInfomationTabConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkNSRPoupupConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
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
            Logger.getLogger(GtnFrameworkNSRPoupupConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
}
