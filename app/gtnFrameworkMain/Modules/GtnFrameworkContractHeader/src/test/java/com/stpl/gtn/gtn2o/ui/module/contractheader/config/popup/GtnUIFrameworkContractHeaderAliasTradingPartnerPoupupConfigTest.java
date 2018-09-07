/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.contractheader.config.popup;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.contractheader.config.GtnUIFrameworkContractHeaderLandingScreenConfig;
import com.stpl.gtn.gtn2o.ui.module.contractheader.config.GtnUIFrameworkContractHeaderLandingScreenConfigTest;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
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
public class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfigTest {
    
    public GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfigTest() {
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
     * Test of getSearchView method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testGetSearchView() {
        System.out.println("getSearchView");
        String tabName = "vh";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        GtnUIFrameworkViewConfig result = instance.getSearchView(tabName);
        assertFalse(result.getGtnComponentList().isEmpty());
    }

    /**
     * Test of addRootLayout method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddRootLayout() {
        String methodName = "addRootLayout";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldPanel method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddFieldPanel() {
        String methodName = "addFieldPanel";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldLayout method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddFieldLayout() {
        String methodName = "addFieldLayout";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResultPanel method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddResultPanel() {
        String methodName = "addResultPanel";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addButtonLayout method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddButtonLayout() {
        String methodName = "addButtonLayout";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldComponent method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddFieldComponent() {
        String methodName = "addFieldComponent";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCompanyId method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddCompanyId() {
        String methodName = "addCompanyId";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCompanyNo method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddCompanyNo() {
        String methodName = "addCompanyNo";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCompanyName method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddCompanyName() {
       String methodName = "addCompanyName";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCompanyStatus method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddCompanyStatus() {
        String methodName = "addCompanyStatus";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCompanyType method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddCompanyType() {
        String methodName = "addCompanyType";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchButtonComponent method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddSearchButtonComponent() {
        String methodName = "addSearchButtonComponent";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResetButtonComponent method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
        String methodName = "addResetButtonComponent";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPagedTableComponent method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddPagedTableComponent() {
        String methodName = "addPagedTableComponent";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addActionButtonLayout method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddActionButtonLayout() {
        String methodName = "addActionButtonLayout";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSelectButtonComponent method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddSelectButtonComponent() {
        String methodName = "addSelectButtonComponent";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCloseButtonComponent method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testAddCloseButtonComponent() {
        String methodName = "addCloseButtonComponent";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of getCustomFilterConfig method, of class GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig.
     */
    @Test
    public void testGetCustomFilterConfig() {
        String methodName = "getCustomFilterConfig";
        GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig instance = new GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig();
        testComponentConfig(methodName, instance);

    }
    
    public void testComponentConfig(String gtnFrameworkContractMainConfigMethodName, GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig gtnFrameworkContractDashboardInfomationTabConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnUIFrameworkContractHeaderAliasTradingPartnerPoupupConfig gtnFrameworkContractDashboardInfomationTabConfig) throws SecurityException {
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
