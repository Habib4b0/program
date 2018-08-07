/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.company.config;

import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
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
public class GtnUIFrameworkCMParentCompanyPopupTest {
    
    public GtnUIFrameworkCMParentCompanyPopupTest() {
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
     * Test of getSearchView method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testGetSearchView() {
        System.out.println("getSearchView");
        GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
        GtnUIFrameworkViewConfig result = instance.getSearchView();
        assertFalse(result.getGtnComponentList() == null || result.getGtnComponentList().isEmpty());

    }

    /**
     * Test of addComponentList method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddComponentList() {
    try {
            System.out.println("addComponentList");
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class);
            method.setAccessible(true);
            method.invoke(instance, view);
            assertFalse( view.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldLayout method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddFieldLayout() {
    try {
            System.out.println("addFieldLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addFieldLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResultPanel method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddResultPanel() {
          try {
            System.out.println("addResultPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addResultPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResultLayout method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddResultLayout() {
    try {
            System.out.println("addResultLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addResultLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addButtonLayout method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddButtonLayout() {
        try {
            System.out.println("addButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldComponent method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddFieldComponent() {
    try {
            System.out.println("addFieldComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addFieldComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyId method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddCompanyId() {
       try {
            System.out.println("addCompanyId");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addCompanyId",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyNo method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddCompanyNo() {
         try {
            System.out.println("addCompanyNo");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addCompanyNo",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyName method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddCompanyName() {
        try {
            System.out.println("addCompanyName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addCompanyName",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyStatus method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddCompanyStatus() {
         try {
            System.out.println("addCompanyStatus");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addCompanyStatus",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addCompanyType method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddCompanyType() {
        try {
            System.out.println("addCompanyType");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addCompanyType",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSearchButtonComponent method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddSearchButtonComponent() {
        try {
            System.out.println("addSearchButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addSearchButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResetButtonComponent method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddResetButtonComponent() {
        try {
            System.out.println("addResetButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addResetButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addPagedTableComponent method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddPagedTableComponent() {
        try {
            System.out.println("addPagedTableComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addPagedTableComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addActionButtonLayout method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddActionButtonLayout() {
        try {
            System.out.println("addActionButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addActionButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addAddButtonComponent method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddAddButtonComponent() {
     try {
            System.out.println("addAddButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addAddButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addEditButtonComponent method, of class GtnUIFrameworkCMParentCompanyPopup.
     */
    @Test
    public void testAddEditButtonComponent() {
           try {
            System.out.println("addEditButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMParentCompanyPopup instance = new GtnUIFrameworkCMParentCompanyPopup();
            Method method = instance.getClass().getDeclaredMethod("addEditButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMParentCompanyPopupTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
