/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.processmonitor.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
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
public class GtnFrameworkProcessMonitorLandingScreenConfigTest {
    
    public GtnFrameworkProcessMonitorLandingScreenConfigTest() {
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
     * Test of getSearchView method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testGetSearchView() {
        System.out.println("getSearchView");
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        GtnUIFrameworkViewConfig result = instance.getSearchView();
        assertFalse(result==null);
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddComponentList() {
        try{
        System.out.println("addComponentList");
        GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class);
        method.setAccessible(true);
        method.invoke(instance, view);
        assertFalse(view==null);
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkProcessMonitorLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addProcessMonitorMainLayout method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddProcessMonitorMainLayout() {
        String methodName = "addProcessMonitorMainLayout";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMonitorEditor method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorEditor() {
        String methodName = "addMonitorEditor";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMonitorResultTable method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorResultTable() {
        String methodName = "addMonitorResultTable";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResultLayout method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddResultLayout() {
        String methodName = "addResultLayout";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldLayout method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddFieldLayout() {
        String methodName = "addFieldLayout";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMonitorEditorLayout method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorEditorLayout() {
        String methodName = "addMonitorEditorLayout";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMonitorInformationCSSLayout method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorInformationCSSLayout() {
        String methodName = "addMonitorInformationCSSLayout";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRunTimesCSSLayout method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddRunTimesCSSLayout() {
        String methodName = "addRunTimesCSSLayout";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMonitorInformationPanel method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorInformationPanel() {
        String methodName = "addMonitorInformationPanel";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMonitorLayout method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorLayout() {
        String methodName = "addMonitorLayout";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMonitorInformationComponent method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorInformationComponent() {
        String methodName = "addMonitorInformationComponent";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRunTimesPanel method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddRunTimesPanel() {
        String methodName = "addRunTimesPanel";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHorizontalCSSLayout method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddHorizontalCSSLayout() {
        String methodName = "addHorizontalCSSLayout";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHorizontalLayoutOneComponent method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddHorizontalLayoutOneComponent() {
        String methodName = "addHorizontalLayoutOneComponent";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHorizontalLayoutTwoComponent method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddHorizontalLayoutTwoComponent() {
        String methodName = "addHorizontalLayoutTwoComponent";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHorizontalLayoutThreeComponent method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddHorizontalLayoutThreeComponent() {
        String methodName = "addHorizontalLayoutThreeComponent";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHours1Ddlb method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddHours1Ddlb() {
        String methodName = "addHours1Ddlb";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHoursLabel method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddHoursLabel() {
        String methodName = "addHours1Ddlb";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMinsLabel method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMinsLabel() {
        String methodName = "addMinsLabel";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMins2Label method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMins2Label() {
        String methodName = "addMins2Label";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHours2Ddlb method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddHours2Ddlb() {
        String methodName = "addHours2Ddlb";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMins3Label method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMins3Label() {
        String methodName = "addMins3Label";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance); 
    }

    /**
     * Test of addHours3Ddlb method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddHours3Ddlb() {
        String methodName = "addHours3Ddlb";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance); 
    }

    /**
     * Test of addRun1Ddlb method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddRun1Ddlb() {
        String methodName = "addRun1Ddlb";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance); 
    }

    /**
     * Test of addRun2Ddlb method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddRun2Ddlb() {
        String methodName = "addRun2Ddlb";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance); 
    }

    /**
     * Test of addRun3Ddlb method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddRun3Ddlb() {
        String methodName = "addRun3Ddlb";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance); 
    }

    /**
     * Test of addMonitorProcessName method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorProcessName() {
        String methodName = "addMonitorProcessName";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance); 
    }

    /**
     * Test of addMonitorProcessType method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorProcessType() {
        String methodName = "addMonitorProcessType";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance); 
    }

    /**
     * Test of addMonitorCalender method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorCalender() {
        String methodName = "addMonitorCalender";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);  
    }

    /**
     * Test of addMonitorStartDate method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorStartDate() {
        String methodName = "addMonitorStartDate";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);  
    }

    /**
     * Test of addMonitorEndDate method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddMonitorEndDate() {
        String methodName = "addMonitorEndDate";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);  
    }

    /**
     * Test of addADDButtonLayout method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddADDButtonLayout() {
        String methodName = "addADDButtonLayout";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);  
    }

    /**
     * Test of addAddButtonComponent method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddAddButtonComponent() {
        String methodName = "addAddButtonComponent";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);  
    }

    /**
     * Test of addDeleteButtonComponent method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddDeleteButtonComponent() {
        String methodName = "addDeleteButtonComponent";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);  
    }

    /**
     * Test of addPagedTableComponent method, of class GtnFrameworkProcessMonitorLandingScreenConfig.
     */
    @Test
    public void testAddPagedTableComponent() {
        String methodName = "addPagedTableComponent";
        GtnFrameworkProcessMonitorLandingScreenConfig instance = new GtnFrameworkProcessMonitorLandingScreenConfig();
        testComponentConfig(methodName, instance);  
    }
    
    public void testComponentConfig(String gtnFrameworkContractDashboardProcessConfigMethodName, GtnFrameworkProcessMonitorLandingScreenConfig gtnFrameworkContractDashboardProcessConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkProcessMonitorLandingScreenConfig gtnFrameworkContractMainConfig) throws SecurityException {
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
            Logger.getLogger(GtnFrameworkProcessMonitorLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
