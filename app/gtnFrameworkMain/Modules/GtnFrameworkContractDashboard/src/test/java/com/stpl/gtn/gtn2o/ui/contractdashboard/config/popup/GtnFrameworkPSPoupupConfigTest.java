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
public class GtnFrameworkPSPoupupConfigTest {
    
    public GtnFrameworkPSPoupupConfigTest() {
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
     * Test of getView method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testGetView() {
        System.out.println("getView");
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        GtnUIFrameworkViewConfig result = instance.getView();
        assertFalse(result.getComponentStyle().isEmpty());
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddComponentList() {
    try {
            System.out.println("addComponentList");
            GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
            GtnUIFrameworkViewConfig cFPPopupView=new GtnUIFrameworkViewConfig();
            String cFPPopupNamspacePrefix="";
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, cFPPopupView,cFPPopupNamspacePrefix);
            assertFalse( cFPPopupView.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkPSPoupupConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addPopupMainPanel method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddPopupMainPanel() {
        String methodName = "addPopupMainPanel";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchCriteriaPanel method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddSearchCriteriaPanel() {
        String methodName = "addSearchCriteriaPanel";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addActionButtonLayout method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddActionButtonLayout() {
        String methodName = "addActionButtonLayout";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSelectButtonComponent method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddSelectButtonComponent() {
        String methodName = "addSelectButtonComponent";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCloseButtonComponent method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddCloseButtonComponent() {
        String methodName = "addCloseButtonComponent";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addButtonLayout method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddButtonLayout() {
        String methodName = "addButtonLayout";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchButtonComponent method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddSearchButtonComponent() {
        String methodName = "addSearchButtonComponent";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResetButtonComponent method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
        String methodName = "addResetButtonComponent";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldLayout method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddFieldLayout() {
        String methodName = "addFieldLayout";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResultPanel method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddResultPanel() {
        String methodName = "addResultPanel";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldComponent method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddFieldComponent() {
        String methodName = "addFieldComponent";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPsType method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddPsType() {
        String methodName = "addPsType";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPsStatus method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddPsStatus() {
        String methodName = "addPsStatus";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPsId method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddPsId() {
        String methodName = "addPsId";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPsNo method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddPsNo() {
        String methodName = "addPsNo";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPsName method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddPsName() {
        String methodName = "addPsName";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addItemId method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddItemId() {
        String methodName = "addItemId";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addItemNo method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddItemNo() {
        String methodName = "addItemNo";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addItemName method, of class GtnFrameworkPSPoupupConfig.
     */
    @Test
    public void testAddItemName() {
        String methodName = "addItemName";
        GtnFrameworkPSPoupupConfig instance = new GtnFrameworkPSPoupupConfig();
        testComponentConfig(methodName, instance);
    }
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkPSPoupupConfig gtnFrameworkContractDashboardInfomationTabConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkPSPoupupConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
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
            Logger.getLogger(GtnFrameworkPSPoupupConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
