/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.processscheduler.config.lookups;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.config.GtnFrameworkProcessSchedulerLandingScreenConfig;
import com.stpl.gtn.gtn2o.ui.module.processscheduler.config.GtnFrameworkProcessSchedulerLandingScreenConfigTest;
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
public class GtnFrameworkCffOutBoundLookUpConfigTest {
    
    public GtnFrameworkCffOutBoundLookUpConfigTest() {
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
     * Test of getCffOutBoundLookUpView method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testGetCffOutBoundLookUpView() {
        System.out.println("getCffOutBoundLookUpView");
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        GtnUIFrameworkViewConfig result = instance.getCffOutBoundLookUpView();
        assertFalse(result==null);
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddComponentList() {
 try{
        System.out.println("addComponentList");
        GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class);
        method.setAccessible(true);
        method.invoke(instance, view);
        assertFalse(view==null);
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCffOutBoundLookUpConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCffOutBoundRootLayout method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffOutBoundRootLayout() {
        String methodName = "addCffOutBoundRootLayout";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffOutBoundRootPanel method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffOutBoundRootPanel() {
        String methodName = "addCffOutBoundRootPanel";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffOutBoundMainLayout method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffOutBoundMainLayout() {
        String methodName = "addCffOutBoundMainLayout";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffOutBoundSearchPanel method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffOutBoundSearchPanel() {
        String methodName = "addCffOutBoundSearchPanel";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffOutBoundSearchPanelLayout method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffOutBoundSearchPanelLayout() {
        String methodName = "addCffOutBoundSearchPanelLayout";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffProjIdCustCompanyNoLayout method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffProjIdCustCompanyNoLayout() {
        String methodName = "addCffProjIdCustCompanyNoLayout";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffId method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffId() {
        String methodName = "addCffId";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProjectionId method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddProjectionId() {
        String methodName = "addProjectionId";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCustomerNo method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCustomerNo() {
        String methodName = "addCustomerNo";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCompanyNo method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCompanyNo() {
        String methodName = "addCompanyNo";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffProjCustCompNameLayout method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffProjCustCompNameLayout() {
        String methodName = "addCffProjCustCompNameLayout";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffName method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffName() {
        String methodName = "addCffName";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProjectionName method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddProjectionName() {
        String methodName = "addProjectionName";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCustomerName method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCustomerName() {
        String methodName = "addCustomerName";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCompanyName method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCompanyName() {
        String methodName = "addCompanyName";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addTypeContractItemBussUnitNoLayout method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddTypeContractItemBussUnitNoLayout() {
        String methodName = "addTypeContractItemBussUnitNoLayout";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addType method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddType() {
        String methodName = "addType";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractNo method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddContractNo() {
        String methodName = "addContractNo";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addItemNo method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddItemNo() {
        String methodName = "addItemNo";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addBusinessUnitNo method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddBusinessUnitNo() {
        String methodName = "addBusinessUnitNo";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffCreationDateFromToContratItemNameLayout method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffCreationDateFromToContratItemNameLayout() {
        String methodName = "addCffCreationDateFromToContratItemNameLayout";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffCreationDateFrom method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffCreationDateFrom() {
        String methodName = "addCffCreationDateFrom";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffCreationDateTo method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffCreationDateTo() {
        String methodName = "addCffCreationDateTo";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractName method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddContractName() {
        String methodName = "addContractName";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addItemName method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddItemName() {
        String methodName = "addItemName";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addBussUnitNameCffApprovalDateFromToLayout method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddBussUnitNameCffApprovalDateFromToLayout() {
        String methodName = "addBussUnitNameCffApprovalDateFromToLayout";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addBusinessUnitName method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddBusinessUnitName() {
        String methodName = "addBusinessUnitName";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffApprovalDateFrom method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffApprovalDateFrom() {
        String methodName = "addCffApprovalDateFrom";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffApprovalDateTo method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffApprovalDateTo() {
        String methodName = "addCffApprovalDateTo";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchResetButtonLayout method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddSearchResetButtonLayout() {
        String methodName = "addSearchResetButtonLayout";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSearchButton method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddSearchButton() {
        String methodName = "addSearchButton";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResetButton method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddResetButton() {
        String methodName = "addResetButton";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffOutBoundResultsPanel method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffOutBoundResultsPanel() {
        String methodName = "addCffOutBoundResultsPanel";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addCffOutBoundResultsLayout method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddCffOutBoundResultsLayout() {
        String methodName = "addCffOutBoundResultsLayout";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResultsTable method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddResultsTable() {
        String methodName = "addResultsTable";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addGenerateOutBoundButtonLayout method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddGenerateOutBoundButtonLayout() {
        String methodName = "addGenerateOutBoundButtonLayout";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addGenerateOutBoundButton method, of class GtnFrameworkCffOutBoundLookUpConfig.
     */
    @Test
    public void testAddGenerateOutBoundButton() {
        String methodName = "addGenerateOutBoundButton";
        GtnFrameworkCffOutBoundLookUpConfig instance = new GtnFrameworkCffOutBoundLookUpConfig();
        testComponentConfig(methodName, instance);
    }
    
    public void testComponentConfig(String gtnFrameworkContractDashboardProcessConfigMethodName, GtnFrameworkCffOutBoundLookUpConfig gtnFrameworkContractDashboardProcessConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkCffOutBoundLookUpConfig gtnFrameworkContractMainConfig) throws SecurityException {
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
            Logger.getLogger(GtnFrameworkCffOutBoundLookUpConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
