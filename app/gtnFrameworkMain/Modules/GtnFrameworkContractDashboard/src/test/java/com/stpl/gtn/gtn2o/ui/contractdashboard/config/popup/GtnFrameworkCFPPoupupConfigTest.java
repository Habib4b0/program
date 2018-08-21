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
public class GtnFrameworkCFPPoupupConfigTest {
    
    public GtnFrameworkCFPPoupupConfigTest() {
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
     * Test of getView method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testGetView() {
        System.out.println("getView");
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        GtnUIFrameworkViewConfig result = instance.getView();
        assertFalse(result.getComponentStyle().isEmpty());
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddComponentList() {
        try {
            System.out.println("addComponentList");
            GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
            GtnUIFrameworkViewConfig cFPPopupView=new GtnUIFrameworkViewConfig();
            String cFPPopupNamspacePrefix="";
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, cFPPopupView,cFPPopupNamspacePrefix);
            assertFalse( cFPPopupView.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCFPPoupupConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addPopupMainPanel method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddPopupMainPanel() {
        String methodName = "addPopupMainPanel";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchCriteriaPanel method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddSearchCriteriaPanel() {
        String methodName = "addSearchCriteriaPanel";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addActionButtonLayout method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddActionButtonLayout() {
        String methodName = "addActionButtonLayout";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSelectButtonComponent method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddSelectButtonComponent() {
        String methodName = "addSelectButtonComponent";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCloseButtonComponent method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddCloseButtonComponent() {
        String methodName = "addCloseButtonComponent";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addButtonLayout method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddButtonLayout() {
        String methodName = "addButtonLayout";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchButtonComponent method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddSearchButtonComponent() {
        String methodName = "addSearchButtonComponent";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResetButtonComponent method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
        String methodName = "addResetButtonComponent";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldLayout method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddFieldLayout() {
        String methodName = "addFieldLayout";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResultPanel method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddResultPanel() {
        String methodName = "addResultPanel";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldComponent method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddFieldComponent() {
        String methodName = "addFieldComponent";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpType method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddCfpType() {
        String methodName = "addCfpType";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpStatus method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddCfpStatus() {
        String methodName = "addCfpStatus";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpId method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddCfpId() {
        String methodName = "addCfpId";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpNo method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddCfpNo() {
        String methodName = "addCfpNo";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCfpName method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddCfpName() {
        String methodName = "addCfpName";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCompanyId method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddCompanyId() {
        String methodName = "addCompanyId";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCompanyNo method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddCompanyNo() {
        String methodName = "addCompanyNo";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCompanyName method, of class GtnFrameworkCFPPoupupConfig.
     */
    @Test
    public void testAddCompanyName() {
        String methodName = "addCompanyName";
        GtnFrameworkCFPPoupupConfig instance = new GtnFrameworkCFPPoupupConfig();
        testComponentConfig(methodName, instance);
    }
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkCFPPoupupConfig gtnFrameworkContractDashboardInfomationTabConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkCFPPoupupConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
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
            Logger.getLogger(GtnFrameworkCFPPoupupConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
