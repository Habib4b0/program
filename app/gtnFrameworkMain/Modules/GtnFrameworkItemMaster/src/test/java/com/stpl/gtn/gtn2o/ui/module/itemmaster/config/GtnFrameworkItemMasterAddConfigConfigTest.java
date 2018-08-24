/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.stpl.gtn.gtn2o.ui.module.itemmaster.config;

import com.stpl.gtn.gtn2o.config.GtnFrameworkComponentConfigProvider;
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
public class GtnFrameworkItemMasterAddConfigConfigTest {
    
    public GtnFrameworkItemMasterAddConfigConfigTest() {
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
     * Test of getItemMasterAddView method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testGetItemMasterAddView() {
          System.out.println("getItemMasterAddView");
          GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
          GtnUIFrameworkViewConfig result = instance.getItemMasterAddView();
          assertFalse(result.getGtnComponentList() == null || result.getGtnComponentList().isEmpty());
    }

    /**
     * Test of addComponentList method, of class GtnFrameworkItemMasterAddConfigConfig.
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
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemInfoPanel method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddItemInfoPanel() {
            try {
            System.out.println("addItemInfoPanel");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemInfoPanel",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }       
    }

    /**
     * Test of addItemInfoFieldLayout method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddItemInfoFieldLayout() {
        try {
            System.out.println("addItemInfoFieldLayout");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            String parentId = "";
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemInfoFieldLayout",List.class,GtnFrameworkComponentConfigProvider.class,String.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig,parentId);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemInfoFieldComponent method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddItemInfoFieldComponent() {
    try {
            System.out.println("addItemInfoFieldComponent");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemInfoFieldComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemInfoItemId method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddItemInfoItemId() {
 try {
            System.out.println("addItemInfoItemId");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemInfoItemId",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemInfoItemNo method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddItemInfoItemNo() {
    try {
            System.out.println("addItemInfoItemNo");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemInfoItemNo",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemInfoItemName method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddItemInfoItemName() {
    try {
            System.out.println("addItemInfoItemName");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemInfoItemName",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addItemInfoDesc method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddItemInfoDesc() {
    try {
            System.out.println("addItemInfoDesc");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addItemInfoDesc",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addTabLayout method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddTabLayout() {
    try {
            System.out.println("addTabLayout");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addTabLayout",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addTabSheet method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddTabSheet() {
    try {
            System.out.println("addTabSheet");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addTabSheet",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addNotesTab method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddNotesTab() {
    try {
            System.out.println("addNotesTab");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            Method method = instance.getClass().getDeclaredMethod("addNotesTab",List.class);
            method.setAccessible(true);
            method.invoke(instance, componentList);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSaveButtonLayout method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddSaveButtonLayout() {
    try {
            System.out.println("addSaveButtonLayout");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addSaveButtonLayout",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addBackButtonComponent method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddBackButtonComponent() {
    try {
            System.out.println("addBackButtonComponent");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addBackButtonComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addSaveButtonComponent method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddSaveButtonComponent() {
    try {
            System.out.println("addSaveButtonComponent");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addSaveButtonComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of addResetButtonComponent method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddResetButtonComponent() {
    try {
            System.out.println("addResetButtonComponent");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addResetButtonComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    /**
     * Test of addDeleteButtonComponent method, of class GtnFrameworkItemMasterAddConfigConfig.
     */
    @Test
    public void testAddDeleteButtonComponent() {
    try {
            System.out.println("addDeleteButtonComponent");
            GtnFrameworkItemMasterAddConfigConfig instance = new GtnFrameworkItemMasterAddConfigConfig();
            List<GtnUIFrameworkComponentConfig> componentList = new ArrayList<>();
            GtnFrameworkComponentConfigProvider componentConfig = GtnFrameworkComponentConfigProvider.getInstance();
            Method method = instance.getClass().getDeclaredMethod("addDeleteButtonComponent",List.class,GtnFrameworkComponentConfigProvider.class);
            method.setAccessible(true);
            method.invoke(instance, componentList,componentConfig);
            assertFalse(componentList.isEmpty());   
       } catch (Exception ex) {
            Logger.getLogger(GtnFrameworkItemMasterAddConfigConfigTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
