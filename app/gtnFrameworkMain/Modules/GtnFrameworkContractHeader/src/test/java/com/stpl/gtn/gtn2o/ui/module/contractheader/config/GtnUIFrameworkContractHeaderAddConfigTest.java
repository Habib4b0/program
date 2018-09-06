/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.contractheader.config;

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
public class GtnUIFrameworkContractHeaderAddConfigTest {
    
    public GtnUIFrameworkContractHeaderAddConfigTest() {
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
     * Test of getContractHeaderAddView method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testGetContractHeaderAddView() {

        String methodName = "getContractHeaderAddView";
        GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addComponentList method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddComponentList() {
    try {
            System.out.println("addComponentList");
            GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
            GtnUIFrameworkViewConfig cFPPopupView=new GtnUIFrameworkViewConfig();
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class);
            method.setAccessible(true);
            method.invoke(instance, cFPPopupView);
            assertFalse( cFPPopupView.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkContracHeadertInfoTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addContractHeaderInfoPanel method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddContractHeaderInfoPanel() {
        
        String methodName = "addContractHeaderInfoPanel";
        GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addContractHeaderInfoFieldLayout method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddContractHeaderInfoFieldLayout() {
        String methodName = "addContractHeaderInfoFieldLayout";
        GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addFieldCSSLayout method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddFieldCSSLayout() {
        String methodName = "addFieldCSSLayout";
        GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
        testComponentConfig(methodName, instance);

    }

    /**
     * Test of addContractHeaderInfoFieldComponent method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddContractHeaderInfoFieldComponent() {

        String methodName = "addContractHeaderInfoFieldComponent";
        GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
        testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderInfoContractHeaderId method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddContractHeaderInfoContractHeaderId() {
       String methodName = "addContractHeaderInfoContractHeaderId";
       GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
       testComponentConfig(methodName, instance);

    }

    /**
     * Test of addContractHeaderInfoContractHeaderNo method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddContractHeaderInfoContractHeaderNo() {

       String methodName = "addContractHeaderInfoContractHeaderNo";
       GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
       testComponentConfig(methodName, instance);
    }

    /**
     * Test of addContractHeaderInfoContractHeaderName method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddContractHeaderInfoContractHeaderName() {
       String methodName = "addContractHeaderInfoContractHeaderName";
       GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
       testComponentConfig(methodName, instance);

    }

    /**
     * Test of addTabLayout method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddTabLayout() {
        
       String methodName = "addTabLayout";
       GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
       testComponentConfig(methodName, instance);

    }

    /**
     * Test of addTabSheet method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddTabSheet() {
       String methodName = "addTabSheet";
       GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
       testComponentConfig(methodName, instance);

    }

    /**
     * Test of addNotesTab method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddNotesTab() {
       String methodName = "addNotesTab";
       GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
       testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSaveButtonLayout method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddSaveButtonLayout() {
       String methodName = "addSaveButtonLayout";
       GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
       testComponentConfig(methodName, instance);

    }

    /**
     * Test of addBackButtonComponent method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddBackButtonComponent() {
       String methodName = "addBackButtonComponent";
       GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
       testComponentConfig(methodName, instance);
    }

    /**
     * Test of addDeleteButtonComponent method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddDeleteButtonComponent() {
       String methodName = "addDeleteButtonComponent";
       GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
       testComponentConfig(methodName, instance);
    }

    /**
     * Test of addSaveButtonComponent method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddSaveButtonComponent() {
       String methodName = "addSaveButtonComponent";
       GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
       testComponentConfig(methodName, instance);
    }

    /**
     * Test of addResetButtonComponent method, of class GtnUIFrameworkContractHeaderAddConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
       String methodName = "addResetButtonComponent";
       GtnUIFrameworkContractHeaderAddConfig instance = new GtnUIFrameworkContractHeaderAddConfig();
       testComponentConfig(methodName, instance);

    }
    
    public void testComponentConfig(String gtnFrameworkContractDashboardProcessConfigMethodName, GtnUIFrameworkContractHeaderAddConfig gtnFrameworkContractDashboardProcessConfig) {
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

    private List<GtnUIFrameworkComponentConfig> getComponentList(Method method, GtnUIFrameworkContractHeaderAddConfig gtnFrameworkContractMainConfig) throws SecurityException {
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
            Logger.getLogger(GtnUIFrameworkContracHeadertInfoTabConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        return componentList;
    }
    
}
