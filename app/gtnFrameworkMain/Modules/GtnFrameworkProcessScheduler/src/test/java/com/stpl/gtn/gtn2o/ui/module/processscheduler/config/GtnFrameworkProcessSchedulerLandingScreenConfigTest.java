/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.processscheduler.config;

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
public class GtnFrameworkProcessSchedulerLandingScreenConfigTest {
    
    public GtnFrameworkProcessSchedulerLandingScreenConfigTest() {
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
     * Test of getProcessSchedularView method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testGetProcessSchedularView() {
        System.out.println("getProcessSchedularView");
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        GtnUIFrameworkViewConfig result = instance.getProcessSchedularView();
        assertFalse(result==null);
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddComponentList() {
    try{
        System.out.println("addComponentList");
        GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class);
        method.setAccessible(true);
        method.invoke(instance, view);
        assertFalse(view==null);
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkProcessSchedulerLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addProcessSchedularRootLayout method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddProcessSchedularRootLayout() {
        String methodName = "addProcessSchedularRootLayout";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProcessSchedularRootPanel method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddProcessSchedularRootPanel() {
        String methodName = "addProcessSchedularRootPanel";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProcessSchedularMainLayout method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddProcessSchedularMainLayout() {
        String methodName = "addProcessSchedularMainLayout";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addManualProcessingLayout method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddManualProcessingLayout() {
        String methodName = "addManualProcessingLayout";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addManualProcessingPanel method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddManualProcessingPanel() {
        String methodName = "addManualProcessingPanel";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addManualprocessingComponent method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddManualprocessingComponent() {
        String methodName = "addManualprocessingComponent";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRunButton method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddRunButton() {
        String methodName = "addRunButton";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSchProcessingSchProcessEditorLayout method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddSchProcessingSchProcessEditorLayout() {
        String methodName = "addSchProcessingSchProcessEditorLayout";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addScheduledProcessingPanel method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddScheduledProcessingPanel() {
        String methodName = "addScheduledProcessingPanel";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addScheduledProcessingComponent method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddScheduledProcessingComponent() {
        String methodName = "addScheduledProcessingComponent";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addScheduledProcessEditorPanel method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddScheduledProcessEditorPanel() {
        String methodName = "addScheduledProcessEditorPanel";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProcessNameFrequencyLayout method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddProcessNameFrequencyLayout() {
        String methodName = "addProcessNameFrequencyLayout";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProcessName method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddProcessName() {
        String methodName = "addProcessName";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFequency method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddFequency() {
        String methodName = "addFequency";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addStatusRunEveryLayout method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddStatusRunEveryLayout() {
        String methodName = "addStatusRunEveryLayout";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addStatus method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddStatus() {
        String methodName = "addStatus";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRunEvery method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddRunEvery() {
        String methodName = "addRunEvery";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addHoursLabel method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddHoursLabel() {
        String methodName = "addHoursLabel";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addStartDateMinutesLayout method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddStartDateMinutesLayout() {
        String methodName = "addStartDateMinutesLayout";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addStartDate method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddStartDate() {
        String methodName = "addStartDate";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMinutes2ComboBox method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddMinutes2ComboBox() {
        String methodName = "addMinutes2ComboBox";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMinutesLabel method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddMinutesLabel() {
        String methodName = "addMinutesLabel";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addEndDateLayout method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddEndDateLayout() {
        String methodName = "addEndDateLayout";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addEndDate method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddEndDate() {
        String methodName = "addEndDate";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMinutes3ComboBox method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddMinutes3ComboBox() {
        String methodName = "addMinutes3ComboBox";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addUpdateButton method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddUpdateButton() {
        String methodName = "addMinutes3ComboBox";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRun1 method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddRun1() {
        String methodName = "addRun1";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRun2 method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddRun2() {
        String methodName = "addRun2";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addRun3 method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddRun3() {
        String methodName = "addRun3";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMinutes1 method, of class GtnFrameworkProcessSchedulerLandingScreenConfig.
     */
    @Test
    public void testAddMinutes1() {
        String methodName = "addMinutes1";
        GtnFrameworkProcessSchedulerLandingScreenConfig instance = new GtnFrameworkProcessSchedulerLandingScreenConfig();
        testComponentConfig(methodName, instance);
    }
    
    public void testComponentConfig(String gtnFrameworkContractDashboardProcessConfigMethodName, GtnFrameworkProcessSchedulerLandingScreenConfig gtnFrameworkContractDashboardProcessConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkProcessSchedulerLandingScreenConfig gtnFrameworkContractMainConfig) throws SecurityException {
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
            Logger.getLogger(GtnFrameworkProcessSchedulerLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
