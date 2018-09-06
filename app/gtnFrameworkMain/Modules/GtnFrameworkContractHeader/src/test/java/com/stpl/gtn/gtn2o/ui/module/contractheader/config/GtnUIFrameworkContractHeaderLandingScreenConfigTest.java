/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.contractheader.config;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
public class GtnUIFrameworkContractHeaderLandingScreenConfigTest {
    
    public GtnUIFrameworkContractHeaderLandingScreenConfigTest() {
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
     * Test of getSearchView method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testGetSearchView() {
        System.out.println("getSearchView");
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        GtnUIFrameworkViewConfig result = instance.getSearchView();
        assertFalse(result.getGtnComponentList().isEmpty());

    }

    /**
     * Test of addComponentList method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddComponentList() {
        try {
            System.out.println("addComponentList");
            GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
            GtnUIFrameworkViewConfig cFPPopupView=new GtnUIFrameworkViewConfig();
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class);
            method.setAccessible(true);
            method.invoke(instance, cFPPopupView);
            assertFalse( cFPPopupView.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCHSearchCriteriaPanel method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddCHSearchCriteriaPanel() {
        String methodName = "addCHSearchCriteriaPanel";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCHResultPanel method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddCHResultPanel() {
        String methodName = "addCHResultPanel";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResultLayout method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddResultLayout() {
        String methodName = "addResultLayout";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldLayout method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddFieldLayout() {
        String methodName = "addFieldLayout";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldCSSLayout method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddFieldCSSLayout() {
        String methodName = "addFieldCSSLayout";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCHButtonLayout method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddCHButtonLayout() {
        String methodName = "addCHButtonLayout";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldComponent method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddFieldComponent() {
        String methodName = "addFieldComponent";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderId method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddContractHeaderId() {
        String methodName = "addContractHeaderId";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addContractHeaderNo method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddContractHeaderNo() {

        String methodName = "addContractHeaderNo";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderName method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddContractHeaderName() {
        String methodName = "addContractHeaderName";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderStatus method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddContractHeaderStatus() {
        String methodName = "addContractHeaderStatus";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderType method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddContractHeaderType() {
        String methodName = "addContractHeaderType";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addTradeClass method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddTradeClass() {
        String methodName = "addTradeClass";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderTPNo method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddContractHeaderTPNo() {
        String methodName = "addContractHeaderTPNo";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchButtonComponent method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddSearchButtonComponent() {
        String methodName = "addSearchButtonComponent";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResetButtonComponent method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
        String methodName = "addResetButtonComponent";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPagedTableComponent method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddPagedTableComponent() {

        String methodName = "addPagedTableComponent";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCHActionButtonLayout method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddCHActionButtonLayout() {

        String methodName = "addCHActionButtonLayout";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addAddButtonComponent method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddAddButtonComponent() {

        String methodName = "addAddButtonComponent";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
        
    }

    /**
     * Test of addEditButtonComponent method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddEditButtonComponent() {
        
        String methodName = "addEditButtonComponent";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addViewButtonComponent method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddViewButtonComponent() {

        String methodName = "addViewButtonComponent";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addExcelButtonComponent method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testAddExcelButtonComponent() {
        String methodName = "addExcelButtonComponent";
        GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getCustomFilterConfig method, of class GtnUIFrameworkContractHeaderLandingScreenConfig.
     */
    @Test
    public void testGetCustomFilterConfig() {
       try {
            System.out.println("getCustomFilterConfig");
            GtnUIFrameworkContractHeaderLandingScreenConfig instance = new GtnUIFrameworkContractHeaderLandingScreenConfig();
            Map<String, GtnUIFrameworkPagedTableCustomFilterConfig> customFilterConfigMap = new HashMap<>();
            Method method = instance.getClass().getDeclaredMethod("getCustomFilterConfig");
            method.setAccessible(true);
            method.invoke(instance);
            assertFalse( !customFilterConfigMap.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContractHeaderLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnUIFrameworkContractHeaderLandingScreenConfig gtnFrameworkContractDashboardInfomationTabConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnUIFrameworkContractHeaderLandingScreenConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
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
            Logger.getLogger(GtnUIFrameworkContractHeaderLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
