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
public class GtnUIFrameworkCMIdentifierEditListTest {
    
    public GtnUIFrameworkCMIdentifierEditListTest() {
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
     * Test of getSearchView method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testGetSearchView() {
        System.out.println("getSearchView");
        GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
        GtnUIFrameworkViewConfig result = instance.getSearchView();
        assertFalse(result.getGtnComponentList() == null || result.getGtnComponentList().isEmpty());
   
    }

    /**
     * Test of addComponentList method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddComponentList() {
       try {
            System.out.println("addComponentList");
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class);
            method.setAccessible(true);
            method.invoke(instance, view);
            assertFalse( view.getGtnComponentList().isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addErrorBanner method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddErrorBanner() {
        try {
            System.out.println("addErrorBanner");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("addErrorBanner",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of companyMasterEditListPanel method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testCompanyMasterEditListPanel() {
        try {
            System.out.println("companyMasterEditListPanel");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("companyMasterEditListPanel",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldLayout method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddFieldLayout() {
        try {
            System.out.println("addFieldLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("addFieldLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldComponent method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddFieldComponent() {
        try {
            System.out.println("addFieldComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("addFieldComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of identifierResultDataTable method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testIdentifierResultDataTable() {
    try {
            System.out.println("identifierResultDataTable");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("identifierResultDataTable",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addIdentifierCodeQualifier method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddIdentifierCodeQualifier() {
        try {
            System.out.println("addIdentifierCodeQualifier");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("addIdentifierCodeQualifier",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addIdentifierCodeQualifierName method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddIdentifierCodeQualifierName() {
        try {
            System.out.println("addIdentifierCodeQualifierName");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("addIdentifierCodeQualifierName",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addEffectiveDates method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddEffectiveDates() {
        try {
            System.out.println("addEffectiveDates");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("addEffectiveDates",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addNotes method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddNotes() {
        try {
            System.out.println("addNotes");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("addNotes",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addButtonLayout method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddButtonLayout() {
        try {
            System.out.println("addButtonLayout");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("addButtonLayout",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSaveButtonComponent method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddSaveButtonComponent() {
    try {
            System.out.println("addSaveButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("addSaveButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addDeleteButtonComponent method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddDeleteButtonComponent() {
        try {
            System.out.println("addDeleteButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("addDeleteButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResetButtonComponent method, of class GtnUIFrameworkCMIdentifierEditList.
     */
    @Test
    public void testAddResetButtonComponent() {
    try {
            System.out.println("addResetButtonComponent");
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnUIFrameworkCMIdentifierEditList instance = new GtnUIFrameworkCMIdentifierEditList();
            Method method = instance.getClass().getDeclaredMethod("addResetButtonComponent",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse( componentList.isEmpty());
        } catch (Exception ex) {
            Logger.getLogger(GtnUIFrameworkCMIdentifierEditListTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
