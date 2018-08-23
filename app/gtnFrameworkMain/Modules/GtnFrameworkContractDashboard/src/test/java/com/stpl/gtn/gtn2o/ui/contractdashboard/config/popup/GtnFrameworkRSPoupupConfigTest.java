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
public class GtnFrameworkRSPoupupConfigTest {
    
    public GtnFrameworkRSPoupupConfigTest() {
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
     * Test of getView method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testGetView() {
        System.out.println("getView");
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        GtnUIFrameworkViewConfig result = instance.getView();
        assertFalse(result.getComponentStyle().isEmpty());
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddComponentList() {
    try {
            System.out.println("addComponentList");
            GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
            GtnUIFrameworkViewConfig cFPPopupView=new GtnUIFrameworkViewConfig();
            String cFPPopupNamspacePrefix="";
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, cFPPopupView,cFPPopupNamspacePrefix);
            assertFalse( cFPPopupView.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkRSPoupupConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addPopupMainPanel method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddPopupMainPanel() {
        String methodName = "addPopupMainPanel";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchCriteriaPanel method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddSearchCriteriaPanel() {
        String methodName = "addSearchCriteriaPanel";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addActionButtonLayout method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddActionButtonLayout() {
        String methodName = "addActionButtonLayout";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSelectButtonComponent method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddSelectButtonComponent() {
        String methodName = "addSelectButtonComponent";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCloseButtonComponent method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddCloseButtonComponent() {
        String methodName = "addCloseButtonComponent";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addButtonLayout method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddButtonLayout() {
        String methodName = "addButtonLayout";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchButtonComponent method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddSearchButtonComponent() {
        String methodName = "addSearchButtonComponent";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResetButtonComponent method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
        String methodName = "addResetButtonComponent";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldLayout method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddFieldLayout() {
        String methodName = "addFieldLayout";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResultPanel method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddResultPanel() {
        String methodName = "addResultPanel";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldComponent method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddFieldComponent() {
        String methodName = "addFieldComponent";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRsType method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddRsType() {
        String methodName = "addRsType";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRsStatus method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddRsStatus() {
        String methodName = "addRsStatus";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProgramType method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddProgramType() {
        String methodName = "addProgramType";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRsId method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddRsId() {
        String methodName = "addRsId";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRsNo method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddRsNo() {
        String methodName = "addRsNo";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRsName method, of class GtnFrameworkRSPoupupConfig.
     */
    @Test
    public void testAddRsName() {
        String methodName = "addRsName";
        GtnFrameworkRSPoupupConfig instance = new GtnFrameworkRSPoupupConfig();
        testComponentConfig(methodName, instance);
    }
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnFrameworkRSPoupupConfig gtnFrameworkContractDashboardInfomationTabConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkRSPoupupConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
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
            Logger.getLogger(GtnFrameworkRSPoupupConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
