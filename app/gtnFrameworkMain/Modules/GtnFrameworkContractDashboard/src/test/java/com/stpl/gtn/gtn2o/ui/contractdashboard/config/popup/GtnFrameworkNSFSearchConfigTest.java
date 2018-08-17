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
public class GtnFrameworkNSFSearchConfigTest {
    
    public GtnFrameworkNSFSearchConfigTest() {
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
     * Test of getView method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testGetView() {
        System.out.println("getView");
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        GtnUIFrameworkViewConfig result = instance.getView();
        assertFalse(result.getComponentStyle().isEmpty());
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddComponentList() {  
        try {
            System.out.println("addComponentList");
            GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
            GtnUIFrameworkViewConfig cFPPopupView=new GtnUIFrameworkViewConfig();
            String cFPPopupNamspacePrefix="";
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, cFPPopupView,cFPPopupNamspacePrefix);
            assertFalse( cFPPopupView.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkNSFSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }

    /**
     * Test of addPopupMainPanel method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddPopupMainPanel() {
        String methodName = "addPopupMainPanel";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchCriteriaPanel method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddSearchCriteriaPanel() {
        String methodName = "addSearchCriteriaPanel";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addActionButtonLayout method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddActionButtonLayout() {
        String methodName = "addActionButtonLayout";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSelectButtonComponent method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddSelectButtonComponent() {
        String methodName = "addSelectButtonComponent";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCloseButtonComponent method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddCloseButtonComponent() {
        String methodName = "addCloseButtonComponent";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addButtonLayout method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddButtonLayout() {
        String methodName = "addButtonLayout";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchButtonComponent method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddSearchButtonComponent() {
        String methodName = "addSearchButtonComponent";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResetButtonComponent method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
        String methodName = "addResetButtonComponent";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldLayout method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddFieldLayout() {
        String methodName = "addFieldLayout";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResultPanel method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddResultPanel() {
        String methodName = "addResultPanel";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldComponent method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddFieldComponent() {
        String methodName = "addFieldComponent";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFormulaType method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddFormulaType() {
        String methodName = "addFormulaType";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFormulaId method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddFormulaId() {
        String methodName = "addFormulaId";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFormulaNo method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddFormulaNo() {
        String methodName = "addFormulaNo";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFormulaName method, of class GtnFrameworkNSFSearchConfig.
     */
    @Test
    public void testAddFormulaName() {
        String methodName = "addFormulaName";
        GtnFrameworkNSFSearchConfig instance = new GtnFrameworkNSFSearchConfig();
        testComponentConfig(methodName, instance);
    }
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkNSFSearchConfig gtnFrameworkContractDashboardInfomationTabConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkNSFSearchConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
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
            Logger.getLogger(GtnFrameworkNSFSearchConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
