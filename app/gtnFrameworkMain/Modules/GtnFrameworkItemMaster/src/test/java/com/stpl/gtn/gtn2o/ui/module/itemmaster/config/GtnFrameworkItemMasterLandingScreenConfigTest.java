/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.itemmaster.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
import com.stpl.gtn.gtn2o.ui.framework.component.GtnUIFrameworkComponentConfig;
import com.stpl.gtn.gtn2o.ui.framework.component.table.pagedtable.filter.GtnUIFrameworkPagedTableCustomFilterConfig;
import com.stpl.gtn.gtn2o.ui.framework.engine.view.GtnUIFrameworkViewConfig;
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
public class GtnFrameworkItemMasterLandingScreenConfigTest {
    
    public GtnFrameworkItemMasterLandingScreenConfigTest() {
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
     * Test of getSearchView method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testGetSearchView() {
          System.out.println("getSearchView");
          GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
          GtnUIFrameworkViewConfig result = instance.getSearchView();
          assertFalse(result.getGtnComponentList() == null || result.getGtnComponentList().isEmpty());
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddComponentList() {
    try {
        System.out.println("addComponentList");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            GtnUIFrameworkViewConfig view = new GtnUIFrameworkViewConfig();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addComponentList",GtnUIFrameworkViewConfig.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, view,componentConfig);
            assertFalse( view.getGtnComponentList().isEmpty());
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSearchCriteriaPanel method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddSearchCriteriaPanel() {
        try {
            System.out.println("addSearchCriteriaPanel");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addSearchCriteriaPanel",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }    
    }

    /**
     * Test of addResultPanel method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddResultPanel() {
     try {
            System.out.println("addResultPanel");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addResultPanel",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResultLayout method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddResultLayout() {
            try {
            System.out.println("addResultLayout");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addResultLayout",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldLayout method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddFieldLayout() {
        try {
            System.out.println("addFieldLayout");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addFieldLayout",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addButtonLayout method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddButtonLayout() {
    try {
            System.out.println("addButtonLayout");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addButtonLayout",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addFieldComponent method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddFieldComponent() {
    try {
            System.out.println("addFieldComponent");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addFieldComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSystemId method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddSystemId() {
    try {
            System.out.println("addSystemId");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addSystemId",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemId method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddItemId() {
    try {
            System.out.println("addItemId");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemId",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemNo method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddItemNo() {
    try {
            System.out.println("addItemNo");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemNo",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemName method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddItemName() {
        try {
            System.out.println("addItemName");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemName",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemDesc method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddItemDesc() {
        try {
            System.out.println("addItemDesc");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemDesc",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemStatus method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddItemStatus() {
        try {
            System.out.println("addItemStatus");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemStatus",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemType method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddItemType() {
        try {
            System.out.println("addItemType");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemType",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addTherapyClass method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddTherapyClass() {
        try {
            System.out.println("addTherapyClass");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addTherapyClass",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addNDC9 method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddNDC9() {
        try {
            System.out.println("addNDC9");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addNDC9",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addForm method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddForm() {
        try {
            System.out.println("addForm");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addForm",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemQualifierName method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddItemQualifierName() {
        try {
            System.out.println("addItemQualifierName");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemQualifierName",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemIdentifier method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddItemIdentifier() {
       try {
            System.out.println("addItemIdentifier");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemIdentifier",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addBrand method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddBrand() {
//        System.out.println("addBrand");
//        List<GtnUIFrameworkComponentConfig> componentList = null;
//        GtnFrameworkComponentConfigProvider componentConfig = null;
//        GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
//        instance.addBrand(componentList, componentConfig);
       try {
            System.out.println("addBrand");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addBrand",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addNDC8 method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddNDC8() {
      try {
            System.out.println("addNDC8");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addNDC8",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemStrength method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddItemStrength() {
     try {
            System.out.println("addItemStrength");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemStrength",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemBatchId method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddItemBatchId() {
    try {
            System.out.println("addItemBatchId");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemBatchId",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSearchButtonComponent method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddSearchButtonComponent() {
    try {
            System.out.println("addSearchButtonComponent");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addSearchButtonComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResetButtonComponent method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
    try {
            System.out.println("addResetButtonComponent");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addResetButtonComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addPagedTableComponent method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddPagedTableComponent() {
    try {
            System.out.println("addPagedTableComponent");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addPagedTableComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addActionButtonLayout method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddActionButtonLayout() {
    try {
            System.out.println("addActionButtonLayout");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addActionButtonLayout",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addAddButtonComponent method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddAddButtonComponent() {
    try {
            System.out.println("addAddButtonComponent");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addAddButtonComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addEditButtonComponent method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddEditButtonComponent() {
    try {
            System.out.println("addEditButtonComponent");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addEditButtonComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addViewButtonComponent method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddViewButtonComponent() {
    try {
            System.out.println("addViewButtonComponent");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addViewButtonComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addExcelButtonComponent method, of class GtnFrameworkItemMasterLandingScreenConfig.
     */
    @Test
    public void testAddExcelButtonComponent() {
    try {
            System.out.println("addExcelButtonComponent");
            GtnFrameworkItemMasterLandingScreenConfig instance = new GtnFrameworkItemMasterLandingScreenConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addExcelButtonComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
        } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterLandingScreenConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
