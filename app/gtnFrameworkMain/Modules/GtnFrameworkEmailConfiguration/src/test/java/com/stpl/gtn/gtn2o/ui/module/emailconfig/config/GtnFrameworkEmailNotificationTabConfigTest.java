/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.emailconfig.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
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
public class GtnFrameworkEmailNotificationTabConfigTest {
    
    public GtnFrameworkEmailNotificationTabConfigTest() {
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
     * Test of addEMailNotificationTab method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddEMailNotificationTab() {
        String methodName = "addEMailNotificationTab";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addPanel method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddPanel() {
        String methodName = "addPanel";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSuccessMailNoficationPanel method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddSuccessMailNoficationPanel() {
        String methodName = "addSuccessMailNoficationPanel";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFailureMailNotificationPanel method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddFailureMailNotificationPanel() {
        String methodName = "addFailureMailNotificationPanel";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addMainLayout method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddMainLayout() {
        String methodName = "addMainLayout";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSuccessMailNoficationLayout method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddSuccessMailNoficationLayout() {
        String methodName = "addSuccessMailNoficationLayout";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFailureMailNotificationLayout method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddFailureMailNotificationLayout() {
        String methodName = "addFailureMailNotificationLayout";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFieldComponent method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddFieldComponent() {
        String methodName = "addFieldComponent";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addProcessName method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddProcessName() {
        String methodName = "addProcessName";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSuccessEmailTo method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddSuccessEmailTo() {
        String methodName = "addSuccessEmailTo";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSuccessSubject method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddSuccessSubject() {
        String methodName = "addSuccessSubject";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSuccessEmailCc method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddSuccessEmailCc() {
        String methodName = "addSuccessEmailCc";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSuccessEmailBody method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddSuccessEmailBody() {
        String methodName = "addSuccessEmailBody";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFailureEmailTo method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddFailureEmailTo() {
        String methodName = "addFailureEmailTo";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFailureSubject method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddFailureSubject() {
        String methodName = "addFailureSubjects";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFailureEmailCc method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddFailureEmailCc() {
        String methodName = "addFailureEmailCc";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addFailureEmailBody method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddFailureEmailBody() {
        String methodName = "addFailureEmailBody";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSaveButtonLayout method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddSaveButtonLayout() {
        String methodName = "addSaveButtonLayout";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSaveButtonComponent method, of class GtnFrameworkEmailNotificationTabConfig.
     */
    @Test
    public void testAddSaveButtonComponent() {
        String methodName = "addSaveButtonComponent";
        GtnFrameworkEmailNotificationTabConfig instance = new GtnFrameworkEmailNotificationTabConfig();
        testComponentConfig(methodName, instance);
    }
    
     public void testComponentConfig(String gtnFrameworkContractDashboardProcessConfigMethodName, GtnFrameworkEmailNotificationTabConfig gtnFrameworkContractDashboardProcessConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnFrameworkEmailNotificationTabConfig gtnFrameworkContractMainConfig) throws SecurityException {
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
            Logger.getLogger(GtnFrameworkEmailNotificationTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
