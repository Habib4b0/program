/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.config;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Hazihabibullah.Syed
 */
public class GtnFrameworkCompanyMasterAddConfigTest {
    
    public GtnFrameworkCompanyMasterAddConfigTest() {
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
     * Test of getCompanyMasterAddView method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testGetCompanyMasterAddView() {
        System.out.println("getCompanyMasterAddView");
        GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
        GtnUIFrameworkViewConfig result = instance.getCompanyMasterAddView();
        assertFalse(result.getGtnComponentList() == null || result.getGtnComponentList().isEmpty());
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddComponentList() {
        try {
        System.out.println("addComponentList");
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class);
            method.setAccessible(true);
            method.invoke(instance, view);
            assertFalse( view.getGtnComponentList().isEmpty());
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addErrorBanner method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddErrorBanner() throws IllegalAccessException, NoSuchMethodException, IllegalArgumentException, InvocationTargetException {
         try {
        System.out.println("addErrorBanner");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addErrorBanner",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyInfoPanel method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddCompanyInfoPanel() {
       try {
            System.out.println("addCompanyInfoPanel");
             List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addCompanyInfoPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of addCompanyInfoFieldLayout method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddCompanyInfoFieldLayout() {
        
     try {
            System.out.println("addCompanyInfoFieldLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addCompanyInfoFieldLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyInfoFieldComponent method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddCompanyInfoFieldComponent() {
 try {
            System.out.println("addCompanyInfoFieldComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addCompanyInfoFieldComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of addCompanyInfoCompanyId method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddCompanyInfoCompanyId() {
try {
            System.out.println("addCompanyInfoCompanyId");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addCompanyInfoCompanyId",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    /**
     * Test of addCompanyInfoCompanyNo method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddCompanyInfoCompanyNo() {
       try {
            System.out.println("addCompanyInfoCompanyNo");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addCompanyInfoCompanyNo",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyInfoCompanyName method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddCompanyInfoCompanyName() {
         try {
            System.out.println("addCompanyInfoCompanyName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addCompanyInfoCompanyName",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addTabLayout method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddTabLayout() {
try {
            System.out.println("addTabLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addTabLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * Test of addTabSheet method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddTabSheet() {
try {
            System.out.println("addTabSheet");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addTabSheet",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addNotesTab method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddNotesTab() {
    try {
            System.out.println("addNotesTab");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addNotesTab",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSaveButtonLayout method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddSaveButtonLayout() {
 try {
            System.out.println("addSaveButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addSaveButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
   
    }

    /**
     * Test of addBackButtonComponent method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddBackButtonComponent() {
 try {
            System.out.println("addBackButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addBackButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    /**
     * Test of addSaveButtonComponent method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddSaveButtonComponent() {
    try {
            System.out.println("addSaveButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addSaveButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResetButtonComponent method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
            try {
            System.out.println("addResetButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addResetButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addDeleteButtonComponent method, of class GtnFrameworkCompanyMasterAddConfig.
     */
    @Test
    public void testAddDeleteButtonComponent() {
         try {
            System.out.println("addDeleteButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkCompanyMasterAddConfig instance = new GtnFrameworkCompanyMasterAddConfig();
            Method method = instance.getClass().getDeclaredMethod("addDeleteButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkCompanyMasterAddConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
